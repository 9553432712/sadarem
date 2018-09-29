<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
    Document   : Letter
    Created on : Feb 29, 2012, 11:39:52 AM
    Author     : 525485
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page language="java" isErrorPage="true" %>

<%
            String path = "/sadarem";
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  ;
           
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type = "text/javascript" >

            var file, n;

            file = window.location.pathname;
            n = file.lastIndexOf('/');
            if (n >= 0) {
                file = file.substring(n + 1);
            }
            
            function preventBack(){
                window.history.forward();
            }

            setTimeout("preventBack()", 0);

            window.onunload=function(){null};

        </script>
    </head>
    <body style="padding: 0px;margin: 0px;">

        <table align="center" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>
                    <img src="./DisabilityUITG/images/UnAccess.png">
                </td>
            </tr>
            <tr>
                <td align="center">
                    <a href="<%=basePath%>"> <font color="Blue" size="3"><b>Go To Home</b></font></a>
                </td>
            </tr>
        </table>
    </body>
</html>
