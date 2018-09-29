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
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pwdrejectedvalidation.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>

 <%
		ArrayList Datalist = new ArrayList();
		ArrayList innerlist = new ArrayList();
		ArrayList basicList = new ArrayList();

		String selSadaremId =  (String)request.getAttribute("selSadaremId");
		String sadaremid =  (String)request.getAttribute("sadaremId");
		innerlist =  (ArrayList)request.getAttribute("RejectList");
		if(innerlist.size()>0)
		{
			Datalist = (ArrayList)innerlist.get(0);
		}
		String oldOrNew =  (String)request.getAttribute("isOldOrNew");
		basicList   =  (ArrayList)request.getAttribute("BasicList");
	    String selmandalid          = CommonUtility.checkNullObj(request.getAttribute("mandalid"));


%>

<script>

function changeselect(val,module)
{
	
	 if(module == "Aadhar")
	 { 
	   if(val == "NotExist")
	   {
		   $('#aadhardiv').hide();
		   $('#aadharno1').val('');
	   }
	   if(val == "Exist")
	   {
		   $('#aadhardiv').show();
	   }
	 } 
	 
}
	 
	 
$(document).ready(function() 
		{
	
	 g_calendarObject_end = new JsDatePick({
    	 
//		minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
			useMode:2,
			target:"recivedDate1",
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
	 
	$('#reject').click(function() 
			{
		
		    if(RegValidateform())
			{
		    	var conf = confirm("Do you want to Save ?");
			 if(conf)
			 {	
				document.rejectform.target="_self";
				document.rejectform.action="<%=request.getContextPath()%>/updatecapturedeaddetails.do?mode=submitRejectedpwdDetails&randomid="+Math.random();
				document.rejectform.submit();
				$('#reject').prop("disabled", true);
				/*Screen Locking Started */
				$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
			    $('#processlayer').css({"display": "block","z-index":"110000"});
			/*Screen Locking Ended */
			}
			}
		
			});
	
	$("#aadharno1").keyup(function(e)
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

	
	
		});
</script>


</head>


<body>

<form name="rejectform" method="post">
  <input name="oldOrNew" type="hidden" id="oldOrNew" value='<%=oldOrNew %>'/>  
   <input name="sadaremid" type="hidden" id="sadaremid" value='<%=selSadaremId %>' /> 
   <input type="hidden" id="mandal" name="mandal" value="<%=selmandalid%>"/> 
   
  <fieldset>
  <legend class='legend' style=" background-color:maroon;">PWD Data Validation Rejected Form</legend>
<table width="80%" border="0" align="center" cellpadding="12" cellspacing="1" border="1">
		<%
		ArrayList tempList=new ArrayList();
		tempList=(ArrayList)basicList.get(0);
		%>
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
	  <td align="left" valign="middle" class="search_col" width="35%"> Aadhaar Card<font color=red>*</font></td>
	   <td align="left" valign="middle" class="search_col2"   colspan="3">
	   <input type="radio"  class="radio" name="exist" id="exist" onclick="changeselect(this.value,'Aadhar');" value="Exist"  <%if((((String)Datalist.get(1)).trim()).equals("Exist")) {%>checked<%} %> />
       <label for="radio">Yes
	        <input type="radio" class="radio" name="exist" id="exist" onclick="changeselect(this.value,'Aadhar');" value="NotExist" <%if(!(((String)Datalist.get(1)).trim()).equals("Exist")) {%>checked<%} %> />
	      </label>No<br/> 
         <%if((((String)Datalist.get(1)).trim()).equals("Exist")) {%>
	       
	           <div id="aadhardiv" style="padding:4px;">
	            <input name="aadharno1" type="text" id="aadharno1" value='<%=Datalist.get(2)%>' class="textbox" size="12" maxlength="12" onkeypress="return NumbersOnly(event);"autocomplete="off" onblur="this.value = SpaceReplace(this.value);" />        
	              <span style="color: #a94442;background-color: #f2dede; border-color: #a94442;font-weight:bold;font-size:12px;" id="aadhaaridErrMsg"></span>
	            </div> 
	            
	                  <%} 
			         else
			         {%>
	           <div id="aadhardiv" style="padding:4px;display:none;">
	            <input name="aadharno1" type="text" id="aadharno1" value='<%=Datalist.get(2)%>' class="textbox" size="12" maxlength="12" onkeypress="return NumbersOnly(event);"autocomplete="off" onblur="this.value = SpaceReplace(this.value);" />      
	             <span style="color: #a94442;background-color: #f2dede; border-color: #a94442;font-weight:bold;font-size:12px;" id="aadhaaridErrMsg"></span>
	            </div>
				<%} %>
	            </tr>
  
     
     <tr>
        <td align="left" valign="middle" class="search_col" width="35%">SKS ID</td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="serveid1" type="text" id="serveid1" value='<%=Datalist.get(3)%>' class="textbox" size="12"  autocomplete="off" onblur="this.value = SpaceReplace(this.value);"/>  
        </td>
     </tr>
      
 <tr>
        <td align="left" valign="middle" class="search_col" width="35%">Validation Done By<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="serveDoneBy1" type="text" id="serveDoneBy1" value='<%=Datalist.get(4)%>' class="textbox" size="12" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"/>  
        </td>
     </tr>

 <tr>
        <td align="left" valign="middle" class="search_col" width="35%">Designation<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" > 
          <input name="designation1" type="text" id="designation1" value='<%=Datalist.get(5)%>' class="textbox" size="12" onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);"/>  
        </td>
     </tr>

 <tr>
        <td align="left" valign="middle" class="search_col" width="35%">Validation Done Date<font color=red>*</font></td>
        <td align="left" valign="middle" class="search_col2"  colspan="3" valign="middle"> 
          <input name="recivedDate1" type="text" id="recivedDate1" class="datetextbox" readonly="readonly" value='<%=Datalist.get(6)%>' onkeypress="return CharactersOnly(event);" autocomplete="off" onblur="this.value = SpaceReplace(this.value);" />
			 </td>
     </tr> 

<tr >

   <td colspan="4" class="search_col" align="center">
   <input type="button" class="button" value="Submit" id="reject" name="reject" ></td>

</tr>

</table>
</fieldset>
</form>


</body>

