<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%
    String personcode=(String)session.getAttribute("personcode");
    String name=(String)session.getAttribute("Name");
%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <HEAD>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
       
        </style>
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

        <script language="javascript" >
            function goBack()
            {
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
            }
        </script>


    </HEAD>
    <body>


        <form>
             <table  align="center" width="100%">
            <logic:present name="sFBBean" scope="request">

                    <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
                    <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

                    <tr>
                        <td  align="center"><font size="4">5.&nbsp;&nbsp;<b>Seguin Form Board(S.F.B)</b></font></td>
                    </tr>




                <br>

                    <logic:equal  name="sFBBean" property="flag" value="true">

                        <tr>
                            <td>&nbsp;</td>
                            <td align="left"><b>Trials</b></td>
                            <td ><b>Time Taken in Seconds</b></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>

                    </logic:equal>

                    <logic:notEmpty name="sFBBean"  property="sfbtrialone">
                        <tr>
                            <td>&nbsp;</td>
                            <td><b>Trial 1</b></td>
                            <td>Seconds :<bean:write name="sFBBean" property="sfbtrialone"/></td>
                            <td>&nbsp;</td>
                        </tr>
                    </logic:notEmpty>

                    <logic:notEmpty name="sFBBean"  property="sfbtrialtwo">
                        <tr>
                            <td>&nbsp;</td>
                            <td><b>Trial 2</b></td>
                            <td>Seconds :<bean:write name="sFBBean" property="sfbtrialtwo"/></td>
                            <td>&nbsp;</td>
                        </tr>

                    </logic:notEmpty>

                    <logic:notEmpty name="sFBBean"  property="sfbtrialthree">
                        <tr>
                            <td>&nbsp;</td>
                            <td><b>Trial 3</b></td>
                            <td align="left">Seconds :<bean:write name="sFBBean" property="sfbtrialthree"/></td>
                            <td>&nbsp;</td>
                        </tr>
                    </logic:notEmpty>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <logic:notEqual name="sFBBean"  property="sfbyear" value="0">
                        <tr>
                            <td>&nbsp;</td>
                            <td><b>Mental Age</b></td>
                            <td><b>:</b></td>
                            <td>Years :<b><bean:write name="sFBBean" property="sfbyear"/></b></td>
                        </tr>
                        <td>&nbsp;</td>

                        <td>&nbsp;</td>
                        <td><b>:</b></td>
                        <td>Months:<b><bean:write name="sFBBean" property="sfbmonth"/></b></td>
                        </tr>
                    </logic:notEqual>

                    <logic:notEqual name="sFBBean"  property="mentalagebuffer" value="null">
                        <tr>
                            <td>&nbsp;</td>
                            <td><b>Mental Age Converted to Years</b></td>
                            <td><b>:</b></td>
                            <td>Age in years + Age in Months/12</td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><b>:</b></td>
                            <td><bean:write name="sFBBean" property="mentalagebuffer"/></td>
                        </tr>
                    </logic:notEqual>

                    <tr>
                        <td>&nbsp;</td>
                        <td><b>Today's Date</b></td>
                        <td><b>:</b></td>
                        <td><bean:write name="sFBBean" property="todaysdate"/></td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td><b>Date of Birth</b></td>
                        <td><b>:</b></td>
                        <td><bean:write name="sFBBean" property="dateofbirth"/></td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td><b>Chronological Age</b></td>
                        <td><b>:</b></td>
                        <td>(Today's Date - Date of Birth)</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>

                        <td>&nbsp;</td>
                        <td><b>:</b></td>
                        <td><bean:write name="sFBBean" property="ca"/></td>
                    </tr>

                    <tr>
                        <td colspan="4" align="left">&nbsp;As Chronological  Age is greater than 15, Chronological  Age is taken as 15 else generated value is considered.</td>
                    </tr>

                    <logic:notEqual name="sFBBean"  property="iqbuffer" value="null">


                        <tr>
                            <td>&nbsp;</td>
                            <td><b>IQ</b></td>
                            <td><b>:</b></td>
                            <td>(Mental Age/Chronological  Age)*100</td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><b><bean:write name="sFBBean" property="iqbuffer"/></b></td>
                        </tr>


                    </logic:notEqual>

           <tr></tr>


            </logic:present>


             </table>
        </form>
    </body>

</html:html>
