<%--
    Document   : RaiwayConcessionFailure
    Created on : Sep 20, 2010, 12:44:57 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
    <HEAD>

        
<script language="javascript">
 function goBack1()
{
            document.forms[0].action="RailwayWithPersoncode1.do";
            document.forms[0].submit();
}
 function goBack()
{
            document.forms[0].action="CalculationForwardAction.do";
            document.forms[0].submit();
}
function goBack2()
{
            document.forms[0].action="Railwaycertificate.do";
            document.forms[0].submit();
}
        </script>
    </HEAD>
    <body>
        <%
 String exx=(String)request.getSession().getAttribute("separate");
        String ex=(String)request.getSession().getAttribute("reailwaycertificate");
        String messag="As per Appendix 1/48, Rule 101. Serial No: 29(1):<B> Railway concession certificate should be issued only by Government Doctors</B>."+


"<br>Please get opinion from <B>Government Doctor</B> to get Railway concession certificate";

String s=(String) request.getSession().getAttribute("psychologist");


%>
        <form>
            <br><br><br><br>



            <center><font color="red"><%=messag %></font></center>

            <br><br>
 <% if(ex!=null){
                          if(ex.equalsIgnoreCase("exits")){request.getSession().removeAttribute("reailwaycertificate");;}%>


<center> <html:button property="" value="Back" onclick="goBack1()" styleClass="button"/></center>


<% }else if(exx!=null && exx.equalsIgnoreCase("yes")){%>


    <center> <html:button property="" value="Back" onclick="goBack2()" styleClass="button"/></center>


  <%     }else{
       %>
           <center> <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></center>
        <% }


        request.getSession().removeAttribute("dn");request.getSession().removeAttribute("rn");
request.getSession().removeAttribute("dr_exists");//session.removeAttribute("Mdspecialistprefix");
//session.removeAttribute("des");
request.getSession().removeAttribute("separate");
        %>
    </form>
</body>
</html>
