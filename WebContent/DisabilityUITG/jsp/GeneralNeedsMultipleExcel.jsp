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
            ArrayList multipleGeneralNeedsList = new ArrayList();
            multipleGeneralNeedsList = (ArrayList) session.getAttribute("multipleGeneralNeedsList");

            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0, managerToTakeCarePropertiesTotal = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0, legalGurdianTotal = 0;
            Iterator iter = multipleGeneralNeedsList.iterator();
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
                legalGurdianTotal = legalGurdianTotal + totalDTO.getLegalGurdian();
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
        <logic:present name="multipleGeneralNeedsList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="14"><b>Multiple Disability General Needs Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="14">
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
                        &nbsp; Type of Disability: <font color="blue">Multiple Disability</font>
                    </td>
                </tr>
                <tr>
                    <td rowspan=2><b>S.No</b></td>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <td rowspan=2><b>Habitation</b></td>
                    <% } else if (!village_id.equals("null")) {%>
                    <td rowspan="2"><b>Village</b></td>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <td rowspan="2"><b>Mandal</b></td>
                    <% } else if (!district_id.equals("null")) {%>
                    <td rowspan="2"><b>District</b></td>
                    <% }%>
                    <td colspan="4" align="center">Education Services</td>
                    <td colspan="2" align="center">Vocational Training</td>
                    <td colspan="2">Counseling & Guidance</td>
                    <td rowspan="2">Legal Gurdian</td>
                    <td rowspan="2">Manager to Take Care Properties</td>
                    <td rowspan="2">Other General Needs</td>
                    <td rowspan="2">Total</td>
                </tr>
                <tr>
                    <td>Early Education Service(<5Yrs)</td>
                    <td>Home Based</td>
                    <td>Special Education</td>
                    <td>Inclusive Education</td>
                    <td>Employment in public/ Pvt.Sector</td>
                    <td>Self Employment</td>
                    <td>Individual</td>
                    <td>Family</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="multipleGeneralNeedsList" scope="session">
                    <tr>
                        <td><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                        <td><bean:write name="modify" property="earlyEducationService"/></td>
                        <td><bean:write name="modify" property="homeBased"/></td>
                        <td><bean:write name="modify" property="specialEducation"/></td>
                        <td><bean:write name="modify" property="inclusiveEducation"/></td>
                        <td><bean:write name="modify" property="employmentPublicPvtSector"/></td>
                        <td><bean:write name="modify" property="selfEmployment"/></td>
                        <td><bean:write name="modify" property="individual"/></td>
                        <td><bean:write name="modify" property="family"/></td>
                        <td><bean:write name="modify" property="legalGurdian"/></td>
                        <td><bean:write name="modify" property="managerToTakeCareProperties"/></td>
                        <td><bean:write name="modify" property="otherGeneralNeeds"/></td>
                        <td><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2"><b>Total</b></td>
                    <td><%=earlyEducationServiceTotal%></td>
                    <td><%=homeBasedTotal%></td>
                    <td><%=specialEducationTotal%></td>
                    <td><%=inclusiveEducationTotal%></td>
                    <td><%=employmentPublicPvtSectorTotal%></td>
                    <td><%=selfEmploymentTotal%></td>
                    <td><%=individualTotal%></td>
                    <td><%=familyTotal%></td>
                    <td><%=legalGurdianTotal %></td>
                    <td><%=managerToTakeCarePropertiesTotal %></td>
                    <td><%=otherGeneralNeedsTotal%></td>
                    <td><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

