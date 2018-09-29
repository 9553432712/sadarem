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
                document.getElementById('detailsButton').style.display='none';
                
                document.getElementById('processing').style.display='';
                document.getElementById('data').style.display='';
                document.forms[0].more.value='More';
                document.forms[0].mode.value="getData";
                document.forms[0].submit();
            }
        </script>


    </head>
    <body >
        <html:form action="RationcardDetails">

            <html:hidden property="more"/>
            <logic:present name="msgSuccess">
                <p id="succmsg"><bean:write name="msgSuccess"/></p>
            </logic:present>
            <logic:present name="msgFailure">
                <p id="errmsg"><bean:write name="msgFailure"/></p>
            </logic:present>

            <html:hidden property="mode"/>
            <logic:empty  name="Pensiondata">
                <center >   Pension data not found for this Rationcard Number <%=ration%></center>
            </logic:empty>
            <logic:notEmpty name="Pensiondata" >
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <logic:notEmpty name="Pensiondata" >
                        <logic:iterate name="Pensiondata" id="row"> <bean:define id="presentRation" value="${row.rno}"/></logic:iterate></logic:notEmpty>



                        <tr><th colspan="11"> Pension Card member details On same RationCard :${presentRation}</th></tr>
                    <tr>
                        <th>
                            Rationcard Serial No
                        </th>
                        <th>
                            Pension ID
                        </th>
                        <th>
                            SADAREM ID
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Relation Name
                        </th>
                        <th>
                            Age
                        </th>
                        <th>
                            Gender
                        </th>

                        <th>
                            Phase
                        </th>
                        <th>
                            ReasonForStatus
                        </th>
                        <th>
                            Status
                        </th>

                        <th>
                            Action
                        </th>

                    </tr>
                    <logic:iterate name="Pensiondata" id="row">


                        <tr>

                            <td>
                                ${row.rationcard_slno}
                            </td>
                            <td>
                                ${row.pensionid}
                            </td>
                            <td >
                                ${row.sadarem}
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <td >
                                ${row.rname}
                            </td>
                            <td >
                                ${row.age}
                            </td>
                            <td >
                                ${row.gender}
                            </td>

                            <td>
                                ${row.phase}
                            </td>
                            <td >
                                ${row.reason}
                            </td>
                            <td>
                                ${row.Ass}
                            </td>
                            <td >
                                ${row.url}
                            </td>
                        </tr>


                    </logic:iterate> </table>
                </logic:notEmpty>
            <br>

            <table  align="center" cellspacing="1" cellpadding="0"  width="50%" border="0">
                <tr>
                    <td style="text-align: center;"  id="detailsButton">
                        <input type="button" value="More Details From Civil Supplies" name="but" onclick="submitDetails();" style="width:200px"/>
                    </td>
                </tr>
                <tr>
                    <td  align="center" id="processing" style="display: none;">
                            <img src='<%=basePath%>DisabilityUITG/images/ajax-loader.gif'/>
                    </td>
                    
                      <td align="center"  id="data" style="display: none;">
                        <font color=red size=2><b>Fetching from Civil Supplies please wait...</b></font>
                    </td>
                </tr>
            </table>

            <br>
            <html:hidden property="rationCardNo" value="<%=ration%>"/>
            <logic:notEmpty name="data">

                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr><th colspan="7">RationCard Members Details : <%=ration%></th></tr>
                    <tr>
                        <th>
                            RationCard SerialNo
                        </th>
                        <th>
                            Pension ID
                        </th>
                        <th>
                            SADAREM ID
                        </th>
                        <th>
                            Name
                        </th>
                        <%--<th>
                            Age
                        </th>
                        <th>
                            Gender
                        </th>
                        <th>
                            Relation
                        </th>--%>

                        <th>
                            Sadarem Status
                        </th>
                        <th>
                            Assessment
                        </th>
                        <th>
                            Action
                        </th>
                    </tr> 
                    <logic:iterate name="data" id="row">
                        <bean:define id="scode" value="${row.rationCard}"/>
                        <tr>
                            <td>
                                ${row.relationSlno} .
                            </td>
                            <td>
                                ${row.pen}
                            </td>
                            <td>
                                ${row.sad}
                            </td>
                            <td>
                                ${row.name}
                            </td>
                            <%-- <td>
                                 ${row.Age}
                             </td>
                             <td>
                                 ${row.gender}
                             </td>
                             <td >
                                 ${row.relation}
                             </td>--%>

                            <td>
                                ${row.imgs}
                            </td>

                            <td>
                                ${row.assStatus}
                            </td>

                            <td>
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
