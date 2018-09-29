<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>
    <script type="text/javascript">
    function goBack()
        {
            document.forms[0].action="Appeal.do";
            document.forms[0].submit();
        }</script>
<% String msg=(String)request.getAttribute("msg"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<br><br><br><br>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <br><br><br><br>
<!--Existing PensionNumber Appleat Status
    <table  align="center" cellspacing="0" cellpadding="5" class="innerTable" width="50%">
                <tr><td colspan="2" align="center" class="heading"> </td></tr>
            </table>-->

    
    <body><html:form action="Appeal.do"  method="post">
        
                     
                <table width="80%" align="center"><tr align="center"><td><font color="red"><B>
            <% if(msg!=null){%>
            <%=msg %></B></font></td></tr></table>
<%}%>
       

        <br><br><br><br>
<center>   <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></center>

</html:form>
    </body>
    
</html:html>
