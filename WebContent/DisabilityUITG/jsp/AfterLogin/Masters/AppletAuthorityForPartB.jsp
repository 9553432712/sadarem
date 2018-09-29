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
        <title>SADAREM</title>
        
    </head>

    <script >
        function getData() {

            if(document.forms[0].elements['sadaremCode'].value=="") {
                alert("Please Enter 17 Digit Number");
                document.forms[0].elements['sadaremCode'].focus();
                return false;
            }else if(document.forms[0].elements['sadaremCode'].value.length < "17") {
                alert("Person Code Must Be 17 Digits");
                document.forms[0].elements['sadaremCode'].value="";
                document.forms[0].elements['sadaremCode'].focus();
                return false;
            }
            document.forms[0].mode.value="getValues";
            document.forms[0].submit();

        }
    </script>

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
String flow=(String)request.getParameter("selectFlow");%>
    <body>

    <html:form action="/appletAuthority" focus="sadaremCode">
        <input type="hidden" name="mode"/>
        <br/><br/><br/><br/>
        <html:hidden property="selectFlow" />
        <table align="center" border="0" cellpadding="0" cellspacing="0"  class="inputform" width="90%">
            <logic:present name="msg">
                <tr>
                    <td class="label" align="center" colspan="2">
                        <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr>
            </logic:present>
            <tr><td colspan="2" align="center" class="heading">
                <%if(flow!=null && flow.equalsIgnoreCase("appellate")){%>


                    Enter SADAREM ID for Appellate Authority <br/><br/>

                <%}else if(flow!=null && flow.equalsIgnoreCase("temporay")) {%>

                    Enter SADAREM ID for Temporay Certificate <br/><br/>

                <%}%>
           </td> </tr>
            <tr>
                <td class="label" align="right">
                    SADAREM ID :
                </td>
                <td class="label" >
                    <html:text property="sadaremCode" maxlength="17" size="25" onkeypress="return onlyNumbers();" />
                </td>


            </tr>
            <tr>
                <th align="center" colspan="2">
                    <html:button property="but" value="Submit" onclick="return getData();"/>
                </th>
            </tr>
        </table>


    </html:form>
</body>
</html:html>


