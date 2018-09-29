<%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.ArrayList,com.tcs.sadarem.util.PasswordEncriptDecrypt" %>

<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>

<html>
<%try{ %>
    <head>
    <style>
 

   /* Process Layer Started */
#processlayer {
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

#blocklayer {
	position: absolute;
	left: 0;
	top: 0;
	background: #ECF1EF;
}

/* 	 Process Layer Ended  */
.panel-heading a:after {
	font-family: 'Glyphicons Halflings';
	content: "\e114";
	float: right;
	color: grey;
}

.panel-heading a.collapsed:after {
	content: "\e080";
}

.bs-example {
	margin: 20px;
}

.errmsg {
	color: red;
}

.mycomboStyle {
	width: 125px !important;
}



inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.inner_page_content {
  background: rgb(255, 255, 255) none repeat scroll 0% 0%;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 0px;
  padding-bottom: 15px;
  border-radius: 5px;
  box-shadow: 0px 0px 0px 3px #607D8B
}
.inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.text_box {
  width: 100%;
  margin-bottom: 6px;
  padding: 5px 5px 5px 6px;
  border: 1px solid #DDD;
  border-radius: 3px;
}
.text_box {
  width: 100%;
  margin-bottom: 6px;
  padding: 5px 5px 5px 6px;
  border: 1px solid #DDD;
  border-radius: 3px;
}
input, button, select, textarea {
  background-image: none;
  border: 1px solid #e1e1e1;
  padding: 7px;
  margin-bottom: 15px;
  font-size: 12px;
}
input, button, select, textarea {
  background-image: none;
  border: 1px solid #e1e1e1;
  padding: 7px;
  margin-bottom: 15px;
  font-size: 12px;
}
.heading_title{
	font-size:16px;
	font-family:Verdana, Geneva, sans-serif;
	font-weight:bold;
	padding:3px;
	padding-left:10px;
	color:#333;
}
.input-group-addon {
    min-width:10px;// if you want width please write here //
    text-align:left;
}

.row {
    position: ralative;    
}
    		
    
    </style>
    
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
     <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
	 
	 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	    

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
	<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/teluguname.js"></script>
	
	
	
	
	<script type="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>
	<script type="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <!-- <script type="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script> -->
        <script type="JavaScript" src="./DisabilityUITG/js/date-picker"></script>     
        
       
    
    
    
        <%
        String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
        String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
        String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";            
        
        String districtid = "";
                    
                    
                    ArrayList mandalList = new ArrayList();
                    ArrayList resultList = new ArrayList();
                    ArrayList panchayatList = new ArrayList();
                    ArrayList villageList = new ArrayList();
                    ArrayList habitationList = new ArrayList();
                    ArrayList tempList= new ArrayList();
                    ArrayList CasteList = new ArrayList();
    	            ArrayList EducationList = new ArrayList();
    	            ArrayList EmploymentList = new ArrayList();
    	            
    	          
                    String districtName="";
                    
                    if (request.getAttribute("districtid") != null) 
                    {
                        districtid = (String) request.getAttribute("districtid");
                        districtName  = (String) request.getAttribute("districtName");
                        System.out.println(districtName);
                    }
                    
                 
 
                    String ResultMSG="";
					ResultMSG=CommonUtility.checkNullObj((String)request.getAttribute("ResultMSG"));
                    

    	            
    	            resultList=(ArrayList)request.getAttribute("resultList");    	            
    	            CasteList=(ArrayList)request.getAttribute("CasteList");
    	            EducationList=(ArrayList)request.getAttribute("EducationList");
    	            EmploymentList=(ArrayList)request.getAttribute("EmploymentList");
    	            mandalList      = (ArrayList)request.getAttribute("mandalList");                    
                    panchayatList      = (ArrayList)request.getAttribute("panchayatList");
                    villageList      = (ArrayList)request.getAttribute("villageList");
                    habitationList      = (ArrayList)request.getAttribute("habitationList");

    	           ArrayList MaritalStatusList=(ArrayList)request.getAttribute("MaritalStatusList");
                    
					String encimgPath="";
                    
                    if(resultList.size()>0)
                    {
                    	resultList = (ArrayList)resultList.get(0);
                    }
                    String selmandal = (String)resultList.get(5);
                    String selpanchayat = (String)resultList.get(6);
                    String selvillage = (String)resultList.get(7);
                    String selhab = (String)resultList.get(8);
                    
                    String dob = (String)resultList.get(19);
                    String dateOfAss = (String)resultList.get(38);
                    
                    
                   
                    
           dob=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dob));          
           dateOfAss = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dateOfAss)); 
           
           
          %>    
        
    </head>

    <body >  
    
	<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	
	<div id="blocklayer">
	</div>
  <div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
						        <div class="panel-heading"  style="cursor: pointer;">
									             <h4 class="panel-title">
															<center>Part- A Details</center>	
												 </h4>
						        </div>
				        <div  >
				        
				        </div>
				    </div>
				  </div> 

	<div id="msgDivID"><center><%=ResultMSG %></center></div>
	<button type="button" name="backSubmit" id="backSubmit" class="btn btn-primary btn-xs" style="width:80px;height:40px;float: right;background-color: #e7e7e7; color: black;"><b>Back</b></button>
    <form   name="dataform" id="dataform" method="post"  enctype="multipart/form-data" >
    
	<div name="mywholediv" id="mywholediv" style="margin: 41px;">
	<!-- <div align="center" style="background-color: #337ab7; height: 40px;color:white">PART-A FORM</div> -->		 
	<input type="hidden" name="district_id" id="district_id" value="<%=districtid%>"/>
	
	<input type="hidden" name="request_id" id="request_id" value="<%=(String)resultList.get(1) %>"/>
	
	<input type="hidden" name="habcode" id="habcode" value="<%=(String)resultList.get(9) %>"/>
	<input type="hidden" name="requestflag" id="requestflag" value="<%=(String)resultList.get(42) %>"/>
	
	
	<input type="hidden" name="epicradio" id="epicradio" value="<%=(String)resultList.get(33) %>"/>
	<input type="hidden" name="pensionradio" id="pensionradio" value="<%=(String)resultList.get(35) %>"/>
	
	<input type="hidden" name="proofdoctype" id="proofdoctype" value="<%=(String)resultList.get(41) %>"/>
	<input type="hidden" name="districtName" id="districtName" value="<%=districtName%>"/>
	<input type="hidden" name="mode" id="mode" value="unspecified">
<div class="panel-group" id=""  style="    width: 1198px;   margin: 10px;">				  
				    <div class="panel  panel-primary" id="">
				    
			<table align="center">
				<tr>
				<!-- <td rowspan="1" align="top"> 
                         
                    </td> -->
				<td rowspan="3"><font style="font-weight:bold;">Aadhar ID</font> 
                         <input type="text" id="aadharCardNo" name="aadharCardNo"  style="width:100px" value="<%=(String)resultList.get(40) %>"  readonly="true"/>&nbsp;&nbsp;
                    </td>
				
					
                    <td rowspan="3"><font style="font-weight:bold;">Request ID</font> 
                         <input type="text" id="requestID" name="requestID"  style="width:120px" value="<%=(String)resultList.get(1) %>"  readonly="true"/>
                    </td>
                    <td rowspan="3">&nbsp;&nbsp;
                    <font style="font-weight:bold;">Form No</font><font color="red"><b>*</b></font></td>
                    
                    <td rowspan="3">
                        <input type="text" id="formno" autocomplete="off" name="formno" maxlength="25"  onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"  value="<%=(String)resultList.get(3) %>" />
                    </td>
                    <td rowspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td rowspan="3"><font style="font-weight:bold;">Date of Assessment</font><font color="red"><b>*</b></font></td>
                    <td rowspan="3">
                        <input type="text" class="form-control " id="dateOfAss" name="dateOfAss"   style="width:80px;"  autocomplete="off" value=<%=dateOfAss%>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                    </td>
                    <td  class="secondrow" style="border-right : #234466 1px solid; border-bottom : #234466 1px solid;" rowspan='3' align="left" valign="middle">
	  				 	
	  				 	 <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&aadhaarid=<%=(String)resultList.get(40) %>&requestID=<%=(String)resultList.get(1) %>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/></td>
   			
                </tr>
			</table></div></div>
  					 
