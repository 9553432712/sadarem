<%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.HashMap,org.apache.commons.fileupload.FileItem,com.ecentric.servicemodels.AadhaarProfile,Aadhar.AadhaarUtility" %> 
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
String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
String telugu 	  = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";            

String sesCampId 	= CommonUtility.checkNullObj(session.getAttribute("campId"));
String districtid 	= CommonUtility.checkNullObj(session.getAttribute("districtId"));
String districtName = CommonUtility.checkNullObj(session.getAttribute("logDistName"));  
String selAadhaarId	= CommonUtility.checkNullObj(session.getAttribute("ses_AadhaarId"));
String currentDate 	= CommonUtility.getDateTime("dd/MM/yyyy");
 
 
AadhaarProfile AadhaarProfileData = (AadhaarProfile)session.getAttribute("AadhaarProfileData");

HashMap PartAformEnteredDataList  = (HashMap)request.getAttribute("PartAformEnteredDataList");
  
boolean is_pension_exist = false,is_epiccard_exist = false; 


String camp_id="",reference_form_number="",form_fill_date="",mandals="",panchayats="",villages="",habitation="",
house_number="",pin_code="",phone_no="",email="",surname="",first_name="",personname_telugu="",date_of_birth="",
gender="",grelation="",relation_name="",relationname_telugu="",education_id="",employment_id="",marital_status_id="",
caste_id="",religion_id	= "",rationcard_number	= "",rationcard_type	= "",rationcard_slno	= "",mole_one="",
mole_two="",pension_type="",pensioncard_no="",parents_marriage="",epiccard_no	= "",add_proof_type ="",add_proof_id ="";
 
if(PartAformEnteredDataList!=null)
{
	camp_id					 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("camp_id")); 						// Camp Id.
	reference_form_number 	 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("reference_form_number")); 		// Form Number
	form_fill_date 			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("form_fill_date")); 				// Form Filled Date
	mandals	 				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("mandals"));						// Mandal ID
	panchayats 				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("panchayats"));					// Panchayat
	villages	 			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("villages"));						// Village
	habitation 				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("habitation"));					// Habitation
	house_number 			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("house_number"));					// house number
	pin_code 				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("pin_code"));						// Pin code
	phone_no				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("phone_no"));						// Contact No.
	email					 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("email"));						// eMail Id
	surname					 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("surname"));						// Surname
	first_name				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("first_name"));					// Name
	personname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("personname_telugu"));			// Person Name in Telugu
	date_of_birth			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("date_of_birth"));				// Date of birth
	gender					 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("gender"));						// Gender
	grelation				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("grelation")); 					// relation type
	relation_name			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("relation_name"));				// Relation name
	relationname_telugu		 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("relationname_telugu"));			// Relation name in telugu
	education_id			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("education_id"));					// Qualification
	employment_id			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("employment_id"));				// Employement
	marital_status_id		 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("marital_status_id"));			// Marital Status
	caste_id				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("caste_id"));						// Caste
	religion_id				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("religion_id"));					// Religion
	rationcard_number		 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("rationcard_number"));			// Ration card No.
	rationcard_type			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("rationcard_type"));				// Ration Type
	rationcard_slno			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("rationcard_slno"));				// Ration card S.No.
	mole_one				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("mole_one"));						// Identity Marks 1
	mole_two				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("mole_two"));						// Identity Marks 2
	pension_type			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("pension_type"));					// Pension Type
	pensioncard_no			 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("pensioncard_no"));				// Pension No.
	parents_marriage		 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("parents_marriage"));				// Parents Marriage 
	epiccard_no				 = CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("epiccard_no"));					// EPIC Card no.
	 
	add_proof_type			= CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("add_proof_type"));				// 2nd Proof Type
	add_proof_id				= CommonUtility.getStringFromFileItem((FileItem)PartAformEnteredDataList.get("add_proof_id"));					// 2nd Proof Id.			
			
}

