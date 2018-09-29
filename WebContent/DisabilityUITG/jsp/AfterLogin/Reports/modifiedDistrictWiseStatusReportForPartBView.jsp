<%--
    Document   : modifiedDistrictWiseStatusReportForPartBView
    Created on : Jan 22, 2013, 3:53:31 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>


<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            if (request.getParameter("financialYearWise") != null && !(request.getParameter("financialYearWise").equals("0"))) {
                String[] fr = request.getParameter("financialYearWise").split("-");
                fromdate = fr[0];
                todate = fr[1];
            }
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String panchayat_id = (String) request.getParameter("panchayat_id");
            String village_id = (String) request.getParameter("village_id");
            String pName = (String) request.getParameter("panchayatName");
            String vName = (String) request.getParameter("villageName");

            String pensionPhase = (String) request.getParameter("pensionPhase");
            if (pensionPhase.equals("1")) {
                pensionPhase = "PhaseI";
            } else if (pensionPhase.equals("2")) {
                pensionPhase = "PhaseII";
            } else if (pensionPhase.equals("3")) {
                pensionPhase = "PhaseIII";
            } else if (pensionPhase.equals("4")) {
                pensionPhase = "PhaseIV";
            } else if (pensionPhase.equals("5")) {
                pensionPhase = "RachaBandaI";
            } else if (pensionPhase.equals("6")) {
                pensionPhase = "RachaBandaII";
            } else if (pensionPhase.equals("7")) {
                pensionPhase = "After RachaBandaI";
            } else if (pensionPhase.equals("8")) {
                pensionPhase = "After RachaBandaII";
            } else {
                pensionPhase = "ALL";
            }

            int total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            int underGoSadarem = 0, toBeAssesed = 0;
            int beforeAssest = 0, existingPensioners = 0;

            ArrayList dailyreportlist = new ArrayList();
            if (request.getAttribute("dailyreportlist") != null) {
                dailyreportlist = (ArrayList) request.getAttribute("dailyreportlist");
                Iterator iter = dailyreportlist.iterator();
                while (iter.hasNext()) {
                    Map m = (Map) iter.next();
                    existingPensioners = existingPensioners + Integer.parseInt(m.get("ExistingPensioners").toString());
                    underGoSadarem = underGoSadarem + Integer.parseInt(m.get("underGoSadarem").toString());
                    total = total + Integer.parseInt(m.get("total").toString());
                    beforeAssest = beforeAssest + Integer.parseInt(m.get("beforeAssest").toString());
                    directRejected = directRejected + Integer.parseInt(m.get("directrejected").toString());
                    assessedAndRejected = assessedAndRejected + Integer.parseInt(m.get("assessedandrejected").toString());
                    totalRejected = totalRejected + Integer.parseInt(m.get("totalRejected").toString());
                    toBeAssesed = toBeAssesed + Integer.parseInt(m.get("toBeAssesed").toString());
                }
            }

