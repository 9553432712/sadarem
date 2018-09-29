<%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.HashMap" %> 
<!DOCTYPE html>
<html>
<%
try
{ 
%>
<head>
<title>:: SADAREM :: Part-A Form </title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>  
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
<% 

HashMap partADetailsList = (HashMap)request.getAttribute("PartARequestFullDltsList"); 

String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
String telugu 	  = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";            
 
String districtid 	= CommonUtility.checkNullObj(session.getAttribute("districtId"));
String districtName = CommonUtility.checkNullObj(session.getAttribute("logDistName"));  
String selAadhaarId	= CommonUtility.checkNullObj(session.getAttribute("ses_AadhaarId"));
String currentDate 	= CommonUtility.getDateTime("dd/MM/yyyy");

ArrayList mandalList 			= (ArrayList)request.getAttribute("mandalList");
ArrayList panchayatList 		= (ArrayList)request.getAttribute("panchayatList");
ArrayList villageList 			= (ArrayList)request.getAttribute("villageList");
ArrayList habitationList 		= (ArrayList)request.getAttribute("habitationList"); 
 
 
 
 ArrayList genderList 			= (ArrayList)request.getAttribute("genderList");
 ArrayList proofTypeList		= (ArrayList)request.getAttribute("proofTypeList");
 ArrayList CasteList			= (ArrayList)request.getAttribute("CasteList");
 ArrayList EducationList		= (ArrayList)request.getAttribute("EducationList");
 ArrayList EmploymentList		= (ArrayList)request.getAttribute("EmploymentList");
 ArrayList MaritalStatusList	= (ArrayList)request.getAttribute("MaritalStatusList");
 ArrayList religionList			= (ArrayList)request.getAttribute("religionList");
 ArrayList rationCardTypeList	= (ArrayList)request.getAttribute("rationCardTypeList");
 ArrayList relationTypeList		= (ArrayList)request.getAttribute("relationTypeList");
 ArrayList pensionTypeList		= (ArrayList)request.getAttribute("pensionTypeList");
 
 
 
  ArrayList tempList 			= new ArrayList();
  tempList.add("PR001");
  tempList.add("Aadhar Card");

	if(proofTypeList.indexOf(tempList)!=-1)
	{  
		proofTypeList.remove(tempList);
	}

%>
  <style type="text/css">
    
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

/* 	 Process Layer Ended  */
  
	</style> 
</head> 
<body>  
    
<div id="processlayer" >
	<font color="blue" size="2">Processing Please Wait...</font><br/>
	<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
</div>

<div id="blocklayer">
</div>  
 <form  method="post" name="part_a_edit_form" id="part_a_edit_form" method="post" > 
<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId")) %>"  readonly="true"/> 
 <div class="panel  panel-success">  
 
				 <div class="panel-heading">
				   <b>Part-A Details of Request Id: <%=CommonUtility.checkNullObj(partADetailsList.get("request_id")) %></b>
				 </div>   
				  <div class="panel-body alert-info">  
					
					<div class="alert alert-warning">
						 <div class="form-inline" style="padding-top:5px;">   
				                 <div class="input-group col-md-3">
				                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Aadhaar Card No</div>
								   <div class="form-control"><%=CommonUtility.checkNullObj(partADetailsList.get("proof_id")) %></div>
				                 </div>
				                 
				                 <div class="input-group col-md-3">
				                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Form No.</div>
				 				   <input type="text" class="form-control" id="reference_form_number" name="reference_form_number" autocomplete="off" maxlength="25" value="<%=CommonUtility.checkNullObj(partADetailsList.get("reference_form_number")) %>" onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
				                 </div> 
				                 
				                 <div class="input-group col-md-3">
				                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Date of Assessment</div>
				 				   <input type="text" class="form-control " id="form_fill_date" name="form_fill_date" value="<%=CommonUtility.checkNullObj(partADetailsList.get("form_filled_date")) %>" readonly>
				 				    <div class="input-group-addon">
								   	<span class="glyphicon glyphicon-calendar"></span>
								   </div>
				                 </div>
				            </div>
			            </div>  
					</div>
			
  					 
 	<div class="row"> 
	 <div class="col-md-4">
			<div class="panel  panel-primary">  
				 <div class="panel-heading">
				     <h4 class="panel-title">Contact Details</h4>
				 </div>   
				  <div class="panel-body">  
			 				 		<div class="form-group" style="padding-top:5px;">
			                          <div class="input-group">
			                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> District</div>
								  		<div class="form-control"><%=CommonUtility.checkNullObj(partADetailsList.get("district_name")) %></div> 
			                          </div>
			                        </div> 
			            
			 				 		<div class="form-group" style="padding-top:5px;">
			                          <div class="input-group">
			                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Mandal</div>
										<div>
											 <%=ComboUtil.createStrComboBoxAuto("mandals",mandalList,CommonUtility.checkNullObj(partADetailsList.get("mandal_id")) ,"form-control","",true,true,"")%>
										 </div>   
			                          </div>
			                        </div>
			                        
			  					<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Panchayat</div>
									 <div id="panchayatTDID">
										<%=ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,CommonUtility.checkNullObj(partADetailsList.get("panchayath_id")),"form-control","onchange='loadvillages()'",true,true,"") %>
                					 </div>
                				 </div>
			                	</div>
			                			 
			  				<div class="form-group" style="padding-top:5px;">
			         			 <div class="input-group">
									<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Village</div>
									<div id="villageTDID" >
			             						 <%=ComboUtil.createStrComboBoxAuto("villages",villageList,CommonUtility.checkNullObj(partADetailsList.get("village_id")),"form-control","onchange='loadhabitation()'",true,true,"")%>
			            			</div>
			                  	</div> 
							</div> 
			                        
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Habitation/Ward No</div>
									 <div id="habitTDID">
				                       	 <%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,CommonUtility.checkNullObj(partADetailsList.get("habitation_id")),"form-control","",true,true,"")%>
				                      </div> 
			                     </div>
			                </div> 
			                        
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> House No.</div>
									 <input type="text" class="form-control"  id="house_number" name="house_number" value="<%=CommonUtility.checkNullObj(partADetailsList.get("house_number")) %>" autocomplete="off" onBlur="this.value = SpaceReplace(this.value);" maxlength="20"  >
				                 </div>
				            </div> 						 
			                     	 
							   
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Pin Code</div>
								 	 <input type="text" id="pin_code" name="pin_code" class="form-control" value="<%=CommonUtility.checkNullObj(partADetailsList.get("pin_code")) %>" autocomplete="off" maxlength="6" onkeypress="return NumbersOnly(event);">
				                 </div>
				            </div>
						
							<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Contact No.</div>
			                         <input type="text" class="form-control ClassValidateContactNo" id="phone_no" name="phone_no" value="<%=CommonUtility.checkNullObj(partADetailsList.get("phone_no")) %>" onkeypress="return NumbersOnly(event);" autocomplete="off" maxlength="10"> 
								 </div>
						   </div>
						
							<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">eMail Id</div> 
			             			 <input type="text" class="form-control" id="email" name="email" value="<%=CommonUtility.checkNullObj(partADetailsList.get("email")) %>" onblur="EmailCheck(this)" autocomplete="off" onBlur="this.value = SpaceReplace(this.value);"  maxlength="75">
			             		 </div> 
			               </div>      		
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
								 		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">
										 	<div class="center-block">
												<div class="img_circular" style="background-size: cover;width:150px;height: 150px;"> 
														<img style="width:100%; height:100%" src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&requestID=<%=CommonUtility.checkNullObj(partADetailsList.get("request_id")) %>&randiomid=<%=Math.random()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultprofile.png'"/>
												 </div>
											</div>
										</div>
								 	</div> 
					           	  <div class="input-group col-md-9" >
					           	  
					           		<div class="form-inline" style="padding-top:5px;">
						                 <div class="input-group">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Sur-Name</div>
										   <input type="text" class="form-control" id="surname" name="surname" value="<%=CommonUtility.checkNullObj(partADetailsList.get("surname")) %>" maxlength="50" value=""  autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();" />
						                 </div>
						                 
						                 <div class="input-group">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> First Name</div>
						 				   <input type="text" class="form-control" id="first_name" name="first_name" value="<%=CommonUtility.checkNullObj(partADetailsList.get("first_name")) %>" maxlength="50" value="" autocomplete="off"  onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();">
						            	</div> 
					            	</div>
					            	
					           		<div class="form-inline" style="padding-top:5px;">
									  	<div class="input-group">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Date of Birth</div>
										   <input type="text" class="form-control" id="date_of_birth"  name="date_of_birth" value="<%=CommonUtility.checkNullObj(partADetailsList.get("date_of_birth")) %>" autocomplete="off" readonly>
										   <div class="input-group-addon">
										   	<span class="glyphicon glyphicon-calendar"></span>
										   </div>
						                </div>
						                 
						                 <div class="input-group">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Gender</div>
						 				   <%=ComboUtil.createStrComboBoxAuto("gender", genderList, CommonUtility.checkNullObj(partADetailsList.get("gender_id")), "form-control", "", true, true, "") %>
						                 </div> 
								  	</div>
								  	
									  <div class="form-inline" style="padding-top:5px;"> 
						                 
						                 <div class="input-group col-md-6">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Type of Relation</div>
						 				   <div id="RelId" class="form-control">	
												<%=ComboUtil.createStrComboBoxAuto("grelation",relationTypeList, CommonUtility.checkNullObj(partADetailsList.get("relationship_id")),"select-optionItem","",true,true,"")%>	 
						                  </div>   
						                 </div> 
						                 
						                 <div class="input-group col-md-5">
						                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Relation Name</div>
										   <input type="text" class="form-control" maxlength="50" value="<%=CommonUtility.checkNullObj(partADetailsList.get("relation_name"))%>" id="relation_name" name="relation_name" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
						                 </div>
						             </div>
						             
						             
								 </div>
							 </div>
								  
								  
					           	<div class="form-inline" style="padding-top:5px;"> 
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=personname%></div>
									   	<input type="text" class="form-control" id="full_name_eng"  name="full_name_eng"  value="<%=CommonUtility.checkNullObj(partADetailsList.get("personname_telugu")) %>" maxlength="300" autocomplete="off"  onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value =SpaceReplace(this.value);" />
										<input type="hidden" class="form-control" id="personname_telugu" name="personname_telugu" value="<%=CommonUtility.checkNullObj(partADetailsList.get("personname_telugu")) %>"/>
					                 </div>
					                 
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=telugu%></div>
					 				   <input type="text" class="form-control" id="disp_personname_telugu" name="disp_personname_telugu" value="<%=CommonUtility.checkNullObj(partADetailsList.get("personname_telugu")) %>" disabled/>
					                 </div> 
					            </div>
									          
					           							  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=fathername%></div>
					                   <input type="text" class="form-control" id="relation_name_eng" name="relation_name_eng"  value="<%=CommonUtility.checkNullObj(partADetailsList.get("relationname_telugu")) %>"  autocomplete="off" maxlength="300"  onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = SpaceReplace(this.value);"/>
					                   <input type="hidden" class="form-control" id="relationname_telugu" name="relationname_telugu"  value="<%=CommonUtility.checkNullObj(partADetailsList.get("relationname_telugu")) %>" />    
									</div>
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=telugu%></div>
					                   <input type="text" class="form-control" id="disp_relation_name" name="disp_relation_name"   value="<%=CommonUtility.checkNullObj(partADetailsList.get("relationname_telugu")) %>"  readonly="true"/>  
									</div> 
								 </div>						  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Education</div>
					                   <%=ComboUtil.createStrComboBoxAuto("education_id",EducationList,CommonUtility.checkNullObj(partADetailsList.get("education_id")) ,"form-control","",true,true,"")%>
					                   <div class="input-group-addon"><span class="glyphicon glyphicon-education" style="font-size: 20px;"></span></div>
									</div> 
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Employment</div>
					                   <%=ComboUtil.createStrComboBoxAuto("employment_id",EmploymentList,CommonUtility.checkNullObj(partADetailsList.get("employment_id")) ,"form-control","",true,true,"")%>
									</div> 
								 </div>				  
												 
								 <div class="form-inline" style="padding-top:5px;">  
									
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Marital Status</div>
					                   <%=ComboUtil.createStrComboBoxAuto("marital_status_id",MaritalStatusList,CommonUtility.checkNullObj(partADetailsList.get("marital_status_id")),"form-control","",true,true,"")%>
									</div> 
									
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Religion</div>
					                   <%=ComboUtil.createStrComboBoxAuto("religion_id",religionList,CommonUtility.checkNullObj(partADetailsList.get("religion_id")),"form-control","",true,true,"")%>
									</div>  
									
					                 <div class="input-group col-md-3">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Caste</div>
					                  <%=ComboUtil.createStrComboBoxAuto("caste_id",CasteList,CommonUtility.checkNullObj(partADetailsList.get("caste_id")),"form-control","",true,true,"")%>
									</div> 
								 </div>			  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card No</div>
					                    <input type="text" class="form-control" id="rationcard_number" name="rationcard_number" value="<%=CommonUtility.checkNullObj(partADetailsList.get("rationcard_number")) %>" maxlength="20" autocomplete="off" onkeyup="rationType()" onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									</div> 
									
					                 <div class="input-group col-md-4">
					                   	<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card Type</div>
					                   	<%=ComboUtil.createStrComboBoxAuto("rationcard_type",rationCardTypeList,CommonUtility.checkNullObj(partADetailsList.get("rationcard_type")),"form-control","onchange='rationType()'",true,true,"")%> 
									 </div> 
									
					                  <div class="input-group col-md-3">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Slno</div>
					                 	 <input type="text" class="form-control" id="rationcard_slno" name="rationcard_slno" maxlength="2" value="<%=CommonUtility.checkNullObj(partADetailsList.get("rationcard_slno")) %>" autocomplete="off" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);"/>
									 </div> 
								 </div> 
												 
								 <div class="form-inline" style="padding-top:5px;">  	 
					                 <div class="input-group col-md-11">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Identification Marks</div>
					                   <div class="input-group-addon">1)</div>
					                   <input type="text" class="form-control" id="mole_one" name="mole_one" value="<%=CommonUtility.checkNullObj(partADetailsList.get("mole_one")) %>" maxlength="50" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
					                   <div class="input-group-addon">2)</div>
					                    <input type="text" class="form-control" id="mole_two" name="mole_two" value="<%=CommonUtility.checkNullObj(partADetailsList.get("mole_two")) %>" maxlength="50" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									 </div>
								 </div>
										  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Pension</div>
					                   	<%=ComboUtil.createStrComboBoxAutoWithAttribute("pension_type", pensionTypeList, CommonUtility.checkNullObj(partADetailsList.get("pension_type")), "form-control", "", true, true, "--NA--", "")%>
					                </div> 
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Pension Number</div>
					                   <input type="text" class="form-control" id="pensioncard_no" name="pensioncard_no" value="<%=CommonUtility.checkNullObj(partADetailsList.get("pensioncard_no")) %>" autocomplete="off" maxlength="7" onkeypress="return onlyNumbers();" <%if(CommonUtility.checkNullObj(partADetailsList.get("pension_type")).equals("")){ %>readonly<%}%> />
									</div> 
								 </div>										  
												 
								 <div class="form-inline" style="padding-top:5px;">  
									  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Consanguineous Marriage of Parents</div>
					                    <select id="parents_marriage" name="parents_marriage" class="form-control">
				                        	  <option value="-1">Select</option>			
				                              <option value="1" <%if(CommonUtility.checkNullObj(partADetailsList.get("parents_marriage")).equals("1")){ %>selected<%}%>>Yes</option>
				                              <option value="0" <%if(CommonUtility.checkNullObj(partADetailsList.get("parents_marriage")).equals("0")){ %>selected<%}%>>No</option> 
				                         </select>
									</div>  
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">EPIC Card</div>
					                   <input type="text" id="epiccard_no" name="epiccard_no" class="form-control" value="<%=CommonUtility.checkNullObj(partADetailsList.get("epiccard_no")) %>" maxlength="20"  autocomplete="off"  onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									</div> 
								 </div> 
				 		</div>
		 </div>
	</div>		
 </div>
</div>
</form>
	   <div class="row" style="padding: 0px 10px 10px 10px;">
		  
		 <button type="button" id="PartACancelBtnID" class="btn btn-danger" style="float: left"><b>Cancel</b></button> 
		 <button type="button" id="PartAUpadateBtnID" class="btn btn-success" style="float: right"><b>Update</b></button> 
	</div>
 
<%
}
catch(Exception e)
{
	e.printStackTrace();
}%>
            


</body>

</html>
