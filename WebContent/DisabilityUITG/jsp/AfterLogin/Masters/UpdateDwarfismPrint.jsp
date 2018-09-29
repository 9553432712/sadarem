<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
    <html:errors/>
<script language="javascript" >
    function goBack()
{
            document.forms[0].action="PhysicalimpairmentUpdateForwadPrintAction.do?getDisabilityPercentages=getDisabilityPercentages";
            document.forms[0].submit();
}
function resetbutton(form)
{

 document.form5.ageyears.value="0";
 document.form5.agemonths.value="0";
 document.form5.height.value="0";

}

function isNumber120(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>120){
alert('Value must be between 0 and 120!')
field.value ='';
field.focus();
}
}

function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        thisform.setAttribute('submitted','true');
    }

</script>
 <script>
	function disableForm(theform) {
		if (document.all || document.getElementById) {
			for (i = 0; i < theform.length; i++) {
			var formElement = theform.elements[i];
				if (true) {
					formElement.disabled = true;
				}
			}
		}
	}
</script>
<style type="text/css">

/* GRID Starts */

.gridbg1 {
	background-color:#f4f4f4; text-align:left;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px; padding:4px;
}
.gridbg2 {
	background-color:#eae9e9; text-align:center;  border-bottom:1px #b0b0b0 solid;  border-left:1px #b0b0b0 solid; vertical-align:middle; font-size:10px;  font-weight:400; height:20px; padding-left:5px; padding-right:5px;
}
.gridhdbg {
	background-color:#b1b0b0; text-align:center;  border-bottom:1px #000 solid;  border-left:1px #000 solid; vertical-align:middle; font-size:10px;  font-weight:bold; height:20px;
}
.gridtb {
	border-right:1px #000 solid; border-top:1px #000 solid;
}

/* GRID Ends */
</style>

<body onLoad="disableForm(dwa);">
    <html:form action="/getdwarfismPrint.do" styleId="dwa">
<table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>Sadarem Code : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>

<table  align="center" cellspacing="1" cellpadding="1" class="innerTable" width="60%">
    <input type="hidden" name="<%= Constants.TOKEN_KEY %>"
            value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" >
<% if(request.getAttribute("msg")!=null) { %>
   <tr><font color="red"><%=request.getAttribute("msg")%></font></tr><% } %>
<tr>
<td colspan=4 align="center" class="gridhdbg" style="border:1px #000 solid; font-size:14px;"> Dwarfism/Short Height</td>
</tr>
</table>
   <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="60%">
        <tr>
            <td align="CENTER" class="gridhdbg" colspan="2" style="font-size:14px;"><b>Old Values</b></td>

   </tr>
   <tr>
       <td align="CENTER" class="gridhdbg">Age of the Person</td>
       <td align="CENTER" class="gridhdbg">Total Height(in inches)</td>
   </tr>
            <tr>
              <td align="CENTER" class="gridbg1">years <html:text property="ageyears" size="5" onkeyup="isNumber120(this)"/>
                    Months <html:text property="agemonths" size="5" onkeyup="isNumber11(this)"/></td>
                <td align="CENTER" class="gridbg1"><br><html:text property="height" size="5"/></td>
            </tr>


</table><br>
 <table  align="center" cellspacing="0" cellpadding="0" border="0" class="gridtb" width="60%">
     <tr>
         <td align="CENTER" class="gridhdbg" colspan="2" style="font-size:14px;"><b>New Values</b></td>

   </tr>
   <tr>
       <td align="CENTER" class="gridhdbg">Age of the Person</td>
       <td align="CENTER" class="gridhdbg">Total Height(in inches)</td>
   </tr>
            <tr>
                <td align="CENTER" class="gridbg1">years <input type="text" name="mohan" size="5" onKeyUp="isNumber120(this)"/>
                    Months <input type="text" name="mohan" size="5" onKeyUp="isNumber11(this)"/></td>
              <td align="CENTER" class="gridbg1"><input type="text" name="mohan" size="5"/></td>
            </tr>


</table>



</html:form>
<form action="">
    <table align="center">
<tr>
     <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
    <td><html:button property=""  value="Next" onclick="goURL()" styleClass="button" /></td>
    <td><html:button  property="" value="Print" onclick="window.print()" styleClass="button" /></td>
 </tr>
</table>
</form>
    </body>
    <script>

                function goURL()
{
            document.forms[0].action="partcPrint.do?selectPartc=selectPartc";
            document.forms[0].submit();
}

         </script>
</html:html>