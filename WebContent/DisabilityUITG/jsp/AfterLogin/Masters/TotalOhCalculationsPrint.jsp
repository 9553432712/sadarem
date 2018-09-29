<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<html:html>
    <head>


    </head>

    <body onload="window.print()">

  <form>
    <logic:notEmpty name="totalOhBean" scope="request">

        <%boolean ohCalcValue=false;%>

        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
            <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

        <tr class="tbl_bg2_content_hdr">
            <th align="center" colspan="2"><font size="4">&nbsp;&nbsp;Total Locomotor Calculation</font></th>
        </tr>



     <logic:notEqual name="totalOhBean" property="upperextrimity" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>UpperExtremity</td>
                   <td align="left" > <bean:write property="upperextrimity" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

      <logic:notEqual name="totalOhBean" property="lowererextrimity" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>LowererExtrimity</td>
                   <td align="left" > <bean:write property="lowererextrimity" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>
 <logic:notEqual name="totalOhBean" property="amputation" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Amputation</td>
                   <td align="left" > <bean:write property="amputation" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>
            <logic:notEqual name="totalOhBean" property="transverse" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Transverse</td>
                   <td align="left" > <bean:write property="transverse" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

             <logic:notEqual name="totalOhBean" property="trunk" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Trunk</td>
                   <td align="left" > <bean:write property="trunk" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

             <logic:notEqual name="totalOhBean" property="evaluation" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Evaluation</td>
                   <td align="left" > <bean:write property="evaluation" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

           <logic:notEqual name="totalOhBean" property="cardiopulmonary" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Cardiopulmonary</td>
                   <td align="left" > <bean:write property="cardiopulmonary" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>

            <logic:notEqual name="totalOhBean" property="dwarfism" value="0"><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Dwarfism</td>
                   <td align="left" > <bean:write property="dwarfism" name="totalOhBean" /></td>
                </tr>
            <% }ohCalcValue= false; %>


         <logic:notEqual name="totalOhBean" property="tolalformula" value=""><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Total Calculation</td>
                   <td align="left" > <pre style="font-family:verdana"><bean:write property="tolalformula" name="totalOhBean" /></pre></td>
                </tr>
            <% }ohCalcValue= false; %>



       <logic:notEqual name="totalOhBean" property="ohfinalvalue" value=""><%  ohCalcValue = true;%></logic:notEqual>
            <%  if (ohCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Total Locomotor Disability</td>
                   <td align="left" > <b><bean:write property="ohfinalvalue" name="totalOhBean" /></b></td>
                </tr>
            <% }ohCalcValue= false; %>
        </logic:notEmpty>

    </TABLE>
     <BR><BR><BR>

    </form>

    </body>
</html:html>