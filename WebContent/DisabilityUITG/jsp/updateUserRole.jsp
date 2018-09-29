<%-- 
    Document   : updateUser
    Created on : May 19, 2010, 2:44:28 PM
    Author     : srinivas_p
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <head>
    <script>

        function getData(read)
        {
            document.forms[0].status.value="update";
            document.forms[0].submit();
        }
       

    </script>

    </head>

    <BODY>

    

   <html:form action="getUser.do?getUserDetails=getUserDetails" >
        <input type="hidden" name="status" value="finish"/>
       
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable" width="60%">
            <br><br><br><br>
        <tr>
            <td class="heading" align="center">Update User Role Details</td>
        </tr>
        </table>
        <table  align="center" cellspacing="1" cellpadding="8" class="innerTable1" width="60%">
            <tr>
            <td class="label">Userid</td>

            <td>
                <html:select  property="rowid">
                <html:option  value="">--SELECT--</html:option>
                <html:options collection="userdetailslist" property="rowid" labelProperty="user_name"/>
                </html:select>
            </td>
            </tr>
            <tr>
                <td class="label">Role</td>
               
            <td>
                <html:select property="role_id" >
                            <html:option value="">--SELECT--</html:option>
                            <html:option value="1">D.E.O Part-A</html:option>
                          <%--  <html:option value="2">D.E.O Part-B</html:option> --%>
                            <html:option value="3">D.E.O Total</html:option>
                            </html:select>
            </td>
            </tr>
            


            <tr>
                <td colspan="4" align="center"><html:submit value="submit" styleClass="button"/></td>
            </tr>

        </table>



    </html:form>


    </body>
</html:html>
