<%--
    Document   : districtWiseStatusReportForPartBView
    Created on : May 30, 2010, 4:09:42 PM
    Author     : srinivas_p
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>


<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");


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
            int icfsPensioners = 0, otherPensioners = 0, underGoSadarem = 0, livePensioners = 0;
            int susTmPensioners = 0;
            ArrayList dailyreportlist = new ArrayList();
            dailyreportlist = (ArrayList) request.getAttribute("dailyreportlist");
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
%>


<html:html>
    <head>
        <title>SADAREM</title>
      
    </head>
    <body>

        <logic:present name="dailyreportlist" scope="request">
            
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%">

                <tr><td  align="left" colspan="28"><p>SADAREM  Phase: <%=pensionPhase%> - Assessment status as on
                        <%=fromdate%> To <%=todate%>
                        District: <%=dName%>, Mandal: <%=mName%>
                        </p></td></tr>
                <tr>
                    <th align="center"  rowspan=2>S.No</th>
                    <% if (mandal_id != null && !mandal_id.equals("0")) {%>
                    <th  rowspan="2" align="center">Village</th>
                    <% } else if (mandal_id != null) {%>
                    <th  rowspan="2" align="center">Mandal</th>
                    <% } else if (district_id != null) {%>
                    <th  rowspan="2" align="center">District</th>
                    <% }%>
                    <th align="left"  rowspan="2">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</th>
                    <th align="left"  rowspan="2">ABH &<br> Not Elg</th>
                    <th align="center"  colspan="4">Deleted</th>
                    <%-- <th align="left"  rowspan="2">ICFS</th>--%>
                    <th align="left"  rowspan=2>Total No.<br> of <br>  persons<br> to under<br> go <br> SADAREM<br>(3-8)</th>
                    <th align="left"  rowspan=2>No. for<br> Part-A<br> Entered</th>
                    <th align="left"  colspan="3">Total no of persons Assessed</th>
                    <th align="left"  rowspan="2">Total<br> Assessed<br> (12+13)</th>
                    <th align="left"  rowspan="2">No of<br> persons<br> to be<br> Assessed<br> (9-14)</th>
                    <th align="center"  colspan="7">Certificates Issued</th>
                    <th align="center"  colspan="3">Rejected Status</th>
                </tr>
                <tr>
                    <th align="left" >Death</th>
                    <th align="left" >PM</th>
                    <th align="left" >Suspend & <br> TM</th>
                    <th align="left" >Total</th>
                     <th align="left" >Death </th>
                    <th align="left" >Live</th>
                    <%--  <th align="left" >ICFS</th>--%>
                    <th align="left" >ABH ,<br> Not ELG, <br>Suspend ,<br> TM <br> & PM</th>
                    <th align="left" >Ortho</th>
                    <th align="left" >Visual</th>
                    <th align="left" >Hearing</th>
                    <th align="left" >Mental<br>(MR)</th>
                    <th align="left" >Mental<br> Illness</th>
                    <th align="left" >Multiple<br> Disability</th>
                    <th align="left" >Total</th>
                    <th align="center" >Before<br> Assment<br> (No <br> visible<br> disb.)</th>
                    <th align="center" >After<br> Assment<br> (<40%<br> disb.)</th>
                    <th align="left" >Total<br> Rejected</th>
                </tr>
                <tr>
                    <td align="center">1</td>
                    <td align="center">2</td>
                    <td align="center">3</td>
                    <td align="center">4</td>
                    <td align="center">5</td>
                    <td align="center">6</td>
                    <td align="center">7</td>
                    <td align="center">8</td>
                    <td align="center">9</td>
                    <td align="center">10</td>
                    <td align="center">11</td>
                    <td align="center">12</td>
                    <td align="center">13</td>
                    <td align="center">14</td>
                    <td align="center">15</td>
                    <td align="center">16</td>
                    <td align="center">17</td>
                    <td align="center">18</td>
                    <td align="center">19</td>
                    <td align="center">20</td>
                    <td align="center">21</td>
                    <td align="center">22</td>
                    <td align="center">23</td>
                    <td align="center">24</td>
                    <td align="center">25</td>
                    <%-- <td align="center">26</td>--%>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="dailyreportlist" scope="request">
                    <html:hidden name="modify" property="mandal_name"/>
                    <html:hidden name="modify" property="fromdate"/>
                    <html:hidden name="modify" property="todate"/>
                    <%if (i % 2 == 1) {%>
                    <tr>
                        <td  align="center"  ><%=++i%></td>
                        <logic:notEmpty name="modify" property="district">
                            <td ><bean:write name="modify" property="district"/></td>
                        </logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandal">
                            <td ><bean:write name="modify" property="mandal"/></td>
                        </logic:notEmpty>
                        <logic:notEmpty name="modify" property="townVillage">
                            <td ><bean:write name="modify" property="townVillage"/></td>
                        </logic:notEmpty>

                        <td align="center"><bean:write name="modify" property="existingpensioners"/></td>
                          <td align="center"><bean:write name="modify" property="otherPensioners"/></td>
                        <td align="center"><bean:write name="modify" property="deathPensioners"/></td>
                        <td align="center"><bean:write name="modify" property="pmPensioners"/></td>

                        <td align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                        <td align="center"><bean:write name="modify" property="totalDeleted"/></td>
                        <%-- <td align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>
                      
                        <td align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                        <td align="center"><bean:write name="modify" property="partacount"/></td>
                         <td align="center"><bean:write name="modify" property="deathAndPm"/></td>
                        <td align="center"><bean:write name="modify" property="livePensioners"/></td>
                       
                        <%--  <td align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                        <td align="center"><bean:write name="modify" property="assessedOthers"/></td>
                        <td align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                        <td align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                        <td align="center"><bean:write name="modify" property="ortho"/></td>
                        <td align="center"><bean:write name="modify" property="visual"/></td>
                        <td align="center"><bean:write name="modify" property="hearing"/></td>
                        <td align="center"><bean:write name="modify" property="mentalretardation"/></td>
                        <td align="center"><bean:write name="modify" property="mentalillness"/></td>
                        <td align="center"><bean:write name="modify" property="multipledisability"/></td>
                        <td align="center"><bean:write name="modify" property="total"/></td>
                        <td align="center"><bean:write name="modify" property="directrejected"/></td>
                        <td align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                        <td align="center"><bean:write name="modify" property="totalrejected"/></td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td  align="center"   ><%=++i%></td>
                        <logic:notEmpty name="modify" property="district">
                            <td  ><bean:write name="modify" property="district"/></td>
                        </logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandal">
                            <td  ><bean:write name="modify" property="mandal"/></td>
                        </logic:notEmpty>
                        <logic:notEmpty name="modify" property="townVillage">
                            <td  ><bean:write name="modify" property="townVillage"/></td>
                        </logic:notEmpty>

                        <td  align="center"><bean:write name="modify" property="existingpensioners"/></td>
                        <td  align="center"><bean:write name="modify" property="otherPensioners"/></td>
                        <td  align="center"><bean:write name="modify" property="deathPensioners"/></td>
                        <td  align="center"><bean:write name="modify" property="pmPensioners"/></td>
                        <td  align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                        <td  align="center"><bean:write name="modify" property="totalDeleted"/></td>
                        <%-- <td  align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>
                        
                        <td  align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                        <td  align="center"><bean:write name="modify" property="partacount"/></td>
                        <td  align="center"><bean:write name="modify" property="deathAndPm"/></td>
                        <td  align="center"><bean:write name="modify" property="livePensioners"/></td>
                        
                        <%--<td  align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                        <td  align="center"><bean:write name="modify" property="assessedOthers"/></td>
                        <td  align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                        <td  align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                        <td  align="center"><bean:write name="modify" property="ortho"/></td>
                        <td  align="center"><bean:write name="modify" property="visual"/></td>
                        <td  align="center"><bean:write name="modify" property="hearing"/></td>
                        <td  align="center"><bean:write name="modify" property="mentalretardation"/></td>
                        <td  align="center"><bean:write name="modify" property="mentalillness"/></td>
                        <td  align="center"><bean:write name="modify" property="multipledisability"/></td>
                        <td  align="center"><bean:write name="modify" property="total"/></td>
                        <td  align="center"><bean:write name="modify" property="directrejected"/></td>
                        <td  align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                        <td  align="center"><bean:write name="modify" property="totalrejected"/></td>
                    </tr>
                    <%}%>
                </logic:iterate>
                <tr>

                    <td  colspan="2"><b>Total</b></td>
                    <td  align="center"><b><%=existingpensionerscount%></b></td>
                    <td  align="center"><b><%=otherPensioners%></b></td>
                    <td  align="center"><b><%=deathPensioners%></b></td>
                    <td  align="center"><b><%=pmPensioners%></b></td>
                    <td  align="center"><b><%=susTmPensioners%></b></td>
                    <td  align="center"><b><%=totalDeleted%></b></td>
                    <%-- <td  align="center"><b><%=icfsPensioners%></b></td>--%>
                    
                    <td  align="center"><b><%=underGoSadarem%></b></td>
                    <td  align="center"><b><%=partACount%></b></td>
                    <td  align="center"><b><%=deathAndPm%></b></td>
                    <td  align="center"><b><%=livePensioners%></b></td>                    
                    <%-- <td  align="center"><b><%=assessedICFS%></b></td>--%>
                    <td  align="center"><b><%=assessedOthers%></b></td>
                    <td  align="center"><b><%=totalPersonsAssed%></b></td>
                    <td  align="center"><b><%=tobeAssessed%></b></td>
                    <td  align="center"><b><%=orthoCount%></b></td>
                    <td  align="center"><b><%=visualCount%></b></td>
                    <td  align="center"><b><%=hearingCount%></b></td>
                    <td  align="center"><b><%=MRCount%></b></td>
                    <td  align="center"><b><%=MICount%></b></td>
                    <td  align="center"><b><%=MDCount%></b></td>
                    <td  align="center"><b><%=total%></b></td>
                    <td  align="center"><b><%=directRejected%></b></td>
                    <td  align="center"><b><%=assessedAndRejected%></b></td>
                    <td  align="center"><b><%=totalRejected%></b></td>
                </tr>
            </table><br>

            <table align="center">
                <%  session.setAttribute("partBDistrictReport", dailyreportlist);
                                    session.removeAttribute("partBMandalReportList");
                                    session.removeAttribute("partBVillageReport");%>
                <a href="distreportforpartbcountexcel.xls?fromdate=<%=fromdate%>&todate=<%=todate%>&pensionPhase=<%=pensionPhase%>&dN=<%=dName%>&mN=<%=mName%>&dID=<%=district_id%>&mID=<%=mandal_id%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="statuscountforpartbprint.xls?fromdate=<%=fromdate%>&todate=<%=todate%>&pensionPhase=<%=pensionPhase%>&dN=<%=dName%>&mN=<%=mName%>&dID=<%=district_id%>&mID=<%=mandal_id%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>
                <td class="label"><font color="red">Note:If you want Print make it Landscape</font></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            </table>
        </logic:present>
    </body>
    <p>&nbsp;</p>

</html:html>
