<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>

       <body  onload="window.print()"><form>
    <logic:present name="cardioPulmonaryBean" scope="request">
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
  <table border="0" align="center" cellspacing="1" cellpadding="5"  width="100%">
       <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

            <tr class="tbl_bg2_content_hdr">
            <center><font size="3">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7:&nbsp;&nbsp;EVALUATION OF PHYSICAL IMPAIRMENT<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DUE TO CARDIOPULMONARY DISEASES
            </font></b></center></tr><BR><BR><BR>
            <tr class="tbl_bg2_content">
                    <td align="center">
                    <b><U>GROUP</U></b>
                    </td>
                    <td align="right">
                        <b><U>percentage</U></b>
                    </td>
                </tr>

              </table>
   <br><br>
            <table  align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="90%">


              <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="13">

                 <tr class="tbl_bg2_content">
                    <td>       <b>Group 0:</b> A patient with cardiopulmonary disease who is asymptomatic <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
(i.e. has no symptom of breathlessness palpitation,fatigue or chest pain)
                       </td>
        <td><center>   <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td>

                 </tr>

        </logic:equal>
     <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="25">

                      <tr class="tbl_bg2_content">
                    <td> <b>Group-1:</b> A patient with cardiopulmonary
                        disease who become symptomatic <br>during his
                        ordinary physical activity but has mild
                        restriction of his <br>physical activities.
                    </td>
                       <td><center> <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>


         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="38">

                      <tr class="tbl_bg2_content">
                    <td><b>Group-2:</b> A patient with cardiopulmonary
                        disease who becomes symptomatic during his ordinary physical activities
                    </td>
                        <td><center><bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>

         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="63">

                        <tr class="tbl_bg2_content">
                    <td> <b>Group-3:</b> A patient  with cardiopulmonary
                        disease who becomes symptomatic during less than ordinary physical activity.
          </td>
                     <td><center>   <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>


        <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="88">

                         <tr class="tbl_bg2_content">
                    <td> <b>Group-4:</b> A patient with cardiopulmonary
                        disease who is symptomatic <br>even at rest or on
                        mildest exertion so that his ordinary physical<br>
                        activities are severely or completely restricted.
                    </td>
            <td><center> <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>

         <logic:equal name="cardioPulmonaryBean"  property="cardiopulmonary" value="100">


                       <tr class="tbl_bg2_content">
                    <td><b>Group-5:</b> A patient with cardiopulmonary
                        disease who gets intermittent <br> symptoms at rest <br>
                        (i.e:patients with bronchial asthma,
                        paroxysmal nocturnal dyspnoea,etc)

                   </td>
            <td><center>    <bean:write name="cardioPulmonaryBean" property="cardiopulmonary"/></center></td></tr>


        </logic:equal>
     </logic:present></form>

    </body>
</html:html>