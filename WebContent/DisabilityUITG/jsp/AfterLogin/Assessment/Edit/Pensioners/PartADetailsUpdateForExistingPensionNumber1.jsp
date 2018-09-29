<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*,com.tcs.sadarem.util.CommonUtility" %>
<%try{ %>
<% String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
            System.out.println("in jsp");
            //String hospitalname= (String)request.getAttribute("HospitalName");
            String teluguname = (String) session.getAttribute("teluguname");
            String telugufathername = (String) session.getAttribute("telugufathername");
            boolean partAUpdateOnly = Boolean.parseBoolean(request.getParameter("partAUpdate"));
             
            String Dirst = (String) request.getAttribute("district_name");
            String Mandal = (String) request.getAttribute("mandal_name");
            String vilage = (String) request.getAttribute("village_name");
            String habtation = (String) request.getAttribute("habitation_name");
            String panchayat = (String) request.getAttribute("panchayat_name");

            String district_id = (String) request.getAttribute("district_id");
            String mandal_id = (String) request.getAttribute("mandal_id");
            String village_id = CommonUtility.checkNullObj(request.getAttribute("village_id"));
            String habitation_id = CommonUtility.checkNullObj(request.getAttribute("habitation_id"));
            String assembly_id = CommonUtility.checkNullObj(request.getAttribute("assembly_id"));
            String panchayat_id = CommonUtility.checkNullObj(request.getAttribute("panchayat_id"));
            String  personcode = CommonUtility.checkNullObj(session.getAttribute("personcode"));
            String vieweditmode = CommonUtility.checkNullObj(request.getAttribute("vieweditmode"));

%>

<style type="text/css">
    #fifteenth{position: absolute;width: 150px;border: 1px solid gray;padding: 2px;visibility: hidden;z-index: 99;}
</style>
<div id="fifteenth"></div>



<html:html >
    <head>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/PartADetailsExistingPensionNumberUpdate.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/TeluguscriptForFatherName.js"></script>
        <script>

            function enableAllProperties()
            {
                for(var i=1;i<=4;i++){
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


/*             <!-------Starts script Allow Alphabets and Numerics For RationCardNumber-------------------->
 */

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

           /*  <!-- Numbers allow only 0 to 100-----------> */
            
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
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function ristrictZero() {
                if(document.forms[0].elements['rationCardSlno'].value=="0") {
                    document.forms[0].elements['rationCardSlno'].value="";
                }

            }
        </script>
    </head>

    <body onload="document.partAForm.formno.focus(),validatepensioncheckbox(),validateepiccheckbox(),generateDob(),rationType()" >

        <html:form action="updatePartAPersonalDetails.do?updatePersonalDetails=updatePersonalDetails"
                   styleId="partAForm" onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');document.getElementById('subDisablButton').disabled = true;">
         

            <input type="hidden" name="rejectedViewstatus" value="true"/>
            <input type="hidden" name="partAUpdateOnly" value="<%=partAUpdateOnly%>" />
            <input type="hidden" name="district_id" value="<%=district_id%>"/>
            <input type="hidden" name="mandal_id" value="<%=mandal_id%>"/>
            <input type="hidden" name="village_id" value="<%=village_id%>"/>
            <input type="hidden" name="habitation_id" value="<%=habitation_id%>"/>
            <input type="hidden" name="panchayat_id" value="<%=panchayat_id%>"/>
            <input type="hidden" name="vieweditmode" value="<%=vieweditmode%>"> 

            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                <tr>
                    <th colspan="4">Enter Personal Data of <%=personcode%></th>
                </tr>
               
             <%if("Edit".equals(vieweditmode)) {%>  
                <tr>
                    <td >Form No<font color="red"><b>*</b></font></td>
                    <td >
                        <html:text property="formno"  name="partAForm" maxlength="25" 
                                  onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"
                                   /></td>
                    <td >Date of Assessment<font color="red"><b>*</b></font></td>
                    <td >
                        <html:text property="fromdate"  readonly="true"  name="partAForm"  />
                        <a  href="javascript:show_calendar('forms[0].fromdate');" onmouseover="window.status='Date Picker';return true;"
                        	   onmouseout="window.status='';return true;"><img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>
               <%}else{ %>
				 <tr>
                    <td >Form No<font color="red"><b>*</b></font></td>
                    <td >
                        <html:text property="formno"  name="partAForm" maxlength="25" readonly="true"
                                  onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"
                                   /></td>
                    <td >Date of Assessment<font color="red"><b>*</b></font></td>
                    <td >
                        <html:text property="fromdate"  readonly="true"  name="partAForm"  readonly="true"/>
                        
                    </td>
                </tr>
               <%} %>
                <tr>
                    <td colspan="4">1.0 Individual Details</td>

                   <%-- <td  class="Note" colspan="3"> To Upload Photo
                        <a href="javascript:void(0);"  onClick=window.open("fileupload.do","Ratting","width=500,height=200,0,status=0");>
                            <u>&nbsp;Click here</u></a></td>--%>
                </tr>
           
                <tr>
                    <td  colspan="4">1.1  Name of the Person</td>
                </tr>

                <tr>
                    <td  >Surname</td>
                    <td>
                       <html:text property="surname" name="partAForm" maxlength="25" readonly="true" style="width:250px"/>
                    </td>
                    <td >
                        Name<font color="red"><b>*</b></font>
                    </td>
                    <td>
                        <html:text property="firstname" name="partAForm" maxlength="25"  
                                   readonly="true" style="width:250px"/>
                    </td>
                </tr>

         <%if("Edit".equals(vieweditmode)) {%>
                <tr>
                    <td  ><font size="2" color="red"><b><%=personname%></b></font></td>
                    <td><input type="text"
                               name="surnameenglish" maxlength="30" readonly="true"
                               style="width:250px"/></td>

                    <td><font size="2" color="red"><b> <%=telugu%></b></font></td>
                    <td><input type="text"  value="<%=teluguname%>" name="surnametelugu"
                               readonly="true"  style="width:250px"/><a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" value="<%=teluguname%>" id="telugu" name="telugupersonname" style="width:250px"/>
                    </td>
                </tr>


                <tr>
                    <td >1.2 Age<font color="red"><b>*</b></font></td>
                    <td  colspan="3">Years &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text
                            property="noOfYears" size="6" maxlength="2"
                            onkeydown="return isNumeric(event.keyCode);"
                            onkeyup="generateDob(this);" onkeypress="return space(event,this)" style='width:100px'/>
                    </td>
                </tr>

                <tr>
                    <td >Date of Birth<font color="red"><b>*</b></font></td>
                    <td colspan="3" >
                        Day &nbsp;&nbsp;&nbsp;&nbsp;<html:select  property="day" style='width:100px'>
                            <%
                                        int i = 0;
                                        for (i = 1; i <= 31; i++) {
                            %>
                            <html:option value="<%=String.valueOf(i)%>"><%=i%></html:option>
                            <%
                                        }
                            %>
                        </html:select>

                   &nbsp;&nbsp;&nbsp;&nbsp; Month &nbsp;&nbsp;&nbsp;&nbsp;
                        <html:select property="month" name="partAForm" style='width:100px'>
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

                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Year&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:select property="year" styleId="year" onchange="calculateAge(this);"  style='width:100px'>


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
                    <td >1.3 Gender<font color="red"><b>*</b></font></td>
                    <td >
                        <html:select property="gender"  styleId="gender" name="partAForm" onchange="changeothercombo(this.options.selectedIndex)">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Male</html:option>
                            <html:option value="2">Female</html:option>
                        </html:select>
                    </td>

                    <td >1.4 Education</td>
                    <td >
                        <html:select property="education"  name="partAForm" >
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
                    <td >1.5 Employment</td>
                    <td >
                        <html:select property="employment" name="partAForm" >
                            <html:option value="0">--SELECT--</html:option>
                            <html:option value="1">Govt</html:option>
                            <html:option value="2">Private</html:option>
                            <html:option value="3">Self-Employed</html:option>
                            <html:option value="4">Un-Employed</html:option>
                            <html:option value="5">Wage-Employee</html:option>
                        </html:select>
                    </td>

                    <td >1.6 Marital Status<font color="red"><b>*</b></font></td>
                    <td >
                        <html:select property="marital" name="partAForm">
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
                    <td >1.7 Caste</td>
                    <td >
                        <html:select property="caste" name="partAForm">
                            <html:option value="6">--SELECT--</html:option>
                            <html:option value="1">ST</html:option>
                            <html:option value="2">SC</html:option>
                            <html:option value="3">BC</html:option>
                            <html:option value="4">OC</html:option>
                            <html:option value="5">Minority</html:option>
                            <html:option value="6">NA</html:option>
                        </html:select>
                    </td>

                    <td >1.8 Religion</td>
                    <td >
                        <html:select property="religion" name="partAForm">
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
                    <td >1.9 Ration Card No</td>
                    <td ><html:text property="card" size="20" maxlength="20"
                               onkeyup="rationType()"  onkeypress="return space(event,this)"/></td>
                    <td >Type</td>
                    <td >
                        <html:select property="rtype" name="partAForm" onchange="rationType()">
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

                        Slno   <html:text property="rationCardSlno" maxlength="1" onkeypress="return onlyNumbers();" onkeyup="ristrictZero()" style="width:50px"/>
                    </td>


                </tr>

             <%--   <tr>
                    <td colspan="4">
                        <table  align="center" cellspacing="1" cellpadding="0" width="100%" >--%>
                            <tr>
                                <td >1.10 EPIC Card</td>
                                <td  >Click if Yes <html:checkbox  property="epiccard"
                                                value="true" name="partAForm"  onclick="validateepiccheckbox(this.value)" style="width:50px;height:25px"/>
                                </td>
                                <td colspan="3">
                                    <div id="epic" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="1" cellpadding="0" width="90%" >
                                            <tr>
                                                <td  align="center">EPIC Number  &nbsp;&nbsp;
                                                    <html:text property="epiccardno"  maxlength="20" onkeypress="return letternumber(event)" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        <%--</table>
                    </td>
                </tr>--%>

              <%--  <tr>
                    <td colspan="4">
                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">--%>
                            <tr>
                                <td width="36%" >1.11 Pension Card </td>
                                <td width="15%" >Click if Yes
                                    <html:checkbox  property="pensioncard" value="true" name="partAForm"  disabled="true" styleId="1"/>
                                </td>
                                <td colspan="3">
                                    <div id="pension" style="visibility:hidden;display:none">
                                        <table  align="center" cellspacing="0" cellpadding="0" bgcolor="white" width="100%">
                                            <tr>
                                                <td>Pension Type</td>
                                                <td>
                                                    <html:select property="pension_type" name="partAForm" disabled="true"  styleId="2">
                                                        <html:option value="">--SELECT--</html:option>
                                                        <html:option value="Disabled">Disabled</html:option>
                                                        <html:option value="Old Age">O.A.P</html:option>
                                                        <html:option value="Widow">Widow</html:option>
                                                        <html:option value="Weavers">Weavers</html:option>
                                                    </html:select>
                                                </td>
                                                <td >Pension Number</td>
                                                <td ><html:text property="pensioncardno"  maxlength="7" readonly="true"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                       <%-- </table>
                    </td>
                </tr>--%>

              <%} %>
                <tr>
                    <td >1.12 Identification Marks<font color="red"><b>*</b></font></td>
                    <td >1)
                        <html:text property="mole1"  size="25" name="partAForm" maxlength="50"   onkeydown="return isAlpha(event.keyCode,mole1);" onkeypress="return space(event,this)"  onchange="capitalizeMe(this)" style="width:200px"/>
                    </td>
                    <td colspan="2" >2)
                        <html:text property="mole2" size="25" name="partAForm" maxlength="50" onkeydown="return isAlpha(event.keyCode,mole2);" onkeypress="return space(event,this)"   onchange="capitalizeMe(this)" style="width:220px"/>
                    </td>
                </tr>
               <%if("Edit".equalsIgnoreCase(vieweditmode)){ %>
                <tr>
                    <td >1.13 Consanguineous Marriage of Parents</td>
                    <td colspan="3" >
                        <html:radio  property="parents_marriage" value ="1" style="width:25px">Yes</html:radio> &nbsp;&nbsp;
                        <html:radio  property="parents_marriage" value ="0" style="width:25px">No</html:radio>
                    </td>
                </tr>
              

                <tr>
                    <td colspan="4" >2.0 Family Details</td>
                </tr>

                <tr>
                    <td   width="33%">2.1 Father/Mother/Husband/Guardian's Name<font color="red"><b>*</b></font></td>
                    <td >
                        <html:text property="gsurname" size="18" maxlength="30"readonly="true"/>
                    </td>
                    <td > Relation type<font color="red"><b>*</b></font> </td>
                   <td>
                        <html:select property="grelation" styleId="grelation"  name="partAForm" > 
                           <html:option value="">--SELECT--</html:option>
                        </html:select>
                    </td>
                </tr>

                <tr>
                    <td><font size="2" color="red"><b><%=fathername%></b></font></td>
                    <td><input type="text"    name="firstnameenglish" maxlength="30"
                               readonly="true"/></td>

                    <td><font size="2" color="red"><b> <%=telugu%></b></font></td>
                    <td><input type="text"  value="<%=telugufathername%>" name="firstnametelugu"  readonly="true"/><a href="javascript:modelesswin('./DisabilityUITG/images/TeluguKeyBoard.JPG',700,300)">TeluguKeyBoard</a>
                        <input type="hidden" value="<%=telugufathername%>" id="telugu" name="telugufathername" readonly="true"/>

                    </td>
                </tr>



                <tr>
                    <td  colspan="4">3.0 Address (As recorded in RATION CARD)</td>
                </tr>

                <tr>

                    <td  > House No.</td>
                    <td  ><html:text  property="houseno" maxlength="15" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,houseno)"  onkeypress="return space(event,this)"> </html:text></td>

                    <td  >Town/Village </td>
                    <td ><html:text  property="townVillage" value="<%=vilage%>" readonly="true"></html:text></td>
                </tr>
                <tr>
                    <td >Habitation/Ward No </td>
                    <td ><html:text  property="habitation" value="<%=habtation%>" readonly="true"></html:text></td>

                    <td >Mandal</td>
                    <td ><html:text  property="mandal" value="<%=Mandal%>" readonly="true"></html:text></td>
                </tr>
                <tr>
                    <td >District</td>
                    <td ><html:text  property="district" value="<%=Dirst%>" readonly="true"></html:text></td>

                    <td >Phone No</td>
                    <td ><html:text  property="phoneno"  maxlength="15" onkeypress="return onlyNumbers();"></html:text></td>
                </tr>
                <tr>
                    <td >E-mail</td>
                    <td ><html:text  property="email"  maxlength="50"></html:text></td>

                    <td >Pin</td>
                    <td ><html:text  property="pin" maxlength="6" onkeypress="return onlyNumbers();"
                                ></html:text></td>
                </tr>

                <tr>

                    <td >4.0 Type of Disability</td>
                    <td colspan="3">
                        <html:select property="type_disability" name="partAForm" style="width:150px">
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">Locomotor/OH</html:option>
                            <html:option value="2">Visual Impairment</html:option>
                            <html:option value="3">Hearing Impairment</html:option>
                            <html:option value="4">Mental Ratardation</html:option>
                            <html:option value="5">Mental Illness</html:option>
                            <html:option value="6">Multiple Disability</html:option>


                        </html:select>
                    </td>

                </tr>

                <tr>

                    <td  >5.0 Existing Percentage</td>
                    <td colspan="3"><html:text property="existingpercentage" name="partAForm" maxlength="3" onkeydown="return isNumeric(event.keyCode);" onkeyup="isNumber100(this)" style="width:150px"/> %</td>

                </tr>
              

                 <tr>
                    <td >6.0 Aadhar Card No</td>
                    <td>
                        <html:text property="aadharCardNo" onkeydown="return isNumeric(event.keyCode);" onkeyup="return isNumeric(event.keyCode);" maxlength="12" style="width:160px" onkeyup="validateAadhar(this.value);"/>
                    </td>
                    <td colspan="2">
                        <div id="data"></div>
                        <div id="loadImg"></div>
                    </td>
                </tr>

                <tr>
                    <td   >7.0 Person Status<font color="red" size="2"><b>*</b></font></td>
                    <td colspan="3"><html:radio property="personstatus" name="partAForm" value="Eligible" disabled="<%=partAUpdateOnly%>" styleId="3" style="width:25px"/> Eligible
                        <html:radio property="personstatus" name="partAForm" value="Rejected" disabled="<%=partAUpdateOnly%>" styleId="4" style="width:25px"/> Rejected</td>

                </tr>
               <%} %>
                <tr><th colspan="4">
                <html:submit  value="Update" styleId="subDisablButton" styleClass="button"  onclick="return checkFormValidations()"/>
                <html:reset value="Reset" styleClass="button"/></th></tr>
       
            </table>
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
                          //  document.getElementById("subButton").disabled=false;
                            $("#data").html("<font color=green size=2><b>Given Aadhar Number Validated Successfully.</b></font>");//data - response from server
                            document.getElementById("loadImg").innerHTML="";
                        }
                        if(data == "fail") {
                          //  document.getElementById("subButton").disabled=true;
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
            

            /*Setting the relation type of database.*/
            
            if("<bean:write name="partAForm"  property="grelation"/>"!="" && "<bean:write name="partAForm"  property="grelation"/>"!="null" && "<bean:write name="partAForm"  property="grelation"/>"!=null)
            	{
            		document.getElementById("grelation").value="<bean:write name="partAForm"  property="grelation"/>";
            	}
        }
        $(document).ready( function()
        		{
        	
       	     var gendpos = document.getElementById("gender").options.selectedIndex;       	     
        	 changeothercombo(gendpos); 
        	
        		});
        
        </script>
</html:html>
<%}catch(Exception e){e.printStackTrace();}%>