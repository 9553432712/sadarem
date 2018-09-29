<%--
    Document   : AgeWiseReportPrint
    Created on : Jun 23, 2011, 2:40:13 PM
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
            String village_id = (String) request.getParameter("villageId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;

           
%>

<html>
    <head>

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="physicalRequiremenList">
             <p>Physical Requirement Report</p>
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="50%">
  <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="11">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>
                </tr>
                <tr>
                    <th align="center" >
                        SNo
                    </th>
                     <th><bean:write name="ExcelHeader"/></th>
                    <th align="center" >
                        Physical Requirement
                    </th>

                </tr>
                <logic:iterate name="physicalRequiremenList" id="row">

                    <tr>
                        <td style="text-align: center" >
                            <%=i++%>
                        </td>
                        <td  >
                            ${row.name}
                        </td>
                        <td style="text-align: center" >
                            ${row.personCount}
                        </td>

                    </tr>

                </logic:iterate>
            </table>
        </logic:notEmpty>
    </body>
</html>
