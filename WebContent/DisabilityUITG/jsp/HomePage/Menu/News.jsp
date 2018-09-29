
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <html:form action="newsTitles">
        <html:hidden property="mode"/>

        <logic:present name="fail">
            <center> <font color="red" size="2">
                    ${fail}
                </font>
            </center>
        </logic:present>
        <logic:notEmpty name="news">
            <br/>
            <table border="0" cellspacing="1" cellpadding="0" class="inputform">
                <tr>
                    <th colspan="5">
                        New & Events
                    </th>
                </tr>
                <tr>
                    <td >S No.</td>
                    <td >Title</td>
                    <td >Description</td>
                    <td >View</td>
                    <td >Download</td>
                </tr>

                <logic:iterate name="news" id="row">
                    <tr >
                        <td><%=i++%>.</td>
                        <td>
                            ${row.titleName}</td>
                        <td>${row.Description}</td>
                        <td>

                            ${row.iframe}</td>
                        <td>

                            ${row.file}
                        </td>

                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
    </html:form>
