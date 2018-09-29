<%--
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 4100058
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %><%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villagesId");
            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;String f=null,t=null;

             String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");
                      String name=(String) request.getParameter("names");

                    String dis=(String) request.getParameter("disability");
if(fromdate!=null && fromdate.contains("-")){
                         f=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                         }
                     if(todate!=null && todate.contains("-")){
                         t=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
                         }

int rownum=9;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    <style type="text/css">
      </style>
    </head>
    <body>
          <logic:notEmpty name="vifWiseDetails">
        <logic:iterate name="vifWiseDetails" id="row">
            <% rownum=6 ;%>
        </logic:iterate></logic:notEmpty>

         <logic:notEmpty name="mrfWiseDetails">
        <logic:iterate name="mrfWiseDetails" id="row">
            <% rownum=6 ;%>
        </logic:iterate></logic:notEmpty>

        <logic:notEmpty name="hifWiseDetails">
        <logic:iterate name="hifWiseDetails" id="row">
            <% rownum=6 ;%>
        </logic:iterate></logic:notEmpty>

        <logic:notEmpty name="mifWiseDetails">
        <logic:iterate name="mifWiseDetails" id="row">
            <% rownum=6 ;%>
        </logic:iterate></logic:notEmpty>

        <logic:notEmpty name="ohfWiseDetails">
        <logic:iterate name="ohfWiseDetails" id="row">
            <% rownum=6 ;%>
        </logic:iterate></logic:notEmpty>




 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


                <tr>
                    <td   colspan=<%=rownum %> align="center" class="formaltbg" ><b>  Disability Causes Performance Ability<br>

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%> <% if(name!=null) {%>, <%=name%> <%}%></font>   </b></td>
                </tr></table>






<% int j=1; i=1; int k=1;%>

       <logic:notEmpty name="hiWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="9" >Hearing Impairment Causes, Performance ability, Percentage wise Report</td>
                    </tr>
                             <tr>
                                <td class="formhdbg" align="center" width="4%">No</td>
                                <td class="formhdbg" align="center" width="12%">Categories</td>
                                <td class="formhdbg" align="center" width="12%">Sub Categories</td>
                                <td width="12%" class="formhdbg" align="center">Male Count</td>
                                <td width="12%"class="formhdbg" align="center">Male %</td>
                                <td width="12%" class="formhdbg" align="center">Female Count</td>
                                <td width="12%" class="formhdbg" align="center">Female %</td>
                                <td width="12%" class="formhdbg" align="center">Total Count</td>
                                <td width="12%" class="formhdbg" align="center">Total %</td>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
 <logic:iterate name="hiWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="10">Hearing  Impairment Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<% }else if(j==11){
  j++;
%></table>
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" >1</td>
        <td class="formaltbg" align="left" width="12%" rowspan="1">Hearing  Impairment Performance ability wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr></table>

<%}else if(j==12){j++;
      %>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="4">Hearing Impairment Percentage wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<%}%>



</logic:iterate></table></logic:notEmpty>

<%  j=1; i=1;  k=1; %>



       <logic:notEmpty name="mrWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="9" >Mental Retardation Causes, Performance ability, Percentage wise report</td>
                    </tr>
                             <tr>
                                <td class="formhdbg" align="center" width="4%">No</td>
                                <td class="formhdbg" align="center" width="12%">Categories</td>
                                <td class="formhdbg" align="center" width="12%">Sub Categories</td>
                                <td width="12%" class="formhdbg" align="center">Male Count</td>
                                <td width="12%"class="formhdbg" align="center">Male %</td>
                                <td width="12%" class="formhdbg" align="center">Female Count</td>
                                <td width="12%" class="formhdbg" align="center">Female %</td>
                                <td width="12%" class="formhdbg" align="center">Total Count</td>
                                <td width="12%" class="formhdbg" align="center">Total %</td>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
 <logic:iterate name="mrWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="10">Mental Retardation Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<% }else if(j==11){
  j++;
%></table>
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" >1</td>
        <td class="formaltbg" align="left" width="12%" rowspan="1">Mental Retardation Performance ability wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr></table>

<%}else if(j==12){j++;
      %>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="5">Mental Retardation  Percentage wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<%}%>



 </logic:iterate>
</table>

       </logic:notEmpty>


















