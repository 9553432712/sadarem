<%--
    Document   : aadharCardPersonDetails
    Created on : Oct 8, 2014, 3:46:55 PM
    Author     : 310926
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList,java.util.HashMap,com.tcs.sadarem.util.CommonUtility,com.ecentric.servicemodels.AadhaarProfile,Aadhar.AadhaarUtility" contentType="text/html" pageEncoding="UTF-8"%>

<%

	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));

	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
            
	String PartArestrict = CommonUtility.checkNullObj(request.getAttribute("PartArestrict"));

	String selUid = CommonUtility.checkNullObj(request.getAttribute("searchaadhaar"));
	AadhaarProfile AadhaarProfileData = (AadhaarProfile)session.getAttribute("AadhaarProfileData");

	ArrayList aadhaarTaggedSadaremIDList = (ArrayList)request.getAttribute("aadhaarTaggedSadaremIDList");

	HashMap requestDetailsList = (HashMap)request.getAttribute("requestDetailsList");
             
%>
<html:html>
<head>
<title>:: SADAREM :: Part-A entry by Aadhaar</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"> 

 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>

<style type="text/css">

/*Profile Photo Circle Start */
	.img_circular_old {
	width: 300px;
	height: 300px;
  	position: relative;
	border-radius: 150px;
	-webkit-border-radius: 150px;
	-moz-border-radius: 150px;
	box-shadow: 0 0 8px rgba(0, 0, 0, .8);
	-webkit-box-shadow: 0 0 8px rgba(0, 0, 0, .8);
	-moz-box-shadow: 0 0 8px rgba(0, 0, 0, .8);
	}
	
	.img_circular 
	{
	  width: 150px;
	  height: 150px;
	  border-radius: 150px;
	  position: relative;
	  cursor: default;
	  margin: 0 auto;
	  -webkit-box-shadow: 0 0 8px rgba(0, 0, 0, .8);
	}

/* Profile Photo Circle End */

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
     	


</style>


<script type="text/javascript">


        function isNumberKey(e)
        {
            //var number = document.getElementById("aadharCardNo").value;

            AllowableCharacters='1234567890';
            var k = document.all?parseInt(e.keyCode): parseInt(e.which);

            if (k!=13 && k!=8 && k!=0){
                if ((e.ctrlKey==false) && (e.altKey==false)) {
                    return (AllowableCharacters.indexOf(String.fromCharCode(k))!=-1);
                } else {
                    return true;
                }
            } else {
                return true;
            }

        }
        <%--function goBack()
        {
            document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
            document.forms[0].submit();
        }--%>



            function validateForm(thisForm){
                var flag = true;
                flag = validate_form(thisForm);
                if(flag){
                    if (thisForm.getAttribute('submitted'))
                    {
                        flag = false;
                        return flag;
                    }
                    else
                    {
                        thisForm.setAttribute('submitted','true');
                    }
                }
                return flag;
            }
            
            function validate_form(thisform)
            {
            
                with (thisform)
                { if (validate_required(aadharCardNo)==false)
                    {
                        aadharCardNo.focus();
                        return false;
                    }
                      if (validateVerhoeff(aadharCardNo)==false)
                    {
                        
                        aadharCardNo.value="";
                        aadharCardNo.focus();
                        return false;
                    }
                    return true;
                }
            }

            function validate_required(field)
            {
                with (field)
                {
                    if (value==null||value=="")
                    {
                    	alert("AadharCard Number must be filled out!");
                    	return false;
                    }

                    if(value != null && value !="")
                    {
                        if(value.length!=12 )
                        {
                            alert(" Please Enter Valid AdhaarCard Number.AdhaarCard Number Must have 12 digits");
                            return false;
                        }
                    }

                }
            }
            
            $(document).ready(function()
            		{ 
            	
            	
            	
            			$("#aadhaarsearchbut").click(function()
            			{
           					
           					if($("#searchaadhaar").val()=="" || fun_validateAadhaarID($("#searchaadhaar").val())==false)
							{ 
						    	 alert("Please provide valid Aadhaar ID");
								
								$("#searchaadhaar").val("");
								$("#searchaadhaar").focus();
								 return false;
							}
           					else
           					{
           						document.aadharsearchform.action="<%=request.getContextPath()%>/withoutproofparta.do?randomid=<%=Math.random()%>";
           						document.aadharsearchform.submit();
           						/*Screen Locking Started */
        		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
        		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
        	      			/*Screen Locking Ended */
           					}
            				
            			});
            			
            			$("#resubmitformdatabut").click(function()
            					
            			{
            				document.partAForm.submit();
            				/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
            			});
            			
            			$("#noproof").click(function()            					
                    	{
            				if(confirm("Do you want to continue"))
            				{
            					 
            					document.sadarempartaentryFormId.action="<%=request.getContextPath()%>/withoutproofparta.do";
           						document.sadarempartaentryFormId.submit();
                        				/*Screen Locking Started */
                		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
                		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
                	      			/*Screen Locking Ended */
            				}
            				
                    	});
            			
            			
			            	 
            		});
            
            
    </script>

