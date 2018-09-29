<%@ page import="java.util.ArrayList" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<html>
<%try{ %>
<%
     ArrayList reassessList = new ArrayList();
     ArrayList innerList = new ArrayList();
     reassessList =(ArrayList)request.getAttribute("reassessList");  
    
 %>
 <head>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/> 
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/> 
     
 <style>
  .table>thead>tr>th
   {
     text-align:center;
     color:#fff;
     background:#62A0D6;
   }
 </style>
 <script>
  $(document).ready(function()
	{
	  $('#issueData').dataTable
	   ({
		   scrollY:        "300px",
	        scrollX:        true,
	        scrollCollapse: true,
	        paging:         false,
	        "searching": false,
	        fixedColumns:   {
	            leftColumns: 2,
	            rightColumns: 1
	        }
		   
	    });
	  
	  
	}) ;
 
 
 </script>
 
 </head>
 <body>
<div class="main_container">
  <div class="row">
   <h4 style="text-align:center;color:#3338B7;">APPELLATE AUTHORITY AT SPMU STATUS REPORT</h4>
   <form name="appellateForm" id="appellateForm" method="post">
   <input type="hidden" name="mode" id="mode" value='getAppellatePartADetails'>
   <table width="70%" align="center">
				<tr>
					<td>	
						<a href="<%=request.getContextPath()%>/appellatestatustracker.xls?mode=exportExcel" style="float:right;text-align:center;">
							<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
			 			</a>
					</td>
				</tr>
			</table>
   <table  class="table table-striped table-bordered" id="issueData">
      <thead>
      		
      		<tr>
      			<th rowspan="2">S.No.</th>
		        <th rowspan="2">District</th>
		        <th colspan="5">Total Applications (Online Grievance)</th>
		        <th colspan="3">Total</th>
		        <th colspan="3">Doubtful Case</th>
		        <th colspan="3">Third time Reassessment</th>
      			
      		</tr>
      		      	
	      <tr>
	        <th>Requests raised</th>
	        <th>Approved</th>
	        <th>Assessed</th>
	        <th>Pending</th>
	        <th>Holded</th>
	        <th>Assessed</th>
	        <th>Eligible</th>
	        <th>Rejected</th>
	        <th>Total</th>
	        <th>Eligible</th>
	        <th>Rejected</th>
	        <th>Total</th>
	        <th>Eligible</th>
	        <th>Rejected</th>
	      </tr>
      </thead>
       <tbody>
      <%if(reassessList.size()>0)
      { 
       for(int i=0;i<reassessList.size();i++)
       {
    	  innerList = new ArrayList();
    	  innerList = (ArrayList)reassessList.get(i);
    	   %>
    	   <tr>
    	   
    	   <% 
    	  for(int j=0;j<innerList.size();j++)
    	  {
       %>     
        		<td><%=innerList.get(j) %></td>
      
        	<%}	 %>
        </tr>
     <%}
      }
     else
     { %>
     <tr>
     <td colspan="8" align="center">No records found to display.</td>
     </tr>
     <%} %>
     </tbody>
   </table>
   </form>
   </div>
</div>

    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			 
<%}catch(Exception e){e.printStackTrace();} %>

</body>
</html>


