<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<% 
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html>
    <head >

        <LINK REL="stylesheet" href="<%=request.getContextPath()%>/DisabilityUITG/css/sadarem_styles.css">
    </head>
    <body>
         <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
                         <tr>
                            <td valign="top">
                                <table width="50%" border="0" cellspacing="0" cellpadding="0" align="center" style="color:#000;font-size:11px;font-weight:normal;" height="40px">
                                    <tr>
                                        <td width="10%" align="right"><img src="<%=request.getContextPath()%>/DisabilityUITG/images/TG_GOV_LOGO.png"/></td>
                                        <td align="center"><b style="font-size:14px; font-style: normal;">Software For Assessment Of Disabled For Access Rehabilitation and Empowerment</b><br/>
                                            <span style="font-size:12px; font-style: normal;">
                                                <font size="3"><b>Society for Elimination of Rural Poverty</b></font><br/>
                                                Department of Rural Development,Govt.of Telangana
                                            </span>
                                        </td>
                                        <td width="10%" align="right"><img src="<%=request.getContextPath()%>/DisabilityUITG/images/SerpLogo.png"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="center" height="423px">
                                <!-- Content Starts -->
                              <%--  <div style="overflow:auto; width: 1000px;height:423px; vertical-align: top" >--%>
                                    <tiles:insert attribute="body" ignore="true"/>
                              <%--  </div>--%>
                                <!-- Content Endts -->
                            </td>
                        </tr>
                        <tr>
                            <td  valign="bottom" height="49px"><tiles:insert attribute="footer" ignore="true" /></td>
                        </tr>
                    </table>
    </body>

</html>