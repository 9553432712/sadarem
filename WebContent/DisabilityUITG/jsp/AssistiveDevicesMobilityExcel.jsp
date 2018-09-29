<%-- 
    Document   : AssistiveDevicesMobilityExcel
    Created on : Dec 31, 2010, 1:18:53 PM
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
            ArrayList mobilityList = new ArrayList();
            mobilityList = (ArrayList) session.getAttribute("mobilityList");
            int wheelChairSmallTotal = 0, wheelChairLargeTotal = 0, total = 0, walkingFrameWalkerLargeTotal = 0;
            int tricycleSmallTotal = 0, tricycleLargeTotal = 0, walkingFrameWalkerSmallTotal = 0;
            Iterator iter = mobilityList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                wheelChairSmallTotal = wheelChairSmallTotal + totalDTO.getWheelChairSmall();
                wheelChairLargeTotal = wheelChairLargeTotal + totalDTO.getWheelChairLarge();
                tricycleSmallTotal = tricycleSmallTotal + totalDTO.getTricycleSmall();
                tricycleLargeTotal = tricycleLargeTotal + totalDTO.getTricycleLarge();
                walkingFrameWalkerSmallTotal = walkingFrameWalkerSmallTotal + totalDTO.getWalkingFrameWalkerSmall();
                walkingFrameWalkerLargeTotal = walkingFrameWalkerLargeTotal + totalDTO.getWalkingFrameWalkerLarge();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Intervention</title>
    </head>
    <body>
        <logic:present name="mobilityList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="9"><b>Assistive Devices Mobility Aids Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
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
                    <td colspan="2" align="center">Wheel chair</td>
                    <td colspan="2" align="center">Tricycle</td>
                    <td colspan="2" align="center">Walking Frame/ Walker</td>
                    <td rowspan=2>Total</td>
                </tr>
                <tr>
                    <td>Small</td>
                    <td>Large</td>
                    <td>Small</td>
                    <td>Large</td>
                    <td>Small</td>
                    <td>Large</td>
                </tr>

                <% int i = 0;%>
                <logic:iterate id="modify" name="mobilityList" scope="session">
                    <tr>
                        <td align="center"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                        <td align="center"><bean:write name="modify" property="wheelChairSmall"/></td>
                        <td align="center"><bean:write name="modify" property="wheelChairLarge"/></td>
                        <td align="center"><bean:write name="modify" property="tricycleSmall"/></td>
                        <td align="center"><bean:write name="modify" property="tricycleLarge"/></td>
                        <td align="center"><bean:write name="modify" property="walkingFrameWalkerSmall"/></td>
                        <td align="center"><bean:write name="modify" property="walkingFrameWalkerLarge"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=wheelChairSmallTotal%></b></td>
                    <td align="center"><b><%=wheelChairLargeTotal%></b></td>
                    <td align="center"><b><%=tricycleSmallTotal%></b></td>
                    <td align="center"><b><%=tricycleLargeTotal%></b></td>
                    <td align="center"><b><%=walkingFrameWalkerSmallTotal%></b></td>
                    <td align="center"><b><%=walkingFrameWalkerLargeTotal%></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

