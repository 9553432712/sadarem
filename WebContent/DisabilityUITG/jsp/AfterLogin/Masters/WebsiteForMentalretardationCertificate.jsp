<%--
    Document   : WebsiteForMentalretardationCertificate
    Created on : Nov 24, 2010, 9:54:00 AM
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
            int i = 0;
%>



<%
            String causeOfDisabilityFlag = (String) request.getAttribute("causeOfDisabilityFlag");
            String conditionlistCheckFlag = (String) request.getAttribute("conditionlistCheckFlag");
            String apflag = (String) request.getAttribute("apflag");
%>

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
            <script language="javascript">

            </script>
        </HEAD>
        <BODY >

            <form action="MentalCertificate.do" method="get">
                <% if (i == 0) {%>
                <logic:notEmpty name="reportlist" >
                    <logic:iterate id="reportprint" name="reportlist" >
                        <input type="hidden" name="flag" value="finish"/>
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
                                            <%}%>   <%if ("0".equals(apflag)) {%>
                                    <tr>
                                        <td align="center" colspan="2"><img src="<%=request.getContextPath()%>/images/ap_govt_logo.png"></td>
                                    </tr>
                                    <tr>
                                        <td class="labelGreen" align="center" colspan="2">Government of Andhra Pradesh</td>
                                    </tr>
                                    <%} else {%>
                                    <tr>
                                         <td align="center" colspan="2"><img src="./DisabilityUITG/images/tggovt.png"></td>
                                    </tr>
                                    <tr>
                                        <td class="labelGreen" align="center" colspan="2">Government of Telangana</td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                        <td align="center" class="labelGreen" colspan="2">CERTIFICATE FOR PERSON WITH DISABILITY</td>
                                    </tr>
                                    <tr>
                                        <td align="center" class="labelGreen" colspan="2"><font size="1">(Issued under the authority vide G.O.Ms.No.31, WD CW & DW Dept.Dated 01.12.2009)</font></td>
                                    </tr>
                                    <tr>
                                        <td class="columnHeight10" colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td width="80%" valign="bottom"><table>
                                                <tr>
                                                    <td height="22" class="label"><strong>Medical Board :</strong></td>
                                                    <td height="22" class="label"><font color="blue">
                                                            <bean:write name="reportprint"  property="hospitalname"/>,&nbsp;&nbsp;<bean:write name="reportprint"  property="hospitaladdress"/></font>                                  </td>
                                                </tr>
                                                <tr>
                                                    <td height="22" class="label"><strong>ID No.of Person with Disability :</strong></td>
                                                    <td height="22" class="label"><font color="blue">
                                                            <bean:write name="reportprint"  property="personcode"/></font>                                  </td>
                                                </tr>
                                                <tr>
                                                    <td height="22" class="label"><strong> Date of Issue :</strong></td>
                                                    <td height="22" class="label"><font color="blue">
                                                            <bean:write name="reportprint"  property="dateofisse"/></font> </td>
                                                </tr>
                                            </table></td>
                                        <td width="20%" align="right"><table>
                                                <tr>
                                                    <td><img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
                                                    </td>
                                                </tr>
                                            </table></td>
                                    </tr>
                                    <tr>
                                        <td class="columnHeight10" colspan="2"></td>
                                    </tr>

                                    <tr>
                                        <td colspan="2" align="center" valign="top"><table class="table_three" width="98%">
                                                <tr>
                                                    <td  align="left" class="label" style="line-height:20px;">
                                                        <ul>
                                                            <li>
                                                                This is certified that <bean:write name="reportprint"  property="maritialstatus"/>
                                                                <font color="blue"><bean:write name="reportprint"  property="name"/></font>,
                                                                <bean:write name="reportprint"  property="relationship"/>
                                                                <font color="blue"><bean:write name="reportprint"  property="relationname"/></font>,
                                                                <font color="blue"><bean:write name="reportprint"  property="gender"/></font>, age
                                                                <font color="blue"><bean:write name="reportprint"  property="age"/></font> years,
                                                                <logic:notEmpty name="reportprint" property="housenumber">
                                                                    resident of H.No.# <font color="blue"><bean:write name="reportprint"  property="housenumber"/></font>,
                                                                </logic:notEmpty>
                                                                <font color="blue"><bean:write name="reportprint"  property="habitation_name"/></font> Habitation,
                                                                <font color="blue"><bean:write name="reportprint"  property="village_name"/></font> Village,
                                                                <font color="blue"><bean:write name="reportprint"  property="mandal_name"/> </font>Mandal,
                                                                <font color="blue"><bean:write name="reportprint"  property="district_name"/></font>
                                                                District, is suffering from <bean:write name="reportprint"  property="validity"/>
                                                                disability of the following category:-
                                                                <br><font color="blue"><bean:write name="reportprint" property="disabilitytype"/></font>
                                                                <logic:notEmpty name="reportprint"  property="disabilitycondition">
                                                                    <font color="blue">(<bean:write name="reportprint" property="disabilitycondition"/>)</font>.
                                                                </logic:notEmpty>

                                                                <logic:notEmpty name="reportprint" property="mentalRetardation_IQ">
                                                                    <br>  IQ= <font color="blue" ><bean:write name="reportprint"  property="mentalRetardation_IQ"/></font>(<bean:write name="reportprint"  property="mentalRetardation_IQ_Inwords"/>)&nbsp;
                                                                </logic:notEmpty>
                                                                <%if ("true".equals(causeOfDisabilityFlag)) {%>
                                                                <br><br>Cause of Disability :
                                                                <font color="blue" > <bean:write name="reportprint"  property="totalcauseofdisabilities" /></font>
                                                                <%}%>
                                                                <logic:notEmpty name="reportprint" property="disabilityreason">
                                                                    <br>
                                                                    <font color="blue" > <bean:write name="reportprint"  property="disabilityreason" />.</font>
                                                                </logic:notEmpty>

                                                            </li>


                                                            <li>Re-assessment of this case is
                                                                <font color="blue"><bean:write name="reportprint"  property="kindofdisability"/>&nbsp;<bean:write name="reportprint"  property="period"/></font>
                                                            </li>
                                                            <li>
                                                                Percentage of disability in
                                                                <bean:write name="reportprint"  property="genderInitial2"/> case is
                                                                &nbsp;<font color="blue" ><bean:write name="reportprint" property="disabilityPercentage"/></font>%&nbsp;
                                                                [<bean:write name="reportprint"  property="percentageInWord"/>].
                                                            </li>
                                                            <%if ("true".equals(conditionlistCheckFlag)) {%>

                                                            <li>
                                                                <bean:write name="reportprint"  property="genderInitial1"/>
                                                                meets the following physical requirements for discharge of <bean:write name="reportprint"  property="genderInitial2"/> duties.
                                                                <font color="blue"><bean:write name="reportprint"  property="mentalRetardationPhysicalRequirementList" /></font>

                                                                <%}%>
                                                            <li>
                                    	Identification Marks of Person with Disability:- <br />
                                                                <font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a)
                                                                    <bean:write name="reportprint"  property="moleone"/>
                                                                </font>. <br />
                                                                <font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)
                                                                    <bean:write name="reportprint"  property="moletwo"/>
                                                                </font>.                                    </li>
                                                        </ul>                                  </td>
                                                </tr>

                                            </table></td>
                                    </tr>
                                    <tr>
                                        <td class="columnHeight10" colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="label" align="right"> Signature/Thumb impression <br />
                                            of Person with Disability </td>
                                    </tr>
                                    <tr>
                                        <td class="columnHeight12" colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><table class="table_three">
                                                <tr>
                                                    <td width="33%" align="left" class="label"><strong>Signature</strong></td>
                                                    <td width="33%" align="left" class="label"><strong>Signature</strong></td>
                                                    <td width="30%" align="left" class="label"><strong>Signature</strong></td>
                                                </tr>
                                                <tr>
                                                    <td align="left" width="33%" class="label"><font color="blue">
                                                            <bean:write name="reportprint"  property="firstdoctorname"/>
                                                        </font> </td>
                                                    <td align="left" width="30%" class="label"><font color="blue">Dr.
                                                            <bean:write name="reportprint"  property="seconddoctorname"/>
                                                        </font> </td>
                                                    <td align="left" width="33%" class="label"><font color="blue">Dr.
                                                            <bean:write name="reportprint"  property="thirddoctorname"/>
                                                        </font> </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" width="33%" class="label"> Designation:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="firstdesgination"/>
                                                        </font> </td>
                                                    <td align="left" width="30%" class="label"> Designation:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="seconddesgination"/>
                                                        </font> </td>
                                                    <td align="left" width="33%" class="label"> Designation:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="thirddesgination"/>
                                                        </font> </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" width="33%" class="label"> Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="firstdoctornumber"/>
                                                        </font> </td>
                                                    <td align="left" width="30%" class="label"> Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="seconddoctornumber"/>
                                                        </font> </td>
                                                    <td align="left" width="33%" class="label"> Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; <font color="blue">
                                                            <bean:write name="reportprint"  property="thirddoctornumber"/>
                                                        </font> </td>
                                                </tr>
                                            </table>
                                            <br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label" style="font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10px; color:#FF0000;">Note: This is not valid for Medico-Legal cases.</td>
                                    </tr>
                                </table></td>
                            <td width="49" background="./DisabilityUITG/images/cert-rgt-bg.png">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="49"><img src="./DisabilityUITG/images/cert-btm-lft.png" width="49" height="49"></td>
                            <td background="./DisabilityUITG/images/cert-btm-bg.png">&nbsp;</td>
                            <td width="49"><img src="./DisabilityUITG/images/cert-btm-rgt.png" width="49" height="49"></td>
                        </tr>
                    </table>
                </logic:iterate>
            </logic:notEmpty>
            <%i = 1;%>
            <%}%>
            <logic:empty name="reportlist" >
                <center ><font color="red" size="2" ><b>No Data</b></font></center>
            </logic:empty >



        </form>
    </BODY>
</html:html>

