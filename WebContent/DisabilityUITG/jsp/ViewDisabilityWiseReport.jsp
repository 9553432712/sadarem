<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<% String fromdate=(String)request.getAttribute("From_Date"); %>
<% String todate=(String)request.getAttribute("To_Date"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
     <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
          
     </head>
    <body>
        <logic:present name="dailyreportlist" scope="request">  
<table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" 
 width="50%">
    <tr class="tbl_bg2_content_hdr" align="center"  width="100%"> 
    <td><font color="#c71585">Disability Count  Report</font> </td></tr>  
    <tr class="tbl_bg2_content" >
    <td>
    <table  border="0" align="center" cellspacing="1" cellpadding="5"
 class="tbl_bg2" width="70%">
         <tr class="tbl_bg2_content_hdr_new" >
      <td colspan="3" align="center"><b>S.NO</b></td> <td align="center"><b>Type Of Disability</b></td>
 <td align="center"><b> Count</b></td>
      </tr>
     <% int i=0;%>  
   
     <logic:iterate id="modify" name="dailyreportlist" scope="request">
              
                   <tr class="tbl_bg2_content_hdr_new">
                       
                  
                <td colspan="3" align="center"><%=++i%>
        <td width="200" align="center"> <bean:write name="modify" property="typeofdisability"/></td>
     <td width="200" align="center"><bean:write name="modify" property="disabilitycount"/></td>
</tr>
                </logic:iterate>
    
             </table>
        </table>

             <table align="center">
            <%    ArrayList arl = new ArrayList();
        arl = (ArrayList) request.getAttribute("dailyreportlist");
          String fromdate1=(String)request.getAttribute("From_Date"); 
         String todate1=(String)request.getAttribute("To_Date"); 
        application.setAttribute("FromDate",fromdate1);
        application.setAttribute("ToDate",todate1);
    application.setAttribute("arraylist",arl); %>      
   <a href="disabilitywiseexcel.xls">
               Export To Excel <img src="DisabilityUITG/images/excel.jpg" 
height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</table> </logic:present>
          </body>
    <p>&nbsp;</p>
  
</html:html>