<div class="row">
 <div class="col-md-4 ">
				  
 <div class="panel  panel-primary" id="districtwiseform" style="background-color: #337ab7; height: 40px;   margin-bottom: 2px;">  
<center><h4 style="color: white;">Contact Details</h4></center>
 </div> 
 			         
				<div class="panel-group" id="accordion">				  
				    <div class="panel  panel-primary" id="districtwiseform">
				    <!-- <div class="col-md-3 ">	 -->			        
				        <div id="collapseOne" >
				            <div class="panel-body">
				            	
				            	<table>
				            		<tr>
				            				<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">District<font color="red">*</font></div>
																
					                       							<input type="text"   id="district_Name" value="<%=districtName%>" readonly="true">
					                       						
				                     			</div>											          			
											</td>				            		
				            		</tr>
				            	</table><br>
				            	<table border="0">
				            		<tr class="spaceUnder">
				            			
				            			<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Mandal<font color="red">*</font></div>
																<div id="mandalId">
																<%=ComboUtil.createStrComboBoxAuto("mandals",mandalList,selmandal,"form-control","",true,true,"")%>
																</div>   
														</div>
				            			</td>
				            			
				            		</tr>
				            	</table><br>
							
								<table>
				            		<tr>
				            			<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">panchayat<font color="red">*</font></div>
																<div id="panchayatTDID">
															<%=ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,selpanchayat,"form-control","onchange='loadvillages()'",true,true,"") %>
				                     						 
				                     						</div>
				                     			</div>
				            			</td>
				            		</tr>
				            		
				            	</table><br>
							
								<table>
									<tr>
				            			<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">village<font color="red">*</font></div>
																<div id="villageTDID">
					                       							<%=ComboUtil.createStrComboBoxAuto("villages",villageList,selvillage,"form-control","onchange='loadhabitation()'",true,true,"")%>
					                      			 				</div>
				                     			</div>
				            			</td>
				            		</tr>
				            		
				            	</table><br>
								
								
							
								<table>
								
									<tr>
				            			<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Habitation/Ward No<font color="red">*</font></div>
																<div id="habitTDID">
					                       				<%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,selhab,"form-control","",true,true,"")%>
					                       			</div> 
				                     			</div>
				            			</td>
				            		</tr>
				            		
				            	</table><br>
				            	<table>
				            		<tr>
				            			<td>
				            				<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">House No.</div>
																<div id="">
					                       							<input type="text"  id="houseno" name="houseno" maxlength="20"  value="<%=(String)resultList.get(25)%>" >
					                       						</div> 
				                     			</div>
				            			</td>
				            		</tr>
				            	</table><br>				            	
				            	<table>
									
										<tr>
				            				<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">PinCode</div>
																<div id="habitTDID">
					                       							<input type="text"  id="pin" name="pin" maxlength="6" onkeypress="return onlyNumbers();" value="<%=(String)resultList.get(26)%>" >
					                       						</div> 
				                     			</div>											          			
											</td>
														            		
				            		</tr>				            		
				            	</table><br>
							
								<table>
				            		<tr>
				            			<td>
				            				   
						                            <div class="input-group" > 
							                            <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;"> Contact No.</div>
		                                                   <input type="text"  id="contid" name="contid" onkeypress="return NumbersOnly(event);" onBlur="validateContactdetailsms('contid','contidErrMsg')" value="<%=(String)resultList.get(27)%>" autocomplete="off" maxlength="10"> 
															<span class="input-group-addon" id="contidErrMsg"></span>
	                                                 </div>
                                                
				            																          			
										</td>										
				            		</tr>
				            	</table><br>
							
								
							
								<table>
									
										<tr>
				            				<td>
				            					<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">E-mail</div>
																<div id="habitTDID">
					                       							<input type="text"   id="email" name="email"  maxlength="50"  value="<%=(String)resultList.get(28)%>">
					                       						</div> 
				                     			</div>											          			
											</td>
															            		
				            		</tr>				            		
				            	</table><br>
				            					            		      		
				            </div>
				         </div>
				       <!-- </div> -->
				    <!-- <div class="col-md-2 "> -->
				     <div class="panel" style="position: static ;    top: 200px;    right: 600px;    width: 400px;    height: 200px;    border: 3px solid #73AD21;" >
             				
             				<!-- <div class="panel-heading"></div> -->
              				  <div class="panel-body" >						 
					           	 <div class="panel panel-primary" >
					               	 <div class="panel-heading" style="padding-bottom:0px;">
					                    <h1 class="panel-title">
					                        <span class="glyphicon glyphicon-paperclip"></span>
					                      	Attached Proofs</h1><br>                  	
					                    
					               	 </div><br>
					            <%   String imgpath[] = ((String)resultList.get(49)).split(",");
					                for(int i=0;i<imgpath.length;i++)
						                  {
						                	
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a  class="btn icon-btn-primary btn-primary" style="color:#fff;padding-top:15px;padding-bottom:15px;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadPartAWithoutProofFiles&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-paperclip">Attachment<%=i+1 %></span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                  <%} %>   
					                          
	                             
	                           </div>
	                           </div>      
                      		</div> 
                      	<!-- </div> -->
                     </div> 
				</div>
		</div>	
		
