 <%@page import="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,java.util.ArrayList" %>
 
 <%
   ArrayList basicData = new ArrayList(); 
   ArrayList mandalList = new ArrayList();
   ArrayList panchayatList = new ArrayList();
   ArrayList villageList = new ArrayList();
   ArrayList habitationList = new ArrayList();
   ArrayList proofTypeList = new ArrayList();
   ArrayList relationTypeList = new ArrayList();
   ArrayList educationList = new ArrayList();
   
   String selIssueType = "",sadaremId="";
   String selmandal = "";
   String selpanchayat="";
   String selvillage = "";
   String selhabitation = "";
   String decription="";
   try{
basicData       = (ArrayList)request.getAttribute("sadaremData"); 
mandalList      = (ArrayList)request.getAttribute("mandalList");
panchayatList   = (ArrayList)request.getAttribute("panchayatList");
villageList     = (ArrayList)request.getAttribute("villageList");
habitationList  = (ArrayList)request.getAttribute("habitationList");
relationTypeList = (ArrayList)request.getAttribute("relationTypeList");

educationList = (ArrayList) request.getAttribute("educationList");
selIssueType = (String)request.getAttribute("selIssueType");
String flag =CommonUtility.checkNullObj( (String)request.getAttribute("flag"));

if(basicData.size()>0)
{
	basicData      = (ArrayList)basicData.get(0);
	selmandal      = (String)basicData.get(2);
	selpanchayat   = (String)basicData.get(3);
	selvillage     = (String)basicData.get(3);
	selhabitation  = (String)basicData.get(4);
}
String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
String fullname = "&#3114;&#3138;&#3120;&#3149;&#3108;&#3135; &#3114;&#3143;&#3120;&#3137;";
String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";

String FormID 	= CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
proofTypeList =(ArrayList) request.getAttribute("proofTypeList") ;
String issueName = CommonUtility.checkNullObj(request.getAttribute("issueName"));
String statusmsg = CommonUtility.checkNullObj(request.getAttribute("statusMsg"));
%>

<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/commonstyle.css" />
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/main.min.css" />
<link rel="stylesheet"href="<%=request.getContextPath()%>/DisabilityUITG/css/style.css" />
<link rel="stylesheet"href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css" />
<link rel="stylesheet" type="text/css" media="all"href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/Common.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>

<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/openissuetracking.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/scripts/ProofValidation.js"></script>


  <style>
.btn {
	width: auto !important;
	padding: 5px !important;
	cursor: pointer !important;
}

/* Process Layer Started */
#processlayer {
	width: 300px;
	height: 50px;
	background: #ECF1EF;
	border: red dotted 5px;
	text-align: center;
	position: fixed;
	margin-right: -150px;
	margin-top: -75px;
	right: 50%;
	top: 50%;
	z-index: 99999;
	display: none;
}

#blocklayer {
	position: absolute;
	left: 0;
	top: 0;
	background: #ECF1EF;
}

/* 	 Process Layer Ended  */
.panel-heading a:after {
	font-family: 'Glyphicons Halflings';
	content: "\e114";
	float: right;
	color: grey;
}

.panel-heading a.collapsed:after {
	content: "\e080";
}

.bs-example {
	margin: 20px;
}

.errmsg {
	color: red;
}

.mycomboStyle {
	width: 125px !important;
}



inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.inner_page_content {
  background: rgb(255, 255, 255) none repeat scroll 0% 0%;
  margin-top: 30px;
  margin-bottom: 30px;
  padding-top: 0px;
  padding-bottom: 15px;
  border-radius: 5px;
  box-shadow: 0px 0px 0px 3px #607D8B
}
.inner_page_content {
  min-height: 473px;
  margin: 0px;
}
.text_box {
  width: 100%;
  margin-bottom: 6px;
  padding: 5px 5px 5px 6px;
  border: 1px solid #DDD;
  border-radius: 3px;
}
.text_box {
  width: 100%;
  margin-bottom: 6px;
  padding: 5px 5px 5px 6px;
  border: 1px solid #DDD;
  border-radius: 3px;
}
input, button, select, textarea {
  background-image: none;
  border: 1px solid #e1e1e1;
  padding: 7px;
  margin-bottom: 15px;
  font-size: 12px;
}
input, button, select, textarea {
  background-image: none;
  border: 1px solid #e1e1e1;
  padding: 7px;
  margin-bottom: 15px;
  font-size: 12px;
}
.heading_title{
	font-size:16px;
	font-family:Verdana, Geneva, sans-serif;
	font-weight:bold;
	padding:3px;
	padding-left:10px;
	color:#333;
}





