<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>

    <head >
        <%
                    HttpSession sessionForCheck = request.getSession(false);
 
        			String  reLoginURL=request.getContextPath()+"/login.do?mode=sessionvalidator";
                    if (session.getAttribute("services") == null) {
                        response.sendRedirect(reLoginURL);
                        return;
                    }
        %>
        <LINK REL="stylesheet" href="./DisabilityUITG/css/sadarem_styles.css">
       

    </head>
 
        <body>

            <table border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td width='15px' align='right' style='background-image:url(./DisabilityUITG/images/lft_shadow.png); background-repeat:repeat-y'><img src='./DisabilityUITG/images/noimg.png' /></td>
                    <td><table width="950px"  border="0" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td  valign="top" width="950px"><tiles:insert attribute="header" ignore="true"/></td>
                            </tr>

                             <tr>
                                <td  valign="top"  width="950px"><tiles:insert attribute="menu"  ignore="true"/></td>
                            </tr>
                            <tr>
                                <td  valign="top"  height="540px">
                                    <!-- Content Starts -->
                                    <tiles:insert attribute="date" ignore="true"/>
                                    <!-- Content Endts -->
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