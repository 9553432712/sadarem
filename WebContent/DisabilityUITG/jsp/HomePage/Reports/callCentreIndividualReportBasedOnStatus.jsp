<%-- 
    Document   : callCentreIndividualReportBasedOnStatus
    Created on : Oct 31, 2013, 9:40:31 AM
    Author     : 310926
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<html>
    <head>
         <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <script>

            function getReport(){

                document.forms[0].mode.value="getReportBasedOnStatus"; 
                document.forms[0].submit();
            }

        </script>
  <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
   {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
   }
</Style>
        <title>SADAREM</title>
    </head>
    <body onload=" OnBodyLoad(1,3);"> 
        <html:form action="callCentreReport.do?mode=getIndividualReportBasedOnStatus">
            <%
                        String districtId = "";
                        if (request.getAttribute("districtId") != null) {
                            districtId = request.getAttribute("districtId").toString();
                        }
                        String urbanId = "";
                        if (request.getAttribute("urbanId") != null) {
                            urbanId = request.getAttribute("urbanId").toString();
                        }

                        String status = "";
                        if (request.getAttribute("status") != null) {
                            status = request.getAttribute("status").toString();
                        }

            %>
            <html:hidden property="mode"/>
           
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" >
                         <tr> <th colspan="4" class="hd_gd" align="center" valign="middle">R6.1 &nbsp; District and Status Wise Grievances Individual Report</th></tr>
                             <br>
                            <tr>
                                <td  align="center" valign="top">

                                    <table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">
                                        <tr>
                                            <td colspan="6">
                                                <logic:present name="msg">
                                                    <center><font color="red" size="2"><b><bean:write name="msg"/></b></font> </center>
                                                </logic:present>
                                            </td>
                                        </tr>

                                    </table>


                                    <%int i = 1;%>

                                    <logic:notEmpty name="ReportList" scope="request">
                                    
                                    
                                       <table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                                            <tr>
                                                <td>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                </td>
                                            </tr>
                                         <tr align="right" >
                        <td  colspan="4" style="text-align: right">
                                                    <a href="javascript: void(0)"  onclick="window.open('callCentreReport.do?mode=getIndividualReportBasedOnStatus&pageType=excel&status=<%=status%>&districtId=<%=districtId%>&urbanId=<%=urbanId%>',
                                                        'windowname1',
                                                        'scrollbars=yes,width=1200, height=600,top=(screen.height/2)-(600/2),left = (screen.width/2)-(1200/2)');
                                                        return false;" >
                                                        <%--<a href="callCentreReport.do?mode=getIndividualReportBasedOnStatus&pageType=excel&status=<%=status%>&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">--%>
                                                         <img src="DisabilityUITG/images/excel.jpg"
                                                                             height="35" width="25" title="Export Excel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                    <a href="callCentreReport.do?mode=getIndividualReportBasedOnStatus&pageType=print&status=<%=status%>&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">
                                                         <img src="DisabilityUITG/images/print.gif" height="25" width="25" title="Print"/></a>
                                                </td>


                                            </tr>
                                          
                                        </table>
                                        
                                        
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                 <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
                  
                        <th class="hd_gd" align="center" valign="middle">S No.</th>
                        <th class="hd_gd" align="center" valign="middle">Request ID</th>
                        <th class="hd_gd" align="center" valign="middle">SADAREM ID</th>
                        <th class="hd_gd" align="center" valign="middle">Pension No.</th>
                        <th class="hd_gd" align="center" valign="middle">SurName</th>
                        <th class="hd_gd" align="center" valign="middle">FirstName </th>
                        <th class="hd_gd" align="center" valign="middle">District</th>
                        <th class="hd_gd" align="center" valign="middle">Mandal</th>
                         <th class="hd_gd" align="center" valign="middle">Request Type</th>
                         <th class="hd_gd" align="center" valign="middle">Generated SADAREM ID</th>
                          <th class="hd_gd" align="center" valign="middle">Mobile Number</th>
                         <th class="hd_gd" align="center" valign="middle">Request Raised Date</th>
                          <th class="hd_gd" align="center" valign="middle">Scheduled Camp Date</th>
                                            </tr>
                                            
                                            
                                            <tr >
                  
                        <th class="hd_gd" align="center" valign="middle">1</th>
                        <th class="hd_gd" align="center" valign="middle">2</th>
                        <th class="hd_gd" align="center" valign="middle">3</th>
                        <th class="hd_gd" align="center" valign="middle">4</th>
                        <th class="hd_gd" align="center" valign="middle">5</th>
                        <th class="hd_gd" align="center" valign="middle">6 </th>
                        <th class="hd_gd" align="center" valign="middle">7</th>
                        <th class="hd_gd" align="center" valign="middle">8</th>
                         <th class="hd_gd" align="center" valign="middle">9</th>
                         <th class="hd_gd" align="center" valign="middle">10</th>
                          <th class="hd_gd" align="center" valign="middle">11</th>
                         <th class="hd_gd" align="center" valign="middle">12</th>
                          <th class="hd_gd" align="center" valign="middle">13</th>
                                            </tr>
                                            


                                         <%String classStyle=""; %>
                                            <logic:iterate id="row" name="ReportList" indexId="count">
                    
                                             <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                                                <tr>
                                                    <td  class="<%=classStyle%>" align="center"><%=i++%>.</td>
                                                    <td  class="<%=classStyle%>"  align="center">${row.requestNo}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.sadaremId}</td>
                                                    <td  class="<%=classStyle%>" align="left">${row.pensionNo}</td>
                                                    <td  class="<%=classStyle%>" align="left">${row.surName}</td>
                                                    <td  class="<%=classStyle%>" align="left">${row.firstName}</td>
                                                    <td  class="<%=classStyle%>" align="left">${row.district}</td>
                                                    <td  class="<%=classStyle%>" align="left">${row.mandal}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.requestName}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.generatedSadaremId}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.mobileNo}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.raisedDate}</td>
                                                    <td  class="<%=classStyle%>" align="center">${row.scheduledCampdate}</td>

                                                </tr> </logic:iterate>
 </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
                                           
                    </logic:notEmpty>
                                </td>
                            </tr>
                        </table>
           
        </html:form>

    </body>
</html>

