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
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<% try{
   
    ArrayList mandalList  	    = (ArrayList)request.getAttribute("mandalList");
    ArrayList villageList  	    = (ArrayList)request.getAttribute("villagelist");
    ArrayList habitationList  	= (ArrayList)request.getAttribute("habitationlist");
    ArrayList dataList  	    = (ArrayList)request.getAttribute("resultList");
    String seldistrictid        = CommonUtility.checkNullObj(request.getAttribute("districtid"));
    String selmandalid          = CommonUtility.checkNullObj(request.getAttribute("mandalid"));
    String selhabitationid      = CommonUtility.checkNullObj(request.getAttribute("habitationid"));
    String selvillageid         = CommonUtility.checkNullObj(request.getAttribute("villageid"));
    String sadaremid            = CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
    String errormsg             = CommonUtility.checkNullObj(request.getAttribute("msg"));
    String resultmsg            = CommonUtility.checkNullObj(request.getAttribute("resultmsg")); 
    String sesMandalId          = CommonUtility.checkNullObj(session.getAttribute("mandalId"));
    String roleId               = CommonUtility.checkNullObj(session.getAttribute("roleId"));
 
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
	
	$('#distid').val('<%=seldistrictid%>');
	$('#mandalid').val('<%=selmandalid%>');
	$('#villageid').val('<%=selvillageid%>');
	$('#habid').val('<%=selhabitationid%>');
	$('#selectid').val('<%=sadaremid%>');
	document.pwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.xls?mode=excelNew";
	document.pwdValadatinfrm.submit();
	
} 

$(document).ready(function() 
		{
	
	$('#mandal').change(function()
			{	
				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadvillageopenrep&distId=<%=seldistrictid%>&mandalId="+ $('#mandal').val()+"&randomid="+Math.random(),"voTDID");
				
			});
	
		$('#getdata').click(function() 
				{
						var sadaremid = $('#sadaremid').val();
						var distid    = '<%=seldistrictid%>'
						if($('#sadaremid').val()!="" && $('#sadaremid').val()!=null)
						{
							
							if(sadaremid.length<17)
							{
								alert("Please enter valid SADAREM ID");
								return false;
							}
						 if(sadaremid.substr(0, 2)!=distid)
								{
									alert("SADAREM ID doesn't belong to this district.");
									return false;
								} 
							else
							{
								document.pwdValadatinfrm.target="_self";
								document.pwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=getpwdsdata&randomid="+Math.random();
								document.pwdValadatinfrm.submit();
								$('#getdata').prop("disabled", true);
								/*Screen Locking Started */
								$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
							    $('#processlayer').css({"display": "block","z-index":"110000"});
							/*Screen Locking Ended */
							}
						}
						else
							{
							document.pwdValadatinfrm.target="_self";
							document.pwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=getpwdsdata&randomid="+Math.random();
							document.pwdValadatinfrm.submit();
							$('#getdata').prop("disabled", true);
							/*Screen Locking Started */
							$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
						    $('#processlayer').css({"display": "block","z-index":"110000"});
						/*Screen Locking Ended */
							}
				});
		
	});
			
function loadHabitationCombo()
{
	
	$('#sadaremid').val('');
	postRequest("<%=request.getContextPath()%>/ajax.do?action=loadhabitation&distId=<%=seldistrictid%>&mandalId="+ $('#mandal').val()+"&villageId="+ $('#village').val()+"&randomid="+Math.random(),"habTDID");

} 
function FormValdate(i)
{
		$('#selectid').val(i);
		document.pwdValadatinfrm.target="_self";
		document.pwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=loadpwdsdata&randomid="+Math.random();
		document.pwdValadatinfrm.submit();
		$('#getdata').prop("disabled", true);
		/*Screen Locking Started */
		$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	    $('#processlayer').css({"display": "block","z-index":"110000"});
	/*Screen Locking Ended */
}	

function confirmID(sadaremid)
{
	 
	var conf = confirm("Do you want to confirm? Once confirmed edit provision will get disabled.");
	if(conf)
	{
		document.pwdValadatinfrm.target="_self";
		document.pwdValadatinfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=submitconfirmdtls&sadaremid="+sadaremid+"&randomid="+Math.random();
		document.pwdValadatinfrm.submit();
		$('#confrm').prop("disabled", true);
		/*Screen Locking Started */
		$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	    $('#processlayer').css({"display": "block","z-index":"110000"});	
	}
	
}



