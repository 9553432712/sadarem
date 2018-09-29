<%--
    Document   : issueRaiseApproval
    Created on : Nov 28, 2012, 1:20:33 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="java.util.Iterator"%>
<%@page  import="java.util.ArrayList"%>
<%@page  import="org.bf.disability.dto.IssueRaiseApprovalDTO"%>


<html>
    <%
                String sadaremIdflag = null;
                String flags = null;
                if (request.getAttribute("sadaremIdflag") != null) {
                    sadaremIdflag = (String) request.getAttribute("sadaremIdflag");
                }
                if (request.getAttribute("flag") != null) {
                    flags = (String) request.getAttribute("flag");
                }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script src="./DisabilityUITG/js/modalpopup.js" type="text/javascript"></script>
        <script src="./DisabilityUITG/js/jquery-min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>
        <script>
            function getCategoryId(){
                document.forms[0].mode.value ="getCategoryWiseDetails";
                document.forms[0].submit();
            }
            function getIssueRaiseDetails() {
                if(document.forms[0].elements['categoryFormId'].value=="0") {
                    alert("Please Select Category!");
                    document.forms[0].elements['categoryFormId'].focus();
                    return false;
                }
                if(document.forms[0].elements['sadaremId'].value=="") {
                    alert("Please Enter SADAREM ID!");
                    document.forms[0].elements['sadaremId'].focus();
                    return false;
                }
                if((document.getElementById('sadaremRadioId').checked==false) && (document.getElementById('pensionRadioId').checked==false)) {
                    alert("Please Select  Any SADAREM ID/PENSION ID!");
                    document.getElementById('pensionRadioId').focus();
                    return false;
                }
                if(document.getElementById('sadaremRadioId').checked==true){
                    if(document.forms[0].elements['populateviceversaSadaremId'].value=="") {
                        alert("Please Enter SADAREM ID!");
                        document.forms[0].elements['populateviceversaSadaremId'].focus();
                        return false;
                    }
                }
                if(document.getElementById('pensionRadioId').checked==true){
                    if(document.forms[0].elements['newPensionId'].value=="") {
                        alert("Please Enter Pension ID!");
                        document.forms[0].elements['newPensionId'].focus();
                        return false;
                    }
                }
                if(document.forms[0].elements['mobile'].value=="") {
                    alert("Please Enter Mobile!");
                    document.forms[0].elements['mobile'].focus();
                    return false;
                }

                if(document.forms[0].elements['decription'].value=="") {
                    alert("Please Enter Decription!");
                    document.forms[0].elements['decription'].focus();
                    return false;
                }
                if(document.forms[0].elements['fileUpload'].value=="") {
                    alert("Please Browse Upload!");
                    document.forms[0].elements['fileUpload'].focus();
                    return false;
                }
                document.forms[0].action="./issueRaiseApproval.do?mode=insertIssueRaise&flag=6";
                document.forms[0].submit();
            }
            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
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
            function isAlpha(keyCode,name){
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

            function zeroFunction(ids) {

                var stdCode= document.forms[0].elements[ids].value;
                if(stdCode.substr(0,1)=="0") {
                    alert("First Digit Should not be ZERO");
                    document.forms[0].elements[ids].value="";
                }
            }
            function checkRationCardNo(id){
                var rationcard = id.toString().toUpperCase().substring(0,3);
                if(rationcard=="RAP" || rationcard=="TAP" || rationcard=="WAD"){
                    document.getElementById('memberId').style.display="block";
                    document.getElementById('serialId').style.display="block";
                }
            }

            function getRelationNameChanges(){

                document.forms[0].mode.value="relationNameChanges";
                document.forms[0].submit();
            }
            function validMobileNo(){
                if(document.forms[0].elements["mobile"].value.length < 10) {
                    alert("Mobile Number Should be TEN Digits");
                    document.forms[0].elements["mobile"].value="";
                    document.forms[0].elements["mobile"].focus();
                    return false;
                }

            }

            function getMedicalAddressDetails(){
                document.forms[0].mode.value ="getMedicalAddressDetails";
                document.forms[0].submit();

            }
            function getEqualSadaremId(){
                var sadaremId = document.forms[0].elements['sadaremId'].value;
                var newSadaremId = document.forms[0].elements['newSadaremId'].value;
                if(sadaremId==newSadaremId){
                    alert("SADAREM ID and  To Be Modified SADAREM ID should not be same!");
                    document.forms[0].elements['newSadaremId'].value="";
                    return false;
                }
            }

            function allnumeric(inputtxt)
            {
                var numbers =/^[0-9]+$/;
                if(inputtxt.value.match(numbers))
                {
                    //alert('Your Registration number has accepted....');
                    return true;
                }
                else
                {
                    alert('Please input numeric characters only');
                    inputtxt.focus();
                    inputtxt.value="";
                    return false;
                }
            }

            function onlyNumbersForMobile(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function uploadPhoto(str,photo)
            {
                var ext = str.value;
                ext = ext.toLowerCase().substring(ext.lastIndexOf(".")+1,ext.length);
                if(ext != "pdf"  && ext != "PDF")
                {
                    alert('You selected a " .'+ext+' "  file; Please Select a " .pdf/.PDF " file');
                    document.getElementById(photo).innerHTML = document.getElementById(photo).innerHTML;
                    return false;
                }else{
                    return true;
                }
            }
            function getsadaremId(id){
                if(id=="1"){
                    document.getElementById('sadaremIds').style.display="block";
                    document.getElementById('pensionIds').style.display="none";
                    document.forms[0].elements['populateviceversaSadaremId'].value="";
                    document.forms[0].elements['populateviceversaPensionId'].value="";
                }else if(id=="2"){
                    document.getElementById('sadaremIds').style.display="none";
                    document.getElementById('pensionIds').style.display="block";
                    document.forms[0].elements['newPensionId'].value="";
                    document.forms[0].elements['newSadaremId'].value="";
                }else{

                    document.getElementById('sadaremIds').style.display="none";
                    document.getElementById('pensionIds').style.display="none";
                }
            }

            function trim(str) {
                return str.replace(/^\s+|\s+$/g,'');
            }




            function getPensionIdDetails(id){
                var flag = false;
                if(id==3){
                    if(document.forms[0].elements["populateviceversaSadaremId"].value.length <17) {
                        alert("SADAREM ID  Should be Seventeen Digits");
                        document.forms[0].elements["populateviceversaSadaremId"].value="";
                        document.forms[0].elements["populateviceversaSadaremId"].focus();
                        flag=true;
                        return false;
                    }

                    var existingNo =document.forms[0].elements['populateviceversaSadaremId'].value;
                    var modifiedNo =document.forms[0].elements['sadaremId'].value;
                    if((existingNo==modifiedNo)){
                        alert("SADAREMID Should be not equal to ViceVersa SADAREMID!");
                        document.forms[0].elements['populateviceversaSadaremId'].value="";
                        document.forms[0].elements['populateviceversaSadaremId'].focus();

                        return false;
                        flag=true;
                    }

                } if(id==2){
                    if((document.forms[0].elements['pensionId'].value!="") &&(document.forms[0].elements['newPensionId'].value!="")){
                        var existingNo =document.forms[0].elements['pensionId'].value;
                        var modifiedNo =trim(document.forms[0].elements['newPensionId'].value);

                        if(existingNo==modifiedNo){
                            alert("PENSION ID Should be not equal to ViceVersa Pension!");
                            document.forms[0].elements['newPensionId'].value="";
                            document.forms[0].elements['newPensionId'].focus();
                            document.forms[0].elements['newSadaremId'].value="";

                            flag=true;
                            return false;
                        }
                    }


                }if(id==1){

                    if(document.forms[0].elements["sadaremId"].value.length <17) {
                        alert("SADAREM ID  Should be Seventeen Digits");
                        document.forms[0].elements["sadaremId"].value="";
                        document.forms[0].elements["sadaremId"].focus();
                        flag=true;
                        return false;
                    }
                    var existingNo =document.forms[0].elements['populateviceversaSadaremId'].value;
                    var modifiedNo =document.forms[0].elements['sadaremId'].value;
                    if((existingNo==modifiedNo)){
                        alert("SADAREMID Should be not equal to ViceVersa SADAREMID!");
                        document.forms[0].elements['sadaremId'].value="";
                        document.forms[0].elements['newPensionId'].value="";
                        document.forms[0].elements['sadaremId'].focus();
                        return false;
                        flag=true;
                    }

                }

                if(flag==false){
                    document.forms[0].action="./issueRaiseApproval.do?mode=getViceversaPopulateDetails&flag="+id;
                    document.forms[0].submit();
                }
            }



            function isNumberKey(evt)
            {
                var number = document.forms[0].elements['newPensionId'].value;
                var charCode = (evt.which) ? evt.which : event.keyCode
                if(number.length < 1){
                    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 65)
                        return false;
                }else{
                    if (charCode > 31 && (charCode < 48 || charCode > 57) )
                        return false;
                }

                return true;
            }

            function textBoxOnBlur(elementRef)
            {
                var checkValue = new String(elementRef.value);
                for (var i=0; i<checkValue.length; i++)
                {
                    var currentChar = checkValue.charAt(i);
                    if ( (currentChar == 'a' || currentChar == 'A') || (currentChar >= 0 || currentChar <= 9) ){
                        return true;
                    }else{
                        document.forms[0].newPensionId.value="";
                        document.forms[0].newPensionId.focus();
                        return false;
                    }
                }
            }
        </script>

        <style>
            select{width:175px;border:1px #ccc solid;}
        </style>
    </head>

    <body>
        <html:form action="/issueRaiseApproval" enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="hiddenFlags" value="<%=flags%>"/>


            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <logic:present name="error">
                <table cellpadding="1" cellspacing="8" align="center" width="60%" >
                    <p id="errmsg">${error}</p>
                </table>
            </logic:present>

            <logic:present name="fileSize">
                <p id="succmsg">${fileSize}</p>
            </logic:present>

            <br>

            <table cellpadding="0" cellspacing="1" align="center" class="inputform" width="90%">
                <tr>

                    <th colspan="2">Issue Raising Details </th>

                </tr>
            </table>

            <table cellpadding="0" cellspacing="1" align="center" width="90%" border="0" class="inputform" >


                <tr  align="left">
                    <td >Category :<font color="red"><b>*</b></font> </td>
                    <td colspan="3">
                        <html:select styleId="3" property="categoryFormId" onchange="getCategoryId(this);" style="width:320px">
                            <html:option value="0" >Select</html:option>
                            <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td> SADAREMID :<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="sadaremId" maxlength="17" onkeypress="return onlyNumbers();" onchange="getPensionIdDetails('1');"  styleId="saId1" style="width:180px"/>
                    </td>
                    <td>Pension ID :<font color="red"><b>*</b></font></td>

                    <td>
                        <html:text property="pensionId" maxlength="8" readonly="true" style="width:180px"/>
                    </td>
                </tr>

                <tr>
                    <td>Name :<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="newName" readonly="true" readonly="true" style="width:180px"/>
                    </td>
                    <td>RelationName:<font color="red"><b>*</b></font></td>

                    <td >
                        <html:text property="newRelationName" maxlength="8" readonly="true" onkeypress="return onlyNumbers();"  style="width:180px"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="4">
                        SADAREM ID <html:radio property="radioPensionId" style="width='25px'" styleId="sadaremRadioId" value="1" onclick="getsadaremId(this.value);"/> &nbsp;&nbsp;
                        Pension ID <html:radio property="radioPensionId" style="width='25px'" styleId="pensionRadioId" value="2" onclick="getsadaremId(this.value);"/>
                    </td>
                </tr>
                <tr id="pensionIds" style="display:block">
                    <td>ViceVersa Pension ID :<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="newPensionId" maxlength="8" onkeypress="return isNumberKey(event)"  onchange="textBoxOnBlur(this),getPensionIdDetails('2');" style="width:180px"/>
                    </td>
                    <td> SADAREM ID :</td>
                    <td>
                        <html:text property="newSadaremId" maxlength="17" onkeypress="return onlyNumbers();" readonly="true" style="width:180px"/>
                    </td>                   
                </tr>
                <tr id="sadaremIds" style="display:block">
                    <td> ViceVersa SADAREM ID :<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="populateviceversaSadaremId" maxlength="17" onkeypress="return onlyNumbers();" onchange="getPensionIdDetails('3');"  styleId="saId1" style="width:180px"/>
                    </td>
                    <td>Pension ID :<font color="red"><b>*</b></font></td>

                    <td>
                        <html:text property="populateviceversaPensionId" readonly="true" maxlength="8" onkeypress="return onlyNumbers();" style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <td>CampIncharge Mobile :<font color="red"><b>* </b></font></td>
                    <td colspan="3">
                        <html:text property="mobile" maxlength="10" onkeypress="return onlyNumbersForMobile(window.event);" onkeyup="zeroFunction('mobile');"  onchange="allnumeric(this),validMobileNo();"   style="width:180px"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        Description :<font color="red"><b>* </b></font></td>
                    <td colspan="3" valign="bottom" >   <html:textarea property="decription" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)" style="width:320px"/>
                        <input readonly type="text" name="remLen1" size="3" maxlength="3" value="500" style="width:30px">

                    </td>
                </tr>
                <tr>

                    <td>
                        Upload:<font color="red"><b>*</b></font>
                    </td>
                    <td colspan="3">
                        <div id="photo">
                            <html:file property="fileUpload" onchange="return uploadPhoto(this,'photo');"  style="width:320px"/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th  colspan="4">
                        <html:button property="but" value="Submit" onclick="getIssueRaiseDetails();" styleId="but" />
                    </th>
                </tr>

            </table>

            <br/>

        </html:form>
    </body>

    <%if (sadaremIdflag != null && sadaremIdflag.equals("1")) {%>
    <script>
        document.getElementById('sadaremIds').style.display="block";
        document.getElementById('pensionIds').style.display="none";
    </script>
    <%} else if (sadaremIdflag != null && sadaremIdflag.equals("2")) {%>
    <script>
        document.getElementById('sadaremIds').style.display="none";
        document.getElementById('pensionIds').style.display="block";
    </script>
    <%} else {%>
    <script>
        document.getElementById('sadaremIds').style.display="none";
        document.getElementById('pensionIds').style.display="none";
    </script>
    <%}%>




</html>