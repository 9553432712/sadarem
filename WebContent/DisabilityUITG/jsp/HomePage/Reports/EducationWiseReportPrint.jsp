<%-- 
    Document   : EducationWiseReportPrint
    Created on : Mar 20, 2011, 4:23:08 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


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
        
       <title> PWD's Educational wise Details</title>
       <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
    </head>
    <body  onLoad="window.print()">
        <logic:present name="educationwiseList" scope="request">
            <p>Educationwise Report</p>
            <table  style="text-align:center" cellspacing="1" border="0" cellpadding="4"  width="90%">
                 <tr>
                    <logic:present name="names">
                        <th style="text-align: center" colspan="11" class="hd_gd">
                            <bean:write name="names"/>
                        </th>
                    </logic:present>

                </tr>
                <tr>
                    <th style="text-align:center" class="hd_gd"><b>S.No</b></th>
                    <logic:present name="ExcelHeader">
                        <th class="hd_gd">
                            <bean:write name="ExcelHeader"/>
                        </th>
                    </logic:present>
                    <th  style="text-align:center" class="hd_gd"><font >Not Entered</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Illiterate</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Below 10th</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >10th Class</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Inter</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Diploma</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Graduate</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Post Graduate</font></th>
                    <th  style="text-align:center" class="hd_gd"><font >Total</font></th>
                </tr>
                <tr>
                      <th align="center" class="hd_gd">1</th>
                      <th align="center" class="hd_gd">2</th>
                      <th align="center" class="hd_gd">3</th>
                      <th align="center" class="hd_gd">4</th>
                      <th align="center" class="hd_gd">5</th>
                      <th align="center" class="hd_gd">6</th>
                      <th align="center" class="hd_gd">7</th>
                      <th align="center" class="hd_gd">8</th>
                      <th align="center" class="hd_gd">9</th>
                      <th align="center" class="hd_gd">10</th>
                      <th align="center" class="hd_gd">11</th>
                    </tr>

                <% int i = 0;%>
                <%String classStyle=""; %>
                <logic:iterate id="modify" name="educationwiseList" scope="request" indexId="count">
                  <% if(count.intValue()%2==0)
              		 {
                    	  classStyle="secondrow";
                	 }
                      else
                      {
                    	  classStyle="firstrow";
                    	  
                      } %>
                    <tr>
                        <td  style="text-align:center" class="<%=classStyle%>"><%=++i%></td>
                        <logic:notEmpty name="modify" property="districtName">
                            <td class="<%=classStyle%>"><bean:write name="modify" property="districtName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="mandalName">
                            <td class="<%=classStyle%>"><bean:write name="modify" property="mandalName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="villageName">
                            <td class="<%=classStyle%>"><bean:write name="modify" property="villageName"/></td></logic:notEmpty>
                        <logic:notEmpty name="modify" property="habitationName">
                            <td class="<%=classStyle%>"><bean:write name="modify" property="habitationName"/></td>
                        </logic:notEmpty>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="notEntered"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="illiterate"/></td>
                            <td class="<%=classStyle%>"style="text-align:center"><bean:write name="modify" property="belowTenth"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="tenth"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="intermediate"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="diplomaOrITI"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="graduate"/></td>
                            <td class="<%=classStyle%>" style="text-align:center"><bean:write name="modify" property="postGraduate"/></td>
                            <td class="<%=classStyle%>" style="text-align:center; border-right:#234466 1px solid;"><bean:write name="modify" property="total"/></td>
                    </tr>
                </logic:iterate>
                <tr>
                    <th colspan="2"  style="text-align:center" class="hd_gd"><font >Total</font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=notEntered%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=illiterate%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=belowTenth%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=tenth%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=inter%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=diploma%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=graduate%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%=postGraduate%></font></th>
                    <th  style="text-align:center" class="hd_gd"><font ><%= total%></font></th>
                </tr>
                  
            </table><br>
        </logic:present>
    </body>
</html>



