<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="java.util.*,org.bf.disability.dao.*,org.bf.disability.dto.TerritoryDTO" %>
<%@ page session="true" %>
<html:html>

    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }


        function textCounter2()
        {
            if (document.forms[0].anyotherinlocomotor.value.length > 100) // if too long...trim it!
                document.forms[0].anyotherinlocomotor.value = document.forms[0].anyotherinlocomotor.value.substring(0,100);
            // otherwise, update 'characters left' counter
        }
        function validateForm(thisForm){
            var flag = true;
            if(flag){
                if (thisForm.getAttribute('submitted')){
                    flag = false;
                    return flag;
                }else{
                    thisForm.setAttribute('submitted','true');
                    document.getElementById('addButton').disabled = true;
                }
            }
            return flag;
        }

    </script>
    <%
                // request.getAttribute("upperExtrimitydto");

                Double upperextrimity = 0.0;
                Double lowererextrimity = 0.0;
                Double amputation = 0.0;
                Double transverse = 0.0;
                Double trunk = 0.0;
                Double Evaluation = 0.0;
                Double cardiopulmonary = 0.0;
                Double dwarfism = 0.0;
                TerritoryDTO territoryDTO = (TerritoryDTO) request.getAttribute("territoryDTO");
                if (territoryDTO != null) {
                    upperextrimity = (Double) new Double(territoryDTO.getUpperExtremity_Total());
                    lowererextrimity = (Double) new Double(territoryDTO.getLowerExtremity_Total());
                    amputation = (Double) new Double(territoryDTO.getAmputation_Total());
                    transverse = (Double) new Double(territoryDTO.getTransverse_Total());
                    trunk = (Double) new Double(territoryDTO.getTrunk_Total());
                    Evaluation = (Double) new Double(territoryDTO.getPhysical_Impairments_Total());
                    cardiopulmonary = (Double) new Double(territoryDTO.getPulmonary_Condition());
                    dwarfism = (Double) new Double(territoryDTO.getDwarfism_Total());
                }

                boolean editFlag = (Boolean) session.getAttribute("disabilityEditFlag");

    %>
    <html:errors/>
    <BODY>
    
    <script language="JavaScript" src="./DisabilityUITG/js/partc.js"></script>
    <html:form action="/Locomotorneeds.do?insertLocomotorneeds=insertLocomotorneeds" method="post" styleId="LocomotorneedsForm" onsubmit="return validateForm(this)">

        <p  id="succmsg"><% String message = (String) request.getAttribute("msg");%>
            <% if (message != null) {%>
            <%=message%> <% }%></p>
        <table  align="center" cellspacing="0" cellpadding="0" border="1" class="inputform" width="90%">
            <tr>
                <th colspan="4">Select The Appropriate Disability Link&nbsp;&nbsp&nbsp;&nbspTO ADD DISABILITY</th></tr>

            <tr>
                <td colspan="4"> Locomotor/Physical Impairment/OH Disabilities<br/><br/>
                    <table  align="center" cellspacing="0" cellpadding="0" border="0" width="100%">
                        <tr>
                            <td style="padding-left: 20px">


                                <font size="3" color=#FF3300><li><a href="UpperExtrimityForwdAction.do">Upper Extremity</a></font><br/><br/>


                                <font size="3" color=#FF3300><li><a href="LowerExtrimityForwdAction.do">Lower Extremity</a></font><br/><br/>



                                <font size="3"color=#FF3300><li><a href="AmputationForwdAction.do">Amputation</a></font><br/><br/>


                                <font size="3"color=#FF3300><li><a href="TransverseForwdAction.do">Congential Deficiencies</a></font><br/><br/>



                                <font size="3"color=#FF3300><li><a href="TrunkForwdAction.do">Trunk</a></font><br/><br/>


                                <font size="3"color=#FF3300><li><a href="EvaluationForwdAction.do">Physical Impairment due to Neurological Conditions</a></font><br/><br/>



                                <font size="3"color=#FF3300><li><a href="CardioPulmonaryDeseasesForwdAction.do">Cardiopulmonary Diseases</a></font><br/><br/>



                                <font size="3"color=#FF3300><li><a href="DwarfismForwdAction.do">Dwarfism</a></font><br/><br/>

                            </td>

                        </tr>
                    </table>
                </td>
            </tr>

            <%--  </table>
              <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">--%>

            <tr>
                <th colspan="4">Need Assessment/Referred/Recommended(Physical Impairment)</th>
            </tr>

            <%
                        int caAU = 0, ca = 0, caPer = 0;
                        if (session.getAttribute("ageAU") != null) {
                            caAU = Integer.parseInt((String) session.getAttribute("ageAU"));
                        } else if (session.getAttribute("agePersonalInsert") != null) {
                            caPer = Integer.parseInt((String) session.getAttribute("agePersonalInsert"));
                        } else {
                            ca = Integer.parseInt((String) session.getAttribute("agePersonal"));
                        }
                        if (caAU <= 3 && ca <= 3 && caPer <= 3) {
            %>
            <tr>
                <td colspan="2" >1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
            </tr>
            <tr> <td  width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Physiotherapy</td>
                <td><html:checkbox property="physiotheraphy" value="physiotheraphy"  style="width:25px"></html:checkbox></td>
            </tr>
            <tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Occupational Therapy</td>
                <td><html:checkbox property="occupationaltheraphy" value="occupationaltheraphy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr> <td  width="50%" >2. Surgery</td>
                <td width="48%" ><html:text property="surgery"  maxlength="100" ></html:text></td>
            </tr>
            <tr> <td  > 3. Physiotherapy</td>
                <td><html:checkbox property="physiotherapy" value="physiotherapy" disabled="true" style="width:25px" style="width:25px"></html:checkbox></td>
            </tr>
            <tr><td  >4. Occupational Therapy</td>
                <td><html:checkbox property="occupationaltherapy" value="occupationaltherapy" disabled="true" style="width:25px"></html:checkbox></td>
            </tr>
            <%} else {%>

            <tr>
                <td colspan="2" >1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
            </tr>
            <tr> <td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Physiotherapy</td>
                <td  width="48%"><html:checkbox property="physiotheraphy" value="physiotheraphy" disabled="true" style="width:25px"></html:checkbox></td>
            </tr>
            <tr><td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Occupational Therapy</td>
                <td><html:checkbox property="occupationaltheraphy" value="occupationaltheraphy" disabled="true" style="width:25px"></html:checkbox></td>
            </tr>
            <tr> <td  width="50%" >2. Surgery</td>
                <td width="48%" ><html:text property="surgery"  maxlength="100" ></html:text></td>
            </tr>
            <tr> <td > 3. Physiotherapy</td>
                <td><html:checkbox property="physiotherapy" value="physiotherapy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr><td  >4. Occupational Therapy</td>
                <td><html:checkbox property="occupationaltherapy" value="occupationaltherapy" style="width:25px"></html:checkbox></td>
            </tr>

            <%}%>

            <tr>
                <td colspan="8" >5.  Assistive & Augmentative Devices For The Persons With Locomotor Disability </td>
            </tr>
            <tr> <td fontsize="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.1  Wheel Chair( Select Fields) </td>
                <td width="48%">
                    <html:select property="selectwheelchair"   >
                        <html:option value="" styleId="selectedwheelchair">---  Select One ---</html:option>
                        <html:option value="Large Wheel Chair">Large Wheel Chair</html:option>
                        <html:option value="Small Wheel Chair">Small Wheel Chair</html:option>
                    </html:select></td>
            </tr>
            <tr> <td width="50%" fontsize="2" >
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.2  Tricycle( Select Fields) </td>
                <td width="48%">  <font size="2">
                        <html:select property="selecttricycle" value=""  >
                            <html:option value="" styleId="selectedtricycle">---  Select One   -----</html:option>
                            <html:option value="Large Tricycle">Large Tricycle</html:option>
                            <html:option value="Small Tricycle">Small Tricycle</html:option></font> </html:select></td>
                </tr>

                <tr> <td width="50%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        5.3  Walking Stick(Select Fields) </td>
                    <td width="48%">  <font size="2">
                        <html:select property="selectwalkingstick" value=""  >
                            <html:option value="" styleId="selectedwalkingstick">---  Select One ---</html:option>
                            <html:option value="Large Walking Stick">Large Walking Stick</html:option>
                            <html:option value="Small Walking Stick">Small Walking Stick</html:option></font></html:select>
                    </td>
                </tr>

                <tr> <td width="50%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        5.4  Crutches(Select Fields) </td>
                    <td width="48%">  <font size="2">
                        <html:select property="selectcrutches" value="" >
                            <html:option value="" styleId="selectedcrutches">---  Select One   ---</html:option>
                            <html:option value="Small Crutches">Small Crutches</html:option>
                            <html:option value="Medium Crutches">Medium Crutches</html:option>
                            <html:option value="Large Crutches">Large Crutches</html:option>
                            <html:option value="Extra Large Crutches">Extra Large Crutches</html:option>
                        </font> </html:select>
                        <font size="2">
                        <html:select property="crutchestype" value=""   >
                            <html:option value="" styleId="crutchestype">---  Select One   ---</html:option>
                            <html:option value="Axillary">Axillary</html:option>
                            <html:option value="Elbow">Elbow</html:option>
                            <html:option value="Gutter">Gutter</html:option>
                            <html:option value="Tripod">Tripod</html:option>
                        </font> </html:select></td>
                </tr>
                <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.5  Walking Frame( Select Fields) </td>
                    <td>  <font size="2">
                        <html:select property="selectwalkingframe" value="" >
                            <html:option value="" styleId="selectedwalkingframe">---  Select One ---</html:option>
                            <html:option value="Small WalkingFrame">Small Walking Frame</html:option>
                            <html:option value="Large WalkingFrame">Large Walking Frame</html:option></font></html:select>
                    </td>
                </tr>

                <tr> <td colspan="8" >&nbsp;&nbsp;&nbsp;&nbsp; 5.6 Orthosis/Splint for UpperExtremity</td>
                </tr>

                <tr> <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Aeroplane Splint</td>
                    <td><html:checkbox property="aeroplanesplint" value="Aeroplane Splint" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Figure-8 Splint</td>
                <td><html:checkbox property="figure8splint" value="Figure-8 Splint" style="width:25px"></html:checkbox> </td>
            </tr>

            <tr> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Fore Arm Splint</td>
                <td><html:checkbox property="forearmsplint" value="ForeArm Splint" style="width:25px"></html:checkbox></td>
            </tr>

            <tr><td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Hand Splint</td>
                <td><html:checkbox property="handsplint" value="Hand Splint" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>  <td colspan="8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.7 Prosthesis for UpperExtremity</td>
            </tr>

            <tr> <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Shoulder Prosthesis</td>
                <td><html:checkbox property="shoulderprosthesis" value="Shoulder Prosthesis" style="width:25px"></html:checkbox></td>
            </tr>

            <tr> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Elbow  Prosthesis</td>
                <td><html:checkbox property="aboveelbowprosthesis" value="Above Elbow  Prosthesis" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr> <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Elbow Disarticulation Prosthesis</td>
                <td><html:checkbox property="elbowdisarticulationprosthesis" value="Elbow Disarticulation Prosthesis" style="width:25px"></html:checkbox></td>
            </tr>

            <tr> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Elbow  Prosthesis</td>
                <td><html:checkbox property="belowelbowprosthesis" value="Below Elbow  Prosthesis" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr> <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Wrist Disarticulation Prosthesis </td>
                <td><html:checkbox property="wristdisarticulationprosthesis" value="Wrist Disarticulation Prosthesis" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr> <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Hand Prosthesis</td>
                <td><html:checkbox property="handprosthesis" value="Hand Prosthesis" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>  <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Cosmetic Finger Prosthesis</td>
                <td><html:checkbox property="cosmeticfingerprosthesis" value="Cosmetic Finger Prosthesis" style="width:25px"></html:checkbox>
                </td>
            </tr>

            <tr>
                <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.8 Orthosis/Splint for Lower Extremity</td>
            </tr>

            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.HKAFO</td>
                <td>
                    <html:checkbox property="hkafo" value="HKAFO" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.KAFO</td>
                <td>
                    <html:checkbox property="kafo" value="KAFO" style="width:25px">
                    </html:checkbox> </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii. AFO</td>
                <td>
                    <html:checkbox property="afo" value="AFO" style="width:25px">
                    </html:checkbox> </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Knee Orthosis</td>
                <td>
                    <html:checkbox property="kneeorthosis" value="Knee Orthosis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.DB Splint</td>
                <td>
                    <html:checkbox property="dbsplint" value="DB Splint" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Modified Shoe (Surgical Shoe)</td>
                <td>
                    <html:checkbox property="modifiedshoe" value="Modified Shoe" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Serial Casting (For CTEV)</td>
                <td>
                    <html:checkbox property="serialcasting" value="Serial Casting" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td colspan="8" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.9 Prosthesis for LowerExtremity</td>
            </tr>

            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Hip Disarticulation Prosthesis</td>
                <td>
                    <html:checkbox property="hipprothesis" value="Hip Disarticulation Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Knee Prosthesis</td>
                <td>
                    <html:checkbox property="abovekneeprothesis" value="Above Knee Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Knee Disarticulation Prosthesis</td>
                <td>
                    <html:checkbox property="kneedisarticulation" value="Knee Disarticulation Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Knee Prosthesis</td>
                <td>
                    <html:checkbox property="belowkneeprothesis" value="Below Knee Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  v.Symes Prosthesis</td>
                <td>
                    <html:checkbox property="symeprothesis" value="Symes Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Partial Foot Prosthesis</td>
                <td>
                    <html:checkbox property="partialfoodprothesis" value="Partial Foot Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.10 Spinal Orthosis</td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  i.Cervical Collar</td>
                <td>
                    <html:checkbox property="cervicalcollar" value="Cervical Collar" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ii.LS Brace</td>
                <td>
                    <html:checkbox property="lsbrace" value="LS Brace" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.TLSO Brace (For Scoliosis/ Kyphosis)</td>
                <td><html:checkbox property="tlsobrace" value="TLSO Brace" style="width:25px"></html:checkbox></td>
            </tr>

            <tr> <td colspan="2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.11 Any ADL Equipment</td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Feeding</td>
                <td><html:checkbox property="feeding" value="Feeding" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Toileting/Bathing</td>
                <td><html:checkbox property="bathing" value="Toileting/Bathing" style="width:25px"></html:checkbox></td>
            </tr>

            <tr> <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Brushing</td>
                <td><html:checkbox property="brushing" value="Brushing" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Combing</td>
                <td><html:checkbox property="combing" value="Combing" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Dressing</td>
                <td><html:checkbox property="dressing" value="Dressing" style="width:25px"></html:checkbox> </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Writing</td>
                <td> <html:checkbox property="writing" value="Writing" style="width:25px"> </html:checkbox> </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Driving/Cycling</td>
                <td> <html:checkbox property="drivingcycling" value="Driving/Cycling" style="width:25px"> </html:checkbox></td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Bed Transfer</td>
                <td><html:checkbox property="bedtransfer" value="Bed Transfer" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>
                <td>6. Any Other Physical Impairment/Locomotor Needs</td>
                <td><html:textarea rows="4" cols="30" property="anyotherinlocomotor" onkeydown="textCounter2()" onkeyup="textCounter2()"> </html:textarea> </td>
            </tr>

            <tr>
                <th colspan="10"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
                    <html:submit value="Add" styleId="addButton" styleClass="button"/>
                    <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>
            </tr>
        </table>
    </html:form>

</BODY>
</html:html>
