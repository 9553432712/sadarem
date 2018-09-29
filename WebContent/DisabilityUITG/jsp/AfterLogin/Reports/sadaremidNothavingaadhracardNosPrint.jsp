<%--
 Document   : sHGEligibleReport
    Created on : 5 Mar, 2014, 3:34:36 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO;" %>

<%
            int i = 1;
           




%>
<html:html>
   
    <body onload="window.print()">
        <html:form action="/sadaremidNothavingaadhracardNos.do">
            <html:hidden property="mode"/>
            <logic:notEmpty name="shgAbstract">



                <table border="0" cellspacing="1" cellpadding="0" style="width:500px" align="center" class="inputform">
                    <tr>
                        <th colspan="12">R1.3 : SADAREM ID Not Tagged To AADHARCARD No</th>
                    </tr>
                    <tr>
                        <th colspan="12">

                            <font size="2"><b>District:&nbsp;<%=request.getAttribute("districtName")%> ,&nbsp;Mandal&nbsp;:<%=request.getAttribute("mandalName")%>, &nbsp;Village: &nbsp;<%=request.getAttribute("villageName")%></b></font>
                        </th>
                    </tr>
                    <tr>
                        <th>S.No</th>
                        <th>SADAREM ID</th>
                        <th>Person Name</th>
                        <th>Relation Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Education</th>


                        <th>RationCard Number</th>
                        <th>Pension ID</th>
                        <th>Assessment Status</th>
                        <th>Address</th>
                        <th>Phone No</th>
                    </tr>


                    <logic:iterate name="shgAbstract" id="row" scope="request">

                        <tr>
                            <td>${row.sno}</td>
                            <td>

                                ${row.SADAREMCODE}
                            </td>
                            <td>${row.PERSONNAME}</td>
                            <td>

                                ${row.RELATIONNAME}

                            </td>

                            <td>

                                ${row.age}
                            </td>
                            <td>

                                ${row.Gender}
                            </td>

                            <td>
                                ${row.EDUCATION}
                            </td>

                            <td>

                                ${row.RATIONCARD_NUMBER}
                            </td>

                            <td>
                                ${row.PENSIONID}
                            </td>
                            <td>
                                ${row.ASSESSEMENTSTATUS}
                            </td>
                            <td>
                                ${row.Address}
                            </td>
                            <td>
                                ${row.phone_no}
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
                <%-- </div>--%>
            </logic:notEmpty>
        </html:form>
    </body>
</html:html>
