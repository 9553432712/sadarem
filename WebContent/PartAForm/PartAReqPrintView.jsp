<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.HashMap,com.tcs.sadarem.util.CommonUtility"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: Part-A Request View/Print</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: Part-A Decision</title><title>:: SADAREM :: Part-A Form View/Print</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
 <%
 
	HashMap myRequestListDtls = (HashMap)request.getAttribute("PartARequestFullDltsList");
 
 	String alert_msg = CommonUtility.checkNullObj(request.getAttribute("alert_msg"));
	String alert_css = CommonUtility.checkNullObj(request.getAttribute("alert_css"));
	
	if(alert_css.equals(""))
	{
		alert_css = "alert-info";
	}
 
%>  
<style type="text/css">
    
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

/* 	 Process Layer Ended  */
  
</style> 
</head>
<body>
<div id="processlayer" >
	<font color="blue" size="2">Processing Please Wait...</font><br/>
	<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
</div>

<div id="blocklayer">
</div>  
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
	%>

<%
if(myRequestListDtls!=null)
{
%>
<div id="PartARequestPrintViewDIVID">
		<table class="table table-striped table-bordered dataTable" style="font-size: 15px; border: 1px 1px 1px 1px;"> 
		   <thead>
		   <tr>
		   		<th colspan="2" style="background-color:#fbeacc;padding:5px; font-weight: bold; text-align:center; vertical-align: middle; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Part-A Request Id (<%=CommonUtility.checkNullObj(myRequestListDtls.get("request_id")) %>) Details</th>
		   </tr>
		     <tr> 
		       <th style="background-color:#fbeacc;padding:5px; text-align:center; vertical-align: middle; font-weight: bold; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Contact Details</th>
		       <th style="background-color:#fbeacc;padding:5px; text-align:center; vertical-align: middle; font-weight: bold; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Personal Details</th> 
		  	 </tr> 
		 	</thead> 
		 	 <tbody>
			  <tr  style="background-color: #f7f5f1 !important;">
			   <td style="text-align: center; vertical-align:top;width: 40%">
				   	<table class="table table-striped table-bordered dataTable" style="font-size: 12px; border: 1px 1px 1px 1px;"> 
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">District</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("district_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Mandal</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("mandal_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Panchayat</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("panchayat_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Village</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("village_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Habitation/Ward No</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("habitation_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">House No.</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("house_number")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Pin Code</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("pin_code")) %></td>
				   		</tr>		
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Contact No.</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("phone_no")) %></td>
				   		</tr>		
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">eMail Id</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("email")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Type of Pension</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("pension_type")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Pension Number</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("pensioncard_no")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Consanguineous Marriage of Parents</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("parents_marriage_desc")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">EPIC Card</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("epiccard_no")) %></td>
				   		</tr>				   	
				   	</table>			   
			   </td> 
		       <td style="text-align:center; vertical-align: top;width: 60%">
		       		<table class="table table-striped table-bordered dataTable" style="font-size: 12px; border: 1px 1px 1px 1px;"> 
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: center; vertical-align: middle; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; " rowspan="4">
								<div class="img_circular" style="background-size: cover;width:150px;height: 150px;"> 
									<img style="width:100%; height:100%" src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&requestID=<%=CommonUtility.checkNullObj(myRequestListDtls.get("request_id")) %>&randiomid=<%=Math.random()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultprofile.png'"/>
								</div>
							</td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">SADAREM ID</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("sadarem_id")) %></td>
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Aadhaar Card No</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("proof_id")) %></td>
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Form No.</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("reference_form_number")) %></td>
				   		</tr>		
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Date of Assessment</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("form_filled_date")) %></td>
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Sur-Name</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("surname")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Person Name</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("first_name")) %></td>				   		
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Gender</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("gender_name")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Date of Birth</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("date_of_birth")) %></td>				   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Full Name (Telugu)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("personname_telugu")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Marital Status</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("marital_status_desc")) %></td>						   		
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Relation Details</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("relationship_type_desc")) %>&nbsp;<%=CommonUtility.checkNullObj(myRequestListDtls.get("relation_name")) %></td>	
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Relation Name(Telugu)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("relationname_telugu")) %></td>	   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Education</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("edu_desc")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Employment</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("employement_type_desc")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Religion</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("religion_desc")) %></td>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Caste</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("caste_desc")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Ration Card No</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_number")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Ration Card Type : <%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_type_desc")) %></td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Slno : <%=CommonUtility.checkNullObj(myRequestListDtls.get("rationcard_slno")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Identification Marks 1)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("mole_one")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Identification Marks 2)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(myRequestListDtls.get("mole_two")) %></td>						   		
				   		</tr>
				   	</table>		
		       
		       </td>  
			  </tr>
			  <tr>
			  	<th colspan="2" style="text-align: left; font-size: 13px;"><font color="blue"><b>Present Status : </b></font><%=CommonUtility.checkNullObj(myRequestListDtls.get("req_status_desc")) %><font color="blue"><b>, Last Updated on </b></font> <%=CommonUtility.checkNullObj(myRequestListDtls.get("last_updated_date")) %></th>
			  </tr>
			  <tr>
			  	<th colspan="2" style="text-align: left; font-size: 13px;"><font color="blue"><b>Note : Its only an acknowledgement.</b></font></th>
			  </tr>
			 </tbody>
			</table>
</div>
		<center><button type="button" class="btn btn-info" id="PartARequestPrintBtnId"> Print </button></center>
		
<%
}
else
{
	%>
		<div class="alert alert-danger"><b>No details are found to display</b></div>
	<%
}
%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>    


<script type="text/javascript">
   	
   $(document).ready(function()
			{ 
	   			
	   			
	   			$("#PartARequestPrintBtnId").click(function()
	   					{  
		   					  w=window.open(null, 'Print_Page', 'scrollbars=yes');
		   					  w.document.write('<html><head><title>::Print::</title>');
		   					  w.document.write('<style>@media print {  #buthide, #closebutid { display: none !important; }</style></head><body>')
		   				      w.document.write("Printed On : <%=CommonUtility.getDateTime("dd/MMM/yyyy hh:mm:ss")%>"+jQuery('#PartARequestPrintViewDIVID').html());
							  w.document.write("<center><button type='button' id='buthide' onclick='window.print()'>Print</button>");
							  w.document.write("&nbsp;&nbsp;&nbsp;<button type='button' id='closebutid' onclick='window.close()'>Close</button>");
							  w.document.write("</center></body></html>");
		   				    w.document.close();
		   				   // w.print();
	   					});
	   					
	 });
  </script>
</body>
</html>