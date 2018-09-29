<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html>
    <script language="javascript" >
    function goBack()
{
            document.forms[0].action="showCalculationsAction.do";
            document.forms[0].submit();
}
</script>
    </head>

    <body>
    <form ><br><br>
  <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
     <logic:present name="visualimpairmentbean" scope="request">



   <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
       <tr><td colspan="4"  align="right" class="label">ID No.&nbsp;<font color="blue"><%=personcode%></font></td>
        </tr>
        <tr><td colspan="4"  align="right" class="label">Name.&nbsp;<font color="blue"><%=name%></font></td>
        </tr>


       <tr>
       <td colspan=4 align="center" class="heading"> &nbsp; VISUAL IMPAIRMENT</td>
       </tr>

       <tr>
                <td class="labelBlue"><br>Category</td>
                <td class="labelBlue"><br>Better Eye</td>
                <td class="labelBlue"><br>Worse Eye</td>
                <td class="labelBlue"><br>Percentage</td>
            </tr>

        <br><br>
  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="20.0">

       <tr>
            <td class="label">Category 0</td>
            <td class="label">6/9-6/18</td>
            <td class="label">6/24-6/36</td>
            <td class="label"> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td>
       </tr>
  </logic:equal>


  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="40.0">

          <tr>
          <td class="label">Category I</td>
            <td class="label">6/18-6/36</td>
           <td class="label"> 6/60-Nil</td>
            <td class="label"> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td>
          </tr>
  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="75.0">

            <tr>
          <td class="label">Category II</td>
            <td class="label">6/40-4/60 or field of vision 10-20 degree</td>
           <td class="label"> 3/60- Nil</td>
       <td class="label">  <bean:write name="visualimpairmentbean" property="visualimpairment"/></td></tr>
  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="101.0">

           <tr>
          <td class="label">Category III</td>
            <td class="label">3/60-1/60 or field of vision 10 degree</td>
           <td class="label"> F.C. at 1ft to Nil</td>
        <td class="label"> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td>
           </tr>

        <br><br><br>
        <p><font size="2" color="red">Since Percentage cannot be above 101,The present value is rounded off to 100.<br>
                    Hence,final percentage is 100.</font></p></td></tr>


  </logic:equal>

  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="100.0">

          <tr>
          <td class="label">Category IV</td>
           <td class="label">F.C.(Finger Count) at 1ft to Nil or field of vision 10 degree</td>
             <td class="label">F.C. at 1ft to Nil</td>
      <td class="label"> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td>
       </tr>
  </logic:equal>
  <logic:equal name="visualimpairmentbean"  property="visualimpairment" value="30.0">


           <tr>
          <td class="label">One eyed Persons</td>
           <td class="label"> 6/6</td>
            <td class="label"> F.C. at 1ft to Nil <br>or<br> field of vision 10 degree</td>
        <td class="label"> <bean:write name="visualimpairmentbean" property="visualimpairment"/></td>
           </tr>
  </logic:equal>
   </logic:present>
  </table>
      <BR><BR><BR>
     <table align="center">
            <tr>
           <html:button property="" value="Back" onclick="goBack()" styleClass="button"/>
            </tr>
           <br>
          <tr><td><a href="showCalc.do?typeofDisabilityFlag=10&flagPrint=print" target="_blank">
                      Print&nbsp;<img src="DisabilityUITG/images/print.gif"  height="25" width="35"/></a></td></tr>

</table>
        </form>
        <BR><BR><BR>

  </body>
</html:html>
    