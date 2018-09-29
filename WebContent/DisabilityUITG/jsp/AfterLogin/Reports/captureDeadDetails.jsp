<%-- 
    Document   : captureDeadDetails
    Created on : Dec 29, 2014, 6:33:27 PM
    Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">
    function validate_required(field)
    {
        with (field)
        { 
            if (value==null||value=="")
            {alert("Please Enter SADAREM ID");return false;}
            var numericExpression = /^[0-9]+$/;            
            if(value.length !=17)
            {
                alert("SADAREM ID must be exactly of 17 numbers");
                document.forms[0].personcode.value="";
                return false;
            }


        }
    }

    function submitSadarem(thisform)
    {var flag = true;
        with (thisform)
        {
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
        }

        if (thisform.getAttribute('submitted')){
            flag = false;
            return flag;
        }else{
            thisform.setAttribute('submitted','true');
        }
            
        document.forms[0].mode.value="validateSADAREMID";
        document.forms[0].submit();
        
    }

   
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>SADAREM</title>

      

    </head>
    <body>

        <html:form action="captureDeadDetails?mode=validateSADAREMID" onsubmit="return submitSadarem(this)" method="post" enctype="multipart/form-data" >
            <input type="hidden" name="mode"/>

            <logic:present name="succmsg">
                <p id="succmsg"><bean:write name="succmsg"/></p>
            </logic:present>
            <logic:present name="errmsg">
                <p id="errmsg"><bean:write name="errmsg"/></p>
            </logic:present>
  <logic:present name="editPwdStatus">
                <p id="errmsg">${editPwdStatus}</p>
            </logic:present>
            <logic:notPresent name="SADAREMIDValidDetails">
                <table  align="center" cellspacing="1" cellpadding="" class="inputform" width="90%">
                    <th>
                        Data Validation of Death Cases, Aids & Appliances, Surgery Corrections
                    </th>


                    <tr>
                        <td>
                            <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="100%">

                                <tr  style="text-align: center"><td>SADAREM ID</td>
                                    <td width="50%"><input type="text"  maxlength="17" size="17" name="personcode" onkeypress="return onlyNumbers();"/>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th align="center" colspan="4">
                            <html:submit value="Submit" styleClass="button" styleId="subButton" onclick="return submitSadarem(this)" />&nbsp;&nbsp;

                            <input type="reset" value="Reset" class="button"/>
                        </th>
                    </tr>
                </table>
            </logic:notPresent>
           

        </html:form>
    </body>


</html>