ArrayList campList	 			= (ArrayList)request.getAttribute("campList");
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
	
	boolean campSelectStatus = true;
	if(campList!=null && campList.size()==1)
	{
		campSelectStatus =false;
	}

	
	
	
	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
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
	<%
	if(alert_msg!=null && alert_msg.trim().length()!=0 &&  !alert_msg.equals("") )
		{
		%> 
			<div class="alert <%=alert_css%>">
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <b><%=alert_msg %></b>
			</div>
<%
		} 
	%>
 <div class="panel  panel-success">  
				 <div class="panel-heading">
				     <h4 class="panel-title"><b>SADAREM Certificate Part-A Form by Aadhaar ID</b></h4>
				 </div>   
				  <div class="panel-body alert-info">  
				  
 					<form  method="post" name="dataform" id="dataform" enctype="multipart/form-data" >
					<input type="hidden" name="district_id" id="district_id" value="<%=districtid%>"/>
					<input type="hidden" name="districtName" id="districtName" value="<%=districtName%>"/> 
					<input type="hidden" id="aadharCardNo" name="aadharCardNo" value="<%=selAadhaarId %>"  readonly="true"/> 
					<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId")) %>"  readonly="true"/> 
					
					<div class="alert alert-warning">
						 <div class="form-inline" style="padding-top:5px;"> 
						 
			                 <div class="input-group col-md-3">
			                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Camp</div>
			                   <%
			                   if(sesCampId==null || sesCampId.equals("")|| sesCampId.equals("0"))
			                   {
			                   %>
							   <%=ComboUtil.createStrComboBoxAuto("camp_id",campList,CommonUtility.checkNullObj(camp_id),"form-control","",campSelectStatus,true,"")%>
							   <%
			                   }
			                   else
			                   {
				               %>
								   <%=ComboUtil.createStrComboBoxAutoWithAttribute("camp_id_disp",campList,CommonUtility.checkNullObj(sesCampId),"form-control","",campSelectStatus,true,""," disabled")%>
								   <input type="hidden" id="camp_id" name="camp_id" value="<%=sesCampId%>"> 
							   <%
			                   }
							   %>
			                 </div>
			                 
			                 <div class="input-group col-md-3">
			                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Aadhar Card No</div>
							   <div class="form-control"><%=selAadhaarId %></div>
			                 </div>
			                 
			                 <div class="input-group col-md-2">
			                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Form No.</div>
			 				   <input type="text" class="form-control" id="reference_form_number" name="reference_form_number" value="<%=CommonUtility.checkNullObj(reference_form_number)%>" autocomplete="off" maxlength="25" onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
			                 </div> 
			                 
			                 <div class="input-group col-md-3">
			                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Date of Assessment</div>
			 				   <input type="text" class="form-control " id="form_fill_date" name="form_fill_date" value="<%=CommonUtility.checkNullObj(form_fill_date)%>" readonly>
			 				    <div class="input-group-addon">
							   	<span class="glyphicon glyphicon-calendar"></span>
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
								  		<div class="form-control"><%=districtName%></div> 
			                          </div>
			                        </div> 
			            
			 				 <div class="form-group" style="padding-top:5px;">
			                          <div class="input-group">
			                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Mandal</div>
									<div>
										 <%=ComboUtil.createStrComboBoxAuto("mandals",mandalList,CommonUtility.checkNullObj(mandals),"form-control","",true,true,"")%>
									 </div>   
			                          </div>
			                        </div>
			                        
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Panchayat</div>
									 <div id="panchayatTDID">
										<%=ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,CommonUtility.checkNullObj(panchayats),"form-control","onchange='loadvillages()'",true,true,"") %>
			                					 </div>
			                				 </div>
			                			 </div>
			                			 
			  				<div class="form-group" style="padding-top:5px;">
			         					<div class="input-group">
									<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Village</div>
									<div id="villageTDID" >
			             						 <%=ComboUtil.createStrComboBoxAuto("villages",villageList,CommonUtility.checkNullObj(villages),"form-control","onchange='loadhabitation()'",true,true,"")%>
			            			 			</div>
			                  			</div> 
							</div> 
			                        
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Habitation/Ward No</div>
									 <div id="habitTDID">
				                       	 <%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,CommonUtility.checkNullObj(habitation),"form-control","",true,true,"")%>
				                      </div> 
			                     </div>
			                </div> 
			                        
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> House No.</div>
									 <input type="text" class="form-control"  id="house_number" name="house_number" value="<%=CommonUtility.checkNullObj(house_number) %>" autocomplete="off" onBlur="this.value = SpaceReplace(this.value);" maxlength="20"  >
				                 </div>
				            </div> 						 
			                     	 
							   
			  				<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Pin Code</div>
								 	 <input type="text" id="pin_code" name="pin_code" class="form-control" autocomplete="off" maxlength="6" value="<%=CommonUtility.checkNullObj(pin_code) %>" onkeypress="return NumbersOnly(event);">
				                 </div>
				            </div>
						
							<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Contact No.</div>
			                         <input type="text" class="form-control ClassValidateContactNo" id="phone_no" name="phone_no" value="<%=CommonUtility.checkNullObj(phone_no) %>" onkeypress="return NumbersOnly(event);" autocomplete="off" maxlength="10"> 
								 </div>
						   </div>
						
							<div class="form-group" style="padding-top:5px;">
				    			 <div class="input-group">
									 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">eMail Id</div> 
			             			 <input type="text" class="form-control" id="email" name="email"  value="<%=CommonUtility.checkNullObj(email) %>" onblur="EmailCheck(this)" autocomplete="off"  maxlength="75">
			             		 </div> 
			               </div>
			               <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#PartAEntryAadhaarServiceBtn" style="background-color:#29bfaa; color:#FFFFFF;font-weight:bold;">Click to view Aadhaar Details</button>		
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
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"> Sur-Name</div>
									   <input type="text" class="form-control" id="surname" name="surname" maxlength="50" value="<%=CommonUtility.checkNullObj(surname) %>"  autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();" />
					                 </div>
					                 
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Person Name</div>
					 				   <input type="text" class="form-control" id="first_name" name="first_name" maxlength="50" value="<%=CommonUtility.checkNullObj(first_name) %>" autocomplete="off"  onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();">
					            </div> 
								
					           	<div class="form-inline" style="padding-top:5px;"> 
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=personname%></div>
									   	<input type="text" class="form-control" id="full_name_eng"  name="full_name_eng" value="<%=CommonUtility.checkNullObj(personname_telugu) %>" maxlength="300" autocomplete="off"  onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = SpaceReplace(this.value);" />
										<input type="hidden" class="form-control" id="personname_telugu" name="personname_telugu" value="<%=CommonUtility.checkNullObj(personname_telugu) %>" />
					                 </div>
					                 
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=telugu%></div>
					 				   <input type="text" class="form-control" id="disp_personname_telugu" name="disp_personname_telugu" value="<%=CommonUtility.checkNullObj(personname_telugu) %>" disabled/>
					                 </div> 
					            </div>
									            	  
								
					           	<div class="form-inline" style="padding-top:5px;"> 
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Date of Birth</div>
									   <input type="text" class="form-control" id="date_of_birth"  name="date_of_birth" value="<%=CommonUtility.checkNullObj(date_of_birth) %>" autocomplete="off" readonly>
									   <div class="input-group-addon">
									   	<span class="glyphicon glyphicon-calendar"></span>
									   </div>
					                 </div>
					                 
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Gender</div>
					 				   <%=ComboUtil.createStrComboBoxAuto("gender", genderList, CommonUtility.checkNullObj(gender) , "form-control", "", true, true, "") %>
					                 </div> 
					            </div>
					             
								
					           	<div class="form-inline" style="padding-top:5px;"> 
					                 
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Type of Relation</div>
					 				   <div id="RelId" class="form-control">	
											<%=ComboUtil.createStrComboBoxAuto("grelation",relationTypeList,CommonUtility.checkNullObj(grelation),"select-optionItem","",true,true,"")%>	 
					                  </div>   
					                 </div> 
					                 
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Relation Name</div>
									   <input type="text" id="relation_name" name="relation_name" value="<%=CommonUtility.checkNullObj(relation_name) %>" class="form-control" maxlength="50" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
					                 </div>
					            </div>							  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=fathername%></div>
					                   <input type="text" class="form-control" id="relation_name_eng" name="relation_name_eng" value="<%=CommonUtility.checkNullObj(relationname_telugu) %>" autocomplete="off" maxlength="300" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = SpaceReplace(this.value);"/>
					                   <input type="hidden" class="form-control" id="relationname_telugu" name="relationname_telugu" value="<%=CommonUtility.checkNullObj(relationname_telugu) %>"/>    
									</div>
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> <%=telugu%></div>
					                   <input type="text" class="form-control" id="disp_relation_name" name="disp_relation_name" value="<%=CommonUtility.checkNullObj(relationname_telugu) %>"  readonly="true"/>  
									</div> 
								 </div>						  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Education</div>
					                   <%=ComboUtil.createStrComboBoxAuto("education_id",EducationList,CommonUtility.checkNullObj(education_id),"form-control","",true,true,"")%>
					                   <div class="input-group-addon"><span class="glyphicon glyphicon-education" style="font-size: 20px;"></span></div>
									</div> 
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Employment</div>
					                   <%=ComboUtil.createStrComboBoxAuto("employment_id",EmploymentList,CommonUtility.checkNullObj(employment_id),"form-control","",true,true,"")%>
									</div> 
								 </div>				  
												 
								 <div class="form-inline" style="padding-top:5px;">  
									
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Marital Status</div>
					                   <%=ComboUtil.createStrComboBoxAuto("marital_status_id",MaritalStatusList,CommonUtility.checkNullObj(marital_status_id),"form-control","",true,true,"")%>
									</div> 
									
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Religion</div>
					                   <%=ComboUtil.createStrComboBoxAuto("religion_id",religionList,CommonUtility.checkNullObj(religion_id),"form-control","",true,true,"")%>
									</div>  
									
					                 <div class="input-group col-md-3">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Caste</div>
					                  <%=ComboUtil.createStrComboBoxAuto("caste_id",CasteList,CommonUtility.checkNullObj(caste_id),"form-control","",true,true,"")%>
									</div> 
								 </div>			  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                 <div class="input-group col-md-4">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card No</div>
					                    <input type="text" class="form-control" id="rationcard_number" name="rationcard_number" value="<%=CommonUtility.checkNullObj(rationcard_number) %>" maxlength="20" autocomplete="off" onkeyup="rationType()" onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									</div> 
									
					                 <div class="input-group col-md-4">
					                   	<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Ration Card Type</div>
					                   	 <%=ComboUtil.createStrComboBoxAuto("rationcard_type",rationCardTypeList,CommonUtility.checkNullObj(rationcard_type),"form-control","",true,true,"")%>
									 </div> 
									
					                  <div class="input-group col-md-3">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Slno</div>
					                 	 <input type="text" class="form-control" id="rationcard_slno" name="rationcard_slno" value="<%=CommonUtility.checkNullObj(rationcard_slno)%>" maxlength="2" autocomplete="off" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);"/>
									 </div> 
								 </div> 
												 
								 <div class="form-inline" style="padding-top:5px;">  	 
					                 <div class="input-group col-md-11">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Identification Marks</div>
					                   <div class="input-group-addon">1)</div>
					                   <input type="text" class="form-control" id="mole_one" name="mole_one" value="<%=CommonUtility.checkNullObj(mole_one)%>" maxlength="50"   autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
					                   <div class="input-group-addon">2)</div>
					                    <input type="text" class="form-control" id="mole_two" name="mole_two" value="<%=CommonUtility.checkNullObj(mole_two)%>" maxlength="50"  autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									 </div>
								 </div>
										  
												 
								 <div class="form-inline" style="padding-top:5px;">  
					                <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Pension</div>
					                   	  <%=ComboUtil.createStrComboBoxAutoWithAttribute("pension_type", pensionTypeList,CommonUtility.checkNullObj(pension_type), "form-control", "", true, true, "--NA--", "")%> 
									</div> 
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Pension Number</div>
					                   <input type="text" class="form-control" id="pensioncard_no" name="pensioncard_no" value="<%=CommonUtility.checkNullObj(pensioncard_no)%>" autocomplete="off" maxlength="7" onkeypress="return onlyNumbers();" <%if(CommonUtility.checkNullObj(pensioncard_no).equals("")){%> readonly<%}%> />
									</div> 
								 </div>										  
												 
								 <div class="form-inline" style="padding-top:5px;">  
									  
					                 <div class="input-group col-md-6">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Consanguineous Marriage of Parents</div>
					                    <select id="parents_marriage" name="parents_marriage" class="form-control">
				                        	  <option value="-1">Select</option>			
				                              <option value="1" <%if(parents_marriage.trim().equals("1")){%>selected<%} %>>Yes</option>
				                              <option value="0" <%if(parents_marriage.trim().equals("0")){%>selected<%} %>>No</option> 
				                         </select>
									</div>  
									
					                 <div class="input-group col-md-5">
					                   <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">EPIC Card</div>
					                   <input type="text" id="epiccard_no" name="epiccard_no" value="<%=CommonUtility.checkNullObj(epiccard_no)%>" class="form-control" maxlength="20"  autocomplete="off"  onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();"/>
									</div> 
								 </div>
								  
								<div class="form-group" style="padding-top:5px;">
					    			 <div class="input-group col-md-11">
										 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font> Profile Photo</div> 
				    					 <input type="file" class="form-control" id="profile_pic" name="profile_pic">
				    					 <div class="input-group-addon" style="color: blue;"><span class="glyphicon glyphicon-camera"></span> Passport Size (File size less than <b>50KB</b> and type <b>JPEG</b>.)</div>
				    				 </div> 
				               </div> 
								  
								<div class="form-group" style="padding-top:5px;">
					    			 <div class="input-group col-md-11">
										 <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;"><font color="red">*</font>  Aadhaar Proof</div> 
				    					<input type="file" class="form-control" id="aadhaar_proof_doc" name="aadhaar_proof_doc">
				    					 <div class="input-group-addon" style="color: blue;"><span class="glyphicon glyphicon-paperclip"></span> File size less than <b>200KB</b> and type <b>JPEG/PDF</b>)</div>
				    				 </div> 
				               </div> 	
					  </div> 
				 </div>
		 </div>
	</div>		
 </div>
 <div class="row">
		  <table class="table table-striped table-bordered dataTable" style="font-size: 15px; font-weight: bold;"> 
		   <thead>
		   <tr>
		   		<th colspan="4" style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Additional Proof of document (Optional)</th>
		   </tr>
		     <tr> 
		       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">S.No.</th>
		       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Proof Type</th>
		       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Proof Id</th>
		       <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">Document (PDF/JPG size less than 200KB)</th> 
		  	 </tr> 
		 	</thead> 
		 	 <tbody>
			  <tr  style="background-color: #f7f5f1 !important;">
			   <td style="text-align: center; vertical-align: middle">1</td> 
		       <td style="text-align:left; vertical-align: middle"><%=ComboUtil.createStrComboBoxAutoWithAttribute("add_proof_type",proofTypeList, CommonUtility.checkNullObj(add_proof_type), "form-control", "",true,true, "","")%> </td> 
		       <td style="text-align:left; vertical-align: middle"><input type="text" class="form-control" id="add_proof_id" name="add_proof_id" value="<%= CommonUtility.checkNullObj(add_proof_id) %>" class="form-control" autocomplete=off  maxlength="30"  onkeypress="return onKeyPressAlphaNumericOnly(event);" onBlur="this.value = (SpaceReplace(this.value)).toUpperCase();" onblur="validate('proofidentity_2','proofid_2')" ></td> 
		       <td style="text-align:left; vertical-align: middle"><input type="file" class="form-control" id="add_proof_doc" name="add_proof_doc"></td>  
			  </tr>
			 </tbody>
			</table>
	</div>
 
		 <button type="button" id="backBtnID" class="btn btn-danger" style="float: left"><b>Back</b></button> 
		 <button type="button" id="formSubmit" class="btn btn-success" style="float: right"><b>Submit</b></button> 
		    
		</form>
	</div>
