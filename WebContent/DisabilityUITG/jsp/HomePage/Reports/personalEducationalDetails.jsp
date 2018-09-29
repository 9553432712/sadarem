<%-- 
    Document   : personalEducationalDetails
    Created on : Jun 21, 2011, 12:05:50 PM
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
        <title> PWD's Educational wise Details</title>
        <%
                    int i = 1;
                    String dI = (String) request.getParameter("dID");
                    String mI = (String) request.getParameter("mID");
                    String vI = (String) request.getParameter("vID");
                    String hI = (String) request.getParameter("hID");
                    String fD = (String) request.getParameter("fDate");
                    String tD = (String) request.getParameter("toDate");
                    String c = (String) request.getParameter("c");
                    String s = (String) request.getParameter("s");
                    String subRequired = (String) request.getParameter("B");
                    String D = (String) request.getParameter("D");
                    String education = (String) request.getParameter("classStatus");
                    String edu = (String) request.getParameter("education");
                    String fromdate = (String) request.getParameter("fDate");
                    String todate = (String) request.getParameter("toDate");
                    String district_id = (String) request.getParameter("dID");
                    String mandal_id = (String) request.getParameter("mID");
                    String village_id = (String) request.getParameter("vID");
                    String habitation_id = (String) request.getParameter("hID");
                    String categeoty = (String) request.getParameter("cName");
                    String urbanId = (String) request.getParameter("urbanId");

                    String districtName = "ALL";
                    String mandalName = "ALL";
                    String villageName = "ALL";
                    String habName = "ALL";

                    ArrayList getAddressList = new ArrayList();
                    if (request.getAttribute("areaDetails") != null) {
                        getAddressList = (ArrayList) request.getAttribute("areaDetails");
                    }
                    //  for(int j = 0;j<=getAddressList.size();j++) {
                    if (getAddressList.size() > 0) {
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
                    }
                    //habName = (String)getAddressList.get(3);
//}
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
        <body onload="OnBodyLoad(1,3);">
            <p>Educational Report(<%=education%>)</p>
       
            <table  align="center" cellspacing="1" border="0" cellpadding="4"  width="90%">

                <tr>
                    <th colspan="11" class="hd_gd"> As On <%=fromdate%> To <%=todate%>

                        District :
                        <% if (district_id.equals("")) {%>
                        <font >ALL</font>
                        <% } else {%>
                        <font ><%=districtName%></font>
                        <%}%>
                        ,
                        Mandal :
                        <% if (mandal_id.equals("")) {%>
                        <font >ALL</font>
                        <% } else {%>
                        <font ><%=mandalName%></font>
                        <%}%>
                        ,
                        Village :
                        <% if (village_id.equals("")) {%>
                        <font >ALL</font>
                        <% } else {%>
                        <font ><%=villageName%></font>
                        <%}%>
                        ,
                        Habitation :
                        <% if (habitation_id.equals("")) {%>
                        <font >ALL</font>
                        <% } else {%>
                        <font ><%=habName%></font>
                        <%}%>
                        .
                    </th>
                </tr>
                 <logic:present name="msg">
                 <tr>
                     <td style="text-align: center">
                       
                            <font color="red" size="3">${msg}</font>
                       
                    </td>
                </tr>
                 </logic:present>
               
               <tr>
                <td>
                 <table width="90%">
                   <tr>
                    <td align="right" width="89%">
                       <a href="educationWiseReports.xls?status=getEduDetails&hID=<%=habitation_id%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&education=<%=edu%>&id=1&fdate=<%=fD%>&todate=<%=tD%>&urbanId=<%=urbanId%>" target="_blank">
                        <img height="30" width="30" src="<%=request.getContextPath()%>/images/excel.jpg" border="0" align="right" title="Export Excel"/>
                      </a>
                    </td>
                   </tr>
                 </table>
                </td>
               </tr>
                <logic:notEmpty name="educationwiseList">
                <tr> 
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="overflow: none;" >
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
                        <th align="center"  class="hd_gd">S.No</th>
                        <th align="center"  class="hd_gd">SADAREM ID</th>
                        <th align="center"  class="hd_gd">Name</th>

                        <th align="center"  class="hd_gd">Age</th>
                        <th align="center"  class="hd_gd">Gender</th>
                        <th align="center"  class="hd_gd">Caste</th>

                        <th align="center"  class="hd_gd">Education</th>
                        <th align="center"  class="hd_gd">Marriage Status</th> 
                        <th align="center"  class="hd_gd">Contact<br>Number</th>
                        <th align="center"  class="hd_gd">Relation Name</th>
                        <th align="center"  class="hd_gd">Address</th>
                    </tr>
                   <tr class="gridHdrStyle" height="50">
                      <th align="center"  class="hd_gd">1</th>
                      <th align="center"  class="hd_gd">2</th>
                      <th align="center"  class="hd_gd">3</th>
                      <th align="center"  class="hd_gd">4</th>
                      <th align="center"  class="hd_gd">5</th>
                      <th align="center"  class="hd_gd">6</th>
                      <th align="center"  class="hd_gd">7</th>
                      <th align="center"  class="hd_gd">8</th>
                      <th align="center"  class="hd_gd">9</th>
                      <th align="center"  class="hd_gd">10</th>
                      <th align="center"  class="hd_gd">11</th>
                   </tr>
                    <%String classStyle=""; %>
                    <logic:iterate name="educationwiseList" id="row" indexId="count">
                          <% if(count.intValue()%2==0)
              		 {
                    	  classStyle="secondrow";
                	 }
                      else
                      { 
                    	  classStyle="firstrow";
                    	  
                      } %>
                        <tr>
                            <td  align="center" class="<%=classStyle%>">
                                <%=i++%>.
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.person_code}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.surname}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.age_years}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.gender}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.cast}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.education}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.marital_status}
                            </td>
                              <td  class="<%=classStyle%>">
                                ${row.contact}
                            </td>
                            <td class="<%=classStyle%>">
                                ${row.relation}
                            </td>
                            <td  class="<%=classStyle%>">
                                ${row.address}
                            </td>
                        </tr>
                    </logic:iterate>
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
         </table>
          </tr>
        </table>
        </logic:notEmpty>

        </body>
    </center>
</html:html>
