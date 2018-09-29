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
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css"/>
        <link rel="stylesheet" href="./DisabilityUITG/css/certificatestyles.css" type="text/css"/>


    </head>

    <script >
        function getData() {

            if(document.forms[0].elements['sadaremCode'].value=="") {
                alert("Please Enter 17 Digit Number");
                document.forms[0].elements['sadaremCode'].focus();
                return false;
            }else if(document.forms[0].elements['sadaremCode'].value.length < "17") {
                alert("SADAREM ID Must Be 17 Digits");
                document.forms[0].elements['sadaremCode'].value="";
                document.forms[0].elements['sadaremCode'].focus();
                return false;
            }
            document.forms[0].mode.value="getValuesForPrints";
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

    <body>

        <html:form action="/appletAuthorityPrints" focus="sadaremCode">
            <input type="hidden" name="mode"/>
            <html:hidden property="catId"/>
            <html:hidden property="catCount"/>
            <logic:present name="msg" >
                <table width="90%">
                    <tr>
                        <td colspan="6">
                            <center>
                                <font color="red" size="2" ><b><bean:write name="msg" /></b></font></center>
                        </td>
                    </tr>
                </table>

            </logic:present>
            <table align="center" border="0" cellpadding="0" cellspacing="1"  class="inputform" width="50%">
                <tr>
                    <th colspan="6" align="center" class="heading">
                        Enter Existing SADAREM ID
                    </th>
                </tr>
                <tr>
                    <td>
                        SADAREM ID :
                    </td>
                    <td >
                        <html:text property="sadaremCode" maxlength="17" size="25" onkeypress="return onlyNumbers();" />
                    </td></tr>
                <tr>
                    <th colspan="4">
                        <html:button property="but" value="Submit" onclick="return getData();"/>
                    </th>
                </tr>
            </table>
            <br/> <br/>
            <table align="center" border="0" cellpadding="0" cellspacing="1"  class="inputform" width="50%">
                <tr><th>
                        Print out Instructions
                    </th>
                </tr>

                <tr align="left">
                    <td class="heading"><font color="red">
                            1. Hearing Impairment - A4 Size</font> </td> </tr>
                <tr align="left"><td  class="heading"><font color="red">
                            2. Mental Illness - A4 Size </font></td> </tr>
                <tr align="left"><td  class="heading"><font color="red">
                            3. Mental Retardation - A4 Size </font></td></tr>
                <tr align="left"> <td   class="heading"><font color="red">
                            4. Physical Impairment - A4 Size </font></td></tr>
                <tr align="left"><td   class="heading"><font color="red">
                            5. Visual Impairment - A4 Size </font></td></tr>
            </table>
        </html:form>
    </body>
</html:html>


