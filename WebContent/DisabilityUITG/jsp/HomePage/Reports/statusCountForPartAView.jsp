<%--
    Document   : PWDsPersonalInformation
    Created on : Feb 23, 2011, 3:03:56 PM
    Author     : 509865
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<% String fromdate = (String) request.getParameter("fromdate");%>
<% String todate = (String) request.getParameter("todate");%>
<% String districtname = (String) request.getParameter("districtname");%>
<% String Districtname = (String) request.getParameter("districtname");%>
<% String districtid = (String) request.getParameter("districtid");%>
<% int existingpensionerscount = (Integer) request.getAttribute("existingcount");%>
<% int partACount = (Integer) request.getAttribute("partACount");%>
<% int orthoCount = (Integer) request.getAttribute("orthoCount");%>
<% int visualCount = (Integer) request.getAttribute("visualCount");%>
<% int hearingCount = (Integer) request.getAttribute("hearingCount");%>
<% int MRCount = (Integer) request.getAttribute("MRCount");%>
<% int MICount = (Integer) request.getAttribute("MICount");%>
<% int MDCount = (Integer) request.getAttribute("MDCount");%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script src="./DisabilityUITG/js/Validation.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script >

            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (isEmpty(fromdate,"From Date must be selected!")==false)
                    {
                        fromdate.focus();
                        return false
                    }
                    if (isEmpty(todate,"To Date must be selected!")==false)
                    {
                        todate.focus();
                        return false
                    }
                    if( dateValidation(forms[0].fromdate,forms[0].todate)==false)
                    {
                        return false
                    }

                }
            }
        </script>
    </head>

    <body>

        <html:form action="districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA" method="post" onsubmit="return validate_form(this)">

            <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="inputform" border="1">
                <tr><th colspan="6">Select PWD's Personal Information</th></tr>
                <tr>
                    <td>
                        <table  align="center" cellspacing="0" cellpadding="0" width="100%" class="inputform" border="0">

                            <tr>
                                <td  >From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromdate" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td  >To Date<font color="red"><b>*</b></font>
                                    <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].todate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                               
                               

                                    <td colspan="8" style="text-align: center">
                                    <input type="Submit" name="card" value="Submit" class="button"></td>
                            </tr>
                        </table>
                        <%
                                    String msg = (String) request.getAttribute("msg");

                        %>
                        <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                    </td>

                </tr>

            </table>

            <br>
            <logic:present name="dailyreportlist" scope="request">
                <table  align="center" cellspacing="1" cellpadding="0" width="90%" class="inputform" border="0">
                   

                    <tr><th  align="center" colspan="10">PWD's Personal Information Report</th></tr>
                    <% if (districtname == null) {%><tr><td></td></tr> <% } else {%>
                    <tr><th  align="center" colspan="10">District: <%=districtname%> </th></tr>
                    <% }%>

                    <tr>
                        <th align="center" >S.No</th>
                        <th ALIGN="center" >Mandal Name</th>
                        <th align="center" >Existing Pensioners</th>
                        <th align="center" >Part-A</th>
                        <th align="center" >P.I</th>
                        <th align="center" >V.I</th>
                        <th align="center" >H.I</th>
                        <th align="center" >M.R</th>
                        <th align="center" >M.I</th>
                        <th align="center" >M.D</th>
                    </tr>
                    <% int i = 0;%>
                    <logic:iterate id="modify" name="dailyreportlist" scope="request">
                        <html:hidden name="modify" property="mandal_name"/>
                       <%-- <html:hidden name="modify" property="fromdate"/>
                        <html:hidden name="modify" property="todate"/>--%>
                        <%if (i % 2 == 1) {%>
                        <tr>
                            <td  align="center"   ><%=++i%></td>
                            <td ><bean:write name="modify" property="mandal_name"/></td>
                            <td ><bean:write name="modify" property="existingpensioners"/></td>
                            <td ><bean:write name="modify" property="partacount"/></td>
                            <td ><bean:write name="modify" property="ortho"/></td>
                            <td ><bean:write name="modify" property="visual"/></td>
                            <td ><bean:write name="modify" property="hearing"/></td>
                            <td ><bean:write name="modify" property="mentalretardation"/></td>
                            <td ><bean:write name="modify" property="mentalillness"/></td>
                            <td  nowrap ><bean:write name="modify" property="multipledisability"/></td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td  align="center"   ><%=++i%></td>
                            <td ><bean:write name="modify" property="mandal_name"/></td>
                            <td ><bean:write name="modify" property="existingpensioners"/></td>
                            <td ><bean:write name="modify" property="partacount"/></td>
                            <td ><bean:write name="modify" property="ortho"/></td>
                            <td ><bean:write name="modify" property="visual"/></td>
                            <td ><bean:write name="modify" property="hearing"/></td>
                            <td ><bean:write name="modify" property="mentalretardation"/></td>
                            <td ><bean:write name="modify" property="mentalillness"/></td>
                            <td  nowrap ><bean:write name="modify" property="multipledisability"/></td>
                        </tr>

                        <%}%>
                    </logic:iterate>
                    <tr>
                        <td >&nbsp;&nbsp;</td>
                        <td ><b>Total</b></td>
                        <td ><b><%=existingpensionerscount%></b></td>
                        <td ><b><%=partACount%></b></td>
                        <td ><b><%=orthoCount%></b></td>
                        <td ><b><%=visualCount%></b></td>
                        <td ><b><%=hearingCount%></b></td>
                        <td ><b><%=MRCount%></b></td>
                        <td ><b><%=MICount%></b></td>
                        <td ><b><%=MDCount%></b></td>

                    </tr>
                </table><br>


                <table align="center">
                    <%
                                ArrayList partAList = new ArrayList();
                                partAList = (ArrayList) request.getAttribute("dailyreportlist");
                                session.setAttribute("partAList", partAList);
                                session.removeAttribute("partADistrictList");
                    %>
                    <a href="reportforpartacountexcel.xls?fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                             height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="statuscountforpartaprint.xls?fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                        Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </table>
            </logic:present>

        </html:form>
    </body>
</html:html>