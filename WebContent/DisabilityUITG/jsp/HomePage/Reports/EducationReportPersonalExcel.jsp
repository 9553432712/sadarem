<%--
    Document   : CasteWisePersonalDetailsExcel
    Created on : Jun 24, 2011, 3:27:19 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.*" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");
            String fromDate = (String) request.getParameter("fdate");
            String toDate = (String) request.getParameter("todate");
            String habitation_id = (String) request.getParameter("hID");
            String date = (String) request.getAttribute("date");
            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


            ArrayList getAddressList = new ArrayList();
            if (request.getAttribute("areaDetails") != null) {
                getAddressList = (ArrayList) request.getAttribute("areaDetails");
            }
            //  for(int j = 0;j<=getAddressList.size();j++) {
            if (getAddressList.size() > 0) {
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
                    } else if (getAddressList.size() == 4) {
                        districtName = (String) getAddressList.get(0);
                        mandalName = (String) getAddressList.get(1);
                        villageName = (String) getAddressList.get(2);
                        habName = (String) getAddressList.get(3);
                    }
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="educationwiseList">
            <table border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td align="center" valign="top">
                        <table  align="center" cellspacing="0" border="1" cellpadding="0">
                            <tr><td align="center" colspan="5"><b>Generated Date:<%=date+"."%>   Education Wise Report from <%=fromDate%> to <%=toDate%></b></td></tr>
                            <tr>
                                <td align="center" colspan="8">
                                    District :
                                    <% if (district_id.equals("")) {%>
                                    <font color="blue">ALL</font>
                                    <% } else {%>
                                    <font color="blue"><%=districtName%></font>
                                    <%}%>
                                    ,
                                    Mandal :
                                    <% if (mandal_id.equals("")) {%>
                                    <font color="blue">ALL</font>
                                    <% } else {%>
                                    <font color="blue"><%=mandalName%></font>
                                    <%}%>
                                    ,
                                    Village :
                                    <% if (village_id.equals("")) {%>
                                    <font color="blue">ALL</font>
                                    <% } else {%>
                                    <font color="blue"><%=villageName%></font>
                                    <%}%>
                                    ,
                                    Habitation :
                                    <% if (habitation_id.equals("")) {%>
                                    <font color="blue">ALL</font>
                                    <% } else {%>
                                    <font color="blue"><%=habName%></font>
                                    <%}%>
                                    .
                                </td>
                            </tr>
                            <tr>
                                <td align="center" ><b>S.No</b></td>
                                <td align="center" ><b>SADAREM Id</b></td>
                                <td align="center" ><b>Name</b></td>

                                <td align="center"><b>Age</b></td>
                                <td align="center"><b>Gender</b></td>
                                <td align="center"><b>cast</b>
                                <td align="center"><b>Education</b></td>
                                <td align="center"><b>Marriage Status</b></td>
                                <td align="center"><b>Contact No.</b></td>
                                <td align="center"><b>Relation Name</b></td>
                                <td align="center"><b>Address</b></td>
                            </tr>


                            <logic:iterate name="educationwiseList" id="row">
                                <tr>
                                    <td align="center">
                                        <%=i++%>.
                                    </td>
                                    <td >
                                        SID - ${row.person_code}
                                    </td>
                                    <td >
                                        ${row.surname}
                                    </td>

                                    <td  align="center">
                                        ${row.age_years}
                                    </td>
                                    <td  align="center">
                                        ${row.gender}
                                    </td>
                                    <td  align="center">
                                        ${row.cast}
                                    </td>
                                    <td  align="center">
                                        ${row.education}
                                    </td>
                                    <td  align="center">
                                        ${row.marital_status}
                                    </td>
                                     <td  align="center">
                                        ${row.contact}
                                    </td>
                                    <td  align="center">
                                        ${row.relation}
                                    </td>
                                    <td  align="center">
                                        ${row.address}
                                    </td>
                                </tr>

                            </logic:iterate>



                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>
    </body>
</html>
