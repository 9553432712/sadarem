<%@page import="java.util.ArrayList" %>

<%ArrayList basicData = new ArrayList(); 

basicData = (ArrayList)request.getAttribute("sadaremData");
if(basicData.size()>0)
{
	basicData = (ArrayList)basicData.get(0);
}

%>
<head>
<link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-dialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap-dialog.min.js"></script>
<style>
 .table tbody tr td {
       padding:1px;
       font-size:14px
       
     }
     .hd_gd
   {
     border : #234466 1px solid;
   }
   .secondrow{
   border-color:#337ab7;
   }
</style>
 <script>

 $(document).ready( function()
 		{
 
      $("#butto").click(function (){
    
        BootstrapDialog.show({
        	message: $('<div>qweqw<br>sdasdf</div>').load('/sadarem/WebContent/common/ContactUs.jsp')
        });
      });
 		});
    </script> 
         
        </head>


    
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  >
	    		
  				<tr align='center'>
  				 	<th height="35" colspan='6' align="center" valign="middle"  style="border-right : #234466 1px solid; text-align:center; font-family:inherit; background-color:#337ab7; border-color:#337ab7;color:#fff;" >
  				 	Basic details of SADAREM ID : <%=basicData.get(0)%></th>

  				</tr>
    			<tr>
	  				
	  				 	<td width="19%" class="secondrow" align="left" valign="middle"><b>SADAREM ID : </b><%=basicData.get(0)%></td>
	  				 	<td width="19%" class="secondrow" align="left" valign="middle"> <b>Name : </b><%=basicData.get(9)%> <%=basicData.get(10)%>
	  				 	(<%=basicData.get(11)%>)</td>
	  				 	<td width="19%" class="secondrow" align="left" valign="middle"><b>Gender : </b><%=basicData.get(15)%></td>
	  				 	<td width="19%" class="secondrow" align="left" valign="middle"><b>Certificate Status : </b><%=basicData.get(22)%></td>
	  				 	<td width="15%" class="secondrow" align="left" valign="middle"><b>Date Of Birth : </b><%=basicData.get(12)%></td>
	  				 	<td  class="secondrow" style="border-right : #234466 1px solid; border-bottom : #234466 1px solid;" rowspan='3' align="left" valign="middle">
	  				 	
	  				 	 <img align='center' src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=basicData.get(0)%>&distName=<%=basicData.get(5)%>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/></td>
   			</tr>
	  			<tr>
	  				 	 	<td width="19%" class="firstrow" align="left" valign="middle"><b>Relation Name : </b>(<%=basicData.get(16)%>)<%=basicData.get(17)%><br>
	  				 	 	(<%=basicData.get(18)%>)</td>
	  				 	<td width="19%" class="firstrow" align="left" valign="middle"><b>Contact Number : </b><%=basicData.get(19)%></td>
	  				 	<td width="19%" class="firstrow" align="left" valign="middle"><b>Disability : </b><%=basicData.get(29)%> </td>
	  				 	<td width="19%" class="firstrow" align="left" valign="middle"><b>Person Status : </b><%=basicData.get(23)%> </td>
	  				 	<td width="15%" class="firstrow" align="left" valign="middle"><b>Proof ID : </b>
	  				 	<%if(!basicData.get(14).equals("-")) {%>
	  				 	<a style="font-family:Verdana, Geneva, sans-serif;" href="<%=request.getContextPath()%>/loadsearchsadaremdtls.do?mode=getaadhaardetailsbyuid&strUID=<%=basicData.get(14)%>&randomid=<%=Math.random()%>" target="_blank">
	  				 	<b><%=basicData.get(14)%></b></a>
	  				 	<%}else{ %>
	  				 	<b><%=basicData.get(14)%></b>
	  				 	<%} %>
	  				 	<br>
	  				 		<%if(!basicData.get(14).equals("-")) {%>
	  				 	(<%=basicData.get(13)%>)
	  				 	<%} %>
	  				 	</td>
	  				 	<%-- <td width="15%" class="firstrow" align="left" valign="middle"><b>Proof ID : </b>
	  				 	<a style="font-family:Verdana, Geneva, sans-serif;" href="#" name='butto' id='butto'>
	  				 	<b><%=basicData.get(14)%></b></a><br>(<%=basicData.get(13)%>)</td> --%>
	  			</tr>
	  			<tr>
	  				 	<td width="19%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Cause Of Disability : </b>
	  				 	 <%=basicData.get(32)%></td>
	  				 	<td width="19%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Address :</b> 
	  				 	<%=basicData.get(5)%>(D),<%=basicData.get(6)%>(M),<br><%=basicData.get(7)%>(V),<%=basicData.get(8)%>(H)</td>
	  				    <td width="19%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Certificate Issued Date :</b> 
	  				    <%=basicData.get(34)%></td>
	  				 	<td width="19%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Certificate Expiry Date :</b> 
	  				 	<%=basicData.get(35)%></td>
				 	    <td width="15%" class="secondrow" style="border-bottom : #234466 1px solid;" align="left" valign="middle"><b>Category :</b> 
				 	    <%if(((String)basicData.get(20)).equals("1") || ((String)basicData.get(20)).equals("2") ){%>Regular<%}else{ %>Temparory<%} %></td>
	  				
	  		 </tr>
					
					 

</table>