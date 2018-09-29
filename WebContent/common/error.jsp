<%@ page import="com.tcs.sadarem.util.CommonUtility,java.util.ArrayList" isErrorPage="true"%> 
<!DOCTYPE html >
<html >
<head> 
<title>:: SADAREM :: Error Report</title>
  
 <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/sadarem_styles.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css" /> 
    
         
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
 <style type="text/css">
    .but
		{
     	background-color: #c8203a; font: normal 9pt;color: white;
    	padding:2px; font-weight: bold; 
    	}
</style>   
<script language="JavaScript">
window.history.forward(1);

function goToLoginPage()
{
	//alert("<%=request.getContextPath()%>");
  location.replace('<%=request.getContextPath()%>/');
}

function closeW() 
{ 
setTimeout("closeWindow()",100000); 
} 
function closeWindow() 
{ 
 window.open('','_parent',''); 
   window.close(); 
	if(navigator.appName=="Netscape") 
	{
		var a=netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite"); 
		alert("This will close the window"); 
		window.open('','_self'); 
		window.close();   
		}
}
try
{
	parent.jQuery.colorbox.close();
}
catch(e)
{
	//alert('hi');
}
</script>

</head>
<body>

<%
if(!CommonUtility.checkNullObj(session.getAttribute("loginid")).equals(""))
{
	%>
		<%@include file="/DisabilityUITG/Scripts/LoginHeader.jsp" %>
		<%@include file="/DisabilityUITG/Scripts/Menu.jsp" %>
	<%
}
else
{
	%>
		<%@include file="/DisabilityUITG/Scripts/Header.jsp" %>
	<%
}
%>

<br/>
<div class="container-fluid">
<div class="row">
	<div class="col-md-8 col-md-offset-2">  
		<div class="panel panel-danger">
	        <div class="panel-heading">
	             <h4 class="panel-title">
								<b>Information Details</b>
				 </h4>
	        </div> 
	            <div class="panel-body">									
									 					    
					    <%
					    if(request.getAttribute("ErrorCode")!=null && !((String)request.getAttribute("ErrorCode")).equals(""))
						{
						%>
							<font color="#c8203a" size="4" face="Verdana, Geneva, sans-serif"><%=request.getAttribute("ErrorCode")%></font>
						<%
						}
					    else
					    if(request.getAttribute("errorMessage")!= null && ! ("").equals(request.getAttribute("errorMessage")))
					    {
					    %>
					    <font color="#c8203a" size="4" face="Verdana, Geneva, sans-serif"><%=request.getAttribute("errorMessage")%></font>
					    <%
					    }
					   	else 
					    {
					    %>
					
								<%
								    boolean handled = false; // Set to true after handling the error
								    
								    // Get the PageContext
								    if(pageContext != null) 
								    {
								    
 								        // Get the ErrorData
								        ErrorData ed = null;
								        try 
								        {
								            ed = pageContext.getErrorData(); 
								        } catch(NullPointerException ne) 
								        {
								            // If the error page was accessed directly, a NullPointerException
								            // is thrown at (PageContext.java:514).
								            // Catch and ignore it... it effectively means we can't use the ErrorData
								        }
								
								        // Display error details for the user
								        if(ed != null) 
								        {
								    		String Msg ="Interal Error. Please Contact Administrator";
								    		if(ed.getStatusCode()==404)
								    		{
								    			Msg = "File or directory not found.";
								    		}
								    		else if(ed.getStatusCode()==500)
								    		{
								    			Msg ="Internal Server Error";
								    		}
								            // Output this part in HTML just for fun
								            %>
								            	<div class="form-line">
										  			<div class="form-group">
										  				<label for="exampleInputUsername">Code</label>
												    	<span class="form-control"><%=ed.getStatusCode()%></span>
											  		</div>
											  		<div class="form-group">
												    	<label for="exampleInputEmail">Description</label>
												    	<span class="form-control"><%=Msg%></span>
												  	</div>	
												  	<div class="form-group">
												    	<label for="telephone">URL</label>
												    	 <span class="form-control"><%=CommonUtility.htmlConvertion(ed.getRequestURI())%></span>
										  			</div>
										  		</div> 
								    <%
									 		// Error handled successfully, set a flag
								            handled = true;
								        }
								    }
								    
								    // Check if the error was handled
					
								    if(!handled) 
								    {
								    	if(!CommonUtility.checkNullObj(session.getAttribute("loginid")).equals(""))
								    	{
								    %>
								        <center><b>No information about this error was available.</b></center>
								    <%
								    	}
								    	else
								    	{
									%>
									        <center>
									         	<font color="#c8203a" size="4" face="Verdana, Geneva, sans-serif"><b>Please login again as your session expired.</b></font>
									         </center>
									 <%
								    		
								    	}
								    }
								%>
					    <%
					    }
					    %>  
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
			