<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
    <head><br><br><br><br>
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
    <logic:present name="cardioPulmonaryBean" scope="request">
  <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="85%">
       <tr>
           <td class="heading" align="center">7:&nbsp;&nbsp;EVALUATION OF PHYSICAL IMPAIRMENT DUE TO CARDIOPULMONARY DISEASES</td>
        </tr>
        <tr><td colspan="3"  align="right" class="label">ID No.&nbsp;<font color="blue"><%=personcode%></font></td></tr>
        <tr><td colspan="3"  align="right" class="label">Name.&nbsp; <font color="blue"><%=name%></font></td></tr>
       </table>

        <table  align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="85%">
        <tr>
            <td align="center" class="labelBlue">GROUP</td>
                    <td align="right" class="labelBlue">percentage</td>
                </tr>

              </table>

       <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">


              <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="13">

                 <tr>
                    <td class="label">Group 0: A patient with cardiopulmonary disease who is asymptomatic (i.e. has no symptom of breathlessness palpitation,fatigue or chest pain)
                       </td>
        <td><center>   <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td>

                 </tr>

        </logic:equal>
     <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="25">

                      <tr>
                    <td class="label"> Group-1: A patient with cardiopulmonary
                        disease who become symptomatic <br>during his
                        ordinary physical activity but has mild
                        restriction of his <br>physical activities.
                    </td>
                       <td><center> <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>


         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="38">

                      <tr>
                    <td class="label">Group-2: A patient with cardiopulmonary
                        disease who becomes symptomatic during his ordinary physical activities
                    </td>
                        <td><center><bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>

         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="63">

                        <tr>
                    <td class="label"> Group-3: A patient  with cardiopulmonary
                        disease who becomes symptomatic during less than ordinary physical activity.
          </td>
                     <td><center>   <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>


        <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="88">

                         <tr>
                    <td class="label"> Group-4: A patient with cardiopulmonary
                        disease who is symptomatic <br>even at rest or on
                        mildest exertion so that his ordinary physical<br>
                        activities are severely or completely restricted.
                    </td>
            <td><center> <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>

         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="100">


                       <tr>
                    <td class="label">Group-5: A patient with cardiopulmonary
                        disease who gets intermittent <br> symptoms at rest <br>
                        (i.e:patients with bronchial asthma,
                        paroxysmal nocturnal dyspnoea,etc)

                   </td>
            <td><center>    <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>
     </logic:present>

            </table> <BR><BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=7&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
   </form>

    </body>
</html:html>