<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
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
            String pensionphase = (String) request.getParameter("pensionPhase");
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
        <script src="./DisabilityUITG/js/Ajax.js"></script>
        <script src="./DisabilityUITG/js/Validation.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <title>SADAREM</title>

        <script language="JavaScript">
            
            
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

                if (document.forms[0].pensionPhase.value=="")
                {alert("Pension Phase must be selected!");
                    document.forms[0].pensionPhase.focus();
                    return false
                }

                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }
        </script>


    </head>
    <body onload="ShowDate()">
        <center>


            <html:form  action="/AppellateReport"  method="post"  >
                <input type="hidden" name="mode"/>
                <input type="hidden" name="districtName"/>
                <input type="hidden" name="mandalName"/>
                <input type="hidden" name="villageName"/>
                <input type="hidden" name="habitationName"/>
                <input type="hidden" name="qualificationName"/>

                <table  align="center" cellspacing="0" cellpadding="0" width="90%" class="inputform" border="1">
                    <tr>
                        <th colspan="8">R2.3 &nbsp; District wise Reassessed PWD's Cumulative Report</th>
                    </tr>
                    <tr>
                        <td>
                            <table  align="center" cellspacing="0" cellpadding="0" width="100%" class="inputform" border="0">

                                <tr>
                                    <td align="left"   colspan="2">Pension Phase<font color="red"><b>*</b></font>

                                        <html:select styleId="2" property="pensionPhase" >
                                            <html:option value="">  --SELECT--   </html:option>
                                            <html:option value="1">PhaseI</html:option>
                                            <html:option value="2">PhaseII</html:option>

                                            <html:option value="4">PhaseIV</html:option>
                                            <html:option value="5">All</html:option>
                                        </html:select>
                                    </td>
                                    <td  colspan="2">From Date<font color="red"><b>*</b></font>
                                        <html:text property="fromdate" readonly="true" size="12" value="27/08/2011"/>
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
                                    <tr align="center">
                                        <td  colspan="8" style="text-align: center;">
                                        <html:button property="but" onclick="getDetails()" value="Submit"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </table>
                <br>
                <input type="hidden" name="mode"/>
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <input type="hidden" name="habitationName"/>
                    <input type="hidden" name="qualificationName"/>

                            <%if (msg != null) {%>
                             <p id="errmsg"><%=msg%>  </p>
                            <%}%>



                <logic:present name="empWiseDetails">
                    <logic:empty name="empWiseDetails">
                        <p id="errmsg">No Data</p>
                                </logic:empty>
                            </logic:present>

                <logic:notEmpty name="empWiseDetails">
                    
                    <%--<div style="overflow: auto;width: 900px;height: 700px">--%>
                        <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
                           
                            <tr>

                                <logic:iterate name="empWiseDetails" id="row" length="1">
                                    <logic:present name="mandal">
                                        <th >
                                            <a href="AppellateReport.do?mode=getDetails&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                        </th>
                                    </logic:present>
                                    <logic:present name="village">
                                        <th>
                                            <a href="AppellateReport.do?mode=getDetails&district_id=${row.district_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                        </th>
                                    </logic:present>

                                    <logic:present name="habitation">
                                        <th>
                                            <a href="AppellateReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> Back</a>
                                        </th>
                                    </logic:present>
                                </logic:iterate>

                                <logic:present name="names">
                                    <th style="text-align: center" colspan="20">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>

                            </tr>
                             <tr>
                                <th colspan="18">
                                    Disability Report
                                </th>
                            </tr>
                            <tr>
                                <th align="center"  rowspan="2">S.No</th>
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
                                <th align="center"  rowspan="2">Total Registered</th>
                                <th align="center"  rowspan="2">Total Assessed </th>
                                <th align="center"  rowspan="2">Total Eligible</th>
                                <th align="center"  rowspan="2">Total Rejected</th>
                                <th align="center"  colspan="2" >Locomotor</th>
                                <th align="center"  colspan="2" >Visual Impairment</th>
                                <th align="center"  colspan="2" >Hearing Impairment</th>
                                <th align="center"  colspan="2" >Mental Retardation</th>
                                <th align="center"  colspan="2" >Mental Illness</th>
                                <th align="center"  colspan="2" >Multiple Disability</th>

                            </tr>

                            <tr>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                                <th align="center"  >Eligible</th>
                                <th align="center"  >Rejected</th>

                            </tr>

                            <% int j = 1;%>


                            <logic:iterate name="empWiseDetails" id="row">



                                <bean:define id="fDate" value="${row.fDate}"/>
                                <bean:define id="tDate" value="${row.tDate}"/>
                               
                                <tr>
                                    <td  align="center">
                                        <%=i++%>
                                    </td>
                                    <logic:present name="district">
                                        <td >
                                            <a href="AppellateReport.do?mode=getDetails&district_id=${row.district_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}"> ${row.name}</a>
                                        </td>
                                    </logic:present>
                                    <logic:present name="mandal">
                                        <td >
                                            <a href="AppellateReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}"> ${row.name}</a>
                                        </td>
                                    </logic:present>
                                    <logic:present name="village">
                                        <td>
                                            <a href="AppellateReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}"> ${row.name}</a>
                                        </td>
                                    </logic:present>

                                    <logic:present name="habitation">
                                        <td>
                                            ${row.name}
                                        </td>
                                    </logic:present>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Total Registred&disability=0&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')"> ${row.treg}</a>
                                    </td>
                                    <td  align="center">
                                        ${row.tass}
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Total Eligible&disability=0&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')"> ${row.teli}</a>
                                    </td>
                                    <td  align="center">
                                        ${row.trej}
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Locomotor-Eligible&disability=1&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')"> ${row.loe} </a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Locomotor-Rejected&disability=1&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.lor}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Visual-Eligible&disability=2&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.ve}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Visible-Rejected&disability=2&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.vr}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Hearing-Eligible&disability=3&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.he}</a>
                                    </td> <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Hearing-Rejected&disability=3&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.hr}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Mental Retradation-Eligible&disability=4&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.me}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Mental Retradation-Rejected&disability=4&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.mr}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=MentalIllness-Eligible&disability=5&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.mie}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=MentalIllness-Rejected&disability=5&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.mir}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Multiple Disability-Eligible&disability=6&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.mue}</a>
                                    </td>
                                    <td  align="center">
                                        <a href="javascript:void(0);" onclick ="window.open('appealReportPersonal.do?status=getpersonalDetails&districtCode=${row.district_id}&mandalCode=${row.mandal_id}&villageCode=${row.village_id}&fromDate=${row.fDate}&toDate=${row.tDate}&disabilityStatus=Multiple Disability-Rejected&disability=6&baseDistrict=${row.district_id}&baseMandal=${row.mandal_id}&baseVillage=${row.village_id}&basehab=${row.hab_id}&pensionPhase=${row.pensionPhase}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.mur}</a>
                                    </td>


                                </tr>
                                


                            </logic:iterate>


                        </table>
                         <%--</td></tr></table><br/>--%>
                        
                        <table align="center">
                            <logic:present name="district">
                                <logic:iterate name="empWiseDetails" id="row" length="1">
                                    <td >
                                        <a href="AppellateReport.xls?mode=getDetails&returnType=Excel&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                    </td>
                                    <td >
                                        <a href="AppellateReport.do?mode=getDetails&returnType=Print&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </logic:iterate>
                            </logic:present>
                            <logic:present name="mandal">
                                <logic:iterate name="empWiseDetails" id="row" length="1">
                                    <td >
                                        <a href="AppellateReport.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                    </td>
                                    <td >
                                        <a href="AppellateReport.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </logic:iterate>
                            </logic:present>
                            <logic:present name="village">
                                <logic:iterate name="empWiseDetails" id="row" length="1">
                                    <td>
                                        <a href="AppellateReport.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                    </td>
                                    <td>
                                        <a href="AppellateReport.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </logic:iterate>
                            </logic:present>

                            <logic:present name="habitation">
                                <logic:iterate name="empWiseDetails" id="row" length="1">
                                    <td>
                                        <a href="AppellateReport.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                    </td>
                                    <td>
                                        <a href="AppellateReport.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&pensionPhase=${row.pensionPhase}&fromdate=${row.fDate}&todate=${row.tDate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </logic:iterate>
                            </logic:present>
                        </logic:notEmpty>
                    </table>

               <%-- </div>--%>
            </html:form></center>
    </body>
</html:html>