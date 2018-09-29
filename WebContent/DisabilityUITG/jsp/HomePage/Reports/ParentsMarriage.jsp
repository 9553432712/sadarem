<%-- 
    Document   : ParentsMarriage
    Created on : Jul 12, 2011, 10:42:53 AM
    Author     : 484898
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
            System.out.println(fromdate+""+todate);
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");


%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
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

        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

    <body onload="ShowDate()">


        <script language="javascript" src="./DisabilityUITG/js/Ajax.js"></script>
        <html:form action="/parentsMarriage.do" >

            <input type="hidden" name="mode"/>
            
                <table  align="center"  border="0" cellpadding="0" width="100%">
                  <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">R3.6 : Consanguineous Marriage of PWD's Parents - Details</th></tr>
                            
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

                                <td  colspan="2">
                                From Date<font color="red"><b>*</b></font>
                                   <input type="text"  id="fromdate"  name="fromdate" value='<%=fromdate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                    
                                     
                                </td>
                                <td  colspan="2">To Date<font color="red"><b>*</b></font>
                                   <input type="text"  id="todate"  name="todate" value='<%=todate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                                    
                                    
                                </td>


                                <td colspan="6">
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
                </td></tr>
              </table><br>
            <logic:present name="msg">
                <center><font color="red" size="3"><b><bean:write name="msg"/></b></font></</center>
            </logic:present>
            <br>
            <%if (request.getAttribute("parentsMarriage") != null) {%>
            <logic:present name="parentsMarriage">
                <logic:notEmpty name="parentsMarriage">

                    <table  align="center" cellspacing="1" border="0" cellpadding="4"   width="80%">
                        <tr>
                            <logic:present name="names">
                                <th style="text-align: left" colspan="11">
                                   <font size="3" color="#234466"> <bean:write name="names"/></font>
                                </th>
                            </logic:present>
                             <logic:iterate name="parentsMarriage" id="modify" length="1">
                                <logic:present name="mandal">
                                    <th >
                                        <a href="parentsMarriage.do?mode=getDetails&dateType=disback&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" style="color: black"> 
<img src="DisabilityUITG/images/but_back.png" height="25" width="25"/></a>
                                    </th>
                                </logic:present>
                                <logic:present name="village">
                                    <th>
                                        <a href="parentsMarriage.do?mode=getDetails&district_id=${modify.district_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" style="color: black"> <img src="DisabilityUITG/images/but_back.png" height="25" width="25"/></a>
                                    </th>
                                </logic:present>

                                <logic:present name="habitation">
                                    <th>
                                        <a href="parentsMarriage.do?mode=getDetails&district_id=${modify.district_id}&mandal_id=${modify.mandal_id}&urban_id=${modify.urban_id}&fromdate=${modify.fromdate}&todate=${modify.todate}" style="color: black"> <img src="DisabilityUITG/images/but_back.png" height="25" width="25"/></a>
                                    </th>
                                </logic:present>
                            </logic:iterate>
                             <logic:present name="district">
                            <logic:iterate name="parentsMarriage" id="row" length="1">
                                <td>
                                    <a href="parentsMarriage.xls?mode=getDetails&returnType=Excel&dateType=dateType&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/excel.jpg" height="35" width="25"/></a> &nbsp; &nbsp; &nbsp;
                               
                                    <a href="parentsMarriage.do?mode=getDetails&returnType=Print&dateType=dateType&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="mandal">
                            <logic:iterate name="parentsMarriage" id="row" length="1">
                                <td>
                                    <a href="parentsMarriage.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/excel.jpg" height="35" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                
                                    <a href="parentsMarriage.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="village">
                            <logic:iterate name="parentsMarriage" id="row" length="1">
                                <td>
                                    <a href="parentsMarriage.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/excel.jpg" height="35" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                
                                    <a href="parentsMarriage.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </logic:iterate>
                        </logic:present>
                        <logic:present name="habitation">
                            <logic:iterate name="parentsMarriage" id="row" length="1">
                                <td>
                                    <a href="parentsMarriage.xls?mode=getDetails&returnType=Excel&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/excel.jpg" height="35" width="25"/></a> &nbsp; &nbsp; &nbsp;
                                
                                    <a href="parentsMarriage.do?mode=getDetails&returnType=Print&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fromdate}&todate=${row.todate}" target="_blank">
                                         <img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </logic:iterate>
                        </logic:present>
                        </tr>
                        </table>
                        
                        <table border="1" align="center" cellspacing="0" rules="all"   id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="80%">
				         <tbody>
                           <tr >
                  
                            <th class="hd_gd" align="center" valign="middle">S.No</th>
                             <th class="hd_gd" align="center" valign="middle"><bean:write name="ExcelHeader"/></th>
                             <th class="hd_gd" align="center" valign="middle">No</th>
                             <th class="hd_gd" align="center" valign="middle">Yes</th>
                             <th class="hd_gd" align="center" valign="middle">Unknown</th>


                        </tr>
						
						 <tr >
                  
                            <th class="hd_gd" align="center" valign="middle">1</th>
                             <th class="hd_gd" align="center" valign="middle">2</th>
                             <th class="hd_gd" align="center" valign="middle">3</th>
                             <th class="hd_gd" align="center" valign="middle">4</th>
                             <th class="hd_gd" align="center" valign="middle">5</th>


                        </tr>
                        <bean:define id="marriedTotal" value="0"/>
                        <bean:define id="unmarriedTotal" value="0"/>
                        <bean:define id="unKnown" value="0"/>
						
						<%String classStyle=""; %>
                        <logic:iterate name="parentsMarriage" id="row" indexId="count">
                    
                                             <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                            <bean:define id="marriedTotal" value="${marriedTotal + row.no}" />
                            <bean:define id="unmarriedTotal" value="${unmarriedTotal + row.yes}" />
                            <bean:define id="unKnown" value="${unKnown + row.none}" />


                            <tr>
                                <td  align="center" class="<%=classStyle%>" style="width: 2%;">
                                    <%=i++%>
                                </td>
                                <logic:present name="district">
                                    <td class="<%=classStyle%>">
                                        <%-- <a href="parentsMarriage.do?mode=getDetails&district_id=${row.district_id}&fromdate=${row.fromdate}&todate=${row.todate}"> --%> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="mandal">
                                    <td class="<%=classStyle%>">
                                        <%-- <a href="parentsMarriage.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&fromdate=${row.fromdate}&todate=${row.todate}"> --%> ${row.name}</a>
                                    </td>
                                </logic:present>
                                <logic:present name="village">
                                    <td class="<%=classStyle%>">
                                      <%--   <a href="parentsMarriage.do?mode=getDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&fromdate=${row.fromdate}&todate=${row.todate}"> --%> ${row.name}</a>
                                    </td>
                                </logic:present>

                                <logic:present name="habitation">
                                    <td class="<%=classStyle%>">
                                        ${row.name}
                                    </td>
                                </logic:present>

                                <td  class="<%=classStyle%>" style="text-align:center" style="width: 8%;">
                                    <%-- <a href="javascript:void(0);" onclick ="window.open('parentsMarriageIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.habitation_id}&mstatus=1&fDate=${row.fromdate}&toDate=${row.todate}&pId=0', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.no}</a>
                                </td>
                                <td  class="<%=classStyle%>" style="text-align:center" style="width: 8%;">
                                    <%-- <a href="javascript:void(0);" onclick ="window.open('parentsMarriageIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.habitation_id}&mstatus=2&fDate=${row.fromdate}&toDate=${row.todate}&pId=1', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.yes}</a>
                                </td>
                                <td  class="<%=classStyle%>" style="text-align:center" style="width: 8%;">
                                    <%-- <a href="javascript:void(0);" onclick ="window.open('parentsMarriageIndivdualDetails.do?status=getPersonalDetails&dID=${row.district_id}&mID=${row.mandal_id}&vID=${row.village_id}&habCode=${row.habitation_id}&mstatus=3&fDate=${row.fromdate}&toDate=${row.todate}&pId=is null', 'Ratting','resizable=yes, scrollbars=yes,')"> --%>${row.none}</a>
                                </td>

                            </tr>

                        </logic:iterate>

                        <tr>
                            <th  class="hd_gd" colspan="2" style="text-align:center"><b>Total</b></th>
                            <th  class="hd_gd" style="text-align:center"><b>${marriedTotal}</b></th>
                            <th  class="hd_gd" style="text-align:center"><b>${unmarriedTotal}</b></th>
                            <th  class="hd_gd" style="text-align:center"><b>${unKnown}</b></th>


                        </tr>

                    </table>
                    <br/>

                  
                </logic:notEmpty>
            </logic:present>
            <%}%>
        </html:form>
    </body>
</html:html>

