<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% String fromdate=(String)request.getParameter("FromDate"); %>
<% String todate=(String)request.getParameter("ToDate"); %>

<html:html>
    <head>
    
     
    </head>
   
    <body>
  
     
<table  border="1" align="center" cellspacing="1" cellpadding="5"  width="30%">
    <tr align="center"  width="100%"> 
    <td><b><font color="#c71585">State Wise Disability Categories Report From <%= fromdate %> To
 <%= todate %></font></b></td></tr>  
    <tr><td>
   <table  border="1" align="center" cellspacing="1" cellpadding="1"  width="40%">
        <tr class="tbl_bg2_content_hdr_new">
      <td  align="center"><b>S.NO</b></td> <td align="center"><b>Type Of Disability</b></td>
      <td align="center"><b>Count</b></td>
          <% int i=0;%> 
          <logic:iterate id="disabilityreport" name="disabilitywislist" scope="session">
<tr>
<td  align="center"><%=++i%>
  <td align="center"> <bean:write name="disabilityreport" property="typeofdisability"/></td>
  <td align="center"><bean:write name="disabilityreport" property="disabilitycount"/></td>
    </tr>
</logic:iterate>
      
            </table>
           
        </table>
  
 
    </body>
    
    <p>&nbsp;</p>
  
</html:html>