<%@ page  import ="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.HashMap,com.tcs.sadarem.util.PasswordEncriptDecrypt" language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>:: SADAREM :: Part-A Request Status</title>
 <%
 	ArrayList requestStatusList = (ArrayList)request.getAttribute("requestStatusList");
	HashMap dataList = new HashMap(); 

	String selectionTitle	= CommonUtility.checkNullObj(request.getAttribute("selectionTitle")); 
	
 
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
									      <input type="text" class="form-control " id="start_date" name="start_date" autocomplete="off" value="" size="15">				
					               		</div>
					                
					              		  <div class="input-group">
										      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div> 
										      <input type="text" class="form-control " id="end_date" name="end_date" autocomplete="off" value="" size="15">				
					               		  </div>
					              
					              		<span class="badge badge-warning">OR</span>
					              		
										<div class="input-group">
											<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Request Id</div> 
									      	<input type="text" class="form-control " id="request_id" name="request_id" autocomplete="off" value="" maxlength="10" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);">	
									 	</div>
									 	
					              		<span class="badge badge-warning">OR</span>
									 	
										<div class="input-group">
											<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Aadhaar Id</div> 
									      	<input type="text" class="form-control " id="proof_id" name="proof_id" autocomplete="off" value="" maxlength="12" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);">	
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
         	  <table id="pendinglistTABID" class="table table-striped table-bordered dataTable"> 
			   <thead>   
			     <tr>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Action</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">S.No.</th>
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
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" rowspan="2">Online Filled Date</th> 
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;" colspan="3">Status Details</th>
			  	 </tr>
			  	 <tr>  
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Status</th>
			       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Date</th>
			  	 </tr>
			  </thead>
			  <tbody>
			  	<%
			  	if(requestStatusList!=null && requestStatusList.size()>0) 
			  	{
			  		  
			  		
			  	  for(int i=0;i<requestStatusList.size();i++)
			  	  {
			  		dataList = (HashMap)requestStatusList.get(i);
			  		 
			  	%>
				  <tr>
				   <td style="text-align:center; vertical-align: middle">
					<button type="button" class="btn btn-info viewPrintBtnId" id="<%=(PasswordEncriptDecrypt.encrypt(CommonUtility.checkNullObj(dataList.get("request_id")))).replace("+","@^")%>" myrequestformid="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">View/Print</button>
				   </td>  
				   <td style="text-align:center; vertical-align: middle"><%=i+1%></td>  
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
			       <td style="text-align:center; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("form_entered_date"))%> </td>
			       <td style="text-align:left; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("req_status_desc"))%> </td>
			       <td style="text-align:center; vertical-align: middle"><%=CommonUtility.checkNullObj(dataList.get("last_updated_date"))%> </td>
				  </tr>
				<%
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
        <%
		  	}
		  	else
		  	{
		  	%>
		  	<div class="alert alert-info">
		  		<b>Please select atleast one option and click on "Search" button</b>
		  	</div>		
		  	<%
		  	}
        %>
        </div>
    </div>
</div>

<div class="modal fade" id="MyRequestStatusModelID" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog"  style=" margin-left: auto; margin-right: auto; width:70% !important"> 
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4 class="modal-title">View and Print Request Details</h4>
            </div>
            <div class="modal-body"><img alt="" src="<%=request.getContextPath()%>/images/processing.gif"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
      
    </div>
</div>
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	
		
<script type="text/javascript"> 
	$( document ).ready(function() 
	{  

		$('.viewPrintBtnId').on('click',function()
		{ 
			var id = $(this).attr('id');
			var myformid = $(this).attr("myrequestformid");
			
			 //alert(id);
			//alert(myformid);
			
			var url="<%=request.getContextPath()%>/withoutproofparta.do?mode=partarequestviewprint&request_id="+id+"&reqFormId="+myformid+"&radomid="+Math.random();
			// alert(url);
		    $('.modal-body').load(url,function()
		    {
		        $('#MyRequestStatusModelID').modal({show:true});
		    });
		});
 
		
		<% 
	    if(requestStatusList!=null && requestStatusList.size()>0)
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
	    
	    $("#request_id").change(function()
   		{
			$("#proof_id").val("");
			$("#start_date").val("");
   			$("#end_date").val("");  
   		}); 
				
	    
	    	$("#search_btn_id").click(function()
	    			{
			    	
								if(
										$("#request_id").val()=="" && $("#proof_id").val()=="" && 
										$("#start_date").val()=="" && $("#end_date").val()==""
								)
								{
									alert("Please select atleast one option and click on Search button"); 
									return false;
								}
								else if
								(
										
										($("#start_date").val()=="" && $("#end_date").val()!="") || 
										($("#start_date").val()!="" && $("#end_date").val()=="") ||
										($("#request_id").val()=="" && $("#proof_id").val()=="" &&  ($("#start_date").val()=="" || $("#end_date").val()=="")) 
								)
								{
									alert("Please select from date and to date.");
									return false; 
								}
								else if($("#proof_id").val()!="" && fun_validateAadhaarID($("#proof_id").val())==false)
								{
									alert("Please enter valid Aadhaar Id.")	;
									$("#proof_id").focus();
									return false;
								}
								else
								{

									 /*Screen Locking Started */
											$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
										    $('#processlayer').css({"display": "block","z-index":"110000"});
									/*Screen Locking Ended */ 
									
								 	document.partAReqFormId.target="_self";
								 	document.partAReqFormId.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=loadmyreqsatusdtls&randomid="+Math.random();
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