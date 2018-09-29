<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>:: Issue Tracking System ::</title>
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" /> 
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/>  
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
    
	
	<style>  
		html 
		 {
		    font-family: serif;
		 }
		.hd_gd
		{
		background-color:#337ab7 !important;
		}
	    .warnRow
	    {
	      font-family:Verdana, Geneva, sans-serif;
			font-size:11px;
			color:#000000;
			padding:6px;
			background-color: #DB5340;
			border-top:#234466 solid 1px !important;
			border-left: #234466 solid 1px !important;
		}
	    .optRow
	    {
	      font-family:Verdana, Geneva, sans-serif;
			font-size:11px;
			color:#000000;
			padding:6px;
			background-color: #7F91DA;
			border-top:#234466 solid 1px !important;
			border-left: #234466 solid 1px !important;
		}
		.JsDatePickBox
		{
           top:35px !important;
           left:-100px !important;
		}
   </style> 
 
  <script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
  </script>

	<%
	  ArrayList districtList = new ArrayList();
	  ArrayList dataList     = new ArrayList();
	  ArrayList rolesList     = new ArrayList();
	  districtList = (ArrayList)request.getAttribute("districtList");
	  rolesList = (ArrayList)request.getAttribute("rolesList");
	  dataList     = (ArrayList)request.getAttribute("dataList");
	  String fromdate = CommonUtility.checkNullObj(request.getAttribute("fromdate"));
	  String todate   = CommonUtility.checkNullObj(request.getAttribute("todate"));
	  String selrole   = CommonUtility.checkNullObj(request.getAttribute("role"));
	  String selDist   = CommonUtility.checkNullObj(request.getAttribute("selDist"));
	  String disable   = CommonUtility.checkNullObj(request.getAttribute("disable"));
	 %>
  
<script>

$(document).ready( function()
		{
	$('#fromdate').datetimepicker(
			{
				dayOfWeekStart : 1,
				lang:'en',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
				
			});
			
	$('#todate').datetimepicker(
			{
				dayOfWeekStart : 1,
				lang:'en',
				formatTime:'H:i',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
			});
			
	
	
	 
				 $("#raiseissueButID").click(function ()
							{
					  if ($('#fromdate').val()=='')
		                {
		                    alert("Please Select From date");
		                    $('#fromdate').focus();
		                    return false;
		                }
					 else if ($('#todate').val()=='')
		                {
		                    alert("Please Select To Date");
		                    $('#todate').focus();
		                    return false;
		                }
					 
					 else{
					 
					    document.form_issue.target="_self";
						document.form_issue.action="<%=request.getContextPath()%>/IssueTrackingDashboard.do";
						document.form_issue.submit();
			     	  
			 	/* }} */}
			}); 
          });

function showData()
{ 
	var data = document.getElementById('statusMsg').value;
	if(!data=='')
		{
		window.location.reload();
		  
		}
}

</script>
</head>
<body onload="showData();">
 
