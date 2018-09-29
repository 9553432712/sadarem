<%--
    Document   : DirectorReport
    Created on : 1 Aug, 2014, 6:28:50 PM
    Author     : 842412
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.ReportDTO" %>
<%@page import="org.bf.disability.Constants.CommonConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <title>SADAREM</title>
        <%
                    ArrayList stateReport = new ArrayList();
                    stateReport = (ArrayList) request.getAttribute("stateReport");


        %>


    </head>
    <%-- <body onload="ShowDate(),bodyLoadfunction();">--%>
    <body>
        <html:form styleId="fm1" action="/mandalClusterlevelPwd.do?mode=getPwdsIndividualCount" method="post" >
            <html:hidden property="mode"/>

            <br/>
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>
            <%--<logic:notPresent name="pwdsIndividualCount">
                <table cellspacing="1" border="0" cellpadding="0" width="96%" align="center" >
                    <tr>
                        <td>
                            ${msg}
                        </td>
                        <logic:present name="msg" >
                        <td>
                            ${msg}
                        </td>
                        </logic:present>
                    </tr>
                </table>
            </logic:notPresent>--%>
            <logic:present name="pwdsIndividualCount" scope="request">
                
                    <table cellspacing="1" border="0" cellpadding="0" style="width: 800px" align="center" >
                        <tr>
                            <td>
                                <table cellspacing="1" border="0" cellpadding="0" width="100%" align="center" class="inputform">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                            <tr>
                                <logic:present name="dateWise">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                         <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"   style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0" >
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&fromdate=${id.fromdate}&todate=${id.todate}&districtName=${id.districtname}&campDate=${id.campdate}&district_ID=${id.districtID}&typeOfSearch=${id.typeOfSearch}&individual=individual"target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="medicalboardWise">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                         <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;camp::&nbsp;${id.campname}&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"   style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0" >
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&campname=${id.campname}&districtName=${id.districtname}&districtID=${id.district_id}&campid=${id.campid}&typeOfSearch=${id.typeOfSearch}&districtName=${id.districtname}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="disabilityWise">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                         <td colspan="17">
                                            Disability::&nbsp;${id.disability}&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"   style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0" >
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&district_ID=${id.district}&disabilityid=${id.disabilityid}&disabilityname=${id.disability}&campname=${id.campname}&districtName=${id.districtname}&districtID=${id.district_id}&campid=${id.campid}&typeOfSearch=${id.typeOfSearch}&districtName=${id.districtname}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="districtWise">
                                <logic:present name="district">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                        <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;Mandal::&nbsp;ALL&nbsp;&nbsp;Village::&nbsp;ALL&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                         </td>
                                        <td id="dt"   style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0" >
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&districtid=${id.districtid}&districtName=${id.districtname}&disabilityid=${id.disabilityid}&typeOfSearch=${id.typeOfSearch}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="mandal">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                        <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;Mandal::&nbsp;${id.mandalname}&nbsp;&nbsp;Village::&nbsp;ALL&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"   style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0">
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&disabilityid=${id.disabilityid}&typeOfSearch=${id.typeOfSearch}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}&districtName=${id.districtname}&mandalName=${id.mandalname}&districtid=${id.districtid}&mandalid=${id.mandalid}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="village">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                        <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;Mandal::&nbsp;${id.mandalname}&nbsp;&nbsp;Village::&nbsp;${id.villagename}&nbsp;&nbsp;Habitation::&nbsp;ALL&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"  style="text-align: right">
                                           <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0">
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&villageid=${id.villageid}&disabilityid=${id.disabilityid}&typeOfSearch=${id.typeOfSearch}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}&districtName=${id.districtname}&mandalName=${id.mandalname}&villageName=${id.villagename}&districtid=${id.districtid}&mandalid=${id.mandalid}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                <logic:present name="habitation">
                                    <logic:iterate id="id" name="pwdsIndividualCount" length="1" scope="request">
                                        <td colspan="17">
                                            District::&nbsp;${id.districtname}&nbsp;&nbsp;Mandal::&nbsp;${id.mandalname}&nbsp;&nbsp;Village::&nbsp;${id.villagename}&nbsp;&nbsp;Habitation::&nbsp;${id.habitationname}&nbsp;&nbsp;From Date::&nbsp;${id.fromdate}&nbsp;&nbsp;To Date::&nbsp;${id.todate}
                                        </td>
                                        <td id="dt"   style="text-align: right">
                                            <img src="./DisabilityUITG/images/excel-ico.jpg" width="27" height="23" border="0">
                                            <a href="mandalClusterlevelPwd.do?mode=excelWriting&habitationName=${id.habitationname}&disabilityid=${id.disabilityid}&typeOfSearch=${id.typeOfSearch}&todate=${id.todate}&individual=individual&fromdate=${id.fromdate}&districtName=${id.districtname}&mandalName=${id.mandalname}&villageName=${id.villagename}&habitationName=${id.habitationname}&districtid=${id.districtid}&mandalid=${id.mandalid}&villageid=${id.villageid}&habitationid=${id.habitationid}" target="_blank">
                                                Excel &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                        </td>
                                    </logic:iterate >
                                </logic:present >
                                </logic:present>
                            </tr>
                        </logic:iterate >
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="overflow:auto; width:1000px; height:350px;vertical-align: top" >
                                <table cellspacing="1" border="0" cellpadding="0" align="center" class="inputform">
                                    <tr>
                            <th colspan="20">
                                Mandal,Cluster level Pwd Individual Details
                            </th>
                        </tr>
                        <tr>
                            <th >S.No</th>
                            <th >Pension ID</th>
                            <th >SADAREM ID</th>
                            <th  >Name </th>
                            <th >Relation Name</th>
                            <th >Gender</th>
                            <th >Age</th>
                            <th >Ration Card Number</th>
                            
                            <%--<th >District Name</th>--%>
                            <th >Mandal </th>
                            <th >Village </th>
                            <th >Habitation </th>
                            <th >Disability</th>
                            <th >Pension Phase</th>
                            <th >House No</th>
                            <%--<th >Category</th>--%>
                            <th >Camp Name,Address</th>
                            <th >Camp Date</th>
                            <th >Phone No</th>
                        </tr>
                        <%--</logic:present>--%>
                        <% int i = 0;%>
                        <logic:iterate id="id" name="pwdsIndividualCount" scope="request">
                            <tr >
                                <td style="text-align: center"><%=++i%></td>
                                <td style="text-align: center">${id.pensionid}</td>
                                <td style="text-align: center">${id.sadaremid}</td>
                                <td>${id.name}</td>
                                <td>${id.relationname}</td>
                                <td >${id.gender}</td>
                                <td style="text-align: center">${id.age}</td>
                                <td style="text-align: center">${id.rationno}</td>
                               <%-- <td>${id.districtname}</td>--%>
                                <td>${id.mandal}</td>
                                <td >${id.village}</td>
                                <td>${id.habitation}</td>
                                <td >${id.disabilityType}</td>
                                <td style="text-align: center">${id.pensionphase}</td>
                                <td style="text-align: center">${id.houseno}</td>
                                <%--<td>${id.category}</td>--%>
                                <td >${id.campname}</td>
                                <td style="text-align: center">${id.campdate}</td>

                                <td style="text-align: center">${id.phone}</td>
                            </tr>
                        </logic:iterate>
                                </table>
                         </div>
                            </td>
                        </tr>
                        
                    </table>
               
                <%--</logic:notPresent>--%>


                <%--</logic:present>--%>
            </logic:present>



        </html:form>
    </body>


    <script src="./DisabilityUITG/js/jquery-min.js"></script>
    <logic:present name="camp">
        <script>
            document.getElementById("cmp").style.display="";
            document.getElementById("cmpval").style.display="";
            document.getElementById("data").style.display="";
        </script>
    </logic:present>
    <logic:present name="cmpStat">
        <script>
            document.getElementById("campwise").style.display="";
            document.getElementById("submitButton").style.display="";
        </script>
    </logic:present>
    <script>
        // The below two methods are for getting the table data



        function getCamps() {
            document.forms[0].elements["mode"].value="unspecified";
            document.forms[0].submit();
        }
    </script>

</html>
