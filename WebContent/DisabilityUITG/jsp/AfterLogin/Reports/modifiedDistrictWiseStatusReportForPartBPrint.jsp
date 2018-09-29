<%-- 
    Document   : modifiedDistrictWiseStatusReportForPartBPrint
    Created on : Jan 23, 2013, 7:02:46 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String pensionPhase = (String) request.getParameter("pensionPhase");
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String dName = (String) request.getParameter("dN");
            String mName = (String) request.getParameter("mN");
            String panchayat_id = (String) request.getParameter("pID");
            String village_id = (String) request.getParameter("vID");
            String pName = (String) request.getParameter("pName");
            String vName = (String) request.getParameter("vName");

            int total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            int underGoSadarem = 0, toBeAssesed = 0;
            int beforeAssest = 0, existingPensioners = 0;
            ArrayList dailyreportlist = new ArrayList();
            dailyreportlist = (ArrayList) session.getAttribute("partBDistrictReport");
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
%>


<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body onLoad="window.print();">
    <body>

        <table align="center" cellspacing="1" border="0" cellpadding="4"  width="90%" class="inputform">

            <tr><td align="center" colspan="10">SADAREM  Phase: <%=pensionPhase%> - Assessment status as on
                    <%=fromdate%> To <%=todate%>
                    District: <%=dName%>, Mandal: <%=mName%>,Panchayat:<%=pName%>,Village:<%=vName%>

                </td>
            </tr>
            <tr>
                <th align="center" width="5%">S.No</th>
                <%if (village_id != null && !village_id.equals("null") && !village_id.equals("0")) {%>
                <th width="15%"  align="center">Habitation</th>
                <% } else if (panchayat_id != null && !panchayat_id.equals("null") && !panchayat_id.equals("0")) {%>
                <th width="15%"  align="center">Village</th>
                <% } else if (mandal_id != null && !mandal_id.equals("null") && !mandal_id.equals("0")) {%>
                <th width="15%"  align="center">Panchayat</th>
                <% } else if (district_id != null && !district_id.equals("null") && !district_id.equals("0")) {%>
                <th width="15%"  align="center">Mandal</th>
                <% } else if (district_id != null) {%>
                <th width="15%"  align="center">District</th>
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
            <logic:iterate id="modify" name="partBDistrictReport" scope="session">
                <html:hidden name="modify" property="mandal_name"/>
                <html:hidden name="modify" property="fromdate"/>
                <html:hidden name="modify" property="todate"/>
                <tr>
                    <td  align="center"  class="formbg" width="5%"><%=++i%></td>
                    <logic:notEmpty name="modify" property="district">
                        <td width="15%" class="formbg"><bean:write name="modify" property="district"/></td>
                    </logic:notEmpty>
                    <logic:notEmpty name="modify" property="mandal">
                        <td width="15%" class="formbg"><bean:write name="modify" property="mandal"/></td>
                    </logic:notEmpty>
                    <logic:notEmpty name="modify" property="panchayat_name">
                        <td width="15%" class="formbg"><bean:write name="modify" property="panchayat_name"/></td>
                    </logic:notEmpty>
                    <logic:notEmpty name="modify" property="townVillage">
                        <td width="15%" class="formbg"><bean:write name="modify" property="townVillage"/></td>
                    </logic:notEmpty>
                    <logic:notEmpty name="modify" property="habitation_name">
                        <td width="15%" class="formbg"><bean:write name="modify" property="habitation_name"/></td>
                    </logic:notEmpty>
                    <td class="formbg" width="10%"><bean:write name="modify" property="ExistingPensioners"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="underGoSadarem"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="total"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="beforeAssest"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="directrejected"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="assessedandrejected"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="totalRejected"/></td>
                    <td class="formbg" width="10%"><bean:write name="modify" property="toBeAssesed"/></td>

                </tr>

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
            <%         session.removeAttribute("partBMandalReportList");
                                    session.removeAttribute("partBVillageReport");%>

        </table>

    </body>
</html:html>




