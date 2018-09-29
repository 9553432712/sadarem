<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%int i = 1;%>

<%         int totalDisability = (Integer) request.getAttribute("totalDisability");
            String rationcard = (String) request.getAttribute("rationcard");
            String aadhracard = (String) request.getAttribute("aadhracard");


%>


<html>
    <head>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>


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


            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
           

           
           
           

        </script>

    </head>
    <body >
        <%int x = 1;%>
        <html:form action="/search.do" method="post" >
            <logic:notEmpty name="rationcardDetails">
                <logic:present name="rationcardnumber">
                    <table border="0" cellspacing="1" cellpadding="0"  width="90%" class="inputform" align="center">
                        <tr><td style="text-align:center"><font size="2"><b>RationCard Number: <%=rationcard%></b></font></td></tr>
                    </table>

                </logic:present>

                <logic:present name="aadhracardnumber">
                    <table border="0" cellspacing="1" cellpadding="0"  width="90%" class="inputform" align="center">
                        <tr><td style="text-align:center"><font size="2"><b>Aadhar Card Number: <%=aadhracard%></b></font></td></tr>
                    </table>

                </logic:present>

                <table border="0" cellspacing="1" cellpadding="0"  width="90%" class="inputform" align="center">

                    <tr>
                        <th  >
                            Sno
                        </th>

                        <th  >
                            SADAREM ID
                        </th>

                        <th>
                            Surname
                        </th>
                        <th  >
                            Name
                        </th>


                        <th>Relation Name</th>

                        <th>
                            Certificate
                        </th>

                        <th>
                            ID CARD
                        </th>
                    </tr>
                    <logic:iterate name="rationcardDetails" id="row">

                        <tr>
                            <td  >
                                <%=i++%> .
                            </td>

                            <td >
                                ${row.Person_Code}
                            </td>


                            <td >
                                ${row.Surname}
                            </td>
                            <td >
                                ${row.First_Name}
                            </td>
                            <td >
                                ${row.Relation_Name}
                            </td>



                            <td>

                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=certificate&personcode=${row.Person_Code}&display=certificatejsp', 'Ratting','resizable=yes, scrollbars=yes,')">View</a>
                            </td>



                            <%

                                        String s = "percentage" + x;
                                      
                                        if (request.getAttribute(s) != null) {
                                            if (Integer.parseInt(request.getAttribute(s).toString())>=40) {
                            %>
                   
                            <td>
                                <a href="javascript:void(0);" onclick ="window.open('searchForCertificate.do?searchCertificate=searchCertificate&print=idcard&personcode=${row.Person_Code}&display=idcardjsp', 'Ratting','resizable=yes, scrollbars=yes,')">View</a>
                            </td>

                            <%  } else {%>
                            <td> <b> - </b> </td>

                            <%  }%>

                            <%}%>


                           

                        </tr>

                    </logic:iterate>

                </table>
            </logic:notEmpty>

        </html:form>
    </body>

</html>