<%-- 
    Document   : AgeWiseReportPrint
    Created on : Jun 23, 2011, 2:40:13 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>

<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

             String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";
            
            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) session.getAttribute("areaDetails");
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

<html>
    <head>
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>

        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="ageListExcels" scope="session">
             <p>R3.3 : PWD's Age Wise - Details</p>
             <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">
                 <tr>
                                <logic:present name="names">
                                    <th style="text-align: center" colspan="11" class="hd_gd">
                                        <bean:write name="names"/>
                                    </th>
                                </logic:present>
                            </tr>

                <tr>

                    <th align="center" class="hd_gd">S.No</font></th>
                   <th align="center" width="20%" class="hd_gd"><bean:write name="ExcelHeader"/></th>
                    <th align="center" class="hd_gd" >Male</th>
                    <th align="center" class="hd_gd">Female</th>
                    <th align="center" class="hd_gd">Total</th>
                </tr>

                <bean:define id="maleTotal" value="0"/>
                <bean:define id="femaleTotal" value="0"/>
                <bean:define id="total" value="0"/>
                 <%String classStyle=""; %> 
                <logic:iterate name="ageListExcels" id="row" scope="session" indexId="count">
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
                        <td  class="<%=classStyle%>" style="width: 5%;text-align: center">
                            <%=i++%>
                        </td>
                        <td class="<%=classStyle%>" style="width: 15%;text-align: center">
                            ${row.name}
                        </td>
                        <td class="<%=classStyle%>" align="center"  style="width: 15%;text-align: center">
                            ${row.male}
                        </td>
                        <td class="<%=classStyle%>" align="center"  style="width: 15%;text-align: center">
                            ${row.female}
                        </td>
                        <td class="<%=classStyle%>" align="center" class="label" style="width: 15%;text-align: center">
                            ${row.male+row.female}
                        </td>
                    </tr>

                </logic:iterate>
                <tr>
                    <th  style="text-align: center" colspan="2" class="hd_gd">
                        Total
                    </th>
                    <th  style="text-align: center" class="hd_gd">
                        <b><%=maleCount%></b>
                        <input type="hidden" value="<%=maleCount%>" name="maleTot"/>
                    </th>
                    <th  style="text-align: center" class="hd_gd">
                        <b><%=femaleCount%></b>
                        <input type="hidden" value="<%=femaleCount%>" name="femaleTot"/>
                    </th>
                    <th  style="text-align: center" class="hd_gd">
                        <b><%=totalCount%></b>
                        <input type="hidden" value="<%=totalCount%>" name="tot"/>
                    </th>
                </tr>
            </table>
        </logic:notEmpty>
    </body>
</html>
