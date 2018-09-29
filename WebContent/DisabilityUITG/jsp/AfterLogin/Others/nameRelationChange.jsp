<%--
    Document   : nameRelationChange
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>

<%

            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>

    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <script>

        function submitValues(){

            document.forms[0].mode.value ="getRequestStatusDetails";
            document.forms[0].submit();

        }

    </script>

    <body>
        <html:form  action="/nameRelationChange">
            <html:hidden property="mode"/>

            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>
            
            <logic:notEmpty name="campList">
                <table cellpadding="0" cellspacing="1" align="center" width="90%" class="inputform" border="0">
                    <tr>
                        <th colspan="10">
                            Name and Relation Name Change
                        </th>
                    </tr>
                    <tr>
                        <th>
                            SNo.
                        </th>
                        <th>
                            SADAREM ID
                        </th>
                         <th>
                            Request Type
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            RelationName
                        </th>

                        <th>
                            District
                        </th>
                        <th>
                            Mandal
                        </th>
                        <th>
                            Village
                        </th>
                        <th>
                            Habitation
                        </th>

                        <th>
                            View
                        </th>
                    </tr>

                    <logic:iterate name="campList" id="row">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>
                            <td>
                                ${row.personCode}
                            </td>
                            <td>
                                ${row.requesttypeName}
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td>
                                ${row.relationName}
                            </td>
                            <td>
                                ${row.districtName}
                            </td>
                            <td>
                                ${row.mandalName}
                            </td>
                            <td>
                                ${row.villageName}
                                <html:hidden property="requesttypeidDataForm" value="${row.requesttypeid}"/>
                            </td>
                            <td>
                                ${row.habitationName}
                            </td>

                            <td>
                                <a href="nameRelationChange.do?mode=getnameRelationPersonalDetails&requestId=${row.requestId}&requesttypeId=${row.requesttypeid}&personCode=${row.personCode}">View</a>
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:notEmpty>

            </table> 

        </html:form>
    </body>
</html>
