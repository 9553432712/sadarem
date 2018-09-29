<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
%>
<%--

The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>

        <script language="javascript" >
            function goBack()
            {
                document.forms[0].action="showCalculationsAction.do";
                document.forms[0].submit();
            }
        </script>

        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    </head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <body><br>

        <form>
            <logic:present name="bBPTIBean"  scope="request">

                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%" >
                    <tr><td align="center" colspan="3" class="heading">7.  Bhatia's Battery of Intelligence Tests </td> </tr>
                    <tr><td colspan="3"  align="right" class="label">ID No:&nbsp;<font color="blue"><%=personcode%></font></td></tr>
                    <tr><td colspan="3"  align="right" class="label">Name:&nbsp;<font color="blue"><%=name%></font></td></tr>


                </table>
                <table  align="center" cellspacing="1" cellpadding="8" border="1"   class="innerTable1" width="85%" >

                    <tr>
                        <td class="label"> I  .</td>
                        <td class="label"> KOHS BLOCK DESIGN TEST </td>
                        <td class="label"> <bean:write name="bBPTIBean" property="bbpti_KOHS_Block_Design_Test"/></td>
                    </tr>

                    <tr>
                        <td class="label">  II .</td><td class="label">PASS ALONG TEST</td>
                        <td class="label"><bean:write name="bBPTIBean" property="bbpti_Passalong_Test"/></td>
                    </tr>

                    <tr>
                        <td class="label">  III. </td><td class="label">PATTERN DRAWING TEST</td>
                        <td class="label"><bean:write name="bBPTIBean" property="bbpti_PatternDrawing_Test"/></td>
                    </tr>

                    <tr>
                        <td class="label"> IV. </td><td class="label">IMMEDIATE MEMORY TEST</td>
                        <td class="label"><bean:write name="bBPTIBean" property="bbpti_ImmediateMemory_Test"/></td>
                    </tr>

                    <tr>
                        <td class="label"> V.</td><td class="label"> PICTURE CONSTRUCTION TEST</td>
                        <td><bean:write name="bBPTIBean" property="bbpti_PictureConstruction_Test"/></td>
                    </tr>


                    <tr>
                        <td></td>
                        <td  class="label"> IQ</td>
                        <td class="label"><bean:write name="bBPTIBean" property="bbpti_IQ"/></td>
                    </tr>


                    <tr>
                        <td class="label" colspan="2">Bhatias battery of intelligence tests percentage is</td>
                        <td class="label"><bean:write name="bBPTIBean" property="bbpti_IQ"/></td>
                    </tr>

                </table>  <br>

            </logic:present>


            <table align="center">
                <tr>
                    <html:button property="" value="Back" onclick="goBack()"  styleClass="button" />  </tr>
                <tr></tr><tr></tr>
                <tr><a href="showCalc.do?typeofDisabilityFlag=17&flagPrint=print" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

            </table>

        </form>



    </body>
</html:html>
