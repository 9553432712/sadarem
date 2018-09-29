<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }

        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted'))
                return false;
            else{
                thisform.setAttribute('submitted','true');
                document.getElementById('ampButton').disabled = true;
            }
        }
    </script>

    <BODY ONLOAD="devi();">
        <script language="javascript" src="./DisabilityUITG/js/Amputation.js"></script>
    

    <html:form action="/Amputation.do?addAmputationdetails=addAmputationdetails" styleId="Amputation"
               onsubmit="return avoidDuplicateForm(this)">


        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <th colspan="8">3.&nbsp;&nbsp; ADD AMPUTATION</th>
            </tr>
            <tr>
                <th colspan="8">3.1 &nbsp;&nbsp;UPPER EXTREMITY AMPUTATION &nbsp;&nbsp;<font size="0">
                        (Please tick in the appropriate box(es))</font></th>
            </tr>

            <tr>
                <th>&nbsp;</th>
                <th><font size="2" >Right</font></th>
                <th><font size="2" >Left</font></th>
            </tr>
            <tr>
                <td > <font size="2">3.1.1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fore-quarter amputation</font></td>
                <td> <html:checkbox property="upper_fore_right"  value="100"  onclick="validatecheckboxupperright()"  /></td>
                <td> <html:checkbox property="upper_fore_left" value="100" onclick="validatecheckboxupperleft()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.2&nbsp;&nbsp;&nbsp; &nbsp;Shoulder disarticulation</font></td>
                <td> <html:checkbox property="upper_shoulder_right"  value="90" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_shoulder_left" value="90" onclick="validatecheckboxupperleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.3 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to upper one third of arm</font></td>
                <td> <html:checkbox property="upper_aboveelbowupper_right" value="85" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_aboveelbowupper_left" value="85" onclick="validatecheckboxupperleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.4 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to lower one third of forearm</font></td>
                <td> <html:checkbox property="upper_elbowlower_right" value="80" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_elbowlower_left" value="80"  onclick="validatecheckboxupperleft()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.5&nbsp;&nbsp;&nbsp; &nbsp;Elbow disarticulation</font></td>
                <td> <html:checkbox property="upper_elbowdis_right" value="75" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_elbowdis_left" value="75" onclick="validatecheckboxupperleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.6 &nbsp;&nbsp;&nbsp;&nbsp;Below elbow up to upper one third of forearm</font></td>
                <td> <html:checkbox property="upper_belowelbowupper_right" value="70" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_belowelbowupper_left" value="70"  onclick="validatecheckboxupperleft()"/></td>
            </tr>

            <tr>
                <td  > <font size="2">3.1.7&nbsp;&nbsp;&nbsp;&nbsp; Below elbow up to lower one third of forearm</font></td>
                <td> <html:checkbox property="upper_belowelbowlower_right" value="65" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_belowelbowlower_left" value="65" onclick="validatecheckboxupperleft()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.8&nbsp;&nbsp;&nbsp;&nbsp; Wrist disarticulation</font></td>
                <td> <html:checkbox property="upper_waistdis_right" value="60" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_waistdis_left" value="60"  onclick="validatecheckboxupperleft()"/></td>
            </tr>

            <tr>
                <td > <font size="2">3.1.9&nbsp;&nbsp;&nbsp;&nbsp; Hand through carpal bone</font></td>
                <td> <html:checkbox property="upper_handcarpel_right" value="55" onclick="validatecheckboxupperright()" /></td>
                <td> <html:checkbox property="upper_handcarpel_left" value="55" onclick="validatecheckboxupperleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.10&nbsp;&nbsp; Thumb through CM or through first MC joint</font></td>
                <td> <html:checkbox property="upper_thumbCM_right" value="30" onclick="validatecheckboxupperrightCMMCPIP()" /></td>
                <td> <html:checkbox property="upper_thumbCM_left" value="30"  onclick="validatecheckboxupperleftCMMCPIP()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.11 &nbsp;&nbsp;Thumb disarticulation through MCP joint or through proximal phalanx</font></td>
                <td> <html:checkbox property="upper_thumbMCP_right" value="25"  onclick="validatecheckboxupperrightCMMCPIP()"/></td>
                <td> <html:checkbox property="upper_thumbMCP_left" value="25" onclick="validatecheckboxupperleftCMMCPIP()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.12 &nbsp;&nbsp;Thumb disarticulation through IP joint or through distal phalanx</font></td>
                <td> <html:checkbox property="upper_thumbIP_right" value="15" onclick="validatecheckboxupperrightCMMCPIP()" /></td>
                <td> <html:checkbox property="upper_thumbIP_left" value="15" onclick="validatecheckboxupperleftCMMCPIP()" /></td>
            </tr>


            <tr>
                <td colspan="8"> <font size="2">3.1.13 &nbsp;&nbsp;Amputation through proximal phalanx or disarticulation through MP joint :</font></td>
            </tr>

            <tr>
                <th width="70%">&nbsp;</th>
                <th><font size="2">Right</font></th>
                <th><font size="2">Left</font></th>
            </tr>
            <tr>
                <td > <font size="2">3.1.13.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font></td>
                <td> <html:checkbox property="upper_MPIndex_right" value="15"  onclick="validatecheckboxupperrightfingers()"/></td>
                <td> <html:checkbox property="upper_MPIndex_left" value="15" onclick="validatecheckboxupperleftfingers()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.13.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font></td>
                <td> <html:checkbox property="upper_MPMiddle_right" value="5" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_MPMiddle_left" value="5" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.13.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font></td>
                <td> <html:checkbox property="upper_MPRing_right" value="3" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_MPRing_left" value="3" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.13.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font></td>
                <td> <html:checkbox property="upper_MPLittle_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_MPLittle_left" value="2" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>


            <tr>
                <td colspan="8"> <font size="2">3.1.14 &nbsp;&nbsp;Amputation through middle phalanx or disarticulation through PIP joint :</font></td>
            </tr>

            <tr>
               <th width="70%">&nbsp;</th>
                <th><font size="2">Right</font></th>
                <th><font size="2">Left</font></th>
            </tr>
            <tr>
                <td > <font size="2">3.1.14.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font></td>
                <td> <html:checkbox property="upper_PIPIndex_right" value="10" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_PIPIndex_left" value="10" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.14.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font></td>
                <td> <html:checkbox property="upper_PIPMiddle_right" value="4" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_PIPMiddle_left" value="4"  onclick="validatecheckboxupperleftfingers()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.14.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font></td>
                <td> <html:checkbox property="upper_PIPRing_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_PIPRing_left" value="2" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.14.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font></td>
                <td> <html:checkbox property="upper_PIPLittle_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_PIPLittle_left" value="1"  onclick="validatecheckboxupperleftfingers()" /></td>
            </tr>
            <tr>
                <td colspan="8"> <font size="2">3.1.15 &nbsp;&nbsp;Amputation through distal phalanx or disarticulation through DIP joint :</font></td>
            </tr>

            <tr>
               <th width="70%">&nbsp;</th>
                <th><font size="2">Right</font></th>
                <th><font size="2">Left</font></th>
            </tr>
            <tr>
                <td > <font size="2">3.1.15.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font>
                <td> <html:checkbox property="upper_DIPIndex_right" value="5"  onclick="validatecheckboxupperrightfingers()"/></td>
                <td> <html:checkbox property="upper_DIPIndex_left" value="5"  onclick="validatecheckboxupperleftfingers()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.15.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font>
                <td> <html:checkbox property="upper_DIPMiddle_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_DIPMiddle_left" value="2"  onclick="validatecheckboxupperleftfingers()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.15.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font>
                <td> <html:checkbox property="upper_DIPRing_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_DIPRing_left" value="1" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.1.15.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font>
                <td> <html:checkbox property="upper_DIPLittle_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
                <td> <html:checkbox property="upper_DIPLittle_left" value="1" onclick="validatecheckboxupperleftfingers()"  /></td>
            </tr>

            <tr>
                <th colspan="8">3.2&nbsp;&nbsp; LOWER EXTREMITY AMPUTAION<font size="0">(Please tick in the appropriate box(es))</font></th>
            </tr>

            <tr>
                <th width="70%">&nbsp;</th>
                <th><font size="2">Right</font></th>
                <th><font size="2">Left</font></th>
            </tr>
            <tr>
                <td > <font size="2">3.2.1 &nbsp;&nbsp;&nbsp;Hind quarter</font>
                <td> <html:checkbox property="lower_hind_right" value="100"  onclick="validatecheckboxlowerright()"/></td>
                <td> <html:checkbox property="lower_hind_left" value="100" onclick="validatecheckboxlowerleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.2 &nbsp;&nbsp;&nbsp;Hip disarticulation</font>
                <td> <html:checkbox property="lower_hip_right" value="90" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_hip_left" value="90"  onclick="validatecheckboxlowerleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.3&nbsp;&nbsp;&nbsp; Above knee upto upper 1/3 of thigh</font>
                <td> <html:checkbox property="lower_AKupper_right" value="85" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_AKupper_left" value="85" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.4 &nbsp;&nbsp;&nbsp;Above knee upto lower 1/3 of thigh </font>
                <td> <html:checkbox property="lower_AKlower_right" value="80" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_AKlower_left" value="80" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.5 &nbsp;&nbsp;&nbsp;Through knee</font>
                <td> <html:checkbox property="lower_truknee_right" value="75" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_truknee_left" value="75" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.6 &nbsp;&nbsp;&nbsp; BK upto 8cm</font>
                <td> <html:checkbox property="lower_bk8cm_right" value="70" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_bk8cm_left" value="70" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>

            <tr>
                <td > <font size="2">3.2.7 &nbsp;&nbsp;&nbsp;BK upto lower 1/3 of leg</font>
                <td> <html:checkbox property="lower_bklower_right" value="60" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_bklower_left" value="60" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.8&nbsp;&nbsp; &nbsp;Through Ankle</font>
                <td> <html:checkbox property="lower_truankle_right" value="55" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_truankle_left" value="55" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>

            <tr>
                <td > <font size="2">3.2.9 &nbsp;&nbsp;&nbsp;Syme's </font>
                <td> <html:checkbox property="lower_symes_right" value="50" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_symes_left" value="50" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.10 &nbsp;&nbsp;Upto mid foot</font>
                <td> <html:checkbox property="lower_uptomid_right" value="40" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_uptomid_left" value="40" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.11&nbsp;&nbsp; Upto fore foot</font>
                <td> <html:checkbox property="lower_uptofore_right" value="30" onclick="validatecheckboxlowerright()" /></td>
                <td> <html:checkbox property="lower_uptofore_left" value="30" onclick="validatecheckboxlowerleft()"  /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.12 &nbsp;&nbsp;All toes</font>
                <td> <html:checkbox property="lower_alltoe_right" value="20" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_alltoe_left" value="20"  onclick="validatetoeleft()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.13&nbsp;&nbsp; Loss of first toe</font>
                <td> <html:checkbox property="lower_1sttoe_right" value="10" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_1sttoe_left" value="10"  onclick="validatetoeleft()"/></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.14 &nbsp;&nbsp;Loss of second toe</font>
                <td> <html:checkbox property="lower_2ndtoe_right" value="5" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_2ndtoe_left" value="5" onclick="validatetoeleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.15 &nbsp;&nbsp;Loss of third toe</font>
                <td> <html:checkbox property="lower_3rdtoe_right" value="4" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_3rdtoe_left" value="4" onclick="validatetoeleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.16 &nbsp;&nbsp;Loss of fourth toe</font>
                <td> <html:checkbox property="lower_4thtoe_right" value="3" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_4thtoe_left" value="3" onclick="validatetoeleft()" /></td>
            </tr>
            <tr>
                <td > <font size="2">3.2.17 &nbsp;&nbsp;Loss of fifth toe</font>
                <td> <html:checkbox property="lower_5thtoe_right" value="2" onclick="validatetoeright()" /></td>
                <td> <html:checkbox property="lower_5thtoe_left" value="2" onclick="validatetoeleft()" /></td>
            </tr>

            <tr>
                <th colspan="8" >3.3&nbsp;&nbsp;COMPLICATIONS<font size="0">(Filling is compulsory for any kind of amputee)</font></th>
            </tr>


            <tr>
                <td> <font size="2">3.3.1 &nbsp;&nbsp;Is the stump unfit for fitting of Prosthesis?</font>
                </td>
                <td colspan="3"> <html:checkbox property="fitting_of_prosthesis" value="5"  style="width:25px"/></td>

            </tr>
            <tr>
                <td ><font size="2">3.3.2 &nbsp;&nbsp;Is there stiffness in the proximal joint?</font>
                </td>
                <td colspan="3"><html:select property="proximal_joint" style="width:50px">
                        <html:option styleId="prox" value="0">0</html:option>
                        <html:option  value="1">1</html:option>
                        <html:option  value="2">2</html:option>
                        <html:option  value="3">3</html:option>
                        <html:option  value="4">4</html:option>
                        <html:option  value="5">5</html:option>
                        <html:option  value="6">6</html:option>
                        <html:option  value="7">7</html:option>
                        <html:option  value="8">8</html:option>
                        <html:option  value="9">9</html:option>
                        <html:option  value="10">10</html:option>

                    </html:select> </td>

            </tr>
            <tr>
                <td ><font size="2">3.3.3&nbsp;&nbsp; Is there Neuroma?</font>
                </td>
                <td colspan="3"><html:select property="neuroma" style="width:50px">
                        <html:option styleId="neur" value="0">0</html:option>
                        <html:option  value="1">1</html:option>
                        <html:option  value="2">2</html:option>
                        <html:option  value="3">3</html:option>
                        <html:option  value="4">4</html:option>
                        <html:option  value="5">5</html:option>
                        <html:option  value="6">6</html:option>
                        <html:option  value="7">7</html:option>
                        <html:option  value="8">8</html:option>
                        <html:option  value="9">9</html:option>
                        <html:option  value="10">10</html:option>

                    </html:select> </td>

            </tr>

            <tr>
                <td ><font size="2">3.3.4 &nbsp;&nbsp;Is there infection? </font>
                </td>
                <td colspan="3"><html:select property="infection" style="width:50px">
                        <html:option styleId="inf" value="0" >0</html:option>
                        <html:option  value="1">1</html:option>
                        <html:option  value="2">2</html:option>
                        <html:option  value="3">3</html:option>
                        <html:option  value="4">4</html:option>
                        <html:option  value="5">5</html:option>
                        <html:option  value="6">6</html:option>
                        <html:option  value="7">7</html:option>
                        <html:option  value="8">8</html:option>
                        <html:option  value="9">9</html:option>
                        <html:option  value="10">10</html:option>

                    </html:select> </td>

            </tr>

            <tr>
                <td ><font size="2">3.3.5&nbsp;&nbsp;&nbsp;Is dominant upper extremity involved? </font>
                </td>

                <td colspan="4" > <html:checkbox property="dominant" value="4"  style="width:25px"/></td>

            </tr>
            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                    <html:submit  value="Add" styleClass="button" styleId="ampButton"/>
                    <html:button  property="" value="Reset" onclick="resetbutton()"  styleClass="button"/></th>
        </table>

    </html:form>
</BODY >

</html:html >