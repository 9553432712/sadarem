<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: Open Issue Tracking</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/style.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/sadarem_styles.css" />
 

    
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
	
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
	
			
<%
String message			= CommonUtility.checkNullObj(request.getAttribute("message"));
ArrayList resultList 	= new ArrayList();
ArrayList resultList1 	= new ArrayList();


resultList 	= (ArrayList)request.getAttribute("historyData");
resultList1 	= (ArrayList)request.getAttribute("openbasicData");

int recordCount  = 0;
int recordCount1 = 0;

if(resultList!=null)
{
	recordCount  = resultList.size();
}
if(resultList1!=null)
{
recordCount1 = resultList1.size();
}
%>

<style>

.btn
{
 width:auto!important; 
 padding : 5px !important;
 cursor:pointer!important;
}


/* Process Layer Started */
     	
     	#processlayer
			{
				width: 300px;
				height: 50px;
				background:#ECF1EF;
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
			
			#blocklayer
			{
				position: absolute;
				left: 0;
				top: 0;
				background: #ECF1EF;
			}
     	
     /* 	 Process Layer Ended  */
     	

.panel-heading a:after 
{
    font-family:'Glyphicons Halflings';
    content:"\e114";
    float: right;
    color: grey;
}
.panel-heading a.collapsed:after {
    content:"\e080";
}

	.bs-example{
		margin: 20px;
	}
	
.errmsg
{
color: red;
}

.mycomboStyle
{
	width : 125px !important;
}

</style>
<script type="text/javascript">
         
</script>
<script type="text/javascript">


/*
2)********************* Disabling Function Key F5 & F6 *********************

The below code disables both F5 & F6 Function Keys works in IE & Mozilla also
*/

var fn = function (e)
{

    if (!e)
        var e = window.event;

    var keycode = e.keyCode;
    if (e.which)
        keycode = e.which;

    var src = e.srcElement;
    if (e.target)
        src = e.target;    

    // 116 = F5
   if (112 == keycode || 113 == keycode || 114 == keycode || 115 == keycode || 116 == keycode || 117 == keycode || 118 == keycode ||119 == keycode || 120 == keycode ||121 == keycode || 122 == keycode ||123 == keycode)
    {
        // Firefox and other non IE browsers
        if (e.preventDefault)
        {
            e.preventDefault();
            e.stopPropagation();
        }
        // Internet Explorer
        else if (e.keyCode)
        {
            e.keyCode = 0;
            e.returnValue = false;
            e.cancelBubble = true;
        }

        return false;
    }
}

// Assign function to onkeydown event
document.onkeydown = fn;

/*
8)******************************* Disable Right Click on a browser *******************
*/

	PopUpURL    = "The right click options are disabled for this window";

	isIE=document.all;
	isNN=!document.all&&document.getElementById;
	isN4=document.layers;

	if (isIE||isNN)
	{
	 document.oncontextmenu=checkV;
	}
	else
	{
	 document.captureEvents(Event.MOUSEDOWN || Event.MOUSEUP);
	 document.onmousedown=checkV;
	} 

	function checkV(e)
	{
		if (isN4)
		 {
		if (e.which==2||e.which==3)
			{
			dPUW=alert(PopUpURL);
			return false;
			}
		}
		else
		{
		dPUW=alert(PopUpURL);
		return false;
		}
	}
	 function validateemail(){
         var address = document.forms[0].email.value;
         if(address!=""){
             var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

             if(reg.test(address) == false) {
                 alert('Invalid Email Address');
                 document.forms[0].email.value="";
                 document.forms[0].email.focus();
                 return false;
             }
         }
     }
	 function checkSpcialChar(event){
         if(!((event.keyCode >= 65) && (event.keyCode <= 90) || (event.keyCode >= 97) && (event.keyCode <= 122) || (event.keyCode >= 48) && (event.keyCode <= 57))){
            event.returnValue = false;
            return;
         }
         event.returnValue = true;
      }

	 function loadvillages(){

			
	     postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadvillagerep&manId="+ $('#mandals').val()+"&randomid="+Math.random()+"&districtId="+$('#distId').val(),"villageTDID");
	     loadhabitation();
	   
	}

	 function loadhabitation(){

			
	     postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadhabitationrep&manId="+ $('#mandals').val()+"&villageId="+ $('#villages').val()+"&randomid="+Math.random()+"&districtId="+$('#distId').val(),"habitationTDID");
	   
	}

$(document).ready(function()
		{ 
	
		    $("#myTab a").click(function(e)
		    		{
		    	e.preventDefault();
		    	$(this).tab('show');
		    });
		    
		    $('#mandals').change(function()
					{	
				    
				       postRequest("<%=request.getContextPath()%>/ajax.do?action=OpenIssueloadpanchayatopenrep&manId="+ $('#mandals').val()+"&randomid="+Math.random()+"&districtId="+$('#distId').val(),"panchayatTDID");
				       loadvillages();
					});

    
    $(".panel-heading").click(function(e)
    		{
    			//alert($(this).attr("href"));
    			
    			var selID = $(this).attr("href");
    			
    			
    			$(".panel-collapse").each(function( index )
    					{
    				
    				if(selID!=("#"+$( this ).attr("id")))
    					{
    						$( this ).removeClass( "in" );
    						
    						//alert($( this ).attr("id"));
    						//$("#formId")[0].reset()
    						

    					}    				
    			});
    		});

    	$("#searchformforidSearchbut").click(function(event)
    			{
    		    		
    				if($("#issueid").val()=="")
    					{
    						alert("Please provide Issue ID ");
    						$("#issueid").focus();
							
							 //event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#issueid").val()!="" && ($("#issueid").val()).length<15)
    					{
    						 $("#sadaremidErrMsg").addClass("errmsg");
    				         $("#sadaremidErrMsg").html("Please provide Grievance ID");
    			
    						$("#issueid").focus();
							 //event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#issueid").val().length!=0 )
    					{
    						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
    						$("#mode").val('searchissueid');
    						document.searchformforid.target="_self";
    						document.searchformforid.action="<%=request.getContextPath()%>/openIsuueTrackingstatus.do?randomid="+Math.random();
    						document.searchformforid.submit();
    						
    					}
    			});
    	
    	$.fn.clearForm = function() 
    	{
    		  return this.each(function() 
    				  {
    				    var type = this.type, tag = this.tagName.toLowerCase();
    				    if (tag == 'form')
    				      return $(':input',this).clearForm();
    				    if (type == 'text' || type == 'password' || tag == 'textarea')
    				      this.value = '';
    				    else if (type == 'checkbox' || type == 'radio')
    				      this.checked = false;
    				    else if (tag == 'select')
    				      this.selectedIndex = 0;
    				  });
    		};
    		
});

function OpenIssuepostRequest(strURL,textID,myFunction) 
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
        	var errorCode="";
        	var returnMsg= xmlHttp.getResponseHeader('returnMsg');
        	if(returnMsg=='Y'){
        		$("#otpmsg").addClass("hide");
            	$("#submitBut").removeClass("hide");
            	$("#contid").attr("disabled","disabled");
            	$("#altcontid").attr("disabled","disabled");
            	$("#emailid").attr("disabled","disabled");
            	$("#issueType").attr("disabled","disabled");
            	if($("#issueType").val()=='S002'){
            		$("#fname").attr("readonly","readonly");
            		 makereadonly();
            	}
            	else if($("#issueType").val()=='S003'){
            		$("#relationname").attr("readonly","readonly");
            		$("#relationType").attr("readonly","readonly");
            		 makereadonly();
            	}
            	else if($("#issueType").val()=='S004'){
            		$("#mandals").attr("readonly","readonly");
            		$("#panchayats").attr("readonly","readonly");
            		$("#villages").attr("readonly","readonly");
            		$("#habitation").attr("readonly","readonly");
            		$("#houseno").attr("readonly","readonly");
            		$("#pincode").attr("readonly","readonly");
            		 makereadonly();
            	}
            	else if($("#issueType").val()=='S015'){
    	        	makereadonly();
           		}else if($("#issueType").val()=='S016'){
           			$("#dob").attr("readonly","readonly");
          		    makereadonly();
           		}
            	updatepage("<span style='color:green'><b>OTP has been sent to your Mobile Number.</b></span>",textID);
        	}else{
        		updatepage("<span style='color:red'><b>OTP generation failed. Please try again later.</b></span>",textID);
        		$("#otpmsg").removeClass("hide");
            	$("#submitBut").addClass("hide");
        	}
    	}
    };
	xmlHttp.send(strURL);
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
		/*  $( "select" ).each( function( index, element )
		   {
		    	$(this).removeClass('select-optionItem').addClass('form-control');		// Removing the existing style of combo and adding new form-control class css. 
		    	
		    	$("#village").removeAttr("onchange");												// Removing the onchange action of village combo 
		    	
		    	$('select option:contains("All")').text('-Select-');								// Modifing the option text of combo from All to -Select- 
		    }); */
	}
}

