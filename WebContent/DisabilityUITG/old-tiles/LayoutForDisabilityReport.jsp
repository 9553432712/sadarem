<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

    <head >

    </head>
    <body>

        <table border="2" cellpadding="0" cellspacing="0"  align="center" width="100%" height="100%" style="border-collapse:collapse;" bordercolor="#660000">
            <tr>
                <td width="100%" colspan="2" valign="top" height="15%"><tiles:insert attribute="header" ignore="true"/></td>
            </tr>
            <tr>
                <td width="100%" colspan="2" valign="middle" height="4%"><tiles:insert attribute="menu" ignore="true"/></td>
            </tr>
            <tr>
                <td height="1%" width="80%" colspan="2" valign="top"><tiles:insert attribute="date" ignore="true"/></td>
            </tr>
            <tr>
                <td height="100%" width="100%" colspan="2" valign="top"><tiles:insert attribute="body" ignore="true"/></td>
            </tr>
            <tr>
                <td width="100%" colspan="2" valign="top" height="7%"><tiles:insert attribute="footer" ignore="true" /></td>
            </tr>
        </table>
    </body>

</html>