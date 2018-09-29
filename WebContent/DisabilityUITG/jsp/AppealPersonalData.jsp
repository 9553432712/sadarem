<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@page session="true"%>
<!DOCTYPE HTML>
<html:html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:: SADAREM :: </title>
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
                var numericExpression = /^\d{17}$/;

                if(!value.match(numericExpression))
                {
                    alert("SADAREM ID must be Integer");
                    document.forms[0].elements['pensioncode'].value="";
                    document.forms[0].elements['pensioncode'].focus();
                    return false;
                }
                if(value.length !=17)
				{
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
                { 
                	return false;
                    personcode.focus();
                }
            }
        }
    </script>
    </head>
    
    <body>
        <html:form action="Appeal.do" styleId="partAForm"  method="post" onsubmit="return validate_form(this)" focus="pensioncode">
       <% String msg = (String) request.getAttribute("msg");
                        if (msg != null) {%>
                        <center><%= msg%></center>
            <% }
            %>


			<div class="col-md-5 col-md-offset-3">
				<div class="panel panel-primary">				            		 
					<div class="panel-heading" style="text-align: center;"><b> Re-Assessment Registration </b></div>
						<div class="panel-body">
							<div class="form-group" style="height:30px;padding-top:5px;">
						         <div class="input-group">
				              		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div> 
									<html:text property="pensioncode" styleId="pensioncode" styleClass="form-control" onkeypress="return isNumberKey(event)" maxlength="17"/>
								 </div> 
							</div>
								 <label for="usr" style="float: right;">			
					                    <input type="submit" value="Submit" class="btn btn-primary">			
								 </label> 	
			            </div>  
				</div>
	 		</div> 
    	</html:form>
    </body>
</html:html>