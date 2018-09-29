<%-- 
    Document   : acknowledgementPrint
    Created on : Oct 29, 2014, 12:15:08 PM
    Author     : 842412
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function validation(){
                if(document.forms[0].elements['sadaremId'].value=="") {
                    alert("Please Enter SADAREM ID");
                    document.forms[0].elements['sadaremId'].focus();
                    return false;
                }
                document.forms[0].mode.value ="PersonCodeAcknowledgement";
                document.forms[0].submit();
            }
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
        </script>
    </head>
    <body>
        <html:form action="/acknowledgementPrint">
            <html:hidden property="mode"/>
            <table cellpadding="0" cellspacing="0" align="center" width="90%" border="1" class="inputform">
                <tr>
                    <th colspan="2">
                        Acknowledgement Print
                    </th>
                </tr>
                <tr>
                    <td>
                        SADAREM ID:<font color="red">*</font>
                    </td>
                    
                    <td>
                        <html:text property="sadaremId" maxlength="17" onkeypress="return onlyNumbers();"  style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <th colspan="4">
                        <html:button property="but" value="Submit" onclick="validation();" styleId="but" />
                    </th>
                </tr>
            </table>
        </html:form>

    </body>
</html>
