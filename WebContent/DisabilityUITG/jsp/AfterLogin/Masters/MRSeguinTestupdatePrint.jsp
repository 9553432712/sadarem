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
   
   <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
  
 

</HEAD>
<script language="javascript" >
    function goBack()
    {
        document.forms[0].action="MentalRetardationForwdActionPrint.do";
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



<body onload="disableForm(mentalform);">
    <html:errors/>
    <html:form action="MRSfbdetailsselectPrint.do" method="post" styleId="mentalform">
        <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>Sadarem Code : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
        </table><br/>

        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="60%">
            <tr>
                <td  align="center" class="heading">5.&nbsp;&nbsp;Update Seguin Form Board(S.F.B)</td>
            </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="60%">
            <tr>
                <td class="labelBlue"><br>Trials</td>
                <td class="labelBlue">Time Taken in Seconds( Old )</td>
                 <td class="labelBlue">Time Taken in Seconds( New )</td>
            </tr>
            <tr>
                <td class="label">Trial 1</td>
                <td class="label">Seconds<html:text property="sfbtrialone" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Seconds<input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
            <tr>
                <td class="label">Trial 2</td>

                <td class="label">Seconds<html:text property="sfbtrialtwo" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Seconds<input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
            <tr>
                <td class="label">Trial 3</td>
                <td class="label">Seconds<html:text property="sfbtrialthree" size="9" onblur="this.value=removeSpaces(this.value);"/></td>
                <td class="label">Seconds<input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/></td>

            </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="60%">
            <tr>
                <td class="labelBlue">Mental Age</td>
                <td class="label">Years <html:text property="sfbyear" size="5" onblur="this.value=removeSpaces(this.value);"/>

                    Months <html:text property="sfbmonth" size="5" onblur="this.value=removeSpaces(this.value);"/></td>
<td class="label">Years<input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/>
Months<input type="text"  name="mohan"  maxlength="4" size="5" onblur="this.value=removeSpaces(this.value);"/>
</td>


            </tr>
        </table><br>

    </html:form>
    <form action="">

        <TABLE align="center">
            <tr>
                <td><html:button property=""  value="Back" onclick="goBack()" styleClass="button"/></td>
                <td><html:button property="" value="Print" onclick="window.print()" styleClass="button"/></td>
            </tr>

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


