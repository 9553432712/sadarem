<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 4100058
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.text.SimpleDateFormat"%>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;
            String f = null, t = null;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String name = (String) request.getParameter("names");
            String dis = (String) request.getParameter("disability");
            if (fromdate != null && fromdate.contains("-")) {
                f = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
            }
            if (todate != null && todate.contains("-")) {
                t = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
            }



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>





    </head>
    <body onload="window.print();">


        <table  align="center" cellspacing="0" border="0" cellpadding="0" class="inputform" width="90%">

            <tr>
                <td>
                    <p>  Disability Causes Performance Ability</p>


                    <p>  As On <%=f%> To <%=t%> <% if (name != null) {%>, <%=name%> <%}%> </p>
                    <% int j = 1;
            i = 1;
            int k = 1;%>

                    <logic:notEmpty name="hiWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Hearing Impairment Causes, Performance ability, Percentage wise Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%">No</th>
                                <th  align="center" width="18%">Categories</th>
                                <th  align="center" width="18%">Sub Categories</th>
                                <th width="10%"  align="center">Male Count</th>
                                <th width="10%" align="center">Male %</th>
                                <th width="10%"  align="center">Female Count</th>
                                <th width="10%"  align="center">Female %</th>
                                <th width="10%"  align="center">Total Count</th>
                                <th width="10%"  align="center">Total %</th>
                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="hiWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>

                                <%if (j == 1) {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left" width="18%" rowspan="17">Hearing  Impairment Causes wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>
                                    <%} else if (j <= 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <% } else if (j == 11) {
                                    j++;
                                %></table>
                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" >1</td>
                                    <td  align="left" width="18%" rowspan="17">Hearing  Impairment Performance ability wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr></table>

                            <%} else if (j == 12) {
                                j++;
                            %>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left" width="18%" rowspan="17">Hearing Impairment Percentage wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>




                                <%} else {
    j++;%>
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <%}%>



                            </logic:iterate></table></logic:notEmpty>


                    <%  j = 1;
            i = 1;
            k = 1;%>

                    <logic:notEmpty name="miWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Mental Illness Causes, Performance ability, Percentage wise Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%">No</th>
                                <th  align="center" width="18%">Categories</th>
                                <th  align="center" width="18%">Sub Categories</th>
                                <th width="10%"  align="center">Male Count</th>
                                <th width="10%" align="center">Male %</th>
                                <th width="10%"  align="center">Female Count</th>
                                <th width="10%"  align="center">Female %</th>
                                <th width="10%"  align="center">Total Count</th>
                                <th width="10%"  align="center">Total %</th>
                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="miWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>

                                <%if (j == 1) {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left" width="18%" rowspan="17">Mental Illness Causes wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>
                                    <%} else if (j <= 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>



                                <%} else if (j == 11) {
                                    j++;
                                %>
                            </table>
                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left" width="18%" rowspan="17">Mental Illness Percentage wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>




                                <%} else {
    j++;%>
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <%}%>



                            </logic:iterate>
                        </table>

                    </logic:notEmpty>
                    <% i = 1;
            j = 1;
            k = 1;%>
                    <logic:notEmpty name="viWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Visual Impairment Causes, Performance ability, Category, Percentage wise report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%">No</th>
                                <th  align="center" width="18%">Categories</th>
                                <th  align="center" width="18%">Sub Categories</th>
                                <th width="10%"  align="center">Male Count</th>
                                <th width="10%" align="center">Male %</th>
                                <th width="10%"  align="center">Female Count</th>
                                <th width="10%"  align="center">Female %</th>
                                <th width="10%"  align="center">Total Count</th>
                                <th width="10%"  align="center">Total %</th>
                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="viWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>

                                <%if (j == 1) {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left" width="18%" rowspan="17">Visual Impairment Causes wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>
                                    <%} else if (j <= 7) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <% } else if (j == 8) {
                                    j++;
                                %></table>
                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" >1</td>
                                    <td  align="left" width="18%" rowspan="17">Visual Impairment Performance ability wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>



                                <%} else if (j == 9) {
    k = 1;
    j++;%>

                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                            </table>




                            <%} else if (j == 10) {
                                j++;
                            %>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left" width="18%" rowspan="17">Visual Impairment Category wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>




                                <%} else {
    j++;%>
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <%}%>



                            </logic:iterate>
                        </table>

                    </logic:notEmpty>



                    <%  j = 1;
            i = 1;
            k = 1;%>



                    <logic:notEmpty name="mrWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Mental Retardation Causes, Performance ability, Percentage wise report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%">No</th>
                                <th  align="center" width="18%">Categories</th>
                                <th  align="center" width="18%">Sub Categories</th>
                                <th width="10%"  align="center">Male Count</th>
                                <th width="10%" align="center">Male %</th>
                                <th width="10%"  align="center">Female Count</th>
                                <th width="10%"  align="center">Female %</th>
                                <th width="10%"  align="center">Total Count</th>
                                <th width="10%"  align="center">Total %</th>
                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="mrWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>

                                <%if (j == 1) {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left" width="18%" rowspan="17">Mental Retardation Causes wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>
                                    <%} else if (j <= 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center">${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <% } else if (j == 11) {
                                    j++;
                                %></table>
                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" >1</td>
                                    <td  align="left" width="18%" rowspan="17">Mental Retardation Performance ability wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr></table>

                            <%} else if (j == 12) {
                                j++;
                            %>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left" width="18%" rowspan="17">Mental Retardation  Percentage wise</td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>




                                <%} else {
    j++;%>
                                <tr>  <td align="center"  width="4%" ><%=k++%></td>
                                    <td  align="left"  width="18%">${row.col}</td>
                                    <td width="10%" style="text-align:center"> ${row.male}</td>
                                    <td width="10%" style="text-align:center">${row.mp}</td>
                                    <td width="10%" style="text-align:center">${row.female}</td>
                                    <td width="10%" style="text-align:center">${row.fp}</td>
                                    <td width="10%" style="text-align:center">${row.tot}</td>
                                    <td width="10%"  >${row.tp}</td></tr>

                                <%}%>



                            </logic:iterate>
                        </table>

                    </logic:notEmpty>



                    <logic:notEmpty name="vifWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Visual Impairment  - Needs Assessment Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%" rowspan="2">No</th>
                                <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                <th  align="center" width="12%" >Count</th>
                                <th  align="center" width="12%" >%</th>
                                <th width="12%"  align="center" >Count</th>
                                <th width="12%"  align="center" >%</th>

                            </tr>


                        </table><% i = 1;%>
                        <bean:define id="l" value="0"></bean:define>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="vifWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>
                                <%if (i < 8) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 8) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Assistive & Augmentative Devices</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 9 && i <= 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%} else if (i == 11) {
    i++;
    j = 11;%>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Visual Impairment General Needs</td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Education services</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>
                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (j > 11 && j < 14) {%>




                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (j == 14) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Education services</td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Vocational Training</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr></table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <% } else if (j == 15) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 16) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Vocational Training </td>
                                    <td width="12%" style="text-align:center"> ${row.l2}</td>
                                    <td width="12%" style="text-align:center">${row.tlp2}</td>
                                    <td width="12%" style="text-align:center">${row.g2}</td>
                                    <td width="12%" style="text-align:center">${row.tgp2}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Counseling and Guidance</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>


                                <% } else if (j == 17) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 18) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Counseling and guidance  </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%}%>




                            </logic:iterate>



                        </table><br><br>


                    </logic:notEmpty>



                    <logic:notEmpty name="mrfWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Visual Impairment  - Needs Assessment Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%" rowspan="2">No</th>
                                <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                <th  align="center" width="12%" >Count</th>
                                <th  align="center" width="12%" >%</th>
                                <th width="12%"  align="center" >Count</th>
                                <th width="12%"  align="center" >%</th>

                            </tr>


                        </table><% i = 1;%>
                        <bean:define id="l" value="0"></bean:define>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="mrfWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>
                                <%if (i < 7) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 7) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Therapeutic Intervention </td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 8 && i <= 9) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%} else if (i == 10) {%>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Assistive & Augmentative Devices</td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (i == 11) {%>

                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i == 12) {%>




                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr></table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>
                                    <td  align="left"  width="52%">Total Assistive & Augmentative Devices </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                                <%} else if (i == 13) {%>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (i == 14) {
    i++;
    j = 14;%>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Mental Retardation General Needs</td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Education services</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>
                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (j > 14 && j < 17) {%>




                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (j == 17) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Education services</td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Vocational Training</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr></table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <% } else if (j == 18) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 19) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Vocational Training </td>
                                    <td width="12%" style="text-align:center"> ${row.l2}</td>
                                    <td width="12%" style="text-align:center">${row.tlp2}</td>
                                    <td width="12%" style="text-align:center">${row.g2}</td>
                                    <td width="12%" style="text-align:center">${row.tgp2}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Counseling and Guidance</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>


                                <% } else if (j == 20) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 21) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Counseling and guidance  </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%}%>










                            </logic:iterate></table><br><br>


                    </logic:notEmpty>


                    <logic:notEmpty name="hifWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Hearing Impairment  - Needs Assessment Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%" rowspan="2">No</th>
                                <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                <th  align="center" width="12%" >Count</th>
                                <th  align="center" width="12%" >%</th>
                                <th width="12%"  align="center" >Count</th>
                                <th width="12%"  align="center" >%</th>

                            </tr>


                        </table><% i = 1;%>
                        <bean:define id="l" value="0"></bean:define>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="hifWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="thate" value="${row.tdate}"/>
                                <%if (i < 9) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 9) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Assistive & Augmentative Devices</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 10 && i <= 12) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%} else if (i == 13) {
    i++;
    j = 13;%>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Hearing Impairment General Needs</td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Education services</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>
                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (j >= 14 && j < 16) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (j == 16) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Education needs</td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Vocational Training</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr></table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <% } else if (j == 17) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 18) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Vocational Training </td>
                                    <td width="12%" style="text-align:center"> ${row.l2}</td>
                                    <td width="12%" style="text-align:center">${row.tlp2}</td>
                                    <td width="12%" style="text-align:center">${row.g2}</td>
                                    <td width="12%" style="text-align:center">${row.tgp2}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Counseling and Guidance</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>


                                <% } else if (j == 19) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 20) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Counseling and guidance  </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%}%>




                            </logic:iterate></table><br><br>


                    </logic:notEmpty>


                    <logic:notEmpty name="mifWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Mental Illness  - Needs Assessment Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%" rowspan="2">S.No</th>
                                <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                <th  align="center" width="12%" >Count</th>
                                <th  align="center" width="12%" >%</th>
                                <th width="12%"  align="center" >Count</th>
                                <th width="12%"  align="center" >%</th>

                            </tr>


                        </table><% i = 1;
            j = 7;%>
                        <bean:define id="l" value="0"></bean:define>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="mifWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>
                                <%if (i < 6) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 6) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">






                                <%} else if (i == 7) {
    i++;
    j = 7;%>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Mental illness General Needs</td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Education services</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>
                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (j >= 8 && j < 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (j == 10) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Education needs</td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Vocational Training</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr></table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <% } else if (j == 11) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 12) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Vocational Training </td>
                                    <td width="12%" style="text-align:center"> ${row.l2}</td>
                                    <td width="12%" style="text-align:center">${row.tlp2}</td>
                                    <td width="12%" style="text-align:center">${row.g2}</td>
                                    <td width="12%" style="text-align:center">${row.tgp2}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Counseling and Guidance</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>


                                <% } else if (j == 13) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 14) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Counseling and guidance  </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%}%>




                            </logic:iterate></table><br><br>


                    </logic:notEmpty>






                    <logic:notEmpty name="ohfWiseDetails">
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                            <tr>
                                <th align="center"  colspan="10" >Locomotor Disability  - Needs Assessment Report</th>
                            </tr>
                            <tr>
                                <th  align="center" width="4%" rowspan="2">S.No</th>
                                <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                <th  align="center" width="12%" >Count</th>
                                <th  align="center" width="12%" >%</th>
                                <th width="12%"  align="center" >Count</th>
                                <th width="12%"  align="center" >%</th>

                            </tr>


                        </table><% i = 1;
            j = 7;%>
                        <bean:define id="l" value="0"></bean:define>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                            <logic:iterate name="ohfWiseDetails" id="row">
                                <bean:define id="fDate" value="${row.fdate}"/>
                                <bean:define id="tDate" value="${row.tdate}"/>
                                <%if (i < 4) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 4) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total (Physiotherapy & Occupational Therapy)</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i >= 5 && i < 7) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 7) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Wheel Chair</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 8 && i < 9) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 9) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Tricycle</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 10 && i < 11) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 11) {%>
                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Walking Stick</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i >= 12 && i < 27) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 27) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Crutches</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">



                                <%} else if (i >= 28 && i < 29) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 29) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Walking Frame</td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>
                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Orthosis / Splint for upper Extremity</td></tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">




                                <%} else if (i >= 30 && i < 33) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 33) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Upper Extremity Orthosis </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Orthosis / Splint for Lower Extremity</td></tr>
                            </table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 34 && i < 40) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 40) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Lower Extremity Orthosis </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Spinal Orhtosis</td></tr>
                            </table>


                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i >= 41 && i < 43) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 43) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Lower Extremity Orthosis </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Prosthesis for Upper Extremity</td></tr>
                            </table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i >= 44 && i < 50) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 50) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Upper Extremity Orthosis </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Prosthesis for Lower Extremity</td></tr>
                            </table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i >= 51 && i < 56) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 56) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Lower extremity Prosthesis </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">ADL Equipment</td></tr>
                            </table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">





                                <%} else if (i >= 57 && i < 64) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                                <%} else if (i == 64) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total ADL Equipment </td>
                                    <td width="12%" style="text-align:center"> ${row.tl}</td>
                                    <td width="12%" style="text-align:center">${row.tlp}</td>
                                    <td width="12%" style="text-align:center">${row.tg}</td>
                                    <td width="12%" style="text-align:center">${row.tgp}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (i == 65) {%>

                                <tr>  <td align="center"  width="4%" ><%=i++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else if (i == 66) {
    i++;
    j = 66;%>

                            </table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                                <tr><td align="left"  colspan="6">Locomotor General Needs </td></tr>
                            </table>
                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>
                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Education services</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>
                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <%} else if (j > 66 && j < 69) {%>




                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>


                                <%} else if (j == 69) {%>

                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Education services</td>
                                    <td width="12%" style="text-align:center"> ${row.l1}</td>
                                    <td width="12%" style="text-align:center">${row.tlp1}</td>
                                    <td width="12%" style="text-align:center">${row.g1}</td>
                                    <td width="12%" style="text-align:center">${row.tgp1}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Vocational Training</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr></table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <% } else if (j == 70) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 71) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Vocational Training </td>
                                    <td width="12%" style="text-align:center"> ${row.l2}</td>
                                    <td width="12%" style="text-align:center">${row.tlp2}</td>
                                    <td width="12%" style="text-align:center">${row.g2}</td>
                                    <td width="12%" style="text-align:center">${row.tgp2}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>  <td align="center"  width="4%" >&nbsp;</td>
                                    <td  align="left"  width="48%">Counseling and Guidance</td>
                                    <td width="12%" style="text-align:center"> &nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                    <td width="12%" style="text-align:center">&nbsp;</td>
                                </tr>


                                <% } else if (j == 72) {





                                %>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>




                                <%} else if (j == 73) {%>


                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>

                            </table>

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                                <tr>
                                    <td  align="left"  width="52%">Total Counseling and guidance  </td>
                                    <td width="12%" style="text-align:center"> ${row.l3}</td>
                                    <td width="12%" style="text-align:center">${row.tlp3}</td>
                                    <td width="12%" style="text-align:center">${row.g3}</td>
                                    <td width="12%" style="text-align:center">${row.tgp3}</td>
                                </tr>

                            </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">


                                <%} else {%>
                                <tr>  <td align="center"  width="4%" ><%=j++%></td>
                                    <td  align="left"  width="48%">${row.col}</td>
                                    <td width="12%" style="text-align:center"> ${row.l}</td>
                                    <td width="12%" style="text-align:center">${row.lp}</td>
                                    <td width="12%" style="text-align:center">${row.g}</td>
                                    <td width="12%" style="text-align:center">${row.gp}</td>
                                </tr>





                                <%}%>





                            </logic:iterate></table><br><br>


                    </logic:notEmpty>
















                </td><tr></table>




        <logic:notEmpty name="ohWiseDetails">
            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">

                <tr>
                    <th align="center"  colspan="10" >Locomotor Disability, Sub Types, Causes, Percentage wise Report</th>
                </tr>
                <tr>
                    <th  align="center" width="4%">No</th>
                    <th  align="center" width="12%">Categories</th>
                    <th  align="center" width="12%">Sub Categories</th>
                    <th width="12%"  align="center">Male Count</th>
                    <th width="12%" align="center">Male %</th>
                    <th width="12%"  align="center">Female Count</th>
                    <th width="12%"  align="center">Female %</th>
                    <th width="12%"  align="center">Total Count</th>
                    <th width="12%"  align="center">Total %</th>
                </tr>
            </table>
            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                <logic:iterate name="ohWiseDetails" id="row">
                    <bean:define id="fDate" value="${row.fdate}"/>
                    <bean:define id="tDate" value="${row.tdate}&type=${row.col}"/>


                    <%if (j == 1) {%>
                    <tr>  <td align="center"  width="4%" ><%=j++%></td>
                        <td  align="left" width="12%" rowspan="17">Locomotor Disability Causes wise</td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center"> ${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>
                        <%} else if (j <= 16) {%>

                    <tr>  <td align="center"  width="4%" ><%=j++%></td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center"> ${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>



                    <%} else if (j == 17) {
                        j++;
                    %>
                </table>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                    <tr>  <td align="center"  width="4%" ><%=k++%></td>
                        <td  align="left" width="12%" rowspan="17">Locomotor Disability Causes wise</td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center"> ${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>
                        <%} else if (k <= 10) {%>

                    <tr>  <td align="center"  width="4%" ><%=k++%></td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center">${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>



                    <%} else if (k == 11) {
                        k = 1;
                        ;
                    %>
                </table>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"width="90%">
                    <tr>  <td align="center"  width="4%" ><%=k++%></td>
                        <td  align="left" width="12%" rowspan="17">Locomotor Disability Percentage wise</td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center">${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>




                    <%} else {
    j++;%>
                    <tr>  <td align="center"  width="4%" ><%=k++%></td>
                        <td  align="left"  width="12%">${row.col}</td>
                        <td width="12%" style="text-align:center"> ${row.male}</td>
                        <td width="12%" style="text-align:center">${row.mp}</td>
                        <td width="12%" style="text-align:center">${row.female}</td>
                        <td width="12%" style="text-align:center">${row.fp}</td>
                        <td width="12%" style="text-align:center">${row.tot}</td>
                        <td width="12%"  >${row.tp}</td></tr>

                    <%}%>



                </logic:iterate>
            </table>

        </logic:notEmpty>





    </body>
</html>