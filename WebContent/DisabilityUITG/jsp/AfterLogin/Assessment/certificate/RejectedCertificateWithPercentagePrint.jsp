<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            int i = 0;
            String apflag = (String) request.getAttribute("apflag");
%>

<%boolean rejectsugg = false;%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <HEAD>
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">

        <style type="text/css">
              p.solid
            {
                border:3px solid green;
            }
            
            p.line
            {
                line-height:0.5;
            }
            
            .cert-table 
	        {
	    		border-spacing: 10px !important;
	    		border-collapse : inherit !important;
	    	}
        </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body  onLoad="window.print()">

        <logic:iterate id="reportprint" name="reportlist" >
            <logic:iterate id="suggestion" name="suggestionlist">
                <%if (i == 0) {%>
                <table class="table_four cert-table" align="center">
                    <%if ("0".equals(apflag)) {%>
                    <tr>
                        <td align="center" colspan="2"><img src="./DisabilityUITG/images/govtprint.JPG"></td>
                    </tr>
                    <tr>
                        <td class="labelGreen" align="center" colspan="2">GOVERNMENT OF Andhra Pradesh
                        </td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td align="center" colspan="2"><img src="./DisabilityUITG/images/tggovtprint.png"></td>
                    </tr>
                    <tr>
                        <td class="labelGreen" align="center" colspan="2">GOVERNMENT OF TELANGANA
                        </td>
                    </tr>
                    <%}%>
                    <tr>
                        <td class="columnHeight10" colspan="2"></td>
                    </tr>
                    <tr>
                        <td width="80%" valign="bottom">
                            <table>

                                <tr>
                                    <td class="Cert-label">Medical Board:</td>
                                    <td class="Cert-label">
                                        <font color="blue">
                                            <bean:write name="reportprint"  property="hospitalname"/>, <bean:write name="reportprint"  property="hospitaladdress"/>
                                        </font>
                                    </td>
                                </tr>
                                <tr><td class="Cert-label">ID No:</td><td class="Cert-label"><font color="blue">AR<bean:write name="reportprint"  property="personcode"/></font></td>
                                </tr>
                                <tr>
                                    <td class="Cert-label">
                                        Date of Issue:</td><td class="Cert-label"><font color="blue"><bean:write name="reportprint"  property="dateofisse"/></font>
                                    </td>
                                </tr>
                            </table></td>
                        <td width="20%" align="right">
                            <table>
                                <tr> 
                                    <td>
<%--                                     <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
                                     <img align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<bean:write name="reportprint"  property="district_name"/>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                    
                                    </td></tr>
                            </table></td></tr>
                    <tr>
                        <td colspan="2">
                            <table class="table_three">
                                <tr>
                                    <td class="Cert-label" align="center">ASSESSMENT REPORT</td></tr>
                            </table>
                    <tr><td colspan="2">
                            <table>
                                <tr valign="top" >
                                    <td class="Cert-label">
                                        <bean:write name="reportprint"  property="maritialstatus"/>&nbsp; <font color="blue"><bean:write name="reportprint"  property="name"/></font> ,
                                        <bean:write name="reportprint"  property="relationship"/>
                                        <font color="blue"> <bean:write name="reportprint"  property="relationname"/></font>,
                                        <logic:notEmpty name="reportprint" property="housenumber">
                                            resident of H.No.#<font color="blue"> <bean:write name="reportprint"  property="housenumber"/></font> ,
                                        </logic:notEmpty>
                                        <font color="blue"><bean:write name="reportprint"  property="habitation_name"/></font> Habitation,
                                        <font color="blue"><bean:write name="reportprint"  property="village_name"/></font> Village,
                                        <font color="blue"><bean:write name="reportprint"  property="mandal_name"/> </font>Mandal,
                                        <font color="blue"><bean:write name="reportprint"  property="district_name"/></font> district, is here by informed that upon assessment of your disability, in accordance
                                        with the Guidelines prescribed by the Government of India wide. Notification No.154 dated: 01-06-2001/18-02-2002, it is found that
                                        you do not come under the category of Person With Disability.
                                        <br>
                                        <logic:notEqual name="suggestion" property="surgery" value=""><%  rejectsugg = true;%></logic:notEqual>
                                        <logic:notEqual name="suggestion" property="physiotherapy" value=""><%  rejectsugg = true;%></logic:notEqual>
                                        <logic:notEqual name="suggestion" property="anyother" value=""><%  rejectsugg = true;%></logic:notEqual>
                                        <logic:notEqual name="suggestion" property="councelling" value=""><%  rejectsugg = true;%></logic:notEqual><br>


                                        <%  if (rejectsugg) {%>

                                        However the following are suggested:<br><br>
                                        <% }
                                            rejectsugg = false;%>
                                        <logic:notEmpty name="suggestion"  property="surgery">
                                            <b>.</b> <bean:write name="suggestion"  property="surgery" /><br>
                                        </logic:notEmpty>
                                        <logic:notEmpty name="suggestion"  property="physiotherapy">
                                            <b>.</b> <bean:write name="suggestion" property="physiotherapy" /><br>
                                        </logic:notEmpty>
                                        <logic:notEmpty name="suggestion"  property="anyother">
                                            <b>.</b> <bean:write name="suggestion" property="anyother" /><br>
                                        </logic:notEmpty>
                                        <logic:notEmpty name="suggestion"  property="councelling">
                                            <b>.</b> <bean:write name="suggestion" property="councelling" />
                                        </logic:notEmpty>
                            </table></td></tr>
                    <tr>
                        <td class="columnHeight10" colspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%">
                                <tr>
                                    <td align="right" class="Cert-label">

                                        SIGNATURE OF THE SPECIALIST<br>
                                        Name:<font color="blue"><bean:write name="reportprint"  property="firstdoctorname"/></font><br>
                                        DESIGNATION:<font color="blue"><bean:write name="reportprint"  property="firstdesgination"/></font><br>
                                        REGN. NO.:<font color="blue"><bean:write name="reportprint"  property="firstdoctornumber"/></font>
                                    </td>

                                </tr>
                            </table>  </td></tr>
                </table>
                <%i++;%>
                <%}%>
            </logic:iterate>
        </logic:iterate>
    </body>
</html:html>
