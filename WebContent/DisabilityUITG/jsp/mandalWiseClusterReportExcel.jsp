<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="mandalWiseClusterReportExcel">
            <html:hidden property="mode"/>
            <%int i = 1;%>
            <logic:notEmpty name="assetsData">
                <table align="center" border="1" cellpadding="0" cellspacing="0" width="90%">
                    <caption align="left"><font color="red"><h4>SADAREM assets Data</h4></font></caption>
                    <tr>

                        <td align="center" class="formhdbg">
                            SNo.
                        </td>
                        <%--<td class="formhdbg" align="center">District Name </td>--%>
                        <td class="formhdbg" align="center">Mandal Name </td>
                        <td class="formhdbg" align="center" > No.of Pwd's(0-55) under Sadarem </td>
                        <td class="formhdbg" align="center" >No.of pwd's Mobilised </td>
                        <td class="formhdbg" align="center">Mobilised % </td>

                    </tr>
                    <logic:iterate id="row" name="assetsData">
                        <tr>
                            <td class="formaltbg" align="center">
                                <%=i++%>.
                            </td>
                            <td class="formaltbg" align="center">${row.assesedcount}</td>
                            <td class="formaltbg" align="center">${row.mobilised}</td>
                            <td class="formaltbg" align="center">${row.percentage}</td>
                            <td class="formaltbg" align="center">${row.MandalName}</td>
                            <%--          <td class="formaltbg" align="center">${row.DistrictName}</td>--%>
                        </tr>
                    </logic:iterate>

                </table>
                <br>

            </logic:notEmpty>
            <logic:notEmpty name="IBDatabase">
                <table align="center" border="1" cellpadding="0" cellspacing="0" width="90%">
                    <caption align="left"><font color="red"><h4>Data as per IB Database</h4></font></caption>
                    <tr>

                        <td align="center" class="formhdbg">
                            SNo.
                        </td>
                        <%-- <td class="formhdbg" align="center">District Name </td>--%>
                        <td class="formhdbg" align="center">Mandal Name </td>
                        <td class="formhdbg" align="center" >No.of SHG's of Pwd formed
                            as per PMI of PD </td>
                        <td class="formhdbg" align="center" >No.of SHG's Pwd Registred
                            with IB data base book keeping</td>
                        <td class="formhdbg" align="center">Mobilised % </td>

                    </tr>
                    <logic:iterate id="row" name="IBDatabase">
                        <tr>
                            <td class="formaltbg" align="center">
                                <%=i++%>.
                            </td>
                            <td class="formaltbg" align="center">${row.assesedcount1}</td>
                            <td class="formaltbg" align="center">${row.mobilised1}</td>
                            <td class="formaltbg" align="center">${row.percentage1}</td>
                            <td class="formaltbg" align="center">${row.MandalName1}</td>
                            <%--<td class="formaltbg" align="center">${row.DistrictName1}</td>--%>
                        </tr>
                    </logic:iterate>

                </table>
            </logic:notEmpty>
            <br>

            <logic:notEmpty name="MISDatabase">
                <table align="center" border="1" cellpadding="0" cellspacing="0" width="90%">
                    <caption align="left"><font color="red"><h4>Data as per MIS Database</h4></font></caption>
                    <tr>

                        <td align="center" class="formhdbg">
                            SNo.
                        </td>
                        <%--<td class="formhdbg" align="center">District Name </td>--%>
                        <td class="formhdbg" align="center">Mandal Name </td>
                        <td class="formhdbg" align="center" >No.of pwd's formed
                            as per MIS of PD  </td>
                        <td class="formhdbg" align="center" >No.of SHG's Linked to Bank
                            at least one </td>
                        <td class="formhdbg" align="center" >Amount Bank Linked </td>

                    </tr>
                    <logic:iterate id="row" name="MISDatabase">
                        <tr>
                            <td class="formaltbg" align="center">
                                <%=i++%>.
                            </td>
                            <td class="formaltbg" align="center">${row.assesedcount2}</td>
                            <td class="formaltbg" align="center">${row.mobilised2}</td>
                            <td class="formaltbg" align="center">${row.percentage2}</td>
                            <td class="formaltbg" align="center">${row.MandalName2}</td>
                            <%--<td class="formaltbg" align="center">${row.DistrictName2}</td>--%>
                        </tr>
                    </logic:iterate>

                </table>
            </logic:notEmpty>
        </html:form>
    </body>
</html>
