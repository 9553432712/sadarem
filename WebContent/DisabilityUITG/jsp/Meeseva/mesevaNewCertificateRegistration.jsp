<%-- 
    Document   : MesevaNewCertificateRegistration
    Created on : Jan 17, 2014, 12:42:28 PM
    Author     : 310926
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
       <script>
            

            function getDetails(){
                
                document.forms[0].mode.value="getMesevaNewCertificatePersonalDetails";
                document.forms[0].submit();
                return true;
            }
        </script>

    </head>
    <body >
    

    <html:form  action="mesevaNewCertificateRegistration.do?mode=getMesevaNewCertificatePersonalDetails"  method="post"  >
        <html:hidden property="mode"/>
        <html:hidden property="SCAUserId"/>
        <html:hidden property="uniqueNo"/>
        <html:hidden property="loginId"/>
        <html:hidden property="channelId"/>
        <html:hidden property="serviceid"/>
        <html:hidden property="scaPassword"/>
        <html:hidden property="applicationNo"/>
        <html:hidden property="meesevaFlag"/>
        <html:hidden property="requestId"/>
        <html:hidden property="encryptedString"/>
        <html:hidden property="checkSum"/>



        <table width="99%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td>
                   
                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" class="inputform">
                        <tr>
                            <th  colspan="4" >SADAREM New Certificate Registration Through MEESEVA</th>
                        </tr>
                        <br>
                        <tr>
                        <td  c colspan="2">Please select Below Criteria </td>
                        </tr>

                        <tr>
                            <td >With Pension Number<font color="red" size="2"><b>*</b></font></td>
                            <td >
                               <%-- <input type="radio" name="pensionNo"  value="Yes" onclick="getDetails()" style="width: 25px"/>Yes--%>
                                <input type="radio" name="pensionNo"  value="No" onclick="getDetails()" style="width: 25px"/>No</td>
                        </tr>


                    </table>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
