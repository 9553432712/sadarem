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
                            // document.getElementById("mentalretd").style.visibility = "Visible";
                            // document.getElementById("mentalretd").style.display = "Inline";
                            // document.getElementById("mentalillness").style.visibility = "Visible";
                            //  document.getElementById("mentalillness").style.display = "Inline";

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


            function getData(read)
            {

                document.forms[0].partAStatus.value="update";
                document.forms[0].submit();
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
                document.getElementById("hideTB").style.display='none';
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
                            document.getElementById("hideTB").style.display='';
                            document.partAForm.yearsfortemporary.focus();
                            flag = false;
                            return flag;
                        }
                    }

                    if (validate_required(disabilityId,"Type of Disability must be selected!")==false)
                    {
                        document.getElementById("hideTB").style.display='';
                        disabilityId.focus();
                        flag = false;
                        return flag;
                    }
                    if (validate_required(camp_venue,"Venue of the Camp must be entered!")==false)
                    {
                        document.getElementById("hideTB").style.display='';
                        camp_venue.focus();
                        flag = false;
                        return flag;
                    }

                    if (validate_required(hospitalname,"Name of the Medical Authority must be entered!")==false)
                    {
                        document.getElementById("hideTB").style.display='';
                        hospitalname.focus();
                        flag = false;
                        return flag;
                    }
                    <!--Validation  For Hospital Address-->
                    if (validate_required(hospitaladdress,"Address of the Medical Authority must be entered!")==false)
                    {
                        document.getElementById("hideTB").style.display='';
                        hospitaladdress.focus();
                        flag = false;
                        return flag;
                    }

            <%--   <!--Validation  For First Doctor-->
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
               } --%>

                           <!--Validation  For Second Doctor-->
                           if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor2name.focus();
                               flag = false;
                               return flag;
                           }

                           if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor2regnumber.focus();
                               flag = false;
                               return flag;
                           }

                           if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor2designation.focus();
                               flag = false;
                               return flag;
                           }
                           <!--Validation  For Third Doctor-->

                           if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor3name.focus();
                               flag = false;
                               return flag;
                           }

                           if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor3regnumber.focus();
                               flag = false;
                               return flag;
                           }

                           if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                           {
                               document.getElementById("hideTB").style.display='';
                               doctor3designation.focus();
                               flag = false;
                               return flag;
                           }
                           var hearingImparimentId =document.partAForm.disabilityId.value;
                           if(hearingImparimentId=="1")
                           {
                               if (validate_required(document.getElementById("locomotorSubTypes"),"Locomoto SubTypes must be selected!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   document.getElementById("locomotorSubTypes").focus();
                                   flag = false;
                                   return flag;
                               }
                               if (validate_required(document.getElementById("locomotorSubSubTypes"),"Part Involved must be selected!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   document.getElementById("locomotorSubSubTypes").focus();
                                   flag = false;
                                   return flag;
                               }
                           }

                           // var hearingImparimentId =document.partAForm.disabilityId.value;
                           // if(hearingImparimentId=="3")
                           // {
                           // if (validate_required(document.getElementById("hearingSubTypes"),"Hearing Impariment must be selected!")==false)
                           // {
                           // document.getElementById("hearingSubTypes").focus();
                           //  return false
                           // }
                           // }

                           // var mentalRetardationId =document.partAForm.disabilityId.value;
                           //  if(mentalRetardationId=="4")
                           //  {
                           // if (validate_required(document.getElementById("mentalRetardationSubType"),"Menta Retardation must be selected!")==false)
                           // {
                           //  document.getElementById("mentalRetardationSubType").focus();
                           //  return false
                           // }
                           //  }

                           // var mentalIllnessId =document.partAForm.disabilityId.value;
                           // if(mentalIllnessId=="5")
                           // {
                           // if (validate_required(document.getElementById("mentalIllinessSubTypes"),"Mental Illness must be selected!")==false)
                           // {
                           //  document.getElementById("mentalIllinessSubTypes").focus();
                           // return false
                           // }
                           // }




                           var theDay =document.partAForm.disabilityId.value;
                           if(theDay=="2")
                           {
                               if (validate_required(document.getElementById("visualSubTypes"),"Visual Impairment Subtypes must be selected!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   document.getElementById("visualSubTypes").focus();
                                   flag = false;
                                   return flag;
                               }
                               if (validate_required(document.getElementById("conditiondisabilityvisual"),"Condition of Disability (visual) must be selected!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   document.getElementById("conditiondisabilityvisual").focus();
                                   flag = false;
                                   return flag;
                               }
                           }
                           else
                           {
                               if (validate_required(document.getElementById("conditiondisabilityvisual"),"Condition of Disability must be selected!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   document.getElementById("conditiondisabilityvisual").focus();
                                   flag = false;
                                   return flag;
                               }

                           }


                           var disabilty =document.partAForm.disabilityId.value;
                           // alert("disbaility=="+disabilty + document.getElementsByName("se_can").length);
                           switch (disabilty)

                           {

                               case "2":{
                                       var se=document.getElementsByName("se_can");var coun=0,rwcan=0;
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               coun++;
                                           }}
                                       se=document.getElementsByName("rw_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               rwcan++;
                                           }}

                                       if(coun>=1 && rwcan>=1){
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return true;
                                       }else{
                                           alert("SE-can perform work by seeing, RW_can must be selected(Yes/No)");
                                           flag=false;
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return flag;

                                       }
                                       break;
                                   }

                               case "3":{


                                       var se=document.getElementsByName("h_can");var coun=0;
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               coun++;
                                           }}
                                       if(coun>=1){
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return true;
                                       }else{
                                           alert("H-can perform work by hearing/speaking must be selected(Yes/No)");
                                           flag=false;
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return flag;

                                       }
                                       break;

                                   }
                               case "1":{
                                       var se=document.getElementsByName("f_can");
                                       var fcan=0,lcan=0,ppcan=0,kccan=0,bcan=0,scan=0,stcan=0,wcan=0,rwcan=0;
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               fcan++;
                                           }}
                                       se=document.getElementsByName("l_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               lcan++;
                                           }}

                                       se=document.getElementsByName("pp_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               ppcan++;
                                           }}

                                       se=document.getElementsByName("kc_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               kccan++;
                                           }}

                                       se=document.getElementsByName("b_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               bcan++;
                                           }}

                                       se=document.getElementsByName("s_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               scan++;
                                           }}

                                       se=document.getElementsByName("st_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               stcan++;
                                           }}

                                       se=document.getElementsByName("w_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               wcan++;
                                           }}
                                       se=document.getElementsByName("rw_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               rwcan++;
                                           }}
                                       if(fcan>=1 && lcan>=1 && ppcan>=1&&kccan>=1&&bcan>=1&&scan>=1&&stcan>=1&&wcan>=1 &&rwcan>=1){
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return true;
                                       }else{
                                           alert("F-can,PP_can,L_can,KC_can,B_can,S_can,ST_can,RW_can must be selected(Yes/No)");
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return false;}



                                       break;
                                   }
                               case "6":{
                                       var se=document.getElementsByName("f_can");
                                       // alert('seeeeeee==='+se + se.length);
                                       var fcan=0,lcan=0,ppcan=0,kccan=0,bcan=0,scan=0,stcan=0,wcan=0,secan=0,hcan=0,rwcan=0;
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               fcan++;
                                           }}
                                       se=document.getElementsByName("l_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               lcan++;
                                           }}

                                       se=document.getElementsByName("pp_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               ppcan++;
                                           }}

                                       se=document.getElementsByName("kc_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               kccan++;
                                           }}

                                       se=document.getElementsByName("b_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               bcan++;
                                           }}

                                       se=document.getElementsByName("s_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               scan++;
                                           }}

                                       se=document.getElementsByName("st_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               stcan++;
                                           }}

                                       se=document.getElementsByName("w_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               wcan++;
                                           }}
                                       se=document.getElementsByName("h_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               hcan++;
                                           }} se=document.getElementsByName("se_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               secan++;
                                           }}
                                       se=document.getElementsByName("rw_can");
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               rwcan++;
                                           }}

                                       if(fcan>=1 && lcan>=1 && ppcan>=1&&kccan>=1&&bcan>=1&&scan>=1&&stcan>=1&&wcan>=1 && secan>=1 &&hcan>=1 && rwcan>=1){
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return true;
                                       }else{
                                           alert("F-can,PP_can,L_can,KC_can,B_can,S_can,ST_can,RW_can must be selected(Yes/No)");
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return false;}



                                       break;
                                   }
                               case "4":{

                                       var se=document.getElementsByName("rw_can");
                                       var rwcan=0;
                                       for( i=0;i<se.length;i++){
                                           if(se[i].checked){
                                               rwcan++;
                                           }}

                                       if(rwcan>=1){
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return true;
                                       }else{
                                           alert("RW-can perform work by reading and writing must be selected(Yes/No)");
                                           flag=false;
                                           document.getElementById("hideTB").style.display='';
                                           document.getElementById("conditiondisabilityvisual").focus();
                                           return flag;

                                       }
                                       break;
                                   }
                           }











                           var multilpledisabilityid =document.partAForm.disabilityId.value;
                           if(multilpledisabilityid == 6)
                           {


                               <!--Validation  For Second Doctor-->
                               if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2name.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2regnumber.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2designation.focus();
                                   flag = false;
                                   return flag;
                               }
                               <!--Validation  For Third Doctor-->

                               if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3name.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3regnumber.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3designation.focus();
                                   flag = false;
                                   return flag;
                               }
                           }


                           else
                           { <!--Validation  For First Doctor-->
                               if(validate_required(doctor1name,"First Doctor Name must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor1name.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor1regnumber,"First Doctor Reg.Number must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor1regnumber.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor1designation,"First Doctor Designation must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor1designation.focus();
                                   flag = false;
                                   return flag;
                               }

                               <!--Validation  For Second Doctor-->
                               if (validate_required(doctor2name,"Second Doctor Name must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2name.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor2regnumber,"Second Doctor Reg.Number must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2regnumber.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor2designation,"Second Doctor Designation must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor2designation.focus();
                                   flag = false;
                                   return flag;
                               }
                               <!--Validation  For Third Doctor-->

                               if (validate_required(doctor3name,"Third Doctor Name must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3name.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor3regnumber,"Third Doctor Reg.Number must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3regnumber.focus();
                                   flag = false;
                                   return flag;
                               }

                               if (validate_required(doctor3designation,"Third Doctor Designation must be Entered!")==false)
                               {
                                   document.getElementById("hideTB").style.display='';
                                   doctor3designation.focus();
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
                               document.getElementById('subDisablButton').disabled = true;
                           }
                       }
                       return flag;
                   }





                   function validatediagnosisofdisability()
                   {
                       var iChars = "!@#$%^&*()+=-[]\\\'`~;/{}|\":<>?1234567890";
                       var diagnosisofdisability=document.partAForm.diagnosisofdisability.value;
                       for (var i = 0; i < diagnosisofdisability.length; i++)
                       {
                           if (iChars.indexOf(diagnosisofdisability.charAt(i)) != -1)
                           {
                               alert ("Please Enter Charecters only in  Disability/Impairment.");
                               document.partAForm.diagnosisofdisability.value="";
                               document.partAForm.diagnosisofdisability.focus();
                               return false;
                           }
                       }
                   }






        </script>
        <script language="javascript">
            function disabile()
            {
            	try{
                var x=document.getElementById("conditiondisability").value;
                if(x == 3)
                {
                    document.forms[0].yearsfortemporary.disabled = false;
                }
                else
                {
                    document.forms[0].yearsfortemporary.disabled = true;
                    document.forms[0].yearsfortemporary.value="0";
                }}catch(e){alert(e);}

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
                document.getElementById("hideTB").style.display='none';
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
                        document.getElementById("hideTB").style.display='';
                        ohdoctor.focus();
                        return false;
                    }


                    if(ohdoctor != null){
                        if ( (ohdoctor.checked == true) && (doctornameOH.value == ""))
                        {
                            alert("Please enter OH Doctor Name");
                            document.getElementById("hideTB").style.display='';
                            doctornameOH.focus();
                            return false;
                        }
                        if ( (ohdoctor.checked == true) && (doctorregnumberOH.value == ""))
                        {
                            alert("Please enter OH Doctor Reg. Number");
                            document.getElementById("hideTB").style.display='';
                            doctorregnumberOH.focus();
                            return false;
                        }
                        if ( (ohdoctor.checked == true) && (doctordesignationOH.value == ""))
                        {
                            alert("Please enter OH Doctor Designation");
                            document.getElementById("hideTB").style.display='';
                            doctordesignationOH.focus();
                            return false;
                        }
                        if ( (ohdoctor.checked == true))
                        {

                            if (validate_required(document.getElementById("locomotorSubTypes"),"Locomoto SubTypes must be selected!")==false)
                            {
                                document.getElementById("locomotorSubTypes").focus();
                                document.getElementById("hideTB").style.display='';
                                return false;
                            }
                            if (validate_required(document.getElementById("locomotorSubSubTypes"),"Part Involved must be selected!")==false)
                            {
                                document.getElementById("locomotorSubSubTypes").focus();
                                document.getElementById("hideTB").style.display='';
                                return false;
                            }

                        }
                    }

                    if(vidoctor != null){
                        if ( (vidoctor.checked == true) && (doctornameVI.value == ""))
                        {
                            alert("Please enter VI Doctor Name");
                            document.getElementById("hideTB").style.display='';
                            doctornameVI.focus();
                            return false;
                        }
                        if ( (vidoctor.checked == true) && (doctorregnumberVI.value == ""))
                        {
                            alert("Please enter VI Doctor Reg. Number");
                            document.getElementById("hideTB").style.display='';
                            doctorregnumberVI.focus();
                            return false;
                        }
                        if ( (vidoctor.checked == true) && (doctordesignationVI.value == ""))
                        {
                            alert("Please enter VI Doctor Designation");
                            document.getElementById("hideTB").style.display='';
                            doctordesignationVI.focus();
                            return false;
                        }
                        if ( (vidoctor.checked == true))
                        {
                            if (validate_required(document.getElementById("visualSubTypes"),"Visual Impairment Subtypes must be selected!")==false)
                            {
                                document.getElementById("visualSubTypes").focus();
                                document.getElementById("hideTB").style.display='';
                                flag = false;
                                return flag;
                            }
                        }
                    }

                    if(hidoctor != null){
                        if ( (hidoctor.checked == true) && (doctornameHI.value == ""))
                        {
                            alert("Please enter HI Doctor Name");
                            document.getElementById("hideTB").style.display='';
                            doctornameHI.focus();
                            return false;
                        }
                        if ( (hidoctor.checked == true) && (doctorregnumberHI.value == ""))
                        {
                            alert("Please enter HI Doctor Reg. Number");
                            document.getElementById("hideTB").style.display='';
                            doctorregnumberHI.focus();
                            return false;
                        }
                        if ( (hidoctor.checked == true) && (doctordesignationHI.value == ""))
                        {
                            alert("Please enter HI Doctor Designation");
                            document.getElementById("hideTB").style.display='';
                            doctordesignationHI.focus();
                            return false;
                        }
                    }

                    if(mrdoctor != null){
                        if ( (mrdoctor.checked == true) && (doctornameMR.value == ""))
                        {
                            alert("Please enter MR Doctor Name");
                            document.getElementById("hideTB").style.display='';
                            doctornameMR.focus();
                            return false;
                        }
                        if ( (mrdoctor.checked == true) && (doctorregnumberMR.value == ""))
                        {
                            alert("Please enter MR Doctor Reg. Number");
                            document.getElementById("hideTB").style.display='';
                            doctorregnumberMR.focus();
                            return false;
                        }
                        if ( (mrdoctor.checked == true) && (doctordesignationMR.value == ""))
                        {
                            alert("Please enter MR Doctor Designation");
                            document.getElementById("hideTB").style.display='';
                            doctordesignationMR.focus();
                            return false;
                        }
                    }

                    if(midoctor != null){
                        if ( (midoctor.checked ==true ) && (doctornameMI.value == ""))
                        {
                            alert("Please enter MI Doctor Name");
                            document.getElementById("hideTB").style.display='';
                            doctornameMI.focus();
                            return false;
                        }
                        if ( (midoctor.checked == true) && (doctorregnumberMI.value == ""))
                        {
                            alert("Please enter MI Doctor Reg. Number");
                            document.getElementById("hideTB").style.display='';
                            doctorregnumberMI.focus();
                            return false;
                        }
                        if ( (midoctor.checked == true) && (doctordesignationMI.value == ""))
                        {
                            alert("Please enter MI Doctor Designation");
                            document.getElementById("hideTB").style.display='';
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

            function isNumberKey(evt)
            {         var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return true;
                return false;
            }
            function alpha(e) {
                var k;
                document.all ? k = e.keyCode : k = e.which;
                return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k==32);
            }

        </script>
        
    </head>

    <body onload="displayAppropriate(this.value),validatecheckbox()">
        <html:form action="insertDisabilityDetails.do?insertDisabilityDetails=insertDisabilityDetails" styleId="partAForm" onsubmit="return validateForm(this)">
            <input type="hidden" name="partAStatus" value="finish"/>
            <%if (request.getAttribute("selectedValue") != null) {%>
            <input type="hidden" name="selectFlow" id="selectFlow" value="<%=request.getAttribute("selectedValue")%>"/>
            <%}%>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="8">Add Disability Details</th>
                </tr>

                <tr>
                    <td width="34%"> Venue of the Camp</td>
                    <td ><html:text property="camp_venue" size="50" name="partAForm" readonly="true" style="width:250px"/></td>


                </tr>
                <tr>
                    <td width="34%">Name of the Medical Authority</td>
                    <td ><html:text styleId="31" property="hospitalname" size="50" name="partAForm" readonly="true" style="width:250px"/></td>
                </tr>
                <tr>
                    <td width="34%">Address of the Medical Authority</td>
                    <td ><html:text styleId="32" size="50" property="hospitaladdress"  name="partAForm"  readonly="true" style="width:250px"/></td>
                </tr>


                <tr> <td width="34%">1.0  Type of Disability<font color="red" ><b>*</b></font></td>
                    <td >
                        <html:select  property="disabilityId"  onchange="getData(this.value)" style="width:250px">
                            <html:option  value="">--SELECT--</html:option>
                            <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityId"  />
                        </html:select>
                    </td>
                </tr>


                <tr>
                    <th colspan="4">Doctor Details</th>
                <tr>

            </table>
            <div id="singledoctor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                    <tr>

                        <!-- start-->
                        <html:hidden  property="specialistprefix" name="partAForm"  />
                        <td width="34%" >1. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="doctor1name" property="doctor1name" name="partAForm" readonly="true" style="width:180px"/></td>
                        <td  >Reg. Number<font color="red"><b>*</b></font> <html:text styleId="doctor1regnumber" property="doctor1regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                        <td  >Designation<font color="red"><b>*</b></font> <html:text styleId="doctor1designation" property="doctor1designation" name="partAForm" readonly="true" style="width:180px"/></td>

                    </tr>
                </table>
            </div>
            <div id="multipledoctor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%" >
                    <tr>

                        <!-- start-->
                        <td width="34%"><html:checkbox property="ohdoctor" styleId="OH" value="1" onclick="validatecheckbox(this.value)" style="width:25px"/>OH Doctor Name <html:text styleId="doctornameOH" property="doctornameOH" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameOH);" onkeypress="return space(event,this)"
                                                                                                                                                                       onchange="capitalizeMe(this)" maxlength="25" style="width:150px"/> </td>
                        <td  >Reg. Number <html:text styleId="doctorregnumberOH" property="doctorregnumberOH" name="partAForm" onkeypress="return space(event,this)" style="width:160px"/></td>
                        <td  >Designation <html:text styleId="doctordesignationOH" property="doctordesignationOH" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationOH);" maxlength="25" onkeypress="return space(event,this)" style="width:160px"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td  width="34%"><html:checkbox property="vidoctor" styleId="VI" value="2" onclick="validatecheckbox(this.value)" style="width:25px"/>VI Doctor Name &nbsp;<html:text styleId="doctornameVI" property="doctornameVI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameVI);" onkeypress="return space(event,this)"
                                                                                                                                                                             onchange="capitalizeMe(this)" maxlength="25" style="width:150px"/> </td>
                        <td  >Reg. Number <html:text styleId="doctorregnumberVI" property="doctorregnumberVI" name="partAForm" onkeypress="return space(event,this)" style="width:160px"/></td>
                        <td  >Designation <html:text styleId="doctordesignationVI" property="doctordesignationVI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationVI);" maxlength="25" onkeypress="return space(event,this)" style="width:160px"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td  width="34%"><html:checkbox property="hidoctor" styleId="HI" value="3" onclick="validatecheckbox(this.value)" style="width:25px"/>HI Doctor Name&nbsp; <html:text styleId="doctornameHI" property="doctornameHI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameHI);" onkeypress="return space(event,this)"
                                                                                                                                                                             onchange="capitalizeMe(this)" maxlength="25" style="width:150px"/> </td>
                        <td  >Reg. Number <html:text styleId="doctorregnumberHI" property="doctorregnumberHI" name="partAForm" onkeypress="return space(event,this)" style="width:160px"/></td>
                        <td  >Designation <html:text styleId="doctordesignationHI" property="doctordesignationHI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationHI);" maxlength="25" onkeypress="return space(event,this)" style="width:160px"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td  width="34%"><html:checkbox property="mrdoctor" styleId="MR" value="4" onclick="validatecheckbox(this.value)" style="width:25px"/>MR Doctor Name
                            <html:select property="mdspecialistprefix"  styleId="mdspecialistprefix" >
                                <html:option value="Dr">Dr</html:option>
                                <html:option value="Psychologist">Psychologist</html:option>
                            </html:select>
                            <html:text styleId="doctornameMR" property="doctornameMR" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMR);" onkeypress="return space(event,this)"
                                       onchange="capitalizeMe(this)" maxlength="25" style="width:150px"/> </td>
                        <td  >Reg. Number <html:text styleId="doctorregnumberMR" property="doctorregnumberMR" name="partAForm" onkeypress="return space(event,this)" style="width:160px"/></td>
                        <td  >Designation <html:text styleId="doctordesignationMR" property="doctordesignationMR" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationMR);" maxlength="25" onkeypress="return space(event,this)" style="width:160px"/></td>


                    </tr>
                    <tr>

                        <!-- start-->
                        <td  width="34%"><html:checkbox property="midoctor" styleId="MI" value="5" onclick="validatecheckbox(this.value)" style="width:25px"/>MI Doctor Name <html:text styleId="doctornameMI" property="doctornameMI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                                                                                                                                                                       onchange="capitalizeMe(this)" maxlength="25" style="width:150px"/> </td>
                        <td  >Reg. Number <html:text styleId="doctorregnumberMI" property="doctorregnumberMI" name="partAForm" onkeypress="return space(event,this)" style="width:160px"/></td>
                        <td  >Designation <html:text styleId="doctordesignationMI" property="doctordesignationMI" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctordesignationMI);" maxlength="25" onkeypress="return space(event,this)" style="width:160px"/></td>


                    </tr>
                </table>
            </div>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>

                    <!-- start-->
                    <td width="34%">2. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="doctor2name" property="doctor2name" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                               onchange="capitalizeMe(this)" maxlength="25" style="width:180px"/> </td>
                    <td  >Reg. Number<font color="red"><b>*</b></font> <html:text styleId="doctor2regnumber" property="doctor2regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td  >Designation<font color="red"><b>*</b></font> <html:text styleId="doctor2designation" property="doctor2designation" name="partAForm" readonly="true" style="width:180px"/></td>

                </tr>
                <tr>

                    <!-- start-->
                    <td >3. Doctor Name<font color="red"><b>*</b></font> <html:text styleId="doctor3name" property="doctor3name" name="partAForm" onkeydown="return isAlpha(event.keyCode,doctornameMI);" onkeypress="return space(event,this)"
                               onchange="capitalizeMe(this)" maxlength="25" style="width:180px"/> </td>
                    <td  >Reg. Number<font color="red"><b>*</b></font> <html:text styleId="doctor3regnumber" property="doctor3regnumber" name="partAForm" readonly="true" style="width:180px"/></td>
                    <td  >Designation<font color="red"><b>*</b></font> <html:text styleId="doctor3designation" property="doctor3designation" name="partAForm" readonly="true" style="width:180px"/></td>

                </tr>
            </table>

            <div id="locomotor" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" border="0"  width="90%">
                    <tr>
                        <td> <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="100%">
                            <tr> <td >Locomotor Disability/OH Subtypes<logic:equal name="partAForm" property="disabilityId" value="1"><font color="red"><b>*</b></font></logic:equal></td>

                        <td width="32%">
                            <html:select  property="disabilityLocoSubIds"  styleId="locomotorSubTypes" multiple="true" style="width:273px;height:150px">
                                <html:option  value="">--SELECT--</html:option>
                                <html:optionsCollection   property="disabilityLocoSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                            </html:select>
                        </td>
                        <td  align="center" >Part Involved<logic:equal name="partAForm" property="disabilityId" value="1"><font color="red"><b>*</b></font></logic:equal> </td>
                        <td  >
                            <html:select  property="disabilityLocoSubSubIds" styleId="locomotorSubSubTypes" multiple="true" style="width:100px;height:150px">
                                <html:option  value="">--SELECT--</html:option>
                                <html:optionsCollection   property="disabilityLocoSubSubList" label="disabilityLocoSubSubName" value="disabilityLocoSubSubId"  />
                            </html:select>
                        </td>
                    </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="100%">
                                <tr> <td width="34%" align="left" >Any other&nbsp;&nbsp;&nbsp;</td>
                                    <td colspan="3">
                                        <html:text property="othertypeofdisability" size="41" maxlength="50" style="width:275px"></html:text>
                                    </td></tr>
                                <tr valign="top"  colspan="2">
                                    <td >F-can perform work by manipulating with fingers<font color="red">*</font></td>
                                    <td ><html:radio  property="f_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="f_can" value ="0" style="width:25px">No</html:radio></td>
                                    <td  width="37%" >PP-can perform work by pulling and pushing<font color="red">*</font></td>
                                    <td><html:radio  property="pp_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="pp_can" value ="0" style="width:25px">No</html:radio></td>
                                </tr>
                                <tr valign="top"  colspan="2">
                                    <td >L-can perform work by lifting<font color="red">*</font></td>
                                    <td ><html:radio  property="l_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="l_can" value ="0" style="width:25px">No</html:radio></td>
                                    <td  swidth="37%" >KC-can perform work by kneeling and crouching<font color="red">*</font></td>
                                    <td><html:radio  property="kc_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="kc_can" value ="0" style="width:25px">No</html:radio></td>
                                </tr>
                                <tr valign="top"  colspan="2">
                                    <td >B-can perform work by bending<font color="red">*</font></td>
                                    <td   ><html:radio  property="b_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="b_can" value ="0" style="width:25px">No</html:radio></td>
                                    <td  width="27%" >S-can perform work by sitting<font color="red">*</font></td>
                                    <td><html:radio  property="s_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="s_can" value ="0" style="width:25px">No</html:radio></td>
                                </tr>
                                <tr valign="top"  colspan="2">
                                    <td >ST-can perform work by standing<font color="red">*</font></td>
                                    <td ><html:radio  property="st_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="st_can" value ="0" style="width:25px">No</html:radio></td>
                                    <td  width="27%" >W-can perform work by walking<font color="red">*</font></td>
                                    <td><html:radio  property="w_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="w_can" value ="0" style="width:25px">No</html:radio></td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </table>
            </div>



            <div id="visual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                    <tr>
                        <td  width="34%">Visual Impairment Subtypes<logic:equal name="partAForm" property="disabilityId" value="2"><font color="red"><b>*</b></font></logic:equal></td>
                        <td >
                            <html:select  property="disabilityLocoSubIds"  styleId="visualSubTypes" multiple="true" style="width:280px" >
                                <html:option  value="">--SELECT--</html:option>
                                <html:optionsCollection   property="disabilityVisualSubList" label="disabilityLocoSubName" value="disabilityLocoSubId"  />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td >SE-can perform work by seeing<font color="red">*</font></td>
                        <td ><html:radio  property="se_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="se_can" value ="0" style="width:25px">No</html:radio></td>
                    </tr>
                </table>
            </div>
            <div id="hearing" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                    <tr>
                        <td colspan="2">Hearing Impairment</td>
                    </tr>

                    <tr>
                        <td  width="34%">H-can perform work by hearing/speaking<font color="red">*</font></td>
                        <td ><html:radio  property="h_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="h_can" value ="0" style="width:25px">No</html:radio></td>
                    </tr>
                </table>
            </div>

            <div id="commonCondition" style="visibility:hidden;display:none">
                <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <td  width="34%">RW-can perform work by reading and writing<font color="red">*</font></td>
                        <td ><html:radio  property="rw_can" value ="1" style="width:25px">Yes</html:radio> <html:radio  property="rw_can" value ="0" style="width:25px">No</html:radio></td>
                    </tr>
                </table>
            </div>





            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">


                <tr> <td  width="34%">Disability/Impairment is due to</td>
                    <td>
                        <html:text size="100"   maxlength="150"  property="diagnosisofdisability"  onblur="validatediagnosisofdisability()" style="width:315px"></html:text>
                    </td></tr>
            </table>







            <div id="conditionofdisabilityvisual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="0" cellpadding="0" border="2" class="inputform" width="90%">
                    <tr>
                        <td  width="34%">1.1 Condition of Disability<font color="red"><b>*</b></font></td>
                        <td >
                            <html:select property="conditiondisability" style="width:315px"  styleId="conditiondisabilityvisual" name="partAForm" onchange="disabile()">
                                <html:option  value="">----------------------SELECT----------------------</html:option>
                                <html:option value="1">Permanent,Progressive,Not-likely to improve </html:option>
                                <html:option value="2">Permanent,Non-progressive,Not likely to improve</html:option>
                                <html:option value="3">Temporary,Non-progressive,Likely to improve</html:option>
                            </html:select>
                        </td>
                    </tr>

                    <tr>
                        <td  width="34%">Period of Reassessment<font color="red"><b>*</b></font></td>
                        <td><html:select property="yearsfortemporary" styleId="yearsfortemporary" name="partAForm" disabled="true">
                                <html:option value="0">--SELECT--</html:option>
                                <html:option value="1">1</html:option>
                                <html:option value="2">2</html:option>
                                <html:option value="3">3</html:option>
                                <html:option value="4">4</html:option>
                                <html:option value="5">5</html:option>
                            </html:select></td>

                    </tr>
                </table>
            </div>

            <div id="causeOfDisabilityVisual" style="visibility:hidden;display:none">
                <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                    <tr>
                        <td width="25%" colspan="5">1.2 Causes of Disability
                        </td>


                    </tr>
                    <tr>
                        <td width="34%" > Congenital
                        </td>
                        <td  > Better Eye
                        </td>
                        <td> <html:checkbox property="congenitalbettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="congenitalworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>
                    <tr>
                        <td > Hereditary
                        </td>
                        <td > Better Eye
                        </td>
                        <td> <html:checkbox property="hereditarybettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="hereditaryworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td > Birth Injury
                        </td>
                        <td > Better Eye
                        </td>
                        <td> <html:checkbox property="birthinjurybettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="birthinjuryworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td > Disease and Infection
                        </td>
                        <td > Better Eye
                        </td>
                        <td> <html:checkbox property="diseaseInfectionbettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="diseaseInfectionworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td > Malnutrition
                        </td>
                        <td > Better Eye
                        </td>
                        <td> <html:checkbox property="malnutritionbettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="malnutritionworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>
                    <tr>
                        <td > Accident
                        </td>
                        <td > Better Eye
                        </td>
                        <td> <html:checkbox property="accidentbettereye" value="1"> </html:checkbox>
                        </td>
                        <td > Worse Eye
                        </td>
                        <td> <html:checkbox property="accidentworseeye" value="1"> </html:checkbox>
                        </td>
                    </tr>


                </table>
            </div>


            <div id="causeOfDisabilityOther" style="visibility:visible;display:inline">
                <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%">
                    <tr>
                        <td colspan="4" width="32%">1.2 Causes of Disability</td>
                    </tr>
                    <tr>
                        <td   width="34%"> Congenital
                        </td>
                        <td> <html:checkbox property="congenital" value="1" style="width:25px"> </html:checkbox>
                        </td>

                        <td  > Hereditary
                        </td>
                        <td><html:checkbox property="hereditary" value="1" style="width:25px"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td  > Birth Injury
                        </td>
                        <td><html:checkbox property="birthinjury" value="1" style="width:25px"> </html:checkbox>
                        </td>

                        <td  > Birth Asphyxia
                        </td>
                        <td><html:checkbox property="birthasphyxia" value="1" style="width:25px"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td  > Meningitis
                        </td>
                        <td><html:checkbox property="highfever" value="1" style="width:25px"> </html:checkbox>
                        </td>

                        <td  > Epilepsy
                        </td>
                        <td><html:checkbox property="epilepsy" value="1" style="width:25px"> </html:checkbox>
                        </td>
                    </tr>

                    <tr>
                        <td  > Disease and Infection
                        </td>
                        <td> <html:checkbox property="diseaseInfection"  value="1" style="width:25px"> </html:checkbox>
                        </td>

                        <td  > Malnutrition
                        </td>
                        <td> <html:checkbox property="malnutrition"value="1" style="width:25px"></html:checkbox>
                        </td>
                    </tr>
                    <tr>
                        <td > Accident
                        </td>
                        <td colspan="3"><html:checkbox property="accident" value="1"  style="width:25px"> </html:checkbox>
                        </td>
                    </tr>

                </table>
            </div>
            <table  align="center" cellspacing="1" cellpadding="0" border="0" class="inputform" width="90%" width="34%" id="hideTB">
                <tr>
                    <td  width="34%">Any other Cause of Disability</td>
                    <td><html:text property="otherconditionofdisability" style="width:300px"  maxlength="75"></html:text></td>
                </tr>
                <%-- </table>
                 <br>
                 <table align="center" id="hideTB" >--%>
                <tr>
                    <th colspan="4" align="center">
                        <html:submit value="Submit" onclick="return multipleDoctors();" styleId="subDisablButton" styleClass="button" />&nbsp;&nbsp;
                        <html:reset value="Reset" styleClass="button"/>
                    </th>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>