</script>
</head> 
 <%try{ %>
<body>

<div class="main_container" oncontextmenu="return false;" ondragstart="return false" onselectstart="return false" >                     

    
		<!-- Screen Lock Started Here -->
			<div id="processlayer">
				<font color="blue" size="2">Processing please wait...</font><br/>
				<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
			</div>
			<div id="blocklayer">
			</div>
		<!-- Screen Lock Ended Here -->  
    
	 <div class="row " style="background-image:url(/sadarem/images/head_bg.png); background-repeat:repeat-x; margin-bottom:15px;background-color:#7DABD2;" class="img-responsive">
       	<div class="col-sm-1" style="text-align:center;">
       		<img src="<%=request.getContextPath()%>/DisabilityUITG/images/TG_GOV_LOGO.png"/>
       	</div>
            <div class="col-sm-9 " style="text-align:center;color:#fff;">
              <b style="font-size:14px; font-style: normal;">Software for Assessment of Disabled for Access Rehabilitation and Empowerment</b><br/>
                 <span style="font-size:12px; font-style: normal;">
                     <font size="2"><b>Society for Elimination of Rural Poverty</b></font><br/>
                     Department of Rural Development,Govt.of Telangana
                 </span>
           </div>
            <div class="col-sm-1" style="text-align:center;">
            	<img src="<%=request.getContextPath()%>/DisabilityUITG/images/SerpLogo.png"/>
            </div>
      </div>
      <div class="row ">
            <div class="col-sm-12 " style="text-align:center;color:blue;">
              <b style="font-size:14px; font-style: normal; padding:5px;">Grievance Tracking System Status</b><br/>
           </div>
      </div>
      <div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseOne"  href="#collapseOne" style="cursor: pointer;">
				             <h4 class="panel-title">
											Search by Grievance ID
							 </h4>
				        </div>
				        <div id="collapseOne" class="panel-collapse">
				            <div class="panel-body">
				            <form class="form-inline" id="searchformforid" name="searchformforid" method="post" autocomplete="off">
				            
					            <div class="input-group">
							      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Grievance ID</div>
							      <input type="text" class="form-control " id="issueid" name="issueid"  onkeypress="return checkSpcialChar(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="16">
							      <span class="input-group-addon" id="sadaremidErrMsg"></span>
							    </div>
								<button type="button" id="searchformforidSearchbut" class="btn btn-success"><b>Search</b></button>
								<input type="hidden" id="mode" name="mode" value="">
							</form>
				            
				            </div>
				        </div>
                        
				    </div>
                    
			</div>   
	</div>
	<div class="col-sm-12" style="text-align:center;">     
             <div style="color:green;" align="center"><b> ${statusMsg}</b></div><br> 
    </div>

<%
	if(!message.equals(""))
	{
	%>
       	   <div class="panel-body  col-md-12">
       	   		 <div class="alert alert-danger" style="height:30px;padding-top:5px;margin-top:2px; width:100%; text-align:center;">
					  <strong style="color:#a94442;"><%=message%></strong>
				</div>
       	   </div>
           
		<%
	}
	else
	{
		if(resultList!=null && resultList1 != null)
		{
		  if(resultList.size()>0 && resultList1.size()>0)
		  {	  
			resultList = (ArrayList)resultList.get(0); 
			resultList1 = (ArrayList)resultList1.get(0);
		
	%>
			<%@include file="/issuestrackingsys/openmyrequestlist.jsp" %>
			
	<%}else
	{%>
		 <div class="panel-body  col-md-12">
       	   		 <div class="alert alert-danger" style="height:30px;padding-top:5px;margin-top:2px; width:100%; text-align:center;">
					  <strong style="color:#a94442;">Please provide valid Grievance ID</strong>
				</div>
       	   </div>
	<%}
	}}
	%>


	
</body>
<%}catch(Exception e){e.printStackTrace();}  %>

</html>