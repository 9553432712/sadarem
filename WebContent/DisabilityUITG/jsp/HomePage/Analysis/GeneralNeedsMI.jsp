<%-- 
    Document   : GeneralNeedsMI
    Created on : Jan 6, 2011, 8:52:45 AM
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
            ArrayList miGeneralNeedsList = new ArrayList();
            miGeneralNeedsList = (ArrayList) request.getAttribute("miGeneralNeedsList");

            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0, managerToTakeCarePropertiesTotal = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0;
            Iterator iter = miGeneralNeedsList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                individualTotal = individualTotal + totalDTO.getInclusiveEducation();
                familyTotal = familyTotal + totalDTO.getFamily();
                managerToTakeCarePropertiesTotal = managerToTakeCarePropertiesTotal + totalDTO.getManagerToTakeCareProperties();
                otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
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

                    }
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

        </script>
    </head>

    <body onload="createDistrictObject('onload'),disabileDistrict()">

    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="generalNeeds.do?getGeneralNeedReport=getGeneralNeedReport" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
             <p>Select General Needs Report Details</p>
            <tr>
                <td height="445" align="center" valign="top">
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <input type="hidden" name="habitationName"/>
                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                      
                        <tr>
                            <td height="40" align="left" valign="middle">
                                <table  align="center" cellspacing="1" cellpadding="5" id="grid"  width="100%">
                                    <tr>
                                        <td valign="middle" class="label" width="16%">District</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="1" property="district_id" onchange="districtList(this.value),disabileDistrict()" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td class="label" valign="middle" width="8%">Mandal</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="2" property="mandal_id" onchange="mandalList(),disabileMandal()" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                            </html:select>

                                        </td>
                                        <td class="label" valign="middle" width="8%">Village</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="3" property="village_id" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                            </html:select>

                                        </td>
                                    </tr>
                                    <tr>

                                        <td class="label" valign="middle">Type of Disability<font color="red"><b>*</b></font></td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="5" property="typeofdisability"  style="height:25px;">
                                                <html:option value="">  --SELECT--   </html:option>
                                                <html:option value="1">Locomotor</html:option>
                                                <html:option value="2">Visual</html:option>
                                                <html:option value="3">Hearing</html:option>
                                                <html:option value="4">Mental Retardation</html:option>
                                                <html:option value="5">Mental Illness</html:option>
                                                <html:option value="6">Multiple Disability</html:option>
                                            </html:select>
                                        </td>

                                        <td class="label" colspan="2">From Date<font color="red"><b>*</b></font>
                                            <html:text property="fromdate" readonly="true" size="12"/>
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
                    <logic:present name="miGeneralNeedsList" scope="request">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="90%">
                            <tr><th class="formhdbg" align="center" colspan="13">Mental Illness General Needs Report</th></tr>
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
                                <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                <th align="center" class="formhdbg" rowspan="2">Manager to Take Care Properties</th>
                                <th align="center" class="formhdbg" rowspan="2">Other General Needs</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Early Education Service(<5Yrs)</th>
                                <th class="formhdbg" align="center">Home Based</th>
                                <th class="formhdbg" align="center">Special Education</th>
                                <th class="formhdbg" align="center">Inclusive Education</th>
                                <th class="formhdbg" align="center">Employment in public/ Pvt.Sector</th>
                                <th class="formhdbg" align="center">Self Employment</th>
                                <th class="formhdbg" align="center">Individual</th>
                                <th class="formhdbg" align="center">Family</th>
                            </tr>
                            <% int i = 0;%>
                            <logic:iterate id="modify" name="miGeneralNeedsList" scope="request">
                                <%if (i % 2 == 1) {%>
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg"><bean:write name="modify" property="districtName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg"><bean:write name="modify" property="mandalName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg"><bean:write name="modify" property="villageName"/></td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg"><bean:write name="modify" property="habitationName"/></td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="earlyEducationService"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="homeBased"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="specialEducation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="selfEmployment"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="individual"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="family"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ManagerProperties&cValue=Manager To Take Care&tN=3&count=<bean:write name="modify" property="managerToTakeCareProperties"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="managerToTakeCareProperties"/></a></td>
                                    <td class="formbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
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
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="earlyEducationService"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="homeBased"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="specialEducation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="selfEmployment"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="individual"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="family"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ManagerProperties&cValue=Manager To Take Care&tN=3&count=<bean:write name="modify" property="managerToTakeCareProperties"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="managerToTakeCareProperties"/></a></td>
                                    <td class="formaltbg" align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
                                    <td class="formaltbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>
                                <%}%>
                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><%=earlyEducationServiceTotal %></td>
                                <td class="formhdbg" align="center"><%=homeBasedTotal %></td>
                                <td class="formhdbg" align="center"><%=specialEducationTotal %></td>
                                <td class="formhdbg" align="center"><%=inclusiveEducationTotal %></td>
                                <td class="formhdbg" align="center"><%=employmentPublicPvtSectorTotal %></td>
                                <td class="formhdbg" align="center"><%=selfEmploymentTotal %></td>
                                <td class="formhdbg" align="center"><%=individualTotal %></td>
                                <td class="formhdbg" align="center"><%=familyTotal %></td>
                                <td class="formhdbg" align="center"><%=managerToTakeCarePropertiesTotal %></td>
                                <td class="formhdbg" align="center"><%=otherGeneralNeedsTotal %></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table><br>
                        <table align="center">
                            <%
                                        session.setAttribute("miGeneralNeedsList", miGeneralNeedsList);
                            %>
                            <a href="MIGeneralNeedsExcel.xls?fdate=<%=fromdate%>&todate=<%=todate%>&dName=<%=dName%>&mName=<%=mName%>&vName=<%=vName%>&hName=<%=hName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&vID=<%=village_id%>&hID=<%=habitation_id%>" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            <a href="MIGeneralNeedsPrint.xls?fdate=<%=fromdate%>&todate=<%=todate%>&dName=<%=dName%>&mName=<%=mName%>&vName=<%=vName%>&hName=<%=hName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&vID=<%=village_id%>&hID=<%=habitation_id%>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>

                        </table>
                    </logic:present>
                </td>
            </tr>
        </table>
    </html:form>
</body>
</html:html>
