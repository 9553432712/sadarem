<%-- 
    Document   : parentsMarriageStatusPrint
    Created on : Jul 12, 2011, 6:47:58 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<html>
    <head>
        <%
                    String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");

                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                    String village_id = (String) request.getParameter("vID");
                    if (village_id == null) {
                        village_id = "0";
                    }

                    String dName = (String) request.getParameter("dN");
                    String mName = (String) request.getParameter("mN");
                    int i = 1;

                    String districtName = null;
                    String mandalName = null;
                    String villageName = null;
                    String habName = null;


                    ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) session.getAttribute("areaDetails");
                    if (getAddressList != null) {
                        if (getAddressList.size() == 1) {
                            districtName = (String) getAddressList.get(0);
                        } else if (getAddressList.size() == 2) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                        } else if (getAddressList.size() == 3) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                        }
                    }

        %>

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <p>R3.6 : Consanguineous Marriage of PWD's Parents - Details</p>
        <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
            <tr>
            <logic:present name="names">
                <th style="text-align: center" colspan="11">
                    <bean:write name="names"/>
                </th>
            </logic:present>
                </tr>
            <tr>

                <th align="center"  style="width: 5%">S.No</th>
                <th style="width: 10%;"><bean:write name="ExcelHeader"/></th>
                <th align="center"  style="width: 10%">Married</th>
                <th align="center"  style="width: 10%">UnMarried</th>
                <th align="center"  style="width: 10%">Unknown</th>


            </tr>

            <bean:define id="marriedTotal" value="0"/>
            <bean:define id="unmarriedTotal" value="0"/>
            <bean:define id="unKnown" value="0"/>

            <logic:iterate name="parentsMarriage" id="row">
                <bean:define id="marriedTotal" value="${marriedTotal + row.no}" />
                <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.yes}" />
                <bean:define id="unKnown" value="${unKnown + row.none}" />


                <tr>
                    <td  style="text-align:center" style="width: 5%;">
                        <%=i++%>
                    </td>
                    <td  style="width: 15%;">
                        ${row.name}
                    </td>
                    <td  style="text-align:center">
                        ${row.no}
                    </td>
                    <td  style="text-align:center">
                        ${row.yes}
                    </td>
                    <td  style="text-align:center">
                        ${row.none}
                    </td>

                </tr>

            </logic:iterate>

            <tr>
                <th  colspan="2" style="text-align:center"><b>Total</b></th>
                <th  style="text-align:center"><b>${marriedTotal}</b></th>
                <th  style="text-align:center"><b>${unmarriedTotal}</b></th>
                <th  style="text-align:center"><b>${unKnown}</b></th>


            </tr>

        </table>

    </body>
</html>

