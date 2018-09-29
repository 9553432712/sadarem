<%-- 
    Document   : changepassword
    Created on : Jun 2, 2010, 10:29:48 AM
    Author     : sivakumar_g
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
    <head>


        <title>:: SADAREM :: Change Password</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/aes.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pad-nopadding-min.js"></script> 
        
 <style>
	   .txtPassword
       {
           -webkit-text-security:disc;
       } 
   
	.errmsg
	{
		color: red;
	}
	
 </style>
	
            
    <script type="text/javascript">
            function maximize() 
            { 
                window.moveTo(0, 0);
                window.resizeTo(screen.width, screen.height);
            }
            maximize();

            history.forward();

        </script>
   
    </head>

    <body onload="clear();">
        <html:form action="/change.do"  focus="password" onsubmit="return validate_form(this)" styleId="shgloginform">
            <input type="hidden" name="encrptPwd" id="encrptPwd">
            <p id="errmsg">
                <% String message = (String) request.getAttribute("msg");%>
                <% if (message != null) {%> <b><%=message%> <% } else {%> <% }%></b>
            </p>
            
              <table  align="center" class="table table-striped table-bordered" style="width:50% !important;">
                 <tr>
                    <th colspan="2" style="background-color:#337ab7;padding:5px;color:#fff; text-align: center; vertical-align: middle;">
                          Change Password
                     </th>
                 </tr>
                <tr> 
                    <td>Old Password<font color="red"><b>*</b></font></td> 
                    <%-- <td><html:password property="password" maxlength="15" onkeyup="restrict(this,' ')" autocomplete="off"/></td> --%>
                    <td><input type="text" id="old_pwdfieldid" name="password" class="txtPassword"  maxlength="15" onkeyup="restrict(this,' ')"  autocomplete="off"></td>
                </tr>
                <tr>
                    <td>New Password<font color="red"><b>*</b></font></td>
                    <%--                    <td><html:password property="newpassword"  size="15" maxlength="15"  onblur="changepwdvalidation()" onkeyup="restrict(this,' ')"  autocomplete="off"/></td>--%>
                    <td><input type="text" id="new_pwdfieldid" name="newpassword" class="txtPassword"  maxlength="15" onkeyup="restrict(this,' ')"  autocomplete="off"></td>
                </tr>
                <tr>
                    <td >Confirm Password<font color="red"><b>*</b></font></td>
                    <%-- <td><html:password property="confirmpassword"  size="15" maxlength="15" onblur="changepwdvalidationsame()" onkeyup="restrict(this,' ')"/></td>--%>
                    <td><input type="text" id="conf_pwdfieldid" name="confirmpassword" class="txtPassword" maxlength="15" onkeyup="restrict(this,' ')" autocomplete="off"></td>

                </tr>

                <tr >
                     <th colspan="2" class="firstline" style="text-align: center;width:100%">
                    	<button type="submit" class="btn btn-success">Submit</button>
                        <html:reset  value="Reset" styleClass="btn btn-info" /> </th>
                </tr>

            </table>

        </html:form>

		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/PassowrdValidation.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/passwordEncryption.js"></script>
    
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/MaskedPassword.js"></script>
  
 
    <script type="text/javascript">

  //pass the field reference, masking symbol, and character limit
  		new MaskedPassword(document.getElementById("old_pwdfieldid"), '\u25CF');
  		new MaskedPassword(document.getElementById("new_pwdfieldid"), '\u25CF');
  		new MaskedPassword(document.getElementById("conf_pwdfieldid"), '\u25CF');
  		
            function validate_required(field,alerttxt)
            {

                with (field)
                {
                    if (value==null || value=="")
                    {
                        alert(alerttxt);
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
            function validate_form(thisform)
            {
            	 
            	
                trimFormfields(thisform);
                with (thisform)
                {
                    if (validate_required(password,"Old Password cannot be empty")==false)
                    {
                        password.focus(); 
                        return false;
                    }
                    else if (validate_required(newpassword,"New Password cannot be empty")==false)
                    {
                        newpassword.focus();
                        return false;
                    }
                    else if (validate_required(confirmpassword,"Confirm Password cannot be empty")==false)
                    {
                        confirmpassword.focus();
                        return false;
                    }
                    else if(changepwdvalidation()==false)
                   	{ 
                   		return false;
                   	}
                    else if(changepwdvalidationsame()==false)
                    {
                    	return false;
                    }
                    else
                    {
	                    var passed = validatePassword(document.getElementById("newpassword").value, {
	                        length:   [8, 15],
	                        upper:    1,
	                        numeric:  1,
	                        special:  1,
	                        badWords: ["password"]
	                    });
	                    
	                    if(passed==false)
	                    {
	                        alert("Password should have atleast 8 letters, 1 Capital letter, 1 Number and 1 Specialcharacter ");
	                        return false;
	                    }
	                    else
	                    {
	                    	 document.getElementById("encrptPwd").value = enctypemd5(document.getElementById("newpassword").value);
	                    	 document.getElementById("password").value=encrypt(document.getElementById("password").value);
	                    	 document.getElementById("newpassword").value=encrypt(document.getElementById("newpassword").value);
	                    	 document.getElementById("confirmpassword").value=encrypt(document.getElementById("confirmpassword").value);
	                    	 
	 	                    return true; 	
	                   	}
	                   
                    }
                    

                }

            }

            function enctypemd5(pwd){
                var encpwd=calcMD5(pwd);
                document.forms[0].encrptPwd.value=encpwd;
                //document.forms[0].submit();
                return encpwd;
            }
            function trim(str, chars)
            {
                return ltrim(rtrim(str, chars), chars);
            }

            function ltrim(str, chars)
            {
                chars = chars || "\\s";
                return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
            }

            function rtrim(str, chars)
            {
                chars = chars || "\\s";
                return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
            }

            function trimFormfields(thisform)
            {

                thisform.password.value=trim(thisform.password.value," ");
                thisform.newpassword.value=trim(thisform.newpassword.value," ");
                thisform.confirmpassword.value=trim(thisform.confirmpassword.value," ");

            }
            
            function clear()
            { 
            	$("#password").val("");
            	$("#newpassword").val("");
            	$("#confirmpassword").val("");
            }

            function changepwdvalidation()
            {        
                var password = document.getElementById("password").value;
                var newpassword = document.getElementById("newpassword").value;
                if(newpassword==password)
                {
                    alert("Old Password and New Password should not be Equal");
                    document.getElementById("newpassword").value="";
                    document.getElementById("newpassword").focus();
                    return false;
                }
                else
                {
                	return true;
                }
            }

            function changepwdvalidationsame()
            {
               
                var newpassword = document.getElementById("newpassword").value;
                var confirmpassword = document.getElementById("confirmpassword").value;
                
                if(confirmpassword=="")
                {
                	alert("Please enter confirm password");
                	document.getElementById("confirmpassword").focus();
                	return false;
                }
                else if(newpassword!=confirmpassword)
                {
                    alert("New Password  and Confirm Passwod must be Equal")
                    document.getElementById("newpassword").value="";
                    document.getElementById("confirmpassword").value="";
                    document.getElementById("confirmpassword").focus();
                    return false;
                }
                else
                {
                	return true;
                }

            }

            function restrict(tbox,chr) 
            {
                tbox.value=tbox.value.replace(chr,''); //replaces specified character with nothing
            }

        </script>
    </body>
</html>
