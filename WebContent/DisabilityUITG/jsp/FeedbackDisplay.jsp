<%-- 
    Document   : FeedbackDisplay
    Created on : Nov 27, 2010, 10:58:47 AM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <%  List feedbackDetailsList = (ArrayList) request.getAttribute("feedbackDetailsList");
                int length = 0;
                if (feedbackDetailsList != null) {
                    length = feedbackDetailsList.size();
                }
                String msg = (String) request.getAttribute("msg");
    %>

    <head>
        <script language="JavaScript">
            function activeFeedbackId(read)
            {
                document.forms[0].FeedbackIdsStatus.value="ActiveFeedback";
                document.forms[0].submit();
            }
            function inActiveFeedbackId(read)
            {
                document.forms[0].FeedbackIdsStatus.value="Inactive";
                document.forms[0].submit();
            }
            function deleteFeedbackId(read)
            {
                document.forms[0].FeedbackIdsStatus.value="Delete";
                document.forms[0].submit();
            }
            checked=false;
            function checkedAll(){
                if(document.getElementById("length") != null && document.getElementById("length") != ""){
                    var aa= document.getElementById("length").value;
                    if (checked == false)
                    {
                        checked = true
                    }
                    else
                    {
                        checked = false
                    }
                    if(aa != null){
                        for (var i =1; i <= aa; i++)
                        {
                            document.getElementById(i).checked = checked;
                        }
                    }
                }

            }
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="feedbackStatusChange.do?updateFeedbackDetails=updateFeedbackDetails" method="post">
            <table align="center">
                <tr>
                    <%if (msg != null && !"".equals(msg)) {%>
                    <td align="center" class="messagelablel" > <%=msg%> </td>
                    <%}%>
                </tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
                <tr>
                <input type="hidden" name="FeedbackIdsStatus"/><br>
                <input type="hidden" name="listlength" id="length" value="<%=length%>"/>

                <td align="center" class="heading">Feedback Details</td>
            </tr>
        </table>
        <%String message = (String) request.getAttribute("message");
                    if (message != null && !"".equals(message)) {
        %>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="85%">
            <tr>
                <td align="center" class="label"><%=message%></td>
            </tr>
        </table>
        <%} else {%>
        <table  align="center" cellspacing="0" cellpadding="5" class="innerTable1" width="85%" border="1">
            <tr>
                <td class="labelBlue" width="80">select All <input type='checkbox' name='checkall' onclick="checkedAll();"></td>
                <td class="labelBlue">Person Name</td>
                <td class="labelBlue">District</td>
                <td class="labelBlue">Mandal</td>
                <td class="labelBlue">Location</td>
                <td class="labelBlue" width="60">Status</td>
                <td class="labelBlue" width="80">Date</td>
            </tr>
            <%int i = 1;%>
            <logic:iterate id="feedbackdetails" name="feedbackDetailsList" scope="request">
                <tr>
                    <td class="label" width="80">
                        <input type="checkbox" name="rowIds" id="<%=i++%>" value="<bean:write name="feedbackdetails" property="rowId"/>"/>
                    </td>
                    <td class="label"><a href="javascript:void(0);" onclick ="window.open('showFeedbackDetails.do?showFeedbackDetails=showFeedbackDetails&rowId=<bean:write name="feedbackdetails" property="rowId"/>', 'Ratting','resizable=yes, scrollbars=yes,')">
                            <bean:write name="feedbackdetails" property="name"/></a></td>
                    <td class="label">
                        <bean:write name="feedbackdetails" property="district"/></td>
                    <td class="label">
                        <bean:write name="feedbackdetails" property="mandal"/></td>
                    <td class="label">
                        <bean:write name="feedbackdetails" property="location"/></td>
                    <td class="label" width="60">
                        <bean:write name="feedbackdetails" property="status"/></td>
                    <td class="label" width="80">
                        <bean:write name="feedbackdetails" property="createdDate"/></td>
                </tr>
            </logic:iterate>

        </table>


        <table align="center"><tr>
                <td align="center">
                    <html:button property="Active" value="Active" styleClass="button" onclick="activeFeedbackId(this.value);"/>&nbsp;&nbsp;
                </td>
                <td align="center">
                    <html:button property="Inactive" value="Inactive" styleClass="button" onclick="inActiveFeedbackId(this.value);"/>&nbsp;&nbsp;
                </td>
                <td align="center">
                    <html:button property="Delete" value="Delete" styleClass="button" onclick="deleteFeedbackId(this.value);"/>&nbsp;&nbsp;
                </td>
            </tr>
        </table>


        <%}%>

    </html:form>
</body>
</html:html>
