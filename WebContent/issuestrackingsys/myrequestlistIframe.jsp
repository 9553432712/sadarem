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
     </head>
 <%

 ArrayList historyData1 = new ArrayList();
 ArrayList historyData = new ArrayList();

String sadaremid=CommonUtility.checkNullObj(request.getAttribute("sadaremid"));
String reqId=CommonUtility.checkNullObj(request.getAttribute("reqId"));
String reqtype=CommonUtility.checkNullObj(request.getAttribute("reqtype"));

historyData=(ArrayList)request.getAttribute("historyData");


 
 
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

 <div class="main_container">
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
	if(historyData!=null && historyData.size()>0){
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     
     for(loopCount=0;loopCount<historyData.size();loopCount++)
	 { 
    	 innerList = (ArrayList)historyData.get(loopCount);
    	  
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

<%}catch(Exception e){e.printStackTrace();}  %>
