<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
 <%
    HttpSession sessionForCheck=request.getSession(false);
  
        String  reLoginURL=request.getContextPath()+"/login.do?mode=sessionvalidator";
    if(session.getAttribute("services")==null)
    {
        response.sendRedirect(reLoginURL);
        return;
    }
%>

</head>

 <body class="body">

<table  align="center" class="table_one">

<tr><td width="85%"><tiles:insert attribute="header" ignore="true"/></td></tr>



<tr valign="top"><td width="85%"><tiles:insert attribute="body" ignore="true"/></td></tr>

<tr><td><tiles:insert attribute="footer" ignore="true" /></td></tr>

</table>

</body> 

</html>