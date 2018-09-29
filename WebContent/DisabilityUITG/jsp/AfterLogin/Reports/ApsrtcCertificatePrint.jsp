<%-- 
    Document   : ApsrtcCertificate
    Created on : Aug 8, 2014, 5:39:20 PM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String personCode = null;String distname=null;
            if (request.getAttribute("personCode") != null) {
                personCode = request.getAttribute("personCode").toString();
            }
             if (request.getAttribute("distName") != null) {
                distname = request.getAttribute("distName").toString();}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
        <style type="text/css">
            p.solid
            {
                border:3px solid green;
            }
            p.line{
                line-height:0.5;}
            .label_small
            {
                font-family      : Verdana,Helvetica,Arial,Geneva,Swiss,sunsans-Regular;
                font-size        : 12px;
                font-weight      : Normal;
                color            : Black;
            }.label_smal
            {
                font-family      : Verdana,Helvetica,Arial,Geneva,Swiss,sunsans-Regular;
                font-size        : 11px;
                font-weight      : Normal;
                color            : Black;
            }

            .table_three {

                height: 5%;
                width: 90%;
            }.table_thre {


                width: 95%;
            }.tbl_bgbg
            {
                font-family      : Verdana,Helvetica,Arial,Geneva,Swiss,sunsans-Regular;
                font-size        : 10px;
                font-style       : normal;
                font-variant     : normal;
                font-weight      : normal;

            }
        </style>

    </head>
    <body>
        <html:form action="/apsrtcCertificate.do?mode=certificatePrint">
            <input type="hidden" name="personCode"  value="<%=personCode%>"/>
            <table  class="tbl_bgbg" width="90%" border="0" cellspacing="5" cellpadding="0" align="center" style=" border:2px #666 solid;border-color:green;">
                <tr>
                    <td colspan="2" style="text-align: right;font-size:9px; line-height: 17px; font-family:Verdana; font-weight: bold;">
                        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; RTC - 282/R (Bio-Data)</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table align="center">

                            <tr>
                                <td>
                                    <center>
                                        <img src="./DisabilityUITG/images/apsrtclogo.jpg">

                                    </center>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font size="2"><b>ANDHRA PRADESH STATE ROAD TRANSPORT CORPORATION</b></font>
                                </td>

                            </tr>
                            <tr>
                                <td style="font-size:15px; line-height: 17px; font-family:Verdana; font-weight: bold; text-align:left;">
                                    <CENTER> <font ><b>BIO-DATA FORM</b></font></CENTER>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:12px; line-height: 17px; font-family:Verdana; font-weight: bold; text-align:left;">
                                    <CENTER>  (For PHC)</CENTER>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td >
                        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  APPLICATION NO.<br/>    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<b><bean:write property="applicationNo" name="ApsrtcCertificateForm"/></b>
                    </td>
                    <td colspan="4">
                        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  ID NO.<br/> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;  <b><bean:write name="ApsrtcCertificateForm" property="idNo" /></b><br/>(To be filled in by RTC official)
                    </td>

                </tr>
                <tr>

                </tr>
                <tr></tr> <tr></tr>
                <tr>
                    <td>
                        <table class="tbl_bgbg" width="90%" border="0" cellspacing="5" cellpadding="0" align="center" >
                            <tr>
                                <td>
                                    1.  &nbsp;  NAME (Maximum 16 characters):&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="name"/></b>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    2.  &nbsp; Father's/ Mother's Name (Maximum 16 characters):&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="fatherName"/></b>
                                </td>
                            </tr>
                            <tr></tr>
                            <tr>
                                <td>
                                    3.  &nbsp; Date Of Birth(DD/MM/YYYY):&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="dob"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>  4.  &nbsp; Sex:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="gender"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td colspan="4">
                                    5.  &nbsp; Residential Address:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="address"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>
                                    6.  &nbsp; Type Of Pass:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="typeOfPass"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>
                                    7.  &nbsp; PROOF ENCLOSED:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="proofEnclosed"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>
                                    8.  &nbsp; Pass type eligibility:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="passTypeEligibility"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>
                                    9.   &nbsp; Type of Disability for PHC:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="typeOfDisability"/></b>
                                </td>
                            </tr>   <tr></tr>
                            <tr>
                                <td>
                                    10. Degree of disability for PHC:&nbsp; <b><bean:write name="ApsrtcCertificateForm" property="degreeOfDisability"/></b>
                                </td>
                                <td>
                                    11.&nbsp;Escort&nbspfor&nbspPHC:&nbsp;<b><bean:write name="ApsrtcCertificateForm" property="escortForPhc"/></b>
                                </td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>
                            <td>
                                <table align="right">
                                    <tr>
                                        <td>
                                            <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="ApsrtcCertificateForm"  property="districtName"/>/<bean:write name="ApsrtcCertificateForm"  property="sadaremId"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
                                        </td>
                                    </tr>
                                </table>

                            </td>
                        </table>
                    </td>
                </tr>
                <tr></tr> <tr></tr> <tr></tr> <tr></tr>
                <tr>
                    <td style="text-indent: inherit">
                        &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I here by declare that the particulars given above are true and correct. I will abide by the
                        rules & regulations of APSRTC governing Issue of Bus Passes.
                    </td>
                </tr>
                <tr></tr> <tr></tr> <tr></tr> <tr></tr>
                <tr>
                    <td>
                        Date:
                        <br/>
                        Place:
                    </td>
                    <td colspan="">
                        <b>Signature&nbsp;of&nbsp;the&nbsp;Applicant </b>
                    </td>
                </tr>
                <tr></tr> <tr></tr> <tr></tr> <tr></tr> <tr></tr>

                <tr>
                    <td  style="text-decoration: underline;text-align: center;font-size:12px;font-weight: bold;">
                        For Office Use Only
                    </td>
                </tr>
                <tr></tr> <tr></tr>
                <tr>
                    <td>
                        MR No./Ack.Receipt No.:<b><bean:write name="ApsrtcCertificateForm" property="ackReceiptNo"/></b>
                    </td>
                    <td>
                        Receipt&nbsp;Date:<b><bean:write name="ApsrtcCertificateForm" property="receiptDate"/></b>
                    </td>
                </tr>
                <tr>
                    <td>
                        Bus Pass Ticket No:<b><bean:write name="ApsrtcCertificateForm" property="busPassTicketNo"/></b>
                    </td>
                    <td>
                        Pass&nbsp;Amount&nbsp;Rs:<b><bean:write name="ApsrtcCertificateForm" property="passAmmountRs"/></b>
                    </td>
                </tr>
                <tr>
                    <td>
                        Pass Issue Of:<b><bean:write name="ApsrtcCertificateForm" property="passIssuedOf"/></b>
                    </td>
                </tr>
                <tr></tr> <tr></tr> <tr></tr> <tr></tr>

                <tr>
                    <td>
                        <b>Signature of the issuing Authority.</b><br/><br/>
                        Name:<br/><br/>
                        Design:<br/><br/>
                        Date:<br/><br/>
                    </td>
                    <td>
                        <b> Signature&nbsp;of&nbsp;the&nbsp;Physical&nbsp;Verifying&nbsp;Authority. </b><br/><br/>
                        Name:<br/><br/>
                        Design:        &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Staff No.<br/><br/>
                        Date:<br/><br/>
                    </td>
                </tr>

                <tr></tr> <tr></tr> <tr></tr> <tr></tr> <tr></tr>
                <tr></tr> <tr></tr> <tr></tr>
                <tr>
                    <td align="center" valign="middle">
                </tr>
                <tr>
                    <td align="left">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td style="font-size:9px; line-height: 18px; font-family:Verdana; font-weight: normal;"><br>
  				Person with Disability ID No: <span style="font-size:10px; font-weight:bold; color:#00F;"><%=personCode%></span><br>
  				The Authenticity of this certificate can be verified at <span style="font-size:10px; font-weight:bold; color:#00F;"> www.sadarem.telangana.gov.in</span><br>
  				The ID Number is unique to Person with Disability in Andhra Pradesh
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr></tr> <tr></tr>
                <tr>
                    <td>
                        <table align="center">
                            <tr>
                                <td>
                                    <a href="./apsrtcCertificate.do?mode=certificatePrint&personcode=<%=personCode%>&disrictName=<%=distname%>" target="_blank">
                                        Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>


        </html:form>
    </body>
</html>
