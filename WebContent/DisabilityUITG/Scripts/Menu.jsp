<%@ page import="com.tcs.sadarem.util.ComboUtil,java.util.ArrayList,com.tcs.sadarem.util.MenuUtil,org.bf.disability.Constants.CommonConstants,com.tcs.sadarem.common.DAO.CommonDAO,com.tcs.sadarem.common.DAO.CommonDAOImpl,com.tcs.sadarem.util.CommonUtility" %>

<!-- <script LANGUAGE="JavaScript" SRC="./DisabilityUITG/js/jsmenu/lw_layers.js"></script>
<script LANGUAGE="JavaScript" SRC="./DisabilityUITG/js/jsmenu/menu.js"></script> -->
	 <script src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	 <script src="<%=request.getContextPath()%>/scripts/jqueryslidemenu.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/top_menu.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<!-- style={margin:0px} -->
<% 
try
{
ArrayList SALogRoleList		= (ArrayList)session.getAttribute("SALogRoleList");
ArrayList SALogDistrictList = (ArrayList)session.getAttribute("SALogDistrictList");
ArrayList SALogCampList		= (ArrayList)session.getAttribute("SALogCampList");
ArrayList SALogMandalList	= (ArrayList)session.getAttribute("SALogMandalList");
 
String SALogCaptchaImageCode = CommonUtility.checkNullObj(session.getAttribute("SALogCaptchaImageCode"));


//System.out.println("SALogCaptchaImageCode : "+SALogCaptchaImageCode+"\n SESSION_CAPTCHA : "+session.getAttribute("SALogCaptchaImageCode"));


boolean SALogCampCombStatus = true;
boolean SALogMandCombStatus = true;

if(SALogCampList!=null && SALogCampList.size()==1)
{
	SALogCampCombStatus = false;
}

if(SALogMandalList!=null && SALogMandalList.size()==1)
{
	SALogMandCombStatus = false;
}

%>
<style>

     	
     	#processlayer
			{
				width: 300px;
				height: 50px;
				background:#ECF1EF;
				border: red dotted 5px;
				text-align: center;
				position: fixed;
				margin-right: -100px;
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
     	
</style>
<script>
    document.onkeydown = showDown;

    function showDown(evt) {
        evt = (evt)? evt : ((event)? event : null);
        if (evt) {
           
            if (event.keyCode == 116) {
                // When F5 is pressed
                cancelKey(evt);
            }
            else if (event.keyCode == 122) {
                // When F11 is pressed
                cancelKey(evt);
            }else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
                // When ctrl is pressed with R or N
                cancelKey(evt);
            }
            else if (event.altKey && event.keyCode==37 ) {
                // stop Alt left cursor
                return false;
            }
        }
    }

    function cancelKey(evt) {
        if (evt.preventDefault) {
            evt.preventDefault();
            return false;
        }
        else {
            evt.keyCode = 0;
            evt.returnValue = false;
        }
    }
    function fun_redirect(id)
	{
	 var url ="";
	 
		if(id=="000")
			{
				url ="<%=request.getContextPath()%>/do?requestType=commonrh&actionVal=logout";					// logout
			}
			else if(id=="100")
			{
				url ="<%=request.getContextPath()%>/do?requestType=commonrh&actionVal=loginhome";				// login home
			}
			else if(id=="101")
			{
				url ="<%=request.getContextPath()%>/do?requestType=commonrh&actionVal=loadchangepwd";			// change password
			}
			else if(id!="" && id.length>3)
			{
				url="<%=request.getContextPath()%>/"+id;
				
				/*  if(url.includes("?"))
					{
					url=url+"&urlchesck=urlcheck";
					}
				else
					{
					url=url+"?urlcheck=urlcheck";
					}  */
				// alert(url);
			} 
		if(url!="")
		{
			location.replace(url);
			/*Screen Locking Started */
				$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
			    $('#processlayer').css({"display": "block","z-index":"110000"});
			/*Screen Locking Ended */
		    
		}
	}


</script>


<div id="noprint">
    <%
           /*      Vector services = (Vector) session.getAttribute("services");
                if (services != null) {
                    out.println("<script LANGUAGE=\"JavaScript\">");
                    for (int i = 0; i < services.size(); i++) {
                        String servicedesc[] = (String[]) services.elementAt(i);
                        String serviceid = servicedesc[0];
                        String parentid = servicedesc[1];
                        String targeturl = servicedesc[2];
                        String menuitemname = servicedesc[3];

                        out.println("AddMenuItem (" + serviceid + ", " + parentid + ", \"" + targeturl + "\", \"" + menuitemname + "\", \"\");");
                    }
                } else {
                    out.println("could not get services");
                }
                out.println("</script>"); */
    %>
   <!--  <script LANGUAGE="JavaScript">
        DrawMenu();
    </script> -->
    
    <%
 CommonDAO comObj = new CommonDAOImpl();
    String myroleId		   = CommonUtility.checkNullObj(session.getAttribute("roleId"));
    String defaultPassword = CommonUtility.checkNullObj(session.getAttribute("defaultPassword"));
    ArrayList menuList = new ArrayList();
    
    
    
    if(myroleId!=null && !myroleId.equals("") && !defaultPassword.equalsIgnoreCase("Y"))
    {
   		menuList = (ArrayList)comObj.getMenuList(Integer.parseInt(myroleId));
    }
%>  

	 	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/but_lft.png" width="9" height="43" /></td>
	        <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/bg_but.png); background-repeat:repeat-x;">
	        	<%
	        	if(defaultPassword.equalsIgnoreCase("Y"))
	        	{
	        	%>
	        		<div id="myslidemenu" class="jqueryslidemenu">
	        		<ul>
	        			<li>
	        					<a href="#" onclick="fun_redirect('changepassword.do')">Change Password</a>
	        			</li>
	        			<li>
	        				<a href="#" onclick="fun_redirect('logout.do')">Logout</a>
	        			</li>
	        		</ul>
	        		</div>
	        	<%
	        	}
	        	else
	        	{
	        	%>
					<%=MenuUtil.createStrHeaderList(menuList,"fun_redirect",true) %>
				<%
	        	}
				%>
			</td>
	        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/but_rgt.png" width="9" height="43" /></td>
	        <%
	        if(!defaultPassword.equalsIgnoreCase("Y") && !CommonUtility.checkNullObj(session.getAttribute(CommonConstants.SUPER_ADMIN_SESSION_ROLE_ID)).equals(""))
	        {
	        %>
			<td>
				 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#SuperAdminControlBtn" style="background-color:#29bfaa; color:#FFFFFF;font-weight:bold;">Super Admin</button>
			</td>
			<%
	        }
			%>
	      </tr>
	    </table> 
