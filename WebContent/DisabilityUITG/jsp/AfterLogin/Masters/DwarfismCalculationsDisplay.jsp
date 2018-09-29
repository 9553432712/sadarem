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
    <logic:present name="dwarfismBean" scope="request">
    <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="85%">
        <tr>
                <td colspan=4 align="center" class="heading">Dwarfism/Short Height</td>
        </tr>


<tr>
<tr><td colspan="3"  align="right" class="label">ID No: &nbsp;<font color="blue"><%=personcode%></font></td>
</tr>
<tr><td colspan="3"  align="right" class="label">Name: &nbsp;<font color="blue"><%=name%></font></td></tr>


    </table>
<table  align="center" cellspacing="1" cellpadding="5" border="1" class="innerTable1" width="85%">
                    <td class="label">Age of the Person</td>
                    <td class="label"><bean:write name="dwarfismBean" property="ageyears"/> Years<logic:notEqual name="dwarfismBean" property="agemonths" value="0"> : <bean:write name="dwarfismBean" property="agemonths"/> Months </logic:notEqual> </td> </tr>
   <tr>
                    <td class="label">Total Height (in inches)</td>
                    <td class="label"> <bean:write name="dwarfismBean" property="height"/> </td></tr>
 <tr>
                    <td class="label">Average Height </td>
                    <td class="label"> <bean:write name="dwarfismBean" property="heighthavetobe"/> </td></tr>
  <tr>
                    <td class="label"> Height Loss (Average Height - Total Height) </td>
                    <td class="label"><bean:write name="dwarfismBean" property="heighthavetobe"/> - <bean:write name="dwarfismBean" property="height"/>
       = <bean:write name="dwarfismBean" property="heightloss"/> </td></tr>
  <tr>
                    <td class="label"> Dwarfism (4 * Height Loss)  </td>
                    <td class="label">4 * <bean:write name="dwarfismBean" property="heightloss"/> =  <bean:write name="dwarfismBean" property="dwarfism"/>% </td></tr>
    </table>
     </logic:present>
     <BR><BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()"  styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=8&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
    </form>

   </body>
</html:html> 