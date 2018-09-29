<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: R8.1 Report</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
   
      	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
  
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
			<!-- 	newEducationWiseDetails -->
 <%try{ %>	
			
<%

ArrayList districtList 	= new ArrayList();
ArrayList resultList = new ArrayList();
ArrayList educationList = new ArrayList();


ArrayList mandalList  = new ArrayList();
ArrayList villageList  = new ArrayList();

String readonly="";
 districtList = (ArrayList)request.getAttribute("districtList"); 


resultList = (ArrayList)request.getAttribute("resultList");

String fromdate1=CommonUtility.checkNullObj((String)request.getAttribute("fromdate"));
String todate1=CommonUtility.checkNullObj((String)request.getAttribute("todate"));
String district1=CommonUtility.checkNullObj(request.getAttribute("district"));
String mandal1=CommonUtility.checkNullObj(request.getAttribute("mandal"));
String village1=CommonUtility.checkNullObj(request.getAttribute("village"));
String heading=CommonUtility.checkNullObj((String)request.getAttribute("heading"));
String areatype1=CommonUtility.checkNullObj(request.getAttribute("areatype1"));
String TRIGPSel =CommonUtility.checkNullObj(request.getAttribute("TRIGPSel"));
mandalList = (ArrayList)request.getAttribute("mandalList");
villageList = (ArrayList)request.getAttribute("villageList");



int recordCount = 0;

%>

<style>




</style>

<style>
.firstline{
background-color:white;
}
.secondline{
background-color:#e2ebf4;
} 

.btn
{
 width:auto!important; 
 padding : 5px !important;
 cursor:pointer!important;
}
#resultdata_filter {float:right;}
.dataTables_length {float:left;}


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
	//$('#campwiseform').hide();
	
	<%
    String ua = request.getHeader( "User-Agent" );
    boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
    %>
	  $('#resultdata').DataTable( {
		 

			    scrollY: "200px",				    
		        scrollX:   true,		  
		          iDisplayLength: 25 , 
		        scrollCollapse: true,
		        fixedColumns:   {
		            leftColumns: 2
		        },
		       
 <% if( isMSIE ){ %> 
		        "bSort": false, 
		        "paging":   false,
		        "ordering": false,
		        "info":     false,
		        "bFilter": false

 <%}%>

	    } );
	

	  
	  //to get todays date
	     var d = new Date();
	     var month = d.getMonth()+1;
	     var day = d.getDate();
	     var output = (day<10 ? '0' : '') + day+ '/' +(month<10 ? '0' : '') + month + '/' +d.getFullYear();
	    //end 
	     
$('#fromdate1').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
				maxDate:output,
		});
		
