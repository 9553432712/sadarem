<%-- 
    Document   : MesevaNewCertificatePersonalDetailsSubmitPensionNo
    Created on : Jan 20, 2014, 11:44:43 AM
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
            function space(evt,thisvalue)
            {
                var number = thisvalue.value;
                var charCode = (evt.which) ? evt.which : event.keyCode
                if(number.length < 1){
                    if(evt.keyCode == 32) {

                        return false;
                    }
                }
                return true;
            }
            function isAlphaNumericHouseNumber(keyCode,name) {

                if (keyCode == 16 )
                    isShift = true;
                var str = name.value;
                if(str.substring(0,1)==" " || str.substring(0,1)==0)
                {
                    name.value ="";
                    name.focus();
                    var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                }else{

                    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 ||  keyCode == 32 || keyCode == 46
                        || keyCode == 189 || keyCode == 191);
                }
                return res;
            }
            function getDetails(){
                if(document.forms[0].elements['pensioncardno'].value==null || document.forms[0].elements['pensioncardno'].value=="") {
                    alert("Please Enter PensionCard No.");
                    document.forms[0].elements['pensioncardno'].focus();
                    return false;
                }else if(document.forms[0].elements['district_id'].value==null || document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District");
                    document.forms[0].elements['district_id'].focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getMesevaNewCertificatePensionDetails";
                    document.forms[0].submit();
                    return true;
                }
                
            }
            function backDetails(){
             
                document.forms[0].action="mesevaNewCertificateRegistration.do?mode=getMesevaNewCertificateRegistration&flag=back";
                document.forms[0].submit();
                
            }

        </script>

    </head>
    <body >
    

    <html:form  action="/mesevaNewCertificateRegistration"  >
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
        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
            <tr>
                <td align="center"  valign="top">
                    <logic:present name="msg">
                        <p id="errmsg">${msg}</p>

                    </logic:present>

                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" class="inputform">
                        <tr>
                            <th  colspan="4" >SADAREM New Certificate Registration Through MEESEVA</th>
                        </tr>
                        <br>
                        <tr>
                            <td colspan="3">Please Enter Pension Number </td>
                        </tr>

                        <tr>
                            <td >PensionCard No.<font color="red"><b>*</b></font></td>
                            <td ><html:text property="pensioncardno"  maxlength="7" onkeypress="return space(event,this)" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,pensioncardno)" /></td>
                        </tr>

                        <tr>
                            <td>District<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select styleId="1" property="district_id"  style="width:160px;" >
                                    <html:option  value="0">- - SELECT - -</html:option>
                                    <html:optionsCollection property="districtlist" label="district_name" value="district_id"  />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="4">
                                <html:submit value="Submit"  styleClass="button"  onclick="return getDetails()" />&nbsp;&nbsp;
                                <html:submit value="Back"  styleClass="button"  onclick="return backDetails()" />&nbsp;&nbsp;
                            </th>
                        </tr>
                    </table>
                   
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
