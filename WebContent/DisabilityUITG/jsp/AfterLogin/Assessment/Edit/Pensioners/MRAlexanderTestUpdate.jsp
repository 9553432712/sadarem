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
            document.forms[0].action="MentalRetardationUpdateForwdAction.do";
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
                    document.getElementById('subDisablButton').disabled = true;
                }
            }
            return flag;

        }

    </script>

    <script language="javascript" src="./DisabilityUITG/js/MRAlexander.js"></script>
    <body>
        <html:errors />
        <html:form action="updateMRAlexanderTestDetails.do?updateMRAlexanderTestDetails=updateMRAlexanderTestDetails"
                   onsubmit="return validateForm(this)"  >

            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8" align="center" class="heading">6.&nbsp;&nbsp;Update Alexander Pass Along Test (P.A.T)</th>
                </tr>
           
                <tr>
                   <th><br>Design No.</th>
                    <th><br>Allotted time (Seconds)</th>
                    <th><br>Actual time taken by testee to solve the problem</th>
                    <th><br>Score awarded </th>
                    <th><br>Remarks</th>
                </tr>
                <tr>
                    <td ><br>1</td>

                    <td ><html:text property="trial1sec" size="9" value="120(2 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_One" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_One" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td ><html:text property="pat_Remarks_One" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>2</td>

                    <td ><html:text property="trial2sec" size="9" value="120(2 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Two" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Two" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Two" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>3</td>

                    <td ><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Three" size="9"  onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Three" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Three" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>4</td>

                    <td ><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Four" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Four" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Four" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>5</td>

                    <td ><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Five" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Five" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Five" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>6</td>

                    <td ><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Six" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Six" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Six" size="9" /></td>


                </tr>

                <tr>
                    <td ><br>7</td>
                    <td ><html:text property="trial3sec" size="9" value="180(3 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Seven" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber180(this)" maxlength="3"/></td>
                    <td ><html:text property="pat_SA_Seven" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Seven" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>8</td>

                    <td ><html:text property="trial3sec" size="9" value="240(4 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Eight" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber240(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Eight" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Eight" size="9" /></td>


                </tr>
                <tr>
                    <td ><br>9</td>

                    <td ><html:text property="trial3sec" size="9" value="300(5 min)" readonly="true"/></td>
                    <td >Seconds <html:text property="pat_Second_Nine" size="9" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber300(this)" maxlength="3"/></td>
                    <td > <html:text property="pat_SA_Nine" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                    <td > <html:text property="pat_Remarks_Nine" size="9" /></td>


                </tr>
                <%-- <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">--%>
                <tr>
                    <td ><br>Mental Age</td>
                    <td  align="center">Years <font color="red">*</font><html:text property="pat_Year" size="14" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber120(this)" maxlength="3"/></td>
                    <td  colspan="3">Months<font color="red">*</font> <html:text property="pat_Month" size="14" onblur="this.value=removeSpaces(this.value);"
                               onkeyup="isNumber11(this)" maxlength="2"/></td>


                </tr>
        
                <tr >
                    <th colspan="8"><html:button property=""  value="Back" onclick="goBack()" styleClass="button" />


                    <html:submit  value="Update" styleId="subDisablButton"  styleClass="button"/>


                    <html:button property="" value="Reset" onclick="goReset()" styleClass="button" /></th>

                      </table>



        </html:form>
    </body>

</html:html>


