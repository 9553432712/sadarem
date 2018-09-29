<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<html:html locale="true">

    <script language="javascript" >


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
            document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }

        function removeSpaces(string)
        {
            return string.split(' ').join('');
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

    <BODY>
        <html:errors/>
        <script language="JavaScript" src="./DisabilityUITG/js/UpperExtremity.js"></script>


        <html:form  action="/upperextrimity.do?addUpperExterimityData=addUpperExterimityData" styleId="UpperExtrimityActionForm"
                    onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');document.getElementById('upExButton').disabled = true;">

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="6">1.&nbsp;&nbsp;ADD UPPER EXTREMITY</th>
                </tr>
                <tr>
                    <th colspan="6">1.1 &nbsp;&nbsp;Arm Component (Total Value=90%)</th>
                </tr>
                <tr>
                    <td colspan="6">1.1.1 &nbsp;&nbsp;Active Range of Motion (ROM) ARC  (in degrees)</td>
                </tr>

                <tr>
                    <th rowspan=2>Joint </th>
                    <th rowspan=2>Component</th>
                    <th rowspan=2>Normal Range (Degree)</th>
                    <th colspan=2>Active ROM</th>
                </tr>
                <tr>
                    <th>Right</th>
                    <th>Left</th>
                </tr>
                <tr>
                    <td>1.&nbsp;Shoulder Joint</td>
                    <td >1. &nbsp;Flexion-Extension <br><BR>2. &nbsp;Abduction-Adduction <br><BR>3.&nbsp;Rotation </td>
                    <td align="center" >0-220<br><BR>0-180<br><BR>0-180</td>
                    <td >
                        <html:text  size="6" property="rom_sf_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)" maxlength="3"/><br>
                        <html:text  size="6" property="rom_sa_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/><br>
                        <html:text  size="6" property="rom_sr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td >
                        <html:text  size="6" property="rom_sf_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber220(this)" maxlength="3"/><br>
                        <html:text size="6" property="rom_sa_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/><br>
                        <html:text  size="6" property="rom_sr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/></td>
                </tr>
                <tr>
                    <td>2.&nbsp;Elbow Joint</td>
                    <td >1.&nbsp; Flexion-Extension  <br><BR>2. &nbsp;Supination-Pronation </td>
                    <td align="center" >0-150<br><BR>0-180</td>
                    <td><html:text  size="6" property="rom_ef_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber150(this)" maxlength="3"/><br>
                        <html:text  size="6"  property="rom_es_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td><html:text  size="6" property="rom_ef_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber150(this)" maxlength="3"/><br>
                        <html:text  size="6"property="rom_es_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber180(this)" maxlength="3"/></td>
                </tr>
                <tr>
                    <td>3.&nbsp;Wrist Joint</td>
                    <td >1.&nbsp; Dorsiflexion-Palmar flexion  <br><BR>2.&nbsp; Radial- Ulnar deviation </td>
                    <td align="center" >0-160<br><BR>0-55</td>
                    <td><html:text  size="6" property="rom_wd_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber160(this)" maxlength="3"/><br>
                        <html:text   size="6" property="rom_wr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber55(this)" maxlength="2"/></td>
                    <td><html:text  size="6" property="rom_wd_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber160(this)" maxlength="3"/><br>
                        <html:text   size="6" property="rom_wr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber55(this)" maxlength="2"/></td>
                </tr>



                <tr>
                    <td colspan="6"><font size="2">1.1.2 &nbsp;&nbsp;Muscle Strength:</font> <font size="1">&nbsp;&nbsp;
                            (Normal Value = Grade 5)(Please write the muscle grade)</font></td>
                </tr>

                <tr>
                    <th rowspan=2 width="25%">Joint </th>
                    <th rowspan=2 width="32%">Component</th>
                    <th rowspan=2 width="15%">Normal Muscle Grade</th>
                    <th colspan=2>Active Muscle Grade </th>
                </tr>
                <tr>
                    <th>Right </th>
                    <th>Left </th>
                </tr>
                <tr>
                    <td>1.&nbsp;Shoulder Joint<br><BR>Muscle Strength <br>Value 90%</td>
                    <td >1. &nbsp;Flexion <br><BR>2. &nbsp;Extension<br><BR>3.&nbsp;Abduction<br><BR>4.&nbsp;Adduction<br><BR>5.&nbsp;Ext-Rotation<br><BR>6.&nbsp;Int-Rotation</td>
                    <td align="center" >0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
                    <td><html:text  size="6" property="ms_sf_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_se_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_sab_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sad_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sext_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sint_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br></td>
                    <td><html:text  size="6" property="ms_sf_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_se_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_sab_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sad_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sext_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_sint_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br></td>
                </tr>

                <tr>
                    <td>2.&nbsp;Elbow Joint<br><BR>Muscle Strength <br>Value 90%</td>
                    <td >1.&nbsp; Flexion <br><BR>2. &nbsp;Extension<br><BR>3.&nbsp;Pronation<br><BR>4.&nbsp;Supination </td>
                    <td align="center" >0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
                    <td><html:text  size="6" property="ms_ef_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_ee_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_ep_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6"property="ms_es_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/></td>
                    <td><html:text  size="6" property="ms_ef_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_ee_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_ep_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text  size="6" property="ms_es_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/></td>
                </tr>
                <tr>
                    <td>3.&nbsp;Wrist Joint<br><BR>Muscle Strength <br>Value 90%</td>
                    <td >1.&nbsp; Dorsiflexion <br><BR>2.&nbsp; Palmar Flexion<br><BR>3.&nbsp;Radial Deviation<br><BR>4.&nbsp; Ulnar Deviation</td>
                    <td align="center" >0-5<br><BR>0-5<br><BR>0-5<br><BR>0-5</td>
                    <td><html:text  size="6" property="ms_wd_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_wp_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6"property="ms_wr_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_wu_right" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/></td>
                    <td><html:text  size="6" property="ms_wd_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_wp_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_wr_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/><br>
                        <html:text   size="6" property="ms_wu_left" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber5(this)" maxlength="1"/></td>
                </tr>


                <tr>
                    <td colspan="6">1.1.3 &nbsp;&nbsp;Coordinated Activities <font size="1">(Tick if not able to do)</font></td>
                </tr>
                <tr>
                    <th width="15%">Coordinated Activities</th>
                    <th width="50%">Components&nbsp; (each 90%)</th>
                    <th  width="25%" colspan="4">Loss of Coordinated Activities</th>

                </tr>

                <tr>
                    <td rowspan="11" width="10%"> value 90% </td>
                </tr>
                <tr>
                    <td>
                        A.&nbsp;Lifting overhead objects,remove and place at the same place(9%)</td><td colspan="3">
                        <html:checkbox  property="coordinate_lifting" value ="9" ></html:checkbox>
                    </td>
                </tr>
                <tr>
                    <td >B.&nbsp;Touching nose with end of the extremity(9%)
                    <td align ="center" colspan="3">
                        <html:checkbox property="coordinate_touching" value ="9" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >
                        C.&nbsp;Eating Indian style(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_eating" value ="9" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >
                        D.&nbsp;Combing and Plaiting (9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_combing" value ="9" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >
                        E.&nbsp;Putting on a shirt/kurtha(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_putting" value ="9" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >
                        F.&nbsp;Ablution glass of water(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_ablution" value ="9" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >
                        G.&nbsp;Buttoning(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_buttoning" value ="9"></html:checkbox></td>

                </tr>
                <tr>
                    <td >H.&nbsp;Tie Nara Dhoti(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_tie" value ="9"></html:checkbox></td>
                </tr>
                <tr>
                    <td >I.&nbsp;Writing(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_writing" value ="9"></html:checkbox></td>

                </tr>
                <tr>
                    <td >J.&nbsp;Drinking Glass of Water(9%)<td align ="center" colspan="3"><html:checkbox property="coordinate_drinking" value ="9"></html:checkbox></td>

                </tr>


                <tr>
                    <th colspan="10">1.2 &nbsp;&nbsp;Upper Extremity: Hand Component(Assess the affected component only) <font size="1"> (Total Value=90%)</font></th>
                </tr>

                <tr>
                    <td colspan="5">1.2.1 Prehension (30%)<font size="1">Tick if not able to do</font></td>

                </tr>


                <tr>
                    <th rowspan=2 width="25%">Activities <br>(30% for each component below)</th>
                    <th rowspan=2 width="32%">Movement</th>
                    <th rowspan=2 width="15%">Normal Value</th>
                    <th colspan=2>Loss in %</th>
                </tr>
                <tr>
                    <th>Right </th>
                    <th>Left </th>
                </tr>

                <%-- <tr>
                     <th rowspan="2">Activities<br>(30% for each component below)</th>
                     <th rowspan="2">Movement</th>
                     <th rowspan="2">Normal Value</th>
                     <th rowspan="2" colspan="2">Loss in %</th>

                </tr>
                <tr>
                    <th>Right </th>
                    <th>Left </th>
                </tr>
                --%>


                <tr>
                    <td rowspan=5>A.&nbsp;Opposition (8%) </td>
                </tr>
                <tr>
                    <td >1.&nbsp;&nbsp;Index</td>
                    <td align  ="center" >2%</td>
                    <td align ="center" ><html:checkbox property="hand_opindex_right" value ="2" ></html:checkbox></td>
                    <td align ="center" ><html:checkbox property="hand_opindex_left" value ="2" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >2.&nbsp;&nbsp;Middle</td>
                    <td align  ="center" >2%</td>
                    <td align ="center"><html:checkbox property="hand_opmiddle_right" value ="2" ></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_opmiddle_left" value ="2" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >3.&nbsp;&nbsp;Ring</td>
                    <td align  ="center" >2%</td>
                    <td align ="center"><html:checkbox property="hand_opring_right" value ="2" ></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_opring_left" value ="2" ></html:checkbox></td>
                </tr>
                <tr>
                    <td >4.&nbsp;&nbsp;Little</td>
                    <td align  ="center" >2%</td>
                    <td align ="center"><html:checkbox property="hand_oplittle_right" value ="2" ></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_oplittle_left" value ="2"></html:checkbox></td>
                </tr>
                <tr>
                    <td rowspan=2>B.&nbsp;Lateral Pinch (5%) </td>
                </tr>
                <tr>
                    <td >Key Holding</td>
                    <td align="center" >5%</td>
                    <td align ="center"><html:checkbox property="hand_lakey_right" value ="5"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_lakey_left" value ="5"></html:checkbox></td>
                </tr>

                <tr>
                    <td rowspan=3>C.&nbsp;Cylindrical Grasp (6%) </td>
                </tr>
                <tr>
                    <td >Large Object(4")</td>
                    <td align ="center" >3%</td>
                    <td align ="center"><html:checkbox property="hand_cylarge_right" value="3"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_cylarge_left" value="3"></html:checkbox></td>
                </tr>

                <tr>
                    <td >Small Object(1")</td>
                    <td align ="center" >3%</td>
                    <td align ="center"><html:checkbox property="hand_cysmall_right" value="3"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_cysmall_left" value="3"></html:checkbox></td>
                </tr>



                <tr>
                    <td rowspan=3>D.&nbsp;Spherical Grasp (6%) </td>
                </tr>
                <tr>
                    <td >Large Object(4")</td>
                    <td align ="center" >3%</td>
                    <td align ="center"><html:checkbox property="hand_splarge_right" value="3"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_splarge_left" value="3"></html:checkbox></td>
                </tr>
                <tr>
                    <td >Small Object(1")</td>
                    <td align ="center" >3%</td>
                    <td align ="center"><html:checkbox property="hand_spsmall_right" value="3"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_spsmall_left" value="3"></html:checkbox></td>
                </tr>


                <tr>
                    <td rowspan=2>E.&nbsp;Hook Grasp (5%) </td>
                </tr>
                <tr>
                    <td >Lifting bag</td>
                    <td align = "center" >5%</td>
                    <td align ="center"><html:checkbox property="hand_hook_right" value="5"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_hook_left" value="5"></html:checkbox></td>
                </tr>

                <tr>
                    <td rowspan=6>1.2.2&nbsp;&nbsp;Sensation(30%)</td>
                </tr>
                <tr>
                    <td >1.&nbsp;&nbsp;Thumb ray</td>
                    <td align  ="center" >9%</td>
                    <td align ="center"><html:checkbox property="hand_sethumb_right" value="9"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_sethumb_left" value="9"></html:checkbox></td>
                </tr>
                <tr>
                    <td >2.&nbsp;&nbsp;Index finger </td>
                    <td align  ="center" >6%</td>
                    <td align ="center"><html:checkbox property="hand_seindex_right" value="6"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_seindex_left" value="6"></html:checkbox></td>
                </tr>
                <tr>
                    <td >3.&nbsp;&nbsp;Middle finger</td>
                    <td align  ="center" >5%</td>
                    <td align ="center"><html:checkbox property="hand_semiddle_right" value="5"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_semiddle_left" value="5"></html:checkbox></td>
                </tr>
                <tr>
                    <td >4.&nbsp;&nbsp;Ring finger</td>
                    <td align  ="center" >5%</td>
                    <td align ="center"><html:checkbox property="hand_sering_right" value="5"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_sering_left" value="5"></html:checkbox></td>
                </tr>
                <tr>
                    <td >5.&nbsp;&nbsp;Little finger</td>
                    <td align  ="center" >5%</td>
                    <td align ="center"><html:checkbox property="hand_selittle_right" value="5"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_selittle_left" value="5"></html:checkbox></td>
                </tr >

                <tr>
                    <td rowspan=3 align="left">1.2.3&nbsp;&nbsp;Strength(30%)</td>
                </tr>
                <tr>
                    <td >1.&nbsp;&nbsp;Grip strength</td>
                    <td align  ="center" >20%</td>
                    <td align ="center"><html:checkbox property="hand_stgrip_right" value="20"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_stgrip_left" value="20"></html:checkbox></td>
                </tr>
                <tr>
                    <td >2.&nbsp;&nbsp;Pinch strength </td>
                    <td align  ="center" >10%</td>
                    <td align ="center"><html:checkbox property="hand_stpinch_right" value="10"></html:checkbox></td>
                    <td align ="center"><html:checkbox property="hand_stpinch_left" value="10"></html:checkbox></td>
                </tr>
            </table>
                <table align="center" class="inputform" cellpadding="0" cellspacing="1" border="0" width="90%">

                <tr >
                    <th colspan="4">1.3 &nbsp;Complications
                  <font size="1" >Percentage Of Complications</font></th>

                </tr>


                <tr >
                    <td >1.&nbsp; Infections
                    </td>


                    <td align="left" ><html:select property="com_inflection" style="width:50px">
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

                        </html:select> </td>
              
                    <td >2.&nbsp; Deformity
                    </td>
                    <td align="left" ><html:select property="com_Deformity" style="width:50px">
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

                        </html:select> </td>

                </tr>
                <tr>
                    <td >3.&nbsp; Mislalignment
                    </td>
                    <td align="left" ><html:select property="com_Misalignment" style="width:50px">
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

                        </html:select> </td>

               
                    <td >4.&nbsp; Contracture
                    </td>
                    <td align="left" ><html:select property="com_Contracture" style="width:50px">
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

                        </html:select> </td>

                </tr>



                <tr>
                    <td >5.&nbsp;Loss of Cosmetic appearance

                    </td>
                    <td align="left" ><html:select property="com_LossofCosmeticappearance" style="width:50px">
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

                        </html:select> </td>

                    <td >6.&nbsp;  Whether dominant upper extremity involved
                    </td>

                    <td align="left">&nbsp;&nbsp;<html:checkbox property="com_domionantupperextremity" value="4" style="width:25px"/></td>


                </tr>
                <tr>
                    <th colspan="4">1.4 &nbsp;Shortening</th>
                </tr>
                <tr>
                    <td>a. Mention in inches </td>
                    <td  colspan="5" ><html:text property="inches" size="5" maxlength="4" onblur="this.value=removeSpaces(this.value);"  onkeyup="isNumber5(this)"/><font size="1">(mention in numbers)</font></td>
                </tr>
            </table>
            <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%" class="inputform">
                <tr>
                    <th><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                        <html:submit value="Add" onfocus="validateinches()" styleId="upExButton" styleClass="button"/>
                        <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>
                </tr>
            </table>

        </html:form>
    </BODY>
</html:html>
