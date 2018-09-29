<%@ page  import ="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>:: SADAREM :: Part-A Request Status</title>
 <%
 	ArrayList requestStatusList = (ArrayList)request.getAttribute("requestStatusList");
	HashMap dataList = new HashMap(); 

	String selectionTitle	= CommonUtility.checkNullObj(request.getAttribute("selectionTitle")); 
	

	String start_date = CommonUtility.checkNullObj(request.getAttribute("start_date"));
	String end_date	 = CommonUtility.checkNullObj(request.getAttribute("end_date"));
	
	
	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
 %> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fixedColumns.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>  

<style type="text/css">
    
 /* Process Layer Started */
	#processlayer
	 {
		width: 300px;
		height: 50px;
		background: #ECF1EF;
		border: red dotted 5px;
		text-align: center;
		position: fixed;
		margin-right: -150px;
		margin-top: -75px;
		right: 50%;
		top: 50%;
		z-index: 99999;
		display: none;
	}
	
	#blocklayer 
	{
		position: absolute;
		left: 0;
		top: 0;
		background: #ECF1EF;
	}

/* 	 Process Layer Ended  */
  
</style> 
 
</head>
<body>
<div id="processlayer" >
	<font color="blue" size="2">Processing Please Wait...</font><br/>
	<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
</div>

<div id="blocklayer">
</div>  

<div class="row" style="padding: 0px 5px 0px 5px;">
	<%
	if(alert_msg!=null && alert_msg.trim().length()!=0 &&  !alert_msg.equals("") )
		{
		%> 
			<div class="alert <%=alert_css%>">
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <b><%=alert_msg %></b>
			</div>
<%
		} 
	%>

	<div class="panel  panel-primary">  
		<div class="panel-heading">
          	<h4 class="panel-title"><b>Part-A request status details</b></h4>
 		</div>   
        <div class="panel-body" style="min-height: 400px;">
        	<div class="row">
		             		<div class="alert alert-warning">
					              <form class="form-inline" id="partAReqFormId" name="partAReqFormId" method="post" autocomplete="off"> 
					              	<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
					              
										 <div class="input-group">
									      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date</div>
									      <input type="text" class="form-control " id="start_date" name="start_date" autocomplete="off" value="<%=start_date %>" size="15" readonly>				
					               		</div>
					                
					              		  <div class="input-group">
										      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div> 
										      <input type="text" class="form-control " id="end_date" name="end_date" autocomplete="off" value="<%=end_date %>" size="15" readonly>				
					               		  </div>  
					               		  
					              		  <div class="input-group">
					              		 	<button type="button" id="search_btn_id" class="btn btn-success"><b>Search</b></button>
					              		 </div>
								 </form>
							 </div>
						</div>
        
        	<%
		  	if(requestStatusList!=null)
		  	{
        	%>
         		<div class="alert alert-info">Search result based on the option(s) <%=selectionTitle%></div>
         <center>
         	 <table class="table table-striped table-bordered dataTable" style="width: 85%; text-align: center;"> 
			   <thead>   
			     <tr>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">S.No.</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">District</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Camp</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Pending</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Approved</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Rejected</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Deleted</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Total</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Pending <br>since inception</th> 
			  	 </tr> 
			  </thead>
			  <tbody>
			  	<%
			  	if(requestStatusList!=null && requestStatusList.size()>0) 
			  	{
			  		  
			  		
			  	  for(int i=0;i<requestStatusList.size();i++)
			  	  {
			  		dataList = (HashMap)requestStatusList.get(i);
			  		 
			  		if(!CommonUtility.checkNullObj(dataList.get("district_name")).equalsIgnoreCase("total"))
			  		{
			  	%>
					  <tr>
					   <td style="text-align:center; vertical-align: middle"><%=i+1%></td>  
				       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("district_name"))%> </td> 
				       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("camp_name"))%> </td> 
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("pending_count"))%> </td> 
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("approved_count"))%> </td> 
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("rejected_count"))%> </td> 
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("deleted_count"))%> </td>  
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("total_count"))%> </td>  
				       <td style="text-align:right; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("total_pending"))%> </td>  
					  </tr>
				<%
			  		}
			  		else
			  		{
				 	%>
						  <tr> 
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" colspan="3"><%=CommonUtility.checkNullObj(dataList.get("district_name"))%> </th>  
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("pending_count"))%> </th> 
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("approved_count"))%> </th> 
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("rejected_count"))%> </th> 
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("deleted_count"))%></th>
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("total_count"))%></th>
					       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=CommonUtility.checkNullObj(dataList.get("total_pending"))%></th>
						  </tr>
					<%
			  		}
			  	  }
			  	}
			  	else
			  	{
			  	%>
					  <tr>
					   <td style="text-align:center; vertical-align: middle" colspan="15">
							<b>No records are found to display</b>
					   </td>
					  </tr>
				<%
			  	}
				%>
			 </tbody>
			</table> 
			
			</center>
        <%
		  	}
		  	else
		  	{
		  	%>
		  	<div class="alert alert-info">
		  		<b>Please select From and To Dates click on "Search" button</b>
		  	</div>		
		  	<%
		  	}
        %>
        </div>
    </div>
</div>

	<form id="RequestFormId" name="RequestFormId" method="post">
		<input type="hidden" id="mode" name="mode" value="loadrequestdtlsforaction">
		<input type="hidden" id="reqFormId" name="reqFormId" value="<%=session.getAttribute("sesFormId")%>">
		<input type="hidden" id="request_id" name="request_id" value="">
	</form>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	
		
<script type="text/javascript"> 
	$( document ).ready(function() 
	{ 
		  
	    
		  
	    
	    	$("#search_btn_id").click(function()
	    			{
			    	
								if
								(
									$("#start_date").val()=="" || $("#end_date").val()==""
								)
								{
									alert("Please select from and to dates.");
									return false; 
								} 
								else
								{

									 /*Screen Locking Started */
											$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
										    $('#processlayer').css({"display": "block","z-index":"110000"});
									/*Screen Locking Ended */ 
									
								 	document.partAReqFormId.target="_self";
								 	document.partAReqFormId.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=loadpartarequestglancerpt&randomid="+Math.random();
								 	document.partAReqFormId.submit();
								
								}
	    			});
	    

	    $('#start_date').datetimepicker(
	   		{
	   			dayOfWeekStart : 1,
	   			lang:'en',
	   			formatDate:'d/m/Y',
	   			format:'d/m/Y',
	   			theme:'',
	   			step:05,
	   			timepicker:false,
	   			maxDate:'<%=CommonUtility.getDateTime("dd/MM/yyyy")%>',
	 			scrollMonth : false,
	 			scrollInput : false
	   		});
	   		
	   $('#end_date').datetimepicker(
	   		{
	   			dayOfWeekStart : 1,
	   			lang:'en',
	   			formatTime:'H:i',
	   			formatDate:'d/m/Y',
	   			format:'d/m/Y',
	   			theme:'',
	   			step:05,
	   			timepicker:false,
	   			maxDate:'<%=CommonUtility.getDateTime("dd/MM/yyyy")%>',
	 			scrollMonth : false,
	 			scrollInput : false
	   		});   
	    
	});
</script>
</body>
</html>