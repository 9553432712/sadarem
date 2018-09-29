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
		
<LINK REL="stylesheet" HREF="./DisabilityUITG/css/styles.css">
        <script language="JavaScript">
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
				if (validate_required(roleid,"Role must be selected!")==false)
				{
					roleid.focus();
					return false
				}
					
			}
		}

            function getData(read)
            {
            
            document.forms[0].status.value="update";
            document.forms[0].submit();
            }
            
            function comparedate(fromDate,toDate)
	{
	
	
	var fromDate= document.forms[0].fromdate.value;
        var toDate= document.forms[0].todate.value;
		
	//Validate Meeting Date
	
        var yye=fromDate.substr(6,4);
        var mme=fromDate.substr(3,2);
        var dde=fromDate.substr(0,2);
        
        var yyb=toDate.substr(6,4);
        var mmb=toDate.substr(3,2);
        var ddb=toDate.substr(0,2);
         
        var dob = new  Date();
        var etd = new  Date();
        var today=new Date();
        
        etd.setFullYear(yye,mme-1,dde);
        dob.setFullYear(yyb,mmb-1,ddb)
        
        var y1=today.getYear();
        var y2= dob.getYear();
        
              
        
        if (today < etd)
        {
        alert("From date is after Todays Date");
        document.forms[0].fromdate.value="";
        return false;
        }
        
        if (today < dob)
        {
        alert("To date is after Todays Date");
        document.forms[0].todate.value="";
        return false;
        }
        
        if (dob < etd)
        {
        alert("From date is greater than To date");
        document.forms[0].fromdate.value="";
        return false;
        }

	return true;
}

        </script>
        <script language="JavaScript" src="./UI/popcal/date-picker.js">
        
        </script>


    </head>
    <body>
   
 
        <html:form action="/selectrole.do" onsubmit="return validate_form(this)" method="post">
            
            <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="50%">
                <tr>
                    <th colspan="2">
                        Select For Role Update
                    </th>
                </tr>
                <tr><td>Select Role </td>
                    <td> <html:select property="roleid" >

                        <html:option  value="">--Select One--</html:option>
                        <html:options collection="roleslist" property="roleid" labelProperty="rolename" />
 
                    </html:select></td>
                </tr>
                
                <tr>
                    <th colspan="2" align="center">
                        <html:submit styleClass="button"/>
                        <html:reset styleClass="button"/>
                    </th>
                </tr>
            </table>
<% String message=(String)request.getAttribute("msg"); %>
  <% if(message!=null)  { %> <b><font color="red"><center><%=message %> <% } else {%><br><br> <% } %></center></font></b>

        </html:form>
    </body>
</html>
 <p>&nbsp;</p>
<p>&nbsp;</p>
