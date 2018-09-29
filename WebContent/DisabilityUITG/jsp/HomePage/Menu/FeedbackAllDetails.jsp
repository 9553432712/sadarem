<%-- 
    Document   : FeedbackAllDetails
    Created on : Dec 2, 2010, 4:20:30 PM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>SADAREM</title>
    <body>

        <table width="99%" border="0" cellspacing="0" cellpadding="15" align="center" >
            <tr>
                <td align="center" valign="top" height="380">
                    <table width="98%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="81%" align="center" valign="top"><table border="0" cellspacing="0" cellpadding="0" width="96%">
                                    <tr>
                                    <p>SADAREM Feedback Details</p>
                        </tr>
                        <tr>
                            <td height="40" align="left" valign="middle" >

                                <table  align="center" cellspacing="0" cellpadding="2"  border="0" width="100%" id="grid">
                                    <tr><td colspan="2" align="left" valign="middle" ><b>Feedback Recieved</b></td>
                                    </tr>
                                    <logic:notEmpty name="feedbackDetailsList" >
                                        <logic:iterate id="feedbackdetails" name="feedbackDetailsList" scope="request">

                                            <tr>
                                                <td align="left" valign="middle" ><b>Comments:</b>
                                                    <bean:write name="feedbackdetails" property="feedback"/></td>
                                            </tr>
                                            <tr>
                                                <td align="left" valign="middle" ><b>by Name:</b>
                                                    <bean:write name="feedbackdetails" property="name"/>,
                                                    <b>Location:</b>
                                                    <bean:write name="feedbackdetails" property="location"/></td>
                                            </tr>
                                        </logic:iterate>

                                    </logic:notEmpty>
                                    <logic:empty name="feedbackDetailsList">
                                        <tr><td align="center" valign="middle" >No Feedback Messages</td>
                                        </tr>
                                    </logic:empty>
                                </table>
                            </td>
                        </tr>
                    </table></td>
                <td width="19%" align="center" valign="middle"><img src="./DisabilityUITG/images/feed-ico.gif"></td>
            </tr>
        </table></td>
</tr>  
</table>

</body>
</html>