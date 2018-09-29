
<SCRIPT LANGUAGE="JavaScript1.1">
function right(e) {
if (navigator.appName == 'Netscape' &&
(e.which == 3 || e.which == 2))
return false;
else if (navigator.appName == 'Microsoft Internet Explorer' &&
(event.button == 2 || event.button == 3)) {
alert("Sorry, you do not have permission to right click.");
return false;
}
return true;
}

document.onmousedown=right;
document.onmouseup=right;
if (document.layers) window.captureEvents(Event.MOUSEDOWN);
if (document.layers) window.captureEvents(Event.MOUSEUP);
window.onmousedown=right;
window.onmouseup=right;

</SCRIPT>
<style>
body
{
	margin:0px 0px 0px 0px;
	padding:0px 0px 0px 0px;
}

.row
{
	margin-right:0px !important;
	margin-left:0px !important;
}

</style>

<script type="text/javascript">
//window.status="WelCome To SADAREM";
</script>


 <script type="text/javascript">
      history.forward();
</script> 
<table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
    <tr>
        <td style="background-image:url(<%=request.getContextPath()%>/images/head_bg.png); background-repeat:repeat-x ;background-color:#3393DF">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" style="color:#fff;font-size:11px;font-weight:normal;">
                <tr>
                     <%-- <td align="right" ><img src="./DisabilityUITG/images/HDR.png"/></td>--%>
                      <td align="right" width="2%"><img src="<%=request.getContextPath()%>/DisabilityUITG/images/Serp.png"/></td>
                       <td align="center" width="58%">
	                       	<b style=" font-size:13px; font-style: normal;">
	                       		Software for Assessment of Disabled for Access Rehabilitation and Empowerment
	                       	</b>
                       		<br/>
	                          <span style="font-size:13px; font-style: normal;font-variant: small-caps ;"> Society for Elimination of Rural Poverty<br/> </span>
	                          <span style="font-size:12px; font-style: normal;">  Governement of Telangana </span> 
                       </td>
                       <td width="38%">
	                       <p style="line-height:20px;text-align:left;color:white;font-weight:normal;font-size:11px;">
			                       	<b style="font-size:12px;">Person Name :</b> <%=session.getAttribute("persondispname") %><br/>
			                       	<b style="font-size:12px;">Login ID :</b> <%=session.getAttribute("username") %><br/>
			                       	<b style="font-size:12px;">District :</b> <%=session.getAttribute("logDistName") %><br/>
			                       	<b style="font-size:12px;">Camp :</b> <%=((String)session.getAttribute("logCampName")).replaceAll("<br/>"," ") %><br/>
			                       	<b style="font-size:12px;">Role :</b> <%=session.getAttribute("logRoleName")%>
	                       	</p>
                       </td>
                       <td width="2%" align="right"><img src="<%=request.getContextPath()%>/images/serp_logo.png"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table> 