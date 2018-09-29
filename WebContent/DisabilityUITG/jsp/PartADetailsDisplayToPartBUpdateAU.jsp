<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>
<% String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
            String teluguname = (String) session.getAttribute("teluguname");
            String telugufathername = (String) session.getAttribute("telugufathername");
            String Dirst = (String) request.getAttribute("district_name");
            String Mandal = (String) request.getAttribute("mandal_name");
            String vilage = (String) request.getAttribute("village_name");
            String habtation = (String) request.getAttribute("habitation_name");
            String panchayat = (String) request.getAttribute("panchayat_name");

            String district_id = (String) request.getAttribute("district_id");
            String mandal_id = (String) request.getAttribute("mandal_id");
            String village_id = (String) request.getAttribute("village_id");
            String habitation_id = (String) request.getAttribute("habitation_id");
            String panchayat_id = (String) request.getAttribute("panchayat_id");


%>
<html:html >
    <head>
        <script language="JavaScript">
 function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function enableAllProperties()
            {

                for(var i=1;i<=15;i++)
                //alert("print"+document.getElementById(i).value);
                    document.getElementById(i).disabled=false;
            }
            function generateDob()
            {
                var x=document.getElementById("noOfYears").value;
                if(x=="")
                {
                    alert("Enter Years");
                    document.forms[0].noOfYears.value="";
                    document.forms[0].noOfYears.focus();
                }
                else
                {
                    var d=new Date();
                    var year=d.getYear();
                    var dob_year=year-x;
                    document.getElementById("year").value=dob_year;
                    document.getElementById("month").value=d.getMonth()+1;
                    document.getElementById("day").value=d.getDate();
                }

            }
            function calculateAge()
            {
                var x=document.getElementById("year").value;
                var d=new Date();
                var year=d.getYear();
                var age=year-x;
                document.getElementById("noOfYears").value=age;
            }

            function disableCalculateDob()
            {


                document.forms[0].calculate_age.disabled = true;
            }


            function enableCalculateAge()
            {
                document.forms[0].calculate_age.disabled = false;

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

                    if (validate_required(formno,"Form No must be selected!")==false)
                    {
                        formno.focus();
                        return false
                    }
                    if (validate_required(fromdate,"Date must be selected!")==false)
                    {
                        fromdate.focus();
                        return false
                    }
                    var datetobevalidated= document.getElementById("fromdate").value;
                    var age1 =0;
                    var yy=datetobevalidated.substr(6,4);
                    var mm=datetobevalidated.substr(3,2);
                    var dd=datetobevalidated.substr(0,2);

                    var dob = new  Date();
                    var today=new Date();

                    dob.setFullYear(yy,mm-1,dd);

                    var y1=today.getYear();
                    var y2= dob.getYear();
                    if (today< dob )
                    {
                        alert("Please Check the Form Date")
                        fromdate.focus();
                        return (false);
                    }
                    <!-- validating doctor1 Details-->

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
                    <!--validating doctor2 Details-->

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
                    <!--validating doctor3 Details-->

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

                    if (validate_required(doctor1designation,"Doctor Designation must be Entered!")==false)
                    {
                        doctor1designation.focus();
                        return false
                    }
                    if (validate_required(surname,"Sur Name must be Entered!")==false)
                    {
                        surname.focus();
                        return false
                    }
                    if (validate_required(firstname,"First Name must be Entered!")==false)
                    {
                        firstname.focus();
                        return false
                    }

                    if (validate_required(mole1,"Identification Marks(Moles) must be Entered!")==false)
                    {
                        mole1.focus();
                        return false
                    }
                    if (validate_required(mole2,"Identification Marks(Moles) must be Entered!")==false)
                    {
                        mole2.focus();
                        return false
                    }
                    if (validate_required(noOfYears,"Age must be Entered!")==false)
                    {
                        noOfYears.focus();
                        return false
                    }

                    if (validate_required(gender,"Gender Name must be selected!")==false)
                    {
                        gender.focus();
                        return false
                    }

                    if (validate_required(marital,"Marital Status must be selected!")==false)
                    {
                        marital.focus();
                        return false
                    }
                    if (validate_required(gsurname,"Father/Mother/Husband/Guardian's Name must be Entered!")==false)
                    {
                        gsurname.focus();
                        return false
                    }
                    if (validate_required(grelation,"Relation must be selected!")==false)
                    {
                        grelation.focus();
                        return false
                    }

                    if (validate_required(surnametelugu,"PWD Telugu Name must be Enter!")==false)
                    {
                        surnameenglish.focus();
                        return false
                    }
                    if (validate_required(firstnametelugu,"Father/Mother/Husband/Guardian's Telugu Name must be Enter!")==false)
                    {
                        firstnameenglish.focus();
                        return false
                    }
                    if (validate_required(houseno,"House No must be Entered!")==false)
                    {
                        houseno.focus();
                        return false
                    }
                    if (validate_required(personstatus,"person status must be entered!")==false)
                    {
                        personstatus.focus();
                        return false
                    }
                }
            }


            function validateepiccheckbox()
            {
                if(document.partAForm.epiccard.checked)
                {
                    document.getElementById("epic").style.visibility = "Visible";
                    document.getElementById("epic").style.display = "Inline";
                }
                else
                {
                    document.getElementById("epic").style.visibility = "hidden";
                    document.getElementById("epic").style.display = "none";
                    document.forms[0].epiccardno.value ="";


                }
            }

            function validatepensioncheckbox()
            {
                if(document.partAForm.pensioncard.checked)
                {
                    document.getElementById("pension").style.visibility = "Visible";
                    document.getElementById("pension").style.display = "Inline";
                }
                else
                {
                    document.getElementById("pension").style.visibility = "hidden";
                    document.getElementById("pension").style.display = "none";
                    document.forms[0].pensioncardno.value ="";
                }
            }

            <!--Validation  For Numbers only in Candidate age-->

            function validatecandidateage(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,./{}|\":<>?";
                var noOfYears=document.partAForm.noOfYears.value;
                for (var i = 0; i < noOfYears.length; i++) {
                    if (iChars.indexOf(noOfYears.charAt(i)) != -1) {
                        alert ("Please Enter Valid Age.");
                        document.partAForm.noOfYears.value="";
                        document.partAForm.noOfYears.focus();
                        return false;
                    }
                }
                if(isNaN(noOfYears))
                {
                    alert("Enter Numbers only in your age")
                    document.partAForm.noOfYears.value="";
                    document.partAForm.noOfYears.focus();
                }
                if(parseInt(noOfYears)<1 ||
                    parseInt(noOfYears)> 120)
                {
                    alert ("Please enter yours valid age!");
                    document.partAForm.noOfYears.value="";
                    document.partAForm.noOfYears.focus();
                }
                return true;
            }


            <!--Validation  For Numbers only in Father age-->
            function validatefatherage()
            {
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,./{}|\":<>?";
                var gageYears=document.partAForm.gageYears.value;
                for (var i = 0; i < gageYears.length; i++) {

                    if (iChars.indexOf(gageYears.charAt(i)) != -1) {
                        alert ("Please Enter Valid Father Age.");
                        document.partAForm.gageYears.value="";
                        document.partAForm.gageYears.focus();
                        return false;
                    }
                }
                if(isNaN(gageYears))
                {
                    alert("Enter Numbers only in father age")
                    document.partAForm.gageYears.value="";
                    document.partAForm.gageYears.focus();
                }
                if(parseInt(gageYears)< 0 ||
                    parseInt(gageYears)> 120)
                {
                    alert ("Please enter a valid father age!");
                    document.partAForm.gageYears.value="";
                    document.partAForm.gageYears.focus();
                }
                return true;
            }


            function numbervalidation()
            {
                var iChars = "!@#$%^&*()+=[]\\\'`~;,./{}|\":<>?";
                var phoneno=document.partAForm.phoneno.value;
                for (var i = 0; i < phoneno.length; i++) {
                    if (iChars.indexOf(phoneno.charAt(i)) != -1) {
                        alert ("Please Enter Valid phone No Number.");
                        document.partAForm.phoneno.value="";
                        document.partAForm.phoneno.focus();
                        return false;
                    }
                }

                if(isNaN(phoneno))
                {
                    alert("Enter Valid Phone Number")
                    document.partAForm.phoneno.value="";
                    document.partAForm.phoneno.focus();
                }
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



            <!--Validation For Pin Number-->

            function validatepin(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,./{}|\":<>?e";
                var pin=document.partAForm.pin.value;
                for (var i = 0; i < pin.length; i++) {
                    if (iChars.indexOf(pin.charAt(i)) != -1) {
                        alert ("Please Enter Valid Pin Number.");
                        document.partAForm.pin.value="";
                        document.partAForm.pin.focus();
                        return false;
                    }
                }
                if(isNaN(pin))
                {
                    alert("Please Enter Valid Pin Number.")
                    document.partAForm.pin.value="";
                    document.partAForm.pin.focus();
                    return false;
                }
                return true;
            }




            function validatemole1()
            {
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?1234567890";
                var mole1=document.partAForm.mole1.value;
                for (var i = 0; i < mole1.length; i++) {

                    if (iChars.indexOf(mole1.charAt(i)) != -1) {
                        alert ("Please Enter Valid Identifacation Mole.");
                        document.partAForm.mole1.value="";
                        document.partAForm.mole1.focus();
                        return false;
                    }
                }
            }

            function validatemole2()
            {
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?1234567890";
                var mole2=document.partAForm.mole2.value;
                for (var i = 0; i < mole2.length; i++) {

                    if (iChars.indexOf(mole2.charAt(i)) != -1) {
                        alert ("Please Enter Valid Identifacation Mole.");
                        document.partAForm.mole2.value="";
                        document.partAForm.mole2.focus();
                        return false;
                    }
                }
            }
            <!--Validation  For Ration Card Type-->
            function selecttype()
            {
                var card=document.partAForm.card.value;
                var type=document.partAForm.rtype.selectedIndex;
                if(card!="")
                {
                    if(type==0)
                    {
                        alert ("Please enter Ration Type!");
                        document.partAForm.rtype.focus();
                        return false;
                    }
                }

                if(type!="")
                {
                    if(card==0)
                    {
                        alert ("Please enter Ration card Number!");
                        document.partAForm.rtype.focus();
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
            function validatesurname(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
                var surname=document.partAForm.surname.value;
                for (var i = 0; i < surname.length; i++) {
                    if (iChars.indexOf(surname.charAt(i)) != -1) {
                        alert ("Please Enter Valid Surname.");
                        document.partAForm.surname.value="";
                        document.partAForm.surname.focus();
                        return false;
                    }
                }
            }

            function validatefirstname(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
                var firstname=document.partAForm.firstname.value;
                for (var i = 0; i < firstname.length; i++) {
                    if (iChars.indexOf(firstname.charAt(i)) != -1) {
                        alert ("Please Enter Valid Firstname.");
                        document.partAForm.firstname.value="";
                        document.partAForm.firstname.focus();
                        return false;
                    }
                }
            }
            function validatelastname(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
                var lastname=document.partAForm.lastname.value;
                for (var i = 0; i < lastname.length; i++) {
                    if (iChars.indexOf(lastname.charAt(i)) != -1) {
                        alert ("Please Enter Valid Last name.");
                        document.partAForm.lastname.value="";
                        document.partAForm.lastname.focus();
                        return false;
                    }
                }
            }
            function validatefathername(){
                var iChars = "!@#$%^&*()+=-[]\\\'`~;,/{}|\":<>?";
                var gsurname=document.partAForm.gsurname.value;
                for (var i = 0; i < gsurname.length; i++) {
                    if (iChars.indexOf(gsurname.charAt(i)) != -1) {
                        alert ("Please Enter Valid Father name.");
                        document.partAForm.gsurname.value="";
                        document.partAForm.gsurname.focus();
                        return false;
                    }
                }
            }

            function validationOnNumbers()
            {
                var phonenumber=document.partAForm.pin.value;


                if(isNaN(phonenumber))
                {
                    alert("Enter numeric data")
                    document.partAForm.pin.value="";
                    document.partAForm.pin.focus();
                    return false;
                }
            }

            var letters=' ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZabcçdefghijklmnñopqrstuvwxyzàáÀÁéèÈÉíìÍÌïÏóòÓÒúùÚÙüÜ'
            var numbers='1234567890'
            var signs=',.:;@-\''
            var mathsigns='+-=()*/'
            var custom='<>#$%&?¿'

            function specialcharactets(e,allow) {
                var k;
                k=document.all?parseInt(e.keyCode): parseInt(e.which);
                return (allow.indexOf(String.fromCharCode(k))!=-1);
            }

        </script>

        

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script>
    </head>


    <body onload="validateepiccheckbox(),validatepensioncheckbox()">
        <html:form action="updatePartAPersonalAUDetails.do?updatePersonalDetails=updatePersonalDetails"  styleId="partAForm" onsubmit="return validate_form(this)">
            <br>
            <input type="hidden" name="district_id" value="<%=district_id%>"/>
            <input type="hidden" name="mandal_id" value="<%=mandal_id%>"/>
            <input type="hidden" name="village_id" value="<%=village_id%>"/>
            <input type="hidden" name="habitation_id" value="<%=habitation_id%>"/>
            <input type="hidden" name="panchayat_id" value="<%=panchayat_id%>"/>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="85%">

                <tr>
                    <td align="center" class="heading">Updates Personal Data</td>
                </tr>

            </table>


            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">

                <tr>

                    <td  class="label">Form No<font color="red"><b>*</b></font></td>
                    <td  class="label"><html:text property="formno"  name="partAForm" maxlength="25" readonly="true"
                               onkeypress="return specialcharactets(event,numbers+letters)"/></td>
                    <td   class="label">Date of Assessment<font color="red"><b>*</b></font></td>
                    <td  class="label"><html:text property="fromdate"   name="partAForm" readonly="true"/></td>

                </tr>



            </table>

            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>
                    <td class="labelBlue">1.0. Individual Details</td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr>
                    <td colspan="4" class="label">1.1 Name Of the person</td>
                </tr>
                <tr>


                    <!-- start-->
                    <td class="label">Surname<font color="red"><b>*</b></font></td>
                    <td><html:text property="surname" name="partAForm" maxlength="30" readonly="true" onblur=" validatesurname()" /></td>
                    <td class="label">Name<font color="red"><b>*</b></font></td>
                    <td><html:text property="firstname" name="partAForm" maxlength="30" readonly="true" onblur=" validatefirstname()" /></td>
                </tr>

                <tr>
                    <td><b><font size="2" color="red"><%=personname%></font></b></td>
                    <td><input type="text"   onkeyup="javascript1:surname_many_words()" onfocus="javascript1:surname_many_words()"
                               name="surnameenglish" maxlength="30" readonly="true" disabled="true"/></td>

                    <td><font size="2" color="red"><b><%=telugu%></b></font></td>
                    <td><input type="text"  value="<%=teluguname%>" name="surnametelugu"  readonly="true" />
                        <input type="hidden" value="<%=teluguname%>" id="telugu" name="telugupersonname" readonly="true"/></td>
                </tr>

                <tr>
                    <td class="label">1.2 Age<font color="red"><b>*</b></font></td>

                    <td colspan="3" class="label">Years&nbsp;&nbsp;<html:text property="noOfYears" size="6" onfocus="disableCalculateDob(this);"
                               onblur="validatecandidateage()" readonly="true" styleId="30" /></td>
                </tr>
                <tr>
                    <td class="label">Date Of Birth</td>
                    <td colspan="3" class="label">
                        Day <html:select  property="day"  name="partAForm" styleId="1" disabled="true">
                            <%

                                        for (int i = 1; i <= 31; i++) {
                            %>
                            <html:option value="<%=String.valueOf(i)%>"><%=i%></html:option>
                            <%
                                        }
                            %>
                        </html:select>


                        Month <html:select property="month" name="partAForm" disabled="true"  styleId="2">
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




                        Year <html:select property="year" styleId="year" name="partAForm" onchange="enableCalculateAge(this);"
                                     styleId="3" disabled="true">

                            <% for (int j = 2020; j > 1850; j--) {
                            %>
                            <html:option value="<%= String.valueOf(j)%>"><%= j%></html:option>
                            <%
                                        }
                            %>



                        </html:select>&nbsp;&nbsp;<html:button property="calculate_age" disabled="true"
                                                               value="Calculate Age" onclick="calculateAge(this);"/>

                    </td>
                </tr>


                <tr>
                    <td class="label">1.3 Gender<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:select property="gender" styleId="gender" name="partAForm" onchange="generateDob(this);changeothercombo(this.options.selectedIndex)" styleId="4" disabled="true">
                            <html:option  value="">--SELECT--</html:option>
                            <html:option value="1">Male</html:option>
                            <html:option value="2">Female</html:option>
                        </html:select></td>
                </tr>

                <tr>
                    <td class="label">1.4 Education</td>
                    <td colspan="3" class="label"><html:select property="education"  name="partAForm" styleId="5" disabled="true">
                            <html:option value="0">--SELECT--</html:option> 
                            <html:option value="1">Illiterate</html:option>
                            <html:option value="2">Below 10th</html:option>
                            <html:option value="3">10th Class</html:option>
                            <html:option value="4">Intermediate</html:option>
                            <html:option value="5">Diploma/ITI</html:option>
                            <html:option value="6"> Graduate</html:option>
                            <html:option value="7">Post Graduate</html:option>
                        </html:select></td>
                </tr>
 

                <tr>
                    <td class="label">1.5 Employment</td>
                    <td colspan="3" class="label">
                        <html:select property="employment" name="partAForm" styleId="6" disabled="true">
                            <html:option value="0">--SELECT--</html:option>
                            <html:option value="1">Govt</html:option>
                            <html:option value="2">Private</html:option>
                            <html:option value="3">Self-Employed</html:option>
                            <html:option value="4">Un-Employed</html:option>
                            <html:option value="5">Wage-Employee</html:option>
                        </html:select></td>
                </tr>



                <tr>
                    <td class="label">1.6  Marital Status<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label">
                        <html:select property="marital" disabled="true" styleId="7" name="partAForm">
                            <html:option value="">--SELECT--</html:option >
                            <html:option value="1">Married</html:option>
                            <html:option value="2">Un-Married</html:option>
                            <html:option value="3">Divorcee</html:option>
                            <html:option value="4">Widow</html:option>
                            <html:option value="5">Widower</html:option>
                        </html:select></td></tr>

                <tr>
                    <td class="label">1.7 Caste</td>
                    <td colspan="3" class="label">
                        <html:select property="caste" name="partAForm" disabled="true" styleId="8">
                            <html:option value="6">--SELECT--</html:option> 
                            <html:option value="1">ST</html:option>
                            <html:option value="2">SC</html:option>
                            <html:option value="3">BC</html:option> 
                            <html:option value="4">OC</html:option>
                            <html:option value="5">MINORITY</html:option>
                            <html:option value="6">NA</html:option>
                        </html:select></td>
                </tr>
                <tr>
                    <td class="label">1.8 Religion</td>
                    <td colspan="3" class="label">
                        <html:select property="religion" name="partAForm" disabled="true" styleId="9">
                            <html:option value="7">--SELECT--</html:option>
                            <html:option value="1">Hindu</html:option>
                            <html:option value="2">Muslim</html:option>
                            <html:option value="3">Christian</html:option>
                            <html:option value="4">Sikh</html:option>
                            <html:option value="5">Jain</html:option>
                            <html:option value="6">Budhist</html:option>
                            <html:option value="7">Others</html:option>
                        </html:select></td>
                </tr>


                <tr>
                    <td class="label">1.9 Ration Card No</td>
                    <td class="label"><html:text property="card" size="25" maxlength="20" disabled="true"/></td>
                    <td class="label" colspan="2">Type <html:select property="rtype" name="partAForm" disabled="true" styleId="10">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">White</html:option>
                            <html:option value="2">Pink</html:option>
                            <html:option value="3">Anthyodaya</html:option>
                            <html:option value="4">Annapurna</html:option>
                            <html:option value="5">Yellow</html:option>
                            <html:option value="6">Temporary</html:option>
                            <html:option value="7">Rachabanda</html:option>
                            <html:option value="8">White</html:option>
                        </html:select></td>
                </tr>


                <tr>
                    <td colspan="4">
                        <table cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                            <tr>
                                <td class="label" width="32%">1.10 EPIC Card</td>
                                <td class="label" width="18%">Click if Yes<html:checkbox  property="epiccard" value="true" name="partAForm"
                                                onclick="validateepiccheckbox(this.value)" disabled="true" styleId="11"/></td>
                                <td>


                                    <div id="epic" style="visibility:hidden;display:none">
                                        <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
                                            <tr>
                                                <td class="label">EPIC Number</td>
                                                <td colspan="3"><html:text property="epiccardno"  maxlength="20" disabled="true"/>
                                                </td> </tr>
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
                                <td width="32%" class="label">1.11 Pension Card </td>
                                <td width="18%" class="label">Click if Yes<html:checkbox  property="pensioncard" value="true" name="partAForm"
                                                onclick="validatepensioncheckbox(this.value)" disabled="true" styleId="12"/></td>

                                <td>

                                    <div id="pension" style="visibility:hidden;display:none">
                                        <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="85%">
                                            <tr>
                                                <td class="label">Pension Number</td>
                                                <td colspan="3"><html:text property="pensioncardno"  maxlength="7" disabled="true"/>
                                                </td> </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <!-- start-->
                    <td class="label">1.12 Identification Marks<font color="red"><b>*</b></font></td>
                    <td width="30%" class="label"><font color="red"><b>*</b></font><b> 1)<html:text property="mole1" size="25" name="partAForm" maxlength="50" onkeyup="validatemole1()" readonly="true"/></b></td>
                    <td colspan="2" class="label"><font color="red"><b>*</b></font><b>2) <html:text property="mole2" size="25" name="partAForm" maxlength="50" onkeyup="validatemole2()" readonly="true"/></b></td>

                </tr>
                <tr>
                    <td width="28%" align="left" class="label">1.13 Consanguineous Marriage of Parents
                    </td>
                    <td colspan="4" width="57%" class="label"><html:radio  property="parents_marriage" value ="1" disabled="true" >Yes</html:radio><html:radio  property="parents_marriage" value ="0" disabled="true" >No</html:radio>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">


                <tr>
                    <td colspan="4" class="labelBlue">2.0 Family Details</td>
                </tr>
                <tr>
                    <td class="label" width="34%">2.1 Father/Mother/Husband/Guardian's Name<font color="red"><b>*</b></font></td>
                    <td><html:text property="gsurname" size="18" maxlength="30" onblur="validatefathername()" readonly="true"/></td>
                    <td class="label">Relation type<font color="red"><b>*</b></font></td>
                      <td>
                        <html:select property="grelation"  styleId="grelation"  name="partAForm" disabled="true" styleId="13">
                           <html:option value="">--SELECT--</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td class="label"><font size="2" color="red"><b><%=fathername%></b></font></td>
                    <td><input type="text"   onkeyup="javascript1:first_many_words()" onfocus="javascript1:first_many_words()"
                               name="firstnameenglish" maxlength="30" readonly="true" disabled="true"/></td>

                    <td><font size="2" color="red"><b> <%=telugu%></b></font></td>
                    <td><input type="text"  value="<%=telugufathername%>" name="firstnametelugu"  readonly="true"/>
                        <input type="hidden" value="<%=telugufathername%>" id="telugu" name="telugufathername"  readonly="true"/>
                    </td>

                </tr>

                <tr>
                    <td class="labelBlue" colspan="4">3.0 Address (As recorded in RATION CARD)</td>
                </tr>
                <tr>
                    <td colSpan=4>
                        <table border="0"  cellspacing="0" cellpadding="8" class="innterTable1" width="85%">
                            <tr>
                                <td width="2%" rowspan="8"></td>
                                <td class="label" width="31%"> House No.<font color="red" size="3"><b>*</b></font>  </td>
                                <td width="52%"><html:text  property="houseno" maxlength="15" readonly="true"> </html:text></td>
                            </tr>
                            <tr>
                                <td  class="label">Town/Village </td>
                                <td class="label"><html:text  property="townVillage" value="<%=vilage%>" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">Habitation/Ward No </td>
                                <td class="label"><html:text  property="habitation" value="<%=habtation%>" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">Mandal</td>
                                <td class="label"><html:text  property="mandal" value="<%=Mandal%>" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">District</td>
                                <td class="label"><html:text  property="district" value="<%=Dirst%>" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">Phone No</td>
                                <td class="label"><html:text  property="phoneno" onkeypress="return onlyNumbers();" maxlength="15" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">EMail</td>
                                <td class="label"><html:text  property="email" onblur="validateemail()" maxlength="50" readonly="true"></html:text></td>
                            </tr>
                            <tr>
                                <td class="label">Pin</td>
                                <td class="label"><html:text  property="pin" maxlength="6" onblur="validatepin()" readonly="true"></html:text></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr>
                    <td colspan="3" class="labelBlue">4.0 Person Status<font color="red"><b>*</b></font></td>
                    <td class="label"><html:radio property="personstatus" name="partAForm" value="Eligible" disabled="true" styleId="14"/> Eligible
                        <html:radio property="personstatus" name="partAForm" value="Rejected" disabled="true"  styleId="15"/> Rejected</td>
                </tr>  </table>
            <br><br>
            <center>

                <html:submit  value="Update" onclick="generateDob(),enableAllProperties();"
                              onfocus="validatecandidateage()" styleClass="button"/>&nbsp;&nbsp;

                <html:reset value="Reset" styleClass="button"/>
            </center>

        </html:form> 
    </body>
        <script src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
 
    <script>
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