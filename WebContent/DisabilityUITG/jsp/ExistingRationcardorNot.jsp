<%-- 
    Document   : ExistingRationcardorNot
    Created on : Nov 28, 2012, 2:13:16 PM
    Author     : 310926
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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

 <script type="text/javascript">


     function selectflow(val){

         if(val!=null && val==4){
             //alert("value========="+val);

             document.forms[0].action="newCertificate.do";
             document.forms[0].submit();

         }else if(val!=null && val==1){
            // alert("value========="+val);
            // document.getElementById("selectFlow").value='rati';
             document.forms[0].action="RationcardDetails.do?PartArestrict=true";
             document.forms[0].submit();

         }
//
     }
 </script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SADAREM</title>
</head>
<body>
      
      <br><br>
      <table align="center">  <logic:present name="msg">
                <tr>
                    <td class="label" align="center" colspan="2">
                        <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr></table>
   </logic:present>
<html:form action="SelectAnyoneofThreeFlow.do"  method="post" styleId="partAForm" onsubmit="return validate_form(this)" >

<br><br>




 <table  align="center" cellspacing="1" cellpadding="8" class="innerTable">

    <tr><td colspan="2" align="center" class="heading">Select Without Rationcard/With Rationcard</td></tr>

    <br>


    <tr>
        <td class="label" width="50%"align="center"><input type="radio" name="ration" id="4" value="4" onclick="return selectflow(this.value)">Without Rationcard</td>
        <td class="label" width="50%"align="center"><input type="radio" name="normal" id="1" value="1" onclick="return selectflow(this.value)">With Rationcard</td>


    </tr>
    <tr>
    <td width="100%" class="label"colspan="4">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  </td>
    </tr>
    <tr><td colspan="2" class="Note">Note</td></tr>
    <tr><td class="labelBlue" colspan="2">
    <li>&nbsp;&nbsp;If Person has Rationcard then Please Select Rationcard Radio Button and enter RationcardNumber.</td>
    </tr>

     <tr>
    <td class="labelBlue" colspan="2">
    <li>&nbsp;&nbsp;For Without Rationcard , Please select Without Rationcard Radio Button.</td>
    </tr>
    </table>

        </html:form>
    </body>
</html>