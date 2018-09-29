<%-- 
    Document   : UserLoginsDisplay
    Created on : Jun 2, 2010, 2:12:10 PM
    Author     : t_bapinaidu
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ page import="java.util.*,com.tcs.sadarem.util.CommonUtility" %> 
 <!DOCTYPE html>
<html:html>
    <% 
    
    List userDetailsList = (ArrayList) request.getAttribute("userDetailsList");
    int length = 0;
    if (userDetailsList != null) 
    {
        length = userDetailsList.size();
    }
    
    String msg = (String) request.getAttribute("msg"); 
	boolean isMSIE =  CommonUtility.getIEBrowserStatus(request);   
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:: SADAREM :: User Status Details</title>
    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>
	
     


<style>
.firstline{
background-color:white;
}
.secondline{
background-color:#e2ebf4;
} 

.btn
{
 width:auto!important; 
 padding : 5px !important;
 cursor:pointer!important;
}
#resultdata_filter {float:right;}
.dataTables_length {float:left;}

    </style>
    
        
    </head>
    <body>
    
 <div class="main_container" oncontextmenu="return false;" ondragstart="return false" onselectstart="return false" >     
        <font color="blue"><b>Note : </b></font>
          <ul>
          	<li><b>Allow Re-Login button  :</b> Use this to allow the user to login again (When user forgot to logout and trying to login again.)</li>
          	<li><b>Deactivate button</b>  : Use this to <b>Deactivate</b> the user (If  Deactivated user can not login)</li>
          	<li><b>Activate button</b>  : Use this button to <b>Activate</b> the user (Use can login once Activated)</li>
          	<li style="font-size:15px; color:red; font-weight:bold;">Please do not forgot to Deactivate the user's every day while leaving the camp</li>
          	<%  
          	if(isMSIE!=true)
		    {
			%>  
          	<li>Login Status  :  <i class="fa fa-check-circle-o fa-5" style="color:green; font-size: 20px;"></i> : Logged In , <i class="fa fa-times-circle-o fa-5" aria-hidden="true" style="color:red; font-size: 20px;"></i> : Not Logged In </li>
          	<li>Status  : <i class="fa fa-unlock-alt fa-5" aria-hidden="true" style="color:green; font-size: 20px;"></i> Activated,  <i class="fa fa-lock fa-5" aria-hidden="true"  style="color:red; font-size: 20px;"></i> : Deactivated</li>
          	<%
          	}
          	%>
          </ul>
        <html:form action="loginDetails.do?updateLoginDetails=updateLoginDetails" method="post">
            <p  id="succmsg">
                <%if (msg != null && !"".equals(msg)) {%>
                <%=msg%>
                <%}%>
            </p>

            <input type="hidden" id="LoginIdsStatus" name="LoginIdsStatus"/>
            <input type="hidden" name="listlength" id="length" value="<%=length%>"/>
            

            <%String message = (String) request.getAttribute("message");
                        if (message != null && !"".equals(message)) {
            %>

            <p id="succmsg"><%=message%></p>

            <%
            } 
            else 
            {
            %> 
      
		 <center>
			  <table id="resultdata" class="table table-striped table-bordered" style="width:80% !important;"> 
			    <thead>
			      <tr>
                    <th colspan="6" style="background-color:#337ab7;padding:5px;color:#fff; text-align: center; vertical-align: middle;">User Login  Un-Lock / Active / Inactive</th>
                </tr>
                <tr>
                    <th width="10%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Select All <br/><input type='checkbox' name='checkall' onclick="checkedAll();" style="width:30px"></th>
                    <th width="15%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">User ID</th>
                    <th width="25%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Person Name</th>
                    <th width="30%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Role</th>
                    <th width="10%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Login Status</th>
                    <th width="10%" style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;">Activated / Deactivated</th>
                </tr>
                <%
                
                int i = 1;
                String style="";
				try
				{
                
                %>
                <logic:iterate id="userdetails" name="userDetailsList" scope="request">
                <%
                if(i%2==0)
	           	 {
	           		 style="firstline";
	           	 }
                 else
	           	 {
	           		 style="secondline";
	           	 }
                %>
                
                    <tr>
                        <td  class="<%=style%>" style="border:#337ab7 1px solid; text-align:center;">
                            <input type="checkbox" name="rowIds" style="width:30px" id="<%=i++%>" value="<bean:write name="userdetails" property="rowId"/>"/>
                        </td>
                        <td class="<%=style%>" style="border:#337ab7 1px solid; text-align: left;">
                            <bean:write name="userdetails" property="loginid"/>
                        </td>
                        <td class="<%=style%>" style="border:#337ab7 1px solid; text-align: left;">
                            <bean:write name="userdetails" property="userName"/>
                        </td>
                        <td class="<%=style%>" style="border:#337ab7 1px solid; text-align: left;">
                            <bean:write name="userdetails" property="rolename"/>
                        </td>
                        <td class="<%=style%>" style="border:#337ab7 1px solid; text-align: center">  
                        
                        	<%  if(isMSIE)
		                    { 
							 %> 
                           	 	<logic:equal name="userdetails" property="lockstatus" value="Yes" >
                        				<font color="red">Logged In</font>
		                        </logic:equal>
		                        
		                        <logic:equal name="userdetails" property="lockstatus" value="No" >
										<font color="green">Not Logged In</font>
		                        </logic:equal> 
							<%
		                    }
                        	else
                        	{
							%>
                        
                         		<logic:equal name="userdetails" property="lockstatus" value="Yes" >
                        				<i class="fa fa-check-circle-o fa-5" aria-hidden="true" style="color:green; font-size: 20px;"></i>
		                        </logic:equal>
		                        
		                        <logic:equal name="userdetails" property="lockstatus" value="No" >
										 <i class="fa fa-times-circle-o fa-5" aria-hidden="true" style="color:red; font-size: 20px;"></i>
		                        </logic:equal> 
		                    <%
                        	}
		                    %>
                        	
                        </td>
                        <td class="<%=style%>" style="border:#337ab7 1px solid; text-align: center">
                        
                        <%  if(isMSIE)
		                    {
							 %>  
		                        <logic:equal name="userdetails" property="status" value="Yes" >
		                         		<font color="green">Activated</font>
		                        </logic:equal>
		                        
		                        <logic:equal name="userdetails" property="status" value="No" >
										<font color="red">Deactivated</font>
		                        </logic:equal>
							<%
		                    }
                        	else
                        	{
							%>
		                        <logic:equal name="userdetails" property="status" value="Yes" >
		                         	<i class="fa fa-lock fa-5" aria-hidden="true"  style="color:red; font-size: 20px;"></i>
		                        </logic:equal>
		                        
		                        <logic:equal name="userdetails" property="status" value="No" >
										 <i class="fa fa-unlock-alt fa-5" aria-hidden="true" style="color:green; font-size: 20px;"></i>
		                        </logic:equal>
		                        <%
                        	}
		                        %>
                        </td>
                    </tr>
                </logic:iterate>
                
                <%
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
                %>
                <tr>
                    <th colspan="6"  style="background-color:#337ab7;padding:5px;  text-align: center; vertical-align: middle;">
                    
                     <button type="button" value="Release" class="btn btn-success" onclick="releaseLoginIds(this.value);">
					    <span class="fa fa-check-circle-o fa-5"></span> Allow Re-Login
					  </button>
                     &nbsp;&nbsp;&nbsp;
                     
                   
                     <button type="button" value="UnLock" class="btn btn-success" onclick="unlockLoginIds(this.value);">
					    <span class="fa fa-unlock-alt fa-5"></span> Activate
					  </button>
                     &nbsp;&nbsp;&nbsp;
					    
					    
                     <button type="button" value="Lock" class="btn btn-warning" onclick="lockLoginIds(this.value);">
					    <span class="fa fa-lock fa-5"></span> Deactivate
					  </button>
                    
                    </th>
                </tr>
            </table> 
</center>
            <%}%>

        </html:form>
        </div>
        
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	
	<script language="JavaScript">
	
            function releaseLoginIds(read)
            {
               $("#LoginIdsStatus").val("RleaseLoginIds");
                document.loginForm.submit();
            }
            
            function lockLoginIds(read)
            {
            	$("#LoginIdsStatus").val("Lock");
                document.loginForm.submit();
            }
            
            function unlockLoginIds(read)
            {
            	$("#LoginIdsStatus").val("UnLock");
                document.loginForm.submit();
            }
            
            checked=false;
            
            function checkedAll()
            {
                if(document.getElementById("length") != null && document.getElementById("length") != ""){
                    var aa= document.getElementById("length").value;
                    if (checked == false)
                    {
                        checked = true;
                    }
                    else
                    {
                        checked = false;
                    }
                    if(aa != null){
                        for (var i =1; i <= aa; i++)
                        {
                            document.getElementById(i).checked = checked;
                        }
                    }
                }

            }
           
        </script>
	
    </body>
</html:html>
