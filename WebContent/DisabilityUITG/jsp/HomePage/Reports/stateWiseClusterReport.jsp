<%--
    Document   : stateWiseClusterReport
    Created on : Sep 17, 2013, 12:30:15 PM
    Author     : 310926
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page import="java.util.*,java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
int i = 1;
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html:html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        
        <script type="text/javascript">
            var file, n;

            file = window.location.pathname;
            n = file.lastIndexOf('/');
            if (n >= 0) {
                file = file.substring(n + 1);
            }

            if(file=="State" ) {
                window.location.href="<%=basePath%>"+file+".do";
            }
        </script>
    </head>

    <body >
        <%
                    Calendar cal = Calendar.getInstance();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    cal.add(Calendar.DATE, -1);

        %>
    
    <html:form action="/State" >
        <html:hidden property="mode"/>

        <table height="440px" width="99%" border="0" cellspacing="0" cellpadding="10" align="center" class="table">
            <tr>
                <td align="center" valign="top">
                    <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                        <tr><td align="center" ><p><b>Indira Kranthi Patham Support to review with disability in Rural Areas
                                    <br>Telangana <br>(As on <%=dateFormat.format(cal.getTime())%>)
                                    <br>State Level Data</b></p></td></tr>
                        <tr>
                    </table>
                    <logic:notEmpty name="stateClusterReportList">

                        <% request.setAttribute("flag", "1");%>
                        <% request.setAttribute("end", "3");%>
                        <logic:iterate name="stateClusterReportList" id="row">
                            <bean:define id="sno" value="${row.flag}"/>
                              <bean:define id="bgColor" value="${row.color}"/>

                            <logic:equal value="<%=sno.toString()%>" name="flag" scope="request">
                                <br>
                                <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                                    <tr><th  align="left" ><b>${row.Heading}</b></th></tr>
                                    <tr>
                                </table>
                                    <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="table" >

                                </logic:equal>

                                <logic:notEqual value="<%=sno.toString()%>" name="flag" scope="request">
                                    <logic:notEqual value="<%=sno.toString()%>" name="end" scope="request">
                                        <tr>
                                            <td  align="left" width="50%" >${row.name}</td>
                                            <td  align="center" width="50%" > ${row.count}</td>
                                        </tr >
                                    </logic:notEqual>


                                    <logic:equal value="<%=sno.toString()%>" name="end" scope="request">
                                        <%--</table>
                                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                        --%>
                                        <tr><td  align="left"  colspan="2">
                                                <a href="#" onclick="window.open('State.do?mode=stateWiseDistrictBreakup&reportId=${row.reportId}')">
                                                    <b>${row.name}</b></a></td></tr>
                                    </table>

                                </logic:equal>
                            </logic:notEqual>

                        </logic:iterate>
                    </logic:notEmpty>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
