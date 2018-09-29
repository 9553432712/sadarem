<%--
    Document   : CertificatesUpload
    Created on : Oct 27, 2014, 2:24:39 PM
    Author     : 747577
--%>  
<%@page import="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,com.tcs.sadarem.util.PasswordEncriptDecrypt,com.tcs.sadarem.util.CommonValidators" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %> 
<!DOCTYPE HTML>
<html>
    <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/> 
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>

	<%

	String encimgPath="";
	
	String selSadaremId 	= CommonUtility.checkNullObj(request.getAttribute("selSadaremId"));
	String PartBStatusRmks 	= CommonUtility.checkNullObj(request.getAttribute("PartBStatusRmks"));
	
	ArrayList resultList 	= (ArrayList)request.getAttribute("SadaremBasicDtlsList");
	ArrayList historyList 	= (ArrayList)request.getAttribute("historyList");
	
	 String alert_msg 	 = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
  	 String alert_css 	 = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
  	 
  	 if(alert_css.equals(""))
  	 {
  		alert_css = "alert-info";
  	 }
	
	
	String person_status="",aadhaar_id="",form_status="",cert_elgible_status="",disp_type="",cert_upload_status="",idcard_upload_status="";
	
	
	%>
<style type="text/css">


/* Process Layer Started */
     	
     	#processlayer
			{
				text-align: center;
				position: fixed;
				margin-right: -150px;
				margin-top: -75px;
				right: 50%;
				top: 50%;
				z-index: 99999;
				width:300px;
				display: none;
			}
			
			#blocklayer
			{
				display: none;
				position: fixed;
				left: 0;
				top: 0;
				bottom:0;
				right:0;
				background: #ECF1EF;
			}
     	
     /* 	 Process Layer Ended  */
     
     
  
		
	.errmsg
	{
	color: red;
	}
	
	  
     
     
</style>	

</head>
<body>
<div class="row">
 <div class="col-md-12">
