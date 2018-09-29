<%--
    Document   : WebsiteForRejectedCertificateInFlow
    Created on : Nov 24, 2010, 10:03:10 AM
    Author     : 509865
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            int i=0;
            String apflag = (String) request.getAttribute("apflag");
%>


<%boolean rejectsugg = false;%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<br><br>
<html:html>
    <HEAD>
        <link rel="stylesheet" href="./DisabilityUITG/css/certificatestyles.css" type="text/css">

        <style type="text/css">
            p.solid
            {
                border:3px solid green;
            }
            p.line{
                line-height:0.5;}
            </style>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>SADAREM</title>
            <script language="text/javascript">

            </script>
        </head>
        <body>
          <% if(i==0){ %>
            <logic:notEmpty name="suggestionlist">

                <logic:iterate id="reportprint" name="reportlist">
                    <logic:iterate id="suggestion" name="suggestionlist">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="49"><img src="./DisabilityUITG/images/cert-top-lft.png" width="49" height="49"></td>
                                <td background="./DisabilityUITG/images/cert-top-bg.png">&nbsp;</td>
                                <td width="49"><img src="./DisabilityUITG/images/cert-top-rgt.png" width="49" height="49"></td>
                            </tr>
                            <tr>
                                <td width="49" background="./DisabilityUITG/images/cert-lft-bg.png">&nbsp;</td>
                                <%if ("0".equals(apflag)) {%>
                                <td  background="./DisabilityUITG/images/Govt-Log-wtrmrk.jpg" style="background-position:center; background-repeat:no-repeat;"><table align="center" width="100%" cellpadding="0" cellspacing="0">
                                    <%} else {%>
                                    <td  background="./DisabilityUITG/images/tgGovt-Log-wtrmrk.png" style="background-position:center; background-repeat:no-repeat;"><table align="center" width="100%" cellpadding="0" cellspacing="0">
                                            <%}%>    <%if ("0".equals(apflag)) {%>
                                        <tr>
                                            <td align="center" colspan="2"><img src="<%=request.getContextPath()%>/images/ap_govt_logo.png"></td>
                                        </tr>
                                        <tr>
                                            <td class="labelGreen" align="center" colspan="2">GOVERNMENT OF ANDHRA PRADESH
                                            </td>
                                        </tr>
                                        <%} else {%>
                                         <tr>
                                              <td align="center" colspan="2"><img src="./DisabilityUITG/images/tggovt.png"></td>
                                        </tr>
                                        <tr>
                                            <td class="labelGreen" align="center" colspan="2">GOVERNMENT OF TELANGANA
                                            </td>
                                        </tr>
                                        <%} %>
                                        <tr>
                                            <td class="columnHeight10" colspan="2"></td>
                                        </tr>
                                        <tr>
                                            <td width="80%" valign="bottom">
                                                <table>
                                                    <tr>
                                                        <td class="label"><strong>Medical Board:</strong></td>
                                                        <td class="label">
                                                            <font color="blue">
                                                                <bean:write name="reportprint"  property="hospitalname"/>, <bean:write name="reportprint"  property="hospitaladdress"/>
                                                            </font>
                                                        </td>
                                                    </tr>
                                                    <tr><td class="label"><strong>ID No:</strong></td><td class="label"><font color="blue">AR<bean:write name="reportprint"  property="personcode"/></font></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="label"><strong>
                                                            Date of Issue:</strong></td><td class="label"><font color="blue"><bean:write name="reportprint"  property="dateofisse"/></font>
                                                        </td>
                                                    </tr>
                                                </table></td>
                                            <td width="20%" align="right">
                                                <table>
                                                    <tr>
                                                        <td>
                                                        <img align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reject"  property="personcode"/>&distName=<bean:write name="reject"  property="districtname"/>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                                        <%-- <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
                                                        </td></tr>
                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"  style="background-position:center; background-repeat:no-repeat;">
                                            <table class="table_three">
                                                <tr>
                                                    <td class="label" align="center">ASSESSMENT REPORT</td></tr>
                                                <tr>
                                                    <td class="columnHeight10" colspan="2"></td>

                                                </tr>


                                                <tr valign="top" >
                                                    <td class="label" colspan="2">
                                                        <bean:write name="reportprint"  property="maritialstatus"/>&nbsp; <font color="blue"><bean:write name="reportprint"  property="name"/></font>,
                                                        <bean:write name="reportprint"  property="relationship"/> <font color="blue">
                                                            <bean:write name="reportprint"  property="relationname"/></font>,
                                                            <logic:notEmpty name="reportprint" property="housenumber">
                                                            resident of H.No.#<font color="blue">
                                                                <bean:write name="reportprint"  property="housenumber"/></font> ,
                                                            </logic:notEmpty>
                                                        <font color="blue"><bean:write name="reportprint"  property="habitation_name"/></font> Habitation,
                                                        <font color="blue"><bean:write name="reportprint"  property="village_name"/></font> Village,
                                                        <font color="blue"><bean:write name="reportprint"  property="mandal_name"/> </font>Mandal,
                                                        <font color="blue"><bean:write name="reportprint"  property="district_name"/></font> district,
                                                        is here by informed that upon assessment of your disability, in accordance with the Guidelines prescribed by the Government of India wide. Notification no.154 dated: 01-06-2001/18-02-2002, it is found that you do not come under the category of Person With Disability.
                                                        <br>
                                                        <logic:notEqual name="suggestion" property="surgery" value=""><%  rejectsugg = true;%></logic:notEqual>
                                                        <logic:notEqual name="suggestion" property="physiotherapy" value=""><%  rejectsugg = true;%></logic:notEqual>
                                                        <logic:notEqual name="suggestion" property="anyother" value=""><%  rejectsugg = true;%></logic:notEqual>
                                                        <logic:notEqual name="suggestion" property="anyotherneed" value=""><%  rejectsugg = true;%></logic:notEqual>
                                                        <logic:notEqual name="suggestion" property="councelling" value=""><%  rejectsugg = true;%></logic:notEqual><br>

                                                        <%  if (rejectsugg) {%>

                                                        However the following are suggested:<br><br>
                                                        <% }
                                                        rejectsugg = false;%>
                                                        <ul>
                                                        <logic:notEmpty name="suggestion"  property="surgery">
                                                            <li> <bean:write name="suggestion"  property="surgery" /></li><br>
                                                        </logic:notEmpty>
                                                        <logic:notEmpty name="suggestion"  property="physiotherapy">
                                                            <li> <bean:write name="suggestion" property="physiotherapy" /></li><br>
                                                        </logic:notEmpty>
                                                        <logic:notEmpty name="suggestion"  property="anyother">
                                                            <li> <bean:write name="suggestion" property="anyother" /></li><br>
                                                        </logic:notEmpty>
                                                        <logic:notEmpty name="suggestion"  property="councelling">
                                                            <li> <bean:write name="suggestion" property="councelling" /></li>
                                                        </logic:notEmpty>
                                                            </ul>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="columnHeight10" colspan="2"></td>
                                                    <td class="columnHeight10" colspan="2"></td>
                                                </tr>
                                                <tr>

                                                    <td align="right" class="label"><strong>
                                                            SIGNATURE OF THE SPECIALIST</strong><br>
                                                        Name:<font color="blue">Dr.<bean:write name="reportprint"  property="firstdoctorname"/></font><br>
                                                        DESIGNATION:<font color="blue"><bean:write name="reportprint"  property="firstdesgination"/></font><br>
                                                        REGN. NO.:<font color="blue"><bean:write name="reportprint"  property="firstdoctornumber"/></font>
                                                    </td>

                                                </tr>
                                            </table>  </td></tr>
                                </table>
                            </td>
                            <td width="49" background="./DisabilityUITG/images/cert-rgt-bg.png">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="49"><img src="./DisabilityUITG/images/cert-btm-lft.png" width="49" height="49"></td>
                            <td background="./DisabilityUITG/images/cert-btm-bg.png">&nbsp;</td>
                            <td width="49"><img src="./DisabilityUITG/images/cert-btm-rgt.png" width="49" height="49"></td>
                        </tr>
                    </table>

                </logic:iterate>
            </logic:iterate>
        </logic:notEmpty>
       <%i=1;%>
       <%}%>
        <logic:empty name="suggestionlist" >
            <center ><font color="red" ><b>No Data</b></font></center>
        </logic:empty >

    </body>
</html:html>

