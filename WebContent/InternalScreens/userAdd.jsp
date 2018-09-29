<%@page import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
	
    <style>
    		
    		table.fixed { table-layout:fixed; }
			table.fixed td { overflow: hidden; }
    		
    		
    		#tabl{
				    border-radius:50px;
				    -moz-border-radius:50px;
				    -webkit-border-radius:50px;
				    
				     border-radius: 25px;
					    background-position: left top;
					    background-repeat: repeat;
					    padding: 40px; 
					    width: 600px;
					    height: 150px; 
				}
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
			
			ul li
			{
			    line-height: 14px !important;
			}

/* 	 Process Layer Ended  */  
    </style>
    
    
     
 </head>

 <body>
    
<%
try
{              
    
    ArrayList districtList = (ArrayList)request.getAttribute("districtList");
    ArrayList rolesToCreateLoginList = (ArrayList)request.getAttribute("rolesToCreateLoginList"); 

    String alertMsg 	=  CommonUtility.checkNullObj(request.getAttribute("resultMSG"));
    String alertMsgCSS 	=  CommonUtility.checkNullObj(request.getAttribute("alertMsgCSS"));
    if(alertMsgCSS.equals(""))
    {
    	alertMsgCSS ="alert-info";
    }
     
%>
    
    	<div id="processlayer" >
			<font color="blue" size="2">Processing Please Wait...</font><br/>
			<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
		</div>
		
		<div id="blocklayer">
		</div>
		  <%
		    if(!alertMsg.equals("")) 
		    {
		    %>
		    <div class="col-md-8" > 
			    <div class="alert <%=alertMsgCSS %> fade in alert-dismissable">
				    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
				    <strong style="color:black"><%=alertMsg%></strong>
				</div>
			</div>
			<%
		    }
		%>    
		<form name="useraddform" id="useraddform" method="post">
	    
	    <input type="hidden" id="mode" name="mode" value="userAdd">
	    	
	    				
	    				
	    				
	    				<%-- <div class="col-md-5 col-md-offset-3">
				            		<div class="panel panel-primary">				            		 
			      						<div class="panel-heading" style="text-align: center;"><b>  Login Creation </b></div>
									    <div class="panel-body" style="text-align: left;">
									    	<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<%=ComboUtil.createStrComboBoxAuto("roleid",rolesToCreateLoginList,"","form-control","",true,true,"")%>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<%=ComboUtil.createStrComboBoxAuto("selDistrict",districtList,"","form-control","",true,true,"")%>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<div class="input-group">
																		      <div id="mandallist">
																		      		<%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(),"","form-control mycomboStyle","",true,true,"NA")%>
																		      </div>
														</div>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<div class="input-group">
																		      <div id="camplist">
																		      		<%=ComboUtil.createStrComboBoxAuto("campId", new ArrayList(),"","form-control mycomboStyle","",true,true,"NA")%>
																		      </div>
														</div>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<input type="text" name="EmpID" id="EmpID" class="form-control" maxlength="15" autocomplete="off"  onkeypress="return fun_employeeid(event);" onblur="this.value=(this.value).toUpperCase()" />
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<input type="text" name="EmpName" id="EmpName" class="form-control" maxlength="50" autocomplete="off"  	  onBlur="this.value = SpaceReplace(this.value);"/>
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<input type="text"  id="mobileno" name="mobileno" class="form-control" onkeypress="return NumbersOnly(event);"  autocomplete="off" maxlength="10">
									    			</div>
									    		</div>														
											</div>
											<div class="form-group">
									    		<div class="row">
									    			<div class="col-md-3">
									    				<label for="usr">  Role </label>
									    				
									    			</div>
									    			<div class="col-md-4">
									    				<input type="text"  id="emailid" name="emailid" class="form-control" OnfocusOut="EmailCheck(this)" autocomplete="off" maxlength="100">
									    			</div>
									    		</div>														
											</div>
											
										</div>
									</div>
								</div> --%>
							
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    				
	    		
	      <table id="tabl" align="center" class="table table-striped table-bordered" class="fixed">
	      	<col width="50px" />
	      	<col width="50px" />
                 <tr>
                    <th colspan="2" style="background-color:#337ab7;padding:5px;color:#fff; text-align: center; vertical-align: middle;">
                          Login Creation
                     </th>
                 </tr>
                 <tr> 
                    <td><font color="red"><b>*</b></font> Role</td>  
                    <td>
                   		<%=ComboUtil.createStrComboBoxAuto("roleid",rolesToCreateLoginList,"","form-control","",true,true,"")%>
                    </td>
                </tr>
                <tr> 
                    <td><font color="red"><b>*</b></font> District</td>  
                    <td>
                    	<div class="input-group">
										      <div id="distlist">
										      		<%=ComboUtil.createStrComboBoxAuto("selDistrict", districtList ,"","form-control mycomboStyle","",true,true,"NA")%>
										      </div>
						</div>
                   		
                    </td>
                </tr>
                <tr  > 
                    <td><font color="red"><b>*</b></font>Mandal</td>  
                    <td>
                   		<div class="input-group">
										      <div id="mandallist">
										      		<%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(),"","form-control mycomboStyle","",true,true,"NA")%>
										      </div>
						</div>
                    </td>
                </tr>
                <tr id="camprow"  > 
                    <td><font color="red"><b>*</b></font> CAMP Name</td>  
                    <td>
                   		<div class="input-group">
										      <div id="camplist">
										      		<%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(),"","form-control mycomboStyle","",true,true,"NA")%>
										      </div>
						</div>
                    </td>
                </tr>
                <tr>
                    <td><font color="red"><b>*</b></font> Employee ID</td>
                    <td>
                    	<input type="text" name="EmpID" id="EmpID" class="form-control" maxlength="15" autocomplete="off"  onkeypress="return fun_employeeid(event);" onblur="this.value=(this.value).toUpperCase()" />
					</td>
                </tr>
                <tr>
                    <td><font color="red"><b>*</b></font> Employee Name</td>
                    <td>
                    	<input type="text" name="EmpName" id="EmpName" class="form-control" maxlength="50" autocomplete="off"  
                    	
                                  									  onBlur="this.value = SpaceReplace(this.value);"/>
					</td>
                </tr>
                <tr>
                    <td><font color="red"><b>*</b></font> Mobile No.</td>
                    <td>
                    	<input type="text"  id="mobileno" name="mobileno" class="form-control" onkeypress="return NumbersOnly(event);"  autocomplete="off" maxlength="10">                    	
					</td>
                </tr>
                <tr>
                    <td> eMail ID</td>
                    <td>
                    	<input type="text"  id="emailid" name="emailid" class="form-control" OnfocusOut="EmailCheck(this)" autocomplete="off" maxlength="100">
					</td>
                </tr>  
                <tr>
                     <th colspan="2" class="firstline" style="text-align: center;width:100%"> 
						  <button type="button" id="formSubmit" class="btn btn-success"><b>Submit</b></button>
					 </th>
                </tr>

            </table>  
            <div class="empinf" align="center" style="align=center;" id="empinf">
                                    	<div class="row">
                                    			<table id="disabTable" style=" background-repeat:no-repeat; width:80%;margin:1;" cellpadding="0" cellspacing="0" border="1"> 
	                                    			<tbody>
	                                    				<tr> 
	                                    					<th style="text-align: center;"><b>S.No.</b></th>
	                                    					<th style="text-align: center;"><b>EMP Name</b></th>
	                                    					<th style="text-align: center;"><b>USER ID</b></th>
	                                    					<th style="text-align: center;"><b>Role</b></th>
	                                    					<th style="text-align: center;"><b>District</b></th>
	                                    					<th style="text-align: center;"><b>Mandal</b></th>
	                                    					<th style="text-align: center;"><b>Camp</b></th>
	                                    					<th style="text-align: center;"><b>Is Active</b></th>
	                                    					<th style="text-align: center;"><b>User Status</b></th>
	                                    					<th style="text-align: center;"><b>Created By</b></th>
	                                    					<th style="text-align: center;"><b>Created Date</b></th>	                                    					
	                                    				</tr>
	                                    			</tbody>
                                    			</table>                                    		
                                    	</div>
             </div>
             <br><br><br><br><br>
	</form>	
	
			 
