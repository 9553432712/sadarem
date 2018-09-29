 <%@page import="java.util.ArrayList" %>
 <%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
     <head>
     <meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
     <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/commonstyle.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/DisabilityUITG/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css" />


<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/Common.js"></script>



<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/openissuetracking.js"></script>

</head>
 <%

 ArrayList openbasicData1 = new ArrayList();
 ArrayList historyData1 = new ArrayList();

String sadaremid=CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
String reqId=CommonUtility.checkNullObj(request.getAttribute("reqId"));
String reqtype=CommonUtility.checkNullObj(request.getAttribute("reqtype"));

historyData1=(ArrayList)request.getAttribute("historyData");
openbasicData1=(ArrayList)request.getAttribute("openbasicData");

request.setAttribute("sadaremid",((ArrayList)openbasicData1.get(0)).get(2));


%>
<style>
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
		.tdstyle
		{
			border: #234466 1px solid;
			padding:5px;
			align:left;
			valign:left;
			background: #e2ebf4;
		}
		.tdstyle1
		{
		border: none;
		padding:10px;
		align:left;
		background: #e2ebf4;
		readonly;
		}
		
		 .loader
		  {
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background: url('images/loading2.gif') 50% 50% no-repeat rgb(249,249,249);
		  }
		
		   .ui-collapsible-heading-collapsed > .ui-collapsible-heading-toggle{
		     /*   background:#337ab7; */
		   }
		   
		  
		   #cboxClose
		   {
                 background-color:orangered !important;
                 color:white !important;
           }
          .ui-overlay-a, .ui-page-theme-a, .ui-page-theme-a .ui-panel-wrapper      {
                 background-color:aliceblue !important;
          }
		
		 
		

		  
</style> 
<!--   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
  <script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>  
 <script>

