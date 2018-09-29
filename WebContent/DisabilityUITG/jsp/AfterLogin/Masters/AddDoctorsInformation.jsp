<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page  import="java.util.*"%>
<script type="text/javascript">
    function alpha(e) {
        var k;
        document.all ? k = e.keyCode : k = e.which;
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32 || k==46);
    }
    function isNumberKey(evt)
    {         var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return true;
        return false;
    }
    function validateform(thisform)
    {
        var typeofdisability = document.forms[0].typeofdisability.value;
        if (typeofdisability == "")
        {
            alert("\n Select Type of Disability.")
            document.forms[0].typeofdisability.focus();
            return false;
        }
        var doctorname1 = document.forms[0].doctorname1.value;
        if(doctorname1==""||doctorname1=='0'||doctorname1==" ")
        {
            alert("Enter the Specialist Name ")
            document.forms[0].doctorname1.focus();
            return false;
        }
        {
            var registerno1 = document.forms[0].registerno1.value;
            if(registerno1==""||registerno1=='0'||registerno1==" ")
            {
                alert("Enter the Reg. Number1")
                document.forms[0].registerno1.focus();
                return false;
            }
            var designation1 = document.forms[0].designation1.value;
            if(designation1==""||designation1=='0'||designation1==" ")
            {
                alert("Enter the Designation1  ")
                document.forms[0].designation1.focus();
                return false;
            }
            var doctorname2 = document.forms[0].doctorname2.value;
            if(doctorname2==""||doctorname2=='0'||doctorname2==" ")
            {
                alert("Enter the Medical Board Member Name ")
                document.forms[0].doctorname2.focus();
                return false;
            }

            var registerno2 = document.forms[0].registerno2.value;
            if(registerno2==""||registerno2=='0'||registerno2==" ")
            {
                alert("Enter the Reg. Number2")
                document.forms[0].registerno2.focus();
                return false;
            }
           /*  if(registerno1==registerno2)
            {
                alert("Register No1 and Reg. Number2 are equal")
                document.forms[0].registerno2.focus();
                return false;
            }  */
            var designation2 = document.forms[0].designation2.value;
            if(designation2==""||designation2=='0'||designation2==" ")
            {
                alert("Enter the Designation2")
                document.forms[0].designation2.focus();
                return false;
            }
            var doctorname3 = document.forms[0].doctorname3.value;
            if(doctorname3==""||doctorname3=='0'||doctorname3==" ")
            {
                alert("Enter the DCHS/Chairman Name ")
                document.forms[0].doctorname3.focus();
                return false;
            }

            var registerno3 = document.forms[0].registerno3.value;
            if(registerno3==""||registerno3=='0'||registerno3==" ")
            {
                alert("Enter the Reg. Number3")
                document.forms[0].registerno3.focus();
                return false;
            }
          /*   if(registerno2==registerno3)
            {
                alert("Reg. Number2 and Reg. Number3 are equal")
                document.forms[0].registerno3.focus();
                return false;
            }
            if(registerno3==registerno1)
            {
                alert("Reg. Number1 and Reg. Number3 are equal")
                document.forms[0].registerno3.focus();
                return false;
            } */
            var designation3 = document.forms[0].designation3.value;
            if(designation3==""||designation3=='0'||designation3==" ")
            {
                alert("Enter the Designation3")
                document.forms[0].designation3.focus();
                return false;
            }
            return true;
        }
    }

    function specialistprefixvalidation(){
        var disabilityid = document.getElementById("1").selectedIndex;
        if(disabilityid == 4 || disabilityid == 6){
            document.getElementById("specialistprefix").style.visibility = "Visible";
            document.getElementById("specialistprefix").style.display = "Inline";
            document.getElementById("doctorprefix").style.visibility = "hidden";
            document.getElementById("doctorprefix").style.display = "none";

        }else{
            document.getElementById("specialistprefix").style.visibility = "hidden";
            document.getElementById("specialistprefix").style.display = "none";
            document.getElementById("doctorprefix").style.visibility = "Visible";
            document.getElementById("doctorprefix").style.display = "Inline";
        }

    }
