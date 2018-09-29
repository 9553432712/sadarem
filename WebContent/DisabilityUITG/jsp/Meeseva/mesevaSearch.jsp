<%--
    Document   : MesevaNewCertificatePersonalDetailsWithPensionNo
    Created on : Jan 17, 2014, 3:36:43 PM
    Author     : 310926
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<%
            int i = 1;
%>

<html:html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
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

            function getMeesevaSearchDetails(){
                if(document.forms[0].elements['sadaremId'].value!="") {
                    if(document.forms[0].elements['sadaremId'].value.length < "17") {
                        alert("Please Enter the Valid Sadarem Number");
                        //document.forms[0].elements['sadaremId'].value="";
                        document.forms[0].elements['sadaremId'].focus();
                        return false;
                    }
                }


                if(document.forms[0].elements['pensionId'].value=="" && document.forms[0].elements['district_id'].value=="0" &&
                    document.forms[0].elements['mandal_id'].value=="0" && document.forms[0].elements['panchayat_id'].value=="0"
                    && document.forms[0].elements['village_id'].value=="0" &&
                    document.forms[0].elements['name'].value=="" &&document.forms[0].elements['aadharCardNo'].value=="" &&
                    document.forms[0].elements['sadaremId'].value=="" && document.forms[0].elements['rationCardNo'].value=="") {
                    alert("Please Select Atleast one Option");
                    return false;
                }
                var rationval = document.forms[0].elements['rationCardNo'].value;
                if(rationval.length>0){
                    if(document.forms[0].elements["rationCardNo"].value.length < 15) {
                        alert("Please Enter Valid RationCard No");
                        document.forms[0].elements["rationCardNo"].focus();
                        document.forms[0].elements["rationCardNo"].value="";
                        return false;
                    }else if(rationval.substring(0,3)!="WAP" && rationval.substring(0,3)!="PAP" && rationval.substring(0,3)!="AAY" &&
                        rationval.substring(0,3)!="AAP" && rationval.substring(0,3)!="YAP" && rationval.substring(0,3)!="wap" &&
                        rationval.substring(0,3)!="pap" && rationval.substring(0,3)!="aay" &&  rationval.substring(0,3)!="aap" &&
                        rationval.substring(0,3)!="yap" && rationval.substring(0,3)!="RAP" && rationval.substring(0,3)!="rap"
                        && rationval.substring(0,3)!="TAP" && rationval.substring(0,3)!="tap"
                        && rationval.substring(0,3)!="WAD" && rationval.substring(0,3)!="wad") {
                        alert("Please Enter Valid RationCard Number");
                        document.forms[0].elements["rationCardNo"].focus();
                        document.forms[0].elements["rationCardNo"].value="";
                        return false;
                    }
                }
                if(document.forms[0].elements['pensionId'].value!="")
                {
                    if(document.forms[0].elements['district_id'].value=="0") {
                        alert("Please Select District");
                        document.getElementById("district_id").focus();
                        return false;
                    }
                }
                
                document.forms[0].mode.value="getMeesevaSearchDetails";
                document.forms[0].submit();
            }
            
            function getCampDetails(){
                document.forms[0].mode.value="getCampDetails";
                document.forms[0].submit();
            }
            
            function OpenWindow(url){
                var features="#width=800,height=500,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes,top=10,left=50,resizable=yes,titlebar=no,directories=no";
                popup1 =window.open(url,"_blank",features);
            }


            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }

            function isAlphaNumeric(keyCode) {
                if (keyCode == 16)
                    isShift = true;
                var res = (((keyCode >= 48 && keyCode <= 57) && isShift == false) ||
                    (keyCode >= 65 && keyCode <= 90) || keyCode == 8 || (keyCode >= 96 && keyCode <= 105)
                    || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46);
                return res;
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

    </head>
    <body>
        <html:form  action="/meesevaSearch">
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
                    <td  align="center" valign="top">
                        <br/>
                        <logic:present name="msg">
                            <center>${msg}</center>
                        </logic:present>

                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" class="inputform">
                            <tr>
                                <th  colspan="4">SADAREM Certificate Print</th>
                            </tr>
                            <tr>
                                <td>SADAREM ID</td>
                                <td>
                                    <html:text  property="sadaremId" style="width:160px" maxlength="17" onkeypress="return onlyNumbers();"/>
                                </td>

                                <td>AadharCard No</td>
                                <td>
                                    <html:text  property="aadharCardNo" style="width:160px" maxlength="12" onkeypress="return onlyNumbers();"/>
                                </td>
                            </tr>

                            <tr>
                                <td>RationCard No</td>
                                <td>
                                    <html:text  property="rationCardNo" style="width:160px" maxlength="15" onkeypress="return onlyNumbers();"/>
                                </td>

                                <td>Pension ID</td>
                                <td>
                                    <html:text  property="pensionId" style="width:160px" maxlength="9" onkeypress="return onlyNumbers();"/>
                                </td>
                            </tr>

                            <tr>
                                <td>Name</td>
                                <td>
                                    <html:text  property="name" style="width:160px" maxlength="75" onkeydown="return isAlphaNumeric(event.keyCode);" onkeypress="return space(event,this)"/>
                                </td>

                                <td width="30%">District</td>
                                <td width="70%">
                                    <html:select styleId="1" property="district_id" onchange="createMandalObject(),getCampDetails();" style="width:160px;">
                                        <html:option  value="0">- - Select - -</html:option>
                                        <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>

                                <td>Mandal</td>
                                <td>
                                    <html:select styleId="2" property="mandal_id" style="width:160px;" onchange="createPanchayatObject();">
                                        <html:option  value="0">- - All - -</html:option>
                                        <html:optionsCollection property="mandalList" label="mandal_name" value="mandal_id"  />
                                    </html:select>
                                </td>

                                <td  width="30%">Panchayat </td>
                                <td width="70%">
                                    <html:select styleId="3" property="panchayat_id" style="width:160px;" onchange="createVillageObject();">
                                        <html:option  value="0">- - All - -</html:option>
                                        <html:optionsCollection property="panchayatList" label="panchayat_name" value="panchayat_id"  />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Town/Village </td>
                                <td width="70%">
                                    <html:select property="village_id" style="width:160px;" onchange="createHabitationObject();">
                                        <html:option value="0">- - All - -</html:option>
                                        <html:optionsCollection property="villagelist" label="village_name" value="village_id" />
                                    </html:select>
                                </td>

                                <td width="30%">Habitation/Ward No </td>
                                <td width="70%">
                                    <html:select styleId="6" property="habitation_id" style="width:160px;">
                                        <html:option  value="0">- - All - -</html:option>
                                        <html:optionsCollection property="habitationlist" label="habitation_name" value="habitation_id"  />
                                    </html:select>
                                </td>

                            </tr>
                            <tr>
                                <td>Camp</td>
                                <td colspan="4">
                                    <html:select property="campId"  style="width:400px;">
                                        <html:option value="">- - All - -</html:option>
                                        <html:optionsCollection property="campList" label="camp_name" value="camp_id"/>
                                    </html:select>
                                </td>
                            </tr>

                            <tr>
                                <th align="center" colspan="4">
                                    <input type="button"  value="Search" Id="subButton" styleClass="button"  onclick="return getMeesevaSearchDetails();" />
                                </th>
                            </tr>
                        </table>
                        <br>
                        <logic:present name="certificateValidList">


                            <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" class="inputform">

                                <tr style="text-decoration: none; font-weight: bold; font-size: 13px; color: red;">Note: For Print Payment Charges Rs.25/-.</tr>
                                <tr>
                                    <th>
                                        S.No
                                    </th>
                                    <th>
                                        Person Code
                                    </th>
                                    <th>
                                        Name
                                    </th>
                                    <th>
                                        Age
                                    </th>
                                    <th>
                                        RelationName
                                    </th>
                                    <th>
                                        Gender
                                    </th>
                                    <th>
                                        Action
                                    </th>

                                </tr>
                                <logic:iterate name="certificateValidList" id="row">
                                    <tr>
                                        <td>
                                            <%=i++%>.
                                        </td>
                                        <td>
                                            ${row.personCode}
                                        </td>
                                        <td>
                                            ${row.name}
                                        </td>
                                        <td>
                                            ${row.ageYears}
                                        </td>
                                        <td>
                                            ${row.relationName}
                                        </td>
                                        <td>
                                            ${row.gender}
                                        </td>
                                        <td>
                                            <a href="javascript:OpenWindow('meesevaSearch.do?mode=getIndivialCertificateDetails&personCode=${row.personCode}&districtName=${row.districtName}&mandalName=${row.mandalName}&districtId=${row.districtId}&mandalId=${row.mandalId}&name=${row.name}&mobile=${row.phone}&uniqueNo=${uniqueNo}&scaUserId=${scaUserId}&userId=${userId}&operatorId=${operatorId}&checkSum=${checkSum}&requestId=${requestId}&serviceid=${serviceid}&scaPassword=${scaPassword}&applicationNo=${applicationNo}&meesevaflag=${meesevaflag}&encryptedString=${encryptedString}&villageId=${row.villageId}&districtId=${row.districtId}&mandalId=${row.mandalId}&habCode=${row.habCode}&districtName=${row.districtName}&mandalName=${row.mandalName}&villageName=${row.villageName}&flagTableStatus=${row.flagTableStatus}&totaldisability=${row.totaldisability}');" style="text-decoration: none; font-weight: bold; font-size: 13px; color: blue;">Print</a>
                                        </td>

                                    </tr>
                                </logic:iterate>
                            </table>
                        </logic:present>

                    </td>
                </tr>
            </table>

        </html:form>
    </body>
</html:html>
