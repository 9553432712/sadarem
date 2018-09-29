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
<%@page import="java.util.Iterator,com.tcs.sadarem.util.CommonUtility" %>
<%@page contentType="text/html"%>
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
     <title>SADAREM</title>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <%
                    int i = 1;
                    String fionance = null;
                    String selFromDate="",selToDate="";
                    ArrayList PecentageWiseData = null;
                    if (request.getAttribute("FinancialYearWise") != null) {
                        fionance = request.getAttribute("FinancialYearWise").toString();
                    }
                    if (request.getAttribute("PecentageWiseData") != null) {
                        PecentageWiseData = (ArrayList) request.getAttribute("PecentageWiseData");
                    }
                    
                    selFromDate = CommonUtility.checkNullObj(request.getAttribute("selFromDate"));
                    selToDate = CommonUtility.checkNullObj(request.getAttribute("selToDate"));
                    

        %>
        <script>
        $(document).ready(function() 
        		{
        	
 
        		new JsDatePick({
        	    	 
//        			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
          			useMode:2,
          			target:"fromdate",
          			//dateFormat:"%d-%M-%Y",
          			//selectedDate:{day:25,month:12,year:2013},
          			yearsRange:[2009,2100],
          			minDate: 0,
          			limitToToday:true,
          			cellColorScheme:"beige",
          			dateFormat:"%d/%m/%Y",
          			imgPath:"img/",
          			weekStartDay:1
                 });	
        		
        g_calendarObject_end = new JsDatePick({
        	    	 
//        			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
          			useMode:2,
          			target:"todate",
          			//dateFormat:"%d-%M-%Y",
          			//selectedDate:{day:25,month:12,year:2013},
          			yearsRange:[2009,2100],
          			minDate: 0,
          			limitToToday:true,
          			cellColorScheme:"beige",
          			dateFormat:"%d/%m/%Y",
          			imgPath:"img/",
          			weekStartDay:1
                 });	
                });
        
            var typeOfselection="";
            var reportType="";
            <%
                        if (request.getAttribute("typeOfSearch") != null) {%>
                            typeOfselection='<%=request.getAttribute("typeOfSearch")%>';
            <%}%>
            
                function insertDetails(){
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
                        document.forms[0].mode.value='getAadharMappingDetails';
                        document.forms[0].submit();
                    }
              
                }
//                 function ShowDate()
//                 {
//                     var dt = new Date();
//                     var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
//                     document.getElementById(8).value = d;

//                 }
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
    <body onload=" OnBodyLoad(2,3);">
        <html:form action="/rejectedAadharMapping" >
            <html:hidden property="mode"/>

            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>
            <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                        R1.4 : Rejected AADHAR Mapping Reports
                    </th>
                </tr>
                
                <tr><td>
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
										     <td > District</td>
										     <td>
											 <html:select styleId="1" property="district_id"  >
					                            <html:option  value="All"> All </html:option>
					                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
					                        </html:select>
										     </td>
										     <td>
									        	From Date
									        </td>
									        <td>
									         <input type="text"  id="fromdate"  name="fromdate" value='<%=selFromDate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                    
									        </td>
									        
										      <td>
									       		To Date
									       	</td>
									       	<td>
									         	  <input type="text"  id="todate"  name="todate" value='<%=selToDate%>'   style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                    
									         </td>
										    
									          <td  valign="middle"  >
									           	<input type="button" name="card" value="Submit" class="button" onclick="return insertDetails();">
									         </td>
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
            <logic:present name="mappingDetails">
                <table  align="center"   border="0" cellpadding="0" width="80%"  >
                    <tr>
                        <logic:present name="District">
                        <logic:iterate id="row" length="1" name="mappingDetails">
                                <td colspan="5">
                           <b> District:</b>&nbsp;All &nbsp;&nbsp;<b>From Date:</b>&nbsp;${row.fromdate}&nbsp;&nbsp; <b>To Date:</b>&nbsp;${row.todate}
                        </td>
                        <td  style="text-align: center">
                                <a href="rejectedAadharMapping.xls?mode=excelWriting&districtid=${row.districtid}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:iterate>
                        </logic:present>
                         <logic:present name="Mandal">
                        <logic:iterate id="row" length="1" name="mappingDetails">
                                <td colspan="5">
                           <b>District:</b> &nbsp;${districtName} &nbsp;&nbsp;<b>From Date:</b>&nbsp;${row.fromdate}&nbsp;&nbsp; <b>To Date:</b>&nbsp;${row.todate}
                        </td>
                        <td  style="text-align: center">
                                <a href="rejectedAadharMapping.xls?mode=excelWriting&districtName=${districtName}&districtid=${row.districtid}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                        </logic:iterate>
                        </logic:present>
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
                
  				<tr >
                  
                        <th class="hd_gd" align="center" valign="middle">
                            S.No
                        </th>
                        <logic:present name="District">
                            <th class="hd_gd" align="center" valign="middle">
                            District
                        </th>
                        </logic:present>
                        <logic:present name="Mandal">
                            <th class="hd_gd" align="center" valign="middle">
                            Mandal
                        </th>
                        </logic:present>
                        <th class="hd_gd" align="center" valign="middle">
                            Target
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Mapped
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Pending
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Mapped Percentage(%)
                        </th>
                    </tr>
                    <tr>
                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (1)
                        </th>
                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (2)
                        </th>

                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (3)
                        </th>
                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (4)
                        </th>
                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (5)
                        </th>
                        <th style="text-align: center" class="hd_gd" align="center" valign="middle">
                            (6)
                        </th>
                    </tr>
                    <%String classStyle=""; %>
                    <logic:iterate id="row" name="mappingDetails" indexId="count">
                    
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
                                <%=i++%>
                            </td>
                            <logic:present name="District">
                                <td class="<%=classStyle%>">
                                ${row.districtname}
                            </td>
                            </logic:present>
                            <logic:present name="Mandal">
                                <td class="<%=classStyle%>">
                                ${row.mandalname}
                            </td>
                            </logic:present>
                            <td class="<%=classStyle%>" style="text-align: right">
                                ${row.totalTarget1}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: right">
                                ${row.mappedcount}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: right">
                                ${row.target}
                            </td>
                            <td class="<%=classStyle%>" style="text-align: right">
                                ${row.mappedPercentage}
                            </td>
                        </tr>
                    </logic:iterate>
                        <tr>
                        <th colspan="2" class="hd_gd" align="center" valign="middle">
                            <b>Total</b>
                        </th>
                        <th style="text-align: center" class="hd_gd" align="right" valign="middle">
                            <b>${row.totalTargetCount}</b>

                        </th>
                        <th style="text-align: center" class="hd_gd" align="right" valign="middle">
                            <b> ${row.totalmappedcount}</b>

                        </th>
                        <th style="text-align: center" class="hd_gd" align="right" valign="middle">
                            <b>${row.totaltarget}</b>
                        </th>
                        <th style="text-align: center" class="hd_gd" align="right" valign="middle">
                            <b>${row.TotalmappedPercentage}</b>

                        </th>
                    </tr>
                                                      </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
            </logic:present>
        </html:form>

    </body>
</html>