</style>
 <script>
 
 
 

 
 
 
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

 function loadvillages(){

		
     postRequest("<%=request.getContextPath()%>/ajax.do?action=loadvillagerep&manId="+ $('#mandals').val()+"&sadaremId="+ $('#sadaremid').val()+"&randomid="+Math.random(),"villageTDID");
   
}

 function loadhabitation(){

		
     postRequest("<%=request.getContextPath()%>/ajax.do?action=loadhabitationrep&manId="+ $('#mandals').val()+"&villageId="+ $('#villages').val()+"&sadaremId="+ $('#sadaremid').val()+"&randomid="+Math.random(),"habitationTDID");
   
}
 
 

 
$(document).ready( function()
		{

	
	document.title = "::SADAREM :: ";
	
	
	$("#printbutid").click(function()
			{  
				  w=window.open(null, 'Print_Page', 'scrollbars=yes');
				  w.document.write('<html><head><title>::Print::</title>');
				  w.document.write('<style>@media print {  #buthide, #closebutid { display: none !important; }</style></head><body>')
			      w.document.write("Printed On : <%=CommonUtility.getDateTime("dd/MMM/yyyy hh:mm:ss")%>"+jQuery('#printdivid').html());
			  w.document.write("<center><button type='button' id='buthide' onclick='window.print()'>Print</button>");
			  w.document.write("&nbsp;&nbsp;&nbsp;<button type='button' id='closebutid' onclick='window.close()'>Close</button>");
			  w.document.write("</center></body></html>");
			    w.document.close();
			   // w.print();
			});
	
	
	 $('#mandals').change(function()
				{	
			    
			       postRequest("<%=request.getContextPath()%>/ajax.do?action=loadpanchayatopenrep&manId="+ $('#mandals').val()+"&sadaremId="+ $('#sadaremid').val()+"&randomid="+Math.random(),"panchayatTDID");
			      
					});
	 g_calendarObject =	new JsDatePick({
	    	
			
//			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
			useMode:2,
			target:"dob",
			//dateFormat:"%d-%M-%Y",
			//selectedDate:{day:25,month:12,year:2013},
			yearsRange:[1900,2017],
			minDate: 0,
			limitToToday:true,
			cellColorScheme:"beige",
			dateFormat:"%d/%m/%Y",
			imgPath:"img/",
			weekStartDay:1,
			
      });	
	 
	 g_calendarObject.addOnSelectedDelegate(function(){
		   var dateString= $('#dob').val();
		   var parts = dateString.split('/');
		   var today = new Date();
		   var birthDate = new Date(parts[2],parts[1]-1,parts[0]);
		   var age = today.getFullYear() - birthDate.getFullYear();
		   var m = today.getMonth() - birthDate.getMonth();
		    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) 
		    {
		        age--;
		    }
		    
		   $('#ageVal').html('Age : '+age);
   	});

	 $("#namecheckbox").click(function()
			 {  
				if( $('#namecheckbox').is(':checked'))
					{
					$('#namecheckbox1').val('namecheck');
					document.getElementById('surname').disabled = false;
					document.getElementById("fname").disabled = false;
					document.getElementById("telname").disabled = false;
					}
				else
					{
					$('#namecheckbox1').val('');
					document.getElementById("surname").disabled = true;
					document.getElementById("fname").disabled = true;
					document.getElementById("telname").disabled = true;
					$('#surname').val('<%=basicData.get(9)%>');
					$('#fname').val('<%=basicData.get(10)%>');
					$('#telname').val('<%=basicData.get(10)%>');
					document.getElementById("fulnametelugu").innerHTML = '<%=basicData.get(11)%>';
					}
					
			 });
	 $("#relationcheckbox").click(function()
			 {  
				if( $('#relationcheckbox').is(':checked'))
					{
					$('#relationcheckbox1').val('relationcheck');
					document.getElementById("relationname").disabled = false;
					document.getElementById("relationType").disabled = false;
					document.getElementById("teluguname").disabled = false;
					}
				else
					{
					$('#relationcheckbox1').val('');
					document.getElementById("relationname").disabled = true;
					document.getElementById("relationType").disabled = true;
					document.getElementById("teluguname").disabled = true;
					$('#relationname').val('<%=basicData.get(17)%>');
					$('#relationType').val('<%=basicData.get(25)%>');
					$('#teluguname').val('<%=basicData.get(17)%>');
					document.getElementById("fullnametelugu").innerHTML = '<%=basicData.get(18)%>';
					}
					
			 });
	 $("#dobcheckbox").click(function()
			 {  
				if( $('#dobcheckbox').is(':checked'))
					{
					$('#dobcheckbox1').val('dobcheck');
					document.getElementById("dob").disabled = false;
					}
				else
					{
					$('#dobcheckbox1').val('');
					document.getElementById("dob").disabled = true;
					$('#dob').val('<%=basicData.get(12)%>');

					}
					
			 });
	 $("#educationcheckbox").click(function()
			 {  
				if( $('#educationcheckbox').is(':checked'))
					{
					$('#educationcheckbox1').val('educationcheck');
					document.getElementById("educationType").disabled = false;
					}
				else
					{
					$('#educationcheckbox1').val('');
					document.getElementById("educationType").disabled = true;
					$('#educationType').val('<%=basicData.get(21)%>');

					}
					
			 });
					
	 $("#addresscheckbox").click(function()
			 {  
				if( $('#addresscheckbox').is(':checked'))
					{
					$('#addresscheckbox1').val('addresscheck');
					document.getElementById("mandals").disabled = false;
					document.getElementById("panchayats").disabled = false;
					document.getElementById("villages").disabled = false;
					document.getElementById("habitation").disabled = false;
					document.getElementById("houseno").disabled = false;
					document.getElementById("pincode").disabled = false;
					}
				else
					{
					$('#addresscheckbox1').val('');
					document.getElementById("mandals").disabled = true;
					document.getElementById("panchayats").disabled = true;
					document.getElementById("villages").disabled = true;
					document.getElementById("habitation").disabled = true;
					document.getElementById("houseno").disabled = true;
					document.getElementById("pincode").disabled = true;
					
					$('#mandals').val('<%=basicData.get(2)%>');
					$('#panchayats').val('<%=basicData.get(3)%>');
					$('#villages').val('<%=basicData.get(3)%>');
					$('#habitation').val('<%=basicData.get(4)%>');
					$('#houseno').val('<%=basicData.get(26)%>');
					$('#pincode').val('<%=basicData.get(27)%>');
					}
					
			 });
    $("#raiseissueButID").click(function ()
	{
    
    	 if($('#namecheckbox').is(':checked') && ($('#surname').val()=="" || $('#surname').val()==null || $('#surname').length == 0))
			{
				alert("Please enter Surname");
				$('#surname').focus();
				return false;
			}
			
	      else if($('#namecheckbox').is(':checked') && ($('#fname').val()=="" || $('#fname').val()==null || $('#fname').length == 0))
			{
				alert("Please enter Name");
				$('#fname').focus();
				return false;
			}
	      else if($('#namecheckbox').is(':checked') && ($('#telname').val()=="" || $('#telname').val()==null || $('#telname').length == 0))
			{
				alert("Please enter telugu name");
				$('#telname').focus();
				return false;
			}
	      else if($('#relationcheckbox').is(':checked') && ($('#relationname').val()=="" || $('#relationname').val()==null || $('#relationname').length == 0))
			{
				alert("Please enter Relation Name");
				$('#relationname').focus();
				return false;
			}
		  else if ($('#relationcheckbox').is(':checked') && $('#relationType').val()==-1)
         {
                   alert("Please Select Relation type");
                   $('#relationType').focus();
                   return false
         }
       else if($('#relationcheckbox').is(':checked') && ($('#teluguname').val()=="" || $('#teluguname').val()==null || $('#teluguname').length == 0))
			{
				alert("Please enter telugu name");
				$('#teluguname').focus();
				return false;	
			}
       else if($('#dobcheckbox').is(':checked') && ($('#dob').val()=="" || $('#dob').val()==null || $('#dob').length == 0))
		  {
		      alert("Please select date of birth");
		      $('#dob').focus();
		      return false;
		  }
       else if($('#educationcheckbox').is(':checked') && ($('#educationType').val()==-1))
		  {
		      alert("Please select Qualification Type");
		      $('#educationType').focus();
		      return false;
		  }
    	 else if ($('#addresscheckbox').is(':checked') && $('#mandals').val()==-1)
                  {
                      alert("Please Select Mandal type");
                      $('#mandals').focus();
                      return false;
                  }
          
	      else if ($('#addresscheckbox').is(':checked') && $('#panchayats').val()==-1)
          {
              alert("Please Select Panchayat type");
              $('#panchayats').focus();
              return false;
          }
          
	      else if ($('#addresscheckbox').is(':checked') && $('#villages').val()==-1)
          {
              alert("Please Select Village type");
              $('#villages').focus();
              return false;
          }
	      else if ($('#addresscheckbox').is(':checked') && $('#habitation').val()==-1)
          {
              alert("Please Select Habitation type");
              $('#habitation').focus();
              return false;
          }

	      else if($('#addresscheckbox').is(':checked') && ($('#houseno').val()=="" || $('#houseno').val()==null || $('#houseno').val().length == 0))
			{
				alert("Please enter House No");
				$('#houseno').focus();
				return false;
			}
	      
	      else if($('#addresscheckbox').is(':checked') && ($('#pincode').val()=="" || $('#pincode').val()==null ||  $('#pincode').val().length!=6))
			{   
	    	alert("Please enter valid PinCode");
				$('#pincode').focus();
				return false;	
			}
   	   else if(!$("#name_issue input:checked").length>0)
	   {
   			alert("Please click on any one of the checkbox");
   				return false;
	   }
	      else if($('#decription').val()=="" || $('#decription').val()==null || $('#decription').val().length == 0)
			{
				alert("Please enter remarks");
				$('#decription').focus();
				return false;
			}
	      else if($('#proofidentity_1').val()=="-1" || $('#proofidentity_1').val()==null || $('#proofidentity_1').length ==0 )
          {
      		alert("Please select the Proof Identity type");
      		$('#proofidentity_1').focus();
      		return false;
      	 }
	      	else if($('#proofid_1').val()=="" || $('#proofid_1').val()==null || $('#proofid_1').length ==0 )
	      	{

	      		alert("Please Enter Proof Id");
	      		$('#proofid_1').focus();
	      		return false;
	      	}
	      	else if($('#proofDoc_1').val()=="" || $('#proofDoc_1').val()==null || $('#proofDoc_1').length == 0)
	      	{
  
	      			alert("Please upload required document");
	      			$('#proofDoc_1').focus();
	      			return false;
	      	}
	        else if($('#proofidentity_2').val()=="-1" || $('#proofidentity_2').val()==null || $('#proofidentity_2').length ==0 )
	          {
	      		alert("Please select the Proof Identity type");
	      		$('#proofidentity_2').focus();
	      		return false;
	      	 }
		      	else if($('#proofid_2').val()=="" || $('#proofid_2').val()==null || $('#proofid_2').length ==0 )
		      	{

		      		alert("Please Enter Proof Id");
		      		$('#proofid_2').focus();
		      		return false;
		      	}
		      	else if(($('#proofid_2').val() != -1) && ($('#proofid_1').val()==$('#proofid_2').val()))
	        	{
	        		$('#collapseTwo').toggleClass('panel panel-collapse in');
	           	 	$('#plusminus2').toggleClass('glyphicon glyphicon-minus');
	        			alert("Please enter different proof id");
	        			$('#proofid_2').focus();
	        			return false;
	        	}
		      	else if($('#proofDoc_2').val()=="" || $('#proofDoc_2').val()==null || $('#proofDoc_2').length == 0)
		      	{
	  
		      			alert("Please upload required document");
		      			$('#proofDoc_2').focus();
		      			return false;
		      	}
		      	 else if($('#proofidentity_3').val()=="-1" || $('#proofidentity_3').val()==null || $('#proofidentity_3').length ==0 )
		          {
		      		alert("Please select the Proof Identity type");
		      		$('#proofidentity_3').focus();
		      		return false;
		      	 }
			      	else if($('#proofid_3').val()=="" || $('#proofid_3').val()==null || $('#proofid_3').length ==0 )
			      	{

			      		alert("Please Enter Proof Id");
			      		$('#proofid_3').focus();
			      		return false;
			      	}
			      	else if($('#proofDoc_3').val()=="" || $('#proofDoc_3').val()==null || $('#proofDoc_3').length == 0)
			      	{
		  
			      			alert("Please upload required document");
			      			$('#proofDoc_3').focus();
			      			return false;
			      	}
		
	      	else if(!($('#proofidentity_1').val()=="PR009") && !($('#proofidentity_2').val()=="PR009") && !($('#proofidentity_3').val()=="PR009"))
	      	{

	      			alert("Please upload Form A scanned copy as it is mandatory for this grievance");
	      			//$('#proofidentity_3').focus();
	      			return false;
	      	} 
	      	else if(!($('#proofidentity_1').val()=="PR008") && !($('#proofidentity_2').val()=="PR008") && !($('#proofidentity_3').val()=="PR008"))
	      	{

	      			alert("Please upload Authorised approval letter as it is mandatory for this grievance");
	      			$('#proofidentity_3').focus();
	      			return false;
	      	} 
	    	else if($('#proofidentity_1').val()==$('#proofidentity_2').val() || $('#proofidentity_1').val()==$('#proofidentity_3').val() || $('#proofidentity_2').val()==$('#proofidentity_3').val())
	      	{

	      		alert("Please select a different Proof Identity type");
	      		$('#proofid_3').val('');
	      		$("#proofDoc_3").val('');
	      		$('#proofidentity_3').focus();
	      		return false;
	      	}
	
	          
	      else
			{  
	    	  if(confirm("Are you sure you want to submit the request?"))
				{
	    		   $("#name_issue input, #name_issue select").prop('disabled',false);   
	    		   $('#flag').val('1');
				 document.name_issue.target="_self";
				 document.name_issue.action="<%=request.getContextPath()%>/multiplegrievanceIssueFlow.do";
				 document.name_issue.submit();
					/*Screen Locking Started */
					$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
				    $('#processlayer').css({"display": "block","z-index":"110000"});
				/*Screen Locking Ended */
				    $('#memberModal').modal('hide');
				} 
		 	}
			  
  	  
	     	  
			});

	 	
	       $("#cancelissueButID").click(function ()
    			{
    			 if(confirm("Are you sure you want to cancel the request?"))
    		     {
    				 $('#memberModal').modal('hide');
    				 $('#decription').val(''); 
    					$('#proofid_1').val('');
    		      		$("#proofDoc_1").val('');
    		      		$('#proofid_2').val('');
    		      		$("#proofDoc_2").val('');
    		      		$('#proofid_3').val('');
    		      		$("#proofDoc_3").val('');
    				 
    	          return false;
    		     }
    			}); 
	   

          


		});


 
