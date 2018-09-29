<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
     <head><br>

<script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>
       <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    </head>

    <body>
    <form>
    <logic:present name="mentalIllnessBean" scope="request">
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <td align="center" class="heading">12.&nbsp;&nbsp;MENTAL ILLNESS</td>
            </tr>
            <tr><td colspan="3"  class="label" align="right">ID No.&nbsp;<font color="blue"><%=personcode%></font></td></tr>
            <tr><td colspan="3"  align="right" class="label">Name.&nbsp;<font color="blue"><%=name%></font></td></tr>



        </table>
            <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
             <tr>
                 <td class="labelBlue" colspan="2">&nbsp;&nbsp;12.1 &nbsp;&nbsp;Evaluation of Mental Illness</td>
            </tr>
            <tr>
                <td class="label"> <font size="2">&nbsp;&nbsp;&nbsp;Indian Disability Evaluation and Assessment Scale (IDEAS)<br>
                        &nbsp;&nbsp;&nbsp;Developed by Rehabilitation committee of Indian Psychiatry Association (IPA)<br><br>
                </font></td>
                <td align="right"> <font size="2"><br></font></td>
            </tr>

        <logic:notEqual name="mentalIllnessBean"  property="selfcare" value="0">

            <tr>
            <td width="80%" class="label">&nbsp;&nbsp; Self care:
            Taking care of body (hygiene,gromming,bathing,toileting,dressing,eating,taking care of one's health)</td>
            <td width="30%" class="label"><CENTER><BR>
                <bean:write name="mentalIllnessBean" property="selfcare"/>
            </CENTER></td></tr>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="personalactivities" value="0">

            <tr>
            <td width="80%" class="label">&nbsp; &nbsp; Inter personal Activities (Social relationships):
                Initiating and maintaing interaction with others in contextual and social appropriate manner</td>
            <td width="30%" class="label">
            <CENTER><BR>
               <bean:write name="mentalIllnessBean" property="personalactivities"/>
            </CENTER></td></tr>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="communication" value="0">

            <tr>
            <td width="80%" class="label">&nbsp; &nbsp;Communication and Understanding:
            Including communication and conversions with others by producing and comprehending spoken/written/non-verbal messages</td>
            <td width="30%" class="label">
            <CENTER><BR><bean:write name="mentalIllnessBean" property="communication"/>
            </CENTER></td></tr>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="work" value="0">


            <tr>
            <td width="80%" class="label">&nbsp; &nbsp;Work:
            Ability to perform tasks at employee,family,house hold and at school,Completly and efficienty and in proper time</td>
            <td width="30%" class="label">
            <CENTER><BR><bean:write name="mentalIllnessBean" property="work"/>
            </CENTER></td></tr>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="duration" value="0">


            <tr>
            <td width="80%" class="label"> &nbsp;&nbsp;Duration of Illness:
            <td width="30%" class="label">
            <CENTER><BR><bean:write name="mentalIllnessBean" property="duration"/>
            </CENTER></td></tr>
        </logic:notEqual>


        <logic:notEqual name="mentalIllnessBean"  property="globalscore" value="0">

            <tr>
            <td width="80%" class="label">&nbsp; &nbsp;Global Disability Score (Total):
             &nbsp;(<logic:notEqual name="mentalIllnessBean" property="selfcare" value="0">
                 <bean:write name="mentalIllnessBean" property="selfcare"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="personalactivities" value="0">
                +<bean:write name="mentalIllnessBean" property="personalactivities"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="work" value="0">+<bean:write name="mentalIllnessBean" property="work"/>
            </logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="communication" value="0">
                +<bean:write name="mentalIllnessBean" property="communication"/></logic:notEqual>
            <logic:notEqual name="mentalIllnessBean"  property="duration" value="0">
                +<bean:write name="mentalIllnessBean" property="duration"/></logic:notEqual>)</td>
            <td width="30%" class="label">
            <CENTER><BR><bean:write name="mentalIllnessBean" property="globalscore"/>
            </CENTER></td></tr>
        </logic:notEqual>

        <logic:notEqual name="mentalIllnessBean"  property="mentaldisability" value="0">

            <tr>
            <td width="80%" class="label">&nbsp; &nbsp;Disability Percentage:
           This is the value generated as per the Chart</td>
            <td width="30%" class="label">
            <CENTER><BR><bean:write name="mentalIllnessBean" property="mentaldisability"/>
            </CENTER></td></tr>
        </logic:notEqual>
</table><br>

<table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable" width="85%">
      <tr>
      <td align="center" class="labelBlue">Chart  Details</td>
      </tr>

          <tr ><td class="label">If Global Disability Score=0    Then    Mental Illness Disability(Final Value)  =0  </td> </tr>
          <tr ><td class="label">If Global Disability Score=1    Then    Mental Illness Disability(Final Value)  =7  </td> </tr>
          <tr > <td class="label">If Global Disability Score=2    Then    Mental Illness Disability(Final Value)  =13   </td></tr>
          <tr ><td class="label">If Global Disability Score=3    Then    Mental Illness Disability(Final Value)  =20   </td></tr>
          <tr ><td class="label">If Global Disability Score=4    Then    Mental Illness Disability(Final Value)  =26   </td></tr>
          <tr> <td class="label">If Global Disability Score=5    Then    Mental Illness Disability(Final Value)  =33  </td> </tr>
          <tr ><td class="label">If Global Disability Score=6    Then    Mental Illness Disability(Final Value)  =39   </td></tr>
          <tr ><td class="label">If Global Disability Score=7    Then    Mental Illness Disability(Final Value)  =40   </td></tr>
          <tr ><td class="label">If Global Disability Score=8    Then    Mental Illness Disability(Final Value)  =45   </td></tr>
          <tr ><td class="label">If Global Disability Score=9    Then    Mental Illness Disability(Final Value)  =50  </td> </tr>
          <tr ><td class="label">If Global Disability Score=10   Then    Mental Illness Disability(Final Value)  =55   </td></tr>
          <tr><td class="label"> If Global Disability Score=11   Then    Mental Illness Disability(Final Value)  =60   </td></tr>
          <tr ><td class="label">If Global Disability Score=12   Then    Mental Illness Disability(Final Value)  =65   </td></tr>
          <tr ><td class="label">If Global Disability Score=13   Then    Mental Illness Disability(Final Value)  =70  </td></tr>
          <tr ><td class="label">If Global Disability Score=14   Then    Mental Illness Disability(Final Value)  =71  </td></tr>
          <tr ><td class="label">If Global Disability Score=15   Then    Mental Illness Disability(Final Value)  =77  </td> </tr>
          <tr ><td class="label">If Global Disability Score=16   Then    Mental Illness Disability(Final Value)  =83   </td></tr>
          <tr ><td class="label">If Global Disability Score=17   Then    Mental Illness Disability(Final Value)  =89   </td></tr>
          <tr ><td class="label">If Global Disability Score=18   Then    Mental Illness Disability(Final Value)  =95  </td> </tr>
          <tr ><td class="label">If Global Disability Score=19   Then    Mental Illness Disability(Final Value)  =99   </td></tr>
          <tr> <td class="label">If Global Disability Score=20   Then    Mental Illness Disability(Final Value)  =100</td> </tr>

  </table><br>
    </logic:present>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()" styleClass="button" />  </tr>

          <tr><a href="showCalc.do?typeofDisabilityFlag=18&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
    </form>


   </body>
</html:html>