<script type="text/javascript">

function fun_employeeid(e)       
{
	try
	{
		var key = window.event ? e.keyCode:e.which;
	    var keychar = String.fromCharCode(key);   
	    reg = /\d/; 
		if(reg.test(keychar)==false)
		{
			if((parseInt(key)>=65&&parseInt(key)<=90)||(parseInt(key)>=97&&parseInt(key)<=122) || parseInt(key)==47)
			{
				return true;
			}
			else
			{
			return false;
			}
		}
		else
		{
			if(parseInt(key)>=48&& parseInt(key)<=57 && parseInt(key)!=32)
			{
				return true;
			}
			else
			{
			return false;
			}
		}
		
	}
	catch(e)
	{
		alert(e);
	}
    
}   

 function validateMobile()
 {
	 	var pattern = /^[0]?[789]\d{9}$/;
		
		if (pattern.test($('#mobileno').val()) == false)
		{
		  	$('#mobileno').focus();
		  	return false;
		}
		return true;
 }
 
$(document).ready( function()
{
				$('#formSubmit').click(function()
				{									
					try
					{
						
					if($('#roleid').val()==null || $('#roleid').val()=="-1")
					{
						alert("Please select Role");
					  	$('#roleid').focus();
					  	return false;
					}
					else  if( ($('#selDistrict').val()==null || $('#selDistrict').val()=="-1")    && !($('#roleid').val()=="97" || $('#roleid').val()=="98" || $('#roleid').val()=="102" || $('#roleid').val()=="103" )  )
					{
						  	alert("Please select District");
						  	$('#selDistrict').focus();
						  	return false;
					}

					else if( ($('#selMandal').val()==null  ||  $('#selMandal').val()=="-1") && $('#roleid').val()=="24"  )
					{
						alert("Please select Mandal");
					  	$('#selMandal').focus();
					  	return false;
					}
						else if( $('#roleid').val()=="4"  && ( $('#selCampId').val()==null || $('#selCampId').val()=="-1")  )
						{
							alert("Please select Camp Name");
						  	$('#selCampId').focus();
						  	return false;
						}
						else if($('#EmpID').val()==null || $('#EmpID').val()=="")
						{
						  	alert("Please enter EmpID");
						  	$('#EmpID').focus();
						  	return false;
						} 
						else if($('#EmpName').val()==null || $('#EmpName').val()=="")
						{
						  	alert("Please enter Employee Name");
						  	$('#EmpName').focus();
						  	return false;
						}
						else if($('#mobileno').val()==null || ($('#mobileno').val()).length!=10 || !validateMobile())
						{
							
						  	alert("Please enter valid Mobile No.");
						  	$('#mobileno').focus();
						  	return false;
						}
						else if(confirm("Are you sure you want to submit the request?"))
							{ 
									 	document.useraddform.target="_self";
									 	document.useraddform.action="<%=request.getContextPath()%>/createuser.do";
									 	document.useraddform.submit();
									 	
										/*Screen Locking Started */
										$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
									    $('#processlayer').css({"display": "block","z-index":"110000"});
									/*Screen Locking Ended */
							} 
					
					}
					catch(e)
					{
						alert("00000fsdfsdfsd00000000000"+e);
					}
			});
				
							$('body').on('change','#roleid',function()
							{
								try
								{
									
									$("#camplist").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "NA")%>");  
									$("#mandallist").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "NA")%>");
									
									
									
									postRequest("<%=request.getContextPath()%>/ajax.do?action=loaddistbyroleid&selroleid="+ $('#roleid').val()+"&randomid="+Math.random(),"distlist");
									
										postRequest("<%=request.getContextPath()%>/ajax.do?action=loademployeeinflocaction&selCampId="+ $('#selCampId').val()+"&selMandal="+ $('#selMandal').val()+"&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"empinf");
								
									
									
									
								}
								catch(e)
								{
									alert(e);
								}
							});
							
							
							$('body').on('change','#selDistrict',function()
									{
								$("#camplist").html(" <%=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "NA")%>");  
								$("#mandallist").html(" <%=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "NA")%>");
								
										postRequest("<%=request.getContextPath()%>/ajax.do?action=loadcampbyroleiddistid&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"camplist");
						    			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmandalbyroleiddistid&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"mandallist");
						    			postRequest("<%=request.getContextPath()%>/ajax.do?action=loademployeeinflocaction&selCampId="+ $('#selCampId').val()+"&selMandal="+ $('#selMandal').val()+"&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"empinf");
										
									});
							
							
						
								
								$('body').on('change','#selMandal',function()
								{
									try
									{
							    			postRequest("<%=request.getContextPath()%>/ajax.do?action=loademployeeinflocaction&selCampId="+ $('#selCampId').val()+"&selMandal="+ $('#selMandal').val()+"&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"empinf");
						    			
									}
									catch(e)
									{
										alert(e);
									}
								});
						
								$('body').on('change','#selCampId',function()
								{
									try
									{
							    			postRequest("<%=request.getContextPath()%>/ajax.do?action=loademployeeinflocaction&selCampId="+ $('#selCampId').val()+"&selMandal="+ $('#selMandal').val()+"&selroleid="+ $('#roleid').val()+"&selDistrict="+ $('#selDistrict').val()+"&randomid="+Math.random(),"empinf");
						    			
									}
									catch(e)
									{
										alert(e);
									}
								});
						
						
						
						$("#EmpName").blur(function()
						{
							if (/[`^~<>,"'!@#$&*()]/.test($("#EmpName").val() ))
							{    
							    alert("Enter valid Name");
							    $("#EmpName").val("");
							    $("#EmpName").focus();
							    return false;
							}							
							
						});
						
						
						
						
				
				
				 
});

function postRequest(strURL,textID) 
{
	try
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
	catch(e)
	{
		alert(e);
	}
    
	
}

function updatepage(msg,id)
{
	try
	{
		if(msg!="" && msg!="null")
		{
			document.getElementById(id).innerHTML=msg;
			 $( "select" ).each( function( index, element )
			   {
			    	$(this).removeClass('select-optionItem').addClass('form-control mycomboStyle');		// Removing the existing style of combo and adding new form-control class css. 
			    	
			    	$("#village").removeAttr("onchange");												// Removing the onchange action of village combo 
			    	
			    	$('select option:contains("All")').text('-Select-');								// Modifing the option text of combo from All to -Select- 
			    });
		}
	}
	catch(e)
	{
		alert(e);
	}
	
}

</script>


<%
}
catch(Exception e)
{
	e.printStackTrace();
}%>
            


</body>

</html>
