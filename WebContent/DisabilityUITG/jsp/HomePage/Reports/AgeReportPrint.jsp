<%-- 
    Document   : AgeReportPrint
    Created on : Jan 4, 2012, 5:49:11 PM
    Author     : 490058
--%>

<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.text.SimpleDateFormat"%>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villagesId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;
            String f = null, t = null;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String name = (String) request.getParameter("names");
            String dis = (String) request.getParameter("disability");
            if (fromdate != null && fromdate.contains("-")) {
                f = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
            }
            if (todate != null && todate.contains("-")) {
                t = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
            }



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:present name="empWiseDetails">
            <logic:empty name="empWiseDetails">
                <p id="errmsg">No Data</p>
                        </logic:empty>
                    </logic:present>
        <p>Age wise Report</p>
        <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
            <logic:present name="names">
                <tr><th colspan="7">
                        <bean:write name="names"/>
                    </th></tr>
                </logic:present>
        </table>
        <logic:notEmpty name="empWiseDetails">
            <bean:define id="reg" value="0"/>
            <bean:define id="ass" value="0"/>
            <bean:define id="eli" value="0"/>
            <bean:define id="ar" value="0"/>
            <bean:define id="dr" value="0"/>
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                <!--  <tr><td class="formhdbg" align="center" colspan="20">Age wise Report</td></tr>-->
                <tr>
                    <th align="center" class="formhdbg" rowspan="2">S.No</th>
                    <logic:present name="ExcelHeader">
                        <th rowspan="2"><bean:write name="ExcelHeader"/> </th>
                    </logic:present>
                    <th align="center" class="formhdbg" rowspan="2">Total Registered</th>
                    <th align="center" class="formhdbg" rowspan="2">Total Assessed </th>
                    <th align="center" class="formhdbg" rowspan="2">Total Eligible</th>
                    <th align="center" class="formhdbg" colspan="2">Total Rejected</th>

                </tr>

                <tr>
                    <th align="center" class="formhdbg" >AR</th>
                    <th align="center" class="formhdbg" >DR</th>
                </tr>

                <% int j = 1;%>


                <logic:iterate name="empWiseDetails" id="row">
                    <bean:define id="fDate" value="${row.fDate}"/>
                    <bean:define id="tDate" value="${row.tDate}"/>
                    <bean:define id="reg" value="${row.treg+reg}"/>
                    <bean:define id="ass" value="${row.tass+ass}"/>
                    <bean:define id="eli" value="${row.teli+eli}"/>
                    <bean:define id="ar" value="${row.tar+ar}"/>
                    <bean:define id="dr" value="${row.tdr+dr}"/>

                    <%if (j % 2 == 1) {
                                        j++;%>
                    <tr>
                        <td class="formbg" align="center">
                            <%=i++%>
                        </td>
                        <td class="formbg">
                            ${row.name}
                        </td>
                        <td class="formbg" style="text-align: center">
                            ${row.treg}
                        </td>
                        <td class="formbg" style="text-align: center">
                            ${row.tass}
                        </td>
                        <td class="formbg" style="text-align: center">
                            ${row.teli}
                        </td>
                        <td class="formbg" style="text-align: center">
                            ${row.tar}
                        </td>
                        <td class="formbg" style="text-align: center">
                            ${row.tdr}
                        </td>


                    </tr>
                    <%} else if (j % 2 == 0) {
                        j++;
                    %>


                    <tr>
                        <td class="formaltbg" align="center">
                            <%=i++%>
                        </td>
                        <td class="formaltbg">
                            ${row.name}
                        </td>
                        <td class="formaltbg" style="text-align: center">
                            ${row.treg}
                        </td>
                        <td class="formaltbg" style="text-align: center">
                            ${row.tass}
                        </td>
                        <td class="formaltbg" style="text-align: center">
                            ${row.teli}
                        </td>
                        <td class="formaltbg" style="text-align: center">
                            ${row.tar}
                        </td>
                        <td class="formaltbg" style="text-align: center">
                            ${row.tdr}
                        </td>

                    </tr>

                    <%}%>


                </logic:iterate>
                <tr>
                    <th class="formbg" align="center">
                        &nbsp;
                    </th>
                    <th class="formbg">
                        Total
                    </th>
                    <th class="formbg1" align="center">
                        ${reg}
                    </th>
                    <th class="formbg1" align="center">
                        ${ass}
                    </th>
                    <th class="formbg1" align="center">
                        ${eli}
                    </th>
                    <th class="formbg1" align="center">
                        ${ar}
                    </th>
                    <th class="formbg1" align="center">
                        ${dr}
                    </th>
                </tr>
            </table>
        </logic:notEmpty>

    </body>
</html>
