 <%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.ArrayList" %>
 <%
   ArrayList basicData = new ArrayList(); 
   ArrayList mandalList = new ArrayList();
   ArrayList panchayatList = new ArrayList();
   ArrayList villageList = new ArrayList();
   ArrayList habitationList = new ArrayList();
   ArrayList proofTypeList = new ArrayList();
   ArrayList districtList = new ArrayList();
   
   String selIssueType = "",sadaremId="";
   String selmandal = "";
   String seldistrict = "";
   String selpanchayat="";
   String selvillage = "";
   String selhabitation = "";
   String decription="";
   
basicData       = (ArrayList)request.getAttribute("sadaremData");
districtList		= (ArrayList)request.getAttribute("DistrictList");
mandalList      = (ArrayList)request.getAttribute("mandalList");
panchayatList   = (ArrayList)request.getAttribute("panchayatList");
villageList     = (ArrayList)request.getAttribute("villageList");
habitationList  = (ArrayList)request.getAttribute("habitationList");
selIssueType = (String)request.getAttribute("selIssueType");

if(basicData.size()>0)
{
	basicData      = (ArrayList)basicData.get(0);
	seldistrict  = (String)basicData.get(1);
	selmandal      = (String)basicData.get(2);
	selpanchayat   = (String)basicData.get(3);
	selvillage     = (String)basicData.get(3);
	selhabitation  = (String)basicData.get(4);
}
String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
proofTypeList =(ArrayList) request.getAttribute("proofTypeList") ;
String issueName = CommonUtility.checkNullObj(request.getAttribute("issueName"));
%>
  <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
  
 <script>
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

 function loadvillages(){

		
     postRequest("<%=request.getContextPath()%>/ajax.do?action=loadVillageforDistrictChange&manId="+ $('#mandal').val()+"&distId="+ $('#districts').val()+"&randomid="+Math.random(),"villageTDID"); 
   
}

 function loadhabitation(){

		
     postRequest("<%=request.getContextPath()%>/ajax.do?action=loadHabitationforDistrictChange&manId="+ $('#mandal').val()+"&villageId="+ $('#villages').val()+"&distId="+ $('#districts').val()+"&randomid="+Math.random(),"habitationTDID");
   
}
 
 

 
$(document).ready( function()
		{

	 
	
				$('#districts').change(function()
				{
		       		postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmandalDistrictChange&distId="+ $('#districts').val()+"&sadaremId="+ $('#sadaremid').val()+"&randomid="+Math.random(),"mandal");
		      
				});
				$('#mandal').change(function()
				{
			       postRequest("<%=request.getContextPath()%>/ajax.do?action=loadPanchayatDistrictChange&manId="+ $('#mandal').val()+"&distId="+ $('#districts').val()+"&randomid="+Math.random(),"panchayatTDID");
			      
				});


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
	     
    	   if ($('#districts').val()==-1)
	       {
	                      alert("Please Select District type");
	                      $('#districts').focus();
	                      return false;
	       }
    	   else if ($('#mandal').val()==-1)
	       {
	                      alert("Please Select Mandal type");
	                      $('#mandal').focus();
	                      return false;
	       }
          
	      else if ($('#panchayats').val()==-1)
          {
              alert("Please Select Panchayat type");
              $('#panchayats').focus();
              return false;
          }
          
	      else if ($('#villages').val()==-1)
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

	      else if($('#houseno').val()=="" || $('#houseno').val()==null || $('#houseno').val().length == 0)
			{
				alert("Please enter House No");
				$('#houseno').focus();
				return false;
			}
	      
	      else if($('#pincode').val()=="" || $('#pincode').val()==null ||  $('#pincode').val().length!=6)
			{   
	    		alert("Please enter valid PinCode");
				$('#pincode').focus();
				return false;	
			}
	     	 else if($('#proofidentity_1').val()=="-1" || $('#proofidentity_1').val()==null || $('#proofidentity_1').length ==0 )
          	 {
	          	 
	      		alert("Please select the Proof Identity type");
	      		$('#proofidentity_1').focus();
	      		return false;
	      	  }
		      else if($('#proofid_1').val()=="" || $('#proofid_1').val()==null || $('#proofid_1').length ==0 )
	      	  {
		      		
		      		alert("Please Enter Proof Id");
		      		$('#proofid_1').focus();
		      		return false;
	      	  }
		      else if($('#proofDoc_1').val()=="" || $('#proofDoc_1').val()==null || $('#proofDoc_1').length == 0)
		      {			       
		      	   alert("Please upload required document");
		      	   $('#proofDoc_1').focus();
		      	   return false;
		      }
	      	else if($('#proofidentity_1').val()==$('#proofidentity_2').val())
	      	{
	      		
	      		alert("Please select a differnt Proof Identity type");
	      		$('#proofid_2').val('');
	      		$("#proofDoc_2").val('');
	      		$('#proofidentity_2').focus();
	      		return false;
	      	}
	      	else if( !($('#proofidentity_1').val()=='PR001') && !($('#proofidentity_2').val()=='PR001'))
	      		{
	      		 
	      		  alert("Please Upload Aadhar Card");
		      		$('#proofid_2').val('');
		      		$("#proofDoc_2").val('');
		      		$('#proofidentity_2').focus();
		      		return false;
	      		}
	          
	      	else if(($('#proofidentity_2').val() != -1) && ( $('#proofid_2').val()=="" || $('#proofid_2').val()==null || $('#proofid_2').length ==0 ))
	      	{
	      		
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
	      		
	      			alert("Please upload required document");
	      			$('#proofDoc_2').focus();
	      			return false;
	      	}
	       else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').val().length == 0)
			{
				alert("Please enter remarks");
				$('#decription').focus();
				return false;
			}
	      else
			{  
	    	  if(confirm("Are you sure you want to submit the request?"))
				{
				 document.address_issue.target="_self";
				 document.address_issue.action="<%=request.getContextPath()%>/DistrictaddressTypeChangeIssue.do";
				 document.address_issue.submit();
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
	  	   $('#proofidentity_2').change(function(){
        	   if($('#proofidentity_2').val() ==-1)
       		   {
       		       $('#proofid_2').val('');
          		   $("#proofDoc_2").val('');
       		   }
          });
          
          $('#proofidentity_1').change(function(){
       	   if($('#proofidentity_1').val() ==-1)
      		   {
      		       $('#proofid_1').val('');
         		   $("#proofDoc_1").val('');
      		   }
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
<form name="address_issue"  method="post" enctype="multipart/form-data">
<input type="hidden" name="sadaremid" id="sadaremid" value='<%=basicData.get(0)%>'>
<input type="hidden" name="selIssueType" id="selIssueType" value='<%=selIssueType%>'>
<input type="hidden" id="FormSessionID" name="FormSessionID" value="<%=FormID%>" />
					<div class="container" style="margin-top:20px;width:100%;">
					 <div class="col-sm-12" style="text-align:center;">     <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> </div>
					    <div class="row">
					           
								<div class="col-sm-6" style="float:left;width:40%;">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                       District Address Details Correction
					                    </h3>
					                </div>
					                <div class="panel-body " style="background-color: #eee;">
					                			<div class="form-group row"> 
						                            <div class="input-group col-md-12" title="">    
						                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;">District <font color='red'>*</font></div> 
	                                                     <%=ComboUtil.createStrComboBoxAuto("districts",districtList,seldistrict,"form-control","",true,true,"")%>
						                             </div>
					                            </div>
											 
					                             <div class="form-group row"> 
						                            <div class="input-group col-md-12" title="Mandal">    
						                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;">Mandal <font color='red'>*</font></div> 
	                                                     <%=ComboUtil.createStrComboBoxAuto("mandal",mandalList,selmandal,"form-control","",true,true,"")%>
						                             </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Panchayat">    
					                               <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Panchayat <font color='red'>*</font></div> 
					                                <div id="panchayatTDID">
                                                      <%=ComboUtil.createStrComboBoxAuto("panchayats",new ArrayList(),selIssueType, "form-control","onchange=loadvillages()",true,true,"")%>
                                                    </div> </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Village">    
					                             <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Village <font color='red'>*</font></div> 
					                                 <div id="villageTDID">
					                                 <%=ComboUtil.createStrComboBoxAuto("villages",villageList,selvillage, "form-control","onchange=loadhabitation()",true,true,"")%> 
                                                    </div>  </div>
					                            </div>
					                            <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Habitation">    
					                                <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" >Habitation <font color='red'>*</font></div> 
					                                <div id="habitationTDID">
					                                <%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,selhabitation, "form-control","",true,true,"")%>
                                                    </div>  </div>
					                            </div>
					                            
					                            
					                           <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="House No">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > House No. <font color='red'>*</font> </div> 
					                                <input type="text" name="houseno" id="houseno" class="form-control" autocomplete="off" onChange="stripHTML(this);"   value='<%=basicData.get(26)%>'  maxlength="50">  
					                             </div>
					                             
					                           </div>
					                           
					                           <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Pin Code">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > PIN Code <font color='red'>*</font> </div> 
					                                <input type="text" name="pincode" id="pincode" class="form-control" autocomplete="off"    value='<%=basicData.get(27)%>'   onkeypress="return NumbersOnly(event);"   maxlength="6">  
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
					                                   <h4 class="panel-title"><span id="plusminus2" class="glyphicon glyphicon-plus"></span>Attach Alternate Proof<font color="red">*</font></h4>
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
		                                                   <textarea class="form-control" rows="2" id="decription" name="decription"  style="width:100%;height:100% " onBlur="this.value = SpaceReplace(this.value);"onChange="stripHTML(this);"   maxlength="200" ></textarea>
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
					                      	Instructions
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;text-align: left;line-height: 25px;font-size: 14px; font-family:  Times New Roman;">
					                <ol>
	                                   <li>All fields marked as (<font color='red'>*</font>) are mandatory</li>
	                                  <li>Please avoid using special characters like <font color='blue' size="3"><b>',",<,></b></font> in description field</li>
	                                  <li>Only .pdf  file type is accepted for document</li>
	                                  <li>Maximum size limit of the document is 5MB.</li> 
	                                  <li>Proof ID is the ID number of your proof.For eg:Aadhar card number is the proof ID if you select Aadhar Card as proof type.</li>
	                                  <li>Upload Aadhar Card without fail.</li> 
	                                </ol>
	                             </div>
	                             </div>
	                             <div class="input-group col-md-6" style="float: center;">
					                            <button id="printbutid" class="btn btn-success btn-lg" type="button">
					                           		 <span class="glyphicon glyphicon-print"></span>Print 
					                           </button>
					            </div>
	                   </div>  
	                   
	                   
		       </div>
		</div>			    
</form>
</div>

<div id="printdivid" style="display: none;">
		<table width="95%" class="table table-striped table-bordered">
			<tr>
				<td width="50%" align="center">
						<table width="98%"  border="1" >
							<tr>
								<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><h2>${statusMsg}</h2></td>
							</tr>
							<tr>
								<td>Issue Type </td>
								<td><%=issueName %> </td>
							</tr>
							
							<tr>
								<td>SADAREM ID </td>
								<td><%=basicData.get(0)%></td>
							</tr>
							<tr>
								<td>Person Name (Surname Name)</td>
								<td><%=basicData.get(9)%> &nbsp;<%=basicData.get(10)%> </td>
							</tr>
							<tr> 
								<td>Date of Birth (DD/MM/YYYY)</td>
								<td><%=basicData.get(12)%> </td>
							</tr>
							<tr> 
								<td>Gender</td>
								<td><%=basicData.get(15)%></td>
							</tr> 
							<tr>
								<td>Relationship Details</td>
								<td>(<%=basicData.get(16)%>)<%=basicData.get(17)%></td>
							</tr> 
							<tr>
								<td>Certificate Status </td>
								<td><%=basicData.get(22)%> </td>
							</tr>
							<tr>
								<td>Disability</td>
								<td><%=basicData.get(29)%></td>
								
							</tr>
							<tr>
								<td>Proof ID </td>
								<td><%=basicData.get(14)%></td>
								
							</tr>
							<tr>
							 <td>District</td>
							 <td><%=basicData.get(5)%></td>
					        </tr>
                         	<tr>
                              <td>Mandal</td>
							  <td><%=basicData.get(6)%></td>
                            </tr>
					        <tr>
					           <td>Village</td>
							   <td><%=basicData.get(7)%></td>
					        </tr>
					        <tr>
					           <td>Habitation</td>
							   <td><%=basicData.get(8)%></td>
					        </tr>	        
							
						</table>
				</td>				
			</tr>
			<tr>
				<td>
					<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0"  >
						<tr>
							<td><span style="color:blue;font-weight:bold;font-size:15px;">Note:</span></td>
										
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">1. The request is pending for the approvals.</span></td>							
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">2. The requested changes will be reflect after all approvals.</span></td>
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">3. This is not the certificate of proof, it is an Acknowledgement.</span></td>
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">4. You can check the status of grievance at <b>http://sadarem.telangana.gov.in/sadarem/openIsuueTrackinghome.do</b> </span></td>
						</tr>
					</table>
				</td>
			</tr>					
		</table>
		<br></br>		
	
	</div>

<!-- end -->