<%--
    Document   : RationCardServiceReport
    Created on : Dec 21, 2011, 12:47:40 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="org.bf.disability.dto.RequestInformationDTO"%>
<%@page import="java.util.ArrayList"%>

<%
            int j = 1;
            int i = 1;
            //   String requestTypeDetails = request.getParameter("requestTypeDetails");

            String statusIds = null;
            String single = null;
            String multiple = null;
            String requestTypeId = null;

            statusIds = request.getParameter("status");
            String statusIdFormat = (String) request.getAttribute("status");
            String requestType = (String) request.getAttribute("requestType");

            ArrayList cc = null;
            cc = (ArrayList) request.getAttribute("cc");
            int length = 0;
            if (cc != null) {
                length = cc.size();
            }


            /* if(request.getParameter("status").equals("Approval")){
            request.setAttribute("Approval", "Approval");
            }else if(request.getParameter("status").equals("Reject")){
            request.setAttribute("Approval", "Approval");
            }else if(request.getParameter("status").equals("Pending")){
            request.setAttribute("Pending", "Pending");
            }*/

              single = (String)request.getAttribute("single");
             multiple =(String)request.getAttribute("multiple");
             requestTypeId = (String) request.getAttribute("requestTypeId");


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
    <link rel="stylesheet" href="./DisabilityUITG/css/Sadarem-Style.css" type="text/css">
    <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

    <script>

        function submitValues(){


              var chosen = "";
                var len = document.forms[0].multiple.length;
                var elems = document.forms[0].multiple;
                if(len>0){
                    for(var i=0;i<len;i++){
                        if(elems[i].checked){
                            chosen = elems[i].value;
                        }
                    }
                }
                if(chosen==2) {
                     if(document.forms[0].elements['requestTypeDetails'].value=="") {
                alert("Please Select RequestType!");
                document.forms[0].elements['requestTypeDetails'].focus();
                return false;
            }

                }
                if(chosen==""){
                  alert("Please Select any Option!");
                    return false;
                }


             else if(document.forms[0].elements['status'].value=="") {
                alert("Please Select Status!");
                document.forms[0].elements['status'].focus();
                return false;
            }else {

                document.forms[0].mode.value ="getRequestStatusDetails";
                document.forms[0].submit();
                }

        }

       function updateDetails(){
 alert("p");
            var p =false;
            var length=<%=length%>;

                for(var i=1;i<=length ;i++)
                {

               alert(document.getElementById('approval'+i).checked);
               alert(document.getElementById('approval1'+i).checked);
                    if(document.getElementById('approval'+i).checked || document.getElementById('approval1'+i).checked )
                    {
                        alert(i);
                        p = true;
                    }
                }
                alert(p);
            if(p==false)
            {
                alert("Please Select AtleastOne!");
                return false;
            }
            else{
                document.forms[0].mode.value ="updateApprovalDetails";
                document.forms[0].submit();
            }
        }



        function approvalDetails(){
            document.forms[0].mode.value ="approvalStatusDetails";
            document.forms[0].approvalId.value ="Approval";
            document.forms[0].submit();

        }

        function rejectDetails(){
            document.forms[0].mode.value ="rejectStatusDetails";
            document.forms[0].rejectId.value ="Reject";

            document.forms[0].submit();

        }
        function assgnStatus(index,status1,status2){
            alert();
            if(document.forms[0].hiddenlist.value==1){
                document.forms[0].recordStatus1.value =status1+"#"+status2;

            }else{
                document.forms[0].recordStatus1[index-1].value = status1+"#"+status2;
                document.forms[0].hiddenlist.value;
            }
        }

            function multipleData(val){

                  if( val.value==1 ){
                     document.getElementById('requestSingleId').style.display='none';
                     document.getElementById('requestSingle1Id').style.display='none';

                  }
                  else if(<%=single%>==2 || val.value==2){

                    document.getElementById('requestSingleId').style.display='';
                    document.getElementById('requestSingle1Id').style.display='';
                  }

            }

        <%--function multipleData(){
            alert("--multi-");


            if(document.forms[0].elements['multiple'].value="1"){
                alert("--123===-");
                document.getElementById('requestSingleId').style.display='none';
            }
        }
        function singleData(){
            alert("--single-");
            if(document.forms[0].elements['multiple'].value="2"){
                alert("--123===-");
                document.getElementById('requestSingleId').style.display='';
            }
        }--%>
    </script>

    <body onload="multipleData(1);">
        <html:form  action="/requestInformation">
            <html:hidden property="mode"/>
            <html:hidden property="hiddenlist"/>
            <html:hidden property="approvalId"/>
            <html:hidden property="rejectId"/>
            <html:hidden property="requestTypeStatusId" value="<%=requestTypeId%>"/>

            <table cellpadding="0" cellspacing="0" align="center">
                <br/><br/>


                <tr align="center">
                    <td>
                        Request Type Information Details
                    </td>
                </tr>
                <tr align="center">
                    <td>
                        <logic:present name="duplicateDetails">
                            <center><font color="blue">${duplicateDetails}</font></center>
                        </logic:present>

                        <logic:present name="raisedRequest">
                            <center><font color="blue">${raisedRequest}</font></center>
                        </logic:present>
                        <logic:present name="approval">
                            <center><font color="blue">${approval}</font></center>
                        </logic:present>
                        <logic:present name="editError">
                            <center><font>< color="blue">${editError}</font></center>
                        </logic:present>
                        <logic:present name="msg">
                            <center><font color="red">${msg}</font></center>
                        </logic:present>
                    </td>
                </tr>

            </table>

            <table cellpadding="1" cellspacing="8" align="center" width="60%" class="innerTable">
                <br/>
                <tr>
                    <td  align="center" class="label">
                        Personal Information

                        <html:radio property="multiple" value="1" styleId="multipleId"  onclick="multipleData(this);"/>
                    </td>

                    <td  align="center" class="label">
                        New Services

                            <html:radio property="multiple" value="2" styleId="multipleIdData"  onclick="multipleData(this);"/>
                    </td>


                </tr>
                <tr>
                    <td  align="center" class="label" id="requestSingleId" style="display: none">
                        Request Type
                    </td>

                    <td id="requestSingle1Id" style="display:none">
                        <html:select property="requestTypeDetails">
                            <html:option value="">--Select--</html:option>
                            <html:optionsCollection property="requestTypeList" label="requestTypeName" value="requestTypeId"/>
                        </html:select>
                    </td>

                    <td  align="center" class="label">
                        Status
                    </td>

                    <td>
                        <html:select property="status">
                            <html:option value="">--Select--</html:option>
                            <html:option value="Pending">Pending</html:option>
                            <html:option value="Approval">Approval</html:option>
                            <html:option value="Reject">Reject</html:option>

                        </html:select>
                    </td>
                </tr>
            </table>
            <table align="center" cellspacing="0" cellpadding="0" width="92%">
                <br/>
                <tr>
                    <td align="center">
                        <html:button property="but" value="Submit" onclick="return submitValues();"/>
                    </td>
                </tr>
                <tr>
                    <td class="label" valign="middle">

                        <logic:notEmpty name="requestInformationList">

                            <table  align="center" cellspacing="0" border="0" cellpadding="0" class="innerTable" width="90%">
                                <br/>
                                <tr>

                                    <td class="formhdbg" align="center">
                                        SNo.
                                    </td>
                                    <td class="formhdbg" align="center">
                                        RequestId
                                    </td>
                                    <logic:present name="personCodeValidate">

                                        <logic:equal name="personCodeValidate" value="0">
                                            <td class="formhdbg" align="center">
                                                SADAREM ID
                                            </td>
                                        </logic:equal>
                                    </logic:present>



                                    <td class="formhdbg" align="center">
                                        Name
                                    </td>
                                    <td class="formhdbg" align="center">
                                        Relation Name
                                    </td>

                                    <td class="formhdbg" align="center">
                                        Address
                                    </td>

                                    <td class="formhdbg" align="center">
                                        RegistrationDate
                                    </td>

                                     <td class="formhdbg" align="center">
                                        RequestTypeName
                                    </td>


                                    <logic:present name="otherDetails">
                                        <logic:present name="Pending">
                                            <td class="formhdbg" align="center">
                                                Approval
                                            </td>
                                            <td class="formhdbg" align="center">
                                                Reject
                                            </td>

                                        </logic:present>
                                    </logic:present>

                                    <logic:present name="requestType">
                                        <logic:equal name="requestType" value="1">
                                            <td class="formhdbg" align="center">
                                                SSC Documents
                                            </td>
                                            <td class="formhdbg" align="center">
                                                Address Document
                                            </td>
                                        </logic:equal>

                                    </logic:present>


                                    <logic:present name="newCertificate">
                                        <logic:present name="Pending">
                                            <td class="formhdbg" align="center">
                                                view
                                            </td>
                                        </logic:present>
                                    </logic:present>

                                     <logic:present name="requestsingle">
                                         <logic:equal name="requestsingle" value="1">
                                            <td class="formhdbg" align="center">
                                                FIR Documents
                                            </td>
                                        </logic:equal>
                                    </logic:present>

                                </tr>

                                <logic:iterate name="requestInformationList" id="row" scope="request" indexId="Sno">
                                    <tr>

                                        <td class="formaltbg">
                                            <%=Sno + 1%>.

                                            <input type="hidden" name="requestHiddenId" value="<bean:write name="row" property="requestId"/>"/>

                                        <td class="formaltbg">
                                            <bean:write name="row" property="requestId"/>
                                        </td>

                                        <logic:present name="personCodeValidate">

                                            <logic:equal name="personCodeValidate" value="0">
                                                <td class="formaltbg">
                                                    <bean:write name="row" property="personCode"/>
                                                </td>
                                            </logic:equal>
                                        </logic:present>


                                        <td class="formaltbg">
                                            <bean:write name="row" property="name"/>
                                        </td>
                                        <td class="formaltbg">
                                            <bean:write name="row" property="relationName"/>
                                        </td>
                                        <td class="formaltbg">
                                            <bean:write name="row" property="houseNO"/>,

                                            <bean:write name="row" property="habitationName"/>,

                                            <bean:write name="row" property="villageName"/>,

                                            <bean:write name="row" property="mandalName"/>,

                                            <bean:write name="row" property="districtName"/>.
                                        </td>

                                        <td class="formaltbg">
                                            <bean:write name="row" property="regDate"/>
                                        </td>
                                         <td class="formaltbg">
                                            <bean:write name="row" property="multipleReqName"/>
                                        </td>

                                        <logic:present name="otherDetails">
                                            <logic:present name="Pending">
                                                <td class="formaltbg">
                                                    <html:hidden property="recordStatus1"/>
                                                    <html:hidden property="recordStatus2"/>

                                                    <input style="border:0px" type="radio"  name="approval<%=Sno + 1%>" id="approval<%=Sno + 1%>" value="<bean:write name="row" property="requestId" />" onclick="javascript: assgnStatus('<%=Sno + 1%>','Approval',this.value);"/>
                                                </td>
                                                <td class="formaltbg">
                                                    <input type="radio" style="border:0px" name="approval<%=Sno + 1%>" id="approval1<%=Sno + 1%>" value="<bean:write name="row" property="requestId"/>" onclick="javascript: assgnStatus('<%=Sno + 1%>','Reject',this.value);"/>
                                                </td>

                                                <%--<td class="formaltbg">
  <bean:write name="row" property="requestId"/>000=== <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<bean:write name="row" property="requestId"/>&personCodeId=<bean:write name="row" property="personCode"/>')">View</a>
                                                </td>--%>

                                            </logic:present>
                                        </logic:present>

                                        <logic:present name="requestType">
                                            <logic:equal name="requestType" value="1">
                                                <td class="formaltbg" align="center">
                                                    <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<bean:write name="row" property="requestId"/>&personCodeId=<bean:write name="row" property="personCode"/>&flag=1')">View</a>
                                                </td>
                                                <td class="formaltbg" align="center">
                                                    <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<bean:write name="row" property="requestId"/>&personCodeId=<bean:write name="row" property="personCode"/>&flag=2')">View</a>
                                                </td>
                                            </logic:equal>

                                        </logic:present>

                                        <logic:present name="newCertificate">
                                            <logic:notPresent name="Pending">
                                                <logic:notPresent name="Approval">
                                                    <logic:notPresent name="Reject">

                                                        <td class="formaltbg">
                                                            <html:hidden property="recordStatus1"/>
                                                            <html:hidden property="recordStatus2"/>

                                                            <input type="radio<%=Sno + 1%>"  id="approval<%=Sno + 1%>" name="approval<%=Sno + 1%>"  value="<bean:write name="row" property="requestId"/>" onclick="javascript: assgnStatus('<%=Sno + 1%>','Approval',this.value);"/>
                                                        </td>
                                                        <td class="formaltbg">
                                                            <input type="radio1<%=Sno + 1%>" id="approval1<%=Sno + 1%>" name="approval<%=Sno + 1%>" value="<bean:write name="row" property="requestId"/>" onclick="javascript: assgnStatus('<%=Sno + 1%>','Reject',this.value);"/>
                                                        </td>
                                                    </logic:notPresent>
                                                </logic:notPresent>
                                            </logic:notPresent>
                                        </logic:present>


                                        <logic:present name="requestsingle">
                                            <logic:equal name="requestsingle" value="1">
                                                <td class="formaltbg" align="center">
                                            <a href="#" onclick="window.open('requestInformation.do?mode=getPhotoDetails&photoId=<bean:write name="row" property="requestId"/>&personCodeId=<bean:write name="row" property="personCode"/>&flag=1')">View</a>
                                                </td>
                                            </logic:equal>

                                        </logic:present>


                                        <logic:present name="newCertificate">
                                            <logic:present name="Pending">

                                                <td class="formaltbg">
                                                    <a href="requestInformation.do?mode=getNewCertificateDetails&requestTypeIdDetails=<bean:write name="row" property="requestTypeIdDataFormat"/>&statusId=<%=statusIds%>&requestDetailsId=<bean:write name="row" property="requestId"/>">View</a>
                                                </td>

                                            </logic:present>
                                        </logic:present>
                                    </tr>
                                </logic:iterate>
                            </table>

                            <br/>
                            <table align="center">

                                <tr>
                                    <logic:present name="status">
                                        <logic:equal name="status" value="1">
                                            <td align="center" colspan="8">
                                                <html:submit property="but" value="Update" onclick="updateDetails(this.value);"/>
                                            </td>
                                        </logic:equal>
                                    </logic:present>

                                    <logic:present name="newCertificate">
                                        <td align="center" colspan="8"> &nbsp;&nbsp;</td>
                                    </logic:present>

                                </tr>
                            </table>

                        </logic:notEmpty>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
