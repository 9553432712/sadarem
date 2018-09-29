<%--
    Document   : CasteWiseReportPrintView
    Created on : Jun 22, 2011, 12:23:41 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areaDetails");
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
        <%-- 
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="casteWiseDetailsExcelDetails" scope="session">
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">

                <TR>
                    <td  colspan="8" style="text-align: center">
                        R3.2 : PWD's Caste Wise - Details
                    </td>
                </TR>

              

                <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="11">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>
                </tr>

                <tr>

                    <th align="center"  >S.No</th>
                    <th align="center"  ><bean:write name="ExcelHeader"/></th>
                    <th align="center"  >ST</th>
                    <th align="center"  >SC</th>
                    <th align="center"  >BC</th>
                    <th align="center"  >OC</th>
                    <th align="center"  >Minority</th>
                    <th align="center"  >NA</th>

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
                        <td align="center" class="label" style="width: 5%;">
                            <%=i++%>
                        </td>
                        <td class="label" style="width: 15%;">
                            ${row.name}
                        </td>
                        <td  class="label" style="width: 15%;text-align: center">
                            ${row.st}
                        </td>
                        <td  class="label" style="width: 15%;text-align: center">
                            ${row.sc}
                        </td>
                        <td class="label" style="width: 15%;text-align: center">
                            ${row.bc}
                        </td>
                        <td  class="label" style="width: 15%;text-align: center">
                            ${row.oc}
                        </td>
                        <td class="label" style="width: 15%;text-align: center">
                            ${row.mina}
                        </td>
                        <td  class="label" style="width: 15%;text-align: center">
                            ${row.na}
                        </td>
                    </tr>

                </logic:iterate>

                <tr>
                    <th  colspan="2" align="center">
                        Total
                    </th>
                    <th  align="center">
                        ${stTotal}
                    </th>
                    <th  align="center">
                        ${scTotal}
                    </th>
                    <th  align="center">
                        ${bcTotal}
                    </th><th  align="center">
                        ${ocTotal}
                    </th><th  align="center">
                        ${minaTotal}
                    </th>

                    <th  align="center">
                        ${naTotal}
                    </th>


                </tr>

            </table>


        </logic:notEmpty>
    </body>
</html>
