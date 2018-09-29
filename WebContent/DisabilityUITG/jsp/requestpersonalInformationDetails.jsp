<%-- 
    Document   : requestpersonalInformationDetails
    Created on : Sep 18, 2012, 1:58:41 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%
 int i =1;

    String requestIdData = (String)request.getAttribute("requestIdData");


    String status = (String)session.getAttribute("status");

        String pending = (String)session.getAttribute("pending");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADARM</title>
    </head>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/DisabilityUITG/css/Sadarem-Style.css" type="text/css">
   
    <script>

         function approvalDetails(){
           document.forms[0].mode.value ="updateApprovalDetails";
          document.forms[0].submit();

        }
    </script>
    <body>

        <logic:notEmpty name="particularInformationList">
            <html:hidden property="requestIdData" value="<%=requestIdData%>"/>
            <br/><br/><br/>
            <table cellpadding="0" cellspacing="0" width="50%" class="innerTable" align="center">
                
                <logic:iterate name="particularInformationList" id="details">
                    <tr>
                        <td  class="formhdbg" align="center">
                           SADAREM ID
                        </td>
                        <td class="formaltbg">
                            <bean:write name="details" property="personalPersonCode"/>
                        </td>
                    </tr>
                        <tr>
                         <td  class="formhdbg" align="center">
                           Name
                        </td>
                        <td class="formaltbg">
                           <bean:write name="details" property="personalName"/>
                        </td>
                        </tr>
                        <tr>
                        <td  class="formhdbg" align="center">
                           Relation Name
                        </td>
                        <td class="formaltbg">
                            <bean:write name="details" property="personalRelationName"/>
                        </td>
                        </tr>
                        <tr>
                        <tr>
                            <td  class="formhdbg" align="center">
                                House No
                            </td>
                             <td class="formaltbg">
                                 <bean:write name="details" property="personalHouseNo"/>
                            </td>
                        </tr>

                        <tr>
                            <td  class="formhdbg" align="center">
                                HabitationName
                            </td>
                             <td class="formaltbg">
                                  <bean:write name="details" property="personalHabitationName"/>
                            </td>
                        </tr>
                        <tr>
                            <td  class="formhdbg" align="center">
                               Village Name
                            </td>
                             <td class="formaltbg" >
                                <bean:write name="details" property="personalVillageName"/>
                            </td>
                        </tr>
                        <tr>
                            <td  class="formhdbg" align="center">
                                Mandal Name
                            </td>
                             <td class="formaltbg">
                                 <bean:write name="details" property="personalMandalName"/>
                            </td>
                        </tr>
                        <tr>
                            <td  class="formhdbg" align="center">
                                District Name
                            </td>
                             <td class="formaltbg">
                                 <bean:write name="details" property="personalDistrictName"/>
                            </td>
                        </tr>
                </logic:iterate>

            </table>
        </logic:notEmpty>
                    <br/>
                        
                    <table align="center" cellpadding="0" cellspacing="0">
                         <tr>
                             <td  align="center">
                                Approval
                            </td>
                             <td>
                                 <input type="radio" name="approval" value="approval"/>
                            </td>
                            <td  align="center">
                                Reject
                            </td>
                             <td>
                                 <input type="radio" name="approval" value="approval"/>
                            </td>
                        </tr>

                    </table>
                    <br/><br/>
            <table cellpadding="0" cellspacing="0"  align="center">
                <tr>
                    <td align="center">
                        <html:submit property="but" value="Submit" onclick="approvalDetails();"/>
                    </td>

                </tr>
            </table>
       
    </body>
</html>
