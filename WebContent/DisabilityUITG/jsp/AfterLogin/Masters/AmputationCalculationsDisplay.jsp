<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
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
<html:html>
    <body><br>
    <form>
        <logic:notEmpty name="amputationBean" scope="request" >
            <%   boolean valEqualsOne = false;%>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                    <th class="heading" align="center">3.&nbsp;&nbsp;AMPUTATION</th>
                </tr>

                 <tr>
                     <td colspan="3"  align="right" class="label"><b>ID No:&nbsp;<font color="blue"><%=personcode%></font></b></td></tr>
                 <tr><td colspan="3"  align="right" class="label"><b>Name:&nbsp;<font color="blue"><%=name%></font></b></td></tr>


            </table>
            <logic:equal scope="request" property="upperExtrmHeading" value="true" name="amputationBean">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <th class="labelBlue"><font size="3">3.1 &nbsp;&nbsp;UPPER EXTREMITY AMPUTATION</font></th>
                    </tr>
                </table>
                <logic:equal scope="request" property="upper1to12totalHeading" value="true" name="amputationBean">
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td width="50%">&nbsp;</td>
                            <td width="10%" class="labelBlue"><font size="2" >Right</font></td>
                            <td width="40%" class="labelBlue"><font size="2" >Left</font></td>
                        </tr>

                        <logic:equal name="amputationBean" property="upper_fore_right" value="100"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_fore_left" value="100"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fore-quarter amputation</font></td>
                            <td align="left"><logic:equal scope="request" property="upper_fore_right" value="100" name="amputationBean"> <bean:write property="upper_fore_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_fore_left" value="100" name="amputationBean"><bean:write property="upper_fore_left" name="amputationBean" /> </td></logic:equal>
                        </tr>
                        <% } valEqualsOne= false;%>


                        <logic:equal name="amputationBean" property="upper_shoulder_right" value="90"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_shoulder_left" value="90"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.2&nbsp;&nbsp;&nbsp; &nbsp;Shoulder disarticulation</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_shoulder_right" value="90" name="amputationBean"><bean:write property="upper_shoulder_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_shoulder_left" value="90" name="amputationBean"><bean:write property="upper_shoulder_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_aboveelbowupper_right" value="85"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_aboveelbowupper_left" value="85"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.3 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to upper one third of arm</font></td>
                            <td align="left"><logic:equal scope="request" property="upper_aboveelbowupper_right" value="85" name="amputationBean"> <bean:write property="upper_aboveelbowupper_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"><logic:equal scope="request" property="upper_aboveelbowupper_left" value="85" name="amputationBean"> <bean:write property="upper_aboveelbowupper_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_elbowlower_right" value="80"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_elbowlower_left" value="80"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.4 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to lower one third of forearm</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_elbowlower_right" value="80" name="amputationBean"><bean:write property="upper_elbowlower_right" name="amputationBean" /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_elbowlower_left" value="80" name="amputationBean"><bean:write property="upper_elbowlower_left" name="amputationBean" /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_elbowdis_right" value="75"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_elbowdis_left" value="75"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.5&nbsp;&nbsp;&nbsp; &nbsp;Elbow disarticulation</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_elbowdis_right" value="75" name="amputationBean"><bean:write property="upper_elbowdis_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"><logic:equal scope="request" property="upper_elbowdis_left" value="75" name="amputationBean"> <bean:write property="upper_elbowdis_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_belowelbowupper_right" value="100"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_belowelbowupper_left" value="100"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.6 &nbsp;&nbsp;&nbsp;&nbsp;Below elbow up to upper one third of forearm</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_belowelbowupper_right" value="100" name="amputationBean"><bean:write property="upper_belowelbowupper_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"><logic:equal scope="request" property="upper_belowelbowupper_left" value="100" name="amputationBean"> <bean:write property="upper_belowelbowupper_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_belowelbowlower_right" value="65"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_belowelbowlower_left" value="65"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.7&nbsp;&nbsp;&nbsp;&nbsp; Below elbow up to lower one third of forearm</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_belowelbowlower_right" value="65" name="amputationBean"><bean:write property="upper_belowelbowlower_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_belowelbowlower_left" value="65" name="amputationBean"><bean:write property="upper_belowelbowlower_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_waistdis_right" value="65"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_waistdis_left" value="65"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.8&nbsp;&nbsp;&nbsp;&nbsp; Wrist disarticulation</font></td>
                            <td align="left"><logic:equal scope="request" property="upper_waistdis_right" value="60" name="amputationBean"> <bean:write property="upper_waistdis_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_waistdis_left" value="60" name="amputationBean"><bean:write property="upper_waistdis_left" name="amputationBean"   /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_handcarpel_right" value="55"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_handcarpel_left" value="55"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.9&nbsp;&nbsp;&nbsp;&nbsp; Hand through carpal bone</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_handcarpel_right" value="55" name="amputationBean"><bean:write property="upper_handcarpel_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_handcarpel_left" value="55" name="amputationBean"><bean:write property="upper_handcarpel_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_thumbCM_right" value="30"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_thumbCM_left" value="30"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.10&nbsp;&nbsp; Thumb through CM or through first MC joint</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_thumbCM_right" value="30" name="amputationBean"><bean:write property="upper_thumbCM_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_thumbCM_left" value="30" name="amputationBean"><bean:write property="upper_thumbCM_left" name="amputationBean"   /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_thumbMCP_right" value="55"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_thumbMCP_left" value="55"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.11 &nbsp;&nbsp;Thumb disarticulation through MCP joint or through proximal phalanx</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_thumbMCP_right" value="55" name="amputationBean"><bean:write property="upper_thumbMCP_right" name="amputationBean"   /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_thumbMCP_left" value="55" name="amputationBean"><bean:write property="upper_thumbMCP_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_thumbIP_right" value="15"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_thumbIP_left" value="15"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.12 &nbsp;&nbsp;Thumb disarticulation through IP joint or through distal phalanx</font></td>
                            <td align="left"><logic:equal scope="request" property="upper_thumbIP_right" value="15" name="amputationBean"> <bean:write property="upper_thumbIP_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_thumbIP_left" value="15" name="amputationBean"><bean:write property="upper_thumbIP_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                    </table>
                </logic:equal>


                <logic:equal scope="request" property="mpJointHeading" value="true" name="amputationBean">

                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td align="left"> <font size="2"><b>3.1.13 &nbsp;&nbsp;Amputation through proximal phalanx or disarticulation through MP joint :</b></font></td>
                        </tr>
                    </table>
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td width="50%">&nbsp;</td>
                            <td width="10%" class="labelBlue"><font size="2">Right</font></td>
                            <td width="40%" class="labelBlue"><font size="2">Left</font></td>
                        </tr>

                        <logic:equal name="amputationBean" property="upper_MPIndex_right" value="15"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_MPIndex_left" value="15"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.13.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font></td>
                            <td align="left"><logic:equal scope="request" property="upper_MPIndex_right" value="15" name="amputationBean"> <bean:write property="upper_MPIndex_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"><logic:equal scope="request" property="upper_MPIndex_left" value="15" name="amputationBean"> <bean:write property="upper_MPIndex_left" name="amputationBean" /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_MPMiddle_right" value="5"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_MPMiddle_left" value="5"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.13.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_MPMiddle_right" value="5" name="amputationBean"><bean:write property="upper_MPMiddle_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_MPMiddle_left" value="5" name="amputationBean"><bean:write property="upper_MPMiddle_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_MPRing_right" value="3"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_MPRing_left" value="3"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.13.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_MPRing_right" value="3" name="amputationBean"><bean:write property="upper_MPRing_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_MPRing_left" value="3" name="amputationBean"><bean:write property="upper_MPRing_left" name="amputationBean" /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_MPLittle_right" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_MPLittle_left" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.13.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_MPLittle_right" value="2" name="amputationBean"><bean:write property="upper_MPLittle_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_MPLittle_left" value="2" name="amputationBean"><bean:write property="upper_MPLittle_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                    </table>
                </logic:equal>


                <logic:equal scope="request" property="pipJointHeading" value="true" name="amputationBean">
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td align="left"> <font size="2"><b>3.1.14 &nbsp;&nbsp;Amputation through middle phalanx or disarticulation through PIP joint :</b></font></td>
                        </tr>
                    </table>
                    <table width="100%" border="0" align="center"  >
                        <tr align="left">
                            <td width="50%">&nbsp;</td>
                            <td width="10%"><font size="2">Right</font></td>
                            <td width="40%"><font size="2">Left</font></td>
                        </tr>

                        <logic:equal name="amputationBean" property="upper_PIPIndex_right" value="10"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_PIPIndex_left" value="10"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.14.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPIndex_right" value="10" name="amputationBean"><bean:write property="upper_PIPIndex_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPIndex_left" value="10" name="amputationBean"><bean:write property="upper_PIPIndex_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_PIPMiddle_right" value="4"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_PIPMiddle_left" value="4"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.14.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPMiddle_right" value="4" name="amputationBean"><bean:write property="upper_PIPMiddle_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPMiddle_left" value="4" name="amputationBean"><bean:write property="upper_PIPMiddle_left" name="amputationBean"   /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_PIPRing_right" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_PIPRing_left" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.14.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPRing_right" value="2" name="amputationBean"><bean:write property="upper_PIPRing_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPRing_left" value="2" name="amputationBean"><bean:write property="upper_PIPRing_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_PIPLittle_right" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_PIPLittle_left" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.14.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font></td>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPLittle_right" value="1" name="amputationBean"><bean:write property="upper_PIPLittle_right" name="amputationBean" /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_PIPLittle_left" value="1" name="amputationBean"><bean:write property="upper_PIPLittle_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                    </table>
                </logic:equal>
                <logic:equal scope="request" property="dipJointHeading" value="true" name="amputationBean">
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td align="left"> <font size="2"><b>3.1.15 &nbsp;&nbsp;Amputation through distal phalanx or disarticulation through DIP joint :</b></font></td>
                        </tr>
                    </table>
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                        <tr align="left">
                            <td width="50%">&nbsp;</td>
                            <td width="10%"><font size="2" class="labelBlue">Right</font></td>
                            <td width="40%"><font size="2" class="labelBlue">Left</font></td>
                        </tr>

                        <logic:equal name="amputationBean" property="upper_DIPIndex_right" value="5"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_DIPIndex_left" value="5"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.15.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</font>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPIndex_right" value="5" name="amputationBean"><bean:write property="upper_DIPIndex_right" name="amputationBean"   /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPIndex_left" value="5" name="amputationBean"><bean:write property="upper_DIPIndex_left" name="amputationBean"   /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_DIPMiddle_right" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_DIPMiddle_left" value="2"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.15.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</font>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPMiddle_right" value="2" name="amputationBean"><bean:write property="upper_DIPMiddle_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPMiddle_left" value="2" name="amputationBean"><bean:write property="upper_DIPMiddle_left" name="amputationBean"   /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_DIPRing_right" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_DIPRing_left" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.15.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</font>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPRing_right" value="1" name="amputationBean"><bean:write property="upper_DIPRing_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPRing_left" value="1" name="amputationBean"><bean:write property="upper_DIPRing_left" name="amputationBean" /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>

                        <logic:equal name="amputationBean" property="upper_DIPLittle_right" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <logic:equal name="amputationBean" property="upper_DIPLittle_left" value="1"><%  valEqualsOne = true;%></logic:equal>
                        <%  if (valEqualsOne) {  %>
                        <tr align="left">
                            <td align="left"> <font size="2">3.1.15.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</font>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPLittle_right" value="1" name="amputationBean"><bean:write property="upper_DIPLittle_right" name="amputationBean"  /></td></logic:equal>
                            <td align="left"> <logic:equal scope="request" property="upper_DIPLittle_left" value="1" name="amputationBean"><bean:write property="upper_DIPLittle_left" name="amputationBean"  /></td></logic:equal>
                        </tr>
                        <% }valEqualsOne= false; %>
                    </table>
                </logic:equal>
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td align="left" colspan="3" rowspan="1"> -----------------------------------------------------------------------------------------------------------------</font></td>
                    </tr>
                    </table>
                    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left" >
                        <td align="left" width="50%" class="labelBlue"> Total</font></td>
                        <td align="left" width="10%"><bean:write property="upperRightTotal" name="amputationBean"  /> </td>
                        <td align="left" width="40%" rowspan="1" colspan="1"> <bean:write property="upperLeftTotal" name="amputationBean"  /></td>
                    </tr>
                </table>

                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr>
                        <td align="left" > <pre><bean:write property="amputationStrBuffUE" name="amputationBean"  /></pre></td>
                    </tr>
                </table>
            </logic:equal>

            <logic:equal scope="request" property="lowerExtrmHeading" value="true" name="amputationBean">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <th class="labelBlue"><font size="3">3.2&nbsp;&nbsp; LOWER EXTREMITY AMPUTAION </font></th>
                    </tr>
                </table>

                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td width="50%">&nbsp;</td>
                        <td width="10%"><font size="2" class="labelBlue">Right</font></td>
                        <td width="40%"><font size="2" class="labelBlue">Left</font></td>
                    </tr>
                    <logic:equal name="amputationBean" property="lower_hind_right" value="100"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_hind_left" value="100"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.1 &nbsp;&nbsp;&nbsp;Hind quarter</font>
                        <td align="left"> <logic:equal scope="request" property="lower_hind_right" value="100" name="amputationBean"><bean:write property="lower_hind_right" name="amputationBean"   /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_hind_left" value="100" name="amputationBean"><bean:write property="lower_hind_left" name="amputationBean" /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_hip_right" value="90"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_hip_left" value="90"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.2 &nbsp;&nbsp;&nbsp;Hip disarticulation</font>
                        <td align="left"> <logic:equal scope="request" property="lower_hip_right" value="90" name="amputationBean"><bean:write property="lower_hip_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_hip_left" value="90" name="amputationBean"><bean:write property="lower_hip_left" name="amputationBean"   /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_AKupper_right" value="85"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_AKupper_left" value="85"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.3&nbsp;&nbsp;&nbsp; Above knee upto upper 1/3 of thigh</font>
                        <td align="left"> <logic:equal scope="request" property="lower_AKupper_right" value="85" name="amputationBean"><bean:write property="lower_AKupper_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_AKupper_left" value="85" name="amputationBean"><bean:write property="lower_AKupper_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_AKlower_right" value="80"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_AKlower_left" value="80"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.4 &nbsp;&nbsp;&nbsp;Above knee upto lower 1/3 of thigh </font>
                        <td align="left"> <logic:equal scope="request" property="lower_AKlower_right" value="80" name="amputationBean"><bean:write property="lower_AKlower_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_AKlower_left" value="80" name="amputationBean"><bean:write property="lower_AKlower_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_truknee_right" value="75"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_truknee_left" value="75"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.5 &nbsp;&nbsp;&nbsp;Through knee</font>
                        <td align="left"> <logic:equal scope="request" property="lower_truknee_right" value="75" name="amputationBean"><bean:write property="lower_truknee_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_truknee_left" value="75" name="amputationBean"><bean:write property="lower_truknee_left" name="amputationBean" /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_bk8cm_right" value="100"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_bk8cm_left" value="100"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.6 &nbsp;&nbsp;&nbsp; BK upto 8cm</font>
                        <td align="left"> <logic:equal scope="request" property="lower_bk8cm_right" value="100" name="amputationBean"><bean:write property="lower_bk8cm_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_bk8cm_left" value="100" name="amputationBean"><bean:write property="lower_bk8cm_left" name="amputationBean" /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_bklower_right" value="60"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_bklower_left" value="60"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.7 &nbsp;&nbsp;&nbsp;BK upto lower 1/3 of leg</font>
                        <td align="left"> <logic:equal scope="request" property="lower_bklower_right" value="60" name="amputationBean"><bean:write property="lower_bklower_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_bklower_left" value="60" name="amputationBean"><bean:write property="lower_bklower_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_truankle_right" value="55"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_truankle_left" value="55"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.8&nbsp;&nbsp; &nbsp;Through Ankle</font>
                        <td align="left"> <logic:equal scope="request" property="lower_truankle_right" value="55" name="amputationBean"><bean:write property="lower_truankle_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_truankle_left" value="55" name="amputationBean"><bean:write property="lower_truankle_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_symes_right" value="50"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_symes_left" value="50"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.9 &nbsp;&nbsp;&nbsp;Syme's </font>
                        <td align="left"> <logic:equal scope="request" property="lower_symes_right" value="50" name="amputationBean"><bean:write property="lower_symes_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_symes_left" value="50" name="amputationBean"><bean:write property="lower_symes_left" name="amputationBean"/></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_uptomid_right" value="40"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_uptomid_left" value="40"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.10 &nbsp;&nbsp;Upto mid foot</font>
                        <td align="left"> <logic:equal scope="request" property="lower_uptomid_right" value="40" name="amputationBean"><bean:write property="lower_uptomid_right" name="amputationBean" /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_uptomid_left" value="40" name="amputationBean"><bean:write property="lower_uptomid_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_uptofore_right" value="30"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_uptofore_left" value="30"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.11&nbsp;&nbsp; Upto fore foot</font>
                        <td align="left"> <logic:equal scope="request" property="lower_uptofore_right" value="30" name="amputationBean"><bean:write property="lower_uptofore_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_uptofore_left" value="30" name="amputationBean"><bean:write property="lower_uptofore_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_alltoe_right" value="20"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_alltoe_left" value="20"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.12 &nbsp;&nbsp;All toes</font>
                        <td align="left"> <logic:equal scope="request" property="lower_alltoe_right" value="20" name="amputationBean"><bean:write property="lower_alltoe_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_alltoe_left" value="20" name="amputationBean"><bean:write property="lower_alltoe_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_1sttoe_right" value="10"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_1sttoe_left" value="10"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.13&nbsp;&nbsp; Loss of first toe</font>
                        <td align="left"> <logic:equal scope="request" property="lower_1sttoe_right" value="10" name="amputationBean"><bean:write property="lower_1sttoe_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_1sttoe_left" value="10" name="amputationBean"><bean:write property="lower_1sttoe_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_2ndtoe_right" value="5"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_2ndtoe_left" value="5"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.14 &nbsp;&nbsp;Loss of second toe</font>
                        <td align="left"> <logic:equal scope="request" property="lower_2ndtoe_right" value="5" name="amputationBean"><bean:write property="lower_2ndtoe_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_2ndtoe_left" value="5" name="amputationBean"><bean:write property="lower_2ndtoe_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_3rdtoe_right" value="4"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_3rdtoe_left" value="4"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.15 &nbsp;&nbsp;Loss of third toe</font>
                        <td align="left"> <logic:equal scope="request" property="lower_3rdtoe_right" value="4" name="amputationBean"><bean:write property="lower_3rdtoe_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_3rdtoe_left" value="4" name="amputationBean"><bean:write property="lower_3rdtoe_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_4thtoe_right" value="3"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_4thtoe_left" value="3"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.16 &nbsp;&nbsp;Loss of fourth toe</font>
                        <td align="left"> <logic:equal scope="request" property="lower_4thtoe_right" value="3" name="amputationBean"><bean:write property="lower_4thtoe_right" name="amputationBean"  /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_4thtoe_left" value="3" name="amputationBean"><bean:write property="lower_4thtoe_left" name="amputationBean" /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>

                    <logic:equal name="amputationBean" property="lower_5thtoe_right" value="2"><%  valEqualsOne = true;%></logic:equal>
                    <logic:equal name="amputationBean" property="lower_5thtoe_left" value="2"><%  valEqualsOne = true;%></logic:equal>
                    <%  if (valEqualsOne) {  %>
                    <tr align="left">
                        <td align="left"> <font size="2">3.2.17 &nbsp;&nbsp;Loss of fifth toe</font>
                        <td align="left"> <logic:equal scope="request" property="lower_5thtoe_right" value="2" name="amputationBean"><bean:write property="lower_5thtoe_right" name="amputationBean" /></td></logic:equal>
                        <td align="left"> <logic:equal scope="request" property="lower_5thtoe_left" value="2" name="amputationBean"><bean:write property="lower_5thtoe_left" name="amputationBean"  /></td></logic:equal>
                    </tr>
                    <% }valEqualsOne= false; %>
                </table>
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td align="left" colspan="3" rowspan="1"> --------------------------------------------------------------------------------------------------------------------------</font></td>
                    </tr>
                    </table>
                     <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td align="left" width="50%" class="labelBlue"> Total</td>
                        <td align="left" width="10%"><bean:write property="lowerRightTotal" name="amputationBean"  /></td>
                        <td align="left" width="40%"><bean:write property="lowerLeftTotal" name="amputationBean"  /></td>
                    </tr>
                    </table>
                     <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr>
                        <td align="left" > <pre><bean:write property="amputationStrBuffLE" name="amputationBean"  /></pre></td>
                    </tr>
                </table>
            </logic:equal>


            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                <logic:equal scope="request" property="upperExtrmHeading" value="true" name="amputationBean"><%  valEqualsOne = true;%></logic:equal>
                <logic:equal scope="request" property="lowerExtrmHeading" value="true" name="amputationBean"><%  valEqualsOne = true;%></logic:equal>
                <%  if (valEqualsOne) {  %>
                <tr>
                <td align="left" > <pre><bean:write property="amputationStrBuffULE" name="amputationBean"  /></pre></td>
                <tr>
                <% }valEqualsOne= false; %>
            </table>


            <logic:equal scope="request" property="complicationsHeading" value="true" name="amputationBean">
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <th align="left" class="labelBlue"><font size="3">3.3&nbsp;&nbsp;COMPLICATIONS </font></th>
                    </tr>
                </table>
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <logic:equal scope="request" property="fitting_of_prosthesis" value="5" name="amputationBean">

                        <tr align="left">
                            <td align="left" width="50%"> <font size="2">3.3.1 &nbsp;&nbsp;Is the stump unfit for fitting of Prosthesis?</font>
                            </td>
                            <td align="left" width="50%"> <bean:write property="fitting_of_prosthesis"  name="amputationBean" /></td>
                        </tr>
                    </logic:equal>
                    <logic:notEqual scope="request" property="proximal_joint" value="0" name="amputationBean">
                        <tr align="left">
                            <td align="left" width="50%"><font size="2">3.3.2 &nbsp;&nbsp;Is there stiffness in the proximal joint?</font>
                            </td>
                            <td align="left" width="50%"> <bean:write property="proximal_joint"  name="amputationBean" /></td>
                        </tr>
                    </logic:notEqual>
                    <logic:notEqual scope="request" property="neuroma" value="0" name="amputationBean">
                        <tr align="left">
                            <td align="left" width="50%"><font size="2">3.3.3&nbsp;&nbsp; Is there Neuroma?</font>
                            </td>
                            <td align="left" width="50%"> <bean:write property="neuroma"  name="amputationBean" /></td>
                        </tr>
                    </logic:notEqual>
                    <logic:notEqual scope="request" property="infection" value="0" name="amputationBean">
                        <tr align="left">
                            <td align="left" width="50%"><font size="2">3.3.4 &nbsp;&nbsp;Is there infection? </font>
                            </td>
                            <td align="left" width="50%"><bean:write property="infection"  name="amputationBean" /> </td>
                        </tr>
                    </logic:notEqual>
                    <logic:equal scope="request" property="dominant" value="4" name="amputationBean">
                        <tr align="left">
                            <td align="left" width="50%"><font size="2">3.3.5&nbsp;&nbsp;&nbsp;Is dominant upper extremity involved? </font>
                            </td>
                            <td align="left" width="50%"> <bean:write property="dominant"  name="amputationBean" /></td>
                        </tr>
                    </logic:equal>
                </table>
                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td align="left"> --------------------------------------------------------------------------------------------------------------------------</font></td>
                    </tr>
                    </table>

                <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                    <tr align="left">
                        <td align="left" width="50%"> Total</font></td>
                        <td align="left" width="50%"><bean:write property="complicationstotal"  name="amputationBean" /> </td>
                    </tr>
                    <tr>
                        <td align="left" > <pre><bean:write property="amputationStrBuffCS" name="amputationBean" /> </pre></td>
                    </tr>
                </table>
            </logic:equal>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                <tr align="left">
                    <td align="left" colspan="3" rowspan="1"><pre><bean:write property="amputationStrBuffEx"  name="amputationBean" /> </pre></td>
                </tr>
                <tr align="left">
                    <td align="left" colspan="3" rowspan="1"><pre><bean:write property="amputationStrBuffTot"  name="amputationBean" /></pre> </td>
                </tr>

            </table>
        </logic:notEmpty>

        <BR><BR><BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=3&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
</form>

    </body>




</html:html>
   
