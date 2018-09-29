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

    
       
       <head><br>
       <script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script> 

    </head>
   
    <body>

    <form>

    <logic:present name="pATBean" scope="request">
      <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">
           <tr>
        <th colspan=4 align="center" class="heading">6.&nbsp;&nbsp;Alexander Pass Along Test (P.A.T) Calculations</th>
         </tr>
         <tr><td colspan="3"  align="right" class="label">ID No:&nbsp;<font color="blue"><%=personcode%></font></td></tr>
         <tr><td colspan="3"  align="right" class="label">Name:&nbsp;<font color="blue"><%=name%></font></td></tr>
       
        
        </table>
      
       <table  align="center" cellspacing="1" cellpadding="8"  class="inputform" width="90%">
           <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_total_flag" value="true">
            <tr> 
                         <td class="label">Design No.</td>
                         <td class="label">Allotted time (Seconds)</td>
                         <td class="label">Actual time taken by testee to solve the problem</td>
                         <td class="label">Score awarded </td>
                         <td class="label">Remarks</td>
          </tr>
           <tr>
              <td colspan="5">&nbsp;</td>
           </tr>
            <logic:equal name="pATBean" property="pat_one_flag" value="true">
          <tr>
           <td class="label">1</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_One" value="0">
             <bean:write name="pATBean" property="pat_Second_One"/>
           </logic:notEqual>
           </td>
           <td class="label">
           <logic:notEqual name="pATBean" property="pat_SA_One" value="0">
             <bean:write name="pATBean" property="pat_SA_One"/>
           </logic:notEqual>
           </td>
           <td class="label">
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
           <td class="label">2</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Two" value="0">
             <bean:write name="pATBean" property="pat_Second_Two"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Two" value="0">
             <bean:write name="pATBean" property="pat_SA_Two"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">3</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Three" value="0">
             <bean:write name="pATBean" property="pat_Second_Three"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Three" value="0">
             <bean:write name="pATBean" property="pat_SA_Three"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">4</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Four" value="0">
             <bean:write name="pATBean" property="pat_Second_Four"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Four" value="0">
             <bean:write name="pATBean" property="pat_SA_Four"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">5</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Five" value="0">
             <bean:write name="pATBean" property="pat_Second_Five"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Five" value="0">
             <bean:write name="pATBean" property="pat_SA_Five"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">6</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Six" value="0">
             <bean:write name="pATBean" property="pat_Second_Six"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Six" value="0">
             <bean:write name="pATBean" property="pat_SA_Six"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">7</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
            <logic:notEqual name="pATBean" property="pat_Second_Seven" value="0">
             <bean:write name="pATBean" property="pat_Second_Seven"/>
           </logic:notEqual>
           </td>
           <td>
            <logic:notEqual name="pATBean" property="pat_SA_Seven" value="0">
             <bean:write name="pATBean" property="pat_SA_Seven"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">8</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Eight" value="0">
             <bean:write name="pATBean" property="pat_Second_Eight"/>
           </logic:notEqual>
           </td>
           <td>
            <logic:notEqual name="pATBean" property="pat_SA_Eight" value="0">
             <bean:write name="pATBean" property="pat_SA_Eight"/>
           </logic:notEqual>
           </td>
           <td>
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
           <td class="label">9</td>
           <td class="label">120(2 min)</td>
           <td class="label">Seconds:
           <logic:notEqual name="pATBean" property="pat_Second_Nine" value="0">
             <bean:write name="pATBean" property="pat_Second_Nine"/>
           </logic:notEqual>
           </td>
           <td>
           <logic:notEqual name="pATBean" property="pat_SA_Nine" value="0">
             <bean:write name="pATBean" property="pat_SA_Nine"/>
           </logic:notEqual>
           </td>
           <td>
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
               <td colspan="3" align="center" class="label">Mental Age</td>
                <td class="label">:</td>
                <td class="label">Years:<bean:write name="pATBean" property="pat_Year"/></td>
          </tr>
          <tr>
               <td colspan="4">&nbsp;</td>
               <td class="label">Months:<bean:write name="pATBean" property="pat_Month"/></td>
               
          </tr>
          <tr>
            <td colspan="3" align="center" class="label">Mental Age Converted in to years</td>
           <td class="label">:</td>
            <td class="label"><bean:write name="pATBean" property="mentalagebuffer"/></td>
            
           </tr>
           <tr>
           <td colspan="3" align="center" class="label">Date Of Birth </td>
           <td class="label">:</td>
           <td class="label"><bean:write name="pATBean" property="dobstring"/></td>
           </tr>
           <tr>
           <td colspan="3" align="center" class="label">Todays Date</td>
           <td>:</td>
           <td><bean:write name="pATBean" property="todaystring"/></td>
           </tr>
           <tr>
           <tr>
            <td colspan="3" align="center" class="label">Chronological Age</td>
           <td class="label">:</td>
            <td class="label">(Today`s Date-Date Of Birth)</td>
           </tr>
            <tr>
            <td colspan="3">&nbsp;</td>
           <td class="label">:</td>
            <td><bean:write name="pATBean" property="chronologicalage"/></td>
           </tr>
            <logic:equal name="pATBean" property="chronologicalageflag" value="true">
           <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
             <td colspan="3" class="label">Since Chronological Age is greater than 16 Chronological Age=16</td>
           </tr>
           </logic:equal>
            <tr>
             <td colspan="3" align="center" class="label">IQ</td>
            <td class="label">:</td>
            <td class="label"><bean:write name="pATBean" property="iqbuffer"/></td>
           </tr>
       </table>
    
    
    </logic:present>

     <BR>
     <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()" styleClass="button" />  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=16&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>
                               
</table>   
        


    </body>
</html>
