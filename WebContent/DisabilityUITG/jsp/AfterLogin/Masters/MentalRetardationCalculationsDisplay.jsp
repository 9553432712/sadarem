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

    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

</head>
<body>
    <form>


        <logic:present name="mentalRetardationBean" scope="request">
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="40%">
                <tr>
                    <td class="heading" align="center">Calculation Reference</td>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="10" border="1" class="innerTable1" width="40%">
                <tr><td class="labelBlue"> Degree of Mental Retardation  </td><td class="labelBlue"> IQ Value  </td> <td class="labelBlue"> Intellectual Imapairment</td></tr>
                <tr><td class="label"> Border Line </td><td class="label"> 70-79</td> <td class="label"> 25%</td></tr>
                <tr><td class="label"> Mild</td><td class="label">50-69 </td><td class="label">50% </td></tr>
                <tr><td class="label"> Moderate</td><td class="label">35-49 </td><td class="label">75%</td></tr>
                <tr><td class="label"> Severe</td><td class="label">20-34 </td><td class="label">90% </td></tr>
                <tr><td class="label"> Profound </td><td class="label">Less than 20</td><td class="label">100% </td></tr>

            </table> <br>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="40%">
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
