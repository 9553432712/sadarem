<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="application/MyExcel.ms-excel" %>
<%@page import="java.util.ArrayList"%>
<%int i = 1;%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html:html>
    <head>
<style>
.formhdbg
{
background-color : black ;
}

</style>

    </head>
    <body>
        <logic:notEmpty name="ageData" scope="session">

            <table border="1" cellspacing="0" cellpadding="0" align="center" class="innerTable">
                <tr bgcolor="gray">
                    <td colspan="12" align="left">
                    <b> Generated Date: <%=new java.util.Date()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      PWD's Details</b> 
                    </td>

                </tr>
                <tr bgcolor="gray">


                    <td class="formhdbg" align="center">
                        <b>Pension ID</b>
                    </td>
                    <td class="formhdbg" align="center">
                        <b>Sadarem ID</b>

                    </td>
                    <td class="formhdbg" align="center">
                        <b>Name</b>
                    </td>
                    <td class="formhdbg" align="center">
                        <b>Relation</b>
                    </td>

                    <td class="formhdbg" align="center">
                        <b>Age</b>
                    </td>

                    <td class="formhdbg" align="center">
                        <b>Qualification</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b>Type Disabilty</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b> Disability Percentage</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b>Contact number</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>District</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>Mandal</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>Village</b>

                    </td>


                </tr>
                 <tr bgcolor="gray">


                    <td class="formhdbg" align="center">
                        <b>1</b>
                    </td>
                    <td class="formhdbg" align="center">
                        <b>2</b>

                    </td>
                    <td class="formhdbg" align="center">
                        <b>3</b>
                    </td>
                    <td class="formhdbg" align="center">
                        <b>4</b>
                    </td>

                    <td class="formhdbg" align="center">
                        <b>5</b>
                    </td>

                    <td class="formhdbg" align="center">
                        <b>6</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b>7</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b>8</b>

                    </td>

                    <td class="formhdbg" align="center">
                        <b>9</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>10</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>11</b>

                    </td>
                     <td class="formhdbg" align="center">
                        <b>12</b>

                    </td>


                </tr>
                <logic:iterate name="ageData" id="row" scope="session">
                    <tr>

                        <td class="formaltbg" align="left">
                            ${row.PensionCard_No}
                        </td>
                        <td class="formaltbg">
                            SID - ${row.Person_Code}
                        </td>
                        <td class="formaltbg">
                            ${row.name}
                        </td>
                        <td class="formaltbg">
                            ${row.relation_name}
                        </td>
                        <td class="formaltbg" align="center">
                            ${row.age}
                        </td>
                        <td class="formaltbg">
                            ${row.qly}
                        </td>
                        <td class="formaltbg">
                            ${row.disability}
                        </td>
                        <td class="formaltbg">
                            ${row.percentage}
                        </td>
                        <td class="formaltbg">
                            ${row.mobile}
                        </td>
                        
                        <td class="formaltbg">
                            ${row.district_name}
                        </td>
                        <td class="formaltbg">
                            ${row.mandal_name}
                        </td>
                        <td class="formaltbg">
                            ${row.village_name}
                        </td>

                    </tr>
                </logic:iterate>
            </table>
        </logic:notEmpty>
    </body>

</html:html>