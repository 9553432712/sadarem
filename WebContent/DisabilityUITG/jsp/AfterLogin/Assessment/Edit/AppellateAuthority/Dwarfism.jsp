<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib  uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib  uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
    <html:errors/>
    <script language="javascript" >
        function goBack()
        {
        document.forms[0].action="PhysicalimpairmentForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
        document.forms[0].submit();
        }
        function resetbutton(form)
{

 document.form5.ageyears.value="";
 document.form5.agemonths.value="";
 document.form5.height.value="";

}

function isNumber11(field) {
var re = /^[0-9]*$/;
if (!re.test(field.value)) {
alert('Value must be an integer number!')
field.value = field.value.replace(/D/g,"");
field.value ='';
field.focus();
}
if(Number(field.value)>11){
alert('Value must be between 0 and 11!')
field.value ='';
field.focus();
}
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
        else{
        thisform.setAttribute('submitted','true');
                document.getElementById('dwarfismButton').disabled = true;
        }
    }
    </script>
    
    <body>
        <html:form action="/dwarfism.do" styleId="form5"
                   onsubmit="return avoidDuplicateForm(this)">

           <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr>
                <th colspan="3" >Add Dwarfism/Short Height</th>
                </tr>


                <tr>
                    <th>Age of the Person</th>
                    <th colspan="2">Total Height (in inches)</th>
                </tr>

                <tr>
                    <td>Years  <html:text property="ageyears"  onkeyup="isNumber120(this)"/></td>
            
             <td> Months  <html:text property="agemonths" onkeyup="isNumber11(this)"/></td>
              <td ><html:text property="height" /></td>
              </tr>

          
                <tr>
                    <th colspan="3"><html:button property=""  value="Back" onclick="goBack()"  styleClass="button"/>
                   <html:submit  value="Add" styleId="dwarfismButton" styleClass="button"/>
                    <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button"/></th>
               </tr>
            </table>

        </html:form>
    </body>
</html:html>