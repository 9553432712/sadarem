<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>



     <body  onload="window.print()">

<form>
     <logic:present name="visualimpairmentbean" scope="request">

<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
   <table  border="0" align="center" cellspacing="1" cellpadding="5"  width="95%">

        <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

       <tr>
       <td colspan=4 align="center"><font size="4"><b> &nbsp;&nbsp; VISUAL IMPAIRMENT<b></font></td></td></tr>
       <tr class="tbl_bg2_content">
                <td align="left"><br><B>Category</B></td>
                <td align="left"><br><b> Better Eye</td>
                <td align="left"><br><b>Worse Eye</td>
                <td align="left"><br><b>Percentage</td>
            </tr>

        <br><br>
  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="20.0">

       <tr class="tbl_bg2_content">
            <td><B>Category 0</B></td>
            <td>6/9-6/18</td>
            <td>6/24-6/36</td>
       <td> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>


  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="40.0">

          <tr class="tbl_bg2_content">
          <td><B>Category I</B></td>
            <td>6/18-6/36</td>
           <td> 6/60-Nil</td>
       <td><bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="75.0">

            <tr class="tbl_bg2_content">
          <td><B>Category II</B></td>
            <td >6/40-4/60 or field of vision 10-20 degree</td>
           <td a> 3/60- Nil</td>
       <td >  <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="101.0">

           <tr class="tbl_bg2_content">
          <td><B> Category III</B></td>
            <td>3/60-1/60 or field of vision 10 degree</td>
           <td> F.C. at 1ft to Nil</td>
        <td> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>

        <br><br><br>
        <p><font size="2" color="red">Since Percentage cannot be above 101,The present value is rounded off to 100.<br>
                    Hence,final percentage is 100.</font></p></td></tr>


  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="100.0">

          <tr class="tbl_bg2_content">
          <td><B> Category IV</B></td>
           <td>F.C.(Finger Count) at 1ft to Nil or field of vision 10 degree</td>
             <td>F.C. at 1ft to Nil</td>
      <td> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>
  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="30.0">


           <tr class="tbl_bg2_content">
          <td><B> One eyed Persons</B></td>
           <td > 6/6</td>
            <td > F.C. at 1ft to Nil <br>or<br> field of vision 10 degree</td>
        <td > <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>
   </logic:present>

  </body>
  </form>
</html:html>
    