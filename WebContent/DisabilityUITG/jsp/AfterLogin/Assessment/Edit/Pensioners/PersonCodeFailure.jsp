<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <script language="javascript" >
        function goBack()
        {
        document.forms[0].action="PersonDetailsUpdateForwardAction.do";
        document.forms[0].submit();
        }
  </script>
<% String pensionNumberFlow = request.getParameter("pensionNumberFlow");%>
<html>
    <head><br><br><br><br>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
<form action="" >
  <center>  <h2><font color="red" >Please Enter valid SADAREM ID</font></h2>
      <input type="hidden" name="pensionNumberFlow" value=<%=pensionNumberFlow%> />
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
