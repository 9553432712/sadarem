<%-- 
    Document   : AssistiveDevicesVIPrint
    Created on : Feb 17, 2011, 10:06:17 AM
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
            ArrayList assistiveDevicesVIList = new ArrayList();

            assistiveDevicesVIList = (ArrayList) session.getAttribute("assistiveDevicesVIList");
            int whiteCaneBlindStickTotal = 0, brailleEquipmentsTotal = 0, total = 0;
            int arithmeticFramesAbacusTotal = 0, lowVisionAidsSpectaclesMagnifiersTotal = 0, speechSynthesizerTotal = 0;
            int brailleShortHandMachinesWritersTotal = 0, talkingWatchCalculatorTotal = 0, viADLEquipmentTotal = 0;
            Iterator iter = assistiveDevicesVIList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                whiteCaneBlindStickTotal = whiteCaneBlindStickTotal + totalDTO.getWhiteCaneBlindStick();
                brailleEquipmentsTotal = brailleEquipmentsTotal + totalDTO.getBrailleEquipments();
                arithmeticFramesAbacusTotal = arithmeticFramesAbacusTotal + totalDTO.getArithmeticFramesAbacus();
                lowVisionAidsSpectaclesMagnifiersTotal = lowVisionAidsSpectaclesMagnifiersTotal
                        + totalDTO.getLowVisionAidsSpectaclesMagnifiers();
                speechSynthesizerTotal = speechSynthesizerTotal + totalDTO.getSpeechSynthesizer();
                brailleShortHandMachinesWritersTotal = brailleShortHandMachinesWritersTotal
                        + totalDTO.getBrailleShortHandMachinesWriters();
                talkingWatchCalculatorTotal = talkingWatchCalculatorTotal + totalDTO.getTalkingWatchCalculator();
                viADLEquipmentTotal = viADLEquipmentTotal + totalDTO.getViADLEquipment();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assistive Devices VI</title>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="assistiveDevicesVIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
              
                <tr>
                    <th align="center" colspan="11"><b>Assistive Devices VI Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="11">
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
                        &nbsp; Type of Disability: <font color="blue">Visual</font>
                    </th>
                </tr>
                <tr>
                    <th align="center"><b>S.No</b></th>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <th align="center"><b>Habitation</b></th>
                    <% } else if (!village_id.equals("null")) {%>
                    <th align="center"><b>Village</b></th>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <th align="center"><b>Mandal</b></th>
                    <% } else if (!district_id.equals("null")) {%>
                    <th><b>District</b></th>
                    <% }%>
                    <th align="center">White Cane/ Blind Stick</th>
                    <th align="center">Braille Equipments</th>
                    <th align="center">Arithmetic Frames/ Abacus</th>
                    <th align="center">Low Vision Aids (Spectacles, Magnifiers)</th>
                    <th align="center">Speech Synthesizer</th>
                    <th align="center">Braille Short Hand Machines/ Type Writers</th>
                    <th align="center">Talking Watch/ Calculator</th>
                    <th align="center">ADL Equipment</th>
                    <th align="center">Total</th>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesVIList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="whiteCaneBlindStick"/></td>
                        <td align="center"><bean:write name="modify" property="brailleEquipments"/></td>
                        <td align="center"><bean:write name="modify" property="arithmeticFramesAbacus"/></td>
                        <td align="center"><bean:write name="modify" property="lowVisionAidsSpectaclesMagnifiers"/></td>
                        <td align="center"><bean:write name="modify" property="speechSynthesizer"/></td>
                        <td align="center"><bean:write name="modify" property="brailleShortHandMachinesWriters"/></td>
                        <td align="center"><bean:write name="modify" property="talkingWatchCalculator"/></td>
                        <td align="center"><bean:write name="modify" property="viADLEquipment"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center">Total</td>
                    <td align="center"><%=whiteCaneBlindStickTotal%></td>
                    <td align="center"><%=brailleEquipmentsTotal%></td>
                    <td align="center"><%=arithmeticFramesAbacusTotal%></td>
                    <td align="center"><%=lowVisionAidsSpectaclesMagnifiersTotal%></td>
                    <td align="center"><%=speechSynthesizerTotal%></td>
                    <td align="center"><%=brailleShortHandMachinesWritersTotal%></td>
                    <td align="center"><%=talkingWatchCalculatorTotal%></td>
                    <td align="center"><%=viADLEquipmentTotal%></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


