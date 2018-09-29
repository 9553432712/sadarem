<%@page contentType="text/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>
<html>
<head>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/> 
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>:: SADAREM :: PwD Validation Abstract Rural Report</title>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>

	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>


</head>
<%
	ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
	ArrayList mandalList 	= (ArrayList)request.getAttribute("mandalList");
	ArrayList villageList 	= (ArrayList)request.getAttribute("villageList");
	
	ArrayList dataList  	= (ArrayList)request.getAttribute("resultList");
	
	
	String SelFromDate			= CommonUtility.checkNullObj(request.getAttribute("SelFromDate"));
	String SelToDate			= CommonUtility.checkNullObj(request.getAttribute("SelToDate"));
	String areatype   =              CommonUtility.checkNullObj(request.getAttribute("areatype"));
	String selDistrictID	= CommonUtility.checkNullObj(request.getAttribute("selDistrictID"));
	String selMandalID		= CommonUtility.checkNullObj(request.getAttribute("selMandalID"));
	String selVillageID  	= CommonUtility.checkNullObj(request.getAttribute("selVillageID"));
	String sesDistrictID    = CommonUtility.checkNullObj(session.getAttribute("districtId"));
	String sesmandalid  	= CommonUtility.checkNullObj(session.getAttribute("mandalId"));
	String roleID  	= CommonUtility.checkNullObj(session.getAttribute("roleId"));
	 
	
	 
%>


<script type="text/javascript">
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


function fn_excel()
{
	
	$('#distid').val('<%=selDistrictID%>');
	$('#mandalid').val('<%=selMandalID%>');
	$('#fromdate').val('<%=SelFromDate%>');
	$('#todate').val('<%=SelToDate%>');
	document.pwdValAbstReport16.action="<%=request.getContextPath()%>/updatedPwdReportdetails.xls?mode=excelNew";
	document.pwdValAbstReport16.submit();
	
} 


$(document).ready(function() 
		{
	

 	/* new JsDatePick({
	    	 
//			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
  			useMode:2,
  			target:"startDate",
  			//dateFormat:"%d-%M-%Y",
  			//selectedDate:{day:25,month:12,year:2013},
  			yearsRange:[2009,2100],
  			minDate: 0,
  			limitToToday:true,
  			cellColorScheme:"beige",
  			dateFormat:"%d/%m/%Y",
  			imgPath:"img/",
  			weekStartDay:1
         });	
		
g_calendarObject_end = new JsDatePick({
	    	 
//			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
  			useMode:2,
  			target:"endDate",
  			//dateFormat:"%d-%M-%Y",
  			//selectedDate:{day:25,month:12,year:2013},
  			yearsRange:[2009,2100],
  			minDate: 0,
  			limitToToday:true,
  			cellColorScheme:"beige",
  			dateFormat:"%d/%m/%Y",
  			imgPath:"img/",
  			weekStartDay:1
         });		 */


$('#startDate').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
			/* 	maxDate:output, */
		});
		
$('#endDate').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatTime:'H:i',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
/* 			maxDate:'31/08/2015' */
			 /* maxDate:output,  */
		});



	 $('#areatype').change(function()
	   		 {	
		 
		 var roleID=<%=roleID%>;
		 
		        if(roleID=='97' || roleID=='101' )
		        	{
	   			  		postRequest("<%=request.getContextPath()%>/CommonReportsAjax.do?action=loaddistricts&areatype="+ $('#areatype').val()+"&randomid="+Math.random(),"districtTDID");
		        	}
		        else if(roleID=='34')
		        	{
		 				postRequest("<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadMandalsbyareawise&mandalidname=mandal&distId="+ $('#district1').val()+"&areatype="+ $('#areatype').val()+"&randomid="+Math.random(),"mandalTDID");
		        	}
		        else
		        	{
		        	var url ="<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadVillagesareawise&villageidname=village&distId="+ $('#district1').val()+"&mandalId="+$('#mandal').val()+"&areatype="+ $('#areatype').val()+"&randomid="+Math.random();
	    			postRequest(url,"villageTDID");
		        	}
		        
	   		  });	
	   $('body').on('change','#district1',function()
	    		{	
	    	postRequest("<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadMandalsbyareawise&mandalidname=mandal&distId="+ $('#district1').val()+"&areatype="+ $('#areatype').val()+"&randomid="+Math.random(),"mandalTDID");
	    		});
	    
	$('body').on('change','#mandal',function()
	    		{	
	    			var url ="<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadVillagesareawise&villageidname=village&distId="+ $('#district1').val()+"&mandalId="+$('#mandal').val()+"&areatype="+ $('#areatype').val()+"&randomid="+Math.random();
	    			postRequest(url,"villageTDID");
	    		});
    
	
	
		$('#getdata').click(function() 
				{
					
							document.pwdValAbstReport16.target="_self";
							document.pwdValAbstReport16.action="<%=request.getContextPath()%>/updatedPwdReportdetails.do?randomid="+Math.random();
							document.pwdValAbstReport16.submit();
							$('#getdata').prop("disabled", true);
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
						
				
				});
			});
			
			
			
