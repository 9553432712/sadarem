<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>:: Issue Tracking System ::</title>
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	  
    
	
	<style>  
   .jqueryslidemenu ul li a:link,.jqueryslidemenu ul li a:visited {
	color:#000000;
	z-index:1000;
	height:30px!important;
}
	  .loader {
		position: fixed;
		left: 0px;
		top: 0px;
		width: 100%;
		height: 100%;
		z-index: 9999;
		background: url('images/loading2.gif') 50% 50% no-repeat rgb(249,249,249);
	}
	.iframe{
    overflow:hidden;
}
   </style> 
 
  <script type="text/javascript">
		$(window).load(function()
		{
			$(".loader").fadeOut("slow");
		});
  </script>

	<%
	try
	{ 
	
	  String selIssueType = "",sadaremId="";
	  
	  selIssueType 			= CommonUtility.checkNullObj(request.getAttribute("selIssueType"));
	  sadaremId 			= CommonUtility.checkNullObj(request.getAttribute("sadaremId"));
	  String distId 		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	  String aliveStatus	= CommonUtility.checkNullObj(request.getAttribute("aliveStatus"));
	  String proofStatus	= CommonUtility.checkNullObj(request.getAttribute("proofStatus"));
	  String reqType 		=  CommonUtility.checkNullObj(request.getAttribute("reqType"));
	  String statusMsg 		= CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
	  
	  ArrayList issuependingRecordList = (ArrayList)request.getAttribute("pendingIssueDtlsList");
	  ArrayList issueTypeList 		   = (ArrayList)request.getAttribute("issueTypeList");
	  
	 %>
  
<script>

$(document).ready( function()
		{
	 
	$('#issueData').dataTable
	   ({
		   "iDisplayLength": 10, 
		   paging : false,
		   "scrollY" : 200
	    });
			
				 $("#raiseissueButID").click(function ()
							{
					  if ($('#reqtype').val()==-1)
		                {
		                    alert("Please Select Status type");
		                    $('#issueType').focus();
		                    return false;
		                }
					 
					 else{
					 
					  
					 document.form_issue.target="_self";
						document.form_issue.action="<%=request.getContextPath()%>/IssueRequestlist.do";
						document.form_issue.submit();
			     	  
			 	/* }} */}
			 
  	  
	     	  
			}); 

				
		});

function showData()
{ 
	var data = document.getElementById('statusMsg').value;
	if(!data=='')
		{
		window.location.reload();
		  
		}
}


</script>
<style>
html {
    font-family: serif;
    }
.firstline{
background-color:none;
}
.secondline{
background-color:#e2ebf4;
} 
</style>

</head>
<body onload="showData();">
 
 <div class="loader"></div>
<%--  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <!-- <th style="text-align:center; background-color: #2E9BD8; color:#fff; text-align="center" valign="middle">Issue Tracking System </th> -->
					   
					   
					    <td height="38" width="100%" align="left" valign="top" class="heading_new" style="background-image:url(images/heading_background_1.png);  background-repeat:no-repeat;padding-left:24px; "><font color="white"><b>My Request List</b></font> </td>
					  </tr>
	  				<tr>
  					<td>
  							<form name="form_issue"  method="post">
  							<input type="hidden" name ="statusMsg" id="statusMsg" value='<%=statusMsg %>'>
  							<input type="hidden" name="mode" id="mode" value='myReqList'>
  									   <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					     		           <table  cellspacing="0" width="100%" border="0px">
									       <tr><td width="35%" style="text-align:right; "><b> Issue Type<font color='red'>*</font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      </b> </td><td width="20%">
					                              <%=ComboUtil.createStrComboBoxAuto("issueType",issueTypeList,selIssueType, "form-control","",true,true,"")%>
					                              </td>
					                            
					                              <td align="center" width="60%">
					                              
					                            <button id="raiseissueButID" class="btn icon-btn-primary btn-primary" type="button">
					                           		 <strong><font style="color:white">Submit</font></strong>
					                           </button>
					                      
					                              </td>
										    
										   
									           
									    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
					    	
						</form>
  					</td>
  				</tr>
  				</table>  --%> 
<div style="height:450px;">
 <div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
						        <div class="panel-heading"  style="cursor: pointer;">
									             <h4 class="panel-title">
																My Request List
												 </h4>
						        </div>
				        <div  >
				              <div class="panel-body">
							              <form class="form-inline" id="form_issue" name="form_issue" method="post" >
									             	<input type="hidden" name ="statusMsg" id="statusMsg" value='<%=statusMsg %>'>
  							                         <input type="hidden" name="mode" id="mode" value='myReqList'>
										            
										            
										            <div class=" col-xs-4" >
													        
												             <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Issue Type</div>
															    <%=ComboUtil.createStrComboBoxAuto("issueType",issueTypeList,selIssueType, "form-control","",true,false,"")%>
															 </div></div>
														
													      <div class=" col-xs-3" >
														  <button type="button" id="raiseissueButID" class="btn btn-success"><b>Submit</b></button>
										</div> </form>
							            
				                 </div>
				        </div>
				    </div>
				  </div>  
	



 <%if(issuependingRecordList!=null && issuependingRecordList.size()>0){ %>
  <table id="issueData" class="table table-striped table-bordered" width="100%">
    <thead>
      <tr>
        <td style="background-color:#337ab7;padding:5px; color:#fff;" align="center">S.No.</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue ID</td>
        <%if("-1".equals(selIssueType))
		{ %> 
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue Type</td>
        <%} %> 
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Village Name</td>
 <%--        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%if(reqType.equals("R")){ %>Rejected On<%}else if(reqType.equals("A")){  %>Approved On<%}else{ %>Pending Since<%} %></td> --%>
       <td style="background-color:#337ab7;padding:5px;color:#fff;  width:30%" align="center">Status</td>
      </tr>
    </thead>
    <tbody>
    <%
	
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     String style="";
     for(loopCount=0;loopCount<issuependingRecordList.size();loopCount++)
	 { 
    	 innerList = (ArrayList)issuependingRecordList.get(loopCount);
    	  
	 
	 if(loopCount%2==0)
	 {
		 style="firstline";
	 }else
	 {
		 style="secondline";
	 }
	 %>
     <tr>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
   <td class="<%=style%>" style="border:#337ab7 1px solid;" >
     <a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/IssueRequestlist.do?mode=getrequestListValues&requestid=<%=innerList.get(0)%>&sadaremid=<%=innerList.get(1)%>&reqtype=<%=reqType %>&randomid=<%=Math.random()%>">
			<font size="3" ><b><%=innerList.get(0)%></b></font>
			</a></td>
       <%if("-1".equals(selIssueType))
		{ %> 
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(5) %></td>
        <%} %> 
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(1) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(2) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(3) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;  width:30%"><%=innerList.get(4) %></td>
        <%-- <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center"><%=innerList.get(5) %> <%if(reqType.equals("P")){ %> day(s)<%} %></td> --%>
        
         
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
      <table  width="100%" class="table table-hover table-bordered table-responsive ">
    <thead>
      <tr>
        <td style="background-color:#337ab7;padding:5px; color:#fff;" align="center">S.No.</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue ID</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>
       <!--  <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District Name</td> -->
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Village Name</td>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Status</td>
       <%--  <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%if(reqType.equals("R")){ %>Rejected On<%}else if(reqType.equals("A")){  %>Approved On<%}else{ %>Pending Since<%} %></td> --%>
      </tr>
    </thead>
     <tr>
     <td height="15" align="center" valign="middle" class="Row" colspan="18">No pending requests to display.</td> 
	</tr>
	</table>
	 <%
	 }
     %>
    
 </div>
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
					$(document).ready(function()
							{
						 
						//Examples of how to assign the Colorbox event to elements
						$(".iframe").colorbox(
								{
								iframe:true, width:"70%", height:"48%",
								 
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
</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>