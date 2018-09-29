<%-- 
    Document   : NiramayaVillageExcelReport
    Created on : Sep 23, 2010, 11:48:30 AM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%
    String mrTotal = (String)request.getParameter("mrTotal");
    String cpTotal = (String)request.getParameter("cpTotal");
    String multipleTotal = (String)request.getParameter("multipleTotal");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>

    </head>
    <body><br>

             <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="100%">
            <tr align="center"  width="100%">
                <td colspan="5"><b><font color="blue">Excel sheet for Villagewise Niramaya Report</font></b></td>
            </tr>
           <tr class="tbl_bg2_content_hdr_new" valign="middle">
                    <td><b>S.NO</b></td>
                    <td><b>Village Name</b></td>
                    <td><b>MENTAL RETARDATION</b></td>
                    <td><b>CEREBRALPALSY</b></td>
                    <td><b>MULTIPLE DISABILITY</b></td>
                </tr>

                <% int i=0;%>
                <logic:notEmpty name="villageList" scope="session" >
                <logic:iterate id="modify" name="villageList" scope="session">
                    <tr class="tbl_bg2_content_hdr_new" valign="middle">
                        <td class="cellheader"><%=++i%> </td>
                        <td><bean:write name="modify" property="villagename"/></td>
                        <td>&nbsp;<bean:write name="modify" property="villageMRCount"/></td>
                        <td>&nbsp;<bean:write name="modify" property="villageCEREBRALPALSYCount"/></td>
                        <td>&nbsp;<bean:write name="modify" property="villageMULTIPLECount"/></td>
                    </tr>

                </logic:iterate>
                    <tr>
                        <td colspan="2"> Total</td>
                        <td>&nbsp;<%=mrTotal%></td>
                        <td>&nbsp;<%=cpTotal%></td>
                        <td>&nbsp;<%=multipleTotal%></td>
                    </tr> </logic:notEmpty>
            </table>
                    <% session.removeAttribute("mandalList");
                       session.removeAttribute("habitationList");
                       session.removeAttribute("districtList");%>

    </body>
    <p>&nbsp;</p>

</html:html>
