<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<% try{ %>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility"%>


<%
			ArrayList BasicData = new ArrayList();
           String sadaremId=CommonUtility.checkNullObj(request.getAttribute("sadID"));
           String distId				= (String)session.getAttribute("districtId");
           Integer roleId				= Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));

			BasicData = (ArrayList)request.getAttribute("sadaremData");
			Integer finalResultLogTable=new Integer(0);
			finalResultLogTable=(Integer)request.getAttribute("DetagQueryResult");
			if(finalResultLogTable ==null)
				finalResultLogTable=new Integer(0);
			
			
			//System.out.println("finalResultLogTable"+finalResultLogTable);
			String errorMsg  =CommonUtility.checkNullObj(request.getAttribute("errorMsg"));
%>
   
<head>
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sadarem/css/bootstrap.min.css"> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
 .table tbody tr td {
       padding:2px;
       font-size:14px;
      
     }
     .hd_gd
   {
     border : #234466 1px solid;
     background-color:#337ab7;
   }
   .secondrow{
   border-color:#337ab7;
   }
   td{
    padding-left:0px;
   }
 </style>


<title>AadharDetag</title>
</head>
<body>
<form name="form_issue" id="form_issue" method="post">
<input type="hidden" id="mode" name="mode" value="">
	<div align="center" style="font-size:20px; color:red;">	
		<%if(errorMsg!=null && errorMsg.length()>0)
		{ 
			out.print(errorMsg);
		}
		%>
	</div>

		 <div class="col-md-5 col-md-offset-3">
				<div class="panel panel-primary">				            		 
					<div class="panel-heading" style="text-align: center;"><b> Aadhaar Detag  </b></div>
						<div class="panel-body">
							<div class="form-group" style="height:30px;padding-top:5px;">
						         <div class="input-group">
				              		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
									<input type="text" "text" id="sadaremID" name="sadaremID" class="form-control" autocomplete="off"  onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" maxlength="17"  value="<%=sadaremId %>"/>
								 </div> 
							</div>
								 <label for="usr" style="float: right;">			
									<input type="submit" class="btn btn-primary"  id="aadharSub" name="aadharSub" > 
								 </label> 	
			            </div>  
				</div>
	 		</div>  
 


	<%if(finalResultLogTable==1)
		{%>
		<h4 align="center" style="color:green">Aadhar Detagged Successfully</h4>
	<%} else{
	%>
	<!-- <h2>Aadhar Detagged Failed</h2> -->
	<%} %>

	  
	<%
	 
	if(BasicData!=null )
	if(!BasicData.isEmpty() &&( BasicData.get(13).toString().contains("Aadhar Card")||BasicData.get(13).toString().contains("Adhaar Card")||BasicData.get(13).toString().contains("Aadhaar Card") || BasicData.get(13).toString().contains("Aadhaar ID")))  
		{	
		if(finalResultLogTable!=1)
	    {
	%>   
 				<table  border="2" align="center" cellpadding="0" cellspacing="0" style="background:#F4FAFF;width:70%;"  class="table table	">

					
										
								   <tr>
											  <td  style="padding-left:8px;"><b>SADAREM ID   </b><%=BasicData.get(0) %></td>
											  <td style="padding-left:8px;"> <b>Name</b><br><%=BasicData.get(9)%><%=BasicData.get(10)%><br>(<%=BasicData.get(11)%>)</td>										
											  <td style="padding-left:8px;"><b>Gender</b><br>(<%=BasicData.get(15)%>)</td>
											  <td style="padding-left:8px;"><b>Certificate Status</b><br>(<%=BasicData.get(22)%>) </td>										  	
								   </tr>	
								   <tr>
											  <td style="padding-left:8px;"><b>Date Of Birth</b><br>(<%=BasicData.get(12)%>)</td>
											  <td style="padding-left:8px;"><b>Relation Name</b><br>(<%=BasicData.get(16)%>)<%=BasicData.get(17)%><br>(<%=BasicData.get(18)%>)</td>
											  <td style="padding-left:8px;"><b>Contact Number</b><br><%=BasicData.get(19)%></td>
											  <td style="padding-left:8px;"><b>Disability</b><br>(<%=BasicData.get(32)%>) </td>
                                   </tr> 
								   <tr>
											  <td style="padding-left:8px;"><b>Person Status</b><br>(<%=BasicData.get(23)%>) </td>
											  <td style="padding-left:8px;"><b>Proof ID</b><br><%=BasicData.get(14)%><br>(<%=BasicData.get(13)%>)</td>		
											  <td style="padding-left:8px;"><b>Certificate Issued Date</b><br>(<%=BasicData.get(34)%>)</td>										
											  <td style="padding-left:8px;"><b>Address:</b><br><%=BasicData.get(5)%>(D),<%=BasicData.get(6)%>(M),<br><%=BasicData.get(7)%>(V),<%=BasicData.get(8)%>(H)</td>										
								  </tr>	
																							
				</table>
			
				<br>
				
						 <table align="center" >
							 <tr>
									<td style="color:black ;font-size:15px; height:35px;">Remarks<font color="red">*</font>	</td>
									<td><input type="text" maxlength="280" autocomplete="off" id="rmrk" name="rmrk" style="width:350px;height:45px;"></td>		
								    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td rowspan=2><button type="button" class="btn btn-success"  id="remarkSubmit" name="remarkSubmit">SUBMIT</button></td>
							 </tr>
					
						 </table> 
				<%} %>
	
	<%}
				else if(BasicData.isEmpty()){
				    %>
				<h4 align="center" style="color:red">Invalid SADAREM ID</h2>
			
				<%}
				else{%>
				<h4 align="center" style="color:red"> No Aadhar ID is tagged to this SADAREM ID</h2>
				<%}%>
	

