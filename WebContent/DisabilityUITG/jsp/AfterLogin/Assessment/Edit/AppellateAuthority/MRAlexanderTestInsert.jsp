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
            return flag
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
                    document.getElementById('hearButton').disabled = true;
                }
            }
            return flag;

        }

    </script>

    <script language="javascript" src="./DisabilityUITG/js/MRAlexander.js"></script>
    <body>
        <html:errors />
        <html:form action="insertMRAlexanderTestDetails.do?insertMRAlexanderTestDetails=insertMRAlexanderTestDetails" onsubmit="return validateForm(this)"  >

            <table  align="center" cellspacing="1" cellpadding="0"  class="inputform" width="90%">
                <tr>
                    <th colspan="10">6.&nbsp;&nbsp;Add Alexander Pass Along Test (P.A.T)</th>
                </tr>

                <tr>
                    <th ><br>Design No.</th>
                    <th><br>Allotted time (Seconds)</th>
                    <th ><br>Actual time taken by testee to solve the problem</th>
                    <th ><br>Score awarded </th>
                    <th ><br>Remarks</th>
                </tr>
                <tr>
                    <td ><br>1</td>

                    <td ><html:text property="trial1sec" value="120(2 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_One"  onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_One" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_One" /></td>


                </tr>
                <tr>
                    <td ><br>2</td>

                    <td ><html:text property="trial2sec" value="120(2 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Two" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Two" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Two" /></td>


                </tr>
                <tr>
                    <td ><br>3</td>

                    <td ><html:text property="trial3sec" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Three"  onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Three" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Three" /></td>


                </tr>
                <tr>
                    <td ><br>4</td>

                    <td ><html:text property="trial3sec" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Four" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Four" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Four" /></td>


                </tr>
                <tr>
                    <td ><br>5</td>

                    <td ><html:text property="trial3sec" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Five" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Five" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Five" /></td>


                </tr>
                <tr>
                    <td ><br>6</td>

                    <td ><html:text property="trial3sec" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Six" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Six" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Six" /></td>


                </tr>

                <tr>
                    <td ><br>7</td>

                    <td ><html:text property="trial3sec" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Seven" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Seven" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Seven" /></td>


                </tr>
                <tr>
                    <td ><br>8</td>

                    <td ><html:text property="trial3sec" value="240(4 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Eight" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber240(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Eight" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Eight" /></td>


                </tr>
                <tr>
                    <td ><br>9</td>

                    <td ><html:text property="trial3sec" value="300(5 min)" readonly="true"/></td>
                    <td >Seconds: <html:text property="pat_Second_Nine" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber300(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Nine" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_Nine" /></td>
                </tr>
               <%-- </table>

            <table  align="center" cellspacing="1" cellpadding="0"  class="inputform" width="90%">--%>
                <tr>
                    <td colspan="8"><br>Mental Age &nbsp;&nbsp;&nbsp;
                        Years:<font color="red">*</font><html:text property="pat_Year" onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber120(this)" maxlength="3"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Months:<font color="red">*</font><html:text property="pat_Month"  onblur="this.value=removeSpaces(this.value);" onkeyup="isNumber11(this)" maxlength="2"/></td>

                </tr>   
          
                <tr>
                    <th colspan="10"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
                        <html:submit  value="Add" styleId="hearButton" styleClass="button"/>
                        <html:reset   value="Reset"  styleClass="button"/></th>

            </table>


        </html:form>
    </body>

</html:html>


