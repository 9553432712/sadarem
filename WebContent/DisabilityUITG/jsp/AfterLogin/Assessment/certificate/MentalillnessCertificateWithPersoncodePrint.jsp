<%@page contentType="text/html"%>
<%@page import="com.tcs.sadarem.util.CommonUtility"  pageEncoding="UTF-8"%>
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
            String apflag = (String) request.getAttribute("apflag");
            String categoryId = CommonUtility.checkNullObj(request.getAttribute("categoryId"));
            String today =(String) request.getAttribute("Todaydate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<br><br>
<html:html>
    <HEAD>

        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/certificatestyles.css">
        <script language="javascript">
            function goBack()
            {
                document.forms[0].action="CalculationForwardAction.do";
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

    <BODY onLoad="window.print()">

        <form action="MentalCertificate.do" method="get">
            <table border="2">
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
                                <td align="center" colspan="2"><img src="./DisabilityUITG/images/govtprint.JPG"></td>
                            </tr>
                            <tr>
                                <td class="labelGreen" align="center" colspan="2">Government of Andhra Pradesh
                                </td>
                            </tr>
                            <%} else {%>
                            <tr>
                               <td align="center" colspan="2"><img src="./DisabilityUITG/images/tggovtprint.png"></td>
                            </tr>
                            <tr>
                                <td class="labelGreen" align="center" colspan="2">Government of Telangana
                                </td>
                            </tr>
                            <%}%>
                            <tr>
                                <td align="center" class="labelGreen" colspan="2">CERTIFICATE FOR PERSON WITH DISABILITY
                                </td>
                            </tr>
                            <tr>
                                <td align="center" class="labelGreen" colspan="2"><font size="1">(Issued under the authority vide G.O.Ms.No.31, WD CW & DW Dept.Dated 01.12.2009)</font></td>
                            </tr>
							 <%if("4".equals(categoryId)){ %> 
                            <tr>
                                <td align="center" class="labelAppelate" colspan="2"><font size="2">(Appellate Authority Assessment)</font></td>
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
                                                <font color="blue">
                                                    <bean:write name="reportprint"  property="hospitalname"/>,&nbsp;&nbsp;<bean:write name="reportprint"  property="hospitaladdress"/>
                                                </font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="Cert-label">ID No.of Person with Disability:</td>
                                            <td class="Cert-label">
                                                <font color="blue">
                                                    <bean:write name="reportprint"  property="personcode"/>
                                                </font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="Cert-label">
                                                Date of Issue:</td><td class="Cert-label"><font color="blue"><bean:write name="reportprint"  property="dateofisse"/></font>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="20%" align="right">
                                    <table>
                                        <tr>
                                            <td>
                                           <%--  <img align="right" src="./DisabilityUITG/uploadedphotos/<bean:write name="reportprint"  property="district_name"/>/<bean:write name="reportprint"  property="personcode"/>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"> --%>
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
                                            <td valign="top" width="4%" class="Cert-label" align="center"><li></td>
                                        <td width="96%" align="left" class="Cert-label">
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
                                            <logic:notEmpty name="reportprint"  property="global_Disablity">
                                                <br>Global Disability Score : <font color="blue" > <bean:write name="reportprint"  property="global_Disablity" /></font><logic:notEmpty  name="reportprint" property="global_Disablity_InWords">[<bean:write name="reportprint"  property="global_Disablity_InWords"/>].</logic:notEmpty>
                                            </logic:notEmpty>
                                            <%if ("true".equals(causeOfDisabilityFlag)) {%>
                                            <br><br>Cause of Disability :
                                            <font color="blue" > <bean:write name="reportprint"  property="totalcauseofdisabilities" /></font>
                                            <%}%>
                                            <logic:notEmpty name="reportprint" property="disabilityreason">
                                                <br>
                                                <font color="blue" > <bean:write name="reportprint"  property="disabilityreason" />.</font>
                                            </logic:notEmpty>
                                        </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label" align="center"><li></td>
                            <td class="Cert-label">Re-assessment of this case is
                                <font color="blue"><bean:write name="reportprint"  property="kindofdisability"/>&nbsp;
                                    <bean:write name="reportprint"  property="period"/></font>
                            </td>
                            </tr>
                            <tr>
                                <td valign="top" class="Cert-label" align="center"><li></td>
                            <td class="Cert-label">
                                Percentage of disability in
                                <bean:write name="reportprint"  property="genderInitial2"/> case is
                                <font color="blue"><bean:write name="reportprint" property="disabilityPercentage"/></font>%&nbsp;
                                [<bean:write name="reportprint"  property="percentageInWord"/>].
                            </td>
                            </tr>
                            <tr>
                                <td  valign="top" class="Cert-label" align="center"><li></td>
                            <td class="Cert-label">Identification Marks of Person with Disability:-
                                <br><font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a)<bean:write name="reportprint"  property="moleone"/></font>.
                                <br><font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)<bean:write name="reportprint"  property="moletwo"/></font>.
                            </td>
                            </tr>
                        </table>
                        </td>
                        </tr>
                        <tr>
                            <td class="columnHeight10" colspan="2"></td>
                        </tr>
                        <tr>
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
                                        <td align="left" width="33%" >Signature</td>
                                        <td align="left" width="33%">Signature</td>
                                        <td align="left" width="30%">Signature</td>
                                    </tr>
                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="firstdoctorname"/></font>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            <font color="blue">Dr.
                                                <bean:write name="reportprint"  property="seconddoctorname"/></font>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">
                                            <font color="blue">Dr.
                                                <bean:write name="reportprint"  property="thirddoctorname"/></font>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            Designation:&nbsp;
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="firstdesgination"/></font>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            Designation:&nbsp;
                                            <font color="blue"><bean:write name="reportprint"  property="seconddesgination"/></font>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">
                                            Designation:&nbsp;
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="thirddesgination"/></font>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" width="33%" class="Cert-label">
                                            Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="firstdoctornumber"/></font>
                                        </td>
                                        <td align="left" width="30%" class="Cert-label">
                                            Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="seconddoctornumber"/></font>
                                        </td>
                                        <td align="left" width="33%" class="Cert-label">
                                            Regn.No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
                                            <font color="blue">
                                                <bean:write name="reportprint"  property="thirddoctornumber"/></font>
                                        </td>
                                    </tr>
                                </table><br>
                            </td>
                        </tr>
                        <tr><td class="Cert-label">Note: This is not valid for Medico-Legal cases.</td></tr>
                    </table>

                </logic:iterate>
                <div align="right" style="font-size:15px; padding-right:35px; ">Print Date: <%=today %></div> 
            </logic:notEmpty>
            <logic:empty name="reportlist" >
                <center><font color="red"  ><b>No Data</b></font></center>
            </logic:empty>
        </table>
    </form>


</BODY>
</html:html>