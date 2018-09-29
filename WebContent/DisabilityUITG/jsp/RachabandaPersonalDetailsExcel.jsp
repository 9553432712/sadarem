<%--
    Document   : RachaBandaExcel
    Created on : Oct 28, 2011, 1:09:41 PM
    Author     : 484898
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%int i = 1;%>
<html:html>
    <head>
        <title>RachaBanda Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <logic:notEmpty name="rachaBandaPersonalDetails">
        <tr>
            <td style="border-left:1px #7c7c7c solid;">&nbsp;</td>
            <td height="40" align="left" valign="middle" >
                <br/>
                <table  border="0" align="center" cellspacing="1" cellpadding="4" width="100%">
                  <tr>
                      <td colspan="10" align="center">
                          <b>SADAREM - RACHEBANDA ASSESSMENT PERSONAL DETAILS</b>
                      </td>
                  </tr>

                   

                    <tr>
                        <td align="center"><b>S.No</b></td>
                        <td align="center"><b>SADAREM ID</b></td>
                        <td align="center"><b>Name</b></td>
                        <td align="center"><b>RelationName</b></td>
                        <td align="center"><b>Age</b></td>
                        <td align="center"><b>Gender</b></td>
                        <td align="center"><b>Disability</b></td>
                        <td align="center"><b>District</b></td>
                        <td align="center"><b>Mandal</b></td>
                        <td align="center"><b>Village</b></td>

                    </tr>
                    <logic:iterate id="rows" name="rachaBandaPersonalDetails" scope="session">
                        <tr>
                            <td align="center">
                                <%=i++%> .
                            </td>
                            <td >
                                ID - ${rows.personCode}
                            </td>
                            <td>
                                ${rows.name}
                            </td>
                            <td >
                                ${rows.relationName}
                            </td>
                            <td>
                                ${rows.age}
                            </td>
                            <td>
                                ${rows.gender}
                            </td>
                            <td>
                                ${rows.disability}
                            </td>
                            <td>
                                ${rows.district}
                            </td>
                            <td>
                                ${rows.mandal}
                            </td>
                            <td>
                                ${rows.village}
                            </td>
                        </tr>

                    </logic:iterate>

                </table><br/>


            </logic:notEmpty>


        </body>
        <% session.removeAttribute("rachaBandaPersonalDetailsExcel"); %>

    </html:html>
