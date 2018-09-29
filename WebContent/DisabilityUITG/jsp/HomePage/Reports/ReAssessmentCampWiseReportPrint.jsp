<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
    Author     : 490058
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;


%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        

    </head>
    <body onload="window.print();">


        <html:form  action="reAssessmentCampWiseReport.do"  method="post">
            <html:hidden property="mode"/>

            <logic:present name="msgss">
                <font color="red" size="3"><bean:write name="msgss"/></font>
            </logic:present>

                <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
                    <tr>
                        <th colspan="9">
                            ReAssessment CampWise Report Details
                        </th>
                    </tr>

                
                <logic:notEmpty name="AssesmentListData">

                    <tr>
                        <th >
                            SNo
                        </th>

                        <th >
                            PersonID
                        </th>
                        <th >
                            SADAREMID
                        </th>
                        <th >
                            Name
                        </th>
                        <th >
                            Age
                        </th>
                        <th >
                            Disability Type
                        </th>
                        <th >
                            Old Percentage
                        </th>
                        <th >
                            New Percentage
                        </th>
                        <th >
                            ReAssessmentText
                        </th>
                    </tr>

                    <logic:iterate name="AssesmentListData" id="row" scope="session">

                        <tr>
                            <td >
                                <%=i++%>
                            </td>

                            <td >
                                ${row.pensionCard}
                            </td>
                            <td >
                                ${row.personCode}
                            </td>
                            <td >
                                ${row.Name}
                            </td>
                            <td >
                                ${row.age}
                            </td>
                            <td >
                                ${row.disability}
                            </td>

                            <td >
                                ${row.totalDisabilityInActive}
                            </td>
                            <td >
                                ${row.totaldisabilityActive}
                            </td>
                            <td >
                                ${row.reAssessmentText}
                            </td>
                        </tr>

                    </logic:iterate>
                </table>
                <%session.setAttribute("AssesmentListData", (ArrayList) request.getAttribute("AssesmentList"));%>
            </logic:notEmpty>

<br>

</html:form>
</body>
</html:html>