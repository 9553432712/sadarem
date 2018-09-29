<%@ page language="java" import="java.util.ArrayList,com.tcs.sadarem.util.CommonUtility" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ArrayList loginUserDetailsExist = (ArrayList)request.getAttribute("loginUserDetailsExist");
ArrayList tableHeaderList = new ArrayList();
ArrayList tempList = new ArrayList();

String alert_tab_msg	= CommonUtility.checkNullObj(request.getAttribute("alert_tab_msg"));
String alert_tab_css	= CommonUtility.checkNullObj(request.getAttribute("alert_tab_css")); 
 

if(alert_tab_css.equals(""))
{
	alert_tab_css = "alert-info";
}
 
int totalExistUserCount =0;

if(loginUserDetailsExist!=null && loginUserDetailsExist.size()>1)
{
	tableHeaderList = (ArrayList)loginUserDetailsExist.get(0);
	totalExistUserCount =loginUserDetailsExist.size();
%>

<table id="ExistingLoginUserTabID"  class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid"> 
<thead>
    <tr> 
      <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;">S.No.</th>
      <%
      for(int i=0;i<tableHeaderList.size();i++)
      {
      %>
      <th style="background-color:#337ab7;padding:5px;color:#fff;text-align:center; vertical-align: middle;"><%=tableHeaderList.get(i) %></th>
      <%
      }
      %>
 	</tr>
</thead>
<tbody>
	<%
	for(int i=1;i<loginUserDetailsExist.size();i++)
	{
		tempList = (ArrayList)loginUserDetailsExist.get(i);
		%> 
	    <tr>
	     <td style="text-align:center; vertical-align: middle"><%=i%></td>
        <%
	    for(int j=0;j<tempList.size();j++)
	    {
	    %>
	     <td style="text-align:left; vertical-align: middle"><%=tempList.get(j)%></td>
	    <%
	    }
	    %>  
		</tr>
	<%
	}
	%>
</tbody>
</table>		
<%
}
else if(loginUserDetailsExist==null)
{
%>
	<div class="alert alert-warning"><b>Please select Role,District,Camp,Mandal to view the details.</b></div>
<%
}
else if(alert_tab_msg==null || alert_tab_msg.equals(""))
{
%>
	<div class="alert alert-danger"><b>No records to display</b></div>
<%
}
else
{ 
    if(!alert_tab_msg.equals("")) 
    {
    %> 
		<div class="row" style="margin-top:10px;"> 
		    <div class="alert <%=alert_tab_css %> fade in alert-dismissable">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
			    <b><%=alert_tab_msg%></b>
			</div>  
		</div> 
	<%
    } 
}
%>

<form id="LoginUserTableFormId" name="LoginUserTableFormId" method="post">
<input type="hidden" id="reqTabFormId" name="reqTabFormId" value="<%=CommonUtility.checkNullObj(session.getAttribute("sesTabFormId"))%>">
<input type="hidden" id="role_id" name="role_id">
<input type="hidden" id="district_id" name="district_id">
<input type="hidden" id="camp_id" name="camp_id">
<input type="hidden" id="mandal_id" name="mandal_id">
<input type="hidden" id="search_user_id" name="search_user_id">
<input type="hidden" id="search_email_id" name="search_email_id">
<input type="hidden" id="search_contact_no" name="search_contact_no">
<input type="hidden" id="totalExistUserCount" name="totalExistUserCount" value="<%=totalExistUserCount%>">
</form>
<script>
$(document).ready(function() 
{
	<%
	if(loginUserDetailsExist!=null && loginUserDetailsExist.size()>1)  
	{
	%>
	
	$('#ExistingLoginUserTabID').DataTable( 
	{
	    scrollY:        "350px",
	    scrollX:        true,
	    scrollCollapse: true,
	    paging:         false, 
	    fixedColumns:   false,
	    lengthChange: false, 
	    orderable:false,
	    fixedColumns:   
	    {
	        leftColumns:3
	    }
	    
	} ); 
	 
	
	<%
	} 
	%>  	
});

</script>