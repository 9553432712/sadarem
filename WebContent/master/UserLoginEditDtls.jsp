<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil,java.util.ArrayList,java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: User Login Details Edit</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css" /> 
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-toggle.min.css"/>
 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script> 
 
<%
	ArrayList statusList	= (ArrayList)request.getAttribute("statusList");
	ArrayList roleList 		= (ArrayList)request.getAttribute("roleList");
	ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
	ArrayList campList		= (ArrayList)request.getAttribute("campList");
	ArrayList mandalList	= (ArrayList)request.getAttribute("mandalList");
	
	HashMap userLoginDataList = (HashMap)request.getAttribute("userLoginDataList");
	 
	String alert_msg	= CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css	= CommonUtility.checkNullObj(request.getAttribute("alert_css")); 
	 
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
	 
	String is_active="",user_status="";
	String role_id="",district_id="",camp_id="",mandal_id="",person_name="",email_id="",contact_no="",user_active_status="",lock_status="";
	
	if(userLoginDataList!=null)
	{
		is_active = CommonUtility.checkNullObj(userLoginDataList.get("is_active"));
		user_status = CommonUtility.checkNullObj(userLoginDataList.get("user_status"));
		
		role_id				= CommonUtility.checkNullObj(userLoginDataList.get("role_id"));
		district_id			= CommonUtility.checkNullObj(userLoginDataList.get("district_id"));
		camp_id				= CommonUtility.checkNullObj(userLoginDataList.get("camp_id"));
		mandal_id			= CommonUtility.checkNullObj(userLoginDataList.get("mandal_id"));
		person_name			= CommonUtility.checkNullObj(userLoginDataList.get("person_name"));
		email_id			= CommonUtility.checkNullObj(userLoginDataList.get("email_id"));
		contact_no			= CommonUtility.checkNullObj(userLoginDataList.get("contact_no"));
		lock_status			= CommonUtility.checkNullObj(userLoginDataList.get("lock_status"));
	}
	
	
	boolean campCombStatus = true;
	boolean mandCombStatus = true;

	if(campList.size()==1)
	{
		campCombStatus = false;
	}
	
	if(mandalList.size()==1)
	{
		mandCombStatus = false;
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
						 
	<%
	if(userLoginDataList!=null)
	{ 
	%>
			<div class="row">
				<div class="col-md-12">
					 <div class="panel panel-primary">
				        <div class="panel-heading">
				             <h4 class="panel-title">
								Edit User Login Details
							 </h4>
				        </div>
				        <div class="panel-body" style="min-height: 350px;"> 
					        	<div class="row">
					        		<div class="col-md-6">
					        			 <div class="panel panel-info">
										        <div class="panel-heading">
										             <h4 class="panel-title">
														User Login Basic Details
													 </h4>
										        </div>
										        <div class="panel-body"> 
										        	
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">User ID</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("user_name"))%></span> 
							                            </div>
							                          </div>
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Login ID</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("user_id"))%></span> 
							                            </div>
							                          </div> 
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name</div>
														  <span class="input-group-addon" style="text-align:left;"><%=person_name%></span> 
							                            </div>
							                          </div>  
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Role</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("role_name"))%></span> 
							                            </div>
							                          </div>   
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">District</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("district_name"))%></span> 
							                            </div>
							                          </div>   
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Camp</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("camp_name"))%></span> 
							                            </div>
							                          </div>    
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Mandal</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("mandal_name"))%></span> 
							                            </div>
							                          </div>    
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">eMail Id</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("email_id"))%></span> 
							                            </div>
							                          </div>     
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Contact No.</div>
														  <span class="input-group-addon" style="text-align:left;"><%=CommonUtility.checkNullObj(userLoginDataList.get("contact_no"))%></span> 
							                            </div>
							                          </div> 
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Active / Inactive</div>
														  <span class="input-group-addon" style="text-align:center;">
																		<%
															       		if(is_active.equalsIgnoreCase("Y"))
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-ok-sign" style="color: green;font-size:18px;"></span>
															       		<%
															       		}
															       		else if(is_active.equalsIgnoreCase("N"))
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-remove-sign" style="color:red;font-size:18px;"></span>
															       		<%
															       		}
															       		else
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-info-sign" style="color:blue;font-size:18px;"></span>
															       		<%
															       		}
															       		%>
															  </span> 
								                            </div>
								                          </div>   
							                          
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">User Status</div>
														  <span class="input-group-addon" style="text-align:center;">
																		<%
															       		if(user_status.equalsIgnoreCase("Active"))
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-ok-sign" style="color: green;font-size:18px;"></span>
															       		<%
															       		}
															       		else if(user_status.equalsIgnoreCase("Inactive"))
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-remove-sign" style="color:red;font-size:18px;"></span>
															       		<%
															       		}
															       		else
															       		{
															       		%>
															       			 <span class="glyphicon glyphicon-info-sign" style="color:blue;font-size:18px;"></span>
															       		<%
															       		}
															       		%>
															  </span> 
								                            </div>
								                          </div>   
														     
									        		 <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Lock Status</div>
														  <span class="input-group-addon" style="text-align:center;">
																	<%
														       		if(lock_status.toString().equalsIgnoreCase("Y"))
														       		{
														       		%>
														       			 <span class="fa fa-lock fa-2x" style="color:red;"></span>
														       		<%
														       		}
														       		else if(lock_status.toString().toString().equalsIgnoreCase("N"))
														       		{
														       		%>
														       			 <span class="fa fa-unlock-alt fa-2x" style="color:green;"></span>
														       		<%
														       		} 
														       		%>
															  </span> 
								                            </div>
								                          </div>   
														    
												 	</div>  
										        </div>
									      </div>  
					        			  <div class="col-md-6"> 
							        			 <div class="panel panel-success">
												        <div class="panel-heading">
												             <h4 class="panel-title">
																User Login Edit Options
															 </h4>
												        </div>
												        <div class="panel-body">
													        		<form name="userloginUpateFormId" id="userloginUpateFormId" method="post">
											        						<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
															             	<input type="hidden" id="is_active_id" name="is_active_id" value="">
															             	<input type="hidden" id="is_locked_id" name="is_locked_id" value="">
															             	<input type="hidden" id="reset_pwd_id" name="reset_pwd_id" value="">
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Role</div>
																			     <%=ComboUtil.createStrComboBoxAutoWithAttribute("selRoleId", roleList, CommonUtility.checkNullObj(userLoginDataList.get("role_id")), "form-control", "", true, true, "", "") %>
																			 </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">District</div>
																			     <div id="districtDivID">
																			     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selDistrict", districtList,  CommonUtility.checkNullObj(userLoginDataList.get("district_id")), "form-control", "", true, true, "All", "") %>
																			     </div>
																			 </div>  
																											 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Camp</div>
																			     <div id="campDivID">
																			     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selCampId", campList,  CommonUtility.checkNullObj(userLoginDataList.get("camp_id")), "form-control", "", campCombStatus, true, "All", "") %>
																			     </div>
																			 </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Mandal</div>
																			     <div id="mandalDivID">
																			     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selMandal", mandalList,  CommonUtility.checkNullObj(userLoginDataList.get("mandal_id")), "form-control", "", mandCombStatus, true, "All", "") %>
																			     </div>
																			 </div> 
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Person Name</div>
																			     <input type="text" class="form-control" id="user_disp_name" name="user_disp_name" maxlength="40" autocomplete="off" value="<%=person_name%>" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = SpaceReplace(this.value);">
																			 </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">eMail Id</div>
																			     <input type="text" class="form-control" id="user_email_id" name="user_email_id" maxlength="75" autocomplete="off" value="<%=CommonUtility.checkNullObj(userLoginDataList.get("email_id")).trim()%>" onblur="EmailCheck(this)">
																			 </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Contact No.</div>
																			     <input type="text" class="form-control ClassValidateContactNo" id="user_contact_no" name="user_contact_no" maxlength="10" autocomplete="off" value="<%=CommonUtility.checkNullObj(userLoginDataList.get("contact_no"))%>" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);">
																			 </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">User Active/Inactive (Inactive : User can't login)</div>
																			      <input type="checkbox" id="user_active_inactive" name="user_active_inactive" data-toggle="toggle" data-on="Active" data-off="Inactive" class="form-control togglecheck" data-size="small" data-onstyle="success" data-offstyle="danger" <%if(is_active.equalsIgnoreCase("Y")){%>checked<%} %>>
																			  </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">User Lock/UnLock (Locked : User can't login)</div>
																			      <input type="checkbox" id="user_lock_status" name="user_lock_status" data-toggle="toggle" data-on="Un-Locked" data-off="Locked" class="form-control togglecheck" data-size="small" data-onstyle="success" data-offstyle="danger" <%if(lock_status.equalsIgnoreCase("N")){%>checked<%} %>>
																			  </div>
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Do you want to reset the password (Yes : Password will be reset)</div>
																			      <input type="checkbox" id="user_pwd_reset" name="user_pwd_reset" data-toggle="toggle" data-on="Yes" data-off="No" class="form-control togglecheck" data-size="small" data-onstyle="success" data-offstyle="danger" >
																			 </div> 
																			 
																        	 <div class="input-group" style="margin-top:20px;">
																			     <div class="input-group-addon">Remarks</div>
																			     <textarea class="form-control" id="user_change_remarks" name="user_change_remarks" rows="1" autocomplete="off" onKeyPress="return fun_limitlength(this,'100')" onBlur="this.value = SpaceReplace(this.value);"></textarea>
																			 </div>
																	</form>
														</div>
														<div class="panel-footer">
															<div class="row">
																<button type="button" id="btn_cancel_id" style="float: left" class="btn btn-danger">Cancel</button>
																<button type="button" id="btn_proceed_id" style="float: right" class="btn btn-success">Proceed</button>
															</div>
														</div>
													</div>
											</div>	
								</div>
						</div>
					</div>
				</div>
			</div>
	<%
	}
	else
	{
		%>
			<div class="alert alert-info"><b>Sorry User Details not available</b></div>
		<%
	}
	%>  
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-toggle.min.js"></script>
 <script type="text/javascript">
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
  			 	});
				
				
				$("#btn_cancel_id").click(function()
				{
					location.replace("<%=request.getContextPath()%>/userloginaction.do?action=loaduserloginchange&randomnum="+Math.random());
				}); 
				
				$("#btn_proceed_id").click(function()
				{
					$("#is_active_id").val("");
					$("#is_locked_id").val("");
					$("#reset_pwd_id").val("");
					
					var selRoleId 				= $("#selRoleId").val();
					var selDistrict 			= $("#selDistrict").val(); 
					var user_disp_name			= $("#user_disp_name").val(); 
					var selMandal				= $("#selMandal").val();
					var user_email_id			= $("#user_email_id").val();
					var user_contact_no			= $("#user_contact_no").val(); 

					var is_active="Y";
					var lock_user="N";
					var reset_pwd="N";
					
					if($("#user_active_inactive").is(':checked')==true)
					{
						is_active="Y";
					}
					else
					{
						is_active="N";
					}
					
					if($("#user_lock_status").is(':checked')==true)
					{
						lock_user="N";
					}
					else
					{
						lock_user="Y";
					}
					
					
					if($("#user_pwd_reset").is(':checked')==true)
					{
						reset_pwd="Y";
					}
					else
					{
						reset_pwd="N";
					}
				  
					
					if(
							selRoleId=="<%=role_id%>" && selDistrict=="<%=district_id%>" &&
							selMandal=="<%=mandal_id%>" && user_disp_name=="<%=person_name%>" && user_email_id=="<%=email_id.trim()%>" &&
							user_contact_no=="<%=contact_no.trim()%>" && 
							is_active=="<%=is_active.trim().toUpperCase()%>" &&
							lock_user=="<%=lock_status.trim().toUpperCase()%>" &&
							reset_pwd=="N" 
						)
						{
							alert("No changes done. Please change atleast one value and click on proceed button");
							return false;
						}
						else if(fun_validateFormValues()==true)
						{
							var activeConfirm = false;
							var lockConfirm = false;
							var pwdConfirm = false;
							
							  if(is_active=="N")
							  {
							 	activeConfirm = confirm("Are your sure do you want to inactive the user.");
							  }
						  	  else
							  {
							  	activeConfirm = true;
							  }
							  
							  if(lock_user=="1")
							  {
								  lockConfirm = confirm("Are your sure do you want to Lock the user.");
							  }
						  	  else
							  {
						  		lockConfirm = true;
							  }
							  
							  if(reset_pwd=="Y")
							  {
								  pwdConfirm = confirm("Are your sure do you want to reset the password.");
							  }
							  else
							  {
							  	pwdConfirm = true;
							  }
							  
							  if(activeConfirm==true && pwdConfirm==true && lockConfirm==true)
							  { 
								  $("#is_active_id").val(is_active);
								  $("#is_locked_id").val(lock_user);
								  $("#reset_pwd_id").val(reset_pwd);
								  
							  		document.userloginUpateFormId.target="_self";
							  		document.userloginUpateFormId.action="<%=request.getContextPath()%>/userloginaction.do?action=loguserloginupdates";
							  		document.userloginUpateFormId.submit();
							  }
							  else
							  {
							  	alert("Request cancelled by user");
							  	return false;
							  }
						}
					
					
					
				});
		});
	
		function fun_validateFormValues()
		{
			var selRoleId 				= $("#selRoleId").val();
			var selDistrict 			= $("#selDistrict").val();
			var selCampId				= $("#selCampId").val();
			var selMandal				= $("#selMandal").val();
			var user_email_id			= $("#user_email_id").val();
			var user_contact_no			= $("#user_contact_no").val();
			var user_change_remarks		= $("#user_change_remarks").val();
		
			 
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
				else if($("#user_disp_name").val()=="" || ($("#user_disp_name").val()).length<5)
				{
					alert("Please enter Person Name with minimum 5 characters");
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
				else if(user_change_remarks=="" || stripHTML($("#user_change_remarks"))==false || ($("#user_change_remarks").val()).length>100)
				{
					alert("Please enter valid remarks less than 100 characters");
					$("#user_change_remarks").val("");
					$("#user_change_remarks").focus();
				}
				else
				{
					return true;
				}
			
		}
		
</script>
</body>
</html>