<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<% String devi = (String) request.getParameter("iqforall");
            String testname = (String) request.getParameter("testname");
%>


<html:html>
    <HEAD>
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
        document.forms[0].action="MentalRetardationUpdateForwdAction.do";
        document.forms[0].submit();
    }


    function textCounter2()
    {
        if (document.forms[0].anyothermentalretardation.value.length > 100) // if too long...trim it!
            document.forms[0].anyothermentalretardation.value = document.forms[0].anyothermentalretardation.value.substring(0,100);
        // otherwise, update 'characters left' counter
    }

</script>

<html:errors/>
<script language="javascript" src="./DisabilityUITG/js/mental.js"></script>
<body>
    <html:form  action="/updatemental.do?updateMentalRetardation=updateMentalRetardation" method="post" styleId="form3" onsubmit="document.getElementById('upmenButton').disabled = true;">

        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="4">11. &nbsp;&nbsp;UPDATE MENTAL RETARDATION</th>
            </tr>
  
            <tr>
                <th align="center" colspan="4">Intelligent Quotient

                  <html:text property="iq" size="15" maxlength="2" readonly="true" value="<%=devi%>" />
                    <html:hidden property="chronologicalage"  value="<%=testname%>" /></th>
            </tr>
      
            <tr>
                <th colspan="8">Need Assessment/Referred/Recommended</th>
            </tr>
            <tr>
                <td colspan="8" >1. Early Intervention Services &nbsp;(For Children Below 3 Years)</td>
            </tr>
            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; i.Speech Therapy</td>
                <td><html:checkbox property="speechtheraphy" value=" EarlyIntervention Speech Therapy" style="width:25px"> </html:checkbox></td>
            </tr>
            <tr>
                <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Behavior Modification</td>
                <td><html:checkbox property="behaviormodification" value="Behavior Modification" style="width:25px"> </html:checkbox></td>
            </tr>
            <tr>
                <td   >2. Sensory Integration/Cognitive Stimulation</td>
                <td ><html:checkbox property="sensoryintegrationcognitive" value="SensoryIntegrationcognitive" style="width:25px"></html:checkbox> </td>
            </tr>
            <tr>
                <td  >3. Speech Therapy/Language Development</td>
                <td> <html:checkbox property="speechtherapy" value="Speech Therapy" style="width:25px" style="width:25px"></html:checkbox> </td>
            </tr>
            <%--  <tr>
                 <td  >4. Language Development</td>
                 <td><html:checkbox property="languagedevelopment" value="Language Development"></html:checkbox></td>
             </tr>--%>

            <tr>
                <td  >4. Physiotherpay</td>
                <td><html:checkbox  property="physiotherapy" value="Physiotherapy" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>
                <td  >5. Occupational Therapy</td>
                <td><html:checkbox  property="occupationaltherapy" value="OccupationalTherapy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td  >6. Psycotherapy/Behaviour Modification</td>
                <td><html:checkbox property="psycotherapybehaviour" value="Psycotherapy/Behaviour Modification" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>
                <td  >7. Cognitive Behaviour Therapy</td>
                <td><html:checkbox property="cognitivebehaviourtherapy" value="CognitiveBehaviourTherapy" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td  >8. Parent/Family Intervention</td>
                <td><html:checkbox property="parientfamilyintervention" value="ParientFamilyintervention" style="width:25px"></html:checkbox></td>
            </tr>

            <tr>
                <td  >9. Legal Guardian (For Persons With MR, CP, Multiple Disabilities And Autism)</td>
                <td> <html:checkbox property="legalguardian" value="Legal Guardian" style="width:25px"> </html:checkbox> </td>
            </tr>
            <tr>
                <td colspan="2" >10.  Assistive & Augmentative Devices For  Mental  Retardation</td>
            </tr>
            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  i.Learning Materials For M.R(Books,CD etc)</td>
                <td><html:checkbox property="learningmaterials" value="Learning Materials For M.R" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ii.Special Software</td>
                <td> <html:checkbox property="specialsoftware" value="Special Software" style="width:25px"></html:checkbox> </td>
            </tr>
            <tr>
                <td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; iii.Toys</td>
                <td> <html:checkbox property="toys" value="Toys" style="width:25px"></html:checkbox></td>
            </tr>
            <tr>
                <td  >11. Any Other Mental Retardation Needs</td>
                <td><html:textarea rows="4" cols="30" property="anyothermentalretardation" onkeydown="textCounter2()" onkeyup="textCounter2()" style="width:400px">

                    </html:textarea> </td>
            </tr>
            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />
                    <html:submit  value="Update" styleClass="button" styleId="upmenButton"/>
                    <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button" /></th>
            </tr>

        </table>

    </html:form>
</body>
</html:html>