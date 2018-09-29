<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="true" %>

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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SADAREM</title>
</head>

<body><br/><br/><br/>

<p id="succmsg" size="10">Rejected Person Data Entered Successfully</p>

<table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="40%">
<tr>
    <td ><center><font size="3">Print the Certificate</font><font size="1" color="red">
                (click on below links) </font></center></td>
</tr>
</table>

<table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="40%">
<tr>
<td align="center" >
      <%String selectFlow="OUTERPROCESS";
      if(request.getAttribute("selectedValue")!=null){
      selectFlow= request.getAttribute("selectedValue").toString();
    }%>
<a href="rejectedCertificate.do?print=certificate&flag=false&selectFlow=<%=selectFlow%>"><b><h3>
            <center>CERTIFICATE</center></h3></b></a>
</td>
</tr>
</table>
</body>
</html>
