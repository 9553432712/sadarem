<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page  import="java.util.*"%>
<html>
<body>
<html:form  action="selectdoctorsinformation.do?parameter=selectDoctorInformation" method="post" onsubmit="return validateDoctorsInformationForm(this);">
<table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">
    <tr>
        <th colspan="4">Update Doctors Details</th>
        </tr>
      
    <tr>
        <td >Disability Type<font color="red">*</font></td>
    <td><html:select property="typeofdisability">
    <html:option value="">-Select-</html:option>
    <%
  ArrayList a=(ArrayList)request.getAttribute("disabilitytype");
  Iterator i=a.iterator();
  while(i.hasNext())
  {
      String str=(String)i.next();
%>
<html:option value="<%= str %>"><%= str %></html:option>
<%
  }
  
%>
    </html:select></td>
    </tr>
<tr>
    <th colspan="2" align="center">
        <html:submit property="submit" value="Submit" styleClass="button"/>
    </th>
</tr>

</table>
    <html:javascript formName="/selectdoctorsinformation"/>
</html:form>
</body>