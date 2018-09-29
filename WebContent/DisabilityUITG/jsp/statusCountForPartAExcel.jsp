<%-- 
    Document   : statusCountForPartAExcel
    Created on : May 28, 2010, 9:15:33 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>>
<%@page import="org.bf.disability.dto.PartADTO" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            ArrayList partAList = new ArrayList();
            partAList = (ArrayList) session.getAttribute("partAList");
            int existingPensionersCount = 0, partACount = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0;
            Iterator iter = partAList.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                existingPensionersCount = existingPensionersCount + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());

            }%>

<html:html>
    <head>
        <title>Mandlawise count reportexcel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    </head>
    <body>
        <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="50%">
            <tr align="center"  width="100%">
                <td colspan="10"><font color="#c71585">PWD's Personal Information Report On <%= fromdate%> To <%= todate%></font>
                </td>
            </tr>
        </table>
        <%--  <tr><td class="heading" align="center">District: <%= districtname%> </td></tr>--%>

        <table  align="center" border="1" cellspacing="1" cellpadding="8" class="innerTable1" width="60%">
            <tr>
                <td align="center" class="labelBlue">S.No</td>
                <td ALIGN="center" class="labelBlue">Mandal Name</td>
                <td align="center" class="labelBlue">Existing Pensioners</td>
                <td align="center" class="labelBlue">Part-A</td>
                <td align="center" class="labelBlue">P.I</td>
                <td align="center" class="labelBlue">V.I</td>
                <td align="center" class="labelBlue">H.I</td>
                <td align="center" class="labelBlue">M.R</td>
                <td align="center" class="labelBlue">M.I</td>
                <td align="center" class="labelBlue">M.D</td>
            </tr>
            <% int i = 0;%>
            <logic:iterate id="disabilityreport" name="partAList" scope="session">
                <tr>
                    <td  align="center" class="label" width="75%" ><%=++i%></td>
                    <td  class="label"><bean:write name="disabilityreport" property="mandal_name"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="existingpensioners"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="partacount"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="ortho"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="visual"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="hearing"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="mentalretardation"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="mentalillness"/></td>
                    <td class="label"><bean:write name="disabilityreport" property="multipledisability"/></td>
                </tr>
            </logic:iterate>
            <tr>
                <td class="label" width="5%">&nbsp;&nbsp;</td>
                <td class="label" width="16%"><b>Total</b></td>
                <td class="label" width="10%"><b><%=existingPensionersCount%></b></td>
                <td class="label" width="6%"><b><%=partACount%></b></td>
                <td class="label" width="4%"><b><%=orthoCount%></b></td>
                <td class="label" width="4%"><b><%=visualCount%></b></td>
                <td class="label" width="4%"><b><%=hearingCount%></b></td>
                <td class="label" width="4%"><b><%=MRCount%></b></td>
                <td class="label" width="4%"><b><%=MICount%></b></td>
                <td class="label" width="4%"><b><%=MDCount%></b></td>


            </tr>
        </table><br>


    </body>


</html:html>


