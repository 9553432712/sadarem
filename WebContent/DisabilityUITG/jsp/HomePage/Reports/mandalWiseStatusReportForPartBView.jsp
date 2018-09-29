<%-- 
    Document   : mandalWiseStatusReportForPartBView
    Created on : Jun 8, 2010, 3:14:43 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="org.bf.disability.dto.PartADTO" %>

<%      String districtid = (String) request.getAttribute("districtid");
            String districtname = (String) request.getAttribute("districtname");
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            int existingPensionersCount = 0, partACount = 0, totalPersonsAssed = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0, total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            ArrayList partBMandalReportList = new ArrayList();
            partBMandalReportList = (ArrayList) request.getAttribute("partBMandalReport");
            Iterator iter = partBMandalReportList.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                existingPensionersCount = existingPensionersCount + Integer.parseInt(partAdto.getExistingpensioners());
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
        <logic:present name="partBMandalReport" scope="request">
            <table align="center" cellspacing="0" cellpadding="4" class="table" width="100%" border="1">
                <tr><td  align="center" colspan="15"><p>Mandal Wise Report For Personal Information & Assessed PWD's Details</p></td></tr>
                <tr><td  align="center" colspan="15"><p>District: <%=districtname%> <p></td></tr>
                <tr>
                    <th align="center"  rowspan=2>S.No</th>
                    <th ALIGN="center"  rowspan=2>Mandal</th>
                    <th align="center"  rowspan=2>Total No. of Disabled Pensioners as per SSP data</th>
                    <th align="center"  rowspan=2>No. of Part-A Entered</th>
                    <th align="center"  rowspan=2>Total No of Persons Assessed</th>
                    <th align="center"  colspan="7">Certificates Issued</th>
                    <th align="center"  colspan="3">Rejected Status</th>
                </tr>
                <tr>
                    <th align="center" >Ortho</th>
                    <th align="center" >Visual</th>
                    <th align="center" >Hearing</th>
                    <th align="center" >Mental Retardation</th>
                    <th align="center" >Mental Illness</th>
                    <th align="center" >Multiple Disability</th>
                    <th align="center" >Total</th>
                    <th align="center" >Direct Rejected</th>
                    <th align="center" >Assessed & Rejected</th>
                    <th align="center" >Total Rejected</th>
                </tr>
                <% int i = 0;%>
                <logic:iterate id="modify" name="partBMandalReport" scope="request">
                    <html:hidden name="modify" property="mandal_name"/>
                    <html:hidden name="modify" property="fromdate"/>
                    <html:hidden name="modify" property="todate"/>
                    <html:hidden name="modify" property="districtid"/>
                    <html:hidden name="modify" property="mandalid"/>
                    <%if(i%2 == 1) {%>
                    <tr>
                        <td  align="center"  class="formbg" ><%=++i%></td>
                        <td  class="formbg"><logic:notEqual value="3" name="districtid" scope="session">

                                <a href="villageWiseStatusReportForPartB.do?villageWiseStatusReportForPartB=villageWiseStatusReportForPartB&fromdate=<%=fromdate%>&todate=<%=todate%>&districtid=<%=districtid%>&districtname=<%=districtname%>&mandalid=<bean:write name="modify" property="mandalid"/>&mandalname=<bean:write name="modify" property="mandal_name"/>">

                                    <bean:write name="modify" property="mandal_name"/></a></td></logic:notEqual>
                        <td class="formbg"><bean:write name="modify" property="existingpensioners"/></td>
                        <td class="formbg"><bean:write name="modify" property="partacount"/></td>
                        <td class="formbg"><bean:write name="modify" property="totalpersonsassessed"/></td>
                        <td class="formbg"><bean:write name="modify" property="ortho"/></td>
                        <td class="formbg"><bean:write name="modify" property="visual"/></td>
                        <td class="formbg"><bean:write name="modify" property="hearing"/></td>
                        <td class="formbg"><bean:write name="modify" property="mentalretardation"/></td>
                        <td class="formbg"><bean:write name="modify" property="mentalillness"/></td>
                        <td class="formbg"><bean:write name="modify" property="multipledisability"/></td>
                        <td class="formbg"><bean:write name="modify" property="total"/></td>
                        <td class="formbg"><bean:write name="modify" property="directrejected"/></td>
                        <td class="formbg"><bean:write name="modify" property="assessedandrejected"/></td>
                        <td class="formbg"><bean:write name="modify" property="totalrejected"/></td>
                    </tr>
                    <%}else{%>
                    <tr>
                        <td  align="center"  class="formaltbg" ><%=++i%></td>
                        <td  class="formaltbg"><logic:notEqual value="3" name="districtid" scope="session">
                                <a href="villageWiseStatusReportForPartB.do?villageWiseStatusReportForPartB=villageWiseStatusReportForPartB&fromdate=<%=fromdate%>&todate=<%=todate%>&districtid=<%=districtid%>&districtname=<%=districtname%>&mandalid=<bean:write name="modify" property="mandalid"/>&mandalname=<bean:write name="modify" property="mandal_name"/>">

                                    <bean:write name="modify" property="mandal_name"/></a></td></logic:notEqual>
                        <td class="formaltbg"><bean:write name="modify" property="existingpensioners"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="partacount"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="totalpersonsassessed"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="ortho"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="visual"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="hearing"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="mentalretardation"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="mentalillness"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="multipledisability"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="total"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="directrejected"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="assessedandrejected"/></td>
                        <td class="formaltbg"><bean:write name="modify" property="totalrejected"/></td>
                    </tr>
                    <%}%>
                </logic:iterate>
                <tr>
                    <td >&nbsp;&nbsp;</td>
                    <td ><b>Total</b></td>
                    <td ><b><%=existingPensionersCount%></b></td>
                    <td ><b><%=partACount%></b></td>
                    <td ><b><%=totalPersonsAssed%></b></td>
                    <td ><b><%=orthoCount%></b></td>
                    <td ><b><%=visualCount%></b></td>
                    <td ><b><%=hearingCount%></b></td>
                    <td ><b><%=MRCount%></b></td>
                    <td ><b><%=MICount%></b></td>
                    <td ><b><%=MDCount%></b></td>
                    <td ><b><%=total%></b></td>
                    <td ><b><%=directRejected%></b></td>
                    <td ><b><%=assessedAndRejected%></b></td>
                    <td ><b><%=totalRejected%></b></td>
                </tr>
            </table>
            <table align="center">
                <%     session.setAttribute("partBMandalReportList", partBMandalReportList);
                       session.removeAttribute("partBDistrictReport");
                       session.removeAttribute("partBVillageReport");  %>
                       <tr><td align="center">
                        <a href="mandalreportforpartbcountexcel.xls?districtname=<%=districtname%>&fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="mandalreportforpartbcountprint.xls?districtname=<%=districtname%>&fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                            Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>
                    <td class="label"><font color="red">Note:If you want Print make it Landscape</font>
                    </td></tr>
            </table>
        </logic:present>
    </body>
    <p>&nbsp;</p>
</html:html>
