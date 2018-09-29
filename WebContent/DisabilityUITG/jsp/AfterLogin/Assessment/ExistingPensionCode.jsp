<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%String message = null;
            String restrictPartA = null;
            restrictPartA = request.getParameter("restrictPartA");
            if (restrictPartA == null || "".equals(restrictPartA)) {
                restrictPartA = (String) request.getAttribute("restrictPartA");
            }
%>
<html:html>
    <script type="text/javascript">
        function validate_required(field)
        {
            with (field)
            {
                if (value==null||value=="")
                {alert("Pension Number must be filled out!");return false;}
                var numericExpression = /^[0-9-A]+$/;
                if(!value.match(numericExpression))
                {alert("Pension Number must be Integer");return false;}
                if(value != null && value !=""){
                    if(value > 2147483647){
                        alert(" Enter Valid Pension Number");return false;
                    }
                }

            }
        }

        function isNumberKey(evt)
        {
            var number = document.getElementById("pensioncode").value;
            var charCode = (evt.which) ? evt.which : event.keyCode
            if(number.length < 1){
                if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 65)
                    return false;
            }else{
                if (charCode > 31 && (charCode < 48 || charCode > 57) )
                    return false;
            }

            return true;
        }
        function goBack()
        {
            document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
            document.forms[0].submit();
        }


        function goBack()
        {
            document.forms[0].action="DecisionForPWDPensionRestrictPartA.do";
            document.forms[0].submit();
        }

        function validateForm(thisForm){
            var flag = true;
            flag = validate_form(thisForm);
            if(flag){
                if (thisForm.getAttribute('submitted')){
                    flag = false;
                    return flag;
                }else{
                    thisForm.setAttribute('submitted','true');
                }
            }
            return flag;
        }
        function validate_form(thisform)
        {
            with (thisform)
            {
                if (validate_required(pensioncode)==false)
                {pensioncode.focus();return false;}
                if (validate_required(pensioncode)==false)
                {pensioncode.focus();return false;}
                document.getElementById("mode").value='Data';
                return true;
            }
        }
    </script>
    <body onload="document.partAForm.pensioncode.focus()">
        <html:form action="getExistingData.do?parameter=getExistingData" styleId="partAForm" onsubmit="return validateForm(this)" method="post">
          <html:hidden property="mode" />
            <p id="#errmsg">  <%  message = (String) request.getAttribute("msg");
                            String personCode = (String) request.getAttribute("existingpersoncode");
                            String pensionnumber = (String) request.getAttribute("pensionnumber");%>
                        <% if (message != null) {%> <%=message%> <% }%></p>
            
             <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">
                 <tr><th colspan="4">Enter Existing Pension Number</th></tr>
                <tr>
                    <%if ("true".equals(restrictPartA)) {%>
                <input type="hidden" name="restrictPartA" value="true"/>
                <%} else if ("pensionNumberRestrictPartA".equals(restrictPartA)) {%>
                <input type="hidden" name="restrictPartA" value="pensionNumberRestrictPartA"/>
                <%}%>
            </tr>
          
            <tr>
                <td>Pension Number</td>
                <td><input type="text" onkeypress="return isNumberKey(event)" maxlength="10" name="pensioncode"/></td>
               
            </tr>
            <tr>
                <th colspan="4"><html:submit value="Submit" styleClass="button"/>&nbsp;&nbsp;
               
                    <%if ("true".equals(restrictPartA)) {%>
                    <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>
                    <%}%>
                </th>
            </tr>
           
        </table>
             <table  align="center" cellspacing="0" cellpadding="0" >
            <%if (personCode != null && !"".equals(personCode)) {%>
            <tr>
                <td align="center" class="labelBlue"><font  size="4">This pension number <font  size="5" color="red"> <%=pensionnumber%> </font>
                        is already entered please make sure that you make a note of SADAREM ID for this pension number and use update facility:- </font>
                 
                    <font  size="10" color="red"> <%=personCode%> </font>
                <td>
            </tr>
            <%}%>
        </table>
    </html:form>
</body>
</html:html>
