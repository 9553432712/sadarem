<%--
    Document   : modifiedAssesedPWDSDetails
    Created on : Jan 21, 2013, 4:13:57 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page import="org.bf.disability.Constants.CommonConstants,com.tcs.sadarem.util.CommonUtility"%>
<%@page session="true"%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <%
                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));%>

        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>

        <script >
            var typeOfselection="";
            <%
                        if (request.getAttribute("typeOfSearchValue") != null) {
            %>
                typeOfselection='<%=request.getAttribute("typeOfSearchValue")%>';
            <%
                                    } else {
            %>typeOfselection="Date Wise"
            <%                        }


            %>
                function resetAllVlaues(){

            <%     if (currentRoleId == CommonConstants.PHASEPWDREPORTROLE) {%>
                    document.getElementById('1').value="0";
            <%}%>
                    document.getElementById('2').value="0";
                    document.getElementById('3').value="0";
                    document.getElementById('4').value="0";
                    document.getElementById('6').value="";
                    document.getElementById('7').value="0";
                    document.getElementById('8').value="";
                    document.getElementById('9').value="";
                    if(document.getElementById('10').checked){
                        typeOfselection="Financial Year Wise";

                    }else{
                        typeOfselection="Date Wise"
                    }

            <%     if (currentRoleId == CommonConstants.PHASEPWDREPORTROLE) {%>
                    createDistrictObject();
            <%} else {%>
                    districtList();
            <%}%>
                    disabileDistrict();
                    bodyLoadfunction();
                }
                function submitForm(){

                    document.forms[0].mode.value = "getResults";
                    document.forms[0].submit();

                }


                function changeSelection(id){
                    if(id=='Financial Year Wise'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        // document.getElementById("7").value="2012-13" ;
                        typeOfselection="Financial Year Wise";
                    }else{

                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById("7").value="0";
                        typeOfselection="Date Wise";
                    }

                }

                function bodyLoadfunction(){

                    if(typeOfselection=='Financial Year Wise'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        // document.getElementById("7").value="2012-13" ;
                        typeOfselection="Financial Year Wise";
                        document.getElementById('10').checked=true;
                    }else{

                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById("7").value="0";
                        typeOfselection="Date Wise";
                        document.getElementById('5').checked=true;
                    }
                }
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
                        removeLists(2,3);
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
                    document.forms[0].mandal_id.value="0";

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

                        createPanchayatObject();
                    }
                }
                function panchList(){

                    var vilid= document.forms[0].panchayat_id.value;
                    if(vilid==""){
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
                        distid = document.getElementById("districtid").value;
                    }
                    var mandid= document.forms[0].mandal_id.value;
                    var pancid= document.forms[0].panchayat_id.value;
                    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&panchayatid="+pancid+"&territory=7";
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
                function  createPanchayatObject()
                {
                    var distid;
                    x=GetXmlHttpObject()
                    x.onreadystatechange=getPanchayatDetails;
                    distid= document.forms[0].district_id.value;
                    if(distid == ""){
                        distid = document.getElementById("districtid").value;
                    }
                    var mandid= document.forms[0].mandal_id.value;
                    var url="getTerritory.do?parameter=getTerritoryList&districtid="+distid+"&mandalid="+mandid+"&territory=4";
                    x.open("GET",url,true)
                    x.send();
                }
                function getPanchayatDetails()
                {
                    var rs1,rs2;
                    removeall("panchayat_id");
                    document.forms[0].panchayat_id.value="0";

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
                function selectedNames()
                {

                    var slcBx2 = document.getElementById("2");
                    var slcBx3 = document.getElementById("3");
                    var slcBx4 = document.getElementById("4");
            <%     if (currentRoleId == CommonConstants.PHASEPWDREPORTROLE) {%>
                    var slcBx1 = document.getElementById("1");
                    document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
            <%} else {%>
                    document.getElementById("districtName").value =  document.forms[0].district_name.value;
            <%}%>
                    document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                    document.getElementById("panchayatName").value = slcBx3.options[slcBx3.selectedIndex].text;
                    document.getElementById("villageName").value = slcBx4.options[slcBx4.selectedIndex].text;
                    if(slcBx2.disabled){
                        document.getElementById("mandalName").value ="ALL";
                        document.getElementById("panchayatName").value = "ALL";
                        document.getElementById("villageName").value ="ALL";
                    }else if(slcBx3.disabled){
                        document.getElementById("panchayatName").value = "ALL";
                        document.getElementById("villageName").value ="ALL";
                    }else if(slcBx4.disabled){
                        document.getElementById("villageName").value ="ALL";
                    }
                }

                function addoption(result1,result2,name)
                {
                    var opt=document.createElement("OPTION");
                    opt.text=result1;
                    opt.value=result2;

                    try{
                        document.getElementById(name).add(opt);
                    }catch(ex){
                        if(name=="district_id") {
                            document.forms[0].district_id.appendChild(opt,null);
                        }else  if(name=="mandal_id") {
                            document.forms[0].mandal_id.appendChild(opt,null);
                        }else  if(name=="village_id") {
                            document.forms[0].village_id.appendChild(opt,null);
                        }else  if(name=="panchayat_id") {
                            document.forms[0].panchayat_id.appendChild(opt,null);
                        }

                    }

                }

                function removeall(name)
                {
                    if(name=="mandal_id") {
                        var x1=document.forms[0].mandal_id.options.length;
                    }
                    else if(name=="district_id") {
                        var x1=document.forms[0].district_id.options.length;
                    }else if(name=="village_id") {
                        var x1=document.forms[0].village_id.options.length;
                    }else if(name=="panchayat_id") {
                        var x1=document.forms[0].panchayat_id.options.length;
                    }

                    for(i=x1;i>0;i--) {
                        if(name=="mandal_id") {
                            document.forms[0].mandal_id.options[i]=null;
                        } else if(name=="district_id") {
                            document.forms[0].district_id.options[i]=null;
                        }else if(name=="village_id") {
                            document.forms[0].village_id.options[i]=null;
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

                function validate_required(field,alerttxt)
                {

                    with (field)
                    {
                        if (value==null||value==""||value=="0")
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
                    selectedNames();

                    with (thisform)
                    {

                        if (validate_required(pensionPhase,"Pension Phase must be selected!")==false)
                        {
                            pensionPhase.focus();
                            return false;
                        }
                        if(typeOfselection=="Financial Year Wise"){
                            if (validate_required(financialYearWise,"Year Wise Selection must be selected!")==false)
                            {
                                financialYearWise.focus();
                                return false;
                            }
                        }else if(typeOfselection=="Date Wise"){
                            if (validate_required(fromdate,"From Date must be selected!")==false)
                            {
                                fromdate.focus();
                                return false;
                            }
                            if (validate_required(todate,"To Date must be selected!")==false)
                            {
                                todate.focus();
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
                        }


                    }

                }

                function disabileDistrict()
                {

                    var x= document.forms[0].district_id.value;
                    if(x == 0)
                    {
                        document.forms[0].mandal_id.disabled = true;
                        document.forms[0].panchayat_id.disabled = true;
                        document.forms[0].village_id.disabled = true;
                        document.forms[0].mandal_id.value = "0";
                        document.forms[0].panchayat_id.value = "0";
                        document.forms[0].village_id.value = "0";


                    }
                    else
                    {
                        document.forms[0].mandal_id.disabled = false;
                        document.forms[0].panchayat_id.disabled = true;
                        document.forms[0].village_id.disabled = true;
                        document.forms[0].panchayat_id.value = "0";
                        document.forms[0].village_id.value = "0";
                    }

                }


                function disabilePanchayat()
                {

                    var x= document.forms[0].mandal_id.value;
                    if(x == 0)
                    {

                        document.forms[0].panchayat_id.disabled = true;
                        document.forms[0].village_id.disabled = true;
                        document.forms[0].panchayat_id.value = "0";
                        document.forms[0].village_id.value = "0";
                    }
                    else
                    {

                        document.forms[0].panchayat_id.disabled = false;
                        document.forms[0].village_id.disabled = true;

                        document.forms[0].village_id.value = "0";

                    }

                }
                function disabileVillage()
                {
                    var x= document.forms[0].panchayat_id.value;

                    if(x == "0")
                    {
                        document.forms[0].village_id.disabled = true;
                        document.forms[0].village_id.value = "0";

                    }else{
                        document.forms[0].village_id.disabled = false;
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
    <%
                if (request.getAttribute("typeOfSearchValue") != null) {

    %>
    <body onload="bodyLoadfunction();">
        <%}
                    if (currentRoleId == CommonConstants.PHASEPWDREPORTROLE) {%>
    <body onload="createDistrictObject(),disabileDistrict(),ShowDate(),bodyLoadfunction();">
        <%} else {
        %>
    <body onload="districtList(),disabileDistrict(),ShowDate(),bodyLoadfunction();">
        <%}
        %>
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <html:form styleId="fm1" action="/modifiedAssessedPWDDetails.do?mode=getResults" method="post" onsubmit="return validate_form(this);">
        <html:hidden property="mode"/>
        <br/>

        <input type="hidden" name="districtName" id="districtName"/>
        <input type="hidden" name="mandalName" id="mandalName"/>
        <input type="hidden" name="panchayatName" id="panchayatName"/>
        <input type="hidden" name="villageName" id="villageName"/>

        <table  align="center" cellspacing="1" cellpadding="0" width="90%" border="0" class="inputform">
            <tr><th colspan="4">District and Phase wise Assessed PWD's Report - Abstract</th></tr>
          <%--  <tr>

                <td height="40" align="left" valign="middle">
                    <table  align="center" cellspacing="1" cellpadding="0" width="100%" border="0" class="inputform">--%>
                        <%
                                    if (currentRoleId == CommonConstants.PHASEPWDREPORTROLE) {
                        %>
                        <tr>
                            <td >District :</td>
                            <td >
                                <html:select styleId="1" property="district_id" onchange="districtList(this.value),disabileDistrict()" style="height:25px;font-size:11px;">
                                    <html:option  value="0">ALL</html:option>
                                    <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                </html:select>
                            </td>
                        </tr>
                        <%} else {%>

                        <html:hidden property="district_name" />
                        <html:hidden property="district_id" />
                        <%}%>
                        <tr>
                            <td>Mandal</td>
                            <td>
                                <html:select styleId="2" property="mandal_id" style="width:150px;height:25px;font-size:11px;" onchange="mandalList(this.value),disabilePanchayat()" >
                                    <html:option  value="0" >ALL</html:option>
                                    <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                </html:select>
                            </td>

                            <td>Panchayat</td>
                            <td>
                                <html:select styleId="3" property="panchayat_id" style="width:150px;height:25px;font-size:11px;" onchange="panchList(this.value),disabileVillage();">
                                    <html:option  value="0">ALL</html:option>
                                    <html:optionsCollection   property="panchayatList" label="panchayat_name" value="panchayat_id"  />
                                </html:select>

                            </td>
                        </tr>
                        <tr>

                            <td>Village :</td>
                            <td>
                                <html:select styleId="4" property="village_id" style="width:150px;height:25px;font-size:11px;">
                                    <html:option  value="0">ALL</html:option>
                                    <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                </html:select>
                            </td>

                            <td>Pension Phase :<font color="red"><b>*</b></font></td>
                            <td>
                                <html:select styleId="6" property="pensionPhase" style="width:150px;height:25px;font-size:11px;">
                                    <html:option value="">  --SELECT--   </html:option>
                                    <html:option value="1">PhaseI</html:option>
                                    <html:option value="2">PhaseII</html:option>
                                    <html:option value="3">PhaseIII</html:option>
                                    <html:option value="4">PhaseIV</html:option>
                                    <html:option value="5">RachaBandaI</html:option>
                                    <html:option value="6">RachaBandaII</html:option>
                                    <html:option value="7">After RachaBandaI</html:option>
                                    <html:option value="8">After RachaBandaII</html:option>
                                    <html:option value="ALL">ALL(PhaseI,PhaseII,PhaseIV)</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="6" align="center">
                                <html:radio property="typeOfSearch" styleId="5" value="Date Wise" onclick="changeSelection(this.value);" style="border:0">Date Wise</html:radio>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio property="typeOfSearch" styleId="10" value="Financial Year Wise" onclick="changeSelection(this.value);" style="border:0">Financial Year Wise</html:radio>
                            </th>
                        </tr>
                        <tr>
                            <td id="betweenDates" style="display: none;" width="60%" colspan="4">
                                  <table  align="center" cellspacing="1" cellpadding="0" width="100%" border="0" class="inputform">
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
                                </table>
                            </td>
                            <td id="financialYear" style="display: none;" width="60%" colspan="4">
                                <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" >
                                    <tr>
                                        <td> Year wise Selection<font color="red"><b>*</b></font></td>
                                        <td align="left">
                                            <html:select styleId="7" property="financialYearWise" style="width:150px;height:25px;font-size:11px;" >
                                                <html:option value="0">  --SELECT--  </html:option>
                                                <html:option value="2009-10">2009-10</html:option>
                                                <html:option value="2010-11">2010-11</html:option>
                                                <html:option value="2011-12">2011-12</html:option>
                                                <html:option value="2012-13">2012-13</html:option>
                                                <html:option value="2012-13">2013-14</html:option>
                                                <html:option value="2012-13">2014-15</html:option>
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>

                            <th colspan="4" align="center"><input type="Submit" name="card" value="Submit" class="button" >&nbsp;&nbsp;<input type="button" name="Reset" value="Reset" class="button" onclick="resetAllVlaues();"></th>

                        </tr>
                    </table>
                    <%
                                String msg = (String) request.getAttribute("msg");

                    %>
                    <% if (msg != null) {%><table align="center"><tr><td><font style="color: red"><%=msg%></font></td></tr></table> <% }%>
              <%--  </td>

            </tr>

        </table>--%>


    </html:form>
</body>
</html:html>