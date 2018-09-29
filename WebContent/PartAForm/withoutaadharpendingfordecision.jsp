<%@ page  import ="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>:: SADAREM :: Part-A pending request</title>
 <%
 	ArrayList pendingReqList = (ArrayList)request.getAttribute("pendingRequestList");
	HashMap dataList = new HashMap();

	String request_type	= CommonUtility.checkNullObj(request.getAttribute("request_type"));
	String start_date 	= CommonUtility.checkNullObj(request.getAttribute("start_date"));
	String end_date 	= CommonUtility.checkNullObj(request.getAttribute("end_date"));
	

	if(request_type.equals(""))
	{
		request_type = "P";
	}
	
	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
 %>
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fixedColumns.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />

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
          	<h4 class="panel-title"><b>Part-A request Worklist</b></h4>
 		</div>   
        <div class="panel-body" style="min-height: 400px;">
        	<div class="row">
		             		<div class="col-md-8 col-md-offset-2 alert alert-info">
					              <form class="form-inline" id="partAReqFormId" name="partAReqFormId" method="post" autocomplete="off"> 
										<div class="input-group">
											<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Status</div>
			   								<select id="request_type" name="request_type"  class="form-control" style="height:35px" >
											  <option value="-1">-Select-</option>
											  <option value="P" <% if(request_type.equals("P")){ %> selected <%} %>>Pending</option>
											  <option value="A" <% if(request_type.equals("A")){ %> selected <%} %>>Approved</option>
											  <option value="R" <% if(request_type.equals("R")){ %> selected <%} %>>Rejected</option>
											  <option value="D" <% if(request_type.equals("D")){ %> selected <%} %>>Deleted</option>
											</select> 
									 	</div>  
										 	 
										 <div class="input-group" id="fromdatediv" name="fromdatediv">
									      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date</div>
									      <input type="text" class="form-control " id="start_date" name="start_date" autocomplete="off" value=<%=start_date%>>				
					               		</div>
					                
					              		  <div class="input-group">
										      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div>
										      <input type="text" class="form-control " id="end_date" name="end_date" autocomplete="off" value=<%=end_date%>>				
					               		  </div>
					              	
					              		  <div class="input-group">
					              		 	<button type="button" id="search_btn_id" class="btn btn-success"><b>Search</b></button>
					              		 </div>
								 </form>
							 </div>
						</div>
        
        
        
         	  <table id="pendinglistTABID" class="table table-striped table-bordered dataTable"> 
			   <thead> 
			     <tr> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">S.No.</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Decision</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Request Id</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Proof Id</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">SADAREM Id</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Form No.</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Form filled Date</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Person Name</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Person Name (Telugu)</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Date of Birth</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Gender</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Relation</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Contact No.</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" colspan="3">Operator Details</th> 
			  	 </tr>
			  	 <tr>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">ID</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Name</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Date of Entry</th>
			  	 </tr>
			  </thead>
			  <tbody>
			  	<%
			  	if(pendingReqList!=null && pendingReqList.size()>0)
			  	{
			  		String butnClass = "btn-primary";
			  		String butTitle	 = "Pending";
			  		
			  	  for(int i=0;i<pendingReqList.size();i++)
			  	  {
			  		dataList = (HashMap)pendingReqList.get(i);
			  		
			  		if(CommonUtility.checkNullObj(dataList.get("req_status")).equalsIgnoreCase("A"))
			  		{
			  			butnClass = "btn-success";
			  			butTitle  = "Approved";
			  		}
			  		else if(CommonUtility.checkNullObj(dataList.get("req_status")).equalsIgnoreCase("R"))
			  		{
			  			butnClass = "btn-danger";
			  			butTitle  = "Rejected";
			  		}
			  		else if(CommonUtility.checkNullObj(dataList.get("req_status")).equalsIgnoreCase("D"))
			  		{
			  			butnClass = "btn-warning";
			  			butTitle  = "Deleted";
			  		}
			  	%>
				  <tr>
				   <td style="text-align:center; vertical-align: middle"><%=i+1%></td> 
				   <td style="text-align:center; vertical-align: middle">
				   		<button type="button" class="btn <%=butnClass%> actionBtn-css" value="<%=CommonUtility.checkNullObj(dataList.get("request_id"))%>"><%=butTitle%></button>
				   	</td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("request_id"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("proof_id"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("sadarem_id"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("reference_form_number"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("form_fill_date"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("surname"))%>&nbsp;<%=CommonUtility.checkNullObj(dataList.get("first_name"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("personname_telugu"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("date_of_birth"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("gender_name"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("relation_name"))%> </td> 
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("phone_no"))%> </td>
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("login_id"))%> </td>
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("login_name"))%> </td>
			       <td style="text-align:center; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("form_entered_date"))%> </td>
				  </tr>
				<%
			  	  }
			  	}
			  	else
			  	{
			  	%>
					  <tr>
					   <td style="text-align:center; vertical-align: middle" colspan="16">
							<b>No records are found to display</b>
					   </td>
					  </tr>
				<%
			  	}
				%>
			 </tbody>
			</table>
        
        </div>
    </div>
</div>

	<form id="RequestFormId" name="RequestFormId" method="post">
		<input type="hidden" id="mode" name="mode" value="loadrequestdtlsforaction">
		<input type="hidden" id="reqFormId" name="reqFormId" value="<%=session.getAttribute("sesFormId")%>">
		<input type="hidden" id="request_id" name="request_id" value="">
	</form>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	
		
<script type="text/javascript"> 
	$( document ).ready(function() 
	{ 
		<% 
	    if(pendingReqList!=null && pendingReqList.size()>0)
	    {
	    %>
	    
	    var table = $('#pendinglistTABID').DataTable( 
	    {
	        scrollY:        "300px",
	        scrollX:        true,
	        scrollCollapse: true,
	        paging:         false,
	        fixedColumns:   true,
	        ordering: false,
	        fixedColumns:   
	        {
	            leftColumns:4
	        }
	    } );
	     
		<%
	    }
	    %>
	    
	    $("#request_type").change(function()
	    		{
	    			if($(this).val()=="P" || $(this).val()=="-1")
	    			{
	    				$("#start_date").val("");
	    				$("#end_date").val("");
	    			}
	    		});
	    
	    $('.actionBtn-css').click(function()
				{	 
			  
				    if(confirm("Are you sure you want to submit the request?"))
					{ 

						 /*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
						
				    	$("#request_id").val($(this).val());
				    	
		    		 	document.RequestFormId.target="_self";
					 	document.RequestFormId.action="<%=request.getContextPath()%>/withoutproofparta.do?randomid="+Math.random();
					 	document.RequestFormId.submit();
							 	
					} 
				});
				
	    
	    	$("#search_btn_id").click(function()
	    			{
			    	
								if($("#request_type").val()=="-1")
								{
									alert("Please select status");
									$("#request_type").focus();
									return false;
								}
								else if
								(
										($("#start_date").val()=="" && $("#end_date").val()!="") || 
										($("#start_date").val()!="" && $("#end_date").val()=="") ||
										($("#request_type").val()!="P" && ($("#start_date").val()=="" || $("#end_date").val()==""))
										
								)
								{
									alert("Please select from date and to date.");
									return false; 
								}
								else
								{

									 /*Screen Locking Started */
											$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
										    $('#processlayer').css({"display": "block","z-index":"110000"});
									/*Screen Locking Ended */ 
									
								 	document.partAReqFormId.target="_self";
								 	document.partAReqFormId.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=loadpendingrequestforpartadtls&randomid="+Math.random();
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