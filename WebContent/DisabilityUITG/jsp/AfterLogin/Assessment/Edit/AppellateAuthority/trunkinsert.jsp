<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    <script language="javascript" >
        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }


        function validateshortning(){
            var flag = true;
            var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
            var cardio_associateleg=document.trunkform.cardio_associateleg.value;
            for (var i = 0; i < cardio_associateleg.length; i++) {
                if (iChars.indexOf(cardio_associateleg.charAt(i)) != -1) {
                    alert ("Please Enter Valid shortening.");
                    document.trunkform.cardio_associateleg.value="";
                    document.trunkform.cardio_associateleg.focus();
                    flag = false;
                    return flag;
                }
            }
            if(isNaN(cardio_associateleg))
            {
                alert("Enter Numbers only in your shortening")
                document.trunkform.cardio_associateleg.value="";
                document.trunkform.cardio_associateleg.focus();
                flag = false;
                return flag;
            }
            if(parseInt(cardio_associateleg)<0 ||
                parseInt(cardio_associateleg)>99)
            {
                alert ("Please enter yours valid shortening!");
                document.trunkform.cardio_associateleg.value="";
                document.trunkform.cardio_associateleg.focus();
                flag = false;
                return flag;
            }
            return flag;
        }

        function validateForm(thisForm){
            var flag = true;
            flag = validateshortning();

            if(flag){
                if (thisForm.getAttribute('submitted')){
                    flag = false;
                    return flag;
                }else{

                    thisForm.setAttribute('submitted','true');
                    document.getElementById('trunButton').disabled = true;
                }

            }
            return flag;
        }



    </script>

    <SCRIPT LANGUAGE="JavaScript">


        function changeScoliosis() {
            var chgPwdFld = document.getElementById("scoliosis");
            if(chgPwdFld.checked == true)
            {
                document.getElementById("changePwd").style.visibility = "Visible";
                document.getElementById("changePwd").style.display = "Inline";

                document.getElementById("extratrunk").style.visibility = "Visible";
                document.getElementById("extratrunk").style.display = "Inline";
            } else {
                document.getElementById("changePwd").style.visibility = "Hidden";
                document.getElementById("changePwd").style.display = "None";
                var kyphosis = document.getElementById("kyphosis");
                if(kyphosis.checked == false){
                    document.getElementById("extratrunk").style.visibility = "Hidden";
                    document.getElementById("extratrunk").style.display = "None";
                }
            }
        }

        function changekyphosis() {
            var chgPwdFld = document.getElementById("kyphosis");
            if(chgPwdFld.checked == true)
            {
                document.getElementById("change").style.visibility = "Visible";
                document.getElementById("change").style.display = "Inline";

                document.getElementById("extratrunk").style.visibility = "Visible";
                document.getElementById("extratrunk").style.display = "Inline";
            } else {
                document.getElementById("change").style.visibility = "Hidden";
                document.getElementById("change").style.display = "None";
                var scoliosis = document.getElementById("scoliosis");
                if(scoliosis.checked == false){
                    document.getElementById("extratrunk").style.visibility = "Hidden";
                    document.getElementById("extratrunk").style.display = "None";
                }
            }
        }

        function removeSpaces(string)
        {
            return string.split(' ').join('');
        }

    </SCRIPT>
    <body >
        <script language="JavaScript" src="./DisabilityUITG/js/Trunk.js"></script>


        <html:form action="/TrunkInsert.do?insertTrunkDetails=insertTrunkDetails" method="post" styleId="trunkform" onsubmit="return  validateForm(this)">
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <th colspan="3"><center>5. Add Trunk</center></th>
            </tr>

            <tr>
                <th colspan="3">5.1.Traumatic Lesions</th>
            </tr>
            <tr>
                <td colspan="3">5.1.1&nbsp;&nbsp;Cervical Spine injuries (Fracture)</td>
            </tr>
            <tr>
                <td colspan="2" width = "42%">I.&nbsp;Compression of one or two adjacent vertebral bodies with no involvement of
                    posterior elements. No nerve root involvement, moderate neck rigidity and persistent soreness</td>
                <td style="text-align: center">
                    <center><html:checkbox value="20" property="compression" style="width:25px"/></center>
                </td>

            <tr>
                <td rowspan=3 >II.&nbsp;Posterior element damage with radiological evidence of moderate
                    partial dislocation/subluxation including whiplash injury </td>
            </tr>
            <tr>
                <td width = "42%" >
                    a)&nbsp;with fusion healed no permanent motor or sensory changes</td>
                <td style="text-align: center"><html:checkbox value="10" property="posterior_fusion" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    b)&nbsp;Persistent pain with radiological demonstrable instability
                </td>
                <td style="text-align: center"><html:checkbox value="25" property="posterior_persistent" style="width:25px"/>
                </td>
            </tr>

            <tr>
                <td width = "28%"rowspan=3 >III. Severe dislocation</td>
            </tr>
            <tr>
                <td width = "42%" >
                    a)&nbsp; Fair to good reduction with or without fusion with no residual motor or sensory involvement</td>
                <td style="text-align: center"><html:checkbox value="10" property="severe_fire" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    b)&nbsp; Inadequate reduction with fusion and persistent radicular pain</td>
                <td style="text-align: center"><html:checkbox value="15" property="severe_inadequate" style="width:25px"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=3 >5.1.2&nbsp;Cervical Intervertebral Disc Lesions
                </td>
            </tr>
            <tr>
                <td width = "42%" >
                    1) &nbsp;Treated case of disc lesion with persistent pain and no neurological deficit
                </td>
                <td style="text-align: center"><html:checkbox value="10" property="cervical_disc" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    2) &nbsp;Treated case with pain and instability
                </td>
                <td style="text-align: center"><html:checkbox value="15" property="cervical_pain" style="width:25px"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=5 >5.1.3&nbsp; Thoracic and Thoracolumbar Spine Injuries
                </td>
            </tr>
            <tr>
                <td width = "42%" >
                    1) &nbsp;Compression of less than 50% involving one vertebral body with no neurological manifestation
                </td>
                <td style="text-align: center"><html:checkbox value="10" property="thoracic_less" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    2)&nbsp; Compession of more than 50% involving single vertebra or more with involvement of posterior
                    elements, healed, no neurological manifestations persistent pain, fusion indicated </td>
                <td style="text-align: center"><html:checkbox value="20" property="thoracic_more" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    3) &nbsp;Same as (2) with fusion ,pain only heavy use of back </td>
                <td style="text-align: center"><html:checkbox value="15" property="thoracic_fusion" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    4) &nbsp;Radiologically demonstrable instability with fracture or fracture dislocation with persistent pain </td>
                <td style="text-align: center"><html:checkbox value="30" property="thoracic_radiologically" style="width:25px"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=4 >5.1.4 Lumbar and lumbosacral spine(Fracture)
                </td>
            </tr>
            <tr>
                <td width = "42%" >
                    1)&nbsp; Compression of 25% or less of one or two adjacent vertebral bodies no definite pattern or neurological
                    deficit
                </td>
                <td style="text-align: center"><html:checkbox value="15" property="fracture_less" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    2)&nbsp; Compression of 25% with disruption of posterior elements,persistent pain and stiffness,healed with or
                    with out fusion, inability to lift more than 10 kgs
                </td>
                <td style="text-align: center"><html:checkbox value="30" property="fracture_more" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    3)&nbsp; Radiologically demonstrable instability in low lumbar or lumbosacral spine with pain </td>
                <td style="text-align: center"><html:checkbox value="35" property="fracture_radiologically" style="width:25px"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=5 >5.1.5 Lumbar and lumbosacral(Disc Lesion)
                </td>
            </tr>
            <tr>
                <td width = "42%" >
                    1)&nbsp;Treated cases with persistent pain
                </td>
                <td style="text-align: center"><html:checkbox value="15" property="disc_persistent" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    2)&nbsp; Treated cases with pain and instability
                </td>
                <td style="text-align: center"><html:checkbox value="20" property="disc_pain" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    3)&nbsp; Treated cases of disc diseases with pain activities of lifting moderately modified
                </td>
                <td style="text-align: center"><html:checkbox value="25" property="disc_diseases" style="width:25px"/></td>
            </tr>
            <tr>
                <td width = "42%" >
                    4)&nbsp; Treated cases of disc diseases with persistent pain and stiffness, aggravated by heavy
                    lifting necessitating modifications of all activities requiring heavy weight lifting </td>
                <td style="text-align: center"><html:checkbox value="30" property="disc_stifness" style="width:25px"/></td></tr>
            <tr>
                <th colspan="3">5.2 NON-TRAUMATIC LESIONS</th>
            </tr>
        </table>

        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <td width="50%"><font size="2">5.2.1 SCOLIOSIS </font><html:checkbox  property="scoliosis" value="1" onclick="changeScoliosis()" style="width:25px"/></td>
                <td ><font size="2">5.2.2 KYPHOSIS </font><html:checkbox  property="kyphosis" value="1"  onclick="changekyphosis()" style="width:25px"/></td>
            </tr>
        </table>
        <div id="changePwd" style="visibility:hidden;display:none">
            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                <tr>
                    <td colspan="2"><font size="2">5.2.1 SCOLIOSIS</font></td>
                </tr>
       

                <tr>
                    <td width="50%">5.2.1.1 Measurement of spine deformity (Scoliosis)
                        Cobb's angle (in degrees)<br>
                            <html:radio  property="scoliosis_measure" value=""  onclick="checkradioscoliosis_measure1()" style="width:25px"/>0-20<BR>
                            <br><html:radio  property="scoliosis_measure" value="10" onclick="checkradioscoliosis_measure2()" style="width:25px"/>21-50<BR>
                            <br><html:radio  property="scoliosis_measure" value="20" onclick="checkradioscoliosis_measure3()" style="width:25px"/>51-100<BR>
                            <br><html:radio  property="scoliosis_measure" value="30" onclick="checkradioscoliosis_measure4()" style="width:25px"/>101& above
                    </td>
                     <td >5.2.1.2 Torso imbalance (Scoliosis)
                        Deviation of plumb line (in cm)

                      <BR><html:radio  property="scoliosis_torso" value="4" onclick="checkradioscoliosis_torso1()" style="width:25px"/>Up-to 1.5<BR>
                            <BR><html:radio  property="scoliosis_torso" value="8" onclick="checkradioscoliosis_torso2()" style="width:25px"/>1.6-3.0 <BR>
                            <BR><html:radio  property="scoliosis_torso" value="16" onclick="checkradioscoliosis_torso3()" style="width:25px"/>3.1-5.0<BR>
                            <BR><html:radio  property="scoliosis_torso" value="32" onclick="checkradioscoliosis_torso4()" style="width:25px"/>5.1 and above<BR>
                     </td>
                    </tr>

            </table>
        </div>

        <div id="change" style="visibility:hidden;display:none">
            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                <tr>
                    <td colspan="2"><font size="2">5.2.2 KYPHOSIS</font></td>
                </tr>
     

                <tr>
                    <td width="50%">5.2.2.1 Measurement of spine deformity (Kyphosis)
                        Angle (in degrees)<br/>
                        <ul><html:radio  property="kyphosis_measure" value="" onclick="checkradiokyphosis_measure1()" style="width:25px"/>Less than 20<br>
                            <BR><html:radio  property="kyphosis_measure" value="10" onclick="checkradiokyphosis_measure2()" style="width:25px"/>21 - 40<BR>
                            <BR><html:radio  property="kyphosis_measure" value="20" onclick="checkradiokyphosis_measure3()" style="width:25px"/>41 - 60<BR>
                            <BR><html:radio  property="kyphosis_measure" value="30" onclick="checkradiokyphosis_measure4()" style="width:25px"/>60 & above</ul>
                    </td>
                <td >5.2.2.2 Torso imbalance (Kyphosis)
                        Deviation of plumb line (in cm in front of ankle)<br/>
                      <html:radio  property="kyphosis_torso" value="4" onclick="checkradiokyphosis_torso1()" style="width:25px"/>Less than 5<br>
                            <BR><html:radio  property="kyphosis_torso" value="8" onclick="checkradiokyphosis_torso2()" style="width:25px"/>5 - 10  <br>
                            <BR><html:radio  property="kyphosis_torso" value="16" onclick="checkradiokyphosis_torso3()" style="width:25px"/>10 - 15<br>
                            <BR><html:radio  property="kyphosis_torso" value="32" onclick="checkradiokyphosis_torso4()" style="width:25px"/>More than 15
                </td>
                </tr>

            </table>
        </div>
        <div id="extratrunk" style="visibility:hidden;display:none" >

            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                <tr>
                    <td width="20%" colspan="2" valign="top">5.2.3 Head tilt over C7 spine<br><ul>

                            <html:radio  property="head"   value="4" onclick="checkradiohead1()" style="width:25px"/>Up to 15<br><br>
                            <html:radio  property="head"  value="10" onclick="checkradiohead2()" style="width:25px"/>More than 15<br></td></tr>
                         
                                <tr>
                                    <td width="20%" colspan="2"><font size="2">5.2.4 Cardio-pulmonary test</font></td>
                                </tr>


                                <tr>
                                    <td width="20%" >5.2.4.1 Chest Expansion
                                        <ul><html:radio  property="cardio_chest" value="" onclick="checkradiocardio_chest1()" style="width:25px"/>4-5 cm<BR>
                                            <BR><html:radio  property="cardio_chest" value="5" onclick="checkradiocardio_chest2()" style="width:25px"/>3 cm<BR>
                                            <BR><html:radio  property="cardio_chest" value="10" onclick="checkradiocardio_chest3()" style="width:25px"/>2 cm<BR>
                                            <BR><html:radio  property="cardio_chest" value="15" onclick="checkradiocardio_chest4()" style="width:25px"/>1 cm<BR>
                                            <BR><html:radio  property="cardio_chest" value="25" onclick="checkradiocardio_chest5()" style="width:25px"/>No Expansion<BR>
                                             <BR><BR>
                                               <BR><BR>

                                            </td>
                                             <br><br> </ul>
                           
                                            <td width="20%" >5.2.4.2 Counting in one breath
                                        <ul><html:radio  property="cardio_counting"  value="" onclick="checkradiocardio_counting1()" style="width:25px"/>Breath count more than 40<BR>
                                            <BR><html:radio  property="cardio_counting"  value="5" onclick="checkradiocardio_counting2()" style="width:25px"/>0-40<BR>
                                            <BR><html:radio  property="cardio_counting"   value="10" onclick="checkradiocardio_counting3()" style="width:25px"/>0-30<BR>
                                            <BR><html:radio  property="cardio_counting"   value="15" onclick="checkradiocardio_counting4()" style="width:25px"/>0-20<BR>
                                            <BR><html:radio  property="cardio_counting"   value="20" onclick="checkradiocardio_counting5()" style="width:25px"/>0-10<BR>
                                            <BR><html:radio  property="cardio_counting"  value="25" onclick="checkradiocardio_counting6()" style="width:25px"/>Less than 5<br>
                                              <BR><BR>
                                            </td></ul>
                                </tr>
                        
                                <tr>
                                    <td colspan="3"width="20%" >5.2.4.3 Associated problem</td>
                                </tr>
                                <tr>

                                        <td width="20%" >A) Pain<br><br>

                                      <html:radio  property="cardio_associatepain"  value="4" onclick="checkradiocardio_associatepain1()" style="width:25px"/>Mild interfering with ADL<br>
                                            <BR><html:radio  property="cardio_associatepain"  value="6" onclick="checkradiocardio_associatepain2()" style="width:25px"/>Moderately restricting with ADL<br>
                                            <BR><html:radio  property="cardio_associatepain"  value="10" onclick="checkradiocardio_associatepain3()" style="width:25px"/>Severely restricting ADL<br>
                                        </td>

                                        <td width="20%" >B) Cosmetic appearance<br><br>

                                      <html:radio  property="cardio_associatecosmetic"  value="" onclick="checkradiocardio_associatecosmetic1()" style="width:25px"/>No obvious disfiguration with clothes on<br>
                                            <BR><html:radio  property="cardio_associatecosmetic"  value="2" onclick="checkradiocardio_associatecosmetic2()" style="width:25px"/>Mild disfigurement<br>
                                            <BR><html:radio  property="cardio_associatecosmetic"  value="4" onclick="checkradiocardio_associatecosmetic3()" style="width:25px"/>Severe disfigurement</td>
                                <tr>
                                    <td colspan="3" width="20%" >C) Leg length discrepancy (shortening in inches)
                                        <html:text  property="cardio_associateleg"  maxlength="4" onblur="this.value=removeSpaces(this.value);" style="width:200px"/></td>
                                </tr>


            </table>
                            </div>

                                <TABLE align="center" border="0" class="inputform" width="90%">
                                <tr>

                                    <th colspan="3"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                                    <html:submit value="Add"  styleId="trunButton" styleClass="button"/>
                                   <html:reset value="Reset" onclick="resetbutton(this.form)" styleClass="button"/></th>
                                </tr>
                            </TABLE>




                        </html:form>
                        </body>
                    </html:html>
