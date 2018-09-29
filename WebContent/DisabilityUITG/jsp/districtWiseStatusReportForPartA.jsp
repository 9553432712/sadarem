<%-- 
    Document   : CampWiseDisabilityReport
    Created on : May 26, 2010, 2:39:04 PM
    Author     : srinivas_p
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
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
              //  {


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
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    </head>

    <BODY>
        <html:form action="districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA"
                   styleId="partAForm" onsubmit="return validate_form(this)">
            <br>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="60%">
                <tr><td class="heading" align="center">Personal Details Report</td></tr>
            </table>
            <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="60%">


                <tr><td align="right" class="label"><font color="red"><b>*</b></font>From Date</td>
                    <td><html:text property="fromdate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].fromdate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a></td>


                    <td align="right" class="label"><font color="red"><b>*</b></font>To Date</td>
                    <td> <html:text property="todate" readonly="true"  name="partAForm"/>
                        <a  href="javascript:show_calendar('forms[0].todate');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a> </td>

                    <% session.removeAttribute("partAList");
                       session.removeAttribute("partADistrictList"); %>

                </tr>
                <tr><td align="center" colspan="4"><html:submit property="button"  value="Generate Report" styleClass="button"/> </table>

            <br>


        </html:form>
    </BODY>
</html:html>


