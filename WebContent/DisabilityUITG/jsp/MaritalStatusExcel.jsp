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
                    String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");

                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                     String village_id = (String) request.getParameter("vID");
                  

                    int i = 1;

                    String districtName = "ALL";
                    String mandalName = "ALL";
                    String villageName = "ALL";
                    String habName = "ALL";

                    ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) session.getAttribute("areaDetails");

                    if (getAddressList.size() > 0) {
                        if (getAddressList.size() == 1) {
                            districtName = (String) getAddressList.get(0);
                        } else if (getAddressList.size() == 2) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                        } else if (getAddressList.size() == 3) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                        } else if (getAddressList.size() == 4) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                            habName = (String) getAddressList.get(3);
                        }
                    }

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <table cellspacing="0" cellpadding="0" border="1">
            <tr>
                <td align="center" colspan="7"><b>Marital Status Details form <%=fromdate%> To <%=todate%> .</b> <br/>
                    
                </td >
            </tr>
            <tr>
                <td align="center" colspan="7">
                    District :
                    <% if (district_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=districtName%></font>
                    <%}%>
                    ,
                    Mandal :
                    <% if (mandal_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=mandalName%></font>
                    <%}%>
                    ,
                    Village :
                    <% if (village_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=villageName%></font>
                    <%}%>
                    .
                </td>
            </tr>
            <tr>
                <td align="center" ><b>S.No</b></td>
                 <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                <td align="center"><b>Habitation</b></td>
                <% }
                else if (!mandal_id.equals("null") && !mandal_id.equals("0")) {%>
                <td align="center"><b>Village</b></td>
                <% } else if (!mandal_id.equals("null")) {%>
                <td align="center"><b>Mandal</b></td>
                <% } else if (!district_id.equals("null")) {%>
                <td align="center"><b>District</b></td>
                <% }%>
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
            <logic:iterate name="maritalStatus" id="row" scope="session">
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
