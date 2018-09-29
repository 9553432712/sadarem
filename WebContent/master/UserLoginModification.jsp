<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil,java.util.ArrayList,java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: View/Edit user login details</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css" /> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/buttons.dataTables.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fixedColumns.bootstrap.min.css"/>
 
 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script> 

<%
ArrayList statusList	= (ArrayList)request.getAttribute("statusList");
ArrayList roleList 		= (ArrayList)request.getAttribute("roleList");
ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
ArrayList campList		= (ArrayList)request.getAttribute("campList");
ArrayList mandalList	= (ArrayList)request.getAttribute("mandalList");

ArrayList userLoginDataList	= (ArrayList)request.getAttribute("userLoginDataList");

String selStatusFlag 	= CommonUtility.checkNullObj(request.getAttribute("selStatusFlag"));
String selRoleId  		= CommonUtility.checkNullObj(request.getAttribute("selRoleId"));
String selDistrict 		= CommonUtility.checkNullObj(request.getAttribute("selDistrict"));
String selCampId		= CommonUtility.checkNullObj(request.getAttribute("selCampId"));
String selMandal 		= CommonUtility.checkNullObj(request.getAttribute("selMandal"));
String selUsername 		= CommonUtility.checkNullObj(request.getAttribute("selUsername"));


String alert_msg	= CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
String alert_css	= CommonUtility.checkNullObj(request.getAttribute("alert_css")); 
 

if(alert_css.equals(""))
{
	alert_css = "alert-info";
}
 
HashMap dataList = new HashMap();

if(alert_msg.equals("") && userLoginDataList!=null && userLoginDataList.size()==0)
{
	alert_msg ="No records found for search option. Please change search options.";
}
%>
	
</head>
<body>
<div class="row">
	<div class="col-md-12">
		 <div class="panel panel-primary">
	        <div class="panel-heading">
	             <h4 class="panel-title">
					View/Edit user login details
				 </h4>
	        </div>
	        <div class="panel-body" style="min-height: 350px;"> 
	        <div class="row">
	        			<form name="userloginFormId" id="userloginFormId" method="post">
	        				<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
					           <div class="form-inline">
						        	 <div class="input-group col-md-3">
									     <div class="input-group-addon">Status</div>
									      <%=ComboUtil.createStrComboBoxAutoWithAttribute("selStatusFlag", statusList, selStatusFlag, "form-control", "", false, false, "", "") %>
									 </div>
									 
						        	 <div class="input-group col-md-3">
									     <div class="input-group-addon">Role</div>
									     <%=ComboUtil.createStrComboBoxAutoWithAttribute("selRoleId", roleList, selRoleId, "form-control", "", true, true, "", "") %>
									 </div>
									 
						        	 <div class="input-group col-md-3">
									     <div class="input-group-addon">District</div>
									     <div class="form-control" id="districtDivID">
									     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selDistrict", districtList, selDistrict, "", "", true, true, "All", "") %>
									     </div>
									 </div>
								</div>
								<div class="form-inline" style="margin-top:20px;">
																	 
						        	 <div class="input-group col-md-3">
									     <div class="input-group-addon">Camp</div>
									     <div class="form-control" id="campDivID">
									     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selCampId", campList, selCampId, "", "", true, true, "All", "") %>
									     </div>
									 </div>
									 
						        	 <div class="input-group col-md-3">
									     <div class="input-group-addon">Mandal</div>
									     <div class="form-control" id="mandalDivID">
									     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("selMandal", mandalList, selMandal, "", "", true, true, "All", "") %>
									     </div>
									 </div>
									 
						        	 <div class="input-group">
						        	 	<span class="badge badge-primary">OR</span>
						        	 </div>
						        	 
						        	 <div class="input-group">
									     <div class="input-group-addon">Username</div>
									     <input type="text" class="form-control" id="selUsername" name="selUsername" value="<%=selUsername.trim()%>" maxlength="15" autocomplete="off">
									 </div>
									 <button type="button" class="btn btn-primary" id="searchBtnId">GO</button>
							  </div>  
						</form>
				</div>
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
								    if(userLoginDataList!=null && userLoginDataList.size()>0) 
								    {
								    %> 
								 	<div class="row" style="margin-top:10px;"> 
										<table id="resultdata"  class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid"  style="font-size: 12px;"> 
									    <thead>
									      <tr>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">View/Edit</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">S.No.</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Username</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">User Id</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Person Name</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Role</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">District</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Camp</th> 
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Mandal</th> 
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Is Active</th> 
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">User Status</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Lock Status</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Created By</th>
										       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Created Date</th>
										  </tr> 
										 </thead>
	    								<tbody>
	    									<%
	    									for(int i=0;i<userLoginDataList.size();i++)
	    									{
	    										dataList = (HashMap)userLoginDataList.get(i);
	    										%> 
											      <tr>
												       <td style="text-align:center; vertical-align: middle"><button type="button" class="btn btn-primary view_edit_button" value="<%=dataList.get("user_name")%>" >View/Edit</button></td> 
												       <td style="text-align:center; vertical-align: middle"><%=i+1%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("user_name")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("user_id")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("person_name")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("role_name")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("district_name")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("camp_name")%></td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("mandal_name")%></td> 
												       <td style="text-align:center; vertical-align: middle">
												       		<%
												       		if(dataList.get("is_active").toString().equalsIgnoreCase("Y"))
												       		{
												       		%>
												       			 <span class="glyphicon glyphicon-ok-sign" style="color: green;font-size:18px;"></span>
												       		<%
												       		}
												       		else if(dataList.get("is_active").toString().equalsIgnoreCase("N"))
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
												       	</td> 
												       <td style="text-align:center; vertical-align: middle">
												       
												       		<%
												       		if(dataList.get("user_status").toString().equalsIgnoreCase("Active"))
												       		{
												       		%>
												       			 <span class="glyphicon glyphicon-ok-sign" style="color: green;font-size:18px;"></span>
												       		<%
												       		}
												       		else if(dataList.get("user_status").toString().equalsIgnoreCase("Inactive"))
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
												        </td> 
												       <td style="text-align:center; vertical-align: middle">
												       
												       		<%
												       		if(dataList.get("lock_status").toString().equalsIgnoreCase("Y"))
												       		{
												       		%>
												       			 <span class="fa fa-lock fa-2x" style="color:red;"></span>
												       		<%
												       		}
												       		else if(dataList.get("lock_status").toString().equalsIgnoreCase("N"))
												       		{
												       		%>
												       			 <span class="fa fa-unlock-alt fa-2x" style="color:green;"></span>
												       		<%
												       		} 
												       		%>
												        </td> 
												       <td style="text-align:left; vertical-align: middle"><%=dataList.get("created_by")%></td> 
												       <td style="text-align:center; vertical-align: middle"><%=dataList.get("created_date")%></td> 
												  </tr> 
	    										<%
	    									}
	    									%>
	    								</tbody>
	    								</table>
    								</div>
									<%
								    }
								%> 
	        </div>
		 </div>
	</div>
