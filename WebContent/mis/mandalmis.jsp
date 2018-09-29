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
ArrayList monthlist= new ArrayList();

 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));
 String monthName= CommonUtility.checkNullObject((String)request.getAttribute("monthName"));
 String MandalName= CommonUtility.checkNullObject((String)request.getAttribute("MandalName"));
 String month= CommonUtility.checkNullObject((String)request.getAttribute("month"));

 String monthDisable= CommonUtility.checkNullObject((String)request.getAttribute("monthDisable"));

 resultlist = (ArrayList)request.getAttribute("resultlist");
 monthlist = (ArrayList)request.getAttribute("monthlist");
 String resultMSG =  CommonUtility.checkNullObject((String)request.getAttribute("resultMSG"));
 String confirmButtonEnable =  CommonUtility.checkNullObject((String)request.getAttribute("confirmButtonEnable"));
 String updatButtonDisable =   CommonUtility.checkNullObject((String)request.getAttribute("updatButtonDisable"));
//System.out.println("monthDisable"+monthDisable+" ---  "+monthDisable.trim().length());


%>



<style>
/*  th, td { white-space: nowrap; }
    div.dataTables_wrapper {
        width: 1040px;
        margin: 0 auto;
    }
 
    div.ColVis {
        float: left;
    } 
 */

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
<div style="height:550px;">
<form   id="mandalwisemis" name="mandalwisemis" method="post">
<input type="hidden" id="mode" name="mode" value="unspecified">
			<div class="panel-group" id="accordion">
				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;text-align:center;">
				             <h4 class="panel-title">
				          			<b>Online MIS Details -- Mandal:<%=MandalName %>&nbsp;&nbsp;&nbsp;Month:<%=monthName %>&nbsp;&nbsp;&nbsp;Year:<%=Year %></b>
				      		</h4>
				        </div>
				        <div id="collapseTwo" class="panel-collapse ">
				            <div class="panel-body">	
				            
				            		<div class="row">
						            		<div class="col-md-2 ">	            	
											<div class="input-group " >
											<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Month<font color="red">*</font></div>
											<span id="monthlistspanid"> <%=ComboUtil.createStrComboBoxAuto("month", monthlist,month,"form-control mycomboStyle","",true,true,"")%></span>	
											</div>	
											</div>
																
											<div class="col-md-1" >
												<button type="button" id="MonthformSubmitbut" name="MonthformSubmitbut" onclick="monthubmit()" class="btn btn-success"><b>Submit</b></button>
											</div>   
   									</div>                                  		
				            </div>
				        </div>
				    </div>
			</div> 


<div class="col-md-6"> 

<% if(resultlist!=null && resultlist.size()>0)
 {%>	        
   
 <center> <%=resultMSG%></center>  
 
     <% if(!(Integer.parseInt(monthDisable)==0)) 
	{	
	%> 
    <table align="center">
   <%if(updatButtonDisable.equals("disabled"))
     { %>  
	 		<tr>
	 					<td>
        					<font style="color:green;font-size=30px;"> <b>You have Confirmed the details</b></font>
         				</td>
			</tr>
 	<%}else 
 	{%>
 				
	 				<tr>
	 					<td><button   id="confirmbut" name="confirmbut" class="btn btn-success" style="background-color:#337ab7;padding:5px;color:#fff;" <%=confirmButtonEnable%>>
        					 <b>Confirm</b></button>
         				 </td>
	 					<td>&nbsp;&nbsp;
	 						<input type="button" onclick="func_edit();" value="update" class="btn btn-success" <%=updatButtonDisable %>> 
	 	
	 					</td>
					 </tr>
	 			
 			
 	<%}%>
 	<%} %>
   </table>
  <table id="resultdata" class="table table-striped table-bordered">

	 <thead>
      
      <tr>
       	<td   style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Activity Name</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Target</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Achievement</td>
        <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Balance</td>
                
      </tr>
      
    </thead>    
    
   <tbody>
 
     <%   ArrayList innerList = new ArrayList();
     int loopCount=0;
     String style="";
     String id="";
  
   
    	  for(loopCount=0;loopCount<resultlist.size();loopCount++)
    		 { 
    		  innerList = (ArrayList)resultlist.get(loopCount);
 		
	 
			     if(loopCount%2==0)
			     {
			    	 
			        style="firstline";
			        }else{
			        style="secondline";
			     }
    	  %>
    	 
       <%  if(!id.equals(innerList.get(0))){%>
      
			       <tr>
			           <td colspan="5" class="" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;background-color:#819eb7;color:#fff;"><b><%=innerList.get(2) %></b>
			           </td> 
			       </tr> 
			             <%id=innerList.get(0).toString();}%>
			             
			         
			          	<tr>             
			         		<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:left;padding-left:2px;">        
			         
			        			 <%=(String)innerList.get(3).toString().trim()%>                
			                
			         		</td>
			         		<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">  
			        				 <input type="hidden"  id="tar<%=loopCount%>" name="tar<%=loopCount%>"   value="<%=innerList.get(4)%>" >
			         					<div class="text_box" id="tar_<%=loopCount%>"  name="tar_<%=loopCount%>" style="color:black; height:16px;">
			        						 <%=(int)Float.parseFloat((String)innerList.get(4).toString().trim())%>
			        					</div> 
			         		</td>
					         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">
							         <input type="hidden"  id="ach<%=loopCount%>" name="ach<%=loopCount%>"   value="<%=innerList.get(5)%>" >
							         
							         <%if(innerList.get(12).toString().trim().equalsIgnoreCase("Y"))
			          				{%>
			          					<%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>
							         <%}else if(innerList.get(13).toString().trim().equalsIgnoreCase("S"))
			          				{%> 
							         <input type="text" class="form-control " id="ach_<%=loopCount%>" name="ach_<%=loopCount%>" maxlength="8" style="width:80px;height:32px;" onkeypress="return NumbersOnly(event);" onkeyup=" balcount(<%=loopCount%>) " autocomplete="off" value=<%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>>
							        <%}else{ %>
							        	<font style="color:red">Not Confirmed By<br>Higher Level</font>
							        <%} %>
							 </td>
					         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">					          
					         <div class="text_box" id="bal_<%=loopCount%>"  name="bal_<%=loopCount%>" style="color:black; height:16px;">
					         		<%=(int)Float.parseFloat((String)innerList.get(6).toString().trim())%>
					         </div>
					           <input type="hidden"  id="misTarId<%=loopCount%>" name="misTarId<%=loopCount%>"  value=<%=innerList.get(7)%>>
					          </td>      
			       </tr>
		
           
      
      <%
      
      
	}%>
		 </tbody>
		 	
    </table>
	<%  
	//Table IF Close
    } //Result SET IF END
    else{%>
   				<table>   				
   					<tr>
	 					<td>
        					<font style="color:red;font-size=30px;"> <b>Please Select Month</b></font>
         				</td>
					</tr>
   				</table>	 
    <%} %>
	</div>
			 <div class="col-md-4">
			   <% if((Integer.parseInt(monthDisable.trim())==0))
	
    {   
	  
    %>
    	
    				<font style="color:red;text-align:center"><b>Please Confirm Earlier Month Activities</b><br>(OR)<br>
		    		<b>Activities Not Confirmed By Higher Level</b>
		    	
		    	</font>  
    			
		    	
    	    
    <%}%>
			 
        		 <div class="panel">
             			<div class="panel-heading"></div>
              				  <div class="panel-body">
						 
					           	 <div class="panel panel-primary">
					               	 <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-check"></span>
					                      	Instructions
					                    </h3>
					               	 </div>
					                <div class="panel-body" style="background-color: #eee;text-align: left;line-height: 25px;font-size: 14px; font-family:  Times New Roman;">
					                <ol>
	                                  <li>* Confirm button Enable only if SPMU Confirm all Activities </li>
	                                  <li>* Once you confirm, you cannot edit</li>
	                                </ol>
	                             </div>
	                           </div>
	                           </div>
      
                      </div>
          </div>
 
         
      </form> 
   </div>
