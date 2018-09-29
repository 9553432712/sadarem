<%--
    Document   : subDisabilityType
    Created on : Mar 12, 2014, 6:11:02 PM
    Author     : 740996
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


            int i = 1;

            String typeofdisability = "";
            if (request.getAttribute("typeofdisability") != null) {
                typeofdisability = (String) request.getAttribute("typeofdisability");
            }


%>
<html>
    <%
                String catId = null;
                if (request.getAttribute("catId") != null) {
                    catId = (String) request.getAttribute("catId");
                }


    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script src="./DisabilityUITG/js/modalpopup.js" type="text/javascript"></script>
        <script src="./DisabilityUITG/js/jquery-min.js" type="text/javascript"></script>
        
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
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
                if(document.forms[0].elements['sadaremId'].value=="") {
                    alert("Please Select SADAREM ID!");
                    document.forms[0].elements['sadaremId'].focus();
                    return false;
                }

                if(document.forms[0].disabilityType!=null&&document.forms[0].elements['disabilityType'].value=="") {
                    alert("Please Select Modified Disability Type!");
                    document.forms[0].disabilityType.focus();
                    return false;

                }

                if(document.forms[0].elements['populaterelationType']!=null) {
                    if(document.forms[0].elements['populaterelationType'].value=="0"){
                        alert("Please Select To Be Modified Relation Ship!");
                        document.forms[0].elements['populaterelationType'].focus();
                        return false;
                    }}
                
                if(  document.forms[0].elements['subdisabilityId']!=null && document.forms[0].elements['subdisabilityId'].value==""
                    && document.forms[0].elements['subdisabilityId'].length>0){
                    alert("Please Select Modified Sub Disability!");
                    document.forms[0].elements['subdisabilityId'].focus();
                    return false;
                }
               
                else if(document.forms[0].elements['subsubdisabilityId']!=null && document.forms[0].elements['subsubdisabilityId'].value==""
                    && document.forms[0].elements['subsubdisabilityId'].length>0){
                    alert("Please Select Modified Sub Sub Disability!");
                    document.forms[0].elements['subsubdisabilityId'].focus();
                    return false;
                }
                else if(document.forms[0].elements['diagnosisDisability']!=null && document.forms[0].elements['diagnosisDisability'].value=="") {
                    alert("Please Enter Modified Disability/Impairment is due to!");
                    document.forms[0].elements['diagnosisDisability'].focus();
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
                document.forms[0].mode.value ="insertIssuesWithCategory";
                document.forms[0].submit();
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
            function onlyNumbersForMobile(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

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

            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

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

            function validatediagnosisofdisability()
            {
                var iChars = "!@#$%^&*()+=-[]\\\'`~;/{}|\":<>?1234567890";
                var diagnosisDisability=document.partAForm.diagnosisDisability.value;
                for (var i = 0; i < diagnosisDisability.length; i++)
                {
                    if (iChars.indexOf(diagnosisDisability.charAt(i)) != -1)
                    {
                        alert ("Please Enter Charecters only in  Disability/Impairment.");
                        document.partAForm.diagnosisDisability.value="";
                        document.partAForm.diagnosisDisability.focus();
                        return false;
                    }
                }
            }

            function getSubDisabilityTypeList(){
                if(document.forms[0].elements["sadaremId"].value.length <17) {
                    alert("SADAREM ID  Should be Seventeen Digits");
                    document.forms[0].elements["sadaremId"].value="";
                    document.forms[0].elements["sadaremId"].focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getsubDisabilityList";
                    document.forms[0].submit();
                }
            }


            function validMobileNo(){
                if(document.forms[0].elements["mobile"].value.length < 10) {
                    alert("Mobile Number Should be TEN Digits");
                    document.forms[0].elements["mobile"].value="";
                    document.forms[0].elements["mobile"].focus();
                    flag = false;
                    return false;
                }

            }
           
            function getRelationTypeChange(){
                if(document.forms[0].elements["sadaremId"].value.length <17) {
                    alert("SADAREM ID  Should be Seventeen Digits");
                    document.forms[0].elements["sadaremId"].value="";
                    document.forms[0].elements["sadaremId"].focus();
                    return false;
                }else{
                    document.forms[0].mode.value="relationTypeChange";
                    document.forms[0].submit();
                }
            }

            function removeRelationOption(){
                if(document.forms[0].relationType.value.length>0){
                    var select=document.getElementById('populaterelationType');
                    for (i=0;i<select.length;  i++) {
                        if (select.options[i].value==document.forms[0].relationType.value) {
                            document.forms[0].relationType.value=select.options[i].text;
                            select.remove(i);
                        }
                    }
                }

            }
        


        </script>

        <style>
            select{width:175px;border:1px #ccc solid;}
        </style>
    </head>

    <body >
        <html:form action="/issueRaiseApproval" enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="typeofdisability" value='<%=typeofdisability%>'/>
            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <logic:present name="error">
                <p id="errmsg">${error}</p>
            </logic:present>
            <logic:present name="errorMsg">
                <p id="errmsg">${errorMsg}</p>
            </logic:present>

            <br>

            <table cellpadding="0" cellspacing="1" align="center" width="90%" border="0" class="inputform">
                <tr >
                    <th colspan="4">
                        Issue Raising Details
                    </th>
                </tr>

                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="1" align="left" width="100%" border="0"  >

                            <tr align="left">
                                <td >Category :<font color="red"><b>*</b></font> </td>
                                <td  colspan="2">
                                    <html:select styleId="3" property="categoryFormId" onchange="getCategoryId(this);" style="width:320px">
                                        <html:option value="0" >Select</html:option>
                                        <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                                    </html:select>
                                </td>
                            </tr>

                            <tr align="left" id="">
                                <td>SADAREM ID :<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="sadaremId" styleId="saId10" maxlength="17" onkeypress="return onlyNumbers();" onchange="getSubDisabilityTypeList();" style="width:175px;"/>
                                </td>
                            </tr>
                            <logic:present name="relationtypechange">
                                <tr align="left" >
                                    <td>Relation Ship <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="relationType" maxlength="17" onkeypress="return onlyNumbers();"  style="width:175px;" readonly="true"/>
                                    </td>
                                </tr>


                                <tr>
                                    <td align="left">
                                        To be Modified Relation Ship :<font color="red"><b>*</b></font>
                                    </td>
                                    <td align="left">
                                        <html:select property="populaterelationType"  style="width:160px;" styleId="populaterelationType" style="width:175px;">
                                            <html:option value="0" >- - SELECT - -</html:option>
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

                                <script>
                                    removeRelationOption();
                                </script>
                            </logic:present>
                            <logic:notPresent name="relationtypechange">

                                <logic:present name="subTypeDis">
                                    <tr align="left" >
                                        <td>Disability Type <font color="red"><b>*</b></font></td>
                                        <td>
                                            <html:text property="disabilityType" maxlength="17" onkeypress="return onlyNumbers();"  style="width:175px;" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr align="left" >
                                        <td>
                                            Modified Sub Disability Type<font color="red"><b>*</b></font>
                                        </td>
                                        <td align="left" >
                                            <html:select property="subdisabilityId" multiple="true" style="width:200px;height:100px">
                                                <html:optionsCollection property="subdisabilityList" label="subdisabilityName" value="subdisabilityId" />
                                            </html:select>
                                            (Multiple selection option provided)
                                        </td>

                                    </tr>
                                </logic:present>
                                <logic:present name="subsubdisabilityId">
                                    <tr align="left" >
                                        <td>Disability Type <font color="red"><b>*</b></font></td>
                                        <td>
                                            <html:text property="disabilityType" maxlength="17" onkeypress="return onlyNumbers();"  style="width:175px;" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr align="left" >
                                        <td>
                                            Modified Sub Sub Disability Type<font color="red"><b>*</b></font>
                                        </td>
                                        <td align="left">
                                            <html:select property="subsubdisabilityId" multiple="true" style="width:300px;height:100px">
                                                <html:optionsCollection property="subsubdisabilityList" label="subsubdisabilityName" value="subsubdisabilityId" />
                                            </html:select>
                                            (Multiple selection option provided)
                                        </td>

                                    </tr>
                                </logic:present>

                            </logic:notPresent>
                            <logic:present name="diag">
                                <tr align="left" >
                                    <td>
                                        Disability/Impairment is due to
                                    </td>
                                    <td align="left"><input type="text" value="${diag}" size="100" style="width:175px;" readonly="true">

                                    </td>
                                </tr>
                                <tr align="left" >
                                    <td>
                                        Modified Disability/Impairment is due to <font color="red"><b>* </b></font>
                                    </td>
                                    <td>
                                        <html:text size="100"   maxlength="150"  property="diagnosisDisability"  onblur="validatediagnosisofdisability()" style="width:175px;"></html:text>
                                    </td>
                                </tr>
                            </logic:present>

                            <tr align="left">
                                <td>CampIncharge Mobile :<font color="red"><b>* </b></font></td>
                                <td>
                                    <html:text property="mobile" maxlength="10" onkeypress="return onlyNumbersForMobile(window.event);" onkeyup="zeroFunction('mobile');"  onchange="allnumeric(this),validMobileNo();"   style="width:175px;"/>
                                </td>

                               
                            </tr>

                            <tr align="left">
                                <td>
                                    Description :<font color="red"><b>* </b></font></td>
                                <td colspan="1" valign="bottom" >   <html:textarea property="decription" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)" style="width:320px;"/>
                                    <input readonly type="text" name="remLen1" size="3" maxlength="3" value="500" style="width:30px">

                                </td>

                            </tr>



                        </table>
                    </td>
                </tr>
                <tr>
                    <th  colspan="4">
                        <html:button property="but" value="Submit" onclick="getIsseRaiseDetails();" styleId="but" />
                    </th>
                    
                </tr>
            </table>
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