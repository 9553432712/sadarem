<%--
    Document   : PercentageWiseReport
    Created on : Jul 24, 2014, 3:52:11 PM
    Author     : 842412
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <%
                    int i = 1;
                    String fionance = null;
                    ArrayList PecentageWiseData = null;
                    if (request.getAttribute("FinancialYearWise") != null) {
                        fionance = request.getAttribute("FinancialYearWise").toString();
                    }
                    if (request.getAttribute("PecentageWiseData") != null) {
                        PecentageWiseData = (ArrayList) request.getAttribute("PecentageWiseData");
                    }

        %>
        <script>
            var typeOfselection="";
            var reportType="";
            <%
                        if (request.getAttribute("typeOfSearch") != null) {%>
                            typeOfselection='<%=request.getAttribute("typeOfSearch")%>';
            <%}%>

                function insertDetails(){
            <%--if(document.forms[0].district_id.value=="0"){
                alert("Please Select District");
                document.forms[0].district_id.focus();
                return false;
            }--%>
                    if(document.forms[0].fromdate.value==""){
                        alert("Please Select From Date");
                        document.forms[0].fromdate.focus();
                        return false;

                    }else if(document.forms[0].todate.value==""){
                        alert("Please Select Todate");
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
                    else{
                        document.forms[0].mode.value='getPecentageWiseData';
                        document.forms[0].submit();
                    }

                }
                function insertFinance(){
            <%--if(document.forms[0].district_id.value=="0"){
                alert("Please Select District");
                document.forms[0].district_id.focus();
                return false;

                    }--%>
                            if(document.forms[0].financialYearWise.value=="0"){
                                alert("Please Select Financial Year");
                                document.forms[0].district_id.focus();
                                return false;
                            }

                            else{
                                document.forms[0].mode.value='getPecentageWiseData';
                                document.forms[0].submit();
                            }

                        }
                        function changeSelection(id){

                            if(id=='Financial Year Wise'){
                                document.getElementById('betweenDates').style.display="none";
                                document.getElementById('financialYear').style.display="block";
                                document.getElementById('submit').style.display="block";
                                // document.getElementById("7").value="2012-13" ;
                                typeOfselection="Financial Year Wise";

                            }else{
                                document.getElementById('betweenDates').style.display="block";
                                document.getElementById('financialYear').style.display="none";
                                document.getElementById('submit').style.display="block";
                                document.getElementById("7").value="0";

                                typeOfselection="Date Wise";

                            }

                        }
                        function bodyLoadfunction(){
                            if(typeOfselection=='Financial Year Wise'){
                                document.getElementById('betweenDates').style.display="none";
                                document.getElementById('financialYear').style.display="block";
                                typeOfselection="Financial Year Wise";
                                document.getElementById('3').checked=true;
                            }
                            else if(typeOfselection=='Date Wise'){
                                document.getElementById('betweenDates').style.display="block";
                                document.getElementById('financialYear').style.display="none";
                                //document.getElementById("7").value="0";
                                typeOfselection="Date Wise";
                                document.getElementById('2').checked=true;
                            }
                            document.forms[0].elements["reptTypeSec"].value=typeOfselection;
                        }

                        function ShowDate()

                        {
                            var dt = new Date();

                            var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();

                            document.getElementById(5).value = d;



                        }
        </script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    </head>
    <body onload="ShowDate(),bodyLoadfunction();">
        <html:form action="/percentageWiseReport" >
            <html:hidden property="mode"/>
            <input type="hidden" name="reptTypeSec">
            <%-- <html:hidden property="financialYear" value="<%=fionance%>"/>--%>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

            <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                <tr>
                    <th colspan="2">
                        Percentage Wise Assessed Report
                    </th>
                </tr>
                <%--<tr>
                    <td>
                        District:<font color="red">*</font>
                    </td>
                    <td>
                        <html:select styleId="1" property="district_id"  >
                            <html:option  value="0">  --- Select ---  </html:option>
                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                    </td>
                </tr>--%>
                <tr>
                    <th colspan="2" align="center">
                        <html:radio property="typeOfSearch" styleId="2" value="Date Wise" onclick="changeSelection(this.value);" style="border:0">Date Wise</html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfSearch" styleId="3" value="Financial Year Wise" onclick="changeSelection(this.value);" style="border:0">Financial Year Wise</html:radio>
                    </th>
                </tr>
                <tr>
                    <td id="betweenDates" style="display: none;" width="60%" colspan="4">
                        <table  align="center" cellspacing="1" cellpadding="0" width="100%" border="0" class="inputform">

                            <tr>


                                <td>From Date<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="fromdate" styleId="4" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td>To Date<font color="red"><b>*</b></font></td>
                                <td>
                                    <html:text property="todate" styleId="5"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].todate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="4" align="center" id="submit"><input type="button" name="card" value="Submit" class="button" onclick="return insertDetails();">&nbsp;&nbsp;<input type="button" name="Reset" value="Reset" class="button" onclick="resetAllVlaues();"></th>
                            </tr>
                        </table>
                    </td>
                    <td id="financialYear" style="display: none;" width="60%" colspan="4">
                        <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" id="finance">
                            <tr>
                                <td> Finance Year wise Selection<font color="red"><b>*</b></font></td>
                                <td align="left">
                                    <html:select styleId="7" property="financialYearWise" style="width:150px;height:25px;font-size:11px;" >
                                        <html:option value="0">  --SELECT--  </html:option>

                                        <html:optionsCollection   property="financialYearList" label="finyear" value="finyear" />
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="4" align="center" id="submit"><input type="button" name="card" value="Submit" class="button" onclick="return insertFinance()">&nbsp;&nbsp;<input type="button" name="Reset" value="Reset" class="button" onclick="resetAllVlaues();"></th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table><br>
            <logic:present name="PecentageWiseData" scope="request">
                <%
                            session.setAttribute("PecentageWiseData", PecentageWiseData);%>
                <table  align="center" cellspacing="0" border="0" cellpadding="0" width="90%" >

                    <logic:present name="DateH">
                        <tr>
                            <td >

                                <font size="2"><b>Percentage Wise Assessed Report : Status as on: &nbsp;${fromdate} &nbsp;to &nbsp;${todate}</b></font>
                            </td>
                        </tr>

                    </logic:present>
                    <logic:present name="Finance">
                        <tr>
                            <td >

                                <font size="2"><b>Percentage Wise Assessed Report : Status as on:&nbsp;${FinancialYearWise}</b></font>
                            </td>
                        </tr>
                    </logic:present>

                    <tr>
                        <%--<td >

                            <font size="2"><b>District: ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </b></font>
                        </td>--%>
                        <logic:present name="district">
                            <td rowspan="2"><font size="2">
                                    District : ALL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal :${mandalname} ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                                </font> </td>
                            </logic:present>

                        <logic:present name="mandal">
                            <td rowspan="2"><font size="2">
                                    District : ${districtName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                                </font></td>
                            </logic:present>

                        <logic:iterate id="row" name="PecentageWiseData" length="1">
                            <logic:present name="village">
                                <td rowspan="2"><font size="2">
                                        District : ${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                                    </font></td>
                                </logic:present>
                            </logic:iterate>
                            <logic:iterate id="row" name="PecentageWiseData" length="1">
                                <logic:present name="habitation">
                                <td rowspan="2"><font size="2">
                                        District : ${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ${villageName}
                                    </font></td>
                                </logic:present>
                            </logic:iterate>
                            <logic:present name="district">
                            <td  style="text-align: center">
                                <a href="percentageWiseReport.do?mode=PecentageWiseDataPrint&FIR=${no.finace}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>

                            <td  style="text-align: center">
                                <a href="percentageWiseReport.xls?mode=excelWriting&FIR=${no.finace}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:present>
                        <logic:present name="mandal">
                            <td  style="text-align: center">
                                <a href="percentageWiseReport.do?mode=PecentageWiseDataPrint&FIR=${no.finace}&district_id=${row.districtId}&mandalname=${row.mandalname}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>

                            <td  style="text-align: center">
                                <a href="percentageWiseReport.xls?mode=excelWriting&FIR=${no.finace}&district_id=${row.districtId}&districtName=${districtName}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:present>
                        <logic:present name="village">
                            <td  style="text-align: center">
                                <a href="percentageWiseReport.do?mode=PecentageWiseDataPrint&FIR=${no.finace}&district_id=${row.districtId}&districtName=${districtName}&mandal_id=${row.mandalId}&mandalname=<%=request.getAttribute("mandalName")%>&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>

                            <td  style="text-align: center">
                                <a href="percentageWiseReport.xls?mode=excelWriting&FIR=${no.finace}&district_id=${row.districtId}&districtName=${districtName}&mandal_id=${row.mandalId}&mandalname=<%=request.getAttribute("mandalName")%>&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>>" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:present>
                        <logic:present name="habitation">
                            <td  style="text-align: center">
                                <a href="percentageWiseReport.do?mode=PecentageWiseDataPrint&FIR=${no.finace}&district_id=${row.districtId}&mandal_id=${row.mandalId}&mandalname=<%=request.getAttribute("mandalName")%>&village_id=${row.villageId}&villagename=<%=request.getAttribute("villageName")%>&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                            <td  style="text-align: center">
                                <a href="percentageWiseReport.xls?mode=excelWriting&FIR=${no.finace}&district_id=${row.districtId}&mandal_id=${row.mandalId}&districtName=${districtName}&mandalname=<%=request.getAttribute("mandalName")%>&village_id=${row.villageId}&villagename=<%=request.getAttribute("villageName")%>&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:present>

                    </tr>
                </table>

                <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">

                    <tr>
                        <th colspan="9">
                            Percentage Wise Assessed Report
                        </th>
                    </tr>
                    <tr>
                        <th rowspan="2">
                            S.No
                        </th>
                        <logic:present name="district">
                            <th rowspan="2">
                                District
                            </th>
                        </logic:present>
                        <logic:present name="mandal">
                            <th rowspan="2">
                                Mandal
                            </th>
                        </logic:present>
                        <logic:present name="village">
                            <th rowspan="2">
                                Village
                            </th>
                        </logic:present>
                        <logic:present name="habitation">
                            <th rowspan="2">
                                Habitation
                            </th>
                        </logic:present>

                        <th rowspan="2">
                            Assessed
                        </th>
                        <th colspan="3">
                            Rejected
                        </th>
                        <th rowspan="2">
                            Eligible(40% to 79%)
                        </th>
                        <th rowspan="2">
                            Eligible(80% to 100%)
                        </th>
                        <th rowspan="2">
                            Eligible(40% to 100%)
                        </th>
                    </tr>
                    <tr>

                        <th>
                            Before
                            Assessment
                            (No
                            visible
                            disb.)
                        </th>
                        <th>
                            After
                            Assessment
                            (<40%
                            disb.)
                        </th>
                        <th>Total<br>
                            Rejected
                        </th>

                    </tr>
                    <tr>
                        <td style="text-align: center">
                            (1)
                        </td>
                        <td style="text-align: center">
                            (2)
                        </td>
                        <td style="text-align: center">
                            (3)
                        </td>
                        <td style="text-align: center">
                            (4)
                        </td>
                        <td style="text-align: center">
                            (5)
                        </td>
                        <td style="text-align: center">
                            (6)
                        </td>
                        <td style="text-align: center">
                            (7)
                        </td>
                        <td style="text-align: center">
                            (8)
                        </td>
                        <td style="text-align: center">
                            (9)
                        </td>
                    </tr>
                    <logic:iterate id="row" name="PecentageWiseData">
                        <tr>
                            <td style="text-align: center">
                                <%=i++%>
                            </td>

                            <%-- <td >
                                 <a href="percentageWiseReport.do?mode=getPecentageWiseDistrictData&district_id=${row.districtId}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>">${row.districtname}</a>
                             </td> --%>
                            <logic:present name="district">
                                <td >
                                    <a href="percentageWiseReport.do?mode=getPecentageWiseData&district_id=${row.districtId}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>">${row.districtname}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td >
                                    <a href="percentageWiseReport.do?mode=getPecentageWiseData&district_id=${row.districtId}&mandal_id=${row.mandalId}&mandalname=${row.mandalname}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>">${row.mandalname}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td >
                                    <a href="percentageWiseReport.do?mode=getPecentageWiseData&district_id=${row.districtId}&mandal_id=${row.mandalId}&mandalname=${mandalName}&village_id=${row.villageId}&villagename=${row.villagename}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>">${row.villagename}</a>
                                </td>
                            </logic:present>
                            <logic:present name="habitation">
                                <td>
                                    ${row.habitationname}
                                </td>
                                <%-- <td >
                                     <a href="percentageWiseReport.do?mode=getPecentageWiseVillageData&district_id=${row.districtId}&mandal_id=${row.mandalId}&village_id=${row.mandalId}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%>">${row.habitationname}</a>
                                 </td>--%>
                            </logic:present>
                            <td style="text-align: center">
                                ${row.Assessed}
                            </td>
                            <td style="text-align: center">
                                ${row.directRejected}
                            </td>
                            <td style="text-align: center">
                                ${row.assessedRejected}
                            </td>
                            <td style="text-align: center">
                                ${row.rejected}
                            </td>
                            <td style="text-align: center">
                                ${row.eligible}
                            </td>
                            <logic:present name="district">
                                <td style="text-align: center">
                                    <a href="percentageWiseReport.xls?mode=excelWriting&individualexcel=individualexcel&district_id=${row.districtId}&districtName=${row.districtname}&fromdate=<%=request.getAttribute("fromdate")%>&todate=<%=request.getAttribute("todate")%>&typeOfSearch=<%=request.getAttribute("typeOfSearch")%>&FinancialYearWise=<%=request.getAttribute("FinancialYearWise")%> target="_blank">
                                       ${row.eligibleData}</a>
                                </td>
                            </logic:present>

                            <logic:present name="mandal">
                                <td style="text-align: center">
                                    ${row.eligibleData}
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td style="text-align: center">
                                    ${row.eligibleData}
                                </td>
                            </logic:present>
                            <logic:present name="habitation">
                                <td style="text-align: center">
                                    ${row.eligibleData}
                                </td>
                            </logic:present>
                            <td style="text-align: center">
                                ${row.eligibledata40to100}
                            </td>

                        </tr>

                    </logic:iterate>
                    <tr>
                        <td colspan="2">
                            <b>Total</b>
                        </td>
                        <td style="text-align: center">
                            <b>${row.totalAssessed}</b>

                        </td>
                        <td style="text-align: center">
                            <b> ${row.totalDirectRejected}</b>

                        </td>
                        <td style="text-align: center">
                            <b>${row.totalAssessedRejected}</b>

                        </td>
                        <td style="text-align: center">
                            <b>${row.totalRejected}</b>

                        </td>
                        <td style="text-align: center">
                            <b>${row.totalEligible}</b>

                        </td>
                        <td style="text-align: center">
                            <b>${row.totalEligibleData}</b>

                        </td>
                        <td style="text-align: center">
                            <b>${row.totaleligible40to80}</b>

                        </td>
                    </tr>

                </table>

            </logic:present>
        </html:form>

        <%--<logic:present name="finance">
            <script>
                document.getElementById('finance').style.disabled="true";
            </script>
        </logic:present>--%>
    </body>
</html>
