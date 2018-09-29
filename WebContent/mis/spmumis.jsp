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
      <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
 
	    

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
		 ActivesubactList = (ArrayList)request.getAttribute("actsubactlist2");
		 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));

		 resultlist = (ArrayList)request.getAttribute("resultlist");
		 String status = CommonUtility.checkNullObject(((String)request.getAttribute("status")));
		 String Activesubact = CommonUtility.checkNullObject(((String)request.getAttribute("Activesubact")));
		 
		 String Activeact = CommonUtility.checkNullObject(((String)request.getAttribute("Activeact"))); 
		 String subactname = CommonUtility.checkNullObject(((String)request.getAttribute("subactname")));
		 String TextBoxDisable= CommonUtility.checkNullObject((String)request.getAttribute("TextBoxDisable"));
		 String ConfirmButtonEnable= CommonUtility.checkNullObject((String)request.getAttribute("ConfirmButtonEnable"));
		 String resultMSG= CommonUtility.checkNullObject((String)request.getAttribute("resultMSG"));
		 
			 ArrayList DistrictList 	= new ArrayList();
			 DistrictList = (ArrayList)request.getAttribute("DistrictList");
			 String district=CommonUtility.checkNullObj(request.getAttribute("district"));
			
			 for(int i=0;i<ActivesubactList.size();i++)
			 {
			 	counts.add(((ArrayList)ActivesubactList.get(i)).get(1));
			 }
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
<form class="form-inline" role="form" id="spmumisform" name="spmumisform" method="post">
<div class="row">
	<div class="col-md-12">
    		<div class="panel-group" id="accordion">
				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div class="panel-heading" data-toggle="collapse" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;text-align:center;">
				             <h4 class="panel-title">
				          			<b>Online MIS Details -- &nbsp;&nbsp;&nbsp;Financial Year: <%=Year %></b>
				      		</h4>
				        </div>
				        <div id="collapseTwo" class="panel-collapse ">
				            <div class="panel-body">	
								<div class="row">            	
									<div class="input-group">
									<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">District<font color="red">*</font></div>
									<span id="districtSPANID1"> <%=ComboUtil.createStrComboBoxAuto("district", DistrictList,district,"form-control mycomboStyle","",true,true,"")%></span>
									</div>	 
							 
									<div class="input-group">
										<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Activity<font color="red">*</font></div>
										<span id="ActiveActListSPAN"> <%=ComboUtil.createStrComboBoxAuto("Activeact", ActiveActivityList,Activeact,"form-control mycomboStyle","",true,true,"")%></span>
									</div> 
						 
									<div class="input-group">
										<div class="input-group-addon" style="background-color: #eee; color:#000;font-weight:bold;">Sub Activity<font color="red">*</font></div>
										<span id="ActiveSubActListSPAN"> <%=ComboUtil.createStrComboBoxAuto("Activesubact", ActivesubactList,Activesubact,"form-control mycomboStyle","",true,true,"")%></span>
									</div>    
										<button type="button" id="districtwiseformSubmitbut" class="btn btn-success"><b>Submit</b></button> 
 								  </div> 
				            </div>
				        </div>
				    </div> 
			</div> 
		</div>
	</div>
