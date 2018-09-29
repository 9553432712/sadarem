<%-- 
    Document   : PrintReportDetails
    Created on : Mar 12, 2013, 4:22:43 PM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<% int i = 1;
            String did = request.getAttribute("distId").toString();
%>
<html:html>
    

    <head>
        <title>SADAREM</title>
    </head>

    <center>
        <body >
            <html:form action="/printDetailsResult" >
                <logic:present name="nodatafound">
                    <p id="errmsg"><b><bean:write name="nodatafound"/></b></p>
                </logic:present>
                <logic:notEmpty name="certificateDetailsList">

                    <%--<div style="
                         background-color: #FFFFFF;width:1000px; height:100%; 
                         ">--%>
                        <table  border="0"  cellspacing="1" cellpadding="4" class="inputform" width="90%">
                            <tr>
                                <th colspan="5">Print Report Details</h>
                            </tr>
                          
                            <tr>

                                <th>
                                    S No
                                </th>
                                <th>
                                    SADAREM ID
                                </th>
                                <th>
                                    Certificate
                                </th>
                                <th>
                                    ID Card
                                </th>
                                <th>
                                    Railway Certificate
                                </th>
                            </tr>

                            <logic:iterate name="certificateDetailsList" id="rows">
                                <tr>
                                    <td>
                                        <%=i++%>
                                    </td>
                                    <td>
                                        ${rows.sid}
                                    </td>
                                    <td>
                                        ${rows.ct}
                                    </td>
                                    <td>
                                        ${rows.id}
                                    </td>
                                    <td>
                                        ${rows.rc}
                                    </td>

                                </tr>
                            </logic:iterate>

                        </table>

                   <%-- </div>--%>
                </logic:notEmpty>
            </html:form>
        </body>
    </center>
</html:html>
