<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.ArrayList,java.util.HashMap,com.tcs.sadarem.util.CommonUtility,com.tcs.sadarem.util.ComboUtil" %>
<% 

ArrayList tempList = new ArrayList();
String personname = "&#3093;&#3134;&#3120;&#3149;&#3105;&#3137; &#3118;&#3136;&#3110; &#3098;&#3138;&#3114;&#3125;&#3122;&#3128;&#3135;&#3112;  &#3114;&#3143;&#3120;&#3137; ";
            String fathername = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135; / &#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137;";
            String telugu = "&#3108;&#3142;&#3122;&#3137;&#3095;&#3137;&#3122;&#3147;";
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
            String village_id = (String) request.getAttribute("village_id"); 
            String habitation_id = (String) request.getAttribute("habitation_id");
            String assembly_id = (String) request.getAttribute("assembly_id");
            String panchayat_id = (String) request.getAttribute("panchayat_id");
            int roleId = 0;
            if (request.getAttribute("roleId") != null) {
                roleId = Integer.parseInt(CommonUtility.checkNullObj(request.getAttribute("roleId")));
            }
            String name = (String) request.getAttribute("name");
            String surName = (String) request.getAttribute("surname");
            String relName = null;
            if (request.getAttribute("relName") != null) {
                relName = (String) request.getAttribute("relName");
            }

            String aadhar = null;
            if (request.getAttribute("aadhar") != null) {
                aadhar = (String) request.getAttribute("aadhar");
            }

            String wpii = null;
            if (request.getAttribute("pensioncardno") != null) {
                wpii = (String) request.getAttribute("pensioncardno");


            }
            

          

            HashMap PartABasicDetailsList = (HashMap)request.getAttribute("PartABasicDetailsList");
            
%>

<style type="text/css">
    #fifteenth{position: absolute;width: 150px;border: 1px solid gray;padding: 2px;visibility: hidden;z-index: 99;}
</style>
<div id="fifteenth"></div>



<html:html >
    <head>
     <title>:: SADAREM :: Part-A Details</title>
        <script language="JavaScript" src="<%=request.getContextPath()%>/DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/DisabilityUITG/js/PartADetailsExistingPensionNumberUpdate.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/DisabilityUITG/js/TeluguScriptForPersonName.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/DisabilityUITG/js/TeluguscriptForFatherName.js"></script>
 
 <style>
        
/* Process Layer Started */
     	
     	#processlayer
			{
				width: 300px;
				height: 50px;
				background:#ECF1EF;
				border: red dotted 5px;
				text-align: center;
				position: fixed;
				margin-right: -150px;
				margin-top: -75px;
				right: 50%;
				top: 50%;
				z-index: 99999;
				display: none;
			}
			
			#blocklayer
			{
				position: absolute;
				left: 0;
				top: 0;
				background: #ECF1EF;
			}
     	
     /* 	 Process Layer Ended  */
     	
</style>
        
        
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

    <body> 
    
    <!-- Screen Lock Started Here -->
	<div id="processlayer">
		<font color="blue" size="2">Validating Please Wait...</font><br/>
		<img src="<%=request.getContextPath()%>/images/processing.gif" border="0"/>
	</div>
	<div id="blocklayer">
	</div>
