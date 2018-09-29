
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
<%@page import="java.text.SimpleDateFormat"%>
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
                    String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");
                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                    String village_id = (String) request.getParameter("vID");
                    String emp = (String) request.getParameter("emp");
                    String name = (String) request.getAttribute("names");//disability
                    String dis = (String) request.getParameter("disability");

                    String f = null, t = null;
                    String tok = null;
                    StringTokenizer st = new StringTokenizer(name, ":");
                    int count = 0;
                    while (st.hasMoreTokens()) {
                        tok = st.nextToken();

                        if (tok != null && !tok.contains("ALL")) {
                            count++;
                        }
                    }

                    if (fromdate != null && fromdate.contains("-")) {
                        f = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                    }
                    if (todate != null && todate.contains("-")) {
                        t = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
                    }



                    String districtName = null;
                    String mandalName = null;
                    String villageName = null;
                    String habName = null;

        %>
                 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <script language="javascript" >
            function changecolor(colorvar)
            {
                var colorvar1=colorvar;
                document.getElementById(colorvar1).style.color="red";
            }
        </script>
    </head>
    
        <body  onload="OnBodyLoad(1,3);">

            <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">Appellate Authority Registered Status Detailed Report

                    As On  <%=f%>  To  <%=t%>, <%=name%>,Disability Type:<%=dis%>  
                    
                        <%
                                    if (villageName != null) {
                                        if (!villageName.equals("null")) {%>
                        District: <font ><%=districtName%></font>, Mandal: <font ><%=mandalName%></font>,
                        Village: <font ><%=villageName%></font>,
                        <% }
                                    }
                                    if (mandalName != null && villageName == null) {
                                        if (!mandalName.equals("null")) {%>
                        District: <font ><%=districtName%></font>, Mandal: <font ><%=mandalName%></font>,
                        <% }
                                    }
                                    if (districtName != null && mandalName == null) {
                                        if (!districtName.equals("null")) {%>
                        District: <font ><%=districtName%></font>,
                        <% }
                                    }%>
                   </th></tr></table>
            <logic:empty name="empWiseDetails">
            <font color="red">NO DATA </font>

            </logic:empty>
            <logic:notEmpty name="empWiseDetails">
            <table align="center" width="80%">
            <tr><td colspan="4" align="right">
                <a href="AppealAuthorityReport.xls?empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis%>&name=<%=name%>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&refID=personalDetailsexcel" target="_blank">
                     <img src="DisabilityUITG/images/excel.jpg"
                                         height="35" width="25" title="Export Excel"/></a> &nbsp; &nbsp; &nbsp;
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
                  
                    <th class="hd_gd" align="center" valign="middle">S.No</th>
                    <th class="hd_gd" align="center" valign="middle">SADAREM ID</th>
                    <th class="hd_gd" align="center" valign="middle">Name</th>
                    <th class="hd_gd" align="center" valign="middle">RelationName</th>
                    <th class="hd_gd" align="center" valign="middle">Age</th>
                    <th class="hd_gd" align="center" valign="middle">Gender</th>

                    <th class="hd_gd" align="center" valign="middle">House No</th>
                    <th class="hd_gd" align="center" valign="middle">HabitationName</th>
                    <%if (count < 4) {%>
                    <th class="hd_gd" align="center" valign="middle">VillageName</th><%}%>

                    <%if (count < 3) {%>

                    <th class="hd_gd" align="center" valign="middle">MandalName</th>
                    <%}%>

                </tr>
                <tr >
                  
                    <th class="hd_gd" align="center" valign="middle">1</th>
                    <th class="hd_gd" align="center" valign="middle">2</th>
                    <th class="hd_gd" align="center" valign="middle">3</th>
                    <th class="hd_gd" align="center" valign="middle">4</th>
                    <th class="hd_gd" align="center" valign="middle">5</th>
                    <th class="hd_gd" align="center" valign="middle">6</th>

                    <th class="hd_gd" align="center" valign="middle">7</th>
                    <th class="hd_gd" align="center" valign="middle">8</th>
                    <%if (count < 4) {%>
                    <th class="hd_gd" align="center" valign="middle">10</th><%}%>

                    <%if (count < 3) {%>

                    <th class="hd_gd" align="center" valign="middle">9</th>
                    <%}%>

                </tr>
                <%String classStyle=""; %> 
                <logic:iterate name="empWiseDetails" id="row" indexId="counter">

                           <%if(counter.intValue()%2 == 0){ 
                              classStyle="firstrow";
                           }
                           else
                           {
                              classStyle="secondrow";
                           }
                           
                           
                           %>
                    <bean:define id="fDate" value="${row.fDate}"/>
                    <bean:define id="tDate" value="${row.tDate}"/>
                    <tr>
                        <td class=<%=classStyle%> align="center">
                            <%=i++%>.
                        </td>
                        <td class=<%=classStyle%>>
                            ${row.person_code}
                        </td>

                        <td class=<%=classStyle%>>
                            ${row.name}
                        </td >
                        <td  align="center" class=<%=classStyle%>>
                            ${row.rname}
                        </td>
                        <td  align="center" class=<%=classStyle%>>
                            ${row.age}
                        </td>
                        <td  align="center" class=<%=classStyle%>>
                            ${row.gender}
                        </td>

                        <td  align="center" class=<%=classStyle%>>
                            ${row.houseno}
                        </td>
                        <td  align="center" class=<%=classStyle%>>
                            ${row.hname}
                        </td>
                        <%if (count < 4) {%><td  align="center" class=<%=classStyle%>>
                            ${row.vname}
                        </td><%}%>
                        <%if (count < 3) {%><td  align="center" class=<%=classStyle%>>
                            ${row.mname}
                        </td> <%}%>

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
          
            
        </logic:notEmpty>
   
</body>
 
</html:html>