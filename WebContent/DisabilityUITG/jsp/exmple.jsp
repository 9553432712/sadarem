<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">


<HEAD>

        
    



<script type="text/javascript" >
function checkradio1(){


  if(document.form.getAttribute("wasChecked")=="true"){
    document.form.Deformity[0].checked = false;
    document.form.setAttribute("wasChecked","false");

  } else
  {
  
    document.form.setAttribute("wasChecked","true");
	document.form.setAttribute("wadChecked","false");

	}
  }
  function checkradio2(theRadio){

 
    
  if(document.form.getAttribute("wadChecked")=="true"){
    document.form.Deformity[1].checked = false;
    document.form.setAttribute("wadChecked","false");
  } else
  {
  
    document.form.setAttribute("wadChecked","true");
	document.form.setAttribute("wasChecked","false");

	}
  }

</script>



</HEAD>


<BODY>


<html:form action="link.do" >
<table>
<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content_hdr">
		<th align="center"><font size="4">2.&nbsp;&nbsp;LOWER EXTRIMITY</font></th>
	</tr>
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="3">2.1 Lower Extremity :&nbsp;&nbsp;Mobility Component (Total value(90%))</font></th>
	</tr>
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="2">2.1.1 Active Range of Motion (ROM) ARC.&nbsp;&nbsp;(in Degrees)</font></th>
	</tr>
</TABLE>

<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content">
		<td rowspan=2><b>Joint ROM</b></td><td rowspan=2><b>Components</b></td><td rowspan=2><b>Normal Value (Degree)</b></td>
		<td colspan=2><b>Active ROM</b></td></tr>
	<tr class="tbl_bg2_content">
		<td><b>Right</td><td><b>Left</td>
	</tr>
	<tr class="tbl_bg2_content"> 
		<td><B>1.Hip Joint</B></td>
		<td>1. &nbsp;Flexion-Extension arc <br><BR>2. &nbsp;Rotation arc<br><BR>3.&nbsp;Abduction-Adduction arc</td>
		<td align="center">0-140<br><BR>0-90<br><BR>0-90</td>
		<td><html:text  size="6" property="rom11r" /><br><html:text size="6" property="rom12r"/><br><html:text  size="6" property="rom13r"/></td>
		<td><html:text size="6" property="rom11l"/><br><html:text size="6" property="rom12l"/><br><html:text  size="6" property="rom13l"/></td>
	</tr>

	<tr class="tbl_bg2_content"> 
		<td><B>2.Knee Joint</B></td>
		<td>1.&nbsp; Flexion-Extension arc </td>
		<td align="center">0-125</td>
		<td><html:text size="6" property="rom21r"/></td>
		<td><html:text size="6" property="rom21l"/></td>
	</tr>
	<tr class="tbl_bg2_content"> 
		<td><B>3.Ankle & Foot joint</B></td>
		<td>1.&nbsp; Dorsiflexion-palmarflexion arc <br><BR>2.&nbsp; Inversion- Eversion arc</td>
		<td align="center">0-70<br><BR>0-60</td>
		<td><html:text size="6" property="rom31r"/><br><html:text  size="6" property="rom32r"/></td>
		<td><html:text size="6" property="rom31l"/><br><html:text  size="6" property="rom32l"/></td>
	</tr>
</TABLE>

<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="2">2.1.2 &nbsp;&nbsp;Muscle Strength.(Normal value = Grade 5 for all)</font></th>
	</tr>
</TABLE>

<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content">
		<td rowspan=2><b>Joint</b></td><td rowspan=2><b>Components</b></td>
		<td rowspan=2><b>Normal Muscle Grade</b></td>
		<td colspan=2><b>Active Muscle Grade</b></td></tr>
	<tr class="tbl_bg2_content">
		<td><b>Right</td><td><b>Left</td>
	</tr>
	<tr class="tbl_bg2_content"> 
		<td><B>1. Hip Joint </B></td>
		<td>1.&nbsp;Flexor Muscles<br><BR>2.&nbsp;Extensor Muscles<br><BR>3.&nbsp;Rotator Muscles<br><BR>4.&nbsp;Abductor Muscles<br><BR>5.&nbsp;Adductor Muscles</td>
		<td align="center">0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
		<td><html:text size="6" property="ms11r"/><br><html:text size="6" property="ms12r" /><br><html:text  size="6" property="ms13r"/><br><html:text  size="6" property="ms14r"/><br><html:text  size="6" property="ms15r"/></td>
		<td><html:text size="6" property="ms11l"/><br><html:text  size="6" property="ms12l"/><br><html:text  size="6" property="ms13l"/><br><html:text  size="6" property="ms14l"/><br><html:text  size="6" property="ms15l"/></td>
	</tr>
	<tr class="tbl_bg2_content"> 
		<td><B>2. Knee Joint</B></td>
		<td>1.&nbsp;Flexor Muscles <br><BR>2.&nbsp;Extensor Muscles</td>
		<td align="center">0-5<br><BR>0-5</td>
		<td><html:text size="6" property="ms21r"/><br><html:text  size="6" property="ms22r"/></td>
		<td><html:text size="6" property="ms21l"/><br><html:text  size="6" property="ms22l"/></td>
	</tr>
	<tr class="tbl_bg2_content"> 
		<td><B>3. Ankle & Foot joint</B></td>
		<td>1.&nbsp;Planterflexor Muscles<br><BR>2.&nbsp;Dorsiflexor Muscles<br><BR>3.&nbsp;Invertor Muscles<br><BR>4.&nbsp;Evertor Muscles</td>
		<td align="center">0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
		<td><html:text size="6" property="ms31r"/><br><html:text size="6" property="ms32r"/><br><html:text  size="6" property="ms33r"/><br><html:text  size="6" property="ms34r"/></td>
		<td><html:text size="6" property="ms31l"/><br><html:text size="6" property="ms32l"/><br><html:text  size="6" property="ms33l"/><br><html:text  size="6" property="ms34l"/></td>
	</tr>
