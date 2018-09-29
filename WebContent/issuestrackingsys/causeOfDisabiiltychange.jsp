 <%@page import="java.util.ArrayList,com.tcs.sadarem.util.CommonUtility" %>
 <%
 
 ArrayList basicData = new ArrayList(); 
basicData = (ArrayList)request.getAttribute("sadaremData");
if(basicData.size()>0)
{
	basicData = (ArrayList)basicData.get(0);
}
String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
String fullname = "&#3114;&#3138;&#3120;&#3149;&#3108;&#3135; &#3114;&#3143;&#3120;&#3137;";
String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
String selIssueType = (String)request.getAttribute("selIssueType");
String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
%>
 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
 <script>

 
 
$(document).ready( function()
		{
	
	 
	     $("#raiseissueButID").click(function ()
			{
	    	 
	      	

	    	 if($('#causename').val()=="" || $('#causename').val()==null || $('#causename').length == 0)
			{
				alert("Please enter Cause Of Disability ");
				$('#causename').focus();
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
						document.name_issue.action="<%=request.getContextPath()%>/CauseOfDisabilityChangeIssue.do";
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
	    				 $('#nameChangeDoc').val(''); 
	    	          return false;
				     }
	 			}); 

	
		});

 
</script>  

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
					 <div class="col-sm-12" style="text-align:center;">     <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> </div>
					    <div class="row">
					          
								<div class="col-md-6" style="width:50%;float:left" >
								
					            <div class="panel panel-primary" >
					                <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title">
					                        Cause Of Disability Correction
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;">

					                           
					                             <div class="form-group"> 
					                            <div class="input-group" title="Cause of disability Name">    
					                               <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff;"> Cause Of Disability <font color='red'>*</font> </div> 
					                                <input type="text" name="causename" id="causename" class="form-control"  autocomplete="off" value='<%=basicData.get(32)%>' onBlur="this.value = SpaceReplace(this.value);" onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="150"> 
					                             </div>
					                            </div>
					                            <div class="form-group row"> 
						                            <div class="input-group col-md-12" title="Description"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Contact No.</div>
		                                                   <input type="text" class="form-control " id="contid" name="contid" onkeypress="return NumbersOnly(event);" onBlur="validateContactdetailsms('contid','contidErrMsg')" autocomplete="off" maxlength="10"> 
															<span class="input-group-addon" id="contidErrMsg"></span>
	                                                 </div>
                                                   </div>
					                            <div class="form-group"> 
						                            <div class="input-group" title="Description"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Remarks<font color='red'>*</font></div>
		                                                   <textarea class="form-control" rows="2" id="decription" name="decription"  style="width:100%;height:100% " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);"  maxlength="200" ></textarea>
	                                                 </div>
                                                 </div>
                                                 
					                     
					                         <div class="form-group">
					                         <div class="input-group" style="float: left;">
					                            <button id="cancelissueButID" class="btn icon-btn-primary btn-primary" style="background-color:red;border:red;" type="button">
					                           		 <strong><font style="color:white">Cancel</font></strong>
					                           </button>
					                        </div>
					                          	<div class="input-group" style="float: right;">
					                            <button id="raiseissueButID" class="btn icon-btn-primary btn-primary" type="button">
					                           		 <strong><font style="color:white">Submit</font></strong>
					                           </button>
					                        </div>
					                     </div>
					             </div>
					        </div>
		            </div>
		            
		              <div class="col-md-6" style="float:left;width:40%">
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
	                                  <!-- <li>Only .pdf  file type is accepted for document</li> -->
	                                </ol>
	                             </div>
	                             </div>
	                   </div>  
	                    
		       </div>
		</div>			    
</form>
</div>