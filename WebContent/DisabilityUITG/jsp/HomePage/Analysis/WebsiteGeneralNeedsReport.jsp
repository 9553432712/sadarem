<%-- 
    Document   : WebsiteGeneralNeedsReport
    Created on : Jan 5, 2011, 2:56:19 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page session="true"%>
<%          String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String hName = (String) request.getParameter("habitationName");
            String typeOfDisability = "" + request.getAttribute("typeofdisability");
            Iterator iter = null;
            ArrayList commonGeneralNeedsList = new ArrayList();
            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0;
            if (request.getAttribute("commonGeneralNeedsList") != null) {
                commonGeneralNeedsList = (ArrayList) request.getAttribute("commonGeneralNeedsList");
                iter = commonGeneralNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                    homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                    specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                    inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                    employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                    selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                    individualTotal = individualTotal + totalDTO.getIndividual();
                    familyTotal = familyTotal + totalDTO.getFamily();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>



<%
            ArrayList mrGeneralNeedsList = new ArrayList();
            mrGeneralNeedsList = (ArrayList) request.getAttribute("mrGeneralNeedsList");

            int legalGurdianTotal = 0;
            if (request.getAttribute("mrGeneralNeedsList") != null) {
                iter = mrGeneralNeedsList.iterator();
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
                    legalGurdianTotal = legalGurdianTotal + totalDTO.getLegalGurdian();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>

<%
            ArrayList miGeneralNeedsList = new ArrayList();
            miGeneralNeedsList = (ArrayList) request.getAttribute("miGeneralNeedsList");

            int managerToTakeCarePropertiesTotal = 0;
            if (request.getAttribute("miGeneralNeedsList") != null) {
                iter = miGeneralNeedsList.iterator();
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
            }
%>


<%
            ArrayList multipleGeneralNeedsList = new ArrayList();
            multipleGeneralNeedsList = (ArrayList) request.getAttribute("multipleGeneralNeedsList");

            if (request.getAttribute("multipleGeneralNeedsList") != null) {
                iter = multipleGeneralNeedsList.iterator();
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
                    legalGurdianTotal = legalGurdianTotal + totalDTO.getLegalGurdian();
                    managerToTakeCarePropertiesTotal = managerToTakeCarePropertiesTotal + totalDTO.getManagerToTakeCareProperties();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./DisabilityUITG/js/Validation.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <title>SADAREM</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script >
      

            function removeLists(start,end){
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            }

            function createDistrictObject()
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
                var disid =  document.forms[0].district_id.value;
                if(disid == ""){
                    removeLists(2,6);
                }else{
                    createMandalObject();
                }
            }

            function  createMandalObject()
            {
                x=GetXmlHttpObject()
                x.onreadystatechange=getMandalDetails;
                var distid= document.forms[0].district_id.value;
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
                }
            }

            function mandalList(){
                var manid= document.forms[0].mandal_id.value;
                if(manid==""){
                    removeLists(4,6);
                }else{
                    createVillageObject();
                }
            }

            function  createVillageObject()
            {
                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                distid= document.forms[0].district_id.value;
                if(distid == ""){
                    distid = document.forms[0].district_id.value;
                }
                var mandid= document.forms[0].mandal_id.value;
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

                try{
                    document.getElementById(name).add(opt);
                }catch(ex)
                {
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else  if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else  if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }


                }
            }
            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                } else if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    } else if(name=="district_id") {
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
                var x= document.forms[0].district_id.value;
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
                var x= document.forms[0].mandal_id.value;
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

            

            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }
        </script>
    </head>

    <body>

    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form action="generalNeedsReport.do?mode=getOtherReports" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

        <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
            <tr>
                <td height="445" align="center"  valign="top">
                    <input type="hidden" name="mode"/>
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <input type="hidden" name="habitationName"/>


                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center"  class="inputform">

                        <tr>
                            <td height="40" align="left" valign="middle">
                                <table border="1" cellspacing="0" cellpadding="0" width="100%" align="center"  class="inputform">
                                    <tr><th colspan="6">A2.2 General Needs Report</th></tr>
                                    <tr>
                                        <td>
                                            <table  align="center" cellspacing="0" cellpadding="0" class="inputform">
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
                                                    <td class="label">From Date<font color="red"><b>*</b></font>
                                                        <html:text property="fromdate" readonly="true" size="12"/>
                                                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                                                            onmouseover="window.status='Date Picker';return true;"
                                                            onmouseout="window.status='';return true;">
                                                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                                    </td>
                                                    <td class="label">To Date<font color="red"><b>*</b></font>
                                                        <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                                        <a  href="javascript:show_calendar('forms[0].todate');"
                                                            onmouseover="window.status='Date Picker';return true;"
                                                            onmouseout="window.status='';return true;">
                                                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="8" align="center" style="height:3px"><input type="Submit" name="card" value="Submit" class="button"></th>
                                    </tr>
                                </table>




                                <%
                                            String msg = (String) request.getAttribute("msg");

                                %>
                                <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                                <% int i = 0;%>
                            </td>
                        </tr><br/>

                        <tr>
                            <td align="center">
                                <logic:present name="commonGeneralNeedsList" scope="request">
                                  
                                    <table align="center">
                                        <tr>
                                            <td>
                                                General Needs Report
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <logic:present name="level">${level}</logic:present>
                                            </td>
                                        </tr>
                                    </table>
                                    <table align="center">
                                        <tr>
                                            <td>

                                                <table align="right">
                                                    <tr>
                                                        <td style="text-align: left">
                                                            <logic:present name="back">${back}</logic:present>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td>
                                                <table align="center">
                                                    <tr>

                                                        <td style="text-align: center">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>
                                                       
                                                    </tr>
                                                </table>
                                            </td>
                                        <tr>



                                        </tr>
                                    </table>

                                    <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                        <tr>
                                            <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                            <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                            <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                            <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                            <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
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

                                        <logic:iterate id="modify" name="commonGeneralNeedsList" scope="request">
                                            <tr>
                                                <td  style="text-align: center" class="formbg" ><%=++i%></td>
                                                <logic:notEmpty name="modify" property="districtName">
                                                    <td  class="formbg" align="left">
                                                        ${modify.districtName}
                                                    </td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="mandalName">
                                                    <td  class="formbg" align="left">${modify.mandalName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="villageName">
                                                    <td  class="formbg" align="left">${modify.villageName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="habitationName">
                                                    <td  class="formbg" align="left">${modify.habitationName}</td>
                                                </logic:notEmpty>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="earlyEducationService"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="homeBased"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="specialEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="selfEmployment"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="individual"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="family"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
                                                <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                            </tr>

                                        </logic:iterate>
                                        <tr>
                                            <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                            <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%= total%></th>
                                        </tr>
                                    </table><br>
                                    <table align="center" width="90%">
                                        <tr>
                                            <td style="text-align: center">
                                                <a href="generalNeedsReport.xls?mode=excelGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                <a href="generalNeedsReport.do?mode=printGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>
                                            </td>
                                        </tr>


                                    </table>
                                </logic:present>


                                <logic:present name="mrGeneralNeedsList" scope="request">
                                    <p>General Needs Report</p>
                                    <p>
                                        <logic:present name="level">${level}</logic:present></p>

                                    <table align="center">
                                        <tr>
                                            <td style="text-align: left">
                                                <logic:present name="back">${back}</logic:present>
                                            </td>

                                            <td style="text-align: right">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>
                                        </tr>
                                    </table>
                                    <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                        <tr>
                                            <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                            <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>

                                            <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                            <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                            <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                            <th align="center" class="formhdbg" rowspan="2">Legal Gurdian</th>
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
                                        <% i = 0;%>
                                        <logic:iterate id="modify" name="mrGeneralNeedsList" scope="request">
                                            <tr>
                                                <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                                <logic:notEmpty name="modify" property="districtName">
                                                    <td  class="formbg" align="left">
                                                        ${modify.districtName}
                                                    </td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="mandalName">
                                                    <td  class="formbg" align="left">${modify.mandalName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="villageName">
                                                    <td  class="formbg" align="left">${modify.villageName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="habitationName">
                                                    <td  class="formbg" align="left">${modify.habitationName}</td>
                                                </logic:notEmpty>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="earlyEducationService"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="homeBased"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="specialEducation"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="selfEmployment"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="individual"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="family"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LegalGuardian&cValue=Legal Guardian&tN=2&count=<bean:write name="modify" property="legalGurdian"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="legalGurdian"/></a></td>
                                                <td class="formbg" style="text-align: center" >
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
                                                <td class="formbg" style="text-align: center" ><bean:write name="modify" property="total"/></td>
                                            </tr>

                                        </logic:iterate>
                                        <tr>
                                            <th class="formhdbg" colspan="2" style="text-align: center" >Total</th>
                                            <th class="formhdbg" style="text-align: center" ><%=earlyEducationServiceTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                            <th class="formhdbg" style="text-align: center" ><%=selfEmploymentTotal%></th>
                                            <th class="formhdbg" style="text-align: center" ><%=individualTotal%></th>
                                            <th class="formhdbg" style="text-align: center" ><%=familyTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=legalGurdianTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%= total%></th>
                                        </tr>
                                    </table><br>
                                    <table align="center" width="90%">
                                        <tr>
                                            <td style="text-align: center">
                                                <a href="generalNeedsReport.xls?mode=excelGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                <a href="generalNeedsReport.do?mode=printGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>
                                            </td>
                                        </tr>


                                    </table>
                                </logic:present>

                                <logic:present name="miGeneralNeedsList" scope="request">
                                    <p>General Needs Report</p>
                                    <p>
                                        <logic:present name="level">${level}</logic:present></p>

                                    <table align="center">
                                        <tr>
                                            <td style="text-align: left">
                                                <logic:present name="back">${back}</logic:present>
                                            </td>

                                            <td style="text-align: right">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>
                                        </tr>
                                    </table>
                                    <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                        <tr>
                                            <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                            <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
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
                                        <% i = 0;%>
                                        <logic:iterate id="modify" name="miGeneralNeedsList" scope="request">

                                            <tr>
                                                <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                                <logic:notEmpty name="modify" property="districtName">
                                                    <td  class="formbg" align="left">
                                                        ${modify.districtName}
                                                    </td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="mandalName">
                                                    <td  class="formbg" align="left">${modify.mandalName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="villageName">
                                                    <td  class="formbg" align="left">${modify.villageName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="habitationName">
                                                    <td  class="formbg" align="left">${modify.habitationName}</td>
                                                </logic:notEmpty>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="earlyEducationService"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="homeBased"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="specialEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="selfEmployment"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="individual"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="family"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ManagerProperties&cValue=Manager To Take Care&tN=3&count=<bean:write name="modify" property="managerToTakeCareProperties"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="managerToTakeCareProperties"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
                                                <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                            </tr>

                                        </logic:iterate>
                                        <tr>
                                            <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                            <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=managerToTakeCarePropertiesTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%= total%></th>
                                        </tr>
                                    </table><br>
                                    <table align="center" width="90%">
                                        <tr>
                                            <td style="text-align: center">
                                                <a href="generalNeedsReport.xls?mode=excelGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                <a href="generalNeedsReport.do?mode=printGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>
                                            </td>
                                        </tr>


                                    </table>
                                </logic:present>


                                <logic:present name="multipleGeneralNeedsList" scope="request">
                                    <p>General Needs Report</p>
                                    <p>
                                        <logic:present name="level">${level}</logic:present></p>

                                    <table align="center">
                                        <tr>
                                            <td style="text-align: left">
                                                <logic:present name="back">${back}</logic:present>
                                            </td>

                                            <td style="text-align: right">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>
                                        </tr>
                                    </table>
                                    <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                        <tr>
                                            <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                            <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                            <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                            <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                            <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                            <th align="center" class="formhdbg" rowspan="2">Legal Gurdian</th>
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
                                        <% i = 0;%>
                                        <logic:iterate id="modify" name="multipleGeneralNeedsList" scope="request">
                                            <tr>
                                                <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                                <logic:notEmpty name="modify" property="districtName">
                                                    <td  class="formbg" align="left">
                                                        ${modify.districtName}
                                                    </td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="mandalName">
                                                    <td  class="formbg" align="left">${modify.mandalName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="villageName">
                                                    <td  class="formbg" align="left">${modify.villageName}</td>
                                                </logic:notEmpty>
                                                <logic:notEmpty name="modify" property="habitationName">
                                                    <td  class="formbg" align="left">${modify.habitationName}</td>
                                                </logic:notEmpty>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EarlyEducation&cValue=Early Education Services&tN=1&count=<bean:write name="modify" property="earlyEducationService"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="earlyEducationService"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Home Based Education&tN=1&count=<bean:write name="modify" property="homeBased"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="homeBased"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Special School&tN=1&count=<bean:write name="modify" property="specialEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="specialEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=1&D=<%=typeOfDisability%>&cName=EducationServices&cValue=Inclusive Education&tN=1&count=<bean:write name="modify" property="inclusiveEducation"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="inclusiveEducation"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For employment in public/ pvt. sector&tN=1&count=<bean:write name="modify" property="employmentPublicPvtSector"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="employmentPublicPvtSector"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=2&D=<%=typeOfDisability%>&cName=VocationalTraining&cValue=For self-employment&tN=1&count=<bean:write name="modify" property="selfEmployment"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="selfEmployment"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Individual&cValue=Individual&tN=1&count=<bean:write name="modify" property="individual"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="individual"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=3&D=<%=typeOfDisability%>&cName=Family&cValue=Family&tN=1&count=<bean:write name="modify" property="family"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="family"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LegalGuardian&cValue=Legal Guardian&tN=2&count=<bean:write name="modify" property="legalGurdian"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="legalGurdian"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ManagerProperties&cValue=Manager To Take Care&tN=3&count=<bean:write name="modify" property="managerToTakeCareProperties"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="managerToTakeCareProperties"/></a></td>
                                                <td class="formbg" style="text-align: center">
                                                    <a href="javascript:void(0);" onclick ="window.open('personalGeneralNeeds.xls?nexxt=start&back=start&getpersonalGeneralNeeds=getpersonalGeneralNeeds&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OtherServices&cValue=1&tN=1&count=<bean:write name="modify" property="otherGeneralNeeds"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                        <bean:write name="modify" property="otherGeneralNeeds"/></a></td>
                                                <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                            </tr>

                                        </logic:iterate>
                                        <tr>
                                            <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                            <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=legalGurdianTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=managerToTakeCarePropertiesTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                            <th class="formhdbg" style="text-align: center"><%= total%></th>
                                        </tr>
                                    </table><br>
                                    <table align="center" width="90%">
                                        <tr>
                                            <td style="text-align: center">
                                                <a href="generalNeedsReport.xls?mode=excelGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                <a href="generalNeedsReport.do?mode=printGeneralNeeds&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a>
                                            </td>
                                        </tr>


                                    </table>
                                </logic:present>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
