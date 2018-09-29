<%--
    Document   : currentCampInsert
    Created on : May 10, 2013, 3:24:50 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%int j = 1;

//System.out.println("diabilityList : "+request.getAttribute("diabilityList"));

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        
        <script >
        
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function insertDetails(){
                var toDate=document.forms[0].campDate.value;
                if(toDate==null || toDate=="")
                {
                    alert("Please insert Camp Date");
                    document.forms[0].campDate.focus();
                    return false;
                }
                var selYY=toDate.substr(6,4);
                var selMM=toDate.substr(3,2);
                var selDD=toDate.substr(0,2);
                var today=new Date();                   
               
                var currentYY=today.getFullYear();
                var currentMM= today.getMonth()+1;
                var currentDD= today.getDate();
                //var ss=ddtoday+"/"+mmtoday+"/"+yytoday;
                var flag=0;
                if(selYY<currentYY){
                    flag=1;
                }
                if(selYY==currentYY && selMM<currentMM){
                    flag=1;
                }
                if(selYY==currentYY && selMM==currentMM && selDD<currentDD){
                    flag=1;
                }
                if(  flag){
                    alert("Camp Date should not before Today's Date");
                    document.forms[0].campDate.value="";
                    document.forms[0].campDate.focus();
                    return false;
                }
              
               
                if(document.forms[0].campId.value=="0")
                {
                    alert("Please select Camps");
                    document.forms[0].campId.focus();
                    return false;
                }

                else{
                    
                    document.forms[0].mode.value="insertCampDetails";
                    document.forms[0].submit();
                    return true;
                }
            }
            function ShowDate()
            {
                var dt = new Date();
                var month=(dt.getMonth()+1);
                if(month.toString().length==1){
                    month="0"+month;
                }
                var d =dt.getDate()+"/"+month+"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }
        </script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    </head>
    <%if (request.getAttribute("After") != null) {%>
    <body>
        <%} else {%>
    <body onload="ShowDate();">
        <%}%>
        <html:form action="/campDetailsInsertDateWise.do" enctype="multipart/form-data">
            <html:hidden property="mode"/>

            <p id="succmsg">
                <logic:present name="msg">
                    ${msg}
                </logic:present></p>

            <logic:present name="pendingCampDetails">
                <center>${pendingCampDetails}</center>
            </logic:present>


            <table  align="center" cellspacing="1" cellpadding="5" class="inputform" width="90%">
                <tr  align="center">

                    <th colspan="4">Camp Date Calender</th>

                </tr>


                <tr >
                    <td class="label">Camps:<font color="red"><b>*</b></font></td>
                    <td >
                        <html:select property="campId" style="width:410px">
                            <html:option value="0">------------------------------Select--------------------------------------</html:option>
                            <html:optionsCollection property="campList" label="campName" value="campId"/>
                        </html:select>
                    </td>
                    <td class="label" >Camp Date<font color="red"><b>*</b></font>
                        <html:text property="campDate" styleId="8"  readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].campDate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr>
                <tr>
                    <th>Sno</th>
                    <th>Disability</th>
                    <th>Count</th>
                </tr>

                <logic:notEmpty name="diabilityList">
                    <logic:iterate id="row" name="diabilityList" indexId="i">
                        <tr>
                            <td class="label" style="text-align: center;"><%=j++%></td>
                            <td class="label">
                                ${row.disability_name}<input type="hidden" name="locValue" value="${row.disability_id}"/>
                            </td>
                            <td class="label" style="text-align: center;">
                                <input type="text" name="locCount" id="dcount" maxlength="3" size="10" style="text-align:left;" onkeypress="return onlyNumbers();" />
                            </td>
                        </tr>
                    </logic:iterate>
                <tr>
                    <th  align="center" colspan="4">

                        <input type="button" value="Submit"  styleId="but" onclick="return insertDetails();" />

                    </th>
                </tr>
                </logic:notEmpty>
            </table>
            <br/>
            <logic:present name="nodata">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                    <tr>
                        <td colspan="6" align="center" class="heading" width="50%" > ${nodata}</td>
                    </tr>
                </table>
            </logic:present>

            <logic:notEmpty name="campReportList">


                <table  align="center" cellspacing="1" cellpadding="5" class="inputform" width="90%">
                    <tr  align="center">

                        <th colspan="3">Submitted Camp Details</th>

                    </tr>
                    <tr>


                        <th><b>Camp Date</b></th>
                        <th><b>Camp Name</b></th>

                    </tr>


                    <% int i = 0;
                    %>
                    <logic:iterate id="rows" name="campReportList" scope="request">
                        <tr>

                            <td align="center" class="formaltbg">${rows.campDate}</td>
                            <td align="left" class="formaltbg">  ${rows.campName} (${rows.venue}), ${rows.address}</td>
                        </tr>
                    </logic:iterate>

                </table>

            </logic:notEmpty>

        </html:form>
    </body>
</html>
