<%--
    Document   : pwdsDisabilityPension
    Created on : Oct 1, 2014, 11:52:01 AM
    Author     : 842412<ima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=${encoding}">
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script>
            var typeOfselection="";
            var reportType="";
            <%
                        if (request.getAttribute("typeOfSearchValue") != null) {%>
                            typeOfselection='<%=request.getAttribute("typeOfSearchValue")%>';
            <%}%>
                function insertDetails(){
                    if(document.forms[0].fromdate.value==""){
                        alert("Please Select from date");
                        document.forms[0].fromdate.focus();
                        return false;
                    }
                    if(document.forms[0].todate.value==""){
                        alert("Please Select To date");
                        document.forms[0].todate.focus();
                        return false;
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
                        alert("From date is before Today's Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date is before Today's Date");
                        document.forms[0].todate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if((document.getElementById('5').checked==true) && (document.forms[0].districtID.value=="0")){
                            <%--if((document.getElementById('5').checked==true) && (document.forms[0].districtID.value=="0") && (document.getElementById('date').style.display="block")){--%>
                        alert("Please Select District.");
                        //document.forms[0].districtID.value="0";
                        return false;

                    }else if((document.getElementById('4').checked==true) && (document.forms[0].district.value=="0")){
                        alert("Please Select District.");
                        //document.forms[0].districtID.value="0";
                        return false;

                    }
                    else{
                        
                        document.forms[0].mode.value='getPwdsPersonalCount';
                        document.forms[0].submit();
                    }
                }

                function changeSelection(id){
                    if(id=='District Wise'){
                        document.getElementById('disability').style.display="none";
                        document.getElementById('district').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.getElementById('date').style.display="none";
                        // document.getElementById("7").value="2012-13" ;
                        document.forms[0].districtid.value="All";
                        document.getElementById("6").style.display="none";
                        typeOfselection="District Wise";
                    }else if(id=='Disability Wise'){
                        document.getElementById('disability').style.display="block";
                        document.getElementById('district').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.getElementById('date').style.display="none";
                        // document.getElementById("7").value="2012-13" ;
                        document.forms[0].disabilityWise.value="All";
                        document.forms[0].districtid.value="All";
                        document.getElementById("6").style.display="none";
                        typeOfselection="Disability Wise";
                    }
                    else if(id=='Medicalboard Wise'){
                       
                        document.getElementById('district').style.display="block";
                        //document.getElementById('campId').style.display="block";
                        document.getElementById('disability').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.getElementById('date').style.display="none";
                        document.forms[0].district.value="0";
                        document.forms[0].campId.value="All";
                        // document.getElementById("7").value="2012-13" ;

                        document.getElementById("6").style.display="none";
                        typeOfselection="Medicalboard Wise";
                    }else if(id=='Date Wise'){
                        document.getElementById('district').style.display="none";
                        document.getElementById('date').style.display="block";
                        document.getElementById('campId').style.display="none";
                        document.getElementById('disability').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.forms[0].districtID.value="0";
                        // document.getElementById("7").value="2012-13" ;

                        document.getElementById("6").style.display="none";
                        typeOfselection="Date Wise";
                    }
                }
                function bodyLoadfunction(){
                    if(typeOfselection=='District Wise'){
                        //document.getElementById('betweenDates').style.display="none";
                        document.getElementById('disability').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.getElementById('date').style.display="none";
                        typeOfselection="District Wise";
                        document.getElementById('2').checked=true;
                    }else if(typeOfselection=='Disability Wise'){
                        //document.getElementById('betweenDates').style.display="none";
                        document.getElementById('disability').style.display="block";
                        document.getElementById('district').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        
                        typeOfselection="Disability Wise";
                        if(document.getElementById('3').checked=true){
                            
                            document.getElementById('disability').style.display="block";
                            document.getElementById('district').style.display="none";
                        }
                        //document.getElementById('3').checked=true;
                    }else if(typeOfselection=='Medicalboard Wise'){
                        //document.getElementById('betweenDates').style.display="none";
                        document.getElementById('district').style.display="block";
                        document.getElementById('date').style.display="none";
                        document.getElementById('getsub').style.display="block";
                        document.getElementById('getsub').style.display="block";
                        //document.getElementById('disability').style.display="none";
                        typeOfselection="Medicalboard Wise";
                        if(document.getElementById('4').checked=true){
                            document.getElementById('district').style.display="block";
                            document.getElementById('campId').style.display="block";
                            document.getElementById('disability').style.display="none";
                        }
                        //document.getElementById('3').checked=true;
                    }else if(typeOfselection=='Date Wise'){
                        //document.getElementById('betweenDates').style.display="none";
                        document.getElementById('district').style.display="none";
                        document.getElementById('date').style.display="block";
                        document.getElementById('getsub').style.display="block";
                        //document.getElementById('disability').style.display="none";
                        typeOfselection="Date Wise";
                        if(document.getElementById('5').checked=true){
                            document.getElementById('district').style.display="none";
                            document.getElementById('date').style.display="block";
                            document.getElementById('campId').style.display="none";
                            document.getElementById('disability').style.display="none";
                        }
                        //document.getElementById('3').checked=true;
                    }
                    document.forms[0].elements["reptTypeSec"].value=typeOfselection;
                }
                function ShowDate()
                {
                    var dt = new Date();
                    var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();

                    document.getElementById(8).value = d;

                }
                function getCamps() {
                    document.forms[0].elements["mode"].value="unspecified";
                    document.forms[0].submit();
                }

        </script>
    </head>
    <body onload="ShowDate(),bodyLoadfunction();">
        <html:form action="/mandalClusterlevelPwd" >
            <html:hidden property="mode"/>
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>
            <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" >
                <tr>
                    <td>
                        <%--<logic:notPresent name="pwdsIndividualCount">
                            <table cellspacing="1" border="0" cellpadding="0" width="96%" align="center" >
                                <tr>
                                    <logic:present name="msg" >
                                        <td>
                                            ${msg}
                                        </td>
                                    </logic:present>
                                </tr>
                            </table>
                        </logic:notPresent>--%>
                        <%--<td id="betweenDates" style="display: none;" width="60%" colspan="4">--%>
                        <table  align="center" cellspacing="1" cellpadding="0" width="100%" border="0" class="inputform">
                            <tr>
                                <th colspan="4">
                                    3.2 Mandal,Cluster level Pwd Personal Details Abstract Report
                                </th>
                            </tr>
                            <tr>
                                <td>From Date<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="fromdate" styleId="9" readonly="true" size="12" />
                                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td>To Date<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].todate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                            </tr>

                            <tr>
                                <th colspan="8">
                                    <html:radio property="typeOfSearch" styleId="2" value="District Wise" onclick="changeSelection(this.value);" style="border:0">District</html:radio>
                                    <html:radio property="typeOfSearch" styleId="3" value="Disability Wise" onclick="changeSelection(this.value);" style="border:0">Disability</html:radio>
                                    <html:radio property="typeOfSearch" styleId="4" value="Medicalboard Wise" onclick="changeSelection(this.value);" style="border:0">Medicalboard</html:radio>
                                    <html:radio property="typeOfSearch" styleId="5" value="Date Wise" onclick="changeSelection(this.value);" style="border:0">Date</html:radio>
                                </th>
                            </tr>
                            <tr>
                                <td id="disability" style="display: none;" width="60%" colspan="4">
                                    <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" id="finance">
                                        <tr>
                                            <td>
                                                District  
                                            </td>
                                            <td >
                                                <html:select property="districtid">
                                                    <html:option value="All">-- All --</html:option>
                                                    <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                                                </html:select>
                                            </td>
                                            <td> Diasbility</td>
                                            <td align="left" >
                                                <html:select styleId="7" property="disabilityWise" style="width:150px;height:25px;font-size:11px;" >
                                                    <html:option value="All">  All  </html:option>

                                                    <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityid" />
                                                </html:select>
                                            </td>
                                            
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <%--<tr>
                                <td id="district" style="display: none;" width="60%" colspan="4">
                                    <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" id="finance">
                                        <tr>
                                            <td> Diasbility<font color="red"><b>*</b></font></td>
                                            <td align="left" >
                                                <html:select styleId="7" property="disabilityWise" style="width:150px;height:25px;font-size:11px;" >
                                                    <html:option value="All">  All  </html:option>

                                                    <html:optionsCollection   property="disabilityList" label="disabilityName" value="disabilityid" />
                                                </html:select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>

                            </tr>--%>

                            <tr  id="district" style="display: none;" bgcolor="white">
                                <td>
                                    District  <font color="red">*</font>
                                </td>
                                <td>
                                    <html:select property="district" onchange="getCamps()">
                                        <html:option value="0">-- Districts --</html:option>
                                        <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                                    </html:select>
                                </td>
                                <td> Camps </td>
                                <td>
                                    <html:select property="campId">
                                        <%--<html:option value="0">-- Camps --</html:option>--%>
                                        <html:option value="All">All</html:option>
                                        <html:optionsCollection property="campList" label="campName" value="campId"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr  id="date" style="display: none;" bgcolor="white">
                                <td>
                                    District  <font color="red">*</font>
                                </td>
                                <td colspan="4">
                                    <html:select property="districtID">
                                        <html:option value="0">-- Districts --</html:option>
                                        <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                                    </html:select>
                                </td>
                            </tr>
                            <%--  </table>--%>
                            <%--     </td>
                             </tr>--%>
                            <tr>
                                <th colspan="4" align="center" id="getsub" style="display: none;">
                                    <input type="button" name="card" value="Submit" class="button" onclick="return insertDetails();"></th>
                            </tr>
                        </table>
                        <br>
                        <div id="6">
                            <logic:present name="pwdsPersonalCount" scope="request">
                                <table  align="center" cellspacing="1" cellpadding="0" width="100%" border="0" class="inputform">
                                    <tr>
                                        <logic:present name="disabilityWise">
                                            <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                <logic:present name="ALL">
                                                    <td colspan="2">
                                                        District::&nbsp;ALL&nbsp;&nbsp; Disability::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:present>
                                                <logic:present name="Districtall">
                                                    <td colspan="2">
                                                        District::&nbsp;ALL&nbsp;&nbsp; Disability::&nbsp;${disabilityName}&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:present>
                                                <logic:present name="Disabilityall">
                                                    <td colspan="2">
                                                        District::&nbsp;${districtname}&nbsp;&nbsp; Disability::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:present>
                                                <logic:present name="Notall">
                                                    <td colspan="2">
                                                        District::&nbsp;${districtname}&nbsp;&nbsp; Disability::&nbsp;${disabilityName}&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:present>
                                                <%--<td colspan="2">
                                                    Disability::&nbsp;${row.disabilityName}&nbsp;&nbsp;District::&nbsp;${row.fromDate}&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                </td>--%>
                                                <%--<logic:iterate id="row"length="1" name="pwdsPersonalCount" >--%>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&districtName=${districtname}&district_ID=${row.district}&disabilityname=${disabilityName}&disabilityid=${row.disabilityID}&typeOfSearch=${row.typeOfSearch}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:iterate>
                                        </logic:present>
                                        <logic:present name="dateWise">
                                            <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                <td colspan="2">
                                                    District::&nbsp;${row.districtname}&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                </td>
                                                <%--<logic:iterate id="row"length="1" name="pwdsPersonalCount" >--%>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&district_ID=${row.districtID}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:iterate>
                                        </logic:present>
                                        <logic:present name="medicalboardWise">
                                            <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                <td colspan="2">
                                                    District::&nbsp;${row.districtName}&nbsp;&nbsp;camp::&nbsp;${row.campname}&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                </td>
                                                <%--<logic:iterate id="row"length="1" name="pwdsPersonalCount" >--%>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&campName=${row.campname}&districtID=${row.districtid}&campid=${row.campid1}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:iterate>
                                        </logic:present>


                                        <logic:present name="districtWise">
                                            <logic:present name="district">
                                                <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                    <td colspan="2">
                                                        District::&nbsp;ALL&nbsp;&nbsp;Mandal::&nbsp;ALL&nbsp;&nbsp;Village::&nbsp;ALL&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                    <%--<logic:iterate id="row"length="1" name="pwdsPersonalCount" >--%>
                                                    <td  style="text-align: right" >
                                                        <a href="mandalClusterlevelPwd.do?mode=excelWriting&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                    </td>
                                                </logic:iterate>
                                            </logic:present>
                                            <logic:present name="mandal">
                                                <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                    <td colspan="2">
                                                        District::&nbsp;${row.districtname}&nbsp;&nbsp;Mandal::&nbsp;ALL&nbsp;&nbsp;Village::&nbsp;ALL&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:iterate>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalName}&districtid=${row.districtid}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:present>
                                            <logic:present name="village">
                                                <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                    <td colspan="2">
                                                        District::&nbsp;${row.districtname}&nbsp;&nbsp;Mandal::&nbsp;${row.mandalname}&nbsp;&nbsp;Village::&nbsp;ALL&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:iterate>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalname}&villageName=${row.villageName}&districtid=${row.districtid}&mandalid=${row.mandalid}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:present>
                                            <logic:present name="habitation">
                                                <logic:iterate id="row"length="1" name="pwdsPersonalCount" >
                                                    <td colspan="2">
                                                        District::&nbsp;${row.districtname}&nbsp;&nbsp;Mandal::&nbsp;${row.mandalname}&nbsp;&nbsp;Village::&nbsp;${row.villagename}&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${row.fromDate}&nbsp;&nbsp;To Date::&nbsp;${row.toDate}
                                                    </td>
                                                </logic:iterate>
                                                <td  style="text-align: right" >
                                                    <a href="mandalClusterlevelPwd.do?mode=excelWriting&habitationName=${row.habitationName}&mandalName=${row.mandalname}&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&villageName=${row.villagename}&districtid=${row.districtid}&mandalid=${row.mandalid}&villageid=${row.villageid}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">
                                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                                </td>
                                            </logic:present>

                                        </tr>
                                        <tr>
                                            <th>
                                                S.No
                                            </th>
                                            <%--<logic:present name="districtWise">--%>
                                            <logic:present name="district">
                                                <th>
                                                    District
                                                </th>
                                            </logic:present>
                                            <logic:present name="mandal">
                                                <th>
                                                    Manadal
                                                </th>
                                            </logic:present>
                                            <logic:present name="village">
                                                <th>
                                                    Village
                                                </th>
                                            </logic:present>
                                            <logic:present name="habitation">
                                                <th>
                                                    Habitation
                                                </th>
                                            </logic:present>
                                            <th>
                                                Only Part-A(Doctor Not Assessed)
                                            </th>
                                        </tr>
                                    </logic:present>
                                    <logic:present name="medicalboardWise" >
                                        <tr>
                                            <th>
                                                S.No
                                            </th>
                                            <th>
                                                Medical Board
                                            </th>
                                            <th>
                                                Only Part-A(Doctor Not Assessed)
                                            </th>
                                        </tr>
                                    </logic:present>
                                    <logic:present name="dateWise" >
                                        <tr>
                                            <th>
                                                S.No
                                            </th>
                                            <th>
                                                Camp Date
                                            </th>
                                            <th>
                                                Only Part-A(Doctor Not Assessed)
                                            </th>
                                        </tr>
                                    </logic:present>
                                    <logic:present name="disabilityWise" >
                                        <tr>
                                            <th>
                                                S.No
                                            </th>
                                            <th>
                                                Disability
                                            </th>
                                            <th>
                                                Only Part-A(Doctor Not Assessed)
                                            </th>

                                        </tr>

                                    </logic:present>

                                    <%int i = 1;%>
                                    <logic:iterate id="row" name="pwdsPersonalCount" >

                                        <tr>
                                            <td style="text-align: center">
                                                <%=i++%>
                                            </td>
                                            <%--<logic:present name="district">
                                            <td>
                                                <a href="mandalClusterlevelPwd.do?mode=getPwdsPersonalCount & districtid=${row.district_id}&fromdate=${row.fromDate}&todate=${row.toDate}">${row.districtName}</a>
                                            </td>
                                            </logic:present>--%>
                                            <logic:present name="medicalboardWise">
                                                <td>
                                                    ${row.campname}
                                                </td>
                                            </logic:present>
                                            <logic:present name="disabilityWise">
                                                <td>
                                                    ${row.disabilityName}
                                                </td>
                                                <%--<td style="text-align: center">
                                                    ${row.count}
                                                </td>--%>
                                                <td style="text-align: center">
                                                    <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&disabilityid=${row.disabilityid}&district_ID=${row.district_id}&disabilityname=${row.disabilityName}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">${row.count}</a>
                                                </td>
                                            </logic:present>

                                            <logic:present name="dateWise">
                                                <td style="text-align: center">
                                                    ${row.campdate}
                                                </td>
                                                <%--<td style="text-align: center">
                                                    ${row.count}
                                                </td>--%>
                                                <td style="text-align: center">
                                                    <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&campDate=${row.campdate}&district_ID=${row.districtID}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">${row.count}</a>
                                                </td>
                                            </logic:present>
                                            <logic:present name="districtWise">

                                                <%--<logic:notPresent name="medicalboardWise">--%>
                                                <logic:present name="district">
                                                    <td>
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsPersonalCount&typeOfSearch=${row.typeOfSearch}&disabilityid=${row.disabilityid}&districtName=${row.districtName}&districtid=${row.district_id}&fromdate=${row.fromDate}&todate=${row.toDate}">${row.districtName}</a>
                                                    </td>
                                                </logic:present>
                                                <%--</logic:notPresent>--%>
                                                <logic:present name="mandal">
                                                    <td>
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsPersonalCount&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalName}&districtid=${row.districtid}&mandalid=${row.mandal_id}&fromdate=${row.fromDate}&todate=${row.toDate}">${row.mandalName}</a>
                                                    </td>
                                                </logic:present>
                                                <logic:present name="village">
                                                    <td>
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsPersonalCount&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalname}&villageName=${row.villageName}&districtid=${row.districtid}&mandalid=${row.mandalid}&villageid=${row.village_id}&fromdate=${row.fromDate}&todate=${row.toDate}">${row.villageName}</a>
                                                    </td>
                                                </logic:present>
                                                <logic:present name="habitation">
                                                    <td>
                                                        ${row.habitationName}
                                                    </td>
                                                </logic:present>
                                                <%--<td style="text-align: center">
                                                    <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&districtName=${row.districtName}&districtid=${row.district_id}&fromdate=${row.fromDate}&todate=${row.toDate}">${row.count}</a>
                                                </td>
                                                --%>

                                                <logic:present name="district">
                                                    <td style="text-align: center">
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&districtid=${row.district_id}&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}" target="_ blank">${row.count}</a>
                                                    </td>
                                                </logic:present>
                                                <logic:present name="mandal">
                                                    <td style="text-align: center">
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalName}&districtid=${row.districtid}&mandalid=${row.mandal_id}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_ blank">${row.count}</a>
                                                    </td>
                                                </logic:present>
                                                <logic:present name="village">
                                                    <td style="text-align: center">
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtname}&mandalName=${row.mandalname}&villageName=${row.villageName}&districtid=${row.districtid}&mandalid=${row.mandalid}&villageid=${row.village_id}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_ blank">${row.count}</a>
                                                    </td>
                                                </logic:present>
                                                <logic:present name="habitation">
                                                    <td style="text-align: center">
                                                        <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&villageName=${row.villagename}&habitationName=${row.habitationName}&disabilityid=${row.disabilityid}&typeOfSearch=${row.typeOfSearch}&habitationid=${row.habitation_id}&districtName=${row.districtname}&mandalName=${row.mandalname}&villageName=${row.villageName}&districtid=${row.districtid}&mandalid=${row.mandalid}&villageid=${row.villageid}&habitationid=${row.habitation_id}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_ blank">${row.count}</a>
                                                    </td>
                                                </logic:present>
                                            </logic:present>
                                            <logic:present name="medicalboardWise">
                                                <td style="text-align: center">
                                                    <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&campName=${row.campname}&districtID=${row.districtid}&campid=${row.campid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">${row.count}</a>
                                                </td>
                                            </logic:present>
                                            <%--<logic:present name="disabilityWise">
                                                <td style="text-align: center">
                                                    <a href="mandalClusterlevelPwd.do?mode=getPwdsIndividualCount&campName=${row.campname}&districtID=${row.districtid}&campid=${row.campid}&typeOfSearch=${row.typeOfSearch}&districtName=${row.districtName}&fromdate=${row.fromDate}&todate=${row.toDate}"target="_blank">${row.count}</a>
                                                </td>
                                            </logic:present>--%>
                                        </tr>
                                    </logic:iterate>
                                    <%-- <logic:present name="dateWise">
                                         <tr>
                                             <th colspan="2">
                                                 Total
                                             </th>
                                             <th style="text-align: center">
                                                 ${row.toptaldatecount}
                                             </th>
                                         </tr>
                                     </logic:present>--%>
                                    <%-- <logic:notPresent name="dateWise">--%>
                                    <tr>
                                        <th colspan="2">
                                            Total
                                        </th>
                                        <th style="text-align: center">
                                            ${row.Totalcount}
                                        </th>
                                    </tr>
                                    <%--</logic:notPresent>--%>
                                </table>
                            </logic:present>
                        </div>
                    </td>

                </tr>
            </table>
        </html:form>
    </body>
    <%--<logic:present  name="districtflag">
        <script>
        document.getElementById('district').style.display="block";
        document.getElementById('getsub').style.display="block";

        </script>

    </logic:present>--%>
</html>
