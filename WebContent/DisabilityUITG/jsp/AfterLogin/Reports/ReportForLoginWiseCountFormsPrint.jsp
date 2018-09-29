<%-- 
    Document   : ReportForLoginWiseCountFormsPrint
    Created on : May 13, 2010, 12:26:46 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>

<html:html>
      <body  onLoad="window.print()">
    <head>
    </head>
    <body>
<table  border="0" align="center" cellspacing="1" cellpadding="5"  width="50%">
    <tr align="center"  width="100%">
    <td><font color="#c71585">Login Wise Count Report From <%= fromdate %>
    To <%= todate %></font> </td></tr>
    <tr>
    <td>
        <table  border="0" align="center" cellspacing="1" cellpadding="5" width="70%" class="inputform">
        <tr>
                    <th><b>S.No</b></th>
                    <th nowrap><b>LoginID</b></th>
                    <th nowrap><b>PartA Entered</b></th>
                    <th nowrap><b>PartB Entered</b></th>
                    <th nowrap><b>Total Records</b></th>

                </tr>
                <% int i=0;%>
              
<logic:iterate id="disabilityreport" name="arraylist1">
    <tr>
        <td style="text-align: center"><%=++i%>
        <td align="left"><bean:write name="disabilityreport" property="loginid"/></td>
        <td style="text-align: center"><bean:write name="disabilityreport" property="partA"/></td>
        <td style="text-align: center"><bean:write name="disabilityreport" property="partB"/></td>
        <td style="text-align: center"><bean:write name="disabilityreport" property="total"/></td>

    </tr>
</logic:iterate>
            </table>
        </table>
    </body>
    <p>&nbsp;</p>

</html:html>

