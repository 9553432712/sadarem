<%--
    Document   : ReAssessmentCampWiseReportExcel
    Created on : Jun 27, 2011, 3:33:27 PM
    Author     : 476208
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%@ page contentType="application/MyExcel.ms-excel"%>
<%
            int i = 1;
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
          </head>
    <body>
                                        <logic:notEmpty name="AssesmentListData">
                                            
                                            <table  align="center" cellspacing="1" cellpadding="5" border="1" width="100%">
                                                
                                                <tr>
                                                    
                                                <b>ReAssessment Camp Wise Report</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <td colspan="6" align="center">
                                                          SNo
                                                    </td>

                                                    <td colspan="6" align="center">
                                                         PersonID
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         SADAREMID
                                                    </td>
                                                    <td colspan="6" align="center">
                                                        Name
                                                    </td>
                                                    <td colspan="6" align="center">
                                                        Age
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         Disability Type
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         Old Percentage
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         New Percentage
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         ReAssessmentText
                                                    </td>
                                                    <td colspan="6" align="center">
                                                        Doctor's Name
                                                    </td>
                                                     <td colspan="6" align="center">
                                                         Doctor's RegNumber
                                                    </td>
                                                     <td colspan="6" align="center">
                                                         Doctor's Designation
                                                    </td>

                                                </tr>

                                                <logic:iterate name="AssesmentListData" id="row" scope="session">

                                                    <tr>
                                                    <td colspan="6" align="center">
                                                         <%=i++%>
                                                    </td>

                                                    <td colspan="6" align="center">
                                                        ${row.pensionCard}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                       SID-- ${row.personCode}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                      ${row.Name}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                        ${row.age}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         ${row.disability}
                                                    </td>
                                                   
                                                    <td colspan="6" align="center">
                                                         ${row.totalDisabilityInActive}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                        ${row.totaldisabilityActive}
                                                    </td>
                                                     <td colspan="6" align="center">
                                                         ${row.reAssessmentText}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         ${row.firstDoctorName}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         ${row.firstDoctorRegNumber}
                                                    </td>
                                                    <td colspan="6" align="center">
                                                         ${row.firstDoctorDesignation}
                                                    </td>
                                      
                                                </tr>
                                                    
                                                </logic:iterate>
                                            </table>


                                             </logic:notEmpty>
                    
    </body>
               
            </html:html>