<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%        int i = 1;

            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");

            session.setAttribute("district_id", district_id);
            session.setAttribute("mandal_id", mandal_id);
            session.setAttribute("village_id", village_id);
            String districtName = request.getParameter("districtName");
            String mandalName = request.getParameter("mandalName");
            String villageName = request.getParameter("villageName");
            
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>

        <script>


          
            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");

                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;

            }

            function validate_required(field,alerttxt)
            {

                with (field)
                {
                    if (value==null||value=="")
                    {
                        alert(alerttxt);
                        return false
                    }
                    else
                    {
                        return true
                    }
                }
            }
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
        </script>

    </head>
    <body>
        <html:form action="/physicalRequirement.do" method="post">
            <input type="hidden" name="mode"/>
            <input type="hidden" name="districtName"/>
            <input type="hidden" name="mandalName"/>
            <input type="hidden" name="villageName"/>
            <logic:notEmpty name="physicalRequiremenList">
                <P>R7.1 &nbsp; Physical Requirements Report</P>
                <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="50%">
                    <tr>
                        <logic:iterate name="physicalRequiremenList" id="modify" length="1">
                            <logic:present name="mandal">
                                <th >
                                    <a href="physicalRequirement.do" style="color: black"> Back</a>
                                </th>
                            </logic:present>
                            <logic:present name="village">
                                <th>
                                    <a href="physicalRequirement.do?mode=getReportDetails&district_id=${modify.districtId}" style="color: black"> Back</a>
                                </th>
                            </logic:present>

                            <logic:present name="habitation">
                                <th>
                                    <a href="physicalRequirement.do?mode=getReportDetails&district_id=${modify.districtId}&mandal_id=${modify.mandalId}" style="color: black"> Back</a>
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
                        <th align="center" >
                            SNo.
                        </th>
                        <th><bean:write name="ExcelHeader"/></th>
                        <th align="center" >
                            Physical Requirements Details
                        </th>
                    </tr>
                    <logic:iterate name="physicalRequiremenList" id="row">
                        <tr>
                            <td  style="text-align: center">
                                <%=i++%>.
                            </td>
                            <logic:present name="district">
                                <td >
                                    <a href="physicalRequirement.do?mode=getReportDetails&district_id=${row.districtId}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td >
                                    <a href="physicalRequirement.do?mode=getReportDetails&district_id=${row.districtId}&mandal_id=${row.mandalId}"> ${row.name}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td>
                                    <a href="physicalRequirement.do?mode=getReportDetails&district_id=${row.districtId}&mandal_id=${row.mandalId}&village_id=${row.villageId}"> ${row.name}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td>
                                    ${row.name}
                                </td>
                            </logic:present>


                            <logic:present name="district">
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('physicalRequirement.do?mode=getphysicalRequirementPersonalDetails&dID=${row.districtId}&mID=${row.mandalId}&vID=${row.villageId}&dname=<%=districtName%>&mname=<%=mandalName%>&vname=<%=villageName%>', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.personCount}</a>
                                </td>
                            </logic:present>
                            <logic:present name="mandal">
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('physicalRequirement.do?mode=getphysicalRequirementPersonalDetails&dID=${row.districtId}&mID=${row.mandalId}&vID=${row.villageId}&dname=<%=districtName%>&mname=<%=mandalName%>&vname=<%=villageName%>', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.personCount}</a>
                                </td>
                            </logic:present>
                            <logic:present name="village">
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('physicalRequirement.do?mode=getphysicalRequirementPersonalDetails&dID=${row.districtId}&mID=${row.mandalId}&vID=${row.villageId}&dname=<%=districtName%>&mname=<%=mandalName%>&vname=<%=villageName%>', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.personCount}</a>
                                </td>
                            </logic:present>

                            <logic:present name="habitation">
                                <td style="text-align: center">
                                    <a href="javascript:void(0);" onclick ="window.open('physicalRequirement.do?mode=getphysicalRequirementPersonalDetails&dID=${row.districtId}&mID=${row.mandalId}&vID=${row.villageId}&dname=<%=districtName%>&mname=<%=mandalName%>&vname=<%=villageName%>', 'Ratting','resizable=yes, scrollbars=yes,')"> ${row.personCount}</a>
                                </td>

                            </logic:present>

                        </tr>
                    </logic:iterate>
                </table>
                <br>
                <table align="center">
                    <tr>
                        <logic:present name="district">
                        <logic:iterate name="physicalRequiremenList" id="row" length="1">
                            <td>
                                <a href="physicalRequirement.xls?mode=getReportDetails&returnType=Excel&dateType=dateType" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="physicalRequirement.do?mode=getReportDetails&returnType=Print&dateType=dateType" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="mandal">
                        <logic:iterate name="physicalRequiremenList" id="row" length="1">
                            <td>
                                <a href="physicalRequirement.xls?mode=getReportDetails&returnType=Excel&district_id=${row.districtId}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="physicalRequirement.do?mode=getReportDetails&returnType=Print&district_id=${row.districtId}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="village">
                        <logic:iterate name="physicalRequiremenList" id="row" length="1">
                            <td>
                                <a href="physicalRequirement.xls?mode=getReportDetails&returnType=Excel&district_id=${row.districtId}&mandal_id=${row.mandalId}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="physicalRequirement.do?mode=getReportDetails&returnType=Print&district_id=${row.districtId}&mandal_id=${row.mandalId}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    <logic:present name="habitation">
                        <logic:iterate name="physicalRequiremenList" id="row" length="1">
                            <td>
                                <a href="physicalRequirement.xls?mode=getReportDetails&returnType=Excel&district_id=${row.districtId}&mandal_id=${row.mandalId}&village_id=${row.villageId}" target="_blank">
                                    Export To Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td>
                            <td>
                                <a href="physicalRequirement.do?mode=getReportDetails&returnType=Print&district_id=${row.districtId}&mandal_id=${row.mandalId}&village_id=${row.villageId}" target="_blank">
                                    Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                            </td>
                        </logic:iterate>
                    </logic:present>
                    </tr>
                   
                </table>
            </logic:notEmpty>

        </html:form>
    </body>

</html>