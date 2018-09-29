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
       
       <TITLE> New Document </TITLE>
       <META property="Generator" CONTENT="EditPlus">
       <META property="Author" CONTENT="">
       <META property="Keywords" CONTENT="">
       <META property="Description" CONTENT="">
       </HEAD>
       <script language="javascript" >
    function goBack()
{
            document.forms[0].action="PhysicalimpairmentUpdateForwadAction.do";
            document.forms[0].submit();
}
</script>

   

     <BODY>
     <script language="javascript" src="./DisabilityUITG/js/Evaluation.js"></script>
     <html:form action="/modifyevaluation.do?updateEvaluationAction=updateEvaluationAction" method="post" styleId="form1">
     <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
     <tr class="tbl_bg2_content_hdr">
     <th align="center"><font size="4">6.&nbsp;&nbsp;EVALUATION OF PHYSICAL IMPAIRMENTS IN NEUROLOGICAL CONDITIONS</font></th>
     </tr>
	
     </TABLE>
  
     <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
     <tr class="tbl_bg2_content_hdr">
     <td width="52%"><font size="2">
     6.1 Involvement of  Dominant Upper Extremity</font>
     </td><td width="70%" align="center">
     <html:checkbox property="dominantupperextremity" value="4"/>
     </td>
     </tr>
    
     <tr class="tbl_bg2_content_hdr"> <td><font size="2">
     6.2 Loss of Sensation in:
     </td></font><td></td><td></td></tr>
     <tr class="tbl_bg2_content">
     <td>
     <ul>A. Upper extremity
     </td>
     <td align="center">
     <html:checkbox property="lossofsensationupper" value="5"/>
     </td>
     </tr>
    
     <tr class="tbl_bg2_content">
     <td>
     <ul> B. Lower extremity
     </td>
     <td align="center">
     <html:checkbox property="lossofsensationlower" value="5"/>
     </td>
     </tr>
   
      <tr class="tbl_bg2_content_hdr">
      <td><font size="2">
      6.3 Altered sensorium  (Neurological Status)
      </td>
      <td align="center">
      <html:checkbox property="neurologicalstatus" value="100"/>
      </td>
      </tr>
      </table>
  
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
        <td colspan="3"><font size="2">
        6.4 Intellectual Impairment:
        </td></font>
        <tr class="tbl_bg2_content">
        <td>
        </td>
        </tr>
        
       <tr class="tbl_bg2_content">
       <td width="30%">
       <b>Degree of Mental Retardation</b> 
       </td>
       <td width="10%" align="center">
       <b>IQ Range</b>
       </td>
       <td align = "left" width="20%"> <font size="0"><b>(Please tick in the appropriate box)</b></font></td>
       </tr>
       <tr class="tbl_bg2_content">	   
       <td>
	<ul>
       Boarderline
       </td>
       <td align="center">
       70-79
       </td>
       <td>
       <html:radio property="intellectualimpairment" value="25" ondblclick="uncheckRadio();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       Mild
       </td>
       <td align="center">
       50-69
       </td>
	<td>
        <html:radio property="intellectualimpairment" value="50" ondblclick="uncheckRadio();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        Moderate
        </td>
        <td align="center">
        35-49
        </td>
        <td>
       <html:radio property="intellectualimpairment" value="75" ondblclick="uncheckRadio();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       Severe
       </td>
       <td align="center">
       20-34
       </td>
       <td>
       <html:radio property="intellectualimpairment" value="90" ondblclick="uncheckRadio();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       Profound</center>
       </td>
       <td align="center">
       Less than 20
       </td>
       <td>
      <html:radio property="intellectualimpairment" value="100" ondblclick="uncheckRadio();"/>
       </td>
       </tr>
       </table>
  
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
	<td width="20%"><font size="2">
        6.5 Speech Defect 
        </td></font><td width="20%" align="left"><font size="0">(Please tick in the appropriate box)</font></td></tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        A. Mild Dysarthria
        </td>
        <td align="left">
        <html:radio property="speechdefect" value="0" ondblclick="uncheckRadio1();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        B. Moderate Dysarthria
        </td>
        <td align="left">
        <html:radio property="speechdefect" value="25" ondblclick="uncheckRadio1();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        C. Severe Dysarthria
        </td>
        <td align="left">
        <html:radio property="speechdefect" value="50" ondblclick="uncheckRadio1();"/>
        </td>
        </tr>
        </table>
 
         <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	 <tr class="tbl_bg2_content_hdr">
         <td width="20%"><font size="2">
         6.6.1 Motor Cranial Nerve Disability
         </td></font>
         <td width ="20%" align="left"> <font size="0">(Please tick in the appropriate box)</font> </td>
         </tr>
         <tr class="tbl_bg2_content">
         <td><ul>
         A. Oculomotor Nerve 
         </td>
         <td align="left">
         <html:checkbox property="e6a" value="20"/>
         </td>
         </tr>
         <tr class="tbl_bg2_content">
         <td><ul>
         B. Trochlear Nerve 
         </td>
         <td align="left">
        <html:checkbox property="e6b" value="20"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        C. Abducence Nerve 
       </td><ul>
       <td align="left">
      <html:checkbox property="e6c" value="20"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       D. Facial Nerve 
       </td>
       <td align="left">
      <html:checkbox property="e6d" value="20"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       E. Accessory Nerve 
       </td>
       <td align="left">
      <html:checkbox property="e6e" value="20"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       F. Hypoglossal Nerve 
       </td>
       <td align="left">
      <html:checkbox property="e6f" value="20"/>
       </td>
       </tr>
        <tr class="tbl_bg2_content">
       <td><ul>
       G. Trigeminal Nerve
       </td>
       <td align="left">
      <html:checkbox property="e6g" value="20"/>
       </td>
       </tr>
        <tr class="tbl_bg2_content">
       <td><ul>
       H. Hypoglossal Nerve
       </td>
       <td align="left">
      <html:checkbox property="ehf" value="20"/>
       </td>
       </tr>
       </table>
   
   
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
        <td width="20%"><font size="2">
        6.6.2 Sensory Cranial Nerve Disability
        </td></font>
        <td width ="20%" align="left"> <font size="0">(Please tick in the appropriate box)</font> </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        A. Olfactory Nerve 
        </td>
        <td>
       <html:checkbox property="sca" value="10"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        B. Trochlear Nerve 
        </td>
        <td>
       <html:checkbox property="scb" value="10"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       C. Vestibulocochlear Nerve 
       </td>
       <td>
      <html:checkbox property="scc" value="10"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       D. Glossopharyngeal Nerve 
       </td>
       <td>
     <html:checkbox property="scd" value="10"/>
       </td>
       </tr>
       </table>
  
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
        <td width="40%"><font size="2">
        6.7 Motor system Disability 
        </td></font>
        <td width="20%" colspan="2" align="left"><font size="0">(Please tick in the appropriate box)</font>
	</tr>
        <tr class="tbl_bg2_content">
        <td width="40%">
        <b>Neurological Involvement( Hemiparesis)
        </b></td>
        <td width="20%" align="center"><b>Right Side</td>
        <td width="20%" align="center"><b>Left Side</td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        A. Mild  
        </td>
        <td align="center">
        <html:radio property="motorsystem" value="25" ondblclick="uncheckRadio2();"/>
	</td>
	<td align="center">
        <html:radio property="motorsystem" value="25" ondblclick="uncheckRadio2();"/>
         </td>
         </tr>
         <tr class="tbl_bg2_content">
         <td><ul>
          B. Moderate 
          </td>
          <td align="center">
         <html:radio property="motorsystem" value="50" ondblclick="uncheckRadio2();"/></td>
	 <td align="center">
         <html:radio property="motorsystem" value="50" ondblclick="uncheckRadio2();"/>
          </td>
          </tr>
          <tr class="tbl_bg2_content">
         <td><ul>
         C. Severe 
         </td>
         <td align="center">
        <html:radio property="motorsystem" value="75" ondblclick="uncheckRadio2();"/>
	 </td>
	 <td align="center">
        <html:radio property="motorsystem" value="75" ondblclick="uncheckRadio2();"/>
        </td>
        </tr>
        </table>
   
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
        <td width="40%"><font size="2">
        6.8 Sensory system Disability:
        </td></font>
        <td width="20%" colspan="3" align="left"><font size="0">(Please tick in the appropriate box)</font>
        </tr>
        <tr class="tbl_bg2_content">
        <td width="40%">
       <b> Extent of Sensory Deficit</b>
       <td width="20%" align="center">
        <b>Mild</td>
		<td width="20%" align="center">
        <b>Moderate</b>
       </td>
       <td width="20%" align="center">
        <b>Severe</b></td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       A. Anesthesia  
       </td>
       <td width="20%"  align="center">
       <html:radio property="sensorysystem" value="2" ondblclick="uncheckRadio6();"/></td>
	   <td width="20%"  align="center">
      <html:radio property="sensorysystem" value="5" ondblclick="uncheckRadio6();"/>
       </td>
       <td width="20%"  align="center">
      <html:radio property="sensorysystem" value="10" ondblclick="uncheckRadio6();"/>
       </td>
       </tr>
        <tr class="tbl_bg2_content">
       <td><ul>
      B. Hypoesthesia
       </td>
       <td width="20%" align="center">
       <html:radio property="sensorysystem1" value="2" ondblclick="uncheckRadio7();"/></td>
	   <td width="20%" align="center">
      <html:radio property="sensorysystem1" value="5" ondblclick="uncheckRadio7();"/>
       </td>
        <td width="20%" align="center">
      <html:radio property="sensorysystem1" value="10" ondblclick="uncheckRadio7();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
     C. Parasthesia 
       </td>
       <td width="20%" align="center">
      <html:radio property="sensorysystem2" value="2" ondblclick="uncheckRadio8();"/></td>
	   <td width="20%" align="center">
     <html:radio property="sensorysystem2" value="5" ondblclick="uncheckRadio8();"/>
       </td>
       <td width="20%" align="center">
     <html:radio property="sensorysystem2" value="10" ondblclick="uncheckRadio8();"/>
       </td>
       </tr>
       </table>
   
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
        <td width="40%"><font size="2">
        6.8.2 
        </td></font>
        <td width="20%" colspan="3" align="left"><b></b>
        </tr>
        <tr class="tbl_bg2_content">
        <td width="40%">
       <b> Extent of Sensory Deficit</b>
       <td width="20%" align="center">
       <b>Hands</td>
	<td width="20%" align="center">
        <b>Feet</b>
        </td>
        <td width="20%" align="center">
        <b>Trunk</b></td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       Hands/Feet/Trunk
       </td>
       <td width="20%"  align="center">
       <html:checkbox property="sensorysystemh" value="4"/>
       </td>
       <td width="20%"  align="center">
       <html:checkbox property="sensorysystemf" value="4"/>
       </td>
       <td width="20%"  align="center">
       <html:checkbox property="sensorysystemt" value="4"/>
       </td></tr>
       </table>
       
      <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	 <tr class="tbl_bg2_content_hdr">
         <td width="20%"><font size="2">
         6.9 Bladder disability Due to neurological involvement:
         </td></font>
         <td width ="20%" align="left"> <font size="0">(Please tick in the appropriate box)</font> </td>
         </tr>
         <tr class="tbl_bg2_content">
         <td>
         </td>
         <td>
         </td>
         </tr>
         <tr class="tbl_bg2_content">
         <td><ul>
         A. Mild (Hesitancy and Frequency) 
         </td>
         <td>
        <html:radio property="bladderinvolvement" value="25" ondblclick="uncheckRadio3();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        B. Moderate</b>(precipitancy)
        </td>
        <td>
       <html:radio property="bladderinvolvement" value="50" ondblclick="uncheckRadio3();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       C. Severe</b>(Occasional but recurrent incontinence)
       </td>
       <td>
       <html:radio property="bladderinvolvement" value="75" ondblclick="uncheckRadio3();"/>
        </td>
        </tr>
         <tr class="tbl_bg2_content">
        <td><ul>
        D. Very Severe</b>(Retension/total incontinence) 
        </td>
        <td>
       <html:radio property="bladderinvolvement" value="100" ondblclick="uncheckRadio3();"/>
       </td>
       </tr>
       </table>
   
        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
       <td colspan="2"><font size="2">
       6.10 Post head injury fits & epileptic Convulsions:
       </td> </font>
       <tr class="tbl_bg2_content">
       <td>
       </td>
       <td>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td width="50%">
       <b>Frequency/Severity of
       Convulsions</b>
       </td>
       <td  align="left"><font size="0"><b>(Please tick in the appropriate box)</b></font></td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       A. Mild :Occurrence of one 
       convulsion only
       </td>
       <td>
       <html:radio property="injury" value="0" ondblclick="uncheckRadio4();"/>
       </td>
       </tr>
        <tr class="tbl_bg2_content">
       <td><ul>
       B. Moderate:1-5
       Convulsion/month on adequate 
       medication
       </td>
       <td>
       <html:radio property="injury" value="25" ondblclick="uncheckRadio4();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       C. Severe: 6-10 convulsion/month 
       on adequate medication
       </td>
       <td>
        <html:radio property="injury" value="50" ondblclick="uncheckRadio4();"/>
       </td>
       </tr>
        <tr class="tbl_bg2_content">
       <td><ul>
       D. Very Severe: more than 10 fits/month on adequate medication 
       </td>
       <td>
      <html:radio property="injury" value="75" ondblclick="uncheckRadio4();"/>
       </td>
       </tr>
       </table>
  
       <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
	<tr class="tbl_bg2_content_hdr">
       <td colspan="2"><font size="2">
       6.11 Ataxia
       </td></font>
       <tr class="tbl_bg2_content">
       <td>
       </td>
       <td>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td width="50%">
       <b>Severity of Ataxia</b>
       </td>
       <td  align="left"><font size="0"><b>(please tick in the appropriate box)</b></font></td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       A. Mild(Detected on examination)
       </td>
       <td>
	<html:radio property="ataxia" value="25" ondblclick="uncheckRadio5();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        B. Moderate
        </td>
        <td>
       <html:radio property="ataxia" value="50" ondblclick="uncheckRadio5();"/>
        </td>
        </tr>
        <tr class="tbl_bg2_content">
        <td><ul>
        C. Severe 
        </td>
        <td>
       <html:radio property="ataxia" value="75" ondblclick="uncheckRadio5();"/>
       </td>
       </tr>
       <tr class="tbl_bg2_content">
       <td><ul>
       D. Very Severe 
       </td>
       <td>
      <html:radio property="ataxia" value="100" ondblclick="uncheckRadio5();"/>
       </td>
       </tr>
       </table>
   
        <TABLE align="center">
      <tr class="tbl_bg2_content">
		<td><html:button property=""  value="Back" onclick="goBack()" /></td>
	
	
		<td><html:submit  value="update"/></td>
	
       
		<td><html:button  property="Reset" value="Reset" onclick="resetbutton()" /></td>
	
</TABLE>
</html:form>
  </body>
  
</html:html>
