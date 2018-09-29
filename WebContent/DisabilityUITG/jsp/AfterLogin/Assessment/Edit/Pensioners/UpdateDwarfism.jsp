<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
    <html:errors/>
    <head>
        <script language="javascript" >
            function goBack()
            {
                document.forms[0].action="PhysicalimpairmentUpdateForwadAction.do?getDisabilityPercentages=getDisabilityPercentages";
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
                else{
                    thisform.setAttribute('submitted','true');
                    document.getElementById('toatlDisButton').disabled = true;
                }
            }

        </script>
    </head>


    <body>
        <html:form action="/updatedwarfism?updateDwarfismDetails=updateDwarfismDetails" styleId="form5"
                   onsubmit="return avoidDuplicateForm(this)">


            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                       value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
                <% if (request.getAttribute("msg") != null) {%>
                <tr><font color="red"><%=request.getAttribute("msg")%></font></tr><% }%>
            <tr>
                <th colspan=4 align="center" class="heading">Update Dwarfism/Short Height</th>
            </tr>
     
            <tr>
                <th  align="CENTER" >Age of the Person</th>
                <th align="CENTER" >Total Height(in inches)</th>
            </tr>
            <tr>
                <td align="CENTER" >years <html:text property="ageyears" size="5" onkeyup="isNumber120(this)"/>
                    Months <html:text property="agemonths" size="5" onkeyup="isNumber11(this)"/></td>
                <td align="CENTER"><br><html:text property="height" size="5"/></td>
            </tr>


      
            <tr>
                <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />
                <html:submit  value="Update" styleId="toatlDisButton"  styleClass="button"/>
                <html:button  property="" value="Reset" onclick="resetbutton()" styleClass="button" /></th>
            </tr>
        </table>

    </html:form>
</body>
</html:html>