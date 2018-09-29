<%--
    Document   : AnalysisPersonalReportExcel
    Created on : Dec 28, 2010, 5:21:46 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromDate = (String) request.getParameter("fD");
   String toDate = (String) request.getParameter("tD");
   String required = (String) request.getParameter("R");
   String secondRequired = (String) request.getParameter("R1");
   String mandalId = (String) request.getParameter("mI");
   String villageId = (String) request.getParameter("vI");
   String habitationId = (String) request.getParameter("hI");
   String category = (String) request.getParameter("c");
   String subCategory = (String) request.getParameter("s");
   String subRequired = (String)request.getParameter("b");
   String typeOfDisability = (String)request.getParameter("D");
   if (typeOfDisability.equals("1")) {
                   typeOfDisability = "Locomotor";
               } else if (typeOfDisability.equals("2")) {
                   typeOfDisability = "Visual";
               } else if (typeOfDisability.equals("3")) {
                   typeOfDisability = "Hearing";
               } else if (typeOfDisability.equals("4")) {
                   typeOfDisability = "Mental Retardation";
               } else if (typeOfDisability.equals("5")) {
                   typeOfDisability = "Menatal Illness";
               }
   ArrayList<FunctionalNeedReportDTO> getAddressList = new ArrayList<FunctionalNeedReportDTO>();
   getAddressList = (ArrayList) session.getAttribute("arraylist1");
   FunctionalNeedReportDTO addressDTO = getAddressList.get(0);
   String districtName = addressDTO.getDistrictName();
   String mandalName = addressDTO.getMandalName();
   String villageName = addressDTO.getVillageName();
   String habitationName = addressDTO.getHabitationName();      

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagewise Excel Report</title>
    </head>
    <body>
        <table  border="1" align="center" cellspacing="1" cellpadding="4" width="100%">
                    <tr>
                        <td align="center" colspan="12">Details of Persons with Disability Personal Details.</td>
                    </tr>
                   <tr>
                        <td align="center" colspan="12">District: <%=districtName%>,
                            Mandal: <% if(mandalId.equals("a")) { %> ALL <% } else { out.println(mandalName); }%>,
                            Village: <% if(villageId.equals("a")) { %> ALL <% } else { out.println(villageName); }%>,
                            Habitation: <% if(habitationId.equals("a")) { %> ALL <% } else { out.println(habitationName); }%>
                            , Report As on From Date:<%=fromDate %>  To ToDate: <%=toDate%>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="12">
                            Type of Disability: <%=typeOfDisability%>,
                            Category: <% if(!category.equals("null")) {
                            if(category.equals("1")) { %> Medical Intervention <% }
                            else if(category.equals("2")) { %> Assistive Devices <% }
                            else if(category.equals("3")) { %> Other Needs <% }
                            }
                            %>
                            <% if(!subCategory.equals("null")) { %>, Sub Category: <%
                            if(subCategory.equals("1")) { %> Prothosis <% }
                            else if(subCategory.equals("2")) { %> Orthosis <% }
                            else if(subCategory.equals("3")) { %> Mobility Aids <% }
                            else if(subCategory.equals("4")) { %> Walking Aids <% }
                            else if(subCategory.equals("5")) { %> ADL Equipments <% }
                            }
                            %> ,
                            Required:<% if(!subRequired.equals("null")) {
                             if(subRequired.equals("b3")) { %> Below 3 Years <% }
                             else if(subRequired.equals("a3")) { %> Above 3 years <% } } %> 
                             <%=required%>
                             <% if(!secondRequired.equals("null")) {
                             out.println(secondRequired);
                              } %>
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><b>S.No</b></td>
                        <td align="center"><b>SADAREM-Id</b></td>
                        <td align="center"><b>Name</b></td>
                         <td align="center"><b>Relation Name</b></td>
                        <td align="center"><b>Age</b></td>
                        <td align="center"><b>Sex</b></td>
                        <td align="center"><b>Address</b></td>
                        <td align="center"><b>Type of Disability</b></td>
                        <td align="center"><b>Sub type of Disability</b></td>
                        <td align="center"><b>Parts Involved</b></td>
                        <td align="center"><b>% of Disability</b></td>
                        <td align="center"><b>Caste</b></td>
                        <td align="center"><b>Occupation</b></td>
                    </tr>
                    <% int i=((Integer)session.getAttribute("counter")).intValue();  %>

                    <logic:iterate id="details" name="arraylist1"  scope="session">
                        <tr>
                            <td align="center"><%=i++%></td>
                            <td align="center">SID-<bean:write name="details" property="personcode"/></td>
                            <td align="center"><bean:write name="details" property="name"/></td>
                             <td class="formbg"><bean:write name="details" property="ralationName"/></td>
                            <td align="center"><bean:write name="details" property="age"/></td>
                            <td align="center"><bean:write name="details" property="gender"/></td>
                            <td align="center"><logic:notEmpty name="details" property="houseno">
                                                <bean:write name="details" property="houseno"/>,
                                                </logic:notEmpty>
                                               <bean:write name="details" property="districtName"/>,
                                               <bean:write name="details" property="mandalName"/>,
                                               <bean:write name="details" property="villageName"/>,
                                               <bean:write name="details" property="habitationName"/>
                            </td>
                            <td align="center"><bean:write name="details" property="disabilityType"/></td>
                            <td align="center"><bean:write name="details" property="subtypeofDisability"/></td>
                            <td align="center"><bean:write name="details" property="partsInvolved"/></td>
                            <td align="center"><bean:write name="details" property="totalpercent"/></td>
                            <td align="center"><bean:write name="details" property="caste"/></td>
                            <td align="center"><bean:write name="details" property="occupation"/></td>
                        </tr>

                    </logic:iterate>

                </table>
    </body>
</html>
