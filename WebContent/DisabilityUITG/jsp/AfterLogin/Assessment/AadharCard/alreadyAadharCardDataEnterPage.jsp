<%-- 
    Document   : alreadyAadharCardDataEnterPage
    Created on : Oct 9, 2014, 4:59:26 PM
    Author     : 310926
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <table  align="center" cellspacing="1" cellpadding="5" class="innerTable" width="60%" >

            <tr>
                <td align="center" class="labelBlue"><font  size="4">This Aadhar Card number <font  size="5" color="red"> <%= request.getAttribute("aadharCard")%> </font>
                        is already entered please make sure that you make a note of SADAREM ID for this pension number and use update facility:- </font>
                    <br><br><br>
                    <font  size="10" color="red"> <%= request.getAttribute("personcode")%> </font>
                </td>
            </tr>

        </table>
    </body>
</html>
