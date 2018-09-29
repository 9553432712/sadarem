<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DecisionForPWDPension</title>


<script language="javascript" >
function decisiontaking(desitionTaking){
                
                 if( desitionTaking != null && desitionTaking != ""){
                     if(desitionTaking == "newPWD"){
                        document.forms[0].action="TerritorySelect.do";
                        document.forms[0].submit();
                     }else if(desitionTaking == "existingPWD"){
                        document.forms[0].action="PensionCodeForwdAction.do";
                        document.forms[0].submit();
                     }

                 }
             }

         </script>
    </head>
    <body>

    <form>
    <br><br><br><br>
    <table  align="center" cellspacing="1" cellpadding="8" class="innerTable">

    <tr><td colspan="2" align="center" class="heading">Select Pensioner/Non-Pensioner</td></tr>

    <tr>
    <td align = "center" class="label"><input type="radio" name="newPWD" value="newPWD" onclick="decisiontaking(this.value)" />&nbsp;&nbsp;Non-Pensioner</td>
    <td align = "center" class="label"><input type="radio"  name="newPWD" value="existingPWD" onclick="decisiontaking(this.value)"/>&nbsp;&nbsp;Pensioner</td>
    </tr>
    <tr>
    <td colspan="2">- - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </td>
    </tr>
    <tr><td colspan="2" class="Note">Note</td></tr>
    <tr><td class="labelBlue" colspan="2">
    <li>&nbsp;&nbsp;If Person have Pension Number Please Select Pensioner Radio Button</td>
    </tr>
    <tr>
    <td class="labelBlue" colspan="2">
    <li>&nbsp;&nbsp;If Person does not have Pension Number Please Select Non-Pensioner Radio Button</td>
    </tr>
    </table>
        </form>
    </body>
</html>
