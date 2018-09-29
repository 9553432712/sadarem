
<%try{ %> <%@page import="java.util.ArrayList" %>
 <%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,com.tcs.sadarem.util.PasswordEncriptDecrypt"language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
     <head>
     <meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
     <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
     </head> 
 <%
 ArrayList basicData = new ArrayList(); 
 ArrayList issueData = new ArrayList();
 ArrayList issueData1 = new ArrayList();
 ArrayList issuedata3 = new ArrayList();
 ArrayList historyData1 = new ArrayList();
 ArrayList historyData = new ArrayList();
 ArrayList MultiIssueIds = new ArrayList();
basicData = (ArrayList)request.getAttribute("sadaremData");
issueData1 = (ArrayList)request.getAttribute("issueData");
String sadaremid=CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
String reqId=CommonUtility.checkNullObj(request.getAttribute("reqId"));
String edu_name=CommonUtility.checkNullObj(request.getAttribute("edu_name"));
String reqtype=CommonUtility.checkNullObj(request.getAttribute("reqtype"));
String newremarks=CommonUtility.checkNullObj(request.getAttribute("newremarks"));
historyData=(ArrayList)request.getAttribute("historyData");
MultiIssueIds=(ArrayList)request.getAttribute("MultiIssueIds");


 if(issueData1.size()>0)
{
	issueData = (ArrayList)issueData1.get(0);
	
} 
if(basicData.size()>0)
{
	basicData = (ArrayList)basicData.get(0);
	
}
ArrayList relationTypeList = new ArrayList();

String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
String fullname = "&#3114;&#3138;&#3120;&#3149;&#3108;&#3135; &#3114;&#3143;&#3120;&#3137;";
String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
String selRelationType = CommonUtility.checkNullObj(request.getAttribute("selIssueType"));
String statusMsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
relationTypeList = (ArrayList)request.getAttribute("relationTypeList");
String selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType"));
String encimgPath="";

%>
<style>
		.tdstyle
		{
			border: #234466 1px solid;
			padding:5px;
			align:left;
			valign:left;
			background: #e2ebf4;
			vertical-align:middle;
		}
		.tdstyle1
		{
		border: none;
		padding:10px;
		align:left;
		background: #e2ebf4;
		readonly;
		}
		
		.thstyle
		{
			padding:5px;
			align:left;
			valign:left;
			background: #337ab7;
			color:#fff;
		}
		 .loader
		  {
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background: url('images/loading2.gif') 50% 50% no-repeat rgb(249,249,249);
		  }
		
		   .ui-collapsible-heading-collapsed > .ui-collapsible-heading-toggle{
		     /*   background:#337ab7; */
		   }
		
		  
</style> 
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>  
 <script>

$(document).ready( function()
		{
	
	     $("#approve").click(function ()
			{
	    	 
	    	  if ($('#newremarks').val()=="")
              {
                  alert("Please enter remarks");
                  $('#newremarks').focus();
                  return false;
              }else if(!($('#verifycheckbox').is(':checked')))
            	  {
            	  alert("Please verify the documents and select verified checkbox");
                  $('#verifycheckbox').focus();
                  return false;
            	  }
              else
            	  {
            	  if(confirm("Are you sure you want to approve the request?"))
            	  
				{
					 
					document.name_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do?mode=approvedCase";
					document.name_issue.submit();
					/*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				   /*Screen Locking Ended */
				}	
            	  }
			});
	     $("#reject").click(function ()
	 			{
	 	    	 
	 	    	  if ($('#newremarks').val()=="")
	               {
	                   alert("Please enter remarks");
	                   $('#newremarks').focus();
	                   return false;
	               }else if(!($('#verifycheckbox').is(':checked')))
	            	  {
	             	  alert("Please verify the documents and select verified checkbox");
	                   $('#verifycheckbox').focus();
	                   return false;
	             	  }
	 	    	  
	 	    	  else
	             	  {
	            	   if(confirm("Are you sure you want to reject the request?"))
	   				   {
		 				    document.name_issue.target="_self";
		 					document.name_issue.action="<%=request.getContextPath()%>/IssueApprovalSystem.do?mode=rejectedCase";
		 					document.name_issue.submit();
		 					/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						   /*Screen Locking Ended */
	   				   }  
	 			    
	             	  }
	 			});  
  	
		});


</script>  

 
     <div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
 
  <div class="main_container" style="width:100%;">
  <div class="loader"></div> 
	<form name="name_issue"  method="post" enctype="multipart/form-data">
	<%
	%>
	<input type="hidden" name="sadaremId" id="sadaremId" value='<%=basicData.get(0)%>'>
	<input type="hidden" name="selIssueType" id="selIssueType" value='<%=issueData.get(1)%>'>
	<input type="hidden" name="reqId" id="reqId" value='<%=issueData.get(0)%>'>
	<input type="hidden" name="remarks" id="remarks">
		<div class="container" style="margin-top:20px;">
			<div class="row">
			 	<div style="color:green;" align="center"><b> ${statusMsg}</b></div> 
				<div class="panel panel-primary"> <!-- style="width:1140px;"  -->
					        
					 <%
					 if(issueData.get(1).equals("S002") && reqtype.equals("P"))
					  { 
						 String imgpath[] = ((String)issueData.get(7)).split(",");
						 
					    
					    %> 
				                    <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Name Change
					                    </h3>
					                </div>
					          <div class="panel-body">	
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                       <td  class="thstyle" valign="middle" style="text-align:center">Name</td>
					                       <td class="thstyle"  valign="middle" style="text-align:center"><%=telugu%>(<%=fullname%>) </td>
					                       <td class="thstyle"   valign="middle" style="text-align:center">Proofs</td>
					                       <td class="thstyle"   valign="middle" style="text-align:center">Remarks</td>
					                    </tr>  
					                   <tr>
				                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(4)%></td>
						                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=(String)issueData.get(5)%></td>
						                  
						                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                  <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
						                   </td>
						                  
						                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
						                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:80%;padding-left:5px; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
						                  </td>
					                  </tr>
					                </table>
					                </div>
					                
					                <table border="0">             
						                <tr>
							                 <td align="center" colspan="4"  style="height:25px">
							                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
							       	         </td>
							       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
						                </tr>
							             <tr>
								             <td  align="center"   colspan="7" style="height:25px">
								             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
								               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
								             </td>
							             </tr>
					               </table> 
                            </div>       
			 <%}
			else if(issueData.get(1).equals("S003")&& reqtype.equals("P"))
			  { 
				 String imgpath[] = ((String)issueData.get(7)).split(",");
			  %> 
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                          Relation Name Change
					                    </h3>
					                </div>
					                <div class="panel-body">	
					                  <div class="panel panel-primary"> 				                 
					                  <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Relation Type</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Relation Name</td>
					                     <td  class="thstyle" valign="middle" style="text-align:center"><%=telugu%>(<%=fullname%>)</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					                
					                    <tr>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%> </td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(4)%></td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=(String)issueData.get(5)%></td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
						                      <%for(int i=0;i<imgpath.length;i++)
							                  {
							                	
							                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
							                      encimgPath = encimgPath.replace("+", "%2B"); %>
							                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
							                  <%} %>
					                      </td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
					                      </td>
					                    </tr>
					                </table>
					                </div>
					                
					             <table border="0"  >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>    
					           </div>          
			<%}else if(issueData.get(1).equals("S004")&& reqtype.equals("P"))
			{ 
				String imgpath[] = ((String)issueData.get(10)).split(",");
			%> 
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Address details Change
					                    </h3>
					                </div>
					                   <div class="panel-body">	
					               
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">District</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Mandal</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Village</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Habitation</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">House No</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">PIN Code</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                       <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					               <tr >
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%> </td>
					                  
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(4)%></td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(5)%></td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(6)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(7)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(8)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
			                       <%for(int i=0;i<imgpath.length;i++)
					                  {
					                	
					                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
					                      encimgPath = encimgPath.replace("+", "%2B"); %>
					                     <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
				                   <%} %>    
					                </td>
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
					                
					                </tr>
					                </table>
					                </div>
					                
					             <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>
					         </div>  
					         
				<%}else if(issueData.get(1).equals("S022")&& reqtype.equals("P"))
				{ 
				String imgpath[] = ((String)issueData.get(10)).split(",");
				%> 
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Inter District Change
					                    </h3>
					                </div>
					                   <div class="panel-body">	
					               
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">District</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Mandal</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Village</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Habitation</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">House No</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">PIN Code</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                       <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					               <tr >
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%> </td>
					                  
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(4)%></td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(5)%></td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(6)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(7)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(8)%> </td>
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
			                       <%for(int i=0;i<imgpath.length;i++)
					                  {
					                	
					                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
					                      encimgPath = encimgPath.replace("+", "%2B"); %>
					                     <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
				                   <%} %>    
					                </td>
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
					                
					                </tr>
					                </table>
					                </div>
					                
					             <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>
					         </div>   
				         
					         
					         
					          
			<%}else if(issueData.get(1).equals("S021")&& reqtype.equals("P"))
			{ 
				
				//String imgpath[] = ((String)issueData.get(10)).split(",");
				
			String imgpath[] = ((String)issueData.get(7)).split(",");
			
			%> 
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Sadarem Deletion Request
					                    </h3>
					                </div>
					                   <div class="panel-body">	
					               
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Request Id</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Sadarem ID</td>
											<td  class="thstyle" valign="middle" style="text-align:center">Description</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                      
					                       <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					               <tr >
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(0)%> </td>
					                  
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(2)%></td>
					                 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(6)%></td>
					               
					                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
					                
					               
					                
			                       <%for(int i=0;i<imgpath.length;i++)
					                  {
					                	
					                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
					                      encimgPath = encimgPath.replace("+", "%2B"); %>
					                     <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
				                   <%} %>    
					                </td>
					               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
					                
					                </tr>
					                </table>
					                </div>
					                
					             <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>
					         </div>          
         <%}else if(issueData.get(1).equals("S007")&& reqtype.equals("P"))
			{ %> 
					             
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Cause Of Disability Change
					                    </h3>
					                </div>
					                 <div class="panel-body">	
					               
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Cause Of Disability</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					                    <tr>
					                     <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%></td>
						                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
						                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
					               </tr>
					                </table>
					                </div>
					               <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>
					         </div>              
         <%}else if(issueData.get(1).equals("S008")&& reqtype.equals("P"))
			{ 
        	 String imgpath[] = ((String)issueData.get(5)).split(",");
			%> 
					             
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                         Aadhaar Change
					                    </h3>
					                </div>
					                 <table width="100%" style=" border : #234466 1px solid;">
					                <tr >
	                                       <td width="30%"  class="tdstyle" height="10px" colspan='1' valign="middle"><b> Aadhaar ID :</b>
						                   <%=issueData.get(3)%>
						                   </td>
						                   
						                   <td class="tdstyle" style="border-right:0px;" width="10%"><b>Remarks</b></td>
						                   
						                   <td  class="tdstyle"   valign="middle" style="border-left:0px;">
						                       <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:100%;display:inherit;" onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
						                  </td>
						                   <td   class="tdstyle" align="center" colspan="1"><font size="3px"><b>Proofs</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							               <%for(int i=0;i<imgpath.length;i++)
					                        {
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
					                          <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
				                   			<%} %>   
							               </td>
					               </tr>
					               <tr>
					                 <td width="56%" align="center" class="tdstyle" colspan="3" style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>I here by declare that I have verified all the proofs before taking any action.</b>
					       	          </td>
					       	          
						             <td  align="center" class="tdstyle" colspan="7" style="height:25px">
						             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
						               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
						             </td>
					             </tr>
					               </table>       
         <%}else if(issueData.get(1).equals("S005")&& reqtype.equals("P"))
			{ 
        	 String imgpath[] = ((String)issueData.get(6)).split(",");
			%> 
					             
					              
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                        Photo Change
					                    </h3>
					                </div>
					                <div class="panel-body">	
					                  <div class="panel panel-primary"> 				                 
					                <%if("M".equals((String)issueData.get(3))){ %> 
					                  <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center;border-right: 1px solid #fff;" >Requested Photo</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center" >Proofs</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center" >Remarks</td>
					                    </tr>
					                   
					                   <tr>
		                                       <td class="tdstyle"  valign="middle" align="center">
		                                         <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showphotoIssueimg&issueid=<%=issueData.get(0)%>&path=<%=issueData.get(5) %>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
		                                        </td>
		                                        
							                   <td  class="tdstyle" valign="middle" align="center" style="text-align:center;vertical-align:middle;"> 
						                       <%for(int i=0;i<imgpath.length;i++)
						                         {
							                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
							                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                          <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
					                   			<%} %>   
							               </td>
						                      
						                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
						                      </td> 
							           </tr>
					                </table>
					                 <%}else{ %>  
					                     <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center" >Aadhar Photo</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center" >Remarks</td>
					                    </tr>
					                   
					                   <tr>
		                                       <td class="tdstyle"  valign="middle" align="center">
		                                         <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showphotoIssueimg&issueid=<%=issueData.get(0)%>&path=<%=issueData.get(5) %>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
							                   </td>
							                   <td  class="tdstyle" valign="middle" align="center" style="text-align:center;vertical-align:middle;"> 
						                       <%for(int i=0;i<imgpath.length;i++)
						                        {
							                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
							                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                          <a class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
					                   			<%} %>   
							               </td>
						                      
						                      <td  class="tdstyle" valign="middle" align="center" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
						                      </td> 
							           </tr>
					                </table>
					                
					                
					                <%} %>
					                </div>
					                
					             <table border="0"  >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>    
					           </div>  
					            
          <%}else if(issueData.get(1).equals("S001")&& reqtype.equals("P"))
			  { 
        	  String imgpath[] = ((String)issueData.get(7)).split(",");
			  %> 
					             <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                  Full Name Change
					                    </h3>
					              </div>
					              <div class="panel-body">	
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Surname </td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Name </td>
					                      <td  class="thstyle" valign="middle" style="text-align:center"><%=telugu%>(<%=fullname%>) </td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs </td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks </td>
					                      </tr>
						                 <tr >
		 					                 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%> </td> 
							                 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(4)%></td>
							                 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=(String)issueData.get(5)%></td>
							                 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
							                  <%for(int i=0;i<imgpath.length;i++)
						                        {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                     <%} %>
						                  </td>
							               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
							            </tr>
					                </table>
					                </div>
					                
					             <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>   
					           </div>           
			 <%}else if(issueData.get(1).equals("S014")&& reqtype.equals("P"))
			   {
				 String imgpath[] = ((String)issueData.get(5)).split(",");
			%> 
					             
					               <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                       Photo Upload
					                    </h3>
					               </div>
					               <div class="panel-body">	
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Requested Photo</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proof</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
						                 <tr>
		                                        <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
		                                          <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showphotoIssueimg&issueid=<%=issueData.get(0)%>&path=<%=issueData.get(3) %>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
							                    </td>
							                    
							                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
								               	 <%for(int i=0;i<imgpath.length;i++)
							                        {
							                	      encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
							                          encimgPath = encimgPath.replace("+", "%2B"); %>
							                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
							                     <%}%>
								               </td>
						               <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea> </td>
							            </tr>
					                </table>
					                </div>
					                
					             <table border="0" >        
					               <tr>
						              <td  align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table> </div>
           <%}  
			else if(issueData.get(1).equals("S009")&& reqtype.equals("P"))
			  { String imgpath[] = ((String)issueData.get(5)).split(",");%> 
					                <div class="panel-heading">
					                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
					                          Person Status Change
					                    </h3>
					                </div>
					                 <div class="panel-body">	
					               
					                <div class="panel panel-primary"> 				                 
					                <table  class="table">
					                    <tr>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Existing Person Status</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Person Status to be changed to</td>
					                   
					                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
					                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
					                    </tr>
					                
					                    <tr>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=basicData.get(23)%> </td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%></td>

					                     <%--  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
					                        <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=issueData.get(0)%>"><span class="glyphicon glyphicon-paperclip">Attachment1</span></a>
					                          --%>
					                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
						           </td>
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
					                      </td> 
					                    </tr>
					                </table>  
					                </div>
					                
					             <table border="0"  >        
					               <tr>
						              <td  align="center" colspan="5"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="1" width="25%" style="height:25px"></td>
					              </tr>
					              <tr>
					               <td  align="center"  colspan="7" style="height:25px">
					                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
					                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
					               </td>
					             </tr>
					           </table>   </div>           
			<%} else if(issueData.get(1).equals("S015") && reqtype.equals("P"))
			  { 
				String imgpath[] = ((String)issueData.get(5)).split(",");
			    %> 
		                    <div class="panel-heading" style="text-align:center">
			                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
			                        Gender Change
			                    </h3>
			                </div>
			          <div class="panel-body">	
			             <div class="panel panel-primary"> 				                 
			                <table  class="table">
			                    <tr>
			                       <td  class="thstyle" valign="middle" style="text-align:center">Gender</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Proofs</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Remarks</td>
			                    </tr>  
			                   <tr>
		                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%if(issueData.get(3).equals("1")) {%>Male<%}else{ %>Female<%} %></td>
				                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
						           </td>
				                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
				                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:80%;padding-left:5px; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
				                  </td>
			                  </tr>
			                </table>
			              </div>
			                
			                <table border="0">             
				                <tr>
					                 <td align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
				                </tr>
					             <tr>
						             <td  align="center"   colspan="7" style="height:25px">
						             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
						               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
						             </td>
					             </tr>
			               </table> 
                    </div>       
	<%}	else if(issueData.get(1).equals("S017")&& reqtype.equals("P"))
	  { 
		 String imgpath[] = ((String)issueData.get(5)).split(",");
	  %> 
			                <div class="panel-heading">
			                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
			                          Qualification Change
			                    </h3>
			                </div>
			                <div class="panel-body">	
			                  <div class="panel panel-primary"> 				                 
			                  <table  class="table">
			                    <tr>
			                      <td  class="thstyle" valign="middle" style="text-align:center">Qualification Type</td>
			                  
			                  
			                      <td  class="thstyle" valign="middle" style="text-align:center">Proofs</td>
			                      <td  class="thstyle" valign="middle" style="text-align:center">Remarks</td>
			                    </tr>
			                
			                    <tr>
			                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><%=issueData.get(3)%> </td>

			              
			                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> 
				                      <%for(int i=0;i<imgpath.length;i++)
					                  {
					                	  System.out.println(imgpath[i]);
					                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
					                      encimgPath = encimgPath.replace("+", "%2B"); %>
					                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
					                  <%} %>
			                      </td>
			                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><textarea class="form-control" rows="1" id="newremarks" name="newremarks"  style="width:80%; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
			                      </td>
			                    </tr>
			                </table>
			                </div>
			                
			             <table border="0"  >        
			               <tr>
				              <td  align="center" colspan="5"  style="height:25px">
			                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
			       	         </td>
			       	         <td  align="center" colspan="1" width="25%" style="height:25px"></td>
			              </tr>
			              <tr>
			               <td  align="center"  colspan="7" style="height:25px">
			                  <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
			                  <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
			               </td>
			             </tr>
			           </table>    
			           </div>          
	<%}
			
			
			else if(issueData.get(1).equals("S016") && reqtype.equals("P"))
			  { 
		String imgpath[] = ((String)issueData.get(5)).split(",");
		System.out.println("imgpath"+imgpath);
			    %> 
			    <marquee><font color="red">For age correction do not accept Aadhar card as a proof document.</font></marquee>
		                    <div class="panel-heading" style="text-align:center">
			                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
			                        Age Correction
			                    </h3>
			                </div>
			          <div class="panel-body">	
			                <div class="panel panel-primary"> 				                 
			                <table  class="table">
			                    <tr>
			                       <td  class="thstyle" valign="middle" style="text-align:center">Date of Birth</td>
			                       <td  class="thstyle" valign="middle" style="text-align:center">Age</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Proofs</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Remarks</td>
			                    </tr>  
			                   <tr>
		                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(3)%></td>
		                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(6)%></td>
				                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
				                   </td>
				                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
				                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:80%;padding-left:5px; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
				                  </td>
			                  </tr>
			                </table>
			                </div>
			                
			                <table border="0">             
				                <tr>
					                 <td align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
				                </tr>
					             <tr>
						             <td  align="center"   colspan="7" style="height:25px">
						             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
						               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
						             </td>
					             </tr>
			               </table> 
                    </div>     
                    <%} else if(issueData.get(1).equals("S018") && reqtype.equals("P"))
			  { 
				String imgpath[] = ((String)issueData.get(5)).split(",");
			    %> 
		                    <div class="panel-heading" style="text-align:center">
			                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
			                       Appellate Authority SPMU level
			                    </h3>
			                </div>
			          <div class="panel-body">	
			             <div class="panel panel-primary"> 				                 
			                <table  class="table">
			                    <tr>
			                       <td  class="thstyle" valign="middle" style="text-align:center">Appellate Category</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Proofs</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Remarks</td>
			                    </tr>  
			                   <tr>
		                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(3)%></td>
				                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
						           </td>
				                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
				                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:80%;padding-left:5px; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
				                  </td>
			                  </tr>
			                </table>
			              </div>
			                
			                <table border="0">             
				                <tr>
					                 <td align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
				                </tr>
					             <tr>
						             <td  align="center"   colspan="7" style="height:25px">
						             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
						               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
						             </td>
					             </tr>
			               </table> 
                    </div>     
	 <%}else if(issueData.get(1).equals("S019") && reqtype.equals("P"))
			  { 
				String imgpath[] = ((String)issueData.get(5)).split(",");
			    %> 
		                    <div class="panel-heading" style="text-align:center">
			                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
			                       Part-B Re-Entry Provision
			                    </h3>
			                </div>
			          <div class="panel-body">	
			             <div class="panel panel-primary"> 				                 
			                <table  class="table">
			                    <tr>
			                       <td  class="thstyle" valign="middle" style="text-align:center">Part-B Re-Entry Reason</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Proofs</td>
			                       <td class="thstyle"   valign="middle" style="text-align:center">Remarks</td>
			                    </tr>  
			                   <tr>
		                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(3)%></td>
				                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} %>
						           </td>
				                  <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
				                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:80%;padding-left:5px; " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
				                  </td>
			                  </tr>
			                </table>
			              </div>
			                
			                <table border="0">             
				                <tr>
					                 <td align="center" colspan="4"  style="height:25px">
					                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
					       	         </td>
					       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
				                </tr>
					             <tr>
						             <td  align="center"   colspan="7" style="height:25px">
						             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
						               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
						             </td>
					             </tr>
			               </table> 
                    </div>     
	 <%}
	 else if(issueData.get(1).equals("S020") && reqtype.equals("P"))
	  { 
		String imgpath[] = ((String)issueData.get(19)).split(",");
	    %> 
                   <div class="panel-heading" style="text-align:center">
	                    <h3 class="panel-title" ><!-- style="background-color: #eee;color: white;" -->
	                       Multiple Grievances Correction
	                    </h3>
	                </div>
	          <div class="panel-body">	
	             <div class="panel panel-primary" style="width:70%; margin:0px auto;"> 				                 
	                <table  class="table" style="width:100%">
	                    <tr>
	                       <td  class="thstyle" valign="middle" style="text-align:center">Fields Name</td>
	                       <td class="thstyle"   valign="middle" style="text-align:center">Existing Details</td>
	                         <td class="thstyle"   valign="middle" style="text-align:center">Updated Details</td>
	           
	                    
	                    </tr>
	                    <% for(int j=0;j<MultiIssueIds.size();j++)
	                    {
	                    if(MultiIssueIds.get(j).equals("S001") ) {
	                    %> 
	                       <tr>
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Sur Name</td> 
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(9)%></td> 
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(3)%></td> 
                          </tr>
                           <tr>
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">First Name</td> 
                            <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(10)%></td> 
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(4)%></td> 
                          </tr>
                           <tr>
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Telugu Name</td> 
                            <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(11)%></td> 
                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(5)%></td> 
                          </tr>
	             	<%} 
	                    if(MultiIssueIds.get(j).equals("S003")) {
		                    %> 
		                       <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Relation Name</td> 
	                             <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(17)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(7)%></td> 
	                          </tr>
	                           <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">First Name</td> 
	                                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(16)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(6)%></td> 
	                          </tr>
	                           <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Telugu Name</td> 
	                                <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(18)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(8)%></td> 
	                          </tr>
		             	<%} 
	                    if(MultiIssueIds.get(j).equals("S016")) {
		                    %> 
		                       <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Date Of Birth</td> 
	                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(12)%>&nbsp;</td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(10)%>&nbsp;(<%=issueData.get(11)%>)</td> 
	                          </tr>
	                      
		             	<%} 
	                    if(MultiIssueIds.get(j).equals("S017")) {
		                    %> 
		                       <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Qualification</td> 
	                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=edu_name%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(9)%></td> 
	                          </tr>
	                      
		             	<%} 
	                    if(MultiIssueIds.get(j).equals("S004")) {
		                    %> 
		                       <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">District</td> 
	                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(5)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(12)%></td> 
	                          </tr>
	                           <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Mandal</td> 
	                             <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(6)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(13)%></td> 
	                          </tr>
	                           <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Village</td> 
	                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(7)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(14)%></td> 
	                          </tr>
	                             <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Habitation</td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(8)%></td> 
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(15)%></td> 
	                          </tr>
	                             <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">House No.</td>
	                            <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(26)%></td>  
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(16)%></td> 
	                          </tr>
	                             <tr>
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">Pin Code</td>
	                           <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=basicData.get(27)%></td>  
	                          <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"> <%=issueData.get(17)%></td> 
	                          </tr>
	                         
		             	<% }} %>
		             	<tr>
		             	 <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font align=left><b>Remarks</b></font>
				                    <textarea class="form-control" rows="1" id="newremarks" name="newremarks"   style="width:100%;padding-left:5px;height:60px " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);" ></textarea>
				                  </td>
		             	  <td  class="tdstyle" colspan="2" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                   <%
				                   for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="btn icon-btn-primary btn-primary" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>
						                  <%} 
				                  
						                  %>
						           </td>
				                 
		             	</tr>
		             	
	             	
	                </table>
	              </div>
	                
	                <table border="0" style="margin:0px auto;">             
		                <tr>
			                 <td align="center" colspan="4"  style="height:25px">
			                   <input type="checkbox" name="verifycheckbox" id="verifycheckbox" value="Y" style="margin:-3px; "><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that I have verified all the proofs before taking any action.</b>
			       	         </td>
			       	         <td  align="center" colspan="2" width="25%" style="height:25px"></td>
		                </tr>
			             <tr>
				             <td  align="center"   colspan="7" style="height:25px">
				             <a href="#" id="approve" name="approve" ><img src="<%=request.getContextPath()%>/images/approve.png" width="100" height="30" /></a>
				               <a href="#" id="reject" name="reject"><img src="<%=request.getContextPath()%>/images/reject.png" width="100" height="30" /></a>
				             </td>
			             </tr>
	               </table> 
           </div>     
