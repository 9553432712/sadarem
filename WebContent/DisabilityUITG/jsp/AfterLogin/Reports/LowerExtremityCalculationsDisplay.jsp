<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            String personcode = (String) session.getAttribute("personcode");
            String name = (String) session.getAttribute("Name");
%>
<html:html>
    <head>

        <script language="javascript" >
            function goBack()
            {
                document.forms[0].action="showCalculationsAction.do";
                document.forms[0].submit();
            }
        </script>
    </head>

    <body><br>

        <form>
            <logic:notEmpty name="lowerExtremityBean" scope="request">

                <%boolean valEqualsOne = false;%>

                <TABLE border="0" align="center" cellspacing="0" cellpadding="0" class="table" width="85%">
                    <tr><td colspan="3"  align="right" class="labelBlue">ID No.&nbsp;<%=personcode%></td></tr>
                    <tr><td colspan="3"  align="right" class="labelBlue">Name.&nbsp;<%=name%></td></tr>

                    <tr>
                        <th align="center" class="heading1">1.&nbsp;&nbsp;LOWER EXTREMITY</th>
                    </tr>


                </TABLE>
                <logic:equal name="lowerExtremityBean" property="checkforrom" value="true">
                    <table border="1" align="center" cellspacing="0" cellpadding="0" class="table" width="85%">
                        <tr>
                            <th align="left" colspan="9" class="labelBlue">2.1 Lower Extremity :&nbsp;&nbsp;Mobility Component (Total value=90%)</th>
                        </tr>
                        <tr>
                            <th align="left" colspan="9" class="labelBlue">2.1.1 Active Range of Motion (ROM) ARC.&nbsp;&nbsp;(in Degrees)</th>
                        </tr>

                        <tr>
                            <td rowspan=2 class="labelBlue"> ROM</td><td rowspan=2 class="labelBlue">Components</td><td rowspan=2 class="labelBlue">Normal Value (Degree)</td>
                            <td colspan=4 class="labelBlue">Active ROM</td><td colspan="2" class="labelBlue">Calculation</td></tr>
                        <tr>
                            <td class="labelBlue">Right</td><td class="labelBlue">Left</td><td class="labelBlue">Right Calculation</td><td class="labelBlue">Left Calculation</td><td class="labelBlue">Right Calculation</td><td class="labelBlue">Left  Calculation</td>
                        </tr>

                        <logic:equal name="lowerExtremityBean" property="romhipright" value="true"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="lowerExtremityBean" property="romhipleft" value="true"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td rowspan="3" class="labelBlue">1.Hip Joint</td>
                            <logic:notEqual name="lowerExtremityBean" property="romhipflexionextensionright" value="140"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="romhipflexionextensionleft" value="140"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>
                            <td class="label">A. &nbsp;Flexion-Extension arc</td>
                            <td align="center" class="label">0-140</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhipflexionextensionright" value="140" name="lowerExtremityBean"><bean:write property="romhipflexionextensionright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhipflexionextensionleft" value="140" name="lowerExtremityBean"><bean:write property="romhipflexionextensionleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhflexrightcalculations" value="" name="lowerExtremityBean"><bean:write property="rhflexrightcalculations" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhflexleftcalculations" value="" name="lowerExtremityBean"><bean:write property="rhflexleftcalculations" name="lowerExtremityBean"  /></td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>

                            <td align="left" rowspan="3" class="label"> <logic:notEqual scope="request" property="romhiptotalrightcalculation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romhiptotalrightcalculation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                            <td align="left" rowspan="3" class="label"> <logic:notEqual scope="request" property="romhiptotalleftcalculation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romhiptotalleftcalculation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>

                        <logic:notEqual name="lowerExtremityBean" property="romhipabductionadductionright" value="90"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romhipabductionadductionleft" value="90"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">B. &nbsp;Abduction-Adduction arc</td>
                            <td align="center" class="label">0-90</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhipabductionadductionright" value="90" name="lowerExtremityBean"><bean:write property="romhipabductionadductionright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhipabductionadductionleft" value="90" name="lowerExtremityBean"><bean:write property="romhipabductionadductionleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhabductionadductioncalculation" value="" name="lowerExtremityBean"><bean:write property="rhabductionadductioncalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhabductionadductionleftcalculation" value="" name="lowerExtremityBean"><bean:write property="rhabductionadductionleftcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                        </tr>
                        <% }
                      valEqualsOne = false;%>
                        <logic:notEqual name="lowerExtremityBean" property="romhiprotationright" value="90"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romhiprotationleft" value="90"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">C.&nbsp;Rotation arc</td>
                            <td align="center" class="label">0-90</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhiprotationright" value="90" name="lowerExtremityBean"><bean:write property="romhiprotationright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romhiprotationleft" value="90" name="lowerExtremityBean"><bean:write property="romhiprotationleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhrotationculations" value="" name="lowerExtremityBean"><bean:write property="rhrotationculations" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="rhrotationleftculations" value="" name="lowerExtremityBean"><bean:write property="rhrotationleftculations" name="lowerExtremityBean"  /></td></logic:notEqual>

                        </tr>

                        <% }
                      valEqualsOne = false;%>

                        <% }
                        valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="romkneeflexionextensionright" value="125"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romkneeflexionextensionleft" value="125"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.Knee Joint</td>
                            <td class="label">A. &nbsp;Flexion-Extension arc</td>
                            <td align="center" class="label">0-140</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romkneeflexionextensionright" value="125" name="lowerExtremityBean"><bean:write property="romkneeflexionextensionright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romkneeflexionextensionleft" value="125" name="lowerExtremityBean"><bean:write property="romkneeflexionextensionleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romkneerightcalulation" value="" name="lowerExtremityBean"><bean:write property="romkneerightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romkneeleftcalculation" value="" name="lowerExtremityBean"><bean:write property="romkneeleftcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <% }
                            valEqualsOne = false;%>


                        </tr>

                        <logic:notEqual name="lowerExtremityBean" property="romankletotalrightcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romankletotalleftcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td rowspan="2" class="labelBlue">3.Ankle & Foot Joint</td>
                            <logic:notEqual name="lowerExtremityBean" property="romankledorsiflexionpalmarflexionright" value="70"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="romankledorsiflexionpalmarflexionleft" value="70"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>
                            <td class="label">A.&nbsp; Dorsiflexion-palntar flexion arc </td>
                            <td align="center" class="label">0-70</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankledorsiflexionpalmarflexionright" value="70" name="lowerExtremityBean"><bean:write property="romankledorsiflexionpalmarflexionright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankledorsiflexionpalmarflexionleft" value="70" name="lowerExtremityBean"><bean:write property="romankledorsiflexionpalmarflexionleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankledorflexionrightcalulation" value="" name="lowerExtremityBean"><bean:write property="romankledorflexionrightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankledorflexionleftcalulation" value="" name="lowerExtremityBean"><bean:write property="romankledorflexionleftcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>

                            <td align="left" rowspan="2" class="label"> <logic:notEqual scope="request" property="romankletotalrightcalulation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romankletotalrightcalulation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                            <td align="left" rowspan="2" class="label"> <logic:notEqual scope="request" property="romankletotalleftcalulation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romankletotalleftcalulation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>
                        <logic:notEqual name="lowerExtremityBean" property="romankleinversioneversionright" value="60"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romankleinversioneversionleft" value="60"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">B.&nbsp; Inversion- Eversion arc</td>
                            <td align="center" class="label">0-60</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankleinversioneversionright" value="60" name="lowerExtremityBean"><bean:write property="romankleinversioneversionright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankleinversioneversionleft" value="60" name="lowerExtremityBean"><bean:write property="romankleinversioneversionleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankleinversionrightcalulation" value="" name="lowerExtremityBean"><bean:write property="romankleinversionrightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="romankleinversionleftcalulation" value="" name="lowerExtremityBean"><bean:write property="romankleinversionleftcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>


                            <% }
                        valEqualsOne = false;%>

                        </tr>
                        <logic:notEqual name="lowerExtremityBean" property="romtotalrightcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="romtotalleftcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">Total ROM</td>
                            <td colspan="4" class="label">Total Rom Right=
                                <logic:notEqual scope="request" property="romtotalrightcalulation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romtotalrightcalulation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                            <td colspan="4" class="label">Total Rom Left =
                                <logic:notEqual scope="request" property="romtotalleftcalulation" value="" name="lowerExtremityBean"><pre style="font-family:verdana"><bean:write property="romtotalleftcalulation" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                    </table>

                </logic:equal>



                <logic:equal name="lowerExtremityBean" property="checkmstotal" value="true">

                    <TABLE border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="left" class="labelBlue">2.1.2 &nbsp;&nbsp;Muscle Strength.(Normal value = Grade 5 )</th>
                        </tr>
                    </TABLE>
                    <table border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <td rowspan=2 class="labelBlue">Joint</td><td rowspan=2 class="labelBlue">Components</td>
                            <td rowspan=2 class="labelBlue">Normal Muscle Grade</td>
                            <td colspan=4 class="labelBlue">Active Muscle Grade</td><td colspan="2" class="labelBlue">Calculation</td>
                        </tr>
                        <tr>
                            <td class="labelBlue">Right</td><td class="labelBlue">Left</td><td class="labelBlue">Right Calculation</td><td class="labelBlue">Left Calculation</td><td class="labelBlue">Right Calculation</td><td class="labelBlue">Left  Calculation</td>
                        </tr>

                        <logic:notEqual name="lowerExtremityBean" property="mshiptotalrightcalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mshiptotalleftcalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td rowspan="5" class="labelBlue">1.Hip<br> Joint</td>
                                <logic:notEqual name="lowerExtremityBean" property="mshipflexormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                                <logic:notEqual name="lowerExtremityBean" property="mshipflexormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                                <%  if (valEqualsOne) {%>
                            <td class="label">A. &nbsp;Flexor-Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipflexormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mshipflexormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipflexormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mshipflexormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshflexormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshflexormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshflexormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshflexormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>




                            <td align="left" rowspan="5" class="label"> <logic:notEqual scope="request" property="mshiptotalrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshiptotalrightcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" rowspan="5" class="label"> <logic:notEqual scope="request" property="mshiptotalleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshiptotalleftcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>


                            <logic:notEqual name="lowerExtremityBean" property="mshipextensormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="mshipextensormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">B.&nbsp;Extensor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipextensormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mshipextensormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipextensormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mshipextensormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshextensormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshextensormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshextensormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshextensormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                        </tr>
                        <% }
                      valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="mshiprotatormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mshiprotatormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">C.&nbsp;Rotator Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshiprotatormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mshiprotatormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshiprotatormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mshiprotatormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshrotatormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshrotatormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshrotatormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshrotatormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                        </tr>

                        <% }
                      valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="mshipabductormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mshipabductormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">D.&nbsp;Abductor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipabductormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mshipabductormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipabductormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mshipabductormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshabductormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshabductormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshabductormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshabductormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                        </tr>

                        <% }
                      valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="mshipadductormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mshipadductormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">E.&nbsp;Adductor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipadductormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mshipadductormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshipadductormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mshipadductormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshadductormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mshadductormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mshadductormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mshadductormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                        </tr>

                        <% }
                      valEqualsOne = false;%>

                        </tr>
                        <% }
                        valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="mskneerightcalulation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mskneeleftcalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td rowspan="2" class="labelBlue">2. Knee <br>Joint</td>
                                <logic:notEqual name="lowerExtremityBean" property="mskneeflexormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                                <logic:notEqual name="lowerExtremityBean" property="mskneeflexormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                                <%  if (valEqualsOne) {%>
                            <td class="label">A. &nbsp;Flexor-Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskneeflexormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mskneeflexormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskneeflexormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mskneeflexormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskflexormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mskflexormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskflexormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mskflexormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>




                            <td align="left" rowspan="2" class="label"> <logic:notEqual scope="request" property="mskneerightcalulation" value="" name="lowerExtremityBean"><bean:write property="mskneerightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" rowspan="2" class="label"> <logic:notEqual scope="request" property="mskneeleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mskneeleftcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>


                            <logic:notEqual name="lowerExtremityBean" property="mskneeextensormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="mskneeextensormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">B.&nbsp;Extensor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskneeextensormusclesright" value="5" name="lowerExtremityBean"><bean:write property="mskneeextensormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskneeextensormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="mskneeextensormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskextensormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="mskextensormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="mskextensormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="mskextensormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                        </tr>
                        <% }
                      valEqualsOne = false;%>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="msankletotalrightcalulation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="msankletotalleftcalulation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td rowspan="4" class="labelBlue">3. Ankle & Foot joint</td>
                            <logic:notEqual name="lowerExtremityBean" property="msankleplanterflexormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="msankleplanterflexormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>
                            <td class="label">A.&nbsp;Planterflexor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleplanterflexormusclesright" value="5" name="lowerExtremityBean"><bean:write property="msankleplanterflexormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleplanterflexormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="msankleplanterflexormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msaplanterflexormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="msaplanterflexormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msaplanterflexormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="msaplanterflexormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                            <% }
                      valEqualsOne = false;%>




                            <td align="left" rowspan="4" class="label"> <logic:notEqual scope="request" property="msankletotalrightcalulation" value="" name="lowerExtremityBean"><bean:write property="msankletotalrightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" rowspan="4" class="label"> <logic:notEqual scope="request" property="msankletotalleftcalulation" value="" name="lowerExtremityBean"><bean:write property="msankletotalleftcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>


                            <logic:notEqual name="lowerExtremityBean" property="msankledorsiflexormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="msankledorsiflexormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">B.&nbsp;Dorsiflexor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankledorsiflexormusclesright" value="5" name="lowerExtremityBean"><bean:write property="msankledorsiflexormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankledorsiflexormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="msankledorsiflexormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msadorsiflexormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="msadorsiflexormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msadorsiflexormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="msadorsiflexormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>

                        </tr>
                        <% }
                      valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="msankleinvertormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="msankleinvertormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">C.&nbsp;Invertor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleinvertormusclesright" value="5" name="lowerExtremityBean"><bean:write property="msankleinvertormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleinvertormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="msankleinvertormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msainvertormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="msainvertormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msainvertormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="msainvertormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                        </tr>

                        <% }
                      valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="msankleevertormusclesright" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="msankleevertormusclesleft" value="5"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>

                        <tr>

                            <td class="label">D.&nbsp;Evertor Muscles</td>
                            <td align="center" class="label">0-5</td>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleevertormusclesright" value="5" name="lowerExtremityBean"><bean:write property="msankleevertormusclesright" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msankleevertormusclesleft" value="5" name="lowerExtremityBean"><bean:write property="msankleevertormusclesleft" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msaevertormusclesrightcalculation" value="" name="lowerExtremityBean"><bean:write property="msaevertormusclesrightcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="msaevertormusclesleftcalculation" value="" name="lowerExtremityBean"><bean:write property="msaevertormusclesleftcalculation" name="lowerExtremityBean"  />%</td></logic:notEqual>
                        </tr>

                        <% }
                      valEqualsOne = false;%>




                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <tr>
                            <td colspan="9" class="labelblue">percentage calculation for Each Grade value :-</td>
                        </tr>
                        <tr>
                            <td class="label">0 - 100% </td><td class="label">1 - 80% </td><td colspan="2" class="label">2 - 60% </td><td colspan="2" class="label">3 - 40% </td> <td colspan="2" class="label">4 - 20% </td>  <td class="label">5 - 0% </td>
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="mstotalrightcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <logic:notEqual name="lowerExtremityBean" property="mstotalleftcalulation" value=""><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">Total MS</td>
                            <td colspan="4" class="label">Total MuscleStrength Right=

                                <logic:notEqual scope="request" property="mstotalrightcalulation" value="" name="lowerExtremityBean"><bean:write property="mstotalrightcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                            <td colspan="4" class="label">Total MuscleStrength Left =
                                <logic:notEqual scope="request" property="mstotalleftcalulation" value="" name="lowerExtremityBean"><bean:write property="mstotalleftcalulation" name="lowerExtremityBean"  /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>


                        <tr>
                            <td rowspan="2" class="labelBlue">MobilityComponent(Right,Left) Total</td>

                            <logic:notEqual name="lowerExtremityBean" property="totalrightmobilitycomponent" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                            <logic:notEqual name="lowerExtremityBean" property="totalleftmobilitycomponent" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                            <%  if (valEqualsOne) {%>

                            <td colspan="9" class="label">Total MobilityComponent Right=

                                <logic:notEqual scope="request" property="totalrightmobilitycomponent" value="" name="lowerExtremityBean"><pre style="font-family:verdana" ><bean:write property="totalrightmobilitycomponent" name="lowerExtremityBean"  /></pre></td></logic:notEqual>


                        <tr>

                            <td colspan="9" class="label">Total MobilityComponent Left =
                                <logic:notEqual scope="request" property="totalleftmobilitycomponent" value="" name="lowerExtremityBean"><pre style="font-family:verdana" ><bean:write property="totalleftmobilitycomponent" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        </tr>
                        <logic:notEqual name="lowerExtremityBean" property="totalmobilitycomponent" value="0"><%  valEqualsOne = true;%></logic:notEqual>

                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">Total MobilityComponent</td>
                            <td colspan="8" class="label">

                                <logic:notEqual scope="request" property="totalmobilitycomponent" value="" name="lowerExtremityBean"><pre style="font-family:verdana" ><bean:write property="totalmobilitycomponent" name="lowerExtremityBean"  /></pre></td></logic:notEqual>

                        </tr>
                        <% }
                        valEqualsOne = false;%>

                    </table>
                </logic:equal>
                <logic:notEqual name="lowerExtremityBean" property="stability" value="0">

                    <TABLE border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="left" class="labelblue">2.2 Lower Extremity :&nbsp;&nbsp;Stability Component (Total value=90%)</th>
                        </tr>
                    </TABLE>
                    <table border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <td rowspan=2 class="labelBlue">&nbsp;Components</td>
                            <td colspan=4 align="center" class="labelBlue">Performing Condition</td></tr>
                        <tr>

                            <td align="center" class="labelBlue"><br>Condition</td>
                            <td class="labelblue">Calculation percentage</td>
                        </tr>



                        <logic:notEqual name="lowerExtremityBean" property="working_on_plane" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.1&nbsp;Walking on plane surface</td>

                            <td align="left" class="label"> <logic:equal scope="request" property="working_on_plane" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="working_on_plane" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="working_on_plane" value="" name="lowerExtremityBean"><bean:write property="working_on_plane" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="working_on_slope" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.2&nbsp;Walking on slope</td>

                            <td align="left" class="label"> <logic:equal scope="request" property="working_on_slope" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="working_on_slope" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="working_on_slope" value="" name="lowerExtremityBean"><bean:write property="working_on_slope" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="working_on_stairs" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.3&nbsp;Climbing stairs </td>

                            <td align="left" class="label"> <logic:equal scope="request" property="working_on_stairs" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="working_on_stairs" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="working_on_stairs" value="" name="lowerExtremityBean"><bean:write property="working_on_stairs" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>




                        <logic:notEqual name="lowerExtremityBean" property="standing_on_both_legs" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.4&nbsp;Standing on both legs </td>

                            <td align="left" class="label"> <logic:equal scope="request" property="standing_on_both_legs" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="standing_on_both_legs" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="standing_on_both_legs" value="" name="lowerExtremityBean"><bean:write property="standing_on_both_legs" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <logic:notEqual name="lowerExtremityBean" property="standing_on_effected" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.5&nbsp;Standing on affected leg </td>

                            <td align="left" class="label"> <logic:equal scope="request" property="standing_on_effected" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="standing_on_effected" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="standing_on_effected" value="" name="lowerExtremityBean"><bean:write property="standing_on_effected" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>




                        <logic:notEqual name="lowerExtremityBean" property="squatting_on_floor" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.6&nbsp;Squatting on floor</td>

                            <td align="left" class="label"> <logic:equal scope="request" property="squatting_on_floor" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="squatting_on_floor" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="squatting_on_floor" value="" name="lowerExtremityBean"><bean:write property="squatting_on_floor" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>
                        <logic:notEqual name="lowerExtremityBean" property="sitting_cross_leg" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.7&nbsp;Sitting cross leg </td>

                            <td align="left" class="label"> <logic:equal scope="request" property="sitting_cross_leg" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="sitting_cross_leg" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="sitting_cross_leg" value="" name="lowerExtremityBean"><bean:write property="sitting_cross_leg" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>
                        <logic:notEqual name="lowerExtremityBean" property="kneeling" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.8&nbsp;Kneeling</td>

                            <td align="left" class="label"> <logic:equal scope="request" property="kneeling" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="kneeling" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="kneeling" value="" name="lowerExtremityBean"><bean:write property="kneeling" name="lowerExtremityBean" /></td></logic:notEqual>

                        </tr>
                        <% }
                        valEqualsOne = false;%>


                        <logic:notEqual name="lowerExtremityBean" property="taking_turns" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.2.9&nbsp;Taking turns </td>

                            <td align="left" class="label"> <logic:equal scope="request" property="taking_turns" value="5" name="lowerExtremityBean">Performing with difficulty</logic:equal>
                                <logic:equal scope="request" property="taking_turns" value="10" name="lowerExtremityBean">Cannot Performing</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="taking_turns" value="" name="lowerExtremityBean"><bean:write property="taking_turns" name="lowerExtremityBean" /></td></logic:notEqual>
                        </tr>
                        <% }
                        valEqualsOne = false;%>

                        <tr>
                            <td colspan="9" class="labelBlue">percentage calculation for Each Condition :-</td>
                        </tr>
                        <tr>
                            <td class="label">Perform without any difficulty - 0% </td><td class="label">Perform with difficulty  - 5% </td><td class="label">Cannot perform - 10% </td>
                        </tr>
                        <logic:notEqual name="lowerExtremityBean" property="stabilitycalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>

                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td colspan="3" class="label">Total Stability value Calculation :-</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="label">

                                <logic:notEqual scope="request" property="stabilitycalculation" value="" name="lowerExtremityBean"><bean:write property="stabilitycalculation" name="lowerExtremityBean"  /></td></logic:notEqual>

                        </tr>
                        <% }
                        valEqualsOne = false;%>

                    </table>
                </logic:notEqual>







                <logic:notEqual name="lowerExtremityBean" property="extra" value="0">



                    <TABLE border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="left" class="labelBlue">2.3 &nbsp;&nbsp;Presence of complications</th>
                        </tr>
                    </TABLE>

                    <TABLE border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <td rowspan=2 class="labelBlue">&nbsp;complications</td>
                            <td colspan=4 align="center" class="labelBlue">Performing Condition</td></tr>
                        <tr>

                            <td align="center" class="labelBlue">Condition</td>
                            <td class="labelBlue">Calculation percentage</td>
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="deformity" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">1.Deformity</td>
                            <td align="left" class="label"> <logic:equal scope="request" property="deformity" value="3" name="lowerExtremityBean">In functional position</logic:equal>
                                <logic:equal scope="request" property="deformity" value="6" name="lowerExtremityBean">In non-functional position</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="deformity" value="" name="lowerExtremityBean"><bean:write property="deformity" name="lowerExtremityBean" /></td></logic:notEqual>
                            <% }
                            valEqualsOne = false;%>
                        </tr>
                        <logic:notEqual name="lowerExtremityBean" property="pain" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">2.Pain</td>
                            <td align="left" class="label">
                                <logic:equal scope="request" property="pain" value="9" name="lowerExtremityBean">Severe (grossly interfering with function)</logic:equal>
                                <logic:equal scope="request" property="pain" value="6" name="lowerExtremityBean">Moderate (moderately interfering with function)</logic:equal>
                                <logic:equal scope="request" property="deformity" value="3" name="lowerExtremityBean">Mild (mildly interfering with function)</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="pain" value="" name="lowerExtremityBean"><bean:write property="pain" name="lowerExtremityBean" /></td></logic:notEqual>
                            <% }
                            valEqualsOne = false;%>
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="loss_of_Function" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">3.Loss of Function</td>
                            <td align="left" class="label"> <logic:equal scope="request" property="loss_of_Function" value="9" name="lowerExtremityBean">Complete loss</logic:equal>
                                <logic:equal scope="request" property="loss_of_Function" value="6" name="lowerExtremityBean">Partial loss</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="loss_of_Function" value="" name="lowerExtremityBean"><bean:write property="loss_of_Function" name="lowerExtremityBean" /></td></logic:notEqual>
                            <% }
                            valEqualsOne = false;%>
                        </tr>

                        <logic:notEqual name="lowerExtremityBean" property="loss_of_Function" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td class="labelBlue">4.Complications</td>
                            <td align="left" class="label"> <logic:equal scope="request" property="complications" value="3" name="lowerExtremityBean">Superficial complications</logic:equal>
                                <logic:equal scope="request" property="complications" value="5" name="lowerExtremityBean">Deep complications</td></logic:equal>
                            <td align="left" class="label"> <logic:notEqual scope="request" property="complications" value="" name="lowerExtremityBean"><bean:write property="complications" name="lowerExtremityBean" /></td></logic:notEqual>
                            <% }
                            valEqualsOne = false;%>
                        </tr>
                        <tr>
                            <td colspan="9" class="labelBlue">percentage calculation for Each Condition :-</td>
                        </tr>
                        <tr>
                            <td class="label">In functional position - 3% </td><td class="label">In non-functional position  - 6% </td><td class="label">Severe - 9% </td>
                        </tr>
                        <tr>
                            <td class="label">Moderate - 6% </td><td class="label">Mild  - 3% </td><td class="label">Complete loss - 9% </td>
                        </tr>
                        <tr >
                            <td class="label">Partial loss - 6% </td><td class="label">Superficial complications  - 3% </td><td class="label">Deep complications - 5% </td>
                        </tr>



                        <logic:notEqual name="lowerExtremityBean" property="extracalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td colspan="3" class="labelBlue">Total Extra(Presence of complications) value Calculation :-</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="label">
                                Extra = <logic:notEqual scope="request" property="extracalculation" value="" name="lowerExtremityBean"><bean:write property="extracalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                        </tr>
                        <% }
                  valEqualsOne = false;%>
                    </TABLE>

                </logic:notEqual>


                <logic:notEqual name="lowerExtremityBean" property="shortening" value="0">

                    <table border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="left" class="labelBlue">2.4&nbsp;&nbsp;Shortening of Limb</th>
                        </tr>

                        <logic:notEqual name="lowerExtremityBean" property="shortening" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td align="center" class="label">Shortening  =
                                <logic:notEqual scope="request" property="shortening" value="" name="lowerExtremityBean"><bean:write property="shortening" name="lowerExtremityBean"  /></td></logic:notEqual>
                        </tr>
                        <% }
                  valEqualsOne = false;%>

                        <tr>
                            <td align="left" class="label">Shortening Calculation :-
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="shortningcalculation" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td align="center" class="label">
                                <logic:notEqual scope="request" property="shortningcalculation" value="" name="lowerExtremityBean"><bean:write property="shortningcalculation" name="lowerExtremityBean"  /></td></logic:notEqual>
                        </tr>
                        <% }
                  valEqualsOne = false;%>

                    </table>

                </logic:notEqual>
                <logic:notEqual name="lowerExtremityBean" property="lowerextremitywithoutextra" value="0">
                    <table border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="left" class="labelBlue">LowerExtremity Without Extra</th>
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="lowerextremitywithoutextra" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td align="left" class="label">
                                <logic:notEqual scope="request" property="lowerextremitywithoutextra" value="" name="lowerExtremityBean"><pre style="font-family:verdana" ><bean:write property="lowerextremitywithoutextra" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>
                        <% }
                  valEqualsOne = false;%>

                    </table>
                </logic:notEqual>

                <logic:notEqual name="lowerExtremityBean" property="lowerextremity" value="0">
                    <table border="1" align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
                        <tr>
                            <th align="center" class="labelBlue">Total LowerExtremity calculation</th>
                        </tr>


                        <logic:notEqual name="lowerExtremityBean" property="lowerextremityfinalvalue" value="0"><%  valEqualsOne = true;%></logic:notEqual>
                        <%  if (valEqualsOne) {%>
                        <tr>
                            <td align="left" class="label">
                                <logic:notEqual scope="request" property="lowerextremityfinalvalue" value="" name="lowerExtremityBean"><pre style="font-family:verdana" ><bean:write property="lowerextremityfinalvalue" name="lowerExtremityBean"  /></pre></td></logic:notEqual>
                        </tr>
                        <% }
                  valEqualsOne = false;%>

                        <tr>
                            <th align="center" class="label"><logic:greaterThan property="lowerextremitytotal" name="lowerExtremityBean" value="100"> percentage canot be above 100, hence the final percentage is :-</logic:greaterThan></th>
                        </tr>




                        <tr>
                            <th align="center" class="labelBlue">LowerExtremity value =<bean:write property="lowerextremity" name="lowerExtremityBean"  />%</th>
                        </tr>


                    </table>
                </logic:notEqual>
            </logic:notEmpty>
            <BR><BR><BR>
            <table align="center">
                <tr>
                    <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>  </tr>
                <tr></tr><tr></tr>
                <tr><a href="showCalc.do?typeofDisabilityFlag=2&flagPrint=print" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

            </table>
        </form>

    </body>
</html:html>

