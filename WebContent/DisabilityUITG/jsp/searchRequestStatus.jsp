<%-- 
    Document   : searchRequestStatus
    Created on : Nov 23, 2012, 11:00:13 AM
    Author     : 310926
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
 

    </head>
   

    
    <body style="margin:0px;padding:0px;">
        <html:form action="searchRequestStatus">
            <html:hidden property="mode"/>



            <logic:present name="msg">
                <br><br>
                <p align="center"><font color="red">${msg}</font></p>
            </logic:present><br>




            <logic:notEmpty name="getDetailsList">
             
                <div style="overflow:scroll;width:100%;overflow-y:hidden;">
                <table align="center" cellpadding="3" cellspacing="1" border="0" width="90%" style="font-size:12px;border:1px solid black;">
                    <tr class="formhdbg">
                        <td align="center">S No.</td>
                        <td align="center">SADAREM ID</td>
                        <td align="center">Request Id</td>
                        <td align="center">Request Type</td>
                        <td align="center">Name</td>
                        <td align="center">Relation Name</td>
                        <td align="center">District</td>
                        <td align="center">Mandal</td>
                        <td align="center">Village</td>
                        <td align="center">habitation Code</td>
                        <td align="center">House No</td>
                        <td align="center">Created Date</td>
                        <td align="center">Request Status</td>
                    </tr>
                    <%int i = 1;%>
                    <logic:iterate name="getDetailsList" id="row">
                        <tr bgcolor="white">
                            <td align="center"><%=i++%></td>
                            <td align="center"><bean:write name="row" property="person_code"/></td>
                            <td align="center"><bean:write name="row" property="req_id"/></td>
                            <td align="center"><bean:write name="row" property="requestType"/></td>
                            <td align="center"><bean:write name="row" property="name"/></td>
                            <td align="center"><bean:write name="row" property="relation_name"/></td>
                            <td align="center"><bean:write name="row" property="district_name"/></td>
                            <td align="center"><bean:write name="row" property="mandal_name"/></td>
                            <td align="center"><bean:write name="row" property="village_name"/></td>
                            <td align="center"><bean:write name="row" property="habitation_name"/></td>
                            <td align="center"><bean:write name="row" property="houseno"/></td>
                            <td align="center"><bean:write name="row" property="created_date"/></td>
                            <td align="center"><bean:write name="row" property="status"/></td>


                        </tr>
                    </logic:iterate>
                </table>
                   </div>
            </logic:notEmpty>

        </html:form>
    </body>
</html>
