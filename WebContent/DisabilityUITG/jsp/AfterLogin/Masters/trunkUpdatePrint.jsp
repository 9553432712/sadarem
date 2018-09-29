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
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
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
          //  document.getElementById("changePwd").style.visibility = "Hidden";
         //   document.getElementById("changePwd").style.display = "None";
            var kyphosis = document.getElementById("kyphosis");
            if(kyphosis.checked == false){
          //  document.getElementById("extratrunk").style.visibility = "Hidden";
          //  document.getElementById("extratrunk").style.display = "None";
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
         //   document.getElementById("change").style.visibility = "Hidden";
          //  document.getElementById("change").style.display = "None";
            var scoliosis = document.getElementById("scoliosis");
            if(scoliosis.checked == false){
         //   document.getElementById("extratrunk").style.visibility = "Hidden";
         //   document.getElementById("extratrunk").style.display = "None";
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
        thisform.setAttribute('submitted','true');
    }
        </SCRIPT>
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

        
    </HEAD>
    <body onLoad="check(),disableForm(trunkform);">
        <script language="JavaScript" src="./DisabilityUITG/js/Trunk.js"></script>
        <html:form action="/trunkselectPrint.do" method="post"
                   styleId="trunkform" >

            <LINK REL="stylesheet" HREF="../scripts/cssmaster_violet.css">
             <table align="center" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table>
           <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                <tr>
                   <th align=middle class="gridhdbg">5. Trunk</th>
                </tr>
                <tr>
                </tr>
                <tr>
                    <td class="gridhdbg">5.1.Traumatic Lesions:</td>
                </tr>
                <tr>
                    <td class="gridhdbg">5.1.1&nbsp;&nbsp;Cervical Spine injuries (Fracture)</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="100%">
                 <tr>
                <td width="30%" class="gridhdbg">&nbsp;</td><td width="30%" class="gridhdbg">&nbsp;</td>
                <td width="15%" class="gridhdbg">Old Values</td>
                <td width="15%" class="gridhdbg">New Values</td>

            </tr>
                <tr>
                    <td class="gridbg1" colspan="2">I.&nbsp;Compression of one or two adjacent vertebral bodies with no involvement of
                        posterior elements. No nerve root involvement, moderate neck rigidity and persistent soreness</td>
                <td class="gridbg1">
                    <center><html:checkbox value="20" property="compression"/></center>
                </td>
                <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "28%" rowspan=3 align="left" class="gridbg1">II.&nbsp;Posterior element damage with radiological evidence
                        of moderate partial dislocation/subluxation including whiplash injury</td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    a)&nbsp;with fusion healed no permanent motor or sensory  changes</td>
                    <td align ="center" class="gridbg1"><html:checkbox value="10" property="posterior_fusion"/></td>
                     <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        b)&nbsp;Persistent pain with radiological demonstrable instability
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="25" property="posterior_persistent"/>
                    </td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>

                <tr>
                    <td width = "28%" rowspan=3 align="left" class="gridbg1">III. Severe dislocation</td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    a)&nbsp; Fair to good reduction with or without fusion with no residual motor or sensory involvement</td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="10" property="severe_fire"/></td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    b)&nbsp; Inadequate reduction with fusion and persistent radicular pain</td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="15" property="severe_inadequate"/></td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>

                <tr>
                    <td width = "28%"rowspan=3 align="left" class="gridbg1">5.1.2&nbsp;Cervical Intervertebral Disc Lesions </td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        1) &nbsp;Treated case of disc lesion with persistent pain and no neurological deficit</td>
                    <td align ="center" class="gridbg1"><html:checkbox value="10" property="cervical_disc"/></td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        2) &nbsp;Treated case with pain and instability
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="15" property="cervical_pain"/></td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>

                <tr>
                    <td width = "28%"rowspan=5 align="left" class="gridbg1">5.1.3&nbsp; Thoracic and Thoracolumbar Spine Injuries</td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        1) &nbsp;Compression of less than 50% involving one vertebral body with no neurological manifestation</td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="10" property="thoracic_less"/></td>
                    <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    2)&nbsp; Compession of more than 50% involving single vertebra or more with involvement of posterior elements, healed,
                    no neurological manifestations persistent pain, fusion indicated </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="20" property="thoracic_more"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    3) &nbsp;Same as (2) with fusion ,pain only heavy use of back </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="15" property="thoracic_fusion"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    4) &nbsp;Radiologically demonstrable instability with fracture or fracture dislocation with persistent pain </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="30" property="thoracic_radiologically"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>

                <tr>
                    <td width = "28%"rowspan=4 align="left" class="gridbg1">5.1.4 Lumbar and lumbosacral spine(Fracture)
                    </td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        1)&nbsp; Compression of 25% or less of one or two adjacent vertebral bodies no definite pattern or neurological deficit
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="15" property="fracture_less"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        2)&nbsp; Compression of 25% with disruption of posterior elements, persistent pain and stiffness, healed with or
                        with out fusion,inability to lift more than 10 kgs </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="30" property="fracture_more"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                    3)&nbsp; Radiologically demonstrable instability in low lumber or lumbosacral spine with pain </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="35" property="fracture_radiologically"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>

                <tr>
                    <td width = "28%"rowspan=5 align="left" class="gridbg1">5.1.5 Lumbar and lumbosacral spine(Disc Lesion)</td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        1)&nbsp;Treated cases with persistent pain
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="15" property="disc_persistent"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        2)&nbsp; Treated cases with pain and instability
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="20" property="disc_pain"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                    <td width = "42%" valign ="top" class="gridbg1">
                        3)&nbsp; Treated cases of disc diseases with pain activities of lifting moderately modified
                    </td>
                    <td align ="center" class="gridbg1" ><html:checkbox value="25" property="disc_diseases"/></td>
                  <td align="left" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td>
                </tr>
                <tr>
                <td width = "42%" valign ="top" class="gridbg1">
                4)&nbsp; Treated cases of disc diseases with persistent pain and stiffness, aggravated by heavy
                lifting necessitating modifications of all activities requiring heavy weight lifting </td>
                <td align ="center" class="gridbg1" ><html:checkbox value="30" property="disc_stifness"/></td>
              <td align="center" class="gridbg1"> <center><input type ="checkbox" name="mohan"  value="100"  onclick="validatecheckboxupperright()"  /></center></td></tr>
            </table><br><br><br><br><br><br><br><br><br><br><br>
            <table  align="center" cellspacing="0" cellpadding="0" border="0" class="GRIDTB" width="100%">
