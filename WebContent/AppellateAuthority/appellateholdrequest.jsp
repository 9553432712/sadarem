<%@page import="com.tcs.sadarem.util.ComboUtil,java.util.ArrayList,com.tcs.sadarem.util.CommonUtility"%>

 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-dialog.min.css"/>
	
	 
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-dialog.min.js"></script> 
	
	<style>
	
	a.list-group-item:focus, a.list-group-item:hover
	{
		background-color: #6bcebb !important;
	}
	
	.ccs-btn-close-hold
	{
		display: none!important;
	}
	
	</style>
	
	
<script type="text/javascript">
//Ajax Call

function postJAXRequest(strURL,textID) 
	{
	
		var xmlHttp;
	    if (window.XMLHttpRequest)  // Mozilla, Safari, ...
		 {
	          xmlHttp = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject)  // IE
		{
	          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    xmlHttp.open('POST', strURL, true);
	    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlHttp.onreadystatechange = function()
	     {
		    
	    	if (xmlHttp.readyState == 1) 
	        {
	    		loadPageContent("<center><img src='<%=request.getContextPath()%>/images/loader.gif' width='15' height='15'></center>",textID);
	        }
		    else if(xmlHttp.readyState == 2)
		    {
		    	loadPageContent('Wait..',textID);
		    }
		    else if(xmlHttp.readyState == 3)
		    {
		    	loadPageContent('Please Wait..',textID);
		    }
		    else if (xmlHttp.readyState == 4) 
	        {
		    	//alert(xmlHttp.responseText);
		    	var errorCode = xmlHttp.getResponseHeader('errorCode');
		    	
		    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
		    	{
		    		loadPageContent(xmlHttp.responseText,textID);
		    	}
		    	else
		    	{
		    		$('#errorMsg').html(errorCode).fadeIn(100);
		    		location.replace(window.location);
		    	}
	    	}
		};
		xmlHttp.send(strURL);
	}

	function loadPageContent(msg,id)
	{		
		if(msg!="" && msg!="null")
		{
			//alert(msg)
			document.getElementById(id).innerHTML=msg;
		}
	}
	
	$(document).ready(function() 
			 {
				$('#displayMsgSpan').html("");
				$('#costcenterADM').change(function(e)
						{
					
							var url="<%=request.getContextPath()%>/ajax.do?action=loaddistbyccforadm&ccid="+$('#costcenterADM').val()+"&randomid="+Math.random(); 
							postJAXRequest(url,'districtADMDIVID'); 
						});
				
				 
				  $(".holdbtn").click(function(e)
							{ 
					  
								var parentBlock	   = $(this);
					  
								var holdrequestid 	  = parentBlock.attr("holdrequestid"); 
								var holdsadaremid	  = parentBlock.attr("holdsadaremid");
								var holdcategory	  = parentBlock.attr("holdcategory");
								var holdpername		  = parentBlock.attr("holdpername");
								 
								var mydialog = $('#appellateholdDIVID');
								
								mydialog.modal('show');

								$("#holdrequestid").text(holdrequestid);
								$("#holdsadaremid").text(holdsadaremid);
								$("#holdcategory").text(holdcategory);
								$("#holdpername").text(holdpername);
								$("#holdmyrequestid").val(holdrequestid);
								
								
								
								//$('#appellateholdtitleID').text('new title');
								
								 
							});
				
				 
				
				
				$(document.body).on('click','#holdappsubmitbutID',function(e)
						{   
					 
							 var reqid = $("#holdrequestid").text();
							 var myremarks = $("#holdappremarks").val();
							 
							 if(myremarks.length==0)
								 {
								 	alert("Please enter remarks");
								 	$("#holdappremarks").focus();
								 	return false;
								 }
							 else
								 {
								 
								 BootstrapDialog.show({
							            title: 'Confirmation of Request ID : '+reqid,
							            
							        
							            message:'<div class="form-group"><div class="input-group"><div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Remarks</div><div class="form-control" style="height: 150px;overflow: auto;">'+myremarks+'</div></div></div>',
							            
							            closable:false,
							            buttons: [{
							                label: 'Confirm',
							                cssClass: 'btn-success', 
							                id: 'btn-confirm-hold',
							                action: function(dialog) 
							                { 
							                 
							                	
							                	$.ajax({
													  type: 'POST',
												  	  url: '<%=request.getContextPath()%>/ajax.do?action=appellateassessmenthold&randomid='+Math.random(),
													  data: $('#holdreqform').serialize(),
													  success: function (data, textStatus) 
													  {
														
														  dialog.getModalBody().html(data);
															 $("#btn-confirm-hold").hide();
															 $("#btn-cancel-hold").hide();
															 $("#btn-close-hold").removeClass("ccs-btn-close-hold");
													  },
													  error: function(xhr, status, e)
													  {
														 alert("error");
													    //alert(status, e);
													  }
													});
							                	
												
												//dialog.close();
							                }
							            }, {
							                label: 'Cancel',
							                id: 'btn-cancel-hold',
							                cssClass: 'btn-danger', 
							                action: function(dialog)
							                {
							                    dialog.close();
							                }
							            }, {
							                label: 'Close',
							                id: 'btn-close-hold',
							                cssClass: 'btn-danger ccs-btn-close-hold', 
							                action: function(dialog)
							                { 
							                    dialog.close();
							                    var mydialog = $('#appellateholdDIVID');
												
												mydialog.modal('hide');
												window.location="<%=request.getContextPath()%>/AppellateReassess.do?randomid="+Math.random();
							                    
							                }
							            } 
							            ]
							        });
								
								
								 }	
											 
						});
				

				$('#xCloseButID').click(function () 
						{
					
						var mydialog = $('#appellateholdDIVID');
							mydialog.modal('hide');
		
							$("#holdrequestid").text("");
							$("#holdsadaremid").text("");
							$("#holdcategory").text("");
							$("#holdpername").text("");
							$("#holdmyrequestid").val("");
					 		
	            		});
				

				  var cmax = 150; 
				
				$("#holdremarksremcount").text(cmax);
				
				$("#holdappremarks").keyup(function ()
						{ 
							  if ($(this).val().length >= cmax) 
							  {
							    $(this).val($(this).val().substr(0, cmax));
							  }
		
							  $("#holdremarksremcount").text(cmax - $(this).val().length); 
						});
				
				  
			 });
	  
</script>
 
	
    <div class="modal fade large" id="appellateholdDIVID" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" style="display: none; z-index: 1000!important;">
      <div class="modal-dialog" style=" margin-left: auto; margin-right: auto; z-index: 999!important; width: 70%!important;">
        <div class="modal-content" style=" z-index: 999!important;">
          <div class="modal-header" style=" z-index: 999!important;    background-color: #aedff7;">
           		<b class="modal-title" id="appellateholdtitleID">Appellate Authority Request Holding</b>  
          </div>
          <div class="modal-body"> 
    				            <form class="form-inline" id="holdreqform" name="holdreqform" method="post" autocomplete="off">
    				            <input type="hidden" id="holdmyrequestid" name="holdmyrequestid">
    				            <div class="row" style="text-align: center;">
	    				             <div class="col-md-6" style="text-align: left;">
						          		  <div class="form-group">
							                   <div class="input-group">
							                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Request ID</div>
							                   		<div id="holdrequestid" class="form-control"></div>
							                   </div>
							              </div>
						              </div>
						             <div class="col-md-6" style="text-align: left;">
							               <div class="form-group">
							                   <div class="input-group">
							                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> SADAREM ID</div>
							                   	<div id="holdsadaremid" name="holdsadaremid" class="form-control"></div>
							                   </div>
							                   <span class="help-block" id="error"></span>
							              </div>
							         </div>
					           </div>
					           <div class="row" style="text-align: center;">
						            <div class="col-md-6" style="text-align: left;">
						               <div class="form-group">
						                   <div class="input-group">
						                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Category</div>
							               		<div id="holdcategory" class="form-control"></div>
						                   </div>
						                   <span class="help-block" id="error"></span>
						              </div>
						            </div>
						             <div class="col-md-6" style="text-align: left;">
						               <div class="form-group">
						                   <div class="input-group">
						                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Person Name</div>
							               		<div id="holdpername" class="form-control"></div>
						                   </div>
						                   <span class="help-block" id="error"></span>
						              </div>
						            </div>
					            </div> 
					            
					           <div class="row" style="text-align: center;">
					           		  <div class="form-group">
						                   <div class="input-group">
						                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Remarks</div>
							               	 <textarea id="holdappremarks" name="holdappremarks" rows="3" cols="50" class="form-control"></textarea>
							               	 <span class="input-group-addon">characters remaining: <span id="holdremarksremcount" style="color: blue"></span></span>
						                   </div>
						                   <span class="help-block" id="error"></span>
						              </div>
					           </div>
					            
					           <div class="row" style="text-align: center;"> 
				          			<div class="col-md-6">
				          				 <button type="button" id="holdappsubmitbutID" class="btn btn-info"><b>Submit</b></button> 
							      	 </div>
							      	 
							      	 <div class="col-md-6">
				          				 <button type="button" id="xCloseButID" class="btn btn-danger"><b>Close</b></button> 
							      	 </div>
							    </div>
							</form>
							<br/>
							<div class="row" >
							<div class="col-md-12"> 
									<div class="row">
										<div class="col-md-12" id="bankbranchifscdtlsDIVID" style="min-height: 200px!important; max-height: 250px!important; overflow: auto; cursor: pointer;"> 
												  &nbsp;
										</div>
							</div>
								
							</div>
		 	</div>
				            
          </div>
        <!--   <div class="modal-footer">
            <button type="button" class="btn btn-default" id="supAdmCloseBut" data-dismiss="modal" style="color:red; font-weight:bold;">Close</button>
            <button type="button" class="btn btn-primary" id="supAdmUpdatBut" style="font-weight:bold;">Update Details</button>
          </div> -->
        </div>
      </div>
    </div>