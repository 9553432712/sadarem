<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,java.util.HashMap,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.PasswordEncriptDecrypt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: Basic Details</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/buttons.dataTables.min.css"/>
 
	 
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
	
			
<%

ArrayList districtList 	= (ArrayList)request.getAttribute("districtList");
ArrayList resultList 	= (ArrayList)request.getAttribute("resultList");
ArrayList originalDetailsList 	= (ArrayList)request.getAttribute("originalDetailsList");
HashMap formStatusList	= (HashMap)request.getAttribute("formStatusList");

String message			= CommonUtility.checkNullObj(request.getAttribute("message"));			
String encimgPath="";
ArrayList historyList 	= (ArrayList)request.getAttribute("historyList");
String PartBStatusRmks =  CommonUtility.checkNullObj(request.getAttribute("PartBStatusRmks"));
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
 

</script>
</head> 
<%try{ %>
<body>

<div class="main_container" oncontextmenu="return false;"  >                     

    
		<!-- Screen Lock Started Here -->
			<div id="processlayer">
				<font color="blue" size="2">Processing please wait...</font><br/>
				<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
			</div>
			<div id="blocklayer">
			</div>
		<!-- Screen Lock Ended Here -->  
    
	<%--  <div class="row " style="background-image:url(/sadarem/images/head_bg.png); background-repeat:repeat-x; margin-bottom:15px;background-color:#7DABD2;" class="img-responsive">
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
      </div> --%>
    
    		<div class="panel-group" id="accordion">
				    <div class="panel panel-primary" id="searchformforid">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseOne"  href="#collapseOne" style="cursor: pointer;">
				             <h4 class="panel-title">
											Search by SADAREM ID or Aadhaar ID
							 </h4>
				        </div>
				        <div id="collapseOne" class="panel-collapse ">
				            <div class="panel-body">
				            <form class="form-inline" id="searchformforid" name="searchformforid" method="post" autocomplete="off">
				            <input type="hidden" id="mode" name="mode" value="searchbyids">
					            <div class="input-group">
							      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">SADAREM ID</div>
							      <input type="text" class="form-control " id="sadaremid" name="sadaremid"  onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="17">
							      <span class="input-group-addon" id="sadaremidErrMsg"></span>
							    </div>
								  <div class="input-group" style="padding: 10px;">
							      	<div  class="input-group-addon" style="background-color: #eee; color:red;font-weight:bold;">OR</div>
								  </div>
					            <div class="input-group">
							      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Aadhaar ID</div>
							      <input type="text" class="form-control" id="aadhaarid" name="aadhaarid" onkeypress="return NumbersOnly(event);" onBlur="this.value = SpaceReplace(this.value);" autocomplete="off" maxlength="12">
							      <span class="input-group-addon" id="aadhaaridErrMsg"></span>
							    </div>
								<button type="button" id="searchformforidSearchbut" class="btn btn-success"><b>Search</b></button>
							 	<%							
								if((message==null || message.equals("") ) && resultList!=null && resultList.size()==1)
								{
								%>
									<button type="button" class="btn btn-primary" id="printbutid"> Print </button>
								<%
								}
								%>
							</form>
				            
				            </div>
				        </div>
				    </div>
				   

			</div>   
	</div>
	<%
	if(!message.equals(""))
	{
		%>
		<div class="alert alert-block alert-danger">
		    <a class="close" data-dismiss="alert">×</a>
					  <b><%=message%></b>
		</div>
       	   		
		<%
	}
	 


if(resultList!=null && resultList.size()==1)
{
	resultList = (ArrayList)resultList.get(0);
	//System.out.println("resultListfghfg"+resultList);

%>

<div id="sadaremDetailsDIVID" style="border-bottom:#ccc solid 1px;margin: 20px;"  oncontextmenu="return false;" >
			    <ul class="nav nav-tabs" id="myTab">
			        <li class="active"><a href="#sectionA"><b>Person Basic Details</b></a></li>
			        <li><a href="#sectionB"><b>Person Disability Details</b></a></li>
			        <li><a href="#sectionC"><b>Uploaded Certificate Details</b></a></li>
			        <li><a href="#sectionD"><b>History Of Assessment</b></a></li>
			        <li><a href="#sectionE"><b>Form Status</b></a></li>
			    </ul>
			    <div class="tab-content" style="padding:10px; border-left:#ccc solid 1px;  border-right:#ccc solid 1px;">
			        <div id="sectionA" class="tab-pane fade in active">
			          <h3>Part A Information</h3>
			          <div class="row">
			          <div class="col-md-5">
			          			<div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(1) %></span> 
					                            </div>
					                          </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">District</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(2) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Mandal</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(3) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Village</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(4) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Habitation</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(5) %></span> 
					                            </div>
					                        </div>
					                        <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Part-A Created Date</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(29) %></span> 
					                            </div>
					                        </div>
					           </div>	
					  </div>
					  <div class="col-md-5">
					           
					 <div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name (Surname Name)</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(6).toString().trim() %>&nbsp;<%=resultList.get(7).toString().trim() %> </span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Birth (DD/MM/YYYY)</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(8).toString().trim() %> </span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Gender</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(9).toString().trim() %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Relationship Details</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(10).toString().trim() %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Status</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(11).toString().trim() %> </span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Contact No.</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(12).toString().trim() %>&nbsp;</span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">ID Proof Details</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(13).toString().trim() %>  <%=resultList.get(14).toString().trim() %>&nbsp;</span> 
					                            </div>
					                        </div>
					           </div>
					        </div>
					        <div class="col-md-2">
					        	<div style="height: 160px; width:160px;background-color: #37ADB6; text-align: center;vertical-align: middle; padding-top: 5px;">
					        		<img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=resultList.get(1).toString().trim()%>" width="152" height="152" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
					        	</div>
					        	
					        </div>
					     </div>
			        </div>
			        <div id="sectionB" class="tab-pane fade">
			              <h3>Part B Information</h3>
			           <div class="row">
			           <%
			           if(resultList.get(0).toString().equalsIgnoreCase("view") && resultList.get(15).toString().length()>0)
			           {
			           %>
			           		<div class="col-md-5">
			          			<div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Disabiligy</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(15) %>&nbsp;</span> 
					                            </div>
					                          </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Disability Percent</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(16) %>%&nbsp;</span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Status</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(17) %>&nbsp;</span> 
					                            </div>
					                        </div>
					           </div>	
					         </div>
					         <div class="col-md-5">  
							 			<div class="panel-body">
							                         <div class="form-group row" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Type</div>
														  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(18).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                         <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Issued on</div>
														  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(19).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                         <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Expires on</div>
														  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(20).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							           </div>
					           </div>
					           <%
			           			}
					           else
					           {
					        	   %>
					        	   <div class="panel-body  col-md-12">
					        	   		 <div class="alert alert-danger" style="height:30px;padding-top:5px;margin-top:2px;">
											  <b>Part B Form not filled.</b>
											  
										</div>
										<br>
											  <%if(PartBStatusRmks.length()>0){ %>
											  <h4 style="color: green;"><%=PartBStatusRmks %></h4>
											  <%} %>
											  <%if(!resultList.get(11).toString().trim().equals("Live"))
											  {%>
												  				<h5 style="color: green;">Before filling Part-B, raise an online grievance in Issue Tracking to change the person status. After all approvals fill Part-B</h5>								  
											  <%}%>	
											  
					        	   </div>
					        	   <%
					           }
					           %>
					     </div>
			           
			        </div>
			           <div id="sectionC" class="tab-pane fade">
			              <h3>Certificate and ID Card Uploaded Information</h3>
			           <div class="row">
			          
			           		<div class="col-md-5">
			          			<div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Is Certificate uploaded </div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(21) %>&nbsp;</span> 
					                            </div>
					                          </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificated Uploaded Date</div>
												  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(22) %>&nbsp;</span> 
					                            </div>
					                        </div>
					                          <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Download Uploaded Certificate</div>
												  <span class="input-group-addon" style="text-align:left;">
												  
												  
											<% if(resultList.get(26).toString().trim().length()>2){
						                	  //System.out.println((String)resultList.get(26).toString().trim());
						                	  encimgPath = PasswordEncriptDecrypt.encrypt((String)resultList.get(26).toString().trim());
						                      encimgPath = encimgPath.replace("+", "%2B"); %>
						                     <a  class="" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadCertificateIdCard&path=<%=encimgPath%>">
						                 <font color="green"><b><% out.write("("+(String)resultList.get(28).toString().trim()+")");%></b></font><img height="25" width="25" src="<%=request.getContextPath()%>/images/pdfIcon.png" border="0" align="right" title="Export PDF"/></a>
						                  
												
												 <%}else{
													 out.write("--");}%>
												 
												  </span> 
					                            </div>
					                        </div>
					                        

					           </div>	
					         </div>
					         <div class="col-md-5">  
							 			<div class="panel-body">
							                         <div class="form-group row" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Is ID Card Uploaded</div>
														  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(23).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                         <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">ID Card Uploaded Date</div>
														  <span class="input-group-addon" style="text-align:left;"><%=resultList.get(24).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                          <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Download Uploaded ID Card</div>
														  <span class="input-group-addon" style="text-align:left;">
														  
														  
														  <% if(resultList.get(25).toString().trim().length()>2){
						                	              // System.out.println((String)resultList.get(25).toString().trim());
						                	               encimgPath = PasswordEncriptDecrypt.encrypt((String)resultList.get(25).toString().trim());
						                                   encimgPath = encimgPath.replace("+", "%2B"); %>
						                                  <a  class="" style="color:#fff;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadCertificateIdCard&path=<%=encimgPath%>">
						                                  <font color="green"><b><% out.write("("+(String)resultList.get(27).toString().trim()+")");%></b></font><img height="25" width="25" src="<%=request.getContextPath()%>/images/pdfIcon.png" border="0" align="right" title="Export PDF"/></a>
						                  
												
												  <%}else{
													 out.write("--");
													 }%>
												 
														  </span> 
							                            </div>
							                        </div>
							                    
							           </div>
					           </div>
					         
					     </div>
			           
			        </div>
			        <div id="sectionD" class="tab-pane fade">
			        			 <h3>History of SADAREMID Assessed.</h3>
			          			 <div class="row">
				        			 <div class="panel-body col-md-12">
				        			 
				        			 <%-- 	<%if(historyList!=null && historyList.size()==1)
			           					 {%>
			           					 	No History Found
			           					 <%}%> --%>
				        			 	<%if(historyList==null || historyList.size()==0)
			           					 {%>
			           					 	No History Found
			           					 <%}%>
						        	   	<%if(historyList!=null && historyList.size()>0)
			           					 {%>
			           					  <table class="table table-striped table-bordered" style="width:85%">
			           					 	<thead>
			           					 		<tr>
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				S.No.
			           					 			</td>
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				Cause Of Disability
			           					 			</td>			           					 			
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				Assessment Date
			           					 			</td>
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				Total Disability
			           					 			</td>
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				Login ID
			           					 			</td>
			           					 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
			           					 				Updated Date
			           					 			</td>
			           					 			
			           					 		</tr>	
			           					 	</thead>
			           					 	<tbody>
							            	<%ArrayList tempList = new ArrayList();
							            	int count=1;
							            	for(int looper=historyList.size()-1;looper>=0;looper--)
							            	{
							            		/* if(count==4)
							            			break; */
							            		tempList = (ArrayList)historyList.get(looper);
							            		
							            	%>	
							            	
							            		<tr>
							            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=count%></td>
							            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(4) %></td>
							            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(0) %></td>
							            			<td style="border:#337ab7 1px solid;text-align:right;padding-left:2px;"><%=tempList.get(2) %></td>
							            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(1) %></td>
							            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(3) %></td>
							            		</tr>						            					            		
							            		
							            	<%count++;
							            	}%>
							            	</tbody>
							            	</table> 
							           	 <%}%>
				            		
											
						        	   </div>			        			
			       				 </div>
			    </div>
			        <div id="sectionE" class="tab-pane fade">
			        			 <h3>SADAREMID Form Data Status.</h3>
			          			 <div class="row">
			          			 <%
			          			 if(
			          					 formStatusList!=null && formStatusList.size()>0 && 
			          					 formStatusList.containsKey("disability_status") &&
			          			 		 formStatusList.containsKey("percentage_status") &&
			          			 		 formStatusList.containsKey("rejection_status") &&
			          			 		 formStatusList.containsKey("record_status") 
			          				)
			          			 {
 
			          			 %>
			          			   <div class="col-md-6">
				        			 <div class="panel-body">
				        			 
					                         <div class="form-group row" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Disablitiy Status</div>
												  <div class="input-group-addon" style="text-align:left;">
												  		<%
												  		if(CommonUtility.checkNullObj(formStatusList.get("disability_status")).equalsIgnoreCase("Y"))
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-ok-circle" style="color: green;"></span>
												  		<%
												  		}
												  		else
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-remove-circle" style="color: red;"></span>
												  		<%	
												  		}
												  		%>
												  </div> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Percentage Status</div>
												  <div class="input-group-addon" style="text-align:left;">
												  		<%
												  		if(CommonUtility.checkNullObj(formStatusList.get("percentage_status")).equalsIgnoreCase("Y"))
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-ok-circle" style="color: green;"></span>
												  		<%
												  		}
												  		else
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-remove-circle" style="color: red;"></span>
												  		<%	
												  		}
												  		%>
												  </div>  
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Cause of Disability Status</div>
												  <div class="input-group-addon" style="text-align:left;">
												  		<%
												  		if(CommonUtility.checkNullObj(formStatusList.get("cause_disp_status")).equalsIgnoreCase("Y"))
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-ok-circle" style="color: green;"></span>
												  		<%
												  		}
												  		else
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-remove-circle" style="color: red;"></span>
												  		<%	
												  		}
												  		%>
												  </div>  
					                            </div>
					                        </div> 
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Form Rejection Status</div>
												  <div class="input-group-addon" style="text-align:left;">
												  		<%
												  		if(CommonUtility.checkNullObj(formStatusList.get("rejection_status")).equalsIgnoreCase("Y"))
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-ok-circle" style="color: green;"></span>
												  		<%
												  		}
												  		else
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-remove-circle" style="color: red;"></span>
												  		<%	
												  		}
												  		%>
												  </div>  
					                            </div>
					                        </div> 
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Final Form Status</div>
												  <div class="input-group-addon" style="text-align:left;">
												  		<%
												  		if(CommonUtility.checkNullObj(formStatusList.get("record_status")).equalsIgnoreCase("Y"))
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-ok-circle" style="color: green;"></span>
												  		<%
												  		}
												  		else
												  		{
												  		%>
												  			<span class="glyphicon glyphicon-remove-circle" style="color: red;"></span>
												  		<%	
												  		}
												  		%>
												  </div>  
					                            </div>
					                        </div> 
						        	   </div>
						        	</div>
						        <%
			          			 }
			          			 else
			          			 {
			          				 %>
			          				 	No Details Available
			          				 <%
			          			 }
						        %>
						      </div>
			    </div>
			    </div>
	</div>
	
	<div id="printdivid" style="display: none;">
		<table width="95%" class="table table-striped table-bordered">
			<tr>
				<td width="50%" align="center">
						<table width="98%"  border="1" >
							<tr>
								<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"> Basic Details</td>
							</tr>
							<tr>
								<td>SADAREM ID </td>
								<td><%=resultList.get(1)%></td>
							</tr>
							<tr>
							 <td>District</td>
							 <td><%=resultList.get(2)%></td>
					        </tr>
                         	<tr>
                              <td>Mandal</td>
							  <td><%=resultList.get(3)%></td>
                            </tr>
					        <tr>
					           <td>Village</td>
							   <td><%=resultList.get(4)%></td>
					        </tr>
					        <tr>
					           <td>Habitation</td>
							   <td><%=resultList.get(5)%></td>
					        </tr>
					        <tr>
								<td>Person Name (Surname Name)</td>
								<td><%=resultList.get(6).toString().trim() %>&nbsp;<%=resultList.get(7).toString().trim() %> </td>
							</tr>
							<tr> 
								<td>Date of Birth (DD/MM/YYYY)</td>
								<td><%=resultList.get(8).toString().trim() %> </td>
							</tr>
							<tr> 
								<td>Gender</td>
								<td><%=resultList.get(9).toString().trim() %></td>
							</tr> 
							<tr>
								<td>Relationship Details</td>
								<td><%=resultList.get(10).toString().trim() %></td>
							</tr> 
							<tr>
								<td>Person Status</td>
								<td><%=resultList.get(11).toString().trim() %> </td>
							</tr> 
							<tr>
								<td>Contact No.</td>
								<td><%=resultList.get(12).toString().trim() %>&nbsp;</td>
								
							</tr>
							<tr>
								<td>Part-A Created Date</td>
								<td><%=resultList.get(29).toString().trim() %>&nbsp;</td>
								
							</tr>
							<tr>
								<td>ID Proof Details</td>
								<td><%=resultList.get(13).toString().trim() %>  <%=resultList.get(14).toString().trim() %>&nbsp;</td>
							</tr> 
						</table>
				</td>
				<td width="50%" valign="top">
				      <%
				      if(resultList.get(0).toString().equalsIgnoreCase("view") && resultList.get(15).toString().length()>0)
			           {
			           %>
						<table  width="98%"  border="1" >
							<tr>
								<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"> Disablitiy Details</td>
							</tr>
							<tr>
								<td>Type of Disabiligy</td>
								<td><%=resultList.get(15) %>&nbsp;</td>
							</tr>
							<tr>
								<td>Disability Percent</td>
								<td><%=resultList.get(16) %>%&nbsp;</td>
							</tr> 
							<tr>
								<td>Certificate Status</td>
								<td><%=resultList.get(17) %>&nbsp;</td>
							</tr>
							<tr>
								<td>Certificate Type</td>
								<td><%=resultList.get(18).toString().trim() %>&nbsp;</td>
							</tr>
							<tr>
								<td>Certificate Issued on</td>
								<td><%=resultList.get(19).toString().trim() %>&nbsp;</td>
							</tr>  
							<tr>
								<td>Certificate Expires on</td>
								<td><%=resultList.get(20).toString().trim() %>&nbsp;</td>
							</tr> 
							<tr>
								<td>Is Certificate uploaded </td>
								<td><%=resultList.get(21) %>&nbsp;</td>
							</tr>
							<tr>
								<td>Certificated Uploaded Date</td>
								<td><%=resultList.get(22) %>&nbsp;</td>
							</tr>
							<tr>
								<td>Is ID Card Uploaded</td>
								<td><%=resultList.get(23).toString().trim() %>&nbsp;</td>
							</tr>
							<tr>
								<td>ID Card  Uploaded Date</td>
								<td><%=resultList.get(24) %>&nbsp;</td>
							</tr>
						</table>
					
					<%
			           }
			           else
			           {
			        	   %>
			        	   	Part-B details not filled.
			        	   <%
			           }
					%>
				</td>
			</tr>
			<tr>
				<td colspan="2" width="100%">
				<table width="100%" align="center" class="table table-striped table-bordered">
					<tr>
						<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">History of SADAREMID Assessed</td>
					</tr>
					<tr>
						<td align="center" width="100%"> 
									<%
									if(historyList!=null && historyList.size()>0)
										{
								   %>
								          <table class="table table-striped table-bordered" border="1" width="100%"> 
								 		<tr>
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				S.No.
								 			</td>
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				Cause Of Disability
								 			</td>			           					 			
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				Assessment Date
								 			</td>
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				Total Disability
								 			</td>
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				Login ID
								 			</td>
								 			<td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">
								 				Updated Date
								 			</td>
								 			
								 		</tr> 
						            	<%
						            	ArrayList tempList = new ArrayList();
						            	int count=1;
						            	for(int looper=historyList.size()-1;looper>=0;looper--)
						            	{
						            		/* if(count==4)
						            			break; */
						            		tempList = (ArrayList)historyList.get(looper);
						            		
						            	%>	
						            	
						            		<tr>
						            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=count%></td>
						            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(4) %></td>
						            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(0) %></td>
						            			<td style="border:#337ab7 1px solid;text-align:right;padding-left:2px;"><%=tempList.get(2) %></td>
						            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(1) %></td>
						            			<td style="border:#337ab7 1px solid;text-align:left;padding-left:2px;"><%=tempList.get(3) %></td>
						            		</tr>						            					            		
						            		
						            	<%count++;
						            	}%> 
						            	</table> 
								  <%
								  }
								  else
								  {
								   %>
								    		No History Avaiable
						          <%}%>
							</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
	
	</div>
	
