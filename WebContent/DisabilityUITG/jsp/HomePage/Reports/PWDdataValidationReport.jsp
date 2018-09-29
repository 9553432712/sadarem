<%--
    Document   : PwDdataValidationReport
    Created on : Feb 23, 2015, 11:01:06 AM
    Author     : 310926
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<%
            int i = 1;
            ArrayList list = (ArrayList) request.getAttribute("reportList");
            String districtid = null;
            districtid = (String) request.getAttribute("districtId");
            String mandalid = null;
            mandalid = (String) request.getAttribute("mandal_id");
            String districtname = null;
            districtname = (String) request.getAttribute("districtname");
            String mandalname = null;
            mandalname = (String) request.getAttribute("mandal_name");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>      
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

        <%-- <script >
             function ShowDate()
             {
                 var dt = new Date();
                 var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                 document.getElementById(8).value = d;
                 document.getElementById(7).value = '01/01/2010';

            }

            function getDetails() {
                var fromDate= document.forms[0].fromDate.value;
                    var toDate= document.forms[0].toDate.value;
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
                    if (validate_required(fromDate,"From Date must be selected!")==false)
                    {
                        document.forms[0].fromDate.focus();
                        return false
                    }
                    if (validate_required(toDate,"To Date must be selected!")==false)
                    {
                        document.forms[0].toDate.focus();
                        return false
                    }
                    if (today < etd)
                    {
                        alert("From date is before Today's Date");
                        document.forms[0].fromDate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date does not exceed Today's Date");
                        document.forms[0].toDate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromDate.value="";
                        return false;
                    }
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                    return true;

            }


            function validate_required(field,alerttxt)
            {

                with (field)
                {

                    if (field==null||field=="")
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

        </script>--%>
        <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
 {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
}
</Style>
    </head>

    <body onload=" OnBodyLoad(2,3);">

 <html:form action="/dataValidationReport.do" >


            <input type="hidden" name="mode"/>
            <%--<table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">
                 <tr>
                     <td>
                         <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                             <tr>
                                 <th colspan="4">
                                     PwD Data Validation Report
                                 </th>
                             </tr>
                             <tr>

                                <td>From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromDate" styleId="7" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromDate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td>To Date<font color="red"><b>*</b></font>
                                    <html:text property="toDate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].toDate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                            </tr>
                            <tr align="center">
                                <th colspan="4">
                                    <html:button property="but" onclick="getDetails()" value="Submit"/>
                                </th>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>--%>
            <%--<br>--%>
             <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                          R1.5 PwD Data Validation Rural Report
                    </th>
                </tr>
                </table>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

            <logic:present name="heading">
                <center>${heading}</center>
            </logic:present>

            <logic:notEmpty name="reportList">
                <table   align="center" cellspacing="1" border="0" cellpadding="4"  width="70%" >


                    <tr>

                        <td align="left"><%if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {%>
                            <b>District:<%=districtname%>, &nbsp;Mandal:<%=mandalname%></b>
                            <%}
                                        if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid == null) {%>
                            <b>District:<%=districtname%></b>
                            <%}%>
                        </td>

                        <%if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {%>
                        <td align="right">
                            <a href="dataValidationReport.do?mode=unspecified&district_id=<%=districtid%>&districtName=<%=districtname%>">  <img src="DisabilityUITG/images/but_back.png" height="35" width="25" title="Back"/></a>&nbsp;&nbsp;
                            <a href="dataValidationReport.xls?mode=excel&district_id=<%=districtid%>&mandal_id=<%=mandalid%>&districtName=<%=districtname%>&mandal_name=<%=mandalname%>" target="_blank">
                                 <img src="DisabilityUITG/images/excel.jpg" height="30" width="25" title="Export Excel"/></a>

                        </td>
                        <%} else if (districtid != null && !districtid.equalsIgnoreCase("0")) {%>
                        <td align="right">
                            <a href="dataValidationReport.do?mode=unspecified"> <img src="DisabilityUITG/images/but_back.png" height="35" width="25" title="Back"/></a>
                            &nbsp;&nbsp;<a href="dataValidationReport.xls?mode=excel&district_id=<%=districtid%>&districtName=<%=districtname%>" target="_blank">
                                 <img src="DisabilityUITG/images/excel.jpg" height="30" width="25" title="Export Excel"/></a>


                        </td>

                        <%}
                                    if (districtid == null) {%>
                        <td align="right"><a href="dataValidationReport.xls?mode=excel" target="_blank"> 
                                  <img src="DisabilityUITG/images/excel.jpg" height="30" width="25" title="Export Excel"/></a></td>
                                <%}%>


                    </tr>
                </table>
 

                 <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                       <tr>
                        <th  class="hd_gd" align="center" valign="middle">S No.</th>

                        <%if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {%>

                        <th class="hd_gd" align="center" valign="middle" >Village</th>

                        <%} else if (districtid != null && !districtid.equalsIgnoreCase("0")) {%>
                        <th class="hd_gd" align="center" valign="middle" >Mandal</th>

                        <%}
                                    if (districtid == null) {%>
                        <th class="hd_gd" align="center" valign="middle" >District</th>

                        <%}%>
                        <th class="hd_gd" align="center" valign="middle" >Total Assessed</th>
                        <th class="hd_gd" align="center" valign="middle" >PwD Validation Done</th>
                        <th class="hd_gd" align="center" valign="middle" >PwD Validation Pending</th>

                    </tr>
                    
                    <tr>
                      <th class="hd_gd" align="center" valign="middle">1</th>
                      <th class="hd_gd" align="center" valign="middle">2</th>
                      <th class="hd_gd" align="center" valign="middle">3</th>
                      <th class="hd_gd" align="center" valign="middle">4</th>
                      <th class="hd_gd" align="center" valign="middle">5</th>
                    
                    </tr>
                    
                    
                    
					<%String classStyle=""; %>
                    <logic:iterate name="reportList" id="row" indexId="count">
                                             <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                        <tr>

                            <td class="<%=classStyle%>" style="text-align: center">
                                ${row.sno}.
                            </td>

                            <%if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {%>

                            <td class="<%=classStyle%>" style="text-align: left">
                                ${row.village_name}


                            </td>

                            <%} else if (districtid != null && !districtid.equalsIgnoreCase("0")) {%>

                            <td class="<%=classStyle%>" style="text-align: left">
                                <a href="dataValidationReport.do?mode=unspecified&district_id=${row.district_id}&districtName=${row.district_name}&mandal_id=${row.mandal_id}&mandal_name=${row.mandal_name}"> ${row.mandal_name}</a>
                            </td>
                            <%} if (districtid == null) {%>
                            <td class="<%=classStyle%>" style="text-align: left">
                                <a href="dataValidationReport.do?mode=unspecified&district_id=${row.district_id}&districtName=${row.district_name}"> ${row.district_name}</a>
                            </td>

                            <%}%>
                            <td   class="<%=classStyle%>" style="text-align: right">
                                ${row.Assessed}
                            </td>

                            <td class="<%=classStyle%>" style="text-align: right">
                                ${row.validationDone}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: right">
                                <%if (districtid != null && !districtid.equalsIgnoreCase("0") && mandalid == null) {%>
                               <a href="dataValidationReport.xls?mode=excelIndividualDetails&district_id=${row.district_id}&districtName=${row.district_name}&mandal_id=${row.mandal_id}&mandal_name=${row.mandal_name}" target="_blank">${row.validationPending}</a>
                                <%} else {%>
                                ${row.validationPending}
                                <%}%>

                            </td>
                        </tr>

                    </logic:iterate>

                    <tr>
                        <th colspan="2" style="text-align: center" class="hd_gd">Total</th>
                        <th style="text-align: center" class="hd_gd">
                            ${row.totalassessed1}
                        </th>
                        <th style="text-align: center" class="hd_gd">  ${row.validationdone1}</th>
                        <th style="text-align: center" class="hd_gd">  ${row.validationPending1}</th> 

                    </tr>
                </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>



            </logic:notEmpty>

        </html:form>
    </body>
</html>