<div class="col-md-7">
			  
 <div class="panel  panel-primary" id="districtwiseform" style="background-color: #337ab7; height: 40px;   margin-bottom: 2px;">  
		<center><h4 style="color: white;">Personal Details</h4></center>
 </div> 
			            
				<div class="panel-group" id="accordion1">				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div id="" >
				            <div class="panel-body">	
				            	        
										<table>
											<tr>
                    							<td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">SurName <font color="red">*</font></div>
															<div id="">
                     						 					<input type="text" id="surname" name="surname" maxlength="25" value="<%=(String)resultList.get(16)%>"    onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                  									 onchange="capitalizeMe(this)"/>
                     										</div>    
														</div>
													</div>
												</td>
                   								 <td>
                   								 	<div class="col-md-2 ">	
                   								 		<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">PersonName<font color="red">*</font></div>
																<div id="">
                     						  						<input type="text" id="firstname" name="firstname" maxlength="25" value="<%=(String)resultList.get(17)%>"  onkeydown="return isAlpha(event.keyCode,firstname);"  onkeypress="return space(event,this)"
                                  				 						onchange="capitalizeMe(this)"/>
                     											</div>    
															</div>
														</div>
												</td>
                   								                     
                							</tr>
										</table><br>
										<table>
											<tr>
                    							<td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;"><b><font color="red"><%=personname%></font></b> <font color="red">*</font></div>
															<div id="">
											                     	<input type="text"  autocomplete="off" onkeyup="surname_many_words()" onfocus="surname_many_words()"
											                        	 id="surnameenglish"  name="surnameenglish" maxlength="30" onkeydown="return isAlpha(event.keyCode,surnameenglish);" onkeypress="return space(event,this)"
											                               onmouseover="messagedisplay(2,'surnameenglish')"  value="<%=(String)resultList.get(18) %>" />
											                               <input type="hidden" id="telugu" name="telugupersonname"  value="<%=(String)resultList.get(18) %>"  />
                     										</div>    
														</div>
													</div>
												</td>
                   								 <td>
                   								 	<div class="col-md-2 ">	
                   								 		<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;"><b><font color="red"><%=telugu%></font></b></div>
																<div id="">
                     						  						<input type="text" id="surnametelugu"  readonly="true"  value="<%=(String)resultList.get(18) %>"  />
                                  				 						
                     											</div>    
															</div>
														</div>
												</td>
                   								                   
                							</tr>
										</table><br>
										<table>
											<tr>
												<td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >                    							
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.2 Date of Birth<font color="red">*</font></div>
																<div id="">
                     												 <input type="text" class="form-control " id="dob" name="dob"   style="width:100px;"  autocomplete="off" value="<%=dob %>  ">
                     											</div>    
														</div>
													</div>
												</td>
                    							<td >
                    								<div class="col-md-2 ">	
	                    								<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.3 Gender<font color="red">*</font></div>
																<div id="mandalId">
			                     						 			<select id="gender" name="gender"  onchange="changeothercombo(this.options.selectedIndex)">
											                            <option value="">--SELECT--</option>
											                            <option <%if(resultList.get(10).equals("1")) {%> selected <%} %>value="1">Male</option>  
											                            <option <%if(resultList.get(10).equals("2")) {%> selected <%} %> value="2">Female</option>
											                        </select>
	                     										</div>    
														</div>
													</div>
												</td>
											</tr>
										
										</table><br>
										
										<table>
											<tr>
                    							<td >
                    								<div class="">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.4 Education</div>
															<div id="">
																	<%=ComboUtil.createStrComboBoxAuto("education",EducationList,(String)resultList.get(14),"form-control","",true,true,"") %>
	                     						 					<!-- <select id="education"  name="education" >
												                            <option value="0">--SELECT--</option>
												                            <option value="1">Illiterate</option>
												                            <option value="2">Below 10th</option>
												                            <option value="3">10th Class</option>
												                            <option value="4">Intermediate</option>
												                            <option value="5">Diploma/ITI</option>
												                            <option value="6"> Graduate</option>
												                            <option value="7">Post Graduate</option> 
												                      </select> -->
                     										</div>    
														</div>
													</div>
												</td>
												<td >&nbsp;&nbsp;  &nbsp;
							                     </td>
												<td >
                    								<div class="">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.5 Employment</div>
															<div id="">
															<%=ComboUtil.createStrComboBoxAuto("employment",EmploymentList,(String)resultList.get(15),"form-control","",true,true,"")%>
																<!-- <select id="employment" name="employment" >
										                            <option value="0">--SELECT--</option>
										                            <option value="1">Govt</option>
										                            <option value="2">Private</option>
										                            <option value="3">Self-Employed</option>
										                            <option value="4">Un-Employed</option>
										                            <option value="5">Wage-Employee</option>
										                        </select> -->                     										</div>    
														</div>
													</div>
												</td>
                   								
                   								                   
                							</tr>
                						</table><br>
							
										<table>
                							<tr>
                								 <td>
                   								 	<div class="col-md-2 ">	
                   								 		<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.6 Marital Status<font color="red">*</font></div>
																<div >
																<%=ComboUtil.createStrComboBoxAuto("marital",MaritalStatusList,resultList.get(13).toString().trim(),"form-control","",true,true,"")%>
                     						  						<%-- <select id="marital" name="marital">
											                            <option   value="">--SELECT--</option>
											                            <option  <%if(resultList.get(13).equals("1")) {%> selected <%} %> value="1">Married</option>
											                            <option  <%if(resultList.get(13).equals("2")) {%> selected <%} %> value="2">Un-Married</option>
											                            <option  <%if(resultList.get(13).equals("3")) {%> selected <%} %> value="3">Divorcee</option>
											                            <option  <%if(resultList.get(13).equals("4")) {%> selected <%} %> value="4">Widow</option>
											                            <option  <%if(resultList.get(13).equals("5")) {%> selected <%} %> value="5">Widower</option>
											                        </select> --%>
                                  				 						
                     											</div>    
															</div>
														</div>
												</td>
                							
                							</tr>
                						
										</table><br>
							
										<table>
											<tr>
                    							
							                    <td >
							                    	<div class="">	
							                    		<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.7 Caste</div>
																<div id="mandalId">
																		<%=ComboUtil.createStrComboBoxAuto("caste",CasteList,(String)resultList.get(12),"form-control","",true,true,"")%>
							                     						 <!-- <select id="caste" name="caste">
												                            <option value="6">--SELECT--</option> 
												                            <option value="1">ST</option>
												                            <option value="2">SC</option>
												                            <option value="3">BC</option>
												                            <option value="4">OC</option>
												                            <option value="5">Minority</option>
												                            <option value="6">NA</option>
												                        </select> -->
							                     				</div> 
							                     		</div>
							                     	</div>		
							                     </td>
							                     <td >&nbsp;&nbsp;  &nbsp;
							                     </td>
							                     <td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >                    							
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.8 Religion</div>
																<div id="mandalId">
                     												 <select id="religion" name="religion">
												                            <option  value="7">--SELECT--</option>
												                            <option <%if(resultList.get(11).equals("1")) {%> selected <%} %> value="1">Hindu</option>
												                            <option <%if(resultList.get(11).equals("2")) {%> selected <%} %> value="2">Muslim</option>
												                            <option <%if(resultList.get(11).equals("3")) {%> selected <%} %> value="3">Christian</option>
												                            <option <%if(resultList.get(11).equals("4")) {%> selected <%} %> value="4">Sikh</option>
												                            <option <%if(resultList.get(11).equals("5")) {%> selected <%} %> value="5">Jain</option>
												                            <option <%if(resultList.get(11).equals("6")) {%> selected <%} %> value="6">Budhist</option>
												                            <option <%if(resultList.get(11).equals("7")) {%> selected <%} %> value="7">Others</option>
												                        </select>
                     											</div>    
														</div>
													</div>
												</td>                   
                							</tr>
										</table><br>
																
										<table>
											<!-- <tr>
							                    <td >
							                    	<div class="col-md-2 ">	
							                    		<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.9 Ration Card No</div>
																<div id="mandalId">
							                     						 <input type="text" maxlength="50" id="" name=""/>
							                     				</div>   
														</div>
													</div>
																
												</td>
							                    <td >
							                    	<div class="col-md-2 ">	
							                    			<div class="input-group " >
																	<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Ration Card Type</div>
																	<div id="mandalId">
							                     						 <select id="rtype" name="partAForm" onchange="rationType()">
														                            <option value="">--SELECT--</option>
														                            <option value="1">White</option>
														                            <option value="2">Pink</option>
														                            <option value="3">Anthyodaya</option>
														                            <option value="4">Annapurna</option>
														                            <option value="5">Yellow</option>
														                            <option value="6">Temporary</option>
														                            <option value="7">Rachabanda</option>
														                            <option value="8">White</option>
														                   </select>
							                     					</div>
							                     			</div>
							                     		</div>
							                     			<div class="col-md-1 ">
                        										Slno<input type="text" id="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();" onkeyup="ristrictZero()" style='width:10px'/>
							                     			
							                     		</div> 
							                     		</td>
							                </tr> -->
							                	<tr>
												<td >
														<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">1.9 Ration Card No</div>
																<div id="mandalId">
							                     						 <input type="text" id="card" name="card" maxlength="20"
                              													 onkeyup="rationType()" onkeypress="return space(event,this)"  style='width:130px' value="<%=(String)resultList.get(32)%>"/>
							                     						 
							                     				</div>   
														</div>
													
												</td>
								                <td >
								                    		<div class="input-group " >
																	<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Ration Card Type</div>
																	<div id="mandalId">
							                     						 <select id="rtype" name="rtype" onchange="rationType()">
														                            <option value="">--SELECT--</option>
														                            <option <%if(resultList.get(30).equals("1")) {%> selected <%} %> value="1">White</option>
														                            <option <%if(resultList.get(30).equals("2")) {%> selected <%} %> value="2">Pink</option>
														                            <option <%if(resultList.get(30).equals("3")) {%> selected <%} %> value="3">Anthyodaya</option>
														                            <option <%if(resultList.get(30).equals("4")) {%> selected <%} %> value="4">Annapurna</option>
														                            <option <%if(resultList.get(30).equals("5")) {%> selected <%} %> value="5">Yellow</option>
														                            <option <%if(resultList.get(30).equals("6")) {%> selected <%} %> value="6">Temporary</option>
														                            <option <%if(resultList.get(30).equals("7")) {%> selected <%} %> value="7">Rachabanda</option>														                            
														                   </select>
							                     					</div>
							                     			</div>
														
												</td>	
												 <td >
								                    		<div class="input-group " >
																		<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Slno</div>
																		<div id="">
								                     						  <input type="text" id="rationCardSlno" name="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();" onkeyup="ristrictZero()" style='width:30px'  value="<%=(String)resultList.get(31)%>" />
								                     					</div>   
															</div>
														
												</td>								                    
								            </tr>
										</table>   	
									<br>
                     				<table>
											<tr>
												<td >
													<!-- <div class="col-md-2 ">	 -->
														<!--<div class="input-group " >
															 <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;"> -->
															<font style="background-color: ; color:#000;font-weight:bold;">1.10 EPIC Card&nbsp;&nbsp;</font><!-- </div> -->Click if YES
																							                     						 
								                     						  <input type="checkbox" id="epiccard"  name="epiccard"  onclick="validateepiccheckbox(this.value)" style="width:25px"  <%if(resultList.get(33).equals("1")) {%> checked <%} %>/>
								                     			  
														<!-- </div> -->
													<!-- </div> -->
												</td>
								                <td >
								                    	<div class="col-md-2 ">	
								                    		<div id="epic" style="visibility:hidden;display:none">
									                    		<div class="input-group " >
																			<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">EPIC Number</div>
																			<div id="">
									                     						   <input type="text" id="epiccardno" name="epiccardno" maxlength="20" onkeypress="return letternumber(event)" style="width:150px" value="<%=(String)resultList.get(34)%>"  />
									                     					</div>   
																</div>
															</div>
														</div>
												</td>								                    
								            </tr>
									</table><br>
									<table   cellspacing="0" cellpadding="0" bgcolor="white" >
					                            <tr>
					                                <td ><b>1.11 Pension Card</b> </td>
					                                <td>&nbsp;&nbsp;  &nbsp;</td>
					                                <td  >Click if Yes
					                                    <input type="checkbox"  id="pensioncard" value="true" name="pensioncard"  onclick="validatepensioncheckbox(this.value)" styleId="1"  style="width:50px" <%if(resultList.get(35).equals("1")) {%> checked <%} %>  />
					                                </td>
					                                <td >
					                                    <div id="pension" style="visibility:hidden;display:none">
					                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
					                                            <tr>
					                                                <td ><b>Pension Type</b></td>
					                                                <td>
					                                                    <select id="pension_type" name="pension_type"  styleId="2" style="width:100px">
					                                                    	  <option value="">--SELECT--</option>			
					                                                          <option <%if(resultList.get(36).equals("Disabled")) {%> selected <%} %> value="Disabled">Disabled</option>
					                                                          <option <%if(resultList.get(36).equals("Old Age")) {%> selected <%} %> value="Old Age">O.A.P</option>
					                                                          <option <%if(resultList.get(36).equals("Widow")) {%> selected <%} %> value="Widow">Widow</option>
					                                                          <option <%if(resultList.get(36).equals("Weavers")) {%> selected <%} %> value="Weavers">Weavers</option>
					                                                    </select>
					                                                </td>
					                                                <td >Pension Number</td>
					                                                <td class="textbox"><input type="text" id="pensioncardno" name="pensioncardno" maxlength="7" onkeypress="return onlyNumbers();"  value="<%=(String)resultList.get(37)%>"  /></td>
					                                            </tr>
					                                        </table>
					                                    </div>
					                                </td>
					                            </tr>
					                        </table><br>
					                        
					                        <table   cellspacing="0" cellpadding="0" bgcolor="white" >
					                            <tr>
					                                <td ><b>1.12 Identification Marks<font color="red">*</font></b> </td>
					                                <td>&nbsp;&nbsp;  &nbsp;&nbsp;</td>
					                                <td>1.
					                                	<input type="text" id="mole1" size="25" name="mole1" maxlength="50"   onkeydown="return isAlpha(event.keyCode,mole1);" onkeypress="return space(event,this)"  onchange="capitalizeMe(this)" style="width:180px" value="<%=(String)resultList.get(23)%>"  />
													</td>
					                                <td >&nbsp;&nbsp;&nbsp;&nbsp;2.
					                                    <input type="text" id="mole2" size="25" name="mole2" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole2);" onkeypress="return space(event,this)" onchange="capitalizeMe(this)" style="width:180px"  value="<%=(String)resultList.get(24)%>" />
					                                </td>
					                            </tr>
					                        </table><br>
											<table   cellspacing="0" cellpadding="0" bgcolor="white" >
					                            <tr>
					                                <td ><b>1.13 Consanguineous Marriage of Parents</b> </td>
					                                <td>&nbsp;&nbsp;  &nbsp;&nbsp;</td>
					                                <td  >
					                                	Yes <input type="radio"  name="parents_marriage" value ="1" <%if(resultList.get(29).equals("1")) {%> checked <%} %> >
													</td>
					                                <td >
					                                   No<input type="radio" name="parents_marriage" value ="0" <%if(resultList.get(29).equals("0")) {%> checked <%} %> >
					                                </td>
					                            </tr>
					                        </table><br>
																	
											<table>
											<tr>
                    							
                    							<td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">2.1 Relation Name<font color="red">*</font></div>
															<div id="mandalId">
					                     						 <input type="text" maxlength="50"  id="GName" name="GName"  value="<%=(String)resultList.get(21)%>" />
					                     					</div>   
														</div>
													</div>		
												</td>
												<td>
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Relation Type<font color="red">*</font></div>
																<div id="mandalId">													
                           		
						                     						 <select id="grelation"  name="grelation">
						                     						 	<option value="">--SELECT--</option>
						                     						 	                     						 
						                     						 </select>
                     											</div>   
														</div>
													</div>
												</td>                   
                							</tr>
										</table><br>
										<table>
											<tr>                    							
                    							<td >
                    								<div class="col-md-2 ">	
                    									<div class="input-group " >
															<div class="input-group-addon" style="font-weight:bold;background-color: #eee; color:#000;"><%=fathername%><font color="red">*</font></div>
															 <input type="text" autocomplete="off"  onkeyup="javascript1:first_many_words()"
                              										 onfocus="javascript1:first_many_words()" name="firstnameenglish" maxlength="30"
                              											 onkeydown="return isAlpha(event.keyCode,firstnameenglish);" onkeypress="return space(event,this)" value="<%=(String)resultList.get(22)%>" />  
														</div>
													</div>		
												</td>
												<td>
                    								<div class="col-md-2 ">	
                    								<table>
                    									<tr>
                    										<td>
                    											<%=telugu%>
                    										</td>
                    										<td>
                    											<input type="text"    id="firstnametelugu"  readonly="true" value="<%=(String)resultList.get(22)%>"/>
													                        
                    										</td>
                    											
                    										<td>
                    											<a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
													                        <input type="hidden" id="telugu" name="telugufathername" value="<%=(String)resultList.get(22)%>"/>
                    										</td>
                    										                   
		                								</tr>
													</table	>									
																
																
                     											   
														</div>
													</td>
												</tr>
											</table>										
														
											</div>
											
									
                     			</div>   
							</div>	
   						</div> 
   					</div>
   				</div>
   				
							<div style="text-align:center;">
							<div id="remarksdiv" name="remarksdiv"><b>Remarks</b>&nbsp;&nbsp;<input type="textarea" placeholder="Rejected remarks" id="remarks" size="200" name="remarks" maxlength="300"   onkeydown="return isAlpha(event.keyCode,remarks);" onkeypress="return space(event,this)"  onchange="capitalizeMe(this)" style="width:180px"  /></div><br>
												<button type="button" name="approveSubmit" id="approveSubmit" class="btn btn-success" style="background-color:green;"><b>Approve</b></button>
												<button type="button" name="rejectSubmit" id="rejectSubmit" class="btn btn-success" style="background-color:red;"><b>Reject</b></button>
												<button type="button" name="editSubmit" id="editSubmit" class="btn icon-btn-primary btn-primary" ><b>Edit</b></button>
												<center>	
												<button style="display:none;" type="button" name="updateSubmit" id="updateSubmit" class="btn btn-success">
												<b>Update</b></button>
												</center><br>
												
										</div>


 </div>       
