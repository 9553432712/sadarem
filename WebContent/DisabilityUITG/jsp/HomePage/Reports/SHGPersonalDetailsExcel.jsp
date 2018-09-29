<%-- 
    Document   : SHGPersonalDetailsExcel
    Created on : Mar 6, 2014, 10:06:10 AM
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
        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td height="445" align="center" valign="top">

                    <table  align="center" cellspacing="1" border="1" cellpadding="4" width="100%">
                        <tr><td align="center" colspan="8">SHG's Individual Report</td></tr>
                        <tr>
                            <td colspan="8">
                                District Name : <b><%=request.getParameter("districtName")%></b>
                               
                                &nbsp;&nbsp;&nbsp;
                                Mandal Name : <b><%=request.getParameter("mandalName")%></b>
                               
                            </td>
                        </tr>
                        <tr>
                                <td class="formhdbg">
                                <b>S No</b>
                                </td>
                                <td class="formhdbg">
                                    Person Name
                                </td>
                                <td class="formhdbg">
                                    SADAREM ID
                                </td>
                                <td class="formhdbg">
                                    Pension ID
                                </td>
                                <td class="formhdbg">
                                    Ration Card No
                                </td>
                                <td class="formhdbg">
                                    Gender
                                </td>
                                <td class="formhdbg">
                                    Age
                                </td>
                                <td class="formhdbg">
                                    Education
                                </td>
                                <td class="formhdbg">
                                    Caste
                                </td>
                                <td class="formhdbg">
                                    Relation Name
                                </td>
                                <td class="formhdbg">
                                    Type Of Disability
                                </td>

                                <td class="formhdbg">
                                    Address
                                </td>

                                <td class="formhdbg">
                                    Phone Number
                                </td>
                            </tr>

                        <logic:notEmpty name="shgPersonalExcel" scope="session">
                            <logic:iterate id="row" name="shgPersonalExcel" scope="session">
                                 <tr>
                                        <td align="center">
                                            <%=i++%>
                                        </td>

                                        <td align="left">
                                            ${row.PERSONNAME}
                                        </td>
                                        <td align="left">
                                         SID- ${row.SADAREMCODE}
                                        </td>
                                        <td align="left">
                                            ${row.PENSIONID}
                                        </td>
                                        <td align="left">
                                            ${row.RATIONCARD_NUMBER}
                                        </td>
                                        <td align="left">
                                            ${row.Gender}
                                        </td>
                                        <td align="center">
                                            ${row.age_years}
                                        </td>
                                        <td align="center">
                                            ${row.EDUCATION}
                                        </td>
                                        <td align="center">
                                            ${row.CASTE}
                                        </td>
                                        <td align="left">
                                            ${row.RELATIONNAME}
                                        </td>
                                        <td align="center">
                                            ${row.TYPEOFDISABILITY}
                                        </td>

                                        <td align="center">
                                            ${row.ADDRESS}
                                        </td>
                                        <td align="center">
                                            ${row.phone_no}
                                        </td>

                                    </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>

                </td></tr></table>
    </body>
</html>
