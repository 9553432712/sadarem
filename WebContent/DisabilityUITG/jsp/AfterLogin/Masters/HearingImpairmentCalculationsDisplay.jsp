<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<html:html>

     <head><br>



<script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>

    <body>
  <form>
<logic:notEmpty name="hearingImpairmentBean" scope="request">
    <%boolean hearingEqualValue=false;%>

 <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">

 <tr>
 <td align="right" class="label">ID No.&nbsp;<font color="blue"><%=personcode%></td>
 </tr>

 <tr>
 <td  align="right" class="label">Name.&nbsp;<font color="blue"><%=name%></td>
 </tr>

 <tr>
  <td  align="center" class="heading">10.&nbsp;&nbsp HEARING IMPAIRMENT</td>
 </tr>
 </table>


 <table  align="center" cellspacing="1" cellpadding="8" border="1" class="innerTable1" width="85%">
 <tr>
  <td colspan="3"class="heading">Pure Tone Audiometry<font size="2">
          (please assess the frequencies and write the values in appropriate boxes)</font>
  </td>
 </tr>
 <tr>
   <td align="center" class="labelBlue">Frequency(HZ)</td>
   <td align="center" class="labelBlue">Right Ear</td>
   <td align="center" class="labelBlue">Left Ear</td>

 </tr>
 <logic:notEqual name="hearingImpairmentBean" property="rightear250" value=""><%  hearingEqualValue = true;%></logic:notEqual>
 <logic:notEqual name="hearingImpairmentBean" property="leftear250" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">250</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear250" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear250" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear250" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear250" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>


       <logic:notEqual name="hearingImpairmentBean" property="rightear500" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear500" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">500</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear500" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear500" name="hearingImpairmentBean"  /></logic:notEqual></td>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear500" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear500" name="hearingImpairmentBean"  /></logic:notEqual></td>
              </tr>
                <% }hearingEqualValue= false; %>


  <logic:notEqual name="hearingImpairmentBean" property="rightear1000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear1000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">1000</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear1000" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear1000" name="hearingImpairmentBean"  /></logic:notEqual></td>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear1000" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear1000" name="hearingImpairmentBean"  /></logic:notEqual></td>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear2000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear2000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">2000</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear2000" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear2000" name="hearingImpairmentBean"  /></logic:notEqual></td>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear2000" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear2000" name="hearingImpairmentBean"  /></logic:notEqual></td>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear4000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear4000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">4000</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear4000" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear4000" name="hearingImpairmentBean"  /></logic:notEqual></td>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear4000" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear4000" name="hearingImpairmentBean"  /></logic:notEqual></td>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear8000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear8000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">8000</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="rightear8000" value="" name="hearingImpairmentBean">
                        <bean:write property="rightear8000" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center" class="label"><logic:notEqual scope="request" property="leftear8000" value="" name="hearingImpairmentBean">
                        <bean:write property="leftear8000" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

      <logic:notEqual name="hearingImpairmentBean" property="righteardblevel" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="lefteardblevel" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">DB Levels</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="righteardblevel" value="" name="hearingImpairmentBean">
                        (RightEar 500Freq DB + RightEar 1000Freq DB <br>+RightEar 2000Freq DB)/3 <br>
                        = (<bean:write property="rightear500" name="hearingImpairmentBean"  />
                        +<bean:write property="rightear1000" name="hearingImpairmentBean"  />
                        +<bean:write property="rightear2000" name="hearingImpairmentBean"  />
                        )/3 = <bean:write property="righteardblevel" name="hearingImpairmentBean"  /> DB</td></logic:notEqual>
                <td align="center" class="label"><logic:notEqual scope="request" property="lefteardblevel" value="" name="hearingImpairmentBean">
                        (LeftEar 500Freq DB + LeftEar 1000Freq DB <br>+LeftEar 2000Freq DB)/3 <br>
                        = (<bean:write property="leftear500" name="hearingImpairmentBean"  />
                        +<bean:write property="leftear1000" name="hearingImpairmentBean"  />
                        +<bean:write property="leftear2000" name="hearingImpairmentBean"  />
                        )/3 = <bean:write property="lefteardblevel" name="hearingImpairmentBean"  /> DB</td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

     <logic:notEqual name="hearingImpairmentBean" property="monauralvaluebetterear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="monauralvaluepoorerear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">Monaural value Calculations</td>
                <td align="center" class="label"> <logic:notEqual scope="request" property="monauralvaluebetterear" value="" name="hearingImpairmentBean">
                        <pre style="font-family:verdana"><bean:write property="monauralvaluebetterear" name="hearingImpairmentBean"  />
                        </pre></td></logic:notEqual>
                <td align="center" class="label"><logic:notEqual scope="request" property="monauralvaluepoorerear" value="" name="hearingImpairmentBean">
                        <pre style="font-family:verdana"><bean:write property="monauralvaluepoorerear" name="hearingImpairmentBean"  />
                        </pre></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

            <logic:notEqual name="hearingImpairmentBean" property="binauralvaluepoorerear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">Total Hearing Impairment Disability</td>
                <td align="center" colspan="2" class="label"> <logic:notEqual scope="request" property="binauralvaluepoorerear" value=""
                    name="hearingImpairmentBean"><pre style="font-family:verdana"><bean:write property="binauralvaluepoorerear"
                    name="hearingImpairmentBean"  /></pre></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

                <logic:notEqual name="hearingImpairmentBean" property="totalpercent" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr>
                 <td align="center" class="label">Hearing Impairment Disability</td>
                <td align="center" colspan="2" class="label"> <logic:notEqual scope="request" property="totalpercent" value=""
                                name="hearingImpairmentBean"><bean:write property="totalpercent" name="hearingImpairmentBean"  />
                            %</td></logic:notEqual>

              </tr>
                <% }hearingEqualValue= false; %>

 </table>

     <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryrightear_pta" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryleftear_pta" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>

              <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="85%">
                 <tr>
                     <td colspan="2" align="center" class="heading">Speech Discrimination</td>
                 </tr>
              </table>
            <table  align="center" cellspacing="1" cellpadding="8"  class="innerTable1" width="85%">
            <tr>
                     <td class="label">%of speech discrimination in Right Ear</td>
                     <td class="label"><logic:notEqual name="hearingImpairmentBean" property="speechaudiometryrightear_pta" value="">
                             <bean:write property="speechaudiometryrightear_pta" name="hearingImpairmentBean" />%</td></logic:notEqual>
                 </tr>
                 <tr>
                     <td class="label">%of speech discrimination in Left Ear</td>
                     <td class="label">  <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryleftear_pta" value="">
                             <bean:write property="speechaudiometryleftear_pta" name="hearingImpairmentBean" />%</td></logic:notEqual>
                 </tr>

             </table>

         <% }hearingEqualValue= false; %>



       <BR><BR><BR>
     <table align="center">
            <tr>
           <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=19&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>

</table>
</form>
    </body>
    </logic:notEmpty>
</html:html>
