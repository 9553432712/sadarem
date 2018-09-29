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



<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>
<%
            double total;

            if (session.getAttribute("total") != null) {
                Double totaldisability = totaldisability = (Double) session.getAttribute("total");
                total = totaldisability.doubleValue();
            } else {
                total = 0;
            }
            String districtname = ((String) request.getAttribute("districtname"));
            String mandalname = ((String) request.getAttribute("mandalname"));
            String villagename = ((String) request.getAttribute("vilagename"));
            String condition = (String) request.getAttribute("condition");
            String causeOfDisabilityFlag = (String) request.getAttribute("causeOfDisabilityFlag");
            String conditionlistCheckFlag = (String) request.getAttribute("conditionlistCheckFlag");
            String apflag = (String) request.getAttribute("apflag");
            
            String Todaydate = (String) request.getAttribute("Todaydate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<br><br>
<html:html>
    <HEAD>

        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">

    </HEAD>

    <BODY >

        <form action="MentalCertificate.do">

            <table border="2" class="tbl_bg3" bgcolor="green">
                <logic:notEmpty name="reportlist" >
                    <logic:notEmpty name="conditionlist" >
                        <logic:iterate id="reportprint" name="reportlist" >

                            <logic:iterate id="subdetails" name="subdetailslist" >
                                <logic:iterate id="condition1" name="conditionlist" >
                                    <logic:iterate id="idcarddata" name="Idcardlist" >
                                        <input type="hidden" name="flag" value="finish"/>
                                        <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="90%">
                                            <%if ("0".equals(apflag)) {%>
                                            <tr valign="top" class="tbl_bg2_content" align="center">
                                                <td align="center"><img src="./DisabilityUITG/images/apgovtlogo.JPG" width="100" height="100" ></td>
                                            </tr>
                                            <%} else {%>
                                            <tr valign="top" class="tbl_bg2_content" align="center">
                                                 <td align="center"><img src="./DisabilityUITG/images/tggovtlogo.png" width="100" height="100" ></td>
                                            </tr>
                                            <%}%>
                                        </table><
                                        <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="90%">
                                            <TR>
                                                <td colspan="3" align="center"><font color="green" size="4"><b>CERTIFICATE FOR PERSON WITH DISABILITY</b></font>
                                                </td>
                                            </TR>
                                            <%if ("0".equals(apflag)) {%>
                                            <tr>
                                                <td colspan="3"  align="center"><font color="FF9900" size="4"><b><br> Government of Andhra Pradesh</b></font>
                                                </td>
                                            </tr>
                                            <%} else {%>
                                            <tr>
                                                <td colspan="3"  align="center"><font color="FF9900" size="4"><b><br> Government of Telangana</b></font>
                                                </td>
                                            </tr>
                                            <%}%>
                                            <tr>
                                                <td colspan="3" align="center"><font size="2"><b>Medical Board &nbsp;:&nbsp;<bean:write name="reportprint"  property="hospitalname"/>&nbsp;,&nbsp;<bean:write name="reportprint"  property="hospitaladdress"/></b></font>
                                                </td>
                                            </tr>
                                            <tr valign="top" class="tbl_bg2_content">
                                                <td width="45%" colspan="2">
                                                    <font size="3">
                                                        <div align="left"><b>I.D.No:&nbsp;  <font color="blue"><bean:write name="reportprint"  property="personcode"/></font></div></font>
                                                </td>
                                                <td  width="45%">
                                                    <font size="3">
                                                        <div align="center">Date:<font color="blue"><bean:write name="idcarddata"  property="dateofisse"/></font>
                                                        </div>
                                                    </font>
                                                </td>
                                            </tr>

                                            <tr valign="top" class="tbl_bg2_content">
                                                <td colspan="3">
<%--                                                 <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
 --%>                                                <img  align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<%=districtname%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                                    <p align="justify"><div align="justify"><font size ="4" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Shri/Smt/Kum: <font color="blue" size="4"><bean:write name="reportprint"  property="name"/></font>, <bean:write name="reportprint"  property="relationship"/><font color="blue" size="4"> <bean:write name="reportprint"  property="relationname"/></font>,&nbsp;<font color="blue" size="4"><bean:write name="reportprint"  property="gender"/></font> aged <font color="blue" size="4"><bean:write name="reportprint"  property="age"/></font> years,
                                                            resident of H.No.# <font color="blue"><bean:write name="reportprint"  property="housenumber"/></font>, <font color="blue"><%=villagename%></font> Village, <font color="blue"><%=mandalname%> </font>Mandal, <font color="blue"><%=districtname%></font>&nbsp;District,
                                                            is suffering from permanent/temporary disability of the following category:-
                                                            <%--<logic:notEmpty name="subdetails"  property="ohsubtypes1">
                                                            <font color="blue" size="3">  </font>

                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes1" /></b></font>
   </logic:notEmpty>

<logic:notEmpty name="subdetails"  property="ohsubtypes2" >
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes2" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes3">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes3" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes4" >
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes4" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes5">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes5" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes6">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes6" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes7">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes7" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes8">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes8" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes9">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes9" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes10">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes10" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes11" >
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes11" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes12">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes12" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes13">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes13" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes14">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes14" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="subdetails"  property="ohsubtypes15">
                                        <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes15" /></b></font>

</logic:notEmpty>--%>


                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">
                                                                The disability is in relation to <bean:write name="reportprint"  property="genderInitial2"/>
                                                            </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">
                                                                <logic:notEmpty name="reportprint"  property="coordinate_lifting">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_lifting" /></b></font>
                                                                </logic:notEmpty>
                                                                <%--<logic:notEmpty name="reportprint"  property="coordinate_touching">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_touching" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_eating">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_eating" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_combing">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_combing" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_putting">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_putting" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_ablution">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_ablution" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_buttoning">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_buttoning" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_tie">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_tie" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_writing">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_writing" /></b></font>
                                                               </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate">
                                                                   <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate" /></b></font>
                                                               </logic:notEmpty>--%><logic:notEmpty name="reportprint"  property="hand_stgrip_left">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stgrip_left" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stgrip_right">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stgrip_right" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stpinch_left">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stpinch_left" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stpinch_right">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stpinch_right" /></b></font>
                                                                </logic:notEmpty><%--<logic:notEmpty name="reportprint"  property="strength">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="strength" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="reportprint"  property="upperExtremity_Total">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="upperExtremity_Total" /></b></font>
                                                                </logic:notEmpty>--%>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved1">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved1" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved2">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved2" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved3">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved3" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved4">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved4" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved5">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved5" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved6">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved6" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved7">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved7" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved8">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved8" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved9">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved9" /></b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="subdetails"  property="partInvolved10">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved10" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved11">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved11" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved12">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved12" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved13">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved13" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved14">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved14" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved15">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved15" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved16">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved16" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved17">
                                                                    <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved17" /></b></font>
                                                                </logic:notEmpty>
                                                                .
                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds1">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds1" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds2">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds2" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds3">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds3" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds4">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds4" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds5">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds5" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds6">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds6" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds7">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds7" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds8">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds8" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds9">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds9" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds10">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds10" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds11">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds11" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds12">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds12" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds13">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds13" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds14">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds14" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds15">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds15" />,</b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds1">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds1" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds2">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds2" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds3">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds3" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds4">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds4" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds5">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds5" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds6">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds6" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds7">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds7" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds8">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds8" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds9">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds9" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds10">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds10" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds11">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds11" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds12">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds12" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds13">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds13" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds14">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds14" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds15">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds15" />,</b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Visual Disabled">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds16">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds16" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds17">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds17" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds18">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds18" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds19">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds19" />,</b></font>
                                                                </logic:notEmpty>
                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Hearing Impairment">
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds20">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds20" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds21">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds21" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds22">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds22" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds23">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds23" />,</b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Mental Retardation">


                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds24">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds24" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds25">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds25" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds26">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds26" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds27">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds27" />,</b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Mental Illness">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds28">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds28" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds29">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds29" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds30">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds30" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubIds31">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubIds31" />,</b></font>
                                                                </logic:notEmpty>
                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds1">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds1" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds2">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds2" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds3">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds3" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds4">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds4" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds5">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds5" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds6">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds6" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds7">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds7" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds8">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds8" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds9">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds9" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds10">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds10" /></b></font>
                                                                </logic:notEmpty>
                                                            </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">

                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds1">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds1" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds2">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds2" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds3">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds3" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds4">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds4" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds5">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds5" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds6">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds6" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds7">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds7" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds8">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds8" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds9">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds9" />,</b></font>
                                                                </logic:notEmpty>
                                                                <logic:notEmpty name="reportprint"  property="disabilitySubSubIds10">
                                                                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="disabilitySubSubIds10" /></b></font>
                                                                </logic:notEmpty>
                                                            </logic:equal>


                                                            <logic:equal name="reportprint" property="disabilitytype" value="Mental Retardation">
                                                                IQ=<font color="blue" size="4"> <bean:write name="reportprint"  property="mentalRetardation_IQ"/>(<bean:write name="reportprint"  property="mentalRetardation_IQ_Inwords"/>)</font>&nbsp;
                                                            </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Hearing Impairment">
                                                                Loss of<font color="blue" size="4"> <bean:write name="reportprint"  property="hearing_DB"/>(<bean:write name="reportprint"  property="hearing_DB_In_Words"/>)</font>&nbsp;decibels in the better ear in the conversational range of frequencies .
                                                            </logic:equal>
                                                            <%if ("true".equals(causeOfDisabilityFlag)) {%>
                                                            Cause of Disability
                                                            <logic:notEmpty name="reportprint"  property="congenital">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="congenital" />,</b></font>
                                                            </logic:notEmpty><logic:notEmpty name="reportprint"  property="hereditary">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hereditary" />,</b></font>
                                                            </logic:notEmpty><logic:notEmpty name="reportprint"  property="birthinjury">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="birthinjury" />,</b></font>
                                                            </logic:notEmpty><logic:notEmpty name="reportprint"  property="malnutrition">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="malnutrition" />,</b></font>
                                                            </logic:notEmpty><logic:notEmpty name="reportprint"  property="duseaseandinfection">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="duseaseandinfection" />,</b></font>
                                                            </logic:notEmpty><logic:notEmpty name="reportprint"  property="accident">
                                                                <font color="blue" size="3"><b> <bean:write name="reportprint"  property="accident" />,</b></font>
                                                            </logic:notEmpty>
                                                            <%}%>


                                                            <%-- <logic:notEqual name="reportprint" property="loco" value="locomotor">  <font color="blue" size="3">  </font>
                                                                <%=disabilityreason%></logic:notEqual>.--%>


                                                            <bean:write name="reportprint"  property="genderInitial1"/> is <font color="blue" size="4"><bean:write name="reportprint" property="disabilitytype"/></font><logic:notEqual name="reportprint" property="comment" value="yes">(<%=condition%>)</logic:notEqual>&nbsp;  and has&nbsp;<font color="blue" size="4"><bean:write name="reportprint" property="disabilityPercentage"/></font>%&nbsp; (<bean:write name="reportprint"  property="percentageInWord"/> Percentage)&nbsp;

                                                            <bean:write name="reportprint"  property="validity"/> &nbsp;<font color="blue" size="4"><bean:write name="reportprint" property="disabilityintellec"/> </font><logic:notEqual name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">. </logic:notEqual>
                                                            <%if ("true".equals(conditionlistCheckFlag)) {%>

                                                            <bean:write name="reportprint"  property="genderInitial1"/>  meets the following physical requirements for discharge of his/her duties.
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">


                                                                <logic:notEmpty name="condition1"  property="ohcondition1">
                                                                    <font color="blue" size="3"><b><bean:write name="condition1"  property="ohcondition1" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition2">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition2" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition3">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition3" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition4">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition4" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition5">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition5" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition6">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition6" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition7">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition7" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition8">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition8" /></b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Physically(Locomotor/orthopaedically) Disabled">


                                                                <logic:notEmpty name="condition1"  property="ohcondition1">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition1" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition2">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition2" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition3">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition3" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition4">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition4" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition5">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition5" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition6">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition6" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition7">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition7" />,</b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition8">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition8" /></b></font>
                                                                </logic:notEmpty>

                                                            </logic:equal>

                                                            <logic:equal name="reportprint" property="disabilitytype" value="Visual Impairment">
                                                                <logic:notEmpty name="condition1"  property="ohcondition9">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition9" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition10">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition10" /></b></font>
                                                                </logic:notEmpty> </logic:equal>
                                                            <logic:equal name="reportprint" property="disabilitytype" value="Visual Disabled">
                                                                <logic:notEmpty name="condition1"  property="ohcondition9">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition9" /></b></font>
                                                                </logic:notEmpty><logic:notEmpty name="condition1"  property="ohcondition10">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition10" /></b></font>
                                                                </logic:notEmpty> </logic:equal>


                                                            <logic:equal name="reportprint" property="disabilitytype" value="Hearing Impairment">
                                                                <logic:notEmpty name="condition1"  property="ohcondition11">
                                                                    <font color="blue" size="3"><b> <bean:write name="condition1"  property="ohcondition11" /></b></font>
                                                                </logic:notEmpty>
                                                            </logic:equal>
                                                            <%}%>
                                                        </p></div>

                                                    <%--<logic:notEmpty name="reportprint"  property="othercause">
                    <font color="blue" size="3"><b> <bean:write name="reportprint"  property="othercause" /></b></font>
                </logic:notEmpty>--%>
                                                    Identification Marks:-
                                                    <font color="blue" size="3"><b> 1)<bean:write name="reportprint"  property="moleone"/></b></font>
                                                    <font color="blue" size="3"><b>2) <bean:write name="reportprint"  property="moletwo"/></b></font>
                                                    <br>This Certificate is valid for <bean:write name="reportprint"  property="period"/>.  <br>
                                                    Note: This condition is <bean:write name="reportprint"  property="validity"/> , <bean:write name="reportprint"  property="conditionofdisability"/>, <bean:write name="reportprint"  property="conditionimprove"/>  and re-assessment is <bean:write name="reportprint"  property="kindofdisability"/>&nbsp;<bean:write name="reportprint"  property="period"/>.<br><br>


                                                    <div align="right">
                                                        Signature/Thumb impression <br> of person with disability <br><br><br></div>
                                                    </font></td>
                                            <tr valign="top" class="tbl_bg2_content"  > <td align="left" width="33%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td><td align="left" width="33%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td><td align="left" width="30%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td></tr>
                                            <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="firstdoctorname"/></font></td><td align="left" width="30%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="seconddoctorname"/></font></td><td align="left" width="33%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="thirddoctorname"/></font></td></tr>
                                            <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="firstdesgination"/></font></td><td align="left" width="30%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="seconddesgination"/></font></td><td align="left" width="33%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="thirddesgination"/></font></td></tr>
                                            <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="firstdoctornumber"/></font></td><td align="left" width="30%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="seconddoctornumber"/></font></td><td align="left" width="33%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="thirddoctornumber"/></font></td></tr>

                                        </table>

                                       <%--  <center>

                                            <h3><a href="CertificateWithPersoncode.do?print=certificateprint&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" target="_blank">
                                                    Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a href="CertificateWithPersoncode.do?print=idcard&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                                                        ID CARD</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <p > <html:button property="" value="Back" onclick="goBack()" /> </p>
                                                     
                                                    </center> --%>
											 <div class="printhide" style="text-align: center">
													<button type="button" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
													<button type="button" class="btn btn-warning" onclick="window.location='CertificateWithPersoncode.do?print=idcard&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>'"><span class="glyphicon glyphicon-picture"></span> <b> View ID Card</b></button>
											</div>
                                                

                                                </logic:iterate>
                                            </logic:iterate>
                                        </logic:iterate>
                                    </logic:iterate>
                                </logic:notEmpty>





                                <logic:empty name="conditionlist" >

                                    <logic:iterate id="reportprint" name="reportlist" >


                                        <logic:iterate id="subdetails" name="subdetailslist" >
                                            <logic:iterate id="idcarddata" name="Idcardlist" >

                                                <input type="hidden" name="flag" value="finish"/>
                                                <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="90%">
                                                    <%if ("0".equals(apflag)) {%>
                                                    <tr valign="top" class="tbl_bg2_content" align="center">
                                                        <td align="center"><img src="./DisabilityUITG/images/apgovtlogo.JPG" width="100" height="100" ></td>
                                                    </tr>
                                                    <%} else {%>
                                                    <tr valign="top" class="tbl_bg2_content" align="center">
                                                       <td align="center"><img src="./DisabilityUITG/images/tggovtlogo.png" width="100" height="100" ></td>
                                                    </tr>
                                                    <%}%>
                                                </table><
                                                <table  border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="90%">
                                                    <TR>
                                                        <td colspan="3" align="center"><font color="green" size="4"><b>CERTIFICATE FOR PERSON WITH DISABILITY</b></font>
                                                        </td>
                                                    </TR>
                                                    <%if ("0".equals(apflag)) {%>
                                                    <tr>
                                                        <td colspan="3"  align="center"><font color="FF9900" size="4"><b><br> Government of Andhra Pradesh</b></font>
                                                        </td>
                                                    </tr>
                                                    <%} else {%>
                                                    <tr>
                                                        <td colspan="3"  align="center"><font color="FF9900" size="4"><b><br> Government of Telangana</b></font>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                    <tr>
                                                        <td colspan="3" align="center"><font size="2"><b>Medical Board &nbsp;:&nbsp;<bean:write name="reportprint"  property="hospitalname"/>&nbsp;,&nbsp;<bean:write name="reportprint"  property="hospitaladdress"/></b></font>
                                                        </td>
                                                    </tr>
                                                    <tr valign="top" class="tbl_bg2_content">
                                                        <td width="45%" colspan="2">
                                                            <font size="3">
                                                                <div align="left"><b>I.D.No:&nbsp;  <font color="blue"><bean:write name="reportprint"  property="personcode"/></font></div></font>
                                                        </td>
                                                        <td  width="45%">
                                                            <font size="3">
                                                                <div align="center">Date:<font color="blue"><bean:write name="idcarddata"  property="dateofisse"/></font>
                                                                </div>
                                                            </font>
                                                        </td>
                                                    </tr>

                                                    <tr valign="top" class="tbl_bg2_content">
                                                        <td colspan="3">
                                                       <%--  <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"><br><br> --%>
                                                          <img  align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<%=districtname%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                                            <p align="justify"><div align="justify"><font size ="4" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Shri/Smt/Kum: <font color="blue" size="4"><bean:write name="reportprint" property="name"/></font>, <bean:write name="reportprint"  property="relationship"/><font color="blue" size="4"> <bean:write name="reportprint"  property="relationname"/></font>,&nbsp;<font color="blue" size="4"><bean:write name="reportprint"  property="gender"/></font> aged <font color="blue" size="4"><bean:write name="reportprint"  property="age"/></font> years, resident of  H.No.# <font color="blue"><bean:write name="reportprint"  property="housenumber"/></font>, <font color="blue"><%=villagename%></font> Village, <font color="blue"><%=mandalname%> </font>Mandal, <font color="blue"><%=districtname%></font>&nbsp;District,
                                                                    is suffering from permanent/temporary disability of the following category:-
                                                                    <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">

                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes1">
                                                                            <font color="blue" size="3">  </font>

                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes1" /></b></font>
                                                                        </logic:notEmpty>

                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes2" >
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes2" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes3">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes3" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes4" >
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes4" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes5">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes5" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes6">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes6" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes7">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes7" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes8">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes8" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes9">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes9" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes10">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes10" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes11" >
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes11" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes12">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes12" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes13">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes13" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes14">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes14" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="ohsubtypes15">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="ohsubtypes15" /></b></font>

                                                                        </logic:notEmpty>
                                                                        <%--<logic:notEmpty name="condtion1"  property="ohcondition1">
                                                                                                                <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition1" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition2">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition2" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition3">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition3" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition4">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition4" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition5">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition5" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition6">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition6" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition7">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition7" /></b></font>

</logic:notEmpty>
<logic:notEmpty name="condtion1"  property="ohcondition8">
                                        <font color="blue" size="3"><b> <bean:write name="condtion1"  property="ohcondition8" /></b></font>

</logic:notEmpty>--%>
                                                                    </logic:equal>



                                                                    <%--<logic:notEqual name="reportprint" property="loco" value="locomotor">  <font color="blue" size="3">  </font>
                                                                    <%=disabilityreason%></logic:notEqual>.--%>


                                                                    <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">
                                                                        The disability is in relation to <bean:write name="reportprint"  property="genderInitial2"/>
                                                                    </logic:equal>
                                                                    <logic:equal name="reportprint" property="disabilitytype" value="Physically Disabled">
                                                                        <logic:notEmpty name="reportprint"  property="coordinate_lifting">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_lifting" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <%--<logic:notEmpty name="reportprint"  property="coordinate_touching">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_touching" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_eating">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_eating" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_combing">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_combing" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_putting">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_putting" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_ablution">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_ablution" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_buttoning">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_buttoning" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_tie">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_tie" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate_writing">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate_writing" /></b></font>
                                                                       </logic:notEmpty><logic:notEmpty name="reportprint"  property="coordinate">
                                                                           <font color="blue" size="3"><b> <bean:write name="reportprint"  property="coordinate" /></b></font>
                                                                       </logic:notEmpty>--%><logic:notEmpty name="reportprint"  property="hand_stgrip_left">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stgrip_left" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stgrip_right">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stgrip_right" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stpinch_left">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stpinch_left" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="reportprint"  property="hand_stpinch_right">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hand_stpinch_right" /></b></font>
                                                                        </logic:notEmpty><%--<logic:notEmpty name="reportprint"  property="strength">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="strength" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="reportprint"  property="upperExtremity_Total">
                                                                            <font color="blue" size="3"><b> <bean:write name="reportprint"  property="upperExtremity_Total" /></b></font>
                                                                        </logic:notEmpty>--%>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved1">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved1" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved2">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved2" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved3">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved3" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved4">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved4" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved5">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved5" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved6">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved6" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved7">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved7" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved8">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved8" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved9">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved9" /></b></font>
                                                                        </logic:notEmpty>
                                                                        <logic:notEmpty name="subdetails"  property="partInvolved10">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved10" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved11">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved11" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved12">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved12" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved13">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved13" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved14">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved14" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved15">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved15" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved16">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved16" /></b></font>
                                                                        </logic:notEmpty><logic:notEmpty name="subdetails"  property="partInvolved17">
                                                                            <font color="blue" size="3"><b> <bean:write name="subdetails"  property="partInvolved17" /></b></font>
                                                                        </logic:notEmpty>
                                                                        .
                                                                    </logic:equal>

                                                                    <logic:equal name="reportprint" property="disabilitytype" value="Hearing Impairment">
                                                                        Loss of<font color="blue" size="4"> <bean:write name="reportprint"  property="hearing_DB"/>(<bean:write name="reportprint"  property="hearing_DB_In_Words"/>)</font>&nbsp;decibels in the better ear in the conversational range of frequencies .
                                                                    </logic:equal>

                                                                    <logic:notEmpty name="reportprint"  property="global_Disablity">
                                                                        Global Disability Score: <font color="blue" size="3"><b> <bean:write name="reportprint"  property="global_Disablity" />,</b></font>
                                                                    </logic:notEmpty>
                                                                    <%if ("true".equals(causeOfDisabilityFlag)) {%>

                                                                    Cause of Disability
                                                                    <logic:notEmpty name="reportprint"  property="congenital">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="congenital" />,</b></font>
                                                                    </logic:notEmpty><logic:notEmpty name="reportprint"  property="hereditary">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="hereditary" />,</b></font>
                                                                    </logic:notEmpty><logic:notEmpty name="reportprint"  property="birthinjury">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="birthinjury" />,</b></font>
                                                                    </logic:notEmpty><logic:notEmpty name="reportprint"  property="malnutrition">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="malnutrition" />,</b></font>
                                                                    </logic:notEmpty>.<logic:notEmpty name="reportprint"  property="duseaseandinfection">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="duseaseandinfection" />,</b></font>
                                                                    </logic:notEmpty><logic:notEmpty name="reportprint"  property="accident">
                                                                        <font color="blue" size="3"><b> <bean:write name="reportprint"  property="accident" />,</b></font>
                                                                    </logic:notEmpty> <%-- <logic:notEmpty name="reportprint"  property="othercause">
                                                                          <font color="blue" size="3"><b> <bean:write name="reportprint"  property="othercause" /></b></font>
                                                                      </logic:notEmpty>--%>
                                                                    <%}%>


                                                                    <bean:write name="reportprint"  property="genderInitial1"/> is <font color="blue" size="4"><bean:write name="reportprint" property="disabilitytype"/></font><logic:notEqual name="reportprint" property="comment" value="yes">(<%=condition%>)</logic:notEqual>&nbsp;  and has&nbsp;<font color="blue" size="4"><bean:write name="reportprint" property="disabilityPercentage"/></font>%&nbsp; (<bean:write name="reportprint"  property="percentageInWord"/> Percentage)&nbsp;

                                                                    <bean:write name="reportprint"  property="validity"/> &nbsp;<font color="blue" size="4"><bean:write name="reportprint" property="disabilityintellec"/> </font><logic:notEqual name="reportprint" property="disabilitytype" value="Physically Disabled">. </logic:notEqual>
                                                                </p></div>


                                                            </p></div><br>
                                                            Identification Marks:-
                                                            <font color="blue" size="3"><b> 1)<bean:write name="reportprint"  property="moleone"/></b></font>
                                                            <font color="blue" size="3"><b>2) <bean:write name="reportprint"  property="moletwo"/></b></font>

                                                            <br><br><br>This Certificate is valid for <bean:write name="reportprint"  property="period"/>.  <br><br>
                                                            Note: This condition is <bean:write name="reportprint"  property="validity"/> , <bean:write name="reportprint"  property="conditionofdisability"/> , <bean:write name="reportprint"  property="conditionimprove"/> and re-assessment is <bean:write name="reportprint"  property="kindofdisability"/>&nbsp;<bean:write name="reportprint"  property="period"/>.<br><br><br>


                                                            <div align="right">
                                                                Signature/Thumb impression <br> of person with disability <br><br><br></div>
                                                            </font></td>
                                                    <tr valign="top" class="tbl_bg2_content"  > <td align="left" width="33%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td><td align="left" width="33%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td><td align="left" width="30%">Signature&nbsp;&nbsp;&nbsp;&nbsp;:</td></tr>
                                                    <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="firstdoctorname"/></font></td><td align="left" width="30%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="seconddoctorname"/></font></td><td align="left" width="33%"><font color="blue" size="4">Dr. <bean:write name="idcarddata"  property="thirddoctorname"/></font></td></tr>
                                                    <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="firstdesgination"/></font></td><td align="left" width="30%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="seconddesgination"/></font></td><td align="left" width="33%">Designation:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="thirddesgination"/></font></td></tr>
                                                    <tr valign="top" class="tbl_bg2_content"> <td align="left" width="33%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="firstdoctornumber"/></font></td><td align="left" width="30%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="seconddoctornumber"/></font></td><td align="left" width="33%">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<font color="blue" size="4"><bean:write name="idcarddata"  property="thirddoctornumber"/></font></td></tr>

                                                </table>
													<div align="right" style="font-size:15px; padding-right:35px; ">Print Date: <%=Todaydate %></div>
                                                <center>
														
                                                    <%-- <h3><a href="CertificateWithPersoncode.do?print=certificateprint&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" target="_blank">
                                                            Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
														<button class="btn btn-primary">Print</button>
                                                        <h3><a href="CertificateWithPersoncode.do?print=idcard&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                                                                ID CARD</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <p > <html:button property="" value="Back" onclick="goBack()" /> </p>
                                                            </center>

                                                        </logic:iterate>
                                                    </logic:iterate>
                                                </logic:iterate>

                                            </logic:empty>



                                        </logic:notEmpty>
                                        <logic:empty name="reportlist" >
                                            <center ><font color="red" size="2" ><b>No Data</b></font></center>
                                        </logic:empty >
                                        </table>
                                        </form>


                                        </BODY>
                                    </html:html>
