<%--
    Document   : AppealAuthorityReportPersonalDetails
    Created on : Nov 23, 2011, 10:20:09 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<%int j = 1;%>
<html>
    <head>
        <title>SADAREM</title>
        <script>
            function getDetails() {
                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
        </script>

        <%
                    String districtName = null;
                    String mandalName = null;
                    String villageName = null;

                    ArrayList getAddressList = null;
                    getAddressList = (ArrayList) session.getAttribute("areadetailsExcel");
                    //  for(int j = 0;j<=getAddressList.size();j++) {
                    if (getAddressList != null) {
                        getAddressList = new ArrayList();
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


    </head>
    <body>
        <html:form action="/appealReportPersonal.do" >
            <html:hidden property="mode"/>



            <p>RACHABANDA ASSESSMENT PERSONAL DETAILS</p>
            <logic:present name="msg">
                <table align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                        </td>
                    </tr>
                </table>
            </logic:present>

            <logic:notEmpty name="appealPersonlReportDetails" >
                <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                    <tr>
                        <logic:present name="names">
                            <th style="text-align: center" colspan="20">
                                <bean:write name="names"/>
                            </th>
                        </logic:present>
                    </tr>

                    <tr>
                        <th align="center" >S.No</th>
                        <th align="center" >SADAREM ID</th>
                        <th align="center" >Name</th>
                        <th align="center" >RelationName</th>
                        <th align="center" >Age</th>
                        <th align="center" >Gender</th>
                        <th align="center" >Disability</th>
                        <th align="center" >District</th>
                        <th align="center" >Mandal</th>
                        <th align="center" >Village</th>
                        <th align="center" >VenueName</th>
                        <th align="center" >VenueAddress</th>
                    </tr>
                    <logic:iterate id="rows" name="appealPersonlReportDetails">
                        <tr>
                            <td  align="center">
                                <%=j++%> .
                            </td>
                            <td >
                                ${rows.personCode}
                            </td>
                            <td >
                                ${rows.name}
                            </td>
                            <td >
                                ${rows.relationName}
                            </td>
                            <td >
                                ${rows.age}
                            </td>
                            <td >
                                ${rows.gender}
                            </td>
                            <td >
                                ${rows.disability}
                            </td>
                            <td >
                                ${rows.district}
                            </td>
                            <td >
                                ${rows.mandal}
                            </td>
                            <td >
                                ${rows.village}
                            </td>
                            <td >
                                ${rows.venuename}
                            </td>
                            <td >
                                ${rows.venueadd}
                            </td>
                        </tr>

                    </logic:iterate>

                </table>

                <table align="center" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td valign="middle">
                            <a href="./appealReportPersonal.xls?status=getExcel&distCodes=<%=request.getParameter("districtCode")%>&mandalCodes=<%=request.getParameter("mandalCode")%>&villageCodes=<%=request.getParameter("villageCode")%>&basehab=<%=request.getParameter("basehab")%>&fromDate=<%=request.getParameter("fromDate")%>&toDate=<%=request.getParameter("toDate")%>"><img src="./DisabilityUITG/images/excel.jpg" width="20%" height="20%" border="0" >Excel</a>
                        </td>

                    </tr>

                </table>

            </logic:notEmpty>
        </html:form>
    </body>
</html>
