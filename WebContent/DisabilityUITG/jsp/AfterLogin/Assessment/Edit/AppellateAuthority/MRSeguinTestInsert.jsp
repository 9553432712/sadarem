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
        document.mentalform.sfbtrialone.value="";
        document.mentalform.sfbtrialtwo.value="";
        document.mentalform.sfbtrialthree.value="";
        document.mentalform.sfbyear.value="";
        document.mentalform.sfbmonth.value="";
    }
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
            if (validate_required(sfbyear,"Enter year")==false)
            {
                sfbyear.focus();
                flag = false;
                return flag
            }
            if (validate_required(sfbmonth,"Enter month")==false)
            {
                sfbmonth.focus();
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

                document.getElementById('hearButton').disabled = true;
            }
        }
        return flag;

    }
</script>
<body>
    <html:errors/>
    <html:form action="MRsfbtestaction.do?MRseguintestdetailsinsert=MRseguintestdetailsinsert" method="post"  onsubmit="return validateForm(this)" styleId="mentalform">
   <table  align="center" cellspacing="1" cellpadding="0"  class="inputform" width="60%">
            <tr>
                <th  colspan="6">5.&nbsp;&nbsp;Add Seguin Form Board(S.F.B)</th>
            </tr>
     
            <tr>
                <th>Trials</th>
                <th align="left">Time Taken in Seconds</th>
            </tr>
            <tr>
                <td >Trial1</td>

                <td >Seconds&nbsp;&nbsp;<html:text property="sfbtrialone" size="9" onblur="this.value=removeSpaces(this.value);"/></td>


            </tr>
            <tr>
                <td >Trial 2</td>

                <td >Seconds&nbsp;&nbsp;<html:text property="sfbtrialtwo" size="9" onblur="this.value=removeSpaces(this.value);"/></td>


            </tr>
            <tr>
                <td >Trial 3</td>

                <td >Seconds&nbsp;&nbsp;<html:text property="sfbtrialthree" size="9" onblur="this.value=removeSpaces(this.value);"/></td>


            </tr>
       
            <tr>
                <td colspan="4"><br>Mental Age &nbsp;&nbsp;&nbsp; Years:<font color="red">*</font><html:text property="sfbyear"  onblur="this.value=removeSpaces(this.value);"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Months:<font color="red">*</font><html:text property="sfbmonth" onblur="this.value=removeSpaces(this.value);"/></td>


            </tr>
       
            <tr >
                <th colspan="6"><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/>
              <html:submit  value="Add" styleId="hearButton"  styleClass="button"/>
              <html:button property=""   value="Reset" onclick="goReset()" styleClass="button"/></th></tr>

        </table>


    </html:form>
</body>

</html:html>


