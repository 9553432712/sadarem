<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<title>:: SADAREM ::</title>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css" rel="stylesheet" type="text/css" />
 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pwddatavalidation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
 
<%try{
ArrayList resultList = new ArrayList();
ArrayList basicList = new ArrayList();
ArrayList reasonList = new ArrayList();
ArrayList shgList = new ArrayList();
ArrayList voList = new ArrayList();
ArrayList organisanationMaster = new ArrayList();
String mappedflag="",selVoId="",selVoName="",isOldOrNew="";
isOldOrNew = (String)request.getAttribute("isOldOrNew");
String selSadaremId =  (String)request.getAttribute("selSadaremId");

resultList =  (ArrayList)request.getAttribute("resultList");
reasonList =  (ArrayList)request.getAttribute("reasonList");
organisanationMaster = (ArrayList)request.getAttribute("organisanationMaster");
mappedflag= (String)request.getAttribute("MappedFlag");
if("Y".equals(mappedflag)){
	selVoId = (String)request.getAttribute("selVoId");
	selVoName = (String)request.getAttribute("selVoName");
}
else{
	selVoId = (String)request.getAttribute("selVoId");
	voList=  (ArrayList)request.getAttribute("voList");
	shgList= (ArrayList)request.getAttribute("shgList");
}
String districtId 			= (String)session.getAttribute("districtId");
basicList   				=  (ArrayList)request.getAttribute("BasicList");
String selmandalid          = CommonUtility.checkNullObj(request.getAttribute("mandalid"));
String msg                   = CommonUtility.checkNullObj(request.getAttribute("msg"));

%>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script>

$(document).ready(function() 
		{
	
	new JsDatePick({
   	 
//		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
			useMode:2,
			target:"deadDate",
			//dateFormat:"%d-%M-%Y",
			//selectedDate:{day:25,month:12,year:2013},
			yearsRange:[1999,2100],
			minDate: 0,
			limitToToday:true,
			cellColorScheme:"beige",
			dateFormat:"%d/%m/%Y",
			imgPath:"img/",
			weekStartDay:1
     });	
	
	
	 if(!( $('#mapflag').val() == 'Y')) 
		 {
			    g_calendarObject_end = new JsDatePick({
			    	 
			     //		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
						useMode:2,
						target:"shgdate",
						//dateFormat:"%d-%M-%Y",
						//selectedDate:{day:25,month:12,year:2013},
						yearsRange:[1989,2100],
						minDate: 0,
						limitToToday:true,
						cellColorScheme:"beige",
						dateFormat:"%d/%m/%Y",
						imgPath:"img/",
						weekStartDay:1
			     });
		 }
     g_calendarObject_end = new JsDatePick({
    	 
// 		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
 			useMode:2,
 			target:"aidsdate",
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
     	 
//   		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
   			useMode:2,
   			target:"surdate",
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
        	 
//   		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
   			useMode:2,
   			target:"recivedDate",
   			//dateFormat:"%d-%M-%Y",
   			//selectedDate:{day:25,month:12,year:2013},
   			yearsRange:[2014,2100],
   			minDate: 0,
   			limitToToday:true,
   			cellColorScheme:"beige",
   			dateFormat:"%d/%m/%Y",
   			imgPath:"img/",
   			weekStartDay:1
        });
	/* $('#aadhraNo').change(function(e)
			{
		    		if(fun_validateAadhaarID(this.val()))
		    		{
		    			return true;
		    		}
			});
	 */
	 $("#aadharno").keyup(function(e)
	    		{
				    	if(fun_validateAadhaarID($(this).val())==false || !($(this).val().length == 12))
						{
						  $("#aadhaaridErrMsg").addClass("errmsg");
				          	  $("#aadhaaridErrMsg").html("Not Valid");
						}
						else
						{
						  $("#aadhaaridErrMsg").removeClass("errmsg");
				          	  $("#aadhaaridErrMsg").html("");
						}
	    		});
	
	$('#edit').click(function() 
			{
			
				//alert("Result : "+Validateform());
				if(Validateform())
					{
                        if(!( $('#mapflag').val() == 'Y')) 
                        {
                        	var e = document.getElementById("shglist");
                        	var strUser = e.options[e.selectedIndex].text;
                        	$('#shgname').val(strUser);
                        }
					 
				   var conf = confirm("Do you want to update the details?");
				   if(conf)
				   {	
					document.pwdEditfrm.target="_self";
					document.pwdEditfrm.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=updatepwdDetails&randomid="+Math.random();
					document.pwdEditfrm.submit();
					}
				  }
					 
			});
		});


function changeselect(val,module)
{
	 
	 if(module == "Aadhar")
	 { 
	   if(val == "NotExist")
	   {
		   $('#aadhardiv').hide();
		   $('#aadharno').val('');
	   }
	   if(val == "Exist")
	   {
		   $('#aadhardiv').show();
	   }
	 }
	 else if(module == "Alive")
	 {
		
		 if(val == "Dead")
		   {
			   document.getElementById('alivediv').style.display="none";
			   document.getElementById('deaddiv').style.display="";
		   }
		   if(val == "Alive")
		   {
			   document.getElementById('alivediv').style.display="";
			   document.getElementById('deaddiv').style.display="none";
			   $('#deadDate').val('');
		   }
	 }
	 else if(module == "shg")
	 {
		 var mapflag = document.getElementById("mapflag").value;
		   if(val == "0")
		   {
			   $('#shgdiv').hide();
			   $('#notshgdiv').show();
			   $('#vo').val('-1');
			   $('#shglist').val('-1');
			   $('#shgdate').val('');
			   
			 
		   }
		   if(val == "1")
		   {
			   $('#notshgdiv').hide();
			   $('#shgdiv').show();
			   $('#notshgreason').val('-1');
			   if(mapflag=="Y")
			   {
				   $('#notmapdiv').hide();
				   $('#mapdiv').show();
			   }
			   else
			   {
				   $('#notmapdiv').show();
				   $('#mapdiv').hide();
			   }   
		   }
	 }
	 
	 else if(module == "aids")
	 {
		 
		   if(val == "Notrequired")
		   {
			   $('#aidsnotrec').hide();
			   $('#aidsrec').hide();
			   $('#applType').val('');
			   $('#aidsorganisanation').val('-1');
			   $('#aidsdate').val('');
			   $('#aidsreason').val('');
		   }
		   if(val == "Received")
		   {
			   $('#aidsnotrec').hide();
			   $('#aidsrec').show();
			   $('#aidsreason').val('');
		   }
		   if(val == "NotReceived")
		   {
			   $('#aidsnotrec').show();
			   $('#aidsrec').hide();
			   $('#applType').val('');
			   $('#aidsorganisanation').val('-1');
			   $('#aidsdate').val('');
		   }
	 }
	 else if(module == "surgical")
	 { 
		 
		   if(val == "NotRequired")
		   {
			   $('#donediv').hide();
			   $('#reqsur').hide();
			   $('#surType').val('');
			   $('#surorganisanation').val('-1');
			   $('#surdate').val('');
			   $('#surreason').val('');
			   
		   }
		   if(val == "Done")
		   {
			   $('#reqsur').hide();
			   $('#donediv').show();
			   $('#surreason').val('');
		   }
		   if(val == "RequiredSurgery")
		   {
			   $('#reqsur').show();
			   $('#donediv').hide();
			   $('#surType').val('');
			   $('#surorganisanation').val('-1');
			   $('#surdate').val('');
		   }
	 }
	 if(module == "pmj")
	 { 
	   if(val == "NotCovered")
	   {
		   $('#pmjY').hide();
		   $('#pmjN').show();
		   $('#accno').val('');
		   $('#bank').val('');
		   $('#branch').val('');
		   $('#ifsc').val('');
		   
		   
	   }
	   if(val == "Covered")
	   {
		   $('#pmjY').show();
		   $('#pmjN').hide();
		   $('#pmjnreason').val('');
	   }
	 }
	 if(module == "aasara")
	 { 
	   if(val == "NotSanctioned")
	   {
		   $('#aasaraN').show();
		  }
	   if(val == "Sanctioned")
	   {
		   $('#aasaraN').hide();
		   $('#aasarareason').val('');
	   }
	 }
   
}
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
function getShgList()
{
  var distId = $('#districtId').val();
  var voId = $('#vo').val();
  postRequest("<%=request.getContextPath()%>/ajax.do?action=getShgListForVO&distId="+distId+"&voId="+voId+"&randomid="+Math.random(),"shgTDID");
  
}
</script>

</head>

<body>

<form name="pwdEditfrm" id="pwdEditfrm" method="post" >
<input type="hidden" name="mapflag" id="mapflag" value='<%=mappedflag %>'>
<input type="hidden" name="personcode" id="personcode" value='<%=selSadaremId%>'>
<input type="hidden" name="isOldOrNew" id="isOldOrNew" value='<%=isOldOrNew%>'>
<input type="hidden" name="districtId" id="districtId" value='<%=districtId%>'>
<input type="hidden" id="mandal" name="mandal" value="<%=selmandalid%>"/>

  <fieldset>
  <legend >PWD Data Validation Form</legend>
<table width="95%" border="0" align="center" cellpadding="12" cellspacing="1" border="1">
<%
ArrayList tempList=new ArrayList();
if(basicList.size()>0 && resultList.size()>0)
{
tempList=(ArrayList)basicList.get(0);
%>
<input type="hidden" id="shgname" name="shgname" value="<%=resultList.get(8)%>"/> 
<tr>
				<td align="center" valign="middle" colspan="4">
				<table align="center" cellspacing="0" cellpadding="0" width="90%" border="1">
					 <tr>
					 <th align="center" valign="middle" class="search_col">SADAREM ID</th>
					 <th align="center" valign="middle" class="search_col">Full Name</th>
					 <th align="center" valign="middle" class="search_col">Relation Name</th>
					 <th align="center" valign="middle" class="search_col">Gender</th>
					 <th align="center" valign="middle" class="search_col">Age</th>
					 <th align="center" valign="middle" class="search_col">Mandal</th>
					 <th align="center" valign="middle" class="search_col">Village</th>
					 <th align="center" valign="middle" class="search_col">Habitation</th>
					 <th align="center" valign="middle" class="search_col">Contact No.</th>
					
					 </tr>
					 <tr>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(0) %> </td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(1) %></td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(2) %></td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(3) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(4) %></td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(5) %></td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(6) %></td>
					 <td align="left" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(7) %></td>
					 <td align="center" valign="middle" class="rowstyle" style="height:30px;"><%=tempList.get(8) %></td>
					 </tr>
					 </table><br/>
			</td>
</tr>

  <tr>
	 <td align="left" valign="middle" class="search_col" width="30%">Aadhaar Card<font color=red>*</font></td>
	   <td align="left" valign="middle" class="search_col2"   colspan="3">
	           <input type="radio"  class="radio" name="exist" id="exist" onclick="changeselect(this.value,'Aadhar');" value="Exist"  <%if((((String)resultList.get(1)).trim()).equals("Exist")) {%>checked<%} %>  />
	                  <label for="radio">Yes
				        <input type="radio" class="radio" name="exist" id="exist" onclick="changeselect(this.value,'Aadhar');" value="NotExist" <%if(!(((String)resultList.get(1)).trim()).equals("Exist")) {%>checked<%} %> />
				      No</label><br/> 
	          <%if((((String)resultList.get(1)).trim()).equals("Exist")) {%>
	           <div id="aadhardiv" style="padding:4px;"  >
	            <input name="aadharno" type="text" id="aadharno" value='<%=resultList.get(2)%>' class="textbox" size="12"  maxlength="12"  autocomplete="off"   onblur="this.value = SpaceReplace(this.value);" onkeypress="return NumbersOnly(event);"  />        
	              <span style="color: #a94442;background-color: #f2dede; border-color: #a94442;font-weight:bold;font-size:12px;" id="aadhaaridErrMsg"></span>
	            </div>             
	         <%}else { %>
	         <div id="aadhardiv" style="padding:4px;display:none;"  >
	            <input name="aadharno" type="text" id="aadharno" value='<%=resultList.get(2)%>' class="textbox" size="12"  maxlength="12" autocomplete="off" onblur="this.value = SpaceReplace(this.value);" onkeypress="return NumbersOnly(event);"  />        
	            <span style="color: #a94442;background-color: #f2dede; border-color: #a94442;font-weight:bold;font-size:12px;" id="aadhaaridErrMsg"></span>
	            </div> 
	       <%} %>
	         </tr>
  <tr>  
     <td align="left" valign="middle" class="search_col" width="10%">Alive or Dead ?<font color=red>*</font></td>
     <td align="left" valign="middle" class="search_col2"   colspan="3">
           <input type="radio" name="alive" id="alive" value="Alive" onclick="changeselect(this.value,'Alive');" <%if((((String)resultList.get(4)).trim()).equals("Alive")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Alive
			        <input type="radio" name="alive" id="alive" value="Dead"  onclick="changeselect(this.value,'Alive');" <%if(!(((String)resultList.get(4)).trim()).equals("Alive")) {%>checked<%} %> class="radio"/>
			      Dead</label><br/> 
      </td>
 
  </tr>
       <tr  id="alivediv" 
          <%
          if(!(((String)resultList.get(4)).trim()).equals("Alive")) 
          {
          %> style="display:none"
         <%} %> >
       <td align="left" valign="middle" class="search_col" width="15%">Member of SHG?<font color=red>*</font></td>
       <td align="left" valign="middle" class="search_col2" width="35%"  colspan="3">
               <input type="radio" name="shg" id="shg" value="1" onclick="changeselect(this.value,'shg');" <%if((((String)resultList.get(6)).trim()).equals("1")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Yes
			        <input type="radio" name="shg" id="shg" value="0" onclick="changeselect(this.value,'shg');"<%if(!(((String)resultList.get(6)).trim()).equals("1")) {%>checked<%} %> class="radio"/>
			      No</label><br/> 
              <%
              if((((String)resultList.get(6)).trim()).equals("1")) 
              {
              %>
                  <div id="shgdiv" style="padding:7px;">
                       <% 
                       if("Y".equals(mappedflag))
                       { 
                       %>
                       <div id="mapdiv">
                      <input type="hidden" id="vo" name="vo"  value="<%=selVoId %>"/>
                      <input type="hidden" id="shglist" name="shglist"  value="<%=resultList.get(9) %>"/>
					 <table cellspacing="1" cellpadding="3">	
                       <tr>
                       	<td><label>VO ID<font color=red>*</font></label></td>
                       	<td><%=selVoId %></td>
                       </tr>
                        <tr>
                        	<td><label>VO Name<font color=red>*</font> </label></td>
                        	<td><%=selVoName%></td>
                        </tr>
                        <tr>
                        	<td><label>SHG ID<font color=red>*</font> </label> </td>
                        	<td><%=resultList.get(9)%>  </td>
                        </tr>
                        <tr>
                        	<td><label>SHG Name<font color=red>*</font></label></td>
                        	<td><%=resultList.get(8)%></td>
                        </tr>
                    </table>
                       </div>
                        <%
                        }
                       else
                       {
                    %>
                           <div id="notmapdiv" > 
	                          <table cellspacing="1" cellpadding="3">	
								<tr><td> <label>VO<font color=red>*</font>  </label></td><td><%=ComboUtil.createStrComboBoxAuto("vo",voList,selVoId,"select-optionItem","onchange=getShgList();",true,true,"")%></td>
	                            <tr><td> <label>SHG<font color=red>*</font> </label></td><td id="shgTDID"><%=ComboUtil.createStrComboBoxAuto("shglist",shgList,(String)resultList.get(9),"select-optionItem","",true,true,"")%></td>
								<tr><td> <label>Formation Date<font color=red>*</font> </label></td>
								<td> <input type="text" readonly="readonly" id="shgdate" name="shgdate" class="datetextbox" value='<%= resultList.get(10)%>'>&nbsp;
	                                    </td>
	                            </table>
                            </div>  
                       <%
                         }
                       %>
                 </div>
  				<div id="notshgdiv" style="padding:5px;display:none">   
                  <label>Reason :<font color=red>*</font> </label>&nbsp;&nbsp;<%=ComboUtil.createStrComboBoxAuto("notshgreason",reasonList,"-1","select-optionItem","",true,true,"")%>
                </div>
            <%
            } 
            else
            {
              %>  
                 <div id="notshgdiv" style="padding:5px;">   
                  <label>Reason :* </label>&nbsp;&nbsp;<%=ComboUtil.createStrComboBoxAuto("notshgreason",reasonList,(String)resultList.get(7),"select-optionItem","",true,true,"")%>
                </div>

				<div id="shgdiv" style="padding:7px;display:none">
                       <%if("Y".equals(mappedflag)){ %>
                      <input type="hidden" id="vo" name="vo"  value='<%=selVoId %>'>
                      <input type="hidden" id="shglist" name="shglist"  value='<%=resultList.get(9) %>'>
                       <div id="mapdiv"  style="display:none">
                          <table cellspacing="1" cellpadding="3">	
                       <tr><td>  <label>VO ID<font color=red>*</font> </label></td><td> <%=selVoId %> </td></tr>
                        <tr><td>   <label>VO Name<font color=red>*</font> </label></td><td>  <%=selVoName %></td></tr>
                        <tr><td>   <label>SHG ID<font color=red>*</font> </label> </td><td> <%=resultList.get(9) %>  </td></tr>
                        <tr><td>   <label>SHG Name<font color=red>*</font></label></td><td>  <%=resultList.get(8) %></td></tr>
                    </table>
                       </div>
                        <%}else{ %>
                           <div id="notmapdiv"  style="display:none"> 
							<table cellspacing="1" cellpadding="3">	
							<tr><td> <label>VO<font color=red>*</font>  </label></td><td><%=ComboUtil.createStrComboBoxAuto("vo",voList,"-1","select-optionItem","onchange=getShgList();",true,true,"")%></td></tr>
                            <tr><td> <label>SHG<font color=red>*</font> </label></td><td id="shgTDID"><%=ComboUtil.createStrComboBoxAuto("shglist",shgList,"-1","select-optionItem","",true,true,"")%></td></tr>
							<tr><td> <label>Formation Date<font color=red>*</font> </label></td><td > <input type="text" readonly="readonly" id="shgdate" name="shgdate" class="datetextbox">&nbsp;
                                     </td></tr>
                              </table>
                            </div>  
                         <%} %>
                 </div>
			<%} %>
             </td> 
      
       </tr>     
      <tr id="deaddiv"  <%
          if((((String)resultList.get(4)).trim()).equals("Alive")) 
          {
          %> style="display:none"
         <%} %> >
         
            <td align="left" valign="middle" class="search_col" width="15%">Death Date<font color=red>*</font></td>
               <td align="left" valign="middle" class="search_col2"  colspan="3" width="35%"> <input name="deadDate" readonly="readonly" type="text" id="deadDate" class="datetextbox"   value='<%=resultList.get(5) %>'/>
			   </td>
               
      </tr>    
      <tr> 
     <td align="left" valign="middle" class="search_col" width="10%">Received any AIDS and Appliances in last 3 years?<font color=red>*</font></td>
     <td align="left" valign="middle" class="search_col2"  colspan="3" >
                <input type="radio" name="aids" id="aids" value="Notrequired"  onclick="changeselect(this.value,'aids');" <%if((((String)resultList.get(11)).trim()).equals("Notrequired")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Not Required
			        <input type="radio" name="aids" id="aids" value="Received" onclick="changeselect(this.value,'aids');" <%if((((String)resultList.get(11)).trim()).equals("Received")) {%>checked<%} %> class="radio"/>
			     Received 
                 <input type="radio" name="aids" id="aids" value="NotReceived" onclick="changeselect(this.value,'aids');" <%if((((String)resultList.get(11)).trim()).equals("NotReceived")) {%>checked<%} %> class="radio"/>
			      Not Received (Eligible) </label><br/><br/>
         <%if((((String)resultList.get(11)).trim()).equals("Received")) {%>
            <div id="aidsrec" style="padding:5px;" >
              <table cellspacing="1" cellpadding="3">	
               <tr><td>  <label>Appliance Type*</label> </td><td> <input name="applType" type="text" id="applType" value='<%=resultList.get(13)%>' class="textbox" size="12" /></td></tr>
                 <tr><td> <label>Organisation*</label></td><td><%=ComboUtil.createStrComboBoxAuto("aidsorganisanation",organisanationMaster,(String)resultList.get(14),"select-optionItem","",true,true,"")%></td></tr>
				<tr><td> <label>Formation Date*</label> </td><td><input type="text" id="aidsdate" name="aidsdate" readonly="readonly" value='<%=((String)resultList.get(15)).trim() %>' class="datetextbox">&nbsp;
                                    </td></tr>
                  </table>        
           </div>
            <div id="aidsnotrec" style="padding:5px;display:none">
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="aidsreason" name="aidsreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"></textarea></div> 
          <%}else if((((String)resultList.get(11)).trim()).equals("NotReceived")) { %>  

			 <div id="aidsrec" style="padding:5px;display:none">
              <table cellspacing="1" cellpadding="3">	
               <tr><td>  <label>Appliance Type<font color=red>*</font></label> </td><td> <input name="applType" type="text" id="applType"   class="textbox" size="12" /></td></tr>
                 <tr><td> <label>Organisation<font color=red>*</font></label></td><td><%=ComboUtil.createStrComboBoxAuto("aidsorganisanation",organisanationMaster,"-1","select-optionItem","",true,true,"")%></td></tr>
				<tr><td> <label>Formation Date<font color=red>*</font></label> </td><td><input type="text" id="aidsdate" name="aidsdate" readonly="readonly"  class="datetextbox">&nbsp;
                                    </td></tr>
                  </table>        
           </div>
            <div id="aidsnotrec" style="padding:5px;" >
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="aidsreason" name="aidsreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(12) %></textarea></div>
          <%}else {  %>
 		<div id="aidsrec" style="padding:5px;display:none">
              <table cellspacing="1" cellpadding="3">	
               <tr><td>  <label>Appliance Type<font color=red>*</font></label> </td><td> <input name="applType" type="text" id="applType"  class="textbox" size="12" /></td></tr>
                 <tr><td> <label>Organisation<font color=red>*</font></label></td><td><%=ComboUtil.createStrComboBoxAuto("aidsorganisanation",organisanationMaster,"-1","select-optionItem","",true,true,"")%></td></tr>
				<tr><td> <label>Formation Date<font color=red>*</font></label> </td><td><input type="text" id="aidsdate" name="aidsdate" readonly="readonly"  class="datetextbox">&nbsp;
                                    </td></tr>
                  </table>  
           </div>
            <div id="aidsnotrec" style="padding:5px;display:none">
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="aidsreason" name="aidsreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(12) %></textarea></div>
         <%} %>
      </td>
	 </tr>

     <tr> 
     <td align="left" valign="middle" class="search_col" width="10%">Surgical Correction?<font color=red>*</font></td>
     <td align="left" valign="middle" class="search_col2"  colspan="3" >
                <input type="radio" name="surgical" id="surgical" value="NotRequired" onclick="changeselect(this.value,'surgical');" <%if((((String)resultList.get(16)).trim()).equals("NotRequired")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Not Required
			        <input type="radio" name="surgical" id="surgical" value="Done" onclick="changeselect(this.value,'surgical');" <%if((((String)resultList.get(16)).trim()).equals("Done")) {%>checked<%} %> class="radio"/>
			      </label><label for="radio">Done 
                 <input type="radio" name="surgical" id="surgical" value="RequiredSurgery" onclick="changeselect(this.value,'surgical');" <%if((((String)resultList.get(16)).trim()).equals("RequiredSurgery")) {%>checked<%} %> class="radio"/>
			      </label><label for="radio">Required Surgery</label><br/><br/>
         <%if((((String)resultList.get(16)).trim()).equals("Done")) {%>
            <div id="donediv" style="padding:5px;" >
              <table cellspacing="1" cellpadding="3"> 
                <tr><td>  <label>Type of Surgery<font color=red>*</font></label> </td><td> <input name="surType" type="text" id="surType" value='<%=resultList.get(18)%>' class="textbox" size="12" /></td></tr>
                <tr><td>   <label>Organisation<font color=red>*</font></label></td><td><%=ComboUtil.createStrComboBoxAuto("surorganisanation",organisanationMaster,(String)resultList.get(19),"select-optionItem","",true,true,"")%></td></tr>
			  <tr><td>   <label>Date of Surgery<font color=red>*</font> </label></td><td> <input type="text" id="surdate" name="surdate" class="datetextbox" readonly="readonly" value='<%=((String)resultList.get(20)).trim() %>'>&nbsp;
                                     
                          </td></tr></table>
           </div> 
			<div id="reqsur" style="display:none">  
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="surreason" name="surreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"></textarea>
           </div>
          <%}else if((((String)resultList.get(16)).trim()).equals("RequiredSurgery")) { %>
            <div id="donediv" style="padding:5px;display:none" >
                <table cellspacing="1" cellpadding="3"> 
                <tr><td>  <label>Type of Surgery<font color=red>*</font></label> </td><td> <input name="surType" type="text" id="surType"   class="textbox" size="12" /></td></tr>
                <tr><td>   <label>Organisation<font color=red>*</font></label></td><td><%=ComboUtil.createStrComboBoxAuto("surorganisanation",organisanationMaster,"-1","select-optionItem","",true,true,"")%></td></tr>
			  <tr><td>   <label>Date of Surgery<font color=red>*</font> </label></td><td> <input type="text" id="surdate" readonly="readonly" name="surdate" class="datetextbox" readonly="readonly">&nbsp;
                                    
                          </td></tr></table>     
           </div> 
			<div id="reqsur">  
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="surreason" name="surreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(17) %></textarea>
           </div>
           <%}else{ %>
			 <div id="donediv" style="padding:5px;display:none" >
                 <table cellspacing="1" cellpadding="3"> 
                <tr><td>  <label>Type of Surgery<font color=red>*</font></label> </td><td> <input name="surType" type="text" id="surType"   class="textbox" size="12" /></td></tr>
                <tr><td>   <label>Organisation<font color=red>*</font></label></td><td><%=ComboUtil.createStrComboBoxAuto("surorganisanation",organisanationMaster,"-1","select-optionItem","",true,true,"")%></td></tr>
			  <tr><td>   <label>Date of Surgery<font color=red>*</font> </label></td><td> <input type="text" readonly="readonly" id="surdate" name="surdate" class="datetextbox"  >&nbsp;
                                    
                          </td></tr></table>
           </div> 
			<div id="reqsur" style="padding:5px;display:none" > 
            <label>Reason<font color=red>*</font></label><textarea class="tarea" id="surreason" name="surreason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"></textarea>
           </div>
          <%} %>


      </td>
	 </tr>      
 
      <tr> 
     <td align="left" valign="middle" class="search_col" width="10%">PMJDY A/C Details (Prime Minister Jan Dhan Yojana)?<font color=red>*</font></td>
     <td align="left" valign="middle" class="search_col2"  colspan="3" >
                <input type="radio" name="pmjdy" id="pmjdy" value="Covered" onclick="changeselect(this.value,'pmj');" <%if((((String)resultList.get(21)).trim()).equals("Covered")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Covered
			        <input type="radio" name="pmjdy" id="pmjdy" value="NotCovered" onclick="changeselect(this.value,'pmj');" <%if((((String)resultList.get(21)).trim()).equals("NotCovered")) {%>checked<%} %> class="radio"/>
			      NotCovered </label>
                 <br/><br/>
         <%if((((String)resultList.get(21)).trim()).equals("Covered")) {%>
            <div id="pmjY" style="padding:5px;" >
              <table cellspacing="1" cellpadding="3"> 
                <tr><td><label>Account Number<font color=red>*</font> </label></td><td>  <input name="accno" type="text" id="accno" value='<%=resultList.get(23)%>' class="textbox" size="12" /></td></tr>
                <tr><td><label>Bank<font color=red>*</font> </label> </td><td><input name="bank" type="text" id="bank" value='<%=resultList.get(24)%>' class="textbox" size="12" /></td></tr>
				<tr><td><label>Branch<font color=red>*</font> </label> </td><td>  <input name="branch" type="text" id="branch" value='<%=resultList.get(25)%>' class="textbox" size="12" /></td></tr>
                <tr><td><label>IFSC Code </label> </td><td>  <input name="ifsc" type="text" id="ifsc" value='<%=resultList.get(26)%>' class="textbox" size="12" /></td></tr>
              </table>                     
           </div> 
       <div id="pmjN" style="padding:5px;display:none;"> <label>Reason<font color=red>*</font></label><textarea class="tarea" id="pmjnreason" name="pmjnreason" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"></textarea></div>
          <%}else if((((String)resultList.get(21)).trim()).equals("NotCovered")) { %>  
          <div id="pmjY" style="padding:5px;display:none;" >
              <table cellspacing="1" cellpadding="3"> 
                <tr><td><label>Account Number<font color=red>*</font> </label></td><td>  <input name="accno" type="text" id="accno"   class="textbox" size="12" /></td></tr>
                <tr><td><label>Bank<font color=red>*</font> </label> </td><td><input name="bank" type="text" id="bank"   class="textbox" size="12" /></td></tr>
				<tr><td><label>Branch<font color=red>*</font> </label> </td><td>  <input name="branch" type="text" id="branch"   class="textbox" size="12" /></td></tr>
                <tr><td><label>IFSC Code </label> </td><td>  <input name="ifsc" type="text" id="ifsc"   class="textbox" size="12" /></td></tr>
              </table></div> 
           <div id="pmjN" style="padding:5px;"> <label style="valign:middle">Reason    </label><textarea class="tarea" id="pmjnreason" name="pmjnreason"   autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(22) %></textarea></div>
          <%}else{ %>
 			<div id="pmjY" style="padding:5px;display:none;" >
              <table cellspacing="1" cellpadding="3"> 
                <tr><td><label>Account Number<font color=red>*</font> </label></td><td>  <input name="accno" type="text" id="accno"   class="textbox" size="12" /></td></tr>
                <tr><td><label>Bank<font color=red>*</font> </label> </td><td><input name="bank" type="text" id="bank"   class="textbox" size="12" /></td></tr>
				<tr><td><label>Branch<font color=red>*</font></label> </td><td>  <input name="branch" type="text" id="branch"   class="textbox" size="12" /></td></tr>
                <tr><td><label>IFSC Code </label> </td><td>  <input name="ifsc" type="text" id="ifsc"   class="textbox" size="12" /></td></tr>
              </table></div> 
           <div id="pmjN" style="padding:5px;display:none;"> <label style="valign:middle">Reason<font color=red>*</font>    </label><textarea class="tarea" id="pmjnreason" name="pmjnreason"   autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(22) %></textarea></div>
      
         <%} %>
      </td>
	 </tr> 

      <tr> 
     <td align="left" valign="middle" class="search_col" width="10%">AASARA Details?<font color=red>*</font></td>
     <td align="left" valign="middle" class="search_col2"  colspan="3" >
                <input type="radio" name="aasara" id="aasara" value="Sanctioned" onclick="changeselect(this.value,'aasara');" <%if((((String)resultList.get(27)).trim()).equals("Sanctioned")) {%>checked<%} %> class="radio"/>
                  <label for="radio">Sanctioned
			        <input type="radio" name="aasara" id="aasara" value="NotSanctioned" onclick="changeselect(this.value,'aasara');" <%if((((String)resultList.get(27)).trim()).equals("NotSanctioned")) {%>checked<%} %> class="radio"/>
			      Not Sanctioned</label>
                 <br/><br/>
         <%if((((String)resultList.get(27)).trim()).equals("NotSanctioned")) { %>  
          <div id="aasaraN" > <label>Reason<font color=red>*</font>    </label><textarea class="tarea" name="aasarareason" id="aasarareason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"><%=resultList.get(28) %></textarea></div>
          <%} else{%>
          <div id="aasaraN" style="display:none;"> <label>Reason<font color=red>*</font></label><textarea class="tarea" name="aasarareason" id="aasarareason" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"></textarea></div>
          <%} %>
      </td>
	 </tr> 
     
     <tr>
        <td align="left" valign="middle" class="search_col" width="10%">SKS ID</td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="serveid" type="text" id="serveid" value='<%=resultList.get(30)%>' class="textbox" size="12" autocomplete="off"  />  
        </td>
     </tr>
      
 <tr>
        <td align="left" valign="middle" class="search_col" width="10%">Validation Done By<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="serveDoneBy" type="text" id="serveDoneBy" value='<%=resultList.get(31)%>' class="textbox" size="12"  onkeypress="return CharactersOnly(event);" autocomplete="off"  onblur="this.value = SpaceReplace(this.value);"/>  
        </td>
     </tr>

 <tr>
        <td align="left" valign="middle" class="search_col" width="10%">Designation<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="designation" type="text" id="designation" value='<%=resultList.get(32)%>' class="textbox" size="12" onkeypress="return CharactersOnly(event);" autocomplete="off"  onblur="this.value = SpaceReplace(this.value);"/>  
        </td>
     </tr>

 <tr>
        <td align="left" valign="middle" class="search_col" width="10%">Validation Done Date<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" valign="middle"> 
          <input name="recivedDate" type="text" id="recivedDate" class="datetextbox" readonly="readonly" value='<%=resultList.get(33)%>'  />
			 
        </td>
     </tr> 

<tr >

   <td colspan="4" class="search_col" align="center" style="border-bottom : black solid 1px;"><input type="button" class="button" value="Submit" id="edit" name="edit" ></td>

</tr>
<%}else{ %>
<tr >

   <td colspan="4" class="search_col" align="center" style="border: black solid 1px;"><font color="red" size="2"><%=msg %></font></td>

</tr>
<%} %>
</table>
</fieldset>
</form>


</body>
<%}catch(Exception e){System.out.println(e); } %>
</html>