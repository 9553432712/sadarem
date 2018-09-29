<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" %>
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
<script language="javascript">
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>
<html>
    <head><br>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<logic:present name="MISICBean" scope="request">
    <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%" >
        <tr>
                         <th align="center" class="heading">4.&nbsp;&nbsp;Malins Intelligence Scale for Indian Children (M.I.S.I.C)</th>
        </tr>
        <tr>
            <th align="right" class="label">ID No.&nbsp;<font color="blue"><%=personcode%></font></th>
        </tr>
        <tr>
            <th align="right" class="label">Name.&nbsp;<font color="blue"><%=name%></font></th>
        </tr>
         </table>
        <table  align="center" cellspacing="1" cellpadding="8" border="0" class="inputform" width="90%">
                     <tr> 
                         <td class="labelBlue">Verbal Test</td>
                         <td class="labelBlue">Raw Score</td>
                         <td class="labelBlue">IQ</td>
                         <td class="labelBlue">Performance Test</td>
                         <td class="labelBlue">Raw Score</td>
                         <td class="labelBlue">IQ </td>
                           
                     </tr>
                     <tr>
                         <td class="label">Information</td>
                         <td>
 <logic:notEqual name="MISICBean" property="misicinformationraw"  value="0">          
  <bean:write name="MISICBean"  property="misicinformationraw"/></td>
  </logic:notEqual>
    <td>
     <logic:notEqual name="MISICBean" property="misicinformationtq"  value="0">   
    <bean:write name="MISICBean"  property="misicinformationtq"/></td>
    </logic:notEqual>
    
     <td class="label">Picture Completion</td>
     <td>
<logic:notEqual name="MISICBean" property="misicpcrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicpcrawscore"/></td>
  </logic:notEqual>
  
  
  <td>
     <logic:notEqual name="MISICBean" property="misicpctq"  value="0">   
    <bean:write name="MISICBean"  property="misicpctq"/></td>
    </logic:notEqual>
  
     </tr>
 <tr>
 
   <td class="label">Comprehension</td>
   <td>
   <logic:notEqual name="MISICBean" property="misiccomprehensionrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misiccomprehensionrawscore"/></td>
  </logic:notEqual>
  
                       
  <td>
 
  <logic:notEqual name="MISICBean" property="misiccomprehensiontq"  value="0">          
  <bean:write name="MISICBean"  property="misiccomprehensiontq"/></td>
  </logic:notEqual>
  
   <td class="label">Block Design</td>
                        
  <td>
<logic:notEqual name="MISICBean" property="misicbdrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicbdrawscore"/></td>
  </logic:notEqual>
  
  <td>
<logic:notEqual name="MISICBean" property="misicbdtq"  value="0">          
  <bean:write name="MISICBean"  property="misicbdtq"/></td>
  </logic:notEqual>
  
  
 </tr> 
  <tr>
                         <td class="label">Arithmetic</td>
    
       <td>
   <logic:notEqual name="MISICBean" property="misicarithmeticrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicarithmeticrawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misicarithmetictq"  value="0">          
  <bean:write name="MISICBean"  property="misicarithmetictq"/></td>
  </logic:notEqual>
  
   <td class="label">Object Assembly</td>
   
   
      <td>
   <logic:notEqual name="MISICBean" property="misicoarawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicoarawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misicoatq"  value="0">          
  <bean:write name="MISICBean"  property="misicoatq"/></td>
  </logic:notEqual>                                     
                         
  </tr>
<tr>

           <td class="label">Similarities</td>
                         
           
      <td>
   <logic:notEqual name="MISICBean" property="misicsimilaritiesrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicsimilaritiesrawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misicsimilaritiestq"  value="0">          
  <bean:write name="MISICBean"  property="misicsimilaritiestq"/></td>
  </logic:notEqual>   
                                  
      <td class="label">Coding</td>
       
                      <td>
   <logic:notEqual name="MISICBean" property="misiccodingrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misiccodingrawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misiccodingtq"  value="0">          
  <bean:write name="MISICBean"  property="misiccodingtq"/></td>
  </logic:notEqual>   