</Script>
</head>
<body>
 <table  align="center" cellspacing="0" cellpadding="0" width="95%">
 
 
	  <tr>
  <th  align="center" valign="middle"> 
  <h4 ><u><!-- style="background-color:rgb(159, 193, 213); padding:.2% 0 .2% 0; border:1px solid grey"> -->
  PWD Data Validation</u></h3> </th>
	  </tr>
	 


	  
	   <tr>
	   <td>
   <form name="pwdValadatinfrm"  method="post">
     <input type="hidden" id="distid" name="distid" value="<%=seldistrictid%>"/>
     <input type="hidden" id="mandalid" name="mandalid"  />
	 <input type="hidden" id="villageid" name="villageid"  />
	 <input type="hidden" id="habid" name="habid"  />
   <input type="hidden" id="selectid" name="selectid" value="" />
 
   
       <table  align="center" cellspacing="0" cellpadding="0" width="100%">
                   <tr>
				   <td  align="left" valign="top">
					    <div style="position:relative;max-width: 1360px;">
					      <div class="heading_text1" style="background-image:url(<%=request.getContextPath()%>/images/Acconcement_bg.png); background-repeat:no-repeat; height:34px; z-index:10; width:181px;  position:absolute; left:20px;">Search</div>
					      <div class="scroll_bg1" style="margin-left:160px; width:87%;">
					      
					        <table  cellspacing="0" width="100%" border="0px" align="center">
							        <tr>
							        <td  align="left" valign="middle" style="font-size:12px">Mandal:</td>
							       <td align="left" valign="middle">
							       <%if((sesMandalId).equals("")||(sesMandalId)==null)
							       	 { %>
							         <%=ComboUtil.createStrComboBoxAuto("mandal",mandalList,selmandalid,"select-optionItem1","",true,false,"")%>
							         <%}
							          else
							         {
							         %>
							         <%=ComboUtil.createStrComboBoxAuto("mandaltest",mandalList,selmandalid,"select-optionItem1","disabled",true,false,"")%>
							          <input type="hidden" id="mandal" name="mandal" value="<%=selmandalid%>"/>
							         <%} %>
							         </td>
							         <td align="left" valign="middle" style="font-size:12px">Village:</td>
							         <td  id="voTDID">
							         <%=ComboUtil.createStrComboBoxAuto("village",villageList,selvillageid,"select-optionItem1","onchange='loadHabitationCombo()'",true,false,"")%>
							         </td>
							         <td align="left" valign="middle" style="font-size:12px">Habitation:</td>
							         <td  id="habTDID">
							         <%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,selhabitationid,"select-optionItem1","",true,false,"")%>
							         </td>
									<td align="left" valign="middle" style="font-size:12px">(OR)</td>
							         <td align="left" valign="middle" style="font-size:12px">SADAREM ID:
							         <input type="text" name="sadaremid" id="sadaremid"  value='<%=sadaremid%>'  style="width:180px;height:20px;" maxlength="17">
							         </td>
							         <td align="left" valign="middle">
							         <input type="button" id="getdata" value="Get Data" style="width:70px;"/>
							         </td>
							         </tr>
					         </table>
			
					     </div>
					    </div>
					   </td>
					</tr>
            </table></br> 

              <%
					  if(dataList!=null && dataList.size()>0)
					  {	%>
					  			<table width="90%">
				                   <tr>
                                     <td align="center" style="padding-left:8px;"><font color="red">Note :1) NA specifies that the particular data is not available.<br>2)Find complete details of PwD validation in Excel.</font></td>
						             <td  align="right" width="59%" >
									   <a href="javascript:fn_excel()">
										<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
					<%} %>	  

            	<%
					  if(dataList!=null && dataList.size()>0)
					  {	%>
			<table width="93%" border="0" align="center" cellpadding="0" cellspacing="0" class="gd_row">
  				<tr>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >S.No.</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >SADAREM ID</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >Name</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >Relation Name</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >Person Status</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >Aadhar Status</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >Aasara Status</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >SHG Tagged Status</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >Edit</th>
  				 	  <%if(!roleId.equals(CommonConstants.DPMLOGINROLEID))
                        	{%>
				 	<th height="30" class="hd_gd" align="center" valign="middle" >Confirm</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >Confirm Date</th> 
  				 	<% }%>
  				</tr>
  				<tr>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >1</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >2</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >3</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >4</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >5</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >6</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >7</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >8</th>
  				 	<th height="30" class="hd_gd" align="center" valign="middle" >9</th>
  				 	  <%if(!roleId.equals(CommonConstants.DPMLOGINROLEID))
                        	{%>
				 	<th height="30" class="hd_gd" align="center" valign="middle" >10</th>
					<th height="30" class="hd_gd" align="center" valign="middle" >11</th> 
  				 	<% }%>
  				</tr>
  					
  				  <%
  				  		ArrayList tempList = new ArrayList();
			        	  String classStyle ="";
							   for(int looper=0;looper<dataList.size();looper++)
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
	  				 	<td class="<%=classStyle%>" align="center" valign="middle"><%=looper+1%>
	  				 	<input type="hidden" id="tranID<%=looper+1%>" name="transID<%=looper+1%>" value="<%=tempList.get(0) %>"/>
	  				 	</td>
	  				 	<td class="<%=classStyle%>" align="center" valign="middle"><%=tempList.get(0) %></td>
	  				 	<td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(1) %></td>
	  				 	<td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(2) %></td>
                        <td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(5) %></td>
						<td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(6) %></td>
                        <td class="<%=classStyle%>" align="left" valign="middle"><%=tempList.get(7) %></td>
                        <%if(CommonUtility.checkNullObj(tempList.get(8)).equals("1")){ %>  
						 <td class="<%=classStyle%>" align="left" valign="middle">Yes</td>
	  				 	<%}else{ %>
                          <td class="<%=classStyle%>" align="left" valign="middle">No</td>
                        <%} %>
                        <td class="<%=classStyle%>"  align="center" valign="middle">
	  				 	    <input type="button" name="edit" id="edit" 
	  				 	    <%if(((String)tempList.get(4)).equals("Y")) {%>value="Update" disabled style="background-color:grey;color:white;"<%} %> value="Update"  onclick="FormValdate('<%=looper+1 %>')">
                        </td>
                        <%if(!roleId.equals(CommonConstants.DPMLOGINROLEID))
                        	{%>
                        
  				    	<td class="<%=classStyle%>"  align="center" valign="middle">
	  				 	 <input type="button" name="confrm" id="confrm" 
	  				 	 <%if(Integer.parseInt((String)tempList.get(10))<=0 || ((String)tempList.get(4)).equals("Y")) 
	  				 	 {%>value="Confirm" disabled style="background-color:grey;color:white;"
	  				 	 <%} %> value="Confirm" onclick="confirmID('<%=tempList.get(0)%>')">
						</td>
						<td class="<%=classStyle%>" align="center" valign="middle">
						 <%if(((String)tempList.get(4)).equals("Y"))
						 {%>
						  <%=tempList.get(9)%><%}else{ %><%="-"%><%} %></td> 
						<% }%>
	  				</tr>
					
					<% }
  				 	  }
					  else
					  {
					%>
					<tr><td>
					 <table width="93%" border="0" align="center" cellpadding="0" cellspacing="0" >
					 <tr>
					<td colspan="4" align="center" valign="middle" width="93%"> 
				    <%if(!resultmsg.equals("") && resultmsg!=null && resultmsg.length()>0)
					{ %>
					      <%=resultmsg%>
					<%} %>
				   </td></tr>
					<tr>
					<td colspan="4" align="center" valign="middle" width="93%">
					<% if(errormsg==null ||errormsg==""){%><b>Please Select for PWD's Data</b><%}else{ %><b><%=errormsg %><%}%></b></td></tr>
					 </table> </td>
						    </tr>
					 <%
					  }
					  %>
		 </table>
		
        </form>
        </td>
        </tr>
        </table>
                    
    <%}catch(Exception e){e.printStackTrace();} %>            
</body>
</html>           
   