<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList,com.tcs.sadarem.util.PasswordEncriptDecrypt" language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>:: Issue Tracking System ::</title>
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	  
    
	
	<style>  
   .jqueryslidemenu ul li a:link,.jqueryslidemenu ul li a:visited {
	color:#000000;
	z-index:1000;
	height:30px!important;
}
	  .loader {
		position: fixed;
		left: 0px;
		top: 0px;
		width: 100%;
		height: 100%;
		z-index: 9999;
		background: url('images/loading2.gif') 50% 50% no-repeat rgb(249,249,249);
	}
	.iframe{
    overflow:hidden;
}
   </style> 
 
  <script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
  </script>

	<%try{ %>
	<%
	String encimgPath="";
	  ArrayList records = new ArrayList();
	  records           = (ArrayList)request.getAttribute("records");
	System.out.println("records->jsp->"+records);
	 %>
  
<script>

$(document).ready( function()
		{
	 
	$('#issueData').dataTable
	   ({
		   "iDisplayLength": 10, 
		   paging : false,
		   "scrollY" : 200
	    });
			
				 $("#raiseissueButID").click(function ()
							{
					  if ($('#reqtype').val()==-1)
		                {
		                    alert("Please Select Status type");
		                    $('#issueType').focus();
		                    return false;
		                }
					 
					 else{
					 
					  
					 document.form_issue.target="_self";
						document.form_issue.action="<%=request.getContextPath()%>/IssueRequestlist.do";
						document.form_issue.submit();
			     	  
			 	/* }} */}
			 
  	  
	     	  
			}); 

				
		});

function showData()
{ 
	var data = document.getElementById('statusMsg').value;
	if(!data=='')
		{
		window.location.reload();
		  
		}
}


</script>
<style>
div.dataTables_filter {
     text-align: left; 
}

    
html {
    font-family: serif;
    }
.firstline{
background-color:none;
}
.secondline{
background-color:#e2ebf4;
} 
</style>

</head>
<body onload="showData();">
 
 <div class="loader"></div>

<div style="height:400px;width:98%;">
 <div class="panel-heading" style="text-align:center;">
					                    <h3 class="panel-title"><font style="color:rgb(51, 122, 183)"><b><U>
					                       Appellate Authority Grievances Report </U> </b></font>
					                    </h3>
					                </div>
  

 <%if(records!=null && records.size()>0){ %>
  <table id="issueData" class="table table-striped table-bordered" width="100%">
    <thead>
      <tr>
        <td style="background-color:#337ab7;padding:5px; color:#fff;" align="center">S.No.</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">SADAREM ID</td>      
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Issue ID</td>     
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Category</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Name</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
        <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal</td>
       <td style="background-color:#337ab7;padding:5px;color:#fff;  width:30%" align="center">Village</td>
            <td style="background-color:#337ab7;padding:5px;color:#fff;  width:30%" align="center">Is Assessed<br>(Y/N)</td>
                 <td style="background-color:#337ab7;padding:5px;color:#fff;  width:30%" align="center">Download</td>
      </tr>
    </thead>
    <tbody>
    <%
	
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     String style="";
   
     for(loopCount=0;loopCount<records.size();loopCount++)
	 { 
    	 innerList = (ArrayList)records.get(loopCount);
    	 
    	  String imgpath[] = ((String)innerList.get(8)).split(",");
	 if(loopCount%2==0)
	 {
		 style="firstline";
	 }else
	 {
		 style="secondline";
	 }
	 %>
     <tr>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
     <td class="<%=style%>" style="border:#337ab7 1px solid;" ><%=innerList.get(0) %></td>  
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(1) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(2) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(3) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(4) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid; "><%=innerList.get(5) %></td>
        <td class="<%=style%>" style="border:#337ab7 1px solid; width:15px;" align="left" ><%=innerList.get(6) %> </td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center"><%=innerList.get(7) %> </td>
        <td class="<%=style%>" style="border:#337ab7 1px solid;" align="center">
           <%for(int i=0;i<imgpath.length;i++)
		 {				                	
		 encimgPath = PasswordEncriptDecrypt.encrypt((String)imgpath[i]);
		encimgPath = encimgPath.replace("+", "%2B"); %>
		 <a  class="btn icon-btn-primary btn-primary" style="color:#fff; padding:inherit;" target="_blank" href="<%=request.getContextPath()%>/dispimg.do?action=downloadFile&reqId=<%=encimgPath%>"><span class="glyphicon glyphicon-save"><%=i+1 %></span></a>
			 <%} %> </td>
        
      </tr>
      <%
      }
		%>
		 </tbody>
    </table>
		
      <%
      }%>
      
    
 </div>

</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>