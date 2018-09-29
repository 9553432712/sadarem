<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
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
    <script language="JavaScript">

        function validate_required(field)
        {
            with (field)
            {
                if (value==null||value=="") {
                    alert("Please Enter SADAREM ID");
                    document.forms[0].elements['pensioncode'].value="";
                    document.forms[0].elements['pensioncode'].focus();
                    return false;
                }
                var numericExpression = /^[0-9]+$/;

                if(!value.match(numericExpression)) {
                    alert("SADAREM ID must be Integer");
                    document.forms[0].elements['pensioncode'].value="";
                    document.forms[0].elements['pensioncode'].focus();
                    return false;
                }
                if(value.length !=17) {
                    alert("SADAREM ID must be exactly of 17 numbers");
                    document.forms[0].elements['pensioncode'].value="";
                    document.forms[0].elements['pensioncode'].focus();
                    return false;
                }
            }
        }

        function validate_form(thisform)
        {
            with (thisform)
            {// alert(validate_required(pensioncode)+"validateform");
                if (validate_required(pensioncode)==false)
                { return false;
                    personcode.focus();
                }
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    
    <body><br>
        <html:form action="registrationPhysicalRequirements.do" styleId="partAForm"  method="post" onsubmit="return validate_form(this)" focus="pensioncode">
            <% String msg = (String) request.getAttribute("msg");
                        if (msg != null) {%>
            <center> <font color="red"><B><%= msg%></B></font></center>
            <% }
            %>
            <br><br>
            <table  align="center" cellspacing="0" cellpadding="5" class="innerTable" width="50%">
                <tr><td colspan="2" align="center" class="heading">Enter Existing SADAREM ID </td></tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="50%">
                <tr>
                    <td  align="center" class="label">SADAREM ID :</td>
                    <td>
                        <html:text property="pensioncode" onkeypress="return isNumberKey(event)" maxlength="17"/>
                    </td>
                </tr>
                <tr><td class="columnHeight20"></td></tr>
                <tr><td align="center" colspan="2" >
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;

                    </td>
                </tr>
            </table>
            <table>
            </table>
        </html:form>
    </body>
</html:html>