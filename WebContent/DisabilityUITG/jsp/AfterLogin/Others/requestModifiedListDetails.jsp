<%--
    Document   : requestModifiedListDetails
    Created on : May 16, 2013, 6:04:16 PM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.File" %>
<%@ page import="org.bf.disability.Constants.CommonConstants" %>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }
            function submitButtons(buttonType){
               
                if(document.forms[0].remarks.value==null || document.forms[0].remarks.value==""){
                    alert("Please Insert Remarks");
                    document.forms[0].remarks.focus();
                    return false;
                }
                if(buttonType=="Approve"){
                    document.getElementById('apprButton').disabled = true;
                    document.getElementById('rejButton').disabled = true;
                    document.forms[0].button.value="Approved";
                    document.forms[0].mode.value = "approveOrRejectRequest";
                    document.forms[0].submit();
                    return true;
                }else if(buttonType=="Reject"){
                    document.getElementById('apprButton').disabled = true;
                    document.getElementById('rejButton').disabled = true;
                    document.forms[0].button.value="Rejected";
                    document.forms[0].mode.value = "approveOrRejectRequest";
                    document.forms[0].submit();
                    return true;
                }
            }

        </script>

        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="callCentreRequestsList">
            <logic:present name="msg1">
                <script language="javascript" type="text/javascript">
                    alert("${msg1}");
                </script>
            </logic:present>
            <logic:notPresent name="msg1">
                <html:hidden property="mode"/>
                <html:hidden property="button"/>
                <br/>
                <%int i = 1;

                            ArrayList list = (ArrayList) request.getAttribute("RequestedList");
                            Map map = null;
                            if (list != null && list.size() > 0) {
                                map = (Map) list.get(0);
                            }

                %>

                <%if (map != null && map.size() > 0) {%>
                <html:hidden property="benificiaryProblemId"  value='<%=map.get("BeneficiaryProblemId").toString()%>'/>
                <html:hidden property="proofType"  value='<%=map.get("Proofdoc_Type").toString()%>'/>

                <table  border="0" class="inputform" cellpadding="0" cellspacing="1"  width="70%">
                    <tr>
                        <th colspan="2">
                            Existing Personal Details
                        </th>
                    </tr>
                    <tr>
                        <td >Name:</td>
                        <td ><%=map.get("Name")%></td>
                    </tr>
                    <tr>
                        <td >Age:</td>
                        <td ><%=map.get("Age")%></td>
                    </tr>
                    <tr>
                        <td >Gender:</td>
                        <td ><%=map.get("Gender")%></td>
                    </tr>
                    <tr>
                        <td >RelationName:</td>
                        <td ><%=map.get("RelationName")%></td>
                    </tr>
                    <tr>

                        <td >Date of Birth:</td>
                        <td ><%=map.get("Dob")%></td>
                    </tr>
                    <tr>
                        <td >Mole One:</td>
                        <td ><%=map.get("Mole_One")%></td>
                    </tr>
                    <tr>

                        <td >Mole Two:</td>
                        <td ><%=map.get("Mole_Two")%></td>
                    </tr>
                    <tr>
                        <td >District:</td>
                        <td ><%=map.get("District")%></td>
                    </tr>
                    <tr>

                        <td >Mandal:</td>
                        <td ><%=map.get("Mandal")%></td>
                    </tr>
                    <tr>
                        <td >Panchayat:</td>
                        <td ><%=map.get("Panchayat")%></td>
                    </tr>
                    <tr>

                        <td >Village:</td>
                        <td ><%=map.get("Village")%></td>
                    </tr>
                    <tr>
                        <td >Habitation:</td>
                        <td ><%=map.get("Habitation")%></td>
                    </tr>
                    <tr>
                        <td >Mobile No.:</td>
                        <td ><%=map.get("PhoneNumber")%></td>
                    </tr>
                </table>
                <br><table  border="0" class="inputform" cellpadding="0" cellspacing="1"  width="70%">

                    <tr> <td colspan="2"><a href="#" onclick="window.open('grievancesRequestDetails.do?mode=getSADAREMCampReport&benificiaryProblemId=<%=map.get("BeneficiaryProblemId")%>&districtId=<%=map.get("DistrictId")%>&mandalId=<%=map.get("MandalId")%>&villageId=<%=map.get("VillageId")%>&panchayatId=<%=map.get("PanchayatId")%>&habitationId=<%=map.get("HabitationId")%>')"> <img src="DisabilityUITG/images/RD.gif" height="30" width="450"/></a>
                        </td></tr>
                </table>


                <%
                    int subReqLength = map.get("SubRequestId").toString().length();
                    String reqId = map.get("SubRequestId").toString();

                    boolean flag = false;

                    if (map.get("RequestId").toString().equals("1") && (reqId.contains("1") || reqId.contains("2") || reqId.contains("3") || reqId.contains("4"))) {

                        flag = true;
                    }
                    if (flag) {%>
                <br><table  border="0" class="inputform" cellpadding="0" cellspacing="1"  width="70%">
                    <tr>
                        <th colspan="2">Requested to Modified Details </th>
                    </tr>
                    <%}
                        if (subReqLength > 1) {
                            String subIds[] = map.get("SubRequestId").toString().split(",");

                            for (int x = 0; x < subIds.length; x++) {
                                if (map.get("RequestId").toString().equals("1") && subIds[x].equals("1")) {%>

                    <tr>

                        <td  >Surname:</td>
                        <td  ><input type="text"  size="25" name="Newsurname" value="<%=map.get("Newsurname")%>" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td >Name:</td>
                        <td ><input type="text"  size="25"  name="Newname" value="<%=map.get("Newname")%>" readonly="true"/></td>
                    </tr>


                    <%}
                                                    if (map.get("RequestId").toString().equals("1") && subIds[x].equals("2")) {%>

                    <tr>
                        <td >Relation Name:</td>
                        <td ><input type="text"  size="25" name="Newrelationname" value="<%=map.get("Newrelationname")%>" readonly="true"/></td>
                    </tr>
                    <%-- <tr>

                    <td  align="center">Relation Type:</td>
                      <td  align="center"><input type="text"  size="25" name="RelationType" value="<%=map.get("RelationType").toString()%>" readonly="true"/>

                      </td>
                </tr>--%>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && subIds[x].equals("3")) {%>

                    <tr>

                        <td>Mole one:</td>
                        <td ><input type="text"  size="25" name="Newmole1" value="<%=map.get("Newmole1")%>" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td >Mole two:</td>
                        <td ><input type="text"  size="25" name="Newmole2" value="<%=map.get("Newmole2")%>" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && subIds[x].equals("4")) {%>

                    <tr>

                        <td >Date of Birth:</td>
                        <td ><input type="text"  size="25"  name="Newdob" value="<%=map.get("Newdob")%>" readonly="true"/></td>
                    </tr>

                    <%
                                                    }
                                                }
                                            } else {

                                                if (map.get("RequestId").toString().equals("1") && reqId.equals("1")) {%>

                    <tr>

                        <td >Surname:</td>
                        <td ><input type="text" size="25"  name="Newsurname" value="<%=map.get("Newsurname")%>" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td >Name:</td>
                        <td ><input type="text"  size="25" name="Newname" value="<%=map.get("Newname")%>" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("2")) {%>

                    <tr>

                        <td >Relation Name:</td>
                        <td ><input type="text"  size="25" name="Newrelationname" value="<%=map.get("Newrelationname")%>" readonly="true"/></td>
                    </tr>
                    <%-- <tr>

                    <td  align="center">Relation Type:</td>
                      <td  align="center"><input type="text"  size="25" name="RelationType" value="<%=map.get("RelationType").toString()%>" readonly="true"/>

                      </td>
                </tr>--%>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("3")) {%>

                    <tr>

                        <td >Mole one:</td>
                        <td ><input type="text" size="25"  name="Newmole1" value="<%=map.get("Newmole1")%>" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td >Mole two:</td>
                        <td ><input type="text"  size="25" name="Newmole2" value="<%=map.get("Newmole2")%>" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("4")) {%>

                    <tr>

                        <td >Date of Birth:</td>
                        <td ><input type="text"  size="25" name="Newdob" value="<%=map.get("Newdob")%>" readonly="true"/></td>
                    </tr>

                    <%}
                        }
                        if (flag) {%>
                </table>
                <%}

                %>
                <% if (map.get("Proofdoc_Type") != null && !(map.get("Proofdoc_Type").equals("")) && !(map.get("Proofdoc_Type").equals("-"))) {
                %>
                <br>
                <table  border="0" class="inputform" cellpadding="0" cellspacing="1"  width="70%">

                    <tr>
                        <th colspan="3">Proof of Document:</th>
                    </tr>
                    <tr>
                        <td > <%=map.get("Proofdoc_Type")%> :</td>
                        <td  style="color: #ff0000"> <%=map.get("Proof_Id")%></td>
                        <% if (map.get("ProofDoc_FileName") != null && !(map.get("ProofDoc_FileName").equals("")) && !(map.get("ProofDoc_FileName").equals("-"))) {
                        %>
                        <td > <a href="#" onclick="window.open('callCentreRequestsList.do?mode=viewDocument&fileName=<%=map.get("ProofDoc_FileName")%>&fileType=<%=map.get("Proofdoc_Type")%>&Proof_Id=<%=map.get("Proof_Id")%>&beneficiaryProblemId=<%=map.get("BeneficiaryProblemId")%>')">
                                View </a> </td>
                                <%}%>
                    </tr>
                </table>

                <%}%>

                <br>
                <table  border="0" class="inputform" cellpadding="0" cellspacing="1"  width="70%">
                    <tr>
                        <td >DPM Remarks:</td>
                        <td >                          
                            <html:textarea property="dpmRemarks" readonly="true" style="width:100%;height:50px;border:1px solid #A4A4A4;font-size:12px;font-weight:normal;font-family:arial;"/>
                        </td>
                    </tr>
                    <tr>
                        <td >Remarks:<font color="red"><b>*</b></font></td>
                        <td >

                            <html:textarea property="remarks" style="width:100%;height:50px;border:1px solid #A4A4A4;font-size:12px;font-weight:normal;font-family:arial;" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)"/>
                            <br><input readonly type="text" name="remLen1" size="3" maxlength="3" value="500" style="width:30px"/>
                        </td>
                    </tr>

                    <tr>

                        <th  colspan="2">
                            <input type="button" id="apprButton" value="Approve"  styleClass="button"  onclick="return submitButtons('Approve');"/>
                            <input type="button"  id="rejButton" value="Reject"  styleClass="button"  onclick="return submitButtons('Reject');"/>
                        </th>
                    <tr/>
                </table>
                <%}%>

            </logic:notPresent>
            <logic:present name="closewindow">
                <script>
                    window.opener.location.reload();
                    window.close();
                </script>
            </logic:present>
        </html:form>
    </body>
</html>
