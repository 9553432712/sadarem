<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>  


<html:html>
    <head>
    </head>
    <body>
<table  border="0" align="center" cellspacing="1" cellpadding="5"  width="50%">
    <tr align="center"  width="100%"> 
    <td><font color="#c71585">Disability Percentage Report From <%= fromdate %> 
    To <%= todate %></font> </td></tr>  
    <tr>
    <td>
    <table  border="1" align="center" cellspacing="1" cellpadding="5" width="70%">
        <tr>
                    <td><b>S.NO</b></td>
                    <td nowrap><b>No Of Persons</b></td>
                    <td nowrap><b>Eligible Persons</b></td>
                    <td nowrap><b>Rejected Persons</b></td>
                    <td nowrap><b> < 40 Percent</b></td>
                    <td nowrap><b>40 To 60 Percent</b></td>
                    <td nowrap><b>61 To 80 Percent</b></td>
                    <td nowrap><b>> 81 Percent</b></td>
                </tr>
                <% int i=0;%>  
<logic:iterate id="disabilityreport" name="arraylist1">
    <tr>
        <td align="center"><%=++i%>
        <td align="center"> <bean:write name="disabilityreport" property="no_of_persons"/></td>
        <td align="center"><bean:write name="disabilityreport" property="eligible_persons"/></td>
        <td align="center"><bean:write name="disabilityreport" property="rejected_persons"/></td>
        <td align="center"><bean:write name="disabilityreport" property="lessthan_fourty"/></td>
        <td align="center"><bean:write name="disabilityreport" property="fourty_to_sixty"/></td>
        <td align="center"><bean:write name="disabilityreport" property="sixtyone_to_eighty"/></td>
        <td align="center"><bean:write name="disabilityreport" property="above_eighty"/></td>
    </tr>
</logic:iterate>
            </table>
        </table>
    </body>
    <p>&nbsp;</p>
  
</html:html>