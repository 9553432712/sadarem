<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>

<html:html>
    <head>
    </head>
    <body>
<table  border="0" align="center" cellspacing="1" cellpadding="5"  width="50%">
    <tr align="center"  width="100%">
    <td><font color="#c71585">Personal Details Report From <%= fromdate %>
    To <%= todate %></font> </td></tr>
    <tr>
    <td>
    <table  border="1" align="center" cellspacing="1" cellpadding="5" width="70%">
        <tr>
                    <td><b>S.NO</b></td>
                    <td nowrap><b>Personcode</b></td>
                    <td nowrap><b>Surname</b></td>
                    <td nowrap><b>FirstName</b></td>
                    <td nowrap><b>Gender</b></td>
                    <td nowrap><b>Date of Birth</b></td>
                    <td nowrap><b>Age</b></td>
                    <td nowrap><b>Religion</b></td>
                    <td nowrap><b>Caste</b></td>
                    <td nowrap><b>Relation Name</b></td>
                    <td nowrap><b>Relationship</b></td>
                    <td nowrap><b>RationCardNumber</b></td>
                    <td nowrap><b>RationCard Type</b></td>
                    <td nowrap><b>Pension Type</b></td>
                    <td nowrap><b>PensionCardNumber</b></td>
                    <td nowrap><b>Identification Marks1</b></td>
                    <td nowrap><b>Identification Marks2</b></td>
                    <td nowrap><b>HouseNumber</b></td>
                    <td nowrap><b>District</b></td>
                    <td nowrap><b>Mandal</b></td>
                    <td nowrap><b>Village</b></td>
                    <td nowrap><b>Habitation</b></td>
                    <td nowrap><b>Type of Disability</b></td>
                </tr>
                <% int i=0;%>
<logic:iterate id="disabilityreport" name="arraylist1">
    <tr>
        <td align="center"><%=++i%>
        <td align="center">PWDID-<bean:write name="disabilityreport" property="personcode"/></td>
        <td align="center"><bean:write name="disabilityreport" property="surname"/></td>
        <td align="center"><bean:write name="disabilityreport" property="firstname"/></td>
        <td align="center"><bean:write name="disabilityreport" property="gender"/></td>
        <td align="center"><bean:write name="disabilityreport" property="dobday"/></td>
        <td align="center"><bean:write name="disabilityreport" property="noOfYears"/></td>
        <td align="center"><bean:write name="disabilityreport" property="religion"/></td>
        <td align="center"><bean:write name="disabilityreport" property="caste"/></td>
        <td align="center"><bean:write name="disabilityreport" property="gsurname"/></td>
        <td align="center"><bean:write name="disabilityreport" property="grelation"/></td>
        <td align="center"><bean:write name="disabilityreport" property="card"/></td>
        <td align="center"><bean:write name="disabilityreport" property="rtype"/></td>
        <td align="center"><bean:write name="disabilityreport" property="pension_type"/></td>
        <td align="center"><bean:write name="disabilityreport" property="pensioncardno"/></td>
        <td align="center"><bean:write name="disabilityreport" property="mole1"/></td>
        <td align="center"><bean:write name="disabilityreport" property="mole2"/></td>
        <td align="center"><bean:write name="disabilityreport" property="houseno"/></td>
        <td align="center"><bean:write name="disabilityreport" property="district"/></td>
        <td align="center"><bean:write name="disabilityreport" property="mandal"/></td>
        <td align="center"><bean:write name="disabilityreport" property="village_name"/></td>
        <td align="center"><bean:write name="disabilityreport" property="habitation_name"/></td>
        <td align="center"><bean:write name="disabilityreport" property="type_disability"/></td>
    </tr>
</logic:iterate>
            </table>
        </table>
    </body>
    <p>&nbsp;</p>

</html:html>
