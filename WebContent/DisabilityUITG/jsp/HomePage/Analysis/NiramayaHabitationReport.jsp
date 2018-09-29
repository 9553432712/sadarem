<%-- 
    Document   : NiramayaHabitationReport
    Created on : Sep 22, 2010, 11:52:32 AM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="org.bf.disability.form.CommonReportForm" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% 
            String districtid = (String) request.getAttribute("districtid");
            String mandalid = (String) request.getAttribute("mandalId");
            String vID = (String) request.getAttribute("villageid");
            String habitationId = (String) request.getAttribute("habitationId");
            String district = (String) request.getAttribute("district");
            String mandal = (String) request.getAttribute("mandal");
            String village = (String) request.getAttribute("village");
            int mrTotal = (Integer) request.getAttribute("mrTotal");
            int cpTotal = (Integer) request.getAttribute("cpTotal");
            int multipleTotal = (Integer) request.getAttribute("multipleTotal");
%>
<html>
<head>
<title>SADAREM</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
    <table width="99%" border="0" cellspacing="0" cellpadding="9"  align="center">
    <logic:present name="niramayareportlist" scope="request">
  <tr>
    <td width="87%" align="center" valign="top">
        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="100%">
            <p><font color="red">Note:</font> 1)Click on counts for Individual details
            </p>
                <tr><th  align="center" colspan="5"><font size="2">Details of Persons with Disability Eligible for NIRAMAYA Health Insurance Scheme.<br></font>
                Assessment done as per G.O.Ms.No.31, Dated.01-12-2009. & G.O.Ms.No.371, Dated 02-12-2009</th>
                </tr>
                
                <tr>
                    <th align="center" >S.No</th>
                    <th ALIGN="center" >Habitation Name</th>
                    <th align="center" >Mental Retardation</th>
                    <th align="center" >Cerebralpalsy</th>
                    <th align="center" >Multiple Disability</th>
                </tr>

                <% int i = 0;%>
                <logic:iterate id="modify" name="niramayareportlist" scope="request">
                    <% if (i == 0) {%>
                    <b><a href="getDistrictidForNiramaya.do?getDistrictReportNiramaya=getDistrictReportNiramaya">Home(Statewise)</a></b><font color="red" > > </font>
                    <b><a href="getMandalidForNiramaya.do?getMandalReportNiramaya=getMandalReportNiramaya&districtid=<%= districtid %>&district=<%= district %>"><%= district %></a><font class="label">  District</font></b><font color="red" > > </font>
                    <b><a href="getVillageidForNiramaya.do?getVillageReportNiramaya=getVillageReportNiramaya&districtid=<%= districtid %>&district=<%= district %>&mandalid=<%= mandalid %>&mandal=<%= mandal %>"><%= mandal %></a><font class="label">  Mandal</font></b><font color="red" > > </font>
                    <font color="blue"><%= village %></font><font class="label">  Village</font>

                <% }%>
                <%if (i % 2 == 1) {%>
                    <tr>
                        <td  style="text-align: center"  ><%=++i%></td>
                        <td align="center"  >
                        <bean:write name="modify" property="habitationname"/></td>
                        <td style="text-align: center"  > <a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=MR&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationMRCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationMRCount"/></a></td> 
                        <td style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=OH&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationCEREBRALPALSYCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationCEREBRALPALSYCount"/></a></td> 
                        <td style="text-align: center" ><a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=MD&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationMULTIPLECount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationMULTIPLECount"/></a></td>
                    </tr>
                     <%} else {%>
                     <tr>
                        <td  style="text-align: center"   ><%=++i%></td>
                        <td align="center"  >
                        <bean:write name="modify" property="habitationname"/></td>
                        <td style="text-align: center"  > <a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=MR&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationMRCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationMRCount"/></a></td> 
                        <td style="text-align: center"  > <a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=OH&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationCEREBRALPALSYCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationCEREBRALPALSYCount"/></a></td> 
                        <td style="text-align: center" > <a href="javascript:void(0);" onclick ="window.open('personalHabitationNiramaya.do?nexxt=start&back=start&getHabitationNiramaya=getHabitationNiramaya&disability=MD&dID=<%= districtid%>&mID=<%= mandalid%>&vID=<%= vID%>&hID=<bean:write name="modify" property="habitationid"/>&hcount=<bean:write name="modify" property="habitationMULTIPLECount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                <bean:write name="modify" property="habitationMULTIPLECount"/></a></td> 
                    </tr>
               <%}%>
                </logic:iterate>
                    <tr>
                        <td  colspan="2" style="text-align: center"><b> Total</b></td>
                        <td  style="text-align: center"><b> <%=mrTotal%> </b></td>
                        <td  style="text-align: center"><b> <%=cpTotal%> </b></td>
                        <td  style="text-align: center"><b> <%=multipleTotal%> </b></td>
                    </tr>
            </table>
                    <table>
                         <tr>
        <td colspan="3" align="left" valign="middle" height="70"><img src="./DisabilityUITG/images/download-ico.jpg" width="106" height="39"></td>
        </tr>
      <tr>
        <td width="23%" align="center" valign="middle">&nbsp;</td>
        <%    ArrayList habitationList = new ArrayList();
                                habitationList = (ArrayList) request.getAttribute("niramayareportlist");
                                session.setAttribute("habitationList", habitationList);%>
        <td width="19%" align="center" valign="middle"><img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></td>
        <td width="58%"><a href="niramayaHabitationExcel.xls?mrTotal=<%=mrTotal%>&cpTotal=<%=cpTotal%>&multipleTotal=<%=multipleTotal%>"  target="_blank">Excel Format</a></td>
      </tr>
                    </table>
    </td>


        </logic:present>
  </tr>
</table>
</body>
</html>
                   