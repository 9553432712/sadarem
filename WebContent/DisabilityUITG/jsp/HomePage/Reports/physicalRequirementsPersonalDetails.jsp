<%-- 
    Document   : physicalRequirementsPersonalDetails
    Created on : Sep 8, 2012, 12:28:04 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="org.bf.disability.dto.PhysicalRequirementsDetailsDTO;"%>



<%
            String districtName = (String) request.getAttribute("districtName");
            String mandalName = (String) request.getAttribute("mandalName");
            String villageName = (String) request.getAttribute("villageName");
            int j = 1;
            String individualIdValue = (String) session.getAttribute("individualIdValue");

            String district_id = (String) session.getAttribute("district_id");
            String mandal_id = (String) session.getAttribute("mandal_id");
            String village_id = (String) session.getAttribute("village_id");

%>


<script language="javascript" >
    function changecolor(colorvar)
    {
        var colorvar1=colorvar;
        document.getElementById(colorvar1).style.color="red";
    }
</script>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>

        <logic:present name="personalList" scope="request">
            <p>Physical Requirement Wise Details</p>
            <table border="0" align="center" cellspacing="1" cellpadding="4" class="inputform" width="90%">
                <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="11">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>
                </tr>
                <th align="center" >
                    SNo
                </th>
                <th align="center" >
                    SADAREMCode
                </th>
                <th align="center" >
                    Name
                </th>
                <th align="center" >
                    RelationName
                </th>
                <th align="center"  colspan="5">
                    Address
                </th>

                <logic:iterate id="details" name="personalList"  scope="request">

                    <tr>
                        <td  align="center">
                            <%=j++%>.
                        </td>
                        <td >
                            <bean:write name="details" property="personCode"/>
                        </td>
                        <td >
                            <bean:write name="details" property="personName"/>
                        </td>
                        <td >
                            <bean:write name="details" property="personRelationName"/>
                        </td>
                        <td >

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
            <br>
            <table width="100%" border="0">
                <tr>
                    <td align="center" >
                        <%session.setAttribute("personalListData", request.getAttribute("personalList"));%>
                        <a href="getphysicalRequirementIndividualExcel.xls?mode=getrep" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" border="0"
                                                 height="25" width="25"/></a>

                    </td>
                </tr>
            </table>
        </logic:present>

    </body>
</html>
