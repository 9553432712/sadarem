<%--
    Document   : LocomotorReport
    Created on : Oct 21, 2011, 11:09:20 AM
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
            String names=(String)request.getAttribute("names");
                   String msg=(String)request.getAttribute("msg");
            String ur=(String)request.getAttribute("ur");
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

//alert(document.forms[0].tot.value);
////function roundNumber(number, decimals) { // Arguments: number to round, number of decimal places
	var newnumber = new Number(document.forms[0].tot.value+'').toFixed(2);
        alert(parseFloat(newnumber));
	//document.forms[0].tot =  parseFloat(newnumber); // Output the result to the form field (change for your purposes)
//}


        </script>


    </head>
    <body >
    <script  language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <html:form  action="<%=ur%>"  method="post" >
        <input type="hidden" name="mode"/>
        <input type="hidden" name="districtName"/>
        <input type="hidden" name="mandalName"/>
        <input type="hidden" name="villageName"/>
        <input type="hidden" name="habitationName"/>
        <input type="hidden" name="qualificationName"/>
        <table width="90%" border="1" cellspacing="0" cellpadding="0"  align="center" class="inputform">
            
           
                    <tr>
                        <th colspan="4">
                            SADAREM Eligible Person with Disability Personal Details Profile

                        </th>
                    </tr>
            <tr>
                <td >
                    <table  align="center" cellspacing="0" cellpadding="0" width="100%"  border="0">
                        <tr>
                            <td valign="middle" width="8%">District</td>
                            <td align="left" valign="middle">
                                <html:select styleId="1" property="district_id" onchange="getData()" style="height:25px;">
                                    <html:option value="0">ALL</html:option>
                                        <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                                </html:select>
                            </td>
                            <td valign="middle" width="8%">Mandal</td>
                            <td align="left" valign="middle">
                                <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
                                    <html:option  value="0">ALL</html:option>
                                    <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                </html:select>
                            </td>
                            <td valign="middle" width="8%">Village</td>
                            <td align="left" valign="middle">
                                <html:select styleId="3" property="village_id" style="height:25px;">
                                    <html:option  value="0">ALL</html:option>
                                    <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                </html:select>
                            </td>
                        </tr>
                        
                       <tr>

                            <td colspan="2">From Date<font color="red"><b>*</b></font>
                                <html:text property="fromdate" readonly="true" size="12" />
                                <a  href="javascript:show_calendar('forms[0].fromdate');"
                                    onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;">
                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>
                            <td colspan="2">To Date<font color="red"><b>*</b></font>
                                <html:text property="todate"   readonly="true" size="12"/>
                                <a  href="javascript:show_calendar('forms[0].todate');"
                                    onmouseover="window.status='Date Picker';return true;"
                                    onmouseout="window.status='';return true;">
                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                            </td>
                            
                           


                        </tr>
                        
                    </table>
                </td>
            </tr>
            <tr> <th>  <html:button property="but" onclick="getDetails()" value="Submit"/>
                            </th></tr>
        </table>   <br>
        
<table width="99%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <%if(msg!=null){%>
                    <p id="errmsg"><%=msg %></p>
<%}%>

                </table>

<% int j=1; i=1; int k=1;%>

       <logic:notEmpty name="miWiseDetails">
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

                    <tr>
                    <th align="center"  colspan="10" >Mental Illness Causes, Performance ability, Percentage wise Report</th>
                    </tr>
                             <tr>
                                <th  align="center" width="4%">S.No</th>
                                <th  align="center" width="12%">Categories</th>
                                <th  align="center" width="12%">Sub Categories</th>
                                <th width="12%"  align="center">Male Count</th>
                                <th width="12%" align="center">Male %</th>
                                <th width="12%"  align="center">Female Count</th>
                                <th width="12%"  align="center">Female %</th>
                                <th width="12%"  align="center">Total Count</th>
                                <th width="12%"  align="center">Total %</th>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
 <logic:iterate name="miWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}&type=${row.col}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
        <td  align="left" width="12%" rowspan="17">Mental Illness Causes wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>



