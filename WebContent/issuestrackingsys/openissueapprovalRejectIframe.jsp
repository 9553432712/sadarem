 <%@page import="java.util.ArrayList" %>
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
basicData = (ArrayList)request.getAttribute("sadaremData");
issueData1 = (ArrayList)request.getAttribute("issueData");
String sadaremid=CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
String reqId=CommonUtility.checkNullObj(request.getAttribute("reqId"));
String reqtype=CommonUtility.checkNullObj(request.getAttribute("reqtype"));
String newremarks=CommonUtility.checkNullObj(request.getAttribute("newremarks"));
historyData=(ArrayList)request.getAttribute("historyData");

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
					 
					document.name_issue.action="<%=request.getContextPath()%>/OpenIssueApprovalSystem.do?mode=approvedCase";
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
		 					document.name_issue.action="<%=request.getContextPath()%>/OpenIssueApprovalSystem.do?mode=rejectedCase";
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

 <%try{ %>
     <div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
 
  <div class="main_container" style="width:100%;">
  <div class="loader"></div> 
	<form name="name_issue"  method="post" enctype="multipart/form-data">
	<input type="hidden" name="sadaremId" id="sadaremId" value='<%=basicData.get(0)%>'>
	<input type="hidden" name="selIssueType" id="selIssueType" value='<%=issueData.get(1)%>'>
	<input type="hidden" name="reqId" id="reqId" value='<%=issueData.get(0)%>'>
	<input type="hidden" name="remarks" id="remarks">
		<div class="container" style="margin-top:20px;">
			<div class="row">
			 	<div style="color:green;" align="center"><b> ${statusMsg}</b></div> 
				<div class="panel panel-primary"> <!-- style="width:1140px;"  -->
					        
					 <%if(issueData.get(1).equals("S002") && reqtype.equals("P"))
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
						                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
                                            <table>
                                              <td><input type="text" name="telname" id="telname" class="form-control" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);"  onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()" value='<%=basicData.get(10)%>' >
                                             </td>
                                              <td><div class="input-group-addon"  id="fulnametelugu" name="fulnametelugu"><%=(String)basicData.get(11)%></div>  
                                             <input type="hidden" name="hidfulnametelugu" id="hidfulnametelugu" value='<%=(String)basicData.get(11)%>'></td>
                                            </table>
                                            </td>
						                  
						                   <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;"><font size="3px"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                  <%for(int i=0;i<imgpath.length;i++)
						                  {
						                	  System.out.println(imgpath[i]);
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
					                      <td  class="tdstyle" valign="middle" style="text-align:center;vertical-align:middle;">
                                           <table>
                                              <td><input type="text" name="telname" id="telname" class="form-control" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);"  onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()" value='<%=basicData.get(17)%>' >
                                             </td>
                                              <td><div class="input-group-addon"  id="fulnametelugu" name="fulnametelugu"><%=(String)basicData.get(18)%></div>  
                                             <input type="hidden" name="hidfulnametelugu" id="hidfulnametelugu" value='<%=(String)basicData.get(18)%>'>
                                            </table>
                                          </td>
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
					                	  System.out.println(imgpath[i]);
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
         <%}else if(issueData.get(1).equals("S015") && reqtype.equals("P"))
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
						                	  System.out.println(imgpath[i]);
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
	<%}else if(issueData.get(1).equals("S016") && reqtype.equals("P"))
			  { 
		String imgpath[] = ((String)issueData.get(5)).split(",");
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
						                	  System.out.println(imgpath[i]);
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
	else if(issueData.get(1).equals("S017")&& reqtype.equals("P"))
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
	 %> 
                               
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

<%}catch(Exception e){e.printStackTrace();}  %>
