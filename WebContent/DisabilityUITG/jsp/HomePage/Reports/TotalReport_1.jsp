<%--
    Document   : TotalReport13
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
        <title>SADAREM</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script language="JavaScript">
            
            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

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
 function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }

        </script>

    </head>
    <body onload="ShowDate()">


        <html:form  action="TotalReport1.do"  method="post" >

            <input type="hidden" name="mode"/>
            <input type="hidden" name="districtName"/>
            <input type="hidden" name="mandalName"/>
            <input type="hidden" name="villageName"/>
            <input type="hidden" name="habitationName"/>
            <input type="hidden" name="qualificationName"/>
            <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">
                <tr>
                    <th colspan="6">A3.1 General Profile of Eligible PwDs (All Disabilities) </th>
                </tr>

                <tr>
                    <td>
                <table  align="center" cellspacing="1" cellpadding="0" width="100%">
                    <tr>
                        <td valign="middle"  width="8%">District</td>
                        <td align="left" valign="middle">
                            <html:select styleId="1" property="district_id" onchange="getData()" style="height:25px;">
                                                    <html:option value="0">--ALL--</html:option>
                                                    <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                            </html:select>
                        </td>
                        <td  valign="middle" width="8%">Mandal</td>
                        <td align="left" valign="middle">
                            <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
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

                        <td  colspan="2">From Date<font color="red"><b>*</b></font>
                            <html:text property="fromdate" readonly="true" size="12" />
                            <a  href="javascript:show_calendar('forms[0].fromdate');"
                                onmouseover="window.status='Date Picker';return true;"
                                onmouseout="window.status='';return true;">
                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                        </td>
                        <td  colspan="2">To Date<font color="red"><b>*</b></font>
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
        <tr>  <th align="center"><html:button property="but" onclick="getDetails()" value="Submit"/>
                        </th></tr>
    </table>


<br>
<table width="99%" border="0" cellspacing="0" cellpadding="10" align="center" class="inputform"><tr align="center"><td>
            <%if (msg != null) {%>
            <font color="red"><%=msg%></font>
            <%}%>

        </td></tr></table>



