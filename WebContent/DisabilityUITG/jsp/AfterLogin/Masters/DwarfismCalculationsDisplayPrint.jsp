<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>

      <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">

    <body  onload="window.print()"><form>

    <logic:present name="dwarfismBean" scope="request">
      
    <table  width="100%" cellspacing="3" cellpadding="4">
         <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

                <tr>
                <td colspan=4 align="center" ><font size="3"><b>Dwarfism/Short Height</b></font></td></tr>


<tr>
                    <td ><b>Age of the Person</b></td>
                    <td><bean:write name="dwarfismBean" property="ageyears"/> Years<logic:notEqual name="dwarfismBean" property="agemonths" value="0"> : <bean:write name="dwarfismBean" property="agemonths"/> Months </logic:notEqual> </td> </tr>
   <tr>
                    <td ><b>Total Height (in inches)</b></td>
                    <td> <bean:write name="dwarfismBean" property="height"/> </td></tr>
 <tr>
                    <td ><b>Average Height </b></td>
                    <td> <bean:write name="dwarfismBean" property="heighthavetobe"/> </td></tr>
  <tr>
                    <td ><b> Height Loss (Average Height - Total Height)</b> </td>
                    <td><bean:write name="dwarfismBean" property="heighthavetobe"/> - <bean:write name="dwarfismBean" property="height"/>
       = <bean:write name="dwarfismBean" property="heightloss"/> </td></tr>
  <tr>
                    <td ><b> Dwarfism (4 * Height Loss) </b> </td>
                    <td>4 * <bean:write name="dwarfismBean" property="heightloss"/> =  <bean:write name="dwarfismBean" property="dwarfism"/>% </td></tr>
    </table>
     </logic:present>

  </form>
   </body>
</html:html> 