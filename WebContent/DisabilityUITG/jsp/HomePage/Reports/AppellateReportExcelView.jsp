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
<%@page import="java.util.Iterator" %><%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
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
            String name = (String) request.getParameter("name");
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
    <body>
        <logic:notEmpty name="empWiseDetails">
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <p>R2.3   District wise Reassessed PWD's Cumulative Report</p>
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="90%">
                            <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="20">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present></tr>
                            <tr>
                                <td align="center" class="formhdbg" rowspan="2">S.No</td>
                               <logic:present name="ExcelHeader">
                                   <th rowspan="2">
                                        <bean:write name="ExcelHeader"/>
                                    </th>
                                </logic:present>
                                <td align="center" class="formhdbg" rowspan="2">Total Registered</td>
                                <td align="center" class="formhdbg" rowspan="2">Total Assessed </td>
                                <td align="center" class="formhdbg" rowspan="2">Total Eligible</td>
                                <td align="center" class="formhdbg" rowspan="2">Total Rejected</td>

                                <td align="center" class="formhdbg" colspan="2" >Locomotor</td>
                                <td align="center" class="formhdbg" colspan="2" >Visual Impairment</td>
                                <td align="center" class="formhdbg" colspan="2" >Hearing Impairment</td>
                                <td align="center" class="formhdbg" colspan="2" >Mental Retardation</td>
                                <td align="center" class="formhdbg" colspan="2" >Menal Illness</td>
                                <td align="center" class="formhdbg" colspan="2" >Multiple Disability</td>


                            </tr>

                            <tr>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                                <td align="center" class="formhdbg" >Eligible</td>
                                <td align="center" class="formhdbg" >Rejected</td>

                            </tr>




                            <logic:iterate name="empWiseDetails" id="row">



                                <bean:define id="fDate" value="${row.fDate}"/>
                                <bean:define id="tDate" value="${row.tDate}"/>

                                <tr>
                                    <td class="formaltbg" align="center">
                                        <%=i++%>
                                    </td>
                                    <td class="formaltbg">
                                        ${row.name}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.treg}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.tass}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.teli}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.trej}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.loe}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.lor}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.ve}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.vr}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.he}
                                    </td> <td class="formaltbg" align="center">
                                        ${row.hr}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.me}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.mr}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.mie}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.mir}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.mue}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.mur}
                                    </td>


                                </tr>


                            </logic:iterate>


                        </table>
                    </td></tr></table><br/>
                </logic:notEmpty>
    </body>
    <%session.removeAttribute("empWiseDetails");%>
</html>