</tr>  
                  
 <tr>
                        
         <td class="label">Vocabulary</td>

  <td>
   <logic:notEqual name="MISICBean" property="misicvocabularyrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicvocabularyrawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misicvocabularytq"  value="0">          
  <bean:write name="MISICBean"  property="misicvocabularytq"/></td>
  </logic:notEqual>   

 <td class="label">Mazes</td>
                       
 <td>
   <logic:notEqual name="MISICBean" property="misicmazesrawscore"  value="0">          
  <bean:write name="MISICBean"  property="misicmazesrawscore"/></td>
  </logic:notEqual>
                                                              
  
       <td>
   <logic:notEqual name="MISICBean" property="misicmazestq"  value="0">          
  <bean:write name="MISICBean"  property="misicmazestq"/></td>
  </logic:notEqual> 
 </tr>
 
  <tr>
  
      <td class="label"  >Digit span</td>
                
      <td >
   <logic:notEqual name="MISICBean" property="misicdigitspanrawscore"  value="0">
  <bean:write name="MISICBean"  property="misicdigitspanrawscore"/>
  </logic:notEqual></td>


           <td colspan="3">
   <logic:notEqual name="MISICBean" property="misicdigitspantq"  value="0">
  <bean:write name="MISICBean"  property="misicdigitspantq"/>
  </logic:notEqual>   </td>


                       
          
                          </tr>
                          </table><br>
        <table align="center" width="85%">
                 
                
          <logic:notEqual name="MISICBean" property="avergeverbalquotient" value="0.0">
    
   
       <tr>
       <td colspan="6" class="label">avergeverbalquotient=(informationtq+comprehensiontq+arithmetictq+similartiestq+vocabularytq+digitspantq)/5 </td>
       </tr>
       <tr>
       <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="misicinformationtq"/>   + <bean:write name="MISICBean"  property="misiccomprehensiontq"/>
                                          + <bean:write name="MISICBean"  property="misicarithmetictq"/>+ <bean:write name="MISICBean"  property="misicsimilaritiestq"/>
                                          + <bean:write name="MISICBean"  property="misicvocabularytq"/> +  <bean:write name="MISICBean"  property="misicdigitspantq"/>)/5</td>
       </tr>
    
       <tr>
        <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="avergeverbalquotient"/></td>
       </tr>
      
 
       
   
          
     </logic:notEqual>        
             
       
    
             <logic:notEqual name="MISICBean" property="avergeperformancequotient" value="0.0">
                 
              <tr>
               <td colspan="6" class="label"> avergeperformancequotient=(picturecompletitiontq+blockdesigntq+objectassemblytq+codingtq+mazestq)/5 </td>
              </tr>
              <tr>
              <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="misicpctq"/>   + <bean:write name="MISICBean"  property="misicbdtq"/>
                                          + <bean:write name="MISICBean"  property="misicoatq"/>+ <bean:write name="MISICBean"  property="misiccodingtq"/>
                                          + <bean:write name="MISICBean"  property="misicmazestq"/>)/5</td>
              </tr>
                 
              <tr>
               <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="avergeperformancequotient"/></td>
              </tr>                          
               
     
 </logic:notEqual>
                 
       <logic:notEqual name="MISICBean" property="misiciq" value="0.0">
           
         <tr>
          <td colspan="6" class="label">IQ Range for MISIC=(avergeverbalquotient+avergeperformancequotient)/2</td>
         </tr>
         <tr>
           <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=(<bean:write name="MISICBean"  property="avergeverbalquotient"/> + <bean:write name="MISICBean"  property="avergeperformancequotient"/>)/2</td>
         </tr>
         <tr>
           <td colspan="6" class="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=<bean:write name="MISICBean"  property="misiciq"/></td>
        </tr>
     </table>
</logic:notEqual>
 
 
   
                 
                 
                 
                 
  </logic:present>                       
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>

    <table align="center">
            <tr>
                <html:button property="" value="Back" onclick="goBack()" styleClass="button" />  </tr>
           <tr></tr><tr></tr>
          <tr><a href="showCalc.do?typeofDisabilityFlag=14&flagPrint=print" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></tr>
                               
</table>  
 </form>

    </body>
</html>
