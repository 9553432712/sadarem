<%-- 
    Document   : editContactUsDetails
    Created on : 1 Oct, 2014, 3:33:29 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=basePath%>Styles/RR-Style.css" type="text/css" rel="stylesheet" />
        <link href="<%=basePath%>Styles/Left-Menu.css" type="text/css" rel="stylesheet" />

        <script type="text/javascript">
            function insertDetails1() {
                if(document.forms[0].contactUsType.value=="0"){
                    document.forms[0].contactUsType.value="0";
                    document.forms[0].contactUsType.focus();
                    alert("Please Select Type Of Contact");
                    return false;
                }if(document.forms[0].contactUsType.value=="Campincharge" &&  document.forms[0].campId.value=="0"){
                    alert("Please Select Camp");
                    document.forms[0].campId.focus();
                    return false;
                }
                else if(document.forms[0].employeeName.value==""){
                    document.forms[0].employeeName.value="";
                    document.forms[0].employeeName.focus();
                    alert("Please Enter Name");
                    return false;
                }  else if(document.forms[0].designation.value==""){
                    document.forms[0].designation.value="";
                    document.forms[0].designation.focus();
                    alert("Please Enter Designation");
                    return false;
                }  else  if(document.forms[0].empContact.value=="" ){
                    document.forms[0].empContact.value="";
                    document.forms[0].empContact.focus();
                    alert("Please Enter Contact Number");
                    return false;
                } if(document.forms[0].empContact.value.length<10){
                    document.forms[0].empContact.focus();
                    alert("Please Enter Valid Contact Number with 10 digits");
                    return false;
                }
                else  if(document.forms[0].email.value==""){
                    document.forms[0].email.value="";
                    document.forms[0].email.focus();
                    alert("Please Enter Email");
                    return false;
                }

                else{
                    document.forms[0].mode.value="insertContactUsDetails";
                    document.forms[0].submit();
                }

            }
            function updateDetails() {
                if(document.forms[0].contactUsType.value=="0"){
                    document.forms[0].contactUsType.value="0";
                    document.forms[0].contactUsType.focus();
                    alert("Please Select Type Of Contact");
                    return false;
                }if(document.forms[0].contactUsType.value=="Campincharge" &&  document.forms[0].campId.value=="0"){
                    alert("Please Select Camp");
                    document.forms[0].campId.focus();
                    return false;
                }
                else if(document.forms[0].employeeName.value==""){
                    document.forms[0].employeeName.value="";
                    document.forms[0].employeeName.focus();
                    alert("Please Enter Name");
                    return false;
                }  else if(document.forms[0].designation.value==""){
                    document.forms[0].designation.value="";
                    document.forms[0].designation.focus();
                    alert("Please Enter Designation");
                    return false;
                }  else  if(document.forms[0].empContact.value=="" ){
                    document.forms[0].empContact.value="";
                    document.forms[0].empContact.focus();
                    alert("Please Enter Contact Number");
                    return false;
                } if(document.forms[0].empContact.value.length<10){
                    document.forms[0].empContact.focus();
                    alert("Please Enter Valid Contact Number with 10 digits");
                    return false;
                }
                else  if(document.forms[0].email.value==""){
                    document.forms[0].email.value="";
                    document.forms[0].email.focus();
                    alert("Please Enter Email");
                    return false;
                }
                else{
                    document.forms[0].mode.value="updateDetails";
                    document.forms[0].submit();
                }
            }
            function onlyNumbers(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }

            function AllowAlphabet(id){
                if (!document.forms[0].elements[id].value.match(/^[A-Za-z\s]*$/) && document.forms[0].elements[id].value !="")
                {
                    document.forms[0].elements[id].value="";

                    document.forms[0].elements[id].focus();
                    alert("Please Enter only alphabets in text");
                }
            }
            function zeroFunction(ids) {

                var stdCode= document.forms[0].elements[ids].value;
                if(stdCode.substr(0,1)=="0") {
                    alert("First Digit Should not be ZERO");
                    document.forms[0].elements[ids].value="";
                }
            }
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
            function checkEmail() {
                var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                var address = document.forms[0].elements['email'].value;

                if(reg.test(address) == false) {
                    alert('Invalid Email Address');
                    document.forms[0].elements['email'].value="";
                    document.forms[0].elements['email'].focus();
                    return false;
                }

            }
            function zeroFunction(ids) {

                var stdCode= document.forms[0].elements[ids].value;
                if(stdCode.substr(0,1)=="0") {
                    alert("First Digit Should not be ZERO");
                    document.forms[0].elements[ids].value="";
                }
            }

            function disp_confirm(id){

                var row=document.forms[0].rowId.value;
                if(row!=id){
                    var name=confirm("Are You Sure! you want to Delete ?")
                    if (name==true)
                    {

                        document.forms[0].action ="./contactUs.do?mode=inActiveStatus&type=contactUs&tokenCodeChecking=${tokenCode}&tId="+id;
                        document.forms[0].submit();
                        //window.close();
                    } else { }
                }
                else{
                    alert('Cannot Delete the Field that is selected');

                }

            }

            function getCamps(selectedValue){

                document.forms[0].mode.value="editDetails";
                document.forms[0].submit();


            }

            function checkContactsExisting(selectedCamp){
                document.forms[0].mode.value="editDetails";
                document.forms[0].submit();
            }

        </script>

    </head>
    <body >
        <html:form action="/editcontactUs" >
            <html:hidden property="districtId"/>
            <html:hidden property="districtName"/>
            <html:hidden property="mode"/>
            <html:hidden property="rowId"/>
            <logic:present name="msg">
                <font color="green"><center><bean:write name="msg"/></center></font>
            </logic:present>


            <logic:present name="Error">

                <font color="red"><center><bean:write name="Error"/></center></font>
            </logic:present>

            <logic:present name="ExistingMSG">

                <font color="red"><center><bean:write name="ExistingMSG"/></center></font>
            </logic:present>


            <table align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform">
                <tr>
                    <th colspan="8">Update Contact Details </th>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="right">Type Of Contact <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="left" colspan="4">
                        <html:select property="contactUsType" style="height:25px;width=200px" onchange="getCamps(this.value);">
                            <html:option value="0">--Select--</html:option>
                            <html:option value="Campincharge" >Campincharge</html:option>
                            <html:option value="District" >DPM</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="right">Camp <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="left" colspan="4">
                        <html:select property="campId" style="height:25px;width=700px" onchange="checkContactsExisting(this.value);">
                            <html:option value="0">--ALL--</html:option>
                            <html:optionsCollection   property="campList" label="camp_name" value="camp_id"  />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="right">Name<font color="red">*</font></td>
                    <td width="28%" colspan="4" class="formbg2" align="left"><html:text property="employeeName" maxlength="50"  onkeypress="return space(event,this)" onkeyup="AllowAlphabet('employeeName');" onchange="capitalizeMe(this);" style="height:25px;width=200px"/></td>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="right">Designation<font color="red">*</font></td>
                    <td width="28%" colspan="4" class="formbg2" align="left"><html:text property="designation" maxlength="50"  onkeypress="return space(event,this)" onkeyup="AllowAlphabet('employeeName');" onchange="capitalizeMe(this);" style="height:25px;width=200px"/></td>
                </tr>

                <tr>
                    <td width="22%" class="formbg1" align="right">Contact Number<font color="red">*</font></td>
                    <td width="28%" colspan="4" class="formbg2" align="left">
                        <%--  <html:text property="contactCode" style="height:25px;width=70px" onkeypress="return onlyNumbers(event);" maxlength="6"/>--%>
                        <html:text property="empContact" style="height:25px;width=130px" onkeypress="return onlyNumbers(event);" onkeyup="zeroFunction('empContact');" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <td width="22%"  class="formbg1" align="left">Email<font color="red">*</font></td>
                    <td width="28%"  colspan="4" class="formbg2" align="right"><html:text property="email" onkeypress="return space(event,this)" onchange="return checkEmail(this);" style="height:25px;width=200px"/></td>
                </tr>

                <logic:notPresent name="ExistingMSG">
                    <tr >
                        <th colspan="5" align="center" valign="middle" class="formbg2" style="text-align:center;">
                            <html:button property="sub" value="Update" onclick="updateDetails();"/>
                        </th>
                    </tr>
                </logic:notPresent >
            </table><br/>

            <logic:notEmpty name="contactUsList">
                <table align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform">
                    <tr>
                        <th colspan="8">ContactUs Details</th>
                    </tr>
                    <tr>
                        <td width="5%" class="gridhdbg">S No</td>
                        <td width="10%" class="gridhdbg">Name</td>
                        <td width="5%" class="gridhdbg">Type</td>
                        <td width="5%" class="gridhdbg">Contact Number</td>
                        <td width="10%" class="gridhdbg">Email</td>
                        <td width="8%" class="gridhdbg">Edit</td>
                        <td width="8%" class="gridhdbg">Delete</td>
                    </tr>
                    <logic:iterate name="contactUsList" id="row">
                        <tr>
                            <td class="gridbg1" align="center"><%=i++%></td>
                            <td class="gridbg1" style="text-align: left">&nbsp;${row.Name}</td>
                            <td class="gridbg1" style="text-align: left">&nbsp;${row.type}</td>
                            <td class="gridbg1" style="text-align: center">&nbsp;${row.Contact}</td>
                            <td class="gridbg1" style="text-align: left">&nbsp;${row.Email}</td>
                            <td class="gridbg1" align="center"><a href="./contactUs.do?mode=editDetails&type=contactUs&rowId=${row.rowid}"> <img src="<%=basePath%>DisabilityUI/images/Edit.jpg" border="0" height="25px" width="25px" alt="Edit"/></a></td>
                            <td class="gridbg1" align="center">
                                <%--   <a href="./contactUs.do?mode=deleteTahasildar&type=contactUs&tId=${row.rowid}">Delete</a>--%>

                                <a href="#"  style="text-decoration: none;border: 0px; border-left: 2px solid grey">&nbsp;
                                    <img src="<%=basePath%>DisabilityUI/images/delete.jpg" alt="Delete" border="0" onclick="disp_confirm('${row.rowid}');" height="25px" width="25px" /></a>
                            </td>

                        </tr>
                    </logic:iterate>
                </table>

            </logic:notEmpty>

            <!-- Body Ends -->
        </html:form>
        <%if (request.getParameter("rowId") != null) {%>
        <input type="hidden" name="row" value="<%=request.getParameter("rowId")%>" id="rowValue">
        <script>
            document.getElementById("hide"+document.getElementById("rowValue").value).disabled=true;
        </script>

        <%}%>
    </body>
</html>