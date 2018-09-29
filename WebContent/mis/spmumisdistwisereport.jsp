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
ArrayList monthyearlistcombo= new ArrayList();
ArrayList ranklist= new ArrayList();
ArrayList counts= new ArrayList();

ActiveActivityList = (ArrayList)request.getAttribute("activeactivityList");
ActivesubactList = (ArrayList)request.getAttribute("actsubactList");
 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));
 String monthName= CommonUtility.checkNullObject((String)request.getAttribute("monthName"));
 String MandalName= CommonUtility.checkNullObject((String)request.getAttribute("MandalName"));
 resultlist = (ArrayList)request.getAttribute("resultlist");
 ranklist = (ArrayList)request.getAttribute("ranklist");

 monthyearlistcombo = (ArrayList)request.getAttribute("monthyearlistcombo");
 String monthyear = CommonUtility.checkNullObject((String)request.getAttribute("monthyear"));
 
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
<form class="form-inline" role="form" id="spmumisform" name="spmumisform" method="post">
    	
	<div class="panel-group" id="accordion">
				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;text-align:center;">
				             <h4 class="panel-title">
				          			<b>Online MIS Details -- &nbsp;&nbsp;&nbsp;Financial Year:<%=Year %></b>
				      		</h4>
				        </div>
				        <div id="collapseTwo" class="panel-collapse ">
				            <div class="panel-body">	
				            
				            		<div class="row">
				            		
							
							<div class="col-md-2 ">
							<div class="input-group " >
									<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Month<font color="red">*</font></div>
							<span id="ActiveActListSPAN"> <%=ComboUtil.createStrComboBoxAuto("monthyear", monthyearlistcombo,monthyear,"form-control mycomboStyle","",true,true,"")%></span>
							</div></div>
							
							<div class="col-md-1" >
						<button type="button" id="districtwiseformSubmitbut" class="btn btn-success"><b>Submit</b></button>
							</div>
										

   
   </div>
                                  		
				            </div>
				        </div>
				    </div>

			</div> 
<div style="height:420px;margin:0px auto;">