<div >
   <div class="panel-group" id="accordion">
		<div class="panel panel-primary" id="searchformforid" style="width:90%;margin:0px auto;">
			<div class="panel-heading"  style="cursor: pointer;">
		             <h4 class="panel-title">
									Issue Tracking System Status
					 </h4>
		     </div>
				       
				              <div class="panel-body">
							              <form class="form-inline" id="form_issue" name="form_issue" method="post" >
									           
										             <div>
													         <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date</div>
															       <input type="text" name="fromdate" id="fromdate" value="<%=fromdate%>" class="form-control">
															 </div>
															  <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div>
															       <input type="text" name="todate" id="todate" value="<%=todate%>" class="form-control">
															 </div>
															  <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">District</div>
															      <%if("Y".equals(disable)){ %>
															          <%=ComboUtil.createStrComboBoxAuto("district", districtList,selDist,"form-control mycomboStyle","disabled=true",true,false,"")%>
															       <%}else{ %>
															          <%=ComboUtil.createStrComboBoxAuto("district", districtList,selDist,"form-control mycomboStyle","",true,false,"")%>
															       <%} %>	
															 </div>
															 <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Role</div>
															       <%=ComboUtil.createStrComboBoxAuto("role", rolesList,selrole,"form-control mycomboStyle","",true,false,"")%>	
															 </div>
									                   <button type="button" id="raiseissueButID" class="btn btn-success"><b>Submit</b></button>
										           </div> 
										  </form>
					            </div>
					           
			       
			    </div>
			    <br>
			    <%if(dataList.size()>0){ %>	
			                
			                 <center>	
			                  <table style="width:50%;">	
			                       <tr><td><span style="background:#DB5340;align:left;border:black 1px solid;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> - Indicates pending percentage > 75,
			                                <span style="background:#7F91DA;align:left;border:black 1px solid;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> - Indicates pending percentage between 50 and 75
			                                
<a href="<%=request.getContextPath()%>/IssueTrackingDashboard.xls?mode=excelNew&fromdate=<%=fromdate%>&todate=<%=todate%>&district=<%=selDist%>&role=<%=selrole%> ">
                              <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
 </a> 
			                            </td></tr>
			                  </table>
			                  <br>
			                  
			                      <table class="gridStyle"  style="border:#234466 solid 1px;text-align:center;width:50%;padding-top:5px;">
						   			   <tr>
						   			      <th class="hd_gd" style="text-align:center" rowspan=2>S.No.</th>
						   			      <th class="hd_gd" style="text-align:center" rowspan=2>Issue Type</th>
						   			       <th class="hd_gd" style="text-align:center" rowspan=2>Total</th>
						   			      <th class="hd_gd" style="text-align:center" colspan=2>Pending</th>
						   			      <th class="hd_gd" style="text-align:center" colspan=2>Approved</th>
						   			      <th class="hd_gd" style="text-align:center" colspan=2>Rejected</th>
						   			   </tr>
						   			   <tr>
						   			      <th class="hd_gd" style="text-align:center" >Count</th>
						   			      <th class="hd_gd" style="text-align:center" >%</th>
						   			      <th class="hd_gd" style="text-align:center" >Count</th>
						   			      <th class="hd_gd" style="text-align:center" >%</th>
						   			      <th class="hd_gd" style="text-align:center" >Count</th>
						   			      <th class="hd_gd" style="text-align:center" >%</th>
						   			   </tr>
						   			   
						   			   
						   	     <%
							   	   ArrayList innerList = new ArrayList();
						   	       String classStyle="";
							   	   for(int i=0;i<dataList.size()-1;i++)
							   	   {
							   		 innerList = (ArrayList) dataList.get(i);  
								   	 
				        			 if(Integer.parseInt((String)innerList.get(3)) >75)
								   	 {
								   		classStyle="warnRow";
								   	 }
				        			 else if(Integer.parseInt((String)innerList.get(3)) >=50 && Integer.parseInt((String)innerList.get(3)) <75)
								   	 {
								   		classStyle="optRow";
								   	 } 
				        			 else
				        			  {
					        			  	classStyle="firstrow";
				        			  }
								   	 
						   		  %>		   
						   	           <tr>
						   			      <td class="<%=classStyle%>" align="center" valign="middle"><%=i+1%></td>
						   			      <td class="<%=classStyle%>" align="left" valign="middle"><%=innerList.get(0) %></td>
						   			      <td class="<%=classStyle%>" align="left" valign="middle"><%=innerList.get(1) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle"><%=innerList.get(2) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle"><%=innerList.get(3) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(4) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(5) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(6) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(7) %></td>
					   			        </tr>
						   			<%}  innerList = (ArrayList) dataList.get(dataList.size()-1); 
								   		 if(Integer.parseInt((String)innerList.get(3)) >75)
									   	 {
									   		classStyle="warnRow";
									   	 }
					        			 else if(Integer.parseInt((String)innerList.get(3)) >=50 && Integer.parseInt((String)innerList.get(3)) <75)
									   	 {
									   		classStyle="optRow";
									   	 } 
					        			 else
					        			  {
						        			  	classStyle="firstrow";
					        			  }
						   			  %>
						   			  <tr>
						   			      <td class="<%=classStyle%>" align="center" valign="middle" colspan=2>Total</td>
						   			      <td class="<%=classStyle%>" align="left" valign="middle"><%=innerList.get(1) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle"><%=innerList.get(2) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle"><%=innerList.get(3) %></td>
						   			      <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(4) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(5) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(6) %></td>
					   			          <td class="<%=classStyle%>" align="center" valign="middle" ><%=innerList.get(7) %></td>
					   			        </tr>
						   	    </table>
						   	    </center>	
						 <%} %>	      
			  </div>  
    </div>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
 </body>
 </html>
	  