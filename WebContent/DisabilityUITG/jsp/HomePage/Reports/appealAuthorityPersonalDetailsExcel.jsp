<%--
    Document   : appellateAuthorityPersonalDetails
    Created on : Nov 28, 2011, 1:09:41 PM
    Author     : 484898
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%int i = 1;%>

<%
            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areadetailsExcel");
            //  for(int j = 0;j<=getAddressList.size();j++) {
            if (getAddressList != null) {
                if (getAddressList.size() > 0) {
                    if (getAddressList.size() == 1) {
                        districtName = (String) getAddressList.get(0);
                    } else if (getAddressList.size() == 2) {
                        districtName = (String) getAddressList.get(0);
                        mandalName = (String) getAddressList.get(1);
                    } else if (getAddressList.size() == 3) {
                        districtName = (String) getAddressList.get(0);
                        mandalName = (String) getAddressList.get(1);
                        villageName = (String) getAddressList.get(2);
                    }
                }
            }

%>

<html:html>
    <head>
        <title>RACHABANDA ASSESSMENT PERSONAL DETAILS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <logic:notEmpty name="appealPersonlReportDetailsExcel" scope="session">
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">

                <tr>
                    <td height="445" align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                            <tr><td class="formhdbg" align="center" colspan="10">RACHABANDA ASSESSMENT PERSONAL DETAILS</td></tr>
                            <tr>
                                <td colspan="10" align="center">
                                    <%
                                                if (villageName != null) {
                                                    if (!villageName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                                    Village: <font color="blue"><%=villageName%></font>, Habitations: <font color="blue">ALL</font>.
                                    <% }
                                                }
                                                if (mandalName != null && villageName == null) {
                                                    if (!mandalName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>, Village: <font color="blue">ALL</font>.
                                    <% }
                                                }
                                                if (districtName != null && mandalName == null) {
                                                    if (!districtName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue">ALL</font>.
                                    <% }
                            }%><br/><br/>
                             From Date : <%=request.getParameter("fromDate") %> ,
                             To Date : <%=request.getParameter("toDate") %>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" bgcolor="blue"><font color="white"><b>S.No</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>SADAREM ID</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Name</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>RelationName</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Age</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Gender</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Disability</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>District</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Mandal</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>Village</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>VenueName</b></font></td>
                                <td align="center" bgcolor="blue"><font color="white"><b>VenueAddress</b></font></td>
                            </tr>



                            <% int j = 1;%>


                            <logic:iterate name="appealPersonlReportDetailsExcel" id="rows" scope="session">

                                <tr>
                                    <td class="formbg" align="center">
                                        <%=j++%> .
                                    </td>
                                    <td align="left">
                                        SID - ${rows.personCode}
                                    </td>
                                    <td align="left">
                                        ${rows.name}
                                    </td>
                                    <td align="left">
                                        ${rows.relationName}
                                    </td>
                                    <td align="left">
                                        ${rows.age}
                                    </td>
                                    <td align="left">
                                        ${rows.gender}
                                    </td>
                                    <td align="left">
                                        ${rows.disability}
                                    </td>
                                    <td align="left">
                                        ${rows.district}
                                    </td>
                                    <td align="left">
                                        ${rows.mandal}
                                    </td>
                                    <td align="left">
                                        ${rows.village}
                                    </td>
                                    <td align="left">
                                        ${rows.venuename}
                                    </td>
                                    <td align="left">
                                        ${rows.venueadd}
                                    </td>
                                </tr>


                            </logic:iterate>


                        </table>
                    </td></tr></table>
                </logic:notEmpty>


    </body>

    <%session.removeAttribute("areadetailsExcel"); %>
    <%session.removeAttribute("appealPersonlReportDetailsExcel"); %>
</html:html>