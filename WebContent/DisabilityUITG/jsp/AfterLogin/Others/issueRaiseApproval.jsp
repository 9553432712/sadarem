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
<%
            String districtId = null;
            if (request.getAttribute("districtId") != null) {
                districtId = (String) request.getAttribute("districtId");
            }

%>

<html>
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
          
            function getIsseRaiseDetails() {
                if(document.forms[0].elements['categoryFormId'].value=="0") {
                    alert("Please Select Category!");
                    document.forms[0].elements['categoryFormId'].focus();
                    return false;
                }
                if(document.forms[0].elements['rationCardNumber'].value=="") {
                    alert("Please Enter RationCard No!");
                    document.forms[0].elements['rationCardNumber'].focus();
                    return false;
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
                document.forms[0].mode.value ="insertIssueRaise";
                document.forms[0].submit();
            }

            function notAllowSpecialCharcters(evt){

                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if(charCode==33 ||charCode==34 || charCode==35
                    || charCode==36 || charCode==37 || charCode==38
                    || charCode==39 || charCode==40 || charCode==41
                    || charCode==42 || charCode==43 || charCode==44
                    || charCode==45 || charCode==46 || charCode==47
                    || charCode==58 || charCode==59 || charCode==60
                    || charCode==61 || charCode==62 || charCode==63
                    || charCode==64 ||  charCode==91 || charCode==92
                    || charCode==93 || charCode==94 || charCode==95
                    || charCode==96  || charCode==123 || charCode==124
                    || charCode==125){
                    return false;
                }

            }

            function getCategoryId(){
                document.forms[0].mode.value ="getCategoryWiseDetails";
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
            
            function validMobileNo(){
                if(document.forms[0].elements["mobile"].value.length < 10) {
                    alert("Mobile Number Should be TEN Digits");
                    document.forms[0].elements["mobile"].value="";
                    document.forms[0].elements["mobile"].focus();
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
            function validRationCardNo(){
                var flag = false;
                if(document.forms[0].elements["rationCardNumber"].value.length <15) {
                    alert("RationCard No Should be Fifteen Digits!");
                    document.forms[0].elements["rationCardNumber"].value="";
                    document.forms[0].elements["rationCardNumber"].focus();
                    return false;
                }else if(document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="RAP"
                    || document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="rap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="TAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="tap"){
                    alert("Ration Card Address Change Provision is not given for RAP and TAP!");
                    document.forms[0].elements["rationCardNumber"].value="";
                    document.forms[0].elements["rationCardNumber"].focus();
                    return false;

                }
                else if(document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="WAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="wap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="PAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="pap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="AAY"||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="aay"||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="AAP"||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="aap"||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="YAP"||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="yap" ){
                    flag= true;
                }else{
                    alert("Please Enter RationCardNumber like WAP,PAP,AAY,AAP,YAP,RAP,TAP");
                    document.forms[0].elements["rationCardNumber"].value="";
                    document.forms[0].elements["rationCardNumber"].focus();

                    return false;

                }
                if(flag==true){
                    document.forms[0].mode.value="rationCardAddressChangebasedUponCivilSupplies";
                    document.forms[0].submit();
                }
            }
          

        </script>


    </head>
    <body>
        <html:form action="/issueRaiseApproval">
            <html:hidden property="mode"/>
            <html:hidden property="rationCardHidedndistrictId" value="<%=districtId%>"/>
            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <logic:present name="error">
                <p id="errmsg">${error}</p>
            </logic:present>

            <table cellpadding="0" cellspacing="0" align="center" width="90%" border="1" class="inputform" >
                <tr>
                    <th colspan="6">
                        Issue Raising Details
                    </th>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" align="center" width="100%" border="0" class="inputform" >
                            <tr align="left">

                    <td >Category :
                        <font color="red"><b>*</b></font>
                    </td>
                    <td colspan="3">
                        <html:select styleId="3" property="categoryFormId" onchange="getCategoryId(this);" style="width=320px" >
                            <html:option value="0" >Select</html:option>
                            <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                        </html:select>
                    </td>

                </tr>

                <tr>
                    <td>RationCard No :<font color="red"><b>* </b></font></td>
                    <td>
                        <html:text property="rationCardNumber" maxlength="15"  onkeypress="return notAllowSpecialCharcters(window.event);" onchange="validRationCardNo();" style="width=180px"  />
                    </td></tr><tr>
                    <td id="changeDis" >
                        Change District :<font color="red"><b>* </b></font>
                    </td>
                    <td>
                        <html:text property="district_name" readonly="true" style="width=180px"/>
                    </td>

                </tr>
                <tr>
                    <td>CampIncharge Mobile :<font color="red"><b>* </b></font></td>
                    <td colspan="3"> <html:text property="mobile"  maxlength="10" onkeypress="return onlyNumbers();" onkeyup="zeroFunction('mobile')" onchange="validMobileNo();" style="width=180px" />

                </tr>

                <tr>
                    <td>
                        Description :<font color="red"><b>* </b></font></td>
                    <td colspan="3" valign="bottom" >
                        <html:textarea property="decription" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)" style="width=320px" />
                        <input readonly type="text" name="remLen1" size="3" maxlength="3" value="500" style="width:30px">

                    </td>
                </tr>
                        </table>
                    </td>
                </tr>
                

                <tr>
                    <th colspan="4">
                        <html:button property="but" value="Submit" onclick="getIsseRaiseDetails();" />
                    </th>

                </tr>

            </table>
            <br/>

            <table cellpadding="0" cellspacing="1" border="0" align="center" width="90%" class="inputform">
                <tr><th colspan="6">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Information
                    </th>

                </tr>
                <tr>
                    <td>
                        <ul>
                            <li> Please verify the card details in the following URL when ever you get ration card details not available.</li>
                            <li> http://epds.nic.in</li>
                            <li>  If the details were present over there,</li>
                            <li>   Please raise request with details in below specified Rationcard format.</li>
                        </ul>
                    </td>
                </tr>

                <tr>
                    <th colspan="6">
                        <input type="button" value="Close" onclick="HideModalPopup('dvPopup'); return false;"/>
                    </th>
                </tr>
            </table>

        </html:form>
    </body>



</html>