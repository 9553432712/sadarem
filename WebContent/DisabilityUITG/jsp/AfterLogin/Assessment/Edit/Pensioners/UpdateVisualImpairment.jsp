<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html locale="true">
    <script language="javascript" >
        function uncheckRadio() {
            var choice = document.myform.visualimpairment;

            for (i = 0; i < choice.length; i++) {
                if ( choice[i].checked = true )
                    choice[i].checked = false;
            }
        }
        function goBack()
        {
            document.forms[0].action="LocomotorSublinkUpdateForwdAction.do?getDisabilityPercentages=getDisabilityPercentages";
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
                document.getElementById('trunButton').disabled = true;
                return true;
            }
        }

    </script>
    <body>
        <script language="JavaScript" src="./DisabilityUITG/js/Visual.js"></script>
        <html:form  styleId="myform" action="updatevisualimparment.do?updateVisualImpairment=updateVisualImpairment"
                    onsubmit="return avoidDuplicateForm(this)">
            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">UPDATE VISUAL IMPAIRMENT</th>
                </tr>
                        <tr>
                    <th colspan="8" >Table for Assessment</th>
                </tr>
                <tr>
                    <th>Category</th>
                    <th><br>Better Eye</th>
                    <th><br>Worse Eye</th>
                    <th><br>%Age Impairment</th>
                    <th><br>Select The Appropriate</th>
                </tr>
                <tr>
                    <td ><br>Category 0</td>
                    <td ><br>6/9-6/18</td>
                    <td ><br>6/24-6/36</td>
                    <td ><br>20%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="20.0"  ondblclick="uncheckRadio();"/></td>
                </tr>
                <tr>
                    <td ><br>Category I</td>
                    <td ><br>6/18-6/36</td>
                    <td ><br>6/60-Nill </td>
                    <td ><br>40%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="40.0" ondblclick="uncheckRadio();"/></td>
                </tr>

                <tr>
                    <td ><br>Category II</td>
                    <td ><br>6/40-4/60 or field of vision 10-20 degree</td>
                    <td ><br>3/60- Nill</td>
                    <td ><br>75%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="75.0" ondblclick="uncheckRadio();"/></td>
                </tr>
                <tr>
                    <td ><br>Category III</td>
                    <td ><br>3/60-1/60 or field of vision 10 degree</td>
                    <td ><br>F.C. at 1ft to Nill</td>
                    <td ><br>100%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="101.0" ondblclick="uncheckRadio();"/></td>
                </tr>
                <tr>
                    <td ><br>Category IV</td>
                    <td ><br>F.C.(Finger Count) at 1ft to Nill or field of vision 10 degree</td>
                    <td ><br>F.C. at 1ft to Nill</td>
                    <td ><br>100%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="100.0" ondblclick="uncheckRadio();"/></td>
                </tr>
                <tr>
                    <td ><br>One eyed Persons</td>
                    <td ><br>6/6</td>
                    <td ><br>F.C. at 1ft to Nill or field of vision 10 degree</td>
                    <td ><br>30%</td>
                    <td align="center"><br><html:radio property="visualimpairment" value="30.0" ondblclick="uncheckRadio();"/></td>
                </tr>
            </table>

            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">Need Assessment/Referred/Recommended(Visual Impairment)</th>
                </tr>  
                <tr>
                    <td  width="47%" >1. Surgery</td>
                    <td ><html:text property="surgery" maxlength="100" style="width:250px"></html:text> </td>
                </tr>
                <tr>
                    <td colspan="8" > 2. Assistive & Augmentative Devices For  Visual Impairment</td>
                </tr>
                <tr>
                    <td  > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.White Cane/Blind Stick</td>
                    <td><html:checkbox property="whitecane" value="White Cane/Blind Stick" style="width:25px"> </html:checkbox>
                    </td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Braille Equipments</td>
                    <td><html:checkbox property="brailleequipments" value="Braille Equipments" style="width:25px"> </html:checkbox></td>
                </tr>
                <tr>
                    <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Arithmetic Frames/Abacus</td>
                    <td><html:checkbox property="arithmeticframes" value="Arithmetic Frames/Abacus" style="width:25px"></html:checkbox></td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iv.Low Vision Aids (Spectacles,Magnifiers)</td>
                    <td><html:checkbox property="lowvisionaids" value="Low Vision Aids" style="width:25px"> </html:checkbox></td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; v.Speech Synthesizer</td>
                    <td><html:checkbox property="speechsynthesizer" value="Speech Synthesizer" style="width:25px"></html:checkbox></td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vi.Braille Short Hand Machines/TypeWriters</td>
                    <td><html:checkbox property="brailleshorthandmachines" value="Braille Short Hand Machines" style="width:25px">  </html:checkbox> </td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; vii.Talking Watch/Calculator</td>
                    <td><html:checkbox property="talkingwatchcalculator" value="Talking Watch/Calculator" style="width:25px"> </html:checkbox></td>
                </tr>
                <tr>
                    <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; viii.Any ADL Equipment</td>
                    <td><html:checkbox property="anyadlequipmentes" value="Any ADL Equipment" style="width:25px"></html:checkbox></td>
                </tr>
                <tr>
                    <td  >3. Any Other VisualImpairment Specific Needs</td>
                    <td><html:textarea rows="4" cols="30" property="anyotherspecify" onkeydown="textCounter2()" onkeyup="textCounter2()" style="width:300px"> </html:textarea></td>
                </tr>
        
                <tr>
                    <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
                   <html:submit value="Update" styleId="trunButton" styleClass="button"/>
                   <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button"/>
                </th>
            </table>
        </html:form>
    </BODY>
</html:html>
