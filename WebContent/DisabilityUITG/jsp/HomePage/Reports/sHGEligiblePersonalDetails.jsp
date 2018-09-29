<%--
    Document   : SHGPersonalDetails
    Created on : 5 Mar, 2014, 7:11:01 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<%
            int i = 1;
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            String disName = null;
            String manName = null;
            String villName = null;
            String distId = null;
            String manId = null;
            String villId = null;
            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";

            if (request.getAttribute("districtName") != null) {
                disName = (String) request.getAttribute("districtName");


            }
            if (request.getAttribute("mandalName") != null) {
                manName = (String) request.getAttribute("mandalName");

            }
            if (request.getAttribute("VillagelName") != null) {
                villName = (String) request.getAttribute("VillagelName");

            }

            if (request.getParameter("districtId") != null) {
                distId = (String) request.getParameter("districtId");

            }
            if (request.getParameter("mandalId") != null) {
                manId = (String) request.getParameter("mandalId");

            }

            if (request.getParameter("villageId") != null) {
                villId = (String) request.getParameter("villageId");

            }

            ArrayList shgdetail = (ArrayList) request.getAttribute("personalData");



%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
          <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
    </head>
    <body>
        <html:form action="/shgPersonalDetails">
 <table  align="center" cellspacing="1" border="0" cellpadding="0" width="100%">
                            <tr><th class="hd_gd" align="center" colspan="15">SHG's Individual Details Report</th></tr>
                             <tr>
                                <td colspan="16" align="center">
                                    District :

                                    <font color="blue"><%=request.getAttribute("districtName")%></font>

                                    ,
                                    Mandal :

                                    <font color="blue"><%=request.getAttribute("mandalName")%></font>

                                    ,
                                    Panchayat :
                                    <font color="blue"><%=request.getAttribute("panchayathName")%></font>
                                    ,
                                    Village :

                                    <font color="blue"><%=request.getAttribute("VillagelName")%></font>


                                </td>
                            </tr> </table>  
                             <logic:notEmpty name="personalData">
                              
                              <table >
                            <tr>
                                    <td align="right" colspan="14" style="
                width:1150px; height:10px;">

                                        <%=request.getAttribute("excel")%>
                                    </td>
                                </tr></table>
                            </logic:notEmpty>
                           
           <div style="
                width:1150px; height:450px;overflow:auto;">

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%">
                           
                           


                            <tr>
                                <th class="hd_gd">
                                    S No
                                </th>
                                <th class="hd_gd">
                                    Person Name
                                </th>
                                <th class="hd_gd">
                                    SADAREM ID
                                </th>
                                <th class="hd_gd">
                                    Pension ID
                                </th>
                                <th class="hd_gd">
                                    Ration Card No
                                </th>
                                <th class="hd_gd">
                                    Gender
                                </th>
                                <th class="hd_gd">
                                    Age
                                </th>
                                <th class="hd_gd">
                                    Education
                                </th>
                                <th class="hd_gd">
                                    Caste
                                </th>
                                <th class="hd_gd">
                                    Relation Name
                                </th>
                                <th class="hd_gd">
                                    Type Of Disability
                                </th>
                                 <th class="hd_gd">
                                    Percentage
                                </th>

                                <th class="hd_gd">
                                    Address
                                </th>

                                <th class="hd_gd">
                                    Phone Number
                                </th>
                            </tr>
                            <tr>
                                <th class="hd_gd">
                                    1
                                </th>
                                 <th class="hd_gd">
                                    2
                                </th>
                                 <th class="hd_gd">
                                    3
                                </th>
                                 <th class="hd_gd">
                                    4
                                </th>
                                 <th class="hd_gd">
                                    5
                                </th>
                                 <th class="hd_gd">
                                    6
                                </th>
                                 <th class="hd_gd">
                                    7
                                </th>
                                 <th class="hd_gd">
                                    8
                                </th>
                                 <th class="hd_gd">
                                    9
                                </th>
                                 <th class="hd_gd">
                                    10
                                </th>
                                 <th class="hd_gd">
                                    11
                                </th>
                                <th class="hd_gd">
                                    12
                                </th>
                                <th class="hd_gd">
                                    13
                                </th>
                                <th class="hd_gd">
                                    14
                                </th>
                                </tr>
                                
                            <logic:empty name="personalData">
                                <tr>
                                    <td align="center" colspan="15">
                                        <font color="red" size="3">PwD Details Not Available</font>
                                    </td>
                                </tr>
                            </logic:empty>
                            <%String  classStyle="";%>
                            <logic:notEmpty name="personalData">
                                <logic:iterate id="row" name="personalData" indexId="count">
                                 <%   if(count.intValue()%2==0)
                                        {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }
                                      %>
                                    <tr>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            <%=i++%>
                                        </td>

                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.PERSONNAME}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.SADAREMCODE}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.PENSIONID}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.RATIONCARD_NUMBER}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.Gender}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.age_years}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.EDUCATION}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.CASTE}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="left">
                                            ${row.RELATIONNAME}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.TYPEOFDISABILITY}
                                        </td>
                                         <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.DISABILITY}
                                        </td>

                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid"<%} %> align="center">
                                            ${row.ADDRESS}
                                        </td>
                                        <td class="<%=classStyle%>" <% if(count.intValue() == shgdetail.size()-1) {%>style="border-bottom : #234466 1px solid;border-right:#234466 1px solid;"<%} %> style="border-right:#234466 1px solid;" align="center">
                                            ${row.phone_no}
                                        </td>

                                    </tr>
                                </logic:iterate>
                               	
                            </logic:notEmpty>
                           
                        </table>
           </div>

        </html:form>
    </body>
</html>
