<%-- 
    Document   : DoctorSign
    Created on : 5 Aug, 2014, 6:19:00 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
            int i = 1;
            int totalDisability = 0;
            if (request.getAttribute("totalDisability") != null) {
                totalDisability = (Integer) request.getAttribute("totalDisability");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>

            function checkBoxGroup(groupName) {
                var checkedCount=0;
                for(var i = 0;i<groupName.length;i++)  {
                    if(groupName[i].checked==true)  {
                        checkedCount +=1;
                    }
                }
                if(checkedCount <=0 )  {
                    return false;
                }
                return true;
            }

            function checkAll(field,id)
            {

                if(document.forms[0].elements[id].checked==true) {
                    for (i = 0; i < field.length; i++) {
                        field[i].checked = true ;
                    }
                }else {
                    for (i = 0; i < field.length; i++) {
                        field[i].checked = false ;
                    }
                }

            }

            function signData() {
                if(!checkBoxGroup(document.getElementsByName("doSign"))) {
                    alert("Select Atleast one PWD for Sign.......");
                    return false;
                }else {
                    document.forms[0].elements["mode"].value="signData";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body>
        <html:form action="/doctorSign">
            <html:hidden property="mode"/>
            <br/>
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>
            <br/>
            <logic:notEmpty name="pwdDetails">
                <div style="overflow:auto; width:950px; height:350px; vertical-align: top">
                    <table align="center" cellspacing="1" cellpadding="0"  border="0"  class="inputform" width="98%">
                        <tr>
                            <th>
                                S No
                            </th>
                            <th>
                                SADAREM ID
                            </th>
                            <th>
                                Person Name
                            </th>
                            <th>
                                Type of disability
                            </th>
                            <th>
                                Percentage
                            </th>
                            <th>
                                Assessed Date
                            </th>
                            <th>
                                Certificate
                            </th>
                            <th>
                                ID Card
                            </th>
                            <th>
                                Railway Certificate
                            </th>
                            <th>
                                RTC Pass
                            </th>
                            <th>
                                Percentage Calculation
                            </th>
                            <th>
                                Doctor Details
                            </th>
                            <%--<th>
                                Check All <input type="checkbox" name="chk" onClick="checkAll(document.forms[0].doSign,'chk')"/>
                            </th>--%>
                        </tr>

                        <logic:iterate id="row" name="pwdDetails">
                            <tr>
                                <td align="center">
                                    <%=i++%> .
                                </td>
                                <td>
                                    ${row.sadaremId}
                                </td>
                                <td>
                                    ${row.personName}
                                </td>
                                <td>
                                    ${row.disability}
                                </td>
                                <td style="text-align: center;">
                                    ${row.percentage} %
                                </td>
                                <td>
                                    ${row.assessedDate}
                                </td>
                                <% request.setAttribute("view", "View");%>
                                <bean:define id="certificate" value="${row.certificate}"/>
                                <td  >
                                    <logic:equal value="<%=certificate.toString()%>" name="view" scope="request">

                                        <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=${row.sadaremId}&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">${row.certificate}</a>

                                    </logic:equal>
                                    <logic:notEqual value="<%=certificate.toString()%>" name="view" scope="request">
                                        ${row.certificate}
                                    </logic:notEqual>
                                </td>
                                <bean:define id="idCard" value="${row.idCard}"/>
                                <td  >
                                    <logic:equal value="<%=idCard.toString()%>" name="view" scope="request">

                                        <a href="javascript:void(0);"  onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=idcard&personcode=${row.sadaremId}&display=idcardjsp', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.idCard}</a>
                                    </logic:equal>
                                    <logic:notEqual value="<%=idCard.toString()%>" name="view" scope="request">
                                        ${row.idCard}
                                    </logic:notEqual>
                                </td>

                                <bean:define id="railwaycertificate" value="${row.railwayCertificate}"/>
                                <td>
                                    <logic:equal value="<%=railwaycertificate.toString()%>" name="view" scope="request">

                                        <a href="javascript:void(0);"  onclick ="window.open('CertificateWithPersoncode.do?print=railwayform&flag=false&doctor=doctor&personcode=${row.sadaremId}', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.railwayCertificate}</a>
                                    </logic:equal>
                                    <logic:notEqual value="<%=railwaycertificate.toString()%>" name="view" scope="request">
                                        ${row.railwayCertificate}
                                    </logic:notEqual>
                                </td>
                                <bean:define id="rtccertificate" value="${row.rtcCertificate}"/>
                                <td>
                                    <logic:equal value="<%=rtccertificate.toString()%>" name="view" scope="request">

                                        <a href="javascript:void(0);"  onclick ="window.open('apsrtcCertificate.do?mode=certificatePrint&personcode=${row.sadaremId}', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.rtcCertificate}</a>
                                    </logic:equal>
                                    <logic:notEqual value="<%=rtccertificate.toString()%>" name="view" scope="request">
                                        ${row.rtcCertificate}
                                    </logic:notEqual>
                                </td>
                                <td>
                                   <a href="javascript:void(0);"  onclick ="window.open('showCalculationsAction.do?mode=excute&doctor=doctor&personcode=${row.sadaremId}&decisionparameter=true', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.percentageCalculation}</a>

                                </td>
                                <td>
                                    <a href="javascript:void(0);"  onclick ="window.open('getDataEnteredFields.do?getDataEnteredFieldsDetails=getDataEnteredFieldsDetails&personcode=${row.sadaremId}&decisionparameter=true&doctor=doctor', 'Ratting','resizable=yes, scrollbars=yes,')"> View</a>

                                    
                                </td>
                               <%-- <td align="center">
                                    <html:checkbox property="doSign" value="${row.encString}"/>
                                </td>--%>
                            </tr>
                        </logic:iterate>


                    </table>
                </div>
               <%-- <table align="center" cellspacing="1" cellpadding="0"  border="0"  class="inputform" width="98%">
                    <tr>
                        <th colspan="13" align="center">
                            <html:button property="but" value="Sign" onclick="signData();"/>
                        </th>
                    </tr>
                </table>--%>
            </logic:notEmpty>
        </html:form>
    </body>
</html>
