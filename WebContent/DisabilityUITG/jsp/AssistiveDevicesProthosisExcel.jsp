<%-- 
    Document   : AssistiveDevicesProthosisExcel
    Created on : Dec 27, 2010, 10:53:53 AM
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
    <body>
        <logic:present name="assistiveDevicesProthosisList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="16"><b>Assistive Devices Prosthesis Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
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
                    <td align="center" colspan="7"><b>Prosthesis for Upper Extremity</b></td>
                                <td align="center" colspan="6"><b>Prosthesis for Lower Extremity</b></td>
                                <td align="center" rowspan=2><b>Total</b></td>
                            </tr>
                            <tr>
                                <td align="center">Shoulder</td>
                                <td align="center">Above Elbow</td>
                                <td align="center">Elbow Disarticulation</td>
                                <td align="center">Below Elbow</td>
                                <td align="center">Wrist Disarticulation</td>
                                <td align="center">Hand</td>
                                <td align="center">Cosmetic Finger</td>
                                <td align="center">Hip Disarticulation</td>
                                <td align="center">Above Knee</td>
                                <td align="center">Knee Disarticulation</td>
                                <td align="center">Below Knee</td>
                                <td align="center">Syme's</td>
                                <td align="center">Partial Foot</td>
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
