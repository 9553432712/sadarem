<%--
    Document   : ValidPersonDetails
    Created on : Feb 13, 2013, 4:39:27 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>
   <%-- <%
        String person="";
        if(request.getAttribute("status") !=null){
        person=request.getAttribute("status").toString();
        }
    %>--%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <title>SADAREM</title>
        <script>
             function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }

            function personIDDetails(){

               if(document.forms[0].elements['personId'].value==""){
                   alert("Enter Person ID");
                   return false;
               }else{
                document.forms[0].mode.value="personIDDetails";
                document.forms[0].submit();

               }
            }

            function updateDetails(){

               if(document.forms[0].elements['textarea'].value==""){
                   alert("Enter Decription");
                   return false;
               }else{
                document.forms[0].mode.value="updateDetails";
                document.forms[0].submit();
               }
            }

        </script>

    </head>
    <body>
        <html:form action="/validPersonDetails">
            <html:hidden property="mode"/>
            
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>
                
                
            <table cellpadding="0" cellspacing="1" align="center" width="50%" border="0" class="inputform">

                <tr>  <th colspan="2">Diagnosis Updates</th></tr>
                <tr>

                    <td align="center">Person ID <font color="red"><b>* </b></font></td>
                    <td  align="left"><html:text property="personId" maxlength="17" style="width:175px;border:1px #000 solid;"/></td>
                </tr>
                <%  if (request.getAttribute("status") != null && request.getAttribute("status").toString().equalsIgnoreCase("true")) {%>

                <tr align="center">
                    <td>
                        Description <font color="red"><b>* </b></font></td>
                    <td align="left"><html:textarea property="textarea" rows="5" cols="30" onkeydown="textCounter(this,document.forms[0].remLen1,150)" onkeyup="textCounter(this,document.forms[0].remLen1,150)"  style="width:175px;height:75px;border:1px #000 solid;"/>
                        <br><input readonly type="text" name="remLen1" size="3" maxlength="3" value="150" style="width:30px">

                    </td>
                </tr>
                <%--<tr align="left">
                    <td align="center" colspan="2"> <html:button property="sub1" value="Submit" onclick="updateDetails();"/></td>
                </tr>--%>
            </table>

            <table cellpadding="0" cellspacing="1" align="center" width="50%" border="0" class="inputform">

                <tr>
                    <th colspan="4"> <br><html:button property="sub1" value="Submit" onclick="updateDetails();"/></th>
                </tr>
            </table>

            <%} else {%>
            <table cellpadding="0" cellspacing="1" align="center" width="50%" border="0" class="inputform">

                <tr>
                    <th  colspan="4"><br> <html:button property="sub" value="Submit" onclick="personIDDetails();"/></th>

                </tr>
            </table>
            <%}%>

        </html:form>

    </body>
</html>
