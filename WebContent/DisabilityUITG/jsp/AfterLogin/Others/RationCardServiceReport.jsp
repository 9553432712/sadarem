<%--
    Document   : RationCardServiceReport
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList;"%>
<% int i = 1;%>
<%
            ArrayList personalDetailsList = null;
            int size = 0;
            if (request.getAttribute("personalDetails") != null) {
                personalDetailsList = (ArrayList) request.getAttribute("personalDetails");
                size = personalDetailsList.size();
            }%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>

            function getData() {
                document.forms[0].mode.value="getVillages";
                document.forms[0].submit();
            }

            function getHabDetails() {
                document.forms[0].mode.value="getHabDetails";
                document.forms[0].submit();
            }

            function getDetails() {
                if(document.forms[0].elements['mandal_id'].value=="0"){
                    alert("Please Select Mandal");
                    document.forms[0].elements['mandal_id'].value=="";
                    document.forms[0].elements['mandal_id'].focus();
                }else if(document.forms[0].elements['village_id'].value=="0"){
                    alert("Please Select Village");
                    document.forms[0].elements['village_id'].value=="";
                    document.forms[0].elements['village_id'].focus();
                }else if(document.forms[0].elements['habitation_id'].value=="0"){
                    alert("Please Select Habitation");
                    document.forms[0].elements['habitation_id'].value=="";
                    document.forms[0].elements['habitation_id'].focus();
                }else if(document.forms[0].elements['personStatus'].value=="0"){
                    alert("Please Select Person Status");
                    document.forms[0].elements['personStatus'].value=="";
                    document.forms[0].elements['personStatus'].focus();
                }

                else{
                    document.forms[0].mode.value="getPersonDetails";
                    document.forms[0].submit();
                }
            }
            function submitRationDetails() {
                document.forms[0].mode.value="getInsertPersonDetails";
                document.forms[0].submit();
            }

            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function checkRationCard(val,no) {
                // if(val.length==3) {
                
                if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="TAP" && val.substring(0,3)!="RAP" &&
                    val.substring(0,3)!="tap" && val.substring(0,3)!="rap" 
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements['rationCardDetails'][no-1].value="";
                    // document.getElementById("rationCardDyn"+no).disabled = true;
                    document.forms[0].elements['rationCardDetails'][no-1].focus();
                    return false;
                    //     }
                }
            }


            function rationType(rationId,no){

                if(rationId.substring(0,3).length ==3) {
                    checkRationCard(rationId,no);
                    var rationCardNumber = rationId+no;

                    var cardnumber = null;
                    var cardnumberthree = null;
                    if(rationCardNumber != null){
                        var rationCardNumberValue = rationCardNumber;
                        if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3){
                            cardnumber = rationCardNumberValue.toString().toUpperCase().substring(0,3);
                            rationCardNumber.value=cardnumber;
                            document.getElementById("rationCardDyn"+no).selectedIndex = "";
                            //document.getElementById("rationCardDyn"+no).disabled = true;
                        }
                        if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3)){
                            cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
                            // document.getElementById("rationCardDyn"+no).disabled = false;
                            if(cardnumber != null || cardnumberthree != null){

                                if(cardnumber == "WAP" || cardnumberthree == "WAP"){

                                    document.getElementById("rationCardDyn"+no).selectedIndex = 1;
                                }else if(cardnumber == "PAP" || cardnumberthree == "PAP"){
                                    document.getElementById("rationCardDyn"+no).selectedIndex = 2;
                                }else if(cardnumber == "AAY" || cardnumberthree == "AAY"){
                                    document.getElementById("rationCardDyn"+no).selectedIndex = 3;
                                }else if(cardnumber == "AAP" || cardnumberthree == "AAP"){
                                    document.getElementById("rationCardDyn"+no).selectedIndex = 4;
                                }else if(cardnumber == "YAP" || cardnumberthree == "YAP"){
                                    document.getElementById("rationCardDyn"+no).selectedIndex = 5;
                                }else if(cardnumber == "TAP" || cardnumberthree == "TAP"){
                                    document.getElementById("rationCardDyn").selectedIndex = 6;
                                }else if(cardnumber == "RAP" || cardnumberthree == "RAP"){
                                    document.getElementById("rationCardDyn").selectedIndex = 7;
                                }else if(cardnumber == "WAD" || cardnumberthree == "WAD"){
                                    document.getElementById("rationCardDyn").selectedIndex = 8;
                                }else{
                                    alert("please enter valid RationCard Number ");
                                    document.getElementById('rationCardDet'+no).value = "";
                                    document.getElementById('rationCardDet'+no).focus();
                             
                                    return false;
                                }
                            }
                        }else if(rationCardNumberValue == ""){
                            document.forms[0].elements["rationCardDyn"+no].value = "";
                            //  document.getElementById("rationCardDyn"+no).disabled = true;
                        }
                    }
                }
            }
            //   }


            /*     function checkRationCard()
            {
                var input;
                var notEmpty = false;
                var div = document.getElementsByName("rationCardDetails");
                for(var i=0;i<div.length;i++)
                {
                    input = div[i];
                    if(input.type == "text")
                    {
                        if(input.value != "")
                        {
                            notEmpty = true;
                            break;
                        }
                        else
                        {
                            notEmpty = false;
                        }
                    }
                }
                if(notEmpty == false)
                {
                    alert("Atleast one RationCard Number must be enterer.");
                    return false;
                }

                return notEmpty;
            } */
           
            function submitRationDetails() {

                var input,serial;
                var notEmpty = false;
                var serialFlag = false;
                var div = document.getElementsByName("rationCardDetails");
                var rationSerial = document.getElementsByName("rationCardSerialNumbers");
                for(var i=0;i<div.length;i++)
                {
                    input = div[i];
                    if(input.type == "text")
                    {
                        if(input.value != "")
                        {
                            notEmpty = true;break;
                        }
                        else
                        {
                            notEmpty = false;
                        }
                    }
                }

                for(var i=0;i<rationSerial.length;i++)
                {
                    serial = rationSerial[i];
                    if(serial.type == "text")
                    {
                        if(serial.value != "")
                        {
                            serialFlag = true;break;
                        }
                        else
                        {
                            serialFlag = false;
                        }
                    }
                } if(notEmpty == false)
                {
                    alert("Please Enter Atleast One RationCard Number");
                    return false;
                }else if(serialFlag == false) {
                    alert("Please Enter Atleast One RationCard Serial Number");
                    return false;
                } else {

                    var personalDetailsLength = <%=size%>
                    var status="in";
                    for(var i=1;i<personalDetailsLength;i++) {
                        if(document.getElementById("rationCardDet"+i).value==""
                            && document.getElementById("rationCardDyn"+i).value!="0"
                            && document.getElementById("serialRationCardDyn"+i).value!="") {
                            alert("Please Enter RationCard Number");
                            status="out";
                            return false;
                        }else if(document.getElementById("rationCardDet"+i).value!=""
                            && document.getElementById("rationCardDyn"+i).value=="0"
                            && document.getElementById("serialRationCardDyn"+i).value!="") {
                            alert("Please Select RationCard Type");
                            status="out";
                            return false;
                        }/*else if(document.getElementById("rationCardDet"+i).value!=""
                            && document.getElementById("rationCardDyn"+i).value!="0"
                            && document.getElementById("serialRationCardDyn"+i).value=="") {
                            alert("Please Enter RationCard Serial Number");
                            status="out";
                            return false;
                        }*/
                        
                    }

                    if(status=="in") {
                        document.forms[0].mode.value="getInsertPersonDetails";
                        document.forms[0].submit();
                    }
                }
            }

            function checkValidate(obj,no) {
                if(obj > 10) {
                    alert("Please Enter Valid RationCard Serial Number");
                    document.forms[0].elements["serialRationCardDyn"+no].value = "";
                    return false;
                }

            }


        </script>
    </head>

    <body>
        <html:form  action="/rationCardServiceReport" >
            <html:hidden property="mode"/>


            <logic:present name="msg">
                <p id="errmsg"><bean:write name="msg"/></p>
            </logic:present>

 <%-- <table align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="90%">
    <tr><td>