</script>

<body onload="specialistprefixvalidation();">
    <html:form action="/adddoctorsinformation?parameter=insertDoctorInformation" method="post" onsubmit="return validateform(this);">
        <center>
            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform"  width="90%" >
                <th colspan="6" class="heading">Add Doctors Details</th>
                <html:errors/>
                <tr><td colspan='6' style="text-align: center" class="labelBlue">Type Of Disability<font color="red">*</font>
                        <html:select property="typeofdisability" styleId="1" onchange="specialistprefixvalidation()">
                            <html:option value="">-Select-</html:option>
                            <logic:present name="disabilitytype" scope="request">
                                <%
                                            ArrayList a = (ArrayList) request.getAttribute("disabilitytype");
                                            Iterator i = a.iterator();
                                            while (i.hasNext()) {
                                                String str = (String) i.next();
                                %>
                                <html:option value="<%= str%>"><%= str%></html:option>
                                <%
                                            }
                                %>
                            </logic:present>

                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td>1. Specialist Name<font color="red">*</font></td>
                    <td width="20">
                        <div id="specialistprefix" style="visibility:hidden;display:none">
                            <html:select property="specialistprefix">
                                <html:option value="Dr">Dr</html:option>
                                <html:option value="Psychologist">Psychologist</html:option>
                            </html:select>
                        </div>
                        <div id="doctorprefix" style="visibility:hidden;display:none">
                            Dr.
                        </div>

                        <html:text property="doctorname1" onkeypress="return alpha(event)" maxlength="25"/></td>
                    <td>Reg. Number<font color="red">*</font></td>
                    <td><html:text property="registerno1" maxlength="25"/></td>
                    <td>Designation<font color="red">*</font></td>
                    <td><html:text property="designation1" onkeypress="return isNumberKey(event)" maxlength="140"/></td>
                </tr><br>
                <tr>
                    <td>2. Medical Board Member<font color="red">*</font></td>
                    <td>Dr. <html:text property="doctorname2" onkeypress="return alpha(event)" maxlength="25"/></td>
                    <td>Reg. Number<font color="red">*</font></td>
                    <td><html:text property="registerno2" maxlength="25"/></td>
                    <td>Designation<font color="red">*</font></td>
                    <td><html:text property="designation2" onkeypress="return isNumberKey(event)" maxlength="140"/></td>
                </tr>
                <tr>
                    <td>3. DCHS/Chairman<font color="red">*</font></td>
                    <td>Dr. <html:text property="doctorname3" onkeypress="return alpha(event)" maxlength="25"/></td>
                    <td>Reg. Number<font color="red">*</font></td>
                    <td><html:text property="registerno3" maxlength="25"/></td>
                    <td>Designation<font color="red">*</font></td>
                    <td><html:text property="designation3" onkeypress="return isNumberKey(event)" maxlength="140"/></td>
                </tr>
                <!--  <tr class="tbl_bg2_content"><td colspan='6' align='center'><b>Hospital Name:&nbsp;&nbsp;
                <html:text property="hospitalname" maxlength="25"/>&nbsp;&nbsp;Hospital Address:&nbsp;&nbsp;
                <html:text property="hospitaladdress" maxlength="75"/></td></tr> -->

                <tr>
                    <td class="userName" colspan="6"><font color="red">NOTE:</font> Don't Enter Dr. Word in Doctor Name Text box</td>
                </tr>

                <tr>
                    <th colspan="6">
                        <html:submit property="submit" value="Submit" styleClass="button" /> <html:reset property="reset" value="Reset" styleClass="button"/>
                    </th>
                </tr>
            </table><br>

        </center><%--<html:javascript formName="DoctorsInformationForm"/>--%>
    </html:form>
</body>
