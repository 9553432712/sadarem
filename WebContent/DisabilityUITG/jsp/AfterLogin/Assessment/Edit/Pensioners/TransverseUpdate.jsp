<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
    <TITLE>Transverse Congential Deficiency</TITLE>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentUpdateForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }

        function doReset()
        {
            document.getElementById("shoulderdisarticulationrightside").checked="";
            document.getElementById("shoulderdisarticulationleftside").checked="";
            document.getElementById("aboveelbowamputeerightside").checked="";
            document.getElementById("aboveelbowamputeeleftside").checked="";
            document.getElementById("elbowdisarticulationrightside").checked="";
            document.getElementById("elbowdisarticulationleftside").checked="";
            document.getElementById("belowelbowamputeerightside").checked="";
            document.getElementById("belowelbowamputeeleftside").checked="";
            document.getElementById("wristdisarticulationrightside").checked="";
            document.getElementById("wristdisarticulationleftside").checked="";
            document.getElementById("carpalbonesrightside").checked="";
            document.getElementById("carpalbonesleftside").checked="";
            document.getElementById("hipdisarticulationrightside").checked="";
            document.getElementById("hipdisarticulationleftside").checked="";
            document.getElementById("kneeamputeerightside").checked="";
            document.getElementById("kneeamputeeleftside").checked="";
       
        }

        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted'))
                return false;
            else{
                thisform.setAttribute('submitted','true');
                document.getElementById('toatlDisButton').disabled = true;
            }
        }
    </script>

    <META http-equiv=Content-Type content="text/html" charset=UTF-8">
    <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/lw_layers.js"></script>
    <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/menu.js"></script>
    <META content="MSHTML 6.00.2900.3268" name=GENERATOR>
    <SCRIPT language=JavaScript1.2 src="./DisabilityUITG/js/coolmenus3.js"></SCRIPT>
    <SCRIPT language=javascript src="./DisabilityUITG/css/cal2.js"></SCRIPT>
    <SCRIPT language=javascript src="./DisabilityUITG/css/cal_conf2.js"></SCRIPT>
    <SCRIPT language=JavaScript1.2 src="./DisabilityUITG/scripts/aes_menu_p1.js"></SCRIPT>
</head>

<BODY>        

    <html:form action="updateTransverseDetails.do?updateTransverseDetails=updateTransverseDetails"
               onsubmit="return avoidDuplicateForm(this)">
        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="80%">
            <TBODY>
                <TR>
                    <TH colspan="4">4.&nbsp;UPDATE CONGENTIAL DEFICIENCIES OF THE LIMB
                    </TH>
                </TR>
                <TR>
                    <TH align=center colspan="4">4.1&nbsp;&nbsp;TRANSVERSE DEFICIENCIES&nbsp;&nbsp;(Please tick in the
                        appropriate box(es))
                    </TH>
                </TR>
            </TBODY>
      
            <TBODY>
                <TR>
                    <Th width="70%">&nbsp;</Th>
                    <th width="15%" class="labelblue">Right</th>
                    <th width="15%" class="labelblue">Left</th>
                </TR>

                <TR>
                    <TD align=left >4.1.1 &nbsp;&nbsp;Deficiency Arm complete (shoulder disarticulation) </TD>
                    <TD align=left><html:checkbox property="shoulderdisarticulationrightside" value="90"/></TD>
                    <TD align=left><html:checkbox property="shoulderdisarticulationleftside" value="90"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.2 &nbsp;&nbsp;Deficiency Proximal upper arm (Above elbow amputee)
                    <TD align=left><html:checkbox property="aboveelbowamputeerightside" value="85"/></TD>
                    <TD align=left><html:checkbox property="aboveelbowamputeeleftside" value="85"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.3 &nbsp;&nbsp;Deficiency Forearm complete (Elbow disarticulation)
                    <TD align=left><html:checkbox property="elbowdisarticulationrightside" value="75"/></TD>
                    <TD align=left><html:checkbox property="elbowdisarticulationleftside" value="75"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.4 &nbsp;&nbsp;Deficiency of Lower forearm (Below elbow amputee)
                    <TD align=left><html:checkbox property="belowelbowamputeerightside" value="65"/></TD>
                    <TD align=left><html:checkbox property="belowelbowamputeeleftside" value="65"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.5 &nbsp;&nbsp;Deficiency of Carpal complete (Wrist disarticulation)
                    <TD align=left><html:checkbox property="wristdisarticulationrightside" value="60"/></TD>
                    <TD align=left><html:checkbox property="wristdisarticulationleftside" value="60"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.6 &nbsp;&nbsp;Deficiency of Metacarpal complete (Disarticulation through carpal bones)
                    <TD align=left><html:checkbox property="carpalbonesrightside" value="55"/></TD>
                    <TD align=left><html:checkbox property="carpalbonesleftside" value="55"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.7&nbsp;&nbsp;Deficiency of Thigh complete (Hip disarticulation)
                    <TD align=left><html:checkbox property="hipdisarticulationrightside" value="90"/></TD>
                    <TD align=left><html:checkbox property="hipdisarticulationleftside" value="90"/></TD>
                </TR>

                <TR>
                    <TD align=left >4.1.8&nbsp;&nbsp;Deficiency of Lower thigh (Above knee amputee)
                    <TD align=left><html:checkbox property="kneeamputeerightside" value="80"/></TD>
                    <TD align=left><html:checkbox property="kneeamputeeleftside" value="80"/></TD>
                </TR>

            </TBODY>
     
            <TBODY>
                <TR>
                    <th colspan="8"><html:button property="Back" value="Back" onclick="goBack();" styleClass="button"/>
                 <html:submit value="Update" styleId="toatlDisButton" styleClass="button"/>
                  <html:button property="Reset" value="Reset" onclick="doReset();" styleClass="button"/>
                    </th>

                </TR>
            </TBODY>
          </table>
          </html:form>
</BODY>
</html:html>
