
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

                    String tablee=(String)request.getAttribute("tablee");
                    String colu=(String)request.getAttribute("colu");
                    String query=(String)request.getAttribute("query");

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
            
                    <p> SADAREM Eligible Person with Disability Personal Details Profile

 As On <%=f%> To <%=t%>  <br>   <% if(name!=null) {%> <%=name%> <%}%>   

  <b> <% if(n1!=null) {%>Selected Category: <%=n1%> <%}%>   </b>

                    </p>
               
 <div style="width:900px; height:100%; overflow-x:scroll;
                 overflow-y:scroll;">
  <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center"><tr align="center"><td>
                    <%
 String msg=(String)request.getAttribute("msg");

if(msg!=null){%>
                    <font color="red"><%=msg %></font>
<%}%>

                </td></tr></table>
                       
                <logic:notEmpty name="viDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="viDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="VIReport.xls?VIReport=VIReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&type=<%=n1%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>


                     
                <logic:notEmpty name="mrDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="mrDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="MRReport.xls?MRReport=MRReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&type=<%=n1%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>


                    
                <logic:notEmpty name="hiDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="hiDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                       <table align="center">
                <a href="HIReport.xls?HIReport=HIReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&type=<%=n1%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>



                     
                <logic:notEmpty name="miDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="miDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="MIReport.xls?MIReport=MIReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&type=<%=n1%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>
<logic:notEmpty name="vifDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="vifDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="VIFunctiionalneedReport.xls?VIFunctiionalneedReport=VIFunctiionalneedReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>
<logic:notEmpty name="hifDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="hifDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="HIFunctiionalneedReport.xls?HIFunctiionalneedReport=HIFunctiionalneedReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>


                    <logic:notEmpty name="mifDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="mifDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="MIFunctiionalneedReport.xls?MIFunctiionalneedReport=MIFunctiionalneedReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>


                            <logic:notEmpty name="mrfDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="mrfDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="MRFunctiionalneedReport.xls?MRFunctiionalneedReport=MRFunctiionalneedReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>


                     <logic:notEmpty name="ohfDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="ohfDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>


                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>



                                <!--<td align="left">
                                    {row.disability}
                                </td>-->
                            </tr>





                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="LocomotorFunctiionalneedReport.xls?LocomotorReport=LocomotorFunctiionalneedReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>

    <logic:notEmpty name="ohWiseDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                       <!-- <tr>
                            <td  align="center" colspan="13"> Disability Wise Details.</td>
                        </tr>-->
                        <tr>
                           <tr>
                            <th align="left" >S.No</th>
                              <th align="left" >Pension Number</th>
                            <th align="left" >SADAREM ID</th>
                           <!-- <th align="left" >SurName</th>-->
                            <th align="left" >Name</th>
                            <th align="left" >Relation Name</th>
                            <th align="left" >Age</th>
                            <th align="left" >Gender</th>

                            <th align="left" >House No</th>
                            <th align="left" >HabitationName</th>
                            <%if (count<3) {%>
                            <th align="left" >VillageName</th><%}%>

                            <%if (count<2) {%>

                            <th align="left" >MandalName</th>
                           <!--  <th align="left" >Type of Disability</th>-->

                           <th align="left" >DistrictName</th>

                           <%}%>

                        </tr>
                        <logic:iterate name="ohWiseDetails" id="row">
                            <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>
                                      <%if(i%2==1){%>

                            <tr>
                                <td align="left">
                                    <%=i++%>.
                                </td>
                                <td align="left">
                                    ${row.pension_no}
                                </td>
                              <td align="left">
                                    ${row.person_code}
                                </td>
                                <td align="left">
                                    ${row.name}
                                </td>
                                <td align="left">
                                    ${row.rname}
                                </td>
                                <td align="left">
                                    ${row.age}
                                </td>
                                 <td align="left">
                                    ${row.gender}
                                </td>

                                 <td align="left">
                                    ${row.houseno}
                                </td>
                                 <td align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td align="left">
                                    ${row.mname}
                                </td>
                                <td align="left">
                                    ${row.dname}
                                </td>
                                <%}%>

                            </tr><%}else if(i%2==0){%>

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
                              <!--  <td>




                                </td>-->
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
                                    ${row.houseno}
                                </td>
                                 <td  align="left">
                                    ${row.hname}
                                </td>
                                <%if (count<3) {%><td  align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<2) {%><td  align="left">
                                    ${row.mname}
                                </td>
                                <td  align="left">
                                    ${row.dname}
                                </td>

                                <%}%>

                            </tr>

                            <%}%>

                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="LocomotorReport.xls?LocomotorReport=LocomotorReport&empStatus=getempDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&fromdate=${fDate}&disability=<%=dis %>&name=<%=name %>&todate=${tDate}&dID=<%=district_id%>&emp=<%=emp%>&tablee=<%=tablee%>&colu=<%=colu%>&refID=personalDetailsexcel&<%=n%>&type=<%=n1%>&query=<%=query%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            </table>
                </logic:notEmpty>




            </div>
        </body>
    </center>
</html:html>