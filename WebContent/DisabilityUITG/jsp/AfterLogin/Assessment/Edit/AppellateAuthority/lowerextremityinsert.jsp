<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page session="true"%>
<html:html>
 <script language="javascript" >


      function validateshortning(){
            var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
            var shortening=document.lowerextremityform.shortening.value;
            for (var i = 0; i < shortening.length; i++) {
  	    if (iChars.indexOf(shortening.charAt(i)) != -1) {
  	    alert ("Please Enter Valid shortening.");
            document.lowerextremityform.shortening.value="";
            document.lowerextremityform.shortening.focus();
  	    return false;
  	    }
            }
            if(isNaN(shortening))
            {
            alert("Enter Numbers only in your shortening")
            document.lowerextremityform.shortening.value="";
            document.lowerextremityform.shortening.focus();
             return false;
            }
            if(parseInt(shortening)<0 ||
            parseInt(shortening)>99)
            {
            alert ("Please enter yours valid shortening!");
            document.lowerextremityform.shortening.value="";
            document.lowerextremityform.shortening.focus();
            return false;
            }
            else{
             document.getElementById('loexButton').disabled = true;
            return true;
            }
            }




    function goBack()
    {
    document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
    document.forms[0].submit();
    }

   function removeSpaces(string)
   {
    return string.split(' ').join('');
   }

     function isNumber5(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>5){
alert('Value must be between 0 and 5!')
field.value ='';
field.focus();
}
}

function isNumber140(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>140){
alert('Value must be between 0 and 140!')
field.value ='';
field.focus();
}
}

function isNumber125(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>125){
alert('Value must be between 0 and 125!')
field.value ='';
field.focus();
}
}

function isNumber90(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>90){
alert('Value must be between 0 and 90!')
field.value ='';
field.focus();
}
}

function isNumber70(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>70){
alert('Value must be between 0 and 70!')
field.value ='';
field.focus();
}
}

function isNumber60(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>60){
alert('Value must be between 0 and 60!')
field.value ='';
field.focus();
}
}

 </script>

 
 <body >
 <script language="JavaScript" src="./DisabilityUITG/js/LowerExtremity.js"></script>
 <html:form action="/LowerExtremityInsert.do?insertLowerExtremityDetails=insertLowerExtremityDetails" method="post" styleId="lowerextremityform" onsubmit="return validateshortning();">
 <html:errors/>
 <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
 
    <tr> <th colspan="8">ADD LOWER EXTREMITY</th></tr>
   <tr>
       <th colspan="8">2.1 Lower Extremity :&nbsp;&nbsp;Mobility Component (Total value=90%)</th>
   </tr>
   <tr>
       <td colspan="8">2.1.1 Active Range of Motion (ROM) ARC.&nbsp;&nbsp;(in Degrees)</td>
   </tr>
