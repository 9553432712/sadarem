<%-- 
    Document   : mandalWiseStatusReportForPartBPrint
    Created on : Jun 12, 2010, 8:41:52 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO" %>
<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String districtname = (String) request.getParameter("districtname");
            int existingPensionersCount = 0, partACount = 0, totalPersonsAssed = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0, total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            ArrayList partBMandalReportList = new ArrayList();
            partBMandalReportList = (ArrayList) session.getAttribute("partBMandalReportList");
            Iterator iter = partBMandalReportList.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                existingPensionersCount = existingPensionersCount + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                totalPersonsAssed = totalPersonsAssed + Integer.parseInt(partAdto.getTotalpersonsassessed());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());
                total = total + Integer.parseInt(partAdto.getTotal());
                directRejected = directRejected + Integer.parseInt(partAdto.getDirectrejected());
                assessedAndRejected = assessedAndRejected + Integer.parseInt(partAdto.getAssessedandrejected());
                totalRejected = totalRejected + Integer.parseInt(partAdto.getTotalrejected());

            }
%>


<html:html>
    <body  onLoad="window.print()">
    <head>
    </head>
    <body>
    

    <table  border="1" cellspacing="1" cellpadding="5" class="innerTable" width="60%">

        <tr>
            <td class="label" align="center" colspan="15">
                <font color="#c71585">
                    Mandal Wise Report For Personal Information & Assessed PWD's Details As On <%= fromdate%> To <%= todate%></font>
                <font color="#c71585">District:<%=districtname%></font>
            </td></tr>

        <tr>
                    <td align="center" class="labelBlue" rowspan=2>S.No</td>
                    <td ALIGN="center" class="labelBlue" rowspan=2>Mandal</td>
                    <td align="center" class="labelBlue" rowspan=2>Total No. of Disabled Pensioners as per SSP data</td>
                    <td align="center" class="labelBlue" rowspan=2>No. of Part-A Entered</td>
                    <td align="center" class="labelBlue" rowspan=2>Total No of Persons Assessed</td>
                    <td align="center" class="labelBlue" colspan="7">Certificates Issued</td>
                    <td align="center" class="labelBlue" colspan="3">Rejected Status</td>
                </tr>
                <tr>
                    <td align="center" class="labelBlue">Ortho</td>
                    <td align="center" class="labelBlue">Visual</td>
                    <td align="center" class="labelBlue">Hearing</td>
                    <td align="center" class="labelBlue">Mental Retardation</td>
                    <td align="center" class="labelBlue">Mental Illness</td>
                    <td align="center" class="labelBlue">Multiple Disability</td>
                    <td align="center" class="labelBlue">Total</td>
                    <td align="center" class="labelBlue">Direct Rejected</td>
                    <td align="center" class="labelBlue">Assessed & Rejected</td>
                    <td align="center" class="labelBlue">Total Rejected</td>
                </tr>
        <% int i = 0;%>
        <logic:iterate id="disabilityreport" name="partBMandalReportList" scope="session">
            <tr>
                <td class="label"><%=++i%></td>
                <td class="label"><bean:write name="disabilityreport" property="mandal_name"/></td>
                <td class="label"><bean:write name="disabilityreport" property="existingpensioners"/></td>
                <td class="label"><bean:write name="disabilityreport" property="partacount"/></td>
                <td class="label"><bean:write name="disabilityreport" property="totalpersonsassessed"/></td>
                <td class="label"><bean:write name="disabilityreport" property="ortho"/></td>
                <td class="label"><bean:write name="disabilityreport" property="visual"/></td>
                <td class="label"><bean:write name="disabilityreport" property="hearing"/></td>
                <td class="label"><bean:write name="disabilityreport" property="mentalretardation"/></td>
                <td class="label"><bean:write name="disabilityreport" property="mentalillness"/></td>
                <td class="label"><bean:write name="disabilityreport" property="multipledisability"/></td>
                <td class="label"><bean:write name="disabilityreport" property="total"/></td>
                <td class="label"><bean:write name="disabilityreport" property="directrejected"/></td>
                <td class="label"><bean:write name="disabilityreport" property="assessedandrejected"/></td>
                <td class="label"><bean:write name="disabilityreport" property="totalrejected"/></td>
            </tr>
        </logic:iterate>
        <tr>
            <td class="label">&nbsp;&nbsp;</td>
            <td class="label"><b>Total</b></td>
            <td class="label"><b><%=existingPensionersCount%></b></td>
            <td class="label"><b><%=partACount%></b></td>
            <td class="label"><b><%=totalPersonsAssed%></b></td>
            <td class="label"><b><%=orthoCount%></b></td>
            <td class="label"><b><%=visualCount%></b></td>
            <td class="label"><b><%=hearingCount%></b></td>
            <td class="label"><b><%=MRCount%></b></td>
            <td class="label"><b><%=MICount%></b></td>
            <td class="label"><b><%=MDCount%></b></td>
            <td class="label"><b><%=total%></b></td>
            <td class="label"><b><%=directRejected%></b></td>
            <td class="label"><b><%=assessedAndRejected%></b></td>
            <td class="label"><b><%=totalRejected%></b></td>
        </tr>
        <%             session.removeAttribute("partBDistrictReport");
                       session.removeAttribute("partBVillageReport");  %>
    </table>
</body>
</html:html>


