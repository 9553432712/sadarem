<%-- 
    Document   : DirectorReport
    Created on : 1 Aug, 2014, 6:28:50 PM
    Author     : 484898
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO" %>
<%@page import="org.bf.disability.Constants.CommonConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <title>SADAREM</title>
        <%
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");
                    int totalApplied = 0, totalAssessed = 0, rejected = 0;
                    int eligible = 0, oh = 0, vi = 0;
                    int hi = 0, mr = 0, mi = 0, md = 0, total = 0;
                    if (stateReport != null) {
                        Iterator iter = stateReport.iterator();
                        while (iter.hasNext()) {
                            ReportDTO totalDTO = (ReportDTO) iter.next();
                            totalApplied = totalApplied + totalDTO.getTotalApplied();
                            totalAssessed = totalAssessed + totalDTO.getTotalAssessed();
                            rejected = rejected + totalDTO.getRejected();
                            eligible = eligible + totalDTO.getEligible();
                            oh = oh + totalDTO.getOh();
                            vi = vi + totalDTO.getVi();
                            hi = hi + totalDTO.getHi();
                            mr = mr + totalDTO.getMr();
                            mi = mi + totalDTO.getMi();
                            md = md + totalDTO.getMd();
                            total = total + totalDTO.getTotal();
                        }
                    }

        %>

        <script>
            var typeOfselection="";
            var reportType="";
            <%if (request.getAttribute("typeOfSearchValue") != null) {%>
                typeOfselection='<%=request.getAttribute("typeOfSearchValue")%>';
            <%}%>
            <%if (request.getAttribute("reportTypeValue") != null) {%>
                reportType='<%=request.getAttribute("reportTypeValue")%>';
            <%}%>

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


                    with (thisform)
                    {
                        if(document.getElementById('3').checked){
                            if (validate_required(financialYear,"Financial Year Wise Selection must be selected!")==false)
                            {
                                financialYearWise.focus();
                                return false;
                            }
                        }
                        else if(document.getElementById('2').checked){
                            if (validate_required(year,"Year must be selected!")==false)
                            {
                                year.focus();
                                return false;
                            }else if (validate_required(month,"Month must be selected!")==false)
                            {
                                month.focus();
                                return false;
                            }
                        }else if(document.getElementById('1').checked){
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


                function changeSelection(id){
                    if(id=='FinancialYear'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["fromdate"].value="";
                        document.forms[0].elements["todate"].value="";
                        document.forms[0].elements["month"].value="0";
                        document.forms[0].elements["year"].value="0";
                     //   document.getElementById('dt').style.display="none";
                     //   document.getElementById('mnt').style.display="none";
                     //   document.getElementById('yr').style.display="";
                        // document.getElementById("7").value="2012-13" ;
                        typeOfselection="FinancialYear";
                    }else if(id=='Month'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="block";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["fromdate"].value="";
                        document.forms[0].elements["todate"].value="";
                        document.forms[0].elements["financialYear"].value="0";
                    //    document.getElementById('dt').style.display="none";
                     //   document.getElementById('mnt').style.display="";
                     //   document.getElementById('yr').style.display="none";
                        // document.getElementById("7").value="2012-13" ;
                        typeOfselection="Month";
                    }else if(id=='Date'){
                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('reportType').style.display="block";
                        document.forms[0].elements["month"].value="0";
                        document.forms[0].elements["year"].value="0";
                        document.forms[0].elements["financialYear"].value="0";
                     //   document.getElementById('dt').style.display="";
                     //   document.getElementById('mnt').style.display="none";
                     //   document.getElementById('yr').style.display="none";
                       
                        //document.getElementById("7").value="0";
                        typeOfselection="Date";
                    } if(id=='Camp'){
                        document.getElementById('campwise').style.display="block";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        reportType="Camp";
                    }else if(id=='District'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        reportType="District";
                    }else if(id=='district'){
                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        reportType="District";
                    }

                }

                function getMonths(){
                    document.forms[0].mode.value = "unspecified";
                    document.forms[0].submit();
                }

                function bodyLoadfunction(){

                    if(typeOfselection=='FinancialYear'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('financialYear').style.display="block";
                        document.getElementById('reportType').style.display="block";
                    //    document.getElementById('dt').style.display="none";
                    //    document.getElementById('mnt').style.display="none";
                    //    document.getElementById('yr').style.display="";
                        typeOfselection="FinancialYear";
                        document.getElementById('3').checked=true;
                    }else if(typeOfselection=='Month'){
                        document.getElementById('betweenDates').style.display="none";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="block";
                        document.getElementById('reportType').style.display="block";
                  //      document.getElementById('dt').style.display="none";
                  //      document.getElementById('mnt').style.display="";
                  //      document.getElementById('yr').style.display="none";
                        typeOfselection="Date Wise";
                        document.getElementById('2').checked=true;
                    }else if(typeOfselection=='Date'){
                        document.getElementById('betweenDates').style.display="block";
                        document.getElementById('financialYear').style.display="none";
                        document.getElementById('month').style.display="none";
                        document.getElementById('reportType').style.display="block";
                 //       document.getElementById('dt').style.display="";
                  //      document.getElementById('mnt').style.display="none";
                  //      document.getElementById('yr').style.display="none";
                        //document.getElementById("7").value="0";
                        typeOfselection="Date";
                        document.getElementById('1').checked=true;
                    } if(reportType=='Camp'){
                        document.getElementById('campwise').style.display="block";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        document.getElementById('10').checked=true;
                        reportType="Camp";
                    }else if(reportType=='District'){

                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        document.getElementById('9').checked=true;
                        reportType="District";
                    }else if(reportType=='Mandal'){

                        document.getElementById('campwise').style.display="none";
                        document.getElementById('submitButton').style.display="block";
                        //document.getElementById("7").value="0";
                        document.getElementById('9').checked=true;
                        reportType="Mandal";
                    }
                  
                }

        </script>
    </head>
    <body onload="bodyLoadfunction();">
        <html:form styleId="fm1" action="/directorReport.do?mode=getResultsForDirector" method="post" onsubmit="return validate_form(this);">
            <html:hidden property="mode"/>

            <br/>


            <table  align="center" cellspacing="1" cellpadding="0" width="90%" border="0" bgcolor="white" class="inputform" >
                <tr><th colspan="4">Assessment Abstract Report</th></tr>

                <tr>
                    <td colspan="4">
                        <html:radio property="typeOfSearch" styleId="1" value="Date" onclick="changeSelection(this.value);" style="width=125px">Date </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfSearch" styleId="2" value="Month" onclick="changeSelection(this.value);" style="width=125px">Month </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="typeOfSearch" styleId="3" value="FinancialYear" onclick="changeSelection(this.value);" style="width=125px">Financial Year </html:radio>
                    </td>
                </tr>

                <tr id="betweenDates" style="display: none;"  >
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

                <%--<td >
                    <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" >--%>
                <tr id="month" style="display: none;"  >
                    <td> Year <font color="red"><b>*</b></font></td>
                    <td >
                        <html:select styleId="6" property="year"  onchange="getMonths(this.value)">
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="yearList" label="year" value="year"/>

                        </html:select>
                    </td>

                    <td> Month <font color="red"><b>*</b></font></td>
                    <td >
                        <html:select styleId="7" property="month"  >
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="monthList" label="month" value="id"/>
                        </html:select>
                    </td>
                </tr>
                <%--</table>
            </td>--%>
                <%-- <td >
                     <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" >--%>
                <tr  id="financialYear" style="display: none;" >
                    <td colspan="4"> Financial Year <font color="red"><b>*</b></font>
                        <html:select styleId="8" property="financialYear" >
                            <html:option value="0">  --SELECT--  </html:option>
                            <html:optionsCollection property="financialYearList" label="finyear" value="finyear"/>
                        </html:select>
                    </td>
                </tr>
                <%--   </table>
               </td>
           </tr>--%>
                <tr>
                    <td id="reportType" style="display: none;"  colspan="4" >
                        <html:radio property="reportType" styleId="9" value="District" onclick="changeSelection(this.value);" style="width=125px">District Wise </html:radio>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:radio property="reportType" styleId="10" value="Camp" onclick="changeSelection(this.value);" style="width=125px">Camp Wise </html:radio>
                    </td>
                </tr>
                <tr  id="campwise" style="display: none;" bgcolor="white">

                    <td>
                        District  <font color="red">*</font>
                    </td>
                    <td>
                        <html:select property="district_id" onchange="getCamps()">
                            <html:option value="0">-- Districts --</html:option>
                            <html:optionsCollection property="districtlist" label="district_name" value="district_id"/>
                        </html:select>
                    </td>

                    <td> Camps <font color="red">*</font></td>
                    <td>
                        <html:select property="campId">
                            <html:option value="0">-- Camps --</html:option>
                            <html:option value="All">All</html:option>
                            <html:optionsCollection property="campList" label="campName" value="campId"/>
                        </html:select>
                    </td>

                </tr>
                <tr  id="submitButton" style="display: none;">
                    <th colspan="4" align="center"><input type="Submit" name="card" value="Submit" class="button" >&nbsp;&nbsp;</th>
                </tr>
            </table> <br>

            <!-- Content Starts -->
            <logic:present name="stateReport" scope="request">

                <%
                            session.setAttribute("stateReport", stateReport);%>
                <logic:present name="heading" scope="request">
                    <table cellspacing="0" border="0" cellpadding="0" width="96%" align="center" class="inputform">
                        <td align="right" colspan="5">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                        <td align="right" id="dt">
                            <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0">
                            <a href="directorReport.do?mode=excel&heading=${heading}"  target="_blank">
                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        </td>
                        
                    </table>
                </logic:present>

                <table cellspacing="1" border="0" cellpadding="0" width="96%" align="center" class="inputform">

                    <tr>
                        <th rowspan="2" >S.No</th>
                        <logic:present name="camp">
                            <th  rowspan="2">Medical Board</th>
                            <th  rowspan="2">MedicalBoard Address</th>
                        </logic:present>
                        <logic:present name="mandal">
                            <th  rowspan="2" >Mandal</th>
                        </logic:present>
                        <logic:present name="district">
                            <th  rowspan="2" >District</th>

                        </logic:present>

                        <th colspan="4">PwDs ASSESSMENT STATUS</th>
                        <th colspan="7">PwDs PENSIONS COVERED</th>
                    </tr>
                    <logic:present name="district">
                        <tr>
                            <th >Total Applied</th>
                            <th >Assessed</th>
                            <th >Rejected</th>
                            <th  >Eligible (>40%)<br/>(4-5)</th>
                            <th >Ortho</th>
                            <th >Visual</th>
                            <th >Speech & Hearing</th>
                            <th >Mental Retardation</th>
                            <th >Mental Illness</th>
                            <th >Multiple Disability</th>
                            <th >Total Pensioners<br/>(7+8+9+10+11+12)</th>
                        </tr>
                        <tr>
                            <th>(1)</th>
                            <th>(2)</th>
                            <th>(3)</th>
                            <th>(4)</th>
                            <th>(5)</th>
                            <th>(6)</th>
                            <th>(7)</th>
                            <th>(8)</th>
                            <th>(9)</th>
                            <th>(10)</th>
                            <th>(11)</th>
                            <th>(12)</th>
                            <th>(13)</th>
                        </tr>
                    </logic:present>
                    <logic:present name="mandal">
                        <tr>
                            <th >Total Applied</th>
                            <th >Assessed</th>
                            <th >Rejected</th>
                            <th  >Eligible (>40%)<br/>(4-5)</th>
                            <th >Ortho</th>
                            <th >Visual</th>
                            <th >Speech & Hearing</th>
                            <th >Mental Retardation</th>
                            <th >Mental Illness</th>
                            <th >Multiple Disability</th>
                            <th >Total Pensioners<br/>(7+8+9+10+11+12)</th>
                        </tr>
                        <tr>
                            <th>(1)</th>
                            <th>(2)</th>
                            <th>(3)</th>
                            <th>(4)</th>
                            <th>(5)</th>
                            <th>(6)</th>
                            <th>(7)</th>
                            <th>(8)</th>
                            <th>(9)</th>
                            <th>(10)</th>
                            <th>(11)</th>
                            <th>(12)</th>
                            <th>(13)</th>
                        </tr>
                    </logic:present>
                    <logic:present name="camp">
                        <tr>
                            <th >Total Applied</th>
                            <th >Assessed</th>
                            <th >Rejected</th>
                            <th  >Eligible (>40%)<br/>(5-6)</th>
                            <th >Ortho</th>
                            <th >Visual</th>
                            <th >Speech & Hearing</th>
                            <th >Mental Retardation</th>
                            <th >Mental Illness</th>
                            <th >Multiple Disability</th>
                            <th >Total Pensioners<br/>(8+9+10+11+12+13)</th>
                        </tr>
                        <tr>
                            <th>(1)</th>
                            <th>(2)</th>
                            <th>(3)</th>
                            <th>(4)</th>
                            <th>(5)</th>
                            <th>(6)</th>
                            <th>(7)</th>
                            <th>(8)</th>
                            <th>(9)</th>
                            <th>(10)</th>
                            <th>(11)</th>
                            <th>(12)</th>
                            <th>(13)</th>
                            <th>(14)</th>
                        </tr>
                    </logic:present>
                    <% int i = 0;%>

                    <logic:iterate id="modify" name="stateReport" scope="request">

                        <tr >
                            <td><%=++i%></td>
                            <logic:present name="camp">
                                <td width="200px">
                                    ${modify.campName}
                                </td>
                                <td>
                                    ${modify.campVenue}
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td>
                                    ${modify.mandal_name}
                                </td>
                            </logic:present>
                            <logic:present name="district">
                                <td>
                                    <a href="./directorReport.do?mode=getResultsForDirector&districtId=${modify.district_id}&srchType=<%=request.getParameter("typeOfSearch")%>&repType=<%=request.getParameter("reportType")%>&yr=<%=request.getParameter("year")%>&mnt=<%=request.getParameter("month")%>&frDate=<%=request.getParameter("fromdate")%>&tDate=<%=request.getParameter("todate")%>&finYear=<%=request.getParameter("financialYear")%>">${modify.district_name}</a>
                                </td>
                            </logic:present>

                            <td style="text-align: center"> ${modify.totalApplied}</td>
                            <td style="text-align: center"> ${modify.totalAssessed}</td>
                            <td style="text-align: center"> ${modify.rejected}</td>
                            <td style="text-align: center"> ${modify.eligible}</td>
                            <td style="text-align: center"> ${modify.oh}</td>
                            <td style="text-align: center"> ${modify.vi}</td>
                            <td style="text-align: center"> ${modify.hi}</td>
                            <td style="text-align: center"> ${modify.mr}</td>
                            <td style="text-align: center"> ${modify.mi}</td>
                            <td style="text-align: center"> ${modify.md}</td>
                            <td style="text-align: center"> ${modify.total}</td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <logic:present name="camp">
                            <th  colspan="3">Total</th>
                        </logic:present>
                        <logic:present name="district">
                            <th  colspan="2">Total</th>
                        </logic:present>
                        <logic:present name="mandal">
                            <th  colspan="2">Total</th>
                        </logic:present>
                        <th><%=totalApplied%></th>
                        <th><%=totalAssessed%></th>
                        <th><%=rejected%></th>
                        <th><%=eligible%></th>
                        <th><%=oh%></th>
                        <th><%=vi%></th>
                        <th><%=hi%></th>
                        <th><%=mr%></th>
                        <th><%=mi%></th>
                        <th><%= md%></th>
                        <th><%= total%></th>
                    </tr>
                </table>
            </logic:present>

        </html:form>
    </body>

    <% if (session.getAttribute("roleId").toString().equals(CommonConstants.COLLECTORROLE)) {%>
    <script>
        document.getElementById("dist").disabled=true;
    </script>
    <%} else {%>
    <script>
        document.getElementById("dist").disabled=false;
    </script>
    <%}%>

    <script src="./DisabilityUITG/js/jquery-min.js"></script>
    <logic:present name="camp">
        <script>
            document.getElementById("cmp").style.display="";
            document.getElementById("cmpval").style.display="";
            document.getElementById("data").style.display="";           
        </script>
    </logic:present>
    <logic:present name="cmpStat">
        <script>
            document.getElementById("campwise").style.display="";
            document.getElementById("submitButton").style.display="";
        </script>
    </logic:present>
    <script>
        // The below two methods are for getting the table data

      

        function getCamps() {
            document.forms[0].elements["mode"].value="unspecified";
            document.forms[0].submit();
        }
    </script>

</html>
