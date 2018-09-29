<%-- 
    Document   : NiramayaExcelPersonalReportPageWise
    Created on : Nov 15, 2010, 1:40:46 PM
    Author     : 509865
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html:html>
    <head>

    </head>

    <body>

        <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="100%">
            <tr align="center"  width="100%">
                <td><b><font color="blue">Details of Persons with Disability eligible for NIRAMAYA Health Insurance Scheme.
                   Assessment done as per G.O.Ms.No.31, Dated.01-12-2009. & G.O.Ms.No.371, Dated 02-12-2009</font></b></td></tr>
            <tr>
                <td>

                    <table  border="1" align="center" cellspacing="1" cellpadding="5"  width="70%" >
                        <tr class="tbl_bg2_content_hdr_new" valign="middle">
                            <td bgcolor="white"><font color="#660000" size="2"><b>S.No</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>SADAREM ID</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Name</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Father or Guardian</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Date of Birth</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Age</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Sex</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Marital Status</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Education</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Type of disability</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>% of Disability</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>House Number</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>District</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Mandal</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Village</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Habitation</b></font></td>
                            <td bgcolor="white"><font color="#660000" size="2"><b>Pincode</b></font></td>
                        </tr><% int i=((Integer)session.getAttribute("counter")).intValue();  %>

                        <logic:iterate id="details" name="arraylist1" scope="session" >
                            <tr class="tbl_bg2_content">
                                <td  bgcolor="white"><%=i++%></td>
                                <td bgcolor="white">SID- <bean:write name="details" property="personcode"/></td>
                                <td bgcolor="white"><bean:write name="details" property="name"/></td>
                                <td bgcolor="white"><bean:write name="details" property="fathername"/></td>
                                <td bgcolor="white"><bean:write name="details" property="dateOfBirth"/></td>
                                <td bgcolor="white" ><bean:write name="details" property="age"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="gender"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="maritalStatus"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="education"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="typeofdisability"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="totalpercent"/></td>
                                <td  bgcolor="white" ><logic:notEmpty name="details" property="houseno">
                                    H.No: <bean:write name="details" property="houseno"/>
                                    </logic:notEmpty></td>
                                <td  bgcolor="white" ><bean:write name="details" property="districtname"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="mandalname"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="villagename"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="habitationname"/></td>
                                <td  bgcolor="white" ><bean:write name="details" property="pincode"/></td>
                            </tr>
                        </logic:iterate>

                    </table>

        </table>


    </body>

    <p>&nbsp;</p>

</html:html>