</form>
<script type="text/javascript">
var sessroleid  =  <%=roleId %> ;
$(document).ready(function()
{
	 $("#aadharSub").click(function( event )
	  {
		 try
		 {
				var sadaremID=document.getElementById("sadaremID").value;	
				//alert(sadaremID.length);
				if(sadaremID.length==0)
				{
					alert("Please enter SADAREM ID");
					return false;
				}					 
				else if(sadaremID.length<17 || sadaremID.length>19)
				{
					alert("Please enter valid SADAREMID");
					return false;
					
				}
				<%-- else  if($('#sadaremID').val().substr(0, 2)!= <%=distId%> && sessroleid!=97 )
				{
					 $("#errorMsg").html("SADAREM ID doesn't belong to this district.");
		                $("#errorMsg").addClass("in");
		                $("#errorMsg").css("display", "block");
					 return false;
				} --%>
				else
				{
					
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
						    document.getElementById("mode").value="AadharDetagMethod";
							document.form_issue.target="_self";
							document.form_issue.action="<%=request.getContextPath()%>/AadharDetag.do";
							document.form_issue.submit();
				}
			}
			 catch(e)
			 {
				 alert(e);
				 return false; 
			 }
	  });
	 
	 
	 $("#remarkSubmit").click(function( event )
	 {
				 try
				 {
	 						
					 var rmrk=document.getElementById("rmrk").value;	
						//alert(sadaremID.length);
						if(rmrk.length==0)
						{
							alert("Please enter remarks");
						}
						else if(confirm("Do You Want Detag"))
						{
							
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
							/*Screen Locking Ended */
							
						    document.getElementById("mode").value="Remarks";
							document.form_issue.target="_self";
							document.form_issue.action="<%=request.getContextPath()%>/AadharDetag.do";
							document.form_issue.submit();
						}					 
				 }
				 catch(e){alert(e);return false;}
	  });
});


</script>
</body>
<%}

catch(Exception e)
{
	System.out.println(e);
}

%>
</html>
