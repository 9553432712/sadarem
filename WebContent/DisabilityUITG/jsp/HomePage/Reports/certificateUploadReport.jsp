<%-- 
    Document   : certificateUploadReport
    Created on : Feb 23, 2015, 12:05:41 PM
    Author     : 740996
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.Iterator,com.tcs.sadarem.util.CommonUtility" %>
<%

            int i = 1;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String selFromDate="",selToDate="";
            selFromDate = CommonUtility.checkNullObj(request.getAttribute("selFromDate"));
            selToDate = CommonUtility.checkNullObj(request.getAttribute("selToDate"));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        
        <title>SADAREM</title>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>

 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
<style>
.rightborder{ border-right: #234466 solid 1px }
</style>
        <script>

            function submitCertificateUploadDetails(){

              
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
                  


             
                if(document.forms[0].fromdate.value==""){
                    alert("Please Enter the FromDate");
                    document.forms[0].fromdate.focus();

                }else if(document.forms[0].todate.value==""){
                    alert("Please Enter the To Date");
                    document.forms[0].todate.focus();

                }else
                    if (today < etd)
                {
                    alert("From date is before Today's Date");
                    document.forms[0].fromdate.value="";
                    return false;
                }
                else if (today < dob)
                {
                    alert("To date does not exceed Today's Date");
                    document.forms[0].todate.value="";
                    return false;
                }
                else if (dob < etd)
                {
                    alert("From date is greater than To date");
                    document.forms[0].fromdate.value="";
                    return false;
                }
                else{
                    
                    document.forms[0].mode.value='submitdetails';
                    document.forms[0].submit();

                   
                }
            }
            $(document).ready(function() 
            		{
            	

            		new JsDatePick({
            	    	 
//            			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
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
            	    	 
//            			minDate: new Date(today.getYear(), today.getMonth() +1, today.getDay()),
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
//             function ShowDate(){
//                 var dt = new Date();
                 
//                 var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
             
//                 document.getElementById(8).value = d;

             

//             }
         
        </script>
    </head>
    <body onload="bodyLoadfunction();">

        <html:form action="/certificateUploadReport">
            <html:hidden property="mode"/>
   
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
					  <tr>
					    <th class="hd_gd" align="center" valign="middle">R 8.1: Certificate Upload Report </th>
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
                    <td>From Date<font color="red"><b>*</b></font></td>
                    <td>
                          <input type="text"  id="fromdate"  name="fromdate" value='<%=selFromDate %>' style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                    </td>
                    <td>To Date<font color="red"><b>*</b></font></td>
                    <td>
                          <input type="text"  id="todate"  name="todate" value='<%=selToDate%>'   style="border:solid 1px #609ebf;" size="10" readonly="readonly"/>
                    </td>
                    <th  align="center" colspan="5">
                        <html:button property="btn" value="Submit" onclick="submitCertificateUploadDetails();"/></th>
              
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
						      <logic:notEmpty name="list">
						   <%--  <table>
						    <tr><td align="right"><a  align="right" href="certificateUploadReport.xls?mode=excelWriting&fDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                       Excel <img src="DisabilityUITG/images/excel.jpg"
                                             height="25" width="25"/></a></tr>
						    </table> --%>
						    <table width="90%">
				                   <tr>
						             <td  align="right" width="89%" >
									   <a href="certificateUploadReport.xls?mode=excelWriting&fDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
										<img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Excel"/>
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
					    	         </logic:notEmpty>
						
  					</td>
  				</tr>
  				</table>
            <br>

            <logic:notEmpty name="list">

                <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="60%">
                    <tr>
                        <th class="hd_gd">S.No</th>
                        <th class="hd_gd">District</th>
                        <th class="hd_gd">Total Assessed</th> 
                        <th class="hd_gd">Completed Certificates</th>
                        <th class="hd_gd">Pending Certificates</th>
                    </tr>
                     <tr>
                        <th class="hd_gd">1</th>
                        <th class="hd_gd">2</th>
                        <th class="hd_gd">3</th> 
                        <th class="hd_gd">4</th>
                        <th class="hd_gd">5</th>
                    </tr>
                    <bean:define id="P1" value="0"/>
                    <% String  classStyle=""; %>
                    <logic:iterate name="list" id="row" indexId='count'>
                       <%   if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%>
                        <tr>

                            <td  class="<%=classStyle%>"  style="text-align:center"> ${row.sno}</td>
                            <td class="<%=classStyle%>"  style="text-align:left"> ${row.district_name}</td>
                            <td class="<%=classStyle%>"  style="text-align:right">${row.assessed}</td>
                            <td class="<%=classStyle%>"  style="text-align:right">${row.validationdone}</td>
                            <td class="<%=classStyle%>"  style="text-align:right; border-right: #234466 solid 1px"> ${row.pending}</td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th class="hd_gd" colspan="2">Total</th>
                        <th class="hd_gd" style="text-align:right">${row.totalassessed}</th>
                        <th class="hd_gd" style="text-align:right">${row.totalvalidationdone}</th>
                        <th class="hd_gd" style="text-align:right">${row.totalpending}</th>
                    </tr>
                </table>


                <logic:present name="nodata">
                    <font color="red">
                        ${nodata}</font>
                    </logic:present>
                <%-- <table align="center">

                    <a href="certificateUploadReport.xls?mode=excelWriting&fDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                             height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                </table> --%>
            </logic:notEmpty>
        </html:form>
    </body>
</html>
