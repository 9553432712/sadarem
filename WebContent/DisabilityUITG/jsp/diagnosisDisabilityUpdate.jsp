<%--
    Document   : RationCardServiceReport
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
   

    <script>
        function submitValues(){

            if(document.forms[0].elements['personCode'].value==""){
                alert("Please enter SADAREM ID");
                document.forms[0].elements['personCode'].focus();
                document.forms[0].elements['personCode'].value="";
                return false;
            }else{
                document.forms[0].mode.value="diagnosisDisabilityDetails";
                document.forms[0].submit();
            }
            
        }

        function updateValues(){

            if(document.forms[0].elements['diagnosisDisability'].value==""){
                alert("Please enter SADAREM ID");
                document.forms[0].elements['diagnosisDisability'].focus();
                document.forms[0].elements['diagnosisDisability'].value="";
                return false;
            }else{
                document.forms[0].mode.value="updateDiagnosisDisabilityDetails";
                document.forms[0].submit();
            }
        }
        function diagnosisDisabilityValidation()
        {
            if (document.forms[0].diagnosisDisability.value.length > 150)
                document.forms[0].diagnosisDisability.value = document.forms[0].diagnosisDisability.value.substring(0,149);
           
        }
        
    </script>

    <body>
        <html:form  action="/diagnosisDisabilityUpdate"  method="post">

            <html:hidden property="mode"/>
            <br/>
            <logic:present name="personCode">
                <center><font color="red">${personCode}</font></center>
            </logic:present>
            <logic:present name="msg">
                <center><font color="blue">${msg}</font></center>
            </logic:present>

            <logic:present name="InvalidPersonCodeNumber">
                <center><font color="red">${InvalidPersonCodeNumber}</font></center>
            </logic:present>

            <table cellpadding="1" cellspacing="8" align="center" width="30%" class="innerTable">
                <br/>
                <tr align="center">
                    <td class="label" valign="middle" width="8%" colspan="3">
                        SADAREM ID <html:text property="personCode" maxlength="17"/>
                    </td>
                </tr>
                <tr align="center">
                    <td class="label" align="center" colspan="2">
                        <html:button property="but" value="Get Report" onclick="submitValues();"/>
                    </td>
                </tr>

            </table>
            <br/>
            <logic:present name="diagnosisDisabilityDetails">
                <table cellpadding="1" cellspacing="8" align="center"  width="50%" class="innerTable">
                    <tr>
                        <td  class="label">Diagnosis Of Disability [Only 150 Characters]</td>
                        <td>
                            <html:textarea rows="5" cols="30" property="diagnosisDisability" style="height:50px;" onkeydown="diagnosisDisabilityValidation();" onkeyup="diagnosisDisabilityValidation();"></html:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="label" valign="middle" width="8%" align="center" colspan="2">
                            <html:button property="but" value="Update" onclick="updateValues();"/>
                        </td>
                    </tr>
                </table>
            </logic:present>
        </html:form>
    </body>
</html>
