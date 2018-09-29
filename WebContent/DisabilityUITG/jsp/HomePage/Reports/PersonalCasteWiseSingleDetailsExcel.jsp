<%-- 
    Document   : PersonalCasteWiseDetailsExcel
    Created on : Jun 24, 2011, 2:34:44 PM
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
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");
              String caste = (String) request.getParameter("caste");

             String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


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
        <logic:notEmpty name="casteWiseSingleDetails">
            <table width="99%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td align="center" valign="top">
                        <table  align="center" cellspacing="0" border="1" cellpadding="0" width="90%">
                            <tr><td align="center" colspan="3">Caste Wise Report</td></tr>
                            <tr>
                                <td align="center" colspan="3">
                                    <%
                                                if (villageName != null) {
                                                    if (!villageName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                                    Village: <font color="blue"><%=villageName%></font>,
                                    <% }
                                                }
                                                if (mandalName != null && villageName == null) {
                                                    if (!mandalName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                                    <% }
                                                }
                                                if (districtName != null && mandalName == null) {
                                                    if (!districtName.equals("null")) {%>
                                    District: <font color="blue"><%=districtName%></font>,
                                    <% }
                                                }%>
                                </td>
                            </tr>
                             <tr>
                            <td align="center" class="formhdbg">S.No</td>
                                <% if (district_id.equals("0")) {%>
                                <td class="formhdbg" align="center" >District</td>
                                <% } if(mandal_id!=null) {if (mandal_id.equals("0")) {%>
                                <td class="formhdbg" align="center" >Mandal</td>
                                <% }} if(!district_id.equals("0") && !mandal_id.equals("0")) {%>
                                <td class="formhdbg" align="center">Village</td>
                                <% } if(village_id!=null) {if(!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")){
                                    %>
                                <td class="formhdbg" align="center">Habitation</td>
                                <% }}%>

                                <%if(caste.equals("1")) {%>
                                   <td align="center" class="formhdbg">ST</td>
                                <%}else if(caste.equals("2")) {%>
                                 <td align="center" class="formhdbg">SC</td>
                                <%}else if(caste.equals("3")) {%>
                               <td align="center" class="formhdbg">BC</td>
                                <%}else if(caste.equals("4")) {%>
                               <td align="center" class="formhdbg">OC</td>
                                <%}else if(caste.equals("5")) {%>
                                <td align="center" class="formhdbg">Minarity</td>
                                <%}else if(caste.equals("6")) {%>
                                 <td align="center" class="formhdbg">NA</td>
                                <%}%>

                        </tr>


                            <logic:iterate name="casteWiseSingleDetails" id="row">
                                <tr>
                                    <td class="formaltbg" align="center">
                                        <%=i++%>
                                    </td>
                                    <td class="formaltbg">
                                        ${row.name}
                                    </td>
                                     <td class="formaltbg" align="center">
                                        ${row.caste}
                                     </td>


                                </tr>

                            </logic:iterate>

                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>
    </body>
</html>