</div> 
<form id="aadharsearchform" name="aadharsearchform" method="post"> 
 <input type="hidden" id="mode" name="mode" value="loadpartawithaadhaardetails">
 <input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
 <input type="hidden" id="searchaadhaar" name="searchaadhaar" value="<%=selAadhaarId.trim()%>">
</form>


<%
 if(AadhaarProfileData!=null)
 {
 %>

	<div class="modal fade" id="PartAEntryAadhaarServiceBtn" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" style="display: none;">
      <div class="modal-dialog" style=" margin-left: auto; margin-right: auto; width:50% !important">
        <div class="modal-content">
          <div class="modal-header" style="background-color:#dff0d8;">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="color:red;" title="Close"><span aria-hidden="true" class="glyphicon glyphicon-remove-circle"></span></button>
            <h4 class="modal-title"><b>Aadhaar Service Details</b></h4>
          </div>
          <div class="modal-body" style="background-color:  #fcf8e3;">   
		   		 <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">
					        <div class="center-block">
								<div  class="img_circular"  style="background-size: cover;width:130px;height:130px;">
									
									<%
									if(AadhaarProfileData.getBase64file()!=null && AadhaarProfileData.getBase64file().length()>0)
									{ %>
										<img style="border-radius: 50%; width:100%;" src="data:image/png;base64,<%=AadhaarProfileData.getBase64file()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultprofile.png'"/>
									<%
									}
									else
									{
										%>
										<img style="border-radius: 50%; width:100%;" src="<%=request.getContextPath()%>/images/defaultprofile.png">
										<%
									}
									%>
									
								</div>
							</div>
						</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;">
				      		<span >UID : <%=CommonUtility.checkNullObj(AadhaarProfileData.getUid())%></span> <br><br>
				      		<span>EID : <%=CommonUtility.checkNullObj(AadhaarProfileData.getEid())%></span><br><br>
				      		<span>Status Code : <%=CommonUtility.checkNullObj(AadhaarProfileData.getStatus())%></span><br><br>
				      		<span>Name : <%=CommonUtility.checkNullObj(AadhaarProfileData.getName())%></span><br><br>
				      		
				      </div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Date of Birth</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getDob())%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Gender</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getGender())%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Relation</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getCareof())%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">District</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getDistrict_name())%>(<%=CommonUtility.checkNullObj(AadhaarProfileData.getDistrict())%>)</div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Mandal</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getMandal_name())%>(<%=CommonUtility.checkNullObj(AadhaarProfileData.getMandal())%>)</div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Village</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getVillage_name())%>(<%=CommonUtility.checkNullObj(AadhaarProfileData.getVillage())%>)</div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Building Name</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getBuildingName())%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Street</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getStreet())%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">State</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=AadhaarUtility.getAadhaarStateNameByStateCode(CommonUtility.checkNullObj(AadhaarProfileData.getStatecode()))%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">PIN Code</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getPincode() )%></div>
				      
				  </div>
                        <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
				      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">Phone</div>
				      <div class="input-group-addon" style="text-align:left;width:70%;"><%=CommonUtility.checkNullObj(AadhaarProfileData.getPhoneNo())%></div>
				      
				  </div> 
          </div>  
        </div>
      </div>
    </div> 
