<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<html:html>


        <body  onload="window.print()">
    <form>
    <logic:present name="transversebean" scope="request">
  <TABLE cellSpacing=1 cellPadding=5 width="80%" align=center border=0>
      <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

  <TBODY>

  <TR >
    <TH align=middle><FONT size=4>4.&nbsp;CONGENTIAL DEFICIENCIES OF THE LIMB</FONT>
    </TH>
  </TR>

  <TR >
    <TH align=left><FONT size=3>4.1&nbsp;&nbsp;TRANSVERSE DEFICIENCIES</FONT>&nbsp;&nbsp;<FONT size=2>(Please tick in the
      appropriate box(es))</FONT>
    </TH>
  </TR>
  </TBODY>
  </TABLE>

  <TABLE cellSpacing=1 cellPadding=5 width="80%" align=center border=0>
  <TBODY>
  <TR >
    <TD width="70%">&nbsp;</TD>
    <TD width="15%"><FONT size=2><B>Right</B></FONT></td>
    <TD width="15%"><FONT size=2><B>Left</B></FONT></TD>
  </TR>

  <logic:equal name="transversebean" property="shoulder" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.1 &nbsp;&nbsp;Arm complete (shoulder disarticulation) </TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="shoulderdisarticulationrightside" value="0"><bean:write name="transversebean" property="shoulderdisarticulationrightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="shoulderdisarticulationleftside" value="0"><bean:write name="transversebean" property="shoulderdisarticulationleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="aboveelbow" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.2 &nbsp;&nbsp;Proximal upper arm (Above elbow amputee)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="aboveelbowamputeerightside" value="0"><bean:write name="transversebean" property="aboveelbowamputeerightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="aboveelbowamputeeleftside" value="0"><bean:write name="transversebean" property="aboveelbowamputeeleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>


  <logic:equal name="transversebean" property="elbow" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.3 &nbsp;&nbsp;Forearm complete (Elbow disarticulation)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="elbowdisarticulationrightside" value="0"><bean:write name="transversebean" property="elbowdisarticulationrightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="elbowdisarticulationleftside" value="0"><bean:write name="transversebean" property="elbowdisarticulationleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="belowelbow" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.4 &nbsp;&nbsp;Lower forearm (Below elbow amputee)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="belowelbowamputeerightside" value="0"><bean:write name="transversebean" property="belowelbowamputeerightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="belowelbowamputeeleftside" value="0"><bean:write name="transversebean" property="belowelbowamputeeleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="writst" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.5 &nbsp;&nbsp;Carpal complete (Wrist disarticulation)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="wristdisarticulationrightside" value="0"><bean:write name="transversebean" property="wristdisarticulationrightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="wristdisarticulationleftside" value="0"><bean:write name="transversebean" property="wristdisarticulationleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="carpalbones" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.6 &nbsp;&nbsp;Metacarpal complete (Disarticulation through carpal bones)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="carpalbonesrightside" value="0"><bean:write name="transversebean" property="carpalbonesrightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="carpalbonesleftside" value="0"><bean:write name="transversebean" property="carpalbonesleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="hip" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.7&nbsp;&nbsp;Thigh complete (Hip disarticulation)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="hipdisarticulationrightside" value="0"><bean:write name="transversebean" property="hipdisarticulationrightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="hipdisarticulationleftside" value="0"><bean:write name="transversebean" property="hipdisarticulationleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  <logic:equal name="transversebean" property="knee" scope="request" value="true">
  <TR >
    <TD align=left style="font-family:verdana;font-size:12">4.1.8&nbsp;&nbsp;Lower thigh (Above knee amputee)
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="kneeamputeerightside" value="0"><bean:write name="transversebean" property="kneeamputeerightside"/></logic:notEqual></TD>
    <TD align=left style="font-family:verdana;font-size:12"><logic:notEqual name="transversebean" property="kneeamputeeleftside" value="0"><bean:write name="transversebean" property="kneeamputeeleftside"/></logic:notEqual></TD>
  </TR>
  </logic:equal>

  </TBODY>
  </TABLE>
  <table  cellSpacing=1 cellPadding=5 width="80%" align=center border=0>
      <tr ><th>Right side calculation part</th></tr>
      <tr >
        <td><pre style="font-family:verdana;font-size:12">
            <bean:write name="transversebean" property="rightsidecalculation"/></pre>
        </td></tr>
        <tr ><th>Left side calculation part</th></tr>
        <tr >
        <td><pre style="font-family:verdana;font-size:12">
            <bean:write name="transversebean" property="leftsidecalculation"/></pre>
        </td>
      </tr>
      <tr ><th>Final Calculation Part</th></tr>
      <tr >
        <td ><pre style="font-family:verdana;font-size:12"><bean:write name="transversebean" property="finalpercentageofdisability"/></pre></td>
      </tr>
  </table>

    </logic:present>


        </form>
</html:html>