<%-- 
    Document   : rationCardMemberDetails
    Created on : Feb 6, 2013, 5:47:17 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

%>
<%
            String rationCardSlno = (String) request.getAttribute("rationSlno");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        
        <%int i = 1;%>
        <script>
            function getDetails() {
                document.forms[0].mode.value="getWebDetails";
                document.forms[0].submit();
            }
        </script>
        <% String ration = (String) request.getAttribute("ration");
                    if (ration == null) {
                        ration = "";
                    }
        %>
       
    </head>
    <body >
        <html:form action="MemberDetails">
            <br/><br/>

            <logic:present name="msgSuccess">
                <center><font color="green"><bean:write name="msgSuccess"/></font></center>
            </logic:present>
            <logic:present name="msgFailure">
                <center><font color="red"><bean:write name="msgFailure"/></font></center>
            </logic:present><br/>

            <html:hidden property="mode"/>
            <logic:notEmpty name="data">

                <table  align="center" cellspacing="0" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr><td colspan="9" align="center" class="heading" >RationCard Members Details : <%=ration%></td></tr>
                    <tr>
                        <th>
                            S.No.
                        </th >
                        <th>
                            PensionId
                        </th >
                        <th>
                            SADAREMId
                        </th >
                        <th>
                            Name
                        </th >
                       <%-- <td class="formhdbg" align="center">
                            Age
                        </td >
                        <td class="formhdbg" align="center">
                            Gender
                        </td >
                        <td class="formhdbg" align="center">
                            Relation
                        </td >--%>
                        <th>
                            Ration Card Serial No.
                        </th >


                    </tr>
                    <logic:iterate name="data" id="row">
                        <bean:define id="scode" value="${row.rationCard}"/>
                        <tr>
                            <td  align="center" >
                                <%=i++%> .
                            </td>
                            <td align="left" >
                                ${row.pen}
                            </td>
                            <td align="left" >
                                ${row.sad}
                            </td>
                            <td align="left" >
                                ${row.name}
                            </td>
                           <%-- <td align="left" >
                                ${row.Age}
                            </td>
                            <td align="left" >
                                ${row.gender}
                            </td>
                            <td  align="left" >
                                ${row.relation}
                            </td>--%>
                            <td  align="left" >
                                ${row.relationSlno}
                            </td>

                        </tr>
                    </logic:iterate>
                    <tr>

                        <th align="center" colspan="8">
                            <a href="./rationCardReport.do" style=""><font color="red">Back</font></a>
                        </th>
                    </tr>
                </table><br/>

            </logic:notEmpty>    
        </html:form>
    </body>
</html>

