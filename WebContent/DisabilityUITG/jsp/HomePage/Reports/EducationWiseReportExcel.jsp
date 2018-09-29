<%-- 
    Document   : EducationWiseReportExcel
    Created on : Mar 20, 2011, 4:06:36 PM
    Author     : 509865
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");
            String fromDate = (String) request.getParameter("fdate");
            String toDate = (String) request.getParameter("todate");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");

            ArrayList educationwiseList = new ArrayList();
            educationwiseList = (ArrayList) request.getAttribute("educationwiseList");
            int illiterate = 0, belowTenth = 0, total = 0;
            int tenth = 0, inter = 0, diploma = 0;
            int graduate = 0, postGraduate = 0, notEntered = 0;
            Iterator iter = educationwiseList.iterator();
            while (iter.hasNext()) {
                FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                notEntered = notEntered + totalDTO.getNotEntered();
                illiterate = illiterate + totalDTO.getIlliterate();
                belowTenth = belowTenth + totalDTO.getBelowTenth();
                tenth = tenth + totalDTO.getTenth();
                inter = inter + totalDTO.getIntermediate();
                diploma = diploma + totalDTO.getDiplomaOrITI();
                graduate = graduate + totalDTO.getGraduate();
                postGraduate = postGraduate + totalDTO.getPostGraduate();
                total = total + totalDTO.getTotal();
            }
            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


           
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <logic:present name="educationwiseList" scope="request">
            <p>Educationwise Report</p>
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="11">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>

                </tr>
                <tr>
                    <td align="center" class="formhdbg"><b>S.No</b></td>
                    <logic:present name="ExcelHeader">
                        <th >
                            <bean:write name="ExcelHeader"/>
                        </th>
                    </logic:present>
                    <td><b>Not Entered</b></td>
                    <td align="center"><b>Illiterate</b></td>
                    <td align="center"><b>Below 10th</b></td>
                    <td align="center"><b>10th Class</b></td>
                    <td align="center"><b>Inter</b></td>
                    <td align="center"><b>Diploma</b></td>
                    <td align="center"><b>Graduate</b></td>
                    <td align="center"><b>Post Graduate</b></td>
                    <td align="center"><b>Total</b></td>
                </tr>


                <% int i = 0;%>
                <logic:iterate id="modify" name="educationwiseList" scope="request">
                    <tr>
                        <td  align="center"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                        <td align="center"><bean:write name="modify" property="notEntered"/></td>
                        <td align="center"><bean:write name="modify" property="illiterate"/></td>
                        <td align="center"><bean:write name="modify" property="belowTenth"/></td>
                        <td align="center"><bean:write name="modify" property="tenth"/></td>
                        <td align="center"><bean:write name="modify" property="intermediate"/></td>
                        <td align="center"><bean:write name="modify" property="diplomaOrITI"/></td>
                        <td align="center"><bean:write name="modify" property="graduate"/></td>
                        <td align="center"><bean:write name="modify" property="postGraduate"/></td>
                        <td align="center"><b><bean:write name="modify" property="total"/></b></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <td colspan="2" align="center"><b>Total</b></td>
                    <td align="center"><b><%=notEntered%></b></td>
                    <td align="center"><b><%=illiterate%></b></td>
                    <td align="center"><b><%=belowTenth%></b></td>
                    <td align="center"><b><%=tenth%></b></td>
                    <td align="center"><b><%=inter%></b></td>
                    <td align="center"><b><%=diploma%></b></td>
                    <td align="center"><b><%=graduate%></b></td>
                    <td align="center"><b><%=postGraduate%></b></td>
                    <td align="center"><b><%= total%></b></td>
                </tr>
            </table><br>
        </logic:present>
    </body>
</html>


