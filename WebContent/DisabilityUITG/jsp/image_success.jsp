<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>SADAREM</title>
	 <style type="text/css">
<!--
@import url("styles.css");
@import url("./STYLES/styles.css");
body {
	background-image: url(./images/background.jpg);
}
-->
</style>
    </head>
    <body >
        <% String message = (String) request.getAttribute("msg");%>
            <% if (message != null) {%> <b><font color="red"><center><%=message%> </center></font><% }
        else {%>
        <font color="blue">  <center><h2>Images Successfully Uploaded</h2></center></font> <% }%>
                    </b>
<br><br><br><br><br>
 <center><a href="javascript:window.close()"> Close the Window</a></center>
    
    </body>
</html>
