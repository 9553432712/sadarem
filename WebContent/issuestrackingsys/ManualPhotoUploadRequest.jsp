 <%@page import="java.util.ArrayList,com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil" %>
 <!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <%
	 
	ArrayList basicData = new ArrayList(); 
    ArrayList proofTypeList = new ArrayList();
	basicData = (ArrayList)request.getAttribute("sadaremData");
	if(basicData.size()>0)
	{
		basicData = (ArrayList)basicData.get(0);
	}
	String selIssueType = (String)request.getAttribute("selIssueType");
	String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
	proofTypeList =(ArrayList) request.getAttribute("proofTypeList") ;
	String statusmsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
	String issueName = CommonUtility.checkNullObj(request.getAttribute("issueName"));
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
<script>
	$(document).ready( function()
	 {
		
document.title = "::SADAREM :: ";
		
		
		$("#printbutid").click(function()
				{  
					  w=window.open(null, 'Print_Page', 'scrollbars=yes');
					  w.document.write('<html><head><title>::Print::</title>');
					  w.document.write('<style>@media print {  #buthide, #closebutid { display: none !important; }</style></head><body>')
				      w.document.write("Printed On : <%=CommonUtility.getDateTime("dd/MMM/yyyy hh:mm:ss")%>"+jQuery('#printdivid').html());
				  w.document.write("<center><button type='button' id='buthide' onclick='window.print()'>Print</button>");
				  w.document.write("&nbsp;&nbsp;&nbsp;<button type='button' id='closebutid' onclick='window.close()'>Close</button>");
				  w.document.write("</center></body></html>");
				    w.document.close();
				   // w.print();
				});
		
	    $("#raiseissueButID").click(function ()
			{
	    	  if( ($('#manualPhoto').val()=="" || $('#manualPhoto').val()==null || $('#manualPhoto').length == 0))
				{
					alert("Please upload photo manually");
					$('#manualPhoto').focus();
					return false;
				}
	     
	    	  else if($('#proofidentity_1').val()=="-1" || $('#proofidentity_1').val()==null || $('#proofidentity_1').length ==0 )
	          {
	          	 $('#collapseOne').toggleClass('panel panel-collapse in');
	          	 $('#plusminus').toggleClass('glyphicon glyphicon-minus');
	      		alert("Please select the Proof Identity type");
	      		$('#proofidentity_1').focus();
	      		return false;
	      	  }
		      else if($('#proofid_1').val()=="" || $('#proofid_1').val()==null || $('#proofid_1').length ==0 )
	      	  {
	      		 $('#collapseOne').toggleClass('panel panel-collapse in');
	          	 $('#plusminus').toggleClass('glyphicon glyphicon-minus');
	      		alert("Please Enter Proof Id");
	      		$('#proofid_1').focus();
	      		return false;
	      	  }
		      else if($('#proofDoc_1').val()=="" || $('#proofDoc_1').val()==null || $('#proofDoc_1').length == 0)
		      {
			        		 $('#collapseOne').toggleClass('panel panel-collapse in');
			            	 $('#plusminus').toggleClass('glyphicon glyphicon-minus');  
		      			alert("Please upload required document");
		      			$('#proofDoc_1').focus();
		      			return false;
		      }
	      	else if($('#proofidentity_1').val()==$('#proofidentity_2').val())
	      	{
	      		 $('#collapseTwo').toggleClass('panel panel-collapse in');
	          	 $('#plusminus2').toggleClass('glyphicon glyphicon-minus');
	      		alert("Please select a differnt Proof Identity type");
	      		$('#proofid_2').val('');
	      		$("#proofDoc_2").val('');
	      		$('#proofidentity_2').focus();
	      		return false;
	      	}
	          
	      	else if(($('#proofidentity_2').val() != -1) && ( $('#proofid_2').val()=="" || $('#proofid_2').val()==null || $('#proofid_2').length ==0 ))
	      	{
	      		 $('#collapseTwo').toggleClass('panel panel-collapse in');
	          	 $('#plusminus2').toggleClass('glyphicon glyphicon-minus');
	      		alert("Please Enter Proof Id");
	      		$('#proofid_2').focus();
	      		return false;
	      	}
	      	else if(($('#proofid_2').val() != -1) && ($('#proofid_1').val()==$('#proofid_2').val()))
        	{
        		$('#collapseTwo').toggleClass('panel panel-collapse in');
           	 	$('#plusminus2').toggleClass('glyphicon glyphicon-minus');
        			alert("Please enter different proof id");
        			$('#proofid_2').focus();
        			return false;
        	}
	      	else if(($('#proofidentity_2').val() != -1) && ($('#proofDoc_2').val()=="" || $('#proofDoc_2').val()==null || $('#proofDoc_2').length == 0))
	      	{
	      		$('#collapseTwo').toggleClass('panel panel-collapse in');
	         	 	$('#plusminus2').toggleClass('glyphicon glyphicon-minus');
	      			alert("Please upload required document");
	      			$('#proofDoc_2').focus();
	      			return false;
	      	}
	         else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').length == 0)
				{
					alert("Please enter Remarks");
					$('#decription').focus();
					return false;
				}
			else
				{  
					if(confirm("Are you sure you want to submit the request?"))
						{
						    //surname_many_words();
						    document.name_issue.target="_self";
							document.name_issue.action="<%=request.getContextPath()%>/photoChangeIssue.do";
							document.name_issue.submit();
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
						}
			     	  
			 	}
			});
	     $("#cancelissueButID").click(function ()
	 			{
	    			 if(confirm("Are you sure you want to cancel the request?"))
				     {
	    				 $('#decription').val(''); 
	    				 $('#proofDoc').val(''); 
	    	          return false;
				     }
	 			}); 
	     $('#proof1').click(function()
   		 {
    	      $('#collapseOne').toggle();
    	      $('#plusminus').toggleClass('glyphicon glyphicon-minus');
   		 });
	     $('#proof2').click(function()
   		 {
    	      $('#collapseTwo').toggle();
    	      $('#plusminus2').toggleClass('glyphicon glyphicon-minus');
   		 });
	     $('#proofidentity_2').change(function()
   	     {
         	   if($('#proofidentity_2').val() ==-1)
        		   {
        		       $('#proofid_2').val('');
           		   $("#proofDoc_2").val('');
        		   }
         });
           
        $('#proofidentity_1').change(function()
         {
        	   if($('#proofidentity_1').val() ==-1)
       		   {
       		       $('#proofid_1').val('');
          		   $("#proofDoc_1").val('');
       		   }
        });
	  }); 
