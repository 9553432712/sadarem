<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*,org.bf.disability.dao.*,org.bf.disability.dto.TerritoryDTO" %>


<%
            Double hearingimpairment = 0.0;
            Double visualimpairment = 0.0;
            Double mentalretardation = 0.0;
            Double mentalillness = 0.0;
            Double totalphysical = 0.0;
            Double maxValue = 0.0;

            TerritoryDTO territoryDTO = (TerritoryDTO) request.getAttribute("territoryDTO");
            int disabilityId = ((Integer) session.getAttribute("disabilityid")).intValue();
            if (disabilityId == 1) {
                totalphysical = (Double) new Double(territoryDTO.getTotalforphysical());
                // session.setAttribute("totallist1", arry1);
            } else if (disabilityId == 2) {
                visualimpairment = (Double) new Double(territoryDTO.getVisual_Impairment());
            } else if (disabilityId == 3) {
                hearingimpairment = (Double) new Double(territoryDTO.getHearing_Percentage());
            } else if (disabilityId == 4) {
                mentalretardation = (Double) new Double(territoryDTO.getMental_Retardation_Total());
            } else if (disabilityId == 5) {
                mentalillness = (Double) new Double(territoryDTO.getMental_Disability_Total());
            } else if (disabilityId == 6) {
                totalphysical = (Double) new Double(territoryDTO.getTotalforphysical());
                visualimpairment = (Double) new Double(territoryDTO.getVisual_Impairment());
                hearingimpairment = (Double) new Double(territoryDTO.getHearing_Percentage());
                mentalretardation = (Double) new Double(territoryDTO.getMental_Retardation_Total());
                mentalillness = (Double) new Double(territoryDTO.getMental_Disability_Total());
            }
            maxValue = (Double) request.getAttribute("maxValue");
            request.getSession().setAttribute("territoryDTOtemp", territoryDTO);

            /*  LinkedList link = new LinkedList();
            link.addAll(arry);
            calculateResult c = new calculateResult();
            double res = Math.round(c.needCalculation(link));
            if (res > 100) {
            res = 100;
            }
            session.setAttribute("total", new Double(res));*/

            String maxMultiplevalue = (String) session.getAttribute("mutiple");
//boolean editFlag = (Boolean)session.getAttribute("disabilityEditFlag");

            boolean editFlag = false;
            if (session.getAttribute("disabilityEditFlag") != null) {
                editFlag = (Boolean) session.getAttribute("disabilityEditFlag");
            }


            double hearingimpairmenttotal = 0;
            double visualimpairmenttotal = 0;
            double mentalretardationtotal = 0;
            double mentalillnesstotal = 0;
            double totalphysicalimpairment = 0;
            if (totalphysical != null) {
                totalphysicalimpairment = Math.round(totalphysical.doubleValue());
            }
            if (hearingimpairment != null) {
                hearingimpairmenttotal = Math.round(hearingimpairment.doubleValue());
            }
            if (visualimpairment != null) {
                visualimpairmenttotal = Math.round(visualimpairment.doubleValue());
            }
            if (mentalretardation != null) {
                mentalretardationtotal = Math.round(mentalretardation.doubleValue());
            }
            if (mentalillness != null) {
                mentalillnesstotal = Math.round(mentalillness.doubleValue());
            }
            /*
            double upperextrimitytotal=Math.round(upperextrimity.doubleValue());
            double lowererextrimitytotal=Math.round(lowererextrimity.doubleValue());
            double amputationtotal=Math.round(amputation.doubleValue());
            double transversetotal=Math.round(transverse.doubleValue());
            double trunktotal=Math.round( trunk.doubleValue());
            double Evaluationtotal=Math.round(Evaluation.doubleValue());
            double cardiopulmonarytotal=Math.round(cardiopulmonary.doubleValue());
            double dwarfismtotal=Math.round(dwarfism.doubleValue());
             */

//double totaldisability=0;
%>


