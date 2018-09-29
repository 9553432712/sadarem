<%-- 
    Document   : ParentsMarriageIndivdualDetails
    Created on : Jul 12, 2011, 6:54:46 PM
    Author     : 484898
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>

<%
            String fromdate = (String) request.getParameter("fDate");



            String todate = (String) request.getParameter("toDate");

            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habCode = (String) request.getParameter("habCode");

            String mstatus = (String) request.getParameter("pId");



            int i = 1;

            String districtName = "ALL";
            String mandalName = "ALL";
            String villageName = "ALL";
            String habName = "ALL";


            ArrayList getAddressList = new ArrayList();
            getAddressList = (ArrayList) request.getAttribute("areaDetails");
            if (getAddressList != null) {
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
                    habName = (String) getAddressList.get(2);
                }
            }


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
	<meta http-equiv="Content-Type"    content="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>      
     <title>SADAREM</title>
 <link href="<%=request.getContextPath()%>/css/commonstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jsDatePick.jquery.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/GridReportStyles.css"/>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/scripts/GridReportStyles.js"></script>
   <Style>
   .hd_gd
   {
     border : #234466 1px solid;
   }
   .gridStyle
   {
	WIDTH: 100%; BORDER-COLLAPSE: collapse; FONT-FAMILY: verdana
   }
</Style>
    </head>
    <body onload=" OnBodyLoad(2,3);">
        <html:form action="/parentsMarriageIndivdualDetails.do" >
            <input type="hidden" name="mode"/>


             <table  align="center"  border="0" cellpadding="0" width="100%">
                <tr>
                    <th colspan="4" class="hd_gd" align="center" valign="middle">
                    <%if (mstatus.equals("0")) {%>
                 Consanguineous Marriage of Parents Report (No) As On <%=fromdate%> To <%=todate%> 
                <%} else if (mstatus.equals("1")) {%>
                 Consanguineous Marriage of Parents Report (Yes) As On <%=fromdate%> To <%=todate%> 
                <%} else if (mstatus.equals("is null")) {%>
                 consanguineous Marriage of Parents Report (Unknown) As On <%=fromdate%> To <%=todate%> 
                <%}%>
 
                <%

                            if (habName != null) {
                                if (!habName.equals("null")) {%>
                District:  <%=districtName%> , Mandal:  <%=mandalName%> ,
                Village:  <%=villageName%> ,
                Habitation:  <%=habName%> 
                <% }
                            }

                            if (villageName != null && habName == null) {
                                if (!villageName.equals("null")) {%>
                District:  <%=districtName%> , Mandal:  <%=mandalName%> ,
                Village: <%=villageName%> ,
                <% }
                            }
                            if (mandalName != null && villageName == null) {
                                if (!mandalName.equals("null")) {%>
                District:  <%=districtName%> , Mandal:  <%=mandalName%> ,
                <% }
                            }
                            if (districtName != null && mandalName == null) {
                                if (!districtName.equals("null")) {%>
                District:  <%=districtName%> ,
                <% }
                            }%>
           
        </tr>
</table>



        <logic:notEmpty  name="parentsIndivdualDetails">
         <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr><td style="text-align:right" colspan="10">
                    <a href="parentsMarriageIndivdualExcel.xls?&habCode=<%=habCode%>&vID=<%=village_id%>&mID=<%=mandal_id%>&dID=<%=district_id%>&pId=<%=mstatus%>&fDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                        <img src="DisabilityUITG/images/excel.jpg"
                                             height="35" width="25" title="Export Excel"/></a>
                </td></tr>
           </table>     
            <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tbody>
                 <tr>
                  <td id="tdGridContent" height="450" valign="top" width="100%" align="middle">
		        <table width="90%" align="center" >
				   <tbody>
				   <tr>
				   <td style="display:none" id="td_grid"  class="scrollme" valign="top" width="100%" colspan="3"  align="middle">
				   <div>
			       <table border="1" align="center" cellspacing="0" rules="all" class="gridStyle"    id="ctl00_dtg_Grid" style="BORDER-COLLAPSE: collapse; border-left: #234466 solid 1px !important;"  width="100%">
				   <tbody>
                
  				<tr >
                  
                <th class="hd_gd" align="center" valign="middle">S.No</th>
                <th class="hd_gd" align="center" valign="middle">SADAREM ID</th>
                <th class="hd_gd" align="center" valign="middle">Name</th>
                <th class="hd_gd" align="center" valign="middle">Gender</th>
                <th class="hd_gd" align="center" valign="middle">Age</th>
                <th class="hd_gd" align="center" valign="middle">Relation Name</th>
                <th class="hd_gd" align="center" valign="middle">Education</th>
                <th class="hd_gd" align="center" valign="middle">Caste</th>
                <th class="hd_gd" align="center" valign="middle">Address</th>
                <th class="hd_gd" align="center" valign="middle">Phone No</th>

            </tr>
            <tr >
                  
                <th class="hd_gd" align="center" valign="middle">1</th>
                <th class="hd_gd" align="center" valign="middle">2</th>
                <th class="hd_gd" align="center" valign="middle">3</th>
                <th class="hd_gd" align="center" valign="middle">4</th>
                <th class="hd_gd" align="center" valign="middle">5</th>
                <th class="hd_gd" align="center" valign="middle">6</th>
                <th class="hd_gd" align="center" valign="middle">7</th>
                <th class="hd_gd" align="center" valign="middle">8</th>
                <th class="hd_gd" align="center" valign="middle">9</th>
                <th class="hd_gd" align="center" valign="middle">10</th>

            </tr>
             <%String classStyle=""; %>
            <logic:iterate id="row" name="parentsIndivdualDetails"  indexId="count">
                    
                                             <% if(count.intValue()%2==0)
              			        			     {
                			        			  	classStyle="secondrow";
                			        			  }
                			        			  else
                			        			  {
                				        			  	classStyle="firstrow";
                			        			  } %>
                <tr>
                    <td  align="center" style="width: 5%;" class="<%=classStyle%>">
                        <%=i++%>.
                    </td>
                    <td class="<%=classStyle%>" style="width: 10%;">
                        ${row.PERSON_CODE}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.PERSONNAME}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.Gender}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.age_years}
                    </td>
                    <td class="<%=classStyle%>" align="center" style="width: 8%;">
                        ${row.relation_name}
                    </td>

                    <td class="<%=classStyle%>" style="width: 10%;">
                        ${row.EDUCATION}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.CASTE}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.Address}
                    </td>
                    <td class="<%=classStyle%>" style="width: 15%;">
                        ${row.phone_no}
                    </td>

                </tr>
            </logic:iterate>

            <%
                        session.setAttribute("parentsIndivdualDetails", (ArrayList) request.getAttribute("parentsIndivdualDetails"));
                        session.setAttribute("areaDetails", (ArrayList) request.getAttribute("areaDetails"));
            %>
            
                </tbody>

        </table>
        </div>
	</td>
	</tr>
	</tbody>
	</table>
	</td></tr></tbody></table>
        
    </logic:notEmpty>

    <table align="center">
        <logic:empty  name="parentsIndivdualDetails">
            <br>
            <p id="errmsg">Details are Not Available!</p>


        </logic:empty>
    </table>


</html:form>
</body>
</html>
