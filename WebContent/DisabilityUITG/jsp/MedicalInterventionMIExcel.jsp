<%-- 
    Document   : MedicalInterventionMIExcel
    Created on : Jan 4, 2011, 1:05:12 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%          String fromdate = (String) request.getParameter("fdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");
            String habitation_id = (String) request.getParameter("hID");
            String districtName = (String) request.getParameter("dName");
            String mandalName = (String) request.getParameter("mName");
            String villageName = (String) request.getParameter("vName");
            String habitationName = (String) request.getParameter("hName");
            ArrayList medicalInterventionMIList = new ArrayList();
            medicalInterventionMIList = (ArrayList) session.getAttribute("medicalInterventionMIList");
            int psychotherapyBehaviourBelowThreeYearsMITotal = 0, psychotherapyBehaviourAboveThreeYearsMITotal = 0, total = 0;
            int surgeryMITotal = 0, admissionPsychiatricTotal = 0;
            Iterator iter = medicalInterventionMIList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                psychotherapyBehaviourBelowThreeYearsMITotal = psychotherapyBehaviourBelowThreeYearsMITotal
                        + totalDTO.getPsychotherapyBehaviourBelowThreeYearsMI();
                psychotherapyBehaviourAboveThreeYearsMITotal = psychotherapyBehaviourAboveThreeYearsMITotal
                        + totalDTO.getPsychotherapyBehaviourAboveThreeYearsMI();
                surgeryMITotal = surgeryMITotal + totalDTO.getSurgeryMI();
                admissionPsychiatricTotal = admissionPsychiatricTotal + totalDTO.getAdmissionPsychiatric();

                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Intervention MI</title>
    </head>
    <body>
        <logic:present name="medicalInterventionMIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="7"><b>Medical Intervention MI Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="7">
                        <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>, Habitation: <font color="blue">ALL</font>,
                        <% } else if (!village_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>,
                        <% } else if (!mandal_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        <% } else if (!district_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>,
                        <% }%>
                        &nbsp; Type of Disability: <font color="blue">Mental Illness</font>
                    </td>
                </tr>
                <tr>
                    <td align="center" rowspan=2><b>S.No</b></td>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <td rowspan=2 align="center"><b>Habitation</b></td>
                    <% } else if (!village_id.equals("null")) {%>
                    <td rowspan="2" align="center"><b>Village</b></td>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <td rowspan="2" align="center"><b>Mandal</b></td>
                    <% } else if (!district_id.equals("null")) {%>
                    <td rowspan="2"><b>District</b></td>
                    <% }%>
                    <td align="center" colspan="2">Psychotherapy/ Behaviour Modification</td>
                    <td align="center" rowspan="2">Surgery</td>
                    <td align="center" rowspan="2">Admission in Psychiatric Hospitals/ Nursing Homes</td>
                    <td align="center" rowspan="2">Total</td>
                </tr>
                <tr>
                    <td align="center">Below 3 Years</td>
                    <td align="center">Above 3 Years</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="medicalInterventionMIList" scope="session">
                    <tr>
                        <td  align="center"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                        <td align="center"><bean:write name="modify" property="psychotherapyBehaviourBelowThreeYearsMI"/></td>
                        <td align="center"><bean:write name="modify" property="psychotherapyBehaviourAboveThreeYearsMI"/></td>
                        <td align="center"><bean:write name="modify" property="surgeryMI"/></td>
                        <td align="center"><bean:write name="modify" property="admissionPsychiatric"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><%=psychotherapyBehaviourBelowThreeYearsMITotal%></td>
                    <td align="center"><%=psychotherapyBehaviourAboveThreeYearsMITotal%></td>
                    <td align="center"><%=surgeryMITotal%></td>
                    <td align="center"><%=admissionPsychiatricTotal%></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

