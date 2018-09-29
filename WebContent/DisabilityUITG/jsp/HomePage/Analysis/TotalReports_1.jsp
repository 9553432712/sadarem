<%--
    Document   : TotalReport3
    Created on : Sep 8, 2011, 5:14:06 PM
    Author     : 509862
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");
            String caste = (String) request.getParameter("caste");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");
            String names = (String) request.getAttribute("names");

            String msg = (String) request.getAttribute("msg");



%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="JavaScript">
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
                var disid = document.getElementById("district_id").value;
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
            function  createVillageObject()
            {
                var distid;
                x=GetXmlHttpObject()
                x.onreadystatechange=getVillageDetails;
                distid=document.getElementById("district_id").value;
                if(distid == ""){
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
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;
            }
            function getDetails() {
                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;
                document.forms[0].fromdate.value=fromDate;
                document.forms[0].todate.value=toDate;

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




                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }







        </script>


    </head>
    <body onload="ShowDate()">
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <html:form  action="TotalReport1.do"  method="post" >
        <input type="hidden" name="mode"/>
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <input type="hidden" name="habitationName"/>
                    <input type="hidden" name="qualificationName"/>
        <table width="90%" border="0" cellspacing="0" cellpadding="0" class="inputform" align="center">
             <p>A3.1  General Profile of Eligible PwDs (All Disabilities)</p>
           
                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                       
                        <tr>
                            <td height="40" align="left" valign="middle" >
                                <table  align="center" cellspacing="1" cellpadding="5" width="100%">
                                    <tr>
                                        <td valign="middle"  width="8%">District</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="1" property="district_id" onchange="getData()" style="height:25px;">
                                                <html:option value="0">ALL</html:option>
                                                    <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td  valign="middle" width="8%">Mandal</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                            </html:select>
                                        </td>
                                        <td  valign="middle" width="8%">Village</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="3" property="village_id" style="height:25px;">
                                                <html:option  value="0">ALL</html:option>
                                                <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--  <td  valign="middle" colspan="2">Caste<font color="red"><b>*</b>
                                              <html:select property="caste" styleId="quali">
                                                  <html:option value="n">--Select--</html:option>
                                                  <html:option value="0">ALL</html:option>
                                                  <html:option value="1">ST</html:option>
                                                  <html:option value="2">SC</html:option>
                                                  <html:option value="3">BC</html:option>
                                                  <html:option value="4">OC</html:option>
                                                  <html:option value="5">Minority</html:option>
                                                  <html:option value="6">NA</html:option>
                                              </html:select>
                                          </td>-->
                                        <td  colspan="2">From Date<font color="red"><b>*</b></font>
                                            <html:text property="fromdate" readonly="true" size="12" value="01/01/2010"/>
                                            <a  href="javascript:show_calendar('forms[0].fromdate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>
                                        <td  colspan="2">To Date<font color="red"><b>*</b></font>
                                            <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                            <a  href="javascript:show_calendar('forms[0].todate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>
                                    
                                        <td align="center">  <html:button property="but" onclick="getDetails()" value="Submit"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </table>

        <br>
        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center"><tr align="center"><td>
                    <%if (msg != null) {%>
                    <font color="red"><%=msg%></font>
                    <%}%>

                </td></tr></table>




    </html:form>
</body>
</html:html>
