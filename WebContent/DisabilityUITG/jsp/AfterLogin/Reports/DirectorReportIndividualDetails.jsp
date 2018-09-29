<%--
    Document   : DirectorReport
    Created on : 1 Aug, 2014, 6:28:50 PM
    Author     : 842412
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO" %>
<%@page import="org.bf.disability.Constants.CommonConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

        <title>SADAREM</title>
        <%
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");


        %>
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
    <%-- <body onload="ShowDate(),bodyLoadfunction();">--%>
    <body onload="OnBodyLoad(1,3);">
        <html:form styleId="fm1" action="/directorReportOutside.do?mode=getDirectorReportIndividualDetails" method="post" >
            <html:hidden property="mode"/>

            <br/>

            <!-- Content Starts -->
            <logic:present name="stateReport" scope="request">
                <logic:notPresent name="disabilitywise">

                    <%
                                session.setAttribute("stateReport", stateReport);
                    %>
                    
                    
                    
                     <table  align="center"  border="0" cellpadding="0" width="100%">
                     
                     
                      <logic:iterate id="id" name="stateReport" length="1" scope="request"> 
                                <tr>
                                    <logic:present name="Date">
                                        <th colspan="4" class="hd_gd" align="center" valign="middle">
                                            <font size="2">Assessed Abstract Report For the Date:: &nbsp;&nbsp;${id.fromdate}&nbsp;&nbsp;to&nbsp;&nbsp;${id.todate}</font>

                                        </th>
                                    </logic:present>
                                    <logic:present name="Month">
                                         <th colspan="4" class="hd_gd" align="center" valign="middle">
                                            <font size="2">Assessed Abstract Report For the Month::&nbsp;&nbsp;${monthName}&nbsp;&nbsp;Year::&nbsp;&nbsp;${id.year}</font>
                                        </th>
                                    </logic:present >
                                    <logic:present name="FinancialYear">
                                         <th colspan="4" class="hd_gd" align="center" valign="middle">
                                            <font size="2">Assessed Abstract Report For the Financial Year&nbsp;&nbsp;${id.financialYear}</font>
                                        </th>
                                    </logic:present >
                                    
                                    </tr>
                                    <tr>
                                     <th colspan="3"   align="right" valign="middle">
                                       
                                        <a href="directorReportOutside.do?mode=excel&campId=${id.campId}&disabilityId=${id.disabilityId}&reporttypeId=${id.reporttypeId}&habcode=${id.habcodes}&villageId=${id.village_ids}&districtId=${id.district_ids}&mandalId=${id.mandal_ids}&individualList=individualList&heading=${id.heading}" target="_blank">
                                           <img src="./DisabilityUITG/images/excel.jpg" width="25" height="35" border="0" title="Export Excel">  </a>
                                    </th>
                                </tr>
                            </logic:iterate >
            
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
			       <table border="1" align="center" cellspacing="0" rules="all"     id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr class="gridHdrStyle">
                  
                        <th class="hd_gd" align="center" valign="middle">S.No</th>
                                <th class="hd_gd" align="center" valign="middle">Pension ID</th>
                                <th class="hd_gd" align="center" valign="middle">Hab Code</th>
                                <th class="hd_gd" align="center" valign="middle">SADAREM ID</th>
                                <th class="hd_gd" align="center" valign="middle" >Name (>40%)<br/>(4-5)</th>
                                <th class="hd_gd" align="center" valign="middle">Gender</th>
                                <th class="hd_gd" align="center" valign="middle">Age</th>
                                <th class="hd_gd" align="center" valign="middle">Education</th>
                                <th class="hd_gd" align="center" valign="middle">Caste</th>
                                <th class="hd_gd" align="center" valign="middle">Relation Name</th>
                                <th class="hd_gd" align="center" valign="middle">Rationcard Number</th>
                                <th class="hd_gd" align="center" valign="middle">Disability</th>
                                <th class="hd_gd" align="center" valign="middle">Percentage</th>
                                <th class="hd_gd" align="center" valign="middle">Assessment Status</th>
                                <th class="hd_gd" align="center" valign="middle">House No</th>
                                <th class="hd_gd" align="center" valign="middle">Habitation Name</th>
                                <th class="hd_gd" align="center" valign="middle">Village Name</th>
                                <th class="hd_gd" align="center" valign="middle">Mandal Name</th>
                                <th class="hd_gd" align="center" valign="middle">District Name</th>
                                <th class="hd_gd" align="center" valign="middle">Phone No</th>
                                    <th class="hd_gd" align="center" valign="middle">Date</th> 
                            </tr>
							<tr class="gridHdrStyle" height="40">
                  
                        <th class="hd_gd" align="center" valign="middle" >1</th>
                                <th class="hd_gd" align="center" valign="middle">2</th>
                                <th class="hd_gd" align="center" valign="middle">3</th>
                                <th class="hd_gd" align="center" valign="middle">4</th>
                                <th class="hd_gd" align="center" valign="middle">5</th>
                                <th class="hd_gd" align="center" valign="middle">6</th>
                                <th class="hd_gd" align="center" valign="middle">7</th>
                                <th class="hd_gd" align="center" valign="middle">8</th>
                                <th class="hd_gd" align="center" valign="middle">9</th>
                                <th class="hd_gd" align="center" valign="middle">10</th>
                                <th class="hd_gd" align="center" valign="middle">11</th>
                                <th class="hd_gd" align="center" valign="middle">12</th>
                                <th class="hd_gd" align="center" valign="middle">13</th>
                                <th class="hd_gd" align="center" valign="middle">14</th>
                                <th class="hd_gd" align="center" valign="middle">15</th>
                                <th class="hd_gd" align="center" valign="middle">16</th>
                                <th class="hd_gd" align="center" valign="middle">17</th>
                                <th class="hd_gd" align="center" valign="middle">18</th>
                                <th class="hd_gd" align="center" valign="middle">19</th>
                                <th class="hd_gd" align="center" valign="middle">20</th>
                                    <th class="hd_gd" align="center" valign="middle">21</th> 
                            </tr>
							
                            <%--</logic:present>--%>
                            <% int i = 0; String classStyle="";%>
                            <logic:iterate id="id" name="stateReport" scope="request" indexId="count">
                            
                                <%if(count.intValue()%2 == 0) 
                                    {
                                	  classStyle="secondrow";
                                	}
                                  else
                                  {
                                	  classStyle="firstrow";
                                  }%>
                            
                                <tr >
                                    <td class="<%=classStyle%>" style="text-align: center"><%=++i%></td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.pensionid}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.habcode}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.sadaremcode}</td>
                                    <td class="<%=classStyle%>">${id.pensionname}</td>
                                    <td class="<%=classStyle%>">${id.gender}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.age}</td>
                                    <td class="<%=classStyle%>">${id.education}</td>
                                    <td class="<%=classStyle%>">${id.cast}</td>
                                    <td class="<%=classStyle%>">${id.relationname}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.rationcardnumber}</td>
                                    <td class="<%=classStyle%>" >${id.typeofdisability}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.percentage}</td>
                                    <td class="<%=classStyle%>">${id.assessmentstatus}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.houseno}</td>
                                    <td class="<%=classStyle%>">${id.habitationname}</td>
                                    <td class="<%=classStyle%>">${id.villagename}</td>
                                    <td class="<%=classStyle%>">${id.mandalname}</td>
                                    <td class="<%=classStyle%>">${id.districtname}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.phone}</td>
                                    <td class="<%=classStyle%>" style="text-align: center">${id.date}</td>
                                </tr>
                            </logic:iterate>
                            
                           </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
                   
                </logic:notPresent>


                <%--</logic:present>--%>
            </logic:present>
            <logic:present name="msgData">
                 <table  align="center"  border="0" cellpadding="0" width="100%">
                    <tr>
                        <th colspan="4" class="hd_gd" align="center" valign="middle">
                              SADAREM Assessed & Pension Status
                        </th>
                    </tr>
                 </table>
                
                <table>
                    <tr>
                        <th class="hd_gd" align="center" valign="middle">S.No</th>
                        <th class="hd_gd" align="center" valign="middle">Pension ID</th>
                        <th class="hd_gd" align="center" valign="middle">Hab Code</th>
                        <th class="hd_gd" align="center" valign="middle">SADAREM ID</th>
                        <th class="hd_gd" align="center" valign="middle" >Name (>40%)<br/>(4-5)</th>
                        <th class="hd_gd" align="center" valign="middle">Gender</th>
                        <th class="hd_gd" align="center" valign="middle">Age</th>
                        <th class="hd_gd" align="center" valign="middle">Education</th>
                        <th class="hd_gd" align="center" valign="middle">Cast</th>
                        <th class="hd_gd" align="center" valign="middle">Relation Name</th>
                        <th class="hd_gd" align="center" valign="middle">Rationcard Number</th>
                        <th class="hd_gd" align="center" valign="middle">Disability</th>
                        <th class="hd_gd" align="center" valign="middle">Percentage</th>
                        <th class="hd_gd" align="center" valign="middle">Assessment Status</th>
                        <th class="hd_gd" align="center" valign="middle">House No</th>
                        <th class="hd_gd" align="center" valign="middle">Habitation Name</th>
                        <th class="hd_gd" align="center" valign="middle">Village Name</th>
                        <th class="hd_gd" align="center" valign="middle">Mandal Name</th>
                        <th class="hd_gd" align="center" valign="middle">District Name</th>
                        <th class="hd_gd" align="center" valign="middle">Phone No</th>
                        <th class="hd_gd" align="center" valign="middle">Date</th>
                        </tr>
                        <tr>

                            <td colspan="21" style="text-align: center;">
                            <center>${msgData}</center>
                        </td>
                    </tr>
                </table>

            </logic:present>



        </html:form>
    </body>


    <script src="./DisabilityUITG/js/jquery-min.js"></script>
    <logic:present name="camp">
        <script>
            document.getElementById("cmp").style.display="";
            document.getElementById("cmpval").style.display="";
            document.getElementById("data").style.display="";
        </script>
    </logic:present>
    <logic:present name="cmpStat">
        <script>
            document.getElementById("campwise").style.display="";
            document.getElementById("submitButton").style.display="";
        </script>
    </logic:present>
    <script>
        // The below two methods are for getting the table data



        function getCamps() {
            document.forms[0].elements["mode"].value="unspecified";
            document.forms[0].submit();
        }
    </script>

</html>
