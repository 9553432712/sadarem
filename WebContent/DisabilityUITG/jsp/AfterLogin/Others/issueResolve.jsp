<%-- 
    Document   : issueResolve
    Created on : Nov 29, 2012, 4:36:00 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="org.bf.disability.Constants.CommonConstants"%>
<%
            String requestRaiseId = null;
            if (request.getAttribute("requestRaiseId") != null) {
                requestRaiseId = (String) request.getAttribute("requestRaiseId");
            }
            String sadaremId = null;
            String statusCategoryId = null;
            if (request.getAttribute("statusCategoryId") != null) {
                statusCategoryId = (String) request.getAttribute("statusCategoryId");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    
        <script>
            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }
             function rationCardFlag(id,flag){
                var url="issueRaiseApproval.do?mode=getRationCardDetailsForNotOpen&rationCard="+id+"&flag="+flag+"";
                window.open(url,"Page",'width=1000px,height=500px,resizable=yes,scrollbars=yes');
            }
            function issueResloveDetails(){
            <%--
                 if(document.forms[0].elements['comments'].value==""){
                     alert("Please Enter SADAREM Development Team!");
                     document.forms[0].elements['comments'].focus();
                     return false;
                 }else if(document.forms[0].elements['resolvedCnt'].value==""){
                     alert("Please Enter Issues Resolved!");
                     document.forms[0].elements['resolvedCnt'].focus();
                     return false;
                 }else if(document.forms[0].elements['time'].value==""){
                     alert("Please Enter  Resolved Time!");
                     document.forms[0].elements['time'].focus();
                     return false;
                 }--%>

            <%--  else {--%>
                    document.forms[0].mode.value ="updateIssueResloveDetails";
                    document.forms[0].submit();
            <%-- }--%>
                }

                 function pensionDetailsFlag(pensionId,districtId){
                var url="issueRaiseApproval.do?mode=getPensionCardNotOpeningList&pensionId="+pensionId+"&districtId="+districtId+"";
                window.open(url,"Page",'width=1000px,height=500px,resizable=yes,scrollbars=yes');
            }

                function onlyNumbers(evt)
                {
                    var e = event || evt; // for trans-browser compatibility
                    var charCode = e.which || e.keyCode;

                    if (charCode > 31 && (charCode < 48 || charCode > 57))
                        return false;

                    return true;

                }
                function onlyNumbers1(evt) {
                    var charCode = (evt.which) ? evt.which : event.keyCode
                    if ((charCode < 46 || charCode > 57))
                        return false;

                    return true;
                }

                function issueApprovalDetails(){
                    if(document.forms[0].elements['rejectComment'].value==""){
                        alert("Please Enter Description For Comments!");
                        document.forms[0].elements['rejectComment'].focus();
                        return false;
                    }else{
                        document.forms[0].mode.value="issueApprovalDetails";
                        document.forms[0].submit();

                    }

                }
                function issueRejectDetails(){
                    if(document.forms[0].elements['rejectComment'].value==""){
                        alert("Please Enter Description For Comments!");
                        document.forms[0].elements['rejectComment'].focus();
                        return false;
                    }else{
                        document.forms[0].mode.value="issueRejectDetails";
                        document.forms[0].submit();
                    }
                
                }
                function issueHoldDetails(){
                    if(document.forms[0].elements['rejectComment'].value==""){
                        alert("Please Enter Description For Comments!");
                        document.forms[0].elements['rejectComment'].focus();
                        return false;
                    }else{
                        document.forms[0].mode.value="issueHoldDetails";
                        document.forms[0].submit();
                    }
                
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
    <body >

        <html:form action="/issueRaiseApproval" enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="requestModeRaiseId" value="<%=requestRaiseId%>"/>
            <html:hidden property="statusCategoryId" value="<%=statusCategoryId%>"/>


            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>

            <%--<logic:present name="msgUpload">
                <center><font color="blue">${msgUpload}</font></center>
           </logic:present>--%>
            <logic:present name="approvalStatus">
                <p id="succmsg">${approvalStatus}</p>
            </logic:present>

            <logic:present name="reject">
                <p id="succmsg">${reject}</p>
            </logic:present>



            <logic:notEmpty name="requestRaiseList">

                <p>
                    Issue Resolution Details
                </p>
                <table border="0" cellspacing="1" cellpadding="0" width="50%" align="center" class="inputform">

                    <logic:iterate  name="requestRaiseList" id="row" scope="request">
                        <tr><th  >Issue ID :${row.requestId} </th>
                            <th align="right">
                    <img src="./DisabilityUITG/images/close.jpg" onclick="javascript:window.close();"/>
                </th>
                        </tr>

                        <%--<logic:present name="cat35Id">
                            <tr>
                                <td  >
                                    Medical Board :
                                </td>
                                <td class="textarea">
                                    <html:text property="resolveCampName" value="${row.campName}" readonly="true" style="width:256px;"/>
                                    <html:hidden property="resloveCampId" value="${row.campId}"/>
                                </td>
                            </tr>
                        </logic:present>--%>
                        <tr>
                            <td  >
                                Category :
                            </td>
                            <td  >
                                <html:text property="resolvecategoryName" value="${row.categoryName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <%if ( statusCategoryId.equals(CommonConstants.INVALIDPERSONCODE) || statusCategoryId.equals(CommonConstants.NODATA) || statusCategoryId.equals(CommonConstants.TECHNICALDIFFICULTIES)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.BLANKPAGES) || statusCategoryId.equals(CommonConstants.OTHERS)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>
                        <tr>
                            <td  >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.FORGOTPASSWORD)) {%>
                        <tr>
                            <td  >
                                User Name :
                            </td>
                            <td >
                                <html:text property="resolveUserName" value="${row.username}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.PENSIONIDNOTOPENING)) {%>
                        <tr>
                            <td  >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                &nbsp;
                            </td>
                            <td   colspan="4">
                                <a href="#" onclick="return pensionDetailsFlag('${row.pensionId}','${row.districtId}');">View Pension Details</a>
                            </td>

                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.NAME)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>

                         <tr>
                             <td>
                               RationCard No :
                            </td>
                            <td>
                                <html:text property="resolveRationCardNo" value="${row.rationCardNoData}" readonly="true" style="width=180px"/>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                Name :
                            </td>
                            <td>
                                <html:text property="existingName" value="${row.existingName}" readonly="true" style="width=180px"/>
                            </td>
                        </tr> 
                        <tr>
                            <td  >
                                Disabled Names :
                            </td>
                            <td>
                                <html:text property="resloveDisName" value="${row.disName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <tr>
                            <td  >
                                To be Modified Name :
                            </td>
                            <td>
                                <html:text property="resolveName" value="${row.name}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Pension Phase :
                            </td>
                            <td>
                                <html:text property="reslovePensionPhase" value="${row.pensionPhase}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.RELATIONNAME)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>
                         <tr>
                             <td>
                               RationCard No :
                            </td>
                            <td>
                                <html:text property="resolveRationCardNo" value="${row.rationCardNoData}" readonly="true" style="width=180px"/>

                            </td>
                        </tr>

                        <tr>
                            <td>
                                Relation Name :
                            </td>
                            <td>
                                <html:text property="existingRelationName" value="${row.existingRelationName}" readonly="true" style="width=180px"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Disabled Names :
                            </td>
                            <td>
                                <html:text property="resloveDisRelationName" value="${row.disRelationName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>


                        <tr>
                            <td  >
                                To be Modified Relation Name :
                            </td>
                            <td>
                                <html:text property="resolverelationName" value="${row.relationName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Pension Phase :
                            </td>
                            <td>
                                <html:text property="reslovePensionPhase" value="${row.pensionPhase}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>


                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.SURNAME)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                       <tr>
                             <td>
                               RationCard No :
                            </td>
                            <td>
                                <html:text property="resolveRationCardNo" value="${row.rationCardNoData}" readonly="true" style="width=180px"/>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                SurName :
                            </td>
                            <td>
                                <html:text property="existingSurName" value="${row.existingSurName}" readonly="true" style="width=180px"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Disabled Names :
                            </td>
                            <td>
                                <html:text property="resloveDisSurName" value="${row.disSurName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                To be Modified SurName :
                            </td>
                            <td>
                                <html:text property="resolvesurName" value="${row.surName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Pension Phase :
                            </td>
                            <td>
                                <html:text property="reslovePensionPhase" value="${row.pensionPhase}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>


                        <%}%>
						 <%if (statusCategoryId.equals(CommonConstants.ADDRESSCHANGEPHASEIII)) {%>
						 
						  <html:hidden property="mandalid"  value="${row.mandalid}"/>
						  <html:hidden property="villageid" value="${row.villageid}"/>
						  <html:hidden property="habitation_id" value="${row.habcode}"/>
						  <html:hidden property="habid" value="${row.habid}"/>
						 
						   <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="sadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                        <td colspan="2">
                        <table border="0" cellspacing="1" cellpadding="0"   align="center" class="inputform">
                           <tr >
                           <th width="55%"> Property</th><th>Existing</th><th>To be modified</th> 
                           </tr>
                         <tr>
                           <td>
                               Mandal  
                            </td>
                            <td>
                                <html:text property="mandalname" value="${row.mandalname}" readonly="true" style="width=180px"/>

                            </td>
                             <td>
                                <html:text property="newmandalname" value="${row.newmandalname}" readonly="true" style="width=180px"/>

                            </td>
                        </tr>
                          <tr>
                            <td>
                                Village  
                            </td>
                            <td>
                                <html:text property="villagename" value="${row.villagename}" readonly="true" style="width=180px"/>
                            </td>
                              <td>
                                <html:text property="newvillagename" value="${row.newvillagename}" readonly="true" style="width=180px"/>
                            </td>
                        </tr>
                          <tr>
                            <td  >
                               Habitation  
                            </td>
                            <td>
                                <html:text property="habname" value="${row.habname}" readonly="true" style="width:256px;"/>
                            </td>
                              <td>
                                <html:text property="newhabname" value="${row.newhabname}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                         <tr>
                            <td  >
                               House No.  
                            </td>
                            <td>
                                <html:text property="houseNo" value="${row.houseno}" readonly="true" style="width:256px;"/>
                            </td>
                              <td>
                                <html:text property="newhouseno" value="${row.newhouseno}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        
                         <tr>
                            <td  >
                               PIN Code  
                            </td>
                            <td>
                                <html:text property="pin" value="${row.pincode}" readonly="true" style="width:256px;"/>
                            </td>
                              <td>
                                <html:text property="newpincode" value="${row.newpincode}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        
                        </table>
                                                
                        
                        </td>
                        </tr>
                        
                        
                        
                        
                        
                        
                         
                        
						 <%} %>
                        <%if (statusCategoryId.equals(CommonConstants.MEDICALBOARDCHANGE)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Medical Board :
                            </td>
                            <td class="textarea">
                                <html:text property="resolveCampName" value="${row.campName}" readonly="true" style="width:256px;"/>

                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Medical Address :
                            </td>
                            <td>
                                <html:text property="resolveMedicalAddress" value="${row.medicalAddress}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                User Name :
                            </td>
                            <td >
                                <html:text property="resolveUserName" value="${row.username}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <%}%>

                        <%--  <logic:present name="cat24Id">
                              <tr>
                                  <td  >
                                      Pension ID :
                                  </td>
                                  <td>
                                      <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:256px;"/>
                                  </td>
                              </tr>
                          </logic:present>--%>

                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {%>
                        <tr>
                            <td  >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:256px;"/>
                            </td>
                            <td>
                                <html:text property="resloveNewDistrict_id" value="${row.newDistrict_id}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDNOTOPEN)) {%>
                        <tr>
                            <td  >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <tr>
                            <td  >
                                Member Name :
                            </td>
                            <td>
                                <html:text property="resolveMemberName" value="${row.memberName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                RationCard serialNo :
                            </td>
                            <td>
                                <html:text property="resolveRationCardserialNo" value="${row.rationCardserialNo}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr align="center" >
                            <td  colspan=4">
                                <a href="#" onclick="return rationCardFlag('${row.rationCard}','1');">View RationCard Details</a>

                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {%>
                        <tr>
                            <td  >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>

                        <td  >
                            Name :
                        </td>
                        <td >
                                <html:text property="nameForRationCardSerialNoCat" value="${row.nameForRationCardSerialNoCat}" readonly="true" style="width=180px"/>
                            </td>


                        <tr>
                            <td  >
                                Relation Name :
                            </td>
                            <td>
                                <html:text property="resloveDisRelationName" value="${row.disRelationName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                RationCard serialNo :
                            </td>
                            <td>
                                <html:text property="existingRationCardSerialNo" value="${row.existingRationCardSerialNo}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                To be Modified RationCard serialNo :
                            </td>
                            <td>
                                <html:text property="resolveRationCardserialNo" value="${row.rationCardserialNo}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>

                        <tr align="center" >
                            <td  colspan=4">
                                <a href="#" onclick="return rationCardFlag('${row.rationCard}','2');">View RationCard Details</a>

                            </td>
                        </tr>

                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI) || statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII) || statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>
                        <tr>
                            <td  >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.VICEVERSA)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Name :
                            </td>
                            <td>
                                <html:text property="resolveName" value="${row.name}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Relation Name :
                            </td>
                            <td>
                                <html:text property="resolverelationName" value="${row.relationName}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>


                        <tr>
                            <td  >
                                New SADAREMID :
                            </td>
                            <td>
                                <html:text property="resolveNewSadadremId" value="${row.newSadadremId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                New PensionID :
                            </td>
                            <td>
                                <html:text property="resolveNewPensionId" value="${row.newpensionId}" readonly="true" style="width:256px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                Document View
                            </td>
                            <td>
                                <a href ='issueRaiseApproval.do?mode=getPhotoDetails&requestId=${row.requestId}'>View</a>
                            </td>
                        </tr>

                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.DATEOFISSUE)) {%>
                        <tr>
                            <td  >
                                SADAREM ID :
                            </td>
                            <td>
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:256px;"/>
                            </td>

                        </tr>
                        <tr>
                            <td  >
                                Date Of Issue:
                            </td>
                            <td>
                                <html:text property="date" value="${row.date}" readonly="true" style="width:256px;"/>
                                <html:hidden property="resolveDateOfIssue" value="${row.dateOfIssue}" style="width:256px;"/>

                            </td>
                        </tr>
                        <%}%>
                        <tr>
                            <td  >
                                Description :
                            </td>
                            <td>
                                <html:textarea property="resolveDescription" rows="5" cols="30"  style="height:75px" value="${row.description}" readonly="true"/>
                            </td>

                        </tr>
                        <tr>
                            <td  >
                                CampIncharge MobileNo :
                            </td>
                            <td>
                                ${row.mobileNo}
                            </td>

                        </tr>

                        <tr>
                            <td  >
                                Description For Comments :
                            </td>
                            <td >
                                <html:textarea property="rejectComment" rows="5" cols="30" onkeypress="return space(event,this)" onkeydown="textCounter(this,document.forms[0].remLen1,300)" onkeyup="textCounter(this,document.forms[0].remLen1,300)" style="width:256px;" rows="5" cols="50"/>
                                <br><input readonly type="text" name="remLen1" size="3" maxlength="3" value="300" style="width:30px">
                            </td>

                        </tr>

                    </logic:iterate>

                    <tr>
                        <th colspan="2">
                            <html:button property="but" value="Approval" onclick="issueApprovalDetails();"  />

                            <html:button property="but" value="Reject" onclick="issueRejectDetails();"  />

                            <html:button property="but" value="Hold" onclick="issueHoldDetails();"  />
                        </th>
                    </tr>
                </table>              

            </logic:notEmpty>
        <tr>

            <%--<tr>
                <td>
                    <table align="center" cellspacing="1" border="0" cellpadding="4" width="50%" class="">

                    </table>
                </td>
            </tr>--%>

        </html:form>

    </body>
</html>