<%  j=1; i=1;  k=1; %>

       <logic:notEmpty name="miWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="9" >Mental Illness Causes, Performance ability, Percentage wise Report</td>
                    </tr>
                             <tr>
                                <td class="formhdbg" align="center" width="4%">No</td>
                                <td class="formhdbg" align="center" width="12%">Categories</td>
                                <td class="formhdbg" align="center" width="12%">Sub Categories</td>
                                <td width="12%" class="formhdbg" align="center">Male Count</td>
                                <td width="12%"class="formhdbg" align="center">Male %</td>
                                <td width="12%" class="formhdbg" align="center">Female Count</td>
                                <td width="12%" class="formhdbg" align="center">Female %</td>
                                <td width="12%" class="formhdbg" align="center">Total Count</td>
                                <td width="12%" class="formhdbg" align="center">Total %</td>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
 <logic:iterate name="miWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="10">Mental Illness Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
    <%}else if(j<=10){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>



<%}else if(j==11){j++;
      %>
</table>
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="4">Mental Illness Percentage wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<%}%>



 </logic:iterate>
</table>

       </logic:notEmpty>
<% i=1;j=1;k=1; %>
<logic:notEmpty name="viWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="4" >Visual Impairment Causes, Performance ability, Category, Percentage wise report</td>
                    </tr>
                             <tr>
                                <td class="formhdbg" align="center" width="4%">No</td>
                                <td class="formhdbg" align="center" width="12%">Categories</td>
                                <td class="formhdbg" align="center" width="12%">Sub Categories</td>
                                <td width="12%" class="formhdbg" align="center">Male Count</td>
                                <td width="12%"class="formhdbg" align="center">Male %</td>
                                <td width="12%" class="formhdbg" align="center">Female Count</td>
                                <td width="12%" class="formhdbg" align="center">Female %</td>
                                <td width="12%" class="formhdbg" align="center">Total Count</td>
                                <td width="12%" class="formhdbg" align="center">Total %</td>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
 <logic:iterate name="viWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>

 <%if(j==1){%>
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="7">Visual Impairment Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
    <%}else if(j<=7){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<% }else if(j==8){
  j++;k=1;
%></table>
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="2">Visual Impairment Performance ability wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>



<%}else if(j==9){  j++; %>

<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

</table>




<%}
else if(j==10){j++;k=1;
      %>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="6">Visual Impairment Category wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>

<%}%>



 </logic:iterate>
</table>

       </logic:notEmpty>
<logic:notEmpty name="vifWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="10" >Visual Impairment  - Needs Assessment Report</td>
                    </tr>
                             <tr>
                                 <td class="formhdbg" align="center" width="4%" rowspan="2">No</td>
                                 <td class="formhdbg" align="center" width="48%" rowspan="2">Functional  Needs</td>
                                 <td class="formhdbg" align="center" width="24%" colspan="2">Upto 14 Years</td>
                                 <td width="24%" class="formhdbg" align="center" colspan="2">Above 14 years</td>

                            </tr>

                            <tr>
                                 <td class="formhdbg" align="center" width="12%" >Count</td>
                                 <td class="formhdbg" align="center" width="12%" >%</td>
                                 <td width="12%" class="formhdbg" align="center" >Count</td>
                                 <td width="12%" class="formhdbg" align="center" >%</td>

                            </tr>


