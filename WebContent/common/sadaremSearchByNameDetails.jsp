<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ArrayList dataList = (ArrayList)request.getAttribute("resultList");
%>
<div class="main_container"  oncontextmenu="return false;" ondragstart="return false" onselectstart="return false">
   <div class="view-content">
			<table id="example" class="table table-striped table-bordered" cellspacing="0" width="98%" style="align:center;">
			        <thead>
			            <tr>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">S.No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">SADAREM ID</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 10%; vertical-align:middle;" rowspan="2">District</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Mandal</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Village</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;" rowspan="2">Habitation</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Person Name</th>
			                <!-- <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Date of Birth</th> -->
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Gender</th>
			                <!-- <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Relation Name</th> -->
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Person Status</th>
			               <!--  <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Contact No.</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" rowspan="2">Proof Details</th> -->
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;" colspan="6">Disability Details</th>
			            </tr>
			            <tr>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Disability</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Percent</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 15%; vertical-align:middle;">Certificate Status</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 10%; vertical-align:middle;">Certificate Type</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Issue Date</th>
			                <th class="hd_gd" style="text-align:center;height:30px; width: 5%; vertical-align:middle;">Expire Date</th>
			            </tr>
			        </thead>
 					<tbody>
						 <%
				            if(dataList!=null && dataList.size()>0)
				            {
				            	ArrayList tempList = new ArrayList();
				            	String status ="";
				            	for(int looper=0;looper<dataList.size();looper++)
				            	{
				            		
				            		tempList = (ArrayList)dataList.get(looper);
				            		
				            		status = tempList.get(0).toString().trim();
				            	
				            		%>
											 <tr>
								                <td align="center"><%=looper+1 %></td>
								                <td align="left"><%=tempList.get(1) %></td>
								                <td align="left"><%=tempList.get(2) %></td>
								                <td align="left"><%=tempList.get(3) %></td>
								                <td align="left"><%=tempList.get(4) %></td>
								                <td align="left"><%=tempList.get(5) %></td>
								                <td align="left"><%=tempList.get(6) %>&nbsp;<%=tempList.get(7) %></td>
								                <%-- <td align="left"><%=tempList.get(8) %></td> --%>
								                <td align="left"><%=tempList.get(9) %></td>
								                <%-- <td align="left"><%=tempList.get(10) %></td> --%>
								                <td align="left"><%=tempList.get(11) %></td>
								                <%--  <td align="left"><%=tempList.get(12) %></td>
								               <td align="left"><%=tempList.get(13) %>&nbsp;<%=tempList.get(14) %>&nbsp;</td> --%>
								                <%
								                if(status.equalsIgnoreCase("view"))
								                {
								                	%>
										                <td align="left"><%=tempList.get(15) %></td>
										                <td align="left"><%=tempList.get(16) %></td>
										                <td align="left"><%=tempList.get(17) %></td>
										                <td align="left"><%=tempList.get(18) %></td>
										                <td align="left"><%=tempList.get(19) %></td>
										                <td align="left"><%=tempList.get(20) %></td>
								                	<%
								                }
								                else
								                {
								                	%>
									                <td align="center" colspan="6" style="background-color: #D8191E; color:#fff;text-align:center;">Part B details not filled</td>
							                	<%
								                }
								                %>
								            </tr>
				            		<%
				            	}
				            }
				            else
				            {
			            		%>
										 <tr>
								                <td colspan="21"> No records available</td>
								            </tr>
			            		<%
				            }
           					%>
												
					</tbody>
			</table>
	</div>
</div>
 <script type="text/javascript">
   	
   $(document).ready(function()
			{
	   			document.title = ":: SADAREM :: Search SADAREM Information";
	   			$('#example').dataTable({
	   				"scrollX": "300px",
	   				"scrollCollapse": true,
	   				"columnDefs": [{
			            "defaultContent": "-",
			            "targets": "_all"
			          }]
	   			});
	   			
			});
  </script>							
