<%-- 
    Document   : stateWiseDistrictBreakUpReport
    Created on : Sep 25, 2013, 11:18:41 AM
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

<html:html>

    <head>
       
        <title>SADAREM</title>
        

        <%int columnCount = 0;
                    if (request.getAttribute("columnsCount") != null) {
                        columnCount = (Integer) request.getAttribute("columnsCount");

                    }
                    ArrayList data = new ArrayList();
                    if (request.getAttribute("stateClusterReportList") != null) {
                        data = (ArrayList) request.getAttribute("stateClusterReportList");

                    }
        %>

    </head>

    <body >

    <html:form action="/State" >
        <html:hidden property="mode"/>

        <table  width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td align="center"  valign="top">
                    <logic:notEmpty name="msg">
                        <center><font color="red" size="3"><b>${msg}</b></font></</center>
                    
                    </logic:notEmpty>
                    <logic:empty name="msg">
                        <logic:notEmpty name="columnNames">
                            <br>
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="table">
                                <tr>
                                    <logic:iterate name="columnNames" id="row">
                                        <td  align="center">
                                            ${row.columnName}</td>
                                        </logic:iterate>
                                </tr>

                            </logic:notEmpty>
                            <%for (int i = 0; i < data.size(); i++) {
                                            ArrayList list1 = new ArrayList();
                                            list1 = (ArrayList) data.get(i);%>
                            <tr>
                                <% if (list1 != null) {
                                        for (int j = 0; j < list1.size(); j++) {
                                            String count = list1.get(j).toString();%>


                                <td class="formaltbg" align="center" >
                                    <%=count%>
                                </td>

                                <%}%>
                            </tr>  <%   }
                                        }%>

                        </table>
                    </logic:empty>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
