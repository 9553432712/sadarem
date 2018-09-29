<%-- 
    Document   : ViewEditMode
    Created on : Aug 20, 2010, 6:32:49 PM
    Author     : 509865
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<% String status = (String) request.getAttribute("status");%>
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


 <%--   function goUpdate()
    {
        document.personcode.value="<%=personCode%>";
        document.forms[0].action="updateViewEdit.do?updateViewOrEdit=updateViewOrEdit";
        document.forms[0].submit();
    } --%>

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
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="document.partAForm.personcode.focus()">
        <html:form action="getViewEdit.do?getViewOrEdit=getViewOrEdit" styleId="partAForm" method="post" onsubmit="return validate_form(this)" >

            <br><br><br><br>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="60%">

                <tr><td class="heading" colspan="2" align="center">Enter SADAREM ID for Person Status View or Edit</td></tr>

            </table>

            <table align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="60%">

                <tr><td class="columnHeight10" colspan="2"></td></tr>

                <tr>
                    <td class="label" align="center">SADAREM ID</td>
                    <td class="label">
                        <input type="text"  maxlength="17" size="17" name="personcode"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </td>
                </tr>

                
         
                <% if (status != null) {%> <tr><td class="label" colspan="2">Person Status is: <font color="blue"><%=status%></font></td></tr>
                
                <% }%> 

            </table>
        </html:form>
    </body>
</html>