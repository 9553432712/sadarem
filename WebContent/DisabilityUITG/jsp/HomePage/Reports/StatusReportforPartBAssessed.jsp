<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<html:html>
    <head>
        <%int i = 1;%>
        
        <title>SADAREM</title>
        

        <%
                    String pensionStatus = (String) request.getParameter("pensionStatus");
                    String fDate = (String) request.getParameter("fDate");
                    String toDate = (String) request.getParameter("toDate");

                     ArrayList dailyreportlist = new ArrayList();
                            dailyreportlist = (ArrayList) request.getAttribute("dailyReportList");

        %>


    </head>
    <body>

    <logic:notEmpty name="dailyReportList">

        <%
                    String pensionName = null;
                    if (pensionStatus.equals("3")) {
                        pensionName = "Total Pensioners";
                    } else if (pensionStatus.equals("4")) {
                        pensionName = "Death";
                    } else if (pensionStatus.equals("5")) {
                        pensionName = "P.M";
                    } else if (pensionStatus.equals("6")) {
                        pensionName = "Total";
                    } else if (pensionStatus.equals("7")) {
                        pensionName = "ICFS";
                    } else if (pensionStatus.equals("8")) {
                        pensionName = "Others(ABH,Suspended,Temporary Migration & Not Elg)";
                    } else if (pensionStatus.equals("9")) {
                        pensionName = "Total No.of persons to under go SADAREM";
                    } else if (pensionStatus.equals("10")) {
                        pensionName = "No. for Part-A entered";
                    } else if (pensionStatus.equals("11")) {
                        pensionName = "Live";
                    } else if (pensionStatus.equals("12")) {
                        pensionName = "Death & PM";
                    } else if (pensionStatus.equals("13")) {
                        pensionName = "ICFS";
                    } else if (pensionStatus.equals("14")) {
                        pensionName = "Others(ABH,Suspended,Temporary Migration & Not ELG)";
                    } else if (pensionStatus.equals("15")) {
                        pensionName = "Total Assessed ";
                    } else if (pensionStatus.equals("16")) {
                        pensionName = "No of persons to be assessed ";
                    } else if (pensionStatus.equals("17")) {
                        pensionName = "Ortho";
                    } else if (pensionStatus.equals("18")) {
                        pensionName = "Visual";
                    } else if (pensionStatus.equals("19")) {
                        pensionName = "Hearing";
                    } else if (pensionStatus.equals("20")) {
                        pensionName = "Mental Retardation";
                    } else if (pensionStatus.equals("21")) {
                        pensionName = "Mental Illness";
                    } else if (pensionStatus.equals("22")) {
                        pensionName = "Multiple Disability";
                    } else if (pensionStatus.equals("23")) {
                        pensionName = "Total";
                    } else if (pensionStatus.equals("24")) {
                        pensionName = "Before Assment (No visible disb.)";
                    } else if (pensionStatus.equals("25")) {
                        pensionName = "After Assment (<40% disb.)";
                    } else if (pensionStatus.equals("26")) {
                        pensionName = "Total Rejected";
                    }
        %>

        <table  align="center" cellspacing="0" border="1" cellpadding="0" class="innerTable" width="100%" style="border-style: outset">
            <tr>
                <td align="center" colspan="14"><img src="DisabilityUITG/images/Banner.jpg"/></td>
            </tr>
            <tr>
                <td align="center" colspan="14" ><b><p>Assessed PWD's Details (<%=pensionName%>) As on <%=fDate%> to <%=toDate%></p></b></td>
            </tr>

            <tr>
                <th align="center"  >
                    Sno
                </th>
                <th align="center"  >
                    Pension ID
                </th>
                <th align="center"  >
                    SADAREM ID
                </th>
                <th align="center"  >
                    Name
                </th>
                <th align="center"  >
                    Relation Name
                </th>
                <th align="center"  >
                    Age
                </th>
                <th align="center"  >
                    Gender
                </th>
                <th align="center"  >
                    Type of <br/> Disability
                </th>
                <th align="center"  >
                    Disability <br/> Percentage
                </th>
                <th align="center"  >
                    District
                </th>
                <th align="center"  >
                    Mandal
                </th>
                <th align="center"  >
                    Village
                </th>
                <th align="center"  >
                    Reason for Status
                </th>
                <th align="center"  >
                    Phase
                </th>
            </tr>
            <logic:iterate name="dailyReportList" id="row">

                <tr>
                    <td align="center" class="label">
                        <%=i++%>.
                    </td>

                    <td class="label" align="center">
                        ${row.dis_per}
                    </td>

                    <td class="label" align="center">
                        ${row.pensionId}
                    </td>


                    <td class="label">
                        ${row.name}
                    </td>


                    <td class="label">
                        ${row.fname}
                    </td>


                    <td class="label" align="center">
                        ${row.age}
                    </td>


                    <td class="label" align="center">
                        ${row.gender}
                    </td>



                    <td class="label" >
                        ${row.phc}
                    </td>

                    <td class="label" align="center">
                        ${row.rationcardno}
                    </td>

                    <td class="label">
                        ${row.districtName}
                    </td>
                    <td class="label">
                        ${row.mandalName}
                    </td>
                    <td class="label">
                        ${row.villageName}
                    </td>
                    <td class="label">
                        ${row.reasonforStatus}
                    </td>

                    <td class="label" align="center">
                        ${row.pensionphase}
                    </td>

                </tr>
             
            </logic:iterate>
                   <%
                           
                            session.setAttribute("dailyReportList", dailyreportlist);
                %>
            <tr>
                <td align="center" colspan="14">
                    <a href="distReportForPartbSubExcel.xls?fromdate=<%=fDate%>&todate=<%=toDate%>&pensionPhase=<%=pensionStatus%>" target="_blank">
                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" onclick="window.print()">
                    Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>
                </td>
              
            </tr>
        </table>
    </logic:notEmpty>

</body>
</html:html>