function loadVillageCombo()
{
	postRequest("<%=request.getContextPath()%>/ajax.do?action=loadvillageopenrep&distId="+ $('#district').val()+"&mandalId="+ $('#mandal').val()+"&randomid="+Math.random(),"villageTDID");
	
}


</Script>
<Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   
   element.style {
    background-color: #fff 
    }
    
</Style>
<body onload="OnBodyLoad(1,3);">
<!-- Screen Lock Started Here -->
	<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	
	  <tr>
	    <td align="left" valign="middle" width="100%">
	    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="left" valign="middle">PwD Validation Report </th>
					  </tr>
					  <tr></tr>
	  				<tr>
  					<td>
  							<form name="pwdValAbstReport16"  method="post">
  							<input type="hidden" name="distid" id="distid" value="" readonly="readonly">
  							<input type="hidden" name="mandalid" id="mandalid" value="" readonly="readonly">
  							<input type="hidden" name="fromdate" id="fromdate" value="" readonly="readonly">
  							<input type="hidden" name="todate" id="todate" value="" readonly="readonly">
									   <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="100%" border="0px">
									        <tr height="30">
									        	  <td>Area Type</td>
										    <td align="left" id="AreaTypeTDID">
				                                 
				                             
				                               <select class="form-control mycomboStyle " id='areatype' name='areatype'>
				                             <option value='-1' <%if(areatype.equalsIgnoreCase("-1")){ %>selected<%} %>>All</option>
				                              <option value='U' <%if(areatype.equalsIgnoreCase("U")){ %>selected<%} %>>Urban</option>
				                               <option value='R' <%if(areatype.equalsIgnoreCase("R")){ %>selected<%} %>>Rural</option>
				                             </select>   
				                             
				                             </td>
				                             
				                             
										     <td > District</td>
										     <td id="districtTDID">
										     <%if(sesDistrictID.equals("") || sesDistrictID == null || roleID.equals("13") || roleID.equals("97") || roleID.equals("101")){ %>
											  <%=ComboUtil.createStrComboBoxAuto("district1", districtList,selDistrictID,"select-optionItem1","",true,false,"")%>
										     <%}else{ %>
										      <%=ComboUtil.createStrComboBoxAuto("district1", districtList,sesDistrictID,"select-optionItem1","disabled",true,false,"")%>
										     <%} %>
										     </td>
										     <td>Mandal</td> 
										    <td align="left" id="mandalTDID">
				                               <%if(sesmandalid.equals("") || roleID.equals("13") || roleID.equals("97") || roleID.equals("101")){ %>
				                               		<%=ComboUtil.createStrComboBoxAuto("mandal", mandalList,selMandalID,"select-optionItem1","",true,false,"")%>
				                               <%}else{ %>
				                                  <%=ComboUtil.createStrComboBoxAuto("mandal", mandalList,sesmandalid,"select-optionItem1","disabled",true,false,"")%>
				                               <%} %>		
											</td>
											
									         <td rowspan="2" valign="middle">
									           	<input type="button" value="GO" id ="getdata" style="width: 50px; text-align: center;height: 30px; cursor: pointer;" title="Click Here">
									         </td>
										
										    </tr>
									        <tr height="30">
									          <td>Village</td>
										    <td align="left" id="villageTDID">
				                                  <%=ComboUtil.createStrComboBoxAuto("village", villageList,selVillageID,"select-optionItem1","",true,false,"")%>
				                             </td>
									        <td>
									        	From Date
									        </td>
									        <td>
									          <input type="text"  id="startDate"  name="startDate" value="<%=SelFromDate%>" style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
									        </td>
									        <td>
									       		To Date
									       	</td>
									       	<td>
									         	<input type="text" id="endDate" name="endDate"  value="<%=SelToDate%>" style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
									         </td>
									     </tr>     
									    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
					    	
						</form>
  					</td>
  				</tr>
  				</table> 
  					 
					  			<table width="90%">
				                   <tr>
				                 <!--   <td align="left" width="60%">
				                   <span><font  color="blue"><b>Note:</b></font> 31<sup>st</sup> August 2015 (Certificates Issued Date) is the cut off date of PWD Validation. </span>
				                   </td> -->
						             <td  align="right" width="40%" >
									   <a href="javascript:fn_excel()">
										<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
					        
						       
	    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
               
                 <tbody>
                <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
                
  				<tr class="gridHdrStyle">
                   <th height="30" class="hd_gd" align="center" valign="middle" rowspan="3">S.No.</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="3">
  				 	<%
  				 	if(!selDistrictID.equals("-1") && !selMandalID.equals("-1") && !selVillageID.equals("-1"))
  				 	{
  				 		out.write("Habitation");
  				 	}
  					else if(!selDistrictID.equals("-1") && !selMandalID.equals("-1"))
  				 	{
  				 		out.write("Village");
  				 	}
  				 	else if(!selDistrictID.equals("-1") && selMandalID.equals("-1"))
  				 	{
  				 		out.write("Mandal");
  				 	}
  				 
  				 	else if(selDistrictID.equals("-1"))
  				 	{
  				 		out.write("District");
  				 	}
  				 	%>
  				 	
  				 	</th>
  				 <th height="30" class="hd_gd" align="center" valign="middle"  rowspan="3" >Total Validated</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="2">Verification</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="3">AADHAR Not Tagged</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="2">Person Status</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="10">SHG Group</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="6">AASARA </th>
  				</tr>
  				<tr class="gridHdrStyle">
  				
  				    <th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Total Edited </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Total<br>Confirmed </th>
  				 	
  				    <th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Total </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Alive </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Dead </th>
  				 	
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Alive </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Dead </th>
  				 	
  				 	<th height="30" class="hd_gd" align="center" valign="middle" rowspan="2">Tagged </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="9">Not Tagged</th>
  				 	
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="3">Not Covered</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" colspan="3">Covered</th>
  				</tr>
  				<tr class="gridHdrStyle">
  					<th height="30" class="hd_gd" align="center" valign="middle">Total </th>
	  				<th height="30" class="hd_gd" align="center" valign="middle">Not<br>enough<br>members<br>to form<br>SHG</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle">Orphans</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle" >Existing<br>in SHG<br>but not<br>in IB</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle">Above<br> poverty<br> line </th>
	  				<th height="30" class="hd_gd" align="center" valign="middle">Above<br> 50 <br>years</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle">Migrated</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle" >Not<br> interested</th>
	  				<th height="30" class="hd_gd" align="center" valign="middle" >Not<br> Eligible</th>
	  				
	  				
	  				<th height="30" class="hd_gd" align="center" valign="middle">Total </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">Alive </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">Dead </th> 
  				 	<th height="30" class="hd_gd" align="center" valign="middle">Total </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">Alive </th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">Dead </th>
  				</tr>
  				<tr class="gridHdrStyle">
  				 	<th height="30" class="hd_gd" align="center" valign="middle">1</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">2</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">3</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">4</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">5</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">6</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">7</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">8</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">9</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">10</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">11</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">12</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">13</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">14</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">15</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">16</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">17</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">18</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">19</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">20</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">21</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">22</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">23</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">24</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">25</th>
  				 		<th height="30" class="hd_gd" align="center" valign="middle">26</th>
  				 
  				</tr> 
  				
  				  <%
					  if(dataList!=null && dataList.size()>0)
					  {
						  ArrayList tempList = new ArrayList();
				        	
			        	  String classStyle ="";
							   for(int looper=0;looper<dataList.size()-1;looper++)
							   { 
								   tempList = (ArrayList)dataList.get(looper);
			        		  		
								  if(looper%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }
						
					  %>
	  				<tr>
	  				 	<td class="<%=classStyle%>" align="center" valign="middle"><%=looper+1%></td>
	  				 	<td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(0) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(1) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(2) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(3) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(4) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(5) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(6) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(7) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(8) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(9) %></td>
	  				    <td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(10) %></td>
	  				    <td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(11) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(12) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(13) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(14) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(15) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(16) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(17) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(18) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(19) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(20) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(21) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(22) %></td>
	  				 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(23) %></td>
	  				 	 	<td class="<%=classStyle%>" align="right" valign="middle"><%=tempList.get(24) %></td>
	  				
	  				
	  				</tr>
					  <%}
							   tempList=(ArrayList)dataList.get(dataList.size()-1);	   
						%>   
						<tr>
	  				 	
	  				 	<th colspan="2" class="hd_gd" align="center" valign="middle"><%if(tempList.get(0).equals("ZZTOTAL")) {out.write("Total");}%></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(1) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(2) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(3) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(4) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(5) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(6) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(7) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(8) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(9) %></th>
	  				    <th class="hd_gd" align="right" valign="middle"><%=tempList.get(10) %></th>
	  				    <th class="hd_gd" align="right" valign="middle"><%=tempList.get(11)  %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(12) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(13) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(14) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(15) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(16) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(17) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(18) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(19) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(20) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(21) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(22) %></th>
	  				 	<th class="hd_gd" align="right" valign="middle"><%=tempList.get(23) %></th>
	  				 		<th class="hd_gd" align="right" valign="middle"><%=tempList.get(24) %></th>
	  				
	  				
	  				</tr>	   
					<% }
					  else
					  {
						  %>
						   <tr>
						    <td colspan="25" align="center" valign="middle" width="100%" height="50" style="border-left:#234466 solid 1px;background-color:#fff">
						    			<b>No Record Found to Display.</b>
						    </td>
						  </tr>
						  <%
					  }
					  %>

    </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
    </td>
   </tr>
 </tbody>
</table>
</td>
</tr>
</table>
 </body>
</html>
