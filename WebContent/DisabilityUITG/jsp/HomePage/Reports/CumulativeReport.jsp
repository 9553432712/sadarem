<%-- 
    Document   : CumulativeReport
    Created on : Mar 9, 2011, 11:26:57 AM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.*" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>


<%

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
        
    </head>
    <body>

        <logic:present name="cumulativeReportList" scope="request">
            <table  align="center" cellspacing="1" border="0" cellpadding="0" width="90%" class="inputform">
                <tr>
                    <td align="left" colspan="28"><p>SADAREM Phase Wise Assessment status as on:
                        <script>

                            var dt = new Date();
                            var dat = dt.getDate()-1;
                            var d =dat+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                            if(dat=="0") {
                                d="01"+"/0"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                            }
                            document.write(d);

                        </script></p>
                    </td>
                </tr>
                <tr>
                    <th align="left"  rowspan=2>S.No</th>
                    <th ALIGN="left"  rowspan=2>District</th>
                    <th align="center"  colspan="5">Phase I</th>
                    <th align="center"  colspan="5">Phase II</th>
                      <th align="center"  colspan="5">Phase III</th>
                    <th align="center"  colspan="5">Phase IV</th>
                    <th align="center"  colspan="5">Total</th>
                </tr>
                <tr>
                    <th align="left" >Total</th>
                    <th align="left" >Assessed</th>
                    <th align="left" >Before Assmnt (No visible disb.)</th>
                    <th align="left" >After Assmnt (<40% disb.)</th>
                    <th align="left" >Eligible</th>
                    <th align="left" >Total</th>
                    <th align="left" >Assessed</th>
                    <th align="left" >Before Assmnt (No visible disb.)</th>
                    <th align="left" >After Assmnt (<40% disb.)</th>
                    <th align="left" >Eligible</th>
                    <th align="left" >Total</th>
                    <th align="left" >Assessed</th>
                    <th align="left" >Before Assmnt (No visible disb.)</th>
                    <th align="left" >After Assmnt (<40% disb.)</th>
                    <th align="left" >Eligible</th>
                    <th align="left" >Total</th>
                    <th align="left" >Assessed</th>
                    <th align="left" >Before Assmnt (No visible disb.)</th>
                    <th align="left" >After Assmnt (<40% disb.)</th>
                    <th align="left" >Eligible</th>
                    <th align="left" >Total</th>
                    <th align="left" >Assessed</th>
                    <th align="left" >Before Assmnt (No visible disb.)</th>
                    <th align="left" >After Assmnt (<40% disb.)</th>
                    <th align="left" >Eligible</th>
                </tr>

                <% int i = 0;%>
                <logic:iterate id="modify" name="cumulativeReportList" scope="request">

                    <%if (i % 2 == 1) {%>
                    <tr>
                        <td  align="center"  ><%=++i%></td>
                        <td ><bean:write name="modify" property="district"/></td>
                        <td><bean:write name="modify" property="phaseOne"/></td>
                        <td><bean:write name="modify" property="phaseIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIDR"/></td>
                        <td><bean:write name="modify" property="phaseIAR"/></td>
                        <td><bean:write name="modify" property="phaseIEligible"/></td>
                        <td><bean:write name="modify" property="phaseTwo"/></td>
                        <td><bean:write name="modify" property="phaseIIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIIDR"/></td>
                        <td><bean:write name="modify" property="phaseIIAR"/></td>
                        <td><bean:write name="modify" property="phaseIIEligible"/></td>
                        <td><bean:write name="modify" property="phaseIII"/></td>
                        <td><bean:write name="modify" property="phaseIIIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIIIDR"/></td>
                        <td><bean:write name="modify" property="phaseIIIAR"/></td>
                        <td><bean:write name="modify" property="phaseIIIEligible"/></td>
                        <td><bean:write name="modify" property="phaseFour"/></td>
                        <td><bean:write name="modify" property="phaseIVAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIVDR"/></td>
                        <td><bean:write name="modify" property="phaseIVAR"/></td>
                        <td><bean:write name="modify" property="phaseIVEligible"/></td>
                        <td><bean:write name="modify" property="totalPhases"/></td>
                        <td><bean:write name="modify" property="toatlAssessed"/></td>
                        <td><bean:write name="modify" property="totalDR"/></td>
                        <td><bean:write name="modify" property="totalAR"/></td>
                        <td><bean:write name="modify" property="totalEligible"/></td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td  align="center"  ><%=++i%></td>
                        <td ><bean:write name="modify" property="district"/></td>
                        <td><bean:write name="modify" property="phaseOne"/></td>
                        <td><bean:write name="modify" property="phaseIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIDR"/></td>
                        <td><bean:write name="modify" property="phaseIAR"/></td>
                        <td><bean:write name="modify" property="phaseIEligible"/></td>
                        <td><bean:write name="modify" property="phaseTwo"/></td>
                        <td><bean:write name="modify" property="phaseIIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIIDR"/></td>
                        <td><bean:write name="modify" property="phaseIIAR"/></td>
                        <td><bean:write name="modify" property="phaseIIEligible"/></td>
                        <td><bean:write name="modify" property="phaseIII"/></td>
                        <td><bean:write name="modify" property="phaseIIIAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIIIDR"/></td>
                        <td><bean:write name="modify" property="phaseIIIAR"/></td>
                        <td><bean:write name="modify" property="phaseIIIEligible"/></td>
                        <td><bean:write name="modify" property="phaseFour"/></td>
                        <td><bean:write name="modify" property="phaseIVAssessed"/></td>
                        <td><bean:write name="modify" property="phaseIVDR"/></td>
                        <td><bean:write name="modify" property="phaseIVAR"/></td>
                        <td><bean:write name="modify" property="phaseIVEligible"/></td>
                        <td><bean:write name="modify" property="totalPhases"/></td>
                        <td><bean:write name="modify" property="toatlAssessed"/></td>
                        <td><bean:write name="modify" property="totalDR"/></td>
                        <td><bean:write name="modify" property="totalAR"/></td>
                        <td><bean:write name="modify" property="totalEligible"/></td>
                    </tr>
                    <%}%>
                </logic:iterate>
                <tr>

                    <td  colspan="2"><b>Total</b></td> 
                    <td ><b><%=phaseOne%></b></td>
                    <td ><b><%=phaseIAssessed%></b></td>
                    <td ><b><%=phaseIDR%></b></td>
                    <td ><b><%=phaseIAR%></b></td>
                    <td ><b><%=phaseIEligible%></b></td>
                    <td ><b><%=phaseTwo%></b></td>
                    <td ><b><%=phaseIIAssessed%></b></td>
                    <td ><b><%=phaseIIDR%></b></td>
                    <td ><b><%=phaseIIAR%></b></td>
                    <td ><b><%=phaseIIEligible%></b></td>
                    <td ><b><%=phaseIII%></b></td>
                    <td ><b><%=phaseIIIAssessed%></b></td>
                    <td ><b><%=phaseIIIDR%></b></td>
                    <td ><b><%=phaseIIIAR%></b></td>
                    <td ><b><%=phaseIIIEligible%></b></td>
                    <td ><b><%=phaseFour%></b></td>
                    <td ><b><%=phaseIVAssessed%></b></td>
                    <td ><b><%=phaseIVDR%></b></td>
                    <td ><b><%=phaseIVAR%></b></td>
                    <td ><b><%=phaseIVEligible%></b></td>
                    <td ><b><%=totalPhase%></b></td>
                    <td ><b><%=totalAssessed%></b></td>
                    <td ><b><%=totalDR%></b></td>
                    <td ><b><%=totalAR%></b></td>
                    <td ><b><%=totalEligible%></b></td>
                </tr>
            </table><br>
            <table align="center">

                <a href="cumulativereportExcel.xls?getcumulativeReport=getcumulativeReport" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="cumulativereportPrint.xls?getcumulativeReport=getcumulativeReport" target="_blank">
                    Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>
                <td class="label"><font color="red">Note:If you want Print make it Landscape</font></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            </table>

        </logic:present>
    </body>

</html:html>
