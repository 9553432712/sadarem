<%-- 
    Document   : MaritalStatusReport
    Created on : Jul 11, 2011, 11:01:31 AM
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
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");
            String selFromDate="",selToDate="";
            selFromDate = CommonUtility.checkNullObj(request.getAttribute("fromdate"));
            selToDate = CommonUtility.checkNullObj(request.getAttribute("todate"));
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");
            String urban_id = (String) request.getParameter("urban_id");

%>




<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script language="javascript" src="./DisabilityUITG/js/Ajax.js"></script>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
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
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }


        </script>

    </head>

    <%if (request.getAttribute("maritalStatus") != null) {%>
    <body>
        <%} else {%>
    <body onload="ShowDate();createDistrictObject();">
        <%}%>


        <html:form action="/maritalStatus.do" >
            <input type="hidden" name="mode"/>

         
            
            
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R3.4 : PWD's Marital Status Wise - Details </th>
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
                                 <td colspan="8" style="text-align: center;">
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
					    	
						
  					</td>
  				</tr>
  				</table>

 <table  width="90%">
 <tr>
                            <logic:iterate name="maritalStatus" id="modify" length="1">
                                <logic:present name="mandal">
                                    <th align="right"  width="89%">
                                        <a href="maritalStatus.do?mode=getDetails&dateType=disback&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" > 
                                        <img align="right" src="DisabilityUITG/images/but_back.png" height="35" width="35"/></a>
                                    </th>
                                </logic:present>
                                <logic:present name="village">
                                    <th align="right"  width="89%">
                                        <a href="maritalStatus.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" > 
                                       <img align="right" src="DisabilityUITG/images/but_back.png" height="35" width="35"/></a>
                                    </th>
                                </logic:present>

                                <logic:present name="habitation">
                                    <th align="right"  width="89%">
                                        <a href="maritalStatus.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" >
                                        <img align="right" src="DisabilityUITG/images/but_back.png" height="35" width="35"/></a>
                                    </th>
                                </logic:present>
                            </logic:iterate>
 
 
 
                        <logic:present name="district">
                            <logic:iterate name="maritalStatus" id="row" length="1">
                                <td  align="right" width="89%" >
                                    <a href="maritalStatus.xls?mode=getDetails&returnType=Excel&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        <img align="right" src="DisabilityUITG/images/excel.jpg" height="35" width="35"/></a> &nbsp; &nbsp; &nbsp;
                                </td>
                                <%-- <td>
                                    <a href="maritalStatus.do?mode=getDetails&returnType=Print&dateType=dateType&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td> --%>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="mandal">
                            <logic:iterate name="maritalStatus" id="row" length="1">
                                <td  align="right" width="89%" >
                                    <a href="maritalStatus.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        <img align="right" src="DisabilityUITG/images/excel.jpg" height="35" width="35"/></a> &nbsp; &nbsp; &nbsp;
                                </td>
                                <%-- <td>
                                    <a href="maritalStatus.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td> --%>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="village">
                            <logic:iterate name="maritalStatus" id="row" length="1">
                                <td  align="right" width="89%" >
                                    <a href="maritalStatus.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        <img align="right" src="DisabilityUITG/images/excel.jpg" height="35" width="35"/></a> &nbsp; &nbsp; &nbsp;
                                </td>
                               <%--  <td>
                                    <a href="maritalStatus.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td> --%>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="habitation">
                            <logic:iterate name="maritalStatus" id="row" length="1">
                                <td  align="right" width="89%" >
                                    <a href="maritalStatus.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        <img align="right" src="DisabilityUITG/images/excel.jpg" height="35" width="35"/></a> &nbsp; &nbsp; &nbsp;
                                </td>
                             <%--    <td>
                                    <a href="maritalStatus.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td> --%>
                            </logic:iterate>
                        </logic:present>
                     
                       </tr>                   
                        </table>




    
         <br>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>
            <br>
            <logic:present name="maritalStatus">
                <logic:notEmpty name="maritalStatus">

                    <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">
                        <tr>
                           
                            <logic:present name="names">
                                <th class="hd_gd" style="text-align: center" colspan="11">
                                    <bean:write name="names"/>
                                </th>
                            </logic:present>
                        </tr>
                        <tr>

                            <th class="hd_gd" align="center" >S.No</th>
                            <th class="hd_gd"><bean:write name="ExcelHeader"/></th>
                            <th class="hd_gd" align="center" >Married</th>
                            <th class="hd_gd" align="center" >UnMarried</th>
                            <th class="hd_gd" align="center" >Divorcee</th>
                            <th class="hd_gd" align="center" >Widow</th>
                            <th class="hd_gd" align="center" >Widower</th>

                        </tr>
                        
                        <tr>
                        <th class="hd_gd" align="center" >1</th>
                        <th class="hd_gd" align="center" >2</th>
                        <th class="hd_gd" align="center" >3</th>
                        <th class="hd_gd" align="center" >4</th>
                        <th class="hd_gd" align="center" >5</th>
                        <th class="hd_gd" align="center" >6</th>
                        <th class="hd_gd" align="center" >7</th>
                        </tr>

                        <bean:define id="marriedTotal" value="0"/>
                        <bean:define id="unmarriedTotal" value="0"/>
                        <bean:define id="divorceeTotal" value="0"/>
                        <bean:define id="widowTotal" value="0"/>
                        <bean:define id="widowerTotal" value="0"/>
