<%-- 
    Document   : AssistiveDevicesOrthosisPrint
    Created on : Feb 17, 2011, 10:08:34 AM
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
            ArrayList assistiveDevicesOrthosisList = new ArrayList();
            assistiveDevicesOrthosisList = (ArrayList) session.getAttribute("assistiveDevicesOrthosisList");
            int aeroplaneSplintTotal = 0, figureEightSplintTotal = 0, total = 0;
            int forearmSplintTotal = 0, handSplintTotal = 0, hkafohTotal = 0;
            int kafoTotal = 0, afoTotal = 0, kneeOrthosisTotal = 0;
            int dbSplintTotal = 0, modifiedShoeTotal = 0, serialCastingCTEVTotal = 0;
            int cervicalCollarTotal = 0, lsBraceTotal = 0, tlsoBraceForScoliosisKyphosisTotal = 0;
            Iterator iter = assistiveDevicesOrthosisList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                aeroplaneSplintTotal = aeroplaneSplintTotal + totalDTO.getAeroplaneSplint();
                figureEightSplintTotal = figureEightSplintTotal + totalDTO.getFigureEightSplint();
                forearmSplintTotal = forearmSplintTotal + totalDTO.getForearmSplint();
                handSplintTotal = handSplintTotal + totalDTO.getHandSplint();
                hkafohTotal = hkafohTotal + totalDTO.getHkafoh();
                kafoTotal = kafoTotal + totalDTO.getKafo();
                afoTotal = afoTotal + totalDTO.getAfo();
                kneeOrthosisTotal = kneeOrthosisTotal + totalDTO.getKneeOrthosis();
                dbSplintTotal = dbSplintTotal + totalDTO.getDbSplint();
                modifiedShoeTotal = modifiedShoeTotal + totalDTO.getModifiedShoe();
                serialCastingCTEVTotal = serialCastingCTEVTotal + totalDTO.getSerialCastingCTEV();
                cervicalCollarTotal = cervicalCollarTotal + totalDTO.getCervicalCollar();
                lsBraceTotal = lsBraceTotal + totalDTO.getLsBrace();
                tlsoBraceForScoliosisKyphosisTotal = tlsoBraceForScoliosisKyphosisTotal + totalDTO.getTlsoBraceForScoliosisKyphosis();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orthosis</title>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="assistiveDevicesOrthosisList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                <tr>
                    <th align="center" colspan="17"><b>Assistive Devices Orthosis  Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="17">
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
                    <th align="center" colspan="4">Orthosis/ Splint for Upper Extremirty</th>
                    <th align="center" colspan="7">Orthosis/ Splint for Lower Extremity</th>
                    <th align="center" colspan="3">Spinal Orthosis</th>
                    <th align="center" rowspan=2>Total</th>
                </tr>
                <tr>
                    <th align="center">Aeroplane Splint</th>
                    <th align="center">Figure 8 Splint</th>
                    <th align="center">Forearm Splint</th>
                    <th align="center">Hand Splint</th>
                    <th align="center">HKAFO</th>
                    <th align="center">KAFO</th>
                    <th align="center">AFO</th>
                    <th align="center">Knee orthosis</th>
                    <th align="center">DB Splint</th>
                    <th align="center">Modified Shoe</th>
                    <th align="center">Serial Casting(CTEV)</th>
                    <th align="center">Cervical Collar</th>
                    <th align="center">LS Brace</th>
                    <th align="center">TLSO Brace(For scoliosis/ Kyphosis)</th>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesOrthosisList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="aeroplaneSplint"/></td>
                        <td align="center"><bean:write name="modify" property="figureEightSplint"/></td>
                        <td align="center"><bean:write name="modify" property="forearmSplint"/></td>
                        <td align="center"><bean:write name="modify" property="handSplint"/></td>
                        <td align="center"><bean:write name="modify" property="hkafoh"/></td>
                        <td align="center"><bean:write name="modify" property="kafo"/></td>
                        <td align="center"><bean:write name="modify" property="afo"/></td>
                        <td align="center"><bean:write name="modify" property="kneeOrthosis"/></td>
                        <td align="center"><bean:write name="modify" property="dbSplint"/></td>
                        <td align="center"><bean:write name="modify" property="modifiedShoe"/></td>
                        <td align="center"><bean:write name="modify" property="serialCastingCTEV"/></td>
                        <td align="center"><bean:write name="modify" property="cervicalCollar"/></td>
                        <td align="center"><bean:write name="modify" property="lsBrace"/></td>
                        <td align="center"><bean:write name="modify" property="tlsoBraceForScoliosisKyphosis"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=aeroplaneSplintTotal%></b></td>
                    <td align="center"><b><%=figureEightSplintTotal%></b></td>
                    <td align="center"><b><%=forearmSplintTotal%></b></td>
                    <td align="center"><b><%=handSplintTotal%></b></td>
                    <td align="center"><b><%=hkafohTotal%></b></td>
                    <td align="center"><b><%=kafoTotal%></b></td>
                    <td align="center"><b><%=afoTotal%></b></td>
                    <td align="center"><b><%=kneeOrthosisTotal%></b></td>
                    <td align="center"><b><%=dbSplintTotal%></b></td>
                    <td align="center"><b><%=modifiedShoeTotal%></b></td>
                    <td align="center"><b><%=serialCastingCTEVTotal%></b></td>
                    <td align="center"><b><%=cervicalCollarTotal%></b></td>
                    <td align="center"><b><%=lsBraceTotal%></b></td>
                    <td align="center"><b><%=tlsoBraceForScoliosisKyphosisTotal%></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

