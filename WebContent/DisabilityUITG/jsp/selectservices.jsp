<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page import="org.bf.disability.form.ServiceBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>





    </head>
    <body>
        <br>
        <br>



        <html:form action="/updateservices.do"  method="post">
            

            <table border="1" align="center" class="inputform">
                <tr>
                    <th colspan="2" align="center" class="tableheader"> Add a New Role </th>
                </tr>


                <tr>
                    <th colspan="2" align="center" class="tableheader">
                        Assign the Services to the New Role
                    </th>

                </tr>
                <tr>

                    <th   align="center" class="tableheader">
                        Select </th>
                    <th   align="center" class="tableheader">Serive Name</th>

                </tr>


                <%-- Iterate should start here--%>


                <%int x = 0;
                            int y = 0;
                            int depth;
                            ArrayList mylist = (ArrayList) request.getAttribute("servicelist");
                            ServiceBean servicebean;
                %>

                <logic:iterate id="ServiceList" name="servicesform" property="ServiceList" indexId="count" >
                    <% servicebean = (ServiceBean) mylist.get(y);
                           depth = Integer.parseInt(servicebean.getDepth());
                           y++;%>
                    <tr>
                        <logic:equal name="ServiceList" property="is_parent" value="false" >
                            <td align="left" class="datafield" >
                            </logic:equal>
                            <logic:notEqual name="ServiceList" property="is_parent" value="false" >
                            <td align="left" class="tableheader" >
                            </logic:notEqual><center><html:checkbox name="ServiceList" property="select" indexed="true"/>
                                <html:hidden name="ServiceList" property="select" indexed="true" value="No"/>
                        </td><center>

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
                        <th colspan="2" align="center" class="tableheader">
                            <html:submit  />
                            <html:reset />
                        </th>
                    </tr>

            </table>

            <% String message = (String) request.getAttribute("msg");%>
            <% if (message != null) {%> <b><font color="red"><center><%=message%> <% } else {%><br><br> <% }%></center></font></b>

        </html:form>
    </body>
</html>

