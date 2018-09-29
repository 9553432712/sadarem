<%--
    Document   : issueResolve
    Created on : Nov 29, 2012, 4:36:00 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList"%>
<%


            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
    <body>
        <html:form action="/issueRaiseApproval">

           <table align="center" cellspacing="1"  cellpadding="0" width="50%" class="inputform">
                <tr>
                    <td>
                        <logic:present name="msg">
                            <center>${msg}</center>
                        </logic:present>
                        <logic:notEmpty name ="rationCardNotOpenList">

                            <table class="inputform" align="center" cellspacing="1" cellpadding="0" width="100%" >

                                            <tr align="left">
                                                <th colspan="4">
                                   Pension Card Details </th>
                                            </tr>
                                            <tr>
                                                <th>
                                            
                                                    S.NO
                                                </th>
                                                <th>
                                                   RationCardNo
                                                </th>
                                                <th>
                                                   PensionId
                                                </th>
                                                <th>
                                                   Name
                                                </th>
                                                 <th>
                                                   Relation Name
                                                </th>
                                                <th>
                                                  Status
                                                </th>
                                            </tr>

                                            <logic:iterate  name="pensionIdNotOpenList" id="row">
                                                <tr>
                                                    <td  align="center">
                                                        <%=i++%>
                                                    </td>
                                                    <td  align="center">
                                                        ${row.rationCardNo}
                                                    </td>
                                                    <td  align="center">
                                                        ${row.pensionCardNo}
                                                    </td>
                                                    <td  align="center">
                                                        ${row.name}
                                                    </td>
                                                    <td  align="center">
                                                        ${row.relationName}
                                                    </td>
                                                     <td  align="center">
                                                        ${row.status}
                                                    </td>

                                                </tr>

                                            </logic:iterate>


                                       
                                
                                <tr>
                                    <td class="label" align="center" colspan="4">
                                        <img src="./DisabilityUITG/images/close.png" onclick="javascript:window.close();"/>
                                    </td>
                                </tr>
                                  </table>
                            </logic:notEmpty>

                      

                    </td>
                </tr>
            </table>

          
        </html:form>
    </body>
</html>