</script>  
 


<!-- new -->

<div class="main_container">
<div id="processlayer" >
		<font color="blue" size="2">Processing Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div> 
<form name="name_issue" id="name_issue" method="post" enctype="multipart/form-data">
<input type="hidden" name="sadaremid" id="sadaremid" value='<%=basicData.get(0)%>'>
<input type="hidden" name="selIssueType" id="selIssueType" value='<%=selIssueType%>'>
<input type="hidden" id="FormSessionID" name="FormSessionID" value="<%=FormID%>" />
<input type="hidden" id="flag" name="flag" value="<%=flag%>" />
					<div class="container" style="margin-top:20px;width:100%;">
					 <div class="col-sm-12" style="text-align:center;">     <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> </div>

	  <!-- start writr your content here  -->
         <div class="container" style="background-color:#fff;">
    <div class="row inner_page_content" style=" margin-bottom:30px;">
    <div class="container-fluid" >
   
     <!-- start writr your content here  -->
<div class="row" style="margin:0px">
                <div class="col-xs-12 col-md-12 col-sm-4 heading_title" style="background-color:#337ab7; color:#fff; -webkit-border-top-left-radius: 6px;
						-webkit-border-top-right-radius: 6px;
						-moz-border-radius-topleft: 6px;
						-moz-border-radius-topright: 6px;
						border-top-left-radius: 6px;
						border-top-right-radius: 6px;">
                             <input type="checkbox" id="namecheckbox" name="namecheckbox" value="">Full Name Correction
                              <input type="hidden" id="namecheckbox1" name="namecheckbox1" value="">
                </div>
   
                <div class="col-xs-12 col-sm-3">
                            <label class="form_lable">Surname</label>
                            <input class="text_box" name="surname" id="surname" type="text" placeholder="Surname" autocomplete="off"    value='<%=basicData.get(9)%>' onBlur="this.value = SpaceReplace(this.value);"  onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="100" disabled>
               </div>
               <div class="col-xs-12 col-sm-3">
                             <label class="form_lable">First Name</label>
                             <input class="text_box" type="text" placeholder="First Name" name="fname" id="fname"  autocomplete="off" value='<%=basicData.get(10)%>' onBlur="this.value = SpaceReplace(this.value);" onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="150" disabled>
               </div>
               <div class="col-xs-12 col-sm-4">
                             <label class="form_lable"> <%=telugu%>(<%=fullname%>)</label>
                             <input type="text" name="telname" id="telname" class="text_box" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);"  onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()" value='<%=basicData.get(10)%>' disabled>
                             <div class="text_box"  id="fulnametelugu" name="fulnametelugu" ><%=(String)basicData.get(11)%></div>  
					         <input type="hidden" name="hidfulnametelugu" id="hidfulnametelugu" value='<%=(String)basicData.get(11)%>'>
			  </div>
                                    
