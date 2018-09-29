<%-- 
    Document   : MesevaNewCertificatePersonalDetailsWithPensionNo
    Created on : Jan 17, 2014, 3:36:43 PM
    Author     : 310926
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%
            String district_id = (String) request.getAttribute("district_id");
            String mandal_id = (String) request.getAttribute("mandal_id");
            String village_id = (String) request.getAttribute("village_id");
            String habitation_id = (String) request.getAttribute("habitation_id");
            String panchayat_id = (String) request.getAttribute("panchayat_id");
            String amt = "25";
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
      
        <script>
            var isShift=false;

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


            <!-------Ends script For Allow Alphabets Only -------------------->




            <!-------Starts script Allow Alphabets and Numerics -------------------->


            function isAlphaNumeric(keyCode) {
                if (keyCode == 16)
                    isShift = true;
                var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                    (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                    || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                return res;
            }

            <!-------Ends script Allow Alphabets and Numerics -------------------->
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }<!-------Starts script Allow Alphabets and Numerics For House Number-------------------->


            function isAlphaNumericHouseNumber(keyCode,name) {

                if (keyCode == 16 )
                    isShift = true;
                var str = name.value;
                if(str.substring(0,1)==" " || str.substring(0,1)==0)
                {
                    name.value ="";
                    name.focus();
                    var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                }else{

                    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 ||  keyCode == 32 || keyCode == 46
                        || keyCode == 189 || keyCode == 191);
                }
                return res;
            }


            function alphavalidation(val)
            {
                var i;
                var valid="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

                if(val!=null)

                {
                    for(i=0;i<val.length;i++)
                    {

                        if(valid.indexOf(val.charAt(i))<0)
                        {

                            alert("Please enter Characters And Numerics only");
                            document.forms[0].proofId.value="";
                            document.forms[0].proofId.focus();
                            break;
                        }

                    }
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

            
            function getDetails(){
                if( document.forms[0].requestNumber.value==null ||document.forms[0].requestNumber.value=="" ){
                    alert("Please Enter Request Number");
                    document.forms[0].requestNumber.focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getRdcallcentreRequestSearch";
                    document.forms[0].submit();
                    return true;
                }
            }
            function submitButtons(){
               
                if(document.forms[0].surname.value==null || document.forms[0].surname.value==""){
                    alert("Please Enter Surname");
                    document.forms[0].surname.focus();
                    return false;
                }else if(document.forms[0].firstname.value==null || document.forms[0].firstname.value==""){
                    alert("Please Enter Name");
                    document.forms[0].firstname.focus();
                    return false;
                }else if(document.forms[0].relationName.value==null || document.forms[0].relationName.value==""){
                    alert("Please Enter Father/ Mother/ Husband/ Guardian's Name");
                    document.forms[0].relationName.focus();
                    return false;
                }else if(document.forms[0].relationType.value==null || document.forms[0].relationType.value==""){
                    alert("Please Select Relation");
                    document.forms[0].relationType.focus();
                    return false;
                }else if(document.forms[0].age.value==null || document.forms[0].age.value==""){
                    alert("Please Enter Age");
                    document.forms[0].age.focus();
                    return false;
                }else if(document.forms[0].gender.value==null || document.forms[0].gender.value==""){
                    alert("Please Select Gender");
                    document.forms[0].gender.focus();
                    return false;
                }else if(document.forms[0].phoneno.value==null || document.forms[0].phoneno.value==""){
                    alert("Please Enter Phone No");
                    document.forms[0].phoneno.focus();
                    return false;
                }else{
                    var flag=true;
                    if(document.forms[0].phoneno.value!=null && document.forms[0].phoneno.value!="") {
                        var no=document.forms[0].phoneno.value;
                        if(no.length<10){
                            alert("Please Enter Valid  Phone No");
                            document.forms[0].phoneno.value="";
                            document.forms[0].phoneno.focus();
                            flag = false;
                        }
                    }
                    if(flag){
                        document.getElementById('subButton').disabled = true;
                        document.forms[0].action="mesevaNewCertificateRegistration.do?mode=submitMesevaNewCertificateDetails&flag=with";
                        document.forms[0].submit();
                        return true;
                    }
                }
                
            }
            function submitBack(){
                document.getElementById('subButtonBack').disabled = true;
                document.forms[0].action="mesevaNewCertificateRegistration.do?mode=submitBackPersonalDetails&enc="+document.forms[0].encryptedString.value;
                document.forms[0].submit();
               
            }
        </script>

    </head>
    <body >
    

    <html:form  action="/mesevaNewCertificateRegistration"  enctype="multipart/form-data">
        <html:hidden property="mode"/>
        <html:hidden property="SCAUserId"/>
        <html:hidden property="uniqueNo"/>
        <html:hidden property="loginId"/>
        <html:hidden property="channelId"/>
        <html:hidden property="serviceid"/>
        <html:hidden property="scaPassword"/>
        <html:hidden property="applicationNo"/>
        <html:hidden property="meesevaFlag"/>
        <html:hidden property="requestId"/>
        <html:hidden property="encryptedString"/>
        <html:hidden property="pensioncardno"/>
        <html:hidden property="checkSum"/>
        <input type="hidden" name="district_id" value="<%=district_id%>"/>
        <input type="hidden" name="mandal_id" value="<%=mandal_id%>"/>
        <input type="hidden" name="village_id" value="<%=village_id%>"/>
        <input type="hidden" name="habitation_id" value="<%=habitation_id%>"/>
        <input type="hidden" name="panchayat_id" value="<%=panchayat_id%>"/>
        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
            <tr>
                <td  align="center" valign="top">

                    <logic:present name="msg">

                        <p id="errmsg">${msg}</p>

                    </logic:present>

                    <br/>

                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" class="inputform">
                        <tr>
                            <th  colspan="4">SADAREM New Certificate Registration Through MEESEVA</th>
                        </tr>
                        <br>
                        <tr>
                            <td colspan="4">Personal Details</td>
                        </tr>

                        <tr>
                            <td width="50%" >Surname</td>
                            <td >
                                <html:text property="surname"  maxlength="25"  readonly="true" style="width:160px"/>
                            </td>

                            <td width="50%">
                                Name<font color="red"><b>*</b></font>
                            </td>
                            <td >
                                <html:text property="firstname"  maxlength="25"  readonly="true" style="width:160px"/>
                            </td>
                        </tr>
                        <tr>
                            <td   >Father/ Mother/ Husband/ Guardian's Name<font color="red"><b>*</b></font></td>
                            <td  >
                                <html:text property="relationName"  maxlength="30"  readonly="true" style="width:160px"/>
                            </td>

                            <td> Relation<font color="red"><b>*</b></font> </td>
                            <td >
                                <html:select property="relationType"  style="width:160px;">
                                    <html:option value="">- - SELECT - -</html:option>
                                    <html:option value="1">Father</html:option>
                                    <html:option value="2">Mother</html:option>
                                    <html:option value="3">Husband</html:option>
                                    <html:option value="7">Wife</html:option>
                                    <html:option value="5">Brother</html:option>
                                    <html:option value="6">Sister</html:option>
                                    <html:option value="4">Guardian</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>

                            <td  >Age <font color="red"><b>*</b></font></td>
                            <td ><html:text  property="age"  maxlength="2" onkeypress="return onlyNumbers();" style="width:160px"></html:text></td>
                            <td >Gender<font color="red"><b>*</b></font></td>
                            <td >
                                <html:select property="gender"  style="width:160px;">
                                    <html:option value="">- - SELECT - -</html:option>
                                    <html:option value="1">Male</html:option>
                                    <html:option value="2">Female</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td >Phone No<font color="red" size="2"><b>*</b></font></td>
                            <td ><html:text  property="phoneno"  maxlength="15" onkeypress="return onlyNumbers();"style="width:160px" ></html:text></td>

                            <td> House No.</td>
                            <td ><html:text  property="houseno" maxlength="15" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,houseno)" style="width:160px"> </html:text></td>
                        </tr>

                        <%--  </table>


                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" style="background-color:#848484">
                        --%>
                        <tr>
                            <td colspan="4">Address:</td>
                        </tr>
                        <tr>
                            <td >District</td>
                            <td ><html:text  property="districtName"   readonly="true" style="width:160px"></html:text></td>

                            <td >Mandal</td>
                            <td><html:text  property="mandalName" readonly="true" style="width:160px"></html:text></td>
                        </tr>
                        <tr>
                            <td  >Town/Village </td>
                            <td ><html:text  property="villageName"  readonly="true" style="width:160px"></html:text></td>

                            <td >Habitation/Ward No </td>
                            <td ><html:text  property="habitationName"  readonly="true" style="width:160px"></html:text></td>
                        </tr>
                        <%-- </table>


                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" style="background-color:#848484">--%>

                        <tr>
                            <td colspan="4">Payment Details:</td>
                        </tr>
                        <tr>

                            <td >Payment charges (Rs.):<font color="red"><b>*</b></font></td>
                            <td  colspan="3"><html:text  property="amount" value="<%=amt%>" readonly="true" style="width:160px"></html:text>
                            </td>
                        </tr>
                        <%--
                                            </table>
                                            <table>--%>
                        <tr>
                            <th align="center" colspan="4">
                                <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitButtons();" />
                                <input type="button"  value="Back" Id="subButtonBack" styleClass="button"  onclick="return submitBack();" />

                            </th>
                        </tr>
                    </table>

                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