</TABLE>



<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="2">2.1.3 &nbsp;&nbsp;Presence of complications&nbsp;&nbsp;(Select any one field)</th>
	</tr>
</TABLE>


<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	        <tr class="tbl_bg2_content">
		<th rowspan=3 align="left">1.Deformity</th>
	</tr>
	<tr class="tbl_bg2_content">
		<td width="50%"><ul>a.&nbsp;&nbsp;In function position </td>
		<td align = "center"><html:radio  property="deformity" value ="pc1a"  onclick="checkradio1()"></html:radio></td></ul>
	</tr>
	<tr class="tbl_bg2_content">
		<td><ul>b.&nbsp;&nbsp;In non-function position </td>
		<td align = "center"><html:radio  property="deformity" value ="pc1b" ></html:radio></td></ul>
	</tr>

	<tr class="tbl_bg2_content">
		<th rowspan=4 align="left">2.Pain</th>
	</tr>

	<tr class="tbl_bg2_content">
		<td width="50%"><ul>a.&nbsp;&nbsp;Severe (grossly interfering with function) </td>
		<td align = "center"><html:radio  property="pain" value ="pc2a" ></html:radio></td></ul>
	</tr>
	<tr class="tbl_bg2_content">
		<td><ul>b.&nbsp;&nbsp;Moderate (moderately interfering with function)</td>
		<td align = "center"><html:radio  property="pain" value ="pc2b" ></html:radio></td></ul>
	</tr>
	
        <tr class="tbl_bg2_content">
		<td><ul>c.&nbsp;&nbsp;Mild (mildly interfering with function)</td>
		<td align = "center"><html:radio  property="pain" value ="pc2c" ></html:radio></td></ul>
	</tr>
	
	
        <tr class="tbl_bg2_content">
		<th rowspan="3" align="left">3.Loss of Function</th>
	</tr>
         <tr class="tbl_bg2_content">
		<td><ul>a.&nbsp;&nbsp;Complete loss </td>
		<td align = "center"><html:radio  property="loss_of_Function" value="pc3a" ></html:radio></td></ul>
	</tr>
        <tr class="tbl_bg2_content">
		<td><ul>b.&nbsp;&nbsp;Partial loss </td>
		<td align = "center"><html:radio  property="loss_of_Function" value="pc3b" ></html:radio></td></ul>
	</tr>

        <tr class="tbl_bg2_content">
		<th rowspan="3" align="left">4.Complications</th>
	</tr>
        <tr class="tbl_bg2_content">
		<td width="50%"><ul>a.&nbsp;&nbsp;Superficial complications </td>
		<td align = "center"><html:radio  property="complications" value ="pc4a" ></html:radio></td></ul>
	</tr>
	<tr class="tbl_bg2_content">
		<td><ul>b.&nbsp;&nbsp;Deep complications </td>
		<td align = "center"><html:radio  property="complications" value ="pc4b" ></html:radio></td></ul>
	</tr>
</TABLE>

<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="2">2.1.4&nbsp;&nbsp;Shortening of Limb</th>
	</tr>
</TABLE>
<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content" >
		<td width="50%"><b>a.&nbsp;&nbsp;Mention in inches <b></td>
		<td><html:text property="inches"/><font size="1">(mention in numbers)</font></td></ul>
	</tr>
</TABLE>
<TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content_hdr">
		<th align="left"><font size="3">2.2 Lower Extremity :&nbsp;&nbsp;Stability Component (Total value=90%)</th>
	</tr>
