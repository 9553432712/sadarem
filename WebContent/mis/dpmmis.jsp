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

ArrayList mandallistcombo= new ArrayList();
ArrayList monthyearlistcombo= new ArrayList();


 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));
 String monthName= CommonUtility.checkNullObject((String)request.getAttribute("monthName"));
 String MandalName= CommonUtility.checkNullObject((String)request.getAttribute("MandalName")); 
 String districtname= CommonUtility.checkNullObject((String)request.getAttribute("districtname")); 
 resultlist = (ArrayList)request.getAttribute("resultlist");
 mandallistcombo = (ArrayList)request.getAttribute("mandallistcombo");
 monthyearlistcombo = (ArrayList)request.getAttribute("monthyearlistcombo");
 String monthyear = CommonUtility.checkNullObject((String)request.getAttribute("monthyear"));
 String mandal = CommonUtility.checkNullObject((String)request.getAttribute("mandal"));
 String TextBoxDisable = CommonUtility.checkNullObject((String)request.getAttribute("TextBoxDisable"));
 String resultMSG = CommonUtility.checkNullObject((String)request.getAttribute("resultMSG"));

 String totaltargetsum = CommonUtility.checkNullObject((String)request.getAttribute("totaltargetsum"));

%>

<style>




</style>

<style>
.dataTables_scrollBody{

 height: 300px !important; 
}

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
.col-sm-12 {
    width: 98%;
}
	.bs-example{
		margin: 20px;
	}
		.iframe{
    overflow:hidden;
}
.errmsg
{
color: red;
}

.mycomboStyle
{
	width : 125px !important;
}
.dataTables_filter {
display: none; 
}
</style>

</head> 
<body>
<div style="height:550px;" >
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
									<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Mandal<font color="red">*</font></div>
									<span id="districtSPANID1"> <%=ComboUtil.createStrComboBoxAuto("mandal", mandallistcombo,mandal,"form-control mycomboStyle","",true,true,"")%></span>
									</div>	
							</div>
							
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

