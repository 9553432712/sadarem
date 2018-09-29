<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true" %>
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
        <title>SADAREM</title>
    </head>
    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
    <body>
        <input type="hidden" name="flag" value="false"/>
        <%
                    boolean exi = false;
                    Integer totaldisability = (Integer) request.getAttribute("totalPercentage");
                    double totalDisabilityD = (Double) request.getAttribute("totaldisability");
                    double eligiblePercentage = (Double) request.getAttribute("eligiblePercentage");
                    String disabilityID = (String) request.getAttribute("disabilityID");
                    //  int totalinInt = totaldisability.intValue();
                    // String railway_eli=(String)request.getAttribute("");
                    String raileli = (String) request.getAttribute("rail_eli");
                    if (raileli != null && raileli != "0") {
                        exi = true;
                    }

        %>

        <br><br>
        <p id="succmsg"><font size="5">Successfully Updated the Data</font></p>
        <%--   <b>Total Disability : <%out.println(totalinInt);%>%</b></font></center> --%>

        <%--
        This example uses JSTL, uncomment the taglib directive above.
        To test, display the page like this: index.jsp?sayHello=true&name=Murphy
        --%>
        <%--
        <c:if test="${param.sayHello}">
            <!-- Let's welcome the user ${param.name} -->
            Hello ${param.name}!
        </c:if>
        --%>
        <%String selectFlow = "OUTERPROCESS";
                    if (request.getAttribute("selectedValue") != null) {
                        selectFlow = request.getAttribute("selectedValue").toString();
                    }%>

        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
            <tr>
                <td><center><font size="3">Distribute the Certificate and IdCard</font></center><br><font size="2" color="red"> <center>(click on below links)</center> </font></td>
            </tr>
            <%--  </table>
                <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="80%">--%>
            <tr><td align="center"><a href="CertificateWithPersoncode.do?print=certificate&flag=false">
                        <h3><center>CERTIFICATE</center></h3></a>
                        <% if (totalDisabilityD >= eligiblePercentage) {%>
                    <a href="CertificateWithPersoncode.do?print=idcard&flag=false" >
                        <h3><center>ID CARD</center></h3></a> <% }%>
                        <% if (disabilityID.equals("1") && totaldisability >= 40 && exi) {
                                        request.getSession().setAttribute("raileli", "raileli");

                        %>
                    <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                        <h3><center>Railway Concession</center></h3></a>
                        <% } else if (disabilityID.equals("2") && totaldisability == 100 && exi) {
                             request.getSession().setAttribute("raileli", "raileli");%>
                    <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                        <h3><center>Railway Concession</center></h3></a>
                        <% } else if (disabilityID.equals("3") && totaldisability >= 70 && exi) {
                             request.getSession().setAttribute("raileli", "raileli");%>
                    <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                        <h3><center>Railway Concession</center></h3></a>
                        <% } else if (disabilityID.equals("4") && totaldisability >= 50 && exi) {
                             request.getSession().setAttribute("raileli", "raileli");%>
                    <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                        <h3><center>Railway Concession</center></h3></a>
                        <% } else if (disabilityID.equals("6") && totaldisability >= 40 && exi) {
                             request.getSession().setAttribute("raileli", "raileli");%>
                    <a href="CertificateWithPersoncode.do?print=railwayform&flag=false" >
                        <h3><center>Railway Concession</center></h3></a>                 <% }%>
                        <%if (!exi) {
                                        String s = (String) request.getSession().getAttribute("raileli");
                                        if (s != null && s.equalsIgnoreCase("raileli")) {
                                            request.getSession().removeAttribute("raileli");
                                        }

                                    }

                                    request.getSession().removeAttribute("max");
                        %>
                        <%--  this is for only Camp admin because delete tha data entry operator.
                        <a href="showCalculationsAction.do?flag=false&backbuttonflagflow=true" >
                              <h3>CALCULATION</h3></a> --%>
                </td></tr>
        </table>
    </body>
</html>