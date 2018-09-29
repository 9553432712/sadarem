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
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String dI = (String) request.getParameter("dID");
            String mI = (String) request.getParameter("mID");
            String vI = (String) request.getParameter("vID");
            String fD = (String) request.getParameter("fDate");
            String tD = (String) request.getParameter("toDate");


            String fromdate = (String) request.getParameter("fDate");


            String todate = (String) request.getParameter("toDate");


            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");



            String caste = (String) request.getParameter("caste");

            String urbanId = (String) request.getParameter("urbanId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
            //  for(int j = 0;j<=getAddressList.size();j++) {
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





//}
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="casteWiseDetails" scope="session">
            <table border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td align="center" valign="top">
                        <table  align="center" cellspacing="0" border="1" cellpadding="0">
                            <%if (caste.equals("1")) {%>
                            <tr>
                            <p align="center" colspan="16"><b>Caste Wise Report for ( ST's ) As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
                </tr>

                <%} else if (caste.equals("2")) {%>
                <tr>
                <p align="center" colspan="16"><b>Caste Wise Report ( SC's ) As On  <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
            </tr>
            <%} else if (caste.equals("3")) {%>
            <tr>
            <p align="center" colspan="16"><b>Caste Wise Report ( BC's ) As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
        </tr>
        <%} else if (caste.equals("4")) {%>
        <tr>
        <p align="center" colspan="16"><b>Caste Wise Report ( OC's ) As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
    </tr>
    <%} else if (caste.equals("5")) {%>
    <tr>
    <p align="center" colspan="16"><b>Caste Wise Report ( Minority's ) As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
</tr>
<%} else if (caste.equals("6")) {%>
<tr>
<p align="center" colspan="16"><b>Caste Wise Report ( Not Applicable ) As On  <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></p>
</tr>
<%}%>
<tr>
    <td align="center" colspan="4">


        <p align="center" colspan="16">
            <%




                        if (habName != null) {
                            if (!habName.equals("null")) {%>
            District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
            Village: <font color="blue"><%=villageName%></font>
            ,Habitation <font color="blue"><%=habName%></font>
            <% }
                        }

                        if (villageName != null && habName == null) {
                            if (!villageName.equals("null")) {%>
            District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
            Village: <font color="blue"><%=villageName%></font>

            <% }
                        }


                        if (mandalName != null && villageName == null) {
                            if (!mandalName.equals("null")) {%>
            District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,

            <% }
                        }

                        if (districtName != null && mandalName == null) {
                            if (!districtName.equals("null")) {%>
            District: <font color="blue"><%=districtName%></font>

            <% }
                        }%>
        </p>
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

<logic:iterate name="casteWiseDetails" id="row" scope="session">
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