<logic:present name="Totalcount1">
    <logic:empty name="Totalcount1">
        <table align="center">  <tr>
                <td  align="center" valign="top">
                    <table  align="center">
                        <tr><td  align="center" colspan="11"><font color="red"><b>No Data</b></font></td></tr>
                    </table></td></tr></table>
                </logic:empty>
            </logic:present>
            <logic:notEmpty name="Totalcount1">

    <logic:iterate name="Totalcount4" id="row">
        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">


            <tr>
                <th align="center"  >&nbsp;</th>
                <th align="center">&nbsp;</th>
                <th   align="center">&nbsp;</th>

                <th align="center"  rowspan="1" colspan="2">Male</th>
                <th align="center"   rowspan="1" colspan="2">Female</th>
                <th align="center"   rowspan="1" colspan="2">Total</th>



            </tr>
            <tr>
                <th  align="center" width="4">No</th>
                <th  align="center" width="31%">Category</th>
                <th   width="17%">SubType</th>

                <th width="8%"  align="center">No</th>
                <th width="8%" align="center">%</th>
                <th width="8%"  align="center">No</th>
                <th width="8%"  align="center">%</th>
                <th width="8%"  align="center">No</th>
                <th width="8%" align="center">%</th>
            </tr>

        </table>





        <bean:define id="fDate" value="${row.fDate}"/>
        <bean:define id="tDate" value="${row.tDate}"/>









        <table align="center" cellspacing="1" border="0" cellpadding="0"  class="inputform" width="90%" >
            <tr>

                <td align="center"  width="4%"  rowspan="2"  >1</td></tr>
            <tr>   <td align="left" width="31%">Number of the Person with Disability (As per the SADAREM)</td>



                <td  align="left"  width="17%">TotalCategories</td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r57}</td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.tom} </td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r59}</td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.tof}</td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r57+row.r59}</td>
                <td width="8%"  style="text-align: center"  style="text-align: center">${row.tom+row.tof}</td></tr>


        </table>

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"  width="90%" >

            <tr>

                <td align="center"  width="4%" ></td>
                <td  align="left" width="31%" rowspan="8">Number of the person with Disability(Category Wise)</td>
                <td  align="left"  width="17%">Locomotor </td>
                <td width="8%"  style="text-align: center" style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r2}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r3}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r4}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1+row.r3}</a></td>
                <td width="8%"  style="text-align: center"  style="text-align: center">${row.s1}</td></tr>
            <tr><td></td><td  align="left"  width="17%">Visual Impairment</td>
                <td width="8%"  style="text-align: center" style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r6}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r7}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r8}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5+row.r7}</a></td>
                <td width="8%"  style="text-align: center"  style="text-align: center">${row.s2}</td></tr>
            <tr> <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%"  >2</td>
                <td  align="left"  width="17%">Hearing Impairment</td>
                <td width="8%"  style="text-align: center" style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r10}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r11}</a></td>
                <td width="8%"  style="text-align: center" style="text-align: center">${row.r12}</td>
                <td width="8%"  style="text-align: center" style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9+row.r11}</a></td>
                <td width="8%"  style="text-align: center"  style="text-align: center">${row.s3}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Mental Retardation</td>
                <td width="8%"  style="text-align: center" style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13}</a></td>
                <td width="8%"  style="text-align: center">${row.r14}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r15}</a></td>
                <td width="8%"  style="text-align: center">${row.r16}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13+row.r15}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s4}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Mental Illness</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17}</a></td>
                <td width="8%"  style="text-align: center">${row.r18}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r19}</a></td>
                <td width="8%"  style="text-align: center">${row.r20}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17+row.r19}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s5}</td></tr>

            <tr> <td></td><td  align="left"  width="17%">Multiple </td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=6&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21}</a></td>
                <td width="8%"  style="text-align: center">${row.r22}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=6&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r23}</a></td>
                <td width="8%"  style="text-align: center">${row.r24}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&disability=6&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21+row.r23}</a></td>
                <td width="8%"  style="text-align: center" >${row.s6}</td></tr>

            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center"> ${row.todm}</td>
                <td width="8%"  style="text-align: center">${row.tdm}</td>
                <td width="8%"  style="text-align: center">${row.todf}</td>
                <td width="8%"  style="text-align: center">${row.tdf}</td>
                <td width="8%"  style="text-align: center">${row.todm+row.todf}</td>




                <td width="8%"  style="text-align: center"  > ${row.d}</td></tr>




        </table>

        <table align="center" cellspacing="1" border="0" cellpadding="0"  class="inputform" width="90%" >
            <tr>


                <td  align="center" ></td>
                <td  align="left" width="31%"  rowspan="10">Number of the person with Disability(Education Wise)</td>

                <td  align="left"  width="17%">Illeterate</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29}</a></td>
                <td width="8%"  style="text-align: center" >${row.r30} </td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r31}</a></td>
                <td width="8%"  style="text-align: center" >${row.r32}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29+row.r31}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s8}</td></tr>


            <tr> <td></td>
                <td  align="left"  width="17%">Belowtenth</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33}</a></td>
                <td width="8%"  style="text-align: center">${row.r34}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r35}</a></td>
                <td width="8%"  style="text-align: center">${row.r36}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33+row.r35}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s9}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">10th</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37}</a></td>
                <td width="8%"  style="text-align: center">${row.r38}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r39}</a></td>
                <td width="8%"  style="text-align: center">${row.r40}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37+row.r39}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s10}</td></tr>
            <tr><td  style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">3</td> <td  align="left"  width="17%">Intermediate</td>
                <td width="8%"  style="text-align: center"  > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r41}</a></td>
                <td width="8%"  style="text-align: center" >${row.r42}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43}</a></td>
                <td width="8%"  style="text-align: center">${row.r44}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r41+row.r43}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s11}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">Diploma</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45}</a></td>
                <td width="8%"  style="text-align: center">${row.r46}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r47}</a></td>
                <td width="8%"  style="text-align: center">${row.r48}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45+row.r47}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s12}</td></tr>

            <tr> <td></td><td  align="left"  width="17%">Graduate</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=6&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49}</a></td>
                <td width="8%"  style="text-align: center">${row.r50}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=6&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r51}</a></td>
                <td width="8%"  style="text-align: center">${row.r52}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=6&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49+row.r51}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s13}</td></tr>

            <tr> <td></td><td  align="left"  width="17%">Post Graduate</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=7&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53}</a></td>
                <td width="8%"  style="text-align: center">${row.r54}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=7&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r55}</a></td>
                <td width="8%"  style="text-align: center">${row.r56}</td>
                <td width="8%"  style="text-align: center"  ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=7&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53+row.r55}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s14}</td></tr>

            <tr> <td></td><td  align="left"  width="17%">Not Recorded</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25}</a></td>
                <td width="8%"  style="text-align: center">${row.r26}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r27}</a></td>
                <td width="8%"  style="text-align: center">${row.r28}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&education=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25+row.r27}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s7}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center"> ${row.toedm}</td>
                <td width="8%"  style="text-align: center">${row.tedm}</td>
                <td width="8%"  style="text-align: center">${row.toedf}</td>
                <td width="8%"  style="text-align: center">${row.tedf}</td>
                <td width="8%"  style="text-align: center"  >${row.toedm+row.toedf}</td>
                <td width="8%"  style="text-align: center"  >${row.e}</td></tr>


        </table>

    </logic:iterate>
    <logic:iterate name="Totalcount3" id="row">


        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

            <tr>

                <td align="center"  ></td>
                <td  align="left" width="31%" rowspan="8" >Number of the person with Disability(Employment Wise)</td>


                <td  align="left"  width="17%">Govt</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5}</a></td>

                <td width="8%"  style="text-align: center">${row.r6}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r7}</a></td>
                <td width="8%"  style="text-align: center">${row.r8}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5+row.r7}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s2}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">Private</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9}</a></td>
                <td width="8%"  style="text-align: center" >${row.r10}</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r11}</a></td>
                <td width="8%"  style="text-align: center" >${row.r11}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9+row.r11}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s3}</td></tr>
            <tr><td  style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">4</td> <td  align="left"  width="17%">Self Employed</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13}</a></td>
                <td width="8%"  style="text-align: center">${row.r14}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r15}</a></td>
                <td width="8%"  style="text-align: center">${row.r16}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13+row.r15}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s4}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Un Employed</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17}</a></td>
                <td width="8%"  style="text-align: center" >${row.r18}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r19}</a></td>
                <td width="8%"  style="text-align: center" >${row.r20}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17+row.r19}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s5}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Wage Employee</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21}</a></td>
                <td width="8%"  style="text-align: center" >${row.r22}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r23}</a></td>
                <td width="8%"  style="text-align: center">${row.r24}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21+row.r23}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s6}</td></tr>

            <tr><td></td> <td  align="left"  width="17%">Not Recorded</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1}</a></td>
                <td width="8%"  style="text-align: center" >${row.r2}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r3}</a></td>
                <td width="8%"  style="text-align: center" >${row.r4}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&Employment=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1+row.r3}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s1}</td></tr>


            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center" > ${row.toem}</td>
                <td width="8%"  style="text-align: center" >${row.tem}</td>
                <td width="8%"  style="text-align: center" >${row.toef}</td>
                <td width="8%"  style="text-align: center">${row.tef}</td>
                <td width="8%"  style="text-align: center">${row.toef+row.toem}</td>
                <td width="8%"  style="text-align: center"  >${row.e2}</td></tr>

        </table>

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

            <tr>

                <td align="center"  width="4%"  ></td>
                <td  align="left" width="31%" rowspan="8">Number of the person with Disability(Marital Status Wise)</td>
                <td  align="left"  width="17%">Married</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25}</a></td>
                <td width="8%"  style="text-align: center" >${row.r26}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r27}</a></td>
                <td width="8%"  style="text-align: center">${row.r28}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25+row.r27}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s7}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Un Married</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29}</a></td>
                <td width="8%"  style="text-align: center">${row.r30}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r31}</a></td>
                <td width="8%"  style="text-align: center">${row.r32}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29+row.r31}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s8}</td></tr>
            <tr> <td  style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">5</td> <td  align="left"  width="17%">Divorcee</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33}</a></td>
                <td width="8%"  style="text-align: center">${row.r34}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r35}</a></td>
                <td width="8%"  style="text-align: center">${row.r36}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33+row.r35}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s9}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Widow</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37}</a></td>
                <td width="8%"  style="text-align: center">${row.r38}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r39}</a></td>
                <td width="8%"  style="text-align: center">${row.r40}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37+row.r39}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s10}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">Widower</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r41}</a></td>
                <td width="8%"  style="text-align: center">${row.r42}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43}</a></td>
                <td width="8%"  style="text-align: center">${row.r44}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&marrtialstatus=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43+row.row.r41}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s11}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center"> ${row.tomm}</td>
                <td width="8%"  style="text-align: center">${row.tmm}</td>
                <td width="8%"  style="text-align: center">${row.tomf}</td>
                <td width="8%"  style="text-align: center">${row.tmf}</td>
                <td width="8%"  style="text-align: center">${row.tomf+row.tomm}</td>
                <td width="8%"  style="text-align: center"  >${row.m}</td></tr>





        </table>






    </logic:iterate>

    <logic:iterate name="Totalcount2" id="row">

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

            <tr>

                <td align="center"  ></td>
                <td  align="left" width="31%" rowspan="9" >Number of the person with Disability(Caste Wise)</td>


                <td  align="left"  width="17%">ST</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5}</a></td>

                <td width="8%"  style="text-align: center">${row.r6}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r7}</a></td>
                <td width="8%"  style="text-align: center">${row.r8}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5+row.r7}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s2}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">SC</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9}</a></td>
                <td width="8%"  style="text-align: center" >${row.r10}</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r11}</a></td>
                <td width="8%"  style="text-align: center" >${row.r11}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9+row.r11}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s3}</td></tr>
            <tr><td  style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">6</td> <td  align="left"  width="17%">BC</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13}</a></td>
                <td width="8%"  style="text-align: center">${row.r14}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r15}</a></td>
                <td width="8%"  style="text-align: center">${row.r16}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13+row.r15}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s4}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">OC</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17}</a></td>
                <td width="8%"  style="text-align: center" >${row.r18}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r19}</a></td>
                <td width="8%"  style="text-align: center" >${row.r20}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17+row.r19}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s5}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Minarity</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21}</a></td>
                <td width="8%"  style="text-align: center" >${row.r22}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r23}</a></td>
                <td width="8%"  style="text-align: center">${row.r24}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21+row.r23}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s6}</td></tr>

            <tr><td></td> <td  align="left"  width="17%">NA</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=6&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25}</a></td>
                <td width="8%"  style="text-align: center" >${row.r26}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=6&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r27}</a></td>
                <td width="8%"  style="text-align: center">${row.r28}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=6&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25+row.r27}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s7}</td></tr>






            <tr><td></td> <td  align="left"  width="17%">Not Entered</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1}</a></td>
                <td width="8%"  style="text-align: center" >${row.r2}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r3}</a></td>
                <td width="8%"  style="text-align: center" >${row.r4}</td>
                <td width="8%"  style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&caste=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1+row.r3}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s1}</td></tr>


            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center" > ${row.tocm}</td>
                <td width="8%"  style="text-align: center" >${row.tcm}</td>
                <td width="8%"  style="text-align: center" >${row.tocf}</td>
                <td width="8%"  style="text-align: center">${row.tcf}</td>
                <td width="8%"  style="text-align: center">${row.tocm+row.tocf}</td>
                <td width="8%"  style="text-align: center"  >${row.c}</td></tr>







        </table>

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

            <tr>

                <td align="center"  width="4%" ></td>
                <td  align="left" width="31%"  rowspan="10">Number of the person with Disability(Religion Wise)</td>
                <td  align="left"  width="17%">Hindhu</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33}</a></td>
                <td width="8%"  style="text-align: center">${row.r36}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r35}</a></td>
                <td width="8%"  style="text-align: center">${row.r32}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33+row.r35}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s9}</td></tr>
            <tr> <td></td><td  align="left"  width="17%">Muslim</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37}</a></td>
                <td width="8%"  style="text-align: center">${row.r38}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r39}</a></td>
                <td width="8%"  style="text-align: center">${row.r40}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37+row.r39}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s10}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Christian</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r41}</a></td>
                <td width="8%"  style="text-align: center">${row.r42}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43}</a></td>
                <td width="8%"  style="text-align: center">${row.r44}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43+row.r41}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s11}</td></tr>
            <tr>  <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%"  >7</td>
                <td  align="left"  width="17%">Sikh</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45}</a></td>
                <td width="8%"  style="text-align: center">${row.r46}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r47}</a></td>
                <td width="8%"  style="text-align: center">${row.r48}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45+row.r47}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s12}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Jain</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49}</a></td>
                <td width="8%"  style="text-align: center">${row.r50}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r51}</a></td>
                <td width="8%"  style="text-align: center">${row.r52}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49+row.r51}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s13}</td></tr>

            <tr><td></td> <td  align="left"  width="17%">Budhist</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=6&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53}</a></td>
                <td width="8%"  style="text-align: center">${row.r54}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=6&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r55}</a></td>
                <td width="8%"  style="text-align: center">${row.r56}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=6&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53+row.r55}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s14}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Others</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=7&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r57}</a></td>
                <td width="8%"  style="text-align: center">${row.r58}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=7&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r59}</a></td>
                <td width="8%"  style="text-align: center">${row.r60}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=7&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r59+row.r57}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s15}</td></tr>

            <tr><td></td> <td  align="left"  width="17%">Not Recorded</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29}</a></td>
                <td width="8%"  style="text-align: center">${row.r30}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r31}</a></td>
                <td width="8%"  style="text-align: center">${row.r32}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&religion=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r31+row.r29}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s8}</td></tr>



            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center"> ${row.torem}</td>
                <td width="8%"  style="text-align: center">${row.trem}</td>
                <td width="8%"  style="text-align: center">${row.toref}</td>
                <td width="8%"  style="text-align: center">${row.tref}</td>
                <td width="8%"  style="text-align: center">${row.torem+row.toref}</td>

                <td width="8%"  style="text-align: center"  >${row.e1}</td></tr>


        </table>



    </logic:iterate>

    <logic:iterate name="Totalcount1" id="row">

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"  width="90%" >

            <tr>

                <td align="center"  width="4%" ></td>
                <td  align="left" width="31%" rowspan="12">Number of the person with Disability(AGE Wise)</td>
                <td  align="left"  width="17%">0 to 3</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1}</a></td>
                <td width="8%"  style="text-align: center">${row.r2}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r3}</a></td>
                <td width="8%"  style="text-align: center">${row.r4}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r1+row.r3}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s1}</td></tr>
            <tr><td></td><td  align="left"  width="17%">3 to 5</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5}</a></td>
                <td width="8%"  style="text-align: center">${row.r6}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r7}</a></td>
                <td width="8%"  style="text-align: center">${row.r8}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r5+row.r7}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s2}</td></tr>
            <tr> <td ></td>
                <td  align="left"  width="17%">5-14</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9}</a></td>
                <td width="8%"  style="text-align: center">${row.r10}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r11}</a></td>
                <td width="8%"  style="text-align: center">${row.r12}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r9+row.r11}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s3}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">14-18</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=4&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13}</a></td>
                <td width="8%"  style="text-align: center">${row.r14}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=4&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r15}</a></td>
                <td width="8%"  style="text-align: center">${row.r16}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=4&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r13+row.r15}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s4}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">18-25</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=5&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17}</a></td>
                <td width="8%"  style="text-align: center">${row.r18}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=5&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r19}</a></td>
                <td width="8%"  style="text-align: center">${row.r20}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=5&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r17+row.r19}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s5}</td></tr>

            <tr><td  style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">8</td><td  align="left"  width="17%">25 to 35</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=6&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21}</a></td>
                <td width="8%"  style="text-align: center">${row.r22}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=6&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r23}</a></td>
                <td width="8%"  style="text-align: center">${row.r24}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=6&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r21+row.r23}</a></td>
                <td width="8%"  style="text-align: center" >${row.s6}</td></tr>


            <tr>


                <td ></td>
                <td  align="left"  width="17%"> 35 to 50</td>
                <td width="8%"  style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=7&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25}</a></td>
                <td width="8%"  style="text-align: center" >${row.r26}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=7&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r27}</a></td>
                <td width="8%"  style="text-align: center">${row.r28}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=7&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r25+row.r27}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s7}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">50 to 60</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=8&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29}</a></td>
                <td width="8%"  style="text-align: center">${row.r30}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=8&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r31}</a></td>
                <td width="8%"  style="text-align: center">${row.r32}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=8&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r29+row.r31}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s8}</td></tr>
            <tr> <td></td> <td  align="left"  width="17%">60 to 70</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=9&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33}</a></td>
                <td width="8%"  style="text-align: center">${row.r34}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=9&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r35}</a></td>
                <td width="8%"  style="text-align: center">${row.r36}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=9&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r33+row.r35}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s9}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">above 70</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=10&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37}</a></td>
                <td width="8%"  style="text-align: center">${row.r38}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=10&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r39}</a></td>
                <td width="8%"  style="text-align: center">${row.r40}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&agee=10&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r37+row.r39}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s10}</td></tr>

            <tr> <td></td>  <td  align="left"  width="17%"> Total</td>
                <td width="8%"  style="text-align: center" > ${row.toagm}</td>
                <td width="8%"  style="text-align: center" >${row.tagm}</td>
                <td width="8%"  style="text-align: center">${row.toagf}</td>
                <td width="8%"  style="text-align: center">${row.tagf}</td>
                <td width="8%"  style="text-align: center">${row.toagm+row.toagf}</td>
                <td width="8%"  style="text-align: center"  >${row.a}</td></tr>

        </table>


        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform"  width="90%" >
            <tr>  <td align="center"  width="4%" ></td> <td  align="left" width="31%" rowspan="4">Number of the person with Disability(Consanguineous Marriage Parents Wise)</td>
                <td  align="left"  width="17%">Yes</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r41}</a></td>
                <td width="8%"  style="text-align: center">${row.r42}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43}</a></td>
                <td width="8%"  style="text-align: center">${row.r44}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r43+row.r41}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s12}</td></tr>
            <tr>  <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%"  >9</td>
                <td  align="left"  width="17%">NO</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45}</a></td>
                <td width="8%"  style="text-align: center">${row.r46}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r47}</a></td>
                <td width="8%"  style="text-align: center">${row.r48}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=01&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r45+row.r47}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s11}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Not Recorded</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49}</a></td>
                <td width="8%"  style="text-align: center">${row.r50}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r51}</a></td>
                <td width="8%"  style="text-align: center">${row.r52}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&pmarige=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r49+row.r51}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s13}</td></tr>
            <tr> <td></td>  <td  align="left"  width="17%"> Total</td>
                <td width="8%"  style="text-align: center" > ${row.totpm}</td>
                <td width="8%"  style="text-align: center" >${row.tpm}</td>
                <td width="8%"  style="text-align: center">${row.totpf}</td>
                <td width="8%"  style="text-align: center">${row.tpf}</td>
                <td width="8%"  style="text-align: center">${row.totpf+row.totpm}</td>
                <td width="8%"  style="text-align: center"  >${row.p}</td></tr>


        </table>

        <table align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >

            <tr>

                <td align="center"  width="4%" ></td>
                <td  align="left" width="31%"  rowspan="7">Number of the person with Disability(RationCard Wise)</td>
                <td  align="left"  width="17%">White</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=1&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r57}</a></td>
                <td width="8%"  style="text-align: center">${row.r58}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=1&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r59}</a></td>
                <td width="8%"  style="text-align: center">${row.r60}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=1&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r59+row.r57}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s15}</td></tr>
            <tr><td></td> <td  align="left"  width="17%">Pink</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=2&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r61}</a></td>
                <td width="8%"  style="text-align: center">${row.r62}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=2&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r63}</a></td>
                <td width="8%"  style="text-align: center">${row.r64}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=2&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r61+row.r63}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s16}</td></tr>


            <tr><td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%"  >10</td><td  align="left"  width="17%">AAY</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=3&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.a1}</a></td>
                <td width="8%"  style="text-align: center">${row.a2}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=3&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.a3}</a></td>
                <td width="8%"  style="text-align: center">${row.a4}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=3&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.a1+row.a3}</a></td>
                <td width="8%"  style="text-align: center"  >${row.a5}</td></tr>
            <tr><td></td>
                <td  align="left"  width="17%">Not Recorded/Not Entered</td>
                <td width="8%"  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=0&gender=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53}</a></td>
                <td width="8%"  style="text-align: center">${row.r54}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=0&gender=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r55}</a></td>
                <td width="8%"  style="text-align: center">${row.r56}</td>
                <td width="8%"  style="text-align: center"><a href="javascript:void(0);" onclick ="window.open('TotalReport1.do?details=getDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&ration=0&total=exist&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.r53+row.r55}</a></td>
                <td width="8%"  style="text-align: center"  >${row.s14}</td></tr>





            <tr><td></td> <td  align="left"  width="17%">Total</td>
                <td width="8%"  style="text-align: center"> ${row.totrm}</td>
                <td width="8%"  style="text-align: center">${row.trm}</td>
                <td width="8%"  style="text-align: center">${row.totrf}</td>
                <td width="8%"  style="text-align: center">${row.trf}</td>
                <td width="8%"  style="text-align: center">${row.totrf +row.totrm}</td>
                <td width="8%"  style="text-align: center"  >${row.r}</td></tr>





        </table>


    </logic:iterate>








    <table align="center">
        <a href="TotalReport1.xls?status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
        <a href="TotalReport1.do?status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
    </table>
</logic:notEmpty>

</html:form>
</body>
</html:html>
