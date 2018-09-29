<%--
    Document   : MaritalStatusExcel
    Created on : Jul 11, 2011, 12:35:22 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<html>
    <head>
<%
            int i = 1;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");
            String urban_id = (String) request.getParameter("urban_id");

%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <table cellspacing="0" cellpadding="0" border="1">
            <tr>
                <td align="center" colspan="7"><b>R3.4 : PWD's Marital Status Wise - Details</b> <br/>

                </td >
            </tr>

                <tr>

                <logic:present name="names">
                    <th style="text-align: center" colspan="11">
                        <bean:write name="names"/>
                    </th>
                </logic:present>
            </tr>

            <tr>
                <td align="center" ><b>S.No</b></td>
                <th ><bean:write name="ExcelHeader"/></th>
                <td align="center" ><b>Married</b></td>
                <td align="center" ><b>Un Married</b></td>
                <td align="center"><b>Divorcee</b></td>
                <td align="center" ><b>Widow</b></td>
                <td align="center" ><b>Widower</b></td>
            </tr>
            <bean:define id="marriedTotal" value="0"/>
            <bean:define id="unmarriedTotal" value="0"/>
            <bean:define id="divorceeTotal" value="0"/>
            <bean:define id="widowTotal" value="0"/>
            <bean:define id="widowerTotal" value="0"/>
            <logic:iterate name="maritalStatus" id="row" >
                <bean:define id="marriedTotal" value="${marriedTotal + row.married}" />
                <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.unmarried}" />
                <bean:define id="divorceeTotal" value="${divorceeTotal + row.divorcee}" />
                <bean:define id="widowTotal" value="${widowTotal + row.widow}" />
                <bean:define id="widowerTotal" value="${widowerTotal + row.widower}" />

                <tr>
                    <td class="formaltbg" align="center">
                        <%=i++%>
                    </td>
                    <td class="formaltbg">
                        ${row.name}
                    </td>
                    <td class="formaltbg" align="center">
                        ${row.married}
                    </td>
                    <td class="formaltbg" align="center">
                        ${row.unmarried}
                    </td>
                    <td class="formaltbg" align="center">
                        ${row.divorcee}
                    </td>
                    <td class="formaltbg" align="center">
                        ${row.widow}
                    </td>
                    <td class="formaltbg" align="center">
                        ${row.widower}
                    </td>
                </tr>

            </logic:iterate>
            <tr>
                <td class="formhdbg" colspan="2" align="center"><b>Total</b></td>
                <td class="formhdbg" align="center"><b>${marriedTotal}</b></td>
                <td class="formhdbg" align="center"><b>${unmarriedTotal}</b></td>
                <td class="formhdbg" align="center"><b>${divorceeTotal}</b></td>
                <td class="formhdbg" align="center"><b>${widowTotal}</b></td>
                <td class="formhdbg" align="center"><b>${widowerTotal}</b></td>

            </tr>
        </table>
        <%

                    //  session.removeAttribute("maritalStatus");
                    //      session.removeAttribute("areaDetails");

        %>

    </body>
</html>
