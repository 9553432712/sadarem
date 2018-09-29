<%-- 
    Document   : personal_details_age
    Created on : Jan 5, 2012, 11:16:31 AM
    Author     : 490058
--%>


<%--
    Document   : personal_Details
    Created on : Dec 09, 2011, 3:55:47 PM
    Author     : 509862
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
                    String name = (String) request.getAttribute("names");
                    String dis = (String) request.getParameter("disability");

                    String n = (String) request.getAttribute("values");
                    String n1 = (String) request.getAttribute("type");
                    request.setAttribute("type", n1);
                    String type1 = (String) request.getAttribute("type1");
                    String tablee = (String) request.getAttribute("tablee");
                    String colu = (String) request.getAttribute("colu");


                    //  request.setAttribute("values", n);
                    int count = 0;
                    String f = null, t = null;
                    String k = null;
                    if (name != null) {
                        StringTokenizer st = new StringTokenizer(name, ",");

                        while (st.hasMoreTokens()) {
                            k = st.nextToken();

                            if (k.contains("ALL")) {
                                ;
                            } else {
                                count++;
                            }
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
        <script language="javascript" >
            function changecolor(colorvar)
            {
                var colorvar1=colorvar;
                document.getElementById(colorvar1).style.color="red";
            }
        </script>
    </head>
    <center>
        <body>
            <center>
                <div style="background-color: #FFFFFF;width:900px; height:100%; overflow-x:scroll;
                     overflow-y:scroll;">
                    <p style="font-size: 12px">SADAREM Eligible Person Details Profile

                        As On <font size="2px" ><%=f%></font> To <font size="2px"><%=t%>  <br>   <% if (name != null) {%> <%=name%> <%}%></font>

                    <% if (type1 != null) {%>Selected Category: <%=type1%> <%}%>   </p>

                    <%
                                String msg = (String) request.getAttribute("msg");

                                if (msg != null) {%>
                    <font color="red"><%=msg%></font>
                    <%}%>
                    <logic:empty name="ageDetails">
                    
                         <font color="red">No Details Found</font>
                  
                    </logic:empty>
                    

                    <logic:notEmpty name="ageDetails">
                        <table  border="0" align="center" cellspacing="1" cellpadding="4" class="inputform" width="90%">
                            <tr>
                                <th align="left" >S.No</th>
                                <th align="left" >Pension Number</th>
                                <th align="left" >SADAREM ID</th>
                                <!-- <th align="left" >SurName</th>-->
                                <th align="left" >Name</th>
                                <th align="left" >Relation Name</th>
                                <th align="left" >Age</th>
                                <th align="left" >Gender</th>
                                <th align="left" >Disability Type</th>
                                <th align="left" >Percentage</th>
                                <th align="left" >House No</th>
                                <th align="left" >HabitationName</th>
                                <%if (count <= 2) {%>
                                <th align="left" >VillageName</th><%}%>

                                <%if (count < 1) {%>

                                <th align="left" >MandalName</th>
                                <!--  <th align="left" >Type of Disability</th>-->

                                <th align="left" >DistrictName</th>

                                <%}%>

                            </tr>
                            <logic:iterate name="ageDetails" id="row">
                                <bean:define id="fDate" value="${row.fDate}"/>
                                <bean:define id="tDate" value="${row.tDate}"/>
                                <%if (i % 2 == 1) {%>

                                <tr>
                                    <td  align="left">
                                        <%=i++%>.
                                    </td>
                                    <td  align="left">
                                        ${row.pension_no}
                                    </td>
                                    <td  align="left">
                                        ${row.person_code}
                                    </td>
                                    <td  align="left">
                                        ${row.name}
                                    </td>
                                    <td  align="left">
                                        ${row.rname}
                                    </td>
                                    <td  align="left">
                                        ${row.age}
                                    </td>
                                    <td  align="left">
                                        ${row.gender}
                                    </td>
                                    <td  align="left">
                                        ${row.disability}
                                    </td>
                                    <td  align="left">
                                        ${row.percentage}
                                    </td>

                                    <td  align="left">
                                        ${row.houseno}
                                    </td>
                                    <td  align="left">
                                        ${row.hname}
                                    </td>
                                    <%if (count <= 2) {%><td  align="left">
                                        ${row.vname}
                                    </td><%}%>
                                    <%if (count < 1) {%><td  align="left">
                                        ${row.mname}
                                    </td>
                                    <td  align="left">
                                        ${row.dname}
                                    </td>


                                    <%}%>



                                    <!--<td  align="left">
                                        {row.disability}
                                    </td>-->
                                </tr><%} else if (i % 2 == 0) {%>

                                <tr>
                                    <td class="formaltbg" align="left">
                                        <%=i++%>.
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.pension_no}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.person_code}
                                    </td>
                                    <!--  <td >




                                      </td>-->
                                    <td class="formaltbg" align="left">
                                        ${row.name}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.rname}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.age}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.gender}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.disability}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.percentage}
                                    </td>

                                    <td class="formaltbg" align="left">
                                        ${row.houseno}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.hname}
                                    </td>
                                    <%if (count <= 2) {%><td class="formaltbg" align="left">
                                        ${row.vname}
                                    </td><%}%>
                                    <%if (count < 1) {%><td class="formaltbg" align="left">
                                        ${row.mname}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.dname}
                                    </td>

                                    <%}%>



                                    <!--<td  align="left">
                                        {row.disability}
                                    </td>-->
                                </tr>





                                <%}%>

                            </logic:iterate>
                        </table><br>
                        <table align="center">
                <!--  <a href="AppellateAgeReport.xls?&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis%>&name=<%=name%>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>&type=<%=type1%>" target="_blank">-->
                            <a href="AgeWiseReport.xls?&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis%>&name=<%=name%>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&refID=personalDetailsexcel&type1=<%=type1%>&type=<%=n1%>&<%=n%>" target="_blank">

                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                        </table>
                    </logic:notEmpty>



                </div>
            </center>
        </body>
    </center>
</html:html>