<%-- 
    Document   : statusCountForPartAPrint
    Created on : May 28, 2010, 9:56:50 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
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
    <body  onLoad="window.print()">
    <head>
        <title>SADAREM</title>
    </head>
    <body>
       <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">
           <tr>
               <td style="text-align: center">
                   PWD's Personal Information Report On <%= fromdate%> To <%= todate%>
               </td>
           </tr>
                <tr>
                    <td>
                        <table border="0" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">

            <%-- <table  align="center" border="1" cellspacing="1" cellpadding="5" class="table" width="60%">--%>
            <tr>
                <th align="center" >S.No</th>
                <th ALIGN="center" >Mandal Name</th>
                <th align="center" >Existing Pensioners</th>
                <th align="center" >Part-A</th>
                <th align="center" >P.I</th>
                <th align="center" >V.I</th>
                <th align="center" >H.I</th>
                <th align="center" >M.R</th>
                <th align="center" >M.I</th>
                <th align="center" >M.D</th>
            </tr>
            <% int i = 0;%>
            <logic:iterate id="disabilityreport" name="partAList" scope="session">
                <tr>
                    <td align="center" ><%=++i%></td>
                    <td ><bean:write name="disabilityreport" property="mandal_name"/></td>
                    <td ><bean:write name="disabilityreport" property="existingpensioners"/></td>
                    <td ><bean:write name="disabilityreport" property="partacount"/></td>
                    <td ><bean:write name="disabilityreport" property="ortho"/></td>
                    <td ><bean:write name="disabilityreport" property="visual"/></td>
                    <td ><bean:write name="disabilityreport" property="hearing"/></td>
                    <td ><bean:write name="disabilityreport" property="mentalretardation"/></td>
                    <td ><bean:write name="disabilityreport" property="mentalillness"/></td>
                    <td ><bean:write name="disabilityreport" property="multipledisability"/></td>
                </tr>
            </logic:iterate>
            <tr>
                <th  width="5%">&nbsp;&nbsp;</th>
                <th  width="16%"><b>Total</b></th>
                <th  width="10%"><b><%=existingPensionersCount%></b></th>
                <th  width="6%"><b><%=partACount%></b></th>
                <th  width="4%"><b><%=orthoCount%></b></th>
                <th width="4%"><b><%=visualCount%></b></th>
                <th  width="4%"><b><%=hearingCount%></b></th>
                <th  width="4%"><b><%=MRCount%></b></th>
                <th  width="4%"><b><%=MICount%></b></th>
                <th  width="4%"><b><%=MDCount%></b></th>


            </tr>
        </table>
                </td>
                </tr>
                </table>

        <%--</table>--%>
    </body>


</html:html>



