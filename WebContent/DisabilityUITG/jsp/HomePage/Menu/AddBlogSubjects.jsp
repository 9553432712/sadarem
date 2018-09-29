<%-- 
    Document   : AddBlogSubjects
    Created on : 27 Nov, 2013, 12:17:05 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="javascript" type="text/javascript">
            function saveDetails() {
                if(document.forms[0].elements["addSubject"].value=="") {
                    alert("Please Enter Subject");
                    document.forms[0].elements["addSubject"].value="";
                    document.forms[0].elements["addSubject"].focus();
                    return false;
                }
                document.forms[0].mode.value="addSubjects";
                document.forms[0].submit();
            }

            function updateDetails() {
                if(document.forms[0].elements["addSubject"].value=="") {
                    alert("Please Enter Subject");
                    document.forms[0].elements["addSubject"].value="";
                    document.forms[0].elements["addSubject"].focus();
                    return false;
                }
                document.forms[0].mode.value="updateSubjects";
                document.forms[0].submit();
            }

            function goForEdit(subjectId,subject) {
                document.forms[0].elements["addSubject"].value=subject;
                document.forms[0].rowForEdit.value=subjectId;
                document.getElementById("insert").style.display="none";
                document.getElementById("update").style.display="";
            }

            function goForDelete(id)
            {
                var conf=confirm("Are You Sure! you want to Delete ?")
                if (conf==true)
                {
                    document.forms[0].action ="./blogSubjects.do?mode=inactiveRecord&inactive="+id;
                    document.forms[0].submit();
                }
            }

        </script>
    </head>
    <body>
        <html:form action="/blogSubjects" focus="addSubject">
            <html:hidden property="mode"/>
            <input type="hidden" name="rowForEdit">
            <logic:present name="msg">
                <center><%=request.getAttribute("msg")%></center>
            </logic:present>
            <br/><table align="center" cellpadding="0" cellspacing="0" border="0" width="60%">
                <tr>
                    <td valign="top">
                        <table align="center" cellpadding="0" cellspacing="0" border="0" class="inputform">

                            <th align="center" colspan="2">Add Blog Subjects</th>

                            <tr>

                                <td>
                                    Subject Name
                                </td>
                                <td align="left">
                                    <html:text property="addSubject" onkeydown="return space(event,this);" size="36" maxlength="100"/>
                                </td>
                            </tr>
                            <logic:notPresent name="updateStat">
                                <tr id="insert">
                                    <th colspan="2" align="center">
                                        <html:button property="subprop" value="Add Subject" onclick="saveDetails();"/>
                                    </th>
                                </tr>
                            </logic:notPresent>
                            <tr id="update" style="display: none;">
                                <th colspan="2" align="center">
                                    <html:button property="subprop" value="Update Subject" onclick="updateDetails();" />
                                </th>
                            </tr>
                            <logic:present name="updateStat">
                                <tr id="update">
                                    <th colspan="2" align="center">
                                        <html:button property="subprop" value="Update Subject" onclick="updateDetails();" />
                                    </th>
                                </tr>
                            </logic:present>
                        </table>

                    </td>
                </tr>

                <tr>
                    <td align="center"><br>
                        <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform">
                            <tr>
                                <th>
                                    Sno
                                </th>
                                <th>
                                    Subject
                                </th>
                                <th>
                                    Action
                                </th>
                            </tr>
                            <logic:empty name="subjects">
                                <tr align="center">
                                    <td colspan="10" align="center"><font color="red" size="2">Subjects Not Updated</font></td>
                                </tr>
                            </logic:empty>
                            <logic:notEmpty name="subjects">
                                <logic:iterate id="ccla" name="subjects" scope="request">
                                    <tr>
                                        <td width="2px">
                                            <center> <%=i++%>.</center>
                                        </td>
                                        <td>
                                            ${ccla.Subject}
                                        </td>

                                        <td width="18%" style="text-align:center">
                                            <center><a href="#" onclick="goForEdit('${ccla.SubjectId}','${ccla.Subject}');"><img src="./DisabilityUITG/images/Edit.png" alt="Edit" ></a>&nbsp; 
                                                <a href="#" onclick="goForDelete(${ccla.SubjectId});"><img src="./DisabilityUITG/images/delete.png" alt="delete"  style="width: 25px"></a></center>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </table>
                    </td>
                </tr>
            </table>

        </html:form>
    </body>
</html>
