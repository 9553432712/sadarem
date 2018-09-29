<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<% String fromdate=(String)request.getAttribute("From_Date"); %>
<% String todate=(String)request.getAttribute("To_Date"); %>

<html:html>
    <head>
    
     
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
          
          
    </head>
    <body> <logic:present name="dailyreportlist" scope="request">  
     <table  border="0" align="center" cellspacing="1" cellpadding="5"
         class="tbl_bg2"  width="50%">
            <tr class="tbl_bg2_content_hdr" align="center"  width="100%"> 
            <td><font color="#c71585">Disability Percentage Count  Report</font>
            </td></tr>  
            <tr class="tbl_bg2_content" >
            <td>
            <table  border="0" align="center" cellspacing="1" cellpadding="5"
              class="tbl_bg2" width="70%">
               <tr class="tbl_bg2_content_hdr_new" >
               <td colspan="3"><b>S.NO</b></td>
               <td nowrap><b>No Of Persons</b></td>
               <td nowrap><b>Eligible Persons</b></td>
               <td nowrap><b>Rejected Persons</b></td>
               <td nowrap><b> < 40 Percent</b></td>
               <td nowrap><b>41 To 60 Percent</b></td>
               <td nowrap><b>61 To 80 Percent</b></td>
               <td nowrap><b>> 81 Percent</b></td>
                       
             
              </tr>
             <% int i=0;%>  
            <logic:iterate id="modify" name="dailyreportlist" scope="request">
              
                   <tr class="tbl_bg2_content_hdr_new">
                       
                  
           <td colspan="3"><%=++i%>
           <td align="center"> <bean:write name="modify" property="no_of_persons"/></td>
          <td align="center"><bean:write name="modify" property="eligible_persons"/></td>
           <td align="center"><bean:write name="modify" property="rejected_persons"/></td>
           <td align="center"><bean:write name="modify" property="lessthan_fourty"/></td>
           <td align="center"><bean:write name="modify" property="fourty_to_sixty"/></td>
           <td align="center"><bean:write name="modify" property="sixtyone_to_eighty"/></td>
           <td align="center"><bean:write name="modify" property="above_eighty"/></td>
       </tr>

</logic:iterate>

           </table>
        </table>

        <table align="center">
       <%    ArrayList ar2 = new ArrayList();
           ar2 = (ArrayList)request.getAttribute("dailyreportlist");
           String fromdate1=(String)request.getAttribute("From_Date");
           String todate1=(String)request.getAttribute("To_Date");
           application.setAttribute("FromDate",fromdate1);
           application.setAttribute("ToDate",todate1);
           application.setAttribute("arraylist1",ar2); %>
            <a href="disabilityPercentagewiseexcel.xls">
               Export To Excel <img src="DisabilityUITG/images/excel.jpg"
 height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</table>
                
          
              
    </logic:present>
    </body>
    <p>&nbsp;</p>
  
</html:html>