$('#todate1').datetimepicker(
		{
			dayOfWeekStart : 1,
			lang:'en',
			formatTime:'H:i',
			formatDate:'d/m/Y',
			format:'d/m/Y',
			theme:'',
			step:05,
			timepicker:false,
			maxDate:output,
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

    $('#areatype1').change(function()
   		 {	
   			  postRequest("<%=request.getContextPath()%>/CommonReportsAjax.do?action=loaddistricts&areatype="+ $('#areatype1').val()+"&randomid="+Math.random(),"districtSPANID1");
   			
   		  });	
	 


    
    $('body').on('change','#district1',function()
    		{	
    	postRequest("<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadMandalsbyareawise&mandalidname=mandal&distId="+ $('#district1').val()+"&areatype="+ $('#areatype1').val()+"&randomid="+Math.random(),"mandalSPANID");
    		});
    
    $('body').on('change','#mandal',function()
    		{	
    			var url ="<%=request.getContextPath()%>/CommonReportsAjax.do?action=loadVillagesareawise&villageidname=village&distId="+ $('#district1').val()+"&mandalId="+$('#mandal').val()+"&areatype="+ $('#areatype1').val()+"&randomid="+Math.random();
    			postRequest(url,"villageSPANID");
    		});
    
    



	
    	$("#districtwiseformSubmitbut").click(function( event )
    			{
    			
    		
    			
    				if($("#fromdate1").val()=="")
    					{
    						alert("Please select From Date");
    						$("#fromdate1").focus();
							
							 event.preventDefault();
							event.stopPropagation();
							return false;
    					}
    					else if($("#todate1").val()=="")
    					{
    						alert("Please select To Date");
    						$("#todate1").focus();
							
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
    						document.districtwiseform.action="<%=request.getContextPath()%>/newCertiUploadReport.do?randomid="+Math.random();
    						document.districtwiseform.submit();
    						
    					}
    			});
    
    	
    	
    	 
    	
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
  <!--   <div align="center"><font color="#337ab7" size="4px"><b>R1.3: PWD's Educational wise - Details</b></font></div> -->
    		<div class="panel-group" id="accordion">
				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;text-align:center;">
				             <h4 class="panel-title">
				          			<b>R8.1: Certificates Upload Report</b>
				      		</h4>
				        </div>
				        <div id="collapseTwo" class="panel-collapse ">
				            <div class="panel-body">
				            		<form class="form-inline" role="form" id="districtwiseform" name="districtwiseform" method="post">
					            		<input type="hidden" id="mode" name="mode" value="getData">
					            		
					            		 
					            			  <div class="input-group " id="fromdatediv" >
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date<font color='red'>*</font></div>
											      <input type="text" class="form-control " id="fromdate1" name="fromdate1"  style="width:80px;"  autocomplete="off" value=<%=fromdate1%>>				
							               </div>
							                <div class="input-group " id="todatediv" >
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date<font color='red'>*</font></div>
											      <input type="text" class="form-control " id="todate1" name="todate1"   style="width:80px;"  autocomplete="off" value=<%=todate1%>>				
							               </div>
							               <div class="input-group " id="Trigpdiv1" >
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Project</div>										  
				                             <select class="form-control mycomboStyle " id='TRIGPSel' name='TRIGPSel'>
				                             <option value='-1' <%if(TRIGPSel.equalsIgnoreCase("-1")){ %>selected<%} %>>All</option>
				                              <option value='Y' <%if(TRIGPSel.equalsIgnoreCase("Y")){ %>selected<%} %>>TRIGP</option>				                              
				                             </select> 
				                               </div> 
							                <div class="input-group " id="areatypediv1" >
											      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Area Type</div>										  
				                             <select class="form-control mycomboStyle " id='areatype1' name='areatype1'>
				                             <option value='-1' <%if(areatype1.equalsIgnoreCase("-1")){ %>selected<%} %>>All</option>
				                              <option value='U' <%if(areatype1.equalsIgnoreCase("U")){ %>selected<%} %>>Urban</option>
				                               <option value='R' <%if(areatype1.equalsIgnoreCase("R")){ %>selected<%} %>>Rural</option>
				                             </select> 
				                                </div> 		
											
										 	<div class="input-group" >
										      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">District</div>
										    <span id="districtSPANID1"> <%=ComboUtil.createStrComboBoxAuto("district1", districtList,district1,"form-control mycomboStyle","",true,false,"")%></span>	
										 </div>
					            	
										  <div class="input-group " id="mandaldiv">
										     <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Mandal</div>
										      <span id="mandalSPANID"><%=ComboUtil.createStrComboBoxAuto("mandal", mandalList,mandal1,"form-control mycomboStyle","",true,false,"")%></span>
										  </div>
										   <div class="input-group" id="villagediv">											    
										   <div class="input-group-addon" style="background-color: #eee; color:#000; font-weight:bold;">Village</div>
										    <span id="villageSPANID"><%=ComboUtil.createStrComboBoxAuto("village",villageList,village1,"form-control mycomboStyle","",true,false,"")%></span>
										  </div> 
										 <button type="button" id="districtwiseformSubmitbut" class="btn btn-success"><b>Submit</b></button>
										
									</form>
				            </div>
				        </div>
				    </div>

			</div>   
		</div> 
	
 <% if(resultList!=null && resultList.size()>0)
 { %>
 <div <%if(fromdate1.length()==10 && todate1.length()==10 && !district1.equals("-1") && !mandal1.equals("-1") && !village1.equals("-1")){ %>style="width:95%;margin:0px auto;" <%}else{ %> style="width:65%;margin:0px auto;" <% }%>>

 <div style=" text-align: center;margin-left: auto;margin-right: auto; ">
 <%=heading%>
 <a href="<%=request.getContextPath()%>/newCertiUploadReport.xls?mode=excelNew&fromdate=<%=fromdate1%>&todate=<%=todate1%>&district=<%=district1%>&mandal=<%=mandal1%>&village=<%=village1%>&heading=<%=heading%>&areatype=<%=areatype1%>&TRIGPSel=<%=TRIGPSel%> ">
<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
 </a>  </div> 


  <table id="resultdata" class="table table-striped table-bordered" >
 <%  if(fromdate1.length()==10 && todate1.length()==10 && !district1.equals("-1") && !mandal1.equals("-1") && !village1.equals("-1")  ) 
	 {%>
	 <thead>
      <tr>
       <td   style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Village</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Habitation</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Person Name</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Date Of Birth</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Gender</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Relation Details</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Person Live Status</td>
         <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Contact</td>
          <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Qualification</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Proof Type</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Proof</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Disability</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Disability Percentage(%)</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Certificate Status</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Certificate Type</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Certificate Issue Date</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Cetificate Expiry Date</td>
       <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Cetificate Upload Status</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Cetificate Uploaded Date</td>
         <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">ID Card Upload Status</td>
          <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">ID Card Uploaded Date</td>  
   
       </tr>
      
    </thead>
	 
	 
	 <% } else{ %>
    <thead>
      <tr>
       <td   style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>

        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">



	<%
  				 	if(district1.equals("-1") && mandal1.equals("-1"))
  				 	{
  				 		out.write("District");
  				 	}
  				 	else if(!district1.equals("-1") && mandal1.equals("-1"))
  				 	{
  				 		out.write("Mandal");
  				 	}
  				 	else if(!district1.equals("-1") && !mandal1.equals("-1"))
  				 	{
  				 		out.write("Village");
  				 	}
  				 	
  				 	%>
</td>

        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Total Assessed</td> 
         <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Eligible</td> 
           <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Not Eligible</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Uploaded  <br> certificates</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Pending <br> Certificates</td>
         <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Uploaded  <br> ID Card's</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Pending <br> ID Card's</td>  

    
       </tr>
   
    </thead>
    <%}%>
    <tbody>
    <%
	
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     String style="";
       if(fromdate1.length()==10 && todate1.length()==10 && !district1.equals("-1") && !mandal1.equals("-1") && !village1.equals("-1")   ) 
	 {
    	    for(loopCount=0;loopCount<resultList.size();loopCount++)
    		 { 
    	    	 innerList = (ArrayList)resultList.get(loopCount);
    	    	  
    		 
    		 if(loopCount%2==0)
    		 {
    			 style="firstline";
    		 }else
    		 {
    			 style="secondline";
    		 }
    		 %>
    	     <tr>
    	        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
    	       <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(0) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;padding-left:2px;"><%=innerList.get(1) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(2) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(3) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(4)%></td>        
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(5) %><%=innerList.get(6) %></td>
    	  
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(7) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(8) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(9) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(10) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(11) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(12) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(13) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(14) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(15) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(16) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(17) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(18) %></td>
    	         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(19) %></td>
    	          <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(20) %></td>
    	           <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(21) %></td>
    	            <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(22) %></td>
    	             <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(23) %></td>
    	              <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(24) %></td>
    	       
    	         
    	         
    	      </tr>
    	      <%
    	      }
    			   
	 }
       else
 {
     for(loopCount=0;loopCount<resultList.size();loopCount++)
	 { 
    	 innerList = (ArrayList)resultList.get(loopCount);
    	  
	 
	 if(loopCount%2==0)
	 {
		 style="firstline";
	 }else
	 {
		 style="secondline";
	 }
	 %>
     <tr>
     
    
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
       
         <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(0).toString().replaceAll("ZZZ_","") %></td>
    
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%=innerList.get(1) %></td>
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(2) %></td>
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(3) %></td>
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(4) %></td>
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(5) %></td>
          <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(6) %></td>
           <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(7) %></td>
         
       <%--   <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">
         <%int diff=Integer.parseInt(innerList.get(2).toString()) - Integer.parseInt(innerList.get(3).toString());
         out.println(diff);
        %></td>   --%>      

      
       </tr>
      <%
      }
		%>
		 </tbody>
    </table>
		</div>
      <%
      }
       }

 else if(fromdate1.length()>0 )
     {	
     %>
      <table  width="90%" class="table table-hover table-bordered table-responsive ">
   
     <tr>
     <td height="15" align="center" valign="middle" class="Row" colspan="18">
			     No records to display. 
				 
	 	</td> 
	</tr>
	</table>
	 <% 
	 }else {  
     %>
       <!--  <table  width="90%" class="table table-hover table-bordered table-responsive ">
   
     <tr>
     <td height="15" align="center" valign="middle" class="Row" colspan="18">
			     
				 
	 	</td> 
	</tr>
	</table> -->
    <%
      }%>


</body>
<% 
 } catch(Exception e)
 {
	 e.printStackTrace();
	 } 
%>
</html>