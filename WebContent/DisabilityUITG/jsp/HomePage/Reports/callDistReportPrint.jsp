<%-- 
    Document   : callDistReportPrint
    Created on : May 24, 2013, 3:29:25 PM
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
                    int i = 1;
                    int assessPendingNewComp = 0;
                    int dOBPendingNewComp = 0;
                    int duplPendingNewComp = 0, iDPendingNewComp = 0;
                    int namePendingNewComp = 0, nCPendingNewComp = 0;
                    int physiPendingNewComp = 0, ressessPendingNewComp = 0;
                    int renewalPendingNewComp = 0, relationNamePendingNewComp = 0;
                    int totalPending = 0;
                    ArrayList reportList = new ArrayList();
                    if (request.getAttribute("DistrictAllList") != null) {
                        reportList = (ArrayList) request.getAttribute("DistrictAllList");
                        Iterator iter = reportList.iterator();
                        while (iter.hasNext()) {
                            Map m = (Map) iter.next();
                            if (districtId.equalsIgnoreCase("ALL")) {

                                assessPendingNewComp = assessPendingNewComp + Integer.parseInt(m.get("AssessPendingNewComp").toString());

                                dOBPendingNewComp = dOBPendingNewComp + Integer.parseInt(m.get("DOBPendingNewComp").toString());

                                duplPendingNewComp = duplPendingNewComp + Integer.parseInt(m.get("DuplPendingNewComp").toString());

                                iDPendingNewComp = iDPendingNewComp + Integer.parseInt(m.get("IDPendingNewComp").toString());

                                namePendingNewComp = namePendingNewComp + Integer.parseInt(m.get("NamePendingNewComp").toString());

                                nCPendingNewComp = nCPendingNewComp + Integer.parseInt(m.get("NCPendingNewComp").toString());

                                physiPendingNewComp = physiPendingNewComp + Integer.parseInt(m.get("PhysiPendingNewComp").toString());

                                ressessPendingNewComp = ressessPendingNewComp + Integer.parseInt(m.get("RessessPendingNewComp").toString());

                                renewalPendingNewComp = renewalPendingNewComp + Integer.parseInt(m.get("RenewalPendingNewComp").toString());

                                relationNamePendingNewComp = relationNamePendingNewComp + Integer.parseInt(m.get("RelationNamePendingNewComp").toString());

                            } else {
                                totalPending = totalPending + Integer.parseInt(m.get("Pending").toString());
                            }
                        }
                    }

        %>
        <table border="1" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">
            <tr>
                <td style="text-align: center">
                    District Wise Grievances Report
                </td>
            </tr>
        </table>

        <logic:notEmpty name="DistrictAllList" scope="request">

            <table border="1" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">
                <tr>
                    <th align="center"  >
                        SNo.
                    </th>
                    <%if (districtId.equalsIgnoreCase("ALL")) {%>
                    <th align="center"  >
                        District
                    </th>


                    <th  align="center" >
                        Assessment Completed. But Not Getting Certificate Pending
                    </th>
                    <th  align="center" >
                        Date of Birth Pending
                    </th>
                    <th  align="center" >
                        Duplicate Certificate Pending
                    </th>
                    <th  align="center" >
                        Identification Marks Pending
                    </th>
                    <th  align="center" >
                        Name Pending
                    </th>
                    <th  align="center" >
                        New Certificate Pending
                    </th>

                    <th  align="center" >
                        Physical Impairment Pending
                    </th>
                    <th  align="center" >
                        Re Assessment Pending
                    </th>
                    <th  align="center" >
                        Relation Name Pending
                    </th>
                    <th  align="center" >
                        Renewal Pending
                    </th>
                    <%} else {%>
                    <th align="center"  >
                        Request Type
                    </th>
                    <th  align="center" >
                        Pending
                    </th>
                    <%}%>

                </tr>

                <logic:iterate id="row" name="DistrictAllList">
                    <tr>
                        <td  align="center">
                            <%=i++%>.
                        </td>
                        <%if (districtId.equalsIgnoreCase("ALL")) {%>

                        <td  align="left">${row.District}</td>

                        <td  align="center">${row.AssessPendingNewComp}</td>

                        <td  align="center">${row.DOBPendingNewComp}</td>

                        <td  align="left">${row.DuplPendingNewComp}</td>

                        <td  align="center">${row.IDPendingNewComp}</td>

                        <td  align="center">${row.NamePendingNewComp}</td>

                        <td  align="left">${row.NCPendingNewComp}</td>

                        <td  align="center">${row.PhysiPendingNewComp}</td>

                        <td  align="center">${row.RessessPendingNewComp}</td>

                        <td  align="center">${row.RenewalPendingNewComp}</td>

                        <td  align="center">${row.RelationNamePendingNewComp}</td>

                        <%} else {%>
                        <td  align="center">${row.RequestName}</td>

                        <td  align="center">${row.Pending}</td>

                        <%}%>

                    </tr>

                </logic:iterate>
                <tr>
                    <th   align="center" colspan="2"><b>Total</b></th>

                    <%if (districtId.equalsIgnoreCase(
                                        "ALL")) {%>
                    <th  align="center"><b><%=assessPendingNewComp%></b></th>

                    <th  align="center"><b><%=dOBPendingNewComp%></b></th>

                    <th  align="center"><b><%=duplPendingNewComp%></b></th>

                    <th  align="center"><b><%=iDPendingNewComp%></b></th>

                    <th  align="center"><b><%=namePendingNewComp%></b></th>

                    <th  align="center"><b><%=nCPendingNewComp%></b></th>

                    <th  align="center"><b><%=physiPendingNewComp%></b></th>

                    <th  align="center"><b><%=ressessPendingNewComp%></b></th>

                    <th  align="center"><b><%=renewalPendingNewComp%></b></th>

                    <th  align="center"><b><%=relationNamePendingNewComp%></b></th>
                    <%} else {%>
                    <th  align="center"><b><%=totalPending%></b></th>
                    <%}%>


                </tr>

            </table>
        </logic:notEmpty>
    </body>
</html>