<%
 }
%>





	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>   
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	     
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/script_telugu_converter.js"></script>  

<script type="text/javascript"> 

//AJax call

	function postRequest(strURL,textID) 
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
	
	function updatepage(msg,id)
	{		
		if(msg!="" && msg!="null")
		{
			document.getElementById(id).innerHTML=msg;
		}
	}


/* function rationType()
{
	try
	{
		//alert("RATION TYPE");
	    var rationCardNumber = document.getElementById("rationcard_number");
	    var rationcardType 	 = document.getElementById("rationcard_type"); 
	    
	    var cardnumber = null;
	    var cardnumberthree = null;
	    if(rationCardNumber != null)
	    {
	        var rationCardNumberValue =rationCardNumber.value;
	        if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3)
	        {
	            cardnumber = rationCardNumberValue.toString().toUpperCase();
	            rationCardNumber.value=cardnumber;
	            rationcardType.selectedIndex = "";
	            rationcardType.disabled = true;
	        }
	        if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3))
	        {
	            cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
	            rationcardType.disabled = false;
	            if(cardnumber != null || cardnumberthree != null)
	            {
	                if(cardnumber == "WAP" || cardnumberthree == "WAP")
	                {
	                    rationcardType.selectedIndex = 1;
	                }
	                else if(cardnumber == "PAP" || cardnumberthree == "PAP")
	                {
	                    rationcardType.selectedIndex = 2;
	                }
	                else if(cardnumber == "AAY" || cardnumberthree == "AAY")
	                {
	                    rationcardType.selectedIndex = 3;
	                }
	                else if(cardnumber == "AAP" || cardnumberthree == "AAP")
	                {
	                    rationcardType.selectedIndex = 4;
	                }
	                else if(cardnumber == "YAP" || cardnumberthree == "YAP")
	                {
	                    rationcardType.selectedIndex = 5;
	                }
	                else if(cardnumber == "TAP" || cardnumberthree == "TAP")
	                {
	                    rationcardType.selectedIndex = 6;
	                }
	                else if(cardnumber == "RAP" || cardnumberthree == "RAP")
	                {
	                    rationcardType.selectedIndex = 7;
	                }
	                else if(cardnumber == "WAD" || cardnumberthree == "WAD")
	                {
	                    rationcardType.selectedIndex = 8;
	                }
	            }
	        }else if(rationCardNumberValue == "")
	        {
	        	rationcardType.selectedIndex = "";
	        	rationcardType.disabled = true;
	        }
	    }
	}
	catch(e)
	{
		alert(e);
	}
  
}  */

