<%--
    Document   : NotComeToSadaremCampPersonalExcel
    Created on : Feb 1, 2011, 6:19:19 PM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            String districtName = (String)request.getParameter("dName");
            String phaseName = (String)request.getParameter("phase");


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Who are not Come to SADAREM Camp</title>
    </head>
    <body>
        <logic:present name="notComeToCampList" scope="request">
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="innerTable" width="90%">
                <tr><td class="formhdbg" align="center" colspan="15" bgcolor="blue"><b><font color="white" >District: <%=districtName%>, Phase: <%=phaseName%>, Who are Not Come to SADAREM Camp Report</font></b></td></tr>
                            <tr>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">S.No</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Pension ID</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">SADAREM ID</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Name</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Relation Name</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Gender</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Age</font></td>
                                <td class="formhdbg" align="center" bgcolor="gray"><font color="white">Ration Card Number</font></td>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Type of Disability</font></td>
                                 <td class="formhdbg" align="center" bgcolor="gray"><font color="white">House No</font></td>
                                 <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Mandal</font></td>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Village</font></td>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Habitation</font></td>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Pension Phase</font></td>
                                <td align="center" class="formhdbg" bgcolor="gray"><font color="white">Status</font></td>
                            </tr>
                            <% int i = 0;%>
                            <logic:iterate id="modify" name="notComeToCampList" scope="request">

                                <tr>
                                    <td  align="center"><%=++i%></td>
                                    <td  class="formbg" align="left"><bean:write name="modify" property="pensionid"/></td>
                                    <td class="formbg" align="left" >SID:<bean:write name="modify" property="personcode"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="name"/></td>
                                    <td  class="formbg" align="left"><bean:write name="modify" property="ralationName"/></td>
                                    <td  class="formbg" align="left"><bean:write name="modify" property="gender"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="age"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="rationcard"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="disabilityType"/></td>
                                    <td  class="formbg" align="center"><bean:write name="modify" property="houseno"/></td>
                                    <td  class="formbg" align="left"><bean:write name="modify" property="mandalName"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="villageName"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="habitationName"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="phase"/></td>
                                    <td class="formbg" align="left"><bean:write name="modify" property="categoryID"/></td>

                                </tr>


                            </logic:iterate>

                        </table><br>
        </logic:present>
    </body>
</html>



