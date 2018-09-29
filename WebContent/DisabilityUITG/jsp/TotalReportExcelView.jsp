<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : 490058
--%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %><%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO;" %>
<%
            int i = 1;
            String district_id = (String) request.getParameter("districtId");
            String mandal_id = (String) request.getParameter("mandalId");
            String village_id = (String) request.getParameter("villagesId");
            String districtName = null;
            String mandalName = null;
            String villageName = null;
            String habName = null;String f=null,t=null;
            
             String fromdate = (String) request.getParameter("fromdate");
                    String todate = (String) request.getParameter("todate");
                      String name=(String) request.getParameter("names");
                      
                    String dis=(String) request.getParameter("disability");
if(fromdate!=null && fromdate.contains("-")){
                         f=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fromdate));
                         }
                     if(todate!=null && todate.contains("-")){
                         t=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(todate));
                         }

         
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    <style type="text/css">
      /*  .formbg1 {
   font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:blue; border-left:1px; border-left-color:#999999; border-left-style:solid; border-bottom:1px; border-bottom-color:#999999; border-bottom-style:solid; padding:3px; padding-left:6px; background-color:#e5e5e5;
}.formaltbg1 {
   font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:blue; border-left:1px; border-left-color:#999999; border-left-style:solid; border-bottom:1px; border-bottom-color:#999999; border-bottom-style:solid; padding:3px; padding-left:6px; background-color:#ffffff;
}*/.formb {
   font-family:Verdana, Arial, Helvetica, sans-serif; font-size:11px; font-weight:normal; color:#333333; border-left:1px; border-left-color:#999999; border-left-style:solid; border-bottom:1px; border-bottom-color:#999999; border-bottom-style:solid; padding:3px; padding-left:6px; background-color:#e5e5e5;
}.formaltb {
   font-family:Verdana, Arial, Helvetica, sans-serif; font-size:11px; font-weight:normal; color:#333333; border-left:1px; border-left-color:#999999; border-left-style:solid; border-bottom:1px; border-bottom-color:#999999; border-bottom-style:solid; padding:3px; padding-left:6px; background-color:#ffffff;
}</style>
    </head>
    <body>
        <logic:notEmpty name="Totalcount">
      <logic:iterate name="Totalcount" id="row">
            <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
             <tr>
                    <td  align="center" valign="top">
                        <table  align="center" cellspacing="1" border="1" cellpadding="4" width="90%">
                            <tr> <td align="center" colspan="9" class="formaltbg"><b>    SADAREM Eligible Person with Disability Personal Details Profile<br>

 As On <font color="blue"><%=f%></font> To <font color="blue"><%=t%><% if(name!=null) {%>, <%=name%> <%}%> </font>   </b></td></tr></table></td></tr></table>
 <table  align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%">
                    <tr>
                                 <td align="center" class="formhdbg" >&nbsp;</td>
                                <td class="formhdbg"  align="center">&nbsp;</td>
                                <td class="formhdbg"  align="center">&nbsp;</td>

                                <td align="center" class="formhdbg" rowspan="1" colspan="2">Male</td>
                                <td align="center" class="formhdbg"  rowspan="1" colspan="2">Female</td>
                                <td align="center" class="formhdbg"  rowspan="1" colspan="2">Total</td>



                            </tr>
                             <tr>
                               <td class="formhdbg" align="center" width="4">S.No</td>
                               <td class="formhdbg" align="center" width="31%">Category</td>
                               <td class="formhdbg" align="center" width="17%">SubType</td>
                               <td width="8%" class="formhdbg" align="center">No</td>
                               <td width="8%"class="formhdbg" align="center">%</td>
                               <td width="8%" class="formhdbg" align="center">No</td>
                               <td width="8%" class="formhdbg" align="center">%</td>
                               <td width="8%" class="formhdbg" align="center">No</td>
                               <td width="8%" class="formhdbg" align="center">%</td>
                            </tr>
                          <!--  <tr>
                                <td  class="formhdbg" align="center" rowspan="1">1</td>
                                <td class="formhdbg" align="center" >Number of the Person with Disability (As per the SADAREM)</td>
                                <td class="formhdbg" align="center">${row.Tmale}</td>
                                <td class="formhdbg" align="center">${row.Tmaleper}</td>
                                <td class="formhdbg" align="center">${row.Tfemale}</td>
                                <td class="formhdbg" align="center">${row.Tfemaleper}</td>
                                <td class="formhdbg" align="center">${row.Tmale+row.Tfemale}</td>
                                <td class="formhdbg" align="center">${row.total}</td>

                            </tr>--></table>





         <bean:define id="fDate" value="${row.fDate}"/>
                                      <bean:define id="tDate" value="${row.tDate}"/>






     <table align="center" cellspacing="1" border="1" cellpadding="4"  class="innerTable" width="90%" >
                       <tr>

                           <td align="center" class="formaltbg" width="4%" >1</td>
                           <td align="left" class="formaltbg"width="31%" >Number of the Person with Disability (As per the SADAREM)</td>
                           <td width="65%">
                               <table border="1" width ="100%">
                                   <tr><!--<td  align="left" class="formbg" width="1%">&nbsp;</td>-->
                                       <td  align="left" class="formbg" width="17%">TotalCategories</td>
                              <td width="8%" class="formbg">${row.Tmale}</td>
                              <td width="8%"class="formbg">${row.Tmaleper} </td>
                              <td width="8%" class="formbg">${row.Tfemale}</td>
                              <td width="8%" class="formbg">${row.Tfemaleper}</td>
                              <td width="8%" class="formbg">${row.Tmale+row.Tfemale}</td>
                             <td width="8%" class="formbg" >${row.Tmaleper+row.Tfemaleper}</td></tr>

                               </table></td></tr></table>

     <table align="center" cellspacing="1" border="1" cellpadding="4"  class="innerTable" width="90%" >
                       <tr>

                           <td align="center"  width="4%"class="formaltbg" >2</td>
                           <td  align="left" width="31%"class="formaltbg">Number of the person with Disability(Education Wise)</td>
                           <td width="65%">
                               <table border="1" width ="100%">
                                   <tr> <td  align="left" class="formaltbg" width="17%">Illeterate</td>
                              <td width="8%" class="formaltbg1">${row.r49}</td>
                              <td width="8%" class="formaltbg">${row.r50} </td>
                              <td width="8%" class="formaltbg1">${row.r51}</td>
                              <td width="8%" class="formaltbg">${row.r52}</td>
                              <td width="8%" class="formaltbg1">${row.r49+row.r51}</td>
                             <td width="8%" class="formaltbg" >${row.r50+row.r52}</td></tr>


                           <tr> <td  align="left" class="formbg" width="17%">Belowtenth</td>
                               <td width="8%" class="formbg1">${row.r53}</td>
                              <td width="8%"class="formbg">${row.r54}</td>
                              <td width="8%"class="formbg1">${row.r55}</td>
                              <td width="8%"class="formbg">${row.r56}</td>
                              <td width="8%"class="formbg1">${row.r53+row.r55}</td>
                             <td width="8%" class="formbg" >${row.r54+row.r56}</td></tr>
                       <tr> <td  align="left" class="formaltbg" width="17%">10th</td>
                               <td width="8%"class="formaltbg1">${row.r57}</td>
                              <td width="8%"class="formaltbg">${row.r58}</td>
                              <td width="8%"class="formaltbg1">${row.r59}</td>
                              <td width="8%"class="formaltbg">${row.r60}</td>
                              <td width="8%"class="formaltbg1">${row.r57+row.r59}</td>
                             <td width="8%" class="formaltbg" >${row.r58+row.r60}</td></tr>
                       <tr> <td  align="left" class="formbg" width="17%">Intermediate</td>
                              <td width="8%" class="formbg1" > ${row.r61}</td>
                              <td width="8%" class="formbg">${row.r62}</td>
                              <td width="8%" class="formbg1">${row.r63}</td>
                              <td width="8%"class="formbg">${row.r64}</td>
                              <td width="8%"class="formbg1">${row.r61+row.r63}</td>
                             <td width="8%" class="formbg" >${row.r62+row.r64}</td></tr>
                       <tr> <td  align="left" class="formaltbg" width="17%">Diploma</td>
                              <td width="8%"class="formaltbg1"> ${row.r65}</td>
                              <td width="8%"class="formaltbg">${row.r66}</td>
                              <td width="8%"class="formaltbg1">${row.r67}</td>
                              <td width="8%"class="formaltbg">${row.r68}</td>
                              <td width="8%"class="formaltbg1">${row.r65+row.r67}</td>
                             <td width="8%" class="formaltbg" >${row.r66+row.r68}</td></tr>

                      <tr> <td  align="left" class="formbg" width="17%">Graduate</td>
                              <td width="8%" class="formbg1"> ${row.r69}</td>
                              <td width="8%"class="formbg">${row.r70}</td>
                              <td width="8%"class="formbg1">${row.r71}</td>
                              <td width="8%"class="formbg">${row.r72}</td>
                              <td width="8%"class="formbg1">${row.r69+row.r71}</td>
                             <td width="8%" class="formbg" >${row.r70+row.r72}</td></tr>

                      <tr> <td  align="left" class="formaltbg" width="17%">Post Graduate</td>
                              <td width="8%"class="formaltbg1"> ${row.r73}</td>
                              <td width="8%"class="formaltbg">${row.r74}</td>
                              <td width="8%"class="formaltbg1"class="formaltbg">${row.r75}</td>
                              <td width="8%"class="formaltbg">${row.r76}</td>
                              <td width="8%" class="formaltbg1" >${row.r73+row.r75}</td>
                             <td width="8%" class="formaltbg" >${row.r74+row.r76}</td></tr>
                     </table></td></tr></table>



 <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%" >

           <tr>

                           <td align="center" class="formaltbg" width="4%" >3</td>
                           <td class="formaltbg" align="left" width="31%">Number of the person with Disability(Employment Wise)</td>


                       <td width="65%"><table border="1" width ="100%">
                                   <tr> <td  align="left" class="formbg" width="17%">Govt</td>
                               <td width="8%" class="formbg1"> ${row.r77}</td>

                              <td width="8%"class="formbg">${row.r78}</td>
                              <td width="8%"class="formbg1">${row.r79}</td>
                              <td width="8%"class="formbg">${row.r80}</td>
                              <td width="8%"class="formbg1">${row.r77+row.r79}</td>
                             <td width="8%" class="formbg" >${row.r78+row.r80}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Private</td>
                            <td width="8%" class="formaltbg1"> ${row.r81}</td>
                              <td width="8%" class="formaltbg">${row.r82}</td>
                               <td width="8%" class="formaltbg1"> ${row.r83}</td>
                              <td width="8%" class="formaltbg">${row.r84}</td>
                              <td width="8%" class="formaltbg1">${row.r81+row.r83}</td>
                             <td width="8%" class="formaltbg" >${row.r82+row.r84}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Self Employed</td>
                              <td width="8%" class="formbg1"> ${row.r85}</td>
                              <td width="8%"class="formbg">${row.r86}</td>
                              <td width="8%"class="formbg1">${row.r87}</td>
                              <td width="8%"class="formbg">${row.r88}</td>
                              <td width="8%"class="formbg1">${row.r85+row.r87}</td>
                             <td width="8%" class="formbg" >${row.r86+row.r88}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Un Employed</td>
                             <td width="8%" class="formaltbg1"> ${row.r89}</td>
                              <td width="8%" class="formaltbg">${row.r90}</td>
                              <td width="8%"class="formaltbg1">${row.r91}</td>
                              <td width="8%" class="formaltbg">${row.r92}</td>
                              <td width="8%" class="formaltbg1">${row.r89+row.r91}</td>
                             <td width="8%" class="formaltbg" >${row.r90+row.r92}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Wage Employee</td>
                               <td width="8%" class="formbg1"> ${row.r93}</td>
                              <td width="8%" class="formbg">${row.r94}</td>
                              <td width="8%" class="formbg1">${row.r95}</td>
                              <td width="8%"class="formbg">${row.r96}</td>
                              <td width="8%"class="formbg1">${row.r93+row.r95}</td>
                             <td width="8%" class="formbg" >${row.r94+row.r96}</td></tr>
                  </table></td></tr></table>

          <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%" >

           <tr>

                           <td align="center"  width="4%" >4</td>
                           <td class="formaltbg" align="left" width="31%">Number of the person with Disability(Marital Status Wise)</td>
                              <td width="65%"><table border="1" width ="100%">
                                   <tr> <td  align="left" class="formaltbg" width="17%">Married</td>
                               <td width="8%" class="formaltbg1"> ${row.r97}</td>
                              <td width="8%" class="formaltbg">${row.r98}</td>
                              <td width="8%"class="formaltbg1">${row.r99}</td>
                              <td width="8%"class="formaltbg">${row.r100}</td>
                              <td width="8%"class="formaltbg1">${row.r97+row.r99}</td>
                             <td width="8%" class="formaltbg" >${row.r98+row.r100}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Un Married</td>
                               <td width="8%"class="formbg1"> ${row.r101}</td>
                              <td width="8%"class="formbg">${row.r102}</td>
                              <td width="8%"class="formbg1">${row.r103}</td>
                              <td width="8%"class="formbg">${row.r104}</td>
                              <td width="8%"class="formbg1">${row.r101+row.r103}</td>
                             <td width="8%" class="formbg" >${row.r102+row.r104}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Divorcee</td>
                             <td width="8%"class="formaltbg1"> ${row.r105}</td>
                              <td width="8%"class="formaltbg">${row.r106}</td>
                              <td width="8%"class="formaltbg1">${row.r107}</td>
                              <td width="8%"class="formaltbg">${row.r108}</td>
                              <td width="8%"class="formaltbg1">${row.r107+row.r105}</td>
                             <td width="8%" class="formaltbg" >${row.r108+row.r106}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Widow</td>
                             <td width="8%"class="formbg1"> ${row.r109}</td>
                              <td width="8%"class="formbg">${row.r110}</td>
                              <td width="8%"class="formbg1">${row.r111}</td>
                              <td width="8%"class="formbg">${row.r112}</td>
                              <td width="8%"class="formbg1">${row.r111+row.r109}</td>
                             <td width="8%" class="formbg" >${row.r112+row.r110}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Widower</td>
                             <td width="8%"class="formaltbg1"> ${row.r113}</td>
                              <td width="8%"class="formaltbg">${row.r114}</td>
                              <td width="8%"class="formaltbg1">${row.r115}</td>
                              <td width="8%"class="formaltbg">${row.r116}</td>
                              <td width="8%"class="formaltbg1">${row.r113+row.row.r115}</td>
                             <td width="8%" class="formaltbg" >${row.r114+row.r116}</td></tr>





                   </table></td></tr></table>

  <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%" >

           <tr>

                           <td align="center"  width="4%" >5</td>
                           <td class="formaltbg" align="left" width="31%">Number of the person with Disability(Caste Wise)</td>
                              <td width="65%"><table border="1" width ="100%">
                              <tr> <td  align="left" class="formbg" width="17%">ST</td>
                              <td width="8%"class="formbg1"> ${row.r1}</td>
                              <td width="8%"class="formbg">${row.r2}</td>
                              <td width="8%"class="formbg1">${row.r3}</td>
                              <td width="8%"class="formbg">${row.r4}</td>
                              <td width="8%"class="formbg1">${row.r1+row.r3}</td>
                             <td width="8%" class="formbg" >${row.r2+row.r4}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">SC</td>
                             <td width="8%"class="formaltbg1"> ${row.r5}</td>
                              <td width="8%"class="formaltbg">${row.r6}</td>
                              <td width="8%"class="formaltbg1">${row.r7}</td>
                              <td width="8%"class="formaltbg">${row.r8}</td>
                              <td width="8%"class="formaltbg1">${row.r5+row.r7}</td>
                             <td width="8%" class="formaltbg" >${row.r6+row.r8}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">BC</td>
                              <td width="8%"class="formbg1"> ${row.r9}</td>
                              <td width="8%"class="formbg">${row.r10}</td>
                              <td width="8%"class="formbg1">${row.r11}</td>
                              <td width="8%"class="formbg">${row.r12}</td>
                              <td width="8%"class="formbg1">${row.r9+row.r11}</td>
                             <td width="8%" class="formbg" >${row.r10+row.r12}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">OC</td>
                             <td width="8%"class="formaltbg1"> ${row.r13}</td>
                              <td width="8%"class="formaltbg">${row.r14}</td>
                              <td width="8%"class="formaltbg1">${row.r15}</td>
                              <td width="8%"class="formaltbg">${row.r16}</td>
                              <td width="8%"class="formaltbg1">${row.r13+row.r15}</td>
                             <td width="8%" class="formaltbg" >${row.r14+row.r16}</td></tr>
                       <tr> <td  align="left" class="formbg" width="17%">Minarity</td>
                             <td width="8%"class="formbg1"> ${row.r17}</td>
                              <td width="8%"class="formbg">${row.r18}</td>
                              <td width="8%"class="formbg1">${row.r19}</td>
                              <td width="8%"class="formbg">${row.r20}</td>
                              <td width="8%"class="formbg1">${row.r17+row.r19}</td>
                             <td width="8%" class="formbg" >${row.r18+row.r20}</td></tr>

                        <tr> <td  align="left" class="formaltbg" width="17%">NA</td>
                              <td width="8%"class="formaltbg1"> ${row.r21}</td>
                              <td width="8%"class="formaltbg">${row.r22}</td>
                              <td width="8%"class="formaltbg1">${row.r23}</td>
                              <td width="8%"class="formaltbg">${row.r24}</td>
                              <td width="8%"class="formaltbg1">${row.r21+row.r23}</td>
                             <td width="8%" class="formaltbg" >${row.r22+row.r24}</td></tr>



                   </table></td></tr></table>
                             <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%" >

           <tr>

                           <td align="center"  width="4%" >6</td>
                           <td class="formaltbg" align="left" width="31%">Number of the person with Disability(Religion Wise)</td>
                               <td width="65%"><table border="1" width ="100%">
                                   <tr> <td  align="left" class="formbg" width="17%">Hindhu</td>
                             <td width="8%"class="formbg1"> ${row.r125}</td>
                              <td width="8%"class="formbg">${row.r126}</td>
                              <td width="8%"class="formbg1">${row.r127}</td>
                              <td width="8%"class="formbg">${row.r128}</td>
                              <td width="8%"class="formbg1">${row.r125+row.r127}</td>
                             <td width="8%" class="formbg" >${row.r126+row.r128}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Muslim</td>
                              <td width="8%"class="formaltbg1"> ${row.r129}</td>
                              <td width="8%"class="formaltbg">${row.r130}</td>
                              <td width="8%"class="formaltbg1">${row.r131}</td>
                              <td width="8%"class="formaltbg">${row.r132}</td>
                              <td width="8%"class="formaltbg1">${row.r131+row.r129}</td>
                             <td width="8%" class="formaltbg" >${row.r132+row.r130}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Christian</td>
                              <td width="8%"class="formbg1"> ${row.r133}</td>
                              <td width="8%"class="formbg">${row.r134}</td>
                              <td width="8%"class="formbg1">${row.r135}</td>
                              <td width="8%"class="formbg">${row.r136}</td>
                              <td width="8%"class="formbg1">${row.r133+row.r135}</td>
                             <td width="8%" class="formbg" >${row.r134+row.r136}</td></tr>
                       <tr> <td  align="left" class="formaltbg" width="17%">Sikh</td>
                              <td width="8%"class="formaltbg1"> ${row.r137}</td>
                              <td width="8%"class="formaltbg">${row.r138}</td>
                              <td width="8%"class="formaltbg1">${row.r139}</td>
                              <td width="8%"class="formaltbg">${row.r140}</td>
                              <td width="8%"class="formaltbg1">${row.r137+row.r139}</td>
                             <td width="8%" class="formaltbg" >${row.r138+row.r140}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Jain</td>
                              <td width="8%"class="formbg1"> ${row.r141}</td>
                              <td width="8%"class="formbg">${row.r142}</td>
                              <td width="8%"class="formbg1">${row.r143}</td>
                              <td width="8%"class="formbg">${row.r144}</td>
                              <td width="8%"class="formbg1">${row.r141+row.r143}</td>
                             <td width="8%" class="formbg" >${row.r142+row.r144}</td></tr>

                       <tr> <td  align="left" class="formaltbg" width="17%">Budhist</td>
                              <td width="8%"class="formaltbg1"> ${row.r145}</td>
                              <td width="8%"class="formaltbg">${row.r146}</td>
                              <td width="8%"class="formaltbg1">${row.r147}</td>
                              <td width="8%"class="formaltbg">${row.r148}</td>
                              <td width="8%"class="formaltbg1">${row.r145+row.r147}</td>
                             <td width="8%" class="formaltbg" >${row.r146+row.r148}</td></tr>
                       <tr> <td  align="left" class="formbg" width="17%">Others</td>
                              <td width="8%"class="formbg1"> ${row.r149}</td>
                              <td width="8%"class="formbg">${row.r150}</td>
                              <td width="8%"class="formbg1">${row.r151}</td>
                              <td width="8%"class="formbg">${row.r152}</td>
                              <td width="8%"class="formbg1">${row.r149+row.r151}</td>
                             <td width="8%" class="formbg" >${row.r150+row.r152}</td></tr>



                    </table></td></tr></table>
                             <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable"  width="90%" >

          <tr>

                           <td align="center"  width="4%" >7</td>
                           <td class="formaltbg" align="left" width="31%">Number of the person with Disability(Category Wise)</td>
                              <td width="65%"><table border="1" width ="100%" cellpadding="4" cellspacing="1">
                                   <tr> <td  align="left" class="formaltbg" width="17%">Locomotor Disability</td>
                              <td width="8%" class="formaltbg1">${row.r25}</td>
                              <td width="8%"class="formaltbg">${row.r26}</td>
                              <td width="8%"class="formaltbg1">${row.r27}</td>
                              <td width="8%"class="formaltbg">${row.r28}</td>
                              <td width="8%"class="formaltbg1">${row.r25+row.r27}</td>
                             <td width="8%" class="formaltbg" >${row.r26+row.r28}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Visual Impairment</td>
                              <td width="8%"class="formbg1"> ${row.r29}</td>
                              <td width="8%"class="formbg">${row.r30}</td>
                              <td width="8%"class="formbg1">${row.r31}</td>
                              <td width="8%"class="formbg">${row.r32}</td>
                              <td width="8%"class="formbg1">${row.r29+row.r31}</td>
                             <td width="8%" class="formbg" >${row.r30+row.r32}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Hearing Impairment</td>
                               <td width="8%"class="formaltbg1"> ${row.r33}</td>
                              <td width="8%"class="formaltbg">${row.r34}</td>
                              <td width="8%"class="formaltbg1">${row.r35}</td>
                              <td width="8%"class="formaltbg">${row.r36}</td>
                              <td width="8%"class="formaltbg1">${row.r33+row.r35}</td>
                             <td width="8%" class="formaltbg" >${row.r34+row.r36}</td></tr>
                        <tr> <td  align="left" class="formbg" width="17%">Mental Retardation</td>
                               <td width="8%"class="formbg1">${row.r37}</td>
                              <td width="8%"class="formbg">${row.r38}</td>
                              <td width="8%"class="formbg1">${row.r39}</td>
                              <td width="8%"class="formbg">${row.r40}</td>
                              <td width="8%"class="formbg1">${row.r37+row.r39}</td>
                             <td width="8%" class="formbg" >${row.r38+row.r40}</td></tr>
                        <tr> <td  align="left" class="formaltbg" width="17%">Mental Illness</td>
                               <td width="8%"class="formaltbg1"> ${row.r41}</td>
                              <td width="8%"class="formaltbg">${row.r42}</td>
                              <td width="8%"class="formaltbg1">${row.r43}</td>
                              <td width="8%"class="formaltbg">${row.r44}</td>
                              <td width="8%"class="formaltbg1">${row.r41+row.r43}</td>
                             <td width="8%" class="formaltbg" >${row.r42+row.r44}</td></tr>

                       <tr> <td  align="left" class="formbg" width="17%">Multiple Disability</td>
                               <td width="8%"class="formbg1"> ${row.r45}</td>
                              <td width="8%"class="formbg">${row.r46}</td>
                              <td width="8%"class="formbg1">${row.r47}</td>
                              <td width="8%"class="formbg">${row.r48}</td>
                              <td width="8%"class="formbg1">${row.r45+row.r47}</td>
                             <td width="8%"class="formbg" >${row.r46+row.r48}</td></tr>



                    </table></td></tr></table>

                              <table align="center" cellspacing="1" border="1" cellpadding="4" class="innerTable" width="90%" >

           <tr>

                           <td align="center"  width="4%" >8</td>
                           <td class="formaltbg" align="left" width="48%" colspan="2">Number of the peson with Disability having Consanguineous Marriage Parents
</td>
                               <td width="48%"><table border="1" width ="100%">
                                   <tr>
                               <td width="8%"class="formaltbg1"> ${row.r121}</td>
                              <td width="8%"class="formaltbg">${row.r122}</td>
                              <td width="8%"class="formaltbg1">${row.r123}</td>
                              <td width="8%"class="formaltbg">${row.r124}</td>
                              <td width="8%"class="formaltbg1">${row.r121+row.r123}</td>
                             <td width="8%" class="formaltbg" >${row.r122+row.r124}</td></tr>



                   </table></td></tr></table>


</logic:iterate>

        </logic:notEmpty>

           
    </body>
</html>