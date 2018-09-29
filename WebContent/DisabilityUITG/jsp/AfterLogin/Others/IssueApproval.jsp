<%--
    Document   : IssueApproval
    Created on : Nov 29, 2012, 2:25:48 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page  import="java.util.ArrayList"%>
<%
            int i = 1;
            int x = 0;

            String cat17Id = null;
            String catId = "";

            if (request.getAttribute("cat17Id") != null) {
                cat17Id = (String) request.getAttribute("cat17Id");
                if (cat17Id.equals("17")) {
                    catId = cat17Id;
                }
            }
            String statusRequest = null;
            if (request.getAttribute("statusRequest") != null) {
                statusRequest = (String) request.getAttribute("statusRequest");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

        <script>
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function getMedicalBoardList(){
                document.forms[0].mode.value='getBoardList';
                document.forms[0].submit();
            }
            function getapprovedList(){
                document.forms[0].mode.value='raisedDetails';
                document.forms[0].submit();
            }
        </script>
        
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script type="text/javascript">

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

            function getCategoryId(id){

                if(id==17){
                    document.getElementById('status3').style.display='block';
                    document.getElementById('status2').style.display='none';
                }else{
                    document.getElementById('status2').style.display='block';
                    document.getElementById('status3').style.display='none';
                }

            }

        </script>
    </head>
    <body>
        <html:form action="/issueRaiseApproval" >
            <html:hidden property="mode"/>
            <html:hidden property="statusRequest" value="<%=statusRequest%>"/>

            <logic:present name="msg">
                <p id="errmsg">${msg}</p>
            </logic:present>

            <logic:present name="msg1">
                <script language="javascript" type="text/javascript">
                    alert("${msg1}");
                </script>
            </logic:present>

            <table border="0" cellspacing="1" cellpadding="0" width="90%" align="center" class="inputform">
                <tr>
                    <th colspan="4">
                        Issue Resolve
                    </th>
                </tr>
                <tr>
                    <td  width="8%">District :</td>
                    <td align="left">
                        <html:select styleId="1" style="height:25px;" property="districtId" onchange="getMedicalBoardList();" style="width:260px">
                            <html:option value="0">-- ALL --</html:option>
                            <html:optionsCollection property="districtList" label="district_name" value="district_id"/>

                        </html:select>
                    </td>
                    <%-- <td  width="20%">Medical Board :</td>
                    <td >
                        <html:select property="medicalBoardId" style="height:25px;" >
                            <html:option value="0">-- ALL --</html:option>
                            <html:optionsCollection property="medicalBoardList" label="campName" value="campId"/>
                        </html:select>
                    </td>
                </tr>--%>

                    <td  width="8%">Category :</td>
                    <td >
                        <html:select styleId="3" property="categoryFormId"  onchange="getCategoryId(this.value);" style="width:260px">
                            <html:option value="0">-- ALL --</html:option>
                            <html:optionsCollection property="categoryList" label="categoryname" value="categoryId"/>
                        </html:select>
                    </td>
                </tr>
                <tr>

                    <td  align="left" width="16%">Status :</td>

                    <td align="left" >
                        <html:select  property="status" style="width:150px">
                            <html:option value="Pending">Pending</html:option>
                            <html:option value="Resolved">Resolved</html:option>
                            <html:option value="Rejected">Reject</html:option>
                            <html:option value="Hold">Hold</html:option>
                        </html:select><br/>
                    </td>

                    <td  align="left" width="16%">Issue ID :</td>
                    <td align="left" >
                        <html:text property="id" onkeypress="return onlyNumbers();" style="width:150px"/>
                    </td>
                </tr>
                <tr>
                    <th colspan="4"><html:submit  value="Submit"  onclick="getapprovedList();" /></th>
                </tr>
            </table>
            <br>

            <logic:notEmpty name="approvedList">
                <br/>
                <div style="overflow:auto; width:900px; height:308px; vertical-align: top ">

                    <table   align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%" >
                        <tr>
                            <th>
                                S.No
                            </th>
                            <th >
                                IssueID
                            </th>
                            <th >
                                Category
                            </th>
                            <th>
                                Description
                            </th>
                            <th>
                                Issue Raised Date
                            </th>
                            <th>
                                Issue Resolved Date
                            </th>
                            <th >
                                Request Status
                            </th>
                            <th >
                                Action
                            </th>
                        </tr>

                        <logic:iterate  name="approvedList" id="row" scope="request" >
                            <tr>
                                <td  >
                                    <%=i++%>.
                                </td>
                                <td >
                                    ${row.requestId}
                                </td>
                                <td>
                                    ${row.categoryName}
                                </td>

                                <td >
                                    ${row.description}
                                </td>
                                <td >
                                    ${row.createdDate}

                                <td >
                                    ${row.updatedDate}
                                </td>
                                <td>
                                    ${row.requeststatus}
                                </td>
                                <%

                                            String s = "personStatusDet" + x;
                                            if (request.getAttribute(s) != null) {
                                                if (request.getAttribute(s).equals("Pending") || request.getAttribute(s).equals("Hold")) {%>
                                <td >
                                    <a href="#" onclick="window.open('issueRaiseApproval.do?mode=getParticularRaisedDetails&categoryId=${row.categoryId}&requestId=${row.requestId}','Ratting','resizable=yes, scrollbars=yes,')">Resolve</a>
                                </td>
                                <%} else {%>
                                <td >
                                    <a href="#" onclick="window.open('issueRaiseApproval.do?mode=getIssueResolvedDetails&requestStatus=<%=statusRequest%>&categoryId=${row.categoryId}&requestId=${row.requestId}','Ratting','resizable=yes, scrollbars=yes,')">View</a>
                                </td>
                                <%}
                                                x++;
                                            }%>
                                <%--     </logic:present>--%>

                            </tr>

                        </logic:iterate>
                    </table>
                </div><br>
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

</html>