<script language="JavaScript">
    function radio_button_checker()
    { document.getElementById("hideTR").style.display='none';
        var radio_choice = false;
        if(!territoryform.multipleRadio.checked){

            for (counter = 0; counter < territoryform.multipleRadio.length; counter++)
            {
                if (territoryform.multipleRadio[counter].checked)
                    radio_choice = true;
            }

            if (!radio_choice)
            {
                alert("Please select radio button for Maximum MultipleDisability");
                document.getElementById("hideTR").style.display='';
                return false;
            }
        }
       
        return true;
    }


    function disableLink(){
        var locomotorValue='<%=totalphysicalimpairment%>';
        var hearingimpairment='<%=hearingimpairment%>';
        var visualimpairment='<%=visualimpairment%>';
        var mentalretardation='<%=mentalretardation%>';
        var mentalillness='<%=mentalillness%>';
        var editStatus = '<%=editFlag%>';
        if(null != locomotorValue){
            if((locomotorValue > 0.0 && editStatus == "false")){
                document.getElementById("locomotor").removeAttribute("href");
                document.getElementById("locomotor").style.color = "gray";
            }
            if(null != document.getElementById("locomotordata")){
                if(locomotorValue > 0.0){
                    document.getElementById("locomotordata").style.visibility = "Visible";
                    document.getElementById("locomotordata").style.display = "Inline";
                }else{
                    document.getElementById("locomotordata").style.visibility = "hidden";
                    document.getElementById("locomotordata").style.display = "none";
                }
            }
        }

        if(null != hearingimpairment){
            if((hearingimpairment > 0.0 && editStatus == "false")){
                document.getElementById("hearing").removeAttribute("href");
                document.getElementById("hearing").style.color = "gray";
            }
            if(null != document.getElementById("hearingdata")){
                if(hearingimpairment > 0.0){
                    document.getElementById("hearingdata").style.visibility = "Visible";
                    document.getElementById("hearingdata").style.display = "Inline";
                }else{
                    document.getElementById("hearingdata").style.visibility = "hidden";
                    document.getElementById("hearingdata").style.display = "none";
                }
            }
        }

        if(null != visualimpairment){
            if((visualimpairment > 0.0 && editStatus == "false")){
                document.getElementById("visual").removeAttribute("href");
                document.getElementById("visual").style.color = "gray";
            }
            if(null != document.getElementById("visualdata")){
                if(visualimpairment > 0.0){
                    document.getElementById("visualdata").style.visibility = "Visible";
                    document.getElementById("visualdata").style.display = "Inline";
                }else{
                    document.getElementById("visualdata").style.visibility = "hidden";
                    document.getElementById("visualdata").style.display = "none";
                }
            }
        }
        if(null != mentalretardation){
            if((mentalretardation > 0.0 && editStatus == "false")){
                document.getElementById("mentalretardation").removeAttribute("href");
                document.getElementById("mentalretardation").style.color = "gray";
            }
            if(null != document.getElementById("mentalretardationdata")){
                if(mentalretardation > 0.0){
                    document.getElementById("mentalretardationdata").style.visibility = "Visible";
                    document.getElementById("mentalretardationdata").style.display = "Inline";
                }else{
                    document.getElementById("mentalretardationdata").style.visibility = "hidden";
                    document.getElementById("mentalretardationdata").style.display = "none";
                }
            }
        }

        if(null != mentalillness){
            if((mentalillness > 0.0 && editStatus == "false")){
                document.getElementById("mentalillness").removeAttribute("href");
                document.getElementById("mentalillness").style.color = "gray";
            }
            if(null != document.getElementById("mentalillnessdata")){
                if(mentalillness > 0.0){
                    document.getElementById("mentalillnessdata").style.visibility = "Visible";
                    document.getElementById("mentalillnessdata").style.display = "Inline";
                }else{
                    document.getElementById("mentalillnessdata").style.visibility = "hidden";
                    document.getElementById("mentalillnessdata").style.display = "none";
                }
            }
        }
               
    }

    function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        thisform.setAttribute('submitted','true');
    }
    
</script>


