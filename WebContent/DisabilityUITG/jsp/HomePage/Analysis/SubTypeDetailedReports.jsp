<%-- 
    Document   : SubTypesReports
    Created on : Feb 15, 2013, 2:04:23 PM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <%
                int i = 1;
                String did = null, mid = null, vid = null, hid = null, ddid = null, sdid = null, dname = null, mname = null, vname = null, hname = null, disname = null, sname = null, disabulityid = null, ssid = null;
                did = request.getAttribute("did").toString();
                mid = request.getAttribute("mid").toString();
                vid = request.getAttribute("vid").toString();
                hid = request.getAttribute("hid").toString();
                ddid = request.getAttribute("ddid").toString();
                sdid = request.getAttribute("sdid").toString();
                if (request.getAttribute("id1") != null) {
                    disabulityid = request.getAttribute("id1").toString();
                }
                if (request.getAttribute("ddid") != null) {
                    disabulityid = request.getAttribute("ddid").toString();
                }
                if (request.getAttribute("sdid") != null) {
                    ssid = request.getAttribute("sdid").toString();
                }

                if (request.getAttribute("dn") != null) {
                    dname = request.getAttribute("dn").toString();
                }
                if (request.getAttribute("mn") != null) {
                    mname = request.getAttribute("mn").toString();
                }
                if (request.getAttribute("vn") != null) {
                    vname = request.getAttribute("vn").toString();
                }
                if (request.getAttribute("hn") != null) {
                    hname = request.getAttribute("hn").toString();
                }
                if (request.getAttribute("ddn") != null) {
                    disname = request.getAttribute("ddn").toString();
                }
                if (request.getAttribute("sdn") != null) {
                    sname = request.getAttribute("sdn").toString();
                }
    %>
    <head>
        <title>SADAREM</title>

    </head>

    <center>
        <body>
            <html:form action="/subTypeReport" >

                <div style="width:900px; height:100%; overflow-x:scroll;
                     ">
                    <p>Details of Persons with SubType Reports Personal Details
                        </p>
                    <table  border="0" align="center" cellspacing="1" cellpadding="4" class="inputform" width="90%">
                        
                        <tr>
                            <logic:present name="names">
                                <th style="text-align: center" colspan="11">
                                    <bean:write name="names"/>
                                </th>
                            </logic:present>
                        </tr>
                        <tr>
                             <logic:present name="nodata">
                                <td style="text-align: center" >
                                    <font color="red" size="2">  <bean:write name="nodata"/></font>
                                </td>
                            </logic:present>

                        </tr>
                        <logic:present name="addressDetailsList">
                            <tr>

                                <th class="formhdbg" align="center">
                                    S No
                                </th>
                                <th class="formhdbg" align="center">
                                    SADAREM ID
                                </th>
                                <th class="formhdbg" align="center">
                                    Name
                                </th>
                                <th class="formhdbg" align="center">
                                    Relation Name
                                </th>
                                <th class="formhdbg" align="center">
                                    Age
                                </th>
                                <th class="formhdbg" align="center">
                                    Caste
                                </th>
                                <th class="formhdbg" align="center">
                                    Address
                                </th>

                            </tr>
                            <logic:iterate name="addressDetailsList" id="rows">
                                <tr>
                                    <td class="formaltbg" align="center">
                                        <%=i++%>
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.sadaremcode}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.name}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.relationname}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.age}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.caste}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${rows.address}
                                    </td>
                                </tr>
                            </logic:iterate>
                        </logic:present>
                    </table>

                    <logic:present name="addressDetailsList" >
                        <table align="center">
                            <tr>
                                <td>
                                    <a href="subTypeReport.xls?mode=getSubTypeReportExcelData" target="_blank">
                                        Export To Excel
                                        <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                </td>
                            </tr>
                            <br>
                        </table>

                    </logic:present>
                </div>
            </html:form>
        </body>
    </center>
</html:html>
