<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
   
</head>
<body> 

<table border="2" cellpadding="5" cellspacing="1"    width="100%"   height="100%" style="border-collapse:collapse;" bordercolor="#660000" >
<tr height="15%">
<td width="100%" colspan="2" valign="top"><tiles:insert attribute="header" ignore="true"/></td>
</tr>
<tr height="5%">
<td width="100%" valign="top" align="center" colspan="2"><tiles:insert attribute="menu" ignore="true"/></td>
</tr>
<tr valign="top" height="10%">
<td  colspan="2" align="center" height="5%" ><tiles:insert attribute="date" ignore="true"/></td>
</tr>



<tr valign="top" height="60%" >
<div style="overflow:scroll">
<td width="20%" ><tiles:insert attribute="left" ignore="true"/></td></div>
<div style="overflow:scroll"><td width="80%"><tiles:insert attribute="body" ignore="true"/></td></div>
</tr>
<tr  valign="top" height="10%">
<td width="10%" colspan="2" valign="center"><tiles:insert attribute="footer" ignore="true" /></td>
</tr>
</body>

</html>