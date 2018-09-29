<%-- 
    Document   : RationcardPensionersData
    Created on : May 15, 2012, 5:37:04 PM
    Author     : 490058
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@  page import="java.util.ArrayList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script language="JavaScript">
    function data(value){

              
        //document.forms[0].action="";
        document.getElementById("mode").value="";
        document.getElementById("pensioncode").value=value;

        document.forms[0].action="getExistingData.do?parameter=getExistingData";
        document.forms[0].submit();
               
    }</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
       
    </head>
    <body>
 <html:form action="getExistingData.do?parameter=getExistingData" styleId="partAForm" onsubmit="return validate_form(this)" method="post">
             <% int i = 1;%>

        <logic:present name="msg">
            <p id="errmsg"><bean:write name="msg"/>
           </p>

        </logic:present>

            <logic:notEmpty name="Pensiondata" >
                <table cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" align="center">


                    <logic:notEmpty name="Pensiondata" >
                        <logic:iterate name="Pensiondata" id="row"> <bean:define id="ration" value="${row.rno}"/></logic:iterate></logic:notEmpty>

                    <html:hidden property="mode"/>
                    <input type="hidden"  name="pensioncode"/>

                    <tr><th colspan="11" align="center" > Pension Card member details On same RationCard : ${ration}</th></tr>
                    <tr>
                        <th>
                            S.No
                        </th>

                        <th>
                            Pension ID
                        </th>

                        <th>
                           SADAREM ID
                       </th>

                        <th>
                            Name
                        </th>

                        <th>
                            Relation Name
                        </th>

                        <th>
                            Age
                        </th>

                        <th>
                            Gender
                        </th>
                      
                        <th>
                            Phase
                        </th>

                        <th>
                            Pension Status
                         </th>

                        <th>
                             Assessment Status
                         </th>

                        <th>
                            Action
                        </th>

                    </tr>
                    <logic:iterate name="Pensiondata" id="row">


                        <tr>

                            <td>
                                <%=i++%> .
                            </td>

                            <td>
                                ${row.pensionid}
                            </td>

                            <td>
                                ${row.sadarem}
                            </td>

                            <td>
                                ${row.name}
                            </td>

                            <td>
                                ${row.rname}
                            </td>

                            <td>
                                ${row.age}
                            </td>

                            <td>
                                ${row.gender}
                            </td>
                         
                            <td>
                                ${row.phase}
                            </td>

                            <td>
                                ${row.reason}
                            </td>

                            <td>
                                ${row.Ass}
                            </td>

                            <td>
                                ${row.url}
                            </td>
                        </tr>


                    </logic:iterate> </table></logic:notEmpty>
              

        <logic:present name="WebService">
            <html:form action="RationcardDetails.do">
                <html:hidden property="mode" value="getData"/>
                <input type="hidden"  name="pensioncode"/>
                <html:hidden property="rationCardNo" />
                <center><html:submit property="but" value="More" /></center>
            </html:form>
        </logic:present>

        </html:form> </body>
</html>
