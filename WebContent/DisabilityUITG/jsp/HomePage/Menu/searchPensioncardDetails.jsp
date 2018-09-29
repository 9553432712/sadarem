<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%int i = 1;
            String path = request.getContextPath();
            String Pensionid = (String) request.getAttribute("Pensionid");
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
            function validateform(){
             
                if(document.forms[0].elements['pensionid'].value=="") {
                    alert("Please Enter Pension Id");
                    document.getElementById("pensionid").focus()
                    return false;
                }
                else if(document.forms[0].elements['district_id'].value=="0") {
                    alert("Please Select District");
                    document.getElementById("district_id").focus()
                    return false;
                }else{
                    document.forms[0].SearchPage.value="searchPensionCardDetaails";
                    document.forms[0].submit();
                
                    document.getElementById('sadaremprocessing').style.display='';
                }
            }

        </script>

    </head>
    <body >
        <%int x = 1;%>
        <html:form action="/search.do" method="post" >

            <input type="hidden" name="SearchPage"/>
            <table border="0" cellspacing="1" cellpadding="0"  width="50%" class="inputform" align="center">


                <tr>
                    <td   align="right">Pension ID</td>

                    <td>
                        <html:text property="pensionid" size="50" maxlength="17" onkeypress="return isNumberKey()"/><br/>
                    </td>
                </tr>

                <tr>
                    <td   align="right">District </td>

                    <td  valign="middle">
                        <html:select styleId="1" property="district_id" onchange="getData()" style="width:165px;height:22PX;">
                            <html:option value="0">-- Districts --</html:option>
                            <html:optionsCollection   property="districtList" label="district_name" value="district_id"  />
                        </html:select><br/>
                    </td>
                </tr>

                <tr>
                    <th colspan="2" align="center" id="hide">
                        <input type="button" value="submit" onclick="validateform();">
                        <img src='<%=basePath%>DisabilityUITG/images/loading.gif' id="sadaremprocessing" style="display: none;"/>

                    </th>
                </tr>

            </table>
            <br/>
            <logic:notEmpty name="rationcardDetails">

                <table border="0" cellspacing="1" cellpadding="0"  width="90%" class="inputform" align="center">
                    <tr><td style="text-align: center" colspan="8"><font size="2"><b>Pension ID :<%=Pensionid%></b></font></td></tr>
                    <tr>
                        <th  >
                            Sno
                        </th>

                        <th  >
                            SADAREM ID
                        </th>

                        <th  >
                            Surname
                        </th>
                        <th  >
                            Name
                        </th>


                        <th>Relation Name</th>
                      
                        <th  >
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
            <logic:present name="noData">
                <font color="red"><center><bean:write name="noData"/></center></font>
            </logic:present>
        </html:form>
    </body>

</html>