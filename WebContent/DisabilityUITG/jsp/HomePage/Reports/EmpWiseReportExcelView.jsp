<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
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
            String village_id = (String) request.getParameter("villagesId");



            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";

           

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="empWiseDetails">
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="90%">
                            <tr><td align="center" colspan="8">R3.5 :Employement Wise Report</td></tr>
                            <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="11">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>
                            </tr>
                            <tr>
                                <td align="center" class="formhdbg">S.No</td>
                                <td><bean:write name="ExcelHeader"/> </td>
                                <td align="center" class="formhdbg">Government</td>
                                <td align="center" class="formhdbg">Private</td>
                                <td align="center" class="formhdbg">Self-employed</td>
                                <td align="center" class="formhdbg">Un-employed</td>
                                <td align="center" class="formhdbg">Wage-employed</td>
                                <td align="center" class="formhdbg">NA</td>
                            </tr>
                            <bean:define id="govttotal" value="0"/>
                            <bean:define id="pritotal" value="0"/>
                            <bean:define id="selftotal" value="0"/>
                            <bean:define id="wagetotal" value="0"/>
                            <bean:define id="unemptotal" value="0"/>
                            <bean:define id="natotal" value="0"/>
                            <logic:iterate name="empWiseDetails" id="row">
                                <bean:define id="govttotal" value="${govttotal+row.gov}"/>
                                <bean:define id="pritotal" value="${pritotal+row.pri}"/>
                                <bean:define id="selftotal" value="${selftotal+row.self}"/>
                                <bean:define id="unemptotal" value="${unemptotal+row.unemp}"/>
                                <bean:define id="wagetotal" value="${wagetotal+row.wage}"/>
                                <bean:define id="natotal" value="${natotal+row.na}"/>
                                <tr>
                                    <td align="center">
                                        <%=i++%>
                                    </td>
                                    <td align="center">
                                        ${row.name}
                                    </td>
                                    <td >
                                        ${row.gov}
                                    </td>
                                    <td align="center">
                                        ${row.pri}
                                    </td>
                                    <td align="center">
                                        ${row.self}
                                    </td>
                                    <td align="center">
                                        ${row.unemp}
                                    </td>
                                    <td align="center">
                                        ${row.wage}
                                    </td>

                                    <td align="center">
                                        ${row.na}
                                    </td>

                                </tr>
                            </logic:iterate>
                            <tr>
                                <td class="formaltbg" align="center">

                                </td>
                                <td class="formaltbg" align="center">
                                    Total
                                </td>
                                <td class="formaltbg" align="center">${govttotal}</td>
                                <td class="formaltbg" align="center">${pritotal}</td>
                                <td class="formaltbg" align="center">${selftotal}</td>
                                <td class="formaltbg" align="center">${unemptotal}</td>
                                <td class="formaltbg" align="center">${wagetotal}</td>
                                <td class="formaltbg" align="center">${natotal}</td>
                            </tr>
                        </table>
                    </td></tr></table><br/>
                </logic:notEmpty>
    </body>
</html>