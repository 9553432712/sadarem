<%--
    Document   : districtWiseStatusReportForPartBExcel
    Created on : May 30, 2010, 11:20:45 AM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String pensionPhase = (String) request.getParameter("pensionPhase");
            String district_id = (String) request.getParameter("dID");
            String mandal_id = (String) request.getParameter("mID");
            String dName = (String) request.getParameter("dN");
            String mName = (String) request.getParameter("mN");
            if (pensionPhase.equals("1")) {
                pensionPhase = "PhaseI";
            } else if (pensionPhase.equals("2")) {
                pensionPhase = "PhaseII";
            } else if (pensionPhase.equals("3")) {
                pensionPhase = "PhaseIII";
            } else if (pensionPhase.equals("4")) {
                pensionPhase = "PhaseIV";
            } else if (pensionPhase.equals("5")) {
                pensionPhase = "RachabandaI";
            } else if (pensionPhase.equals("7")) {
                pensionPhase = "RachabandaII";
            } else if (pensionPhase.equals("6")) {
                pensionPhase = "ALL (PhaseI,PhaseII,PhaseIV)";
            }
            int existingpensionerscount = 0, partACount = 0, totalPersonsAssed = 0, deathAndPm = 0;
            int deathPensioners = 0, pmPensioners = 0, totalDeleted = 0, assessedICFS = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0, assessedOthers = 0;
            int MICount = 0, MDCount = 0, total = 0, directRejected = 0, tobeAssessed = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            int icfsPensioners = 0, otherPensioners = 0, underGoSadarem = 0, livePensioners = 0, susTmPensioners = 0;
            ArrayList dailyreportlist = new ArrayList();
            if(session.getAttribute("partBDistrictReport")!=null){
                 dailyreportlist = (ArrayList) session.getAttribute("partBDistrictReport");
                 Iterator iter = dailyreportlist.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                deathPensioners = deathPensioners + Integer.parseInt(partAdto.getDeathPensioners());
                pmPensioners = pmPensioners + Integer.parseInt(partAdto.getPmPensioners());
                totalDeleted = totalDeleted + Integer.parseInt(partAdto.getTotalDeleted());
                //assessedICFS = assessedICFS + Integer.parseInt(partAdto.getAssessedICFS());
                deathAndPm = deathAndPm + Integer.parseInt(partAdto.getDeathAndPm());
                assessedOthers = assessedOthers + Integer.parseInt(partAdto.getAssessedOthers());
                tobeAssessed = tobeAssessed + Integer.parseInt(partAdto.getTobeAssessed());
                susTmPensioners = susTmPensioners + Integer.parseInt(partAdto.getSusTmPensioners());
                //icfsPensioners = icfsPensioners + Integer.parseInt(partAdto.getIcfsPensioners());
                otherPensioners = otherPensioners + Integer.parseInt(partAdto.getOtherPensioners());
                underGoSadarem = underGoSadarem + Integer.parseInt(partAdto.getUnderGoSadarem());
                livePensioners = livePensioners + Integer.parseInt(partAdto.getLivePensioners());
                existingpensionerscount = existingpensionerscount + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                totalPersonsAssed = totalPersonsAssed + Integer.parseInt(partAdto.getTotalpersonsassessed());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());
                total = total + Integer.parseInt(partAdto.getTotal());
                directRejected = directRejected + Integer.parseInt(partAdto.getDirectrejected());
                assessedAndRejected = assessedAndRejected + Integer.parseInt(partAdto.getAssessedandrejected());
                totalRejected = totalRejected + Integer.parseInt(partAdto.getTotalrejected());
            }
            }
           
            
%> 

