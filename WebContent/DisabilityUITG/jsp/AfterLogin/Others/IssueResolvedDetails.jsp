<%--
    Document   : IssueResolvedDetails
    Created on : Mar 19, 2013, 4:06:26 PM
    Author     : 707797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="org.bf.disability.Constants.CommonConstants"%>
<%
            String statusCategoryId = null;
            String requestRaiseId = (String) request.getAttribute("requestRaiseId");

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
            function issueResloveDetails(){

                if(document.forms[0].elements['comments'].value==""){
                    alert("Please Enter SADAREM Development Team!");
                    document.forms[0].elements['comments'].focus();
                    return false;
                }

                else {
                    document.forms[0].mode.value ="updateIssueResloveDetails";
                    document.forms[0].submit();
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
        </script>


    </head>
    <body>


        <html:form action="/issueRaiseApproval" enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="requestModeRaiseId" value="<%=requestRaiseId%>"/>

            <logic:present name="msg">
                <p id="succmsg">${msg}</p>
            </logic:present>
            <logic:present name="error">
                <p id="errmsg">${error}</p>
            </logic:present>
            <p> Issue Resolved Details

                <logic:notEmpty name="requestRaiseList">
                    <br>

                <table  align="center" cellspacing="1" border="0" cellpadding="0" width="60%" class="inputform">

                    <logic:iterate  name="requestRaiseList" id="row" scope="request">
                        <tr><th  colspan="2" >Raise Details  IssueID :${row.requestId}</th>
                        </tr>
                        <tr>
                            <td >
                                Category :
                            </td>
                            <td  >
                                <html:text property="resolvecategoryName" value="${row.categoryName}" readonly="true" style="width:320px"/>
                            </td>
                        </tr>


                        <%if (statusCategoryId.equals(CommonConstants.ADDRESSCHANGEPHASEIII) || statusCategoryId.equals(CommonConstants.INVALIDPERSONCODE) || statusCategoryId.equals(CommonConstants.NODATA) || statusCategoryId.equals(CommonConstants.TECHNICALDIFFICULTIES)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td>
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.BLANKPAGES) || statusCategoryId.equals(CommonConstants.OTHERS)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.FORGOTPASSWORD)) {%>
                        <tr>
                            <td >
                                User Name :
                            </td>
                            <td >
                                <html:text property="resolveUserName" value="${row.username}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Password :
                            </td>
                            <td >
                                Sadarem@123
                            </td>
                        </tr>


                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.PENSIONIDNOTOPENING)) {%>
                        <tr>
                            <td >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.NAME)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Existing Name :
                            </td>
                            <td>
                                <html:text property="existingName" value="${row.existingName}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                To be Modified Name :
                            </td>
                            <td>
                                <html:text property="resolveName" value="${row.name}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.RELATIONNAME)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>

                        <tr>
                            <td >
                                Relation Name :
                            </td>
                            <td>
                                <html:text property="existingRelationName" value="${row.existingRelationName}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>

                        <tr>
                            <td >
                                To be modified Relation Name :
                            </td>
                            <td>
                                <html:text property="resolverelationName" value="${row.relationName}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>

                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.SURNAME)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Existing SurName :
                            </td>
                            <td>
                                <html:text property="existingSurName" value="${row.existingSurName}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                To be Modified SurName :
                            </td>
                            <td>
                                <html:text property="resolvesurName" value="${row.surName}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.MEDICALBOARDCHANGE)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Medical Address :
                            </td>
                            <td>
                                <html:text property="resolveMedicalAddress" value="${row.medicalAddress}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                User Name :
                            </td>
                            <td >
                                <html:text property="resolveUserName" value="${row.username}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>


                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {%>
                        <tr>
                            <td >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Distrtict Name :
                            </td>
                            <td>
                                <html:text property="resloveNewDistrict_id" value="${row.newDistrict_id}" readonly="true" style="width:250px"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDNOTOPEN)) {%>
                        <tr>
                            <td >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>

                        <tr>
                            <td >
                                Member Name :
                            </td>
                            <td>
                                <html:text property="resolveMemberName" value="${row.memberName}" readonly="true" style="width:250px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                RationCard serialNo :
                            </td>
                            <td>
                                <html:text property="resolveRationCardserialNo" value="${row.rationCardserialNo}" readonly="true" style="width:100px"/>
                            </td>
                        </tr>
                        <%}%>


                        <%if (statusCategoryId.equals(CommonConstants.RATIONCARDSERIALNUMBERCHANGE)) {%>
                        <tr>
                            <td >
                                RationCard No :
                            </td>
                            <td>
                                <html:text property="resolverationCard" value="${row.rationCard}" readonly="true" style="width:100px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                RationCard serialNo :
                            </td>
                            <td>
                                <html:text property="resolveRationCardserialNo" value="${row.rationCardserialNo}" readonly="true" style="width:100px"/>
                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEI) || statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEII) || statusCategoryId.equals(CommonConstants.REQUESTFORCHANGEPHASEIIITOPHASEIV)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:100px"/>
                            </td>
                        </tr>
                        <%}%>

                        <%if (statusCategoryId.equals(CommonConstants.VICEVERSA)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Pension ID :
                            </td>
                            <td >
                                <html:text property="resolvepensionId" value="${row.pensionId}" readonly="true" style="width:100px"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                New SADAREMID :
                            </td>
                            <td>
                                <html:text property="resolveNewSadadremId" value="${row.newSadadremId}" readonly="true" style="width:200px"/>
                            </td>
                        </tr>
                        <%}%>
                        <%if (statusCategoryId.equals(CommonConstants.DATEOFISSUE)) {%>
                        <tr>
                            <td >
                                SADAREM ID :
                            </td>
                            <td >
                                <html:text property="resolvesadaremId" value="${row.sadaremId}" readonly="true" style="width:200px"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                Date Of Issue:
                            </td>
                            <td >
                                <html:text property="date" value="${row.date}" readonly="true" style="width:100px"/>
                            </td>

                        </tr>
                        <%}%>
                        <tr>
                            <td >
                                Description :
                            </td>
                            <td>
                                <html:textarea property="resolveDescription" rows="5" cols="30"   value="${row.description}" readonly="true"/>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                CampIncharge MobileNo :
                            </td>
                            <td>
                                ${row.mobileNo}
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Resolution Remarks :
                            </td>
                            <td>
                                <html:textarea property="comments" value="${row.comments}" readonly="true" rows="5" cols="30"/>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>

            </logic:notEmpty>
        </html:form>
    </body>
</html>
