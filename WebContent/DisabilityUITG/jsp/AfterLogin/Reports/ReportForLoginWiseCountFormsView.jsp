<%--
    Document   : ReportForLoginWiseCountFormsView
    Created on : May 12, 2010, 4:19:10 PM
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
                {


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
            }
        </script>
    </head>
    <body>

        <html:form action="loginwiseformscountreport.do?getReportForLoginWiseCount=getReportForLoginWiseCount"
                   styleId="partAForm" onsubmit="return validate_form(this)">
             <logic:present name="noResults" scope="request">
                 <center> <font color="red" size="2"> Loginwise Details Not Available</font></center>
            </logic:present>
            <input type="hidden" name="flag" value="finish"/>
            <br/>
            <table  align="center" cellspacing="1" cellpadding="0" class="inputform" width="60%">
                <tr>
                    <th class="heading" align="center" colspan="8">
                       Loginwise Details
                    </th>
                </tr>
                <tr>
                    <td align="right" >
                    
                        From Date :<font color="red"><b>*</b></font>
                    </td>
                    <td>
                        <html:text property="fromdate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                    <td align="right" >
                        To Date :<font color="red"><b>*</b></font>
                    </td>

                    <td>
                        <html:text property="todate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].todate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>
                </tr><tr>
                    <th colspan="4">
                        <html:submit property="button"  value="Generate Report"
                                     onclick="comparedate(this.fromdate,this.todate)" styleClass="button"/>
                    </th>
                </tr>
            </table>
            <br/>

           
            <logic:notEmpty name="dailyreportlist" scope="request">
                <table  align="center" border="0" cellspacing="1" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center" class="labelBlue">S.No</th>
                        <th ALIGN="center" class="labelBlue">LoginID</th>
                        <th ALIGN="center" class="labelBlue">PartA Entered</th>
                        <th ALIGN="center" class="labelBlue">PartB Entered</th>
                        <th align="center" class="labelBlue">Total Records</th>
                    </tr>
                    <% int i = 0;%>
                    <logic:iterate id="modify" name="dailyreportlist" scope="request">
                        <tr>
                            <td  style="text-align: center"  ><%=++i%></td>
                            <td  > <bean:write name="modify" property="loginid"/></td>
                            <td  style="text-align: center" > <bean:write name="modify" property="partA"/></td>
                            <td  style="text-align: center" > <bean:write name="modify" property="partB"/></td>
                            <td  style="text-align: center" > <bean:write name="modify" property="total"/></td>
                        </tr>
                    </logic:iterate>
                    <tr>
                        <td style="text-align: center" colspan="5">
                            <%    ArrayList ar2 = new ArrayList();
                                        ar2 = (ArrayList) request.getAttribute("dailyreportlist");
                                        String fromdate1 = (String) request.getAttribute("From_Date");
                                        String todate1 = (String) request.getAttribute("To_Date");
                                        application.setAttribute("FromDate", fromdate1);
                                        application.setAttribute("ToDate", todate1);
                                        application.setAttribute("arraylist1", ar2);%>
                            <a href="reportforlogonwisecountexcel.xls" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="reportforlogonwisecountprint.xls" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif" height="25" width="25"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </table>
            </logic:notEmpty>
        </body>
    </html:form>
</html:html>