%>
<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
    </head>
    <body style="margin:0px;padding:0px;" >
        <logic:present name="dailyreportlist" scope="request">
            <div style="
                background-color: #FFFFFF;width:950px; height:300px;overflow:auto;">



                <table align="center" cellspacing="1" border="0" cellpadding="4" width="90%" style="vertical-align: top" class="inputform" >
                    <tr>
                        <th align="center" colspan="10">SADAREM  Phase: <%=pensionPhase%> - Assessment status as on
                            <%=fromdate%> To <%=todate%>
                            District: <%=dName%>, Mandal: <%=mName%>,Panchayat:<%=pName%>,Village:<%=vName%>
                        </th>
                    </tr>

                    <tr >
                        <th align="center" width="5%">S.No</th>
                        <%if (village_id != null && !village_id.equals("0")) {%>
                        <th width="15%" align="center">Habitation</th>
                        <% } else if (panchayat_id != null && !panchayat_id.equals("0")) {%>
                        <th width="15%" align="center">Village</th>
                        <% } else if (mandal_id != null && !mandal_id.equals("0")) {%>
                        <th width="15%" align="center">Panchayat</th>
                        <% } else if (district_id != null && !district_id.equals("0")) {%>
                        <th width="15%"align="center">Mandal</th>
                        <% } else if (district_id != null) {%>
                        <th  align="center" width="15%">District</th>
                        <% }%>
                        <th align="left" width="10%">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</th>
                        <th align="left" width="10%">Under Go <br> SADAREM<br>(Live,<br>ABH ,<br>Not ELG)</th>
                        <th align="left" width="10%">Total Assessed </th>
                        <th align="left" width="10%">Eligible</th>
                        <th align="left" width="10%">Before <br>Assment <br>(No visible disb.)</th>
                        <th align="left" width="10%">After <br>Assment <br>(<40% disb.)</th>
                        <th align="left" width="10%">Total Rejected</th>
                        <th align="left" width="10%">To Be Assessed</th>
                    </tr>

                    <% int i = 0;%>
                    <logic:iterate id="modify" name="dailyreportlist" scope="request">
                        <html:hidden name="modify" property="mandal_name"/>
                        <html:hidden name="modify" property="fromdate"/>
                        <html:hidden name="modify" property="todate"/>
                        <%if (i % 2 == 1) {%>
                        <tr>

                            <td width="5%" align="center"  ><%=++i%></td>
                            <logic:notEmpty name="modify" property="district">
                                <td width="15%"><bean:write name="modify" property="district"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandal">
                                <td width="15%"><bean:write name="modify" property="mandal"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="panchayat_name">
                                <td width="15%"><bean:write name="modify" property="panchayat_name"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="townVillage">
                                <td width="15%"><bean:write name="modify" property="townVillage"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitation_name">
                                <td width="15%"><bean:write name="modify" property="habitation_name"/></td>
                            </logic:notEmpty>
                            <td width="10%"><bean:write name="modify" property="ExistingPensioners"/></td>
                            <td width="10%"><bean:write name="modify" property="underGoSadarem"/></td>
                            <td width="10%"><bean:write name="modify" property="total"/></td>
                            <td width="10%"><bean:write name="modify" property="beforeAssest"/></td>
                            <td width="10%"><bean:write name="modify" property="directrejected"/></td>
                            <td width="10%"><bean:write name="modify" property="assessedandrejected"/></td>
                            <td width="10%"><bean:write name="modify" property="totalRejected"/></td>
                            <td width="10%"><bean:write name="modify" property="toBeAssesed"/></td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td width="5%" align="center"  ><%=++i%></td>
                            <logic:notEmpty name="modify" property="district">
                                <td width="15%"><bean:write name="modify" property="district"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandal">
                                <td width="15%" ><bean:write name="modify" property="mandal"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="panchayat_name">
                                <td width="15%"><bean:write name="modify" property="panchayat_name"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="townVillage">
                                <td width="15%"><bean:write name="modify" property="townVillage"/></td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitation_name">
                                <td width="15%"><bean:write name="modify" property="habitation_name"/></td>
                            </logic:notEmpty>

                            <td width="10%"><bean:write name="modify" property="ExistingPensioners"/></td>
                            <td width="10%"><bean:write name="modify" property="underGoSadarem"/></td>
                            <td width="10%"><bean:write name="modify" property="total"/></td>
                            <td width="10%"><bean:write name="modify" property="beforeAssest"/></td>
                            <td width="10%"><bean:write name="modify" property="directrejected"/></td>
                            <td width="10%"><bean:write name="modify" property="assessedandrejected"/></td>
                            <td width="10%"><bean:write name="modify" property="totalRejected"/></td>
                            <td width="10%"><bean:write name="modify" property="toBeAssesed"/></td>
                        </tr>
                        <%}%>
                    </logic:iterate>

                    <tr>
                        <td colspan="2"><b>Total</b></td>
                        <td><b><%=existingPensioners%></b></td>
                        <td><b><%=underGoSadarem%></b></td>
                        <td><b><%=total%></b></td>
                        <td><b><%=beforeAssest%></b></td>
                        <td><b><%=directRejected%></b></td>
                        <td><b><%=assessedAndRejected%></b></td>
                        <td><b><%=totalRejected%></b></td>
                        <td><b><%=toBeAssesed%></b></td>
                    </tr>
                </table>

            </div>
            <table>

                <tr>
                    <td>
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center"  class="inputform" >
                            <tr>
                                <td>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <% session.setAttribute("partBDistrictReport", dailyreportlist);
                                                session.removeAttribute("partBMandalReportList");
                                                session.removeAttribute("partBVillageReport");%>
                                    <a href="sadaremexcel.xls?mode=excelWriting&fromdate=<%=fromdate%>&todate=<%=todate%>&pensionPhase=<%=pensionPhase%>&dN=<%=dName%>&mN=<%=mName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&pID=<%=panchayat_id%>&vID=<%=village_id%>&pName=<%=pName%>&vName=<%=vName%>" target="_blank">
                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                             height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <%-- <a href="sadaremStatusReportExcel.xls?fromdate=<%=fromdate%>&pensionPhase=<%=pensionPhase%>&todate=<%=todate%>&dN=<%=dName%>&mN=<%=mName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&pID=<%=panchayat_id%>&vID=<%=village_id%>&pName=<%=pName%>&vName=<%=vName%>" target="_blank">
                                             Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                  height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                    <a href="modifiedstatuscountforpartbprint.xls?fromdate=<%=fromdate%>&pensionPhase=<%=pensionPhase%>&todate=<%=todate%>&dN=<%=dName%>&mN=<%=mName%>&dID=<%=district_id%>&mID=<%=mandal_id%>&pID=<%=panchayat_id%>&vID=<%=village_id%>&pName=<%=pName%>&vName=<%=vName%>" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>

                                </td>
                            </tr>

                        </table>
                        <br/>
                        <center><font color="red" size="2">Note:If you want Print make it Landscape</font></center>
                        <br/>

                    </td>
                </tr>
            </table>
        </logic:present>
    </body>

</html:html>

