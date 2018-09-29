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


        <style type="text/css">
            .pg-normal {
                color: #0000FF;
                font-weight: normal;
                font-family:Verdana, Arial, Helvetica, sans-serif;
                font-size:11px;
                text-decoration: none;
                cursor: pointer;
                text-indent:50px;
            }

            .pg-selected {
                color: #800080;
                font-weight: normal;
                font-size:14px;
                text-decoration: underline;
                cursor: pointer;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
    </head>
    
    <body>
        <html:form action="grievancesRequestDetails.do">
            <html:hidden property="mode"/>
            <br>
            <logic:present name="msg">
                <center><font color="red">${msg}</font></center>
            </logic:present>

            <table  align="center" cellspacing="0" cellpadding="0" id="grid" width="90%" border="1">
                <tr  align="center">
                    <td colspan="6" align="center"><h3>RDCall Center Requests Approval</h3></td>
                </tr>
                <tr>
                    <td align="right">Request In :</td>
                    <td align="left" >
                        <html:select property="requestIn"  style="width:150px;height:25px;font-size:11px;" >
                            <html:option value="SADAREM" >SADAREM</html:option>
                        </html:select></td>
                    <td align="right">Request Type</td>
                    <td align="left">
                        <html:select property="requestId"  style="width:250px;height:25px;font-size:11px;" >
                            <html:option  value="ALL">--- ALL ---</html:option>
                            <html:optionsCollection property="requestList" label="requestName" value="requestId"/>
                        </html:select>

                    </td>
                </tr>
                <tr>
                    <td align="right">Request Status :</td>
                    <td align="left"><html:select property="status"  style="width:150px;height:25px;font-size:11px;" >
                            <html:option  value="ALL">--- ALL ---</html:option>
                            <html:option value="DUP">Documents Upload Pending</html:option>
                            <html:option value="DVP">Documents Verification Pending</html:option>
                            <html:option value="SCP">Schedule Camp Pending</html:option>
                            <html:option value="CIP">Certificate Issue Pending</html:option>
                        </html:select></td>

                </tr>
                <tr>
                    <td colspan="6" align="center"><html:button  property="sub" value="Submit" onclick="getDetails();"/>
                    </td>
                </tr>
            </table>

            <br>

            <%int i = 1;%>

            <logic:notEmpty name="approvalList">
                <table class="table" align="center" border="1" cellpadding="0" cellspacing="0" width="90%">
                    <tr>
                        <th align="center" class="formhdbg">
                            SNo.
                        </th>
                        <th class="formhdbg" align="center">Request Number</th>
                        <th class="formhdbg" align="center">SADAREM ID</th>
                        <th class="formhdbg" align="center">Name </th>
                        <th class="formhdbg" align="center">Relation Name </th>
                        <th class="formhdbg" align="center">Mandal</th>
                        <th class="formhdbg" align="center">Request Type</th>
                        <th class="formhdbg" align="center">Proof Type</th>
                        <th class="formhdbg" align="center">Proof ID</th>
                        <th class="formhdbg" align="center">Action</th>
                    </tr>
                    <logic:iterate id="row" name="approvalList">
                        <tr>
                            <td class="formaltbg" align="center">
                                <%=i++%>.
                            </td>
                            <td class="formaltbg" align="center">${row.Beneficiary_Problem_ID}</td>
                            <td class="formaltbg" align="center">${row.SadaremId}</td>
                            <td class="formaltbg">${row.Name}</td>
                            <td class="formaltbg">${row.RelationName}</td>
                            <td class="formaltbg">${row.Mandal}</td>
                            <td class="formaltbg">${row.requestName}</td>
                            <td class="formaltbg">${row.Proofdoc_Type}</td>
                            <td class="formaltbg" align="center">${row.Proof_Id}</td>
                            <%--<td class="formaltbg">${row.Status}</td>--%>
                            <td class="formaltbg" align="center">
                                <a href="#" onclick="window.open('grievancesRequestDetails.do?mode=grievancesDPMApprovalList&BenificiaryProblemId=${row.Beneficiary_Problem_ID}&reqId=${row.RequestId}&subReqId=${row.SubRequestId}&flag=Action')">Action</a>
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
