<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<% boolean doctorsUpdate = Boolean.parseBoolean(request.getParameter("doctorsUpdate"));
            String personcode = (String) request.getAttribute("personCode");
            String disabilitytype = (String) request.getAttribute("disabilitytype");
            // String disabilityname = disabilitytype.substring(2, disabilitytype.length()).replace(" ", "");
%>

<script language="javascript">
    function validateform(thisform)
    {
      
        var doctorname1 = document.forms[0].doctorname1.value;
        if(doctorname1==""||doctorname1=='0'||doctorname1==" ")
        {
            alert("Enter Specialist Name")
            document.forms[0].doctorname1.focus();
            return false;
        }
        {
            var registerno1 = document.forms[0].registerno1.value;
            if(registerno1==""||registerno1=='0'||registerno1==" ")
            {
                alert("Enter Reg. Number1")
                document.forms[0].registerno1.focus();
                return false;
            }
            var designation1 = document.forms[0].designation1.value;
            if(designation1==""||designation1=='0'||designation1==" ")
            {
                alert("Enter Designation1")
                document.forms[0].designation1.focus();
                return false;
            }
            var doctorname2 = document.forms[0].doctorname2.value;
            if(doctorname2==""||doctorname2=='0'||doctorname2==" ")
            {
                alert("EnterMedical Board Member")
                document.forms[0].doctorname2.focus();
                return false;
            }

            var registerno2 = document.forms[0].registerno2.value;
            if(registerno2==""||registerno2=='0'||registerno2==" ")
            {
                alert("Enter Reg. Number2")
                document.forms[0].registerno2.focus();
                return false;
            }
            /* if(registerno1==registerno2)
            {
                alert("Reg. Number1 And Reg. Number2 Are Equal")
                document.forms[0].registerno2.focus();
                return false; 
            } */
            var designation2 = document.forms[0].designation2.value;
            if(designation2==""||designation2=='0'||designation2==" ")
            {
                alert("Enter Designation2")
                document.forms[0].designation2.focus();
                return false;
            }
            var doctorname3 = document.forms[0].doctorname3.value;
            if(doctorname3==""||doctorname3=='0'||doctorname3==" ")
            {
                alert("Enter DCHS/Chairman")
                document.forms[0].doctorname3.focus();
                return false;
            }

            var registerno3 = document.forms[0].registerno3.value;
            if(registerno3==""||registerno3=='0'||registerno3==" ")
            {
                alert("Enter Reg. Number3")
                document.forms[0].registerno3.focus();
                return false;
            }
          /*   if(registerno2==registerno3)
            {
                alert("Reg. Number2 And Reg. Number3 Are Equal")
                document.forms[0].registerno3.focus();
                return false;
            }
            if(registerno3==registerno1)
            {
                alert("Reg. Number1 And Reg. Number3 Are Equal")
                document.forms[0].registerno3.focus();
                return false;
            } */
            var designation3 = document.forms[0].designation3.value;
            if(designation3==""||designation3=='0'||designation3==" ")
            {
                alert("Enter Designation3")
                document.forms[0].designation3.focus();
                return false;
            }
            return true;
        }
    }

    function specialistprefixvalidation(){
        var disabilityid = "<%=disabilitytype%>";
        if(disabilityid == "D.Mental Retardation" || disabilityid == "F.Multiple Disability"){
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
<body bgcolor='white' onload="specialistprefixvalidation();">

    <html:form action="/updateDoctorInformation?parameter=updateDoctorInformation" method="post"
               onsubmit="return validateform(this);">
        <%--<html:form action="/updateDoctorInformation?parameter=updateDoctorInformation" method="post"
                 onsubmit="return validateDoctorsInformationForm(this);">--%>
        <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">
            <th colspan='6' class="heading">Update Doctors Details</th>
            <html:errors/>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8"  border="0" class="inputform" width="90%">
            <input type="hidden" name="doctorsUpdate" value="<%=doctorsUpdate%>" />
            <input type="hidden" name="personcode" value="<%=personcode%>" />
            <tr>
                <td>1. Specialist Name<font color="red">*</font></td>
                <td>

                    <div id="specialistprefix" style="visibility:hidden;display:none">
                        <html:select property="specialistprefix">
                            <html:option value="Dr">Dr</html:option>
                            <html:option value="Psychologist">Psychologist</html:option>
                        </html:select>
                    </div>
                    <div id="doctorprefix" style="visibility:hidden;display:none">
                        Dr.
                    </div>

                    <html:text property="doctorname1" maxlength="25"/></td>
                <td>Reg. Number<font color="red">*</font></td>
                <td><html:text property="registerno1" maxlength="25"/></td>
                <td>Designation<font color="red">*</font></td>
                <td><html:text property="designation1" maxlength="140"/></td>  
            </tr>
            <tr>
                <td>2. Medical Board Member<font color="red">*</font></td>
                <td>Dr. <html:text property="doctorname2" maxlength="25"/></td>
                <td>Reg. Number<font color="red">*</font></td>
                <td><html:text property="registerno2" maxlength="25"/></td>
                <td>Designation<font color="red">*</font></td>
                <td><html:text property="designation2" maxlength="140"/></td>
            </tr>
            <tr>
                <td>3. DCHS/Chairman<font color="red">*</font></td>
                <td>Dr. <html:text property="doctorname3" maxlength="25"/></td>
                <td>Reg. Number<font color="red">*</font></td>
                <td><html:text property="registerno3" maxlength="25"/></td>
                <td>Designation<font color="red">*</font></td>
                <td><html:text property="designation3" maxlength="140"/></td>
            </tr>

            <tr rowspan='2' style="visibility:hidden;display:none" >
                <td colspan='6' align="center">Type of Disability
                    <html:select property="typeofdisability">
                        <html:option value="select">-Select-</html:option>
                        <html:option value="Locomotor/OH">Locomotor/OH</html:option>
                        <html:option value="Visual Impairment">Visual Impairment</html:option>
                        <html:option value="Hearing Impairment">Hearing Impairment</html:option>
                        <html:option value="Mental Retardation">Mental Retardation</html:option>
                        <html:option value="Mental Illness">Mental Illness</html:option>
                        <html:option value="Multiple Disability">Multiple Disability</html:option>
                    </html:select>
                </td>
            </tr>

            <tr>
                <td class="userName" colspan="6"><font color="red">NOTE:</font> Don't Enter Dr. Word in Doctor Name Text box</td>
            </tr>
            <tr>
                <th colspan="6">
                    <html:submit property="submit" value="Update"  styleClass="button"/>
                    <html:reset property="reset" value="Reset" styleClass="button"/>
                </th>
            </tr>
        </table>

    </html:form>
</body>