<% if(resultlist!=null && resultlist.size()>0)
 {%>
 

  <%--<div style=" text-align: center;margin-left: auto;margin-right: auto; "> -->
 <%-- <%=districtheadingmsg%> --%>
<a href="<%=request.getContextPath()%>/spmudistmisreport.xls?mode=excelNew&monthyear=<%=monthyear%>">
<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
 </a>  
  
			 <table id="resultdata" class="table table-striped table-bordered" style="width:80%;align:center;">
		
			 <thead>
		      <tr>
		       <td  rowspan="3" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
		        <td rowspan="3" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
		        
		     <% if(ActiveActivityList!=null && ActiveActivityList.size()>0)
			 
		   {
			    int i,l=0;
		        for(i=0;i<ActiveActivityList.size();i++)
		        {    	
		            l= Collections.frequency(counts,((String)((ArrayList)ActiveActivityList.get(i)).get(0)));       
		        %>
		        <td colspan="<%=l*3%>" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=(String)(((ArrayList)ActiveActivityList.get(i)).get(1)) %></td>
		        <%}
		   } %> 
		  <td rowspan="3" style="background-color:#337ab7;padding:5px;color:#fff;border:#ddd 1px solid;" align="center">Master Rank</td>    
         </tr>
     <tr>
	        <% if(ActivesubactList!=null && ActivesubactList.size()>0)
	        {
		 		int i=0;
	            for(i=0;i<ActivesubactList.size();i++)
	          
				   {%>
		              <td colspan="3" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=(String)((ArrayList)ActivesubactList.get(i)).get(4) %></td>
	              <%}
	       } %>  
        
	       </tr>
	     <tr>
	        <% if(ActivesubactList!=null && ActivesubactList.size()>0)
	        {
		 		int i=0;
	            for(i=0;i<ActivesubactList.size();i++)
	          
				   {%>
		              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Target</td>
		               <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Achievement</td>	             
	              <td style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Rank</td>
	             
	              <%}
	       } %>  
        
	       </tr>       
	       
	       </thead>
	       <tbody>

       <%   ArrayList TarginnerList = new ArrayList();
            ArrayList rankinnerList = new ArrayList();
            ArrayList AchvinnerList = new ArrayList();
            /* 
            ArrayList Totalsumtargetlist = new ArrayList();            
            ArrayList Totalsumachievelist = new ArrayList();
            
            Totalsumtargetlist = (ArrayList)resultlist.get(resultlist.size()-3);
 	       Totalsumachievelist = (ArrayList)resultlist.get(resultlist.size()-1);
 	        */
            ArrayList rankylist = new ArrayList();
            
		     int loopCount=0;
		     String style="";
		     String id="";
		  int g=0;
		  int b=1;
		  int rankloop=0;
    	  for(loopCount=0, rankloop=0;loopCount<resultlist.size();loopCount++,rankloop++)
    		 { 
    		  
    		  if(rankloop<(int)(-1+resultlist.size()/3))
    		  {
    		  rankylist = (ArrayList)ranklist.get(rankloop);
    		  }
    		  TarginnerList = (ArrayList)resultlist.get(loopCount);
    		  rankinnerList  = (ArrayList)resultlist.get(loopCount+1);
    		  AchvinnerList = (ArrayList)resultlist.get(loopCount+2);
	       
    	
    		  
			     if(loopCount%2==0)
			     {
			        style="firstline";
			        }else{
			        style="secondline";
			     }
    	  %>
     
          
 
     <tr>
      <td class="<%=style%>" style="border:#337ab7 1px solid;"><%=b++ %></td>
       
      <td class="<%=style%>" style="border:#337ab7 1px solid;"><%if(TarginnerList.get(1).toString().contains("ZZZZ")) {out.write("Total");}else{ %><%=TarginnerList.get(1)%><% }%>
     </td>
     <%  int h=  ActivesubactList.size()+3;
    
     
          for(int i=3,x=0;i<h;i++,x++)
          
			  { 
			
			 if(TarginnerList.get(2).equals("T") || TarginnerList.get(2).equals("ST")) 
			 {
      
      if(((String)((ArrayList)ActivesubactList.get(x)).get(0)).equals("MISTARGET00010")){%>
      
		     <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(TarginnerList.get(i)==null) {%><%=g %><% }else{%> <%=Float.parseFloat((String)TarginnerList.get(i).toString().trim())%>(<%=Float.parseFloat((String)TarginnerList.get(i).toString().trim())*1.5%>) <% }%></td>
		
		<%}else{ %>
		
			     <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(TarginnerList.get(i)==null) {%><%=g %><% }else{%> <%=(String)TarginnerList.get(i).toString().trim()%> <% }%></td>
	
		<%} %>
		     
		      <%}if(AchvinnerList.get(2).equals("A") || AchvinnerList.get(2).equals("SA")) {
		      
		       
      if(((String)((ArrayList)ActivesubactList.get(x)).get(0)).equals("MISTARGET00010")){%>
      
		     <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(AchvinnerList.get(i)==null) {%><%=g %><% }else{%> <%=Float.parseFloat((String)AchvinnerList.get(i).toString().trim())%>(<%=Float.parseFloat((String)AchvinnerList.get(i).toString().trim())*1.5%>) <% }%></td>
		
		<%}else{ %>
		
		  <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(AchvinnerList.get(i)==null) {%><%=g %><% }else{%> <%=(String)AchvinnerList.get(i).toString().trim()%> <% }%></td>
          
		<%} %>
		      
		      
		      
		      
            
            <%}if(rankinnerList.get(2).equals("R") || rankinnerList.get(2).equals("SR")) {%>
           
            <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(rankinnerList.get(i)==null || rankinnerList.get(i).equals("0")) {%>-<% }else{%> <%=((String)rankinnerList.get(i).toString().trim())%> <% }%></td>
           
             <%}
			 
			 
			  }
			  
          if(rankloop<(int)(-1+resultlist.size()/3))
		  {
			  %>
          <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;"><%if(rankylist.get(3)==null ) {%>-<% }else{%> <%=((String)rankylist.get(3).toString().trim())%> <% }%></td>
             <%}else { %>
              <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;">-</td>
          <%} %>
       </tr>
     
      
      <% loopCount = loopCount + 2;
           } //for loop close
		%>

		 </tbody>
    </table>	
		
		<%} //else if close 
		
       else if(monthyear.equals("") || monthyear.equals("-1")){
	     %>
	        <table  width="90%" class="table table-hover table-bordered table-responsive ">
	   
	     <tr>
	     <td height="15" align="center" valign="middle" class="Row" colspan="18">
				   Please select month from above dropdown. 
					 
		 	</td> 
		</tr>
		</table>
	    <%
	      }  else{
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
		</div>
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
			
 		if($("#monthyear").val()=="" || $("#monthyear").val()=="-1")
		   {
			alert("Please select Month");
			$("#monthyear").focus();
			
			 event.preventDefault();
			event.stopPropagation();
			return false;
		    }
	
        else{
						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
						
						//document.searchbynameform.target="_self";

						document.spmumisform.action="<%=request.getContextPath()%>/spmudistmisreport.do?&randomid="+Math.random();
						document.spmumisform.submit();
    	      				
		}	
			});

		});
	
	

</script>
</html>
 <%}  catch(Exception e)   
 { e.printStackTrace(); 
 System.out.print(e);
 } %>	