<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="org.bf.disability.form.CommonReportForm" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>


<% String fromdate=(String)request.getAttribute("fromDate");

    String todate=(String)request.getAttribute("toDate");
    String fieldvalue= (String)request.getAttribute("fieldvalue");
    String secondfieldvalue=(String)request.getAttribute("secondfieldvalue");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
        <title>jsp</title>
       
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    </head>
    <body>
    
    <table  border="1" height="10%" align="center" cellspacing="1" 
            cellpadding="5"  width="50%">
            <caption><h2><font face="vardana" color="#660000"> 
            <%= session.getAttribute("TableHeading") %></font></h2></caption>
            <tr class="tbl_bg2_content" >
                <td align="center" bgcolor="white"  ><b><font size="2">
                <font color="#660000"><b>S.NO</b></font></td> 
                <td align="center" bgcolor="white" ><font size="2">
                      <font color="#660000">
              <b>Mandal Name</b></font></td> <td align="center" bgcolor="white">
                <font size="2"><font color="#660000"><b>Mandal Level Count</b>
                </font></td>
            </tr>
       <% int i=0;%>     
 <logic:iterate id="modify" name="reportlist" scope="request">
       <% if(i==0) { %>
   <font color="blue" ><b>you are in</b></font> <font color="red" > > </font><font size="2" color="blue"><a href="getMandalidForAssistive.do?getMandalReport=getMandalReport&districtid=<bean:write name="modify" property="districtid"/>&field=<%=fieldvalue%>&secondfield=<%=secondfieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>"><font color="blue" ><bean:write name="modify" property="districtname"/></font></a> District</font>        
        
        <% } %>
       
        
            
           
                <tr class="tbl_bg2_content">
                    <td align="center" bgcolor="white"  ><%=++i%></td>
                    <td align="center" bgcolor="white" >
                    <a href="getVillageidForAssistive.do?getVillageReport=getVillageReport&mandalid=<bean:write name="modify" property="mandalid"/>&districtid=<bean:write name="modify" property="districtid"/>&secondfieldvalue=<%=secondfieldvalue%>&fieldvalue=<%=fieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>">
                    <bean:write name="modify" property="mandal"/></a></td>
                    <td align="center" bgcolor="white" ><a href="selectmandalforassistive.do?nexxt=start&back=start&getmandalPersonalReport=getmandalPersonalReport&districtid=<bean:write name="modify" property="districtid"/>&fieldvalue=<%=request.getAttribute("fieldvalue")%>&secondfieldvalue=<%=request.getAttribute("secondfieldvalue")%>&mandalid=<bean:write name="modify" property="mandalid"/>&FromDate=<%= fromdate %>&ToDate=<%= todate %>&distcount=<bean:write name="modify" property="mandalcount"/>"> 
                    <bean:write name="modify" property="mandalcount"/></a></td>
                </tr>
            </logic:iterate>

           
        </table>
       
    </body>
    <p>&nbsp;</p>
   
</html:html>