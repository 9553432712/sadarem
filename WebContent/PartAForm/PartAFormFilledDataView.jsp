 <%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList" %> 
<%@page import="java.util.HashMap,com.tcs.sadarem.util.CommonUtility"%> 
 
<%
 
	HashMap myRequestListDtls = (HashMap)request.getAttribute("PartARequestFullDltsList");
%>  
 <div class="panel  panel-success">  
	 <div class="panel-heading">
	     <h4 class="panel-title"><b>Part-A Details of Request Id: <%=CommonUtility.checkNullObj(myRequestListDtls.get("request_id")) %></b></h4>
	 </div>   
	  <div class="panel-body alert-info"> 
 				    
			 	<div class="row"> 
				 <div class="col-md-4">
						<div class="panel  panel-primary">  
							 <div class="panel-heading">
							     <h4 class="panel-title">Contact Details</h4>
							 </div>   
							  <div class="panel-body"> 
							  
						 				 		<div class="form-group" style="padding-top:5px;">
						                          <div class="input-group">
						                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> District</div>
											  		<div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("district_name")) %></div> 
						                          </div>
						                        </div> 
						            
						 				 		<div class="form-group" style="padding-top:5px;">
						                          <div class="input-group">
						                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Mandal</div>
													  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("mandal_name")) %></div> 
						                          </div>
						                        </div>
						                        
						  						<div class="form-group" style="padding-top:5px;">
							    			 		<div class="input-group">
												 		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Panchayat</div>
														<div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("panchayat_name")) %></div>
						                			</div>
						                		</div>
						                			 
						  						<div class="form-group" style="padding-top:5px;">
						         					<div class="input-group">
														<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Village</div>
														<div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("village_name")) %></div>
													</div> 
						                        </div>
						                        
								  				<div class="form-group" style="padding-top:5px;">
									    			 <div class="input-group">
														 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Habitation/Ward No</div>
														 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("habitation_name")) %></div>
								                     </div>
								                </div> 
						                        
						  				<div class="form-group" style="padding-top:5px;">
							    			 <div class="input-group">
												 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> House No.</div>
												 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("house_number")) %></div>
							                 </div>
							            </div> 						 
						                     	 
										   
						  				<div class="form-group" style="padding-top:5px;">
							    			 <div class="input-group">
												 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Pin Code</div>
											 	 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("pin_code")) %></div>
							                 </div>
							            </div>
									
										<div class="form-group" style="padding-top:5px;">
							    			 <div class="input-group">
												 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Contact No.</div>
						                        <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("phone_no")) %></div>
											 </div>
									   </div>
									
										<div class="form-group" style="padding-top:5px;">
							    			 <div class="input-group">
												 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">eMail Id</div> 
						             			 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("email")) %></div>
						             		 </div> 
						               </div>
						               <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#partASupportDocModal" style="background-color:#29bfaa; color:#FFFFFF;font-weight:bold;">Click to View Supporting Documents</button>	
						         </div> 
						     </div> 
				 </div> 
				 <div class="col-md-8">  
					 <div class="panel  panel-primary">  
						<div class="panel-heading">
				            	<h4 class="panel-title">Personal Details</h4>
						 </div>   
				           <div class="panel-body">
				           
				           
								           	<div class="form-inline" style="padding-top:5px;"> 
								                 <div class="input-group col-md-2"> 
												      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">
													        <div class="center-block">
																<div class="img_circular" style="background-size: cover;width:150px;height: 150px;"> 
																	<img style="width:100%; height:100%" src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&requestID=<%=CommonUtility.checkNullObj(myRequestListDtls.get("request_id")) %>&randiomid=<%=Math.random()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultprofile.png'"/>
																 </div>
															</div>
													 </div>
												</div>
										      <div class="input-group col-md-9" >
							           				<div class="form-inline">
								           				<div class="input-group">
											      		  <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
												   		  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("sadarem_id")) %></div>
											       		</div>
											       		 
								           				<div class="input-group">
											      		  <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Aadhaar Card No</div>
												   		  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("proof_id")) %></div>
											       		</div>
											       		
										      		</div> 
							           				<div class="form-inline" style="padding-top:10px;">
							           															       		  
								           				<div class="input-group">
											      		  <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Form No.</div>
												   		  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("reference_form_number")) %></div>
											       		</div>
											       		
								           				<div class="input-group">
											      		  <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Assessment</div>
												   		  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("form_filled_date")) %></div>
											       		</div>
											       		
										                 <div class="input-group">
										                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Sur-Name</div>
														   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("surname")) %></div>
										                 </div>
										              </div>
										              
								           			<div class="form-inline" style="padding-top:10px;">
								           			  
										                 <div class="input-group">
										                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Person Name</div>
										 				   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("first_name")) %></div>
										            	</div> 
										            	
										            	 <div class="input-group">
										                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Gender</div>
										 				  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("gender_name")) %></div>
										                 </div> 
										            	
										      		</div> 
										  	 </div> 
										</div>
								           	<div class="form-inline" style="padding-top:5px;">  
								                 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Date of Birth</div>
												   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("date_of_birth")) %></div> 
								                 </div>
								                 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Full Name (Telugu)</div>
												   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("personname_telugu")) %></div>
								                 </div> 
								                 
								            </div>
												            	  
											
								           	<div class="form-inline" style="padding-top:5px;"> 
								                 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Relation Details</div>
								 				  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("relationship_type_desc")) %>&nbsp;<%=CommonUtility.checkNullObj(myRequestListDtls.get("relation_name")) %></div>
								                 </div>  
								                 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Relation Name(Telugu)</div>
												   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("relationname_telugu")) %></div>
								                 </div>
								                 
								            </div>
								             
											
								           	<div class="form-inline" style="padding-top:5px;"> 
												
								                 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Marital Status</div>
								                   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("marital_status_desc")) %></div>
												</div>  
												
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><span class="glyphicon glyphicon-education" style="font-size: 20px;"></span> Education</div>
								                  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("edu_desc")) %></div>
												</div> 
												
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Employment</div>
								                  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("employement_type_desc")) %></div>
												</div> 
												
								            </div> 		  
															 
											 <div class="form-inline" style="padding-top:5px;">  
											 
								                 <div class="input-group col-md-4">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Religion</div>
								                  <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("religion_desc")) %></div>
												</div>  
												
								                 <div class="input-group col-md-3">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Caste</div>
								                   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("caste_desc")) %></div>
												</div> 
											 </div>			  
															 
											 <div class="form-inline" style="padding-top:5px;">  
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card No</div>
								                   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_number")) %></div>
												</div> 
												
								                 <div class="input-group">
								                   	<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card Type</div>
								                 	<div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_type_desc")) %></div>
												 </div> 
												
								                  <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Slno</div>
								                 	 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_slno")) %></div>
												 </div> 
											 </div> 
															 
											 <div class="form-inline" style="padding-top:5px;">  	 
								                 <div class="input-group">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Identification Marks</div>
								                   <div class="input-group-addon">1)</div>
								                   <textarea class="form-control" rows="1" cols="40"><%=CommonUtility.checkNullObj(myRequestListDtls.get("mole_one")) %></textarea>
								                   <div class="input-group-addon">2)</div>
								                    <textarea class="form-control" rows="1" cols="40"><%=CommonUtility.checkNullObj(myRequestListDtls.get("mole_two")) %></textarea>
												 </div>
											 </div>
													  
															 
											 <div class="form-inline" style="padding-top:5px;">  
								                <div class="input-group col-md-6">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Pension</div>
								                   	 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("pension_type")) %></div>
												</div> 
												
								                 <div class="input-group col-md-5">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Pension Number</div>
								                   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("pensioncard_no")) %></div>
												</div> 
											 </div>										  
															 
											 <div class="form-inline" style="padding-top:5px;">  
												  
								                 <div class="input-group col-md-6">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Consanguineous Marriage of Parents</div>
								                 <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("parents_marriage_desc")) %></div>
												</div>  
												
								                 <div class="input-group col-md-5">
								                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">EPIC Card</div>
								                   <div class="form-control"><%=CommonUtility.checkNullObj(myRequestListDtls.get("epiccard_no")) %></div>
												</div> 
											 </div> 
							 			</div>
					 				</div>
							</div>		
			 </div>
			  <%
				 if(CommonUtility.checkNullObj(myRequestListDtls.get("req_status")).equalsIgnoreCase("P"))
				 {
				 %>
					 <div class="row"> 
					 	<form id="parta_decision_form" name="parta_decision_form" method="post">
					 		<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId")) %>">
							 <div class="form-inline">   
				                 <div class="input-group col-md-6">
				                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font>&nbsp;Remarks</div>
				                   <textarea rows="1" cols="60" id="status_remarks" name="status_remarks" class="form-control" onKeyPress="return fun_limitlength(this,'200')" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = SpaceReplace(this.value);"></textarea>
								</div>   
								
				                 <div class="input-group col-md-5">
				                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">
									<font color="red">*</font>&nbsp;<input type="checkbox" id="term_status" name="term_status" value="Y"> 
								  </div>
				                   <div  class="form-control">I here by declare that I have verified all the proofs before taking any action.</div>
								</div> 
							 </div>  
						</form>
					 </div>
			 <%
				 }
			 %>
  				<div class="row" style="text-align: center;padding-top:10px;"> 
					 <button type="button" id="BackBtnID" class="btn btn-warning" style="float:left;"><b>Back</b></button>
					 <%
					 if(CommonUtility.checkNullObj(myRequestListDtls.get("req_status")).equalsIgnoreCase("P"))
					 {
					 %>
					 <button type="button" id="EditPartABtnID" class="btn btn-primary"><b>Edit</b></button>
					 <button type="button" id="ApprovePartABtnID" class="btn btn-success"><b>Approve</b></button>					 
					 <button type="button" id="RejectPartABtnID" class="btn btn-danger" style="float:right;"><b>Reject</b></button>
					 <%
					 }
					 %>
				</div>
	</div>
</div> 
  
</body>

</html>