</body>

<script type="text/javascript">
function balcount(id)	
{
	try{
		
		targetkey="tar_"+id;
		achievekey="ach_"+id;
		balkey="bal_"+id;
		
		target=$('#'+targetkey).text();;
		achieve=document.getElementById(achievekey).value;
		
		balance=target-achieve;			
			
		$('#'+balkey).html(balance);
	}
	catch(e)
	{
		alert(e);
	}
};

function func_edit()
{
	
	 if($("#month").val()=="-1")
		{
		 	alert("Please Select Month");
		   	return false;
		}
	
	try{
		/*Screen Locking Started */
  			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
  		    $('#processlayer').css({"display": "block","z-index":"110000"});
			/*Screen Locking Ended */		
				
			$("#mode").val("updateMandalTargetValues");	
			document.mandalwisemis.target="_self";
			document.mandalwisemis.action="<%=request.getContextPath()%>/mis.do?randomid="+Math.random();
			document.mandalwisemis.submit();
		}
		catch(e)
		{
			alert(e);
		}
};

function monthubmit()
 {	
		if($("#month").val()=="-1")
		{
		 	alert("Please Select Month");
		   	return false;
		}
 
  
			/*Screen Locking Started */
      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
      		    $('#processlayer').css({"display": "block","z-index":"110000"});
  			/*Screen Locking Ended */
			
			
            $("#mode").val("getDetails");
			document.mandalwisemis.action="<%=request.getContextPath()%>/mis.do?randomid="+Math.random();
			document.mandalwisemis.submit();

		
 }

$(document).ready(function()
	{ 
	
	
	
	$("#confirmbut").click(function(event)
			{
				if($("#month").val()=="-1")
				{
				 	alert("Please Select Month");
				   	return false;
				}
				if(confirm("Once Confirm You Can't Edit, Want To Submit The Request"))
				{
					/*Screen Locking Started */
	      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	      		    $('#processlayer').css({"display": "block","z-index":"110000"});
		  			/*Screen Locking Ended */
					
					//document.searchbynameform.target="_self";
		           $("#mode").val("confirmMethod");
					document.mandalwisemis.action="<%=request.getContextPath()%>/mis.do?randomid="+Math.random();
					document.mandalwisemis.submit();
				}
				else
				{
					return false;
				}				   
				
			});

	
	
	<%
    String ua = request.getHeader( "User-Agent" );
    boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
    %>
	  $('#resultdata').DataTable( {
		 
		  "bSort": false, 
			    scrollY: "300px",
		        scrollX:   false, 
		          iDisplayLength: 25 , 
		        scrollCollapse: true,
		        "paging" : false,
		        "bLengthChange": false,
		        "bFilter": false,		      
		         fixedColumns:   {
		            leftColumns: 2
		        } ,
		        "columnDefs": [{
		            "defaultContent": "-",
		            "targets": "_all"
		          }]
		       
 				<% if( isMSIE ){ %> 
		        "bSort": false, 
		        "paging":   false,
		        "bLengthChange": false,
		        "ordering": false,
		        "info":     false,
		        "bFilter": false

 				<%}%>
	  } );
	
	
			
	});
 	
 	

</script>

</html>
 <%}  catch(Exception e)   
 { e.printStackTrace(); 
 System.out.print(e);
 } %>	