
<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,org.bf.disability.Constants.CommonConstants"language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   	<%
	try 
	{ 
	  ArrayList issueTypeList = new ArrayList();
	  String selIssueType = "",sadaremId="";
	  issueTypeList = (ArrayList)request.getAttribute("issueTypeList");
	  selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType"));
	  
	  sadaremId = CommonUtility.checkNullObj(request.getAttribute("sadaremId"));

	  Integer roleId			= Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId"))); 
	  String distId				= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	  String aliveStatus		= CommonUtility.checkNullObj(request.getAttribute("aliveStatus"));
	  String proofStatus		= CommonUtility.checkNullObj(request.getAttribute("proofStatus"));
	  String mandalvalidation	= CommonUtility.checkNullObj(request.getAttribute("mandalvalidation"));
	  
	 
	%>

<script>
var sessroleid  =  <%=roleId %> ;	

$(document).ready( function()
		{
		  
	     $("#raiseissueButID").click(function ()
			{

	      
			    	 if ($('#issueType').val()=="-1")
		             {
		                 $("#errorMsg").html("Please Select Issue type");
		                 $("#errorMsg").addClass("in");
		                 $("#errorMsg").css("display", "block");
		                 $('#issueType').focus();
		                 return false;
		             }
					else if($('#sadaremid').val()=="" || $('#sadaremid').val()===null || $('#sadaremid').val().length<17)
					{
							$("#errorMsg").html("Please enter valid SADAREM ID");
			                $("#errorMsg").addClass("in");
			                $("#errorMsg").css("display", "block");
						
							return false;
					}
					
					else 
					{  
						/*Screen Locking Started */
						$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
					    $('#processlayer').css({"display": "block","z-index":"110000"});
					/*Screen Locking Ended */	
							 document.form_issue.target="_self";
								document.form_issue.action="<%=request.getContextPath()%>/IssueTrackingSystem.do";
								document.form_issue.submit();
					     	  
					 }
  	  
			});

	
		});

</script>

<style>

	.jqueryslidemenu ul li a:link,.jqueryslidemenu ul li a:visited {
	color:#000000;
	z-index:1000;
	height:30px!important;
}

	</style>	
 
<div class="main_container">
<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div> 


													<form id="form_issue" name="form_issue"  method="post" >	
													<input type="hidden" id="mode" name="mode" value="raiseIssue"/>
<% if(aliveStatus.length()>0 ||  proofStatus.length()>0 || mandalvalidation.length()>0){ %>	
										 <div   class="alert alert-danger fade in" style="height: 65px;padding-top:5px;width:80%;"   id="errorMsg" >
										    <%=aliveStatus.trim() %><%=proofStatus.trim() %><%=mandalvalidation.trim() %>
							            </div>
					                   
					                  <%}else{ %>
					                     <div   class="alert alert-danger fade" style="height: 50px;padding-top:5px;width:80%;display:none;"  id="errorMsg" >
										      
					                   </div>
					                  <%} %> 
					                   
					    <div class="row">
								<div class="col-xs-6">				 
								 							
					            <div class="panel panel-primary">
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Raise New Request
					                    </h3>
					                </div>
						          			<div class="panel-body">
								                         <div class="form-group">
								                            <div class="input-group" title="Issue Type">
								                              <div class="input-group-addon" style="text-align:left;  background-color: #2E9BD8; color:#fff;"><span class="glyphicon glyphicon-list-alt"></span> Issue Type</div> 
								                               	<%=ComboUtil.createStrComboBoxAuto("issueType",issueTypeList,selIssueType, "form-control","",true,true,"")%>
								                            </div>
								                          </div>
								                          
														  <div class="form-group">
								                            <div class="input-group" title="SADAREM ID" >
								                              <div class="input-group-addon" style="text-align:left; background-color: #2E9BD8; color:#fff;"> <span class="glyphicon glyphicon-star" style="color:red;"></span> SADAREM ID</div> 
								                              	<input type="text" name="sadaremid" id="sadaremid" class="form-control" value="" autocomplete="off" onkeypress="return NumbersOnly(event);" maxlength=17>
								                              </div>
								                          </div>
								                          <div class="form-group">
								                          	<div class="input-group" >
									                            <button class="btn icon-btn-primary btn-primary" class="btn btn-default" id="raiseissueButID"  type="button">
									                           		 <strong><font style="color:white">Submit</font></strong>
									                           </button>
								                          	</div>
								                          </div>
								               	 </div>
								          </div>
								     </div>
					       
							 <div class="col-xs-5">
							   <div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-check"></span>
					                      	Instructions
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;text-align: left;line-height: 25px;font-size: 14px; font-family:  Times New Roman;">
					                <ol>
	                                  <li>All fields marked as (<font color='red'>*</font>) are mandatory</li>
	                                  <li>Enter SADAREM ID that belongs to your district.</li>
	                                  
	                                </ol>
	                             </div>
	                             </div>
							 </div>
			 </div>
			 </form>
					
</div>



<%}catch(Exception e){e.printStackTrace();} %>