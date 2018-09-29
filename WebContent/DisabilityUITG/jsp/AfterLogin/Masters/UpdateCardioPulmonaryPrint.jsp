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
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
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

    <body onLoad="disableForm(CardioPulmonaryForm);">
        <html:form  styleId="CardioPulmonaryForm" action="getcardiopolumonaryPrint.do">
             <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >
            
          <% if(request.getAttribute("msg")!=null) { %>
          <font color="red"><center><%=request.getAttribute("msg")%></center></font>  <% } %>
           <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>
          <table  align="center" cellspacing="1" cellpadding="1" class="innerTable" width="85%">
            <tr>
                <td class="gridhdbg" style="border:1px #000 solid;">7:&nbsp;&nbsp; EVALUATION OF PHYSICAL IMPAIRMENT DUE TO CARDIOPULMONARY DISEASES</td>
            </tr>
              </table>

          <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="85%">
                <tr>
                  <td width="60%" class="gridhdbg">&nbsp;</td>
                    <td width="20%" align="center" class="gridhdbg">
                      <b>Please tick in the appropriate box<br/>( Old Values )</b>
                    </td>
                     <td width="20%" align="center" class="gridhdbg">
                        <b>Please tick in the appropriate box<br/>
                       ( New Values )</b>
                  </td>
                </tr>
                <tr>
                    <td width="60%" class="gridbg1">
                        <font color="blue">Group 0:</font> A patient with cardiopulmonary disease who is asymptomatic (i.e. has no  symptom of breathlessness palpitation,fatigue or chest pain)

                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="13" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                    <td width="60%" class="gridbg1" style="font-size:11px;">
                        <font color="blue">Group-1:</font> A patient with cardiopulmonary
                        disease who become symptomatic during his
                        ordinary physical activity but has mild
                        restriction of his physical activities.
                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="25" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                    <td width="60%" class="gridbg1" style="font-size:11px;">
                        <font color="blue">Group-2:</font> A patient with cardiopulmonary
                        disease who becomes symptomatic during his <br>ordinary physical activities

                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="38" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                    <td width="60%" class="gridbg1" style="font-size:11px;">
                        <font color="blue">Group-3:</font> A patient  with cardiopulmonary
                        disease who becomes symptomatic during less than ordinary physical activity.
                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="63" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
                <tr>
                    <td width="60%" class="gridbg1" style="font-size:11px;">
                        <font color="blue">Group-4:</font> A patient with cardiopulmonary
                        disease who is symptomatic even at rest or on
                        mildest exertion so that his ordinary physical
                        activities are severely or completetely restricted.
                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="88" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>

                <tr>
                    <td width="60%" class="gridbg1" style="font-size:11px;">
                        <font color="blue">Group-5:</font> A patient with cardiopulmonary
                        disease who gets intermittent symptoms at rest
                        (i.e:patients with bronchial asthma,
                        paroxysmal nocturnal dyspnoea,etc)
                    </td>
                    <td width="20%" class="gridbg1">
                        <center><html:radio property="cardiopulmonary" value="100" ondblclick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                    <td width="20%" class="gridbg1">
                      <center><input type="radio" name="mohan" value="13" onDblClick="uncheckRadio();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
                    </td>
                </tr>
            </table><br>


        </html:form>
            <form action="">
                 <table align="center">
                <tr>
                     <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <td><html:button property=""  value="Next" onclick="goURL()"  styleClass="button"/></td>
                    <td><html:button  property="" value="Print" onclick="window.print()" styleClass="button" /></td>
                </tr>
            </table>
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
