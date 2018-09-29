<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,org.bf.disability.Constants.CommonConstants" %>
<html>
<head>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>:: SADAREM ::</title>
<link href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<%try{
   
   ArrayList dataList  	    = (ArrayList)request.getAttribute("resultList");
	String	selsadaremid	= CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
    String errormsg         = CommonUtility.checkNullObj(request.getAttribute("msg"));
    String selmandalid          = CommonUtility.checkNullObj(request.getAttribute("mandalid"));
    String roleId               = CommonUtility.checkNullObj(session.getAttribute("roleId"));
 
%>
<script type="text/javascript">


$(document).ready(function() 
		{
	
	
	
		$('#back').click(function() 
				{
					
						document.viewpwdValadatinfrm.target="_self";
						document.viewpwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=getpwdsdata&randomid="+Math.random();
						document.viewpwdValadatinfrm.submit();
						$('#getdata').prop("disabled", true);
						/*Screen Locking Started */
						$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
					    $('#processlayer').css({"display": "block","z-index":"110000"});
					/*Screen Locking Ended */
				});
		
		$('#confirm').click(function() 
				{
			var conf = confirm("Do you want to confirm? Once confirmed edit provision will get disabled.");
			if(conf)
			{
						document.viewpwdValadatinfrm.target="_self";
						document.viewpwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=submitconfirmdtls&randomid="+Math.random();
						document.viewpwdValadatinfrm.submit();
						$('#getdata').prop("disabled", true);
						/*Screen Locking Started */
						$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
					    $('#processlayer').css({"display": "block","z-index":"110000"});
					/*Screen Locking Ended */
			}
				});
			});
		
</Script>

</head>
<body>
 <table  align="center" cellspacing="0" cellpadding="0" width="95%">
 <tr><td>
         <form name="viewpwdValadatinfrm"  method="post">
         <%if(dataList!=null && dataList.size()>0)
					  {
  				  		ArrayList tempList = new ArrayList();
					   for(int looper=0;looper<dataList.size();looper++)
					   { 
						   tempList = (ArrayList)dataList.get(looper);
					  %>
					<input type="hidden" id="sadaremid" name="sadaremid" value="<%=selsadaremid %>"/>
					<input type="hidden" id="mandal" name="mandal" value="<%=selmandalid%>"/>
	<fieldset>
				<legend style=" background-color:maroon;">PWD Validation Details For Rejected SADAREM ID : <%=selsadaremid%></legend>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					
					 <tr>
					 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">Personal Details</td>
					 </tr>
					 <tr>
					  <td align="center" valign="middle" class="" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4"><br/>
					 <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
					 <tr>
					 
					 <th align="center" valign="middle" class="search_col">Full Name</th>
					 <th align="center" valign="middle" class="search_col">Relation Name</th>
					 <th align="center" valign="middle" class="search_col">Gender</th>
					 <th align="center" valign="middle" class="search_col">Age</th>
					<th align="center" valign="middle" class="search_col">Mandal</th>
					 <th align="center" valign="middle" class="search_col">Village</th>
					 <th align="center" valign="middle" class="search_col">Habitation</th>
					 <th align="center" valign="middle" class="search_col">Contact No.</th>
					 <th align="center" valign="middle" class="search_col">Aadhar Card</th>
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(1) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(2) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(3) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(4) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(5) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(6) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(7) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(8) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(9) %></td>
					 </tr>
					 </table><br/>
					 </td>
					 </tr>
					<tr>
					 <th align="left" valign="middle" class="search_col" colspan="4">Authorised Editor details</th>
					 </tr>
					 <tr>
					  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
					<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
					 <tr>

					 <th align="center" valign="middle" class="search_col">SKS ID</th>
					 <th align="center" valign="middle" class="search_col">Validation done by</th>
					 <th align="center" valign="middle" class="search_col">Designation</th>
					 <th align="center" valign="middle" class="search_col">Validation done Date</th>
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(10)%></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(11)%></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(12)%></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(13)%></td>
					 </tr>
					 </table><br/></td></tr>
					<tr>
					  <%if(CommonConstants.DPMLOGINROLEID.equals(roleId)) {%>
					   <td align="center" valign="middle" colspan="4" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;border-right:#97a0ba solid 1px" colspan="2">
					    <input type="button" class="button"  id="back" name="back" value="Back" style="height:30px; width:80px;text-align: center;"></td>
					   <%}else{ %>
					    <td align="center" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;border-right:#97a0ba solid 1px" colspan="2">
					    <input type="button" class="button"  id="back" name="back" value="Back" style="height:30px; width:80px;text-align: center;"></td>
					    <td align="center" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;" colspan="2">
					    <input type="button"  id="confirm" name="confirm" class="button" value="Confirm" style="height:30px;width:140px;text-align:center;"/></td>
					  <%} %>
					 
					 </tr>
					<% 
					}
					}
					else
					{%>
					<tr>
					  <td align="center" valign="middle"   colspan="4">
					  No Data Found.</td>
					  </tr>
				   <%}
					%>
		   		</table>
			</fieldset>		  
  		</form>
 	  </td>
 	 </tr>
  </table>
              
            <%}catch(Exception e) {e.printStackTrace();}%>    
</body>
</html>           
   