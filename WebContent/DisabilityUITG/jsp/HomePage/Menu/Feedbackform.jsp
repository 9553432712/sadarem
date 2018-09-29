<%--
    Document   : Feedbackform
    Created on : Nov 26, 2010, 11:42:21 AM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Feedback Page</title>

        <script LANGUAGE="JavaScript">

            function validate_required(field,alerttxt)
            {

                with (field)
                {
                    if (value==null || value=="")
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
                trimFormfields(thisform);
                with (thisform)
                {
                    if (validate_required(name,"Name cannot be empty")==false)
                    {
                        name.focus();

                        return false
                    }
                    if (validate_required(state,"State cannot be empty")==false)
                    {
                        state.focus();
                        return false
                    }

                    if (validate_required(district,"District cannot be empty")==false)
                    {
                        district.focus();
                        return false
                    }

                    if (validate_required(mandal,"Mandal cannot be empty")==false)
                    {
                        mandal.focus();
                        return false
                    }

                    if (validate_required(location,"Location cannot be empty")==false)
                    {
                        location.focus();
                        return false
                    }

                    if (validate_required(feedback,"Feedback cannot be empty")==false)
                    {
                        feedback.focus();
                        return false
                    }


                    return true;

                }

            }
            function trim(str, chars)
            {
                return ltrim(rtrim(str, chars), chars);
            }

            function ltrim(str, chars)
            {
                chars = chars || "\\s";
                return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
            }

            function rtrim(str, chars)
            {
                chars = chars || "\\s";
                return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
            }

            function trimFormfields(thisform)
            {

                thisform.name.value=trim(thisform.name.value," ");
                thisform.state.value=trim(thisform.state.value," ");
                thisform.district.value=trim(thisform.district.value," ");
                thisform.mandal.value=trim(thisform.mandal.value," ");
                thisform.location.value=trim(thisform.location.value," ");
                thisform.feedback.value=trim(thisform.feedback.value," ");

            }

            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }

            <!--Validation  For Email-->
            function validateemail(){
                var address = document.forms[0].email.value;
                if(address!=""){
                    var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

                    if(reg.test(address) == false) {
                        alert('Invalid Email Address');
                        document.forms[0].email.value="";
                        document.forms[0].email.focus();
                        return false;
                    }
                }
            }



        </script>

    </head>

    <body>
        <html:form action="feedbacksubmit.do?insertFeedback=insertFeedback" style="margin:0px; padding:0px;" styleId="websiteform" onsubmit="return validate_form(this)">
 <logic:present name="SUCCESS">

                <center>
                    ${SUCCESS}
                </center>
            </logic:present>
            <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
                <tr>
                    <td align="center" valign="top" height="380">
                       <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="inputform">
                           
                                <th colspan="2"> Feedback Forum</th>
                                <tr>
                                  <td >
                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">

                                        <tr>
                                            <td>Name<font color="red"><b>*</b></font></td>

                                            <td><html:text property="name"  maxlength="50" name="websiteform"/></td>
                                        </tr>
                                        <tr>

                                            <td>State<font color="red"><b>*</b></font></td>

                                            <td><html:text property="state"  maxlength="100" name="websiteform"/></td>
                                        </tr>

                                        <tr>

                                            <td>District<font color="red"><b>*</b></font></td>

                                            <td><html:text property="district"  maxlength="100" name="websiteform"/></td>
                                        </tr>

                                        <tr>

                                            <td>Mandal<font color="red"><b>*</b></font></td>

                                            <td><html:text property="mandal"  maxlength="100" name="websiteform"/></td>
                                        </tr>

                                        <tr>
                                            <td>Location<font color="red"><b>*</b></font></td>

                                            <td><html:text property="location"  maxlength="100" name="websiteform"/></td>
                                        </tr>

                                        <tr>
                                            <td>Phone</td>

                                            <td><html:text property="phone"  maxlength="15" name="websiteform" onkeypress="return isNumberKey(event)"/></td>
                                        </tr>

                                        <tr>
                                            <td>E-mail</td>

                                            <td><html:text property="email"  maxlength="75" onblur="validateemail()" name="websiteform"/></td>
                                        </tr>

                                        <tr>
                                            <td>Suggestions/Feedback<font color="red"><b>*</b></font></td>

                                            <td><html:textarea  cols="30" property="feedback" name="websiteform"></html:textarea></td>
                                        </tr>

                                       

                                        <% String displayStaus = (String) request.getAttribute("rowId");
                                                    if (displayStaus == null) {%>
                                        <tr align="center">
                                            <th colspan="4"> <html:submit  value="Submit" styleClass="button"/>
                                                <html:reset  value="Reset" styleClass="button"/> </th>
                                        </tr>
                                        </table>
                                        <br>
                                   <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="inputform">
                                        <tr>
                                            <th colspan="2"  ><b>Feedback Recieved</b></th>
                                        </tr>
                                        <logic:notEmpty name="feedbackDetailsList" >
                                            <logic:iterate id="feedbackdetails" name="feedbackDetailsList" scope="request" length="2">

                                                <tr>
                                                    <td  colspan="2"><b>Comments:</b>
                                                        <bean:write name="feedbackdetails" property="feedback"/><br>
                                                        <b>by Name:</b>
                                                        <bean:write name="feedbackdetails" property="name"/>,
                                                        <b>Location:</b>
                                                        <bean:write name="feedbackdetails" property="location"/>
                                                    </td>
                                                </tr>

                                            </logic:iterate>
                                                <tr><td  colspan="2"><a href="javascript:void(0);" onclick ="window.open('showAllFeedbackDetails.do?getFeedback=getFeedback')"> More >>> </a></td>
                                            </tr>
                                        </logic:notEmpty>
                                        <logic:empty name="feedbackDetailsList">
                                            <tr><td  align="center">No Feedback Messages</td></tr>
                                        </logic:empty>
                                    </table>
                                </td>
                            </tr>

                        </table></td>
                    <td width="19%" align="center" valign="middle"><img src="./DisabilityUITG/images/feed-ico.gif"></td>
                </tr>
            </table>
            <%--   <%}%> --%>
            <% }%>

        </html:form>
    </body>
</html>