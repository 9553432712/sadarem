<%-- 
    Document   : AssistiveDevicesProthosis
    Created on : Dec 27, 2010, 9:35:36 AM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String hName = (String) request.getParameter("habitationName");
            String typeOfDisability = (String)request.getParameter("typeofdisability");            
            String category = (String) request.getParameter("reportcategory");
            String subCategory = (String) request.getParameter("reportSubcategory");
            
            ArrayList assistiveDevicesProthosisList = new ArrayList();
            assistiveDevicesProthosisList = (ArrayList) request.getAttribute("prothosisList");
            int shoulderTotal = 0, aboveElbowTotal = 0, total = 0;
            int elbowDisarticulationTotal = 0, belowElbowTotal = 0, wristDisarticulationTotal = 0;
            int handTotal = 0, cosmeticFingerTotal = 0, hipDisarticulationTotal = 0;
            int aboveKneeTotal = 0, kneeDisarticulationTotal = 0, belowKneeTotal = 0;
            int symesTotal = 0, partialFootTotal = 0;
            Iterator iter = assistiveDevicesProthosisList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                shoulderTotal = shoulderTotal + totalDTO.getShoulder();
                aboveElbowTotal = aboveElbowTotal + totalDTO.getAboveElbow();
                elbowDisarticulationTotal = elbowDisarticulationTotal + totalDTO.getElbowDisarticulation();
                belowElbowTotal = belowElbowTotal + totalDTO.getBelowElbow();
                wristDisarticulationTotal = wristDisarticulationTotal + totalDTO.getWristDisarticulation();
                handTotal = handTotal + totalDTO.getHand();
                cosmeticFingerTotal = cosmeticFingerTotal + totalDTO.getCosmeticFinger();
                hipDisarticulationTotal = hipDisarticulationTotal + totalDTO.getHipDisarticulation();
                aboveKneeTotal = aboveKneeTotal + totalDTO.getAboveKnee();
                kneeDisarticulationTotal = kneeDisarticulationTotal + totalDTO.getKneeDisarticulation();
                belowKneeTotal = belowKneeTotal + totalDTO.getBelowKnee();
                symesTotal = symesTotal + totalDTO.getSymes();
                partialFootTotal = partialFootTotal + totalDTO.getPartialFoot();
                total = total + totalDTO.getTotal();
            }
