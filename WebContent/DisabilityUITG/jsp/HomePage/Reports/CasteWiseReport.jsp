<%-- 
    Document   : CasteWiseReport
    Created on : Jun 21, 2011, 3:33:27 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.util.Iterator,com.tcs.sadarem.util.CommonUtility" %>

<%
            int i = 1;
            String SelFromDate = (String) request.getParameter("fromdate");
            String selFromDate="",selToDate="";
            selFromDate = CommonUtility.checkNullObj(request.getAttribute("fromdate"));
            selToDate = CommonUtility.checkNullObj(request.getAttribute("todate"));
            String SelToDate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            if (district_id == null) {
                district_id = "0";
            }
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");
            String caste = (String) request.getParameter("caste");
            String urban_id = (String) request.getParameter("urban_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");
            ArrayList list = (ArrayList) request.getAttribute("casteWiseDetails");



%>




<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.s"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>




        <script >
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
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }

            function getDetails() {
                if( validate_form(document.forms[0])==false){
                    return false;
                }else{
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                    return true;
                }
            }
            function getData() {
                document.forms[0].mode.value="hide";
                document.forms[0].submit();
            }

            function hideMode() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }
           
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
            function validate_form(thisform)
            {
                with (thisform)
                {
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
                    if (validate_required(fromDate,"From Date must be selected!")==false)
                    {
                        document.forms[0].fromdate.focus();
                        return false
                    }
                    if (validate_required(toDate,"To Date must be selected!")==false)
                    {
                        document.forms[0].todate.focus();
                        return false
                    }
                    if (today < etd)
                    {
                        alert("From date is before Today's Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date does not exceed Today's Date");
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

        </script>

    </head>

    <%if (request.getAttribute("casteWiseDetails") != null) {%>
    <body>
        <%} else {%>
    <body onload="ShowDate();createDistrictObject();">
        <%}%>


        <html:form action="/casteWiseReports.do" >


            <input type="hidden" name="mode"/>
            <input type="hidden" name="districtName"/>
            <input type="hidden" name="mandalName"/>
            <input type="hidden" name="villageName"/>
            <input type="hidden" name="habitationName"/>
            <input type="hidden" name="qualificationName"/>

           <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                <tr>
                    <td>
                    
     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle"> R3.2 : PWD's Caste Wise - Details </th>
					  </tr>
					  <tr>
					  <th>&nbsp;&nbsp;&nbsp;&nbsp;
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
									         <tr>
                                <td valign="middle" width="12%">Area Type</td>
                                <td align="left" valign="middle">
                                    <html:select styleId="4" property="urban_id"  onchange="districtList();">
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:option  value="Rural">Rural</html:option>
                                        <html:option  value="Urban">Urban</html:option>
                                    </html:select>
                                </td>
                                <%-- <td>From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromdate" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromdate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td>To Date<font color="red"><b>*</b></font>
                                    <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].todate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td> --%>
                                 <td>
									        	From Date
									        </td>
									        <td>
									          <input type="text"  id="fromdate"  name="fromdate" value="<%=selFromDate%>" style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
									        </td>
									        <td>
									       		To Date
									       	</td>
									       	<td>
									         	<input type="text" id="todate" name="todate"  value="<%=selToDate%>" style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
									         </td>
                                 <th colspan="4">
                                    <html:button property="but" onclick="getDetails()" value="Submit"/>
                                </th>
                          
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
					    	
						</form>
  					</td>
  				</tr>
  				</table>
  				<%
                        if (list != null) {
                            if (list.size() > 0) {%>
  				<logic:notEmpty name="casteWiseDetails">
  				<table width="90%" height="15px">
  				
				                   <tr>
				                    <logic:iterate name="casteWiseDetails" id="modify" length="1">
                            <logic:present name="mandal">
                                <th align="right" width="89%">
                                    <a href="casteWiseReports.do?mode=getDetails&dateType=disback&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" >
                                    <img   height="35" width="35"  src="DisabilityUITG/images/but_back.png" border="0" align="right"/></a>
                                </th>
                            </logic:present>
                            <logic:present name="village">
                                <th align="right" width="89%">
                                    <a href="casteWiseReports.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" >
                                    <img   height="35" width="35"  src="DisabilityUITG/images/but_back.png" border="0" align="right"/></a>
                                </th>
                            </logic:present>

                            <logic:present name="habitation">
                                <th align="right" width="89%">
                                    <a href="casteWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" >
                                    <img   height="35" width="35"  src="DisabilityUITG/images/but_back.png" border="0" align="right"/></a>
                                </th>
                            </logic:present>
                        </logic:iterate>
						             <logic:present name="district">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td align="right" width="89%">
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    <img  height="35" width="35"  src="DisabilityUITG/images/excel.jpg" border="0" align="right"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                           
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="mandal">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td align="right" width="89%">
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    <img   height="35" width="35"  src="DisabilityUITG/images/excel.jpg" border="0" align="right"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                       
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="village">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td align="right" width="89%">
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    <img   height="35" width="35"   src="DisabilityUITG/images/excel.jpg" border="0" align="right"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                           
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="habitation">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td align="right" width="89%">
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    <img   height="35" width="35"   src="DisabilityUITG/images/excel.jpg" border="0" align="right"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                         
                        </logic:iterate>
                    </logic:present>
                     </tr>
						       </table> 
  				</logic:notEmpty>
  				 <%}
                        }%>
  				                   
                        
                        
                        
                        
                    </td>
                </tr>
            </table>
<br>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>
            <br>

            <%
                        if (list != null) {
                            if (list.size() > 0) {%>
            <logic:notEmpty name="casteWiseDetails">

                <table   align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">
                    <tr>
                       
                        <logic:present name="names">
                            <th class="hd_gd" style="text-align: center" colspan="11">
                                <bean:write name="names"/>
                            </th>
                        </logic:present></tr>
                    <tr>

                        <th class="hd_gd" style="text-align: center" >S.No</th>
                        <logic:present name="district">
                            <th class="hd_gd">District</th>
                        </logic:present>
                        <logic:present name="mandal">
                            <th class="hd_gd">Mandal</th>
                        </logic:present>
                        <logic:present name="village">
                            <th class="hd_gd">Village</th>
                        </logic:present>
                        <logic:present name="habitation">
                            <th class="hd_gd">Habitation</th>
                        </logic:present>
                        <th class="hd_gd" style="text-align: center" >ST</th>
                        <th class="hd_gd" style="text-align: center" >SC</th>
                        <th class="hd_gd" style="text-align: center" >BC</th>
                        <th class="hd_gd" style="text-align: center" >OC</th>
                        <th class="hd_gd" style="text-align: center" >Minority</th>
                        <th class="hd_gd" style="text-align: center" >NA</th>

                    </tr>
                    <tr>
                    <th class="hd_gd" style="text-align: center" >1</th>
                    <th class="hd_gd" style="text-align: center" >2</th>
                    <th class="hd_gd" style="text-align: center" >3</th>
                    <th class="hd_gd" style="text-align: center" >4</th>
                    <th class="hd_gd" style="text-align: center" >5</th>
                    <th class="hd_gd" style="text-align: center" >6</th>
                    <th class="hd_gd" style="text-align: center" >7</th>
                    <th class="hd_gd" style="text-align: center" >8</th>
                    
                    </tr>

                    <bean:define id="stTotal" value="0"/>
                    <bean:define id="scTotal" value="0"/>
                    <bean:define id="bcTotal" value="0"/>
                    <bean:define id="ocTotal" value="0"/>
                    <bean:define id="minaTotal" value="0"/>
                    <bean:define id="naTotal" value="0"/>
            <%String classStyle=""; %>
                    <logic:iterate name="casteWiseDetails" id="row" indexId="count">
                  <%  if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%> 
                        <bean:define id="stTotal" value="${stTotal + row.st}"/>
                        <bean:define id="scTotal" value="${scTotal + row.sc}"/>
                        <bean:define id="bcTotal" value="${bcTotal + row.bc}"/>
                        <bean:define id="ocTotal" value="${ocTotal + row.oc}"/>
                        <bean:define id="minaTotal" value="${minaTotal + row.mina}"/>
                        <bean:define id="naTotal" value="${naTotal + row.na}"/>
                        <tr>
                            <td class="<%=classStyle%>" style="text-align: center" style="width: 5%;">
                                <%=i++%>
                            </td>
                            <logic:present name="district">
                                <td class="<%=classStyle%>">
                                   <%--  <a href="casteWiseReports.do?mode=getDetails&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}">  --%>${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td class="<%=classStyle%>">
                                    <%-- <a href="casteWiseReports.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}"> --%> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td class="<%=classStyle%>">
                                   <%--  <a href="casteWiseReports.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}"> --%> ${row.name}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td class="<%=classStyle%>">
                                    ${row.name}
                                </td>
                            </logic:present>
                            <td class="<%=classStyle%>" style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=1&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.st}</a>
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=2&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.sc}</a>
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=3&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.bc}</a>
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=4&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.oc}</a>
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center" style="width: 10%;">
                                <%-- <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=5&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.mina}</a>
                            </td>
                            <td class="<%=classStyle%>" style="text-align: center;border-right:#234466 1px solid;">
                            <%--     <a href="javascript:void(0);" onclick ="window.open('casteWiseReports.do?casteStatus=getcasteDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.hab_id}&caste=6&fDate=${fromdate}&urbanId=<%=urban_id%>&toDate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.na}</a>
                            </td>
                        </tr>

                    </logic:iterate>

                    <tr>
                        <th class="hd_gd" colspan="2" align="center">
                            Total
                        </th>
                        <th class="hd_gd" style="text-align: center">
                            ${stTotal}
                        </th>
                        <th class="hd_gd" style="text-align: center">
                            ${scTotal}
                        </th>
                        <th class="hd_gd" style="text-align: center">
                            ${bcTotal}
                        </th><th class="hd_gd" style="text-align: center">
                            ${ocTotal}
                        </th><th class="hd_gd" style="text-align: center">
                            ${minaTotal}
                        </th>

                        <th class="hd_gd" style="text-align: center">
                            ${naTotal}
                        </th>


                    </tr>

                </table>
                <br/>
                <%-- <table style="text-align: center">
                    <logic:present name="district">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td>
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="casteWiseReports.do?mode=getDetails&returnType=Print&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="mandal">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td>
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="casteWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="village">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td>
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="casteWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="habitation">
                        <logic:iterate name="casteWiseDetails" id="row" length="1">
                            <td>
                                <a href="casteWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="casteWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <%session.setAttribute("casteWiseDetails", (ArrayList) request.getAttribute("casteWiseDetails"));%>
                     <a href="casteWiseReports.xls?modes=getCasteWiseReportExcelExport&urbanId=<%=urban_id%>&villageId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>&caste=<%=request.getParameter("caste")%>&fromDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                         Excel <img src="DisabilityUITG/images/excel.jpg"
                                    height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                    <a href="casteWiseReports.xls?modes=getCasteWiseReportPrintExport&urbanId=<%=urban_id%>&villageId=<%=village_id%>&mandalId=<%=mandal_id%>&districtId=<%=district_id%>&villageName=<%=vName%>&mandalName=<%=mName%>&districtName=<%=dName%>&caste=<%=request.getParameter("caste")%>&fromDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                   
                </table> --%>
            </logic:notEmpty>
            <%}
                        }%>

        </html:form>
    </body>
</html:html>


