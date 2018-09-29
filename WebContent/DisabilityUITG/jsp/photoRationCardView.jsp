<%--
    Document   : empReport
    Created on : Jun 27, 2011, 3:33:27 PM
    Author     : 490058
--%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.bf.disability.common.DataBase.DbManager"%>


<%
            String personCode = (String) request.getParameter("sadaremID");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String districtname = (String) session.getAttribute("districtname");
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <style type="text/css">
            .arrowlistmenu{
                width: 185px; /*width of accordion menu*/
            }
            .arrowlistmenu .menuheader{ /*CSS class for menu headers in general (expanding or not!)*/
                                        font: bold 11px Verdana;
                                        color: #828282;
                                        padding-bottom:7px; /*header text is indented 10px*/
                                        cursor: hand;
                                        border-bottom:1px solid #828282;
                                        padding-left:5px;
            }
            .arrowlistmenu .openheader{ /*CSS class to apply to expandable header when it's expanded*/
                                        border-bottom:1px solid #000000;
                                        background-image:url(./DisabilityUITG/images/titlebar-active.png);
                                        padding-bottom:4px;
                                        color:#000000;
            }
            .arrowlistmenu ul{ /*CSS for UL of each sub menu*/
                               list-style-type: none;
                               margin: 0;
                               padding: 0;
                               margin-bottom: 8px; /*bottom spacing between each UL and rest of content*/
            }
            .arrowlistmenu ul li{
                padding-bottom: 2px; /*bottom spacing between menu items*/
            }
            .arrowlistmenu ul li a{
                color: #A70303;
                /*custom bullet list image*/
                display: block;
                padding:0px 0px 0px 5px;
                margin:0px 0px 0px 25px;
                text-decoration: none;
                line-height:20px;
                vertical-align:middle;
                font-weight: normal;
                border-bottom: 1px solid #dadada;
                list-style-image:url(./DisabilityUITG/images/arrowbullet.png);
                font-size: 11px;
            }
            .arrowlistmenu ul li a:visited{
                color: #ssdd33;
            }
            .arrowlistmenu ul li a:hover{ /*hover state CSS*/
                                          color: #ff9900;
                                          background-color:#FFFFFF; height:100%;
            }
        </style>

    </head>
    <body>
    
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
    <html:form  action="/filterPhotoupload"  method="post">
        <table border="0" cellpadding="5" cellspacing="0" width="150px" align="center">
            <tr>
                <td align="center" style="border-style:dotted;background-color:inherit">
                    <%-- <img src="./DisabilityUITG/uploadedphotos/<%=districtname%>/<%=personCode%>.JPG" height="150" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/> --%>
                    <img  align="right" src="<%=request.getContextPath()%>/dispimg.do?action=showsadaremimg&sadaremid=<bean:write name="reportprint"  property="personcode"/>"  width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'"/>
                    <br/>
                    <b><%=personCode%></b>
                </td>
            </tr>
        </table>
    </html:form>
</body>
</html:html>