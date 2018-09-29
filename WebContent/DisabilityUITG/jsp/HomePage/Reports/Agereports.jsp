<%-- 
    Document   : Agereports
    Created on : Jan 5, 2012, 12:08:35 PM
    Author     : 490058
--%>



<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String qualification = (String) request.getParameter("qualification");
            String caste = (String) request.getParameter("caste");

            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String qly = (String) request.getParameter("qualificationName");
            String names = (String) request.getAttribute("names");
            String msg = (String) request.getAttribute("msg");



%>
<html:html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <script src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>


        <script language="JavaScript">
            
            
          
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;
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
            function validate_form(thisform)
            {
                with (thisform)
                {
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
                    if (validate_required(fromDate,"From Date must be selected!")==false)
                    {
                        document.forms[0].fromdate.focus();
                        return false
                    }
                    if (validate_required(toDate,"To Date must be selected!")==false)
                    {
                        document.forms[0].todate.focus();
                        return false
                    }
                    if (today < etd)
                    {
                        alert("From date is before Today's Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date does not exceed Today's Date");
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

            function getDetails() {
                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;
                document.forms[0].fromdate.value=fromDate;
                document.forms[0].todate.value=toDate;
                var fage=document.forms[0].fromAge.value;
                var tage=document.forms[0].toAge.value;

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
                if(fage=='' || tage==''){
                    alert("Enter Fromage and Toage");
                    return false;

                }
                if(fage > tage) {
                    alert("To Age must be grater than from Age");
                    document.forms[0].elements['fromAge'].value="";
                    document.forms[0].elements['toAge'].value="";
                    return false;
                }
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
                if(fage=='' ||tage==''){
                    alert("Enter Fromage and Toage");
                    return false;
                }
                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }

        </script>
    </head>
    <body onload="ShowDate()">
        <center>
            <html:form  action="AgeWiseReport.do"  method="post"  >
                <input type="hidden" name="mode"/>
                <input type="hidden" name="districtName"/>
                <input type="hidden" name="mandalName"/>
                <input type="hidden" name="villageName"/>
                <input type="hidden" name="habitationName"/>
                <input type="hidden" name="qualificationName"/>
               <%-- <p>R2.5 &nbsp; Agewise Assessed Report</p>--%>
                <table  align="center" cellspacing="1" cellpadding="0" width="90%" class="inputform">
                    <tr >
                        <th colspan="6">R2.5 &nbsp; Agewise Assessed Report</th>
                    </tr>
                    <tr>
                        <td>AgeFrom <font color="red"><b>*</b></font></td>
                        <td> <html:text property="fromAge" />

                        </td>
                        <td>AgeTo <font color="red"><b>*</b></font></td>
                        <td>
                            <html:text property="toAge" />
                        </td>
                    </tr>
                    <tr>

                        <td  >From Date<font color="red"><b>*</b></font></td>
                        <td>
                            <html:text property="fromdate" readonly="true" size="12" value="01/01/2010"/>
                            <a  href="javascript:show_calendar('forms[0].fromdate');"
                                onmouseover="window.status='Date Picker';return true;"
                                onmouseout="window.status='';return true;">
                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                        </td>
                        <td  >To Date<font color="red"><b>*</b></font></td>
                        <td>
                            <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                            <a  href="javascript:show_calendar('forms[0].todate');"
                                onmouseover="window.status='Date Picker';return true;"
                                onmouseout="window.status='';return true;">
                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="6">  <html:button property="but" onclick="getDetails()" value="Submit"/>
                        </th>

                    </tr>
                </table>
                <br>
                <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center"><tr align="center"><td>
                            <%if (msg != null) {%>
                            <font color="red"><%=msg%></font>
                            <%}%>
                        </td></tr>
                </table>
            </html:form></center>
    </body>
</html:html>

