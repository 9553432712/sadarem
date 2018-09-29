<%-- 
    Document   : MedicalInterventionHIExcel
    Created on : Jan 4, 2011, 10:09:00 AM
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
            ArrayList medicalInterventionHIList = new ArrayList();
            medicalInterventionHIList = (ArrayList) session.getAttribute("medicalInterventionHIList");

            int speechTherapyBelowThreeYearsHITotal = 0, speechTherapyAboveThreeYearsHITotal = 0, total = 0;
            int languageDevelopmentTotal = 0, surgeryHITotal = 0, cochlearImplantationTotal = 0;
            Iterator iter = medicalInterventionHIList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                speechTherapyBelowThreeYearsHITotal = speechTherapyBelowThreeYearsHITotal +
                        totalDTO.getSpeechTherapyBelowThreeYearsHI();
                speechTherapyAboveThreeYearsHITotal = speechTherapyAboveThreeYearsHITotal +
                        totalDTO.getSpeechTherapyAboveThreeYearsHI();
                languageDevelopmentTotal = languageDevelopmentTotal + totalDTO.getLanguageDevelopment();
                surgeryHITotal = surgeryHITotal + totalDTO.getSurgeryHI();
                cochlearImplantationTotal = cochlearImplantationTotal + totalDTO.getCochlearImplantation();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Intervention HI</title>
    </head>
    <body>
        <logic:present name="medicalInterventionHIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="8"><b>Medical Intervention HI Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="8">
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
                        &nbsp; Type of Disability: <font color="blue">Hearing</font>
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
                    <td align="center" colspan="2">Speech therapy</td>
                                <td align="center" rowspan="2">Language Development</td>
                                <td align="center" rowspan=2>Surgery</td>
                                <td align="center" rowspan=2>Cochlear Implantation</td>
                                <td align="center" rowspan=2>Total</td>
                            </tr>
                            <tr>
                                <td align="center">Below 3 Years</td>
                                <td align="center">Above 3 Years</td>
                            </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="medicalInterventionHIList" scope="session">
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
                            <td align="center"><bean:write name="modify" property="speechTherapyBelowThreeYearsHI"/></td>
                                    <td align="center"><bean:write name="modify" property="speechTherapyAboveThreeYearsHI"/></td>
                                    <td align="center"><bean:write name="modify" property="languageDevelopment"/></td>
                                    <td align="center"><bean:write name="modify" property="surgeryHI"/></td>
                                    <td align="center"><bean:write name="modify" property="cochlearImplantation"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><%=speechTherapyBelowThreeYearsHITotal %></td>
                                <td align="center"><%=speechTherapyAboveThreeYearsHITotal %></td>
                                <td align="center"><%=languageDevelopmentTotal %></td>
                                <td align="center"><%=surgeryHITotal %></td>
                                <td align="center"><%=cochlearImplantationTotal %></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

