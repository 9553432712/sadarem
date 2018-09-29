<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% String devi= (String)request.getParameter("iqforall");
String testname=(String)request.getParameter("testname") ;
%>


     <html:html>
     <HEAD><br>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disabilities</title>
        <script LANGUAGE="JavaScript" SRC="../js/lw_layers.js"></script>
        <script LANGUAGE="JavaScript" SRC="../js/menu.js"></script>


       <script language="JavaScript1.2" src="DisabilityUITG/js/coolmenus3.js"></script>
       <script language="javascript" src="DisabilityUITG/js/cal2.js"></script>
       <script language="javascript" src="DisabilityUITG/js/cal_conf2.js"></script>
       
       <TITLE> New Document </TITLE>
       <META property="Generator" CONTENT="EditPlus">
       <META property="Author" CONTENT="">
       <META property="Keywords" CONTENT="">
       <META property="Description" CONTENT="">
       </HEAD>
       <script language="javascript" >
    function goBack()
{
           // document.forms[0].action="LocomotorSublinkUpdateForwdAction.do";
            document.forms[0].action="MentalRetardationForwdActionPrint.do";
            document.forms[0].submit();
}


    function textCounter2()
        {
   if (document.forms[0].anyothermentalretardation.value.length > 100) // if too long...trim it!
       document.forms[0].anyothermentalretardation.value = document.forms[0].anyothermentalretardation.value.substring(0,100);
    // otherwise, update 'characters left' counter
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


    <html:errors/>
      <script language="javascript" src="./DisabilityUITG/js/mental.js"></script>
      <body onload="disableForm(form3);">
    <html:form  action="/mentalretarselectPrint.do" method="post" styleId="form3">
 <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>
    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
    <tr>
        <td colspan=4 align="center" class="heading">11. &nbsp;&nbsp;MENTAL RETARDATION</td>
    </tr>
    </table>

    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                     <tr>
                         <td align="CENTER" class="labelBlue"><br>Intelligent Quotient

                     <html:text property="iq" size="15" maxlength="2" readonly="true" value="<%=devi%>" /></td>
                            <html:hidden property="chronologicalage"  value="<%=testname%>" />
                         </tr>
                 </table>
                         <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
             <tr>
                 <td colspan="3" class="heading">Need Assessment/Referred/Recommended</td>
                </tr>

                  <tr>
                      <td>&nbsp;</td>
                      <td class="label" align="center"><font color="blue">Old Values</font></td>
                      <td  class="label" align="center"><font color="blue">New Values</font></td>
                </tr>

                <tr>
                    <td colspan="3" class="label">1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
                </tr>
     <tr>
         <td  class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Speech Therapy</td>
         <td align="center"><html:checkbox property="speechtheraphy" value=" EarlyIntervention Speech Therapy" > </html:checkbox></td>
         <td align="center"><input type="checkbox" name="mohan" /> </td>
     </tr>
       <tr>
           <td class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Behavior Modification</td>
           <td align="center"><html:checkbox property="behaviormodification" value="Behavior Modification" > </html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>
     <tr>
         <td   class="label">2. Sensory Integration/Cognitive Stimulation</td>
        <td align="center"><html:checkbox property="sensoryintegrationcognitive" value="SensoryIntegrationcognitive" ></html:checkbox> </td>
        <td align="center"><input type="checkbox" name="mohan" /> </td>
     </tr>
      <tr>
          <td  class="label">3. Speech Therapy/Language Development</td>
          <td align="center"> <html:checkbox property="speechtherapy" value="Speech Therapy"></html:checkbox> </td>
          <td align="center"><input type="checkbox" name="mohan" /> </td>
      </tr>
      <%--  <tr>
           <td  class="label">4. Language Development</td>
           <td><html:checkbox property="languagedevelopment" value="Language Development"></html:checkbox></td>
       </tr>--%>

      <tr>
           <td  class="label">4. Physiotherpay</td>
           <td align="center"><html:checkbox  property="physiotherapy" value="Physiotherapy"></html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>

        <tr>
           <td  class="label">5. Occupational Therapy</td>
           <td align="center"><html:checkbox  property="occupationaltherapy" value="OccupationalTherapy"></html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>
       <tr>
           <td  class="label">6. Psycotherapy/Behaviour Modification</td>
           <td align="center"><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification"></html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>

       <tr>
           <td  class="label">7. Cognitive Behaviour Therapy</td>
           <td align="center"><html:checkbox property="cognitivebehaviourtherapy" value="CognitiveBehaviourTherapy"></html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>
       <tr>
           <td  class="label">8. Parent/Family Intervention</td>
           <td align="center"><html:checkbox property="parientfamilyintervention" value="ParientFamilyintervention"></html:checkbox></td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>
       </tr>

       <tr>
           <td  class="label">9. Legal Guardian (For Persons With MR, CP, Multiple Disabilities And Autism)</td>
           <td align="center"> <html:checkbox property="legalguardian" value="Legal Guardian"> </html:checkbox> </td>
           <td align="center"><input type="checkbox" name="mohan" /> </td>

       </tr>
       <tr>
           <td colspan="3" class="label">10.  Assistive & Augmentative Devices For  Mental  Retardation</td>
       </tr>
      <tr>
          <td  class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  i.Learning Materials For M.R(Books,CD etc)</td>
          <td align="center"><html:checkbox property="learningmaterials" value="Learning Materials For M.R" ></html:checkbox></td>
          <td align="center"><input type="checkbox" name="mohan" /> </td>
      </tr>
     <tr>
         <td  class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Special Software</td>
    <td align="center"> <html:checkbox property="specialsoftware" value="Special Software" ></html:checkbox> </td>
    <td align="center"><input type="checkbox" name="mohan" /> </td>
     </tr>
    <tr>
        <td  class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Toys</td>
        <td align="center"> <html:checkbox property="toys" value="Toys" ></html:checkbox></td>
        <td align="center"><input type="checkbox" name="mohan" /> </td>
    </tr>
      <tr>
          <td  class="label">11. Any Other Mental Retardation Needs</td>
          <td><html:textarea rows="4" cols="30" property="anyothermentalretardation" onkeydown="textCounter2()" onkeyup="textCounter2()">
              </html:textarea> </td>
          <td  class="label"><TEXTAREA NAME="mohan" COLS=30 ROWS=4></TEXTAREA></td>
      </tr>


        </table><br>



  </html:form>
        <form action="">
             <TABLE align="center">
      <tr>
         <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
           <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button"/></td>
        <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
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