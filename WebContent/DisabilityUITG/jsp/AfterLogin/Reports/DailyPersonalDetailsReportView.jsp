<%-- 
    Document   : DailyPersonalDetailsReportView
    Created on : May 8, 2010, 6:49:10 PM
    Author     : srinivas_p
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>

<% String fromdate = (String) request.getAttribute("From_Date");%>
<% String todate = (String) request.getAttribute("To_Date");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" src="../styles/cal2.js"></script>
        <script language="javascript" src="../styles/cal_conf2.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script>
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
            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(fromdate,"From Date must be selected!")==false)
                    {
                        fromdate.focus();
                        return false
                    }
                    if (validate_required(todate,"To Date must be selected!")==false)
                    {
                        todate.focus();
                        return false
                    }

                }
                //  }
                //   function comparedate(fromDate,toDate)
                // {


                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;

                //Validate Meeting Date

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



                if (today < etd)
                {
                    alert("From date is after Todays Date");
                    document.forms[0].fromdate.value="";
                    return false;
                }

                if (today < dob)
                {
                    alert("To date is after Todays Date");
                    document.forms[0].todate.value="";
                    return false;
                }

                if (dob < etd)
                {
                    alert("From date is greater than To date");
                    document.forms[0].fromdate.value="";
                    return false;
                }
            }

        </script>
    </head>
    <body>
        <html:form action="dailypersonalreportaction.do?getPersonPersonalDetailsWise=getPersonPersonalDetailsWise"
                   styleId="partAForm" onsubmit="return validate_form(this)">
            <logic:present name="msg"><center><font color="red" size="2">
                        <bean:write name="msg"/></font></center>
                    </logic:present>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr>
                    <th class="heading" align="center" colspan="6">
                        Personal Details
                    </th>
                </tr>

                <tr>
                    <td align="right" >

                        From Date : <font color="red">
                            <b>*</b>
                        </font>
                    </td>
                    <td>
                        <html:text property="fromdate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                    <td align="right" >
                        To Date : <font color="red">
                            <b>*</b>
                        </font>
                    </td>
                    <td>
                        <html:text property="todate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].todate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0">
                        </a>
                    </td>
                </tr>
                <tr>
                    <th align="center"  colspan="4">
                        <html:submit property="button"  value="Generate Report"  styleClass="button"/>
                    </th>
                </tr>
            </table><br/>
            <logic:present name="dailyreportlist" scope="request">
                <center>
                    <div style="overflow:auto; width:860px;height:500px;text-align: center" >
                        <table  cellspacing="1" border="0" cellpadding="0" class="inputform" align="center" >
                            <tr >
                                <th align="center" >S.No</th>
                                <th align="center" >SADAREM ID</th>
                                <th align="center" >SurName </th>
                                <th align="center" >FirstName</th>
                                <th align="center" >Gender</th>
                                <th align="center" >Date Of Birth</th>
                                <th align="center" >Age</th>
                                <th align="center" >Religion</th>
                                <th align="center" >Caste</th>
                                <th align="center" >Relation Name</th>
                                <th align="center" >Relationship</th>
                                <th align="center" >RationCardNumber</th>
                                <th align="center" >RationCard Type</th>
                                <th align="center" >Pension Type</th>
                                <th align="center" >PensionCardNumber</th>
                                <th align="center" >Identification Marks</th>
                                <th align="center" >Identification Marks2</th>
                                <th align="center" >HouseNumber</th>
                                <th align="center" >District</th>
                                <th align="center" >Mandal</th>
                                <th align="center" >Village</th>
                                <th align="center" >Habitation</th>
                                <th align="center" >Type of Disability</th>
                            </tr>
                            <% int i = 0;%>
                            <logic:iterate id="modify" name="dailyreportlist" scope="request">
                                <tr>
                                    <td  align="center" ><%=++i%></td>
                                    <td >PWDID-<bean:write name="modify" property="personcode"/></td>
                                    <td > <bean:write name="modify" property="surname"/></td>
                                    <td > <bean:write name="modify" property="firstname"/></td>
                                    <td > <bean:write name="modify" property="gender"/></td>
                                    <td > <bean:write name="modify" property="dobday"/></td>
                                    <td > <bean:write name="modify" property="noOfYears"/></td>
                                    <td > <bean:write name="modify" property="religion"/></td>
                                    <td > <bean:write name="modify" property="caste"/></td>
                                    <td > <bean:write name="modify" property="gsurname"/></td>
                                    <td > <bean:write name="modify" property="grelation"/></td>
                                    <td >&nbsp<bean:write name="modify" property="card"/></td>
                                    <td >&nbsp<bean:write name="modify" property="rtype"/></td>
                                    <td >&nbsp<bean:write name="modify" property="pension_type"/></td>
                                    <td >&nbsp<bean:write name="modify" property="pensioncardno"/></td>
                                    <td > <bean:write name="modify" property="mole1"/></td>
                                    <td > <bean:write name="modify" property="mole2"/></td>
                                    <td > <bean:write name="modify" property="houseno"/></td>
                                    <td > <bean:write name="modify" property="district"/></td>
                                    <td > <bean:write name="modify" property="mandal"/></td>
                                    <td > <bean:write name="modify" property="village_name"/></td>
                                    <td > <bean:write name="modify" property="habitation_name"/></td>
                                    <td > <bean:write name="modify" property="type_disability"/></td>
                                </tr>
                            </logic:iterate>
                            <tr>
                                <td colspan="23" style="text-align: center">
                                    <%    ArrayList ar2 = new ArrayList();
                                                ar2 = (ArrayList) request.getAttribute("dailyreportlist");
                                                String fromdate1 = (String) request.getAttribute("From_Date");
                                                String todate1 = (String) request.getAttribute("To_Date");
                                                application.setAttribute("FromDate", fromdate1);
                                                application.setAttribute("ToDate", todate1);
                                                application.setAttribute("arraylist1", ar2);%>
                                    <a href="personaldetailswiseexcel.xls" target="_blank" style="text-align: center">
                                        Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                             height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                        </table>
                    </div>
                </center>
            </logic:present>

        </html:form>
    </body>

</html:html>
