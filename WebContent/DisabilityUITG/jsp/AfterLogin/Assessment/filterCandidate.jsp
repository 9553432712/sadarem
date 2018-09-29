
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.bf.disability.common.DataBase.DbManager"%>




<%
            String rid = "";
%>
<%
            int i = 1;
            ResultSet rs = null;
            Connection con = null;
            Statement st = null;
            String sql = null;
            DataSource ds = null;
            String res = null;
            String penId = null;
            ArrayList duplicateRationCardData = new ArrayList();

            Map duplicate = null;


            ArrayList duplicateRationCardDetails = (ArrayList) request.getAttribute("duplicateRationCardDetails");

            if (duplicateRationCardDetails != null) {
                Iterator it = duplicateRationCardDetails.iterator();

                while (it.hasNext()) {
                    duplicate = new HashMap();
                    duplicate = (HashMap) it.next();
                }
            }

            if (request.getAttribute("ds") != null) {
                ds = (DataSource) request.getAttribute("ds");
            }
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");




%>
<script type="text/javascript">
    function validate_required(field)
    {
        with (field)
        {
            if (value==null||value=="")
            {alert("Please Enter SADAREM ID");return false;}
            var numericExpression = /^[0-9]+$/;
            if(!value.match(numericExpression))
            {alert("SADAREM ID must be Integer");return false;}
            if(value.length !=17)
            {alert("SADAREM ID must be exactly of 17 numbers");return false;}


        }
    }

    function validate_form(thisform)
    {
        with (thisform)
        {
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
            if (validate_required(personcode)==false)
            {personcode.focus();return false;}
        }
    }