</div> 


      <!-- end writr your content here  -->
      
<div class="row" style="margin:0px">
                  <div class="col-xs-12 col-md-12 col-sm-4 heading_title" style="background-color:#337ab7; color:#fff; -webkit-border-top-left-radius: 6px;
									-webkit-border-top-right-radius: 6px;
									-moz-border-radius-topleft: 6px;
									-moz-border-radius-topright: 6px;
									border-top-left-radius: 6px;
									border-top-right-radius: 6px;">
                                 <input type="checkbox" id="relationcheckbox" name="relationcheckbox" value=""> Relation Details correction
                                 <input type="hidden" id="relationcheckbox1" name="relationcheckbox1" value="">
                   </div>
                   <div class="col-xs-12 col-sm-3">
                            <label class="form_lable">Relation Name</label>
                            <input class="text_box" name="relationname" id="relationname" autocomplete="off"    value='<%=basicData.get(17)%>' onBlur="this.value = SpaceReplace(this.value);"  onkeypress="return onKeyPressAlphbateOnly(event);" maxlength="100" disabled>  
                   </div>
                   <div class="col-xs-12 col-sm-3">
                           <label class="form_lable" value='<%=(String)basicData.get(16)%>'>Relation Type</label>
                           <%=ComboUtil.createStrComboBoxAuto("relationType",relationTypeList,(String)basicData.get(25), "text_box","disabled",true,true,"")%> 
                  </div>
                  <div class="col-xs-12 col-sm-4">
                           <label class="form_lable"> <%=telugu%>(<%=fullname%>)</label>
                           <input type="text" name="teluguname" id="teluguname" class="text_box" autocomplete="off" onkeypress="return onKeyPressAlphbateOnly(event);"  onkeyup="javascript1:relation_many_words()" onfocus="javascript1:relation_many_words()" value='<%=basicData.get(17)%>' disabled>
				            <div class="text_box"  id="fullnametelugu" name="fullnametelugu"><%=(String)basicData.get(18)%></div>  
					       <input type="hidden" name="hidfullnametelugu" id="hidfullnametelugu" value='<%=(String)basicData.get(18)%>'>
				 </div>
                              
