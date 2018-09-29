<%--
    Document   : GeneralNeedsMRExcel
    Created on : Jan 6, 2011, 8:48:42 AM
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
            ArrayList miGeneralNeedsList = new ArrayList();
            miGeneralNeedsList = (ArrayList) session.getAttribute("miGeneralNeedsList");

            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0, managerToTakeCarePropertiesTotal = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0;
            Iterator iter = miGeneralNeedsList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                individualTotal = individualTotal + totalDTO.getInclusiveEducation();
                familyTotal = familyTotal + totalDTO.getFamily();
                managerToTakeCarePropertiesTotal = managerToTakeCarePropertiesTotal + totalDTO.getManagerToTakeCareProperties();
                otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MI General Needs</title>
    </head>
    <body>
        <logic:present name="miGeneralNeedsList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="13"><b>Mental Illness General Needs Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="13">
                        <% if (!habitation_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>, Habitation: <font color="blue"><%=habitationName%></font>,
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
                    <td align="center" colspan="4">Education Services</td>
                    <td align="center" colspan="2">Vocational Training</td>
                    <td align="center" colspan="2">Counseling & Guidance</td>
                    <td align="center" rowspan="2">Manager to Take Care Properties</td>
                    <td align="center" rowspan="2">Other General Needs</td>
                    <td align="center" rowspan="2">Total</td>
                </tr>
                <tr>
                    <td align="center">Early Education Service(<5Yrs)</td>
                    <td align="center">Home Based</td>
                    <td align="center">Special Education</td>
                    <td align="center">Inclusive Education</td>
                    <td align="center">Employment in public/ Pvt.Sector</td>
                    <td align="center">Self Employment</td>
                    <td align="center">Individual</td>
                    <td align="center">Family</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="miGeneralNeedsList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="earlyEducationService"/></td>
                        <td align="center"><bean:write name="modify" property="homeBased"/></td>
                        <td align="center"><bean:write name="modify" property="specialEducation"/></td>
                        <td align="center"><bean:write name="modify" property="inclusiveEducation"/></td>
                        <td align="center"><bean:write name="modify" property="employmentPublicPvtSector"/></td>
                        <td align="center"><bean:write name="modify" property="selfEmployment"/></td>
                        <td align="center"><bean:write name="modify" property="individual"/></td>
                        <td align="center"><bean:write name="modify" property="family"/></td>
                        <td align="center"><bean:write name="modify" property="managerToTakeCareProperties"/></td>
                        <td align="center"><bean:write name="modify" property="otherGeneralNeeds"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><%=earlyEducationServiceTotal%></td>
                    <td align="center"><%=homeBasedTotal%></td>
                    <td align="center"><%=specialEducationTotal%></td>
                    <td align="center"><%=inclusiveEducationTotal%></td>
                    <td align="center"><%=employmentPublicPvtSectorTotal%></td>
                    <td align="center"><%=selfEmploymentTotal%></td>
                    <td align="center"><%=individualTotal%></td>
                    <td align="center"><%=familyTotal%></td>
                    <td align="center"><%=managerToTakeCarePropertiesTotal %></td>
                    <td align="center"><%=otherGeneralNeedsTotal%></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

