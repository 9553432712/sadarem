<%--
    Document   : campWiseDoctorsCountDetails
    Created on : Dec 19, 2012, 1:25:32 PM
    Author     : 310926
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@page  import="java.util.ArrayList"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <%
                    String campDate = (String) request.getAttribute("date");
                    ArrayList countData = new ArrayList();
                    countData = (ArrayList) request.getAttribute("countData");
                    session.setAttribute("countData", countData);
                    int i = 1;
        %>



        <script type="">




            function getAssessedPwdDoctorsCount(){
                document.forms[0].mode.value ="insertAssessedPwdDoctorsCount";
                document.forms[0].submit();
            }




        </script>

    </head>

    <body>
        <html:form action="/campWiseDoctorsCount">
            <html:hidden property="mode"/>
            <html:hidden property="campDate" value="<%=campDate%>"/>

            <table cellpadding="0" cellspacing="0" align="center">
                <br/>
                <tr align="center">
                    <td>
                        <h4> CampWise Assessed Doctors Details </h4>
                    </td>
                </tr>
            </table>

            <br/>
             <logic:present name="msgData">
                <center><font color="red">${msgData}</font></center>
            </logic:present>


            <logic:present name="campBasedOnDisabilityDetails">
                <table  align="center" cellspacing="0" border="0" cellpadding="0" class="innerTable" width="85%">

                    <tr>
                        <td class="formhdbg" align="center">
                            S.No
                        </td>
                        <td class="formhdbg" align="center">
                            Disabilty Name
                        </td>


                        <td class="formhdbg" align="center">
                            Doctor's Name
                        </td>
                        <td class="formhdbg" align="center">
                            Doctor's RegNO
                        </td>

                        <td class="formhdbg" align="center">
                            Doctor's Count
                        </td>

                    </tr>
                    <logic:iterate name="campBasedOnDisabilityDetails" id="row">

                        <tr>
                            <td class="formaltbg" align="center">
                                <%=i++%>
                            </td>
                            <td class="formaltbg" align="left">
                                ${row.disabilityName}
                                <html:hidden property="disabilityId" value="${row.disabilityId}"/>
                            </td>
                            <td class="formaltbg" align="left">
                                ${row.doctorName}
                            </td>
                            <td class="formaltbg" align="center">
                                ${row.doctorRegNumber}
                            </td>


                            <td class="formaltbg" align="center">
                                ${row.doctorCount}
                                <html:hidden property="assessedDoctorCount" value="dCount${row.sno}"/>
                            </td>
                        </tr>

                    </logic:iterate>
                </table>



                <br/>
                <table  align="center" cellspacing="0" border="0" cellpadding="0" width="90%">
                    <tr>
                        <td class="label" align="center" colspan="4">
                            <html:button property="but" value="Submit" onclick="getAssessedPwdDoctorsCount();"/>
                        </td>
                    </tr>
                </table>

            </logic:present>
        </html:form>
    </body>
</html>
