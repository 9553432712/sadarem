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

       <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
<script language="javascript">
 function goBack()
{
            document.forms[0].action="CalculationForwardAction.do";
            document.forms[0].submit();
} function goBack1()
{
            document.forms[0].action="RailwayWithPersoncode1.do";
            document.forms[0].submit();
}
        </script>
    </HEAD>
    <%String ex=(String)request.getSession().getAttribute("failed");

%>
    <body>
        <form>
            <br><br><br><br>

            <center><font color="red"><h3> No Disability found (please update disability details)</h3></font></center>

            <br><br>
<% if(ex!=null){
                          if(ex.equalsIgnoreCase("exits")){
                              request.getSession().removeAttribute("failed");}%>


 <center> <html:button property="" value="Back" onclick="goBack1()" styleClass="button"/></center> 


<% }

       else{
       %>
            <center> <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></center> 
   <%}%> </form>
</body>
</html>
