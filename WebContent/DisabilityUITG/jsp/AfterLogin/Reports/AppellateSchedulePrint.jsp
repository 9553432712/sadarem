<%-- 
    Document   : AppellateSchedulePrint
    Created on : Aug 25, 2011, 7:27:54 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
       
            String districtName = null;
            String mandalName = null;
       

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
            if (getAddressList.size() == 1) {
                districtName = (String) getAddressList.get(0);
            } else if (getAddressList.size() == 2) {
                districtName = (String) getAddressList.get(0);
                mandalName = (String) getAddressList.get(1);
            } 
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="scheduleData" scope="request">
            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                <tr>
                    <td align="center" colspan="11" class="formhdbg"><b>Appellate Authority Schedule Report</b></td>
                </tr>
                <tr>
                    <td align="center" colspan="11">
                        <%
                                  
                                    if (mandalName != null ) {
                                        if (!mandalName.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                        <% }
                                    }
                                    if (districtName != null && mandalName == null) {
                                        if (!districtName.equals("null")) {%>
                        District: <font color="blue"><%=districtName%></font>,
                        <% }
                                    }%>
                    </td>
                </tr>

                <tr>

                    <th align="center" class="formhdbg" >S.No</th>
                    <th align="center" class="formhdbg" >Village Name</th>
                    <th align="center" class="formhdbg" >Schedule Date</th>
                  

                </tr>

             
                <logic:iterate name="scheduleData" id="row" scope="request">
                 
                    <tr>
                        <td align="center" class="label" style="width: 5%;">
                            <%=i++%>
                        </td>
                        <td class="label" style="width: 15%;">
                            ${row.village_name}
                        </td>
                        <td align="center" class="label" style="width: 15%;">
                            ${row.scheduleDate}
                        </td>
                       
                    </tr>

                </logic:iterate>

              
            </table>


        </logic:notEmpty>
       
    </body>
</html>