--%>
            <table align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="90%">

                
                <tr>
                    <th colspan="8">RationCard Update</th>
                </tr>
                <tr>
                    <td>Mandal<font color="red"><b>*</b></font></td>
                <td>
                    <html:select property="mandal_id" onchange="getData();">
                        <html:option  value="0">--- SELECT ---</html:option>
                        <html:optionsCollection property="mandal_list" label="mandal_name" value="mandal_id"/>
                    </html:select>
                </td>
                <td >Village<font color="red"><b>*</b></font></td>
                <td>
                    <html:select property="village_id" style="height:25px;" onchange="getHabDetails()">
                        <html:option  value="0">--- SELECT ---</html:option>
                        <html:optionsCollection property="villagelist" label="village_name" value="village_id"/>
                    </html:select>
                </td>
               
                    <td >Habitation<font color="red"><b>*</b></font></td>
                <td>
                    <html:select property="habitation_id" style="height:25px;">
                        <html:option  value="0">--- SELECT ---</html:option>
                        <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"/>
                    </html:select>
                </td>
                <td >Person Status<font color="red"><b>*</b></font></td>
                <td>
                    <html:select property="personStatus">
                        <html:option value="0">-- Select --</html:option>
                        <html:option value="1">All</html:option>
                        <html:option value="2">Eligible</html:option>
                        <html:option value="3">AR</html:option>
                        <html:option value="4">DR</html:option>
                    </html:select>

                </td>
                </tr>
                
                <tr>
                    <th colspan="8">
                        <html:button property="but" value="Get" onclick="getDetails();"/>
                    </th>
                </tr>

                </table>
 <%--   </td></tr>
            </table>--%><br/>

            <logic:notEmpty name="personalDetails">
                <p>Person / Person's Details</p>
                <div style="overflow:scroll; width:950px; height:335px" align="center">
                <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th>
                            Slno
                        </th>
                        <th>
                            PensionCardNo
                        </th>
                        <th>
                            SADAREM ID
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Relation
                        </th>
                        <th>
                            RationCard
                        </th>
                        <th>
                            Type
                        </th>
                        <th>
                            Serial No
                        </th>
                    </tr>
                    <logic:iterate id="row" name="personalDetails">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>
                            <td>
                                ${row.pensioncard_no}
                            </td>
                            <td>
                                ${row.person_code}
                                <html:hidden property="personCode" value="${row.person_code}"/>
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.relation}
                            </td>
                            <td>
                                <html:text property="rationCardDetails" maxlength="15" onkeyup="rationType(this.value,${row.sno})" onchange="checkRationCard(this.value,${row.sno})"  styleId="rationCardDet${row.sno}" value="${row.rationCard}"/>
                            </td>
                            <td>
                                <html:select property="rationCardTypes" styleId="rationCardDyn${row.sno}" value="${row.rationCardType}">
                                    <html:option value="0">-- Select --</html:option>
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
                                <html:text style='width:50px' property="rationCardSerialNumbers" maxlength="2" onkeypress="return onlyNumbers();"  styleId="serialRationCardDyn${row.sno}" onkeyup="checkValidate(this.value,${row.sno})"/>
                            </td>
                        </tr>
                    </logic:iterate>
                    <tr>
                        <th colspan="8">
                            <html:button property="det" value="Submit" onclick="submitRationDetails();"/>
                        </th>
                    </tr>
                </table>

            </logic:notEmpty>
                </div>
        </html:form>
    </body>

</html>