<%}else if(j==11){j++;
      %>
</table>
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" ><%=k++ %></td>
        <td  align="left" width="12%" rowspan="17">Mental Illness Percentage wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%" ><%=k++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MIReport.do?MIReport=MIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<%}%>



 </logic:iterate>
</table>
            <table align="center">
                <a href="MIReport.xls?MIReport=MIReport&status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                <a href="MIReport.xls?MIReport=MIReport&status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
            </table>
       </logic:notEmpty>


<logic:notEmpty name="hiWiseDetails">
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

                    <tr>
                    <th align="center"  colspan="10" >Hearing Impairment Causes, Performance ability, Percentage wise Report</th>
                    </tr>
                             <tr>
                                <th  align="center" width="4%">S.No</th>
                                <th  align="center" width="12%">Categories</th>
                                <th  align="center" width="12%">Sub Categories</th>
                                <th width="12%"  align="center">Male Count</th>
                                <th width="12%" align="center">Male %</th>
                                <th width="12%"  align="center">Female Count</th>
                                <th width="12%"  align="center">Female %</th>
                                <th width="12%"  align="center">Total Count</th>
                                <th width="12%"  align="center">Total %</th>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
 <logic:iterate name="hiWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
        <td  align="left" width="12%" rowspan="17">Hearing  Impairment Causes wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<% }else if(j==11){
  j++;
%></table>
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" >1</td>
        <td  align="left" width="12%" rowspan="17">Hearing  Impairment Performance ability wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr></table>

<%}else if(j==12){j++;
      %>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" ><%=k++ %></td>
        <td  align="left" width="12%" rowspan="17">Hearing Impairment Percentage wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>


<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%" ><%=k++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('HIReport.do?HIReport=HIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<%}%>


 </logic:iterate>
</table>
            <table align="center">
                <a href="HIReport.xls?HIReport=HIReport&status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                <a href="HIReport.xls?HIReport=HIReport&status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
            </table>
       </logic:notEmpty>


       <logic:notEmpty name="mrWiseDetails">
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                    <th align="center"  colspan="10" >Mental Retardation Causes, Performance ability, Percentage wise report</th>
                    </tr>
                             <tr>
                                <th  align="center" width="4%">S.No</th>
                                <th  align="center" width="12%">Categories</th>
                                <th  align="center" width="12%">Sub Categories</th>
                                <th width="12%"  align="center">Male Count</th>
                                <th width="12%" align="center">Male %</th>
                                <th width="12%"  align="center">Female Count</th>
                                <th width="12%"  align="center">Female %</th>
                                <th width="12%"  align="center">Total Count</th>
                                <th width="12%"  align="center">Total %</th>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
 <logic:iterate name="mrWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
        <td  align="left" width="12%" rowspan="17">Mental Retardation Causes wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<% }else if(j==11){
  j++;
%></table>
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" >1</td>
        <td  align="left" width="12%" rowspan="17">Mental Retardation Performance ability wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr></table>

<%}else if(j==12){j++;
      %>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" ><%=k++ %></td>
        <td  align="left" width="12%" rowspan="17">Mental Retardation  Percentage wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%" ><%=k++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('MRReport.do?MRReport=MRReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<%}%>

 </logic:iterate>
</table>
            <table align="center">
                <a href="MRReport.xls?MRReport=MRReport&status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                <a href="MRReport.xls?MRReport=MRReport&status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
            </table>
       </logic:notEmpty>

 <logic:notEmpty name="viWiseDetails">
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

                    <tr>
                    <th align="center"  colspan="10" >Visual Impairment Causes, Performance ability, Category, Percentage wise report</th>
                    </tr>
                             <tr>
                                <th  align="center" width="4%">S.No</th>
                                <th  align="center" width="12%">Categories</th>
                                <th  align="center" width="12%">Sub Categories</th>
                                <th width="12%"  align="center">Male Count</th>
                                <th width="12%" align="center">Male %</th>
                                <th width="12%"  align="center">Female Count</th>
                                <th width="12%"  align="center">Female %</th>
                                <th width="12%"  align="center">Total Count</th>
                                <th width="12%"  align="center">Total %</th>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
 <logic:iterate name="viWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
          <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
        <td  align="left" width="12%" rowspan="17">Visual Impairment Causes wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>
    <%}else if(j<=7){ %>

    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<% }else if(j==8){
  j++;k=1;
%></table>
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" ><%=k++ %></td>
        <td  align="left" width="12%" rowspan="17">Visual Impairment Performance ability wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>



<%}else if(j==9){  j++; %>

<tr>  <td align="center"  width="4%" ><%=k++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

</table>




<%}
else if(j==10){j++; k=1;
      %>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
<tr>  <td align="center"  width="4%" ><%=k++ %></td>
        <td  align="left" width="12%" rowspan="17">Visual Impairment Category wise</td>
        <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%" ><%=k++ %></td>
     <td  align="left"  width="12%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a></td>
                              <td width="12%" style="text-align:center">${row.mp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a></td>
                              <td width="12%" style="text-align:center">${row.fp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('VIReport.do?VIReport=VIReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablet}&colu=${row.columnt}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.coll}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.tot}</a></td>
                             <td width="12%"  >${row.tp}</td></tr>

<%}%>

 </logic:iterate>
</table>
            <table align="center">
                <a href="VIReport.xls?VIReport=VIReport&status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                <a href="VIReport.xls?VIReport=VIReport&status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
            </table>
       </logic:notEmpty>


<logic:notEmpty name="ohfWiseDetails">
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                    <th align="center"  colspan="10" >Locomotor Disability  - Needs Assessment Report</th>
                    </tr>
                             <tr>
                                 <th  align="center" width="48%" rowspan="2">Functional  Needs</th>
                                 <th  align="center" width="24%" colspan="2">Upto 14 Years</th>
                                 <th width="24%"  align="center" colspan="2">Above 14 years</th>

                            </tr>

                            <tr>
                                 <th  align="center" width="12%" >Count</th>
                                 <th  align="center" width="12%" >%</th>
                                 <th width="12%"  align="center" >Count</th>
                                 <th width="12%"  align="center" >%</th>

                            </tr>


</table><% i=1;j=7; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <logic:iterate name="ohfWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                   <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<4){%>
       <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==4){%>
<tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>
         </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total (Physiotherapy & Occupational Therapy)</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<%}else if(i>=5 && i<7){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==7){%>
<tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Wheel Chair</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


   <%}else if(i>=8 && i<9){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==9){%>
<tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Tricycle</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


<%}else if(i>=10 && i<11){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==11){%>
<tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Walking Stick</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<%}else if(i>=12 && i<27){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==27){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Crutches</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">



<%}else if(i>=28 && i<29){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==29){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Walking Frame</td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>
</table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Orthosis / Splint for upper Extremity</td></tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">




<%}else if(i>=30 && i<33){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==33){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Upper Extremity Orthosis </td>
                              <td width="12%" style="text-align:center">${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Orthosis / Splint for Lower Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


<%}else if(i>=34 && i<40){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==40){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Lower Extremity Orthosis </td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Spinal Orhtosis</td></tr>
</table>


                              <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<%}else if(i>=41 && i<43){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==43){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Lower Extremity Orthosis </td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table>
          <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Prosthesis for Upper Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<%}else if(i>=44 && i<50){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==50){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>


       <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Upper Extremity Orthosis </td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table>




                              <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Prosthesis for Lower Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


    <%}else if(i>=51 && i<56){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==56){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Lower extremity Prosthesis </td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">ADL Equipment</td></tr>
</table><table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">





    <%}else if(i>=57 && i<64){%>

    <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        <%}else if(i==64){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total ADL Equipment </td>
                              <td width="12%" style="text-align:center"> ${row.tl}</td>
                              <td width="12%" style="text-align:center">${row.tlp}</td>
                              <td width="12%" style="text-align:center">${row.tg}</td>
                              <td width="12%" style="text-align:center">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

  <%}else if(i==65){%>

        <tr>  <td align="center"  width="4%" ><%=i++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


    </table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


<%}



else if(i==66){i++; j=66;%>

</table><table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
    <tr><td align="left"  colspan="6">Locomotor General Needs </td></tr>
</table>
<table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

    <tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="#">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%" >&nbsp;</td>
     <td  align="left"  width="48%">Education services</td>
                              <td width="12%" style="text-align:center"> &nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<%}else if(j>66 && j<69){%>




<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>


<%}else if(j==69){%>

<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Education services</td>
                              <td width="12%" style="text-align:center"> ${row.l1}</td>
                              <td width="12%" style="text-align:center">${row.tlp1}</td>
                              <td width="12%" style="text-align:center">${row.g1}</td>
                              <td width="12%" style="text-align:center">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

<tr>  <td align="center"  width="4%" >&nbsp;</td>
     <td  align="left"  width="48%">Vocational Training</td>
                              <td width="12%" style="text-align:center"> &nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


<% }else if(j==70){





%>
<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>




<%}else if(j==71){%>


<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Vocational Training </td>
                              <td width="12%" style="text-align:center"> ${row.l2}</td>
                              <td width="12%" style="text-align:center">${row.tlp2}</td>
                              <td width="12%" style="text-align:center">${row.g2}</td>
                              <td width="12%" style="text-align:center">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

    <tr>  <td align="center"  width="4%" >&nbsp;</td>
     <td  align="left"  width="48%">Counseling and Guidance</td>
                              <td width="12%" style="text-align:center"> &nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              <td width="12%" style="text-align:center">&nbsp;</td>
                              </tr>


<% }else if(j==72){





%>
<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>




<%}else if(j==73){%>


<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

     <tr>
     <td  align="left"  width="52%">Total Counseling and guidance  </td>
                              <td width="12%" style="text-align:center"> ${row.l3}</td>
                              <td width="12%" style="text-align:center">${row.tlp3}</td>
                              <td width="12%" style="text-align:center">${row.g3}</td>
                              <td width="12%" style="text-align:center">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


<%}else{%>
<tr>  <td align="center"  width="4%" ><%=j++ %></td>
     <td  align="left"  width="48%">${row.col}</td>
                              <td width="12%" style="text-align:center"> <a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablem}&colu=${row.columnm}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.l}</a></td>
                              <td width="12%" style="text-align:center">${row.lp}</td>
                              <td width="12%" style="text-align:center"><a href="javascript:void(0);" onclick ="window.open('LocomotorFunctiionalneedReport.do?LocomotorReport=LocomotorFunctiionalneedReport&details=getDetails&dID=${row.district}&mID=${row.mandal}&vID=${row.village}&tablee=${row.tablef}&colu=${row.columnf}&hid=${row.hab_id}&fromdate=${row.fdate}&todate=${row.tdate}&type=${row.col}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.g}</a></td>
                              <td width="12%" style="text-align:center">${row.gp}</td>
                              </tr>





<%}%>





    </logic:iterate></table><br><br>
<table align="center">
                <a href="LocomotorFunctiionalneedReport.xls?LocomotorReport=LocomotorFunctiionalneedReport&status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                <a href="LocomotorFunctiionalneedReport.xls?LocomotorReport=LocomotorFunctiionalneedReport&status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
            </table>

</logic:notEmpty>
   






   










    </html:form>
</body>
</html:html>