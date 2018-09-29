<%-- 
    Document   : AssistiveDevicesVIExcel
    Created on : Jan 4, 2011, 9:27:58 AM
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
    <body>
        <logic:present name="assistiveDevicesVIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="11"><b>Assistive Devices VI Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="11">
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
                    </td>
                </tr>
                <tr>
                    <td align="center"><b>S.No</b></td>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <td align="center"><b>Habitation</b></td>
                    <% } else if (!village_id.equals("null")) {%>
                    <td align="center"><b>Village</b></td>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <td align="center"><b>Mandal</b></td>
                    <% } else if (!district_id.equals("null")) {%>
                    <td><b>District</b></td>
                    <% }%>
                    <td align="center">White Cane/ Blind Stick</td>
                    <td align="center">Braille Equipments</td>
                    <td align="center">Arithmetic Frames/ Abacus</td>
                    <td align="center">Low Vision Aids (Spectacles, Magnifiers)</td>
                    <td align="center">Speech Synthesizer</td>
                    <td align="center">Braille Short Hand Machines/ Type Writers</td>
                    <td align="center">Talking Watch/ Calculator</td>
                    <td align="center">ADL Equipment</td>
                    <td align="center">Total</td>
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

