<%@ page import="java.util.*" %>

<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META HTTP-EQUIV="imagetoolbar" CONTENT="no">
        <title>Health Management</title>
        <script LANGUAGE="JavaScript" SRC="scripts/lw_layers.js"></script>
        <script LANGUAGE="JavaScript" SRC="scripts/menu.js"></script>
 <style type="text/css">
<!--
.style1 {
	font-size: 34px;
	font-style: italic;
	color: #000080;
}
-->
</style>
<script>
    window.history.forward(1);
</script>
</head>
<body>
<table  border=0 cellspacing=0  width=700 align=center>
    
    <tr>
<table width="966" height="122">
    <tr>
    <td width="142" height="116"><div align="center"><img src="HelpDeskUI/images/Header/byr.jpg" width="107" height="114" /></div></td>
    <td width="639"><h1 align="center" class="style1">Health Management</h1></td>    
    <td width="169"><div align="center"><img src="HelpDeskUI/images/Header/gram.jpg" width="137" height="86" /></div></td>
    </tr>
</table><td id="noprint">
<%
Vector services=(Vector) session.getAttribute("servicelist");
if (services != null)
{
out.println("<script LANGUAGE=\"JavaScript\">");
for (int i=0;i<services.size();i++)
												{
String servicedesc[]=(String [])services.elementAt(i);
String serviceid=servicedesc[0];
String parentid=servicedesc[1];
 String targeturl=servicedesc[2];
 String menuitemname=servicedesc[3];

 out.println("AddMenuItem ("+serviceid+", "+parentid+", \""+targeturl+"\", \""+menuitemname+"\", \"\");");
												}
}
else
out.println("could not get services");
out.println("</script>");
%>
<script LANGUAGE="JavaScript">
     DrawMenu();
</script></td></tr>
</table>
</body>
</html>	