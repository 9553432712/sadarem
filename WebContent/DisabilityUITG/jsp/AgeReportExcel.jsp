<%-- 
    Document   : AgeReportExcel
    Created on : Jan 4, 2012, 5:27:01 PM
    Author     : 490058
--%>

<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 4100058
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %><%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villagesId");
            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;String f=null,t=null;

             String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");
                      String name=(String) request.getParameter("names");

                    String dis=(String) request.getParameter("disability");
if(fromdate!=null && fromdate.contains("-")){
                         f=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                         }
                     if(todate!=null && todate.contains("-")){
                         t=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
                         }

int rownum=9;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    <style type="text/css">
      </style>
    </head>
    <body>

   <logic:present name="empWiseDetails">
                    <logic:empty name="empWiseDetails">
                        <table align="center">  <tr>
                                <td  align="center" valign="top">
                                    <table  align="center">
                                        <tr><td  align="center" colspan="7"><font color="red"><b>No Data</b></font></td></tr>
                                    </table></td></tr></table>
                                </logic:empty>
                            </logic:present>
                            <logic:notEmpty name="empWiseDetails">
                                 <bean:define id="reg" value="0"/>
                                          <bean:define id="ass" value="0"/>
                                           <bean:define id="eli" value="0"/>
                                            <bean:define id="ar" value="0"/>
                                             <bean:define id="dr" value="0"/>

                    <div align="center" >
                         <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


                <tr><td   colspan=7 align="center" class="formaltbg" >
                 <b>  Age wise Report <br>

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%> <% if(name!=null) {%>, <%=name%> <%}%></font>   </b></td>
                </tr></table>
                        <table width="100%" border="0" cellspacing="0" cellpadding="10" align="center">
                            <tr>
                                <td height="445" align="center"  valign="top">
                                    <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                                        
                                        <tr>
                                            <td align="center" class="formhdbg" rowspan="2">S.No</td>
                                            <% if (district_id.equals("0")) {%>
                                            <td class="formhdbg" align="center" rowspan="2">District</td>
                                            <% } else if (mandal_id.equals("0")) {%>
                                            <td class="formhdbg" align="center" rowspan="2">Mandal</td>
                                            <% } else if (village_id.equals("0")) {%>
                                            <td class="formhdbg" align="center" rowspan="2">Village</td>
                                            <% } else if (!district_id.equals("0")) {%>
                                           <td class="formhdbg" align="center" rowspan="2">Habitation</td>
                                            <% }%>
                                            <td align="center" class="formhdbg" rowspan="2">Total Registered</td>
                                            <td align="center" class="formhdbg" rowspan="2">Total Assessed </td>
                                            <td align="center" class="formhdbg" rowspan="2">Total Eligible</td>
                                            <td align="center" class="formhdbg" colspan="2">Total Rejected</td>

                                        </tr>

                                        <tr>
                                            <td align="center" class="formhdbg" >AR</td>
                                            <td align="center" class="formhdbg" >DR</td>
                                        </tr>

                                        <% int j = 1;%>


                                        <logic:iterate name="empWiseDetails" id="row">
                                            <bean:define id="fDate" value="${row.fDate}"/>
                                            <bean:define id="tDate" value="${row.tDate}"/>
                                            <bean:define id="reg" value="${row.treg+reg}"/>
                                          <bean:define id="ass" value="${row.tass+ass}"/>
                                           <bean:define id="eli" value="${row.teli+eli}"/>
                                            <bean:define id="ar" value="${row.tar+ar}"/>
                                             <bean:define id="dr" value="${row.tdr+dr}"/>

                                            <%if (j % 2 == 1) {
                                                            j++;%>
                                            <tr>
                                                <td class="formbg" align="center">
                                                    <%=i++%>
                                                </td>
                                                <td class="formbg">
                                                    ${row.name}
                                                </td>
                                                <td class="formbg" align="center">
                                                   ${row.treg}
                                                </td>
                                                <td class="formbg" align="center">
                                                   ${row.tass}
                                                </td>
                                                <td class="formbg" align="center">
                                                   ${row.teli}
                                                </td>
                                                <td class="formbg" align="center">
                                                   ${row.tar}
                                                </td>
                                                <td class="formbg" align="center">
                                                   ${row.tdr} 
                                                </td>


                                            </tr>
                                            <%} else if (j % 2 == 0) {
                                                j++;
                                            %>


                                            <tr>
                                                <td class="formaltbg" align="center">
                                                    <%=i++%>
                                                </td>
                                                <td class="formaltbg">
                                                    ${row.name}
                                                </td>
                                                <td class="formaltbg" align="center">
                                                      ${row.treg}
                                                </td>
                                                <td class="formaltbg" align="center">
                                                      ${row.tass}
                                                </td>
                                                <td class="formaltbg" align="center">
                                                    ${row.teli}
                                                </td>
                                                <td class="formaltbg" align="center">
                                                    ${row.tar}
                                                </td>
                                                <td class="formaltbg" align="center">
                                                     ${row.tdr}
                                                </td>

                                            </tr>

                                            <%}%>


                                        </logic:iterate>

<tr>
                                                <td class="formbg" align="center">
                                                    &nbsp;
                                                </td>
                                                <td class="formbg">
                                                    Total
                                                </td>
                                                <td class="formbg1" align="center">
                                               ${reg}
                                                </td>
                                                <td class="formbg1" align="center">
                                                    ${ass}
                                                </td>
                                                <td class="formbg1" align="center">
                                                   ${eli}
                                                </td>
                                                <td class="formbg1" align="center">
                                                   ${ar}
                                                </td>
                                                <td class="formbg1" align="center">
                                                    ${dr}
                                                </td>


                                            </tr>
                                    </table>
                                </td></tr></table><br/>
                       </div>
                    </logic:notEmpty>









   </body>
</html>