</div> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>   
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/scripts/dataTables.buttons.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
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
		}
	}

	$( document ).ready(function() 
	{

		var userNameRegex =  /^\S+[a-zA-Z0-9]\w[\/|\_]?\w[\/|\_]?\w/; 
		 
				$('#selRoleId').change(function()
		  		{	 
		  			$("#selDistrict").html(" <%=ComboUtil.createStrComboBoxAuto("selDistrict", new ArrayList(), "", "", "", true, true, "All")%>");  
		  			$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "All")%>");  
					$("#mandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "All")%>"); 
			  		
		  			postRequest("<%=request.getContextPath()%>/ajax.do?action=loaddistbyroleid&selroleid="+ $('#selRoleId').val()+"&randomid="+Math.random(),"districtDivID");
		  			
		  		});

				
				$('body').on('change','#selDistrict',function()
			    		{	 
							$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "All")%>");  
							$("#mandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "All")%>");  
				  			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadcampbyroleiddistid&selroleid="+ $('#selRoleId').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"campDivID");
				  			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmandalbyroleiddistid&selroleid="+ $('#selRoleId').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"mandalDivID");
			  			 	
		  			 	});
				

				$('#selUsername').on("blur",function()
			    		{ 
						 
							if(($(this).val()).length!=0 && userNameRegex.test($(this).val())==false)
							{  
								alert("Please enter vaild username");
								$(this).val("");
								$(this).focus(); 
							}
							else if(($(this).val()).length!=0 )
							{		
								$(this).val(SpaceReplace($(this).val()));
								
								$('#selRoleId').val("-1");
								$("#selDistrict").html(" <%=ComboUtil.createStrComboBoxAuto("selDistrict", new ArrayList(), "", "", "", true, true, "All")%>");  
					  			$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "All")%>");  
								$("#mandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "All")%>"); 
							}
			    		});
				
				$("#searchBtnId").click(function()
						{ 
							 
								 if((($('#selUsername').val()).length>=5 && userNameRegex.test($('#selUsername').val())==true) || $('#selRoleId').val()!="-1")
								{
									document.userloginFormId.target="_self";
									document.userloginFormId.action="<%=request.getContextPath()%>/userloginaction.do?action=loaduserlogindetailforsearch&randomnum="+Math.random();
									document.userloginFormId.submit();
								}
								else
								{
									alert("Please select the options or enter minimum 5 characters of username");
									return false;
								}
					
						});
				
				$(".view_edit_button").click(function()
				{
					$('#selRoleId').val("-1");
					$('#selUsername').val($(this).val());
					document.userloginFormId.target="_self";
					document.userloginFormId.action="<%=request.getContextPath()%>/userloginaction.do?action=loaduserdetailsbyusername&randomnum="+Math.random();
					document.userloginFormId.submit();
				});
			 
				<%
			    	if(userLoginDataList!=null && userLoginDataList.size()>0)  
				    {
				    %>
				    
				    var table = $('#resultdata').DataTable( 
				    {
				        scrollY:        "300px",
				        scrollX:        true,
				        scrollCollapse: true,
				        paging:         false, 
				        fixedColumns:   true,
				        lengthChange: false, 
				        orderable:false,
				        fixedColumns:   
				        {
				            leftColumns:3
				        }
				        
				    } ); 
				    

					 table.buttons().container().appendTo( '#resultdata_wrapper .col-sm-6:eq(0)' ); 
				    
					<%
				    } 
				 %>  	
				
				
				 
	});

</script>
</body>
</html>