</div>
                            
<div class="row" style="margin:0px">
               <div class="col-xs-12 col-md-12 col-sm-4 heading_title" style="background-color:#337ab7; color:#fff; -webkit-border-top-left-radius: 6px;
							-webkit-border-top-right-radius: 6px;
							-moz-border-radius-topleft: 6px;
							-moz-border-radius-topright: 6px;
							border-top-left-radius: 6px;
							border-top-right-radius: 6px;">
                            <input type="checkbox" id="dobcheckbox" name="dobcheckbox" value=""> Date of Birth correction
                            <input type="hidden" id="dobcheckbox1" name="dobcheckbox1"  value="">
               </div>
               
               
                <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">Date Of Birth</label>
                      <input class="text_box" id="dob"  name="dob"  value='<%=basicData.get(12)%>'   disabled readOnly="readOnly"></div>
               
               
               
               <div class="col-xs-12 col-sm-3">
                 <label class="form_lable">&nbsp;</label>
                       <div class="text_box" id="ageVal"  name="ageVal" style="color:red; height:16px;"  ></div>
               
               
               
               
               
               
               
            <!--      <div class="col-xs-12 col-sm-3">
                      <p> <span class="glyphicon glyphicon-chevron-down" style="padding-down:none;"></span></p> 
	             <div class="text_box" id="ageVal"  name="ageVal" style="color:red;"  > 
                </div>
                 </div>   -->                                                         
