<%-- 
    Document   : Educationwise
    Created on : Feb 28, 2011, 10:43:35 AM
    Author     : 509865
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>

<html:html>
    <head>
        <title>SADAREM</title>



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



            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }
        </script>
    </head>

    <body onload="createDistrictObject(),disabileDistrict(),ShowDate()">


        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <html:form action="generalNeeds.do?getGeneralNeedReport=getGeneralNeedReport" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center" id="grid">
                <tr>

                <input type="hidden" name="districtName"/>
                <input type="hidden" name="mandalName"/>
                <input type="hidden" name="villageName"/>
                <input type="hidden" name="habitationName"/>
                <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                    <p>Select Demogrepy Details</p>
                    <tr>

                        <td align="left" valign="middle" >

                            <table  align="center" cellspacing="1" cellpadding="5" width="100%">
                                <tr>
                                    <td valign="middle"  width="8%">District</td>
                                    <td align="left" valign="middle">
                                        <html:select styleId="1" property="district_id" onchange="districtList(this.value),disabileDistrict()" style="height:25px;">
                                            <html:option  value="0">--ALL--</html:option>
                                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                                        </html:select>
                                    </td>
                                    <td  valign="middle" width="8%">Mandal</td>
                                    <td align="left" valign="middle">
                                        <html:select styleId="2" property="mandal_id" onchange="mandalList(),disabileMandal()" style="height:25px;">
                                            <html:option  value="0">--ALL--</html:option>
                                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                        </html:select>

                                    </td>
                                    <td  valign="middle" width="8%">Village</td>
                                    <td align="left" valign="middle">
                                        <html:select styleId="3" property="village_id" style="height:25px;">
                                            <html:option  value="0">--ALL--</html:option>
                                            <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                        </html:select>

                                    </td>
                                </tr>
                                <tr>


                                    <td colspan="2">&nbsp;</td>
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
                                </tr>
                            </table>
                            <%
                                        String msg = (String) request.getAttribute("msg");

                            %>
                            <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                        </td>

                    </tr>

                </table> <br>
                <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">

                    <tr>
                    <p>Select Demogrepy Details</p>

                    <tr>
                        <td  style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                        <td height="40" align="left" valign="middle" >
                            <table  align="center" cellspacing="1" cellpadding="5" width="100%">
                                <tr><td class="labelBlue"><b>R4.1 Personal Details Reports:</b><br><br> &nbsp; &nbsp; &nbsp; &nbsp;
                                        <a href="javascript:void(0);" onClick="clickOnLink(this.id);" id="partacount" style="color:#000000">R4.1.1 &nbsp; Educationwise Report</a><br><br>

                                    </td>
                                </tr>
                            </table>
                        </td>

                    </tr>

                </table>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
