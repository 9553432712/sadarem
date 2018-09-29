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
        else{
        thisform.setAttribute('submitted','true');
         document.getElementById('visaddButton').disabled = true;
        }
    }

    </script>
    
    <body>
    <html:form action="visualimparment.do?insertVisualImpairment=insertVisualImpairment"
               onsubmit="return avoidDuplicateForm(this)">
    
    <logic:present name="msgVisual">
        <center><b><font color="blue" size="2"><bean:write name="msgVisual"/></font></b></center>
    </logic:present>
        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">

            <tr>
                <th colspan="5">ADD VISUAL IMPAIRMENT</th>
            </tr>

            <tr>
                 <th colspan="5">Table for Assessment</th>
            </tr>

            <tr>
                <th>Category</th>
                <th >Better Eye</th>
                <th >Worse Eye</th>
                <th>%Age Impairment</th>
                <th >Select Appropriate</th>
            </tr>

            <tr>
            <td ><br>Category 0</td>
            <td ><br>6/9-6/18</td>
            <td ><br>6/24-6/36</td>
            <td ><br>20%</td>
            <td ><br><html:radio property="visualimpairment" value="20.0"/></td>
            </tr>

            <tr>
                <td ><br>Category I</td>
                <td ><br>6/18-6/36</td>
                <td ><br>6/60-Nil </td>
                <td ><br>40%</td>
                <td  ><br><html:radio property="visualimpairment" value="40.0"/></td>
            </tr>

            <tr>
                <td ><br>Category II</td>
                <td ><br>6/40-4/60 or field of vision 10-20 degree</td>
                <td ><br>3/60- Nil</td>
                <td ><br>75%</td>
                <td ><br><html:radio property="visualimpairment" value="75.0"/></td>
            </tr>

            <tr>
                <td ><br>Category III</td>
                <td ><br>3/60-1/60 or field of vision 10 degree</td>
                <td ><br>F.C. at 1ft to Nil</td>
                <td ><br>100%</td>
                <td  ><br><html:radio property="visualimpairment" value="101.0"/></td>
            </tr>

            <tr>
                <td ><br>Category IV</td>
                <td ><br>F.C.(Finger Count) at 1ft to Nil or field of vision 10 degree</td>
                <td ><br>F.C. at 1ft to Nil</td>
                <td ><br>100%</td>
                <td  ><br><html:radio property="visualimpairment" value="100.0"/></td>
            </tr>

            <tr>
                <td ><br>One eyed Persons</td>
                <td ><br>6/6</td>
                <td ><br>F.C. at 1ft to Nil or field of vision 10 degree</td>
                <td ><br>30%</td>
                <td  ><br><html:radio property="visualimpairment" value="30.0"/></td>
            </tr>
    </table>

            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
            <tr>
                <th colspan="10">Need Assessment/Referred/Recommended(Visual Impairment)</th>
             
             </tr>

            <tr>
                <td  width="47%" >1. Surgery</td>
                <td colspan="8"><html:text property="surgery" size="40" maxlength="10" style="width:250px"></html:text> </td>
            </tr>

          <tr>
              <td colspan="10" >2. Assistive & Augmentative Devices For  Visual Impairment</td>
          </tr>

        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.White Cane/Blind Stick </td>
            <td  colspan="8"><html:checkbox property="whitecane" value="White Cane/Blind Stick"  style="width:25px"> </html:checkbox></td>
        </tr>

        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Braille Equipments</td>
            <td  colspan="8"><html:checkbox property="brailleequipments" value="Braille Equipments" style="width:25px"> </html:checkbox></td>
        </tr>

        <tr>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Arithmetic Frames/Abacus</td>
            <td  colspan="8"><html:checkbox property="arithmeticframes" value="Arithmetic Frames/Abacus" style="width:25px"></html:checkbox></td>
        </tr>

        <tr>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Low Vision Aids (Spectacles,Magnifiers)</td>
            <td  colspan="8"><html:checkbox property="lowvisionaids" value="Low Vision Aids" style="width:25px"> </html:checkbox></td>
        </tr>

        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Speech Synthesizer</td>
            <td  colspan="8"><html:checkbox property="speechsynthesizer" value="Speech Synthesizer" style="width:25px"></html:checkbox></td>
        </tr>

        <tr>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Braille Short Hand Machines/TypeWriters</td>
            <td  colspan="8"><html:checkbox property="brailleshorthandmachines" value="Braille Short Hand Machines" style="width:25px">  </html:checkbox> </td>
        </tr>

        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Talking Watch/Calculator</td>
            <td  colspan="8"><html:checkbox property="talkingwatchcalculator" value="Talking Watch/Calculator" style="width:25px"> </html:checkbox></td>
        </tr>

        <tr>
            <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Any ADL Equipment</td>
            <td  colspan="8"><html:checkbox property="anyadlequipmentes" value="Any ADL Equipment" style="width:25px"></html:checkbox></td>
        </tr>

        <tr>
            <td   align="top">3. Any Other Visual Impairment Specific Needs</td>
            <td  colspan="8"><html:textarea rows="4" cols="30" property="anyotherspecify" onkeydown="textCounter2()" onkeyup="textCounter2()" style="width:250x"> </html:textarea></td>

        </tr>
       
            <tr>
                <th colspan="10"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
           <html:submit value="Add" styleId="visaddButton" styleClass="button"/>
           <html:reset value="Reset" styleClass="button"/></th>
            </tr>
        </table>
    </html:form>
    </BODY>
</html:html>
