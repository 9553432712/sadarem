<%-- 
    Document   : NiramayaPersonalReport
    Created on : Nov 12, 2010, 3:26:19 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@page session="true"%>

<html:html>
    <head>
        <%
                    int prenext = ((Integer) session.getAttribute("start")).intValue();
                    int idvar = ((Integer) session.getAttribute("color")).intValue();
                    String url = (String) request.getAttribute("url");
                    String prenextt = String.valueOf(prenext + 1);
                    String preprev = String.valueOf(prenext - 1);
                    String disabilityName = (String) request.getAttribute("disabilityName");
                    String districtid = (String) request.getAttribute("districtid");                    
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
    <body  onload="changecolor('<%=prenext%>')">
        <div style="width:900px; height:100%; overflow-x:scroll;
             overflow-y:scroll;"> 
            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="100%">
                <tr><th  colspan="18" ><font size="2">Details of Persons with Disability Eligible for NIRAMAYA Health Insurance Scheme.<br></font>
                Assessment done as per G.O.Ms.No.31, Dated.01-12-2009. & G.O.Ms.No.371, Dated 02-12-2009</th>
                </tr>
                <tr>
                    <th align="center" >S.No</th>
                    <th align="center" >SADAREM ID</th>
                    <th align="center" >Name</th>
                    <th align="center" >Father or Guardian</th>
                    <th align="center" >Date of Birth</th>
                    <th align="center" >Age</th>
                    <th align="center" >Sex</th>
                    <th align="center" >Marital Status</th>
                    <th align="center" >Education</th>
                    <th align="center" >Type of disability</th>
                    <th align="center" >% of Disability</th>
                    <th align="center" >House Number</th>
                    <th align="center" >District</th>
                    <th align="center" >Mandal</th>
                    <th align="center" >Village</th>
                    <th align="center" >Habitation</th>
                    <th align="center" >Pincode</th>
                    <th align="center" >Photo</th>
                </tr>
                <% int i = (prenext - 1) * 10 + 1;
                            session.setAttribute("counter", new Integer(i));
                %>

                <logic:iterate id="details" name="reportlist"  scope="request">
                    <%if (i % 2 == 1) {%>
                    <tr>
                        <td ><%=i++%></td>
                        <td ><bean:write name="details" property="personcode"/></td>
                        <td ><bean:write name="details" property="name"/></td>
                        <td ><bean:write name="details" property="fathername"/></td>
                        <td ><bean:write name="details" property="dateOfBirth"/></td>
                        <td ><bean:write name="details" property="age"/></td>
                        <td ><bean:write name="details" property="gender"/></td>
                        <td ><bean:write name="details" property="maritalStatus"/></td>
                        <td ><bean:write name="details" property="education"/></td>
                        <td ><bean:write name="details" property="typeofdisability"/></td>
                        <td ><bean:write name="details" property="totalpercent"/></td>
                        <td ><bean:write name="details" property="houseno"/></td>
                        <td ><bean:write name="details" property="districtname"/></td>
                        <td ><bean:write name="details" property="mandalname"/></td>
                        <td ><bean:write name="details" property="villagename"/></td>
                        <td ><bean:write name="details" property="habitationname"/></td>
                        <td ><bean:write name="details" property="pincode"/></td>
                        <td ><a href="javascript:void(0);" onclick ="window.open('photoView.do?viewPhoto=viewPhoto&name=<bean:write name="details" property="name"/>&personcode=<bean:write name="details" property="personcode"/>', 'Photo', 'resizable=yes, scrollbars=yes, toolbar=no, location=no, directories=no, status=no, menubar=no, width=500, height=200, top=5, left=5')">
                                View</a></td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td ><%=i++%></td>
                        <td ><bean:write name="details" property="personcode"/></td>
                        <td ><bean:write name="details" property="name"/></td>
                        <td ><bean:write name="details" property="fathername"/></td>
                        <td ><bean:write name="details" property="dateOfBirth"/></td>
                        <td ><bean:write name="details" property="age"/></td>
                        <td ><bean:write name="details" property="gender"/></td>
                        <td ><bean:write name="details" property="maritalStatus"/></td>
                        <td ><bean:write name="details" property="education"/></td>
                        <td ><bean:write name="details" property="typeofdisability"/></td>
                        <td ><bean:write name="details" property="totalpercent"/></td>
                        <td ><bean:write name="details" property="houseno"/></td>
                        <td ><bean:write name="details" property="districtname"/></td>
                        <td ><bean:write name="details" property="mandalname"/></td>
                        <td ><bean:write name="details" property="villagename"/></td>
                        <td ><bean:write name="details" property="habitationname"/></td>
                        <td ><bean:write name="details" property="pincode"/></td>
                        <td ><a href="javascript:void(0);" onclick ="window.open('photoView.do?viewPhoto=viewPhoto&name=<bean:write name="details" property="name"/>&personcode=<bean:write name="details" property="personcode"/>', 'Photo', 'resizable=yes, scrollbars=yes, toolbar=no, location=no, directories=no, status=no, menubar=no, width=500, height=200, top=5, left=5')">
                                View</a></td>
                    </tr>
                    <%}%>
                </logic:iterate>

            </table>
            <table align="center">
                
                <a href="niramayapersonalreport.xls"  target="_blank">Total
                    <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="niramayapersonalreportPageWise.xls"  target="_blank">Individual Page
                    <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                <br>
                <logic:notEqual  name="removeback" value="yes" scope="request">
                    <a  href="<%=url + "&back=true&nexxt=false" + "&disability=" + disabilityName%>" style="color:black"><<=Previous</a>
                </logic:notEqual>
                <logic:notEqual  name="removeprevious" value="yes" scope="request">
                    <a  href="<%=url + "&nexxt=false&back=false&start=" + preprev + "&disability=" + disabilityName%>" style="color:green">Previous</a>
                </logic:notEqual>
                <%
                            ArrayList devi = (ArrayList) request.getAttribute("numbers");
                            Iterator itr = devi.iterator();
                            while (itr.hasNext()) {
                                String prasad = itr.next().toString();
                %>

                <a  href="<%=url + "&nexxt=false&back=false&start=" + prasad + "&disability=" + disabilityName%>" id="<%=idvar%>" onclick="changecolor('<%=idvar%>')" style="color:blue"><%out.println(prasad);%></a>
                <% ++idvar;
                                }%>
                <logic:equal  name="removenext" value="no" scope="request">
                    <a  href="<%=url + "&nexxt=false&back=false&start=" + prenextt + "&disability=" + disabilityName%>" style="color:green">Next</a>
                </logic:equal>
                <logic:notEqual  name="removenexxt" value="yes" scope="request">
                    <a  href="<%=url + "&nexxt=true&back=false" + "&disability=" + disabilityName%>" style="color:black">Next=>></a>
                </logic:notEqual>
            </table>            
        </div>
    </body>
    </center>
</html:html>