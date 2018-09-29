<%-- 
    Document   : printMeesevaCertificate
    Created on : Dec 4, 2014, 2:38:20 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
       String totalDisability = (String)request.getAttribute("totalDisability");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <html:form  action="/meesevaSearch">
            <html:hidden property="mode"/>
            <html:hidden property="SCAUserId"/>
            <html:hidden property="uniqueNo"/>
            <html:hidden property="loginId"/>
            <html:hidden property="channelId"/>
            <html:hidden property="serviceid"/>
            <html:hidden property="scaPassword"/>
            <html:hidden property="applicationNo"/>
            <html:hidden property="meesevaFlag"/>
            <html:hidden property="requestId"/>
            <html:hidden property="encryptedString"/>
            <html:hidden property="checkSum"/>
          
                            <table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" class="inputform" >

                                <th>Print Certificate </th>

                                <tr>
                                    <td style="text-align: center">
                                        
                                        <a href="meesevaSearch.do?mode=getPrintViewCertificate&districtName=${districtName}&mandalName=${mandalName}&flag=1&personCode=${personCode}&habCode=${habCode}&districtId=${districtId}&mandalId=${mandalId}&villageId=${villageId}&name=${name}&uniqueNo=${uniqueNo}&scaUserId=${scaUserId}&userId=${userId}&operatorId=${operatorId}&checkSum=${checkSum}&serviceid=${serviceid}&scaPassword=${scaPassword}&sadaremId=${sadaremId}&meesevaflag=${meesevaflag}&phoneNo=${phoneNo}" target="_blank">Print Certificate</a>&nbsp;&nbsp;&nbsp;&nbsp;

                                        <%if (Float.parseFloat(totalDisability.toString()) >= 40) {%>
                                        <a href="meesevaSearch.do?mode=getPrintViewCertificate&districtName=${districtName}&mandalName=${mandalName}&flag=2&personCode=${personCode}&habCode=${habCode}&districtId=${districtId}&mandalId=${mandalId}&villageId=${villageId}&name=${name}&uniqueNo=${uniqueNo}&scaUserId=${scaUserId}&userId=${userId}&operatorId=${operatorId}&checkSum=${checkSum}&serviceid=${serviceid}&scaPassword=${scaPassword}&sadaremId=${sadaremId}&meesevaflag=${meesevaflag}&phoneNo=${phoneNo}" target="_blank">Print IDCard</a>
                                        <%}%>
                                    </td>
                                </tr>

                            </table>
               
</html:form>
</body>
</html>
