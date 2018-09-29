<%--
    Document   : aadharCardMapping
    Created on : 1 Oct, 2014, 3:33:29 PM
    Author     : 728056
--%>

<%@page import="java.util.*, com.tcs.sadarem.util.CommonUtility;"%>
<%try{ %>
<html>
	<head>
		<style>
 

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
		</style>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	</head>
	<%
	
	
	ArrayList resultList = new ArrayList();
	HashMap sadaremData = new HashMap();
	
	resultList=(ArrayList)request.getAttribute("sadaremDetails");
	
	System.out.println("resultList"+resultList);
	String errmsg = CommonUtility.checkNull((String)request.getAttribute("errmsg"));
	String succmsg = CommonUtility.checkNull((String)request.getAttribute("succmsg"));
	String nodata = CommonUtility.checkNull((String)request.getAttribute("nodata"));
	
	
	%>
	<body>
		<div id="processlayer" >
			<font color="blue" size="2">Processing Please Wait...</font><br/>
			<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
		</div>
		
		<div id="blocklayer">
		</div>
		<form name="AadharCardMappingForm" >
		
			<input type="hidden" name="mode" id="mode" value=""/>
			<div align="center"><%if(errmsg.length()>0){ %>
			<h4><label><%=errmsg %></label></h4>
			<%} %>
			<%if(succmsg.length()>0){ %>
			<h4><label><%=succmsg %></label></h4>
			<%} %>
			<%if(nodata.length()>0){ %>
			<h4><label><%=nodata %></label></h4>
			<%} %>
			</div>
			<div class="row">
				<div class="col-md-5 col-md-offset-3">
					<div class="panel panel-primary">				            		 
						<div class="panel-heading" style="text-align: center;"><b> AADHAR ID Mapping</b></div>
							<div class="panel-body">
								<div class="form-group" style="height:30px;padding-top:5px;">
							         <div class="input-group">
					              		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
											<input type="text" id="personcode" name="personcode" class="form-control" maxlength="17" autocomplete="off" value="<%= CommonUtility.checkNullObj(sadaremData.get("personCode"))%>" />
									 </div> 
								</div>
									 <label for="usr" style="float: right;">			
						                    <input type="button" name="getDataBut" id="getDataBut" value="Submit" class="btn btn-primary"/>			
									 </label> 	
				            </div>  
					</div>
		 		</div>
	 		</div>
	 		<% 
	 		
	 		 if(resultList!=null && resultList.size()>0)
             {
	 			sadaremData = (HashMap)resultList.get(0); 		
	 		%> 
		
			<div class="row" align="center">
				<input type="hidden" id="personC" name="personC" class="form-control"  value="<%= CommonUtility.checkNullObj(sadaremData.get("personCode"))%>" />
				<table class="table table-striped table-bordered" style="width:80% !important;">
					<thead style="background-color: #337a;">
						<tr>
							<th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">SADAREM ID</th>
							<th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Person Nmae</th>
							<th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Relation</th>
							<th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Address</th>
							<th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Aadhar ID</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><%= CommonUtility.checkNullObj(sadaremData.get("personCode"))%></td>
							<td><%= CommonUtility.checkNullObj(sadaremData.get("name"))%></td>
							<td><%= CommonUtility.checkNullObj(sadaremData.get("relationName"))%></td>
							<td><%= CommonUtility.checkNullObj(sadaremData.get("address"))%></td>
							<td>
								<input type="text" name="aadharCardId" id="aadharCardId"  >
							</td>
							
						</tr>
					</tbody>
					
					
				
				</table>
				
			</div>
			<div align="center" class="row"> 
				<input type="button" id="MAP"  value="MAP" class="btn btn-success"> 
			</div>
			<%} else{ %>
				
			<%} %>
		</form>
		
		
		<script type="text/javascript">
		$(document).ready( function()
		{
			$('#getDataBut').click(function()
			{
				try
				{
					if($('#personcode').val()==null||$('#personcode').val()=="")
	                {
	                	alert("Please enter SADAREM ID");
	                	$('#personcode').focus();
	                	return false;
	                }
	                var numericExpression = /\d{17}$/;
	                
	                if(!$('#personcode').val().match(numericExpression))
	                {
	                	alert("SADAREM ID must be Integer");
	                	$('#personcode').focus();
	                	return false;
	                }
	                
	                if($('#personcode').val().length !=17)
	                {
	                	alert("SADAREM ID must be exactly of 17 numbers");
	                	$('#personcode').focus();
	                	return false;
	                }
	                
	                $("#mode").val("getValidSADAREMDetails");
	                document.AadharCardMappingForm.target="_self";
					document.AadharCardMappingForm.action="<%=request.getContextPath()%>/aadharCardMapping.do";
					document.AadharCardMappingForm.submit();
					/*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				/*Screen Locking Ended */
				}
				catch(e)
				{
					alert(e);
				}
				
			                
			            
			});
			
			$('#MAP').click(function()
			{
						try
						{
							if($('#aadharCardId').val()==null||$('#aadharCardId').val()=="")
			                {
			                	alert("Please enter AADHAR ID");
			                	$('#aadharCardId').focus();
			                	return false;
			                }
							if($('#aadharCardId').val().length !=12)
			                {
			                	alert("AADHAR ID must be exactly of 12 numbers");
			                	$('#aadharCardId').focus();
			                	return false;
			                }
			                var numericExpression = /\d{12}$/;
			                
			                if(!$('#aadharCardId').val().match(numericExpression))
			                {
			                	alert("AADHAR ID must be Integer");
			                	$('#aadharCardId').focus();
			                	return false;
			                }
			                
			                
			                 if(!fun_validateAadhaarID($('#aadharCardId').val()))
			                {
			                	alert("Please enter valid AADHAR ID");
			                	$('#aadharCardId').focus();
			                	return false;
			                }
			                $("#mode").val("updatePersonalDetails");
			                $("#personcode").val($("#personC").val());
			                
			                document.AadharCardMappingForm.target="_self";
							document.AadharCardMappingForm.action="<%=request.getContextPath()%>/aadharCardMapping.do";
							document.AadharCardMappingForm.submit();
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
						}
						catch(e)
						{
							alert(e);
						}
						
					                
					            
					});
			
		});
		
		
		</script>
	
	
	
	</body>

</html>
<%}
catch(Exception e)
{
	e.printStackTrace();
}%>