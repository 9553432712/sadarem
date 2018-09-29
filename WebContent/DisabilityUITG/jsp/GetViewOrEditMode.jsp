<%--
    Document   : ViewEditMode
    Created on : Aug 20, 2010, 6:32:49 PM
    Author     : 509865
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function validate_required(field)
    {
        with (field)
        {
            if (value==null||value=="")
            {
                alert("Please Select Person Status");return false;}
            

        }
    }

    function validate_form(thisform)
    {
        with (thisform)
        {
            if (validate_required(changeStatus)==false)
            {changeStatus.focus();return false;}
            if (validate_required(changeStatus)==false)
            {changeStatus.focus();return false;}
        }
    }
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="updateViewEdit.do?updateViewOrEdit=updateViewOrEdit" styleId="partAForm" method="post" onsubmit="return validate_form(this)" >

            <br><br><br><br>
            <table  align="center" cellspacing="0" cellpadding="8" class="innerTable" width="60%">
                <tr><td class="heading" colspan="2" align="center">Update Person Status View or Edit</td></tr>
            </table>
            <table align="center" cellspacing="0" cellpadding="8" class="innerTable1" width="60%">
                <tr><td class="columnHeight10" colspan="2"></td></tr>
                <% String status = (String) request.getAttribute("status");
                   String personCode = (String) request.getAttribute("personCode");%>
                <input type="hidden" name="personcode" value="<%=personCode%>"/>
                <% if (status != null) {%> <tr><td class="label" colspan="2">Person Status is: <font color="blue"><%=status%></font><br>
                        SADAREM ID: <font color="blue"><%=personCode%></font></td></tr>
                <tr>
                    <td class="label" colspan="2">If You Want to Change  Select Status<font color="red"><b>*</b></font>&nbsp;&nbsp;&nbsp;
                   
                        <select name="changeStatus">
                            <option value="">Select</option>
                            <option value="View">View</option>
                            <option value="Edit">Edit</option>
                        </select>
                    </td>
                    </tr>
                    <tr><td align="center" colspan="2">
                        <html:submit value="Update" styleClass="button"/>

                    </td>
                </tr>
                <% }%>

            </table>
        </html:form>
    </body>
</html>
