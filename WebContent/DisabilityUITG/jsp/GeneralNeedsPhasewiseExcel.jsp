<%-- 
    Document   : GeneralNeedsPhasewiseExcel
    Created on : Feb 15, 2011, 10:21:50 AM
    Author     : 509865
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<% String fromDate = (String) request.getParameter("fD");
   String toDate = (String) request.getParameter("tD");
   String required = (String) request.getParameter("cName");
   String mandalId = (String) request.getParameter("mI");
   String villageId = (String) request.getParameter("vI");
   String habitationId = (String) request.getParameter("hI");
   String other = (String) request.getParameter("R");
   String category = (String) request.getParameter("b");
   if(other.equals("1")) {
       other = "Other Needs";
       }
   ArrayList<FunctionalNeedReportDTO> getAddressList = new ArrayList<FunctionalNeedReportDTO>();
   getAddressList = (ArrayList) session.getAttribute("genaralPersonalList1");
   FunctionalNeedReportDTO addressDTO = getAddressList.get(0);
   String districtName = addressDTO.getDistrictName();
   String mandalName = addressDTO.getMandalName();
   String villageName = addressDTO.getVillageName();
   String habitationName = addressDTO.getHabitationName();
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
               } else if (typeOfDisability.equals("6")) {
                   typeOfDisability = "Multiple Disability";
               }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>General Needs</title>
    </head>
    <body>
        <logic:present name="genaralPersonalList1" scope="session">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                        <tr>
                        <td align="center" colspan="13">Details of Persons with Disability General Needs Personal Details.</td>
                    </tr>
                   <tr>
                       <td colspan="13" align="center">District: <%=districtName%>,
                            Mandal: <% if(mandalId.equals("a")) { %> ALL <% } else { out.println(mandalName); }%>,
                            Village: <% if(villageId.equals("a")) { %> ALL <% } else { out.println(villageName); }%>,
                            Habitation: <% if(habitationId.equals("a")) { %> ALL <% } else { out.println(habitationName); }%>
                            , Report As on From Date:<%=fromDate %>  To ToDate: <%=toDate%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="13" align="center">
                            Type of Disability: <%=typeOfDisability%>,
                             Category:
                             <% if(!category.equals("null")) {
                                 if(category.equals("1")) { %> Education Services-
                                 <% } else if(category.equals("2")) { %> Vocational Training-
                                 <% } else if(category.equals("3")) { %> Counseling & Guidance- <% } } %>
                                    <%=other%>
                        </td>
                    </tr>
                            <tr>
                                <td class="formhdbg">S.No</td>
                                <td class="formhdbg">SADAREM ID</td>
                                <td class="formhdbg">Name</td>
                                <td class="formhdbg">Relation Name</td>
                                <td class="formhdbg">Gender</td>
                                <td class="formhdbg">Age</td>
                                <td class="formhdbg">Caste</td>
                                <td class="formhdbg">Employment</td>
                                <td class="formhdbg">Type of Disability</td>
                                <td class="formhdbg">Sub Type of Disability</td>
                                <td class="formhdbg">Parts Involved</td>
                                <% if(other.equals("1")) { %>
                                <td align="center" class="formhdbg">Other Needs</td> <% } %>
                                <td class="formhdbg">Total Percentage</td>
                                <td class="formhdbg">Address</td>
                            </tr>
                            <% int i=((Integer)session.getAttribute("counter")).intValue();  %>
                            <logic:iterate id="modify" name="genaralPersonalList1" scope="session">

                                <tr>
                                    <td><%=i++%></td>
                                    <td  class="formbg">SID- <bean:write name="modify" property="personcode"/></td>
                                    <td class="formbg"><bean:write name="modify" property="name"/></td>
                                    <td class="formbg"><bean:write name="modify" property="ralationName"/></td>
                                    <td class="formbg"><bean:write name="modify" property="gender"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="age"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="caste"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="occupation"/></td>
                                    <td class="formbg"><bean:write name="modify" property="disabilityType"/></td>
                                    <td class="formbg"><bean:write name="modify" property="subtypeofDisability"/></td>
                                    <td class="formbg"><bean:write name="modify" property="partsInvolved"/></td>
                                    <% if(other.equals("1")) { %>
                                    <td class="formaltbg"><bean:write name="details" property="surgeryType"/></td> <% } %>
                                    <td class="formbg"><bean:write name="modify" property="totalpercent"/></td>
                                    <td  class="formbg">
                                        <logic:notEmpty name="modify" property="houseno">
                                      H.No.# <bean:write name="modify"  property="houseno"/>,
                                     </logic:notEmpty>
                                        <bean:write name="modify" property="districtName"/>
                                        <bean:write name="modify" property="mandalName"/>,
                                    <bean:write name="modify" property="villageName"/>,
                                    <bean:write name="modify" property="habitationName"/></td>
                                </tr>


                            </logic:iterate>

                        </table><br>
        </logic:present>
    </body>
</html>




