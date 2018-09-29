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


    function goReset()
    {

        document.mentalform.misicinformationraw.value="";
        document.mentalform.misicinformationtq.value="";
        document.mentalform.misicpcrawscore.value="";
        document.mentalform.misicpctq.value="";
        document.mentalform.misiccomprehensionrawscore.value="";
        document.mentalform.misiccomprehensiontq.value="";
        document.mentalform.misicbdrawscore.value="";
        document.mentalform.misicbdtq.value="";
        document.mentalform.misicarithmeticrawscore.value="";
        document.mentalform.misicarithmetictq.value="";
        document.mentalform.misicoarawscore.value="";
        document.mentalform.misicoatq.value="";
        document.mentalform.misicsimilaritiesrawscore.value="";
        document.mentalform.misicsimilaritiestq.value="";
        document.mentalform.misiccodingrawscore.value="";
        document.mentalform.misiccodingtq.value="";
        document.mentalform.misicvocabularyrawscore.value="";
        document.mentalform.misicvocabularytq.value="";
        document.mentalform.misicmazesrawscore.value="";
        document.mentalform.misicmazestq.value="";
        document.mentalform.misicdigitspanrawscore.value="";
        document.mentalform.misicdigitspantq.value="";

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


<body onload="disableForm(mentalform);">
    <html:errors/>
    <html:form action="MRMalinsdetailsselectPrint.do" method="post" styleId="mentalform">
        <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>


        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <td align="center" class="heading">4.&nbsp;&nbsp;Malins Intelligence Scale for Indian Children (M.I.S.I.C)</td>
            </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
            <tr>
                <td class="labelBlue">Verbal Test</td>
                <td class="labelBlue">Old Raw Score</td>
                <td class="labelBlue">New Raw Score</td>
                <td class="labelBlue">Old IQ</td>
                <td class="labelBlue">New IQ</td>
                <td class="labelBlue">Performance Test</td>
                <td class="labelBlue">Old Raw Score</td>
                <td class="labelBlue">New Raw Score</td>
                <td class="labelBlue">Old IQ</td>
                <td class="labelBlue">New IQ</td>

            </tr>
            <tr>
                <td class="label">Information</td>

                <td><html:text property="misicinformationraw" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicinformationtq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Picture Completion</td>

                <td><html:text property="misicpcrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicpctq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label">Comprehension</td>

                <td><html:text property="misiccomprehensionrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misiccomprehensiontq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                    <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Block Design</td>

                <td><html:text property="misicbdrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicbdtq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label">Arithmetic</td>

                <td><html:text property="misicarithmeticrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                 <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicarithmetictq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>

                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Object Assembly</td>

                <td><html:text property="misicoarawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicoatq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
            <tr>
                <td class="label">Similarities</td>

                <td><html:text property="misicsimilaritiesrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicsimilaritiestq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>

                <td><input type="text"  name="mohan"  size="5" maxlength="4" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Coding</td>

                <td><html:text property="misiccodingrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misiccodingtq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
            <tr>
                <td class="label">Vocabulary</td>

                <td><html:text property="misicvocabularyrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                  <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicvocabularytq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>

                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Mazes</td>

                <td><html:text property="misicmazesrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicmazestq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td class="label">Digit span</td>

                <td><html:text property="misicdigitspanrawscore" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                 <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td ><html:text property="misicdigitspantq" size="5"onblur="this.value=removeSpaces(this.value);"/></td>
                <td><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


            </tr>


        </table><br>
    </html:form>
    <form action="">
        <TABLE align="center">
            <tr>
                <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>

        </TABLE>
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


