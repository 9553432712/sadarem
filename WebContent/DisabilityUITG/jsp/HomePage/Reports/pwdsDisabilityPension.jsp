<%-- 
    Document   : pwdsDisabilityPension
    Created on : Oct 1, 2014, 11:52:01 AM
    Author     : 842412
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <%int i=1; %>
        <%int j=1; %>
    </head>
    <body>
        <html:form action="/PwdsDisabilityPension.do?mode=getDisabilityPensionsDetails" >
            <html:hidden property="mode"/>

            <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                <logic:present name="pensionData">
                            <%--<td  style="text-align: center">
                                <a href="PwdsDisabilityPension.do?mode=excelWriting&FIR=${no.finace}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>--%>

                            <td  style="text-align: right" colspan="7">
                                <a href="PwdsDisabilityPension.do?mode=excelWriting" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:present>
                <tr>
                    <th colspan="7">
                        PwDs - PENSIONS COVERED Disability Wise
                    </th>
                </tr>
                 <logic:present name="pensionData" scope="request">
                <tr>
                    <th>
                        S.No
                    </th>
                    <th>
                        Disability
                    </th>
                    <th>
                        40 to 79
                    </th>
                    <th>
                        %
                    </th>
                    <th>
                        80 to 100
                    </th>
                    <th>
                        %
                    </th>
                    <th>
                        Total
                    </th>
                </tr>
                 <logic:iterate id="id" name="pensionData">
                     <tr>
                         <td style="text-align: center">
                                <%=i++%>
                            </td>
                         <td >
                             ${id.disability}
                         </td>
                         <td style="text-align: center">
                             ${id.dis40to90}(${id.inter40to70}%)
                         </td>
                         <td style="text-align: center">
                             ${id.per40to79}
                         </td>
                         <td style="text-align: center">
                             ${id.dis80}(${id.inter80}%)
                         </td>
                         <td style="text-align: center">
                             ${id.per80}
                         </td>
                         <td style="text-align: center">
                             ${id.total}
                         </td>
                     </tr>
                 </logic:iterate>
                         <tr>
                             <th colspan="2">
                                 Total
                             </th>
                             <th style="text-align: center">
                                 ${id.totalDis40to90}
                             </th>
                             <th style="text-align: center">
                                 ${id.avg80}
                             </th>
                            
                             
                              <th style="text-align: center">
                                 ${id.avg40to79}
                             </th>
                             <th style="text-align: center">
                                 ${id.totalDis80}
                             </th>
                             
                             <th style="text-align: center">
                                 ${id.allTotal}
                             </th>

                         </tr>
                 </logic:present>
            </table>
            <br>
            <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                <tr>
                    <th colspan="8">
                        Analysis of PwDs Population,Assessement,Eligibility & Pensions Covered
                    </th>
                </tr>
                <logic:present name="pwdPensionData" scope="request">
                <tr>
                    <th>
                        S.No
                    </th>
                    <th>
                        District
                    </th>
                    <th>
                        Census
                    </th>
                    <th>
                        SADAREM<br>
                        Assessed
                    </th>
                    <th>
                        % of Coverage<br>
                        against Census
                    </th>
                    <th>
                        Eligible PwDs
                    </th>
                    <th>
                        Pensions<br>
                        Coverage
                    </th>
                    <th>
                        % of Pensions Covered<br>
                        against Eligiblity
                    </th>
                </tr>
                <logic:iterate id="id" name="pwdPensionData">
                <tr>
                    <td style="text-align: center">
                                <%=j++%>
                    </td>
                    <td style="text-align: left">
                                ${id.district_name}
                    </td>
                    <td style="text-align: center">
                                ${id.census}
                    </td>
                    <td style="text-align: center">
                                ${id.Totalassessed}
                    </td>
                    <td style="text-align: center">
                                ${id.coverageaganistcensus}
                    </td>
                    <td style="text-align: center">
                                ${id.Eligible}
                    </td>
                    <td style="text-align: center">
                                ${id.pensionscoverage}
                    </td>
                    <td style="text-align: center">
                                ${id.pensioncoveredaganisteligibility}
                    </td>
                </tr>
                </logic:iterate>
                <tr>
                    <th colspan="2">
                        Total
                    </th>
                    <th style="text-align: center">
                                ${id.totalCensus}
                    </th>
                    <th style="text-align: center">
                                ${id.totalAssessed}
                    </th>
                    <th style="text-align: center">
                                ${id.angCoverageaganistcensus}
                    </th>
                    <th style="text-align: center">
                                ${id.totalEligible}
                    </th>
                    <th style="text-align: center">
                                ${id.totalPensionscoverage}
                    </th>
                    <th style="text-align: center">
                                ${id.avgPensioncoveredaganisteligibility}
                    </th>
                </tr>
                 </logic:present>
            </table>
         </html:form>
    </body>
</html>
