<%--
    Document   : EducationWiseReportExcel
    Created on : Mar 20, 2011, 4:06:36 PM
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
            //  String district_id = (String) request.getParameter("districtId");
            //  String mandal_id = (String) request.getParameter("mandalId");
            //  String village_id = (String) request.getParameter("villageId");
            String fromDate = (String) request.getParameter("fdate");
            String toDate = (String) request.getParameter("todate");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");

            String district_id = (String) session.getAttribute("district_id");
            String mandal_id = (String) session.getAttribute("mandal_id");
            String village_id = (String) session.getAttribute("village_id");


            int i = 1;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <html:form action="getphysicalRequirementReportExcel">
        <body>

            <logic:present name="physicalRequiremenList">
                 <p>Physical Requirement Report</p>
                <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="50%">

                    <tr>
                        <td align="center">
                            SNo
                        </td>
                        <td><bean:write name="ExcelHeader"/></td>
                        <td align="center">
                            Physical Requirements Details
                        </td>
                    </tr>
                    <logic:iterate name="physicalRequiremenList" id="row">
                        <tr>
                            <td align="center">
                                <%=i++%>.
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td align="center">
                                ${row.personCount}
                            </td>
                        </tr>

                    </logic:iterate>

                </table><br>
            </logic:present>
            <%session.removeAttribute("district_id");%>
            <%session.removeAttribute("mandal_id");%>
            <%session.removeAttribute("village_id");%>

        </body>
    </html:form>
</html>


