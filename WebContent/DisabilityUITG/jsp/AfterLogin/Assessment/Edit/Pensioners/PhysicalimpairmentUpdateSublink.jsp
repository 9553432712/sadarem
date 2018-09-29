<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="java.util.*,org.bf.disability.dao.*,org.bf.disability.dto.TerritoryDTO" %>
<%@ page session="true" %>
<html:html>

    <%
                Double upperextrimity = 0.0;
                Double lowererextrimity = 0.0;
                Double amputation = 0.0;
                Double transverse = 0.0;
                Double trunk = 0.0;
                Double Evaluation = 0.0;
                Double cardiopulmonary = 0.0;
                Double dwarfism = 0.0;
                TerritoryDTO territoryDTO = (TerritoryDTO) request.getAttribute("territoryDTO");
                upperextrimity = (Double) new Double(territoryDTO.getUpperExtremity_Total());
                lowererextrimity = (Double) new Double(territoryDTO.getLowerExtremity_Total());
                amputation = (Double) new Double(territoryDTO.getAmputation_Total());
                transverse = (Double) new Double(territoryDTO.getTransverse_Total());
                trunk = (Double) new Double(territoryDTO.getTrunk_Total());
                Evaluation = (Double) new Double(territoryDTO.getPhysical_Impairments_Total());
                cardiopulmonary = (Double) new Double(territoryDTO.getPulmonary_Condition());
                dwarfism = (Double) new Double(territoryDTO.getDwarfism_Total());


                boolean editFlag = (Boolean) session.getAttribute("disabilityEditFlag");


                /* double upperextrimitytotal=Math.round(upperextrimity.doubleValue());
                double lowererextrimitytotal=Math.round(lowererextrimity.doubleValue());
                double amputationtotal=Math.round(amputation.doubleValue());
                double transversetotal=Math.round(transverse.doubleValue());
                double trunktotal=Math.round( trunk.doubleValue());
                double Evaluationtotal=Math.round(Evaluation.doubleValue());
                double cardiopulmonarytotal=Math.round(cardiopulmonary.doubleValue());
                double dwarfismtotal=Math.round(dwarfism.doubleValue()); */


