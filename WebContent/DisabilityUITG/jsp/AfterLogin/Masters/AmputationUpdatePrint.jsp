<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>

<html:html locale="true">
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }

        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted'))
                return false;
            thisform.setAttribute('submitted','true');
        }

    </script>
    <script>
	function disableForm(theform) {
		if (document.all || document.getElementById) {
			for (i = 0; i < theform.length; i++) {
			var formElement = theform.elements[i];
				if (true) {
					formElement.disabled = true;
				}
			}
		}
	}
</script>
<style type="text/css">

/* GRID Starts */

.gridbg1 {
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:16px; padding-left:5px; padding-right:5px; padding:2px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:11px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:11px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #000 solid; border-top:1px #000 solid;
}

/* GRID Ends */
</style>

    <BODY ONLOAD="disableForm(amp);">
        <script language="javascript" src="./DisabilityUITG/js/Amputation.js"></script>
    

    <html:form action="/SelectAmputationDetailsPrint.do" styleId="amp">
       <table align="center" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table>
       <table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td width="50%" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
      <tr>
        <td colspan="5" align="left" class="gridhdbg">3. Amputation &quot;Tick the appropriate box (es)&quot;</td>
      </tr>
      <tr>
        <td width="70%" class="gridhdbg" style="text-align:left;">3.1 Upper Extremely Amputation :</td>
        <td width="15%" class="gridhdbg">Old Values Right</td>
        <td width="15%" class="gridhdbg">Old Values Left</td>
        <td width="15%" class="gridhdbg">New Values Right</td>
        <td width="15%" class="gridhdbg">New Values Left</td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fore-quarter amputation</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_fore_right"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_fore_left" value="100" onclick="validatecheckboxupperleft()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.2&nbsp;&nbsp;&nbsp; &nbsp;Shoulder disarticulation</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_shoulder_right"  value="90" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_shoulder_left" value="90" onclick="validatecheckboxupperleft()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.3 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to upper one third of arm</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_aboveelbowupper_right" value="100" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_aboveelbowupper_left" value="100" onclick="validatecheckboxupperleft()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.4 &nbsp;&nbsp;&nbsp;&nbsp;Above elbow up to lower one third of forearm</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_elbowlower_right" value="80" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_elbowlower_left" value="80"  onclick="validatecheckboxupperleft()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.5&nbsp;&nbsp;&nbsp; &nbsp;Elbow disarticulation</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_elbowdis_right" value="75" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_elbowdis_left" value="75" onclick="validatecheckboxupperleft()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.6 &nbsp;&nbsp;&nbsp;&nbsp;Below elbow up to upper one third of forearm</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_belowelbowupper_right" value="70" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_belowelbowupper_left" value="70"  onclick="validatecheckboxupperleft()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1"> 3.1.7&nbsp;&nbsp;&nbsp;&nbsp; Below elbow up to lower one third of forearm</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_belowelbowlower_right" value="65" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_belowelbowlower_left" value="65" onclick="validatecheckboxupperleft()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1"> 3.1.8&nbsp;&nbsp;&nbsp;&nbsp; Wrist disarticulation</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_waistdis_right" value="60" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_waistdis_left" value="60"  onclick="validatecheckboxupperleft()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.9&nbsp;&nbsp;&nbsp;&nbsp; Hand through carpal bone</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_handcarpel_right" value="55" onclick="validatecheckboxupperright()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_handcarpel_left" value="55" onclick="validatecheckboxupperleft()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.10&nbsp;&nbsp; Thumb through C.M. or through first MC joint</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbCM_right" value="30" onclick="validatecheckboxupperrightCMMCPIP()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbCM_left" value="30"  onclick="validatecheckboxupperleftCMMCPIP()"/></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.11 &nbsp;&nbsp;Thumb disarticulation through MCP joint or through proximal phalanx</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbMCP_right" value="25"  onclick="validatecheckboxupperrightCMMCPIP()"/></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbMCP_left" value="25" onclick="validatecheckboxupperleftCMMCPIP()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td align="left" class="gridbg1">3.1.12 &nbsp;&nbsp;Thumb disarticulation through IP joint or through distal phalanx</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbIP_right" value="15" onclick="validatecheckboxupperrightCMMCPIP()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_thumbIP_left" value="15" onclick="validatecheckboxupperleftCMMCPIP()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan2"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td colspan="5" align="left" class="gridbg2"><font size="2">3.1.13 &nbsp;&nbsp;Amputation through proximal phalanx or disarticulation through MP joint :</font></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.13.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPIndex_right" value="15"  onclick="validatecheckboxupperrightfingers()"/></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPIndex_left" value="15" onclick="validatecheckboxupperleftfingers()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.13.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPMiddle_right" value="5" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPMiddle_left" value="5" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.13.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPRing_right" value="3" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPRing_left" value="3" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.13.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPLittle_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_MPLittle_left" value="2" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td colspan="5" align="left" class="gridbg2">3.1.14 &nbsp;&nbsp;Amputation through middle phalanx or disarticulation through PIP joint :</td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.14.&nbsp;a)&nbsp;&nbsp;&nbsp;Index finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPIndex_right" value="10" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPIndex_left" value="10" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.14.&nbsp;b)&nbsp;&nbsp;&nbsp;Middle finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPMiddle_right" value="4" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPMiddle_left" value="4"  onclick="validatecheckboxupperleftfingers()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.14.&nbsp;c)&nbsp;&nbsp;&nbsp;Ring finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPRing_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPRing_left" value="2" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.14.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger</td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPLittle_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_PIPLittle_left" value="1"  onclick="validatecheckboxupperleftfingers()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan3"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr>
        <td colspan="5" align="left" class="gridbg2"><span class="labelBlue">3.1.15 &nbsp;&nbsp;Amputation through distal phalanx or disarticulation through DIP joint :</span></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.15. a) Index finger </td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPIndex_right" value="5"  onclick="validatecheckboxupperrightfingers()"/></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPIndex_left" value="5"  onclick="validatecheckboxupperleftfingers()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.15. b) Middle finger </td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPMiddle_right" value="2" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPMiddle_left" value="2"  onclick="validatecheckboxupperleftfingers()" /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.15. c) Ring finger </td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPRing_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPRing_left" value="1" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
      <tr class="innerTable1">
        <td align="left" class="gridbg1" style="text-align:right;">3.1.15.&nbsp;d)&nbsp;&nbsp;&nbsp;Little finger </td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPLittle_right" value="1" onclick="validatecheckboxupperrightfingers()" /></td>
        <td align="left" class="gridbg1"><html:checkbox property="upper_DIPLittle_left" value="1" onclick="validatecheckboxupperleftfingers()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
        <td align="left" class="gridbg1"><input type ="checkbox" name="mohan4"  value="100"  onclick="validatecheckboxupperright()"  /></td>
      </tr>
    </table></td>
    <td width="50%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
          <tr>
            <td width="70%" class="gridhdbg" style="text-align:left;">3.2&nbsp;&nbsp; LOWER EXTREMITY AMPUTAION(Please tick in the appropriate box(es))</td>
            <td width="15%" class="gridhdbg">Old Values Right</td>
            <td width="15%" class="gridhdbg">Old Values Left</td>
            <td width="15%" class="gridhdbg">New Values Right</td>
            <td width="15%" class="gridhdbg">New Values Left</td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.1 &nbsp;&nbsp;&nbsp;Hind quarter </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_hind_right" value="100"  onclick="validatecheckboxlowerright()"/></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_hind_left" value="100" onclick="validatecheckboxlowerleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.2 &nbsp;&nbsp;&nbsp;Hip disarticulation </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_hip_right" value="90" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_hip_left" value="90"  onclick="validatecheckboxlowerleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.3&nbsp;&nbsp;&nbsp; Above knee upto upper 1/3 of thigh </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_AKupper_right" value="100" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_AKupper_left" value="100" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.4 &nbsp;&nbsp;&nbsp;Above knee upto lower 1/3 of thigh </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_AKlower_right" value="80" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_AKlower_left" value="80" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.5 &nbsp;&nbsp;&nbsp;Through knee </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_truknee_right" value="75" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_truknee_left" value="75" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.6 &nbsp;&nbsp;&nbsp;BK upto 8cm </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_bk8cm_right" value="70" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_bk8cm_left" value="70" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.7 &nbsp;&nbsp;&nbsp;BK upto lower 1/3 of leg </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_bklower_right" value="60" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_bklower_left" value="60" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.8&nbsp;&nbsp; &nbsp;Through Ankle </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_truankle_right" value="55" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_truankle_left" value="55" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.9 &nbsp;&nbsp;&nbsp;Syme's </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_symes_right" value="50" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_symes_left" value="50" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.10 &nbsp;&nbsp;Upto mid foot </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_uptomid_right" value="40" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_uptomid_left" value="40" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"><font size="2">3.2.11&nbsp;&nbsp; Upto fore foot</font></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_uptofore_right" value="30" onclick="validatecheckboxlowerright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_uptofore_left" value="30" onclick="validatecheckboxlowerleft()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.12 &nbsp;&nbsp;All toes </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_alltoe_right" value="20" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_alltoe_left" value="20"  onclick="validatetoeleft()"/></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.13&nbsp;&nbsp; Loss of first toe </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_1sttoe_right" value="10" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_1sttoe_left" value="10"  onclick="validatetoeleft()"/></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.14 &nbsp;&nbsp;Loss of second toe </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_2ndtoe_right" value="5" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_2ndtoe_left" value="5" onclick="validatetoeleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.15 &nbsp;&nbsp;Loss of third toe </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_3rdtoe_right" value="4" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_3rdtoe_left" value="4" onclick="validatetoeleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1"> 3.2.16 &nbsp;&nbsp;Loss of fourth toe </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_4thtoe_right" value="3" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_4thtoe_left" value="3" onclick="validatetoeleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td align="left" class="gridbg1">3.2.17 &nbsp;&nbsp;Loss of fifth toe </td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_5thtoe_right" value="2" onclick="validatetoeright()" /></td>
            <td align="left" class="gridbg1"><html:checkbox property="lower_5thtoe_left" value="2" onclick="validatetoeleft()" /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan5"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
          <tr>
            <td width="70%" class="gridhdbg" style="text-align:left;">3.3 COMPLICATIONS (Filling is compulsory for any kind of amputee)</td>
            <td width="15%" class="gridhdbg">Old Values</td>
            <td width="15%" class="gridhdbg">New Values</td>
          </tr>
          <tr>
            <td width="70%" class="gridbg1"><font size="2" class="label">3.3.1 &nbsp;&nbsp;Is the stump unfit for fitting of Prosthesis?</font></td>
            <td align="left" class="gridbg1"><html:checkbox property="fitting_of_prosthesis" value="5"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan6"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
          <tr>
            <td class="gridbg1"><font size="2" class="label">3.3.2 &nbsp;&nbsp;Is there stiffness in the proximal joint?</font></td>
            <td align="left" class="gridbg1"><html:select property="proximal_joint">
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
            </html:select></td>
            <td align="left" class="gridbg1"><select  name="com_inflection1">
              <option  value="0">0</option>
              <option  value="1">1</option>
              <option  value="2">2</option>
              <option  value="3">3</option>
              <option> </option>
              <option  value="4">4</option>
              <option  value="5">5</option>
              <option  value="6">6</option>
              <option  value="7">7</option>
              <option  value="8">8</option>
              <option  value="9">9</option>
              <option  value="10">10</option>
            </select></td>
          </tr>
          <tr>
            <td class="gridbg1">3.3.3&nbsp;&nbsp; Is there Neuroma? </td>
            <td align="left" class="gridbg1"><html:select property="neuroma">
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
            </html:select></td>
            <td align="left" class="gridbg1"><select  name="com_inflection1">
              <option  value="0">0</option>
              <option  value="1">1</option>
              <option  value="2">2</option>
              <option  value="3">3</option>
              <option> </option>
              <option  value="4">4</option>
              <option  value="5">5</option>
              <option  value="6">6</option>
              <option  value="7">7</option>
              <option  value="8">8</option>
              <option  value="9">9</option>
              <option  value="10">10</option>
            </select></td>
          </tr>
          <tr>
            <td class="gridbg1"><font size="2">3.3.4 &nbsp;&nbsp;Is there infection? </font></td>
            <td align="left" class="gridbg1"><html:select property="infection">
              <html:option styleId="inf" value="0">0</html:option>
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
            </html:select></td>
            <td align="left" class="gridbg1"><select  name="com_inflection1">
              <option  value="0">0</option>
              <option  value="1">1</option>
              <option  value="2">2</option>
              <option  value="3">3</option>
              <option> </option>
              <option  value="4">4</option>
              <option  value="5">5</option>
              <option  value="6">6</option>
              <option  value="7">7</option>
              <option  value="8">8</option>
              <option  value="9">9</option>
              <option  value="10">10</option>
            </select></td>
          </tr>
          <tr>
            <td class="gridbg1">3.3.5&nbsp;&nbsp;&nbsp;Is dominant upper extremity involved ? </td>
            <td align="left" class="gridbg1"><html:checkbox property="dominant" value="4"  /></td>
            <td align="left" class="gridbg1"><input type ="checkbox" name="mohan6"  value="100"  onclick="validatecheckboxupperright()"  /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>


</html:form>
        <form action="">
             <TABLE align="center">
            <tr>
                  <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>

                <td><html:button  property="" value="Print" onclick="window.print()" styleClass="button"/></td>

        </TABLE>
        </form>
</BODY >
<script>

    function goURL()
    {
        document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
        document.forms[0].submit();
    }

</script>
</html:html >