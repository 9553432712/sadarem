<%-- 
    Document   : MonthWiseCumulativePrint
    Created on : Mar 12, 2013, 3:43:16 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,java.text.*"%>
<%
            int i = 1;
            String type = null;
            String did = request.getParameter("district_id");
            String mid = request.getParameter("mandal_id");
            String vid = request.getParameter("village_id");
            String hid = request.getParameter("habitation_id");
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            String dname = (String) request.getAttribute("districtname");
            String mname = (String) request.getAttribute("mandalname");
            String vname = (String) request.getAttribute("vname");
            String hname = (String) request.getAttribute("hname");
            
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0, t9 = 0, t10 = 0, t11 = 0, t12 = 0, t13 = 0, t14 = 0, t15 = 0, t16 = 0, t17 = 0, t18 = 0, t19 = 0, t20 = 0;
          ArrayList ar = new ArrayList();
            ar = (ArrayList) request.getAttribute("reportList");
            if(ar!=null){
            Iterator ir = ar.iterator();
            while (ir.hasNext()) {
                Map m = new HashMap();
                m = (Map) ir.next();
                t1 = t1 + Integer.parseInt(m.get("phaseI").toString());
                t2 = t2 + Integer.parseInt(m.get("phaseII").toString());
                t3 = t3 + Integer.parseInt(m.get("phaseIII").toString());
                t4 = t4 + Integer.parseInt(m.get("phaseIV").toString());
                t5 = t5 + Integer.parseInt(m.get("rachabandaI").toString());
                t6 = t6 + Integer.parseInt(m.get("rachabandaII").toString());

                t7 = t7 + Integer.parseInt(m.get("attended").toString());
                t8 = t8 + Integer.parseInt(m.get("eligible4150").toString());
                t9 = t9 + Integer.parseInt(m.get("eligible5160").toString());
                t10 = t10 + Integer.parseInt(m.get("eligible6170").toString());
                t11 = t11 + Integer.parseInt(m.get("eligible7180").toString());
                t12 = t12 + Integer.parseInt(m.get("eligible8190").toString());
                t13 = t13 + Integer.parseInt(m.get("eligibl9100").toString());
                t14 = t14 + Integer.parseInt(m.get("neligible0010").toString());
                t15 = t15 + Integer.parseInt(m.get("eligible1120").toString());
                t16 = t16 + Integer.parseInt(m.get("eligible2130").toString());
                t17 = t17 + Integer.parseInt(m.get("eligible3140").toString());
                t18 = t18 + Integer.parseInt(m.get("total").toString());
                t19 = t19 + Integer.parseInt(m.get("unattended").toString());
            }
}
%>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract)</title>
    </head>
    <body onload="window.print();">
        <logic:notEmpty name="reportList">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" class="innerTable">
                
                <tr><td colspan="26" class="formhdbg" align="center" align="center"> SADAREM Phase wise Assessed PWD's Cumulative Report - (Abstract)
                        <%if (did != null && !did.equalsIgnoreCase("null") && did.equalsIgnoreCase("0")) {%>
                        All Districts
                        <%}%>
                        <%if (did != null && !did.equalsIgnoreCase("null") && !did.equalsIgnoreCase("0") && mid.equalsIgnoreCase("0")) {%>
                        District:<%=dname%>
                        <%} else if (mid != null && !mid.equalsIgnoreCase("null") && !mid.equalsIgnoreCase("0") && vid.equalsIgnoreCase("0")) {%>
                        District:<%=dname%> Mandal:<%=mname%>
                        <%} else if (vid != null && !vid.equalsIgnoreCase("null") && !vid.equalsIgnoreCase("0") && hid.equalsIgnoreCase("0")) {%>
                        District:<%=dname%> Mandal:<%=mname%> Village:<%=vname%>

                        <%}%> From Date: <%=fromdate%> To Date:<%=todate%></td> </tr>
                <tr>
                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                        S.No
                    </td>

                    <td class="formhdbg" align="center" rowspan="2">
                        <%if (did != null && !did.equalsIgnoreCase("null") && did.equalsIgnoreCase("0")) {%>
                        District
                        <%}%>
                        <%if (did != null && !did.equalsIgnoreCase("null") && !did.equalsIgnoreCase("0") && mid.equalsIgnoreCase("0")) {%>
                        Mandal
                        <%} else if (mid != null && !mid.equalsIgnoreCase("null") && !mid.equalsIgnoreCase("0") && vid.equalsIgnoreCase("0")) {%>
                        Village
                        <%} else if (vid != null && !vid.equalsIgnoreCase("null") && !vid.equalsIgnoreCase("0") ) {%>
                        Habitation

                        <%}%>


                    </td>
                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                        Phase-I
                    </td>
                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                        Phase-II
                    </td>
                    <td class="formhdbg" align="center" rowspan="2" nowrap>
                        Phase-III
                    </td>
                    

                    <td colspan="3" class="formhdbg" align="center"> Phase-IV</td>
 
                                      <td class="formhdbg" align="center" rowspan="2" nowrap>
                                        Attended<br/>
                                        <font size="1.5px">(Phase-I<br/>+<br/>Phase-II<br/>+<br/>Phase-III<br/>+<br/>Phase-IV)</font>

                                    </td>

                                    <td colspan="6" class="formhdbg" align="center" nowrap>
                                        Eligible<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font>
                                    </td >

                                    <td class="formhdbg" align="center" colspan="4" nowrap>
                                        Not Eligible<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font>
                                    </td>
                                    <td class="formhdbg" align="center" rowspan="2">
                                        Total
                                    </td>
                                    <td class="formhdbg" align="center" colspan="6" nowrap>Not Attended<br><font size="1.5px">(Phase-I+Phase-II+Phase-III+Phase-IV)</font></td>


                </tr>
                <tr>
                    <td class="formhdbg" align="center" nowrap>
                        Old
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        Rachabanda-I
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        Rachabanda-II

                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        40-50
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        51-60
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        61-70
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        71-80
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        81-90
                    </td>
                    <td class="formhdbg" align="center" nowrap>
                        91-100
                    </td>
                    <td class="formhdbg" align="center" >
                        0-10
                    </td>
                    <td class="formhdbg" align="center" >
                        11-20
                    </td>
                    <td class="formhdbg" align="center" >
                        21-30
                    </td>
                    <td class="formhdbg" align="center" >
                        31-39
                    </td>
                    <td class="formhdbg" align="center">Total</td>
                    <td class="formhdbg" align="center">Notice Served<br> Not Attended</td>
                    <td class="formhdbg" align="center">Dead</td>
                    <td class="formhdbg" align="center">Not Eligible</td>
                    <td class="formhdbg" align="center">Not Traceble</td>
                    <td class="formhdbg" align="center">Duplicate can be removed</td>

                </tr>

                <logic:iterate name="reportList" id="row">
                    <tr>
                        <td class="formaltbg" align="center">
                            <%=i++%>
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.district}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.phaseI}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.phaseII}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.phaseIII}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.phaseIV}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.rachabandaI}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.rachabandaII}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.attended}
                        </td>

                        <td class="formaltbg" align="left">
                            ${row.eligible4150}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible5160}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible6170}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible7180}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible8190}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligibl9100}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.neligible0010}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible1120}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible2130}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.eligible3140}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.total}
                        </td>
                        <td class="formaltbg" align="left">
                            ${row.unattended}
                        </td>
                        <td class="formaltbg" align="left">
                            0
                        </td>
                        <td class="formaltbg" align="left">
                            0
                        </td>
                        <td class="formaltbg" align="left">
                            0
                        </td>
                        <td class="formaltbg" align="left">
                            0
                        </td>
                        <td class="formaltbg" align="left">
                            0
                        </td>
                    </tr>

                </logic:iterate>
                    <tr>
                                        <td colspan="2" class="formhdbg" align="center" align="center">Total</td>
                                        <td class="formhdbg" align="center" align="center"><%=t1%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t2%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t3%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t4%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t5%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t6%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t7%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t8%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t9%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t10%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t11%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t12%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t13%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t14%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t15%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t16%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t17%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t18%></td>
                                        <td class="formhdbg" align="center" align="center"><%=t19%></td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>
                                        <td class="formhdbg" align="center" align="center">0</td>



                                    </tr>

            </table>

        </logic:notEmpty>


    </body>
</html>
