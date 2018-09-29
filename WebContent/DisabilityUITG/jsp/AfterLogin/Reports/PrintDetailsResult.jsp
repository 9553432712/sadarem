<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%
            int i = 1;
            String did = request.getAttribute("distId").toString();
%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
       

        <script>
            function getPrintData(){
                document.forms[0].mode.value="getPrintDetails";
                document.forms[0].submit();
            }
            

        </script>
    </head>

    <body >

    
    <html:form action="/printDetailsResult" >
        <html:hidden property="mode"/>
        <html:hidden property="districtid" value="<%=did%>" />

        <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="50%">
            <tr>
                <th colspan="3">
                    Certificate Prints
                </th>
            </tr>
            <tr>
                <td> Camp</td>
                <td>
                    <html:select styleId="1" property="camp_id" style="width:510px">
                        <html:option  value="0">--ALL--</html:option>
                        <html:optionsCollection   property="campList" label="camp_name" value="camp_id"  />
                    </html:select>
                </td>
                <td> <html:button property="but" value="Get Report" onclick="getPrintData();"/></td>
            </tr>
           
        </table>
        <br/>
        <logic:present name="nodata">
            <center><font color="red" size="3"><b><bean:write name="nodata"/></b></font></center>
        </logic:present>
        <logic:notEmpty name="campDetailsList">
            <table width="90%"  align="center" cellpadding="0" cellspacing="1" border="0" class="inputform">
                <tr>
                    <th align="center">
                        S No
                    </th>
                    <th>
                        Camp Name
                    </th>
                    <th align="center">
                        Total
                    </th>

                </tr>
                <logic:iterate name="campDetailsList" id="row">
                    <tr>
                        <td>
                            <%=i++%>
                        </td>
                        <td>
                            ${row.camp_name}
                        </td>
                        <td width="12%" class="formaltbg"  align="center">

                            <a href="javascript:void(0);"  onclick ="window.open('printDetailsResult.do?mode=getPrintCountFullDetails&distid=<%=did%>&selectedId=${row.camp_id}')">${row.count}</a>

                        </td>

                    </tr>
                </logic:iterate>

            </table>

        </logic:notEmpty>

    </html:form>
</body>
</html:html>
