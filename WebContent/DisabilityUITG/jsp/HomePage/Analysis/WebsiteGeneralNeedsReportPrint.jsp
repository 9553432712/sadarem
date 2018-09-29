<%-- 
    Document   : WebsiteGeneralNeedsReport
    Created on : Jan 5, 2011, 2:56:19 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page session="true"%>
<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String hName = (String) request.getParameter("habitationName");
            String typeOfDisability = (String) request.getParameter("typeofdisability");
            Iterator iter = null;
            ArrayList commonGeneralNeedsList = new ArrayList();
            int earlyEducationServiceTotal = 0, homeBasedTotal = 0, total = 0;
            int specialEducationTotal = 0, inclusiveEducationTotal = 0, employmentPublicPvtSectorTotal = 0;
            int selfEmploymentTotal = 0, individualTotal = 0, familyTotal = 0, otherGeneralNeedsTotal = 0;
            if (request.getAttribute("commonGeneralNeedsList") != null) {
                commonGeneralNeedsList = (ArrayList) request.getAttribute("commonGeneralNeedsList");
                iter = commonGeneralNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                    homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                    specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                    inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                    employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                    selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                    individualTotal = individualTotal + totalDTO.getIndividual();
                    familyTotal = familyTotal + totalDTO.getFamily();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>



<%
            ArrayList mrGeneralNeedsList = new ArrayList();
            mrGeneralNeedsList = (ArrayList) request.getAttribute("mrGeneralNeedsList");

            int legalGurdianTotal = 0;
            if (request.getAttribute("mrGeneralNeedsList") != null) {
                iter = mrGeneralNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                    homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                    specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                    inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                    employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                    selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                    individualTotal = individualTotal + totalDTO.getInclusiveEducation();
                    familyTotal = familyTotal + totalDTO.getFamily();
                    legalGurdianTotal = legalGurdianTotal + totalDTO.getLegalGurdian();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>

<%
            ArrayList miGeneralNeedsList = new ArrayList();
            miGeneralNeedsList = (ArrayList) request.getAttribute("miGeneralNeedsList");

            int managerToTakeCarePropertiesTotal = 0;
            if (request.getAttribute("miGeneralNeedsList") != null) {
                iter = miGeneralNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                    homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                    specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                    inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                    employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                    selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                    individualTotal = individualTotal + totalDTO.getInclusiveEducation();
                    familyTotal = familyTotal + totalDTO.getFamily();
                    managerToTakeCarePropertiesTotal = managerToTakeCarePropertiesTotal + totalDTO.getManagerToTakeCareProperties();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }
            }
%>


<%
            ArrayList multipleGeneralNeedsList = new ArrayList();
            multipleGeneralNeedsList = (ArrayList) request.getAttribute("multipleGeneralNeedsList");

            if (request.getAttribute("multipleGeneralNeedsList") != null) {
                iter = multipleGeneralNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    earlyEducationServiceTotal = earlyEducationServiceTotal + totalDTO.getEarlyEducationService();
                    homeBasedTotal = homeBasedTotal + totalDTO.getHomeBased();
                    specialEducationTotal = specialEducationTotal + totalDTO.getSpecialEducation();
                    inclusiveEducationTotal = inclusiveEducationTotal + totalDTO.getInclusiveEducation();
                    employmentPublicPvtSectorTotal = employmentPublicPvtSectorTotal + totalDTO.getEmploymentPublicPvtSector();
                    selfEmploymentTotal = selfEmploymentTotal + totalDTO.getSelfEmployment();
                    individualTotal = individualTotal + totalDTO.getInclusiveEducation();
                    familyTotal = familyTotal + totalDTO.getFamily();
                    legalGurdianTotal = legalGurdianTotal + totalDTO.getLegalGurdian();
                    managerToTakeCarePropertiesTotal = managerToTakeCarePropertiesTotal + totalDTO.getManagerToTakeCareProperties();
                    otherGeneralNeedsTotal = otherGeneralNeedsTotal + totalDTO.getOtherGeneralNeeds();
                    total = total + totalDTO.getTotal();
                }

            }
            int i = 0;
