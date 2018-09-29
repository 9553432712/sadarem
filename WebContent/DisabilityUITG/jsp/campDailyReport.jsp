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

<% ArrayList data = (ArrayList) request.getAttribute("campDataList");
            int size = data.size();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
    <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <script>
        
        function submitValues(){

                var chosen = "";
                var len = document.forms[0].campData.length;
                var elems = document.forms[0].campData;
                if(len>0){
                    for(var i=0;i<len;i++){
                        if(elems[i].checked){
                           
                            chosen = elems[i].value;
                        }
                    }
                }
                if(chosen==""){
                  alert("Select the CampID");
                    return false;
                }

             

            if(document.forms[0].elements['disabiltyId'].value=="0") {

                alert("Please Select Disability!");
                document.forms[0].elements['disabiltyId'].focus();
                return false;
            }

            if(document.forms[0].elements['pwdAttended'].value=="") {
                    
                alert("Please Enter pwdAttended!");
                document.forms[0].elements['pwdAttended'].focus();
                return false;
            }
            if(document.forms[0].elements['pwdAssessed'].value=="") {
                   
                alert("Please Enter pwdAssessed!");
                document.forms[0].elements['pwdAssessed'].focus();
                return false;
            }
            if(document.forms[0].elements['doctorsCount'].value=="") {
                    
                alert("Please Enter  NO Of DoctorsCount!");
                document.forms[0].elements['doctorsCount'].focus();
                return false;
            }
            
            else {
           
                document.forms[0].mode.value="submitValues";
                document.forms[0].submit();
            }
            
        }

         


        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;

            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;

        }

        function currentdate(){

            var currentTime = new Date()
            var month = currentTime.getMonth() + 1
            var day = currentTime.getDate()
            var year = currentTime.getFullYear()

            var todaydate=day+"/"+month+"/"+year;

            document.getElementById("campdate").value=todaydate;

        }
    </script>

    <body onload="currentdate()">
        <html:form  action="/campDailyReport"  method="post">

            <html:hidden property="mode"/>

            <logic:present name="msg">
                <font color="blue" size="3"><center><bean:write name="msg"/></center></font>
            </logic:present>
            <table align="center" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" colspan="5">
                        <b><h5>Camp Daily Assessment Report</h5></b>
                    </td>
                </tr>

                <td class="label" valign="middle">


                    <logic:notEmpty name="campDataList">
                        <table  align="center" cellspacing="0" border="0" cellpadding="0" class="innerTable" width="90%">
                            <tr>
                                <td class="formhdbg" align="center">
                                    Action
                                </td>
                                <td class="formhdbg" align="center">
                                    Name
                                </td >
                                <td class="formhdbg" align="center">
                                    Address
                                </td >
                                <td class="formhdbg" align="center">
                                    Venue Name
                                </td>
                            </tr>
                            <logic:iterate  name="campDataList" id="row">
                                <tr>
                                    <td class="formaltbg" align="center">

                                        <html:radio property="campData" value="${row.camp_id}" styleId="campDataId${row.camp_id}"/>
                                    </td>

                                    <td class="formaltbg">
                                        ${row.name}
                                    </td>
                                    <td class="formaltbg">
                                        ${row.address}
                                    </td>
                                    <td class="formaltbg">
                                        ${row.venuename}
                                    </td>

                                </tr>
                            </logic:iterate>
                        </table>
                    </logic:notEmpty>

                    <br/>
                    <table  align="center" cellspacing="0" border="0" cellpadding="0" class="innerTable" width="90%">

                        <tr>
                            <td class="formhdbg" align="center">Camp  Date
                            <td class="formhdbg" align="center">
                                <html:text property="campdate" size="36" readonly="true"/>
                                <a  href="javascript:show_calendar('forms[0].campdate');"
                                    onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;">
                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>

                            </td>

                            <td class="formhdbg" align="center">
                                Disability:<html:select property="disabiltyId">
                                    <html:option value="0">---Select---</html:option>
                                    <html:optionsCollection property="disabilityList" label="disabilityName" value="disabiltyId"/>
                                </html:select>
                            </td>
                        </tr>

                        <table  align="center" cellspacing="0" border="0" cellpadding="0" class="innerTable" width="90%">
                            <tr>

                                <td class="formhdbg" align="center">
                                    No Of Pwd's Attended <html:text property="pwdAttended" onkeypress="return onlyNumbers();" maxlength="4"/>
                                </td>

                                <td class="formhdbg" align="center">
                                    No Of Pwd's Assessed  <html:text property="pwdAssessed" onkeypress="return onlyNumbers();" maxlength="4"/>
                                </td>
                                <td class="formhdbg" align="center">
                                    No Of Doctors Count <html:text property="doctorsCount" onkeypress="return onlyNumbers();" maxlength="1"/>
                                </td>



                            </tr>
                            <tr align="center">
                                <td class="formhdbg" align="center" colspan="3">
                                    <html:button property="button" value="Submit" onclick="submitValues();"/>
                                </td>
                            </tr>

                        </table>
                    </table>


                </td>




            </table><br/>



        </html:form>
    </body>
</html>
