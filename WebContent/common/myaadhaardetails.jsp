<%@ page language="java" import="java.util.HashMap,java.util.Iterator,com.ecentric.servicemodels.AadhaarProfile" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Aadhar Details :: </title>
</head>
<body>
<center>
<%
try
		{
		AadhaarProfile AadhaarProfileData = (AadhaarProfile)request.getAttribute("AadhaarProfileData");
		
		if(AadhaarProfileData!=null)
		{
		
			
		%>
			<table width="50%" align="center" border="1" style="border-color: #12627b; font-family: Times New Roman; font-size:12px;">
			<tr>
				<td colspan="4" align="center">Aadhaar Details</td>
			</tr>
			<tr>
				<th align="left" width="25%">UID</th>
				<td align="left" width="25%"><%=AadhaarProfileData.getUid() %>&nbsp;</td>
				<td align="center" valign="middle" width="50%" colspan="2" rowspan="3"><img src="data:image/png;base64,<%=AadhaarProfileData.getBase64file()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultProfile.jpg'" border="0"/></td>
			</tr>
			<tr>
				<th align="left">EID</th>
				<td align="left"><%=AadhaarProfileData.getEid() %>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Status Code</th>
				<td align="left"><%=AadhaarProfileData.getStatus() %>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Name</th>
				<td align="left"><%=AadhaarProfileData.getName() %>&nbsp;</td>
				<th align="left">Date of Birth</th>
				<td align="left"><%=AadhaarProfileData.getDob() %>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Gender</th>
				<td align="left"><%=AadhaarProfileData.getGender()%>&nbsp;</td>
				<th align="left">Relation</th>
				<td align="left"><%=AadhaarProfileData.getCareof()%>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">State Code</th>
				<td align="left"><%=AadhaarProfileData.getStatecode()%>&nbsp;</td>
				<th align="left">District Code</th>
				<td align="left"><%=AadhaarProfileData.getDistrict()%>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">District Name</th>
				<td align="left"><%=AadhaarProfileData.getDistrict_name() %>&nbsp;</td>
				<th align="left">Mandal Name</th>
				<td align="left"><%=AadhaarProfileData.getMandal_name()%>(<%=AadhaarProfileData.getMandal()%>)&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Village Name</th>
				<td align="left"><%=AadhaarProfileData.getVillage_name() %>(<%=AadhaarProfileData.getVillage() %>)&nbsp;</td>
				<th align="left">Building Name</th>
				<td align="left"><%=AadhaarProfileData.getBuildingName()%>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Street</th>
				<td align="left"><%=AadhaarProfileData.getStreet()%>&nbsp;</td>
				<th align="left">PIN Code</th>
				<td align="left"><%=AadhaarProfileData.getPincode()%>&nbsp;</td>
			</tr>
			<tr>
				<th align="left">Contact No.</th>
				<td align="left" colspan="3"><%=AadhaarProfileData.getPhoneNo()%>&nbsp;</td>
			</tr>
			</table>
		<%
		}
		else 
		{
		%>
		<center>Not Data Found</center>
		<%
		}
}
catch(Exception e)
{
	%>
		Exception : <br><%=e.getMessage() %>
	<%
}
%>
<br/>
<a href="<%=request.getContextPath()%>/sadaremdefault.do"><b>Home</b></a>
</center>
</body>
</html>