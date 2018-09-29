<%--
    Document   : CasteWiseReportExcelView
    Created on : Jun 22, 2011, 12:23:41 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");

            String districtName = null;
            String mandalName = null;

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
            if (getAddressList.size() == 1) {
                districtName = (String) getAddressList.get(0);
            } else if (getAddressList.size() == 2) {
                districtName = (String) getAddressList.get(0);
                mandalName = (String) getAddressList.get(1);
            }
%>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="scheduleData" scope="request">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" >

                <tr>
                    <td align="center" colspan="3" ><b>Appellate Authority Schedule Report</b></td>
                </tr>
                <tr>
                    <td align="center" colspan="3">
                        <%

                                    if (mandalName != null) {
                                        if (!mandalName.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        <% }
                                    }
                                    if (districtName != null && mandalName == null) {
                                        if (!districtName.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>,
                        <% }
                                    }%>
                    </td>
                </tr>

                <tr>

                    <td align="center" ><b>S.No</b></td>
                    <td align="center"><b>Village Name</b></td>
                    <td align="center"><b>Schedule Date</b></td>


                </tr>


                <logic:iterate name="scheduleData" id="row" scope="request">

                    <tr>
                        <td align="center" style="width: 5%;">
                            <%=i++%>
                        </td>
                        <td style="width: 15%;">
                            ${row.village_name}
                        </td>
                        <td align="center" style="width: 15%;">
                            ${row.scheduleDate}
                        </td>

                    </tr>

                </logic:iterate>


            </table>


        </logic:notEmpty>

    </body>
</html>

