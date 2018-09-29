<%--
Document   : OfficialLogin
Created on : 28 Oct, 2013, 6:41:34 PM
Author     : 484898 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="org.bf.disability.common.String_Enc_Dec,com.tcs.sadarem.util.CommonUtility"%>
 
<%
            String_Enc_Dec ss = new String_Enc_Dec();
            String SE = ss.encrypt("secret", session.getId().toString());
            String rn1 = String.valueOf((int) (Math.random() * 9999));
            String rn2 = String.valueOf((int) (Math.random() * 9999));
            SE = "{(" + rn1.toString() + ").(" + SE + ").(" + rn2.toString() + ")}";
            
            String captchaCode = CommonUtility.checkNullObj(request.getAttribute("captchaCode"));
            String msg		   = CommonUtility.checkNullObj(request.getAttribute("msg"));
%>

	    <script src="<%=request.getContextPath()%>/DisabilityUITG/js/md5.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/aes.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pad-nopadding-min.js"></script> 
<style>
body {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333;    
    background-repeat: repeat;
	z-index:99999;
}
.body_img {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
	height:400px;
    color: #333; background-image: url(<%=request.getContextPath()%>/images/loginbgicon.png);
    background-repeat: repeat;
	z-index:99;
}
 .txtPassword
       {
           -webkit-text-security:disc;
       } 
   
     	


/* Process Layer Started */
     	
     	#processlayer
			{
				width: 300px;
				height: 50px;
				background:#ECF1EF;
				border: red dotted 5px;
				text-align: center;
				position: fixed;
				margin-right: -150px;
				margin-top: -75px;
				right: 50%;
				top: 50%;
				z-index: 99999;
				display: none;
			}
			
			#blocklayer
			{
				position: absolute;
				left: 0;
				top: 0;
				background: #ECF1EF;
			}
     	
     /* 	 Process Layer Ended  */
     	
     	
    
     	
