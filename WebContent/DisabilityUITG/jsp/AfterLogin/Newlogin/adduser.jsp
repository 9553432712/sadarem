<%@page import="com.tcs.sadarem.util.ComboUtil,java.util.ArrayList"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:: SADAREM :: New Data Entry Operator</title> 
        
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/Common.js"></script>
		
		
        <script language="javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/PassowrdValidation.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/passwordEncryption.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/aes.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pad-nopadding-min.js"></script> 
	<style>
	
	.errmsg
	{
		color: red;
	}
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
                    if (validate_required(username,"Enter Person Name")==false)
                    {
                        username.focus();
                        return false
                    }
                    if (fun_validateAadhaarID(userid.value)==false)
                    {
                    	alert("Enter valid Aadhar Id");
                        userid.focus();
                        return false
                    }  
                    if (validate_required(password,"Enter Password")==false)
                    {
                        password.focus();
                        return false
                    }
                     
                    if (validate_required(roleid,"Select Role Id")==false)
                    {
                        roleid.focus();
                        return false
                    }
                    var passed = validatePassword(document.forms[0].password.value, {
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
                     
  					document.forms[0].userencrptPwd.value= encrypt(document.forms[0].password.value); 
                    document.forms[0].encrptPwd.value=    enctypemd5(document.forms[0].password.value);
                    
                    document.forms[0].password.value="";
                    document.forms[0].password.disabled=true;
                }            	
            }
            function enctypemd5(pwd)
            {
                var encpwd=calcMD5(pwd);
                document.forms[0].encrptPwd.value=encpwd;
                //document.forms[0].submit();
                return encpwd;
            }
            
            function validatesurname1()
            {

                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";

                var user_name=document.forms[0].userid.value;

                for (var i = 0; i < userid.length; i++) {
                    if (iChars.indexOf(userid.charAt(i)) != -1) {

                        document.forms[0].userid.value="";
                        document.forms[0].userid.focus();
                        return false;
                    }
                }
            }

            function restrict(tbox,chr) 
            {
                tbox.value=tbox.value.replace(chr,''); //replaces specified character with nothing
            }

           

        </script>


    </head>
    <body>
       
        <html:form action="/adduserdisplay.do"  method="post"  onsubmit="return validate_form(this)">
            <html:hidden property="mode" value="insert"/>
            <html:hidden property="encrptPwd" styleId="encrptPwd"/> 
            <html:hidden property="userencrptPwd" styleId="userencrptPwd"/>

            <p  id="succmsg"> <% String msg= (String) request.getAttribute("msg");%>

                <% if (msg!= null) {%> <%=msg%> <% }%></p>

            <p  id="errmsg"> <% String msg1 = (String) request.getAttribute("message");%>

                <% if (msg1 != null) {%> <%=msg1%> <% }%></p>

				  
            <table  align="center" class="table table-striped table-bordered" style="width:50% !important;">
                <tr>
                    <th colspan="2" style="background-color:#337ab7;padding:5px;color:#fff; text-align: center; vertical-align: middle;"> Create Login for Data Entry Operator</th>
                </tr>
                <tr>
                	<td class="firstline" style="text-align: left;width:50%">
                		Person Name<font color="red"><b>* </b></font>
                	</td>
                    <td class="secondline" style="text-align: left;width:50%"> 
                    		<html:text property="username" styleId="username" size="30" maxlength="25"/>
                    </td>
                </tr>


                <tr>
                	<td class="firstline" style="text-align: left;width:50%">
                			Aadhaar ID (User ID)<font color="red"><b>* </b></font>
                	</td>
                    <td class="secondline" style="text-align: left;width:50%">
                        <html:text property="userid" styleId="userid" size="30" maxlength="15" onkeypress="return NumbersOnly(event);" onblur="this.value = SpaceReplace(this.value);" />
                         <span id="aadhaaridErrMsg"></span>
 					</td>
                </tr>
                <tr>
                	<td class="firstline" style="text-align: left;width:50%">
                		Password<font color="red"><b>* </b></font>
                	</td>
                    <td class="secondline" style="text-align: left;width:50%">
                        <html:password property="password" styleId="password" size="30" maxlength="15"  onkeyup="restrict(this,' ')"/>
                    </td> 
                </tr>

                <tr>
                	<td class="firstline" style="text-align: left;width:50%">
                		Role<font color="red"><b>* </b></font>
                	</td>
                    <td class="secondline" style="text-align: left;width:50%">
                        <html:select property="roleid" styleId="roleid" styleClass="dropdown" style="width:250px;">
                            <html:option value="">--SELECT--</html:option>
                            <html:optionsCollection property="rolesList" label="name" value="id"/>
                        </html:select>
                    </td>
                </tr>


                <tr>
                    <th colspan="2" class="firstline" style="text-align: center;width:100%">
                        <html:submit styleClass="btn btn-success" />
                        <html:reset styleClass="btn btn-info" />
                    </th>
                </tr>
            </table>
            
        </html:form>
        
<script>
$(document).ready(function()
		{

	
			 $(function(){
			        $(":text").attr("autocomplete", "off");
			    });
			
			 
			 $(document).on('focus', ':input', function() {
				    $(this).attr('autocomplete', 'off');
				  });
			 
	
			 $("#userid").on('keypress blur change paste cut',function (e)
		     		{
		     	
		 				    	var pattern = /^\d{12}$/;
		 				    	
		 						if((pattern.test($(this).val())==false || fun_validateAadhaarID($(this).val()) ==false) && $(this).val().length>0)
		 						{
		 							 $("#aadhaaridErrMsg").addClass("errmsg");
		 					           	 $("#aadhaaridErrMsg").html("Not Valid").show();
		 						}
		 						else
		 						{
		 							 $("#aadhaaridErrMsg").removeClass("errmsg");
		 					           	 $("#aadhaaridErrMsg").html("").show();
		 						}			
		     	
		      	  });
		});

</script>
        
        
           
    </body>
</html>