%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        

        <script >

            function removeLists(start,end)
            {
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            }

            function createDistrictObject(list)
            {
                val=list;
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
                    if(val == "onload"){
                        selectedDistrictDelaultValues();
                        createMandalObject('onload');
                        disabileDistrict();
                        document.getElementById('reportcategory').value = <%=category%>;
                        disabileSubCategory();
                    }
                }

            }

            function districtList()
            {
                var disid = document.getElementById("district_id").value;
                if(disid == ""){
                    removeLists(2,6);
                }else{
                    createMandalObject();
                }

            }

            function  createMandalObject(list)
            {
                val=list;
                x=GetXmlHttpObject()
                x.onreadystatechange=getMandalDetails;
                var distid=document.getElementById("district_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&territory=2";
                x.open("GET",url,true)
                x.send();
            }

            function getMandalDetails()
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
                    if(val == "onload"){
                        selectedMandalDelaultValues();
                        createVillageObject('onload');
                        disabileMandal();
                        disabileSubCategory();
                    }

                }
            }

            function mandalList(){
                var manid=document.getElementById("mandal_id").value;
                if(manid==""){
                    removeLists(4,6);
                }else{
                    createVillageObject();
                }
            }

            function  createVillageObject(list)
            {
                val=list;
                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                distid=document.getElementById("district_id").value;
                if(distid == "" && document.getElementById("districtid") != null){
                    distid = document.getElementById("districtid").value;
                }
                var mandid=document.getElementById("mandal_id").value;
                var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=5";
                x.open("GET",url,true)
                x.send();
            }

            function getVillageDetails()
            {
                var rs1,rs2;
                removeall("village_id");
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
                        addoption(rs1,rs2,"village_id");
                        z++;
                    }
                    if(val == "onload"){
                        selectedVillageDelaultValues();
                        disabileSubCategory();
                    }
                }
            }


            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

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
                    if (validate_required(typeofdisability,"Select Type Of Disability")==false)
                    {
                        typeofdisability.focus();
                        return false
                    }
                    if (validate_required(reportcategory,"Select Category")==false)
                    {
                        reportcategory.focus();
                        return false
                    }
                    if (validate_required(fromdate,"From Date must be selected!")==false)
                    {
                        fromdate.focus();
                        return false
                    }
                    if (validate_required(todate,"To Date must be selected!")==false)
                    {
                        todate.focus();
                        return false
                    }
                    var fromDate= document.forms[0].fromdate.value;
                    var toDate= document.forms[0].todate.value;
                    var yye=fromDate.substr(6,4);
                    var mme=fromDate.substr(3,2);
                    var dde=fromDate.substr(0,2);
                    var yyb=toDate.substr(6,4);
                    var mmb=toDate.substr(3,2);
                    var ddb=toDate.substr(0,2);
                    var dob = new  Date();
                    var etd = new  Date();
                    var today=new Date();
                    etd.setFullYear(yye,mme-1,dde);
                    dob.setFullYear(yyb,mmb-1,ddb)
                    var y1=today.getYear();
                    var y2= dob.getYear();
                    if (today < etd)
                    {
                        alert("From date is after Todays Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date is after Todays Date");
                        document.forms[0].todate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    var disability=document.getElementById("typeofdisability").value;
                    var category=document.getElementById("reportcategory").value;
                    if((disability == 1 || disability == 6) && category == 2)
                    {
                        if (validate_required(reportSubcategory,"Selecte Sub Category")==false)
                        {
                            reportSubcategory.focus();
                            return false
                        }
                    }
                }
            }

            function disabileSubCategory()
            {
                var disability=document.getElementById("typeofdisability").value;
                var category=document.getElementById("reportcategory").value;

                if((disability == 1 || disability == 6) && category == 2)
                {
                    document.getElementById("divsubcategory").style.visibility = "Visible";
                    document.getElementById("divsubcategory").style.display = "Inline";
                }else{
                    document.getElementById("divsubcategory").style.visibility = "hidden";
                    document.getElementById("divsubcategory").style.display = "none";

                }


            }

            function selectedDistrictDelaultValues()
            {
                var a = "<%=district_id%>";
                var distid = document.getElementById("district_id");
                for(var i = 0; i<distid.options.length; i++){
                    var b = document.getElementById("district_id").options(i).value;
                    if(a == b){
                        document.getElementById("district_id").value = b;
                        break;
                    }
                }

            }

            function selectedMandalDelaultValues()
            {
                var a = "<%=mandal_id%>";
                var mandalid = document.getElementById("mandal_id");
                for(var i = 0; i<mandalid.options.length; i++){
                    var b = document.getElementById("mandal_id").options(i).value;
                    if(a == b){
                        document.getElementById("mandal_id").value = b;
                        break;
                    }
                }
            }
            function selectedVillageDelaultValues()
            {
                var v = "<%=village_id%>";
                var villgid = document.getElementById("village_id");
                for(var i = 0; i<villgid.options.length; i++){
                    var vo = document.getElementById("village_id").options(i).value;
                    if(v == vo){
                        document.getElementById("village_id").value = vo;
                        break;
                    }
                }

            }


            function disabileDistrict()
            {

                var x=document.getElementById("district_id").value;
                if(x == 0)
                {
                    document.forms[0].mandal_id.disabled = true;
                    document.forms[0].village_id.disabled = true;

                }
                else
                {
                    document.forms[0].mandal_id.disabled = false;
                    document.forms[0].village_id.disabled = true;
                    document.forms[0].village_id.value="0";
                }


            }

            function disabileMandal()
            {
                var x=document.getElementById("mandal_id").value;
                if(x == 0)
                {
                    document.forms[0].village_id.value="0";
                    document.forms[0].village_id.disabled = true;

                }
                else
                {
                    document.forms[0].village_id.disabled = false;
                    document.forms[0].village_id.value="0";
                }

            }


            function droupDownOptions()
            {

                var z=document.getElementById("typeofdisability").value;
                var select=document.getElementById("reportcategory");
                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == "" || z == 1 || z == 2 || z == 3 || z == 4 || z == 6){
                    var newOption1 = document.createElement('<option value="1">');
                    document.all.reportcategory.options.add(newOption1);
                    newOption1.innerText = "Medical Intervention";
                    var newOption2 = document.createElement('<option value="2">');
                    document.all.reportcategory.options.add(newOption2);
                    newOption2.innerText = "Assistive Devices";
                    var newOption3 = document.createElement('<option value="3">');
                    document.all.reportcategory.options.add(newOption3);
                    newOption3.innerText = "Other Needs";

                }

                if(z == 5){
                    var newOption1 = document.createElement('<option value="1">');
                    document.all.reportcategory.options.add(newOption1);
                    newOption1.innerText = "Medical Intervention";
                    var newOption2 = document.createElement('<option value="3">');
                    document.all.reportcategory.options.add(newOption2);
                    newOption2.innerText = "Other Needs";

                }

            }

            function droupDownSubCategoryOptions()
            {

                var z=document.getElementById("typeofdisability").value;
                var select=document.getElementById("reportSubcategory");
                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == 1 ){
                    var newOption1 = document.createElement('<option value="1">');
                    document.all.reportSubcategory.options.add(newOption1);
                    newOption1.innerText = "Prosthesis";
                    var newOption2 = document.createElement('<option value="2">');
                    document.all.reportSubcategory.options.add(newOption2);
                    newOption2.innerText = "Orthosis";
                    var newOption3 = document.createElement('<option value="3">');
                    document.all.reportSubcategory.options.add(newOption3);
                    newOption3.innerText = "Mobility Aids";
                    var newOption4 = document.createElement('<option value="4">');
                    document.all.reportSubcategory.options.add(newOption4);
                    newOption4.innerText = "Walking Aids";
                    var newOption5 = document.createElement('<option value="5">');
                    document.all.reportSubcategory.options.add(newOption5);
                    newOption5.innerText = "ADL Equipments";
                }


            }
            function onloadDroupDownSubCategoryOptions()
            {

                var z=document.getElementById("typeofdisability").value;
                var select=document.getElementById("reportSubcategory");
                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == 1 ){
                    var newOption1 = document.createElement('<option value="1">');
                    document.all.reportSubcategory.options.add(newOption1);
                    newOption1.innerText = "Prosthesis";
                    var newOption2 = document.createElement('<option value="2">');
                    document.all.reportSubcategory.options.add(newOption2);
                    newOption2.innerText = "Orthosis";
                    var newOption3 = document.createElement('<option value="3">');
                    document.all.reportSubcategory.options.add(newOption3);
                    newOption3.innerText = "Mobility Aids";
                    var newOption4 = document.createElement('<option value="4">');
                    document.all.reportSubcategory.options.add(newOption4);
                    newOption4.innerText = "Walking Aids";
                    var newOption5 = document.createElement('<option value="5">');
                    document.all.reportSubcategory.options.add(newOption5);
                    newOption5.innerText = "ADL Equipments";
                }

                document.getElementById('reportSubcategory').value = <%=subCategory%>;

            }
        </script>
    </head>

    <body onload="createDistrictObject('onload'),droupDownOptions(),onloadDroupDownSubCategoryOptions()">

    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <html:form action="functionalNeed.do?getFunctionalNeedReport=getFunctionalNeedReport" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <p>Select Report Details</p>
            <tr>
                <td height="445" align="center" valign="top">
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="grid" align="center">
                        <tr>
                            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                <table  align="center" cellspacing="1" cellpadding="5" width="100%">
                                    <tr>
                                        <td valign="middle" class="label" width="16%">District</td>
                                        <td align="left" valign="middle" width="16%">
                                            <html:select styleId="1" property="district_id" onchange="districtList(this.value),disabileDistrict()" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td class="label" valign="middle" width="8%">Mandal</td>
                                        <td align="left" valign="middle" width="25%">
                                            <html:select styleId="2" property="mandal_id" onchange="mandalList(),disabileMandal()" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                            </html:select>

                                        </td>
                                        <td class="label" valign="middle" width="8%">Village</td>
                                        <td align="left" valign="middle" width="26%">
                                            <html:select styleId="3" property="village_id" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                            </html:select>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label" valign="middle">Type of Disability<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="5" property="typeofdisability" onchange="disabileSubCategory(),droupDownOptions(),droupDownSubCategoryOptions()" style="height:25px;">
                                                <html:option value="">  --SELECT--   </html:option>
                                                <html:option value="1">Locomotor</html:option>
                                                <html:option value="2">Visual</html:option>
                                                <html:option value="3">Hearing</html:option>
                                                <html:option value="4">Mental Retardation</html:option>
                                                <html:option value="5">Mental Illness</html:option>
                                            </html:select>
                                        </td>
                                        <td class="label" valign="middle">Category<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">

                                            <html:select styleId="6" property="reportcategory" onchange="disabileSubCategory(),droupDownSubCategoryOptions()" style="height:25px;">
                                                <html:option  value="">  --SELECT--   </html:option>
                                            </html:select>
                                        </td>
                                        <td class="label" valign="middle" colspan="2">
                                            <div id="divsubcategory" style="visibility:hidden;display:none">
                                                Sub Category<font color="red"><b>*</b></font>
                                                <html:select styleId="7" property="reportSubcategory" style="height:25px;">
                                                    <html:option  value="">  --SELECT--   </html:option>
                                                </html:select>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">&nbsp;</td>
                                        <td class="label" colspan="2">From Date<font color="red"><b>*</b></font>
                                            <html:text property="fromdate" styleId="10" readonly="true" size="12"/>
                                            <a  href="javascript:show_calendar('forms[0].fromdate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>
                                        <td class="label" colspan="2">To Date<font color="red"><b>*</b></font>
                                            <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                            <a  href="javascript:show_calendar('forms[0].todate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" align="center"><input type="Submit" name="card" value="Submit" class="button"></td>
                                    </tr>
                                </table>
                                <%
                                            session.removeAttribute("medicalInterventionOHList");
                                            session.removeAttribute("assistiveDevicesProthosisList");
                                %>
                            </td>
                        </tr>
                    </table><br>
                    <logic:present name="prothosisList" scope="request">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                            <tr><th class="formhdbg" align="center" colspan="16">Assistive Devices Prosthesis Report</th></tr>
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <% if (village_id != null && !village_id.equals("0")) {%>
                                <th class="formhdbg" rowspan=2 align="center">Habitation</th>
                                <% } else if (village_id != null) {%>
                                <th class="formhdbg" rowspan="2" align="center">Village</th>
                                <% } else if (mandal_id != null) {%>
                                <th class="formhdbg" rowspan="2" align="center">Mandal</th>
                                <% } else if (district_id != null) {%>
                                <th class="formhdbg" rowspan="2" align="center">District</th>
                                <% }%>
                                <th align="center" class="formhdbg" colspan="7">Prosthesis for Upper Extremity</th>
                                <th align="center" class="formhdbg" colspan="6">Prosthesis for Lower Extremity</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Shoulder</th>
                                <th class="formhdbg" align="center">Above Elbow</th>
                                <th class="formhdbg" align="center">Elbow Disarticulation</th>
                                <th class="formhdbg" align="center">Below Elbow</th>
                                <th class="formhdbg" align="center">Wrist Disarticulation</th>
                                <th class="formhdbg" align="center">Hand</th>
                                <th class="formhdbg" align="center">Cosmetic Finger</th>
                                <th class="formhdbg" align="center">Hip Disarticulation</th>
                                <th class="formhdbg" align="center">Above Knee</th>
                                <th class="formhdbg" align="center">Knee Disarticulation</th>
                                <th class="formhdbg" align="center">Below Knee</th>
                                <th class="formhdbg" align="center">Syme's</th>
                                <th class="formhdbg" align="center">Partial Foot</th>
                            </tr>
                            <% int i = 0;%>
                            <logic:iterate id="modify" name="prothosisList" scope="request">
                                <%if (i % 2 == 1) {%>
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg"><bean:write name="modify" property="districtName"/>
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg"><bean:write name="modify" property="mandalName"/>
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg"><bean:write name="modify" property="villageName"/>
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td class="formbg"><bean:write name="modify" property="habitationName"/>
                                        </td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ShoulderProsthesis&cValue=Shoulder Prosthesis&count=<bean:write name="modify" property="shoulder"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="shoulder"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveElbowProsthesis&cValue=Above Elbow  Prosthesis&count=<bean:write name="modify" property="aboveElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="aboveElbow"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ElbowDisarticulationProsthesis&cValue=Elbow Disarticulation Prosthesis&count=<bean:write name="modify" property="elbowDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="elbowDisarticulation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowElbowProsthesis&cValue=Below Elbow  Prosthesis&count=<bean:write name="modify" property="belowElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="belowElbow"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WristDisarticulationProsthesis&cValue=Wrist Disarticulation Prosthesis&count=<bean:write name="modify" property="wristDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="wristDisarticulation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HandProsthesis&cValue=Hand Prosthesis&count=<bean:write name="modify" property="hand"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="hand"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CosmeticFingerProsthesis&cValue=Cosmetic Finger Prosthesis&count=<bean:write name="modify" property="cosmeticFinger"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="cosmeticFinger"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HipDisarticulationProsthesis&cValue=Hip Disarticulation Prosthesis&count=<bean:write name="modify" property="hipDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="hipDisarticulation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveKneeProsthesis&cValue=Above Knee Prosthesis&count=<bean:write name="modify" property="aboveKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="aboveKnee"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=KneeDisarticulationProsthesis&cValue=Knee Disarticulation Prosthesis&count=<bean:write name="modify" property="kneeDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="kneeDisarticulation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowKneeProsthesis&cValue=Below Knee Prosthesis&count=<bean:write name="modify" property="belowKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="belowKnee"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SymesProsthesis&cValue=Symes Prosthesis&count=<bean:write name="modify" property="symes"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="symes"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=PartialFootProsthesis&cValue=Partial Foot Prosthesis&count=<bean:write name="modify" property="partialFoot"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="partialFoot"/></a></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>
                                <%} else {%>
                                <tr>
                                    <td  align="center"  class="formaltbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formaltbg"><bean:write name="modify" property="districtName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formaltbg"><bean:write name="modify" property="mandalName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formaltbg"><bean:write name="modify" property="villageName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formaltbg"><bean:write name="modify" property="habitationName"/></td>
                                    </logic:notEmpty>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ShoulderProsthesis&cValue=Shoulder Prosthesis&count=<bean:write name="modify" property="shoulder"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="shoulder"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveElbowProsthesis&cValue=Above Elbow  Prosthesis&count=<bean:write name="modify" property="aboveElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="aboveElbow"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ElbowDisarticulationProsthesis&cValue=Elbow Disarticulation Prosthesis&count=<bean:write name="modify" property="elbowDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="elbowDisarticulation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowElbowProsthesis&cValue=Below Elbow  Prosthesis&count=<bean:write name="modify" property="belowElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="belowElbow"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WristDisarticulationProsthesis&cValue=Wrist Disarticulation Prosthesis&count=<bean:write name="modify" property="wristDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="wristDisarticulation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HandProsthesis&cValue=Hand Prosthesis&count=<bean:write name="modify" property="hand"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="hand"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CosmeticFingerProsthesis&cValue=Cosmetic Finger Prosthesis&count=<bean:write name="modify" property="cosmeticFinger"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="cosmeticFinger"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HipDisarticulationProsthesis&cValue=Hip Disarticulation Prosthesis&count=<bean:write name="modify" property="hipDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="hipDisarticulation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveKneeProsthesis&cValue=Above Knee Prosthesis&count=<bean:write name="modify" property="aboveKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="aboveKnee"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=KneeDisarticulationProsthesis&cValue=Knee Disarticulation Prosthesis&count=<bean:write name="modify" property="kneeDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="kneeDisarticulation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowKneeProsthesis&cValue=Below Knee Prosthesis&count=<bean:write name="modify" property="belowKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="belowKnee"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SymesProsthesis&cValue=Symes Prosthesis&count=<bean:write name="modify" property="symes"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="symes"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=PartialFootProsthesis&cValue=Partial Foot Prosthesis&count=<bean:write name="modify" property="partialFoot"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="partialFoot"/></a></td>
                                    <td class="formaltbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>
                                <%}%>
                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><b><%=shoulderTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=aboveElbowTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=belowElbowTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=wristDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=handTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=cosmeticFingerTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=hipDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=aboveKneeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=kneeDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=belowKneeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=symesTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=partialFootTotal%></b></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table><br>
                        <table align="center">
                            <%
                                        session.setAttribute("assistiveDevicesProthosisList", assistiveDevicesProthosisList);
                            %>
                            <a href="assistiveDevicesProthosisExcel.xls?fdate=<%=fromdate%>&todate=<%=todate%>&dName=<%=dName%>&mName=<%=mName%>&vName=<%=vName%>&hName=<%=hName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&vID=<%=village_id%>&hID=<%=habitation_id%>" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            <a href="assistiveDevicesProthosisPrint.xls?fdate=<%=fromdate%>&todate=<%=todate%>&dName=<%=dName%>&mName=<%=mName%>&vName=<%=vName%>&hName=<%=hName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&vID=<%=village_id%>&hID=<%=habitation_id%>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </table>
                    </logic:present>
                </td>
            </tr>
        </table>
    </html:form>
</body>
</html:html>

