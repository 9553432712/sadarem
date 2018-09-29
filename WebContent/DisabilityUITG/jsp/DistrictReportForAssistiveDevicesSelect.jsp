<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="org.bf.disability.form.CommonReportForm" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% 
    String fieldvalue=(String)request.getAttribute("fieldvalue");
%>
<% 
    String secondfieldvalue=(String)request.getAttribute("secondfieldvalue");
%>
<% String fromdate=(String)request.getAttribute("fromDate");
    String todate=(String)request.getAttribute("toDate");
%>
<html:html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
          
    </head>
    <body>
        <form action="gotoexcel.do" method="post">
            <br>
         <% String tableheading=(String)session.getAttribute("TableHeading");%>
            <table  border="1" height="10%" cellspacing="1" cellpadding="5" 
                width="50%" height="" align="center" valign="top">
                <caption><h2><font face="vardana" color="#660000">
                     <%=tableheading%></font> </h2></caption>  
                <tr class="tbl_bg2_content" >
                    <td align="center" bgcolor="white"><font size="2">
                      <font color="#660000"><b><b>S.NO</b></font></td>
                    <td align="center" bgcolor="white"><b> <font size="2">
                   <font color="#660000"><b>District Name</b></font></td>
                    <td align="center" bgcolor="white"><b> <font size="2">
                 <font color="#660000"><b>District Level Count</b></font></td>
                </tr>
            <% int i=0;%>  
              <% CommonReportForm commonreportform=new CommonReportForm();
           request.setAttribute("district_id",commonreportform.getDistrictid());
                %>
                 <logic:iterate id="modify" name="reportlist" scope="request">
                    <tr class="tbl_bg2_content">
                        <td align="center" bgcolor="white" ><%=++i%> </td>
                        <td align="center" bgcolor="white"><a href="getMandalidForAssistive.do?getMandalReport=getMandalReport&districtid=<bean:write name="modify" property="districtid"/>&field=<%=fieldvalue%>&secondfield=<%=secondfieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>"> <bean:write name="modify" property="district"/></a></td>
                        <td align="center" bgcolor="white"><a href="selectdistrictforassistive.do?nexxt=start&back=start&getdistrictPersonalReport=getdistrictPersonalReport&districtid=<bean:write name="modify" property="districtid"/>&FieldValue=<%=fieldvalue%>&SecondFieldValue=<%=secondfieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>&distcount=<bean:write name="modify" property="districtcount"/>"> <bean:write name="modify" property="districtcount"/></a></td>
                    </tr>
                </logic:iterate>
             </table>
         
           		
        </form>   
    </body>
    <p>&nbsp;</p>
  
</html:html>