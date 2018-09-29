<%--
    Document   : MaritalDetailsIndivdualExcel
    Created on : Jul 11, 2011, 3:27:55 PM
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
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");

            String fromdate = (String) request.getParameter("fromdate");

         
            String todate = (String) request.getParameter("todate");
           
            String mstatus = (String) request.getParameter("statusMarital");
            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areaDetails");
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

        <table border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="center" valign="top">
                    <table  align="center" cellspacing="0" border="1" cellpadding="0" >

                        <tr>
                            <%if (mstatus.equals("1")) {%>
                        <p align="center" colspan="16"><b>Marital Status Report (Married) As On  <font ><%=fromdate%></font> To <font ><%=todate%></font></b></p>
                        <%} else if (mstatus.equals("2")) {%>
                        <p align="center" colspan="16"><b>Marital Status Report (UnMarried) As On <font ><%=fromdate%></font> To <font ><%=todate%></font></b></p>
                        <%} else if (mstatus.equals("3")) {%>
                        <p align="center" colspan="16"><b>Marital Status Report (Divorcee) As On <font ><%=fromdate%></font> To <font ><%=todate%></font></b></p>
                        <%} else if (mstatus.equals("4")) {%>
                        <p align="center" colspan="16"><b>Marital Status Report (Widow) As On <font ><%=fromdate%></font> To <font ><%=todate%></font></b></p>
                        <%} else if (mstatus.equals("5")) {%>
                        <p align="center" colspan="16"><b>Marital Status Report (Widower) As On <font ><%=fromdate%></font> To <font ><%=todate%></font></b></p>
                        <%}%>
            </tr>
            <tr>
                <td align="center" colspan="5">

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
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>
                    <% }
                                }
                                if (districtName != null && mandalName == null) {
                                    if (!districtName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>
                    <% }
                            }%>
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


            <logic:iterate name="indivdualDetails" id="row" scope="session">
                <tr>
                    <td class="formbg" align="center" style="width: 5%;">
                        <%=i++%>.
                    </td>
                    <td class="formbg" style="width: 10%;">
                       SID- ${row.PERSON_CODE}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.PERSONNAME}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.Gender}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.age_years}
                    </td>
                    <td class="formbg" align="center" style="width: 8%;">
                        ${row.relation_name}
                    </td>

                    <td class="formbg" style="width: 10%;">
                        ${row.EDUCATION}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.CASTE}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.Address}
                    </td>
                    <td class="formbg" style="width: 15%;">
                        ${row.phone_no}
                    </td>

                </tr>

            </logic:iterate>

        </table>
    </td></tr></table><br/>
</body>
</html>
