<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<script>

    function validateinches(){
            var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
            var inches=document.UpperExtrimityActionForm.inches.value;
            for (var i = 0; i < inches.length; i++) {
  	    if (iChars.indexOf(inches.charAt(i)) != -1) {
  	    alert ("Please Enter Valid inches.");
            document.UpperExtrimityActionForm.inches.value="";
            document.UpperExtrimityActionForm.inches.focus();
  	    return false;
  	    }
            }
            if(isNaN(inches))
            {
            alert("Enter Numbers only in your inches")
            document.UpperExtrimityActionForm.inches.value="";
            document.UpperExtrimityActionForm.inches.focus();
             return false;
            }
            if(parseInt(inches)<0 ||
            parseInt(inches)>99)
            {
            alert ("Please enter yours valid inches!");
            document.UpperExtrimityActionForm.inches.value="";
            document.UpperExtrimityActionForm.inches.focus();
            return false;
            }
            return true;
            }


    function goBack()
{
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
}

 function removeSpaces(string)
   {
    return string.split(' ').join('');
   }



function checkIt(evt) {
    evt = (evt) ? evt : window.event
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        status = "This field accepts numbers only."
        return false
    }
    status = ""
    return true
}


function isNumber220(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>220){
alert('Value must be between 0 and 220!')
field.value ='';
field.focus();
}
}

function isNumber180(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>180){
alert('Value must be between 0 and 180!')
field.value ='';
field.focus();
}
}

function isNumber160(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>160){
alert('Value must be between 0 and 160!')
field.value ='';
field.focus();
}
}

function isNumber150(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>150){
alert('Value must be between 0 and 150!')
field.value ='';
field.focus();
}
}

function isNumber55(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>55){
alert('Value must be between 0 and 55!')
field.value ='';
field.focus();
}
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
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:11px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:12px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #000 solid; border-top:1px #000 solid;
}

