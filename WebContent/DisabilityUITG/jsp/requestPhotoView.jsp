<%-- 
    Document   : requestPhotoView
    Created on : Oct 26, 2012, 6:53:12 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
            String id = (String) request.getAttribute("photoId");

            String personCodeId = (String) request.getAttribute("personCodeId");



%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM Page</title>
    </head>
   
    <body>
        <html:form  action="/requestInformation">

            <table>
                <tr>

                </tr>

            </table>

        </html:form>
    </body>
</html>
