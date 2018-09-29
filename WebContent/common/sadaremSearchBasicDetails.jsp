
<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ArrayList dataList = (ArrayList)request.getAttribute("resultList");

if(dataList!=null && dataList.size()==1)
{
	dataList = (ArrayList)dataList.get(0);
%>

<div id="sadaremDetailsDIVID" style="border-bottom:#ccc solid 1px;margin: 20px;"  oncontextmenu="return false;"  >
			    <ul class="nav nav-tabs" id="myTab">
			        <li class="active"><a href="#sectionA"><b>Person Basic Details</b></a></li>
			        <li><a href="#sectionB"><b>Person Disability Details</b></a></li>
			    </ul>
			    <div class="tab-content" style="padding:10px; border-left:#ccc solid 1px;  border-right:#ccc solid 1px;">
			        <div id="sectionA" class="tab-pane fade in active">
			          <h3>Part A Information</h3>
			          <div class="row">
			          <div class="col-md-5">
			          			<div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(1) %></span> 
					                            </div>
					                          </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">District</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(2) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Mandal</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(3) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Village</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(4) %></span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Habitation</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(5) %></span> 
					                            </div>
					                        </div>
					           </div>	
					  </div>
					  <div class="col-md-5">
					           
					 <div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Name (Surname Name)</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(6).toString().trim() %>&nbsp;<%=dataList.get(7).toString().trim() %> </span> 
					                            </div>
					                        </div>
					                         <%-- <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Date of Birth (DD/MM/YYYY)</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(8).toString().trim() %> </span> 
					                            </div>
					                        </div> --%>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Gender</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(9).toString().trim() %></span> 
					                            </div>
					                        </div>
					                       <%--   <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Relationship Details</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(10).toString().trim() %></span> 
					                            </div>
					                        </div> --%>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Person Status</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(11).toString().trim() %> </span> 
					                            </div>
					                        </div>
					                        <%--  <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Contact No.</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(12).toString().trim() %>&nbsp;</span> 
					                            </div>
					                        </div> --%>
					                       <%--   <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">ID Proof Details</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(13).toString().trim() %>  <%=dataList.get(14).toString().trim() %>&nbsp;</span> 
					                            </div>
					                        </div> --%>
					                         
					           </div>
					        </div>
					        <div class="col-md-2">
					        	<div style="height: 160px; width:160px;background-color: #37ADB6; text-align: center;vertical-align: middle; padding-top: 5px;">
					        		<img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=dataList.get(1).toString().trim()%>" width="152" height="152" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
					        	</div>
					        	
					        </div>
					     </div>
			        </div>
			        <div id="sectionB" class="tab-pane fade">
			              <h3>Part B Information</h3>
			           <div class="row">
			           <%
			           if(dataList.get(0).toString().equalsIgnoreCase("view") && dataList.get(15).toString().length()>0)
			           {
			           %>
			           		<div class="col-md-5">
			          			<div class="panel-body">
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Type of Disability</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(15) %>&nbsp;</span> 
					                            </div>
					                          </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Disability Percent</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(16) %>%&nbsp;</span> 
					                            </div>
					                        </div>
					                         <div class="form-group" style="height:30px;padding-top:5px;">
					                            <div class="input-group">
					                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Status</div>
												  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(17) %>&nbsp;</span> 
					                            </div>
					                        </div>
					           </div>	
					         </div>
					         <div class="col-md-5">  
							 			<div class="panel-body">
							                         <div class="form-group row" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Type</div>
														  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(18).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                         <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Issued on</div>
														  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(19).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							                         <div class="form-group" style="height:30px;padding-top:5px;">
							                            <div class="input-group">
							                              <div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">Certificate Expires on</div>
														  <span class="input-group-addon" style="text-align:left;"><%=dataList.get(20).toString().trim() %>&nbsp;</span> 
							                            </div>
							                        </div>
							           </div>
					           </div>
					           <%
			           			}
					           else
					           {
					        	   %>
					        	   <div class="panel-body  col-md-12">
					        	   		 <div class="alert alert-danger" style="height:30px;padding-top:5px;margin-top:2px;">
											  <strong>Part B Form not filled.</strong>
										</div>
					        	   </div>
					        	   <%
					           }
					           %>
					     </div>
			           
			        </div>
			    </div>
	</div>
<%
}
%>
