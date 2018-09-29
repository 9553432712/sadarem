<%-- 
    Document   : currentWorkingCampReport
    Created on : May 13, 2013, 3:12:13 PM
    Author     : 728056
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <script  type="text/javascript">

            function getReport(){

                document.forms[0].mode.value="getCampReportDetails";
                document.forms[0].submit();
                 
            }
          
        </script>
    </head>
    <body >
        <html:form action="/campDetailsDateWiseReport.do">
            <html:hidden property="mode"/>

            <table  width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td align="center"  valign="top">
                        <table border="1" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                            <tr>
                                <th colspan="4">Camp Details Report</th>
                </tr>
                <tr>
                    <td>

                        <table  align="center" cellspacing="0" cellpadding="0" width="100%" border="0" class="inputform">

                            <tr >
                                <td  valign="middle" width="10%">Districts:</td>
                                <td  valign="middle">
                                    <html:select property="district_id" style="height:25px;font-size:11px;width:160px;">
                                        <html:option value="0">ALL</html:option>
                                        <html:optionsCollection property="districtList" label="district_name" value="district_id"/>
                                    </html:select>
                                </td>
                                <td  valign="middle" width="10%">Month:<font color="red"><b>*</b></font></td>
                                <td  valign="middle">
                                    <html:select property="month" >
                                        <html:option value="0">--Select--</html:option>
                                        <html:option value="01">JAN</html:option>
                                        <html:option value="02">FEB</html:option>
                                        <html:option value="03">MAR</html:option>
                                        <html:option value="04">APR</html:option>
                                        <html:option value="05">MAY</html:option>
                                        <html:option value="06">JUN</html:option>
                                        <html:option value="07">JUL</html:option>
                                        <html:option value="08">AUG</html:option>
                                        <html:option value="09">SEP</html:option>
                                        <html:option value="10">OCT</html:option>
                                        <html:option value="11">NOV</html:option>
                                        <html:option value="12">DEC</html:option>
                                    </html:select>
                                </td>
                                <td  valign="middle" width="10%">Year:<font color="red"><b>*</b></font></td>
                                <td  valign="middle">
                                    <html:select property="year" >
                                        <html:option value="0">--Select--</html:option>
                                        <html:option value="2013">2013</html:option>
                                        <html:option value="2014">2014</html:option>
                                        <html:option value="2015">2015</html:option>
                                        <html:option value="2016">2016</html:option>
                                        <html:option value="2017">2017</html:option>
                                        <html:option value="2018">2018</html:option>
                                        <html:option value="2019">2019</html:option>
                                        <html:option value="2020">2020</html:option>
                                    </html:select>
                                </td>
                            </tr>
                           
                        </table>
                            <tr>
                                <th colspan="6" >

                                    <input type="button" value="Submit"  styleId="but" onclick="getReport();" />

                                </th>
                            </tr>

                    </td>

                </tr>

            </table><br/><br/>
            <logic:present name="msg">
                <p d="errmsg"> ${msg}</p>
            </logic:present>
            <logic:notEmpty name="campReportList">
                
                            <table align="center" cellspacing="1" border="0" cellpadding="4" width="90%" class="inputform">
                                <tr>


                                    <th align="center" ><b>Camp Date</b></th>
                                    <th align="center" ><b>Camp Name</b></th>
                                    <th align="center" ><b>Address</b></th>
                                </tr>


                                <% int i = 0;
                                %>
                                <logic:iterate id="rows" name="campReportList" scope="request">
                                    <tr>

                                        <td align="center" >${rows.campDate}</td>
                                        <td  >  ${rows.campName}</td>
                                        <td  >  ${rows.address}</td>
                                    </tr>
                                </logic:iterate>

                            </table>
                        
            </logic:notEmpty>
        </td>
    </tr>
</table>
</html:form>
</body>
</html>
