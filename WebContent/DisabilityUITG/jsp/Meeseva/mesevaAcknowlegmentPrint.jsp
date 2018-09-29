<%-- 
    Document   : mesevaAcknowlegmentPrint
    Created on : Jan 29, 2014, 6:02:04 PM
    Author     : 310926
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <link rel="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <%
                    String name = (String) request.getAttribute("name");
                    String age = (String) request.getAttribute("age");
                    String created_date = (String) request.getAttribute("created_date");
                    String districtName = (String) request.getAttribute("districtName");
                    String mandalName = (String) request.getAttribute("mandalName");
                    String villageName = (String) request.getAttribute("villageName");
                    String habitationName = (String) request.getAttribute("habitationName");
                    String panchayatName = (String) request.getAttribute("panchayatName");
                    String applicationNo = (String) request.getAttribute("applicationNo");
                    String channelId = (String) request.getAttribute("channelId");
                    String meesevaId = (String) request.getAttribute("meesevaId");
        %>

       <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
            <tr>
                <td height="445" align="center"  valign="top">

                    <table width=100%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
                        <tr>
                            <td align="center"><img src="./DisabilityUITG/images/tggovtlogo.png" /></td>
                        </tr>
                        <tr><td  align="right"  colspan="4" style="font-weight: bold">Date of Payment :  <%=created_date%></td>

                        </tr>
                        <tr> <table width=100%" border="0" cellspacing="1" cellpadding="0" align="center" class="inputform">
                            
                                <th colspan="4">SADAREM New Certificate Registration Acknowledgment</th>
                           

                            <%if(meesevaId!=null && !meesevaId.equalsIgnoreCase("")){%>
                            <tr><td style="font-weight: bold"> Transaction Id :</td>
                                <td ><%=meesevaId%></td>
                                <td style="font-weight: bold">Authorised Agent:</td>
                                <td ><%=channelId%></td>
                            </tr>
                            <%}else{%>
                            <tr>
                                <td style="font-weight: bold">Authorised Agent:</td>
                                <td colspan="3"><%=channelId%></td>
                            </tr>
                            <%}%>
                            <tr><td style="font-weight: bold">Applicant Name :</td>
                                <td ><%=name%></td>
                                <td style="font-weight: bold">Application No :</td>
                                <td ><%=applicationNo%></td>
                            </tr>

                            <tr><td style="font-weight: bold">Document District :</td>
                                <td ><%=districtName%></td>
                                <td style="font-weight: bold">Document Mandal :</td>
                                <td ><%=mandalName%></td>
                            </tr>

                            <tr><td style="font-weight: bold">Document Village :</td>
                                <td ><%=villageName%></td>
                                <td style="font-weight: bold">Delivery Type :</td>
                                <td >Manual</td>
                            </tr>

                            <tr><td style="font-weight: bold">Amount Paid (in Rs.) :</td>
                                <td colspan="3">25</td>
                            </tr>
                            <tr><td style="font-weight: bold">Status :</td>
                                <td colspan="3">Pending for Document Verification. Please Contact District Project Manager</td>
                            </tr>

                        </table></tr>
        </table>


    </td>
</tr>
</table>
</body>
</html>