</div> 
                         
                         
<div class="row" style="margin:0px">
          <div class="col-xs-12 col-md-12 col-sm-4 heading_title" style="background-color:#337ab7; color:#fff; -webkit-border-top-left-radius: 6px;
						-webkit-border-top-right-radius: 6px;
						-moz-border-radius-topleft: 6px;
						-moz-border-radius-topright: 6px;
						border-top-left-radius: 6px;
						border-top-right-radius: 6px;">
                            <input type="checkbox" id="educationcheckbox" name="educationcheckbox"  value=""> Qualification Details correction
                             <input type="hidden" id="educationcheckbox1" name="educationcheckbox1"  value="">
          </div>
     
                 <div class="col-xs-12 col-sm-3">
	                       <label class="form_lable">Qualification</label>
	                       <%=ComboUtil.createStrComboBoxAuto("educationType",educationList, (String) basicData.get(36),"text_box", "disabled", true, true, "")%>  
	           
                  </div>                                                                            
</div>
                         

                         
     <div class="row" style="margin:0px">
            <div class="col-xs-12 col-md-12 col-sm-4 heading_title" style="background-color:#337ab7; color:#fff; -webkit-border-top-left-radius: 6px;
                   -webkit-border-top-right-radius: 6px;
                   -moz-border-radius-topleft: 6px;
                   -moz-border-radius-topright: 6px;
                    border-top-left-radius: 6px;
                    border-top-right-radius: 6px;">
                            <input type="checkbox" id="addresscheckbox" name="addresscheckbox"  value=""> Address Details correction
                            <input type="hidden" id="addresscheckbox1" name="addresscheckbox1"  value="">
            </div>

                  <div class="col-xs-12 col-sm-3">
                      <label class="form_lable" >Mandal</label>
                      <%=ComboUtil.createStrComboBoxAuto("mandals",mandalList,selmandal,"text_box","disabled",true,true,"")%>                           
                  </div>     
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >Panchayat</label>
                          <div id="panchayatTDID">
                       <%=ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,selIssueType, "text_box","disabled",true,true,"")%>
                       </div>
                  </div>  
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >Village</label>
                         <div id="villageTDID">
                       <%=ComboUtil.createStrComboBoxAuto("villages",villageList,selvillage, "text_box","disabled",true,true,"")%>
                       </div> 
				  </div>                                    
    </div>
             <div class="row" style="margin:0px">

                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >Habitation</label>
                         <div id="habitationTDID">
                       <%=ComboUtil.createStrComboBoxAuto("habitation",habitationList,selhabitation, "text_box","disabled",true,true,"")%>
                       </div>
                  </div>
                   
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">House no.</label>
                       <input class="text_box"  type="text" name="houseno" id="houseno"  autocomplete="off" onChange="stripHTML(this);"   value='<%=basicData.get(26)%>'  maxlength="100" disabled>
                  </div>
                  
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">PIN CODE</label>
                       <input class="text_box" type="text" name="pincode" id="pincode"  autocomplete="off"    value='<%=basicData.get(27)%>'   onkeypress="return NumbersOnly(event);"   maxlength="6" disabled>
                  </div>
                   </div>
                           <div class="form-group row"> 
						                            <div class="input-group col-md-12" title="Description"> 
							                            <div class="input-group-addon" style="background-color: #2E9BD8; color:#fff; "> Contact No.</div>
		                                                   <input type="text" class="form-control " id="contid" name="contid" onkeypress="return NumbersOnly(event);" onBlur="validateContactdetailsms('contid','contidErrMsg')" autocomplete="off" maxlength="10"> 
															<span class="input-group-addon" id="contidErrMsg"></span>
	                                                 </div>
                                                   </div> 
                            <div  style="margin:0px; border-top:#ccc solid 5px; padding-top:5px;">
                            
                        <label class="form_lable">Remarks</label><font color='red'>*</font>
		                     <textarea class="text_box" rows="2" id="decription" name="decription"  style="width: 93%;height:100% " onBlur="this.value = SpaceReplace(this.value);" onChange="stripHTML(this);"  maxlength="200" ></textarea>
                            
                            
                            </div>
                            
                
                
                <div class="row" style="margin:0px">

                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >1.Proof Type</label>                      
                       <%=ComboUtil.createStrComboBoxAuto("proofidentity_1",proofTypeList, "", "text_box", "",true,true, "")%>                     
                  </div>
                   
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">Proof ID</label>
                       <input type="text" id="proofid_1" name="proofid_1" class="text_box" autocomplete=off  maxlength=30 onblur="validate('proofidentity_1','proofid_1')" >
                  </div>
                  
                  <div class="col-xs-12 col-sm-3">
                       <label class="glyphicon glyphicon-paperclip" style="font-weight: 700;">Proof Document</label>
                        <input type="file" class="text_box"   id="proofDoc_1" name="proofDoc_1">	
                  </div>
                   </div>
                   
                     <div class="row" style="margin:0px">

                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >2.Proof Type</label>                      
                       <%=ComboUtil.createStrComboBoxAuto("proofidentity_2",proofTypeList, "", "text_box", "",true,true, "")%>                     
                  </div>
                   
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">Proof ID</label>
                       <input type="text" id="proofid_2" name="proofid_2" class="text_box" autocomplete=off  maxlength=30 onblur="validate('proofidentity_2','proofid_2')" >
                  </div>
                  
                  <div class="col-xs-12 col-sm-3">
                       <label class="glyphicon glyphicon-paperclip" style="font-weight: 700;"> Proof Document</label>
                        <input type="file" class="text_box"   id="proofDoc_2" name="proofDoc_2">	
                  </div>
                   </div>
                   
                     <div class="row" style="margin:0px">

                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable" >3.Proof Type</label>                      
                       <%=ComboUtil.createStrComboBoxAuto("proofidentity_3",proofTypeList, "", "text_box", "",true,true, "")%>                     
                  </div>
                   
                  <div class="col-xs-12 col-sm-3">
                       <label class="form_lable">Proof ID</label>
                       <input type="text" id="proofid_3" name="proofid_3" class="text_box" autocomplete=off  maxlength=30 onblur="validate('proofidentity_3','proofid_3')" >
                  </div>
                  
                  <div class="col-xs-12 col-sm-3">
                       <label class="glyphicon glyphicon-paperclip" style="font-weight: 700;">Proof Document</label>
                        <input type="file" class="text_box"   id="proofDoc_3" name="proofDoc_3">	
                  </div>
                   </div>
                   
                   
                            
                            <div class="row col-md-12" style="margin:0px;">
                            <div class="col-md-4" style="text-align:right">
                            <button  type="submit" ID="raiseissueButID"  class="btn btn-success" style="margin-top:0px;padding: 2px 15px;">
                                Submit
                            </button>
                            </div>
                               <div class="col-md-4" style="text-align:center">
                            <button  type="submit" ID="cancelissueButID"  class="btn btn-success" style="background-color:red;border:red; margin-top:0px;padding: 2px 15px;">
                                Cancel
                            </button>
                            </div>
                            <%if(statusmsg.length()>0){ %>
	                             <div class="input-group col-md-4" style="float: left;">
					                            <button id="printbutid" class="btn btn-success btn-lg" type="button">
					                           		 <span class="glyphicon glyphicon-print"></span>Print 
					                           </button>
					            </div>
					            <%} %>
                            </div>
    

          

