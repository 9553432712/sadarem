
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
            {alert("employeeid must be filled out!");return false;}
            if(value.length !=10)
            {alert("employeeid must be exactly of 10 numbers");return false;}


        }
    }

    function validate_form(thisform)
    {
        with (thisform)
        {
            if (validate_required(employeeid)==false)
            {employeeid.focus();return false;}
            if (validate_required(employeeid)==false)
            {employeeid.focus();return false;}
        }
    }
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM-Employee ID</title>
    </head>
    <body>
        <html:form action="/getEmployeeDetails.do?parameter=getEmployeeDetails" onsubmit="return validate_form(this)" method="post">


            <p id="succmsg"> <% String message = (String) request.getAttribute("msg");%>
                <% if (message != null) {%>
                <b><%=message%></b>

                <% } else {%><br><br> <% }%></p>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">

                <tr><th colspan="2">Enter Employee ID for Pension</th></tr>


                <tr>
                    <td>Employee ID</td>
                    <td>
                        <input type="text"  maxlength="11" name="employeeid"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">Enter EmployeeId Like Ex:- SERP/XXXXX</td>
                </tr>


                <tr>
                    <th colspan="4">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>


            </table>
        </html:form>
    </body>
</html>
