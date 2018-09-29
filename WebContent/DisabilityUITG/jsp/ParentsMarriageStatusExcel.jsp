<%-- 
    Document   : ParentsMarriageStatusExcel
    Created on : Jul 12, 2011, 6:34:00 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");
            String fromDate = (String)request.getParameter("fromdate");
            String toDate = (String)request.getParameter("todate");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;

            ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) session.getAttribute("areaDetails");
                    if (getAddressList != null) {
                        if (getAddressList.size() == 1) {
                            districtName = (String) getAddressList.get(0);
                        } else if (getAddressList.size() == 2) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                        } else if (getAddressList.size() == 3) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                        }
                    }
            


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="parentsMarriage">
            <table border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" >
                            <tr><td align="center" colspan="5"><b>Consanguineous Marriage of Parents Report from <%=fromDate%> to <%=toDate%></b></td></tr>
                            <tr>
                                <td align="center" colspan="5">
                                    <%
                                                if (villageName != null) {
                                                    if (!villageName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                                    Village: <font color="blue"><%=villageName%></font>, Habitations: <font color="blue">ALL</font>.
                                    <% }
                                                }
                                                if (mandalName != null && villageName == null) {
                                                    if (!mandalName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>, Village: <font color="blue">ALL</font>.
                                    <% }
                                                }
                                                if (districtName != null && mandalName == null) {
                                                    if (!districtName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue">ALL</font>.
                                    <% }
                                                }%>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" class="formhdbg" style="width: 5%"><b>S.No</b></td>
                                <% if (district_id.equals("0")) {%>
                                <td class="formhdbg" align="center" style="width: 15%"><b>District</b></td>
                                <% } else if (mandal_id.equals("0")) {%>
                                <td class="formhdbg" align="center" style="width: 15%"><b>Mandal</b></td>
                                <% } else if (village_id.equals("0")) {%>
                                <td class="formhdbg" align="center" style="width: 15%"><b>Village</b></td>
                                <% } else if (!district_id.equals("0")) {%>
                                <td class="formhdbg" align="center" style="width: 15%"><b>Habitation</b></td>
                                <% }%>
                                <td align="center" class="formhdbg" style="width: 10%"><b>Married</b></td>
                                <td align="center" class="formhdbg" style="width: 10%"><b>UnMarried</b></td>
                                <td align="center" class="formhdbg" style="width: 10%"><b>Unknown</b></td>
                            </tr>

                            <bean:define id="marriedTotal" value="0"/>
                            <bean:define id="unmarriedTotal" value="0"/>
                            <bean:define id="unKnown" value="0"/>

                            <logic:iterate name="parentsMarriage" id="row" scope="session">
                                <bean:define id="marriedTotal" value="${marriedTotal + row.no}" />
                                <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.yes}" />
                                <bean:define id="unKnown" value="${unKnown + row.none}" />


                                <tr>
                                    <td class="formaltbg" align="center" >
                                        <%=i++%>
                                    </td>
                                    <td class="formaltbg">
                                        ${row.name}
                                    </td>
                                    <td class="formaltbg" align="center">
                                       ${row.no}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.yes}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.none}
                                    </td>

                                </tr>

                            </logic:iterate>

                            <tr>
                                <td class="formhdbg" colspan="2" align="center"><b>Total</b></td>
                                <td class="formhdbg" align="center"><b>${marriedTotal}</b></td>
                                <td class="formhdbg" align="center"><b>${unmarriedTotal}</b></td>
                                <td class="formhdbg" align="center"><b>${unKnown}</b></td>


                            </tr>

                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>



    </body>
</html>