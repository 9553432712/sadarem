<%--
    Document   : captureDeadDetailsSubmit
    Created on : Dec 29, 2014, 6:33:27 PM
    Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>


<%
            String shgname = "";
            if (request.getAttribute("shgname") != null && !request.getAttribute("shgname").toString().equalsIgnoreCase("")) {
                shgname = request.getAttribute("shgname").toString();
                shgname = "";
            }
            String flag = "";
            String readonly = null;

            String validateEligibleDetails = null;
            if (request.getAttribute("readonly") != null) {
                readonly = (String) request.getAttribute("readonly");


             
            }

            String propertyName = null, styleId = null, noStyleId = null;
            ArrayList SADAREMIDValidDetails = new ArrayList();

            if (request.getAttribute("flag") != null) {
                flag = (String) request.getAttribute("flag");
            }
            if (request.getAttribute("SADAREMIDValidDetails") != null) {
                SADAREMIDValidDetails = (ArrayList) request.getAttribute("SADAREMIDValidDetails");
            }
            if (request.getAttribute("validateEligibleDetails") != null) {
                validateEligibleDetails = (String) request.getAttribute("validateEligibleDetails");
           
            }



%>

<%
            int noOfTxtBoxes = 0;
            if (request.getAttribute("noOfTxtBoxes") != null && !request.getAttribute("noOfTxtBoxes").toString().equalsIgnoreCase("")) {
                noOfTxtBoxes = Integer.parseInt(request.getAttribute("noOfTxtBoxes").toString());
            }
            String notMappedFlag = "";
            if (request.getAttribute("notMappedFlag") != null) {
                notMappedFlag = (String) request.getAttribute("notMappedFlag");
            }

%>

