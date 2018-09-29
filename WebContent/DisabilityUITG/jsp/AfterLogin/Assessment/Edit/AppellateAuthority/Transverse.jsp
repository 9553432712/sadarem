<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
<head>
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
                document.getElementById('transButton').disabled = true;
        }
    }
</script>  

<html:form action="insertTransverseDetails.do?insertTransverseDetails=insertTransverseDetails"
           onsubmit="return avoidDuplicateForm(this)">
  <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
      
  <TBODY>
  
  <TR>
      <TH colspan="8">4.&nbsp;ADD CONGENTIAL DEFICIENCIES OF THE LIMB</TH>
  </TR>
  
  <TR>
      <TH colspan="4">4.1&nbsp;&nbsp;TRANSVERSE CONGENITAL DEFICIENCIES &nbsp;&nbsp;<FONT size=2>(Please tick in the
      appropriate boxes)</FONT>
    </TH>
  </TR>
  </TBODY>

  <TBODY>
  <TR>
    <th>&nbsp;</th>
    <th><FONT size=2><B>Right</B></FONT></th>
    <th><FONT size=2><B>Left</B></FONT></th>
  </TR>
  
  <TR>
    <TD ><FONT size=2>4.1.1 &nbsp;&nbsp;Deficiency Arm complete (shoulder disarticulation)</FONT> </TD>
    <TD><html:checkbox property="shoulderdisarticulationrightside" value="90"/></TD>
    <TD><html:checkbox property="shoulderdisarticulationleftside" value="90"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.2 &nbsp;&nbsp;Deficiency Proximal upper arm (Above elbow amputee)</FONT>
    <TD><html:checkbox property="aboveelbowamputeerightside" value="85"/></TD>
    <TD><html:checkbox property="aboveelbowamputeeleftside" value="85"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.3 &nbsp;&nbsp;Deficiency Forearm complete (Elbow disarticulation)</FONT>
    <TD><html:checkbox property="elbowdisarticulationrightside" value="75"/></TD>
    <TD><html:checkbox property="elbowdisarticulationleftside" value="75"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.4 &nbsp;&nbsp;Deficiency of Lower forearm (Below elbow amputee)</FONT>
    <TD><html:checkbox property="belowelbowamputeerightside" value="65"/></TD>
    <TD><html:checkbox property="belowelbowamputeeleftside" value="65"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.5 &nbsp;&nbsp;Deficiency of Carpal complete (Wrist disarticulation)</FONT>
    <TD><html:checkbox property="wristdisarticulationrightside" value="60"/></TD>
    <TD><html:checkbox property="wristdisarticulationleftside" value="60"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.6 &nbsp;&nbsp;Deficiency of Metacarpal complete (Disarticulation through carpal bones)</FONT>
    <TD><html:checkbox property="carpalbonesrightside" value="55"/></TD>
    <TD><html:checkbox property="carpalbonesleftside" value="55"/></TD>
  </TR>

  <TR>
    <TD ><FONT size=2>4.1.7&nbsp;&nbsp;Deficiency of Thigh complete (Hip disarticulation)</FONT>
    <TD><html:checkbox property="hipdisarticulationrightside" value="90"/></TD>
    <TD><html:checkbox property="hipdisarticulationleftside" value="90"/></TD>
  </TR>
  
  <TR>
    <TD ><FONT size=2>4.1.8&nbsp;&nbsp;Deficiency of Lower thigh (Above knee amputee)</FONT>
    <TD><html:checkbox property="kneeamputeerightside" value="80"/></TD>
    <TD><html:checkbox property="kneeamputeeleftside" value="80"/></TD>
  </TR>
  
  </TBODY>

  <TBODY>
  <TR>
      <th colspan="8"><html:button property="Back" value="Back" onclick="goBack();" styleClass="button"/>
  <html:submit value="Add" styleClass="button" styleId="transButton"/>
  <html:reset value="Reset" styleClass="button"/></th>
  </TR>
  </TBODY>
  </table>
        




</html:form>
</BODY>
</html:html>
