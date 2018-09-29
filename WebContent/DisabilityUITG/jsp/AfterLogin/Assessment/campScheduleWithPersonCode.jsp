<%-- 
    Document   : campScheduleWithPersonCode
    Created on : Sep 7, 2014, 10:38:36 PM
    Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            int noOfrows = 0;
            String personcode = "";
            String result = "";
            if (request.getAttribute("noOfRows") != null) {
                noOfrows = Integer.parseInt(request.getAttribute("noOfRows").toString());
            }
            String propertyName = null, styleId = null;

            if (request.getAttribute("personcode") != null) {
                personcode = (String) request.getAttribute("personcode").toString();
            }
            if (request.getAttribute("result") != null) {
                result = (String) request.getAttribute("result").toString();
            }
%>

<script type="text/javascript">
   
    function checkValidations(){
        var flag=true;

        var phoneno = document.forms[0].phoneno;

        if(phoneno == null || phoneno.value == ""){
            alert("Phone No must be Entered");
            document.forms[0].phoneno.focus();
            return false;
        }
        var type_disability = document.forms[0].type_disability;
        if(type_disability !=null){
            if(type_disability.value == ""){
                alert("Type of Disability must be selected!");
                document.forms[0].type_disability.focus();
                return false;
            }
        }
        flag=campValidation();

        if(!flag)
            return flag;
        flag=campDateValidation();

        //      if(!flag)
        //        return flag;
        //    flag=checkMultipleDisabilies();


        if(!flag)
            return flag;
        if(type_disability.value == "6"){
            //kavya
            flag=checkSelectingMultiple();
            if(!flag)
                return flag;
        }

        flag=checkMultipleDisabilies();
        if(!flag)
            return flag;

        return true;
    }

    function checkMultipleDisabilies() {
        if(document.forms[0].type_disability.value==6){
            if(document.forms[0].noOfdisabilities.value==""){
                alert("Please Enter Multiple Disability Count");
                document.forms[0].noOfdisabilities.focus();
                return false;
            }
        }
        return true;
    }
    var flag=true;
    function checkSelectingMultiple(){


    <%

                for (int p = 0; p < noOfrows; p++) {
                    for (int j = 1; j <= 3; j++) {
    %>
            var variblechange=<%=p%>+'#'+<%=j%>;

            if(document.forms[0].type_disability.value==6){
                if(document.getElementById(variblechange)!=null && document.getElementById(variblechange).value=="0"){
                    if(<%=j%>==1){
                        alert("Please Select Camp");
                    }else if(<%=j%>==2){
                        alert("Please Select Disability");
                    }else{
                        alert("Please Select Date");
                    }

                    document.getElementById(variblechange).focus();
                    flag=false;
                    return flag;
                }
            }


    <%}
                }%>
                        return flag;
                    }


                    function checkOtherOptions(presentId,prevalue,column){
    <%

                for (int p = 0; p < noOfrows; p++) {%>
                        if(<%=p%>!=presentId){
                                if(document.getElementById(<%=p%>+"#"+column).value==prevalue)
                                {
                                    if(column==2){
                                        alert("This disability already selected");
                                        document.getElementById(presentId+"#"+column).value="0";
                                        return false;
                                    }

                                        
                               
                            }
                        }


    <%}%>
            //alert(presentId);
        }
        function partaCampIdValue(id){
            document.forms[0].partaCampId=id;
        }
        function getCampDetailsList(id){
            document.forms[0].noOfdisabilities.value="";

            if(id==6){
                // document.getElementById("dynamicRows").style.display = "";
                document.getElementById("multipledid").style.display = "";
                document.getElementById("sigledid").style.display = "none";
                //document.getElementById("multipledidlist").style.display = "none";
            }else{
                document.getElementById("dynamicRows").style.display = "none";
                document.getElementById("sigledid").style.display = "";
                document.getElementById("multipledid").style.display = "none";
    <%request.removeAttribute("noOfRows");%>
                //document.getElementById("multipledidlist").style.display = "none";
            }
        }
        function noOfDisabilitysList(idvalue){
       
            
            var id=document.forms[0].type_disability.value;
            document.forms[0].noOfdisabilities.value=idvalue;
            var flag=true;
            
            if(idvalue<=1 || idvalue>5){
                flag=false;
            }
            if(flag){
                if(id==6){
                    //getMultipleCampDetails(1,2);
                    document.getElementById("multipledid").style.display = "";

                }else{

                    document.getElementById("multipledid").style.display = "none";
                }
            }else{
                // alert("Plese Enter Less Then 5");
                document.forms[0].noOfdisabilities.value="";
                document.forms[0].noOfdisabilities.focus();
                alert("No Of Disabilities count should be greater than 1 and less than 5");
                //document.getElementById("multipledidlist").style.display = "none";
                return false;
            }
            callActionMethod(idvalue);

        }
        function callActionMethod(id){
            document.forms[0].mode.value="setNoOfcampvalue";
            document.forms[0].action="personalDetailsForPersonCode.do?noofrows="+id;
            document.forms[0].submit();
        }
        function returnRowNumber(){
    <%
                for (int p = 0; p < 2; p++) {
                    for (int j = 1; j <= 3; j++) {
    %>

            var flag=1;
            var variblechange=<%=p%>+'#'+<%=j%>;
            if(variblechange==<%=p%>+"#1"){
                if((document.getElementById(2+"#1")!=null && document.getElementById(2+"#1").value!="0")
                    || (document.getElementById(2+"#3")!=null && document.getElementById(2+"#3").value!="0") 
                    || (document.getElementById(2+"#2")!=null && document.getElementById(2+"#2").value!="0")
            ){
                    flag=0;

                }
            }
    <%}
                        if (p == 2 - 1) {
    %>

            if(flag==1){
                return 0;
            }else{
                return null;
            }
    <%                                                        }
                }%>
                    }
                    function checkForm(){
                        // var columnNumber=returnRowNumber();

                        var columnNumber=1;

    <%  for (int j = 0; j <= 2; j++) {
    %>
            var variblechange=columnNumber+'#'+<%=j%>;
            if(document.getElementById(columnNumber+"#1")==null || document.getElementById(columnNumber+"#1").value=="0"){
                alert("Please Select Camp");
                document.getElementById(columnNumber+"#1").focus();
                return false;
            }else  if(document.getElementById(columnNumber+"#3")==null || document.getElementById(columnNumber+"#3").value=="0"){
                alert("Please Select Camp Date");
                document.getElementById(columnNumber+"#3").focus();
                return false;
            }else  if(document.getElementById(columnNumber+"#2")==null || document.getElementById(columnNumber+"#2").value=="0"){
                alert("Please Select Disability");
                document.getElementById(columnNumber+"#2").focus();
                return false;
            }
            else if(variblechange==columnNumber+"#3"){
                if(document.getElementById(variblechange)!=null){




                }
            }



    <% }%>
            return false;
        }


        function checkDisability(){
            var id=document.forms[0].type_disability.value;
            if(id==6){
                //getMultipleCampDetails(1,2);
                document.getElementById("multipledid").style.display = "";
                document.getElementById("dynamicRows").style.display = "";
            }else{
                document.getElementById("dynamicRows").style.display = "none";
                document.getElementById("multipledid").style.display = "none";
            }
        }
        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;

        }
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>
        <title>SADAREM</title>
    </head>
    <body onload="checkDisability();">
        <html:form action="/personalDetailsForPersonCode.do?mode=insertCampScheduleDetails" method="post" onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');document.getElementById('subButton').disabled = true;" >
            <input type="hidden" name="noofrowvalue" value="<%=noOfrows%>"/>
            <input type="hidden" name="mode" />
            <input type="hidden" name="personcode" value="<%=personcode%>"/>
            <input type="hidden" name="category" value="<%=result%>"/>
            <table  align="center" cellspacing="0" cellpadding="0" class="inputform"  border="0">


                <logic:present name="result" >

                    <logic:equal name="result" value="1">
                        <tr>
                            <th colspan="4"  >  <font style="text-align: center">  Camp schedule for Part B Entry</font>
                            </th>

                        </tr>

                    </logic:equal>
                    <logic:equal name="result" value="2">
                        <tr>
                            <th colspan="4" > <font style="text-align: center">  Camp schedule for Reassessment</font></th>

                        </tr>

                    </logic:equal>
                    <logic:equal name="result" value="3">
                        <tr>
                            <th colspan="4"  > <%=personcode%> <font style="text-align: center"> Camp schedule for Temporary Certificate </font></th>

                        </tr>

                    </logic:equal>
                </logic:present>


                <tr>

                    <td >SADAREM ID</td>
                    <td colspan="3"><%=personcode%> </td>
                </tr>
                <tr>

                    <td >Phone No<font color="red" ><b>*</b></font></td>
                    <td colspan="3"><html:text  property="phoneno"  maxlength="10" onkeypress="return onlyNumbers();" style="width:200px"></html:text></td>
                </tr>

                <tr>

                    <td> Type of Disability<font color="red" ><b>*</b></font></td>
                    <td colspan="3">
                        <html:select property="type_disability" name="partAForm" style="width:200px"
                                     onchange="getCampDetailsList(this.value),createCampObject();">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Locomotor/OH</html:option>
                            <html:option value="2">Visual Impairment</html:option>
                            <html:option value="3">Hearing Impairment</html:option>
                            <html:option value="4">Mental Retardation</html:option>
                            <html:option value="5">Mental Illness</html:option>
                            <html:option value="6">Multiple Disabilities</html:option>
                        </html:select>
                    </td>
                <tr style="display: none"  id="multipledid" align="center">
                    <td>No Of Disabilities<font color="red" ><b>*</b></font></td>
                    <td  colspan="3" align="center">
                        <html:text property="noOfdisabilities" onkeyup="return noOfDisabilitysList(this.value);"  maxlength="1" onkeypress="return onlyNumbers();"/>
                    </td>
                </tr>

                <tr style="display: none"  id="sigledid">
                    <td>Camp<font color="red" ><b>*</b></font></td>
                    <td >
                        <html:select property="partaCampId" onchange="createCampDateObject();" style="width:300px">
                            <html:option value="0">--SELECT--</html:option>
                        </html:select>
                    </td>
                    <td>Camp Date<font color="red" ><b>*</b></font></td>
                    <td >
                        <html:select property="partaCampDate" >
                            <html:option value="0">--SELECT--</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr >
                    <td colspan="4" id="dynamicRows" style="display: none;">
                        <table>

                            <%
                                        for (int j = 0; j < noOfrows; j++) {
                            %>
                            <html:hidden property="dynaProperty(noOfRows)" name="partAForm" styleId="noOfRows" />
                            <tr>
                                <td>Camp <font color="red" ><b>*</b></font></td>  <td  >
                                    <%
                                                                                propertyName = "dynaProperty(" + j + "#1)";
                                                                                styleId = j + "#1";
                                    %>

                                    <select name="<%=propertyName%>" id="<%=styleId%>"  onchange="getMultipleCampDatesDetails(<%=j%>,<%=j%>,this.value);" style="width: 300px">
                                        <option value="0">-- SELECT CAMP --</option>
                                        <logic:iterate id="man" name="campsList" >
                                            <option value="${man.campid}">${man.campname}</option>
                                        </logic:iterate>
                                    </select>



                                </td>
                                <td >Date <font color="red" ><b>*</b></font></td>  <td >
                                    <%
                                                                                propertyName = "dynaProperty(" + j + "#3)";
                                                                                styleId = j + "#3";
                                    %>

                                    <div id="MULTIPLECAMPDATEDIV<%=j%>"></div>
                                </td>
                                <td>Disabilitys<font color="red" ><b>*</b></font></td>
                                <td>
                                    <%
                                                                                propertyName = "dynaProperty(" + j + "#2)";
                                                                                styleId = j + "#2";
                                    %>
                                    <div id="MULTIPLEDISABILITIESDIV<%=j%>"></div>
                                </td>



                            </tr>

                            <% }

                            %>


                        </table>
                    </td>


                </tr>

                <tr>
                    <th align="center" colspan="4">
                        <html:submit value="Submit" styleClass="button"  styleId="subButton" onclick="return checkValidations()" />&nbsp;&nbsp;
                        <html:reset value="Reset" styleClass="button"/>
                    </th>
                </tr>
            </table>

        </html:form>
    </body>
</html>
