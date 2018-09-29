<%-- 
    Document   : OtherNeedsHIPrint
    Created on : Feb 17, 2011, 10:02:55 AM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%          String fromdate = (String) request.getParameter("fdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");
            String habitation_id = (String) request.getParameter("hID");
            String districtName = (String) request.getParameter("dName");
            String mandalName = (String) request.getParameter("mName");
            String villageName = (String) request.getParameter("vName");
            String habitationName = (String) request.getParameter("hName");
            ArrayList otherNeedsHIList = new ArrayList();
            otherNeedsHIList = (ArrayList) session.getAttribute("otherNeedsHIList");
            int otherNeedsHITotal = 0;
            Iterator iter = otherNeedsHIList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                otherNeedsHITotal = otherNeedsHITotal + totalDTO.getOtherNeedsHI();
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Other Needs HI</title>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="otherNeedsHIList" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="40%">
                <tr>
                    <td align="center" colspan="3"><img src="DisabilityUITG/images/Banner.jpg"/></td>
                </tr>
                <tr>
                    <th align="center" colspan="3"><b>Other Needs Report HI As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></th>
                </tr>
                <tr>
                    <th align="center" colspan="3">
                        <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>, Habitation: <font color="blue">ALL</font>,
                        <% } else if (!village_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        Village: <font color="blue"><%=villageName%></font>,
                        <% } else if (!mandal_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        <% } else if (!district_id.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>,
                        <% }%>
                        &nbsp; Type of Disability: <font color="blue">Hearing</font>
                    </th>
                </tr>
                <tr>
                    <th align="center"><b>S.No</b></th>
                    <% if (!village_id.equals("null") && !village_id.equals("0")) {%>
                    <th align="center"><b>Habitation</b></th>
                    <% } else if (!village_id.equals("null")) {%>
                    <th align="center"><b>Village</b></th>
                    <% } else if (!mandal_id.equals("null")) {%>
                    <th align="center"><b>Mandal</b></th>
                    <% } else if (!district_id.equals("null")) {%>
                    <th align="center"><b>District</b></th>
                    <% }%>
                    <th align="center"><b>Other Needs</b></th>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="otherNeedsHIList" scope="session">
                    <tr>
                        <td  align="center"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                            <td align="center"><bean:write name="modify" property="otherNeedsHI"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=otherNeedsHITotal %></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


