<%-- 
    Document   : statusCountForPWDVillageReportView
    Created on : May 17, 2010, 4:01:15 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<% String fromdate = (String) request.getAttribute("From_Date");%>
<% String todate = (String) request.getAttribute("To_Date");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html:html>

    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body><br/>

        <logic:present name="dailyreportlist" scope="request">

            <table  align="center" border="0" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr>
                    <th align="center" class="labelBlue">S.No</th>
                    <th align="center" class="labelBlue">VillageName</th>
                    <th align="center" class="labelBlue">Total Records</th>
                </tr>
                <% int i = 0;%>
                <logic:iterate id="modify" name="dailyreportlist" scope="request">
                    <html:hidden name="modify" property="village_name"/>
                    <html:hidden name="modify" property="fromdate"/>
                    <html:hidden name="modify" property="todate"/>
                    <tr>
                        <td  style="text-align: center"><%=++i%>.</td>
                        <td  class="label">
                            <bean:write name="modify" property="village_name"/></td>
                        <td  style="text-align: center"> <bean:write name="modify" property="total"/></td>

                    </tr>
                </logic:iterate>
                <tr>
                    <td style="text-align: center" colspan="4">
                        <%    ArrayList ar2 = new ArrayList();
                                    ar2 = (ArrayList) request.getAttribute("dailyreportlist");
                                    String fromdate1 = (String) request.getAttribute("From_Date");
                                    String todate1 = (String) request.getAttribute("To_Date");
                                    application.setAttribute("FromDate", fromdate1);
                                    application.setAttribute("ToDate", todate1);
                                    application.setAttribute("arraylist1", ar2);%>
                        <a href="reportforvillagewisecountexcel.xls" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="reportforvillagewisecountprint.xls" target="_blank">
                            Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </logic:present>

    </body>
    <p>&nbsp;</p>

</html:html>