<% if(resultlist!=null && resultlist.size()>0)
 {%>
 	<%-- <center><font color='green'><b><%=status %></b></font></center> --%>
 	<input type="hidden" id="listsize" name="listsize" value="<%=resultlist.size() %>">
 	<div class="row">
  <div class="col-md-6" style="align:center;">
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
    		  int g=0;
    		  innerList = (ArrayList)resultlist.get(loopCount);
 		//System.out.print("vv"+innerList);
	 
     if(loopCount%2==0)
     {
    	 
        style="firstline";
        }else{
        style="secondline";
     }
    	  %>
    	 
       <%  if(!id.equals(innerList.get(0))){%>
      
			       <tr>
			       <td style="display:none;"></td>
			           <td colspan="4" class="" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;background-color:#819eb7;color:#fff;"><b><%=innerList.get(2) %></b>
			           </td> 
			               <td style="display:none;"></td>
			                   <td style="display:none;"></td>
			       </tr> 
			             <%
			             id=innerList.get(0).toString();}%>
			             
			         
			          	<tr>             
			         		<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:left;padding-left:2px;">        
			         
			        			 <%=innerList.get(3)%>                
			                
			         		</td>
			         		<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">  
			         		 
			         	<input type="hidden" id="target_<%=loopCount%>"  name="target_<%=loopCount%>"  value="<%=innerList.get(4)%>">
                         <div class="text_box" id="target<%=loopCount%>"  name="target<%=loopCount%>" style="color:black; height:16px;" value="<%=innerList.get(4)%>">
				                         <%if(innerList.get(4)==null) {%>
				                         <%=g %> <%}else{ %>
							        	 <%=(int)Float.parseFloat((String)innerList.get(4).toString().trim())%>
							        	 <%} %>
			        	</div> 
			         		</td>
			         		
					         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">
							        <input type="hidden" class="form-control " id="achieve<%=loopCount%>" name="achieve<%=loopCount%>" value="<%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>">    
							         <%if(innerList.get(13).toString().trim().equalsIgnoreCase("Y"))
			          				{%>
			          				
			          					     <%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>
			          					     
							         <%}else if(innerList.get(12).toString().trim().equalsIgnoreCase("Y") && (innerList.get(13).toString().trim().equalsIgnoreCase("S") || innerList.get(13).toString().trim().equalsIgnoreCase("N") ))
			          				  {%> 
			          				  
                                      <input type="text" class="form-control " id="achieve_<%=loopCount%>" name="achieve_<%=loopCount%>" maxlength="8" style="width:80px;height:32px;" onkeypress="return NumbersOnly(event);" onkeyup="balcount(<%=loopCount%>);" autocomplete="off" 
                                      value="<%if(innerList.get(5)==null) {%><%=g %> <%}else{ %><%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%><%} %>" maxlength="8" <%=TextBoxDisable%>>
                                      
							        <%}else{ %>
							        
							        	<font style="color:red">APM not yet confirmed!</font>
							        	
							        <%} %>
							 </td>
							 
							 
					         <td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;">					          
					         <div class="text_box" id="balance_<%=loopCount%>"  name="balance_<%=loopCount%>" style="color:black; height:16px; ">
					         <%if(innerList.get(6)==null) {%>
                                <%=g %> 
                                <%}else{ %>
                                
			        	       <%=(int)Float.parseFloat((String)innerList.get(6).toString().trim())%>
			        	       
			        	          <%} %>
					         </div>
					           <input type="hidden"  id="misTarId_<%=loopCount%>" name="misTarId_<%=loopCount%>"  value=<%=innerList.get(7)%>>
					          </td>      
			       </tr>
		
           
      
      <%
      
      }
	%>
		 </tbody>
		 	
    </table>
		</div>
		<div><span id="resultMSG"><%=resultMSG %></span></div>
			<%if(!TextBoxDisable.equals("")) {%>	
		<br> <div><span id="resultMSG"><font color="green"><b>Details has been confirmed!</b></font></span></div> 
		<%}  else if(Integer.parseInt(totaltargetsum)==0){%>
		<br> <div><span id="resultMSG"><font color="red"><b>Please complete earlier month activities.After that <br>you can proceed here!
		<br>(OR)<br>Targets & achievments not yet confirmed at Mandal level<br>for the selected month to proceed here. 
		</b>
		</font></span></div> 
		<%} %>
	 <div class="col-md-4">
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
					                  <li>* You are requested to give achievements for all activities. </li>
	                                  <li>* Please check thoroughly before you confirm it. </li>
	                                  <li>* Once you confirm, you cannot edit.</li>
	                                  <li>* Target & achievement values will be displayed only after it was confirmed in mandal level. </li>
	                                   </ol>
	                             </div>
	                           </div>
	                           </div>
      
                      </div>
          </div>
         <%if(innerList.get(12).toString().trim().equalsIgnoreCase("Y") && (innerList.get(13).toString().trim().equalsIgnoreCase("S") || innerList.get(13).toString().trim().equalsIgnoreCase("N")))
			 {%>		
				<div class="col-md-4" >
				<button type="button" style="background-color:#337ab7;padding:5px;color:#fff;align:right;"   id="confirmall" name="confirmall" class="btn btn-success">
		        <b>Confirm</b></button>
		                                  
		        <button type="button" style="background-color:#337ab7;padding:5px;color:#fff;align:right;"   id="saveall" name="saveall" class="btn btn-success">
		        <b>Update</b></button>
                      
                             </div>	<%} %>	
                             
		      <input type="hidden" id="districtid" name="districtid" value="<%=(String)(((ArrayList)resultlist.get(0)).get(0))%>">
		<%} //else if close 
		
		
		 else if(mandal.equals(""))
	     {	
	     %>
	      <table  width="90%" class="table table-hover table-bordered table-responsive ">
	   
	     <tr>
	     <td height="15" align="center" valign="middle" class="Row" colspan="18">
				     Please select any mandal in the above dropdown.
					 
		 	</td> 
		</tr>
		</table>
		 <%
		 }else {
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
	    <input type="hidden" id="mode" name="mode" value="unspecified">
		</form></div>
</body>

<script type="text/javascript">

function balcount(id)	
{
	try{

		targetkey="target"+id;
		achievekey="achieve_"+id;
		balkey="balance_"+id;
		
		/* target=document.getElementById(targetkey).value; */
		 target=$('#'+targetkey).text(); 
		achieve=document.getElementById(achievekey).value;
		

		
		balance=target-achieve;			
						
		$('#'+balkey).html(balance);
	}
	catch(e)
	{
		alert(e);
	}
};

$(document).ready(function()
		{ 
	  
	<%
    String ua = request.getHeader( "User-Agent" );
    boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
    %>
	  $('#resultdata').DataTable( {
		 
		  		 "bSort": false,  
			  scrollY: "300px", 
		         scrollX:   false,  
		         scrollCollapse: true, 
		         "paging" : false,
		        "bLengthChange": false,
		        "bFilter": false,	 	      
		     
		       
 <% if( isMSIE ){ %> 
		        "bSort": false, 
		        "paging":   false,
		        "bLengthChange": false,
		        "ordering": false,
		        "info":     false,
		        "bFilter": false

 <%}%>
	  });

	
 	$("#districtwiseformSubmitbut").click(function( event )
			{
 		
						 		if($("#mandal").val()=="" || $("#mandal").val()=="-1")
								{
									alert("Please select Mandal");
									$("#mandal").focus();
									
									 event.preventDefault();
									event.stopPropagation();
									return false;
								}
								else if($("#monthyear").val()=="" || $("#monthyear").val()=="-1")
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
						$("#mode").val("unspecified");				
						document.spmumisform.action="<%=request.getContextPath()%>/dpmmis.do?randomid="+Math.random();
						document.spmumisform.submit();
    	      				
								}
			});
	
    $("#saveall").click(function( event )
  			{
  			
   		 
  						/*Screen Locking Started */
      		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
      		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
      	      			/*Screen Locking Ended */
  						
  						//document.searchbynameform.target="_self";
                          $("#mode").val("saveAchvsAtDPMLevel");
                        //document.spmumisform.target="_self";
  						document.spmumisform.action="<%=request.getContextPath()%>/dpmmis.do?randomid="+Math.random();
  						document.spmumisform.submit();
   		 		
  			});
	
	$("#confirmall").click(function( event )
			{
			
 		   if(confirm("Please check before doing this action.As once you confirm it you cannot EDIT."))
 			   {
						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
						
						//document.searchbynameform.target="_self";
                        $("#mode").val("confirmAchvsAtDPMLevel");
						document.spmumisform.action="<%=request.getContextPath()%>/dpmmis.do?randomid="+Math.random();
						document.spmumisform.submit();
 			   }			
					
			});
	
 
	
  
		});
