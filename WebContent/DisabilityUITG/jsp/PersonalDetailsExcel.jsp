<%-- 
    Document   : PersonalDetailsExcel
    Created on : Dec 13, 2011, 4:48:58 PM
    Author     : 509862
--%>

<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %><%@page import="java.util.*"%>
<%@page import="java.util.Iterator" %><%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String village_id = (String) request.getParameter("vID");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;String f=null,t=null;
            String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");
                     if(fromdate!=null && fromdate.contains("-")){
                         f=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                         }
                     if(todate!=null && todate.contains("-")){
                         t=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
                         }
                    //  String name=(String) request.getParameter("name");
                 //   String dis=(String) request.getParameter("disability");

 int count=0;String k=null;
                     String name=(String) request.getAttribute("names");

                    String dis=(String) request.getParameter("disability");
                    if(name!=null){

                   StringTokenizer st=new StringTokenizer(name,",");

                    while(st.hasMoreTokens()){
                         k= st.nextToken();

                        if(k.contains("ALL")){
                            ;
                        }else{
                            count++;
                        }
                        }}

                   String n=(String)request.getAttribute("values");


String n1=request.getParameter("type");  
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
     <center>
        <body>
            <div style="border:1px solid #000000;
                 background-color: #FFFFFF;width:1000px; height:100%; overflow-x:scroll;
                 overflow-y:scroll;">
               
                        
                <logic:notEmpty name="viDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="viDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>
                    
                </logic:notEmpty>


                     
                <logic:notEmpty name="mrDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="mrDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>
                      
                </logic:notEmpty>


                     
                <logic:notEmpty name="hiDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="hiDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>
                     
                </logic:notEmpty>



                     
                <logic:notEmpty name="miDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="miDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>
                    
                </logic:notEmpty>
<logic:notEmpty name="vifDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="10"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                       
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="vifDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                        </table></td></tr></table><br>
                     
                </logic:notEmpty>
<logic:notEmpty name="hifDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="hifDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>
                      
                </logic:notEmpty>


                    <logic:notEmpty name="mifDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="mifDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>

                </logic:notEmpty>


                                        <logic:notEmpty name="mrfDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="mrfDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>

                </logic:notEmpty>




                                        <logic:notEmpty name="ohfDetails">
                    <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="ohfDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
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
                    </table></td></tr></table><br>

                </logic:notEmpty>
                       <logic:notEmpty name="ohWiseDetails">
                           <table width="99%" border="1" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="11"><b>  SADAREM Eligible Person with Disability Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </font></b>
                                </td></tr>
                           <tr>
                            <td align="left" class="formhdbg">S.No</td>
                              <td align="left" class="formhdbg">Pension Number</td>
                            <td align="left" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="left" class="formhdbg">SurName</td>-->
                            <td align="left" class="formhdbg">Name</td>
                            <td align="left" class="formhdbg">Relation Name</td>
                            <td align="left" class="formhdbg">Age</td>
                            <td align="left" class="formhdbg">Gender</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<3) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<2) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->

                           <td align="left" class="formhdbg">DistrictName</td>

                           <%}%>

                        </tr>
                        <logic:iterate name="ohWiseDetails" id="row">
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
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formbg" align="left">
                                    ${row.mname}
                                </td>
                                <td class="formbg" align="left">
                                    ${row.dname}
                                </td>
                                <%}%>

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
                                    ${row.houseno}
                                </td>
                                 <td class="formaltbg" align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td class="formaltbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td class="formaltbg" align="left">
                                    ${row.mname}
                                </td>
                                <td class="formaltbg" align="left">
                                    ${row.dname}
                                </td>

                                <%}%>

                            </tr>

                            <%}%>

                        </logic:iterate>
                    </table></td></tr></table><br>

                </logic:notEmpty>
            </div>
        </body>
    </center>
</html>