</style>
        <script type="text/javascript">
          
        	
         	$(document).ready(function()
        		 {  
         				$('input').keypress(function (e)
         				{	
   						  if (e.which == 13)
   							{
   								loginCheck(e);
   						  	}
         				});	 		

         				$('#loginBut').click(function(e)
     						{
     								loginCheck(e);
     						});	
         				
         			    $('#dispuserid').attr("autocomplete", "off");
         	 		    $('#disppassword').attr("autocomplete", "off");
         	 		    $('#disptxtInput').attr("autocomplete", "off");
         	 		     
         				
        		 });
 
     		 
     	 
         	function loginCheck(e)
      	  {
      		
         		var mypwd = $('#disppassword').val();
         		
         		$('#disppassword').val("");
      		//Username
      		 if($("#dispuserid").val()=="" || ($("#dispuserid").val()).length==0)
      		 {
       			$('#errorMsg').show();
      			$('#errorMsg').html("Please Enter Username.");
      			$('#dispuserid').focus();
       			$('#errorMsg').fadeOut(8000);
      			
      			return false;
      		 }
       		//Password
       		else if(mypwd=="" || (mypwd).length==0)
       		 {
        		$('#errorMsg').show();
       			$('#errorMsg').html("Please Enter Password.");
       			$('#disppassword').focus();
       			$('#errorMsg').fadeOut(8000);
       			
       			return false;
       		 }
      		//Captcha
      		else if($('#disptxtInput').val()=="" || ($('#disptxtInput').val()).length==0)
      		 {
       			$('#errorMsg').show();
      			$('#errorMsg').html("Please Enter Captcha.");
      			$('#disptxtInput').focus();
       			$('#errorMsg').fadeOut(8000);
       			
      			return false;
      		 }
      		else if($('#dispuserid').val()!="" && ($('#dispuserid').val()).length!=0 && mypwd!="" && (mypwd).length!=0 && $('#disptxtInput').val()!="" && ($('#disptxtInput').val()).length!=0)
      		{
				$('#blocklayer').focus();
      			
      			/*Screen Locking Started */
	      			 $('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
	      		    $('#processlayer').css({"display": "block","z-index":"110000"});
      			/*Screen Locking Ended */
      		
      			 $('#userid').val(encrypt($('#dispuserid').val())); 
	      		     
  				$('#password').val(encrypt(calcMD5(mypwd))); 
  				$('#txtInput').val($('#disptxtInput').val());

                $('#processlayer').focus();
  				$('#mode').val("checkLogin");

  				
  				 
  				$('#dispuserid').val("");
  				$('#disppassword').val("");
  				$('#disptxtInput').val("");
  				$('#pwdfieldid').val("");
  				
  				document.forms[0].target="_self";
                document.forms[0].submit();
      									
      			return true;
      		}
      	}
        </script> 
        <script type="text/javascript">
			 window.moveTo(screen.availLeft, screen.availTop,screen.width-screen.availWidth,screen.height-screen.availHeight);
			 window.resizeTo(screen.availWidth,screen.availHeight);

            function onlyNumbers(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }

          
        </script>

<div class="main-container" style="margin-top:10px;"> 
		<div class="alert alert-warning">
<!-- Screen Lock Started Here -->
	<div id="processlayer">
		<font color="blue" size="2">Validating Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->
 
		<div class="row" style="min-height: 400px;"> 
		
			<div class="col-xs-5 col-xs-offset-3 col-sm-5 col-sm-offset-3 col-md-5 col-md-offset-3">
			
			 <div  class="alert alert-danger" id="errorMsg" <%if(msg.equals("")){%>style="display: none;"<%}%>>
             	<logic:present name="msg">
                   <bean:write name="msg"/>
       			</logic:present>
             </div>
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title" style="text-align: left;"><b>Login Details</b></div> 
                    </div>      
                    		<div class="panel-body" style="padding-top:10px;"> 
                              <div style="margin-bottom: 25px" class="input-group">
			                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"> <b>Username</b></i></span>
			                                        <input type="text" class="form-control" name="dispuserid" id="dispuserid"  value="" autoComplete="off" maxlength="50">                                        
			                                    </div>
			                                
			                            <div style="margin-bottom: 25px" class="input-group">
			                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"> <b>Password </b>  </i></span>  
			                                        <input type="text" class="form-control txtPassword" id="pwdfieldid" name="disppassword"  value="" autocomplete="off">
			                                    </div>
			                                
			                            		<div style="margin-bottom: 25px" class="input-group" title="Captcha">
			                                        <span class="input-group-addon">
				                                        <i class="glyphicon glyphicon-barcode"></i>
				                                        <logic:present name="captchaCode">
	                                                    	<font color="blue" size="3"><b><bean:write name="captchaCode"/></b></font>
	                                               		 </logic:present>
                                               		</span>
			                                        <input type="text" class="form-control" name="disptxtInput" id="disptxtInput"  value="" autocomplete="off" onkeypress="return onlyNumbers(event);" maxlength="5">
			                                    </div>
			                                    
			
			                                <div style="margin-top:10px" class="form-group">
			                                    <!-- Button -->
			
			                                    <div class="col-sm-12">
			                                      <div style="float:left; font-size: 12px; position: relative; top:-15px;">Please login with your login details</div>
			                                      <a id="loginBut" href="#" class="btn btn-success" style=" float:right; margin-right:10px !important;">Login  </a>
			
			                                    </div>
			                                </div>
                            
                            
                            <!-- In order avoid the password saving option below changes has been done. -->
                            
			                      <html:form action="login.do" method="post" > 
							            <html:hidden property="mode"  value="checkLogin"/>          
							
							            <%
							            if (captchaCode != null && captchaCode.length() > 0) 
							            {
							            %>
							            	<input type="hidden" id="txtCaptcha" value="<%=captchaCode%>" autoComplete="off"/>
							            <%
							            }
							            %>
							            <input type="hidden" id="loginStatus1" name="loginStatus" value="<%=SE%>"  autoComplete="off"/>
			                            <input type="hidden" id="userid" name="userid"  value="" autoComplete="off">    
			                            <input type="hidden" id="password" name="password" value="" autoComplete="off">
			                            <input type="hidden" id="txtInput" name="txtInput" value="" autoComplete="off">
							            
							        </html:form>    
                    		</div>  
		       		 </div>
		       </div>
		    </div>
		</div> 
</div>


	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/MaskedPassword.js"></script>
<script>


//pass the field reference, masking symbol, and character limit
		new MaskedPassword(document.getElementById("pwdfieldid"), '\u25CF');


</script>