<%-- 
    Document   : ReportForLoginWiseCountFormsExcel
    Created on : May 13, 2010, 12:13:52 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>

<html:html>
    <head>
    </head>
    <body>
<table  border="0" align="center" cellspacing="1" cellpadding="5"  width="50%">
    <tr align="center"  width="100%">
    <td><font color="#c71585">Login Wise Count Report From <%= fromdate %>
    To <%= todate %></font> </td></tr>
    <tr>
    <td>
    <table  border="1" align="center" cellspacing="1" cellpadding="5" width="70%">
        <tr>
                    <td><b>S.No</b></td>
                    <td nowrap><b>LoginID</b></td>
                    <td nowrap><b>PartA Entered</b></td>
                    <td nowrap><b>PartB Entered</b></td>
                    <td nowrap><b>Total Records</b></td>

                </tr>
                <% int i=0;%>
<logic:iterate id="disabilityreport" name="arraylist1">
    <tr>
        <td align="left"><%=++i%>
        <td align="left"><bean:write name="disabilityreport" property="loginid"/></td>
        <td align="middle"><bean:write name="disabilityreport" property="partA"/></td>
        <td align="middle"><bean:write name="disabilityreport" property="partB"/></td>
        <td align="middle"><bean:write name="disabilityreport" property="total"/></td>

    </tr>
</logic:iterate>
            </table>
        </table>
    </body>
    <p>&nbsp;</p>

</html:html>
