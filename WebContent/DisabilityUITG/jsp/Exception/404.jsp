
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page isErrorPage="true"%>
<html>
    <head>

        <%
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type = "text/javascript" >

            var file, n;

            file = window.location.pathname;
            n = file.lastIndexOf('/');
            if (n >= 0) {
                file = file.substring(n + 1);
            }

            if(file=="State" || file=="District" ||file=="AreaCluster" || file=="Mandal" ) {
                window.location.href="<%=basePath%>"+file+".do";
            }
            function preventBack(){
                window.history.forward();
            }

            setTimeout("preventBack()", 0);

            window.onunload=function(){null};

        </script>
    </head>
    <body>
        <center>
            <br/><br/><br/><br/><br/><br/><br/><br/>
            <font color="red" size="5">Sorry,the page you are looking for has not been found.</font><br/>
            <a href="<%=basePath%>"><b><font color="green" size="4">Login again</font></b></a>
        </center>
    </body>
</html>
