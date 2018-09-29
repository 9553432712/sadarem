<%-- 
    Document   : reportForMandalWiseCountPrint
    Created on : May 17, 2010, 8:22:44 PM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>



<html:html>
    <body  onLoad="window.print()">
    <head>
    </head>
    <body>
        <p>Mandal Wise Count Report</p>
            <table  align="center" border="1" cellspacing="1" cellpadding="8" class="inputform" width="60%">
                    <tr>
                        <th align="center" class="labelBlue">S.No</th>
                        <th ALIGN="center" class="labelBlue">MandalName</th>
                        <th align="center" class="labelBlue">Total Records</th>

                    </tr>
                    <% int i=0;%>
                  <logic:iterate id="disabilityreport" name="arraylist1">

                        <tr>
                            <td  style="text-align: center"  class="label"><%=++i%></td>
                            <td  class="label"><bean:write name="disabilityreport" property="mandal_name"/></td>
                                <td  style="text-align: center"><bean:write name="disabilityreport" property="total"/></td>

                        </tr>
                    </logic:iterate>
                </table><br>
    </body>
    <p>&nbsp;</p>

</html:html>