// double totaldisability=0;

                /*
                LinkedList arry= new LinkedList();

                arry.add(upperextrimity);
                arry.add(lowererextrimity);
                arry.add(amputation);
                arry.add(transverse);
                arry.add(trunk);
                arry.add(Evaluation);
                arry.add(cardiopulmonary);
                arry.add(dwarfism);

                session.setAttribute("totallist1",arry);
                LinkedList link= new LinkedList();
                link.addAll(arry);
                calculateResult c=new calculateResult();
                double ressub=Math.round(c.needCalculation(link));
                if(ressub>100)
                ressub=100;

                session.setAttribute("totalphysical",new Double(ressub));*/

    %>

    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }

        function textCounter2()
        {
            if (document.forms[0].anyotherinlocomotor.value.length > 100) // if too long...trim it!
                document.forms[0].anyotherinlocomotor.value = document.forms[0].anyotherinlocomotor.value.substring(0,100);
            // otherwise, update 'characters left' counter
        }


        function disableLink(){

            var upperextrimityValue ='<%=upperextrimity%>';
            var lowererextrimityValue ='<%=lowererextrimity%>';
            var amputation ='<%=amputation%>';
            var transverse ='<%=transverse%>';
            var trunk ='<%=trunk%>';
            var Evaluation ='<%=Evaluation%>';
            var cardiopulmonary ='<%=cardiopulmonary%>';
            var dwarfism ='<%=dwarfism%>';

            var editStatus = '<%=editFlag%>';

            if(null != upperextrimityValue){
                if((upperextrimityValue > 0.0 && editStatus == "false")){
                    document.getElementById("upper").removeAttribute("href");
                    document.getElementById("upper").style.color = "gray";
                }
                if(null != document.getElementById("upperdata")){
                    if(upperextrimityValue > 0.0){
                        document.getElementById("upperdata").style.visibility = "Visible";
                        document.getElementById("upperdata").style.display = "Inline";
                    }else{
                        document.getElementById("upperdata").style.visibility = "hidden";
                        document.getElementById("upperdata").style.display = "none";
                    }
                }
            }
            if(null != lowererextrimityValue){
                if((lowererextrimityValue > 0.0 && editStatus == "false")){
                    document.getElementById("lower").removeAttribute("href");
                    document.getElementById("lower").style.color = "gray";
                }
                if(null != document.getElementById("lowerdata")){
                    if(lowererextrimityValue > 0.0){
                        document.getElementById("lowerdata").style.visibility = "Visible";
                        document.getElementById("lowerdata").style.display = "Inline";
                    }else{
                        document.getElementById("lowerdata").style.visibility = "hidden";
                        document.getElementById("lowerdata").style.display = "none";
                    }
                }
            }
            if(null != amputation){
                if((amputation > 0.0 && editStatus == "false")){
                    document.getElementById("amputation").removeAttribute("href");
                    document.getElementById("amputation").style.color = "gray";
                }
                if(null != document.getElementById("amputationdata")){
                    if(amputation > 0.0){
                        document.getElementById("amputationdata").style.visibility = "Visible";
                        document.getElementById("amputationdata").style.display = "Inline";
                    }else{
                        document.getElementById("amputationdata").style.visibility = "hidden";
                        document.getElementById("amputationdata").style.display = "none";
                    }
                }
            }
            if(null != transverse){
                if((transverse > 0.0 && editStatus == "false")){
                    document.getElementById("transverse").removeAttribute("href");
                    document.getElementById("transverse").style.color = "gray";
                }
                if(null != document.getElementById("transversedata")){
                    if(transverse > 0.0){
                        document.getElementById("transversedata").style.visibility = "Visible";
                        document.getElementById("transversedata").style.display = "Inline";
                    }else{
                        document.getElementById("transversedata").style.visibility = "hidden";
                        document.getElementById("transversedata").style.display = "none";
                    }
                }
            }
            if(null != trunk){
                if((trunk > 0.0 && editStatus == "false")){
                    document.getElementById("trunk").removeAttribute("href");
                    document.getElementById("trunk").style.color = "gray";
                }
                if(null != document.getElementById("trunkdata")){
                    if(trunk > 0.0){
                        document.getElementById("trunkdata").style.visibility = "Visible";
                        document.getElementById("trunkdata").style.display = "Inline";
                    }else{
                        document.getElementById("trunkdata").style.visibility = "hidden";
                        document.getElementById("trunkdata").style.display = "none";
                    }
                }
            }
            if(null != Evaluation){
                if((Evaluation > 0.0 && editStatus == "false")){
                    document.getElementById("evalution").removeAttribute("href");
                    document.getElementById("evalution").style.color = "gray";
                }
                if(null != document.getElementById("evalutiondata")){
                    if(Evaluation > 0.0){
                        document.getElementById("evalutiondata").style.visibility = "Visible";
                        document.getElementById("evalutiondata").style.display = "Inline";
                    }else{
                        document.getElementById("evalutiondata").style.visibility = "hidden";
                        document.getElementById("evalutiondata").style.display = "none";
                    }
                }
            }
            if(null != cardiopulmonary){
                if((cardiopulmonary > 0.0 && editStatus == "false")){
                    document.getElementById("cardio").removeAttribute("href");
                    document.getElementById("cardio").style.color = "gray";
                }
                if(null != document.getElementById("cardiodata")){
                    if(cardiopulmonary > 0.0){
                        document.getElementById("cardiodata").style.visibility = "Visible";
                        document.getElementById("cardiodata").style.display = "Inline";
                    }else{
                        document.getElementById("cardiodata").style.visibility = "hidden";
                        document.getElementById("cardiodata").style.display = "none";
                    }
                }
            }
            if(null != dwarfism){
                if((dwarfism > 0.0 && editStatus == "false")){
                    document.getElementById("dwarfism").removeAttribute("href");
                    document.getElementById("dwarfism").style.color = "gray";
                }
                if(null != document.getElementById("dwarfismdata")){
                    if(dwarfism > 0.0){
                        document.getElementById("dwarfismdata").style.visibility = "Visible";
                        document.getElementById("dwarfismdata").style.display = "Inline";
                    }else{
                        document.getElementById("dwarfismdata").style.visibility = "hidden";
                        document.getElementById("dwarfismdata").style.display = "none";
                    }
                }
            }
        }
        
    </script> 
    <html:errors/>
    <BODY onload="disableLink();">
    
    <script language="JavaScript" src="./DisabilityUITG/js/partc.js"></script>
    <html:form action="/Locomotorupdateneeds.do?updateLocomotorneeds=updateLocomotorneeds" method="post" styleId="LocomotorneedsForm" onsubmit="document.getElementById('phyUpdateButton').disabled = true;">
        <p  id="succmsg"> <% String message = (String) request.getAttribute("msg");%>
            <% if (message != null) {%> <%=message%> <% }%></p>
        <table  border="1" align="center" cellspacing="0" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th class="2"><center>Select The Appropriate Disability Link &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp TO UPDATE DISABILITY</center></th>
            </tr>

            <tr>
               <td colspan="4"> Locomotor/Physical Impairment/OH Disabilities<br/><br/>
                    <table>   <tr>  <td style="padding-left: 20px">

                                <font size="3"color=#FF3300><li><a href="SelectUpperExtreimity.do?selectUpperExterimity=selectUpperExterimity" id="upper" onclick="disableLink();"><font size="2">Upper Extremity</font> </a></font>
                    <div id="upperdata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>

 <br/><br/>

                    <font size="3"color=#FF3300><li><a href="lowerextremityselect.do?getLowerExtremityDetails=getLowerExtremityDetails" id="lower" onclick="disableLink();"><font size="2">Lower Extremity</font></a></font>
                    <div id="lowerdata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>

                    <font size="3"color=#FF3300><li><a href="SelectAmputationDetails.do?getAmputationDetails=getAmputationDetails" id="amputation" onclick="disableLink();"><font size="2">Amputation</font></a></font>
                    <div id="amputationdata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>
                    <font size="3"color=#FF3300><li><a href="getTransverseDetails.do?getTransverseDetails=getTransverseDetails" id="transverse" onclick="disableLink();"><font size="2">Congential Deficiencies</font></a></font>
                    <div id="transversedata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>

                    <br/><br/>


                    <font size="3"color=#FF3300><li><a href="trunkselect.do?getTrunkDetails=getTrunkDetails" id="trunk" onclick="disableLink();"><font size="2">Trunk</font></a></font>
                    <div id="trunkdata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>

                    <font size="3"color=#FF3300><li><a href="evaluationselect.do?selectEvaluation=selectEvaluation" id="evalution" onclick="disableLink();"><font size="2">Physical Impairment due to Neurological Conditions</font></a></font>
                    <div id="evalutiondata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>


                    <font size="3"color=#FF3300><li><a href="getcardiopolumonary.do?getCardio=getCardio" id="cardio" onclick="disableLink();"><font size="2">Cardiopulmonary Diseases</font></a></font>
                    <div id="cardiodata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>

                    <font size="3"color=#FF3300><li><a href="getdwarfism.do?getDwarfismDetails=getDwarfismDetails" id="dwarfism" onclick="disableLink();"><font size="2">Dwarfism</font></a></font>
                    <div id="dwarfismdata" style="visibility:hidden;display:none">
                        <font color="red" size="2">Data Entered</font>
                    </div>
                    <br/><br/>
             
                            </td></tr></table>
                </td></tr>
        </table>
       
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">

            <tr>
                <th colspan="8">Need Assessment/Referred/Recommended (Physical Impairment)</th>
            </tr>

            <tr>
                <td colspan="3">1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
            </tr>

            <tr>
                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Physiotherapy</td>
                <td><html:checkbox property="physiotheraphy" value="physiotheraphy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Occupational Therapy</td>
                <td><html:checkbox property="occupationaltheraphy" value="occupationaltheraphy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td>2. Surgery</td>
                <td width="48%"><html:text property="surgery" maxlength="100" style="width:200px"></html:text></td>
            </tr>
            <tr>
                <td>3. Physiotherapy</td>
                <td><html:checkbox property="physiotherapy" value="physiotherapy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td>4. Occupational Therapy</td>
                <td><html:checkbox property="occupationaltherapy" value="occupationaltherapy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td colspan="8">5.  Assistive & Augmentative Devices For Locomotor Disability</td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.1  Wheel Chair( Select Fields) </td>
                <td>
                    <html:select property="selectwheelchair"   >
                        <html:option value="" >---  Select One ---</html:option>
                        <html:option value="Large Wheel Chair">Large Wheel Chair</html:option>
                        <html:option value="Small Wheel Chair">Small Wheel Chair</html:option>
                    </html:select></td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.2  Tricycle ( Select Fields) </td>
                <td>  <font size="2">
                        <html:select property="selecttricycle"  >
                            <html:option value="">---  Select One   -----</html:option>
                            <html:option value="Large Tricycle">Large Tricycle</html:option>
                            <html:option value="Small Tricycle">Small Tricycle</html:option></font> </html:select></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.3  Walking Stick &nbsp;&nbsp;&nbsp;&nbsp; (Select Fields) </td>
                    <td>  <font size="2">
                        <html:select property="selectwalkingstick"    >
                            <html:option value="" >---  Select One ---</html:option>
                            <html:option value="Large Walking Stick">Large Walking Stick</html:option>
                            <html:option value="Small Walking Stick">Small Walking Stick</html:option></font></html:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        5.4  Crutches&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <font size="1">
                            ( Select Fields)</font>
                    </td>
                    <td>
                    <html:select property="selectcrutches"   >
                        <html:option value="" >---  Select One   ---</html:option>
                        <html:option value="Small Crutches">Small Crutches</html:option>
                        <html:option value="Medium Crutches">Medium Crutches</html:option>
                        <html:option value="Large Crutches">Large Crutches</html:option>
                        <html:option value="Extra Large Crutches">Extra Large Crutches</html:option>
                    </html:select>

                    <html:select property="crutchestype"    >
                        <html:option value="" >---  Select One   ---</html:option>
                        <html:option value="Axillary">Axillary</html:option>
                        <html:option value="Elbow">Elbow</html:option>
                        <html:option value="Gutter">Gutter</html:option>
                        <html:option value="Tripod">Tripod</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font size="2">
                        5.5  Walking Frame&nbsp;&nbsp;&nbsp;&nbsp;</font>( Select Fields)
                </td>
                <td width="48%">  <font size="2">
                        <html:select property="selectwalkingframe"   >
                            <html:option value="" >---  Select One ---</html:option>
                            <html:option value="Small WalkingFrame">Small Walking Frame</html:option>
                            <html:option value="Large WalkingFrame">Large Walking Frame</html:option></font></html:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" align="left">
                        <font size="2">
                            &nbsp;&nbsp;&nbsp;&nbsp; 5.6 Orthosis Calipers/Splint for UpperExtremity
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>

                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Aeroplane Splint

                    </td>
                    <td>
                    <html:checkbox property="aeroplanesplint" value="Aeroplane Splint" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Figure-8 Splint
                </td>
                <td>
                    <html:checkbox property="figure8splint" value="Figure-8 Splint" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Fore Arm Splint
                </td>
                <td>
                    <html:checkbox property="forearmsplint" value="ForeArm Splint" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Hand Splint
                </td>
                <td>
                    <html:checkbox property="handsplint" value="Hand Splint" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.7 Upper Extremity Prosthesis
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Shoulder Prosthesis
                </td>
                <td>
                    <html:checkbox property="shoulderprosthesis" value="Shoulder Prosthesis" style="width:25px">

                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Elbow  Prosthesis
                </td>
                <td>
                    <html:checkbox property="aboveelbowprosthesis" value="Above Elbow  Prosthesis" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Elbow Disarticulation Prosthesis
                </td>
                <td>
                    <html:checkbox property="elbowdisarticulationprosthesis" value="Elbow Disarticulation Prosthesis" style="width:25px">

                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Elbow  Prosthesis
                </td>
                <td>
                    <html:checkbox property="belowelbowprosthesis" value="Below Elbow  Prosthesis" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Wrist Disarticulation Prosthesis
                </td>
                <td>
                    <html:checkbox property="wristdisarticulationprosthesis" value="Wrist Disarticulation Prosthesis" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Hand Prosthesis

                </td>
                <td>
                    <html:checkbox property="handprosthesis" value="Hand Prosthesis" style="width:25px"></html:checkbox>
                </td> </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Cosmetic Finger Prosthesis
                </td>
                <td>
                    <html:checkbox property="cosmeticfingerprosthesis" value="Cosmetic Finger Prosthesis" style="width:25px"></html:checkbox>
                </td>
            </tr>

            <tr>
                <td colspan="8">
                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.8 Orthosis Calipers/Splint (Lower Extremity) </font>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.HKAFO</font>
                </td>
                <td>
                    <html:checkbox property="hkafo" value="HKAFO" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.KAFO</font>
                </td>
                <td>
                    <html:checkbox property="kafo" value="KAFO" style="width:25px">
                    </html:checkbox> </td></tr>
            <tr>
                <td>
                    <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii. AFO</font>
                </td>
                <td>
                    <html:checkbox property="afo" value="AFO" style="width:25px">
                    </html:checkbox> </td></tr>
            <tr>
                <td>
                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Knee Orthosis</font>
                </td>
                <td>
                    <html:checkbox property="kneeorthosis" value="Knee Orthosis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.DB Splint</font>
                </td>
                <td>
                    <html:checkbox property="dbsplint" value="DB Splint" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>

            <tr>
                <td>
                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Modified Shoe (Surgical Shoe)</font>
                </td>
                <td>
                    <html:checkbox property="modifiedshoe" value="Modified Shoe" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Serial Casting (For CTEV)</font>
                </td>
                <td>
                    <html:checkbox property="serialcasting" value="Serial Casting" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.9 Prosthesis (Lower Extremity)
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Hip Disarticulation Prosthesis
                </td>
                <td>
                    <html:checkbox property="hipprothesis" value="Hip Disarticulation Prosthesis" style="width:25px">
                    </html:checkbox>

                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Knee Prosthesis
                </td>
                <td>
                    <html:checkbox property="abovekneeprothesis" value="Above Knee Prosthesis" style="width:25px">
                    </html:checkbox>

                </td>
            </tr>

            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Knee Disarticulation Prosthesis
                </td>
                <td>
                    <html:checkbox property="kneedisarticulation" value="Knee Disarticulation Prosthesis" style="width:25px">
                    </html:checkbox>

                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Knee Prosthesis
                </td>
                <td>
                    <html:checkbox property="belowkneeprothesis" value="Below Knee Prosthesis" style="width:25px">
                    </html:checkbox>

                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  v.Symes Prosthesis
                </td>
                <td>
                    <html:checkbox property="symeprothesis" value="Symes Prosthesis" style="width:25px">
                    </html:checkbox>

                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Partial Foot Prosthesis
                </td>
                <td>
                    <html:checkbox property="partialfoodprothesis" value="Partial Foot Prosthesis" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.10 Spinal Orthosis
                </td>
            </tr>

            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  i.Cervical Collar
                </td>
                <td>
                    <html:checkbox property="cervicalcollar" value="Cervical Collar" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ii.LS Brace
                </td>
                <td>
                    <html:checkbox property="lsbrace" value="LS Brace" style="width:25px">
                    </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.TLSO Brace (For Scoliosis/ Kyphosis)
                </td>
                <td>
                    <html:checkbox property="tlsobrace" value="TLSO Brace" style="width:25px"></html:checkbox>
                </td>
            </tr>


            <tr>
                <td colspan="8">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.11 Any ADL Equipment
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Feeding
                </td>
                <td>
                    <html:checkbox property="feeding" value="Feeding" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Toileting/Bathing
                </td>
                <td>
                    <html:checkbox property="bathing" value="Toileting/Bathing" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Brushing

                </td>
                <td>
                    <html:checkbox property="brushing" value="Brushing" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Combing
                </td>
                <td>
                    <html:checkbox property="combing" value="Combing" style="width:25px"> </html:checkbox>
                </td> </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Dressing
                </td>
                <td>
                    <html:checkbox property="dressing" value="Dressing" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Writing
                </td>

                <td>
                    <html:checkbox property="writing" value="Writing" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Driving/Cycling
                </td>
                <td>
                    <html:checkbox property="drivingcycling" value="Driving/Cycling" style="width:25px"> </html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Bed Transfer

                </td>
                <td>
                    <html:checkbox property="bedtransfer" value="Bed Transfer" style="width:25px"></html:checkbox>
                </td>
            </tr>
            <tr>
                <td>
                    6. Any Other Physical Impairment/Locomotor Needs
                </td>
                <td> <html:textarea rows="4" cols="30" property="anyotherinlocomotor"  onkeydown="textCounter2()" onkeyup="textCounter2()"> </html:textarea>
                </td></tr>

            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />
                    <html:submit value="Update" styleId="phyUpdateButton" styleClass="button"/>
                    <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>
            </tr>
        </table>
    </html:form>

</BODY>
</html:html>
