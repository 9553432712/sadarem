<%--
    Document   : grievancesDPMApprove
    Created on : May 15, 2013, 4:37:59 PM
    Author     : 310926
--%>


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*,java.text.*"%>
<%@page session="true"%>


<html:html>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <head>
        <script type="text/javascript">


            function getDetails(){
                document.forms[0].mode.value="grievancesDPMApprovalList";
                document.forms[0].submit();


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


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>

    <body>
        <html:form action="grievancesRequestDetails.do">
            <html:hidden property="mode"/>

            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>

            <table  cellspacing="0" cellpadding="5" class="inputform" width="90%" border="0" align="center">
                <tr>

                    <td  style=" font-weight: bold" colspan="6" >
                        <font COLOR="RED" size="2"> <marquee >For New Cerificate Request, Aadhar Card Number has been validated for the request raised from 11/09/2014 in RDCall Center and Meeseva. Document is not required for approval</marquee></font>
                    </td>
                </tr>

                <tr>
                    <th colspan="6">RDCall Center Requests Approval</th>
                </tr>

                <tr>
                    <td>Request In</td>
                    <td>
                        <html:select property="requestIn"  style="width:150px;height:25px;font-size:11px;" >
                            <html:option value="SADAREM" >SADAREM</html:option>
                        </html:select></td>
                    <td>Request Type</td>
                    <td>

                        <html:select property="requestId"  style="width:250px;height:25px;font-size:11px;" >
                            <html:option  value="ALL">-- ALL --</html:option>
                            <html:optionsCollection property="requestList" label="requestName" value="requestId"/>
                        </html:select>

                    </td>
                    <td>Request Status</td>
                    <td><html:select property="status" style="width:150px;height:25px;font-size:11px;" >
                            <html:option  value="ALL">-- ALL --</html:option>
                            <html:option value="DUP">Documents Upload Pending</html:option>
                            <html:option value="DVP">Documents Verification Pending</html:option>
                            <html:option value="SCP">Schedule Camp Pending</html:option>
                            <html:option value="CIP">Certificate Issue Pending</html:option>
                        </html:select></td>
                </tr>
                <tr>

                    <th colspan="6"><html:button  property="sub" value="Submit" onclick="getDetails();"/>
                    </th>
                </tr>
            </table>

            <br>

            <%--</td>
        </tr>
    </table>--%>
            <%int i = 1;%>

            <logic:notEmpty name="approvalList">
                <table border="0" cellpadding="0" cellspacing="1" width="90%" class="inputform" align="center">
                    <tr>
                        <th>
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
                        <%--<th>Current Status</th>--%>
                        <th>Action</th>


                    </tr>
                    <logic:iterate id="row" name="approvalList">
                        <tr>
                            <td>
                                <%=i++%>.
                            </td>
                            <td>${row.Beneficiary_Problem_ID}</td>
                            <td>${row.SadaremId}</td>
                            <td>${row.Name}</td>
                            <td>${row.RelationName}</td>
                            <td>${row.Mandal}</td>
                            <td>${row.requestName}</td>
                            <td>${row.Proofdoc_Type}</td>
                            <td>${row.Proof_Id}</td>
                            <%--<td>${row.Status}</td>--%>
                            <td>
                                <a href="#" onclick="window.open('grievancesRequestDetails.do?mode=grievancesDPMApprovalList&BenificiaryProblemId=${row.Beneficiary_Problem_ID}&reqId=${row.RequestId}&subReqId=${row.SubRequestId}&ProofdocType=${row.Proofdoc_Type}&flag=Action')">Action</a>
                            </td>

                        </tr>

                    </logic:iterate>
                </table>
                <div id="pageNavPosition"></div>
            </logic:notEmpty>

            <logic:present name="closewindow">
                <script>
                    window.opener.location.reload();
                    window.close();
                </script>
            </logic:present>
        </html:form>
        <script type="text/javascript">
            var pager = new Pager('results',20);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        </script>
    </body>

</html:html>
