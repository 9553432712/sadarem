<%-- 
    Document   : ApsrtcCertificate
    Created on : Aug 8, 2014, 5:39:20 PM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    </head>
    <body>
        <html:form action="apsrtcCertificate.do" method="post" onsubmit="return validate_form(this)" >
            <table align="center" cellspacing="1" cellpadding="8" class="inputform" width="60%">
               <%-- <input type="hidden" name="personcode"  value="personcode"/>--%>
              
                <tr><th class="heading" colspan="4" align="center">Enter SADAREM ID For APSRTC Certificate</th></tr>

                <tr>
                    <td  align="center">SADAREM ID</td>
                    <td >
                        <input type="text"  maxlength="17" size="17" name="personcode"/>
                    </td>
                </tr>
                <tr>
                    <th colspan="4">
                        <html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset" class="button" />
                    </th>
                </tr>
            </table>
                        <br/>
                         <logic:present name="errorMsg">
                             <center><font color="red" size="3" style="font-weight: bold">${errorMsg}</font></center>
               </logic:present>
        </html:form>
    </body>
</html>
