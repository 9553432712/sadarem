<%@page import="com.tcs.sadarem.util.ComboUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.CommonUtility"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM TG :: Login Details</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<%
	ArrayList campadminDtlsList = (ArrayList)request.getAttribute("campadminDtlsList");
	ArrayList roleList			= (ArrayList)request.getAttribute("roleslist");

	String selRoleId 	= CommonUtility.checkNullObj(request.getAttribute("selRoleId"));
	String selRoleName  = CommonUtility.checkNullObj(request.getAttribute("selRoleName"));
	
	ArrayList headerList = null; 
	
	if(campadminDtlsList.size()>0)
	{
		headerList = (ArrayList)campadminDtlsList.get(0);
	} 
	
	%>
	
<style>
.firstline
{
	background-color:white;
}

.secondline
{
	background-color:#e2ebf4;
} 

.btn
{
 width:auto!important; 
 padding : 5px !important;
 cursor:pointer!important;
}
  
 </style>
	
</head>
<body>
 <div class="main_container" oncontextmenu="return false;" ondragstart="return false" onselectstart="return false" >     
 <center> 
 <span style="float: right; padding-right: 20px;"><button type="button" id="printbutid" class="btn btn-primary btn-large">  <span class="glyphicon glyphicon-print"></span><b> Print</b>  </button></span>
 					<form name="viewlogindetails" method="post">
		 				<div class="col-md-3 col-md-push-4" style="padding-bottom: 5px!important">
							<div class="input-group">
						      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Role</div>
						      	<%=ComboUtil.createStrComboBoxAutoWithAttribute("roleid", roleList, selRoleId, "form-control", "", true, true, "", "")%>
						    </div>
						</div> 
						<a href="<%=request.getContextPath()%>/adduserdisplay.do?mode=exportExcel&roleid=<%=selRoleId %>" style="float:right;text-align:center;">
							<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
			 			</a>
				   </form>
<div id="printdivid">
			  <table id="campadmindtaildTABID" class="table table-striped table-bordered" style="width:80% !important;"> 
			    <thead>
			      <tr>
                    <th colspan="<%=headerList.size() %>" style="background-color:#337ab7;padding:5px;color:#fff; text-align: center; vertical-align: middle;"> Login Details of <%=selRoleName %> Role</th>
                </tr>
                <%
                if(headerList.size()>0)
                {
                	%>
                	<tr>
                	<%
                	for(int i=0;i<headerList.size();i++)
                	{
                	%>
                		<th style="background-color:#337ab7;padding:5px;color:#fff; border:#ffffff 1px solid; text-align: center; vertical-align: middle;"><%=headerList.get(i)%></th>
                 	<%
                	}
                	%>
                	</tr>
                <%
                } 
                %>
                </thead>
                <tbody>
                <%
	            try
	             { 
	            	if(campadminDtlsList.size()>1)
	            	{

	                    String style="";
	            		ArrayList dataList = null;
	            		
	            		for(int i=1;i<campadminDtlsList.size();i++)
	            		{
	            			dataList = null;
	            			dataList = (ArrayList)campadminDtlsList.get(i); 
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
				             <%
			                	for(int j=0;j<dataList.size();j++)
			                	{
			                	%>
			                		 <td class="<%=style%>" style="border:#337ab7 1px solid; "><%=dataList.get(j) %></td>
			                 	<%
			                	}
			                	%>
	            			</tr> 
                	
                <%
	            		}
	            	}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
                %>
                </tbody>
            </table>
          </div>          
    </center>   
 </div>
 
<script type="text/javascript">
  $(document).ready(function()
			{ 
	   			
	   			$("#printbutid").click(function()
	   					{  
		   					  w=window.open(null, 'Print_Page', 'scrollbars=yes');
		   					  w.document.write('<html><head><title>::Print::</title>');
		   					  w.document.write('<style>@media print {  #buthide, #closebutid { display: none !important; }</style></head><body>')
							  w.document.write("<center><button type='button' id='buthide' onclick='window.print()'>Print</button>");
							  w.document.write("&nbsp;&nbsp;&nbsp;<button type='button' id='closebutid' onclick='window.close()'>Close</button><br>");
		   				      w.document.write("Printed On : <%=CommonUtility.getDateTime("dd/MMM/yyyy hh:mm:ss")%>"+jQuery('#printdivid').html());
							  w.document.write("</center></body></html>");
		   				    w.document.close();
		   				   // w.print();
	   				});
	   			
	   			
	   			$("#roleid").change(function ()
	   			{
	   					//alert($(this).val());
	   					if($(this).val()=="-1")
	   						{
	   							alert("Please select Role");
	   							$(this).focus();
	   							return false;
	   						}
	   					else
	   						{
	   							document.viewlogindetails.action="<%=request.getContextPath()%>/adduserdisplay.do?mode=campadmindetailsreport";
	   							document.viewlogindetails.submit();
	   						}
	   			});
	   			
	   		});
</script>   					
 
</body>
</html>