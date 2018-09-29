<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html locale="true">
 <script language="javascript" >
    function goBack()
{
            document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
}
  function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
         else{
        thisform.setAttribute('submitted','true');         
                document.getElementById('cardioButton').disabled = true;
        }
    }
</script> 
    <body>
        <html:form action="cardiopulmonary.do?insertCardioPulmonary=insertCardioPulmonary" onsubmit="return avoidDuplicateForm(this)">
                     
         <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th >7:&nbsp;&nbsp;ADD EVALUATION OF PHYSICAL IMPAIRMENT DUE TO CARDIOPULMONARY DISEASES</th>
            
                    <th>
                        <b>Please tick in the appropriate box</b>
                    </th>
                </tr>
                <tr>
                    <td><font color="blue">
                             Group 0: </font> A patient with cardiopulmonary disease who is asymptomatic (i.e. has no
                        symptom of breathlessness palpitation,fatigue or chest pain)
       
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="13"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                   <td ><font color="blue">
                           Group-1: </font> A patient with cardiopulmonary 
                        disease who become symptomatic during his 
                        ordinary physical activity but has mild 
                        restriction of his physical activities.
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="25"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                   <td ><font color="blue">
                           Group-2: </font> A patient with cardiopulmonary 
                        disease who becomes symptomatic during his ordinary physical activities
           
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="38"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                   <td ><font color="blue">
                           Group-3: </font> A patient  with cardiopulmonary 
                        disease who becomes symptomatic during less than ordinary physical activity.
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="63"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                    <td ><font color="blue">
                            Group-4: </font> A patient with cardiopulmonary 
                        disease who is symptomatic even at rest or on 
                        mildest exertion so that his ordinary physical 
                        activities are severely or completely restricted.
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="88"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
       
                <tr>
                   <td ><font color="blue">
                           Group-5: </font> A patient with cardiopulmonary 
                        disease who gets intermittent symptoms at rest
                        (i.e:patients with bronchial asthma,
                        paroxysmal nocturnal dyspnoea,etc)
                    </td>
                    <td>
                        <center><html:radio property="cardiopulmonary" value="100"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
         
                <tr>
                    <th colspan="6"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                   <html:submit value="Add" styleId="cardioButton" styleClass="button"/>
                   <html:reset value="Reset" styleClass="button"/></th></tr>
            </table>
        </html:form>
    </body>
</html:html>
