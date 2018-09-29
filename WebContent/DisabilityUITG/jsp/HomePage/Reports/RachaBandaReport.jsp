<%-- 
    Document   : rachaBandaReport
    Created on : Oct 28, 2011, 10:20:09 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%int i = 1;%>
<%int j = 1;%>
<% String districtStatus = request.getParameter("district_id");%>
<html>
    <head>

        <title>SADAREM</title>





        <script>
            function getDetails() {
                document.forms[0].mode.value="getReportDetails";
                document.forms[0].submit();
            }
           
        </script>

    </head>
    <body>

    

    <html:form action="/rachaBanda.do" >

        <html:hidden property="mode"/>

        <logic:present name="msg">
            <table align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr>
            </table>
        </logic:present>
        <p>RachaBanda Details</p>
                <table  align="center" cellspacing="1" cellpadding="5" width="100%"  id="grid">
                    <tr>

                        <td align="center" valign="middle">
                            District : <html:select property="district_id" style="height:25px;">
                                <html:option value="0">ALL</html:option>
                        <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                            </html:select>
                        </td>
                        <td colspan="6" align="center">  <html:button property="but" onclick="getDetails()" value="Submit"/>
                        </td>
                    </tr>




                </table>

        <%--tr>
            <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
            <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
            <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
        </tr>
        --%>
        <br/>

        <bean:define id="totalPensioners" value="0"/>
        <bean:define id="partAEntered" value="0"/>
        <bean:define id="ortho" value="0"/>
        <bean:define id="visual" value="0"/>
        <bean:define id="hearing" value="0"/>
        <bean:define id="mr" value="0"/>
        <bean:define id="mi" value="0"/>
        <bean:define id="md" value="0"/>
        <bean:define id="tot" value="0"/>
        <bean:define id="dr" value="0"/>
        <bean:define id="ar" value="0"/>
        <bean:define id="totalRe" value="0"/>

        <logic:notEmpty name="rachaBandaData">
            <table align="center" cellspacing="1" border="1" cellpadding="4" class="table" width="100%">
                <tr>
                    <th  align="center">
                        Sno
                        </th>
                        <% if (districtStatus.equals("0")) {%>
                    <th  align="center">
                        DISTRICT
                        </th>
                        <%} else {%>
                    <th  align="center">
                        MANDAL
                        </th>
                        <%}%>
                    <th  align="center">
                        TOTAL PENSIONERS
                    </th>
                    <th  align="center">
                        PARTA ENTERED
                    </th>
                    <th colspan="7"  align="center">
                        Assessed & Certificates Issued
                    </th>
                    <th colspan="3"  align="center">
                        Rejected Status
                    </th>
                </tr>
                <tr>
                    <td  align="center">
                        &nbsp;
                    </td>
                    <td  align="center">
                        &nbsp;
                    </td>
                    <td  align="center">
                        &nbsp;
                    </td>
                    <td  align="center">
                        &nbsp;
                    </td>
                  <th  align="center">
                        ORTHO
                   </th>
                  <th  align="center">
                        VISUAL
                   </th>
                  <th  align="center">
                        HEARING
                   </th>
                  <th  align="center">
                        MR
                   </th>
                  <th  align="center">
                        MI
                   </th>
                  <th  align="center">
                        MD
                   </th>
                  <th  align="center">
                        TOTAL
                   </th>
                  <th  align="center">
                        DR
                   </th>
                  <th  align="center">
                        AR
                   </th>
                  <th  align="center">
                        TOTAL
                   </th>
                </tr>
                <logic:iterate name="rachaBandaData" id="row">
                    <bean:define id="totalPensionTotal" value="${totalPensionTotal+row.totalPensioners}"/>
                    <bean:define id="partAEnteredTotal" value="${partAEnteredTotal+row.partAEntered}"/>
                    <bean:define id="orthoTotal" value="${orthoTotal+row.ortho}"/>
                    <bean:define id="visualTotal" value="${visualTotal+row.visual}"/>
                    <bean:define id="hearingTotal" value="${hearingTotal+row.hearing}"/>
                    <bean:define id="mrTotal" value="${mrTotal+row.mr}"/>
                    <bean:define id="miTotal" value="${miTotal+row.mi}"/>
                    <bean:define id="mdTotal" value="${mdTotal+row.md}"/>
                    <bean:define id="totTotal" value="${totTotal+row.total}"/>
                    <bean:define id="drTotal" value="${drTotal+row.dr}"/>
                    <bean:define id="arTotal" value="${arTotal+row.ar}"/>
                    <bean:define id="totalReTotal" value="${totalReTotal+row.totalRe}"/>
                    <tr>
                        <td ">
                            <%=i++%> .
                    </td>
                    <td  align="left">
                        ${row.district}
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=totalPensioners&mandalName=${row.mandal}', 'totalPensioners','resizable=yes, scrollbars=yes,')">${row.totalPensioners}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=partAEntered&mandalName=${row.mandal}', 'partAEntered','resizable=yes, scrollbars=yes,')">${row.partAEntered}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=Locomotor&mandalName=${row.mandal}', 'Ortho','resizable=yes, scrollbars=yes,')">${row.ortho}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=Visual&mandalName=${row.mandal}', 'visual','resizable=yes, scrollbars=yes,')">${row.visual}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=Hearing&mandalName=${row.mandal}', 'hearing','resizable=yes, scrollbars=yes,')">${row.hearing}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=MentalRetradation&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.mr}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=MentalIllness&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.mi}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=MultipleDisability&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.md}</a>
                    </td>
                    <td  align="right">

                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=total&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.total}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=dr&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.dr}</a>
                    </td>
                    <td  align="right">
                        <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=<%=request.getParameter("district_id")%>&modes=ar&mandalName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.ar}</a>
                    </td>
                    <td  align="right">
                        ${row.totalRe}
                       <!-- <a href="javascript:void(0);" onclick ="window.open('rachaBandaPersonal.do?status=getpersonalDetails&mID=${row.id}&dID=${row.did}&modes=totalRe&districtName=${row.mandal}', 'Ratting','resizable=yes, scrollbars=yes,')">${row.totalRe}</a>-->
                    </td>
                </tr>
            </logic:iterate>
            <tr>
                <td  align="center" colspan="2">
                    Total
                </td>
                <td  align="right" >
                    <b> ${totalPensionTotal}</b>

                </td>
                <td  align="right">
                    <b>${partAEnteredTotal}</b>

                </td>
                <td  align="right">
                    <b>${orthoTotal} </b>

                </td>
                <td  align="right">
                    <b>${visualTotal} </b>

                </td>
                <td  align="right">
                    <b>${hearingTotal} </b>

                </td>
                <td  align="right">
                    <b>${mrTotal} </b>

                </td>
                <td  align="right">
                    <b>${miTotal} </b>

                </td>
                <td  align="right">
                    <b>${mdTotal} </b>

                </td>
                <td  align="right">
                    <b>${totTotal} </b>

                </td>
                <td  align="right">
                    <b>${drTotal} </b>

                </td>
                <td  align="right">
                    <b>${arTotal} </b>

                </td><td  align="right">
                    <b>${totalReTotal} </b>

                </td>
            </tr>
        </table><br/>
        <%session.setAttribute("rachaBandaData", (ArrayList) request.getAttribute("rachaBandaData"));%>
        <table align="center" cellpadding="0" cellspacing="0" border="0">
            <a href="rachaBanda.xls?status=getReportExcel&names=<%= request.getParameter("district_id")%>" target="_blank">
                Export To Excel <img src="./DisabilityUITG/images/excel.jpg"
                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
            <a href="rachaBanda.do?status=getReportPrint&names=<%= request.getParameter("district_id")%>" target="_blank">
                Print<img src="./DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
        </table>

    </logic:notEmpty>


</html:form>
</body>
</html>
