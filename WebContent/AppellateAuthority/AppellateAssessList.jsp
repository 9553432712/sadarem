<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%try{ %>
<%
     ArrayList reassessList = new ArrayList();
     ArrayList innerList = new ArrayList();
     reassessList =(ArrayList)request.getAttribute("reassessList");  
    
     
     String selStatus 		= CommonUtility.checkNullObj(request.getAttribute("selStatus"));
     ArrayList statusList   = new ArrayList();
     
     innerList = new ArrayList();
	     innerList.add("P");
	     innerList.add("Pending");
     statusList.add(innerList);
     
     innerList = new ArrayList();
     innerList.add("H");
     innerList.add("Hold");
 statusList.add(innerList);
 
 innerList = new ArrayList();
     
 %>
 <link href="<%=request.getContextPath()%>/css/colorbox.css" type="text/css" rel="stylesheet"/> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/> 
 <style>
  .table>thead>tr>th
   {
     text-align:center;
     color:#fff;
     background:#62A0D6;
   }
 </style>
 </head>
 <body>


  			<table width="70%" align="center">
				<tr>
					<td>	
						<a href="<%=request.getContextPath()%>/AppellateReassess.xls?mode=exportExcel" style="float:right;text-align:center;">
							<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
			 			</a>
					</td>
				</tr>
			</table>
			   <h4 style="text-align:center;color:#3338B7;">Appellate Authority Reassessment</h4>
			   <form name="appellateForm" id="appellateForm" method="post">
			  <div class="form-group">
						                   <div class="input-group">
						                   <div class="input-group-addon"><span class="glyphicon glyphicon-star"></span> Status</div>
							             		<%=ComboUtil.createStrComboBoxAuto("appellatestatus", statusList, selStatus, "form-control", "", false, false, "") %> 
						                   </div>
						                   <span class="help-block" id="error"></span>
						              </div>
			   
			   <input type="hidden" name="mode" id="mode" value='getAppellatePartADetails'>
			     <table border="1" align="center" cellspacing="0" style="BORDER-COLLAPSE: collapse"  width="90%">
				  
			     <thead> 
			      <tr>
			       	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">S.No.</th>
			       	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Select</th>
			        <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">SADAREM ID</th>
			        <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Request ID</th>
			        <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Category</th>
			        <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Name</th>
			        <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">District</th>
			       	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Mandal</th>
			      	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Village</th>
			      	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Type of Disability</th>
			      	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" colspan="2">Pending Status</th>
			       <th height="30" class="hd_gd" style="text-align: center;vertical-align: middle" rowspan="2">Hold</th>
			      </tr>
			      <tr>
			       	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle">Date</th>
			       	<th height="30" class="hd_gd" style="text-align: center;vertical-align: middle">No. of Day(s)</th>
			      </tr>
			       	
			      </thead>
			      <%
			      String classStyle = "firstrow";
			      
			      if(reassessList.size()>0)
			      { 
			       for(int i=0;i<reassessList.size();i++)
			       {
			    	  innerList = new ArrayList();
			    	  innerList = (ArrayList)reassessList.get(i);
			    	  if(i%2==0)
        			  {
        			  	classStyle="secondrow";
        			  }
        			  else
        			  {
	        			  	classStyle="firstrow";
        			  }
			    	  
			       %>     
			        <tr>
			          <td align="center" class="<%=classStyle%>">
			          	<%=i+1 %>
			           	  <input type="hidden" id="sadaremId<%=i%>" name="sadaremId<%=i%>" value='<%=innerList.get(0)%>'>
				          <input type="hidden" id="reqId<%=i%>" name="reqId<%=i%>" value='<%=innerList.get(1)%>'>
			          </td>
			          <td align="center" class="<%=classStyle%>">
			          <%
			          if(innerList.get(10).toString().equalsIgnoreCase("N"))
			          {  
			        	  %>
			          
			          	<input type="radio" name="assessId" id='assessId+<%=i%>+' value='<%=i%>'>
				         <% 
			          }
			          else
			          {
			        	  %>
			        	  	NA
			        	  <%
			          }
			          	%>
			          </td>
			          <td align="center" class="<%=classStyle%>"><%=innerList.get(0)%></td>
			          <td align="center" class="<%=classStyle%>"><a class='iframe' style="overflow:hidden; font-family:sans-serif" href="<%=request.getContextPath()%>/IssueRequestlist.do?mode=getrequestListValues&requestid=<%=innerList.get(1)%>&sadaremid=<%=innerList.get(0)%>&reqtype=<%="S018" %>&randomid=<%=Math.random()%>">
						<font size="3" ><b><%=innerList.get(1)%></b></font>
						</a> </td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(2) %></td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(3) %></td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(4) %></td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(5) %></td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(6) %></td>
			          <td align="left" class="<%=classStyle%>"><%=innerList.get(7) %></td>
			          <td align="center" class="<%=classStyle%>"><%=innerList.get(8) %></td>
			          <td align="right" class="<%=classStyle%>"><%=innerList.get(9) %></td>
			          <td align="center" class="<%=classStyle%>">
			          
			          <%
			          if(innerList.get(10).toString().equalsIgnoreCase("N"))
			          {  
			        	  %>
			          	<button type="button" class="btn btn-info holdbtn" holdsadaremid="<%=innerList.get(0)%>" holdrequestid="<%=innerList.get(1)%>" holdcategory="<%=innerList.get(2)%>" holdpername="<%=innerList.get(3)%>">Hold</button>
			          	<%
			          }
			          else
			          {
			        	  %>
			        	  	NA
			        	  <%
			          }
			          	%>
			          </td>
			       </tr>
			     <%}
			      }
			     else
			     { %>
			     <tr>
			     <td colspan="13" align="center">No records found to display.</td>
			     </tr>
			     <%} %>
			   </table>
  </form>  

<%@include file="/AppellateAuthority/appellateholdrequest.jsp" %>
	  
<script src="<%=request.getContextPath()%>/scripts/jquery.colorbox.js"></script>
			<script type="text/javascript">
					$(document).ready(function()
				   {
						
						$(document).attr("title", ":: SADAREM :: Appellate Authority");
						
						  $('input[type=radio][name=assessId]').click(function(){
							  
							  if(confirm('Are you sure you want to proceed to Part B?'))
								  {
								     document.appellateForm.action="<%=request.getContextPath()%>/AppellateReassess.do";
								     document.appellateForm.submit();
								  }
						  });
						
						  
						  $("#appellatestatus").change(function()	  
						  {
							  $("#mode").val("unspecified");
							  document.appellateForm.action="<%=request.getContextPath()%>/AppellateReassess.do";
							  document.appellateForm.submit();
						  });
						  
						  
						 
						//Examples of how to assign the Colorbox event to elements
						$(".iframe").colorbox(
								{
								iframe:true, width:"80%", height:"48%",
								 
								});
						$(".prntbut").click(function(e){
							alert("Print Button");
						});
						$(".callbacks").colorbox({
							onOpen:function(){ alert('onOpen: colorbox is about to open'); },
							onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
							onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
							onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
							onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
						});
						$('#cboxClose').click(function (){alert(1);});
		
						$('.non-retina').colorbox({rel:'group5', transition:'none'});
						$('.retina').colorbox({rel:'group5', transition:'none', retinaImage:true, retinaUrl:true});
						
						//Example of preserving a JavaScript event for inline calls.
						$("#click").click(function(){ 
							$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit",}).text("Open this window again and this message will still be here.");
							return false;
						});
						
						 
					});
		
					
					
					
					
				</script>
<%}catch(Exception e){e.printStackTrace();} %>


</body>

</html>