<%--
    Document   : RationCardMembersSearch
    Created on : 28-04-2010, 6:18
    Author     : SADAREM
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    <head>
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script >
            function submitValues(){
                var val = document.forms[0].elements['rationCardNo'].value;
                if(document.forms[0].elements["rationCardNo"].value==""){
                    alert("Please Enter RationCard No");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else if(document.forms[0].elements["rationCardNo"].value.length < 15) {
                    alert("Please Enter Valid RationCard No");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                    && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements["rationCardNo"].focus();
                    document.forms[0].elements["rationCardNo"].value="";
                    return false;
                }else {
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                }

            }
        </script>
    </head>

    <body>

        <html:form action="rationcardMembersDetails" focus="rationCardNo">
            <html:hidden property="mode"/>

            <table border="0" cellspacing="0" cellpadding="1" width="90%" align="center" id="grid">
                <p>R4.3 : Ration card Members Details </p>
                <tr>
                    <td height="20" align="left"  >
                <tr>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td  align="center"> RationCard Number<font color="red"></font>
                          <html:text property="rationCardNo" maxlength="15"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">

                        <html:button  value="Get Members" property="sub" onclick="submitValues();"/><br><br>
                    </td>
                </tr>
            </table>
                    <br>

<logic:present name="msg">
    <table align="center"><tr><td><font color="red" size="2"><bean:write name="msg"/></font></td></tr></table>

</logic:present>
<logic:notEmpty name="RationCardMember">
    <table align="center" cellpadding="0" cellspacing="0" border="0" class="table" width="40%">

        <tr>
            <th  align="center">
                Sl No
            </th>

            <th  align="center">
                Member Name
            </th>

            <th  align="center">
                Age
            </th>

        </tr>
        <logic:iterate id="row" name="RationCardMember">

            <tr>
                <td align="center" >${row.slNo}</td>
                <td align="left" >${row.memberName}</td>
                <td align="center" >${row.age}</td>

            </tr>

        </logic:iterate>
    </table>
</logic:notEmpty>

</html:form>
</body>
</html:html>
