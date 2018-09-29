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
                boolean editFlag = false;
                if (session.getAttribute("disabilityEditFlag") != null) {

                    editFlag = (Boolean) session.getAttribute("disabilityEditFlag");
                }


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
                    //  document.getElementById("upper").removeAttribute("href");
                    //  document.getElementById("upper").style.color = "gray";
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
                    //    document.getElementById("lower").removeAttribute("href");
                    //    document.getElementById("lower").style.color = "gray";
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
                    //  document.getElementById("amputation").removeAttribute("href");
                    //  document.getElementById("amputation").style.color = "gray";
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
                    //     document.getElementById("transverse").removeAttribute("href");
                    //    document.getElementById("transverse").style.color = "gray";
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
                    //    document.getElementById("trunk").removeAttribute("href");
                    //    document.getElementById("trunk").style.color = "gray";
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
                    //   document.getElementById("evalution").removeAttribute("href");
                    //   document.getElementById("evalution").style.color = "gray";
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
                    // document.getElementById("cardio").removeAttribute("href");
                    //  document.getElementById("cardio").style.color = "gray";
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
                    //   document.getElementById("dwarfism").removeAttribute("href");
                    //   document.getElementById("dwarfism").style.color = "gray";
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

        X = screen.width;
        Y = screen.height;
        window.moveTo(0,0);
        window.resizeTo(X,Y)

    </script>

    <html:errors/>
    <BODY onload="disableLink();">

 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
 
    <style type="text/css">
