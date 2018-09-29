<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <style type="text/css">
            <!--
            @import url("styles.css");
            @import url("./STYLES/styles.css");
            body {
                background-image: url(./images/background.jpg);
            }
            -->
        </style>
        <script>
            function validate_required(field,alerttxt)
            {
                with (field)
                {
                    if (value==null||value=="")
                    {
                        alert(alerttxt);
                        return false
                    }
                    else
                    {
                        return true
                    }
                }
            }
            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(userid,"Select Role")==false)
                    {
                        userid.focus();
                        return false
                    }
                    if (validate_required(password,"Enter new password")==false)
                    {
                        password.focus();
                        return false
                    }
            
                                         
                }
            }
            function getData(read)
            {
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }
            
        </script>   

        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/styles.css">

    </head>
    <body>
        <br>
        <br>

        <html:form action="/resetpassword.do"  method="post" onsubmit="return validate_form(this)">


            <table border="1" align="center">
                <tr>
                    <td colspan="2" align="center" class="tableheader"> Reset Password </td>
                </tr>






                <tr><td align="left" class="datafield" width="100" > <b> Select Role </b></td>
                    <td align="left" class="datafield" > <html:select property="userid" onchange="getData(this.value)">

                            <html:option  value="">--Select One--</html:option>
                           <%-- <html:options collection="users" property="userid" labelProperty="userid" />--%>
                              <html:optionsCollection   property="users" label="userid" value="userid"  />
                        </html:select></td>
                </tr>

                <tr><td align="left" class="datafield" width="100" > <b> New Password </b></td>
                    <td align="left" class="datafield" > <html:password property="password" size="15" maxlength="15"/></td>
                </tr>



                <tr>
                    <td colspan="2" align="center" class="tableheader">
                        <html:submit  />
                        <html:reset />
                    </td>
                </tr>
            </table>
            <% String message = (String) request.getAttribute("msg");%>
            <% if (message != null) {%> <b><font color="red"><center><%=message%> <% } else {%><br><br> <% }%></center></font></b>

        </html:form>
    </body>
</html>
<p>&nbsp;</p>
<p>&nbsp;</p>
