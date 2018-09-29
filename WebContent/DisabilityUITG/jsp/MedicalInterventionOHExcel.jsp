<%-- 
    Document   : MedicalInterventionExcel
    Created on : Dec 23, 2010, 10:20:21 AM
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
            ArrayList medicalInterventionList = new ArrayList();
            medicalInterventionList = (ArrayList) session.getAttribute("medicalInterventionOHList");
            int phyBelow3Total = 0, phyAbove3Total = 0, total = 0;
            int otBelow3Total = 0, otAbove3Total = 0, surgeryTotal = 0;
            Iterator iter = medicalInterventionList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                phyBelow3Total = phyBelow3Total + totalDTO.getPhysiotherapyBelowThreeYears();
                phyAbove3Total = phyAbove3Total + totalDTO.getPhysiotherapyAboveThreeYears();
                otBelow3Total = otBelow3Total + totalDTO.getOccupationalTherapyBelowThreeYears();
                otAbove3Total = otAbove3Total + totalDTO.getOccupationalTherapyAboveThreeYears();
                surgeryTotal = surgeryTotal + totalDTO.getSurgeryOH();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Intervention</title>
    </head>
    <body>
        <logic:present name="medicalInterventionOHList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="8"><b>Medical Intervention Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
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
                        &nbsp; Type of Disability: <font color="blue">Locomotor</font>
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
                    <td align="center" colspan="2"><b>Physiotherapy</b></td>
                    <td align="center" colspan="2"><b>Occupational Therapy</b></td>
                    <td align="center" rowspan=2><b>Surgery</b></td>
                    <td align="center" rowspan=2><b>Total</b></td>
                </tr>
                <tr>
                    <td align="center">Below 3 Years</td>
                    <td align="center">Above 3 Years</td>
                    <td align="center">Below 3 Years</td>
                    <td align="center">Above 3 Years</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="medicalInterventionOHList" scope="session">
                    <tr>
                        <td  align="center"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td align="center"><bean:write name="modify" property="habitationName"/></td></logic:notEmpty>
                            <td align="center"><bean:write name="modify" property="physiotherapyBelowThreeYears"/></td>
                            <td align="center"><bean:write name="modify" property="physiotherapyAboveThreeYears"/></td>
                            <td align="center"><bean:write name="modify" property="occupationalTherapyBelowThreeYears"/></td>
                            <td align="center"><bean:write name="modify" property="occupationalTherapyAboveThreeYears"/></td>
                            <td align="center"><bean:write name="modify" property="surgeryOH"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%= phyBelow3Total%></b></td>
                    <td align="center"><b><%= phyAbove3Total%></b></td>
                    <td align="center"><b><%= otBelow3Total%></b></td>
                    <td align="center"><b><%= otAbove3Total%></b></td>
                    <td align="center"><b><%= surgeryTotal%></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>
