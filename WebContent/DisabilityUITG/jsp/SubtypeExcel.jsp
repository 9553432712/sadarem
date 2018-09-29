<%-- 
    Document   : SubtypeExcel
    Created on : Feb 27, 2013, 11:45:33 AM
    Author     : 747577
--%>

<%@page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<%
            int i = 1;
            String type = null;
            ArrayList list = (ArrayList) session.getAttribute("subTypeReportListExcel");
            String did = session.getAttribute("did").toString();
            String mid = session.getAttribute("mid").toString();
            String vid = session.getAttribute("vid").toString();
            String hid = session.getAttribute("hid").toString();


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>

        <table align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
            <tr>
                <td class="formhdbg" align="center" colspan="12">Details of Persons with SubType Reports</td>
            </tr>
            <tr>
                <td class="formhdbg" align="center">
                    S No
                </td>
                <td class="formhdbg" align="center">
                    <% if (!did.equals("0") && mid.equals("0")) {
                                    type = null;
                                    type = "Mandal";
                                    out.print(type);
                                } else if (!did.equals("0") && !mid.equals("0") && vid.equals("0")) {
                                    type = null;
                                    type = "Village";
                                    out.print(type);
                                } else if (!vid.equals("0") && !mid.equals("0") && hid.equals("0")) {
                                    type = null;
                                    type = "Habitation";
                                    out.print(type);
                                } else if (!hid.equals("0") && !vid.equals("0") && !mid.equals("0")) {
                                    type = null;
                                    type = "Habitation";
                                    out.print(type);
                                }
                    %>
                </td>
                <td class="formhdbg" align="center">
                    Count
                </td>

            </tr>
            <% for (int j = 0; j < list.size(); j++) {
                            Map m = (Map) list.get(j);
            %>

            <tr>
                <td class="formaltbg" align="center">
                    <%=i++%>
                </td>
                <td class="formaltbg" align="left">
                    <%=m.get("name")%>
                </td>

                <td width="12%" class="formaltbg"  align="center">
                    <%=m.get("count")%>
                </td>

            </tr>
            <%}%>

        </table><br/>


    </body>
</html>
