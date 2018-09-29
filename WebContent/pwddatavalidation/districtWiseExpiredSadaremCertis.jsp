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
     
<title>:: SADAREM :: Expired SADAREM Certificates Abstract Report </title>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

</head>
<%try{ %>
<%
	ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
	ArrayList mandalList 	= (ArrayList)request.getAttribute("mandalList");
	ArrayList villageList  	    = (ArrayList)request.getAttribute("villagelist");
    ArrayList habitationList  	= (ArrayList)request.getAttribute("habitationlist");
	ArrayList dataList  	= (ArrayList)request.getAttribute("resultList");
	

	   
	    String selhabitationid      = CommonUtility.checkNullObj(request.getAttribute("habitationid"));
	    String selvillageid         = CommonUtility.checkNullObj(request.getAttribute("villageid"));
	    String sadaremid            = CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
	    String errormsg             = CommonUtility.checkNullObj(request.getAttribute("msg"));
	    String resultmsg            = CommonUtility.checkNullObj(request.getAttribute("resultmsg")); 
	    String sesMandalId          = CommonUtility.checkNullObj(session.getAttribute("mandalId"));
	    String roleId               = CommonUtility.checkNullObj(session.getAttribute("roleId"));
	
	
	String SelFromDate			= CommonUtility.checkNullObj(request.getAttribute("SelFromDate"));
	String SelToDate			= CommonUtility.checkNullObj(request.getAttribute("SelToDate"));
	
	String selDistrictID	= CommonUtility.checkNullObj(request.getAttribute("selDistrictID"));
	String selMandalID		= CommonUtility.checkNullObj(request.getAttribute("selMandalID"));
	System.out.println("selMandalID"+selMandalID);

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
	document.ExpiredSadaremdataform.action="<%=request.getContextPath()%>/districtexpiredSadaremCertisReport.do?mode=excelNew";
	document.ExpiredSadaremdataform.submit();
	
} 


$(document).ready(function() 
		{
	

		new JsDatePick({
	    	 
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
         });		
			
	
	$('#district').change(function()
			{	
				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadMandalsforOpenExpiredReport&distId="+ $('#district').val()+"&randomid="+Math.random(),"mandalTDID");
				
			});

	
		$('#getdata').click(function() 
				{
			
			
			if($('#startDate').val()==""||$('#startDate').val()==null)
		 	{
				alert("Please Enter From Date before submitting");}
		 	
			
			else if($('#endDate').val()==''||$('#endDate').val()==null)
		 	{
				alert("Please Enter To Date before submitting");}
				
				else
					{
					
							document.ExpiredSadaremdataform.target="_self";
							document.ExpiredSadaremdataform.action="<%=request.getContextPath()%>/districtexpiredSadaremCertisReport.do?mode=getExpiredSadaremdata&randomid="+Math.random();
							document.ExpiredSadaremdataform.submit();
							$('#getdata').prop("disabled", true);
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
					}	
				
				});
			});
function loadvillageCombo()
		{	
	//$('#mandal').val('');
	//alert($('#district').val());
			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadvillageopenrep&distId="+ $('#district').val()+"&mandalId="+ $('#mandal').val()+"&randomid="+Math.random(),"voTDID");
			
		}

</Script>
<Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
 {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
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
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	
	  <tr>
	    <td align="left" valign="middle" width="100%">
	    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R.5.3.1 Abstract Report of Expired SADAREM Certificates</th>
					  </tr>
	  				<tr>
  					<td>
  							<form name="ExpiredSadaremdataform"  method="post">
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
										     <td > District</td>
										     <td>
											  <%=ComboUtil.createStrComboBoxAuto("district", districtList,selDistrictID,"select-optionItem1","",true,false,"")%>
										     </td>
										     <td>Mandal</td>
										    <td align="left" id="mandalTDID">
				                               		<%=ComboUtil.createStrComboBoxAuto("mandal", mandalList,selMandalID,"select-optionItem1","onchange='loadvillageCombo()'",true,false,"")%>
											</td>
												    <td align="left" valign="middle" >Village:</td>
							         <td  id="voTDID">
							         <%=ComboUtil.createStrComboBoxAuto("village",villageList,selvillageid,"select-optionItem1","onchange='loadHabitationCombo()'",true,false,"")%>
							         </td>
							         
									        
										    </tr>
										    
										   
									        <tr height="30">
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
									          <td rowspan="2" valign="middle">
									           	<input type="button" value="Get Data" id ="getdata" style="width: 80px; text-align: center;height: 20px; cursor: pointer;" title="Click Here">
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
  					<%
					  if(dataList!=null && dataList.size()>0)
					  {	%>
					  			<table width="90%">
				                   <tr>
						             <td  align="right" width="89%" >
									   <a href="javascript:fn_excel()">
										<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
						       
						       
						       
	    		<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" style="overflow: none;" >
	    		
	    		
	    		
	    		<%
					  if(dataList.size()>17)
					  {	%>
	    		<tbody>
                 <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
                <%} %>
  				<tr class="gridHdrStyle">
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >S.No.</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >
  				 	<%
  				 	if(selDistrictID.equals("-1"))
  				 	{
  				 		out.write("District");
  				 	}
  				 	else if(!selDistrictID.equals("-1") && selMandalID.equals("-1"))
  				 	{
  				 		out.write("Mandal");
  				 	}
  				 	else if(!selDistrictID.equals("-1") && !selMandalID.equals("-1"))
  				 	{
  				 		out.write("Village");
  				 	}
  				 	%>
  				 	</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">No. Of Expired Sadarem Certificates</th>
  				</tr>
  		
  				<tr class="gridHdrStyle">
  				 	<th height="30" class="hd_gd" align="center" valign="middle">1</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">2</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle">3</th>
  				 
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
	  				 	<td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(1) %></td>
	  				 	

	  				 	
	  				 
	  				 	
	  				
	  				</tr>
					  <%}
							   tempList=(ArrayList)dataList.get(dataList.size()-1);	   
						%>   
				
				
				<tr>
	  				 	
	  				 	<th colspan="2" class="hd_gd" align="center" valign="middle"><%if(tempList.get(0).equals("ZZTOTAL")) {out.write("Total");}%></th>
	  				 	<th class="hd_gd" align="left" valign="middle"><%=tempList.get(1) %></th>
	  				 
	  				 	
	  				
	  				</tr>	   
					<% }
					  else
					  {
						  %>
						   <tr>
						    <td colspan="14" align="center" valign="middle" width="100%" height="50" style="border-left:#234466 solid 1px;">
						    			<b>No Record Found to Display.</b>
						    </td>
						  </tr>
						  <%
					  }
					  %>
					  
					  
			<%
					  if(dataList.size()>17)
					  {	%>		  
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
 <%} %>
 
</table>
<%}else
{%>
	<table align='center'>
	<tr align='center'><td  align="center" valign="center">No Data Found </td></tr></table><% 
	
}%>
		              
</td>
</tr>
</table>
 </body>
 
 <%}catch(Exception e){e.printStackTrace();} %>
</html>
