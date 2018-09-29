<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%int i=1;%>
<%@page import="java.util.ArrayList"%>

<html:html>
    <body  onload="window.print()">
        <form>
          <logic:notEmpty name="ageData" scope="session">
              <p>PWD's Details</p>
              <table border="0" cellspacing="1" cellpadding="0" align="center" class="inputform" width="90%">
                     
                   
                    <tr>

                        <th>
                            Sno
                        </th>
                        <th>
                            Pension ID
                        </th>
                        <th>
                            Sadarem ID

                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Relation
                        </th>

                        <th>
                            Age
                        </th>

                        <th>
                            Qualification

                        </th>

                        <th>
                            Type Disabilty

                        </th>

                        <th>
                            Disability Percentage

                        </th>

                        <th>
                            Contact number

                        </th>


                    </tr>
                    <logic:iterate name="ageData" id="row" scope="session">
                        <tr>
                            <td>
                                <%=i++%> .
                            </td>
                            <td  align="left">
                                ${row.PensionCard_No}
                            </td>
                            <td >
                                ${row.Person_Code}
                            </td>
                            <td >
                                ${row.name}
                            </td>
                             <td >
                                ${row.relation_name}
                            </td>
                            <td>
                                ${row.age}
                            </td>
                            <td >
                                ${row.qly}
                            </td>
                            <td >
                                ${row.disability}
                            </td>
                            <td >
                                ${row.percentage}
                            </td>
                            <td >
                                ${row.mobile}
                            </td>

                        </tr>
                    </logic:iterate>
                </table>
            </logic:notEmpty>

        </form>

    </body>


</html:html>
