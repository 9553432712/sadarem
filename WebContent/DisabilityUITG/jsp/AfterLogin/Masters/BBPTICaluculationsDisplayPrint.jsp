<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
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
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<html:html>

    <body onload="window.print()">


<logic:present name="bBPTIBean"  scope="request">
    <br><br><br> <br>
<table  border="0" align="center" width="60%">
          <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

   <tr><td align="center" colspan="3"><b>7.  Bhatia's Battery of Intelligence Tests </b></td> </tr>
   <tr></tr><tr></tr>  <tr></tr><tr></tr>
   <tr></tr>

      <tr>
         <td><b> I  .</b></td>
         <td> KOHS BLOCK DESIGN TEST </td>
         <td> <bean:write name="bBPTIBean" property="bbpti_KOHS_Block_Design_Test"/></td>
      </tr>

      <tr>
       <td>  <b>II .</b></td><td>PASS ALONG TEST</td>
       <td><bean:write name="bBPTIBean" property="bbpti_Passalong_Test"/></td>
      </tr>

        <tr>
       <td>  <b>III.</b> </td><td>PATTERN DRAWING TEST</td>
       <td><bean:write name="bBPTIBean" property="bbpti_PatternDrawing_Test"/></td>
      </tr>

        <tr>
       <td><b> IV.</b> </td><td>IMMEDIATE MEMORY TEST</td>
       <td><bean:write name="bBPTIBean" property="bbpti_ImmediateMemory_Test"/></td>
      </tr>

        <tr>
       <td> <b>V.</b></td><td> PICTURE CONSTRUCTION TEST</td>
       <td><bean:write name="bBPTIBean" property="bbpti_PictureConstruction_Test"/></td>
      </tr>

      <tr></tr><tr></tr> <tr></tr><tr></tr>
        <tr>
        <td></td>
       <td  align="left"> <b>IQ</b></td>
       <td><bean:write name="bBPTIBean" property="bbpti_IQ"/></td>
      </tr>

      <tr></tr><tr></tr> <tr></tr><tr></tr>
       <tr>
       <td align="left" colspan="2"><b>Bhatias battery of intelligence tests percentage is</b></td>
       <td><b><bean:write name="bBPTIBean" property="bbpti_IQ"/></b></td>
      </tr>

</table>

</logic:present>

</body>
</html:html>
