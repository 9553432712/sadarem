<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%><HEAD>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disabilities</title>
        <script LANGUAGE="JavaScript" SRC="../js/lw_layers.js"></script>
        <script LANGUAGE="JavaScript" SRC="../js/menu.js"></script>


        <script language="JavaScript1.2" src="DisabilityUITG/js/coolmenus3.js"></script>
        <script language="javascript" src="DisabilityUITG/js/cal2.js"></script>
        <script language="javascript" src="DisabilityUITG/js/cal_conf2.js"></script>
        
        <script language="javascript" src="./DisabilityUITG/js/Evaluation.js"></script>
        <TITLE> New Document </TITLE>
        <META property="Generator" CONTENT="EditPlus">
        <META property="Author" CONTENT="">
        <META property="Keywords" CONTENT="">
        <META property="Description" CONTENT="">
    </HEAD>
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

        function enableAllProperties()
        {
            for(var i=1;i<=3;i++){
                document.getElementById(i).disabled=false;
            }
            return true;
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

<html:html>



    <BODY onLoad="disableForm(eve);">
        <script language="javascript" src="./DisabilityUITG/js/Evaluation.js"></script>
        <html:form action="/evaluationselectPrint.do" styleId="eve">
          <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
            <table align="center" cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
                </tr>
            </table><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td colspan="2" class="gridhdbg">6.&nbsp;&nbsp;EVALUATION OF PHYSICAL IMPAIRMENTS IN NEUROLOGICAL CONDITIONS</td>
                </tr>
              <tr>
                <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0"   class="gridtb" width="100%">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;">6.1 Involvement of Dominant Upper Extremity </td>
                        <td width="30%" align="center" class="gridhdbg"> Old Values </td>
                        <td width="30%" align="center" class="gridhdbg"> New Values </td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> 6.1 Involvement of  Dominant Upper Extremity </td>
                        <td width="30%" align="center" class="gridbg1"><html:checkbox property="dominantupperextremity" value="4"/></td>
                        <td width="30%" align="center" class="gridbg1"><input type="checkbox" name="mohan" value="4"/></td>
                      </tr>
                      <tr>
                        <td class="gridhdbg" style="text-align:left;"> 6.2 Loss of Sensation in </td>
                        <td class="gridhdbg">&nbsp;</td>
                        <td class="gridhdbg">&nbsp;</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Upper extremity </td>
                        <td width="30%" align="center" class="gridbg1"><html:checkbox property="lossofsensationupper" value="5"/></td>
                        <td width="30%" align="center" class="gridbg1"><input type="checkbox" name="mohan" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Lower extremity </td>
                        <td width="30%" align="center" class="gridbg1"><html:checkbox property="lossofsensationlower" value="5"/></td>
                        <td width="30%" align="center" class="gridbg1"><input type="checkbox" name="mohan" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.3 Altered sensorium  (Neurological Status) </td>
                        <td width="30%" align="center" class="gridbg1"><html:checkbox property="neurologicalstatus" value="100"/></td>
                        <td width="30%" align="center" class="gridbg1"><input type="checkbox" name="mohan" value="4"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td colspan="4" class="gridhdbg"> 6.4 Intellectual Impairment </td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridhdbg"> Degree of Mental Retardation </td>
                        <td width="10%" align="center" class="gridhdbg"> IQ Range </td>
                        <td align = "left" width="20%" class="gridhdbg"> (Please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td align = "left" width="20%" class="gridhdbg"> (Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridbg1"> Boarderline </td>
                        <td width="10%" align="center" class="gridbg1"> 70-79 </td>
                        <td width="30%" class="gridbg1"><html:radio property="intellectualimpairment" value="25" ondblclick="uncheckRadio();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridbg1"> Mild </td>
                        <td width="10%" align="center" class="gridbg1"> 50-69 </td>
                        <td width="30%" class="gridbg1"><html:radio property="intellectualimpairment" value="50" ondblclick="uncheckRadio();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridbg1"> Moderate </td>
                        <td width="10%" align="center" class="gridbg1"> 35-49 </td>
                        <td width="30%" class="gridbg1"><html:radio property="intellectualimpairment" value="75" ondblclick="uncheckRadio();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridbg1"> Severe </td>
                        <td width="10%" align="center" class="gridbg1"> 20-34 </td>
                        <td width="30%" class="gridbg1"><html:radio property="intellectualimpairment" value="90" ondblclick="uncheckRadio();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="30%" class="gridbg1"> Profound </td>
                        <td width="10%" align="center" class="gridbg1"> Less than 20 </td>
                        <td width="30%" class="gridbg1"><html:radio property="intellectualimpairment" value="100" ondblclick="uncheckRadio();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.5 Speech Defect </td>
                        <td width="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td width="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Mild Dysarthria </td>
                        <td width="30%" align="left" class="gridbg1"><html:radio property="speechdefect" value="0" ondblclick="uncheckRadio1();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Moderate Dysarthria </td>
                        <td width="30%" align="left" class="gridbg1"><html:radio property="speechdefect" value="25" ondblclick="uncheckRadio1();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Severe Dysarthria </td>
                        <td width="30%" align="left" class="gridbg1"><html:radio property="speechdefect" value="50" ondblclick="uncheckRadio1();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.6.1 Motor Cranial Nerve Disability </td>
                        <td width="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td width="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Oculomotor Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6a" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Trochlear Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6b" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Abducence Nerve </td>
                        <td width="30%" class="gridbg1">
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6c" value="20"/>
                          <input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> D. Facial Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6d" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> E. Accessory Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6e" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> F. Hypoglossal Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6f" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> G. Trigeminal Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6g" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> H. Vagus Nerve </td>
                        <td width="30%" align="left" class="gridbg1"><html:checkbox property="e6h" value="20"/></td>
                        <td width="30%" class="gridbg1"><input type="checkbox" name="mohan2" value="4"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                  <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                    <tr>
                      <td width="40%" class="gridhdbg" style="text-align:left;"> 6.6.2 Sensory Cranial Nerve Disability </td>
                      <td width ="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                        Old Values</td>
                      <td width ="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                        New Values</td>
                    </tr>
                    <tr>
                      <td width="40%" class="gridbg1"> A. Olfactory Nerve </td>
                      <td width="30%" class="gridbg1"><html:checkbox property="sca" value="10"/></td>
                      <td width="30%" class="gridbg1"><input type="checkbox" name="mohan3" value="4"/></td>
                    </tr>
                    <tr>
                      <td width="40%" class="gridbg1"><%-- Changed Trochlear to Optic  --%>
                        B. Optic Nerve </td>
                      <td width="30%" class="gridbg1"><html:checkbox property="scb" value="10"/></td>
                      <td width="30%" class="gridbg1"><input type="checkbox" name="mohan3" value="4"/></td>
                    </tr>
                    <tr>
                      <td width="40%" class="gridbg1"> C. Vestibulocochlear Nerve </td>
                      <td width="30%" class="gridbg1"><html:checkbox property="scc" value="10"/></td>
                      <td width="30%" class="gridbg1"><input type="checkbox" name="mohan3" value="4"/></td>
                    </tr>
                    <tr>
                      <td width="40%" class="gridbg1"> D. Glossopharyngeal Nerve </td>
                      <td width="30%" class="gridbg1"><html:checkbox property="scd" value="10"/></td>
                      <td width="30%" class="gridbg1"><input type="checkbox" name="mohan3" value="4"/></td>
                    </tr>
                  </table></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                </table></td>
                <td width="50%" align="center" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.7 Motor system Disability </td>
                        <td width="20%" colspan="2" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td width="20%" colspan="2" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridhdbg"> Neurological Involvement( Hemiparesis) </td>
                        <td width="15%" align="center" class="gridhdbg">Right Side
                          <input type="radio"  name="mohan"  value="R" styleId="4" onDblClick="uncheckRadio9();" onClick="enableRadioButton()"/></td>
                        <td width="15%" align="center" class="gridhdbg">Left Side
                          <input type="radio"  name="mohan"  value="L" styleId="5" onDblClick="uncheckRadio9();" onClick="enableRadioButton()"/></td>
                        <td width="15%" align="center" class="gridhdbg">Right Side
                          <input type="radio"  name="mohan"  value="R" styleId="4" onDblClick="uncheckRadio9();" onClick="enableRadioButton()"/></td>
                        <td width="15%" align="center" class="gridhdbg">Left Side
                          <input type="radio"  name="mohan"  value="L" styleId="5" onDblClick="uncheckRadio9();" onClick="enableRadioButton()"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Mild </td>
                        <td colspan="2" align="center" class="gridbg1"><html:radio property="motorsystem" value="25" ondblclick="uncheckRadio2();" styleId="1"/></td>
                        <td colspan="2" align="center" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Moderate </td>
                        <td colspan="2" align="center" class="gridbg1"><html:radio property="motorsystem" value="50" ondblclick="uncheckRadio2();" styleId="2"/></td>
                        <td colspan="2" align="center" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Severe </td>
                        <td colspan="2" align="center" class="gridbg1"><html:radio property="motorsystem" value="75" ondblclick="uncheckRadio2();" styleId="3"/></td>
                        <td colspan="2" align="center" class="gridbg1"><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.8 Sensory system Disability </td>
                        <td  colspan="3" align="center" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td  colspan="3" align="center" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%"  class="gridhdbg"> Extent of Sensory Deficit</td>
                        <td align="center" class="gridhdbg" width="10%"> Mild</td>
                        <td align="center" class="gridhdbg" width="10%"> Moderate </td>
                        <td width="10%"  align="center" class="gridhdbg"> Severe</td>
                        <td align="center" class="gridhdbg" width="10%"> Mild</td>
                        <td align="center" class="gridhdbg" width="10%"> Moderate </td>
                        <td width="10%"  align="center" class="gridhdbg"> Severe</td>
                      </tr>
                      <tr class="label">
                        <td width="40%" class="gridbg1"> A. Anesthesia </td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem" value="2" ondblclick="uncheckRadio6();"/></td>
                        <td align="center" class="gridbg1"><html:radio property="sensorysystem" value="5" ondblclick="uncheckRadio6();"/></td>
                        <td   align="center" class="gridbg1"><html:radio property="sensorysystem" value="10" ondblclick="uncheckRadio6();"/></td>
                        <td  align="center" class="gridbg1"><input type="radio"  name="mohan"  value="2" onDblClick="uncheckRadio6();"/></td>
                        <td align="center" class="gridbg1"><input type="radio"  name="mohan"  value="5" onDblClick="uncheckRadio6();"/></td>
                        <td   align="center" class="gridbg1"><input type="radio"  name="mohan"  value="10" onDblClick="uncheckRadio6();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Hypoesthesia </td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem1" value="2" ondblclick="uncheckRadio7();"/></td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem1" value="5" ondblclick="uncheckRadio7();"/></td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem1" value="10" ondblclick="uncheckRadio7();"/></td>
                        <td  align="center" class="gridbg1"><input type="radio"  name="mohan"  value="2" onDblClick="uncheckRadio6();"/></td>
                        <td align="center" class="gridbg1"><input type="radio"  name="mohan"  value="5" onDblClick="uncheckRadio6();"/></td>
                        <td   align="center" class="gridbg1"><input type="radio"  name="mohan"  value="10" onDblClick="uncheckRadio6();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Parasthesia </td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem2" value="2" ondblclick="uncheckRadio8();"/></td>
                        <td  align="center" class="gridbg1"><html:radio property="sensorysystem2" value="5" ondblclick="uncheckRadio8();"/></td>
                        <td align="center" class="gridbg1"><html:radio property="sensorysystem2" value="10" ondblclick="uncheckRadio8();"/></td>
                        <td  align="center" class="gridbg1"><input type="radio"  name="mohan"  value="2" onDblClick="uncheckRadio6();"/></td>
                        <td align="center" class="gridbg1"><input type="radio"  name="mohan"  value="5" onDblClick="uncheckRadio6();"/></td>
                        <td   align="center" class="gridbg1"><input type="radio"  name="mohan"  value="10" onDblClick="uncheckRadio6();"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td width="35%" class="gridhdbg" colspan="7" style="text-align:left;"> 6.8.1
                          Extent of Sensory Deficit</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridhdbg" align="center">&nbsp;</td>
                        <td colspan="3" align="center" class="gridhdbg"> Old Values </td>
                        <td width="25%" class="gridhdbg" colspan="3" align="center"> New Values </td>
                      </tr>
                      <tr>
                        <td  class="gridhdbg" width="40%"> Extent of Sensory Deficit</td>
                        <td  align="center" class="gridhdbg" width="10%"> Hands</td>
                        <td align="center" class="gridhdbg" width="10%"> Feet </td>
                        <td width="10%"  align="center" class="gridhdbg"> Trunk</td>
                        <td  align="center" class="gridhdbg" width="10%"> Hands</td>
                        <td align="center" class="gridhdbg" width="10%"> Feet </td>
                        <td width="10%"  align="center" class="gridhdbg"> Trunk</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> Hands/Feet/Trunk </td>
                        <td   align="center" class="gridbg1"><html:checkbox property="sensorysystemh" value="4"/></td>
                        <td  align="center" class="gridbg1"><html:checkbox property="sensorysystemf" value="4"/></td>
                        <td align="center" class="gridbg1"><html:checkbox property="sensorysystemt" value="4"/></td>
                        <td   align="center" class="gridbg1"><input type="checkbox" name="mohan4"  value="4"/></td>
                        <td  align="center" class="gridbg1"><input type="checkbox" name="mohan4"  value="4"/></td>
                        <td align="center" class="gridbg1"><input type="checkbox" name="mohan4"  value="4"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td width="40%" class="gridhdbg" style="text-align:left;"> 6.9 Bladder disability Due to neurological involvement: </td>
                        <td width ="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old Values </td>
                        <td width ="30%" align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values </td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Mild (Hesitancy/Frequency) </td>
                        <td width="30%" class="gridbg1"><html:radio property="bladderinvolvement" value="25" ondblclick="uncheckRadio3();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Moderate(precipitancy) </td>
                        <td width="30%" class="gridbg1"><html:radio property="bladderinvolvement" value="50" ondblclick="uncheckRadio3();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Severe(Occasional but recurrent incontinence) </td>
                        <td width="30%" class="gridbg1"><html:radio property="bladderinvolvement" value="75" ondblclick="uncheckRadio3();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> D. Very Severe(Retension/total incontinence) </td>
                        <td width="30%" class="gridbg1"><html:radio property="bladderinvolvement" value="100" ondblclick="uncheckRadio3();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td colspan="3" class="gridhdbg" style="text-align:left;"> 6.10 Post head injury fits & epileptic Convulsions: </td>
                      <tr>
                        <td width="40%" class="gridhdbg"> Frequency/Severity of
                          Convulsions </td>
                        <td width="30%"  align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          Old values</td>
                        <td width="30%"  align="left" class="gridhdbg">(Please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Mild :Occurrence of one
                          convulsion only </td>
                        <td width="30%" class="gridbg1"><html:radio property="injury" value="0" ondblclick="uncheckRadio4();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr >
                        <td width="40%" class="gridbg1"> B. Moderate:1-5
                          Convulsion/month on adequate
                          medication </td>
                        <td width="30%" class="gridbg1"><html:radio property="injury" value="25" ondblclick="uncheckRadio4();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Severe: 6-10 convulsion/month
                          on adequate medication </td>
                        <td width="30%" class="gridbg1"><html:radio property="injury" value="50" ondblclick="uncheckRadio4();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> D. Very Severe: more than 10 fits/month on adequate medication </td>
                        <td width="30%" class="gridbg1"><html:radio property="injury" value="75" ondblclick="uncheckRadio4();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td><table border="0" align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                      <tr>
                        <td colspan="3" class="gridhdbg" style="text-align:left;"> 6.11 Ataxia </td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridhdbg"> Severity of Ataxia </td>
                        <td width="30%"  align="left" class="gridhdbg">(please tick in the appropriate box)<br/>
                          Old Values</td>
                        <td width="30%"  align="left" class="gridhdbg">(please tick in the appropriate box)<br/>
                          New Values</td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> A. Mild(Detected on examination) </td>
                        <td width="30%" class="gridbg1"><html:radio property="ataxia" value="25" ondblclick="uncheckRadio5();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> B. Moderate </td>
                        <td width="30%" class="gridbg1"><html:radio property="ataxia" value="50" ondblclick="uncheckRadio5();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> C. Severe </td>
                        <td width="30%" class="gridbg1"><html:radio property="ataxia" value="75" ondblclick="uncheckRadio5();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                      <tr>
                        <td width="40%" class="gridbg1"> D. Very Severe </td>
                        <td width="30%" class="gridbg1"><html:radio property="ataxia" value="100" ondblclick="uncheckRadio5();"/></td>
                        <td width="30%" class="gridbg1"><input type="radio"  name="mohan" value="25" onDblClick="uncheckRadio3();"/></td>
                      </tr>
                    </table></td>
                  </tr>
                </table></td>
              </tr>
                        </table>


        </html:form>
        <form action="">
            <TABLE align="center">
                <tr class="tbl_bg2_content">
                    <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>

                    <td><html:button  property="Reset" value="Print" onclick="window.print();" styleClass="button"/></td>

            </TABLE>
        </form>
    </body>
    <script>

        function goURL()
        {
            document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
            document.forms[0].submit();
        }

    </script>
</html:html>
