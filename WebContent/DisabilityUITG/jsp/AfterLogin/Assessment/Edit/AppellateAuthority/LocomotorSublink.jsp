<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="java.util.*,org.bf.disability.dao.*,org.bf.disability.form.DisabilityIdForm,org.bf.disability.dto.TerritoryDTO" %>
<%@ page session="true" %>
<html:html>
    <html:errors/>


    <script language="JavaScript">
        function radio_button_checker()
        {
            document.getElementById("hideTR").style.display='none';
            var radio_choice = false;
            if(!territoryform.multipleRadio.checked){

                for (counter = 0; counter < territoryform.multipleRadio.length; counter++)
                {
                    if (territoryform.multipleRadio[counter].checked)
                        radio_choice = true;
                }

                if (!radio_choice)
                {
                    alert("Please select radio button for Maximum MultipleDisability")
                    document.getElementById("hideTR").style.display='';
                    return false;
                }
            }
            
            return true;
        }

        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted'))
                return false;
            thisform.setAttribute('submitted','true');
        }
    
    </script>




    <BODY>
    
    <html:form action="TotalDisability.do" styleId="territoryform" onsubmit="return avoidDuplicateForm(this)">
      
       <p id="succmsg"> <% String message = (String) request.getAttribute("msg");%>
        <% if (message != null) {%> <b><%=message%> <% }%></b></p>
      
  


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
                    boolean editFlag = (Boolean) session.getAttribute("disabilityEditFlag");


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
                    String selectflow="";
                    if(request.getAttribute("selectedValue")!=null){
                        selectflow=request.getAttribute("selectedValue").toString();
                        session.setAttribute("selectflow", selectflow);
                        }else if(session.getAttribute("selectflow")!=null){
                            selectflow=session.getAttribute("selectflow").toString();
                                }
                                   %>
        <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="70%">
            <tr><th colspan="10">Select The below Disability Link (TO ADD DISABILITY)</td></tr>
            <tr>
                <td>
  <input type="hidden" name="selectFlow" id="selectFlow" value="<%=selectflow%>"/>

                    <logic:equal name="disabilityIdForm"property="disabilityId" value="1">
                        <input type="hidden" name="disabilityId" value="1"/>
                        <font size="3"color=#FF3300><b><li><a href="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages"><font size="2">
                                            <b>Locomotor/Physical Impairment/OH Disability</b></font></a></li> <%-- <%out.println(totalphysicalimpairment);%> --%>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <%-- <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%> --%>
                            </logic:equal>

                            <logic:equal name="disabilityIdForm"property="disabilityId" value="3">
                                <input type="hidden" name="disabilityId" value="3"/>
                                <font size="3"color=#FF3300><b><li><a href="HearingImpairmentForwdAction.do"><font size="2"><b>Hearing Impairment</a></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <%-- <%out.println(hearingimpairmenttotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                 <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%> --%>
                                            </logic:equal>
                                            <logic:equal name="disabilityIdForm"property="disabilityId" value="2">
                                                <input type="hidden" name="disabilityId" value="2"/>
                                                <font size="3"color=#FF3300><b><li><a href="VisualImpairmentForwdAction.do"><font size="2"><b>Visual Impairment</a></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <%-- <%out.println(visualimpairmenttotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                 <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%></b> --%> <br><br>
                                                            </logic:equal>
                                                            <logic:equal name="disabilityIdForm"property="disabilityId" value="4">
                                                                <input type="hidden" name="disabilityId" value="4"/>
                                                                <font size="3"color=#FF3300><b><li><a href="MentalRetardationForwdAction.do"><font size="2"><b>Mental Retardation</a></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                <%-- <%out.println(mentalretardationtotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                 <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%> --%>
                                                                            </logic:equal>
                                                                            <logic:equal name="disabilityIdForm"property="disabilityId" value="5">
                                                                                <font size="3"color=#FF3300><b><li><a href="MentalIllnessForwdAction.do"><font size="2"><b>Mental Illness</a></b></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                <%-- <%out.println(mentalillnesstotal);%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                 <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%> --%>
                                                                                            </logic:equal>

                                                                                            <logic:equal name="disabilityIdForm"property="disabilityId" value="6">
                                                                                                <input type="hidden" name="disabilityId" value="6"/>
                                                                                                <table>
                                                                                                    <% ArrayList multipleList = (ArrayList) session.getAttribute("multipleDisabilityList");
                                                                                                                for (int i = 0; i < multipleList.size(); i++) {
                                                                                                                    String id = (String) multipleList.get(i);
                                                                                                                    if ("1".equals(id)) {
                                                                                                    %>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <%if ((Math.round(maxValue.doubleValue()) == totalphysicalimpairment) && totalphysicalimpairment != 0) {%>
                                                                                                            <html:radio property="multipleRadio"  value="1" ></html:radio> <%}%>
                                                                                                            <br>  </td>
                                                                                                        <td width="50%">
                                                                                                            <font size="3"color=#FF3300><b><a href="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages"><font size="2">
                                                                                                                            <b>Locomotor/Physical Impairment/OH Disability</li></a></b></font>
                                                                                                                        <br><br>     </td>
                                                                                                                        <td>
                                                                                                                            <font size="3"color=red><b>   <%out.println(totalphysicalimpairment);%>  </b></font>
                                                                                                                            <br><br>  </td>
                                                                                                                        </tr>
                                                                                                                        <%}
                                                                                                                                                                                                                                            if ("3".equals(id)) {%>
                                                                                                                        <tr>
                                                                                                                            <td>
                                                                                                                                <%if ((Math.round(maxValue.doubleValue()) == hearingimpairmenttotal) && hearingimpairmenttotal != 0) {%>
                                                                                                                                <html:radio property="multipleRadio"  value="3" ></html:radio> <%}%>
                                                                                                                                <br>  </td>
                                                                                                                            <td width="50%">
                                                                                                                                <font size="3"color=#FF3300><b><a href="HearingImpairmentForwdAction.do"><font size="2">
                                                                                                                                                <b>Hearing Impairment</a></b></font>
                                                                                                                                            <br><br>    </td>
                                                                                                                                            <td>
                                                                                                                                                <font size="3"color=red><b>   <%out.println(hearingimpairmenttotal);%></b></font>
                                                                                                                                                <br><br>   </td>
                                                                                                                                            </tr>
                                                                                                                                            <%}
                                                                                                                                                                                                                                                                if ("2".equals(id)) {%>
                                                                                                                                            <tr>
                                                                                                                                                <td>
                                                                                                                                                    <%if ((Math.round(maxValue.doubleValue()) == visualimpairmenttotal) && visualimpairmenttotal != 0) {%>
                                                                                                                                                    <html:radio property="multipleRadio"  value="2" ></html:radio> <%}%>
                                                                                                                                                    <br>  </td>
                                                                                                                                                <td width="50%">
                                                                                                                                                    <font size="3"color=#FF3300><b><a href="VisualImpairmentForwdAction.do"><font size="2">
                                                                                                                                                                    <b>Visual Impairment</a></b></font>
                                                                                                                                                                <br><br>    </td>
                                                                                                                                                                <td>
                                                                                                                                                                    <font size="3"color=red><b>  <%out.println(visualimpairmenttotal);%></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                                                                                    <%--    <font size="2" color="blue"><b>Total Disability : </font><%out.println(res);%></b> --%>
                                                                                                                                                                    <br><br>  </td>
                                                                                                                                                                </tr>
                                                                                                                                                                <%}
                                                                                                                                                                                                                                                                                    if ("4".equals(id)) {%>
                                                                                                                                                                <tr>
                                                                                                                                                                    <td>
                                                                                                                                                                        <%if ((Math.round(maxValue.doubleValue()) == mentalretardationtotal) && mentalretardationtotal != 0) {%>
                                                                                                                                                                        <html:radio property="multipleRadio"  value="4" ></html:radio> <%}%>
                                                                                                                                                                        <br>  </td>
                                                                                                                                                                    <td width="50%">
                                                                                                                                                                        <font size="3"color=#FF3300><b><a href="MentalRetardationForwdAction.do"><font size="2">
                                                                                                                                                                                        <b>Mental Retardation</a></b></font>
                                                                                                                                                                                    <br><br>     </td>
                                                                                                                                                                                    <td>
                                                                                                                                                                                        <font size="3"color=red><b>      <%out.println(mentalretardationtotal);%></b></font>
                                                                                                                                                                                        <br><br> </td>
                                                                                                                                                                                    </tr>
                                                                                                                                                                                    <%}
                                                                                                                                                                                                                                                                                                        if ("5".equals(id)) {%>
                                                                                                                                                                                    <tr>
                                                                                                                                                                                        <td>
                                                                                                                                                                                            <%if ((Math.round(maxValue.doubleValue()) == mentalillnesstotal) && mentalillnesstotal != 0) {%>
                                                                                                                                                                                            <html:radio property="multipleRadio"  value="5" ></html:radio> <%}%>

                                                                                                                                                                                            <br>  </td>
                                                                                                                                                                                        <td width="50%">
                                                                                                                                                                                            <font size="3"color=#FF3300><b><a href="MentalIllnessForwdAction.do"><font size="2">
                                                                                                                                                                                                            <b>Mental Illness</a></b></font>
                                                                                                                                                                                                        <br><br>    </td>
                                                                                                                                                                                                        <td>
                                                                                                                                                                                                            <font size="3"color=red><b>         <%out.println(mentalillnesstotal);%>  </b></font>
                                                                                                                                                                                                            <br><br>    </td>
                                                                                                                                                                                                        </tr>
                                                                                                                                                                                                        <%}
                                                                                                                                                                                                                    }%>

                                                                                                                                                                                                        </table>

                                                                                                                                                                                                    </logic:equal>
                                                                                                                                                                                                  <tr bgcolor="white" id="hideTR">
                                                                                                                                                                                                      <th colspan="10"  align="center">
                                                                                                                                                                                                            <input type="Submit" value="Next"  onclick="return radio_button_checker()"  class="button">
                                                                                                                                                                                                        </th>
                                                                                                                                                                                                    </tr>
                                                                                                                                                                                                  

                                                                                                                                                                                                    </table>
                                                                                                                                                                                                </html:form>

                                                                                                                                                                                                </BODY>
                                                                                                                                                                                            </html:html>
