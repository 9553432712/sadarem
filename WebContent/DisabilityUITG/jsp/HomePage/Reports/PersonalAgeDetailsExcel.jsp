<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");

            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


            String fromdate = (String) request.getParameter("fDate");


            String todate = (String) request.getParameter("toDate");


            String gender = (String) request.getParameter("gender");


            ArrayList getAddressList = new ArrayList();

            getAddressList = (ArrayList) request.getAttribute("areaDetails");
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
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="ageWiseDetails" scope="session">
            <table border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td align="center" valign="top">
                        <table  align="center" cellspacing="0" border="1" cellpadding="0">


                            <%if (gender.equals("1")) {%>
                            <tr>
                            <p align="center" colspan="16"><b>Age Wise Report For<font color="blue">Male's</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
                </tr>
                <%} else if (gender.equals("2")) {%>
                <tr>
                <p align="center" colspan="16"><b>Age Wise Report For<font color="blue">Female's</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
            </tr>
            <%} else {%>
            <tr>
            <p align="center" colspan="16"><b>Age Wise Report For <font color="blue">Male & Female</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
        </tr>
        <%}%>
        <tr>
            <td align="center" colspan="5">
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

                <%if (request.getParameter("habName") != null && !(request.getParameter("habName").equals("ALL"))) {%>
                , Habitation:
                <font color="blue"><%=request.getParameter("habName")%></font>
                <%}%>
                .
            </td>
        </tr>
        <tr>
            <th align="center" >S.No</th>
            <th align="center" >SADAREM ID</th>
            <th align="center" >Name</th>
            <th align="center" >Gender</th>
            <th align="center" >Age</th>
            <th align="center" >Relation Name</th>
            <th align="center" >Education</th>
            <th align="center" >Caste</th>
            <th align="center" >Address</th>
            <th align="center" >Phone No</th>

        </tr>


        <logic:iterate name="ageWiseDetails" id="row" scope="session">
            <tr>

                <td>
                    <%=i++%>.
                </td>
                <td>
                    SID-${row.PERSON_CODE}
                </td>
                <td>
                    ${row.PERSONNAME}
                </td>
                <td>
                    ${row.Gender}
                </td>
                <td>
                    ${row.age_years}
                </td>
                <td>
                    ${row.relation_name}
                </td>

                <td>
                    ${row.EDUCATION}
                </td>
                <td>
                    ${row.CASTE}
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
</td></tr></table><br/>

</logic:notEmpty>
</body>
</html>