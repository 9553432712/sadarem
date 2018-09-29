<%-- 
    Document   : AssistiveDevicesHIExcel
    Created on : Jan 4, 2011, 10:45:47 AM
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
            ArrayList assistiveDevicesHIList = new ArrayList();
            assistiveDevicesHIList = (ArrayList) session.getAttribute("assistiveDevicesHIList");

            int lowIntensitySCordTotal = 0, lowIntensityVCordTotal = 0, total = 0;
            int moderateIntensitySCordTotal = 0, moderateIntensityVCordTotal = 0, highIntensitySCordTotal = 0;
            int highIntensityVCordTotal = 0, implantableHearingAidTotal = 0;
            Iterator iter = assistiveDevicesHIList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                lowIntensitySCordTotal = lowIntensitySCordTotal + totalDTO.getLowIntensitySCord();
                lowIntensityVCordTotal = lowIntensityVCordTotal + totalDTO.getLowIntensityVCord();
                moderateIntensitySCordTotal = moderateIntensitySCordTotal + totalDTO.getModerateIntensitySCord();
                moderateIntensityVCordTotal = moderateIntensityVCordTotal + totalDTO.getModerateIntensityVCord();
                highIntensitySCordTotal = highIntensitySCordTotal + totalDTO.getHighIntensitySCord();
                highIntensityVCordTotal = highIntensityVCordTotal + totalDTO.getHighIntensityVCord();
                implantableHearingAidTotal = implantableHearingAidTotal + totalDTO.getImplantableHearingAid();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assistive Devices HI</title>
    </head>
    <body>
        <logic:present name="assistiveDevicesHIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="10"><b>Assistive Devices HI Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="10">
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
                    <td colspan="2">Low intensity</td>
                    <td colspan="2">Moderate Intensity</td>
                    <td colspan="2">High Intensity</td>
                    <td rowspan="2">Implantable Hearing Aid</td>
                    <td rowspan="2">Total</td>
                </tr>
                <tr>
                    <td>S-Cord</td>
                    <td>V-Cord</td>
                    <td>S-Cord</td>
                    <td>V-Cord</td>
                    <td>S-Cord</td>
                    <td>V-Cord</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesHIList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="lowIntensitySCord"/></td>
                        <td align="center"><bean:write name="modify" property="lowIntensityVCord"/></td>
                        <td align="center"><bean:write name="modify" property="moderateIntensitySCord"/></td>
                        <td align="center"><bean:write name="modify" property="moderateIntensityVCord"/></td>
                        <td align="center"><bean:write name="modify" property="highIntensitySCord"/></td>
                        <td align="center"><bean:write name="modify" property="highIntensityVCord"/></td>
                        <td align="center"><bean:write name="modify" property="implantableHearingAid"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><%=lowIntensitySCordTotal%></td>
                    <td align="center"><%=lowIntensityVCordTotal%></td>
                    <td align="center"><%=moderateIntensitySCordTotal%></td>
                    <td align="center"><%=moderateIntensityVCordTotal%></td>
                    <td align="center"><%=highIntensitySCordTotal%></td>
                    <td align="center"><%=highIntensityVCordTotal%></td>
                    <td align="center"><%=implantableHearingAidTotal%></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

