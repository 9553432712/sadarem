<%--
    Document   : SHGPersonalDetails
    Created on : 5 Mar, 2014, 7:11:01 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <title>JSP Page</title>
    </head>
    <body>
        <html:form action="/shgPersonalDetails">

            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td  align="center"  valign="top">
                  
                        <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
                                <th colspan="26">SHG's Individual Details Report</th>
                            <tr>
                                    <td colspan="26">
                                    District Name : <b><%=request.getAttribute("districtName") %></b>
                                    &nbsp;&nbsp;&nbsp;
                                    Mandal Name : <b><%=request.getAttribute("mandalName") %></b>
                                </td>
                            </tr>
                                <logic:present name="shgflag">
                            <tr>
                                <th>
                                    S No
                                </th>
                                <th>
                                    Name
                                </th>
                                <th>
                                    Relation Name
                                </th>
                                    <th>
                                        SADAREM ID
                                    </th>
                                        <th>
                                            Education
                                        </th>
                                        <th>
                                            Employment
                                        </th>
                                        <th>
                                            Martial Status
                                        </th>

                                        <th>
                                            Disable percentage
                                        </th>
                                        <th>
                                            Caste
                                        </th>
                                        <th>
                                            Habitation Name
                                        </th>
                                        <th>
                                            Village Name
                                        </th>
                                        <th>
                                            Mandal Name
                                        </th>
                                        <th>
                                            District Name
                                        </th>

                                    </tr>
                                </logic:present>
                                <logic:present name="flag">
                                    <tr>
                                        <th>
                                            S No
                                        </th>
                                        <th>
                                            Name
                                        </th>
                                        <th>
                                            Relation Name
                                        </th>
                                        <th>
                                            SADAREM ID
                                        </th>
                                        <th>
                                            Pension ID
                                        </th>
                                        <th>
                                            Age
                                        </th>
                                        <th>
                                            Gender
                                        </th>

                                        <th>
                                        Education
                                    </th>
                                    <th>
                                        Caste
                                    </th>
                                    <th>
                                        Type Of Disability
                                    </th>
                                    <%--<th>
                                       Percentage Of Disability
                                    </th>--%>
                                    <th>
                                       House No
                                    </th>

                                    <th>
                                        Habitation Name
                                    </th>
                                    <th>
                                        Village Name
                                    </th>
                                    <th>
                                        Mandal Name
                                    </th>
                                    <th>
                                         District Name
                                    </th>
                                    <th>
                                        Phone No
                                        </th>
                                        <th>
                                            Assessment Status
                                        </th>
                                    </tr>
                                </logic:present>

                                  <logic:present name="unmappedFlag">
                                    <tr>
                                        <th>
                                            S No
                                        </th>
                                        <th>
                                            Name
                                        </th>
                                        <th>
                                            Relation Name
                                        </th>
                                        <th>
                                            SADAREM ID
                                        </th>
                                        <th>
                                            Pension ID
                                        </th>
                                        <th>
                                            Age
                                        </th>
                                        <th>
                                            Gender
                                        </th>

                                        <th>
                                            Education
                                        </th>
                                        <th>
                                            Caste
                                        </th>
                                        <th>
                                            Type Of Disability
                                        </th>

                                        <th>
                                            House No
                                        </th>

                                        <th>
                                            Habitation Name
                                        </th>
                                        <th>
                                            Village Name
                                        </th>
                                        <th>
                                            Mandal Name
                                        </th>
                                        <th>
                                            District Name
                                        </th>
                                        <th>
                                            Phone No
                                        </th>
                                        <th>
                                            Assessment Status
                                        </th>
                                        <th>
                                           SHG Name
                                        </th>
                                        <th>
                                          SHG Bank Account
                                        </th>
                                        <th>
                                            Mamber Bank Account
                                        </th>
                                        <th>
                                           Member Bank Name
                                        </th>
                                        <th>
                                           Bank Branch Name
                                        </th>

                                    </tr>
                                </logic:present>

                                <logic:present name="msg">
                                    <tr>
                                        <td align="left" colspan="26" style="text-align: center">
                                            ${msg}
                                        </td>
                                    </tr>
                                </logic:present>

                                <logic:present name="flag">
                                <logic:notEmpty name="personalData">
                                    <logic:iterate id="row" name="personalData">
                                        <tr>
                                            <td align="center">
                                                <%=i++%>
                                            </td>

                                            <td align="left">
                                                ${row.personName}
                                            </td>
                                            <td align="left">
                                                ${row.relationName}
                                            </td>
                                            <td align="left">
                                                ${row.SADAREMCODE}
                                            </td>
                                            <td align="left">
                                                ${row.pensionId}
                                            </td>

                                            <td align="left">
                                                ${row.age}
                                            </td>
                                            <td align="center">
                                                ${row.gender}
                                            </td>
                                            <td align="center">
                                                ${row.education}
                                            </td>
                                            <td align="center">
                                                ${row.caste}
                                            </td>
                                            <td align="center">
                                                ${row.typeOfDisability}
                                            </td>
                                            <%--<td align="center">
                                                ${row.perOfDisability}
                                            </td>--%>
                                            <td align="center">
                                                ${row.houseNo}
                                            </td>
                                            <td align="center">
                                                ${row.habitationName}
                                            </td>
                                            <td align="center">
                                                ${row.villageName}
                                            </td>
                                            <td align="center">
                                                ${row.mandalName}
                                            </td>
                                             <td align="center">
                                                ${row.districtName}
                                            </td>
                                             <td align="center">
                                                ${row.phoneNo}
                                            </td>
                                            <td align="center">
                                                ${row.assesmentStatus}
                                                </td>

                                            </tr>
                                        </logic:iterate>

                                        <tr>
                                            <td style="text-align: center" colspan="26">
                                                <a href="report.xls?mode=getShgPersonalDetailsExcel&heading=${heading}&district_name=${districtName}&mandal_name=${mandalName}&districtId=${districtId}&mandalId=${mandalId}&flag=${flag}"  target="_blank">
                                                    Export To Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="DisabilityUITG/images/excel.jpg"
                                                                                                              height="25" width="25"/></a>
                                            </td>
                                        </tr>
                                    </logic:notEmpty>
                                        </logic:present>
                                    <logic:present name="shgflag">
                                        <logic:notEmpty name="personalData1">
                                            <logic:iterate id="row" name="personalData1">
                                                <tr>
                                                    <td align="center">
                                                        <%=i++%>
                                                    </td>

                                                    <td align="left">
                                                        ${row.personName}
                                                    </td>
                                                    <td align="left">
                                                        ${row.relationName}
                                                    </td>
                                                    <td align="left">
                                                        ${row.SADAREMCODE}
                                                    </td>
                                                    <td align="left">
                                                        ${row.education}
                                                    </td>

                                                    <td align="left">
                                                        ${row.emp}
                                                    </td>
                                                    <td align="center">
                                                        ${row.martitalStatus}
                                                    </td>
                                                    <td align="center">
                                                        ${row.disabledperscentage}
                                                    </td>
                                                    <td align="center">
                                                        ${row.caste}
                                                    </td>

                                                    <td align="center">
                                                        ${row.habitationName}
                                                    </td>

                                                    <td align="center">
                                                        ${row.villageName}
                                                    </td>


                                                    <td align="center">
                                                        ${row.mandalName}
                                                    </td>

                                                    <td align="center">
                                                        ${row.districtName}
                                                    </td>


                                                </tr>
                                            </logic:iterate>

                                            <tr>
                                                <td style="text-align: center" colspan="26">
                                                    <a href="report.xls?mode=getShgPersonalDetailsExcel&heading=${heading}&district_name=${districtName}&mandal_name=${mandalName}&districtId=${districtId}&mandalId=${mandalId}&flag=${shgflagData}"  target="_blank">
                                                        Export To Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="DisabilityUITG/images/excel.jpg"
                                                                                                                  height="25" width="25"/></a>
                                                </td>
                                            </tr>
                                        </logic:notEmpty>
                                    </logic:present>


                                    <logic:present name="unmappedFlag">
                                    <logic:notEmpty name="personalData">
                                        <logic:iterate id="row" name="personalData">
                                            <tr>
                                                <td align="center">
                                                    <%=i++%>
                                                </td>

                                                <td align="left">
                                                    ${row.personName}
                                                </td>
                                                <td align="left">
                                                    ${row.relationName}
                                                </td>
                                                <td align="left">
                                                    ${row.SADAREMCODE}
                                                </td>
                                                <td align="left">
                                                    ${row.pensionId}
                                                </td>

                                                <td align="left">
                                                    ${row.age}
                                                </td>
                                                <td align="center">
                                                    ${row.gender}
                                                </td>
                                                <td align="center">
                                                    ${row.education}
                                                </td>
                                                <td align="center">
                                                    ${row.caste}
                                                </td>
                                                <td align="center">
                                                    ${row.typeOfDisability}
                                                </td>
                                                <%--<td align="center">
                                                    ${row.perOfDisability}
                                                </td>--%>
                                                <td align="center">
                                                    ${row.houseNo}
                                                </td>
                                                <td align="center">
                                                    ${row.habitationName}
                                                </td>
                                                <td align="center">
                                                    ${row.villageName}
                                                </td>
                                                <td align="center">
                                                    ${row.mandalName}
                                                </td>
                                                <td align="center">
                                                    ${row.districtName}
                                                </td>
                                                <td align="center">
                                                    ${row.phoneNo}
                                                </td>
                                                <td align="center">
                                                    ${row.assesmentStatus}
                                                </td>
                                                <td align="center">

                                                </td>
                                                <td align="center">

                                                </td>
                                                <td align="center">

                                                </td>
                                                <td align="center">

                                                </td>
                                                <td align="center">

                                                </td>

                                            </tr>
                                        </logic:iterate>

                                        <tr>
                                            <td style="text-align: center" colspan="26">
                                                <a href="report.xls?mode=getShgPersonalDetailsExcel&heading=${heading}&district_name=${districtName}&mandal_name=${mandalName}&districtId=${districtId}&mandalId=${mandalId}&flag=${excelFlag}"  target="_blank">
                                                   Export To Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="DisabilityUITG/images/excel.jpg"
                                                                                                              height="25" width="25"/></a>
                                            </td>
                                        </tr>
                                    </logic:notEmpty>
                                   </logic:present>
                            </table>
                    
                    </td>
                </tr>
            </table>
            <br/>
        </html:form>
    </body>
</html>
