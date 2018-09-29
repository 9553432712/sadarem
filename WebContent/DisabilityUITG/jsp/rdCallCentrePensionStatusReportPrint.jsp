<%--
    Document   : rdCallCentrePensionStatusReportPrint
    Created on : May 3, 2013, 2:24:43 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        

        <%
                    ArrayList statusList = new ArrayList();

                    int totalRegistered = 0;
                    int rdcallCenterPending = 0;
                    int rdcallCenterClosed = 0;
                    int sadaremPending = 0;
                    int sadaremClosed = 0;
                    int newCertificate = 0;
                    int nameChange = 0;
                    int relationName = 0;
                    int dateOfBirth = 0;
                    int spellingMistake = 0;
                    int identificationMarksChange = 0;
                    int duplicateCertificate = 0;
                    int reassessment = 0;
                    int otherNeedsHIPhysicalRequirements = 0;
                    int renualCertificate = 0;
                    int assessementComptdNtGettingCertificate = 0;
                    int notGettingPenion = 0;
                    int notEligible = 0;
                    int abayastam = 0;
                    int iCFSDelation = 0;
                    int fingerPrintProblems = 0;
                    int aliveButpensionDead = 0;
                    int gettingDoublePension = 0;
                    int others = 0;
                    if (request.getAttribute("statusList") != null) {
                        statusList = (ArrayList) request.getAttribute("statusList");

                        Iterator iter = statusList.iterator();
                        while (iter.hasNext()) {
                            Map m = (Map) iter.next();
                            totalRegistered = totalRegistered + Integer.parseInt(m.get("totalRegistered").toString());
                            rdcallCenterPending = rdcallCenterPending + Integer.parseInt(m.get("rdcallCenterPending").toString());
                            rdcallCenterClosed = rdcallCenterClosed + Integer.parseInt(m.get("rdcallCenterClosed").toString());
                            sadaremPending = sadaremPending + Integer.parseInt(m.get("sadaremPending").toString());
                            sadaremClosed = sadaremClosed + Integer.parseInt(m.get("sadaremClosed").toString());
                            newCertificate = newCertificate + Integer.parseInt(m.get("NewCertificate").toString());
                            nameChange = nameChange + Integer.parseInt(m.get("NameChange").toString());
                            relationName = relationName + Integer.parseInt(m.get("RelationName").toString());
                            dateOfBirth = dateOfBirth + Integer.parseInt(m.get("DateOfBirth").toString());
                            spellingMistake = spellingMistake + Integer.parseInt(m.get("SpellingMistake").toString());
                            identificationMarksChange = identificationMarksChange + Integer.parseInt(m.get("IdentificationMarksChange").toString());
                            duplicateCertificate = duplicateCertificate + Integer.parseInt(m.get("DuplicateCertificate").toString());
                            reassessment = reassessment + Integer.parseInt(m.get("Reassessment").toString());
                            otherNeedsHIPhysicalRequirements = otherNeedsHIPhysicalRequirements + Integer.parseInt(m.get("PhysicalRequirements").toString());
                            renualCertificate = renualCertificate + Integer.parseInt(m.get("RenualCertificate").toString());
                            assessementComptdNtGettingCertificate = assessementComptdNtGettingCertificate + Integer.parseInt(m.get("AssessementComptdNtGettingCertificate").toString());
                            notGettingPenion = notGettingPenion + Integer.parseInt(m.get("NotGettingPenion").toString());
                            notEligible = notEligible + Integer.parseInt(m.get("NotEligible").toString());
                            abayastam = abayastam + Integer.parseInt(m.get("Abayastam").toString());
                            iCFSDelation = iCFSDelation + Integer.parseInt(m.get("ICFSDelation").toString());
                            fingerPrintProblems = fingerPrintProblems + Integer.parseInt(m.get("FingerPrintProblems").toString());
                            aliveButpensionDead = aliveButpensionDead + Integer.parseInt(m.get("AliveButpensionDead").toString());
                            gettingDoublePension = gettingDoublePension + Integer.parseInt(m.get("GettingDoublePension").toString());
                            others = others + Integer.parseInt(m.get("Others").toString());
                        }
                    }%>

        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>


    </head>

    <body onload="window.print();">

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>


    <logic:notEmpty name="statusList" scope="request">
        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
            <tr>
                <td valign="top">


                    <table align="center" cellspacing="1" border="0" cellpadding="4" width="100%" style="border:1px solid #fff">
                        <tr>
                            <td align="center" class="formhdbg"><b>S.No.</b></td>
                            <td align="center" class="formhdbg"><b>District</b></td>
                            <td align="center" class="formhdbg"><b>Total Registered</b></td>
                            <td align="center" class="formhdbg"><b>Rd Call Centre Pending</b></td>
                            <td align="center" class="formhdbg"><b>Rd Call Centre Closed</b></td>
                            <td align="center" class="formhdbg"><b>SADAREM Pending</b></td>
                            <td align="center" class="formhdbg"><b>SADAREM Closed</b></td>
                            <td align="center" class="formhdbg"><b>New Certificate</b></td>
                            <td align="center" class="formhdbg"><b>Name Change</b></td>
                            <td align="center" class="formhdbg"><b>Relation Name</b></td>
                            <td align="center" class="formhdbg"><b>Date of Birth</b></td>
                            <td align="center" class="formhdbg"><b>Spelling Mistake</b></td>
                            <td align="center" class="formhdbg"><b>Identification Marks Change</b></td>
                            <td align="center" class="formhdbg"><b>Duplicate Certificate</b></td>
                            <td align="center" class="formhdbg"><b>Reassessment</b></td>
                            <td align="center" class="formhdbg"><b>Physical Requirements</b></td>
                            <td align="center" class="formhdbg"><b>Renual Certificate</b></td>
                            <td align="center" class="formhdbg"><b>Assessement Completed But not Getting Certificate</b></td>
                            <td align="center" class="formhdbg"><b>Not Getting Penion</b></td>
                            <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Not Eligible)</b></td>
                            <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Abayastam)</b></td>
                            <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (ICFS Delation)</b></td>
                            <td align="center" class="formhdbg"><b>Having Certificate But not Getting Pension Like (Finger Print Problems)</b></td>
                            <td align="center" class="formhdbg"><b>Alive But In The Pension “Dead”</b></td>
                            <td align="center" class="formhdbg"><b>Getting Double Pension</b></td>
                            <td align="center" class="formhdbg"><b>Others</b></td>

                        </tr>


                        <% int i = 0;
                        %>
                        <logic:iterate id="rows" name="statusList" scope="request">
                            <tr>
                                <td  align="center" class="formaltbg"><%=++i%></td>

                                <td align="center" class="formaltbg">  ${rows.districtName}</td>
                                <td align="center" class="formaltbg">${rows.totalRegistered}</td>
                                <td align="center" class="formaltbg">${rows.rdcallCenterPending}</td>
                                <td align="center" class="formaltbg">${rows.rdcallCenterClosed}</td>
                                <td align="center" class="formaltbg">${rows.sadaremPending}</td>
                                <td align="center" class="formaltbg">${rows.sadaremClosed}</td>
                                <td align="center" class="formaltbg">${rows.NewCertificate}</td>
                                <td align="center" class="formaltbg">${rows.NameChange}</td>
                                <td align="center" class="formaltbg">${rows.RelationName}</td>
                                <td align="center" class="formaltbg">${rows.DateOfBirth}</td>
                                <td align="center" class="formaltbg">${rows.SpellingMistake}</td>
                                <td align="center" class="formaltbg">${rows.IdentificationMarksChange}</td>
                                <td align="center" class="formaltbg">${rows.DuplicateCertificate}</td>
                                <td align="center" class="formaltbg">${rows.Reassessment}</td>
                                <td align="center" class="formaltbg">${rows.PhysicalRequirements}</td>
                                <td align="center" class="formaltbg">${rows.RenualCertificate}</td>
                                <td align="center" class="formaltbg">${rows.AssessementComptdNtGettingCertificate}</td>
                                <td align="center" class="formaltbg">${rows.NotGettingPenion}</td>
                                <td align="center" class="formaltbg">${rows.NotEligible}</td>
                                <td align="center" class="formaltbg">${rows.Abayastam}</td>
                                <td align="center" class="formaltbg">${rows.ICFSDelation}</td>
                                <td align="center" class="formaltbg">${rows.FingerPrintProblems}</td>
                                <td align="center" class="formaltbg">${rows.AliveButpensionDead}</td>
                                <td align="center" class="formaltbg">${rows.GettingDoublePension}</td>
                                <td align="center" class="formaltbg">${rows.Others}</td>


                            </tr>
                        </logic:iterate>
                        <tr>
                            <td colspan="2" align="center" class="formhdbg"><b>Total</b></td>
                            <td align="center" class="formhdbg"><b><%=totalRegistered%></b></td>
                            <td align="center" class="formhdbg"><b><%=rdcallCenterPending%></b></td>
                            <td align="center" class="formhdbg"><b><%=rdcallCenterClosed%></b></td>
                            <td align="center" class="formhdbg"><b><%=sadaremPending%></b></td>
                            <td align="center" class="formhdbg"><b><%=sadaremClosed%></b></td>
                            <td align="center" class="formhdbg"><b><%=newCertificate%></b></td>
                            <td align="center" class="formhdbg"><b><%=nameChange%></b></td>

                            <td align="center" class="formhdbg"><b><%=relationName%></b></td>
                            <td align="center" class="formhdbg"><b><%=dateOfBirth%></b></td>
                            <td align="center" class="formhdbg"><b><%=spellingMistake%></b></td>
                            <td align="center" class="formhdbg"><b><%=identificationMarksChange%></b></td>
                            <td align="center" class="formhdbg"><b><%=duplicateCertificate%></b></td>
                            <td align="center" class="formhdbg"><b><%=reassessment%></b></td>
                            <td align="center" class="formhdbg"><b><%=otherNeedsHIPhysicalRequirements%></b></td>
                            <td align="center" class="formhdbg"><b><%=renualCertificate%></b></td>
                            <td align="center" class="formhdbg"><b><%=assessementComptdNtGettingCertificate%></b></td>
                            <td align="center" class="formhdbg"><b><%=notGettingPenion%></b></td>
                            <td align="center" class="formhdbg"><b><%=notEligible%></b></td>
                            <td align="center" class="formhdbg"><b><%=abayastam%></b></td>
                            <td align="center" class="formhdbg"><b><%=iCFSDelation%></b></td>
                            <td align="center" class="formhdbg"><b><%=fingerPrintProblems%></b></td>
                            <td align="center" class="formhdbg"><b><%=aliveButpensionDead%></b></td>
                            <td align="center" class="formhdbg"><b><%=gettingDoublePension%></b></td>
                            <td align="center" class="formhdbg"><b><%=others%></b></td>


                        </tr>
                    </table>
                </td></tr></table><br/><br>

    </logic:notEmpty>
</body>
</html>

