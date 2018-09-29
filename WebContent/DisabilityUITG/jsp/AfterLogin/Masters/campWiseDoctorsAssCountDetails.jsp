<%--
    Document   : campWiseDoctorsCount
    Created on : Jul 17, 2012, 11:50:32 AM
    Author     : 693461
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>

<%
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::SADAREM:::</title>

    </head>
    <body>
        <html:form action="/campWiseDoctorsCount">
            <html:hidden property="mode"/>
            <logic:present name="list">
                <br/>
                <logic:present name="msg">
                    <center><font color="red">${msg}</font></center>
                </logic:present>
                <center>
                    <br><font style="font-size: 13px; font-weight: bold;">Camp Date: <bean:write name="date"/></font><br/><br>
                </center>
                <table  align="center" cellspacing="0" border="0" cellpadding="0" width="85%" class="table">
                    <tr>
                        <th class="formhdbg" align="center">
                            Doctor Name
                        </th>
                        <th class="formhdbg" align="center">
                            Doctor Registration Number
                        </th>
                        <th class="formhdbg" align="center">
                            Doctor Designation
                        </th>
                        <th class="formhdbg" align="center">
                            Type of Disability
                        </th>
                        <th class="formhdbg" align="center">
                            AssesedCountOfPwD's
                        </th>
                    </tr>
                    <logic:iterate name="list" id="row">
                        <tr>
                            <td class="formaltbg" align="left">
                                ${row.doctorName}
                            </td>
                            <td class="formaltbg" align="center">
                                ${row.doctorRegNumber}
                            </td>
                            <td class="formaltbg" align="left">
                                ${row.desig}
                            </td>
                            <td class="formaltbg" align="left">
                                ${row.disability}
                            </td>
                            <td class="formaltbg" align="center" >
                                ${row.count}
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </logic:present>

        </html:form>
    </body>
</html>
