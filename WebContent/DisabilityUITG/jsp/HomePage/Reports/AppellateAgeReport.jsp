<%-- 
    Document   : AppellateAgeReport
    Created on : Dec 28, 2011, 2:45:27 PM
    Author     : 490058
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
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script language="javascript" src="./DisabilityUITG/js/Mainheader-1.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <style type="text/css">


            .formbg1 {
                font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10px; font-weight:normal; color:#333333; border-left:1px; border-left-color:#999999; border-left-style:solid; border-bottom:1px; border-bottom-color:#999999; border-bottom-style:solid; padding:3px; padding-left:6px; background-color:#e5e5e5;
            }
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
                var fage=document.forms[0].fromAge.value;
                var tage=document.forms[0].toAge.value;
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
                if(fage=='' || tage==''){
                    alert("Enter Fromage and Toage");
                    return false;

                }
                if(fage > tage) {
                    alert("To Age must be grater than from Age");
                    document.forms[0].elements['fromAge'].value="";
                    document.forms[0].elements['toAge'].value="";
                    return false;
                }
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
            function goBack()
            {
                window.history.go(-1);
            }

        </script>


        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
    </head>
    <body onload="ShowDate()">
        <center>

            <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
            <html:form  action="AgeWiseReport.do"  method="post"  >
                <input type="hidden" name="mode"/>
                <input type="hidden" name="districtName"/>
                <input type="hidden" name="mandalName"/>
                <input type="hidden" name="villageName"/>
                <input type="hidden" name="habitationName"/>
                <input type="hidden" name="qualificationName"/>

                <table border="1" cellspacing="0" cellpadding="0" width="90%" class="inputform" align="center">
                    <tr>
                        <th colspan="6">R2.5 Age Wise Report Details</th>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" cellspacing="0" cellpadding="0" width="90%" class="inputform" align="center">
                                <tr>
                                    <td >From Age <font color="red"><b>*</b></font></td>
                                    <td > <html:text property="fromAge" />

                                    </td>
                                    <td >To Age <font color="red"><b>*</b></font></td>
                                    <td >
                                        <html:text property="toAge" />
                                    </td>
                                </tr>
                                <tr>
                                    <td >From Date<font color="red"><b>*</b></font></td>
                                    <td><html:text property="fromdate" readonly="true" size="12" />
                                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                                            onmouseover="window.status='Date Picker';return true;"
                                            onmouseout="window.status='';return true;">
                                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                    </td>
                                    <td >To Date<font color="red"><b>*</b></font></td>
                                    <td>  <html:text property="todate"  readonly="true" size="12"/>
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
                        <th colspan="6">  <html:button property="but" onclick="getDetails()" value="Submit"/>
                        </th>
                    </tr>
                </table>

                <table width="99%" border="0" cellspacing="0" cellpadding="10" style="text-align: center"><tr style="text-align: center"><td>
                            <%if (msg != null) {%>
                            <font color="red"><%=msg%></font>
                            <%}%>

                        </td></tr></table>
                        <logic:present name="empWiseDetails">
                            <logic:empty name="empWiseDetails">
                        <table style="text-align: center">  <tr>
                                <td  style="text-align: center" valign="top">
                                    <table  style="text-align: center">
                                        <tr><td  style="text-align: center" colspan="11"><font color="red"><b>No Data</b></font></td></tr>
                                    </table></td></tr></table>
                                </logic:empty>
                            </logic:present>
                            <logic:notEmpty name="empWiseDetails">
                    <p>Age wise Report</p>


                    <table  style= "text-align: center" cellspacing = "1" border = "0" cellpadding = "4" class= "inputform" width="90%">
                        <tr>
                            <%--  <logic:present name="backButton">
                                  <th style="text-align: left; ">
                                      <a href="#" onclick="goBack()" style="color: red">Back</a>
                                  </th>
                              </logic:present>--%>
                            <logic:iterate name="empWiseDetails" id="row" length="1">
                                <logic:present name="mandal">
                                    <th >
                                        <a href="AgeWiseReport.do?mode=getDetails&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                    </th>
                                </logic:present>
                                <logic:present name="village">
                                    <th>
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                    </th>
                                </logic:present>

                                <logic:present name="habitation">
                                    <th>
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                    </th>
                                </logic:present>
                            </logic:iterate>
                            <logic:present name="names">
                                <th style="text-align: center" colspan="7">
                                    <bean:write name="names"/>
                                </th>
                            </logic:present>

                        </tr>

                        <tr>
                            <th rowspan="2">S No</th>
                            <logic:present name="district">
                                <th rowspan="2">District</th>
                            </logic:present>
                            <logic:present name="mandal">
                                <th rowspan="2">Mandal</th>
                            </logic:present>
                            <logic:present name="village">
                                <th rowspan="2">Village</th>
                            </logic:present>
                            <logic:present name="habitation">
                                <th rowspan="2">Habitation</th>
                            </logic:present>
                            <th rowspan="2">Total Registered</th>
                            <th rowspan="2">Total Assessed </th>
                            <th rowspan="2">Total Eligible</th>
                            <th colspan="2">Total Rejected</th>

                        </tr>

                        <tr>
                            <th >After Assmnt<br/> (<40% disb.)</th>
                            <th >Before Assmnt<br/> (No visible disb.)</th>
                        </tr>

                        <% int j = 1;%>

                        <bean:define id="reg" value="0"/>
                        <bean:define id="ass" value="0"/>
                        <bean:define id="eli" value="0"/>
                        <bean:define id="ar" value="0"/>
                        <bean:define id="dr" value="0"/>


                        <logic:iterate name="empWiseDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                            <bean:define id="tDate" value="${row.tDate}"/>
                            <bean:define id="fage" value="${row.fage}"/>
                            <bean:define id="tage" value="${row.tage}"/>

                            <bean:define id="reg" value="${row.treg+reg}"/>
                            <bean:define id="ass" value="${row.tass+ass}"/>
                            <bean:define id="eli" value="${row.teli+eli}"/>
                            <bean:define id="ar" value="${row.tar+ar}"/>
                            <bean:define id="dr" value="${row.tdr+dr}"/>

                            <%if (j % 2 == 1) {
                                            j++;%>
                            <tr>
                                <td style="text-align: center">
                                    <%=i++%>
                                </td>

                                <logic:present name="district">
                                    <td >
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="mandal">
                                    <td >
                                       <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="village">
                                    <td>
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>

                                <logic:present name="habitation">
                                    <td>
                                        ${row.name}
                                    </td>
                                </logic:present>

                                <td style="text-align: center">
                                    <font size="1" >  ${row.treg}</font>
                                </td>
                                <td style="text-align: center">
                                    <font size="1" > ${row.tass}</font>
                                </td>
                                <td style="text-align: center">
                                   <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=eli', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.teli}</a>
                                </td>
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=tar', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.tar}</a>
                                </td>
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=tdr', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.tdr} </a>
                                </td>
                            </tr>
                            <%} else if (j % 2 == 0) {
                                j++;
                            %>


                            <tr>
                                <td class="formaltbg" style="text-align: center">
                                    <%=i++%>
                                </td>
                                <logic:present name="district">
                                    <td >
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="mandal">
                                    <td >
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="village">
                                    <td>
                                        <a href="AgeWiseReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}"> ${row.name}</a>
                                    </td>
                                </logic:present>

                                <logic:present name="habitation">
                                    <td>
                                        ${row.name}
                                    </td>
                                </logic:present>
                                <td class="formaltbg1" style="text-align: center">
                                    <font size="1" >    ${row.treg}</font>
                                </td>
                                <td class="formaltbg1" style="text-align: center">
                                    <font size="1" >    ${row.tass}</font>
                                </td>
                                <td class="formaltbg" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=eli', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.teli}</a>
                                </td>
                                <td class="formaltbg" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=tar', 'Ratting','resizable=yes, scrollbars=yes,')">  ${row.tar}</a>
                                </td>
                                <td class="formaltbg" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('AgeWiseReport.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}&fromAge=${row.fage}&toAge=${row.tage}&type=tdr', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.tdr}</a>
                                </td>

                            </tr>

                            <%}%>


                        </logic:iterate>
                        <tr>
                            <th style="text-align: center">
                                &nbsp;
                            </th>
                            <th >
                                Total
                            </th>
                            <th  style="text-align: center">
                                ${reg}
                            </th>
                            <th  style="text-align: center">
                                ${ass}
                            </th>
                            <th  style="text-align: center">
                                ${eli}
                            </th>
                            <th  style="text-align: center">
                                ${ar}
                            </th>
                            <th  style="text-align: center">
                                ${dr}
                            </th>
                        </tr>
                    </table>
                    <table style="text-align: center">
                        <%-- <a href="AgeWiseReport.xls?status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>&fromAge=${fage}&toAge=${tage}" target="_blank">--%>
                        <a href="AgeWiseReport.xls?status=getempWiseReport" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            <%--<a href="AgeWiseReport.do?status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>&fromAge=${fage}&toAge=${tage}" target="_blank">--%>
                        <a href="AgeWiseReport.do?status=getempWiseReportPrint" target="_blank">
                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                    </table>
                </logic:notEmpty>
            </html:form></center>
    </body>
</html:html>