<%}%>
         </div>  
    	</div>
	</div>
				
 <table width="100%">
	<tr> 
      <td colspan="6" width="100%">
        <div data-role="main" class="ui-content" >
        <div data-role="collapsible"  >
        <h1><font color="#337ab7">Workflow Status of Request : <%=reqId %></font></h1>
        <div class="container">
    <table class="table table-hover table-bordered table-responsive " width="90%">
	    <thead>
	      <tr>
	        <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Role Name</td>
	        <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Person Name</td>
	        <td  align="center" style="background-color:#337ab7;padding:5px; color:#fff; ">Action</td>
	        <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Remarks</td>
	        <td  align="center" style="border-right:#337ab7 1px solid;background-color:#337ab7;padding:5px;color:#fff;">Date</td>
	      </tr>
	    </thead>
    <%
    
    
	if(historyData!=null && historyData.size()>0)
	{
	   ArrayList innerList = new ArrayList();
       int loopCount;
       for(loopCount=0;loopCount<historyData.size();loopCount++)
	   { 
    	 innerList = (ArrayList)historyData.get(loopCount);
    %>
	  <tr>
       <td  style="border:#337ab7 1px solid;" align="left"><%=innerList.get(5) %></td> 
       <td  style="border:#337ab7 1px solid;" align="left"><%=innerList.get(4) %> </td>
        <td  style="border:#337ab7 1px solid;"  align="left"><%=innerList.get(0)%></td>
        <td  style="border:#337ab7 1px solid;"  align="left"><%=innerList.get(1) %></td>
        <td  style="border:#337ab7 1px solid;"  align="left"><%=innerList.get(2) %></td>
      </tr>
      <%}
     }
     else
     {%>
   	     <tr>
   	     <td height="15" align="center" valign="middle" class="Row" colspan="18">No history to display.</td> 
   		</tr>
   <%}%>
   </table>
  </div>
                         </div>
                            </div></td><td></td></tr>
					</table>
</form>
 
</div>

<%}catch(Exception e){
	
	e.printStackTrace();}  %>
