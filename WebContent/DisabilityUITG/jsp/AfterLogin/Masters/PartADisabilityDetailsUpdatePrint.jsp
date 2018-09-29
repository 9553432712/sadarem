<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ page import="java.util.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <%Integer olddisablityid = (Integer) request.getAttribute("olddisablityid");

                    Integer aa = (Integer) session.getAttribute("disabilityid");%>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="JavaScript">



            function displayAppropriate()
            {
                var theDay =document.partAForm.disabilityId.value;

                switch (theDay)

                {
                    case "1":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            document.getElementById("doctor1name").readOnly = true;
                            document.getElementById("doctor1regnumber").readOnly = true;
                            document.getElementById("doctor1designation").readOnly = true;
                            document.getElementById("doctor2name").readOnly = true;
                            document.getElementById("doctor2regnumber").readOnly = true;
                            document.getElementById("doctor2designation").readOnly = true;
                            document.getElementById("doctor3name").readOnly = true;
                            document.getElementById("doctor3regnumber").readOnly = true;
                            document.getElementById("doctor3designation").readOnly = true;
                            document.getElementById("singledoctor").style.visibility = "Visible";
                            document.getElementById("singledoctor").style.display = "Inline";

                            break;
                        }
                    case "2":{
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            //document.getElementById("conditionofdisabilityother").style.visibility = "hidden";
                            //document.getElementById("conditionofdisabilityother").style.display = "none";
                            document.getElementById("causeOfDisabilityVisual").style.visibility = "Visible";
                            document.getElementById("causeOfDisabilityVisual").style.display = "Inline";

                            document.getElementById("causeOfDisabilityOther").style.visibility = "hidden";
                            document.getElementById("causeOfDisabilityOther").style.display = "none";
                            document.getElementById("doctor1name").readOnly = true;
                            document.getElementById("doctor1regnumber").readOnly = true;
                            document.getElementById("doctor1designation").readOnly = true;
                            document.getElementById("doctor2name").readOnly = true;
                            document.getElementById("doctor2regnumber").readOnly = true;
                            document.getElementById("doctor2designation").readOnly = true;
                            document.getElementById("doctor3name").readOnly = true;
                            document.getElementById("doctor3regnumber").readOnly = true;
                            document.getElementById("doctor3designation").readOnly = true;
                            document.getElementById("singledoctor").style.visibility = "Visible";
                            document.getElementById("singledoctor").style.display = "Inline";

                            break;
                        }

                    case "3":{
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            document.getElementById("doctor1name").readOnly = true;
                            document.getElementById("doctor1regnumber").readOnly = true;
                            document.getElementById("doctor1designation").readOnly = true;
                            document.getElementById("doctor2name").readOnly = true;
                            document.getElementById("doctor2regnumber").readOnly = true;
                            document.getElementById("doctor2designation").readOnly = true;
                            document.getElementById("doctor3name").readOnly = true;
                            document.getElementById("doctor3regnumber").readOnly = true;
                            document.getElementById("doctor3designation").readOnly = true;
                            document.getElementById("singledoctor").style.visibility = "Visible";
                            document.getElementById("singledoctor").style.display = "Inline";
                            break;
                        }
                    case "4":{
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                            //document.getElementById("mentalretd").style.visibility = "Visible";
                            //document.getElementById("mentalretd").style.display = "Inline";
                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            document.getElementById("doctor1name").readOnly = true;
                            document.getElementById("doctor1regnumber").readOnly = true;
                            document.getElementById("doctor1designation").readOnly = true;
                            document.getElementById("doctor2name").readOnly = true;
                            document.getElementById("doctor2regnumber").readOnly = true;
                            document.getElementById("doctor2designation").readOnly = true;
                            document.getElementById("doctor3name").readOnly = true;
                            document.getElementById("doctor3regnumber").readOnly = true;
                            document.getElementById("doctor3designation").readOnly = true;
                            document.getElementById("singledoctor").style.visibility = "Visible";
                            document.getElementById("singledoctor").style.display = "Inline";
                            break;
                        }
                    case "5":{
                            //document.getElementById("mentalillness").style.visibility = "Visible";
                            //document.getElementById("mentalillness").style.display = "Inline";
                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            document.getElementById("doctor1name").readOnly = true;
                            document.getElementById("doctor1regnumber").readOnly = true;
                            document.getElementById("doctor1designation").readOnly = true;
                            document.getElementById("doctor2name").readOnly = true;
                            document.getElementById("doctor2regnumber").readOnly = true;
                            document.getElementById("doctor2designation").readOnly = true;
                            document.getElementById("doctor3name").readOnly = true;
                            document.getElementById("doctor3regnumber").readOnly = true;
                            document.getElementById("doctor3designation").readOnly = true;
                            document.getElementById("singledoctor").style.visibility = "Visible";
                            document.getElementById("singledoctor").style.display = "Inline";
                            break;
                        }
                    case "6":{
                            document.getElementById("locomotor").style.visibility = "Visible";
                            document.getElementById("locomotor").style.display = "Inline";
                            document.getElementById("visual").style.visibility = "Visible";
                            document.getElementById("visual").style.display = "Inline";
                            document.getElementById("hearing").style.visibility = "Visible";
                            document.getElementById("hearing").style.display = "Inline";
                            document.getElementById("commonCondition").style.visibility = "Visible";
                            document.getElementById("commonCondition").style.display = "Inline";
                            //document.getElementById("mentalretd").style.visibility = "Visible";
                            //document.getElementById("mentalretd").style.display = "Inline";
                            //document.getElementById("mentalillness").style.visibility = "Visible";
                            //document.getElementById("mentalillness").style.display = "Inline";

                            document.getElementById("conditionofdisabilityvisual").style.visibility = "Visible";
                            document.getElementById("conditionofdisabilityvisual").style.display = "Inline";
                            document.getElementById("causeOfDisabilityVisual").style.visibility = "Visible";
                            document.getElementById("causeOfDisabilityVisual").style.display = "Inline";
                            document.getElementById("doctor1name").readOnly = false;
                            document.getElementById("doctor1regnumber").readOnly = false;
                            document.getElementById("doctor1designation").readOnly = false;
                            document.getElementById("doctor2name").readOnly = false;
                            document.getElementById("doctor2regnumber").readOnly = false;
                            document.getElementById("doctor2designation").readOnly = false;
                            document.getElementById("doctor3name").readOnly = false;
                            document.getElementById("doctor3regnumber").readOnly = false;
                            document.getElementById("doctor3designation").readOnly = false;
                            document.getElementById("multipledoctor").style.visibility = "Visible";
                            document.getElementById("multipledoctor").style.display = "Inline";
                            break;
                        }
                    case "7":{
                            break;
                        }

                }
            }



            function deselect(read)
            {

                document.partAForm.disabilityLocoSubIds.value = null;

            }
            function getData(read)
            {
                document.partAForm.partAStatus.value="update";
                document.partAForm.submit();
            }




            function disabile()
            {
                if(document.forms[0].education.selectedIndex == 1)
                {
                    document.forms[0].edustatus.disabled = true;
                    document.forms[0].edustatus.value = 0;
                }
                else
                {
                    document.forms[0].edustatus.disabled = false;
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
                var flag = true;
                with (thisform)
                {

                    var temporary=document.partAForm.conditiondisability.value;
                    var years=document.partAForm.yearsfortemporary.value;
                    if(temporary==3)
                    {
                        if(years==0)
                        {
                            alert ("Please enter period of Reassessment!");
                            document.partAForm.yearsfortemporary.focus();
                            flag = false;
                            return flag;
                        }
                    }
                    if (validate_required(disabilityId,"Type of Disability must be selected!")==false)
                    {
                        disabilityId.focus();
                        flag = false;
                        return flag;
                    }
                    if (validate_required(camp_venue,"Venue of the Camp must be entered!")==false)
                    {
                        camp_venue.focus();
                        flag = false;
                        return flag;
                    }

                    if (validate_required(hospitalname,"Name of the Medical Authority must be entered!")==false)
                    {
                        hospitalname.focus();
                        flag = false;
                        return flag;
                    }
                    /* <!--Validation  For Hospital Address--> */
                    if (validate_required(hospitaladdress,"Address of the Medical Authority must be entered!")==false)
                    {
                        hospitaladdress.focus();
                        flag = false;
                        return flag;
                    }

                    var multilpledisabilityid =document.partAForm.disabilityId.value;
                    if(multilpledisabilityid == 6)
                    {


                       /*  <!--Validation  For Second Doctor--> */
                        if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                        {
                            doctor2name.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                        {
                            doctor2regnumber.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                        {
                            doctor2designation.focus();
                            flag = false;
                            return flag
                        }
                        <!--Validation  For Third Doctor-->

                        if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                        {
                            doctor3name.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                        {
                            doctor3regnumber.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                        {
                            doctor3designation.focus();
                            flag = false;
                            return flag
                        }
                    }


                    else
                    { <!--Validation  For First Doctor-->
                        if(validate_required(doctor1name,"First Doctor Name must be Entered!")==false)
                        {
                            doctor1name.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor1regnumber,"First Doctor Reg.Number must be Entered!")==false)
                        {
                            doctor1regnumber.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor1designation,"First Doctor Designation must be Entered!")==false)
                        {
                            doctor1designation.focus();
                            flag = false;
                            return flag
                        }

                        <!--Validation  For Second Doctor-->
                        if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                        {
                            doctor2name.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                        {
                            doctor2regnumber.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                        {
                            doctor2designation.focus();
                            flag = false;
                            return flag
                        }
                        <!--Validation  For Third Doctor-->

                        if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                        {
                            doctor3name.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                        {
                            doctor3regnumber.focus();
                            flag = false;
                            return flag
                        }

                        if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                        {
                            doctor3designation.focus();
                            flag = false;
                            return flag
                        }
                    }


            <%--         <!--Validation  For First Doctor-->
                     if (validate_required(doctor1name,"First Doctor Name must be Entered!")==false)
                     {
                         doctor1name.focus();
                         flag = false;
                          return flag;
                     }

                    if (validate_required(doctor1regnumber,"First Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor1regnumber.focus();
                        flag = false;
                         return flag;
                    }

                    if (validate_required(doctor1designation,"First Doctor Designation must be Entered!")==false)
                    {
                        doctor1designation.focus();
                        flag = false;
                         return flag;
                    }

                    <!--Validation  For Second Doctor-->
                    if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                    {
                        doctor2name.focus();
                        flag = false;
                         return flag;
                    }

                    if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor2regnumber.focus();
                        flag = false;
                         return flag;
                    }

                    if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                    {
                        doctor2designation.focus();
                       flag = false;
                         return flag;
                    }
                    <!--Validation  For Third Doctor-->

                    if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                    {
                        doctor3name.focus();
                        flag = false;
                         return flag;
                    }

                    if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                    {
                        doctor3regnumber.focus();
                       flag = false;
                         return flag;
                    }

                    if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                    {
                        doctor3designation.focus();
                        flag = false;
                         return flag;
                    }
            --%>




                                //   var mentalRetardationId =document.partAForm.disabilityId.value;
                                //   if(mentalRetardationId=="4")
                                //     {
                                //   if (validate_required(document.getElementById("mentalRetardationSubType"),"Menta Retardation must be selected!")==false)
                                //   {
                                //   document.getElementById("mentalRetardationSubType").focus();
                                //   return false
                                //   }
                                //   }

                                //  var hearingImparimentId =document.partAForm.disabilityId.value;
                                //  if(hearingImparimentId=="3")
                                //    {
                                //  if (validate_required(document.getElementById("hearingSubTypes"),"Hearing Impariment must be selected!")==false)
                                //  {
                                //  document.getElementById("hearingSubTypes").focus();
                                //  return false
                                //  }
                                //  }

                                //   var mentalIllnessId =document.partAForm.disabilityId.value;
                                //  if(mentalIllnessId=="5")
                                //     {
                                //   if (validate_required(document.getElementById("mentalIllinessSubTypes"),"Mental Illness must be selected!")==false)
                                //   {
                                //   document.getElementById("mentalIllinessSubTypes").focus();
                                //   return false
                                //   }
                                //   }


                                var hearingImparimentId =document.partAForm.disabilityId.value;
                                if(hearingImparimentId=="1")
                                {
                                    if (validate_required(document.getElementById("locomotorSubTypes"),"Locomoto SubTypes must be selected!")==false)
                                    {
                                        document.getElementById("locomotorSubTypes").focus();
                                        flag = false;
                                        return flag;
                                    }
                                    if (validate_required(document.getElementById("locomotorSubSubTypes"),"Part Involved must be selected!")==false)
                                    {
                                        document.getElementById("locomotorSubSubTypes").focus();
                                        flag = false;
                                        return flag;
                                    }
                                }


                                var theDay =document.partAForm.disabilityId.value;
                                if(theDay=="2")
                                {
                                    if (validate_required(document.getElementById("visualSubTypes"),"Visual Disability must be selected!")==false)
                                    {
                                        document.getElementById("visualSubTypes").focus();
                                        flag = false;
                                        return flag;
                                    }
                                    if (validate_required(document.getElementById("conditiondisabilityvisual"),"Condition of Disability (visual) must be selected!")==false)
                                    {
                                        document.getElementById("conditiondisabilityvisual").focus();
                                        flag = false;
                                        return flag;
                                    }
                                }
                                else
                                {
                                    if (validate_required(document.getElementById("conditiondisabilityvisual"),"Condition of Disability must be selected!")==false)
                                    {
                                        document.getElementById("conditiondisabilityvisual").focus();
                                        flag = false;
                                        return flag;
                                    }

                                }

                            }
                            return flag;
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
                                }
                            }
                            return flag;
                        }



                        function years()

                        {
                            var x=document.getElementById("conditiondisability").value;
                            if(x==1 ||x==2){
                                document.forms[0].yearsfortemporary.disabled = true;
                                document.forms[0].yearsfortemporary.value=0;
                            }
                            else
                                document.forms[0].yearsfortemporary.disabled = false;
                        }
                        function yearsenable()
                        {
                            var x=document.getElementById("conditiondisability").value;

                            if(x == 3)
                            {
                                document.forms[0].yearsfortemporary.disabled = false;
                            }
                            else
                            {
                                document.forms[0].yearsfortemporary.disabled = true;
                            }

                        }

                        function disabile()
                        {
                            var x=document.getElementById("conditiondisability").value;
                            if(x == 3)
                            {
                                document.forms[0].yearsfortemporary.disabled = false;
                            }
                            else
                            {
                                document.forms[0].yearsfortemporary.disabled = true;
                                document.forms[0].yearsfortemporary.value="0";
                            }

                        }
                        function validatediagnosisofdisability(){
                            var iChars = "!@#$%^&*()+=-[]\\\'`~;/{}|\":<>?1234567890";
                            var diagnosisofdisability=document.partAForm.diagnosisofdisability.value;
                            for (var i = 0; i < diagnosisofdisability.length; i++) {
                                if (iChars.indexOf(diagnosisofdisability.charAt(i)) != -1) {
                                    alert ("Please Enter Charecters only in  Disability/Impairment.");
                                    document.partAForm.diagnosisofdisability.value="";
                                    document.partAForm.diagnosisofdisability.focus();
                                    return false;
                                }
                            }
                        }


                        function alpha(e) {
                            var k;
                            document.all ? k = e.keyCode : k = e.which;
                            return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32);
                        }
                        function isNumberKey(evt)
                        {         var charCode = (evt.which) ? evt.which : event.keyCode
                            if (charCode > 31 && (charCode < 48 || charCode > 57))
                                return true;
                            return false;
                        }
                        function validatecheckbox()
                        {
                            if(document.getElementById("OH").checked == true)
                            {
                                document.getElementById("doctornameOH").readOnly = false;
                                document.getElementById("doctorregnumberOH").readOnly = false;
                                document.getElementById("doctordesignationOH").readOnly = false;

                            } else {
                                document.getElementById("doctornameOH").readOnly = true;
                                document.getElementById("doctorregnumberOH").readOnly = true;
                                document.getElementById("doctordesignationOH").readOnly = true;
                                document.getElementById("doctornameOH").value = "";
                                document.getElementById("doctorregnumberOH").value = "";
                                document.getElementById("doctordesignationOH").value = "";
                            }


                            if(document.getElementById("VI").checked == true)
                            {
                                document.getElementById("doctornameVI").readOnly = false;
                                document.getElementById("doctorregnumberVI").readOnly = false;
                                document.getElementById("doctordesignationVI").readOnly = false;

                            }
                            else {
                                document.getElementById("doctornameVI").readOnly = true;
                                document.getElementById("doctorregnumberVI").readOnly = true;
                                document.getElementById("doctordesignationVI").readOnly = true;
                                document.getElementById("doctornameVI").value = "";
                                document.getElementById("doctorregnumberVI").value = "";
                                document.getElementById("doctordesignationVI").value = "";

                            }

                            if(document.getElementById("HI").checked == true)
                            {
                                document.getElementById("doctornameHI").readOnly = false;
                                document.getElementById("doctorregnumberHI").readOnly = false;
                                document.getElementById("doctordesignationHI").readOnly = false;

                            }
                            else {
                                document.getElementById("doctornameHI").readOnly = true;
                                document.getElementById("doctorregnumberHI").readOnly = true;
                                document.getElementById("doctordesignationHI").readOnly = true;
                                document.getElementById("doctornameHI").value = "";
                                document.getElementById("doctorregnumberHI").value = "";
                                document.getElementById("doctordesignationHI").value = "";
                            }

                            if(document.getElementById("MR").checked == true)
                            {
                                document.getElementById("doctornameMR").readOnly = false;
                                document.getElementById("doctorregnumberMR").readOnly = false;
                                document.getElementById("doctordesignationMR").readOnly = false;
                                document.getElementById("mdspecialistprefix").style.visibility = "Visible";
                                document.getElementById("mdspecialistprefix").style.display = "Inline";


                            }
                            else {
                                document.getElementById("doctornameMR").readOnly = true;
                                document.getElementById("doctorregnumberMR").readOnly = true;
                                document.getElementById("doctordesignationMR").readOnly = true;
                                document.getElementById("doctornameMR").value = "";
                                document.getElementById("doctorregnumberMR").value = "";
                                document.getElementById("doctordesignationMR").value = "";
                                document.getElementById("mdspecialistprefix").style.visibility = "hidden";
                                document.getElementById("mdspecialistprefix").style.display = "none";
                            }

                            if(document.getElementById("MI").checked == true)
                            {
                                document.getElementById("doctornameMI").readOnly = false;
                                document.getElementById("doctorregnumberMI").readOnly = false;
                                document.getElementById("doctordesignationMI").readOnly = false;

                            }
                            else {
                                document.getElementById("doctornameMI").readOnly = true;
                                document.getElementById("doctorregnumberMI").readOnly = true;
                                document.getElementById("doctordesignationMI").readOnly = true;
                                document.getElementById("doctornameMI").value = "";
                                document.getElementById("doctorregnumberMI").value = "";
                                document.getElementById("doctordesignationMI").value = "";
                            }

                        }

                        function multipleDoctors()
                        {
                            var multilpledisabilityid =document.partAForm.disabilityId.value;
                            if(multilpledisabilityid == 6)
                            {
                                var ohdoctor = document.getElementById("OH");
                                var vidoctor = document.getElementById("VI");
                                var hidoctor = document.getElementById("HI");
                                var mrdoctor = document.getElementById("MR");
                                var midoctor = document.getElementById("MI");

                                var array1 = new Array();
                                array1[0] = ohdoctor;
                                array1[1] = vidoctor;
                                array1[2] = hidoctor
                                array1[3] = mrdoctor;
                                array1[4] = midoctor;
                                var j = 0;
                                var doctornameOH = document.getElementById("doctornameOH");
                                var doctorregnumberOH = document.getElementById("doctorregnumberOH");
                                var doctordesignationOH = document.getElementById("doctordesignationOH");
                                var doctornameVI = document.getElementById("doctornameVI");
                                var doctorregnumberVI = document.getElementById("doctorregnumberVI");
                                var doctordesignationVI = document.getElementById("doctordesignationVI");
                                var doctornameHI = document.getElementById("doctornameHI");
                                var doctorregnumberHI = document.getElementById("doctorregnumberHI");
                                var doctordesignationHI = document.getElementById("doctordesignationHI");
                                var doctornameMR = document.getElementById("doctornameMR");
                                var doctorregnumberMR = document.getElementById("doctorregnumberMR");
                                var doctordesignationMR = document.getElementById("doctordesignationMR");
                                var doctornameMI = document.getElementById("doctornameMI");
                                var doctorregnumberMI = document.getElementById("doctorregnumberMI");
                                var doctordesignationMI = document.getElementById("doctordesignationMI");
                                for(var i = 0; i<array1.length; i++)
                                {
                                    var checkedValue = array1[i];
                                    if(checkedValue.checked == true)
                                    {
                                        j++;
                                    }

                                }
                                if(j < 2)
                                {
                                    alert("Please Check Doctors Check box more than one");
                                    ohdoctor.focus();
                                    return false;
                                }


                                if(ohdoctor != null){
                                    if ( (ohdoctor.checked == true) && (doctornameOH.value == ""))
                                    {
                                        alert("Please enter OH Doctor Name");
                                        doctornameOH.focus();
                                        return false;
                                    }
                                    if ( (ohdoctor.checked == true) && (doctorregnumberOH.value == ""))
                                    {
                                        alert("Please enter OH Doctor Reg. Number");
                                        doctorregnumberOH.focus();
                                        return false;
                                    }
                                    if ( (ohdoctor.checked == true) && (doctordesignationOH.value == ""))
                                    {
                                        alert("Please enter OH Doctor Designation");
                                        doctordesignationOH.focus();
                                        return false;
                                    }
                                    if ( (ohdoctor.checked == true))
                                    {

                                        if (validate_required(document.getElementById("locomotorSubTypes"),"Locomoto SubTypes must be selected!")==false)
                                        {
                                            document.getElementById("locomotorSubTypes").focus();
                                            return false;
                                        }
                                        if (validate_required(document.getElementById("locomotorSubSubTypes"),"Part Involved must be selected!")==false)
                                        {
                                            document.getElementById("locomotorSubSubTypes").focus();
                                            return false;
                                        }

                                    }
                                }

                                if(vidoctor != null){
                                    if ( (vidoctor.checked == true) && (doctornameVI.value == ""))
                                    {
                                        alert("Please enter VI Doctor Name");
                                        doctornameVI.focus();
                                        return false;
                                    }
                                    if ( (vidoctor.checked == true) && (doctorregnumberVI.value == ""))
                                    {
                                        alert("Please enter VI Doctor Reg. Number");
                                        doctorregnumberVI.focus();
                                        return false;
                                    }
                                    if ( (vidoctor.checked == true) && (doctordesignationVI.value == ""))
                                    {
                                        alert("Please enter VI Doctor Designation");
                                        doctordesignationVI.focus();
                                        return false;
                                    }
                                    if ( (vidoctor.checked == true))
                                    {
                                        if (validate_required(document.getElementById("visualSubTypes"),"Visual Disability must be selected!")==false)
                                        {
                                            document.getElementById("visualSubTypes").focus();
                                            flag = false;
                                            return flag;
                                        }
                                    }
                                }

                                if(hidoctor != null){
                                    if ( (hidoctor.checked == true) && (doctornameHI.value == ""))
                                    {
                                        alert("Please enter HI Doctor Name");
                                        doctornameHI.focus();
                                        return false;
                                    }
                                    if ( (hidoctor.checked == true) && (doctorregnumberHI.value == ""))
                                    {
                                        alert("Please enter HI Doctor Reg. Number");
                                        doctorregnumberHI.focus();
                                        return false;
                                    }
                                    if ( (hidoctor.checked == true) && (doctordesignationHI.value == ""))
                                    {
                                        alert("Please enter HI Doctor Designation");
                                        doctordesignationHI.focus();
                                        return false;
                                    }
                                }

                                if(mrdoctor != null){
                                    if ( (mrdoctor.checked == true) && (doctornameMR.value == ""))
                                    {
                                        alert("Please enter MR Doctor Name");
                                        doctornameMR.focus();
                                        return false;
                                    }
                                    if ( (mrdoctor.checked == true) && (doctorregnumberMR.value == ""))
                                    {
                                        alert("Please enter MR Doctor Reg. Number");
                                        doctorregnumberMR.focus();
                                        return false;
                                    }
                                    if ( (mrdoctor.checked == true) && (doctordesignationMR.value == ""))
                                    {
                                        alert("Please enter MR Doctor Designation");
                                        doctordesignationMR.focus();
                                        return false;
                                    }
                                }

                                if(midoctor != null){
                                    if ( (midoctor.checked ==true ) && (doctornameMI.value == ""))
                                    {
                                        alert("Please enter MI Doctor Name");
                                        doctornameMI.focus();
                                        return false;
                                    }
                                    if ( (midoctor.checked == true) && (doctorregnumberMI.value == ""))
                                    {
                                        alert("Please enter MI Doctor Reg. Number");
                                        doctorregnumberMI.focus();
                                        return false;
                                    }
                                    if ( (midoctor.checked == true) && (doctordesignationMI.value == ""))
                                    {
                                        alert("Please enter MI Doctor Designation");
                                        doctordesignationMI.focus();
                                        return false;
                                    }
                                }

                                return true;
                            }
                        }

                        <!-- script for captal at first letter-->
                        function capitalizeMe(obj) {
                            var val = obj.value;
                            var newVal = '';
                            if(val != ""){
                                val = val.split(' ');
                                for(var c=0; c < val.length; c++) {
                                    newVal += val[c].substring(0,1).toUpperCase() +
                                        val[c].substring(1,val[c].length) + ' ';
                                }
                            }
                            obj.value = newVal;
                        }

                        var isShift=false;
                        function keyUPOld(keyCode)
                        {
                            if(keyCode==32)
                                isShift = false;
                        }
                        function isAlpha(keyCode,name)
                        {
                            if (keyCode == 16)
                                isShift = true;
                            var str = name.value;
                            if(str.substring(0,1)==" ")
                            {
                                name.value ="";
                                name.focus();
                                return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                            }else{
                                return ((keyCode >= 65 && keyCode <= 90) || keyCode == 8 || keyCode == 32 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)
                            }
                        }

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
        </script>

            <script>
	function disableForm(theform) {
		if (document.all || document.getElementById) {
			for (i = 0; i < theform.length; i++) {
			var formElement = theform.elements[i];
                       // alert("name========="+formElement.value);
				if (true) {
					formElement.disabled = true;
				}
			}
		}
	}
</script>




 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
    </head>


    <body onload="displayAppropriate(this.value),years();">


        <html:form action="PartADisabilityDetailsPrint.do"  styleId="partAForm"  >
            <input type="hidden" name="partAStatus" value="finish"/>
            <input type="hidden" name="previousdisabilityid" value="<%=olddisablityid%>"/>

            <input type="hidden" name="<%= Constants.TOKEN_KEY%>"
                   value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" >

            <table align="center" cellspacing="0" cellpadding="0" width="45%">
            <tr>
                <td align="center"><font color="blue"><b>SADAREM ID : <%=session.getAttribute("personcode")%></b></font></td>
            </tr>
       </table><br/>

            <table  align="center" cellspacing="0" cellpadding="1" class="innerTable" width="100%">
                <tr>
                    <td align="center" class="heading">Disability Details</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="1" class="innerTable1" width="100%">
                <tr>
                    <td class="label" width="32%">Venue of the Camp</td>
                    <td><html:text  property="camp_venue"  size="50" name="partAForm" readonly="true"/></td>

                </tr>
                <tr>
                    <td class="label">Name of the Medical Authority</td>
                    <td><html:text styleId="31" property="hospitalname" size="50" name="partAForm" readonly="true"/></td>
                </tr>
                <tr>

                    <td class="label">Address of the Medical Authority</td>
                    <td> <html:text styleId="32" size="50" property="hospitaladdress"  name="partAForm" readonly="true"/></td>
                </tr>


                <tr>
                    <td class="labelBlue">1.0 Type of Disability<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select  property="disabilityId" disabled="true">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityId"  />
                        </html:select>
                    </td></tr>

            </table>




            <table  align="center" cellspacing="0" cellpadding="1" class="innerTable1" width="100%">
                <tr>
                    <td colspan="3" class="labelBlue">Doctor Details</td>
                </tr>
            </table>
            <div id="singledoctor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="100%" border="1">
                    <tr>

                        <!-- start-->
                        <html:hidden  property="specialistprefix" name="partAForm"  />
                        <td class="label">1. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="33" property="doctor1name" name="partAForm" onkeypress="return alpha(event)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number<font color="red"><b>*</b></font> <html:text styleId="34" property="doctor1regnumber" name="partAForm" /></td>
                        <td  class="label">Designation<font color="red"><b>*</b></font> <html:text styleId="35" property="doctor1designation" name="partAForm" onkeypress="return isNumberKey(event)" maxlength="25" /></td>


                    </tr>
                </table>
            </div>
            <div id="multipledoctor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="100%">
                    <tr>

                        <!-- start-->
                        <td class="label"><html:checkbox property="ohdoctor" styleId="OH" value="1" onclick="validatecheckbox(this.value)"/>OH Doctor Name <html:text styleId="42" property="doctornameOH" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameOH);" onkeypress="return space(event,this)"
                                                                                                                                                    onchange="capitalizeMe(this)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number <html:text styleId="43" property="doctorregnumberOH" name="partAForm" onkeypress="return space(event,this)"/></td>
                        <td  class="label">Designation <html:text styleId="44" property="doctordesignationOH" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationOH);" maxlength="25" onkeypress="return space(event,this)"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td class="label"><html:checkbox property="vidoctor" styleId="VI" value="2" onclick="validatecheckbox(this.value)"/>VI Doctor Name <html:text styleId="45" property="doctornameVI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameVI);" onkeypress="return space(event,this)"
                                                                                                                                                    onchange="capitalizeMe(this)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number <html:text styleId="46" property="doctorregnumberVI" name="partAForm" onkeypress="return space(event,this)"/></td>
                        <td  class="label">Designation <html:text styleId="47" property="doctordesignationVI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationVI);" maxlength="25" onkeypress="return space(event,this)"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td class="label"><html:checkbox property="hidoctor" styleId="HI" value="3" onclick="validatecheckbox(this.value)"/>HI Doctor Name <html:text styleId="48" property="doctornameHI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameHI);" onkeypress="return space(event,this)"
                                                                                                                                                    onchange="capitalizeMe(this)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number <html:text styleId="49" property="doctorregnumberHI" name="partAForm" onkeypress="return space(event,this)"/></td>
                        <td  class="label">Designation <html:text styleId="50" property="doctordesignationHI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationHI);" maxlength="25" onkeypress="return space(event,this)"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td class="label"><html:checkbox property="mrdoctor" styleId="MR" value="4" onclick="validatecheckbox(this.value)"/>MR Doctor Name

                            <html:text styleId="51" property="doctornameMR" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMR);" onkeypress="return space(event,this)"
                                       onchange="capitalizeMe(this)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number <html:text styleId="52" property="doctorregnumberMR" name="partAForm" onkeypress="return space(event,this)"/></td>
                        <td  class="label">Designation <html:text styleId="53" property="doctordesignationMR" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationMR);" maxlength="25" onkeypress="return space(event,this)"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td class="label"><html:checkbox property="midoctor" styleId="MI" value="5" onclick="validatecheckbox(this.value)"/>MI Doctor Name <html:text styleId="54" property="doctornameMI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                                                                                                                                                    onchange="capitalizeMe(this)" maxlength="25"/> </td>
                        <td  class="label">Reg. Number <html:text styleId="55" property="doctorregnumberMI" name="partAForm" onkeypress="return space(event,this)"/></td>
                        <td  class="label">Designation <html:text styleId="56" property="doctordesignationMI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationMI);" maxlength="25" onkeypress="return space(event,this)"/></td>


                    </tr>
                </table>
            </div>
              <div  style="visibility:hidden;display:none">
            <table  align="center" cellspacing="0" cellpadding="1" class="innerTable1" width="100%">
                <tr>

                    <!-- start-->
                    <td class="label">2. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="36" property="doctor2name" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                               onchange="capitalizeMe(this)" maxlength="25"/> </td>
                    <td class="label">Reg. Number<font color="red"><b>*</b></font> <html:text styleId="37" property="doctor2regnumber" name="partAForm" onkeypress="return space(event,this)"/></td>
                    <td class="label">Designation<font color="red"><b>*</b></font> <html:text styleId="38" property="doctor2designation" name="partAForm" onkeypress="return isNumberKey(event)" maxlength="25" onkeypress="return space(event,this)"/></td>


                </tr>
                <tr>

                    <!-- start-->
                    <td class="label">3. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="39" property="doctor3name" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                               onchange="capitalizeMe(this)" maxlength="25"/> </td>
                    <td class="label">Reg. Number<font color="red"><b>*</b></font> <html:text styleId="40" property="doctor3regnumber" name="partAForm" onkeypress="return space(event,this)"/></td>
                    <td class="label">Designation<font color="red"><b>*</b></font> <html:text styleId="41" property="doctor3designation" name="partAForm" onkeypress="return isNumberKey(event)" maxlength="25" onkeypress="return space(event,this)"/></td>


                </tr>
            </table></div>


  <div id="locomotor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                     <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>
                <td class="labelBlue"> &nbsp;   </td>
                 <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>

            </tr>
                    <tr>
                        <td class="label">Locomotor Disability/OH Subtypes
                        <logic:equal name="partAForm" property="disabilityId" value="1"><font color="red"><b>*</b></font></logic:equal>
                        </td>

                        <td width="20%">
                            <html:select  property="disabilityLocoSubIds" styleId="locomotorSubTypes" multiple="true" size="15" >
                                <html:optionsCollection property="disabilityLocoSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                            </html:select>
                        </td>


                         <td width="20%">
                            <select  name="rekha" styleId="locomotorSubTypes" multiple="true" size="15" disabled="true">
                                <option value="1">A-1 Cerebral Palsy (CP)</option>
                                <option value="2">A-2 Post Polio Residual Paralysis (PPRP)</option>
                                <option value="3">A-3 Post Hansen&#39;s Disease Sequel</option>
                                <option value="4">A-4 Muscular Dystrophy</option>
                                <option value="5">A-5 Post Traumatic Amputation</option>
                                <option value="6">A-6 Post Traumatic Sequel - Limbs</option>
                                <option value="7">A-7 Post Traumatic Sequel - Spine</option>
                                <option value="8">A-8 Post Head Injury Sequel</option>
                                <option value="9">A-9 Post Burn Injury Sequel</option>
                                <option value="10">A-10 Cerebro-Vascular Accidents  </option>
                                <option value="11">A-11 Dwarfism</option>
                                <option value="12">A-12 Congenital Deformities of Limbs</option>
                                <option value="13">A-13 Congenital Deformities of Spine</option>
                                <option value="14">A-14 Cardio Pulmonary Diseasee</option>
                                <option value="15">A-16 Others</option>
                                <option value="32">A-15 Muscular Weakness</option>



                            </select>
                        </td >
                        <td class="label">Part Involved<logic:equal name="partAForm" property="disabilityId"
                                     value="1"><font color="red"><b>*</b></font></logic:equal> </td>
                        <td width="25%">
                            <html:select  property="disabilityLocoSubSubIds" styleId="locomotorSubSubTypes" multiple="true" size="11" >
                                <html:optionsCollection   property="disabilityLocoSubSubList" label="disabilityLocoSubSubName" value="disabilityLocoSubSubId" />
                            </html:select>
                        </td>
                         <td width="25%">
                            <select  name="rekha" styleId="locomotorSubSubTypes" multiple="true" size="11" disabled="true">
                               <option value="1">a.Rt. U/L</option>
                               <option value="2">b.Lt U/L</option>
                               <option value="3">c.Bil.U/L</option>
                               <option value="4">d.Rt.L/L</option>
                               <option value="5">e.Lt.L/L</option>
                               <option value="6">f.Bil.L/L</option>
                               <option value="7">g.Spine</option>
                               <option value="8">h.Trunk</option>
                               <option value="9">j.All 4 Limbs</option>
                               <option value="10">k.Whole Body</option>
                               <option value="11">i.Hips</option>


                            </select>
                        </td>
                    </tr>
                     <tr><td width="32%" colspan="2"class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td colspan="4" class="labelBlue" > New   </td>

            </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">Any other</td>
                        <td  class="label">
                            <html:text property="othertypeofdisability" size="40" maxlength="50" disabled="true"></html:text>
                        </td>
                        <td  colspan="4" class="label">
                            <input type="text" name="rekha" size="40" maxlength="50" disabled="true"/>
                        </td>

                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label" >F-can perform work by manipulating with fingers</td>
                        <td  class="label" ><html:radio  property="f_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="f_can" value ="0" disabled="true">No</html:radio></td>
                        <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>

                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">PP-can perform work by pulling and pushing</td>
                        <td  class="label"><html:radio  property="pp_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="pp_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">L-can perform work by lifting</td>
                        <td  class="label"><html:radio  property="l_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="l_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">KC-can perform work by kneeling and crouching</td>
                        <td  class="label"><html:radio  property="kc_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="kc_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">B-can perform work by bending</td>
                        <td   class="label"><html:radio  property="b_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="b_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">S-can perform work by sitting</td>
                        <td  class="label"><html:radio  property="s_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="s_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">ST-can perform work by standing</td>
                        <td  class="label"><html:radio  property="st_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="st_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                    <tr>
                        <td width="32%" colspan="2"class="label">W-can perform work by walking</td>
                        <td  class="label"><html:radio  property="w_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="w_can" value ="0" disabled="true">No</html:radio></td>
                          <td  colspan="3" class="label" ><input type="radio" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true"/>No</td>
                </table>
            </div>


            <div id="visual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">

                    <tr>
                        <td class="label" width="32%">Visual Impairment Subtypes<logic:equal name="partAForm" property="disabilityId" value="2">
                                <font color="red"><b>*</b></font></logic:equal></td>
                            <td width="25%">
                            <html:select  property="disabilityLocoSubIds" styleId="visualSubTypes" multiple="true" size="4" >
                              <!--  <html:option  value="">--SELECT--</html:option>-->
                                <html:optionsCollection   property="disabilityVisualSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                            </html:select>
                        </td>
                        <td width="25%">
                            <select  property="disabilityLocoSubIds" styleId="visualSubTypes" multiple="true" size="4" disabled="true">
                             <!--   <option  value="">--SELECT--</option>-->
                                <option value="1">Complete Loss of Vission - Better Eye</option>
                                 <option value="1">Complete Loss of Vission - Worse Eye</option>
                                  <option value="1">Low Vission - Better Eye</option>
                                   <option value="1">Low Vission - Worse Eye</option>
                            </select>
                        </td>






                    </tr>
                    <tr>
                        <td class="label" >SE-can perform work by seeing</td>
                        <td class="label" width="25%"><html:radio  property="se_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="se_can" value ="0" disabled="true">No</html:radio></td>
                        <td class="label" width="25%"><input type="radio" name="rekha" value ="1" disabled="true"/>Yes<input type="radio" name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>

                </table>




            </div>
            <div id="hearing" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                    <tr>
                        <td class="labelBlue" colspan="2">Hearing Impairment</td>
                    </tr>

                    <%--<tr valign="top" class="tbl_bg2_content_hdr_new"> <td><b><font size="2">Hearing Impairment <logic:equal name="partAForm" property="disabilityId" value="3"><font color="red" size="3"><b>*</b></font></logic:equal> :</font></td>
                    <td colspan="3" width="68%">
                        <html:select  property="disabilityLocoSubIds" styleId="hearingSubTypes" name="partAForm">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="disabilityHearingSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                        </html:select>
                    </td></tr>--%>

                    <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>

            </tr>
                    <tr>
                        <td class="label" width="32%">H-can perform work by hearing/speaking</td>
                        <td  class="label" width="25%"><html:radio  property="h_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="h_can" value ="0" disabled="true">No</html:radio></td>
                        <td  class="label" width="25%"><input type="radio"  name="rekha" value ="1" disabled="true"/>Yes<input type="radio"  name="rekha" value ="0" disabled="true">No</td>
                    </tr>
                </table>
            </div>
            <div id="commonCondition" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="100%">
                     <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>


            </tr>
                    <tr>
                        <td class="label" width="32%">RW-can perform work by reading and writing</td>
                        <td class="label" width="25%"><html:radio  property="rw_can" value ="1" disabled="true">Yes</html:radio><html:radio  property="rw_can" value ="0" disabled="true">No</html:radio></td>
                         <td class="label" width="25%"><input type="radio" name="rekha" value ="1" disabled="true"/>Yes<input type="radio" name="rekha" value ="0" disabled="true"/>No</td>
                    </tr>
                </table>
            </div>
            <%-- <div id="mentalretd" style="visibility:hidden;display:none">
                 <table  border="0" align="center" cellspacing="0" cellpadding="5" class="tbl_bg2" width="100%">
                     <tr valign="top" class="tbl_bg2_content_hdr_new"> <td><b><font size="2">Mental Retardation <logic:equal name="partAForm" property="disabilityId" value="4"><font color="red" size="3"><b>*</b></font></logic:equal> :</font></td>
                     <td colspan="3" width="68%">
                         <html:select  property="disabilityLocoSubIds" styleId="mentalRetardationSubType" name="partAForm">
                             <html:option  value="">--SELECT--</html:option>
                             <html:optionsCollection   property="disabilityMentalRetdSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                         </html:select>
                     </td></tr>

                 </table>
             </div>
             <div id="mentalillness" style="visibility:hidden;display:none">
                 <table  border="0" align="center" cellspacing="0" cellpadding="5" class="tbl_bg2" width="100%">
                     <tr valign="top" class="tbl_bg2_content_hdr_new"> <td><b><font size="2">Mental Illness <logic:equal name="partAForm" property="disabilityId" value="5"> <font color="red" size="3"><b>*</b></font></logic:equal> :</font></td>
                     <td colspan="3" width="68%">
                         <html:select  property="disabilityLocoSubIds" styleId="mentalIllinessSubTypes">
                             <html:option  value="">--SELECT--</html:option>
                             <html:optionsCollection   property="disabilityMentalIllnessSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                         </html:select>
                     </td></tr>
                 </table>
             </div>--%>



            <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
             <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td></tr>

                <tr>
                    <td width="32%" class="label">Disability/Impairment is due to</td>

                    <td width="25%">
                        <html:textarea  rows="4" cols="30"  property="diagnosisofdisability" onblur="validatediagnosisofdisability()" disabled="true"></html:textarea>
                    </td>
                    <td width="25%">
                        <textarea rows="4" cols="30"  name="rekha" disabled="true"></textarea>
                    </td>




                </tr>
            </table>

            <div id="conditionofdisabilityvisual" style="visibility:hidden;">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                     <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td></tr>
                    <tr>
                        <td class="labelBlue" width="32%">1.1 Condition of Disability<font color="red"><b>*</b></font></td>
                        <td  class="label" width="25%">
                        	<html:select size="3" property="conditiondisability" style="width: 336" styleId="conditiondisabilityvisual" name="partAForm" onchange="disabile()">
                                <html:option value="1">Permanent,Progressive,Not-likely to improve </html:option>
                                <html:option value="2">Permanent,Non-progressive,Not likely to improve</html:option>
                                <html:option value="3">Temporary,Non-progressive,Likely to improve</html:option>
                            </html:select>
                        </td>
                        <td class="label" width="25%">
                           <select name="rekha" size="3" disabled="true">
								<option VALUE="1">Permanent,Progressive,Not-likely to improve </option>
								<option VALUE="2">Permanent,Non-progressive,Not likely to improve</option>
								<option VALUE="3">Temporary,Non-progressive,Likely to improve</option>
						   </select>



                        </td>



                    </tr>
                </table>
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                     <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>
                    <tr>


                        <td  class="label" width="25%">Period of Reassessment</td>
                        <td width="30%"> 
                        <html:select property="yearsfortemporary"  name="partAForm"  size="5">
                                <html:option value="1">1</html:option>
                                <html:option value="2">2</html:option>
                                <html:option value="3">3</html:option>
                                <html:option value="4">4</html:option>
                                <html:option value="5">5</html:option>
                            </html:select>
                         </td>


                        <td width="30%"> <select name="rekha"  size="5" width="25%" disabled="true">
                                <!--<option value="0">--SELECT--</option>-->
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select></td>

                    </tr>
                </table>
            </div>
            <div id="causeOfDisabilityVisual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                    <tr>
                        <td class="labelBlue" >1.2 Causes of Disability</td>
                         <td class="labelBlue" >&nbsp;</td>
                           <td class="labelBlue" >Old</td>
                             <td class="labelBlue" >New</td>
                               <td class="labelBlue" >&nbsp;</td>  <td class="labelBlue" >Old</td>
                                 <td class="labelBlue" >New</td>





                    </tr>
                    <tr>
                        <td width="32%" class="label"> Congenital
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td class="label"> <html:checkbox property="congenitalbettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td class="label"> <html:checkbox property="congenitalworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"> Hereditary
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td class="label"> <html:checkbox property="hereditarybettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td class="label"> <html:checkbox property="hereditaryworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="label"> Birth Injury
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td> <html:checkbox property="birthinjurybettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td> <html:checkbox property="birthinjuryworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="label"> Disease and Infection
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td> <html:checkbox property="diseaseInfectionbettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td> <html:checkbox property="diseaseInfectionworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="label"> Malnutrition
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td> <html:checkbox property="malnutritionbettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td> <html:checkbox property="malnutritionworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"> Accident
                        </td>
                        <td class="label"> Better Eye
                        </td>
                        <td> <html:checkbox property="accidentbettereye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                        <td class="label"> Worse Eye
                        </td>
                        <td> <html:checkbox property="accidentworseeye" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td class="label"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                </table>
            </div>


            <div id="causeOfDisabilityOther" style="visibility:visible;display:inline">
                <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                    <tr>
                        <td class="labelBlue" >1.2 Causes of Disability</td>
                         <td class="labelBlue" >Old</td>
                          <td class="labelBlue" >New</td>
                    </tr>
                    <tr>
                        <td width="32%" class="label"> Congenital
                        </td>
                        <td width="25%"> <html:checkbox property="congenital" value="1" disabled="true"> </html:checkbox>
                        </td>
                        <td width="25%"> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"> Hereditary
                        </td>
                        <td><html:checkbox property="hereditary" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="label"> Birth Injury
                        </td>
                        <td><html:checkbox property="birthinjury" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td  class="label"> Birth Asphyxia
                        </td>
                        <td><html:checkbox property="birthasphyxia" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>


                    <tr>
                        <td class="label"> Meningitis
                        </td>
                        <td><html:checkbox property="highfever" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td  class="label"> Epilepsy
                        </td>
                        <td><html:checkbox property="epilepsy" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td  class="label"> Disease and Infection
                        </td>
                        <td> <html:checkbox property="diseaseInfection"  value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="label"> Malnutrition
                        </td>
                        <td> <html:checkbox property="malnutrition"value="1" disabled="true"></html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"> Accident
                        </td>
                        <td><html:checkbox property="accident" value="1" disabled="true"> </html:checkbox>
                        </td>
                         <td> <input type="checkbox" name="rekha" value="1" disabled="true"/>
                        </td>
                    </tr>
                </table>
            </div>
            <table  align="center" cellspacing="0" cellpadding="1" border="1" class="innerTable1" width="100%">
                 <tr><td class="labelBlue"> &nbsp;   </td>
                <td class="labelBlue" >Old  </td>
                <td class="labelBlue" > New   </td>
                <tr>

                    <td class="label" width="32%">Any other Cause of Disability</td>
                    <td><html:text property="otherconditionofdisability" size="50"  maxlength="75" disabled="true"></html:text></td>
                     <td><input type="text" name="rekha"size="50"  maxlength="75" disabled="true"/></td>
                </tr>
            </table><br>




        </html:form>

            <form action="">
                  <%if (aa == 1) {%>

            <table align="center"><tr align="center">
                    <td colspan="4" align="center">
                        <html:button value="Next" property="but" onclick="return getOne();" styleClass="button" />&nbsp;&nbsp;

                        <html:button property="b" value="Print" styleClass="button" onclick="window.print();"/></td> </tr>
            </table>
            <%}%>

            <%if (aa == 2) {%>

            <table align="center"><tr align="center">
                    <td colspan="4" align="center">
                        <html:button value="Next" property="but" onclick="return getTwo();" styleClass="button" />&nbsp;&nbsp;

                        <html:button property="b" value="Print" styleClass="button" onclick="window.print();"/></td> </tr>
            </table>
            <%}%>

            <%if (aa == 3) {%>

            <table align="center"><tr align="center">
                    <td colspan="4" align="center">
                        <html:button value="Next" property="but" onclick="return getThree();" styleClass="button" />&nbsp;&nbsp;

                        <html:button property="b" value="Print" styleClass="button" onclick="window.print();"/></td> </tr>
            </table>
            <%}%>

            <%if (aa == 4) {%>

            <table align="center"><tr align="center">
                    <td colspan="4" align="center">
                        <html:button value="Next" property="but" onclick="return getFour();" styleClass="button" />&nbsp;&nbsp;

                        <html:button property="b" value="Print" styleClass="button" onclick="window.print();"/></td> </tr>
            </table>
            <%}%>

            <%if (aa == 5) {%>

            <table align="center"><tr align="center">
                    <td colspan="4" align="center">
                        <html:button value="Next" property="but" onclick="return getFive();" styleClass="button" />&nbsp;&nbsp;

                        <html:button property="b" value="Print" styleClass="button" onclick="window.print();"/></td> </tr>
            </table>
            <%}%>
            </form>
    </body>
    <script>

        function getOne()
        {
            document.forms[0].action="LocomotorneedsPrintselect.do?getLocomotorneeds=getLocomotorneeds";
            document.forms[0].submit();
        }
        //visual

        function getTwo()
        {
            document.forms[0].action="getvisualimpairmentPrint.do?getVisualImpairment=getVisualImpairment";
            document.forms[0].submit();
        }
        //hearint
        function getThree()
        {
            document.forms[0].action="hearingUpdatePrint.do?selectHearing=selectHearing";
            document.forms[0].submit();
        }

        //retradation
        function getFour()
        {
            document.forms[0].action="MentalRetardationForwdActionPrint.do";
            document.forms[0].submit();
        }

        // Mental illness
        function getFive()
        {
            document.forms[0].action="getmentalillnessPrint.do";
            document.forms[0].submit();
        }




    </script>
</html>
