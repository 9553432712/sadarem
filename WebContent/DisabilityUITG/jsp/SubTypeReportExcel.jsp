<%-- 
    Document   : SubTypeReportExcel
    Created on : Feb 20, 2013, 1:31:21 PM
    Author     : 747577
--%>

<%@page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%int i = 1;
            ArrayList list = (ArrayList) session.getAttribute("addressDetailsLists");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <table  border="0" align="center" cellspacing="1" cellpadding="4" class="tbl_bg2" width="100%">
            <tr>
                <td class="formhdbg" align="center" colspan="12">Details of Persons with SubType Reports Personal Details</td>
            </tr>

            <tr>
                <th class="formhdbg" align="center">
                    S No
                </th>
                <th class="formhdbg" align="center">
                    SADAREM ID
                </th>
                <th class="formhdbg" align="center">
                    Name
                </th>
                <th class="formhdbg" align="center">
                    Relation Name
                </th>
                <th class="formhdbg" align="center">
                    Age
                </th>
                <th class="formhdbg" align="center">
                    Caste
                </th>
                <th class="formhdbg" align="center">
                    Address
                </th>
            </tr>
            <% for (int j = 0; j < list.size(); j++) {
                            Map m = (Map) list.get(j);
            %>
            <tr>
                <td class="formaltbg" align="center">
                    <%=i++%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("sadaremcode")%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("name")%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("relationname")%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("age")%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("caste")%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("address")%>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
