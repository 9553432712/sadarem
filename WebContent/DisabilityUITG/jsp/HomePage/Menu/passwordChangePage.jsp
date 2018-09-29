<%--
    Document   : passwordChangePage
    Created on : Nov 08, 2013, 3:53:31 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=${encoding}">
        <title>SADAREM</title>
        <script language="javascript" src="./DisabilityUITG/js/passwordEncryption.js"></script>
        <script type="text/javascript">
            <%--function clearValues(){
                document.forms[0].newpassword.value="";
                document.forms[0].confirmpassword.value="";
            }--%>


                function restrict(tbox,chr) {
                    tbox.value=tbox.value.replace(chr,''); //replaces specified character with nothing
                }
                function pwdEncryption(thisform){
                    with (thisform)
                    {
                        document.forms[0].elements["encrptPwd"].value = enctypemd5( document.forms[0].elements["newpassword"].value);
                        
                    }
                }


                function enctypemd5(pwd){
                    var encpwd=calcMD5(pwd);
                    document.forms[0].encrptPwd.value=encpwd;
                    //document.forms[0].submit();
                    return encpwd;
                }
        </script>

    </head>
    <body >
        <br/>
        <html:form action="/login.do?mode=changePassword" onsubmit="return pwdEncryption(this)">
            <input type="hidden" name="encrptPwd" />

            <% if (request.getAttribute("MSG") != null) {%>
            <center>  <b style="color: red;font-size: 15px"><%=request.getAttribute("MSG")%> </b></center>
            <% }%>
            <br/>
            <table cellpadding="0" cellspacing="0" align="center" border="1" class="inputform"><tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" align="center"  class="inputform" width="100%">
                <th colspan="2"> Reset Password </th>


                <tr><td align="left" class="datafield" width="50%" > <b> New Password </b></td>
                    <td align="left" class="datafield" > 
                        <%--<html:password property="newpassword" size="15" maxlength="15" onkeyup="restrict(this,' ')" />--%>
                        <input type="text" name="newpassword"  size="15" maxlength="15" autocomplete="off" onkeyup="restrict(this,' ')" >
                    </td>
                </tr>

                <tr><td align="left" class="datafield" width="100" > <b> Conform Password </b></td>
                    <td align="left" class="datafield" > <%--<html:password property="confirmpassword" size="15" maxlength="15" onkeyup="restrict(this,' ')" />--%>
                        <input type="text" name="confirmpassword"  size="15" maxlength="15" autocomplete="off" onkeyup="restrict(this,' ')" >
                    </td></tr>

                <tr>
                    <td colspan="2" align="center" class="tableheader">
                        <html:submit  value="Submit" styleClass="button" />
                        <html:reset  value="Reset" styleClass="button"/>
                    </td>
                </tr>
            </table>

</td></tr></table>
        </html:form>

        <br/>
        <br/>
       

        <table align="center" >

            <tr>
                <td colspan="3" align="center" class="tableheader"><b style="color: red;"> Password Policies</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="left">Password Must Have atleast 8 letters,One Capital Letter,One Special Symbol,One Alphabet,One Number</td>
            </tr>          

            <tr>
                <td align="left" >Example:<font style="color: red;">Test123$</font>

                </td>
            </tr>
        </table>

    </body>
</html>
