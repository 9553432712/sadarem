<%-- 
    Document   : callCentreReportBasedOnMonth
    Created on : May 23, 2013, 3:05:38 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
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
    


        <script>

            function getReport(){
                if( document.forms[0].month.value=="ALL"){
                    alert("Please select Month");
                    document.forms[0].month.focus();
                    return false;
                }
                if( document.forms[0].year.value=="ALL"){
                    alert("Please select year");
                    document.forms[0].year.focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getcallCentreReport";
                    document.forms[0].submit();
                    return true;
                }
            }

        </script>
<Style>
   .border_hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
 {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
}
</Style>
        
    </head>
    <body >

        <html:form action="callCentreReport.do?mode=getMonthWiseCallCentreReport">
            <html:hidden property="mode"/>
            <br>
            <%
                        String districtId = "";
                        String month = "";
                        String year = "";
                        if (request.getAttribute("districtId") != null) {
                            districtId = request.getAttribute("districtId").toString();
                        }
                        String urbanId = "";
                        if (request.getAttribute("urbanId") != null) {
                            urbanId = request.getAttribute("urbanId").toString();
                        }
                        if (request.getAttribute("month") != null) {
                            month = request.getAttribute("month").toString();
                        }
                        if (request.getAttribute("year") != null) {
                            year = request.getAttribute("year").toString();
                        }
                        int registerdBeforethismonth = 0, registerdthismonth = 0;
                        int resolvdthismonth = 0, pending = 0;
                        ArrayList reportList = new ArrayList();
                        if (request.getAttribute("DistrictMonthwiseList") != null) {
                            reportList = (ArrayList) request.getAttribute("DistrictMonthwiseList");
                            Iterator iter = reportList.iterator();
                            while (iter.hasNext()) {
                                Map m = (Map) iter.next();
                                registerdBeforethismonth = registerdBeforethismonth + Integer.parseInt(m.get("registerdBeforethismonth").toString());
                                registerdthismonth = registerdthismonth + Integer.parseInt(m.get("registerdthismonth").toString());
                                resolvdthismonth = resolvdthismonth + Integer.parseInt(m.get("resolvdthismonth").toString());
                                pending = pending + Integer.parseInt(m.get("pending").toString());

                            }
                        }
                        int statYear = 2010;
                        int currentYear = 2010;
                        if (request.getAttribute("currentYear") != null) {
                            currentYear = Integer.parseInt(request.getAttribute("currentYear").toString());
                        }
                        ArrayList yearList = new ArrayList();
                        yearList.add(statYear);
                        while (statYear != currentYear) {
                            yearList.add(++statYear);
                        }

            %>
            <p id="errmsg">
                <logic:present name="msg">
                    ${msg}
                </logic:present></p>

            <p id="errmsg">
                <logic:present name="msg">
                    <bean:write name="msg"/> 
                </logic:present></p>

            <table  align="center" cellspacing="1" cellpadding="0" width="90%"  border="0">

               <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                      R6.2 : District and Month Wise Grievances Report
                    </th>
                </tr>
                <tr><td colspan="2">

                       <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="100%" border="0px">
									        <tr height="30"> 
                                <td valign="middle"  >&nbsp;&nbsp;&nbsp;District</td>
                                <td align="left" valign="middle">
                                    <html:select  property="district_id" >
                                        <html:option value="all">--ALL--</html:option>
                                        <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                    </html:select>
                                </td>

                                <td  valign="middle"  >Area Type</td>
                                <td align="left" valign="middle">
                                    <html:select styleId="4" property="urban_id"  >
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:option  value="Rural">Rural</html:option>
                                        <html:option  value="Urban">Urban</html:option>
                                    </html:select>

                                </td>
                             </tr>

                            <tr>
                                <td valign="middle"  >&nbsp;&nbsp;&nbsp;Month<font color="red"><b>*</b></font></td>
                                <td align="left" valign="middle" >
                                    <html:select property="month" >
                                        <html:option value="ALL">--select--</html:option>
                                        <html:option value="1">JAN</html:option>
                                        <html:option value="2">FEB</html:option>
                                        <html:option value="3">MAR</html:option>
                                        <html:option value="4">APR</html:option>
                                        <html:option value="5">MAY</html:option>
                                        <html:option value="6">JUN</html:option>
                                        <html:option value="7">JUL</html:option>
                                        <html:option value="8">AUG</html:option>
                                        <html:option value="9">SEP</html:option>
                                        <html:option value="10">OCT</html:option>
                                        <html:option value="11">NOV</html:option>
                                        <html:option value="12">DEC</html:option>
                                    </html:select>

                                </td>
                                <td valign="middle"  >&nbsp;&nbsp;&nbsp;Year<font color="red"><b>*</b></font></td>
                                <td align="left" valign="middle" >
                                    <html:select property="year" >
                                        <html:option value="ALL">--select--</html:option>

                                        <%
                                                    for (int x = 0; x < yearList.size(); x++) {
                                        %>
                                        <html:option value='<%=yearList.get(x).toString()%>'><%=yearList.get(x).toString()%></html:option>
                                        <%
                                                    }
                                        %>

                                    </html:select>

                                </td>
                            
                                <td>
                                    <html:button property="sub" onclick="return getReport();" value="Submit"/></td>
                             </tr>
									    
									    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
                </td></tr>
              </table><br>
              
                <logic:notEmpty name="DistrictMonthwiseList" scope="request">
                <table  align="center" cellspacing="1" cellpadding="0" width="100%"  border="0">

                    <tr>
                        <td style="text-align: right">

                            <a href="callCentreReport.do?mode=getMonthWiseCallCentreReport&pageType=monthexcel&districtId=<%=districtId%>&month=<%=month%>&year=<%=year%>&urbanId=<%=urbanId%>" target="_blank">
                                <img src="DisabilityUITG/images/excel.jpg" title="Export Excel"
                                                     height="35" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <a href="callCentreReport.do?mode=getMonthWiseCallCentreReport&pageType=monthprint&districtId=<%=districtId%>&month=<%=month%>&year=<%=year%>&urbanId=<%=urbanId%>" target="_blank">
                                 <img src="DisabilityUITG/images/print.gif" height="25" width="25" title="Print"/></a>

                        </td>
                    </tr>

                </table>
              </logic:notEmpty>
              
            <%int i = 1;%>

            <logic:notEmpty name="DistrictMonthwiseList" scope="request">
              
			       <table width="60%" border="0" cellspacing="1" cellpadding="0" align="center" >
			        <tbody>
                
  				<tr >
                  
                        <th class="hd_gd" align="center" valign="middle">
                            SNo.
                        </th>
                          <th class="hd_gd" align="center" valign="middle">District</th>
                        <th class="hd_gd" align="center" valign="middle">No of Complaints Registered </th>
                        <th class="hd_gd" align="center" valign="middle"> No of Complaints Registered till selected Month </th>
                        <th class="hd_gd" align="center" valign="middle"> No of Complaints Resolved till selected Month (i.e. Certificate Issued) </th>
                        <th class="hd_gd" align="center" valign="middle"> No of Complaints Pending </th>

                    </tr>
                    
                    <tr >
                  
                        <th class="hd_gd" align="center" valign="middle">
                            1
                        </th>
                          <th class="hd_gd" align="center" valign="middle">2</th>
                        <th class="hd_gd" align="center" valign="middle">3</th>
                        <th class="hd_gd" align="center" valign="middle">4</th>
                        <th class="hd_gd" align="center" valign="middle">5</th>
                        <th class="hd_gd" align="center" valign="middle">6</th>

                    </tr>
                    
                    
                    <%String classStyle=""; %>
                    <logic:iterate id="row" name="DistrictMonthwiseList" indexId="count">
                
                              <%   if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%>
                        <tr>
                            <td class="<%=classStyle%>" align="center">
                                <%=i++%>.
                            </td>
                            <td class="<%=classStyle%>" align="left">${row.District}</td>
                            <td class="<%=classStyle%>" align="right">${row.registerdBeforethismonth}</td>
                            <td class="<%=classStyle%>" align="right">${row.registerdthismonth}</td>
                            <td class="<%=classStyle%>" align="right">${row.resolvdthismonth}</td>
                            <td class="<%=classStyle%>"  align="right" style="border-right:#234466 1px solid;">${row.pending}</td>

                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  class="hd_gd"  align="center" colspan="2"><b>Total</b></th>
                        <th class="hd_gd" align="center" valign="middle"><b><%=registerdBeforethismonth%></b></th>
                        <th class="hd_gd" align="center" valign="middle"><b><%=registerdthismonth%></b></th>
                        <th class="hd_gd" align="center" valign="middle"><b><%=resolvdthismonth%></b></th>
                        <th class="hd_gd" align="center" valign="middle"><b><%=pending%></b></th>
                    </tbody>
	</table>
 


            </logic:notEmpty>


        </html:form>

    </body>
</html>
