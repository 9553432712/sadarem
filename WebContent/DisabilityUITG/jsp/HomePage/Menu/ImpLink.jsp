<%--
    Document   : TenderNotice
    Created on : Jun 25, 2013, 3:22:43 PM
    Author     : 693461
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
    <%
                String webSiteId = (String) request.getAttribute("webSiteId");

    %>
    <%
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                int i = 1;
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>
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


            function webDetails(){
                if(document.forms[0].elements['webSiteName'].value==""){
                    alert(" Plerase Enter webSiteName");
                    document.forms[0].elements['webSiteName'].focus();
                    return false;
                } else if(document.forms[0].elements['webSiteUrl'].value==""){
                    alert("Please Enter webSiteUrl");
                    document.forms[0].elements['webSiteUrl'].focus();
                    return false;
                }
                else{
                    document.forms[0].mode.value="insertWebDetails";
                    document.forms[0].submit();
                }
            }

            function updatewebDetails(){
                if(document.forms[0].elements['webSiteName'].value==""){
                    alert(" Plerase Enter webSiteName");
                    document.forms[0].elements['webSiteName'].focus();
                    return false;
                } else if(document.forms[0].elements['webSiteUrl'].value==""){
                    alert("Please Enter webSiteUrl");
                    document.forms[0].elements['webSiteUrl'].focus();
                    return false;
                }
                else{
                    document.forms[0].mode.value="updateImpLinkDetails";
                    document.forms[0].submit();
                }

        
            }
           
            function disp_confirm(id){
                var row=document.forms[0].webSiteId.value;
                if(row!=id){
                    var name=confirm("Are You Sure! you want to Delete ?")
                    if (name==true)
                    {

                        document.forms[0].action ="./impLink.do?mode=deleteImpLinkDetails&tokenCodeChecking=${tokenCode}&webSiteId="+id;
                        document.forms[0].submit();
                        //window.close();
                    } else { }
                }
                else{
                    alert('Cannot Delete the Field that is selected');

                }

            }
        </script>
        <script language=javascript src="./js/popcalendar.js"></script>
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
    </head>
    <body>
        <html:form action="/impLink">
            <html:hidden property="mode"/>

            <html:hidden property="webSiteId" value="<%=webSiteId%>"/>

            <logic:present name="msg">
                <center>
                    <font color="green">${msg}</font>
                </center>
            </logic:present>
            <logic:present name="msgerror">
                <center>
                    <font color="red">${msgerror}</font>
                </center>
            </logic:present>

            <table align="center" width="70%" border="0" cellspacing="0" cellpadding="0" class="inputform">
                <tr>
                    <th colspan="4" >Important Links</th>
                </tr>
                <tr>
                    <td>Website Name: <font color="red"><b>* </b></font></td>
                    <td><html:text property="webSiteName" maxlength="50" style="height:25px;width=200px" onkeypress="return space(event,this);"/></td>
                </tr>
                <tr>
                    <td>Website URL: <font color="red"><b>* </b></font></td>
                    <td><html:text property="webSiteUrl" maxlength="100" style="height:25px;width=200px" onkeypress="return space(event,this);"/>

                </tr>

                <%if (request.getAttribute("editData") == null) {%>
                <tr>
                    <th colspan="4" align="center" valign="middle" style="text-align:center;">
                        <html:button property="sub" value="Submit" onclick="return webDetails();"/>
                    </th>
                </tr>


                <%}%>

                <%if (request.getAttribute("editData") != null) {%>
                <tr>
                    <th colspan="2" align="center" >
                        <html:button property="sub" value="update" onclick="return updatewebDetails();"/>
                    </th>
                </tr>
                <%}%>
            </table><br/>

            <logic:notEmpty name="impList">

                <table align="center" cellpadding="0" cellspacing="1" border="0" class="inputform" width="70%" id="resultList">
                    <tr>
                        <th colspan="8" >Important Links</th>
                    </tr>
                    <tr> <th >
                            S.No
                        </th>
                        <th >
                            Website Name
                        </th>
                        <th >
                            Website Url
                        </th>
                        <th >
                            Edit
                        </th>
                        <th >
                            Delete
                        </th>
                    </tr>


                    <logic:iterate name="impList" id="row">
                        <tr>
                            <td  style="text-align: left">
                                <%=i++%>
                            </td >
                            <td  style="text-align: left">
                                ${row.webSiteName}
                            </td >
                            <td  style="text-align: left">
                                <a href="${row.webSiteUrl}" target="_blank">${row.webSiteUrl}</a>
                            </td>
                            <td  align="right">
                                <a href="./impLink.do?mode=editImpLinkDetails&webSiteId=${row.rowId}"><img src="<%=basePath%>DisabilityUITG/images/Edit.jpg" border="0" height="25px" width="25px" alt="Edit"/></a>
                            </td>
                           
                            <td  style="text-align: center">
                                <a href="#" ><img src="<%=basePath%>DisabilityUITG/images/delete.jpg" alt="Delete" border="0" onclick="disp_confirm(${row.rowId});" height="25px" width="25px" /></a>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>

            </logic:notEmpty>
            <br/>
            <logic:present name="page">
                <div id="pageNavPosition"></div>
            </logic:present>

       
<script type="text/javascript">
    var pager = new Pager('resultList',10);
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition');
    pager.showPage(1);
</script>
</body>
</html:form>
</html>

