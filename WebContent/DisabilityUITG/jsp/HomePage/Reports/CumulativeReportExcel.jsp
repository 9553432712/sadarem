<%-- 
    Document   : CumulativeReportExcel
    Created on : Mar 9, 2011, 2:46:05 PM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.*" %>
<%@page import="java.sql.Date" %>
<%@page import="java.util.Calendar" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>


<%
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            cal.add(Calendar.DATE, -1);
            int phaseOne = 0, phaseIAssessed = 0, phaseIDR = 0, phaseIAR = 0, phaseIEligible = 0;
            int phaseTwo = 0, phaseIIAssessed = 0, phaseIIDR = 0, phaseIIAR = 0, phaseIIEligible = 0;
            int phaseIII = 0, phaseIIIAssessed = 0, phaseIIIDR = 0, phaseIIIAR = 0, phaseIIIEligible = 0;
            int phaseFour = 0, phaseIVAssessed = 0, phaseIVDR = 0, phaseIVAR = 0, phaseIVEligible = 0;
            int totalPhase = 0, totalAssessed = 0, totalDR = 0, totalAR = 0, totalEligible = 0;
            ArrayList dailyreportlist = new ArrayList();
            dailyreportlist = (ArrayList) request.getAttribute("cumulativeReportList");
            Iterator iter = dailyreportlist.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                phaseOne = phaseOne + Integer.parseInt(partAdto.getPhaseOne());
                phaseIAssessed = phaseIAssessed + Integer.parseInt(partAdto.getPhaseIAssessed());
                phaseIDR = phaseIDR + Integer.parseInt(partAdto.getPhaseIDR());
                phaseIAR = phaseIAR + Integer.parseInt(partAdto.getPhaseIAR());
                phaseIEligible = phaseIEligible + Integer.parseInt(partAdto.getPhaseIEligible());
                phaseTwo = phaseTwo + Integer.parseInt(partAdto.getPhaseTwo());
                phaseIIAssessed = phaseIIAssessed + Integer.parseInt(partAdto.getPhaseIIAssessed());
                phaseIIDR = phaseIIDR + Integer.parseInt(partAdto.getPhaseIIDR());
                phaseIIAR = phaseIIAR + Integer.parseInt(partAdto.getPhaseIIAR());
                phaseIIEligible = phaseIIEligible + Integer.parseInt(partAdto.getPhaseIIEligible());
                 phaseIII = phaseIII + Integer.parseInt(partAdto.getPhaseIII());
                phaseIIIAssessed = phaseIIIAssessed + Integer.parseInt(partAdto.getPhaseIIIAssessed());
                phaseIIIDR = phaseIIIDR + Integer.parseInt(partAdto.getPhaseIIIDR());
                phaseIIIAR = phaseIIIAR + Integer.parseInt(partAdto.getPhaseIIIAR());
                phaseIIIEligible = phaseIIIEligible + Integer.parseInt(partAdto.getPhaseIIIEligible());
                phaseFour = phaseFour + Integer.parseInt(partAdto.getPhaseFour());
                phaseIVAssessed = phaseIVAssessed + Integer.parseInt(partAdto.getPhaseIVAssessed());
                phaseIVDR = phaseIVDR + Integer.parseInt(partAdto.getPhaseIVDR());
                phaseIVAR = phaseIVAR + Integer.parseInt(partAdto.getPhaseIVAR());
                phaseIVEligible = phaseIVEligible + Integer.parseInt(partAdto.getPhaseIVEligible());
                totalPhase = totalPhase + Integer.parseInt(partAdto.getTotalPhases());
                totalAssessed = totalAssessed + Integer.parseInt(partAdto.getToatlAssessed());
                totalDR = totalDR + Integer.parseInt(partAdto.getTotalDR());
                totalAR = totalAR + Integer.parseInt(partAdto.getTotalAR());
                totalEligible = totalEligible + Integer.parseInt(partAdto.getTotalEligible());
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

        <logic:present name="cumulativeReportList" scope="request">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="100%">

                <tr>
                    <td class="label" align="center" colspan="22">SADAREM Phase Wise Assessment status as on:
                        <%=dateFormat.format(cal.getTime())%>
                    </td>
                </tr>
                <tr>
                    <td align="left" class="label" rowspan=2>S.No</td>
                    <td ALIGN="left" class="label" rowspan=2>District</td>
                    <td align="center" class="label" colspan="5">Phase I</td>
                    <td align="center" class="label" colspan="5">Phase II</td>
                       <td align="center" class="label" colspan="5">Phase III</td>
                    <td align="center" class="label" colspan="5">Phase IV</td>
                    <td align="center" class="label" colspan="5">Total</td>
                </tr>
                <tr>
                    <td align="left" class="label">Total</td>
                    <td align="left" class="label">Assessed</td>
                    <td align="left" class="label">Before<br> Assmnt<br> (No<br> visible<br> disb.)</td>
                    <td align="left" class="label">After<br> Assmnt<br> (<40%<br> disb.)</td>
                    <td align="left" class="label">Eligible</td>
                    <td align="left" class="label">Total</td>
                    <td align="left" class="label">Assessed</td>
                    <td align="left" class="label">Before<br> Assmnt<br> (No<br> visible<br> disb.)</td>
                    <td align="left" class="label">After<br> Assmnt<br> (<40%<br> disb.)</td>
                    <td align="left" class="label">Elgible</td>
                    <td align="left" class="label">Total</td>                    
                    <td align="left" class="label">Assessed</td>
                    <td align="left" class="label">Before<br> Assmnt<br> (No<br> visible<br> disb.)</td>
                    <td align="left" class="label">After<br> Assmnt<br> (<40%<br> disb.)</td>
                    <td align="left" class="label">Eligible</td>
                    <td align="left" class="label">Total</td>
                    <td align="left" class="label">Assessed</td>
                    <td align="left" class="label">Before<br> Assmnt<br> (No<br> visible<br> disb.)</td>
                    <td align="left" class="label">After<br> Assmnt<br> (<40%<br> disb.)</td>
                    <td align="left" class="label">Elgible</td>
                    <td align="left" class="label">Total</td>
                    <td align="left" class="label">Assessed</td>
                    <td align="left" class="label">Before<br> Assmnt<br> (No<br> visible<br> disb.)</td>
                    <td align="left" class="label">After<br> Assmnt<br> (<40%<br> disb.)</td>
                    <td align="left" class="label">Elgible</td>
                </tr>

                <% int i = 0;%>
                <logic:iterate id="modify" name="cumulativeReportList" scope="request">

                    <tr>
                        <td  align="center"  class="label" ><%=++i%></td>
                        <td  class="label"><bean:write name="modify" property="district"/></td>
                        <td class="label"><bean:write name="modify" property="phaseOne"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIAssessed"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIDR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIAR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIEligible"/></td>
                        <td class="label"><bean:write name="modify" property="phaseTwo"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIAssessed"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIDR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIAR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIEligible"/></td>
                       <td class="label"><bean:write name="modify" property="phaseIII"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIIAssessed"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIIDR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIIAR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIIIEligible"/></td>
                        <td class="label"><bean:write name="modify" property="phaseFour"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIVAssessed"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIVDR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIVAR"/></td>
                        <td class="label"><bean:write name="modify" property="phaseIVEligible"/></td>
                        <td class="label"><bean:write name="modify" property="totalPhases"/></td>
                        <td class="label"><bean:write name="modify" property="toatlAssessed"/></td>
                        <td class="label"><bean:write name="modify" property="totalDR"/></td>
                        <td class="label"><bean:write name="modify" property="totalAR"/></td>
                        <td class="label"><bean:write name="modify" property="totalEligible"/></td>
                    </tr>

                </logic:iterate>
                <tr>

                    <td class="label" colspan="2"><b>Total</b></td>
                    <td class="label"><b><%=phaseOne%></b></td>
                    <td class="label"><b><%=phaseIAssessed%></b></td>
                    <td class="label"><b><%=phaseIDR%></b></td>
                    <td class="label"><b><%=phaseIAR%></b></td>
                    <td class="label"><b><%=phaseIEligible%></b></td>
                    <td class="label"><b><%=phaseTwo%></b></td>
                    <td class="label"><b><%=phaseIIAssessed%></b></td>
                    <td class="label"><b><%=phaseIIDR%></b></td>
                    <td class="label"><b><%=phaseIIAR%></b></td>
                    <td class="label"><b><%=phaseIIEligible%></b></td>
                     <td class="label"><b><%=phaseIII%></b></td>
                    <td class="label"><b><%=phaseIIIAssessed%></b></td>
                    <td class="label"><b><%=phaseIIIDR%></b></td>
                    <td class="label"><b><%=phaseIIIAR%></b></td>
                    <td class="label"><b><%=phaseIIIEligible%></b></td>
                    <td class="label"><b><%=phaseFour%></b></td>
                    <td class="label"><b><%=phaseIVAssessed%></b></td>
                    <td class="label"><b><%=phaseIVDR%></b></td>
                    <td class="label"><b><%=phaseIVAR%></b></td>
                    <td class="label"><b><%=phaseIVEligible%></b></td>
                    <td class="label"><b><%=totalPhase%></b></td>
                    <td class="label"><b><%=totalAssessed%></b></td>
                    <td class="label"><b><%=totalDR%></b></td>
                    <td class="label"><b><%=totalAR%></b></td>
                    <td class="label"><b><%=totalEligible%></b></td>
                </tr>
            </table>


        </logic:present>
    </body>
    <p>&nbsp;</p>

</html:html>

