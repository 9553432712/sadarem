<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%-- 
    Document   : RailwayCertificatewithpersoncode
    Created on : Jun 30, 2011, 11:11:33 AM
    Author     : 509862
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
        <html:form action="RailwayWithPersoncode1.do" method="post" styleId="partAForm" onsubmit="return validate_form(this)" >

            <input type="hidden" name="flag" value="true"/>
            <input type="hidden" name="status" value="finish" />
            <input type="hidden" name="pensionNumberFlow" value=<%=pensionNumberFlow%> />

 <% if (status != null) {%> <b><%=status%> <% }%></b>
            <table align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr><th class="heading" colspan="4" align="center">Enter SADAREM ID for Railway Certificate</th></tr>
                <tr>
                    <td align="center">SADAREM ID</td>
                    <td >
                        <input type="text"  maxlength="17" size="17" name="personcode"/>
                    </td>
                </tr><tr>
                    <th  colspan="2">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>

            </table>
        </html:form>
    </body>
</html>
