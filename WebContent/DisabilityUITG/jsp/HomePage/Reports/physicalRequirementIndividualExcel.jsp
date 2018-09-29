<%--
    Document   : EducationWiseReportExcel
    Created on : Mar 20, 2011, 4:06:36 PM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PhysicalRequirementsDetailsDTO;" %>



<%
            //  String district_id = (String) request.getParameter("districtId");
            //  String mandal_id = (String) request.getParameter("mandalId");
            //  String village_id = (String) request.getParameter("villageId");
            String fromDate = (String) request.getParameter("fdate");
            String toDate = (String) request.getParameter("todate");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");


            ArrayList<PhysicalRequirementsDetailsDTO> getAddressList = new ArrayList<PhysicalRequirementsDetailsDTO>();
            getAddressList = (ArrayList) session.getAttribute("personalListData");
            // PhysicalRequirementsDetailsDTO addressDTO = getAddressList.get(0);
            //  String districtName = addressDTO.getPersonCode();
            //  String mandalName = addressDTO.getMandalName();
            //  String villageName = addressDTO.getVillageName();
            // String habitationName = addressDTO.getHabitationName();


            for (int i = 0; i < getAddressList.size(); i++) {
                PhysicalRequirementsDetailsDTO addressDTO = getAddressList.get(i);
                String personCode = addressDTO.getPersonCode();
                String personName = addressDTO.getPersonName();
                String personRelName = addressDTO.getPersonRelationName();
              
            }


            int i = 1;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>

    <body>

        <logic:notEmpty name="personalListData" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">

                <tr>
                    <td align="center">
                        SNo
                    </td>
                    <td align="center" class="formhdbg">
                        SADAREMCode
                    </td>
                    <td align="center" class="formhdbg">
                        Name
                    </td>
                    <td align="center" class="formhdbg">
                        RelationName
                    </td>
                    <td align="center" class="formhdbg" colspan="5">
                        Address
                    </td>
                </tr>
                <logic:iterate name="personalListData" id="details" scope="session">
                      <tr>
                    <td align="center">
                        <%=i++%>.
                    </td>
                    <td>
                        <bean:write name="details" property="personCode"/>
                    </td>
                    <td>
                        <bean:write name="details" property="personName"/>
                    </td>
                    <td>
                        <bean:write name="details" property="personRelationName"/>
                    </td>
                    <td>
                        <logic:notEmpty name="details" property="personalhouseNo">
                            <bean:write name="details" property="personalhouseNo"/>,
                        </logic:notEmpty>
                        <bean:write name="details" property="personalHabitationName"/>,
                        <bean:write name="details" property="personalVillageName"/>,
                        <bean:write name="details" property="personalMandalName"/>,
                        <bean:write name="details" property="personalDistrictName"/>
                    </td>
                  </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
        <%session.removeAttribute("personalListData");%>
    </body>

</html>