<div class="panel-group" id="accordion">
   <div class="panel panel-primary" >
       <div class="panel-heading" data-toggle="collapse" data-target="#collapseOne"  href="#collapseOne" style="cursor: pointer;">
            <h4 class="panel-title">
					SADAREM Certificate Upload
		 </h4>
       </div> 
           <div class="panel-body" style="min-height: 350px;">
         <%
								    if(!alert_msg.equals("")) 
								    {
								    %> 
									    <div class="alert <%=alert_css %> fade in alert-dismissable">
										    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
										    <b><%=alert_msg%></b>
										</div> 
									<%
								    }
								%>    
								            
           
							<%
							if(selSadaremId.equals("") && resultList==null)
							{
							%>
							<div class="row">
								<div class="col-md-12">	
									<div class="alert alert-info">
											<form class="form-inline" id="searchCertuploadForm" name="searchCertuploadForm" method="post" autocomplete="off">
											            <input type="hidden" id="action" name="action" value="getuploadedcertificatebasedtls">
											            <input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
												            <div class="input-group">
														      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">SADAREM ID</div>
														      <input type="text" class="form-control " id="sadaremid" name="sadaremid"  onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="17">
														      <span class="input-group-addon" id="sadaremidErrMsg"></span>
														    </div>  
															<button type="button" id="searchbtnid" class="btn btn-success"><b>Search</b></button>
														 	 
										    </form>
									</div>
								</div>
							</div>
							<%
							}
							%>
							<div class="row">
								<div class="col-md-12">	
							<%	    
							
							if(resultList!=null && resultList.size()==1)
							{
								resultList = (ArrayList)resultList.get(0); 

								form_status 		= CommonUtility.checkNullObj(resultList.get(0));
								person_status 		= CommonUtility.checkNullObj(resultList.get(11));
								aadhaar_id 			= CommonUtility.checkNullObj(resultList.get(14));
								disp_type			= CommonUtility.checkNullObj(resultList.get(15));
								cert_elgible_status = CommonUtility.checkNullObj(resultList.get(17));
								cert_upload_status	= CommonUtility.checkNullObj(resultList.get(21));
								idcard_upload_status= CommonUtility.checkNullObj(resultList.get(23));
							%>
							<div class="alert alert-warning">
							<div id="sadaremDetailsDIVID" style="border-bottom:#ccc solid 1px;margin: 20px;"  oncontextmenu="return false;" >
										    <ul class="nav nav-tabs" id="myTab">
										        <li class="active"><a href="#sectionA"><b>Person Basic Details</b></a></li>
										        <li><a href="#sectionB"><b>Person Disability Details</b></a></li>
										        <li><a href="#sectionC"><b>Uploaded Certificate Details</b></a></li>
										        <li><a href="#sectionD"><b>History Of Assessment</b></a></li>
										    </ul>
										    <div class="tab-content" style="padding:10px; border-left:#ccc solid 1px;  border-right:#ccc solid 1px;">
										        <div id="sectionA" class="tab-pane fade in active">
										          <h3>Part A Information</h3>
										          <div class="row">
										          <div class="col-md-5">
										          			<div class="panel-body">
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(1) %></span> 
												                            </div>
												                          </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">District</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(2) %></span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Mandal</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(3) %></span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Village</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(4) %></span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Habitation</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(5) %></span> 
												                            </div>
												                        </div>
												                        <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Part-A Created Date</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(29) %></span> 
												                            </div>
												                        </div>
												           </div>	
												  </div>
												  <div class="col-md-5">
												           
												 <div class="panel-body">
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name (Surname Name)</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(6).toString().trim() %>&nbsp;<%=resultList.get(7).toString().trim() %> </span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Birth (DD/MM/YYYY)</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(8).toString().trim() %> </span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Gender</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(9).toString().trim() %></span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Relationship Details</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(10).toString().trim() %></span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Status</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(11).toString().trim() %> </span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Contact No.</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(12).toString().trim() %>&nbsp;</span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">ID Proof Details</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(13).toString().trim() %>  <%=resultList.get(14).toString().trim() %>&nbsp;</span> 
												                            </div>
												                        </div>
												           </div>
												        </div>
												        <div class="col-md-2">
												        	<div style="height: 160px; width:160px;background-color: #37ADB6; text-align: center;vertical-align: middle; padding-top: 5px;">
												        		<img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=resultList.get(1).toString().trim()%>" width="152" height="152" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
												        	</div>
												        	
												        </div>
												     </div>
										        </div>
										        <div id="sectionB" class="tab-pane fade">
										              <h3>Part B Information</h3>
										           <div class="row">
										           <%
										           if(resultList.get(0).toString().equalsIgnoreCase("view") && resultList.get(15).toString().length()>0)
										           {
										           %>
										           		<div class="col-md-5">
										          			<div class="panel-body">
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Disabiligy</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(15) %>&nbsp;</span> 
												                            </div>
												                          </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Disability Percent</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(16) %>%&nbsp;</span> 
												                            </div>
												                        </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Status</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(17) %>&nbsp;</span> 
												                            </div>
												                        </div>
												           </div>	
												         </div>
												         <div class="col-md-5">  
														 			<div class="panel-body">
														                         <div class="form-group row" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Type</div>
																					  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(18).toString().trim() %>&nbsp;</span> 
														                            </div>
														                        </div>
														                         <div class="form-group" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Issued on</div>
																					  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(19).toString().trim() %>&nbsp;</span> 
														                            </div>
														                        </div>
														                         <div class="form-group" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Expires on</div>
																					  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(20).toString().trim() %>&nbsp;</span> 
														                            </div>
														                        </div>
														           </div>
												           </div>
												           <%
										           			}
												           else
												           {
												        	   %>
												        	   <div class="panel-body  col-md-12">
												        	   		 <div class="alert alert-danger" style="height:30px;padding-top:5px;margin-top:2px;">
																		  <b>Part B Form not filled.</b>
																		  
																	</div>
																	<br>
																		  <%if(PartBStatusRmks.length()>0){ %>
																		  <h4 style="color: green;"><%=PartBStatusRmks %></h4>
																		  <%} %>
																		  <%if(!resultList.get(11).toString().trim().equals("Live"))
																		  {%>
																			  				<h5 style="color: green;">Before filling Part-B, raise an online grievance in Issue Tracking to change the person status. After all approvals fill Part-B</h5>								  
																		  <%}%>	
																		  
												        	   </div>
												        	   <%
												           }
												           %>
												     </div>
										           
										        </div>
										           <div id="sectionC" class="tab-pane fade">
										              <h3>Certificate and ID Card Uploaded Information</h3>
										           <div class="row">
										          
										           		<div class="col-md-5">
										          			<div class="panel-body">
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Is Certificate uploaded </div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(21) %>&nbsp;</span> 
												                            </div>
												                          </div>
												                         <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificated Uploaded Date</div>
																			  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(22) %>&nbsp;</span> 
												                            </div>
												                        </div>
												                          <div class="form-group" style="height:30px;padding-top:5px;">
												                            <div class="input-group">
												                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Download Uploaded Certificate</div>
																			  <span class="input-group-addon" style="text-align:left;">
																	   				<% 
																					  if((resultList.get(21).toString().trim()).equalsIgnoreCase("Yes"))
																					  {
													                	              // System.out.println((String)resultList.get(25).toString().trim());
													                	               encimgPath = PasswordEncriptDecrypt.encrypt((String)resultList.get(26).toString().trim());
													                                   encimgPath = encimgPath.replace("+", "%2B"); 
													                                  %>
														                                  	<a  class="" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadCertificateIdCard&path=<%=encimgPath%>">
														                                  		<button type="button" class="btn btn-primary">
																						      		<span class="glyphicon glyphicon-download"></span> <%="("+resultList.get(28).toString().trim()+")"%>
																						    	</button> 
																						    </a>
																		 			  <%
																					  }
																					   else
																					  {
																						 out.write("--");
																					  }
																					  %>  
																			  </span> 
												                            </div>
												                        </div>
												                        
							
												           </div>	
												         </div>
												         <div class="col-md-5">  
														 			<div class="panel-body">
														                         <div class="form-group row" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Is ID Card Uploaded</div>
																					  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(23).toString().trim() %>&nbsp;</span> 
														                            </div>
														                        </div>
														                         <div class="form-group" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">ID Card Uploaded Date</div>
																					  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(24).toString().trim() %>&nbsp;</span> 
														                            </div>
														                        </div>
														                          <div class="form-group" style="height:30px;padding-top:5px;">
														                            <div class="input-group">
														                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Download Uploaded ID Card</div>
																					  <span class="input-group-addon" style="text-align:left;">  
																							  <% 
																							  if((resultList.get(23).toString().trim()).equalsIgnoreCase("Yes"))
																							  {
															                	              // System.out.println((String)resultList.get(25).toString().trim());
															                	               encimgPath = PasswordEncriptDecrypt.encrypt((String)resultList.get(25).toString().trim());
															                                   encimgPath = encimgPath.replace("+", "%2B"); 
															                                  %>
																                                  	<a  class="" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadCertificateIdCard&path=<%=encimgPath%>">
																                                  		<button type="button" class="btn btn-primary">
																								      		<span class="glyphicon glyphicon-download"></span> <%="("+resultList.get(27).toString().trim()+")"%>
																								    	</button> 
																								    </a>
																				 			  <%
																							  }
																							   else
																							  {
																								 out.write("--");
																							  }
																							  %> 
																					  </span> 
														                            </div>
														                        </div> 
														           </div>
												           </div>
												         
												     </div>
										           
										        </div>
										        <div id="sectionD" class="tab-pane fade">
										        			 <h3>History of SADAREMID Assessed.</h3>
										          			 <div class="row">
											        			 <div class="panel-body col-md-12">
											        			 
											        			 <%-- 	<%if(historyList!=null && historyList.size()==1)
										           					 {%>
										           					 	No History Found
										           					 <%}%> --%>
											        			 	<%if(historyList==null || historyList.size()==0)
										           					 {%>
										           					 	No History Found
										           					 <%}%>
													        	   	<%if(historyList!=null && historyList.size()>0)
										           					 {%>
										           					  <table class="table table-striped table-bordered" style="width:85%">
										           					 	<thead>
										           					 		<tr>
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				S.No.
										           					 			</td>
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				Cause Of Disability
										           					 			</td>			           					 			
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				Assessment Date
										           					 			</td>
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				Total Disability
										           					 			</td>
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				Login ID
										           					 			</td>
										           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
										           					 				Updated Date
										           					 			</td>
										           					 			
										           					 		</tr>	
										           					 	</thead>
										           					 	<tbody>
														            	<%ArrayList tempList = new ArrayList();
														            	int count=1;
														            	for(int looper=historyList.size()-1;looper>=0;looper--)
														            	{
														            		/* if(count==4)
														            			break; */
														            		tempList = (ArrayList)historyList.get(looper);
														            		
														            	%>	
														            	
														            		<tr>
														            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=count%></td>
														            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(4) %></td>
														            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(0) %></td>
														            			<td style="border:#337ab7 1px solid;text-align:right;padding-left:2px;"><%=tempList.get(2) %></td>
														            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(1) %></td>
														            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(3) %></td>
														            		</tr>						            					            		
														            		
														            	<%count++;
														            	}%>
														            	</tbody>
														            	</table> 
														           	 <%}%>
											            		
																		
													        	   </div>			        			
										       				 </div>
										    </div>
										    </div>
								</div>
								
								<div id="printdivid" style="display: none;">
									<table width="95%" class="table table-striped table-bordered">
										<tr>
											<td width="50%" align="center">
													<table width="98%"  border="1" >
														<tr>
															<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"> Basic Details</td>
														</tr>
														<tr>
															<td>SADAREM ID </td>
															<td><%=resultList.get(1)%></td>
														</tr>
														<tr>
														 <td>District</td>
														 <td><%=resultList.get(2)%></td>
												        </tr>
							                         	<tr>
							                              <td>Mandal</td>
														  <td><%=resultList.get(3)%></td>
							                            </tr>
												        <tr>
												           <td>Village</td>
														   <td><%=resultList.get(4)%></td>
												        </tr>
												        <tr>
												           <td>Habitation</td>
														   <td><%=resultList.get(5)%></td>
												        </tr>
												        <tr>
															<td>Person Name (Surname Name)</td>
															<td><%=resultList.get(6).toString().trim() %>&nbsp;<%=resultList.get(7).toString().trim() %> </td>
														</tr>
														<tr> 
															<td>Date of Birth (DD/MM/YYYY)</td>
															<td><%=resultList.get(8).toString().trim() %> </td>
														</tr>
														<tr> 
															<td>Gender</td>
															<td><%=resultList.get(9).toString().trim() %></td>
														</tr> 
														<tr>
															<td>Relationship Details</td>
															<td><%=resultList.get(10).toString().trim() %></td>
														</tr> 
														<tr>
															<td>Person Status</td>
															<td><%=resultList.get(11).toString().trim() %> </td>
														</tr> 
														<tr>
															<td>Contact No.</td>
															<td><%=resultList.get(12).toString().trim() %>&nbsp;</td>
															
														</tr>
														<tr>
															<td>Part-A Created Date</td>
															<td><%=resultList.get(29).toString().trim() %>&nbsp;</td>
															
														</tr>
														<tr>
															<td>ID Proof Details</td>
															<td><%=resultList.get(13).toString().trim() %>  <%=resultList.get(14).toString().trim() %>&nbsp;</td>
														</tr> 
													</table>
											</td>
											<td width="50%" valign="top">
											      <%
											      if(resultList.get(0).toString().equalsIgnoreCase("view") && resultList.get(15).toString().length()>0)
										           {
										           %>
													<table  width="98%"  border="1" >
														<tr>
															<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"> Disablitiy Details</td>
														</tr>
														<tr>
															<td>Type of Disabiligy</td>
															<td><%=resultList.get(15) %>&nbsp;</td>
														</tr>
														<tr>
															<td>Disability Percent</td>
															<td><%=resultList.get(16) %>%&nbsp;</td>
														</tr> 
														<tr>
															<td>Certificate Status</td>
															<td><%=resultList.get(17) %>&nbsp;</td>
														</tr>
														<tr>
															<td>Certificate Type</td>
															<td><%=resultList.get(18).toString().trim() %>&nbsp;</td>
														</tr>
														<tr>
															<td>Certificate Issued on</td>
															<td><%=resultList.get(19).toString().trim() %>&nbsp;</td>
														</tr>  
														<tr>
															<td>Certificate Expires on</td>
															<td><%=resultList.get(20).toString().trim() %>&nbsp;</td>
														</tr> 
														<tr>
															<td>Is Certificate uploaded </td>
															<td><%=resultList.get(21) %>&nbsp;</td>
														</tr>
														<tr>
															<td>Certificated Uploaded Date</td>
															<td><%=resultList.get(22) %>&nbsp;</td>
														</tr>
														<tr>
															<td>Is ID Card Uploaded</td>
															<td><%=resultList.get(23).toString().trim() %>&nbsp;</td>
														</tr>
														<tr>
															<td>ID Card  Uploaded Date</td>
															<td><%=resultList.get(24) %>&nbsp;</td>
														</tr>
													</table>
												
												<%
										           }
										           else
										           {
										        	   %>
										        	   	Part-B details not filled.
										        	   <%
										           }
												%>
											</td>
										</tr>
										<tr>
											<td colspan="2" width="100%">
											<table width="100%" align="center" class="table table-striped table-bordered">
												<tr>
													<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">History of SADAREMID Assessed</td>
												</tr>
												<tr>
													<td align="center" width="100%"> 
																<%
																if(historyList!=null && historyList.size()>0)
																	{
															   %>
															          <table class="table table-striped table-bordered" border="1" width="100%"> 
															 		<tr>
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				S.No.
															 			</td>
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				Cause Of Disability
															 			</td>			           					 			
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				Assessment Date
															 			</td>
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				Total Disability
															 			</td>
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				Login ID
															 			</td>
															 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
															 				Updated Date
															 			</td>
															 			
															 		</tr> 
													            	<%
													            	ArrayList tempList = new ArrayList();
													            	int count=1;
													            	for(int looper=historyList.size()-1;looper>=0;looper--)
													            	{
													            		/* if(count==4)
													            			break; */
													            		tempList = (ArrayList)historyList.get(looper);
													            		
													            	%>	
													            	
													            		<tr>
													            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=count%></td>
													            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(4) %></td>
													            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(0) %></td>
													            			<td style="border:#337ab7 1px solid;text-align:right;padding-left:2px;"><%=tempList.get(2) %></td>
													            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(1) %></td>
													            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(3) %></td>
													            		</tr>						            					            		
													            		
													            	<%count++;
													            	}%> 
													            	</table> 
															  <%
															  }
															  else
															  {
															   %>
															    		No History Avaiable
													          <%
													          }
													          %>
														</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
								
								</div>
							</div>
							
						<div class="row">
      						  <div class="col-md-12">
      								<div class="alert alert-info">
							<%
							   
							/* 
							cert_elgible_status
							cert_upload_status
							idcard_upload_status 
							*/
							if(
								form_status.equalsIgnoreCase("view") &&
								person_status.equalsIgnoreCase("live") &&
								CommonValidators.AadhaarNumberValidator(aadhaar_id)==true && 
								disp_type.length()>0
							)
							{
							%>  
							<form id="certuploadForm" name="certuploadForm" method="post" enctype="multipart/form-data">   
					            <input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>"> 
								   <div class="row form-inline">
							            <div class="input-group">
									      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Upload Certificate</div>
									      <input type="file" class="form-control " id="cert_file" name="cert_file" autocomplete="off">
									    </div>  
									     <div class="input-group">
									      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Upload ID Card</div>
									      <input type="file" class="form-control " id="idcard_file" name="idcard_file" autocomplete="off" <%if(!cert_elgible_status.trim().toUpperCase().equalsIgnoreCase("ELIGIBLE")){%>disabled<%}%>>
									    </div>   
									     <div class="input-group">
									      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Remarks</div>
									      <textarea class="form-control " id="upload_remarks" name="upload_remarks" rows="1" onBlur="this.value = SpaceReplace(this.value);" onKeyPress="return fun_limitlength(this,'150')"></textarea>
									    </div>  
									   </div>
								   <div class="row"> 
										<button type="button" id="certuploadbtnid" class="btn btn-primary" style="float: right"><b>Upload</b></button>
								   </div>
							  </form>
           					 <%
							}
							else
							{
								%>
									<strong><font color="blue">Your can't upload the certificate due to below reasons</font></strong>
									<ul style="font-weight: bold;color:#000000;">
										<li>Part-B details are not filled</li>
										<li>Person Status is not alive</li>
										<li>Aadhaar Number is not tagged</li>
										<li>Type of disability is not available</li>
									</ul>
								<%
							}
           					 %>
           					 
      								
									</div>
							  </div>
						  </div> 
           					 <button type="button" id="backbtnid" class="btn btn-danger"> Back </button> 
							<%
									} 
							%>
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
 </div> 

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function()
		{
   			document.title = "::SADAREM :: Certificate Upload"; 
   			

		    $("#myTab a").click(function(e)
		    {
		    	e.preventDefault();
		    	$(this).tab('show');
		    });
   			

		    // Back Button function
		    
		    $("#backbtnid").click(function ()
		    		{
		    			location.replace("<%=request.getContextPath()%>/certificatesUpload.do?randomid="+Math.random());
		    		});
		    
		    
		    
   			//SADAREM ID Validation
   			
   		    $("#sadaremid").on('keypress blur change paste cut',function (e) 
   		    		{
   		    		        
   				        	var pattern = /^\d{17}$/;
   				            		        	
   			        		if(pattern.test($(this).val())==false  && $(this).val().length>0)
   		        			{
   			        			 $("#sadaremidErrMsg").addClass("errmsg");
   			  		           	 $("#sadaremidErrMsg").html("Not Valid").show();
   		        			}
   			        		else
   		        			{
   			        			 $("#sadaremidErrMsg").removeClass("errmsg");
   			  		           	 $("#sadaremidErrMsg").html("").show();
   		        			}
   		     	  });
   			
   			
   			
   			
   			$("#searchbtnid").click(function()
   			{
   				try
   				{
   				if($("#sadaremid").val()=="" || ($("#sadaremid").val()).length!=17)
   					{
   						alert("Please enter SADAREM ID");
   						$("#sadaremid").focus();
   						return false;
   					}
   				else
   					{
   					
   					/*Screen Locking Started */
		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
	      			/*Screen Locking Ended */
   					
   						
   						document.searchCertuploadForm.target="_self";
				  		document.searchCertuploadForm.action="<%=request.getContextPath()%>/certificatesUpload.do?action=getuploadedcertificatebasedtls";
				  		document.searchCertuploadForm.submit();
   					}
   				}
   				catch(e)
   				{
   					alert(e);
   				}
   			});
   			
   			
   			$("#certuploadbtnid").click(function ()
   			{
   			 
   				if($("#cert_file").val()=="")
				{
					alert("Please upload certificate");
					$("#cert_file").focus();
					return false;
				}
   				else if("<%=cert_elgible_status.trim().toUpperCase()%>"=="ELIGIBLE" && $("#idcard_file").val()=="")
				{
					alert("Please upload IDCard");
					 $("#idcard_file").focus();
					return false;
				}
   				else if($("#upload_remarks").val()=="" || ($("#upload_remarks").val()).length<15)
				{
					alert("Please enter remarks minimum 15 characters");
					$("#upload_remarks").focus();
					return false;
				}
   				else
   				{ 
					/*Screen Locking Started */
		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
      				/*Screen Locking Ended */ 
						
					document.certuploadForm.target="_self";
			  		document.certuploadForm.action="<%=request.getContextPath()%>/certificatesUpload.do?action=uploadsadaremcerts&randomid="+Math.random();
			  		document.certuploadForm.submit();
   				}
   			});
   			
		});

</script>			
							
	</body>
</html>
