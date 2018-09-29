<%-- 
    Document   : RachaBandaPrint
    Created on : Oct 28, 2011, 1:09:59 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
    <head>
        
        <title>RachaBanda Details</title>
        <%int i = 1;%>
        
    </head>
    <body onload="window.print();">

        <logic:notEmpty name="rachaBandaData" >
        <table align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="100%">
            
            <tr>
                <td colspan="14" align="center">
                    <b><p>SADAREM - RACHEBANDA ASSESSMENT REPORT</p></b>
                </td>
            </tr>

            <tr>
                <th align="center" >
                    <b>Sno</b>
                </th>
                <th  align="center">
                    DISTRICT
                </th>
                <th  align="center">
                    TOTAL PENSIONERS
                </th>
                <th  align="center">
                    PARTA ENTERED
                </th>
                <th colspan="7"  align="center">
                    Assessed & Certificates Issued
                </th>
                <th colspan="3"  align="center">
                    Rejected Status
                </th>
            </tr>
            <tr>
                <td  align="center">
                    &nbsp;
                </td>
                <td  align="center">
                    &nbsp;
                </td>
                <td  align="center">
                    &nbsp;
                </td>
                <td  align="center">
                    &nbsp;
                </td>
                <th  align="center">
                    ORTHO
                </th>
                <th  align="center">
                    VISUAL
                </th>
                <th  align="center">
                    HEARING
                </th>
                <th  align="center">
                    MR
                </th>
                <th  align="center">
                    MI
                </th>
                <th  align="center">
                    MD
                </th>
                <th  align="center">
                    TOTAL
                </th>
                <th  align="center">
                    DR
                </th>
                <th  align="center">
                    AR
                </th>
                <th  align="center">
                    TOTAL
                </th>
            </tr>
            <bean:define id="totalPensioners" value="0"/>
            <bean:define id="partAEntered" value="0"/>
            <bean:define id="ortho" value="0"/>
            <bean:define id="visual" value="0"/>
            <bean:define id="hearing" value="0"/>
            <bean:define id="mr" value="0"/>
            <bean:define id="mi" value="0"/>
            <bean:define id="md" value="0"/>
            <bean:define id="tot" value="0"/>
            <bean:define id="dr" value="0"/>
            <bean:define id="ar" value="0"/>
            <bean:define id="totalRe" value="0"/>
            <logic:iterate name="rachaBandaData" id="row" scope="session">
                <bean:define id="totalPensionersTotal" value="${totalPensionersTotal+row.totalPensioners}"/>
                <bean:define id="partAEnteredTotal" value="${partAEnteredTotal+row.partAEntered}"/>
                <bean:define id="orthoTotal" value="${orthoTotal+row.ortho}"/>
                <bean:define id="visualTotal" value="${visualTotal+row.visual}"/>
                <bean:define id="hearingTotal" value="${hearingTotal+row.hearing}"/>
                <bean:define id="mrTotal" value="${mrTotal+row.mr}"/>
                <bean:define id="miTotal" value="${miTotal+row.mi}"/>
                <bean:define id="mdTotal" value="${mdTotal+row.md}"/>
                <bean:define id="totTotal" value="${totTotal+row.total}"/>
                <bean:define id="drTotal" value="${drTotal+row.dr}"/>
                <bean:define id="arTotal" value="${arTotal+row.ar}"/>
                <bean:define id="totalReTotal" value="${totalReTotal+row.totalRe}"/>
                <tr>
                    <td>
                        <%=i++%>.
                    </td>
                    <td>
                        ${row.district}
                    </td>
                    <td align="right">
                        ${row.totalPensioners}
                    </td>
                    <td align="right">
                        ${row.partAEntered}
                    </td>
                    <td align="right">
                        ${row.ortho}
                    </td>
                    <td align="right">
                        ${row.visual}
                    </td>
                    <td align="right">
                        ${row.hearing}
                    </td>
                    <td align="right">
                        ${row.mr}
                    </td>
                    <td align="right">
                        ${row.mi}
                    </td>
                    <td align="right">
                        ${row.md}
                    </td>
                    <td align="right">
                        ${row.total}
                    </td>
                    <td align="right">
                        ${row.dr}
                    </td>
                    <td align="right">
                        ${row.ar}
                    </td>
                    <td align="right">
                        ${row.totalRe}
                    </td>
                </tr>
            </logic:iterate>
            <tr>
                <td  align="center" colspan="2">
                    <b>Total</b>
                </td>
                <td  align="right" >
                    <b> ${totalPensionersTotal}</b>

                </td>
                <td  align="right">
                    <b>${partAEnteredTotal}</b>

                </td>
                <td  align="right">
                    <b>${orthoTotal} </b>

                </td>
                <td  align="right">
                    <b>${visualTotal} </b>

                </td>
                <td  align="right">
                    <b>${hearingTotal} </b>

                </td>
                <td  align="right">
                    <b>${mrTotal} </b>

                </td>
                <td  align="right">
                    <b>${miTotal} </b>

                </td>
                <td  align="right">
                    <b>${mdTotal} </b>

                </td>
                <td  align="right">
                    <b>${totTotal} </b>

                </td>
                <td  align="right">
                    <b>${drTotal} </b>

                </td>
                <td  align="right">
                    <b>${arTotal} </b>

                </td><td  align="right">
                    <b>${totalReTotal} </b>

                </td>
            </tr>
        </table>
    </logic:notEmpty>
         <%session.removeAttribute("rachaBandaData"); %>
</body>
</html>
