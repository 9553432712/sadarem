<%--
    Document   : ClusterReport
    Created on : Apr 18, 2013, 11:06:05 AM
    Author     : 747577
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page import="java.util.*,java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%int i = 1;


            String mName = (String) request.getAttribute("mName");
            String dName = (String) request.getAttribute("dName");
            String cName = (String) request.getAttribute("cName");

%>
<html:html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <script language="javascript" src="./DisabilityUITG/css/jqueryslidemenu.js"></script>
        <script language="javascript" src="./DisabilityUITG/css/jquery.min.js"></script>


        <script>
            var typeOfselection="";
            var reportSelection="";
            
            <%if (request.getAttribute("reportSelection") != null) {%>
                reportSelection='<%=request.getAttribute("reportSelection")%>';
            <%  }%>
            <%if (request.getAttribute("typeOfselection") != null) {%>
                typeOfselection='<%=request.getAttribute("typeOfselection")%>';
            <%  }%>

                function getDistricts(){
                    document.forms[0].mode.value="getDistricts";
                    document.forms[0].submit();
                }
                function getReportTypeOnChange(){
                    typeOfselection="";
                    reportSelection="";
                    hiddenFields();
                    
                }
                function getClusterData(){
                    
                    document.forms[0].typeOfSearch.value=typeOfselection;
                    document.forms[0].reportSelection.value=reportSelection;
                    document.forms[0].mode.value="getCluster";
                    document.forms[0].submit();
            
                }
                function getMandalData(){
                   
                    document.forms[0].typeOfSearch.value=typeOfselection;
                    document.forms[0].reportSelection.value=reportSelection;
                    document.forms[0].mode.value="getMandals";
                    document.forms[0].submit();
                }
               
                function getReportSelection(selected){
                    var selected=selected;
                    document.forms[0].typeOfSearch[0].checked=false;
                    document.forms[0].typeOfSearch[1].checked=false;
                    document.forms[0].typeOfSearch[2].checked=false;
                    if(selected=="district"){
                        document.getElementById("breakUpDistrictID").style.display = "block";
                        document.getElementById("breakUpClusterID").style.display = "block";
                        document.getElementById("breakUpMandalID").style.display = "block";
                        document.getElementById('districtID').style.display="none";
                        document.getElementById("clusterID").style.display = "none";
                        document.getElementById("mandalID").style.display = "none";
                       
                    } else  if(selected=="cluster"){
                        document.getElementById("breakUpDistrictID").style.display = "none";
                        document.getElementById("breakUpClusterID").style.display = "block";
                        document.getElementById("breakUpMandalID").style.display = "block";
                        document.getElementById('districtID').style.display="none";
                        document.getElementById("clusterID").style.display = "none";
                        document.getElementById("mandalID").style.display = "none";
                       
                    } else  if(selected=="mandal"){
                        document.getElementById("breakUpDistrictID").style.display = "none";
                        document.getElementById("breakUpClusterID").style.display = "none";
                        document.getElementById("breakUpMandalID").style.display = "none";
                        document.getElementById('districtID').style.display="block";
                        document.getElementById("clusterID").style.display = "block";
                        document.getElementById("mandalID").style.display = "block";
                        document.forms[0].district_name.value="ALL";
                        document.forms[0].cluster_name.value="ALL";
                        document.forms[0].mandal_name.value="ALL";
                        
                    }
                    reportSelection=selected;
                }
                function changeSelection(selected){
                    var upload=selected;
                    var slcBx4 = document.getElementById("4");
                    if(upload == "districtBreakup"){
                           
                        document.getElementById('districtID').style.display="block";
                        document.getElementById("clusterID").style.display = "none";
                        document.getElementById("mandalID").style.display = "none";
                        document.forms[0].district_name.value="ALL";
                        
                        typeOfselection="districtBreakup";
                    }
                    else if(upload == "clusterBreakup"){
                       
                        document.getElementById('districtID').style.display="block";
                        document.getElementById("clusterID").style.display = "block";
                        document.getElementById("mandalID").style.display = "none";
                       
                        document.forms[0].district_name.value="ALL";
                        document.forms[0].cluster_name.value="ALL";
                        
                        typeOfselection="clusterBreakup";
                    }
                    else if(upload == "mandalBreakup"){
                        document.forms[0].district_name.value="ALL";
                        document.forms[0].cluster_name.value="ALL";
                        document.forms[0].mandal_name.value="ALL";
                        document.getElementById('districtID').style.display="block";
                        document.getElementById("clusterID").style.display = "block";
                        document.getElementById("mandalID").style.display = "block";
                        typeOfselection="mandalBreakup";
                    }

                }
                function getclusterReport(){
                    var flag=true;
                    if(document.getElementById('1').checked){
                        reportSelection="district";

                    }else if(document.getElementById('2').checked){
                        reportSelection="cluster";

                    }else if(document.getElementById('3').checked){
                        reportSelection="mandal";

                    }
                    if(document.getElementById('7').checked){
                        typeOfselection="districtBreakup";

                    }else if(document.getElementById('8').checked){
                        typeOfselection="clusterBreakup";

                    }else if(document.getElementById('9').checked){
                        typeOfselection="mandalBreakup";

                    }
                    
                    if(document.forms[0].reportSelection[0].checked || document.forms[0].reportSelection[1].checked || document.forms[0].reportSelection[2].checked){
                        flag=true;
                    }  else{
                        flag=false;
                        alert("Please select District/ Cluster/ Mandal !")
                        return false;
                    }
                    if(reportSelection!=""){
                        if(document.getElementById('7').checked || document.getElementById('8').checked || document.getElementById('9').checked)
                        {
                            flag=true;
                            if(typeOfselection=="districtBreakup"){
                                flag=true;
                            }else if(typeOfselection=="clusterBreakup"){
                                if(document.forms[0].district_name.value=="ALL")
                                {
                                    flag=false;
                                    alert("Please select any District other than ALL")
                                    return false;
                                }
                                if(reportSelection=="district" && document.forms[0].cluster_name.value=="ALL")
                                {
                                    flag=false;
                                    alert("Please select Cluster other than ALL")
                                    return false;
                                }
                            }else if(typeOfselection=="mandalBreakup"){
                                if(document.forms[0].district_name.value=="ALL")
                                {
                                    flag=false;
                                    alert("Please select District other than ALL")
                                    return false;
                                }
                                if(document.forms[0].cluster_name.value=="ALL")
                                {
                                    flag=false;
                                    alert("Please select Cluster other than ALL")
                                    return false;
                                }
                                if(reportSelection=="district" && document.forms[0].mandal_name.value=="ALL")
                                {
                                    flag=false;
                                    alert("Please select Mandal other than ALL")
                                    return false;
                                }
                            }
                        } else{
                            flag=false;
                            alert("Please select District/ Cluster/ Mandal BreakUp?")
                            return false;
                        }
                    }
                    if(flag){
                        document.forms[0].reportSelection.value=reportSelection;
                        if(reportSelection!=""){
                            document.forms[0].typeOfSearch.value=typeOfselection;
                        }
                        document.forms[0].mode.value="getClusterReport";
                        document.forms[0].submit();
                    }

                }


                function hiddenFields(){
                    
                    if(reportSelection==""){
                        document.getElementById("breakUpDistrictID").style.display = "none";
                        document.getElementById("breakUpClusterID").style.display = "none";
                        document.getElementById("breakUpMandalID").style.display = "none";
                        document.forms[0].reportSelection[0].checked=false;
                        document.forms[0].reportSelection[1].checked=false;
                        document.forms[0].reportSelection[2].checked=false;
                        document.getElementById('districtID').style.display="none";
                        document.getElementById("clusterID").style.display = "none";
                        document.getElementById("mandalID").style.display = "none";
                    } else{
                        if(reportSelection=="district"){
                            document.getElementById('breakUpDistrictID').style.display="block";
                            document.getElementById("breakUpClusterID").style.display = "block";
                            document.getElementById("breakUpMandalID").style.display = "block";
                        } else if(reportSelection=="cluster"){
                            document.getElementById('breakUpDistrictID').style.display="none";
                            document.getElementById("breakUpClusterID").style.display = "block";
                            document.getElementById("breakUpMandalID").style.display = "block";
                        }else if(reportSelection=="mandal"){
                            document.getElementById("breakUpDistrictID").style.display = "none";
                            document.getElementById("breakUpClusterID").style.display = "none";
                            document.getElementById("breakUpMandalID").style.display = "none";
                        }
                        

                        if(typeOfselection == "districtBreakup"){
                            document.getElementById('districtID').style.display="block";
                            document.getElementById("clusterID").style.display = "none";
                            document.getElementById("mandalID").style.display = "none";                     
                        
                            typeOfselection="districtBreakup";
                            document.getElementById('7').checked=true;                        
                        }
                        else if(typeOfselection == "clusterBreakup"){                     
                        
                            document.getElementById('districtID').style.display="block";
                            document.getElementById("clusterID").style.display = "block";
                            document.getElementById("mandalID").style.display = "none";
                       
                            typeOfselection="clusterBreakup";
                            document.getElementById('8').checked=true;                       
                        }
                        else if(typeOfselection == "mandalBreakup" || reportSelection=="mandal"){
                       
                            document.getElementById('districtID').style.display="block";
                            document.getElementById("clusterID").style.display = "block";
                            document.getElementById("mandalID").style.display = "block";
                        
                            typeOfselection="mandalBreakup";
                            document.getElementById('9').checked=true;
                        }
                        else if(typeOfselection == ""){
                     
                            document.getElementById('districtID').style.display="none";
                            document.getElementById("clusterID").style.display = "none";
                            document.getElementById("mandalID").style.display = "none";
                            document.forms[0].typeOfSearch[0].checked=false;
                            document.forms[0].typeOfSearch[1].checked=false;
                            document.forms[0].typeOfSearch[2].checked=false;
                            typeOfselection="";
                        }
                    }
                }

                

        </script>
    </head>

    <body onload="hiddenFields();">

    
    <html:form action="/clusterReport" >
        <html:hidden property="mode"/>

        <table height="440px" width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td align="center" bgcolor="#e4f5fd" valign="top">
                    <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
                        <tr>
                            <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                            <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center"><strong>Cluster Wise Report</strong></td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                        </tr>
                        <tr>
                            <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                <table border="0" align="center" cellspacing="1" cellpadding="5" width="100%">

                                    <tr>
                                        <td class="label" valign="middle" width="10%">Report Type</td>
                                        <td align="left" valign="middle" colspan="2">
                                            <html:select styleId="1" property="reportType"  style="height:25px;" onchange="getReportTypeOnChange()">
                                                <html:option  value="ALL">--ALL--</html:option>
                                                <html:optionsCollection property="reportList" label="reportname" value="reportid"  />

                                            </html:select>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label" width="20%">
                                            <html:radio property="reportSelection" styleId="1" value="district" onclick="getReportSelection(this.value);" style="border:0">District</html:radio>
                                        </td>
                                        <td class="label" width="20%">
                                            <html:radio property="reportSelection" styleId="2" value="cluster" onclick="getReportSelection(this.value);" style="border:0">Cluster</html:radio>
                                        </td>

                                        <td class="label" width="20%">
                                            <html:radio property="reportSelection" styleId="3" value="mandal" onclick="getReportSelection(this.value);" style="border:0">Mandal</html:radio>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td id="breakUpDistrictID" style="display: none;" width="20%" colspan="3">
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >
                                                <tr><td class="label" width="20%">
                                                        <html:radio property="typeOfSearch" styleId="7" value="districtBreakup" onclick="changeSelection(this.value);" style="border:0">District BreakUp?</html:radio>
                                                    </td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="breakUpClusterID" style="display: none;" width="20%" colspan="3">
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >

                                                <tr><td class="label" width="20%">
                                                        <html:radio property="typeOfSearch" styleId="8" value="clusterBreakup" onclick="changeSelection(this.value);" style="border:0">Cluster BreakUp?</html:radio>
                                                    </td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="breakUpMandalID" style="display: none;" width="20%" colspan="3">
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >

                                                <tr><td class="label" width="20%">
                                                        <html:radio property="typeOfSearch" styleId="9" value="mandalBreakup" onclick="changeSelection(this.value);" style="border:0">Mandal BreakUp?</html:radio>
                                                    </td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <%-- <tr>
                                     <div id="confirmBox"  style="display: none;">
                                         <div class="message"></div>
                                         <span class="yes" type="button">Yes</span>
                                         <span class="no" type="button">No</span>
                                     </div>--%>



                                    <%-- </tr>--%>
                                    <tr>
                                        <%--<div id="districtID" style="visibility:hidden;display:none">--%>
                                        <td id="districtID" style="display: none;" width="40%" >
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >
                                                <tr>
                                                    <td class="label" valign="middle" width="10%">District</td>
                                                    <td align="left" valign="middle">
                                                        <html:select styleId="4" property="district_name" onchange="getClusterData()" style="height:25px;">
                                                            <html:option  value="ALL">--ALL--</html:option>
                                                            <html:optionsCollection property="districtList" label="districtName" value="districtName"  />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <%-- </div>--%>
                                        <%--<div id="clusterID" style="visibility:hidden;display:none">--%>
                                        <td id="clusterID" style="display: none;" width="30%" >
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >
                                                <tr>
                                                    <td class="label" valign="middle" width="10%">Cluster</td>
                                                    <td align="left" valign="middle">
                                                        <html:select styleId="5" property="cluster_name" style="height:25px;"  onchange="getMandalData()">
                                                            <html:option  value="ALL">--ALL--</html:option>
                                                            <html:optionsCollection property="clusterList" label="clusterName" value="clusterName"  />
                                                        </html:select>

                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <%-- </div>
                                         <div id="mandalID" style="visibility:hidden;display:none">--%>
                                        <td id="mandalID" style="display: none;" width="30%">
                                            <table  align="center" cellspacing="1" cellpadding="5" width="100%" border="0" >
                                                <tr>
                                                    <td class="label" valign="middle" width="10%">Mandal</td>
                                                    <td align="left" valign="middle">
                                                        <html:select styleId="6" property="mandal_name" style="height:25px;" >
                                                            <html:option  value="ALL">--ALL--</html:option>
                                                            <html:optionsCollection   property="mandalList" label="mandalName" value="mandalName"  />
                                                        </html:select>

                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>

                                    <%--</div>--%>
                                    <tr>
                                        <td align="center" colspan="3">
                                            <html:button property="but" value="Get Report" onclick="getclusterReport()" />
                                        </td>
                                    </tr>


                                </table>
                            </td>
                            <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
                            <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
                        </tr>
                    </table><br><br>

                    <logic:present name="nodata">
                        <center><font color="red" size="3"><b><bean:write name="nodata"/></b></font></center>
                    </logic:present>
                    <logic:present name="data">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="center" colspan="26"> Report for District: <%=dName%>, Cluster: <%=cName%>, Mandal: <%=mName%></td></tr>
                            <tr>

                        </table>
                    </logic:present>
                    <logic:notEmpty name="clusterReportListOne">

                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">Profile: Over All SADAREM Assessed </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>
                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%--<td class="formhdbg" align="center">
                                    Cluster Phase
                                </td>--%>
                                <td class="formhdbg" align="center">
                                    Sadarem Assessed
                                </td>
                                <td class="formhdbg" align="center">
                                    Eligible
                                </td>
                                <%--<td class="formhdbg" align="center" width="120px;">
                                    Before Assment (No visible disb.)
                                </td>
                                <td class="formhdbg" align="center" width="120px;">
                                    After Assment (<40% disb.)
                                </td>--%>


                            </tr>
                            <logic:iterate name="clusterReportListOne" id="row">
                                <tr>

                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">
                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                        <%--<td class="formaltbg" align="left">
                                            ${row.phase}
                                        </td>--%>
                                    </logic:notEqual>
                                    <td class="formaltbg" align="center">
                                        ${row.totalassessed}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.eligible}
                                    </td>
                                    <%-- <td class="formaltbg" align="center">
                                         ${row.rejected}
                                     </td>

                                    <td class="formaltbg" align="center">
                                        ${row.notrejected}
                                    </td>--%>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <%--</div>--%>
                        <br>

                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListTwo">


                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">Profile: SADAREM</td></tr>
                            <tr>

                        </table>
                        <div style="overflow:auto;width:90%">
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                <tr>
                                    <td class="formhdbg" align="center">
                                        District
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Cluster
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Mandal
                                    </td>
                                    <%--<td class="formhdbg" align="center">
                                        Cluster Phase
                                    </td>--%>
                                    <td class="formhdbg" align="center">
                                        ST
                                    </td>
                                    <td class="formhdbg" align="center">
                                        SC
                                    </td>
                                    <td class="formhdbg" align="center">
                                        BC
                                    </td>

                                    <td class="formhdbg" align="center">
                                        OC
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Minority
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Age (Below 18)
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Age (Above 18)
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Male
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Female
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Illiterate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Below 10th
                                    </td>

                                    <td class="formhdbg" align="center">
                                        Class 10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Intermediate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Diploma
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Graduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Postgraduate
                                    </td>
                                </tr>
                                <% i = 1;%>
                                <logic:iterate name="clusterReportListTwo" id="row">
                                    <tr>
                                        <bean:define id="sno" value="${row.district}"/>
                                        <bean:define id="cno" value="${row.cluster}"/>
                                        <% request.setAttribute("total", "Total");%>
                                        <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                            <td class="formaltbg" align="center" colspan="2"><b>
                                                    ${row.district}</b>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                            <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                    ${row.district}
                                                </td>
                                            </logic:notEqual>
                                            <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                    ${row.cluster}
                                                </td>
                                            </logic:notEqual>
                                            <td class="formaltbg" align="left">
                                                ${row.mandal}
                                            </td>
                                        </logic:notEqual>
                                        <%--<td class="formaltbg" align="left">
                                            ${row.phase}
                                        </td>--%>

                                        <td class="formaltbg" align="center">
                                            ${row.st}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.sc}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.bc}
                                        </td>

                                        <td class="formaltbg" align="center">
                                            ${row.oc}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.minority}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.agebelow18}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.ageabove18}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.male}
                                        </td>

                                        <td class="formaltbg" align="center">
                                            ${row.female}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.illiterate}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.below10th}
                                        </td>

                                        <td class="formaltbg" align="center">
                                            ${row.class10th}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.intermediate}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.diploma}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.graduate}
                                        </td>
                                        <td class="formaltbg" align="center">
                                            ${row.postgraduate}
                                        </td>
                                        <% request.setAttribute("dist", sno);%>
                                        <% request.setAttribute("cls", cno);%>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </div>
                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListThree">


                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">Profile: Surgical Corrections  </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>

                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%-- <td class="formhdbg" align="center">
                                     Cluster Phase
                                 </td>--%>
                                <td class="formhdbg" align="center">
                                    Required
                                </td>
                                <td class="formhdbg" align="center">
                                    Evolution
                                </td>
                                <td class="formhdbg" align="center">
                                    Phsiotherapy
                                </td>

                                <td class="formhdbg" align="center">
                                    Completed
                                </td>

                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListThree" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <%-- <td class="formaltbg" align="left">
                                         ${row.phase}
                                     </td>--%>

                                    <td class="formaltbg" align="center">
                                        ${row.required}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.evolution}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.Phsiotherapy}
                                    </td>

                                    <td class="formaltbg" align="lecenterft">
                                        ${row.completed}
                                    </td>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <%--</div>--%>

                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListFour">


                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">Profile: Surgical Aid & Appliances </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>
                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%-- <td class="formhdbg" align="center">
                                     Cluster Phase
                                 </td>--%>
                                <td class="formhdbg" align="center">
                                    Advised
                                </td>
                                <td class="formhdbg" align="center">
                                    Distribution
                                </td>

                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListFour" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <%-- <td class="formaltbg" align="left">
                                         ${row.phase}
                                     </td>--%>

                                    <td class="formaltbg" align="center">
                                        ${row.adviced}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.distribution}
                                    </td>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <%--</div>--%>

                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListFive">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: JBY Policies
                                </td>
                            </tr>
                        </table>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>
                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <td class="formhdbg" align="center">
                                    JBY Policies
                                </td>
                            </tr>
                            <logic:iterate name="clusterReportListFive" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <td class="formaltbg" align="center">
                                        ${row.count1}
                                    </td>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListSix">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: SADAREM Grievances
                                </td>
                            </tr>
                        </table>

                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>
                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <td class="formhdbg" align="center" width="5%">
                                    Closed
                                </td>
                                <td class="formhdbg" align="center" width="5%">
                                    Pending
                                </td>
                                <td class="formhdbg" align="center" width="5%">
                                    Closed/Pending with Camp Incharge
                                </td>
                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListSix" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <td class="formaltbg" align="center">
                                        ${row.closed}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.pending}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.clopencamp}
                                    </td>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListSeven">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: Scholarships
                                </td>
                            </tr>
                        </table>

                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>
                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <td class="formhdbg" align="center">
                                    Scholarships
                                </td>
                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListSeven" id="row">
                                <bean:define id="sno" value="${row.district}"/>
                                <bean:define id="cno" value="${row.cluster}"/>

                                <tr>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <td class="formaltbg" align="center">
                                        ${row.count}
                                    </td>
                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <br>
                    </logic:notEmpty>

                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListEight">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: TSC Male
                                </td>
                            </tr>
                        </table>
                        <div style="overflow:auto;width:90%">
                            <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                <tr>
                                    <td class="formhdbg" align="center">
                                        District
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Cluster
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Mandal
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Locomotor
                                    </td>
                                    <td class="formhdbg" align="center">
                                        VisualImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        HearingImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalRetardation
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalIllness
                                    </td>

                                    <td class="formhdbg" align="center">
                                        MultipleDisability
                                    </td>
                                    <td class="formhdbg" align="center">
                                        lliterate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Below10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Class10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Intermediate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Diploma
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Graduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Postgraduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Married
                                    </td>
                                    <td class="formhdbg" align="center">
                                        UnMarried
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Divorcee
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widow
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widower
                                    </td>
                                </tr>
                                <% i = 1;%>
                                <logic:iterate name="clusterReportListEight" id="row">
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>

                                    <tr>
                                        <% request.setAttribute("total", "Total");%>
                                        <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                            <td class="formaltbg" align="center" colspan="2"><b>
                                                    ${row.district}</b>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                            <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                    ${row.district}
                                                </td>
                                            </logic:notEqual>
                                            <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                    ${row.cluster}
                                                </td>
                                            </logic:notEqual>
                                            <td class="formaltbg" align="left">
                                                ${row.mandal}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="center"> ${row.Locomotor}</td>
                                        <td class="formaltbg" align="center"> ${row.VisualImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.HearingImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalRetardation}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalIllness}</td>
                                        <td class="formaltbg" align="center"> ${row.MultipleDisability}</td>
                                        <td class="formaltbg" align="center"> ${row.lliterate}</td>
                                        <td class="formaltbg" align="center"> ${row.Below10th}</td>
                                        <td class="formaltbg" align="center"> ${row.Class10th}</td>
                                        <td class="formaltbg" align="center"> ${row.intermediate}</td>
                                        <td class="formaltbg" align="center"> ${row.Diploma}</td>
                                        <td class="formaltbg" align="center"> ${row.Graduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Postgraduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Married}</td>
                                        <td class="formaltbg" align="center"> ${row.UnMarried}</td>
                                        <td class="formaltbg" align="center"> ${row.Divorcee}</td>
                                        <td class="formaltbg" align="center"> ${row.Widow}</td>
                                        <td class="formaltbg" align="center"> ${row.Widower}</td>
                                        <% request.setAttribute("dist", sno);%>
                                        <% request.setAttribute("cls", cno);%>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </div>
                        <br>
                    </logic:notEmpty>

                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListNine">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: TSC Female
                                </td>
                            </tr>
                        </table>
                        <div style="overflow:auto;width:90%">
                            <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                <tr>
                                    <td class="formhdbg" align="center">
                                        District
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Cluster
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Mandal
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Locomotor
                                    </td>
                                    <td class="formhdbg" align="center">
                                        VisualImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        HearingImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalRetardation
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalIllness
                                    </td>

                                    <td class="formhdbg" align="center">
                                        MultipleDisability
                                    </td>
                                    <td class="formhdbg" align="center">
                                        lliterate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Below10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Class10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Intermediate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Diploma
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Graduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Postgraduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Married
                                    </td>
                                    <td class="formhdbg" align="center">
                                        UnMarried
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Divorcee
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widow
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widower
                                    </td>
                                </tr>
                                <% i = 1;%>
                                <logic:iterate name="clusterReportListNine" id="row">
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>

                                    <tr>
                                        <% request.setAttribute("total", "Total");%>
                                        <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                            <td class="formaltbg" align="center" colspan="2"><b>
                                                    ${row.district}</b>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                            <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                    ${row.district}
                                                </td>
                                            </logic:notEqual>
                                            <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                    ${row.cluster}
                                                </td>
                                            </logic:notEqual>
                                            <td class="formaltbg" align="left">${row.mandal}</td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="center"> ${row.Locomotor}</td>
                                        <td class="formaltbg" align="center"> ${row.VisualImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.HearingImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalRetardation}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalIllness}</td>
                                        <td class="formaltbg" align="center"> ${row.MultipleDisability}</td>
                                        <td class="formaltbg" align="center"> ${row.lliterate}</td>
                                        <td class="formaltbg" align="center"> ${row.Below10th}</td>
                                        <td class="formaltbg" align="center"> ${row.Class10th}</td>
                                        <td class="formaltbg" align="center"> ${row.intermediate}</td>
                                        <td class="formaltbg" align="center"> ${row.Diploma}</td>
                                        <td class="formaltbg" align="center"> ${row.Graduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Postgraduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Married}</td>
                                        <td class="formaltbg" align="center"> ${row.UnMarried}</td>
                                        <td class="formaltbg" align="center"> ${row.Divorcee}</td>
                                        <td class="formaltbg" align="center"> ${row.Widow}</td>
                                        <td class="formaltbg" align="center"> ${row.Widower}</td>
                                        <% request.setAttribute("dist", sno);%>
                                        <% request.setAttribute("cls", cno);%>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </div>
                        <br>
                    </logic:notEmpty>

                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListTen">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: TSP Male
                                </td>
                            </tr>
                        </table>
                        <div style="overflow:auto;width:90%">
                            <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                <tr>
                                    <td class="formhdbg" align="center">
                                        District
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Cluster
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Mandal
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Locomotor
                                    </td>
                                    <td class="formhdbg" align="center">
                                        VisualImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        HearingImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalRetardation
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalIllness
                                    </td>

                                    <td class="formhdbg" align="center">
                                        MultipleDisability
                                    </td>
                                    <td class="formhdbg" align="center">
                                        lliterate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Below10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Class10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Intermediate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Diploma
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Graduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Postgraduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Married
                                    </td>
                                    <td class="formhdbg" align="center">
                                        UnMarried
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Divorcee
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widow
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widower
                                    </td>
                                </tr>
                                <% i = 1;%>
                                <logic:iterate name="clusterReportListTen" id="row">
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>

                                    <tr>
                                        <% request.setAttribute("total", "Total");%>
                                        <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                            <td class="formaltbg" align="center" colspan="2"><b>
                                                    ${row.district}</b>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                            <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                    ${row.district}
                                                </td>
                                            </logic:notEqual>
                                            <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                    ${row.cluster}
                                                </td>
                                            </logic:notEqual>
                                            <td class="formaltbg" align="left">
                                                ${row.mandal}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="center"> ${row.Locomotor}</td>
                                        <td class="formaltbg" align="center"> ${row.VisualImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.HearingImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalRetardation}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalIllness}</td>
                                        <td class="formaltbg" align="center"> ${row.MultipleDisability}</td>
                                        <td class="formaltbg" align="center"> ${row.lliterate}</td>
                                        <td class="formaltbg" align="center"> ${row.Below10th}</td>
                                        <td class="formaltbg" align="center"> ${row.Class10th}</td>
                                        <td class="formaltbg" align="center"> ${row.intermediate}</td>
                                        <td class="formaltbg" align="center"> ${row.Diploma}</td>
                                        <td class="formaltbg" align="center"> ${row.Graduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Postgraduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Married}</td>
                                        <td class="formaltbg" align="center"> ${row.UnMarried}</td>
                                        <td class="formaltbg" align="center"> ${row.Divorcee}</td>
                                        <td class="formaltbg" align="center"> ${row.Widow}</td>
                                        <td class="formaltbg" align="center"> ${row.Widower}</td>
                                        <% request.setAttribute("dist", sno);%>
                                        <% request.setAttribute("cls", cno);%>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </div>
                        <br>
                    </logic:notEmpty>

                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>
                    <logic:notEmpty name="clusterReportListEleven">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr>
                                <td class="formhdbg" align="left" colspan="26">
                                    ReportType: TSP Female
                                </td>
                            </tr>
                        </table>
                        <div style="overflow:auto;width:90%">
                            <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                <tr>
                                    <td class="formhdbg" align="center">
                                        District
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Cluster
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Mandal
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Locomotor
                                    </td>
                                    <td class="formhdbg" align="center">
                                        VisualImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        HearingImpairment
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalRetardation
                                    </td>
                                    <td class="formhdbg" align="center">
                                        MentalIllness
                                    </td>

                                    <td class="formhdbg" align="center">
                                        MultipleDisability
                                    </td>
                                    <td class="formhdbg" align="center">
                                        lliterate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Below10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Class10th
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Intermediate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Diploma
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Graduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Postgraduate
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Married
                                    </td>
                                    <td class="formhdbg" align="center">
                                        UnMarried
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Divorcee
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widow
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Widower
                                    </td>
                                </tr>
                                <% i = 1;%>
                                <logic:iterate name="clusterReportListEleven" id="row">
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>

                                    <tr>
                                        <% request.setAttribute("total", "Total");%>
                                        <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                            <td class="formaltbg" align="center" colspan="2"><b>
                                                    ${row.district}</b>
                                            </td>
                                        </logic:equal>
                                        <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                            <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                    ${row.district}
                                                </td>
                                            </logic:notEqual>
                                            <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                                <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                    ${row.cluster}
                                                </td>
                                            </logic:notEqual>
                                            <td class="formaltbg" align="left">
                                                ${row.mandal}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="center"> ${row.Locomotor}</td>
                                        <td class="formaltbg" align="center"> ${row.VisualImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.HearingImpairment}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalRetardation}</td>
                                        <td class="formaltbg" align="center"> ${row.MentalIllness}</td>
                                        <td class="formaltbg" align="center"> ${row.MultipleDisability}</td>
                                        <td class="formaltbg" align="center"> ${row.lliterate}</td>
                                        <td class="formaltbg" align="center"> ${row.Below10th}</td>
                                        <td class="formaltbg" align="center"> ${row.Class10th}</td>
                                        <td class="formaltbg" align="center"> ${row.intermediate}</td>
                                        <td class="formaltbg" align="center"> ${row.Diploma}</td>
                                        <td class="formaltbg" align="center"> ${row.Graduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Postgraduate}</td>
                                        <td class="formaltbg" align="center"> ${row.Married}</td>
                                        <td class="formaltbg" align="center"> ${row.UnMarried}</td>
                                        <td class="formaltbg" align="center"> ${row.Divorcee}</td>
                                        <td class="formaltbg" align="center"> ${row.Widow}</td>
                                        <td class="formaltbg" align="center"> ${row.Widower}</td>
                                        <% request.setAttribute("dist", sno);%>
                                        <% request.setAttribute("cls", cno);%>
                                    </tr>
                                </logic:iterate>
                            </table>
                        </div>
                        <br>
                    </logic:notEmpty>




                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListTwelve">


                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">ReportType: PWD SHG's (MIS)  </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>

                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%-- <td class="formhdbg" align="center">
                                     Cluster Phase
                                 </td>--%>
                                <td class="formhdbg" align="center">
                                    Pwdshgs Count
                                </td>


                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListTwelve" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <%-- <td class="formaltbg" align="left">
                                         ${row.phase}
                                     </td>--%>

                                    <td class="formaltbg" align="center">
                                        ${row.Pwdshgs}
                                    </td>

                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                        <%--</div>--%>

                        <br>
                    </logic:notEmpty>

                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListThirteen">


                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">ReportType: Bank Linkages  </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>

                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%-- <td class="formhdbg" align="center">
                                     Cluster Phase
                                 </td>--%>
                                <td class="formhdbg" align="center">
                                    No of loans
                                </td>
                                <td class="formhdbg" align="center">
                                    Amounts
                                </td>


                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListThirteen" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <%-- <td class="formaltbg" align="left">
                                         ${row.phase}
                                     </td>--%>

                                    <td class="formaltbg" align="center">
                                        ${row.noofloans}
                                    </td>

                                    <td class="formaltbg" align="center">
                                        ${row.amount}
                                    </td>

                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>
                        </table>

                        <%--</div>--%>

                        <br>
                    </logic:notEmpty>
                    <% request.setAttribute("dist", null);%>
                    <% request.setAttribute("cls", null);%>

                    <logic:notEmpty name="clusterReportListFourteen">                      
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td class="formhdbg" align="left" colspan="26">ReportType: Pension Data  </td></tr>
                            <tr>

                        </table>

                        <%-- <div style="overflow:auto;height:700px">--%>
                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                            <tr>

                                <td class="formhdbg" align="center">
                                    District
                                </td>
                                <td class="formhdbg" align="center">
                                    Cluster
                                </td>
                                <td class="formhdbg" align="center">
                                    Mandal
                                </td>
                                <%-- <td class="formhdbg" align="center">
                                     Cluster Phase
                                 </td>--%>
                                <td class="formhdbg" align="center">
                                    PhaseI
                                </td>
                                <td class="formhdbg" align="center">
                                    PhaseII
                                </td>
                                <td class="formhdbg" align="center">
                                    PhaseIV
                                </td>
                            </tr>
                            <% i = 1;%>
                            <logic:iterate name="clusterReportListFourteen" id="row">
                                <tr>
                                    <bean:define id="sno" value="${row.district}"/>
                                    <bean:define id="cno" value="${row.cluster}"/>
                                    <% request.setAttribute("total", "Total");%>
                                    <logic:equal value="<%=sno.toString()%>" name="total" scope="request">
                                        <td class="formaltbg" align="center" colspan="2"><b>
                                                ${row.district}</b>
                                        </td>
                                    </logic:equal>
                                    <logic:notEqual value="<%=sno.toString()%>" name="total" scope="request">

                                        <logic:notEqual value="<%=sno.toString()%>" name="dist" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.dcount}">
                                                ${row.district}
                                            </td>
                                        </logic:notEqual>
                                        <logic:notEqual value="<%=cno.toString()%>" name="cls" scope="request">
                                            <td class="formaltbg" align="center" rowspan="${row.ccount}">
                                                ${row.cluster}
                                            </td>
                                        </logic:notEqual>
                                        <td class="formaltbg" align="left">
                                            ${row.mandal}
                                        </td>
                                    </logic:notEqual>
                                    <td class="formaltbg" align="center">
                                        ${row.Phaseonepensiondata}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.phasetwopensiondata}
                                    </td>
                                    <td class="formaltbg" align="center">
                                        ${row.phasefourpensiondata}
                                    </td>

                                    <%-- <td class="formaltbg" align="left">
                                         ${row.phase}
                                     </td>--%>


                                    <% request.setAttribute("dist", sno);%>
                                    <% request.setAttribute("cls", cno);%>
                                </tr>
                            </logic:iterate>

                        </table>
                        <%--</div>--%>

                        <br>
                    </logic:notEmpty>



                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>
