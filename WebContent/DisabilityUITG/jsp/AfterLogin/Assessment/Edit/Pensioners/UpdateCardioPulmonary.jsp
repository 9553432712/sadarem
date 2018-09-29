<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html locale="true">
    <script language="JavaScript" type="text/JavaScript">

        function uncheckRadio() {
            var choice = document.CardioPulmonaryForm.cardiopulmonary;
            for (i = 0; i < choice.length; i++) {
                if ( choice[i].checked = true )
                    choice[i].checked = false;
            }
        }

        function goBack()
        {
            document.forms[0].action="PhysicalimpairmentUpdateForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
        }
        function resetbutton(form)
        {
            document.CardioPulmonaryForm.cardiopulmonary[0].checked = false;
            document.CardioPulmonaryForm.cardiopulmonary[1].checked = false;
            document.CardioPulmonaryForm.cardiopulmonary[2].checked = false;
            document.CardioPulmonaryForm.cardiopulmonary[3].checked = false;
            document.CardioPulmonaryForm.cardiopulmonary[4].checked = false;
            document.CardioPulmonaryForm.cardiopulmonary[5].checked = false;
            
        }

        function avoidDuplicateForm(thisform){
            if (thisform.getAttribute('submitted'))
                return false;
            else{
                thisform.setAttribute('submitted','true');
                document.getElementById('toatlDisButton').disabled = true;
            }
        }
    </script>
    <body>
        <html:form  styleId="CardioPulmonaryForm" action="updatecardiopulmonary.do?updateCardio=updateCardio"
                    onsubmit="return avoidDuplicateForm(this)">
            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
        <% if (request.getAttribute("msg") != null) {%>
        <font color="red"><center><%=request.getAttribute("msg")%></center></font>  <% }%>
        <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="85%">
            <tr>
                <th class="heading">7:&nbsp;&nbsp;UPDATE EVALUATION OF PHYSICAL IMPAIRMENT DUE TO CARDIOPULMONARY DISEASES</th>
            </tr>
        </table>

        <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="85%">
            <tr>
                <td>&nbsp;</td>
                <td align="center" class="labelBlue">
                    <b>Please tick in the appropriate box</b>
                </td>
            </tr>
            <tr>
                <td >
                    <font color="blue">Group 0:</font> A patient with cardiopulmonary<br>disease who is asymptomatic (i.e. has no 
                    symptom of breathlessness palpitation,fatigue<br>or chest pain)

                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="13" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>
            <tr>
                <td >
                    <font color="blue">Group-1:</font> A patient with cardiopulmonary 
                    disease who become symptomatic during his <br>
                    ordinary physical activity but has mild <br>
                    restriction of his physical activities.
                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="25" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>
            <tr>
                <td >
                    <font color="blue">Group-2:</font> A patient with cardiopulmonary 
                    disease who becomes symptomatic during his <br>ordinary physical activities

                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="38" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>
            <tr>
                <td >
                    <font color="blue">Group-3:</font> A patient  with cardiopulmonary 
                    disease who becomes symptomatic during less than<br> ordinary physical activity.
                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="63" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>
            <tr>
                <td >
                    <font color="blue">Group-4:</font> A patient with cardiopulmonary 
                    disease who is symptomatic even at rest or on <br>
                    mildest exertion so that his ordinary physical <br>
                    activities are severely or completetely restricted.
                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="88" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>

            <tr>
                <td >
                    <font color="blue">Group-5:</font> A patient with cardiopulmonary 
                    disease who gets intermittent symptoms at rest <br>
                    (i.e:patients with bronchial asthma,<br>
                    paroxysmal nocturnal dyspnoea,etc)
                </td>
                <td>
                    <center><html:radio property="cardiopulmonary" value="100" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                </td>
            </tr>
    
            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                <html:submit value="Update"  styleId="toatlDisButton"  styleClass="button"/>
             <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button" /></th>
            </tr>
        </table>
    </html:form>
</body>
</html:html>
