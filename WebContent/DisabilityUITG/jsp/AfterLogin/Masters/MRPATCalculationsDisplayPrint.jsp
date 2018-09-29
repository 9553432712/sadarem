<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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

<html>



    <body onload="window.print()">

    <logic:present name="pATBean" scope="request">
          <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
       <table width="100%">
            <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

         <tr>
        <td colspan=4 align="center">6.&nbsp;&nbsp;<b>Alexander Pass Along Test (P.A.T) Calculations<b></td>
         </tr>
        </table>

       <table width="100%" border="0">
           <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_total_flag" value="true">
            <tr>
                         <td><b>Design No.</td>
                         <td><b>Allotted time (Seconds)</b></td>
                         <td><b>Actual time taken by testee to solve the problem</b></td>
                         <td><b>Score awarded</b> </td>
                         <td><b>Remarks</b></td>
          </tr>
           <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_one_flag" value="true">
          <tr>
           <td><b>1</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_One" value="0">
             <bean:write name="pATBean" property="pat_Second_One"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_One" value="0">
             <bean:write name="pATBean" property="pat_SA_One"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_One" value="0">
             <bean:write name="pATBean" property="pat_Remarks_One"/>
           </logic:notEqual>
           </td>
          </tr>
           </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_two_flag" value="true">
          <tr>
           <td><b>2</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Two" value="0">
             <bean:write name="pATBean" property="pat_Second_Two"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Two" value="0">
             <bean:write name="pATBean" property="pat_SA_Two"/>
           </logic:notEqual>
           </td>
           <td><b>
            <logic:notEqual name="pATBean" property="pat_Remarks_Two" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Two"/>
           </logic:notEqual>
           </td>
          </tr>
          </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_three_flag" value="true">
          <tr>
           <td><b>3</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Three" value="0">
             <bean:write name="pATBean" property="pat_Second_Three"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Three" value="0">
             <bean:write name="pATBean" property="pat_SA_Three"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Three" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Three"/>
           </logic:notEqual>
           </td>
          </tr>
           </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
           <logic:equal name="pATBean" property="pat_four_flag" value="true">
          <tr>
           <td><b>4</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Four" value="0">
             <bean:write name="pATBean" property="pat_Second_Four"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Four" value="0">
             <bean:write name="pATBean" property="pat_SA_Four"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Four" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Four"/>
           </logic:notEqual>
           </td>
          </tr>
            </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_five_flag" value="true">
          <tr>
           <td><b>5</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Five" value="0">
             <bean:write name="pATBean" property="pat_Second_Five"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Five" value="0">
             <bean:write name="pATBean" property="pat_SA_Five"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Five" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Five"/>
           </logic:notEqual>
           </td>
          </tr>
           </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_six_flag" value="true">
          <tr>
           <td><b>6</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Six" value="0">
             <bean:write name="pATBean" property="pat_Second_Six"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Six" value="0">
             <bean:write name="pATBean" property="pat_SA_Six"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Six" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Six"/>
           </logic:notEqual>
           </td>
          </tr>
           </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_seven_flag" value="true">
          <tr>
           <td><b>7</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
            <logic:notEqual name="pATBean" property="pat_Second_Seven" value="0">
             <bean:write name="pATBean" property="pat_Second_Seven"/>
           </logic:notEqual>
           </td>
           <td><b>
            <logic:notEqual name="pATBean" property="pat_SA_Seven" value="0">
             <bean:write name="pATBean" property="pat_SA_Seven"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Seven" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Seven"/>
           </logic:notEqual>
           </td>
          </tr>
          </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
           <logic:equal name="pATBean" property="pat_eight_flag" value="true">
          <tr>
           <td><b>8</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Eight" value="0">
             <bean:write name="pATBean" property="pat_Second_Eight"/>
           </logic:notEqual>
           </td>
           <td><b>
            <logic:notEqual name="pATBean" property="pat_SA_Eight" value="0">
             <bean:write name="pATBean" property="pat_SA_Eight"/>
           </logic:notEqual>
           </td>
           <td><b>
            <logic:notEqual name="pATBean" property="pat_Remarks_Eight" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Eight"/>
           </logic:notEqual>
           </td>
          </tr>
          </logic:equal>
          <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_nine_flag" value="true">
          <tr>
           <td><b>9</b></td>
           <td><b>120(2 min)</td>
           <td><b>Seconds:</b>
           <logic:notEqual name="pATBean" property="pat_Second_Nine" value="0">
             <bean:write name="pATBean" property="pat_Second_Nine"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_SA_Nine" value="0">
             <bean:write name="pATBean" property="pat_SA_Nine"/>
           </logic:notEqual>
           </td>
           <td><b>
           <logic:notEqual name="pATBean" property="pat_Remarks_Nine" value="0">
             <bean:write name="pATBean" property="pat_Remarks_Nine"/>
           </logic:notEqual>
           </td>
          </tr>
            </logic:equal>
           <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
           </logic:equal>

          <tr>
               <td colspan="3" align="center"><b>Mental Age</b></td>
                <td>:</td>
                <td><b>Years:</b><bean:write name="pATBean" property="pat_Year"/></td>
          </tr>
          <tr>
               <td colspan="4">&nbsp;</td>
               <td><b>Months:</b><bean:write name="pATBean" property="pat_Month"/></b></td>

          </tr>
          <tr>
            <td colspan="3" align="center"><b>Mental Age Converted in to years</b></td>
           <td><b>:</b></td>
            <td><b><bean:write name="pATBean" property="mentalagebuffer"/></b></td>

           </tr>
           <tr>
           <td colspan="3" align="center"><b>Date Of Birth </b></td>
           <td><b>:</b></td>
           <td><bean:write name="pATBean" property="dobstring"/></td>
           </tr>
           <tr>
           <td colspan="3" align="center"><b>Todays Date</b></td>
           <td><b>:</b></td>
           <td><bean:write name="pATBean" property="todaystring"/></td>
           </tr>
           <tr>
           <tr>
            <td colspan="3" align="center"><b>Chronological Age</b></td>
           <td><b>:</b></td>
            <td>(Today`s Date-Date Of Birth)</td>
           </tr>
            <tr>
            <td colspan="3">&nbsp;</td>
           <td><b>:</b></td>
            <td><b><bean:write name="pATBean" property="chronologicalage"/></b></td>
           </tr>
            <logic:equal name="pATBean" property="chronologicalageflag" value="true">
           <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
             <td colspan="3" >Since Chronological Age is greater than 16 <b>Chronological Age=16</b></td>
           </tr>
           </logic:equal>
            <tr>
             <td colspan="3" align="center"><b>IQ</b></td>
            <td><b>:</b></td>
            <td><b><bean:write name="pATBean" property="iqbuffer"/></b></td>
           </tr>
       </table>


    </logic:present>

    </body>
</html>
