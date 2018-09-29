<%@page contentType="text/html"%>
<%@page import="com.tcs.sadarem.util.CommonUtility"  pageEncoding="UTF-8"%>
<%@taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<% 
    String lingualTeluguName="&#3114;&#3143;&#3120;&#3137;&#9;&#9;&#9;&#9;&#9;&#9";
    String lingualTeluguSex="&#3122;&#3135;&#3074;&#3095;&#3118;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
    String lingualTeluguAddress="&#3098;&#3135;&#3120;&#3137;&#3112;&#3134;&#3118;&#3134;&#9;&#9;&#9;&#9;&#9;&#9;";
    String telugupersonname=(String)request.getAttribute("telugupersonname");
    String telugufathername=(String)request.getAttribute("telugufathername");
    String gender=(String)request.getAttribute("gender");
    String father="&#3108;&#3074;&#3105;&#3149;&#3120;&#3135;";
    String gardian="&#3128;&#3074;&#3120;&#3093;&#3149;&#3127;&#3093;&#3137;&#3105;&#3135; &#3114;&#3143;&#3120;&#3137; ";
    String dateofissue="&#3100;&#3134;&#3120;&#3135; &#3098;&#3143;&#3128;&#3135;&#3112; &#3108;&#3143;&#3110;&#3135;";
    String dateofbirth="&#3114;&#3137;&#3103;&#3149;&#3103;&#3135;&#3112; &#3108;&#3143;&#3110;&#3135;";
    String mole="&#3095;&#3137;&#3120;&#3149;&#3108;&#3135;&#3074;&#3114;&#3137; &#3098;&#3135;&#3129;&#3149;&#3112;&#3134;&#3122;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
    String validdate="&#3093;&#3134;&#3122;&#3114;&#3120;&#3135;&#3118;&#3108;&#3135;";
    String disabilityduration =(String) request.getAttribute("disabilityduration");
    String age="&#3125;&#3119;&#3128;&#3149;&#3128;&#3137;&#9;&#9;&#9;&#9;&#9;&#9; ";
    String certificatewithidcard=(String)request.getParameter("certificatewithidcard");
    String lingualTeluguTypeofDisability="&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;";
    String disabilitypercentage="&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125; &#3126;&#3134;&#3108;&#3074;";
    String disabilitytypeInTelugu=(String)request.getAttribute("disabilitytypeInTelugu");
    String teluguDisablityName =(String)session.getAttribute("teluguDisablityName");
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
<!DOCTYPE>
<br>
<html:html>
      <%String selectFlow = "OUTERPROCESS";
                    if (request.getAttribute("selectedValue") != null) {
                        selectFlow = request.getAttribute("selectedValue").toString();
               }
String apflag = (String) request.getAttribute("apflag");%>
    <HEAD>
    <title>:: SADAREM :: ID Card</title>
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
<script language="javascript">
 function goBack()
{
    
            document.forms[0].action="CalculationForwardAction.do?selectedValue=<%=selectFlow%>";
            document.forms[0].submit();
}
        </script>
    </HEAD>
    <BODY >
        <form action="MentalCertificate.do">
             <input type="hidden" name="selectedValue" value="<%=selectFlow%>"/>
    <logic:iterate id="reportprint" name="reportlist" >
 <table  width="90%" align="center" border="0" style="height: 431px">
         <tr>
             <td width="49%" style="vertical-align: top;">
                 <table border="1" width="100%" style="height: 431px;">
                 <tr>
                  <td width="100%" style="padding-left:5px;">
                      <table border="0" align="center" width="100%" >
                     <tr> 
                           <%
                           if ("0".equals(apflag)) 
                           {
                           %>
                           	<td><img src="./DisabilityUITG/images/govtlogo.JPG"></td>
                         		 <td class="idcardLabels" style="text-align: center">
                              <span style="color:green !important; font-size: 12px;">Government of Andhra Pradesh<br>IDENTITY CARD FORPERSON WITH DISABILITY</span>
                          	</td>
                            <%
                            }
                           else
                           {
                           %>
                           	<td> <img src="./DisabilityUITG/images/tg_govtlogo.png" ></td> 
                           <td class="idcardLabels" style="text-align: center">
                               <span style="color:green !important; font-size: 12px;">Government of Telangana<br>IDENTITY CARD FORPERSON WITH DISABILITY</span>
                           </td>
                            <%
                            }
                            %>  
                      </tr>
	                 	 <%
	                    if("4".equals(categoryId))
	                    {
				        %> 
                          <tr>
                              <td align="center" class="labelAppelate" colspan="2"><font size="1">(Appellate Authority Assessment)</span></td>
                          </tr>
                       <%
                       }
	                 	 %>
                     <tr>
                     <td colspan="2"  class="idcardLabels" align="center">
                         Medical Board of <bean:write name="reportprint"  property="hospitalname"/>,
                    	 <bean:write name="reportprint"  property="hospitaladdress"/></td>
                     </tr>
                     </table>
                 </td>
                 </tr>
                 <tr>
                 <td  style="padding-left: 5px;text-align: left" width="100%">
                     <table border="0" align="center" width="100%" >
                     <tr>
                     <td class="idcardLabels">
                     	ID No: <span style="color: blue !important; "><bean:write name="reportprint"  property="personcode"/></span>
                     </td>
                     <td rowspan="3" align="right" valign="top">
