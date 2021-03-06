<%-- 
    Document   : AssistiveDevicesMRExcel
    Created on : Jan 4, 2011, 12:25:41 PM
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
            ArrayList assistiveDevicesMRList = new ArrayList();
            assistiveDevicesMRList = (ArrayList) session.getAttribute("assistiveDevicesMRList");
            int learningMaterialsTotal = 0, specialSoftwareTotal = 0, total = 0;
            int toysTotal = 0;
            Iterator iter = assistiveDevicesMRList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                learningMaterialsTotal = learningMaterialsTotal + totalDTO.getLearningMaterials();
                specialSoftwareTotal = specialSoftwareTotal + totalDTO.getSpecialSoftware();
                toysTotal = toysTotal + totalDTO.getToys();
                total = total + totalDTO.getTotal();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assistive Devices MR</title>
    </head>
    <body>
        <logic:present name="assistiveDevicesMRList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="60%">
                <tr>
                    <td align="center" colspan="6"><b>Assistive Devices MR Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <tr>
                    <td align="center" colspan="6">
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
                        &nbsp; Type of Disability: <font color="blue">Mental Retardation</font>
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
                    <td align="center"><b>District</b></td>
                    <% }%>
                    <td align="center"><b>Learning Materials</b></td>
                                <td align="center"><b>Special Software</b></td>
                                <td align="center"><b>Toys</b></td>
                                <td align="center"><b>Total</b></td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="assistiveDevicesMRList" scope="session">
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
                        <td align="center"><bean:write name="modify" property="learningMaterials"/></td>
                                    <td align="center"><bean:write name="modify" property="specialSoftware"/></td>
                                    <td align="center"><bean:write name="modify" property="toys"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center">Total</td>
                    <td align="center"><%=learningMaterialsTotal %></td>
                    <td align="center"><%=specialSoftwareTotal %></td>
                    <td align="center"><%=toysTotal %></td>

                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>

