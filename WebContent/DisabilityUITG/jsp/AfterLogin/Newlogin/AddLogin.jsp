
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <head>
        <script language="javascript" src="./DisabilityUITG/js/PassowrdValidation.js"></script>
        <script language="javascript" src="./DisabilityUITG/js/passwordEncryption.js"></script>
        <%String userName = "";
                    if ((String) request.getAttribute("userName") != null) {
                        userName = (String) request.getAttribute("userName");
                    }
                    String password = "";
                    if ((String) request.getAttribute("password") != null) {
                        password = (String) request.getAttribute("password");
                    }
                    String encyptPwd = "";
                    if ((String) request.getAttribute("encyptPwd") != null) {
                        encyptPwd = (String) request.getAttribute("encyptPwd");
                    }
        %>
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
                    if (validate_required(person_name,"Enter Person Name")==false )

                    {
                        person_name.focus();
                        return false
                    }

                    if (validate_required(user_name,"Enter User Name")==false)
                    {
                        user_name.focus();
                        return false
                    }

                    if (validate_required(password,"Enter password")==false )
                    {
                        password.focus();
                        return false
                    }

                    if (validate_required(role_id,"Select Role")==false)
                    {
                        role_id.focus();
                        return false
                    }

                    if (validate_required(district_id,"Select District")==false)
                    {
                        district_id.focus();
                        return false
                    }

                    if (validate_required(camp_id,"Select Camp")==false)
                    {
                        camp_id.focus();
                        return false
                    }
                    var passed = validatePassword(document.addhabitationform.password.value, {
                        length:   [8, 15],
                        upper:    1,
                        numeric:  1,
                        special:  1,
                        badWords: ["password"]
                    });
                    if(passed==false){
                        alert("Password should have atleast 8 letters, 1 Capital letter, 1 Number and 1 Specialcharacter ");
                        return false
                    }
                    document.forms[0].elements["encrptPwd"].value = enctypemd5( document.forms[0].elements["password"].value);

                }
            }
            function enctypemd5(pwd){
                var encpwd=calcMD5(pwd);
                document.forms[0].encrptPwd.value=encpwd;
                //document.forms[0].submit();
                return encpwd;
            }
            function getData(read)
            {
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }
            function alpha(e) {
                var k;
                document.all ? k = e.keyCode : k = e.which;
                return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32);
            }


            function validatesurname1(){
            
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
             
                var user_name=document.getElementById("user_name").value;
           
                for (var i = 0; i < user_name.length; i++) {
                    if (iChars.indexOf(user_name.charAt(i)) != -1) {
  	  
                        document.getElementById("user_name").value="";
                        document.getElementById("user_name").focus();
                        return false;
                    }
                }
            }

            function restrict(tbox,chr) {
                tbox.value=tbox.value.replace(chr,''); //replaces specified character with nothing
            }
           

        </script>

    </head>

    <BODY>

    

    <html:form action="addLogin.do?addLogin=addLogin" onsubmit="return validate_form(this)" >

        <input type="hidden" name="status" value="finish"/>
        <input type="hidden" name="encrptPwd" />
        <% String message = (String) request.getAttribute("msg");%>
        <% if (message != null) {%> <font color="red" size="2"><b><center><%=message%> <% }%></center></b></font><br>

        
        <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="90%">
            <tr>
                <th colspan="4">
                    Create Login for Camp Admin
                </th>
            </tr>
            <tr>
                <td>Person Name</td>
                <td>
                    <html:text property="person_name"   onkeypress=" return alpha(event)" maxlength="25"/>
                </td>
                 <td>User Name</td>
                <td>
                    <%--<html:text property="user_name" onkeypress="return alpha(event)" maxlength="15" onblur=" validatesurname1()" onkeyup="restrict(this,' ')"/>
                    --%> <input type="text" name="user_name" value="<%=userName%>" onkeypress="return alpha(event)" maxlength="15" onblur=" validatesurname1()" onkeyup="restrict(this,' ')" autocomplete="off">
                </td>
            </tr>
            
            <tr>
                <td>Password</td>
                <td>
                    <%--<html:password property="password" maxlength="10"  onkeyup="restrict(this,' ')"/>
                    --%> <input type="text" name="password" value="<%=password%>"  maxlength="15" onkeyup="restrict(this,' ')" autocomplete="off">

                </td>
                 <td>Role Name</td>

                <td>
                    <html:select  property="role_id">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="rolelist" label="role_name" value="role_id"  />
                    </html:select>
                </td>
            </tr>
          
            <tr>
                <td>District Name</td>

                <td>
                    <html:select  property="district_id" onchange="getData(this.value)">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                    </html:select>
                </td>
                 <td>Camp Name</td>

                <td>
                    <html:select  property="camp_id">
                        <html:option  value="">--SELECT--</html:option>
                        <html:optionsCollection   property="camplist" label="camp_name" value="camp_id"  />
                    </html:select>
                </td>
            </tr>
           
            <tr>
                <th colspan="4" ><html:submit value="submit" styleClass="button"/></th>
            </tr>

        </table>



    </html:form>


</body>
</html:html>
