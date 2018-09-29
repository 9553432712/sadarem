<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>
<%--
    Document   : PersonalCasteDetails
    Created on : Jun 22, 2011, 3:55:47 PM
    Author     : 484898
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<html:html>

    <head>
        <%
                    int i = 1;
                    String dI = (String) request.getParameter("dID");
                    String mI = (String) request.getParameter("mID");
                    String vI = (String) request.getParameter("vID");
                    String fD = (String) request.getParameter("fDate");
                    String tD = (String) request.getParameter("toDate");
                    String fromdate = (String) request.getParameter("fDate");
                    String todate = (String) request.getParameter("toDate");
                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                    String village_id = (String) request.getParameter("vID");
                    String habid = (String) request.getParameter("habId");

                    String emp = (String) request.getParameter("emp");


                    String urbanId = (String) request.getParameter("urbanId");

                    String districtName = "ALL";
                    String mandalName = "ALL";
                    String villageName = "ALL";
                    String habName = "ALL";

                    ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) request.getAttribute("areaDetails");
                    //  for(int j = 0;j<=getAddressList.size();j++) {
                    if (getAddressList.size() > 0) {
                        if (getAddressList.size() == 1) {
                            districtName = (String) getAddressList.get(0);
                        } else if (getAddressList.size() == 2) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                        } else if (getAddressList.size() == 3) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                        } else if (getAddressList.size() == 4) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                            habName = (String) getAddressList.get(3);
                        }


                    }

        %>
        <script language="javascript" >
            function changecolor(colorvar)
            {
                var colorvar1=colorvar;
                document.getElementById(colorvar1).style.color="red";
            }
        </script>
        <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }</style>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
    </head>
    <center>
        <body body onload="OnBodyLoad(1,3);">

            <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">

              
                <tr>
                    <%if (emp.equals("0")) {%>
                <td class="hd_gd" align="center" colspan="16"><b>Employment Status Report (NA) As On <%=fromdate%> To <%=todate%></b></td>
                <%} else if (emp.equals("1")) {%>
                <td class="hd_gd"  align="center" colspan="16"><b>Employment Status Report (Government) As On <%=fromdate%> To <%=todate%></b></td>
                <%} else if (emp.equals("2")) {%>
                <td class="hd_gd"  align="center" colspan="16"><b>Employment Status Report (Private) As On <%=fromdate%> To <%=todate%></b></td>
                <%} else if (emp.equals("3")) {%>
                <td class="hd_gd"  align="center" colspan="16"><b>Employment Status (Self-employed) As On <%=fromdate%> To <%=todate%></b></td>
                <%} else if (emp.equals("4")) {%>
                <td class="hd_gd"  align="center" colspan="16"><b>Employment Status (Un-employed) As On <%=fromdate%> To <%=todate%></b></td>
                <%} else if (emp.equals("5")) {%>
                <td class="hd_gd"  align="center" colspan="16"><b>Employment Status (Wage-employed) As On <%=fromdate%> To <%=todate%></b></td>
                
                <%}%>
                </tr>

                <tr>

                <td align="center" colspan="11">
                    <%

                                   if (habName != null) {
                                       if (!habName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                    Village: <font color="blue"><%=villageName%></font>,
                    Habitation: <font color="blue"><%=habName%></font>
                    <% }
                                }

                                if (villageName != null && habName == null) {
                                    if (!villageName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                    Village: <font color="blue"><%=villageName%></font>,
                    <% }
                                }
                                if (mandalName != null && villageName == null) {
                                    if (!mandalName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                    <% }
                                }
                                if (districtName != null && mandalName == null) {
                                    if (!districtName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>,
                    <% }
                            }%>
                </td>
                  <% session.setAttribute("empWiseDetails", (ArrayList) request.getAttribute("empWiseDetails"));%>
                   <td colspan="16" style="text-align: right"> <a href="empReport.xls?empStatus=getempDetailsAction&habId=<%=habid%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&urbanId=<%=urbanId%>&emp=<%=emp%>&fDate=<%=fromdate%>&toDate=<%=todate%>&refID=personalDetailsexcel" target="_blank">
                        <img align="right" src="DisabilityUITG/images/excel.jpg" height="35" width="35"/></a> </td>
                </tr>
 </table>
     <table align="center" cellpadding="1" cellspacing="1" border="0"  width="90%">
      <tbody>
                 <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
				   
                <logic:notEmpty name="empWiseDetails">

                  
                    <tr>
                        <th class="hd_gd" align="center" >S.No</th>
                        <th class="hd_gd" align="center" >SADAREM ID</th>
                        <th class="hd_gd" align="center" >Name</th>
                        <th class="hd_gd" align="center" >Gender</th>
                        <th class="hd_gd" align="center" >Age</th>
                        <th class="hd_gd" align="center" >Relation Name</th>
                        <th class="hd_gd" align="center" >Education</th>
                        <th class="hd_gd" align="center" >Caste</th>
                        <th class="hd_gd" align="center" >Address</th>
                        <th class="hd_gd" align="center" >Phone No</th>

                    </tr>
                     <tr>
                        <th class="hd_gd" align="center" >1</th>
                        <th class="hd_gd" align="center" >2</th>
                        <th class="hd_gd" align="center" >3</th>
                        <th class="hd_gd" align="center" >4</th>
                        <th class="hd_gd" align="center" >5</th>
                        <th class="hd_gd" align="center" >6</th>
                        <th class="hd_gd" align="center" >7</th>
                        <th class="hd_gd" align="center" >8</th>
                        <th class="hd_gd" align="center" >9</th>
                        <th class="hd_gd" align="center" >10</th>
                        </tr>
                        <%String  classStyle="";%>
                    <logic:iterate name="empWiseDetails" id="row" indexId="count">
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
                            <td class="<%=classStyle%>" align="center" style="width: 5%;">
                                <%=i++%>.
                            </td>
                            <td class="<%=classStyle%>" style="width: 10%;">
                                ${row.PERSON_CODE}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.PERSONNAME}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.Gender}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.age_years}
                            </td>
                            <td class="<%=classStyle%>" align="center" style="width: 8%;">
                                ${row.relation_name}
                            </td>

                            <td class="<%=classStyle%>" style="width: 10%;">
                                ${row.EDUCATION}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.CASTE}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.Address}
                            </td>
                            <td class="<%=classStyle%>" style="width: 15%;">
                                ${row.phone_no}
                            </td>

                        </tr>

                    </logic:iterate>
                     </logic:notEmpty>
                    
                    
                       </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
    </td>
   </tr>
 </tbody>
                </table>
                <br>
                
           

            <table align="center">
                <logic:empty  name="empWiseDetails">
                    <br>
                    <p id="errmsg">Details are Not Available!</p>


                </logic:empty>
            </table>
        </body>
    </center>
</html:html>