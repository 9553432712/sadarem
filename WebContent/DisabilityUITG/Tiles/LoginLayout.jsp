<%@ page language="java" %>
<% 
try
{
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!DOCTYPE html >
<html>
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=7"/>
<meta http-equiv="X-UA-Compatible" content="IE=100"/> 
<meta http-equiv="Cache-Control" content="no-store"/>
<!-- <meta http-equiv="X-UA-Compatible" content="chrome=1"/>  -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 

 <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/sadarem_styles.css"/> 
    
        <%
                    HttpSession sessionForCheck = request.getSession(false);
                
        			String  reLoginURL=request.getContextPath()+"/login.do?mode=sessionvalidator";
                    if (session.getAttribute("services") == null) {
                        response.sendRedirect(reLoginURL);
                        return;
                    }
        %>
        
        <%   
		  response.setHeader("Cache-Control", "no-cache");   
		  response.setHeader("Pragma", "no-cache");   
		  response.setDateHeader("max-age", 0);   
		  response.setDateHeader("Expires", 0);   
		  response.setHeader("Cache-control","private");
		  response.setHeader("Cache-control","no-cache");
		  response.setHeader("Cache-control","no-store");
		  response.setHeader("Cache-control","pre-check=0");
		  response.setHeader("Cache-control","post-check=0");
		  response.setHeader("Cache-control","must-revalidate");
		  response.setHeader("Pragma","no-cache");
	%>  
       
<style>

					@media print 
					{ 
						.printhide{display:none;margin:0px;} 
						.printshow{display:block;margin:0px;} 
						body {-webkit-print-color-adjust: exact;}
					} 
					
					@media screen 
					{ 
						.printhide{display:block;margin:0px;} 
						.printhow{display:block;margin:0px; } 
					} 
</style>
 </head>
  <body>
 <div class="main-container" style="background-color: #fffcf6;">
        <div class="row printhide">
        			<tiles:insert attribute="header" ignore="true"/>
        </div>
        <div class="row printhide">
        			<tiles:insert attribute="menu"  ignore="true"/>
        </div>
        <div class="row printhow" style="min-height: 380px;">
        			 <tiles:insert attribute="body" ignore="true"/>
        </div>
        <div class="row printhide" style="bottom: 0px;">
        			<tiles:insert attribute="footer" ignore="true" />
        </div>
  </div>
 </body>
</html>
<%

}
catch(Exception e)
{ 
     e.printStackTrace(new java.io.PrintWriter(out)); 
 }
 %>