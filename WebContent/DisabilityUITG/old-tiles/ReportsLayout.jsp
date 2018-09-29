<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

<head >
    
</head>
<body> 

<table border="2" cellpadding="0" cellspacing="0"  align="center" width="100%" height="100%" style="border-collapse:collapse;" bordercolor="#660000">
  <tr>
  <td width="100%" colspan="2" valign="top" height="15%"><tiles:insert attribute="header" ignore="true"/></td>
  </tr>

   <tr>
   <td width="100%" colspan="2" valign="middle" height="4%"><tiles:insert attribute="menu" ignore="true"/></td>
   </tr>
   <tr >
    <td height="1%" width="80%" colspan="2" valign="top"><tiles:insert  attribute="surgerytype" ignore="true"/></td>
    </tr>
     <tr >
    <td height="1%" width="80%" colspan="2" valign="top"><tiles:insert  attribute="assistivedevice" ignore="true"/></td>
    </tr>
     <tr >
    <td height="1%" width="80%" colspan="2" valign="top"><tiles:insert  attribute="educationservices" ignore="true"/></td>
    </tr>
    
       <tr>
        <td  width="15%" valign="top"><tiles:insert attribute="LeftNavigationReport" ignore="true"/></td>
       
        <td width="65%" valign="middle" ><tiles:insert attribute="MiddleBodyReport" ignore="true"/></td>
      </tr>
        <tr>
        <td width="80%" colspan="2" valign="top" height="7%"><tiles:insert attribute="footer" ignore="true" /></td>
        </tr>
   
</table>

</body>

</html>