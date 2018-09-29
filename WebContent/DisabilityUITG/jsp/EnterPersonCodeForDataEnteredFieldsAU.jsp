<%-- 
    Document   : EnterPersonCodeForDataEnteredFieldsAU
    Created on : Aug 5, 2011, 10:23:38 AM
    Author     : 484898
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript">
function validate_required(field)
{
with (field)
{
if (value==null||value=="")
  {alert("Please Enter SADAREM ID");return false;}
var numericExpression = /^[0-9]+$/;
if(!value.match(numericExpression))
   {alert("SADAREM ID must be Integer");return false;}
if(value.length !=17)
   {alert("SADAREM ID must be exactly of 17 numbers");return false;}


}
}

function validate_form(thisform)
{
with (thisform)
{
if (validate_required(personcode)==false)
  {personcode.focus();return false;}
  if (validate_required(personcode)==false)
  {personcode.focus();return false;}
}
}
</script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    
    <body onload="document.getElementById('personcode').focus()"><br><br><br><br>
        <html:form action="getDataEnteredFieldsAU.do?getDataEnteredFieldsDetails=getDataEnteredFieldsDetails" onsubmit="return validate_form(this)" method="post">

   <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="50%">
        <tr><td colspan="2" class="Note">  <%String  message = (String) request.getAttribute("msg");%>
                        <% if (message != null) {%> <%=message%> <% }%></td>
                </tr>

        <tr align="center"><td class="heading">Enter SADAREM ID For DataEnetered Fields</td></tr>
    </table>
    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="50%">
   <tr>

<td align="center" class="label"> Enter Eligible SADAREM ID </td>
<td><input type="text" id="personcode" name="personcode" maxlength="17" size="17"  /></td>
   </tr>

 </table><br>

    <center> <html:submit  value="Submit" styleClass="button"/></center>


</html:form>



    </body>

</html:html>