</table><% i=1; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <logic:iterate name="vifWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<8){%>
       <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==8){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
         <td  align="left" class="formbg" width="52%" colspan="2" >Total Assistive & Augmentative Devices</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


            <%}else if(i>=9 && i<=10){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}else if(i==11){i++; j=11;%>

</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="4">Visual Impairment General Needs</td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Education services</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(j>11 && j<14){%>




<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==14){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2" >Total Education services</td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Vocational Training</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<% }else if(j==15){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==16){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2" >Total Vocational Training </td>
                              <td width="12%"class="formbg1"> ${row.l2}</td>
                              <td width="12%"class="formb">${row.tlp2}</td>
                              <td width="12%"class="formbg1">${row.g2}</td>
                              <td width="12%"class="formb">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Counseling and Guidance</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>


<% }else if(j==17){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==18){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2" >Total Counseling and guidance  </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else{%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}%>




    </logic:iterate></table><br><br>


</logic:notEmpty>

     <logic:notEmpty name="mrfWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="4" >Mental Retardation   - Needs Assessment Report</td>
                    </tr>
                             <tr>
                                 <td class="formhdbg" align="center" width="4%" rowspan="2">No</td>
                                 <td class="formhdbg" align="center" width="48%" rowspan="2">Functional  Needs</td>
                                 <td class="formhdbg" align="center" width="24%" colspan="2">Upto 14 Years</td>
                                 <td width="24%" class="formhdbg" align="center" colspan="2">Above 14 years</td>

                            </tr>

                            <tr>
                                 <td class="formhdbg" align="center" width="12%" >Count</td>
                                 <td class="formhdbg" align="center" width="12%" >%</td>
                                 <td width="12%" class="formhdbg" align="center" >Count</td>
                                 <td width="12%" class="formhdbg" align="center" >%</td>

                            </tr>


</table><% i=1; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <logic:iterate name="mrfWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<7){%>
       <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==7){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Therapeutic Intervention </td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


            <%}else if(i>=8 && i<=9){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}else if(i==10){%>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Assistive & Augmentative Devices</td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}





else if(i==11){ %>

</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(i==12){%>




<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr></table>
  <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Assistive & Augmentative Devices </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

<%}else if(i==13){%>
  </table>
  <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(i==14){i++; j=14;%>

</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Mental Retardation General Needs</td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Education services</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(j>14 && j<17){%>




<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==17){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Education services</td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Vocational Training</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<% }else if(j==18){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==19){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Vocational Training </td>
                              <td width="12%"class="formbg1"> ${row.l2}</td>
                              <td width="12%"class="formb">${row.tlp2}</td>
                              <td width="12%"class="formbg1">${row.g2}</td>
                              <td width="12%"class="formb">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Counseling and Guidance</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>


<% }else if(j==20){


%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==21){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Counseling and guidance  </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else{%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}%>










    </logic:iterate></table><br><br>s


</logic:notEmpty>
<logic:notEmpty name="hifWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="4" >Hearing Impairment  - Needs Assessment Report</td>
                    </tr>
                             <tr>
                                 <td class="formhdbg" align="center" width="4%" rowspan="2">No</td>
                                 <td class="formhdbg" align="center" width="48%" rowspan="2">Functional  Needs</td>
                                 <td class="formhdbg" align="center" width="24%" colspan="2">Upto 14 Years</td>
                                 <td width="24%" class="formhdbg" align="center" colspan="2">Above 14 years</td>

                            </tr>

                            <tr>
                                 <td class="formhdbg" align="center" width="12%" >Count</td>
                                 <td class="formhdbg" align="center" width="12%" >%</td>
                                 <td width="12%" class="formhdbg" align="center" >Count</td>
                                 <td width="12%" class="formhdbg" align="center" >%</td>

                            </tr>


</table><% i=1; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <logic:iterate name="hifWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                                      <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<9){%>
       <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==9){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Assistive & Augmentative Devices</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


            <%}else if(i>=10 && i<=12){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}else if(i==13){i++; j=13;%>

</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Hearing Impairment General Needs</td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Education services</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(j>=14 && j<16){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==16){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Education needs</td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Vocational Training</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<% }else if(j==17){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==18){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Vocational Training </td>
                              <td width="12%"class="formbg1"> ${row.l2}</td>
                              <td width="12%"class="formb">${row.tlp2}</td>
                              <td width="12%"class="formbg1">${row.g2}</td>
                              <td width="12%"class="formb">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Counseling and Guidance</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>


<% }else if(j==19){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==20){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Counseling and guidance  </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else{%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}%>




    </logic:iterate></table><br><br>


</logic:notEmpty>

<logic:notEmpty name="mifWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="4" >Mental Illness  - Needs Assessment Report</td>
                    </tr>
                             <tr>
                                 <td class="formhdbg" align="center" width="4%" rowspan="2">No</td>
                                 <td class="formhdbg" align="center" width="48%" rowspan="2">Functional  Needs</td>
                                 <td class="formhdbg" align="center" width="24%" colspan="2">Upto 14 Years</td>
                                 <td width="24%" class="formhdbg" align="center" colspan="2">Above 14 years</td>

                            </tr>

                            <tr>
                                 <td class="formhdbg" align="center" width="12%" >Count</td>
                                 <td class="formhdbg" align="center" width="12%" >%</td>
                                 <td width="12%" class="formhdbg" align="center" >Count</td>
                                 <td width="12%" class="formhdbg" align="center" >%</td>

                            </tr>


</table><% i=1;j=7; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <logic:iterate name="mifWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                   <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<6){%>
       <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==6){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>





<%}else if(i==7){ i++; j=7;%>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Mental illness General Needs</td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Education services</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(j>=8 && j<10){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==10){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Education needs</td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Vocational Training</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<% }else if(j==11){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==12){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
         <td  align="left" class="formbg" width="52%" colspan="2">Total Vocational Training </td>
                              <td width="12%"class="formbg1"> ${row.l2}</td>
                              <td width="12%"class="formb">${row.tlp2}</td>
                              <td width="12%"class="formbg1">${row.g2}</td>
                              <td width="12%"class="formb">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Counseling and Guidance</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>


<% }else if(j==13){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==14){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
         <td  align="left" class="formbg" width="52%" colspan="2">Total Counseling and guidance  </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else{%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}%>




    </logic:iterate></table><br><br>


</logic:notEmpty>


<logic:notEmpty name="ohfWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="4" >Locomotor Disability  - Needs Assessment Report</td>
                    </tr>
                             <tr>
                                 <td class="formhdbg" align="center" width="4%" rowspan="2">S.No</td>
                                 <td class="formhdbg" align="center" width="48%" rowspan="2">Functional  Needs</td>
                                 <td class="formhdbg" align="center" width="24%" colspan="2">Upto 14 Years</td>
                                 <td width="24%" class="formhdbg" align="center" colspan="2">Above 14 years</td>

                            </tr>

                            <tr>
                                 <td class="formhdbg" align="center" width="12%" >Count</td>
                                 <td class="formhdbg" align="center" width="12%" >%</td>
                                 <td width="12%" class="formhdbg" align="center" >Count</td>
                                 <td width="12%" class="formhdbg" align="center" >%</td>

                            </tr>


</table><% i=1;j=7; %>
<bean:define id="l" value="0"></bean:define>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <logic:iterate name="ohfWiseDetails" id="row">
        <bean:define id="fDate" value="${row.fdate}"/>
                   <bean:define id="tDate" value="${row.tdate}"/>
        <%if(i<4){%>
       <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==4){%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
         </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total (Physiotherapy & Occupational Therapy)</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(i>=5 && i<7){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==7){%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Wheel Chair</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


   <%}else if(i>=8 && i<9){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==9){%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Tricycle</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else if(i>=10 && i<11){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==11){%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Walking Stick</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(i>=12 && i<27){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==27){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Crutches</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">



<%}else if(i>=28 && i<29){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==29){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Walking Frame</td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>
</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Orthosis / Splint for upper Extremity</td></tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">




<%}else if(i>=30 && i<33){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==33){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Upper Extremity Orthosis </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Orthosis / Splint for Lower Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else if(i>=34 && i<40){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==40){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Lower Extremity Orthosis </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Spinal Orhtosis</td></tr>
</table>


                              <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(i>=41 && i<43){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==43){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Lower Extremity Orthosis </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table>
          <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Prosthesis for Upper Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(i>=44 && i<50){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==50){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Upper Extremity Orthosis </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Prosthesis for Lower Extremity</td></tr>
</table><table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


    <%}else if(i>=51 && i<56){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==56){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Lower extremity Prosthesis </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">ADL Equipment</td></tr>
</table><table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">





    <%}else if(i>=57 && i<64){%>

    <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        <%}else if(i==64){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


 </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total ADL Equipment </td>
                              <td width="12%"class="formbg1"> ${row.tl}</td>
                              <td width="12%"class="formb">${row.tlp}</td>
                              <td width="12%"class="formbg1">${row.tg}</td>
                              <td width="12%"class="formb">${row.tgp}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

  <%}else if(i==65){%>

        <tr>  <td align="center"  width="4%"class="formbg" ><%=i++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


    </table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}



else if(i==66){i++; j=66;%>

</table><table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr><td align="left" class="formbg" colspan="6">Locomotor General Needs </td></tr>
</table>
<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>
<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Education services</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>
                              </table>

<table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<%}else if(j>66 && j<69){%>




<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>


<%}else if(j==69){%>

<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Education services</td>
                              <td width="12%"class="formbg1"> ${row.l1}</td>
                              <td width="12%"class="formb">${row.tlp1}</td>
                              <td width="12%"class="formbg1">${row.g1}</td>
                              <td width="12%"class="formb">${row.tgp1}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

<tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Vocational Training</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr></table>

   <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<% }else if(j==70){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==71){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Vocational Training </td>
                              <td width="12%"class="formbg1"> ${row.l2}</td>
                              <td width="12%"class="formb">${row.tlp2}</td>
                              <td width="12%"class="formbg1">${row.g2}</td>
                              <td width="12%"class="formb">${row.tgp2}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

    <tr>  <td align="center"  width="4%"class="formbg" >&nbsp;</td>
     <td  align="left" class="formbg" width="48%">Counseling and Guidance</td>
                              <td width="12%"class="formbg1"> &nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              <td width="12%"class="formbg1">&nbsp;</td>
                              <td width="12%"class="formb">&nbsp;</td>
                              </tr>


<% }else if(j==72){





%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>




<%}else if(j==73){%>


<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>

        </table>

<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

     <tr>
     <td  align="left" class="formbg" width="52%" colspan="2">Total Counseling and guidance  </td>
                              <td width="12%"class="formbg1"> ${row.l3}</td>
                              <td width="12%"class="formb">${row.tlp3}</td>
                              <td width="12%"class="formbg1">${row.g3}</td>
                              <td width="12%"class="formb">${row.tgp3}</td>
                              </tr>

</table> <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">


<%}else{%>
<tr>  <td align="center"  width="4%"class="formbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="48%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.l}</td>
                              <td width="12%"class="formb">${row.lp}</td>
                              <td width="12%"class="formbg1">${row.g}</td>
                              <td width="12%"class="formb">${row.gp}</td>
                              </tr>





<%}%>





    </logic:iterate></table><br><br>


</logic:notEmpty>



 <logic:notEmpty name="ohWiseDetails">
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                    <tr>
                    <td align="center" class="formhdbg" colspan="9" >Locomotor Disability, Sub Types, Causes, Percentage wise Report</td>
                    </tr>
                             <tr>
                                <td class="formhdbg" align="center" width="4%">No</td>
                                <td class="formhdbg" align="center" width="12%">Categories</td>
                                <td class="formhdbg" align="center" width="12%">Sub Categories</td>
                                <td width="12%" class="formhdbg" align="center">Male Count</td>
                                <td width="12%"class="formhdbg" align="center">Male %</td>
                                <td width="12%" class="formhdbg" align="center">Female Count</td>
                                <td width="12%" class="formhdbg" align="center">Female %</td>
                                <td width="12%" class="formhdbg" align="center">Total Count</td>
                                <td width="12%" class="formhdbg" align="center">Total %</td>
                            </tr>
 </table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
 <logic:iterate name="ohWiseDetails" id="row">
          <bean:define id="fDate" value="${row.fdate}"/>
           <bean:define id="tDate" value="${row.tdate}&type=${row.col}"/>


          <%if(j==1){%>
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="16">Locomotor Disability Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
  <%}else if(j<=16){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=j++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>



<%}else if(j==17){j++;
      %>
</table>
     <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="10">Locomotor Disability Causes wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>
    <%}else if(k<=10){ %>

    <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>



<%}else if(k==11){k=1;;
      %>
</table>
<table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">
<tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
        <td class="formaltbg" align="left" width="12%" rowspan="4">Locomotor Disability Percentage wise</td>
        <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1">${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                             <td width="12%" class="formb" >${row.tp}</td></tr>




<%}else {  j++;  %>
 <tr>  <td align="center"  width="4%"class="formaltbg" ><%=k++ %></td>
     <td  align="left" class="formbg" width="12%">${row.col}</td>
                              <td width="12%"class="formbg1"> ${row.male}</td>
                              <td width="12%"class="formb">${row.mp}</td>
                              <td width="12%"class="formbg1">${row.female}</td>
                              <td width="12%"class="formb">${row.fp}</td>
                              <td width="12%"class="formbg1">${row.tot}</td>
                              <td width="12%" class="formb" >${row.tp}</td></tr>

<%}%>



 </logic:iterate>
</table>

       </logic:notEmpty>








   </body>
</html>