<script type="text/javascript">




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
    function textCounter(field,cntfield,maxlimit) {
        if (field.value.length > maxlimit){  // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
            // otherwise, update 'characters left' counter
        }
        else{
            cntfield.value = maxlimit - field.value.length;
        }

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

    function onlyNumbers(evt)
    {
        var e = event || evt; // for trans-browser compatibility
        var charCode = e.which || e.keyCode;

        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;

    }
    function isAlphaNumeric(keyCode) {
        if (keyCode == 16)
            isShift = true;
        var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
            (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
            || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
        return res;
    }



    function enable(){

        document.getElementById("data").innerHTML="";
        document.getElementById("loadImg").innerHTML="";
    <%if (readonly != null) {%>

          
            document.getElementById('aadharNo').style.display="block";
            document.getElementById('1').checked=true;
    <%}%>
        }
        function changeSelection(id){
        	 
            if(id=='Exist'){
                document.getElementById("data").innerHTML="";
                document.getElementById("loadImg").innerHTML="";
                document.getElementById('aadharRemarks').style.display="none";
                document.getElementById('aadharNo').style.display="block";
                document.forms[0].elements["aadharCardNo"].value="";

            }else if(id=='NotExist'){
                document.getElementById("data").innerHTML="";
                document.getElementById("loadImg").innerHTML="";
                document.getElementById('aadharRemarks').style.display="block";
                document.getElementById('aadharNo').style.display="none";
                document.forms[0].elements["aadharRemarks"].value="";
            }
            if(id=='Dead'){
                document.getElementById('shg').style.display="none";
                document.getElementById('deaddate').style.display="block";
                document.getElementById('shgno').style.display="none";
                document.getElementById('shgyes').style.display="none";
                document.forms[0].elements["shgReason"].value="";
                //document.forms[0].elements["shgname"].value="";
    <%--  document.forms[0].elements["shgid"].value="";--%>
                document.forms[0].elements["shgdate"].value="";
                document.getElementById('5').checked=false;
                document.getElementById('6').checked=false;

            }else if(id=='Alive'){

                document.getElementById('shg').style.display="block";
                document.getElementById('deaddate').style.display="none";
                document.forms[0].elements["deathdate"].value="";
                //   document.forms[0].elements["SHGRadio"].checked=false;

            }
            if(id=='1'){

                document.getElementById('shgno').style.display="none";
                document.getElementById('shgyes').style.display="block";
                document.getElementById('shgyes1').style.display="block";
                document.forms[0].elements["shgReason"].value="";

                if((document.forms[0].elements["voId"].value!="0") && (document.forms[0].elements["shgname"].value!="0")){
                    document.getElementById('mappedShgid1').style.display="none";
                    document.getElementById('mappedShgid2').style.display="none";


                }



            }

            if(id=='1' && '<%=notMappedFlag%>'=="notMappedFlag"){
                document.getElementById('shgno').style.display="none";
                document.getElementById('shgyes').style.display="block";
                document.getElementById('shgyes1').style.display="block";
                document.forms[0].elements["shgReason"].value="";
                document.forms[0].elements["voId"].value="0";
                document.forms[0].elements["shgname"].value="0";
                document.forms[0].elements["shgid"].value="";
            }else if(id=='0'){
                document.getElementById('shgno').style.display="block";
                document.getElementById('shgyes').style.display="none";
                document.getElementById('shgyes1').style.display="none";
                document.forms[0].elements["shgname"].value="";

                document.forms[0].elements["shgdate"].value="";
    <%-- alert("22=="+document.forms[0].elements["deadRadio"].checked);
      document.forms[0].elements["deadRadio"].checked=true;--%>
              }
              if(id=='Done'){
                  document.getElementById('surno').style.display="none";
                  document.getElementById('suryes').style.display="block";
                  document.getElementById('suryes1').style.display="block";
                  document.forms[0].elements["surReason"].value="";
              }else if(id=='RequiredSurgery'){
                  document.getElementById('surno').style.display="block";
                  document.getElementById('suryes').style.display="none";
                  document.getElementById('suryes1').style.display="none";
                  document.forms[0].elements["surtypeOfApp"].value="";
                  document.forms[0].elements["surOrganization"].value="";
                  document.forms[0].elements["surdate"].value="";
              }else if(id=="NotRequired"){
                  document.getElementById('surno').style.display="none";
                  document.getElementById('suryes').style.display="none";
                  document.getElementById('suryes1').style.display="none";

                  document.forms[0].elements["surtypeOfApp"].value="";
                  document.forms[0].elements["surOrganization"].value="";
                  document.forms[0].elements["surdate"].value="";
                  document.forms[0].elements["surReason"].value="";
              }
              if(id=='Received'){
                  document.getElementById('appno').style.display="none";
                  document.getElementById('appyes').style.display="block";
                  document.getElementById('appyes1').style.display="block";

                  document.forms[0].elements["appReason"].value="";
              }else if(id=='NotReceived'){
                  document.getElementById('appno').style.display="block";
                  document.getElementById('appyes').style.display="none";
                  document.getElementById('appyes1').style.display="none";
                  document.forms[0].elements["typeOfApp"].value="";
                  document.forms[0].elements["appOrganization"].value="";
                  document.forms[0].elements["appdate"].value="";
              }else if(id=='Notrequired'){
                  document.getElementById('appno').style.display="none";
                  document.getElementById('appyes').style.display="none";
                  document.getElementById('appyes1').style.display="none";

                  document.forms[0].elements["typeOfApp"].value="";
                  document.forms[0].elements["appOrganization"].value="";
                  document.forms[0].elements["appdate"].value="";
                  document.forms[0].elements["appReason"].value="";
              }

              if(id=='Covered'){
                  document.getElementById('pno').style.display="none";
                  document.getElementById('pyes').style.display="block";
                  document.getElementById('pyes1').style.display="block";
                  document.forms[0].elements["pReason"].value="";
              }else if(id=='NotCovered'){
                  document.getElementById('pno').style.display="block";
                  document.getElementById('pyes').style.display="none";
                  document.getElementById('pyes1').style.display="none";
                  document.forms[0].elements["pAccNo"].value="";
                  document.forms[0].elements["pBank"].value="";
                  document.forms[0].elements["pBranch"].value="";

              }

              if(id=='Sanctioned'){
                  document.getElementById('ano').style.display="none";

                  document.forms[0].elements["aRemarks"].value="";
              }else if(id=='NotSanctioned'){
                  document.getElementById('ano').style.display="block";

                  document.forms[0].elements["aasaraPensionId"].value="";
              }
          }


    <%if (validateEligibleDetails != null && validateEligibleDetails.equalsIgnoreCase("Eligible")) {%>
        function submitDetails(thisForm){
            var flag="true";
         /*    <!-- Validation for AADHAR Details Exist  --> */
            if((document.getElementById('1').checked==false) && (document.getElementById('2').checked==false)) {
                alert("Please Select AADHAR Details Exist?");
                document.getElementById('aadharRadio').focus();
                return false;
            } else if(document.getElementById('1').checked==true){
                var aadharCardNo = document.forms[0].aadharCardNo;
                if(aadharCardNo !=null){
                    if(aadharCardNo.value == ""){
                        alert("Please Enter Aadhar Card No!");
                        document.forms[0].aadharCardNo.focus();
                        return false;
                    }
                    if(aadharCardNo.value != "" && (aadharCardNo.value.length<12 ||aadharCardNo.value.length>12)){
                        alert("Given Aadhar Number is Not Valid Number. Aadhar Number should be 12 digits!");
                        document.forms[0].elements['aadharCardNo'].value="";
                        document.forms[0].aadharCardNo.focus();
                        return false;
                    }
                }
            }else if(document.getElementById('2').checked==true){

                var aadharRemarks = document.forms[0].aadharRemarks;
                if(aadharRemarks !=null){
                    if(aadharRemarks.value == ""){
                        alert("Please Enter Remarks!");
                        document.forms[0].aadharRemarks.focus();
                        return false;
                    }
                }
            }


          /*   <!-- Is the person dead or alive  --> */
            if((document.getElementById('3').checked==false) && (document.getElementById('4').checked==false)) {
                alert("Please Select the Person Dead or Alive?");
                document.getElementById('deadRadio').focus();
                return false;
            }else if(document.getElementById('4').checked==true){
              /*   <!-- SHG Members  --> */
                if((document.getElementById('5').checked==false) && (document.getElementById('6').checked==false)) {
                    alert("Please Select Member of SHG?");
                    document.getElementById('SHGRadio').focus();
                    return false;
                }  else if(document.getElementById('5').checked==true){
                    if(document.forms[0].voId.value == "0"){
                        alert("Please Select VO!");
                        document.forms[0].voId.focus();
                        return false;
                    }
                    else if(document.forms[0].shgname.value == "0"){
                        alert("Please Select SHG Name!");
                        document.forms[0].shgname.focus();
                        return false;
                    }

                }else if(document.getElementById('6').checked==true){
                    var shgReason = document.forms[0].shgReason;
                    if(shgReason !=null){
                        if(shgReason.value == ""){
                            alert("Please Select Reason!");
                            document.forms[0].shgReason.focus();
                            return false;
                        }
                    }
                }
            }


           /*  <!-- Received any AIDS and Appliances in last 3 years  --> */
            if((document.getElementById('15').checked==false) && (document.getElementById('7').checked==false) && (document.getElementById('8').checked==false)) {
                alert("Please Select Received any AIDS and Appliances in last 3 years?");
                document.getElementById('appRadio').focus();
                return false;
            } else if(document.getElementById('7').checked==true){
                var typeOfApp = document.forms[0].typeOfApp;
                if(typeOfApp !=null){
                    if(typeOfApp.value == ""){
                        alert("Please Enter Type of appliance!");
                        document.forms[0].typeOfApp.focus();
                        return false;
                    }
                }
                var appOrganization = document.forms[0].appOrganization;
                if(appOrganization !=null){
                    if(appOrganization.value == ""){
                        alert("Please Select Organization!");
                        document.forms[0].appOrganization.focus();
                        return false;
                    }
                }
                var appdate = document.forms[0].appdate;
                if(appdate !=null){
                    if(appdate.value == ""){
                        alert("Please Select Date of Disbursement!");
                        document.forms[0].appdate.focus();
                        return false;
                    }else{

                        var yye=appdate.value.substr(6,4);
                        var mme=appdate.value.substr(3,2);
                        var dde=appdate.value.substr(0,2);
                        var etd = new  Date();
                        var today=new Date();
                        etd.setFullYear(yye,mme-1,dde);
                        if (today < etd)
                        {
                            flag="false";
                            alert("Date of disbursement cannot be Future Date");
                            document.forms[0].appdate.value="";
                            return false;
                        }
                    }

                }
            }else if(document.getElementById('8').checked==true){
                var appReason = document.forms[0].appReason;
                if(appReason !=null){
                    if(appReason.value == ""){
                        alert("Please Enter Requirement Type!");
                        document.forms[0].appReason.focus();
                        return false;
                    }
                }
            }

            /* <!-- Surgical correction  --> */
            if((document.getElementById('16').checked==false) && (document.getElementById('9').checked==false) && (document.getElementById('10').checked==false)) {
                alert("Please Select Surgical Correction?");
                document.getElementById('surRadio').focus();
                return false;
            } else if(document.getElementById('9').checked==true){
                var surtypeOfApp = document.forms[0].surtypeOfApp;
                if(surtypeOfApp !=null){
                    if(surtypeOfApp.value == ""){
                        alert("Please Enter Type of Surgery!");
                        document.forms[0].surtypeOfApp.focus();surOrganization
                        return false;
                    }
                }
                var surOrganization = document.forms[0].surOrganization;
                if(surOrganization !=null){
                    if(surOrganization.value == ""){
                        alert("Please Select  Organization!");
                        document.forms[0].surOrganization.focus();
                        return false;
                    }
                }
                var surdate = document.forms[0].surdate;
                if(surdate !=null){
                    if(surdate.value == ""){
                        alert("Please Select Date of Surgery!");
                        document.forms[0].surdate.focus();
                        return false;
                    }else{

                        var yye=surdate.value.substr(6,4);
                        var mme=surdate.value.substr(3,2);
                        var dde=surdate.value.substr(0,2);
                        var etd = new  Date();
                        var today=new Date();
                        etd.setFullYear(yye,mme-1,dde);
                        if (today < etd)
                        {
                            flag="false";
                            alert("Date of Surgery cannot be Future Date");
                            document.forms[0].surdate.value="";
                            return false;
                        }
                    }
                }
            }else if(document.getElementById('10').checked==true){
                var surReason = document.forms[0].surReason;
                if(surReason !=null){
                    if(surReason.value == ""){
                        alert("Please Enter Type of Surgery!");
                        document.forms[0].surReason.focus();
                        return false;
                    }
                }
            }

          /*   <!-- PMJDY A/C details (Prime Minister Jan Dhan Yojana)  --> */
            if((document.getElementById('11').checked==false) && (document.getElementById('12').checked==false)) {
                alert("Please Select PMJDY A/C Details (Prime Minister Jan Dhan Yojana)?");
                document.getElementById('pRadio').focus();
                return false;
            } else if(document.getElementById('11').checked==true){
                var pAccNo = document.forms[0].pAccNo;
                if(pAccNo !=null){
                    if(pAccNo.value == ""){
                        alert("Please Enter Account Number!");
                        document.forms[0].pAccNo.focus();
                        return false;
                    }
                }
                var pBank = document.forms[0].pBank;
                if(pBank !=null){
                    if(pBank.value == ""){
                        alert("Please Enter Bank!");
                        document.forms[0].pBank.focus();
                        return false;
                    }
                }
                var pBranch = document.forms[0].pBranch;
                if(pBranch !=null){
                    if(pBranch.value == ""){
                        alert("Please Enter Branch!");
                        document.forms[0].pBranch.focus();
                        return false;
                    }
                }
    <%-- var pifsccode = document.forms[0].pIFSCCode;
     if(pifsccode !=null){
         if(pifsccode.value == ""){
             alert("Please Enter IFSC Code!");
             document.forms[0].pIFSCCode.focus();
             return false;
         }
     }--%>
             }else if(document.getElementById('12').checked==true){
                 var pReason = document.forms[0].pReason;
                 if(pReason !=null){
                     if(pReason.value == ""){
                         alert("Please Enter Reason!");
                         document.forms[0].pReason.focus();
                         return false;
                     }
                 }
             }

           /*   <!-- AASARA ID  --> */
             if((document.getElementById('13').checked==false) && (document.getElementById('14').checked==false)) {
                 alert("Please Select AASARA Details?");
                 document.getElementById('aasaraRadio').focus();
                 return false;
             } else if(document.getElementById('14').checked==true){
                 var aRemarks = document.forms[0].aRemarks;
                 if(aRemarks !=null){
                     if(aRemarks.value == ""){
                         alert("Please Enter Remarks!");
                         document.forms[0].aRemarks.focus();
                         return false;
                     }
                 }
             }

    <%--if(document.forms[0].elements['serveid'].value==""){
        alert("Please Enter the SKS ID");
        document.forms[0].elements['serveid'].focus();
        return false;
    }--%>
            if(document.forms[0].elements['serveDoneBy'].value==""){
                alert("Please Enter the Validation Done By");
                document.forms[0].elements['serveDoneBy'].focus();
                return false;
            }
            if(document.forms[0].elements['designation'].value==""){
                alert("Please Enter the Designation");
                document.forms[0].elements['designation'].focus();
                return false;
            }

            var recivedDate = document.forms[0].recivedDate;
            if(recivedDate !=null){
                if(recivedDate.value == ""){
                    alert("Please Select Validation Done Date !");
                    document.forms[0].recivedDate.focus();
                    return false;
                }else{

                    var yye=recivedDate.value.substr(6,4);
                    var mme=recivedDate.value.substr(3,2);
                    var dde=recivedDate.value.substr(0,2);
                    var etd = new  Date();
                    var today=new Date();
                    etd.setFullYear(yye,mme-1,dde);
                    if (today < etd)
                    {
                        flag="false";
                        alert("Validation Done Date cannot be Future Date");
                        document.forms[0].recivedDate.value="";
                        return false;
                    }
                }
            }


            if (thisForm.getAttribute('submitted')){
                flag = false;
                return flag;
            }else{
                thisForm.setAttribute('submitted','true');
            }
            if(flag=="true"){
                document.forms[0].action="./captureDeadDetails.do?mode=insertDetails";
                document.forms[0].submit();
            }
        }

        function getShgDetails(){
            //alert("shgNameDada==");
            document.forms[0].action="./captureDeadDetails.do?mode=getPwdShgData";
            document.forms[0].submit();

        }
        function getShgIdDetails(){
            //alert("shgid");
            document.forms[0].action="./captureDeadDetails.do?mode=getShgIdDetails";
            document.forms[0].submit();

        }


    <%} else {%>
        function enable(){

            document.getElementById("data").innerHTML="";
            document.getElementById("loadImg").innerHTML="";
    <%if (readonly != null) {%>
            document.getElementById('aadharNo').style.display="block";
            document.getElementById('1').checked=true;
    <%}%>
        }


        function changeSelection(id){
            if(id=='Exist'){
              
                document.getElementById("data").innerHTML="";
                document.getElementById("loadImg").innerHTML="";
                document.getElementById('aadharRemarks').style.display="none";
                document.getElementById('aadharNo').style.display="block";
                document.forms[0].elements["aadharCardNo"].value="";

            }else if(id=='NotExist'){
              
                document.getElementById("data").innerHTML="";
                document.getElementById("loadImg").innerHTML="";
                document.getElementById('aadharRemarks').style.display="block";
                document.getElementById('aadharNo').style.display="none";
                document.forms[0].elements["aadharRemarks"].value="";

            }

        }






        function submitDetails(thisForm){
            var flag="true";
            if((document.getElementById('1').checked==false) && (document.getElementById('2').checked==false)) {
                alert("Please Select AADHAR Details Exist?");
                document.getElementById('aadharRadio').focus();
                return false;
            } else if(document.getElementById('1').checked==true){
                var aadharCardNo = document.forms[0].aadharCardNo;
                if(aadharCardNo !=null){
                    if(aadharCardNo.value == ""){
                        alert("Please Enter Aadhar Card No!");
                        document.forms[0].aadharCardNo.focus();
                        return false;
                    }
                    if(aadharCardNo.value != "" && (aadharCardNo.value.length<12 ||aadharCardNo.value.length>12)){
                        alert("Given Aadhar Number is Not Valid Number. Aadhar Number should be 12 digits!");
                        document.forms[0].elements['aadharCardNo'].value="";
                        document.forms[0].aadharCardNo.focus();
                        return false;
                    }
                }
            }else if(document.getElementById('2').checked==true){

                var aadharRemarks = document.forms[0].aadharRemarks;
                if(aadharRemarks !=null){
                    if(aadharRemarks.value == ""){
                        alert("Please Enter Remarks!");
                        document.forms[0].aadharRemarks.focus();
                        return false;
                    }
                }
            }
            if(document.forms[0].elements['serveDoneBy'].value==""){
                alert("Please Enter the Validation Done By");
                document.forms[0].elements['serveDoneBy'].focus();
                return false;
            }
            if(document.forms[0].elements['designation'].value==""){
                alert("Please Enter the Designation");
                document.forms[0].elements['designation'].focus();
                return false;
            }

            var recivedDate = document.forms[0].recivedDate;
            if(recivedDate !=null){
                if(recivedDate.value == ""){
                    alert("Please Select Validation Done Date !");
                    document.forms[0].recivedDate.focus();
                    return false;
                }else{

                    var yye=recivedDate.value.substr(6,4);
                    var mme=recivedDate.value.substr(3,2);
                    var dde=recivedDate.value.substr(0,2);
                    var etd = new  Date();
                    var today=new Date();
                    etd.setFullYear(yye,mme-1,dde);
                    if (today < etd)
                    {
                        flag="false";
                        alert("Validation Done Date cannot be Future Date");
                        document.forms[0].recivedDate.value="";
                        return false;
                    }
                }
            }


            if (thisForm.getAttribute('submitted')){
                flag = false;
                return flag;
            }else{
                thisForm.setAttribute('submitted','true');
            }
            if(flag=="true"){
                document.forms[0].action="./captureDeadDetails.do?mode=insertDetails";
                document.forms[0].submit();
            }
        }


    <%}%>



    <%-- <%if(!validateEligibleDetails.equalsIgnoreCase("Eligible")){%>

            <%}%>--%>

</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>SADAREM</title>

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>


    </head>
    <body onload="enable();">

        <html:form action="captureDeadDetails?mode=insertDetails" onsubmit="return submitDetails(this)" method="post" enctype="multipart/form-data" >
            <input type="hidden" name="mode"/>
            <html:hidden property="voidData" value="${voidData}"/>

            <logic:present name="succmsg">
                <p id="succmsg"><bean:write name="succmsg"/></p>
            </logic:present>
            <logic:present name="errmsg">
                <p id="errmsg"><bean:write name="errmsg"/></p>
            </logic:present>

            <logic:present name="editPwdStatus">
                <p id="errmsg">${editPwdStatus}</p>
            </logic:present>


            <logic:notPresent name="Edit">
                <%if (validateEligibleDetails != null && validateEligibleDetails.equalsIgnoreCase("Eligible")) {%>




                <logic:present name="SADAREMIDValidDetails">
                    <br>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                        <tr>  <td align="right">

                                <a href="captureDeadDetails.do" > Back</a>
                            </td>
                        </tr>
                    </table>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                        <th colspan="8">
                            Personal Details
                        </th>
                        <tr><td>SADAREM ID</td>
                            <td>Full Name</td>
                            <td>Relation Name</td>
                            <td>Gender</td>
                            <td>Habitation</td>
                            <td>Village</td>
                            <td>Mandal</td>
                            <td>Contact No</td>
                        </tr>
                        <logic:iterate id="modify" name="SADAREMIDValidDetails" scope="request">
                            <input type="hidden" name="personcode" value="${modify.personCode}"/>

                            <tr>
                                <td>${modify.personCode}</td>
                                <td>${modify.name}</td>
                                <td>${modify.relationName}</td>
                                <td>${modify.gender}</td>
                                <td>${modify.habitation}</td>
                                <td>${modify.village}</td>
                                <td>${modify.mandal}</td>
                                <td>${modify.phoneno}</td>
                            </tr>
                        </logic:iterate>
                    </table>
                    <br><br>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">

                        <th colspan="8">
                            Data Validation Details
                        </th>
                        <tr> <th style="text-align: left" colspan="4">1. AADHAR Details Exist?<font color="red"><b>* </b></font></th></tr>
                        <tr>

                            <td colspan="4">
                                <html:radio  property="aadharRadio"  styleId="1" value ="Exist" onclick="changeSelection(this.value);" style="width:30px">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <%if (readonly == null) {%>
                                <html:radio  property="aadharRadio" styleId="2"  value ="NotExist" onclick="changeSelection(this.value);"  style="width:30px" >No</html:radio>
                                <%}%>
                            </td>

                        </tr>

                        <tr id="aadharNo" style="display: none;">

                            <td style="text-align: right"> Aadhar Card No<font color="red"><b>* </b></font></td>
                            <%if (readonly != null) {%>

                            <td  >
                                <input name="aadharCardNo"  style="width:200px"  readonly="true" value='<%=readonly%>'/>

                            </td>
                            <%} else if (validateEligibleDetails != null && validateEligibleDetails.equalsIgnoreCase("Rejected")) {%>
                            <td  >
                                <html:text property="aadharCardNo" onkeypress="return onlyNumbers();"  maxlength="12" style="width:200px"  onkeyup="validateAadhar(this.value);"/>
                            </td>
                            <%} else{%>
                                    <td  >
                                <html:text property="aadharCardNo" onkeypress="return onlyNumbers();"  maxlength="12" style="width:200px"  onkeyup="validateAadhar(this.value);"/>
                            </td>
                                   <%}%>
                            <td colspan="6" >
                                <div id="data"></div>
                                <div id="loadImg"></div>
                            </td>

                        </tr>
                        <tr  id="aadharRemarks" style="display: none;"   >
                            <td style="text-align: right">
                                Remarks <font color="red"><b>* </b></font></td>
                            <td colspan="3" valign="bottom" >


                                <html:textarea property="aadharRemarks" rows="5" cols="30"
                                               onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,50)" onkeyup="textCounter(this,document.forms[0].remLen1,50)"style="width:220px"/>
                                <input readonly type="text" name="remLen1" size="3" maxlength="3" value="200" style="width:30px">

                            </td>
                        </tr>
                        <%-- </table>
                     </tr>--%>

                        <%-- <tr style="vertical-align: top">
                         <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%" style="vertical-align: top">
                        --%>
                        <tr>
                            <th style="text-align: left" colspan="4">2. Is the Person Dead or Alive?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr style="vertical-align: top">

                            <td colspan="4">
                                <html:radio  property="deadRadio" styleId="3" value ="Dead" onclick="changeSelection(this.value);"  style="width:30px" >Dead</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio  property="deadRadio" styleId="4"  value ="Alive" onclick="changeSelection(this.value);"  style="width:30px" >Alive</html:radio>
                            </td>

                        </tr>

                        <tr id="deaddate" style="display: none;"  >

                            <td style="text-align: left">Death Date</td>
                            <td colspan="44">
                                <html:text property="deathdate"  readonly="true" />
                                <a  href="javascript:show_calendar('forms[0].deathdate');" onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>

                        </tr>
                        <tr id="shg" style="display: none;"  >

                            <td style="text-align: left">Member of SHG?<font color="red"><b>* </b></font></td>
                            <td colspan="3">
                                <html:radio  property="SHGRadio" styleId="5" value ="1" onclick="changeSelection(this.value);"   style="width:30px">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio  property="SHGRadio" styleId="6"  value ="0" onclick="changeSelection(this.value);"   style="width:30px">No</html:radio>
                            </td>

                        </tr>
                        <tr id="shgno" style="display: none;"  >

                            <td style="text-align: left">Reason<font color="red"><b>* </b></font></td>
                            <td colspan="3" >
                                <html:select property="shgReason" style="width:200px;" >
                                    <html:option value="">--SELECT--</html:option>
                                    <html:option value="Above poverty line">Above poverty line</html:option>
                                    <html:option value="Above 50 years">Above 50 years</html:option>
                                    <html:option value="Migrated">Migrated</html:option>
                                    <html:option value="No sufficient members to form">No sufficient members to form</html:option>
                                    <html:option value="Not interested">Not interested</html:option>
                                    <html:option value="Orphan">Orphan</html:option>
                                    <html:option value="Any other reason">Any other reason</html:option>



                                </html:select></td>

                        </tr>


                        <logic:present name="mappedShg">
                            <tr id="shgyes" style="display: none;">
                                <td style="text-align: left"> VO&nbsp;Name:<font color="red"><b>* </b></font></td>
                                <td >
                                    ${voName}
                                    <html:hidden property="voId"/>
                                </td>

                                <td style="text-align: left">SHG&nbsp;Name<font color="red"><b>* </b></font></td>
                                <td >
                                    ${shgName}
                                    <input type="hidden" name="shgname" value="${shgName}"/>

                                </td>
                            </tr>

                            <tr id="shgyes1" style="display: none;">
                                <td style="text-align: left">SHG&nbsp;ID</td>
                                <td >
                                    ${shgid}
                                    <html:hidden property="shgid"/>

                                </td>

                                <td style="text-align: left">Formation&nbsp;Date</td>
                                <td >
                                    <html:text property="shgdate"  readonly="true" />&nbsp;
                                    <a  href="javascript:show_calendar('forms[0].shgdate');" onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>

                            </tr>
                        </logic:present>
                        <logic:present name="notMappedShg">

                            <tr id="shgyes" style="display: none;">
                                <td style="text-align: left;">VO<font color="red"><b>* </b></font></td>
                                <td>

                                    <html:select property="voId" onchange="getShgDetails();">
                                        <html:option value="0">-- VO --</html:option>
                                        <html:optionsCollection property="voList" label="voName" value="voId"/>
                                    </html:select>
                                </td>

                                <td style="text-align: left;">SHG&nbsp;Name<font color="red"><b>* </b></font></td>
                                <td>

                                    <html:select property="shgname" onchange="getShgIdDetails();">
                                        <html:option value="0">-- SHG Name --</html:option>
                                        <html:optionsCollection property="shgList" label="shgname" value="shgname"/>
                                    </html:select>
                                </td>

                                <logic:present name="shgId">


                                    <td  id="mappedShgid1" style="text-align: right">SHG&nbsp;ID</td>
                                    <td id="mappedShgid2">
                                        ${shgId}
                                        <html:hidden property="shgid"/>
                                    </td>

                                </logic:present>
                                <td style="text-align: left">Formation&nbsp;Date</td>
                                <td >
                                    <html:text property="shgdate"  readonly="true" />&nbsp;
                                    <a  href="javascript:show_calendar('forms[0].shgdate');" onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>

                            </tr>

                        </logic:present>
                        <%-- </tr>
                     </table>--%>
                        <tr >
                            <th style="text-align: left" colspan="4">
                                3. Received any AIDS and Appliances in last 3 years?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr style="vertical-align: top">

                            <td colspan="4">
                                <html:radio  property="appRadio" styleId="15" value ="Notrequired" onclick="changeSelection(this.value);"   style="width:30px">Not Required</html:radio>&nbsp;&nbsp;
                                <html:radio  property="appRadio" styleId="7" value ="Received" onclick="changeSelection(this.value);"   style="width:30px">Received</html:radio>&nbsp;&nbsp;
                                <html:radio  property="appRadio" styleId="8"  value ="NotReceived" onclick="changeSelection(this.value);"   style="width:30px">Not Received (Eligible)</html:radio>
                            </td>

                        </tr>




                        <tr  id="appno" style="display: none;"   >
                            <td style="text-align: left">
                                Requirement Type <font color="red"><b>* </b></font></td>
                            <td colspan="3" valign="bottom" >
                                <html:textarea property="appReason" rows="5" cols="30"
                                               onkeypress="return space(event,this)"
                                               onkeydown="textCounter(this,document.forms[0].reasonlen,200)"
                                               onkeyup="textCounter(this,document.forms[0].reasonlen,200)" style="width:220px"/>
                                <input readonly type="text" name="reasonlen" size="3" maxlength="3" value="200" style="width:30px">

                            </td>
                        </tr>


                        <tr id="appyes" style="display: none;">

                            <td style="text-align: left">Type of Appliance<font color="red"><b>* </b></font></td>
                            <td >
                                <html:text property="typeOfApp" size="25"  maxlength="75"   onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"  />
                            </td>

                            <td style="text-align: left">Organization<font color="red"><b>* </b></font></td>
                            <td  >
                                <html:select property="appOrganization" style="width:200px;" >
                                    <html:option value="">--SELECT--</html:option>
                                    <html:option value="SERP">SERP</html:option>
                                    <html:option value="Disability welfare department">Disability welfare department</html:option>
                                    <html:option value="SSA">SSA</html:option>
                                    <html:option value="NGO">NGO</html:option>
                                    <html:option value="Others">Others</html:option>

                                </html:select></td>
                        </tr>
                        <tr id="appyes1" style="display: none;"  >

                            <td style="text-align: left">Date of Disbursement<font color="red"><b>*</b></font></td>
                            <td colspan="3">
                                <html:text property="appdate"  readonly="true" />
                                <a  href="javascript:show_calendar('forms[0].appdate');" Member of SHG?*onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>

                        </tr>
                        <%--</table>
                    </tr>--%>

                        <tr>
                            <th style="text-align: left" colspan="4">4. Surgical Correction?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr style="vertical-align: top">

                            <td colspan="4">
                                <html:radio  property="surRadio" styleId="16" value ="NotRequired" onclick="changeSelection(this.value);"  style="width:30px" >Not Required</html:radio> &nbsp;&nbsp;
                                <html:radio  property="surRadio" styleId="9" value ="Done" onclick="changeSelection(this.value);"  style="width:30px" >Done</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio  property="surRadio" styleId="10"  value ="RequiredSurgery" onclick="changeSelection(this.value);"  style="width:30px" >Required Surgery (Eligible)</html:radio>
                            </td>

                        </tr>




                        <tr  id="surno" style="display: none;"   >
                            <%--<td style="text-align: right">
                                Reason <font color="red"><b>* </b></font></td>
                            <td colspan="5" valign="bottom" >   <html:textarea property="surReason" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="return isAlphaNumeric(event.keyCode);textCounter(this,document.forms[0].reasonlen2,200)" onkeyup="textCounter(this,document.forms[0].reasonlen2,200)" style="width:220px"/>
                                <input readonly type="text" name="reasonlen2" size="3" maxlength="3" value="200" style="width:30px">

                        </td>--%>

                            <td style="text-align: left">Type of Surgery<font color="red"><b>* </b></font></td>
                            <td colspan="3">
                                <html:text property="surReason" size="25"  maxlength="75"   onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"  />
                            </td>
                        </tr>


                        <tr id="suryes" style="display: none;"  >

                            <td style="text-align: left">Type of Surgery<font color="red"><b>* </b></font></td>
                            <td >
                                <html:text property="surtypeOfApp" size="25"  maxlength="75"   onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"  />
                            </td>

                            <td style="text-align: left">Organization<font color="red"><b>* </b></font></td>
                            <td  >
                                <html:select property="surOrganization" style="width:200px;" >
                                    <html:option value="">--SELECT--</html:option>
                                    <html:option value="SERP">SERP</html:option>
                                    <html:option value="Disability welfare department">Disability welfare department</html:option>
                                    <html:option value="SSA">SSA</html:option>
                                    <html:option value="NGO">NGO</html:option>
                                    <html:option value="Others">Others</html:option>


                                </html:select></td>
                        </tr>
                        <tr id="suryes1" style="display: none;">

                            <td style="text-align: left">Date of Surgery<font color="red"><b>*</b></font></td>
                            <td colspan="3">
                                <html:text property="surdate"  readonly="true" />
                                <a  href="javascript:show_calendar('forms[0].surdate');" onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>

                        </tr>

                        <tr>
                            <th style="text-align: left" colspan="4">5. PMJDY A/C Details (Prime Minister Jan Dhan Yojana)?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr style="vertical-align: top">

                            <td colspan="4">
                                <html:radio  property="pRadio" styleId="11" value ="Covered" onclick="changeSelection(this.value);"  style="width:30px" >Covered</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio  property="pRadio" styleId="12"  value ="NotCovered" onclick="changeSelection(this.value);"  style="width:30px" >Not Covered</html:radio>
                            </td>

                        </tr>




                        <tr  id="pno" style="display: none;"   >
                            <td style="text-align: left">
                                Reason <font color="red"><b>* </b></font></td>
                            <td colspan="3" valign="bottom" >
                                <html:textarea property="pReason" rows="5" cols="30" onkeypress="return space(event,this)"
                                               onkeydown="textCounter(this,document.forms[0].reasonlen3,200)"
                                               onkeyup="textCounter(this,document.forms[0].reasonlen3,200)" style="width:220px"/>
                                <input readonly type="text" name="reasonlen3" size="3" maxlength="3" value="200" style="width:30px">

                            </td>
                        </tr>


                        <tr id="pyes" style="display: none;"  >

                            <td style="text-align: left">Account number<font color="red"><b>* </b></font></td>
                            <td >
                                <html:text property="pAccNo" size="25"  maxlength="50"   onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"  />
                            </td>
                            <td style="text-align: left">Bank<font color="red"><b>* </b></font></td>
                            <td >
                                <html:text property="pBank" size="25"  maxlength="50"   onkeydown="return isAlpha(event.keyCode,pBank);" onkeypress="return space(event,this)"  />
                            </td>

                        </tr>

                        <tr id="pyes1" style="display: none;"  >
                            <td style="text-align: left">Branch<font color="red"><b>* </b></font></td>
                            <td >
                                <html:text property="pBranch" size="25"  maxlength="50"   onkeydown="return isAlpha(event.keyCode,pBranch);" onkeypress="return space(event,this)"  />
                            </td>
                            <td style="text-align: left">IFSC code</td>
                            <td >
                                <html:text property="pIFSCCode" size="25"  maxlength="50"   onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"  />
                            </td>
                        </tr>


                        <tr>
                            <th style="text-align: left" colspan="4">6. AASARA Details?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr style="vertical-align: top">

                            <td colspan="4">
                                <html:radio  property="aasaraRadio" styleId="13" value ="Sanctioned" onclick="changeSelection(this.value);"   style="width:30px">Sanctioned</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio  property="aasaraRadio" styleId="14"  value ="NotSanctioned" onclick="changeSelection(this.value);"  style="width:30px" >Not Sanctioned</html:radio>
                            </td>

                        </tr>




                        <tr  id="ano" style="display: none;"   >
                            <td style="text-align: left">
                                Remarks <font color="red"><b>* </b></font></td>
                            <td colspan="3" valign="bottom" >   <html:textarea property="aRemarks" rows="5" cols="30"
                                           onkeypress="return space(event,this)"
                                           onkeydown="textCounter(this,document.forms[0].reasonlen4,200)"
                                           onkeyup="textCounter(this,document.forms[0].reasonlen4,200)" style="width:220px"/>
                                <input readonly type="text" name="reasonlen4" size="3" maxlength="3" value="500" style="width:30px">

                            </td>
                        </tr>
                        <%-- <tr  id="ano" style="display: none;"   >
          <td style="text-align: left">
              Remarks <font color="red"><b>* </b></font></td>--%>
                        <%-- <td colspan="3" valign="bottom" >
                             <html:textarea property="aRemarks" rows="5" cols="30"
                                        onkeypress="return space(event,this)"
                                        onkeydown="textCounter(this,document.forms[0].reasonlen4,50)"
                                        onkeyup="textCounter(this,document.forms[0].reasonlen4,50)"/>
                             <input readonly type="text" name="reasonlen4" size="3" maxlength="3" value="500" style="width:30px">

                        </td>--%>

                        <%--  <td>
                              <html:textarea property="aRemarks" rows="5" cols="50" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].reasonlen4,50)" onkeyup="textCounter(this,document.forms[0].reasonlen4,50)"/>
                              <input readonly type="text" name="reasonlen4" size="3" maxlength="3" value="50">
                          </td>
                          </tr>--%>



                        <%--<tr id="ayes" style="display: none;"  >

                        <td style="text-align: left">Pension ID<font color="red"><b>* </b></font></td>
                        <td colspan="3">
                            <input type="text" onkeypress="return isNumberKey(event)" maxlength="10" name="aasaraPensionId"/>
                        </td>

                    </tr>--%>


                        <tr>
                            <th style="text-align: left">7.SKS ID</th>
                            <td colspan="3"><input type="text" name="serveid"  maxlength="50" onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"></td>

                        </tr>


                        <tr >
                            <th style="text-align: left" >8.Validation Done By<font color="red"><b>* </b></font></th>
                            <td  colspan="3"><input type="text" name="serveDoneBy" maxlength="50" onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"></td>



                        </tr>

                        <tr>
                            <th style="text-align: left">9.Designation<font color="red"><b>* </b></font></th>
                            <td  colspan="3"><input type="text" name="designation" maxlength="50" onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"></td>


                        </tr>

                        <tr>

                            <th style="text-align: left">10.Validation Done Date<font color="red"><b>*</b></font></th>
                            <td colspan="3">
                                <html:text property="recivedDate"  readonly="true" />
                                <a  href="javascript:show_calendar('forms[0].recivedDate');" onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>

                        </tr>
                        <tr>
                            <th align="center" colspan="8">
                                <html:submit value="Submit" styleClass="button"/>

                            </th>
                        </tr>

                    </table>

                </logic:present>





                <% } else {%>



                <logic:present name="SADAREMIDValidDetails">
                    <br>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                        <tr>  <td align="left">

                                <a href="captureDeadDetails.do" > Back</a>
                            </td>
                        </tr>
                    </table>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                        <th colspan="8">
                            Personal Details
                        </th>
                        <tr><td>SADAREM ID</td>
                            <td>Full Name</td>
                            <td>Relation Name</td>
                            <td>Gender</td>
                            <td>Habitation</td>
                            <td>Village</td>
                            <td>Mandal</td>
                            <td>Contact No</td>
                        </tr>
                        <logic:iterate id="modify" name="SADAREMIDValidDetails" scope="request">
                            <input type="hidden" name="personcode" value="${modify.personCode}"/>

                            <tr>
                                <td>${modify.personCode}</td>
                                <td>${modify.name}</td>
                                <td>${modify.relationName}</td>
                                <td>${modify.gender}</td>
                                <td>${modify.habitation}</td>
                                <td>${modify.village}</td>
                                <td>${modify.mandal}</td>
                                <td>${modify.phoneno}</td>
                            </tr>
                        </logic:iterate>
                    </table>
                    <br><br>
                    <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">

                        <th colspan="4">
                            Data Validation Details
                        </th>

                        <%--<tr>
                        <table align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                        --%>
                        <tr>
                            <th style="text-align: left" colspan="4">1. AADHAR Details Exist?<font color="red"><b>* </b></font></th>
                        </tr>
                        <tr>

                            <td colspan="4">
                                <html:radio  property="aadharRadio"  styleId="1" value ="Exist" onclick="changeSelection(this.value);" style="width:30px">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <%if (readonly == null) {%>
                                <html:radio  property="aadharRadio" styleId="2"  value ="NotExist" onclick="changeSelection(this.value);"  style="width:30px" >No</html:radio>
                                <%}%>
                            </td>

                        </tr>

                        <tr id="aadharNo" style="display: none;"  >

                            <td style="text-align: left"> Aadhar Card No<font color="red"><b>* </b></font></td>
                            <%if (readonly != null) {%>
                            <td  >
                                <input name="aadharCardNo"  style="width:200px"  readonly="true" value='<%=readonly%>'/>

                            </td>
                            <%} else {%>
                            <td  >
                                <html:text property="aadharCardNo" onkeypress="return onlyNumbers();"  maxlength="12" style="width:200px"  onkeyup="validateAadhar(this.value);"/>
                            </td>
                            <%}%>
                            <td colspan="" >
                                <div id="data"></div>
                                <div id="loadImg"></div>
                            </td>

                        </tr>
                        <tr  id="aadharRemarks" style="display: none;"   >
                            <td style="text-align: left">
                                Remarks <font color="red"><b>* </b></font></td>
                            <td colspan="3" valign="bottom" >


                                <html:textarea property="aadharRemarks" rows="5" cols="30"
                                               onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,50)" onkeyup="textCounter(this,document.forms[0].remLen1,50)"style="width:220px"/>
                                <input readonly type="text" name="remLen1" size="3" maxlength="3" value="200" style="width:30px">

                            </td>
                        </tr>

                        <tr>
                            <th style="text-align: left">2.SKS ID</th>
                            <td colspan="3"><input type="text" name="serveid"  maxlength="50" onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"></td>

                        </tr>


                        <tr >
                            <th style="text-align: left" >3.Validation Done By<font color="red"><b>* </b></font></th>
                            <td  colspan="3"><input type="text" name="serveDoneBy" maxlength="50" onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"></td>



                        </tr>

                        <tr>
                            <th style="text-align: left">4.Designation<font color="red"><b>* </b></font></th>
                            <td  colspan="3"><input type="text" name="designation" maxlength="50" onkeydown="return isAlpha(event.keyCode,typeOfApp);" onkeypress="return space(event,this)"></td>


                        </tr>

                        <tr>

                            <th style="text-align: left">5.Validation Done Date<font color="red"><b>*</b></font></th>
                            <td colspan="3">
                                <html:text property="recivedDate"  readonly="true" />
                                <a  href="javascript:show_calendar('forms[0].recivedDate');" onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>

                        </tr>
                        <tr>
                            <th align="center" colspan="8">
                                <html:submit value="Submit" styleClass="button"/>

                            </th>
                        </tr>

                    </table>

                </logic:present>
                <%}%>

            </logic:notPresent>

        </html:form>
    </body>


    <script src="./DisabilityUITG/js/jquery-min.js"></script>
    <script language="javascript" src="./DisabilityUITG/js/GenerateAadharCardValidation.js"></script>


    <%if (validateEligibleDetails != null && validateEligibleDetails.equalsIgnoreCase("Eligible")) {%>
    <logic:present name ="shgFlag">
        <script>
            document.getElementById('shgyes').style.display='';
            document.getElementById('shgyes1').style.display='';
            document.getElementById('shg').style.display='';

        </script>
    </logic:present>
    <logic:present name="existFlag">
        <script>
            document.getElementById('aadharNo').style.display='';
        </script>
    </logic:present>
    <logic:present name="notExist">
        <script>
            document.getElementById('aadharRemarks').style.display='';
        </script>
    </logic:present>
    <%} else {%>



    <logic:present name="existFlag">
        <script>
            document.getElementById('aadharNo').style.display='';
        </script>
    </logic:present>
    <logic:present name="notExist">
        <script>
            document.getElementById('aadharRemarks').style.display='';
        </script>
    </logic:present>

    <%}%>



    <script>
        // The below two methods are for getting the table data

        function validateAadhar(aadharId) {

            document.getElementById("data").innerHTML="";
            document.getElementById("loadImg").innerHTML="";
            if(aadharId.toString().length=="12") {
                if(validateVerhoeff(aadharId)==true){
                    document.getElementById("data").innerHTML="";
                    document.getElementById("loadImg").innerHTML="<img src=\"./DisabilityUITG/images/ajax-loader.gif\">Aadhar Number Validating Please Wait.....";
                    $.ajax({
                        url : "insertPartAaction.do?insertPersonalDetails=validateAadharNumberWithAadharDB",
                        type: "GET",
                        data : "aadharNo="+aadharId+"&personcode="+document.forms[0].personcode.value,
                        success: function(data, textStatus, jqXHR)
                        {
                            if(data=="success") {
                                //  document.getElementById("subButton").disabled=false;
                                $("#data").html("<font color=green size=2><b>Given Aadhar Number Validated Successfully.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                            }
                            if(data == "mapping") {
                                // document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Given Aadhar Number is Already Mapped to Another SADAREM ID.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                            if(data == "ap") {
                                // document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Aadhar Number No belongs to Andhra Pradesh State.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                            if(data == "invalid") {
                                // document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Aadhar Number is In-Valid.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                            if(data == "rejected") {
                                // document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Aadhar Number is Cancelled/Rejected.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                            if(data == "down") {
                                // document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Aadhar Webservice is down. Please try after some time.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                            if(data == "fail") {
                                //  document.getElementById("subButton").disabled=true;
                                $("#data").html("<font color=red size=2><b>Given Aadhar Number is Not Valid Number.</b></font>");//data - response from server
                                document.getElementById("loadImg").innerHTML="";
                                document.forms[0].elements['aadharCardNo'].value="";
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            alert(errorThrown);
                        }
                    });
                }else{

                    document.forms[0].aadharCardNo.value="";
                    document.forms[0].aadharCardNo.focus();
                }
            }<%--else if(aadharId.toString().length>"0") {
                //  document.getElementById("subButton").disabled=true;
                $("#data").html("<font color=red size=2><b>Given Aadhar Number is Not Valid Number. Aadhar Number should be 12 digits</b></font>");//data - response from server
                document.getElementById("loadImg").innerHTML="";
                document.forms[0].elements['aadharCardNo'].value="";
            }--%>
                }
    </script>
</html>
