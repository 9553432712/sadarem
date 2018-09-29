<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%try{ %>
<html>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.CommonUtility" %>

<%ArrayList basicData = new ArrayList(); 

basicData = (ArrayList)request.getAttribute("basidtls");

String errMSG= CommonUtility.checkNull((String)request.getAttribute("ResultMSG"));

String ResultMSG = "";
if(basicData!=null && basicData.size()==1)
{
	ResultMSG = basicData.get(0).toString();
}
else if(basicData!=null && basicData.size()>1)
{
	basicData = (ArrayList)basicData.get(0);
	ResultMSG="Success";
}


%>


<head>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-dialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-dialog.min.js"></script>
<style>
 .table tbody tr td {
       padding:1px;
       font-size:14px
       
     }
     .hd_gd
   {
     border : #234466 1px solid;
   }
   .secondrow{
   border-color:#337ab7;
   }
</style>



	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
      <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
	    

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PartA DEO entry Success</title>
</head>

<style type="text/css">
    #fifteenth
    {
    	position: absolute;width: 150px;
    	border: 1px solid gray;padding: 2px;visibility: hidden;z-index: 99;
    }
</style>
<div id="fifteenth"></div>

    <head>

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumber.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script>
        
    </head>
    <body >
      <center><%=errMSG %></center>
      <%if(ResultMSG.equals("Success")){%>
      <center><span style="color:red;font-weight:bold;font-size:15px;"> This is not the certificate of proof, it is an Acknowledgement.</span></center>
      <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0"  >
	    		
  				<tr align='center'>
  				 	<th height="35" colspan='4' align="center" valign="middle"  style="border : #234466 1px solid; text-align:center; font-family:inherit; background-color:#337ab7; border-color:#337ab7;color:#fff;" >
  				 	Acknowledgement Slip

  				</tr>
    			<tr>	  				
	  				 	<td width="30%" class="secondrow" align="left" valign="middle"> <b>Name: </b><%=basicData.get(4)%> <%=basicData.get(3)%>
	  				 	 <br>(<%=basicData.get(6)%>) </td>
	  				 	<td width="30%" class="secondrow" align="left" valign="middle"><b>Gender: </b><%=basicData.get(14)%></td>
	  				 	<td width="25%" class="secondrow" align="left" valign="middle"><b>Date Of Birth: </b><%=basicData.get(8)%></td>
	  				 	<td  class="" style="border-right : #234466 1px solid; border-bottom : #234466 1px solid; border-left: #234466 1px solid;text-align:center;"  rowspan='4'  valign="middle">	  				 	
	  				 	  <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showwithoutproofpartaimg&aadhaarid=<%=(String)basicData.get(12) %>&requestID=<%=(String)basicData.get(0) %>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.jpg'"/>
	  				 	 </td>
   			    </tr>
	  			<tr>
	  				 	 	<td width="30%" class="firstrow" align="left" valign="middle"><b>Relationship Details: </b><%=basicData.get(24)+" "%> <%=basicData.get(5)%><br>
	  				 	 	(<%=basicData.get(7)%>)</td>
	  				 	 	<td width="30%" class="firstrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>marital Status:</b> 
	  				    <%=basicData.get(22)%></td>
	  				 	<td width="25%" class="firstrow" align="left" valign="middle"><b>Contact Number: </b><%=basicData.get(20)%></td>
	  				 	
	  			</tr>
	  			<tr>
	  				 	<td width="30%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Address:</b> 
	  				 	<%=basicData.get(17)%>(V),<br><%=basicData.get(16)%>(M),<br><%=basicData.get(15)%>(D)</td>
	  				 	<td width="30%" class="secondrow" align="left" valign="middle"><b>Proof ID: </b>
	  				 	<%if(!basicData.get(12).equals("-")) {%>
	  				 	<%-- <a style="font-family:Verdana, Geneva, sans-serif;" href="<%=request.getContextPath()%>/loadsearchsadaremdtls.do?mode=getaadhaardetailsbyuid&strUID=<%=basicData.get(12)%>&randomid=<%=Math.random()%>" target="_blank"> --%>
	  				 	<b><%=basicData.get(12)%></b><!-- </a> -->
	  				 	<%}else{ %>
	  				 	<b><%=basicData.get(12)%></b>
	  				 	<%} %><br>(<%=basicData.get(13)%>)	  				 		
	  				 	</td>
	  				 	<td width="25%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Camp Details:</b> 
	  				 	<br><%=basicData.get(21)%></td>	  				      				
	  		 	</tr>
				<tr>
	  				 	<td width="30%" class="firstrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Request Id: 
	  				    <br><%=basicData.get(0)%></b> </td>
	  				    <td width="30%" class="firstrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Created By: </b> 
	  				    <br><%=basicData.get(23)%></td> 
	  				    <td width="25%" class="firstrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Created Date:</b> 
	  				    <br><%=basicData.get(11)%></td>	  				 	 				
	  		   </tr>	
					 

		</table><br><br>
		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0"  >
			<tr>
				<td><span style="color:blue;font-weight:bold;font-size:15px;">Note:</span></td>
							
			</tr>
			<tr>
				<td><span style="color:green;font-weight:bold;font-size:15px;">1. The request is pending for the approval at CAMP-INCHARGE.</span></td>	
				
			</tr>
			<!-- <tr>
				<td><span style="color:green;font-weight:bold;font-size:15px;">2. This is not the certificate of proof, it is an Acknowledgement.</span></td>
			</tr> -->
			<tr>
				<td><span style="color:green;font-weight:bold;font-size:15px;">2. SADAREM ID will be generated after approval of the CAMP-INCHARGE.</span></td>
			</tr>
		</table>
		
			<br><br><br>
										<div style="text-align:center;">
												<button type="button" id="formSubmit" class="btn btn-success"><b>Print</b></button>
										</div>
		<%}else{%>
		<center><h1><%=ResultMSG %></h1></center>
		<%} %>
		
    </body> 	       

   
<script type="text/javascript">

		$(document).ready( function()
		{
						
						$('#formSubmit').click(function()
						{
							window.print();
							
						});
		});

</script>
</html>
<%}catch(Exception e)
{
	
	e.printStackTrace();
}%>