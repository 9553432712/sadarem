<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
     <head>



        <body  onload="window.print()"><form>

    <logic:present name="mentalIllnessBean" scope="request">
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
        <TABLE align="center" width="80%">
            <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>
            <tr>
                <th align="center"><font size="4">12.&nbsp;&nbsp;MENTAL&nbsp; ILLNESS</font></th>
            </tr>
            <tr >
                <th align="left"><font size="3">&nbsp;&nbsp;12.1 &nbsp;&nbsp;Evaluation of Mental Illness<br><br></font></th>
            </tr>
        </TABLE>
        <TABLE  border="0" align="center" >
            <tr>
                <td align="left"> <font size="2">&nbsp;&nbsp;&nbsp;Indian Disability Evaluation and Assessment Scale (IDEAS)<br>&nbsp;&nbsp;&nbsp;Developed by Rehabilitation committee of Indian Psychiatry Association (IPA)<br><br>
                </font></td>
                <td align="right"> <font size="2"><br></font></td>
            </tr>
        </TABLE>
        <logic:notEqual name="mentalIllnessBean"  property="selfcare" value="0">
            <table  border="0" align="center"  width="80%">
            <tr>
            <td width="80%"><b><li>&nbsp;&nbsp; Self care:</li></b>
            <UL>Taking care of body (hygiene,gromming,bathing,toileting,dressing,eating,taking care of one's health)</td>
            <td width="30%"><CENTER><BR>
                <b><bean:write name="mentalIllnessBean" property="selfcare"/>
            </CENTER></td></tr></table>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="personalactivities" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B><li>&nbsp; &nbsp; Inter personal Activities (Social relationships):</li></B>
            <UL>Initiating and maintaing interaction with others in contextual and social appropriate manner</td>
            <td width="30%">
            <CENTER><BR>
                <b><bean:write name="mentalIllnessBean" property="personalactivities"/>
            </CENTER></td></tr></table>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="communication" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B><li>&nbsp; &nbsp;Communication and Understanding:</li></B>
            <UL>Including communication and conversions with others by producing and comprehending spoken/written/non-verbal messages</td>
            <td width="30%">
            <CENTER><BR> <b><bean:write name="mentalIllnessBean" property="communication"/>
            </CENTER></td></tr></table>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="work" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B><li>&nbsp; &nbsp;Work:</li></B>
            <UL>Ability to perform tasks at employee,family,house hold and at school,Completly and efficienty and in proper time</td>
            <td width="30%">
            <CENTER><BR><b><bean:write name="mentalIllnessBean" property="work"/>
            </CENTER></td></tr></table>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="duration" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B><FONT SIZE="3"><li> &nbsp;&nbsp;Duration of Illness:</li><B></FONT>
            <td width="30%">
            <CENTER><BR> <b><bean:write name="mentalIllnessBean" property="duration"/>
            </CENTER></td></tr></table>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="globalscore" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B>&nbsp; &nbsp;Global Disability Score (Total):</B>
            <UL><b>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;(<logic:notEqual name="mentalIllnessBean" property="selfcare" value="0"><bean:write name="mentalIllnessBean" property="selfcare"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="personalactivities" value="0">+<bean:write name="mentalIllnessBean" property="personalactivities"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="work" value="0">+<bean:write name="mentalIllnessBean" property="work"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="communication" value="0">+<bean:write name="mentalIllnessBean" property="communication"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="duration" value="0">+<bean:write name="mentalIllnessBean" property="duration"/></logic:notEqual>)</b></td>
            <td width="30%">
            <CENTER><BR><b><bean:write name="mentalIllnessBean" property="globalscore"/>
            </CENTER></td></tr></table>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="mentaldisability" value="0">
            <table  border="0" align="center" width="80%">
            <tr valign="top">
            <td width="80%"><B>&nbsp; &nbsp;Disability Percentage:</B>
            <UL>This is the value generated as per the Chart</td>
            <td width="30%">
            <CENTER><BR><b><bean:write name="mentalIllnessBean" property="mentaldisability"/>
            </CENTER></td></tr></table>
        </logic:notEqual>

<br>

    <TABLE width="100%" align=center border=0>
      <TR >
          <tr align="center"><b><font size="3.9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Chart&nbsp; Details</font></b></tr>
          <tr ></tr>

          <tr >If Global Disability Score=0    Then    Mental Illness Disability(Final Value)  =0   </tr>
          <tr >If Global Disability Score=1    Then    Mental Illness Disability(Final Value)  =7   </tr>
          <tr >If Global Disability Score=2    Then    Mental Illness Disability(Final Value)  =13   </tr>
          <tr >If Global Disability Score=3    Then    Mental Illness Disability(Final Value)  =20   </tr>
          <tr >If Global Disability Score=4    Then    Mental Illness Disability(Final Value)  =26   </tr>
          <tr> If Global Disability Score=5    Then    Mental Illness Disability(Final Value)  =33   </tr>
          <tr >If Global Disability Score=6    Then    Mental Illness Disability(Final Value)  =39   </tr>
          <tr >If Global Disability Score=7    Then    Mental Illness Disability(Final Value)  =40   </tr>
          <tr >If Global Disability Score=8    Then    Mental Illness Disability(Final Value)  =45   </tr>
          <tr >If Global Disability Score=9    Then    Mental Illness Disability(Final Value)  =50   </tr>
          <tr >If Global Disability Score=10   Then    Mental Illness Disability(Final Value)  =55   </tr>
          <tr> If Global Disability Score=11   Then    Mental Illness Disability(Final Value)  =60   </tr>
          <tr >If Global Disability Score=12   Then    Mental Illness Disability(Final Value)  =65   </tr>
          <tr >If Global Disability Score=13   Then    Mental Illness Disability(Final Value)  =70   </tr>
          <tr >If Global Disability Score=14   Then    Mental Illness Disability(Final Value)  =71   </tr>
          <tr >If Global Disability Score=15   Then    Mental Illness Disability(Final Value)  =77   </tr>
          <tr >If Global Disability Score=16   Then    Mental Illness Disability(Final Value)  =83   </tr>
          <tr >If Global Disability Score=17   Then    Mental Illness Disability(Final Value)  =89   </tr>
          <tr >If Global Disability Score=18   Then    Mental Illness Disability(Final Value)  =95   </tr>
          <tr >If Global Disability Score=19   Then    Mental Illness Disability(Final Value)  =99   </tr>
          <tr> If Global Disability Score=20   Then    Mental Illness Disability(Final Value)  =100 </tr>
      </TR>
  </table>
    </logic:present>
         <BR><BR><BR>

   </form>
      </body>
</html:html>