</TABLE>
                    
                    
 <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
	<tr class="tbl_bg2_content">
		<td rowspan=2><b>&nbsp;Components</b></td>
		<td colspan=3 align="center"><b>Please Tick in the appropriate box</b></td></tr>
	<tr class="tbl_bg2_content">
		<td align ="center"><b><br>Perform<br>without any<br>difficulty</br></td>
		<td align ="center"><b><br>Perform<br> with<br>difficulty</br></td>
		<td align ="center"><b><br>Cannot <br> perform</br></td>
	</tr>
        <tr class="tbl_bg2_content">
		<td>2.2.1&nbsp;Working on plane surface </td>
		<td align = "center"><html:radio  property="working_on_plane" value ="sc1a"></html:radio></td>
		<td align = "center"><html:radio  property="working_on_plane" value ="sc1b" ></html:radio></td>
		<td align = "center"><html:radio  property="working_on_plane" value ="sc1c" ></html:radio></td>
	</tr>
	<tr class="tbl_bg2_content">
		<td>2.2.2&nbsp;Working on slope</td>
		<td align = "center"><html:radio  property="working_on_slope" value ="sc2a" ></html:radio></td>
		<td align = "center"><html:radio  property="working_on_slope" value ="sc2b" ></html:radio></td>
		<td align = "center"><html:radio  property="working_on_slope" value ="sc2c" ></html:radio></td>
	</tr>
	<tr class="tbl_bg2_content">
		<td>2.2.3&nbsp;Climbing stairs </td>
		<td align = "center"><html:radio  property="working_on_stairs" value ="sc3a" ></html:radio></td>
		<td align = "center"><html:radio  property="working_on_stairs" value ="sc3b" ></html:radio></td>
		<td align = "center"><html:radio  property="working_on_stairs" value ="sc3c" ></html:radio></td>
	</tr>
        <tr class="tbl_bg2_content">
		<td>2.2.4&nbsp;Standing on both legs </td>
		<td align = "center"><html:radio  property="standing_on_both_legs" value ="sc4a" ></html:radio></td>
		<td align = "center"><html:radio  property="standing_on_both_legs" value ="sc4b" ></html:radio></td>
		<td align = "center"><html:radio  property="standing_on_both_legs" value ="sc4c" ></html:radio></td>
	</tr>
        <tr class="tbl_bg2_content">
		<td>2.2.5&nbsp;Standing on effected leg </td>
		<td align = "center"><html:radio  property="standing_on_effected" value ="sc5a" ></html:radio></td>
		<td align = "center"><html:radio  property="standing_on_effected" value ="sc5b" ></html:radio></td>
		<td align = "center"><html:radio  property="standing_on_effected" value="sc5c" ></html:radio></td>
	</tr>
        
        
        <tr class="tbl_bg2_content">
		<td>2.2.6&nbsp;Squatting on floor</td>
		<td align = "center"><html:radio  property="squatting_on_floor" value ="sc6a" ></html:radio></td>
		<td align = "center"><html:radio  property="squatting_on_floor" value ="sc6b" ></html:radio></td>
		<td align = "center"><html:radio  property="squatting_on_floor" value ="sc6c" ></html:radio></td>
	</tr>
        
        <tr class="tbl_bg2_content">
		<td>2.2.7&nbsp;Sitting cross leg </td>
		<td align = "center"><html:radio  property="sitting_cross_leg" value ="sc7a" ></html:radio></td>
		<td align = "center"><html:radio  property="sitting_cross_leg" value ="sc7b" ></html:radio></td>
		<td align = "center"><html:radio  property="sitting_cross_leg" value ="sc7c" ></html:radio></td>
	</tr>
        <tr class="tbl_bg2_content">
		<td>2.2.8&nbsp;Kneeling</td>
		<td align = "center"><html:radio  property="kneeling" value ="sc8a" ></html:radio></td>
		<td align = "center"><html:radio  property="kneeling" value ="sc8b" ></html:radio></td>
		<td align = "center"><html:radio  property="kneeling" value ="sc8c" ></html:radio></td>
	</tr>
        
	<tr class="tbl_bg2_content">
		<td>2.2.9&nbsp;Taking turns </td>
		<td align = "center"><html:radio  property="taking_turns" value ="sc9a" ></html:radio></td>
		<td align = "center"><html:radio  property="taking_turns" value ="sc9b" ></html:radio></td>
		<td align = "center"><html:radio  property="taking_turns" value ="sc9c" ></html:radio></td>
	</tr>
                      
 </TABLE>
                    

                  
<TABLE align="center">
	<tr class="tbl_bg2_content">
		<td><html:submit  value="Add"></html:submit></td>
	</tr>
</TABLE>
<TABLE align="center">
	<tr class="tbl_bg2_content">
		<a href="index.jsp"><b>Back</b> </a>
	</tr>
</TABLE>

 </table>
 </html:form>
 </body>
 </html:html>
