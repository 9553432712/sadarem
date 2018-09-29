<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.tcs.sadarem.util.ComboUtil,com.tcs.sadarem.util.CommonUtility,java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SADAREM :: MIS </title>
 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.min.css"/>
      <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
	    

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.fixedColumns.min.js"></script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
					<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
					<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
				<![endif]-->
			<!-- 	newEducationWiseDetails -->
 <%try{ %>	
			
<%
int recordCount = 0;
ArrayList resultlist= new ArrayList();
ArrayList ActiveActivityList= new ArrayList();
ArrayList ActivesubactList= new ArrayList();
ArrayList counts= new ArrayList();

ActiveActivityList = (ArrayList)request.getAttribute("activeactivityList");
ActivesubactList = (ArrayList)request.getAttribute("actsubactList");
 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));
 String monthName= CommonUtility.checkNullObject((String)request.getAttribute("monthName"));
 String MandalName= CommonUtility.checkNullObject((String)request.getAttribute("MandalName"));
 resultlist = (ArrayList)request.getAttribute("resultlist");

 String message = CommonUtility.checkNullObject((String)request.getAttribute("message"));
 
 ArrayList DistrictList 	= new ArrayList();
 DistrictList = (ArrayList)request.getAttribute("DistrictList");
 String district=CommonUtility.checkNullObj(request.getAttribute("district"));
 for(int i=0;i<ActivesubactList.size();i++)
 {
 	counts.add(((ArrayList)ActivesubactList.get(i)).get(1));
 }
%>

<style>




</style>

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


/* Process Layer Started */
     	
     	#processlayer
			{
				text-align: center;
				position: fixed;
				margin-right: -150px;
				margin-top: -75px;
				right: 50%;
				top: 50%;
				z-index: 99999;
				width:300px;
				display: none;
			}
			
			#blocklayer
			{
				display: none;
				position: fixed;
				left: 0;
				top: 0;
				bottom:0;
				right:0;
				background: #ECF1EF;
			}
     	
     /* 	 Process Layer Ended  */
     	

.panel-heading a:after 
{
    font-family:'Glyphicons Halflings';
    content:"\e114";
    float: right;
    color: grey;
}
.panel-heading a.collapsed:after {
    content:"\e080";
}

	.bs-example{
		margin: 20px;
	}
	
.errmsg
{
color: red;
}

.mycomboStyle
{
	width : 125px !important;
}
.col-sm-12 {
    width: 98%;
}
</style>

</head> 
<body>
<h4 style="color:#337ab7;text-align: center;margin-top:3px;margin-bottom:15px;"><u><b>Online MIS district wise target Details --Financial Year:<%=Year %></b></u></h3>
<form class="form-inline" role="form" id="spmumisform" name="spmumisform" method="post">
    	

<% if(resultlist!=null && resultlist.size()>0)
 {%>

  
			 <table id="resultdata" class="table table-striped table-bordered" style="width:80%;align:center;">
		
			 <thead>
		      <tr>
		       <td  rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
		        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
		     <% if(ActiveActivityList!=null && ActiveActivityList.size()>0)
			 
		   {
			    int i,l=0;
		        for(i=0;i<ActiveActivityList.size();i++)
		        {    	
		            l= Collections.frequency(counts,((String)((ArrayList)ActiveActivityList.get(i)).get(0)));       
		        %>
		        <td colspan="<%=l %>" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=(String)(((ArrayList)ActiveActivityList.get(i)).get(1)) %></td>
		        <%}
		   } %>    
         </tr>
     <tr>
	        <% if(ActivesubactList!=null && ActivesubactList.size()>0)
	        {
		 		int i=0;
	            for(i=0;i<ActivesubactList.size();i++)
	          
				   {%>
		              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=(String)((ArrayList)ActivesubactList.get(i)).get(4) %></td>
	              <%}
	       } %>  
        
	       </tr>
	       </thead>
	       <tbody>

       <%   ArrayList innerList = new ArrayList();
		     int loopCount=0;
		     String style="";
		     String id="";
		  int g=0;
   
    	  for(loopCount=0;loopCount<resultlist.size();loopCount++)
    		 { 
    		  innerList = (ArrayList)resultlist.get(loopCount);
 		       //System.out.print("vv"+innerList);
	 
			     if(loopCount%2==0)
			     {
			        style="firstline";
			        }else{
			        style="secondline";
			     }
    	  %>
     
          
 
     <tr>
      <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=loopCount+1 %></td>
       
      <td class="<%=style%>" style="border:#337ab7 1px solid;"><%if(innerList.get(0).toString().contains("ZZ_Total")) {out.write("Total");}else{ %><%=innerList.get(0)%><% }%>
     </td>
     <%  int h=  ActivesubactList.size()+2;
    
     
          for(int i=2;i<h;i++)
          
			  {%>
      
		     <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(innerList.get(i)==null) {%><%=g %><% }else{%> <%=(int)Float.parseFloat((String)innerList.get(i).toString().trim())%> <% }%></td>
           
             <%}%>
       </tr>
      
      <%
      
           } //for loop close
		%>
		 </tbody>
    </table>	
		
		<%} //else if close 
		
       else {
	     %>
	        <table  width="90%" class="table table-hover table-bordered table-responsive ">
	   
	     <tr>
	     <td height="15" align="center" valign="middle" class="Row" colspan="18">
				   Some error occured!! try again by refreshing the page. 
					 
		 	</td> 
		</tr>
		</table>
	    <%
	      }%>
		
		<!-- <input type="hidden" id="mode" name="mode" value="getSPMUleveltargetsdetails"> -->
		</form>
</body>

<script type="text/javascript">

$(document).ready(function()
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
	        "bLengthChange": false,
	        "bFilter": false,
	        fixedColumns:   {
	            leftColumns: 2
	        } ,
	       
<% if( isMSIE ){ %> 
	        "bSort": false, 
	        "paging":   false,
	        "bLengthChange": false,
	        "ordering": false,
	        "info":     false,
	        "bFilter": false

 <%}%>
	  } );
	
 	$("#districtwiseformSubmitbut").click(function( event )
			{
			
 		   
						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
						
						//document.searchbynameform.target="_self";

						document.spmumisform.action="<%=request.getContextPath()%>/spmumis.do?&randomid="+Math.random();
						document.spmumisform.submit();
    	      				
					
			});

		});
	
	

</script>
</html>
 <%}  catch(Exception e)   
 { e.printStackTrace(); 
 System.out.print(e);
 } %>	