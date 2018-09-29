<%--
    Document   : districtWiseStatusReportForPartBView
    Created on : May 30, 2010, 4:09:42 PM
    Author     : srinivas_p
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.PartADTO;" %>
<%@ page contentType="application/MyExcel.ms-excel" %>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%          String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");
            String type = (String) request.getAttribute("type");
            String district = (String) request.getAttribute("dname");
            String mandal = (String) request.getAttribute("mname");


            String pensionPhase = null;
            String ph = null;
            if (request.getAttribute("pensionPhase") != null) {
                pensionPhase = (String) request.getAttribute("pensionPhase");
                ph = (String) request.getAttribute("pensionPhase");
                if (pensionPhase.equals("1")) {
                    pensionPhase = "PhaseI";
                } else if (pensionPhase.equals("2")) {
                    pensionPhase = "PhaseII";
                } else if (pensionPhase.equals("3")) {
                    pensionPhase = "PhaseIII";
                } else if (pensionPhase.equals("4")) {
                    pensionPhase = "PhaseIV";
                } else if (pensionPhase.equals("5")) {
                    pensionPhase = "RachabandaI";
                } else if (pensionPhase.equals("7")) {
                    pensionPhase = "RachabandaII";
                } else if (pensionPhase.equals("6")) {
                    pensionPhase = "ALL (PhaseI,PhaseII,PhaseIV)";
                }
            }


            int existingpensionerscount = 0, partACount = 0, totalPersonsAssed = 0, deathAndPm = 0;
            int deathPensioners = 0, pmPensioners = 0, totalDeleted = 0, assessedICFS = 0;
            int orthoCount = 0, visualCount = 0, hearingCount = 0, MRCount = 0, assessedOthers = 0;
            int MICount = 0, MDCount = 0, total = 0, directRejected = 0, tobeAssessed = 0;
            int assessedAndRejected = 0, totalRejected = 0;
            int icfsPensioners = 0, otherPensioners = 0, underGoSadarem = 0, livePensioners = 0;
            int susTmPensioners = 0;
            ArrayList dailyreportlist = new ArrayList();

            if (request.getAttribute("dailyreportlist") != null) {
                dailyreportlist = (ArrayList) request.getAttribute("dailyreportlist");
            }
            if (request.getAttribute("mandallist") != null) {
                dailyreportlist = (ArrayList) request.getAttribute("mandallist");
            }
            if (request.getAttribute("villagelist") != null) {
                dailyreportlist = (ArrayList) request.getAttribute("villagelist");
            }
            Iterator iter = dailyreportlist.iterator();
            while (iter.hasNext()) {
                PartADTO partAdto = (PartADTO) iter.next();
                deathPensioners = deathPensioners + Integer.parseInt(partAdto.getDeathPensioners());
                pmPensioners = pmPensioners + Integer.parseInt(partAdto.getPmPensioners());
                totalDeleted = totalDeleted + Integer.parseInt(partAdto.getTotalDeleted());
                //assessedICFS = assessedICFS + Integer.parseInt(partAdto.getAssessedICFS());
                deathAndPm = deathAndPm + Integer.parseInt(partAdto.getDeathAndPm());
                assessedOthers = assessedOthers + Integer.parseInt(partAdto.getAssessedOthers());
                tobeAssessed = tobeAssessed + Integer.parseInt(partAdto.getTobeAssessed());
                susTmPensioners = susTmPensioners + Integer.parseInt(partAdto.getSusTmPensioners());
                //icfsPensioners = icfsPensioners + Integer.parseInt(partAdto.getIcfsPensioners());
                otherPensioners = otherPensioners + Integer.parseInt(partAdto.getOtherPensioners());
                underGoSadarem = underGoSadarem + Integer.parseInt(partAdto.getUnderGoSadarem());
                livePensioners = livePensioners + Integer.parseInt(partAdto.getLivePensioners());
                existingpensionerscount = existingpensionerscount + Integer.parseInt(partAdto.getExistingpensioners());
                partACount = partACount + Integer.parseInt(partAdto.getPartacount());
                totalPersonsAssed = totalPersonsAssed + Integer.parseInt(partAdto.getTotalpersonsassessed());
                orthoCount = orthoCount + Integer.parseInt(partAdto.getOrtho());
                visualCount = visualCount + Integer.parseInt(partAdto.getVisual());
                hearingCount = hearingCount + Integer.parseInt(partAdto.getHearing());
                MRCount = MRCount + Integer.parseInt(partAdto.getMentalretardation());
                MICount = MICount + Integer.parseInt(partAdto.getMentalillness());
                MDCount = MDCount + Integer.parseInt(partAdto.getMultipledisability());
                total = total + Integer.parseInt(partAdto.getTotal());
                directRejected = directRejected + Integer.parseInt(partAdto.getDirectrejected());
                assessedAndRejected = assessedAndRejected + Integer.parseInt(partAdto.getAssessedandrejected());
                totalRejected = totalRejected + Integer.parseInt(partAdto.getTotalrejected());
            }




