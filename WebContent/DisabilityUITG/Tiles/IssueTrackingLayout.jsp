<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<!DOCTYPE html>
<html lang="en">
<%try{ %>
      
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     
    
 <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/style.css" />
 <link rel="stylesheet" href="<%=request.getContextPath() %>/DisabilityUITG/css/sadarem_styles.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
 <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/commonstyle.css" />
    
        
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/teluguname.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/FakeLoader.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
 <style>
 	.jqueryslidemenu ul li a:link,.jqueryslidemenu ul li a:visited {
	color:#000000;
	z-index:1000;
	height:35px!important;
}
 .loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url('<%=request.getContextPath()%>/images/loading2.gif') 50% 50% no-repeat rgb(249,249,249);
}
 </style>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>   

 </head>
     <body>
 <div class="main_container">
    	 <div class="loader"></div>
     
     	<div class="row">
     	  		<tiles:insert attribute="header" ignore="true"/>
     	</div>
     	<div class="row">
     	  		<tiles:insert attribute="menu"  ignore="true"/>
     	</div>
     	<div class="row">
     	  		 <tiles:insert attribute="basicdtls" ignore="true"/>
     	</div>
     	<div class="row">
     	  		 <tiles:insert attribute="body" ignore="true"/>
     	</div>
     	<div class="row">
     	  		 <tiles:insert attribute="footer" ignore="true" />
     	</div>
     	
     </div>     
 </body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>