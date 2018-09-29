<%-- 
    Document   : alreadyRationDataEnterPage
    Created on : Jul 30, 2012, 12:42:25 PM
    Author     : 490058
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% String rationcard= (String)request.getAttribute("card");
String slno= (String)request.getAttribute("slno");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>SADAREM</title>
    </head>
    <body>
       <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%" >

            <tr>
                <td align="center" class="labelBlue">
                    <logic:notPresent name="aadharNoPersonCode">
                        <font  size="4">This Rationcard Number <font  size="5" color="red"> <%= rationcard %>
                        with Serial Number <%=slno %></font>
                        is already entered please make sure that you make a note of SADAREM ID for this Rationcard number and use update facility:- </font>
                    <br><br><br>
                     </logic:notPresent>

                    <logic:present name="aadharNoPersonCode">
                        <font  size="4">Already Assessment Done For The Given Aadhar Card Number <font  size="5" color="red"> <%= request.getAttribute("aadharNoForDisplay")%> </font>
                            .Please make sure that you make a note of SADAREM ID for this pension number and use update facility:- </font>
                        <font  size="10" color="red"> <%= request.getAttribute("alreadyEnterPersonId")%> </font>
                    </logic:present>
                <td>
            </tr>

            </table>
    </body>
</html>