function fun_reformatDateString(s)
{
	  var b = s.split(/\D/);
	  return b.reverse().join('');
}

	function submitValildations()
	{
		var rationStatus = 0;
		var addProofValid = 0;
		
		
		
		if($("#rationcard_number").val()!="")
			{
				rationStatus=rationStatus+1;
			}
			
			if($("#rationcard_type").val()!="" && $("#rationcard_type").val()!="-1" )
			{
				rationStatus=rationStatus+1;
			}
				
			if($("#rationcard_slno").val()!="")
			{
				rationStatus=rationStatus+1;
			}
			 
			
			if($("#add_proof_type").val()!="-1")
			{
				addProofValid=addProofValid+1;
			}
			
			if($("#add_proof_id").val()!="" )
			{
				addProofValid=addProofValid+1;
			}
				
			if($("#add_proof_doc").val()!="")
			{
				addProofValid=addProofValid+1;
			}
			
			 
			  
			var dobstr 	 = fun_reformatDateString($("#date_of_birth").val());
			var formdate = fun_reformatDateString($("#form_fill_date").val());

		 
		 if($("#form_fill_date").val()!="" && $("#date_of_birth").val()!="" && (parseInt(dobstr)>parseInt(formdate))==true)
		 {
			  alert("Date of Assessment should be after date of birth");
			  $("#form_fill_date").val("");
			  return false;
		 }
			
	 	if($('#camp_id').val()=="-1")
		{
		    alert("Please select camp");
		    $('#camp_id').focus(); 
		    fun_setbackgroundcolor($('#camp_id'));
		    return false;
		}
		else if($('#reference_form_number').val()=="")
		{
		    alert("Please enter Form No.");
		    $('#reference_form_number').focus();
		    return false;
		}
		else if($("#form_fill_date").val()=="")
		{
			alert("Please select Date of Assessment");
			$("#form_fill_date").focus();
			return false;
		}
		else if($('#mandals').val()==-1)
		{
		    alert("Please select Mandal");
		    $('#mandals').focus();
		    return false;
		}	          
		else if($('#panchayats').val()==-1)
		{
		    alert("Please select Panchayat");
		    $('#panchayats').focus();
		    return false;
		}	          
		else if($('#villages').val()==-1)
		{
		    alert("Please select Village");
		    $('#villages').focus();
		    return false;
		}
		else if($('#habitation').val()==-1)
		{
		    alert("Please select Habitation");
		    $('#habitation').focus();
		    return false;
		}
		else if($("#house_number").val()=="")
		{
			alert("Please enter House No.");
			$("#house_number").focus();
			return false;
		}
		else if($("#pin_code").val()=="")
		{
			alert("Please enter Pin Code");
			$("#pin_code").focus();
			return false;
		}
		else if($("#phone_no").val()=="")
		{
			alert("Please enter Contact No.");
			$("#phone_no").focus();
			return false;
		}
		/* else if($("#surname").val()=="")
		{
			alert("Please enter Sur-Name");
			$("#surname").focus();
			return false;
		} */
		else if($("#first_name").val()=="")
		{
			alert("Please enter Person Name");
			$("#first_name").focus();
			return false;
		}
		else if($("#full_name_eng").val()=="")
		{
			alert("Please enter Name that should be shown on card");
			$("#full_name_eng").focus();
			return false;
		}
		else if($("#date_of_birth").val()=="")
		{
			alert("Please enter Date of Birth");
			$("#date_of_birth").focus();
			return false;
		}
		else if($("#gender").val()=="-1")
		{
			alert("Please select Gender");
			$("#gender").focus();
			return false;
		}		
		else if($("#grelation").val()=="-1")
		{
			alert("Please select Type of Relation");
			$("#grelation").focus();
			return false;
		}
		else if($("#relation_name").val()=="")
		{
			alert("Please enter Relation Name");
			$("#relation_name").focus();
			return false;
		}
		else if($("#relation_name_eng").val()=="")
		{
			alert("Please enter Father/Guardian Name");
			$("#relation_name_eng").focus();
			return false;
		}
		else if($("#education_id").val()=="-1")
		{
			alert("Please select Education");
			$("#education_id").focus();
			return false;
		}
		else if($("#employment_id").val()=="-1")
		{
			alert("Please select Employment");
			$("#employment_id").focus();
			return false;
		}
		else if($("#marital_status_id").val()=="-1")
		{
			alert("Please select Marital Status");
			$("#marital_status_id").focus();
			return false;
		}
		else if($("#religion_id").val()=="-1")
		{
			alert("Please select Religion");
			$("#religion_id").focus();
			return false;
		}
		else if($("#caste_id").val()=="-1")
		{
			alert("Please select Caste");
			$("#caste_id").focus();
			return false;
		}
		else if(rationStatus>0 && rationStatus<3)
		{ 
			alert("Please enter ration card details");
			return false;
		}
		else if($("#mole_one").val()=="" || ($("#mole_one").val()).length < 15)
		{
			alert("Please enter Identification Marks-1.(Minimum 15 characters)");
			$("#mole_one").focus();
			return false;
		} 
		/* else if($("#mole_two").val()=="" || ($("#mole_two").val()).length < 15)
		{
			alert("Please enter Identification Marks-2.(Minimum 15 characters)");
			$("#mole_two").focus();
			return false;
		}  */
		else if($("#mole_one").val()==$("#mole_two").val())
		{
			alert("Mole 1 and 2 should not be same.");
			$("#mole_two").val("");
			$("#mole_two").focus();
			return false;
		} 
		else if($("#pension_type").val()!="-1" && $("#pensioncard_no").val()=="")
		{
			    alert("Please select Pension Number");
				$("#pensioncard_no").focus();
				return false;					
		}
		else if($("#parents_marriage").val()=="-1")
		{
			alert("Please select Consanguineous Marriage of Parents");
			$("#parents_marriage").focus();
			return false;
		}
		else if($('#profile_pic').val()=="" || $('#profile_pic').val()==null || ($('#profile_pic').val()).length ==0 )
		{
			alert("Please upload Profile Photo (Passport size photo less than 50KB jpeg/jpg/png type)");
			$('#profile_pic').focus();
			return false;
		 }  
		else if($('#aadhaar_proof_doc').val()=="" || $('#aadhaar_proof_doc').val()==null || ($('#aadhaar_proof_doc').val()).length == 0)
		{ 
			alert("Please upload Adhaar document (Image less than 50KB jpeg/jpg/png type)");
			$('#aadhaar_proof_doc').focus();
			return false;
		}
		else if(addProofValid>0 && addProofValid<3)
		{
			alert("Please enter valid additional document details");
			return false;
		}
		else if( $("#add_proof_id").val()!="" &&  $("#add_proof_id").val()==$("#aadharCardNo").val())
		{
			alert("Please enter other than Aadhaar Id in Additional Proof Id");
			$("#add_proof_id").val("");
			return false;
		}
		else if( $("#add_proof_doc").val()!="" && ( $("#add_proof_doc").val()==$("#aadhaar_proof_doc").val() || $("#add_proof_doc").val()==$("#profile_pic").val() ))
		{
			alert("Please select other file in Additional Proof document.\nAadhaar and Additional Documents should not be same");
			$("#add_proof_doc").val("");
			return false;
		}
		else
		{
			return true;
		}
		
	}


