 <%@page contentType="text/html"%>
 <%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<html:html>
<HEAD>
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
            document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
}
function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        else{
        thisform.setAttribute('submitted','true');
                document.getElementById('evalButton').disabled = true;
        }
    }
</script>

       <BODY onload="enableRadioButton()">
      <BODY>
       <html:errors/>
      <script language="javascript" src="./DisabilityUITG/js/Evaluation.js"></script>

      <html:form action="/insertevaluationaction.do?insertEvaluation=insertEvaluation"
                 method="post" styleId="form1" onsubmit="return avoidDuplicateForm(this)">
      <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
      <tr>
          <th colspan="8">6.&nbsp;&nbsp;ADD EVALUATION OF PHYSICAL IMPAIRMENTS IN NEUROLOGICAL CONDITIONS</th>
      </tr>

     
      <tr>
          <td width="50%">6.1 Involvement of  Dominant Upper Extremity
          </td><td colspan="4">
      <html:checkbox property="dominantupperextremity" value="4" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td colspan="4"><font size="2">
      6.2 Loss of Sensation in
      </td>
      </tr>
      <tr>
      <td >
      <ul>A. Upper extremity
      </td>
      <td colspan="4">
      <html:checkbox property="lossofsensationupper" value="5" style="width:25px"/>
      </td>
      </tr>
      <tr>
      <td >
      <ul> B. Lower extremity
      </td>
      <td colspan="4">
      <html:checkbox property="lossofsensationlower" value="5" style="width:25px"/>
      </td>
      </tr>
      <tr>
      <td>6.3 Altered sensorium   (Neurological Status)</td>
      <td colspan="4">
      <html:checkbox property="neurologicalstatus" value="100" style="width:25px"/>
      </td>
      </tr>
    
      <tr>
      <td colspan="6">6.4 Intellectual Impairment </td>
      </tr>
     
      <tr>
      <td width="30%">
      Degree of Mental Retardation
      </td>
      <td width="10%" align="center">
      IQ Range
      </td>
      <td align = "left" width="20%" colspan="4"><font size="0">(Please tick in the appropriate box)</font></td>
      </tr>
      <tr>
      <td >
      <ul>
      Boarderline
      </td>
      <td align="center" >
      70-79
      </td>
      <td colspan="4">
      <html:radio property="intellectualimpairment" value="25" ondblclick="uncheckRadio();" style="width:25px"/>
      </tr>
      <tr>
      <td ><ul>&nbsp;
              Mild
      </td>
      <td align="center" >
      50-69
      </td>
      <td colspan="4">
      <html:radio property="intellectualimpairment" value="50" ondblclick="uncheckRadio();" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
                  Moderate</ul>
       </td>
       <td align="center" >
      35-49
      </td>
      <td colspan="4">
      <html:radio property="intellectualimpairment" value="75" ondblclick="uncheckRadio();" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      Severe
      </td>
      <td align="center" >
      20-34
      </td>
      <td colspan="4">
      <html:radio property="intellectualimpairment" value="90" ondblclick="uncheckRadio();" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      Profound</center>
      </td>
      <td align="center" >
      Less than 20
      </td>
      <td colspan="4">
      <html:radio property="intellectualimpairment" value="100" ondblclick="uncheckRadio();" style="width:25px"/>
      </td>
      </tr>
      <%--</table>

      <table  align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="90%">--%>
      <tr>
      <td width="20%">6.5 Speech Defect </td>
      <td align="left" colspan="4"><font size="0">(Please tick in the appropriate box)</font></td>
      </tr>
      <tr>
      <td ><ul>
      A. Mild Dysarthria
      </td>
      <td align="left" colspan="4">
      <html:radio property="speechdefect" value="00" ondblclick="uncheckRadio1();" style="width:25px"/>
      </td>
      </tr>
      <tr>
      <td ><ul>
      B. Moderate Dysarthria
      </td>
      <td align="left" colspan="4">
      <html:radio property="speechdefect" value="25" ondblclick="uncheckRadio1();" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      C. Severe Dysarthria
      </td>
      <td align="left" colspan="4">
      <html:radio property="speechdefect" value="50" ondblclick="uncheckRadio1();" style="width:25px"/>
      </td>
      </tr>
    
      <tr>
      <td width="20%">6.6.1 Motor Cranial Nerve Disability</td>
      <td align="left" colspan="4"><font size="0">(Please tick in the appropriate box)</font></td>
      </tr>
      <tr>
      <td ><ul>
      A. Occulomotor Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6a" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      B. Trochlear Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6b" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      C. Abducence Nerve
      </td><ul>
      <td align="left"  colspan="4">
      <html:checkbox property="e6c" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td  ><ul>
      D. Facial Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6d" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      E. Accessory Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6e" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      F. Hypoglossal Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6f" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      G. Trigeminal Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6g" value="20" style="width:25px"/>
      </td>
      </tr>
      <tr>
          <td ><ul>
      H. Vagus Nerve
      </td>
      <td align="left"  colspan="4">
      <html:checkbox property="e6h" value="20" style="width:25px"/>
      </td>
      </tr>
     
       <tr>
           <td width="20%" >6.6.2 Sensory Cranial Nerve Disability</td>
           <td align="left" colspan="4"><font size="0">(Please tick in the appropriate box)</font></td>
       </tr>
       <tr>
       <td ><ul>
       A. Olfactory Nerve
       </td>
       <td colspan="4">
       <html:checkbox property="sca" value="10" style="width:25px"/>
       </td>
       </tr>
       <tr>
       <td ><ul>
               <%-- Changed Trochlear to Optic  --%>
       B. Optic Nerve
       </td>
       <td colspan="4">
       <html:checkbox property="scb" value="10" style="width:25px"/>
       </td>
       </tr>
       <tr>
       <td ><ul>
       C. Vestibulocochlear Nerve
       </td>
       <td colspan="4">
       <html:checkbox property="scc" value="10" style="width:25px"/>
       </td>
       </tr>
       <tr>
       <td ><ul>
       D. Glossopharyngeal Nerve
       </td>
       <td colspan="4">
       <html:checkbox property="scd" value="10" style="width:25px"/>
       </td>
       </tr>
      
       <tr>
       <td width="40%">6.7 Motor system Disability </td>
       <td width="20%" colspan="4"><font size="0">(Please tick in the appropriate box)</font></td>
       </tr>
       <tr>
       <td width="40%">
       Neurological Involvement( Hemiparesis)
       </td>
       <td width="20%">Right Side <html:radio property="motorsystemLeftOrRight" styleId="4" value="R" ondblclick="uncheckRadio9();" onclick="enableRadioButton()" style="width:25px"/></td>
       <td width="20%"  colspan="2">Left Side <html:radio property="motorsystemLeftOrRight" styleId="5" value="L" ondblclick="uncheckRadio9();" onclick="enableRadioButton()" style="width:25px"/></td>
       </tr>
       <tr>
       <td><ul>
       A. Mild
       </td>
       <td  colspan="4">
           <html:radio property="motorsystem" value="25" ondblclick="uncheckRadio2();" styleId="1" style="width:25px"/>
         </td>

         </tr>
         <tr>
         <td ><ul>
         B. Moderate
         </td>
         <td  colspan="4">
             <html:radio property="motorsystem" value="50" ondblclick="uncheckRadio2();" styleId="2" style="width:25px"/></td>

         </tr>
         <tr>
             <td ><ul>
         C. Severe
         </td>
         <td  colspan="4">
             <html:radio property="motorsystem" value="75" ondblclick="uncheckRadio2();" styleId="3" style="width:25px"/>
	 </td>

         </tr>
     
	 <tr>
             <td width="30%"> 6.8 Sensory system Disability </td>
             <td colspan="4" align="left"><ul><font size="0">(Please tick in the appropriate box)</font></td>
         </tr>
         <tr>
             <td width="27%">
          Extent of Sensory Deficit</td>
             <td  width="30%" align="center">
         Mild</td>
         <td  align="center" >
         Moderate
         </td>
         <td  align="center" >
         Severe</td>
         </tr>
         <tr>
         <td >
         A. Anesthesia
         </td>
         <td   align="center">
         <html:radio property="sensorysystem" value="2" ondblclick="uncheckRadio6();" style="width:25px"/></td>
	  <td  align="center">
         <html:radio property="sensorysystem" value="5" ondblclick="uncheckRadio6();" style="width:25px"/>
         </td>
         <td  align="center">
         <html:radio property="sensorysystem" value="10" ondblclick="uncheckRadio6();" style="width:25px"/>
         </td>
         </tr>
         <tr>
             <td ><ul>
         B. Hypoesthesia
         </td>
         <td align="center">
         <html:radio property="sensorysystem1" value="2" ondblclick="uncheckRadio7();" style="width:25px"/></td>
	  <td align="center">
              <html:radio property="sensorysystem1" value="5" ondblclick="uncheckRadio7();" style="width:25px"/>
         </td>
         <td  align="center">
        <html:radio property="sensorysystem1" value="10" ondblclick="uncheckRadio7();" style="width:25px"/>
        </td>
        </tr>
        <tr>
        <td ><ul>
        C. Parasthesia
        </td>
        <td align="center">
        <html:radio property="sensorysystem2" value="2" ondblclick="uncheckRadio8();" style="width:25px"/></td>
	<td  align="center">
        <html:radio property="sensorysystem2" value="5" ondblclick="uncheckRadio8();" style="width:25px"/>
        </td>
        <td align="center">
        <html:radio property="sensorysystem2" value="10" ondblclick="uncheckRadio8();" style="width:25px"/>
        </td>
        </tr>
       
	<tr>
            <td  colspan="8"> 6.8.1  </td>
         </tr>
         <tr>
             <td width="30%">
         Extent of Sensory Deficit</td>
             <td width="20%" align="center">        Hands</td>
        <td  align="center">
        Feet
        </td>
        <td align="center">
        Trunk</td>
        </tr>
        <tr>
            <td >
        Hands/Feet/Trunk
        </td>
        <td  align="center">
        <html:checkbox property="sensorysystemh" value="4" style="width:25px"/>
        </td>
        <td   align="center">
        <html:checkbox property="sensorysystemf" value="4" style="width:25px"/>
        </td>
        <td  align="center">
        <html:checkbox property="sensorysystemt" value="4" style="width:25px"/>
        </td></tr>
       
	  <tr>
              <td width="20%">
          6.9 Bladder disability Due to neurological involvement
          </td>
          <td align="left" colspan="4"> <font size="0">(Please tick in the appropriate box)</font> </td>
          </tr>
          
          <tr>
              <td ><ul>
          A. Mild (Hesitancy/Frequency)
          </td>
          <td colspan="4">
         <html:radio property="bladderinvolvement" value="25" ondblclick="uncheckRadio3();" style="width:25px"/>
          </td>
          </tr>
          <tr>
              <td ><ul>
          B. Moderate(Precipitancy)
          </td>
          <td colspan="4">
        <html:radio property="bladderinvolvement" value="50" ondblclick="uncheckRadio3();" style="width:25px"/>
         </td>
         </tr>
         <tr>
             <td ><ul>
         C. Severe(Occasional but
         recurrent incontinence)
         </td>
         <td colspan="4">
        <html:radio property="bladderinvolvement" value="75" ondblclick="uncheckRadio3();" style="width:25px"/>
       </td>
       </tr>
        <tr>
            <td ><ul>
        D. Very Severe(Retension/total incontinence)
       </td>
       <td colspan="4">
       <html:radio property="bladderinvolvement" value="100" ondblclick="uncheckRadio3();" style="width:25px"/>
       </td>
       </tr>
     
	<tr>
            <td colspan="8"> 6.10 Post head injury fits & epileptic Convulsions
       </td>

      
       <tr>
           <td width="50%">
       Frequency/Severity of
       Convulsions
       </td>
       <td  colspan="4" align="left"><font size="0">(Please tick in the appropriate box)</font></td>
       </tr>
       <tr>
           <td ><ul>
       A. Mild :Occurrence of one
       convulsion only
       </td>
       <td colspan="4">
      <html:radio property="injury" value="00" ondblclick="uncheckRadio4();" style="width:25px"/>
       </td>
       </tr>
        <tr>
            <td ><ul>
       B. Moderate:1-5
       Convulsion/month on adequate
       medication
       </td>
       <td colspan="4">
       <html:radio property="injury" value="25" ondblclick="uncheckRadio4();" style="width:25px"/>
       </td>
       </tr>
       <tr>
           <td ><ul>
       C. Severe: 6-10 convulsion/month
       on adequate medication
       </td>
       <td colspan="4">
       <html:radio property="injury" value="50" ondblclick="uncheckRadio4();" style="width:25px"/>
       </td>
       </tr>
        <tr>
            <td ><ul>
       D. Very Severe: more than 10 fits/month on adequate medication
       </td>
       <td colspan="4">
       <html:radio property="injury" value="75" ondblclick="uncheckRadio4();" style="width:25px"/>
       </td>
       </tr>
      
	<tr>
            <td colspan="6" > 6.11 Ataxia
       </td>
       </tr>
      
       <tr>
           <td width="50%" >
       Severity of Ataxia
       </td>
       <td colspan="4" align="left"><font size="0">(Please tick in the appropriate box)</font></td>
       </tr>
       <tr>
           <td ><ul>
       A. Mild(Detected on examination)
       </td>
       <td colspan="4">
	<html:radio property="ataxia" value="25" ondblclick="uncheckRadio5();" style="width:25px"/>
       </td>
       </tr>
        <tr>
            <td ><ul>
       B. Moderate
       </td>
       <td colspan="4">
       <html:radio property="ataxia" value="50" ondblclick="uncheckRadio5();" style="width:25px"/>
       </td>
       </tr>
       <tr>
           <td ><ul>
       C. Severe
       </td>
       <td colspan="4">
      <html:radio property="ataxia" value="75" ondblclick="uncheckRadio5();" style="width:25px"/>
       </td>
       </tr>
        <tr>
            <td ><ul>
        D. Very Severe
       </td>
       <td colspan="4">
       <html:radio property="ataxia" value="100" ondblclick="uncheckRadio5();" style="width:25px"/>
       </td>
       </tr>
      
     
    <tr>
        <th colspan="4"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>


       <html:submit  value="Add" styleClass="button" styleId="evalButton" onclick="return selectRedioButton();"/>


        <html:button  property="Reset" value="Reset" onclick="resetbutton()"  styleClass="button"/></th>

 </table>

</html:form>
  </body>

</html:html>
