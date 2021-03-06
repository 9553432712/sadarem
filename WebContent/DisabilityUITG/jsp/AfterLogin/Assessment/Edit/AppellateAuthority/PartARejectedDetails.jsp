<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String base64Photo = (String) request.getAttribute("base64Photo");
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <%
                    String personCode = null;
                    if (session.getAttribute("personcode") != null) {
                        personCode = session.getAttribute("personcode").toString();
                    } else {
                        personCode = session.getAttribute("sadaremCodeAu").toString();
                    }

        %>


        <script language="JavaScript">
            function clearValues()
            {
                document.getElementById("othertypeofdisability").value="";
                document.getElementById("referredto").value="";
                document.getElementById("surgery").value="";
                document.getElementById("councellingandguidance").checked="";
                document.getElementById("hearingaid").checked="";
                document.getElementById("behaviourmodification").checked="";
                document.getElementById("phychotherapy").checked="";
                document.getElementById("admissioninpsychiatrichospital").checked="";
                document.getElementById("Physiotherapy").checked="";
                document.getElementById("lowvisionaid").checked="";
                document.getElementById("anyotherneed").value="";
            }
            function refreshAllFields()
            {
                document.getElementById("referredto1").style.visibility='hidden';
                document.getElementById("surgery1").style.visibility='hidden';
                document.getElementById("councelling1").style.visibility='hidden';

                document.getElementById("hearing1").style.visibility='hidden';
                document.getElementById("behaviour1").style.visibility='hidden';
                document.getElementById("phychotherapy1").style.visibility='hidden';
                document.getElementById("admissioninhospital1").style.visibility='hidden';
                document.getElementById("physiotherapy1").style.visibility='hidden';
                document.getElementById("lowvisionaid1").style.visibility='hidden';
                document.getElementById("anyotherneed1").style.visibility='hidden';

                document.getElementById("referredto1").style.display="none";
                document.getElementById("surgery1").style.display="none";
                document.getElementById("councelling1").style.display="none";

                document.getElementById("hearing1").style.display="none";
                document.getElementById("behaviour1").style.display="none";
                document.getElementById("phychotherapy1").style.display="none";
                document.getElementById("admissioninhospital1").style.display="none";
                document.getElementById("physiotherapy1").style.display="none";
                document.getElementById("lowvisionaid1").style.display="none";
                document.getElementById("anyotherneed1").style.display="none";
            }
            function showsubfields()
            {
                var disabilityid=document.getElementById("disabilityIdList").value;
                var x=disabilityid;
                clearValues();
                refreshAllFields();
                if(x=='1')
                {
                    document.getElementById("referredto1").style.visibility='visible';
                    document.getElementById("referredto1").style.display="";
                    document.getElementById("surgery1").style.visibility='visible';
                    document.getElementById("surgery1").style.display="";
                    document.getElementById("physiotherapy1").style.visibility='visible';
                    document.getElementById("physiotherapy1").style.display="";
                    document.getElementById("councelling1").style.visibility='visible';
                    document.getElementById("councelling1").style.display="";
                    document.getElementById("anyotherneed1").style.visibility='visible';
                    document.getElementById("anyotherneed1").style.display="";

                }
                if(x=='2')
                {
                    document.getElementById("referredto1").style.visibility='visible';
                    document.getElementById("referredto1").style.display="";
                    document.getElementById("surgery1").style.visibility='visible';
                    document.getElementById("surgery1").style.display="";
                    document.getElementById("lowvisionaid1").style.visibility='visible';
                    document.getElementById("lowvisionaid1").style.display="";
                    document.getElementById("councelling1").style.visibility='visible';
                    document.getElementById("councelling1").style.display="";
                    document.getElementById("anyotherneed1").style.visibility='visible';
                    document.getElementById("anyotherneed1").style.display="";
                }
                if(x=='3')
                {
                    document.getElementById("referredto1").style.visibility='visible';
                    document.getElementById("referredto1").style.display="";
                    document.getElementById("surgery1").style.visibility='visible';
                    document.getElementById("surgery1").style.display="";
                    document.getElementById("hearing1").style.visibility='visible';
                    document.getElementById("hearing1").style.display="";
                    document.getElementById("councelling1").style.visibility='visible';
                    document.getElementById("councelling1").style.display="";
                    document.getElementById("anyotherneed1").style.visibility='visible';
                    document.getElementById("anyotherneed1").style.display="";
                }
                if(x=='4')
                {
                    document.getElementById("referredto1").style.visibility='visible';
                    document.getElementById("referredto1").style.display="";
                    document.getElementById("surgery1").style.visibility='visible';
                    document.getElementById("surgery1").style.display="";
                    document.getElementById("behaviour1").style.visibility='visible';
                    document.getElementById("behaviour1").style.display="";
                    document.getElementById("councelling1").style.visibility='visible';
                    document.getElementById("councelling1").style.display="";
                    document.getElementById("anyotherneed1").style.visibility='visible';
                    document.getElementById("anyotherneed1").style.display="";
                }
                if(x=='5')
                {
                    document.getElementById("referredto1").style.visibility='visible';
                    document.getElementById("referredto1").style.display="";
                    document.getElementById("phychotherapy1").style.visibility='visible';
                    document.getElementById("phychotherapy1").style.display="";
                    document.getElementById("admissioninhospital1").style.visibility='visible';
                    document.getElementById("admissioninhospital1").style.display="";
                    document.getElementById("councelling1").style.visibility='visible';
                    document.getElementById("councelling1").style.display="";
                    document.getElementById("anyotherneed1").style.visibility='visible';
                    document.getElementById("anyotherneed1").style.display="";

                }

            }
            function validate_required(field,alerttxt)
            {
                with (field)
                {
                    if (value==null||value=="")
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
                with (thisform)
                {

                    if (validate_required(disabilityIdList,"Type of Disability must be selected!")==false)
                    {
                        disabilityIdList.focus();
                        return false
                    }


                    if (validate_required(hospitalname,"Hospital Name must be entered!")==false)
                    {
                        hospitalname.focus();
                        return false
                    }
                    <!--Validation  For Hospital Address-->
                    if (validate_required(hospitaladdress,"Hospital Address must be entered!")==false)
                    {
                        hospitaladdress.focus();
                        return false
                    }

                    <!--Validation  For First Doctor-->
                    if (validate_required(doctor1name,"First Doctor Name must be Entered!")==false)
                    {
                        doctor1name.focus();
                        return false
                    }

                    if (validate_required(doctor1regnumber,"First Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor1regnumber.focus();
                        return false
                    }

                    if (validate_required(doctor1designation,"First Doctor Designation must be Entered!")==false)
                    {
                        doctor1designation.focus();
                        return false
                    }

                    <!--Validation  For Second Doctor-->
                    if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                    {
                        doctor2name.focus();
                        return false
                    }

                    if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor2regnumber.focus();
                        return false
                    }

                    if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                    {
                        doctor2designation.focus();
                        return false
                    }
                    <!--Validation  For Third Doctor-->

                    if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                    {
                        doctor3name.focus();
                        return false
                    }

                    if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor3regnumber.focus();
                        return false
                    }

                    if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                    {
                        doctor3designation.focus();
                        return false
                    }

                    else{
                        return true;
                    }

                }
            }

            function validateForm(thisForm){
                var flag = true;
                flag = validate_form(thisForm);
                if(flag){
                    if (thisForm.getAttribute('submitted')){
                        flag = false;
                        return flag;
                    }else{
                        thisForm.setAttribute('submitted','true');
                        document.getElementById('subDisablButton').disabled = true;
                    }
                }
                return flag;
            }
            function getData(read)
            {

                document.forms[0].partAStatus.value="update";
                document.forms[0].submit();

            }
        </script>
    </head>
    <body onload="showsubfields()">

        <html:form action="PartARejectedDetails.do?insertDisabilityDetails=insertDisabilityDetails"  styleId="partAForm"  onsubmit="return validateForm(this)">
            <input type="hidden" name="selectFlow" value="partAUpdate"/>
            <input type="hidden" name="partAStatus" value="finish"/>

            <center><font  size="3" color="blue" >Please Kindly Make a Note of Person Number and Proceed</font> </center>
            <center><font  size="10" color="red" ><%= personCode%></font></center>

            <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">Rejected Person Disability Details</th>
                </tr>

                <tr>
                    <td >Venue of the Camp</td>
                    <td colspan="4"><html:text styleId="31" property="camp_venue"  name="partAForm" readonly="true" style="width:250px"/></td>
                </tr>
                <tr>
                    <td>Name of the Medical Authority</td>
                    <td colspan="4"> <html:text styleId="31" property="hospitalname" name="partAForm"  readonly="true" style="width:250px"/></td>
                </tr>

                <tr>
                    <td >Address of the Medical Authority</td>
                    <td colspan="4"> <html:text styleId="32" property="hospitaladdress"   name="partAForm" readonly="true" style="width:250px"/></td>
                </tr>
                <tr> <td >1.0 Type of Disability<font color="red"><b>*</b></font></td>
                    <td colspan="4">
                        <html:select  property="disabilityId" styleId="disabilityIdList" onchange="getData(this.value)" style="width:250px">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityId"/>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <th colspan="8" >Doctor Details</th></tr>
                <tr>

                    <!-- start-->
                    <html:hidden  property="specialistprefix" name="partAForm"  />
                    <td align="center"  width="20%">1. Doctor Name<font color="red"><b>*</b></font>  <html:text styleId="33" property="doctor1name" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td width="30%" >Reg. Number<font color="red"><b>*</b></font> <html:text styleId="34" property="doctor1regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td  width="30%">Designation<font color="red"><b>*</b></font> <html:text styleId="35" property="doctor1designation" name="partAForm" readonly="true" style="width:180px"/></td>

                </tr>
                <tr>

                    <!-- start-->
                    <td align="center" width="20%">2. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="36" property="doctor2name" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td width="30%" >Reg. Number<font color="red"><b>*</b></font>  <html:text styleId="37" property="doctor2regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td colspan="2"width="30%" >Designation<font color="red"><b>*</b></font>  <html:text styleId="38" property="doctor2designation" name="partAForm" readonly="true" style="width:180px"/></td>

                </tr>
                <tr>

                    <!-- start-->
                    <td align="center" width="20%">3. Doctor Name<font color="red"><b>*</b></font>  <html:text styleId="39" property="doctor3name" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td width="30%" width="30%">Reg. Number<font color="red"><b>*</b></font>  <html:text styleId="40" property="doctor3regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td colspan="2" width="30%">Designation<font color="red"><b>*</b></font>  <html:text styleId="41" property="doctor3designation" name="partAForm" readonly="true" style="width:180px"/></td>

                </tr>

                <tr> <td width="33%" align="left" >Any other Disability</td>
                    <td colspan="4" width="50%">
                        <html:text maxlength="40" property="othertypeofdisability" style="width:250px"></html:text>
                    </td></tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="referredto1">
                    <td width="33%" align="left" >Referred to</td><td colspan="4" width="50%"><html:text maxlength="40" property="referredto" style="width:250px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="surgery1">
                    <td width="33%" align="left" >Surgery</td><td colspan="4" width="50%"><html:text maxlength="40" property="surgery" style="width:250px"/></td>
                </tr>


                <tr valign="top"  style="visibility:hidden;display:none" id="hearing1">
                    <td width="33%" align="left" >Hearing Aid</td><td colspan="4" width="50%"><html:checkbox property="hearingaid" value="Hearing Aid" style="width:25px"/></td>
                </tr>
                <tr valign="top" style="visibility:hidden;display:none" id="behaviour1">
                    <td width="33%" align="left" >Behaviour Modification</td><td colspan="4" width="50%"><html:checkbox property="behaviourmodification" value="Behaviour Modification" style="width:25px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="phychotherapy1">
                    <td width="33%" align="left" >Phychotherapy</td><td colspan="4" width="50%"><html:checkbox property="phychotherapy" value="Phychotherapy" style="width:25px"/></td>
                </tr>
                <tr valign="top" style="visibility:hidden;display:none" id="admissioninhospital1">
                    <td width="33%" align="left" >Admission in Psychiatric Hospital</td><td colspan="4" width="50%"><html:checkbox property="admissioninpsychiatrichospital" value="Admission in Psychiatric Hospital" style="width:25px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="physiotherapy1">
                    <td width="33%" align="left" >Physiotherapy</td><td colspan="4" width="50%"><html:checkbox property="physiotherapy" value="Physiotherapy" style="width:25px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="lowvisionaid1">
                    <td width="33%" align="left" >Low vision aid</td><td colspan="4" width="50%"><html:checkbox property="lowvisionaid" value="Low vision aid" style="width:25px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="councelling1">
                    <td width="33%" align="left" >Counseling & Guidance</td><td colspan="4" width="50%"><html:checkbox property="councellingandguidance" value="Counseling & Guidance" name="partAForm" style="width:25px"/></td>
                </tr>
                <tr valign="top"  style="visibility:hidden;display:none" id="anyotherneed1">
                    <td width="33%" align="left" >Any Other Need</td><td colspan="4" width="50%"><html:text maxlength="40" property="anyotherneed" style="width:180px" style="width:250px"/></td>
                </tr>
                <% if (base64Photo != null && base64Photo.equalsIgnoreCase("-")) {%>

                <tr align="center">
                    <td width="50%" colspan="5" >To Upload Photo Please <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a></td>
                </tr>
                <%}%>
                <tr>
                    <th colspan="4" align="center">
                        <html:submit value="Submit" styleId="subDisablButton"  styleClass="button"/>&nbsp;&nbsp;
                        <html:reset value="Reset" styleClass="button"/></th> </tr>
            </table>


        </html:form>
    </body>
</html:html>
