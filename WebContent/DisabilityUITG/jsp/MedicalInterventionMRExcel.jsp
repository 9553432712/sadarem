<%-- 
    Document   : MedicalInterventionMRExcel
    Created on : Jan 4, 2011, 12:00:49 PM
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
            ArrayList medicalInterventionMRList = new ArrayList();
            medicalInterventionMRList = (ArrayList) session.getAttribute("medicalInterventionMRList");

            int speechTherapyBelowThreeYearsMRTotal = 0, speechTherapyAboveThreeYearsMRTotal = 0, total = 0;
            int behaviourModificationPsychotherapyBelowTotal = 0, behaviourModificationPsychotherapyAboveTotal = 0;
            int sensoryIntegrationCognitiveDevelopmentTotal = 0, cognitiveBehaviourTherapyTotal = 0;
            int parentFamilyInterventionTotal = 0, physiotherapyMRTotal = 0, occupationalTherapyMRTotal = 0;
            Iterator iter = medicalInterventionMRList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                speechTherapyBelowThreeYearsMRTotal = speechTherapyBelowThreeYearsMRTotal +
                        totalDTO.getSpeechTherapyBelowThreeYearsMR();
                speechTherapyAboveThreeYearsMRTotal = speechTherapyAboveThreeYearsMRTotal +
                        totalDTO.getSpeechTherapyAboveThreeYearsMR();
                behaviourModificationPsychotherapyBelowTotal = behaviourModificationPsychotherapyBelowTotal +
                        totalDTO.getBehaviourModificationPsychotherapyBelow();
                behaviourModificationPsychotherapyAboveTotal = behaviourModificationPsychotherapyAboveTotal +
                        totalDTO.getBehaviourModificationPsychotherapyAbove();
                sensoryIntegrationCognitiveDevelopmentTotal = sensoryIntegrationCognitiveDevelopmentTotal +
                        totalDTO.getSensoryIntegrationCognitiveDevelopment();
                cognitiveBehaviourTherapyTotal = cognitiveBehaviourTherapyTotal + totalDTO.getCognitiveBehaviourTherapy();
                parentFamilyInterventionTotal = parentFamilyInterventionTotal + totalDTO.getParentFamilyIntervention();
                physiotherapyMRTotal = physiotherapyMRTotal + totalDTO.getPhysiotherapyMR();
                occupationalTherapyMRTotal = occupationalTherapyMRTotal + totalDTO.getOccupationalTherapyMR();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Intervention MR</title>
    </head>
    <body>
        <logic:present name="medicalInterventionMRList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="12"><b>Medical Intervention MR Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="12">
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
                        &nbsp; Type of Disability: <font color="blue">Mental Retardation</font>
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
                    <td align="center" colspan="2">Speech Therapy & Language Development</td>
                                <td align="center" colspan="2">Behaviour Modification/Psychotherapy</td>
                                <td align="center" rowspan=2>Sensory Integration/ Cognitive Development</td>
                                <td align="center" rowspan=2>Cognitive Behaviour Therapy</td>
                                <td align="center" rowspan=2>Parent/ Family Intervention</td>
                                <td align="center" rowspan=2>Physiotherapy</td>
                                <td align="center" rowspan=2>Occupational Therapy</td>
                                <td align="center" rowspan=2>Total</td>
                            </tr>
                            <tr>
                                <td align="center">Below 3 Years</td>
                                <td align="center">Above 3 Years</td>
                                <td align="center">Below 3 Years</td>
                                <td align="center">Above 3 Years</td>
                            </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="medicalInterventionMRList" scope="session">
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
                            <td align="center"><bean:write name="modify" property="speechTherapyBelowThreeYearsMR"/></td>
                                    <td align="center"><bean:write name="modify" property="speechTherapyAboveThreeYearsMR"/></td>
                                    <td align="center"><bean:write name="modify" property="behaviourModificationPsychotherapyBelow"/></td>
                                    <td align="center"><bean:write name="modify" property="behaviourModificationPsychotherapyAbove"/></td>
                                    <td align="center"><bean:write name="modify" property="sensoryIntegrationCognitiveDevelopment"/></td>
                                    <td align="center"><bean:write name="modify" property="cognitiveBehaviourTherapy"/></td>
                                    <td align="center"><bean:write name="modify" property="parentFamilyIntervention"/></td>
                                    <td align="center"><bean:write name="modify" property="physiotherapyMR"/></td>
                                    <td align="center"><bean:write name="modify" property="occupationalTherapyMR"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><%=speechTherapyBelowThreeYearsMRTotal %></td>
                                <td align="center"><%=speechTherapyAboveThreeYearsMRTotal %></td>
                                <td align="center"><%=behaviourModificationPsychotherapyBelowTotal %></td>
                                <td align="center"><%=behaviourModificationPsychotherapyAboveTotal %></td>
                                <td align="center"><%=sensoryIntegrationCognitiveDevelopmentTotal %></td>
                                <td align="center"><%=cognitiveBehaviourTherapyTotal %></td>
                                <td align="center"><%=parentFamilyInterventionTotal %></td>
                                <td align="center"><%=physiotherapyMRTotal %></td>
                                <td align="center"><%=occupationalTherapyMRTotal %></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>
