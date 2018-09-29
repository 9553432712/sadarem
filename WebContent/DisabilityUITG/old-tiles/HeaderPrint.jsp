
<html>
<head>
<title>SADAREM</title>
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


<script type="text/javascript">
// window.status="WelCome To SADAREM";
</script>


 <script type="text/javascript">
      history.forward();
</script>



</head>
<% String selection=(String)session.getAttribute("selectFlow"); %>
 <table width="85%" align="center">


 <tr>
     <% if(selection!=null && selection.equalsIgnoreCase("appellate")){%>
     <td width="100%" colspan="2" align="center"><h3><b>Appellate Authority Formats</b></h3></td><%}%>
     <% if(selection!=null && selection.equalsIgnoreCase("temporay")){%>
     <td width="100%" colspan="2" align="center"><h3><b>Temporary Formats</b></h3></td><%}%>
     <% if(selection!=null && selection.equalsIgnoreCase("Physical")){%>
     <td width="100%" colspan="2" align="center"><h3><b>Physical Requirements Formats</b></h3></td><%}%>
 </tr>

 </table>


</html>