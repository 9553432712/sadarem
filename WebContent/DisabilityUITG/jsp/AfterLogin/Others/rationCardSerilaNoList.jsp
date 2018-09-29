<%--
    Document   : issueResolve
    Created on : Nov 29, 2012, 4:36:00 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList"%>
<%
            ArrayList rationCardNotOpenList = new ArrayList();
            if (request.getAttribute("rationCardNotOpenList") != null) {
                rationCardNotOpenList = (ArrayList) request.getAttribute("rationCardNotOpenList");
            }

            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body >

        <table align="center" class="inputform" cellspacing="1"  cellpadding="0" width="50%">
            <tr>
                <td>
                    <html:form action="/issueRaiseApproval" enctype="multipart/form-data">
                        <html:hidden property="mode"/>
                        <logic:present name="msg">
                            <center>${msg}</center>
                        </logic:present>
                        <logic:notEmpty name ="rationCardNotOpenList">

                            <table align="inputform" cellspacing="1"  cellpadding="0" width="100%">
                                <tr >
                                    <th colspan="4">
                                        Ration Card Details
                                    </th>
                                </tr>
                                <tr>
                                    <th  >
                                        S.NO
                                    </th>
                                    <th  >
                                        SADAREM ID
                                    </th>
                                    <th >
                                        RationCard SerialNo.
                                    </th>
                                    <th >
                                        Status
                                    </th>
                                </tr>

                                <logic:iterate  name="rationCardNotOpenList" id="row">
                                    <tr>
                                        <td  align="center">
                                            <%=i++%>
                                        </td>
                                        <td  align="center">
                                            ${row.householdcardno}
                                        </td>
                                        <td  align="center">
                                            ${row.slno}
                                        </td>
                                        <td  align="center">
                                            ${row.membername}
                                        </td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td align="center">
                                        <img src="./DisabilityUITG/images/close.jpg" onclick="javascript:window.close();"/>
                                    </td>
                                </tr>
                            </table>
                        </logic:notEmpty>

                    </td>
                </tr>
            </table>
        </html:form>

    </body>
</html>