/* GRID Ends */
</style>
<html:html>



    <BODY onLoad="disableForm(UpperExtrimityActionForm);">

        
           <script language="JavaScript" src="./DisabilityUITG/js/UpperExtremity.js"></script>
      <html:form action="/modify.do"
                 styleId="UpperExtrimityActionForm" onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');">
    <html:errors/>
    <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >
    <table align="center" cellspacing="0" cellpadding="0" width="100%">
    	<tr>
        	<td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
    	</tr>
    </table>
    <table  align="center" cellspacing="2" cellpadding="0" class="innerTable" width="100%">
    	<tr>
        	<td class="gridhdbg" style="border:1px #000 solid; height:30px; font-size:16px;">1. 1 UPPER EXTREMITY : Arm Component (Assess the affected component only) (Total Value = 90%)</td>
        </tr>
        <tr>
        	<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        	  <tr>
        	    <td>
                    <table width="100%" border="0" cellspacing="2" cellpadding="0">
                      <tr>
                        <td width="33%" align="center" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                          <tr>
                            <td colspan="6" class="gridhdbg" style="text-align:left;">1.1.1  Active Range of Motion (ROM) ARC(in degrees)</td>
                          </tr>
                          <tr>
                            <td width="34%" rowspan="2" class="gridhdbg">Joint<br />
                              Component</td>
                            <td width="16%" rowspan="2" align="center" class="gridhdbg">Normal<br />
                              Range<br />
                              (Degree)</td>
                            <td colspan="2" class="gridhdbg" align="center">Old<br />
                              Active<br />
                              ROM</td>
                            <td colspan="2" class="gridhdbg" align="center">New<br />
                              Active<br />
                              ROM</td>
                          </tr>
                          <tr>
                            <td width="10%" class="gridhdbg">Right</td>
                            <td width="10%" class="gridhdbg">Left</td>
                            <td width="15%" class="gridhdbg">Right</td>
                            <td width="15%" class="gridhdbg">Left</td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg" style="text-align:left;">1. Shoulder Joint</td>
                          </tr>
                          <tr>
                            <td align="left" class="gridbg1">1. Flexion-Extension <br /></td>
                            <td align="center" class="gridbg1">0-220 <br /></td>
                            <td class="gridbg1"><html:text  size="6" property="rom_sf_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="rom_sf_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1" style="line-height:30px;"><input type="text"  size="6" name="re" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td align="left" class="gridbg1">2. &nbsp;Abduction-Adduction </td>
                            <td align="center" class="gridbg1">0-180</td>
                            <td class="gridbg1"><html:text  size="6" property="rom_sa_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><html:text size="6" property="rom_sa_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1" style="line-height:30px;"><span class="gridbg1" style="line-height:30px;">
                              <input type="text"  size="6" name="re3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/>
                            </span></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re5" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                          </tr>
                          <tr>
                            <td align="left" class="gridbg1">3.&nbsp;Rotation </td>
                            <td align="center" class="gridbg1">0-180</td>
                            <td class="gridbg1"><html:text  size="6" property="rom_sr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="rom_sr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1" style="line-height:30px;"><span class="gridbg1" style="line-height:30px;">
                              <input type="text"  size="6" name="re4" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/>
                            </span></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re6" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg" style="text-align:left;">2. Elbow Joint</td>
                          </tr>
                          <tr>
                            <td class="gridbg1">1.&nbsp; Flexion-Extension</td>
                            <td align="center" class="gridbg1">0-150</td>
                            <td class="gridbg1"><html:text  size="6" property="rom_ef_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber150(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="rom_ef_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber150(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">2. &nbsp;Supination-Pronation </td>
                            <td align="center" class="gridbg1">0-180</td>
                            <td class="gridbg1"><html:text  size="6"  property="rom_es_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><html:text  size="6"property="rom_es_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re8" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re7" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg" style="text-align:left;">3. Wrist Joint</td>
                          </tr>
                          <tr>
                            <td class="gridbg1">1.&nbsp; Dorsiflexion-Palmar flexion <br /></td>
                            <td align="center" class="gridbg1">0-160</td>
                            <td class="gridbg1"><html:text  size="6" property="rom_wd_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber160(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="rom_wd_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber160(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">2.&nbsp; Radial- Ulnar deviation </td>
                            <td align="center" class="gridbg1">0-55</td>
                            <td class="gridbg1"><html:text   size="6" property="rom_wr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber55(this)"/></td>
                            <td class="gridbg1"><html:text   size="6" property="rom_wr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber55(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re10" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re9" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)"/></td>
                          </tr>
                        </table></td>
                        <td width="33%" align="center" valign="top"><table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" class="gridtb">
                          <tr>
                            <td colspan="6" class="gridhdbg">1.1.2 Muscle Strength: (Normal Value = Grade 5) Please write the muscle grade)</td>
                          </tr>
                          <tr>
                            <td width="27%" rowspan="2" class="gridhdbg">Component</td>
                            <td rowspan="2" class="gridhdbg" align="center" width="14%">Normal<br />
                              Muscle<br />
                              Grade</td>
                            <td colspan="2" class="gridhdbg">Old
                              Active<br />
                              Muscle<br />
                              Grade</td>
                            <td colspan="2" class="gridhdbg">New
                              Active<br />
                              Muscle<br />
                              Grade</td>
                          </tr>
                          <tr>
                            <td width="15%" class="gridhdbg">Right</td>
                            <td width="14%" class="gridhdbg">Left </td>
                            <td width="15%" class="gridhdbg">Right</td>
                            <td width="15%" class="gridhdbg">Left </td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg">1.Shoulder Joint Muscle Strength Value 90%</td>
                          </tr>
                          <tr>
                            <td class="gridbg1">1.&nbsp;Flexion </td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sf_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sf_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">2.&nbsp;Extension<br /></td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_se_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_se_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re12" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">3.&nbsp;Abduction</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text   size="6" property="ms_sab_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text   size="6" property="ms_sab_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">4.&nbsp;Adduction</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sad_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sad_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">5.&nbsp;Ext-Rotation</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sext_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sext_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">6.&nbsp;Int-Rotation</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sint_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_sint_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg">2. Elbow Joint Muscle Strength Value 90%</td>
                          </tr>
                          <tr>
                            <td class="gridbg1">1.&nbsp; Flexion </td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ef_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ef_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">2.&nbsp;Extension</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ee_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ee_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re18" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re15" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">3.&nbsp;Pronation</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ep_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_ep_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re17" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re14" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">4.&nbsp;Supination </td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6"property="ms_es_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_es_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re16" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re13" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td colspan="6" class="gridhdbg">3. Wrist Joint Muscle Strength Value 90%</td>
                          </tr>
                          <tr>
                            <td class="gridbg1">1. Dorsiflexion</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text  size="6" property="ms_wd_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text  size="6" property="ms_wd_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re11" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">2.&nbsp; Palmar Flexion</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text   size="6" property="ms_wp_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text   size="6" property="ms_wp_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re24" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re21" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">3.&nbsp;Radial Deviation</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text   size="6"property="ms_wr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text   size="6" property="ms_wr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re23" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re20" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">4.&nbsp; Ulnar Deviation</td>
                            <td align="center" class="gridbg1">0-5</td>
                            <td class="gridbg1"><html:text   size="6" property="ms_wu_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><html:text   size="6" property="ms_wu_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re22" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                            <td class="gridbg1"><input type="text"  size="6" name="re19" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)"/></td>
                          </tr>
                        </table></td>
                        <td width="33%" align="center" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                          <tr>
                            <td colspan="3" class="gridhdbg">1.1.3 Coordinated Activities: (Tick if not able to do)</td>
                          </tr>
                          <tr>
                            <td  class="gridhdbg">Components value 90% </td>
                            <td colspan="1" class="gridhdbg" align="center">Old Loss of Coordinated Activities</td>
                            <td colspan="1" class="gridhdbg" align="center">New Loss of Coordinated Activities</td>
                          </tr>
                          <tr>
                            <td width="56%" class="gridbg1"> A.&nbsp;Lifting overhead objects,remove and <br />
                              &nbsp;&nbsp;&nbsp;&nbsp;placing at the same place(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox  property="coordinate_lifting" value ="9"></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">B.&nbsp;Touching nose with end of the extremity(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_touching" value ="9" ></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1"> C.&nbsp;Eating Indian style(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_eating" value ="9" ></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1"> D.&nbsp;Combing and Plaiting (9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_combing" value ="9" ></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1"> E.&nbsp;Putting on a shirt/kurtha(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_putting" value ="9" ></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1"> F.&nbsp;Ablution glass of water(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_ablution" value ="9" ></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1"> G.&nbsp;Buttoning(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_buttoning" value ="9"></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">H.&nbsp;Tie Nara Dhoti(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_tie" value ="9"></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">I.&nbsp;Writing(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_writing" value ="9"></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                          <tr>
                            <td class="gridbg1">J.&nbsp;Drinking Glass of Water(9%)</td>
                            <td align ="center" class="gridbg1"><html:checkbox property="coordinate_drinking" value ="9"></html:checkbox></td>
                            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting2" value ="9"/></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table>
              </td>
      	    </tr>
          </table></td>
        </tr>
        <tr>
        	<td class="gridhdbg" style="border:1px #000 solid; height:30px; font-size:16px;">1. 2 UPPER EXTREMITY Hand Component (Assess the affected component only) (Total Value = 90%)</td>
        </tr>
        <tr>
        	<td><table width="100%" border="0" cellspacing="2" cellpadding="0">
        	  <tr>
        	    <td width="50%" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
        	      <tr>
        	        <td class="gridhdbg" colspan="7">1.2.1 Prehension (30%)<font size="1"> Tick if not able to do</font></td>
      	        </tr>
        	      <tr>
        	        <td rowspan="2" align="center" class="gridhdbg" width="28%" >Activities<br />
        	          (30% for each component below)</td>
        	        <td rowspan="2" align="center" class="gridhdbg" width="13%">Movement</td>
        	        <td rowspan="2" align="center" class="gridhdbg" width="10%">Normal Value</td>
        	        <td colspan="2" align="center" class="gridhdbg">Old Loss in %</td>
        	        <td colspan="2" align="center" class="gridhdbg">New Loss in %</td>
      	        </tr>
        	      <tr>
        	        <td width="14%" align="center" class="gridhdbg">Right </td>
        	        <td width="12%" align="center" class="gridhdbg">Left </td>
        	        <td width="12%" align="center" class="gridhdbg">Right </td>
        	        <td width="11%" align="center" class="gridhdbg">Left </td>
      	        </tr>
        	      <tr>
        	        <td rowspan="5" align="left" class="gridbg1">A.&nbsp;Opposition (8%) </td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">1.&nbsp;&nbsp;Index</td>
        	        <td align  ="center" class="gridbg1">2%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opindex_right" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opindex_left" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">2.&nbsp;&nbsp;Middle</td>
        	        <td align  ="center" class="gridbg1">2%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opmiddle_right" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opmiddle_left" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">3.&nbsp;&nbsp;Ring</td>
        	        <td align  ="center" class="gridbg1">2%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opring_right" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_opring_left" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">4.&nbsp;&nbsp;Little</td>
        	        <td align  ="center" class="gridbg1">2%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_oplittle_right" value ="2" ></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_oplittle_left" value ="2"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td rowspan="2" align ="left" class="gridbg1">B.&nbsp;Lateral Pinch (5%) </td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">Key Holding</td>
        	        <td align="center" class="gridbg1">5%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_lakey_right" value ="5"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_lakey_left" value ="5"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td rowspan="3" align ="left" class="gridbg1">C.&nbsp;Cylindrical Grasp (6%) </td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">Large Object(4&quot;)</td>
        	        <td align ="center" class="gridbg1">3%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_cylarge_right" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_cylarge_left" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">Small Object(1&quot;)</td>
        	        <td align ="center" class="gridbg1">3%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_cysmall_right" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_cysmall_left" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td rowspan="3" align ="left" class="gridbg1">D.&nbsp;Spherical Grasp (6%) </td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">Large Object(4&quot;)</td>
        	        <td align ="center" class="gridbg1">3%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_splarge_right" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_splarge_left" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td class="gridbg1">Small Object(1&quot;)</td>
        	        <td align ="center" class="gridbg1">3%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_spsmall_right" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_spsmall_left" value="3"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
        	      <tr>
        	        <td rowspan="2" align ="left" class="gridbg1">E.&nbsp;Hook Grasp (5%) </td>
      	        </tr>
        	      <tr>
        	        <td align = "left" class="gridbg1">Lifting bag</td>
        	        <td align = "center" class="gridbg1">5%</td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_hook_right" value="5"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><html:checkbox property="hand_hook_left" value="5"></html:checkbox></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
        	        <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting3" value ="9"/></td>
      	        </tr>
      	      </table></td>
        	    <td width="50%" align="center" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
        	      <tr>
        	        <td width="50%"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
        	          <tr>
        	            <td rowspan="2" align="center" class="gridhdbg">1.2.2&nbsp;&nbsp;Sensation (30%)</td>
        	            <td colspan="2" align="center" class="gridhdbg">Old Loss in %</td>
        	            <td colspan="2" align="center" class="gridhdbg">New Loss in %</td>
      	            </tr>
        	          <tr>
        	            <td align="center" class="gridhdbg">Right </td>
        	            <td align="center" class="gridhdbg">Left </td>
        	            <td align="center" class="gridhdbg">Right </td>
        	            <td align="center" class="gridhdbg">Left </td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">1.&nbsp;&nbsp;Thumb ray 9%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_sethumb_right" value="9"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_sethumb_left" value="9"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">2.&nbsp;&nbsp;Index finger 6%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_seindex_right" value="6"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_seindex_left" value="6"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">3.&nbsp;&nbsp;Middle finger 5%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_semiddle_right" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_semiddle_left" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">4.&nbsp;&nbsp;Ring finger 5%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_sering_right" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_sering_left" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">5.&nbsp;&nbsp;Little finger 5%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_selittle_right" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_selittle_left" value="5"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting4" value ="9"/></td>
      	            </tr>
      	          </table></td>
        	        <td width="50%" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
        	          <tr>
        	            <td rowspan="2" align="center" class="gridhdbg">1.2.3&nbsp;&nbsp;Strength (30%)</td>
        	            <td colspan="2" align="center" class="gridhdbg">Old Loss in %</td>
        	            <td colspan="2" align="center" class="gridhdbg">New Loss in %</td>
      	            </tr>
        	          <tr class="gridhdbg">
        	            <td align="center" class="labelBlue">Right </td>
        	            <td align="center" class="labelBlue">Left </td>
        	            <td align="center" class="labelBlue">Right </td>
        	            <td align="center" class="labelBlue">Left </td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">1.&nbsp;&nbsp;Grip strength 20%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_stgrip_right" value="20"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_stgrip_left" value="20"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting5" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting5" value ="9"/></td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">2.&nbsp;&nbsp;Pinch strength 10%</td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_stpinch_right" value="10"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><html:checkbox property="hand_stpinch_left" value="10"></html:checkbox></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting5" value ="9"/></td>
        	            <td align ="center" class="gridbg1"><input type="checkbox"  name="coordinate_lifting5" value ="9"/></td>
      	            </tr>
      	          </table></td>
      	          </tr>
        	      <tr>
        	        <td colspan="2"><table  align="center" cellspacing="0" cellpadding="0" class="grigtd" width="100%" border="0">
        	          <tr>
        	            <td class="gridhdbg" width="43%">1.3 &nbsp;Complications</td>
        	            <td class="gridhdbg">Old Percentage Of Complications</td>
        	            <td class="gridhdbg">New Percentage Of Complications</td>
      	            </tr>
        	          <tr>
        	            <td class="gridbg1">1. Infections </td>
        	            <td align="left" class="gridbg1"><html:select property="com_inflection">
        	              <html:option  value="0">0</html:option>
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
        	            <td class="gridbg1">2. Deformity </td>
        	            <td align="left" class="gridbg1"><html:select property="com_Deformity">
        	              <html:option  value="0">0</html:option>
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
        	            <td align="left" class="gridbg1"><select  name="com_inflection2">
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
        	            <td class="gridbg1">3. Misalignment </td>
        	            <td align="left" class="gridbg1"><html:select property="com_Misalignment">
        	              <html:option  value="0">0</html:option>
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
        	            <td align="left" class="gridbg1"><select  name="com_inflection2">
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
        	          <tr >
        	            <td class="gridbg1">4. Contracture </td>
        	            <td align="left" class="gridbg1"><html:select property="com_Contracture">
        	              <html:option  value="0">0</html:option>
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
        	            <td align="left" class="gridbg1"><select  name="com_inflection2">
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
        	            <td class="gridbg1"> 5. Loss of Cosmetic appearance </td>
        	            <td align="left" class="gridbg1"><html:select property="com_LossofCosmeticappearance">
        	              <html:option  value="0">0</html:option>
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
        	            <td align="left" class="gridbg1"><select  name="com_inflection2">
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
        	            <td class="gridbg1"> 6. Whether dominant upper extremity involved </td>
        	            <td align ="left" class="gridbg1">&nbsp;&nbsp;
        	              <html:checkbox property="com_domionantupperextremity" value="4"/></td>
        	            <td align ="left" class="gridbg1">&nbsp;&nbsp;
        	              <input type="checkbox" nmae="com_domionantupperextremity1" value="4"/></td>
      	            </tr>
      	          </table></td>
      	        </tr>
        	      <tr>
        	        <td colspan="2"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
        	          <tr>
        	            <td class="gridhdbg" colspan="3">1.2.5 &nbsp;Shortening:</td>
      	            </tr>
        	          <tr class="gridbg1">
        	            <td width="42%"class="label">a. Mention in inches</td>
        	            <td class="label"><html:text property="inches" size="5" maxlength="4" onblur="this.value=removeSpaces(this.value);"/>
        	              <font size="1">(mention in numbers)</font></td>
        	            <td class="label"><input type="text" name="inches" size="5" maxlength="4" onblur="this.value=removeSpaces(this.value);"/>
        	              <font size="1">(mention in numbers)</font></td>
      	            </tr>
      	          </table></td>
      	        </tr>
      	        </table></td>
      	    </tr>
      	  </table></td>
        </tr>
   </table>

      </html:form>
<form action="">
     <TABLE align="center">
                    <tr>
                        <td align="center"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
                     <td align="center"><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/>

                        <html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
                    </tr>
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