<!-- Screen Lock Ended Here -->
    
        <html:form action="updatePartAPersonalDetails.do?updatePersonalDetails=updatePersonalDetails" styleId="partAForm" onsubmit="if (this.getAttribute('submitted')) return false; this.setAttribute('submitted','true');document.getElementById('updPartaButton').disabled = true;">
            <input type="hidden" name="rejectedViewstatus" value="true"/>
            <input type="hidden" name="partAUpdateOnly" value="<%=partAUpdateOnly%>" />
            <input type="hidden" name="district_id" value="<%=district_id%>"/>
            <input type="hidden" name="mandal_id" value="<%=mandal_id%>"/>
            <input type="hidden" name="village_id" value="<%=village_id%>"/>
            <input type="hidden" name="habitation_id" value="<%=habitation_id%>"/>
            <input type="hidden" name="panchayat_id" value="<%=panchayat_id%>"/>
            <input type="hidden" name="wpii" value="<%=wpii%>"/>
            
            
            <input type="hidden" id="formno" name="formno" value="<bean:write name="partAForm" property="formno" />"/>
            <input type="hidden" id="fromdate" name="fromdate" value="<bean:write name="partAForm" property="fromdate" />"/>
            <input type="hidden" id="surname" name="surname" value="<bean:write name="partAForm" property="surname" />"/>
            <input type="hidden" id="firstname" name="firstname" value="<bean:write name="partAForm" property="firstname"/>"/>
            <input type="hidden" id="surnametelugu" name="surnametelugu" value="<bean:write name="partAForm" property="surnametelugu" />"/>
            <input type="hidden" id="noOfYears" name="noOfYears" value="<bean:write name="partAForm" property="noOfYears" />"/>
            <input type="hidden" id="telugu" name="telugupersonname" value="<%=teluguname%>" />
            <input type="hidden" id="day" name="day" value="<bean:write name="partAForm" property="day" />"/>
            <input type="hidden" id="month" name="month" value="<bean:write name="partAForm" property="month" />"/>
            <input type="hidden" id="year" name="year" value="<bean:write name="partAForm" property="year" />"/>
            <input type="hidden" id="gender" name="gender" value="<bean:write name="partAForm" property="gender" />"/>
            <input type="hidden" id="education" name="education" value="<bean:write name="partAForm" property="education" />"/>
            <input type="hidden" id="employment" name="employment" value="<bean:write name="partAForm" property="employment" />"/>
            <input type="hidden" id="marital" name="marital" value="<bean:write name="partAForm" property="marital" />"/>
            <input type="hidden" id="caste" name="caste" value="<bean:write name="partAForm" property="caste" />"/>
            <input type="hidden" id="religion" name="religion" value="<bean:write name="partAForm" property="religion" />"/>
            <input type="hidden" id="card" name="card" value="<bean:write name="partAForm" property="card" />"/>
            <input type="hidden" id="rtype" name="rtype" value="<bean:write name="partAForm" property="rtype" />"/>
            <input type="hidden" id="rationCardSlno" name="rationCardSlno" value="<bean:write name="partAForm" property="rationCardSlno" />"/>
            <input type="hidden" id="epiccardno" name="epiccardno" value="<bean:write name="partAForm" property="epiccardno" />"/>
            <input type="hidden" id="pension_type" name="pension_type" value="<bean:write name="partAForm" property="pension_type" />"/>
            <input type="hidden" id="pensioncardno" name="pensioncardno" value="<bean:write name="partAForm" property="pensioncardno" />"/>
            <input type="hidden" id="parents_marriage" name="parents_marriage" value="<bean:write name="partAForm" property="parents_marriage" />"/>
            <input type="hidden" id="gsurname" name="gsurname" value="<bean:write name="partAForm" property="gsurname" />"/>
            <input type="hidden" id="grelation" name="grelation" value="<bean:write name="partAForm" property="grelation" />"/>
            <input type="hidden" id="telugufathername" name="telugufathername" value="<bean:write name="partAForm" property="telugufathername" />"/>
            <input type="hidden" id="houseno" name="houseno" value="<bean:write name="partAForm" property="houseno" />"/>
            <input type="hidden" id="townVillage" name="townVillage" value="<bean:write name="partAForm" property="townVillage" />"/>
            <input type="hidden" id="habitation" name="habitation" value="<bean:write name="partAForm" property="habitation" />"/>
            <input type="hidden" id="mandal" name="mandal" value="<bean:write name="partAForm" property="mandal" />"/>
            <input type="hidden" id="district" name="district" value="<bean:write name="partAForm" property="district" />"/>
            <input type="hidden" id="phoneno" name="phoneno" value="<bean:write name="partAForm" property="phoneno" />"/>
            <input type="hidden" id="email" name="email" value="<bean:write name="partAForm" property="email" />"/>
            <input type="hidden" id="pin" name="pin" value="<bean:write name="partAForm" property="pin" />"/> 
            
            
          <table class="table table-striped table-bordered dataTable" style="font-size: 15px; border: 1px 1px 1px 1px; width: 98%; margin-left: 10px;"> 
		   <thead>
		   <tr>
		   		<th colspan="2" style="background-color:#fbeacc;padding:5px; font-weight: bold; text-align:center; vertical-align: middle; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Part-A basic details for Part-B form entry</th>
		   </tr>
		     <tr> 
		       <th style="background-color:#fbeacc;padding:5px; text-align:center; vertical-align: middle; font-weight: bold; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Contact Details</th>
		       <th style="background-color:#fbeacc;padding:5px; text-align:center; vertical-align: middle; font-weight: bold; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Personal Details</th> 
		  	 </tr> 
		 	</thead> 
		 	 <tbody>
			  <tr  style="background-color: #f7f5f1 !important;">
			   <td style="text-align: center; vertical-align:top;width: 40%">
				   	<table class="table table-striped table-bordered dataTable" style="font-size: 12px; border: 1px 1px 1px 1px;"> 
				   		<tr>
				   			<td width="60%"  style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">District</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("district_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Mandal</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("mandal_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Panchayat</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("panchayat_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Village</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("village_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Habitation/Ward No</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("habitation_name")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">House No.</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("house_number")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Pin Code</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("pin_code")) %></td>
				   		</tr>		
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Contact No.</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("phone_no")) %></td>
				   		</tr>		
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">eMail Id</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("email")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Type of Pension</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("pension_type")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Pension Number</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("pensioncard_no")) %></td>
				   		</tr>
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Consanguineous Marriage of Parents</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("parents_marriage_desc")) %></td>
				   		</tr>	
				   		<tr>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px; text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">EPIC Card</td>
				   			<td  style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("epiccard_no")) %></td>
				   		</tr>				   	
				   	</table>			   
			   </td> 
		       <td style="text-align:center; vertical-align: top;width: 60%">
		       		<table class="table table-striped table-bordered dataTable" style="font-size: 12px; border: 1px 1px 1px 1px;"> 
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: center; vertical-align: middle; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; " rowspan="4">
							  <img style="width:150px; height:150px;" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<%=CommonUtility.checkNullObj(PartABasicDetailsList.get("sadarem_id")) %>&randiomid=<%=Math.random()%>" alt="Profile Photo" onerror="this.src='<%=request.getContextPath()%>/images/defaultprofile.png'"/>
							</td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">SADAREM ID</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("sadarem_id")) %></td>
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Aadhaar Card No</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("proof_id")) %></td>
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Form No.</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("reference_form_number")) %></td>
				   		</tr>		
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; " colspan="2">Date of Assessment</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("form_filled_date")) %></td>
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Sur-Name</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("surname")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Person Name</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("first_name")) %></td>				   		
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Gender</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("gender_name")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Date of Birth</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("date_of_birth")) %></td>				   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Full Name (Telugu)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("personname_telugu")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Marital Status</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("marital_status_desc")) %></td>						   		
				   		</tr>
				   		<tr> 
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Relation Details</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("relationship_type_desc")) %>&nbsp;<%=CommonUtility.checkNullObj(PartABasicDetailsList.get("relation_name")) %></td>	
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Relation Name(Telugu)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("relationname_telugu")) %></td>	   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Education</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("edu_desc")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Employment</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143;   padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("employement_type_desc")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Religion</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("religion_desc")) %></td>
				   			<td  style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Caste</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("caste_desc")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Ration Card No</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("rationcard_number")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Ration Card Type : <%=CommonUtility.checkNullObj(PartABasicDetailsList.get("rationcard_type_desc")) %></td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Slno : <%=CommonUtility.checkNullObj(PartABasicDetailsList.get("rationcard_slno")) %></td>						   		
				   		</tr>
				   		<tr>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Identification Marks 1)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("mole_one")) %></td>
				   			<td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; ">Identification Marks 2)</td>
				   			<td style="background-color: #f7f5f1 !important;text-align: left; border: 1px solid #ddd; line-height: 1.42857143; padding: 8px; "><%=CommonUtility.checkNullObj(PartABasicDetailsList.get("mole_two")) %></td>						   		
				   		</tr>
				   	</table>		
		       
		       </td>  
			  </tr>
			  <tr>

                    <td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Type of Disability</td>
                    <td>
                        <html:select property="type_disability" name="partAForm" style="width:200px">
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
                    <td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Existing Percentage</td>
                    <td><html:text property="existingpercentage" name="partAForm" maxlength="3" onkeypress="return onlyNumbers();" onkeyup="isNumber100(this)" style="width:200px"/> %</td>

                </tr>  
                <tr>
                    <td style="background-color: #37ADB6 !important; font-size: 15px;  text-align: left; border: 1px solid #ddd; line-height: 1.42857143;  padding: 8px; ">Person Status<font color="red" ><b>*</b></font></td>
                    <td><html:radio property="personstatus" name="partAForm" value="Eligible" disabled="<%=partAUpdateOnly%>" styleId="3" style="width:25px"/> Eligible
                        <html:radio property="personstatus" name="partAForm" value="Rejected" disabled="<%=partAUpdateOnly%>" styleId="4" style="width:25px"/> Rejected</td>

                </tr>
                <tr>
                    <th align="center" colspan="2" style="text-align: center;">
                        <button  type="button" class="btn btn-success" id="updPartaButton" /> Proceed </button>
                       
                    </th>
                </tr>
			 </tbody>
			</table>
             
        </html:form>
    </body>
      <script src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script> 
	  <script language="javascript">

		$("#updPartaButton").click(function ()
		{
			if($("#type_disability").val()=="")
				{
					alert("Please select Type of Disability");
					$("#type_disability").focus();
					return false;
				}
				else if(!$('input[name="personstatus"]:checked').val())
				{ 
					alert("Please select Person Status");
					return false;
				}
				else
				{
					$('#blocklayer').focus();
	      			
	      			/*Screen Locking Started */
		      			 $('#blocklayer').css({	"display": "block", opacity: 0.7,"z-index":"100000", "width":$(document).width(),"height":$(document).height()});
		      		    $('#processlayer').css({"display": "block","z-index":"110000"});
	      			/*Screen Locking Ended */
	      		

					
					document.partAForm.submit();
					$(this).hide();
				}
				
		});
	  </script>	   
</html:html>
