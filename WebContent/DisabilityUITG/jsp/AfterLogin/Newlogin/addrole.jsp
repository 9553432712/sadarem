<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.bf.disability.form.ServiceBean" %>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <script>
            function validate_required(field,alerttxt)
            {
                with (field)
                {
                    if (value==null||value=="")
                    {
                        alert(alerttxt);
                        return false
                    }
                    else
                    {
                        return true
                    }
                }
            }
            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(rolename,"Enter Role Name")==false)
                    {
                        rolename.focus();
                        return false
                    }
                }
            }
        </script>
        <LINK REL="stylesheet" HREF="./DisabilityUITG/css/styles.css">


    </head>
    <body>
       
        <html:form action="/addrole.do"  method="post"  onsubmit="return validate_form(this)">


            <table border="0" align="center" cellpadding="0" cellspacing="1" class="inputform">
                <tr>
                    <th colspan="2"> Create a New Role </th>
                </tr>


                <tr><td > <b> Role Name</b></td>
                    <td> <input type="text" name="rolename" size="15" maxlength="15"></td>
                </tr>

                <tr>
                    <th colspan="2">
                        Assign the Services to the New Role
                    </th>
                </tr> 


                <tr>
                <th align="center" >
                    Select </th>
                <th align="center" >Serive Name</th>


            </tr>

            <%-- Iterate should start here--%>


            <%int x = 0;
                        int y = 0;
                        int depth;
                        ArrayList mylist = (ArrayList) request.getAttribute("servicelist");
                        ServiceBean servicebean;
            %>

            <logic:iterate id="ServiceList" name="addroleform" property="ServiceList" indexId="count" >
                <% servicebean = (ServiceBean) mylist.get(y);
                           depth = Integer.parseInt(servicebean.getDepth());
                           y++;%>
                <tr>
                    <logic:equal name="ServiceList" property="is_parent" value="false" >
                        <td align="left" class="datafield" >
                        </logic:equal>
                        <logic:notEqual name="ServiceList" property="is_parent" value="false" >
                        <td align="left" class="tableheader" >
                        </logic:notEqual><center><html:checkbox name="ServiceList" property="select" indexed="true"/></td></center>

                    <logic:equal name="ServiceList" property="is_parent" value="false" >
                        <td align="left" class="datafield" >
                        </logic:equal>
                        <logic:notEqual name="ServiceList" property="is_parent" value="false" >
                        <td align="left" class="tableheader" >
                        </logic:notEqual>


                        <% for (int i = 0; i < depth; i++) {%>

                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <%            }%>


                        <bean:write name="ServiceList" property="service_name"  />




                    </td>



                </logic:iterate>



            <tr>
                <th colspan="2">
                    <html:submit styleClass="button"  />
                    <html:reset styleClass="button" />
                </th>
            </tr>

        </table>

        <% String message = (String) request.getAttribute("msg");%>
        <% if (message != null) {%> <b><font color="red"><center><%=message%> <% } else {%><br><br> <% }%></center></font></b>

    </html:form>
</body>
</html>


