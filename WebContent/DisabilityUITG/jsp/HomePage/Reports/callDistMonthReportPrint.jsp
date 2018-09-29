<%-- 
    Document   : callDistMonthReportPrint
    Created on : May 24, 2013, 3:39:01 PM
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
        <%int i = 1;
                    int registerdBeforethismonth = 0, registerdthismonth = 0;
                    int resolvdthismonth = 0, pending = 0;
                    ArrayList reportList = new ArrayList();
                    if (request.getAttribute("DistrictMonthwiseList") != null) {
                        reportList = (ArrayList) request.getAttribute("DistrictMonthwiseList");
                        Iterator iter = reportList.iterator();
                        while (iter.hasNext()) {
                            Map m = (Map) iter.next();
                            registerdBeforethismonth = registerdBeforethismonth + Integer.parseInt(m.get("registerdBeforethismonth").toString());
                            registerdthismonth = registerdthismonth + Integer.parseInt(m.get("registerdthismonth").toString());
                            resolvdthismonth = resolvdthismonth + Integer.parseInt(m.get("resolvdthismonth").toString());
                            pending = pending + Integer.parseInt(m.get("pending").toString());

                        }
                    }

        %>

        <table border="0" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">
            <tr>
                <td style="text-align: center">
                    Month Wise Grievances Report
                </td>
            </tr>
        </table>

        <logic:notEmpty name="DistrictMonthwiseList" scope="request">
            <table border="0" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">

                <tr>
                    <th align="center" >
                        SNo.
                    </th>
                    <th  align="center">District</th>
                    <th  align="center">No of complaints registered </th>
                    <th  align="center"> No of complaints registered till selected month </th>
                    <th  align="center"> No of complaints resolved till selected month (i.e. Certificate Issued)  </th>
                    <th  align="center"> No of complaints pending </th>

                </tr>
                <logic:iterate id="row" name="DistrictMonthwiseList">
                    <tr>
                        <td  align="center">
                            <%=i++%>.
                        </td>
                        <td  align="left">${row.District}</td>
                        <td  align="center">${row.registerdBeforethismonth}</td>
                        <td  align="center">${row.registerdthismonth}</td>
                        <td  align="center">${row.resolvdthismonth}</td>
                        <td  align="center">${row.pending}</td>

                    </tr>

                </logic:iterate>
                <tr>
                    <th  align="center" colspan="2"><b>Total</b></th>
                    <th  align="center"><b><%=registerdBeforethismonth%></b></th>
                    <th  align="center"><b><%=registerdthismonth%></b></th>
                    <th  align="center"><b><%=resolvdthismonth%></b></th>
                    <th  align="center"><b><%=pending%></b></th>
                </tr>
            </table>
        </logic:notEmpty>
    </body>
</html>