<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    <table  border="1" class="innerTable" width="100%">
        <tr>
            <td class="label" align="center" colspan="26">SADAREM  Phase: <%=pensionPhase%> - Assessment status as on
                <%=fromdate%> To <%=todate%>
                District: <%=dName%>, Mandal: <%=mName%>
            </td>
        </tr>

        <tr>
            <td align="left" class="label" rowspan=2 valign="top">S.No</td>
            <% if (!mandal_id.equals("null") && !mandal_id.equals("0")) {%>
            <td rowspan=2>Village</td>
            <% } else if (!mandal_id.equals("null")) {%>
            <td rowspan="2">Mandal</td>
            <% } else if (!district_id.equals("null")) {%>
            <td rowspan="2">District</td>
            <% }%>
            <td align="left" class="label" rowspan="2">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</td>
           <td align="left" class="label" rowspan="2">ABH &<br> Not Elg</td>
            <td align="center" class="label" colspan="4">Deleted</td>
            <%-- <td align="left" class="label" rowspan="2">ICFS</td>--%>
            
            <td align="left" class="label" rowspan=2>Total No.<br> of <br>  persons<br> to under<br> go <br> SADAREM<br>(3-8)</td>
            <td align="left" class="label" rowspan=2>No. for<br> Part-A<br> entered</td>
            <td align="left" class="label" colspan="3">Total no of persons Assessed</td>
            <td align="left" class="label" rowspan="2">Total<br> Assessed<br> (12+13)</td>
            <td align="left" class="label" rowspan="2">No of<br> persons<br> to be<br> assessed<br> (9-14)</td>
            <td align="center" class="label" colspan="7">Certificates Issued</td>
            <td align="center" class="label" colspan="3">Rejected Status</td>
        </tr>
        <tr>
            <td align="left" class="label">Death</td>
            <td align="left" class="label">PM</td>
            <td align="left" class="label">Suspend & <br> TM</td>
            <td align="left" class="label">Total</td>
            <td align="left" class="label">Death </td>
            <td align="left" class="label">Live</td>

            <%--  <td align="left" class="label">ICFS</td>--%>
            <td align="left" class="label">ABH ,<br> Not ELG, <br>Suspend ,<br> TM <br> & PM</td>
            <td align="left" class="label">Ortho</td>
            <td align="left" class="label">Visual</td>
            <td align="left" class="label">Hearing</td>
            <td align="left" class="label">Mental<br>(MR)</td>
            <td align="left" class="label">Mental<br> Illness</td>
            <td align="left" class="label">Multiple<br> Disability</td>
            <td align="left" class="label">Total</td>
            <td align="center" class="label">Before<br> Assment<br> (No <br> visible<br> disb.)</td>
            <td align="center" class="label">After<br> Assment<br> (<40%<br> disb.)</td>
            <td align="left" class="label">Total<br> Rejected</td>
        </tr>
        <tr>
            <td class="label" align="center">1</td>
            <td class="label" align="center">2</td>
            <td class="label" align="center">3</td>
            <td class="label" align="center">4</td>
            <td class="label" align="center">5</td>
            <td class="label" align="center">6</td>
            <td class="label" align="center">7</td>
            <td class="label" align="center">8</td>
            <td class="label" align="center">9</td>
            <td class="label" align="center">10</td>
            <td class="label" align="center">11</td>
            <td class="label" align="center">12</td>
            <td class="label" align="center">13</td>
            <td class="label" align="center">14</td>
            <td class="label" align="center">15</td>
            <td class="label" align="center">16</td>
            <td class="label" align="center">17</td>
            <td class="label" align="center">18</td>
            <td class="label" align="center">19</td>
            <td class="label" align="center">20</td>
            <td class="label" align="center">21</td>
            <td class="label" align="center">22</td>
            <td class="label" align="center">23</td>
            <td class="label" align="center">24</td>
            <td class="label" align="center">25</td>
            <%-- <td class="label" align="center">26</td>--%>
        </tr>

        <% int i = 0;%>
            <logic:present name="partBDistrictReport">
        <logic:iterate id="modify" name="partBDistrictReport" scope="session">
            <tr>
                <td class="label"><%=++i%></td>
                <logic:notEmpty name="modify" property="district">
                    <td  class="label"><bean:write name="modify" property="district"/></td>
                </logic:notEmpty>
                <logic:notEmpty name="modify" property="mandal">
                    <td  class="label"><bean:write name="modify" property="mandal"/></td>
                </logic:notEmpty>
                <logic:notEmpty name="modify" property="townVillage">
                    <td  class="label"><bean:write name="modify" property="townVillage"/></td>
                </logic:notEmpty>
                <td class="label" align="center"><bean:write name="modify" property="existingpensioners"/></td>
                <td class="label" align="center"><bean:write name="modify" property="otherPensioners"/></td>
                <td class="label" align="center"><bean:write name="modify" property="deathPensioners"/></td>
                <td class="label" align="center"><bean:write name="modify" property="pmPensioners"/></td>

                <td class="label" align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                <td class="label" align="center"><bean:write name="modify" property="totalDeleted"/></td>
                <%-- <td class="label" align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>
                
                <td class="label" align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                <td class="label" align="center"><bean:write name="modify" property="partacount"/></td>
                <td class="label" align="center"><bean:write name="modify" property="deathAndPm"/></td>
                <td class="label" align="center"><bean:write name="modify" property="livePensioners"/></td>

                <%--  <td class="label" align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                <td class="label" align="center"><bean:write name="modify" property="assessedOthers"/></td>
                <td class="label" align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                <td class="label" align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                <td class="label" align="center"><bean:write name="modify" property="ortho"/></td>
                <td class="label" align="center"><bean:write name="modify" property="visual"/></td>
                <td class="label" align="center"><bean:write name="modify" property="hearing"/></td>
                <td class="label" align="center"><bean:write name="modify" property="mentalretardation"/></td>
                <td class="label" align="center"><bean:write name="modify" property="mentalillness"/></td>
                <td class="label" align="center"><bean:write name="modify" property="multipledisability"/></td>
                <td class="label" align="center"><bean:write name="modify" property="total"/></td>
                <td class="label" align="center"><bean:write name="modify" property="directrejected"/></td>
                <td class="label" align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                <td class="label" align="center"><bean:write name="modify" property="totalrejected"/></td>
            </tr>
        </logic:iterate>
            </logic:present>
        <tr>

            <td class="label" colspan="2"><b>Total</b></td>
            <td class="label" align="center"><b><%=existingpensionerscount%></b></td>
            <td class="label" align="center"><b><%=otherPensioners%></b></td>
            <td class="label" align="center"><b><%=deathPensioners%></b></td>
            <td class="label" align="center"><b><%=pmPensioners%></b></td>
            <td class="label" align="center"><b><%=susTmPensioners%></b></td>
            <td class="label" align="center"><b><%=totalDeleted%></b></td>
            <%-- <td class="label" align="center"><b><%=icfsPensioners%></b></td>--%>
            
            <td class="label" align="center"><b><%=underGoSadarem%></b></td>
            <td class="label" align="center"><b><%=partACount%></b></td>
            <td class="label" align="center"><b><%=deathAndPm%></b></td>
            <td class="label" align="center"><b><%=livePensioners%></b></td>

            <%-- <td class="label" align="center"><b><%=assessedICFS%></b></td>--%>
            <td class="label" align="center"><b><%=assessedOthers%></b></td>
            <td class="label" align="center"><b><%=totalPersonsAssed%></b></td>
            <td class="label" align="center"><b><%=tobeAssessed%></b></td>
            <td class="label" align="center"><b><%=orthoCount%></b></td>
            <td class="label" align="center"><b><%=visualCount%></b></td>
            <td class="label" align="center"><b><%=hearingCount%></b></td>
            <td class="label" align="center"><b><%=MRCount%></b></td>
            <td class="label" align="center"><b><%=MICount%></b></td>
            <td class="label" align="center"><b><%=MDCount%></b></td>
            <td class="label" align="center"><b><%=total%></b></td>
            <td class="label" align="center"><b><%=directRejected%></b></td>
            <td class="label" align="center"><b><%=assessedAndRejected%></b></td>
            <td class="label" align="center"><b><%=totalRejected%></b></td>
        </tr>
        <%         session.removeAttribute("partBMandalReportList");
                    session.removeAttribute("partBVillageReport");%>

    </table>
</body>
</html:html>




