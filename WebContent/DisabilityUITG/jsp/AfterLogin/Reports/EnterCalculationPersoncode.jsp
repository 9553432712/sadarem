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
                if (validate_required(personCodeFlag)==false)
                {personCodeFlag.focus();return false;}
                if (validate_required(personCodeFlag)==false)
                {personCodeFlag.focus();return false;}
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
    <body>

        <form action="showCalculationsAction.do"  onsubmit="return validate_form(this)">
            <input type="hidden" name="print" value="certificate" />
            <input type="hidden" name="flag" value="true"/>
            <input type="hidden" name="decisionparameter" value="true"/>
            <table align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">
                <tr>
                    <th colspan="3">Percentage Calculation</th>
                </tr>
                <tr>
                    <td >
                        Enter SADAREM ID :
                    </td>
                    <td>
                        <input type="text" name="personCodeFlag" maxlength="17" size="17"/>
                    </td></tr><tr>
                    <th align="center" colspan="4">
                        <html:submit  value="Submit" styleClass="button"/>
                    </th>
                </tr>
            </table>
        </form>
    </body>
</html:html>
