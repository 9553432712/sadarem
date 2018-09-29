<%-- 
    Document   : AssistiveDevicesMobilityPrint
    Created on : Feb 17, 2011, 10:08:16 AM
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
    <body  onLoad="window.print()">
        <logic:present name="mobilityList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                <tr>
                    <th align="center" colspan="9"><b>Assistive Devices Mobility Aids Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="9">
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
                    <th align="center" colspan="2">Wheel chair</th>
                    <th align="center" colspan="2">Tricycle</th>
                    <th align="center" colspan="2">Walking Frame/ Walker</th>
                    <th align="center" rowspan=2>Total</th>
                </tr>
                <tr>
                    <th>Small</th>
                    <th>Large</th>
                    <th>Small</th>
                    <th>Large</th>
                    <th>Small</th>
                    <th>Large</th>
                </tr>

                <% int i = 0;%>
                <logic:iterate id="modify" name="mobilityList" scope="session">
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
                        <td><bean:write name="modify" property="wheelChairSmall"/></td>
                        <td><bean:write name="modify" property="wheelChairLarge"/></td>
                        <td><bean:write name="modify" property="tricycleSmall"/></td>
                        <td><bean:write name="modify" property="tricycleLarge"/></td>
                        <td><bean:write name="modify" property="walkingFrameWalkerSmall"/></td>
                        <td><bean:write name="modify" property="walkingFrameWalkerLarge"/></td>
                        <td><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td><b><%=wheelChairSmallTotal%></b></td>
                    <td><b><%=wheelChairLargeTotal%></b></td>
                    <td><b><%=tricycleSmallTotal%></b></td>
                    <td><b><%=tricycleLargeTotal%></b></td>
                    <td><b><%=walkingFrameWalkerSmallTotal%></b></td>
                    <td><b><%=walkingFrameWalkerLargeTotal%></b></td>
                    <td><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


