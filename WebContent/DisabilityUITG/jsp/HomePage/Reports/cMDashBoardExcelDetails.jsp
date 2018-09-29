<%--
    Document   : cMDashBoardExcelDetails
    Created on : Aug 13, 2014, 12:16:54 PM
    Author     : 310926
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <title>SADAREM</title>
        <link REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css" />
        <script type="text/javascript" src="./DisabilityUITG/js/jquery.min.js"></script> 
         <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
      <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <%

                    String district_name = (String) request.getAttribute("district_name");
                    String mandal_name = (String) request.getAttribute("mandal_name");
                    String village_name = (String) request.getAttribute("village_name");
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");%>
    </head>
    <body onload="OnBodyLoad(1,3);">
        <table border="0" cellspacing="0" cellpadding="0" align="center" width="90%">
            <tr>
                 <td width="97%" style="padding-left: 5px; padding-right: 5px;">
                    <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0">

                        <tr>
                            <td valign="top"  align="center" colspan="2">
                                <!-- Content Starts -->
                                <logic:present name="stateReport" scope="request">
                                    <%
                                                session.setAttribute("stateReport", stateReport);%>
                                                <br/>
                                    <table  cellspacing="0" border="0" cellpadding="0" width="100%" align="center">
                                      <tr> <th colspan="2" class="hd_gd" align="center" valign="middle">R1.1 : SADAREM Assessment Status Detailed Report</th></tr>
                                    
                                        <tr>
                                            <td align="left" >
                                                <b>District :<%=district_name%> &nbsp;&nbsp;&nbsp;Mandal :<%=mandal_name%> &nbsp;&nbsp;&nbsp;Village :<%=village_name%></b>

                                            </td>
                                            <td align="center">
                                                <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></img>
                                                <a href="dashboardReport.xls?mode=getIndividualDashBoardDetailsExcel&heading=${heading}&district_name=<%=district_name%>&mandal_name=<%=mandal_name%>&village_name=<%=village_name%>"  target="_blank">
                                                    Excel </a>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid"  class="gd_row" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="98%">
				   <tbody>
                
  				<tr class="gridHdrStyle">
                                                <th width="8%" class="hd_gd">S.No</th>
                                                <th width="8%" class="hd_gd">Person Name</th>
                                                <th width="8%" class="hd_gd">Relation Name</th>
                                                <th width="8%" class="hd_gd" >SADAREM ID</th>
                                                <th width="8%" class="hd_gd">Pension ID</th>
                                                <th width="8%" class="hd_gd" >RationCard No</th>
                                                <th width="8%" class="hd_gd">Age</th>
                                                <th width="8%" class="hd_gd">Gender</th>
                                                <th width="8%" class="hd_gd">Education</th>
                                                <th width="8%" class="hd_gd" >Caste</th>
                                                <th width="8%" class="hd_gd">Type Of Disability</th>
                                                <th width="8%" class="hd_gd">Percentage</th>
                                                <th width="8%" class="hd_gd">House No.</th>
                                                <th width="8%" class="hd_gd">Habitation</th>
                                                <th width="8%" class="hd_gd">Village</th>
                                                <th width="8%" class="hd_gd">Mandal</th>
                                                <th width="8%" class="hd_gd">District</th>
                                                <th width="8%" class="hd_gd">Phone No.</th>
                                                <th width="8%" class="hd_gd">Assessment Status</th>
                                                <th width="8%" class="hd_gd">Date</th>
                                            </tr>
                                            
                                            <tr class="gridHdrStyle">
                                                <th width="8%" class="hd_gd">1</th>
                                                <th width="8%" class="hd_gd">2</th>
                                                <th width="8%" class="hd_gd">3</th>
                                                <th width="8%" class="hd_gd" >4</th>
                                                <th width="8%" class="hd_gd">5</th>
                                                <th width="8%" class="hd_gd" >6</th>
                                                <th width="8%" class="hd_gd">7</th>
                                                <th width="8%" class="hd_gd">8</th>
                                                <th width="8%" class="hd_gd">9</th>
                                                <th width="8%" class="hd_gd" >10</th>
                                                <th width="8%" class="hd_gd">11</th>
                                                <th width="8%" class="hd_gd">12</th>
                                                <th width="8%" class="hd_gd">13</th>
                                                <th width="8%" class="hd_gd">14</th>
                                                <th width="8%" class="hd_gd">15</th>
                                                <th width="8%" class="hd_gd">16</th>
                                                <th width="8%" class="hd_gd">17</th>
                                                <th width="8%" class="hd_gd">18</th>
                                                <th width="8%" class="hd_gd">19</th>
                                                <th width="8%" class="hd_gd">20</th>
                                            </tr>
                                            
                                            
                                            <% int i = 0; String classStyle="";%>
                                            <logic:iterate id="modify" name="stateReport" scope="request" indexId="count">
                                                   
                                                <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                                                <tr >
                                                    <td width="4%" class="<%=classStyle%>" style="text-align: center"><%=++i%></td>
                                                    <td width="10%" style="text-align: center " class="<%=classStyle%>"> <bean:write name="modify" property="personName"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="relationName"/></td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="SADAREMCODE"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="pensionId"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="rationCardNo"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="age"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="gender"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="education"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="caste"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="typeOfDisability"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="percentage"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="houseNo"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="habitationName"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="villageName"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="mandalName"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="districtName"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="phoneNo"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="assessmentStatus"/>
                                                    </td>
                                                    <td width="8%" style="text-align: center; " class="<%=classStyle%>">
                                                        <bean:write name="modify" property="updatedDate"/>
                                                    </td>
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
                            
                                </logic:present>
                                <!-- Content Endts -->
                            </td>
                        </tr>
                    </table>
                </td>
             
            </tr>
        </table>
           <table width="100%" border="0"  align="center" cellspacing="0" cellpadding="0">
               <tr><td  valign="middle" style="text-align: center">
                 <logic:present name="noDatas">
                <font color="red">
                    ${noDatas}</font>
                </logic:present></td>
            </tr>
        </table>
    </body>
</html>
