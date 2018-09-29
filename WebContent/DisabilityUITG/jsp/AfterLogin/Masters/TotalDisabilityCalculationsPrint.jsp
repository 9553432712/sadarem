<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
    <head>

  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    </head>

    <body onload="window.print()">

  <form>
    <logic:notEmpty name="totalDisabilityBean" scope="request">

        <%boolean totalCalcValue=false;%>

        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
            <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>
        <tr class="tbl_bg2_content_hdr">
            <th align="center" colspan="2"><font size="4">&nbsp;&nbsp;Total Disability Calculation</font></th>
        </tr>



     <logic:notEqual name="totalDisabilityBean" property="totalphysical" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Locomotor Disability</td>
                   <td align="left" > <bean:write property="totalphysical" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>

      <logic:notEqual name="totalDisabilityBean" property="hearingimpairment" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Hearing Impairment Disability</td>
                   <td align="left" > <bean:write property="hearingimpairment" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>
 <logic:notEqual name="totalDisabilityBean" property="visualimpairment" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Visual Impairment Disability</td>
                   <td align="left" > <bean:write property="visualimpairment" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>
            <logic:notEqual name="totalDisabilityBean" property="mentalretardation" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Mental Retardation Disability</td>
                   <td align="left" > <bean:write property="mentalretardation" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>

             <logic:notEqual name="totalDisabilityBean" property="mentalillness" value="0"><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Mental Illness Disability</td>
                   <td align="left" > <bean:write property="mentalillness" name="totalDisabilityBean" /></td>
                </tr>
            <% }totalCalcValue= false; %>


         <logic:notEqual name="totalDisabilityBean" property="tolalformula" value=""><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Total Calculation</td>
                   <td align="left" > <pre style="font-family:verdana"><bean:write property="tolalformula" name="totalDisabilityBean" /></pre></td>
                </tr>
            <% }totalCalcValue= false; %>



       <logic:notEqual name="totalDisabilityBean" property="ohfinalvalue" value=""><%  totalCalcValue = true;%></logic:notEqual>
            <%  if (totalCalcValue) {  %>
               <tr class="tbl_bg2_content">
                   <td>Total Disability Percentage</td>
                   <td align="left" > <b><bean:write property="ohfinalvalue" name="totalDisabilityBean" /></b></td>
                </tr>
            <% }totalCalcValue= false; %>
        </logic:notEmpty>

    </TABLE>
     <BR><BR><BR>

    </form>

    </body>
</html:html>