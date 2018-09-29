<%@ page language="java" import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	ArrayList dataList = (ArrayList) request.getAttribute("resultList");
	String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));

	ArrayList issueTypeList = new ArrayList();
	String selIssueType = "", sadaremId = "";
	String captchaCode = CommonUtility.checkNullObj(request.getAttribute("captchaCode"));
	String code        =  CommonUtility.checkNullObj(request.getAttribute("code"));
	selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType"));

	ArrayList mandalList = new ArrayList();
	ArrayList panchayatList = new ArrayList();
	ArrayList villageList = new ArrayList();
	ArrayList habitationList = new ArrayList();
	ArrayList relationTypeList = new ArrayList();
	ArrayList educationList = new ArrayList();
	ArrayList proofTypeList = new ArrayList();

	if (request.getAttribute("issueTypeList") != null)
		issueTypeList = (ArrayList) request
				.getAttribute("issueTypeList");

	if (request.getAttribute("proofList") != null)
		proofTypeList = (ArrayList) request.getAttribute("proofList");
	

	ArrayList tempList = null;

	/* if(issueTypeList!=null){

	 tempList= new ArrayList();


	 proofTypeList.add("1");
	 proofTypeList.add("Aadhar card");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("2");
	 proofTypeList.add("Pan card");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("3");
	 proofTypeList.add("SSC/CBSE marks memo");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("4");
	 proofTypeList.add("Birth Certificate");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("5");
	 proofTypeList.add("Passport");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("6");
	 proofTypeList.add("Driving Licences");
	 tempList.add(proofTypeList);

	 proofTypeList = new ArrayList();
	 proofTypeList.add("7");
	 proofTypeList.add("Voter card");
	 tempList.add(proofTypeList);

	 } */

	 educationList = (ArrayList) request
			.getAttribute("educationList");
		relationTypeList = (ArrayList) request
				.getAttribute("relationTypeList");
	mandalList = (ArrayList) request.getAttribute("mandalList");
	panchayatList = (ArrayList) request.getAttribute("panchayatList");
	villageList = (ArrayList) request.getAttribute("villageList");
	habitationList = (ArrayList) request.getAttribute("habitationList");

	String selmandal = "";
	String selpanchayat = "";
	String selvillage = "";
	String selhabitation = "";
	String distId = "";

	if (dataList != null && dataList.size() == 1) {
		dataList = (ArrayList) dataList.get(0);
		selmandal = (String) dataList.get(14);
		//selpanchayat   = (String)basicData.get(3);
		selvillage = (String) dataList.get(15);
		selhabitation = (String) dataList.get(16);
		distId = CommonUtility.checkNullObj(dataList.get(13));
%>
<form name="openissue"  method="post" enctype="multipart/form-data">
<div id="sadaremDetailsDIVID"
	style="margin: 20px;"
	oncontextmenu="return false;" ondragstart="return false"
	onselectstart="return false">
 
    <div class="row ">
     <div class="col-sm-12 " style="text-align: center; color: blue;">
      <b style="font-size: 14px; font-style: normal; padding: 5px;">Raise New Grievance</b><br />
     </div>
   </div>
	<div class="panel panel-primary"
		style="padding: 10px;">
		 <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" style="cursor: pointer;"> <b>Person Basic Details</b></div>
		 <div id="collapseTwo" class="panel-collapse in">
		<div id="sectionA" class="panel-body">
			<div class="row">
				<div class="col-md-5">
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">SADAREM
									ID</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(0)%></span>
								<input type="hidden" name="sadaremId" id="sadaremId" value="<%=dataList.get(0)%>">
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Person
									Name (Surname Name)</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(1).toString().trim()%>&nbsp;<%=dataList.get(2).toString().trim()%>
								</span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Date
									of Birth (DD/MM/YYYY)</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(4).toString().trim()%>
								</span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Gender</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(5).toString().trim()%></span>
							</div>
						</div>

                        <div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Relationship
									Details</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(3).toString().trim()%></span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Person
									Status</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(6).toString().trim()%>
								</span>
							</div>
						</div>
					
					</div>
			  <div class="col-md-5">
                   <div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">House No</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(8)%></span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Habitation</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(12)%></span>
							</div>
						</div>
                       <div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Village</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(11)%></span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Mandal</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(10)%></span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">District</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(9)%></span>
							</div>
						</div>
						<div class="form-group" style="height: 30px; padding-top: 5px;">
							<div class="input-group">
								<div class="input-group-addon"
									style="background-color: #37ADB6; color: #fff; text-align: left;">Contact No.</div>
								<span class="input-group-addon" style="text-align: left;"><%=dataList.get(7).toString().trim()%>&nbsp;</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>

	<div class="panel panel-primary"
		style="padding: 10px;">
		 <div class="panel-heading"> <b>Contact Details</b></div>
		 <div class="panel-body">
		  <div class="row">
		  <div class="col-md-4" style="padding-top: 5px;" >
					<div class="input-group">
						<div class="input-group-addon"
							style="background-color: #eee; color: #000; font-weight: bold;">Contact No <span style="color:red">*</span>
							</div>
						<input type="text" class="form-control " id="contid"
							name="contid" onkeypress="return NumbersOnly(event);"
							onBlur="this.value = SpaceReplace(this.value);"
							autocomplete="off" maxlength="10"> <span
							class="input-group-addon" id="contidErrMsg"></span>
					</div>
			</div>
		  <div class="col-md-4" style="padding-top: 5px;" >
		   <div class="input-group">
						<div class="input-group-addon"
							style="background-color: #eee; color: #000; font-weight: bold;">Alternate Contact No
							</div>
						<input type="text" class="form-control " id="altcontid"
							name="altcontid" onkeypress="return NumbersOnly(event);"
							onBlur="this.value = SpaceReplace(this.value);"
							autocomplete="off" maxlength="10"> <span
							class="input-group-addon" id="altcontidErrMsg"></span>
					</div>
		  </div>
		  <div class="col-md-3" style="padding-top: 5px;" >
		    <div class="input-group">
						<div class="input-group-addon"
							style="background-color: #eee; color: #000;  font-weight: bold;">Email Id
							</div>
						<input type="text" class="form-control " id="emailid"
							name="emailid"
							onBlur="this.value = SpaceReplace(this.value);"
							autocomplete="off"> <span
							class="input-group-addon" id="emailidErrMsg"></span>
					</div>
		  </div>
		 </div>
	</div>
	
	
</div>
<div class="panel panel-primary"
		style="padding: 10px;">
		 <div class="panel-heading"> <b>Grievance Details</b></div>
		 <div class="panel-body">
		  <div class="row">
		   <div class="col-md-4" style="margin:0px auto; float: none; padding-top: 5px;" >
		   <div class="form-group">
			<div class="input-group" title="Issue Type">
			<div class="input-group-addon" style="text-align:left;  background-color: #2E9BD8; color:#fff;"><span class="glyphicon glyphicon-list-alt"></span> Grievance Type</div> 
			<%=ComboUtil.createStrComboBoxAuto("issueType",
						issueTypeList, selIssueType, "form-control", "", true,
						true, "")%>
			</div>
			</div>
	  </div>
		 
   </div>

			<div class="row">
				<div id="nameChange" class="col-md-7 hide">
                  
					<div class="panel panel-primary">
						<div class="panel-heading" style="text-align: center;">
							<h3 class="panel-title">Name Correction</h3>
						</div>
						<div class="panel-body" style="background-color: #eee;">
							<div class="form-group row">
								<div class="input-group col-md-12" title="First Name">
									<div class="input-group-addon"
										style="background-color: #2E9BD8; color: #fff;">
										First Name <font color='red'>*</font>
									</div>
									<input type="text" name="fname" id="fname" class="form-control"
										autocomplete="off" value='<%=dataList.get(2)%>'
										onBlur="this.value = SpaceReplace(this.value);"
										onkeypress="return onKeyPressAlphbateOnly(event);"
										maxlength="150">
								</div>
							</div>
						<div id="proof_namechange" class="proof">
							
						</div>                     
							
							
						</div>
					</div>
				</div>
				
				
				<div id="genderchange" class="col-sm-7 hide">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Gender Correction
					                    </h3>
					                </div>
					             
					                 <div class="panel-body" style="background-color: #eee;">
											 <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Gender">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > Gender <font color='red'>*</font> </div> 
					                             <span class="input-group-addon"> <%=CommonUtility.checkNullObj(dataList.get(5)).equals(
						"Male") ? "Female" : "Male"%></span>
					                                <input type="hidden" name="genderId" id="genderId" class="form-control" autocomplete="off"  value='<%=CommonUtility.checkNullObj(dataList.get(5)).equals(
						"Male") ? "2" : "1"%>'>  
					                                <input type="hidden" name="gender" id="gender" class="form-control" autocomplete="off"    value='<%=CommonUtility.checkNullObj(dataList.get(5)).equals(
						"Male") ? "Female" : "Male"%>'>
					                             </div>
					                             
					                           </div>
					                           
                                                 <div id="proof_genderchange" class="proof">
                                                
                                                 </div>
                              
					              </div>
					        </div>
		            </div>
		            
		            <div id="addresschange" class="col-sm-7 hide">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Address Details Correction
					                    </h3>
					                </div>
					                <div class="panel-body " style="background-color: #eee;">
					                
											 
					                             <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Mandal">    
					                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;">Mandal <font color='red'>*</font></div> 
                                                     <%=ComboUtil.createStrComboBoxAuto("mandals",
						mandalList, selmandal, "form-control", "", true, true,
						"")%>
					                             </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Panchayat">    
					                               <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Panchayat <font color='red'>*</font></div> 
					                                <div id="panchayatTDID">
                                                      <%=ComboUtil.createStrComboBoxAuto("panchayats",
						panchayatList, selIssueType, "form-control",
						"onchange=loadvillages()", true, true, "")%>
                                                    </div> </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Village">    
					                             <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Village <font color='red'>*</font></div> 
					                                 <div id="villageTDID">
					                                 <%=ComboUtil.createStrComboBoxAuto("villages",
						villageList, selvillage, "form-control",
						"onchange=loadhabitation()", true, true, "")%> 
                                                    </div>  </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Habitation">    
					                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Habitation <font color='red'>*</font></div> 
					                                <div id="habitationTDID">
					                                <%=ComboUtil.createStrComboBoxAuto("habitation",
						habitationList, selhabitation, "form-control", "",
						true, true, "")%>
                                                    </div>  </div>
					                            </div>
					                            
					                            
					                           <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="House No">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > House No. <font color='red'>*</font> </div> 
					                                <input type="text" name="houseno" id="houseno" class="form-control" autocomplete="off" onChange="stripHTML(this);"   value='<%=dataList.get(8)%>'  maxlength="50">  
					                             </div>
					                             
					                           </div>
					                           
					                           <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Pin Code">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > PIN Code <font color='red'>*</font> </div> 
					                                <input type="text" name="pincode" id="pincode" class="form-control" autocomplete="off"    value='<%=dataList.get(17)%>'   onkeypress="return NumbersOnly(event);"   maxlength="6">  
					                             </div>
					                             
					                           </div> 
					                            
                                                 <div id="proof_addresschange" class="proof">
                                                 
                                                 </div>
                                                 
					             </div>
					        </div>
		            </div>
		            
				<div id="agecorrection" class="col-sm-7 hide" >
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Age Correction
					                    </h3>
					                </div>
					             
					                 <div class="panel-body" style="background-color: #eee;">
											 <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="DOB">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > Date of Birth <font color='red'>*</font> </div>
					                              
					                                <input type="text"  id="dob"  name="dob" class="form-control" value='<%=dataList.get(4)%>'  style="border:solid 1px #609ebf;" size="10" readonly="readonly"/> 
					                             <div class="input-group-addon" style="color:red;" id="ageVal" ></div>
					                             </div>
					                             
					                           </div>                  
					                            
                                                 <div id="proof_agechange" class="proof">
                                                  
                                                 </div>
                                                 
					              </div>
					        </div>
		            </div>
				
				<div id="relchange" class="col-sm-7 hide">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Relation Details Correction
					                    </h3>
					                </div>
					             
					                 <div class="panel-body" style="background-color: #eee;">
											 <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Relation Name">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > Relation Name <font color='red'>*</font> </div> 
					                                <input type="text" name="relationname" id="relationname" class="form-control" autocomplete="off"    value='<%=dataList.get(19)%>' onBlur="this.value = SpaceReplace(this.value);"  onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="50">  
					                             </div>
					                             
					                           </div>
					                             <div class="form-group row"> 
					                            <div class="input-group  col-md-12" title="Relation Type">    
					                               <%-- <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;"> Relation Type <font color='red'>*</font> </div> 
					                                <input type="text" name="fname" id="fname" class="form-control"  autocomplete="off" value='<%=basicData.get(16)%>' onBlur="this.value = SpaceReplace(this.value);" onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="150">
					                               --%> 
					                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; " value='<%=(String) dataList.get(18)%>'> Relation Type <font color='red'>*</font></div> 
					                              <%=ComboUtil.createStrComboBoxAuto("relationType",
						relationTypeList, (String) dataList.get(20),
						"form-control", "", true, true, "")%>  
					                             </div>
					                            </div>
					                            
                                                 <div id="proof_relchange" class="proof">
                                                 
                                                 </div>
                                                 
					              </div>
					              
					              
					        </div>
		            </div>
				
				
		            
		            <div id="qualificationchange" class="col-sm-7 hide">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Qualification Details Correction
					                    </h3>
					                </div>
					             
					                 <div class="panel-body" style="background-color: #eee;">
											 
					                             <div class="form-group row"> 
					                            <div class="input-group  col-md-12" title="Qualification Type">    

					                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; " value=""> Qualification <font color='red'>*</font></div> 
					                              <%=ComboUtil.createStrComboBoxAuto("educationType",educationList, (String) dataList.get(21),"form-control", "", true, true, "")%>  
					                             </div>
					                            </div>
					                            
                                                 <div id="proof_qualificationchange" class="proof">
                                                 
                                                 </div>
                                                 
					              </div>
					              
					              
					        </div>
		            </div>

				<div id="Instructions" class="col-md-4 hide">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-check"></span> Instructions
							</h3>
						</div>
						<div class="panel-body"
							style="background-color: #eee; text-align: left; line-height: 25px; font-size: 14px; font-family: Times New Roman;">
							<ol>
								<li>All fields marked as (<font color='red'>*</font>) are
									mandatory
								</li>
								<li>Please avoid using special characters like <font
									color='blue' size="3"><b>',",<,></b></font> in description
									field
								</li>
								<li>Only .pdf file type is accepted for document</li>
							</ol>
						</div>
					</div>
				</div>
           </div>
           <div class="row">
			<div  style="margin:0px auto;text-align:center;float:none;" class="col-md-4">
				<div id="otpreturnmsg" class="input-group" style="float: none;padding:3px; text-align: center;margin: 0px auto;">
				</div>
				
			</div>


		</div>
		<div class="row">
			<div id="otpmsg" style="margin:0px auto;text-align:center;float:none;" class="col-md-4 hide">
				<div class="input-group" style="float: none;padding:3px; text-align: center;margin: 0px auto;">
					<button type="button" id="otpbut" class="btn btn-success">
						<b>Generate OTP</b>
					</button>
				</div>
				
			</div>


		</div>
	<div class="row">
			<div id="submitBut" style="margin: 0px auto !important;text-align: center;float:none;padding: 5px;" class="col-md-4 hide">
			<div class="input-group">
						<div class="input-group-addon" style="background-color: #eee; color: #000; font-weight: bold;">Enter Generated OTP here<span style="color:red">*</span>
							</div>
						<input type="password" class="form-control " id="otpid" name="otpid" onkeypress="return NumbersOnly(event);" onblur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="6"> 
						<span class="input-group-addon" id="otpErrMsg"></span>
					</div>
                
                <div class="input-group" style="float: none;padding:3px; text-align: center;margin: 10px auto;">
				 <span class="input-group-addon">
				  <i class="glyphicon glyphicon-barcode"></i>
				  <logic:present name="captchaCode">
	               <font color="blue" size="3"><b><%=captchaCode%></b></font>
	               </logic:present>
                   </span>
			    <input type="text" class="form-control" name="txtInput" id="txtInput"   autoComplete="off" onkeypress="return onlyNumbers(event);" maxlength="5">
				</div>  
				
                <div class="input-group" style="float: none;padding:3px; text-align: center;margin: 10px auto;">
					<input type="button" id="submitButton" name ="submitButton" value ="Submit" class="btn btn-success" style="float: none;margin: 0px auto;">
					 
				</div>
			</div>


		</div>

			</div>
