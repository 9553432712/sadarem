<%--
    Document   : PersonalCasteWiseSingle
    Created on : Jun 24, 2011, 3:55:47 PM
    Author     : 484898
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


                    String districtName = "ALL";
                    String mandalName = "ALL";
                    String villageName = "ALL";
                    String habName = "ALL";


                    ArrayList getAddressList = new ArrayList();
                    getAddressList = (ArrayList) request.getAttribute("areaDetails");
                    if (getAddressList.size() > 0) {
                        if (getAddressList.size() == 1) {
                            districtName = (String) getAddressList.get(0);
                        } else if (getAddressList.size() == 2) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                        } else if (getAddressList.size() == 3) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                        } else if (getAddressList.size() == 4) {
                            districtName = (String) getAddressList.get(0);
                            mandalName = (String) getAddressList.get(1);
                            villageName = (String) getAddressList.get(2);
                            habName = (String) getAddressList.get(3);
                        }
                    }
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
            
                <table  align="center" cellspacing="0" border="1" cellpadding="4" class="inputform" width="90%">
                   
                    <tr>
                        <td align="center" colspan="16"><b><p>Caste Wise Report As On <font color="blue"><%=fromdate%></font> To <font color="blue"><%=todate%></font></p></b></td>
                    </tr>

                    <tr>

                    <p align="center" colspan="11">
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
                        </p>
                    </tr>

                </table>
                <logic:notEmpty name="casteWiseDetails">
                    <table  border="0" align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">

                        <tr>
                            <th align="center" >S.No</th>
                            <th align="center" >SADAREM ID</th>
                            <th align="center" >Name</th>
                            <th align="center" >Relation Name</th>
                            <th align="center" >Age</th>



                        </tr>

                        <logic:iterate name="casteWiseDetails" id="row">
                            <tr>
                                <td  align="center">
                                    <%=i++%>.
                                </td>
                                <td >
                                    ${row.person_code}
                                </td>
                                <td >
                                    ${row.surname}
                                </td>
                                <td >
                                    ${row.relation}
                                </td>
                                <td  align="center">
                                    ${row.age_years}
                                </td>
                            </tr>

                        </logic:iterate>
                    </table><br>
                    <table align="center">

                        <a href="casteWiseReports.xls?casteStatus=getcasteDetailsAction&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&caste=<%=caste%>&refID=personalDetailsexcel" target="_blank">
                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;


                    </table>
                </logic:notEmpty>
         
        </body>
    </center>
</html:html>
