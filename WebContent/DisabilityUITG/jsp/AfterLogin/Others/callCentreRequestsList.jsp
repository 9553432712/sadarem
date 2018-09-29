<%-- 
    Document   : callCentreRequestsList
    Created on : May 16, 2013, 11:13:13 AM
    Author     : 728056
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script>
           
            function getRequestDetails(){

                document.forms[0].mode.value="getAllRequestedDetails";
                document.forms[0].submit();


            }
            function validateCheckBox(buttonType){
                var checkCount = 0;
                var sCheck = document.getElementsByName("beneficiaryIds");
                for(var i=0;i<sCheck.length;i++){
                    if(sCheck[i].checked){
                        checkCount++;

                    }
                }
               
                if(checkCount==0){
                    alert("Select Atleast One checkbox");
                    return false;
                }else
                {
                    if(buttonType=="Approve"){
                        document.forms[0].button.value="Approved";
                        document.forms[0].mode.value = "approveOrRejectRequest";
                        document.forms[0].submit();
                    }else if(buttonType=="Reject"){
                        document.forms[0].button.value="Rejected";
                        document.forms[0].mode.value = "approveOrRejectRequest";
                        document.forms[0].submit();
                    }
              

                }
            }
            function checkedAll(element){

                var CheckBoxArray = document.getElementsByName("beneficiaryId1");
                if(element.checked == true){
                    for(var i=0;i<CheckBoxArray.length;i++){
                        CheckBoxArray[i].checked = true;

                    }
                }
                else{
                    for(var i=0;i<CheckBoxArray.length;i++){
                        CheckBoxArray[i].checked = false;
                    }
                }
            }
            function Pager(tableName, itemsPerPage) {
                this.tableName = tableName;
                this.itemsPerPage = itemsPerPage;
                this.currentPage = 1;
                this.pages = 0;
                this.inited = false;

                this.showRecords = function(from, to) {
                    var rows = document.getElementById(tableName).rows;
                    // i starts from 1 to skip table header row
                    for (var i = 1; i < rows.length; i++) {
                        if (i < from || i > to)
                            rows[i].style.display = 'none';
                        else
                            rows[i].style.display = '';
                    }
                }

                this.showPage = function(pageNumber) {
                    if (! this.inited) {
                        alert("not inited");
                        return;
                    }

                    var oldPageAnchor = document.getElementById('pg'+this.currentPage);
                    oldPageAnchor.className = 'pg-normal';

                    this.currentPage = pageNumber;
                    var newPageAnchor = document.getElementById('pg'+this.currentPage);
                    newPageAnchor.className = 'pg-selected';

                    var from = (pageNumber - 1) * itemsPerPage + 1;
                    var to = from + itemsPerPage - 1;
                    this.showRecords(from, to);
                }

                this.prev = function() {
                    if (this.currentPage > 1)
                        this.showPage(this.currentPage - 1);
                }

                this.next = function() {
                    if (this.currentPage < this.pages) {
                        this.showPage(this.currentPage + 1);
                    }
                }

                this.init = function() {
                    var rows = document.getElementById(tableName).rows;
                    var records = (rows.length - 1);
                    this.pages = Math.ceil(records / itemsPerPage);
                    this.inited = true;
                }

                this.showPageNav = function(pagerName, positionId) {
                    if (! this.inited) {
                        alert("not inited");
                        return;
                    }
                    var element = document.getElementById(positionId);

                    var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal">  Prev </span> | ';
                    for (var page = 1; page <= this.pages; page++)
                        pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
                    pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next </span>';

                    element.innerHTML = pagerHtml;
                }
            }

        </script>

        <title>SADAREM</title>
    </head>
    <body>
        <html:form action="callCentreRequestsList">
            <html:hidden property="mode"/>
            <html:hidden property="button"/>

            <logic:present name="msg1">
                <script language="javascript" type="text/javascript">
                    alert("${msg1}");
                </script>
            </logic:present>
            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>

            <table align="center"  cellspacing="0" cellpadding="0" class="inputform" width="90%">
                <tr>

                    <td  style=" font-weight: bold" colspan="2">
                        <font COLOR="RED" size="2"> <marquee >Grievances New Cerificate Request Aadhar Card Number has been validated for the request raised from 11/09/2014 in RDCall Center and Meeseva. Document is not required for approval</marquee></font>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">RDCall Center Requests</th>
                </tr>

                <tr>
                    <td>Request In

                        <html:select property="requestIn">
                            <html:option  value="SADAREM" >SADAREM</html:option>

                        </html:select></td>
                    <td> Request Type

                        <html:select property="requestId" >
                            <html:option  value="ALL">--ALL--</html:option>
                            <html:optionsCollection property="requestList" label="requestName" value="requestId"/>
                        </html:select></td>

                </tr>
                <tr>
                    <th colspan="2"><html:button  property="sub" value="Submit" onclick="getRequestDetails();"/>
                    </th>
                </tr>
            </table>

            <br/>
            <%int i = 1;%>

            <logic:notEmpty name="RequestedList" scope="request">
                <div style="overflow:auto; width:900px; height:308px; vertical-align: middle">
                    <table  border="0" align="center" class="inputform" cellpadding="0" cellspacing="1"  width="90%">
                        <tr>
                            <th >
                                SNo.
                            </th>
                            <th>Request Number</th>
                            <th>SADAREM ID</th>
                            <th>Name </th>
                            <th>Relation Name </th>
                            <th>Mandal</th>
                            <th>Request Type</th>
                            <th>Proof Type</th>
                            <th>Proof ID</th>
                            <%--<th  >Current Status</th>--%>
                            <th>Action</th>
                            <%-- <td  ><font size="2"><b>Approve All</b></font><html:checkbox property="beneficiaryIds" styleId="beneficiaryId"  onclick="checkedAll(this)"/></td>--%>


                        </tr>
                        <logic:iterate id="row" name="RequestedList">
                            <tr>
                                <td >
                                    <%=i++%>.
                                </td>
                                <td>${row.BeneficiaryProblemId}</td>
                                <td>${row.SadaremId}</td>
                                <td>${row.Name}</td>
                                <td>${row.RelationName}</td>
                                <td>${row.Mandal}</td>
                                <td>${row.requestName}</td>
                                <td>${row.Proofdoc_Type}</td>
                                <td>${row.Proof_Id}</td>
                                <%--<td>${row.Status}</td>--%>
                                <td>
                                    <a href="#" onclick="window.open('callCentreRequestsList.do?mode=getAllRequestedDetails&beneficiaryId=${row.BeneficiaryProblemId}')">

                                        Action </a>
                                </td>
                                <%--<td ><html:checkbox property="beneficiaryIds" styleId="beneficiaryId1${i}" value="${row.BeneficiaryProblemId}" />--%>
                            </tr>

                        </logic:iterate>

                    </table>
                </div>
                <br/>
                <div id="pageNavPosition"></div>

            </logic:notEmpty>

        </html:form>
        <script type="text/javascript">
            var pager = new Pager('results',20);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        </script>
    </body>
</html>