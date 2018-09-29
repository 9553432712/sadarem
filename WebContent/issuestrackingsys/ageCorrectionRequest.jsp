 <%@page import="java.util.ArrayList" %>
 <%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	ArrayList basicData = new ArrayList(); 
    ArrayList proofTypeList = new ArrayList();
	basicData = (ArrayList)request.getAttribute("sadaremData");
	if(basicData.size()>0)
	{
		basicData = (ArrayList)basicData.get(0);
	}
	ArrayList relationTypeList = new ArrayList();
	String gender = CommonUtility.checkNullObj(request.getAttribute("gender"));
	String genderId = CommonUtility.checkNullObj(request.getAttribute("genderId"));
	String selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType")); 
	String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
	proofTypeList =(ArrayList) request.getAttribute("proofTypeList") ;
	String statusmsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
	String issueName = CommonUtility.checkNullObj(request.getAttribute("issueName"));
%>
  <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
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
	      
		   if($('#dob').val()=="" || $('#dob').val()==null || $('#dob').length == 0)
			  {
			      alert("Please select date of birth");
			      $('#dob').focus();
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
					document.name_issue.action="<%=request.getContextPath()%>/ageChangeIssue.do";
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
		 
		 g_calendarObject =	new JsDatePick({
		    	
				
//				minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
	  			useMode:2,
	  			target:"dob",
	  			//dateFormat:"%d-%M-%Y",
	  			//selectedDate:{day:25,month:12,year:2013},
	  			yearsRange:[1900,2016],
	  			minDate: 0,
	  			limitToToday:true,
	  			cellColorScheme:"beige",
	  			dateFormat:"%d/%m/%Y",
	  			imgPath:"img/",
	  			weekStartDay:1,
	  			
	         });	
		 
		 g_calendarObject.addOnSelectedDelegate(function(){
			   var dateString= $('#dob').val();
			   var parts = dateString.split('/');
			   var today = new Date();
			   var birthDate = new Date(parts[2],parts[1]-1,parts[0]);
			   var age = today.getFullYear() - birthDate.getFullYear();
			   var m = today.getMonth() - birthDate.getMonth();
			    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) 
			    {
			        age--;
			    }
			    
			   $('#ageVal').html('Age : '+age);
	      	});
 			
});
  
  
  
 
</script>  

<!-- new -->

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
					<marquee><font color="red">For age correction Aadhar card is not accepted as a proof document.</font></marquee>
					 <div class="col-sm-12" style="text-align:center;">     <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> </div>
					    <div class="row">
					            
								<div class="col-sm-6" style="float:left;width:40%;">
								
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
					                              
					                                <input type="text"  id="dob"  name="dob" class="form-control" value='<%=basicData.get(12)%>'  style="border:solid 1px #609ebf;" size="10" readonly="readonly"/> 
					                             <div class="input-group-addon" style="color:red;" id="ageVal" ></div>
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
												                 <input type="text" id="proofid_1" name="proofid_1" class="form-control" autocomplete=off  maxlength=30 onblur="validate('proofidentity_1','proofid_1')" />
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
												                 <input type="text" id="proofid_2" name="proofid_2" class="form-control" autocomplete=off maxlength=30 onblur="validate('proofidentity_2','proofid_2')" />
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
					                         <div class="input-group col-md-6" style="float: left;">
					                            <button id="cancelissueButID" class="btn icon-btn-primary btn-primary" style="background-color:red;border:red;" type="button">
					                           		 <strong><font style="color:white">Cancel</font></strong>
					                           </button>
					                        </div>
					                          	<div class="input-group col-md-6" style="float: right;">
					                            <button id="raiseissueButID" class="btn icon-btn-primary btn-primary" type="button">
					                           		 <strong><font style="color:white">Submit</font></strong>
					                           </button>
					                        </div>
					                     </div>
					              </div>
					        </div>
		            </div>
		            
		              <div class="col-sm-6" style="float:left;width:50%;">
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
	                                  <li>Maximum size limit of the document is 5MB.</li> 
	                                  <li>Proof ID is the ID number of your proof.For eg:Aadhar card number is the proof ID if you select Aadhar Card as proof type.</li>
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



<!-- end -->