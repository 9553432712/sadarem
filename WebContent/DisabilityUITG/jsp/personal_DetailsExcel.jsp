<%-- 
    Document   : personal_DetailsExcel
    Created on : Jan 5, 2012, 11:09:58 AM
    Author     : 490058
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

                   String n=(String)request.getAttribute("type1");


String n1=request.getParameter("type"); 
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:notEmpty name="ageDetails">
            <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
                <tr>
                    <td height="445" align="left" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="5">
                            <tr><td align="center" colspan="10"><b>  SADAREM Agewise Person's  Personal Details Profile

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><br><% if(name!=null) {%> <%=name%><%}%> </font>   </b>
 <b> <font color="blue">  <% if(n!=null) {%>Selected Category: <%=n%> <%}%>   </font></b>
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
                             <td align="left" class="formhdbg">Disability Type</td>
                            <td align="left" class="formhdbg">Percentage</td>

                            <td align="left" class="formhdbg">House No</td>
                            <td align="left" class="formhdbg">HabitationName</td>
                            <%if (count<=2) {%>
                            <td align="left" class="formhdbg">VillageName</td><%}%>

                            <%if (count<1) {%>

                            <td align="left" class="formhdbg">MandalName</td>
                           <!--  <td align="left" class="formhdbg">Type of Disability</td>-->
                           <%}%>
                            <!-- <td align="left" class="formhdbg">Type of Disability</td>-->
                        </tr>




                            <logic:iterate name="ageDetails" id="row">


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
                                </td> <%if (count<=2) {%><td class="formbg" align="left">
                                    ${row.vname}
                                </td><%}%>
                                <%if (count<1) {%><td class="formbg" align="left">
                                    ${row.mname}
                                </td> <%}%>
                                </tr>
                            </logic:iterate>



                        </table>
                    </td></tr></table><br/>

        </logic:notEmpty>
    </body>
</html>