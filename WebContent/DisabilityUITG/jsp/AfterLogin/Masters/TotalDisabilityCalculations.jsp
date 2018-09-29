<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
    <head>
 <br><br><br><br>
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
    <logic:notEmpty name="totalDisabilityBean" scope="request">

        <%boolean totalCalcValue=false;%>

        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="75%">

            <tr><td colspan="3"  align="right" class="label">ID No.&nbsp;<font color="blue"><%=personcode%></font></td>
            </tr>
            <tr><td colspan="3"  align="right" class="label">Name.&nbsp;<font color="blue"><%=name%></font></td>
        </tr>
        <tr>
            <th align="center" colspan="2" class="heading">Total Disability Calculation</th>
        </tr>
        </table>

        <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="75%">





     <logic:notEqual name="totalDisabilityBean" property="totalphysical" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Locomotor Disability</td>
                   <td class="label"> <bean:write property="totalphysical" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>

      <logic:notEqual name="totalDisabilityBean" property="hearingimpairment" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Hearing Impairment Disability</td>
                   <td class="label" > <bean:write property="hearingimpairment" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>
 <logic:notEqual name="totalDisabilityBean" property="visualimpairment" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Visual Impairment Disability</td>
                   <td class="label"> <bean:write property="visualimpairment" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>
            <logic:notEqual name="totalDisabilityBean" property="mentalretardation" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Mental Retardation Disability</td>
                   <td class="label"> <bean:write property="mentalretardation" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>

             <logic:notEqual name="totalDisabilityBean" property="mentalillness" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Mental Illness Disability</td>
                   <td align="left" > <bean:write property="mentalillness" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>


         <logic:notEqual name="totalDisabilityBean" property="tolalformula" value=""><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Total Calculation</td>
                   <td class="label"> <pre style="font-family:verdana"><bean:write property="tolalformula" name="totalDisabilityBean" /></pre></td>
                </tr>
            <% }totalCalcValue= false; %>



       <logic:notEqual name="totalDisabilityBean" property="ohfinalvalue" value=""><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr>
                   <td class="labelBlue">Total Disability Percentage</td>
                   <td class="label"> <bean:write property="ohfinalvalue" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>
        </logic:notEmpty>

    </table>
     <BR><BR>
     <table align="center">
            <tr>
           <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>  </tr>

          <tr><a href="showCalc.do?typeofDisabilityFlag=21&flagPrint=print" target="_blank">
                                Print <img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
    </form>

    </body>
</html:html>