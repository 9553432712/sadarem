 <%@page import="java.util.ArrayList" %>
 <%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
    ArrayList basicData = new ArrayList(); 
    ArrayList proofTypeList = new ArrayList();
    ArrayList ApelateCategoryList = new ArrayList();
    basicData = (ArrayList)request.getAttribute("sadaremData");
	if(basicData.size()>0)
	{
		basicData = (ArrayList)basicData.get(0);
	}
	
	ApelateCategoryList = (ArrayList)request.getAttribute("AppelateCategory");
	String selIssueType = CommonUtility.checkNullObj(request.getAttribute("selIssueType")); 
	String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
	proofTypeList =(ArrayList) request.getAttribute("proofTypeList") ;
	String issueName = CommonUtility.checkNullObj(request.getAttribute("issueName"));
	String statusmsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
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
		   
		   if($('#ApelateCategory').val()=="-1" || $('#ApelateCategory').val()==null || $('#ApelateCategory').length==0)
			   {
			       alert("Please select Category");
			       $('#ApelateCategory').focus();
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
	      	else if( !($('#proofidentity_1').val()=='PR008') && !($('#proofidentity_2').val()=='PR008'))
	      		{
	      		 
	      		  alert("Please Upload Authorised Approval Letter");
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
				document.name_issue.action="<%=request.getContextPath()%>/appellateAuthorityIssue.do";
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
					<marquee><font color="red">After the Final Approval of grievance,Further process of Part-B will be done in SPMU.</font></marquee>
					 <div class="col-sm-12" style="text-align:center;">     <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> </div>
					    <div class="row">
					            
								<div class="col-sm-6" style="float:left;width:50%;">
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                       Appellate Authority Assessment - SPMU level
					                    </h3>
					                </div>
					             
					                 <div class="panel-body" style="background-color: #eee;">
											 <div class="form-group row"> 
					                            <div class="input-group col-md-12" title="Gender">
					                              <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;" > Category <font color='red'>*</font> </div> 
					                               <%=ComboUtil.createStrComboBoxAuto("ApelateCategory",ApelateCategoryList, "", "form-control", "",true,true, "")%>
		 										</div>
					                           </div>
					                             
			
				<div class='form-group row'>									       	
			     <div class='panel panel-primary' >
			 	 <div class='panel-heading'> <b>Please Upload Documents</b></div>
			 	 <div class='panel-body'>
			     <div class='form-group row'>
			     <table>
			      <tr style='border: 1px solid #337ab7;'>
				   <th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>S no<font color='red'>*</font></th>
				   <th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>Proof Type<font color='red'>*</font></th>
				   <th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>Proof ID<font color='red'>*</font></th>
				   <th style='background-color: #2E9BD8;color: #fff;height: 50px;width: 15%;border: 1px solid #337ab7; text-align: center;'>File<font color='red'>*</font></th>
				  </tr>
				  <tr>
				  <td style='border: 1px solid #337ab7;text-align: center;font-weight: bold;'>1<font color='red'>*</font></td>
				  <td style='border: 1px solid #337ab7;'>
				  <%=ComboUtil.createStrComboBoxAuto("proofidentity_1",proofTypeList, "", "form-control", "", true,true, "")%>
				  </td>
				  <td style='border: 1px solid #337ab7;'><input type='text' name='proofid_1' id='proofid_1' class='form-control' autocomplete='off'  maxlength='30'  onblur="validate('proofidentity_1','proofid_1')"></td>
			      <td style='border: 1px solid #337ab7;'><input type='file' class='form-control'   id='proofDoc_1' name='proofDoc_1'></td>
				  </tr>
			      <tr style='border: 1px solid #337ab7;'>
				  <td style='border: 1px solid #337ab7;text-align: center;font-weight: bold;'>2<font color='red'>*</font></td>
				  <td style='border: 1px solid #337ab7;'>
				  <%=ComboUtil.createStrComboBoxAuto("proofidentity_2",proofTypeList, "", "form-control", "",true,true, "")%>
				  </td>
				  <td style='border: 1px solid #337ab7;'><input type='text' name='proofid_2' id='proofid_2' class='form-control' autocomplete='off' maxlength='30' onblur="validate('proofidentity_2','proofid_2')"></td>
				  <td style='border: 1px solid #337ab7;'><input type='file' class='form-control'   id='proofDoc_2' name='proofDoc_2'></td>
				  </tr>
				 </table>
			    </div>
			    </div>
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
		            
		              <div class="col-sm-6" style="float:left;width:40%;">
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
	                                   <li>Upload PD approval letter under Authorised approval letter category without fail.</li> 
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