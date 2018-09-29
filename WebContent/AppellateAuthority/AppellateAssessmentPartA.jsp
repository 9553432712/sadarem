<%@ page import="java.util.*, com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

<%
ArrayList campList = new ArrayList();
ArrayList basicData = new ArrayList();
String sadaremId="",reqId="",contactNo="";

sadaremId = CommonUtility.checkNullObj(request.getAttribute("sadaremId"));
reqId     = CommonUtility.checkNullObj(request.getAttribute("reqId"));
contactNo     = CommonUtility.checkNullObj(request.getAttribute("contactNo")); 
System.out.println("contact : "+contactNo);

basicData = (ArrayList)request.getAttribute("sadaremData");
campList = (ArrayList)request.getAttribute("campList");
if(basicData.size()>0)
{
	basicData = (ArrayList)basicData.get(0);
}
String captchaCode = CommonUtility.checkNullObj(request.getAttribute("captchaCode"));
String code        =  CommonUtility.checkNullObj(request.getAttribute("code"));

ArrayList disabilityList = new ArrayList();
disabilityList = (ArrayList)request.getAttribute("DisabilityTypeList");
%>


<script>
document.title=":: SADAREM TS :: Appellate Authority Reassessment";

  $(document).ready(function()
		{
	 // setTimeout('$("#otpreturnmsg").hide()',8000);
	//  $('#otpreturnmsg').delay(5000).fadeOut();	
	
	  
			$('#appSubmit').click(function()
			{
			
				if($('#camp').val()=='-1')
				{
					  alert("Please select camp");
					  $('#camp').focus();
					  return false;
				}
				else if($('#txtInput').val()=="" || $('#txtInput').val()==null || $('#txtInput').length == 0)
	        	{ 
	        			alert("Please enter captcha");
	        			$('#txtInput').focus();
	        			return false;
	        	}
				else if($("input[name=personStatusForAU]:checked").val()=="" || $("input[name=personStatusForAU]:checked").val()==null || $("input[name=personStatusForAU]:checked").val().length == 0)
	        	{ 
        			alert("Please enter Person Status");        			
        			return false;
        		}
				else if( $("input[name=personStatusForAU]:checked").val()=="Rejected" && $("#disabilityId").val()=='-1')
				{
					alert("Please select disability");
					$('#disabilityId').focus();
					return false;
				}
				else if( $("input[name=personStatusForAU]:checked").val()=="Rejected" && $("#disabilityId").val()!='-1' && checktable()==false)
				{
					alert("Please fill the  doctors details ");
					return false;
				}
				else
				{
				      document.appellateForm.action="<%=request.getContextPath()%>/AppellateReassess.do?";
				      document.appellateForm.submit();
				}
			});
			
			$('#otpbut').click(function()
			{
				if($('#camp').val()=='-1')
				{
				  alert("Please select camp");
				  $('#camp').focus();
				  return false;
				}
			else
				{

          		$("#otpbutspan").addClass("hide");
              	$("#butsubmit").addClass("hide");
              	$("#otpreturnmsg").html("");
              	OpenIssuepostRequest("<%=request.getContextPath()%>/ajax.do?action=AppellategetOTP&sadaremId="+$('#sadaremId').val()+"&reqId="+$('#reqId').val()+"&randomid="+Math.random(),"otpreturnmsg");
				}
			});
			
		});
  
		  function checktable()
		  {
			  var result = true;
			  try
			  {
					$('#disabTable tr').eq(1).each(function()
					{
						
						var $row = $(this);
						var t1 = $row.find(':nth-child(2)').text();
						var t2 = $row.find(':nth-child(3)').text();
						var t3 = $row.find(':nth-child(4)').text();
						
						if(t1==' ' || t2==' ' || t3==' ' || t1.length==0 || t2.length==0 || t3.length==0)
						{							
							result = false;
						}
						
					 });
					$('#disabTable tr').eq(2).each(function()
					{
								
								var $row = $(this);
								var t1 = $row.find(':nth-child(2)').text();
								var t2 = $row.find(':nth-child(3)').text();
								var t3 = $row.find(':nth-child(4)').text();
								
								if(t1==' ' || t2==' ' || t3==' ' || t1.length==0 || t2.length==0 || t3.length==0)
								{
									result = false;
								}
								
							 });
							$('#disabTable tr').eq(3).each(function()
							{
								
								var $row = $(this);
								var t1 = $row.find(':nth-child(2)').text();
								var t2 = $row.find(':nth-child(3)').text();
								var t3 = $row.find(':nth-child(4)').text();
								
								if(t1==' ' || t2==' ' || t3==' ' || t1.length==0 || t2.length==0 || t3.length==0)
								{				
									result = false;
								}
								
							 });
					return result;
					
				}
			  catch(e){alert(e);}
			  
		  }
  function OpenIssuepostRequest(strURL,textID) 
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
      xmlHttp.onreadystatechange =function(){
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
          	
          	var returnMsg= xmlHttp.getResponseHeader('returnMsg');
          
          	
          	if(returnMsg=='Y')
          	{
          		$("#otpbutspan").addClass("hide");
              	$("#butsubmit").removeClass("hide");
              	$("#otpmsg").removeClass("hide");
              		//	$("#camp").attr("readonly","readonly");
              	updatepage('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
    			  	    '<b>Alert! </b> OTP is sent to your Mobile Number. If not received, then generate OTP again.</div>',textID);
          	}
          	else
          	{updatepage('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
			  	    '<b>Alert! </b> OTP generation failed. Please click on the Generate OTP button.</div>',textID);
          		$("#otpbutspan").removeClass("hide");
              	$("#butsubmit").addClass("hide");
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
  

  $(document).ready(function () 
  {
	  try{
	    $('#showhidedisableList').hide();
	    
	    $('input[type="radio"]').click(function () 
	    {
	    	if($('#camp').val()=='-1')
			{
				  alert("Please select camp");
				  $('#camp').focus();
				  return false;
			}
	    	
	        if (this.value == "Rejected") 
	        { //check value if it is domicilio
	            $('#showhidedisableList').stop(true,true).show(500); //then show
	           
	        } 
	        else
	        {
	            $('#showhidedisableList').stop(true,true).hide(2000); //else hide
	        }
	    });
	    
	    $("#disabilityId").change(
	    	    function()
	    	    {
	    	    	if($("#disabilityId").val()!='-1')
	    	    	{
	    	    		 loadDoctInf();
	    	    	}
	    	    	
	    	    }    
	    	    );
	    	    
	    	    $("#camp").change(
	    	    	    function()
	    	    	    {
	    	    	    	if($("#disabilityId").val()!='-1' && $("#camp").val()!='-1')
	    	    	    	{
		    	    	    	 loadDoctInf();
	    	    	    	}
	    	    	    }    
	    	    	    );
	  }
	  catch(e)
	  {
		  alert(e);
	  }
	});

		function loadDoctInf()
		{			
		     postRequest("<%=request.getContextPath()%>/ajax.do?action=loadDocinf&campId="+ $('#camp').val()+"&disabId="+ $('#disabilityId').val()+"&randomid="+Math.random(),"doctinf");
		   
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
</script>
<style>
.table
   {
      width:90%;
      align:center;
   }
   legend
   {
     width:90%;
   }
   select
   {
    width:30%;
   }
</style>
<div class="main_container">
   <div class="row"> 
       
           <form name="appellateForm" method="post" >
           <input type="hidden" name="sadaremId" id="sadaremId" value='<%=sadaremId %>' >
           <input type="hidden" name="reqId" id="reqId" value='<%=reqId %>' > 
           <input type="hidden" name="rejectedViewstatus" value="true"/>
            <input type="hidden" name="partAUpdateOnly" value="false" />
           	<input type="hidden" name="mode" id="mode" value="openpartb">
           	<input type="hidden" name="district_id" id="district_id" value="<%=basicData.get(1)%>"> 
           	<input type="hidden" name="mandal_id" id="mandal_id" value="<%=basicData.get(2)%>"> 
           	<input type="hidden" name="village_id" id="village_id" value="<%=basicData.get(3)%>"> 
           	<input type="hidden" name="habitation_id" id="habitation_id" value="<%=basicData.get(4)%>">  
           	 <h4 style="text-align:center;color:#1C54CA;">Appellate Authority Reassessment</h4>
           	<fieldset><legend style="color:#1882BD;font-size:18px;">Basic Details</legend></fieldset>
           	<table class="table"  align="center" border="1" width="80%">
	    		<tr>
   			        <td style="border-top:0px;padding:5px;" align="left" valign="middle"><b>SADAREM ID : </b><%=basicData.get(0)%></td>
  				 	<td style="border-top:0px;padding:5px;" align="left" valign="middle"> <b>Name : </b><%=basicData.get(9)%> <%=basicData.get(10)%>
  				 	(<%=basicData.get(11)%>)</td>
  				 	<td style="border-top:0px;padding:5px;" align="left" valign="middle"><b>Gender : </b><%=basicData.get(15)%></td>
  				    <td style="border-top:0px;padding:5px;" rowspan='3' align="center" valign="middle">
  				 	 <%-- <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=basicData.get(0)%>&distName=<%=basicData.get(5)%>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/> --%>
  				 	 <img  align="center" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=basicData.get(0)%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/> 
  				 	 </td>
   			    </tr>
	  			<tr>
	  				 	<td style="padding:5px;" align="left" valign="middle"><b>Relation Name : </b>(<%=basicData.get(16)%>)<%=basicData.get(17)%></td>
	  				 	<td style="padding:5px;" align="left" valign="middle"><b>Contact Number : </b><%=basicData.get(19)%></td>
	  				 	<td style="padding:5px;"  align="left" valign="middle"><b>Proof ID : </b>
	  				 	<%if(!basicData.get(14).equals("-")) {%>
	  				 	<a style="font-family:Verdana, Geneva, sans-serif;" href="<%=request.getContextPath()%>/loadsearchsadaremdtls.do?mode=getaadhaardetailsbyuid&strUID=<%=basicData.get(14)%>&randomid=<%=Math.random()%>" target="_blank">
	  				 	<b><%=basicData.get(14)%></b></a>
	  				 	<%}else{ %>
	  				 	<b><%=basicData.get(14)%></b>
	  				 	<%} %>
	  				 	</td>
	  			</tr>
	  			<tr>
	  			 <td  style="padding:5px;" align="left" valign="middle"><b>District :</b><%=basicData.get(5)%></td>
	  			 <td style="padding:5px;"  align="left" valign="middle"><b>Address :</b><%=basicData.get(6)%>(M),<%=basicData.get(7)%>(V),<%=basicData.get(8)%>(H)</td>
	  			 <td style="padding:5px;" align="left" valign="middle"><b>Date Of Birth : </b><%=basicData.get(12)%></td>
	  			</tr>
	  		</table>
	  		
  		     <div id="otpreturnmsg" class="input-group" style="float: none;padding:3px; text-align: center;margin: 0px auto;">
			</div> 
			<!-- <div class="alert alert-danger">
			  	    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  	    <strong>Alert!</strong> This alert box could indicate a dangerous or potentially negative action.
			  	  </div> -->
  		
	  		 <fieldset><legend style="color:#1882BD;font-size:18px;">Camp Details</legend></fieldset> 
           	<table class="table" width="80%" align="center">		 
	  			<tr>
	  			  <td style="border-top:0px;padding:5px;" align="left" valign="middle"><b>Camp </b> <%=ComboUtil.createStrComboBoxAuto("camp",campList, "", "form-control", "",true,true, "")%>	  			 
				  	<logic:present name="captchaCode">
	               		<font color="blue" size="3"><b><%=captchaCode%></b></font>
	                </logic:present>
                    <input type="text" class="form-control" name="txtInput" id="txtInput"   autoComplete="off" onkeypress="return NumbersOnly(event);" maxlength="5">
				  
				 </td>
	  			</tr>
	  			<tr>
	  						<td>
		  						
	                               <b>Person Status:</b> 
	                           	
	                                <input type="radio" name="personStatusForAU" style="width:25px;" checked value="Eligible" > Eligible
	                           		<input type="radio" name="personStatusForAU" style="width:25px;" value="Rejected"> Rejected
	                           
                           	</td>
                 </tr>
                 <tr>
                           	<td>
                           		<div style="hidden" id="showhidedisableList">                           			
                           			<div class="form-group row" > 
						                            <div class="input-group col-md-3" title="Remarks"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Select Disability<font color='red'>*</font></div>
		                                                   <div id="disability">
												                 <%=ComboUtil.createStrComboBoxAuto("disabilityId",disabilityList, "", "form-control", "",true,true, "")%>
												           </div>
	                                                 </div>
                                    </div>
                                    <div class="row">
                                    </div>
                                    <div class="doctorsinf" id="doctinf">
                                    	<div class="row">
                                    			<table id="disabTable" style=" background-repeat:no-repeat; width:80%;margin:1;" cellpadding="0" cellspacing="0" border="1"> <tbody><tr> <th style="text-align: center;"><b>S.No.</b></th><th style="text-align: center;"><b>Doctor Name</b></th><th style="text-align: center;"><b>Designation</b></th><th style="text-align: center;"><b>Regd No.</b></th></tr> <tr> <td>1</td><td></td><td></td><td></td></tr> <tr> <td>2</td><td></td><td></td><td></td></tr> <tr> <td>3</td><td></td><td></td><td></td></tr> </tbody></table>                                    		
                                    	</div>
                                    </div>
                                    
                                    
                                 </div>
                                
                           	</td>
                           	                        
                </tr>
                
	  		   <tr id="butsubmit" class="">
	  			 <td colspan=2 style="border-top:0px;text-align:center;padding:5px;" > 
	  			 	<button id="appSubmit" class="btn icon-btn-primary btn-primary"  type="button" ><strong style="color:#fff">Submit</strong>
	  			 	</button>
	  			 </td>
	  		   </tr>
         </table>
         <%
			 if (captchaCode != null && captchaCode.length() > 0) 
			{
			%>
			<input type="hidden" id="txtCaptcha" name="txtCaptcha" value="<%=code%>"/>
			<%
			}
		%>
         </form> 
        </div>
      </div>
      