</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <script type="text/javascript">


            function getVillageData() {
                document.forms[0].mode.value="getVillageDataDetails";
                document.forms[0].submit();
            }



            function getRationCardDuplicateList(){

                if(document.forms[0].elements['mandal_id'].value=="") {
                    alert("Please Select Mandal");
                    document.forms[0].elements['mandal_id'].focus();
                    return false;
                }
                else if(document.forms[0].elements['village_id'].value=="") {
                    alert("Please Select Village");
                    document.forms[0].elements['village_id'].focus();
                    return false;
                }

                document.forms[0].mode.value="getRationCardDuplicateData";
                document.forms[0].submit();

            }

            function getDuplicatePensionDetails(){
                document.forms[0].mode.value="updateDuplicateData";
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>

        <html:form action="/filterCandidate" method="post">


            <html:hidden property="mode"/>
            <html:hidden property="duplicatePensioncodes"/>

            <logic:present name="duplicateRationMsgs">
                <center>
                    <font color="red" size="3">${duplicateRationMsgs}</font>
                </center>
            </logic:present>
             <logic:present name="succmsg">
                <p id="succmsg">${succmsg}</p>
            </logic:present>

            <logic:present name="errmsg">
                <p id="errmsg">${errmsg}</p>
            </logic:present>
            <table  align="center" cellspacing="0" cellpadding="0" class="inputform" width="50%">

                <th  colspan="4" align="center">Filter Candidate Details</th>



                <tr>
                    <td  >Mandal</td>
                    <td >
                        <html:select styleId="2" property="mandal_id" onchange="getVillageData()" style="height:25px;">
                            <html:option  value="0">All</html:option>
                            <html:optionsCollection property="mandalList" label="mandal_name" value="mandal_id"/>

                        </html:select>
                    </td>

                    <td >Village</td>
                    <td >
                        <html:select styleId="2" property="village_id"  style="height:25px;">
                            <html:option  value="0">All</html:option>
                            <html:optionsCollection property="villageList" label="village_name" value="village_id"/>
                        </html:select>
                    </td>
                </tr>


                <th colspan="4" >
                    <html:button property="but" onclick="getRationCardDuplicateList();" value="Get"/>

                </th>

            </table>
            <br/><br/>
            <logic:present name="duplicateRationCardDetails">
                <logic:notEmpty name="duplicateRationCardDetails">
                    <div style="overflow:auto; width:900px; height:458px; vertical-align: top;border: 1px">
                        <table width="90%" cellpadding="0" cellspacing="1" border="0" align="center"  class="inputform">
                            <tr>
                                <th>
                                    Sno
                                </th>
                                <th  >
                                    Pension Card Number
                                </th>
                                <th  >
                                    Ration Card Number
                                </th>
                                <th >
                                    SADAREM ID
                                </th>
                                <th >
                                    Name
                                </th>
                                <th >
                                    Relation Name
                                </th>


                                <% if (mandal_id.equals("0")) {%>
                                <th class="formhdbg" align="center">Mandal</th>
                                <% } else if (village_id.equals("0")) {%>
                                <th class="formhdbg" align="center">Village</th>
                                <% } else if (!village_id.equals("0")) {%>
                                <th class="formhdbg" align="center">Habitation</th>
                                <% }%>
                                <th >
                                    Reason For PersonStatus
                                </th>

                                <th >
                                    PensionPhase
                                </th>
                                <th >
                                    Age
                                </th>
                                <th >
                                    Gender
                                </th>

                                <th >
                                    Assessement Status
                                </th>

                                <th  >
                                    PensionCardNo
                                </th>
                                <th  >
                                    Photo Details
                                </th>
                            </tr>

                            <logic:iterate name="duplicateRationCardDetails" id="row">
                                <bean:define id="sno" value="${row.rationCardNumber}"/>
                                <bean:define id="pensionId" value="${row.pensionCardNo}"/>
                                <bean:define id="sadaremId" value="${row.pensionCode}"/>

                                <%res = sno.toString();%>
                                <% penId = pensionId.toString();%>
                                <input type="hidden" name="rCard" value="<%=sno.toString()%>">
                                <tr>

                                    <logic:notEqual value="<%=sno.toString()%>" name="scodes" scope="request">
                                        <td class="formaltbg" align="center"> <%=i++%></td>
                                    </logic:notEqual>
                                    <logic:equal value="<%=sno.toString()%>" name="scodes" scope="request">
                                        <td class="formaltbg" align="center"> &nbsp;</td>
                                    </logic:equal>


                                    <td class="formaltbg" align="center">
                                        ${row.pensionCardNo}

                                    </td>

                                    <td class="formaltbg" align="center">
                                        <logic:notEqual value="<%=sno.toString()%>" name="scodes" scope="request">
                                            ${row.rationCardNumber}
                                        </logic:notEqual>
                                        <logic:equal value="<%=sno.toString()%>" name="scodes" scope="request">
                                            "  &nbsp;    "    &nbsp;   "
                                        </logic:equal>
                                    </td>

                                    <td class="formaltbg" align="center">
                                        ${row.pensionCode}

                                    </td>

                                    <td class="formaltbg" align="left">
                                        ${row.name}
                                    </td>

                                    <td class="formaltbg" align="left">
                                        <!-- <colored:colored color1="red" color2="yellow" color3="blue"/> -->

                                        ${row.ralationName}
                                    </td>

                                    <td class="formaltbg" align="left">
                                        ${row.territaoryDetails}
                                    </td>


                                    <td class="formaltbg" align="left">
                                        ${row.reasonForStatus}
                                    </td>
                                    <td class="formaltbg" align="left">
                                        ${row.pensionPhase}
                                    </td>

                                    <td class="formaltbg" align="center">
                                        ${row.ageYears}
                                    </td>

                                    <td class="formaltbg" align="center">
                                        ${row.gender}
                                    </td>

                                    <td class="formaltbg" align="left">
                                        ${row.assessmentStatus}
                                    </td>


                                    <td class="formaltbg" align="center">
                                        <table align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="formaltbg" align="center">
                                                    <html:select property="pensionids">
                                                        <html:option value = "0" > --Select-- </html:option>
                                                        <%
                                                                    ArrayList duplicateList = new ArrayList();
                                                                    String duplicateData = null;
                                                                    try {
                                                                        sql = "select Ltrim(Rtrim(pensioncard_no)) from tblperson_personal_details with (nolock) where rationcard_number='" + res + "' ";

                                                                        con = DbManager.getConnection(ds);
                                                                        st = con.createStatement();
                                                                        rs = st.executeQuery(sql);

                                                                        if (rs != null) {
                                                                            while (rs.next()) {%>

                                                        <%     if (!(rs.getString(1).equals(penId))) {
                                                        %>

                                    <!--<td class="formaltbg" align="center" colspan="<%=rs.getString(1).length()%>"> -->
                                                        <html:option value="<%=rs.getString(1).trim()%>">
                                                            <%=rs.getString(1)%>
                                                        </html:option>

                                                        <%}
                                                                            }
                                                                        }

                                                                    } catch (SQLException e) {
                                                                        e.printStackTrace();
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    } finally {
                                                                        if (con != null) {
                                                                            con.close();
                                                                        }
                                                                        if (st != null) {
                                                                            st.close();
                                                                        }
                                                                        if (rs != null) {
                                                                            rs.close();
                                                                        }
                                                                    }
                                                        %>
                                                    </html:select>
                                                    <html:hidden property="pensionCardDuplicate" value=" ${row.pensionCardNo}"/>
                                            </tr>
                                        </table>
                                    </td>




                                    <!-- <td class="formbg" align="center" style="border-style:dotted;background-color:inherit">
                                           <img src="./DisabilityUI/uploadedphotos/${row.pensionCode}.JPG" height="150"/><br/>
                                           <b>${row.pensionCode}</b>
                                       </td>-->
                                    <td class="formaltbg" align="center" >
                                        <a href="javascript:void(0);"onclick="window.open('photoupload.do?mode=photoUploadDetails&sadaremID=${row.pensionCode}', 'Ratting','resizable=yes, scrollbars=yes,width=300px' )">
                                            PhotoView
                                        </a>
                                    </td>

                                    <% request.setAttribute("scodes", sno);%>
                                </tr>
                            </logic:iterate>
                        </table>
                    </div>
                    <br>
                    <table width="90%" cellpadding="0" cellspacing="0" border="0" align="center">
                        <tr>
                            <td colspan="6" align="center">
                                <html:button property="but" onclick="getDuplicatePensionDetails();" value="Update"/>
                            </td>
                        </tr>

                    </table>
                </logic:notEmpty>
            </logic:present>

        </html:form>
    </body>
</html>
