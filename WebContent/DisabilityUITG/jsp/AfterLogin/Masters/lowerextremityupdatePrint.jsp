
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



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


        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted')){
                return false;
            }else{
                thisform.setAttribute('submitted','true');
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


    




    <body onLoad="disableForm(lowerextremityform);">
        <script language="JavaScript" src="./DisabilityUITG/js/LowerExtremity.js"></script>
        <html:errors/>

        <html:form action="/lowerextremityselectPrint.do" method="post"
                   styleId="lowerextremityform" onsubmit="return avoidDuplicateForm(this)">
            <table align="center" cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
                </tr>
            </table><br/>
            <table align="center" cellspacing="1" cellpadding="1" class="innerTable" width="100%">
                <tr>
                    <th align="center" class="gridhdbg" style="border:1px #000 solid; height:30px; font-size:16px;">2. LOWER EXTREMITY</th>
                </tr>
                <tr>
                    <th align="center">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="65%">
                                    <table width="100%" border="0" cellspacing="2" cellpadding="0">
                                        <tr>
                                            <td width="50%" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb1" width="100%" border="0">
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left; border-bottom:1px #000 solid;">2.1.1 Active Range of Motion (ROM) ARC.&nbsp;&nbsp;(in Degrees)</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="34%" rowspan="2" class="gridhdbg">Components</td>
                                                        <td width="14%" rowspan="2" class="gridhdbg">Normal Value (Degree)</td>
                                                        <td colspan="2" class="gridhdbg">Old Active ROM</td>
                                                        <td colspan="2" class="gridhdbg">New Active ROM</td>
                                                    </tr>
                                                    <tr class="gridhdbg">
                                                        <td width="13%" class="labelBlue">Right</td>
                                                        <td width="13%" class="labelBlue">Left</td>
                                                        <td width="13%" class="labelBlue">Right</td>
                                                        <td width="13%" class="labelBlue">Left</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left; border-top:1px #000 solid;">1.Hip Joint</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Flexion-Extension arc</td>
                                                        <td align="center" class="gridbg1">0-140</td>
                                                        <td class="gridbg1"><html:text  size="6" property="romhipflexionextensionright" maxlength="3" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><html:text size="6" property="romhipflexionextensionleft" maxlength="3" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright1" maxlength="3" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright2" maxlength="3" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">B.&nbsp;Abduction-Adduction arc</td>
                                                        <td align="center" class="gridbg1">0-90</td>
                                                        <td class="gridbg1"><html:text  size="6" property="romhipabductionadductionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><html:text  size="6" property="romhipabductionadductionleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright8" maxlength="2" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright6" maxlength="2" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">C.&nbsp;Rotation arc</td>
                                                        <td align="center" class="gridbg1">0-90</td>
                                                        <td class="gridbg1"><html:text size="6" property="romhiprotationright" maxlength="2" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><html:text size="6" property="romhiprotationleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" /></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright7" maxlength="2" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright5" maxlength="2" onBlur="this.value=removeSpaces(this.value);"  size="6"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left;">2.Knee Joint</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Flexion-Extension arc </td>
                                                        <td align="center" class="gridbg1">0-125</td>
                                                        <td class="gridbg1"><html:text size="6" property="romkneeflexionextensionright" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber125(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="romkneeflexionextensionleft" maxlength="3" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber125(this)"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright2" maxlength="3"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright2" maxlength="3"  size="6"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left;"><span class="labelBlue">3.Ankle &amp; Foot joint</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Dorsiflexion-palntar flexion arc </td>
                                                        <td align="center" class="gridbg1">0-70</td>
                                                        <td class="gridbg1"><html:text size="6" property="romankledorsiflexionpalmarflexionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber70(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="romankledorsiflexionpalmarflexionleft" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber70(this)"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright2" maxlength="2"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright2" maxlength="2"  size="6"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">B.&nbsp;Inversion- Eversion arc</td>
                                                        <td align="center" class="gridbg1">0-60</td>
                                                        <td class="gridbg1"><html:text  size="6" property="romankleinversioneversionright" maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber60(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="romankleinversioneversionleft"  maxlength="2" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber60(this)"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright3" maxlength="2"  size="6"/></td>
                                                        <td class="gridbg1"><input type="text" name="romhipflexionextensionright4" maxlength="2"  size="6"/></td>
                                                    </tr>
                                                </table></td>
                                            <td width="50%"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb1" width="100%" border="0">
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left; border-bottom:1px #000 solid;">2.1.2 &nbsp;&nbsp;Muscle Strength.(Normal value = Grade 5 for all)</td>
                                                    </tr>
                                                    <tr>
                                                        <td rowspan="2" class="gridhdbg">Components</td>
                                                        <td rowspan="2" class="gridhdbg">Normal Muscle Grade</td>
                                                        <td colspan="2" class="gridhdbg">Old Active Muscle Grade</td>
                                                        <td colspan="2" class="gridhdbg">New Active Muscle Grade</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridhdbg">Right</td>
                                                        <td class="gridhdbg">Left</td>
                                                        <td class="gridhdbg">Right</td>
                                                        <td class="gridhdbg">Left</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left; border-top:1px #000 solid;">1. Hip Joint</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Flexor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5 <br /></td>
                                                        <td class="gridbg1"><html:text size="6" property="mshipflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="mshipflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright1" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright2" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">B.&nbsp;Extensor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text size="6" property="mshipextensormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshipextensormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright6" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright10" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">C.&nbsp;Rotator Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshiprotatormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshiprotatormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright5" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright9" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">D.&nbsp;Abductor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshipabductormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshipabductormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright4" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright8" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">E.&nbsp;Adductor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshipadductormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="mshipadductormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright3" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright7" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left;">2. Knee Joint</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Flexor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text size="6" property="mskneeflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="mskneeflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright2" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright2" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">B.&nbsp;Extensor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="mskneeextensormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="mskneeextensormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright12" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright11" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" class="gridhdbg" style="text-align:left;">3. Ankle & Foot joint</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">A.&nbsp;Planterflexor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text size="6" property="msankleplanterflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="msankleplanterflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright2" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright2" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">B.&nbsp;Dorsiflexor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text size="6" property="msankledorsiflexormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text size="6" property="msankledorsiflexormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright18" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright15" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">C.&nbsp;Invertor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="msankleinvertormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="msankleinvertormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright17" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright14" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">D.&nbsp;Evertor Muscles</td>
                                                        <td align="center" class="gridbg1">0-5</td>
                                                        <td class="gridbg1"><html:text  size="6" property="msankleevertormusclesright" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><html:text  size="6" property="msankleevertormusclesleft" maxlength="1" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright16" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                        <td class="gridbg1"><input type="text" size="6" name="mshipflexormusclesright13" maxlength="1" onBlur="this.value=removeSpaces(this.value);" onKeyUp="isNumber5(this)"/></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="gridhdbg" style="border:1px #000 solid; height:30px; font-size:16px;">2.2 Lower Extremity :&nbsp;&nbsp;Stability Component (Total value=90%)</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb1" width="100%" border="0">
                                                    <tr>
                                                        <td rowspan="2" class="gridhdbg" width="53%">&nbsp;Components</td>
                                                        <td colspan="6" align="center" class="gridhdbg" style="border-bottom:1px #000 solid;">Please Tick in the appropriate box</td>
                                                    </tr>
                                                    <tr>
                                                        <td align ="center" class="gridhdbg" width="21%">
                                                            Old Perform<br />
                                                            without any<br />
                                                            difficulty</br></td>
                                                        <td align ="center" class="gridhdbg">
                                                            Old Perform<br />
                                                            with<br />
                                                            difficulty</br></td>
                                                        <td align ="center" class="gridhdbg">
                                                            Old Cannot <br />
                                                            perform</br></td>
                                                        <td align ="center" class="gridhdbg" width="21%">
                                                            New Perform<br />
                                                            without any<br />
                                                            difficulty</br></td>
                                                        <td align ="center" class="gridhdbg">
                                                            New Perform<br />
                                                            with<br />
                                                            difficulty</br></td>
                                                        <td align ="center" class="gridhdbg">
                                                            New Cannot <br />
                                                            perform</br></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.1&nbsp;Walking on plane surface </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_plane" value ="0" onclick="checkradioworking_on_plane1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_plane" value ="5" onclick="checkradioworking_on_plane2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_plane" value ="10" onclick="checkradioworking_on_plane3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.2&nbsp;Walking on slope</td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_slope" value ="0" onclick="checkradioworking_on_slope1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_slope" value ="5" onclick="checkradioworking_on_slope2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_slope" value ="10" onclick="checkradioworking_on_slope3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.3&nbsp;Climbing stairs </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_stairs" value ="0" onclick="checkradioworking_on_stairs1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_stairs" value ="5" onclick="checkradioworking_on_stairs2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="working_on_stairs" value ="10" onclick="checkradioworking_on_stairs3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.4&nbsp;Standing on both legs </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_both_legs" value ="0" onclick="checkradiostanding_on_both_legs1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_both_legs" value ="5"  onclick="checkradiostanding_on_both_legs2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_both_legs" value ="10"  onclick="checkradiostanding_on_both_legs3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.5&nbsp;Standing on affected leg </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_effected" value ="0" onclick="checkradiostanding_on_effected1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_effected" value ="5" onclick="checkradiostanding_on_effected2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="standing_on_effected" value="10" onclick="checkradiostanding_on_effected3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.6&nbsp;Squatting on floor</td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="squatting_on_floor" value ="0" onclick="checkradiosquatting_on_floor1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="squatting_on_floor" value ="5"  onclick="checkradiosquatting_on_floor2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="squatting_on_floor" value ="10"  onclick="checkradiosquatting_on_floor3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.7&nbsp;Sitting cross leg </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="sitting_cross_leg" value ="0"  onclick="checkradiositting_cross_leg1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="sitting_cross_leg" value ="5" onclick="checkradiositting_cross_leg2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="sitting_cross_leg" value ="10" onclick="checkradiositting_cross_leg3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.8&nbsp;Kneeling</td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="kneeling" value ="0" onclick="checkradiokneeling1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="kneeling" value ="5" onclick="checkradiokneeling2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="kneeling" value ="10" onclick="checkradiokneeling3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1">2.2.9&nbsp;Taking turns </td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="taking_turns" value ="0" onclick="checkradiotaking_turns1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="taking_turns" value ="5" onclick="checkradiotaking_turns2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><html:radio  property="taking_turns" value ="10" onclick="checkradiotaking_turns3()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="25%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td><table  align="center" cellspacing="0" cellpadding="0" class="gridtb1" width="100%" border="0">
                                                    <tr>
                                                        <th colspan="4" align="left" class="gridhdbg">1.Deformity</th>
                                                    </tr>
                                                    <tr class="gridbg1">
                                                        <th rowspan="2" align="left" class="gridbg1">
                                                            a.&nbsp;&nbsp;In function position</th>
                                                    </tr>
                                                    <tr>
                                                        <td align = "center" class="gridbg1" ><html:radio  property="deformity" value ="3" onclick="checkradiodeformity1()" ></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th align="left" class="gridbg1">
                                                            b.&nbsp;&nbsp;In non-function position </th>
                                                        <td align = "center" class="gridbg1"><html:radio  property="deformity" value ="6" onclick="checkradiodeformity2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th colspan="4" align="left" class="gridhdbg">2.Pain</th>
                                                    </tr>
                                                    <tr class="gridbg1">
                                                        <th rowspan="2" align="left" class="gridbg1">
                                                            a.&nbsp;&nbsp;Severe (grossly interfering with function) </th>
                                                    </tr>
                                                    <tr>
                                                        <td align = "center" class="gridbg1"><html:radio  property="pain" value ="9" onclick="checkradiopain1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th align="left" class="gridbg1">
                                                            b.&nbsp;&nbsp;Moderate (moderately interfering with function)</th>
                                                        <td align = "center" class="gridbg1"><html:radio  property="pain" value ="6" onclick="checkradiopain2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th align="left" class="gridbg1">
                                                            c.&nbsp;&nbsp;Mild (mildly interfering with function)</th>
                                                        <td align = "center" class="gridbg1"><html:radio  property="pain" value ="3"onclick="checkradiopain3()" ></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th colspan="4" align="left" class="gridhdbg">3.Loss of Function</th>
                                                    </tr>
                                                    <tr class="gridbg1">
                                                        <th rowspan="2" align="left" class="gridbg1">
                                                            a.&nbsp;&nbsp;Complete loss </th>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="gridbg1"><html:radio  property="loss_of_Function" value="9" onclick="checkradioloss_of_Function1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th align="left" class="gridbg1">
                                                            b.&nbsp;&nbsp;Partial loss </th>
                                                        <td align = "center" class="gridbg1"><html:radio  property="loss_of_Function" value="6" onclick="checkradioloss_of_Function2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th colspan="4" align="left" class="gridhdbg">4.Complications</th>
                                                    </tr>
                                                    <tr class="gridbg1">
                                                        <th rowspan="2" align="left" class="gridbg1">
                                                            a.&nbsp;&nbsp;Superficial complications </th>
                                                    </tr>
                                                    <tr>
                                                        <td align = "center" class="gridbg1"><html:radio  property="complications" value ="3" onclick="checkradiocomplications1()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                    <tr>
                                                        <th align="left" class="gridbg1">
                                                            b.&nbsp;&nbsp;Deep complications </th>
                                                        <td align = "center" class="gridbg1"><html:radio  property="complications" value ="5" onclick="checkradiocomplications2()"></html:radio></td>
                                                        <td align = "center" class="gridbg1"><input type="radio"  name="working_on_plane1" value ="10" onClick="checkradioworking_on_plane3()"/></td>

                                                    </tr>
                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td height="10"></td>
                                        </tr>
                                        <tr>
                                            <td><table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb1" width="100%">
                                                    <tr>
                                                        <td class="gridhdbg">2.1.4&nbsp;&nbsp;Shortening of Limb</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="50%" class="gridbg1">a.&nbsp;&nbsp;Mention in inches&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <html:text property="shortening"  maxlength="4" onblur="this.value=removeSpaces(this.value);"></html:text>
                                                            <font size="1">(mention in numbers)</font></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="gridbg1"><input type="text" name="shortening1"  maxlength="4" onBlur="this.value=removeSpaces(this.value);"/>
                                                            <font size="1">(mention in numbers)</font></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </table></td>
                            </tr>
                        </table>
                    </th>
                </tr>
            </table>
            <br>

        </html:form>

        <form action="">
            <TABLE align="center">
                <tr class="tbl_bg2_content">
                    <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>
                    <td><html:button  property="" value="Print" onclick="window.print()" styleClass="button"/></td>
                </tr>
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

