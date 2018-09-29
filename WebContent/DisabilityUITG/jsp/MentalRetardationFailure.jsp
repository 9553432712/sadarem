<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>

    <body><br><br><br><br>
        <form action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages">
            <table align="center" >
                <tr>
                    <td  ><font size="4" color="red" > <b>You are not Permitted. Please use <font size="4" color="blue" >Update Facility</font></font>
                    </td>

                </tr>

                <tr ><td align="center">
                        <a href="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages">
                            <font size="4">Back</font></a>
                </td>
                </tr>
            </table>
        </form>
    </body>
</html>
