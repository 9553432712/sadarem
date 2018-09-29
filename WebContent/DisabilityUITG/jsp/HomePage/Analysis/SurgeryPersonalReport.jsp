<%-- 
    Document   : SurgeryPersonalReport
    Created on : Feb 3, 2011, 4:10:03 PM
    Author     : 509865
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<% String fromDate = (String) request.getParameter("fDate");
   String toDate = (String) request.getParameter("toDate");
   String required = (String) request.getParameter("cName");
   String mandalId = (String) request.getParameter("mID");
   String villageId = (String) request.getParameter("vID");
   String habitationId = (String) request.getParameter("hID");
   String category = (String) request.getParameter("c");   
   ArrayList<FunctionalNeedReportDTO> getAddressList = new ArrayList<FunctionalNeedReportDTO>();
   getAddressList = (ArrayList) request.getAttribute("reportlist");
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
               }
   

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Who are not Come to SADAREM Camp</title>
    </head>
    <body>
        <logic:present name="reportlist" scope="request">
            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                        <tr>
                        <th align="center" colspan="13">Details of Persons with Disability Personal Details</th>
                    </tr>
                   <tr>
                        <th colspan="13">District: <%=districtName%>,
                            Mandal: <% if(mandalId.equals("a")) { %> ALL <% } else { out.println(mandalName); }%>,
                            Village: <% if(villageId.equals("a")) { %> ALL <% } else { out.println(villageName); }%>,
                            Habitation: <% if(habitationId.equals("a")) { %> ALL <% } else { out.println(habitationName); }%>
                            , Report As on From Date:<%=fromDate %>  To ToDate: <%=toDate%>
                        </th>
                    </tr>
                    <tr>
                        <th colspan="13">
                            Type of Disability: <%=typeOfDisability%>,
                            Category: <% if(!category.equals("null")) {
                            if(category.equals("1")) { %> Medical Intervention <% }
                            else if(category.equals("2")) { %> Assistive Devices <% }
                            else if(category.equals("3")) { %> Other Needs <% }
                            }
                            %>, Required: <%=required%>
                        </th>
                    </tr>
                            <tr>
                                <th class="formhdbg">S.No</th>
                                <th class="formhdbg">SADAREM ID</th>
                                <th class="formhdbg">Name</th>
                                <th class="formhdbg">Gender</th>
                                <th class="formhdbg">Age</th>
                                <th class="formhdbg">Caste</th>
                                <th class="formhdbg">Employment</th>
                                <th class="formhdbg">Type of Disability</th>
                                <th class="formhdbg">Sub Type of Disability</th>
                                <th class="formhdbg">Parts Involved</th>
                                <% if(required.equals("Surgery")) { %>
                                <th class="formhdbg">Surgery</th>
                                <% } else if(required.equals("AnyOther")) { %>
                                <th class="formhdbg">Any Other</th> <% } %>
                                <th class="formhdbg">Total Percentage</th>
                                <th class="formhdbg">Address</th>
                            </tr>
                            <% int i = 0;%>
                            <logic:iterate id="modify" name="reportlist" scope="request">

                                <tr>
                                    <td><%=++i%></td>
                                    <td  class="formbg">SID- <bean:write name="modify" property="personcode"/></td>
                                    <td class="formbg"><bean:write name="modify" property="name"/></td>
                                    <td class="formbg"><bean:write name="modify" property="gender"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="age"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="caste"/></td>
                                    <td  class="formbg"><bean:write name="modify" property="occupation"/></td>
                                    <td class="formbg"><bean:write name="modify" property="disabilityType"/></td>
                                    <td class="formbg"><bean:write name="modify" property="subtypeofDisability"/></td>
                                    <td class="formbg"><bean:write name="modify" property="partsInvolved"/></td>
                                    <td class="formbg"><bean:write name="modify" property="surgeryType"/></td>
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