h1{
page-break-before: always;
}
</style>

    <script language="JavaScript" src="./DisabilityUITG/js/partc.js"></script>

    <html:form action="/PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages" method="post">


        <% String message = (String) request.getAttribute("msg");%>
        <% if (message != null) {%> <font color="black"><center><%=message%> <% }%></center>
            <table align="center" cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
                </tr>
            </table><br/>
            <table  border="0" align="center" cellspacing="1" cellpadding="1" class="innerTable" width="100%" id="mohanPrint">
                <tr>
                    <td class="heading1"><center>Select The Appropriate Disability Link </center></td>
                </tr></table>
            <table  border="1" align="center" cellspacing="1" cellpadding="1" class="innerTable1" width="100%" id="printDis">
                <tr valign="top"><td class="labelBlue">



                <li>Physical Impairment Disabilities</li>
                </td>
                <%-- <td>&nbsp;</td> --%>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="SelectUpperExtreimityPrint.do?selectUpperExterimity=selectUpperExterimity" id="upper" onclick="disableLink();"><font size="2">Upper Extremity </li></a></font>
                            <div id="upperdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                    </td>
                    <%-- <td width="40%"><font size="3" color="red"><%out.println(upperextrimitytotal);%><br><br>
                     </td> --%>
                </tr>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="lowerextremityselectPrint.do?getLowerExtremityDetails=getLowerExtremityDetails" id="lower" onclick="disableLink();"><font size="2">Lower Extremity</a></font>
                            <div id="lowerdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                    </td>

                    <%-- <td width="40%"><font size="3" color="red"><%out.println(lowererextrimitytotal);%><br><br>
                     </td> --%>
                </tr>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="SelectAmputationDetailsPrint.do?getAmputationDetails=getAmputationDetails" id="amputation" onclick="disableLink();"><font size="2">Amputation</a></font>
                            <div id="amputationdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>

                    </td>

                    <%-- <td width="40%"><font size="3" color="red"><%out.println(amputationtotal);%><br><br>
                     </td> --%>
                </tr>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="getTransverseDetailsPrint.do?getTransverseDetails=getTransverseDetails" id="transverse" onclick="disableLink();"><font size="2">Congential Deficiencies</a></font>
                            <div id="transversedata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>

                    </td>

                    <%-- <td width="40%"><font size="3" color="red"><%out.println(transversetotal);%><br><br>
                     </td> --%>
                </tr>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="trunkselectPrint.do?getTrunkDetails=getTrunkDetails" id="trunk" onclick="disableLink();"><font size="2">Trunk</a></font>
                            <div id="trunkdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                    </td>

                    <%-- <td width="40%"><font size="3" color="red"><%out.println(trunktotal);%><br><br>
                     </td> --%>
                </tr>
                <tr>
                    <td width="40%"><ul><font size="3"color=#FF3300><li><a href="evaluationselectPrint.do?selectEvaluation=selectEvaluation" id="evalution" onclick="disableLink();"><font size="2">Physical Impairment due to <br>Neurological Conditions</a></font>
                                        <div id="evalutiondata" style="visibility:hidden;display:none">
                                            <font color="red" size="2">Data Entered</font>
                                        </div>
                                        </td>

                                        <%--  <td width="40%"><font size="3" color="red"><%out.println(Evaluationtotal);%><br><br>
                                          </td> --%>
                                        </tr>
                                        <tr>
                                            <td width="40%"><ul><font size="3"color=#FF3300><li><a href="getcardiopolumonaryPrint.do?getCardio=getCardio" id="cardio" onclick="disableLink();"><font size="2">Cardiopulmonary Diseases</a></font>
                                                    <div id="cardiodata" style="visibility:hidden;display:none">
                                                        <font color="red" size="2">Data Entered</font>
                                                    </div>
                                            </td>

                                            <%-- <td width="40%"><font size="3" color="red"><%out.println(cardiopulmonarytotal);%><br><br>
                                             </td> --%>
                                        </tr>
                                        <tr>
                                            <td width="40%"><ul><font size="3"color=#FF3300><li><a href="getdwarfismPrint.do?getDwarfismDetails=getDwarfismDetails" id="dwarfism" onclick="disableLink();"><font size="2">Dwarfism</a></font>
                                                    <div id="dwarfismdata" style="visibility:hidden;display:none">
                                                        <font color="red" size="2">Data Entered</font>
                                                    </div>
                                            </td>
                                            <%-- <td width="40%"><font size="3" color="red"><%out.println(dwarfismtotal);%></ul>
                                             </td> --%>
                                        </tr>

                                        </td>
                                        </table>

                                        <br>


                                                    <table  align="center" cellspacing="1" cellpadding="1" border="1" class="innerTable" width="100%">

                                                        <tr>
                                                            <td colspan="4" class="heading">Need Assessment/Referred/Recommended (Physical Impairment)</td>
                                                        </tr>

                                                        <tr>
                                                            <td class="heading">&nbsp;</td>
                                                            <td colspan="2" class="heading">Old Values</td>
                                                            <td colspan="2" class="heading">New Values</td>
                                                        </tr>

                                                        <tr>
                                                            <td colspan="4" class="label">1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
                                                        </tr>

                                                        <tr>
                                                            <td colspan="2" class="label"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Physiotherapy</td>
                                                            <td><html:checkbox property="physiotheraphy" value="physiotheraphy" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" disabled="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Occupational Therapy</td>
                                                            <td><html:checkbox property="occupationaltheraphy" value="occupationaltheraphy" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan"  disabled="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td   class="label">2. Surgery</td>
                                                            <td width="48%" colspan="2"><html:text property="surgery" maxlength="100" disabled="true"></html:text></td>
                                                            <td><input type="text"  name="mohan"  maxlength="4"  disabled="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" class="label">3. Physiotherapy</td>
                                                            <td><html:checkbox property="physiotherapy" value="physiotherapy" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan"  disabled="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" class="label">4. Occupational Therapy</td>
                                                            <td><html:checkbox property="occupationaltherapy" value="occupationaltherapy" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan"  disabled="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" class="label">5.  Assistive & Augmentative Devices For Locomotor Disability</td>
                                                        </tr>
                                                        <tr>
                                                            <td  colspan="2" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.1  Wheel Chair( Select Fields) </td>
                                                            <td>
                                                                <html:select property="selectwheelchair" disabled="true" multiple="multiple" style="height: 50px;">

                                                                    <html:option value="Large Wheel Chair">Large Wheel Chair</html:option>
                                                                    <html:option value="Small Wheel Chair">Small Wheel Chair</html:option>
                                                                </html:select></td>

                                                            <td>
                                                                <select name="mohan" disabled="true" multiple="multiple" style="height: 50px;">

                                                                    <option value="Large Wheel Chair">Large Wheel Chair</option>
                                                                    <option value="Small Wheel Chair">Small Wheel Chair</option>
                                                                </select></td>

                                                        </tr>
                                                        <tr>
                                                            <td colspan="2"  class="label"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.2  Tricycle ( Select Fields) </td>
                                                            <td>  <font size="2">
                                                                    <html:select property="selecttricycle"  disabled="true" multiple="multiple" style="height: 50px;">

                                                                        <html:option value="Large Tricycle">Large Tricycle</html:option>
                                                                        <html:option value="Small Tricycle">Small Tricycle</html:option></font> </html:select>
                                                                </td>
                                                                <td>  <font size="2">
                                                                        <select name="mohan"  disabled="true" multiple="multiple" style="height: 50px;">

                                                                            <option value="Large Tricycle">Large Tricycle</option>
                                                                            <option value="Small Tricycle">Small Tricycle</option></font>
                                                                    </select>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.3  Walking Stick &nbsp;&nbsp;&nbsp;&nbsp; (Select Fields) </td>
                                                                <td>  <font size="2">
                                                                    <html:select property="selectwalkingstick"    disabled="true" style="height: 50px;" multiple="multiple">
                                                                        <html:option value="Large Walking Stick">Large Walking Stick</html:option>
                                                                        <html:option value="Small Walking Stick">Small Walking Stick</html:option></font></html:select>
                                                                </td>

                                                                <td>  <font size="2">
                                                                        <select name="mohan"    disabled="true" style="height: 50px;" multiple="multiple">
                                                                            <option value="">Large Walking Stick</option>
                                                                            <option value="">Small Walking Stick</option></font></select>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  5.4  Crutches&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> <font size="1">( Select Fields) </td>
                                                                <td>  <font size="2">
                                                                    <html:select property="selectcrutches"   disabled="true" multiple="multiple" style="height: 50px;" >

                                                                        <html:option value="Small Crutches">Small Crutches</html:option>
                                                                        <html:option value="Medium Crutches">Medium Crutches</html:option>
                                                                        <html:option value="Large Crutches">Large Crutches</html:option>
                                                                        <html:option value="Extra Large Crutches">Extra Large Crutches</html:option>
                                                                    </font> </html:select>
                                                                    <font size="2">
                                                                    <html:select property="crutchestype"   disabled="true" multiple="multiple" style="height: 50px;" >

                                                                        <html:option value="Axillary">Axillary</html:option>
                                                                        <html:option value="Elbow">Elbow</html:option>
                                                                        <html:option value="Gutter">Gutter</html:option>
                                                                        <html:option value="Tripod">Tripod</html:option>
                                                                    </font> </html:select></td>


                                                                <td>  <font size="2">
                                                                        <select name="mohan"    disabled="true" style="height: 50px;" multiple="multiple">

                                                                            <option value="Small Crutches">Small Crutches</option>
                                                                            <option value="Medium Crutches">Medium Crutches</option>
                                                                            <option value="Large Crutches">Large Crutches</option>
                                                                            <option value="Extra Large Crutches">Extra Large Crutches</option>
                                                                    </font> </select>
                                                                    <font size="2">
                                                                        <select name="mohan"    disabled="true" style="height: 50px;" multiple="multiple">

                                                                            <option value="Axillary">Axillary</option>
                                                                            <option value="Elbow">Elbow</option>
                                                                            <option value="Gutter">Gutter</option>
                                                                            <option value="Tripod">Tripod</option>
                                                                    </font> </select></td>


                                                            </tr>
                                                            <tr>
                                                                <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="2"> 5.5  Walking Frame&nbsp;&nbsp;&nbsp;&nbsp;</font><font size="1">( Select Fields) </td>
                                                                <td width="48%" colspan="2">  <font size="2">
                                                                        <select name="mohan"    disabled="true" style="height: 50px;" multiple="multiple">

                                                                            <option value="Small WalkingFrame">Small Walking Frame</option>
                                                                            <option value="Large WalkingFrame">Large Walking Frame</option></font></select>
                                                                </td>
                                                                <td  >  <font size="2">
                                                                        <select name="mohan"    disabled="true" style="height: 50px;" multiple="multiple">

                                                                            <option value="Small WalkingFrame">Small Walking Frame</option>
                                                                            <option value="Large WalkingFrame">Large Walking Frame</option></font></select>
                                                                </td>

                                                            </tr>
                                                            <tr> <td colspan="3" class="label">
                                                                    <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp; 5.6 Orthosis Calipers/Splint for UpperExtremity </font>
                                                                </td></tr>
                                                            <tr> <td colspan="2" class="label">
                                                                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Aeroplane Splint </font>
                                                                </td>
                                                                <td><html:checkbox property="aeroplanesplint" value="Aeroplane Splint" disabled="true"> </html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label">
                                                                <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Figure-8 Splint</font>
                                                            </td> <td><html:checkbox property="figure8splint" value="Figure-8 Splint" disabled="true"></html:checkbox> </td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label">
                                                                <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Fore Arm Splint</font>
                                                            </td> <td><html:checkbox property="forearmsplint" value="ForeArm Splint" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr><td colspan="2" class="label">
                                                                <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Hand Splint</font></td>
                                                            <td><html:checkbox property="handsplint" value="Hand Splint" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr>  <td colspan="3" class="label"> <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.7 Upper Extremity Prosthesis </font>
                                                            </td> </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Shoulder Prosthesis </font>
                                                            </td><td><html:checkbox property="shoulderprosthesis" value="Shoulder Prosthesis" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Elbow  Prosthesis </font>
                                                            </td><td><html:checkbox property="aboveelbowprosthesis" value="Above Elbow  Prosthesis" disabled="true"> </html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Elbow Disarticulation Prosthesis </font>
                                                            </td><td><html:checkbox property="elbowdisarticulationprosthesis" value="Elbow Disarticulation Prosthesis" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Elbow  Prosthesis </font>
                                                            </td> <td><html:checkbox property="belowelbowprosthesis" value="Below Elbow  Prosthesis" disabled="true"> </html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Wrist Disarticulation Prosthesis </font>
                                                            </td> <td><html:checkbox property="wristdisarticulationprosthesis" value="Wrist Disarticulation Prosthesis" disabled="true"> </html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                                        </tr>
                                                        <tr> <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Hand Prosthesis </font>
                                                            </td><td><html:checkbox property="handprosthesis" value="Hand Prosthesis" disabled="true"></html:checkbox></td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                                        <tr>  <td colspan="2" class="label"> <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Cosmetic Finger Prosthesis </font>
                                                            </td><td><html:checkbox property="cosmeticfingerprosthesis" value="Cosmetic Finger Prosthesis" disabled="true"></html:checkbox>
                                                            </td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                                        <tr>
                                                            <td colspan="3" class="label">
                                                                <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.8 Orthosis Calipers/Splint (Lower Extremity) </font>
                                                            </td></tr>
                                                        <tr>
                                                            <td colspan="2" class="label">
                                                                <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.HKAFO</font>
                                                            </td>
                                                            <td>
                                                                <html:checkbox property="hkafo" value="HKAFO" disabled="true" >
                                                                </html:checkbox>
                                                            </td>
                                                            <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.KAFO</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="kafo" value="KAFO" disabled="true">
                                                    </html:checkbox> </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii. AFO</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="afo" value="AFO" disabled="true">
                                                    </html:checkbox> </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Knee Orthosis</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="kneeorthosis" value="Knee Orthosis" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.DB Splint</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="dbsplint" value="DB Splint" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>

                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Modified Shoe (Surgical Shoe)</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="modifiedshoe" value="Modified Shoe" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Serial Casting (For CTEV)</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="serialcasting" value="Serial Casting" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="3" class="label">
                                                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.9 Prosthesis (Lower Extremity)</font>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Hip Disarticulation Prosthesis</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="hipprothesis" value="Hip Disarticulation Prosthesis" disabled="true">
                                                    </html:checkbox>

                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Above Knee Prosthesis </font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="abovekneeprothesis" value="Above Knee Prosthesis" disabled="true">
                                                    </html:checkbox>

                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>

                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Knee Disarticulation Prosthesis </font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="kneedisarticulation" value="Knee Disarticulation Prosthesis" disabled="true">
                                                    </html:checkbox>

                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Below Knee Prosthesis </font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="belowkneeprothesis" value="Below Knee Prosthesis" disabled="true">
                                                    </html:checkbox>

                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  v.Symes Prosthesis </font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="symeprothesis" value="Symes Prosthesis" disabled="true">
                                                    </html:checkbox>

                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Partial Foot Prosthesis </font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="partialfoodprothesis" value="Partial Foot Prosthesis" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="3" class="label">
                                                    <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.10 Spinal Orthosis </font>
                                                </td></tr>

                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  i.Cervical Collar</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="cervicalcollar" value="Cervical Collar" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ii.LS Brace</font>
                                                </td>
                                                <td>
                                                    <html:checkbox property="lsbrace" value="LS Brace" disabled="true">
                                                    </html:checkbox>
                                                </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="label">
                                                    <font size="2">     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.TLSO Brace (For Scoliosis/ Kyphosis)</font>
                                                </td> <td><html:checkbox property="tlsobrace" value="TLSO Brace" disabled="true"></html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>
                                            </tr>


                                            <tr> <td colspan="3" class="label"> <font size="2">   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5.11 Any ADL Equipment </font>
                                                </td></tr>
                                            <tr> <td colspan="2" class="label"><font size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Feeding </font>
                                                </td><td><html:checkbox property="feeding" value="Feeding" disabled="true"></html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr> <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Toileting/Bathing </font>
                                                </td><td><html:checkbox property="bathing" value="Toileting/Bathing" disabled="true"></html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr> <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Brushing </font>
                                                </td><td><html:checkbox property="brushing" value="Brushing" disabled="true"> </html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr> <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Combing </font>
                                                </td> <td><html:checkbox property="combing" value="Combing" disabled="true"> </html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr>  <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Dressing </font>
                                                </td><td><html:checkbox property="dressing" value="Dressing" disabled="true"></html:checkbox> </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr> <td colspan="2" class="label"> <font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Writing </font>
                                                </td><td> <html:checkbox property="writing" value="Writing" disabled="true"> </html:checkbox> </td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td></tr>

                                            <tr>  <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Driving/Cycling</font>
                                                </td>   <td> <html:checkbox property="drivingcycling" value="Driving/Cycling" disabled="true"> </html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                            </tr>
                                            <tr>  <td colspan="2" class="label"><font size="2">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Bed Transfer </font>
                                                </td><td><html:checkbox property="bedtransfer" value="Bed Transfer" disabled="true"></html:checkbox></td>
                                                <td><input type="checkbox" name="mohan" value="4" disabled="true"/></td>
                                            </tr>
                                            <tr>  <td colspan="2" class="label"><font size="2"> 6. Any Other Physical Impairment/Locomotor Needs</font> </td>
                                                <td> <html:textarea rows="4" cols="30" property="anyotherinlocomotor"  onkeydown="textCounter2()" onkeyup="textCounter2()" disabled="true"> </html:textarea>
                                                </td>
                                                <td><TEXTAREA NAME="mohan" COLS=30 ROWS=4></TEXTAREA>

                                                </td>
                                            </tr>
                                        </table>

                                        <br>
                                    </html:form>
                                    <form action="">
                                        <TABLE align="center">
                                            <tr>
                                                <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button" /></td>
                                                <td><html:button property="" value="Print" onclick="prints()" styleClass="button"/></td>
                                                <td><html:button property="" value="Reset" onclick="printsDis()" styleClass="button"/></td>
                                            </tr>
                                        </TABLE>

                                    </form>

                                    </BODY>
                                    <script>

                                        function goURL()
                                        {
                                            document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
                                            document.forms[0].submit();
                                        }

                                        function prints() {
                                            document.getElementById("printDis").style.display="none";
                                            document.getElementById("mohanPrint").style.display="none";
                                            window.print();
                                        }
                                        function printsDis() {
                                            document.getElementById("printDis").style.display="";
                                            document.getElementById("mohanPrint").style.display="";

                                        }



                                    </script>
                                </html:html>
