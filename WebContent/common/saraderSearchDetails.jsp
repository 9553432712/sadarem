<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>:: SADAREM :: Quick Search</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/> 
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
	
			
<%

ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
ArrayList resultList 	= (ArrayList)request.getAttribute("resultList");
String message			= CommonUtility.checkNullObj(request.getAttribute("message"));			


int recordCount = 0;
if(resultList!=null)
{
	recordCount = resultList.size();
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
				text-align: center;
				position: fixed;
				margin-right: -150px;
				margin-top: -75px;
				right: 50%;
				top: 50%;
				z-index: 99999;
				width:300px;
				display: none;
			}
			
			#blocklayer
			{
				display: none;
				position: fixed;
				left: 0;
				top: 0;
				bottom:0;
				right:0;
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



$(document).ready(function()
		{ 
	
	
		    $("#myTab a").click(function(e)
		    		{
		    	e.preventDefault();
		    	$(this).tab('show');
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
    			$("form").each(function( index )
						{
			    				try
			    				{
			    					 $('#district').change();
				    				$(this).clearForm();
			    				}
			    				catch(e)
			    				{
			    					//alert(e);
			    				}
						});
    		});

   
  
    
    $("#sadaremid").on('keypress blur change paste cut',function (e) 
    		{
    		        
		        	var pattern = /^\d{17}$/;
		            		        	
	        		if(pattern.test($(this).val())==false  && $(this).val().length>0)
        			{
	        			 $("#sadaremidErrMsg").addClass("errmsg");
	  		           	 $("#sadaremidErrMsg").html("Not Valid").show();
        			}
	        		else
        			{
	        			 $("#sadaremidErrMsg").removeClass("errmsg");
	  		           	 $("#sadaremidErrMsg").html("").show();
        			}
     	  });
    
    $("#aadhaarid").on('keypress blur change paste cut',function (e)
    		{
    	
				    	var pattern = /^\d{12}$/;
				    	
						if((pattern.test($(this).val())==false || fun_validateAadhaarID($(this).val()) ==false) && $(this).val().length>0)
						{
							 $("#aadhaaridErrMsg").addClass("errmsg");
					           	 $("#aadhaaridErrMsg").html("Not Valid").show();
						}
						else
						{
							 $("#aadhaaridErrMsg").removeClass("errmsg");
					           	 $("#aadhaaridErrMsg").html("").show();
						}			
    	
     	  });
   
    
    $('#district').change(function()
    		{	
    	
    			$("#mandalSPANID").html("<%=ComboUtil.createStrComboBoxAuto("mandal", new ArrayList(),"","form-control mycomboStyle","",true,true,"")%>");    	
    			$("#villageSPANID").html("<%=ComboUtil.createStrComboBoxAuto("village", new ArrayList(),"","form-control mycomboStyle","",true,true,"")%>");
    			postRequest("<%=request.getContextPath()%>/ajax.do?action=loadmandalopenrep&distId="+ $('#district').val()+"&randomid="+Math.random(),"mandalSPANID");
    			
    		});
    
    	$('body').on('change','#mandal',function()
    		{	
    			$("#villageSPANID").html("<%=ComboUtil.createStrComboBoxAuto("village", new ArrayList(),"","form-control mycomboStyle","",true,true,"")%>");
    			var url ="<%=request.getContextPath()%>/ajax.do?action=loadvillageopenrep&distId="+ $('#district').val()+"&mandalId="+$('#mandal').val()+"&randomid="+Math.random();
    			//alert(url);
    			postRequest(url,"villageSPANID");
    		});
    
    	
    	$("#searchformforidSearchbut").click(function(event)
    			{
    		    		
    				if($("#sadaremid").val()=="" && $("#aadhaarid").val()=="")
    					{
    						alert("Please provide SADAREM ID");
    						$("#sadaremid").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#sadaremid").val()!="" && ($("#sadaremid").val()).length<17)
    					{
    						 $("#sadaremidErrMsg").addClass("errmsg");
    				         $("#sadaremidErrMsg").html("Please provide valid SADAREM ID");
    			
    						$("#sadaremid").focus();
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#aadhaarid").val()!="" && fun_validateAadhaarID($("#aadhaarid").val())==false)
    					{
    						 $("#sadaremidErrMsg").removeClass("errmsg");
    						 $("#sadaremidErrMsg").html("");
    						 $("#aadhaaridErrMsg").addClass("errmsg");
    				         $("#aadhaaridErrMsg").html("Please provide valid Aadhaar ID");
    						
    						$("#aadhaarid").val("");
    						$("#aadhaarid").focus();
							
							 event.preventDefault();
							 event.stopPropagation();
							 return false;
    					}
    					else if($("#sadaremid").val().length!=0 || $("#aadhaarid").val().length!=0 )
    					{
    						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
    						
    						document.searchformforid.target="_self";
    						document.searchformforid.action="<%=request.getContextPath()%>/loadsearchsadaremdtls.do?randomid="+Math.random();
    						document.searchformforid.submit();
    						
    					}
    			});
    	
    	
    	$("#searchbynameformSearchbut").click(function( event )
    			{
    			
    		
    			$("#mode").val('searchbynames');
    				if($("#district").val()=="-1")
    					{
    						alert("Please select District");
    						$("#district").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#mandal").val()=="-1")
    					{
    						alert("Please select Mandal");
    						$("#mandal").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#village").val()=="-1")
    					{
    						alert("Please select Village");
    						$("#village").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#personname").val()=="")
    					{
    						alert("Please select Person Name");
    						$("#personname").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else
    					{
    						/*Screen Locking Started */
	    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
	    	      			/*Screen Locking Ended */
    						
    						//document.searchbynameform.target="_self";
    						document.searchbynameform.action="<%=request.getContextPath()%>/loadsearchsadaremdtls.do?randomid="+Math.random();
    						document.searchbynameform.submit();
    						
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
		 $( "select" ).each( function( index, element )
		   {
		    	$(this).removeClass('select-optionItem').addClass('form-control mycomboStyle');		// Removing the existing style of combo and adding new form-control class css. 
		    	
		    	$("#village").removeAttr("onchange");												// Removing the onchange action of village combo 
		    	
		    	$('select option:contains("All")').text('-Select-');								// Modifing the option text of combo from All to -Select- 
		    });
	}
}



</script>
</head> 
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
    
    		<div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseOne"  href="" style="cursor: pointer;">
				             <h4 class="panel-title">
											Search by SADAREM ID 
							 </h4>
				        </div>
				        <div id="collapseOne" class="">
				            <div class="panel-body">
				            <form class="form-inline" id="searchformforid" name="searchformforid" method="post" autocomplete="off">
				            <input type="hidden" id="mode" name="mode" value="searchbyids">
					            <div class="input-group">
							      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">SADAREM ID</div>
							      <input type="text" class="form-control " id="sadaremid" name="sadaremid"  onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="17">
							      <span class="input-group-addon" id="sadaremidErrMsg"></span>
							    </div>
								 <!--   <div class="input-group" style="padding: 10px;">
							      	<div  class="input-group-addon" style="background-color: #eee; color:red;font-weight:bold;">OR</div>
								  </div>
					           <div class="input-group">
							      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Aadhaar ID</div>
							      <input type="text" class="form-control" id="aadhaarid" name="aadhaarid" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="12">
							      <span class="input-group-addon" id="aadhaaridErrMsg"></span>
							    </div> -->
							     <input type="hidden" class="form-control" id="aadhaarid" name="aadhaarid" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="12">
								<button type="button" id="searchformforidSearchbut" class="btn btn-success"><b>Search</b></button>
							</form>
				            
				            </div>
				        </div>
				    </div>
				    <div class="panel  panel-primary" id="searchbynameform">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;">
				             <h4 class="panel-title">
				          			Search by Person's District,Mandal,Village, Name and Relation Name.
				      		</h4>
				        </div>
				        <div id="collapseTwo" class="">
				            <div class="panel-body">
				            		<form class="form-inline" role="form" id="searchbynameform" name="searchbynameform" method="post">
					            		<input type="hidden" id="mode" name="mode" value="searchbynames">
					            		<div class="input-group" >
										      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">District</div>
										     <%=ComboUtil.createStrComboBoxAuto("district", districtList,"","form-control mycomboStyle","",true,true,"")%>	
										 </div>
										  <div class="input-group">
										     <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Mandal</div>
										      <span id="mandalSPANID"><%=ComboUtil.createStrComboBoxAuto("mandal", new ArrayList(),"","form-control mycomboStyle","",true,true,"")%></span>
										  </div>
										  <div class="input-group">											    
										   <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Village</div>
										    <span id="villageSPANID"><%=ComboUtil.createStrComboBoxAuto("village", new ArrayList(),"","form-control mycomboStyle","",true,true,"")%></span>
										  </div>
										  <div class="input-group">											    
										    <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Person Name</div>
										   <input type="text" id="personname" name="personname" class="form-control" size="20"  onBlur="this.value = SpaceReplace(this.value);" autocomplete="off"/>
										  </div>
										  <div class="input-group" >											    
										   <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Relation Name</div> 
										   <input type="text" id="relationname" name="relationname" class="form-control" size="20"  onBlur="this.value = SpaceReplace(this.value);" autocomplete="off"/>
										  </div>
										 <button type="button" id="searchbynameformSearchbut" class="btn btn-success"><b>Search</b></button>
										
									</form>
				            </div>
				        </div>
				    </div>

			</div>   
	<div class="row">
	<div class="col-md-11"> 
				<%
				if(!message.equals(""))
				{
					%>
					<div class="alert alert-block alert-danger">
					    <a class="close" data-dismiss="alert">×</a>
								  <strong><%=message%></strong>
					</div>
			       	   		
					<%
				}
				else if(recordCount==1)
				{
					resultList = (ArrayList)resultList.get(0);
				%>
						<%@include file="/common/sadaremSearchBasicDetails.jsp" %>
				<%
				}
				else if(recordCount>1)
				{
					%>
						<%@include file="/common/sadaremSearchByNameDetails.jsp" %>
					<%
				}	
				%> 
		</div>
	</div>
</div>
</body>
</html>