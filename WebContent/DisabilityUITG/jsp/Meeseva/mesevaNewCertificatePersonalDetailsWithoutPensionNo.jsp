<%-- 
    Document   : MesevaNewCertificatePersonalDetailsWithoutPensionNo
    Created on : Jan 17, 2014, 3:38:44 PM
    Author     : 310926
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%
            String amt = "25";
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>

        <script>
            var isShift=false;

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


            <!-------Ends script For Allow Alphabets Only -------------------->




            <!-------Starts script Allow Alphabets and Numerics -------------------->


            function isAlphaNumeric(keyCode) {
                if (keyCode == 16)
                    isShift = true;
                var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                    (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                    || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                return res;
            }

            <!-------Ends script Allow Alphabets and Numerics -------------------->
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            <!-------Starts script Allow Alphabets and Numerics For House Number-------------------->


            function isAlphaNumericHouseNumber(keyCode,name) {

                if (keyCode == 16 )
                    isShift = true;
                var str = name.value;
                if(str.substring(0,1)==" " || str.substring(0,1)==0)
                {
                    name.value ="";
                    name.focus();
                    var res =(((keyCode >= 49 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                }else{

                    var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                        (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                        || keyCode == 9 || keyCode == 37 || keyCode == 39 ||  keyCode == 32 || keyCode == 46
                        || keyCode == 189 || keyCode == 191);
                }
                return res;
            }


            function alphavalidation(val)
            {
                var i;
                var valid="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

                if(val!=null)

                {
                    for(i=0;i<val.length;i++)
                    {

                        if(valid.indexOf(val.charAt(i))<0)
                        {

                            alert("Please enter Characters only");
                            document.forms[0].relationship_with_benificiary.value="";
                            document.forms[0].relationship_with_benificiary.focus();
                            break;
                        }

                    }
                }
            }
            function alphavalidation(val)
            {
                var i;
                var valid="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

                if(val!=null)

                {
                    for(i=0;i<val.length;i++)
                    {

                        if(valid.indexOf(val.charAt(i))<0)
                        {

                            alert("Please enter Characters only");
                            document.forms[0].relationship_with_benificiary.value="";
                            document.forms[0].relationship_with_benificiary.focus();
                            break;
                        }

                    }
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


            function getDetails(){
                if( document.forms[0].requestNumber.value==null ||document.forms[0].requestNumber.value=="" ){
                    alert("Please Enter Request Number");
                    document.forms[0].requestNumber.focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getRdcallcentreRequestSearch";
                    document.forms[0].submit();
                    return true;
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
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }else if(name=="habitation_id") {
                        document.forms[0].habitation_id.appendChild(opt,null);
                    }else if(name=="panchayat_id") {
                        document.forms[0].panchayat_id.appendChild(opt,null);
                    }
                }
            }
            function removeall(name)
            {
                if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }else if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                }else if(name=="habitation_id") {
                    var x1=document.forms[0].habitation_id.options.length;
                }else if(name=="panchayat_id") {
                    var x1=document.forms[0].panchayat_id.options.length;
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

            
            var x = null;
            function  createMandalObject()
            {
                x = GetXmlHttpObject()
                x.onreadystatechange=getMandalDetails;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&territory=2";
                x.open("GET",url,true)
                x.send();
            }

            function getMandalDetails()
            {
                var rs1,rs2;
                removeall("mandal_id");
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
                        addoption(rs1,rs2,"mandal_id");
                        z++;
                    }
                }
            }

            function mandalList(){
                var mandalid = document.forms[0].mandal_id.value;
                var slcBx = document.getElementById("2");
                document.getElementById("mandalname").value = slcBx.options[slcBx.selectedIndex].text;
                if(mandalid == ""){
                    removeLists(6,6);
                }else{
                    createHabitationObject();
                }
            }
            function  createPanchayatObject()
            {
                x = GetXmlHttpObject()
                x.onreadystatechange=getPanchayatDetails;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&territory=4";
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
                var villageid = document.forms[0].panchayat_id.value;
                var slcBx = document.getElementById("3");
                document.getElementById("panchayatname").value = slcBx.options[slcBx.selectedIndex].text;
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
               
                var pan=document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&panchayatid="+pan+"&territory=7";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
                // removeall("panchayat_id");
                //    removeall("habitation_id");
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
                var villageid = document.forms[0].village_id.value;
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

                var villageid = document.forms[0].village_id.value;
                var panchayat = document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].district_id.value+"&mandalid="+document.forms[0].mandal_id.value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=8";

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

            
            function  createAadharVillageObject()
            {

                //var distid;
                
                x=GetXmlHttpObject()

                x.onreadystatechange=getVillageDetails;

                var pan=document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].adhaarDistrictId.value+"&mandalid="+document.forms[0].adhaarMandalId.value+"&panchayatid="+pan+"&territory=7";
                x.open("GET",url,true);
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
                // removeall("panchayat_id");
                //    removeall("habitation_id");
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
                var villageid = document.forms[0].village_id.value;
                var slcBx = document.getElementById("4");
                document.getElementById("villagename").value = slcBx.options[slcBx.selectedIndex].text;
                if(villageid == ""){
                    removeLists(5,6);
                }else{
                    createPanchayatObject();
                }
            }






            function  createAadharHabitationObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetails;
                

                var villageid = document.forms[0].village_id.value;
                var panchayat = document.forms[0].panchayat_id.value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+document.forms[0].adhaarDistrictId.value+"&mandalid="+document.forms[0].adhaarMandalId.value+"&villageid="+villageid+"&panchayatid="+panchayat+"&territory=8";

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

            function alphavalidation(val)
            {
                var i;
                var valid="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

                if(val!=null)

                {
                    for(i=0;i<val.length;i++)
                    {

                        if(valid.indexOf(val.charAt(i))<0)
                        {

                            alert("Please enter Characters And Numerics only");
                            document.forms[0].proofId.value="";
                            document.forms[0].proofId.focus();
                            break;
                        }

                    }
                }
            }




            function checkphoto(str,leaf)
            {
                var ext = str.value;

                ext = ext.substring(ext.lastIndexOf(".")+1);
                ext=ext.toString().toLowerCase();


                if(!((ext== "jpg")||(ext=="jpeg")))
                {
                    alert('You selected a " .'+ext+' "  file; Please Select a image file');
                    document.getElementById(leaf).innerHTML = document.getElementById(leaf).innerHTML;
                    return false;
                }else{
                    return true;
                }
            }
            function submitButtons(){

                if(document.forms[0].proofId.value==null || document.forms[0].proofId.value==""){
                    alert("Please Enter Proof ID");
                    document.forms[0].proofId.focus();
                    return false;
                } else if(document.forms[0].proofType.value==null || document.forms[0].proofType.value==""){
                    alert("Please Select Proof Type");
                    document.forms[0].proofType.focus();
                    return false;
                }

                else if(document.forms[0].proofType.value!="Adhaar Card"){
                    if(document.forms[0].elements['uploadDocument'].value=="") {
                        alert("Please Browse Documents!");
                        document.forms[0].elements['uploadDocument'].focus();
                        return false;
                    }
               
               
                    if(document.forms[0].surname.value==null || document.forms[0].surname.value==""){
                        alert("Please Enter Surname");
                        document.forms[0].surname.focus();
                        return false;
                    }else if(document.forms[0].firstname.value==null || document.forms[0].firstname.value==""){
                        alert("Please Enter Name");
                        document.forms[0].firstname.focus();
                        return false;
                    }
                    else if(document.forms[0].relationName.value==null || document.forms[0].relationName.value==""){
                        alert("Please Enter Father/ Mother/ Husband/ Guardian's Name");
                        document.forms[0].relationName.focus();
                        return false;
                    }
                } if(document.forms[0].relationType.value==null || document.forms[0].relationType.value==""){
                    alert("Please Select Relation");
                    document.forms[0].relationType.focus();
                    return false;
                }else if(document.forms[0].age.value==null || document.forms[0].age.value==""){
                    alert("Please Enter Age");
                    document.forms[0].age.focus();
                    return false;
                }
                if(document.forms[0].proofType.value!="Adhaar Card"){
                    if(document.forms[0].gender.value==null || document.forms[0].gender.value==""){
                        alert("Please Select Gender");
                        document.forms[0].gender.focus();
                        return false;
                    }
                }if(document.forms[0].phoneno.value==null || document.forms[0].phoneno.value==""){
                    alert("Please Enter Phone No");
                    document.forms[0].phoneno.focus();
                    return false;
                }
                
                if(document.forms[0].proofType.value!="Adhaar Card"){
                    if(document.forms[0].district_id.value=="0"){
                        alert("Please Select District");
                        document.forms[0].district_id.focus();
                        return false;
                    }else if(document.forms[0].mandal_id.value=="0"){
                        alert("Please Select Mandal");
                        document.forms[0].mandal_id.focus();
                        return false;
                    }
                }
                if(document.forms[0].panchayat_id.value=="0"){
                    alert("Please Select Panchayat");
                    document.forms[0].panchayat_id.focus();  
                    return false;
                }else if(document.forms[0].village_id.value=="0"){
                    alert("Please Select Town/Village");
                    document.forms[0].village_id.focus();
                    return false;
                }else if(document.forms[0].habitation_id.value=="0"){
                    alert("Please Select Habitation/Ward No");
                    document.forms[0].habitation_id.focus();
                    return false;
                }else{
                    var flag=true;
                    if(document.forms[0].proofId.value!=null && document.forms[0].proofId.value!=""){
                        var leng=false;
                        var proof_Id=document.forms[0].proofId.value;
                        if(document.forms[0].proofType.value=='Adhaar Card'){
                            var numbers = /^[0-9]+$/;
                            if(proof_Id.length==12){
                                leng=true;
                            }
                            if((!leng)||(!proof_Id.match(numbers))) {
                                flag=false;
                                alert("Please Enter Valid Adhaar Card.Adhaar Card Must have 12 digits");
                                return false;
                            }
                        }
                    }
                    if(document.forms[0].phoneno.value!=null && document.forms[0].phoneno.value!="") {
                        var no=document.forms[0].phoneno.value;
                        if(no.length<10){
                            alert("Please Enter Valid  Phone No");
                            document.forms[0].phoneno.value="";
                            document.forms[0].phoneno.focus();
                            flag = false;
                        }
                    }
                    if(flag){
                        document.getElementById('subButton').disabled = true;
                        document.forms[0].action="mesevaNewCertificateRegistration.do?mode=submitMesevaNewCertificateDetails&flag=without";
                        document.forms[0].submit();
                        return true;
                    }
                }
            }
            function submitBack(){
                
                document.getElementById('subButtonBack').disabled = true;
                document.forms[0].action="mesevaNewCertificateRegistration.do?mode=getMesevaNewCertificateRegistration&flag=back";
                document.forms[0].submit();
               
            }
            function getProofDetials(id){
                
                if(id=="Adhaar Card"){
                    document.getElementById('uploadId').style.display='none';
                }else {
                    document.getElementById('uploadId').style.display='';
                }
                if(document.forms[0].proofId.value==""){
                    alert("Please Enter Proof ID");
                    return false;

                }else if(document.forms[0].proofType.value==""){
                    alert("Please Enter Proof Type");
                    return false;
                }else{
                    document.forms[0].mode.value="getAadhaarProofDetials";
                    document.forms[0].submit();
                }
            }

            function getProofIdValue(id){
                if(id!=null){
                    if(document.forms[0].proofType.value!=""){
                        document.forms[0].proofType.value="";
                        alert("Please Enter Proof ID");
                        return false;
                    }


                }

            }

        </script>

    </head>
    <body >


        <html:form  action="/mesevaNewCertificateRegistration"  enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="SCAUserId"/>
            <html:hidden property="uniqueNo"/>
            <html:hidden property="loginId"/>
            <html:hidden property="channelId"/>
            <html:hidden property="serviceid"/>
            <html:hidden property="scaPassword"/>
            <html:hidden property="applicationNo"/>
            <html:hidden property="meesevaFlag"/>
            <html:hidden property="requestId"/>
            <html:hidden property="encryptedString"/>
            <html:hidden property="checkSum"/>

            <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
                <tr>
                    <td align="center"  valign="top">

                        <logic:present name="msg">
                            <p>${msg}</p>

                        </logic:present>

                        <br/>
                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%"  class="inputform">
                            <tr>
                                <th  colspan="4" >SADAREM New Certificate Registration Through MEESEVA</th>
                            </tr>

                            <tr>
                                <td  colspan="4">Personal Details</td>
                            </tr>
                            <tr>
                                <td  colspan="4">Proof Of Document:</td>
                            </tr>
                            <tr>

                                <td>Proof ID:<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="proofId"  maxlength="20"  onchange="alphavalidation(this.value),getProofIdValue(this.value);" style="width:160px" />
                                </td>

                                <td width="30%">Proof Type:<font color="red"><b>*</b></font></td>
                                <td width="70%">
                                    <html:select styleId="1" property="proofType"  style="width:160px;" onchange="return getProofDetials(this.value);">
                                        <html:option value="">- - SELECT - -</html:option>
                                        <html:optionsCollection property="proofList" label="proofType" value="proofType" />

                                    </html:select>
                                </td>


                            </tr>
                            <tr  id="uploadId" style="display: none;">

                                <td width="30%">Upload Documents<font color="red"><b>*</b></font></td>
                                <td width="70%" colspan="3"><div id="leaf"><html:file property="uploadDocument" styleId="uploadDoc" onchange="return checkphoto(this,'leaf')"/> </div>
                                    (Max sixe:100 Kb, Only jpg file)
                                </td>
                            </tr>

                            <logic:present name="adhaarFlag">
                                <tr>
                                    <td  width="50%">Surname<font color="red"><b>*</b></font></td>
                                    <td width="50%">
                                        <html:text property="adhaarSurname"  style="width:160px"  readonly="true"/>
                                    </td>

                                    <td >
                                        Name<font color="red"><b>*</b></font>
                                    </td>
                                    <td >
                                        <html:text property="adhaarName"  style="width:160px"  readonly="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="30%" >Father/ Mother/ Husband/ Guardian's Name<font color="red"><b>*</b></font></td>
                                    <td  width="70%">
                                        <html:text property="adhaarRelationName"  style="border:1px #ccc solid;width:350px;"  readonly="true"/>
                                    </td>
                                    <td>Gender<font color="red"><b>*</b></font></td>
                                    <td >
                                        <html:text property="adhaarGender"  style="width:160px" readonly="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td> Relation<font color="red"><b>*</b></font> </td>
                                    <td>
                                        <html:select property="relationType"  style="width:160px;">
                                            <html:option value="">- - SELECT - -</html:option>
                                            <html:option value="1">Father</html:option>
                                            <html:option value="2">Mother</html:option>
                                            <html:option value="3">Husband</html:option>
                                            <html:option value="7">Wife</html:option>
                                            <html:option value="5">Brother</html:option>
                                            <html:option value="6">Sister</html:option>
                                            <html:option value="4">Guardian</html:option>
                                        </html:select>
                                    </td>

                                    <td>Age <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text  property="age"  maxlength="2" onkeypress="return onlyNumbers();" style="width:160px" ></html:text>
                                    </td>

                                </tr>

                                <tr>

                                    <td width="30%">Phone No<font color="red" size="2"><b>*</b></font></td>
                                    <td width="70" colspan="3">
                                        <html:text  property="phoneno"  maxlength="15" onkeypress="return onlyNumbers();"style="width:160px" ></html:text>
                                    </td>

                                </tr>
                                <tr>
                                    <td  width="30%" >House No<font color="red"><b>*</b></font></td>
                                    <td  width="70%" colspan="3">
                                        <html:text property="adhaarHouseNo"  style="width:160px"  readonly="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  colspan="4">Address:</td>
                                </tr>
                                <tr>
                                    <td>District<font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="adhaarDistrictName"  style="width:160px"  readonly="true"/>
                                        <html:hidden property="adhaarDistrictId"/>
                                        <html:hidden property="district_id"/>

                                    </td>

                                    <td>Mandal<font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text property="adhaarMandalName"  style="width:160px"  readonly="true"/>
                                        <html:hidden property="adhaarMandalId"/>
                                        <html:hidden property="mandal_id"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td  width="30%">Panchayat<font color="red"><b>*</b></font> </td>
                                    <td width="70%">
                                        <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createAadharVillageObject();">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="panchayatlist" label="panchayat_name" value="panchayat_id"  />
                                        </html:select>
                                    </td>

                                    <td >Town/Village<font color="red"><b>*</b></font> </td>
                                    <td>
                                        <html:select property="village_id" style="width:160px;" onchange="createAadharHabitationObject();">
                                            <html:option value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="villagelist" label="village_name" value="village_id" />
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">Habitation/Ward No<font color="red"><b>*</b></font> </td>
                                    <td width="70%" >
                                        <html:select styleId="6" property="habitation_id" style="width:160px;">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        &nbsp;

                                    </td>
                                </tr>
                            </logic:present>
                            <logic:notPresent name="adhaarFlag">
                                <tr>
                                    <td  width="50%">Surname<font color="red"><b>*</b></font></td>
                                    <td width="50%">
                                        <html:text property="surname"  maxlength="25" onkeydown="return isAlpha(event.keyCode,surname);" onkeypress="return space(event,this)"
                                                   onchange="capitalizeMe(this)" style="width:160px"/>
                                    </td>

                                    <td >
                                        Name<font color="red"><b>*</b></font>
                                    </td>
                                    <td >
                                        <html:text property="firstname"  maxlength="25" onkeydown="return isAlpha(event.keyCode,firstname);" onkeypress="return space(event,this)"
                                                   onchange="capitalizeMe(this)" style="width:160px"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td  width="30%" >Father/ Mother/ Husband/ Guardian's Name<font color="red"><b>*</b></font></td>
                                    <td  width="70%">
                                        <html:text property="relationName"  maxlength="30" onkeydown="return isAlpha(event.keyCode,relationName);" onkeypress="return space(event,this)"
                                                   onchange="capitalizeMe(this)" style="width:160px"/>
                                    </td>

                                    <td>Gender<font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:select property="gender"  style="width:160px;">
                                            <html:option value="">- - SELECT - -</html:option>
                                            <html:option value="1">Male</html:option>
                                            <html:option value="2">Female</html:option>
                                        </html:select>
                                    </td>
                                </tr>

                                <tr>
                                    <td> Relation<font color="red"><b>*</b></font> </td>
                                    <td>
                                        <html:select property="relationType"  style="width:160px;">
                                            <html:option value="">- - SELECT - -</html:option>
                                            <html:option value="1">Father</html:option>
                                            <html:option value="2">Mother</html:option>
                                            <html:option value="3">Husband</html:option>
                                            <html:option value="7">Wife</html:option>
                                            <html:option value="5">Brother</html:option>
                                            <html:option value="6">Sister</html:option>
                                            <html:option value="4">Guardian</html:option>
                                        </html:select>
                                    </td>

                                    <td>Age <font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:text  property="age"  maxlength="2" onkeypress="return onlyNumbers();" style="width:160px" ></html:text>
                                    </td>

                                </tr>

                                <tr>

                                    <td width="30%">Phone No<font color="red" size="2"><b>*</b></font></td>
                                    <td width="70"  colspan="3">
                                        <html:text  property="phoneno"  maxlength="15" onkeypress="return onlyNumbers();"style="width:160px" ></html:text>
                                    </td>

                                </tr>
                                <tr>

                                    <td> House No.</td>
                                    <td  colspan="3">
                                        <html:text  property="houseno" maxlength="15" onkeydown="return isAlphaNumericHouseNumber(event.keyCode,houseno)" style="width:160px" > </html:text>
                                    </td>

                                </tr>

                                <tr>
                                    <td  colspan="4">Address:</td>
                                </tr>
                                <tr>
                                    <td width="30%">District<font color="red"><b>*</b></font></td>
                                    <td width="70%">
                                        <html:select styleId="1" property="district_id" style="width:160px;" onchange="createMandalObject();">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="districtlist" label="district_name" value="district_id"  />
                                        </html:select>
                                    </td>


                                    <td>Mandal<font color="red"><b>*</b></font></td>
                                    <td>
                                        <html:select styleId="2" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="mandallist" label="mandal_name" value="mandal_id"  />
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td  width="30%">Panchayat<font color="red"><b>*</b></font> </td>
                                    <td width="70%">
                                        <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="panchayatlist" label="panchayat_name" value="panchayat_id"  />
                                        </html:select>
                                    </td>

                                    <td >Town/Village<font color="red"><b>*</b></font> </td>
                                    <td>
                                        <html:select property="village_id" style="width:160px;" onchange="createHabitationObject();">
                                            <html:option value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="villagelist" label="village_name" value="village_id" />
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">Habitation/Ward No<font color="red"><b>*</b></font> </td>
                                    <td width="70%">
                                        <html:select styleId="6" property="habitation_id" style="width:160px;">
                                            <html:option  value="0">- - SELECT - -</html:option>
                                            <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />
                                        </html:select>
                                    </td>
                                    <td colspan="2">
                                        &nbsp;
                                    </td>
                                </tr>
                            </logic:notPresent>
                            <%--</table>

                    <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" >
                            --%>

                            <%--</table>
                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" >--%>

                            <tr>
                                <td  colspan="4">Payment Details:</td>
                            </tr>
                            <tr>

                                <td width="30%">Payment charges (Rs.):<font color="red"><b>*</b></font></td>
                                <td width="70%" colspan="3"><html:text  property="amount" value="<%=amt%>" readonly="true" style="width:160px"></html:text>
                                </td>
                            </tr>

                            <%--/table>
                            <table>--%>
                            <tr>
                                <th align="center" colspan="4">
                                    <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitButtons();" />
                                    <input type="button"  value="Back" Id="subButtonBack" styleClass="button"  onclick="return submitBack();" />

                                </th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

        </html:form>
        <logic:present name="upladFlag">
            <script>
                document.getElementById('uploadId').style.display='';
            </script>
        </logic:present>
    </body>
</html:html>

