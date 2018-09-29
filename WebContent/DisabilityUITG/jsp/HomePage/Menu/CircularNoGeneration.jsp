<%-- 
    Document   : CircularNoGeneration
    Created on : 30 Dec, 2013, 10:39:13 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<script src="./DisabilityUITG/js/Ajax.js"></script>
<script src="./DisabilityUITG/js/Validation.js"></script>
<script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
<%int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function saveDetails() {
               
                if(document.forms[0].elements['issueingDate'].value=="") {
                    alert("Please Enter Issuing Date");
                    document.forms[0].elements['issueingDate'].value="";
                    document.forms[0].elements['issueingDate'].focus();
                    return false;
                }else if(document.forms[0].elements['subject'].value=="") {
                    alert("Please Enter Subject");
                    document.forms[0].elements['subject'].value="";
                    document.forms[0].elements['subject'].focus();
                    return false;
                }else if(document.forms[0].elements['reference'].value=="") {
                    alert("Please Enter Reference");
                    document.forms[0].elements['reference'].value="";
                    document.forms[0].elements['reference'].focus();
                    return false;
                }

                document.forms[0].mode.value="saveDetails";
                document.forms[0].submit();
            }

            function goForData(id) {
                document.forms[0].action ="./cmpUploads.do?mode=getValuesWhenClickOnEdit&RowId="+id;
                document.forms[0].submit();
            }

        </script>
    </head>
    <body>
        <html:form action="/circularNoGeneration">
            <html:hidden property="mode"/>
            <html:hidden property="documentType" value="Circular"/>

            <table align="center" cellpadding="0" cellspacing="0" border="0" width="60%">
                <tr>
                    <td valign="top">
                        <table align="left" cellpadding="0" cellspacing="0" border="0" class="inputform">

                            <th >Circular Number Generation</th>


                            <tr>
                                <td>
                                    <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" >

                                        <tr align="right">
                                            <td>
                                                Circular Number
                                            </td>
                                            <td align="left">
                                                <html:text property="memoNumber" size="44" onkeydown="return space(event,this)"/>
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <td>
                                                Issuing Date
                                            </td>
                                            <td align="left">
                                                <html:text property="issueingDate" readonly="true" size="40"/>
                                                <a  href="javascript:show_calendar('forms[0].issueingDate');"
                                                    onmouseover="window.status='Date Picker';return true;"
                                                    onmouseout="window.status='';return true;">
                                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <td>
                                                Subject
                                            </td>
                                            <td align="left">
                                                <html:textarea property="subject" onkeypress="return inputLimiter(event,'Letters');" onkeydown="return space(event,this)" cols="100" rows="5"/>
                                            </td>
                                        </tr>
                                        <tr align="right">
                                            <td>
                                                Reference
                                            </td>
                                            <td align="left">
                                                <html:textarea property="reference" onkeypress="return inputLimiter(event,'Letters');" onkeydown="return space(event,this)" cols="100" rows="5"/>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th colspan="2" align="center">
                                                <html:button property="subprop" value="Submit" onclick="saveDetails();"/>
                                            </th>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td align="center">

                        <table align="center" cellpadding="0" cellspacing="1" border="0" width="96%" class="inputform">
                            <tr>
                                <th>
                                    Sno
                                </th>
                                <th>
                                    Document Type
                                </th>
                                <th>
                                    Circular No
                                </th>
                                <th>
                                    Issuing Date
                                </th>
                                <th>
                                    Subject
                                </th>
                                <th>
                                    Reference
                                </th>
                                <th>
                                    View
                                </th>
                            </tr>
                            <logic:empty name="cmpUploadsDetails">
                                <tr align="center">
                                    <td colspan="10" align="center"><font color="red" size="2">Circulars Details Not Available</font></td>
                                </tr>
                            </logic:empty>
                            <logic:notEmpty name="cmpUploadsDetails">
                                <logic:iterate id="ccla" name="cmpUploadsDetails" scope="request">
                                    <tr>
                                        <td width="2px" valign="top">
                                            <center> <%=i++%>.</center>
                                        </td>
                                        <td valign="top">
                                            ${ccla.DocumentType}
                                        </td>
                                        <td valign="top">
                                            ${ccla.CMPNumber}
                                        </td>

                                        <td valign="top">
                                            ${ccla.IssuingDate}
                                        </td>
                                        <td width="250px" valign="top">
                                            ${ccla.Subject}
                                        </td>
                                        <td valign="top">
                                            ${ccla.Reference}
                                        </td>
                                        <td>
                                            ${ccla.UploadFile}
                                        </td>
                                        <!-- <td width="12%">
                                             <center>    <a href="./cmpUploads.do?mode=getValuesWhenClickOnEdit&RowId=${ccla.RowId}"><img src="images/Edit.png" alt="Edit" ></a>&nbsp; <a href="#" onclick="goForDelete(${ccla.RowId});" id="hide${row.RowId}"><img src="images/delete.png" alt="delete"  ></a></center>
                                         </td>-->
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </table>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
