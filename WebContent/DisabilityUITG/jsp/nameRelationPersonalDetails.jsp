<%--
    Document   : RationCardServiceReport
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>

<%

            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
   

    <script>

        function submitValues(){

            document.forms[0].mode.value ="getRequestStatusDetails";
            document.forms[0].submit();

        }

    </script>

    <body bgcolor="#f2f0e6">
        <table cellpadding="0" cellspacing="0" width="1000px" border="0" align="center" style="border:0px #000 solid">
            
            <tr>
                <td>
                    <table cellpadding="0" cellspacing="0" align="center">
                        <tr align="center">
                            <td>
                                Individual Person Details
                            </td>
                        </tr>
                    </table>

                    <logic:present name="msg">
                        <center><font color="red">${msg}</font></center>
                    </logic:present>
                    <br/>
                    <logic:notEmpty name="individualRelationList">
                        <table cellpadding="0" cellspacing="0" align="center" width="90%" style="border:1px #000 solid">
                            <tr>
                                <td class="formhdbg">
                                    SNo.
                                </td>
                                <td class="formhdbg">
                                    SADAREM ID
                                </td>
                                <td class="formhdbg">
                                    RequestType Name
                                </td>
                                <td class="formhdbg">
                                    View
                                </td>
                            </tr>
                            <logic:iterate name="individualRelationList" id="row">
                                <tr>
                                    <td class="formaltbg">
                                        <%=i++%>.
                                    </td>
                                    <td class="formaltbg">
                                        ${row.IndividualPersonCode}
                                    </td>
                                    <td class="formaltbg">
                                        ${row.requesttypeName}

                                        <html:hidden property="requesttypeId" value="${requesttypeId}"/>
                                        <html:hidden property="requestIdData" value="${requestIdData}"/>
                                    </td>

                                    <td class="formaltbg">
                                        <a href="nameRelationChange.do?mode=getParticularOldDetails&personCodeId=${row.IndividualPersonCode}&requesttypeId=${row.requesttypeId}&requestIdData=${row.requestIdData}">View</a>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </logic:notEmpty>
                    </table>
                </td>
            </tr>
            <tr><td height="270px">&nbsp;</td></tr>
           <!-- <tr>
                <td><div align="center">
                        <font face="Arial" size="1" color="#143A27"> Copyrights Reserved by Govt of Telangana &#169; 2010 </font> <br />
                        <strong><font face="Arial" size="1" color="#143A27">SADAREM</font>
                        </strong> <font face="Arial" size="1" color="#143A27">is hosted & maintained by </font>
                        <a href="http://www.aponline.gov.in" target="_blank">
                            <font face="Arial" size="1"> APOnline </font> </a> <br />
                        <font face="Arial" size="1" color="#143A27"> Site Best viewed in 1024*768 Resolution  </font><br>
                        <font face="Arial" size="1" color="#143A27">Best Use in Internet Explorer <font size="2" color="blue">6</font></font>
                    </div></td>
            </tr> -->
        </table>
    </body>
</html>
