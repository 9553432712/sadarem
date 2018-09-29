<%--
    Document   : MemberDetails
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

%>
<%
            String rationCardSlno = (String) request.getAttribute("rationSlno");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%int i = 1;%>
        <% String ration = (String) request.getAttribute("rationcard");
                    if (ration == null) {
                        ration = "";
                    }
        %>
        <script>
            function getDetails() {
                document.forms[0].mode.value="getWebDetails";
                document.forms[0].submit();
            }
            function submitDetails() {
                document.forms[0].rationCardNo.value='<%=ration%>';
                document.forms[0].more.value='More';
                document.forms[0].mode.value="getData";
                document.forms[0].submit();
            }
        </script>

        
    </head>
    <body >
        <html:form action="RationcardDetails">
            <br/><br/>
            <html:hidden property="more"/>
            <logic:present name="msgSuccess">
                <center><font color="green"><bean:write name="msgSuccess"/></font></center>
            </logic:present>
            <logic:present name="msgFailure">
                <center><font color="red"><bean:write name="msgFailure"/></font></center>
            </logic:present><br/>

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
                        <th>
                           SerialNo
                        </th >
                        <th>
                            PensionId
                        </th >
                        <th>
                            SADAREMID
                        </th >
                        <th>
                            Name
                        </th >
                        <th>
                            Relation Name
                        </th >
                        <th>
                            Age
                        </th >
                        <th>
                            Gender
                        </th >

                        <th>
                            Phase
                        </th >
                        <th>
                            ReasonForStatus
                        </th >
                        <th>
                            Status
                        </th >

                        <th>
                            Action
                        </th >

                    </tr>
                    <logic:iterate name="Pensiondata" id="row">


                        <tr>

                            <td  align="center" >
                                 ${row.rationcard_slno} 
                            </td>
                            <td align="left" >
                                ${row.pensionid}
                            </td>
                            <td  align="left" >
                                ${row.sadarem}
                            </td>
                            <td align="left" >
                                ${row.name}
                            </td>
                            <td  align="left" >
                                ${row.rname}
                            </td>
                            <td  align="left" >
                                ${row.age}
                            </td>
                            <td  align="left" >
                                ${row.gender}
                            </td>

                            <td align="left" >
                                ${row.phase}
                            </td>
                            <td  align="left" >
                                ${row.reason}
                            </td>
                            <td align="left" >
                                ${row.Ass}
                            </td>
                            <td  align="left" >
                                ${row.url}
                            </td>
                        </tr>


                    </logic:iterate> </table></logic:notEmpty>
                <br/>
                <center>    <input type="button" value="More Details From Civil Supplies" name="but" onclick="submitDetails();"/></center>
                <html:hidden property="rationCardNo" value="<%=ration%>"/>
                <logic:notEmpty name="data">

                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr><th colspan="9" align="center" class="heading" >RationCard Members Details : <%=ration%></th></tr>
                    <tr>
                        <th>
                            RationCard SerialNo
                        </th >
                        <th  ">
                            PensionId
                        </th >
                        <th  >
                            SADAREM ID
                        </th >
                        <th >
                            Name
                        </th >
                        <%--<td>
                            Age
                        </td >
                        <td>
                            Gender
                        </td >
                        <td>
                            Relation
                        </td >--%>

                        <th >
                            Sadarem Status
                        </th >
                        <th >
                            Assessment
                        </th >
                        <th >
                            Action
                        </th>
                    </tr> 
                    <logic:iterate name="data" id="row">
                        <bean:define id="scode" value="${row.rationCard}"/>
                        <tr>
                            <td  align="center" >
                                ${row.relationSlno} .
                            </td>
                            <td align="left" >
                                ${row.pen}
                            </td>
                            <td align="left" >
                                ${row.sad}
                            </td>
                            <td align="left" >
                                ${row.name}
                            </td>
                           <%-- <td align="left" >
                                ${row.Age}
                            </td>
                            <td align="left" >
                                ${row.gender}
                            </td>
                            <td  align="left" >
                                ${row.relation}
                            </td>--%>

                            <td align="center" >
                                ${row.imgs}
                            </td>

                             <td align="center" >
                                ${row.assStatus}
                            </td>

                            <td align="center" >
                                ${row.go}
                            </td>
                        </tr>
                    </logic:iterate>
                </table><br/>
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
