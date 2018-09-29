
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
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

<html:html>
    <head>


    </head>
    <body onload="window.print()">
<logic:present name="MISICBean" scope="request">
    <table  align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
        <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

                     <tr class="tbl_bg2_content_hdr">
                     <td align="center"><font size="4">4.&nbsp;&nbsp;<b>Malins Intelligence Scale for Indian Children (M.I.S.I.C)<b></font></td></tr>
                 </table>
                 <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
                     <tr class="tbl_bg2_content">
                         <td align="left"><b>Verbal Test</td>
                         <td><b>Raw Score</b></td>
                         <td ><b>TQ</b></td>
                         <td ><b>Performance Test</b></td>
                         <td><b>Raw Score</b></td>
                         <td ><b>TQ</b> </td>

                     </tr>
                     <tr class="tbl_bg2_content">
                         <td><b>Information</b></td>
                         <td>
 <logic:notEqual name="MISICBean" property="misicinformationraw"  value="0">
  <bean:write name="MISICBean"  property="misicinformationraw"/></td>
  </logic:notEqual>
    <td>
     <logic:notEqual name="MISICBean" property="misicinformationtq"  value="0">
    <bean:write name="MISICBean"  property="misicinformationtq"/></td>
    </logic:notEqual>

     <td><b>Picture Completion</b></td>
     <td>
<logic:notEqual name="MISICBean" property="misicpcrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicpcrawscore"/></td>
  </logic:notEqual>


  <td>
     <logic:notEqual name="MISICBean" property="misicpctq"  value="0">
    <bean:write name="MISICBean"  property="misicpctq"/></td>
    </logic:notEqual>

     </tr>
 <tr class="tbl_bg2_content">

   <td><b>Comprehension</b></td>
   <td>
   <logic:notEqual name="MISICBean" property="misiccomprehensionrawscore"  value="0">
  <bean:write name="MISICBean"  property="misiccomprehensionrawscore"/></td>
  </logic:notEqual>


  <td>

  <logic:notEqual name="MISICBean" property="misiccomprehensiontq"  value="0">
  <bean:write name="MISICBean"  property="misiccomprehensiontq"/></td>
  </logic:notEqual>

   <td><b>Block Design</b></td>

  <td>
<logic:notEqual name="MISICBean" property="misicbdrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicbdrawscore"/></td>
  </logic:notEqual>

   <td>
<logic:notEqual name="MISICBean" property="misicbdtq"  value="0">
  <bean:write name="MISICBean"  property="misicbdtq"/></td>
  </logic:notEqual>


 </tr>
  <tr class="tbl_bg2_content">
                         <td><b>Arithmetic</b></td>

       <td>
   <logic:notEqual name="MISICBean" property="misicarithmeticrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicarithmeticrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicarithmetictq"  value="0">
  <bean:write name="MISICBean"  property="misicarithmetictq"/></td>
  </logic:notEqual>

   <td><b>Object Assembly</b></td>


      <td>
   <logic:notEqual name="MISICBean" property="misicoarawscore"  value="0">
  <bean:write name="MISICBean"  property="misicoarawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicoatq"  value="0">
  <bean:write name="MISICBean"  property="misicoatq"/></td>
  </logic:notEqual>

  </tr>
<tr class="tbl_bg2_content">

           <td><b>Similarities</b></td>


      <td>
   <logic:notEqual name="MISICBean" property="misicsimilaritiesrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicsimilaritiesrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicsimilaritiestq"  value="0">
  <bean:write name="MISICBean"  property="misicsimilaritiestq"/></td>
  </logic:notEqual>

      <td><b>Coding</b></td>

                      <td>
   <logic:notEqual name="MISICBean" property="misiccodingrawscore"  value="0">
  <bean:write name="MISICBean"  property="misiccodingrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misiccodingtq"  value="0">
  <bean:write name="MISICBean"  property="misiccodingtq"/></td>
  </logic:notEqual>
</tr>

 <tr class="tbl_bg2_content">

         <td><b>Vocabulary</b></td>

  <td>
   <logic:notEqual name="MISICBean" property="misicvocabularyrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicvocabularyrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicvocabularytq"  value="0">
  <bean:write name="MISICBean"  property="misicvocabularytq"/></td>
  </logic:notEqual>

 <td><b>Mazes</b></td>

 <td>
   <logic:notEqual name="MISICBean" property="misicmazesrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicmazesrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicmazestq"  value="0">
  <bean:write name="MISICBean"  property="misicmazestq"/></td>
  </logic:notEqual>
 </tr>

  <tr class="tbl_bg2_content">

                <td><b>Digit span</b></td>

           <td>
   <logic:notEqual name="MISICBean" property="misicdigitspanrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicdigitspanrawscore"/></td>
  </logic:notEqual>


       <td>
   <logic:notEqual name="MISICBean" property="misicdigitspantq"  value="0">
  <bean:write name="MISICBean"  property="misicdigitspantq"/></td>
  </logic:notEqual>

       <td></td>
                         <td></td>  <td></td>

                          </tr>

                 <br><br><br>
          <logic:notEqual name="MISICBean" property="avergeverbalquotient" value="0.0">

    <tr></tr>   <tr></tr>
       <tr>
       <td colspan="6">avergeverbalquotient=(informationtq+comprehensiontq+arithmetictq+similartiestq+vocabularytq+digitspantq)/5 </td>
       </tr>
       <tr>
       <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="misicinformationtq"/>   + <bean:write name="MISICBean"  property="misiccomprehensiontq"/>
                                          + <bean:write name="MISICBean"  property="misicarithmetictq"/>+ <bean:write name="MISICBean"  property="misicsimilaritiestq"/>
                                          + <bean:write name="MISICBean"  property="misicvocabularytq"/> +  <bean:write name="MISICBean"  property="misicdigitspantq"/>)/5</td>
       </tr>

       <tr>
        <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="avergeverbalquotient"/></td>
       </tr>





     </logic:notEqual>



             <logic:notEqual name="MISICBean" property="avergeperformancequotient" value="0.0">

              <tr>
               <td colspan="6"> avergeperformancequotient=(picturecompletitiontq+blockdesigntq+objectassemblytq+codingtq+mazestq)/5 </td>
              </tr>
              <tr>
              <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="misicpctq"/>   + <bean:write name="MISICBean"  property="misicbdtq"/>
                                          + <bean:write name="MISICBean"  property="misicoatq"/>+ <bean:write name="MISICBean"  property="misiccodingtq"/>
                                          + <bean:write name="MISICBean"  property="misicmazestq"/>)/5</td>
              </tr>

               <tr>
               <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="avergeperformancequotient"/></td>
              </tr>


 </logic:notEqual>

       <logic:notEqual name="MISICBean" property="misiciq" value="0.0">

         <tr>
          <td colspan="6">IQ Range for MISIC=(avergeverbalquotient+avergeperformancequotient)/2</td>
         </tr>
         <tr>
           <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="avergeverbalquotient"/> + <bean:write name="MISICBean"  property="avergeperformancequotient"/>)/2</td>
         </tr>
         <tr>
           <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="misiciq"/></td>
        </tr>
     </table>
</logic:notEqual>







  </logic:present>

   <BR><BR><BR>
    </body>
</html:html>
