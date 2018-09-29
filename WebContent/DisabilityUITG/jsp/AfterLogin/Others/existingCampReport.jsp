<%--
    Document   : existingCampReport
    Created on : july 24, 2013, 11:55:16 AM
    Author     : 728056
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
       
    </head>
    <body>
        <logic:present name="msg">
            <center><font color="red">${msg}</font></center>
        </logic:present>

        <% int i = 1, x = 1;%>
        <logic:notEmpty name="sadremCampList">

            <table border="1" cellspacing="0" cellpadding="0" align="center" width="100%">

                <tr>
                    <td class="formhdbg" align="center">
                        Sno
                    </td>
                    <td class="formhdbg" align="center">
                        Pension ID
                    </td>
                    <td class="formhdbg" align="center">
                        SADAREM ID
                    </td>
                    <td class="formhdbg" align="center">
                        Name
                    </td>
                    <td class="formhdbg" align="center">
                        Relation Name
                    </td>
                    <td class="formhdbg" align="center">
                        Age
                    </td>
                    <td class="formhdbg" align="center">
                        Qualification
                    </td>
                    <td class="formhdbg" align="center">
                        Type Disabilty
                    </td>
                    <td class="formhdbg" align="center">
                        Disability Percentage
                    </td>
                    <td class="formhdbg" align="center">
                        Contact number
                    </td>
                    <td class="formhdbg" align="center">
                        PWD Status
                    </td>
                    <td class="formhdbg" align="center">
                        Assessment Status
                    </td>
                    <td class="formhdbg" align="center">
                        PensionPhase
                    </td>

                </tr>
                <logic:iterate name="sadremCampList" id="row">

                    <tr>
                        <td class="formaltbg" align="center">
                            <%=i++%> .
                        </td>
                        <td class="formaltbg">
                            ${row.PensionID}
                        </td>
                        <td class="formaltbg">
                            ${row.SADAREMID}
                        </td>
                        <td class="formaltbg">
                            ${row.Name}
                        </td>
                        <td class="formaltbg">
                            ${row.RelationName}
                        </td>
                        <td class="formaltbg">
                            ${row.Age}
                        </td>
                        <td class="formaltbg">
                            ${row.Qualification}
                        </td>
                        <td class="formaltbg">
                            ${row.DisabiltyType}
                        </td>
                        <td class="formaltbg" align="center">
                            ${row.DisabilityPercentage}
                        </td>
                        <td class="formaltbg">
                            ${row.ContactNumber}
                        </td>
                        <td class="formaltbg" align="center">
                            ${row.PWDStatus}
                        </td>
                        <td class="formaltbg" align="center">
                            ${row.AssessmentStatus}
                        </td>
                        <td class="formaltbg" align="center">
                            ${row.PensionPhase}
                        </td>


                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
    </body>
</html>
