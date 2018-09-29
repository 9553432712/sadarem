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
            function insertDetails(){
                if(document.forms[0].district_id.value=="0"){
                    alert("Please Select District");
                    document.forms[0].district_id.focus();
                    return false;
                }
                else if(document.forms[0].fromdate.value==""){
                    alert("Please Select From Date");
                    document.forms[0].district_id.focus();
                    return false;

                }else if(document.forms[0].todate.value==""){
                    alert("Please Select Todate");
                    document.forms[0].district_id.focus();
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
                if(document.forms[0].district_id.value=="0"){
                    alert("Please Select District");
                    document.forms[0].district_id.focus();
                    return false;

                }
                else if(document.forms[0].financialYearWise.value=="0"){
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
        </script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    </head>
    <body onload="window.print();">
        <html:form action="/percentageWiseReport" >
            <html:hidden property="mode"/>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

            <logic:present name="PecentageWiseData" scope="request">
                <%
                            session.setAttribute("PecentageWiseData", PecentageWiseData);%>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">

                    <logic:present name="DateH">
                        <tr>
                            <td colspan="9">

                                <font size="2"><b>Percentage Wise Assessed Report : Status as on: &nbsp;${fromdate} &nbsp;to &nbsp;${todate}</b></font>
                            </td>
                        </tr>

                    </logic:present>
                    <logic:present name="Finance">
                        <tr>
                            <td colspan="9">

                                <font size="2"><b>Percentage Wise Assessed Report : Status as on:&nbsp;Finance Year:&nbsp;${FinancialYearWise}</b></font>
                            </td>
                        </tr>
                    </logic:present>
                    <tr>
                        <%--<td colspan="8">

                            <font size="2"><b>District: ${districtName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </b></font>
                        </td>--%>

                        <logic:present name="district">
                            <td colspan="9">
                                District : ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                            </td>
                        </logic:present>

                        <logic:present name="mandal">
                            <td colspan="9">
                                District : ${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ALL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                            </td>
                        </logic:present>

                        <logic:iterate id="row" name="PecentageWiseData" length="1">
                            <logic:present name="village">
                                <td colspan="9">
                                    District : ${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ALL
                                </td>
                            </logic:present>
                        </logic:iterate>
                        <logic:iterate id="row" name="PecentageWiseData" length="1">
                            <logic:present name="habitation">
                                <td colspan="9">
                                    District : ${districtName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mandal : ${mandalName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Village : ${villageName}
                                </td>
                            </logic:present>
                        </logic:iterate>
                    </tr>
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
                            <logic:present name="district">
                            <td >
                                ${row.districtname}
                            </td>
                        </logic:present>
                        <logic:present name="mandal">
                            <td >
                                ${row.mandalname}
                            </td>
                        </logic:present>
                        <logic:present name="village">
                            <td >
                                ${row.villagename}
                            </td>
                        </logic:present>
                        <logic:present name="habitation">
                               <td >
                                ${row.habitationname}
                            </td>

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
                            <td style="text-align: center">
                                ${row.eligibleData}
                            </td>
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
                    <%-- <tr>
                         <td colspan="8">
                             <a href="percentageWiseReport.do?mode=PecentageWiseDataPrint" target="_blank">
                         Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                         </td>
                     </tr>--%>
                </table>

            </logic:present>
        </html:form>
    </body>
</html>
