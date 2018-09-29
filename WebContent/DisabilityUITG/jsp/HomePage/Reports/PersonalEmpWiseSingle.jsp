<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<html:html>
    
    <head>
        <%
                    int i = 1;
                    String dI = (String) request.getParameter("dID");
                    String mI = (String) request.getParameter("mID");
                    String vI = (String) request.getParameter("vID");
                    String fD = (String) request.getParameter("fDate");
                    String tD = (String) request.getParameter("toDate");
                    String fromdate = (String) request.getParameter("fDate");
                    String todate = (String) request.getParameter("toDate");
                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                    String village_id = (String) request.getParameter("vID");
                    String caste = (String) request.getParameter("caste");
                     String urbanId = (String) request.getParameter("urbanId");
                    String districtName = null;
                    String mandalName = null;
                    String villageName = null;
                    String habName = null;
                    ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
            //  for(int j = 0;j<=getAddressList.size();j++) {
            if (getAddressList.size() == 1) {
                districtName = (String) getAddressList.get(0);
            } else if (getAddressList.size() == 2) {
                districtName = (String) getAddressList.get(0);
                mandalName = (String) getAddressList.get(1);
            } else if (getAddressList.size() == 3) {
                districtName = (String) getAddressList.get(0);
                mandalName = (String) getAddressList.get(1);
                villageName = (String) getAddressList.get(2);
            }
//}
%>
        <script language="javascript" >
            function changecolor(colorvar)
            {
                var colorvar1=colorvar;
                document.getElementById(colorvar1).style.color="red";
            }
        </script>
    </head>
    <center>
        <body>
            <div style="border:1px solid #000000;
                 background-color: #FFFFFF;width:1000px; height:100%; overflow-x:scroll;
                 overflow-y:scroll;">
                <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="103%">
                    <tr>
                        <td align="center" colspan="16"><img src="DisabilityUITG/images/Banner.jpg"/></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="16"><b>Employement Wise Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></b></td>
                    </tr>
                    <tr>
                        <td>
                        <td align="center" colspan="11">
                            <%
                                        if (villageName != null) {
                                            if (!villageName.equals("null")) {%>
                            District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                            Village: <font color="blue"><%=villageName%></font>
                            <% }
                                        }
                                        if (mandalName != null && villageName == null) {
                                            if (!mandalName.equals("null")) {%>
                            District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>
                            <% }
                                        }
                                        if (districtName != null && mandalName == null) {
                                            if (!districtName.equals("null")) {%>
                            District: <font color="blue"><%=districtName%></font>
                            <% }
                                    }%>
                        </td>
                    </tr>
                </table>
                <logic:notEmpty name="empWiseDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="4" class="tbl_bg2" width="100%">
                        <tr>
                            <td class="formhdbg" align="center" colspan="13"> Employement Wise Details.</td>
                        </tr>
                        <tr>
                           <tr>
                            <td align="center" class="formhdbg">S.No</td>
                            <td align="center" class="formhdbg">SADAREM Id</td>
                            <td align="center" class="formhdbg">Name</td>
                            <td align="center" class="formhdbg">Relation</td>
                            <td align="center" class="formhdbg">Age</td>
                        </tr>
                        <logic:iterate name="empWiseDetails" id="row">
                            <tr>
                                <td class="formbg" align="center">
                                    <%=i++%>.
                                </td>
                                <td class="formbg">
                                    ${row.person_code}
                                </td>
                                <td class="formbg">
                                    ${row.surname}
                                </td>
                                <td class="formbg">
                                    ${row.relation}
                                </td>
                                <td class="formbg" align="center">
                                    ${row.age_years}
                                </td>
                            </tr>
                        </logic:iterate>
                    </table><br>
                      <table align="center">
                <a href="casteWiseReports.xls?casteStatus=getcasteDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&urbanId=<%=urbanId%>&caste=<%=caste%>&refID=personalDetailsexcel" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                      </table>
                </logic:notEmpty>
            </div>
        </body>
    </center>
</html:html>
