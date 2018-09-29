<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.text.SimpleDateFormat"%>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;
            String f = null, t = null;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String name = (String) request.getParameter("name");
            String dis = (String) request.getParameter("disability");
            if (fromdate != null && fromdate.contains("-")) {
                f = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
            }
            if (todate != null && todate.contains("-")) {
                t = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
            }



%>

<html>
    <head>

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="empWiseDetails">
            <p>Appellate Authority Registred Status Report </p>
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                <tr> <th align="center" colspan="16">
                        <logic:present name="names">
                            <bean:write name="names"/>
                        </logic:present>
                    </th>
                </tr>
                <tr>

                    <th align="center"  ><font color="white">S.No</font></th>
                    <logic:present name="ExcelHeader">
                        <th ><bean:write name="ExcelHeader"/> </th>
                    </logic:present>
                    <th align="center"  ><font color="white">Locomotor</font></th>
                    <th align="center"   ><font color="white">Visual Imapairment</font></th>
                    <th align="center"  ><font color="white">Hearing Impairment</font></th>
                    <th align="center"   ><font color="white">Menal Retardation</font></th>
                    <th align="center"  ><font color="white">Mental Illness</font></th>
                    <th align="center"  ><font color="white">Multiple Disability</font></th>
                    <th align="center"  ><font color="white">Total</font></th>


                </tr>
                <bean:define id="total" value="0"/>
                <bean:define id="ohtotal" value="0"/>
                <bean:define id="vitotal" value="0"/>
                <bean:define id="hitotal" value="0"/>
                <bean:define id="mrtotal" value="0"/>
                <bean:define id="mitotal" value="0"/>
                <bean:define id="rowwisetotal" value="0"/>
                <logic:iterate name="empWiseDetails" id="row">
                    <bean:define id="total" value="${total+row.multi}"/>
                    <bean:define id="ohtotal" value="${ohtotal+row.oh}"/>
                    <bean:define id="vitotal" value="${vitotal+row.vi}"/>
                    <bean:define id="hitotal" value="${hitotal+row.hi}"/>
                    <bean:define id="mrtotal" value="${mrtotal+row.mr}"/>
                    <bean:define id="mitotal" value="${mitotal+row.mi}"/>
                    <bean:define id="rowwisetotal" value="${row.oh+row.vi+row.hi+row.mr+row.mi+row.multi}"/>

                    <tr>
                        <td align="center">
                            <%=i++%>
                        </td>
                        <td align="center">
                            ${row.name}
                        </td>
                        <td align="center">
                            ${row.oh}
                        </td>
                        <td align="center">
                            ${row.vi}
                        </td>
                        <td align="center">
                            ${row.hi}
                        </td>
                        <td align="center">
                            ${row.mr}
                        </td>
                        <td align="center">
                            ${row.mi}
                        </td>

                        <td align="center">
                            ${row.multi}
                        </td>
                        <td align="center">
                            ${rowwisetotal}
                        </td>
                    </tr>
                    <bean:define id="rowwisetotal" value="0"/>
                </logic:iterate>
                <tr>
                    <th  align="center">

                    </th>
                    <th   align="center">
                        Total
                    </th>
                    <th  align="center">${ohtotal}</th>
                    <th  align="center">${vitotal}</th>
                    <th  align="center">${hitotal}</th>
                    <th  align="center">${mrtotal}</th>
                    <th  align="center">${mitotal}</th>
                    <th  align="center">${total}</th>
                    <th  align="center">${ohtotal+vitotal+hitotal+mrtotal+mitotal+total}</th>
                </tr>

            </table>


        </logic:notEmpty>
    </body>
</html>
