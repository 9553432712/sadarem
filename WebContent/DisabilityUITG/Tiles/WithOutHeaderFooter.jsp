<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<% 
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html> 
<head >
    <LINK REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css"> 
</head> 
 <body>
  <tiles:insert attribute="body" ignore="true"/>
</body>
</html>