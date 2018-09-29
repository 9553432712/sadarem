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
ArrayList resultlist= new ArrayList();
ArrayList counts= new ArrayList();
	String monthName =CommonUtility.checkNullObject((String)request.getAttribute("monthName"));
 String Year= CommonUtility.checkNullObject((String)request.getAttribute("Year"));
 resultlist = (ArrayList)request.getAttribute("resultlist");

 ArrayList DistrictList 	= new ArrayList();
 DistrictList = (ArrayList)request.getAttribute("DistrictList");
 String district=CommonUtility.checkNullObj(request.getAttribute("district"));
 String distName=CommonUtility.checkNullObj(request.getAttribute("distName"));
 ArrayList mandalList= new ArrayList();
 mandalList=(ArrayList)request.getAttribute("mandalList");
 
 String resultMSG=CommonUtility.checkNullObject((String)request.getAttribute("resultMSG"));
 String ConfirmButtonEnable= CommonUtility.checkNullObject((String)request.getAttribute("ConfirmButtonEnable"));
 String TextBoxDisable= CommonUtility.checkNullObject((String)request.getAttribute("TextBoxDisable"));
 
//System.out.print("distName--"+distName);
%>


<style>
	p.pos_fixed
	{
		position:fixed;
		top:300px;
		right:30%;
	}
.firstline{
background-color:white;
}
.secondline{
background-color:#e2ebf4;
} 

