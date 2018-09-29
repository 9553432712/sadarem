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
                    String name=(String) request.getAttribute("names");
                    String dis=(String) request.getParameter("disability");

                    String n=(String)request.getAttribute("values");
                    String n1=(String)request.getAttribute("type");
                    request.setAttribute("type", n1);
                    String type1=(String)request.getAttribute("type1");
                    String tablee=(String)request.getAttribute("tablee");
                    String colu=(String)request.getAttribute("colu");


                   //  request.setAttribute("values", n);
                  int count=0;
String f=null,t=null;String k=null;
                 if(name!=null){
                   StringTokenizer st=new StringTokenizer(name,",");

                    while(st.hasMoreTokens()){
                       k= st.nextToken();

                        if(k.contains("ALL")){
                            ;
                        }else{
                            count++;
                        }
                        }
                 }
                     if(fromdate!=null && fromdate.contains("-")){
                         f=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                         }
                     if(todate!=null && todate.contains("-")){
                         t=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
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

            <div style="border:1px solid #000000;
                 background-color: #FFFFFF;width:1000px; height:100%; overflow-x:scroll;
                 overflow-y:scroll;">
                <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="103%">
                    <tr>
                        <td align="center" colspan="10"><img src="DisabilityUITG/images/Banner.jpg"/></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="10"><b>  SADAREM Eligible Person Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%>  <br>   <% if(name!=null) {%> <%=name%> <%}%></font>   </b>

  <b><font color="blue">  <% if(type1!=null) {%>Selected Category: <%=type1%> <%}%>   </font></b>

                        </td>
                    </tr>

                </table>

  <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center"><tr align="center"><td>
                    <%
 String msg=(String)request.getAttribute("msg");

if(msg!=null){%>
                    <font color="red"><%=msg %></font>
<%}%>

                </td></tr></table>

             

  <logic:notEmpty name="ageDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="4" class="tbl_bg2" width="100%">
                       <!-- <tr>
                            <td class="formhdbg" align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>
                            <td align="left" class="formhdbg">Disability Type</td>
                            <td align="left" class="formhdbg">Percentage</td>
                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<=2) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<1) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="ageDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td class="formbg" align="left">
                                    <%=i++%>.
                                </td>
                                <td class="formbg" align="left">
                                    ${row.pension_no}
                                </td>
                              <td class="formbg" align="left">
                                    ${row.person_code}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.name}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.rname}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.age}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.gender}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.disability}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.percentage}
                                </td>

                                 <td class="formbg" align="left">
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<=2) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<1) {%><td class="formbg" align="left">
                                    ${row.mname}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td class="formbg" align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td class="formbg">




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
                                <%if (count<=2) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<1) {%><td class="formaltbg" align="left">
                                    ${row.mname}
                                </td>
                                <td class="formaltbg" align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td class="formbg" align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
              <!--  <a href="AppellateAgeReport.xls?&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>&type=<%=type1%>" target="_blank">-->
                     <a href="AgeWiseReport.xls?&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&refID=personalDetailsexcel&type1=<%=type1%>&type=<%=n1%>&<%=n%>" target="_blank">
                    
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>



            </div>
        </body>
    </center>
</html:html>