%>


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>

    <body onload="window.print()">





        <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">
            <tr>
                <td align="center">
                    <logic:present name="commonGeneralNeedsList" scope="request">
                        <p>General Needs Report</p>
                        <p>
                            <logic:present name="level">${level}</logic:present></p>
                        <table align="center">
                            <tr>
                                <td>
                                    <logic:present name="back">${back}</logic:present>
                                <td>
                                    <logic:present name="areadescription"> ${areadescription}</logic:present>
                                </td>
                            </tr>


                        </table>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                <th align="center" class="formhdbg" rowspan="2">Other General Needs</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Early Education Service(<5Yrs)</th>
                                <th class="formhdbg" align="center">Home Based</th>
                                <th class="formhdbg" align="center">Special Education</th>
                                <th class="formhdbg" align="center">Inclusive Education</th>
                                <th class="formhdbg" align="center">Employment in public/ Pvt.Sector</th>
                                <th class="formhdbg" align="center">Self Employment</th>
                                <th class="formhdbg" align="center">Individual</th>
                                <th class="formhdbg" align="center">Family</th>
                            </tr>

                            <logic:iterate id="modify" name="commonGeneralNeedsList" scope="request">
                                <tr>
                                    <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="earlyEducationService"/>
                                    </td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="homeBased"/>
                                    </td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="specialEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="inclusiveEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="employmentPublicPvtSector"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="selfEmployment"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="individual"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="family"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="otherGeneralNeeds"/></td>
                                    <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%= total%></th>
                            </tr>
                        </table>

                    </logic:present>


                    <logic:present name="mrGeneralNeedsList" scope="request">
                        <p>General Needs Report</p>

                        <table align="center">
                            <tr>
                                <td>

                                    <table align="center">
                                        <tr>
                                            <td>
                                                <logic:present name="level">${level}</logic:present>
                                            </td>
                                        </tr>
                                    </table>
                                    <table align="center">
                                        <tr>

                                            <td style="text-align: center">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>

                                        </tr>
                                    </table>
                                </td>
                            <tr>



                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>

                                <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                <th align="center" class="formhdbg" rowspan="2">Legal Gurdian</th>
                                <th align="center" class="formhdbg" rowspan="2">Other General Needs</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Early Education Service(<5Yrs)</th>
                                <th class="formhdbg" align="center">Home Based</th>
                                <th class="formhdbg" align="center">Special Education</th>
                                <th class="formhdbg" align="center">Inclusive Education</th>
                                <th class="formhdbg" align="center">Employment in public/ Pvt.Sector</th>
                                <th class="formhdbg" align="center">Self Employment</th>
                                <th class="formhdbg" align="center">Individual</th>
                                <th class="formhdbg" align="center">Family</th>
                            </tr>
                            <% i = 0;%>
                            <logic:iterate id="modify" name="mrGeneralNeedsList" scope="request">
                                <tr>
                                    <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="earlyEducationService"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="homeBased"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="specialEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="inclusiveEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="employmentPublicPvtSector"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="selfEmployment"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="individual"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="family"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="legalGurdian"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="otherGeneralNeeds"/></td>
                                    <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=legalGurdianTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%= total%></th>
                            </tr>
                        </table>

                    </logic:present>

                    <logic:present name="miGeneralNeedsList" scope="request">
                        <p>General Needs Report</p>
                        <table align="center">
                            <tr>
                                <td>

                                    <table align="center">
                                        <tr>
                                            <td>
                                                <logic:present name="level">${level}</logic:present>
                                            </td>
                                        </tr>
                                    </table>
                                    <table align="center">
                                        <tr>

                                            <td style="text-align: center">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>

                                        </tr>
                                    </table>
                                </td>
                            <tr>



                            </tr>
                        </table>
                      
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                <th align="center" class="formhdbg" rowspan="2">Manager to Take Care Properties</th>
                                <th align="center" class="formhdbg" rowspan="2">Other General Needs</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Early Education Service(<5Yrs)</th>
                                <th class="formhdbg" align="center">Home Based</th>
                                <th class="formhdbg" align="center">Special Education</th>
                                <th class="formhdbg" align="center">Inclusive Education</th>
                                <th class="formhdbg" align="center">Employment in public/ Pvt.Sector</th>
                                <th class="formhdbg" align="center">Self Employment</th>
                                <th class="formhdbg" align="center">Individual</th>
                                <th class="formhdbg" align="center">Family</th>
                            </tr>
                            <% i = 0;%>
                            <logic:iterate id="modify" name="miGeneralNeedsList" scope="request">

                                <tr>
                                    <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="earlyEducationService"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="homeBased"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="specialEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="inclusiveEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="employmentPublicPvtSector"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="selfEmployment"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="individual"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="family"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="managerToTakeCareProperties"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="otherGeneralNeeds"/></td>
                                    <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=managerToTakeCarePropertiesTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>


                    <logic:present name="multipleGeneralNeedsList" scope="request">
                        <p>General Needs Report</p>
                        <table align="center">
                            <tr>
                                <td>

                                    <table align="center">
                                        <tr>
                                            <td>
                                                <logic:present name="level">${level}</logic:present>
                                            </td>
                                        </tr>
                                    </table>
                                    <table align="center">
                                        <tr>

                                            <td style="text-align: center">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>

                                        </tr>
                                    </table>
                                </td>
                            <tr>



                            </tr>
                        </table>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="4">Education Services</th>
                                <th align="center" class="formhdbg" colspan="2">Vocational Training</th>
                                <th align="center" class="formhdbg" colspan="2">Counseling & Guidance</th>
                                <th align="center" class="formhdbg" rowspan="2">Legal Gurdian</th>
                                <th align="center" class="formhdbg" rowspan="2">Manager to Take Care Properties</th>
                                <th align="center" class="formhdbg" rowspan="2">Other General Needs</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Early Education Service(<5Yrs)</th>
                                <th class="formhdbg" align="center">Home Based</th>
                                <th class="formhdbg" align="center">Special Education</th>
                                <th class="formhdbg" align="center">Inclusive Education</th>
                                <th class="formhdbg" align="center">Employment in public/ Pvt.Sector</th>
                                <th class="formhdbg" align="center">Self Employment</th>
                                <th class="formhdbg" align="center">Individual</th>
                                <th class="formhdbg" align="center">Family</th>
                            </tr>
                            <% i = 0;%>
                            <logic:iterate id="modify" name="multipleGeneralNeedsList" scope="request">
                                <tr>
                                    <td  style="text-align: center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="earlyEducationService"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="homeBased"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="specialEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="inclusiveEducation"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="employmentPublicPvtSector"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="selfEmployment"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="individual"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="family"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="legalGurdian"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="managerToTakeCareProperties"/></td>
                                    <td class="formbg" style="text-align: center">
                                        <bean:write name="modify" property="otherGeneralNeeds"/></td>
                                    <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                <th class="formhdbg" style="text-align: center"><%=earlyEducationServiceTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=homeBasedTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=specialEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=inclusiveEducationTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=employmentPublicPvtSectorTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=selfEmploymentTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=individualTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=familyTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=legalGurdianTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=managerToTakeCarePropertiesTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%=otherGeneralNeedsTotal%></th>
                                <th class="formhdbg" style="text-align: center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>
                </td>
            </tr>
        </table>


    </body>
</html:html>
