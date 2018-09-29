<%--
    Document   : districtWiseStatusReportForPartAExcel
    Created on : May 30, 2010, 11:20:45 AM
    Author     : srinivas_p
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>
<%@ page contentType="application/MyExcel.MS-Excel.excel.xls" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String pensionPhase = (String) request.getParameter("pensionPhase");
          
            if (pensionPhase.equals("1")) {
                pensionPhase = "PhaseI";
            } else if (pensionPhase.equals("2")) {
                pensionPhase = "PhaseII";
            } else if (pensionPhase.equals("3")) {
                pensionPhase = "PhaseIII";
            } else if (pensionPhase.equals("4")) {
                pensionPhase = "PhaseIV";
            } else if (pensionPhase.equals("5")) {
                pensionPhase = "ALL";
            }
            int i=1;

%>

<html:html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

   
       
        <table  align="center" cellspacing="0" border="1" cellpadding="0"  width="100%" style="border-style: outset">
           
            <tr>
                <td align="center" colspan="14" class="formhdbg"><b>Assessed PWD's Details  As on <%=fromdate%> to <%=todate%></b></td>
            </tr>

            <tr>
                <td align="center" >
                    Sno
                </td>
                <td align="center" >
                    Person ID
                </td>
                <td align="center"  >
                    SADAREM ID
                </td>
                <td align="center" >
                    Name
                </td>
                <td align="center">
                    Relation Name
                </td>
                <td align="center" >
                    Age
                </td>
                <td align="center" >
                    Gender
                </td>
                <td align="center">
                    Type of <br/> Disability
                </td>
                <td align="center" >
                    Disability <br/> Percentage
                </td>
                <td align="center" >
                    District
                </td>
                <td align="center">
                    Mandal
                </td>
                <td align="center" >
                    Village
                </td>
                <td align="center">
                    Reason for Status
                </td>
                <td align="center">
                    Phase
                </td>
            </tr>
            <logic:iterate name="dailyReportList" id="row" scope="session">

                <tr>
                    <td align="center" >
                        <%=i++%>.
                    </td>

                    <td align="center">
                        ${row.dis_per}
                    </td>

                    <td align="center">
                        ${row.pensionId}
                    </td>


                    <td >
                        ${row.name}
                    </td>


                    <td>
                        ${row.fname}
                    </td>


                    <td align="center">
                        ${row.age}
                    </td>


                    <td align="center">
                        ${row.gender}
                    </td>



                    <td >
                        ${row.phc}
                    </td>

                    <td align="center">
                        ${row.rationcardno}
                    </td>

                    <td >
                        ${row.districtName}
                    </td>
                    <td>
                        ${row.mandalName}
                    </td>
                    <td>
                        ${row.villageName}
                    </td>
                    <td>
                        ${row.reasonforStatus}
                    </td>

                    <td align="center">
                        ${row.pensionphase}
                    </td>

                </tr>
             
            </logic:iterate>

                   <%

                            session.removeAttribute("dailyReportList");
                %>
           
        </table>

</body>
</html:html>




