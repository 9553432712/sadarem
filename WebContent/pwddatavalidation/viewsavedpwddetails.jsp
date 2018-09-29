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

<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<%
   
   ArrayList dataList  	    = (ArrayList)request.getAttribute("DetailsList");
	String	selsadaremid	= CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
    String resultmsg         = CommonUtility.checkNullObj(request.getAttribute("resultmsg"));
    String selmandalid          = CommonUtility.checkNullObj(request.getAttribute("mandalid"));

    String roleId               = CommonUtility.checkNullObj(session.getAttribute("roleId"));
   // System.out.println("DetailsList-in jsp---"+selsadaremid); 
 
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
<%try{ %>
<body>
 <table  align="center" cellspacing="0" cellpadding="0" width="95%">
   <center> 
				    <%if(!resultmsg.equals("") && resultmsg!=null && resultmsg.length()>0)
					{ %>
					      <%=resultmsg%>
					<%} %>
	</center><br/>
 <tr><td>
         <form name="viewpwdValadatinfrm"  method="post">
         <input type="hidden" id="sadaremid" name="sadaremid" value="<%=selsadaremid %>"/>
         <input type="hidden" id="mandal" name="mandal" value="<%=selmandalid%>"/>
	<fieldset>
				<legend>PWD Validation Details For SADAREM ID : <%=selsadaremid %></legend>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<%if(dataList!=null && dataList.size()>0)
					  {
  				  		ArrayList tempList = new ArrayList();
					   for(int looper=0;looper<dataList.size();looper++)
					   { 
						   tempList = (ArrayList)dataList.get(looper);
					  %>
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
					 <th align="center" valign="middle" class="search_col">Contact No</th>
					 <th align="center" valign="middle" class="search_col">Aadhar Card</th>
					 <th align="center" valign="middle" class="search_col">Person Status</th>
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;" ><%=tempList.get(1) %></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(2) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(3) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(4) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(5) %></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(6) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(7) %></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(8) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(9) %></td>
					 <td align="center" valign="middle"  class="rowstyle" style="height:30px;"><%=tempList.get(10) %></td>
					 </tr>
					 </table><br/>
					 </td>
					 </tr>
					 
					 
	 <%if(tempList.get(10).equals("ALIVE"))
		{  
						%>
					 <tr>
					 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
					 SHG  </td>
					 <td align="left" valign="middle" class="search_col2" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2"> <%=tempList.get(11) %>
					 </tr>
					 <tr>
					  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
						 <%if(tempList.get(11).equals("EXISTS"))
							     {  
										%>
										<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
										 <tr>
					
										 <th align="center" valign="middle" class="search_col">VO</th>
										 <th align="center" valign="middle" class="search_col">SHG Name</th> 
										 <th align="center" valign="middle" class="search_col">SHG ID</th>
										 <th align="center" valign="middle" class="search_col">Formation Date</th>
										 </tr>
										 <tr>
										 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(12)%></td>
										 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(14)%></td>
										 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(13)%></td>
										 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(15)%></td>
										 </tr>
										 </table><br/>
						    <% } %>
									 
	 <% } %>
					 
					 
					 
								   <tr>
									 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
									 Received any AIDS and Appliances in last 3 years  </td>
									 <td align="left" valign="middle" class="search_col2" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2"> <%=tempList.get(16)%></td>
									 </tr>
									 <tr>
									  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
		 <% if(tempList.get(16).equals("Received"))
					  { %>
								<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
								 <tr>
								 <th align="center" valign="middle" class="search_col">Type of Appliance</th>
								 <th align="center" valign="middle" class="search_col">Organization</th>
								 <th align="center" valign="middle" class="search_col">Date of Disbursement</th>
								 </tr>
								 <tr>
								 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(17)%></td>
								 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(18)%></td>
								 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(19)%></td>
								 </tr>
								 </table><br/>
					 <%} %>
									 </td></tr>
									 <tr>
									 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
									 Surgical Correction  </td>
									 <td align="left" valign="middle" class="search_col2" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2"> <%=tempList.get(20)%></td>
									 </tr>
									 <tr>
									  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
	 <% if(tempList.get(20).equals("DONE"))
			 { %>
							<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
							 <tr>
							 <th align="center" valign="middle" class="search_col">Type of Surgery</th>
							 <th align="center" valign="middle" class="search_col">Organization</th>
							 <th align="center" valign="middle" class="search_col">Date of Surgery</th>
							 </tr>
							 <tr>
							 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(21)%></td>
							 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(22)%></td>
							 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(23)%></td>
							 </tr>
							 </table><br/>
			<%} %>
					 </td></tr>
					 <tr>
					 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
					 PMJDY A/C Details (Prime Minister Jan Dhan Yojana)  </td>
					 <td align="left" valign="middle" class="search_col2" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2"><%=tempList.get(24)%></td>
					 </tr>
					 <tr>
					  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
	 <% if(tempList.get(24).equals("Covered"))
			{ %>
					<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
					 <tr>
					 <th align="center" valign="middle" class="search_col">Account number</th>
					 <th align="center" valign="middle" class="search_col">Bank</th>
					 <th align="center" valign="middle" class="search_col">Branch</th>
					 <th align="center" valign="middle" class="search_col">IFSC code</th>
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(25)%></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(26)%></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(27)%></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(28)%></td>
					 </tr>
					 </table><br/>
		 <%} %>
					 </td>
					 </tr><tr>
					 <td align="left" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
					 AASARA Details </td>
					 <td align="left" valign="middle" class="search_col2" style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="2">
					    <%=tempList.get(29)%></td>
					 </tr>
					<tr>
					 <th align="left" valign="middle" class="search_col" colspan="4" style="background-color:rgb(75, 84, 87);color:white">Authorised Editor details</th>
					 </tr>
					 <tr>
					  <td align="center" valign="middle"  style="border-left:#97a0ba solid 1px;border-right:#97a0ba solid 1px;" colspan="4">
					<br/> <table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
					 <tr>

					 <th align="center" valign="middle" class="search_col">SKS ID:</th>
					 <th align="center" valign="middle" class="search_col">Validation done by</th>
					 <th align="center" valign="middle" class="search_col">Designation</th>
					 <th align="center" valign="middle" class="search_col">Validation done Date</th>
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle"   style="height:30px;"><%if(CommonUtility.checkNullObj(tempList.get(31)).length()>0) {%><%=tempList.get(31)%><%}else{ %>-<%} %></td> 
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(32)%></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(33)%></td>
					 <td align="center" valign="middle" class="rowstyle"  style="height:30px;"><%=tempList.get(34)%></td>
					 </tr>
					 </table><br/></td></tr>
					<tr>
					  <%if(CommonConstants.DPMLOGINROLEID.equals(roleId)) {%>
					     <td align="center" colspan="4" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;border-right:#97a0ba solid 1px" colspan="2">
					    <input type="button" class="button" id="back" name="back" value="Back" style="height:30px; width:80px;text-align: center;"></td>
					     
					  <%}else{ %>
					    <td align="center" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;border-right:#97a0ba solid 1px" colspan="2">
					    <input type="button" class="button" id="back" name="back" value="Back" style="height:30px; width:80px;text-align: center;"></td>
					    <td align="center" valign="middle" class="search_col" style="border-left:#97a0ba solid 1px; border-bottom:#97a0ba solid 1px;" colspan="2">
					    <input type="button" class="button" id="confirm" name="confirm" value="Confirm" style="height:30px;width:80px;text-align:center;"/></td>
					    
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
  <%}catch(Exception e)
  {
	  e.printStackTrace();
  }%>            
</body>
</html>           
   