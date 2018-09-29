<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%try{ %>
<%
     ArrayList reassessList = new ArrayList();
     ArrayList innerList = new ArrayList();
     reassessList =(ArrayList)request.getAttribute("resultList");  
    
 %>
 <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
     	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/teluguname.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
 <style>
  .table>thead>tr>th
   {
     text-align:center;
     color:#fff;
     background:#62A0D6;
   }
 </style>
 <script type="text/javascript">
			 $(document).ready( function()
			 {		 
				$('#issueData').dataTable
				   ({
					   "iDisplayLength": 10, 
					   "paging" : false,
					   "scrollY" : 300
				    });
			 });
</script>
 
<div class="main_container">
  <div class="container">
   <h4 style="text-align:center;color:#3338B7;">Report For Part-A without Proof</h4>
   <form name="appellateForm" id="appellateForm" method="post">
   <table id="issueData" class="table table-striped table-bordered" width="90%">
     <thead> 
      <tr>
      	<th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">SNO.</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">SADAREM ID</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">Surname</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">Name</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">DOB</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">Relation Name</th>
        <th style="background-color:#337ab7;padding:5px; color:#fff;" align="center">Part-A Created Date</th>
        
      </tr>
      </thead>
      <%if(reassessList.size()>0)
      { 
       for(int i=0;i<reassessList.size();i++)
       {
    	  innerList = new ArrayList();
    	  innerList = (ArrayList)reassessList.get(i);
       %>     
        <tr>
          <td align="center"><%=i+1 %></td>
          <td align="center"><%=innerList.get(0)%></td>
          <td align="left"><%=innerList.get(1) %></td>
          <td align="left"><%=innerList.get(2) %></td>
          <td align="left"><%=innerList.get(3) %></td>
          <td align="left"><%=innerList.get(4) %></td>
          <td align="left"><%=innerList.get(5) %></td>
       </tr>
     <%}
      }
     else
     { %>
     <tr>
     <td colspan="8" align="center">No records found to display.</td>
     </tr>
     <%} %>
   </table>
   </form>
   </div>
</div>
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
					$(document).ready(function()
							{
						 
						//Examples of how to assign the Colorbox event to elements
						$(".iframe").colorbox(
								{
								iframe:true, width:"80%", height:"48%",
								 
								});
						$(".prntbut").click(function(e){
							alert("Print Button");
						});
						$(".callbacks").colorbox({
							onOpen:function(){ alert('onOpen: colorbox is about to open'); },
							onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
							onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
							onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
							onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
						});
						$('#cboxClose').click(function (){alert(1);});
		
						$('.non-retina').colorbox({rel:'group5', transition:'none'});
						$('.retina').colorbox({rel:'group5', transition:'none', retinaImage:true, retinaUrl:true});
						
						//Example of preserving a JavaScript event for inline calls.
						$("#click").click(function(){ 
							$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit",}).text("Open this window again and this message will still be here.");
							return false;
						});
					}
					);
		
					
				</script>
<%}catch(Exception e){e.printStackTrace();} %>




