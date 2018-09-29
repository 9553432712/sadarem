<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<% 
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html>
<head >
 <title>:: SADAREM ::</title>
    <meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    
<LINK REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css">
</head>
    <body>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr>
	       <td  valign="top" width="100%">
	       <tiles:insert attribute="header" ignore="true"/></td>
	   </tr>
	
	   <tr>
	       <td height="379px">
	           <!-- Content Starts -->
	           <tiles:insert attribute="body" ignore="true"/>
	           <!-- Content Endts -->
	       </td>
	   </tr>
	   <tr>
	       <td  valign="bottom" height="49px"><tiles:insert attribute="footer" ignore="true" /></td>
	    </tr>
	</table>
</body>
</html>