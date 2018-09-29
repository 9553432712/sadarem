<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
   
<html:html>

     <body  onload="window.print()">
     <form>

     <logic:present name="pIDuetoNeurologicalConditionsBean" scope="request">
  
        <table width="100%"  border="0" align="center" >
            <tr><td colspan="3"  align="right">ID No.&nbsp;<%=personcode%></td></tr>
        <tr><td colspan="3"  align="right">Name.&nbsp;<%=name%></td></tr>

            <tr>
                <th align="center" width="100%" >&nbsp;&nbsp;Reference for Calculation</th>
            </tr>
        </table>
        <table width="100%"  border="0" align="center" >


            <!--Displays DominantUpperExtrimity-->

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="dominantupperextremity" value="0">
                <tr>
                <td width="5%">6.1</td>
                <td> Involvement of Dominant Upper Extremity </td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="dominantupperextremity"/></td>
                </tr>
            </logic:notEqual>

            <!--Checks for sum of LossOfSensation-->

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sumoflossofsensation" value="0">
                <tr>
                <td width="5%">6.2</td>
                <td colspan="2">Loss of Sensation</td>
                <!--Displays LossOfSensationUpper-->
                </tr>

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper" value="0">
                    <tr>
                    <td>&nbsp;</td>
                    <td ><ul>A. Loss of Sensation Upper</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper"/></td>
                    </tr>
                </logic:notEqual>

                <!--Displays LossOfSensationLower-->

                <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower" value="0">
                    <tr>
                    <td>&nbsp;</td>
                    <td ><ul> B. Loss of Sensation Lower</td>
                    <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower"/></td>
                    </tr>
                </logic:notEqual>


                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="lossofsensationflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align=center>Total</td>
                <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationupper"/>+<bean:write name="pIDuetoNeurologicalConditionsBean" property="lossofsensationlower"/>=<bean:write name="pIDuetoNeurologicalConditionsBean" property="sumoflossofsensation"/></td>
                </tr>
                </logic:equal>

             </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus" value="0">

                <tr>

                 <td >6.3</td>
                 <td>Altered sensorium (Neurological Status)</td>
                 <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus"/></td>

               </tr>

            </logic:notEqual>



            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value1flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td>After substituting <bean:write name="pIDuetoNeurologicalConditionsBean" property="neurologicalstatus"/> into formula, the result will be</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="value1"/></td>
                </tr>
            </logic:equal>



            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="0">
                <tr>
                <td>6.4</td>
                <td colspan="2">Intellectual Impairment</td>
                </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="25">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>Boarderline</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="50">
                <tr>
                <td>&nbsp;</td>
                <td><ul>Mild</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="75">
                <tr>
                <td>&nbsp;</td>
                <td><ul>Moderate</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="90">
                <tr>
                <td>&nbsp;</td>
                <td><ul>Severe</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment" value="100">
                <tr>
                <td>&nbsp;</td>
                <td><ul>Profound</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="intellectualimpairment"/></td>
                </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value2flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center">Total</td>
                 <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="value2"/></td>
                 </tr>
            </logic:equal>

            </logic:notEqual>

            <tr>
            <td>6.5</td>
            <td colspan="2">Speech Defect</td>
            </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="0">

                <tr>
                <td>&nbsp;</td>
                <td ><ul>A. Mild Dysarthria</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>

            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="25">

                <tr>
                <td>&nbsp;</td>
                <td><ul>B.Moderate Dysarthria</td>
                 <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>

            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="speechdefect" value="50">

                <tr>
                <td >&nbsp;</td>
                <td ><ul> C. Severe Dysarthria</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="speechdefect"/></td>
                </tr>
             </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value3flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center">Total</td>
                 <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value3"/></td>
                 </tr>
            </logic:equal>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorcranialnerveflag" value="true">
                <tr>
                 <td >6.6.1</td><td colspan="2"> Motor Cranial Nerve</td>
                </tr>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6a" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>A. Oculomotor Nerve</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6a"/></td>
                 </tr>
            </logic:notEqual>



            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6b" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>B. Trochlear Nerve</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6b"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6c" value="0">
                <tr>
                <td>&nbsp;</td>
                <td><ul>C. Abducence Nerve</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6c"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6d" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>D. Facial Nerve</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6d"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6e" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>E. Accessory Nerve</td>
                <td width="20%" ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6e"/></td>
                </tr>
            </logic:notEqual>


            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6f" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>F. Hypoglossal Nerve</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6f"/></td>
                </tr>
            </logic:notEqual>

                 <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6g" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>G. Trigeminal Nerve</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6g"/></td>
                </tr>
            </logic:notEqual>

                 <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="e6h" value="0">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>H. Vagus Nerve</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="e6h"/></td>
                </tr>
            </logic:notEqual>





            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motornervetotalflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center">Motor Cranial Nerve Total </td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorstring"/></td>
                </tr>
            </logic:equal>


             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motornerveflagforvalue" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td>Since Motor Cranial Nerve Total value is greater than 100, Motor Cranial Nerve Total value is taken as 100</td>
                 <td>&nbsp;</td>
                 </tr>
              </logic:equal>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value4flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center">Total</td>
                <td width="30%"><bean:write name="pIDuetoNeurologicalConditionsBean" property="value4"/></td>
                </tr>
            </logic:equal>

            </logic:equal>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorycranialnerveflag" value="true">
                <tr>
                <td >6.6.2</td>
                <td colspan="2"> Sensory Cranial Nerve</td>
                </tr>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sca" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>A. Olfactory Nerve</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="sca"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scb" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>B. Trochlear Nerve</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="scb"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scc" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>C. Vestibulocochlear Nerve</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="scc"/></td>
                 </tr>
            </logic:notEqual>

             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="scd" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>D. Glossopharyngeal Nerve</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="scd"/></td>
                 </tr>
            </logic:notEqual>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorynervetotalflag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center">Sensory Nerve Total</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorystring"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value5flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center">Total</td>
                <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="value5"/></td>
                </tr>
            </logic:equal>

             </logic:equal>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="0">
                 <tr>
                 <td >6.7</td>
                 <td colspan="2">Motor system Disability</td>
                 </tr>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="25">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>A. Mild</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="50">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>B.  Moderate</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="motorsystem" value="75">
                <tr>
                <td>&nbsp;</td>
                <td ><ul>C. Severe</td>
                <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="motorsystem"/></td>
                </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value6flag" value="true">
                <tr>
                <td>&nbsp;</td>
                <td align="center">Total</td>
                <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value6"/></td>
                </tr>
            </logic:equal>


            </logic:notEqual>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystemflag" value="true">
                <tr>
                <td >6.8</td>
                <td colspan="2">Sensory system Disability</td>
                </tr>



             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2"><ul>A. Anesthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2"><ul>B. Hypoesthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem1"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>

            <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="0">
                 <tr>
                 <td>&nbsp;</td>
                 <td colspan="2"><ul>C. Parasthesia:</td>
                 </tr>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="2">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mild</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="5">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Moderate</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

                <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2" value="10">
                    <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Severe</td>
                    <td><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystem2"/></td>
                    </tr>
                </logic:equal>

            </logic:notEqual>


            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="sensorysystemtotalflag" value="true">
                    <tr>
                    <td>&nbsp;</td>
                    <td align="center">Sensory System Disability Total </td>
                    <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="sensorysystemstring"/></td>
                    </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value7flag" value="true">
                    <tr>
                    <td>&nbsp;</td>
                    <td align="center">Total</td>
                    <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value7"/></td>
                    </tr>
            </logic:equal>

            </logic:equal>


              <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="0">
                  <tr>
                  <td >6.9</td>
                  <td colspan="2">Bladder disability Due to neurological involvement:</td>
                  </tr>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="25">
                  <tr>
                  <td>&nbsp;</td>
                  <td ><ul>A. Mild (Hesitancy and Frequency)</td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                  </tr>
               </logic:equal>


              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="50">
                  <tr>
                  <td>&nbsp;</td>
                  <td ><ul>B. Moderate(precipitancy)</td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                  </tr>
               </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="75">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>C. Severe(Occasional but recurrent incontinence)</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                 </tr>
               </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement" value="100">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>D. Very Severe(Retension/total incontinence)</td>
                 <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="bladderinvolvement"/></td>
                 </tr>
               </logic:equal>

               <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value8flag" value="true">
                   <tr>
                   <td>&nbsp;</td>
                   <td align="center">Total</td>
                   <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value8"/></td>
                   </tr>
            </logic:equal>

             </logic:notEqual>

             <tr>
              <td>6.10</td>
              <td colspan="2"> Post head injury fits & epileptic Convulsions:</td>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="0">
              <tr>
               <td>&nbsp;</td>
               <td ><ul>A. Mild :Occurrence of one convulsion only</td>
               <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="25">
              <tr>
              <td>&nbsp;</td>
              <td ><ul>B. Moderate:1-5 Convulsion/month on adequate medication </td>
              <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="50">
              <tr>
              <td>&nbsp;</td>
              <td ><ul>C.  Severe: 6-10 convulsion/month on adequate medication</td>
              <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
              </logic:equal>

              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="injury" value="75">
              <tr>
              <td>&nbsp;</td>
              <td ><ul>D. Very Severe: more than 10 fits/month on adequate medication </td>
              <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="injury"/></td>
              </tr>
              </logic:equal>


              <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value9flag" value="true">
                  <tr>
                  <td>&nbsp;</td>
                  <td align="center">Total</td>
                  <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value9"/></td>
                  </tr>
              </logic:equal>


             <logic:notEqual name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="0">
                 <tr>
                 <td >6.11</td>
                 <td colspan="2"> Ataxia </td>
                 </tr>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="25">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>A. Mild(Detected on examination) </td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="50">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>B.  Moderate </td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="75">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>C. Severe </td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="ataxia" value="100">
                 <tr>
                 <td>&nbsp;</td>
                 <td ><ul>D. Very Severe</td>
                  <td  ><bean:write name="pIDuetoNeurologicalConditionsBean" property="ataxia"/></td>
                 </tr>
            </logic:equal>

             <logic:equal name="pIDuetoNeurologicalConditionsBean" property="value10flag" value="true">
                 <tr>
                 <td>&nbsp;</td>
                 <td align="center">Total</td>
                 <td ><bean:write name="pIDuetoNeurologicalConditionsBean" property="value10"/></td>
                 </tr>
            </logic:equal>

            </logic:notEqual>

            <tr>

              <td colspan="3" align="center"><bean:write name="pIDuetoNeurologicalConditionsBean" property="totalpercentbuffer"/></td>
            </tr>
            <tr>
            <logic:equal name="pIDuetoNeurologicalConditionsBean" property="totalpercentflag" value="true">
                <td colspan="3" align="center">Since Total Percent is Greater than 100 we are setting Total Percent <bean:write name="pIDuetoNeurologicalConditionsBean" property="totalpercent"/></td>
            </logic:equal>
            </tr>
         </logic:present>
        </tr>
        </table>
             <BR><BR><BR>

        </form>
  </body>

</html:html>  