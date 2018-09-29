

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%
            int i = 1;

            String did = request.getParameter("district_id");
            String mid = request.getParameter("mandal_id");
            String vid = request.getParameter("village_id");
            String hid = request.getParameter("habitation_id");
            String fromdate = request.getParameter("frmdt");
            String todate = request.getParameter("toDate");
            String dname = (String) request.getAttribute("districtname");
            String mname = (String) request.getAttribute("mandalname");
            String vname = (String) request.getAttribute("vname");
            String hname = (String) request.getAttribute("hname");
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0, t9 = 0, t10 = 0, t11 = 0, t12 = 0, t13 = 0, t14 = 0, t15 = 0, t16 = 0, t17 = 0, t18 = 0, t19 = 0, t20 = 0;
            ArrayList ar = new ArrayList();
            ar = (ArrayList) request.getAttribute("reportList");
            if(ar!=null){
            Iterator ir = ar.iterator();
            while (ir.hasNext()) {
                Map m = new HashMap();
                m = (Map) ir.next();
                t1 = t1 + Integer.parseInt(m.get("phaseI").toString());
                t2 = t2 + Integer.parseInt(m.get("phaseII").toString());
                t3 = t3 + Integer.parseInt(m.get("phaseIII").toString());
                t4 = t4 + Integer.parseInt(m.get("phaseIV").toString());
                t5 = t5 + Integer.parseInt(m.get("rachabandaI").toString());
                t6 = t6 + Integer.parseInt(m.get("rachabandaII").toString());
       
                t7 = t7 + Integer.parseInt(m.get("attended").toString());
                t8 = t8 + Integer.parseInt(m.get("eligible4150").toString());
                t9 = t9 + Integer.parseInt(m.get("eligible5160").toString());
                t10 = t10 + Integer.parseInt(m.get("eligible6170").toString());
                t11 = t11 + Integer.parseInt(m.get("eligible7180").toString());
                t12 = t12 + Integer.parseInt(m.get("eligible8190").toString());
                t13 = t13 + Integer.parseInt(m.get("eligibl9100").toString());
                t14 = t14 + Integer.parseInt(m.get("neligible0010").toString());
                t15 = t15 + Integer.parseInt(m.get("eligible1120").toString());
                t16 = t16 + Integer.parseInt(m.get("eligible2130").toString());
                t17 = t17 + Integer.parseInt(m.get("eligible3140").toString());
                t18 = t18 + Integer.parseInt(m.get("total").toString());
                t19 = t19 + Integer.parseInt(m.get("unattended").toString());
            }
}
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css"/>
        <link type="text/css" rel="Stylesheet" href="http://ajax.microsoft.com/ajax/jquery.ui/1.8.5/themes/redmond/jquery-ui.css" /> 

        <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.js"></script>
        <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.ui/1.8.5/jquery-ui.js"></script>


        <script>

            function getMandals(){
                document.forms[0].mode.value="getMandalsList";
                document.forms[0].mandal_id.value="0";
                document.forms[0].submit();
            }
            function getVillages(){

                document.forms[0].mode.value="getVillagesList";
                document.forms[0].village_id.value="0";
                document.forms[0].submit();
            }
            function getHabitations(){
                document.forms[0].mode.value="getHabitationsList";
                document.forms[0].habitation_id.value="0";
                document.forms[0].submit();
            }
            function getSubDisabilitys(){
                document.forms[0].mode.value="getSubDisabilityList";

                document.forms[0].submit();
            }
         
            function getReport(){
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");
                var slcBx4 = document.getElementById("4");
                var chk=document.getElementById("cumulative").checked;
                document.forms[0].districtName.value = slcBx1.options[slcBx1.selectedIndex].text;
                document.forms[0].mandalName.value = slcBx2.options[slcBx2.selectedIndex].text;
                document.forms[0].villageName.value = slcBx3.options[slcBx3.selectedIndex].text;
                document.forms[0].habitationName.value = slcBx4.options[slcBx4.selectedIndex].text;

                if(chk==true){
                                     
                    document.forms[0].mode.value="getMonthReport";
                    if(!document.forms[0].frmdt.value==0&&!document.forms[0].toDate.value==0){
                        document.forms[0].submit();
                    }
                    else {
                        alert("select month and year");
                        document.forms[0].fromDate.focus();
                    }
                }
                else {
                    document.forms[0].mode.value="getMonthReport";
                    if(!document.forms[0].frmdt.value==0&&!document.forms[0].toDate.value==0){
                        document.forms[0].submit();
                    }
                    else {
                        alert("select month and year");
                        document.forms[0].fromDate.focus();
                    }
                }
            }
            function cumul(){
                var chk=document.getElementById("cumulative").checked;
                if(chk==true){
                    document.forms[0].frmdt.value='01/01/2010';
                }
            }
                   
        </script>
        <script>
            $(document).ready(function()
            {
               
                $("#frod").datepicker({
                    dateFormat: 'yy MM',
                    changeMonth: true,
                    changeYear: true,
                    maxDate:"-1d",
                    minDate: new Date(2010,0,1),
                    showButtonPanel: true,

                    onClose: function(dateText, inst) {
                        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                        var k=parseInt(month)+1;
                        var curdat=new Date();
                        var curmonth=curdat.getMonth();
                        var curday=curdat.getDate();
                        var curyear=curdat.getFullYear();
                        
                        $(this).val($.datepicker.formatDate('MM yy', new Date(year, month, 1)));
                        if(curmonth==month && curyear==curyear){$("#toDate").val(k+"/"+curday+"/"+curyear);}
                        else{
                            $("#toDate").val($.datepicker.formatDate('mm/dd/yy',new Date(year,k,0)));
                        }
                        
                        if(document.getElementById("cumulative").checked==false){
                            $("#frmdt").val($.datepicker.formatDate('mm/dd/yy',new Date(year,month,1)));
                        }
                    }
                });


            });
        </script>

        <style>

            .ui-datepicker-calendar {
                display: none;
            }
        </style>

    </head>


    <body>
        <html:form action="/MonthWiseCumulativeReport" >
            <html:hidden property="mode"/>
            <html:hidden property="districtName"/>
            <html:hidden property="mandalName" />
            <html:hidden property="villageName"/>
            <html:hidden property="habitationName"/>
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>


                    <td height="445" align="center" bgcolor="#e4f5fd" valign="top">
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
                            <tr>
                                <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                                <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center"><strong>SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract)</strong></td>
                                <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                            </tr>
                            <tr>
                                <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                                <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                    <table  align="center" cellspacing="1" cellpadding="5" width="100%" >

                                        <tr>
                                            <td class="label" valign="middle" width="10%">
                                                District</td>

                                            <td align="left" valign="middle">
                                                <html:select styleId="1" property="district_id" onchange="getMandals()" style="height:25px;">
                                                    <html:option  value="0">--ALL--</html:option>
                                                    <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                                </html:select>

                                            </td>
                                            <td class="label" valign="middle" width="8%">Mandal</td>
                                            <td align="left" valign="middle">
                                                <html:select styleId="2" property="mandal_id" style="height:25px;" onchange="getVillages()" >
                                                    <html:option  value="0">ALL</html:option>
                                                    <html:optionsCollection   property="mandalList" label="mandal_name" value="mandal_id"  />
                                                </html:select>

                                            </td>
                                            <td class="label" valign="middle" width="8%">Village</td>
                                            <td align="left" valign="middle">
                                                <html:select styleId="3" property="village_id" style="height:25px;" onchange="getHabitations()">
                                                    <html:option  value="0">ALL</html:option>
                                                    <html:optionsCollection   property="villageList" label="village_name" value="village_id"  />
                                                </html:select>
                                            </td>
                                            <td class="label" valign="middle" width="8%">Habitation</td>
                                            <td align="left" valign="middle">
                                                <html:select styleId="4" property="habitation_id" style="height:25px;">
                                                    <html:option  value="0">ALL</html:option>
                                                    <html:optionsCollection   property="habitationList" label="habitation_name" value="habitation_id"  />
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr>



                                            <td class="label" nowrap>Select Month and Year<font color="red">*</font></td>
                                            <td><html:text property="fromDate" styleId="frod" readonly="true"></html:text></td>
                                            <td class="label" nowrap>From Date</td>
                                            <td><html:text property="frmdt" styleId="frmdt" readonly="true"></html:text></td>
                                            <td class="label" nowrap>To Date</td>
                                            <td><html:text property="toDate" styleId="toDate"></html:text></td>
                                            <td  class="label" nowrap><html:checkbox property="cumulative" onclick="cumul()"/><font size="2px">Cumulative Report</font><br/><font size="1.5px">(Generate from start,i.e 01/01/2010)</font></td>
                                        </tr>
                                    </table>
                                </td>
                                <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
                                <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
                                <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
                            </tr>
                        </table><br>
                        <table>
                            <tr>
                                <td colspan="6" align="center">
                                    <html:button property="but" value="Get Report" onclick="getReport()" />
                                </td>
                            </tr>
                        </table><br/>
                        <logic:present name="noData">
                            <center><font color="red" size="3"><b><bean:write name="noData"/></b></font></center>
                        </logic:present>
                        <logic:notEmpty name="reportList">
                            <table align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">

                                <tr><td colspan="26" class="formhdbg" align="center" align="center"> SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract)
                                        <%if (did != null && !did.equalsIgnoreCase("null") && did.equalsIgnoreCase("0")) {%>
                                        All Districts
                                        <%}%>
                                        <%if (did != null && !did.equalsIgnoreCase("null") && !did.equalsIgnoreCase("0") && mid.equalsIgnoreCase("0")) {%>
                                        District:<%=dname%>
                                        <%} else if (mid != null && !mid.equalsIgnoreCase("null") && !mid.equalsIgnoreCase("0") && vid.equalsIgnoreCase("0")) {%>
                                        District:<%=dname%> Mandal:<%=mname%>
                                        <%} else if (vid != null && !vid.equalsIgnoreCase("null") && !vid.equalsIgnoreCase("0") && hid.equalsIgnoreCase("0")) {%>
                                        District:<%=dname%> Mandal:<%=mname%> Village:<%=vname%>

                                        <%}%> From Date: <%=fromdate%> To Date:<%=todate%></td> </tr>
                                <tr>
                                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        S.No
                                    </td>

                                    <td class="formhdbg" align="center" rowspan="2">
                                        <%if (did != null && !did.equalsIgnoreCase("null") && did.equalsIgnoreCase("0")) {%>
                                        District
                                        <%}%>
                                        <%if (did != null && !did.equalsIgnoreCase("null") && !did.equalsIgnoreCase("0") && mid.equalsIgnoreCase("0")) {%>
                                        Mandal
                                        <%} else if (mid != null && !mid.equalsIgnoreCase("null") && !mid.equalsIgnoreCase("0") && vid.equalsIgnoreCase("0")) {%>
                                        Village
                                        <%} else if (vid != null && !vid.equalsIgnoreCase("null") && !vid.equalsIgnoreCase("0")) {%>
                                        Habitation

                                        <%}%>


                                    </td>
                                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        Phase-I
                                    </td>
                                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        Phase-II
                                    </td>
                                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        Phase-III
                                    </td>
                                    
                                    <td colspan="3" class="formhdbg" align="center"> Phase-IV</td>



                                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        Attended<br/>
                                        <font size="1.5px">(Phase-I<br/>+<br/>Phase-II<br/>+<br/>Phase-III<br/>+<br/>Phase-IV)</font>

                                    </td>

                                    <td colspan="6" class="formhdbg" align="center" nowrap>
                                        Eligible<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font>
                                    </td >

                                    <td class="formhdbg" align="center" colspan="4" nowrap>
                                        Not Eligible<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font>
                                    </td>
                                    <td class="formhdbg" align="center" rowspan="2">
                                        Total
                                    </td>
                                    <td class="formhdbg" align="center" colspan="6" nowrap>Not Attended<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font></td>

                                </tr>
                                <tr>

                                    <td class="formhdbg" align="center">
                                    Old
                                    </td>

                                    <td class="formhdbg" align="center" nowrap>
                                        Rachabanda-I
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        Rachabanda-II

                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        40-50
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        51-60
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        61-70
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        71-80
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        81-90
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        91-100
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        0-10
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        11-20
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        21-30
                                    </td>
                                    <td class="formhdbg" align="center" nowrap>
                                        31-39
                                    </td>
                                    <td class="formhdbg" align="center">Total</td>
                                    <td class="formhdbg" align="center">Notice Served<br> Not Attended</td>
                                    <td class="formhdbg" align="center">Dead</td>
                                    <td class="formhdbg" align="center">Not Eligible</td>
                                    <td class="formhdbg" align="center">Not Traceble</td>
                                    <td class="formhdbg" align="center">Duplicate can be removed</td>

                                </tr>
                                <logic:iterate name="reportList" id="row">
                                    <tr>
                                        <td class="formaltbg" align="center">
                                            <%=i++%>
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.district}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.phaseI}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.phaseII}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.phaseIII}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.phaseIV}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.rachabandaI}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.rachabandaII}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.attended}
                                        </td>

                                        <td class="formaltbg" align="left">
                                            ${row.eligible4150}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible5160}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible6170}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible7180}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible8190}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligibl9100}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.neligible0010}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible1120}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible2130}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.eligible3140}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.total}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            ${row.unattended}
                                        </td>
                                        <td class="formaltbg" align="left">
                                            0
                                        </td>
                                        <td class="formaltbg" align="left">
                                            0
                                        </td>
                                        <td class="formaltbg" align="left">
                                            0
                                        </td>
                                        <td class="formaltbg" align="left">
                                            0
                                        </td>
                                        <td class="formaltbg" align="left">
                                            0
                                        </td>
                                    </tr>


                                </logic:iterate>
                                    <tr>
                                        <td colspan="2" class="formhdbg" align="center" align="center">Total</td>
                                        <td class="formhdbg" align="center" align="center"><%=t1%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t2%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t3%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t4%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t5%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t6%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t7%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t8%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t9%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t10%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t11%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t12%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t13%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t14%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t15%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t16%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t17%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t18%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t19%></td>
                                        
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>



                                    </tr>
                            </table>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="MonthWiseCumulativeReport.do?mode=genExcel&district_id=<%=did%>&mandal_id=<%=mid%>&village_id=<%=vid%>&habitation_id=<%=hid%>&fromdate=<%=fromdate%>&todate=<%=todate%>&districtName=<%=dname%>&mandalName=<%=mname%>&villageName=<%=vname%>&habitationName=<%=hname%>" target="_blank"  >Export Excel
                                            <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                    </td>
                                    <td><a href="MonthWiseCumulativeReport.do?mode=print&district_id=<%=did%>&mandal_id=<%=mid%>&village_id=<%=vid%>&habitation_id=<%=hid%>&fromdate=<%=fromdate%>&todate=<%=todate%>&districtName=<%=dname%>&mandalName=<%=mname%>&villageName=<%=vname%>&habitationName=<%=hname%>" target="_blank"  >Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a></td>
                                </tr>
                                <br>
                            </table>
                        </logic:notEmpty>

                        <br/>

            </table>
        </html:form>
    </body>
</html:html>
