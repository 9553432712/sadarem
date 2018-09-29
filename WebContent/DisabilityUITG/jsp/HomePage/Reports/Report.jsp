<%-- 
    Document   : Report
    Created on : 5 Mar, 2014, 3:34:36 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>

        <title>SADAREM</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>

        <script>
            function openWindow(districtId,mandalId,mandalName,districtName,flag) {
                window.open("./shgPersonalDetails.do?mode=getShgPersonalDetails&districtId="+districtId+"&mandalId="+mandalId+"&mandalName="+mandalName+"&districtName="+districtName+"&flag="+flag+"","_blank","toolbar=no, scrollbars=yes, resizable=yes,width=1100, height=800");
            }
            function getDetails() {
                
                if(document.forms[0].elements['district_id'].value=="" || document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District");
                    document.forms[0].elements['district_id'].focus();
                    return false;
                }
                else{
                    document.forms[0].mode.value="getSHGAbstract";
                    document.forms[0].submit();
                }
            }
            
        </script>
    </head>
    <body>
        <html:form action="/report">
            <html:hidden property="mode"/><br/>

          
            <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">

                      <tr>
                    <td>
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                <tr>
                    <th colspan="6">R7.1 : SHG's Report</th>
                </tr>
                <tr>
                    <td>District<font color="red"><b>*</b></font></td>
                    <td>
                        <html:select styleId="1" property="district_id" onchange="districtNonUrbanList();" >
                            <html:option value="0">--SELECT--</html:option>
                            <html:optionsCollection   property="districtlist" label="district_name" value="district_id"  />
                        </html:select>
                    </td>

                    <td >Mandal</td>
                    <td>
                        <html:select styleId="2" property="mandal_id"  >
                            <html:option  value="0">--ALL--</html:option>
                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <th colspan="6" align="center">
                        <html:button property="but" onclick="getDetails()" value="Submit"/>
                    </th>
                </tr>
                
                 </table>
                    </td>
                </tr>
            </table>
                    <br>
                      <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

            <logic:notEmpty name="shgAbstract">
                <br>
                <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" >
                    <tr>
                        <td  align="center" valign="top">

                            <table border="0" cellspacing="1" cellpadding="0" width="100%" align="center" class="inputform">
                                <tr >
                                    <th  colspan="11"align="center"> SHG's Report</th></tr>
                                <tr>

                                    <th>
                                        S No
                                    </th>
                                    <th>
                                        Mandal
                                    </th>
                                    <th>
                                        Total Applied
                                    </th>
                                    <th>
                                        Age (<18)  PwDs
                                    </th>
                                    <th>
                                        Age (0-50)  PwDs
                                    </th>
                                    <th>
                                        Age (> 50)  PwDs
                                    </th>
                                    <th>
                                        Dead  PwDs
                                    </th>
                                    <th>
                                        Migratant  PwDs
                                    </th>
                                   <%-- <th>
                                        Pensioners Who can be deleted
                                    </th>--%>
                                    <th>
                                        Eligible  PwDs for SHG's
                                    </th>
                                    <th>
                                        PwD's Mapped for SHG's
                                    </th>
                                    <th>
                                      To Be Mapped
                                    </th>

                                </tr>
                                <logic:empty name="shgAbstract">
                                    <tr>
                                        <td align="left" colspan="8">
                                            <font color="red" size="3">Data Not Available</font>
                                        </td>
                                    </tr>
                                </logic:empty>

                                <logic:iterate id="row" name="shgAbstract">
                                    <tr>
                                        <td align="center">
                                            ${row.sno}.
                                        </td>
                                        <td align="left">
                                            ${row.madnalName}
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',1);"> ${row.total_pensioners}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',2);">${row.Age18Pensioners}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',3);"> ${row.Age18to55Pensioners}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',4);"> ${row.Age55Pensioners}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',5);"> ${row.DeadPensioners}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',6);"> ${row.Migratant_Pensioners}</a>
                                        </td>
                                       <%-- <td align="center">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',7);">${row.Pensioners_Who_can_be_deleted}</a>
                                        </td>--%>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',8);">${row.Eligible_pensioners}</a>
                                        </td>
                                        <%--<td style="text-align: center;">
                                            ${row.totalSHGMembers}
                                        </td>--%>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',9);">${row.totalSHGMembers}</a>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="javascript:void(0)" onclick="openWindow('${row.districtId}','${row.madnalId}','${row.madnalName}','${row.districtName}',10);">${row.percentageSHGMembers}</a>
                                        </td>

                                       <%-- <td style="text-align: center;">
                                            ${row.percentageSHGMembers}
                                        </td>--%>
                                    </tr>
                                </logic:iterate>
                                <logic:iterate id="row" name="totalList">
                                    <tr>

                                        <td align="left" colspan="2">
                                            ${row.madnalName}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                             ${row.total_pensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                            ${row.Age18Pensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                            ${row.Age18to55Pensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                             ${row.Age55Pensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                           ${row.DeadPensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                            ${row.Migratant_Pensioners}
                                        </td>
                                        <%--<td align="center">
                                            ${row.Pensioners_Who_can_be_deleted}
                                        </td>--%>
                                        <td style="text-align: center;font-weight: bold;">
                                            ${row.Eligible_pensioners}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                          ${row.totalSHGMembers}
                                        </td>
                                        <td style="text-align: center;font-weight: bold;">
                                            ${row.percentageSHGMembers}
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </table>
                <br/>
                <a href="shgAbstractExcel.xls" target="_blank">
                    Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                         height="25" width="25"/></a>
                    <%--    <%=request.getAttribute("excel")%>--%>
                </logic:notEmpty>

        </html:form>
    </body>
</html>
