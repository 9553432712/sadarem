<%--
    Document   : TotalReport3
    Created on : Sep 8, 2011, 5:14:06 PM
    Author     : 509862
--%>

<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
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
        
        <title>SADAREM</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script language="JavaScript" src="./DisabilityUITG/js/Ajax.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/Validations.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        
        <script language="JavaScript">
            
           
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
           
            function getDetails() {
                var fromDate= document.forms[0].fromdate.value;
                var toDate= document.forms[0].todate.value;
                document.forms[0].fromdate.value=fromDate;
                document.forms[0].todate.value=toDate;

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




                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
            function getData() {
                document.forms[0].mode.value="";
                document.forms[0].submit();
            }



            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;

            }




        </script>


    </head>
    <body onload="ShowDate();createDistrictObject();">
   
    <html:form  action="TotalReport1.do"  method="post" >
      
                    <input type="hidden" name="mode"/>
                    <input type="hidden" name="districtName"/>
                    <input type="hidden" name="mandalName"/>
                    <input type="hidden" name="villageName"/>
                    <input type="hidden" name="habitationName"/>
                    <input type="hidden" name="qualificationName"/>
                    <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">
                        <tr>
                            <th colspan="6">
                                A3.1 General Profile of Eligible PwDs (All Disabilities)
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">
                                    <tr>
                                        <td valign="middle"  width="8%">District</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="1" property="district_id" onchange="getData()" style="height:25px;">
                                                    <html:option value="0">--ALL--</html:option>
                                                    <html:optionsCollection property="districtList" label="district_name" value="district_id"  />
                                            </html:select>
                                        </td>
                                        <td  valign="middle" width="8%">Mandal</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="2" property="mandal_id" onchange="getData()" style="height:25px;">
                                                <html:option  value="0">--ALL--</html:option>
                                                <html:optionsCollection   property="mandallist" label="mandal_name" value="mandal_id"  />
                                            </html:select>
                                        </td>
                                        <td  valign="middle" width="8%">Village</td>
                                        <td align="left" valign="middle">
                                            <html:select styleId="3" property="village_id" style="height:25px;">
                                                <html:option  value="0">--ALL--</html:option>
                                                <html:optionsCollection   property="villagelist" label="village_name" value="village_id"  />
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td  colspan="2">From Date<font color="red"><b>*</b></font>
                                            <html:text property="fromdate" readonly="true" size="12" value="01/01/2010"/>
                                            <a  href="javascript:show_calendar('forms[0].fromdate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>
                                        <td  colspan="4">To Date<font color="red"><b>*</b></font>
                                            <html:text property="todate" styleId="8"  readonly="true" size="12"/>
                                            <a  href="javascript:show_calendar('forms[0].todate');"
                                                onmouseover="window.status='Date Picker';return true;"
                                                onmouseout="window.status='';return true;">
                                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                        </td>

                                        
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr><th  align="center">  <html:button property="but" onclick="getDetails()" value="Submit"/>
                                        </th></tr>
                                </table>
        <br>
        <table width="99%" border="0" cellspacing="0" cellpadding="10" align="center"><tr align="center"><td>
                    <%if (msg != null) {%>
                    <font color="red"><%=msg%></font>
                    <%}%>

                </td></tr></table>




    </html:form>
</body>
</html:html>
