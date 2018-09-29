<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>


<%--
    Document   : CasteWiseReportPrintView
    Created on : Jun 22, 2011, 12:23:41 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
                <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="empWiseDetails">
              <p>R3.5 : PWD's Employment Status - Details</p>
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
              
               <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="11">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>
                            </tr>

                <tr>

                   <th align="center" ><font >S.No</font></th>
                    <th><bean:write name="ExcelHeader"/> </th>
                   <th align="center" ><font >Government</font></th>
                   <th align="center" ><font >Private</font></th>
                   <th align="center" ><font >Self-employed</font></th>
                   <th align="center" ><font >Un-employed</font></th>
                   <th align="center" ><font >Wage-employed</font></th>
                   <th align="center" ><font >NA</font></th>

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
                        <td style="text-align:center">
                            <%=i++%>
                        </td>
                        <td align="center">
                            ${row.name}
                        </td>
                        <td style="text-align:center">
                            ${row.gov}
                        </td>
                        <td style="text-align:center">
                            ${row.pri}
                        </td>
                        <td style="text-align:center">
                            ${row.self}
                        </td>
                        <td style="text-align:center">
                            ${row.unemp}
                        </td>
                        <td style="text-align:center">
                            ${row.wage}
                        </td>

                        <td style="text-align:center">
                            ${row.na}
                        </td>
                    </tr>

                </logic:iterate>
                <tr>

                    <th   style="text-align:center"colspan="2">
                        Total
                    </th>
                    <th  style="text-align:center">${govttotal}</th>
                    <th  style="text-align:center">${pritotal}</th>
                    <th  style="text-align:center">${selftotal}</th>
                    <th  style="text-align:center">${unemptotal}</th>
                    <th  style="text-align:center">${wagetotal}</th>
                    <th  style="text-align:center">${natotal}</th>
                </tr>

            </table>

        </logic:notEmpty>
    </body>
</html>