<!--   <div class="alert alert-info"> 
    <marquee>
    	<span style="padding-left: 20px;"><strong>Info!</strong> Soon we are going to scroll the updates here</span>  
    </marquee>
     
</div>	  --> 

</div>

<br/>
<input type="hidden" name="logintime" value=<%=(String) session.getAttribute("logintime")%> >
<!-- Screen Lock Started Here -->
	<div id="processlayer">
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->
 <%
	        if(!defaultPassword.equalsIgnoreCase("Y") && !CommonUtility.checkNullObj(session.getAttribute(CommonConstants.SUPER_ADMIN_SESSION_ROLE_ID)).equals(""))
	        {
	        %>

<div class="modal fade" id="SuperAdminControlBtn" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" style="display: none;">
      <div class="modal-dialog" style=" margin-left: auto; margin-right: auto; width:50% !important">
        <div class="modal-content">
          <div class="modal-header" style="background-color:#dff0d8;">
            <button type="button" id="xSALogCrossBtn" class="close" data-dismiss="modal" aria-label="Close" style="color:red;" title="Close"><span aria-hidden="true" class="glyphicon glyphicon-remove-circle"></span></button>
            <h4 class="modal-title"><b>Super Admin Control Panel</b></h4>
          </div>
          <div class="modal-body" style="background-color:  #fcf8e3;">   
		   		<div class="form-group" style="padding-top:5px;"> 
          			<span id="SALogDisplayMsgSpan" style="text-align:center;"></span>
                      <form name="supAdminControlFormId" id="supAdminControlFormId" method="post">
                      	<input type="hidden" id="SALogStatus" name="SALogStatus"> 
				        	 <div class="input-group" style="margin-top:20px;">
							     <div class="input-group-addon">Role</div>
							     <%=ComboUtil.createStrComboBoxAutoWithAttribute("SALogRoleId", SALogRoleList, CommonUtility.checkNullObj(session.getAttribute("roleId")), "form-control", "", true, true, "", "") %>
							 </div>
							 
				        	 <div class="input-group" style="margin-top:20px;">
							     <div class="input-group-addon">District</div>
							     <div id="SUPLogDistrictDivID">
							     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("SALogDistrictId", SALogDistrictList, CommonUtility.checkNullObj(session.getAttribute("districtId")), "form-control", "", true, true, "All", "") %>
							     </div>
							 </div>  
															 
				        	 <div class="input-group" style="margin-top:20px;">
							     <div class="input-group-addon">Camp</div>
							     <div id="SUPLogCampDivID">
							     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("SALogCampId", SALogCampList, CommonUtility.checkNullObj(session.getAttribute("campId")), "form-control", "", SALogMandCombStatus, true, "All", "") %>
							     </div>
							 </div>
							 
				        	 <div class="input-group" style="margin-top:20px;">
							     <div class="input-group-addon">Mandal</div>
							     <div id="SUPLogMandalDivID">
							     	<%=ComboUtil.createStrComboBoxAutoWithAttribute("SALogMandal", SALogMandalList, CommonUtility.checkNullObj(session.getAttribute("mandalId")), "form-control", "", SALogMandCombStatus, true, "All", "") %>
							     </div>
							 </div>
							  
							<div class="input-group" style="margin-top:20px;">
								<div class="input-group-addon" ondragstart="return false" onselectstart="return false" style="cursor:no-drop;">
									<i class="glyphicon glyphicon-barcode"></i> <img src="data:image/png;base64,<%=CommonUtility.CapchaColorImage(SALogCaptchaImageCode)%>"/>
								</div>
								<input type="text" class="form-control" placeholder="Captcha" id="SALogCapText" name="SALogCapText" onmouseover="this.title='Captcha'" required  style="height: 37px;" maxlength="5" autocomplete="off">
							</div> 
					</form>
				</div> 
          </div> 
          <div class="modal-footer" style="text-align:center;background-color:#dff0d8;">
          		<button type="button" class="btn btn-danger" id="SALogCancelBtnID" style="float:left;" >Cancel</button>
          		<button type="button" class="btn btn-primary SALogActionClass" id="SALogUpdateBtnID" value="update">Update</button>
          		<button type="button" class="btn btn-success SALogActionClass" id="SALogMyRoleBtnID" value="reset" style="float:right;">My Role</button>
          </div>
        </div>
      </div>
    </div> 

<script type="text/javascript">
	function SUPLog_postRequest(strURL,textID) 
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
	    		SUPLog_updatepage("<center><img src='<%=request.getContextPath()%>/images/loading.gif' width='15' height='15'></center>",textID);
	        }
		    else if(xmlHttp.readyState == 2)
		    {
		    	SUPLog_updatepage('Wait..',textID);
		    }
		    else if(xmlHttp.readyState == 3)
		    {
		    	SUPLog_updatepage('Please Wait..',textID);
		    }
		    else if (xmlHttp.readyState == 4) 
	        {
		    	var errorCode = xmlHttp.getResponseHeader('errorCode');
		    
		    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
		    	{
		    		SUPLog_updatepage(xmlHttp.responseText,textID); 
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
	
	function SUPLog_updatepage(msg,id)
	{		
		if(msg!="" && msg!="null")
		{
			document.getElementById(id).innerHTML=msg; 
		}
		
		 
	}
	
 
	$('#SALogRoleId').change(function()
	{	 
		$("#SUPLogDistrictDivID").html(" <%=ComboUtil.createStrComboBoxAuto("SALogDistrictId", new ArrayList(), "", "form-control", "", true, true, "-Select-")%>");  
		$("#SUPLogCampDivID").html(" <%=ComboUtil.createStrComboBoxAuto("SALogCampId", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
		$("#SUPLogMandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("SALogMandal", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>"); 
			
		SUPLog_postRequest("<%=request.getContextPath()%>/userloginaction.do?action=loadsalogdistrictcombobyroleid&SALogRoleId="+ $('#SALogRoleId').val()+"&randomid="+Math.random(),"SUPLogDistrictDivID");
		
	});

	$('body').on('change','#SALogDistrictId',function()
    {	 
		$("#campDivID").html(" <%=ComboUtil.createStrComboBoxAuto("SALogCampId", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
		$("#SUPLogMandalDivID").html(" <%=ComboUtil.createStrComboBoxAuto("SALogMandal", new ArrayList(), "", "form-control", "", true, true, "-NA-")%>");  
		SUPLog_postRequest("<%=request.getContextPath()%>/userloginaction.do?action=loadsalogcampbyroleiddistid&SALogRoleId="+ $('#SALogRoleId').val()+"&SALogDistrictId="+ $('#SALogDistrictId').val()+"&randomid="+Math.random(),"SUPLogCampDivID");
		SUPLog_postRequest("<%=request.getContextPath()%>/userloginaction.do?action=loadsalogmandalbyroleiddistid&SALogRoleId="+ $('#SALogRoleId').val()+"&SALogDistrictId="+ $('#SALogDistrictId').val()+"&randomid="+Math.random(),"SUPLogMandalDivID");
	});
	
	
	$('#xSALogCrossBtn').click(function () 
	{
		window.location="<%=request.getContextPath()%>/login.do?mode=reloadhomepage&randomid"+Math.random();
  	});
	
	$('#SALogCancelBtnID').click(function () 
	{
		window.location="<%=request.getContextPath()%>/login.do?mode=reloadhomepage&randomid"+Math.random();
  	});
	
	$('.SALogActionClass').click(function(e)
	 		{
		  
			var dist_leng				= $('select#SALogDistrictId option').length;
			var camp_leng				= $('select#SALogCampId option').length;
			var mand_leng				= $('select#SALogMandal option').length;

		
				var msgDiv = $('#SALogDisplayMsgSpan');
				
				msgDiv.html("");
					
					if($('#SALogRoleId').val()=="-1")
					{
						msgDiv.html("<font color='red' size='2'><b>Please Select Role</b></font>");
						$('#SALogRoleId').focus();
						return false;
					}
					else if(dist_leng>1 && $('#SALogDistrictId').val()=="-1")
					{ 
						msgDiv.html("<font color='red' size='2'><b>Please Select District</b></font>");
						$('#SALogDistrictId').focus();
						return false;
					}
					else if(camp_leng>1 && $('#SALogCampId').val()=="-1")
					{
						msgDiv.html("<font color='red' size='2'><b>Please Select Camp</b></font>");
						$('#SALogCampId').focus();
						return false;
					}
					else if(mand_leng>1 && $('#SALogMandal').val()=="-1")
					{
						msgDiv.html("<font color='red' size='2'><b>Please Select Mandal</b></font>");
						$('#SALogMandal').focus();
						return false;
					}
					else if($('#SALogCapText').val()=="")
					{
						msgDiv.html("<font color='red' size='2'><b>Please enter Captcha</b></font>");
						$('#SALogCapText').focus();
						return false;
					}
					else
					{
						$("#SALogStatus").val($(this).val());


						$("#SALogUpdateBtnID").hide();
						$("#SALogMyRoleBtnID").hide();
						
						$.ajax({
							  type: 'POST',
						  	  url: '<%=request.getContextPath()%>/userloginaction.do?action=reloadsuperadmincontrol&randomid='+Math.random(),
							  data: $('#supAdminControlFormId').serialize(),
							  success: function (data, textStatus) 
							  { 
								  msgDiv.html(data);
							  },
							  error: function(xhr, status, e)
							  {
								 alert("error");
							    //alert(status, e);
							  }
							});
					}
	 		}); 
</script>

<%
}
}
catch(Exception e)
{
	 e.printStackTrace();  
}
%>