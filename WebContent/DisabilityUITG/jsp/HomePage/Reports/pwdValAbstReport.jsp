<%--
    Document   : pwdValAbstReport
    Created on : Mar 18, 2015, 12:01:06 AM
    Author     : 567999
--%>
<%@page import="java.util.ArrayList,java.util.Iterator,com.tcs.sadarem.util.CommonUtility" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%
    String fate = CommonUtility.checkNullObj(request.getAttribute("fdate"));
    String tate = CommonUtility.checkNullObj(request.getAttribute("tdate"));
    
    ArrayList dataList = (ArrayList)request.getAttribute("report");
    
    System.out.println("Data List : "+dataList);
%>
<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script>
            function getReport()
            {
                
                var d =document.forms[0];

                var a = d.fromdate.value;
                var b = d.todate.value;

                var fromtime = new Date()
                var totime = new Date()
                var currentdate = new Date();

                fromtime.setYear(a.substring(6,10));
                fromtime.setMonth((a.substring(3,5)-1),(a.substring(0,2)));
                fromtime.setDate(a.substring(0,2));

                totime.setYear(b.substring(6,10));
                totime.setMonth((b.substring(3,5)-1),(b.substring(0,2)));
                totime.setDate(b.substring(0,2));


                if(d.fromdate.value=="")
                {
                    alert("Please select From Date");
                    d.fromdate.focus();
                    return false;
                }else if(fromtime>currentdate){
                    alert("From date should be less than or equal to Today's Date");
                    d.fromdate.focus();
                    d.fromdate.value="";
                    return false;
                }else if(d.todate.value==""){
                    alert("Please select To Date");
                    d.fromdate.focus();
                    return false;
                }else if(totime>currentdate){
                    alert("To Date should be less than or equal to Today's Date");
                    d.todate.focus();
                    d.todate.value="";
                    return false;
                }else if(fromtime>totime){
                    alert("From date should be less than or equal to To date");
                    d.fromdate.focus();
                    d.fromdate.value="";
                    d.todate.value="";
                    return false;
                }else {
                    d.mode.value='getReport16';
                    d.submit();
                }

            }
       
            function defaultDates(){
                var currentTime = new Date()
                var month = currentTime.getMonth() + 1
                var day = currentTime.getDate()
                var year = currentTime.getFullYear()
                if(day.toString().length=="1"){
                    day = "0"+day;
                }
                if(month.toString().length=="1"){
                    month = "0"+month;
                }
                document.forms[0].todate.value= day+ "/" + month  + "/" + year;
                document.forms[0].fromdate.value=  "01/01/2010";
            }



        </script>
    </head>
    <logic:present name="date">
        <body>
        </logic:present>
        <logic:notPresent name="date">
        <body onload="defaultDates();">
        </logic:notPresent>
        <html:form action="/pwdValAbstReport.do" >
            <html:hidden property="mode"/>
            <table   align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                <tr>
                    <th colspan="4">
                        R.1.6 PwD Validation Abstract Rural Report
                    </th>
                </tr>
                <tr>
                    <td width="25%">From Date<font color="red"><b>*</b></font></td>
                    <td width="25%">
                        <html:text property="fromdate" styleId="4" readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                    <td width="25%">To Date<font color="red"><b>*</b></font></td>
                    <td width="25%">
                        <html:text property="todate" styleId="5"  readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].todate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>
                <tr>
                    <th colspan="4">
                        <input type="button" value="Get Report" onclick="return getReport();"/>
                    </th>
                </tr>
            </table>
            <br/>
            <table   align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                <tr>
                    <td colspan="10" style="text-align: right">
                        <a href="pwdValAbstReport.xls?mode=getReport16Excel&fdate=<%=fate%>&tdate=<%=tate%>" target="_blank">
                            Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> 
                    </td>
                </tr>
                <tr>
                    <th rowspan="2" width="3%">
                        S No
                    </th>
                    <th rowspan="2"  width="12%">
                        District
                    </th>
                     <th rowspan="2"  width="8%">
                        Total Assessed
                    </th>
                    <th rowspan="2"  width="8%">
                        Total Validation  Done
                    </th>
                    <th rowspan="2"  width="8%">
                        AADHAR Not  Tagged
                    </th>
                    <th colspan="3">
                        PwD
                    </th>
                   <%-- <th colspan="2">
                        PMJDY Details
                    </th>--%>
                    <th colspan="2">
                        AASARA
                    </th>
                </tr>
                <tr>
                    <th  width="8%">
                        Alive
                    </th>
                    <th width="8%">
                        Dead
                    </th>
                    <th width="8%">
                        Tagged to SHG Group
                    </th>
                    <%--<th width="8%">
                        Not Having Account
                    </th>
                    <th width="8%">
                        Having Account
                    </th>--%>
                    <th width="8%">
                        Not Covered
                    </th>
                    <th width="8%">
                        Covered
                    </th>
                </tr>
                <logic:present name="report" scope="request">
                    <logic:iterate id="row" name="report" scope="request">
                        <tr>

                            <td style="text-align: center">
                                ${row.sno}
                            </td>
                            <td style="text-align: left">
                                ${row.disname}
                            </td>
                             <td style="text-align: center">
                                ${row.totalassessed}
                            </td>
                            <td style="text-align: center">
                                ${row.total}
                            </td>
                            <td style="text-align: center">
                                ${row.taged}
                            </td>
                            <td style="text-align: center">
                                ${row.alive}
                            </td>
                            <td style="text-align: center">
                                ${row.dead}
                            </td>
                            <td style="text-align: center">
                                ${row.tagshg}
                            </td>
                           <%-- <td style="text-align: center">
                                ${row.noaccount}
                            </td>
                            <td style="text-align: center">
                                ${row.haveaccount}
                            </td>--%>
                            <td style="text-align: center">
                                ${row.notcover}
                            </td>
                            <td style="text-align: center">
                                ${row.conver}
                            </td>
                            
                        </tr>
                    </logic:iterate>
                        <tr>
                        <th colspan="2">Total</th>
                         <th style="text-align:center">${row.t11}</th>
                        <th style="text-align:center">${row.t2}</th>
                        <th style="text-align:center">${row.t3}</th>
                        <th style="text-align:center">${row.t4}</th>
                        <th style="text-align:center">${row.t5}</th>
                        <th style="text-align:center">${row.t6}</th>
                       <%-- <th style="text-align:center">${row.t7}</th>
                        <th style="text-align:center">${row.t8}</th>--%>
                        <th style="text-align:center">${row.t9}</th>
                        <th style="text-align:center">${row.t10}</th>
                        
                    </tr>
                </logic:present>
            </table>
        </html:form>
    </body>
</html:html>



