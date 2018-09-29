<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil,java.util.ArrayList,java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: New User Login Details</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fixedColumns.bootstrap.min.css"/> 
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css" />  
 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script> 
 
<%
	ArrayList statusList	= (ArrayList)request.getAttribute("statusList");
	ArrayList roleList 		= (ArrayList)request.getAttribute("roleList");
	ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
	ArrayList campList		= (ArrayList)request.getAttribute("campList");
	ArrayList mandalList	= (ArrayList)request.getAttribute("mandalList");
	 
	String alert_msg	= CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css	= CommonUtility.checkNullObj(request.getAttribute("alert_css")); 
	 
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
	 
 
	 
%>

	
 
</head>
<body> 
<%
    if(!alert_msg.equals("")) 
    {
    %> 
		<div class="row" style="margin-top:10px;"> 
		    <div class="alert <%=alert_css %> fade in alert-dismissable">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
			    <b><%=alert_msg%></b>
			</div>  
		</div> 
	<%
    }
%> 
		 
<div class="row">
	<div class="col-md-12">
	 <div class="panel panel-primary">
       <div class="panel-heading">
         <h4 class="panel-title">New User Login Details</h4>
        </div>
        <div class="panel-body"> 
			<div class="row"> 
       			  <div class="col-md-4"> 
	        			 	 <div class="panel panel-success">
						        <div class="panel-heading">
						             <h4 class="panel-title" style="text-align: center; font-weight: bold;">
										New User Login Details
									 </h4>
						        </div>
						        <div class="panel-body"> 
						         <div class="alert alert-info">
					        		<form name="NewUserLoginFormId" id="NewUserLoginFormId" method="post">
			        						<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>"> 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">Role</div>
											     <%=ComboUtil.createStrComboBoxAutoWithAttribute("selRoleId", roleList,"", "form-control", "", true, true, "", "") %>
											 </div>
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">District</div>
											     <div id="districtDivID">
											     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selDistrict", districtList,"", "form-control", "", true, true, "-Select-", "") %>
											     </div>
											 </div>  
																			 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">Camp</div>
											     <div id="campDivID">
											     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selCampId", campList,"", "form-control", "", true, true, "-Select-", "") %>
											     </div>
											 </div>
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">Mandal</div>
											     <div id="mandalDivID">
											     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selMandal", mandalList, "", "form-control", "", true, true, "-Select-", "") %>
											     </div>
											 </div> 
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">User Id</div>
											     <input type="text" class="form-control" id="login_user_id" name="login_user_id" maxlength="20" autocomplete="off" value="" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();">
											 </div>
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">Person Name</div>
											     <input type="text" class="form-control" id="user_disp_name" name="user_disp_name" maxlength="40" autocomplete="off" value="" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value =(SpaceReplace(this.value)).toUpperCase();">
											 </div>
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">eMail Id</div>
											     <input type="text" class="form-control" id="user_email_id" name="user_email_id" maxlength="75" autocomplete="off" value="" onblur="EmailCheck(this)">
											 </div>
											 
								        	 <div class="input-group" style="margin-top:20px;">
											     <div class="input-group-addon">Contact No.</div>
											     <input type="text" class="form-control ClassValidateContactNo" id="user_contact_no" name="user_contact_no" maxlength="10" autocomplete="off" value="" onBlur="this.value = SpaceReplace(this.value);">
											 </div> 
									</form>
									</div>
								</div>
								<div class="panel-footer">
									<div class="row">
										<button type="button" id="btn_cancel_id" style="float: left" class="btn btn-danger">Cancel</button>
										<button type="button" id="btn_create_login_id" style="float: right" class="btn btn-success">Proceed</button>
									</div>
								</div>
						</div>
				   </div>
       			  	<div class="col-md-8"> 
	       			  	<div class="alert alert-info">
       			  			<h5 style="color:blue; font-weight: bold;">Note :</h5>
							<ul>
								<li>All fields are mandatory</li>
								<li>Please find list of existing user on right panel before clicking creating</li>
								<li>Existing users panel will be loaded by selecting Role or District or Camp or Mandal</li>
								<li>User Id must be minimum 8 characters of length with the combination of Alphanumeric and ( <strong style="color:blue; font-size: 15px;">/</strong> or <strong  style="color:blue; font-size: 15px;">_</strong> ) special characters are allowed with out any white space</li>
								<li>Person Name must be minimum 5 characters of length </li>
							</ul> 
	       			  	</div>
	       			  	<div class="row">
	        			 	 <div class="panel panel-primary">
						        <div class="panel-heading">
						             <h4 class="panel-title" style="text-align: center;">
										<b>Existing User Login Details</b>
									 </h4>
						        </div> 
						        <div class="panel-body" style="min-height: 450px;" id="ExistUserLoginDtlsDispDivId">
						        	<%@include file="/master/LoginUserDetailsTable.jsp" %>
						        </div> 
					    	</div>
					   </div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	  
 <script type="text/javascript">
 
 var ajaxCallLoadStatus = 0;
 
	function postRequest(strURL,textID) 
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
	    		updatepage("<center><img src='<%=request.getContextPath()%>/images/loading.gif' width='15' height='15'></center>",textID);
	        }
		    else if(xmlHttp.readyState == 2)
		    {
		    	updatepage('Wait..',textID);
		    }
		    else if(xmlHttp.readyState == 3)
		    {
		    	updatepage('Please Wait..',textID);
		    }
		    else if (xmlHttp.readyState == 4) 
	        {
		    	var errorCode = xmlHttp.getResponseHeader('errorCode');
		    
		    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
		    	{
	        		updatepage(xmlHttp.responseText,textID);
	        		
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
	
	function updatepage(msg,id)
	{		
		if(msg!="" && msg!="null")
		{
			document.getElementById(id).innerHTML=msg;
			fun_addselectclass(id);
		}
  
	}

	
	function fun_addselectclass(id)
	{
		$( "#"+id+" select" ).each(function( index ) 
				{
					$(this).addClass("form-control");
				});
	}
		$( document ).ready(function() 
		{
			
			
			$(".ClassValidateContactNo").blur(function()
	    			{ 
	    				var NumberRegex = /[789]\d{9}$/;
	    				var myno = $(this).val();
	    			 
	    					if((myno.length < 10) || NumberRegex.test(myno)==false || myno=="999999999" || myno=="7777777777" || myno=="8888888888")
	    					{  
	    						$(this).val("");
	    					}
	    					else
	    					{
	    						fun_reload_exist_userlogin();
	    						return true;
	    					} 
	    				
	    			});
			
			$("#login_user_id").blur(function()
	    			{  
						//alert("HI : "+$(this).val());
				
						var userNameRegex =  /^\S+[a-zA-Z0-9]\w[\/|\_]?\w[\/|\_]?\w$/; 
	    				var user_id_login = $(this).val();
	    			 
	    					if((user_id_login.length < 8) || userNameRegex.test(user_id_login)==false)
	    					{  
	    						$(this).val("");
	    					}
	    					else
	    					{
	    						fun_reload_exist_userlogin();
	    						return true;
	    					} 
	    				
	    			});
			
			
				$('#selRoleId').change(function()
		  		{	 
		  			$("#districtDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selDistrict", new ArrayList(), "", "form-control", "", true, true, "-Select-")%>");  
		  			$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
					$("#mandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>"); 
					
					if($('#selRoleId').val()!="-1")
			  		{ 
		  				postRequest("<%=request.getContextPath()%>/ajax.do?action=loaddistbyroleid&selroleid="+ $('#selRoleId').val()+"&randomid="+Math.random(),"districtDivID");
			  		}
					
		  			fun_reload_exist_userlogin();
		  		});

				
				$('body').on('change','#selDistrict',function()
	    		{	 
					$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
					$("#mandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
		  			
		  			if($('#selRoleId').val()!="-1" && $('#selDistrict').val()!="-1")
			  		{ 
		  				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadcampbyroleiddistid&selroleid="+ $('#selRoleId').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"campDivID");
			  			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmandalbyroleiddistid&selroleid="+ $('#selRoleId').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"mandalDivID");
			  		}  
		  			 fun_reload_exist_userlogin();
  			 	});

				$('body').on('change','#selCampId',function()
			    {
		  			 fun_reload_exist_userlogin();
			    });
				
				$('body').on('change','#selMandal',function()
				{
					fun_reload_exist_userlogin();
				});
				
				$('body').on('blur','#user_email_id',function()
				{
					fun_reload_exist_userlogin();
				});
				
				 
				$("#btn_cancel_id").click(function()
				{
					location.replace("<%=request.getContextPath()%>/userloginaction.do?action=lognewuserlogindetails&randomnum="+Math.random());
				});
				
				   
				
				$("#btn_create_login_id").click(function()
				{
					  var noOfUserCount = 0; 
			 
					  if(fun_validateFormValues()==true)
						{ 
						  noOfUserCount = parseInt($("#totalExistUserCount").val());
						   
						  if(noOfUserCount>=1)
						  {
						  	alert("Please check the Existing User Login Details. As already user existing with same role");
						  }
						  
							  if(confirm("Are your sure do you want to proceed.\nPlease check the details before clicking ok. ")==true)
							  {  
							  		document.NewUserLoginFormId.target="_self";
							  		document.NewUserLoginFormId.action="<%=request.getContextPath()%>/userloginaction.do?action=lognewuserlogindetails&randomid="+Math.random();
							  		document.NewUserLoginFormId.submit();
							  }
							  else
							  {
							  	alert("Request cancelled by user");
							  	return false;
							  }
						}
					
					
					
				});
		});
	
		 function fun_reload_exist_userlogin()
		 		{
			       var msgDiv = $("#ExistUserLoginDtlsDispDivId");
					 	  
			       /*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				/*Screen Locking Ended */
			       
					if(ajaxCallLoadStatus==0 && ($('#selRoleId').val()!="-1" || $("#login_user_id").val()!="" || $("#user_email_id").val()!="" || $("#user_contact_no").val()))
					{
						ajaxCallLoadStatus = 1;
						  $("#role_id").val($('#selRoleId').val());
						  $("#district_id").val($('#selDistrict').val());
						  $("#camp_id").val($("#selCampId").val());
						  $("#mandal_id").val($("#selMandal").val());
						  $("#search_user_id").val($("#login_user_id").val());
						  $("#search_email_id").val($("#user_email_id").val());
						  $("#search_contact_no").val($("#user_contact_no").val());
						   
							$.ajax({
								  type: 'POST',
							  	  url: '<%=request.getContextPath()%>/userloginaction.do?action=viewexistingloginuserdetails&randomid='+Math.random(),
								  data: $('#LoginUserTableFormId').serialize(),
								  success: function (data, textStatus) 
								  {  
									  ajaxCallLoadStatus = 0;
									  msgDiv.html(data);
									  $('#blocklayer').css({"display": "none"});
									  $('#processlayer').css({"display": "none"});
								  },
								  error: function(xhr, status, e)
								  {
									  ajaxCallLoadStatus = 0;	
									  $('#blocklayer').css({"display": "none"});
									  $('#processlayer').css({"display": "none"});
									 alert("error");
								  
								  }
								}); 
					}
					else
					{
						msgDiv.load("<%=request.getContextPath()%>/master/LoginUserDetailsTable.jsp");
						  $('#blocklayer').css({"display": "none"});
						  $('#processlayer').css({"display": "none"});	
					}

					
		 		}
		
		
		
		function fun_validateFormValues()
		{
			

			var userNameRegex =  /^\S+[a-zA-Z0-9]\w[\/|\_]?\w[\/|\_]?\w/;
			
			var selRoleId 				= $("#selRoleId").val();
			var selDistrict 			= $("#selDistrict").val();
			var selCampId				= $("#selCampId").val();
			var selMandal				= $("#selMandal").val();
			var user_email_id			= $("#user_email_id").val();
			var user_contact_no			= $("#user_contact_no").val();
			 
			var dist_leng				= $('select#selDistrict option').length;
			var camp_leng				= $('select#selCampId option').length;
			var mand_leng				= $('select#selMandal option').length;
			
			if(selRoleId=="-1")
				{
					alert("Please select Role");
					$("#selRoleId").focus();
					return false;
				}
				else if(dist_leng>1 && selDistrict=="-1")
				{
					alert("Please select District");
					$("#selDistrict").focus();
					return false;
				}
				else if(camp_leng>1 && selCampId=="-1")
				{
					alert("Please select Camp");
					$("#selCampId").focus();
					return false;
				}
				else if(mand_leng>1 && selMandal=="-1")
				{
					alert("Please select Mandal");
					$("#selMandal").focus();
					return false;
				}
				else if($("#login_user_id").val()=="" || ($("#login_user_id").val()).length<8 || userNameRegex.test($("#login_user_id").val())==false)
				{
					alert("Please enter valid User Id ( Minimum 8 characters Alphanumeric ( / or _ ) special characters are allowed)");
					$("#login_user_id").focus();
					return false;
				} 
				else if($("#user_disp_name").val()=="")
				{
					alert("Please enter Person Name");
					$("#user_disp_name").focus();
					return false;
				}
				else if(user_email_id=="")
				{
					alert("Please enter valid eMail Id");
					//$("#user_email_id").focus();
					return false; 
				}
				else if(user_contact_no=="")
				{
					alert("Please enter valid contact no");
					$("#user_contact_no").focus();
					return false; 
				} 
				else
				{
					return true;
				}
			
		}
		
</script>
</body>
</html>