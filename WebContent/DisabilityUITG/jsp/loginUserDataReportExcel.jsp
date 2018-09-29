<%-- 
    Document   : loginUserDataReportExcel
    Created on : Jan 18, 2013, 11:34:14 AM
    Author     : 728056
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page contentType="application/MyExcel.ms-excel" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>kavya</title>
    </head>
    <body>  

        <table  align="center" style="font-weight:bold;"  width="100%">

            <tr class="darkgrey">
                <th colspan="24" align="center" >
                    Report
                </th>
            </tr>
            <tr class="darkgrey">
                <th class="th" align="center">Sl No</th>
                <th class="th" align="center">SADAREM ID</th>
                <th class="th" align="center">Pension ID</th>
                <th class="th" align="center">Name</th>
                <th class="th" align="center">Gender</th>
                <th class="th" align="center">Caste</th>
                <th class="th" align="center">Age</th>
                <th class="th" align="center">Marital Status</th>
                <th class="th" align="center">Relation Name</th>
                <th class="th" align="center">Education</th>
                <th class="th" align="center">Rationcard</th>
                <th class="th" align="center">Disability</th>
                <th class="th" align="center">Disability %</th>
                <th class="th" align="center">Phase</th>
                <th class="th" align="center">Mandal</th>
                <th class="th" align="center">Village</th>
                <th class="th" align="center">Medical Board</th>
                <th class="th" align="center">Medical Board Address</th>
                <th class="th" align="center">Assessed Doctor</th>
                <th class="th" align="center">Doctor RegNo</th>
                <th class="th" align="center">Doctor Designation</th>
                <th class="th" align="center">Assessed Date</th>
                <th class="th" align="center">PWD Status</th>
                <th class="th" align="center">Assessement Status</th>

            </tr>
            <%int i = 0;%>
            <logic:iterate id="modify" name="alldataList" scope="request">
                <tr>
                    <td  align="center"  class="label"><%=++i%></td>
                    <td  class="label"> <bean:write name="modify" property="SADAREM ID"/></td>
                    <td  class="label"> <bean:write name="modify" property="Pension ID"/></td>
                    <td  class="label"> <bean:write name="modify" property="Name"/></td>
                    <td  class="label"> <bean:write name="modify" property="Gender"/></td>
                    <td  class="label"> <bean:write name="modify" property="Caste"/></td>
                    <td  class="label"> <bean:write name="modify" property="Age"/></td>
                    <td  class="label"> <bean:write name="modify" property="Marital Status"/></td>
                    <td  class="label"> <bean:write name="modify" property="Relation Name"/></td>
                    <td  class="label"> <bean:write name="modify" property="Education"/></td>
                    <td  class="label"> <bean:write name="modify" property="Rationcard"/></td>
                    <td  class="label"> <bean:write name="modify" property="Disability"/></td>
                    <td  class="label"> <bean:write name="modify" property="Disability %"/></td>
                    <td  class="label"> <bean:write name="modify" property="Phase"/></td>
                    <td  class="label"> <bean:write name="modify" property="Mandal"/></td>
                    <td  class="label"> <bean:write name="modify" property="Village"/></td>
                    <td  class="label"> <bean:write name="modify" property="Medical Board"/></td>
                    <td  class="label"> <bean:write name="modify" property="Medical Board Address"/></td>
                    <td  class="label"> <bean:write name="modify" property="Assessed Doctor"/></td>
                    <td  class="label"> <bean:write name="modify" property="Doctor RegNo"/></td>
                    <td  class="label"> <bean:write name="modify" property="Doctor Designation"/></td>
                    <td  class="label"> <bean:write name="modify" property="Assessed Date"/></td>
                    <td  class="label"> <bean:write name="modify" property="PWD Status"/></td>
                     <td  class="label"> <bean:write name="modify" property="Assessement Status"/></td>
                </tr>
            </logic:iterate>

        </table>

    </body>
</html>
