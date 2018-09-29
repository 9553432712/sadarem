<%-- 
    Document   : EnterPesonCodeforPhysicalRequire
    Created on : Jul 30, 2012, 2:47:34 PM
    Author     : 490058
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function validate_required(field)
    {
        with (field)
        {
            if (value==null||value=="")
            {alert("SADAREM ID must be filled out!");return false;}
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
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter SADAREM ID</title>
    </head>
    <body>
        <html:form action="/CertificatewithPhysicalRequirement.do" onsubmit="return validate_form(this)" method="post">

            <br><br><br><br>
            <center> <% String message = (String) request.getAttribute("msg");%>
                <% if (message != null) {%>
                <font color="red"><b><%=message%></b>
                </font>
                <% } else {%><br><br> <% }%></center>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="60%">

                <tr><td class="heading" colspan="2" align="center">Enter Existing PersonCode for Certificate with Physical Requirements</td></tr>

            </table>

            <table align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="60%">

                <tr><td class="columnHeight10" colspan="2"></td></tr>

                <tr>
                    <td class="labelBlue" align="center">SADAREM ID</td>
                    <td class="label">
                        <input type="text"  maxlength="17" size="17" name="personcode"/>
                    </td>
                </tr>
                <tr><td colspan="2" class="columnHeight20"></td></tr>

                <tr>
                    <td align="center" colspan="2">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </td>
                </tr>

                <tr><td class="columnHeight10" colspan="2"></td></tr>

            </table>
        </html:form>
    </body>
</html>
