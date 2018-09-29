<%--
    Document   : UpdateSadaremDetails
    Created on : Jan 31, 2013, 4:35:29 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
        <title>SADAREM</title>
    </head>
    <script>
        function checkAllBoxes(element){
            var CheckBoxArray = document.getElementsByName("selectBox");
            if(element.checked == true){
                for(var i=0;i<CheckBoxArray.length;i++){
                    CheckBoxArray[i].checked = true;

                }
            }
            else{
                for(var i=0;i<CheckBoxArray.length;i++){
                    CheckBoxArray[i].checked = false;
                }
            }
        }
        function checkValues(){
            var checkCount = 0;
            var sCheck = document.getElementsByName("selectBox");
            for(var i=0;i<sCheck.length;i++){
                if(sCheck[i].checked){
                    checkCount++;

                }
            }

            if(checkCount==0){
                alert("Select Atleast One checkbox");
                return false;
            }else
            {

                document.forms[0].mode.value = "getdata";
                document.forms[0].submit();

            }
        }



    </script>
    <body >
        <html:form action="updateSadaremDetails">
            <html:hidden property="mode"/>
            <%int i = 1;%>

            <logic:present name="Success">
                <center><font color="red" size="2"><b><bean:write name="Success"/></b></font> </center>
            </logic:present>
            <logic:notEmpty name="sadarem">
                <table align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td  colspan="9" ><font color="black"><h4> &nbsp;&nbsp;&nbsp;<br><b>Duplicate Request Details</b></h4></font></td>

                    </tr>
                </table>


                <table id="results" align="center" border="0" cellpadding="0" cellspacing="0" width="60%" style="font-size:11px;">
                    <tr>
                        <td>
                            <div style="overflow-y:auto;height:250px">
                                <table border="0" cellpadding="5" cellspacing="1" width="100%">
                                    <tr>
                                        <td align="center" class="formhdbg">
                                            SNo.
                                        </td>
                                        <td class="formhdbg" align="center">PensionCard No</td>
                                        <td class="formhdbg" align="center">SADAREM ID </td>
                                        <td class="formhdbg" align="center">First Name </td>
                                        <td class="formhdbg" align="center">Relation Name</td>
                                        <td class="formhdbg" align="center">Mandal Name</td>
                                        <td class="formhdbg" align="center">Village Name</td>
                                        <td class="formhdbg" align="center">Habitation Name </td>
                                        <td class="formhdbg" align="center">ALL<html:checkbox property="selectBox" styleId="selectBox"  onclick="checkAllBoxes(this)"/></td>
                                    </tr>
                                    <logic:iterate id="row" name="sadarem">
                                        <tr>
                                            <td class="formaltbg" align="center">
                                                <%=i++%>.
                                            </td>
                                            <td class="formaltbg" align="left">${row.Pensioncardno}</td>
                                            <td class="formaltbg" align="center">${row.PersonCode}</td>
                                            <td class="formaltbg" align="left">${row.firstname}</td>
                                            <td class="formaltbg" align="left">${row.relationname}</td>
                                            <td class="formaltbg" align="left">${row.MANDALNAME}</td>
                                            <td class="formaltbg" align="left">${row.VILLAGENAME}</td>
                                            <td class="formaltbg" align="left"> ${row.HabitationName}</td>

                                            <td class="formaltbg" align="center"><html:checkbox property="selectBox" styleId="selectBox${i}" value="${row.Pensioncardno}" /></td>
                                        </tr>

                                    </logic:iterate>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>


                <table align="center">
                    <tr>
                        <td align="center" colspan="10"><br><html:button property="sub"  value="Submit" onclick="checkValues();"/></td>
                    </tr>
                </table>


            </logic:notEmpty>

        </html:form>

    </body>
</html>
