<%-- 
    Document   : districtWiseStatusReportForPartAPrint
    Created on : May 30, 2010, 5:28:33 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO" %>
<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String)request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            int existingPensionersCount = 0, partACount = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0;
            ArrayList partADistrictList = new ArrayList();
            partADistrictList = (ArrayList)session.getAttribute("partADistrictList");
            Iterator iter = partADistrictList.iterator();                               /*this is for find the mandal wise total of all the values displayed in the report*/
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
    </head>
    <body>
       <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
           
                <td>
                    <font><p>PWD's Personal Information Report On <%= fromdate%>
                        To <%= todate%></p>
                    </font>
                </td>
            <tr>
                <td>
                    <table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="inputform">
                        <tr>
                           <th align="center" >S.No</th>
                           <th ALIGN="center" >District Name</th>
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
                        <logic:iterate id="disabilityreport" name="partADistrictList" scope="session">
                            <tr>
                                <td align="center"  ><%=++i%></td>
                                <td ><bean:write name="disabilityreport" property="district"/></td>
                                <td ><bean:write name="disabilityreport" property="existingpensioners"/></td>
                                <td ><bean:write name="disabilityreport" property="partacount"/></td>
                                <td ><bean:write name="disabilityreport" property="ortho"/></td>
                                <td ><bean:write name="disabilityreport" property="visual"/></td>
                                <td ><bean:write name="disabilityreport" property="hearing"/></td>
                                <td class="label"><bean:write name="disabilityreport" property="mentalretardation"/></td>
                                <td class="label"><bean:write name="disabilityreport" property="mentalillness"/></td>
                                <td class="label"><bean:write name="disabilityreport" property="multipledisability"/></td>                                
                            </tr>
                        </logic:iterate>
                        <tr>
                            <th  width="5%">&nbsp;&nbsp;</th>
                            <th  width="16%"><b>Total</b></th>
                            <th  width="10%"><b><%=existingPensionersCount%></b></th>
                            <th  width="6%"><b><%=partACount%></b></th>
                            <th  width="4%"><b><%=orthoCount%></b></th>
                            <th  width="4%"><b><%=visualCount%></b></th>
                            <th  width="4%"><b><%=hearingCount%></b></th>
                            <th  width="4%"><b><%=MRCount%></b></th>
                            <th  width="4%"><b><%=MICount%></b></th>
                            <th  width="4%"><b><%=MDCount%></b></th>
                        </tr>
                    </table>
                </td>
            </tr>

        </table>
    </body>


</html:html>




