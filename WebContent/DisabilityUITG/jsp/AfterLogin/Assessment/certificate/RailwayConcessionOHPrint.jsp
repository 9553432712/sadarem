<%-- 
    Document   : RailwayConcessionOHPrint
    Created on : Mar 28, 2012, 4:15:17 PM
    Author     : 490058
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
            String dn=null;String rn=null;
          //   String dn=(String)request.getSession().getAttribute("dn");

  //String rn=(String)request.getSession().getAttribute("rn");



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
            String causeOfDisabilityFlag = (String) request.getAttribute("causeOfDisabilityFlag");
            String conditionlistCheckFlag = (String) request.getAttribute("conditionlistCheckFlag");
             String personcode = (String) session.getAttribute("personcode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <HEAD>

        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
        <style type="text/css">
            .tbl_bgbg
{
font-family      : Verdana,Helvetica,Arial,Geneva,Swiss,sunsans-Regular;
font-size        : 10px;
font-style       : normal;
font-variant     : normal;
font-weight      : normal;


}
        </style>

    </HEAD>
    <BODY onLoad="window.print()">
        <form action="MentalCertificate.do" method="get">
           <logic:notEmpty name="reportlist">
                <logic:iterate id="reportprint" name="reportlist" >
                    <input type="hidden" name="flag" value="finish"/>
<table  class="tbl_bgbg" width="90%" border="0" cellspacing="5" cellpadding="0" align="center" style="border:2px #666 solid;border-color:green;">
 <!-- <tr>
      <td align="center"><img src="./DisabilityUITG/images/IND-LOGO.gif" width="65" height="87" ></td>
  </tr>-->
 <tr><td>&nbsp;</td></tr>
    <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="150px" align="center" valign="middle">
        <%-- <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
         <img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<bean:write name="reportprint"  property="district_name"/>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/> 
        </td>
        <td width="570px" align="center" valign="top">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
              <td align="left" valign="middle" style="padding-left: 24px;">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td align="center" style="font-size:12px; font-weight:bold; color:#008000; line-height:21px;">
                            Appendix No.1/36 <br>
                          (See Rule No:101, Serial No:25)<br>
                          <span style="font-size:18px;">CONCESSION CERTIFICATE</span>
                        </td>
                    </tr>
                </table>
            </td>
          </tr>
          <tr>
              <td align="right" valign="middle" style="padding-top:10px;"><table cellpadding="0" cellspacing="0" border="0">
              <tr>
                <td style="font-size:10px; line-height: 17px; font-family:Verdana; font-weight: bold; text-align:left;">Form for the purpose of grant of
                  rail concession<br>to Orthopaedically Handicapped/Paraplegic<br>persons/patients to be issued by the<br>
                  Government Doctor. </td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="left" valign="middle" style="font-size:10px; line-height: 18px; font-family:Verdana; font-weight: normal;">
    	This is to certify that <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="maritialstatus"/> <bean:write name="reportprint"  property="name"/>
    	</span>, Whose Particulars are furnished below, is a bonafide Orthopaedically Handicapped/ Paraplegic person/ Patient and <strong>CANNOT TRAVEL WITHOUT THE ASSISTANCE OF AN ESCORT.</strong>
   </td>
  </tr>
  <tr>
  	<td align="left" valign="middle" style="font-size:11px; line-height: 18px; font-family:Verdana; font-weight: bold;">
    	Particulars of the Orthopaedically Handicapped/paraplegic person/patient :
        <ol style="list-style-type:lower-alpha; font-size:10px; font-weight:normal; line-height: 16px;">
        	<li>
            	Address : <logic:notEmpty name="reportprint" property="housenumber"> H.No.# <span style="font-size:10px; font-weight:bold; color:#00F;">
            	<bean:write name="reportprint"  property="housenumber"/></span>, </logic:notEmpty>
                                                <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="habitation_name"/> </span> Habitation,
                                                <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="village_name"/> </span> Village,
                                                <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="mandal_name"/> </span>  Mandal,
                                                <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="district_name"/></span> District.
            </li>
            <li>
            	Father's/ Husband's Name :
            	  <bean:write name="reportprint"  property="relationship"/><span style="font-size:11px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="relationname"/></span>
            </li>
            <li>Age : <span style="font-size:11px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="age"/></span> years</li>
            <li>Sex : <span style="font-size:11px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="gender"/></span></li>
            <li>Nature of Handicap (To be written by doctor whether the disability is Temporary or Permanent): <span style="font-size:11px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="validity"/></span></li>
            <li>Causes of loss of Functional capacity:
                                            <%if ("true".equals(causeOfDisabilityFlag)) {%>
                                            <span style="font-size:11px; font-weight:bold; color:#00F;"> <bean:write name="reportprint"  property="totalcauseofdisabilities" /></span>
                                            <%}%></li><br><br>
            <li> Signature or Thumb impression of Orthopaedically <br>handicapped/ paraplegic person/patient: <br>(not
                         necessary for those whose both hands <br>are missing or non-funtional).</li>
        </ol>
    </td>
  </tr>
  <tr>
    <td align="left" valign="middle">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="50%" align="left" valign="top" style="font-size:10px; font-weight:bold; line-height:18px;">
            	Place : <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="hospitaladdress"/></span><br>
                Date : <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="dateofisse"/></span><br>
                <span style="font-size:10px; font-weight:normal; font-family:Verdana, Geneva, sans-serif;">Clear seal of Government Hospital/Clinic</span>
            </td>
            <td width="50%" align="right" valign="top" style="font-size:10px; line-height:18px; font-weight:bold; ">
            	Signature of Government Doctor<br>
                               <%if(dn!=null){%>
                <%=dn%>
                <%}else { %>
                <span style="font-size:10px; font-weight:bold; color:#00F;">  <bean:write name="reportprint"  property="firstdoctorname"/></span><%}%><br>
                 Regn.No :
				 <%if(rn!=null){%>
                 <%=rn%>
                 <%}else { %>
                 <span style="font-size:10px; font-weight:bold; color:#00F;"><bean:write name="reportprint"  property="firstdoctornumber"/></span><%}%><br>
                <span style="font-size:10px; font-weight:normal; font-family:Verdana, Geneva, sans-serif;">Seal containing full name and <br>Regd.No. of the Doctor</span>
            </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="middle" style="font-size:9px; line-height: 14px; font-family:Verdana; font-weight: normal;">
    	Strike out where not applicable.<br>* Note :-
        <ol>
        	<li>This certificate should be issued only to those Orthopaedically Handicapped/  paraplegic persons/ patients <B> WHO CANNOT TRAVEL WITHOUT THE ASSISTANCE OF AN ESCORT.</B> The photo must be signed and stamped in such a way that Doctor's signature and stamp appears partly on the photo and partly on the certificate.</li>
            <li>In the case of temporary disability, the certificate will be valid for five years from the date of
                                issue. In the case of permanent disability, the certificate will remain valid for
                                <strong>(1)</strong> five years, in case of persons upto the age of 25 years,
                                <strong>(2)</strong> ten years, in case of persons in the age group of 26 to 35 years and
                                <strong>(3)</strong> in case of persons above the age of 35 years, the certificate will remain valid for whole life of the
                                concerned person. After expiry of the period of the validity of the certificate, the person is
                                required to obtain a fresh certificate. A photo copy of this certificate is accepted for the purpose of grant concession the original certificate will have to be
                                produced for inspection at the time of purchase of concessional
                                ticket and during the journey, if demanded</li>
            <li>No alteration in the form is permitted.</li>

        </ol>
    </td>
  </tr>
  <tr>
    <td align="left" valign="middle" style="border-top:1px #333 dashed;">
    	<table cellpadding="0" cellspacing="0" border="0">
        	<tr>
            	<td style="font-size:9px; line-height: 18px; font-family:Verdana; font-weight: normal;"><br>
  				Person with Disability ID No: <span style="font-size:10px; font-weight:bold; color:#00F;"><%=personcode%></span><br>
  				The Authenticity of this certificate can be verified at <font color="blue"> www.sadarem.telangana.gov.in</font><br>
  				The ID Number is unique to Person with Disability in Telangana
                </td>
  			</tr>
  		</table>
    </td>
  </tr>
</table>

                </logic:iterate>
            </logic:notEmpty>


<% request.getSession().removeAttribute("dn");request.getSession().removeAttribute("rn");
%>

            <logic:empty name="reportlist">
                <center><font color="red" size="2" ><b>No Data</b></font></center>
            </logic:empty>
        </form>
    </BODY>
</html:html>