</div>
    

           
</div>


		        
<!-- 
				 <div id="Instructions" class="col-md-4 hide">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-check"></span> Instructions
							</h3>
						</div>
						<div class="panel-body"
							style="background-color: #eee; text-align: left; line-height: 25px; font-size: 14px; font-family: Times New Roman;">
							<ol>
								<li>All fields marked as (<font color='red'>*</font>) are
									mandatory
								</li>
								<li>Please avoid using special characters like <font
									color='blue' size="3"><b>',",<,></b></font> in description
									field
								</li>
								<li>Only .pdf file type is accepted for document</li>
							</ol>
						</div>
					</div>
				</div>  -->
           </div>
		</div>			    
</form>
<%@include file="/issuestrackingsys/printissuetrackingrequest.jsp" %>
</div>



<%if(flag.equals("")) { %>
<!-- Modal -->
<div class="modal fade" id="memberModal" tabindex="-1" role="dialog" aria-labelledby="memberModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="memberModalLabel">Instructions</h4>
      </div>
      <div class="modal-body">
      <div class="panel-body"
							style="background-color: #eee; text-align: left; line-height: 25px; font-size: 14px; font-family: Times New Roman;">
							<ol>
								<li>The fields under selected Checkbox are
									mandatory
								</li>
								<li>Please avoid using special characters like <font
									color='blue' size="3"><b>',",<,></b></font> in description
									field
								</li>
								<li>Only .pdf file type is accepted for document</li>
								<li>Form A scanned copy & Authorised approval letter are mandatory for this grievance</li>
								<li>pdf file size should be less than 5MB </li>
							</ol>
						</div>
      </div>
       
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<%} %>
<script type="text/javascript">
<!--
$('#memberModal').modal('show');
//-->
</script>
<%}catch(Exception e)
{
	e.printStackTrace();
	System.out.println("Exception in Multiplr Grievance JSP:"+e);
}%>