</div>
<input type="hidden" name="distId" id="distId" value="<%=CommonUtility.checkNullObj(request.getAttribute("distId"))%>">
<input type="hidden" id="method" name="method" value="">
<input type="hidden" id="FormSessionID" name="FormSessionID" value="<%=FormID%>" />
<%
 if (captchaCode != null && captchaCode.length() > 0) 
{
%>
<input type="hidden" id="txtCaptcha" name="txtCaptcha" value="<%=code%>"/>
<%
}
%>

</form>
<!--<script type="text/javascript">

function getProofs(){
	return "<div class='form-group row'>"+ 
    "<div class='input-group col-md-12' title='Remarks'>"+ 
     "<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Remarks<font color='red'>*</font></div>"+
           "<textarea class='form-control' rows='2' id='decription' name='decription'  style='width:100%;height:100% ' onBlur='this.value = SpaceReplace(this.value);' onChange='stripHTML(this);'  maxlength='200' ></textarea>"+
     "</div>"+
     "</div>"+
	 "<div class='form-group row'><div class='input-group col-md-6' title='Proof of Identity' style= 'float:left; padding:5px;'> "+
	"<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Proof of Identity<font color='red'>*</font></div>"+
	"<%=ComboUtil.createStrComboBoxAuto("proofidentity_1",proofTypeList, "", "form-control", "", false,false, "")%>"+
	"</div><div class='input-group col-md-5' title='ProofId' style= 'float:left; padding:5px;'>"+
	"<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Proof Id<font color='red'>*</font></div>"+
	"<input type='text' name='proofid_1' id='proofid_1' class='form-control' autocomplete='off'  maxlength='30'  onblur=validate('proofidentity_1','proofid_1')>  "+
	"</div>"+
	"</div>"+
	"<div class='form-group row'> "+
	"<div class='input-group col-md-6' title='Document' style= 'float:left; padding:5px;'> "+
	"<div class='input-group-addon'  style='background-color: #2E9BD8; color:#fff; '> "+
	"<span class='glyphicon glyphicon-paperclip'>Document</span><font color='red'>*</font>"+
	"</div>"+
	"<input type='file' class='form-control'   id='proofDoc_1' name='proofDoc_1'>			                "+
	"</div>"+
	"</div>"+
	"<div class='form-group row'>"+
	"<div class='input-group col-md-6' title='Proof of Identity' style= 'float:left; padding:5px;'> "+
	"<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Proof of Identity<font color='red'>*</font></div>"+
	"<%=ComboUtil.createStrComboBoxAuto("proofidentity_2",proofTypeList, "", "form-control", "", false,false, "")%>"+
	"</div>"+
	"<div class='input-group col-md-5' title='ProofId' style= 'float:left; padding:5px;'> "+
	"<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Proof Id<font color='red'>*</font></div>"+
	"<input type='text' name='proofid_2' id='proofid_2' class='form-control' autocomplete='off' maxlength='30' onblur=validate('proofidentity_2','proofid_2')>   "+
	"</div>"+
	"</div>"+
	"<div class='form-group row'> "+
	"<div class='input-group col-md-6' title='Document' style= 'float:left; padding:5px;'> "+
	"<div class='input-group-addon'  style='background-color: #2E9BD8; color:#fff; '> "+
	"<span class='glyphicon glyphicon-paperclip'>Document</span><font color='red'>*</font>"+
	"</div>"+
	"<input type='file' class='form-control'   id='proofDoc_2' name='proofDoc_2'>			                "+
	"</div>"+
	"</div>";
}
</script>
-->
<script type="text/javascript">

