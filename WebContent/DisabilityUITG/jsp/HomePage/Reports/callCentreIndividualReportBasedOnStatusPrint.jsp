<%-- 
    Document   : callCentreIndividualReportBasedOnStatusPrint
    Created on : Oct 31, 2013, 3:58:48 PM
    Author     : 310926
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

        
        <link rel="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css" type="text/css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <%
                    String districtId = "";
                    if (request.getAttribute("districtId") != null) {
                        districtId = request.getAttribute("districtId").toString();
                    }

                    int i = 1;
        %>

        <logic:notEmpty name="ReportList" scope="request">

            <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">
                <tr>
                    <th>
                        District and Status Wise Grievances Report
                    </th>
                </tr>
                <tr>

                    <td>
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                            <tr>
                                <th align="center" class="formhdbg" >S No.</th>
                                <th class="formhdbg" align="center" >Request ID</th>
                                <th class="formhdbg" align="center" >SADAREM ID</th>
                                <th class="formhdbg" align="center" >Pension No.</th>
                                <th class="formhdbg" align="center" >SurName</th>
                                <th class="formhdbg" align="center" >FirstName </th>
                                <th class="formhdbg" align="center" >District</th>
                                <th class="formhdbg" align="center" >Mandal</th>
                                <th class="formhdbg" align="center" >Request Type</th>
                                <th class="formhdbg" align="center" >Generated SADAREM ID</th>
                                <th class="formhdbg" align="center" >Mobile Number</th>
                                <th class="formhdbg" align="center" >Request Raised Date</th>
                                <th class="formhdbg" align="center" >Scheduled Camp Date</th>

                            </tr>

                            <logic:iterate id="row" name="ReportList">
                                <tr>
                                    <td class="formaltbg" align="center"><%=i++%>.</td>
                                    <td class="formaltbg" align="center">${row.requestNo}</td>
                                    <td class="formaltbg" align="center">${row.sadaremId}</td>
                                    <td class="formaltbg" align="left">${row.pensionNo}</td>
                                    <td class="formaltbg" align="left">${row.surName}</td>
                                    <td class="formaltbg" align="left">${row.firstName}</td>
                                    <td class="formaltbg" align="left">${row.district}</td>
                                    <td class="formaltbg" align="left">${row.mandal}</td>
                                    <td class="formaltbg" align="center">${row.requestName}</td>
                                    <td class="formaltbg" align="center">${row.generatedSadaremId}</td>
                                    <td class="formaltbg" align="center">${row.mobileNo}</td>
                                    <td class="formaltbg" align="center">${row.raisedDate}</td>
                                    <td class="formaltbg" align="center">${row.scheduledCampdate}</td>

                                </tr>

                            </logic:iterate>

                        </table>
                    </td>
                </tr>
            </table>
        </logic:notEmpty>
    </body>
</html>