.btn
{
width:100%!important; 
 padding : 5px !important;
 cursor:pointer!important;
 position:relative;
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
<script type="text/javascript">
$(document).ready(function ()
		{
	$('input').bind('keypress', function (event) {
	    var regex = new RegExp("^[0-9]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	
	<%
    String ua = request.getHeader( "User-Agent" );
    boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
    %>
	 
	
		}
);
</script>
<body>
<form class="form-inline" id="mispupulation" name="mispupulation" method="post">
<% if(((ArrayList)mandalList.get(0)).get(3).equals("N") || ((ArrayList)mandalList.get(0)).get(3).equals(""))
		 {%>
<p class="pos_fixed" align="center">
	<img id="up_button" src="<%=request.getContextPath()%>/images/arrow_up.png" title="Scroll Top"/><br/><br/>
													
	<input type="button" name="confirm" id="confirm" value="Confirm" class="btn btn-success"  style="height:30px;align:center;width:75px;font-weight:bold;" <%=ConfirmButtonEnable %> ><br/><br/>
	<input type="button" name="update" id="update" value="Save" class="btn btn-success" style="height:30px;width:75px;align:center;font-weight:bold;"><br/><br/>
	
	<img id="down_button" src="<%=request.getContextPath()%>/images/arrow_down.png" title="Scroll Down"/>
	</p>
	<% }%>
	<input type="hidden" id="mode" name="mode" value="">
    		<div class="panel-group" id="accordion">
				  
				    <div class="panel  panel-primary" id="districtwiseform">
				        <div class="panel-heading" data-toggle="" data-target="#collapseTwo" href="#collapseTwo" class="collapsed" style="cursor: pointer;text-align:center;">
				             <h4 class="panel-title">
				          			<b>Online MIS Population Details --District: <%=distName %> &nbsp;&nbsp;&nbsp;Financial Year:<%=Year %></b>
				      		</h4>
				        </div>
				        
				        </div>
				    </div>

			</div> 
			

<%
if(resultlist!=null && resultlist.size()>0)
 {
	//if(district=="-1" || district=="")
	 %>
		  <table id="" class="table table-striped table-bordered" style="width:50%;margin:0px auto;">
	 <thead>
      <tr>
       <td  rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">District</td>
        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Total Population</td>
        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Edit</td>
        
      </tr>      
    </thead>
    <%
    String style="";
    ArrayList innerList = new ArrayList();
    for(int loopCount=0;loopCount<resultlist.size();loopCount++)
    {
    	 innerList = (ArrayList)resultlist.get(loopCount);
    	if(loopCount%2==0)
        {
           style="firstline";
           }else{
           style="secondline";
        }
    	%>
   <tbody>
    <tr>  
    	<td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;">         
         <%=loopCount+1%>       
         </td>
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:left;padding-left:2px;">         
         <%=innerList.get(1)%>       
         </td>           
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:right;padding-left:2px;">         
         <%=innerList.get(2)%>       
         </td>
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;" > 
         <%if( innerList.get(3).equals("P")) 
		 {%>
		 	<a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/mispopulation.do?mode=districtWisePopulation&distName=<%=innerList.get(1)%>&district1=<%=innerList.get(0)%>&randomid=<%=Math.random()%>">
			      
         <input type="button"  value="Confirmed" class="btn btn-primary" id="" name="">    
         </a>  
         <%}else if( innerList.get(3).equals("N") ||  innerList.get(3).equals("")) { %> 
         <a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/mispopulation.do?mode=districtWisePopulation&distName=<%=innerList.get(1)%>&district1=<%=innerList.get(0)%>&randomid=<%=Math.random()%>">
			      
         <input type="button"  value="Edit" class="btn btn-info" id="" name="">    
         </a>  
         <%} else{%>
         <a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/mispopulation.do?mode=districtWisePopulation&distName=<%=innerList.get(1)%>&district1=<%=innerList.get(0)%>&randomid=<%=Math.random()%>">
			      
         <input type="button"  value="Info" class="btn btn-success" id="" name="">    
         </a> 
         <%} %> 
         </td> 
     </tr>
     </tbody>
      
     <%}//for loop End %>
      </table>
     <%} %>
   
     
     <% if(mandalList!=null && mandalList.size()>0)
		 {		 	
		 %>
		 <center><b><%=resultMSG %></b></center>
		 
	 <%if(((ArrayList)mandalList.get(0)).get(3).equals("P")) 
		 {%>
		 	<p align="center"><font color='green'><b>Population has been confirmed</b></font></p>
		 <%}%>	 
		 
		 <div class="row">
    <div class="col-md-7">
         <div class="panel">
             <div class="panel-heading"></div>
                <div class="panel-body">
		 
		 
		  <table id="resultdata" class="table table-striped table-bordered " style="width:50%;margin:0px auto;" >
	 <thead>
      <tr>
       <td  rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">S.No.</td>
        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Mandal Name</td>
        <td rowspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center">Enter Population</td>
      </tr>          
    </thead> <tbody>
    <%  
    int loopCount=0;
    String style="";
    ArrayList innerList = new ArrayList();
    for( loopCount=0;loopCount<mandalList.size();loopCount++)
    {
    	 innerList = (ArrayList)mandalList.get(loopCount);
    	if(loopCount%2==0)
        {
           style="firstline";
           }else{
           style="secondline";
        }
    	%>
  
    <tr>  
    	<td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;">         
         <%=loopCount+1%>
         <input type="hidden" name="manId_<%=loopCount%>"      id="manId_<%=loopCount%>" value="<%=innerList.get(0)%> ">
         </td>
         <td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:left;padding-left:2px;">         
         <%=innerList.get(1)%>       
         </td>
		 	<td class="<%=style%>" style="border:#337ab7 1px solid ;text-align:center;padding-left:2px;">
         <%if(innerList.get(3).equals("P")) 
		 {%>
		 	<%=innerList.get(2)%>
		 <%}
		 else
		 {%>
		 	         
        <input type="text" name="val_<%=loopCount%>"  id="val_<%=loopCount%>" maxlength="8" value="<%=innerList.get(2)%>" <%=TextBoxDisable%>>
		 
		<%} %>
		
         </td>
         
     </tr>
	
     <%}//for loop End %>
          </tbody>
	 </table>
	
	
	</div>
          </div>
    </div>
    <div class="col-md-4">
         <div class="panel">
             <div class="panel-heading"></div>
                <div class="panel-body">
						 
					            <div class="panel panel-primary">
					                <div class="panel-heading">
					                    <h3 class="panel-title">
					                        <span class="glyphicon glyphicon-check"></span>
					                      	Instruction
					                    </h3>
					                </div>
					                <div class="panel-body" style="background-color: #eee;text-align: left;line-height: 25px;font-size: 14px; font-family:  Times New Roman;">
					                <ol>
	                                  <li>* Confirm button Enable only if all fields are filled </li>
	                                  <li>* Once you confirm, you cannot edit</li>
	                                </ol>
	                             </div>
	                           </div>
	                           </div>
      
</div>
          </div>
    </div>
   	                   
	                   
		 <input type="hidden" id="totalcount" id="totalcount" value="<%=loopCount%>">
		 
		 <br>

			
		 <%} %> 
		 	
	</form>

</body>
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dataTables.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/dataTables.bootstrap.js"></script> 
<script type="text/javascript">


$(document).ready( function()
 {

$('#resultdata').DataTable( {
    
    scrollY: "200px",
    scrollX:   false,
      iDisplayLength: 25 , 
    scrollCollapse: false,
    fixedColumns:   {
        leftColumns: 2
    }
   
<%--  <% if( isMSIE ){ %> 
    "bSort": false, 
    "paging":   false,
    "ordering": false,
    "info":     false,
    "bFilter": false

<%}%> --%>

} );
$('#up_button').click(function () {

	 var documentHeight = $(document).height(); 
	  var scrollPosition = 	0; 
	
	if($(window).scrollTop()>0)
		{
			scrollPosition =$(window).scrollTop()-$(window).height();
		}
	
		$('body,html').animate({
			scrollTop: scrollPosition
		}, 800);
	return false;
});

/*Down arrow Buttion Action function*/

$('#down_button').click(function ()
{
	
	 var documentHeight = $(document).height(); 
	  var scrollPosition = $(window).height() + $(window).scrollTop(); 
	$('body,html').animate({
		scrollTop: scrollPosition
	}, 800);
	return false;
});

$(".iframe").colorbox(
		{
		iframe:true, width:"90%", height:"90%",
		onClosed: function(){document.mispupulation.action="<%=request.getContextPath()%>/mispopulation.do?mode=unspecified&"+Math.random();
		document.mispupulation.submit();}
		 
		});
 });

			
	      	$("#districtwiseformSubmitbut").click(function( event )
			{
				try{
						/*Screen Locking Started */
    		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
    		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
    	      			/*Screen Locking Ended */
    	      			
    	      				$("#mode").val("districtWisePopulation");	
    	      			
    	      			
						document.mispupulation.target="_self";
						document.mispupulation.action="<%=request.getContextPath()%>/mispopulation.do?";
						document.mispupulation.submit();
				}catch(e){alert(e);}
	      	});
	      	$("#update").click(function( event )
	    			{
	    				try{
	    					var count = document.getElementById("totalcount").value;
	    					for(var i=0;i<count;i++)
	    					{
	    						if(document.getElementById("val_"+i).value!="")
	    						{
	    							
	    						}
	    						/* else
	    						{
	    							alert("Please Fill the Mandal");
	    							$('#val_'+i).focus();
	    							return false;
	    						} */
	    					}
	    					
	    					 
	    					if($('#val_0').prop('disabled'))
	    					{
	    						alert("Records are already Saved");
	    						return false;
	    					}
	    					if(confirm("Are You Sure Want Sumbit the Request"))
	    					{
	    					/*Screen Locking Started */
	    	      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	    	      		    $('#processlayer').css({"display": "block","z-index":"110000"});
	    	  				/*Screen Locking Ended */
	    	  				$("#mode").val("inserPopulation");
	    	      		 	 document.mispupulation.action="<%=request.getContextPath()%>/mispopulation.do";
	    					document.mispupulation.submit();
	    					}
	    				}catch(e)
	    				{
	    					alert(e);
	    				}
	    			});
	      	$("#confirm").click(function( event )
	    			{
	    				try{
	    						if(confirm("Once You Confirm You Cannot EDIT"))
	    						{
		    							/*Screen Locking Started */
		        		      			$('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		        		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
		        	      			/*Screen Locking Ended */
		        	      			$("#mode").val("confirmMethod");
		    						document.mispupulation.target="_self";
		    						document.mispupulation.action="<%=request.getContextPath()%>/mispopulation.do?";
		    						document.mispupulation.submit();
	    						}
	    						
	    				}catch(e){alert(e);}
	    	      	});
 	//Examples of how to assign the Colorbox event to elements
	
	

</script>
</html>
 <%}catch(Exception e)
 {
 	System.out.println(e);
 }%>