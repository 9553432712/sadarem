<%@ page  import ="com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.ArrayList"language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>:: Issue Tracking System ::</title>

      <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />     
	<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
	
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
	  
    
	
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
		html 
		 {
		    font-family: serif;
		 }
		.hd_gd
		{
		background-color:#337ab7 !important;
		}
	    .warnRow
	    {
	      font-family:Verdana, Geneva, sans-serif;
			font-size:11px;
			color:#000000;
			padding:6px;
			background-color: #DB5340;
			border-top:#234466 solid 1px !important;
			border-left: #234466 solid 1px !important;
		}
	    .optRow
	    {
	      font-family:Verdana, Geneva, sans-serif;
			font-size:11px;
			color:#000000;
			padding:6px;
			background-color: #7F91DA;
			border-top:#234466 solid 1px !important;
			border-left: #234466 solid 1px !important;
		}
		.JsDatePickBox
		{
           top:35px !important;
           left:-100px !important;
		}
		.errmsg
{
color: red;
}

.mycomboStyle
{
	width : 125px !important;
}
   </style> 
 
  <script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
  </script>

	<%
	  ArrayList typeList = new ArrayList();
	  ArrayList dataList     = new ArrayList();

	  typeList = (ArrayList)request.getAttribute("typeList");
	  dataList     = (ArrayList)request.getAttribute("dataList");
	  String fromdate = CommonUtility.checkNullObj(request.getAttribute("fromdate"));
	  String todate   = CommonUtility.checkNullObj(request.getAttribute("todate"));

	 %>
  
<script>

$(document).ready( function()
		{
	
	<%
    String ua = request.getHeader( "User-Agent" );
    boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
    %>
	  $('#resultdata').DataTable( {
		 

			    scrollY: "200px",
		        scrollX:   true,
		          iDisplayLength: 25 , 
		        scrollCollapse: true,
		        fixedColumns:   {
		            leftColumns: 2
		        },
		       
 <% if( isMSIE ){ %> 
		        "bSort": false, 
		        "paging":   false,
		        "ordering": false,
		        "info":     false,
		        "bFilter": false

 <%}%>

	    } );
	  
	  //to get todays date
	    var d = new Date();
	    var month = d.getMonth()+1;
	    var day = d.getDate();
	    var output = (day<10 ? '0' : '') + day+ '/' +(month<10 ? '0' : '') + month + '/' +d.getFullYear();
	   //end 
	   
	$('#fromdate').datetimepicker(
			{
				dayOfWeekStart : 1,
				lang:'en',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
			 maxDate:output, 
				
			});
			
	$('#todate').datetimepicker(
			{
				dayOfWeekStart : 1,
				lang:'en',
				formatTime:'H:i',
				formatDate:'d/m/Y',
				format:'d/m/Y',
				theme:'',
				step:05,
				timepicker:false,
				 maxDate:output, 
			});
			
	
	
	 
				 $("#raiseissueButID").click(function ()
							{
					  if ($('#fromdate').val()=='')
		                {
		                    alert("Please Select From date");
		                    $('#fromdate').focus();
		                    return false;
		                }
					 else if ($('#todate').val()=='')
		                {
		                    alert("Please Select To Date");
		                    $('#todate').focus();
		                    return false;
		                }
					 
					 else{
					 
					    document.form_issue.target="_self";
						document.form_issue.action="<%=request.getContextPath()%>/DistrictWiseGrievanceStatus.do";
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
</head>
<body onload="showData();">
 
<div >
   <div class="panel-group" id="accordion">
		<div class="panel panel-primary" id="searchformforid" style="width:90%;margin:0px auto;">
			<div class="panel-heading"  style="cursor: pointer;">
		             <h4 class="panel-title">
									District Wise Issue Tracking Dashboard
					 </h4>
		     </div>
				       
				              <div class="panel-body">
							              <form class="form-inline" id="form_issue" name="form_issue" method="post" >
									           
										             <div>
													         <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">From Date</div>
															       <input type="text" name="fromdate" id="fromdate" value="<%=fromdate%>" class="form-control">
															 </div>
															  <div class="input-group" >
															      <div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">To Date</div>
															       <input type="text" name="todate" id="todate" value="<%=todate%>" class="form-control">
															 </div>
														
														
									                   <button type="button" id="raiseissueButID" class="btn btn-success"><b>Submit</b></button>
										           </div> 
										  </form>
					            </div>
					           
			       
			    </div>
			    <br>
		<% if(dataList!=null && dataList.size()>0)
 { %>
 <div style="width:95%;margin:0px auto;">

 <div style=" text-align: center;margin-left: auto;margin-right: auto; ">
 <a href="<%=request.getContextPath()%>/DistrictWiseGrievanceStatus.xls?mode=excelNew&fromdate=<%=fromdate%>&todate=<%=todate%>">
 <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
 </a> <br></div> 


  <table id="resultdata" class="table table-striped table-bordered" >

    <thead>
      <tr>
       <td  rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>

        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
        
        
 <% if(typeList!=null && typeList.size()>0)
	 
   {
	 int i=0;
     for(i=0;i<typeList.size();i++)
        {
     %>
        <td colspan="3" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=((ArrayList)typeList.get(i)).get(0) %></td>
       <%}
   } %> 
         
          <td colspan="3" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Total</td>
       </tr>
       
       
       
       
       <tr>
        <% if(typeList!=null && typeList.size()>0)
        {
	 		int i=0;
          for(i=0;i<typeList.size();i++)
          
			  {%>
	              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Pending</td>
	              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Approved</td>
	              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Rejected</td>
              <%}
       } %>  
                  <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Pending</td>
                  <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Approved</td>
                  <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Rejected</td> 
        
       </tr>
    </thead>
   
    <tbody>
    <%
	
	
     ArrayList innerList = new ArrayList();
     int loopCount;
     String style="";


     for(loopCount=0;loopCount<dataList.size();loopCount++)
	 { 
    	 innerList = (ArrayList)dataList.get(loopCount);
    	  
	 
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
       
         <td class="<%=style%>" style="border:#337ab7 1px solid;">
         
         <%if(innerList.get(1).toString().contains("ZZtotal")) 
         {
                out.write("Total");
         }
         else if(innerList.get(1).toString().contains("ZMisc"))
         { 
               out.write("Miscellaneous");
         }
         else
         { %>
                <%=innerList.get(1) %>
      <% }%>
      
         </td>
    
       <% if(typeList!=null && typeList.size()>0)
           {
	
    	   int h=0,g=2;
 
    	   for(h=0;h<typeList.size();h++)
    	   {%>
    
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%=innerList.get(g) %></td>
            <%g++; %>
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%=innerList.get(g) %></td>
            <%g++; %>
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%=innerList.get(g) %></td>
            <%g++; %>
            
         <%} %>   
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(g) %></td>
           <%g++; %>
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(g)%></td>
           <%g++; %>        
         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"><%=innerList.get(g) %></td>
           <%g++; %>
           
          <%} %>     
         
         
       </tr>
      <%
      }
		%>
		 </tbody>
    </table>
		</div>
      <%
      
       }else {
       %>
       <div style="height:275px;"><h4 align="center" style="color:red">No records found</h2></div>
       <%} %>     
			  </div>  
    </div>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datetimepicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
</body>
</html>