<tr>
            <td width="20%" class="gridhdbg" colspan="2">5.2 NON-TRAUMATIC LESIONS:</td>
            </tr>
                <tr>
                    <td class="gridbg1">5.2.1 SCOLIOSIS<html:checkbox  property="scoliosis" value="1" onclick="changeScoliosis()" /></td>
                    <td class="gridbg1">5.2.2 KYPHOSIS<html:checkbox  property="kyphosis" value="1"  onclick="changekyphosis()" /></td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td width="50%" align="center" valign="top">


                 <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                    <tr>
                        <td class="gridhdbg" colspan="2">5.2.1 SCOLIOSIS</td>
                    </tr>
                <tr><td class="gridhdbg" colspan="2">5.2.1.1 Measurement of spine deformity (Scoliosis)
                <b>Cobb's angle (in degrees)</b></ul></td></tr>
                <tr><td class="gridhdbg">Old Values</td><td class="gridhdbg">New Values</td>
                   </tr>
                <tr><td class="gridbg1"><ul><html:radio  property="scoliosis_measure" value="0"  onclick="checkradioscoliosis_measure1()"/>0-20<BR>
                <br><html:radio  property="scoliosis_measure" value="10" onclick="checkradioscoliosis_measure2()"/>21-50<BR>
                <br><html:radio  property="scoliosis_measure" value="20" onclick="checkradioscoliosis_measure3()"/>51-100<BR>
                <br><html:radio  property="scoliosis_measure" value="30" onclick="checkradioscoliosis_measure4()"/>101& above</b></ul></td>

                <td class="gridbg1"><ul><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/>21-50<BR>
                <br><input type="radio"  name="mohan" value="10" onClick="checkradioscoliosis_measure2()"/>21-50<BR>
                <br><input type="radio"  name="mohan" value="20" onClick="checkradioscoliosis_measure3()"/>51-100<BR>
                <br><input type="radio"  name="mohan" value="30" onClick="checkradioscoliosis_measure4()"/>101& above</b></ul></td>

                </tr>
                <tr><td class="gridhdbg" colspan="2">5.2.1.2 Torso imbalance (Scoliosis)
                Deviation of plumb line (in cm)</ul></td></tr>
                <tr><td class="gridbg1"><ul><html:radio  property="scoliosis_torso" value="4" onclick="checkradioscoliosis_torso1()"/>Up-to 1.5</b><BR>
                <BR><html:radio  property="scoliosis_torso" value="8" onclick="checkradioscoliosis_torso2()"/>1.6-3.0 </b><BR>
                <BR><html:radio  property="scoliosis_torso" value="16" onclick="checkradioscoliosis_torso3()"/>3.1-5.0</b><BR>
                <BR><html:radio  property="scoliosis_torso" value="32" onclick="checkradioscoliosis_torso4()"/>5.1 and above</b><BR></ul></td>


                <td class="gridbg1"><ul><input type="radio"  name="mohan" value="4" onClick="checkradioscoliosis_torso1()"/>Up-to 1.5</b><BR>
                <BR><input type="radio"  name="mohan" value="8" onClick="checkradioscoliosis_torso2()"/>1.6-3.0 </b><BR>
                <BR><input type="radio"  name="mohan" value="16" onClick="checkradioscoliosis_torso3()"/>3.1-5.0</b><BR>
                <BR><input type="radio"  name="mohan" value="32" onClick="checkradioscoliosis_torso4()"/>5.1 and above</b><BR></ul></td>

                </tr>
                </table>
            </div>

                </td>
                <td width="50%" align="center" valign="top">
                	<div id="change" >
                 <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%">
                    <tr>
                        <td class="gridhdbg" colspan="2">5.2.2 KYPHOSIS</td>
                    </tr>
                    <tr><td class="gridhdbg" colspan="2">5.2.2.1 Measurement of spine deformity (Kyphosis)
                    Angle (in degrees)</ul></td></tr>
                    <tr><td class="gridbg1"><ul><html:radio  property="kyphosis_measure" value="0" onclick="checkradiokyphosis_measure1()"/>Less than 20</b><BR>
                    <BR><html:radio  property="kyphosis_measure" value="10" onclick="checkradiokyphosis_measure2()"/>21 - 40</b><BR>
                    <BR><html:radio  property="kyphosis_measure" value="20" onclick="checkradiokyphosis_measure3()"/>41 - 60</b><BR>
                    <BR><html:radio  property="kyphosis_measure" value="30" onclick="checkradiokyphosis_measure4()"/>60 & above</b></ul></td>

                    <td class="gridbg1"><ul><input type="radio"  name="mohan" value="0" onClick="checkradiokyphosis_measure1()"/>Less than 20</b><BR>
                    <BR><input type="radio"  name="mohan" value="10" onClick="checkradiokyphosis_measure2()"/>21 - 40</b><BR>
                    <BR><input type="radio"  name="mohan" value="20" onClick="checkradiokyphosis_measure3()"/>41 - 60</b><BR>
                    <BR><input type="radio"  name="mohan" value="30" onClick="checkradiokyphosis_measure4()"/>60 & above</b></ul></td>

                    </tr>
                    <tr><td class="gridhdbg" colspan="2">5.2.2.2 Torso imbalance (Kyphosis)
                    Deviation of plumb line (in cm in front of ankle)</ul></td></tr>
                   <tr><td class="gridbg1"> <ul><html:radio  property="kyphosis_torso" value="4" onclick="checkradiokyphosis_torso1()"/>Less than 5</b><br>
                    <BR><html:radio  property="kyphosis_torso" value="8" onclick="checkradiokyphosis_torso2()"/>5 - 10  </b><br>
                    <BR><html:radio  property="kyphosis_torso" value="16" onclick="checkradiokyphosis_torso3()"/>10 - 15</b><br>
                    <BR><html:radio  property="kyphosis_torso" value="32" onclick="checkradiokyphosis_torso4()"/>More than 15</b></ul></td>


                    <td class="gridbg1"> <ul><input type="radio"  name="mohan" value="4" onClick="checkradiokyphosis_torso1()"/>Less than 5</b><br>
                    <BR><input type="radio"  name="mohan" value="8" onClick="checkradiokyphosis_torso2()"/>5 - 10  </b><br>
                    <BR><input type="radio"  name="mohan" value="16" onClick="checkradiokyphosis_torso3()"/>10 - 15</b><br>
                    <BR><input type="radio"  name="mohan" value="32" onClick="checkradiokyphosis_torso4()"/>More than 15</b></ul></td>

                   </tr>
                </table>
            </div>
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center" valign="top">
                	<div id="extratrunk" >
                    	  <table width="100%" border="0" cellspacing="2" cellpadding="0">
                    	    <tr>
                    	      <td colspan="2"><table  align="center" cellspacing="0" cellpadding="0" class="gridhdbg" width="100%" border="0">
                    	        <tr>
                    	          <td width="20%" class="gridhdbg" colspan="3">5.2.3 Head tilt over C7 spine:</td>
                  	          </tr>
                    	        <tr>
                    	          <td width="50%" class="gridbg1"><html:radio  property="head"   value="4" onclick="checkradiohead1()"/>
                    	            Up to 15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	            <html:radio  property="head"  value="10" onclick="checkradiohead2()"/>
                    	            More than 15<br></td>
                    	          <td width="50%" class="gridbg1"><input type="radio"  name="mohan"   value="4" onClick="checkradiohead1()"/>
                    	            Up to 15&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	            <input type="radio"  name="mohan"  value="10" onClick="checkradiohead2()"/>
                    	            More than 15<br></td>
                  	          </tr>
                  	        </table></td>
                   	        </tr>
                    	    <tr>
                    	      <td class="gridhdbg" colspan="2" style="border:1px #000 solid;">5.2.4 Cardio-pulmonary test</td>
                   	        </tr>
                    	    <tr>
                    	      <td width="50%" align="center" valign="top"><table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                    	        <tr>
                    	          <td class="gridhdbg" colspan="2">5.2.4 Cardio-pulmonary test</td>
                  	          </tr>
                    	        <tr>
                    	          <td width="20%" class="gridhdbg" colspan="2">5.2.4.1 Chest Expansion</td>
                  	          </tr>
                    	        <tr>
                    	          <td width="50%" class="gridbg1"><ul>
                    	            <html:radio  property="cardio_chest" value="0" onclick="checkradiocardio_chest1()"/>
                    	            4-5 cm<BR>
                    	            <BR>
                    	            <html:radio  property="cardio_chest" value="5" onclick="checkradiocardio_chest2()"/>
                    	            3 cm<BR>
                    	            <BR>
                    	            <html:radio  property="cardio_chest" value="10" onclick="checkradiocardio_chest3()"/>
                    	            2 cm<BR>
                    	            <BR>
                    	            <html:radio  property="cardio_chest" value="15" onclick="checkradiocardio_chest4()"/>
                    	            1 cm<BR>
                    	            <BR>
                    	            <html:radio  property="cardio_chest" value="25" onclick="checkradiocardio_chest5()"/>
                    	            No Expansion
                  	            </ul></td>
                    	          <td width="50%" class="gridbg1"><ul>
                    	            <input type="radio"  name="mohan" value="0" onClick="checkradiocardio_chest1()"/>
                    	            4-5 cm<BR>
                    	            <BR>
                    	            <input type="radio"  name="mohan" value="5" onClick="checkradiocardio_chest2()"/>
                    	            3 cm<BR>
                    	            <BR>
                    	            <input type="radio"  name="mohan" value="10" onClick="checkradiocardio_chest3()"/>
                    	            2 cm<BR>
                    	            <BR>
                    	            <input type="radio"  name="mohan" value="15" onClick="checkradiocardio_chest4()"/>
                    	            1 cm<BR>
                    	            <BR>
                    	            <input type="radio"  name="mohan" value="25" onClick="checkradiocardio_chest5()"/>
                    	            No Expansion
                  	            </ul></td>
                  	          </tr>
                  	        </table>
                    	        <table  align="center" cellspacing="0" cellpadding="0" class="gridtb" width="100%" border="0">
                    	          <tr>
                    	            <td class="gridhdbg" colspan="2">5.2.4.2 Counting in one breath<br></td>
                  	            </tr>
                    	          <tr>
                    	            <td width="50%" class="gridbg1"><ul>
                    	              <html:radio  property="cardio_counting"  value="0" onclick="checkradiocardio_counting1()"/>
                    	              Breath count more than 40<BR>
                    	              <BR>
                    	              <html:radio  property="cardio_counting"  value="5" onclick="checkradiocardio_counting2()"/>
                    	              0-40<BR>
                    	              <BR>
                    	              <html:radio  property="cardio_counting"   value="10" onclick="checkradiocardio_counting3()"/>
                    	              0-30<BR>
                    	              <BR>
                    	              <html:radio  property="cardio_counting"   value="15" onclick="checkradiocardio_counting4()"/>
                    	              0-20<BR>
                    	              <BR>
                    	              <html:radio  property="cardio_counting"   value="20" onclick="checkradiocardio_counting5()"/>
                    	              0-10<BR>
                    	              <BR>
                    	              <html:radio  property="cardio_counting"  value="25" onclick="checkradiocardio_counting6()"/>
                    	              Less than 5
                  	              </ul></td>
                    	            <td width="50%" class="gridbg1"><input type="radio"  name="mohan"  value="0" onClick="checkradiocardio_counting1()"/>
                    	              Breath count more than 40<BR>
                    	              <BR>
                    	              <input type="radio"  name="mohan"  value="5" onClick="checkradiocardio_counting2()"/>
                    	              0-40<BR>
                    	              <BR>
                    	              <input type="radio"  name="mohan"   value="10" onClick="checkradiocardio_counting3()"/>
                    	              0-30<BR>
                    	              <BR>
                    	              <input type="radio"  name="mohan"   value="15" onClick="checkradiocardio_counting4()"/>
                    	              0-20<BR>
                    	              <BR>
                    	              <input type="radio"  name="mohan"   value="20" onClick="checkradiocardio_counting5()"/>
                    	              0-10<BR>
                    	              <BR>
                    	              <input type="radio"  name="mohan"  value="25" onClick="checkradiocardio_counting6()"/>
                    	              Less than 5
                    	              </ul></td>
                  	            </tr>
                  	          </table></td>
                    	      <td width="50%" align="center" valign="top"><table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="100%">
                    	        <tr>
                    	          <td width="20%" class="gridhdbg" colspan="2">5.2.4.3 Associated problem<br>
                    	            <br>
                    	            A)Pain</td>
                  	          </tr>
                    	        <tr>
                    	          <td class="gridbg1"><ul>
                    	            <html:radio  property="cardio_associatepain"  value="4" onclick="checkradiocardio_associatepain1()"/>
                    	            Mild interfering with ADL<br>
                    	            <BR>
                    	            <html:radio  property="cardio_associatepain"  value="6" onclick="checkradiocardio_associatepain2()"/>
                    	            Moderately restricting with ADL<br>
                    	            <BR>
                    	            <html:radio  property="cardio_associatepain"  value="10" onclick="checkradiocardio_associatepain3()"/>
                    	            Severely restricting ADL<br>
                  	            </ul></td>
                    	          <td class="gridbg1"><ul>
                    	            <input type="radio"  name="mohan"  value="4" onClick="checkradiocardio_associatepain1()"/>
                    	            Mild interfering with ADL<br>
                    	            <BR>
                    	            <input type="radio"  name="mohan"  value="6" onClick="checkradiocardio_associatepain2()"/>
                    	            Moderately restricting with ADL<br>
                    	            <BR>
                    	            <input type="radio"  name="mohan"  value="10" onClick="checkradiocardio_associatepain3()"/>
                    	            Severely restricting ADL<br>
                  	            </ul></td>
                  	          <tr>
                  	            <td class="gridhdbg" colspan="2">B)Cosmetic appearance</td>
                	            </tr>
                    	        <tr>
                    	          <td class="gridbg1"><ul>
                    	            <html:radio  property="cardio_associatecosmetic"  value="0" onclick="checkradiocardio_associatecosmetic1()"/>
                    	            No obvious disfiguration with clothes on<br>
                    	            <BR>
                    	            <html:radio  property="cardio_associatecosmetic"  value="2" onclick="checkradiocardio_associatecosmetic2()"/>
                    	            Mild disfigurement<br>
                    	            <BR>
                    	            <html:radio  property="cardio_associatecosmetic"  value="4" onclick="checkradiocardio_associatecosmetic3()"/>
                    	            Severe disfigurement
                  	            </ul></td>
                    	          <td class="gridbg1"><ul>
                    	            <input type="radio"  name="mohan"  value="0" onClick="checkradiocardio_associatecosmetic1()"/>
                    	            No obvious disfiguration with clothes on<br>
                    	            <BR>
                    	            <input type="radio"  name="mohan"  value="2" onClick="checkradiocardio_associatecosmetic2()"/>
                    	            Mild disfigurement<br>
                    	            <BR>
                    	            <input type="radio"  name="mohan"  value="4" onClick="checkradiocardio_associatecosmetic3()"/>
                    	            Severe disfigurement
                  	            </ul></td>
                  	          </tr>
                    	        <tr>
                    	          <td height="35" class="gridbg1">C)Leg length discrepancy
                    	            (shortening in inches)
                    	            <html:text  property="cardio_associateleg"  maxlength="4" onblur="this.value=removeSpaces(this.value);"/>
                    	            <br>
                    	            </ul></td>
                    	          <td class="gridbg1"><input type="text"  name="mohan2"  maxlength="4" onBlur="this.value=removeSpaces(this.value);"/>
                    	            <br>
                    	            </ul></td>
                  	          </tr>
                  	        </table></td>
                  	      </tr>
               	        </table>
                	</div>

                </td>
              </tr>
            </table>

        </html:form>
       <form action="">
            <TABLE align="center">
                <tr>
                       <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>
                    <td><html:button property="" value="Print" onclick="window.print();" styleClass="button"/></td>
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
