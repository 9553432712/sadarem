<%-- 
    Document   : AgeWiseReport
    Created on : Jun 23, 2011, 10:13:40 AM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<%
            int i = 1;
            String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");

            String a = (String) request.getParameter("maleTot");
            String b = (String) request.getParameter("femaleTot");
            String c = (String) request.getParameter("tot");
            String urban_id = (String) request.getParameter("urban_id");
%>




<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
         
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>


        <script language="javascript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>


        <title>SADAREM</title>

        <%
                    int femaleCount = 0, maleCount = 0, totalCount = 0;
                    ArrayList list = new ArrayList();
                    if (request.getAttribute("ageList") != null) {
                        list = (ArrayList) request.getAttribute("ageList");
                        Iterator iter = list.iterator();
                        while (iter.hasNext()) {
                            Map m = (Map) iter.next();
                            maleCount = maleCount + Integer.parseInt(m.get("male").toString());
                            femaleCount = femaleCount + Integer.parseInt(m.get("female").toString());
                        }
                        totalCount = maleCount + femaleCount;

                    }
        %>
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
           

            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }

            function getDetails() {

                if(eval(document.forms[0].elements['fromAge'].value) > eval(document.forms[0].elements['toAge'].value)) {
                    alert("To Age must be grater than from Age");
                    document.forms[0].elements['fromAge'].value="";
                    document.forms[0].elements['toAge'].value="";
                    return false;
                }else if(document.forms[0].elements['fromAge'].value=="") {
                    alert("Please Enter From Age");
                    document.forms[0].elements['fromAge'].focus();
                    return false;
                }else if(document.forms[0].elements['toAge'].value=="") {
                    alert("Please Enter To Age");
                    document.forms[0].elements['toAge'].focus();
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

                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }

            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }


        </script>

    </head>

    <%if (request.getAttribute("ageList") != null) {%>
    <body>
        <%} else {%>
    <body onload="ShowDate();createDistrictObject();">
        <%}%>

        <html:form action="/ageWiseReports.do" >
                <input type="hidden" name="mode"/>
                <input type="hidden" name="districtName"/>
                <input type="hidden" name="mandalName"/>
                <input type="hidden" name="villageName"/>
                <input type="hidden" name="habitationName"/>
                <input type="hidden" name="qualificationName"/>
                <logic:present name="msg">
                    <center><font color="red">${msg}</font></center>
                </logic:present>
               <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="left" valign="middle" width="100%">
                       <table   width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                         <tr>
                           <th class="hd_gd" align="center" valign="middle">R3.3 : PWD's Age Wise - Details</th> 
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
                              <td>Area Type </td>
                              <td align="left" valign="middle">
                               <html:select styleId="4" property="urban_id" style="text-align:left">
                               <html:option  value="0">--ALL--</html:option>
                               <html:option  value="Rural">Rural</html:option>
                               <html:option  value="Urban">Urban</html:option>
                              </html:select>
                             </td>
                            <td>From Age<font color="red"><b>*</b></font></td>
                             <td><html:text property="fromAge" maxlength="3"/>
                            </td>
                             <td>To Age<font color="red"><b>*</b></font></td>
                              <td><html:text property="toAge" maxlength="3"/>
                             </td>
                           </tr>
                          <tr>
                           <td>From Date<font color="red"><b>*</b></font></td>
                            <td>
                             <input type="text"  id="fromdate"  name="fromdate" value='<%=fromdate%>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                           </td>
                           <td>To Date<font color="red"><b>*</b></font></td>
                           <td>
                            <input type="text"  id="todate"  name="todate" value='<%=todate%>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                         </td>
                         <td colspan="2">
                           <html:button property="but" onclick="getDetails()" value="Submit"/>
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
                  </tr>
                  </table>
                </td>
               </tr>
              </table>
            <br>

            <logic:present name="mag">
                <center><font color="red" size="3"><b><bean:write name="mag"/></b></font></</center>
            </logic:present>
            
            <table width="90%">
              <tr>
               <td align="right" style="padding-left: 1067px;">
                <table> 
                 <tr>
                 <td>
                <logic:iterate name="ageList" id="modify" length="1">
                 <logic:present name="mandal">
                  <th >
                   <a href="ageWiseReports.do?mode=getDetails&dateType=disback&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}&fromage=${modify.fromage}&toage=${modify.toage}" style="color: black">
                    <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0" align="right" title="Back button"/>
                   </a>
                   </th>
                  </logic:present>
                  <logic:present name="village">
                  <th>
                    <a href="ageWiseReports.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}&fromage=${modify.fromage}&toage=${modify.toage}" style="color: black"> 
                    <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0" align="right" title="Back button"/>
                    </a>
                   </th>
                  </logic:present>
                  <logic:present name="habitation">
                     <th>
                      <a href="ageWiseReports.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}&fromage=${modify.fromage}&toage=${modify.toage}" style="color: black"> 
                      <img src="<%=request.getContextPath()%>/images/but_back.png" height="30" width="30" border="0" align="right" title="Back button"/>
                     </a>
                     </th>
                  </logic:present>
               </logic:iterate>
               </td>
               </tr>
               </table>
               </td>
               <td  align="right" width="89%" >
                 <logic:present name="district">
                      <logic:iterate name="ageList" id="row" length="1">
                            <td>
                                <a href="ageWiseReports.xls?mode=getDetails&returnType=Excel&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
                                </a>
                            </td>
                            <td>
                                <a href="ageWiseReports.do?mode=getDetails&returnType=Print&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img src="<%=request.getContextPath()%>/images/printer.gif" height="30" width="30" border="0"align="right" title="Print perview"/>
                                </a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="mandal">
                        <logic:iterate name="ageList" id="row" length="1">
                            <td>
                                <a href="ageWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
                                </a> 
                            </td>
                            <td>
                                <a href="ageWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img src="<%=request.getContextPath()%>/images/printer.gif" height="30" width="30" border="0"align="right" title="Print perview"/>
                                </a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="village">
                        <logic:iterate name="ageList" id="row" length="1">
                            <td>
                                <a href="ageWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
                                 </a>
                            </td>
                            <td>
                                <a href="ageWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                                    <img src="<%=request.getContextPath()%>/images/printer.gif" height="30" width="30" border="0"align="right" title="Print perview"/>
                                </a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                <logic:present name="habitation">
                 <logic:iterate name="ageList" id="row" length="1">
				 <a href="ageWiseReports.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
                   <img src="<%=request.getContextPath()%>/images/printer.gif" height="30" width="30" border="0"align="right" title="Print perview"/>
                </a>
                 
               <a href="ageWiseReports.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}" target="_blank">
				<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
			  </a>
              </logic:iterate>
              </logic:present>
              </td> 
              </tr>
            </table>

            <logic:notEmpty name="ageList">
             

                <table  align="center" cellspacing="1" border="0" cellpadding="4" width="90%">
                    <tr>
                        <logic:present name="names">
                            <th style="text-align: center" colspan="11" class="hd_gd">
                                <bean:write name="names"/>
                            </th>
                        </logic:present></tr>
                    <tr>
                        <th align="center" width="10%"  class="hd_gd">S.No</th>
                        <th align="center" width="20%"  class="hd_gd"><bean:write name="ExcelHeader"/></th>
                        <th align="center"  width="20%" class="hd_gd">Male</th>
                        <th align="center" width="20%"  class="hd_gd">Female</th>
                        <th align="center"  width="20%" class="hd_gd">Total</th>
                    </tr>
                      
                     <tr>
                      <th align="center" class="hd_gd">1</th>
                      <th align="center" class="hd_gd">2</th>
                      <th align="center" class="hd_gd">3</th>
                      <th align="center" class="hd_gd">4</th>
                      <th align="center" class="hd_gd">5</th>
                    </tr>


                    <bean:define id="maleTotal" value="0"/>
                    <bean:define id="femaleTotal" value="0"/>
                    <bean:define id="total" value="0"/>

                    <logic:iterate name="ageList" id="row" indexId="count">
                     <%String classStyle=""; %>
                       <% if(count.intValue()%2==0)
              		 {
                    	  classStyle="secondrow";
                	 }
                      else
                      {
                    	  classStyle="firstrow";
                    	  
                      } %>


                        <bean:define id="maleTotal" value="${maleTotal+row.male}"/>
                        <bean:define id="femaleTotal" value="${femaleTotal+row.female}"/>
                        <bean:define id="total" value="${total+row.total}"/>
                        <tr>
                            <td  align="center" style="width: 5%;text-align: center;" class="<%=classStyle%>">
                                <%=i++%>.
                            </td>
                            <logic:present name="district">
                                <td class="<%=classStyle%>">
                                    <a href="ageWiseReports.do?mode=getDetails&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromage=${row.fromage}&toage=${row.toage}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td class="<%=classStyle%>">
                                    <a href="ageWiseReports.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromage=${row.fromage}&toage=${row.toage}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td class="<%=classStyle%>">
                                    <a href="ageWiseReports.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}&fromage=${row.fromage}&toage=${row.toage}"> ${row.name}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td class="<%=classStyle%>">
                                    ${row.name}
                                </td>
                            </logic:present>
                            <td  class="<%=classStyle%>" align="center" style="width: 10%;;text-align: center;" >
                                <a href="javascript:void(0);" onclick ="window.open('ageWiseReports.do?status=getPersonalAgeDetails&dID=${row.district_id}&mID=${row.mandal_id}&urbanId=<%=urban_id%>&vID=${row.village_id}&habID=${row.habitation_id}&habName=${row.name}&gender=1&fDate=${row.fromdate}&toDate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male}</a>
                            </td>
                            <td  class="<%=classStyle%>" align="center" style="width: 10%;;text-align: center;">
                                <a href="javascript:void(0);" onclick ="window.open('ageWiseReports.do?status=getPersonalAgeDetails&dID=${row.district_id}&mID=${row.mandal_id}&urbanId=<%=urban_id%>&vID=${row.village_id}&habID=${row.habitation_id}&habName=${row.name}&gender=2&fDate=${row.fromdate}&toDate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.female}</a>
                            </td>
                            <td class="<%=classStyle%>" align="center" style="width: 10%;text-align: center;  border-right:#234466 1px solid;">
                                <a href="javascript:void(0);" onclick ="window.open('ageWiseReports.do?status=getPersonalAgeDetails&dID=${row.district_id}&mID=${row.mandal_id}&urbanId=<%=urban_id%>&vID=${row.village_id}&habID=${row.habitation_id}&habName=${row.name}&gender=12&fDate=${row.fromdate}&toDate=${row.todate}&fromAge=${row.fromage}&toAge=${row.toage}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.male+row.female}</a>
                            </td>
                        </tr>

                    </logic:iterate>

                    <tr>
                        <th  align="center" colspan="2" class="hd_gd">
                            Total
                        </th>
                        <th  align="center" class="hd_gd">
                            <b><%=maleCount%></b>
                            <input type="hidden" value="<%=maleCount%>" name="maleTot"/>
                        </th>
                        <th  align="center" class="hd_gd">
                            <b><%=femaleCount%></b>
                            <input type="hidden" value="<%=femaleCount%>" name="femaleTot"/>
                        </th>
                        <th  align="center" class="hd_gd">
                            <b><%=totalCount%></b>
                            <input type="hidden" value="<%=totalCount%>" name="tot"/>
                        </th>
                    </tr>
                </table>
                <br/>
                </logic:notEmpty>

        </html:form>
    </body>
</html:html>


