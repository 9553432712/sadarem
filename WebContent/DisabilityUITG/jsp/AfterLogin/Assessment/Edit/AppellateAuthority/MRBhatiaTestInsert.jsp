<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>




<html:html>
    <HEAD>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    

</HEAD>
<script language="javascript" >
    function goBack()
    {
        document.forms[0].action="MentalRetardationForwdAction.do";
        document.forms[0].submit();
    }
</script>
<script>
    function validate_required(field,alerttxt)
    {
        with (field)
        {
            if (value==null||value=="")
            {
                alert(alerttxt);
                return false
            }
            else
            {
                return true
            }
        }
    }
    function validate_form(thisform)
    {
        var flag = true;
        with (thisform)
        {
            if (validate_required(bbpti_IQ,"Enter IQ")==false)
            {
                bbpti_IQ.focus();
                flag = false;
                return flag
            }
        }
        return flag;
    }
            
    function removeSpaces(string)
    {
        return string.split(' ').join('');
    }

    function validateForm(thisForm){
        var flag = true;
        flag = validate_form(thisForm);
        if(flag){
            if (thisForm.getAttribute('submitted')){
                flag = false;
                return flag;
            }else{
                thisForm.setAttribute('submitted','true');

                document.getElementById('hearButton').disabled = true;
            }
        }
        return flag;

    }
</script>

<script language="javascript" src="./DisabilityUITG/js/MRbhatia.js"></script>

<body>
    <html:errors />
    <html:form action="insertBhatiaTestDetails.do?insertBhatiaTestDetails=insertBhatiaTestDetails" onsubmit="return validateForm(this)" >
        <table  align="center" cellspacing="1" cellpadding="0"  class="inputform" width="60%">
            <tr>
                <th colspan="4">7.&nbsp;&nbsp;Add Bhatia's Battery of Intelligence Tests</th>
            </tr>
       
            <tr>
                <th >Tests</th>
                <th >Score</th>
            </tr>
            <tr>
                <td ><br>I  . KOHS BLOCK DESIGN TEST</td>
                <td ><html:text property="bbpti_KOHS_Block_Design_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td ><br>II  . PASS ALONG TEST</td>
                <td ><html:text property="bbpti_Passalong_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td ><br>III  . PATTERN DRAWING TEST</td>
                <td><html:text property="bbpti_PatternDrawing_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td ><br>IV  . IMMEDIATE MEMORY TEST</td>
                <td ><html:text property="bbpti_ImmediateMemory_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td ><br> V  . PICTURE CONSTRUCTION TEST</td>
                <td ><html:text property="bbpti_PictureConstruction_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>

            
                <tr>
                    <td align="center" colspan="4" width="50%">&nbsp;&nbsp;IQ<font color="red">*</font> &nbsp;&nbsp;&nbsp;<html:text property="bbpti_IQ"  onblur="this.value=removeSpaces(this.value);"/></td>
                </tr>
                <tr>
                    <th colspan="4"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
                   <html:submit  value="Add" styleId="hearButton" styleClass="button"/>
                 <html:button property="" value="Reset" onclick="goReset()" styleClass="button"/></th>

        </table>


        </html:form>
</body>

</html:html>


