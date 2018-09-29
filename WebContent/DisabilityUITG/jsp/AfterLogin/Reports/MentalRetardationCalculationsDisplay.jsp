<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head><br><br>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
        }
    </script>

</head>
<body>
    <form>

        <logic:present name="mentalRetardationBean" scope="request">
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="40%">
                <tr>
                    <td class="heading" align="center">Calculation Reference</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="0" border="1" class="table" width="40%">
                <tr><th class="labelBlue"> Degree of Mental Retardation  </th><th class="labelBlue"> IQ Value  </th>
                    <th class="labelBlue"> Intellectual Imapairment</th></tr>
                <tr><th class="label"> Border Line </th><th class="label"> 70-79</th> <th class="label"> 25%</th></tr>
                <tr><th class="label"> Mild</th><th class="label">50-69 </th><th class="label">50% </th></tr>
                <tr><th class="label"> Moderate</th><th class="label">35-49 </th><th class="label">75%</th></tr>
                <tr><th class="label"> Severe</th><th class="label">20-34 </th><th class="label">90% </th></tr>
                <tr><th class="label"> Profound </th><th class="label">Less than 20</th><th class="label">100% </th></tr>

            </table> <br>
            <table  align="center" cellspacing="0" cellpadding="0" class="table" border="1" width="40%">
                <tr>
                    <td class="heading" align="center">  MENTAL RETARDATION</td>
                </tr>
                <tr>
                    <td  align="right" class="label">ID No: &nbsp;<font color="blue" ><%=personcode%></font></td>
                </tr>
                <tr>
                    <td  align="right" class="label">Name: &nbsp; <font color="blue"><%=name%></font></td>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="40%">
                <tr>
                    <td class="label">
                        IQ value Based on <logic:notEqual name="mentalRetardationBean" property="test" value="initial">
                            <bean:write name="mentalRetardationBean" property="test"/>
                        </logic:notEqual> &nbsp;&nbsp; </td>
                    <td> <bean:write name="mentalRetardationBean" property="iq"/></td></tr>
                <tr><td class="label">Total Mental Retardation  :</td><td> <bean:write name="mentalRetardationBean" property="totalpercent"/> %
                    </td></tr></table>
                </logic:present>

        <BR>
        <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()" styleClass="button" />  </tr>
            <tr></tr><tr></tr><br>
            <tr><a href="showCalc.do?typeofDisabilityFlag=9&flagPrint=print" target="_blank">
                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

        </table>
    </form>
</body>
</html>
