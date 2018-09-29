<%-- 
    Document   : CasteWiseDetailsSinglePrint
    Created on : Jun 24, 2011, 2:58:04 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");
            String caste = (String) request.getParameter("caste");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;


            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
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
%>

<html>
    <head>
        
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="casteWiseSingleDetails" scope="session">
            <table  align="center" cellspacing="0" border="1" cellpadding="4" class="table" width="100%">
                
                <tr>
                    <td align="center" colspan="11"><b><p>Caste Wise Report</p></b></td>
                </tr>
                <tr>
                    <p align="center" colspan="11">
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
                    </p>
                </tr>

                <tr>

                    <th align="center"   bgcolor="blue"><font color="white">S.No</font></th>
                    <% if (district_id.equals("0")) {%>
                                <th  align="center" bgcolor="blue"><font color="white">District</font></th>
                                <% } if(mandal_id!=null) {if (mandal_id.equals("0")) {%>
                                <th  align="center" bgcolor="blue"><font color="white">Mandal</font></th>
                                <% }} if(!district_id.equals("0") && !mandal_id.equals("0")) {%>
                                <th  align="center" bgcolor="blue"><font color="white">Village</font></th>
                                <% } if(village_id!=null) {if(!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")){
                                    %>
                                <th  align="center" bgcolor="blue"><font color="white">Habitation</font></th>
                                <% }}%>

                    <%if (caste.equals("1")) {%>
                    <th align="center"  bgcolor="blue">ST</th>
                    <%} else if (caste.equals("2")) {%>
                    <th align="center"  bgcolor="blue">SC</th>
                    <%} else if (caste.equals("3")) {%>
                    <th align="center"  bgcolor="blue">BC</th>
                    <%} else if (caste.equals("4")) {%>
                    <th align="center"  bgcolor="blue">OC</th>
                    <%} else if (caste.equals("5")) {%>
                    <th align="center"  bgcolor="blue">Minarity</th>
                    <%} else if (caste.equals("6")) {%>
                    <th align="center"  bgcolor="blue">NA</th>
                    <%}%>

                </tr>


                <logic:iterate name="casteWiseSingleDetails" id="row" scope="session">
                    <tr>
                        <td  align="center">
                            <%=i++%>
                        </td>
                        <td >
                            ${row.name}
                        </td>
                        <td  align="center">
                            ${row.caste}
                        </td>


                    </tr>

                </logic:iterate>

            </table>


        </logic:notEmpty>
    </body>
</html>