<%--                         <img src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="60" height="60" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
               			 <img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<bean:write name="reportprint"  property="district_name"/>&randiomid=<%=Math.random()%>"  width="60" height="60" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                     </td>
                     </tr>
                     <tr>
                         <td class="idcardLabels">Name (<%=lingualTeluguName%>) : <span style="color: blue !important;"> <bean:write name="reportprint"  property="name"/><br>(<%=telugupersonname%>)</span>
                     </td>
                     </tr>
                     <tr>
                         <td class="idcardLabels">Father(<%=father%>)/<br> Gaurdian Name(<%=gardian%>): <span style="color: blue !important; "><bean:write name="reportprint"  property="relationname"/> &nbsp;&nbsp;(<%=telugufathername%>)</span>
                     </td>
                     </tr>
                     <tr>
                         <td class="idcardLabels" colspan="2">Date of Issue(<%=dateofissue%>) : <span style="color: blue !important; "> <bean:write name="reportprint"  property="dateofisse"/></span>
                     </td>
                     </tr>
                     <tr>
                         <td class="idcardLabels" colspan="2">Valid Upto(<%=validdate%>): <span style="color: blue !important; "> <bean:write name="reportprint"  property="period"/>&nbsp;(<%=disabilityduration%>)</span>
                     </td>
                     </tr>
                     <tr>
                     <td class="idcardLabels" colspan="2">Date of Birth (<%=dateofbirth%>): <span style="color: blue !important; "> <bean:write name="reportprint"  property="dateofbirth"/></span>
                     </td>
                     </tr>
                     <tr>
                     <td class="idcardLabels" colspan="2">Age (<%=age%>): <span style="color: blue !important; "> <bean:write name="reportprint"  property="age"/></span>
                     </td>
                     </tr>
                     <tr>
                     <td class="idcardLabels" colspan="2">Sex&nbsp;&nbsp;(<%=lingualTeluguSex%>) : <span style="color: blue !important; "><bean:write name="reportprint"  property="gender"/>&nbsp;&nbsp;(<%=gender%>)</span>
                     </td>
                     </tr>
                     <tr>
                         <td class="idcardLabels" colspan="2">Address (<%=lingualTeluguAddress%>) : #<span style="color: blue !important; " dir="LTR">
                                 <logic:notEmpty name="reportprint" property="housenumber">
                                     <bean:write name="reportprint"  property="housenumber"/>,</logic:notEmpty> <bean:write name="reportprint"  property="habitation_name"/>, <br>
                          <bean:write name="reportprint"  property="village_name"/>,<bean:write name="reportprint"  property="mandal_name"/>,
                          </span><br><span style="color: blue !important; " dir="LTR"><bean:write name="reportprint"  property="district_name"/>.</span>
                     </td>
                     </tr>
                     <tr>
                     <td class="idcardLabels" colspan="2">Identification Marks (<%=mole%>) :
                     <br>1. <span style="color: blue !important; "> <bean:write name="reportprint"  property="moleone"/>.</span>
                     <br>2. <span style="color: blue !important; "> <bean:write name="reportprint"  property="moletwo"/>.</span>
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
         <td width="2%">&nbsp;</td>
         <td width="49%" valign="top">
          <table border="1" align="center" width="100%" style="height: 431px">
        <tr>
       <td width="100%" style="padding-left: 5px;">
	        <table border="0" align="center" width="100%" >
	        <tr>
	        <td class="idcardLabels">Nature of Disability (<%=lingualTeluguTypeofDisability%> ): <span style="color: blue !important; " ><bean:write name="reportprint"  property="validity"/></span>&nbsp;&nbsp;<span style="color: blue !important; "><bean:write name="reportprint"  property="disabilityTypeInIdCard"/> <br>(<%=teluguDisablityName%> &nbsp; <%=disabilitytypeInTelugu%>)</span>
	        </td>
	        </tr>
	        <tr>
	        <td class="idcardLabels">Percentage of Impairment (<%=disabilitypercentage%>): <span style="color: blue !important; "><bean:write name="reportprint"  property="disabilityvalue"/>%</span>
	         </td>
	         </tr>
	         </table>
         </td>
         </tr>
         <tr>
        <td width="100%" style="padding-left: 5px;">
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
           <td  class="idcardLabels">Name : <span style="color: blue !important; ">
                   <bean:write name="reportprint"  property="firstdoctorname"/></span>
            </td>
            </tr>
            <tr>
            <td  class="idcardLabels">Designation : <span style="color: blue !important; ">
                    <bean:write name="reportprint"  property="firstdesgination"/></span>
            </td>
            </tr>
            <tr>
            <td  class="idcardLabels">Registration No : <span style="color: blue !important; ">
                    <bean:write name="reportprint"  property="firstdoctornumber"/></span>
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
           <td class="idcardLabels">Name : <span style="color: blue !important; ">
                   Dr. <bean:write name="reportprint"  property="seconddoctorname"/></span>
            </td>
            </tr>
             <tr>
             <td  class="idcardLabels">Designation : <span style="color: blue !important; ">
                     <bean:write name="reportprint"  property="seconddesgination"/></span>
             </td>
             </tr>
             <tr>
             <td  class="idcardLabels">Registration No : <span style="color: blue !important; " >
                     <bean:write name="reportprint"  property="seconddoctornumber"/></span>
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
              <td  class="idcardLabels">Name : <span style="color: blue !important; " >
                      Dr. <bean:write name="reportprint"  property="thirddoctorname"/></span>
              </td>
              </tr>
              <tr>
              <td class="idcardLabels">Designation : <span style="color: blue !important; ">
                      <bean:write name="reportprint"  property="thirddesgination"/></span>
              </td>
              </tr>
              <tr>
              <td class="idcardLabels">Registration No : <span style="color: blue !important; " >
                      <bean:write name="reportprint"  property="thirddoctornumber"/></span>
             <br>
             </td>
             </tr>
         </table>
            </td>
            </tr>
            <tr>
            <td class="idcardLabels"  width="100%" style="padding-left: 5px;">
            <div align="justify"> 
             <u>NOTE</u> :-
				<br><br> 1. This card is valid for claiming Air / Bus /  Rail  Concession and benefits  sanctioned for eligible disabled persons,  by authorities concerned / Government of  <%if ("0".equals(apflag)) {%>
                                            A.P.
                                            <%} else {%>
                                            T.S.
                                            <%}%> <br>
                    <br><br>2. All particulars, except disability and degree of disability, are based on information given by I-card holder.
                </div></td>
            </tr>
        </table>
      </td>
         </tr>
         <tr>
         	<td colspan="3" style="text-align: right">
         		 Printed Date : <%=CommonUtility.getDateTime("dd-MMM-yyyy HH:mm") %> 
         	</td>
         </tr>
         </table>
     </logic:iterate>
     <div class="printhide" style="text-align: center;padding-top: 20px;">
     	<button type="button" id="printBtn" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
     	<button type="button" class="btn btn-warning" onclick="window.location='CertificateWithPersoncode.do?print=certificate&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>'"><span class="glyphicon glyphicon-picture"></span> <b>View Certificate</b></button>
	     <%
	     if(!"true".equals(certificatewithidcard)) 
	     {
	     %>
	    	<button type="button" class="btn btn-danger" onclick="goBack()"><span class="glyphicon glyphicon-circle-arrow-left"></span> <b>Back</b></button>
	    <%
	    }
	    %>
     </div>
     
     <%-- <h3 align="center">  
			     <a href="TerritorySelectForIdcard.do?print=idcardprint&status=finish&personstatus=Eligible&selectedValue=<%=selectFlow%>" target="_blank" >
			     Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>
			     </h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <h3 align="center"><a href="CertificateWithPersoncode.do?print=certificate&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>" >
			    CERTIFICATE</a></h3>
			   <h3 align="center"><a href="CertificateWithPersoncode.do?print=railwayform&certificatewithidcard=<%=certificatewithidcard%>&flag=false" >
			                        Railway Concession<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
			    <% if(!"true".equals(certificatewithidcard)) {%>
			    <center><p> <html:button property="" value="Back" onclick="goBack()" styleClass="button"/></p> </center>
			    <%}%> --%>
    </form>
    </BODY>
    </html:html>
<script type="text/javascript">
$("printBtn").click(function()
{
	alert("HI");
	
});
</script> 
    