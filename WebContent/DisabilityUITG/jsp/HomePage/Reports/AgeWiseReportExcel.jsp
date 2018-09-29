<%-- 
    Document   : AgeWiseReportExcel
    Created on : Jun 23, 2011, 1:10:25 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        
    </head>
<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");
            String fromDate = (String) request.getParameter("fromDate");
            String toDate = (String) request.getParameter("toDate");
            String fromAge = (String) request.getParameter("fromAge");
            String toAge = (String) request.getParameter("toAge");

            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";

            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areaDetails");
            //  for(int j = 0;j<=getAddressList.size();j++) {
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



            int femaleCount = 0, maleCount = 0, totalCount = 0;
            ArrayList list = new ArrayList();
            if (session.getAttribute("ageListExcels") != null) {
                list = (ArrayList) session.getAttribute("ageListExcels");
                Iterator iter = list.iterator();
                while (iter.hasNext()) {
                    Map m = (Map) iter.next();
                    maleCount = maleCount + Integer.parseInt(m.get("male").toString());
                    femaleCount = femaleCount + Integer.parseInt(m.get("female").toString());
                }
                totalCount = maleCount + femaleCount;

            }
%>

    <body>
        <logic:notEmpty name="ageListExcels" scope="session">
            <table border="0" cellspacing="0" cellpadding="10" align="center">
                <tr>
                    <td height="445" align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4">
                          <tr><th class="hd_gd" colspan="4" style="text-align: center">R3.3 : PWD's Age Wise - Details</th></tr>
                                <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="4" class="hd_gd">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>
                            </tr>
                            <tr >
                                <td align="center" class="hd_gd"><b>S.No</b></td>
                                  <th align="center" width="20%" class="hd_gd"><bean:write name="ExcelHeader"/></th>
                                <td align="center" width="10%" class="hd_gd" ><b>Male</b></td>
                                <td align="center" width="10%" class="hd_gd"><b>Female</b></td>
                                <td align="center" width="10%" class="hd_gd"><b>Total</b></td>
                            </tr>
                           <tr>
                      <td align="center" class="hd_gd">1</td>
                      <td align="center" class="hd_gd">2</td>
                      <td align="center" class="hd_gd">3</td>
                      <td align="center" class="hd_gd">4</td>
                      <td align="center" class="hd_gd">5</td>
                    </tr>
                            <bean:define id="maleTotal" value="0"/>
                            <bean:define id="femaleTotal" value="0"/>
                            <bean:define id="total" value="0"/>
                            <logic:iterate name="ageListExcels" id="row" scope="session" indexId="count">
                             <%String classStyle=""; %>
                               <% if(count.intValue()%2==0)
              		           {
                    	       classStyle="secondrow";
                	           }
                              else
                             {
                    	       classStyle="firstrow";
                    	  
                            } %>
                                <bean:define id="maleTotal" value="${maleTotal+row.male}"/>
                                <bean:define id="femaleTotal" value="${femaleTotal+row.female}"/>
                                <bean:define id="total" value="${total+row.total}"/>
                                <tr>
                                    <td align="center" class="<%=classStyle%>">
                                        <%=i++%>
                                    </td>
                                    <td class="<%=classStyle%>">
                                        ${row.name}
                                    </td>
                                    <td align="center" class="<%=classStyle%>">
                                        ${row.male}
                                    </td>
                                    <td align="center" class="<%=classStyle%>">
                                        ${row.female}
                                    </td>
                                    <td align="center" class="<%=classStyle%>">
                                        ${row.male+row.female}
                                    </td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="hd_gd" align="center" colspan="2">
                                    Total
                                </td>
                                <td class="hd_gd" align="center" >
                                    <b><%=maleCount%></b>
                                    <input type="hidden" value="<%=maleCount%>" name="maleTot"/>
                                </td>
                                <td class="hd_gd" align="center">
                                    <b><%=femaleCount%></b>
                                    <input type="hidden" value="<%=femaleCount%>" name="femaleTot"/>
                                </td>
                                <td class="hd_gd" align="center">
                                    <b><%=totalCount%></b>
                                    <input type="hidden" value="<%=totalCount%>" name="tot"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br/>

        </logic:notEmpty>



    </body>
</html>
