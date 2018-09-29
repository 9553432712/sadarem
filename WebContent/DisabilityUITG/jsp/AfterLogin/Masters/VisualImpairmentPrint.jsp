<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html locale="true">
    <script language="javascript" >
        function goBack()
        {
        document.forms[0].action="LocomotorSublinkForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
        document.forms[0].submit();
        }


       function textCounter2()
        {
   if (document.forms[0].anyotherspecify.value.length > 100) // if too long...trim it!
       document.forms[0].anyotherspecify.value = document.forms[0].anyotherspecify.value.substring(0,100);
    // otherwise, update 'characters left' counter
        }

        function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        thisform.setAttribute('submitted','true');
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
            @media print
            {
                #non-printable { display: none; }
                #printable { display: block; }
            }
        </style>

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

    
    <body onLoad="disableForm(vis);">
        <html:form action="getvisualimpairmentPrint.do" styleId="vis">
   <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table>
        <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%" height="20%">

            <tr>
            <td colspan="6" align="center" class="gridhdbg">VISUAL IMPAIRMENT</td>
          </tr>

            <tr>
                <td colspan="6" class="gridhdbg">Table for Assessment</td>
          </tr>

            <tr>
                <td width="10%" class="gridhdbg">Category</td>
                <td width="39%" class="gridhdbg">
                Better Eye</td>
                <td width="22%" class="gridhdbg">
                Worse Eye</td>
                <td width="10%" class="gridhdbg">
                %Age Impairment</td>
                <td width="9%" class="gridhdbg" >
                Select Old Appropriate</td>
                 <td width="10%" class="gridhdbg">
                 Select New Appropriate</td>

            </tr>

            <tr>
            <td class="gridbg1"><br>Category 0</td>
            <td class="gridbg1"><br>6/9-6/18</td>
            <td class="gridbg1"><br>6/24-6/36</td>
            <td class="gridbg1"><br>20%</td>
            <td align="center" class="gridbg1"><br><html:radio property="visualimpairment" value="20.0"/></td>
            <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="20.0"/></td>

            </tr>

            <tr>
                <td class="gridbg1"><br>Category I</td>
                <td class="gridbg1"><br>6/18-6/36</td>
            <td class="gridbg1"><br>6/60-Nil </td>
            <td class="gridbg1"><br>40%</td>
           <td class="gridbg1" align="center"><br><html:radio property="visualimpairment" value="40.0"/></td>
              <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="40.0"/></td>
            </tr>

            <tr>
                <td class="gridbg1"><br>Category II</td>
                <td class="gridbg1"><br>6/40-4/60 or field of vision 10-20 degree</td>
            <td class="gridbg1"><br>3/60- Nil</td>
            <td class="gridbg1"><br>75%</td>
           <td align="center" class="gridbg1"><br><html:radio property="visualimpairment" value="75.0"/></td>
              <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="75.0"/></td>
            </tr>

            <tr>
                <td class="gridbg1"><br>Category III</td>
                <td class="gridbg1"><br>3/60-1/60 or field of vision 10 degree</td>
            <td class="gridbg1"><br>F.C. at 1ft to Nil</td>
            <td class="gridbg1"><br>100%</td>
           <td align="center" class="gridbg1"><br><html:radio property="visualimpairment" value="101.0"/></td>
              <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="101.0"/></td>
            </tr>

            <tr>
                <td class="gridbg1"><br>Category IV</td>
                <td class="gridbg1"><br>F.C.(Finger Count) at 1ft to Nil or field of vision 10 degree</td>
            <td class="gridbg1"><br>F.C. at 1ft to Nil</td>
            <td class="gridbg1"><br>100%</td>
           <td align="center" class="gridbg1"><br><html:radio property="visualimpairment" value="100.0" /></td>
              <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="100.0"/></td>
            </tr>

            <tr>
                <td class="gridbg1"><br>One eyed Persons</td>
                <td class="gridbg1"><br>6/6</td>
            <td class="gridbg1"><br>F.C. at 1ft to Nil or field of vision 10 degree</td>
            <td class="gridbg1"><br>30%</td>
           <td align="center" class="gridbg1" ><br><html:radio property="visualimpairment" value="30.0"/></td>
              <td align="center" class="gridbg1"><br><input type="radio" name="rekha" value="30.0"/></td>
            </tr>
        </table>


        <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%">
      <tr>
           <td colspan="3" class="gridhdbg">Need Assessment/Referred/Recommended(Visual Impairment)</td>
             </tr>

            <tr>
                <td  width="60%" class="gridbg1">1. Surgery</td>
                <td width="20%" class="gridbg1">
                  <html:text property="surgery" size="20" maxlength="100"></html:text>
                </td>
                <td width="20%" class="gridbg1">
                <input type="text" size="20" maxlength="100"/>
                </td>


            </tr>

          <tr>
              <td colspan="3" class="gridbg1">2. Assistive & Augmentative Devices For  Visual Impairment</td>
          </tr>

        <tr>
            <td width="60%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.White Cane/Blind Stick </td>
            <td width="20%" class="gridbg1"><html:checkbox property="whitecane" value="White Cane/Blind Stick" > </html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="White Cane/Blind Stick"/></td>
        </tr>

        <tr>
            <td width="60%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Braille Equipments</td>
            <td width="20%" class="gridbg1"><html:checkbox property="brailleequipments" value="Braille Equipments" > </html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Braille Equipments"/></td>
        </tr>

        <tr>
            <td width="60%" class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Arithmetic Frames/Abacus</td>
            <td width="20%" class="gridbg1"><html:checkbox property="arithmeticframes" value="Arithmetic Frames/Abacus" ></html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Arithmetic Frames/Abacus"/></td>
        </tr>

        <tr>
            <td width="60%" class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Low Vision Aids (Spectacles,Magnifiers)</td>
            <td width="20%" class="gridbg1"><html:checkbox property="lowvisionaids" value="Low Vision Aids" > </html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Low Vision Aids"/></td>
        </tr>

        <tr>
            <td width="60%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Speech Synthesizer</td>
            <td width="20%" class="gridbg1"><html:checkbox property="speechsynthesizer" value="Speech Synthesizer" ></html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Speech Synthesizer"/></td>
        </tr>

        <tr>
            <td width="60%" class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Braille Short Hand Machines/TypeWriters</td>
            <td width="20%" class="gridbg1"><html:checkbox property="brailleshorthandmachines" value="Braille Short Hand Machines" >  </html:checkbox> </td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Braille Short Hand Machines"/></td>
        </tr>

        <tr>
            <td width="60%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Talking Watch/Calculator</td>
            <td width="20%" class="gridbg1"><html:checkbox property="talkingwatchcalculator" value="Talking Watch/Calculator" > </html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Talking Watch/Calculator"/></td>
        </tr>

        <tr>
            <td width="60%"  class="gridbg1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Any ADL Equipment</td>
            <td width="20%" class="gridbg1"><html:checkbox property="anyadlequipmentes" value="Any ADL Equipment" ></html:checkbox></td>
          <td width="20%" class="gridbg1"><input type="checkbox" name="rekha" value="Any ADL Equipment"/></td>
        </tr>

        <tr>
            <td width="60%" align="top"  class="gridbg1">3. Any Other Visual Impairment Specific Needs</td>
            <td width="20%" class="gridbg1">
              <html:textarea rows="4" cols="30" property="anyotherspecify" onkeydown="textCounter2()" onkeyup="textCounter2()"> </html:textarea>
            </td>
            <td width="20%" class="gridbg1"><textarea  rows="4" cols="30"  onkeydown="textCounter2()" onKeyUp="textCounter2()"> </textarea>
 </td>

        </tr>
        </table><br>


    </html:form>

        <form action="">
            <div id="non-printable">
              <table align="center">
            <tr>
            <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>
            <td><html:button property="but" value="Print" styleClass="button" onclick="window.print()"/></td>

            </tr>
        </table></div>
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
