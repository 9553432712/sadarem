<%--
    Document   : cMDashBoardReport6
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

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width" />

        <title>SADAREM</title>

        <%

                    String district_name = (String) request.getAttribute("district_name");
                    String mandal_name = (String) request.getAttribute("mandal_name");
                    String village_name = (String) request.getAttribute("village_name");
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");


        %>

        <script type="text/javascript" src="<%=request.getContextPath()%>/DisabilityUITG/js/jquery.min.js"></script>
      <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
      <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
 
        <script language="javascript" type="text/javascript">
            function OpenPopupCenter(pageURL, title, w, h) {
                var left = (screen.width - w) / 2;
                var top = (screen.height - h) / 4;  // for 25% - devide by 4  |  for 33% - devide by 3 
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            }
        </script>
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

    <body onload="OnBodyLoad(1,3);">
        <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
            <tr>
                <td width="97%" style="padding-left: 5px; padding-right: 5px;">
                    <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0" style="overflow:scroll; width:200px; height:308px; vertical-align: top">
                        <!-- Header Starts -->
                        <%-- <tr>
                             <td style="background-color: #e7f3f8; padding: 10px;" colspan="2">

                                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                    <tr>
                                        <td >
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="color:#000;font-size:11px;font-weight:normal;" height="40px">
                                                <tr>
                                                    <td align="right"><img src="./DisabilityUITG/images/TG_GOV_LOGO.png"/></td>
                                                    <td align="center" style="font-size:12px; line-height: 18px; font-family: verdana; font-weight: bold; text-align: center;">
                                                        <span style="font-size:18px;">Government Of Andhra Pradesh</span><br/>
                                                        Rural Development Department SERP<br/>
                                                        SADAREM<br/>
                                                        (Software for Assessment Of Disabled For Access Rehabilitation and Empowerment)<br/>
                                                        SADAREM Assessment And Pension status as on:
                                                        <script>

                                                            var dt = new Date();
                                                            var dat = dt.getDate()-1;
                                                            var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                            if(dat=="0") {
                                                                d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                                                            }
                                                            document.write(d);

                                                        </script>
                                                    </td>
                                                    <td align="right" ><img src="./DisabilityUITG/images/Sadarem-logo-Rep.png"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>

                                </table>
                            </td>

                        </tr>

                        <br/>--%>
                      <logic:present name="district">  
                        <tr> <th colspan="2" class="hd_gd" align="center" valign="middle">R1.1 : SADAREM Assessment Status Report - State Level</th></tr>
                       </logic:present>
                       
                        <logic:present name="mandal">  
                        <tr> <th colspan="2" class="hd_gd" align="center" valign="middle">R1.1 : SADAREM Assessment Status Report - District Level</th></tr>
                       </logic:present>
                       
                        <logic:present name="village">  
                        <tr> <th colspan="2" class="hd_gd" align="center" valign="middle">R1.1 : SADAREM Assessment Status Report - Mandal Level</th></tr>
                       </logic:present>
                       
                        <logic:present name="habitation">  
                        <tr> <th colspan="2" class="hd_gd" align="center" valign="middle">R1.1 : SADAREM Assessment Status Report - Village Level</th></tr>
                       </logic:present>
                       
                       
                       
                       
                       
                        <br> <br>

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
                                             <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 14;font-weight: bold" >
                                                        ${heading}
                                                    </td>
                                                    <td align="right" >
                                                        <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                        <a href="dashboardReport.do?mode=excel&heading=<%=request.getAttribute("heading")%>"  target="_blank">
                                                            Excel </a></td>
                                                </tr>
                                            </logic:present>


                                        </logic:present>
                                        <logic:present name="mandal">
                                           
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 14;font-weight: bold" >
                                                        ${heading}
                                                    </td>

                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=excel&heading=${heading}&district_name=<%=district_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=gerDashBoardReport6" > Back</a>
                                                </td>
                                            </tr>
                                        </logic:present>
                                        <logic:present name="village">
                                           
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 14;font-weight: bold;">
                                                        ${heading}

                                                    </td>
                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=excel&heading=${heading}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=gerDashBoardReport6&district_id=${modify.district_id}&district_name=<%=district_name%>" > Back</a>
                                                </td>
                                            </tr>
                                        </logic:present>

                                        <logic:present name="habitation">
                                          
                                            <logic:present name="heading">
                                                <tr>
                                                    <td style="font-size: 14;font-weight: bold" >
                                                        ${heading}

                                                    </td>
                                                </logic:present>
                                                <td align="right">
                                                    <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                    <a href="dashboardReport.do?mode=excel&heading=${heading}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>&village_name=<%=village_name%>"  target="_blank">
                                                        Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                    <a href="dashboardReport.do?mode=gerDashBoardReport6&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>" > Back</a>
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
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
  				       <th rowspan="2" class="hd_gd" align="center" valign="middle" >S.No</th>
                                                <logic:present name="district">
                                                    <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                        District
                                                    </th>
                                                </logic:present>
                                                <logic:present name="mandal">
                                                    <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                        Mandal
                                                    </th>
                                                </logic:present>
                                                <logic:present name="village">
                                                    <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                        Village
                                                    </th>
                                                </logic:present>
                                                <logic:present name="habitation">
                                                    <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                        Habitation
                                                    </th>
                                                </logic:present>
                                                <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                    Total Applied
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Total
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Locomotor/OH
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Visual Impairment
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Hearing Impairment
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Mental Retardation
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Mental Illness
                                                </th>
                                                <th colspan="3" class="hd_gd" align="center" valign="middle" >
                                                    Multiple Disability
                                                </th>



                                                <th rowspan="2" class="hd_gd" align="center" valign="middle" >
                                                    To be Assessed
                                                </th>



                                            </tr>
                                            <tr class="gridHdrStyle">
  				      
                                                <th   class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th  class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th   class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th  class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th  class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                                <th class="hd_gd" align="center" valign="middle"> Assessed </th>
                                                <th class="hd_gd" align="center" valign="middle"> Eligible </th>
                                                <th class="hd_gd" align="center" valign="middle"> Rejected </th>

                                            </tr>
                                            <tr class="gridHdrStyle">
                                                <th class="hd_gd" align="center" valign="middle">(1)</th>
                                                <th class="hd_gd" align="center" valign="middle">(2)</th>
                                                <th class="hd_gd" align="center" valign="middle">(3)</th>

                                                <th class="hd_gd" align="center" valign="middle">(4)</th>
                                                <th class="hd_gd" align="center" valign="middle">(5)</th>
                                                <th class="hd_gd" align="center" valign="middle">(6)</th>
                                                <th class="hd_gd" align="center" valign="middle">(7)</th>
                                                <th class="hd_gd" align="center" valign="middle">(8)</th>
                                                <th class="hd_gd" align="center" valign="middle">(9)</th>
                                                <th class="hd_gd" align="center" valign="middle">(10)</th>
                                                <th class="hd_gd" align="center" valign="middle">(11)</th>
                                                <th class="hd_gd" align="center" valign="middle">(12)</th>
                                                <th class="hd_gd" align="center" valign="middle">(13)</th>
                                                <th class="hd_gd" align="center" valign="middle">(14)</th>
                                                <th class="hd_gd" align="center" valign="middle">(15)</th>
                                                <th class="hd_gd" align="center" valign="middle">(16)</th>
                                                <th class="hd_gd" align="center" valign="middle">(17)</th>
                                                <th class="hd_gd" align="center" valign="middle">(18)</th>
                                                <th class="hd_gd" align="center" valign="middle">(19)</th>
                                                <th class="hd_gd" align="center" valign="middle">(20)</th>
                                                <th class="hd_gd" align="center" valign="middle">(21)</th>
                                                <th class="hd_gd" align="center" valign="middle">(22)</th>
                                                <th class="hd_gd" align="center" valign="middle">(23)</th>
                                                <th class="hd_gd" align="center" valign="middle">(24)</th>
                                                <th class="hd_gd" align="center" valign="middle">(25)</th>



                                            </tr>



                                            <% int i = 0;
                                                        int j = 0; String classStyle="";%>
                                            <logic:iterate id="modify" name="stateReport" scope="request" indexId="count">
                                                <% if (stateReport.size() - 1 != j) {%>
                                                
                                                <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                                                

                                                <tr >
                                                    <td  class="<%=classStyle%>" style="text-align: center"><%=++i%></td>
                                                    <logic:present name="district">
                                                        <td width="10%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea;
                                                                line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="dashboardReport.do?mode=gerDashBoardReport6&district_id=${modify.district_id}&district_name=${modify.district_name}">
                                                                <bean:write name="modify" property="district_name"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=5','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=6','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="oheligible"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=7','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohrejected"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=8','Details',1000,650);return false;">

                                                                <bean:write name="modify" property="viassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=9','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="vieligible"/>
                                                            </a> 
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=10','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="virejected"/>
                                                            </a> 
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hiassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hirejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrassessed"/>
                                                            </a>    
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=15','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mreligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=16','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrrejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=17','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="miassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=18','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=19','Details',1000,650);return false;">

                                                                <bean:write name="modify" property="mirejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=20','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=21','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdeligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=22','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdrejected"/>
                                                            </a>

                                                        </td>





                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a  style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal;
                                                                color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                                href="javascript: void(0)"
                                                                onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=${modify.district_name}&flag=23','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)%
                                                            </a>

                                                        </td>



                                                    </logic:present>
                                                    <logic:present name="mandal"  >
                                                    
                                                        <td width="10%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=gerDashBoardReport6&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}"><bean:write name="modify" property="mandal_name"/></a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=5','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=6','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="oheligible"/>
                                                            </a>

                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=7','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohrejected"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=8','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="viassessed"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=9','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="vieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=10','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="virejected"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hiassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hirejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=15','Details',1000,650);return false;">

                                                                <bean:write name="modify" property="mreligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=16','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrrejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=17','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="miassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=18','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=19','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mirejected"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=20','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=21','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdeligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=22','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdrejected"/>
                                                            </a>

                                                        </td>




                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=${modify.mandal_name}&flag=23','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)%
                                                            </a>

                                                        </td>
                                                    </logic:present>
                                                    <logic:present name="village">
                                                        <td width="10%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="dashboardReport.do?mode=gerDashBoardReport6&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}"><bean:write name="modify" property="village_name"/></a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=5','Details',1000,650);return false;">

                                                                <bean:write name="modify" property="ohassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=6','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="oheligible"/>
                                                            </a>

                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=7','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohrejected"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=8','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="viassessed"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=9','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="vieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=10','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="virejected"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hiassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hieligible"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hirejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrassessed"/>
                                                            </a>

                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=15','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mreligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=16','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrrejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=17','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="miassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=18','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mieligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=19','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mirejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=20','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=21','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdeligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">

                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=22','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdrejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1"
                                                               href="javascript: void(0)"
                                                               onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&village_name=${modify.village_name}&flag=23','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)%
                                                            </a>

                                                        </td>
                                                    </logic:present>
                                                    <logic:present name="habitation">
                                                        <td width="10%" class="<%=classStyle%>">
                                                            <bean:write name="modify" property="habitation_name"/>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=1','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAppliedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=2','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="totalAssessedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=4','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="eligibleCM"/>
                                                            </a>

                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=3','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="rejectedCM"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=5','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=6','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="oheligible"/>
                                                            </a>
                                                        </td>
                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=7','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="ohrejected"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=8','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="viassessed"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=9','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="vieligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=10','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="virejected"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=11','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hiassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=12','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hieligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=13','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="hirejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=14','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=15','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mreligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=16','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mrrejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=17','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="miassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=18','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mieligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=19','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mirejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=20','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdassessed"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name=<%=village_name%>&flag=21','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdeligible"/>
                                                            </a>
                                                        </td>

                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=22','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="mdrejected"/>
                                                            </a>
                                                        </td>


                                                        <td width="8%"  class="<%=classStyle%>">
                                                            <a style="font-family:Verdana, Geneva, sans-serif; font-size:11px; font-weight:normal; color:#0074ea; line-height:20px; text-decoration:underline;" class="lnk1" href="javascript: void(0)"  onclick="OpenPopupCenter('dashboardReport.do?mode=getIndividualDashBoardDetails&district_id=${modify.district_id}&district_name=<%=district_name%>&mandal_id=${modify.mandal_id}&mandal_name=<%=mandal_name%>&village_id=${modify.village_id}&habitation_Code=${modify.habitation_id}&village_name==<%=village_name%>&flag=23','Details',1000,650);return false;">
                                                                <bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)%
                                                            </a>
                                                        </td>
                                                    </logic:present>


                                                    <%--<td width="8%"  class="<%=classStyle%>">
                                                        <bean:write name="modify" property="total"/>
                                                    </td>--%>
                                                </tr>
                                                <%}
                                                            j++;%>
                                            </logic:iterate>
                                            <%  j = 0;%>
                                            <logic:iterate id="modify" name="stateReport" scope="request">

                                                <%

                                                            if (stateReport.size() - 1 == j) {%>
                                                <tr class="gridHdrStyle">
                                                    <th   colspan="2" class="hd_gd" align="center" valign="middle">Total</th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalAppliedCM"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalAssessedCM"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="totaleligibleCM"/></th>
                                                    <th class="hd_gd" align="center" valign="middle">
                                                        <bean:write name="modify" property="totalrejectedCM"/>
                                                    </th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalOhAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalOhEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalOhRejected"/></th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalViAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalViEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalViRejected"/></th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalHiAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalHiEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalHiRejected"/></th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMrAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMrEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMrRejected"/></th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMiAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMiEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMiRejected"/></th>

                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMdAssessed"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMdEligible"/></th>
                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="grandtotalMdRejected"/></th>




                                                    <th class="hd_gd" align="center" valign="middle"><bean:write name="modify" property="showDifference"/>(<bean:write name="modify" property="certificatePercentage"/>)%</th>


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



                                 
                                </logic:present>
                                <!-- Content Endts -->
                            </td>
                        </tr>


                        <!-- Footer Starts -->


                    </table>
                </td>
            </tr>
        </table>

        <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0">
            <tr><td style="text-align:center">
                    <logic:present name="noData">
                        <font color="red">
                            ${noDatas}</font>
                    </logic:present></td>
            </tr>
        </table>

        <!-- Footer Ends -->


    </body>
</html>
