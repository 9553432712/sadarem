<%--
    Document   : webServiceDetails
    Created on : Aug 7, 2011, 11:15:36 AM
    Author     : SADAREM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";


            String rationCardNo = (String) request.getAttribute("rationCardNo");
            int i = 1;

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <script>
            function getDetails() {
                document.forms[0].mode.value="getWebDetails";
                document.forms[0].submit();
            }
        </script>
        <% String ration = (String) request.getAttribute("rationcard");
                    if (ration == null) {
                        ration = "";
                    }
        %>
    </head>
    <body >
        <html:form action="MemberDetails">

            <br/><br/>

            <logic:present name="msgFailure">
                <center><font color="green"><bean:write name="msgFailure"/></font></center>
            </logic:present>
            <br/>

            <html:hidden property="mode"/>
            <logic:empty  name="Pensiondata">
                <center  class="heading">   Pension data not found for this Rationcard Number <%=ration%></center>
            </logic:empty>
            <logic:notEmpty name="Pensiondata" >
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <logic:notEmpty name="Pensiondata" >
                        <logic:iterate name="Pensiondata" id="row"> <bean:define id="presentRation" value="${row.rno}"/></logic:iterate></logic:notEmpty>

                        <tr><th colspan="11" align="center" class="heading" > Pension Card member details On same RationCard : ${presentRation}</th></tr>
                    <tr>
                        <th class="formhdbg" align="center">
                            SerialNo
                        </th >
                        <th class="formhdbg" align="center">
                            Pension ID
                        </th >
                        <th class="formhdbg" align="center">
                            SADAREMID
                        </th >
                        <th class="formhdbg" align="center">
                            Name
                        </th >
                        <th class="formhdbg" align="center">
                            Relation Name
                        </th >
                        <th class="formhdbg" align="center">
                            Age
                        </th >
                        <th class="formhdbg" align="center">
                            Gender
                        </th >

                        <th class="formhdbg" align="center">
                            Phase
                        </th >
                        <th class="formhdbg" align="center">
                            Pension Status
                        </th >
                        <th class="formhdbg" align="center">
                            Assessment Status
                        </th >

                        <th class="formhdbg" align="center">
                            Action
                        </th >

                    </tr>
                    <logic:iterate name="Pensiondata" id="row">


                        <tr>

                            <td  align="center" class="formaltbg">
                                ${row.rationcard_slno}  
                            </td>
                            <td align="left" class="formaltbg">
                                ${row.pensionid}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.sadarem}
                            </td>
                            <td align="left" class="formaltbg">
                                ${row.name}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.rname}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.age}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.gender}
                            </td>

                            <td align="left" class="formaltbg">
                                ${row.phase}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.reason}
                            </td>
                            <td align="left" class="formaltbg">
                                ${row.Ass}
                            </td>
                            <td  align="left" class="formaltbg">
                                ${row.url}
                            </td>
                        </tr>


                    </logic:iterate> 
                </table>
            </logic:notEmpty>
            <br/>
            <logic:notEmpty name="webServiceListData">

                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr><th colspan="8" align="center" class="heading" >RationCard Members Details : <%=rationCardNo%></th></tr>
                    <tr>
                        <th class="formhdbg" align="center">
                            RationCard SerialNo
                        </th>

                        <th class="formhdbg" align="center">
                            PensionCard No
                        </th>
                        <th class="formhdbg" align="center">
                            SADAREM ID
                        </th>
                        <th class="formhdbg" align="center">
                            Name
                        </th>
                        <%--  <th class="formhdbg" align="center">
                              Age
                          </th>--%>
                        <th class="formhdbg" align="center">
                            Sadarem Status
                        </th >
                        <th class="formhdbg" align="center">
                            AssessmentStatus
                        </th>
                        <th class="formhdbg" align="center">
                            Assessment Done For Name
                        </th >

                        <th class="formhdbg" align="center">
                            Action
                        </th>

                    </tr>
                    <logic:iterate name="webServiceListData" id="row">

                        <tr>
                            <td align="center" class="formaltbg">
                                ${row.slNo}
                            </td>

                            <td align="center" class="formaltbg">
                                ${row.pensionNo}
                            </td>
                            <td align="center" class="formaltbg">
                                ${row.personCode}
                            </td>

                            <td align="center" class="formaltbg">
                                ${row.memberName}
                            </td>
                            <%-- <td align="center" class="formaltbg">
                                 ${row.age}
                             </td>--%>

                            <td align="center" class="formaltbg">
                                ${row.imgs}
                            </td>

                            <td align="center" class="formaltbg">
                                ${row.assStatus}
                            </td>
                            <td align="center" class="formaltbg">
                                ${row.PersonName}
                            </td>
                            <td align="center" class="formaltbg">
                                ${row.webServiceGo}
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
                        
                <table align="center" border="0" cellpadding="0" cellspacing="0" >
                    <tr >
                        <td align="center"> <img src="./DisabilityUITG/images/right.JPG" height="18"><font size="1"> <b>Sadarem</b></font></td>
                        <td>&nbsp;</td>
                        <td align="center"> <img src="./DisabilityUITG/images/cross.JPG" height="18"><font size="1"> <b>Non Sadarem</b></font></td>
                    </tr>
                </table>
            </logic:notEmpty>

        </html:form>
    </body>
</html>
