<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>




<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.text.SimpleDateFormat"%>


<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villageId");

            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;
            String f = null, t = null;
            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String name = (String) request.getParameter("names");
            String dis = (String) request.getParameter("disability");
            if (fromdate != null && fromdate.contains("-")) {
                f = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
            }
            if (todate != null && todate.contains("-")) {
                t = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
            }



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body onload="window.print();">
        <table  align="center" cellspacing="0" border="0" cellpadding="0" class="inputform" width="100%">

            <tr>
                <td>

                    <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%">
                        <tr>
                            <th align="center" class="formaltbg" ><b>   SADAREM Eligible Person with Disability Personal Details Profile<br>

                                    As On <font size="2"><%=f%></font> To <font size="2"><%=t%> <% if (name != null) {%>, <%=name%> <%}%></font>   </b></th>
                        </tr></table>
                        <logic:notEmpty name="Totalcount1">

                        <logic:iterate name="Totalcount4" id="row">
                            <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%">


                                <tr>
                                    <th align="center" class="formhdbg" >&nbsp;</th>
                                    <th class="formhdbg"align="center">&nbsp;</th>
                                    <th class="formhdbg"  align="center">&nbsp;</th>

                                    <th align="center" class="formhdbg" rowspan="1" colspan="2">Male</th>
                                    <th align="center" class="formhdbg"  rowspan="1" colspan="2">Female</th>
                                    <th align="center" class="formhdbg"  rowspan="1" colspan="2">Total</th>



                                </tr>
                                <tr>
                                    <th class="formhdbg" align="center" width="4">No</th>
                                    <th class="formhdbg" align="center" width="31%">Category</th>
                                    <th class="formhdbg" align="center" width="17%">SubType</th>

                                    <th width="8%" class="formhdbg" align="center">No</th>
                                    <th width="8%"class="formhdbg" align="center">%</th>
                                    <th width="8%" class="formhdbg" align="center">No</th>
                                    <th width="8%" class="formhdbg" align="center">%</th>
                                    <th width="8%" class="formhdbg" align="center">No</th>
                                    <th width="8%" class="formhdbg" align="center">%</th>
                                </tr>

                            </table>





                            <bean:define id="fDate" value="${row.fDate}"/>
                            <bean:define id="tDate" value="${row.tDate}"/>









                            <table align="center" cellspacing="1" border="0" cellpadding="4"  class="inputform" width="100%" >
                                <tr>

                                    <td align="center" class="formaltbg" width="4%"  rowspan="2"  >1</td></tr>
                                <tr>   <td align="left" class="formaltbg"width="31%">Number of the Person with Disability (As per the SADAREM)</td>



                                    <td  align="left" class="formbg" width="17%">TotalCategories</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r57}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.tom} </td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r59}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.tof}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r57+row.r59}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.tom+row.tof}</td></tr>


                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform"  width="100%" >

                                <tr>

                                    <td align="center"  width="4%"class="formaltbg" ></td>
                                    <td class="formaltbg" align="left" width="31%" rowspan="7">Number of the person with Disability(Category Wise)</td>
                                    <td  align="left" class="formaltbg" width="17%">Locomotor</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r1}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r2}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r3}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r4}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r1+row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s1}</td></tr>
                                <tr><td></td><td  align="left" class="formbg" width="17%">Visual Impairment</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r5}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r6}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r7}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r8}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r5+row.r7}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s2}</td></tr>
                                <tr> <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%" class="formaltbg" >2</td>
                                    <td  align="left" class="formaltbg" width="17%">Hearing Impairment</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r9}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r10}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r11}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r12}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r9+row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s3}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Mental Retardation</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r13}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r14}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r15}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r16}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r13+row.r15}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s4}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Mental Illness</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r17}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r18}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r19}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r20}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r17+row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s5}</td></tr>

                                <tr> <td></td><td  align="left" class="formbg" width="17%">Multiple</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r21}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r22}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r24}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r21+row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb" >${row.s6}</td></tr>

                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.todm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tdm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.todf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tdf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.todm+row.todf}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.d}</td></tr>




                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="4"  class="inputform" width="100%" >
                                <tr>


                                    <td  align="center" ></td>
                                    <td  align="left" width="31%"class="formaltbg"  rowspan="9">Number of the person with Disability(Education Wise)</td>

                                    <td  align="left" class="formaltbg" width="17%">Illeterate</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r29}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r30} </td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r31}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r32}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r29+row.r31}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s8}</td></tr>


                                <tr> <td></td>
                                    <td  align="left" class="formbg" width="17%">Belowtenth</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r33}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r34}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r35}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r36}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r33+row.r35}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s9}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">10th</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r37}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r38}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r39}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r40}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r37+row.r39}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s10}</td></tr>
                                <tr><td class="formaltbg" style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">3</td> <td  align="left" class="formbg" width="17%">Intermediate</td>
                                    <td width="8%"  style="text-align: center" class="formbg1" > ${row.r41}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r42}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.r43}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r44}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r41+row.r43}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s11}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Diploma</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r45}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r46}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r47}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r48}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r45+row.r47}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s12}</td></tr>

                                <tr> <td></td><td  align="left" class="formbg" width="17%">Graduate</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r49}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r50}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r51}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r52}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r49+row.r51}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s13}</td></tr>

                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Post Graduate</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r53}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r54}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"class="formaltbg">${row.r55}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r56}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1" >${row.r53+row.r55}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s14}</td></tr>

                                <tr> <td></td><td  align="left" class="formbg" width="17%">Not Recorded</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r25}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r26}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r27}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r28}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r25+row.r27}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s7}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.toedm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tedm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"class="formaltbg">${row.toedf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tedf}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1" >${row.toedm+row.toedf}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.e}</td></tr>


                            </table>
                        </logic:iterate>
                        <logic:iterate name="Totalcount3" id="row">


                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%" >

                                <tr>

                                    <td align="center"  ></td>
                                    <td class="formaltbg" align="left" width="31%" rowspan="7" >Number of the person with Disability(Employment Wise)</td>


                                    <td  align="left" class="formbg" width="17%">Govt</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r5}</td>

                                    <td width="8%"  style="text-align: center"class="formb">${row.r6}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r7}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r8}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r5+row.r7}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s2}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Private</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r9}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r10}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r9+row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s3}</td></tr>
                                <tr><td class="formaltbg" style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">4</td> <td  align="left" class="formbg" width="17%">Self Employed</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r13}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r14}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r15}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r16}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r13+row.r15}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s4}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Un Employed</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r17}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r18}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r20}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r17+row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s5}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Wage Employee</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r21}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r22}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r24}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r21+row.r23}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s6}</td></tr>

                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Not Recorded</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r1}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r2}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r4}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r1+row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s1}</td></tr>


                                <tr><td></td> <td  align="left" class="formbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.toem}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.tem}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.toef}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.tef}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.toef+row.toem}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.e2}</td></tr>

                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%" >

                                <tr>

                                    <td align="center"  width="4%" class="formaltbg" ></td>
                                    <td class="formaltbg" align="left" width="31%" rowspan="6">Number of the person with Disability(Marital Status Wise)</td>
                                    <td  align="left" class="formaltbg" width="17%">Married</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r25}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r26}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r27}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r28}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r25+row.r27}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s7}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Un Married</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r29}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r30}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r31}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r32}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r29+row.r31}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s8}</td></tr>
                                <tr> <td class="formaltbg" style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">5</td> <td  align="left" class="formaltbg" width="17%">Divorcee</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r33}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r34}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r35}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r36}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r33+row.r35}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s9}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Widow</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r37}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r38}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r39}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r40}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r37+row.r39}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s10}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Widower</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r41}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r42}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r43}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r44}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r43+row.row.r41}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s11}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.tomm}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.tmm}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.tomf}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.tmf}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.tomf+row.tomm}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.m}</td></tr>





                            </table>






                        </logic:iterate>

                        <logic:iterate name="Totalcount2" id="row">

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%" >

                                <tr>

                                    <td align="center"  ></td>
                                    <td class="formaltbg" align="left" width="31%" rowspan="8" >Number of the person with Disability(Caste Wise)</td>


                                    <td  align="left" class="formbg" width="17%">ST</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r5}</td>

                                    <td width="8%"  style="text-align: center"class="formb">${row.r6}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r7}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r8}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r5+row.r7}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s2}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">SC</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r9}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r10}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r9+row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s3}</td></tr>
                                <tr><td class="formaltbg" style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">6</td> <td  align="left" class="formbg" width="17%">BC</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r13}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r14}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r15}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r16}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r13+row.r15}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s4}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">OC</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r17}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r18}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r20}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r17+row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s5}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Minarity</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r21}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r22}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r24}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r21+row.r23}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s6}</td></tr>

                                <tr><td></td> <td  align="left" class="formbg" width="17%">NA</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.r25}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.r26}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.r27}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r28}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r25+row.r27}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s7}</td></tr>






                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Not Entered</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r1}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r2}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r4}</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r1+row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s1}</td></tr>


                                <tr><td></td> <td  align="left" class="formbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center" class="formbg1"> ${row.tocm}</td>
                                    <td width="8%"  style="text-align: center" class="formb">${row.tcm}</td>
                                    <td width="8%"  style="text-align: center" class="formbg1">${row.tocf}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.tcf}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.tocm+row.tocf}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.c}</td></tr>







                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%" >

                                <tr>

                                    <td align="center"  width="4%" class="formaltbg"></td>
                                    <td class="formaltbg" align="left" width="31%"  rowspan="10">Number of the person with Disability(Religion Wise)</td>
                                    <td  align="left" class="formbg" width="17%">Hindhu</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r33}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r34}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r35}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r36}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r33+row.r35}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s9}</td></tr>
                                <tr> <td></td><td  align="left" class="formaltbg" width="17%">Muslim</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r37}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r38}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r39}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r40}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r37+row.r39}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s10}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Christian</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r41}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r42}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r43}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r44}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r43+row.r41}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s11}</td></tr>
                                <tr>  <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%" class="formaltbg" >7</td>
                                    <td  align="left" class="formaltbg" width="17%">Sikh</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r45}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r46}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r47}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r48}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r45+row.r47}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s12}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Jain</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r49}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r50}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r51}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r52}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r49+row.r51}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s13}</td></tr>

                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Budhist</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r53}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r54}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r55}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r56}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r53+row.r55}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s14}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Others</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r57}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r58}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r59}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r60}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r59+row.r57}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s15}</td></tr>

                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Not Recorded</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r29}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r30}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r31}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r32}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r31+row.r29}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s8}</td></tr>


                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.torem}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.trem}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.toref}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tref}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.torem+row.toref}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.e1}</td></tr>


                            </table>



                        </logic:iterate>

                        <logic:iterate name="Totalcount1" id="row">

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform"  width="100%" >

                                <tr>

                                    <td align="center"  width="4%"class="formaltbg" ></td>
                                    <td class="formaltbg" align="left" width="31%" rowspan="11">Number of the person with Disability(AGE Wise)</td>
                                    <td  align="left" class="formaltbg" width="17%">0 to 3</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1">${row.r1}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r2}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r3}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r4}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r1+row.r3}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s1}</td></tr>
                                <tr><td></td><td  align="left" class="formbg" width="17%">3 to 5</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r5}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r6}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r7}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r8}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r5+row.r7}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s2}</td></tr>
                                <tr> <td ></td>
                                    <td  align="left" class="formaltbg" width="17%">5 to 14</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r9}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r10}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r11}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r12}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r9+row.r11}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s3}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">14 to 18</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r13}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r14}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r15}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r16}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r13+row.r15}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s4}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">18 to25</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r17}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r18}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r19}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r20}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r17+row.r19}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s5}</td></tr>

                                <tr><td class="formaltbg" style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;">8</td><td  align="left" class="formbg" width="17%">25 to 35</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r21}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r22}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r24}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r21+row.r23}</td>
                                    <td width="8%"  style="text-align: center"class="formb" >${row.s6}</td></tr>
                                <tr>
                                    <td ></td>
                                    <td  align="left" class="formaltbg" width="17%"> 35 to 50</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.r25}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.r26}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r27}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r28}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r25+row.r27}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s7}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">50 to 60</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r29}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r30}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r31}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r32}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r29+row.r31}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s8}</td></tr>
                                <tr> <td></td> <td  align="left" class="formaltbg" width="17%">60 to 70</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r33}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r34}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r35}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r36}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r33+row.r35}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s9}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">above 70</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r37}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r38}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r39}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r40}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r37+row.r39}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s10}</td></tr>

                                <tr> <td></td>  <td  align="left" class="formaltbg" width="17%"> Total</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.toagm}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.tagm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.toagf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tagf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.toagm+row.toagf}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.a}</td></tr>

                            </table>


                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform"  width="100%" >
                                <tr>  <td align="center"  width="4%"class="formaltbg" ></td> <td class="formaltbg" align="left" width="31%" rowspan="4">Number of the person with Disability(Consanguineous Marriage Parents Wise)</td>
                                    <td  align="left" class="formbg" width="17%">Yes</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r41}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r42}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r43}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r44}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r43+row.r41}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s12}</td></tr>
                                <tr>  <td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%" class="formaltbg" >9</td>
                                    <td  align="left" class="formaltbg" width="17%">NO</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r45}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r46}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r47}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r48}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r45+row.r47}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s11}</td></tr>
                                <tr><td></td> <td  align="left" class="formbg" width="17%">Not Recorded</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r49}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r50}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r51}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r52}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r49+row.r51}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s13}</td></tr>
                                <tr> <td></td>  <td  align="left" class="formaltbg" width="17%"> Total</td>
                                    <td width="8%"  style="text-align: center" class="formaltbg1"> ${row.totpm}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb">${row.tpm}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.totpf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.tpf}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.totpm+row.totpf}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.p}</td></tr>


                            </table>

                            <table align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%" >

                                <tr>

                                    <td align="center"  width="4%" class="formaltbg"></td>
                                    <td class="formaltbg" align="left" width="31%"  rowspan="6">Number of the person with Disability(RationCard Wise)</td>
                                    <td  align="left" class="formbg" width="17%">White</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.r57}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r58}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r59}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.r60}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.r59+row.r57}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.s15}</td></tr>
                                <tr><td></td> <td  align="left" class="formaltbg" width="17%">Pink</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r61}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r62}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r63}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r64}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r61+row.r63}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s16}</td></tr>


                                <tr><td style="border-left:0px;border-right: 0px;border-bottom: 0px;border-top: 0px;" align="center"  width="4%" class="formaltbg" >10</td><td  align="left" class="formbg" width="17%">AAY</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.a1}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.a2}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.a3}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.a4}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.a1+row.a3}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.a5}</td></tr>
                                <tr><td></td>
                                    <td  align="left" class="formaltbg" width="17%">Not Recorded/Not Entered</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1"> ${row.r53}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r54}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r55}</td>
                                    <td width="8%"  style="text-align: center"class="formaltb">${row.r56}</td>
                                    <td width="8%"  style="text-align: center"class="formaltbg1">${row.r53+row.r55}</td>
                                    <td width="8%"  style="text-align: center" class="formaltb" >${row.s14}</td></tr>





                                <tr><td></td> <td  align="left" class="formbg" width="17%">Total</td>
                                    <td width="8%"  style="text-align: center"class="formbg1"> ${row.totrm}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.trm}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.totrf}</td>
                                    <td width="8%"  style="text-align: center"class="formb">${row.trf}</td>
                                    <td width="8%"  style="text-align: center"class="formbg1">${row.totrm+row.totrf}</td>
                                    <td width="8%"  style="text-align: center" class="formb" >${row.r}</td></tr>





                            </table>


                        </logic:iterate>

                    </logic:notEmpty>

                </td></tr></table>


    </body>
</html>