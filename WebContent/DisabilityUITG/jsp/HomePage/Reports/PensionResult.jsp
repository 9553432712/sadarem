<%--
    Document   : Search
    Created on : Nov 22, 2010, 4:16:19 PM
    Author     : 509865
--%>


<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%         int totalDisability = (Integer) request.getAttribute("totalDisability");
            String personcode = (String) request.getAttribute("personCode");%>
<html>  
    <head>



    </head>

    <BODY>
        <html:form action="districtstatusreportforPartA.do?distWisestatusreportforPartA=distWisestatusreportforPartA"
                   styleId="partAForm" onsubmit="return validate_form(this)" style="margin:0px; padding:0px;">
           <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" id="grid">
                            <tr>
                                <td height="530" align="center"  valign="middle">

                                    <table border="0" cellspacing="0" cellpadding="0" width="70%" align="left">

                                        <tr>

                                            <td width="99%" align="center" >

                                                <TABLE border="0" cellSpacing="0" cellPadding="6" width="100%" align="center" >
                                                    <TR>
                                                    <p>Searching Number</p>
                                        </TR>
                                    </TABLE>

                                </td>
                            </tr>
                            <tr>

                                <td height="40" align="left" valign="middle" >

                                    <table  align="center" cellspacing="1" cellpadding="5" width="100%" id="grid">
                                        <tr><td align="center" class="label" width="50%">

                                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=<%= personcode%>&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                    <img src="./DisabilityUITG/images/cert.png" border="0" ><br>
                                                    <h3>CERTIFICATE</h3></a>
                                            </td>
                                            <% if (totalDisability >= 40) {%>
                                            <td align="center" class="label" width="50%">

                                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=idcard&personcode=<%= personcode%>&display=idcardjsp', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                    <img src="./DisabilityUITG/images/ID-Card.png" border="0" ><br>
                                                    <h3>ID CARD</h3></a></td>
                                                    <% }%>
                                        </tr>
                                    </table>

                                </td>

                            </tr>

                        </table>
                        <br><br><br><br>

                    </td>
                </tr>

            </table>


</html:form>
</BODY>
<html>


