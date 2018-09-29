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
                String catId = null;
                if (request.getAttribute("catId") != null) {
                    catId = (String) request.getAttribute("catId");
                }
                String rationCardNoChange = null;
                if (request.getAttribute("rationCardNoChange") != null) {
                    rationCardNoChange = (String) request.getAttribute("rationCardNoChange");

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
            function createDistrictObject()
            {

                x=GetXmlHttpObject();
                x.onreadystatechange=getDistrictDetails;
                var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                x.open("Get", url, true);
                x.send();
            }

            function getDistrictDetails()
            {

                var res1,res2;

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var countss=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                    z=1;
                    while(z<=countss)
                    {
                        res1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        res2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(res1,res2,"district_id");
                        z++;
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
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;

                try{
                    document.getElementById(name).add(opt);
                }catch(ex){
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }
                }

            }
            function getIsseRaiseDetails() {
                if(document.forms[0].elements['categoryFormId'].value=="0") {
                    alert("Please Select Category!");
                    document.forms[0].elements['categoryFormId'].focus();
                    return false;
                } else if(document.forms[0].elements['rationCardNumber'].value=="") {
                    alert("Please Enter rationCardNo!");
                    document.forms[0].elements['rationCardNumber'].focus();
                    return false;
                }else if(document.forms[0].elements['sadaremIdRationCardSlno'].value=="0"){
                    alert("Please Select the SADAREM ID!");
                    document.forms[0].elements['sadaremIdRationCardSlno'].focus();
                    return false;

                }else if(document.forms[0].elements['newSerialNo'].value==""){
                    alert("Please Enter To be modified RationCard Serial No ");
                    document.forms[0].elements['newSerialNo'].focus();
                    return false;

                }
                else if(document.forms[0].elements['mobile'].value=="") {
                    alert("Please Enter Mobile!");
                    document.forms[0].elements['mobile'].focus();
                    return false;
                }

                else if(document.forms[0].elements['decription'].value=="") {
                    alert("Please Enter Decription!");
                    document.forms[0].elements['decription'].focus();
                    return false;
                }else {
                    document.forms[0].mode.value ="insertIssueRaise";
                    document.forms[0].submit();
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

           

            function getSADAREMIDDetails(){
                var flag =0;
                if(document.forms[0].elements["rationCardNumber"].value.length <15) {
                    alert("RationCard No Should be Fifteen Digits!");
                    document.forms[0].elements["rationCardNumber"].value="";
                    document.forms[0].elements["rationCardNumber"].focus();
                    return false;
                }
                else if(document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="WAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="wap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="PAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="pap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="AAY" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="aay" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="AAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="aap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="YAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="yap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="RAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="rap" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toUpperCase()=="TAP" ||
                    document.forms[0].elements["rationCardNumber"].value.substring(0,3).toLowerCase()=="tap"){
                    flag= true;
                }else{
                    alert("Please Enter RationCardNumber like WAP,PAP,AAY,AAP,YAP,RAP,TAP");
                    document.forms[0].elements["rationCardNumber"].value="";
                    document.forms[0].elements["rationCardNumber"].focus();
                    return false;
                }

                if(flag==true){
                    document.forms[0].mode.value ="getSADAREMIDDetails";
                    document.forms[0].submit();
                }
            }
            function basedUponSADAREMIDDetails(){
                document.forms[0].mode.value ="basedUponSADAREMIDDetails";
                document.forms[0].submit();

            }

            function checkEqualRationSlNo(){
               
                var existingSerialNo =document.forms[0].elements['populateSerialNo'].value;
                var modifiedSerialNo =document.forms[0].elements['newSerialNo'].value;
                if(existingSerialNo==modifiedSerialNo){
                    alert("Do not match With Existing RationCard Serial No!");
                    document.forms[0].elements['newSerialNo'].value="";
                    document.forms[0].elements['newSerialNo'].focus();
                    return false;
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
             

           
        </script>


        <style>
            select{width:175px;border:1px #ccc solid;}
        </style>
    </head>
    <body>
        <html:form action="/issueRaiseApproval">

            <html:hidden property="mode"/>
            <html:hidden property="hiddentoSetRationCardNo" value="<%=rationCardNoChange%>"/>


            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <logic:present name="error">
                <p id="errmsg">${error}</p>
            </logic:present>
            <logic:present name="errorMsg">
                <p id="errmsg">${errorMsg}</p>
            </logic:present>

            <logic:present name="validMsg">
                <p id="err msg">${validMsg}</p>
            </logic:present>


            <table cellpadding="0" cellspacing="1" align="center" width="90%" border="0" class="inputform">
                <tr>
                    <th colspan="4">
                        Issue Raising Details
                    </th>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="1" align="left" width="100%" border="0" >
                            <tr>
                                <td >Category :<font color="red"><b>*</b></font> </td>
                                <td colspan="3">
                                    <html:select styleId="3" property="categoryFormId" onchange="getCategoryId(this);" style="width:320px">
                                        <html:option value="0" >Select</html:option>
                                        <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>RationCard No :<font color="red"><b>* </b></font></td>
                                <td>

                                    <html:text property="rationCardNumber" maxlength="15" onchange="getSADAREMIDDetails();" value="<%=rationCardNoChange%>" style="width:180px"/>
                                </td>
                                <logic:present name="sadarermIdList">


                                    <td>SADAREM ID:<font color="red"><b>* </b></font></td>
                                    <td>
                                        <html:select property="sadaremIdRationCardSlno" onchange="basedUponSADAREMIDDetails();" style="width:180px">
                                            <html:option value="0">--Select--</html:option>
                                            <html:optionsCollection property="sadaremIdList" label="personCode" value="personCode"/>
                                        </html:select>

                                    </td>

                                </logic:present>
                            </tr>
                            <logic:present name="sadaremFlag">
                                <tr>
                                    <td>Name : <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="populateName" maxlength="50" readonly="true" style="width:180px"/>
                                    </td>
                                    <td>Relation Name : <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="populateRelationName" maxlength="17" onkeypress="return onlyNumbers();" readonly="true" style="width:180px"/>
                                    </td>
                                </tr>

                                <tr >
                                    <td>RationCard Serial No : <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="populateSerialNo" maxlength="3"  size="10" onkeypress="return onlyNumbers();"   readonly="true" style="width:180px"/>
                                    </td>
                                    <td>To be modified RationCard Serial No : <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="newSerialNo" maxlength="1"  size="10" onkeypress="return onlyNumbers();"  onchange="checkEqualRationSlNo();" style="width:180px"/>
                                    </td>
                                </tr>

                            </logic:present>

                            <tr>

                                <td>CampIncharge Mobile :<font color="red"><b>* </b></font></td>
                                <td colspan="3">
                                    <html:text property="mobile" maxlength="10" onkeypress="return onlyNumbersForMobile(window.event);" onkeyup="zeroFunction('mobile');"  onchange="allnumeric(this),validMobileNo();"  style="width:180px"/>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    Description :<font color="red"><b>* </b></font></td>
                                <td colspan="3" valign="bottom" >
                                    <html:textarea property="decription" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)" style="width:320px"/>
                                    <input readonly type="text" name="remLen1" maxlength="3" value="500" style="width:30px">

                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>


                <tr>
                    <th  colspan="4">
                        <html:button property="but" value="Submit" onclick="getIsseRaiseDetails();" />
                    </th>

                </tr>

            </table>
            <%-- <table cellpadding="10" cellspacing="0" border="0" align="center" width="90%">
                 <tr>
                     <td style="color:blue;font-size:14px;text-align: left"><u><b>Note:</b></u><span  style="color:#000;font-size:12px;">&nbsp;Please find the Format for Ration Card Details</span>&nbsp;&nbsp;<a href="#"onclick="window.open('./DisabilityUITG/documents/Rationcard Format.xlsx','','width=900,height=700,toolbar=no,scrollbars=yes');"class="topmenu">Click here.</a></td>
                 </tr>
             </table>--%>


            <%-- <a href="./issueRaiseApproval.do?mode=rationcardFormat">Here</a>--%>
            <br/>
            <div id="dvPopup" style="display:none;background-color:#fff;width:500px;border:2px #d0d0d0 solid;color:#fff;font-size: 11px;">

                <table cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
                    <tr><td style="background-color:#483D8B;color:#ffffff;padding:5px;text-align: center;font-size:18px;font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Information
                        </td>

                    </tr>
                    <tr>
                        <td  style="background-color:#fff;line-height: 18px;color:black;padding:15px;text-align:left;font-size:11px;font-weight:bold;">
                            <ul style="list-style-type: none">
                                <li> Please verify the card details in the following URL when ever you get ration card details not available.</li>
                                  <li> http://epds.nic.in</li>
                                <li>  If the details were present over there,</li>
                                <li>   Please raise request with details in below specified Rationcard format.</li>
                            </ul>
                        </td>
                    </tr>

                    <tr>
                        <td align="center" style="background-color:white;color:white;padding:10px;"">
                            <input type="button" value="Close" onclick="HideModalPopup('dvPopup'); return false;"/>
                        </td>
                    </tr>
                </table>
            </div>

        </html:form>
    </body>



</html>