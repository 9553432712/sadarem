<%-- 
    Document   : insertAadharCardPersonalDetails
    Created on : Oct 9, 2014, 4:14:02 PM
    Author     : 310926
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>
<% String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
            //String hospitalname= (String)request.getAttribute("HospitalName");
            int noOfrows1 = 0;
            if (request.getAttribute("noOfRows") != null) {
                noOfrows1 = Integer.parseInt(request.getAttribute("noOfRows").toString());
            }

            int noOfrows = 0;
            if (request.getAttribute("noOfRows") != null) {
                noOfrows = Integer.parseInt(request.getAttribute("noOfRows").toString());
            }
            String propertyName = null, styleId = null;
            String restrictPartA = (String) request.getParameter("restrictPartA");
            if (restrictPartA == null || "".equals(restrictPartA)) {
                restrictPartA = (String) session.getAttribute("restrictPartA");
            }
            String district_id = (String) request.getAttribute("district_id");
            String base64Photo = (String) request.getAttribute("base64Photo");
            String surname = "";
            if (request.getAttribute("surname") != null) {
                surname = request.getAttribute("surname").toString();
            }

            String relationname = "";
            if (request.getAttribute("relationname") != null) {
                relationname = request.getAttribute("relationname").toString();
            }

%>
<style type="text/css">
    #fifteenth{position: absolute;width: 150px;border: 1px solid gray;padding: 2px;visibility: hidden;z-index: 99;}
