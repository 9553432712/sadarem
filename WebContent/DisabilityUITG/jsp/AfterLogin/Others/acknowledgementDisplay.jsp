<%-- 
    Document   : acknowledgementDisplay
    Created on : Oct 29, 2014, 4:33:07 PM
    Author     : 842412
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <html:form action="/acknowledgementPrint">
            <html:hidden property="mode"/>
            <%--<p id="errmsg"><%=msg %></p>--%>
            <p id="errmsg">${msg}</p>
            <table  align="center" cellspacing="0" cellpadding="0"  width="70%" >

                <tr >
                    <td colspan="3">
                        <% int i = 1;%>
                        <logic:notEmpty name="aknowledgementDetails">
                            <logic:present name="aknowledgementDetails">
                                <logic:iterate id="rows" name="aknowledgementDetails" length="1" >
                                    <% if (i == 1) {
                                    %>
                                    <logic:present name="aknowledgementDetails">
                                        <logic:iterate id="row" name="aknowledgementDetails"  length="1">
                                            <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="100%" border="1">
                                    <tr>
                                        <th colspan="2">
                                            Acknowledgement of Personal Details Registration
                                        </th>
                                    </tr>
                                    <tr>
                                        <td >
                                            <font size="3"> Application Number:</font><font color="blue">&nbsp;${row.ackno}</font><br/><br/>
                                        </td>
                                        <td >
                                            <%--<font size="3"> Token Number:</font><font color="blue">&nbsp;${row.tokenNo}</font><br/><br/>--%>
                                            <font size="3"> Registered Date:</font><font color="blue">&nbsp;${row.currentdate}</font><br/><br/>
                                        </td>
                                    </tr>
                                    <tr>


                                        <td colspan="2" align="center"><font size="3">SADAREMID:</font><font color="blue">&nbsp;${row.person_code}</font><br/><br/>
                                            This is to acknowleged that &nbsp;${row.Marital_Status}<font color="blue">&nbsp;${row.fullname}</font>&nbsp;&nbsp;${row.Relationship}&nbsp;&nbsp;<font color="blue">${row.relation_name}</font>

                                            &nbsp; Address:<font color="blue">${row.house_number}</font>
                                            <font color="blue">${row.habitation_name}</font>&nbsp;Habitation,
                                            <font color="blue">${row.village_name}</font>&nbsp;Village,
                                            <font color="blue">${row.mandal_name}</font>&nbsp;Mandal,
                                            <font color="blue">${row.district_name}</font>&nbsp;District,&nbsp;
                                            ${row.disabilitytype}
                                            <logic:notPresent name="aknowledgementDetailsmultiple">

                                                At  <font color="blue">${row.campname}</font>&nbsp;Camp,&nbsp;
                                                On  <font color="blue">${row.campdate}</font>&nbsp;Date.&nbsp;
                                            </logic:notPresent>
                                            <logic:present name="aknowledgementDetailsmultiple">

                                                <logic:iterate id="row" name="aknowledgementDetailsmultiple"  >

                                                    At  <font color="blue">${row.campname}</font>&nbsp;Camp,&nbsp;
                                                    On  <font color="blue">${row.campdate}</font>&nbsp;Date,&nbsp;
                                                    Disability Of <font color="blue">${row.disability}</font>&nbsp;

                                                </logic:iterate>
                                            </logic:present>
                                                    <logic:present name="result">

                                                        <logic:equal name="result" value="1"> for Part B entry</logic:equal>
                                                        <logic:equal name="result" value="2"> for Reassessment</logic:equal>
                                                        <logic:equal name="result" value="3"> for Temporary Certificate</logic:equal>

                                                    </logic:present>
                                            <logic:present name="aknowledgementDetails">
                                                <logic:iterate id="row" name="aknowledgementDetails"  length="1">
                                                    . For Further Details Contact to<font color="blue"> ${row.dpmname}(${row.contactno}), Camp Incharge.</font>
                                                </logic:iterate>
                                            </logic:present>

                                        </td>
                                    </tr>


                                    <br/>
                                    </table>
                                </logic:iterate>
                            </logic:present>

                            <%}
                                        i++;%>
                        </logic:iterate>
                    </logic:present>
                            <tr><td style="text-align: center" colspan="2">  <a href="#" onclick="window.open('./acknowledgementPrint.do?mode=PersonCodeAcknowledgementPrint&sadaremid=${sadarem}','_blank','toolbar=no, scrollbars=yes, resizable=yes,width=1100, height=800');"> <img src="./DisabilityUITG/images/print.gif" height="25"  width="25" border="0">
                        </a>
                    </td>
                    <%--<td style="text-align: center" colspan="2">  <a href="#" onclick="window.open('./insertPartAaction.do?insertPersonalDetails=print&result=<%=result%>','_blank','toolbar=no, scrollbars=yes, resizable=yes,width=1100, height=800');"> <img src="./DisabilityUITG/images/print.gif" height="25"  width="25" border="0">
                        </a>
                    </td>--%>
                </tr>
                </logic:notEmpty>

                

            </table>
        </html:form>
    </body>
</html>
