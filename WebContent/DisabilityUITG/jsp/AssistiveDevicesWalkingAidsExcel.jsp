<%-- 
    Document   : AssistiveDevicesWalkingAidsExcel
    Created on : Dec 31, 2010, 4:38:47 PM
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

            ArrayList assistiveDevicesWalkingAidsList = new ArrayList();
            assistiveDevicesWalkingAidsList = (ArrayList) session.getAttribute("assistiveDevicesWalkingAidsList");
            int walkingStickSmallTotal = 0, walkingStickLargeTotal = 0, total = 0;
            int axillarySmallTotal = 0, axillaryMediumTotal = 0, axillaryLargeTotal = 0, axillaryExtraLargeTotal = 0;
            int elbowSmallTotal = 0, elbowMediumTotal = 0, elbowLargeTotal = 0, elbowExtraLargeTotal = 0;
            int gutterSmallTotal = 0, gutterMediumTotal = 0, gutterLargeTotal = 0, gutterExtraLargeTotal = 0;
            int tripodSmallTotal = 0, tripodMediumTotal = 0, tripodLargeTotal = 0, tripodExtraLargeTotal = 0;
            Iterator iter = assistiveDevicesWalkingAidsList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                walkingStickSmallTotal = walkingStickSmallTotal + totalDTO.getWalkingStickSmall();
                walkingStickLargeTotal = walkingStickLargeTotal + totalDTO.getWalkingStickLarge();
                axillarySmallTotal = axillarySmallTotal + totalDTO.getAxillarySmall();
                axillaryMediumTotal = axillaryMediumTotal + totalDTO.getAxillaryMedium();
                axillaryLargeTotal = axillaryLargeTotal + totalDTO.getAxillaryLarge();
                axillaryExtraLargeTotal = axillaryExtraLargeTotal + totalDTO.getAxillaryExtraLarge();
                elbowSmallTotal = elbowSmallTotal + totalDTO.getElbowSmall();
                elbowMediumTotal = elbowMediumTotal + totalDTO.getElbowMedium();
                elbowLargeTotal = elbowLargeTotal + totalDTO.getElbowLarge();
                elbowExtraLargeTotal = elbowExtraLargeTotal + totalDTO.getElbowExtraLarge();
                gutterSmallTotal = gutterSmallTotal + totalDTO.getGutterSmall();
                gutterMediumTotal = gutterMediumTotal + totalDTO.getGutterMedium();
                gutterLargeTotal = gutterLargeTotal + totalDTO.getGutterLarge();
                gutterExtraLargeTotal = gutterExtraLargeTotal + totalDTO.getGutterExtraLarge();
                tripodSmallTotal = tripodSmallTotal + totalDTO.getTripodSmall();
                tripodMediumTotal = tripodMediumTotal + totalDTO.getTripodMedium();
                tripodLargeTotal = tripodLargeTotal + totalDTO.getTripodLarge();
                tripodExtraLargeTotal = tripodExtraLargeTotal + totalDTO.getTripodExtraLarge();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:present name="assistiveDevicesWalkingAidsList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="21"><b>Assistive Devices Walking Aids Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="21">
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
                    <td align="center" class="formhdbg" colspan="2">Walking Stick</td>
                    <td align="center" colspan="4">Axillary</td>
                    <td align="center" colspan="4">Elbow</td>
                    <td align="center" colspan="4">Gutter</td>
                    <td align="center" colspan="4">Tripod</td>
                    <td align="center" rowspan=2>Total</td>
                </tr>
                <tr>
                    <td align="center">Small</td>
                    <td align="center">Large</td>
                    <td align="center">Small</td>
                    <td align="center">Medium</td>
                    <td align="center">Large</td>
                    <td align="center">Extra Large</td>
                    <td align="center">Small</td>
                    <td align="center">Medium</td>
                    <td align="center">Large</td>
                    <td align="center">Extra Large</td>
                    <td align="center">Small</td>
                    <td align="center">Medium</td>
                    <td align="center">Large</td>
                    <td align="center">Extra Large</td>
                    <td align="center">Small</td>
                    <td align="center">Medium</td>
                    <td align="center">Large</td>
                    <td align="center">Extra Large</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesWalkingAidsList" scope="session">
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
                        <td class="formbg" align="center"><bean:write name="modify" property="walkingStickSmall"/></td>
                        <td align="center"><bean:write name="modify" property="walkingStickLarge"/></td>
                        <td align="center"><bean:write name="modify" property="axillarySmall"/></td>
                        <td align="center"><bean:write name="modify" property="axillaryMedium"/></td>
                        <td align="center"><bean:write name="modify" property="axillaryLarge"/></td>
                        <td align="center"><bean:write name="modify" property="axillaryExtraLarge"/></td>
                        <td align="center"><bean:write name="modify" property="elbowSmall"/></td>
                        <td align="center"><bean:write name="modify" property="elbowMedium"/></td>
                        <td align="center"><bean:write name="modify" property="elbowLarge"/></td>
                        <td align="center"><bean:write name="modify" property="elbowExtraLarge"/></td>
                        <td align="center"><bean:write name="modify" property="gutterSmall"/></td>
                        <td align="center"><bean:write name="modify" property="gutterMedium"/></td>
                        <td align="center"><bean:write name="modify" property="gutterLarge"/></td>
                        <td align="center"><bean:write name="modify" property="gutterExtraLarge"/></td>
                        <td align="center"><bean:write name="modify" property="tripodSmall"/></td>
                        <td align="center"><bean:write name="modify" property="tripodMedium"/></td>
                        <td align="center"><bean:write name="modify" property="tripodLarge"/></td>
                        <td align="center"><bean:write name="modify" property="tripodExtraLarge"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=walkingStickSmallTotal%></b></td>
                    <td align="center"><b><%=walkingStickLargeTotal%></b></td>
                    <td align="center"><b><%=axillarySmallTotal%></b></td>
                    <td align="center"><b><%=axillaryMediumTotal%></b></td>
                    <td align="center"><b><%=axillaryLargeTotal%></b></td>
                    <td align="center"><b><%=axillaryExtraLargeTotal%></b></td>
                    <td align="center"><b><%=elbowSmallTotal%></b></td>
                    <td align="center"><b><%=elbowMediumTotal%></b></td>
                    <td align="center"><b><%=elbowLargeTotal%></b></td>
                    <td align="center"><b><%=elbowExtraLargeTotal%></b></td>
                    <td align="center"><b><%=gutterSmallTotal%></b></td>
                    <td align="center"><b><%=gutterMediumTotal%></b></td>
                    <td align="center"><b><%=gutterLargeTotal%></b></td>
                    <td align="center"><b><%=gutterExtraLargeTotal%></b></td>
                    <td align="center"><b><%=tripodSmallTotal%></b></td>
                    <td align="center"><b><%=tripodMediumTotal%></b></td>
                    <td align="center"><b><%=tripodLargeTotal%></b></td>
                    <td align="center"><b><%=tripodExtraLargeTotal%></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

