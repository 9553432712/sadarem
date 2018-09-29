
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
<% String pensionNumberFlow = request.getParameter("pensionNumberFlow");
            String status = (String) request.getAttribute("massage");%>
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="document.partAForm.personcode.focus()">
        <html:form action="TerritorySelectForUpdate.do?getPersonDetails=getPersonDetails" method="post" styleId="partAForm" onsubmit="return validate_form(this)" >

            <input type="hidden" name="flag" value="true"/>
            <input type="hidden" name="status" value="finish" />
            <input type="hidden" name="pensionNumberFlow" value=<%=pensionNumberFlow%> />

            <center><span>   <% if (status != null) {%><p id="errmsg"><%=status%></p> <% }%></span></center>

          
            <table align="center" cellspacing="0" cellpadding="0" width="50%" border="1">
                <tr><td>
                        <table align="center" cellspacing="0" cellpadding="0" class="inputform"  width="100%"  border="0">
                            <tr><th colspan="2">Enter SADAREM ID for Update</th></tr>


                            <tr>
                                <td  align="center">SADAREM ID</td>
                                <td >
                                    <input type="text"  maxlength="17" size="17" name="personcode" style="width:150px"/>
                                </td>
                            </tr>

                            <tr>
                                <th align="center" colspan="2">
                                    <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                                    <input type="reset" value="Reset" class="button" />
                                </th>
                            </tr>


                        </table>
                    </td>
                </tr>

            </table>
        </html:form>
    </body>
</html>
