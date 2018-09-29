<%--
    Document   : requestpersonalInformationDetails
    Created on : Sep 18, 2012, 1:58:41 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%
            int i = 1;

            String requestDetailsId = (String) request.getAttribute("requestDetailsId");
            String requestStatusIdData = (String) request.getAttribute("statusIdData");




%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADARM</title>
    </head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/DisabilityUITG/css/Sadarem-Style.css" type="text/css">
   
    <script>

        function updateNewPersonalDetails(){
            
            document.forms[0].mode.value ="insertDetails";
            document.forms[0].submit();
        }

        function rejectNewCertificateDetails(){
            document.forms[0].mode.value ="rejectDetails";
            document.forms[0].submit();

        }
    </script>
    <body bgcolor="#f2f0e6">
        <table cellpadding="0" cellspacing="0" width="1000px" border="0" align="center" style="border:0px #000 solid">
            <!--<tr>
                <td><img src="DisabilityUITG/images/Header_3.jpg"></td>
            </tr> -->
            <!--<tr>
                <td><hr></td>
            </tr> -->
            <tr>
                <td>
                    <html:form action="/requestInformation">
                        <html:hidden property="mode"/>
                        <input type="hidden" value="<%= request.getAttribute("statusId")%>" name="statusIdHidden">
                        <logic:present name="msg">
                            <center>
                                <font color="red">${msg}</font>
                            </center>
                        </logic:present>
                            <br/><br/><br/>
                        <logic:present name="generatedSadaremID">
                            <center>
                               SADAREM ID : <font color="red">${generatedSadaremID}</font>
                            </center>
                        </logic:present>

                         <logic:present name="selecthabCode">
                            <center>
                               SADAREM ID : <font color="red">${selecthabCode}</font>
                            </center>
                        </logic:present>
                        <logic:present name="rejectStatus">
                            <center>
                                <font color="red">${rejectStatus}</font>
                            </center>
                        </logic:present>
                        <logic:notEmpty name="newCertificateList">
                            <table cellpadding="0" cellspacing="0" width="50%" border="0" align="center" style="border:1px #000 solid">
                                <br/><br/>
                                <logic:iterate name="newCertificateList" id="row" indexId="Sno">
                                    <html:hidden property="newCertificateReqiestId" value="${row.requestid}"/>
                                    <html:hidden property="newCertificateReqiestTypeId" value="${row.requesttypeid}"/>
                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            S.No
                                        </td>
                                        <td class="formaltbg">
                                            <%=Sno + 1%>.
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                            PensionCardNo.
                                        </td>
                                        <td class="formaltbg">
                                            ${row.pensionCardNO}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            RationCardNumber
                                        </td>
                                        <td class="formaltbg">
                                            ${row.rationCardNumber}
                                        </td>
                                    </tr>

                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                            SurName
                                        </td>
                                        <td class="formaltbg">
                                            ${row.surName}
                                        </td>
                                    </tr>

                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            Name
                                        </td>
                                        <td class="formaltbg">
                                            ${row.firstName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                            Gender
                                        </td>
                                        <td class="formaltbg" >
                                            ${row.gender}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            DistrictName
                                        </td>
                                        <td class="formaltbg">
                                            ${row.districtName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                            Mandal Name
                                        </td>
                                        <td class="formaltbg">
                                            ${row.mandalName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            Village Name
                                        </td>
                                        <td class="formaltbg">
                                            ${row.villageName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                            Habitation Name
                                        </td>
                                        <td class="formaltbg">
                                            ${row.habitationName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg" align="center">
                                            SSC Documents
                                        </td>

                                        <td class="formaltbg" align="">
                             <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<%=requestDetailsId%>&personCodeId=<bean:write name="row" property="personCode"/>&flag=1')">View</a>
                                        </td>
                                       
                                    </tr>
                                    <tr>
                                        <td  class="formhdbg1" align="center">
                                           Address Documents
                                        </td>

                                        <td class="formaltbg" align="">
                                  <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<%=requestDetailsId%>&personCodeId=<bean:write name="row" property="personCode"/>&flag=1')">View</a>
                                        </td>

                                    </tr>


                                </logic:iterate>
                            </table>
                            <br/>
                            <table cellpadding="0" cellspacing="0"  align="center">
                                <tr>
                                    <td align="center">
                                        <html:submit property="but" value="Approval" onclick="updateNewPersonalDetails();"/>
                                    </td>
                                    <td align="center">&nbsp;</td>
                                    <td align="center">
                                        <html:submit property="but" value="Reject" onclick="rejectNewCertificateDetails();"/>
                                    </td>
                                </tr>
                            </table>
                        </logic:notEmpty>



                    </html:form> <br>
                </td>
            </tr>
         <!--   <tr>
                <td><div align="center">
            <font face="Arial" size="1" color="#143A27"> Copyrights Reserved by Govt of Telangana &#169; 2010 </font> <br />
            <strong><font face="Arial" size="1" color="#143A27">SADAREM</font>
            </strong> <font face="Arial" size="1" color="#143A27">is hosted & maintained by </font>
            <a href="http://www.aponline.gov.in" target="_blank">
            <font face="Arial" size="1"> APOnline </font> </a> <br />
            <font face="Arial" size="1" color="#143A27"> Site Best viewed in 1024*768 Resolution  </font><br>
            <font face="Arial" size="1" color="#143A27">Best Use in Internet Explorer <font size="2" color="blue">6</font></font>
        </div></td>
            </tr> -->
        </table>
       
    </body>
</html>
