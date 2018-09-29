<%-- 
    Document   : aadharCardWebDetails
    Created on : Oct 9, 2014, 10:14:14 AM
    Author     : 310926
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <%
                    String aadharCardNo = "";
                    if (request.getAttribute("aadharCardNo") != null) {
                        aadharCardNo = request.getAttribute("aadharCardNo").toString();
                    }
                    String surname = "";
                    if (request.getAttribute("surname") != null) {
                        surname = request.getAttribute("surname").toString();
                    }
        %>
        <script>
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("option");
                opt.text=result1;
                opt.value=result2;
                try {
                    document.getElementById(name).add(opt);
                }catch(ex)
                {
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }
                }
            }
            function removeall(name)
            {
                if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }else if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }else if(name=="panchayat_id") {
                    var x1=document.forms[0].panchayat_id.options.length;
                }
                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    } else if(name=="habitation_id") {
                        document.forms[0].habitation_id.options[i]=null;
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.options[i]=null;
                    }
                }
            }

            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }


            var x = null;
            function  createPanchayatObject()
            {
                x = GetXmlHttpObject()
                x.onreadystatechange=getPanchayatDetails;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&territory=4";

                x.open("GET",url,true)
                x.send();
            }

            function getPanchayatDetails()
            {
                var rs1,rs2;
                removeall("panchayat_id");
                removeall("habitation_id");
                removeall("village_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"panchayat_id");
                        z++;
                    }
                }
            }

            function panchayatList(){
                var villageid = document.forms[0].panchayat_id.value;
                var slcBx = document.getElementById("3");
                document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(6,6);
                }else{
                    createHabitationObject();
                }
            }

            function  createVillageObject()
            {

                //var distid;

                x=GetXmlHttpObject()

                x.onreadystatechange=getVillageDetails;

                var pan=document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&panchayatid="+pan+"&territory=7";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
                // removeall("panchayat_id");
                removeall("habitation_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                }
            }

            function villagesList(){
                var villageid = document.forms[0].village_id.value;
                var slcBx = document.getElementById("4");
                document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(5,6);
                }else{
                    createPanchayatObject();
                }
            }






            function  createHabitationObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;

                var villageid = document.forms[0].village_id.value;
                var panchayat = document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=8";

                x.open("GET",url,true)
                x.send();
            }

            function getHabitationDetails()
            {
                var rs1,rs2;
                removeall("habitation_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"habitation_id");
                        z++;
                    }
                }
            }

            function submitBack(){

                document.getElementById('subButtonBack').disabled = true;
                document.forms[0].action="aadharCardDetails.do";
                document.forms[0].submit();

            }

            function submitButtons(){
                
                if(document.forms[0].surname.value==null || document.forms[0].surname.value==""){
                    alert("Please Enter  Surname");
                    document.forms[0].surname.focus();
                    return false;
                } else if(document.forms[0].firstname.value==null || document.forms[0].firstname.value==""){
                    alert("Please Enter  Firstname");
                    document.forms[0].firstname.focus();
                    return false;
                }
                else if(document.forms[0].mandal_id.value=="0"){
                    alert("Please Select Mandal");
                    document.forms[0].mandal_id.focus();
                    return false;
                } else if(document.forms[0].panchayat_id.value=="0"){
                    alert("Please Select Panchayat");
                    document.forms[0].panchayat_id.focus();
                    return false;
                }else if(document.forms[0].village_id.value=="0"){
                    alert("Please Select Town/Village");
                    document.forms[0].village_id.focus();
                    return false;
                }else if(document.forms[0].habitation_id.value=="0"){
                    alert("Please Select Habitation/Ward No");
                    document.forms[0].habitation_id.focus();
                    return false;
                }else{
                    document.getElementById('subButton').disabled = true;
                    document.forms[0].action="insertAadharCardDetails.do?mode=partAForm";
                    document.forms[0].submit();
                    return true;

                }
            }

            function submitDetails(){
                var checkCount = 0;
                if((document.getElementById("checkYes").checked) || (document.getElementById("checkNo").checked)){
                    if(document.getElementById("checkYes").checked){
                        var sCheck = document.getElementsByName("tagSadarem");
                        for(var i=0;i<sCheck.length;i++){
                            if(sCheck[i].checked){
                                checkCount++;
                            }
                        }
                        if(checkCount==0){
                            alert("Select SADAREM ID to which AadharCard No to be Tagged ");
                            return false;
                        }else{
                            document.getElementById('subButton').disabled = true;
                            document.forms[0].action="insertAadharCardDetails.do?mode=taggedAadharCardNo";
                            document.forms[0].submit();
                            return true;
                        }
                    }else if(document.getElementById("checkNo").checked){
                        document.getElementById('subButton').disabled = true;
                        document.forms[0].action="insertAadharCardDetails.do?mode=partAForm";
                        document.forms[0].submit();
                        return true;
                    }
                }else{
                    alert("Please Select AadharCard Number Person Details found in the below SADAREM Assessed lis");
                    document.forms[0].isExist.focus();
                    return false;

            <%--}else{
                document.getElementById('subButton').disabled = true;
                document.forms[0].action="insertAadharCardDetails.do?mode=getSADAREMAssessedList";
                document.forms[0].submit();
                return true;--%>

                        }
                    }
                    

                    <!-- script for captal at first letter-->
                    function capitalizeMe(obj) {
                        var val = obj.value;
                        var newVal = '';
                        if(val != ""){
                            val = val.split(' ');
                            for(var c=0; c < val.length; c++) {
                                newVal += val[c].substring(0,1).toUpperCase() +
                                    val[c].substring(1,val[c].length) + ' ';
                            }
                        }
                        obj.value = newVal;
                    }
                    function isAlpha(keyCode,name)
                    {
                        if (keyCode == 16)
                            isShift = true;
                        var str = name.value;
                        if(str.substring(0,1)==" ")
                        {
                            name.value ="";
                            name.focus();
                            return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                        }else{
                            return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                        }
                    }
                    function space(evt,thisvalue)
                    {
                        var number = thisvalue.value;
                        var charCode = (evt.which) ? evt.which : event.keyCode
                        if(number.length < 1){
                            if(evt.keyCode == 32) {

                                return false;
                            }
                        }
                        return true;
                    }

        </script>

    </head>
    <body>
        <html:form  action="/insertAadharCardDetails?mode=getSADAREMAssessedList"  enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="district_id"/>
            <html:hidden property="houseno"/>
            <html:hidden property="gsurname"/>
            <html:hidden property="gender"/>
            <html:hidden property="base64Photo"/>

            <table align="center" border="0" cellpadding="0" cellspacing="1" width="90%"  class="inputform">
                <tr>
                    <th  colspan="4" >AadharCard Number Details</th>
                </tr>
                <tr>
                    <td  colspan="4"><b>Personal Details</b></td>
                </tr>
                <tr>
                    <td >AadharCard Number<font color="red"></font></td>
                    <td colspan="3">
                        <html:text property="aadharCardNo"  style="width:160px"  readonly="true"/>
                    </td>
                </tr>

                <logic:notPresent name="aadharDetailsFlag">


                    <tr>

                        <td >Surname<font color="red"></font></td>
                        <td >
                            <%if (surname.equalsIgnoreCase("")) {%>
                            <html:text property="surname" name="partAForm" maxlength="25" onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                       onchange="capitalizeMe(this)" style="width:160px"/>
                            <%} else {%>
                            <html:text property="surname"  style="width:160px" readonly="true" />
                            <% }%>
                        </td>
                        <td >
                            Name<font color="red"></font>
                        </td>
                        <td >
                            <html:text property="firstname"  style="width:160px"  readonly="true"/>
                        </td>
                    </tr>

                </logic:notPresent>
                <logic:present name="aadharDetailsFlag">

                    <tr>

                        <td >Surname<font color="red"></font></td>
                        <td ><%if (surname.equalsIgnoreCase("")) {%>
                            <html:text property="surname" name="partAForm" maxlength="25" onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                       onchange="capitalizeMe(this)" style="width:160px"/>
                            <%} else {%>
                            <html:text property="surname"  style="width:160px" readonly="true" />
                            <% }%>
                        </td>
                        <td >
                            Name<font color="red"></font>
                        </td>
                        <td >
                            <html:text property="firstname"  style="width:160px"/>
                        </td>
                    </tr>
                </logic:present>



                <tr>
                    <td  colspan="4"><b>Address:</b></td>
                </tr>
                <tr>
                    <td>District<font color="red"></font></td>
                    <td>
                        <html:text property="district"  style="width:160px"  readonly="true"/>

                    </td>

                    <td>Mandal<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select styleId="3" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                            <html:option  value="0">- - SELECT - -</html:option>
                            <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                        </html:select>
                    </td>

                </tr>
                <tr>
                    <td >Panchayat<font color="red"><b>*</b></font> </td>
                    <td >
                        <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                            <html:option  value="0">- - SELECT - -</html:option>
                            <html:optionsCollection property="panchayatlist" label="panchayat_name" value="panchayat_id"  />
                        </html:select>
                    </td>

                    <td >Town/Village<font color="red"><b>*</b></font> </td>
                    <td>
                        <html:select property="village_id" style="width:160px;" onchange="createHabitationObject();">
                            <html:option value="0">- - SELECT - -</html:option>
                            <html:optionsCollection property="villagelist" label="village_name" value="village_id" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td >Habitation/Ward No<font color="red"><b>*</b></font> </td>
                    <td  >
                        <html:select styleId="6" property="habitation_id" style="width:160px;">
                            <html:option  value="0">- - SELECT - -</html:option>
                            <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />
                        </html:select>
                    </td>
                    <td colspan="2">
                        &nbsp;

                    </td>
                </tr>
                <tr>
                    <th align="center" colspan="4">
                        <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitButtons();" />
                        <input type="button"  value="Back" Id="subButtonBack" styleClass="button"  onclick="return submitBack();" />

                    </th>
                </tr>
            </table>
            <% int i = 1;%>
            <logic:present name="sadaremAssessedList">
                <table align="center" border="0" cellpadding="0" cellspacing="1" width="90%"  class="inputform" >
                    <br>
                    <tr valign="top">
                        <td >AadharCard Number Person Details found in the below SADAREM Assessed list<font color="red"><b>*</b></font></td>
                        <td ><html:radio  property="isExist" styleId="checkYes" value ="Yes" style="width:15px">Yes</html:radio>
                            <html:radio  property="isExist" value ="No"  styleId="checkNo" style="width:15px">No</html:radio></td>
                    </tr>
                    <tr><td colspan="2">
                            <div style="overflow:auto; width:830px; height:458px; vertical-align: top">
                                <table align="center" border="0" cellpadding="0" cellspacing="1" width="90%"  class="inputform" >
                                    <tr>
                                        <th class="formhdbg" align="center">
                                            S No
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Pension ID
                                        </th>
                                        <th class="formhdbg" align="center">
                                            SADAREM ID
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Name
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Relation Name
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Age
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Qualification
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Type Disabilty
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Disability Percentage
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Contact number
                                        </th>
                                        <th class="formhdbg" align="center">
                                            PWD Status
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Assessment Status
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Pension Phase
                                        </th>
                                        <th class="formhdbg" align="center">
                                            Select
                                        </th>

                                    </tr>
                                    <logic:iterate name="sadaremAssessedList" id="row">

                                        <tr>
                                            <td class="formaltbg" align="center">
                                                <%=i++%> .
                                            </td>
                                            <td class="formaltbg">
                                                ${row.PensionID}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.SADAREMID}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.Name}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.RelationName}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.Age}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.Qualification}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.DisabiltyType}
                                            </td>
                                            <td class="formaltbg" align="center">
                                                ${row.DisabilityPercentage}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.ContactNumber}
                                            </td>
                                            <td class="formaltbg" align="center">
                                                ${row.PWDStatus}
                                            </td>
                                            <td class="formaltbg" align="center">
                                                ${row.AssessmentStatus}
                                            </td>
                                            <td class="formaltbg" align="center">
                                                ${row.PensionPhase}
                                            </td>
                                            <td>

                                                <input type="radio" name="tagSadarem"  id="tag" value="${row.SADAREMID}!<%=aadharCardNo%>"/>
                                            </td>

                                        </tr>


                                    </logic:iterate>
                                </table>
                            </div>
                        </td></tr>


                    <tr>
                        <th align="center" colspan="2" >
                            <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitDetails(); " />


                        </th>
                    </tr>

                </table>


            </logic:present>

            <logic:present name="nodata">
                <table align="center" border="0" cellpadding="0" cellspacing="1" width="90%"  class="inputform" >
                    <br>
                    <tr valign="top">
                        <td >AadharCard Number Person Details found in the below SADAREM Assessed list<font color="red"><b>*</b></font></td>
                        <td ><html:radio  property="isExist" styleId="checkYes" value ="Yes" style="width:15px">Yes</html:radio>
                            <html:radio  property="isExist" value ="No"  styleId="checkNo" style="width:15px" >No</html:radio></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            No Assessment List done in the selected Habitation
                        </td>
                    </tr>


                    <tr>
                        <th align="center" colspan="2" >
                            <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitDetails(); " />


                        </th>
                    </tr>

                </table>


            </logic:present>
        </html:form>
    </body>
</html>
