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


                     String name=(String) request.getAttribute("names");//disability
                    String dis=(String) request.getParameter("disability");
                   
String tok=null;
                   StringTokenizer st=new StringTokenizer(name,":");
                    int count=0;
                    while(st.hasMoreTokens()){
                         tok= st.nextToken();
                       if(tok!=null && !tok.contains("ALL"))
                        count++;
                        }
                  
         

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="empWiseDetails">
            <table width="99%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr>


                                <td align="center" colspan="10"><b>    Appellate Authority Registred Status Report

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%>, <%=name%>,<%=dis %> </font>   </b></td></tr>
                            <tr>
                                <td align="center" colspan="5">
                                    <%
                                                if (villageName != null) {
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
                            </tr>
                            <tr>
                            <td align="center" class="formhdbg">S.No</td>
                            <td align="center" class="formhdbg">SADAREM ID</td>
                           <!-- <td align="center" class="formhdbg">SurName</td>-->
                            <td align="center" class="formhdbg">Name</td>
                              <td align="center" class="formhdbg">RelationName</td>
                            <td align="center" class="formhdbg">Age</td>
                            <td align="center" class="formhdbg">Gender</td>
                          
                            <td align="center" class="formhdbg">House No</td>
                            <td align="center" class="formhdbg">HabitationName</td>
                            <%if (count<4) {%>
                            <td align="center" class="formhdbg">VillageName</td><%}%>

                            <%if (count<3) {%>

                            <td align="center" class="formhdbg">MandalName</td>
                           <!--  <td align="center" class="formhdbg">Type of Disability</td>-->
                           <%}%>
                            <!-- <td align="center" class="formhdbg">Type of Disability</td>-->
                        </tr>


                        

                            <logic:iterate name="empWiseDetails" id="row">
                                <tr>
                                <td class="formbg" align="center">
                                    <%=i++%>.
                                </td>
                                <td class="formbg" align="center">
                                    ${row.person_code}
                                </td>
                                
                                <td class="formbg" align="center">
                                    ${row.name}
                                </td>
                                <td class="formbg" align="center">
                                    ${row.rname}
                                </td>
                                <td class="formbg" align="center">
                                    ${row.age}
                                </td>
                                 <td class="formbg" align="center">
                                    ${row.gender}
                                </td>
                                
                                <td class="formbg" align="center">
                                    ${row.houseno}
                                </td>
                                 <td class="formbg" align="center">
                                    ${row.hname}
                                </td> <%if (count<4) {%><td class="formbg" align="center">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<3) {%><td class="formbg" align="center">
                                    ${row.mname}
                                </td> <%}%>



                                <!--<td class="formbg" align="center">
                                    {row.disability}
                                </td>-->
                            </tr>
                            </logic:iterate>



                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>
    </body>
</html>
