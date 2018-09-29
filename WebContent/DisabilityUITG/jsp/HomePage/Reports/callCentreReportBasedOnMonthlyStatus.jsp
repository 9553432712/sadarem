<%-- 
    Document   : callCentreReportBasedOnMonthlyStatus
    Created on : May 24, 2013, 11:47:16 AM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<html>
    <head>

        <script>

            function getReport(){
                document.forms[0].mode.value="getcallCentreReport";
                document.forms[0].submit();
            }

        </script>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
        <title>SADAREM</title>
    </head>
    <body>

        <html:form action="callCentreReport.do?mode=getMonthWiseCallCentreReport&page=onlyDist">
            <html:hidden property="mode"/>
            <br>
            <%
                        String districtId = "";
                        if (request.getAttribute("districtId") != null) {
                            districtId = request.getAttribute("districtId").toString();
                        }
                        String urbanId = "";
                        if (request.getAttribute("urbanId") != null) {
                            urbanId = request.getAttribute("urbanId").toString();
                        }
                        int assessPendingNewComp = 0;
                        int dOBPendingNewComp = 0;
                        int duplPendingNewComp = 0, iDPendingNewComp = 0;
                        int namePendingNewComp = 0, nCPendingNewComp = 0;
                        int physiPendingNewComp = 0, ressessPendingNewComp = 0;
                        int renewalPendingNewComp = 0, relationNamePendingNewComp = 0;
                        int totalPending = 0;
                        ArrayList reportList = new ArrayList();
                        if (request.getAttribute("DistrictAllList") != null) {
                            reportList = (ArrayList) request.getAttribute("DistrictAllList");
                            Iterator iter = reportList.iterator();
                            while (iter.hasNext()) {
                                Map m = (Map) iter.next();


                                if (districtId.equalsIgnoreCase("ALL")) {
                                    assessPendingNewComp = assessPendingNewComp + Integer.parseInt(m.get("AssessPendingNewComp").toString());

                                    dOBPendingNewComp = dOBPendingNewComp + Integer.parseInt(m.get("DOBPendingNewComp").toString());

                                    duplPendingNewComp = duplPendingNewComp + Integer.parseInt(m.get("DuplPendingNewComp").toString());

                                    iDPendingNewComp = iDPendingNewComp + Integer.parseInt(m.get("IDPendingNewComp").toString());

                                    namePendingNewComp = namePendingNewComp + Integer.parseInt(m.get("NamePendingNewComp").toString());

                                    nCPendingNewComp = nCPendingNewComp + Integer.parseInt(m.get("NCPendingNewComp").toString());

                                    physiPendingNewComp = physiPendingNewComp + Integer.parseInt(m.get("PhysiPendingNewComp").toString());

                                    ressessPendingNewComp = ressessPendingNewComp + Integer.parseInt(m.get("RessessPendingNewComp").toString());

                                    renewalPendingNewComp = renewalPendingNewComp + Integer.parseInt(m.get("RenewalPendingNewComp").toString());

                                    relationNamePendingNewComp = relationNamePendingNewComp + Integer.parseInt(m.get("RelationNamePendingNewComp").toString());


                                } else {
                                    totalPending = totalPending + Integer.parseInt(m.get("Pending").toString());
                                }

                            }
                        }

            %>

            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>
            <p id="errmsg"> <logic:present name="msg">
                    <bean:write name="msg"/>
                </logic:present></p>

            <table  align="center" cellspacing="0" cellpadding="0" width="90%"  >


                <tr> <th colspan="4" class="hd_gd" align="center" valign="middle">
                    R6.3 : District and Request Type Wise Grievances Report
                    </th></tr>
             
                <tr>
                    <td>
                        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
									    <tr>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_top.png" width="16" height="16" /></td>
					                     <td width="100%" align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_top_bg.png); background-repeat:repeat-x;"></td>
					                     <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_top.png" width="16" height="16" /></td>
					                   </tr>
					                   <tr>
					                     <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_lft_bg.png); background-repeat:repeat-y;">&nbsp;</td>
					                     <td align="left" valign="top" >
					    		           <table  cellspacing="0" width="100%" border="0px">
									        <tr height="30">
                                <td valign="middle"  width="8%">District</td>
                                <td align="center" valign="middle">
                                    <html:select  property="district_id"  style="width:150px;height:25px;font-size:11px;">
                                        <html:option value="ALL">--ALL--</html:option>
                                        <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                    </html:select>
                                </td>
                                <td  valign="middle" width="12%">Area Type</td>
                                <td align="left" valign="middle">
                                    <html:select styleId="4" property="urban_id" style="height:25px;" >
                                        <html:option  value="0">--ALL--</html:option>
                                        <html:option  value="Rural">Rural</html:option>
                                        <html:option  value="Urban">Urban</html:option>
                                    </html:select>

                                </td>
                               
                                <td>
                                    <html:button property="sub" onclick="getReport();" value="Submit"/>
                                </td>
                           </tr>
									    
									    </table> 
									  </td>
					                <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_rgt_bg.png); background-repeat:repeat-y">&nbsp;</td>
					             </tr>
								<tr>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_lft_bom.png" width="16" height="19" /></td>
						        <td align="left" valign="top" style="background-image:url(<%=request.getContextPath()%>/images/cur_bom_bg.png); background-repeat:repeat-x;">&nbsp;</td>
						        <td align="left" valign="top"><img src="<%=request.getContextPath()%>/images/cur_rgt_bom.png" width="16" height="19" /></td>
						      </tr>
						    </table>
                </td></tr>
              </table>
              <br>
            <%int i = 1;%>

            <logic:notEmpty name="DistrictAllList" scope="request">
              <table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                    <tr>
                        <td>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td align="right">

                            <a href="callCentreReport.do?mode=getMonthWiseCallCentreReport&pageType=distexcel&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">
                                <img src="DisabilityUITG/images/excel.jpg"
                                                     height="35" width="25" title="Export Excel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <a href="callCentreReport.do?mode=getMonthWiseCallCentreReport&pageType=distprint&districtId=<%=districtId%>&urbanId=<%=urbanId%>" target="_blank">
                                 <img src="DisabilityUITG/images/print.gif" height="25" width="25" title="Print"/></a>

                        </td>
                    </tr>

                </table>
                <%if (districtId.equalsIgnoreCase("ALL")) {%>

                <%}%>
                <table width="60%" border="0" cellspacing="1" cellpadding="0" align="center" >
			        <tbody>
                     <tr >
                  
                         <th class="hd_gd" align="center" valign="middle">
                            SNo.
                        </th>
                        
                        <%if (districtId.equalsIgnoreCase("ALL")) {%>
                         <th class="hd_gd" align="center" valign="middle">
                            District
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            Assessment Completed. But Not Getting Certificate Pending
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Date of Birth Pending
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Duplicate Certificate Pending
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            Identification Marks Pending
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Name Pending
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            New Certificate Pending
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                            Physical Impairment Pending
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                            Re Assessment Pending
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            Relation Name Pending
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            Renewal Pending
                        </th>
                        <%} else {%>


                         <th class="hd_gd" align="center" valign="middle">
                            Request Type
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            Pending
                        </th>
                        <%}%>

                    </tr>
                    
                    
                     <tr >
                  
                         <th class="hd_gd" align="center" valign="middle">
                            1
                        </th>
                        
                        <%if (districtId.equalsIgnoreCase("ALL")) {%>
                         <th class="hd_gd" align="center" valign="middle">
                            2
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                           3
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                           3
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                           4
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            5
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                          6
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                            7
                        </th>

                        <th class="hd_gd" align="center" valign="middle">
                         8
                        </th>
                         <th class="hd_gd" align="center" valign="middle">
                           9
                        </th>
                        <th class="hd_gd" align="center" valign="middle">
                           10
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                          11
                        </th>
                        <%} else {%>


                         <th class="hd_gd" align="center" valign="middle">
                           2
                        </th>
                       <th class="hd_gd" align="center" valign="middle">
                            3
                        </th>
                        <%}%>

                    </tr>
                    
                    
                    
                    
                    
                    
						<%String classStyle=""; %>
                    <logic:iterate id="row" name="DistrictAllList" indexId="count">
                
                              <%   if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%>
                        <tr>
                            <td class=<%=classStyle%> align="center">
                                <%=i++%>.
                            </td>
                            <%if (districtId.equalsIgnoreCase("ALL")) {%>

                            <td  class=<%=classStyle%> align="left">${row.District}</td>

                            <td  class=<%=classStyle%> align="right">${row.AssessPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.DOBPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.DuplPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.IDPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.NamePendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.NCPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.PhysiPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.RessessPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right">${row.RenewalPendingNewComp}</td>

                            <td  class=<%=classStyle%> align="right" style="border-right:#234466 1px solid;">${row.RelationNamePendingNewComp}</td>


                            <%} else {%>
                            <td  class=<%=classStyle%> align="left">${row.RequestName}</td>

                            <td  class=<%=classStyle%> align="right" style="border-right:#234466 1px solid;">${row.Pending}</td>

                            <%}%>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  class="hd_gd" align="center" colspan="2"><b>Total</b></th>

                        <%if (districtId.equalsIgnoreCase("ALL")) {%>
                        <th class="hd_gd" align="center"><b><%=assessPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=dOBPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=duplPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=iDPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=namePendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=nCPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=physiPendingNewComp%></b></th>

                        <th  class="hd_gd" align="center"><b><%=ressessPendingNewComp%></b></th>

                        <th class="hd_gd"  align="center"><b><%=renewalPendingNewComp%></b></th>

                        <th class="hd_gd"  align="center"><b><%=relationNamePendingNewComp%></b></th>
                        <%} else {%>
                        <td class="hd_gd"  align="center"><b><%=totalPending%></b></td>
                        <%}%>
                    </tr>

                </table>
               
                <%if (districtId.equalsIgnoreCase("ALL")) {%>

                <%}%>
              

            </logic:notEmpty>
        </html:form>
    </body>
</html>
