<%--
    Document   : AppletAuthorityForPartB
    Created on : Jul 29, 2011, 10:45:05 AM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>:: SADAREM ::</title> 
    </head> 
    <script language="JavaScript">
        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;

        }


    </script>
    <%
            String flow = (String) request.getParameter("selectFlow");%>
    <body>

        <html:form action="/appletAuthority" focus="sadaremCode">
            <input type="hidden" name="mode" value="getValues"/>
        
            <html:hidden property="selectFlow" />
            <logic:present name="msg">
            <tr>
                <td class="label" align="center" colspan="2">
                    <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                </td>
            </tr>
        </logic:present>
       
         <table cellspacing="1" cellpadding="0" class="inputform" align="center" width="50%">
         	<tr>
             <th colspan="2" style="text-align: center;"> <%if (flow != null && flow.equalsIgnoreCase("appellate")) {%>


            Enter SADAREM ID for Second Time Re-Assessment

            <%} else if (flow != null && flow.equalsIgnoreCase("temporay")) {%>

            Enter SADAREM ID for Temporay Certificate

            <%}%></th>
           </tr>
            <tr>
                <td>
                    SADAREM ID :
                </td>
                <td>
                    <input type="text" id="sadaremCode" name="sadaremCode" maxlength="17" size="25" onkeypress="return onlyNumbers();"  autocomplete="off" />
                </td>
            </tr><tr>
                <th colspan="2"  style="text-align: center;">
                    <html:button property="but" value="Submit" styleClass="btn btn-success" onclick="return getData();"/>
                </th>
            </tr>
           
        </table>


    </html:form>
    
    
      <script src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script> 
      <script type="text/javascript">
 
      function getData() 
      { 
    	  
          if($("#sadaremCode").val()=="")
          {
              alert("Please Enter SADAREM Id.");
              $("#sadaremCode").focus();
              return false;
          }
          else if(($("#sadaremCode").val()).length < 17) 
          {
              alert("SADAREM Id must be 17 Digits");
              $("#sadaremCode").val("");
              $("#sadaremCode").focus();
              return false;
          }
          else
          { 
	            document.appletAuthorityFrom.submit();
          }

      }

      </script>
</body>
</html:html>


