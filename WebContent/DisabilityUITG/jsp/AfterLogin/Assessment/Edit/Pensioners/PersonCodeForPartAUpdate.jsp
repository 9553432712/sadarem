
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

<!DOCTYPE HTML >


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
<% 
	String message 			 = null;
    String pensionNumberFlow = request.getParameter("pensionNumberFlow");
%>
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
            {
            	personcode.focus();return false;
            } 
        }
    }
</script>

    </head>
    <body onload="document.partAForm.personcode.focus()">
        <html:form action="TerritorySelectForUpdate.do?getPersonDetails=getPersonDetails" method="post" styleId="partAForm" onsubmit="return validate_form(this)" >

            <p  id="succmsg">  <%  message = (String) request.getAttribute("msg");
                %>
                <% if (message != null) {%> <%=message%> <% }%></p>

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">

                <input type="hidden" name="flag" value="true"/>
                <input type="hidden" name="status" value="finish" />
                <input type="hidden" name="pensionNumberFlow" value=<%=pensionNumberFlow%> />
                <input type="hidden" name="partAUpdate" value="true" />

                <tr><th colspan="4">Enter SADAREM ID for Update Part-A Only</th></tr>
                <tr>
                    <td  align="center">SADAREM ID</td>
                    <td >
                        <input type="text"  maxlength="17" size="17" name="personcode"/>
                    </td>
                <tr>
                    <th align="center" colspan="4">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>




            </table>
        </html:form>
    </body>
</html>
