<%-- 
    Document   : NiramayaDistrictExcelReport
    Created on : Sep 23, 2010, 10:33:59 AM
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
                <td colspan="5"><b><font color="blue">Excel sheet for Districtwise Niramaya Report</font></b></td>
            </tr>
           <tr class="tbl_bg2_content_hdr_new" valign="middle">
                    <td><b>S.NO</b></td>
                    <td><b>District Name</b></td>
                    <td><b>MENTAL RETARDATION</b></td>
                    <td><b>CEREBRALPALSY</b></td>
                    <td><b>MULTIPLE DISABILITY</b></td>
                </tr>

                <% int i=0;%>
                <logic:notEmpty name="districtList" scope="session">
                <logic:iterate id="modify" name="districtList" scope="session">
                    <tr class="tbl_bg2_content_hdr_new" valign="middle">
                        <td class="cellheader"><%=++i%> </td>
                        <td><bean:write name="modify" property="district"/></td>
                        <td>&nbsp;<bean:write name="modify" property="districtMRCount"/></td>
                        <td>&nbsp;<bean:write name="modify" property="districtCEREBRALPALSYCount"/></td>
                        <td>&nbsp;<bean:write name="modify" property="districtMULTIPLECount"/></td>
                    </tr>

                </logic:iterate>
                    <tr>
                        <td colspan="2"> Total</td>
                        <td>&nbsp;<%=mrTotal%></td>
                        <td>&nbsp;<%=cpTotal%></td>
                        <td>&nbsp;<%=multipleTotal%></td>
                    </tr> </logic:notEmpty>
            </table>
                        <% session.removeAttribute("habitationList");
                           session.removeAttribute("mandalList");
                           session.removeAttribute("villageList");%>

                    
    </body>
    <p>&nbsp;</p>

</html:html>
