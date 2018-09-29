<%-- 
    Document   : PrintCountDetails
    Created on : Mar 13, 2013, 1:53:25 PM
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
            String campid = request.getAttribute("campId").toString();
            String did = request.getAttribute("distId").toString();
%>
<html:html>
    

    <head>
        <title>SADAREM</title>
    </head>

    <center>
        <body >
            <html:form action="/printDetailsResult" >
                <html:hidden property="mode"/>
                <logic:present name="nodata">
                    <p id="errmsg"><b><bean:write name="nodata"/></b></p>
                </logic:present>
                <logic:notEmpty name="certificateCountDetailsList">
                    <%--<div style="
                         background-color: #FFFFFF;width:1000px; height:100%; 
                         ">--%>

                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">
                        <tr>
                            <th colspan="3">Print Report Details</th>
                        </tr>
                        <tr>
                            <th>
                                S No
                            </th>
                            <th>
                                SADAREM ID
                            </th>
                            <th>
                                Total
                            </th>
                        </tr>

                        <logic:iterate name="certificateCountDetailsList" id="rows">
                            <tr>
                                <td align="center">
                                    <%=i++%>
                                </td>
                                <td align="center">
                                    ${rows.sid}
                                </td align="center">
                                <td>

                                    <a href="javascript:void(0);"  onclick ="window.open('printDetailsResult.do?mode=getPrintFullDetails&sID=${rows.sid}&dID=<%=did%>&campID=<%=campid%>')">${rows.count}</a>
                                </td>

                            </tr>
                        </logic:iterate>

                    </table>

                 <%--   </div>--%>
                </logic:notEmpty>
            </html:form>
        </body>
    </center>
</html:html>

