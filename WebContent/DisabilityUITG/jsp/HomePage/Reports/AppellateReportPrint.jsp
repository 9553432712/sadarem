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
        <logic:notEmpty name="empWiseDetails">
            <p>R2.3   District wise Reassessed PWD's Cumulative Report</p>
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">

                <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="20">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>
                </tr>
                <tr>
                    <th align="center"  rowspan="2">S.No</th>
                    <logic:present name="district">
                        <th rowspan="2">District</th>
                    </logic:present>
                    <logic:present name="mandal">
                        <th rowspan="2">Mandal</th>
                    </logic:present>
                    <logic:present name="village">
                        <th rowspan="2">Village</th>
                    </logic:present>
                    <logic:present name="habitation">
                        <th rowspan="2">Habitation</th>
                    </logic:present>
                    <th align="center"  rowspan="2">Total Registered</th>
                    <th align="center"  rowspan="2">Total Assessed </th>
                    <th align="center"  rowspan="2">Total Eligible</th>
                    <th align="center"  rowspan="2">Total Rejected</th>

                    <th align="center"  colspan="2" >Locomotor</th>
                    <th align="center"  colspan="2" >Visual Impairment</th>
                    <th align="center"  colspan="2" >Hearing Impairment</th>
                    <th align="center"  colspan="2" >Mental Retardation</th>
                    <th align="center"  colspan="2" >Menal Illness</th>
                    <th align="center"  colspan="2" >Multiple Disability</th>
                </tr>
                <tr>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                    <th align="center"  >Eligible</th>
                    <th align="center"  >Rejected</th>

                </tr>
                <logic:iterate name="empWiseDetails" id="row">

                    <bean:define id="fDate" value="${row.fDate}"/>
                    <bean:define id="tDate" value="${row.tDate}"/>

                    <tr>
                        <td  align="center">
                            <%=i++%>
                        </td>
                        <td >
                            ${row.name}
                        </td>
                        <td  align="center">
                            ${row.treg}
                        </td>
                        <td  align="center">
                            ${row.tass}
                        </td>
                        <td  align="center">
                            ${row.teli}
                        </td>
                        <td  align="center">
                            ${row.trej}
                        </td>
                        <td  align="center">
                            ${row.loe}
                        </td>
                        <td  align="center">
                            ${row.lor}
                        </td>
                        <td  align="center">
                            ${row.ve}
                        </td>
                        <td  align="center">
                            ${row.vr}
                        </td>
                        <td  align="center">
                            ${row.he}
                        </td> <td  align="center">
                            ${row.hr}
                        </td>
                        <td  align="center">
                            ${row.me}
                        </td>
                        <td  align="center">
                            ${row.mr}
                        </td>
                        <td  align="center">
                            ${row.mie}
                        </td>
                        <td  align="center">
                            ${row.mir}
                        </td>
                        <td  align="center">
                            ${row.mue}
                        </td>
                        <td  align="center">
                            ${row.mur}
                        </td>
                    </tr>
                </logic:iterate>
            </table>
            <br/>

        </logic:notEmpty>
    </body>
</html>