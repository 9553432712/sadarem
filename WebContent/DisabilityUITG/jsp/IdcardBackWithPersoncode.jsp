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

<%String lingualTeluguTypeofDisability = "&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125;&#3074;";
            String disabilitypercentage = "&#3125;&#3135;&#3093;&#3122;&#3134;&#3074;&#3095;&#3108;&#3149;&#3125; &#3126;&#3134;&#3108;&#3074;";
            String disabilitytypeInTelugu = (String) request.getAttribute("disabilitytypeInTelugu");
            String apflag = (String) request.getAttribute("apflag");
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
        
    </HEAD>

    <BODY>
        <logic:iterate id="reportprint" name="reportlist" >
                <table border="1" align="center" width="25%" height="20%" >
                    <tr>
                        <td>
                            <table border="0" align="center" width="100%" >
                                <tr>
                                    <td class="idcardLabels">Nature of Disability(<%=lingualTeluguTypeofDisability%> ):<font color="blue">&nbsp;&nbsp;&nbsp;<bean:write name="reportprint"  property="validity"/></font><font color="blue"><bean:write name="reportprint"  property="disabilitytype"/><br>(<%=(String) request.getAttribute("teluguDisablityName")%>&nbsp;<%=disabilitytypeInTelugu%>)</font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="idcardLabels">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="idcardLabels">Percentage of Impairment(<%=disabilitypercentage%>): <font color="blue"><bean:write name="reportprint"  property="disabilityvalue"/>%</font>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table border="0" align="center" width="100%" >
                                <tr>
                                    <td width="50%" class="idcardLabels">Doctor
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Signature & Date :
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

                                    <td width="50%" class="idcardLabels" > Doctor
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Signature & Date :
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Name : <font color="blue">Dr. <bean:write name="reportprint"  property="seconddoctorname"/></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Designation : <font color="blue"> <bean:write name="reportprint"  property="seconddesgination"/></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Registration No : <font color="blue"> <bean:write name="reportprint"  property="seconddoctornumber"/></font>
                                    </td>
                                </tr>

                                <tr>

                                    <td width="50%" class="idcardLabels">Doctor
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Signature & Date :
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Name : <font color="blue">Dr. <bean:write name="reportprint"  property="thirddoctorname"/></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Designation : <font color="blue"> <bean:write name="reportprint"  property="thirddesgination"/></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="idcardLabels">Registration No : <font color="blue"> <bean:write name="reportprint"  property="thirddoctornumber"/></font>

                                    </td>
                                </tr>
                        </td>
                    </tr>
                </table>
            <tr>
                <td class="idcardLabels"><div align="right">  <u>NOTE</u> :- 1.<b>This card is valid for claiming Air / Bus /  Rail  Concession and benefits  sanctioned for eligible disabled persons,  by authorities concerned / Government of
                         <%if (apflag!=null && "0".equals(apflag)) {%>
                                            A.P.
                                            <%} else {%>
                                            T.S.
                                            <%}%></b><br>
                        <br>  2. <b>All particulars, except disability and degree of disability, are based on information given by I-card holder.</b>
                    </div>  </td>
            </tr> 
        </table> 
</logic:iterate>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<center><!--<a href="TerritorySelectForCertificate.doc?print=word" target="_blank">
Edit<img src="DisabilityUITG/images/word.jpg" height="25" width="25"/></a>-->
    <!--
    <a href="TerritorySelectForIdcard.do?print=print" target="_blank" >
    <h3 align="center">Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    -->

</BODY>
</html:html>