$(document).ready( function()
{
		
	// Person Full Name to telugu
	
	$('body').on('focusin focusout change blur keyup','#full_name_eng',function()
    		{	 
				print_many_words('full_name_eng','disp_personname_telugu','personname_telugu');
    		});
	
	// Relation Name to telugu
	$('body').on('focusin focusout change blur keyup','#relation_name_eng',function()
    		{	 
				print_many_words('relation_name_eng','disp_relation_name','relationname_telugu');
    		});
	
	 
		$("#pension_type").change(function()
		{
			if($(this).val()=="-1")
				{
					$("#pensioncard_no").prop('readonly', true);
					$("#pensioncard_no").val("");
				} 
				else
				{
					$("#pensioncard_no").prop('readonly', false);
				}
		});
	
	
		

	  	  /* 
		  * Pin Code Validation  
		  */ 
		  $('body').on('blur','#pin_code',function()
		    {	 
				var NumberRegex = /[1-9]\d{5}$/;
				var pincode = $(this).val();
			 //alert(myno);
					if((pincode.length < 6) || NumberRegex.test(pincode)==false)
					{  
						$(this).val("");
						//alert("Please enter valid contact no.");
						return false;
					}
					else
					{
						return true;
					}  
			});
		
	  	  
		  /* 
			  * House Number Validation  
			  */ 
			  $('body').on('blur','#house_number',function()
			    {	 
				  var houserNumberRegex =  /\S\w+[\/|-]?\w+$/; 
					var houseno = $(this).val();
				 //alert(myno);
						if(houserNumberRegex.test(houseno)==false)
						{  
							$(this).val("");
							//alert("Please enter valid contact no.");
							return false;
						}
						else
						{
							return true;
						}  
				});
	  	  
		 

			  /* 
				  * Ration Card No. Validation
				  */ 
				  $('body').on('blur','#rationcard_number',function()
				    {	 
					  var RationCardNoRegex = /[A-Z]{3}\d{7}([A-Z]|[0-9])\d{4}/;
						var rationCardNo = $(this).val();
					 //alert(myno);
							if(RationCardNoRegex.test(rationCardNo)==false)
							{  
								$(this).val("");
								//alert("Please enter valid contact no.");
								return false;
							}
							else
							{
								return true;
							}  
					});
  	  
		  
		  /* 
			  * Ration Card Serial No. Validation
			  */ 
			  $('body').on('blur','#rationcard_slno',function()
			    {	 
					var RationCardSLNoRegex = /[1-9][0-9]/;
					var rationCardSLNo = $(this).val();
				 //alert(myno);
						if(RationCardSLNoRegex.test(rationCardSLNo)==false)
						{  
							$(this).val("");
							//alert("Please enter valid contact no.");
							return false;
						}
						else
						{
							return true;
						}  
				});

			  
			  /* 
				  * Pension No. Validation
				  */ 
				  $('body').on('blur','#pensioncard_no',function()
				    {	 
						var PensionNoRegex = /[1-9]\d{6}/;
						var pensionCardNo = $(this).val();
					 //alert(myno);
							if(PensionNoRegex.test(pensionCardNo)==false)
							{  
								$(this).val("");
								//alert("Please enter valid contact no.");
								return false;
							}
							else
							{
								return true;
							}  
					});
				  	  
		
	
	//Back Button onclick event
	$("#backBtnID").click(function()
	{
		document.aadharsearchform.target="_self";
		document.aadharsearchform.action="<%=request.getContextPath()%>/withoutproofparta.do?randomid="+Math.random();
		document.aadharsearchform.submit();
	});
	

	 /*
	  * Contact No. Validation  
	  */
	  $(".ClassValidateContactNo").blur(function()
	    {	 
			var NumberRegex = /[6789]\d{9}$/;
			var myno = $(this).val();
		 //alert(myno);
				if((myno.length < 10) || NumberRegex.test(myno)==false || myno=="9999999999" || myno=="7777777777" || myno=="8888888888"|| myno=="6666666666")
				{  
					$(this).val("");
					//alert("Please enter valid contact no.");
					return false;
				}
				else
				{
					return true;
				}  
		});
	
	
	
	
	$('#mandals').change(function()
	{ 
		$("#panchayatTDID").html("<%=ComboUtil.createStrComboBoxAuto("panchayats",new ArrayList(),"","form-control","onchange='loadvillages()'",true,true,"") %>");
		$("#villageTDID").html("<%=ComboUtil.createStrComboBoxAuto("villages",new ArrayList(),"","form-control","onchange='loadhabitation()'",true,true,"")%>");
		$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
       
		postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadpanchayatopenrep&manId="+ $('#mandals').val()+"&districtId=<%=districtid%>&randomid="+Math.random(),"panchayatTDID");
      
	});
	
	$('#gender').change(function()
	{		 
		$("#RelId").html("<%=ComboUtil.createStrComboBoxAuto("grelation",new ArrayList(),"","select-optionItem","",true,true,"")%>");
	      
		if($("#gender").val()!="-1")
		{		
		  postRequest("<%=request.getContextPath()%>/ajax.do?action=loadRelationDetails&gender="+ $('#gender').val()+"&randomid="+Math.random(),"RelId");
		}
		
	 });
				
				$('#formSubmit').click(function()
				{	 
					if(!submitValildations())
					{
					    return false;
					}	
					
				    else	if(confirm("Are you sure you want to submit the request?"))
					{
							 	document.dataform.target="_self";
							 	document.dataform.action="<%=request.getContextPath()%>/withoutproofparta.do?mode=logpartadetailsbyaadhaar&randomid="+Math.random();
							 	document.dataform.submit();
								/*Screen Locking Started */
								$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
							    $('#processlayer').css({"display": "block","z-index":"110000"});
							/*Screen Locking Ended */
					} 
					
					 
			});
				
				
				  
		$('#form_fill_date').datetimepicker(
				{
					dayOfWeekStart : 1,
					lang:'en',
					formatDate:'d/m/Y',
					format:'d/m/Y',
					theme:'',
					step:05,
					timepicker:false,
					minDate:'<%=CommonUtility.getDateAddOrSubDays(-30, "dd/MM/yyyy")%>',
					maxDate:'<%=currentDate%>', 
					scrollMonth : false,
		 			scrollInput : false
				});
		
		$('#date_of_birth').datetimepicker(
				{
					lang:'en',
					formatDate:'d/m/Y',
					format:'d/m/Y',
					theme:'',
					step:05,
					timepicker:false,
					minDate:'<%=CommonUtility.getDateAddOrSubDays(-32850, "dd/MM/yyyy")%>',
					maxDate:'<%=currentDate%>', 
					scrollMonth : false,
		 			scrollInput : false
				});		
 
});
		function loadvillages()
		{		     
			$("#villageTDID").html("<%=ComboUtil.createStrComboBoxAuto("villages",new ArrayList(),"","form-control","onchange='loadhabitation()'",true,true,"")%>");
			$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
		    postRequest("<%=request.getContextPath()%>/ajax.do?action=loadVilagePanchayatChangerep&manId="+ $('#mandals').val()+"&panchId="+ $('#panchayats').val()+"&districtId= <%=districtid%>&randomid="+Math.random(),"villageTDID");
		}
		
		function loadhabitation()
		{ 
			$("#habitTDID").html("<%=ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,true,"")%>");
		    postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadhabitationrep&manId="+ $('#mandals').val()+"&villageId="+ $('#villages').val()+"&districtId=<%=districtid%>&randomid="+Math.random(),"habitTDID");
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
