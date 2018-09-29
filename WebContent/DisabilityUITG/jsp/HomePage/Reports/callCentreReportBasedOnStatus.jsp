<%-- 
    Document   : callCentreReportBasedOnStatus
    Created on : May 23, 2013, 12:19:43 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<html>
    <head>

        <script>

            function getReport(){

                document.forms[0].mode.value="getReportBasedOnStatus";
                document.forms[0].submit();
            }
         
        </script>
        <script language="javascript" type="text/javascript">
            function OpenPopupCenter(pageURL, title, w, h) {
                var left = (screen.width - 500) ;
                var top = (screen.height - 200);  // for 25% - devide by 4  |  for 33% - devide by 3
                var targetWin = window.open(pageURL);//, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            }
        </script> 


        <title>SADAREM</title>
         <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

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


    </head>
    <body onload=" OnBodyLoad(2,3);">
        <html:form action="callCentreReport.do?mode=getReportBasedOnStatus">
            <%
                        String districtId = "";
                        if (request.getAttribute("districtId") != null) {
                            districtId = request.getAttribute("districtId").toString();
                        }
                        String urbanId = "";
                        if (request.getAttribute("urbanId") != null) {
                            urbanId = request.getAttribute("urbanId").toString();
                        }
                        int applicationRegistered = 0;
                        int documentsUploaded = 0, documentsToBeUploaded = 0, documentsUploadedRejected = 0;
                        int documentsVerifiedPending = 0, documentsVerified = 0, documentsVerifiedRejected = 0;
                        int mPDOPending = 0, mPDOApproved = 0;
                        int mPDORejected = 0;
                        int pDPending = 0;
                        int pDApproved = 0;
                        int pDRejected = 0;
                        int scheduleCampPending = 0;
                        int scheduleCampApproved = 0, scheduleCampRejected = 0;
                        int certificateIssued = 0, certificateRejected = 0;
                        int ovrAllPending = 0;
                        ArrayList reportList = new ArrayList();
                        if (request.getAttribute("ReportList") != null) {
                            reportList = (ArrayList) request.getAttribute("ReportList");
                            Iterator iter = reportList.iterator();
                            while (iter.hasNext()) {
                                Map m = (Map) iter.next();
                                applicationRegistered = applicationRegistered + Integer.parseInt(m.get("ApplicationRegistered").toString());
                                documentsToBeUploaded = documentsToBeUploaded + Integer.parseInt(m.get("DocumentsToBeUploaded").toString());
                                documentsUploadedRejected = documentsUploadedRejected + Integer.parseInt(m.get("DocumentsUploadedRejected").toString());
                                documentsUploaded = documentsUploaded + Integer.parseInt(m.get("DocumentsUploaded").toString());
                                documentsVerifiedPending = documentsVerifiedPending + Integer.parseInt(m.get("DocumentsVerifiedPending").toString());
                                documentsVerified = documentsVerified + Integer.parseInt(m.get("DocumentsVerified").toString());
                                documentsVerifiedRejected = documentsVerifiedRejected + Integer.parseInt(m.get("DocumentsVerifiedRejected").toString());
                                mPDOPending = mPDOPending + Integer.parseInt(m.get("MPDOPending").toString());
                                mPDOApproved = mPDOApproved + Integer.parseInt(m.get("MPDOApproved").toString());
                                mPDORejected = mPDORejected + Integer.parseInt(m.get("MPDORejected").toString());
                                pDPending = pDPending + Integer.parseInt(m.get("PDPending").toString());
                                pDApproved = pDApproved + Integer.parseInt(m.get("PDApproved").toString());
                                pDRejected = pDRejected + Integer.parseInt(m.get("PDRejected").toString());
                                scheduleCampPending = scheduleCampPending + Integer.parseInt(m.get("ScheduleCampPending").toString());
                                scheduleCampApproved = scheduleCampApproved + Integer.parseInt(m.get("ScheduleCampApproved").toString());
                                scheduleCampRejected = scheduleCampRejected + Integer.parseInt(m.get("ScheduleCampRejected").toString());
                                certificateIssued = certificateIssued + Integer.parseInt(m.get("certificateIssued").toString());
                                certificateRejected = certificateRejected + Integer.parseInt(m.get("certificateRejected").toString());
                                ovrAllPending = ovrAllPending + Integer.parseInt(m.get("ovrAllPending").toString());
                            }
                        }

            %> 
            <html:hidden property="mode"/>
            <br>
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>


            <br>
            
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center"  >
                            <tr> <th colspan="4" class="hd_gd"  align="center" valign="middle">R6.1 &nbsp; District and Status Wise Grievances Report</th></tr>
                              
                            <tr style="padding-top: 10px;">
                                <td>
                                    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="100%" border="0px">
									        <tr >
                                            <td colspan="6">
                                                <logic:present name="msg">
                                                    <center><font color="red" size="2"><b><bean:write name="msg"/></b></font> </center>
                                                </logic:present>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td align="right"  >District</td>
                                            <td align="center" >
                                                <html:select  property="district_id"  >
                                                    <html:option value="ALL">--ALL--</html:option>
                                                    <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                                </html:select>
                                            </td>
                                            <td  valign="middle" width="12%">Area Type</td>
                                            <td align="left" valign="middle">
                                                <html:select styleId="4" property="urban_id" style="height:25px;" >
                                                    <html:option  value="0">--ALL--</html:option>
                                                    <html:option  value="Rural">Rural</html:option>
                                                    <html:option  value="Urban">Urban</html:option>
                                                </html:select>

                                            </td>


                                           
                                                <td>
                                                <html:button property="sub" onclick="getReport();" value="Submit"/>
                                            </td>
                                        </tr>

                                    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
                </td></tr>
              </table> 

            <br/>
            <logic:notEmpty name="ReportList" scope="request"> 
             <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr align="right" >
                        <td  colspan="4" style="text-align: right">

                            <a href="callCentreReport.do?mode=getReportBasedOnStatus&pageType=excel&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">
                                  <img src="DisabilityUITG/images/excel.jpg"
                                                     height="35" width="25" title="Export Excel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <a href="callCentreReport.do?mode=getReportBasedOnStatus&pageType=print&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">
                                 <img src="DisabilityUITG/images/print.gif" height="25" width="25" title="Print"/></a>
                        </td>

                    </tr>
                     
                </table></logic:notEmpty>
            
            <%int i = 1;%>

            <logic:notEmpty name="ReportList" scope="request">
 
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
                  
                        <th class="hd_gd" align="center" valign="middle"  rowspan="2">
                            SNo.
                        </th>
                        <th  class="hd_gd" align="center" valign="middle" rowspan="2">District</th>
                        <th class="hd_gd" align="center" valign="middle" rowspan="2">Application Registered</th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="3">Documents Upload</th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="3">Field Verification</th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="3">MPDO </th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="3">PD</th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="3">Schedule To Camp</th>
                        <th  class="hd_gd" align="center" valign="middle" colspan="2">Certificate</th>
                        <th  class="hd_gd" align="center" valign="middle" rowspan="2">OverAll Pending</th>

                    </tr>
                    <tr>
                        <th  class="hd_gd" align="center" valign="middle" >To be Uploaded</th>
                        <th  class="hd_gd" align="center" valign="middle" >Uploaded</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>
                        <th  class="hd_gd" align="center" valign="middle" >For Field Verification</th>
                        <th  class="hd_gd" align="center" valign="middle" >Field Verification Completed</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>

                        <th  class="hd_gd" align="center" valign="middle" >Pending</th>
                        <th  class="hd_gd" align="center" valign="middle" >Approved</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>
                        <th  class="hd_gd" align="center" valign="middle" >Pending</th>
                        <th  class="hd_gd" align="center" valign="middle" >Approved</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>
                        <th  class="hd_gd" align="center" valign="middle" >Pending</th>
                        <th class="hd_gd" align="center" valign="middle" >Approved</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>
                        <th  class="hd_gd" align="center" valign="middle"> Issued</th>
                        <th  class="hd_gd" align="center" valign="middle"> Rejected</th>
                    </tr>
                    <tr>
                      <th class="hd_gd">1</th>
                      <th class="hd_gd">2</th>
                      <th class="hd_gd">3</th>
                      <th class="hd_gd">4</th>
                      <th class="hd_gd">5</th>
                      <th class="hd_gd">6</th>
                      <th class="hd_gd">7</th>
                      <th class="hd_gd">8</th>
                      <th class="hd_gd">9</th>
                      <th class="hd_gd">10</th>
                      <th class="hd_gd">11</th>
                      <th class="hd_gd">12</th>
                      <th class="hd_gd">13</th>
                      <th class="hd_gd">14</th>
                      <th class="hd_gd">15</th>
                      <th class="hd_gd">16</th>
                      <th class="hd_gd">17</th>
                      <th class="hd_gd">18</th>
                      <th class="hd_gd">19</th>
                      <th class="hd_gd">20</th>
                      <th class="hd_gd">21</th>
                      
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
                            <td  align="center" class="<%=classStyle%>">
                                <%=i++%>.
                            </td>
                            <td  class="<%=classStyle%>" align="left" >${row.District}</td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=AR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.ApplicationRegistered}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UDP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsToBeUploaded}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UD&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsUploaded}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UDR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsUploadedRejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VDP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsVerifiedPending}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VD&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsVerified}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VDR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.DocumentsVerifiedRejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.MPDOPending}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MA&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.MPDOApproved}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MR&districtId=${row.DistrictId}&urbanId=<%=urbanId%> ',
                                'Details',1000,650);return false;" >
                                    ${row.MPDORejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.PDPending}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PA&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.PDApproved}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.PDRejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.ScheduleCampPending}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCA&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.ScheduleCampApproved}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.ScheduleCampRejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=CI&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.certificateIssued}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=CR&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.certificateRejected}</a></td>
                            <td  class="<%=classStyle%>" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=OAP&districtId=${row.DistrictId}&urbanId=<%=urbanId%>',
                                'Details',1000,650);return false;" >
                                    ${row.ovrAllPending}</a></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center" class="hd_gd"><b>Total</b></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=AR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=applicationRegistered%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UDP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsToBeUploaded%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UD&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsUploaded%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=UDR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsUploadedRejected%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VDP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsVerifiedPending%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VD&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsVerified%></b></a></td>
                        <td class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=VDR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=documentsVerifiedRejected%></b></a></td>

                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=mPDOPending%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MA&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=mPDOApproved%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=MR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=mPDORejected%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=pDPending%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PA&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=pDApproved%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=PR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=pDRejected%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=scheduleCampPending%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCA&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=scheduleCampApproved%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=SCR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=scheduleCampRejected%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=CI&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=certificateIssued%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=CR&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=certificateRejected%></b></a></td>
                        <td  class="hd_gd" align="center"><a href="javascript: void(0)"  onclick="OpenPopupCenter('callCentreReport.do?mode=getIndividualReportBasedOnStatus&status=OAP&districtId=<%=districtId%>&urbanId=<%=urbanId%> ',                                                 'Details',1000,650);return false;" >
                                <b><%=ovrAllPending%></b></a></td>


                    </tr>
                  </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
            </logic:notEmpty>

        </html:form>

    </body>
</html>
