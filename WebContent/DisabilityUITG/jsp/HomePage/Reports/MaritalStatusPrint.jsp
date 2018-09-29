<%-- 
    Document   : MaritalStatusPrint
    Created on : Jul 11, 2011, 1:42:15 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%
 int i = 1;
%>
<html>
    <head>
        

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <p>R3.4 : PWD's Marital Status Wise - Details</p>
      <%--  <p>Marital Status Details from ${fromdate} To ${todate}</p>--%>
        <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
            <tr>

                <logic:present name="names">
                    <th style="text-align: center" colspan="11">
                        <bean:write name="names"/>
                    </th>
                </logic:present>
            </tr>

            <tr>
                <th align="center">  S.No</th>
                <th ><bean:write name="ExcelHeader"/></th>
                <th align="center"   >Married</th>
                <th align="center"   >Un Married</th>
                <th align="center"   >Divorcee</th>
                <th align="center"   >Widow</th>
                <th align="center"   >Widower</th>
            </tr>

            <bean:define id="marriedTotal" value="0"/>
            <bean:define id="unmarriedTotal" value="0"/>
            <bean:define id="divorceeTotal" value="0"/>
            <bean:define id="widowTotal" value="0"/>
            <bean:define id="widowerTotal" value="0"/>

            <logic:iterate name="maritalStatus" id="row" >
                <bean:define id="marriedTotal" value="${marriedTotal + row.married}" />
                <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.unmarried}" />
                <bean:define id="divorceeTotal" value="${divorceeTotal + row.divorcee}" />
                <bean:define id="widowTotal" value="${widowTotal + row.widow}" />
                <bean:define id="widowerTotal" value="${widowerTotal + row.widower}" />
                <tr>
                    <td  style="text-align:center">
                        <%=i++%>
                    </td>
                    <td >
                        ${row.name}
                    </td>
                    <td  style="text-align:center">
                        ${row.married}
                    </td>
                    <td  style="text-align:center">
                        ${row.unmarried}
                    </td>
                    <td  style="text-align:center">
                        ${row.divorcee}
                    </td>
                    <td  style="text-align:center">
                        ${row.widow}
                    </td>
                    <td  style="text-align:center">
                        ${row.widower}
                    </td>
                </tr>

            </logic:iterate>
            <tr>
                <th  colspan="2" style="text-align:center"><b>Total</b></th>
                <th  style="text-align:center"><b>${marriedTotal}</b></th>
                <th  style="text-align:center"><b>${unmarriedTotal}</b></th>
                <th  style="text-align:center"><b>${divorceeTotal}</b></th>
                <th  style="text-align:center"><b>${widowTotal}</b></th>
                <th  style="text-align:center"><b>${widowerTotal}</b></th>

            </tr>
        </table>


    </body>
</html>

