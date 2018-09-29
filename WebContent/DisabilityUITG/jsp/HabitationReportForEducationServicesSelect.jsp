<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<% String fromdate=(String)request.getAttribute("fromDate");

                String todate = (String) request.getAttribute("toDate");
                String villagename = (String) request.getAttribute("villagename");
                String secondfieldvalue = (String) request.getAttribute("secondfieldvalue");
                String fieldvalue = (String) request.getAttribute("fieldvalue");
%>

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
    <body >
     <table height="10%"  border="1" align="center" cellspacing="1" 
            cellpadding="5"  width="50%">
            <caption><h2><font face="vardana" color="#660000"> 
            <%= session.getAttribute("TableHeading") %></font></h2></caption> 
            <tr class="tbl_bg2_content" >
                <td align="center" bgcolor="white">
                <font size="2" color="#660000"><b>S.NO</b></font></td>
                <td align="center"  bgcolor="white" >
                <font size="2" color="#660000"><b>Habitation Name</b></font></td>
                <td align="center"  bgcolor="white" >
                <font size="2" color="#660000"><b>Habitation  Level Count</b></font></td>
            </tr>
            <% int i=0;%>         
    
     <logic:iterate id="modify" name="reportlist" scope="request">
              <% if(i==0) { %>
   <font color="blue"><b>you are in</b></font> <font color="red"> > </font>  <a href="getMandalidForEducation.do?getMandalReport=getMandalReport&districtid=<bean:write name="modify" property="districtid"/>&field=<%=fieldvalue%>&secondfield=<%=secondfieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>">
                        <bean:write name="modify" property="districtname"/> District</a> <font color="red"> >> </font>
     <a href="getVillageidForEducation.do?getVillageReport=getVillageReport&mandalid=<bean:write name="modify" property="mandalid"/>&districtid=<bean:write name="modify" property="districtid"/>&secondfieldvalue=<%=secondfieldvalue%>&fieldvalue=<%=fieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>">
                    <bean:write name="modify" property="mandalname"/> mandal</a> <font color="red"> >>> </font> 
    <a href="getHabitationidForEducation.do?getHabitationReport=getHabitationReport&villageid=<bean:write name="modify" property="villageid"/>&districtid=<bean:write name="modify" property="districtid"/>&mandalid=<bean:write name="modify" property="mandalid"/>&secondfieldvalue=<%=request.getAttribute("secondfieldvalue")%>&fieldvalue=<%=request.getAttribute("fieldvalue")%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>"> 
                    <%=villagename%> village</a>
<% } %>
        
           
                <TR class="tbl_bg2_content">
                    <td align="center" bgcolor="white" ><%=++i%>
                    </td>
                    <td align="center" bgcolor="white">
                        <bean:write name="modify"    property="habitationname"/> 
                    </td>
                    <td align="center" bgcolor="white"><a href="selecthabitationforeducation.do?nexxt=start&back=start&gethabitationPersonalReport=gethabitationPersonalReport&habitationid=<bean:write name="modify" property="habitationid"/>&villageid=<bean:write name="modify" property="villageid"/>&districtid=<bean:write name="modify" property="districtid"/>&mandalid=<bean:write name="modify" property="mandalid"/>&fieldvalue=<%= fieldvalue %>&secondfieldvalue=<%=secondfieldvalue%>&FromDate=<%= fromdate %>&ToDate=<%= todate %>&distcount=<bean:write name="modify" property="habitationcount"/>">
                        <bean:write name="modify" property="habitationcount"/></a>
                    </td>
                </TR>
            </logic:iterate>

          
        </table>     
    </body>
    <p>&nbsp;</p>
   
</html:html>
