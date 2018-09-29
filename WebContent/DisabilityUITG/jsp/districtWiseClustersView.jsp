<%--
    Document   :districtWiseClustersView
    Created on :  Oct 9, 2013, 11:10:15 AM
    Author     :728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*,java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <%
                String url = "";
                String district = "";
                String cluster = "";
                String clusterName = "";
                String mandal = "";
                String reportId="";
                Calendar cal = Calendar.getInstance();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                cal.add(Calendar.DATE, -1);
                if (request.getAttribute("districtName") != null) {
                    district = request.getAttribute("districtName").toString();
                }
                if(request.getAttribute("reportId") != null){
                    reportId=request.getAttribute("reportId").toString();
                    }

                if (request.getAttribute("URL") != null) {
                    url = request.getAttribute("URL").toString();
                }


    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript">
           function getBreakup(){
                if(document.forms[0].cluster_name.value=="0"){
                        alert("please select Cluster");
                        document.forms[0].cluster_name.focus();
                        return false;
                    }else{
                        var district=document.forms[0].district_name.value;
                        var cluster=document.forms[0].cluster_name.value;
                        location.href= '<%=url%>.do?mode=districtWiseDistrictBreakup&reportId=<%=reportId%>&district='+district+'&cluster='+cluster;
                    }
           }
        </script>

    </head>
    <body >

    

    <html:form action="/District" >
        <html:hidden property="mode"/>
        <%-- <input type="hidden" name="districtName" id="districtName"/>--%>
        <table height="440px" width="99%" border="0" cellspacing="0" cellpadding="10" align="center">
            <tr>
                <td align="center" bgcolor="#e4f5fd" valign="top">

                    <table border="0" cellspacing="0" cellpadding="0" width="60%" align="center">

                        <tr>
                            <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                            <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center">
                                <%if (url.equals("Mandal")) {%>
                                <strong>Mandal Level Report</strong>
                                <%} else if (url.equals("AreaCluster")) {%>
                                <strong>Cluster Level Report</strong>
                                <%} else {%>
                                <strong>District Level Report(Mandal Wise BreakUp)</strong>
                                <%}%>
                            </td>
                            <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
                        </tr>

                        <tr>
                            <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                            <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">

                                <table  align="center" cellspacing="1" cellpadding="5" width="60%">
                                    
                                    <tr>
                                        <td align="right" class="label" >District<font color="red"><b>*</b></font></td>
                                        <td align="center" >
                                            <html:text property="district_name" readonly="true"/>
                                           
                                        </td>
                                        <td align="right" class="label" >Cluster<font color="red"><b>*</b></font></td>
                                        <td align="center" >
                                            <html:select  property="cluster_name" styleId="2" style="width:180px;height:25px;font-size:11px;" onchange="return getBreakup();">
                                                <html:option value="0">Select</html:option>
                                                <html:optionsCollection   property="clusterList" label="clusterName" value="clusterName"  />
                                            </html:select>
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
                    </table>
                    <br/>
                    <logic:notEmpty name="districtClusterReportList">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                            <tr><td align="center"  style="color: #ff0000" ><b>Indira Kranthi Patham Support to review with disability in Rural Areas
                                        <br>Andhra Pradesh <br>(As on <%=dateFormat.format(cal.getTime())%>)
                                        <br><%=district%> District <%=clusterName%><%=mandal%>Level Data</b></td></tr>
                            <tr>
                        </table>
                        <% request.setAttribute("flag", "1");%>
                        <% request.setAttribute("end", "3");%>
                        <logic:iterate name="districtClusterReportList" id="row">
                            <bean:define id="sno" value="${row.flag}"/>
                            <bean:define id="bgColor" value="${row.color}"/>


                            <logic:equal value="<%=sno.toString()%>" name="flag" scope="request">
                                <br>
                                <table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
                                    <tr><td class="formhdbg" align="left" ><b>${row.Heading}</b></td></tr>
                                    <tr>
                                </table>

                                <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable" >

                                </logic:equal>

                                <logic:notEqual value="<%=sno.toString()%>" name="flag" scope="request">
                                    <logic:notEqual value="<%=sno.toString()%>" name="end" scope="request">
                                        <tr>
                                            <td class="formaltbg" align="left" width="50%" style="background-color:<%=bgColor.toString()%>;">${row.name}</td>
                                            <td class="formaltbg" align="center" width="50%" style="background-color:<%=bgColor.toString()%>;"> ${row.count}</td>
                                        </tr >
                                    </logic:notEqual>


                                    <logic:equal value="<%=sno.toString()%>" name="end" scope="request">
                                        <%--</table>
                                        <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" class="innerTable">
                                        --%>
                                        <tr><td  align="left"  colspan="2" style="background-color:<%=bgColor.toString()%>;">
                                                <a href="#" onclick="window.open('<%=url%>.do?mode=districtWiseDistrictBreakup&reportId=${row.reportId}&district=<%=district%>&cluster=<%=cluster%>')">
                                                    <b>${row.name}</b></a>
                                              <%
                                            if(!district.equals("") && mandal.equals("") && cluster.equals("")){
                                                %>
                                                <br/>
                                                 <a href="#" onclick="window.open('<%=url%>.do?mode=districtWiseDistrictBreakup&reportId=${row.reportId}&district=<%=district%>')">
                                                    <b>Mandal Wise BreakUp</b></a>
                                                <%
                                                }

                                                %>
                                            </td></tr>
                                    </table>

                                </logic:equal>
                            </logic:notEqual>

                        </logic:iterate>
                    </logic:notEmpty>
                    <logic:present name="msg">
                        <center><font color="red" size="2"><b><bean:write name="msg"/></b></font> </center>
                    </logic:present>
                </td>
            </tr>
        </table>

    </html:form>
</body>
</html:html>