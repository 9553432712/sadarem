<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@page import="org.bf.disability.dto.TerritoryDTO;" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
    <body>
        <input type="hidden" name="flag" value="false"/>

        <%
                    TerritoryDTO territoryDTO = (TerritoryDTO) request.getSession().getAttribute("territoryDTOtemp");
                    Double totaldisability = new Double(territoryDTO.getTotaldisability());
                    double totalinInt = totaldisability.intValue();
                    double eligiblePercentage = (Double) request.getAttribute("eligiblePercentage");
        %>
        <br/><br/>
        <p id="succmsg"><font size="4">Successfully Entered the Data</font></p>


        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="40%">
            <tr>
                <td><center>Distribute the Certificate and ID Card</center>

                    <font size="1" color="red"> <center>(click on below links)</center> </font></td>
            </tr>
            <%--  </table>
              <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">--%>
            <tr><td align="center">
                    <%String selectFlow = "OUTERPROCESS";
                                if (request.getAttribute("selectedValue") != null) {
                                    selectFlow = request.getAttribute("selectedValue").toString();
                                } else if (request.getParameter("selectedValue") != null) {
                                    selectFlow = request.getParameter("selectedValue").toString();
                                }


                    %>

                    <a href="CertificateWithPersoncode.do?print=certificate&flag=false&selectFlow=<%=selectFlow%>"  >
                        <h3><center>CERTIFICATE</center></h3></a>
                        <% if (totalinInt >= eligiblePercentage) {%>
                    <a href="CertificateWithPersoncode.do?print=idcard&flag=false" >
                        <h3><center>ID CARD</center></h3></a>
                        <%-- <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                              <h3>Railway Concession</h3></a> --%>
                        <% }%>
                        <%--  this is for only Camp admin because delete tha data entry operator.
                             <a href="showCalculationsAction.do?flag=false&backbuttonflagflow=true" >
                                 <h3>CALCULATION</h3></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
                        <%--   <% String s=(String)request.getSession().getAttribute("raileli");
                         if(s!=null && s.equalsIgnoreCase("raileli")){%>
                              <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                                <h3>Railway Concession</h3></a>
                          <%   }
        %>--%>
                </td></tr>
        </table>
    </body>
</html>