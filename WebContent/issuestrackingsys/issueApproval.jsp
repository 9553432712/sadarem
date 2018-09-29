
<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,com.tcs.sadarem.util.PasswordEncriptDecrypt"language="java" contentType="text/html;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<title>:: Issue Tracking System ::</title>
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
     	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
  
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/teluguname.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
    
	
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
	.panel-heading a:after 
{
    font-family:'Glyphicons Halflings';
    content:"\e114";
    float: right;
    color: grey;
}
.panel-heading a.collapsed:after {
    content:"\e080";
}
   </style> 
 
  <script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
  </script>

	<%try{ %>
	<%
	String encimgPath="";
	String[]  imgpath=new String[5] ;
	  ArrayList issueTypeList = new ArrayList();
	  String selIssueType = "",sadaremId="";
	  issueTypeList = (ArrayList)request.getAttribute("issueTypeList");
	  selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType"));
	  sadaremId = CommonUtility.checkNullObj(request.getAttribute("sadaremId"));
	  String distId = (String)session.getAttribute("districtId");
	  String aliveStatus=CommonUtility.checkNullObj(request.getAttribute("aliveStatus"));
	  String proofStatus=CommonUtility.checkNullObj(request.getAttribute("proofStatus"));
	  String reqType =  CommonUtility.checkNullObj(request.getAttribute("reqType"));
	  ArrayList issuependingRecordList = new ArrayList();
	  issuependingRecordList           = (ArrayList)request.getAttribute("pendingIssueDtlsList");
	  String statusMsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
	  String fromdate=CommonUtility.checkNullObj((String)request.getAttribute("fromdate"));
	  String todate=CommonUtility.checkNullObj((String)request.getAttribute("todate"));
	
	%>
  
<script>

$(document).ready( function()
		{
	 
	$('#issueData').dataTable
	   ({
		   "iDisplayLength": 10, 
		   "paging" : false,
		   "scrollY" : 300
	    });
	  
	  //to get todays date
	     var d = new Date();
	     var month = d.getMonth()+1;
	     var day = d.getDate();
	     var output = (day<10 ? '0' : '') + day+ '/' +(month<10 ? '0' : '') + month + '/' +d.getFullYear();
	    //end 
	     
$('#fromdate').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
				maxDate:output,
		});
		
$('#todate').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatTime:'H:i',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
			maxDate:output,
		});   

						
			$('body').on('change','#reqtype',function()
					{	
								var vig=$("#reqtype").val();
								
								if(vig!="P")
								{
								
									$("#fromdatediv").show();
									$("#todatediv").show();
								}
								else
									{
								
									$("#fromdatediv").hide();
									$("#todatediv").hide();
									}
					});
		
		
				 $("#raiseissueButID").click(function ()
							{
						 
					 
						
					 if ($('#reqtype').val()==-1)
		                {
		                    alert("Please Select Status type");
		                    $('#issueType').focus();
		                    return false;
		                }					 
					 else
					 {						 			
						if($('#reqtype').val()!="P") 
						 {
							if($("#fromdate").val()=="")
	    					{
	    						alert("Please select From Date");
	    						$("#fromdate").focus();
								
								 event.preventDefault();
								event.stopPropagation();
								return false;
	    					}
	    					else if($("#todate").val()=="")
	    					{
	    						alert("Please select To Date");
	    						$("#todate").focus();
								
								 event.preventDefault();
								event.stopPropagation();
								return false;
	    					}
	    					else if(process($('#fromdate').val()) > process($('#todate').val()))
	    					{
	    						alert("fromdate cannot be greater than todate.Please select the dates properly");
	    						$("#todate").focus();
								
								 event.preventDefault();
								event.stopPropagation();
								return false;
	    					}
	    				
	    					else
	    					{	
	    					
	    						
	    						
	    						  document.form_issue.target="_self";
	    							document.form_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do";
	    							document.form_issue.submit();
	    					}
							
	    					}
						else
					 {

							
							
							 document.form_issue.target="_self";
 							document.form_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do";
 							document.form_issue.submit();	
					 }
						 }
						 
					  
			 		//}
			 
  	  
	     	  
			}); 
				 
				
		});