<%-- </table>
 <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">--%>
   <tr>
       <th rowspan=2> ROM</th>
       <th rowspan=2>Components</th>
       <th rowspan=2>Normal Value (Degree)</th>
       <th colspan=2 align="center">Active ROM</th>
   </tr>
   <tr>
   <th>Right</th>
   <th>Left</th>
   </tr>
   <tr>
   <td>1. Hip Joint</td>
   <td >A. &nbsp;Flexion-Extension arc <br><BR>B. &nbsp;Abduction-Adduction arc<br><BR>C.&nbsp;Rotation arc</td>
   <td align="center" >0-140<br><BR>0-90<br><BR>0-90</td>
   <td >
       <html:text  size="6" property="romhipflexionextensionright" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber140(this)"/><br>
       <html:text  size="6" property="romhipabductionadductionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber90(this)"/><br>
       <html:text size="6" property="romhiprotationright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber90(this)"/></td>
   <td ><html:text size="6" property="romhipflexionextensionleft" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber140(this)"/><br>
       <html:text  size="6" property="romhipabductionadductionleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber90(this)"/><br>
       <html:text size="6" property="romhiprotationleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber90(this)"/></td>
   </tr>
   <tr>
   <td>2. Knee Joint</td>
   <td >A.&nbsp; Flexion-Extension arc </td>
   <td align="center" >0-125</td>
   <td >
       <html:text size="6" property="romkneeflexionextensionright" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber125(this)"/></td>
   <td>
       <html:text size="6" property="romkneeflexionextensionleft" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber125(this)"/></td>
   </tr>
   <tr>
   <td>3. Ankle & Foot joint</td>
   <td >A.&nbsp; Dorsiflexion-plantar flexion arc <br><BR>B.&nbsp; Inversion- Eversion arc</td>
   <td align="center" >0-70<br><BR>0-60</td>
   <td >
       <html:text size="6" property="romankledorsiflexionpalmarflexionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber70(this)"/><br>
       <html:text  size="6" property="romankleinversioneversionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber60(this)"/></td>
   <td >
       <html:text size="6" property="romankledorsiflexionpalmarflexionleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber70(this)"/><br>
       <html:text  size="6" property="romankleinversioneversionleft"  maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber60(this)"/></td>
   </tr>
<%-- </table>
 <table  align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">--%>
   <tr>
       <td colspan="8"><font size="2">2.1.2 &nbsp;&nbsp;Muscle Strength.(Normal value = Grade 5 for all)</font></td>
   </tr>
<%-- </table>
 <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">--%>
   <tr>
       <th rowspan=2 width="20%">Joint</th>
       <th rowspan=2 width="35%">Components</th>
   <th rowspan=2>Normal Muscle Grade</th>
   <th colspan=2 align="center">Active Muscle Grade</th></tr>
   <tr>
   <th>Right</th>
   <th>Left</th>
   </tr>
   <tr>
   <td>1. Hip Joint </td>
   <td >A.&nbsp;Flexor Muscles<br><BR>B.&nbsp;Extensor Muscles<br><BR>C.&nbsp;Rotator Muscles<br><BR>D.&nbsp;Abductor Muscles<br><BR>E.&nbsp;Adductor Muscles</td>
   <td align="center" >0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
   <td >
       <html:text size="6" property="mshipflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text size="6" property="mshipextensormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshiprotatormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshipabductormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshipadductormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       </td>
   <td >
       <html:text size="6" property="mshipflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshipextensormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshiprotatormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshipabductormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mshipadductormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
   </tr>
   <tr>
   <td>2. Knee Joint</td>
   <td >A.&nbsp;Flexor Muscles <br><BR>B.&nbsp;Extensor Muscles</td>
   <td align="center" >0-5<br><BR>0-5</td>
   <td >
       <html:text size="6" property="mskneeflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mskneeextensormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
   <td >
       <html:text size="6" property="mskneeflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="mskneeextensormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
   </tr>
   <tr>
   <td>3. Ankle & Foot joint</td>
   <td >A.&nbsp;Planterflexor Muscles<br><BR>B.&nbsp;Dorsiflexor Muscles<br><BR>C.&nbsp;Invertor Muscles<br><BR>D.&nbsp;Evertor Muscles</td>
   <td align="center" >0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
   <td >
       <html:text size="6" property="msankleplanterflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text size="6" property="msankledorsiflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="msankleinvertormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="msankleevertormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
   <td >
       <html:text size="6" property="msankleplanterflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text size="6" property="msankledorsiflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="msankleinvertormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/><br>
       <html:text  size="6" property="msankleevertormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
   </tr>
 <%--</table>
 <table  align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">--%>
   <tr>
       <td colspan="8">2.2 Lower Extremity :&nbsp;&nbsp;Stability Component (Total value=90%)</td>
   </tr>
