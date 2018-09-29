<%-- 
    Document   : ViewPhoto
    Created on : Dec 9, 2010, 5:56:48 PM
    Author     : 509865
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<% String msg = null;
            String personcode = (String) request.getAttribute("personcode");
            String name = (String) request.getAttribute("name");
            String districtName = (String) request.getAttribute("districtName");
            msg = (String) request.getAttribute("msg");
%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>SADAREM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
            </head>

    <body>
        <table border="0"  cellspacing="0" cellpadding="0" width="60%" align="left" height="200px" >
            <tr>
                <td align="center">

                    <table border="1" cellspacing="0" cellpadding="0" width="95%" align="center" style="margin-top:10px;" class="inputform">
                        <tr>
                            <th>PERSON Details</th>
                         
                        </tr>
                        <tr>
                            <td align="left" valign="middle" >
                                <TABLE border="0" cellSpacing="0" cellPadding="6" width="100%" align="center" style="border-right:1px #999999 solid;">
                                    <TR>
                                        <TD width="39%" align="left" valign="middle" ><b>Person Name :</b></TD>
                                        <TD width="45%" align="left" valign="middle" ><%= name%></TD>
                                        <TD width="16%" rowspan="2" align="center" valign="middle" >
                                            <% if (msg != null) {%> <font color="black"><center></center></font><br><%=msg%> <% } else {%>
                                            <img align="right" src="./DisabilityUITG/uploadedphotos/<%= districtName%>/<%= personcode%>.JPG" width="100" height="100" onerror="this.src='<%=request.getContextPath()%>/images/noimage.png'">
                                            <% }%></TD>
                                    </TR>
                                    <TR>
                                        <TD align="left" valign="middle" ><b>SADAREM ID :</b></TD>
                                        <TD align="left" valign="middle" ><%= personcode%></TD>
                                    </TR>
                                </TABLE>
                            </td>
                        </tr>
                       
                    </table>

                </td>
            </tr>
        </table>

    </body>
</html>


