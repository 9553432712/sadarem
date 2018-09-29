<%-- 
    Document   : certificatewithPhysicalreq
    Created on : Jul 30, 2012, 7:09:43 PM
    Author     : 490058
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@ page import="java.util.*" %>


<%
String personcode=(String)request.getAttribute("personcode");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    
    <body>
        <input type="hidden" name="flag" value="false"/>


        <br><br><br><br>
        <center><font size="5" color="blue"><h2>Successfully Updated the Data</h2></font></center><br>
        <input type="hidden" name="personcode"/>
    
       

        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="40%">
            <tr>
                <td align="center" class="heading">Distribute the Certificate and IdCard<br><font size="2" color="red"> (click on below links) </font></td>
            </tr>
        </table>
        <%if(personcode!=null){%>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="40%">
            <tr><td align="center"><a href="CertificateWithPersoncode.do?print=certificate&flag=true&personcode=<%=personcode%>">
                        <h3>CERTIFICATE</h3></a>

                    
                </td></tr>
        </table><%}%>
    </body>
</html>
