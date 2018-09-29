<%-- 
    Document   : totalDataReport
    Created on : 11 Nov, 2013, 11:30:00 AM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page import="org.bf.disability.Constants.CommonConstants,com.tcs.sadarem.util.CommonUtility"%>
<%@page session="true"%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
       
        <%
                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
             %>

        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>


    </head>
    <body>
    

    <html:form styleId="fm1" action="/totalDataReport.do?mode=getAllDataReport" method="post" >
        <html:hidden property="mode"/>
       

        <input type="hidden" name="districtName" id="districtName"/>
        <input type="hidden" name="mandalName" id="mandalName"/>
        <input type="hidden" name="panchayatName" id="panchayatName"/>
        <input type="hidden" name="villageName" id="villageName"/>


        <table align="center" cellspacing="0" cellpadding="0"  border="0" class="inputform" width="50%">
            <tr>
                <th colspan="3">
                  20-39 Percentage Assessed Report
                </th>
            </tr>
            <tr>
                <html:hidden property="district_id" />
                <td align="right">Mandal:</td>
                <td align="left"  width="50%">
                    <html:select styleId="2" property="mandal_id"  >
                        <html:option  value="0" >ALL</html:option>
                        <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                    </html:select>
                </td>
                <td><input type="Submit" name="card" value="Get Excel" class="button" ></td>
            </tr>
            
        </table>
    </html:form>
</body>
</html:html>
