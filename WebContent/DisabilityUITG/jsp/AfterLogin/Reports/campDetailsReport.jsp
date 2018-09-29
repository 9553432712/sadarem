<%--
    Document   : campDetailsReport
    Created on : Apr 22, 2014, 3:06:43 PM
    Author     : 310926
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%@page session="true"%>
<%

            String campId = null;
            int year = 0;
            String month = null;
            if(request.getAttribute("campId")!=null){
                campId=(String)request.getAttribute("campId");
            }
            if(request.getAttribute("year")!=null){
                year=(Integer)request.getAttribute("year");
            }
            if(request.getAttribute("month")!=null){
                month=(String)request.getAttribute("month");
            }
            %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>


        <script >
            function submitValues(){

                if(document.forms[0].elements["year"].value=="0" ||  document.forms[0].elements["year"].value==""){
                    alert("Please Select Year");
                    document.forms[0].elements["year"].focus();
                    return false;
                }else  if(document.forms[0].elements["month"].value=="0" ||  document.forms[0].elements["month"].value==""){
                    alert("Please Select Month");
                    document.forms[0].elements["month"].focus();
                    return false;
                }else{
                    document.forms[0].mode.value="getCampReport";
                    document.forms[0].submit();
                }

            }
            function getMonthList(year){
                document.forms[0].year.value=year;
                document.forms[0].status.value="Active";
                document.forms[0].mode.value="getCampReport";
                document.forms[0].submit();

            }
        </script>

    </head>

    <body>

        <html:form action="campDetailsReport" >
            <html:hidden property="mode"/>
             <html:hidden property="status"/>
          
            <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">
                <tr align="center"><th colspan="6">Camp Report</th></tr>
       
                <tr> <td class="label">Camps:</td>
                    <td >
                        <html:select property="campId" style="width:410px">
                            <html:option value="0">All</html:option>
                            <html:optionsCollection property="campList" label="campName" value="campId" />
                        </html:select>
                    </td>
                    <td class="label" valign="middle" width="10%">Year:<font color="red"><b>*</b></font></td>
                    <td align="left" valign="middle">
                        <html:select property="year" onchange="getMonthList(this.value)" style="height:25px;font-size:11px;width:150px;"  >
                            <html:option value="0">--Select--</html:option>
                            <html:optionsCollection property="yearsList" label="year" value="year"/>
                        </html:select>
                    </td>

                    <td class="label" valign="middle" width="10%">Month:<font color="red"><b>*</b></font></td>
                    <td align="left" valign="middle">
                        <html:select property="month" style="height:25px;font-size:11px;width:150px;">
                            <html:option value="0">--Select--</html:option>
                            <html:optionsCollection property="monthList" label="month" value="id"/>
                        </html:select>
                    </td>

                <tr>

                <tr><th colspan="6">  <input type="button" value="Submit"  styleId="but" onclick="return submitValues();" /></th></tr>

          </table>
            <br>
            <logic:present name="msg">
                <table align="center">

                    <tr><td><font color="red" size="2"><bean:write name="msg"/></font></td></tr></table>

            </logic:present>
            <logic:notEmpty name="campReportList">

               
                 <table  align="center" cellspacing="1" cellpadding="8" class="inputform" width="90%">

                    <tr>
                     <th>
                            Date
                      </th>

                     <th>
                            Part-A
                      </th>

                     <th>
                            Total Persons Assessed
                      </th>
                     <th>
                            Physical Impairment
                      </th>

                     <th>
                            Visual Impairment
                      </th>

                     <th>
                            Hearing Impairment
                      </th>
                     <th>
                            Mental Retardation
                      </th>

                     <th>
                            Mental Illness
                      </th>

                     <th>
                            Multiple Disability
                      </th>
                     <th>
                            Total
                      </th>

                     <th>
                            Direct Rejected
                      </th>

                     <th>
                            Assessed And Rejected
                      </th>
                     <th>
                            T Rejected
                      </th>

                    </tr>
                    <logic:iterate id="row" name="campReportList">

                        <tr>
                            <td align="left" class="formaltbg">${row.date}</td>
                            <td align="center" class="formaltbg">${row.partA}</td>
                            <td align="center" class="formaltbg">${row.TAP}</td>
                            <td align="center" class="formaltbg">${row.PI}</td>
                            <td align="center" class="formaltbg">${row.VI}</td>
                            <td align="center" class="formaltbg">${row.HI}</td>
                            <td align="center" class="formaltbg">${row.MR}</td>
                            <td align="center" class="formaltbg">${row.MI}</td>
                            <td align="center" class="formaltbg">${row.MD}</td>
                            <td align="center" class="formaltbg">${row.total}</td>
                            <td align="center" class="formaltbg">${row.DR}</td>
                            <td align="center" class="formaltbg">${row.AR}</td>
                            <td align="center" class="formaltbg">${row.TR}</td>

                        </tr>

                    </logic:iterate>
                </table>
                <br>
                 <table align="center">
                     <a href="campDetailsReport.do?mode=getCampReport&flag=excel&campId=<%=campId%>&month=<%=month%>&year=<%=year%>" target="_blank">
                         Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                              height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;
                 </table>
            </logic:notEmpty>


        </html:form>
    </body>
</html:html>
