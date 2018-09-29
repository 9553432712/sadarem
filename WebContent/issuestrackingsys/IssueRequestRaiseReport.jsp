<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%

    String roleId = "";
    if (session.getAttribute("roleId") != null) {
        roleId = session.getAttribute("roleId").toString();
    }
    String districtId = "";
    if (session.getAttribute("districtId") != null) {
        districtId = session.getAttribute("districtId").toString();
    }
    String mandalId = "";
    if (session.getAttribute("mandalId") != null) {
        mandalId = session.getAttribute("mandalId").toString();
    }
   
%>
        
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<style type="text/css">
	th
	{
		text-align:center !important;
	}
	</style>
	<link rel="stylesheet" type="text/css" href="/sadarem/css/bootstrap.min.css">
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Role Wise Grievences</title>
		
	</head>
	<body>
		<form name="issuereport"  id="issuereport" method="post">
			<input type="hidden" id="view" name="view">
	<%if(roleId!="" || districtId!="" || mandalId!="") 
	{
	%>
		 	<%@page import="com.tcs.sadarem.issuetracksystem.actions.*" %>
 			<%@page import="java.util.*" %>
 			<%  
 								
 					ArrayList roleNames = new ArrayList(); 	
 					roleNames = (ArrayList)request.getAttribute("roleNames");
 					ArrayList outputTable = new ArrayList();
 					outputTable = (ArrayList)request.getAttribute("outputTable");
 					
			 %>
			<div align="center"><font color="#337ab7" size="4px"><b><U>Issue Raising Provision</U></b></font>
				 
			</div><br>
			
			<table width="70%" align="center">
				<tr>
					<td>	
						<a href="<%=request.getContextPath()%>/IssueRequestRaiseReport.xls?view=exportExcel&view=exportExcel" style="float:right;text-align:center;">
							<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
			 			</a>
					</td>
				</tr>
			</table>
			
 			<table style="width:70%" align="center" bgcolor="#ccf5ff" border="2" cellspacing="0" cellpadding="0">
 					 <thead style="height:30px" align="center"> 	
 					 <tr>
 					 <th style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</th>
 					 <%	
 					
 							for(int i=0;i<roleNames.size();i++)
							{	
						%>		
								<th style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%= roleNames.get(i).toString().substring(0, roleNames.get(i).toString().length()) %></th>		
						<%}%>
						</tr>
 					</thead> 
 					<%	 		
 						for(int i=0;i<outputTable.size();i++)
						{	
 							int buf=0;
 							if(i%2==0)
 							{
					%>		
							<tr bgcolor="#e2ebf4">
							<%}
 							else { 								
 									%>	<tr>
 											
 									<% 								
 							}
 							%>
 								<td align="center"><%out.print(i+1); %></td>
									<%	for(String retval:outputTable.get(i).toString().split(",", 11))
									{	
										
											if(buf==0)
											{
												
												++buf;%>
												<td align="left" style="height:30px"><%out.print(retval.substring(1, retval.length())); %></td>
												<%
											}
											else if(retval.toString().contains("True"))
											{

												%>	
																						
												<td align="center" style="height:30px"><span class="glyphicon glyphicon-ok-sign" style="color:green;"></span></td>
										<%}else{%>											
										<td align="center" style="height:30px"><span class="glyphicon glyphicon-remove-sign" style="color:red;"></span></td>
										<%}%>
										
									<%
									
										}
									%>
							</tr>		
					<%
						} 		
 					%>		
			</table>
			<%}
	else{%>
		
			<h1 style="background-color:#337ab7;"	>	<% out.print("Please Login");%></h1>	
	<%}
	%>
			</form>
		</body>
</html>