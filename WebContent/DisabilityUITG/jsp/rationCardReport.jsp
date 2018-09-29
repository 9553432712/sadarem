<%-- 
    Document   : rationCardReport
    Created on : Feb 6, 2013, 1:18:21 PM
    Author     : 728056
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%String message = null;

            String restrictPartA = null;
            restrictPartA = request.getParameter("PartArestrict");
            if (restrictPartA == null || "".equals(restrictPartA)) {
                restrictPartA = (String) session.getAttribute("PartArestrict");
            }
            String rid = (String) request.getAttribute("rationid");
            request.setAttribute("rationid", rid);
            int i = 1;
%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
      

        <script type="text/javascript">

            function submitValues() {
                var val = document.forms[0].elements['rationCardNo'].value;
                if(document.forms[0].elements['rationCardNo'].value=="") {
                    alert("Please Enter RationCard Number");
                    document.forms[0].elements['rationCardNo'].focus();
                    return false;
                }else if(document.forms[0].elements['rationCardNo'].value.length < 15) {
                    alert("Please Enter Valid RationCard Number");
                    document.forms[0].elements['rationCardNo'].value="";
                    document.forms[0].elements['rationCardNo'].focus();
                    return false;
                }else if(val.substring(0,3)!="WAP" && val.substring(0,3)!="PAP" && val.substring(0,3)!="AAY" &&
                    val.substring(0,3)!="AAP" && val.substring(0,3)!="YAP" && val.substring(0,3)!="wap" &&
                    val.substring(0,3)!="pap" && val.substring(0,3)!="aay" &&  val.substring(0,3)!="aap" &&
                    val.substring(0,3)!="yap" && val.substring(0,3)!="RAP" && val.substring(0,3)!="rap"
                    && val.substring(0,3)!="TAP" && val.substring(0,3)!="tap"
                    && val.substring(0,3)!="WAD" && val.substring(0,3)!="wad") {
                    alert("Please Enter Valid RationCard Number");
                    // document.forms[0].elements['rationCardNo'].value="";
                    document.forms[0].elements['rationCardNo'].focus();
                    return false;
                }
                document.forms[0].mode.value="getNotLoginData";
                document.forms[0].submit();
            }

            function submitRationCardDetails() {

                if(document.forms[0].elements['rationCardslNo'].value=="") {
                    alert("Enter Rationcard Serial Number");
                    document.forms[0].elements['rationCardslNo'].focus();
                    return false;
                }

                document.forms[0].mode.value="submitRationCardDetails";
                document.forms[0].submit();
            }

        </script>

    </head>
    <body>
   
    <html:form action="RationcardDetailsReport.do" focus="rationCardNo" >


        <html:hidden property="mode"/>

 <logic:present name="msgSuccess">
                        <center><font color="green"><bean:write name="msgSuccess"/></font></center>
                    </logic:present>
                    <logic:present name="msgFailure">
                        <center><font color="red"><bean:write name="msgFailure"/></font></center>
                    </logic:present>
      

              

                                 <table width="50%" align="center" class="inputform" cellspacing="1" cellpadding="0" valign="top">
                                    <tr>
                                        <th colspan="2">
                                           RationCard Search
                                        </th>
                                    </tr>
                                    <tr>
                                        <td  class="label" >RationCard Number :</td>
                                        <td><html:text property="rationCardNo" maxlength="15" size="21"/></td>
                                    </tr>
                                    <tr>
                                       
                                        <th colspan="2">
                                            <html:button property="but" value="Submit" onclick="submitValues();"/>
                                        </th>
                                    </tr>
                                </table>
                          
                    <br/>
                    <logic:notEmpty name="rationCardDetails">
                         <table width="90%" align="center" class="inputform" cellspacing="1" cellpadding="0" valign="top">
                            <tr>
                                <th class="formhdbg" colspan="8" align="center">
                                    Person / Person's Details
                                </th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">
                                    Slno
                                </th >
                                <th class="formhdbg" align="center">
                                    SADAREM ID
                                </th >
                                <th class="formhdbg" align="center">
                                    Name
                                </th>
                                <%--<td class="formhdbg" align="center">
                                    Relation
                                </td>
                                <td class="formhdbg" align="center">
                                    Gender
                                </td>--%>
                                <th class="formhdbg" align="center">
                                    Date of Birth
                                </th>
                                <%--<td class="formhdbg" align="center">
                                    Age
                                </td>--%>
                                <th class="formhdbg" align="center">
                                    RationCard SlNo
                                </th>
                            </tr>

                            <logic:iterate id="row" name="rationCardDetails">
                                <tr>
                                    <td  align="center">
                                        <%=i++%>.
                                    </td>
                                    <td >
                                        ${row.person_code}
                                    </td>
                                    <td >
                                        ${row.name}
                                    </td>
                                    <%--<td >
                                        ${row.Relation_name}
                                    </td>
                                    <td >
                                        ${row.gender}
                                    </td>--%>
                                    <td >
                                        ${row.date_of_birth}
                                    </td>
                                   <%-- <td >
                                        ${row.age_years}
                                    </td>--%>
                                    <td  align="center">
                                        <html:text property="rationCardslNo" maxlength="2" size="2" value=""/>
                                        <html:hidden property="rationCardHidden" value="${row.person_code}"/>
                                    </td>
                                </tr>

                            </logic:iterate>
                            <%-- <tr>
                                 <td align="center" colspan="8">
                                     <html:button property="buttons" value="Submit" onclick="submitRationCardDetails();"/>
                                 </td>
                             </tr>--%>

                        </table>

                    </logic:notEmpty>
               

    </html:form>
</body>
</html:html>
