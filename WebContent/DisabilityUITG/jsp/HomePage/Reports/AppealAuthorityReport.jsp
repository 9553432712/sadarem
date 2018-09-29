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
            String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");
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
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.js"></script> 
         <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <script language="JavaScript">
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
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

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
    <body onload="ShowDate() ">
        <html:form  action="AppealAuthorityReport.do"  method="post"  >
            <input type="hidden" name="mode"/>
            <input type="hidden" name="districtName"/> 
            <input type="hidden" name="mandalName"/>
            <input type="hidden" name="villageName"/>
            <input type="hidden" name="habitationName"/>
            <input type="hidden" name="qualificationName"/>
            <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                       R5.1 : Appellate Authority Registered Status Report
                    </th> 
                </tr>
                <tr>
                    <td>
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
                                <td >From Date<font color="red"><b>*</b></font>
                                   <input type="text"  id="fromdate"  name="fromdate" value='<%=fromdate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                   
                                     
                                </td>
                                <td >To Date<font color="red"><b>*</b></font>
                                    <input type="text"  id="todate"  name="todate" value='<%=todate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                   
                                    
                                </td>
                            
                                <td colspan="4" >
                                    <html:button property="but" onclick="getDetails()" value="Submit"/>
                                </td>
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
            <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center"><tr align="center"><td>
                        <%if (msg != null) {%>
                        <p id="errmsg"><%=msg%></p>
                        <%}%>

                    </td></tr></table>

            <logic:present name="empWiseDetails">
                <logic:empty name="empWiseDetails">
                    <p id="errmsg">No Data</p>
                </logic:empty>
            </logic:present>
            <logic:notEmpty name="empWiseDetails">
              
                <table  align="center" cellspacing="1" border="0" cellpadding="0"  width="95%">
                    <tr>
                        <logic:present name="names">
                            <td style="text-align: left" colspan="8">
                             <font size="2" color="#234466"> <b> <bean:write name="names"/></b></font> 
                            </td>
                        </logic:present>
                        
                        <logic:iterate name="empWiseDetails" id="row" length="1">
                            <logic:present name="mandal">
                                <th >
                                    <a href="AppealAuthorityReport.do?mode=getDetails&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> <img src="DisabilityUITG/images/but_back.png"
                                             height="25" width="25" title="Back"/></a>
                                </th>
                            </logic:present>
                            <logic:present name="village">
                                <th>
                                    <a href="AppealAuthorityReport.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> <img src="DisabilityUITG/images/but_back.png"
                                             height="25" width="25" title="Back"/></a>
                                </th>
                            </logic:present>

                            <logic:present name="habitation">
                                <th>
                                    <a href="AppealAuthorityReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fDate}&todate=${row.tDate}" style="color: black"> <img src="DisabilityUITG/images/but_back.png"
                                             height="25" width="25" title="Back"/></a>
                                </th>
                            </logic:present>
                        </logic:iterate>

                       
                        <th align="right" valign="middle"> <a href="AppealAuthorityReport.xls?status=getempWiseReport&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                         <img src="DisabilityUITG/images/excel.jpg"
                                             height="35" width="25" title="Export Excel"/></a> &nbsp; &nbsp; &nbsp;
                    <a href="AppealAuthorityReport.do?status=getempWiseReportPrint&names=<%=names%>&villagesId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&fromdate=${fDate}&todate=${tDate}&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>" target="_blank">
                         <img src="DisabilityUITG/images/print.gif"  height="35" width="25" title="Print"/></a>
                </th>
                     </tr>
                    </table>
                    
                   
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
                  
                        <th class="hd_gd" align="center" valign="middle">
                        S.No</th>
                        <logic:present name="district">
                              <th class="hd_gd" align="center" valign="middle">District</th>
                        </logic:present>
                        <logic:present name="mandal">
                             <th class="hd_gd" align="center" valign="middle">Mandal</th>
                        </logic:present>
                        <logic:present name="village">
                             <th class="hd_gd" align="center" valign="middle">Village</th>
                        </logic:present>
                        <logic:present name="habitation">
                              <th class="hd_gd" align="center" valign="middle">Habitation</th>
                        </logic:present>
                          <th class="hd_gd" align="center" valign="middle">Locomotor</th>
                          <th class="hd_gd" align="center" valign="middle">Visual Impairment</th>
                          <th class="hd_gd" align="center" valign="middle">Hearing Impairment</th>
                         <th class="hd_gd" align="center" valign="middle">Mental Retardation</th>
                         <th class="hd_gd" align="center" valign="middle">Menal Illness</th>
                         <th class="hd_gd" align="center" valign="middle">Multiple Disability</th>
                         <th class="hd_gd" align="center" valign="middle">Total</th>
                    </tr>
                    
                    <tr>
                          <th class="hd_gd" align="center" valign="middle">1</th>
                          <th class="hd_gd" align="center" valign="middle">2</th>
                          <th class="hd_gd" align="center" valign="middle">3</th>
                         <th class="hd_gd" align="center" valign="middle">4</th>
                         <th class="hd_gd" align="center" valign="middle">5</th>
                         <th class="hd_gd" align="center" valign="middle">6</th>
                         <th class="hd_gd" align="center" valign="middle">7</th>
                         <th class="hd_gd" align="center" valign="middle">8</th>
                         <th class="hd_gd" align="center" valign="middle">9</th>
                    
                    </tr>
                    
                   <bean:define id="ohtotal" value="0"/>
                    <bean:define id="vitotal" value="0"/>
                    <bean:define id="hitotal" value="0"/>
                    <bean:define id="mrtotal" value="0"/>
                    <bean:define id="miptotal" value="0"/>
                    <bean:define id="total" value="0"/>
                    <bean:define id="rowwisetotal" value="0"/>

                       <%String classStyle=""; %> 
                    <logic:iterate name="empWiseDetails" id="row" indexId="count">

                           <%if(count.intValue()%2 == 0){ 
                              classStyle="firstrow";
                           }
                           else
                           {
                              classStyle="secondrow";
                           }
                           
                           
                           %>
                        <bean:define id="ohtotal" value="${ohtotal+row.oh}"/>
                        <bean:define id="vitotal" value="${vitotal+row.vi}"/>
                        <bean:define id="hitotal" value="${hitotal+row.hi}"/>
                        <bean:define id="mrtotal" value="${mrtotal+row.mr}"/>
                        <bean:define id="mitotal" value="${mitotal+row.mi}"/>
                        <bean:define id="total" value="${total+row.multi}"/>
                        <bean:define id="rowwisetotal" value="${row.oh+row.vi+row.hi+row.mr+row.mi+row.multi}"/>
                        <bean:define id="fDate" value="${row.fDate}"/>
                        <bean:define id="tDate" value="${row.tDate}"/>

                        <tr>
                            <td class=<%=classStyle%> style="text-align: center">
                                <%=i++%>
                            </td>
                            <logic:present name="district">
                                <td class=<%=classStyle%>>
                                   <%--  <a href="AppealAuthorityReport.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fDate}&todate=${row.tDate}"> --%> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td class=<%=classStyle%>>
                                   <%--  <a href="AppealAuthorityReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fDate}&todate=${row.tDate}"> --%> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td class=<%=classStyle%>>
                                    <%-- <a href="AppealAuthorityReport.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fDate}&todate=${row.tDate}"> --%> ${row.name}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td class=<%=classStyle%>>
                                    ${row.name}
                                </td>
                            </logic:present>
                            <td  class=<%=classStyle%> style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Locomotor&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=1&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.oh}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: center">
                               <%--  <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Visual%20Impairment&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=2&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.vi}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Hearing%20Impairment&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=3&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.hi}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Mental%20Retardation&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=4&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.mr}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Mental%20Illness&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=5&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.mi}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('AppealAuthorityReport.do?empStatus=getempDetails&dID=${row.district_id}&disability=Multiple%20Disabiliyt&names=<%=names%>&mID=${row.mandal_id}&vID=${row.village_id}&emp=6&hid=${row.hab_id}&fromdate=${row.fDate}&todate=${row.tDate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.multi}</a>
                            </td>
                            <td  class=<%=classStyle%> style="text-align: right">
                                ${rowwisetotal}
                            </td>
                        </tr>
                    </logic:iterate>
                    <tr>
                          <th class="hd_gd" align="center" valign="middle">
                        </th>
                          <th class="hd_gd" align="center" valign="middle">
                            Total
                        </th>
                          <th class="hd_gd" align="center" valign="middle">${ohtotal}</th>
                          <th class="hd_gd" align="center" valign="middle">${vitotal}</th>
                          <th class="hd_gd" align="center" valign="middle">${hitotal}</th>
                          <th class="hd_gd" align="center" valign="middle">${mrtotal}</th>
                          <th class="hd_gd" align="center" valign="middle">${mitotal}</th>
                          <th class="hd_gd" align="center" valign="middle">${total}</th>
                          <th class="hd_gd" align="center" valign="middle">${ohtotal+vitotal+hitotal+mrtotal+mitotal+total}</th>
                    </tr>
               </tbody>
	</table>
 
                   
            </logic:notEmpty>
        </html:form>
    </body>
</html:html>