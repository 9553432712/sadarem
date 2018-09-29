<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
    <head><br><br><br>

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
    <logic:notEmpty name="totalOhBean" scope="request">

        <%boolean ohCalcValue=false;%>

       <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="70%">
           <tr>
            <th align="center" colspan="2" class="heading">Total Locomotor Calculation</th>
        </tr>
            <tr>
                <td colspan="3"  align="right" class="label">ID No: &nbsp;<font color="blue" ><%=personcode%></font></td>
            </tr>
        <tr>
            <td colspan="3"  align="right" class="label">Name: &nbsp; <font color="blue"><%=name%></font></td>
        </tr>

       </table>
        <table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="70%">



     <logic:notEqual name="totalOhBean" property="upperextrimity" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">UpperExtremity</td>
                   <td align="left" > <bean:write property="upperextrimity" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

      <logic:notEqual name="totalOhBean" property="lowererextrimity" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">LowererExtrimity</td>
                   <td align="left" > <bean:write property="lowererextrimity" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>
 <logic:notEqual name="totalOhBean" property="amputation" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Amputation</td>
                   <td align="left" > <bean:write property="amputation" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>
            <logic:notEqual name="totalOhBean" property="transverse" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Transverse</td>
                   <td align="left" > <bean:write property="transverse" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

             <logic:notEqual name="totalOhBean" property="trunk" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Trunk</td>
                   <td align="left" > <bean:write property="trunk" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

             <logic:notEqual name="totalOhBean" property="evaluation" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Evaluation</td>
                   <td align="left" > <bean:write property="evaluation" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

           <logic:notEqual name="totalOhBean" property="cardiopulmonary" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Cardiopulmonary</td>
                   <td align="left" > <bean:write property="cardiopulmonary" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

            <logic:notEqual name="totalOhBean" property="dwarfism" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Dwarfism</td>
                   <td align="left" > <bean:write property="dwarfism" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>


         <logic:notEqual name="totalOhBean" property="tolalformula" value=""><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Total Calculation</td>
                   <td align="left" > <pre style="font-family:verdana"><bean:write property="tolalformula" name="totalOhBean" /></pre></td>
                </tr>
            <% }ohCalcValue= false; %>



       <logic:notEqual name="totalOhBean" property="ohfinalvalue" value=""><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr>
                   <td class="label">Total Locomotor Disability</td>
                   <td> <bean:write property="ohfinalvalue" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>
        </logic:notEmpty>

    </table>
     <BR><BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=20&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
    </form>

    </body>
</html:html>