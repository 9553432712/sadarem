<%-- 
    Document   : EnterPersonCodeForDataEnteredFields
    Created on : Jun 9, 2010, 3:24:46 PM
    Author     : t_bapinaidu
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

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

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body onload="document.getElementById('personcode').focus()">
        <html:form action="getDataEnteredFields.do?getDataEnteredFieldsDetails=getDataEnteredFieldsDetails" onsubmit="return validate_form(this)" method="post">
          <%String message = (String) request.getAttribute("msg");%>
          <% if (message != null) {%><center><font color="red" size="3"> <%=message%></font></center> <% }%>
                
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" border="0" width="50%">
               
                <tr>
                    <th colspan="3">
                       Data Entered Fields
                    </th>
                </tr>
                <tr>
                    <td>Enter SADAREM ID </td>
                    <td><input type="text" id="personcode" name="personcode" maxlength="17" size="17"  /></td></tr>
                <tr> <th colspan="4">
                        <html:submit value="Submit" styleClass="button"/>
                    </th>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>
