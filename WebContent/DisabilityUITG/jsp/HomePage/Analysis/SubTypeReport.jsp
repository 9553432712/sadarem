

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%
            int i = 1;
            String type = null;
            String disid = null;
            String disabulityid = null;
            String ssid = null;
            String dname = null, mname = null, vname = null, hname = null, disname = null, sname = null;
            String did = request.getParameter("districts_id");
            String mid = request.getParameter("mandal_id");
            String vid = request.getParameter("village_id");
            String hid = request.getParameter("habitation_id");
            if (request.getAttribute("id1") != null) {
                disabulityid = request.getAttribute("id1").toString();
            }
            if (request.getAttribute("ddid") != null) {
                disid = request.getAttribute("ddid").toString();
            }
            if (request.getAttribute("sdid") != null) {
                ssid = request.getAttribute("sdid").toString();
            }
            if (request.getAttribute("dn") != null) {
                dname = request.getAttribute("dn").toString();
            }
            if (request.getAttribute("mn") != null) {
                mname = request.getAttribute("mn").toString();
            }
            if (request.getAttribute("vn") != null) {
                vname = request.getAttribute("vn").toString();
            }
            if (request.getAttribute("hn") != null) {
                hname = request.getAttribute("hn").toString();
            }
            if (request.getAttribute("ddn") != null) {
                disname = request.getAttribute("ddn").toString();
            }
            if (request.getAttribute("sdn") != null) {
                sname = request.getAttribute("sdn").toString();
            }
            ArrayList alist = new ArrayList();
            if (request.getAttribute("addressDetailsList") != null) {
                alist = (ArrayList) request.getAttribute("addressDetailsList");
            }
            ArrayList al = new ArrayList();
            if (request.getAttribute("subTypeReportList") != null) {
                al = (ArrayList) request.getAttribute("subTypeReportList");
            }
            String s = "";
            if (al.size() > 0) {
                for (int j = 0; j < al.size(); j++) {
                    Map m = (Map) al.get(j);
                    s = m.get("total").toString();
                }
            }

%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script>
            function getSubReport(){
                if(document.forms[0].subTypeOfDisability_id.value=="0"){
                    alert("Please Select SubDisability");
                    document.forms[0].subTypeOfDisability_id.focus();
                    return false;
                }
         
                document.forms[0].mode.value="getSubReportDetails";
                document.forms[0].submit();
            }
            function getDetails(){
                document.forms[0].mode.value="getFullDetails";
                document.forms[0].submit();
            }
            
        </script>
    </head>

    <body>

        <html:form action="/subTypeReport" >
            <html:hidden property="mode"/>
            <html:hidden property="typeOfDisability_id" value="<%=disabulityid%>" />
            <table width="70%" border="1" cellspacing="0" cellpadding="10"  align="center" class="inputform">
                <tr><th colspan="4">Sub Type Report</th></tr>
                <tr>
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"  align="center" class="inputform">
                            <tr>
                                <td >
                                    Subtype of Disability<font color="red">*</font></td>

                                <td align="left" valign="middle">
                                    <html:select styleId="1" property="subTypeOfDisability_id" style="width:250px" >
                                        <html:option  value="0">--SELECT--</html:option>
                                        <html:optionsCollection   property="subTypeOfDisabilityList" label="subdisabilityname" value="subdisabilityid"  />
                                    </html:select>
                                </td>
                               
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> <th align="center">
                                    <html:button property="but" value="Get Report" onclick="getSubReport();" />
                                </th></tr>
            </table>

            <logic:present name="nodata">
                <p id="errmsg"><bean:write name="nodata"/></p>
            </logic:present>
            <br/>
            <logic:notEmpty name="subTypeReportList">
                <table width="70%" align="center" cellpadding="0" cellspacing="1" border="0" class="inputform">
                    <tr>
                        <logic:iterate name="subTypeReportList" id="row" length="1">
                            <logic:present name="mandal">
                                <th >
                                    <a href="subTypeReport.do?mode=getSubReportDetails&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}" style="color: black"> Back</a>
                                </th>
                            </logic:present>
                            <logic:present name="village">
                                <th>
                                    <a href="subTypeReport.do?mode=getSubReportDetails&district_id=${row.district_id}&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}" style="color: black"> Back</a>
                                </th>
                            </logic:present>

                            <logic:present name="habitation">
                                <th>
                                    <a href="subTypeReport.do?mode=getSubReportDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}" style="color: black"> Back</a>
                                </th>
                            </logic:present>
                        </logic:iterate>
                        <logic:present name="names">
                            <th style="text-align: center" colspan="11">
                                <bean:write name="names"/>
                            </th>
                        </logic:present>
                    </tr>
                    <tr>
                        <logic:present name="nodata">
                        <p id="errmsg"> <bean:write name="nodata"/></p>
                        </logic:present>
                    </tr>
                    <tr>
                        <th align="center">
                            S No
                        </th>
                        <th align="center">
                            <bean:write name="ExcelHeader"/>
                        </th>
                        <th align="center">
                            Count
                        </th>

                    </tr>
                    <logic:iterate name="subTypeReportList" id="row">
                        <tr>
                            <td  style="text-align: center">
                                <%=i++%>
                            </td>
                            <logic:present name="district">
                                <td >
                                    <a href="subTypeReport.do?mode=getSubReportDetails&district_id=${row.district_id}&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td >
                                    <a href="subTypeReport.do?mode=getSubReportDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td>
                                    <a href="subTypeReport.do?mode=getSubReportDetails&district_id=${row.district_id}&mandal_id=${row.mandal_id}&village_id=${row.village_id}&disability_id=${row.disability_id}&subTypeOfDisability_id=${row.subTypeOfDisability_id}"> ${row.name}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td>
                                    ${row.name}
                                </td>
                            </logic:present>

                            <td width="12%" style="text-align: center">

                                <a href="javascript:void(0);" onclick ="window.open('subTypeReport.do?mode=getFullDetails&types=<%=type%>&ddID=${row.district_id}&mmID=${row.mandal_id}&vvID=${row.village_id}&hhID=${row.habitation_id}&ddsID=${row.disability_id}&ssID=${row.subTypeOfDisability_id}&countId=${row.id}')" >${row.count}</a>

                            </td>
                        </tr>
                    </logic:iterate>
                    <tr>
                        <th  colspan="2" style="text-align: center">Total</th>
                        <th style="text-align: center">
                            <%=s%>
                        </th>



                    </tr>
                </table>
                <table align="center">
                    <tr>
                        <td>

                            <a href="subTypeReport.xls?mode=getSubTypeReportCountExcelData&totalCount=<%=s%>" target="_blank">Export Excel
                                <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        </td>
                    </tr>

                </table>
            </logic:notEmpty>
        </html:form>
    </body>
</html:html>
