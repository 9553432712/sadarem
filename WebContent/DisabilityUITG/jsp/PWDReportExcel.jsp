<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% String s=(String)application.getAttribute("pwdreportsheadings"); %>
<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>
 
<html:html>
    <head>
     <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     </head>
    <body>
        <table  border="1" align="center" cellspacing="0" cellpadding="0"   width="100%">
                <tr  align="center"  width="70%"> 
                <td><b><font color="blue"> PWD Reports From <%= fromdate %> To <%= todate %></font></b></td></tr>  
                <tr>
                <td>
                <table  border="1" cellspacing="0" cellpadding="0"  width="70%">
                    <tr>
                        <td colspan="1">&nbsp;</td>
                         <td colspan="1">&nbsp;</td>
                           <td colspan="1">&nbsp;</td>
    <td colspan="3" align="center"><b><font color="#c71585">PI</font></b></td>
    <td colspan="4"align="center"><b><font color="#c71585">VI</font></b></td>
    <td colspan="4"align="center"><b><font color="#c71585">HI</font></b></td>
    <td colspan="4"align="center"><b><font color="#c71585">MR</font></font></b></td>
    <td colspan="4"align="center"><b><font color="#c71585">MI</font></b></td>
    <td colspan="4"align="center"><b><font color="#c71585">MD</font></b></td>
    <td colspan="1">&nbsp;</td>
                        
                    </tr>
                    <tr>
                     <td align="center"><b>S<br>NO</b></td>
                         <td align="center"><b><font color="blue"><%= s%></font></b></td>
                        
                       <td align="center"><b><br><p><<br>-<br>40</b></p></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                    
                        <td align="center"><b><<br>-<br>40</b></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                      
                        <td align="center"><b><<br>-<br>40</b></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                    
                        <td align="center"><b><<br>-<br>40</b></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                        
                        <td align="center"><b><<br>-<br>40</b></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                       
                        <td align="center"><b><<br>-<br>40</b></td>
                        <td align="center"><b>41<br>-<br>60</b></td>
                        <td align="center"><b>61<br>-<br>80</b></td>
                        <td align="center"><b>><br>-<br>81</b></td>
                  
                       <td align="center"><b><font color="blue">EP</font></b></td>
                        <td align="center"><b><font color="blue">AR</font></b></td>
                         <td align="center"><b><font color="blue">RP</font></b></td>
                    </tr>                                   
                    <% int i=0;%>  
                     <logic:iterate id="reports" name="pwdexcel">
                 <tr>
                        <td align="center"><%=++i%></td>
                       
                            
<td align="center">  <logic:present name="pwdexcel">  <font color="blue"><b>
<bean:write name="reports" property="district_name"/></logic:present>
 <logic:present name="pwdexcel"> <bean:write name="reports" property="village_name"/>
 </logic:present>
   <logic:present name="pwdexcel"> <bean:write name="reports" property="mandal_name"/>
   </logic:present>  
<logic:present name="pwdexcel"> <bean:write name="reports" property="habitation_name"/>
</font></b></td></logic:present> 

<td align="center"><bean:write name="reports" property="belowfourtyforphysical"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyforphysical"/></td>
<td align="center"><bean:write name="reports" property="sixtytoeightyforphysical"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneforphysical"/></td>

<td align="center"><bean:write name="reports" property="belowfourtyforvisual"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyforvisual"/></td>
<td align="center"><bean:write name="reports" property="sixtyonetoeightyforvisual"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneforvisual"/></td>

<td align="center"><bean:write name="reports" property="belowfourtyforhearing"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyforhearing"/></td>
<td align="center"><bean:write name="reports" property="sixtyonetoeightyforhearing"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneforhearing"/></td>

<td align="center"><bean:write name="reports" property="belowfourtyformentalretardation"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyformentalretardation"/></td>
<td align="center"><bean:write name="reports" property="sixtyonetoeightyformentalretardation"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneformentalretardation"/></td>

<td align="center"><bean:write name="reports" property="belowfourtyformentalillness"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyformentalillness"/></td>
<td align="center"><bean:write name="reports" property="sixtyonetoeightyformentalillness"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneformentalillness"/></td>

<td align="center"><bean:write name="reports" property="belowfourtyformultipledisability"/></td>
<td align="center"><bean:write name="reports" property="fourtytosixtyformultipledisability"/></td>
<td align="center"><bean:write name="reports" property="sixtyonetoeightyformultipledisability"/></td>
<td align="center"><bean:write name="reports" property="aboveeightyoneformultipledisability"/></td>

<td align="center"><font color="blue"><b><bean:write name="reports" property="totaldisability"/></b></td>
 <td align="center">
 <font color="blue"><b><bean:write name="reports" property="assessedandrejectedtotal"/></b></td> 
  <td align="center"><b><font color="blue">
   <bean:write name="reports" property="rejectedpersonscount"/></b></font></td>
                        </tr>
                    </logic:iterate>
                  </table>
            </table>
          </body>
   
  
</html:html>