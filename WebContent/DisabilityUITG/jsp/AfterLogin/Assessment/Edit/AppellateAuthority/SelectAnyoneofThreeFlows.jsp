<%-- 
    Document   : SelectAnyoneofThreeFlows
    Created on : Jun 11, 2012, 12:59:14 PM
    Author     : 490058
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


<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>:: SADAREM ::</title>
 <script type="text/javascript">
     
	function checkBrowser()
	{
		try
   	 	{
   		    // Internet Explorer 6-11
   			var isIE = /*@cc_on!@*/false || !!document.documentMode;
   		    // Edge 20+
   			var isEdge = !isIE && !!window.StyleMedia;
   		    if(!isIE)
   		    {
   		    	alert("Please fill Part-B in Internet Explorer");
   		    	return false;
   		    }
   	 	}
   	 	catch(e)
   	 	{
   		 	alert(e);
   	 	}
   	 	return isIE;
	}
     function selectflow(val)
     { 
     if(checkBrowser())
    	{ 
    		 if(val!=null && val==1){
                 //alert("value========="+val);

                 document.forms[0].action="PersonDetailsUpdateForwardAction.do?pensionNumberFlow=pensionNumberFlow";
                 document.forms[0].submit();

             }else if(val!=null && val==2){
               //  alert("value========="+val);
                 document.getElementById("selectFlow").value='appellate';
                 document.forms[0].action="appletAuthority.do?selectFlow=appellate";
                 document.forms[0].submit();

             }else if(val!=null && val==3){
                // alert("value========="+val);
                 document.getElementById("selectFlow").value='temporay';
                 document.forms[0].action="appletAuthority.do?selectFlow=temporay";
                 document.forms[0].submit();

             }else if(val!=null && val==4){
                // alert("value========="+val);
                // document.getElementById("selectFlow").value='rati';
                 document.forms[0].action="RationcardDetails.do";
                 document.forms[0].submit();

             } 
    	  }
    	 else
    	 {
    		 return false;
    	 } 
//
     }
 </script>
</head>
<body>
 
     
        <logic:present name="msg">
             <%--   <tr>
                    <td  align="center" colspan="2">
                        <font color="red" size="2"><b><></b></font>
                    </td>
                </tr>--%>
             <p id="errmsg"><bean:write name="msg"/></p>

            </logic:present>

        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%">

          
<html:form action="SelectAnyoneofThreeFlow.do"  method="post" styleId="partAForm" onsubmit="return validate_form(this)"  >

<%-- <html:hidden property="selectFlow" /> --%>
<jsp:element name="input">
    <jsp:attribute name="type">hidden</jsp:attribute>
    <jsp:attribute name="id">selectFlow</jsp:attribute>
    <jsp:attribute name="name">selectFlow</jsp:attribute>
   
</jsp:element>

     <div name="msg" id="msg"></div>
    <th colspan="4">Select SADAREM ID/Second Time Re-Assessment/Temporary Certificate</th>
    <tr>
        <td style="text-align: center"><%--<input type="radio" style="width:30px" name="ration" id="4" value="4" onclick="return selectflow(this.value)"/>Non-Pensioners&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        --%><input type="radio"  style="width:30px" name="normal" id="1" value="1" onclick="return selectflow(this.value)"/>SADAREM ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="radio" style="width:30px" name="normal" id="2" value="2" onclick="return selectflow(this.value)"/>Second Time Re-Assessment &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="radio" style="width:30px" name="normal" id="3" value="3" onclick="return selectflow(this.value)"/>Temporary Certificate</td>

    </tr>
    </table>
<table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="90%" border="0" >
    <tr> <th colspan="4" align="left" >Note</th></tr>

    <tr><td colspan="4">
     <li>&nbsp;&nbsp;If Person has SadaremID and to update Please Select SADAREM ID Radio Button
  <br/>  <br/>
     <li>&nbsp;&nbsp;For Second Time Re-Assessment, Please select Second Time Re-Assessment Radio Button
    <br/>  <br/>

   <li>&nbsp;&nbsp;For Temporary Certificate, Please select Temporary Certificate Radio Button
   <%--
      <br/>  <br/>
    <li>&nbsp;&nbsp;For Non-pensioner , Please select Non-pensioner Radio Button
        --%></td>
    </tr>
    </table>

        </html:form>
    </body>
</html>