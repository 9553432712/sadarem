<%@page contentType="text/html"%>
<%@page import="com.tcs.sadarem.util.CommonUtility" pageEncoding="UTF-8"%>
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
            String causeOfDisabilityFlag = (String) request.getAttribute("causeOfDisabilityFlag");
            String conditionlistCheckFlag = (String) request.getAttribute("conditionlistCheckFlag");
            String checkFlag = (String) request.getAttribute("flag");
            String checkTerritoryFlag = (String) request.getAttribute("checkTerritoryFlag");
            String certificatewithidcard = (String) request.getAttribute("certificatewithidcard");
            String apflag = (String) request.getAttribute("apflag");
            String categoryId = CommonUtility.checkNullObj(request.getAttribute("categoryId"));
            String Todaydate = (String) request.getAttribute("Todaydate");
%>

<!DOCTYPE HTML> 
<html:html>
     <%String selectFlow = "OUTERPROCESS";
                    if (request.getAttribute("selectedValue") != null) {
                        selectFlow = request.getAttribute("selectedValue").toString();
               }%>
    <HEAD>
    <title>:: SADAREM :: View Certificate</title>
       <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
        <script language="javascript">
            function goBack()
            {
                document.forms[0].action="CalculationForwardAction.do?selectedValue=<%=selectFlow%>";
                document.forms[0].submit();
            }
        </script>
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
    </HEAD>

    <BODY>
        <form action="MentalCertificate.do" method="get">
            <table border="2" class="tbl_bg3" bgcolor="green">
                <logic:notEmpty name="reportlist" >
                    <logic:iterate id="reportprint" name="reportlist" >
                        <input type="hidden" name="flag" value="finish"/>
                         <%
                      String classStyle="table_four";
                      if("4".equals(categoryId))
                      {   
                    	  classStyle="table_appellate";
                      }
                      else
                      {  
                    	  classStyle="table_four";
                      } %>
                         <table class="<%=classStyle %> cert-table" align="center">
                            <%if ("0".equals(apflag)) {%>
                            <tr>
                                <td align="center" colspan="2"><img src="<%=request.getContextPath()%>/images/ap_govt_logo.png"></td>
                            </tr>
                            <tr>
                                <td class="labelGreen" align="center" colspan="2"><span style="color: green !important; size:1;">Government of Andhra Pradesh</span></td>
                            </tr>
                            <%} else {%>
                            <tr>
                               <td align="center" colspan="2"><img src="./DisabilityUITG/images/tggovt.png"></td>
                            </tr>
                            <tr>
                                <td class="labelGreen" align="center" colspan="2"><span style="color: green !important; size:1;">Government of Telangana</span></td>
                            </tr>
                            <%}%>
                            <tr>
                                <td align="center" class="labelGreen" colspan="2"><span style="color: green !important; size:1;">CERTIFICATE FOR PERSON WITH DISABILITY</span></td>
                            </tr>
                            <tr>
                                <td align="center" class="labelGreen" colspan="2"><span style="color: green !important; font-size: 12px !important;">(Issued under the authority vide G.O.Ms.No.31, WD CW & DW Dept.Dated 01.12.2009)</span></td>
                            </tr>
                           <%if("4".equals(categoryId)){ %> 
                            <tr>
                                <td align="center" class="labelAppelate" colspan="2"><span style="color: red !important; font-size: 10px !important;">(Appellate Authority Assessment)</span></td>
                            </tr>
                            <%} %>
                            <tr>
                                <td class="columnHeight10" colspan="2"></td>
                            </tr>
                            <tr>
                                <td width="80%" valign="bottom">
                                    <table>
                                        <tr>
                                            <td class="Cert-label">Medical Board:</td>
                                            <td class="Cert-label">
                                                <span style="color: blue !important; ">
                                                    <bean:write name="reportprint"  property="hospitalname"/>,&nbsp;&nbsp;<bean:write name="reportprint"  property="hospitaladdress"/>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="Cert-label">ID No.of Person with Disability:</td>
                                            <td class="Cert-label">
                                                <span style="color: blue !important; ">
                                                    <bean:write name="reportprint"  property="personcode"/>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="Cert-label">
                                                Date of Issue:</td> <td class="Cert-label"><span style="color: blue !important; "><bean:write name="reportprint"  property="dateofisse"/></span>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="20%" align="right">
                                    <table>
                                        <tr>
                                            <td>
                                            <%-- <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"><br> --%>
                                             <img src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>&distName=<bean:write name="reportprint"  property="district_name"/>&randiomid=<%=Math.random()%>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="columnHeight10" colspan="2"></td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <table class="table_three">
                                        <tr>
                                            <td valign="top" width="4%" class="Cert-label"><li/>
                                        <td width="96%" align="left" class="Cert-label">
                                            This is certified that <bean:write name="reportprint"  property="maritialstatus"/>
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="name"/></span>,
                                            <bean:write name="reportprint"  property="relationship"/>
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="relationname"/></span>,
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="gender"/></span>, age
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="age"/></span> years,
                                            <logic:notEmpty name="reportprint" property="housenumber">
                                                resident of H.No.# <span style="color: blue !important; "><bean:write name="reportprint"  property="housenumber"/></span>,
                                            </logic:notEmpty>
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="habitation_name"/></span> Habitation,
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="village_name"/></span> Village,
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="mandal_name"/> </span>Mandal,
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="district_name"/></span>
                                            District, is suffering from <bean:write name="reportprint"  property="validity"/>
                                            disability of the following category:-
                                            <br><span style="color: blue !important; "><bean:write name="reportprint" property="disabilitytype"/></span>
                                            <logic:notEmpty name="reportprint"  property="disabilitycondition">
                                                <span style="color: blue !important; ">(<bean:write name="reportprint" property="disabilitycondition"/>)</span>.
                                            </logic:notEmpty>
                                            <logic:notEmpty name="reportprint" property="hearing_DB">
                                                <br>Loss of<span style="color: blue !important; "> <bean:write name="reportprint"  property="hearing_DB"/>(<bean:write name="reportprint"  property="hearing_DB_In_Words"/>)</span>&nbsp;decibels in the better ear in the conversational range of frequencies.
                                            </logic:notEmpty>
                                            <%if ("true".equals(causeOfDisabilityFlag)) {%>
                                            <br><br>Cause of Disability :
                                            <span style="color: blue !important; " > <bean:write name="reportprint"  property="totalcauseofdisabilities" /></span>
                                            <%}%>
                                            <logic:notEmpty name="reportprint" property="disabilityreason">
                                            <br>
                                            <span style="color: blue !important; " > <bean:write name="reportprint"  property="disabilityreason" />.</span>
                                            </logic:notEmpty>
                                        </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label"><li/></td>
                            <td class="Cert-label">Re-assessment of this case is
                                <span style="color: blue !important; "><bean:write name="reportprint"  property="kindofdisability"/>&nbsp;
                                    <bean:write name="reportprint"  property="period"/></span>
                            </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label"><li/></td>
                            <td class="Cert-label">
                                Percentage of disability in
                                <bean:write name="reportprint"  property="genderInitial2"/> case is &nbsp;<span style="color: blue !important; "><bean:write name="reportprint" property="disabilityPercentage"/></span>%&nbsp;[<bean:write name="reportprint"  property="percentageInWord"/>].

                                <%if ("true".equals(conditionlistCheckFlag)) {%>
                            </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label"><li/></td>
                            <td class="Cert-label">
                                <bean:write name="reportprint"  property="genderInitial1"/>
                                meets the following physical requirements for discharge of
                                <bean:write name="reportprint"  property="genderInitial2"/> duties.
                                <span style="color: blue !important; "><bean:write name="reportprint"  property="hearingImpairmentPhysicalRequirementList" /></span>
                                <%}%>

                            </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label"><li/></td>
                            <td class="Cert-label">Identification Marks of Person with Disability:-
                                <br><span style="color: blue !important; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a)<bean:write name="reportprint"  property="moleone"/></span>.
                                <br><span style="color: blue !important; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)<bean:write name="reportprint"  property="moletwo"/></span>.
                            </td>
                            </tr>
                        </table>
                        </td>
                        </tr>
                        <tr>
                            <td class="columnHeight10" colspan="2"></td>
                        </tr>
                        <tr>
                            <td colspan="2" class="Cert-label" align="right">
                                Signature/Thumb impression <br> of Person with Disability
                            </td>
                        </tr>
                        <tr>
                            <td class="columnHeight12" colspan="2"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table class="Cert-label" width="100%">
                                    <tr>
                                        <td align="left" width="33%">Signature</td>
                                        <td align="left" width="33%">Signature</td>
                                        <td align="left" width="30%">Signature</td>
                                    </tr>
                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="firstdoctorname"/></span>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            <span style="color: blue !important; ">Dr.
                                                <bean:write name="reportprint"  property="seconddoctorname"/></span>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">
                                            <span style="color: blue !important; ">Dr.
                                                <bean:write name="reportprint"  property="thirddoctorname"/></span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            Designation:&nbsp;
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="firstdesgination"/></span>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            Designation:&nbsp;
                                            <span style="color: blue !important; "><bean:write name="reportprint"  property="seconddesgination"/></span>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">
                                            Designation:&nbsp;
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="thirddesgination"/></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="firstdoctornumber"/></span>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="seconddoctornumber"/></span>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <span style="color: blue !important; ">
                                                <bean:write name="reportprint"  property="thirddoctornumber"/></span>
                                        </td>
                                    </tr>
                                </table><br>

                            </td>

                        </tr>
                        <tr><td class="Cert-label">Note: This is not valid for Medico-Legal cases.</td></tr>
                    </table>
					
			        <div class="row">
			            <div align="right" style="font-size:15px; padding-right:35px; ">Print Date: <%=CommonUtility.getDateTime("dd-MMM-yyyy HH:mm") %></div> 
			        </div>
					
        		<div class="printhide" style="text-align: center;">
                    <%
                    if ("true".equals(checkFlag) && !"true".equals(certificatewithidcard)) 
                    {
                    %>

                   <%--  <center>
                        <h3><a href="CertificateWithPersoncode.do?print=certificateprint&flag=true&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                    </center> --%>
                       <button type="button" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
                    
                    <%
                    } 
                    else if ("true".equals(checkTerritoryFlag)) 
                    {
                    %>
                   <%--  <center>

                        <h3><a href="TerritorySelectForCertificate.do?print=certificateprint&status=finish&personstatus=Eligible&selectedValue=<%=selectFlow%>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                        <h3><a href="CertificateWithPersoncode.do?print=idcard&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                                ID CARD<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                       <h3><a href="CertificateWithPersoncode.do?print=railwayform&certificatewithidcard=<%=certificatewithidcard%>&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                        Railway Concession<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                    </center> --%>
                       <button type="button" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
                       <button type="button" class="btn btn-warning" onclick="window.location='CertificateWithPersoncode.do?print=idcard&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>'"><span class="glyphicon glyphicon-picture"></span> <b>ID Card</b></button>    
                    
                    <%
                    } 
                    else if ("true".equals(certificatewithidcard)) 
                    {
                    %>
                <%--     <center>

                        <h3><a href="CertificateWithPersoncode.do?print=certificateprint&flag=true&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                        <h3><a href="CertificateWithPersoncode.do?print=idcard&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                                ID CARD<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                       <h3><a href="CertificateWithPersoncode.do?print=railwayform&certificatewithidcard=<%=certificatewithidcard%>&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                        Railway Concession<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                    </center> --%>
                    <button type="button" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
                    <button type="button" class="btn btn-warning" onclick="window.location='CertificateWithPersoncode.do?print=idcard&certificatewithidcard=<%=certificatewithidcard%>&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>'"><span class="glyphicon glyphicon-picture"></span> <b>ID Card</b></button>    
                    
                    
                    <%
                    }
                    else 
                    {
                    %>
                    <%-- <center>
                        <h3><a href="CertificateWithPersoncode.do?print=certificateprint&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>

                        <h3><a href="CertificateWithPersoncode.do?print=idcard&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                                ID CARD<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>

                       <h3><a href="CertificateWithPersoncode.do?print=railwayform&flag=false&personcode=<bean:write name="reportprint"  property="personcode"/>" >
                        Railway Concession<img src="DisabilityUITG/images/Photo-ID.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
                        <p > <html:button property="" value="Back" onclick="goBack()" styleClass="button"/> </p>
                    </center> --%>
                      <button type="button" class="btn btn-primary" onclick="window.print()"><span class="glyphicon glyphicon-print"></span> <b>Print</b></button>
                      <button type="button" class="btn btn-warning" onclick="window.location='CertificateWithPersoncode.do?print=idcard&flag=false&selectedValue=<%=selectFlow%>&personcode=<bean:write name="reportprint"  property="personcode"/>'"><span class="glyphicon glyphicon-picture"></span> <b>ID Card</b></button>    
                      <button type="button" class="btn btn-danger" onclick="goBack()"><span class="glyphicon glyphicon-circle-arrow-left"></span> <b>Back</b></button>
                    
                    <%}%>
				</div>
                </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="reportlist" >
                <center ><font color="red" size="2" ><b>No Data</b></font></center>
            </logic:empty >


        </form>


    </BODY>
</html:html>