<%--
    Document   : grievancesApprovalDetails
    Created on : May 21, 2013, 11:04:16 AM
    Author     : 310926
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
        <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style1.css" type="text/css">
        <script>
            var buttonType="";
            var reasonFlag="";

            function textCounter(field,cntfield,maxlimit) {
                if (field.value.length > maxlimit){  // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                    // otherwise, update 'characters left' counter
                }
                else{
                    cntfield.value = maxlimit - field.value.length;
                }

            }



            function alphavalidation(val)
            {
                var i;
                var valid="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

                if(val!=null)

                {
                    for(i=0;i<val.length;i++)
                    {

                        if(valid.indexOf(val.charAt(i))<0)
                        {

                            alert("Please enter Characters only");
                            document.forms[0].relationship_with_benificiary.value="";
                            document.forms[0].relationship_with_benificiary.focus();
                            break;
                        }

                    }
                }
            }



            var exts = "jpg";
            function checkExt3(value){

                if(value=="")return true;
                var re = new RegExp("^.+\.("+exts+")$","i");

                if(!re.test(value))
                {
                    alert("Extension not allowed for file: \"" + value + "\"\nOnly these extensions are allowed: "+exts.replace(/\|/g,',')+" \n\n");
                    document.forms[0].uploadDocument.value="";
                    document.forms[0].uploadDocument.focus();

                    return false;
                }
                else{
                    return true;
                }
            }

            function testNew(value,radiovalue){

                if((document.getElementById("Approve").checked)&& (document.getElementById("Reject").checked)){
                    alert("You Need To Select Either Yes (Or) No");
                    document.getElementById(radiovalue).checked=false;
                }
                reasonFlag=value;
                buttonType=radiovalue;
                if(value){
                    document.getElementById("rem").style.display="none";
                    document.getElementById("res").style.display="block";
                }else{
                    document.getElementById("rem").style.display="block";
                    document.getElementById("res").style.display="none";
                }

            }
        </script>

        <title>SADAREM</title>
    </head>
    <body>

        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
        <html:form action="grievancesRequestDetails" enctype="multipart/form-data">
            <logic:present name="msg">
                <script language="javascript" type="text/javascript">
                    alert("${msg}");
                </script>
            </logic:present>
            <logic:present name="approvalList">
                <html:hidden property="mode"/>
                <html:hidden property="button"/>
                <br/>
                <%          boolean approveButtons = true;
                            boolean newCrtifictFlag = false;
                            int i = 1;
                            int isValid = 0;

                            if (request.getAttribute("isValid") != null) {
                                isValid = (Integer) request.getAttribute("isValid");
                            }
                            String validationMsg = null;
                            if (request.getAttribute("validationMsg") != null) {
                                validationMsg = (String) request.getAttribute("validationMsg");
                            }
                            ArrayList list = (ArrayList) request.getAttribute("approvalList");
                            Map map = (Map) list.get(0);

                %>
                <script>
                    function submitButtons(butType){


                        var success=1;
                        var submit=0;
                        var isValid='<%=isValid%>';
                        var validationMsg='<%=validationMsg%>';
                        var requestId='<%= map.get("RequestId")%>';
                        var status='<%= map.get("Status")%>';
                        var NOB='<%=map.get("Nature_Of_Beneficiary")%>';
                        var  ProofdocType='<%=map.get("Proofdoc_Type").toString()%>';
                        var uploadFlag  ='<%=map.get("uploadFlag").toString()%>';

                        if(status=="Application Registered" && NOB=="Voice"
                            && ((uploadFlag=="--" && requestId=="5") || (requestId=="1"))
                            && (document.getElementById("Approve").checked)){

                            if(document.forms[0].proofType.value==null || document.forms[0].proofType.value==""){
                                alert("Please Select Proof Type");
                                document.forms[0].proofType.focus();
                                return false;
                            }

                    <%--var proof_Id=document.forms[0].proofId.value;
                    if(proof_Id==null || proof_Id==""){
                        alert("Please Enter Proof ID");
                        document.forms[0].proofId.focus();
                        return false;
                    }--%>

                    <%--var leng=false;--%>
                    <%--if(document.forms[0].proofType.value=='Adhaar Card'){

                                var numbers = /^[0-9]+$/;


                                if(proof_Id.length==12){
                                    leng=true;
                                }

                                if((!leng)||(!proof_Id.match(numbers))) {
                                    alert("Please Enter Valid Adhaar Card.Adhaar Card Must have 12 digits");
                                    return false;
                                }
                            }
                            if(document.forms[0].proofType.value=='Ration Card'){
                                if(proof_Id.length==15){
                                    leng=true;
                                }
                                if(proof_Id.substring(0,3)!="WAP" && proof_Id.substring(0,3)!="PAP" && proof_Id.substring(0,3)!="AAY" &&
                                    proof_Id.substring(0,3)!="AAP" && proof_Id.substring(0,3)!="YAP" && proof_Id.substring(0,3)!="wap" &&
                                    proof_Id.substring(0,3)!="pap" && proof_Id.substring(0,3)!="aay" &&  proof_Id.substring(0,3)!="aap" &&
                                    proof_Id.substring(0,3)!="yap" && proof_Id.substring(0,3)!="TAP" && proof_Id.substring(0,3)!="RAP" &&
                                    proof_Id.substring(0,3)!="tap" && proof_Id.substring(0,3)!="rap"
                                    && proof_Id.substring(0,3)!="WAD" && proof_Id.substring(0,3)!="wad") {
                                    alert("Please Enter Valid Ration Card");
                                    return false;
                                }
                                if(!leng){
                                    alert("Please Enter Valid Ration Card ");
                                    return false;
                                }
                            }--%>
                            if(document.forms[0].elements['uploadDocument'].value=="") {
                                alert("Please Browse Documents!");
                                document.forms[0].elements['uploadDocument'].focus();
                                return false;
                            }
                        }

                        if(status=="MPDO Approved" || status=="PD Approved" ||status=="Documents Verified And Ok"){
                            if(document.forms[0].elements['schDate'].value==""){
                                alert("Please Enter Schedule Camp Date");
                                document.forms[0].elements['schDate'].focus();
                                return false;
                            }
                        }
                        if(butType!="Submit"){

                            buttonType=butType;
                        }else{
                            submit=1;
                            if(!(document.getElementById("Approve").checked) && !(document.getElementById("Reject").checked)){
                                alert("Please select checkbox Yes (or) No");
                                document.getElementById("Approve").focus();
                                return false;
                            }
                            if(isValid>0 && document.getElementById("Approve").checked){
                                alert(validationMsg);
                                return false;
                            }
                        }
                        if(document.forms[0].remarks.value==null || document.forms[0].remarks.value==""){

                            if(reasonFlag){
                                if(buttonType=="Approve"){
                                    alert("Please Enter Reason For Not Having RationCard");
                                }
                            }
                            else{
                                alert("Please Enter Remarks");
                            }
                            document.forms[0].remarks.focus();
                            return false;
                        }



                        if( submit==1){
                            document.getElementById('subButton').disabled = true;
                        }
                        if(buttonType=="Approve"){
                            if(submit==0 ){
                                document.getElementById('apprButton').disabled = true;
                                document.getElementById('rejButton').disabled = true;
                            }
                            if(status=="Application Registered" && NOB=="Voice"
                                        && ((uploadFlag==null && requestId=="5") || (requestId=="1"))){
                                if( checkExt3(document.forms[0].uploadDocument.value)){
                                    document.forms[0].button.value="Approved";
                                    document.forms[0].mode.value = "approveOrRejectDPMRequest";
                                    document.forms[0].submit();
                                    return true;
                                }}
                            else{

                                        document.forms[0].button.value="Approved";
                                        document.forms[0].mode.value = "approveOrRejectDPMRequest";

                                        document.forms[0].submit();
                                        return true;
                                    }
                                }else if(buttonType=="Reject"){
                                    if(submit==0 ){
                                        document.getElementById('apprButton').disabled = true;
                                        document.getElementById('rejButton').disabled = true;
                                    }
                                    if( status=="Application Registered" && NOB=="Voice"
                                        && (requestId=="1"
                                        ||requestId=="5"
                                        && uploadFlag==null)){
                                        document.getElementById('uploadDoc').disabled = true;
                                    }
                                    document.forms[0].button.value="Rejected";
                                    document.forms[0].mode.value = "approveOrRejectDPMRequest";
                                    document.forms[0].submit();
                                    return true;

                        }
                    }
                </script>


                <input type="hidden" name="reqId" value='<%=map.get("RequestId").toString()%>'/>
                <input type="hidden" name="subReqId" value='<%=map.get("SubRequestId").toString()%>'/>
                <html:hidden property="proofAadharType"  value='<%=map.get("Proofdoc_Type").toString()%>'/>

                <html:hidden property="benificiaryProblemId"  value='<%=map.get("BeneficiaryProblemId").toString()%>'/>
                <html:hidden property="status"  value='<%=map.get("Status").toString()%>'/>
                <html:hidden property="natureOfBeneficiary"  value='<%=map.get("Nature_Of_Beneficiary").toString()%>'/>
                <html:hidden property="uploadFlag"  value='<%=map.get("uploadFlag").toString()%>'/>
                <center> <logic:present name="uploadMessage">
                        <font  color="red"><bean:write name="uploadMessage"/></font>
                    </logic:present>
                </center>
                <table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">
                    <tr>
                        <th colspan="2">
                            Existing Personal Details
                        </th>
                    </tr>
                    <tr>
                        <td ><b><font color="blue">Request Number:</font></b></td>
                        <td ><b><font color="blue"><%=map.get("Beneficiary_Problem_ID")%></font></b></td>
                    </tr>
                    <tr>
                        <td >Name:</td>
                        <td ><%=map.get("Name")%></td>
                    </tr>
                    <tr>
                        <td>Age:</td>
                        <td><%=map.get("Age")%></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td><%=map.get("Gender")%></td>
                    </tr>
                    <tr>
                        <td>RelationName:</td>
                        <td><%=map.get("RelationName")%></td>
                    </tr>
                    <tr>

                        <td>Date of Birth:</td>
                        <td><%=map.get("Dob")%></td>
                    </tr>
                    <tr>
                        <td>Mole One:</td>
                        <td><%=map.get("Mole_One")%></td>
                    </tr>
                    <tr>

                        <td>Mole Two:</td>
                        <td><%=map.get("Mole_Two")%></td>
                    </tr>
                    <tr>
                        <td>District:</td>
                        <td><%=map.get("District")%></td>
                    </tr>
                    <tr>

                        <td>Mandal:</td>
                        <td><%=map.get("Mandal")%></td>
                    </tr>
                    <tr>
                        <td>Panchayat:</td>
                        <td><%=map.get("Panchayat")%></td>
                    </tr>
                    <tr>

                        <td>Village:</td>
                        <td><%=map.get("Village")%></td>
                    </tr>
                    <tr>
                        <td>Habitation:</td>
                        <td><%=map.get("Habitation")%></td>
                    </tr>
                    <tr>
                        <td>Mobile No.:</td>
                        <td><%=map.get("PhoneNumber")%></td>
                    </tr>
                </table>

                <br><table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">

                    <tr> <td colspan="2"><a href="#" onclick="window.open('grievancesRequestDetails.do?mode=getSADAREMCampReport&benificiaryProblemId=<%=map.get("BeneficiaryProblemId")%>&districtId=<%=map.get("DistrictId")%>&mandalId=<%=map.get("MandalId")%>&villageId=<%=map.get("VillageId")%>&panchayatId=<%=map.get("PanchayatId")%>&habitationId=<%=map.get("HabitationId")%>')">
                                <img src="./DisabilityUITG/images/RD.gif" height="30" width="450"/>
                            </a>
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
                <table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">
                    <br/>
                    <tr>
                        <th colspan="2" >Requested to Modified Details </th>
                    </tr>
                    <%}
                                if (subReqLength > 1) {
                                    String subIds[] = map.get("SubRequestId").toString().split(",");

                                    for (int x = 0; x < subIds.length; x++) {
                                        if (map.get("RequestId").toString().equals("1") && subIds[x].equals("1")) {%>

                    <tr>

                        <td >Surname:</td>
                        <td ><input type="text" size="25" name="Newsurname" value="<%=map.get("Newsurname")%>" class="in" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td>Name:</td>
                        <td><input type="text" size="25" name="Newname" value="<%=map.get("Newname")%>" class="in" readonly="true"/></td>
                    </tr>


                    <%}

                                                            if (map.get("RequestId").toString().equals("1") && subIds[x].equals("2")) {%>

                    <tr>
                        <td>Relation Name:</td>
                        <td><input type="text" size="25" name="Newrelationname" value="<%=map.get("Newrelationname")%>" class="in" readonly="true"/></td>
                    </tr>
                    <%-- <tr>

                    <td  align="center">Relation Type:</td>
                      <td  align="center"><input type="text" name="RelationType" value="<%=map.get("RelationType").toString()%>" readonly="true"/>

                      </td>
                </tr>--%>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && subIds[x].equals("3")) {%>

                    <tr>

                        <td  width="30%">Mole One:</td>
                        <td  width="70%" ><input type="text" size="25" name="Newmole1" value="<%=map.get("Newmole1")%>" class="in" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td>Mole Two:</td>
                        <td><input type="text" size="25" name="Newmole2" value="<%=map.get("Newmole2")%>" class="in" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && subIds[x].equals("4")) {%>

                    <tr>

                        <td>Date of Birth:</td>
                        <td><input type="text" size="25" name="Newdob" value="<%=map.get("Newdob")%>" class="in" readonly="true"/></td>
                    </tr>

                    <%
                                                            }
                                                        }
                                                    } else {

                                                        if (map.get("RequestId").toString().equals("1") && reqId.equals("1")) {%>

                    <tr>

                        <td>Surname:</td>
                        <td><input type="text" size="25" name="Newsurname" value="<%=map.get("Newsurname")%>" class="in" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td>Name:</td>
                        <td><input type="text" size="25" name="Newname" value="<%=map.get("Newname")%>" class="in" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("2")) {%>

                    <tr>

                        <td>Relation Name:</td>
                        <td><input type="text" size="25" name="Newrelationname" value="<%=map.get("Newrelationname")%>" class="in" readonly="true"/></td>
                    </tr>
                    <%-- <tr>

                    <td  align="center">Relation Type:</td>
                      <td  align="center"><input type="text" name="RelationType" value="<%=map.get("RelationType").toString()%>" readonly="true"/>

                      </td>
                </tr>--%>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("3")) {%>

                    <tr>

                        <td>Mole One:</td>
                        <td><input type="text" size="25"  name="Newmole1" value="<%=map.get("Newmole1")%>" class="in" readonly="true"/></td>
                    </tr>
                    <tr>

                        <td>Mole Two:</td>
                        <td><input type="text" size="25"  name="Newmole2" value="<%=map.get("Newmole2")%>" class="in" readonly="true"/></td>
                    </tr>

                    <%}%>
                    <%if (map.get("RequestId").toString().equals("1") && reqId.equals("4")) {%>

                    <tr>

                        <td>Date of Birth:</td>
                        <td><input type="text" size="25" name="Newdob" value="<%=map.get("Newdob")%>" class="in" readonly="true"/></td>
                    </tr>

                    <%}
                                }
                                if (flag) {%>
                </table>
                <%}%>


                <%if (map.get("Status") != null && map.get("Status").toString().equals("Application Registered")
                                    && map.get("Nature_Of_Beneficiary") != null
                                    && map.get("Nature_Of_Beneficiary").toString().equals("Voice")
                                    && map.get("uploadFlag").toString().equalsIgnoreCase("--")
                                    && (map.get("RequestId").toString().equals("1")
                                    || map.get("RequestId").toString().equals("5"))) {
                %>

                <table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">

                    <tr>
                        <th  colspan="2">Upload Document:</th>
                    </tr>
                    <tr>

                        <td >Proof Type:<font color="red"><b>*</b></font></td>
                        <td> <html:text property="proofType"  maxlength="20"   readonly="true"/>

                        </td>
                    </tr>
                    <tr>

                        <td >Proof ID:<font color="red"><b>*</b></font></td>
                        <td>
                            <html:text property="proofId"  maxlength="20"   readonly="true"/>
                        </td>
                    </tr>
                    <tr>

                        <td >Upload Documents<font color="red"><b>*</b></font></td>
                        <td ><html:file property="uploadDocument" styleId="uploadDoc" onchange="checkExt3(this.value);"/>
                        </td>
                    </tr>
                </table>
                <%}%>
                <% boolean state = true;

                            if (map.get("Status").toString().equals("Application Registered") && map.get("Nature_Of_Beneficiary").toString().equals("Voice") && map.get("uploadFlag").toString().equalsIgnoreCase("--") ) {
                                state = false;

                            }
                            if (map.get("Proofdoc_Type") != null && !(map.get("Proofdoc_Type").equals("")) && !(map.get("Proofdoc_Type").equals("-")) && state) {
                %>
                <br>
                <html:hidden property="proofType"  value='<%=map.get("Proofdoc_Type").toString()%>'/>

                <table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">

                    <tr>
                        <td  colspan="3">Proof of Document:</td>
                    </tr>
                    <tr>
                        <td > <%=map.get("Proofdoc_Type")%> :</td>
                        <td  style="color: #ff0000"> <%=map.get("Proof_Id")%></td>
                        <% if (map.get("ProofDoc_FileName") != null && !(map.get("ProofDoc_FileName").equals("")) && !(map.get("ProofDoc_FileName").equals("-"))) {
                        %>

                        <td id="gridbg" width="40%">
                            <a href="#" onclick="window.open('callCentreRequestsList.do?mode=viewDocument&fileName=<%=map.get("ProofDoc_FileName")%>&fileType=<%=map.get("Proofdoc_Type")%>&Proof_Id=<%=map.get("Proof_Id")%>&beneficiaryProblemId=<%=map.get("BeneficiaryProblemId")%>')">
                                View
                            </a>
                        </td>

                        <%}%>
                    </tr>

                </table>
                
                <%}%>


                <%if (map.get("Status") != null && (map.get("Status").toString().equals("MPDO Approved") || map.get("Status").toString().equals("PD Approved")
                                    || (map.get("Status").toString().equalsIgnoreCase("Documents Verified And Ok") && map.get("Proofdoc_Type") != null && map.get("Proofdoc_Type").toString().equalsIgnoreCase("Adhaar Card")
                                    && map.get("RequestId").toString().equalsIgnoreCase("5")))) {%>
                <br><table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">

                    <tr>
                        <td >Schedule Camp Date:<font color="red"><b>*</b></font></td>
                        <td ><html:text property="schDate" readonly="true" size="12" />
                            <a  href="javascript:show_calendar('forms[0].schDate');"
                                onmouseover="window.status='Date Picker';return true;"
                                onmouseout="window.status='';return true;">
                                <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                        </td>
                    </tr>

                </table>
                <%}%>
                <table align="center" border="0" cellpadding="0" cellspacing="1" width="70%" class="inputform">

                    <%if (map.get("Status") != null && map.get("Status").toString().equals("Application Registered") && map.get("Nature_Of_Beneficiary") != null && map.get("Nature_Of_Beneficiary").toString().equals("Voice")) {
                                    approveButtons = false;
                    %>

                    <tr>
                        <td width="35%">
                            <%if (map.get("RequestId").toString().equals("5")) {
                            %>
                            This Applicant has not been assesed in SADAREM camps before
                            <%} else {%>
                            Approve
                            <%}%>
                            <font color="red"><b>*</b></font>
                        </td>
                        <td width="70%" ><input style="width: 25px" type="checkbox" name="Approve"  id="Approve" value="Approve" onclick="testNew(<%=newCrtifictFlag%>,'Approve');"/>Yes&nbsp;
                            <input  style="width: 25px" type="checkbox" name="Reject"  id="Reject" value="Reject" onclick="testNew(false,'Reject');"/>No</td>
                    </tr>


                    <%
                    } else if (map.get("requestName") != null) {
                        if ((map.get("Status") != null && map.get("Status").toString().equals("Application Registered") && map.get("Nature_Of_Beneficiary") != null && map.get("Nature_Of_Beneficiary").toString().equals("Web"))
                                || (map.get("Status").toString().equals("Documents Uploaded"))) {

                            if (map.get("requestName").toString().equalsIgnoreCase("New Certificate")) {
                                newCrtifictFlag = true;
                                approveButtons = false;
                    %>
                    <tr>   <td width="35%">
                            Verified and found that the applicant is living at the address submitted in the proof of address document
                            <font color="red"><b>*</b></font>
                        </td>
                        <td width="70%"><input type="checkbox" name="Approve"  id="Approve" value="Approve" onclick="testNew(<%=newCrtifictFlag%>,'Approve');"/>Yes&nbsp;
                            <input type="checkbox" name="Reject"  id="Reject" value="Reject" onclick="testNew(false,'Reject');"/>No</td>
                    </tr>


                    <%                                                                            } else {
                                                    approveButtons = false;
                    %>
                    <tr>   <td width="30%">
                            Documents Verified <font color="red"><b>*</b></font>
                        </td>
                        <td width="70%"><input type="checkbox" name="Approve"  id="Approve" value="Approve" onclick="testNew(<%=newCrtifictFlag%>,'Approve');"/>Yes&nbsp;
                            <input type="checkbox" name="Reject"  id="Reject" value="Reject" onclick="testNew(false,'Reject');"/>No</td>
                    </tr>
                    <%                                                                            }
                        }

                    }
                    %>
                    <tr>
                        <td >
                            <div id="rem" style="display: block;">
                                Remarks: <font color="red"><b>*</b></font>
                            </div>
                            <div id="res" style="display:none;">
                                Reason For Not Having RationCard: <font color="red"><b>*</b></font>
                            </div>


                        </td>
                        <td width="70%">

                            <html:textarea property="remarks" style="width:100%;height:50px;border:1px solid #A4A4A4;font-size:12px;font-weight:normal;font-family:arial;" onkeydown="textCounter(this,document.forms[0].remLen1,500)" onkeyup="textCounter(this,document.forms[0].remLen1,500)"/>
                            <br><input readonly type="text" name="remLen1" size="3" maxlength="3" value="500" style="width:20px"/>
                        </td>
                    </tr>

                    <tr>

                        <%
                                    if (approveButtons) {
                        %>
                        <td  colspan="2">
                            <input type="button"  value="Approve"  Id="apprButton" styleClass="button"  onclick="return submitButtons('Approve');" class="in"/>
                            <input type="button"  value="Reject"  Id="rejButton" styleClass="button"  onclick="return submitButtons('Reject');" class="in"/>
                        </td>
                        <%   } else {
                        %>
                        <td colspan="2">
                            <input type="button"  value="Submit" Id="subButton" styleClass="button"  onclick="return submitButtons('Submit');" class="in"/>

                        </td>
                        <%                                            }
                        %>



                    <tr/>
                </table>

            </logic:present>
            <logic:present name="closewindow">
                <script>
                    window.opener.location.reload();
                    window.close();
                </script>
            </logic:present>
        </html:form>
    </body>
</html>


