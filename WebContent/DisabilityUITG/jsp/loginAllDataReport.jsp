<%--
    Document   : loginAllDataReport
    Created on : Jan 18, 2013, 9:52:46 AM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type="text/javascript">
            function getAllDataReport(){
                document.forms[0].mode.value="getAllDataReport";
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>
        <html:form action="/districtReport">
            <html:hidden property="mode"/>
           <%-- <center>
            <input type="button" value="getReport" onclick="getAllDataReport();"/>
            </center>--%>
            <table align="center">
                <tr>
                    <td>
                        <a href="districtReport.xls?mode=exportReport" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                    </td>
                </tr>
            </table>

        </html:form>

    </body>
</html>