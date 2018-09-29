<%-- 
    Document   : GrievancesRequestDetails
    Created on : Mar 13, 2013, 5:06:26 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
try
{
	//System.out.println("HI DUDE");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

        <script>
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function getDetails(){
                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();


            }
        </script>

        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="grievancesRequestDetails">
            <html:hidden property="mode"/>
            
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>

                <table  align="center" cellspacing="1" cellpadding="0" border="0"  class="inputform" width="90%">
                <tr >
                    <th colspan="6">RD Call Center Request Status</th>
                </tr>
              
                <tr>
                    <td>Request Type :</td>
                    <td>
                        <html:select property="requestId">
                            <html:option  value="000">--ALL--</html:option>
                            <html:optionsCollection property="requestList" label="requestName" value="requestId"/>
                        </html:select></td>
                    <td>Status:</td>
                    <td>
                        <html:select property="status">
                            <html:option  value="ALL">--ALL--</html:option>
                            <html:option  value="Pending">Pending</html:option>
                            <html:option  value="Closed">Closed</html:option>
                        </html:select>
                    </td>
                    <td>Request No : </td>
                    <td><html:text property="requestNumber" onkeypress="return onlyNumbers()"/></td>

                </tr>
                <tr>
                    <th colspan="6"><html:button  property="sub" value="Submit" onclick="getDetails();"/>
                    </th>
                </tr>
            </table>


            <br>

            <%--</td>
        </tr>
    </table>--%>
            <%int i = 1;%>

            <logic:notEmpty name="request">
            <div style="overflow:scroll; width:950px; height:335px" align="center">
                <table border="0" cellpadding="0" cellspacing="1" width="90%" class="inputform" align="center">
                    <tr>
                        <th>
                            SNo.
                        </th>
                        <th>Request Number</th>
                        <th>SADAREM ID</th>
                        <th>Pension Card No </th>
                        <th>Name </th>
                        <th>Relation Name </th>
                        <th>Generated SADAREM ID</th>
                        <th>Request Type</th>
                        <th>Mandal</th>
                        <th>Status</th>
                    </tr>
                    <logic:iterate id="row" name="request">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>
                            <td>${row.requestNumber}</td>
                            <td>${row.Person_Code}</td>
                            <td>${row.pensioncard_no}</td>
                            <td>${row.Name}</td>
                            <td>${row.RelationName}</td>
                            <td>${row.SadaremID}</td>
                            <td>${row.SubRequestId}</td>
                            <td>${row.MandalId}</td>
                            <td>${row.SaStatus}</td>
                        </tr>

                    </logic:iterate>
                </table>
 </div>
            </logic:notEmpty>
           



        </html:form>
    </body>
</html>
<%

}
catch(Exception e)
{
	e.printStackTrace();
}
%>