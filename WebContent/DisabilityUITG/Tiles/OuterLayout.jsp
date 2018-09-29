<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<% 
try
{ 
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html>
<head >
  <!-- Meta Data -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
<meta http-equiv="X-UA-Compatible" content="chrome=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/> 
 <title>:: SADAREM :: </title>
 
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css"/>
    
   <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
				<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
				<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->
			
			

	    <script src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	    <script src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
        
</head>
<body>
		<div class="main_container">
			<div class="row">
				<tiles:insert attribute="header" ignore="true"/>
			</div>
			<div class="row">
				<tiles:insert attribute="body" ignore="true"/>
			</div>
			<div class="row">
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