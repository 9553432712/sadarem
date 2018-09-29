<%-- 
    Document   : AnalysisPersonalReport
    Created on : Dec 28, 2010, 10:42:10 AM
    Author     : 509865
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page session="true"%>

<html:html>
    

    <head>
        <%
                    int prenext = ((Integer) session.getAttribute("start")).intValue();
                    int idvar = ((Integer) session.getAttribute("color")).intValue();
                    String url = (String) request.getAttribute("url");
                    String prenextt = String.valueOf(prenext + 1);
                    String preprev = String.valueOf(prenext - 1);
                    String cName = (String) request.getAttribute("cName");
                    String cValue = (String) request.getAttribute("cValue");
                    String secondColumnValue = (String)request.getAttribute("cValueTwo");
                    String dI = (String) request.getParameter("dID");
                    String mI = (String) request.getParameter("mID");
                    String vI = (String) request.getParameter("vID");
                    String hI = (String) request.getParameter("hID");                 
                    String fD = (String) request.getParameter("fDate");
                    String tD = (String) request.getParameter("toDate");
                    String c = (String) request.getParameter("c");
                    String s = (String) request.getParameter("s");
                    String subRequired = (String)request.getParameter("B");
                    String D = (String)request.getParameter("D");
                    
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
            <div style="
                 width:1000px; height:100%; overflow-x:scroll;
                 overflow-y:scroll;">
                <table  border="0" align="center" cellspacing="1" cellpadding="4" class="inputform" width="100%">
                    <tr>
                        <th class="formhdbg" align="center" colspan="13">Details of Persons with Disability Personal Details.</th>
                    </tr>
                    <tr>
                        <th align="center" class="formhdbg">S.No</th>
                        <th align="center" class="formhdbg">SADAREM Id</th>
                        <th align="center" class="formhdbg">Name</th>
                        <th align="center" class="formhdbg">Relation Name</th>
                        <th align="center" class="formhdbg">Age</th>
                        <th align="center" class="formhdbg">Sex</th>
                        <th align="center" class="formhdbg">Address</th>
                        <th align="center" class="formhdbg">Type of Disability</th>
                        <th align="center" class="formhdbg">Sub type of Disability</th>
                        <th align="center" class="formhdbg">Parts Involved</th>
                        <th align="center" class="formhdbg">% of Disability</th>
                        <th align="center" class="formhdbg">Caste</th>
                        <th align="center" class="formhdbg">Occupation</th>
                    </tr>
                    <% int i = (prenext - 1) * 10 + 1;
                                session.setAttribute("counter", new Integer(i));
                    %>

                    <logic:iterate id="details" name="reportlist"  scope="request">
                        <%if (i % 2 == 1) {%>
                        <tr>
                            <td class="formbg"><%=i++%></td>
                            <td class="formbg"><bean:write name="details" property="personcode"/></td>
                            <td class="formbg"><bean:write name="details" property="name"/></td>
                            <td class="formbg"><bean:write name="details" property="ralationName"/></td>
                            <td class="formbg"><bean:write name="details" property="age"/></td>
                            <td class="formbg"><bean:write name="details" property="gender"/></td>
                            <td class="formbg"><logic:notEmpty name="details" property="houseno">
                                                <bean:write name="details" property="houseno"/>,
                                                </logic:notEmpty>
                                               <bean:write name="details" property="districtName"/>,
                                               <bean:write name="details" property="mandalName"/>,
                                               <bean:write name="details" property="villageName"/>,
                                               <bean:write name="details" property="habitationName"/>
                            </td>
                            <td class="formbg"><bean:write name="details" property="disabilityType"/></td>
                            <td class="formbg"><bean:write name="details" property="subtypeofDisability"/></td>
                            <td class="formbg"><bean:write name="details" property="partsInvolved"/></td>
                            <td class="formbg"><bean:write name="details" property="totalpercent"/></td>
                            <td class="formbg"><bean:write name="details" property="caste"/></td>
                            <td class="formbg"><bean:write name="details" property="occupation"/></td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td class="formaltbg"><%=i++%></td>
                            <td class="formaltbg"><bean:write name="details" property="personcode"/></td>
                            <td class="formaltbg"><bean:write name="details" property="name"/></td>
                             <td class="formbg"><bean:write name="details" property="ralationName"/></td>
                            <td class="formaltbg"><bean:write name="details" property="age"/></td>
                            <td class="formaltbg"><bean:write name="details" property="gender"/></td>
                            <td class="formaltbg"><logic:notEmpty name="details" property="houseno">
                                                  <bean:write name="details" property="houseno"/>,
                                                </logic:notEmpty>
                                               <bean:write name="details" property="districtName"/>,
                                               <bean:write name="details" property="mandalName"/>,
                                               <bean:write name="details" property="villageName"/>,
                                               <bean:write name="details" property="habitationName"/>
                            </td>
                            <td class="formaltbg"><bean:write name="details" property="disabilityType"/></td>
                            <td class="formaltbg"><bean:write name="details" property="subtypeofDisability"/></td>
                            <td class="formaltbg"><bean:write name="details" property="partsInvolved"/></td>
                            <td class="formaltbg"><bean:write name="details" property="totalpercent"/></td>
                            <td class="formaltbg"><bean:write name="details" property="caste"/></td>
                            <td class="formaltbg"><bean:write name="details" property="occupation"/></td>
                        </tr>
                        <%}%>
                    </logic:iterate>

                </table>
                <table align="center">

                    <a href="analysisPersonalReportExcel.xls?fD=<%=fD%>&tD=<%=tD%>&dI=<%=dI%>&mI=<%=mI%>&vI=<%=vI%>&hI=<%=hI%>&R=<%=cValue%>&R1=<%=secondColumnValue%>&c=<%=c%>&s=<%=s%>&b=<%=subRequired%>&D=<%=D%>"  target="_blank">Total
                        <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="analysisPersonalReportExcelPageWise.xls?fD=<%=fD%>&tD=<%=tD%>&dI=<%=dI%>&mI=<%=mI%>&vI=<%=vI%>&hI=<%=hI%>&R=<%=cValue%>&R1=<%=secondColumnValue%>&c=<%=c%>&s=<%=s%>&b=<%=subRequired%>&D=<%=D%>"  target="_blank">Individual Page
                        <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    <br>
                    <logic:notEqual  name="removeback" value="yes" scope="request">
                        <a  href="<%=url + "&back=true&nexxt=false" + "&cName=" + cName + "&cValue=" + cValue%>" style="color:black"><<=Previous</a>
                    </logic:notEqual>
                    <logic:notEqual  name="removeprevious" value="yes" scope="request">
                        <a  href="<%=url + "&nexxt=false&back=false&start=" + preprev + "&cName=" + cName + "&cValue=" + cValue%>" style="color:green">Previous</a>
                    </logic:notEqual>
                    <%
                                ArrayList numbers = (ArrayList) request.getAttribute("numbers");
                                Iterator itr = numbers.iterator();
                                while (itr.hasNext()) {
                                    String start = itr.next().toString();
                    %>

                    <a  href="<%=url + "&nexxt=false&back=false&start=" + start + "&cName=" + cName + "&cValue=" + cValue%>" id="<%=idvar%>" onclick="changecolor('<%=idvar%>')" style="color:blue"><%out.println(start);%></a>
                    <% ++idvar;
                            }%>
                    <logic:equal  name="removenext" value="no" scope="request">
                        <a  href="<%=url + "&nexxt=false&back=false&start=" + prenextt + "&cName=" + cName + "&cValue=" + cValue%>" style="color:green">Next</a>
                    </logic:equal>
                    <logic:notEqual  name="removenexxt" value="yes" scope="request">
                        <a  href="<%=url + "&nexxt=true&back=false" + "&cName=" + cName + "&cValue=" + cValue%>" style="color:black">Next=>></a>
                    </logic:notEqual>
                </table>
            </div>
        </body>
    </center>
</html:html>
