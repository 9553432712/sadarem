<%@page import="java.util.*,com.tcs.sadarem.util.CommonUtility" %>
<%

ArrayList basicData11       = (ArrayList)request.getAttribute("sadaremData");
String issueName1 = CommonUtility.checkNullObj(request.getAttribute("issueName"));
if(basicData11.size()>0)
{
	basicData11      = (ArrayList)basicData11.get(0);
}

%>

<div id="printdivid" style="display: none;">
		<table width="95%" class="table table-striped table-bordered">
			<tr>
				<td width="50%" align="center">
						<table width="98%"  border="1" >
							<tr>
								<td colspan="2" style="background-color:#337ab7;padding:5px;color:#fff;" align="center"><h3>${statusMsg}</h3></td>
							</tr>
							<tr>
								<td>Issue Type </td>
								<td><%=issueName1 %> </td>
							</tr>
							
							<tr>
								<td>SADAREM ID </td>
								<td><%=basicData11.get(0)%></td>
							</tr>
							<tr>
								<td>Person Name (Surname Name)</td>
								<td><%=basicData11.get(9)%> &nbsp;<%=basicData11.get(10)%> </td>
							</tr>
							<tr> 
								<td>Date of Birth (DD/MM/YYYY)</td>
								<td><%=basicData11.get(12)%> </td>
							</tr>
							<tr> 
								<td>Gender</td>
								<td><%=basicData11.get(15)%></td>
							</tr> 
							<tr>
								<td>Relationship Details</td>
								<td>(<%=basicData11.get(16)%>)<%=basicData11.get(17)%></td>
							</tr> 
							<tr>
								<td>Certificate Status </td>
								<td><%=basicData11.get(22)%> </td>
							</tr>
							<tr>
								<td>Disability</td>
								<td><%=basicData11.get(29)%></td>
								
							</tr>
							<tr>
								<td>Proof ID </td>
								<td><%=basicData11.get(14)%></td>
								
							</tr>
							<tr>
							 <td>District</td>
							 <td><%=basicData11.get(5)%></td>
					        </tr>
                         	<tr>
                              <td>Mandal</td>
							  <td><%=basicData11.get(6)%></td>
                            </tr>
					        <tr>
					           <td>Village</td>
							   <td><%=basicData11.get(7)%></td>
					        </tr>
					        <tr>
					           <td>Habitation</td>
							   <td><%=basicData11.get(8)%></td>
					        </tr>	        
							
						</table>
				</td>				
			</tr>
			<tr>
				<td>
					<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0"  >
						<tr>
							<td><span style="color:blue;font-weight:bold;font-size:15px;">Note:</span></td>
										
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">1. The request is pending for the approvals.</span></td>							
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">2. The requested changes will be reflect after all approvals.</span></td>
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">3. This is not the certificate of proof, it is an Acknowledgement.</span></td>
						</tr>
						<tr>
							<td><span style="color:green;font-weight:bold;font-size:15px;">4. You can check the status of grievance at <b>http://sadarem.telangana.gov.in/sadarem/openIsuueTrackinghome.do</b> </span></td>
						</tr>
					</table>
				</td>
			</tr>					
		</table>
		<br></br>		
	
	</div>