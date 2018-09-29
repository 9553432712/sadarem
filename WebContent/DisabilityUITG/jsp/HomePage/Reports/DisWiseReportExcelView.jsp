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
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="90%">
                            <tr> <td align="center" colspan="16"><b>R5.1 : Appellate Authority Registred Status Report </b>
                                    <logic:present name="names">
                                        <bean:write name="names"/>
                                    </logic:present>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" class="formhdbg">S.No</td>
                                 <logic:present name="ExcelHeader">
                                        <th ><bean:write name="ExcelHeader"/> </th>
                                    </logic:present>
                                <td align="center" class="formhdbg">Locomotor</td>
                                <td align="center" class="formhdbg">Visual Impairment</td>
                                <td align="center" class="formhdbg">Hearing Impairment</td>
                                <td align="center" class="formhdbg">Mental Retardation</td>
                                <td align="center" class="formhdbg">Mental Illness</td>
                                <td align="center" class="formhdbg">Multiple Disability</td>
                                <td align="center" class="formhdbg">Total</td>

                            </tr>
                            <bean:define id="ohtotal" value="0"/>
                            <bean:define id="vitotal" value="0"/>
                            <bean:define id="hitotal" value="0"/>
                            <bean:define id="mrtotal" value="0"/>
                            <bean:define id="mitotal" value="0"/>
                            <bean:define id="total" value="0"/>
                            <bean:define id="rowwisetotal" value="0"/>
                            <logic:iterate name="empWiseDetails" id="row">
                                <bean:define id="rowwisetotal" value="${row.oh+row.vi+row.hi+row.mr+row.mi+row.multi}"/>

                                <bean:define id="ohtotal" value="${ohtotal+row.oh}"/>
                                <bean:define id="vitotal" value="${vitotal+row.vi}"/>
                                <bean:define id="hitotal" value="${hitotal+row.hi}"/>
                                <bean:define id="mrtotal" value="${mrtotal+row.mr}"/>
                                <bean:define id="mitotal" value="${mitotal+row.mi}"/>
                                <bean:define id="total" value="${total+row.multi}"/>
                                <tr>
                                    <td align="center">
                                        <%=i++%>
                                    </td>
                                    <td align="center">
                                        ${row.name}
                                    </td>
                                    <td >
                                        ${row.oh}
                                    </td>
                                    <td align="center">
                                        ${row.vi}
                                    </td>
                                    <td align="center">
                                        ${row.hi}
                                    </td>
                                    <td align="center">
                                        ${row.mr}
                                    </td>
                                    <td align="center">
                                        ${row.mi}
                                    </td>

                                    <td align="center">
                                        ${row.multi}
                                    </td>

                                    <td align="center">
                                        ${rowwisetotal}
                                    </td>


                                </tr>
                                <bean:define id="rowwisetotal" value="0"/>
                            </logic:iterate>
                            <tr>
                                <td class="formaltbg" align="center">

                                </td>
                                <td class="formaltbg" align="center">
                                    Total
                                </td>
                                <td class="formaltbg" align="center">${ohtotal}</td>
                                <td class="formaltbg" align="center">${vitotal}</td>
                                <td class="formaltbg" align="center">${hitotal}</td>
                                <td class="formaltbg" align="center">${mrtotal}</td>
                                <td class="formaltbg" align="center">${mitotal}</td>
                                <td class="formaltbg" align="center">${total}</td>
                                <td class="formaltbg" align="center">${ohtotal+vitotal+hitotal+mrtotal+mitotal+total}</td>
                            </tr>
                        </table>
                    </td></tr></table><br/>
                </logic:notEmpty>
    </body>
</html>