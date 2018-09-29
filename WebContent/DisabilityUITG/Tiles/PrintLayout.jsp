<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<% 
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html>

    <head >
        <LINK REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css">

    </head>

    <body>

        <table border="0" cellspacing="0" cellpadding="0" align="center" >
            <tr>
                <td width='15px' align='right' style='background-image:url(./DisabilityUITG/images/lft_shadow.png); background-repeat:repeat-y'><img src='./DisabilityUITG/images/noimg.png' /></td>
                <td>
                    <table width="950px"  align="center" border="0" cellspacing="0" cellpadding="0">
                         <tr>
                            <td height="90px">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" style="color:#000;font-size:11px;font-weight:normal;" height="40px">
                                    <tr>
                                        <td align="right"><img src="./DisabilityUITG/images/TG_GOV_LOGO.png"/></td>
                                        <td align="center"><b style="font-size:14px; font-style: normal;font-variant: small-caps;">Software For Assessment Of Disabled For Access Rehabilitation and Empowerment</b><br/>
                                            <span style="font-size:12px; font-style: normal;">
                                                Society for Elimination of Rural Poverty
                                            </span>
                                        </td>
                                       <td align="right"><img src="./DisabilityUITG/images/SerpLogo.png"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" align="center">
                                <!-- Content Starts -->
                                <tiles:insert attribute="body" ignore="true"/>
                                <!-- Content Endts -->
                            </td>
                        </tr>
                        <tr>
                            <td  valign="bottom" height="49px"><tiles:insert attribute="footer" ignore="true" /></td>
                        </tr>
                    </table>
                </td>
                <td width='15px' align='right' style='background-image:url(./DisabilityUITG/images/rgt_shadow.png); background-repeat:repeat-y'><img src='./DisabilityUITG/images/noimg.png' /></td>
            </tr>
        </table>
    </body>

</html>