</head>
    
    <body>

	<!-- Screen Lock Started Here -->
			<div id="processlayer">
				<font color="blue" size="2">Processing please wait...</font><br/>
				<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
			</div>
			<div id="blocklayer">
			</div>
		<!-- Screen Lock Ended Here -->  
   
<div class="main-container"> 
<div class="alert alert-info" style="min-height: 430px;">
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
    try
    {
      if(selUid!=null && selUid.trim().length()==12 && AadhaarProfileData!=null && AadhaarProfileData.getStatus()!=null && !CommonUtility.checkNullObj(AadhaarProfileData.getStatus()).trim().equals(""))
    {  
    %>   
    <form name="sadarempartaentryFormId" method="post">
    	<input type="hidden" id="mode" name="mode" value="loadpartawithaadhaarentry"> 
    	<input type="hidden" id="AaadhaarStatus" name="AaadhaarStatus" value="<%=CommonUtility.checkNullObj(AadhaarProfileData.getStatus())%>">
    	<input type="hidden" id="selAadhaarId" name="selAadhaarId" value="<%=CommonUtility.checkNullObj(AadhaarProfileData.getUid())%>">
		<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
    </form>
    <div class="row">
			          <div class="col-md-6">
			          			<div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-th"></span>
					                      	Aadhaar Details for Part-A Entry
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;">  
					              
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
					                          <div class="input-group" style="height:30px;padding-top:5px;width:100%;">
											      <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;width:30%;">SADAREM ID(s) Tagged</div>
											      <div class="input-group-addon" style="text-align:left;width:70%;"><%=AadhaarUtility.getSADAREMTagedtoAadhaar(aadhaarTaggedSadaremIDList)%></div>
											      
											  </div>
					          	    </div>
					        </div>
					  </div>
					  <div class="col-md-5">
					           <div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-th"></span>
					                      Aadhaar ID Validation Status
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;"> 
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #3877cd; color:#fff;text-align:left;width:80%">Aadhaar belongs to Telangana State</div>
												  <div class="input-group-addon"><%=AadhaarUtility.getAadhaarBelongstoTelangan(CommonUtility.checkNullObj(AadhaarProfileData.getStatecode())) %></div> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #3877cd; color:#fff;text-align:left;width:80%">Is Aadhaar Card is Active</div>
												  <div class="input-group-addon"><%=AadhaarUtility.getAadhaarErrorCodeStatus(CommonUtility.checkNullObj(AadhaarProfileData.getStatus())) %></div> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #3877cd; color:#fff;text-align:left;width:80%">Is Aadhaar Profile Photo Available</div>
												  <div class="input-group-addon"><%=AadhaarUtility.getAadhaarPhotoStatus(CommonUtility.checkNullObj(AadhaarProfileData.getStatus())) %></div> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #3877cd; color:#fff;text-align:left;width:80%">Is Aadhaar not tagged to a SADAREM ID</div>
												  <div class="input-group-addon"><%=AadhaarUtility.getAadhaarTagStatus(aadhaarTaggedSadaremIDList) %></div> 
					                            </div>
					                        </div>
					                      <%--  <%
					                       if( AadhaarUtility.getAadhaarFormEntryStatus(CommonUtility.checkNullObj(AadhaarProfileData.getStatus()), CommonUtility.checkNullObj(AadhaarProfileData.getStatecode()), aadhaarTaggedSadaremIDList))
					                       {
					                       %>
					                         <div class="form-group" style="height:30px;padding-top:5px;text-align: center;"> 
															<button type="button" id="resubmitformdatabut" name="resubmitformdatabut" class="btn btn-success">Proceed</button> 
					                        </div>
					                        <%}else  if((AadhaarProfileData.getStatus().trim().equals("100") || AadhaarProfileData.getStatecode().trim().equals("101") ) && !AadhaarProfileData.getStatecode().trim().equals("2") && aadhaarTaggedSadaremIDList.size()==0)--%>
					                        <%
					                        if((AadhaarProfileData.getStatus().trim().equals("100") || AadhaarProfileData.getStatecode().trim().equals("101") ) && aadhaarTaggedSadaremIDList.size()==0)
					                       {%>
					                       			<form name="partANoProofform" id="partANoProofform" method="post">
					                       			<input type="hidden" id="adhaarid1"  name="adhaarid1" value="<%=CommonUtility.checkNullObj(AadhaarProfileData.getUid())%>">
					                       			<input type="hidden" name="mode" id="mode" value="">
					                       			<div class="form-group" style="height:30px;padding-top:5px;text-align: center;"> 
															<button type="button" id="noproof" name="noproof" class="btn btn-success">Continue Without Proof</button> 
					                       			 </div> 
					                       			 </form>
					                       <%}%> 
					           </div>
					        </div>
					</div>
    </div>
    
    <%
    }
    else
    {
    			
				%>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">  
					
    			         <div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-th"></span>
					                      	Part- A Entry by Aadhaar ID
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;">
			               				<form class="form-inline" id="aadharsearchform" name="aadharsearchform" method="post">
			               				<input type="hidden" id="mode" name="mode" value="loadpartawithaadhaardetails">
			               				<input type="hidden" id="reqFormId" name="reqFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesFormId"))%>">
										  <div class="form-group"> 
										    <div class="input-group">
										      <div class="input-group-addon">Aadhaar ID</div>
										      <input type="text" class="form-control" id="searchaadhaar" name="searchaadhaar" placeholder="Aadhaar ID" maxlength="12"  onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off"> 								      
										    </div>
										  </div>
										  <button type="button" id="aadhaarsearchbut" name="aadhaarsearchbut" class="btn btn-primary">View Details</button>
										</form> 
				   					 </div>
				   			</div>
				   		</div>
					</div>
					
					
			    <%
			    if(requestDetailsList!=null && requestDetailsList.size()>0)
			    {
					%>
			   	 		
								<div class="row">
									<div class="col-md-12">  
										  <div class="panel  panel-success">  
											   <div class="panel-heading">
										           <h4 class="panel-title"><b>Registered Details</b></h4>
											   </div>   
									           <div class="panel-body" style="background-color: #4b7288;">
									             <div class="row">
									             		<div class="col-md-2" style="vertical-align: middle">  
															        <div class="center-block"  style="background-color: #ffffff;border-radius: 50%; width:80%;margin-top:15%;">
																		<div  class="img_circular"  style="background-size: cover;width:150px;height:150px;"> 
																		 	<img style="border-radius: 50%; width:100%;"  src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&aadhaarid=<%=CommonUtility.checkNullObj(requestDetailsList.get("proof_id"))%>&requestID=<%=CommonUtility.checkNullObj(requestDetailsList.get("request_id"))%>&randiomid=<%=Math.random()%>"  width="150" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
   			 															</div>
																	</div>  
														</div>
														<div class="col-md-10">
																<div class="form-inline col-md-12" style="padding-top:5px;">
																
											                          <div class="input-group col-md-3">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Request ID</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("request_id")) %></div> 
											                          </div>
											                          
											                          <div class="input-group col-md-4">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Aadhaar ID</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("proof_id")) %></div> 
											                          </div> 
											                          
											                          <div class="input-group col-md-4">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("sadarem_id")) %></div> 
											                          </div>  
										                        </div> 
																<div class="form-inline col-md-12" style="padding-top:5px;">
																
											                          <div class="input-group col-md-3">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Form No.</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("form_no")) %></div> 
											                          </div>
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("person_name")) %></div> 
											                          </div>
																
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name (Telugu)</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("person_name_telugu")) %></div> 
											                          </div> 
											                          
										                        </div> 
																<div class="form-inline col-md-12" style="padding-top:5px;">
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Birth</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("person_dob")) %></div> 
											                          </div>  
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Camp ID</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("camp_id")) %></div> 
											                          </div>
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Camp Name</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("camp_name")) %></div> 
											                          </div> 
											                          
										                        </div> 
																<div class="form-inline col-md-12" style="padding-top:5px;">
																
											                          
											                          <div class="input-group col-md-4">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Assessment</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("form_filled_date")) %></div> 
											                          </div> 
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Form Entered By</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("enrolled_by")) %></div> 
											                          </div>  
											                          
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Form Entry Date</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("enrolled_date")) %></div> 
											                          </div>
											                          
										                        </div> 
																<div class="form-inline col-md-12" style="padding-top:5px;">
																
											                          <div class="input-group">
											                            <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Application Status</div>
																  		<div class="form-control"><%=CommonUtility.checkNullObj(requestDetailsList.get("req_status_desc")) %></div> 
											                          </div>  
										                        </div> 
											       		</div>
											  	</div>
										      </div>
										 </div>
									</div>
								</div>
			   	 		
			   	 	<%
			    } 
    }
    
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
    %>
  </div>
</div>  
</body>
</html:html>