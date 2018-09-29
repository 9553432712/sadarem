<%-- 
    Document   : DeletePersonCode
    Created on : Apr 9, 2010, 5:04:06 PM
    Author     : srinivas_p
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
     function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
 </script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="/TerritorySelectForDelete.do?" method="get" onsubmit="return validate_form(this)">
             <br><br><br><br>
          <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%">
                <input type="hidden" name="flag" value="true"/>
                <input type="hidden" name="status" value="finish" />
             <%-- <input type="hidden" name="restPartB" value="true" />--%>
                <input type="hidden" name="delete" value="delete"/>
                 <tr>
                    <td width="50%" align="center" class="heading">Enter PersonCode For Delete</td>
                    </tr>
                    </table>
                 <table  align="center" cellspacing="1" cellpadding="5" class="innerTable1" width="60%">
               <tr><td class="columnHeight20"></td></tr>
               <tr><td class="label" align="center">Personcode</td>
                    <td width="50%" align="left">
                   <input type="text"  maxlength="17" size="17" name="personcode" onkeypress="return isNumberKey(event)">
               </td>
                </tr>
                <tr><td class="columnHeight20"></td></tr>
                <tr><td align="center" colspan="2">
                        <input type="submit" value="Submit" class="button"/>&nbsp;&nbsp;

                    <input type="reset" value="Reset" class="button"/>
                </td></tr>
                <tr><td class="columnHeight20"></td></tr>
            </table> 
        </html:form>
    </body>
</html>
