<%--
    Document   : cMDashBoard
    Created on : Jul 22, 2014, 12:16:54 PM
    Author     : 310926
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
    <head profile="http://gmpg.org/xfn/11">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width" />

        <title>SADAREM</title>
        <link REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css" />

        <%

                    String district_name = (String) request.getAttribute("district_name");
                    String mandal_name = (String) request.getAttribute("mandal_name");
                    String village_name = (String) request.getAttribute("village_name");

                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");


        %>

        <script type="text/javascript" src="./DisabilityUITG/js/jquery.min.js"></script>
        <script type="text/javascript" src="./DisabilityUITG/js/jquery.fixheadertable.min.js"></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <link rel="stylesheet" type="text/css" href="./DisabilityUITG/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="./DisabilityUITG/css/jquery-ui-1.8.4.custom.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript">
            function OpenPopupCenter(pageURL, title, w, h) {
                var left = (screen.width - w) / 2;
                var top = (screen.height - h) / 4;  // for 25% - devide by 4  |  for 33% - devide by 3
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            }
            function closeWin() {
                window.close();
            }

        </script>
    </head>

    <body onload="OnBodyLoad(1,3);">
        <table  border="0" cellspacing="0" cellpadding="0" align="center" width="90%">
            <tr>
                <td width='15px' align='right' style='background-image:url(./DisabilityUITG/images/rgt_shadow.png); background-repeat:repeat-y'><img src='./DisabilityUITG/images/noimg.png' /></td>
                <td width="98%" style="padding-left: 5px; padding-right: 5px;">
                    <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0">




                        <logic:present name="stateReport" scope="request">

                            <%
                                        session.setAttribute("stateReport", stateReport);%>
                            <tr>
                                <td align="center" colspan="15">
                                </td>
                            </tr>





                            <tr>

                                <td style="text-align: center; font-family: verdana; font-size: 18px; font-weight: bold; color: #ff4e00;">
                                    <logic:iterate name="stateReport" id="modify" length="1">

                                        <logic:present name="district">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; State Level Report

                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 7;font-weight: bold" >
                                                        ${heading} &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
                                                        <font size="3">SADAREM Assessment and Pension status as on:
                                                            <script>

                                                                var dt = new Date();
                                                                var dat = dt.getDate()-1;
                                                                var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                if(dat=="0") {
                                                                    d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                }
                                                                document.write(d);

                                                            </script></font>
                                                    </td>
                                                    <td align="right" >
                                                        <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                        <a href="dashboardReport.do?mode=CMexcel&heading=<%=request.getAttribute("heading")%>"  target="_blank">
                                                            Excel </a></td>
                                                </tr>
                                            </logic:present>


                                        </logic:present>
                                        <logic:present name="mandal">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;District Level Report
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 5;font-weight: bold" >
                                                        ${heading}&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
                                                        <font size="3">SADAREM Assessment and Pension status as on:
                                                            <script>

                                                                var dt = new Date();
                                                                var dat = dt.getDate()-1;
                                                                var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                if(dat=="0") {
                                                                    d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                }
                                                                document.write(d);

                                                            </script></font>
                                                    </td>

                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=CMexcel&heading=${heading}&district_name=<%=district_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=unspecified" > Back</a>
                                                </td>
                                            </tr>
                                        </logic:present>
                                        <logic:present name="village">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Mandal Level Report
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 5;font-weight: bold;">
                                                        ${heading}&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
                                                        <font size="3">SADAREM Assessment and Pension status as on:
                                                            <script>

                                                                var dt = new Date();
                                                                var dat = dt.getDate()-1;
                                                                var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                if(dat=="0") {
                                                                    d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                }
                                                                document.write(d);

                                                            </script></font>

                                                    </td>
                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=CMexcel&heading=${heading}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&district_name=<%=district_name%>" > Back</a>
                                                </td>
                                            </tr>
                                        </logic:present>

                                        <logic:present name="habitation">
                                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Village Level Report
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 5;font-weight: bold" >
                                                        ${heading}&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
                                                        <font size="3">SADAREM Assessment and Pension status as on:
                                                            <script>

                                                                var dt = new Date();
                                                                var dat = dt.getDate()-1;
                                                                var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                if(dat=="0") {
                                                                    d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                                }
                                                                document.write(d);

                                                            </script></font>

                                                    </td>
                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=CMexcel&heading=${heading}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>&village_name=<%=village_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>" > Back</a>
                                                </td>
                                            </tr>
                                        </logic:present>
                                    </logic:iterate>
                                </td>
                            </tr>
                        </logic:present>
                        <!-- Header Ends -->
                        <tr>
                            <td valign="top"  align="center" colspan="2" style="height:458px">
                                <!-- Content Starts -->
                                <logic:present name="stateReport" scope="request">
                                
                                
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
               
                 <tbody>
                <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
                
  				<tr class="gridHdrStyle">
  				   <th rowspan="2" height="30" class="hd_gd" align="center" valign="middle" >S.No</th>
                                                <logic:present name="district">
                                                    <th  rowspan="2"  height="30" class="hd_gd" align="center" valign="middle">District</th>
                                                </logic:present>
                                                <logic:present name="mandal">
                                                    <th  rowspan="2"  height="30" class="hd_gd" align="center" valign="middle">Mandal</th>
                                                </logic:present>
                                                <logic:present name="village">
                                                    <th  rowspan="2" height="30" class="hd_gd" align="center" valign="middle">Village</th>
                                                </logic:present>
                                                <logic:present name="habitation">
                                                    <th  rowspan="2" height="30" class="hd_gd" align="center" valign="middle">Habitation</th>
                                                </logic:present>
                                                <th colspan="8" height="30" class="hd_gd" align="center" valign="middle">PwDs - ASSESSMENT STATUS</th>
                                            </tr>
                                            <tr class="gridHdrStyle">
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">Total Applied</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">Assessed (%)</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">Disability (<40%) <br/>(%)</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle"> (>=40%)<br/>(%)</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">(>=40% to <80%)(%)</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">(>=80%)(%)</th>
                                                <th width="8%"height="30" class="hd_gd" align="center" valign="middle">PwDs - Pensions Covered</th>
                                                <th width="8%" height="30" class="hd_gd" align="center" valign="middle">Pendency Certificate (%)</th>
                                            </tr>
                                            <tr class="gridHdrStyle">
                                                <%-- <th width="8%" >&nbsp;</th>
                                                 <th width="8%" >&nbsp;</th>--%>

                                                <th height="30" class="hd_gd" align="center" valign="middle">1</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >2</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >3</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >4</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >5</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >6</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >7</th>

                                                <th height="30" class="hd_gd" align="center" valign="middle" >8</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >9</th>
                                                <th height="30" class="hd_gd" align="center" valign="middle" >10</th>
                                            </tr>
                                            <% int j = 0;%>

                                            <logic:iterate id="modify" name="stateReport" scope="request">

                                                <%

                                                            if (stateReport.size() - 1 == j) {%>
                                                <tr  >
                                                    <th   colspan="2"  >Total</th>
                                                    <th><bean:write name="modify" property="grandtotalAppliedCM"/></th>
                                                    <th><bean:write name="modify" property="grandtotalAssessedCM"/> (<bean:write name="modify" property="totalassessedPercentage"/>)</th>
                                                    <th><bean:write name="modify" property="totalrejectedCM"/> (<bean:write name="modify" property="totalrejectedPercentage"/>)</th>
                                                    <th><bean:write name="modify" property="totaleligibleCM"/> (<bean:write name="modify" property="totaleligiblePercentage"/>)</th>
                                                    <th><bean:write name="modify" property="totaleligiblebelow80"/> (<bean:write name="modify" property="totaleligiblebelow80Percentage"/>)</th>
                                                    <th><bean:write name="modify" property="totaleligibleabove80"/> (<bean:write name="modify" property="totaleligibleabove80Percentage"/>)</th>
                                                    <th><bean:write name="modify" property="totaltotalCM"/> (<bean:write name="modify" property="totalpensionerPercentage"/>)</th>
                                                    <th><bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)</th>
                                                </tr>
                                                <%}
                                                            j++;%>
                                            </logic:iterate>
                                            <% int i = 0;
                                                        j = 0;%>
                                            <logic:iterate id="modify" name="stateReport" scope="request">
                                                <% if (stateReport.size() - 1 != j) {%>
                                                <tr >
                                                    <td width="4%" style="font-family: verdana; font-size: 12px;text-align: center"><%=++i%></td>
                                                   <%-- <logic:present name="district">
                                                        <td width="10%" style=" font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&district_name=${modify.district_name}" >
                                                                <bean:write name="modify" property="district_name"/></a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/> </a></td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/> (<bean:write name="modify" property="assessedPercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=3','Details',1000,650);return false;" >
                                                                <bean:write name="modify" property="rejectedCM"/> (<bean:write name="modify" property="rejectedPercentage"/>)
                                                            </a>
                                                        </td>
                                                             <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/> (<bean:write name="modify" property="eligiblePercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligiblebelow80"/> (<bean:write name="modify" property="eligiblebelow80Percentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleabove80"/> (<bean:write name="modify" property="eligibleabove80Percentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  class="lnk1" href="dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=11">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalCM"/> (<bean:write name="modify" property="pensionerPercentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/> (<bean:write name="modify" property="certificatePercentage"/>)
                                                            </a>
                                                        </td>
                                                    </logic:present>--%>
                                                   <logic:present name="district">
                                                        <td width="10%" style=" font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&district_name=${modify.district_name}" >
                                                                <bean:write name="modify" property="district_name"/></a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/> </a></td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/> (<bean:write name="modify" property="assessedPercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=3','Details',1000,650);return false;" >
                                                                <bean:write name="modify" property="rejectedCM"/> (<bean:write name="modify" property="rejectedPercentage"/>)
                                                            </a>
                                                        </td>
                                                             <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/> (<bean:write name="modify" property="eligiblePercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligiblebelow80"/> (<bean:write name="modify" property="eligiblebelow80Percentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleabove80"/> (<bean:write name="modify" property="eligibleabove80Percentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <%--<a  class="lnk1" href="dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=11">--%>
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalCM"/> (<bean:write name="modify" property="pensionerPercentage"/>)
                                                            </a>
                                                        </td>


                                                                              <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/> (<bean:write name="modify" property="certificatePercentage"/>)
                                                            </a>
                                                        </td>
                                                    </logic:present>
                                                    <logic:present name="mandal">
                                                        <td width="10%" style="font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}"><bean:write name="modify" property="mandal_name"/></a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/> (<bean:write name="modify" property="assessedPercentage"/>)
                                                            </a>

                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>(<bean:write name="modify" property="rejectedPercentage"/>)
                                                            </a>
                                                        </td>
                                                             <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>(<bean:write name="modify" property="eligiblePercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligiblebelow80"/> (<bean:write name="modify" property="eligiblebelow80Percentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleabove80"/>(<bean:write name="modify" property="eligibleabove80Percentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalCM"/>(<bean:write name="modify" property="pensionerPercentage"/>)
                                                            </a>
                                                        </td>
                                                             <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)
                                                            </a>
                                                        </td>

                                                    </logic:present>
                                                    <logic:present name="village">
                                                        <td width="10%" style="font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=unspecified&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}"><bean:write name="modify" property="village_name"/></a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/> (<bean:write name="modify" property="assessedPercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>(<bean:write name="modify" property="rejectedPercentage"/>)
                                                            </a>
                                                        </td>
                                                            <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>(<bean:write name="modify" property="eligiblePercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligiblebelow80"/> (<bean:write name="modify" property="eligiblebelow80Percentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleabove80"/>(<bean:write name="modify" property="eligibleabove80Percentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalCM"/>(<bean:write name="modify" property="pensionerPercentage"/>)
                                                            </a>
                                                        </td>
                                                              <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)
                                                            </a>
                                                        </td>

                                                    </logic:present>
                                                    <logic:present name="habitation">
                                                        <td width="10%" style="font-family: verdana; font-size: 12px;">
                                                            <bean:write name="modify" property="habitation_name"/>
                                                        </td>
                                                        <td width="10%" style="font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/> (<bean:write name="modify" property="assessedPercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>(<bean:write name="modify" property="rejectedPercentage"/>)
                                                            </a>
                                                        </td>
                                                             <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>(<bean:write name="modify" property="eligiblePercentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligiblebelow80"/> (<bean:write name="modify" property="eligiblebelow80Percentage"/>)
                                                            </a>
                                                        </td>
                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleabove80"/>(<bean:write name="modify" property="eligibleabove80Percentage"/>)
                                                            </a>
                                                        </td>

                                                        <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalCM"/>(<bean:write name="modify" property="pensionerPercentage"/>)
                                                            </a>
                                                        </td>
                                                              <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)
                                                            </a>
                                                        </td>
                                                      <%--  <td width="8%" style="text-align: center; font-family: verdana; font-size: 12px;">
                                                           <bean:write name="modify" property="showDifference"/> (<bean:write name="modify" property="certificatePercentage"/>)
                                                        </td>--%>
                                                    </logic:present>

                                                </tr>
                                                <%}
                                                            j++;%>
                                            </logic:iterate>
                                        </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
	
	
                                    <p align="center"><script type="text/javascript">// <![CDATA[
                                        $('#fixmyheader').fixheadertable({height: '515',minWidth:600,caption:'', zebra : true});
                                        // ]]&gt;</script>
                                    </p>
                                </logic:present>
                                <!-- Content Endts -->
                            </td>
                        </tr>
                        <tr>
                            <td align="right" colspan="14">
                                <a href="javascript:void(0)" onclick="closeWin();"><img src="./DisabilityUITG/images/close.jpg" alt="Close" width="21px" height="21px"/>Close Window </a>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width='18px' align='right' style='background-image:url(./DisabilityUITG/images/lft_shadow.png); background-repeat:repeat-y'><img src='./DisabilityUITG/images/noimg.png' /></td>
            </tr>
            <tr><td colspan="3" height="15px" style='background-image:url(./DisabilityUITG/images/btm_shadow.png); background-repeat:repeat-x'><img src='./DisabilityUITG/images/noimg.png' /></td></tr>
        </table>
         <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0">
          <tr><td align="center">
                 <logic:present name="noData">
                <font color="red">
                    ${noData}</font>
                </logic:present></td>
            </tr>
        </table>
    </body>

</html>
