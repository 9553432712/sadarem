<%--
    Document   : pwdValAbstReport
    Created on : Mar 18, 2015, 12:01:06 AM
    Author     : 567999
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%
    String fate = request.getAttribute("fdate").toString();
    String tate = request.getAttribute("tdate").toString();
%>
<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
        <html:form action="/pwdValAbstReport.do" >
            <html:hidden property="mode"/>
            <table   align="center" cellspacing="1" border="2" cellpadding="4"  width="90%">
                <tr>
                    <th colspan="11">
                        R.1.6 PwD Validation Abstract Rural Report
                    </th>
                </tr>
                 <tr>
                    <th colspan="10">
                        From Date - <%=fate%> To Date - <%=tate%>
                    </th>
                </tr>
                <tr>
                    <th rowspan="2" width="3%">
                        S No
                    </th>
                    <th rowspan="2"  width="12%">
                        District
                    </th>
                    <th rowspan="2"  width="8%">
                        Total Assessed
                    </th>
                    <th rowspan="2"  width="8%">
                        Total Validation  Done
                    </th>
                    <th rowspan="2"  width="8%">
                        AADHAR Not  Tagged
                    </th>
                    <th colspan="3">
                        PwD
                    </th>
                   <%-- <th colspan="2">
                        PMJDY Details
                    </th>--%>
                    <th colspan="2">
                        AASARA
                    </th>
                </tr>
                <tr>
                    <th  width="8%">
                        Alive
                    </th>
                    <th width="8%">
                        Dead
                    </th>
                    <th width="8%">
                        Tagged to SHG Group
                    </th>
                   <%-- <th width="8%">
                        Not Having Account
                    </th>
                    <th width="8%">
                        Having Account
                    </th>--%>
                    <th width="8%">
                        Not Covered
                    </th>
                    <th width="8%">
                        Covered
                    </th>
                </tr>
                <logic:present name="report">
                    <logic:iterate id="row" name="report" scope="request">
                        <tr>

                            <td style="text-align: center">
                                ${row.sno}
                            </td>
                            <td style="text-align: left">
                                ${row.disname}
                            </td>
                             <td style="text-align: center">
                                ${row.totalassessed}
                            </td>
                            <td style="text-align: center">
                                ${row.total}
                            </td>
                            <td style="text-align: center">
                                ${row.taged}
                            </td>
                            <td style="text-align: center">
                                ${row.alive}
                            </td>
                            <td style="text-align: center">
                                ${row.dead}
                            </td>
                            <td style="text-align: center">
                                ${row.tagshg}
                            </td>
                           <%-- <td style="text-align: center">
                                ${row.noaccount}
                            </td>
                            <td style="text-align: center">
                                ${row.haveaccount}
                            </td>--%>
                            <td style="text-align: center">
                                ${row.notcover}
                            </td>
                            <td style="text-align: center">
                                ${row.conver}
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:present>
                        <tr>
                        <th colspan="2">Total</th>
                         <th style="text-align:center">${row.t11}</th>
                        <th style="text-align:center">${row.t2}</th>
                        <th style="text-align:center">${row.t3}</th>
                        <th style="text-align:center">${row.t4}</th>
                        <th style="text-align:center">${row.t5}</th>
                        <th style="text-align:center">${row.t6}</th>
                       <%-- <th style="text-align:center">${row.t7}</th>
                        <th style="text-align:center">${row.t8}</th>--%>
                        <th style="text-align:center">${row.t9}</th>
                        <th style="text-align:center">${row.t10}</th>

                    </tr>
            </table>

        </html:form>
    </body>
</html:html>



