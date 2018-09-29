<%--
    Document   : UploadGosandActs
    Created on : Apr 19, 2013, 2:21:42 PM
    Author     : 695048
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<script src="./DisabilityUITG/js/Ajax.js"></script>
<script src="./DisabilityUITG/js/Validation.js"></script>
<script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>
<script>

    function displayOther(){

        if(document.forms[0].elements['goCategory'].value=="5"){
            document.getElementById('other').style.display ='';

        }else {
            document.getElementById('other').style.display ='none';


        }    }

    function uploadPhoto(str,photo)
    {
        var ext = str.value;
        ext = ext.toLowerCase().substring(ext.lastIndexOf(".")+1,ext.length);
        if(ext != "pdf"  && ext != "PDF")
        {
            alert('You selected a " .'+ext+' "  file; Please Select a " .pdf/.PDF " file');
            document.getElementById(photo).innerHTML = document.getElementById(photo).innerHTML;
            return false;
        }else{
            return true;
        }
    }
    function insertDetails(){
        if(document.forms[0].elements['goSection'].value=="00"){
            alert("Please Select Section");
            document.forms[0].elements['goSection'].focus();
            return false;
        }else if(document.forms[0].elements['goType'].value=="00"){
            alert("Please Select Go Type");
            document.forms[0].elements['goType'].focus();
            return false;
        }else if(document.forms[0].elements['dateofApplication'].value==""){
            alert("Please Select GO Date");
            document.forms[0].elements['dateofApplication'].focus();
            return false;
        }else if(document.forms[0].elements['goNumber'].value==""){
            alert("Please Enter Go Number");
            document.forms[0].elements['goNumber'].focus();
            return false;
        }else if(document.forms[0].elements['goCategory'].value=="00" || document.forms[0].elements['goCategory1'].value==""){
            alert("Please Select Go Category");
            document.forms[0].elements['goCategory'].focus();
            document.forms[0].elements['goCategory1'].focus();
            return false;
        }else if(document.forms[0].elements['uploadGosActs'].value==""){
            alert("Please Upload Go");
            document.forms[0].elements['uploadGosActs'].focus();
            return false;
        }else if(document.forms[0].elements['godescription'].value==""){
            alert("Please Enter Subject");
            document.forms[0].elements['godescription'].focus();
            return false;
        }else{
            document.forms[0].mode.value="insertDetails";
            document.forms[0].submit();
        }
    }

    function updateDetails(){
      if(document.forms[0].elements['goSection'].value=="00"){
            alert("Please Select Section");
            document.forms[0].elements['goSection'].focus();
            return false;
        }else if(document.forms[0].elements['goType'].value=="00"){
            alert("Please Select Go Type");
            document.forms[0].elements['goType'].focus();
            return false;
        }else if(document.forms[0].elements['dateofApplication'].value==""){
            alert("Please Select GO Date");
            document.forms[0].elements['dateofApplication'].focus();
            return false;
        }else if(document.forms[0].elements['goNumber'].value==""){
            alert("Please Enter Go Number");
            document.forms[0].elements['goNumber'].focus();
            return false;
        }else if(document.forms[0].elements['goCategory'].value=="00"){
            alert("Please Select Go Category");
            document.forms[0].elements['goCategory'].focus();
            return false;

        }else if(document.forms[0].elements['goCategory1'].value==""){
            alert("Please Enter Other Category Name ");
            document.forms[0].elements['goCategory1'].focus();
            return false;
        }else if(document.forms[0].elements['godescription'].value==""){
            alert("Please Enter Subject");
            document.forms[0].elements['godescription'].focus();
            return false;
        }else{
            document.forms[0].mode.value="updateDetails";
            document.forms[0].submit();
        }
    }

   
    function disp_confirm(id){
        var row=document.forms[0].rowId.value;
        if(row!=id){
            var name=confirm("Are You Sure! you want to Delete ?")
            if (name==true)
            {

                document.forms[0].action ="./uploadGosActs.do?mode=inActiveStatus&tokenCodeChecking=${tokenCode}&regNo="+id;
                document.forms[0].submit();
                //window.close();
            } else { }
        }
        else{
            alert('Cannot Delete the Field that is selected');

        }

    }

  
    function textCounter(field,cntfield,maxlimit) {
        if (field.value.length > maxlimit){  // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
            // otherwise, update 'characters left' counter
        }
        else{
            cntfield.value = maxlimit - field.value.length;
        }

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


<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            int i = 1;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <html:form action="/uploadGosActs" enctype="multipart/form-data">
            <html:hidden property="mode"/>
            <html:hidden property="rowId"/>
            <input type="hidden" name="tokenCodeChecking" value="${tokenCode}"/>
            <logic:present name="msg">
                <center><font color="green" size="2">${msg}</font></center>
            </logic:present>
            <logic:present name="nodata">
                <center><font color="red" size="2">${nodata}</font></center>
            </logic:present>

            <table align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform">
                <tr><th colspan="9">GOs and Acts</th>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="right">Section : <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="left"><html:select property="goSection" style="height:18px;width:230px">
                            <html:option value="00">--Select--</html:option>
                            <html:option value="1">General GOs & Acts</html:option>
                            <html:option value="2">SADAREM GO's</html:option>
                           <%-- <html:option value="3">CE-III</html:option>--%>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="left">GO Type : <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="right"><html:select property="goType" style="height:18px;width:230px">
                            <html:option value="00">--Select--</html:option>
                            <html:option value="MS">MS</html:option>
                            <html:option value="RT">RT</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="left">GO Date</td>
                    <td>
                        <html:text property="dateofApplication" styleId="8"  readonly="true" size="12"/>
                        <a  href="javascript:show_calendar('forms[0].dateofApplication');"
                            onmouseover="window.status='Date Picker';return true;"
                            onmouseout="window.status='';return true;">
                            <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                    </td>

                </tr>
                <tr>
                    <td width="22%" class="formbg1" align="left">GO No. : <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="right">
                        <html:text property="goNumber" onkeypress="return space(event,this)" onkeypress="return onlyNumbers(window.event);" title="Only numbers" style="height:18px;width:230px" maxlength="20"/></td>
                </tr>

                <tr>
                    <td width="22%" class="formbg1" align="left">GO Category  : <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="right">
                        <html:select property="goCategory" style="height:18px;width:230px" onchange="displayOther(this);">
                            <html:option value="00">--Select--</html:option>
                           <%-- <html:option value="1">Budget Release Order</html:option>
                            <html:option value="2">Service Matter</html:option>
                            <html:option value="3">Tours</html:option>
                            <html:option value="4">Transfers</html:option>--%>
                            <html:option value="5">Others</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr id="other" style="display: none">
                    <td width="22%" class="formbg1" align="left">Other Category Name <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="right">
                        <html:text property="goCategory1" onkeypress="return space(event,this)" style="height:18px;width:230px" maxlength="20"/>
                    </td>
                </tr>

                <tr>
                    <td width="22%" class="formbg1" align="left">Upload GO : <logic:notPresent name="updateButton"><font color="red">*</font></logic:notPresent></td>

                    <td width="28%" class="formbg2" align="right" height="50px">
                        <div id="photo"><html:file property="uploadGosActs" onchange="return uploadPhoto(this,'photo');" style="height:18px;width:230px"/>
                        </div> </td>
                </tr>

                <tr>
                    <td width="22%" class="formbg1" align="left">Description : <font color="red">*</font></td>
                    <td width="28%" class="formbg2" align="right">
                        <html:textarea property="godescription" rows="3" cols="40" onkeypress="return space(event,this)" style="height:60px;width=230px" onkeydown="textCounter(this,document.forms[0].remLen1,1000)" onkeyup="textCounter(this,document.forms[0].remLen1,1000)"/>
                    </td>
                </tr>
                <tr>
                    <logic:notPresent name="updateButton">
                        <th colspan="2" align="center" class="formbg2" style="text-align: center">
                            <html:button property="sub" value="Submit" onclick="insertDetails();"/>
                        </th>
                    </logic:notPresent>
                    <logic:present name="updateButton">
                        <th colspan="2" align="center" class="formbg2" style="text-align: center">
                            <html:button property="suub" value="Update" onclick="updateDetails();"/>
                        </th>
                    </logic:present>
                </tr>
            </table>

            <logic:notEmpty name="uploadDetails"><br/>
                <table align="center" width="90%" border="0" cellspacing="1" cellpadding="0" class="inputform"   id="resultList" style="table-layout: fixed; ">
                    <tr><th colspan="8">GOs and Acts</th>
                    <tr>
                        <th width="6%" class="gridhdbg">S.No</th>
                        <th width="10%" class="gridhdbg">GO.No</th>
                        <th width="6%" class="gridhdbg">GO Type</th>
                        <th width="15%" class="gridhdbg">Description</th>
                        <th width="11%" class="gridhdbg">GO Date</th>
                        <th width="11%" class="gridhdbg">GO Uploaded Date</th>
                        <th width="11%" class="gridhdbg">Last Modified Date</th>

                        <th width="10%" class="gridhdbg">Action</th>
                    </tr>

                    <logic:iterate name="uploadDetails" id="row">
                        <tr>
                            <td class="gridbg1" ><%=i++%>.</td>
                            <td class="gridbg1" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.Go_no}</td>
                            <td class="gridbg1" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.GoType}</td>
                            <td class="gridbg0" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.subject}</td>
                            <td class="gridbg1" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.Godate}</td>
                            <td class="gridbg1" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.CreatedDate}</td>
                            <td class="gridbg1" style="text-align: left;word-wrap:break-word;height: 100%;vertical-align: top">${row.updatedDate}</td>
                            <%-- <td class="gridbg1"><a target="_blank" href="./Gos.do?mode=downloadFileHomePage&id=${row.Go_no}">${row.UploadGo}</a></td>--%>
                            <td class="gridbg1" align="center" nowrap >
                                <a  href="./uploadGosActs.do?mode=downloadFileHomePage&id=${row.Go_no}" style="text-decoration: none;border: 0px; ">
                                    <img src="<%=basePath%>DisabilityUITG/images/view.jpg" border="0" height="25px" width="25px" alt="View"/></a>

                                <a href="./uploadGosActs.do?mode=getEditDetails&regNo=${row.rowid}"  style="text-decoration: none;border: 0px;">&nbsp;
                                    <img src="<%=basePath%>DisabilityUITG/images/Edit.jpg" border="0" height="25px" width="25px" alt="Edit"/></a>
                                <a href="#"><img src="<%=basePath%>DisabilityUITG/images/delete.jpg" border="0" onclick="disp_confirm(${row.rowid});" height="25px" width="25px" style="border-left: 2px solid grey" alt="Delete"/></a></td>
                        </tr>
                    </logic:iterate>
                </table>

            </logic:notEmpty>
            <table align="center">
                <tr>
                    <td align="right" colspan="2">
                        <div id="pageNavPosition"></div>
                    </td>
                </tr>
            </table>

        </html:form>
        <script type="text/javascript">
            var pager = new Pager('resultList',10);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        </script>

    </body>
</html>
