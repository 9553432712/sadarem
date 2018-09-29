<%-- 
    Document   : CasteWiseReportExcelView
    Created on : Jun 22, 2011, 12:23:41 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");
            String fromDate = (String) request.getParameter("fromDate");
            String toDate = (String) request.getParameter("toDate");

            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areaDetails");
            //  for(int j = 0;j<=getAddressList.size();j++) {

            if (getAddressList.size() > 0) {
                if (getAddressList.size() == 1) {
                    districtName = (String) getAddressList.get(0);
                } else if (getAddressList.size() == 2) {
                    districtName = (String) getAddressList.get(0);
                    mandalName = (String) getAddressList.get(1);
                } else if (getAddressList.size() == 3) {
                    districtName = (String) getAddressList.get(0);
                    mandalName = (String) getAddressList.get(1);
                    villageName = (String) getAddressList.get(2);
                } else if (getAddressList.size() == 4) {
                    districtName = (String) getAddressList.get(0);
                    mandalName = (String) getAddressList.get(1);
                    villageName = (String) getAddressList.get(2);
                    habName = (String) getAddressList.get(3);
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="casteWiseDetailsExcelDetails" scope="session">
            <table border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <p>  R3.2 : PWD's Caste Wise - Details</p>
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="90%">
                            <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="11">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>
                            </tr>
                            <tr>
                                <td align="center" class="formhdbg"><b>S.No</b></td>
                               <th align="center"  ><bean:write name="ExcelHeader"/></th>
                                <td align="center" class="formhdbg"><b>ST</b></td>
                                <td align="center" class="formhdbg"><b>SC</b></td>
                                <td align="center" class="formhdbg"><b>BC</b></td>
                                <td align="center" class="formhdbg"><b>OC</b></td>
                                <td align="center" class="formhdbg"><b>Minority</b></td>
                                <td align="center" class="formhdbg"><b>NA</b></td>
                            </tr>

                            <bean:define id="stTotal" value="0"/>
                            <bean:define id="scTotal" value="0"/>
                            <bean:define id="bcTotal" value="0"/>
                            <bean:define id="ocTotal" value="0"/>
                            <bean:define id="minaTotal" value="0"/>
                            <bean:define id="naTotal" value="0"/>
                            <logic:iterate name="casteWiseDetailsExcelDetails" id="row" scope="session">
                                <bean:define id="stTotal" value="${stTotal + row.st}"/>
                                <bean:define id="scTotal" value="${scTotal + row.sc}"/>
                                <bean:define id="bcTotal" value="${bcTotal + row.bc}"/>
                                <bean:define id="ocTotal" value="${ocTotal + row.oc}"/>
                                <bean:define id="minaTotal" value="${minaTotal + row.mina}"/>
                                <bean:define id="naTotal" value="${naTotal + row.na}"/>
                                <tr>
                                    <td align="center">
                                        <%=i++%>
                                    </td>
                                    <td >
                                        ${row.name}
                                    </td>
                                    <td align="center">
                                        ${row.st}
                                    </td>
                                    <td align="center">
                                        ${row.sc}
                                    </td>
                                    <td align="center">
                                        ${row.bc}
                                    </td>
                                    <td align="center">
                                        ${row.oc}
                                    </td>
                                    <td align="center">
                                        ${row.mina}
                                    </td>
                                    <td align="center">
                                        ${row.na}
                                    </td>
                                </tr>

                            </logic:iterate>

                            <tr>
                                <td colspan="2" align="center">
                                    <b>Total</b>
                                </td>
                                <td  align="center">
                                    <b> ${stTotal}</b>
                                </td>
                                <td  align="center">
                                    <b> ${scTotal}</b>
                                </td>
                                <td align="center">
                                    <b> ${bcTotal}</b>
                                </td><td align="center">
                                    <b> ${ocTotal}</b>
                                </td><td  align="center">
                                    <b>  ${minaTotal}</b>
                                </td>

                                <td  align="center">
                                    <b>  ${naTotal}</b>
                                </td>

                            </tr>

                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>

    </body>
</html>
