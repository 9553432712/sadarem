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
<html:html>

    <body onload="window.print()">
<logic:notEmpty name="hearingImpairmentBean" scope="request">
    <%boolean hearingEqualValue=false;%>
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
   <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="table_content">
             <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
                 <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

                 <tr class="tbl_bg2_content_hdr">
                 <td colspan=5 align="center"><font size="4"><b>10.&nbsp;&nbsp HEARING IMPAIRMENT<b></font></td></td></tr>


   <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
                 <tr class="tbl_bg2_content_hdr">
                 <td colspan=9 align="left"><font size="4"><b>Pure Tone Audiometry</b></font><font size="2">(please assess the frequencies and write the values in appropriate boxes)</font></td></tr>
                 <tr class="tbl_bg2_content" >
                     <td align="center"><b>Frequency(HZ)</b></td>
                     <td align="center"><b>Right Ear</b></td>
                     <td align="center"><b>Left Ear</b></td>

          </tr>
            <logic:notEqual name="hearingImpairmentBean" property="rightear250" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear250" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>250</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear250" value="" name="hearingImpairmentBean"><bean:write property="rightear250" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear250" value="" name="hearingImpairmentBean"><bean:write property="leftear250" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>


       <logic:notEqual name="hearingImpairmentBean" property="rightear500" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear500" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>500</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear500" value="" name="hearingImpairmentBean"><bean:write property="rightear500" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear500" value="" name="hearingImpairmentBean"><bean:write property="leftear500" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>


  <logic:notEqual name="hearingImpairmentBean" property="rightear1000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear1000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>1000</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear1000" value="" name="hearingImpairmentBean"><bean:write property="rightear1000" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear1000" value="" name="hearingImpairmentBean"><bean:write property="leftear1000" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear2000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear2000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>2000</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear2000" value="" name="hearingImpairmentBean"><bean:write property="rightear2000" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear2000" value="" name="hearingImpairmentBean"><bean:write property="leftear2000" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear4000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear4000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>4000</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear4000" value="" name="hearingImpairmentBean"><bean:write property="rightear4000" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear4000" value="" name="hearingImpairmentBean"><bean:write property="leftear4000" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>



                 <logic:notEqual name="hearingImpairmentBean" property="rightear8000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="leftear8000" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>8000</b></td>
                <td align="center"> <logic:notEqual scope="request" property="rightear8000" value="" name="hearingImpairmentBean"><bean:write property="rightear8000" name="hearingImpairmentBean"  /></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="leftear8000" value="" name="hearingImpairmentBean"><bean:write property="leftear8000" name="hearingImpairmentBean"  /></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

      <logic:notEqual name="hearingImpairmentBean" property="righteardblevel" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="lefteardblevel" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>DB Levels</b></td>
                <td align="center"> <logic:notEqual scope="request" property="righteardblevel" value="" name="hearingImpairmentBean">(RightEar 500Freq DB + RightEar 1000Freq DB <br>+RightEar 2000Freq DB)/3 <br> = (<bean:write property="rightear500" name="hearingImpairmentBean"  />+<bean:write property="rightear1000" name="hearingImpairmentBean"  />+<bean:write property="rightear2000" name="hearingImpairmentBean"  />)/3 = <bean:write property="righteardblevel" name="hearingImpairmentBean"  /> DB</td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="lefteardblevel" value="" name="hearingImpairmentBean">(LeftEar 500Freq DB + LeftEar 1000Freq DB <br>+LeftEar 2000Freq DB)/3 <br> = (<bean:write property="leftear500" name="hearingImpairmentBean"  />+<bean:write property="leftear1000" name="hearingImpairmentBean"  />+<bean:write property="leftear2000" name="hearingImpairmentBean"  />)/3 = <bean:write property="lefteardblevel" name="hearingImpairmentBean"  /> DB</td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

     <logic:notEqual name="hearingImpairmentBean" property="monauralvaluebetterear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="monauralvaluepoorerear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>Monaural value Calculations</b></td>
                <td align="center"> <logic:notEqual scope="request" property="monauralvaluebetterear" value="" name="hearingImpairmentBean"><pre style="font-family:verdana"><bean:write property="monauralvaluebetterear" name="hearingImpairmentBean"  /></pre></td></logic:notEqual>
                <td align="center"><logic:notEqual scope="request" property="monauralvaluepoorerear" value="" name="hearingImpairmentBean"><pre style="font-family:verdana"><bean:write property="monauralvaluepoorerear" name="hearingImpairmentBean"  /></pre></td></logic:notEqual>
              </tr>
                <% }hearingEqualValue= false; %>

            <logic:notEqual name="hearingImpairmentBean" property="binauralvaluepoorerear" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>Total Hearing Impairment Disability</b></td>
                <td align="center" colspan="2"> <logic:notEqual scope="request" property="binauralvaluepoorerear" value="" name="hearingImpairmentBean"><pre style="font-family:verdana"><bean:write property="binauralvaluepoorerear" name="hearingImpairmentBean"  /></pre></td></logic:notEqual>

              </tr>
                <% }hearingEqualValue= false; %>

                <logic:notEqual name="hearingImpairmentBean" property="totalpercent" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>
             <tr class="tbl_bg2_content" >
                 <td align="center"><b>Hearing Impairment Disability</b></td>
                <td align="center" colspan="2"> <logic:notEqual scope="request" property="totalpercent" value="" name="hearingImpairmentBean"><b><bean:write property="totalpercent" name="hearingImpairmentBean"  />%</b></td></logic:notEqual>

              </tr>
                <% }hearingEqualValue= false; %>



     <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryrightear_pta" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryleftear_pta" value=""><%  hearingEqualValue = true;%></logic:notEqual>
            <%  if (hearingEqualValue) {  %>

              <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="80%">
                 <tr class="tbl_bg2_content_hdr">
                 <td colspan="2" align="center"><font size="4"><b>Speech Discrimination<b></font></td></td></tr>
            <tr class="tbl_bg2_content">
                     <td align="left"><b>%of speech discrimination in Right Ear</b></td>
                     <td ><logic:notEqual name="hearingImpairmentBean" property="speechaudiometryrightear_pta" value=""><bean:write property="speechaudiometryrightear_pta" name="hearingImpairmentBean" />%</td></logic:notEqual>
                 </tr>
                 <tr class="tbl_bg2_content">
                     <td align="left"><b>%of speech discrimination in Left Ear</b></td>
                     <td >  <logic:notEqual name="hearingImpairmentBean" property="speechaudiometryleftear_pta" value=""><bean:write property="speechaudiometryleftear_pta" name="hearingImpairmentBean" />%</td></logic:notEqual>
                 </tr>

             </table>

         <% }hearingEqualValue= false; %>



   </table>


    </body>
    </logic:notEmpty>
</html:html>
