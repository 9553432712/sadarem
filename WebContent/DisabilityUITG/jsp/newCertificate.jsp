<%--
    Document   : newCertificate
    Created on : Nov 01, 2012, 3:55:11 PM
    Author     : 310926
--%>



<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

 




<% String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
            //String hospitalname= (String)request.getAttribute("HospitalName");
            String districtId = (String) request.getAttribute("districtId");
            String districtName = (String) request.getAttribute("districtName");



%>
<style type="text/css">
    #fifteenth{position: absolute;width: 150px;border: 1px solid gray;padding: 2px;visibility: hidden;z-index: 99;}
</style>
<div id="fifteenth"></div>
<html:html>
    <head>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/newCertificatePartADetails.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForNewcertificatePerson.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForNewcertificateFather.js"></script>

        <script>
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function rationType(){
                var rationCardNumber = document.getElementById("card");
                var cardnumber = null;
                var cardnumberthree = null;
                if(rationCardNumber != null){
                    var rationCardNumberValue =rationCardNumber.value;
                    if(rationCardNumberValue != "" && rationCardNumberValue.toString().length<=3){
                        cardnumber = rationCardNumberValue.toString().toUpperCase();
                        rationCardNumber.value=cardnumber;
                        document.getElementById("rtype").selectedIndex = "";
                        document.getElementById("rtype").disabled = true;
                    }
                    if(rationCardNumberValue != "" && (rationCardNumberValue.toString().length == 3 || rationCardNumberValue.toString().length >= 3)){
                        cardnumberthree = rationCardNumberValue.toString().toUpperCase().substring(0,3);
                        document.getElementById("rtype").disabled = false;
                        if(cardnumber != null || cardnumberthree != null){
                            if(cardnumber == "WAP" || cardnumberthree == "WAP"){
                                document.getElementById("rtype").selectedIndex = 1;
                            }else if(cardnumber == "PAP" || cardnumberthree == "PAP"){
                                document.getElementById("rtype").selectedIndex = 2;
                            }else if(cardnumber == "AAY" || cardnumberthree == "AAY"){
                                document.getElementById("rtype").selectedIndex = 3;
                            }else if(cardnumber == "AAP" || cardnumberthree == "AAP"){
                                document.getElementById("rtype").selectedIndex = 4;
                            }else if(cardnumber == "YAP" || cardnumberthree == "YAP"){
                                document.getElementById("rtype").selectedIndex = 5;
                            }else if(cardnumber == "TAP" || cardnumberthree == "TAP"){
                                document.getElementById("rtype").selectedIndex = 6;
                            }else if(cardnumber == "RAP" || cardnumberthree == "RAP"){
                                document.getElementById("rtype").selectedIndex = 7;
                            }else if(cardnumber == "WAD" || cardnumberthree == "WAD"){
                                document.getElementById("rtype").selectedIndex = 8;
                            }
                        }
                    }else if(rationCardNumberValue == ""){
                        document.getElementById("rtype").selectedIndex = "";
                        document.getElementById("rtype").disabled = true;
                    }
                }
            }


            <!-------Starts script Allow Alphabets and Numerics For RationCardNumber-------------------->


            function isAlphaNumericRationCard(keyCode,name) {
                if (keyCode == 16 )
                    isShift = true;
                var str = name.value;
                if(str.substring(0,1)=="" || str.substring(0,1)=="W" || str.substring(0,1)=="w" ||
                    str.substring(0,1)=="A" || str.substring(0,1)=="a" ||
                    str.substring(0,1)=="P" || str.substring(0,1)=="p" ||
                    str.substring(0,1)=="Y" ||  str.substring(0,1)=="y")
                {
                    var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
                        keyCode == 87 || keyCode == 65 || keyCode == 80 || keyCode == 89 || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                }else{
                    name.value="";
                    name.focus();
                    var res = (keyCode == 8);

                }
                return res;
            }

            function modelesswin(url,mwidth,mheight){
                if (document.all&&window.print) //if ie5
                    eval('window.showModelessDialog(url,"devi","help:0;resizable:1;dialogWidth:'+mwidth+'px;dialogHeight:'+mheight+'px")')
                else
                    eval('window.open(url,"devi","width='+mwidth+'px,height='+mheight+'px,resizable=0,scrollbars=0")')
            }

            <!-- Numbers allow only 0 to 100----------->
            function isNumber100(field) {
                var re = /^[0-9]*$/;
                if (!re.test(field.value)) {
                    alert('Value must be an integer number!')
                    field.value = field.value.replace(/D/g,"");
                    field.value ='';
                    field.focus();
                }
                if(Number(field.value)>100){
                    alert('Value must be between 0 and 100!')
                    field.value ='';
                    field.focus();
                }
            }



            function removeLists(start,end){
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            }function createDistrictObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getDistrictDetails;
                var url="getTerritory.do?parameter=getTerritoryList&territory=1";
                x.open("Get", url, true);
                x.send();
            }
            function getDistrictDetails()
            {
                var res1,res2;
                removeall("district_id");

                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var countss=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                    z=1;
                    while(z<=countss)
                    {
                        res1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        res2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(res1,res2,"district_id");
                        z++;
                    }
                }
            }

            function districtList(){

                var disid = document.getElementById("district_id").value;
                if(disid == ""){
                    removeLists(2,6);
                }else{
                    createMandalObjectDetails();
                }
            }

            function  createMandalObjectDetails()
            {

                x=GetXmlHttpObject()
                x.onreadystatechange=getMandalDetailsPWD;
                var distid=document.getElementById("district_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";
                x.open("GET",url,true)
                x.send();
            }
            function getMandalDetailsPWD()
            {
                var rs1,rs2;

                removeall("mandal_id");
                removeall("village_id");
                document.forms[0].mandal_id.value="0";
                document.forms[0].village_id.value="0";
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"mandal_id");

                        z++;
                    }
                }
            }
            function mandalList(){
                var manid=document.getElementById("mandal_id").value;

                if(manid==""){
                    removeLists(4,6);
                }else{
                    createVillageObjectDetails();
                }
            }


            function getPanchayatList() {
                createPanchayatObject();
            }
            var x = null;
            function  createPanchayatObject()
            {

                x = GetXmlHttpObject()
                x.onreadystatechange=getPanchayatDetails;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.getElementById("district_id").value+"&mandalid="+document.getElementById("mandal_id").value+"&territory=4";


                x.open("GET",url,true)
                x.send();
            }

            function getPanchayatDetails()
            {
                var rs1,rs2;
                removeall("panchayat_id");
                removeall("habitation_id");
                removeall("village_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"panchayat_id");
                        z++;
                    }
                }
            }

            function panchayatList(){
                var villageid = document.getElementById("panchayat_id").value;
                var slcBx = document.getElementById("3");
                document.getElementById("panchayat").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(6,6);
                }else{
                    createHabitationObject();
                }
            }

            function  createVillageObject()
            {
                //var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;

                var pan=document.getElementById("panchayat_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.getElementById("district_id").value+"&mandalid="+document.getElementById("mandal_id").value+"&panchayatid="+pan+"&territory=7";


                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
                // removeall("panchayat_id");
                removeall("habitation_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                }
            }

            function villagesList(){
                var villageid = document.getElementById("village_id").value;
                var slcBx = document.getElementById("4");
                document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(5,6);
                }else{
                    createPanchayatObject();
                }
            }






            function  createHabitationObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;

                var villageid = document.getElementById("village_id").value;
                var panchayat = document.getElementById("panchayat_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.getElementById("district_id").value+"&mandalid="+document.getElementById("mandal_id").value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=8";
                x.open("GET",url,true)
                x.send();
            }

            function getHabitationDetails()
            {
                var rs1,rs2;
                removeall("habitation_id");
                if(x.readyState==4 || x.readyState=="complete")
                {
                    var m=x.responseXML.documentElement;
                    var z=0;
                    var counts=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                    m.getElementsByTagName("id")[z].childNodes[0].nodeValues;
                    z=1;
                    while(z<=counts)
                    {
                        rs1=m.getElementsByTagName("name")[z].childNodes[0].nodeValue;
                        rs2=m.getElementsByTagName("id")[z].childNodes[0].nodeValue;
                        addoption(rs1,rs2,"habitation_id");
                        z++;
                    }
                }
            }
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("option");
                opt.text=result1;
                opt.value=result2;
                try {
                    document.getElementById(name).add(opt);
                }catch(ex)
                {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }else if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }
                }
            }
            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }else if(name=="panchayat_id") {
                    var x1=document.forms[0].panchayat_id.options.length;
                }
                else if(name=="district_id") {
                    document.forms[0].district_id.options[i]=null;
                }
                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    } else if(name=="habitation_id") {
                        document.forms[0].habitation_id.options[i]=null;
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.options[i]=null;
                    }else if(name=="district_id") {
                        document.forms[0].district_id.options[i]=null;
                    }
                }
            }

            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }


            function getPersonCodeList(){
                var personstatus = document.getElementById("7").value;
                var slcBx = document.getElementById("6");
                document.getElementById("habitation_name").value = slcBx.options[slcBx.selectedIndex].text;
                if(personstatus == 0 || personstatus == ""){
                    removeLists(8,8);
                }
            }


            <!--Start-->
            function checkFormValidations(){

                if(document.forms[0].elements['formno'].value=="") {
                    alert("Please Enter Form No.");
                    document.forms[0].elements['formno'].focus();
                    return false;
                }else if(document.forms[0].elements['surname'].value=="") {
                    alert("Please Enter Surname");
                    document.forms[0].elements['surname'].focus();
                    return false;
                } else if(document.forms[0].elements['firstname'].value=="") {
                    alert("Please Enter Name");
                    document.forms[0].elements['firstname'].focus();
                    return false;
                } else if(document.forms[0].elements['surnameenglish'].value=="") {
                    alert("Please Enter SurnameEnglish");
                    document.forms[0].elements['surnameenglish'].focus();
                    return false;
                }else if(document.forms[0].elements['noOfYears'].value=="") {
                    alert("Please Enter Age in Years");
                    document.forms[0].elements['noOfYears'].focus();
                    return false;
                }else if(document.forms[0].elements['day'].value=="") {
                    alert("Please Enter Day");
                    document.forms[0].elements['day'].focus();
                    return false;
                }else if(document.forms[0].elements['month'].value=="0") {
                    alert("Please Enter month");
                    document.forms[0].elements['month'].focus();
                    return false;
                }else if(document.forms[0].elements['year'].value=="") {
                    alert("Please Enter year");
                    document.forms[0].elements['year'].focus();
                    return false;
                }else if(document.forms[0].elements['gender'].value=="") {
                    alert("Please Enter Gender");
                    document.forms[0].elements['gender'].focus();
                    return false;
                }

            <%--else if(document.forms[0].elements['education'].value=="") {
                alert("Please Enter Education");
                document.forms[0].elements['education'].focus();
                return false;
            }
            else if(document.forms[0].elements['employment'].value=="") {
                alert("Please Enter Employment");
                document.forms[0].elements['employment'].focus();
                return false;
            }--%>
                    else if(document.forms[0].elements['marital'].value=="") {
                        alert("Please Enter Marital");
                        document.forms[0].elements['marital'].focus();
                        return false;
                    }
            <%--else if(document.forms[0].elements['caste'].value=="") {
                alert("Please Enter Caste");
                document.forms[0].elements['caste'].focus();
                return false;
            }
            else if(document.forms[0].elements['religion'].value=="") {
                alert("Please Enter Religion");
                document.forms[0].elements['religion'].focus();
                return false;
            }--%>
            <%--else if(document.forms[0].elements['card'].value=="") {
                alert("Please Enter RationCardNo!");
                document.forms[0].elements['card'].focus();
                return false;
            }else if(document.forms[0].elements['rtype'].value=="0") {
                alert("Please Enter RationCardType!");
                document.forms[0].elements['rtype'].focus();
                return false;
            }
            else if(document.forms[0].elements['rationCardSlno'].value=="") {
                alert("Please Enter RationCardSlNo!");
                document.forms[0].elements['rationCardSlno'].focus();
                return false;
            }--%>

                    else if(document.forms[0].elements['mole1'].value=="") {
                        alert("Identification Marks(Moles) must be Entered!");
                        document.forms[0].elements['mole1'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['mole2'].value=="") {
                        alert("Identification Marks(Moles) must be Entered!");
                        document.forms[0].elements['mole2'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['parents_marriage'].value=="") {
                        alert("Consanguineous Marriage of Parents!");
                        document.forms[0].elements['parents_marriage'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['gsurname'].value=="") {
                        alert("PWD FatherName must be Entered!");
                        document.forms[0].elements['gsurname'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['grelation'].value=="") {
                        alert("Please Select  Relation!");
                        document.forms[0].elements['grelation'].focus();
                        return false;
                    } else if(document.forms[0].elements['firstnameenglish'].value=="") {
                        alert("Father/Mother/Husband/Guardian's Name must be Entered!!");
                        document.forms[0].elements['firstnameenglish'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['firstnametelugu'].value=="") {
                        alert("Father/Mother/Husband/Guardian's Name must be Entered!!");
                        document.forms[0].elements['firstnametelugu'].focus();
                        return false;
                    }
            <%--else if(document.forms[0].elements['houseno'].value=="") {
                alert("Please Enter HouseNo!");
                document.forms[0].elements['houseno'].focus();
                return false;
            }--%>
                    else if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District!");
                        document.forms[0].elements['district_id'].focus();
                        return false;
                    }else if(document.forms[0].elements['mandal_id'].value=="0") {
                        alert("Please Select Mandal!");
                        document.forms[0].elements['mandal_id'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['panchayat_id'].value=="0") {
                        alert("Please Select Panchayat!");
                        document.forms[0].elements['panchayat_id'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['village_id'].value=="0") {
                        alert("Please Select Village!");
                        document.forms[0].elements['village_id'].focus();
                        return false;
                    }
                    else if(document.forms[0].elements['habitation_id'].value=="0") {
                        alert("Please Select Habitation!");
                        document.forms[0].elements['habitation_id'].focus();
                        return false;
                    }
            <%--else if(document.forms[0].elements['phoneno'].value=="") {
                alert("Please Enter Phone Number!");
                document.forms[0].elements["phoneno"].value="";
                document.forms[0].elements["phoneno"].focus();
                return false;
            }--%>
            <%--else if(document.forms[0].elements['emailId'].value=="") {
                alert("Please Enter Email!");
                document.forms[0].elements['emailId'].focus();
                return false;
            }--%>
            <%--else if(document.forms[0].elements['pin'].value=="") {
                alert("Please Enter Pin!");
                document.forms[0].elements['pin'].focus();
                return false;
            }--%>
            <%-- else if(document.forms[0].elements['type_disability'].value=="") {
                 alert("Please Enter TypeOf Disability!");
                 document.forms[0].elements['type_disability'].focus();
                 return false;
             }--%>
            <%--else if(document.forms[0].elements['existingpercentage'].value=="") {
                alert("Please Enter Existingpercentage!");
                document.forms[0].elements['existingpercentage'].focus();
                return false;
            }--%>

                    else {
                        document.getElementById('subDisablButton').disabled = true;
                        document.forms[0].mode.value ="insertPartADetails";
                        document.forms[0].submit();
                    }

                }

                <!--End-->

                function formValidation1() {
                    <!-- Email Validation-->
                    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                    var address = document.forms[0].elements['emailId'].value;

                    if(reg.test(address) == false) {

                        alert('Invalid Email Address!');
                        document.forms[0].elements['emailId'].value="";
                        document.forms[0].elements["emailId"].focus();
                        return false;

                    }

                    <!-- End for email Validation-->
                }




        </script>
    </head>
    <body  onload="currentdate(),validateepiccheckbox(),generateDob(),rationType()" bgcolor="#f2f0e6" style="margin:0px;padding:0px;">

        <html:form action="/newCertificate"  enctype="multipart/form-data">
            <br>

            <html:hidden property ="mode"/>
            <input type="hidden" name="district_id" value="<%=(String) request.getAttribute("districtId")%>"/>


            <logic:present name="msg">
                <center><font>${msg}</font></center>
            </logic:present>

            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                    <td align="center" class="heading">Enter Personal Data</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="85%">

                <tr>
                    <td class="label">Form No<font color="red"><b>*</b></font></td>
                    <td class="label">
                        <html:text property="formno"   maxlength="25"
                                   onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"
                                   /></td>
                    <td class="label">Date of Assessment<font color="red"><b>*</b></font></td>
                    <td class="label">
                        <html:text property="fromdate"  readonly="true" />
                        <a  href="javascript:show_calendar('forms[0].fromdate');" onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="5" border="1" class="innerTable1" width="85%">

                <tr>
                    <td class="labelBlue" colspan="4">1.0 Individual Details</td>
                </tr>
                <tr>
                    <td class="label" colspan="4">1.1  Name of the Person</td>
                </tr>
                <tr>
                    <td  class="label">Surname<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="surname"  maxlength="25" onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)"/>
                    </td>
                    <td class="label">
                        Name<font color="red"><b>*</b></font>
                    </td>
                    <td>
                        <html:text property="firstname"  maxlength="25" onkeydown="return isAlpha(event.keyCode,firstname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)" readonly="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="label"><b><font color="red"><%=personname%></font></b></td>
                    <td>
                        <input type="text" onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()"
                               name="surnameenglish" maxlength="30" onkeydown="return isAlpha(event.keyCode,surnameenglish);" onkeypress="return space(event,this)"
                               onmouseover="messagedisplay(2,'surnameenglish')" />
                    </td>
                    <td class="label"><b><font color="red"><%=telugu%></font></b></td>
                    <td>
                        <html:text  property="surnametelugu"  readonly="true"/>
                        <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" id="telugu" name="telugupersonname" />
                    </td>
                </tr>
                <tr>
                    <td class="label">1.2 Age<font color="red"><b>*</b></font></td>
                    <td class="label" colspan="3">Years &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text
                            property="noOfYears" size="6" maxlength="2"
                            onkeydown="return isNumeric(event.keyCode);"
                            onkeyup="generateDob(this);" onkeypress="return space(event,this)"   />
                    </td>
                </tr>
                <tr>
                    <td class="label">Date of Birth<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label">
                        Day &nbsp;&nbsp;&nbsp;<html:select  property="day" >
                            <%
                                        int i = 0;
                                        for (i = 1; i <= 31; i++) {
                            %>
                            <html:option value="<%=String.valueOf(i)%>"><%=i%></html:option>
                            <%
                                        }
                            %>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;Month &nbsp;&nbsp;&nbsp;
                        <html:select property="month"  >
                            <html:option value="1">January</html:option>
                            <html:option value="2">February</html:option>
                            <html:option value="3">March</html:option>
                            <html:option value="4">April</html:option>
                            <html:option value="5">May</html:option>
                            <html:option value="6">June</html:option>
                            <html:option value="7">July</html:option>
                            <html:option value="8">August</html:option>
                            <html:option value="9">September</html:option>
                            <html:option value="10">October</html:option>
                            <html:option value="11">November</html:option>
                            <html:option value="12">December</html:option>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;Year &nbsp;&nbsp;&nbsp;
                        <html:select property="year" styleId="year" onchange="calculateAge(this)"  >
                            <% for (int j = 2020; j > 1850; j--) {
                            %>
                            <html:option value="<%= String.valueOf(j)%>"><%= j%></html:option>
                            <%
                                        }
                            %>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td class="label">1.3 Gender<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="textbox">
                        <html:select property="gender" styleId="gender" onchange="changeothercombo(this.options.selectedIndex)">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Male</html:option>
                            <html:option value="2">Female</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.4 Education<font color="red"></font></td>
                    <td colspan="3" class="textbox">
                        <html:select property="education">
                            <html:option value="0">--SELECT--</html:option>
                            <html:option value="1">Illiterate</html:option>
                            <html:option value="2">Below 10th</html:option>
                            <html:option value="3">10th Class</html:option>
                            <html:option value="4">Intermediate</html:option>
                            <html:option value="5">Diploma/ITI</html:option>
                            <html:option value="6"> Graduate</html:option>
                            <html:option value="7">Post Graduate</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.5 Employment<font color="red"></font></td> 
                    <td colspan="3" class="textbox">
                        <html:select property="employment">
                            <html:option value="0">--SELECT--</html:option>
                            <html:option value="1">Govt</html:option>
                            <html:option value="2">Private</html:option>
                            <html:option value="3">Self-Employed</html:option>
                            <html:option value="4">Un-Employed</html:option>
                            <html:option value="5">Wage-Employee</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.6 Marital Status<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="textbox">
                        <html:select property="marital" >
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Married</html:option>
                            <html:option value="2">Un-Married</html:option>
                            <html:option value="3">Divorcee</html:option>
                            <html:option value="4">Widow</html:option>
                            <html:option value="5">Widower</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.7 Caste<font color="red"></font></td>
                    <td colspan="3" class="textbox">
                        <html:select property="caste">
                            <html:option value="6">--SELECT--</html:option> 
                            <html:option value="1">ST</html:option>
                            <html:option value="2">SC</html:option>
                            <html:option value="3">BC</html:option>
                            <html:option value="4">OC</html:option>
                            <html:option value="5">Minority</html:option>
                            <html:option value="6">NA</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.8 Religion<font color="red"></font></td>
                    <td colspan="3" class="textbox">
                        <html:select property="religion"  >
                            <html:option value="7">--SELECT--</html:option>
                            <html:option value="1">Hindu</html:option>
                            <html:option value="2">Muslim</html:option>
                            <html:option value="3">Christian</html:option>
                            <html:option value="4">Sikh</html:option>
                            <html:option value="5">Jain</html:option>
                            <html:option value="6">Budhist</html:option>
                            <html:option value="7">Others</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.9 Ration Card No<font color="red"></font></td>
                    <td class="textbox">

                        <% if (request.getAttribute("RationReadOnly") != null) {
                        %>
                        <html:text property="card" size="20" maxlength="20"
                                   onkeyup="rationType()" onkeypress="return space(event,this)" readonly="true"/>
                        <%                             } else {%>
                        <html:text property="card" size="20" maxlength="20"
                                   onkeyup="rationType()" onkeypress="return space(event,this)"/>
                        <%}%>
                    </td>
                    <td class="label">Type</td>
                    <td>
                        <html:select property="rtype"  onchange="rationType()">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">White</html:option>
                            <html:option value="2">Pink</html:option>
                            <html:option value="3">Anthyodaya</html:option>
                            <html:option value="4">Annapurna</html:option>
                            <html:option value="5">Yellow</html:option>
                            <html:option value="6">Temporary</html:option>
                            <html:option value="7">Rachabanda</html:option>
                            <html:option value="8">White</html:option>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Slno  

                        <% if (request.getAttribute("SlNoReadOnly") != null) {
                        %>
                        <html:text property="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();"  size="2" readonly="true"/>
                        <%                             } else {%>
                        <input type="hidden" name="readonly"/>
                        <html:text property="rationCardSlno" maxlength="2" onkeypress="return onlyNumbers();" size="2"/>
                        <%}%>

                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                            <tr>
                                <td class="label" width="36%">1.10 EPIC Card</td>
                                <td class="label" width="15%">Click if Yes <html:checkbox  property="epiccard"
                                                value="true"  onclick="validateepiccheckbox(this.value)"/>
                                </td>
                                <td width="50%">
                                    <div id="epic" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                                            <tr>
                                                <td class="label" align="center">EPIC Number  &nbsp;&nbsp;
                                                    <html:text property="epiccardno"  maxlength="20" onkeypress="return letternumber(event)"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                            <tr>
                                <td width="36%" class="label">1.11 Pension Card <font color="red"></font></td>
                                <td width="15%" class="label">Click if Yes
                                    <html:checkbox  property="pensioncard" value="true"  onclick="validatepensioncheckbox(this.value)" styleId="1"/>
                                </td>
                                <td width="50%">
                                    <div id="pension" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                                            <tr>
                                                <td class="label">Pension Type</td>
                                                <td>
                                                    <html:select property="pension_type"   styleId="2">
                                                        <html:option value="">--SELECT--</html:option>
                                                        <html:option value="Disabled">Disabled</html:option>
                                                        <html:option value="Old Age">O.A.P</html:option>
                                                        <html:option value="Widow">Widow</html:option>
                                                        <html:option value="Weavers">Weavers</html:option>
                                                    </html:select>
                                                </td>
                                                <td class="label">Pension Number</td>
                                                <td class="textbox"><html:text property="pensioncardno"  maxlength="7" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.12 Identification Marks<font color="red"><b>*</b></font></td>
                    <td class="label">1)
                        <html:text property="mole1" size="25"  maxlength="50"   onkeydown="return isAlpha(event.keyCode,mole1);" onkeypress="return space(event,this)"  onchange="capitalizeMe(this)"/>
                    </td>
                    <td colspan="2" class="label">2)
                        <html:text property="mole2" size="25" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole2);" onkeypress="return space(event,this)" onchange="capitalizeMe(this)"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">1.13 Consanguineous Marriage of Parents</td>
                    <td colspan="3" class="label">
                        <html:radio  property="parents_marriage" value ="1" >Yes</html:radio> &nbsp;&nbsp;
                        <html:radio  property="parents_marriage" value ="0" >No</html:radio>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr>
                    <td colspan="4" class="labelBlue">2.0 Family Details</td>
                </tr>
                <tr>
                    <td  class="label" width="33%">2.1 Father/Mother/Husband/Guardian's Name<font color="red"><b>*</b></font></td>
                    <td  class="label">
                        <html:text property="gsurname" size="18" maxlength="30" onkeydown="return isAlpha(event.keyCode,gsurname);" onkeypress="return space(event,this)"
                                   onchange="capitalizeMe(this)" />
                    </td>
                    <td class="label"> Relation type<font color="red"><b>*</b></font> </td>
                 <td>
                        <html:select property="grelation" styleId="grelation" >
                           <html:option value="">--SELECT--</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label"><font size="2" color="red"><b><%=fathername%></b></font></td>
                    <td><input type="text"   onkeyup="javascript1:first_many_words()"
                               onfocus="javascript1:first_many_words()" name="firstnameenglish" maxlength="30"
                               onkeydown="return isAlpha(event.keyCode,firstnameenglish);" onkeypress="return space(event,this)" />
                    </td>
                    <td><font size="2" color="red"><b> <%=telugu%></b></font></td>
                    <td>
                        <html:text   property="firstnametelugu"  readonly="true"/>
                        <a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" id="telugu" name="telugufathername" />
                    </td>
                </tr>
                <tr>
                    <td class="labelBlue" colspan="4">3.0 Address (As recorded in RATION CARD)</td>
                </tr>
                <tr>
                    <td colSpan=4>
                        <table border="0"  cellspacing="0" cellpadding="9"  class="innterTable1" width="85%">

                            <tr>
                                <td width="2%" rowspan="9"></td>
                                <td class="label" width="30%"> House No.<font color="red"></font></td>
                                <td class="label" width="52%"><html:text  property="houseno" maxlength="15" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,houseno)"  onkeypress="return space(event,this)"> </html:text></td>
                            </tr>


                            <tr>
                                <td class="label">District<font color="red"><b>*</b></font></td>
                                <td class="label"><html:text  property="district"  readonly="true"></html:text></td>
                            </tr>

                            <tr>
                                <td class="label">Mandal<font color="red"><b>*</b></font></td>
                                <td class="label">
                                    <html:select styleId="7" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                                        <html:option  value="0">--Mandals--</html:option>
                                        <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr>
                                <td  class="label">Panchayat<font color="red"><b>*</b></font> </td>
                                <td class="label">
                                    <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                                        <html:option  value="0">--Panchayat--</html:option>

                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td  class="label">Town/Village<font color="red"><b>*</b></font> </td>
                                <td class="label">
                                    <html:select property="village_id" style="width:160px;" onchange="createHabitationObject();">
                                        <html:option value="0">--Town/Village--</html:option>
                                        <html:optionsCollection property="villagelist" label="village_name" value="village_id"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td class="label">Habitation/Ward No<font color="red"><b>*</b></font> </td>
                                <td class="label">
                                    <html:select styleId="6" property="habitation_id" style="width:160px;">
                                        <html:option  value="0">--Habitation/Ward No--</html:option>
                                        <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr>
                                <td class="label">Phone No<font color="red"></font></td>
                                <td class="label"><html:text  property="phoneno"  maxlength="10" onkeypress="return onlyNumbers();"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">E-mail<font color="red"></font></td>
                                <td class="label"><html:text  property="emailId"  maxlength="50" onchange="return formValidation1();"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">Pin<font color="red"></font></td>
                                <td class="label"><html:text  property="pin" maxlength="6" onkeypress="return onlyNumbers();"></html:text></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td colspan="2" class="labelBlue" width="32%">4.0 Type of Disability<font color="red"></font></td>
                    <td>
                        <html:select property="type_disability" >
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Locomotor/OH</html:option>
                            <html:option value="2">Visual Impairment</html:option>
                            <html:option value="3">Hearing Impairment</html:option>
                            <html:option value="4">Mental Retardation</html:option>
                            <html:option value="5">Mental Illness</html:option>
                            <html:option value="6">Multiple Disabilities</html:option>
                        </html:select>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td class="labelBlue" width="32%">5.0 Existing Percentage<font color="red"></font></td>
                    <td><html:text property="existingpercentage" maxlength="3" onkeypress="return onlyNumbers();" onkeyup="isNumber100(this)"/> %</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td  class="labelBlue" width="32%">6.0 Person Status<font color="red" size="2"><b>*</b></font></td>
                    <td class="label"><input type="radio" name="personstatus"  value="Eligible" checked="true" />Eligible
                        <input type="radio"  name="personstatus"  value="Rejected" />Rejected</td>

                </tr>
                <tr>
                    <td class="labelBlue" width="32%">7.0 Aadhar Card No</td>
                    <td ><html:text property="aadharCardNo" onkeypress="return onlyNumbers();" onkeyup="return isNumeric(event.keyCode);" maxlength="12" style="width:200px" onkeyup="validateAadhar(this.value);"/>

                    </td>
                    <td colspan="2">
                        <div id="data"></div>
                        <div id="loadImg"></div>
                    </td>
                </tr>
            </table>
            <br><br>

            <center>
                <html:submit value="Submit" styleId="subDisablButton" styleClass="button"  onclick="return checkFormValidations()" />&nbsp;&nbsp;
                <html:reset value="Reset" styleClass="button"/>
            </center>
            <logic:present name="closewindow">
                <script>
                    window.opener.location.reload();
                    window.close();
                </script>
            </logic:present>
        </html:form>
    </body>
        <script src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
   

    <script>
        // The below two methods are for getting the table data

        function validateAadhar(aadharId) {
            if(aadharId.toString().length=="12") {
                document.getElementById("loadImg").innerHTML="<img src=\"./DisabilityUITG/images/ajax-loader.gif\">Aadhar Number Validating Please Wait.....";

                $.ajax({
                    url : "insertPartAaction.do?insertPersonalDetails=validateAadharNumberWithAadharDB",
                    type: "GET",
                    data : "aadharNo="+aadharId,
                    success: function(data, textStatus, jqXHR)
                    {
                        if(data=="success") {
                           // document.getElementById("subDisablButton").disabled=false;
                            $("#data").html("<font color=green size=2><b>Given Aadhar Number Validated Successfully.</b></font>");//data - response from server
                            document.getElementById("loadImg").innerHTML="";
                        }
                        if(data == "fail") {
                           // document.getElementById("subDisablButton").disabled=true;
                            $("#data").html("<font color=red size=2><b>Given Aadhar Number is Not Valid Number.</b></font>");//data - response from server
                            document.getElementById("loadImg").innerHTML="";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        alert(errorThrown);
                    }
                });
            }
        }
      var groups=document.getElementById("gender").options.length;
        
        
        var group=new Array(groups);
        for (i=0; i<groups; i++)
        	{
        		group[i]=new Array();
        	}

        group[0][0]=new Option("-1","----------Select----------");

        group[1][0]=new Option("-1","----------Select----------");
        group[1][1]=new Option("0","C/o Of");
        group[1][2]=new Option("1","S/o Of");
        group[1][3]=new Option("2","G/o Of");
        group[1][4]=new Option("3","H/o Of");


        group[2][0]=new Option("-1","----------Select----------");
        group[2][1]=new Option("0","C/o Of");
        group[2][2]=new Option("1","D/o Of");
        group[2][3]=new Option("2","G/o Of");
        group[2][4]=new Option("3","W/o Of");
        
        var temp=document.getElementById("grelation");

       // alert(temp.options.length);
        
        function changeothercombo(x)
        {

            for (m=temp.options.length-1;m>0;m--)
            temp.options[m]=null;
            for (i=0;i<group[x].length;i++)
            {
            temp.options[i]=new Option(group[x][i].value,group[x][i].text); 
            }
            temp.options[0].selected=true;
        } 
        $(document).ready( function()
        		{
        	
       	     var gendpos = document.getElementById("gender").options.selectedIndex;       	     
        	 changeothercombo(gendpos); 
        	
        		});
    </script>
</html:html>

<script>
    function goBack()
    {

        document.forms[0].mode.value="backRequest";
        document.forms[0].submit();

    }
</script>