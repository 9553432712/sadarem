<%-- 
    Document   : appealAuthorityAbstractReport
    Created on : Aug 25, 2011, 5:32:10 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            int i = 1;
            String mandal_id = request.getParameter("mandal_id");
            String district_id = (String) session.getAttribute("districtId");
%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>

            function getData() {
                document.forms[0].mode.value="getVillages";
                document.forms[0].submit();
            }

        </script>
    </head>

    <body>
        <html:form action="/appealAbstract" >
            <html:hidden property="mode"/>


            <logic:present name="msg">
                <center><font color="red"><b><bean:write name="msg"/></b></font></center>
            </logic:present>


            <table align="center" border="0" cellpadding="0" cellspacing="1" class="inputform" width="55%" >

                <tr>

                    <th align="center">
                         Second Time Re-Assessment Schedule Report
                    </th>
                </tr>
                <tr>
                    <td style="text-align: center">
                        Mandal :  <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
                            <html:option  value="0">-- Select --</html:option>
                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;

                    </td>

                </tr>

            </table><br/>
            <logic:notEmpty name="scheduleData">
                <center>

                    <div style="overflow: auto;width: 885px;height: 450px" >
                        <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="55%">
                            <tr>
                                <th class="formhdbg" align="center">
                                    S No
                                </th>
                                <th class="formhdbg" align="center">
                                    Village
                                </th>
                                <th class="formhdbg" align="center">
                                    Schedule Date
                                </th>
                            </tr>
                            <logic:iterate name="scheduleData" id="row">
                                <tr>
                                    <td style="text-align: center">
                                        <%=i++%> .
                                    </td>
                                    <td class="formaltbg">
                                        ${row.village_name}
                                    </td>
                                    <td style="text-align: center">
                                        ${row.scheduleDate}
                                    </td>
                                </tr>
                            </logic:iterate>
                            <tr>
                                <td colspan="3" style="text-align: center">
                                    <a href="appealAbstract.xls?mode=excel&mandalId=<%=mandal_id%>" target="_blank">
                                        Excel <img src="DisabilityUITG/images/excel.jpg"
                                                   height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                    <a href="appealAbstract.do?mode=print&mandalId=<%=mandal_id%>" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </center>
            </logic:notEmpty>
        </html:form>
    </body>
</html:html>

