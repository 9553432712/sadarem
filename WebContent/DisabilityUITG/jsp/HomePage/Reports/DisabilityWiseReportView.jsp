<%--
    Document   : PWDsPersonalInformation
    Created on : Feb 23, 2011, 3:03:56 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
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

    <body onload="ShowDate()">


        <html:form action="dailyreportaction.do?getDisabilityWise=getDisabilityWise" method="post" onsubmit="return validate_form(this)">

            <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="inputform" border="1">
                            <tr>
                                <th>
                                    Select PWD's Personal Information
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    <table  align="center" cellspacing="0" cellpadding="0" width="100%" class="inputform" border="0">
                                        <tr>
                                <td>From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromdate" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td >To Date<font color="red"><b>*</b></font>
                                    <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].todate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>

                                <td  align="center"><input type="Submit" name="card" value="Submit" class="button"></td>
                            </tr>
                                    </table>
                                </td>
                            </tr>
                            
                        </table>
                        <%
                                    String msg = (String) request.getAttribute("msg");

                        %>
                        <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                   <br>
            <logic:present name="dailyreportlist" scope="request">
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr><th  align="center" colspan="3">State Wise Disability Categories Report</th></tr>
                    <tr><th align="center" >S.No</th>
                        <th ALIGN="center" >Type Of Disability</th>
                        <th align="center" >Count</th>
                    </tr>
                    <% int i = 0;%>
                    <logic:iterate id="modify" name="dailyreportlist" scope="request">

                        <%--<html:hidden name="modify" property="fromdate"/>
                        <html:hidden name="modify" property="todate"/>--%>

                        <%if (i % 2 == 1) {%>
                        <tr>
                            <td align="center"   ><%=++i%></td>
                            <td  align="center">
                                <bean:write name="modify" property="typeofdisability"/></td>
                            <td  align="center">
                                <bean:write name="modify" property="disabilitycount"/></td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td  align="center"  ><%=++i%></td>
                            <td align="center">
                                <bean:write name="modify" property="typeofdisability"/></td>
                            <td align="center">
                                <bean:write name="modify" property="disabilitycount"/></td>
                        </tr>
                        <%}%>
                    </logic:iterate>
                </table>
                <table align="center">
                    <%    ArrayList arl = new ArrayList();
                                arl = (ArrayList) request.getAttribute("dailyreportlist");
                                String fromdate1 = (String) request.getAttribute("From_Date");
                                String todate1 = (String) request.getAttribute("To_Date");
                                session.setAttribute("disabilitywislist", arl);%>
                    <a href="disabilitywiseexcel.xls?FromDate=<%=fromdate1%>&ToDate=<%=todate1%>" target="_blank">
                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                             height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </table>

            </logic:present>
        </html:form>
    </body>
</html:html>