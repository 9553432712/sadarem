<%-- 
    Document   : AddLoginError
    Created on : Apr 15, 2010, 2:02:32 PM
    Author     : srinivas_p
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
 String message=null;

%>
<html:html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AddLoginError</title>
    </head>
   <script language="javascript">
 
        </script>

    <body><br><br><br><br>
        
       
        <center> <b>UserName</b><% message=(String)request.getAttribute("userName") + (String)request.getAttribute("msg");%>
  <% if(message!=null)  { %> <b><%=message %> <% } %></b></center><br><br>

               
              <!--  <a href="/DisabilityUITG/jsp/AddLogin.jsp" >Back</a>-->
        
</body>
</html:html>
