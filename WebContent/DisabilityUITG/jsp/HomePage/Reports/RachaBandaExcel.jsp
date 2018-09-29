<%-- 
    Document   : RachaBandaExcel
    Created on : Oct 28, 2011, 1:09:41 PM
    Author     : 484898
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%int i = 1;%>
<html:html>
    <head>
        <title>RachaBanda Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <logic:notEmpty name="rachaBandaData">
            <table align="center" cellspacing="1" border="1" cellpadding="4" width="100%">

                <tr>
                    <td colspan="13" align="center">
                        <b>SADAREM - RACHEBANDA ASSESSMENT REPORT</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                    </td>
                    
                </tr>
                <tr>
                    <td align="center">
                        <b>Sno</b>
                    </td>
                    <td align="center">
                        <b>DISTRICT</b>
                    </td>
                    <td align="center">
                        <b>TOTAL PENSIONERS</b>
                    </td>
                    <td align="center">
                        <b>PARTA ENTERED</b>
                    </td>
                    <td colspan="7" align="center">
                        <b>Assessed & Certificates Issued</b>
                    </td>
                    <td colspan="3" align="center">
                        <b> Rejected Status</b>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        &nbsp;
                    </td>
                    <td align="center">
                        &nbsp;
                    </td>
                    <td align="center">
                        &nbsp;
                    </td>
                    <td align="center">
                        &nbsp;
                    </td>
                    <td align="center">
                        <b> ORTHO</b>
                    </td>
                    <td align="center">
                        <b>VISUAL</b>
                    </td>
                    <td align="center">
                        <b>HEARING</b>
                    </td>
                    <td align="center">
                        <b>MR</b>
                    </td>
                    <td align="center">
                        <b>MI</b>
                    </td>
                    <td align="center">
                        <b>MD</b>
                    </td>
                    <td align="center">
                        <b>TOTAL</b>
                    </td>
                    <td align="center">
                        <b>DR</b>
                    </td>
                    <td align="center">
                        <b>AR</b>
                    </td>
                    <td align="center">
                        <b> TOTAL</b>
                    </td>
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
                            <%=i++%> .
                        </td>
                        <td align="left">
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
                    <td align="center" colspan="2">
                        Total
                    </td>
                    <td align="right" >
                        <b> ${totalPensionersTotal}</b>

                    </td>
                    <td align="right">
                        <b>${partAEnteredTotal}</b>

                    </td>
                    <td align="right">
                        <b>${orthoTotal} </b>

                    </td>
                    <td align="right">
                        <b>${visualTotal} </b>

                    </td>
                    <td align="right">
                        <b>${hearingTotal} </b>

                    </td>
                    <td align="right">
                        <b>${mrTotal} </b>

                    </td>
                    <td align="right">
                        <b>${miTotal} </b>

                    </td>
                    <td align="right">
                        <b>${mdTotal} </b>

                    </td>
                    <td align="right">
                        <b>${totTotal} </b>

                    </td>
                    <td align="right">
                        <b>${drTotal} </b>

                    </td>
                    <td align="right">
                        <b>${arTotal} </b>

                    </td><td align="right">
                        <b>${totalReTotal} </b>

                    </td>
                </tr>
            </table>
        </logic:notEmpty>


    </body>

    <%session.removeAttribute("rachaBandaData"); %>
</html:html>
