<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList" %>
<htm>
<head>
<title>:: SADAREM :: Contact Us</title>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
</head>
<%
	ArrayList contactList = (ArrayList)request.getAttribute("contactList");

%>
<body>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <th class="hd_gd" align="center" valign="middle" height="50">Contact Details</th>
  </tr>
  <%
  if(contactList!=null && contactList.size()>0)
  {
	  String newType ="",oldType ="",strMyStyle="";
	  int mycount = 0;
	
  %>
	  <tr>
	    <td align="left" valign="middle" width="100%">
	    <%
	    
	    ArrayList tempList = new ArrayList();
	    
		  for(int i=0;i<contactList.size();i++)
		  {
			  tempList = (ArrayList)contactList.get(i);
			  
			  newType= tempList.get(8).toString();
			  if(!newType.equals(oldType))
			  {
				
				 mycount++;
				 if(i!=0)
				 {
					 %>
					 </table>
					 <%
				 }
				 mycount = 1;
	    %>
	    <br/><br/>
	    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="gd_row">
				  <tr>
				    <th class="hd_gd" align="center" valign="middle" height="50" colspan="8" style=""><%=newType %></th>
				  </tr>
  				<tr>
  				 	<th class="hd_gd" align="center" valign="middle" width="5%" height="50" rowspan="2">S.No.</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="10%" height="50" rowspan="2">District</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="20%" height="50" rowspan="2">Name</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="10%" height="50" rowspan="2">Designation</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="55%" height="50" colspan="4">Contact Details</th>
  				</tr>
  				<tr>
  				 	<th class="hd_gd" align="center" valign="middle" width="10%" height="50">Mobile No.</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="15%" height="50">Land Line</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="15%" height="50">eMail</th>
  				 	<th class="hd_gd" align="center" valign="middle" width="15%" height="50">Address</th>
  				</tr>
	  				<tr>
	  				 	<td class="firstrow" align="center" valign="middle"><%=mycount%>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(1) %>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(2) %>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(3) %>&nbsp;</td>
	  				 	<td class="firstrow" align="center" valign="middle"><%=tempList.get(4) %>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(5) %>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(6) %>&nbsp;</td>
	  				 	<td class="firstrow" align="left" valign="middle"><%=tempList.get(7) %>&nbsp;</td>
	  				</tr>
  			<%
		  			if(i==contactList.size()-1)
					 {
						 %>
						 </table>
						 <%
					 }
		  		}
			  else
			  {
				  mycount++;
				  if(mycount%2==0)
				  {
					  strMyStyle="secondrow";
				  }
				  else
				  {
					  strMyStyle="firstrow";
				  }
				  %>
	  				<tr>
	  				 	<td class="<%=strMyStyle%>" align="center" valign="middle"><%=mycount%>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(1) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(2) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(3) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="center" valign="middle"><%=tempList.get(4) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(5) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(6) %>&nbsp;</td>
	  				 	<td class="<%=strMyStyle%>" align="left" valign="middle"><%=tempList.get(7) %>&nbsp;</td>
	  				</tr>
				  <%
				  if(i==contactList.size()-1)
					 {
						 %>
						 </table>
						 <%
					 }
			  }
			  
			  oldType=newType;
		  }
  			%>
	    </td>
	  </tr>
  <%
  }
  else
  {
	  %>
	   <tr>
	    <td align="center" valign="middle" width="100%" height="50" style="border-left:#234466 solid 1px;" colspan="8">
	    			No Contact Details Available.
	    </td>
	  </tr>
	  <%
  }
  %>
</table>
</body>
</htm>
