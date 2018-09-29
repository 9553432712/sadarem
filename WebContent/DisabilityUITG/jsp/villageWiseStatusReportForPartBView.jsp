<%-- 
    Document   : villageWiseStatusReportForPartBView
    Created on : Jun 22, 2010, 4:58:08 PM
    Author     : srinivas_p
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO" %>



<%          String districtid = (String) request.getAttribute("districtid");
            String mandalid = (String) request.getAttribute("mandalid");
            String districtname = (String) request.getAttribute("districtname");
            String mandalname = (String) request.getParameter("mandalname");
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            int existingPensionersCount = 0, partACount = 0, totalPersonsAssed = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0, total = 0, directRejected = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            ArrayList partBVillageReport = new ArrayList();
            partBVillageReport = (ArrayList) request.getAttribute("partBVillageReport");
            Iterator iter = partBVillageReport.iterator();
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    </head>
    <body>
        
        <logic:present name="partBVillageReport" scope="request">
            
            <table align="center" cellspacing="1" cellpadding="4" class="innerTable" width="60%" border="1">
                <tr><td class="formhdbg" align="center" colspan="15">Village Wise Report For Personal Information & Assessed PWD's Details</td></tr>
                <tr><td align="center" class="formhdbg" colspan="15">District:<%=districtname%>&nbsp;&nbsp;Mandal:<%=mandalname%></td></tr>
                <%--<% if (districtname == null){ %><tr><td class="heading" align="center" colspan="15">District:<%=Districtname%></td></tr> <% } else {%>
                <tr><td class="heading" align="center" colspan="15">District: <%=districtname%> </td></tr>
                <% } %>--%>

                <tr>
                    <td align="center" class="formhdbg" rowspan=2>S.No</td>
                    <td ALIGN="center" class="formhdbg" rowspan=2>Village</td>
                    <td align="center" class="formhdbg" rowspan=2>Total No. of Disabled Pensioners as per SSP data</td>
                    <td align="center" class="formhdbg" rowspan=2>No. of Part-A entered</td>
                    <td align="center" class="formhdbg" rowspan=2>Total No. of Persons Assessed</td>
                    <td align="center" class="formhdbg" colspan="7">Certificates Issued</td>
                    <td align="center" class="formhdbg" colspan="3">Rejected Status</td>
                </tr>
                <tr>
                    <td align="center" class="formhdbg">Ortho</td>
                    <td align="center" class="formhdbg">Visual</td>
                    <td align="center" class="formhdbg">Hearing</td>
                    <td align="center" class="formhdbg">Mental Retardation</td>
                    <td align="center" class="formhdbg">Mental Illness</td>
                    <td align="center" class="formhdbg">Multiple Disability</td>
                    <td align="center" class="formhdbg">Total</td>
                    <td align="center" class="formhdbg">Direct Rejected</td>
                    <td align="center" class="formhdbg">Assessed & Rejected</td>
                    <td align="center" class="formhdbg">Total Rejected</td>
                </tr>
                <% int i = 0;%>
                <logic:iterate id="modify" name="partBVillageReport" scope="request">
                    <html:hidden name="modify" property="mandal_name"/>
                    <html:hidden name="modify" property="fromdate"/>
                    <html:hidden name="modify" property="todate"/>
                    <html:hidden name="modify" property="districtid"/>
                    <%if(i%2 == 1){%>
                    <tr>
                        <td  align="center"  class="formbg" ><%=++i%></td>
                        <td  class="formbg"><%--<logic:notEqual value="3" name="districtid" scope="session">
                        <a href="villageWiseStatusReportForPartB.do?villageWiseStatusReportForPartB=villageWiseStatusReportForPartB&fromdate=<%=request.getParameter("fromdate")%>&todate=<%=request.getParameter("todate")%>&districtid=<bean:write name="modify" property="districtid"/>&districtname=<bean:write name="modify" property="district"/>">--%>
                            <bean:write name="modify" property="village_name"/><%--</a>--%></td><%--</logic:notEqual>--%>
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
                        <td  class="formaltbg"><%--<logic:notEqual value="3" name="districtid" scope="session">
                        <a href="villageWiseStatusReportForPartB.do?villageWiseStatusReportForPartB=villageWiseStatusReportForPartB&fromdate=<%=request.getParameter("fromdate")%>&todate=<%=request.getParameter("todate")%>&districtid=<bean:write name="modify" property="districtid"/>&districtname=<bean:write name="modify" property="district"/>">--%>
                            <bean:write name="modify" property="village_name"/><%--</a>--%></td><%--</logic:notEqual>--%>
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
                    <%--<td class="label">&nbsp;&nbsp;</td>--%>
                    <td align="center" class="formhdbg" colspan="2"><b>Total</b></td>
                    <td class="formhdbg"><b><%=existingPensionersCount%></b></td>
                    <td class="formhdbg"><b><%=partACount%></b></td>
                    <td class="formhdbg"><b><%=totalPersonsAssed%></b></td>
                    <td class="formhdbg"><b><%=orthoCount%></b></td>
                    <td class="formhdbg"><b><%=visualCount%></b></td>
                    <td class="formhdbg"><b><%=hearingCount%></b></td>
                    <td class="formhdbg"><b><%=MRCount%></b></td>
                    <td class="formhdbg"><b><%=MICount%></b></td>
                    <td class="formhdbg"><b><%=MDCount%></b></td>
                    <td class="formhdbg"><b><%=total%></b></td>
                    <td class="formhdbg"><b><%=directRejected%></b></td>
                    <td class="formhdbg"><b><%=assessedAndRejected%></b></td>
                    <td class="formhdbg"><b><%=totalRejected%></b></td>
                </tr>            
            </table>

                <table align="center">
                <% session.setAttribute("partBVillageReport", partBVillageReport);%>
                <a href="villagewisereportforpartbcountexcel.xls?districtname=<%=districtname%>&mandalname=<%=mandalname%>&fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="villagewisereportforpartbcountprint.xls?districtname=<%=districtname%>&mandalname=<%=mandalname%>&fromdate=<%=fromdate%>&todate=<%=todate%>" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;



                <td class="label"><font color="red">Note:If you want Print make it Landscape</font></td>


            </table>
                    <% session.removeAttribute("partBDistrictReport");
                       session.removeAttribute("partBMandalReportList");  %>

        </logic:present>

    </body>
    <p>&nbsp;</p>

</html:html>
