<%-- 
    Document   : Gos
    Created on : Apr 26, 2013, 4:07:56 PM
    Author     : 484898
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>

<script>
    function getDetails(){
        document.getElementById("mode").value="getDescriptionWise";
        document.getElementById("form1").submit();
    }
</script>
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
<body>
    <html:form action="/Gos" styleId="form1">
        <html:hidden property="mode" styleId="mode"/>
        <logic:present name="msg">
            <center>
                <font color="red"><bean:write name="msg"/></font>
            </center>
        </logic:present>
        <logic:present name="nodata">
            <center>
                <font color="red"><bean:write name="nodata"/></font>
            </center>
        </logic:present>
        <logic:present name="nodataMSG1">

            <table width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform" id="resultList">
                <tr>
                    <th colspan="9">General GOs & Acts</th>
                </tr>
                <tr>

                    <td colspan="9"> <font color="red"><bean:write name="nodataMSG1"/></font></td>
                </tr>
            </table>
        </logic:present>

        <logic:notEmpty name="gosDetailssec1">

            <table width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform" id="resultList">
                <tr>
                    <th colspan="9">General GOs & Acts</th>
                </tr>
                <tr>
                    <th width="6%" >S.No.</th>
                    <th width="22%" >G.O. No.</th>
                    <th width="16%" >G.O. Date</th>
                    <th width="50%"  style="text-align:center; padding-left:15px;">Description</th>
                     <th width="16%" >Download</th>
                </tr>
                <logic:iterate id="row" name="gosDetailssec1">
                    <tr>
                        <td  style="text-align:center;"><%=i++%>.</td>
                        <td align="center" style="text-align:left;" >
                          -
                        </td>
                        <td align="center" style="text-align:center;" >${row.Godate}</td>
                        <td  style="text-align:left;">${row.subject}</td>
                           <td align="center" style="text-align:left;" >
                            <a target="_blank" href="./Gos.do?mode=downloadFileHomePage&id=${row.Go_no}" style="text-decoration: none"><img src="<%=basePath%>./DisabilityUITG/images/download.png" style="border: 0px"/></a>
                        </td>
                    </tr>
                </logic:iterate>

            </table>
            <%--<div id="pageNavPosition"></div>
             <script type="text/javascript">
                 var pager = new Pager('resultList',10);
                 pager.init();
                 pager.showPageNav('pager', 'pageNavPosition');
                 pager.showPage(1);
             </script> --%>

        </logic:notEmpty>
        <br/>
        <logic:present name="nodataMSG2">
            <table width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform" id="resultList">
                <tr>
                    <th colspan="9">SADAREM GO's</th>
                </tr>
                <tr>
                    <td colspan="9"> <font color="red"><bean:write name="nodataMSG2"/></font></td>
                </tr>
            </table>

        </logic:present>
        <logic:notEmpty name="gosDetailssec2">
            <table width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform" id="resultList">
                <tr>
                    <th colspan="9">SADAREM GO's</th>
                </tr>
                <tr>
                    <th width="6%" >S.No.</th>
                    <th width="22%" >G.O. No.</th>
                    <th width="16%" >G.O. Date</th>
                    <th width="50%"  style="text-align:center; padding-left:15px;">Description</th>
                      <th width="16%" >Download</th>
                </tr>
                <logic:iterate id="row" name="gosDetailssec2">
                    <tr>
                        <td  style="text-align:center;"><%=i++%>.</td>
                        <td align="center" style="text-align:left;" >
                           ${row.UploadGo}
                        </td>
                        <td align="center" style="text-align:center;" >${row.Godate}</td>
                        <td  style="text-align:left;">${row.subject}</td>
                        
                           <td align="center" style="text-align:left;" >
                            <a target="_blank" href="./Gos.do?mode=downloadFileHomePage&id=${row.Go_no}" style="text-decoration: none"><img src="<%=basePath%>./DisabilityUITG/images/download.png" style="border: 0px"/></a>
                        </td>
                    </tr>
                </logic:iterate>

            </table>
        </logic:notEmpty>
    </html:form>
</body>
