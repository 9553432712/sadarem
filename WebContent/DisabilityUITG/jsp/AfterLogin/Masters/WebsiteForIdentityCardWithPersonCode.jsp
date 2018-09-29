<%--
    Document   : WebsiteForIdentityCardWithPersonCode
    Created on : Nov 24, 2010, 10:13:29 AM
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
%>


<%
            String apflag = (String) request.getAttribute("apflag");
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
            String mole = "&#3095;&#3137;&#3120;&#3149;&#3108;&#3135;&#3074;&#3114;&#3137; &#3098;&#3135;&#3129;&#3149;&#3112;&#3134;&#3122;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";

            String validdate = "&#3093;&#3134;&#3122;&#3114;&#3120;&#3135;&#3118;&#3108;&#3135;";
            String disabilityduration = (String) request.getAttribute("disabilityduration");
            String age = "&#3125;&#3119;&#3128;&#3149;&#3128;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
            String lingualTeluguTypeofDisability = "&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;";
            String disabilitypercentage = "&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125; &#3126;&#3134;&#3108;&#3074;";
            String disabilitytypeInTelugu = (String) request.getAttribute("disabilitytypeInTelugu");
            String teluguDisablityName = (String) session.getAttribute("teluguDisablityName");
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

        <link rel="stylesheet" href="./DisabilityUITG/css/certificatestyles.css" type="text/css">
        <script language="javascript">

        </script>
    </HEAD>

    <BODY >
        <form action="MentalCertificate.do">

            <logic:iterate id="reportprint" name="reportlist" >
                <table  width="50%" align="center" border="0" style="height: 431px">
                    <tr>
                        <td width="50%" valign="top">
                            <table border="1" width="100%" style="height: 431px">
                                <tr>
                                    <td>
                                        <table  width="100%">
                                            <%if ("0".equals(apflag)) {%>
                                            <tr>
                                                <td><img src="./DisabilityUITG/images/govtlogo.JPG" width="40" height="40"></td>
                                                <td class="idcardLabels">
                                                    <font color="green">Government of Andhra Pradesh<br>IDENTITY CARD FOR<br> PERSON WITH DISABILITY</font></td>
                                            </tr>
                                            <%} else {%>
                                            <tr>
                                                <td><img src="./DisabilityUITG/images/tg_govtlogo.png" width="40" height="40"></td>
                                                <td class="idcardLabels">
                                                    <font color="green">Government of Telangana<br>IDENTITY CARD FOR<br> PERSON WITH DISABILITY</font></td>
                                            </tr>
                                            <%}%>
                                            <tr>
                                                <td colspan="2"  class="idcardLabels">Medical Board of <bean:write name="reportprint"  property="hospitalname"/>,
                                                    <bean:write name="reportprint"  property="hospitaladdress"/></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table>
                                            <tr>
                                                <td class="idcardLabels">
                                                    ID No: <font color="blue"><bean:write name="reportprint"  property="personcode"/></font>
                                                </td>
                                                <td rowspan="3" align="right" valign="top">
                                                    <img src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="60" height="60" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels">Name (<%=lingualTeluguName%>) : <font color="blue"> <bean:write name="reportprint"  property="name"/><br>(<%=telugupersonname%>)</font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels">Father(<%=father%>)/<br> Gaurdian Name(<%=gardian%>): <font color="blue"><bean:write name="reportprint"  property="relationname"/> &nbsp;&nbsp;(<%=telugufathername%>)</font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Date of Issue(<%=dateofissue%>) : <font color="blue"> <bean:write name="reportprint"  property="dateofisse"/></font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Valid Upto(<%=validdate%>): <font color="blue"> <bean:write name="reportprint"  property="period"/>&nbsp;(<%=disabilityduration%>)</font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Date of Birth (<%=dateofbirth%>): <font color="blue"> <bean:write name="reportprint"  property="dateofbirth"/></font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Age (<%=age%>): <font color="blue"> <bean:write name="reportprint"  property="age"/></font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Sex&nbsp;&nbsp;(<%=lingualTeluguSex%>) : <font color="blue"><bean:write name="reportprint"  property="gender"/>&nbsp;&nbsp;(<%=gender%>)</font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Address (<%=lingualTeluguAddress%>) : #<font color="blue" dir="LTR">
                                                        <logic:notEmpty name="reportprint" property="housenumber">
                                                            <bean:write name="reportprint"  property="housenumber"/>,</logic:notEmpty> <bean:write name="reportprint"  property="habitation_name"/>, <br>
                                                        <bean:write name="reportprint"  property="village_name"/>,<bean:write name="reportprint"  property="mandal_name"/>,
                                                    </font><br><font color="blue" dir="LTR"><bean:write name="reportprint"  property="district_name"/>.</font>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="idcardLabels" colspan="2">Identification Marks (<%=mole%>) :
                                                    <br>1. <font color="blue"> <bean:write name="reportprint"  property="moleone"/>.</font>
                                                    <br>2. <font color="blue"> <bean:write name="reportprint"  property="moletwo"/>.</font>
                                                </td>
                                            </tr>

                                            <tr><td class="columnHeight10" colspan="2"></td></tr>

                                            <tr>
                                                <td colspan="2" class="idcardLabels" align="right" valign="bottom">Signature/Thumb impression</td>
                                            </tr>

                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="2%"></td>
                        <td width="50%" valign="top">
                            <table border="1" align="center" width="100%" style="height: 431px">
                                <tr>
                                    <td>
                                        <table border="0" align="center" width="100%" >
                                            <tr>
                                                <td class="idcardLabels">Nature of Disability (<%=lingualTeluguTypeofDisability%> ): <font color="blue" ><bean:write name="reportprint"  property="validity"/></font>&nbsp;&nbsp;<font color="blue"><bean:write name="reportprint"  property="disabilityTypeInIdCard"/> <br>(<%=teluguDisablityName%> &nbsp; <%=disabilitytypeInTelugu%>)</font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Percentage of Impairment (<%=disabilitypercentage%>): <font color="blue"><bean:write name="reportprint"  property="disabilityvalue"/>%</font>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table border="0" align="center" width="100%">
                                            <tr>
                                                <td class="idcardLabels">Doctor
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Signature :
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Name : <font color="blue"><bean:write name="reportprint"  property="firstdoctorname"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Designation : <font color="blue"> <bean:write name="reportprint"  property="firstdesgination"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Registration No : <font color="blue"> <bean:write name="reportprint"  property="firstdoctornumber"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="50%" class="idcardLabels">Doctor
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Signature :
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Name : <font color="blue">Dr. <bean:write name="reportprint"  property="seconddoctorname"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Designation : <font color="blue"> <bean:write name="reportprint"  property="seconddesgination"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Registration No : <font color="blue" > <bean:write name="reportprint"  property="seconddoctornumber"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="50%" class="idcardLabels">Doctor
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Signature :
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  class="idcardLabels">Name : <font color="blue" >Dr. <bean:write name="reportprint"  property="thirddoctorname"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Designation : <font color="blue"> <bean:write name="reportprint"  property="thirddesgination"/></font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="idcardLabels">Registration No : <font color="blue" > <bean:write name="reportprint"  property="thirddoctornumber"/></font>
                                                    <br>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="idcardLabels"><div align="justify">  <u>NOTE</u> :- 1. This card is valid for claiming Air / Bus /  Rail  Concession and benefits  sanctioned for eligible disabled persons,  by authorities concerned / Government of  <%if ("0".equals(apflag)) {%>
                                            A.P.
                                            <%} else {%>
                                            T.S.
                                            <%}%> <br>
                                            2. All particulars, except disability and degree of disability, are based on information given by I-card holder.
                                        </div></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

            </logic:iterate>

        </form>
    </BODY>
</html:html>