function getProofs(){
	return "<div class='form-group row'>"+ 
    "<div class='input-group col-md-12' title='Remarks'>"+ 
     "<div class='input-group-addon' style='background-color: #2E9BD8; color:#fff; '> Remarks<font color='red'>*</font></div>"+
           "<textarea class='form-control' rows='2' id='decription' name='decription'  style='width:100%;height:100% ' onBlur='this.value = SpaceReplace(this.value);' onChange='stripHTML(this);'  maxlength='200' ></textarea>"+
     "</div>"+
     "</div>"+
     "<div class='panel panel-primary' style='padding: 10px;'>"+
 	 "<div class='panel-heading'> <b>Please Upload Documents</b></div>"+
 	 "<div class='panel-body'>"+
     "<div class='form-group row'>"+
     "<table>"+
      "<tr style='border: 1px solid #337ab7;'>"+
	   "<th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>S no<font color='red'>*</font></th>"+
	   "<th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>Proof Type<font color='red'>*</font></th>"+
	   "<th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>Proof ID<font color='red'>*</font></th>"+
	   "<th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>File<font color='red'>*</font></th>"+
	  "</tr>"+
	  "<tr>"+
	  "<td style='border: 1px solid #337ab7;text-align: center;font-weight: bold;'>1<font color='red'>*</font></td>"+
	  "<td style='border: 1px solid #337ab7;'>"+
	  "<%=ComboUtil.createStrComboBoxAuto("proofidentity_1",proofTypeList, "", "form-control", "", false,false, "")%>"+
	  "</td>"+
	  "<td style='border: 1px solid #337ab7;'><input type='text' name='proofid_1' id='proofid_1' class='form-control' autocomplete='off'  maxlength='30'  onblur=validate('proofidentity_1','proofid_1')></td>"+
      "<td style='border: 1px solid #337ab7;'><input type='file' class='form-control'   id='proofDoc_1' name='proofDoc_1'></td>"+
	  "</tr>"+
      "<tr style='border: 1px solid #337ab7;'>"+
	  "<td style='border: 1px solid #337ab7;text-align: center;font-weight: bold;'>2<font color='red'>*</font></td>"+
	  "<td style='border: 1px solid #337ab7;'>"+
	  "<%=ComboUtil.createStrComboBoxAuto("proofidentity_2",proofTypeList, "", "form-control", "", false,false, "")%>"+
	  "</td>"+
	  "<td style='border: 1px solid #337ab7;'><input type='text' name='proofid_2' id='proofid_2' class='form-control' autocomplete='off' maxlength='30' onblur=validate('proofidentity_2','proofid_2')></td>"+
	  "<td style='border: 1px solid #337ab7;'><input type='file' class='form-control'   id='proofDoc_2' name='proofDoc_2'></td>"+
	  "</tr>"
	 "</table>"+
    "</div>"+
    "</div>"+
    "</div>"+
    "</div>";
}
</script>



<%
	}
%>
