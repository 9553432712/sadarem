<%--
    Document   : exportExcelAllData
    Created on : Jun 6, 2013, 6:11:35 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script type="text/javascript">
            function ShowDate()
            {

                var dt = new Date();
                var d ;
                if(dt.getDate()==1){
                    var LastDayPrevMonth = new Date(dt.getFullYear(), dt.getMonth(), 0).getDate();
                    if(dt.getMonth()==0){
                        d=LastDayPrevMonth+"/"+(dt.getMonth()+12)+"/" +(dt.getFullYear()-1);
                    }
                    else{
                        d=LastDayPrevMonth+"/"+(dt.getMonth()) +"/" +dt.getFullYear();
                    }
                }
                else{

                    d =dt.getDate()-1+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                }
                document.getElementById(8).value = d;

            }
            function getVillages(){

                document.forms[0].mode.value="getVillages";
                document.forms[0].submit();
            }

            function getRequestDetails(){
                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;
                var yye=fromDate.substr(6,4);
                var mme=fromDate.substr(3,2);
                var dde=fromDate.substr(0,2);
                var yyb=toDate.substr(6,4);
                var mmb=toDate.substr(3,2);
                var ddb=toDate.substr(0,2);
                var dob = new  Date();
                var etd = new  Date();
                var today=new Date();
                etd.setFullYear(yye,mme-1,dde);
                dob.setFullYear(yyb,mmb-1,ddb)
                var y1=today.getYear();
                var y2= dob.getYear();
                if(fromDate==null ||fromDate==""){
                    alert("Please enter From date");
                    document.forms[0].fromdate.focus();

                    return false;
                }  if(toDate==null ||toDate==""){
                    alert("Please enter to date");
                    document.forms[0].todate.focus();

                    return false;
                }
                if (today < etd)
                {
                    alert("From date is after Todays Date");
                    document.forms[0].fromdate.value="";
                    return false;
                }
                if (today-1 < dob)
                {
                    alert("To date is before Today's Date");
                    document.forms[0].todate.value="";
                    return false;
                }
                if (dob < etd)
                {
                    alert("From date is greater than To date");
                    document.forms[0].fromdate.value="";
                    return false;
                }

                else{

                    document.forms[0].mode.value="getRequestDetails";
                    document.forms[0].submit();
                    return true;
                }
            }
        </script>
        <script type="text/javascript">
            <!--
            spe=500;
            na=document.all.tags("blink");
            swi=1;
            bringBackBlinky();
            function bringBackBlinky() {
                if (swi == 1) {
                    sho="visible";
                    swi=0;
                }
                else {
                    sho="hidden";
                    swi=1;
                }
                for(i=0;i<na.length;i++) {
                    na[i].style.visibility=sho;
                }
                setTimeout("bringBackBlinky()", spe);
            }
            -->
        </script>
    </head>
    <body onload="ShowDate();">
        <%String toDate = "";
                    String district = "";
                    String mandal = "";
                    String village = "";
                    String pensionPhase = "";
                    String fromDate = "";

                    if (request.getAttribute("toDate") != null) {
                        toDate = request.getAttribute("toDate").toString();
                    }
                    if (request.getAttribute("district") != null) {
                        district = request.getAttribute("district").toString();
                    }
                    if (request.getAttribute("mandal") != null) {
                        mandal = request.getAttribute("mandal").toString();
                    }
                    if (request.getAttribute("village") != null) {
                        village = request.getAttribute("village").toString();
                    }
                    if (request.getAttribute("pensionPhase") != null) {
                        pensionPhase = request.getAttribute("pensionPhase").toString();
                    }
                    if (request.getAttribute("fromDate") != null) {
                        fromDate = request.getAttribute("fromDate").toString();
                    }
        %>



        <html:form action="exportToExcelAllData">
            <html:hidden property="mode"/>
            <input type="hidden" id="districtId" name="districtId" value="<%=session.getAttribute("districtId").toString()%>"/>
            
            <table  align="center" cellspacing="0" cellpadding="0" id="grid" border="0" width="90%" class="inputform">
                <tr>
                    <th colspan="6">Total Assessed Data Download</th>

                </tr>
                <tr>
                    <td >Mandal :</td>
                    <td>
                        <html:select styleId="2" property="mandal_id"  onchange="getVillages();" >
                            <html:option  value="0" >ALL</html:option>
                            <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                        </html:select>
                    </td>
                    <td >Village :</td>
                    <td>
                        <html:select styleId="4" property="village_id" >
                            <html:option  value="0">ALL</html:option>
                            <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                        </html:select>
                    </td>
                    <td >Pension Phase :</td>
                    <td>
                        <html:select styleId="6" property="pensionPhase" style="width:150px;height:25px;font-size:11px;">
                            <html:option value="0">ALL </html:option>
                            <html:option value="PhaseI">PhaseI</html:option>
                            <html:option value="PhaseII">PhaseII</html:option>
                            <html:option value="PhaseIII">PhaseIII</html:option>
                            <html:option value="PhaseIV">PhaseIV</html:option><%--
                            <html:option value="5">RachaBandaI</html:option>
                            <html:option value="6">RachaBandaII</html:option>
                            <html:option value="7">After RachaBandaI</html:option>
                            <html:option value="8">After RachaBandaII</html:option>
                            <html:option value="ALL">ALL(PhaseI,PhaseII,PhaseIV)</html:option>--%>
                        </html:select>
                    </td>

                </tr>

                <tr>
                    <td >From Date<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="fromdate" styleId="9" readonly="true" size="12" />
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                    <td >To Date<font color="red"><b>*</b></font></td>
                    <td>
                        <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].todate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                   <td colspan="2" ><html:button  property="sub" value="Submit" onclick="return getRequestDetails();"/>
                    </td>
                   
                </tr>
               <tr>
                    <td align="center" colspan="6">
                        <logic:present name="alldataListSize">
                            Total assessed data: <a href="exportToExcelAllData.do?mode=exportReport&district=<%=district%>&mandal=<%=mandal%>&village=<%=village%>&pensionPhase=<%=pensionPhase%>&fromDate=<%=fromDate%>&toDate=<%=toDate%>"><font size="3"><bean:write name="alldataListSize"/></font></a><br/><blink> <font color="red"> (Please Click on the count to Download Excel)</font></blink>
                        </logic:present>
            </td>
        </tr>
              
    </table>
</html:form>
</body>
</html>