</style>
<div id="fifteenth"></div>
<html:html>
    <head>

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumberAadharCard.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script>

        <script>

            function decisiontaking(desitionTaking) {
                alert(desitionTaking)
                //  document.forms[0].getelementbyid.value=desitionTaking;
                document.forms[0].townVillage.value=desitionTaking;
                document.forms[0].action="details.do";
                document.forms[0].submit();


            }
            function rationType(){
                var rationCardNumber = document.getElementById("card");
                var cardnumber = null;
                var cardnumberthree = null;  
                if(rationCardNumber != null){
                    var rationCardNumberValue =rationCardNumber.value;
                    if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3){
                        cardnumber = rationCardNumberValue.toString().toUpperCase();
                        rationCardNumber.value=cardnumber;
                        document.getElementById("rtype").selectedIndex = "";
                        document.getElementById("rtype").disabled = true;
                    }
                    if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3)){
                        cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
                        document.getElementById("rtype").disabled = false;
                        if(cardnumber != null || cardnumberthree != null){
                            if(cardnumber == "WAP" || cardnumberthree == "WAP"){
                                document.getElementById("rtype").selectedIndex = 1;
                            }else if(cardnumber == "PAP" || cardnumberthree == "PAP"){
                                document.getElementById("rtype").selectedIndex = 2;
                            }else if(cardnumber == "AAY" || cardnumberthree == "AAY"){
                                document.getElementById("rtype").selectedIndex = 3;
                            }else if(cardnumber == "AAP" || cardnumberthree == "AAP"){
                                document.getElementById("rtype").selectedIndex = 4;
                            }else if(cardnumber == "YAP" || cardnumberthree == "YAP"){
                                document.getElementById("rtype").selectedIndex = 5;
                            }else if(cardnumber == "TAP" || cardnumberthree == "TAP"){
                                document.getElementById("rtype").selectedIndex = 6;
                            }else if(cardnumber == "RAP" || cardnumberthree == "RAP"){
                                document.getElementById("rtype").selectedIndex = 7;
                            }
                            else if(cardnumber == "WAD" || cardnumberthree == "WAD"){
                                document.getElementById("rtype").selectedIndex = 8;
                            }
                        }
                    }else if(rationCardNumberValue == ""){
                        document.getElementById("rtype").selectedIndex = "";
                        document.getElementById("rtype").disabled = true;
                    }
                }
            }


            <!-------Starts script Allow Alphabets and Numerics For RationCardNumber-------------------->


            function isAlphaNumericRationCard(keyCode,name) {
                if (keyCode == 16 )
                    isShift = true;
                var str = name.value;
                if(str.substring(0,1)=="" || str.substring(0,1)=="W" || str.substring(0,1)=="w" ||
                    str.substring(0,1)=="A" || str.substring(0,1)=="a" ||
                    str.substring(0,1)=="P" || str.substring(0,1)=="p" ||
                    str.substring(0,1)=="Y" ||  str.substring(0,1)=="y")
                {
                    var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
                        keyCode == 87 || keyCode == 65 || keyCode == 80 || keyCode == 89 || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                }else{
                    name.value="";
                    name.focus();
                    var res = (keyCode == 8);

                }
                return res;
            }

            function modelesswin(url,mwidth,mheight){
                if (document.all&&window.print) //if ie5
                    eval('window.showModelessDialog(url,"devi","help:0;resizable:1;dialogWidth:'+mwidth+'px;dialogHeight:'+mheight+'px")')
                else
                    eval('window.open(url,"devi","width='+mwidth+'px,height='+mheight+'px,resizable=0,scrollbars=0")')
            }

            <!-- Numbers allow only 0 to 100 --> 
           
            function isNumber100(field) {
                var re = /^[0-9]*$/;
                if (!re.test(field.value)) {
                    alert('Value must be an integer number!')
                    field.value = field.value.replace(/D/g,"");
                    field.value ='';
                    field.focus();
                }
                if(Number(field.value)>100){
                    alert('Value must be between 0 and 100!')
                    field.value ='';
                    field.focus();
                }
            }

           

            function getPersonCodeList(){
                var personstatus = document.getElementById("7").value;
                var slcBx = document.getElementById("6");
                document.getElementById("habitation_name").value = slcBx.options[slcBx.selectedIndex].text;
                if(personstatus == 0 || personstatus == ""){
                    removeLists(8,8);
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
                            document.getElementById("multipledid").style.display = "";
                        }else{
                            document.getElementById("multipledid").style.display = "none";
                        }
                    }else{
                        document.forms[0].noOfdisabilities.value="";
                        document.forms[0].noOfdisabilities.focus();
                        alert("No Of Disabilities count should be greater than 1 and less than 5");
                        return false;
                    }
                    callActionMethod(idvalue);
                }
                function callActionMethod(id){


                    document.forms[0].mode.value="partAForm";
                    document.forms[0].action="insertAadharCardDetails.do?noofrows="+id;
                    document.forms[0].submit();
                }

        </script>
        <script>
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



                var flag=true;
                function checkSelectingMultiple(){


            <%

                        for (int p = 0; p < noOfrows1; p++) {
                            for (int j = 1; j <= 3; j++) {
            %>
                    var variblechange=<%=p%>+'#'+<%=j%>;


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


            <%}
                        }%>
                                return flag;
                            }


                            function checkOtherOptions(presentId,prevalue,column){
                                //alert(prevalue);
            <%

                        for (int p = 0; p < noOfrows1; p++) {%>
                                if(<%=p%>!=presentId){
                                    if(document.getElementById(<%=p%>+"#"+column)!=null && document.getElementById(<%=p%>+"#"+column).value==prevalue){
                                        if(column==2){
                                            alert("This Disability Already Selected");
                                        }
                                        document.getElementById(presentId+"#"+column).value="0";
                                        return false;
                                    }
                                }


            <%}%>
                    //alert(presentId);
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
            <%
                        if (request.getAttribute("mandal_id") != null) {
            %>
                    document.getElementById('7').value=<%=request.getAttribute("mandal_id")%>;
            <%
                        }

                        if (request.getAttribute("village_id") != null) {
            %>

                    document.forms[0].village_id.value=<%=request.getAttribute("village_id")%>;
                    document.getElementById('99').value=<%=request.getAttribute("village_id")%>;

            <%
                        }
                        if (request.getAttribute("habitation_id") != null) {
            %>

                    document.forms[0].habitation_id.value=<%=request.getAttribute("habitation_id")%>;
                    document.getElementById('6').value=<%=request.getAttribute("habitation_id")%>;

            <%
                        }
            %>
                }
                function setpartacampid(id){
                    document.forms.partaCampIds=id;

                }
        </script>
    </head>
    <body onload="document.partAForm.formno.focus(),currentdate(),validatepensioncheckbox(),validateepiccheckbox(),generateDob(),rationType(),checkDisability();" >
        <html:form action="/insertAadharCardDetails.do?mode=insertPersonalDetails"
                   styleId="partAForm" onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');document.getElementById('subButton').disabled = true;">
            <html:hidden  property="mode" />
            <html:hidden  property="parameter" />
            <input type="hidden" name="noofrowvalue" value="<%=noOfrows%>"/>
            <html:hidden  property="panchayatiid" />
            <html:hidden  property="assemblyid" />
            <input type="hidden" name="district_id" value="<%=(String) request.getAttribute("districtId")%>"/>
            <input type="hidden" name="restrictPartA" value="<%=restrictPartA%>"/>
            <input type="hidden" name="noofrowvalue" value="<%=noOfrows%>"/>
            <input type="hidden" name="base64Photo" value="<%=base64Photo%>"/>
            <logic:present name="dvaluess">
                <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                    <td> <font color='red' style="text-align: center"size='2'>Select Disabilities Exceeded To Selete Camp</font></td>
                </table>
            </logic:present>
            <table  align="center" cellspacing="1" cellpadding=""  width="90%" border="0" class="inputform">
                <th colspan="4">
                    Enter Personal Data
                </th>
                <html:hidden property="pensionPhase" name="partAForm"/>
                <tr>
                    <td>Form No<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="formno"  name="partAForm" maxlength="25"
                                   onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"
                                   /></td>
                    <td>Date of Assessment<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="fromdate"  readonly="true"  name="partAForm"  />
                        <a  href="javascript:show_calendar('forms[0].fromdate');" onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>

                <tr>
                    <td colspan="4">1.0 Individual Details</td>
                </tr>
                <tr>
                    <td colspan="4">1.1  Name of the Person</td>
                </tr>
                <tr>
                    <td >Surname<font color="red"><b>*</b></font></td>
                    <td>
                        <%if (surname.equalsIgnoreCase("")) {%>
                        <html:text property="surname" name="partAForm" maxlength="25" onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)" style="width:200px"/>
                        <%} else {%>
                        <html:text property="surname" name="partAForm"  style="width:200px"  readonly="true"/>
                        <% }%>
                    </td>
                    <td>
                        Name<font color="red"><b>*</b></font>
                    </td>
                    <td>
                        <html:text property="firstname" name="partAForm" maxlength="25" onkeydown="return isAlpha(event.keyCode,firstname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)" readonly="true" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td><b><font color="red"><%=personname%></font></b></td>
                    <td>
                        <input type="text"   onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()"
                               name="surnameenglish" maxlength="30" onkeydown="return isAlpha(event.keyCode,surnameenglish);" onkeypress="return space(event,this)"
                               onmouseover="messagedisplay(2,'surnameenglish')" style="width:200px"/>
                    </td>
                    <td><b><font color="red"><%=telugu%></font></b></td>
                    <td>
                        <html:text  property="surnametelugu"  readonly="true" style="width:200px"/>
                        <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" id="telugu" name="telugupersonname" />
                    </td>
                </tr>
                <tr>
                    <td>1.2 Age<font color="red"><b>*</b></font></td>
                    <td colspan="3">Years &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text
                            property="noOfYears"  maxlength="2"
                            onkeydown="return isNumeric(event.keyCode);"
                            onkeyup="generateDob(this);" onkeypress="return space(event,this)"  style='width:50px' />
                    </td>
                </tr>
                <tr>
                    <td>Date of Birth<font color="red"><b>*</b></font></td>
                    <td colspan="3">
                        Day:<html:select  property="day" style='width:80px' >
                            <%
                                        int i = 0;
                                        for (i = 1; i <= 31; i++) {
                            %>
                            <html:option value="<%=String.valueOf(i)%>"><%=i%></html:option>
                            <%
                                        }
                            %>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;Month:
                        <html:select property="month" name="partAForm" style='width:100px'>
                            <html:option value="1">January</html:option>
                            <html:option value="2">February</html:option>
                            <html:option value="3">March</html:option>
                            <html:option value="4">April</html:option>
                            <html:option value="5">May</html:option>
                            <html:option value="6">June</html:option>
                            <html:option value="7">July</html:option>
                            <html:option value="8">August</html:option>
                            <html:option value="9">September</html:option>
                            <html:option value="10">October</html:option>
                            <html:option value="11">November</html:option>
                            <html:option value="12">December</html:option>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;Year:
                        <html:select property="year" styleId="year" onchange="calculateAge(this)"  style='width:100px'>
                            <% for (int j = 2020; j > 1850; j--) {
                            %>
                            <html:option value="<%= String.valueOf(j)%>"><%= j%></html:option>
                            <%
                                        }
                            %>
                        </html:select>

                    </td>
                </tr>
                <tr>
                    <td>1.3 Gender<font color="red"><b>*</b></font></td>
                    <td > 
                        <html:select property="gender" styleId="gender" name="partAForm" onchange="changeothercombo(this.options.selectedIndex)">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Male</html:option>
                            <html:option value="2">Female</html:option>
                        </html:select>
                    </td>
                    <td>1.4 Education</td>
                    <td>
                        <html:select property="education"  name="partAForm" >
                            <html:option value="0">--SELECT--</html:option> 
                            <html:option value="1">Illiterate</html:option>
                            <html:option value="2">Below 10th</html:option>
                            <html:option value="3">10th Class</html:option>
                            <html:option value="4">Intermediate</html:option>
                            <html:option value="5">Diploma/ITI</html:option>
                            <html:option value="6"> Graduate</html:option>
                            <html:option value="7">Post Graduate</html:option>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>1.5 Employment</td>
                    <td>
                        <html:select property="employment" name="partAForm" >
                            <html:option value="0">--SELECT--</html:option>
                            <html:option value="1">Govt</html:option>
                            <html:option value="2">Private</html:option>
                            <html:option value="3">Self-Employed</html:option>
                            <html:option value="4">Un-Employed</html:option>
                            <html:option value="5">Wage-Employee</html:option>
                        </html:select>
                    </td>
                    <td>1.6 Marital Status<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select property="marital" name="partAForm">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Married</html:option>
                            <html:option value="2">Un-Married</html:option>
                            <html:option value="3">Divorcee</html:option>
                            <html:option value="4">Widow</html:option>
                            <html:option value="5">Widower</html:option>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>1.7 Caste</td>
                    <td>
                        <html:select property="caste" name="partAForm">
                            <html:option value="6">--SELECT--</html:option>
                            <html:option value="1">ST</html:option>
                            <html:option value="2">SC</html:option>
                            <html:option value="3">BC</html:option>
                            <html:option value="4">OC</html:option>
                            <html:option value="5">Minority</html:option>
                            <html:option value="6">NA</html:option>
                        </html:select>
                    </td>
                    <td>1.8 Religion</td>
                    <td>
                        <html:select property="religion" name="partAForm" >
                            <html:option value="7">--SELECT--</html:option>
                            <html:option value="1">Hindu</html:option>
                            <html:option value="2">Muslim</html:option>
                            <html:option value="3">Christian</html:option>
                            <html:option value="4">Sikh</html:option>
                            <html:option value="5">Jain</html:option>
                            <html:option value="6">Budhist</html:option>
                            <html:option value="7">Others</html:option>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>1.9 Ration Card No</td>
                    <td>

                        <% if (request.getAttribute("RationReadOnly") != null) {
                        %>
                        <html:text property="card"  maxlength="20"
                                   onkeyup="rationType()" onkeypress="return space(event,this)" readonly="true" style="width:150px"/>
                        <%                             } else {%>
                        <html:text property="card"  maxlength="20"
                                   onkeyup="rationType()" onkeypress="return space(event,this)" style="width:150px"/>
                        <%}%>
                    </td>
                    <td>Type:<html:select property="rtype" name="partAForm"  onchange="rationType()" >
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">White</html:option>
                            <html:option value="2">Pink</html:option>
                            <html:option value="3">Anthyodaya</html:option>
                            <html:option value="4">Annapurna</html:option>
                            <html:option value="5">Yellow</html:option>
                            <html:option value="6">Temporary</html:option>
                            <html:option value="7">Rachabanda</html:option>
                            <html:option value="8">White</html:option>
                        </html:select>

                    </td>
                    <td>


                        Slno:
                        <% if (request.getAttribute("SlNoReadOnly") != null) {
                        %>
                        <html:text property="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();"  readonly="true" style="width:50px"/>
                        <%                                                    } else {%>
                        <input type="hidden" name="readonly"/>
                        <html:text property="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();" style="width:50px"/>
                        <%}%>

                    </td>
                </tr>
                <%--<tr>
                    <td colspan="4">
                        <table cellspacing="0" cellpadding="0" bgcolor="white" width="100%">--%>
                <tr>
                    <td>1.10 EPIC Card</td>
                    <td >Click if Yes <html:checkbox  property="epiccard" value="true" name="partAForm"  onclick="validateepiccheckbox(this.value)" style="width:25px"/>
                    </td>
                    <td colspan="3">
                        <div id="epic" style="visibility:hidden;display:none">
                            <table  align="center" cellspacing="0" cellpadding="0"  width="100%">
                                <tr>
                                    <td align="center">EPIC Number
                                        <html:text property="epiccardno"  maxlength="20" onkeypress="return letternumber(event)" style="width:150px"/></td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <%--</table>
            </td>
        </tr>--%>
                <%-- <tr>
                     <td colspan="4">
                         <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">--%>
                <tr>
                    <td width="36%">1.11 Pension Card </td>
                    <td >Click if Yes
                        <html:checkbox  property="pensioncard" value="true" name="partAForm"  onclick="validatepensioncheckbox(this.value)" styleId="1" style="width:25px"/>
                    </td>
                    <td width="50%" colspan="3">
                        <div id="pension" style="visibility:hidden;display:none">
                            <table  align="center" cellspacing="0" cellpadding="0" width="100%">
                                <tr>
                                    <td>Pension Type</td>
                                    <td>
                                        <html:select property="pension_type" name="partAForm"  styleId="2">
                                            <html:option value="">--SELECT--</html:option>
                                            <html:option value="Disabled">Disabled</html:option>
                                            <html:option value="Old Age">O.A.P</html:option>
                                            <html:option value="Widow">Widow</html:option>
                                            <html:option value="Weavers">Weavers</html:option>
                                        </html:select>
                                    </td>
                                    <td>Pension Number</td>
                                    <td><html:text property="pensioncardno"  maxlength="7" /></td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <%--</table>
            </td>
        </tr>--%>
                <tr>
                    <td>1.12 Identification Marks<font color="red"><b>*</b></font></td>
                    <td>1)<html:text property="mole1"  name="partAForm" maxlength="50"   onkeydown="return isAlpha(event.keyCode,mole1);" onkeypress="return space(event,this)"  onchange="capitalizeMe(this)" style="width:200px"/>
                    </td>
                    <td colspan="3">2)
                        <html:text property="mole2"  name="partAForm" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole2);" onkeypress="return space(event,this)" onchange="capitalizeMe(this)" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td>1.13 Consanguineous Marriage of Parents</td>
                    <td colspan="4">
                        <html:radio  property="parents_marriage" value ="1" style="width:25px">Yes</html:radio> &nbsp;&nbsp;
                        <html:radio  property="parents_marriage" value ="0" style="width:25px">No</html:radio>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">2.0 Family Details</td>
                </tr>
                <tr>
                    <td>2.1 Father/Mother/Husband/Guardian's Name<font color="red"><b>*</b></font></td>
                    <td >
                        <%if (relationname.equalsIgnoreCase("")) {%>
                        <html:text property="gsurname"  maxlength="30" onkeydown="return isAlpha(event.keyCode,gsurname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)" style="width:200px"/>
                        <%} else {%>
                        <html:text property="gsurname"  style="width:200px"  readonly="true"/>
                        <% }%>
                    </td>
                    <td> Relation type<font color="red"><b>*</b></font> </td>
                  <td>
                        <html:select property="grelation" styleId="grelation"  name="partAForm" >
                           <html:option value="">--SELECT--</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td><font  color="red"><b><%=fathername%></b></font></td>
                    <td><input type="text"   onkeyup="javascript1:first_many_words()"
                               onfocus="javascript1:first_many_words()" name="firstnameenglish" maxlength="30"
                               onkeydown="return isAlpha(event.keyCode,firstnameenglish);" onkeypress="return space(event,this)" style="width:200px"/>
                    </td>
                    <td><font  color="red"><b> <%=telugu%></b></font></td>
                    <td>
                        <html:text property="firstnametelugu"  readonly="true" style="width:200px" style="width:200px"/>
                        <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" id="telugu" name="telugufathername" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4">3.0 Address (As recorded in RATION CARD)</td>
                </tr>


                <%
                            /* String Dirst = (String) request.getParameter("district_name");
                            String Mandal = (String) request.getParameter("mandal_name");*/
                            String vilage = (String) request.getParameter("village_name");
                            String habtation = (String) request.getParameter("habitation_name");
                            String panchayat = (String) request.getParameter("panchayat_name");
                %>
                <tr>

                    <td> House No.</td>
                    <td><html:text  property="houseno" maxlength="15" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,houseno)"  onkeypress="return space(event,this)" style="width:160px"> </html:text></td>
                    <td>District<font color="red"><b>*</b></font></td>
                    <td><html:text  property="district"  readonly="true" style="width:160px"></html:text></td>

                </tr>

                <tr>
                    <td>Mandal<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select styleId="7" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                            <html:option  value="0">ALL</html:option>
                            <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                        </html:select>
                    </td>
                    <td >Panchayat<font color="red"><b>*</b></font> </td>
                    <td>
                        <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                            <html:option  value="0">ALL</html:option>

                            <html:optionsCollection property="panchayatlist" label="panchayat_name" value="panchayat_id"  />

                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td >Town/Village<font color="red"><b>*</b></font> </td>
                    <td>
                        <html:select styleId="99" property="village_id" style="width:160px;" onchange="createHabitationObject();">
                            <html:option value="0">ALL</html:option>

                            <html:optionsCollection property="villagelist" label="village_name" value="village_id" />

                        </html:select>
                    </td>
                    <td>Habitation/Ward No<font color="red"><b>*</b></font> </td>
                    <td>
                        <html:select styleId="6" property="habitation_id" style="width:160px;">
                            <html:option  value="0">ALL</html:option>

                            <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />

                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>Phone No</td>
                    <td><html:text  property="phoneno"  maxlength="15" onkeypress="return onlyNumbers();" style="width:160px;"></html:text></td>
                    <td>E-mail</td>
                    <td><html:text  property="email"  maxlength="50" style="width:160px;"></html:text></td>
                </tr>

                <tr>
                    <td>Pin</td>
                    <td colspan="3"><html:text  property="pin" maxlength="6" onkeypress="return onlyNumbers();" style="width:160px;"></html:text></td>
                </tr>

                <tr>
                    <td>4.0 Type of Disability<font color="red"><b>*</b></font></td>
                    <td  colspan="3" >
                        <html:select property="type_disability" name="partAForm" style="width:160px;"
                                     onchange="getCampDetailsList(this.value),createNewCampObject();">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Locomotor/OH</html:option>
                            <html:option value="2">Visual Impairment</html:option>
                            <html:option value="3">Hearing Impairment</html:option>
                            <html:option value="4">Mental Retardation</html:option>
                            <html:option value="5">Mental Illness</html:option>
                            <html:option value="6">Multiple Disabilities</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr style="display: none"  id="multipledid" align="center">
                    <td>No Of Disabilities<font color="red" ><b>*</b></font></td>
                    <td  colspan="3" align="center">
                        <input type="text" name="noOfdisabilities" value="<%=noOfrows%>" onkeyup="return noOfDisabilitysList(this.value);"  maxlength="1" onkeypress="return onlyNumbers();"/>
                    </td>
                </tr>
                <tr>
                    <td >5.0 Existing Percentage</td>
                    <td colspan="3"><html:text property="existingpercentage" name="partAForm" maxlength="3" onkeydown="return isNumeric(event.keyCode);" onkeyup="isNumber100(this)" style="width:160px"/> %</td>
                </tr>

                <tr>
                    <td >6.0 Aadhar Card No</td>
                    <td>
                        <html:text property="aadharCardNo"  style="width:160px"  readonly="true"/>
                    </td>
                    <td colspan="2">
                        <div id="data"></div>
                        <div id="loadImg"></div>
                    </td>
                </tr>

                <tr>
                    <td>7.0 Person Status<font color="red" ><b>*</b></font></td>
                    <td colspan="3"><input type="radio" name="personstatus"  value="Eligible" checked="true" style="width:25px"/>Eligible
                        <input type="radio"  name="personstatus"  value="Rejected" style="width:25px" disabled/>Rejected</td>  

                </tr>
                <tr style="display: none"  id="sigledid">
                    <td>8.0 Camp<font color="red" ><b>*</b></font></td>
                    <td >
                        <html:select property="partaCampId" onchange="getCampDatesDetails(this.value);" style="width:300px">
                            <html:option value="0">--SELECT--</html:option>


                        </html:select>
                    </td>
                    <td>Camp Date<font color="red" ><b>*</b></font></td>
                    <td >
                        <div id="CAMPDATEDIV"></div>
                    </td>

                </tr>
                <tr >
                    <td colspan="4" id="dynamicRows" style="display: none;">
                        <table>

                            <%
                                        for (int j = 0; j < noOfrows1; j++) {
                            %>
                            <html:hidden property="dynaProperty(noOfRows)" name="partAForm" styleId="noOfRows" />
                            <tr>
                                <td>Camp <font color="red" ><b>*</b></font></td>  <td  >
                                    <%
                                                                                propertyName = "dynaProperty(" + j + "#1)";
                                                                                styleId = j + "#1";
                                    %>

                                    <select style="width:300px" name="<%=propertyName%>" id="<%=styleId%>"  onchange="getMultipleCampDatesDetails(<%=j%>,<%=j%>,this.value);">
                                        <option value="0">-- SELECT--</option>
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
                                <td>Disabilities<font color="red" ><b>*</b></font></td>
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
                    <th colspan="4">
                        <html:submit value="Submit" styleClass="button" styleId="subButton" onclick="return checkFormValidations()" />&nbsp;&nbsp;
                        <html:reset value="Reset" styleClass="button"/>
                    </th>

                </tr>

            </table>
            <%-- </td>
         </tr>
     </table>--%>
            <%-- <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">

            </table>--%>
            <%--<table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">

            </table>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">

            </table>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">

            </table>--%>

            <center>

            </center>
        </html:form>
    </body>
        <script src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>


    <script>
        // The below two methods are for getting the table data

        function validateAadhar(aadharId) {
            if(aadharId.toString().length=="12") {
                document.getElementById("loadImg").innerHTML="<img src=\"./DisabilityUITG/images/ajax-loader.gif\">Aadhar Number Validating Please Wait.....";
                $.ajax({
                    url : "insertPartAaction.do?insertPersonalDetails=validateAadharNumberWithAadharDB",
                    type: "GET",
                    data : "aadharNo="+aadharId,
                    success: function(data, textStatus, jqXHR)
                    {
                        if(data=="success") {
                            //  document.getElementById("subButton").disabled=false;
                            $("#data").html("<font color=green size=2><b>Given Aadhar Number Validated Successfully.</b></font>");//data - response from server
                            document.getElementById("loadImg").innerHTML="";
                        }
                        if(data == "fail") {
                            //  document.getElementById("subButton").disabled=true;
                            $("#data").html("<font color=red size=2><b>Given Aadhar Number is Not Valid Number.</b></font>");//data - response from server
                            document.getElementById("loadImg").innerHTML="";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        alert(errorThrown);
                    }
                });
            }
        }

        
        var groups=document.getElementById("gender").options.length;
        
        
        var group=new Array(groups);
        for (i=0; i<groups; i++)
        	{
        		group[i]=new Array();
        	}

        group[0][0]=new Option("-1","----------Select----------");

        group[1][0]=new Option("-1","----------Select----------");
        group[1][1]=new Option("0","C/o Of");
        group[1][2]=new Option("1","S/o Of");
        group[1][3]=new Option("2","G/o Of");
        group[1][4]=new Option("3","H/o Of");


        group[2][0]=new Option("-1","----------Select----------");
        group[2][1]=new Option("0","C/o Of");
        group[2][2]=new Option("1","D/o Of");
        group[2][3]=new Option("2","G/o Of");
        group[2][4]=new Option("3","W/o Of");
        
        var temp=document.getElementById("grelation");

       // alert(temp.options.length);
        
        function changeothercombo(x)
        {

            for (m=temp.options.length-1;m>0;m--)
            temp.options[m]=null;
            for (i=0;i<group[x].length;i++)
            {
            temp.options[i]=new Option(group[x][i].value,group[x][i].text);
            }
            temp.options[0].selected=true;
            

            /*Setting the relation type of database.*/
            
            if("<bean:write name="partAForm"  property="grelation"/>"!="" && "<bean:write name="partAForm"  property="grelation"/>"!="null" && "<bean:write name="partAForm"  property="grelation"/>"!=null)
            	{
            		document.getElementById("grelation").value="<bean:write name="partAForm"  property="grelation"/>";
            	}
            
        }
        $(document).ready( function()
        		{
        	
       	     var gendpos = document.getElementById("gender").options.selectedIndex;       	     
        	 changeothercombo(gendpos); 
        	
        		});
    </script> 
</html:html>
