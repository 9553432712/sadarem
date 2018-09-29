<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
   
</head>
<body> 

<table border="1" cellpadding="5" cellspacing="1" width="100%"  height="100%"  bordercolor="#660000" >
<tr height="3%">
<td width="100%" colspan="2" valign="top"><tiles:insert attribute="header" ignore="true"/></td>
</tr>
<tr height="1%">
<td width="100%" valign="top" align="center" colspan="2"><tiles:insert attribute="menu" ignore="true"/></td>
</tr>
<tr valign="middle" height="5%">
<td  colspan="2" align="center" height="5%" ><tiles:insert attribute="date" ignore="true"/></td>
</tr>



<tr valign="middle" height="90%">


<td ><tiles:insert attribute="body" ignore="true"/></td>
</tr>
<tr height="1%">
<td width="80%" colspan="2" valign="bottom"><tiles:insert attribute="footer" ignore="true" /></td>
</tr>
</table>
</body>

</html>