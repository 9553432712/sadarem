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
             <table cellpadding="0" cellspacing="0" align="center" width="90%" border="0" class="inputform" >

                <tr>
                 
                                <th colspan="2">Searching Number: <%= personcode%></th>
                           
                  
                </tr>
                <tr>
                    

                       <td align="center"  >

                                    <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=<%= personcode%>&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">
                                        <img src="./DisabilityUITG/images/cert.png" border="0" ><br>
                                        <h3>CERTIFICATE</h3></a>
                                </td>
                                <% if (totalDisability >= 40) {%>
                                <td align="center" >

                                    <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=idcard&personcode=<%= personcode%>&display=idcardjsp', 'Ratting','resizable=yes, scrollbars=yes,')">
                                        <img src="./DisabilityUITG/images/ID-Card.png" border="0" ><br>
                                        <h3>ID CARD</h3></a></td>
                                        <% }%>
                           
                      
                   
                </tr>

            </table>




        </html:form>
    </BODY>
    <html>


