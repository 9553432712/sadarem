<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
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
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    <body><br>
    <form>
     <logic:present name="pIDuetoNeurologicalConditionsBean" scope="request">
      <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
        <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="85%">
            <tr>
                <th align="center" class="heading">Reference for Calculation</th>
            </tr>
            <tr><td colspan="3"  align="right" class="label">ID No: &nbsp;<font color="blue"><%=personcode%></font></td></tr>
            <tr><td colspan="3"  align="right" class="label">Name: &nbsp;<font color="blue"><%=name%></font></td></tr>

        </table>
        <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">


            <!--Displays DominantUpperExtrimity-->

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="dominantupperextremity" value="0">
                <tr>
                    <td width="5%" class="labelBlue">6.1</td>
                <td class="label"> Involvement of Dominant Upper Extremity </td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="dominantupperextremity"/></td>
                </tr>
            </logic:notEqual>

            <!--Checks for sum of LossOfSensation-->

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sumoflossofsensation" value="0">
                <tr>
                <td width="5%" class="labelBlue">6.2</td>
                <td colspan="2" class="label">Loss of Sensation</td>
                <!--Displays LossOfSensationUpper-->
                </tr>

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper" value="0">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label"><ul>A. Loss of Sensation Upper</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper"/></td>
                    </tr>
                </logic:notEqual>

                <!--Displays LossOfSensationLower-->

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower" value="0">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label"><ul> B. Loss of Sensation Lower</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower"/></td>
                    </tr>
                </logic:notEqual>


                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="lossofsensationflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align=center class="labelBlue">Total</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper"/>+<bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower"/>=<bean:write name="pIDuetoNeurologicalConditionsBean" property="sumoflossofsensation"/></td>
                </tr>
                </logic:equal>

             </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus" value="0">

                <tr>

                 <td class="labelBlue">6.3</td>
                 <td class="label">Altered sensorium (Neurological Status)</td>
                 <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus"/></td>

               </tr>

            </logic:notEqual>



            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value1flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td class="label">After substituting <bean:write name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus"/> into formula, the result will be</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value1"/></td>
                </tr>
            </logic:equal>



            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="0">
                <tr>
                <td class="labelBlue">6.4</td>
                <td colspan="2" class="label">Intellectual Impairment</td>
                </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="25">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>Boarderline</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="50">
                <tr>
                <td>&nbsp;</td>
                <td> class="label"<ul>Mild</td>
                <td class="labelBlue"><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="75">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>Moderate</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="90">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>Severe</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="100">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>Profound</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value2flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center" class="labelBlue">Total</td>
                 <td class="labelBlue"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value2"/></td>
                 </tr>
            </logic:equal>

            </logic:notEqual>

            <tr>
            <td class="labelBlue">6.5</td>
            <td colspan="2" class="label">Speech Defect</td>
            </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="0">

                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>A. Mild Dysarthria</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>

            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="25">

                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>B.Moderate Dysarthria</td>
                 <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>

            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="50">

                <tr>
                <td >&nbsp;</td>
                <td class="label"><ul> C. Severe Dysarthria</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>
             </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value3flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center" class="labelBlue">Total</td>
                 <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value3"/></td>
                 </tr>
            </logic:equal>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorcranialnerveflag" value="true">
                <tr>
                 <td class="labelBlue">6.6.1</td>
                 <td colspan="2" class="labelBlue"> Motor Cranial Nerve</td>
                </tr>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6a" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>A. Oculomotor Nerve</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6a"/></td>
                 </tr>
            </logic:notEqual>



            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6b" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>B. Trochlear Nerve</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6b"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6c" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>C. Abducence Nerve</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6c"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6d" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>D. Facial Nerve</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6d"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6e" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>E. Accessory Nerve</td>
                <td width="20%" class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6e"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6f" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>F. Hypoglossal Nerve</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6f"/></td>
                </tr>
            </logic:notEqual>

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6g" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>G. Trigeminal Nerve</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6g"/></td>
                </tr>
            </logic:notEqual>

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6h" value="0">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>H. Vagus Nerve</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6h"/></td>
                </tr>
            </logic:notEqual>





            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motornervetotalflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center" class="label">Motor Cranial Nerve Total </td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorstring"/></td>
                </tr>
            </logic:equal>


             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motornerveflagforvalue" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label">Since Motor Cranial Nerve Total value is greater than 100, Motor Cranial Nerve Total value is taken as 100</td>
                 <td>&nbsp;</td>
                 </tr>
              </logic:equal>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value4flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center" class="labelBlue">Total</td>
                <td width="30%" class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value4"/></td>
                </tr>
            </logic:equal>

            </logic:equal>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorycranialnerveflag" value="true">
                <tr>
                <td class="labelBlue">6.6.2</td>
                <td colspan="2" class="label"> Sensory Cranial Nerve</td>
                </tr>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sca" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>A. Olfactory Nerve</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sca"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scb" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>B. Optic Nerve</td>
                 <td class="label" ><bean:write name="pIDuetoNeurologicalConditionsBean" property="scb"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scc" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>C. Vestibulocochlear Nerve</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="scc"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scd" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>D. Glossopharyngeal Nerve</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="scd"/></td>
                 </tr>
            </logic:notEqual>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorynervetotalflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center" class="labelBlue">Sensory Nerve Total</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorystring"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value5flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center" class="labelBlue">Total</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value5"/></td>
                </tr>
            </logic:equal>

             </logic:equal>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="0">
                 <tr>
                 <td class="labelBlue">6.7</td>
                 <td colspan="2" class="label">Motor system Disability</td>
                 </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="25">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>A. Mild</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="50">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>B.  Moderate</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="75">
                <tr>
                <td>&nbsp;</td>
                <td class="label"><ul>C. Severe</td>
                <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value6flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center" class="labelBlue">Total</td>
                <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value6"/></td>
                </tr>
            </logic:equal>


            </logic:notEqual>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystemflag" value="true">
                <tr>
                <td class="labelBlue">6.8</td>
                <td colspan="2" class="label">Sensory system Disability</td>
                </tr>



             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2" class="label"><ul>A. Anesthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2" class="label"><ul>B. Hypoesthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2" class="label"><ul>C. Parasthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystemtotalflag" value="true">
                    <tr>
                    <td>&nbsp;</td>
                    <td align="center" class="labelBlue">Sensory System Disability Total </td>
                    <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystemstring"/></td>
                    </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value7flag" value="true">
                    <tr>
                    <td>&nbsp;</td>
                    <td align="center" class="labelBlue">Total</td>
                    <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value7"/></td>
                    </tr>
            </logic:equal>

            </logic:equal>


              <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="0">
                  <tr>
                  <td class="labelBlue">6.9</td>
                  <td colspan="2" class="label">Bladder disability Due to neurological involvement:</td>
                  </tr>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="25">
                  <tr>
                  <td>&nbsp;</td>
                  <td class="label"><ul>A. Mild (Hesitancy and Frequency)</td>
                  <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                  </tr>
               </logic:equal>


              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="50">
                  <tr>
                  <td>&nbsp;</td>
                  <td class="label"><ul>B. Moderate(precipitancy)</td>
                  <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                  </tr>
               </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="75">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>C. Severe(Occasional but recurrent incontinence)</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                 </tr>
               </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="100">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>D. Very Severe(Retension/total incontinence)</td>
                 <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                 </tr>
               </logic:equal>

               <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value8flag" value="true">
                   <tr>
                   <td>&nbsp;</td>
                   <td align="center" class="labelBlue">Total</td>
                   <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value8"/></td>
                   </tr>
            </logic:equal>

             </logic:notEqual>

             <tr>
              <td class="labelBlue">6.10</td>
              <td colspan="2" class="label"> Post head injury fits & epileptic Convulsions:</td>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="0">
              <tr>
               <td>&nbsp;</td>
               <td class="label"><ul>A. Mild :Occurrence of one convulsion only</td>
               <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="25">
              <tr>
              <td>&nbsp;</td>
              <td class="label"><ul>B. Moderate:1-5 Convulsion/month on adequate medication </td>
              <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="50">
              <tr>
              <td>&nbsp;</td>
              <td class="label"><ul>C.  Severe: 6-10 convulsion/month on adequate medication</td>
              <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
              </logic:equal>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="75">
              <tr>
              <td>&nbsp;</td>
              <td class="label"><ul>D. Very Severe: more than 10 fits/month on adequate medication </td>
              <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
              </logic:equal>


              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value9flag" value="true">
                  <tr>
                  <td>&nbsp;</td>
                  <td align="center" class="labelBlue">Total</td>
                  <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value9"/></td>
                  </tr>
              </logic:equal>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="0">
                 <tr>
                 <td class="labelBlue">6.11</td>
                 <td colspan="2" class="label"> Ataxia </td>
                 </tr>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="25">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>A. Mild(Detected on examination) </td>
                  <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="50">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>B.  Moderate </td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="75">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>C. Severe </td>
                  <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="100">
                 <tr>
                 <td>&nbsp;</td>
                 <td class="label"><ul>D. Very Severe</td>
                  <td  class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value10flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center" class="labelBlue">Total</td>
                 <td class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value10"/></td>
                 </tr>
            </logic:equal>

            </logic:notEqual>

            <tr>

              <td colspan="3" align="center" class="label"><bean:write name="pIDuetoNeurologicalConditionsBean" property="totalpercentbuffer"/></td>
            </tr>
            <tr>
            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="totalpercentflag" value="true">
                <td colspan="3" align="center" class="label">Since Total Percent is Greater than 100 we are setting Total Percent <bean:write name="pIDuetoNeurologicalConditionsBean" property="totalpercent"/></td>
            </logic:equal>
            </tr>
         </logic:present>
        </tr>
        </table>
       <BR><BR><BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=6&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
        </form>
         <BR><BR><BR>

  </body>

</html:html>  