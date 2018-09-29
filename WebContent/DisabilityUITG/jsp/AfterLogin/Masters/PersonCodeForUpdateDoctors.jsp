<%-- 
    Document   : PersonCodeForUpdateDocctors
    Created on : Jul 8, 2010, 2:20:46 PM
    Author     : sivakumar_g
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

<% String msg = (String) request.getAttribute("msg");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="document.personcode.focus()">
        <html:form action="selectdoctorsinformation.do?parameter=selectDoctorInformation" method="post"  onsubmit="return validate_form(this)" >


            <table  align="center" cellspacing="0" cellpadding="0" class="inputform" border="1">
                <input type="hidden" name="doctorsUpdate" value="true" />

                <tr><th  colspan="2" align="center">Update Doctors Information</th></tr>

                <tr>
                    <td >

                        <table align="center" cellspacing="0" cellpadding="0" class="inputform" width="100%">


                            <tr><td colspan="2" class="Note">  <%  msg = (String) request.getAttribute("msg");
                                    %>
                                    <% if (msg != null) {%> <%=msg%> <% }%></td>
                            </tr>

                            <tr>
                                <td  align="center">SADAREM ID</td>
                                <td >
                                    <input type="text"  maxlength="17" size="17" name="personcode"/>
                                </td>
                            </tr>
                         

                        </table>
                    </td>
                </tr>
                <tr>
                    <th align="center" colspan="2">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>

    </table>
</html:form>
</body>
</html>