%>

<html:html>

    <head>
        <title>SADAREM</title>
        <script language="JavaScript" src="./DisabilityUI/js/date-picker"></script>
        <script>
            function ShowDate()
            {
                var dt = new Date();
                var d =dt.getDate()+"/"+(dt.getMonth()+1) +"/" +dt.getFullYear();
                document.getElementById(8).value = d;
            }
            function getValidate(){
                var d = document.forms[0];
                if(d.typeOfSearch[0].checked==false && d.typeOfSearch[1].checked==false && d.typeOfSearch[2].checked==false){
                    alert("Please Check Search Type");
                    return false;
                }else if(d.typeOfSearch[0].checked==true){
                    if(d.fromdate.value==""){
                        alert("Please Select From Date");
                        d.fromdate.focus();
                        return false;
                    }else if(d.todate.value==""){
                        alert("Please Select To Date");
                        d.todate.focus();
                        return false;
                    }else if(d.pensionPhase.value==""){
                        alert("Please Select Pension Phase");
                        d.pensionPhase.focus();
                        return false;
                    }
                }if(d.typeOfSearch[1].checked==true){
                    if(d.year.value=="0"){
                        alert("Please Select Year");
                        d.year.focus();
                        return false;
                    }else if(d.month.value=="0"){
                        alert("Please Select Month");
                        d.month.focus();
                        return false;
                    }else if(d.pensionPhase.value==""){
                        alert("Please Select Pension Phase");
                        d.pensionPhase.focus();
                        return false;
                    }
                }if(d.typeOfSearch[2].checked==true){
                    if(d.financialYear.value=="0"){
                        alert("Please Select Financial Year");
                        d.financialYear.focus();
                        return false;
                    }else if(d.pensionPhase.value==""){
                        alert("Please Select Pension Phase");
                        d.pensionPhase.focus();
                        return false;
                    }else {
                        d.mode.value="unspecified";
                        d.submit();
                    }
                }else {
                    d.mode.value="unspecified";
                    d.submit();
                }
               

            }

            function changeSelection(type){
                if(type=="1"){
                    document.getElementById('dates').style.display="";
                    document.getElementById('month').style.display="none";
                    document.getElementById('fyear').style.display="none";
                }else if(type=="2"){
                    document.getElementById('dates').style.display="none";
                    document.getElementById('month').style.display="";
                    document.getElementById('fyear').style.display="none";
                }else if(type=="3"){
                    document.getElementById('dates').style.display="none";
                    document.getElementById('month').style.display="none";
                    document.getElementById('fyear').style.display="";
                }
            }
            function getMonths(){
                document.forms[0].mode.value = "unspecified";
                document.forms[0].submit();
            }

        </script>
    </head>
    <body onload="ShowDate()">
        <html:form action="/districtWiseStatusReportForPartBViewNew" >
            <html:hidden property="mode"/>
            <logic:present name="dailyreportlist" scope="request">

                <table  align="center" cellspacing="1" border="1" cellpadding="4" width="100%">
                    <tr>
                        <th  rowspan="2" align="center">District</th>
                        <th align="left"  rowspan="2">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</th>
                        <th align="left"  rowspan="2">ABH &<br> Not Elg</th>
                        <th align="center"  colspan="4">Deleted</th>
                        <th align="left"  rowspan=2>Total No.<br> of <br>  persons<br> to under<br> go <br> SADAREM<br>(3-8)</th>
                        <th align="left"  rowspan=2>No. for<br> Part-A<br> Entered</th>
                        <th align="left"  colspan="3">Total no of persons Assessed</th>
                        <th align="left"  rowspan="2">Total<br> Assessed<br> (12+13)</th>
                        <th align="left"  rowspan="2">No of<br> persons<br> to be<br> Assessed<br> (9-14)</th>
                        <th align="center"  colspan="7">Certificates Issued</th>
                        <th align="center"  colspan="3">Rejected Status</th>
                    </tr>
                    <tr>
                        <th align="left" >Death</th>
                        <th align="left" >PM</th>
                        <th align="left" >Suspend & <br> TM</th>
                        <th align="left" >Total</th>
                        <th align="left" >Death </th>
                        <th align="left" >Live</th>
                        <th align="left" >ABH ,<br> Not ELG, <br>Suspend ,<br> TM <br> & PM</th>
                        <th align="left" >Ortho</th>
                        <th align="left" >Visual</th>
                        <th align="left" >Hearing</th>
                        <th align="left" >Mental<br>(MR)</th>
                        <th align="left" >Mental<br> Illness</th>
                        <th align="left" >Multiple<br> Disability</th>
                        <th align="left" >Total</th>
                        <th align="center" >Before<br> Assment<br> (No <br> visible<br> disb.)</th>
                        <th align="center" >After<br> Assment<br> (<40%<br> disb.)</th>
                        <th align="left" >Total<br> Rejected</th>
                    </tr>
                    <tr>
                        <th align="center">1</th>
                        <th align="center">2</th>
                        <th align="center">3</th>
                        <th align="center">4</th>
                        <th align="center">5</th>
                        <th align="center">6</th>
                        <th align="center">7</th>
                        <th align="center">8</th>
                        <th align="center">9</th>
                        <th align="center">10</th>
                        <th align="center">11</th>
                        <th align="center">12</th>
                        <th align="center">13</th>
                        <th align="center">14</th>
                        <th align="center">15</th>
                        <th align="center">16</th>
                        <th align="center">17</th>
                        <th align="center">18</th>
                        <th align="center">19</th>
                        <th align="center">20</th>
                        <th align="center">21</th>
                        <th align="center">22</th>
                        <th align="center">23</th>
                        <th align="center">24</th>
                    </tr>
                    <logic:iterate id="modify" name="dailyreportlist" scope="request">
                        <tr>
                            <td  >
                                <bean:write name="modify" property="district"/>
                            </td>
                            <td align="center"><bean:write name="modify" property="existingpensioners"/></td>
                            <td align="center"><bean:write name="modify" property="otherPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="deathPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="pmPensioners"/></td>

                            <td align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="totalDeleted"/></td>
                            <%-- <td align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>

                            <td align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                            <td align="center"><bean:write name="modify" property="partacount"/></td>
                            <td align="center"><bean:write name="modify" property="deathAndPm"/></td>
                            <td align="center"><bean:write name="modify" property="livePensioners"/></td>

                            <%--  <td align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                            <td align="center"><bean:write name="modify" property="assessedOthers"/></td>
                            <td align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                            <td align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                            <td align="center"><bean:write name="modify" property="ortho"/></td>
                            <td align="center"><bean:write name="modify" property="visual"/></td>
                            <td align="center"><bean:write name="modify" property="hearing"/></td>
                            <td align="center"><bean:write name="modify" property="mentalretardation"/></td>
                            <td align="center"><bean:write name="modify" property="mentalillness"/></td>
                            <td align="center"><bean:write name="modify" property="multipledisability"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                            <td align="center"><bean:write name="modify" property="directrejected"/></td>
                            <td align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                            <td align="center"><bean:write name="modify" property="totalrejected"/></td>
                        </tr>
                    </logic:iterate>
                    <tr>

                        <td ><b>Total</b></td>
                        <td  align="center"><b><%=existingpensionerscount%></b></td>
                        <td  align="center"><b><%=otherPensioners%></b></td>
                        <td  align="center"><b><%=deathPensioners%></b></td>
                        <td  align="center"><b><%=pmPensioners%></b></td>
                        <td  align="center"><b><%=susTmPensioners%></b></td>
                        <td  align="center"><b><%=totalDeleted%></b></td>
                        <%-- <td  align="center"><b><%=icfsPensioners%></b></td>--%>

                        <td  align="center"><b><%=underGoSadarem%></b></td>
                        <td  align="center"><b><%=partACount%></b></td>
                        <td  align="center"><b><%=deathAndPm%></b></td>
                        <td  align="center"><b><%=livePensioners%></b></td>
                        <%-- <td  align="center"><b><%=assessedICFS%></b></td>--%>
                        <td  align="center"><b><%=assessedOthers%></b></td>
                        <td  align="center"><b><%=totalPersonsAssed%></b></td>
                        <td  align="center"><b><%=tobeAssessed%></b></td>
                        <td  align="center"><b><%=orthoCount%></b></td>
                        <td  align="center"><b><%=visualCount%></b></td>
                        <td  align="center"><b><%=hearingCount%></b></td>
                        <td  align="center"><b><%=MRCount%></b></td>
                        <td  align="center"><b><%=MICount%></b></td>
                        <td  align="center"><b><%=MDCount%></b></td>
                        <td  align="center"><b><%=total%></b></td>
                        <td  align="center"><b><%=directRejected%></b></td>
                        <td  align="center"><b><%=assessedAndRejected%></b></td>
                        <td  align="center"><b><%=totalRejected%></b></td>
                    </tr>
                </table>
            </logic:present>
            <logic:present name="mandallist" scope="request">

                <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%">
                  
                    <tr>
                        <th  rowspan="2" align="center">Mandal</th>
                        <th align="left"  rowspan="2">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</th>
                        <th align="left"  rowspan="2">ABH &<br> Not Elg</th>
                        <th align="center"  colspan="4">Deleted</th>
                        <th align="left"  rowspan=2>Total No.<br> of <br>  persons<br> to under<br> go <br> SADAREM<br>(3-8)</th>
                        <th align="left"  rowspan=2>No. for<br> Part-A<br> Entered</th>
                        <th align="left"  colspan="3">Total no of persons Assessed</th>
                        <th align="left"  rowspan="2">Total<br> Assessed<br> (12+13)</th>
                        <th align="left"  rowspan="2">No of<br> persons<br> to be<br> Assessed<br> (9-14)</th>
                        <th align="center"  colspan="7">Certificates Issued</th>
                        <th align="center"  colspan="3">Rejected Status</th>
                    </tr>
                    <tr>
                        <th align="left" >Death</th>
                        <th align="left" >PM</th>
                        <th align="left" >Suspend & <br> TM</th>
                        <th align="left" >Total</th>
                        <th align="left" >Death </th>
                        <th align="left" >Live</th>
                        <th align="left" >ABH ,<br> Not ELG, <br>Suspend ,<br> TM <br> & PM</th>
                        <th align="left" >Ortho</th>
                        <th align="left" >Visual</th>
                        <th align="left" >Hearing</th>
                        <th align="left" >Mental<br>(MR)</th>
                        <th align="left" >Mental<br> Illness</th>
                        <th align="left" >Multiple<br> Disability</th>
                        <th align="left" >Total</th>
                        <th align="center" >Before<br> Assment<br> (No <br> visible<br> disb.)</th>
                        <th align="center" >After<br> Assment<br> (<40%<br> disb.)</th>
                        <th align="left" >Total<br> Rejected</th>
                    </tr>
                    <tr>
                        <th align="center">1</th>
                        <th align="center">2</th>
                        <th align="center">3</th>
                        <th align="center">4</th>
                        <th align="center">5</th>
                        <th align="center">6</th>
                        <th align="center">7</th>
                        <th align="center">8</th>
                        <th align="center">9</th>
                        <th align="center">10</th>
                        <th align="center">11</th>
                        <th align="center">12</th>
                        <th align="center">13</th>
                        <th align="center">14</th>
                        <th align="center">15</th>
                        <th align="center">16</th>
                        <th align="center">17</th>
                        <th align="center">18</th>
                        <th align="center">19</th>
                        <th align="center">20</th>
                        <th align="center">21</th>
                        <th align="center">22</th>
                        <th align="center">23</th>
                        <th align="center">24</th>
                    </tr>
                    <logic:iterate id="modify" name="mandallist" scope="request">
                        <tr>
                            <td  >
                                    <bean:write name="modify" property="mandal"/>
                            </td>
                            <td align="center"><bean:write name="modify" property="existingpensioners"/></td>
                            <td align="center"><bean:write name="modify" property="otherPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="deathPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="pmPensioners"/></td>

                            <td align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="totalDeleted"/></td>
                            <%-- <td align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>

                            <td align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                            <td align="center"><bean:write name="modify" property="partacount"/></td>
                            <td align="center"><bean:write name="modify" property="deathAndPm"/></td>
                            <td align="center"><bean:write name="modify" property="livePensioners"/></td>

                            <%--  <td align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                            <td align="center"><bean:write name="modify" property="assessedOthers"/></td>
                            <td align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                            <td align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                            <td align="center"><bean:write name="modify" property="ortho"/></td>
                            <td align="center"><bean:write name="modify" property="visual"/></td>
                            <td align="center"><bean:write name="modify" property="hearing"/></td>
                            <td align="center"><bean:write name="modify" property="mentalretardation"/></td>
                            <td align="center"><bean:write name="modify" property="mentalillness"/></td>
                            <td align="center"><bean:write name="modify" property="multipledisability"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                            <td align="center"><bean:write name="modify" property="directrejected"/></td>
                            <td align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                            <td align="center"><bean:write name="modify" property="totalrejected"/></td>
                        </tr>
                    </logic:iterate>
                    <tr>

                        <td ><b>Total</b></td>
                        <td  align="center"><b><%=existingpensionerscount%></b></td>
                        <td  align="center"><b><%=otherPensioners%></b></td>
                        <td  align="center"><b><%=deathPensioners%></b></td>
                        <td  align="center"><b><%=pmPensioners%></b></td>
                        <td  align="center"><b><%=susTmPensioners%></b></td>
                        <td  align="center"><b><%=totalDeleted%></b></td>
                        <%-- <td  align="center"><b><%=icfsPensioners%></b></td>--%>

                        <td  align="center"><b><%=underGoSadarem%></b></td>
                        <td  align="center"><b><%=partACount%></b></td>
                        <td  align="center"><b><%=deathAndPm%></b></td>
                        <td  align="center"><b><%=livePensioners%></b></td>
                        <%-- <td  align="center"><b><%=assessedICFS%></b></td>--%>
                        <td  align="center"><b><%=assessedOthers%></b></td>
                        <td  align="center"><b><%=totalPersonsAssed%></b></td>
                        <td  align="center"><b><%=tobeAssessed%></b></td>
                        <td  align="center"><b><%=orthoCount%></b></td>
                        <td  align="center"><b><%=visualCount%></b></td>
                        <td  align="center"><b><%=hearingCount%></b></td>
                        <td  align="center"><b><%=MRCount%></b></td>
                        <td  align="center"><b><%=MICount%></b></td>
                        <td  align="center"><b><%=MDCount%></b></td>
                        <td  align="center"><b><%=total%></b></td>
                        <td  align="center"><b><%=directRejected%></b></td>
                        <td  align="center"><b><%=assessedAndRejected%></b></td>
                        <td  align="center"><b><%=totalRejected%></b></td>
                    </tr>
                </table><br>
            </logic:present>

            <logic:present name="villagelist" scope="request">
             
                <table  align="center" cellspacing="1" border="0" cellpadding="4" class="inputform" width="100%">
                   
                    <tr>
                        <th  rowspan="2" align="center">Village</th>
                        <th align="left"  rowspan="2">Total <br> Pensioners<br>(Live,<br>ABH ,<br>Not ELG,<br>Suspend,<br>Death <br>TM & PM)</th>
                        <th align="left"  rowspan="2">ABH &<br> Not Elg</th>
                        <th align="center"  colspan="4">Deleted</th>
                        <th align="left"  rowspan=2>Total No.<br> of <br>  persons<br> to under<br> go <br> SADAREM<br>(3-8)</th>
                        <th align="left"  rowspan=2>No. for<br> Part-A<br> Entered</th>
                        <th align="left"  colspan="3">Total no of persons Assessed</th>
                        <th align="left"  rowspan="2">Total<br> Assessed<br> (12+13)</th>
                        <th align="left"  rowspan="2">No of<br> persons<br> to be<br> Assessed<br> (9-14)</th>
                        <th align="center"  colspan="7">Certificates Issued</th>
                        <th align="center"  colspan="3">Rejected Status</th>
                    </tr>
                    <tr>
                        <th align="left" >Death</th>
                        <th align="left" >PM</th>
                        <th align="left" >Suspend & <br> TM</th>
                        <th align="left" >Total</th>
                        <th align="left" >Death </th>
                        <th align="left" >Live</th>
                        <th align="left" >ABH ,<br> Not ELG, <br>Suspend ,<br> TM <br> & PM</th>
                        <th align="left" >Ortho</th>
                        <th align="left" >Visual</th>
                        <th align="left" >Hearing</th>
                        <th align="left" >Mental<br>(MR)</th>
                        <th align="left" >Mental<br> Illness</th>
                        <th align="left" >Multiple<br> Disability</th>
                        <th align="left" >Total</th>
                        <th align="center" >Before<br> Assment<br> (No <br> visible<br> disb.)</th>
                        <th align="center" >After<br> Assment<br> (<40%<br> disb.)</th>
                        <th align="left" >Total<br> Rejected</th>
                    </tr>
                    <tr>
                        <th align="center">1</th>
                        <th align="center">2</th>
                        <th align="center">3</th>
                        <th align="center">4</th>
                        <th align="center">5</th>
                        <th align="center">6</th>
                        <th align="center">7</th>
                        <th align="center">8</th>
                        <th align="center">9</th>
                        <th align="center">10</th>
                        <th align="center">11</th>
                        <th align="center">12</th>
                        <th align="center">13</th>
                        <th align="center">14</th>
                        <th align="center">15</th>
                        <th align="center">16</th>
                        <th align="center">17</th>
                        <th align="center">18</th>
                        <th align="center">19</th>
                        <th align="center">20</th>
                        <th align="center">21</th>
                        <th align="center">22</th>
                        <th align="center">23</th>
                        <th align="center">24</th>
                    </tr>
                    <logic:iterate id="modify" name="villagelist" scope="request">
                        <tr>
                            <td  >
                                <bean:write name="modify" property="townVillage"/>

                            </td>
                            <td align="center"><bean:write name="modify" property="existingpensioners"/></td>
                            <td align="center"><bean:write name="modify" property="otherPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="deathPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="pmPensioners"/></td>

                            <td align="center"><bean:write name="modify" property="susTmPensioners"/></td>
                            <td align="center"><bean:write name="modify" property="totalDeleted"/></td>
                            <%-- <td align="center"><bean:write name="modify" property="icfsPensioners"/></td>--%>

                            <td align="center"><bean:write name="modify" property="underGoSadarem"/></td>
                            <td align="center"><bean:write name="modify" property="partacount"/></td>
                            <td align="center"><bean:write name="modify" property="deathAndPm"/></td>
                            <td align="center"><bean:write name="modify" property="livePensioners"/></td>

                            <%--  <td align="center"><bean:write name="modify" property="assessedICFS"/></td>--%>
                            <td align="center"><bean:write name="modify" property="assessedOthers"/></td>
                            <td align="center"><bean:write name="modify" property="totalpersonsassessed"/></td>
                            <td align="center"><bean:write name="modify" property="tobeAssessed"/></td>
                            <td align="center"><bean:write name="modify" property="ortho"/></td>
                            <td align="center"><bean:write name="modify" property="visual"/></td>
                            <td align="center"><bean:write name="modify" property="hearing"/></td>
                            <td align="center"><bean:write name="modify" property="mentalretardation"/></td>
                            <td align="center"><bean:write name="modify" property="mentalillness"/></td>
                            <td align="center"><bean:write name="modify" property="multipledisability"/></td>
                            <td align="center"><bean:write name="modify" property="total"/></td>
                            <td align="center"><bean:write name="modify" property="directrejected"/></td>
                            <td align="center"><bean:write name="modify" property="assessedandrejected"/></td>
                            <td align="center"><bean:write name="modify" property="totalrejected"/></td>
                        </tr>
                    </logic:iterate>
                    <tr>

                        <td ><b>Total</b></td>
                        <td  align="center"><b><%=existingpensionerscount%></b></td>
                        <td  align="center"><b><%=otherPensioners%></b></td>
                        <td  align="center"><b><%=deathPensioners%></b></td>
                        <td  align="center"><b><%=pmPensioners%></b></td>
                        <td  align="center"><b><%=susTmPensioners%></b></td>
                        <td  align="center"><b><%=totalDeleted%></b></td>
                        <%-- <td  align="center"><b><%=icfsPensioners%></b></td>--%>

                        <td  align="center"><b><%=underGoSadarem%></b></td>
                        <td  align="center"><b><%=partACount%></b></td>
                        <td  align="center"><b><%=deathAndPm%></b></td>
                        <td  align="center"><b><%=livePensioners%></b></td>
                        <%-- <td  align="center"><b><%=assessedICFS%></b></td>--%>
                        <td  align="center"><b><%=assessedOthers%></b></td>
                        <td  align="center"><b><%=totalPersonsAssed%></b></td>
                        <td  align="center"><b><%=tobeAssessed%></b></td>
                        <td  align="center"><b><%=orthoCount%></b></td>
                        <td  align="center"><b><%=visualCount%></b></td>
                        <td  align="center"><b><%=hearingCount%></b></td>
                        <td  align="center"><b><%=MRCount%></b></td>
                        <td  align="center"><b><%=MICount%></b></td>
                        <td  align="center"><b><%=MDCount%></b></td>
                        <td  align="center"><b><%=total%></b></td>
                        <td  align="center"><b><%=directRejected%></b></td>
                        <td  align="center"><b><%=assessedAndRejected%></b></td>
                        <td  align="center"><b><%=totalRejected%></b></td>
                    </tr>
                </table><br>
            </logic:present>

        </html:form>
    </body>


</html:html>