<%String  classStyle="";%>
                        <logic:iterate name="maritalStatus" id="row" indexId="count">
                            <bean:define id="marriedTotal" value="${marriedTotal + row.married}" />
                            <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.unmarried}" />
                            <bean:define id="divorceeTotal" value="${divorceeTotal + row.divorcee}" />
                            <bean:define id="widowTotal" value="${widowTotal + row.widow}" />
                            <bean:define id="widowerTotal" value="${widowerTotal + row.widower}" />
                           <%
                            if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%>
                            <tr>
                                <td class="<%=classStyle%>" align="center" style="width: 5%;text-align: center;">
                                    <%=i++%>
                                </td>
                                <logic:present name="district">
                                    <td class="<%=classStyle%>">
                                        <a href="maritalStatus.do?mode=getDetails&district_id=${row.district_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="mandal">
                                    <td class="<%=classStyle%>">
                                        <a href="maritalStatus.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}"> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="village">
                                    <td class="<%=classStyle%>">
                                        <a href="maritalStatus.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&urban_id=${row.urban_id}&fromdate=${row.fromdate}&todate=${row.todate}"> ${row.name}</a>
                                    </td>
                                </logic:present>

                                <logic:present name="habitation">
                                    <td class="<%=classStyle%>">
                                        ${row.name}
                                    </td>
                                </logic:present>
                                <td class="<%=classStyle%>" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('maritalStatusIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&urbanId=<%=urban_id%>&mID=${row.mandal_id}&vID=${row.village_id}&habId=${row.habitation_id}&mstatus=1&fromdate=${row.fromdate}&todate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.married}</a>
                                </td>
                                <td class="<%=classStyle%>" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('maritalStatusIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&urbanId=<%=urban_id%>&mID=${row.mandal_id}&vID=${row.village_id}&habId=${row.habitation_id}&mstatus=2&fromdate=${row.fromdate}&todate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.unmarried}</a>
                                </td>
                                <td class="<%=classStyle%>" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('maritalStatusIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&urbanId=<%=urban_id%>&mID=${row.mandal_id}&vID=${row.village_id}&habId=${row.habitation_id}&mstatus=3&fromdate=${row.fromdate}&todate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.divorcee}</a>
                                </td>
                                <td class="<%=classStyle%>" style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('maritalStatusIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&urbanId=<%=urban_id%>&mID=${row.mandal_id}&vID=${row.village_id}&habId=${row.habitation_id}&mstatus=4&fromdate=${row.fromdate}&todate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.widow}</a>
                                </td>
                                <td class="<%=classStyle%>" style="border-right:#234466 1px solid; text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('maritalStatusIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&urbanId=<%=urban_id%>&mID=${row.mandal_id}&vID=${row.village_id}&habId=${row.habitation_id}&mstatus=5&fromdate=${row.fromdate}&todate=${todate}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.widower}</a>
                                </td>
                            </tr>

                        </logic:iterate>

                        <tr>
                            <th class="hd_gd" colspan="2" style="text-align: center"><b>Total</b></th>
                            <th class="hd_gd" style="text-align: center"><b>${marriedTotal}</b></th>
                            <th class="hd_gd" style="text-align: center"><b>${unmarriedTotal}</b></th>
                            <th class="hd_gd" style="text-align: center"><b>${divorceeTotal}</b></th>
                            <th class="hd_gd" style="text-align: center"><b>${widowTotal}</b></th>
                            <th class="hd_gd" style="text-align: center"><b>${widowerTotal}</b></th>

                        </tr>

                    </table>
                    <br/>

                   
                </logic:notEmpty>
            </logic:present>

        </html:form>
    </body>
</html:html>