function postRequest(strURL,textID) 
{
    
	var xmlHttp;
    if (window.XMLHttpRequest)  // Mozilla, Safari, ...
	 {
          xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)  // IE
	{
          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttp.onreadystatechange = function()
     {
	    
    	if (xmlHttp.readyState == 1) 
        {
    		updatepage("<center><img src='<%=request.getContextPath()%>/images/loading.gif' width='15' height='15'></center>",textID);
        }
	    else if(xmlHttp.readyState == 2)
	    {
	    	updatepage('Wait..',textID);
	    }
	    else if(xmlHttp.readyState == 3)
	    {
	    	updatepage('Please Wait..',textID);
	    }
	    else if (xmlHttp.readyState == 4) 
        {
	    	var errorCode = xmlHttp.getResponseHeader('errorCode');
	    
	    	if(errorCode=="" || errorCode==null ) // Check null to for mozilla
	    	{
        		updatepage(xmlHttp.responseText,textID);
        		
	    	}
	    	else
	    	{
	    		$('#errorMsg').html(errorCode).fadeIn(100);
	    		location.replace(window.location);
	    	}
    	}
	};
	xmlHttp.send(strURL);
}

function updatepage(msg,id)
{		
	if(msg!="" && msg!="null")
	{
		document.getElementById(id).innerHTML=msg;
	}
}
</script>

</html>
 <%}  catch(Exception e)   
 { e.printStackTrace(); 
 System.out.print(e);
 } %>	