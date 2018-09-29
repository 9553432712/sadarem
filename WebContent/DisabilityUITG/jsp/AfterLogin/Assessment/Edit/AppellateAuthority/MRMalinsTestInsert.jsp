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
    function removeSpaces(string)
    {
        return string.split(' ').join('');
    }
    function avoidDuplicateForm(thisform){
        if (thisform.getAttribute('submitted'))
            return false;
        else{
            thisform.setAttribute('submitted','true');
            document.getElementById('hearButton').disabled = true;
            return true;
        }
    }
</script>
<body>
    <html:errors/>
    <html:form action="MRmalinstestaction.do?MRmalinstestdetailsinsert=MRmalinstestdetailsinsert" method="post"  styleId="mentalform"
               onsubmit="return avoidDuplicateForm(this)">
        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="12">4.&nbsp;&nbsp;Add Malins Intelligence Scale for Indian Children (M.I.S.I.C)</th>
            </tr>
       
            <tr>
                <th>Verbal Test</th>
                <th>Raw Score</th>
                <th>IQ</th>
                <th>Performance Test</th>
                <th>Raw Score</th>
                <th>IQ</th>

            </tr>
            <tr>
                <td >Information</td>

                <td><html:text property="misicinformationraw" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><b></b><html:text property="misicinformationtq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td >Picture Completion</td>

                <td><html:text property="misicpcrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicpctq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td >Comprehension</td>

                <td><html:text property="misiccomprehensionrawscore" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misiccomprehensiontq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td >Block Design</td>

                <td><html:text property="misicbdrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicbdtq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td >Arithmetic</td>

                <td><html:text property="misicarithmeticrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicarithmetictq" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td >Object Assembly</td>

                <td><html:text property="misicoarawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td><html:text property="misicoatq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
            <tr>
                <td >Similarities</td>

                <td><html:text property="misicsimilaritiesrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td>  <html:text property="misicsimilaritiestq" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td >Coding</td>

                <td><html:text property="misiccodingrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td> <html:text property="misiccodingtq" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td >Vocabulary</td>

                <td><html:text property="misicvocabularyrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td> <html:text property="misicvocabularytq" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td >Mazes</td>

                <td><html:text property="misicmazesrawscore" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
                <td>  <html:text property="misicmazestq" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <td >Digit span</td>
                <td><html:text property="misicdigitspanrawscore" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                <td colspan="4"><html:text property="misicdigitspantq" size="5"  onblur="this.value=removeSpaces(this.value);"/></td>
            </tr>
            <tr>
                <th colspan="12"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
               <html:submit  value="Add" styleId="hearButton"  styleClass="button"/>
                <html:button property=""   value="Reset" onclick="goReset()" styleClass="button"/></th></tr>

        </table>


    </html:form>
</body>

</html:html>


