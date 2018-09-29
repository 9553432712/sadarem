<%--
    Document   : addDoctor
    Created on : Jul 17, 2012, 11:50:32 AM
    Author     : 693461
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>

<%
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::SADAREM:::</title>
        <script>
            function insertDocotr(){
                
                var d = document.forms[0];

                if(d.disabilityid.value=="0"){
                    alert("Please Select Type of Disability");
                    d.disabilityid.focus();
                    return false;
                }else if(stringTrim(d.docname.value)==""){
                    alert("Please Enter Doctor Name");
                    d.docname.focus();
                    return false;
                }else if(stringTrim(d.docreg.value)==""){
                    alert("Please Enter Doctor Registration Number");
                    d.docreg.focus();
                    return false;
                }else if(stringTrim(d.docdesig.value)==""){
                    alert("Please Enter Doctor Designation");
                    d.docdesig.focus();
                    return false;
                }else {
                    d.mode.value="insertDoctor";
                    d.submit();
                }
               
            }
            function stringTrim(str)
            {
                return str.replace(/^\s+|\s+$/g, "");

            }
            function onlyNumbers(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }


        </script>
    </head>
    <body>
        <html:form action="/campWiseDoctorsCount">
            <html:hidden property="mode"/>
            <html:hidden property="campDate"/>

            <br/>
            <table cellpadding="0" cellspacing="1" align="center" width="65%" border="0" class="inputform">
                <tr class="formhdbg">
                    <th colspan="2" align="center" height="35">Add Doctor</th>
                </tr>
                <tr>
                    <td class="formaltbg">Type of Disability<font color="red" ><b>*</b></font></td>
                    <td class="formaltbg" align="left">
                        <html:select property="disabilityid" style="width:200px">
                            <html:option value="0">--Select--</html:option>
                            <html:optionsCollection property="disabilityList" value="disid" label="disName"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="formaltbg">Doctor Name<font color="red"><b>*</b></font></td>
                    <td class="formaltbg" align="left">
                        <html:text property="docname" size="30" maxlength="150" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="formaltbg">Doctor Registration Number<font color="red"><b>*</b></font></td>
                    <td class="formaltbg" align="left">
                        <html:text property="docreg" size="30" maxlength="50" onkeypress="return onlyNumbers(event);" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="formaltbg">Doctor Designation <font color="red"><b>*</b></font></td>
                    <td class="formaltbg" align="left">
                        <html:text property="docdesig" size="30" maxlength="150" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <th class="formaltbg" align="center" colspan="4">
                        <html:button property="but" value="Submit" onclick="insertDocotr();"/>

                    </th>
                </tr>

            </table>
            <logic:present name="msg" scope="request">
                <script language="javascript" type="text/javascript">
                    alert("${msg}");
                </script>
            </logic:present>
            <logic:present name="closewindow">
                <script>
                  
                    window.close();
                </script>
            </logic:present>
        </html:form>
    </body>
</html>
