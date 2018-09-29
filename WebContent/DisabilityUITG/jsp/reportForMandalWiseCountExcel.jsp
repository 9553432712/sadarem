<%-- 
    Document   : reportForMandalWiseCountExcel
    Created on : May 17, 2010, 7:07:45 PM
    Author     : srinivas_p
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String fromdate=(String)application.getAttribute("FromDate"); %>
<% String todate=(String)application.getAttribute("ToDate"); %>






<html:html>
    <head>
        <title>Mandlawise count reportexcel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        
    </head>
    <body>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="60%">
                <tr><td class="heading" align="center">Mandal Wise Count Report</td></tr>
                </table>
            <table  align="center" border="1" cellspacing="1" cellpadding="8" class="innerTable1" width="60%">
                    <tr>
                        <td align="center" class="labelBlue">S.No</td>
                        <td ALIGN="center" class="labelBlue">MandalName</td>
                        <td align="center" class="labelBlue">Total Records</td>

                    </tr>
                    <% int i=0;%>
                  <logic:iterate id="disabilityreport" name="arraylist1">
                        
                        <tr>
                            <td  align="center"  class="label"><%=++i%></td>
                            <td  class="label"><bean:write name="disabilityreport" property="mandal_name"/></td>
                                <td  class="label"><bean:write name="disabilityreport" property="total"/></td>

                        </tr>
                    </logic:iterate>
                </table><br>
    </body>
    <p>&nbsp;</p>

</html:html>

