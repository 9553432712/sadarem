<%-- 
    Document   : mesevaAcknowledgment
    Created on : Jan 29, 2014, 10:11:58 AM
    Author     : 310926
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<html:html>
    <LINK REL="stylesheet" HREF="./DisabilityUITG/css/sadarem_styles.css">

    <html:form  action="/mesevaNewCertificateRegistration"  >

        <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
            <tr>
                <td  align="center"  valign="top">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="inputform">
                        <tr>
                            <td align="center"><img src="./DisabilityUITG/images/tggovtlogo.png" /></td>
                        </tr>
                        <tr>
                            <td  align="right"  colspan="4" style="font-weight: bold">Date of Payment : <%=created_date%></td>

                        </tr>
                        <tr>
                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%" class="inputform" >
                           
                                <th  colspan="4">SADAREM New Certificate Registration Acknowledgment</th>
                            
                            <%if(meesevaId!=null && !meesevaId.equalsIgnoreCase("")){%>
                            <tr><td  style="font-weight: bold"> Transaction Id :</td>
                                <td ><%=meesevaId%></td>
                                <td  style="font-weight: bold">Authorised Agent:</td>
                                <td ><%=channelId%></td>
                            </tr>
                            <%}else{%>
                            <tr>
                                <td  style="font-weight: bold">Authorised Agent:</td>
                                <td  colspan="3"><%=channelId%></td>
                            </tr>
                            <%}%>
                            <tr><td  style="font-weight: bold">Applicant Name :</td>
                                <td ><%=name%></td>
                                <td  style="font-weight: bold">Application No :</td>
                                <td ><%=applicationNo%></td>
                            </tr>

                            <tr><td  style="font-weight: bold">Document District :</td>
                                <td ><%=districtName%></td>
                                <td  style="font-weight: bold">Document Mandal :</td>
                                <td ><%=mandalName%></td>
                            </tr>

                            <tr><td  style="font-weight: bold">Document Village :</td>
                                <td  ><%=villageName%></td>
                                <td  style="font-weight: bold">Delivery Type :</td>
                                <td >Manual</td>
                            </tr>

                            <tr><td  style="font-weight: bold">Amount Paid (in Rs.) :</td>
                                <td  colspan="3">25</td>
                            </tr>
                            <tr><td  style="font-weight: bold">Status :</td>
                                <td  colspan="3">Pending for Document Verification. Please Contact District Project Manager</td>
                            </tr>

                        </table>
                    </tr>
            <tr><td align="center">
                    <a href="mesevaNewCertificateRegistration.do?mode=acknowlegmentPrint&channelId=<%=channelId%>&applicationNo=<%=applicationNo%>" target="_blank">
                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td></tr>
        </table>
    </td>
</tr>
</table>


</html:form>
</body>
</html:html>
