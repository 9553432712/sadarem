<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<%@page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<% String s=(String)request.getAttribute("pwdreportsheading"); %>
<% String fromdate=(String)request.getAttribute("FromDateToPWDTerritory"); %>
<% String todate=(String)request.getAttribute("ToDateToPWDTerritory"); %>

<html:html>
    <head>
    
     
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                
    </head>
    <body>
        <div style="border:1px solid #000000;
    background-color: #FFFFFF;width:1000px; height:100%; overflow-x:scroll;
     overflow-y:scroll;">
      <logic:present name="pwdreports" scope="request">
          
  
     
            <table  border="1" align="center" cellspacing="1"  cellpadding="5"
        class="innerTable"  width="100%">
                <tr> 
                <td class="heading" align="center">PWD Reports</td></tr>
                <tr>
                <td>
                <table  border="1" align="center" cellspacing="1"
         cellpadding="5" class="innerTable1" width="70%">
                <tr>
    <td colspan="1">&nbsp;</td>
    <td colspan="1">&nbsp;</td>
   
    <td colspan="5" align="center" class="labelBlue">Physical Impairment
   </td>
    <td colspan="5"align="center" class="labelBlue">Visual Impairment
   </td>
    <td colspan="5"align="center" class="labelBlue">Hearing Impairment
    </td>
    <td colspan="5"align="center" class="labelBlue">Mental Retardation
    </td>
    <td colspan="5"align="center" class="labelBlue">Mental Illness
    </td>
    <td colspan="5"align="center" class="labelBlue">Multiple Disability
    </td>
    <td colspan="5">&nbsp;</td>
                       
                </tr>
                <tr>
                    
                <td class="label">S.NO</td>
                <td class="labelBlue"><%= s%></td>
              
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="label">Below 40</td>
                <td nowrap class="label">41 To 60</td>
                <td nowrap class="label">61 To 80</td>
                <td nowrap class="label">Above 81</td>
                <td nowrap class="labelBlue">Sub Total</td>
                <td nowrap class="labelBlue">Eligible</td>
                <td nowrap class="labelBlue">AssessedRejected</td>
                <td nowrap class="labelblue">Rejected</td>
                </tr>                                   
                <% int i=0;%>  
                   
              
              
              
              
                <logic:iterate id="modify" name="pwdreports" scope="request">
                
                    <tr>
                       
                  
                        <td align="center" class="label"><%=++i%>
                       
<logic:present name="pwdreports" scope="request"> <td class="label"> <b>
<bean:write name="modify" property="district_name"/></logic:present>
<logic:present name="pwdreports" scope="request">
 <bean:write name="modify" property="village_name"/></logic:present>
<logic:present name="pwdreports" scope="request"> 
<bean:write name="modify" property="mandal_name"/></logic:present>  
<logic:present name="pwdreports" scope="request">
 <bean:write name="modify" property="habitation_name"/></b></td
></logic:present>

<td align="center" class="label">
<bean:write name="modify" property="belowfourtyforphysical"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyforphysical"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtytoeightyforphysical"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneforphysical"/></td>
<td align="center" class="labelBlue"><bean:write name="modify"
 property="totalforphysical"/></td>
<td align="center" class="label">
<bean:write name="modify" property="belowfourtyforvisual"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyforvisual"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtyonetoeightyforvisual"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneforvisual"/></td>
<td align="center" class="labelBlue">
<bean:write name="modify" property="totalforvisual"/></td>
<td align="center" class="label">
<bean:write name="modify" property="belowfourtyforhearing"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyforhearing"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtyonetoeightyforhearing"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneforhearing"/></td>
<td align="center" class="labelBlue"><bean:write name="modify" property="totalforhearing"/></td>
<td align="center" class="label">
<bean:write name="modify" property="belowfourtyformentalretardation"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyformentalretardation"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtyonetoeightyformentalretardation"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneformentalretardation"/></td>
<td align="center" class="labelBlue">
<bean:write name="modify" property="totalformentalretardation"/>
</td>
<td align="center" class="label">
<bean:write name="modify" property="belowfourtyformentalillness"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyformentalillness"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtyonetoeightyformentalillness"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneformentalillness"/></td>
<td align="center" class="labelblue">
<bean:write name="modify" property="totalformentalillness"/></td>
<td align="center" class="label">
<bean:write name="modify" property="belowfourtyformultipledisability"/></td>
<td align="center" class="label">
<bean:write name="modify" property="fourtytosixtyformultipledisability"/></td>
<td align="center" class="label">
<bean:write name="modify" property="sixtyonetoeightyformultipledisability"/></td>
<td align="center" class="label">
<bean:write name="modify" property="aboveeightyoneformultipledisability"/></td>
<td align="center" class="labelBlue">
<bean:write name="modify" property="totalformultipledisability"/>
</td>
<td align="center" class="label">
<bean:write name="modify" property="totaldisability"/>
</td>
<td align="center" class="label">
<bean:write name="modify" property="assessedandrejectedtotal"/>
</td>
<td align="center" class="label">
<bean:write name="modify" property="rejectedpersonscount"/></td>
                   </tr>
                </logic:iterate>
                  
                        
<%    ArrayList arl = new ArrayList();

    arl =(ArrayList)request.getAttribute("pwdreports");
    String arl2 =(String)request.getAttribute("pwdreportsheading");
    String fromdate1=(String)request.getAttribute("FromDateToPWDTerritory"); 
    String todate1=(String)request.getAttribute("ToDateToPWDTerritory"); 
    application.setAttribute("pwdexcel",arl);
      application.setAttribute("FromDate",fromdate1);
       application.setAttribute("ToDate",todate1);
    application.setAttribute("pwdreportsheadings",arl2); %>

            
          <tr><td colspan="35" align="center">
<a href="pwdreportexcel.xls" target="_blank"><img src="DisabilityUITG/images/excel.jpg"
 height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table>
                
            </table>
           
        
      </logic:present>
             </div>
    </body>
    <p>&nbsp;</p>
  
</html:html>