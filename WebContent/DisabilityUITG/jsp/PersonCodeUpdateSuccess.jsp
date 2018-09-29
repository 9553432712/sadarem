<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>


<%-- 
    Document   : PersonCodeUpdateSuccess
    Created on : Apr 22, 2010, 6:16:06 PM
    Author     : t_bapinaidu
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%
          String NewPersonCode = (String)request.getAttribute("NewPersonCode");
          String personcode = (String)request.getAttribute("personcode");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <br><br><br><br>
        <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%" >
           <tr>
               <td align="center" class="labelBlue"><font  size="4">Please Make Sure that you make a Note of Newly Generated SADAREM ID:- </font>
                       <br><br><br>
                      <font  size="10" color="red"> <%=NewPersonCode%> </font>
                       <br><br><br>
                       <font  size="4">Your Existing SADAREM ID is :- <font  size="5" color="red"> <%=personcode%> </font>
                   </font>
                   <br><br><br>
                   <font color="red"><b>Note:-</b></font>
                   <font color="blue"><b>If you want update details of this person please use update facility with newly generated SADAREM ID</b></font>
               </td>
            </tr>
        </table>

    </body>
</html>