<% if(resultlist!=null && resultlist.size()>0)
 {
 %>
 	<center><font color='green'><b><%=status %></b></font></center>
 	<input type="hidden" id="listsize" name="listsize" value="<%=resultlist.size() %>">
 	<div class="row">
  <div class="col-md-6" style="align:center;">
			 <table id="resultdata" class="table table-striped table-bordered">
			<% ArrayList innerList = new ArrayList();
			innerList =(ArrayList)resultlist.get(0);
		
			%>
			 <thead>
		      <tr>
		       <td   rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
		        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal</td>
		        <td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><%=subactname %><br>Target
	 <input type="hidden" class="form-control " id="MisTargetId" name="MisTargetId"  value="<%=innerList.get(4)%>" >	        
		        
		        </td>
		        </tr>
		        <tr>
	       <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">View	        		        
		        </td>
		          <td  style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Edit	        		        
		        </td>
         </tr>

	       </thead>
	       <tbody>
         <%  
		     int loopCount=0;
		     String style="";
		     String id="";
		     int g=0;
		  
   String mandalList="";
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
 
	<td class="<%=style%>" style="border:#337ab7 1px solid;"><%=innerList.get(3) %></td>
		<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"> 
	<% 
   	
		   if(innerList.get(5)==null){%>
		   <%=g %>
		   <%  
		   }else{%>
		   
	   <%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>
	   <% }
   
	 %>
   <input type="hidden" class="form-control " id="MandalId<%=loopCount%>" name="MandalId<%=loopCount%>"  value="<%=innerList.get(2)%>"  >
  
   
   </td> 	  
	<td class="<%=style%>" style="border:#337ab7 1px solid;text-align:right;"> 
	<%if(TextBoxDisable.equals("")) {%>	
   <input type="text" class="form-control " id="Target<%=loopCount%>" name="Target<%=loopCount%>"  style="width:80px;height:32px;" onkeypress="return NumbersOnly(event);"  autocomplete="off" value="<%if(innerList.get(5)==null){%><%=g %><%}else{ %><%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%><%} %>"  maxlength="8" <%=TextBoxDisable%> >
   <%} else{
   	
		   if(innerList.get(5)==null){%>
		   <%=g %>
		   <%  
		   }else{%>
		   
	   <%=(int)Float.parseFloat((String)innerList.get(5).toString().trim())%>
	   <% }
   }
	 %>
   <input type="hidden" class="form-control " id="MandalId<%=loopCount%>" name="MandalId<%=loopCount%>"  value="<%=innerList.get(2)%>"  >
  
   
   </td>      	  
       </tr>
      
      <%
      
           } //for loop close
		%>
		
		 </tbody>
    </table>	
		</div>
		<div><span id="resultMSG"><%=resultMSG %></span></div>
			<%if(!TextBoxDisable.equals("")) {%>	
		<br><div><span id="resultMSG"><font color="green"><b>Details has been confirmed!</b></font></span></div>
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
					                  <li>* You are requested to give Financial year wise target for all mandals activity wise. </li>
	                                  <li>* Please check thoroughly before you confirm it. </li>
	                                  <li>* Once you confirm, you cannot edit.</li>
	                                  <li>* These targets are distributed accordingly every month to all mandals.</li>
	                                  <li>* Eg:-If Financial Target=120 then monthly target=120/12=10.So 10 will be target for 1 month and Calculation follows accordingly</li>
	                                </ol>
	                             </div>
	                           </div>
	                           </div>
      
                      </div>
          </div>
          								<%if(TextBoxDisable.equals("")) {%>		
										<div class="col-md-4" >
						<button type="button" style="background-color:#337ab7;padding:5px;color:#fff;align:right;"   id="confirmall" name="confirmall" class="btn btn-success">
                                  <b>Confirm</b></button>
                                  
                                  <button type="button" style="background-color:#337ab7;padding:5px;color:#fff;align:right;"   id="saveall" name="saveall" class="btn btn-success">
                                  <b>Save</b></button>
                      
                             </div>	<%} %>	
		      <input type="hidden" id="districtid" name="districtid" value="<%=(String)(((ArrayList)resultlist.get(0)).get(0))%>">
		<%} //else if close 
		
		
		 else if(district.equals(""))
	     {	
	     %>
	     	<div class="alert alert-info">
	     		<b>Please select any district in the above dropdown.</b>
	     	</div>
	     	 
		 <%
		 }
		 else 
		 {
	     %>
	     	<div class="alert alert-danger">
	     		<b>Some error occured!! try again by refreshing the page. </b>
	     	</div>
	      
	    <%
	      }
	    %>
	    <input type="hidden" id="mode" name="mode" value="unspecified">
		</form> 
</body>



	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 

<script type="text/javascript">
$(document).ready(function()
		{ 
	//alert($('#Activeact').val());
	$('#Activeact').change(function()
			{	
		//alert($('#Activeact').val());
				postRequest("<%=request.getContextPath()%>/ajax.do?action=loadsubactlist&Activeact="+ $('#Activeact').val()+"&Activesubact="+ $('#Activesubact').val()+"&randomid="+Math.random(),"ActiveSubActListSPAN");
				
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
		       
 <% if( isMSIE ){ %> 
		        "bSort": false, 
		        "paging":   false,
		        "bLengthChange": false,
		        "ordering": false,
		        "info":     false,
		        "bFilter": false

 <%}%>
	  } );
	
      $("#saveall").click(function( event )
  			{
  			
   		 
  						/*Screen Locking Started */
      		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
      		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
      	      			/*Screen Locking Ended */
  						
  						//document.searchbynameform.target="_self";
                          $("#mode").val("savetargets");
                        //document.spmumisform.target="_self";
  						document.spmumisform.action="<%=request.getContextPath()%>/spmumis.do?randomid="+Math.random();
  						document.spmumisform.submit();
   		 		
  			});
	  
 	$("#districtwiseformSubmitbut").click(function( event )
			{
 		
						 		if($("#district").val()=="" || $("#district").val()=="-1")
								{
									alert("Please select District");
									$("#district").focus();
									
									 event.preventDefault();
									event.stopPropagation();
									return false;
								}
								else if($("#Activeact").val()=="" || $("#Activeact").val()=="-1")
								{
									alert("Please select Activity");
									$("#Activeact").focus();
									
									 event.preventDefault();
									event.stopPropagation();
									return false;
								}
								else if($("#Activesubact").val()=="" || $("#Activesubact").val()=="-1")
								{
									alert("Please select Sub activity");
									$("#Activesubact").focus();
									
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
						
						document.spmumisform.action="<%=request.getContextPath()%>/spmumis.do?randomid="+Math.random();
						document.spmumisform.submit();
    	      				
								}
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
                        $("#mode").val("confirmallinserttargetsmandalwise");
						document.spmumisform.action="<%=request.getContextPath()%>/spmumis.do?randomid="+Math.random();
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