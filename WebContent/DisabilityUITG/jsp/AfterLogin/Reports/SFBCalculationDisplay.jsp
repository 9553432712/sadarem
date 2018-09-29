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

<html:html>
    <HEAD><br>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>

    </style>


    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
        }
    </script>

    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
</HEAD>
<body>


    <form>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <logic:present name="sFBBean" scope="request">
                <tr>
                    <td  align="center" colspan="4" class="heading">5.&nbsp;&nbsp;Seguin Form Board(S.F.B)</td>
                </tr>
                <tr><td   colspan="4" align="right" class="label">ID No:&nbsp;<font color="blue"><%=personcode%></font></td></tr>
                <tr><td  colspan="4"  align="right" class="label">Name:&nbsp;<font color="blue"><%=name%></font></td></tr>






                <tr></tr>

                <logic:equal  name="sFBBean" property="flag" value="true">

                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Trials</td>
                        <td class="label">Time Taken in Seconds</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>

                </logic:equal>

                <logic:notEmpty name="sFBBean"  property="sfbtrialone">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Trial 1</td>
                        <td> class="label"Seconds :<bean:write name="sFBBean" property="sfbtrialone"/></td>
                        <td>&nbsp;</td>
                    </tr>
                </logic:notEmpty>

                <logic:notEmpty name="sFBBean"  property="sfbtrialtwo">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Trial 2</td>
                        <td class="label">Seconds :<bean:write name="sFBBean" property="sfbtrialtwo"/></td>
                        <td>&nbsp;</td>
                    </tr>

                </logic:notEmpty>

                <logic:notEmpty name="sFBBean"  property="sfbtrialthree">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Trial 3</td>
                        <td class="label">Seconds :<bean:write name="sFBBean" property="sfbtrialthree"/></td>
                        <td>&nbsp;</td>
                    </tr>
                </logic:notEmpty>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <logic:notEqual name="sFBBean"  property="sfbyear" value="0">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Mental Age</td>
                        <td class="label">:</td>
                        <td class="label">Years :<bean:write name="sFBBean" property="sfbyear"/> Months :<bean:write name="sFBBean" property="sfbmonth"/></td>


                    </tr>
                </logic:notEqual>

                <logic:notEqual name="sFBBean"  property="mentalagebuffer" value="null">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">Mental Age Converted to Years</td>
                        <td class="label">:</td>
                        <td class="label">Age in years + Age in Months/12</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td class="label">:</td>
                        <td class="label"><bean:write name="sFBBean" property="mentalagebuffer"/></td>
                    </tr>
                </logic:notEqual>

                <tr>
                    <td>&nbsp;</td>
                    <td class="label">Today's Date</td>
                    <td class="label">:</td>
                    <td><bean:write name="sFBBean" property="todaysdate"/></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="label">Date of Birth</td>
                    <td class="label">:</td>
                    <td><bean:write name="sFBBean" property="dateofbirth"/></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="label">Chronological Age</td>
                    <td class="label">:</td>
                    <td class="label">(Today's Date - Date of Birth)</td>
                </tr>

                <tr>
                    <td>&nbsp;</td>

                    <td>&nbsp;</td>
                    <td class="label">:</td>
                    <td class="label"><bean:write name="sFBBean" property="ca"/></td>
                </tr>

                <tr>
                    <td colspan="4" class="label">&nbsp;As Chronological  Age is greater than 15, Chronological  Age is taken as 15 else generated value is considered.</td>
                </tr>

                <logic:notEqual name="sFBBean"  property="iqbuffer" value="null">


                    <tr>
                        <td>&nbsp;</td>
                        <td class="label">IQ</td>
                        <td class="label">:</td>
                        <td class="label">(Mental Age/Chronological  Age)*100</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td></td>
                        <td class="label"><bean:write name="sFBBean" property="iqbuffer"/></td>
                    </tr>


                </logic:notEqual>



                <table align="center">

                    <tr>
                        <td align="center">
                            <html:button property="" value="Back" onclick="goBack()"styleClass="button" />
                        </td>
                    </tr>

                    <tr>
                        <td align="center"><a href="showCalc.do?typeofDisabilityFlag=15&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>
                        </td>
                    </tr>

                </table>
            </logic:present>



    </form>
</body>

</html:html>
