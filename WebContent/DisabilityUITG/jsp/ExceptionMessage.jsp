<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
         
    </head>
    <body>
        <br><br><br><br>
        <table  width="85%"  align="center" >
            <tr >
                <%
                            if (request.getAttribute("MSG") != null) {
                %>
                <th align="center"><font style="color: red;">   <%=request.getAttribute("MSG")%></font></th>
                <%
                            }
                %>
                <th align="center"><html:errors/> 
                <th>
            <tr>
        </table>

    </body>
</html:html>
