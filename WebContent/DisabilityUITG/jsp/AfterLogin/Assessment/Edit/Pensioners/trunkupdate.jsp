<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>



    <HEAD>
        <script language="javascript" >
            function goBack()
            {
                document.forms[0].action="PhysicalimpairmentUpdateForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
                document.forms[0].submit();
            }


            function validateshortning(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
                var cardio_associateleg=document.trunkform.cardio_associateleg.value;
                for (var i = 0; i < cardio_associateleg.length; i++) {
                    if (iChars.indexOf(cardio_associateleg.charAt(i)) != -1) {
                        alert ("Please Enter Valid shortening.");
                        document.trunkform.cardio_associateleg.value="";
                        document.trunkform.cardio_associateleg.focus();
                        return false;
                    }
                }
                if(isNaN(cardio_associateleg))
                {
                    alert("Enter Numbers only in your shortening")
                    document.trunkform.cardio_associateleg.value="";
                    document.trunkform.cardio_associateleg.focus();
                    return false;
                }
                if(parseInt(cardio_associateleg)<0 ||
                    parseInt(cardio_associateleg)>99)
                {
                    alert ("Please enter yours valid shortening!");
                    document.trunkform.cardio_associateleg.value="";
                    document.trunkform.cardio_associateleg.focus();
                    return false;
                }
                return true;
            }

            function removeSpaces(string)
            {
                return string.split(' ').join('');
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
            function check(){
                changeScoliosis();
                changekyphosis();
            }
            function avoidDuplicateForm(thisform){
                if (thisform.getAttribute('submitted'))
                    return false;
                else{
                    thisform.setAttribute('submitted','true');
                    document.getElementById('toatlDisButton').disabled = true;
                }
            }
        </SCRIPT>
        
    </HEAD>
    <body onload="check();">
        <script language="JavaScript" src="./DisabilityUITG/js/Trunk.js"></script>
        <html:form action="/trunkupdate.do?updateTrunkDetails=updateTrunkDetails" method="post"
                   styleId="trunkform" onsubmit="return avoidDuplicateForm(this)">
            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
        <LINK REL="stylesheet" HREF="../scripts/cssmaster_violet.css">
        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="8">5.Update Trunk</th>
            </tr>

            <tr>
                <th colspan="8">5.1.Traumatic Lesions:</th>
            </tr>
            <tr>
                <td colspan="8">5.1.1&nbsp;&nbsp;Cervical Spine injuries (Fracture)</td>
            </tr>

            <tr>
                <td  colspan="2" align="center">I.&nbsp;Compression of one or two adjacent vertebral bodies with no involvement of
                    posterior elements. No nerve root involvement, moderate neck rigidity and persistent soreness</td>
                <td align="center">
                    <html:checkbox value="20" property="compression"/>
                </td>
            </tr>
            <tr>
                <td width = "28%" rowspan=3 align="left" >II.&nbsp;Posterior element damage with radiological evidence
                    of moderate partial dislocation/subluxation including whiplash injury</td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    a)&nbsp;with fusion healed no permanent motor or sensory  changes</td>
                <td align ="center"><html:checkbox value="10" property="posterior_fusion"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    b)&nbsp;Persistent pain with radiological demonstrable instability
                </td>
                <td align ="center" ><html:checkbox value="25" property="posterior_persistent"/>
                </td>
            </tr>

            <tr>
                <td width = "28%"rowspan=3 align="left" >III. Severe dislocation</td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    a)&nbsp; Fair to good reduction with or without fusion with no residual motor or sensory involvement</td>
                <td align ="center" ><html:checkbox value="10" property="severe_fire"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    b)&nbsp; Inadequate reduction with fusion and persistent radicular pain</td>
                <td align ="center" ><html:checkbox value="15" property="severe_inadequate"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=3 align="left" >5.1.2&nbsp;Cervical Intervertebral Disc Lesions </td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    1) &nbsp;Treated case of disc lesion with persistent pain and no neurological deficit</td>
                <td align ="center"><html:checkbox value="10" property="cervical_disc"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    2) &nbsp;Treated case with pain and instability
                </td>
                <td align ="center" ><html:checkbox value="15" property="cervical_pain"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=5 align="left" >5.1.3&nbsp; Thoracic and Thoracolumbar Spine Injuries</td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    1) &nbsp;Compression of less than 50% involving one vertebral body with no neurological manifestation</td>
                <td align ="center" ><html:checkbox value="10" property="thoracic_less"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    2)&nbsp; Compession of more than 50% involving single vertebra or more with involvement of posterior elements, healed,
                    no neurological manifestations persistent pain, fusion indicated </td>
                <td align ="center" ><html:checkbox value="20" property="thoracic_more"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    3) &nbsp;Same as (2) with fusion ,pain only heavy use of back </td>
                <td align ="center" ><html:checkbox value="15" property="thoracic_fusion"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    4) &nbsp;Radiologically demonstrable instability with fracture or fracture dislocation with persistent pain </td>
                <td align ="center" ><html:checkbox value="30" property="thoracic_radiologically"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=4 align="left" >5.1.4 Lumbar and lumbosacral spine(Fracture)
                </td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    1)&nbsp; Compression of 25% or less of one or two adjacent vertebral bodies no definite pattern or neurological deficit
                </td>
                <td align ="center" ><html:checkbox value="15" property="fracture_less"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    2)&nbsp; Compression of 25% with disruption of posterior elements, persistent pain and stiffness, healed with or
                    with out fusion,inability to lift more than 10 kgs </td>
                <td align ="center" ><html:checkbox value="30" property="fracture_more"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    3)&nbsp; Radiologically demonstrable instability in low lumber or lumbosacral spine with pain </td>
                <td align ="center" ><html:checkbox value="35" property="fracture_radiologically"/></td>
            </tr>

            <tr>
                <td width = "28%"rowspan=5 align="left" >5.1.5 Lumbar and lumbosacral spine(Disc Lesion)</td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    1)&nbsp;Treated cases with persistent pain
                </td>
                <td align ="center" ><html:checkbox value="15" property="disc_persistent"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    2)&nbsp; Treated cases with pain and instability
                </td>
                <td align ="center" ><html:checkbox value="20" property="disc_pain"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    3)&nbsp; Treated cases of disc diseases with pain activities of lifting moderately modified
                </td>
                <td align ="center" ><html:checkbox value="25" property="disc_diseases"/></td>
            </tr>
            <tr>
                <td width = "42%" valign ="top" >
                    4)&nbsp; Treated cases of disc diseases with persistent pain and stiffness, aggravated by heavy
                    lifting necessitating modifications of all activities requiring heavy weight lifting </td>
                <td align ="center" ><html:checkbox value="30" property="disc_stifness"/></td></tr>

            <tr>
                <th width="20%" colspan="8">5.2 NON-TRAUMATIC LESIONS:</th>
            </tr>

            <tr>
                <td >5.2.1 SCOLIOSIS<html:checkbox  property="scoliosis" value="1" onclick="changeScoliosis()" /></td>
                <td colspan="4">5.2.2 KYPHOSIS<html:checkbox  property="kyphosis" value="1"  onclick="changekyphosis()" /></td>
            </tr>
        </table>
        <div id="changePwd" style="display:none;visibility:hidden">
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">5.2.1 SCOLIOSIS</th>
                </tr>

                <tr>
                    <td width="50%">5.2.1.1 Measurement of spine deformity (Scoliosis)
                        <b>Cobb's angle (in degrees)</b></ul>

                <ul><html:radio  property="scoliosis_measure" value="0"  onclick="checkradioscoliosis_measure1()"/>0-20<BR>
                            <br><html:radio  property="scoliosis_measure" value="10" onclick="checkradioscoliosis_measure2()"/>21-50<BR>
                            <br><html:radio  property="scoliosis_measure" value="20" onclick="checkradioscoliosis_measure3()"/>51-100<BR>
                            <br><html:radio  property="scoliosis_measure" value="30" onclick="checkradioscoliosis_measure4()"/>101& above</b></ul>
                    </td>

                    <td >5.2.1.2 Torso imbalance (Scoliosis)
                        Deviation of plumb line (in cm)</ul>


                        <ul><html:radio  property="scoliosis_torso" value="4" onclick="checkradioscoliosis_torso1()"/>Up-to 1.5</b><BR>
                            <BR><html:radio  property="scoliosis_torso" value="8" onclick="checkradioscoliosis_torso2()"/>1.6-3.0 </b><BR>
                            <BR><html:radio  property="scoliosis_torso" value="16" onclick="checkradioscoliosis_torso3()"/>3.1-5.0</b><BR>
                            <BR><html:radio  property="scoliosis_torso" value="32" onclick="checkradioscoliosis_torso4()"/>5.1 and above</b><BR></ul>
                    </td>
                </tr>
            </table>
        </div>
        <div id="change" style="visibility:hidden;display:none">
              <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">5.2.2 KYPHOSIS</th>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <td width="50%">5.2.2.1 Measurement of spine deformity (Kyphosis)
                        Angle (in degrees)</ul>

                        <ul><html:radio  property="kyphosis_measure" value="0" onclick="checkradiokyphosis_measure1()"/>Less than 20</b><BR>
                            <BR><html:radio  property="kyphosis_measure" value="10" onclick="checkradiokyphosis_measure2()"/>21 - 40</b><BR>
                            <BR><html:radio  property="kyphosis_measure" value="20" onclick="checkradiokyphosis_measure3()"/>41 - 60</b><BR>
                            <BR><html:radio  property="kyphosis_measure" value="30" onclick="checkradiokyphosis_measure4()"/>60 & above</b></ul>
                    </td>

                    <td >5.2.2.2 Torso imbalance (Kyphosis)
                        Deviation of plumb line (in cm in front of ankle)</ul>

                    <ul><html:radio  property="kyphosis_torso" value="4" onclick="checkradiokyphosis_torso1()"/>Less than 5</b><br>
                            <BR><html:radio  property="kyphosis_torso" value="8" onclick="checkradiokyphosis_torso2()"/>5 - 10  </b><br>
                            <BR><html:radio  property="kyphosis_torso" value="16" onclick="checkradiokyphosis_torso3()"/>10 - 15</b><br>
                            <BR><html:radio  property="kyphosis_torso" value="32" onclick="checkradiokyphosis_torso4()"/>More than 15</b></ul>
                    </td>
                </tr>
            </table>
        </div>
        <div id="extratrunk" style="visibility:hidden;display:none">
             <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <td width="20%" >5.2.3 Head tilt over C7 spine:</td><ul></tr>
                    <tr><td ><html:radio  property="head"   value="4" onclick="checkradiohead1()"/>Up to 15<br><br>
                            <html:radio  property="head"  value="10" onclick="checkradiohead2()"/>More than 15<br></td></tr>
            </table>
               <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <td width="20%" >5.2.4 Cardio-pulmonary test</td>
                </tr>
            </table>
               <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <td width="20%" >5.2.4.1 Chest Expansion

                        <ul><html:radio  property="cardio_chest" value="0" onclick="checkradiocardio_chest1()"/>4-5 cm<BR>
                            <BR><html:radio  property="cardio_chest" value="5" onclick="checkradiocardio_chest2()"/>3 cm<BR>
                            <BR><html:radio  property="cardio_chest" value="10" onclick="checkradiocardio_chest3()"/>2 cm<BR>
                            <BR><html:radio  property="cardio_chest" value="15" onclick="checkradiocardio_chest4()"/>1 cm<BR>
                            <BR><html:radio  property="cardio_chest" value="25" onclick="checkradiocardio_chest5()"/>No Expansion</ul>
                    </td>

                    <td width="20%" >5.2.4.2 Counting in one breath<br>

                        <ul><html:radio  property="cardio_counting"  value="0" onclick="checkradiocardio_counting1()"/>Breath count more than 40<BR>
                            <BR><html:radio  property="cardio_counting"  value="5" onclick="checkradiocardio_counting2()"/>0-40<BR>
                            <BR><html:radio  property="cardio_counting"   value="10" onclick="checkradiocardio_counting3()"/>0-30<BR>
                            <BR><html:radio  property="cardio_counting"   value="15" onclick="checkradiocardio_counting4()"/>0-20<BR>
                            <BR><html:radio  property="cardio_counting"   value="20" onclick="checkradiocardio_counting5()"/>0-10<BR>
                            <BR><html:radio  property="cardio_counting"  value="25" onclick="checkradiocardio_counting6()"/>Less than 5</ul>
                    </td>
                </tr>
            </table>
              <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <td width="20%" colspan="4">5.2.4.3 Associated problem<br><br>

                    </td>
                </tr>
                <tr>
                    <td width="20%" >
                         A)Pain<br>
                    <ul><html:radio  property="cardio_associatepain"  value="4" onclick="checkradiocardio_associatepain1()" style="width:25px"/>Mild interfering with ADL<br>
                            <BR><html:radio  property="cardio_associatepain"  value="6" onclick="checkradiocardio_associatepain2()" style="width:25px"/>Moderately restricting with ADL<br>
                            <BR><html:radio  property="cardio_associatepain"  value="10" onclick="checkradiocardio_associatepain3()" style="width:25px"/>Severely restricting ADL<br>
                        </ul>
                    </td>

                    <td width="20%" >B)Cosmetic appearance<br>

                        <ul><html:radio  property="cardio_associatecosmetic"  value="0" onclick="checkradiocardio_associatecosmetic1()" style="width:25px"/>No obvious disfiguration with clothes on<br>
                            <BR><html:radio  property="cardio_associatecosmetic"  value="2" onclick="checkradiocardio_associatecosmetic2()" style="width:25px"/>Mild disfigurement<br>
                            <BR><html:radio  property="cardio_associatecosmetic"  value="4" onclick="checkradiocardio_associatecosmetic3()" style="width:25px"/>Severe disfigurement</ul>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">C)Leg length discrepancy
                        (shortening in inches)
                        <html:text  property="cardio_associateleg"  maxlength="4" onblur="this.value=removeSpaces(this.value);"/><br></ul></td></tr>
            </table><br>
        </div>
           <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
             <html:submit value="Update" styleId="toatlDisButton" onfocus="validateshortning()" styleClass="button"/>
             <html:button property="" value="Reset" onclick="resetbutton()" styleClass="button"/></th>
            </tr>
           </table>
    </html:form>
</body>
</html:html>
