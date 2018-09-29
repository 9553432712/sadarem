<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>




<html:html>
    <HEAD>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   

</HEAD>
<script language="javascript" >
    function goBack()
    {
        document.forms[0].action="MentalRetardationForwdActionPrint.do";
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
        return flag
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
            }
        }
        return flag;

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

<script language="javascript" src="./DisabilityUITG/js/MRbhatia.js"></script>

<body onload="disableForm(bhatia);">
    <html:errors />
    <html:form action="getBhatiaTestDetailsPrint.do" styleId="bhatia">

        <table align="right" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <td colspan=4 align="center" class="heading">7.&nbsp;&nbsp;Bhatia's Battery of Intelligence Tests</td>
            </tr>
        </table>



        <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
            <tr>
                <td class="labelBlue">&nbsp;&nbsp;Tests</td>
                <td class="labelBlue">Old Score</td>
                <td class="labelBlue">New Score</td>
            </tr>
            <tr>
                <td class="label"><br> I. KOHS BLOCK DESIGN TEST</td>
                <td><html:text property="bbpti_KOHS_Block_Design_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label"><br> II. PASS ALONG TEST</td>
                <td><html:text property="bbpti_Passalong_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label"><br>III. PATTERN DRAWING TEST</td>
                <td><html:text property="bbpti_PatternDrawing_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label"><br>IV. IMMEDIATE MEMORY TEST</td>
                <td><html:text property="bbpti_ImmediateMemory_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label"><br>V. PICTURE CONSTRUCTION TEST</td>
                <td><html:text property="bbpti_PictureConstruction_Test" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>



        </table>
        <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
            <tr>
                <td align="center" class="labelBlue">Old IQ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <html:text property="bbpti_IQ" size="17" onblur="this.value=removeSpaces(this.value);"/></td>
                <td align="center" class="labelBlue">New IQ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  name="mohan"  maxlength="4" size="12" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
        </table><br>
    </html:form>
    <form action="">
        <div id="non-printable">
            <TABLE align="center">
                <tr>
                    <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                    <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
            </TABLE></div>
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


