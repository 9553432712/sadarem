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



            String Dirst = (String) request.getAttribute("districtname");
            String existingmandal_id = (String) request.getAttribute("existingmandal_id");
            String existingvillage_id = (String) request.getAttribute("existingvillage_id");
            String existinghabitation_id = (String) request.getAttribute("existinghabitation_id");
            String habcode = request.getAttribute("habitationcode").toString();
%>
<html:html >
    <head>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumberUpdate.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script>
        <script>
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
                for(var i=1;i<=18;i++){
                    document.getElementById(i).disabled=false;
                }
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
                        document.getElementById("rtype").disabled = true;
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

            function getData(read)
            {
                enableAllProperties();
                document.forms[0].status.value="update";
                document.forms[0].submit();
            }

            function goBack()
            {
                document.forms[0].action="PersonCodeForUpdateForwardAction.do";
                document.forms[0].submit();
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

        </script>


    </head>


    <body onload="document.partAForm.formno.focus(),validatepensioncheckbox(),validateepiccheckbox(),generateDob(),rationType()">
        <html:form action="updatePersonCodeDetails.do?updatePersonalCode=updatePersonalCode"  styleId="partAForm">
            <br>
            <input type="hidden" name="status" value="finish"/>
            <input type="hidden" name="existingmandal_id" value="<%=existingmandal_id%>"/>
            <input type="hidden" name="existingvillage_id" value="<%=existingvillage_id%>"/>
            <input type="hidden" name="existinghabitation_id" value="<%=existinghabitation_id%>"/>
            <html:hidden property="loginid"   name="partAForm" />
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="85%" border="1">
                <tr>
                    <td align="center"><font color="red" size="2"><b>Note:-&nbsp;</b></font>
                        <font color="blue" size="2"> <b>Please go through the personal details of given SADAREM ID and update exact Mandal,Vilage,Habitation of the Person.It will generate new SADAREM ID.</b></font></td>
                </tr>
                <tr>
                    <td align="center" class="heading">Update SADAREM ID</td>
                </tr>

            </table>


            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">

                <tr>

                    <td  class="label">Form No<font color="red"><b>*</b></font></td>
                    <td  class="label"><html:text property="formno"  name="partAForm" maxlength="25" readonly="true"
                               onkeydown="return isAlphaNumeric(event.keyCode);" onkeyup="keyUP(event.keyCode)"/></td>
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
                    <td><html:text property="surname" name="partAForm" maxlength="30" readonly="true" onkeydown="return isAlpha(event.keyCode,surname);" onkeyup="keyUP(event.keyCode)"
                               onchange="capitalizeMe(this)"/></td>
                    <td class="label">Name<font color="red"><b>*</b></font></td>
                    <td><html:text property="firstname" name="partAForm" maxlength="30" readonly="true" onkeydown="return isAlpha(event.keyCode,firstname);" onkeyup="keyUP(event.keyCode)"
                               onchange="capitalizeMe(this)"/></td>
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



                        </html:select>

                    </td>
                </tr>


                <tr>
                    <td class="label">1.3 Gender<font color="red"><b>*</b></font></td>
                    <td colspan="3" class="label"><html:select property="gender" styleId="gender"  name="partAForm" onchange="changeothercombo(this.options.selectedIndex)"  disabled="true" styleId="4">
                            <html:option  value="">--SELECT--</html:option>
                            <html:option value="1">Male</html:option>
                            <html:option value="2">Female</html:option>
                        </html:select></td>
                </tr>

                <tr>
                    <td class="label">1.4 Education</td>
                    <td colspan="3" class="label"><html:select property="education"  name="partAForm" disabled="true" styleId="5">
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
                        <html:select property="employment" name="partAForm" disabled="true" styleId="6">
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
                        <html:select property="marital" disabled="true"  name="partAForm" styleId="7">
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
                    <td class="label"><html:text property="card" size="15" maxlength="20" readonly="true" onkeydown="return isAlphaNumericRationCard(event.keyCode,card);"
                               onkeyup="keyUP(event.keyCode),rationType()"  /></td>
                    <td class="label">Type </td>
                    <td><html:select property="rtype" name="partAForm" onchange="rationType()" disabled="true" styleId="10">

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
                                <td class="label" width="36%">1.10 EPIC Card</td>
                                <td class="label" width="15%">Click if Yes<html:checkbox  property="epiccard" value="true" name="partAForm"
                                                onclick="validateepiccheckbox(this.value)" disabled="true" styleId="11"/></td>
                                <td width="50%">


                                    <div id="epic" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                                            <tr>
                                                <td class="label">EPIC Number&nbsp;&nbsp;
                                                    <html:text property="epiccardno"  maxlength="20"  readonly="true"
                                                               onkeydown="return isAlphaNumeric(event.keyCode);" onkeyup="keyUP(event.keyCode)"  /></td>
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
                                <td width="36%" class="label">1.11 Pension Card </td>
                                <td width="15%" class="label">Click if Yes
                                    <html:checkbox  property="pensioncard" value="true" name="partAForm"  disabled="true" styleId="12"/></td>

                                <td width="50%">

                                    <div id="pension" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                                            <tr>
                                                <td class="label">Pension Type</td>
                                                <td>
                                                    <html:select property="pension_type" name="partAForm" disabled="true"  styleId="13">
                                                        <html:option value="">--SELECT--</html:option>
                                                        <html:option value="Disabled">Disabled</html:option>
                                                        <html:option value="Old Age">O.A.P</html:option>
                                                        <html:option value="Widow">Widow</html:option>
                                                        <html:option value="Weavers">Weavers</html:option>
                                                    </html:select>
                                                </td>
                                                <td class="label">Pension Number</td>
                                                <td class="textbox"><html:text property="pensioncardno"  maxlength="7" readonly="true"/></td>
                                            </tr>
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
                    <td width="30%" class="label"><font color="red"><b>*</b></font><b> 1)<html:text property="mole1" size="30" name="partAForm" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole1);" onkeyup="keyUP(event.keyCode)"  onchange="capitalizeMe(this)" readonly="true"/></b></td>
                    <td colspan="2" class="label"><font color="red"><b>*</b></font><b>2) <html:text property="mole2" size="30" name="partAForm" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole2);" onkeyup="keyUP(event.keyCode)"    onchange="capitalizeMe(this)" readonly="true"/></b></td>

                </tr>
                <tr>
                    <td width="28%" align="left" class="label">1.13 Consanguineous Marriage of Parents
                    </td>
                    <td colspan="4" width="57%" class="label"><html:radio  property="parents_marriage" value ="1" disabled="true" styleId="14" >Yes</html:radio><html:radio  property="parents_marriage" value ="0" disabled="true" styleId="15" >No</html:radio>
                    </td>
                </tr>
            </table>
            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">


                <tr>
                    <td colspan="4" class="labelBlue">2.0 Family Details</td>
                </tr>
                <tr>
                    <td class="label">2.1 Father/Mother/Husband/Guardian's Name<font color="red"><b>*</b></font></td>
                    <td><html:text property="gsurname" size="18" maxlength="30" onblur="validatefathername()" readonly="true"/></td>
                    <td class="label">Relation type<font color="red"><b>*</b></font></td>
                      <td>
                        <html:select property="grelation"  styleId="grelation" name="partAForm" disabled="true" styleId="16">
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
                                <td class="label" width="46%"> House No.<font color="red" size="3"><b>*</b></font>  </td>
                                <td width="52%"><html:text  property="houseno" maxlength="15" readonly="true"> </html:text></td>
                            </tr>

                            <tr>
                                <td class="label">Habitation/Ward No </td>
                                <td class="label">
                                    <%--<html:text  property="habitation" value="<%=habtation%>" readonly="true"></html:text>--%>
                                    <html:select property="habitation">
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="habitationlist" label="habitation_name" value="habitation"  />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td  class="label">Town/Village </td>
                                <td class="label">

                                    <%-- <html:text  property="townVillage" value="<%=vilage%>" readonly="true"></html:text> --%>

                                    <html:select property="townVillage" onchange="getData(this.value)">
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="villagelist" label="village_name" value="townVillage"  />
                                    </html:select>
                                </td>
                            </tr>

                            <tr>
                                <td class="label">Mandal</td>
                                <td class="label">
                                    <%--  <html:text  property="mandal" value="<%=Mandal%>" readonly="true"></html:text>--%>
                                    <html:select property="mandal" onchange="getData(this.value)" >
                                        <html:option  value="">--SELECT--</html:option>
                                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal"  />
                                    </html:select>
                                </td>
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
                                <td class="label"><html:text  property="pin" maxlength="6" onkeypress="return onlyNumbers();" onblur="validatepin()" readonly="true"></html:text></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>

                    <td colspan="2" class="labelBlue" width="32%">4.0 Type of Disability</td>
                    <td>
                        <html:select property="type_disability" name="partAForm" styleId="17" disabled="true">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Locomotor/OH</html:option>
                            <html:option value="2">Visual Impairment</html:option>
                            <html:option value="3">Hearing Impairment</html:option>
                            <html:option value="4">Mental Ratardation</html:option>
                            <html:option value="5">Menatl Illness</html:option>
                            <html:option value="6">Multiple Disability</html:option>


                        </html:select>
                    </td>

                </tr>
            </table>

            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="85%">
                <tr>

                    <td class="labelBlue" width="32%">5.0 Existing Percentage</td>
                    <td><html:text property="existingpercentage" name="partAForm" maxlength="3" onkeypress="return onlyNumbers();" onkeyup="isNumber100(this)" readonly="true"/> %</td>

                </tr>
            </table>

            <table  align="center" cellspacing="0" cellpadding="8" border="1" class="innerTable1" width="85%">
                <tr>
                    <td colspan="2" class="labelBlue">6.0 Person Status<font color="red"><b>*</b></font></td>
                    <td class="label"><html:radio property="personstatus" name="partAForm" value="Eligible" disabled="true" styleId="18"/> Eligible
                        <html:radio property="personstatus" name="partAForm" value="Rejected" disabled="true"  styleId="19"/> Rejected</td>
                </tr>  </table>
            <br><br>
            <center>

                <html:submit  value="Update" onclick="generateDob(),enableAllProperties();" onmouseover="selecttype()"
                              onfocus="validatecandidateage()" styleClass="button"/>&nbsp;&nbsp;
                <input type="button" name="Back" value="Back" onclick="goBack()" class="button" />
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