</script>  

 <%try{ %>

<div id="sadaremDetailsDIVID"
	style="margin: 20px;"
	oncontextmenu="return false;" ondragstart="return false"
	onselectstart="return false">
    
    <div class="row ">
     <div class="col-sm-12 " style="text-align: center; color: blue;">
      <b style="font-size: 14px; font-style: normal; padding: 5px;">Grievance Status</b> 
     </div>
   </div>

   <div class="panel panel-primary"
		style="padding: 10px;">
		 <div class="panel-heading"> <b>Grievance Request Details of SADAREM ID ( <%=((ArrayList)openbasicData1.get(0)).get(2)%> ).</b></div>
		 <div id="collapseTwo" class="panel-collapse in">
		<div id="sectionA" class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<table >
                      <thead>
                      <tr>
                       <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Grievance ID</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Grievance Type</td>
                     <!--   <td  align="center" style="background-color:#337ab7;padding:5px; color:#fff;">SADAREM ID</td> -->
                       <td  align="center" style="background-color:#337ab7;padding:5px; color:#fff;">Person Name</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">District</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Mandal</td>
                       <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Status</td> 
                        <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Request Raised Date</td> 
                       <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Status Updated date</td> 
                    
                       
                      </tr>
                      </thead>
                       
                        <%
	                   if(openbasicData1!=null && openbasicData1.size()>0){
	                    ArrayList innerList = new ArrayList();
                        int loopCount;
                        for(loopCount=0;loopCount<openbasicData1.size();loopCount++)
	                    { 
    	                  innerList = (ArrayList)openbasicData1.get(loopCount);
    	                 %>
	                      <tr>
                           <td  style="border:#337ab7 1px solid; width:10%;  padding:5px;align="left">
                           
                           
                           <%=innerList.get(0) %>
                           </td> 
                           <td  style="border:#337ab7 1px solid; padding:5px;width:10%; " align="left"><%=innerList.get(1) %> </td>
                      <%--      <td  style="border:#337ab7 1px solid; width:15%;" align="left"><%=innerList.get(2)%></td> --%>
                           <td  style="border:#337ab7 1px solid; width:20%;" align="left"><%=innerList.get(3)%></td>
                           <td  style=" border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(4) %></td>
                           <td  style="border:#337ab7 1px solid;  width:10%; " align="left"><%=innerList.get(5) %></td>
                            <td  style="border:#337ab7 1px solid; width:10%; " align="left"><b> <%=innerList.get(6) %> </b></td>
                             <td  style="border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(7) %></td>
                              <td  style=" border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(8) %></td>
                              
                          </tr>     
                            <%}
                           }
                           else
                           {	
                           %>
                          <tr>
                           <td height="15" align="center" valign="middle" class="Row" colspan="18">No records to display.</td> 
	                      </tr>
	                     <%
	                       }
                         %>
                	</table>		
					
				</div>
			 	</div>
			</div>
			</div>
   <% if(historyData1!=null && historyData1.size()>0){ %>
           <div class="panel-heading"> <b>Work Flow Status</b></div>
		 <div id="collapseTwo" class="panel-collapse in">
		<div id="sectionA" class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<table  > 
                     <thead>
                     <tr>
                     <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">S.No.</td>
                     <td  align="center" style="background-color:#337ab7;color:#fff;padding:5px;">Action by</td>
                     <td  align="center" style="background-color:#337ab7;width:40%; color:#fff;padding:5px; ">Action</td>
                     <td  align="center" style="background-color:#337ab7;color:#fff;padding:5px;">Remarks</td>
                     <td  align="center" style="border-right:#337ab7 1px solid;width:35%;background-color:#337ab7;padding:5px;color:#fff;">Date</td>
                     </tr>
                    </thead>
                    <%
	               
	
                     ArrayList innerList = new ArrayList();
                     int loopCount;
                     for(loopCount=0;loopCount<historyData1.size();loopCount++)
	                  { 
    	                 innerList = (ArrayList)historyData1.get(loopCount);
    	                %>
	                   <tr>
                        <td  style="border:#337ab7 1px solid; width:10%"" align="center"><%=loopCount+1%></td> 
                        <td  style="border:#337ab7 1px solid; width:20%"" align="left"><%=innerList.get(4) %> </td>
                        <td  style="border:#337ab7 1px solid; width:30%""  align="left"><%=innerList.get(0)%></td>
                        <td  style="border:#337ab7 1px solid; width:20%""  align="left"><%=innerList.get(1) %></td>
                        <td  style="border:#337ab7 1px solid; width:35%""  align="left"><%=innerList.get(2) %></td>
                       </tr>
                       <%}
                     }
                     
                     %>
                   
                	</table>		
				</div>
			 	</div>
			</div>




			</div>


		</div>
 
</div>


 <!--<div class="main_container">
  <div class="loader"></div>
<table align="center" border="0">
<tr><th align='center'><font color='blue'> Work Flow Status </font></th></tr>
<tr>
<td style="text-align: center;vertical-align: top;height:420px; width:100%;" >
<form name="name_issue"  method="post" enctype="multipart/form-data">
			<table width="100%">
					 <tr> 
					            <td colspan="6" width="100%">
                           <div data-role="main" class="ui-content" >
                             
                       <div class="container">
  <table class="table table-hover table-bordered table-responsive " width="90%">
  
    <thead>
      <tr>
      <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Role Name</td>
      <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Person Name</td>
        <td  align="center" style="background-color:#337ab7;width:40%;padding:5px; color:#fff; ">Action</td>
        <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Remarks</td>
        <td  align="center" style="border-right:#337ab7 1px solid;width:35%;background-color:#337ab7;padding:5px;color:#fff;">Date</td>
        
        
       
      </tr>
    </thead>
    <%
	if(historyData1!=null && historyData1.size()>0){
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     
     for(loopCount=0;loopCount<historyData1.size();loopCount++)
	 { 
    	 innerList = (ArrayList)historyData1.get(loopCount);
    	  
	 %>
	
     <tr>
       <td  style="border:#337ab7 1px solid;" align="left"><%=innerList.get(5) %></td> 
       <td  style="border:#337ab7 1px solid;" align="left"><%=innerList.get(4) %> </td>
        <td  style="border:#337ab7 1px solid;width:40%;"  align="left"><%=innerList.get(0)%></td>
        <td  style="border:#337ab7 1px solid;"  align="left"><%=innerList.get(1) %></td>
        <td  style="border:#337ab7 1px solid;width:35%;"  align="left"><%=innerList.get(2) %></td>
    
      </tr>
     
      
      <%}
      }
     else
     {	
     %>
     <tr>
     <td height="15" align="center" valign="middle" class="Row" colspan="18">No history to display.</td> 
	</tr>
	 <%
	 }
     %>
 </table>
</div>
                    
                            </div></td><td></td></tr>
					</table>
</form>
</td>
 </tr>
</table>
</div>

-->

<%}catch(Exception e){e.printStackTrace();}  %>
