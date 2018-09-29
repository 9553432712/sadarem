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
    
    <body onload="document.eligibleReportForm.personcode.focus()"><br><br><br><br>
        <html:form action="CertificateForPersoncode.do" styleId="eligibleReportForm"  onsubmit="return validate_form(this)">
    
  
    
    
    
   <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="50%">
        <tr align="center"><td class="heading">Reports -------> Print Certificate ------> Eligible Person</td></tr>
    </table>
    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="50%">
   <tr>
<input type="hidden" name="print" value="certificate" />
<input type="hidden" name="flag" value="true"/>
<input type="hidden" name="displaybackbuttonforAR" value="hide"/>
<td align="center" class="label"> Enter Eligible SADAREM ID </td>
    <td><input type="text" name="personcode" maxlength="17" size="17"  /></td>
   </tr>
   
 </table><br>
    
    <center> <html:submit  value="Submit" styleClass="button"/></center>
    
   
</html:form> 



    </body>
    
</html:html>