</form>

<script type="text/javascript">
function formNumber()
{
	try
	{
		var formNumber =document.forms[0].formno;
	    if(formNumber !=null){
	        if(formNumber.value == "")
	        {
	            alert("Please enter Form Number!");
	            document.forms[0].formno.focus();
	            return false;
	        }
	    }
	    return true;
	}
	catch(e)
	{
		alert(e);
		return true;
		
	}
    
}

//-- Validation for Person Name  -->
function personName()
{
  var firstname =document.forms[0].firstname;
  if(firstname !=null)
  {
      if(firstname.value == "")
      {
          alert("PWD Name must be Entered!");
          document.forms[0].firstname.focus();
          return false;
      }
  }
  return true;
}

//-- Validation for Person Teluguname  -->
function personTeluguName()
{
  var surnameenglish =document.forms[0].surnameenglish;
  if(surnameenglish !=null)
  {
      if(surnameenglish.value == "")
      {
          alert("PWD Telugu Name must be Enter!");
          document.forms[0].surnameenglish.focus();
          return false;
      }
  }
  return true;
}

//-- Validation for Gender  -->
function gender()
{
	try
	{
		 var gender = document.forms[0].gender;
		  if(gender !=null)
		  {
		      if(gender.value == "")
		      {
		          alert("gender must be selected!");
		          document.forms[0].gender.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
 
}
//-- Validation for marital  -->
function maritalStatus()
{
	try
	{
		var marital = document.forms[0].marital;
		  if(marital !=null)
		  {
		      if(marital.value == "")
		      {
		          alert("marital must be selected!");
		          document.forms[0].marital.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}


//-- Validation for Moleone  -->
function moleone()
{
	try
	{
		var mole1 = document.forms[0].mole1;
		  if(mole1 !=null)
		  {
		      if(mole1.value == "")
		      {
		          alert("Identification Marks(Moles) must be Entered!");
		          document.forms[0].mole1.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}

//-- Validation for Moletwo  -->
function moletwo()
{
	try
	{
		var mole2 = document.forms[0].mole2;
		  if(mole2 !=null)
		  {
		      if(mole2.value == "")
		      {
		          alert("Identification Marks(Moles) must be Entered!");
		          document.forms[0].mole2.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}

//-- Validation for Father Name  -->
function fatherName()
{
	try
	{
		var firstname = document.forms[0].gsurname;
		  if(firstname !=null)
		  {
		      if(firstname.value == "")
		      {
		          alert("PWD FatherName must be Entered!");
		          document.forms[0].gsurname.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}

//-- Validation for fatherTeluguname  -->
function fatherteluguname()
{
	try
	{
		 var firstnameenglish = document.forms[0].firstnameenglish;
		  if(firstnameenglish !=null)
		  {
		      if(firstnameenglish.value == "")
		      {
		          alert("Guardian's Name must be Entered!");
		          document.forms[0].firstnameenglish.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
 
}

//-- Radio button validation -->
function personStatus()
{
	try
	{
		var radio_choice = false;
		  for (counter = 0; counter < dataform.personstatus.length; counter++)
		  {
		      if (dataform.personstatus[counter].checked)
		          radio_choice = true;
		  }
		  if (!radio_choice)
		  {
		      alert("Please select a Person Status.")
		      return false;
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}

//DOB validation
function dobvalidation()
{
	try
	{
		var dob =  document.forms[0].dob.value;
		
		if(dob.length==0||dob.value==" ")
		{
			alert("Please enter Date Of Birth");
			document.forms[0].dob.focus();
			return false;
		}
		return true;
			
	}
	catch(e)
	{
		alert(e);
	}
	
}
//Date Of Assessment validation
function dateAssesvalidation()
{
	try
	{
		var dateassess =  document.forms[0].dateOfAss.value;
		
		if(dateassess.trim().length==0||dateassess.value==" ")
		{
			alert("Please enter Date Of Birth");
			document.forms[0].dateassess.focus();
			return false;
		}
		return true;
			
	}
	catch(e)
	{
		alert(e);
	}
	
}

//-- Validation for EPIC -->

function EPICCardNumber()
{
	try
	{
		
		//var checkedValue = document.querySelector('epiccard:checked').value;
		//alert(checkedValue);
		var epiccard =  document.forms[0].epiccard;
		//var checkedValue = document.querySelector('epiccard:checked').value;
		
		  var epiccardno =  document.forms[0].epiccardno;
		  
		  if(epiccard != null && epiccardno != null)
		  {
		      if ( (epiccard.checked==true) && (epiccardno.value==""))
		      {
		          alert("Please enter EPIC Number");
		          epiccardno.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}
function validateepiccheckbox()
{
	try
	{
		if(document.dataform.epiccard.checked)
		  {
		      document.getElementById("epic").style.visibility = "Visible";
		      document.getElementById("epic").style.display = "Inline";
		  }
		  else
		  {
		      document.getElementById("epic").style.visibility = "hidden";
		      document.getElementById("epic").style.display = "none";
		      document.forms[0].epiccardno.value ="";
		  }
	}
	catch(e)
	{
		alert(e);
	}
  
}
function  pensionNumber()
{
	try
	{		
		var pensioncardno =  document.forms[0].pensioncardno.value;
		var pensionType =  document.forms[0].pension_type.value;
//		alert(pensionType+"pension"+pensioncardno);
		if(document.dataform.pensioncard.checked)
		{
			
			if(pensionType=="" || pensionType.length==0 )
			{
				alert("Please Select Pension type");
				document.forms[0].pension_type.focus();
				return false;
			}
			if(pensioncardno=="" || pensioncardno.length==0  )
			{
				alert("Please Select Pension Card No.");
				document.forms[0].pensioncardno.focus();
				return false;
			}
			return true;
		}
		else
		{
			return true;
		}		  
	}
	catch(e)
	{
		alert(e);
		return false;
	}

}

//-- Validation for Relation -->

function relationName()
{
	try
	{
		 var grelation = document.forms[0].GName.value;
		 
		  if(grelation != null)
		  {
		      if(grelation.value == "")
		      {
		          alert("Relation  must be entered!");
		          document.forms[0].GName.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
 
}
function relationType()
{
	try
	{
		 var grelation = document.forms[0].grelation.value;
		 //alert(document.forms[0].grelation.value);
		  if(grelation != null)
		  {
		      if(grelation == "-1" || grelation == "")
		      {
		          alert("Relation must be selected!");
		          document.forms[0].grelation.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
 
}

//-- Validation for phoneNo -->

function phoneNo()
{
	try
	{
		var phoneno = document.forms[0].phoneno;
		  if(phoneno != null)
		  {
		      if(phoneno.value == "")
		      {
		          alert("Phone No must be Entered");
		          document.forms[0].phoneno.focus();
		          return false;
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}



//-- Validation for Email -->

function validateemailAddress()
{
	try
	{
		var address = document.forms[0].email;
		  if(address != null){
		      if(address.value!="")
		      {
		          var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		          if(reg.test(address.value) == false)
		          {
		              alert('Invalid Email Address');
		              document.forms[0].email.value="";
		              document.forms[0].email.focus();
		              return false;
		          }
		      }
		  }
		  return true;
	}
	catch(e)
	{
		alert(e);
	}
  
}
//-- Validation for ration Card -->
//-- Added by Mohan on 19/09/2011 -->

function rationCardCheck() 
{
	try
	{
		//alert("rationCardCheck");
		 var rcard =  document.forms[0].card;
		  var rationCardSlno = document.forms[0].rationCardSlno;
		  if(rcard.value != "" && rationCardSlno.value == "") 
		  {
		      alert("Please Enter RationCard Serial Number");
		      document.forms[0].rationCardSlno.focus();
		      return false;
		  }

		  return true;

	}
	catch(e)
	{
		alert(e);
		return false;
	}
 }

//--Validation  For Pension Card Checkbox-->


function rationType()
{
	try
	{
		//alert("RATION TYPE");
	    var rationCardNumber = document.getElementById("card");
	    var cardnumber = null;
	    var cardnumberthree = null;
	    if(rationCardNumber != null)
	    {
	        var rationCardNumberValue =rationCardNumber.value;
	        if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3)
	        {
	            cardnumber = rationCardNumberValue.toString().toUpperCase();
	            rationCardNumber.value=cardnumber;
	            document.getElementById("rtype").selectedIndex = "";
	            document.getElementById("rtype").disabled = true;
	        }
	        if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3))
	        {
	            cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
	            document.getElementById("rtype").disabled = false;
	            if(cardnumber != null || cardnumberthree != null)
	            {
	                if(cardnumber == "WAP" || cardnumberthree == "WAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 1;
	                }
	                else if(cardnumber == "PAP" || cardnumberthree == "PAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 2;
	                }else if(cardnumber == "AAY" || cardnumberthree == "AAY")
	                {
	                    document.getElementById("rtype").selectedIndex = 3;
	                }else if(cardnumber == "AAP" || cardnumberthree == "AAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 4;
	                }else if(cardnumber == "YAP" || cardnumberthree == "YAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 5;
	                }else if(cardnumber == "TAP" || cardnumberthree == "TAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 6;
	                }else if(cardnumber == "RAP" || cardnumberthree == "RAP")
	                {
	                    document.getElementById("rtype").selectedIndex = 7;
	                }else if(cardnumber == "WAD" || cardnumberthree == "WAD")
	                {
	                    document.getElementById("rtype").selectedIndex = 8;
	                }
	            }
	        }else if(rationCardNumberValue == "")
	        {
	            document.getElementById("rtype").selectedIndex = "";
	            document.getElementById("rtype").disabled = true;
	        }
	    }
	}
	catch(e)
	{
		alert(e);
	}
  
}
function first_many_words(index_pmw)
{
	try
	{
		//alert("hi father telugu");
		var text_pmw = many_words(document.dataform.firstnameenglish.value);

		  var ans = "";
		  while (text_pmw.length)
		  {
		    var unicode_chars = /&#[0-9]+;/;
		    re = unicode_chars;
		    var matche = re.exec(text_pmw);
		    if (matche != null)
		    {
		      matche = matche[0];
		      search = text_pmw.search(unicode_chars);
		      ans += text_pmw.substring(0, search);
		      ans += String.fromCharCode(matche.match(/[0-9]+/));
		      text_pmw = text_pmw.substring(search + matche.length);
		    } else
		    {
		      ans += text_pmw.substring(0);
		      text_pmw = "";
		    }
		  }

		  document.dataform.firstnametelugu.value = ans;

		  var html_txt = "";
		  for (i=0; i<ans.length; i++)
		  {
		    var unicode_character = ans.charCodeAt(i);
		    switch (unicode_character) 
		    {
			    case 32:
			      html_txt += " ";
			      break;
			    case 10:
			    case 13:
			      html_txt += "<br/>\n";
			      break;
			    default:
			      html_txt += "&#" + unicode_character + ";";
		    }
		  }

		  document.dataform.telugufathername.value = html_txt;
	}
	catch(e)
	{
		alert(e);
	}
  
}

function isAlpha(keyCode,name)
{
	try
	{
		
	    if (keyCode == 16)
	        isShift = true;

	    var str = name.value;
	    //alert(str);
	    if(str.substring(0,1)==" ")
	    {
	        name.value ="";
	        name.focus();
	        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
	    }
	    else
	    {
	        return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
	    }
	}
	catch(e)
	{
		alert(e);
	}	
}
function surname_many_words(index_pmw)
{
	try
	{
	
  var text_pmw = many_words(document.forms[0].surnameenglish.value);
  //alert("text_pmw");

  var ans = "";
  while (text_pmw.length)
  {
    var unicode_chars = /&#[0-9]+;/;
    re = unicode_chars;
    var matche = re.exec(text_pmw);
    if (matche != null)
    {
      matche = matche[0];
      search = text_pmw.search(unicode_chars);
      ans += text_pmw.substring(0, search);
      ans += String.fromCharCode(matche.match(/[0-9]+/));
      text_pmw = text_pmw.substring(search + matche.length);
    } 
    else
    {
      ans += text_pmw.substring(0);
      text_pmw = "";
    }
  }

  document.getElementById('surnametelugu').value = ans;
  //document.getElementById('fulnametelugu').innerHTML = ans;

  var html_txt = "";
  for (i=0; i<ans.length; i++)
  {
    var unicode_character = ans.charCodeAt(i);
    switch (unicode_character)
    {
	    case 32:
	      html_txt += " ";
	      break;
	    case 10:
	    case 13:
	      html_txt += "<br/>\n";
	      break;
	    default:
	      html_txt += "&#" + unicode_character + ";";
    }
  }

  document.forms[0].telugupersonname.value = html_txt;
	}catch(e)
	{
		alert(e);
	}
}
function many_words(sentence)
{
	//alert("many_words--")
  var regex = "((" + vowels + ")|(" + consonants + "))+";
  var words = new Array(0);
  while (sentence.length >= 1) {
    re = new RegExp("^``" + regex);
    var match = re.exec(sentence);
    if (match != null)
    {
      match = match[0];
      words.push("`");
      words.push(one_word(match.substring(2)));
      sentence = sentence.substring(match.length);
    }
    else
    {
      re = new RegExp("^`" + regex);
      match = re.exec(sentence);
      if (match != null)
      {
		match = match[0];
		words.push(match.substring(1));
		sentence = sentence.substring(match.length);
      } 
      else 
      {
			re = new RegExp("^" + regex);
			match = re.exec(sentence);
			if (match != null)
			{
			  match = match[0];
			  words.push(one_word(match));
			  sentence = sentence.substring(match.length);
			}
			else 
			{
			  words.push(sentence.charAt(0));
			  sentence = sentence.substring(1);
			}
      }
    }
  }
  return words.join("");
}
function onlyNumbers(evt)
{
	try
	{
		var reg = /^\d+$/;
        var e = event || evt; // for trans-browser compatibility
        var charCode = e.which || e.keyCode;

        if (charCode > 31 && ((charCode < 48) || (charCode > 57)))
            return false;

        return true;
        
        
	}
	catch(e)
	{
		alert(e);
	}
    
}
function submitValildations()
{
	 try
	{
	    var flag=true;
	    
		 flag=formNumber();
		 if(!flag)
	        return flag;
	   // flag=fromdateValidation();

	    if(!flag)
	        return flag;
	   // flag=personSurame();  As per client requirement not sur-name will be entered

	    if(!flag)
	        return flag;
	    flag=personName();

	    if(!flag)
	        return flag;
	    flag=personTeluguName();

	    if(!flag)
	        return flag;
	    
	    flag=dobvalidation();

	    if(!flag)
	        return flag; 
	        
	    flag=gender();

	    if(!flag)
	        return flag;
	    flag=maritalStatus();
 
	    if(!flag)
	        return flag;
	   // alert("before ration");
	    flag = rationCardCheck(); 
	    
	    if(!flag)
	        return flag;
	    flag=EPICCardNumber();
	    //alert("after epinc");
	    if(!flag)
	        return flag;
	    
	    flag=pensionNumber();
	    if(!flag)
	        return flag;
	    
	    flag=moleone();

	    if(!flag)
	        return flag;
	    
	    flag=moletwo();
	    if(!flag)
	        return flag;
	    
	    flag=relationName();
	    if(!flag)
	        return flag;
	    
	    flag=relationType();
	    if(!flag)
	        return flag;
	    
	    flag=fatherName();
	    if(!flag)
	        return flag;
	    
	   
	    
	    flag=fatherteluguname();
	    if(!flag)
	        return flag;
	    
	    flag=validateemailAddress();
	    if(!flag)
	        return flag;
	    
	    flag=phoneNo();
	    if(!flag)
	        return flag;

	    //flag=personStatus();//No need

	   // flag=typeOfDisability();//No need

	    //flag=aadharCardNo();

	    if(!flag)
	        return flag;
	   


	    return flag;
	}
	catch(e)
	{
		alert(e);
		return false;
	} 
	

}
$(document).ready( function()
{
				$('#mandals').change(function()
				{			    
			       postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadpanchayatopenrep&manId="+ $('#mandals').val()+"&districtId="+ <%=districtid%>+"&randomid="+Math.random(),"panchayatTDID");
			      
				});
			
				
				
				 //to get todays date
			     var d = new Date();
			     var month = d.getMonth()+1;
			     var day = d.getDate();
			     var output = (day<10 ? '0' : '') + day+ '/' +(month<10 ? '0' : '') + month + '/' +d.getFullYear();
			    //end 
			     var today= new Date();
		$('#dob').datetimepicker(
				{
					dayOfWeekStart : 1,
					lang:'en',
					formatDate:'d/m/Y',
					format:'d/m/Y',
					theme:'',
					step:05,
					timepicker:false,
						maxDate:output,
						yearStart:today.getFullYear()-100,
						yearEnd:today.getFullYear(),
						scrollMonth : false,
			 			scrollInput : false
				});
				
		$('#dateOfAss').datetimepicker(
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
					yearStart:today.getFullYear()-100,
					yearEnd:today.getFullYear(),
					scrollMonth : false,
		 			scrollInput : false
				});
});
function loadvillages()
{		    
    postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadvillagerep&manId="+ $('#mandals').val()+"&districtId="+ <%=districtid%>+"&randomid="+Math.random(),"villageTDID");
   
	
}
function loadhabitation()
{			    
    postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadhabitationrep&manId="+ $('#mandals').val()+"&villageId="+ $('#villages').val()+"&districtId="+ <%=districtid%>+"&randomid="+Math.random(),"habitTDID");
   
	
}
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




var groups=document.getElementById("gender").options.length;


var group=new Array(groups);
for (i=0; i<groups; i++)
	{
		group[i]=new Array();
	}

group[0][0]=new Option("-1","--SELECT--");

group[1][0]=new Option("-1","--SELECT--");
group[1][1]=new Option("0","C/o Of");
group[1][2]=new Option("1","S/o Of");
group[1][3]=new Option("2","G/o Of");
group[1][4]=new Option("3","H/o Of");


group[2][0]=new Option("-1","--SELECT--");
group[2][1]=new Option("0","C/o Of");
group[2][2]=new Option("1","D/o Of");
group[2][3]=new Option("2","G/o Of");
group[2][4]=new Option("3","W/o Of");

var temp=document.getElementById("grelation");



function changeothercombo(x)
{
	try
	{
		 for (m=temp.options.length-1;m>0;m--)
			    temp.options[m]=null;
			    for (i=0;i<group[x].length;i++)
			    {
			    temp.options[i]=new Option(group[x][i].value,group[x][i].text);
			    }
			   
			    
			    var id = <%=(String)resultList.get(20) %>;
			    temp.options[id+1].selected=true;
	}
	catch(e)
	{
		alert("Please select Gender : ");
	}

   
}
function validatepensioncheckbox()
	{
		try
		{
			if(document.dataform.pensioncard.checked)
			  {
			      document.getElementById("pension").style.visibility = "Visible";
			      document.getElementById("pension").style.display = "Inline";
			  }
			  else
			  {
				  
			      document.getElementById("pension").style.visibility = "hidden";
			      document.getElementById("pension").style.display = "none";
			      document.forms[0].pensioncardno.value ="";
			      document.forms[0].pension_type.value ="";
			  }
		}
		catch(e)
		{
			alert(e);
		}
	  
	}
$(document).ready( function()
		{
	<% SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");

    // String strDate = formDate.format(System.currentTimeMillis()); // option 1
    String strDate = formDate.format(new Date()); 
    
    
    %>
	
    var flag='<%=(String)resultList.get(42)%>';
    
    if(flag=="A" || flag=="R" || flag!="P")
    	{
      	$("#updateSubmit").addClass("hide");
 		$("#approveSubmit").addClass("hide");
 		$("#editSubmit").addClass("hide");
 		$("#rejectSubmit").addClass("hide");
 		$("#remarks").addClass("hide");  
 		$("#remarksdiv").addClass("hide");
    	}
    
    
	   $("#msgDivID").addClass("show");
	   $("#dataform input, #dataform select").prop('disabled',true); 

	   $("#remarks").prop('disabled',false); 
	   $("#remarksdiv").prop('disabled',false); 
	   
	
		     var gendpos = document.getElementById("gender").options.selectedIndex;       	     
		 	 changeothercombo(gendpos); 
		 	 	 
		 	validatepensioncheckbox();
		 	validateepiccheckbox(); 
		 	 
		 	
		 	 $("#approveSubmit").click(function ()
						{
		 			 
			 		   if ( $('#mandals').val()==-1)
		                  {
		                      alert("Please Select Mandal type");
		                      $('#mandals').focus();
		                      return false;
		                  }	          
			      			else if ( $('#panchayats').val()==-1)
		          			{
					              alert("Please Select Panchayat type");
					              $('#panchayats').focus();
					              return false;
		          			}	          
						      else if ( $('#villages').val()==-1)
					          {
					              alert("Please Select Village type");
					              $('#villages').focus();
					              return false;
					          }
						      else if ($('#habitation').val()==-1)
					          {
					              alert("Please Select Habitation type");
					              $('#habitation').focus();
					              return false;
					          }
						      else if(process($('#dateOfAss').val()) > process('<%=strDate%>'))
		    					{
						    	  alert("Date Of assessment cannot be greater than todays date");
						    	   $('#dateOfAss').focus();
						              return false;
		    					}
						      else if(process($('#dob').val()) > process('<%=strDate%>'))
		    					{
						    	  alert("Date Of birth cannot be greater than todays date");
						    	   $('#dob').focus();
						              return false;
		    					}
						 
						      else  if (!submitValildations())
					          {
					              return false;
					          }	
						      else
						    	  {
			  
						    	   $("#dataform input, #dataform select").prop('disabled',false); 
						 		    $("#mode").val('approvePartAform');
						 		   $("#updateSubmit").addClass("hide");
							 		$("#approveSubmit").addClass("hide");
							 		$("#editSubmit").addClass("hide");
							 		$("#rejectSubmit").addClass("hide");
							 		$("#remarks").addClass("hide");  
							 		$("#remarksdiv").addClass("hide");
						 		
				    document.dataform.target="_self";
					document.dataform.action="<%=request.getContextPath()%>/withoutproofparta.do";
					document.dataform.submit();
					/*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				/*Screen Locking Ended */
					
						    	  }
		 	});	
		 	 
		 	 $("#editSubmit").click(function ()
						{
		 		$("#dataform input, #dataform select").prop('disabled',false);    
		 		$("#updateSubmit").addClass("show");
		 		$("#approveSubmit").addClass("hide");
		 		$("#editSubmit").addClass("hide");
		 		$("#rejectSubmit").addClass("hide");
		 		$("#remarks").addClass("hide");  
		 		$("#remarksdiv").addClass("hide");
						});	
		 		 
		 	 
			 $("#updateSubmit").click(function ()
						{
				
		 		 
		 		   if ( $('#mandals').val()==-1)
	                  {
	                      alert("Please Select Mandal type");
	                      $('#mandals').focus();
	                      return false;
	                  }	          
		      			else if ( $('#panchayats').val()==-1)
	          			{
				              alert("Please Select Panchayat type");
				              $('#panchayats').focus();
				              return false;
	          			}	          
					      else if ( $('#villages').val()==-1)
				          {
				              alert("Please Select Village type");
				              $('#villages').focus();
				              return false;
				          }
					      else if ($('#habitation').val()==-1)
				          {
				              alert("Please Select Habitation type");
				              $('#habitation').focus();
				              return false;
				          }
					      else if(process($('#dateOfAss').val()) > process('<%=strDate%>'))
	    					{
					    	  alert("Date Of assessment cannot be greater than todays date");
					    	   $('#dateOfAss').focus();
					              return false;
	    					}
					      else if(process($('#dob').val()) > process('<%=strDate%>'))
	    					{
					    	  alert("Date Of birth cannot be greater than todays date");
					    	   $('#dob').focus();
					              return false;
	    					}
					      else  if (!submitValildations())
				          {
				              return false;
				          }	
					      else
					    	  {
		 		    
					    	 	$("#updateSubmit").addClass("hide");
						 		$("#approveSubmit").addClass("show");
						 		$("#editSubmit").addClass("show");
						 		$("#rejectSubmit").addClass("show");
						 		$("#remarks").addClass("show");
						 		
						 	   $("#dataform input, #dataform select").prop('disabled',false); 
					 		    $("#mode").val('updatePartAform');
					    	  
				    document.dataform.target="_self";
					document.dataform.action="<%=request.getContextPath()%>/withoutproofparta.do";
					document.dataform.submit();
					
					/*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				/*Screen Locking Ended */
					
					    	  }
		 		   
		 		   
		 	});	
			 
			 $("#rejectSubmit").click(function ()
						{
		 			 
			 		   if ( $('#mandals').val()==-1)
		                  {
		                      alert("Please Select Mandal type");
		                      $('#mandals').focus();
		                      return false;
		                  }	          
			      			else if ( $('#panchayats').val()==-1)
		          			{
					              alert("Please Select Panchayat type");
					              $('#panchayats').focus();
					              return false;
		          			}	          
						      else if ( $('#villages').val()==-1)
					          {
					              alert("Please Select Village type");
					              $('#villages').focus();
					              return false;
					          }
						      else if ($('#habitation').val()==-1)
					          {
					              alert("Please Select Habitation type");
					              $('#habitation').focus();
					              return false;
					          }
						      else if(process($('#dateOfAss').val()) > process('<%=strDate%>'))
		    					{
						    	  alert("Date Of Assessment cannot be greater than todays date");
						    	   $('#dateOfAss').focus();
						              return false;
		    					}
						      else if(process($('#dob').val()) > process('<%=strDate%>'))
		    					{
						    	  alert("Date Of Birth cannot be greater than todays date");
						    	   $('#dob').focus();
						              return false;
		    					}
						      else  if (!submitValildations())
					          {
					              return false;
					          }	
						      else if($('#remarks').val()==null || $('#remarks').val()=="")
					    	  {
					    	  alert("Please enter rejected remarks");
				              $('#remarks').focus();
				              return false;
					    	  }
						    
						      else
						    	  {
						    	  
						    	  	$("#updateSubmit").addClass("hide");
							 		$("#approveSubmit").addClass("hide");
							 		$("#editSubmit").addClass("hide");
							 		$("#rejectSubmit").addClass("hide");
							 		$("#remarks").addClass("hide");  
							 		$("#remarksdiv").addClass("hide");
							  
						 	
						    	  
			  
						    	   $("#dataform input, #dataform select").prop('disabled',false); 
						 		    $("#mode").val('rejectPartAform');
						 			
						 
						    	  
					    document.dataform.target="_self";
						document.dataform.action="<%=request.getContextPath()%>/withoutproofparta.do";
						document.dataform.submit();
						
							
						/*Screen Locking Started */
						$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
					    $('#processlayer').css({"display": "block","z-index":"110000"});
					/*Screen Locking Ended */
						
						    	  }
		 	
		});
			 
			 $("#backSubmit").click(function ()
						{
				   $("#mode").val('unspecified');
				 document.dataform.target="_self";
					document.dataform.action="<%=request.getContextPath()%>/withoutproofparta.do";
					document.dataform.submit();
					
						});

		});
function process(date){
	   var parts = date.split("/");
	   var date = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	   return date.getTime();
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
