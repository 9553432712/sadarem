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
     <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
     
     

</head>
 <%

 ArrayList openbasicData1 = new ArrayList();
 ArrayList historyData1 = new ArrayList();

String sadaremid=CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
String reqId=CommonUtility.checkNullObj(request.getAttribute("reqId"));
String reqtype=CommonUtility.checkNullObj(request.getAttribute("reqtype"));

historyData1=(ArrayList)request.getAttribute("historyData");
openbasicData1=(ArrayList)request.getAttribute("openbasicData");
//System.out.println("sadaremiduuuuuuuuuuuuuu"+sadaremid);


%>
<style>
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
				iframe {
    z-index: 9999 !important;
    width: 100% !important;
    height: 100%;
    position:absolute;
}
   #cboxClose
		   {
                 background-color:orangered !important;
                 color:white !important;
           }
           
          .ui-overlay-a, .ui-page-theme-a, .ui-page-theme-a .ui-panel-wrapper      {
                 background-color:#000 !important;
          }
		
		  .ui-page {
		  background-color:#000 !important;
		  
		  } 
	
</style> 
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
      <b style="font-size: 14px; font-style: normal; padding: 5px;">Grievance Status</b><br />
     </div>
   </div>

   <div class="panel panel-primary"
		style="padding: 10px;">
		 <div class="panel-heading"> <b>Grievance Request Details of SADAREM ID  ( <%=((ArrayList)openbasicData1.get(0)).get(2)%> ).</b></div>
		 <div id="collapseTwo" class="panel-collapse in">
		<div id="sectionA" class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<table> 
                      <thead>
                      <tr>
                       <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">Grievance ID</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Grievance Type</td>
                       <!-- <td  align="center" style="background-color:#337ab7;padding:5px; color:#fff;">SADAREM ID</td> -->
                       <td  align="center" style="background-color:#337ab7;padding:5px; color:#fff;">Person Name</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">District</td>
                       <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Mandal</td>
                       <td  align="center" style="border-right:#337ab7 1px solid;background-color:#337ab7;padding:5px;color:#fff;">Request Raised Date</td> 
                       <td  align="center" style="border-right:#337ab7 1px solid;background-color:#337ab7;padding:5px;color:#fff;">Status</td> 
                       <td  align="center" style="border-right:#337ab7 1px solid;background-color:#337ab7;padding:5px;color:#fff;">Status Update Date</td> 
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
	                      <%if(historyData1!=null && historyData1.size()>0){ %>
		                           
		                            <td  style="border:#337ab7 1px solid; width:10%; padding:5px;">			                            
			                           		<b><%=innerList.get(0) %></b>			                         
		                           </td>
                           <%}else{ %>
		                             
		                            <td  style="border:#337ab7 1px solid; width:10%; padding:5px;">
			                            <a class="iframe" style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/openIsuueTrackingstatus.do?mode1=searchissueidstatus&issueid=<%=innerList.get(0) %>&randomid="+Math.random()">
			                           		<b><%=innerList.get(0) %></b>
			                           </a>
		                           </td>
                           <%} %>
                           <td  style="border:#337ab7 1px solid; width:15%;" align="left"><%=innerList.get(1) %> </td>
                        <%--    <td  style="border:#337ab7 1px solid; width:15%;" align="left"><%=innerList.get(2)%></td> --%>
                           <td  style="border:#337ab7 1px solid; width:20%;" align="left"><%=innerList.get(3)%></td>
                           <td  style="border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(4) %></td>
                           <td  style="border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(5) %></td>
                           <td  style="border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(7) %></td>
                           <td  style="border:#337ab7 1px solid; width:10%;" align="left"><b> <%=innerList.get(6) %> </b></td>
                           <td  style="border:#337ab7 1px solid; width:10%;" align="left"><%=innerList.get(8) %></td>
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
					  <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Content will be loaded here from "remote.php" file -->
            </div>
        </div>
    </div>
				</div>
			 	</div>
			</div>
			</div>
   <% 
   if(historyData1!=null && historyData1.size()>0)
   {
	   %>
           <div class="panel-heading"> <b>Work Flow Status</b></div>
		 <div id="collapseTwo" class="panel-collapse in">
		<div id="sectionA" class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<table>
                     <thead>
                     <tr>
                     <td  align="center" style="border-left:#337ab7 1px solid; background-color:#337ab7;padding:5px;color:#fff;">S.No.</td>
                     <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Action by</td>
                     <td  align="center" style="background-color:#337ab7;width:40%;padding:5px; color:#fff; ">Action</td>
                     <td  align="center" style="background-color:#337ab7;padding:5px;color:#fff;">Remarks</td>
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
                        <td  style="border:#337ab7 1px solid;width:10%" align="center"><%=loopCount+1%></td> 
                        <td  style="border:#337ab7 1px solid;width:20%" align="left"><%=innerList.get(4) %> </td>
                        <td  style="border:#337ab7 1px solid;width:30%;"  align="left"><%=innerList.get(0)%></td>
                        <td  style="border:#337ab7 1px solid;width:20%"  align="left"><%=innerList.get(1) %></td>
                        <td  style="border:#337ab7 1px solid;width:35%;"  align="left"><%=innerList.get(2) %></td>
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
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
					$(document).ready(function()
							{
						 
						//Examples of how to assign the Colorbox event to elements
						$(".iframe").colorbox(
								{
								iframe:true, width:"1200px", height:"550px" 
								
									
								});
						$(".prntbut").click(function(e){
							alert("Print Button");
						});
						$(".callbacks").colorbox({
							onOpen:function(){ alert('onOpen: colorbox is about to open'); },
							onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
							onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
							onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
							onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
						});
						$('#cboxClose').click(function (){alert(1);});
		
						$('.non-retina').colorbox({rel:'group5', transition:'none'});
						$('.retina').colorbox({rel:'group5', transition:'none', retinaImage:true, retinaUrl:true});
						
						//Example of preserving a JavaScript event for inline calls.
						$("#click").click(function(){ 
							$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
							return false;
						});
					}
					);
		
				</script>
<%}catch(Exception e){e.printStackTrace();}  %>
