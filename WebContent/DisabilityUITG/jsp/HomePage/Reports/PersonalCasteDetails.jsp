<%--
    Document   : PersonalCasteDetails
    Created on : Jun 22, 2011, 3:55:47 PM
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
                    String habitation_id = (String) request.getParameter("habCode");


                    String caste = (String) request.getParameter("caste");
                    String urbanId = (String) request.getParameter("urbanId");

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
        <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>      
    </head>

    <center>
    <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }</style>
        <body onload="OnBodyLoad(2,3);">

            <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">

                <%if (caste.equals("1")) {%>
                <tr >
                
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report for ( ST's ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>

                <%} else if (caste.equals("2")) {%>
                <tr >
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report ( SC's ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>
                <%} else if (caste.equals("3")) {%>
                <tr>
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report ( BC's ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>
                <%} else if (caste.equals("4")) {%>
                <tr >
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report ( OC's ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>
                <%} else if (caste.equals("5")) {%>
                <tr >
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report ( Minority's ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>
                <%} else if (caste.equals("6")) {%>
                <tr >
                <td class="hd_gd" align="center" colspan="16"><b>Caste Wise Report ( Not Applicable ) As On <%=fromdate%> To <%=todate%></b></td>
                </tr>
                <%}%>

                

            </table>
            <table width="90%">
            <tr>

                <td align="center" colspan="16">
                    <%
                                if (habName != null) {
                                    if (!habName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                    Village: <font color="blue"><%=villageName%></font>
                    ,Habitation <font color="blue"><%=habName%></font>
                    <% }
                                }

                                if (villageName != null && habName == null) {
                                    if (!villageName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,
                    Village: <font color="blue"><%=villageName%></font>

                    <% }
                                }


                                if (mandalName != null && villageName == null) {
                                    if (!mandalName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>, Mandal: <font color="blue"><%=mandalName%></font>,

                    <% }
                                }

                                if (districtName != null && mandalName == null) {
                                    if (!districtName.equals("null")) {%>
                    District: <font color="blue"><%=districtName%></font>

                    <% }
                                }%>
                </td	>
                
				                   
						             <td  align="right" >
									    <a href="casteWiseReports.xls?casteStatus=getcasteDetailsAction&habCode=<%=habitation_id%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&urbanId=<%=urbanId%>&caste=<%=caste%>&fDate=<%=fromdate%>&toDate=<%=todate%>&refID=personalDetailsexcel" target="_blank">
                       <img src="DisabilityUITG/images/excel.jpg"
                                             height="35" width="35" align="right"/></a>
									  </a>
						     	     </td> 
						            </tr>
						       </table> 
            <table  align="center" cellspacing="1" border="0" cellpadding="4">
            
                <tr>
                    <td>
                        <logic:present name="msg">
                            <font color="red" size="3">${msg}</font>
                        </logic:present>
                    </td>
                </tr>
            </table>
            <logic:notEmpty name="casteWiseDetails">
                <table align="center" cellspacing="0" border="0" cellpadding="0"  width="90%"  style="overflow: none;" >
                <tbody>
                 <tr>
                <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center">
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"  id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse"  width="100%">
				   <tbody>
                    <tr class="gridHdrStyle">
                        <th class="hd_gd" align="center" >S.No</th>
                        <th class="hd_gd" align="center" >SADAREM ID</th>
                        <th class="hd_gd" align="center" >Name</th>
                        <th class="hd_gd" align="center" >Gender</th>
                        <th class="hd_gd" align="center" >Age</th>
                        <th class="hd_gd" align="center" >Relation Name</th>
                        <th class="hd_gd" align="center" >Education</th>
                        <th class="hd_gd" align="center" >Caste</th>
                        <th class="hd_gd" align="center" >Address</th>
                        <th class="hd_gd" align="center" >Phone No</th>
                    </tr>
                    <tr class="gridHdrStyle" height="40">
                     <th class="hd_gd" align="center" >1</th>
                      <th class="hd_gd" align="center" >2</th>
                       <th class="hd_gd" align="center" >3</th>
                        <th class="hd_gd" align="center" >4</th>
                         <th class="hd_gd" align="center" >5</th>
                          <th class="hd_gd" align="center" >6</th>
                           <th class="hd_gd" align="center" >7</th>
                            <th class="hd_gd" align="center" >8</th>
                             <th class="hd_gd" align="center" >9</th>
                              <th class="hd_gd" align="center" >10</th>
                    </tr>
                    <%String  classStyle="";%>
                    <logic:iterate name="casteWiseDetails" id="row" indexId="count">
 <%  if(count.intValue()%2==0)
			        			  {
			        			  	classStyle="secondrow";
			        			  }
			        			  else
			        			  {
				        			  	classStyle="firstrow";
			        			  }%> 
                        <tr>
                            <td class="<%=classStyle%>">
                                <%=i++%>.
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.PERSON_CODE}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.PERSONNAME}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.Gender}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.age_years}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.relation_name}
                            </td>

                            <td class="<%=classStyle%>">
                                ${row.EDUCATION}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.CASTE}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.Address}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.phone_no}
                            </td>

                        </tr>

                    </logic:iterate>
                    <% session.setAttribute("casteWiseDetails", (ArrayList) request.getAttribute("casteWiseDetails"));%>
                    
                    
                    </tbody>
	</table>
	</div>
	</td>
	</tr>
	</tbody>
	</table>
    </td>
   </tr>
 </tbody>
                </table><br>
              <%--   <table align="center">

                    <a href="casteWiseReports.xls?casteStatus=getcasteDetailsAction&habCode=<%=habitation_id%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&urbanId=<%=urbanId%>&caste=<%=caste%>&fDate=<%=fromdate%>&toDate=<%=todate%>&refID=personalDetailsexcel" target="_blank">
                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                             height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                </table> --%>
            </logic:notEmpty>
            <logic:empty name="casteWiseDetails">
                <p id="errmsg">Details are Not Available!</p>
            </logic:empty>
        </body>
    </center>
</html:html>
