<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html>
    <TITLE>Transverse Congential Deficiency</TITLE>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
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


    <META http-equiv=Content-Type content="text/html" charset=UTF-8">
    <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/lw_layers.js"></script>
    <script LANGUAGE="JavaScript" SRC="scripts/jsmenu/menu.js"></script>
    <META content="MSHTML 6.00.2900.3268" name=GENERATOR>

    <SCRIPT language=JavaScript1.2 src="./DisabilityUITG/js/coolmenus3.js"></SCRIPT>
    <SCRIPT language=javascript src="./DisabilityUITG/css/cal2.js"></SCRIPT>
    <SCRIPT language=javascript src="./DisabilityUITG/css/cal_conf2.js"></SCRIPT>
<SCRIPT language=JavaScript1.2 src="./DisabilityUITG/scripts/aes_menu_p1.js"></SCRIPT>



<style type="text/css">

/* GRID Starts */

.gridbg1 {
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:10px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #000 solid; border-top:1px #000 solid;
}

/* GRID Ends */
</style>

</head>

<BODY onload="disableForm(tra);">

<html:form action="updateTransverseDetails.do?updateTransverseDetails=updateTransverseDetails"
               onsubmit="return avoidDuplicateForm(this)" styleId="tra">
        <table align="center" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>
        <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
            <TBODY>
                <TR>
                    <TH align=middle class="gridhdbg" style="font-size:14px;">4.&nbsp; CONGENTIAL DEFICIENCIES OF THE LIMB
                    </TH>
                </TR>
                <TR>
                    <TH align=left class="gridhdbg" style="font-size:14px;">4.1&nbsp;&nbsp;TRANSVERSE DEFICIENCIES&nbsp;&nbsp;(Please tick in the
                        appropriate box(es))
                    </TH>
                </TR>
            </TBODY>
        </table>

    <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
        <TBODY>
            <TR>
                <TD width="70%" class="gridhdbg">&nbsp;</TD>
                <td width="15%" class="gridhdbg">Old Values Right</td>
                <td width="15%" class="gridhdbg">Old Values Left</td>
                 <td width="15%" class="gridhdbg">New Values Right</td>
                <td width="15%" class="gridhdbg">New Values Left</td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.1 &nbsp;&nbsp;Deficiency Arm complete (shoulder disarticulation) </TD>
                <TD align=left class="gridbg1"><html:checkbox property="shoulderdisarticulationrightside" value="90"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="shoulderdisarticulationleftside" value="90"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.2 &nbsp;&nbsp;Deficiency Proximal upper arm (Above elbow amputee)
                <TD align=left class="gridbg1"><html:checkbox property="aboveelbowamputeerightside" value="100"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="aboveelbowamputeeleftside" value="100"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.3 &nbsp;&nbsp;Deficiency Forearm complete (Elbow disarticulation)
                <TD align=left class="gridbg1"><html:checkbox property="elbowdisarticulationrightside" value="75"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="elbowdisarticulationleftside" value="75"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.4 &nbsp;&nbsp;Deficiency of Lower forearm (Below elbow amputee)
                <TD align=left class="gridbg1"><html:checkbox property="belowelbowamputeerightside" value="65"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="belowelbowamputeeleftside" value="65"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.5 &nbsp;&nbsp;Deficiency of Carpal complete (Wrist disarticulation)
                <TD align=left class="gridbg1"><html:checkbox property="wristdisarticulationrightside" value="60"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="wristdisarticulationleftside" value="60"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.6 &nbsp;&nbsp;Deficiency of Metacarpal complete (Disarticulation through carpal bones)
                <TD align=left class="gridbg1"><html:checkbox property="carpalbonesrightside" value="55"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="carpalbonesleftside" value="55"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.7&nbsp;&nbsp;Deficiency of Thigh complete (Hip disarticulation)
                <TD align=left class="gridbg1"><html:checkbox property="hipdisarticulationrightside" value="90"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="hipdisarticulationleftside" value="90"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

            <TR>
                <TD align=left class="gridbg1">4.1.8&nbsp;&nbsp;Deficiency of Lower thigh (Above knee amputee)
                <TD align=left class="gridbg1"><html:checkbox property="kneeamputeerightside" value="80"/></TD>
              <TD align=left class="gridbg1"><html:checkbox property="kneeamputeeleftside" value="80"/></TD>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
              <td align="left" class="gridbg1"> <input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></td>
            </TR>

        </TBODY>
    </table>
<br>


    </html:form>
    <form action="">
        <TABLE align=center>
            <TBODY>
                <TR>
                    <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <TD><html:button property="Back" value="Next" onclick="goURL();" styleClass="button"/>

                    <TD><html:button property="Reset" value="Print" onclick="window.print();" styleClass="button"/>
                    </TD>

                </TR>
            </TBODY>
        </TABLE>
    </form>
</BODY>
<script>

    function goURL()
    {
        document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
        document.forms[0].submit();
    }

</script>
</html:html>
