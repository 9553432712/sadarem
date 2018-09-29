<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility" %>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%try{ %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
    </head>
    <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }</style>
    <body><br><br>	

        <logic:present name="msg">
            <table align="center">  
                <tr>
                    <td class="label" align="center" colspan="2">
                        <font color="red" size="4"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr></table>
            </logic:present>

        <logic:notPresent name="msg">
        <% ArrayList basic=new ArrayList();
     basic=(ArrayList)request.getAttribute("basicdetails");
     
     %>
            <table align="center">
                <p id="errmsg">Invalid SADAREM ID or Disability does not exists</p>

            </table>
             <logic:present name="basicdetails">
              <table align="center" width="70%" border='0'>
      
          <tr><th height="30" class="hd_gd" style="border:#234466 1px solid" align="center" valign="middle">SADAREM ID</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Name</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Relation Name</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"   align="center" valign="middle">Gender</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">District</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Mandal</th> 
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Person Status</th></tr> 
          
    
          <tr><th class="secondrow" style="border:#234466 1px solid"><%= basic.get(0)%></th>
          <th class="secondrow"  style="border:#234466 1px solid"><%=basic.get(1)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(2)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(3)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(4)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(5)%></th>
           <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(6)%></th></tr>
    
      </table>
      </logic:present> 
        </logic:notPresent>
        
      
   <logic:present name="basicDetails">
     <% ArrayList basic=new ArrayList();
     basic=(ArrayList)request.getAttribute("basicDetails");
     
     %>
    
      
      <table align="center" width="70%" border='0'>
      
          <tr><th height="30" class="hd_gd" style="border:#234466 1px solid" align="center" valign="middle">SADAREM ID</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Name</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Relation Name</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"   align="center" valign="middle">Gender</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">District</th>
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Mandal</th> 
          <th height="30" class="hd_gd" style="border:#234466 1px solid"  align="center" valign="middle">Person Status</th></tr> 
          
    
          <tr><th class="secondrow" style="border:#234466 1px solid"><%= basic.get(0)%></th>
          <th class="secondrow"  style="border:#234466 1px solid"><%=basic.get(1)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(2)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(3)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(4)%></th>
          <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(5)%></th>
           <th class="secondrow" style="border:#234466 1px solid"><%=basic.get(6)%></th></tr>
    
      </table>
    
   
   </logic:present>
 
    </body>
</html>
<%}catch(Exception e){
	System.out.println(e);
}%>