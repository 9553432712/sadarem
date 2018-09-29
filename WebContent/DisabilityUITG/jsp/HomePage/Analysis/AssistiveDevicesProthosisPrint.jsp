<%-- 
    Document   : AssistiveDevicesProthosisPrint
    Created on : Feb 17, 2011, 10:08:54 AM
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
            ArrayList assistiveDevicesProthosisList = new ArrayList();
            assistiveDevicesProthosisList = (ArrayList) session.getAttribute("assistiveDevicesProthosisList");
            int shoulderTotal = 0, aboveElbowTotal = 0, total = 0;
            int elbowDisarticulationTotal = 0, belowElbowTotal = 0, wristDisarticulationTotal = 0;
            int handTotal = 0, cosmeticFingerTotal = 0, hipDisarticulationTotal = 0;
            int aboveKneeTotal = 0, kneeDisarticulationTotal = 0, belowKneeTotal = 0;
            int symesTotal = 0, partialFootTotal = 0;
            Iterator iter = assistiveDevicesProthosisList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                shoulderTotal = shoulderTotal + totalDTO.getShoulder();
                aboveElbowTotal = aboveElbowTotal + totalDTO.getAboveElbow();
                elbowDisarticulationTotal = elbowDisarticulationTotal + totalDTO.getElbowDisarticulation();
                belowElbowTotal = belowElbowTotal + totalDTO.getBelowElbow();
                wristDisarticulationTotal = wristDisarticulationTotal + totalDTO.getWristDisarticulation();
                handTotal = handTotal + totalDTO.getHand();
                cosmeticFingerTotal = cosmeticFingerTotal + totalDTO.getCosmeticFinger();
                hipDisarticulationTotal = hipDisarticulationTotal + totalDTO.getHipDisarticulation();
                aboveKneeTotal = aboveKneeTotal + totalDTO.getAboveKnee();
                kneeDisarticulationTotal = kneeDisarticulationTotal + totalDTO.getKneeDisarticulation();
                belowKneeTotal = belowKneeTotal + totalDTO.getBelowKnee();
                symesTotal = symesTotal + totalDTO.getSymes();
                partialFootTotal = partialFootTotal + totalDTO.getPartialFoot();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prosthesis</title>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="assistiveDevicesProthosisList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                <tr>
                    <th align="center" colspan="16"><b>Assistive Devices Prosthesis Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="16">
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
                    <th align="center" rowspan=2><b>S.No</b></th>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <th rowspan=2 align="center"><b>Habitation</b></th>
                    <% } else if (!village_id.equals("null")) {%>
                    <th rowspan="2" align="center"><b>Village</b></th>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <th rowspan="2" align="center"><b>Mandal</b></th>
                    <% } else if (!district_id.equals("null")) {%>
                    <th rowspan="2"><b>District</b></th>
                    <% }%>
                    <th align="center" colspan="7"><b>Prosthesis for Upper Extremity</b></th>
                                <th align="center" colspan="6"><b>Prosthesis for Lower Extremity</b></th>
                                <th align="center" rowspan=2><b>Total</b></th>
                            </tr>
                            <tr>
                                <th align="center">Shoulder</th>
                                <th align="center">Above Elbow</th>
                                <th align="center">Elbow Disarticulation</th>
                                <th align="center">Below Elbow</th>
                                <th align="center">Wrist Disarticulation</th>
                                <th align="center">Hand</th>
                                <th align="center">Cosmetic Finger</th>
                                <th align="center">Hip Disarticulation</th>
                                <th align="center">Above Knee</th>
                                <th align="center">Knee Disarticulation</th>
                                <th align="center">Below Knee</th>
                                <th align="center">Syme's</th>
                                <th align="center">Partial Foot</th>
                            </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesProthosisList" scope="session">
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
                            <td align="center"><bean:write name="modify" property="shoulder"/></td>
                            <td align="center"><bean:write name="modify" property="aboveElbow"/></td>
                            <td align="center"><bean:write name="modify" property="elbowDisarticulation"/></td>
                            <td align="center"><bean:write name="modify" property="belowElbow"/></td>
                            <td align="center"><bean:write name="modify" property="wristDisarticulation"/></td>
                            <td align="center"><bean:write name="modify" property="hand"/></td>
                            <td align="center"><bean:write name="modify" property="cosmeticFinger"/></td>
                            <td align="center"><bean:write name="modify" property="hipDisarticulation"/></td>
                            <td align="center"><bean:write name="modify" property="aboveKnee"/></td>
                            <td align="center"><bean:write name="modify" property="kneeDisarticulation"/></td>
                            <td align="center"><bean:write name="modify" property="belowKnee"/></td>
                            <td align="center"><bean:write name="modify" property="symes"/></td>
                            <td align="center"><bean:write name="modify" property="partialFoot"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=shoulderTotal %></b></td>
                    <td align="center"><b><%=aboveElbowTotal %></b></td>
                    <td align="center"><b><%=elbowDisarticulationTotal %></b></td>
                    <td align="center"><b><%=belowElbowTotal %></b></td>
                    <td align="center"><b><%=wristDisarticulationTotal %></b></td>
                    <td align="center"><b><%=handTotal %></b></td>
                    <td align="center"><b><%=cosmeticFingerTotal %></b></td>
                    <td align="center"><b><%=hipDisarticulationTotal %></b></td>
                    <td align="center"><b><%=aboveKneeTotal %></b></td>
                    <td align="center"><b><%=kneeDisarticulationTotal %></b></td>
                    <td align="center"><b><%=belowKneeTotal %></b></td>
                    <td align="center"><b><%=symesTotal %></b></td>
                    <td align="center"><b><%=partialFootTotal %></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

