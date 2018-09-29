<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >

</head>
<body class="body" >

<table  align="center"  class="table_one">
<tr>
<td width="85%" colspan="2" valign="top"><tiles:insert attribute="header" ignore="true"/></td>
</tr>
<tr>
<td width="85%" valign="top" align="center" colspan="2"><tiles:insert attribute="menu" ignore="true"/></td>
</tr>
<tr >
    <td  colspan="2" align="center" height="5%" ><tiles:insert attribute="date" ignore="true"/></td>
</tr>
<tr >
<td  colspan="2" align="center" height="380px" valign="top"><br><tiles:insert attribute="body" ignore="true"/></td>
</tr>
<tr >
<td  colspan="2" align="center" ><tiles:insert attribute="footer" ignore="true"/></td>
</tr>

<%--<tr valign="top" height="60%">
<div style="overflow:scroll">
<td width="20%" ><tiles:insert attribute="left" ignore="true"/></td></div>
<div style="overflow:scroll"><td width="80%"><tiles:insert attribute="body" ignore="true"/></td></div>
</tr>
<tr  valign="top" height="10%">
<td width="10%" colspan="2" valign="center"><tiles:insert attribute="footer" ignore="true" /></td>
</tr>--%>
</table>
</body>

</html>