function process(date){
	   var parts = date.split("/");
	   var date = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	   return date.getTime();
	}
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

 <div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
						        <div class="panel-heading"  style="cursor: pointer;">
									             <h4 class="panel-title">
																My WorkList
												 </h4>
						        </div>
				        <div  >
				              <div class="panel-body">
							              <form class="form-inline" id="form_issue" name="form_issue" method="post" autocomplete="off">
							              <input type="hidden" name ="statusMsg" id="statusMsg" value='<%=statusMsg %>'>
  							<input type="hidden" id="mode" name="mode" value='pendingIssueReqList'>
									   <div class="row">         
										            
												             <div class=" col-xs-2" >
												             <div class="input-group">
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Issue Type</div>
															    <%=ComboUtil.createStrComboBoxAuto("issueType",issueTypeList,selIssueType, "form-control","",true,false,"")%>
															 </div>
															 </div>
														
													         <div class=" col-xs-2" >
													          <div class="input-group">
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Status</div>
															   	<select id="reqtype" name="reqtype"  class="form-control" style="height:35px" >
																							  <option value="P" <% if(reqType.equals("P")){ %> selected <%} %>>Pending</option>
																							  <option value="A" <% if(reqType.equals("A")){ %> selected <%} %>>Approved</option>
																							  <option value="R" <% if(reqType.equals("R")){ %> selected <%} %>>Rejected</option>
																							  
																							</select> 
																							 </div>
															 </div>	
													 
															 
										  <div class=" col-xs-2" > 
												 <div class="input-group" id="fromdatediv" name="fromdatediv" <% if(reqType.equals("P")){ %> style="display:none;" <%} %>>
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date</div>
											      <input type="text" class="form-control " id="fromdate" name="fromdate"  style="width:100px;"  autocomplete="off" value=<%=fromdate%>>				
							               </div>
							              </div>	
							              		
							              <div class=" col-xs-2" > 
							              		  <div class="input-group" id="todatediv"  name="todatediv" <% if(reqType.equals("P")){ %> style="display:none;" <%} %>>
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div>
											      <input type="text" class="form-control " id="todate" name="todate"   style="width:100px;"  autocomplete="off" value=<%=todate%>>				
							               </div>	 
										</div>
							             <div class=" col-xs-2" >
														  <button type="button" id="raiseissueButID" class="btn btn-success"><b>Submit</b></button>
										 </div>	
										</div>
										 </form>
							            
				                 </div>
				        </div>
				    </div>
	</div>
 
 
 <%if(issuependingRecordList!=null && issuependingRecordList.size()>0){ %>
  <table id="issueData" class="table table-striped table-bordered" width="90%">
    <thead>
      <tr>
        <td style="background-color:#337ab7;padding:5px; color:#fff;" align="center">S.No.</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue ID</td>
        <%if("-1".equals(selIssueType)){ %>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue Type</td>
        <%} %>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%if(reqType.equals("R")){ %>Rejected On<%}else if(reqType.equals("A")){  %>Approved On<%}else{ %>Pending Since<br> day(s)<%} %></td>
        <%if(reqType.equals("A")){ %>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Approved Remarks</td>
        <%} %>
           <%if(reqType.equals("R")){ %>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Rejected Remarks</td>
        <%} %> 
        
       <%if(reqType.equals("A") || reqType.equals("R")){ %>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Attachments</td>
        <%} %>  
            <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Count Of Grievances <br> Raised Under<br> this SADAREM ID </td>  
      </tr>
    </thead>
    <tbody>
    <%
	
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     String style="",tkttype="";
    
     for(loopCount=0;loopCount<issuependingRecordList.size();loopCount++)
	 { 
    	 innerList = (ArrayList)issuependingRecordList.get(loopCount);
    	 if(reqType.equals("A") || reqType.equals("R")){ 
    	  imgpath = ((String)innerList.get(8)).split(","); 
    	 }
		 
		 if(loopCount%2==0)
		 {
			 style="firstline";
		 }
		 else
		 {
			 style="secondline";
		 }
		 
		 if(reqType.equals("A") || reqType.equals("R"))
	     {
	    	 tkttype = (String) innerList.get(6); 
	     }
		 else
		 {
			 tkttype = (String) innerList.get(5);
		 }
	 %>
     <tr>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
   <td class="<%=style%>" style="border:#337ab7 1px solid;" >
     <a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/IssueApprovalSystem.do?mode=getValues&requestid=<%=innerList.get(0)%>&sadaremid=<%=innerList.get(1)%>&reqtype=<%=reqType %>&tkttype=<%=tkttype%>&randomid=<%=Math.random()%>">
			<font size="3" ><b><%=innerList.get(0)%></b></font>
			</a></td>
        <%if("-1".equals(selIssueType) && reqType.equals("P")){ %>
          <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(6) %>	</td>
        <%}else if("-1".equals(selIssueType)){ %>
            <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(7) %></td>
        <%} %>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(1) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(2) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(3) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center"><%=innerList.get(4) %> </td> <%-- <%if(reqType.equals("P")){ %> day(s)<%} %> --%>
         <%if(reqType.equals("A") || reqType.equals("R")){ %>
         <td class="<%=style%>" style="border:#337ab7 1px solid;" align="left"><%=innerList.get(5) %></td>
         <%} %>
         
           <%if(reqType.equals("A") || reqType.equals("R")){ %>
              <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center">
           <%for(int i=0;i<imgpath.length;i++)
		 {				                	
		 encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
		encimgPath = encimgPath.replace("+", "%2B"); %>
		 <a  class="btn icon-btn-primary btn-primary" style="color:#fff; padding:inherit;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-save "><%=i+1 %></span></a>
			 <%} %> </td>
			     <%} %>
			     
			        <%if(reqType.equals("P")){ %>
         <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center">
           <a class='iframehis' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/openIsuueTrackingstatus.do?mode1=getcountofgrievances&sadaremid=<%=innerList.get(1) %>&randomid=<%=Math.random()%>">
             <%if("-1".equals(selIssueType) && reqType.equals("P")){ %>
			<font size="3" ><b>  <span class="badge" style="background-color: rgb(51, 122, 183);"><%=innerList.get(7)%></span>  </b></font>
			  <%}else{ %>
			  	<font size="3" ><b> <span class="badge" style="background-color: rgb(51, 122, 183);"><%=innerList.get(6)%></span> </b></font>
			  
			  <%} %>
			</a> 
        
         
         </td>
         <%} %>
         	        <%if(reqType.equals("A") || reqType.equals("R")){ %>
         <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center">
          <a class='iframehis' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/openIsuueTrackingstatus.do?mode1=getcountofgrievances&sadaremid=<%=innerList.get(1) %>&randomid=<%=Math.random()%>">
            <%if("-1".equals(selIssueType) && (reqType.equals("A") || reqType.equals("R"))){ %>
			<font size="3" ><b><span class="badge" style="background-color: rgb(51, 122, 183);"><%=innerList.get(9)%></span> </b></font>
			  <%}else{ %>
			 	<font size="3" ><b><span class="badge" style="background-color: rgb(51, 122, 183);"><%=innerList.get(8)%></span></b></font> 
			   <%} %>
			</a>
         </td>
         <%} %>
         
         
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
      <table  width="90%" class="table table-hover table-bordered table-responsive ">
    <thead>
      <tr>
        <td style="background-color:#337ab7;padding:5px; color:#fff;" align="center">S.No.</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue ID</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%if(reqType.equals("R")){ %>Rejected On<%}else if(reqType.equals("A")){  %>Approved On<%}else{ %>Pending Since<%} %></td>
        <%if(reqType.equals("A")){ %>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Approved Remarks</td>
        <%} %>
           <%if(reqType.equals("R")){ %>
         <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Rejected Remarks</td>
        <%} %>
      </tr>
    </thead>
     <tr>
     <td height="15" align="center" valign="middle" class="Row" colspan="18">No records to display.</td> 
	</tr>
	</table>
	 <%
	 }
     %>

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
					$(document).ready(function()
							{
						 
						//Action on the issue
						
						$(".iframe").colorbox(
								{
								iframe:true, width:"95%", height:"95%",
								 onClosed: function(){document.form_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do?";
									document.form_issue.submit();}
								});
						
						
						//History
						
						$(".iframehis").colorbox(
								{
								iframe:true, width:"95%", height:"95%"
								 <%-- onClosed: function(){document.form_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do?";
									document.form_issue.submit();} --%>
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
							$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
							return false;
						});
					}
					);
		
				</script>
</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>