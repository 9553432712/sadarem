<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
    Author     : 490058
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="org.bf.disability.dto.PwdRequestDTO;"%>

<%
            PwdRequestDTO pwdRequestDTO = (PwdRequestDTO) request.getAttribute("pwdRequestDTO");

            String requesttypeDetails = (String) request.getAttribute("requesttypeDetails");


%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>

        <style type="text/css">
            @media print
            {
                #non-printable { display: none; }
                #printable { display: block; }
            }
        </style>
        <style type="text/css">
            .arrowlistmenu{
                width: 185px; /*width of accordion menu*/
            }
            .arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
                                        font: bold 11px Verdana;
                                        color: #828282;
                                        padding-bottom:7px; /*header text is indented 10px*/
                                        cursor: hand;
                                        border-bottom:1px solid #828282;
                                        padding-left:5px;
            }
            .arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
                                        border-bottom:1px solid #000000;
                                        background-image:url(./DisabilityUITG/images/titlebar-active.png);
                                        padding-bottom:4px;
                                        color:#000000;
            }
            .arrowlistmenu ul{ /*CSS for UL of each sub menu*/
                               list-style-type: none;
                               margin: 0;
                               padding: 0;
                               margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
            }
            .arrowlistmenu ul li{
                padding-bottom: 2px; /*bottom spacing between menu items*/
            }
            .arrowlistmenu ul li a{
                color: #A70303;
                /*custom bullet list image*/
                display: block;
                padding:0px 0px 0px 5px;
                margin:0px 0px 0px 25px;
                text-decoration: none;
                line-height:20px;
                vertical-align:middle;
                font-weight: normal;
                border-bottom: 1px solid #dadada;
                list-style-image:url(./DisabilityUITG/images/arrowbullet.png);
                font-size: 11px;
            }
            .arrowlistmenu ul li a:visited{
                color: #ssdd33;
            }
            .arrowlistmenu ul li a:hover{ /*hover state CSS*/
                                          color: #ff9900;
                                          background-color:#FFFFFF; height:100%;
            }
        </style>
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        <script language="JavaScript">
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
                        addoption(res1,res2,"requestDistrict_id");
                        z++;
                    }
                }
            }
            function districtList(){

                var disid = document.getElementById("requestDistrict_id").value;
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
                var distid=document.getElementById("requestDistrict_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";

                x.open("GET",url,true)
                x.send();
            }
            function getMandalDetailsPWD()
            {
                var rs1,rs2;

                removeall("requestMandal_id");
                removeall("requestVillage_id");
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
                        addoption(rs1,rs2,"requestMandal_id");

                        z++;
                    }
                }
            }
            function mandalList(){
                var manid=document.getElementById("requestMandal_id").value;

                if(manid==""){
                    removeLists(4,6);
                }else{
                    createVillageObjectDetails();
                }
            }
            function  createVillageObjectDetails()
            {

                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetailsPWD;
                distid=document.getElementById("requestDistrict_id").value;
                if(distid == ""){
                    distid = document.getElementById("requestDistrict_id").value;
                }
                var mandid=document.getElementById("requestMandal_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";

                x.open("GET",url,true)
                x.send();
            }
            function getVillageDetailsPWD()
            {
                var rs1,rs2;
                removeall("requestVillage_id");
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
                        addoption(rs1,rs2,"requestVillage_id");
                        z++;
                    }
                }
            }
            function villageList(){
                var manid=document.getElementById("requestVillage_id").value;
                if(manid==""){
                    removeLists(4,6);
                }else{
                    createHabitationObjectDetails();
                }
            }

            function  createHabitationObjectDetails()
            {

                x=GetXmlHttpObject()
                x.onreadystatechange=getHabitationDetailsPWD;
                var distid=document.getElementById("requestDistrict_id").value;
                if(distid == ""){
                    distid = document.getElementById("requestDistrict_id").value;
                }
                var mandid=document.getElementById("requestMandal_id").value;
                var villageid = document.getElementById("requestVillage_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&villageid="+villageid+"&territory=6";
                x.open("GET",url,true)
                x.send();
            }

            function getHabitationDetailsPWD()
            {
                var rs1,rs2;
                removeall("requestHabitation_id");
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
                        addoption(rs1,rs2,"requestHabitation_id");
                        z++;
                    }
                }
            }
            function habiltationList(){
                var manid=document.getElementById("requestHabitation_id").value;
                if(manid==""){
                    removeLists(4,6);
                }
            }

            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");
                var slcBx4 = document.getElementById("4");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;
                document.getElementById("habitationName").value = slcBx4.options[slcBx4.selectedIndex].text;

            }
            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;
                document.getElementById(name).add(opt);
            }
            function removeall(name)
            {
                var x1=document.getElementById(name).length;

                for(i=x1;i>0;i--)
                    document.getElementById(name).options[i]=null;
                document.getElementById(name).value="";
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

            function getPersonalDetails(val){

                document.forms[0].personal.value=val.value;
                document.forms[0].mode.value="getRequestTypeDetails";
                document.forms[0].submit();

            }


            function getPersonalInforamtionDetails(){
                document.forms[0].mode.value="getPersonalInforamtionDetails";
                document.forms[0].submit();
            }



            function getDetails() {
                alert("DAta");

                if(document.forms[0].elements['personalName'].value=="") {
                    alert("Please Enter personalName");
                    document.forms[0].elements['personalName'].focus();
                    return false;
                }
                else if(document.forms[0].elements['personalrelationName'].value=="") {
                    alert("Please Enter RelationName");
                    document.forms[0].elements['personalrelationName'].focus();
                    return false;
                }
                else if(document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District!");
                    document.forms[0].elements['district_id'].focus();
                    return false;
                }

                else if(document.forms[0].elements['mandal_id'].value=="0") {
                    alert("Please Select mandal");
                    document.forms[0].elements['mandal_id'].focus();
                    return false;
                }
                else if(document.forms[0].elements['village_id'].value=="0") {
                    alert("Please Select Village!");
                    document.forms[0].elements['village_id'].focus();
                    return false;
                }
                else if(document.forms[0].elements['habitation_id'].value=="") {
                    alert("Please Select Habitation!");
                    document.forms[0].elements['habitation_id'].focus();
                    return false;
                }
                else if(document.forms[0].elements['houseNo'].value=="") {
                    alert("Please Enter HouseNo!");
                    document.forms[0].elements['houseNo'].focus();
                    return false;
                }
                else if(document.forms[0].elements['pinCode'].value=="") {
                    alert("Please Enter PinCode!");
                    document.forms[0].elements['pinCode'].focus();
                    return false;
                }
                else if(document.forms[0].elements["contactNo"].value.length < 10) {
                    alert("Mobile Number Should be TEN Digits");
                    document.forms[0].elements["contactNo"].value="";
                    document.forms[0].elements["contactNo"].focus();
                    flag = false;
                    return false;
                }else if(document.forms[0].elements['email'].value=="") {
                    alert("Please Enter Email!");
                    document.forms[0].elements['email'].focus();
                    return false;
                }
                else if(document.forms[0].elements['sscFile'].value=="") {
                    alert("Please Brows Ssc Documents!");
                    document.forms[0].elements['sscFile'].focus();
                    return false;
                }
                else if(document.forms[0].elements['addressProof'].value=="") {
                    alert("Please Brows Address Documents!");
                    document.forms[0].elements['addressProof'].focus();
                    return false;
                }
                else{
                    document.forms[0].mode.value="insertDetails";
                    document.forms[0].submit();
                }


            }


            function loopSelected(){

                var selectedArray = new Array();
                var selObj = document.getElementById('requestId');

                var i;
                var count = 0;
                for (i=0; i<selObj.options.length; i++) {
                    if (selObj.options[i].selected) {
                        document.getElementById('id'+selObj.options[i].value).style.display='';
                        count++;
                    }else {
                        document.getElementById('id'+selObj.options[i].value).style.display='none';
                    }
                }
            }
            function loopRequestIDDetails(){
                var id = document.forms[0].requestIdData.value;
                document.getElementById('id').style.display='none';

                if(id==6){
                    document.getElementById('id').style.display='none';
                }
                else if(id==0){
                    document.getElementById('id').style.display='none';
                }
                else{
                    document.getElementById('id').style.display='';
                }
            }

            function validateSadaremID(){

                document.forms[0].mode.value="getValidateSadaremDetails";
                document.forms[0].submit();
            }

            function ristrictSpace() {
                var code = window.event.keyCode;
                if (code == 32)
                {
                    window.event.keyCode = 0
                    return false;
                }
            }


            function formValidation1() {
                <!-- Email Validation-->
                var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                var address = document.forms[0].elements['email'].value;

                if(reg.test(address) == false) {

                    alert('Invalid Email Address');
                    document.forms[0].elements['email'].value="";
                    document.forms[0].elements["email"].focus();
                    return false;

                }

                <!-- End for email Validation-->
            }

            function isAlpha(keyCode,name)
            {
                var isShift=false;
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

            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function zeroFunction(ids) {

                var stdCode= document.forms[0].elements[ids].value;
                if(stdCode.substr(0,1)=="0") {
                    alert("First Digit Should not be ZERO");
                    document.forms[0].elements[ids].value="";
                }
            }

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

            function validateSadaremIDDetails(){
                document.forms[0].mode.value="getValidateSadaremDataPersoncode";
                document.forms[0].submit();

            }

            function goBack(){

                document.forms[0].mode.value="backRequest";
                document.forms[0].submit();

            }



        </script>


        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
    </head>
    <body>

        <html:form action="/pwdRequest">
            <html:hidden property="mode"/>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/cssmaster.css" type="text/css">

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/DistrictDetails.js"></script>

        <table width="99%" border="1" cellspacing="0" cellpadding="10" align="center">

            <tr>
                <td height="445" align="center" bgcolor="#e4f5fd" valign="top">
                    <br/><br/><br/>
                    <table border="0" cellspacing="0" cellpadding="0" width="80%" align="center" style="border:1px #000 solid;">
                        <div id="printable">

                            <tr>
                                <td colspan="3" bgcolor="#f4f4f4" style="border:1px #7c7c7c solid;">
                                    <table border="0" cellspacing="0" cellpadding="1" width="100%" align="center">

                                        <tr class="label">
                                            <td class="formhdbg" align="center" > <b>ACKNOWLEDGEMENT FOR GRIEVANCES.</b></td>
                                            <td class="label"></td>
                                        </tr>
                                        <tr>
                                       <!-- <td align="left"  class="label">SADAREM ID:&nbsp;&nbsp;<font color="blue"><%=pwdRequestDTO.getReciptPersonCode()%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
                                            <td align="left" class="formhdbg" ><font size="2">Registration Number:</font>
                                                <font color="white" size="2"><%=pwdRequestDTO.getReciptRequestId()%></font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left" class="label" style="line-height:20px;padding:5px">
                                                <br/><strong>
                                                    <p>This is acknowledged that
                                                        <font color="blue">&nbsp;<%=pwdRequestDTO.getReciptSurName()%></font><font color="blue">&nbsp;<%=pwdRequestDTO.getReciptName()%></font>
                                                        &nbsp;C/o&nbsp;
                                                        <font color="blue">&nbsp;<%=pwdRequestDTO.getReciptRelationName()%>&nbsp;</font>
                                                        &nbsp;Address &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptHouseno()%>,</font>
                                                        &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptHabitationName()%></font>&nbsp;Habitation,
                                                        &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptVillageName()%></font>&nbsp;Village,
                                                        &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptMandalName()%></font>&nbsp;Mandal,
                                                        &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptDistrictName()%></font>&nbsp;District,
                                                        &nbsp;is applied for  the &nbsp;
                                                        &nbsp;<font color="blue"><%=pwdRequestDTO.getReciptRequestTypeName()%></font>&nbsp;&nbsp;on&nbsp;&nbsp; <font color="blue"><%=pwdRequestDTO.getReciptRegisteratedDate()%></font>.
                                                    </p></strong> 

                                            </td>
                                        </tr>

                                        <tr>
                                            <td align="left" class="label" colspan="2" style="line-height:20px;padding:5px">
                                                <strong><p STYLE="color: red">Note:&nbsp;In case you have any clarification regarding Grievances please contact to Project Director-DRDA of the concern District.</p>
                                                </strong>
                                            </td>
                                        </tr>

                        </div>
                    </table>

                    <br/>
                   
                </td>
            </tr>
        </table>
                                                     <div id="non-printable">
                        <table align="center">
                            <tr>
                                <td>
                                    <html:button property="but" value="Print" onclick="window.print()"/>
                                </td>
                                <td>
                                    <html:button property="but" value="Home" onclick="goBack();"/>
                                </td>
                            </tr>
                        </table>
                    </div><br/><br/><br/><br/><br/><br/>
        <script language="javascript" src="./DisabilityUITG/js/Mainfooter.js"></script>
    </html:form>
</body>

</html:html>