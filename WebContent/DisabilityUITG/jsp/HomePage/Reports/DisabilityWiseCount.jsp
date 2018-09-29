<%--
    Document   : PWDsPersonalInformation
    Created on : Feb 23, 2011, 3:03:56 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    <head>

        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>



        <%--<script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>--%>

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
            function ShowDate()
                {
                    var dt = new Date();
                    var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                    document.getElementById(8).value = d;

                }
        </script>
    </head>

    <body onload="ShowDate()">


        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <html:form action="dailyreportaction.do?getDisabilityWise=getDisabilityWise" method="post" onsubmit="return validate_form(this)">


           
            <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="inputform" border="1">
                            <tr>
                                <th colspan="4">
                                    R2.4 &nbsp; State Level Disability Wise Count
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="inputform">
                                        <tr>
                                <td>From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromdate" readonly="true" size="12" value="01/01/2010"/>
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

                                    <td  colspan="8" style="text-align: center">

                                    <input type="Submit" name="card" value="Submit" class="button"></td>
                            </tr>
                                    </table>
                                </td>
                            </tr>
                            
                        </table>
                        <%
                                    String msg = (String) request.getAttribute("msg");

                        %>
                        <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                   

        </html:form>
    </body>
</html:html>
