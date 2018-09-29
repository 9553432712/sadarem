<%--
    Document   : Gos
    Created on : Apr 26, 2013, 4:07:56 PM
    Author     : 484898
--%>
<% int i = 1;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/impLink">
    <html:hidden property="mode"/>
    <logic:notEmpty name="reportList" >
        <table width="90%" align="center" border="0" cellspacing="1" cellpadding="0" class="inputform">
            <tr><th colspan="6">Important Links</th></tr>
            <tr align="center">
                <th >S.No.</th>
                <th  >Website eName</th>
                <th  >Website Link</th>



            </tr>
            <logic:iterate name="reportList" id="row">

                <tr align="left">
                    <td style="text-align: center"  >
                        <%=i++%>.
                    </td>
                    <td style="text-align: left">${row.webSiteName}</td>
                    <td style="text-align: center"><a href="${row.webSiteUrl}" target="_blank">LINK</a></td>


                </tr>
            </logic:iterate>


        </logic:notEmpty>
    </table>
    <logic:present name="msg">
        <tr>
            <td>
                <center><br/><br/><font color="red" size="3"><bean:write name="msg"/></font></center>
            </td>
        </tr>
    </logic:present>
</html:form>

