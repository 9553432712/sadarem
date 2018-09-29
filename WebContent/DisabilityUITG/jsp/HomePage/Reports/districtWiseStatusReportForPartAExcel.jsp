<%-- 
    Document   : districtWiseStatusReportForPartAExcel
    Created on : May 30, 2010, 11:20:45 AM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            int existingPensionersCount = 0, partACount = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0;
            int MICount = 0, MDCount = 0;
            ArrayList partADistrictList = new ArrayList();
            partADistrictList = (ArrayList) session.getAttribute("partADistrictList");
           
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
    <head>
        <title>Mandlawise count reportexcel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    </head>
    <body>

        <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="50%">
            <logic:present name="partADistrictList" scope="session">
                <tr align="center"  width="100%">

                    <td><font color="#c71585">PWD's Personal Information Report On <%= fromdate%>
                            To <%= todate%></font> </td>
                </tr>

               
                    <tr>
                        <td align="center">S.No</td>
                        <td ALIGN="center" >District Name</td>
                        <td align="center">Existing Pensioners</td>
                        <td align="center" >Part-A</td>
                        <td align="center" >P.I</td>
                        <td align="center" >V.I</td>
                        <td align="center">H.I</td>
                        <td align="center" >M.R</td>
                        <td align="center" >M.I</td>
                        <td align="center">M.D</td>
                    </tr>
                    <% int i = 0;%>
                    <logic:iterate id="row" name="partADistrictList" scope="session">
                        <tr>
                            <td  align="center" class="label" width="75%" ><%=++i%></td>
                            <td  class="label"><bean:write name="row" property="district"/></td>
                            <td class="label"><bean:write name="row" property="existingpensioners"/></td>
                            <td class="label"><bean:write name="row" property="partacount"/></td>
                            <td class="label"><bean:write name="row" property="ortho"/></td>
                            <td class="label"><bean:write name="row" property="visual"/></td>
                            <td class="label"><bean:write name="row" property="hearing"/></td>
                            <td class="label"><bean:write name="row" property="mentalretardation"/></td>
                            <td class="label"><bean:write name="row" property="mentalillness"/></td>
                            <td class="label"><bean:write name="row" property="multipledisability"/></td>
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
                
            </logic:present>
        </table>


    </body>


</html:html>