<%
}
else if(resultList!=null && resultList.size()>0)
{
%>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="98%" style="align:center;">
<thead>
			            <tr>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">S.No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">SADAREM ID</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 10%; vertical-align:middle;" rowspan="2">District</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Mandal</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Village</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Habitation</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Person Name</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Date of Birth</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Gender</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Relation Name</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Person Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Contact No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Proof Details</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" colspan="6">Disability Details</th>
			            </tr>
			            <tr>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Disability</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Percent</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Certificate Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 10%; vertical-align:middle;">Certificate Type</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Issue Date</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Expire Date</th>
			            </tr>
			        </thead>
			        <tbody>
						 <%
				            if(resultList!=null && resultList.size()>0)
				            {
				            	ArrayList tempList = new ArrayList();
				            	String status ="";
				            	for(int looper=0;looper<resultList.size();looper++)
				            	{
				            		
				            		tempList = (ArrayList)resultList.get(looper);
				            		
				            		status = tempList.get(0).toString().trim();
				            	
				            		%>
											 <tr>
								                <td align="center"><%=looper+1 %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(1) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(2) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(3) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(4) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(5) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(6) %>&nbsp;<%=tempList.get(7) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(8) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(9) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(10) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(11) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(12) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(13) %>&nbsp;<%=tempList.get(14) %>&nbsp;</td>
								                <%
								                if(status.equalsIgnoreCase("view"))
								                {
								                	%>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(15) %></td>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(16) %></td>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(17) %></td>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(18) %></td>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(19) %></td>
										                <td style="text-align:left; vertical-align:middle;"><%=tempList.get(20) %></td>
								                	<%
								                }
								                else
								                {
								                	%>
									                <td align="center" colspan="6" style="background-color: #D8191E; color:#fff;text-align:center;">Part B details not filled</td>
							                	<%
								                }
								                %>
								            </tr>
				            		<%
				            	}
				            }
				            else
				            {
			            		%>
										 <tr>
								                <td colspan="21"> No records available</td>
								            </tr>
			            		<%
				            }
           					%>
												
					</tbody>

</table>


	<%
		} 
		else if(originalDetailsList!=null && originalDetailsList.size()>0)
		{

%>
					<font color="blue"><b>Note :</b> Below SADAREM ID are with incomplete details.</font> (Please check district,mandal,village,habitation,gender,relationship type,Date of Birth,Disability status) 
					<table id="orginal_data_tab" class="table table-striped table-bordered" cellspacing="0" width="98%" style="align:center;">
					<thead>
			            <tr>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">S.No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">SADAREM ID</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 10%; vertical-align:middle;">District</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Mandal</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Village</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Habitation</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Surname</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Name</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Date of Birth</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Gender</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Relationship Type</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Relation Name</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Contact No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Proof Details</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Form Filled Date</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Form Reference No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Person Live Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Form Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Disablitiy Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Disablitiy Percentage Status</th> 
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Couse of Disablitiy Status</th> 
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Rejection Status</th> 
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Final Status</th> 
			            </tr> 
			        </thead>
			        <tbody>
						 <%
				            if(originalDetailsList!=null && originalDetailsList.size()>0)
				            {
				            	HashMap tempList = new HashMap();
				            	String status ="";
				            	for(int looper=0;looper<originalDetailsList.size();looper++)
				            	{
				            		
				            		tempList = (HashMap)originalDetailsList.get(looper);
				            		
				            	 
				            	
				            		%>
											 <tr>
								                <td style="text-align:center; vertical-align:middle;"><%=looper+1 %></td>
								                <td style="text-align:center; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("person_code")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("district_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("mandal_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("village_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("habitation_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("surname")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("first_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("date_of_birth")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("gender_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("relation_type")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("relation_name")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("person_contactno")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("proofdoc_type")) %> : <%=CommonUtility.checkNullObj(tempList.get("proof_id")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("form_fill_date")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("reference_form_number")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("person_live_status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("view_edit_mode")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("disability_status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("percentage_status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("cause_disp_status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("rejection_status")) %></td>
								                <td style="text-align:left; vertical-align:middle;"><%=CommonUtility.checkNullObj(tempList.get("record_status")) %></td>
								            </tr>
				            		<%
				            	}
				            }
				            else
				            {
			            		%>
										 <tr>
								                <td colspan="24"> No records available</td>
								            </tr>
			            		<%
				            }
           					%>
												
					</tbody>
				</table> 
	<%
				}
	 
	%> 

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/buttons.print.min.js"></script>


<script type="text/javascript">
   	
   $(document).ready(function()
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

	   		  	$('#example').DataTable( {
	   		  	scrollY:        "300px",
		        scrollX:        true,
		        scrollCollapse: true,
		        paging:         false,  
	   		        dom: 'Bfrtip',
	   		        buttons: [
	   		            'print'
	   		        ]
	   		    } ); 


	   		  	$('#').DataTable(  ); 
	   			
	   		 <% 
			    if(originalDetailsList!=null && originalDetailsList.size()>0)
			    {
			    %>
			    
			    var table = $('#orginal_data_tab').DataTable( 
			    {
			        scrollY:        "300px",
			        scrollX:        true,
			        scrollCollapse: true,
			        paging:         false,  
			    } ); 
				<%
			    }  
			    %>
	   		  	

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
	   
	   
	    
	    	
	    			$("#searchformforidSearchbut").click(function(event)
	    			{
	    		    		
	    				if($("#sadaremid").val()=="" && $("#aadhaarid").val()=="")
	    					{
	    						alert("Please provide SADAREM ID or Aadhaar ID");
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
	    						document.searchformforid.action="<%=request.getContextPath()%>/loadsadarembasicdtls.do?randomid="+Math.random();
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
  </script>							



</body>
<%}catch(Exception e){
System.out.println(e);}%>
</html>


