<%-- 
    Document   : DeletePersonCodeFailure
    Created on : Apr 24, 2010, 5:10:52 PM
    Author     : srinivas_p
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script language="javascript" >
        function goBack()
        {
        document.forms[0].action="DeletePersonDetailsUpdateForwardAction.do";
        document.forms[0].submit();
        }
  </script>
<html>
    <head><br><br><br><br>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
<form action="" >
  <center>  <h2><font color="red" >Please Enter valid SADAREM ID</font></h2>
      <input type="button" name="Back" value="Back" onclick="goBack()" class="button" />
    </center>
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>
    </form>
    </body>
</html>