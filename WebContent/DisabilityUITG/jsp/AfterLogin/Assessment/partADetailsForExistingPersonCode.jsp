<%-- 
    Document   : partADetailsForExistingPersonCode
    Created on : Sep 7, 2014, 6:48:32 PM
    Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
            if(!value.match(numericExpression))
            {alert("SADAREM ID must be Integer");return false;}
            if(value.length !=17)
            {alert("SADAREM ID must be exactly of 17 numbers");return false;}


        }
    }

    function validate_form(thisform)
    {
        with (thisform)
        {
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
        }
    }

    function onlyNumbers(evt)
    {
        var e = event || evt; // for trans-browser compatibility
        var charCode = e.which || e.keyCode;

        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;

    }
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="/personalDetailsForPersonCode" method="post" onsubmit="return validate_form(this)" >
            <input type="hidden" name="flag" value="true"/>
            <input type="hidden" name="status" value="finish" />
            <logic:present name="msg">
                <center>   <font color="red" size="2">${msg}</font></center>
            </logic:present>

            <table  align="center" cellspacing="0" cellpadding="0" class="inputform"  border="0" width="50%">

                <logic:present name="alreadyRegistered">
                    <p id="errmsg"> Already Camp Scheduled for this SADAREM ID &nbsp;

                        <logic:iterate id="row" name="alreadyRegistered"  >

                            At  <font color="blue">${row.campdate}</font>&nbsp;,&nbsp;
                            <font color="blue">${row.campaddress}</font>&nbsp;Camp,&nbsp;
                            On  <font color="blue">${row.campname}</font>&nbsp;Date.&nbsp;


                        </logic:iterate></p>

                </logic:present>

                <tr>
                    <th colspan="4" align="center" >Enter SADAREM ID</th>
                </tr>
                <tr>
                    <td>
                        <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="100%">

                            <tr><td  align="center">SADAREM ID</td>
                                <td width="50%" align="left"><input type="text"  maxlength="17" size="17" name="personcode" onkeypress="return onlyNumbers();"/>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
                <tr>
                    <th align="center" colspan="4">
                        <input type="submit" value="Submit" class="button"/>&nbsp;&nbsp;

                        <input type="reset" value="Reset" class="button"/>
                    </th>
                </tr>
            </table>

        </html:form>
    </body>
</html>
