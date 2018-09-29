<%-- 
    Document   : GeneralNeedsMRPrint
    Created on : Feb 14, 2011, 10:04:45 AM
    Author     : 509865
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


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
            ArrayList mrGeneralNeedsList = new ArrayList();
            mrGeneralNeedsList = (ArrayList) session.getAttribute("mrGeneralNeedsList");

            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0, legalGurdianTotal = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0;
            Iterator iter = mrGeneralNeedsList.iterator();
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
                otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MR General Needs Print</title>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="mrGeneralNeedsList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                <tr>
                    <th align="center" colspan="13"><b>Mental Retardation General Needs Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="13">
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
                        &nbsp; Type of Disability: <font color="blue">Mental Retardation</font>
                    </th>
                </tr>
                <tr>
                    <th rowspan=2><b>S.No</b></th>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <th rowspan=2><b>Habitation</b></th>
                    <% } else if (!village_id.equals("null")) {%>
                    <th rowspan="2"><b>Village</b></th>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <th rowspan="2"><b>Mandal</b></th>
                    <% } else if (!district_id.equals("null")) {%>
                    <th rowspan="2"><b>District</b></th>
                    <% }%>
                    <th colspan="4" align="center">Education Services</th>
                    <th colspan="2" align="center">Vocational Training</th>
                    <th colspan="2">Counseling & Guidance</th>
                    <th rowspan="2">Legal Gurdian</th>
                    <th rowspan="2">Other General Needs</th>
                    <th rowspan="2">Total</th>
                </tr>
                <tr>
                    <th>Early Education Service(<5Yrs)</th>
                    <th>Home Based</th>
                    <th>Special Education</th>
                    <th>Inclusive Education</th>
                    <th>Employment in public/ Pvt.Sector</th>
                    <th>Self Employment</th>
                    <th>Individual</th>
                    <th>Family</th>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="mrGeneralNeedsList" scope="session">
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
                    <td><%=otherGeneralNeedsTotal%></td>
                    <td><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


