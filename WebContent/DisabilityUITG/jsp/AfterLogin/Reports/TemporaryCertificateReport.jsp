<%-- 
    Document   : TemporaryCertificateReport
    Created on : Feb 7, 2015, 11:01:06 AM
    Author     : 310926
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<%
            int i = 1;
            String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");

            ArrayList list = (ArrayList) request.getAttribute("reportList");



%>




<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.s"></script>


        <script >
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;
                document.getElementById(7).value = '01/01/2010';

            }

            function getDetails() {
                var fromDate= document.forms[0].fromDate.value;
                    var toDate= document.forms[0].toDate.value;
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
                    if (validate_required(fromDate,"From Date must be selected!")==false)
                    {
                        document.forms[0].fromDate.focus();
                        return false
                    }
                    if (validate_required(toDate,"To Date must be selected!")==false)
                    {
                        document.forms[0].toDate.focus();
                        return false
                    }
                    if (today < etd)
                    {
                        alert("From date is before Today's Date");
                        document.forms[0].fromDate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date does not exceed Today's Date");
                        document.forms[0].toDate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromDate.value="";
                        return false;
                    }
                    document.forms[0].mode.value="getDetails";
                    document.forms[0].submit();
                    return true;
                
            }


            function validate_required(field,alerttxt)
            {

                with (field)
                {

                    if (field==null||field=="")
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
            
        </script>

    </head>

   <%if (request.getAttribute("onload") != null) {%>
    <body onload="ShowDate();">
        <%} else {%>
    <body >
        <%}%>
        


        <html:form action="/temporaryCertificateReport.do" >


            <input type="hidden" name="mode"/>
           <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center">
                <tr>
                    <td>
                        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                            <tr>
                                <th colspan="4">
                                    Temporary Certificate Report
                                </th>
                            </tr>
                            <tr>

                                <td>From Date<font color="red"><b>*</b></font>
                                    <html:text property="fromDate" styleId="7" readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].fromDate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                                <td>To Date<font color="red"><b>*</b></font>
                                    <html:text property="toDate" styleId="8"  readonly="true" size="12"/>
                                    <a  href="javascript:show_calendar('forms[0].toDate');"
                                        onmouseover="window.status='Date Picker';return true;"
                                        onmouseout="window.status='';return true;">
                                        <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                </td>
                            </tr>
                            <tr align="center">
                                <th colspan="4">
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
            <br>


            <logic:notEmpty name="reportList">
                <table   align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%" style="border: hidden">
                    <tr> <td colspan="10" style="text-align: right">
                                <a href="temporaryCertificateReport.xls?mode=excel&fromDate=<%=fromdate%>&toDate=<%=todate%>" target="_blank">
                                    Excel <img src="DisabilityUITG/images/excel.jpg" height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                            </td></tr>
                 </table>
                <div style="overflow:auto; width:933px;  vertical-align: top; border: black medium; padding-left: 50px">
                <table   align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="90%">
                  
                    <tr>  <logic:present name="names">
                            <th style="text-align: center" colspan="10">
                                <bean:write name="names"/>
                            </th>
                        </logic:present></tr>
                    <tr>

                        <th align="center" >S.No</th>
    <th align="center" >SADAREM ID</th>
    <th align="center" >Name</th>
    <th align="center" >Gender</th>
    <th align="center" >Age</th>
    <th align="center" >Relation Name</th>
    <th align="center" >Rationcard No</th>
    <th align="center" >Type of disability</th>
    <th align="center" >Percentage</th>
    <th align="center" >Address</th>
                    </tr>

<logic:iterate name="reportList" id="row" >
    <tr>

        <td>
           ${row.sno}.
        </td>
        <td>
            ${row.sadaremid}
        </td>
        <td>
            ${row.personname}
        </td>
        <td>
            ${row.gender}
        </td>
        <td>
            ${row.age}
        </td>
        <td>
            ${row.relationname}
        </td>

        <td>
            ${row.rationcardno}
        </td>
        <td>
            ${row.typeofdisability}
        </td>
        <td>
            ${row.percentage}
        </td>
        <td>
            ${row.address}
        </td>

    </tr>

</logic:iterate>


                </table>
                    </div>
            </logic:notEmpty>

        </html:form>
    </body>
</html:html>



