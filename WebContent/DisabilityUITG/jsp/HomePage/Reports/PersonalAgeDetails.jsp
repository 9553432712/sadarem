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
                    String gender = (String) request.getParameter("gender");
                    String fromAge = (String) request.getParameter("fromAge");
                    String toAge = (String) request.getParameter("toAge");
                    String urbanId = (String) request.getParameter("urbanId");
                    String habitation_id = (String) request.getParameter("habID");


                    String districtName = "ALL";
                    String mandalName = "ALL";
                    String villageName = "ALL";
                    String habName = "ALL";
                    ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) request.getAttribute("areaDetails");
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

        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>


    </head>

    <center>
        <body onload="OnBodyLoad(2,3);">

            <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">

                <%if (gender.equals("1")) {%>
                <tr >
                <td align="center" colspan="16" class="hd_gd"><b>Age Wise Report For <font color="blue">Male's</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <%} else if (gender.equals("2")) {%>
                <tr>
                <td align="center" colspan="16" class="hd_gd"><b>Age Wise Report For <font color="blue">Female's</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <%} else {%>
                <tr>
                <td align="center" colspan="16" class="hd_gd"><b>Age Wise Report For <font color="blue">Male & Female</font> As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                </tr>
                <%}%>
                
            </table>
              
             <table  width="90%">
                 <tr>
                  <td align="center">
                    <table>
                    <tr>
                     <td>
                        District :
                    <% if (district_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=districtName%></font>
                    <%}%>
                    ,
                    Mandal :
                    <% if (mandal_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=mandalName%></font>
                    <%}%>
                    ,
                    Village :
                    <% if (village_id.equals("")) {%>
                    <font color="blue">ALL</font>
                    <% } else {%>
                    <font color="blue"><%=villageName%></font>
                    <%}%>

                    <% if (!habitation_id.equals("")) {
                                    habName = request.getParameter("habName");
                    %>
                    , Habitation :
                    <font color="blue"><%=request.getParameter("habName")%></font>
                    <%}%>
                     </td>
                    </tr>
                    </table>
                   </td>
                    <td align="right">
                    <%session.setAttribute("ageWiseDetails", (ArrayList) request.getAttribute("ageWiseDetails"));%>
                    <a href="ageWiseReports.xls?status=getPersonalAgeDetails&habName=<%=habName%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&gender=<%=gender%>&fromAge=<%=fromAge%>&toAge=<%=toAge%>&fDate=<%=fromdate%>&toDate=<%=todate%>&urbanId=<%=urbanId%>&refID=excel" target="_blank">
                     <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
                     </a>
                   </td>
                  </tr>
                
           </table>


            <logic:notEmpty name="ageWiseDetails">
                <table  align="center" cellspacing="1" border="0" cellpadding="0"  width="90%">
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
                    <tr class="gridHdrStyle">
                        <th align="center" class="hd_gd">S.No</th>
                        <th align="center" class="hd_gd">SADAREM ID</th>
                        <th align="center" class="hd_gd">Name</th>
                        <th align="center" class="hd_gd">Gender</th>
                        <th align="center" class="hd_gd">Age</th>
                        <th align="center" class="hd_gd">Relation Name</th>
                        <th align="center" class="hd_gd">Education</th>
                        <th align="center" class="hd_gd">Caste</th>
                        <th align="center" class="hd_gd">Address</th>
                        <th align="center" class="hd_gd">Phone No</th>

                    </tr>
                      <tr class="gridHdrStyle" height="30">
                        <th align="center" class="hd_gd">1</th>
                        <th align="center" class="hd_gd">2</th>
                        <th align="center" class="hd_gd">3</th>
                        <th align="center" class="hd_gd">4</th>
                        <th align="center" class="hd_gd">5</th>
                        <th align="center" class="hd_gd">6</th>
                        <th align="center" class="hd_gd">7</th>
                        <th align="center" class="hd_gd">8</th>
                        <th align="center" class="hd_gd">9</th>
                        <th align="center" class="hd_gd">10</th>

                    </tr>
                    <%String classStyle=""; %>
                    <logic:iterate name="ageWiseDetails" id="row" indexId="count">
                     <% if(count.intValue()%2==0)
              		 {
                    	  classStyle="secondrow";
                	 }
                      else
                      {
                    	  classStyle="firstrow";
                    	  
                      } %> 
                        <tr>
                            <td  align="center" style="width: 5%;" class="<%=classStyle%>">
                                <%=i++%>.
                            </td>
                            <td  style="width: 10%;" class="<%=classStyle%>">
                                ${row.PERSON_CODE}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.PERSONNAME}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.Gender}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.age_years}
                            </td>
                            <td  align="center" style="width: 8%;" class="<%=classStyle%>">
                                ${row.relation_name}
                            </td>

                            <td  style="width: 10%;" class="<%=classStyle%>">
                                ${row.EDUCATION}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.CASTE}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.Address}
                            </td>
                            <td  style="width: 15%;" class="<%=classStyle%>">
                                ${row.phone_no}
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
                <br>
                
            </logic:notEmpty>
            <br>

            <logic:empty name="ageWiseDetails">
                <p id="errmsg">Details are Not Available!</p>
            </logic:empty>

        </body>
    </center>
</html:html>