<%-- </table>
 <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">--%>
   <tr>
       <th rowspan=2 width="55%">&nbsp;Components</th>
   <th colspan="4" align="center" >Please Tick in the appropriate box</th>
   </tr>
   <tr>
       <th align ="center" width="21%">Perform without any difficulty</th>
   <th align ="center">Perform  with difficulty</th>
   <th align ="center" colspan="2">Cannot perform</th>
   </tr>
   <tr>
   <td>2.2.1&nbsp;Walking on plane surface </td>
   <td align = "center"><html:radio  property="working_on_plane" value ="0" onclick="checkradioworking_on_plane1()"></html:radio></td>
   <td align = "center"><html:radio  property="working_on_plane" value ="5" onclick="checkradioworking_on_plane2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="working_on_plane" value ="10" onclick="checkradioworking_on_plane3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.2&nbsp;Walking on slope</td>
   <td align = "center"><html:radio  property="working_on_slope" value ="0" onclick="checkradioworking_on_slope1()"></html:radio></td>
   <td align = "center"><html:radio  property="working_on_slope" value ="5" onclick="checkradioworking_on_slope2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="working_on_slope" value ="10" onclick="checkradioworking_on_slope3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.3&nbsp;Climbing stairs </td>
   <td align = "center"><html:radio  property="working_on_stairs" value ="0" onclick="checkradioworking_on_stairs1()"></html:radio></td>
   <td align = "center"><html:radio  property="working_on_stairs" value ="5" onclick="checkradioworking_on_stairs2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="working_on_stairs" value ="10" onclick="checkradioworking_on_stairs3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.4&nbsp;Standing on both legs </td>
   <td align = "center"><html:radio  property="standing_on_both_legs" value ="0" onclick="checkradiostanding_on_both_legs1()"></html:radio></td>
   <td align = "center"><html:radio  property="standing_on_both_legs" value ="5"  onclick="checkradiostanding_on_both_legs2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="standing_on_both_legs" value ="10"  onclick="checkradiostanding_on_both_legs3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.5&nbsp;Standing on affected leg </td>
   <td align = "center"><html:radio  property="standing_on_effected" value ="0" onclick="checkradiostanding_on_effected1()"></html:radio></td>
   <td align = "center"><html:radio  property="standing_on_effected" value ="5" onclick="checkradiostanding_on_effected2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="standing_on_effected" value="10" onclick="checkradiostanding_on_effected3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.6&nbsp;Squatting on floor</td>
   <td align = "center"><html:radio  property="squatting_on_floor" value ="0" onclick="checkradiosquatting_on_floor1()"></html:radio></td>
   <td align = "center"><html:radio  property="squatting_on_floor" value ="5"  onclick="checkradiosquatting_on_floor2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="squatting_on_floor" value ="10"  onclick="checkradiosquatting_on_floor3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.7&nbsp;Sitting cross leg </td>
   <td align = "center"><html:radio  property="sitting_cross_leg" value ="0"  onclick="checkradiositting_cross_leg1()"></html:radio></td>
   <td align = "center"><html:radio  property="sitting_cross_leg" value ="5" onclick="checkradiositting_cross_leg2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="sitting_cross_leg" value ="10" onclick="checkradiositting_cross_leg3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.8&nbsp;Kneeling</td>
   <td align = "center"><html:radio  property="kneeling" value ="0" onclick="checkradiokneeling1()"></html:radio></td>
   <td align = "center"><html:radio  property="kneeling" value ="5" onclick="checkradiokneeling2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="kneeling" value ="10" onclick="checkradiokneeling3()"></html:radio></td>
   </tr>
   <tr>
   <td>2.2.9&nbsp;Taking turns </td>
   <td align = "center"><html:radio  property="taking_turns" value ="0" onclick="checkradiotaking_turns1()"></html:radio></td>
   <td align = "center"><html:radio  property="taking_turns" value ="5" onclick="checkradiotaking_turns2()"></html:radio></td>
   <td align = "center" colspan="2"><html:radio  property="taking_turns" value ="10" onclick="checkradiotaking_turns3()"></html:radio></td>
   </tr>

   <tr>
       <td colspan="8">2.3 &nbsp;&nbsp;Presence of complications (Additional Points)&nbsp;&nbsp;(Select any one field)</td>
	</tr>

	        <tr>
                    <td rowspan=3 width="20%">1. Deformity</td>
	</tr>
	<tr>
		<td width="56%" ><ul>a.&nbsp;&nbsp;In functional position </td>
                <td align = "center" colspan="6"><html:radio  property="deformity" value ="3" onclick="checkradiodeformity1()" ></html:radio></td></ul>
	</tr>
	<tr>
		<td ><ul>b.&nbsp;&nbsp;In non-functional position </td>
		<td align = "center" colspan="6"><html:radio  property="deformity" value ="6" onclick="checkradiodeformity2()"></html:radio></td></ul>
	</tr>
        <tr>
		<td rowspan=4>2. Pain</td>
	</tr>
        <tr>
		<td width="50%" ><ul>a.&nbsp;&nbsp;Severe (grossly interfering with function) </td>
		<td align = "center" colspan="6"><html:radio  property="pain" value ="9" onclick="checkradiopain1()"></html:radio></td></ul>
	</tr>
        <tr>
		<td width="50%" ><ul>b.&nbsp;&nbsp;Moderate (moderately interfering with function)</td>
		<td align = "center" colspan="6"><html:radio  property="pain" value ="6" onclick="checkradiopain2()"></html:radio></td></ul>
	</tr>
         <tr>
		<td width="50%" ><ul>c.&nbsp;&nbsp;Mild (mildly interfering with function)</td>
		<td align = "center" colspan="6"><html:radio  property="pain" value ="3"onclick="checkradiopain3()" ></html:radio></td></ul>
	</tr>
         <tr>
		<td rowspan="3">3. Loss of Function</td>
	</tr>
         <tr>
		<td ><ul>a.&nbsp;&nbsp;Complete loss </td>
		<td align = "center" colspan="6"><html:radio  property="loss_of_Function" value="9" onclick="checkradioloss_of_Function1()"></html:radio></td></ul>
	</tr>
           <tr>
		<td ><ul>b.&nbsp;&nbsp;Partial loss </td>
		<td align = "center" colspan="6"><html:radio  property="loss_of_Function" value="6" onclick="checkradioloss_of_Function2()"></html:radio></td></ul>
	</tr>
        <tr>
		<td rowspan="3">4. Complications</td>
	</tr>
          <tr>
		<td width="50%" ><ul>a.&nbsp;&nbsp;Superficial complications </td>
		<td align = "center" colspan="6"><html:radio  property="complications" value ="3" onclick="checkradiocomplications1()"></html:radio></td></ul>
	</tr>
        <tr>
		<td ><ul>b.&nbsp;&nbsp;Deep complications </td>
		<td align = "center" colspan="6"><html:radio  property="complications" value ="5" onclick="checkradiocomplications2()"></html:radio></td></ul>
	</tr>

	<tr>
            <th colspan="8">2.4&nbsp;&nbsp;Shortening of Limb</th>
	</tr>
<%--</table>
 <table  align="center" cellspacing="1" cellpadding="5"  class="innerTable1" width="85%">--%>
	<tr>
		<td width="20%">a.&nbsp;&nbsp;Mention in inches </td>
                <td colspan="4"><html:text property="shortening"  maxlength="4" onblur="this.value=removeSpaces(this.value);">

                                </html:text><font size="1">(mention in numbers)</font></td>
	</tr>

	<tr>

            <th colspan="8"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
          <html:submit  value="Add"  styleId="loexButton" styleClass="button"></html:submit>
           <html:reset value="Reset" onclick="resetbutton(this.form)" styleClass="button"/></th>
	</tr>
 </table>




 </html:form>
 </body>
 </html:html>

