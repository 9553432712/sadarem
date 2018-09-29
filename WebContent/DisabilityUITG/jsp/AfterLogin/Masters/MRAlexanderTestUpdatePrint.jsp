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
                if (validate_required(pat_Year,"Enter Years")==false)
                {
                    pat_Year.focus();
                    flag = false;
                    return flag
                }
                if (validate_required(pat_Month,"Enter Months")==false)
                {
                    pat_Month.focus();
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

        function isNumber300(field) {
            var re = /^[0-9]*$/;
            if (!re.test(field.value)) {
                alert('Value must be an integer number!')
                field.value = field.value.replace(/D/g,"");
                field.value ='';
                field.focus();
            }
            if(Number(field.value)>300){
                alert('Value must be between 0 and 300!')
                field.value ='';
                field.focus();
            }
        }

        function isNumber240(field) {
            var re = /^[0-9]*$/;
            if (!re.test(field.value)) {
                alert('Value must be an integer number!')
                field.value = field.value.replace(/D/g,"");
                field.value ='';
                field.focus();
            }
            if(Number(field.value)>240){
                alert('Value must be between 0 and 240!')
                field.value ='';
                field.focus();
            }
        }

        function isNumber180(field) {
            var re = /^[0-9]*$/;
            if (!re.test(field.value)) {
                alert('Value must be an integer number!')
                field.value = field.value.replace(/D/g,"");
                field.value ='';
                field.focus();
            }
            if(Number(field.value)>180){
                alert('Value must be between 0 and 180!')
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


    <script language="javascript" src="./DisabilityUITG/js/MRAlexander.js"></script>
    <body onload="disableForm(alex);">
        <html:errors />
        <html:form action="getMRAlexanderTestDetailsPrint.do" styleId="alex">
            <table align="center" cellspacing="0" cellpadding="0" width="45%">
                <tr>
                    <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
                </tr>
            </table><br/>

            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                    <td colspan=4 align="center" class="heading">6.&nbsp;&nbsp;Alexander Pass Along Test (P.A.T)</td>
                </tr>
            </table>


            <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr>
                    <td class="labelBlue"><br>Design No.</td>
                    <td class="labelBlue"><br>Allotted time (Seconds)</td>
                    <td class="labelBlue"><br>Actual time taken by <br/>testee to solve the problem (Old)</td>
                    <td class="labelBlue"><br>Actual time taken by <br/>testee to solve the problem (New)</td>
                    <td class="labelBlue"><br>Score awarded(Old) </td>
                    <td class="labelBlue"><br>Score awarded(New) </td>
                    <td class="labelBlue"><br>Remarks(Old)</td>
                    <td class="labelBlue"><br>Remarks(New)</td>
                </tr>
                <tr>
                    <td class="label"><br>1</td>

                    <td class="label"><html:text property="trial1sec" size="9" value="120(2 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_One" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><html:text property="pat_SA_One" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><html:text property="pat_Remarks_One" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>2</td>

                    <td class="label"><html:text property="trial2sec" size="9" value="120(2 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Two" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Two" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Two" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

                </tr>
                <tr>
                    <td class="label"><br>3</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Three" size="9"  onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Three" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Three" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>4</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Four" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Four" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Four" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>5</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Five" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Five" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Five" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>6</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Six" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Six" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Six" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>

                <tr>
                    <td class="label"><br>7</td>
                    <td class="label"><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Seven" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><html:text property="pat_SA_Seven" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Seven" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>8</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="240(4 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Eight" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber240(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Eight" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Eight" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
                <tr>
                    <td class="label"><br>9</td>

                    <td class="label"><html:text property="trial3sec" size="9" value="300(5 min)" readonly="true"/></td>
                    <td class="label">Seconds <html:text property="pat_Second_Nine" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber300(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_SA_Nine" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label"> <html:text property="pat_Remarks_Nine" size="9" /></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>

                <tr>
                    <td class="labelBlue"><br>Mental Age</td>
                    <td class="label" colspan="2" align="center">Years <html:text property="pat_Year" size="14" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td class="label" colspan="2">Months <html:text property="pat_Month" size="14" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber11(this)" maxlength="2"/></td>
                    <td class="label"><input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>


                </tr>
            </table>
            <br>

        </html:form>
        <form action="">
            <TABLE align="center">
                <tr class="tbl_bg2_content">
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


