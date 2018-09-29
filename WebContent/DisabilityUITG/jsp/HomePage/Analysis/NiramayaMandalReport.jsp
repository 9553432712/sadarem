<%-- 
    Document   : NiramayaMandalReport
    Created on : Sep 22, 2010, 9:07:16 AM
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
            String district = (String) request.getAttribute("district");
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
                            <p><font color="red">Note:</font> 1)Click on district to drill down up to Habitation<br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2)Click on counts for Individual details
                            </p>
                            <tr><th  align="center" colspan="5"><font size="2">Details of Persons with Disability Eligible for NIRAMAYA Health Insurance Scheme.<br></font>
                                    Assessment done as per G.O.Ms.No.31, Dated.01-12-2009. & G.O.Ms.No.371, Dated 02-12-2009</th>
                            </tr>
                            
                            <tr>
                                <th align="center" >S.No</th>
                                <th ALIGN="center" >Mandal Name</th>
                                <th align="center" >Mental Retardation</th>
                                <th align="center" >Cerebralpalsy</th>
                                <th align="center" >Multiple Disability</th>
                            </tr>

                            <% int i = 0;%>
                            <logic:iterate id="modify" name="niramayareportlist" scope="request">
                                <% if (i == 0) {%>
                                <b><a href="getDistrictidForNiramaya.do?getDistrictReportNiramaya=getDistrictReportNiramaya"><font class="label">Home(StateWise)</font></a></b><font color="red" > > </font>
                                <font color="blue"><%= district%> </font>District

                                <% }%>
                                <%if (i % 2 == 1) {%>
                                <tr>
                                    <td style="text-align: center"><%=++i%></td>
                                    <td >
                                        <a href="getVillageidForNiramaya.do?getVillageReportNiramaya=getVillageReportNiramaya&districtid=<%= districtid%>&district=<%= district%>&mandalid=<bean:write name="modify" property="mandalid"/>&mandal=<bean:write name="modify" property="mandalname"/>">
                                            <bean:write name="modify" property="mandalname"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=MR&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalMRCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalMRCount"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=OH&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalCEREBRALPALSYCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalCEREBRALPALSYCount"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=MD&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalMULTIPLECount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalMULTIPLECount"/></a></td>
                                </tr>
                                <%} else {%>
                                <tr>
                                    <td   style="text-align: center"><%=++i%></td>
                                    <td >
                                        <a href="getVillageidForNiramaya.do?getVillageReportNiramaya=getVillageReportNiramaya&districtid=<%= districtid%>&district=<%= district%>&mandalid=<bean:write name="modify" property="mandalid"/>&mandal=<bean:write name="modify" property="mandalname"/>">
                                            <bean:write name="modify" property="mandalname"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=MR&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalMRCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalMRCount"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=OH&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalCEREBRALPALSYCount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalCEREBRALPALSYCount"/></a></td>
                                    <td  style="text-align: center"> <a href="javascript:void(0);" onclick ="window.open('personalMandalNiramaya.do?nexxt=start&back=start&getMandalNiramaya=getMandalNiramaya&dID=<%= districtid%>&disability=MD&mID=<bean:write name="modify" property="mandalid"/>&mcount=<bean:write name="modify" property="mandalMULTIPLECount"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                            <bean:write name="modify" property="mandalMULTIPLECount"/></a></td>
                                </tr>
                                <%}%>
                            </logic:iterate>
                            <tr>
                                <td  colspan="2"  style="text-align: center"><b> Total</b></td>
                                <td   style="text-align: center"><b> <%=mrTotal%> </b></td>
                                <td   style="text-align: center"><b> <%=cpTotal%> </b></td>
                                <td   style="text-align: center"><b> <%=multipleTotal%> </b></td>
                            </tr>
                        </table>
                        <table>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="70"><img src="./DisabilityUITG/images/download-ico.jpg" width="106" height="39"></td>
                            </tr>
                            <tr>
                                <td width="23%" align="center" valign="middle">&nbsp;</td>
                                <%    ArrayList mandalList = new ArrayList();
                    mandalList = (ArrayList) request.getAttribute("niramayareportlist");
                    session.setAttribute("mandalList", mandalList);%>
                                <td width="19%" align="center" valign="middle"><img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0"></td>
                                <td width="58%"><a href="niramayaMandalExcel.xls?mrTotal=<%=mrTotal%>&cpTotal=<%=cpTotal%>&multipleTotal=<%=multipleTotal%>"  target="_blank">Excel Format</a></td>
                            </tr>
                        </table>
                    </td>

                </logic:present>
            </tr>
        </table>
    </body>
</html>
