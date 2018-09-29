<%--
    Document   : RachabandaPersonalDetails
    Created on : Oct 28, 2011, 10:20:09 AM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.ArrayList" %>
<%int i = 1;%>
<%int j = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">       
        <link rel="stylesheet" href="./DisabilityUITG/css/jqueryslidemenu.css" type="text/css">


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



        <script>
            function getDetails() {
                document.forms[0].mode.value="getDetails";
                document.forms[0].submit();
            }
        </script>

    </head>
    <body>

    

    <html:form action="/rachaBandaPersonal.do" >
        <html:hidden property="mode"/>
        <logic:present name="msg">
            <table align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <font color="red" size="2"><b><bean:write name="msg"/></b></font>
                    </td>
                </tr>
            </table>
        </logic:present>

        <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center" >
            <tr>
                <td><img src="./DisabilityUITG/images/srch-lft-top-img.png" width="11" height="28" /></td>
                <td width="99%" background="./DisabilityUITG/images/srch-top-bg.png" class="label" align="center"><strong><b>RACHEBANDA ASSESSMENT PERSONAL DETAILS</b></strong></td>
                <td><img src="./DisabilityUITG/images/srch-rgt-top-img.png" width="11" height="28" /></td>
            </tr>

            <logic:notEmpty name="rachaBandaPersonalDetails" scope="session">

                <tr>
                    <td bgcolor="#f4f4f4" style="border-left:1px #7c7c7c solid;">&nbsp;</td>
                    <td height="40" align="left" valign="middle" bgcolor="#f4f4f4">
                        <br/>
                        <table  border="0" align="center" cellspacing="1" cellpadding="4" width="100%">
                            <tr>
                                <td colspan="10" align="center">
                                    <% if (session.getAttribute("district").equals("0")) { %>
                                    District :<b><%=request.getAttribute("districtNameDesc")%></b>
                                     <%}%>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <%if (!request.getAttribute("mandalName").equals("")) {%>
                                    District :  <b><%=request.getAttribute("districtNameDesc")%></b>
                                    Mandal : <b><%=request.getAttribute("mandalName")%></b>
                                    <%}%>
                                   
                                </td>
                            </tr>
                            <tr>
                                <td align="center" class="formhdbg">S.No</td>
                                <td align="center" class="formhdbg">SADAREM ID</td>
                                <td align="center" class="formhdbg">Name</td>
                                <td align="center" class="formhdbg">RelationName</td>
                                <td align="center" class="formhdbg">Age</td>
                                <td align="center" class="formhdbg">Gender</td>
                                <td align="center" class="formhdbg">Disability</td>
                                <td align="center" class="formhdbg">District</td>
                                <td align="center" class="formhdbg">Mandal</td>
                                <td align="center" class="formhdbg">Village</td>

                            </tr>
                            <logic:iterate id="rows" name="rachaBandaPersonalDetails" scope="session">
                                <tr>
                                    <td class="formbg" align="center">
                                        <%=j++%> .
                                    </td>
                                    <td class="formbg">
                                        ${rows.personCode}
                                    </td>
                                    <td class="formbg">
                                        ${rows.name}
                                    </td>
                                    <td class="formbg">
                                        ${rows.relationName}
                                    </td>
                                    <td class="formbg">
                                        ${rows.age}
                                    </td>
                                    <td class="formbg">
                                        ${rows.gender}
                                    </td>
                                    <td class="formbg">
                                        ${rows.disability}
                                    </td>
                                    <td class="formbg">
                                        ${rows.district}
                                    </td>
                                    <td class="formbg">
                                        ${rows.mandal}
                                    </td>
                                    <td class="formbg">
                                        ${rows.village}
                                    </td>
                                </tr>

                            </logic:iterate>

                        </table><br/>

                        <table align="center" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td valign="middle">
                                    <a href="./rachaBandaPersonal.xls?status=getExcel&distCodes=<%=request.getAttribute("districtName")%>"><img src="./DisabilityUITG/images/excel.jpg" width="20%" height="20%" border="0" >Excel</a>
                                </td>

                            </tr>

                        </table>

                    </td>
                    <td bgcolor="#f4f4f4" style="border-right:1px #7c7c7c solid;">&nbsp;</td>
                </tr>
            </logic:notEmpty>
            <tr>
                <td height="11"><img src="./DisabilityUITG/images/srch-lft-btm-img.png" width="11" height="11" /></td>
                <td background="./DisabilityUITG/images/srch-btm-bg.png"></td>
                <td><img src="./DisabilityUITG/images/srch-rgt-btm-img.png" width="11" height="11" /></td>
            </tr>
        </table><br>


    </html:form>
</body> <%session.removeAttribute("rachaBandaData");%>
</html>
