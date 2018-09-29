<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        
        <script>
            
            
            function clearValues(disabilityid)
            {
                var x=disabilityid;
                document.getElementById("othertypeofdisability").value="";
                document.getElementById("referredto").value="";
                document.getElementById("councellingandguidance").checked="";
                document.getElementById("anyotherneed").value="";
             
                if(x=="1")
                {
                    document.getElementById("surgery").value="";
                    document.getElementById("Physiotherapy").checked="";
                }
                if(x=="2")
                {
                    document.getElementById("surgery").value="";
                    document.getElementById("lowvisionaid").checked="";
                }
                if(x=="3")
                {
                    document.getElementById("surgery").value="";
                    document.getElementById("hearingaid").checked="";
                }
                if(x=="4")
                {
                    document.getElementById("surgery").value="";
                    document.getElementById("behaviourmodification").checked="";
                }
                if(x=="5")
                {
                    document.getElementById("admissioninpsychiatrichospital").checked="";
                    document.getElementById("phychotherapy").checked="";
                }
             
            
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
            function showsubfields(disabilityid)
            {
                var x=disabilityid;
            
           
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
            function passValue()
            {
                var x=document.getElementById("disabilityId").value;
                showsubfields(x);
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
                     return true
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
    <body onload="passValue()">

        <html:form action="PartARejectedDetailsUpdate.do?updateDisabilityDetails=updateDisabilityDetails" styleId="partAForm"  onsubmit="return validateForm(this)" >
            <input type="hidden" name="partAStatus" value="finish"/><br><br><br><br>


            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                    <th align="center" class="heading">Update Rejected Person Disability Details</th>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">

                <tr>
                    <td width="32%" class="label">Venue of the Camp</td>
                    <td> <html:text styleId="31" property="camp_venue" size="50" name="partAForm" readonly="true"/></td>

                </tr>
                <tr>
                    <td class="label" width="32%">Name of the Medical Authority</td>

                    <td><html:text styleId="31" property="hospitalname" size="50" name="partAForm"  readonly="true"/></td>
                </tr>
                <tr>
                    <td class="label">Address of the Medical Authority</td>
                    <td> <html:text styleId="32" property="hospitaladdress" size="50"  name="partAForm" readonly="true"/>
                    </td>
                </tr>
                <tr> <td class="label">1.0 Type of Disability<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select  property="disabilityId" styleId="disabilityIdList"  onchange="getData(this.value)">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityId"/>
                        </html:select>
                    </td>
                </tr>

            </table>




            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td colspan="3"><font size="2"><b>Doctor Details:</b></font></td>
                </tr>
                <tr>

                    <!-- start-->
                    <html:hidden  property="specialistprefix" name="partAForm"  />
                    <td  class="label">1. Doctor Name<font color="red" size="3"><b>*</b></font>
                        <html:text styleId="33" property="doctor1name" name="partAForm" readonly="true"/></td>
                    <td class="label">Reg. Number<font color="red" size="3"><b>*</b></font>
                        <html:text styleId="34" property="doctor1regnumber" name="partAForm" readonly="true"/></td>
                    <td class="label">Designation<font color="red" size="3"><b>*</b></font>
                        <html:text styleId="35" property="doctor1designation" name="partAForm" readonly="true"/></td>

                </tr>

                <tr>

                    <!-- start--> 
                    <td class="label">2. Doctor Name<font color="red"><b>*</b></font>
                        <html:text styleId="36" property="doctor2name" name="partAForm" readonly="true"/></td>
                    <td class="label">Reg. Number<font color="red"><b>*</b></font>
                        <html:text styleId="37" property="doctor2regnumber" name="partAForm" readonly="true"/></td>
                    <td class="label">Designation<font color="red"><b>*</b></font>
                        <html:text styleId="38" property="doctor2designation" name="partAForm" readonly="true"/></td>

                </tr>

                <tr>

                    <!-- start--> 
                    <td class="label" >3. Doctor Name<font color="red"><b>*</b></font>
                        <html:text styleId="39" property="doctor3name" name="partAForm" readonly="true"/></td>
                    <td class="label">Reg. Number<font color="red"><b>*</b></font>
                        <html:text styleId="40" property="doctor3regnumber" name="partAForm" readonly="true"/></td>
                    <td class="label">Designation<font color="red"><b>*</b></font>
                        <html:text styleId="41" property="doctor3designation" name="partAForm" readonly="true"/></td>

                </tr>
            </table>   




            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">

                <tr> <td width="33%" align="left"><font size="2"><b>Any other Disability</b></font></td>
                    <td colspan="4" width="50%"><html:text maxlength="40" property="othertypeofdisability" ></html:text></td>
                </tr>
                <tr style="visibility:hidden;display:none" id="referredto1">
                    <td width="33%" class="label">Referred to</td>
                    <td colspan="4" width="50%"><html:text maxlength="40" property="referredto" /></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="surgery1">
                    <td width="33%" class="label">Surgery</td>
                    <td colspan="4" width="50%"><html:text maxlength="40" property="surgery" /></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="hearing1">
                    <td width="33%" class="label">Hearing Aid</td>
                    <td colspan="4" width="50%"><html:checkbox property="hearingaid" value="Hearing Aid"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="behaviour1">
                    <td width="33%" class="label">Behaviour Modification</td>
                    <td colspan="4" width="50%"><html:checkbox property="behaviourmodification" value="Behaviour Modification"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="phychotherapy1">
                    <td width="33%" class="label">Phychotherapy</td>
                    <td colspan="4" width="50%"><html:checkbox property="phychotherapy" value="Phychotherapy"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="admissioninhospital1">
                    <td width="33%" class="label">Admission in Psychiatric Hospital</td>
                    <td colspan="4" width="50%"><html:checkbox property="admissioninpsychiatrichospital" value="Admission in Psychiatric Hospital"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="physiotherapy1">
                    <td width="33%" class="label">Physiotherapy</td>
                    <td colspan="4" width="50%"><html:checkbox property="physiotherapy" value="Physiotherapy"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="lowvisionaid1">
                    <td width="33%" class="label">Low vision aid</td>
                    <td colspan="4" width="50%"><html:checkbox property="lowvisionaid" value="Low vision aid"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="councelling1">
                    <td width="33%" class="label">Counseling & Guidance</td>
                    <td colspan="4" width="50%"><html:checkbox property="councellingandguidance" value="Counseling & Guidance"/></td>
                </tr>
                <tr  style="visibility:hidden;display:none" id="anyotherneed1">
                    <td width="33%" class="label">Any other need</td>
                    <td colspan="4" width="50%"><html:text maxlength="40" property="anyotherneed" /></td>
                </tr>

                <%--<tr align="center">
                    <td width="50%" colspan="2" class="label">To Upload Photo Please<a href="javascript:void(0);"
                                                                                       onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");><u>Click here</u></a></td>
                </tr>--%>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td colspan="4" align="center">
                        <html:submit value="Update"  styleId="subDisablButton"  styleClass="button" />&nbsp;&nbsp;
                        <html:reset value="Reset" styleClass="button"/></td>
                </tr>
            </table>  


        </html:form>
    </body>
</html:html>
