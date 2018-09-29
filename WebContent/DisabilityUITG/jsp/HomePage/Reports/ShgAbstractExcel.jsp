<%-- 
    Document   : ShgAbstractExcel
    Created on : Mar 6, 2014, 9:44:01 AM
    Author     : mohanb
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<% int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <logic:notEmpty name="excelData" scope="session">
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">

                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="70%">
                            <tr><td  align="center" colspan="11">SHG's Report</td></tr>
                            <tr>

                                <td  >
                                    <b> Sno</b>
                                </td>
                                <td  >
                                    <b> Mandal</b>
                                </td>
                                <td  >
                                    <b> Total Applied</b>
                                </td>
                                <th>
                                    Age (<18)  PwDs
                                </th>
                                <th>
                                    Age (0-50)  PwDs
                                </th>
                                <th>
                                    Age (> 50)  PwDs
                                </th>
                                <th>
                                    Dead  PwDs
                                </th>
                                <th>
                                    Migratant  PwDs
                                </th>
                                <%-- <th>
                                     Pensioners Who can be deleted
                                 </th>--%>
                                <th>
                                    Eligible  PwDs for SHG's
                                </th>
                                <th>
                                    PwD's Mapped for SHGS
                                </th>
                                <th>
                                    To Be Mapped
                                </th>
                            </tr>


                            <logic:iterate id="row" name="excelData" scope="session">
                                <tr>
                                    <td align="center">
                                        <%=i++%>.
                                    </td>
                                    <td align="left">
                                        ${row.madnalName}
                                    </td>
                                    <td align="left">
                                        ${row.total_pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.Age18Pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.Age18to55Pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.Age55Pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.DeadPensioners}
                                    </td>
                                    <td align="center">
                                        ${row.Migratant_Pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.Eligible_pensioners}
                                    </td>
                                    <td align="center">
                                        ${row.totalSHGMembers}
                                    </td>
                                    <td align="center">
                                        ${row.percentageSHGMembers}
                                    </td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td></tr></table><br/>
                </logic:notEmpty>
    </body>
</html>
