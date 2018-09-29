<%--
    Document   : RationCardServiceReport
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>

<%

            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
    <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">

    <script>
        function getSubmitDetails(){
            document.forms[0].mode.value="getData";
            document.forms[0].submit();
        }



    </script>

    <body bgcolor="#f2f0e6">
        <html:form  action="/nameRelationChange">
            <html:hidden property="mode"/>


            <table align="center" cellspacing="0" cellpadding="0" width="85%" border="0">
                <tr>
                    <td width="80%" align="center">
                        <table align="center" cellspacing="0" cellpadding="0" width="100%" class="innerTable">
                            <tr>
                                <td  align="center" colspan="5">
                                    <font color="blue" size="2"><b>Name and RelationName Requests</b></font>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle" class="label">District:
                                <td align="left" valign="middle">
                                    <html:select styleId="1" property="district_id" style="height:25px;">

                                        <html:option value="0">ALL</html:option>
                                        <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                                    </html:select>
                                </td>
                                <td align="left" valign="middle" class="label">Status:
                                <td align="left" valign="middle">
                                    <html:select property="status" style="height:25px">

                                        <html:option value="0">ALL</html:option>
                                        <html:option value="1">Pending</html:option>
                                        <html:option value="2">Resolved</html:option>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" align="center">
                                    <html:button  property="sub" value="submit" onclick="return getSubmitDetails();"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="80%" align="center" style="padding:5px">
                        <logic:present name="msg">
                            <center><font color="red">${msg}</font></center>
                        </logic:present>
                    </td>
                </tr>
                <tr>
                    <td width="80%" align="center" style="padding-top:10px">
                        <logic:notEmpty name="nameChangeList" scope="request">
                            <div style="width:900px; height:300px; overflow:scroll;border:1px solid #ccc" align="center">
                                <table align="center" cellpadding="0" cellspacing="0" width="100%" class="innerTable">

                                    <tr>
                                        <td class="formhdbg" align="center">
                                            SNo.
                                        </td>
                                        <td class="formhdbg" align="center">
                                            SADAREM ID
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Name
                                        </td>
                                        <td class="formhdbg" align="center">
                                            RelationName
                                        </td>

                                        <td class="formhdbg" align="center">
                                            District
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Mandal
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Village
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Habitation
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Created Date
                                        </td>
                                        <td class="formhdbg" align="center">
                                            Updated Date
                                        </td>

                                    </tr>

                                    <logic:iterate name="nameChangeList" id="row">
                                        <tr>
                                            <td class="formaltbg">
                                                <%=i++%>.
                                            </td>
                                            <td class="formaltbg">
                                                ${row.personCode}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.name}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.relationName}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.districtName}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.mandalName}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.villageName}
                                                <%--  <html:hidden property="requesttypeidDataForm" value="${row.requesttypeid}"/>--%>
                                            </td>
                                            <td class="formaltbg">
                                                ${row.habitationName}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.created_date}
                                            </td>
                                            <td class="formaltbg">
                                                ${row.updated_date}
                                            </td>

                                            <%-- <td class="formaltbg">
                                                 <a href="nameRelationChange.do?mode=getData&requestId=${row.requestId}&requesttypeId=${row.requesttypeid}&personCode=${row.personCode}">View</a>
                                             </td>--%>
                                        </tr>
                                    </logic:iterate>
                                </table>
                            </div>
                        </logic:notEmpty>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
