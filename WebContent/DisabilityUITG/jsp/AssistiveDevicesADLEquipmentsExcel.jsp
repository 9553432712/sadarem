<%-- 
    Document   : AssistiveDevicesADLEquipmentsExcel
    Created on : Jan 3, 2011, 8:50:56 AM
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
            ArrayList adlEquipmentsList = new ArrayList();

            adlEquipmentsList = (ArrayList) session.getAttribute("adlEquipmentsList");
            int feedingTotal = 0, toiletingBathingTotal = 0, total = 0;
            int brushingTotal = 0, combingTotal = 0, dressingTotal = 0;
            int writingTotal = 0, drivingCyclingTotal = 0, bedTransferTotal = 0;
            Iterator iter = adlEquipmentsList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                feedingTotal = feedingTotal + totalDTO.getFeeding();
                toiletingBathingTotal = toiletingBathingTotal + totalDTO.getToiletingBathing();
                brushingTotal = brushingTotal + totalDTO.getBrushing();
                combingTotal = combingTotal + totalDTO.getCombing();
                dressingTotal = dressingTotal + totalDTO.getDressing();
                writingTotal = writingTotal + totalDTO.getWriting();
                drivingCyclingTotal = drivingCyclingTotal + totalDTO.getDrivingCycling();
                bedTransferTotal = bedTransferTotal + totalDTO.getBedTransfer();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADL</title>
    </head>
    <body>
        <logic:present name="adlEquipmentsList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <td align="center" colspan="11"><b>Assistive Devices ADL Equipment Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="11">
                        <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>, Habitation:<font color="blue">ALL</font>
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
                    <td align="center">Feeding</td>
                    <td align="center">Toileting/ Bathing</td>
                    <td align="center">Brushing</td>
                    <td align="center">Combing</td>
                    <td align="center">Dressing</td>
                    <td align="center">Writing</td>
                    <td align="center">Driving/ Cycling</td>
                    <td align="center">Bed Transfer</td>
                    <td align="center">Total</td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="adlEquipmentsList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="feeding"/></td>
                        <td align="center"><bean:write name="modify" property="toiletingBathing"/></td>
                        <td align="center"><bean:write name="modify" property="brushing"/></td>
                        <td align="center"><bean:write name="modify" property="combing"/></td>
                        <td align="center"><bean:write name="modify" property="dressing"/></td>
                        <td align="center"><bean:write name="modify" property="writing"/></td>
                        <td align="center"><bean:write name="modify" property="drivingCycling"/></td>
                        <td align="center"><bean:write name="modify" property="bedTransfer"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center">Total</td>
                    <td align="center"><%=feedingTotal%></td>
                    <td align="center"><%=toiletingBathingTotal%></td>
                    <td align="center"><%=brushingTotal%></td>
                    <td align="center"><%=combingTotal%></td>
                    <td align="center"><%=dressingTotal%></td>
                    <td align="center"><%=writingTotal%></td>
                    <td align="center"><%=drivingCyclingTotal%></td>
                    <td align="center"><%=bedTransferTotal%></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>
