<%-- 
    Document   : EnterPersonCodeForCertificateWithIdcard
    Created on : May 30, 2010, 9:11:59 PM
    Author     : t_bapinaidu
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<!DOCTYPE HTML >
<html:html>

    <script type="text/javascript">
        function validate_required(field)
        {
            with (field)
            {
                if(value==null||value=="")
                {
                	alert("Please enter SADAREM ID");
                	return false;
                }
                var numericExpression = /\d{17}$/;
                
                if(!value.match(numericExpression))
                {
                	alert("SADAREM ID must be Integer");
                	return false;
                }
                
                if(value.length !=17)
                {
                	alert("SADAREM ID must be exactly of 17 numbers");
                	return false;
                }


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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <html:form action="CertificateForPersoncode.do" styleId="eligibleReportForm"  onsubmit="return validate_form(this)">
        
		<input type="hidden" name="print" value="certificate" />
		<input type="hidden" name="flag" value="true"/>
		<input type="hidden" name="certificatewithidcard" value="true"/>
		<input type="hidden" name="displaybackbuttonforAR" value="hide"/>
			<div class="col-md-5 col-md-offset-3">
				<div class="panel panel-primary">				            		 
					<div class="panel-heading" style="text-align: center;"><b> Eligible Person Certificate </b></div>
						<div class="panel-body">
							<div class="form-group" style="height:30px;padding-top:5px;">
						         <div class="input-group">
				              		<div class="input-group-addon" style="background-color: #37ADB6; color:#fff;text-align:left;">SADAREM ID</div>
									<input type="text" id="personcode" name="personcode" class="form-control" maxlength="17" autocomplete="off" />
								 </div> 
							</div>
								 <label for="usr" style="float: right;">			
					                    <html:submit  value="Submit" styleClass="btn btn-primary"/>			
								 </label> 	
			            </div>  
				</div>
	 		</div>  
</html:form>

 
</body>

</html:html> 