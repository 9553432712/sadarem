<%@page contentType="text/html"%>
<%@page import="com.tcs.sadarem.util.CommonUtility"  pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@include  file="/DisabilityUITG/jsp/MentalCetificate.jsp" %>

<%
            String apflag = (String) request.getAttribute("apflag");
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>


<%
            String lingualTeluguName = "&#3114;&#3143;&#3120;&#3137;&#9;&#9;&#9;&#9;&#9;&#9";
            String lingualTeluguSex = "&#3122;&#3135;&#3074;&#3095;&#3118;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
            String lingualTeluguAddress = "&#3098;&#3135;&#3120;&#3137;&#3112;&#3134;&#3118;&#3134;&#9;&#9;&#9;&#9;&#9;&#9;";
            String telugupersonname = (String) request.getAttribute("telugupersonname");
            String telugufathername = (String) request.getAttribute("telugufathername");
            String gender = (String) request.getAttribute("gender");
            String father = "&#3108;&#3074;&#3105;&#3149;&#3120;&#3135;";
            String gardian = "&#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137; ";
            String dateofissue = "&#3100;&#3134;&#3120;&#3135; &#3098;&#3143;&#3128;&#3135;&#3112; &#3108;&#3143;&#3110;&#3135;";
            String dateofbirth = "&#3114;&#3137;&#3103;&#3149;&#3103;&#3135;&#3112; &#3108;&#3143;&#3110;&#3135;";
            String mole = "&#3095;&#3137;&#3120;&#3149;&#3108;&#3135;&#3074;&#3114;&#3137; &#3098;&#3135;&#3129;&#3149;&#3112;&#3134;&#3122;&#3137;&#9;&#9;&#9;&#9;&#9;&#9;";
            String validdate = "&#3093;&#3134;&#3122;&#3114;&#3120;&#3135;&#3118;&#3108;&#3135;";
            String disabilityduration = (String) request.getAttribute("disabilityduration");
            String age = "&#3125;&#3119;&#3128;&#3149;&#3128;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
            String categoryId = CommonUtility.checkNullObj(request.getAttribute("categoryId"));
%>
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
<br>
<html:html>
    <HEAD>

      <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">

    </HEAD>
    <BODY >
        <form action="MentalCertificate.do">

            <logic:iterate id="reportprint" name="reportlist" >
                <table border="1" align="center" width="25%" height="20%" >
                    <tr>
                        <td>
                            <table border="0" align="center" width="100%" >
                                <tr>
                                    <%if ("0".equals(apflag)) {%>
                                    <td width="20%" colspan="1"><img src="./DisabilityUITG/images/govtlogo.JPG" width="40" height="40">
                                    </td>
                                    <td  width="80%" class="idcardLabels">
                                        <font color="green">IDENTITY CARD FOR<br> PERSON WITH DISABILITY<br><br>Government of Andhra Pradesh</font>
                                    </td>
                                    <%} else {%>
                                        <td width="20%" colspan="1"><img src="./DisabilityUITG/images/tg_govtlogo.png" width="40" height="40">
                                    </td>
                                    <td  width="80%" class="idcardLabels">
                                        <font color="green">IDENTITY CARD FOR<br> PERSON WITH DISABILITY<br><br>Government of Telangana</font>
                                    </td>
                                        <%}%>
                                         <%if("4".equals(categoryId)){ %> 
				                            <tr>
				                                <td align="center" class="labelAppelate" colspan="2"><font size="1">(Appellate Authority Assessment)</font></td>
				                            </tr>
				                          <%} %>
	                                     
                                    <tr>
                                        <td colspan="3" align="center" class="idcardLabels">Medical Board:<bean:write name="reportprint"  property="hospitalname"/>
                                        </td>
                                    </tr>

                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table border="0" align="center" width="100%" cellpadding="1" cellspacing="1">

                    <tr>
                        <td class="idcardLabels">ID No:<font color="blue"><bean:write name="reportprint"  property="personcode"/></font>
                        </td>
                        <td rowspan="4" valign="top" class="idcardLabels"><img src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="80" height="80" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
                        </td>
                    </tr>
                    <tr>
                        <td class="idcardLabels">Name(<%=lingualTeluguName%>) : <font color="blue"> <bean:write name="reportprint"  property="name"/>&nbsp;&nbsp; (<%=telugupersonname%>) </font>
                        </td>
                    </tr>

                    <tr>
                        <td class="idcardLabels">Father(<%=father%>) /<br> Gaurdian Name(<%=gardian%>): <font color="blue"><bean:write name="reportprint"  property="relationname"/> &nbsp;&nbsp;(<%=telugufathername%> )</font>
                        </td>
                    </tr>

                    <tr>
                        <td class="idcardLabels">Date of Issue &nbsp;(<%=dateofissue%>) : <font color="blue" > <bean:write name="reportprint"  property="dateofisse"/></font>
                        </td>
                    </tr>

                    <tr>
                        <td class="idcardLabels">Valid Upto (<%=validdate%>) : <font color="blue"> <bean:write name="reportprint"  property="period"/>&nbsp;(<%=disabilityduration%>)</font>
                        </td>
                    </tr>


                    <tr>
                        <td class="idcardLabels">Date of Birth (<%=dateofbirth%>): <font color="blue"> <bean:write name="reportprint"  property="dateofbirth"/></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="idcardLabels">Age (<%=age%>): <font color="blue"> <bean:write name="reportprint"  property="age"/></font>
                        </td>
                    </tr>

                    <tr>
                        <td class="idcardLabels">Sex&nbsp;&nbsp;(<%=lingualTeluguSex%>) : <font color="blue"><bean:write name="reportprint"  property="gender"/>&nbsp;&nbsp;(<%=gender%>)</font>
                        </td>
                    </tr>

                    <tr>
                        <td class="idcardLabels">Address (<%=lingualTeluguAddress%>) : #<font color="blue" dir="LTR"> <bean:write name="reportprint"  property="housenumber"/>,<bean:write name="reportprint"  property="habitation_name"/>,
                                <bean:write name="reportprint"  property="village_name"/>,<bean:write name="reportprint"  property="mandal_name"/>,</font><br>&nbsp;&nbsp;&nbsp;<font color="blue" dir="LTR"><bean:write name="reportprint"  property="district_name"/>.</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="idcardLabels">Identification Marks (<%=mole%>) :
                            <br>1. <font color="blue" size="1"> <bean:write name="reportprint"  property="moleone"/>.</font>
                            <br>2. <font color="blue" size="1"> <bean:write name="reportprint"  property="moletwo"/>.</font>
                            <br>
                        </td>
                    </tr>
                    <tr>
                        <td class="columnHeight10" colspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="idcardLabels" align="right">
                            Signature/Thumb impression
                        </td>
                    </tr>

                </table>
            </td>
        </tr>
    </table>

</logic:iterate> 
<h3 align="center"> <a href="TerritorySelectForIdcard.do?print=idcardprint&status=finish&personstatus=Eligible" target="_blank" >
        Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</form>
</BODY>
</html:html>
