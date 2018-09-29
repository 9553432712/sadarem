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
            document.forms[0].action="Railwaycertificate.do";
            document.forms[0].submit();
}
        </script>
    </HEAD>
    <body>
        <%  String ex=(String)request.getSession().getAttribute("reailwaycertificate");
        String messag="Please get Doctor opinion to get Railway Concession Certificate";
      String notassess=(String)request.getAttribute("msg");
      String m="Not Assesed";


%>
        <form>
            <br><br><br><br>

<%if(notassess!=null){%>
<center><font color="red"><%=m %></font></center><%}else{%>

            <center><font color="red"><%=messag %></font></center>

         <%}%>   <br><br>


         <%if(notassess!=null&&notassess.equalsIgnoreCase("msg")){%>

           <center> <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></center>

<%}else{%>
  <center> <html:button property="" value="Back" onclick="goBack1()" styleClass="button"/></center>
  <%}%>
     <%   request.getSession().removeAttribute("dn");request.getSession().removeAttribute("rn");
request.getSession().removeAttribute("dr_exists");//session.removeAttribute("Mdspecialistprefix");
//session.removeAttribute("des");

        %>
    </form>
</body>
</html>



