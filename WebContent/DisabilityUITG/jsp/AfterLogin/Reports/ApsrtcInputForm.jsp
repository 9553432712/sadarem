<%-- 
    Document   : ApsrtcInputForm
    Created on : Aug 9, 2014, 1:07:55 PM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String personCode = null;
            if (request.getAttribute("personCode") != null) {
                personCode = request.getAttribute("personCode").toString();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/validation.js"></script>
        <script>
            function saveDetails(){
                if(document.forms[0].idNo.value==""){
                    alert("Please Enter ID NO");
                    document.forms[0].idNo.focus();
                    return false;
                }else if(document.forms[0].receiptDate.value==""){
                    alert("Please Enter Receipt Date");
                    document.forms[0].receiptDate.focus();
                    return false;
                }
            }
        </script>

    </head>
    <body>
        <html:form action="apsrtcCertificate.do?mode=storeCertificateDetails" method="post" onsubmit="return saveDetails()" >
            <input type="hidden" name="personcode" value="<%=personCode%>"/>

            <table align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
                <tr><td colspan="4" style="text-align: center;color: blue"><b>SADAREM ID:<%=personCode%></b></td></tr>
                <tr><td colspan="4"><b>Applicant Details:</b></td></tr>
                <tr>
                    <td >
                        ID NO: </td>
                    <td><html:text property="idNo" onkeypress="return space(event,this)"  maxlength="50"/>
                    </td>
                    <td>
                        Proof Enclosed: </td>
                    <td><html:text property="proofEnclosed" onkeypress="return space(event,this)"  maxlength="50"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        Pass Type Eligibility: </td>
                    <td><html:text property="passTypeEligibility" onkeypress="return space(event,this)"  maxlength="50"/>
                    </td>
                    <td> Escort For PHC:
                    </td>
                    <td><html:radio property="escortForPhc" value="Yes" style="width:15px"/>Yes <html:radio property="escortForPhc" value="No" style="width:15px"/>No
                    </td>
                </tr>
                <tr>
                    
                   <td >Type Of Pass:</td><td><html:text property="typeOfPass" onkeypress="return space(event,this)"  maxlength="50"/>
                   </td> <td colspan="2"></td>
                </tr>

                <tr>
                    <td colspan="4"><b>For Office Use Only:</b></td>
                </tr>
                <tr>
                    <td>MR No./Ack.Receipt No.: </td>
                    <td><html:text property="ackReceiptNo" onkeypress="return space(event,this)"  maxlength="50"/></td>
                    <td>Receipt Date:
                    </td>
                    <td><html:text property="receiptDate" readonly="true" size="12" />
                        <a  href="javascript:show_calendar('forms[0].receiptDate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>
                <tr>
                    <td>Bus Pass Ticket No: </td>
                    <td><html:text property="busPassTicketNo" onkeypress="return space(event,this)"  maxlength="50"/></td>
                    <td>Pass Amount Rs: </td>
                    <td><html:text property="passAmmountRs" onkeypress="return space(event,this)"  maxlength="50"/></td>
                </tr>
                <tr>
                    <td >Pass Issue Of: </td>
                    <td><html:text property="passIssuedOf" onkeypress="return space(event,this)"  maxlength="50"/>
                     </td> <td colspan="2"></td>
                </tr>
                <tr>
                    <th colspan="4">
                        <html:submit value="Submit" styleClass="button" />&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>
            </table>
        </html:form>
    </body>
</html>