</script>  
</head>

<body>
<%try{ %>
<div class="main_container">
<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div> 
<form name="name_issue"  method="post" enctype="multipart/form-data">
<input type="hidden" name="sadaremid" id="sadaremid" value='<%=basicData.get(0)%>'>
<input type="hidden" name="selIssueType" id="selIssueType" value='<%=selIssueType%>'>
<input type="hidden" id="FormSessionID" name="FormSessionID" value="<%=FormID%>" />
 
			<div class="container" style="margin-top:20px;width:100%;">
					 <div class="col-sm-12" style="text-align:center;">     
							 <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> 
					 </div>
					 <div class="row">					          
						<div class="col-md-6" style="width:50%;float:left" >
							 <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                     Upload Photo
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;">
					                           <div class="form-group row" id="manualUpload"  > 
					                              <div class="input-group col-md-12" title="Photo"> 
					                                <div class="input-group-addon"  style="background-color: #2E9BD8; color:#fff; "> <span class="glyphicon glyphicon-paperclip">  Upload Photo</span><font color='red'>*</font></div>
					                                  <input type="file" class="form-control"   id="manualPhoto" name="manualPhoto">			                
					                                </div>
					                            </div>
					                          
					                           <div class="form-group row"> 
	                                            <div class="input-group-addon" data-toggle="collapse" data-target="#collapseOne"  id="proof1"  style="cursor: pointer;background-color: #2E9BD8; color:#fff;text-align:left;">
					                              <h4 class="panel-title"><span id="plusminus" class="glyphicon glyphicon-plus"></span>Attach Proof<font color="red">*</font></h4>
					                            </div>
					                            
					                              <div id="collapseOne" class=" panel-collapse collapse" style="width:90%;margin:0px auto;">
														 <div class="form-group row" style="align:center">
												            <div class="input-group col-md-12" title="Proofs">
												                 <div class="input-group-addon"  style="background-color: #2E9BD8; color:#fff;">Proof Type<font color="red">*</font></div>
												                 <%=ComboUtil.createStrComboBoxAuto("proofidentity_1",proofTypeList, "", "form-control", "",true,true, "")%>
		 												   
		   										                 <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;">Proof ID<font color="red">*</font></div>
												                 <input type="text" id="proofid_1" name="proofid_1" class="form-control"  autocomplete=off maxlength=30 onblur="validate('proofidentity_1','proofid_1')" />
												            </div>
												            </div>		
												         <div class="form-group row"> 
							                              <div class="input-group col-md-12" title="Document"> 
							                                <div class="input-group-addon"  style="background-color: #2E9BD8; color:#fff; "> <span class="glyphicon glyphicon-paperclip"> Proof Document</span><font color='red'>*</font></div>
							                                  <input type="file" class="form-control"   id="proofDoc_1" name="proofDoc_1">			                
							                                </div>
							                             </div> 
											       </div>   
										     </div>
										    
										     <div class="form-group row"> 	
											       <div class="input-group-addon" data-toggle="collapse" data-target="#collapseTwo"  id="proof2"  style="cursor: pointer;background-color: #2E9BD8; color:#fff;text-align:left;">
					                                   <h4 class="panel-title"><span id="plusminus2" class="glyphicon glyphicon-plus"></span>Attach Alternate Proof</h4>
					                              </div>
					                            
					                              <div id="collapseTwo" class=" panel-collapse collapse" style="width:90%;margin:0px auto;">
														 <div class="form-group row" style="align:center">
												            <div class="input-group col-md-12" title="Proofs">
												                 <div class="input-group-addon"  style="background-color: #2E9BD8; color:#fff;">Proof Type</div>
												                 <%=ComboUtil.createStrComboBoxAuto("proofidentity_2",proofTypeList, "", "form-control", "",true,true, "")%>
		 												   
		   										                 <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;">Proof ID</div>
												                 <input type="text" id="proofid_2" name="proofid_2" class="form-control" autocomplete=off  maxlength=30 onblur="validate('proofidentity_2','proofid_2')" />
												            </div>
												            </div>		
												         <div class="form-group row"> 
							                              <div class="input-group col-md-12" title="Document"> 
							                                <div class="input-group-addon"  style="background-color: #2E9BD8; color:#fff; "> <span class="glyphicon glyphicon-paperclip"> Proof Document</span></div>
							                                  <input type="file" class="form-control"   id="proofDoc_2" name="proofDoc_2">			                
							                                </div>
							                             </div> 
											       </div>  
										       </div> 
										       	
												<br>
					                             <div class="form-group row"> 
						                            <div class="input-group col-md-12" title="Description"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Contact No.</div>
		                                                   <input type="text" class="form-control " id="contid" name="contid" onkeypress="return NumbersOnly(event);" onBlur="validateContactdetailsms('contid','contidErrMsg')" autocomplete="off" maxlength="10"> 
															<span class="input-group-addon" id="contidErrMsg"></span>
	                                                 </div>
                                                   </div>
					                             <div class="form-group row"> 
						                            <div class="input-group col-md-12" title="Remarks"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Remarks<font color='red'>*</font></div>
		                                                   <textarea class="form-control" rows="2" id="decription" name="decription"  style="width:100%;height:100% " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);"  maxlength="200" ></textarea>
	                                                 </div>
                                                 </div>
                                                
                                                <div class="form-group row"> 
							                         <div class="input-group col-md-12" style="text-align: center;">
							                            <button id="cancelissueButID" class="btn icon-btn-primary btn-primary" style="background-color:red;border:red;" type="button">
							                           		 <strong><font style="color:white">Cancel</font></strong>
							                           </button>&nbsp;&nbsp;&nbsp;&nbsp;
							                            <button id="raiseissueButID" class="btn icon-btn-primary btn-primary" type="button">
							                           		 <strong><font style="color:white">Submit</font></strong>
							                           </button>
						                        	</div>
					                     	 </div>
					             </div>
					        </div>
		             </div>
		            
		              <div class="col-md-6" style="float:left;width:40%;">
					            <div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-check"></span>
					                      	Instruction
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;text-align: left;line-height: 25px;font-size: 14px; font-family:  Times New Roman;">
					                <ol>
	                                  <li>All fields marked as (<font color='red'>*</font>) are mandatory</li>
	                                  <li>Please avoid using special characters like <font color='blue' size="3"><b>',",<,></b></font> in description field</li>
	                                  <li>Only .pdf  file type is accepted for document</li>
	                                </ol>
	                             </div>
	                           </div>
	                           <%if(statusmsg.length()>0){ %>
	                             <div class="input-group col-md-6" style="float: center;">
					                            <button id="printbutid" class="btn btn-success btn-lg" type="button">
					                           		 <span class="glyphicon glyphicon-print"></span>Print 
					                           </button>
					            </div>
					            <%} %>
	                   </div>  
	                   
	             </div>
		</div>			    
</form>
<%@include file="/issuestrackingsys/printissuetrackingrequest.jsp" %>
</div>

<%}catch(Exception e){e.printStackTrace();}%>
</body>
</html>