<html:html>
    <html:errors/>
    <BODY onload="disableLink();">
    
    <html:form action="updateTotalDisability.do" styleId="territoryform" onsubmit="return avoidDuplicateForm(this)">
        <br>
       <p id="succmsg"> <% String message = (String) request.getAttribute("msg");%>
        <% if (message != null) {%><%=message%> <% }%></p>
       <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="80%" border="0">
                <tr>
                    <th>Select The Appropriate Disability Link (TO UPDATE DISABILITY)</th>
                </tr>
                         <tr><td>
                        <logic:equal name="disabilityIdForm" property="disabilityId" value="1">
                            <input type="hidden" name="disabilityId" value="1"/>
                            <font size="3"color=#FF3300><li>
                                    <a href="Locomotorneedsselect.do?getLocomotorneeds=getLocomotorneeds"
                                       id="locomotor" onclick="disableLink();">
                                            Locomotor Disability</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <div id="locomotordata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                            <%-- <%out.println(totalphysicalimpairment);%> --%>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- <font size="2" color="blue">Total Disability : </font> <%out.println(res);%> --%>
                        </logic:equal>
                        <logic:equal name="disabilityIdForm"property="disabilityId" value="3">
                            <input type="hidden" name="disabilityId" value="3">
                            <font size="3"color=#FF3300><li><a href="hearingselect.do?selectHearing=selectHearing" id="hearing" onclick="disableLink();"><font size="2">Hearing Impairment</a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- <%out.println(hearingimpairmenttotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             <font size="2" color="blue">Total Disability : </font><%out.println(res);%> --%>
                            <div id="hearingdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                        </logic:equal>
                        <logic:equal name="disabilityIdForm"property="disabilityId" value="2">
                            <input type="hidden" name="disabilityId" value="2">
                            <font size="3"color=#FF3300><li><a href="getvisualimpairment.do?getVisualImpairment=getVisualImpairment" id="visual" onclick="disableLink();"><font size="2">Visual Impairment</a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- <%out.println(visualimpairmenttotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             <font size="2" color="blue">Total Disability : </font><%out.println(res);%> --%>
                            <div id="visualdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                        </logic:equal>
                        <logic:equal name="disabilityIdForm"property="disabilityId" value="4">
                            <input type="hidden" name="disabilityId" value="4">
                            <font size="3"color=#FF3300><li><a href="getMentalRetardationIqvalues.do?getMentalRetardationIQValues=getMentalRetardationIQValues" id="mentalretardation" onclick="disableLink();"><font size="2">Mental Retardation</a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- <%out.println(mentalretardationtotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <font size="2" color="blue">Total Disability : </font><%out.println(res);%> --%>
                            <div id="mentalretardationdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                        </logic:equal>
                        <logic:equal name="disabilityIdForm"property="disabilityId" value="5">
                            <%--    <input type="hidden" name="disabilityId" value="5">  --%>
                            <font size="3"color=#FF3300><li><a href="getmentalillness.do" id="mentalillness" onclick="disableLink();"><font size="2">Mental Illness</a></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%--  <%out.println(mentalillnesstotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <font size="2" color="blue">Total Disability : </font><%out.println(res);%> --%>
                            <div id="mentalillnessdata" style="visibility:hidden;display:none">
                                <font color="red" size="2">Data Entered</font>
                            </div>
                        </logic:equal>
                        <logic:equal name="disabilityIdForm"property="disabilityId" value="6">
                            <input type="hidden" name="disabilityId" value="6">

                            <table>
                                <% ArrayList multipleList = (ArrayList) session.getAttribute("multipleDisabilityList");
                                            for (int i = 0; i < multipleList.size(); i++) {
                                                String id = (String) multipleList.get(i);
                                                if ("1".equals(id)) {
                                %>

                                <tr>
                                    <td>
                                        <%if ((maxValue.doubleValue() == totalphysicalimpairment) && totalphysicalimpairment != 0) {
                                                    if (maxMultiplevalue != null && !"null".equals(maxMultiplevalue) && maxMultiplevalue.equals("1")) {%>
                                        <input type="radio" name="multipleRadio"  value="1" checked="true" />
                                        <%} else {%>
                                        <html:radio property="multipleRadio"  value="1" styleId="1" ></html:radio> <% }
                                                }%>
                                        <br>  </td>
                                    <td width="50%">

                                        <font size="3"color=#FF3300><a href="Locomotorneedsselect.do?getLocomotorneeds=getLocomotorneeds" id="locomotor" onclick="disableLink();"><font size="2">
                                                    Locomotor Disability</li></a></font><br><br>
                                    </td>
                                    <td>
                                        <font size="3"color=red>  <%out.println(totalphysicalimpairment);%> </font>
                                        <br><br></td>

                                </tr>
                                <%}
                                    if ("3".equals(id)) {%>
                                <tr>
                                    <td>
                                        <%if ((Math.round(maxValue.doubleValue()) == hearingimpairmenttotal) && hearingimpairmenttotal != 0) {
                                                if (maxMultiplevalue != null && !"null".equals(maxMultiplevalue) && maxMultiplevalue.equals("3")) {%>
                                        <input type="radio" name="multipleRadio"  value="3" checked="true"/>
                                        <%} else {%>
                                        <html:radio property="multipleRadio"  value="3" styleId="3"></html:radio> <%}
                                            }%>
                                        <br>   </td>
                                    <td width="50%">

                                        <font size="3"color=#FF3300><a href="hearingselect.do?selectHearing=selectHearing" id="hearing" onclick="disableLink();"><font size="2">
                                                    Hearing Impairment</a></font>
                                        <br><br>  </td>
                                    <td>
                                        <font size="3"color=red>   <%out.println(hearingimpairmenttotal);%></font>
                                        <br>  <br> </td>
                                </tr>
                                <%}
                                    if ("2".equals(id)) {%>
                                <tr>
                                    <td>
                                        <%if ((maxValue.doubleValue() == visualimpairmenttotal) && visualimpairmenttotal != 0) {
                                             if (maxMultiplevalue != null && !"null".equals(maxMultiplevalue) && maxMultiplevalue.equals("2")) {%>
                                        <input type="radio" name="multipleRadio"  value="2" checked="true" />
                                        <%} else {%>
                                        <html:radio property="multipleRadio"  value="2" styleId="2" ></html:radio> <% }
                                         }%>
                                        <br>   </td>
                                    <td width="50%">

                                        <font size="3"color=#FF3300><a href="getvisualimpairment.do?getVisualImpairment=getVisualImpairment" id="visual" onclick="disableLink();"><font size="2">
                                                    Visual Impairment</a></font>
                                        <br><br>  </td>

                                    <td>
                                        <font size="3"color=red> <%out.println(visualimpairmenttotal);%></font>
                                        <%--   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="2" color="blue">Total Disability : </font><%out.println(res);%> --%>
                                        <br>  <br> </td>
                                </tr>
                                <%}
                                    if ("4".equals(id)) {%>
                                <tr>
                                    <td>
                                        <%if ((maxValue.doubleValue() == mentalretardationtotal) && mentalretardationtotal != 0) {
                                             if (maxMultiplevalue != null && !"null".equals(maxMultiplevalue) && maxMultiplevalue.equals("4")) {%>
                                        <input type="radio" name="multipleRadio"  value="4" checked="true" />
                                        <%} else {%>
                                        <html:radio property="multipleRadio"  value="4" styleId="4"></html:radio> <%}
                                         }%>
                                        <br>    </td>
                                    <td width="50%">

                                        <font size="3"color=#FF3300><a href="getMentalRetardationIqvalues.do?getMentalRetardationIQValues=getMentalRetardationIQValues" id="mentalretardation" onclick="disableLink();"><font size="2">
                                                    Mental Retardation</a></font>
                                        <br><br>    </td>
                                    <td>
                                        <font size="3"color=red><%out.println(mentalretardationtotal);%></font>
                                        <br>     <br> </td>
                                </tr>
                                <%}
                                    if ("5".equals(id)) {%>
                                <tr>
                                    <td>
                                        <%if ((maxValue.doubleValue() == mentalillnesstotal) && mentalillnesstotal != 0) {
                                              if (maxMultiplevalue != null && !"null".equals(maxMultiplevalue) && maxMultiplevalue.equals("5")) {%>
                                        <input type="radio" name="multipleRadio"  value="5" checked="true" />
                                        <%} else {%>
                                        <html:radio property="multipleRadio"  value="5" styleId="5"></html:radio> <%}
                                          }%>
                                        <br>  </td>
                                    <td width="50%">
                                        <font size="3"color=#FF3300><a href="getmentalillness.do" id="mentalillness" onclick="disableLink();"><font size="2">
                                                    Mental Illness</a></font>
                                        <br><br>  </td>
                                    <td>
                                        <font size="3"color=red><%out.println(mentalillnesstotal);%> </font>
                                        <br><br> </td>
                                </tr>
                                <%}
                      }%>

                                <tr>
                                    <td >

                                    </td>
                                    <td>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                            </table>
                        </logic:equal>
                    </td></tr>
           <tr id="hideTR"><th colspan="6">
                        <input type="Submit" value="Next" onclick="return radio_button_checker()" class="button"></th>
                </tr></table>
            </html:form>

    </BODY>
</html:html>
