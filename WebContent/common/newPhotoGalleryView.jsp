
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		<title>:: SADAREM ::Photo Gallery</title>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
   
	<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/commonstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/colorbox.css" />
	<script src="<%=request.getContextPath()%>/scripts/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
    <style>
		body
		{
			margin:0px 0px 0px 0px;
			padding:0px 0px 0px 0px;
		}
	</style>
<script>
			$(document).ready(function()
			{
				//Examples of how to assign the Colorbox event to elements
				$(".group4").colorbox({rel:'group4', slideshow:true, transition:"elastic",opacity: 0.9});
				//Example of preserving a JavaScript event for inline calls.
				$("#click").click(function()
				{ 
					$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
					return false;
				});
			});
		</script>
 </head>		
<body>
       
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
		 <tr>
		   <th class="hd_gd" colspan="7" align="center" valign="middle" height="60">Photo Gallery</th>
		 </tr>
		  <tr style="padding-top:5px;">
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/1.jpg " title="Sadaram camp,Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/1.jpg" style="width: 200px; height: 150px"></a></td>
			    <td align="left" valign="top">&nbsp;</td>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/2.jpg " title="Sadaram camp,Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/2.jpg" style="width: 200px; height: 150px"/></a></td>
			    <td align="left" valign="top">&nbsp;</td>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/3.jpg " title="Sadaram Camp Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/3.jpg" style="width: 200px; height: 150px"/></a></td>
			    <td align="left" valign="top">&nbsp;</td>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/4.jpg " title="Sadaram,Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/4.jpg" style="width: 200px; height: 150px"/></a></td>
		  </tr>
		  <tr>
		   		 <td colspan="7" align="left" valign="top">&nbsp;</td>
		  </tr>
		  <tr>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/5.jpg " title="For Sadaram certification,at Sadaram camp Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/5.jpg" style="width: 200px; height: 150px"/></a></td>
			    <td align="left" valign="top">&nbsp;</td>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/6.jpg " title="Hearing impaired child with mother at registration Sadaram Vanasthalipuram"><img src="<%=request.getContextPath()%>/images/6.jpg" style="width: 200px; height: 150px"/></a></td>
			    <td align="left" valign="top">&nbsp;</td>
			    <td align="left" valign="top"><a class="group4"  href="<%=request.getContextPath()%>/images/7.jpg " title="Rigistrations at Sadaram,Vanasthalipuram,Rangareddy"><img src="<%=request.getContextPath()%>/images/7.jpg" style="width: 200px; height: 150px"/></a></td>
		  </tr>
			  
	</table>
</body>
</html>    
    

