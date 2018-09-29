<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
            String doctor = null;
            if (request.getAttribute("doctor") != null) {
                doctor = (String) request.getAttribute("doctor");
            }
%>
<script language="javascript">


    function goBack()
    {
        document.forms[0].action="CalculationForwardAction.do";
        document.forms[0].submit();
    }


   
</script>
<html:html>
     <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    <body>

        <form>


            <br><br>

            <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%">
                <tr>
                    <td class="heading" align="center">Calculation Links for PWD<br>
                        <font size="2" color="blue">(If Required Click the Below Links)</font></td>
                </tr>
                <tr>
                    <td colspan="3"  align="right" class="label">ID No: &nbsp;<font color="blue" ><%=personcode%></font></td>
                </tr>
                <tr>
                    <td colspan="3"  align="right" class="label">Name: &nbsp; <font color="blue"><%=name%></font></td>
                </tr>

            </table>
            <table  align="center" cellspacing="1" cellpadding="8"b border="1" class="innerTable1" width="60%">
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="toaldisability" value="false">

                            <center><font color="red" size="2"><b>No Data</b></font></center>
                        </logic:equal>
                        <logic:equal name="calBean" scope="request" property="oh" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=20&doctor=doctor"><b>Total Calculation for Locomotor Impairment</b></html:link>
                            <%}else{%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=20"><b>Total Calculation for Locomotor Impairment</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>





                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohUe" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=1&doctor=doctor"><b>UpperExtremity</b></html:link>
                            <%}else{%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=1"><b>UpperExtremity</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>

                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohLe" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=2&doctor=doctor"><b>LowerExtremity</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=2"><b>LowerExtremity</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>

                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohAmp" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=3&doctor=doctor"><b>Amputation</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=3"><b>Amputation</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>

                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohTrans" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=4&doctor=doctor"><b>Transverse</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=4"><b>Transverse</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>

                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohTrunk" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=5&doctor=doctor"><b>Trunk</b></html:link>
                             <%}else{%>
                               <html:link href="showCalc.do?typeofDisabilityFlag=5"><b>Trunk</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohPhysical" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=6&doctor=doctor"><b>Physical</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=6"><b>Physical</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohCardio" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=7&doctor=doctor"><b>Cardiopulmonary</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=7"><b>Cardiopulmonary</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>


                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="ohDwarf" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=8&doctor=doctor"><b>Dwarfism</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=8"><b>Dwarfism</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="hi" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=19&doctor=doctor"><b>Hearing</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=19"><b>Hearing</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelblue">
                        <logic:equal name="calBean" scope="request" property="vi" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=10&doctor=doctor"><b>Visual</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=10"><b>Visual</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mr" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=9&doctor=doctor"><b>Mental Retardation</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=9"><b>Mental Retardation</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrDst" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=11&doctor=doctor"><b>Developmental Screening Test</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=11"><b>Developmental Screening Test</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrVsms" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=12&doctor=doctor"><b>Vineland Social Maturity Scale</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=12"><b>Vineland Social Maturity Scale</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrBkt" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=13&doctor=doctor"><b>Binet Kamat Test of Intelligence</b></html:link>
                             <%}else{%>
                              <html:link href="showCalc.do?typeofDisabilityFlag=13"><b>Binet Kamat Test of Intelligence</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrMisic" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=14&doctor=doctor"><b>Malins Intelligence Scale for Indian Children</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=14"><b>Malins Intelligence Scale for Indian Children</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrSfb" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=15&doctor=doctor"><b>Seguin Form Board</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=15"><b>Seguin Form Board</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrPat" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=16&doctor=doctor"><b>Alexander's Pass Along Test</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=16"><b>Alexander's Pass Along Test</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mrBbpti" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=17&doctor=doctor"><b>Bhatias Battery of Intelligence Tests</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=17"><b>Bhatias Battery of Intelligence Tests</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="mi" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=18&doctor=doctor"><b>MentalIllness</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=18"><b>MentalIllness</b></html:link>
                            <%}%>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue">
                        <logic:equal name="calBean" scope="request" property="toaldisability" value="true">
                            <%if(doctor!=null){%>
                            <html:link href="showCalc.do?typeofDisabilityFlag=21&doctor=doctor"><b>Total Disability Calculation</b></html:link>
                             <%}else{%>
                             <html:link href="showCalc.do?typeofDisabilityFlag=21"><b>Total Disability Calculation</b></html:link>
                            <%}%>
                        </logic:equal>



                    </td>
                </tr>

            </table><br>


            <table align="center">

                <logic:present name="backbuttonflag" scope="session">
                    <logic:equal name="backbuttonflag" scope="session" value="show">
                        <tr><td><html:button property=""  value="Back" onclick="goBack()" styleClass="button" /></td></tr>
                    </logic:equal>
                </logic:present>



            </table>






        </form>
    </body>
</html:html>