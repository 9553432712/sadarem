<%-- 
    Document   : reportBasedOnStatusPrint
    Created on : May 24, 2013, 2:25:33 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <%
                    String districtId = "";
                    if (request.getAttribute("districtId") != null) {
                        districtId = request.getAttribute("districtId").toString();
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
                    int i = 1;
        %>

        <logic:notEmpty name="ReportList" scope="request">
            <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">
                <tr>
                    <th style="text-align: center">
                           District Wise Grievance Report
                    </th>
                </tr>
                <tr>
                    <td>
                        <table border="1" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">

                           
                         
                            

                            <tr>
                                <th align="center"  rowspan="2">
                                    SNo.
                                </th>
                                <th  align="center" rowspan="2">District</th>
                                <th  align="center" rowspan="2">Application Registered</th>
                                <th  align="center" colspan="3">Documents Upload</th>
                                <th  align="center" colspan="3">Field Verification</th>
                                <th  align="center" colspan="3">MPDO </th>
                                <th  align="center" colspan="3">PD</th>
                                <th  align="center" colspan="3">Schedule To Camp</th>
                                <th  align="center" colspan="2">Certificate</th>
                                <th  align="center" rowspan="2">OverAll Pending</th>

                            </tr>
                            <tr>

                                <th  align="center" >To be Uploaded</th>
                                <th  align="center" >Uploaded</th>
                                <th  align="center"> Rejected</th>
                                <th  align="center" >For Field Verification</th>
                                <th  align="center" >Field Verification Completed</th>
                                <th  align="center"> Rejected</th>

                                <th  align="center" >Pending</th>
                                <th  align="center" >Approved</th>
                                <th  align="center"> Rejected</th>
                                <th  align="center" >Pending</th>
                                <th  align="center" >Approved</th>
                                <th  align="center"> Rejected</th>
                                <th  align="center" >Pending</th>
                                <th  align="center" >Approved</th>
                                <th  align="center"> Rejected</th>
                                <th  align="center"> Issued</th>
                                <th  align="center"> Rejected</th>



                            </tr>
                            <logic:iterate id="row" name="ReportList">
                                <tr>
                                    <td  align="center">
                                        <%=i++%>.
                                    </td>
                                    <td  align="left">${row.District}</td>
                                    <td  align="center">${row.ApplicationRegistered}</td>
                                    <td  align="center">${row.DocumentsToBeUploaded}</td>
                                    <td  align="center">${row.DocumentsUploaded}</td>
                                    <td  align="center">${row.DocumentsUploadedRejected}</td>
                                    <td  align="center">${row.DocumentsVerifiedPending}</td>
                                    <td  align="center">${row.DocumentsVerified}</td>
                                    <td  align="center">${row.DocumentsVerifiedRejected}</td>
                                    <td  align="center">${row.MPDOPending}</td>
                                    <td  align="center">${row.MPDOApproved}</td>
                                    <td  align="center">${row.MPDORejected}</td>
                                    <td  align="center">${row.PDPending}</td>
                                    <td  align="center">${row.PDApproved}</td>
                                    <td  align="center">${row.PDRejected}</td>
                                    <td  align="center">${row.ScheduleCampPending}</td>
                                    <td  align="center">${row.ScheduleCampApproved}</td>
                                    <td  align="center">${row.ScheduleCampRejected}</td>
                                    <td  align="center">${row.certificateIssued}</td>
                                    <td  align="center">${row.certificateRejected}</td>
                                    <td  align="center">${row.ovrAllPending}</td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td  colspan="2" align="left"><b>Total</b></td>
                                <td  align="center"><b><%=applicationRegistered%></b></td>
                                <td  align="center"><b><%=documentsToBeUploaded%></b></td>
                                <td  align="center"><b><%=documentsUploaded%></b></td>
                                <td  align="center"><b><%=documentsUploadedRejected%></b></td>
                                <td  align="center"><b><%=documentsVerifiedPending%></b></td>
                                <td  align="center"><b><%=documentsVerified%></b></td>
                                <td  align="center"><b><%=documentsVerifiedRejected%></b></td>

                                <td  align="center"><b><%=mPDOPending%></b></td>
                                <td  align="center"><b><%=mPDOApproved%></b></td>
                                <td  align="center"><b><%=mPDORejected%></b></td>
                                <td  align="center"><b><%=pDPending%></b></td>
                                <td  align="center"><b><%=pDApproved%></b></td>
                                <td  align="center"><b><%=pDRejected%></b></td>
                                <td  align="center"><b><%=scheduleCampPending%></b></td>
                                <td  align="center"><b><%=scheduleCampApproved%></b></td>
                                <td  align="center"><b><%=scheduleCampRejected%></b></td>
                                <td  align="center"><b><%=certificateIssued%></b></td>
                                <td  align="center"><b><%=certificateRejected%></b></td>
                                <td   align="center"><b><%=ovrAllPending%></b></td>


                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </logic:notEmpty>
    </body>
</html>
