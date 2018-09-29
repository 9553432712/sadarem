<%-- 
    Document   : NotComeToSadaremCampReportExcel
    Created on : Jan 31, 2011, 2:35:55 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            String districtName = (String) request.getParameter("districtName");
            String phaseName = (String) request.getParameter("phase");
            String district_id = (String) request.getParameter("district_id");
            ArrayList notCometoSadaremList = new ArrayList();
            if (session.getAttribute("notComeToCampList1") != null) {
                notCometoSadaremList = (ArrayList) session.getAttribute("notComeToCampList1");
            }

            int totalNotCome = 0, totalonlyPartA = 0;
            Iterator iter = notCometoSadaremList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                totalNotCome = totalNotCome + totalDTO.getNotComrtoCamp();
                totalonlyPartA = totalonlyPartA + totalDTO.getOnlyPartA();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>R2.1 : SADAREM Camp Not Attended PWD's</title>
    </head>
    <body>
        <logic:present name="notComeToCampList1" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr><td  align="center" colspan="4">District: <%=districtName%>, Phase: <%=phaseName%>, Who are Not Come to SADAREM Camp Report</td></tr>
                <tr>
                    <td align="center" >S.No</td>
                    <td  align="center" >Mandal Name</td>
                   <%-- <td  align="center" >Not Come to SADAREM Camp</td>--%>
                    <%int j = 0;
                                if (phaseName.equalsIgnoreCase("Reassment") || phaseName.equalsIgnoreCase("Renual")) {
                                    j = 0;
                                } else {
                                    j = 1;
                                }
                                if (j == 1) {%>
                    <td  align="center" >Only Part-A(Doctor Not Assessed)</td>
                    <%}%>
                </tr>
                <% int i = 0;%>
                <logic:iterate id="modify" name="notComeToCampList1" scope="session">
                    <tr>
                        <td  align="center"   ><%=++i%></td>
                        <td   align="center"><bean:write name="modify" property="mandalName"/></td>
                      <%-- <%if (j == 1) {%>
                        <td class="formbg" align="center"><bean:write name="modify" property="notComrtoCamp"/></td>
                        <%}%>--%>
                        <td class="formbg" align="center"><bean:write name="modify" property="onlyPartA"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td  colspan="2" align="center">Total</td>
                <%--   <%if (j == 1) {%>
                    <td  align="center"><%=totalNotCome%></td>
                    <%}%>--%>
                    <td  align="center"><%=totalonlyPartA%></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


